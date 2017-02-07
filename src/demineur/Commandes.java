package demineur;

import demineur.tools.MyException;

/**
 *
 * @author alexis
 */
public interface Commandes {
    public void nouvellePartie();
    public void nouvellePartie(int difficulte)throws MyException;
    public void nouvellePartie(int lignes,int colonnes,int mines)throws MyException;
    public void decouvreCase(int x, int y);
    public void majDrapeau(int x, int y);
    public void quitter();
    public void perdu();
    public void gagne();
}
