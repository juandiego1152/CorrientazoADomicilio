package com.corrientazo.Control;

import com.corrientazo.Modelo.modConfiguracion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import org.apache.log4j.Logger;

public class archivoNotas {

    final static Logger LOGGER = Logger.getLogger(archivoNotas.class);
    
    public modConfiguracion cargarDatos() {   
        String cantidadDrones;
        String cantidadPlatos;                  
       
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
        modConfiguracion modConfig = new modConfiguracion();           
        modConfig.setCantidadDrones(Integer.parseInt(cantidadDrones));
        modConfig.setCantidadPlatos(Integer.parseInt(cantidadPlatos));
        return modConfig;
    }

    public String leerArchivoUnicaLinea(String archivo) {
        try {
            String cadena;
            FileReader f;
            f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                return cadena;
            }           
            b.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return " ";
    }

    

}
