package com.parkify.vehitrack.helper;

import com.parkify.vehitrack.entity.Visitors;

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
}
