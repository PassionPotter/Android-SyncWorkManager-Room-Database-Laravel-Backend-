package com.androidigniter.excelapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "crane_task_table")
public class CraneTaskResponse {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("projectID")
    @Expose
    private String projectID;
    @SerializedName("WTGID")
    @Expose
    private String WTGID;
    @SerializedName("status")
    @Expose
    private Double status;
    @SerializedName("crane_type")
    @Expose
    private String craneID;
    @SerializedName("shift_option")
    @Expose
    private Integer shift_option;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("taskID")
    @Expose
    private String taskID;

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


    public String getProjectID() {
        return projectID;
    }

    public String getWTGID() {
        return WTGID;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    public void setProjectID(String projectID) {
        this.projectID = projectID;
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

    public String getCraneID() {
        return craneID;
    }

    public void setCraneID(String craneID) {
        this.craneID = craneID;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }
}