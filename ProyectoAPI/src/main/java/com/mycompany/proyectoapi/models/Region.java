
package com.mycompany.proyectoapi.models;

import jakarta.persistence.*;


@Entity
@Table(name = "region")
public class Region {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    
    private String name;
    private String iso;
    
    private String province;
    private String lat;
    private String lon;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIso() {
        return iso;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIso(String iso) {
        this.iso = iso;
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
