

package com.mycompany.proyectoapi.threads;


import com.mycompany.proyectoapi.dtos.CovidDTOReports;
import com.mycompany.proyectoapi.dtos.ProvincesDTO;
import com.mycompany.proyectoapi.dtos.RegionDTO;
import com.mycompany.proyectoapi.services.*;
import jakarta.persistence.EntityManagerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ApiDataLoader extends Thread {

    private static final Logger logger = LogManager.getLogger(ApiDataLoader.class);

    private final RegionService regionsService;
    private final ProvincesService provincesService;
    private final ReportsService reportService;
    private final RequestedDataService requestedDataService;
    private final EntityManagerFactory emf;

    public ApiDataLoader(
            RegionService regionsService,
            ProvincesService provincesService,
            ReportsService reportService,
            RequestedDataService requestedDataService,
            EntityManagerFactory emf
    ) {
        this.regionsService = regionsService;
        this.provincesService = provincesService;
        this.reportService = reportService;
        this.requestedDataService = requestedDataService;
        this.emf = emf;
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

            // 4. Registro de reporte consultado.
            requestedDataService.save(iso, date);
            
            // 5. Consultar datos y agrupar
            reportService.mostrarReportesAgrupadosPorRegion(iso, date);

            logger.info("Datos cargados exitosamente.");
        } catch (Exception e) {
            logger.error("Error al ejecutar el hilo de carga de datos:", e);
        } finally {
            if (emf != null && emf.isOpen()) {
                emf.close();
                logger.info("EntityManagerFactory cerrado correctamente.");
            }
        }
    }
}
