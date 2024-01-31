package com.e2.medicalequipment;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.e2.medicalequipment.dto.CreateCustomerDTO;
import com.e2.medicalequipment.model.Address;
import com.e2.medicalequipment.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationConcurentAccessTest {
    @Autowired
    private CustomerService customerService;

    @Test(expected = PessimisticLockingFailureException.class)
    public void testPessimisticLockingScenario() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        CreateCustomerDTO customerDTO1 = new CreateCustomerDTO();
        customerDTO1.name = "ime1";
        Address address1 = new Address(-1L, "Kraljevo", "Srbija", "Ibarska 2", 19.849171,45.242092);
        customerDTO1.lastname = "prezime1";
        customerDTO1.password = "jaka";
        customerDTO1.username = "miki@gmail.com";
        customerDTO1.phoneNumber = "0603908001";
        customerDTO1.profession = "student1";
        customerDTO1.address = address1;

        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                try {
                    customerService.Create(customerDTO1);
                } catch (PessimisticLockingFailureException e){
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Future<?> future2 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                try { Thread.sleep(20); } catch (InterruptedException e) { }// otprilike 150 milisekundi posle prvog threada krece da se izvrsava drugi

                try {
                    customerService.Create(customerDTO1);
                } catch (PessimisticLockingFailureException e){
                    throw e;
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
