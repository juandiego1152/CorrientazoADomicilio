package com.corrientazo.Vistas;

import com.corrientazo.Control.archivoNotas;
import com.corrientazo.Modelo.modConfiguracion;
import java.util.Optional;
import org.apache.log4j.Logger;

public class frmMovimientosDron extends javax.swing.JPanel {

    final static Logger LOGGER = Logger.getLogger(frmPrincipal.class);
    archivoNotas archivo = new archivoNotas();
    modConfiguracion config;
    int lineaRuta = 0;
    int dron = 0;

    public frmMovimientosDron(int dron) {
        initComponents();
        jLabel22.setText(" INSPECCION DE RUTA DRON NUM " + dron);
        this.dron = dron;
        //Instanciamos el panel del fondo de pantala
        //fondoPantallaPanel pnlFondo = new fondoPantallaPanel();
        //Al pnl fondo le vamos a añadir la clase de fondo de pantalla.
      
        //A la clase config inicialidada publica le añadimos los datos del metodo ubicado en la clase archivo..
//        config = archivo.cargarDatos();
        config = new modConfiguracion();

        //Llenamos las lineas que hay en el archivo de cada dron
        leerLineas(dron);
//        for (int i = 0; i < filasCargadas.length; i++) {

        Optional<String> filasOptional = Optional.ofNullable(archivo.leerArchivoUnicaLinea("Config/Ubicaciones/ubicacionDron" + dron + ".txt"));

        if (filasOptional.isPresent()) {
            if (filasOptional.get().equals(" ")) {
                lineaRuta = 0;
            } else {
                lineaRuta = filasOptional.map(Integer::parseInt).get();
            }
        } else {
            lineaRuta = 0;
        }
      

//        }
    }

    private void leerLineas(int dron) {
        LOGGER.info("DRON: " + dron);
        String texto;
        //Hacemo la condicion para que texto lea el archivo quele pertenece
        if (dron < 10) {
            texto = archivo.leerArchivo("Entradas/in0" + dron + ".txt");
        } else {
            texto = archivo.leerArchivo("Entradas/in" + dron + ".txt");
        }
        //Enviamos al campo el texto
        txtComandos.setText(texto);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelDibujo = new javax.swing.JPanel();
        lbDron = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPosiciones = new javax.swing.JTextArea();
        btnIniciarRecorrido = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComandos = new javax.swing.JTextArea();
        btnRutaAnterior = new javax.swing.JButton();
        btnSiguienteRuta = new javax.swing.JButton();
        btnLimpiarRecorrido = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanelDibujo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDibujo.setPreferredSize(new java.awt.Dimension(700, 700));

        lbDron.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbDron.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Dron.png"))); // NOI18N
        lbDron.setToolTipText("Click para posición");

        javax.swing.GroupLayout jPanelDibujoLayout = new javax.swing.GroupLayout(jPanelDibujo);
        jPanelDibujo.setLayout(jPanelDibujoLayout);
        jPanelDibujoLayout.setHorizontalGroup(
            jPanelDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDibujoLayout.createSequentialGroup()
                .addContainerGap(255, Short.MAX_VALUE)
                .addComponent(lbDron)
                .addGap(245, 245, 245))
        );
        jPanelDibujoLayout.setVerticalGroup(
            jPanelDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDibujoLayout.createSequentialGroup()
                .addGap(258, 258, 258)
                .addComponent(lbDron)
                .addContainerGap(340, Short.MAX_VALUE))
        );

        jLabel22.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("                                              INSPECCION DE RUTA");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Datos de entrada:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Posiciones:");

        txtPosiciones.setColumns(20);
        txtPosiciones.setRows(5);
        txtPosiciones.setEnabled(false);
        jScrollPane2.setViewportView(txtPosiciones);

        btnIniciarRecorrido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnIniciarRecorrido.setText("Iniciar Recorrido");
        btnIniciarRecorrido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarRecorridoActionPerformed(evt);
            }
        });

        txtComandos.setColumns(20);
        txtComandos.setRows(5);
        txtComandos.setEnabled(false);
        jScrollPane1.setViewportView(txtComandos);

        btnRutaAnterior.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRutaAnterior.setText("Ruta Anterior");
        btnRutaAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRutaAnteriorActionPerformed(evt);
            }
        });

        btnSiguienteRuta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSiguienteRuta.setText("Siguiente Ruta");
        btnSiguienteRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteRutaActionPerformed(evt);
            }
        });

        btnLimpiarRecorrido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLimpiarRecorrido.setText("Limpiar Recorrido");
        btnLimpiarRecorrido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarRecorridoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addComponent(btnIniciarRecorrido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnLimpiarRecorrido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(0, 0, 0))
            .addComponent(btnSiguienteRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRutaAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSiguienteRuta)
                .addGap(5, 5, 5)
                .addComponent(btnRutaAnterior)
                .addGap(5, 5, 5)
                .addComponent(btnIniciarRecorrido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiarRecorrido)
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanelDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel22)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarRecorridoActionPerformed
        //Iniciamos el recorrido y se pone -2 solo para que cumpla la condicion de que va a tomar todas las lineas
        lineaRuta = -2;
        //Llamamos el metodo 
        
    }//GEN-LAST:event_btnIniciarRecorridoActionPerformed

    private void btnSiguienteRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteRutaActionPerformed
        //Sumamos la ruta de la linea para que coja la linea siguiente
        lineaRuta++;
      
    }//GEN-LAST:event_btnSiguienteRutaActionPerformed

    private void btnRutaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutaAnteriorActionPerformed
        //Sumamos la ruta de la linea para que coja la linea anterior
        lineaRuta--;
        
    }//GEN-LAST:event_btnRutaAnteriorActionPerformed

    private void btnLimpiarRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarRecorridoActionPerformed
        //le enviamos la linea 0 para que limpie las rutas y la posicion final
        lineaRuta = 0;
       
    }//GEN-LAST:event_btnLimpiarRecorridoActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarRecorrido;
    private javax.swing.JButton btnLimpiarRecorrido;
    private javax.swing.JButton btnRutaAnterior;
    private javax.swing.JButton btnSiguienteRuta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelDibujo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbDron;
    private javax.swing.JTextArea txtComandos;
    private javax.swing.JTextArea txtPosiciones;
    // End of variables declaration//GEN-END:variables
}
