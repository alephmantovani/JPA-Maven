package com.Controler;

import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Aleph Mantovani
 */
public class Utilidades {
    
    //Limpar todos os campos do painel Inserir
    public void limparTodosCampos(JPanel comp){
        Component c[] = comp.getComponents();
        for(Component component: c){
            if(component instanceof JTextField){
                System.out.println("Text Name: " + ((JTextField) component).getName());
                ((JTextField) component).setText(null);
                if(((JTextField) component).getName().equals("nome")){
                    ((JTextField) component).requestFocus();
                }
            }
        }
    }
    
    //Inserir um Icone no Frame
    public void inserirIcone(JFrame frame){
        try {
//            frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/com/Icone/cad.png"));
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage("/com/Icone/cad.png"));
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
