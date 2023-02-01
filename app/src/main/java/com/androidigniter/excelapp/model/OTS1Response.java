package com.androidigniter.excelapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ots1_table")
public class OTS1Response {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("phaseID")
    @Expose
    private Integer phaseID;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("main_lead_time")
    @Expose
    private Double main_lead_time;
    @SerializedName("tn_lead_time")
    @Expose
    private Double tn_lead_time;
    @SerializedName("auxiliary_lead_time")
    @Expose
    private Double auxiliary_lead_time;
    @SerializedName("roadmap_lead_time")
    @Expose
    private Double roadmap_lead_time;
    @SerializedName("n_technician")
    @Expose
    private Integer n_technician;
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


    public void setIs_sync(boolean is_sync) {
        this.is_sync = is_sync;
    }
    public boolean getIs_sync() {
        return this.is_sync;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAuxiliary_lead_time() {
        return auxiliary_lead_time;
    }

    public Double getMain_lead_time() {
        return main_lead_time;
    }

    public Integer getN_technician() {
        return n_technician;
    }

    public Double getRoadmap_lead_time() {
        return roadmap_lead_time;
    }

    public Double getTn_lead_time() {
        return tn_lead_time;
    }

    public Integer getPhaseID() {
        return phaseID;
    }

    public String getCode() {
        return code;
    }

    public void setAuxiliary_lead_time(Double auxiliary_lead_time) {
        this.auxiliary_lead_time = auxiliary_lead_time;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMain_lead_time(Double main_lead_time) {
        this.main_lead_time = main_lead_time;
    }

    public void setN_technician(Integer n_technician) {
        this.n_technician = n_technician;
    }

    public void setPhaseID(Integer phaseID) {
        this.phaseID = phaseID;
    }

    public void setRoadmap_lead_time(Double roadmap_lead_time) {
        this.roadmap_lead_time = roadmap_lead_time;
    }

    public void setTn_lead_time(Double tn_lead_time) {
        this.tn_lead_time = tn_lead_time;
    }

    public String getStringID() {
        return stringID;
    }

    public void setStringID(String stringID) {
        this.stringID = stringID;
    }
}