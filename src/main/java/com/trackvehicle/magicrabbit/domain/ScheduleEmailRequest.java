package com.trackvehicle.magicrabbit.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.sun.istack.NotNull;

public class ScheduleEmailRequest {

    private String email;

    private String subject;

    private String body;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private ZoneId timeZone;

    // Getters and Setters (Omitted for brevity)

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ZoneId getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(final ZoneId timeZone) {
        this.timeZone = timeZone;
    }

}
