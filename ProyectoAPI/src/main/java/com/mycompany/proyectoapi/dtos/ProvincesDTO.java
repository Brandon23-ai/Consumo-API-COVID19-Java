/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoapi.dtos;

/**
 *
 * @author btmor
 */
public class ProvincesDTO {
    private String iso;
    private String name;
    private String province;
    private String lat;
    private String lon;
    
    public ProvincesDTO() {
        
    }

    public ProvincesDTO(String iso, String name, String province, String lat, String lon) {
        this.iso = iso;
        this.name = name;
        this.province = province;
        this.lat = lat;
        this.lon = lon;
    }

    public String getIso() {
        return iso;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
    
    
    
    
    
}
