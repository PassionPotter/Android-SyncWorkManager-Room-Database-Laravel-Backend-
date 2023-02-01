package com.androidigniter.excelapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "personel_table")
public class PersonelResponse {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("week")
    @Expose
    private Integer week;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("n_workers_day")
    @Expose
    private Integer n_workers_day;
    @SerializedName("n_workers_night")
    @Expose
    private Integer n_workers_night;
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

    public Integer getWeek() {
        return week;
    }

    public Integer getN_workers_night() {
        return n_workers_night;
    }

    public String getDate() {
        return date;
    }

    public Integer getN_workers_day() {
        return n_workers_day;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setN_workers_day(Integer n_workers_day) {
        this.n_workers_day = n_workers_day;
    }

    public void setN_workers_night(Integer n_workers_night) {
        this.n_workers_night = n_workers_night;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
}