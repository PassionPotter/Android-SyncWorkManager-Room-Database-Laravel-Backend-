package com.androidigniter.excelapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "crane_inefficiencies_table")
public class CraneInefficienciesResponse {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("project")
    @Expose
    private String project;
    @SerializedName("wtg")
    @Expose
    private String wtg;
    @SerializedName("hours")
    @Expose
    private Integer hours;
    @SerializedName("responsible")
    @Expose
    private String responsible;
    @SerializedName("validated")
    @Expose
    private String validated;
    @SerializedName("cause")
    @Expose
    private String cause;
    @SerializedName("subcause")
    @Expose
    private String subcause;
    @SerializedName("justification")
    @Expose
    private String justification;
    @SerializedName("crane_type")
    @Expose
    private String crane_type;
    @SerializedName("shift_option")
    @Expose
    private Integer shift_option;
    @SerializedName("partial_time")
    @Expose
    private String partial_time;

    @SerializedName("is_sync")
    @Expose
    private boolean is_sync;
    @SerializedName("taskID")
    @Expose
    private String taskID;

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

    public Integer getHours() {
        return hours;
    }

    public String getCause() {
        return cause;
    }

    public String getJustification() {
        return justification;
    }

    public String getProject() {
        return project;
    }

    public String getResponsible() {
        return responsible;
    }

    public String getSubcause() {
        return subcause;
    }

    public String getValidated() {
        return validated;
    }

    public String getWtg() {
        return wtg;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public void setSubcause(String subcause) {
        this.subcause = subcause;
    }

    public void setValidated(String validated) {
        this.validated = validated;
    }

    public void setWtg(String wtg) {
        this.wtg = wtg;
    }


    public Integer getShift_option() {
        return shift_option;
    }

    public String getPartial_time() {
        return partial_time;
    }

    public String getCrane_type() {
        return crane_type;
    }

    public void setShift_option(Integer shift_option) {
        this.shift_option = shift_option;
    }

    public void setCrane_type(String crane_type) {
        this.crane_type = crane_type;
    }

    public void setPartial_time(String partial_time) {
        this.partial_time = partial_time;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }
}