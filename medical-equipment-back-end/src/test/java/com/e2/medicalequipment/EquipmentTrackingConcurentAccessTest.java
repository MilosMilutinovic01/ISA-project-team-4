package com.e2.medicalequipment;

import java.sql.Time;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.e2.medicalequipment.dto.EquipmentDTO;
import com.e2.medicalequipment.dto.EquipmentTrackingDTO;
import com.e2.medicalequipment.dto.UpdateAddressDTO;
import com.e2.medicalequipment.dto.UpdateCompanyDTO;
import com.e2.medicalequipment.model.EquipmentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import com.e2.medicalequipment.service.EquipmentTrackingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipmentTrackingConcurentAccessTest {

    @Autowired
    private EquipmentTrackingService equipmentTrackingService;


    @Test(expected = PessimisticLockingFailureException.class)
    public void testPessimisticLockingScenario() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        EquipmentDTO equipment = new EquipmentDTO();
        equipment.id = -1L;
        equipment.price = 10.0;
        equipment.type = EquipmentType.SURGICAL;
        equipment.name = "maska";
        equipment.description = "hirurska";
        UpdateAddressDTO address = new UpdateAddressDTO();
        address.id = -5l;
        address.street = "Cara Dusana 12";
        address.city = "Novi Sad";
        address.country = "Srbija";
        UpdateCompanyDTO company = new UpdateCompanyDTO();
        company.id = -1L;
        company.startTime = "08:30";
        company.endTime = "14:30";
        company.address = address;
        company.averageRating = 4.0;
        company.description = "Neki opiss";
        company.name = "MEDLAB";
        EquipmentTrackingDTO equipmentTracking = new EquipmentTrackingDTO();
        equipmentTracking.id = -1L;
        equipmentTracking.equipment = equipment;
        equipmentTracking.company = company;
        equipmentTracking.count = 300;
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                equipmentTracking.count = 200;
                //equipmentTrackingService.findOneById(-1L); // izvrsavanje transakcione metode traje oko 200 milisekundi
                equipmentTrackingService.Update(equipmentTracking);
            }
        });
        Future<?> future2 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                try { Thread.sleep(50); } catch (InterruptedException e) { }// otprilike 150 milisekundi posle prvog threada krece da se izvrsava drugi
                /*
                 * Drugi thread pokusava da izvrsi transakcionu metodu findOneById dok se prvo izvrsavanje iz prvog threada jos nije zavrsilo.
                 * Metoda je oznacena sa NO_WAIT, sto znaci da drugi thread nece cekati da prvi thread zavrsi sa izvrsavanjem metode vec ce odmah dobiti PessimisticLockingFailureException uz poruke u logu:
                 * [pool-1-thread-2] o.h.engine.jdbc.spi.SqlExceptionHelper : SQL Error: 0, SQLState: 55P03
                 * [pool-1-thread-2] o.h.engine.jdbc.spi.SqlExceptionHelper : ERROR: could not obtain lock on row in relation "product"
                 * Prema Postgres dokumentaciji https://www.postgresql.org/docs/9.3/errcodes-appendix.html, kod 55P03 oznacava lock_not_available
                 */
               // equipmentTrackingService.findOneById(-1L);
                equipmentTracking.count = 100;
                equipmentTrackingService.Update(equipmentTracking);

            }
        });
        try {
            future2.get(); // podize ExecutionException za bilo koji izuzetak iz drugog child threada
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas PessimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

}
