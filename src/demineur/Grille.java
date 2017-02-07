package demineur;

import demineur.tools.MyException;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Maxime GASTON, Alexis DEBERG
 * @see Case
 */
public class Grille
{

    /**
     * Constante : nombre de lignes d'une partie facile
     */
    public static final int DEF_NB_LIGNES_FACILE = 20;
    /**
     * Constante : nombre de colonnes d'une partie facile
     */
    public static final int DEF_NB_COLONNES_FACILE = 20;
    /**
     * Constante : nombre de mines d'une partie facile
     */
    public static final int DEF_NB_MINES_FACILE = 25;
    /**
     * Constante : nombre de lignes d'une partie moyenne
     */
    public static final int DEF_NB_LIGNES_MOYEN = 20;
    /**
     * Constante : nombre de colonnes d'une partie moyen
     */
    public static final int DEF_NB_COLONNES_MOYEN = 20;
    /**
     * Constante : nombre de mines d'une partie moyen
     */
    public static final int DEF_NB_MINES_MOYEN = 70;
    /**
     * Constante : nombre de lignes d'une partie difficile
     */
    public static final int DEF_NB_LIGNES_DIFFICILE = 20;
    /**
     * Constante : nombre de colonnes d'une partie difficile
     */
    public static final int DEF_NB_COLONNES_DIFFICILE = 20;
    /**
     * Constante : nombre de mines d'une partie facile
     */
    public static final int DEF_NB_MINES_DIFFICILE = 125;
    /**
     * Constante correspondant au nombre maximum de lignes que l'on peut saisir.
     */
    public static final int NB_MAX_LIGNES = 25;
    /**
    * Constante correspondant au nombre maximum de colonnes que l'on peut saisir.
     */
    public static final int NB_MAX_COLONNES = 25;
    /**
     * Constante correspondant au nombre maximum de mines que l'on peut saisir.
     */
    public static final int NB_MAX_MINES = 200;
    /**
     * Tableau 2D de Case constituant le principal composant du jeu.
     */
    private Case[][] _grille;
    /**
     * Nombre de lignes de la grille.
     */
    private int _nbLignes;
    /**
     * Nombre de colonnes de la grille.
     */
    private int _nbColonnes;
    /**
     * Nombre de mines de la grille.
     */
    private int _nbMines;
    /**
     * Nombre de drapeaux posés sur la grille.
     */
    private int _nbDrapeaux = 0;

    private Demineur _controlleur;

    public Grille(Demineur ctrl)
    {
        _controlleur = ctrl;
        _nbLignes = DEF_NB_LIGNES_FACILE;
        _nbColonnes = DEF_NB_COLONNES_FACILE;
        _nbMines = DEF_NB_MINES_FACILE;
        _grille = new Case[_nbLignes][_nbColonnes];
    }
    /**
     * Constructeur avec paramètres.
     * @param nbLignes nombre de lignes
     * @param nbColonnes nombre de colonnes
     * @param nbMines nombre de mines
     */
    public Grille(Demineur ctrl, int nbLignes, int nbColonnes, int nbMines) throws MyException
    {
        if (nbLignes <= 0 || nbLignes > NB_MAX_LIGNES)
            throw new MyException("Erreur : nombre de lignes erroné");
        else if(nbColonnes <= 0 || nbColonnes > NB_MAX_COLONNES)
            throw new MyException("Erreur : nombre de colonnes erroné");
        else if(nbMines <= 0 || nbMines > NB_MAX_MINES)
            throw new MyException("Erreur : nombre de mines erroné");
        else if(nbMines >= (nbLignes * nbColonnes))
            throw new MyException("Erreur : autant de mines que de cases");
        else{
            _controlleur = ctrl;
            _nbLignes = nbLignes;
            _nbColonnes = nbColonnes;
            _nbMines = nbMines;
            _grille = new Case[_nbLignes][_nbColonnes];
        }
    }

    /**
     * Méthode permettant de remplir la grille de cases en plaçant aléatoirement les n mines.
     */
    public void initialiser()
    {
        Random r = new Random();
        Vector<Point> indexMines = new Vector<Point>();
        int x, y;
        // tirage aléatoire sans remise de _nbMines chiffres qui seront les index des cases minées
        for (int i = 0; i < this._nbMines; i++)
        {
            Point pt;
            do
            {
                pt = new Point(r.nextInt(this._nbLignes), r.nextInt(this._nbColonnes));
            } while (indexMines.indexOf(pt) != -1);
            indexMines.addElement(pt);
        }
        //initialisation des cases
        for (int i = 0; i < this._nbLignes; i++)
        {
            for (int j = 0; j < this._nbColonnes; j++)
            {
                if (indexMines.indexOf(new Point(i, j)) != -1) // si l'indice est dans indiceMines
                {
                    this._grille[i][j] = new Case(true); // ajout d'une case minée
                } else
                {
                    this._grille[i][j] = new Case(false); // ajout d' une case autre.
                }
            }
        }
        this.set_nbMinesAProximite();
    }

