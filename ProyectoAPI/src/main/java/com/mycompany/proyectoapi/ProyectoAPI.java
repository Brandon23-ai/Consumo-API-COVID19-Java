

package com.mycompany.proyectoapi;

import com.mycompany.proyectoapi.services.*;
import com.mycompany.proyectoapi.threads.ApiDataLoader;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProyectoAPI {
    
    private static final Logger logger = LogManager.getLogger(ProyectoAPI.class);

    public static void main(String[] args) {
        
        logger.info("Iniciando proyecto. . . \n Cargando datos. . .");
        ApiDataLoader loader = new ApiDataLoader(
                new RegionService(),
                new ProvincesService(),
                new ReportsService()
        );
        loader.start();
    }
}
