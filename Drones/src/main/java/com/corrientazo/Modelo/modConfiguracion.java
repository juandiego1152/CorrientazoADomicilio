package com.corrientazo.Modelo;

public class modConfiguracion {

    private int cantidadDrones;
    private int cantidadPlatos;

    public modConfiguracion(int cantidadDrones, int cantidadPlatos) {
        this.cantidadDrones = cantidadDrones;
        this.cantidadPlatos = cantidadPlatos;
    }
    
    public modConfiguracion(){
        cantidadDrones = 1;
        cantidadPlatos = 3;
    }
   
    public int getCantidadDrones() {
        return cantidadDrones;
    }

    public void setCantidadDrones(int cantidadDrones) {
        this.cantidadDrones = cantidadDrones;
    }

    public int getCantidadPlatos() {
        return cantidadPlatos;
    }

    public void setCantidadPlatos(int cantidadPlatos) {
        this.cantidadPlatos = cantidadPlatos;
    }

}
