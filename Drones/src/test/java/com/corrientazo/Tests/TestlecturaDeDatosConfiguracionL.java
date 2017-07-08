package com.corrientazo.Tests;

import com.corrientazo.Control.archivoNotas;
import com.corrientazo.Modelo.modConfiguracion;
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
        //BasicConfigurator.configure();
        archivoNotas archivo = new archivoNotas();       
        modConfiguracion modConfig = archivo.cargarDatos();   
        LOGGER.info("Cantidad drones " + modConfig.getCantidadDrones());
        LOGGER.info("Cantidad platos " + modConfig.getCantidadPlatos());
        assertEquals(20, modConfig.getCantidadDrones());
        assertEquals(3, modConfig.getCantidadPlatos());
    }
   
}
