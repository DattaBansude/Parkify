package com.parkify.vehitrack.helper;

import com.parkify.vehitrack.entity.Visitors;

import java.time.Duration;
import java.time.LocalDateTime;

public class VisitorHelper {

    public static void setEntryTime(Visitors visitor) {
        visitor.setTimeIn(LocalDateTime.now());
        visitor.setActiveVisitor(true);
    }

    // Decide exit time
    public static LocalDateTime resolveExitTime(LocalDateTime exitTime) {
        return (exitTime != null) ? exitTime : LocalDateTime.now();
    }


    // New: Calculate visit duration
    public static String calculateDuration(LocalDateTime timeIn, LocalDateTime timeOut) {
        if (timeIn == null || timeOut == null) {
            return null;
        }
        Duration duration = Duration.between(timeIn, timeOut);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}
