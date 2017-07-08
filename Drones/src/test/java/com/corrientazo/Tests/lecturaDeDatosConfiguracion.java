/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corrientazo.Tests;

import com.corrientazo.Control.archivoNotas;
import java.awt.List;
import java.util.ArrayList;
import java.util.Optional;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan Diego Pro
 */
public class lecturaDeDatosConfiguracion {

    final static Logger LOGGER = Logger.getLogger(lecturaDeDatosConfiguracion.class);

    public lecturaDeDatosConfiguracion() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void verificarDatos() {
        
        String cantidadDrones;
        String cantidadPlatos;   
                
        BasicConfigurator.configure();
        archivoNotas archivo = new archivoNotas();
        String linea = archivo.leerArchivoUnicaLinea("config.txt");
        String[] config;        
        Optional<String> lineaOptional = Optional.ofNullable(linea);        
        if (lineaOptional.isPresent() && linea.contains(";") ) {
            config = linea.split(";");       
            cantidadDrones = config[0];
            cantidadPlatos = config[1];
        } else {
            config = null;
            cantidadDrones = null;
            cantidadPlatos = null;
        }

        Optional<String> cantidadDronesOptional = Optional.ofNullable(cantidadDrones);    
        String cantidadPlatosOptional = Optional.ofNullable(cantidadPlatos).orElse("3");           
        if (cantidadDronesOptional.isPresent()) {
            if (cantidadDronesOptional.get().equals(" ")) {
                cantidadDrones = "1";
            } else {
                cantidadDrones = cantidadDronesOptional.get();
            }
        }else{
            cantidadDrones = "1";
        }
        if (cantidadPlatosOptional.equals("")) {
            cantidadPlatos = "3";
        } else {
            cantidadPlatos = cantidadPlatosOptional;
        }

        LOGGER.info("Cantidad de drones: " + cantidadDrones);
        LOGGER.info("Cantidad de platos: " + cantidadPlatos);
        assertEquals("1", cantidadDrones);
        assertEquals("3", cantidadPlatos);

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
