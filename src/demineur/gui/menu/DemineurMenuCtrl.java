package demineur.gui.menu;

import demineur.*;
import demineur.gui.DemineurGui;
import demineur.tools.MyException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author alexis
 */
public class DemineurMenuCtrl implements ActionListener {
    private DemineurGui _demineur;
    private DemineurMenu _menu;
    private String _action;

    public DemineurMenuCtrl(DemineurMenu menu,DemineurGui d, String a){
        _demineur = d;
        _menu = menu;
        _action = a;
    }

    public void actionPerformed(ActionEvent e) {
        try{
            if(_action.equalsIgnoreCase("aide"))
                JOptionPane.showMessageDialog(new JFrame("Aide"), "Clic Clic pour découvrir des cases...","Aide",JOptionPane.PLAIN_MESSAGE);
            else if(_action.equalsIgnoreCase("about"))
                JOptionPane.showMessageDialog(new JFrame("A propos"), "Demineur en Java\n\nProgramme & GUI par:\n\nDeberg Alexis et Maxime Gaston\nIUT Orsay\nTP APP-1","A Propos",JOptionPane.PLAIN_MESSAGE);
            else if(_action.equalsIgnoreCase(DemineurMenu.QUITTER))
                System.exit(0);
            else if(_action.equalsIgnoreCase(DemineurMenu.NOUVELLE_PARTIE))
                _demineur.nouvellePartie();
            else if(_action.equalsIgnoreCase(DemineurMenu.FACILE))
                _demineur.nouvellePartie(Demineur.NIVEAU_FACILE);
            else if(_action.equalsIgnoreCase(DemineurMenu.MOYEN))
                _demineur.nouvellePartie(Demineur.NIVEAU_MOYEN);
            else if(_action.equalsIgnoreCase(DemineurMenu.DIFFICILE))
                _demineur.nouvellePartie(Demineur.NIVEAU_DIFFICILE);
            else if(_action.equalsIgnoreCase(DemineurMenu.PERSONALISER))
                _menu.choisirDimensions();
            else if(_action.equalsIgnoreCase("Scores"))
                _menu.meilleurs_scores();
        }
        catch(MyException me){
            me.show_erreur();
        }

    }
}
