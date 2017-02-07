/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demineur.gui.menu;

import demineur.Score;
import demineur.gui.DemineurGui;
import demineur.tools.MyFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author alexis
 */
public class VoirScores extends MyFrame implements ActionListener{
    private DemineurGui _parent;
    private JButton btnRAZ;
    private JButton btnOk;
    
    public VoirScores(DemineurGui p){
        super("Meilleurs Scores");
        _parent = p;
        GridLayout layout = new GridLayout(0,3);
        FlowLayout bLayout = new FlowLayout();

        Dimension d = new Dimension(500,100);
        if(_parent.get_partie().getScores() != null){
            setLayout(layout); 
            for(Score s:_parent.get_partie().getScores()){
                add(new JLabel(s.getSNiveau()));
                add(new JLabel(""+s.getTemps()+" secondes"));
                add(new JLabel(s.getNom()));
            }
            btnRAZ = new JButton("Effacer");
            btnRAZ.addActionListener(this);
            add(btnRAZ);
        }
        else {
            add(new JLabel("Aucun score enregistré pour le moment"));
            setLayout(bLayout);
        }

        btnOk = new JButton("OK");
        btnOk.addActionListener(this);
        add(btnOk);
        
        setMinimumSize(d);
        this.pack();
        setProperties();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnRAZ){
            _parent.get_partie().effacerScores();
            dispose();
        }
        else if(e.getSource() == btnOk){
            dispose();
        }
    }
}
