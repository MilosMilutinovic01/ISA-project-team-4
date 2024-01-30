package com.e2.medicalequipment;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import com.e2.medicalequipment.dto.UpdateCompanyAdministratorDTO;
import com.e2.medicalequipment.model.Address;
import com.e2.medicalequipment.service.AppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CreatingMultipleAppointmentsConcurentAccessTest {
    @Autowired
    private AppointmentService appointmentService;

    @Test(expected = PessimisticLockingFailureException.class)
    public void testPessimisticLockingScenario() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        CreateAppointmentDTO appointmentDTO = new CreateAppointmentDTO();
        appointmentDTO.isPredefined = true;
        appointmentDTO.startTime = "Mon Feb 05 2024 12:30:00 GMT+0100 (Central European Standard Time";
        appointmentDTO.endTime = "Mon Feb 05 2024 13:00:00 GMT+0100 (Central European Standard Time";
        UpdateCompanyAdministratorDTO administratorDTO = new UpdateCompanyAdministratorDTO();
        administratorDTO.id = -20L;
        administratorDTO.name = "ime";
        administratorDTO.lastname = "prezime";
        administratorDTO.password = "lozinka";
        administratorDTO.companyId = -1L;
        administratorDTO.phoneNumber = "0603908001";
        administratorDTO.username = "korisnik";
        administratorDTO.address = new Address(1L,"ulica","grad","zemlja",45.45,45.45);
        appointmentDTO.companyAdministrator = administratorDTO;

        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                try {
                    appointmentService.Create(appointmentDTO);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Future<?> future2 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                try { Thread.sleep(50); } catch (InterruptedException e) { }// otprilike 150 milisekundi posle prvog threada krece da se izvrsava drugi

                try {
                    appointmentService.Create(appointmentDTO);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        try {
            future2.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas PessimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
