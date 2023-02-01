package com.androidigniter.excelapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "task_table")
public class TaskResponse {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("projectID")
    @Expose
    private String projectID;
    @SerializedName("taskID")
    @Expose
    private String taskID;
    @SerializedName("WTGID")
    @Expose
    private String WTGID;
    @SerializedName("status")
    @Expose
    private Double status;
    @SerializedName("number_workers")
    @Expose
    private Integer number_workers;
    @SerializedName("serial_number")
    @Expose
    private Integer serial_number;
    @SerializedName("shift_option")
    @Expose
    private Integer shift_option;
    @SerializedName("comments")
    @Expose
    private String comments;

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

    public String getComments() {
        return comments;
    }

    public Integer getShift_option() {
        return shift_option;
    }

    public Double getStatus() {
        return status;
    }

    public Integer getNumber_workers() {
        return number_workers;
    }

    public Integer getSerial_number() {
        return serial_number;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getWTGID() {
        return WTGID;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setNumber_workers(Integer number_workers) {
        this.number_workers = number_workers;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public void setSerial_number(Integer serial_number) {
        this.serial_number = serial_number;
    }

    public void setShift_option(Integer shift_option) {
        this.shift_option = shift_option;
    }

    public void setStatus(Double status) {
        this.status = status;
    }

    public void setWTGID(String WTGID) {
        this.WTGID = WTGID;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }
}