package com.corrientazo.Vistas;

import com.corrientazo.Control.archivoNotas;
import com.corrientazo.Modelo.iniciarFutures;
import com.corrientazo.Modelo.modConfiguracion;
import com.corrientazo.Modelo.moverDron;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Function;
import org.apache.log4j.Logger;

public class frmMovimientosDron extends javax.swing.JPanel implements moverDron, iniciarFutures {

    final static Logger LOGGER = Logger.getLogger(frmPrincipal.class);
    Executor executor = Executors.newCachedThreadPool();
    archivoNotas archivo = new archivoNotas();
    modConfiguracion config;
    int lineaRuta = 0;

    public int getLineaRuta() {
        return lineaRuta;
    }

    public void setLineaRuta(int lineaRuta) {
        this.lineaRuta = lineaRuta;
    }

    int dron = 0;

    public frmMovimientosDron(int dron) {
        initComponents();
        fondoPantallaPanel pnlFondo = new fondoPantallaPanel();
        //Al pnl fondo le vamos a añadir la clase de fondo de pantalla.
        jPanelDibujo.add(pnlFondo);

        lbDron.setLocation(280, 280);

        jLabel22.setText(" INSPECCION DE RUTA DRON NUM " + dron);
        this.dron = dron;
        config = new modConfiguracion();
        iniciarFutures();

    }

    @Override
    public void iniciarFutures() {
        CompletableFuture<Void> comandos = CompletableFuture.runAsync(() -> {
            LOGGER.info("LEYENDO COMANDOS DEL DRON: " + dron);
            String texto;
            //Hacemo la condicion para que texto lea el archivo quele pertenece
            if (dron < 10) {
                texto = archivo.leerArchivo("Entradas/in0" + dron + ".txt");
            } else {
                texto = archivo.leerArchivo("Entradas/in" + dron + ".txt");
            }
            //Enviamos al campo el texto    
            System.out.println("TEXTO " + texto);
            txtComandos.setText(texto.toUpperCase());
        }, executor);

        CompletableFuture<Void> rutas = comandos.thenRun(() -> {
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
            LOGGER.info("Linea de ruta: " + lineaRuta);
        });

        rutas.whenComplete((s, e) -> moverDron());
    }

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
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanelDibujo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDibujo.setPreferredSize(new java.awt.Dimension(700, 700));

        lbDron.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbDron.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDron.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Dron.png"))); // NOI18N
        lbDron.setToolTipText("Click para posición");

        javax.swing.GroupLayout jPanelDibujoLayout = new javax.swing.GroupLayout(jPanelDibujo);
        jPanelDibujo.setLayout(jPanelDibujoLayout);
        jPanelDibujoLayout.setHorizontalGroup(
            jPanelDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDibujoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbDron)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDibujoLayout.setVerticalGroup(
            jPanelDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDibujoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbDron)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        txtPosiciones.setLineWrap(true);
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
        txtComandos.setLineWrap(true);
        txtComandos.setRows(5);
        txtComandos.setEnabled(false);
        jScrollPane1.setViewportView(txtComandos);

