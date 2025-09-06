package com.parkify.vehitrack.scheduler;


import com.parkify.vehitrack.service.VisitorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VisitorBackupScheduler {

    private VisitorService visitorBackupService;

    // Runs daily at 11 PM
    @Scheduled(cron = "0 0 23 * * ?")
    public void runDailyBackup() {
        visitorBackupService.createDailyVisitorBackup();
    }

}
