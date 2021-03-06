package com.corrientazo.Vistas;

import com.corrientazo.Control.archivoNotas;
import com.corrientazo.Modelo.modConfiguracion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

import javax.swing.JButton;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class frmPrincipal extends javax.swing.JFrame {

    final static Logger LOGGER = Logger.getLogger(frmPrincipal.class);
    JButton boton;
    archivoNotas archivo;

    public frmPrincipal() throws InterruptedException, ExecutionException {
        initComponents();
        this.setLocationRelativeTo(null);
        archivo = new archivoNotas();
        cargarBotones();
    }

    public void cargarBotones() throws InterruptedException, ExecutionException {

        modConfiguracion modConfig = archivo.cargarDatos();
        //posX  esta la posicion inicial de los botones en sentido vertical
        int posX = 2;
        LOGGER.info("Cantidad de drones cargar botones: " + modConfig.getCantidadDrones());
        pnlMenu.removeAll();
        for (int i = 1; i <= modConfig.getCantidadDrones(); i++) {
            //Creamos un nuevo objeto boton
            boton = new JButton();
            //Le enviamos las caracteriscitas de ubicación y medida.
            boton.setBounds(0, posX, 120, 30);
            boton.setText("DRON " + (i));
            //Le añadimos el nombre al boton
            boton.setName("boton" + i);
            //Creamos un contador x que es igual a numero que vayamos del ciclo.
            final int x = i;
            //Al boton le añadimos el action listener para cuando presiones el boton
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Cuando presionemos el boton se llama el metodo para que muestre el mapa de su caso.
                    llamarFrmMovimientos(x);
                }
            });
            //añadimos el boton al menu
            pnlMenu.add(boton);
            //Sumamos la posicion posX para que el siguiente boton se cree mas abajo del ya creado.
            posX = posX + 31;
        }
        //Le enviamos pnl menu las nuevas dimensiones para que puedan caber todos los botones y se active el scroll panel.
        pnlMenu.setPreferredSize(new Dimension(130, posX + 5));
        pnlMenu.revalidate();
        pnlMenu.repaint();
    }

    private void llamarFrmMovimientos(int dron) {
        //Instanciamos la clase del mapa
        frmMovimientosDron mapaPrincipal = new frmMovimientosDron(dron);
        //Le enviamos el tamaño del jpanel donde lo vamos a meter.
        mapaPrincipal.setSize(pnlContenedorMapa.getSize());
        //Removemos todas las propiedades que hay dentor del Jcontenedor
        pnlContenedorMapa.removeAll();
        //Al contenedor le añadimos el mapa principal y le damos la posicion en centro.
        pnlContenedorMapa.add(mapaPrincipal, BorderLayout.CENTER);
        //Sirve para volver a cargar los atributos del pnl que vamos a llamar y que se puedan usar en el contenedor.
        pnlContenedorMapa.revalidate();
        //Pintamos o llamamos el mapa
        pnlContenedorMapa.repaint();
//      pnlContenedorMapa.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackGround = new javax.swing.JPanel();
        btnConfiguracion = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlMenu = new javax.swing.JPanel();
        pnlContenedorMapa = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Su corrientazo a domicilio");
        setFocusCycleRoot(false);
        setResizable(false);

        btnConfiguracion.setText("CONFIGURACIÓN");
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });

        jScrollPane2.setAutoscrolls(true);

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnlMenu.setPreferredSize(new java.awt.Dimension(130, 500));

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 152, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 686, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(pnlMenu);

        pnlContenedorMapa.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlContenedorMapaLayout = new javax.swing.GroupLayout(pnlContenedorMapa);
        pnlContenedorMapa.setLayout(pnlContenedorMapaLayout);
        pnlContenedorMapaLayout.setHorizontalGroup(
            pnlContenedorMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1019, Short.MAX_VALUE)
        );
        pnlContenedorMapaLayout.setVerticalGroup(
            pnlContenedorMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlBackGroundLayout = new javax.swing.GroupLayout(pnlBackGround);
        pnlBackGround.setLayout(pnlBackGroundLayout);
        pnlBackGroundLayout.setHorizontalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(pnlContenedorMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnlBackGroundLayout.setVerticalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlContenedorMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBackGroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(btnConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackGround, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackGround, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        frmConfiguracionDrones infConfig = new frmConfiguracionDrones();
        infConfig.setSize(pnlContenedorMapa.getSize());
        pnlContenedorMapa.removeAll();
        pnlContenedorMapa.add(infConfig, CENTER_ALIGNMENT);
        pnlContenedorMapa.revalidate();
        pnlContenedorMapa.repaint();
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    public static void main(String args[]) {

        BasicConfigurator.configure();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmPrincipal().setVisible(true);
                } catch (InterruptedException | ExecutionException ex) {
                    java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlBackGround;
    private javax.swing.JPanel pnlContenedorMapa;
    private javax.swing.JPanel pnlMenu;
    // End of variables declaration//GEN-END:variables

}
