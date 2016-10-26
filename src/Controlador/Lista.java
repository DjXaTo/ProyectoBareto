/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * Metodos para extraer datos de un JList
 * @author Manuel Guillermo Aguilar
 */
public class Lista {
    
    /**
     * Devuelve un ArrayList con el contenido de un JList
     * @param a Jlist 
     * @return ArrayList con el contenido
     */
    public static ArrayList<String> extraerElementos(JList a){
        ArrayList<String> ob=new ArrayList();
        for (int i=0; i<a.getModel().getSize(); i++){
            ob.add((String) a.getModel().getElementAt(i));
        }
        return ob;
    }
    
    /**
     * Extrae el contenido de un JList y lo introduce en un DefaultListModel
     * @param a JList
     * @return DefaultaListModel con los datos
     */
    public static DefaultListModel extraerDefaultList(JList a){
        DefaultListModel ob=new DefaultListModel();
        for (int i=0; i<a.getModel().getSize(); i++){
            ob.addElement(a.getModel().getElementAt(i));
        }
        return ob;
    }
    
    
}
