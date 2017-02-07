/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demineur;

import java.io.Serializable;

/**
 *
 * @author alexis
 */
public class Score implements Comparable<Score>,Serializable{
    private String nom;
    private int temps;
    private int niveau;
    private String sNiveau;

    public Score(String nom,int niveau,int temps){
        this.nom = nom;
        this.temps = temps;
        this.niveau = niveau;
        sNiveau = strNiveau();
        //System.out.println(""+niveau+" / "+sNiveau);
    }

    public String getNom(){
        return nom;
    }

    public int getTemps(){
        return temps;
    }

    public int getNiveau(){
        return niveau;
    }

    public int compareTo(Score o) {
        if(temps < o.temps) return -1;
        else if(temps > o.temps) return 1;
        else return 0;
    }

    public String getSNiveau(){
        return sNiveau;
    }

    public String strNiveau(){
        String str = "";
        switch(niveau){
            case Demineur.NIVEAU_FACILE: str = "Facile"; break;
            case Demineur.NIVEAU_MOYEN: str = "Moyen"; break;
            case Demineur.NIVEAU_DIFFICILE: str = "Difficile"; break;
            case Demineur.NIVEAU_PERSO: str = "Personnalisé"; break;
        }

        return str;
    }

    @Override
    public String toString(){
        return(sNiveau+":   "+nom+"     "+temps);
    }
}
