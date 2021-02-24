package com.company.dialogos;

import com.company.base.Autor;
import com.company.base.Manga;
import com.company.mvc.gui.Controlador;
import com.company.mvc.modelo.Modelo;
import com.company.util.Util;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * @author Jose Maria Cenador Ramirez
 * @version 3.0
 * @since 2021
 */
public class AnnadirAutor extends JDialog{
    JTextField textNombreAutor;
    JTextField textApellidoAutor;
    JButton botCancel;
    JButton botAddAutores;
    DatePicker dateFechaNacAutor;
    JPanel panelAutor;
    private JTextField txtAutorId;
    JSpinner spinner1;


    /**
     * Este dialogo permite añadir autores a la lista de autores de la clase {@code Modelo}
     * @param modelo Necesitamos la clase {@code Modelo} para invocar el metodo para añadir
     * @param controlador Recibimos el objeto de clase {@code Controlador} con la unica intencion de listar
     */
    public AnnadirAutor(Modelo modelo, Controlador controlador) {
        setContentPane(panelAutor);
        setResizable(false);
        setModal(true);
        addKeyListener();
        getRootPane().setDefaultButton(botAddAutores);
        botCancel.setMnemonic(KeyEvent.VK_ESCAPE);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        //he tenido que ajustar la posicion de la ventana ya que por defecto se colocaba en la esquina superior izquierda
        setSize(width/4, height/4);
        setLocationRelativeTo(null);
        addActionListeners(modelo, controlador);
        pack();
        setVisible(true);

    }

    private void addKeyListener() {
        txtAutorId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    int num=Integer.parseInt(txtAutorId.getText());
                    if(num<=0)
                        Integer.parseInt("error");
                } catch (Exception ex) {
                    txtAutorId.setText("1");
                }
            }
        });
    }


    private void addActionListeners(Modelo modelo, Controlador controlador) {
        botAddAutores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textNombreAutor.getText();
                String apellido = textApellidoAutor.getText();
                int id = Integer.valueOf(txtAutorId.getText());
                LocalDate fecha = dateFechaNacAutor.getDate();
                if(!nombre.replace(" ","").equals("")&&!apellido.replace(" ","").equals("")&&fecha!=null){
                Autor autor = new Autor(nombre,id, fecha,new ArrayList<Manga>(),apellido);
                modelo.addAutores(autor);
                controlador.listarAutores();
                dispose();}
                else
                    Util.mostrarDialogoError(ResourceBundle.getBundle("language").getString("mensaje.error.rellenar.campos"));
                }

        });
        botCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
        });
    }
}
