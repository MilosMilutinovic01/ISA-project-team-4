package com.e2.medicalequipment;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import com.e2.medicalequipment.dto.UpdateItemDTO;
import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.service.AppointmentService;
import com.e2.medicalequipment.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationsConcurrentAccessTest {
    @Autowired
    ItemService itemService;

    @Autowired
    AppointmentService appointmentService;

    @Test(expected = PessimisticLockingFailureException.class)
    public void testPessimisticLockingScenario() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        CreateAppointmentDTO appointmentDTO = new CreateAppointmentDTO();
        appointmentDTO.startTime = "Fri Feb 02 2024 14:30:00 GMT+0100 (Central European Standard Time)";
        appointmentDTO.endTime = "Fri Feb 02 2024 15:00:00 GMT+0100 (Central European Standard Time)";
        appointmentDTO.isPredefined = false;
        appointmentDTO.companyAdministrator = null;
        String customerId1 = "-8";
        String customerId2 = "-9";

        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                Appointment savedAppointment = appointmentService.CreateIrregular(appointmentDTO, customerId1);
            }
        });
        Future<?> future2 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                try { Thread.sleep(50); } catch (InterruptedException e) { }
                Appointment savedAppointment = appointmentService.CreateIrregular(appointmentDTO, customerId2);
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
