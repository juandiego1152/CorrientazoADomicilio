package com.corrientazo.Vistas;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public class fondoPantallaPanel extends JPanel {

    public static void main(String[] args) {
        //Instanciamos un jFrame el cual le vamos a inicializar las caracteristicas del panel que va a contener la imagen.
        JFrame f = new JFrame();
        //Iniciamos el frame como visible
        f.setVisible(true);
        //Instanciamos un nuevo fondo de pantalla para tomas las caracteristicas.
        fondoPantallaPanel p = new fondoPantallaPanel();
        //al nuevo fondo le enviamos las caracteristicas de medida y de las pocisiones donde van a iniciar.
        p.setBounds(0, 0, 800, 800);
        //Le añadimos al frame el fondo con las caracteristicas que ya agregamos.
        f.add(p);
    }

    public fondoPantallaPanel() {
        //Le enviamos una medidad al frame
        this.setSize(800, 800);
        //Enviamos el layout en null para podemo crear nuevos objetos en el y poder modificarlos.
        this.setLayout(null);
        //Volvemos a pintar los cambios de caracteristicas realizadas.
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        //Creamos un ImagenIcon, es decir, una imagen la añadiremos como icono para enviarla de fondo,
        //Tomamos la direccion donde esta el archivo, "getClass().getResourse" representa la direccion local del programa.    
        ImageIcon fot = new ImageIcon(getClass().getResource("/Files/Ciudad.jpg"));               
           
        //A la propiedad de los graficos le enviamos la imagen, y las caracteristicas de posicion y tamaño.
        g.drawImage(fot.getImage(), 0, 0, 800, 800, null);
        //Enviamos que no sea opaco
        setOpaque(false);
        //Pintamos la clase
        super.paint(g);
    }

}
