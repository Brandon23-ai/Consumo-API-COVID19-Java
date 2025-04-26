

package com.mycompany.proyectoapi.services;

import com.mycompany.proyectoapi.dtos.*;
import com.mycompany.proyectoapi.models.*;

import com.google.gson.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProvincesService {
    
    private static final Logger logger = LogManager.getLogger(ProvincesService.class);

    private static final String API_URL = "https://covid-19-statistics.p.rapidapi.com/provinces";
    private static final String API_KEY = "2505eda46amshc60713983b5e807p1da25ajsn36febcbf4a71";
    
    public List<ProvincesDTO> getProvincesFromAPI(String iso) {
    logger.info(">>> Entrando al método getProvincesFromAPI()");
    List<ProvincesDTO> provinces = new ArrayList<>();

    try {
        String urlWithParams = API_URL + "?iso=" + iso;
        URL url = new URL(urlWithParams);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-RapidAPI-Key", API_KEY);
        conn.setRequestProperty("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = in.readLine()) != null) {
            response.append(line);
        }

        in.close();
        logger.info("Respuesta completa de la API:");
        System.out.println(response.toString());

        JsonObject jsonResponse = new Gson().fromJson(response.toString(), JsonObject.class);
        JsonArray data = jsonResponse.getAsJsonArray("data");

        for (JsonElement elem : data) {
            JsonObject obj = elem.getAsJsonObject();

            ProvincesDTO dto = new ProvincesDTO();
            dto.setIso(obj.get("iso").getAsString());
            dto.setName(obj.get("name").getAsString());
            dto.setProvince(obj.get("province").getAsString());
            dto.setLat(obj.get("lat").getAsString());
            dto.setLon(obj.get("long").getAsString());

            provinces.add(dto);
        }

        logger.info("Total provincias obtenidas: " + provinces.size());

    } catch (Exception e) {
        logger.error("Error al obtener provincias: "+ e);
    }

    return provinces;
}

    
    public void saveProvincesInDb(List<ProvincesDTO> provincesDTOs) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();

        for (ProvincesDTO dto : provincesDTOs) {
            Provinces entidad = convertDtoToEntity(dto);
            em.persist(entidad);
            logger.info("Guardado: " + entidad.getName() + " - " + entidad.getProvince());
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    
    private Provinces convertDtoToEntity(ProvincesDTO dto) {
        Provinces province = new Provinces();
        province.setIso(dto.getIso());
        province.setName(dto.getName());
        province.setProvince(dto.getProvince());
        province.setLat(dto.getLat());
        province.setLon(dto.getLon());
        return province;
}


   
    
}
