package com.androidigniter.excelapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "project_table")
public class ProjectResponse {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("project_code")
    @Expose
    private String project_code;
    @SerializedName("windfarm_name")
    @Expose
    private String windfarm_name;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("customer")
    @Expose
    private String customer;
    @SerializedName("wtg_power")
    @Expose
    private String wtg_power;
    @SerializedName("crane_company")
    @Expose
    private String crane_company;
    @SerializedName("assembly_subcontractor")
    @Expose
    private String assembly_subcontractor;
    @SerializedName("n_wtgs")
    @Expose
    private Integer n_wtgs;
    @SerializedName("wtg_type")
    @Expose
    private String wtg_type;
    @SerializedName("blade_length")
    @Expose
    private Double blade_length;
    @SerializedName("blade_manufacture")
    @Expose
    private String blade_manufacture;
    @SerializedName("tower_height")
    @Expose
    private Double tower_height;
    @SerializedName("n_tower_section")
    @Expose
    private Integer n_tower_section;
    @SerializedName("pre_assembly_section")
    @Expose
    private Integer pre_assembly_section;
    @SerializedName("n_wtg_special_nacelle_beacon")
    @Expose
    private Integer n_wtg_special_nacelle_beacon;
    @SerializedName("n_wtg_special_tower_beacon")
    @Expose
    private Integer n_wtg_special_tower_beacon;

    @SerializedName("is_sync")
    @Expose
    private boolean is_sync;

    @SerializedName("stringID")
    @Expose
    private String stringID;

    public void setIs_sync(boolean is_sync) {
        this.is_sync = is_sync;
    }
    public boolean getIs_sync() {
        return this.is_sync;
    }


    public Integer getId() {
        return id;
    }

    public Double getBlade_length() {
        return blade_length;
    }

    public Integer getN_wtgs() {
        return n_wtgs;
    }

    public Integer getN_wtg_special_tower_beacon() {
        return n_wtg_special_tower_beacon;
    }

    public String getAssembly_subcontractor() {
        return assembly_subcontractor;
    }

    public Integer getPre_assembly_section() {
        return pre_assembly_section;
    }

    public String getCountry() {
        return country;
    }

    public String getCrane_company() {
        return crane_company;
    }

    public String getCustomer() {
        return customer;
    }

    public Double getTower_height() {
        return tower_height;
    }

    public String getBlade_manufacture() {
        return blade_manufacture;
    }

    public Integer getN_tower_section() {
        return n_tower_section;
    }

    public String getProject_code() {
        return project_code;
    }

    public Integer getN_wtg_special_nacelle_beacon() {
        return n_wtg_special_nacelle_beacon;
    }

    public String getWindfarm_name() {
        return windfarm_name;
    }

    public String getWtg_power() {
        return wtg_power;
    }

    public String getWtg_type() {
        return wtg_type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAssembly_subcontractor(String assembly_subcontractor) {
        this.assembly_subcontractor = assembly_subcontractor;
    }

    public void setBlade_length(Double blade_length) {
        this.blade_length = blade_length;
    }

    public void setBlade_manufacture(String blade_manufacture) {
        this.blade_manufacture = blade_manufacture;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCrane_company(String crane_company) {
        this.crane_company = crane_company;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setN_tower_section(Integer n_tower_section) {
        this.n_tower_section = n_tower_section;
    }

    public void setN_wtg_special_nacelle_beacon(Integer n_wtg_special_nacelle_beacon) {
        this.n_wtg_special_nacelle_beacon = n_wtg_special_nacelle_beacon;
    }

    public void setN_wtg_special_tower_beacon(Integer n_wtg_special_tower_beacon) {
        this.n_wtg_special_tower_beacon = n_wtg_special_tower_beacon;
    }

    public void setN_wtgs(Integer n_wtgs) {
        this.n_wtgs = n_wtgs;
    }

    public void setPre_assembly_section(Integer pre_assembly_section) {
        this.pre_assembly_section = pre_assembly_section;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public void setTower_height(Double tower_height) {
        this.tower_height = tower_height;
    }

    public void setWindfarm_name(String windfarm_name) {
        this.windfarm_name = windfarm_name;
    }

    public void setWtg_power(String wtg_power) {
        this.wtg_power = wtg_power;
    }

    public void setWtg_type(String wtg_type) {
        this.wtg_type = wtg_type;
    }

    public void setStringID(String stringID) {
        this.stringID = stringID;
    }
    public String getStringID() { return this.stringID; }
}