        btnRutaAnterior.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRutaAnterior.setText("Anterior Domicilio");
        btnRutaAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRutaAnteriorActionPerformed(evt);
            }
        });

        btnSiguienteRuta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSiguienteRuta.setText("Siguiente Domicilio");
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

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(3);
        jTextArea1.setText("NOTA: Puede ver la posición final \ndel Dron presionando \"Iniciar \nRecorrido\" o ver cada fase de la \nruta con \"Siguiente Domicilio\" o \n\"Anterior Domicilio\" ");
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnIniciarRecorrido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnLimpiarRecorrido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSiguienteRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRutaAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jScrollPane3)
                .addComponent(jScrollPane1)
                .addComponent(jScrollPane2)
                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(btnSiguienteRuta)
                .addGap(5, 5, 5)
                .addComponent(btnRutaAnterior)
                .addGap(5, 5, 5)
                .addComponent(btnIniciarRecorrido)
                .addGap(5, 5, 5)
                .addComponent(btnLimpiarRecorrido)
                .addGap(5, 5, 5)
                .addComponent(jLabel3)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 1188, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanelDibujo, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel22)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelDibujo, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
        moverDron();
    }//GEN-LAST:event_btnIniciarRecorridoActionPerformed

    private void btnSiguienteRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteRutaActionPerformed
        //Sumamos la ruta de la linea para que coja la linea siguiente
        lineaRuta++;
        moverDron();
    }//GEN-LAST:event_btnSiguienteRutaActionPerformed

    private void btnRutaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutaAnteriorActionPerformed
        //Sumamos la ruta de la linea para que coja la linea anterior
        lineaRuta--;
        moverDron();
    }//GEN-LAST:event_btnRutaAnteriorActionPerformed

    private void btnLimpiarRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarRecorridoActionPerformed
        //le enviamos la linea 0 para que limpie las rutas y la posicion final
        lineaRuta = 0;
        moverDron();
    }//GEN-LAST:event_btnLimpiarRecorridoActionPerformed

    Function<String, String[]> lineaDeComandos() {
        return x -> x.split(";");
    }

    /**
     *
     */
    @Override
    public void moverDron() {
        LOGGER.info("Moviendo el dron");
        //archivoNotas archivoNotas = new archivoNotas();
        //Creamos un vector linea y le enviamos cada una de las rutas separadas por punto y coma.
        String[] linea = lineaDeComandos().apply(txtComandos.getText());
        iniciarRecorrido(linea);
    }

    public List<String> iniciarRecorrido(String[] linea) {
        //Creamos un vector para las posiciones finales de cada ruta;
        //String[] posicionFinal;
        List<String> posicionFinal = new ArrayList<>();
        //Iniciamos los contador posX y posY los cuales nos van a llevar el calculo de las posiciones del dron
        int posX = 0, posY = 0;
        //Iniciamos los contadores para las posiciones del label.
        int locationLabelX = 280, locationLabelY = 280;
        //Iniciamos el sentido del dron que sera norta
        String sentido = "N";
        //N = norte, S = Sur, O= Oeste, E=Este;    
        //Iniciamos una variable para guardar cual es la ultima direccion del dron que lo vamos a necesitar en la posicion final.
        String ultimaDireccionX = "";
        //Si la linea es 0 o -1 significa que esta vacio y retorna y inicia las variables nuevamente,
        if (lineaRuta == 0 || lineaRuta == -1) {
            LOGGER.debug("Linea ruta 0");
            txtPosiciones.setText("");
            lineaRuta = 0;
            //Llamarmos el archivo y el metodo que va a escribir el fichero.
            archivoNotas.editarFichero("Config/Ubicaciones/ubicacionDron" + dron + ".txt", "" + lineaRuta);
            sacarUbicaciones(new ArrayList());
            locationLabelX = 280;
            locationLabelY = 280;
            lbDron.setLocation(locationLabelY, locationLabelX);
            return posicionFinal;
            //Si la linea de la ruta es -2 significa que va a leer todas las rutas y no linea por linea
        } else if (lineaRuta == -2) {
            //Le agregamos al vector posicion final la cantidad de filas que puede tener en este caso las de la linea de rutas leidas;
            //posicionFinal = new String[linea.length];
            lineaRuta = linea.length;
        } else if (lineaRuta > linea.length) {
            LOGGER.debug("Linea ruta > Lineas de codigo");
            //Si la linea de la ruta es mayor a la cantidad de lineas leidas la asigna para que sean iguales.
            lineaRuta = linea.length;
            archivoNotas.editarFichero("Config/Ubicaciones/ubicacionDron" + dron + ".txt", "" + lineaRuta);
            return posicionFinal;
        } else {
            //Le agregamos al vector posicion final la cantidad de filas que puede tener en este caso las de la linea de rutas leidas;
            //posicionFinal = new String[lineaRuta];
        }

        for (int i = 0; i < lineaRuta; i++) {
            //realizamos modulo sobre la posicion en que estamos para verificar si esta en la cantidad que puede llevar cada dron de platos.
            if (i % config.getCantidadPlatos() == 0) {
//                System.out.println("SE DEVOLVIO A LA POSICION 0");
                //Si entra aca, significa que es modulo de la cantidad configurada y el dron vuelve a iniciar en cero.
                posX = 0;
                locationLabelX = 250;
                posY = 0;
                locationLabelY = 200;
                sentido = "N";
                ultimaDireccionX = "";
            }
            //A la ruta le asignamos la linea de la ruta que estamos ejecutando.
            String ruta = linea[i];
            //Iniciamos una variable para saber cual es la posicion de la ultima letra;
            int letraFinal = ruta.length();
            //Iniicamos un for que recorra cada una de las letras de la ruta
            for (int x = 0; x < ruta.length(); x++) {
                //Realizamos un substring para tomar la letra que se va a ejecutar
                String letras = ruta.substring(x, x + 1);
                //Verificamos cual es la letra que se esta ejecutando
                if (letras.equals("A") || letras.equals("a")) {
//                    System.out.println("ADELANTE");
                    switch (sentido) {
                        case "N":
                            ultimaDireccionX = "N";
                            posX++;
                            locationLabelX = locationLabelX - 20;
                            break;
                        case "S":
                            ultimaDireccionX = "S";
                            posX--;
                            locationLabelX = locationLabelX + 20;
                            break;
                        case "O":
                            posY--;
                            locationLabelY = locationLabelY - 20;
                            break;
                        case "E":
                            posY++;
                            locationLabelY = locationLabelY + 20;
                            break;
                        default:
                            break;
                    }
                }

                //Verificamos la posicion de la letra final si es igual a la posicion en que estamos para validar cual seria el sentido final del dron.
                //La diferencia entre el sentido es que si viene desde norte, la posicion iria hacia abajo y viceversa
                if (letraFinal - 1 != x) {
                    if (letras.equals("D") || letras.equals("d")) {
                        switch (sentido) {
                            case "N":
                                sentido = "E";
                                break;
                            case "S":
                                sentido = "O";
                                break;
                            case "O":
                                sentido = "N";
                                break;
                            case "E":
                                sentido = "S";
                                break;
                            default:
                                break;
                        }
                    } else if (letras.equals("I") || letras.equals("i")) {
                        switch (sentido) {
                            case "N":
                                sentido = "O";
                                break;
                            case "S":
                                sentido = "O";
                                break;
                            case "O":
                                sentido = "S";
                                break;
                            case "E":
                                sentido = "N";
                                break;
                            default:
                                break;
                        }
                    }
                } else {
                    if (ultimaDireccionX.equals("S")) {
                        if (letras.equals("D") || letras.equals("d")) {
                            switch (sentido) {
                                case "N":
                                    sentido = "E";
                                    break;
                                case "S":
                                    sentido = "E";
                                    break;
                                case "O":
                                    sentido = "S";
                                    break;
                                case "E":
                                    sentido = "N";
                                    break;
                                default:
                                    break;
                            }
                        } else if (letras.equals("I") || letras.equals("i")) {
                            switch (sentido) {
                                case "N":
                                    sentido = "O";
                                    break;
                                case "S":
                                    sentido = "O";
                                    break;
                                case "O":
                                    sentido = "N";
                                    break;
                                case "E":
                                    sentido = "S";
                                    break;
                                default:
                                    break;
                            }
                        }
                    } else {
                        if (letras.equals("D") || letras.equals("d")) {
                            switch (sentido) {
                                case "N":
                                    sentido = "O";
                                    break;
                                case "S":
                                    sentido = "O";
                                    break;
                                case "O":
                                    sentido = "N";
                                    break;
                                case "E":
                                    sentido = "S";
                                    break;
                                default:
                                    break;
                            }
                        } else if (letras.equals("I") || letras.equals("i")) {
                            switch (sentido) {
                                case "N":
                                    sentido = "O";
                                    break;
                                case "S":
                                    sentido = "O";
                                    break;
                                case "O":
                                    sentido = "S";
                                    break;
                                case "E":
                                    sentido = "N";
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }      
            //Le enviamos al dron la nueva ubicacion 
            lbDron.setLocation(locationLabelY, locationLabelX);
            //Le asingamos el nombre a cada letra de los sentidos.
            String sentidoFinal = sentidoEnLetras().apply(sentido);

            //Le añadimos al vector de posicion final las posiciones tomadas
            //posicionFinal[i] = "(" + posY + ", " + posX + ") Dirección " + sentidoFinal;
            posicionFinal.add("(" + posY + ", " + posX + ") Dirección " + sentidoFinal);
            lbDron.setToolTipText("(" + posY + ", " + posX + ") Dirección " + sentidoFinal);
//            lbDron.setText("( " + posY + "," + posX + " )");
        }
        archivoNotas.editarFichero("Config/Ubicaciones/ubicacionDron" + dron + ".txt", "" + lineaRuta);
        //Llamamos el metodo para sacar las ubicaciones
        return sacarUbicaciones(posicionFinal);
    }

    public Function<String, String> sentidoEnLetras() {
        return (String x) -> {
            switch (x) {
                case "N":
                    return "Norte";
                case "S":
                    return "Sur";
                case "E":
                    return "Este";
                case "O":
                    return "Oriente";
                default:
                    return "";
            }
        };
    }

    public List<String> sacarUbicaciones(List<String> listaUbiaciones) {
        txtPosiciones.setText("");
        listaUbiaciones.stream().forEach(s -> txtPosiciones.setText(txtPosiciones.getText() + s + "\n"));

        if (dron < 10 && dron != 0) {
            archivoNotas.editarFichero("Salidas/out0" + dron + ".txt", listaUbiaciones.stream().toString());
        } else {
            archivoNotas.editarFichero("Salidas/out" + dron + ".txt", listaUbiaciones.stream().toString());
        }
        return listaUbiaciones;
    }

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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbDron;
    private javax.swing.JTextArea txtComandos;
    private javax.swing.JTextArea txtPosiciones;
    // End of variables declaration//GEN-END:variables

}
