package demineur.gui;

import demineur.gui.menu.DemineurMenu;
import demineur.Grille;
import demineur.Partie;
import demineur.tools.MyFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
/**
 *
 * @author alexis
 */
public class DemineurGuiVue extends MyFrame{

    /**
     * Attribut constant correspondant au nom de la fenêtre.
     */
    private static final String NOM_FENETRE = "Démineur";

    DemineurGui _controlleur;

    DemineurMenu _menu;
    GrilleVue _grilleVue;
    PartieVue _partieVue;

    public DemineurGuiVue(DemineurGui ctrl,Grille grille,Partie p){
        super(NOM_FENETRE);
        _controlleur = ctrl;

        _menu = new DemineurMenu(ctrl);
        _grilleVue = new GrilleVue(this,grille);
        _partieVue = new PartieVue(p);

        add(_menu,BorderLayout.NORTH);
        add(_partieVue);
        add(_grilleVue,BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(100,100));
        setProperties();
    }

    public GrilleVue get_grilleVue(){
        return _grilleVue;
    }

    public void start(){
        _partieVue.demarrer();
    }

    public boolean isStarted(){
        return _partieVue.isStarted();
    }

    public void perdu(){
        _partieVue.stop();
        _controlleur.perdu();
    }

    public void gagne(){
        _partieVue.stop();
        _controlleur.gagne();
        _grilleVue.decouvrirAutour(0, 0);
    }
}
