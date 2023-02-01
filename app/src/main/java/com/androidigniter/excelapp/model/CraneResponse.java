package com.androidigniter.excelapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "crane_table")
public class CraneResponse {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("resource")
    @Expose
    private String resource;
    @SerializedName("crane_type")
    @Expose
    private String crane_type;
    @SerializedName("mob_demob")
    @Expose
    private String mob_demob;
    @SerializedName("arrival_date")
    @Expose
    private String arrival_date;
    @SerializedName("ready")
    @Expose
    private String ready;
    @SerializedName("demob")
    @Expose
    private String demob;
    @SerializedName("stringID")
    @Expose
    private String stringID;

    @SerializedName("is_sync")
    @Expose
    private boolean is_sync;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public String getCrane_type() {
        return crane_type;
    }

    public String getDemob() {
        return demob;
    }

    public String getMob_demob() {
        return mob_demob;
    }

    public String getResource() {
        return resource;
    }

    public String getReady() {
        return ready;
    }
    public void setIs_sync(boolean is_sync) {
        this.is_sync = is_sync;
    }
    public boolean getIs_sync() {
        return this.is_sync;
    }

    public void setCrane_type(String crane_type) {
        this.crane_type = crane_type;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public void setDemob(String demob) {
        this.demob = demob;
    }

    public void setMob_demob(String mob_demob) {
        this.mob_demob = mob_demob;
    }

    public void setReady(String ready) {
        this.ready = ready;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getStringID() {
        return stringID;
    }

    public void setStringID(String stringID) {
        this.stringID = stringID;
    }
}