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

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.japo.java.components.BackgroundPanel;
import org.japo.java.entities.Credencial;
import org.japo.java.events.WEM;
import org.japo.java.exceptions.ConnectivityException;
import org.japo.java.layers.services.S2Bussiness;
import org.japo.java.layers.services.S1User;
import org.japo.java.libraries.UtilesSwing;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
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

    // Propiedades Resources
    public static final String PRP_RESOURCE_FONT = "resource.font";
    public static final String PRP_RESOURCE_IMG = "resource.img";
    public static final String PRP_RESOURCE_IMG_FAVICON = "resource.img.favicon";

    // Propiedades Aplicación
    private final Properties prp;

    // Gestor Lógica de Negocio
    private final S2Bussiness bs;

    // Constructor Parametrizado
    public M1User(Properties prp, S2Bussiness bs) {
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

        // Evaluación del modo de conexión
        if (mode != null && mode.equals("login")) {
            // ---
        }

        // Creación de Entidad Credencial
        Credencial c = new Credencial(user, pass);

        // Conexión de Credenciales
        bs.loginApp(c);

        // Mensaje - Bitácora ( Comentar en producción )
//        System.out.println("Patrón de Diseño Estructural de Capas Funcionales");
//        System.out.println("=================================================");
        System.out.println("Bitácora: Acceso a Datos Establecido");
        System.out.println("---");
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
        System.out.println("Bitácora: Acceso a Datos Finalizado");

        // Despedida
//        System.out.println("---");
//        System.out.println("Copyright JAPO Labs - Servicios Informáticos");
    }

    // Interfaz - Inicialización Previa
    private void initBefore() {
        // Establecer LnF
        UtilesSwing.establecerLnFProfile(prp.getProperty(PRP_FORM_LNF_PROFILE));

        // Fuentes
//        fntPpal = UtilesSwing.generarFuenteRecurso(prp.getProperty(PRP_RESOURCE_FONT));
        // Imágenes
        imgBack = UtilesSwing.importarImagenRecurso(prp.getProperty(PRP_RESOURCE_IMG));

        // Panel Principal
        pnlPpal = new BackgroundPanel(imgBack);

        // Ventana Principal
        setContentPane(pnlPpal);
    }

    // Interfaz - Inicialización Posterior
    private void initAfter() {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty(PRP_RESOURCE_IMG_FAVICON));
    }
    //</editor-fold>

    // Componentes
    private JPanel pnlPpal;

    // Fuentes
//    private Font fntPpal;
    
    // Imágenes
    private Image imgBack;

    // Interfaz - Construcción
    private void initComponents() {
        // Panel Principal
        pnlPpal = new BackgroundPanel(imgBack);

        // Ventana Principal
        setTitle(prp.getProperty(PRP_FORM_TITLE));
        setContentPane(pnlPpal);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WEM(this));
    }

    // ---
    public void procesarAccion(ActionEvent e) {
        // ---
    }

    public void procesarTeclado(KeyEvent e) {
        // ---
    }

    public void procesarRaton(MouseEvent e) {
        // ---
    }

    public void procesarArrastre(MouseEvent e) {
        // ---
    }
}
