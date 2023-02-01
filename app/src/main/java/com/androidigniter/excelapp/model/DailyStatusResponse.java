package com.androidigniter.excelapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "daily_status_table")
public class DailyStatusResponse {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("total_employee")
    @Expose
    private Integer total_employee;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("entry_time")
    @Expose
    private String entry_time;
    @SerializedName("departure_time")
    @Expose
    private String departure_time;
    @SerializedName("projectID")
    @Expose
    private String projectID;

    @SerializedName("is_sync")
    @Expose
    private boolean is_sync;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setIs_sync(boolean is_sync) {
        this.is_sync = is_sync;
    }
    public boolean getIs_sync() {
        return this.is_sync;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Integer getTotal_employee() {
        return total_employee;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setEntry_time(String entry_time) {
        this.entry_time = entry_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public void setTotal_employee(Integer total_employee) {
        this.total_employee = total_employee;
    }

    public String getEntry_time() {
        return entry_time;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
}