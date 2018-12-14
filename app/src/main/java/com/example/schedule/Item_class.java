package com.example.schedule;

import java.util.Locale;

public class Item_class {
    public final long _id;
    public final String subject;
    public final String day;
    public final String time;
    public final String classroom;


    public Item_class(long _id, String subject, String day, String time, String classroom) {
        this._id = _id;
        this.subject = subject;
        this.day = day;
        this.time = time;
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        String msg = String.format(
                Locale.getDefault(),
                "ชื่อวิชา %s\n เวลาเรียน (%s)",
                this.subject,
                this.time
        );
        return msg;
    }
}
