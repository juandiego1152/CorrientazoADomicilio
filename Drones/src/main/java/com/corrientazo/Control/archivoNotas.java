package com.corrientazo.Control;

import com.corrientazo.Modelo.configuracionDronesPlatos;
import com.corrientazo.Modelo.modConfiguracion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class archivoNotas implements configuracionDronesPlatos {

    final static Logger LOGGER = Logger.getLogger(archivoNotas.class);
    Executor executor = Executors.newCachedThreadPool();

    public modConfiguracion cargarDatos() throws InterruptedException, ExecutionException {
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
        modConfiguracion modConfig = new modConfiguracion();
        CompletableFuture<String> cantidadDronesCompletable = CompletableFuture.supplyAsync(() -> cantidadDrones(cantidadDrones), executor);

        CompletableFuture<String> cantidadPlatosCompletable = CompletableFuture.supplyAsync(() -> cantidadPlatos(cantidadPlatos), executor);

        CompletableFuture<String> futureCantidadDrones = cantidadDronesCompletable.exceptionally(e -> {
            LOGGER.error("Resultado con excepción de cantidad de drones!!");
            return "Error en la cantidad drones en configuracion";
        });

//        CompletableFuture<modConfiguracion> modelo = cantidadDronesCompletable.thenAcceptBoth(cantidadPlatosCompletable, () -> {
//            try {
        modConfig.setCantidadDrones(Integer.parseInt(cantidadDronesCompletable.get()));
        modConfig.setCantidadPlatos(Integer.parseInt(cantidadPlatosCompletable.get()));
//               return modConfig;
//            } catch (InterruptedException | ExecutionException ex) {
//              
//            } 
//        });

        LOGGER.info("Cantidad de drones: " + cantidadDrones);
        LOGGER.info("Cantidad de platos: " + cantidadPlatos);
        return modConfig;
    }

    @Override
    public String cantidadDrones(String cantDrones) {
        Optional<String> cantidadDronesOptional = Optional.ofNullable(cantDrones);
        if (cantidadDronesOptional.isPresent()) {
            if (cantidadDronesOptional.get().equals(" ")) {
                cantDrones = "1";
            } else {
                cantDrones = cantidadDronesOptional.get();
            }
        } else {
            cantDrones = "1";
        }
        return cantDrones;
    }

    @Override
    public String cantidadPlatos(String cantPlatos) {
        String cantidadPlatosOptional = Optional.ofNullable(cantPlatos).orElse("3");
        if (cantidadPlatosOptional.equals("")) {
            cantPlatos = "3";
        } else {
            cantPlatos = cantidadPlatosOptional;
        }
        return cantPlatos;
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

//    public String leerArchivo(String archivo) {
//          LOGGER.info("RUTA ARCHIVO: "+ archivo);
//        try {
//            String cadena = "";
//            String cadena2;
//            FileReader f;          
//            f = new FileReader(archivo);
//            BufferedReader b = new BufferedReader(f);
//            cadena2 = b.readLine();
//            //cadena2 = b.lines().flatMap(String::toUpperCase).;
//            while (cadena2 != null) {
//                if (cadena.equals("")) {
//                    cadena = cadena2 + ";";
//                } else {
//                    cadena = cadena + "\n" + cadena2 + ";";
//                }
//                cadena2 = b.readLine();
//            }
//
//            b.close();
//
//            return cadena;
//        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(null, "No se encontro el archivo txt de este dron");            
//            LOGGER.error("Error : " + ex.getMessage());
//        }
//        return " ";
//    }
    public String leerArchivo(String archivo) {
        LOGGER.info("RUTA ARCHIVO: " + archivo);
        List<String> lista = new ArrayList<>();
        String cadena;
        try {
            FileReader f;
            f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            cadena = b.readLine();
            while (cadena != null) {
                lista.add(cadena);
                cadena = b.readLine();
            }
            b.close();
            lista.stream().map(String::toUpperCase);
            return volverCadena(lista);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo txt de este dron");
            LOGGER.error("Error : " + ex.getMessage());
            return "";
        }
    }
    
    String volverCadena(List<String> lista){
        String cadenaFinal = "";
        for (String string : lista) {
            cadenaFinal = cadenaFinal + string + "\n";
        }
        return "";
    }
}

//	
//try (BufferedReader reader = Files.newBufferedReader(Paths.get("SomeLines.txt"), StandardCharsets.UTF_8)) {
//	reader.lines()
//		  .flatMap(line -> Stream.of(line.split(WORD_REGEXP)))
//		  .distinct()
//		  .map(String::toLowerCase)
//		  .forEach(System.out::println);
//}
//}
