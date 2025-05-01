/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoapi.services;

import com.mycompany.proyectoapi.models.*;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class RequestedDataService {

    private EntityManagerFactory emf;

    public RequestedDataService(EntityManagerFactory emf) {
        this.emf = emf;

    }

    public void save(String iso, String date) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            RequestedData data = new RequestedData();
            data.setIso(iso);
            data.setReportDate(LocalDate.parse(date));
            data.setRequestDateTime(LocalDateTime.now());
            em.persist(data);

            em.getTransaction().commit();
            em.close();

        }

    }

}
