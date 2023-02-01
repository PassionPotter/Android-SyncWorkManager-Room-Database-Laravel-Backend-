package com.androidigniter.excelapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "company_table")
public class CompanyResponse {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;


    public CompanyResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
