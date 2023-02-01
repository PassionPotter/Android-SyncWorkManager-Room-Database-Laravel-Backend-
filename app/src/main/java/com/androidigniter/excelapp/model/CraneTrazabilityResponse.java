package com.androidigniter.excelapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "crane_trazability_table")
public class CraneTrazabilityResponse {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("wtg")
    @Expose
    private String wtg;
    @SerializedName("component_type")
    @Expose
    private String component_type;
    @SerializedName("serial_number")
    @Expose
    private Integer serial_number;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("comments")
    @Expose
    private String comments;

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

    public String getComments() {
        return comments;
    }

    public String getWtg() {
        return wtg;
    }

    public Integer getSerial_number() {
        return serial_number;
    }

    public String getComponent_type() {
        return component_type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setWtg(String wtg) {
        this.wtg = wtg;
    }

    public void setSerial_number(Integer serial_number) {
        this.serial_number = serial_number;
    }

    public void setComponent_type(String component_type) {
        this.component_type = component_type;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
}