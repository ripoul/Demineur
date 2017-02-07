package demineur;

/**
 * Classe permettant de définir des objets cases.
 * Il est possible de savoir si la case est minée ou non, si un drapeau est posé dessus ou non, ainsi que le nombre de mines à proximité de la case.
 * @author Maxime GASTON, Alexis DEBERG
 */
public class Case
{

    /**
     * Booléen permettant de savoir si un drapeau a été posé sur la case.
     */
    private boolean _drapeau = false;
    /**
     * Booléen permettant de savoir si la case est découverte.
     */
    private boolean _decouvert = false;
    /**
     * Booléen permettant de savoir si la case est minée.
     */
    private boolean _mine;
    /**
     * Nombre de mines à proximité de this.
     */
    private int _nbMinesProximite;

    /**
     * Constructeur d'une case.
     * @param mine true si la case est minée, false sinon.
     */
    public Case(boolean mine)
    {
        this._decouvert = false;
        this._drapeau = false;
        this._mine = mine;
    }

    /**
     * Getter pour l'attribut _decouvert.
     * @return true si la case est découverte false sinon.
     */
    public boolean get_decouvert()
    {
        return this._decouvert;
    }

    /**
     * Méthode permettant de découvrir la case s'il n'y a pas de drapeau dessus.
     */
    public void decouvrir()
    {
        if (this.get_drapeau() == false)
        {
            this._decouvert = true;
        }
    }

    /**
     * Getter pour l'attribut _drapeau.
     * @return true si un drapeau a été mis sur la case, false sinon.
     */
    public boolean get_drapeau()
    {
        return this._drapeau;
    }

    /**
     * Méthode permettant de mettre ou d'enlever un drapeau sur une case non découverte.
     * @param choix true si on veut mettre un drapeau, false si on veut l'enlever.
     */
    public void set_drapeau(boolean choix)
    {
        if (this.get_decouvert() == false)
        {
            this._drapeau = choix;
        }
    }

    /**
     * Getter pour l'attribut _mine.
     * @return true si la case est minée, false sinon.
     */
    public boolean get_mine()
    {
        return this._mine;
    }

    /**
     * Getter pour l'attribut _nbMinesProximite.
     * @return le nombre de mines à proximité de this.
     */
    public int get_nbMinesProximite()
    {
        return this._nbMinesProximite;
    }

    /**
     * Setter pour l'attribut _nbMinesProximite.
     * @param _nbMinesProximite nombre de mines à proximité de la mine.
     */
    public void set_nbMinesProximite(int _nbMinesProximite)
    {
        this._nbMinesProximite = _nbMinesProximite;
    }

    /**
     * Méthode permettant d'afficher une case dans un terminal.
     * @return "CASE MINÉE" si _mine vaut true, "CASE  OK " sinon.
     */
    @Override
    public String toString()
    {
        if(_drapeau) return "!";
        else if(_decouvert != true) return "?";
        else if (get_mine() == true) return "X";
        else if(_nbMinesProximite > 0) return ""+_nbMinesProximite;
        else return ".";
    }
}
