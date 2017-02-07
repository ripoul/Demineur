package demineur.gui.menu;

import demineur.InterfaceDemineurMenu;
import demineur.Demineur;
import demineur.gui.DemineurGui;
import demineur.tools.MyException;
import demineur.Grille;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

/**
 *
 * @author alexis
 */
public class DemineurMenu extends JMenuBar implements InterfaceDemineurMenu {
        /**
     * Constante : intitulé de l'item nouvelle partie.
     */
    public static final String NOUVELLE_PARTIE = "Nouvelle partie";
    /**
     * Constante : intitulé de l'item permettant de choisir une partie facile.
     */
    public static final String FACILE = "Facile";
    /**
     * Constante : intitulé de l'item permettant de choisir une partie moyenne.
     */
    public static final String MOYEN = "Moyen";
    /**
     * Constante : intitulé de l'item permettant de choisir une partie difficile.
     */
    public static final String DIFFICILE = "Difficile";
    /**
     * Constante : intitulé de l'item permettant de choisir les dimensions et le nombre de mines de la grille.
     */
    public static final String PERSONALISER = "Personaliser";
    /**
     * Constante : intitulé de l'item quitter.
     */
    public static final String QUITTER = "Quitter";
    /**
     * Element "Nouvelle Partie" du menu "Partie".
     */
    private JMenuItem _nouvellePartie;
    /**
     * RadioButton correspondant au choix du niveau facile.
     */
    private JRadioButtonMenuItem _partieFacile;
    /**
     * RadioButton correspondant au choix du niveau moyen.
     */
    private JRadioButtonMenuItem _partieMoyen;
    /**
     * RadioButton correspondant au choix du niveau difficile.
     */
    private JRadioButtonMenuItem _partieDifficile;
    /**
     * Element "Personaliser" du menu "Partie".
     */
    private JMenuItem _personaliser;
    /**
     * Element "Quitter" du menu "Partie".
     */
    private JMenuItem _quitter;

    private JMenuItem _scores;


    DemineurGui _parent;

    public DemineurMenu(DemineurGui p){
       super();
       _parent = p;

        JMenu menuPartie = new JMenu("Partie");
        JMenu menuAide = new JMenu("?");
        this.add(menuPartie);
        this.add(menuAide);

        //Ajout des items du menu "Partie"
        _nouvellePartie = new JMenuItem(DemineurMenu.NOUVELLE_PARTIE);
        _nouvellePartie.addActionListener(new DemineurMenuCtrl(this,_parent,DemineurMenu.NOUVELLE_PARTIE));
        menuPartie.add(_nouvellePartie);

        menuPartie.add(new JSeparator());

        _partieFacile = new JRadioButtonMenuItem(DemineurMenu.FACILE);
        _partieFacile.setSelected(true);
        _partieFacile.addActionListener(new DemineurMenuCtrl(this,_parent,DemineurMenu.FACILE));
        menuPartie.add(_partieFacile);

        _partieMoyen = new JRadioButtonMenuItem(DemineurMenu.MOYEN);
        _partieMoyen.setSelected(false);
        _partieMoyen.addActionListener(new DemineurMenuCtrl(this,_parent,DemineurMenu.MOYEN));
        menuPartie.add(_partieMoyen);

        _partieDifficile = new JRadioButtonMenuItem(DemineurMenu.DIFFICILE);
        _partieDifficile.setSelected(false);
        _partieDifficile.addActionListener(new DemineurMenuCtrl(this,_parent,DemineurMenu.DIFFICILE));
        menuPartie.add(_partieDifficile);

        _personaliser = new JMenuItem(DemineurMenu.PERSONALISER);
        _personaliser.addActionListener(new DemineurMenuCtrl(this,_parent,DemineurMenu.PERSONALISER));
        menuPartie.add(_personaliser);
        menuPartie.add(new JSeparator());

        _quitter = new JMenuItem(DemineurMenu.QUITTER);
        _quitter.addActionListener(new DemineurMenuCtrl(this,_parent,DemineurMenu.QUITTER));
        menuPartie.add(_quitter);

        _scores = new JMenuItem("Meilleurs scores");
        _scores.addActionListener(new DemineurMenuCtrl(this,_parent,"Scores"));
        menuAide.add(_scores);

        //Ajout des items du menu "?"
        JMenuItem aide = new JMenuItem("Aide");
        JMenuItem about = new JMenuItem("A Propos");
        aide.addActionListener(new DemineurMenuCtrl(this,_parent,"Aide"));
        about.addActionListener(new DemineurMenuCtrl(this,_parent,"About"));
        menuAide.add(aide);
        menuAide.add(about);
    }

    public void nouvellePartie() {
        _parent.nouvellePartie();
    }

    public void nouvellePartiePerso(int lig,int col,int mines){
        try{
            _parent.nouvellePartie(lig, col, mines);
        }
        catch(MyException me){
            me.show_erreur();
        }
    }

    public Grille choixNiveau(int niveau) throws MyException {
        switch (niveau)
        {
            case Demineur.NIVEAU_FACILE:
                return new Grille(_parent,Grille.DEF_NB_LIGNES_FACILE, Grille.DEF_NB_COLONNES_FACILE, Grille.DEF_NB_MINES_FACILE);
            case Demineur.NIVEAU_MOYEN:
                return new Grille(_parent,Grille.DEF_NB_LIGNES_MOYEN, Grille.DEF_NB_COLONNES_MOYEN, Grille.DEF_NB_MINES_MOYEN);
            case Demineur.NIVEAU_DIFFICILE:
                return new Grille(_parent,Grille.DEF_NB_LIGNES_DIFFICILE, Grille.DEF_NB_COLONNES_DIFFICILE, Grille.DEF_NB_MINES_DIFFICILE);
            default:
                throw new MyException("Ce mode de difficulté n'existe pas.");
        }
    }

    public void choisirDimensions() {
        PersonaliserGrilleVue choixGrille = new PersonaliserGrilleVue(this);
    }

    public void meilleurs_scores(){
        VoirScores v = new VoirScores(_parent);
    }

    public void quitter() {
        _parent.quitter();
    }

}
