package com.corrientazo.Tests;

import com.corrientazo.Vistas.frmMovimientosDron;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan Diego Pro
 */
public class TestDireccionEnLetrasFinal {
    
    public TestDireccionEnLetrasFinal() {
    }
         
    @Test
    public void revisarRutaSalida(){
        frmMovimientosDron app = new frmMovimientosDron(1);
        String sentido = app.sentidoEnLetras().apply("S");
        assertEquals("Sur", sentido);
        
        sentido = app.sentidoEnLetras().apply("N");
        assertEquals("Norte", sentido);
    }
    
}
