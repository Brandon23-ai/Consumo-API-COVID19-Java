
package com.mycompany.proyectoapi.services;

import com.mycompany.proyectoapi.models.*;
import com.mycompany.proyectoapi.dtos.*;

import com.google.gson.*;
import jakarta.persistence.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;
import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ReportsService {
    
    private static final Logger logger = LogManager.getLogger(ReportsService.class);
    
    private static final String API_URL = "https://covid-19-statistics.p.rapidapi.com/reports?iso=GTM";
    private static final String API_KEY = "2505eda46amshc60713983b5e807p1da25ajsn36febcbf4a71";
    
    public List<CovidDTOReports> getReportsFromAPI(String iso, String fecha) {
        List<CovidDTOReports> listaReportes = new ArrayList<>();

        try {
            // Construccoin de la URL con la fecha específica
            String urlWithParams = "https://covid-19-statistics.p.rapidapi.com/reports?iso=" + iso + "&date=" + fecha;
            URL url = new URL(urlWithParams);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            conn.setRequestProperty("X-RapidAPI-Key", API_KEY);
            conn.setRequestProperty("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com");
            conn.setRequestProperty("x-rapidapi-region", "AWS - us-east-1");  // Esta es la cabecera que mencionaste
            conn.setRequestProperty("x-rapidapi-request-id", "ccc562a17a4bc731e57f29c5422b4418af49ec9d6f821e58a914ed4dfc2df666");  // Incluye el ID de la solicitud si es necesario
            conn.setRequestProperty("x-rapidapi-version", "1.2.8");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            logger.info("Reportes obtenidos: " + response);

            JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonArray dataArray = json.getAsJsonArray("data");

            // Parsear cada reporte
            for (JsonElement element : dataArray) {
                JsonObject obj = element.getAsJsonObject();

                // Mapeamos Region
                JsonObject regionJson = obj.getAsJsonObject("region");
                RegionDTO region = new RegionDTO();
                region.setIso(regionJson.get("iso").getAsString());
                region.setName(regionJson.get("name").getAsString());
                region.setProvince(regionJson.get("province").getAsString());
                region.setLat(regionJson.get("lat").getAsString());
                region.setLon(regionJson.get("long").getAsString());

                // Mapeamos Reporte
                CovidDTOReports reporte = new CovidDTOReports();
                reporte.setDate(obj.get("date").getAsString());
                reporte.setConfirmed(obj.get("confirmed").getAsInt());
                reporte.setDeaths(obj.get("deaths").getAsInt());
                reporte.setRecovered(obj.get("recovered").getAsInt());
                reporte.setConfirmed_diff(obj.get("confirmed_diff").getAsInt());
                reporte.setDeaths_diff(obj.get("deaths_diff").getAsInt());
                reporte.setRecovered_diff(obj.get("recovered_diff").getAsInt());
                reporte.setLast_update(obj.get("last_update").getAsString());
                reporte.setActive(obj.get("active").getAsInt());
                reporte.setActive_diff(obj.get("active_diff").getAsInt());
                reporte.setFatality_rate(obj.get("fatality_rate").getAsDouble());
                reporte.setRegion(region);

                listaReportes.add(reporte);
            }

        } catch (Exception e) {
            logger.error("Error al obtener el reporte: " + e);
            e.printStackTrace();
        }

        return listaReportes;
    }
    
    public void saveReportsToDb(List<CovidDTOReports> reportesDTO) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        for (CovidDTOReports dto : reportesDTO) {
            
            // Buscar la región por su código ISO
            Region region = em.createQuery(
            "SELECT r FROM Region r WHERE r.iso = :iso", Region.class)
            .setParameter("iso", dto.getRegion().getIso())
            .getResultStream()
            .findFirst()
            .orElse(null);
            
            if (region == null) {
                region = new Region();
                region.setIso(dto.getRegion().getIso());
                region.setName(dto.getRegion().getName());
                region.setProvince(dto.getRegion().getProvince());
                region.setLon(dto.getRegion().getLat());
                region.setLat(dto.getRegion().getLon());
                em.persist(region);
            }

            Reports reporte = convertDtoToEntity(dto, region);
            em.persist(reporte);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    
    private Reports convertDtoToEntity(CovidDTOReports dto, Region region) {
        Reports reporte = new Reports();
        reporte.setDate(Date.valueOf(dto.getDate()));
        reporte.setConfirmed(dto.getConfirmed());
        reporte.setDeaths(dto.getDeaths());
        reporte.setRecovered(dto.getRecovered());
        reporte.setConfirmed_diff(dto.getConfirmed_diff());
        reporte.setDeaths_diff(dto.getDeaths_diff());
        reporte.setRecovered_diff(dto.getRecovered_diff());

        String raw = dto.getLast_update().replace("T", " ");
        if (raw.length() > 19) {
            raw = raw.substring(0, 19);
        }
        reporte.setLast_update(Timestamp.valueOf(raw));

        reporte.setActive(dto.getActive());
        reporte.setActive_diff(dto.getActive_diff());
        reporte.setFatality_rate(dto.getFatality_rate());
        reporte.setRegion(region);
        return reporte;
    }

    
    

    
}
