package com.company;


import com.company.mvc.gui.Controlador;
import com.company.mvc.gui.Vista;
import com.company.mvc.modelo.Modelo;
import com.company.util.Util;

import javax.swing.*;
import java.util.Locale;
/**
 * @author Jose Maria Cenador Ramirez
 * @version 3.0
 * @since 2021
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        Util.crearSiNoExisteDirectorioDatos();
        Locale.setDefault(Util.obtenerLocale());
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);

    }
}
