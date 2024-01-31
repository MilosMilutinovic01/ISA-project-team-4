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
public class PredefinedReservationsConcurrentAccessTest {
    @Autowired
    ItemService itemService;

    @Autowired
    AppointmentService appointmentService;

    @Test(expected = PessimisticLockingFailureException.class)
    public void testPessimisticLockingScenario() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        UpdateItemDTO updatedItem1 = new UpdateItemDTO();
        updatedItem1.CompanyId = -1L;
        updatedItem1.EquipmentId = -4L;
        updatedItem1.Count = 2;
        updatedItem1.CustomerId = -8L;
        updatedItem1.AppointmentId = -1L;
        updatedItem1.Id = -9L;
        updatedItem1.PickedUp = false;

        UpdateItemDTO updatedItem2 = new UpdateItemDTO();
        updatedItem2.CompanyId = -1L;
        updatedItem2.EquipmentId = -4L;
        updatedItem2.Count = 2;
        updatedItem2.CustomerId = -9L;
        updatedItem2.AppointmentId = -1L;
        updatedItem2.Id = -10L;
        updatedItem2.PickedUp = false;
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                itemService.Update(updatedItem1);
            }
        });
        Future<?> future2 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                try { Thread.sleep(50); } catch (InterruptedException e) { }
                itemService.Update(updatedItem2);
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