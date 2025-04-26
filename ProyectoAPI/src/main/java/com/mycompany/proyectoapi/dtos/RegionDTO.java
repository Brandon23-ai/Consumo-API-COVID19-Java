

package com.mycompany.proyectoapi.dtos;


public class RegionDTO {
    private String name;
    private String iso;
    private String province;
    private String lat;
    private String lon;
    
    public RegionDTO(){
    }
    
    public RegionDTO(String name, String iso){
        this.name = name;
        this.iso = iso;
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
