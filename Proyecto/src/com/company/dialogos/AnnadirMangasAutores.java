package com.company.dialogos;

import com.company.base.Autor;
import com.company.base.Manga;
import com.company.mvc.modelo.Modelo;
import com.company.util.Util;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * @author Jose Maria Cenador Ramirez
 * @version 3.0
 * @since 2021
 */
public class AnnadirMangasAutores extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JList listMangas;
    private DefaultListModel<Manga> dlmMangas;
    private JButton botAddManga;
    private JLabel lblAutor;
    private ArrayList<Manga> mangas;
    private ResourceBundle resourceBundle;
    /**
     * Este dialogo permite añadir mangas a la lista de mangas de la clase {@code Autor}
     * @param modelo Necesitamos la clase {@code Modelo} para invocar el metodo para añadir
     * @param autor Recibimos el objeto de clase {@code Autor} para saber a que objeto añadir los mangas
     */
    public AnnadirMangasAutores(Autor autor, Modelo modelo) {
        resourceBundle=ResourceBundle.getBundle("language", Locale.getDefault());
        getRootPane().setDefaultButton(botAddManga);
        mangas = (ArrayList<Manga>) modelo.getMangas().clone();
        lblAutor.setText(autor.getNombre());
        setContentPane(contentPane);
        dlmMangas = new DefaultListModel<>();
        listMangas.setModel(dlmMangas);
        listarMangas(autor, modelo);
        getRootPane().setDefaultButton(buttonOK);
        setModal(true);
        addActions(modelo, autor);
        pack();
        setVisible(true);
    }

    private void listarMangas(Autor autor,Modelo modelo) {
        dlmMangas.clear();

        ArrayList<Integer> i = new ArrayList<>();
        for(Manga mang : autor.getPublicaciones()){
            int cont = 0;
            for(Manga manga : mangas){
                if(mang == manga)
                        i.add(cont);
                cont++;
            }
        }
        int resta = 0;
        for (int j : i){
            mangas.remove(j-resta);
            resta++;
        }
        for (Manga man : mangas) {
            dlmMangas.addElement(man);
        }
    }

    private void addActions(Modelo modelo, Autor autor) {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        botAddManga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!listMangas.isSelectionEmpty()){
                    Manga manga = (Manga) listMangas.getSelectedValue();
                    modelo.addAutorManga(autor.getIdAutor(),manga);
                    listarMangas(autor, modelo);
                }
                else
                    Util.mostrarDialogoError(resourceBundle.getString("mensaje.error.seleccion.autor"));
            }
        });


    }}
