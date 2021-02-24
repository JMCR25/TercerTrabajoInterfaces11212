package com.company.util;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
/**
 * @author Jose Maria Cenador Ramirez
 * @version 3.0
 * @since 2021
 */
public class Util {
	
	public static final int ACEPTAR = JOptionPane.OK_OPTION;
	public static final int CANCELAR = JOptionPane.CANCEL_OPTION;

	public static void crearSiNoExisteDirectorioDatos(){
		File directorio = new File("datos");
		if(!directorio.exists()) {
			directorio.mkdir();
		}
	}

	public static void mostrarDialogoError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);	
	}
	public static int mostrarDialogoConfirmacion(String mensaje) {
		return JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
	}
	public static Locale obtenerLocale() {
		Properties properties= new Properties();
		try {
			properties.load(new FileReader("datos/preferencias.conf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Locale local = new Locale(properties.getProperty("idioma"), properties.getProperty("pais"));
		return local;
	}

}
