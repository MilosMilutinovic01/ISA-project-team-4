package com.e2.medicalequipment.scheduler;

import com.e2.medicalequipment.kafka.ContractProducer;
import com.e2.medicalequipment.model.Contract;
import com.e2.medicalequipment.service.ContractService;
import com.e2.medicalequipment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MonthlyTaskScheduler {
    @Autowired
    UserService userService;

    @Autowired
    ContractProducer contractProducer;

    @Autowired
    ContractService contractService;

    @Scheduled(cron = "0 36 13 28 * ?")
    public void deleteData() {
        userService.deleteAllPenaltyPoints(0);
    }

    @Scheduled(cron = "0 53 15 * * ?")
    public void sendMessageForDelivery() throws Exception {
        List<Contract> contracts = contractService.GetAll();


        for (Contract contract : contracts) {
            int deliveryDay = contract.getDateInMonth();

            if (isTodayDeliveryDay(deliveryDay)) {
                contractProducer.sendMessage("Dostava je zapoceta za bolnicu: " + contract.getHospital());
            }
        }
    }

    private boolean isTodayDeliveryDay(int deliveryDay) {
        int todayDayOfMonth = java.time.LocalDate.now().getDayOfMonth();
        return todayDayOfMonth == deliveryDay;
    }
}
