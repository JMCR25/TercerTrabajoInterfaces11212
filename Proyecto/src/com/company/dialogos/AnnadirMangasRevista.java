package com.company.dialogos;

import com.company.base.Autor;
import com.company.base.Manga;
import com.company.base.Revista;
import com.company.mvc.modelo.Modelo;
import com.company.util.Util;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 * @author Jose Maria Cenador Ramirez
 * @version 3.0
 * @since 2021
 */
public class AnnadirMangasRevista extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button1;
    private JList listMangas;
    private JLabel lblRevista;
    private DefaultListModel<Manga>dlmMangas;
    private ArrayList<Manga> mangas;
    private Revista revista;
    /**
     * Este dialogo permite añadir mangas a la lista de mangas de la clase {@code Revista}
     * @param modelo Necesitamos la clase {@code Modelo} para invocar el metodo para añadir
     * @param revista Recibimos el objeto de clase {@code Revista} para saber a cual de las revistas de la clase
     * modelo añadir el/los mangas.
     */
    public AnnadirMangasRevista(Revista revista, Modelo modelo) {
        mangas = modelo.getMangas();
        this.revista=revista;
        getRootPane().setDefaultButton(buttonOK);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        dlmMangas=new DefaultListModel<>();
        listMangas.setModel(dlmMangas);
        listarMangas(modelo);
        addListeners(modelo);
        pack();
        setVisible(true);
    }

    private void addListeners( Modelo modelo) {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!listMangas.isSelectionEmpty()){
                    Manga manga = (Manga) listMangas.getSelectedValue();
                    revista.addPublicacion(manga);
                    modelo.addMangaRevista(revista);
                    listarMangas(modelo);
                    if(dlmMangas.size()==0)
                        dispose();
                }
                else
                    Util.mostrarDialogoError("");
            }
        });
    }

    private void listarMangas(Modelo modelo) {
        dlmMangas.clear();
        for(Manga manga: mangas){
            boolean annadir =true;
            for(Revista revista : modelo.getRevistas()){
                if(revista.getPublicaciones().contains(manga))
                    annadir=false;
            }
            if (annadir)
                dlmMangas.addElement(manga);
        }
    }

}
