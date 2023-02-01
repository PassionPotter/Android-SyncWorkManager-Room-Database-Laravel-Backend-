package com.androidigniter.excelapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "resource_activation_table")
public class ResourceActivationResponse {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("model_name")
    @Expose
    private String model_name;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("arrival_date")
    @Expose
    private String arrival_date;
    @SerializedName("ready_to_work")
    @Expose
    private String ready_to_work;
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

    public String getArrival_date() {
        return arrival_date;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }

    public String getModel_name() {
        return model_name;
    }

    public String getReady_to_work() {
        return ready_to_work;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public void setReady_to_work(String ready_to_work) {
        this.ready_to_work = ready_to_work;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
}