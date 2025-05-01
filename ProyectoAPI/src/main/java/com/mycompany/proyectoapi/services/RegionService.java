
package com.mycompany.proyectoapi.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import com.mycompany.proyectoapi.models.Region;
import com.mycompany.proyectoapi.dtos.RegionDTO;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegionService {
    
    private static final Logger logger = LogManager.getLogger(RegionService.class);

    private static final String API_URL = "https://covid-19-statistics.p.rapidapi.com/regions";
    private static final String API_KEY = "2505eda46amshc60713983b5e807p1da25ajsn36febcbf4a71";

    public List<RegionDTO> obtenerRegionesDesdeAPI() {
        logger.info(">>> Iniciando solicitud a la API de regiones...");
        try {
            String jsonResponse = sendGetRequest();
            return parseRegionsFromJson(jsonResponse);
        } catch (Exception e) {
            logger.error("❌ Error al obtener regiones desde la API", e);
            return new ArrayList<>();
        }
    }

    public void saveRegionsInDb(List<RegionDTO> regionDTOs) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        for (RegionDTO dto : regionDTOs) {
            if (!regionExists(em, dto.getIso())) {
                Region region = convertirDTOaEntidad(dto);
                em.persist(region);
                logger.info("✅ Guardada región: " + dto.getName() + " (" + dto.getIso() + ")");
            } else {
                logger.info("⏩ Región ya existente, se omite: " + dto.getName() + " (" + dto.getIso() + ")");
            }
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    // ──────── Métodos privados ────────
    private String sendGetRequest() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-RapidAPI-Key", API_KEY);
        conn.setRequestProperty("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        logger.debug("Respuesta cruda de API de regiones: " + response.toString());

        return response.toString();
    }

    private List<RegionDTO> parseRegionsFromJson(String response) {
        List<RegionDTO> regionDTOs = new ArrayList<>();
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        JsonArray dataArray = jsonResponse.getAsJsonArray("data");

        for (int i = 0; i < dataArray.size(); i++) {
            JsonObject obj = dataArray.get(i).getAsJsonObject();
            String name = obj.get("name").getAsString();
            String iso = obj.get("iso").getAsString();


            if (iso.length() <= 10) {
                regionDTOs.add(new RegionDTO(name, iso));
            } else {
                logger.warn("ISO muy largo, se omite: " + iso);
            }
        }

        logger.info("✅ Regiones parseadas: " + regionDTOs.size());
        return regionDTOs;
    }

    private Region convertirDTOaEntidad(RegionDTO dto) {
        Region region = new Region();
        region.setName(dto.getName());
        region.setIso(dto.getIso());
        return region;
    }

    private boolean regionExists(EntityManager em, String iso) {
        Long count = em.createQuery(
                "SELECT COUNT(r) FROM Region r WHERE r.iso = :iso", Long.class)
                .setParameter("iso", iso)
                .getSingleResult();
        return count > 0;
    }


    
    
}
