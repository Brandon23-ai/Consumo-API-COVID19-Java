/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoapi.dtos;

/**
 *
 * @author btmor
 */
   public class CovidDTOReports {
    private String date; 
    private int confirmed;
    private int deaths;
    private int recovered;
    private int confirmed_diff;
    private int deaths_diff;
    private int recovered_diff;
    private String last_update;
    private int active;
    private int active_diff;
    private double fatality_rate;
    private RegionDTO region;
    
    public RegionDTO getRegion() {
        return region;
    }

    public void setRegion(RegionDTO region) {
        this.region = region;
    }

    public CovidDTOReports() {
    }
    
    public CovidDTOReports(String date, int confirmed, int deaths, int recovered, int confirmed_diff, int deaths_diff, int recovered_diff, String last_update, int active, int active_diff, int fatality_rate) {
        this.date = date;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.confirmed_diff = confirmed_diff;
        this.deaths_diff = deaths_diff;
        this.recovered_diff = recovered_diff;
        this.last_update = last_update;
        this.active = active;
        this.active_diff = active_diff;
        this.fatality_rate = fatality_rate;
    }

    public String getDate() {
        return date;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getConfirmed_diff() {
        return confirmed_diff;
    }

    public int getDeaths_diff() {
        return deaths_diff;
    }

    public int getRecovered_diff() {
        return recovered_diff;
    }

    public String getLast_update() {
        return last_update;
    }

    public int getActive() {
        return active;
    }

    public int getActive_diff() {
        return active_diff;
    }

    public double getFatality_rate() {
        return fatality_rate;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public void setConfirmed_diff(int confirmed_diff) {
        this.confirmed_diff = confirmed_diff;
    }

    public void setDeaths_diff(int deaths_diff) {
        this.deaths_diff = deaths_diff;
    }

    public void setRecovered_diff(int recovered_diff) {
        this.recovered_diff = recovered_diff;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setActive_diff(int active_diff) {
        this.active_diff = active_diff;
    }

    public void setFatality_rate(double fatality_rate) {
        this.fatality_rate = fatality_rate;
    }
 
    
}
