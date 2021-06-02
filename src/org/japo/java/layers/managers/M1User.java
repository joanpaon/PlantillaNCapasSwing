/*
 * Copyright 2021 José A. Pacheco Ondoño - japolabs@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.layers.managers;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.japo.java.components.BackgroundPanel;
import org.japo.java.entities.Credencial;
import org.japo.java.exceptions.ConnectivityException;
import org.japo.java.layers.services.S1User;
import org.japo.java.libraries.UtilesSwing;
import org.japo.java.layers.services.S2Bnes;

/**
 *
 * @author José A. Pacheco Ondoño - japolabs@gmail.com
 */
public final class M1User extends JFrame implements S1User {

    //<editor-fold defaultstate="collapsed" desc="--- User Interface Manager ---">
    // Propiedades Credencial
    public static final String PRP_CONN_MODE = "jdbc.conn.mode";
    public static final String PRP_CONN_USER = "jdbc.conn.user";
    public static final String PRP_CONN_PASS = "jdbc.conn.pass";

    // Propiedades Ventana
    public static final String PRP_FORM_LNF_PROFILE = "form.lnf.profile";
    public static final String PRP_FORM_TITLE = "form.title";
    public static final String PRP_FORM_WIDTH = "form.width";
    public static final String PRP_FORM_HEIGHT = "form.height";
    public static final String PRP_FORM_FULL_SCREEN = "form.full_screen";

    // Propiedades Resources
    public static final String PRP_RESOURCE_FONT = "resource.font";
    public static final String PRP_RESOURCE_IMG = "resource.img";
    public static final String PRP_RESOURCE_IMG_FAVICON = "resource.img.favicon";

    // Propiedades Aplicación
    private final Properties prp;

    // Gestor Lógica de Negocio
    private final S2Bnes bs;

    // Constructor Parametrizado
    public M1User(Properties prp, S2Bnes bs) {
        // Propiedades Aplicación
        this.prp = prp;

        // Gestor Lógica de Negocio
        this.bs = bs;

        // Inicialización Previa
        initBefore();

        // Creación del interfaz
        initComponents();

        // Inicialización Previa
        initAfter();
    }

    // Validación de Usuario
    @Override
    public final void loginApp() throws ConnectivityException {
        // Usuario > Credenciales
        String user = prp.getProperty(PRP_CONN_USER);
        String pass = prp.getProperty(PRP_CONN_PASS);

        // Modo de Conexión
        String mode = prp.getProperty(PRP_CONN_MODE);

        // Evaluación del modo de conexión (Properties)
        if (mode != null && mode.equals("login")) {
            // ---
        }

        // Creación de Entidad Credencial
        Credencial c = new Credencial(user, pass);

        // Conexión de Credenciales
        bs.loginApp(c);

        // Mensaje - Bitácora ( Comentar en producción )
        System.out.println("Bitácora: Acceso a Datos Establecido");
    }

    // Interfaz - Inicialización Previa
    private void initBefore() {
        // Establecer LnF
        UtilesSwing.establecerLnFProfile(prp.getProperty(PRP_FORM_LNF_PROFILE));

        // Fuentes
        fntPpal = UtilesSwing.generarFuenteRecurso(prp.getProperty(PRP_RESOURCE_FONT));

        // Imágenes
        imgBack = UtilesSwing.importarImagenRecurso(prp.getProperty(PRP_RESOURCE_IMG));

        // Panel Principal
        pnlPpal = new BackgroundPanel(imgBack);

        // Panel Principal > Ventana Principal
        setContentPane(pnlPpal);
    }

    // Interfaz - Inicialización Posterior
    private void initAfter() {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty(PRP_RESOURCE_IMG_FAVICON));

        // Ventana Principal ( Parametrización Opcional )
//        setTitle(prp.getProperty(PRP_FORM_TITLE));
//        int w = Integer.parseInt(prp.getProperty(PRP_FORM_WIDTH));
//        int h = Integer.parseInt(prp.getProperty(PRP_FORM_HEIGHT));
//        setSize(w, h);
//        setLocationRelativeTo(null);

        // Establecer Modo Pantalla Completa
        String scrMode = prp.getProperty(PRP_FORM_FULL_SCREEN);
        if (scrMode != null && scrMode.contains("true")) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }

    public void procesarCierreVentana(WindowEvent e) {
        // Cerrar Acceso a Datos
        closeApp();

        // Cerrar Interfaz
        UtilesSwing.terminarPrograma(this);
    }

    // Cierre de la Aplicación
    public final void closeApp() {
        // Cierre Base de Datos
        bs.closeApp();

        // Mensaje - Bitácora ( Comentar en producción )
        System.out.println("---");
        System.out.println("Bitácora: Acceso a Datos Finalizado");
    }
    //</editor-fold>

    // Componentes
    private JPanel pnlPpal;

    // Fuentes
    private Font fntPpal;

    // Imágenes
    private Image imgBack;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Plantilla N Capas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(816, 539));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        procesarCierreVentana(evt);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
