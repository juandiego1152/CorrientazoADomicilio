package com.corrientazo.Tests;

import com.corrientazo.Control.archivoNotas;
import com.corrientazo.Modelo.modConfiguracion;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan Diego Pro
 */
public class TestlecturaDeDatosConfiguracionL {

    final static Logger LOGGER = Logger.getLogger(TestlecturaDeDatosConfiguracionL.class);

    @Test
    public void verificarDatos() {              
        try {
            //BasicConfigurator.configure();
            archivoNotas archivo = new archivoNotas();
            modConfiguracion modConfig = archivo.cargarDatos();
            LOGGER.info("Cantidad drones " + modConfig.getCantidadDrones());
            LOGGER.info("Cantidad platos " + modConfig.getCantidadPlatos());
            assertEquals(20, modConfig.getCantidadDrones());
            assertEquals(10, modConfig.getCantidadPlatos());
        } catch (InterruptedException | ExecutionException ex) {
            java.util.logging.Logger.getLogger(TestlecturaDeDatosConfiguracionL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
