

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
        try {
            String responseJson = sendGetRequest(iso);
            return parseProvincesFromJson(responseJson);
        } catch (Exception e) {
            handleApiError(e);
            return new ArrayList<>();
        }
    }

    public void saveProvincesInDb(List<ProvincesDTO> provincesDTOs) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        for (ProvincesDTO dto : provincesDTOs) {
            if (!provinceExists(em, dto.getIso(), dto.getProvince())) {
                Provinces entidad = convertDtoToEntity(dto);
                em.persist(entidad);
                logger.info("Provincia guardada : " + entidad.getName() + " - " + entidad.getProvince());
            } else {
                logger.info("Provincia ya existente: " + dto.getProvince() + " (ISO: " + dto.getIso() + ")");
            }
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    // ──────── Métodos privados ────────
    private String sendGetRequest(String iso) throws IOException {
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
        logger.debug(response.toString());
        return response.toString();
    }
    
    //Metodo auxiliar para evitar respuesta Null 
    private String getAsStringSafe(JsonObject obj, String memberName) {
        JsonElement el = obj.get(memberName);
        return (el != null && !el.isJsonNull()) ? el.getAsString() : "";
    }


    private List<ProvincesDTO> parseProvincesFromJson(String json) {
        List<ProvincesDTO> provinces = new ArrayList<>();
        JsonObject jsonResponse = new Gson().fromJson(json, JsonObject.class);
        JsonArray data = jsonResponse.getAsJsonArray("data");

        for (JsonElement elem : data) {
            JsonObject obj = elem.getAsJsonObject();
            ProvincesDTO dto = new ProvincesDTO();

            dto.setIso(getAsStringSafe(obj, "iso"));
            dto.setName(getAsStringSafe(obj, "name"));
            dto.setProvince(getAsStringSafe(obj, "province"));
            dto.setLat(getAsStringSafe(obj, "lat"));
            dto.setLon(getAsStringSafe(obj, "long"));

            provinces.add(dto);
        }

        logger.info("Total provincias parseadas: " + provinces.size());
        return provinces;
    }


    private void handleApiError(Exception e) {
        logger.error("❌ Error al obtener provincias: ", e);
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

    private boolean provinceExists(EntityManager em, String iso, String provinceName) {
        Long count = em.createQuery(
                "SELECT COUNT(p) FROM Provinces p WHERE p.iso = :iso AND p.province = :province", Long.class)
                .setParameter("iso", iso)
                .setParameter("province", provinceName)
                .getSingleResult();
        return count > 0;
    }
    


 
}
