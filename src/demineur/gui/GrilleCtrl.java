package demineur.gui;

import demineur.Grille;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author alexis
 */
public class GrilleCtrl implements MouseListener {
    private Grille _grille;
    private GrilleVue _grilleVue;
    private int _posX;
    private int _posY;
    private DemineurGuiVue _parentVue;

    public GrilleCtrl(DemineurGuiVue p,Grille g, GrilleVue gv, int posX, int posY){
        _grille = g;
        _grilleVue = gv;
        _posX = posX;
        _posY = posY;
        _parentVue = p;
    }

    public void mouseClicked(MouseEvent e) {
        if(!_parentVue.isStarted())
            _parentVue.start();

        if(e.getButton() == e.BUTTON1){
            _grilleVue.decouvrirCase(_posX, _posY);
            _grille.decouvrirCase(_posX, _posY);
            
            if(_grille.get_case(_posX, _posY).get_mine())
                partiePerdue();
            else{
                if(_grille.get_case(_posX, _posY).get_nbMinesProximite() == 0)
                    decouvrirAutour(_posX,_posY);
                //System.out.println(_grille.get_nbCasesDecouvertes());
                if(_grille.get_nbCasesDecouvertes() == _grille.nbCases()-_grille.get_nbMines())
                    partieGagnee();
                //System.out.println(""+_grille.get_nbCasesDecouvertes());
            }
        }
        else if(e.getButton() == e.BUTTON3)
            _grilleVue.poserDrapeau(_posX, _posY);
    }

    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }

    private void partiePerdue(){
        _parentVue.perdu();
        JOptionPane.showMessageDialog(new JFrame("Perdu"), "Vous avez perdu !");
    }

    private void partieGagnee(){
        _parentVue.gagne();
        JOptionPane.showMessageDialog(new JFrame("Gagné"), "Bravo !");
    }

    private void decouvrirAutour(int x,int y){
        _grille.decouvrirAutour(x,y);
        _grilleVue.decouvrirAutour(x,y);
    }
}
