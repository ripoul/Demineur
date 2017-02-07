package demineur.gui;

import demineur.Grille;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author alexis
 */
public class GrilleVue extends JPanel {
    private DemineurGuiVue _parent;
    private Grille _grille;
    private Color _defaultColor;
    private CaseVue[][] _buttons;

    public GrilleVue(DemineurGuiVue parent, Grille g){
        _grille = g;
        _parent = parent;

        GridLayout layout = new GridLayout(_grille.get_nbLignes(),_grille.get_nbColonnes());
        _buttons = new CaseVue[_grille.get_nbLignes()][_grille.get_nbColonnes()];
        setLayout(layout);

        for(int i=0;i<_grille.get_nbLignes();i++){
            for(int j=0;j<_grille.get_nbColonnes();j++){
                CaseVue b = new CaseVue(i,j);
                b.addMouseListener(new GrilleCtrl(_parent,_grille,this,i,j));
                _buttons[i][j] = b;
                this.add(_buttons[i][j]);
            }
        }

        _defaultColor = _buttons[0][0].getBackground();
        this.setMinimumSize(new Dimension(500,500));
    }

    private void removeClick(int x,int y){
        for(MouseListener m: _buttons[x][y].getMouseListeners())
                _buttons[x][y].removeMouseListener(m);
    }

    public void decouvrirCase(int x, int y){
        if(!_grille.get_case(x, y).get_drapeau()){
            _grille.get_case(x, y).decouvrir();
            removeClick(x,y);

            if(_grille.get_case(x, y).get_mine())
                _buttons[x][y].decouvrirMine();
            else
                _buttons[x][y].decouvrirVide(_grille.get_case(x,y).get_nbMinesProximite());
        }
    }

    public void poserDrapeau(int x, int y){
        if(!_grille.get_case(x, y).get_decouvert()){
            if(_grille.get_case(x, y).get_drapeau() == false){
                _grille.get_case(x, y).set_drapeau(true);
                _buttons[x][y].poserDrapeau();
            }
            else{
                _grille.get_case(x, y).set_drapeau(false);
                _buttons[x][y].enleverDrapeau();
            }
        }
    }

    public void partiePerdue(){
        toutDecouvrir();
    }

    private void toutDecouvrir(){
        for(int i=0;i<_grille.get_nbLignes();i++){
            for(int j=0;j<_grille.get_nbColonnes();j++){
                if(_grille.get_case(i, j).get_mine())
                    decouvrirCase(i,j);
                else{
                    _buttons[i][j].setEnabled(false);
                    removeClick(i,j);
                }
            }
        }
    }

    public void decouvrirAutour(int x,int y){
        for(int i=0;i<_grille.get_nbLignes();i++){
            for(int j=0;j<_grille.get_nbColonnes();j++){
                if(_grille.get_case(i, j).get_decouvert() && !_grille.get_case(x, y).get_mine())
                    decouvrirCase(i,j);
            }
        }
    }
}
