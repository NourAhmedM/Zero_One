package com.example.android.secrethands.datastructures;

import java.util.Date;

/**
 * Created by Aly on 7/29/2019.
 */

public class Session {
    private double duration;
    private Date startTime;
    private boolean booked;
    private String doctorId;
    private String patientId;

    public Session(double duration, Date startTime, boolean booked, String doctorId, String patientId) {
        this.duration = duration;
        this.startTime = startTime;
        this.booked = booked;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public double getDuration() {
        return duration;
    }

    public Date getStartTime() {
        return startTime;
    }

    public boolean isBooked() {
        return booked;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getPatientId() {
        return patientId;
    }
}
