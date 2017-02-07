/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demineur.gui;

import demineur.tools.MyException;
import demineur.tools.MyFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author alexis
 */
public class AjoutScore extends MyFrame implements ActionListener{
    DemineurGui _parent;
    JButton btnOk;
    JTextField nom;

    public AjoutScore(DemineurGui p){
        super("Nouveau meilleur score !");
        _parent = p;
        btnOk = new JButton("OK");
        btnOk.addActionListener(this);
        nom = new JTextField();

        setLayout(new GridLayout(5,1));
        add(new JLabel("Vous avez fait un très bon temps"));
        add(new JLabel("Entrez votre nom:"));
        add(new JLabel());
        add(nom);
        add(btnOk);

        setMaximumSize(new Dimension(300,200));
        setMinimumSize(new Dimension(300,200));
        setProperties();
    }

    public void actionPerformed(ActionEvent e) {
        String n = "Anonyme";
        if(e.getSource() == btnOk){
            if(!nom.getText().isEmpty())
                n = nom.getText();
            _parent.get_partie().addScore(n);
           try{ _parent.get_partie().sauverScores();}
           catch(MyException me){me.show_erreur();}
           dispose();
        }
    }
}
