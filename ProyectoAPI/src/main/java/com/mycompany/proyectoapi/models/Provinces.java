
package com.mycompany.proyectoapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "provinces")
public class Provinces {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String iso;
    private String name;
    private String province;
    private String lat;
    private String lon;

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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
