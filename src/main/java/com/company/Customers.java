package com.company;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Customers {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nama")
    private String nama;
    @JsonProperty("jenisKelamin")
    private String jenisKelamin;
    @JsonProperty("created_at")
    private Object createdAt;
    @JsonProperty("updated_at")
    private Object updatedAt;

    public Customers(Integer id, String nama, String jenisKelamin, Object createdAt, Object updatedAt) {
        this.id = id;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }
}
