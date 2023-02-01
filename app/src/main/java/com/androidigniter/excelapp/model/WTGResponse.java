package com.androidigniter.excelapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "wtg_table")
public class WTGResponse {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("wtg_name")
    @Expose
    private String wtg_name;
    @SerializedName("wtg_type")
    @Expose
    private String wtg_type;
    @SerializedName("assembly_secuence")
    @Expose
    private String assembly_secuence;

    @SerializedName("stringID")
    @Expose
    private String stringID;

    @SerializedName("special_nacelle_beacon")
    @Expose
    private Integer special_nacelle_beacon;
    @SerializedName("special_tower_beacon")
    @Expose
    private Integer special_tower_beacon;


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

    public String getWtg_type() {
        return wtg_type;
    }

    public String getAssembly_secuence() {
        return assembly_secuence;
    }

    public String getWtg_name() {
        return wtg_name;
    }

    public void setWtg_type(String wtg_type) {
        this.wtg_type = wtg_type;
    }

    public void setAssembly_secuence(String assembly_secuence) {
        this.assembly_secuence = assembly_secuence;
    }

    public void setSpecial_nacelle_beacon(Integer special_nacelle_beacon) {
        this.special_nacelle_beacon = special_nacelle_beacon;
    }
    public Integer getSpecial_nacelle_beacon() {
        return this.special_nacelle_beacon;
    }

    public void setSpecial_tower_beacon(Integer special_tower_beacon) {
        this.special_tower_beacon = special_tower_beacon;
    }
    public Integer getSpecial_tower_beacon() {
        return this.special_tower_beacon;
    }

    public void setWtg_name(String wtg_name) {
        this.wtg_name = wtg_name;
    }

    public String getStringID() {
        return stringID;
    }

    public void setStringID(String stringID) {
        this.stringID = stringID;
    }
}