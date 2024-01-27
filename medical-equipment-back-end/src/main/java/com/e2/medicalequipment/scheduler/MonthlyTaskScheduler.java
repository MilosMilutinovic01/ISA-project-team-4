package com.e2.medicalequipment.scheduler;

import com.e2.medicalequipment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MonthlyTaskScheduler {
    @Autowired
    UserService userService;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void deleteData() {
        userService.deleteAllPenaltyPoints(0);
    }
}
