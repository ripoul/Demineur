package demineur;

import demineur.tools.MyException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author alexis
 */
public class Partie{
    private Demineur _demineur;
    private ArrayList<Score> scores = null;

    private int niveau;
    private int _temps = 0;
    private boolean _gagne = false;
    public final static int DELAY = 1000;

    public Partie(Demineur d){
        _demineur = d;
        try{lireScores();} catch(MyException me){me.show_erreur();}
    }

    public int get_temps(){
        return _temps;
    }

    public void majTemps(){
        _temps++;
    }

    public boolean get_resultat(){
        return _gagne;
    }

    public void setNiveau(int n){
        niveau = n;
    }

    public int getNiveau(){
        return niveau;
    }

    public void gagne(){
        _gagne = true;
    }

    public void addScore(String nom){
        Score player = new Score(nom,niveau,_temps);
        if(scores == null)
            scores = new ArrayList<Score>();
        
        if(
            scores != null &&
            scores.size() > 0 &&
            scores.size() <= 10 &&
            player.compareTo(scores.get(scores.size()-1)) >= 1
        )
            scores.add(player);
        else
            scores.add(player);

        Collections.sort(scores);
    }

    public ArrayList<Score> getScores(){
        return scores;
    }

    public boolean isHighScore(){
        Score s = new Score("Test",niveau,_temps);
        
        boolean ok = false;
        if(getScores()!=null){
            for(Score c: getScores())
                if(c.compareTo(s) == -1) ok = true;
        }else ok = true;
        return ok;
    }


    public void sauverScores()throws MyException{
        if(scores != null){
            FileOutputStream f_out = null;
            try{f_out = new FileOutputStream("./scores");}
            catch(FileNotFoundException fnf){fnf.printStackTrace();}

            try{ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
                obj_out.writeObject(scores);
                obj_out.flush();
                obj_out.close();
            }catch(IOException ioe){ioe.printStackTrace();}
        }
//        System.out.println(scores);
    }

    public void lireScores() throws MyException{
        FileInputStream f_in = null;

        try{f_in = new FileInputStream("./scores");

            try{ObjectInputStream obj_in = new ObjectInputStream(f_in);
            ArrayList<Score> tmp_scores = (ArrayList<Score>)obj_in.readObject();
            scores = tmp_scores;
            }
            catch(IOException ioe){ioe.printStackTrace();}
            catch(ClassNotFoundException cnf){cnf.printStackTrace();}
        }
        catch(FileNotFoundException fnf){}
    }

    public void effacerScores(){
        scores = null;
        File f = new File("./scores");
        if(f.exists() && f.canWrite() && !f.isDirectory())
            if(!f.delete()) JOptionPane.showInputDialog(new JLabel("Erreur de suppression"));
    }
}
