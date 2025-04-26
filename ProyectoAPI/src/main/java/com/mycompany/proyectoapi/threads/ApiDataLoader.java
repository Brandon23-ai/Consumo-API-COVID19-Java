/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoapi.threads;

import com.mycompany.proyectoapi.threads.AppConfig;
import com.mycompany.proyectoapi.services.RegionService;
import com.mycompany.proyectoapi.services.ProvincesService;
import com.mycompany.proyectoapi.services.ReportsService;

import com.mycompany.proyectoapi.dtos.RegionDTO;
import com.mycompany.proyectoapi.dtos.ProvincesDTO;
import com.mycompany.proyectoapi.dtos.CovidDTOReports;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ApiDataLoader extends Thread {
    
    private static final Logger logger = LogManager.getLogger(ApiDataLoader.class);
    
    private final RegionService regionsService;
    private final ProvincesService provincesService;
    private final ReportsService reportService;

    public ApiDataLoader(com.mycompany.proyectoapi.services.RegionService regionsService, com.mycompany.proyectoapi.services.ProvincesService provincesService, com.mycompany.proyectoapi.services.ReportsService reportService) {
        this.regionsService = regionsService;
        this.provincesService = provincesService;
        this.reportService = reportService;
    }
    
    @Override
    public void run() {
        try {
            long delay = Long.parseLong(AppConfig.get("app.api.delay"));
            String iso = AppConfig.get("app.api.iso");
            String date = AppConfig.get("app.api.report-date");

            logger.info("⏳ Esperando " + delay + " ms para iniciar carga de datos...");
            Thread.sleep(delay);

            logger.info("Iniciando consumo de datos...");

            // 1. Regiones
            List<RegionDTO> regiones = regionsService.obtenerRegionesDesdeAPI();
            regionsService.saveRegionsInDb(regiones);

            // 2. Provincias
            List<ProvincesDTO> provincias = provincesService.getProvincesFromAPI(iso);
            provincesService.saveProvincesInDb(provincias);

            // 3. Reportes
            List<CovidDTOReports> reportes = reportService.getReportsFromAPI(iso, date);
            reportService.saveReportsToDb(reportes);

            logger.info("Datos cargados exitosamente.");

        } catch (Exception e) {
            logger.error("❌ Error al ejecutar el hilo de carga de datos:");
            e.printStackTrace();
        }
    }
    
    
    
    
}
