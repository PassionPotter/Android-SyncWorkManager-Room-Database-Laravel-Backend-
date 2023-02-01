package com.androidigniter.excelapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "crane_recolocation_table")
public class CraneRecolocationResponse {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("movement_from")
    @Expose
    private String movement_from;
    @SerializedName("movement_to")
    @Expose
    private String movement_to;
    @SerializedName("crane_type")
    @Expose
    private String crane_type;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("assemble_option")
    @Expose
    private String assemble_option;
    @SerializedName("start_date")
    @Expose
    private String start_date;
    @SerializedName("start_hour")
    @Expose
    private String start_hour;
    @SerializedName("end_date")
    @Expose
    private String end_date;
    @SerializedName("end_hour")
    @Expose
    private String end_hour;

    @SerializedName("total_hours")
    @Expose
    private Integer total_hours;
    @SerializedName("hours_contract")
    @Expose
    private Integer hours_contract;
    @SerializedName("hours_excess")
    @Expose
    private Integer hours_excess;
    @SerializedName("hours_standby")
    @Expose
    private Integer hours_standby;
    @SerializedName("hours_not_standby")
    @Expose
    private Integer hours_not_standby;

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

    public Double getDistance() {
        return distance;
    }

    public String getCrane_type() {
        return crane_type;
    }

    public Integer getHours_contract() {
        return hours_contract;
    }

    public Integer getHours_excess() {
        return hours_excess;
    }

    public Integer getHours_not_standby() {
        return hours_not_standby;
    }

    public Integer getHours_standby() {
        return hours_standby;
    }

    public String getMovement_from() {
        return movement_from;
    }

    public String getMovement_to() {
        return movement_to;
    }

    public Integer getTotal_hours() {
        return total_hours;
    }

    public String getAssemble_option() {
        return assemble_option;
    }

    public String getComments() {
        return comments;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getEnd_hour() {
        return end_hour;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getStart_hour() {
        return start_hour;
    }

    public void setAssemble_option(String assemble_option) {
        this.assemble_option = assemble_option;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setCrane_type(String crane_type) {
        this.crane_type = crane_type;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setEnd_hour(String end_hour) {
        this.end_hour = end_hour;
    }

    public void setHours_contract(Integer hours_contract) {
        this.hours_contract = hours_contract;
    }

    public void setHours_excess(Integer hours_excess) {
        this.hours_excess = hours_excess;
    }

    public void setHours_not_standby(Integer hours_not_standby) {
        this.hours_not_standby = hours_not_standby;
    }

    public void setHours_standby(Integer hours_standby) {
        this.hours_standby = hours_standby;
    }

    public void setMovement_from(String movement_from) {
        this.movement_from = movement_from;
    }

    public void setMovement_to(String movement_to) {
        this.movement_to = movement_to;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setStart_hour(String start_hour) {
        this.start_hour = start_hour;
    }

    public void setTotal_hours(Integer total_hours) {
        this.total_hours = total_hours;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
}