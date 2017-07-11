/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corrientazo.Tests;

import com.corrientazo.Vistas.frmMovimientosDron;
import java.util.ArrayList;
import java.util.List;
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
public class TestDeSalidas {

    public TestDeSalidas() {
    }

    @Test
    public void validarSalidas() {
        BasicConfigurator.configure();
        frmMovimientosDron app = new frmMovimientosDron(1);
        app.setLineaRuta(-2);
        String[] lineas = new String[]{"AAAAIAAD", "DDAIAD", "AAIADAD"};

        List<String> lineasList = new ArrayList<>();

        lineasList.add("(-2, 4) Dirección Norte");
        lineasList.add("(-3, 3) Dirección Sur");
        lineasList.add("(-4, 2) Dirección Oriente");

        List<String> listaResultado = app.iniciarRecorrido(lineas);

        assertEquals(listaResultado, lineasList);

    }
}
