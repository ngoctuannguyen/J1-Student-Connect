package com.example.j1studentconnect;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.util.Locale;

public class ThisLocalizedWeek {
    private final static ZoneId TZ = ZoneId.of("Asia/Ho_Chi_Minh");
    private final Locale locale;
    private final DayOfWeek firstDayofWeek;
    private final DayOfWeek lastDayofWeek;

    public ThisLocalizedWeek(Locale locale, DayOfWeek firstDayofWeek, DayOfWeek lastDayofWeek) {
        this.locale = locale;
        this.firstDayofWeek = firstDayofWeek;
        this.lastDayofWeek = DayOfWeek.of((this.firstDayofWeek.getValue() + 5) % DayOfWeek.values().length + 1);
    }

//    public LocalDate getFirstDay(){
//        return LocalDate.now(TZ).with(TemporalAdjuster.previousOrSame(this.firstDayofWeek));
//    }
//
//    public LocalDate getLastDay(){
//        return LocalDate.now(TZ).with(TemporalAdjuster.nextOrSame(this.lastDayofWeek));
//    }

    public String toString(){
        return String.format("the %s week starts on %s and ends on %s",
                this.locale.getDisplayName(),
                this.firstDayofWeek,
                this.lastDayofWeek);
    }
}
