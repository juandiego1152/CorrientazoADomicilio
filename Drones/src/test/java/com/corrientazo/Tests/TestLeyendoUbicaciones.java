/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corrientazo.Tests;

import com.corrientazo.Vistas.frmMovimientosDron;
import javax.security.auth.login.Configuration;
import org.apache.log4j.BasicConfigurator;
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
public class TestLeyendoUbicaciones {
    
    public TestLeyendoUbicaciones() {
    }
  
    
    @Test
    public void validarSalidas() {
        BasicConfigurator.configure();
        frmMovimientosDron app = new frmMovimientosDron(1);
        app.setLineaRuta(-2);
        String[] lineas = new String[]{"AAAAIAAD","DDAIAD", "AAIADAD"};
        String[] resultado = app.iniciarRecorrido(lineas);
        
        for (String string : resultado) {
            System.out.println("Resultado: " + string);
        }
        String[] salidas = new String[]{"(-2, 4) Dirección Norte","(-3, 3) Dirección Sur","(-4, 2) Dirección Oriente"};
        
        for (String salida : salidas) {
            System.out.println("Salida: " + salida);
        }
        assertArrayEquals(resultado, salidas);
        
    }
}
