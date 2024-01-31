package com.e2.medicalequipment;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import com.e2.medicalequipment.dto.UpdateCompanyAdministratorDTO;
import com.e2.medicalequipment.model.Address;
import com.e2.medicalequipment.model.CompanyAdministrator;
import com.e2.medicalequipment.repository.CompanyAdministratorRepository;
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

    @Autowired
    private CompanyAdministratorRepository companyAdministratorRepository;

    @Test(expected = PessimisticLockingFailureException.class)
    public void testPessimisticLockingScenario() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        CreateAppointmentDTO appointmentDTO = new CreateAppointmentDTO();
        appointmentDTO.isPredefined = true;
        appointmentDTO.startTime = "Fri Feb 09 2024 14:30:00 GMT+0100 (Central European Standard Time)";
        appointmentDTO.endTime = "Fri Feb 09 2024 15:00:00 GMT+0100 (Central European Standard Time)";
        UpdateCompanyAdministratorDTO administratorDTO = new UpdateCompanyAdministratorDTO();
        administratorDTO.id = -2L;
        administratorDTO.name = "Petar";
        administratorDTO.lastname = "Company";
        administratorDTO.password = "$2a$10$lnAdFe/m6/0IuJtGoO11IuYPp.rgG0gchAud/480F84lwnI5Ejau6";
        administratorDTO.companyId = -1L;
        administratorDTO.phoneNumber = "0504901001";
        administratorDTO.username = "petar@gmail.com";
        administratorDTO.address = new Address(-2L, "Karadjordjeva 17b", "Vlasenica", "BIH", 19.849171,45.242092);
        appointmentDTO.companyAdministrator = administratorDTO;


        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan Thread 1");

                //appointmentService.findOneById(-2L);
                appointmentService.Create(appointmentDTO);
            }
        });
        Future<?> future2 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                try { Thread.sleep(20); } catch (InterruptedException e) { }

                //appointmentService.findOneById(-2L);
                appointmentService.Create(appointmentDTO);
            }
        });
        try {
            future2.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass());
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
