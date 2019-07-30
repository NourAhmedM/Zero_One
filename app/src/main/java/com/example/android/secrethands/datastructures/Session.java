package com.example.android.secrethands.datastructures;

import java.util.Date;

/**
 * Created by Aly on 7/29/2019.
 */

public class Session {
    private Date endTime;
    private Date startTime;
    private boolean booked;
    private String doctorId;
    private String patientId;

    public Session(Date endTime, Date startTime, boolean booked, String doctorId, String patientId) {
        this.endTime = endTime;
        this.startTime = startTime;
        this.booked = booked;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public void setDuration(Date endTime) {
        this.endTime = endTime;
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

    public Date getDuration() {
        return endTime;
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
