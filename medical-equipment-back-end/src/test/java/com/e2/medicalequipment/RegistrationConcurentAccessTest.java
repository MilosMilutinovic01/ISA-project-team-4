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
        customerDTO1.username = "test@gmail.com";
        customerDTO1.phoneNumber = "0603908001";
        customerDTO1.profession = "student1";
        customerDTO1.address = address1;

        CreateCustomerDTO customerDTO2 = new CreateCustomerDTO();
        customerDTO2.name = "ime2";
        Address address2 = new Address(-2L, "Vlasenica", "BIH", "Karadjordjeva 17b", 19.849171,45.242092);
        customerDTO2.address = address2;
        customerDTO2.lastname = "prezime2";
        customerDTO2.password = "jaka";
        customerDTO2.username = "test@gmail.com";
        customerDTO2.phoneNumber = "0603808001";
        customerDTO2.profession = "student2";

        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                    customerService.findByUsername("miki@gmail.com");
                    //customerService.Create(customerDTO1);
            }
        });
        Future<?> future2 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                try { Thread.sleep(50); } catch (InterruptedException e) { }// otprilike 150 milisekundi posle prvog threada krece da se izvrsava drugi

                    customerService.findByUsername("petar@gmail.com");
                    //customerService.Create(customerDTO2);
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
