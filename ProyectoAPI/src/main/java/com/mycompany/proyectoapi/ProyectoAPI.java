

package com.mycompany.proyectoapi;

import com.mycompany.proyectoapi.services.*;
import com.mycompany.proyectoapi.threads.ApiDataLoader;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProyectoAPI {

    private static final Logger logger = LogManager.getLogger(ProyectoAPI.class);

    public static void main(String[] args) {

        logger.info("Iniciando proyecto. . . \n Cargando datos. . .");

        // Crear EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        // Instanciar los servicios con el EntityManagerFactory
        RegionService regionService = new RegionService();
        ProvincesService provincesService = new ProvincesService();
        ReportsService reportsService = new ReportsService();
        RequestedDataService requestedDataService = new RequestedDataService(emf);

        // Pasar servicios y EntityManagerFactory al hilo de carga
        ApiDataLoader loader = new ApiDataLoader(
                regionService,
                provincesService,
                reportsService,
                requestedDataService,
                emf
        );

        loader.start();
    }
}

