package demineur.gui;

import demineur.Demineur;
import demineur.Partie;
import demineur.tools.MyException;

/**
 *
 * @author alexis
 */
public class DemineurGui extends Demineur {
    private DemineurGuiVue _vue;
    private int niveau;

    public DemineurGui(){
        super();
        _vue = new DemineurGuiVue(this, _grille,_partie);
    }

    public void nouvellePartie() {
        super.initialiser();
        _partie = new Partie(this);
        _partie.setNiveau(niveau);
        _vue.dispose();
        _vue = new DemineurGuiVue(this,_grille,_partie);
    }

    public void nouvellePartie(int difficulte) throws MyException{
        niveau = difficulte;
        _grille = setupGrille(difficulte);
        nouvellePartie();
    }

    public void nouvellePartie(int lignes,int colonnes,int mines)throws MyException{
        niveau = Demineur.NIVEAU_PERSO;
        _grille = setupGrille(lignes,colonnes,mines);
        nouvellePartie();
    }

    public void decouvreCase(int x, int y) {
        _grille.decouvrirCase(x, y);
        _vue.get_grilleVue().decouvrirCase(x, y);
    }

    public void majDrapeau(int x, int y) {
        _grille.majDrapeau(x, y);
        _vue.get_grilleVue().poserDrapeau(x, y);
    }

    public void perdu(){
        _grille.partiePerdue();
        _vue.get_grilleVue().partiePerdue();
    }

    public void gagne(){
       if(_partie.isHighScore())
       {AjoutScore as = new AjoutScore(this);}

       try{ _partie.sauverScores();} catch(MyException me){me.show_erreur();}
        _grille.gagne();
    }
}
