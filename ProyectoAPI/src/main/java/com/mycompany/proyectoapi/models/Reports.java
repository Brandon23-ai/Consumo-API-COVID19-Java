/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoapi.models;


import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Reports")
public class Reports {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private Date date; 
    
    private int confirmed;
    private int deaths;
    private int recovered;
    private int confirmed_diff;
    private int deaths_diff;
    private int recovered_diff;
    
    @Column(name = "Last_Update")
    private Timestamp last_update;
    
    private int active;
    private int active_diff;
    private double fatality_rate;
    
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    
    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Region getRegion() {
        return region;
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

    public Timestamp getLast_update() {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRegion(Region region) {
        this.region = region;
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

    public void setLast_update(Timestamp last_update) {
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