    private void set_nbMinesAProximite(){
        for(int i=0;i<this._nbLignes;i++){
            for(int j=0;j<this._nbColonnes;j++){
                if(!_grille[i][j].get_mine())
                    _grille[i][j].set_nbMinesProximite(this.nbMinesCase(i, j));
            }
        }
    }

    /**
     * Méthode retournant le nombre de mines à proximité de la case dont les coordonnées sont passées en paramètre.
     * @param x indice de la ligne de la case
     * @param y indice de la colonne de la case
     * @return nombre de mines à proximité
     */
    private int nbMinesCase(int x, int y){
        int nbMines = 0;
        for(int i=x-1;i<=x+1;i++){
            for(int j=y-1;j<=y+1;j++){
                if(i>=0 && i<this._nbLignes && j>=0 && j<this._nbColonnes){
                    if(_grille[i][j].get_mine()) nbMines++;
                }
            }
        }

        return nbMines;
    }

    /**
     * Méthode permettant de connaitre le nombre de cases de la grille
     * @return nombre de cases de la grille (lignes * colonnes)
     */
    public int nbCases()
    {
        return (this._nbLignes * this._nbColonnes);
    }

    /**
     * Méthode de connaitre le nombre de drapeaux posées sur la grille.
     * @return
     */
    public int get_nbDrapeaux()
    {
        return this._nbDrapeaux;
    }

    public void majDrapeau(int x, int y)
    {
        if (this._grille[x][y].get_drapeau() == true)
        {
            this._grille[x][y].set_drapeau(false);
            this._nbDrapeaux--;
        } else
        {
            this._grille[x][y].set_drapeau(true);
            this._nbDrapeaux++;
        }
    }

    public void decouvrirCase(int x,int y){
        _grille[x][y].decouvrir();
    }

    public int get_nbColonnes(){
        return this._nbColonnes;
    }

    public int get_nbLignes(){
        return this._nbLignes;
    }

    public Case get_case(int x, int y){
        return _grille[x][y];
    }

    public int get_nbMines(){
        return _nbMines;
    }

    public void partiePerdue(){
        toutDecouvrir();
    }

    public int get_nbCasesDecouvertes(){
        int n = 0;
        for(int i=0;i<_nbLignes;i++){
            for(int j=0;j<_nbColonnes;j++)
                if(_grille[i][j].get_decouvert()) n++;
        }
        return n;
    }

    private void toutDecouvrir(){
        for(int i=0;i<this._nbLignes;i++){
            for(int j=0;j<this._nbColonnes;j++){
                if(!_grille[i][j].get_decouvert())
                    decouvrirCase(i,j);
            }
        }
    }

    public void gagne(){
        toutDecouvrir();
    }

    public void decouvrirAutour(int x, int y){
        if(_grille[x][y].get_nbMinesProximite() == 0){
            for(int i=-1;i<=1;i++){
                for(int j=-1;j<=1;j++){
                    if((x+i)>= 0 && (x+i)<_nbLignes && (y+j)>=0 && (y+j)<_nbColonnes){
                        if(
                            _grille[x+i][y+j].get_nbMinesProximite() == 0 &&
                            !_grille[x+i][y+j].get_decouvert() &&
                            !_grille[x+i][y+j].get_mine()
                           )
                        {
                            decouvrirCase(x+i,y+j);
                            decouvrirAutour(x+i,y+j);
                        }
                        else if(!_grille[x+i][y+j].get_mine() && !_grille[x+i][y+j].get_decouvert()){
                            decouvrirCase(x+i,y+j);
                        }
                    }
                }
            }
        }
    }

    /**
     * Méthode permettant l'affichage formaté dans un terminal.
     * @return chaine de caractère formatée décrivant la grille.
     */
    @Override
    public String toString()
    {
        String grille = "";
        for (int i = 0; i < this._nbLignes; i++)
        {
            for (int j = 0; j < this._nbColonnes; j++)
            {
                grille += "" + this._grille[i][j] + "\t";
            }
            grille += "\n";
        }

        return grille;
    }
}
