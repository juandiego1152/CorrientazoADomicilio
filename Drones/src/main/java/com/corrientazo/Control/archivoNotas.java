package com.corrientazo.Control;

import com.corrientazo.Modelo.modConfiguracion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Optional;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class archivoNotas {

    final static Logger LOGGER = Logger.getLogger(archivoNotas.class);

    public modConfiguracion cargarDatos() {
        String cantidadDrones;
        String cantidadPlatos;

        archivoNotas archivo = new archivoNotas();
        String linea = archivo.leerArchivoUnicaLinea("Config/config.txt");
        String[] config;
        Optional<String> lineaOptional = Optional.ofNullable(linea);
        if (lineaOptional.isPresent() && linea.contains(";")) {
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
        } else {
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
            LOGGER.error("Error : " + ex.getMessage());
        }
        return " ";
    }

    public static void editarFichero(String ruta, String dato) {
        try {
            File Ffichero = new File(ruta);
            //Si no Existe el fichero lo crea
            if (!Ffichero.exists()) {
                Ffichero.createNewFile();
            }
            /*Abre un Flujo de escritura,sobre el fichero con codificacion utf-8. 
             *Además  en el pedazo de sentencia "FileOutputStream(Ffichero,true)",
             *true es por si existe el fichero seguir añadiendo texto y no borrar lo que tenia*/
            BufferedWriter Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Ffichero, false), "utf-8"));
            /*Escribe en el fichero la cadena que recibe la función. 
             *el string "\r\n" significa salto de linea*/
            Fescribe.write(dato);
            //Cierra el flujo de escritura
            Fescribe.close();
        } catch (Exception ex) {
            LOGGER.error("Error : " + ex.getMessage());
        }
    }

    public static void editarFichero(String ruta, String[] dato) {
        //Este metodo a diferencia del editar fiche que solo recibe el String lo que hace es que
        //imprime las lineas por renglon, el otro las escribe todas en la misma linea.
        try {

            File Ffichero = new File(ruta);
            //Si no Existe el fichero lo crea
            if (!Ffichero.exists()) {
                Ffichero.createNewFile();
            }
            /*Abre un Flujo de escritura,sobre el fichero con codificacion utf-8. 
             *Además  en el pedazo de sentencia "FileOutputStream(Ffichero,true)",
             *true es por si existe el fichero seguir añadiendo texto y no borrar lo que tenia*/
            BufferedWriter Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Ffichero, false), "utf-8"));
            PrintWriter salida = new PrintWriter(Fescribe, true);
            /*Escribe en el fichero la cadena que recibe la función. 
             *el string "\r\n" significa salto de linea*/
            for (int i = 0; i < dato.length; i++) {
                if (i == 0) {
                    salida.write(dato[i]);
                } else {
                    salida.println();
                    salida.write(dato[i]);
                }
            }
            Fescribe.close();
        } catch (Exception ex) {
            LOGGER.error("Error : " + ex.getMessage());
        }
    }

    public String leerArchivo(String archivo) {
        try {
            String cadena = "";
            String cadena2;
            FileReader f;

            f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            cadena2 = b.readLine();
            while (cadena2 != null) {
                if (cadena.equals("")) {
                    cadena = cadena2 + ";";
                } else {
                    cadena = cadena + "\n" + cadena2 + ";";
                }
                cadena2 = b.readLine();
            }
            b.close();
            return cadena;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo txt de este dron");
            System.out.println(ex.getMessage());
            LOGGER.error("Error : " + ex.getMessage());
        }
        return " ";
    }
}
