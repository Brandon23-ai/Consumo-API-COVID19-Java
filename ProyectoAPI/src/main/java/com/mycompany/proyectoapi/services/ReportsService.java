
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
import java.time.LocalDate;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ReportsService {
    
    private static final Logger logger = LogManager.getLogger(ReportsService.class);
    
    private static final String API_URL = "https://covid-19-statistics.p.rapidapi.com/reports?iso=GTM";
    private static final String API_KEY = "2505eda46amshc60713983b5e807p1da25ajsn36febcbf4a71";
    
    public List<CovidDTOReports> getReportsFromAPI(String iso, String fecha) {
        List<CovidDTOReports> listaReportes = new ArrayList<>();
        try {
            String response = sendGetRequest(iso, fecha);
            listaReportes = parseReportsResponse(response);
        } catch (Exception e) {
            handleApiError(e);
        }
        return listaReportes;
    }

    private String sendGetRequest(String iso, String fecha) throws IOException {
        String urlWithParams = "https://covid-19-statistics.p.rapidapi.com/reports?iso=" + iso + "&date=" + fecha;
        URL url = new URL(urlWithParams);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        conn.setRequestProperty("X-RapidAPI-Key", API_KEY);
        conn.setRequestProperty("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com");
        conn.setRequestProperty("x-rapidapi-region", "AWS - us-east-1");
        conn.setRequestProperty("x-rapidapi-request-id", "ccc562a17a4bc731e57f29c5422b4418af49ec9d6f821e58a914ed4dfc2df666");
        conn.setRequestProperty("x-rapidapi-version", "1.2.8");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        logger.info("Reportes obtenidos: " + response);
        return response.toString();
    }

    private List<CovidDTOReports> parseReportsResponse(String response) {
        List<CovidDTOReports> reportes = new ArrayList<>();
        JsonObject json = JsonParser.parseString(response).getAsJsonObject();
        JsonArray dataArray = json.getAsJsonArray("data");

        for (JsonElement element : dataArray) {
            JsonObject obj = element.getAsJsonObject();

            RegionDTO region = parseRegion(obj.getAsJsonObject("region"));
            CovidDTOReports reporte = parseReport(obj, region);

            reportes.add(reporte);
        }

        return reportes;
    }

    private RegionDTO parseRegion(JsonObject regionJson) {
        RegionDTO region = new RegionDTO();
        region.setIso(regionJson.get("iso").getAsString());
        region.setName(regionJson.get("name").getAsString());
        region.setProvince(regionJson.get("province").getAsString());
        region.setLat(regionJson.get("lat").getAsString());
        region.setLon(regionJson.get("long").getAsString());
        return region;
    }

    private CovidDTOReports parseReport(JsonObject obj, RegionDTO region) {
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
        return reporte;
    }

    private void handleApiError(Exception e) {
        logger.error("Error al obtener el reporte: ", e);
        e.printStackTrace();
    }

    
    public void saveReportsToDb(List<CovidDTOReports> reportesDTO) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        for (CovidDTOReports dto : reportesDTO) {
            processReport(dto, em);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    private void processReport(CovidDTOReports dto, EntityManager em) {
        Region region = findOrCreateRegion(dto, em);
        if (!reportExists(region, dto.getDate(), em)) {
            Reports reporte = convertDtoToEntity(dto, region);
            em.persist(reporte);
            logger.info("Reporte guardado para: " + region.getIso() + " - " + region.getProvince());
        } else {
            logDuplicate(dto);
        }
    }

    private Region findOrCreateRegion(CovidDTOReports dto, EntityManager em) {
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
            region.setLon(dto.getRegion().getLon());
            region.setLat(dto.getRegion().getLat());
            em.persist(region);
        }

        return region;
    }

    private boolean reportExists(Region region, String dateStr, EntityManager em) {
        Date date = Date.valueOf(dateStr);
        Long count = em.createQuery(
                "SELECT COUNT(r) FROM Reports r WHERE r.region = :region AND r.date = :date", Long.class)
                .setParameter("region", region)
                .setParameter("date", date)
                .getSingleResult();
        return count > 0;
    }


    private void logDuplicate(CovidDTOReports dto) {
        System.out.println("Reporte duplicado encontrado para ISO: " + dto.getRegion().getIso()
                + " y fecha: " + dto.getDate() + ", no se guarda.");
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
    
    //metodos para consulta de datos
    
    public void mostrarReportesAgrupadosPorRegion(String iso, String fecha) {
        try {
            Date fechaConvertida = Date.valueOf(fecha);
            List<Reports> reportes = obtenerReportesPorIsoYFecha(iso, fechaConvertida);
            Map<Integer, Reports> reportesUnicos = agruparReportesPorRegion(reportes);
            imprimirReportesAgrupados(reportesUnicos);
        } catch (Exception e) {
            logger.error("Error al mostrar reportes agrupados por región", e);
        }
    }

    private List<Reports> obtenerReportesPorIsoYFecha(String iso, Date fecha) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        List<Reports> reportes = new ArrayList<>();

        try {
            reportes = em.createQuery(
                    "SELECT r FROM Reports r WHERE r.region.iso = :iso AND r.date = :fecha ORDER BY r.region.id",
                    Reports.class)
                    .setParameter("iso", iso)
                    .setParameter("fecha", fecha)
                    .getResultList();
        } finally {
            em.close();
            emf.close();
        }

        return reportes;
    }

    private Map<Integer, Reports> agruparReportesPorRegion(List<Reports> reportes) {
        Map<Integer, Reports> unicos = new TreeMap<>();
        for (Reports r : reportes) {
            int regionId = r.getRegion().getId();
            unicos.putIfAbsent(regionId, r); // evita duplicados por región
        }
        return unicos;
    }

    private void imprimirReportesAgrupados(Map<Integer, Reports> reportesUnicos) {
        System.out.println("Reportes agrupados por region (unicos por region):");
        for (Reports r : reportesUnicos.values()) {
            System.out.println("Region ID: " + r.getRegion().getId()
                    + ", Nombre: " + r.getRegion().getName()
                    + ", Confirmados: " + r.getConfirmed()
                    + ", Muertes: " + r.getDeaths()
                    + ", Fecha: " + r.getDate());
        }
    }

    
    
}
