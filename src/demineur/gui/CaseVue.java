package demineur.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author alexis
 */
public class CaseVue extends JButton {
    public static final int WIDTH_CASE = 25;
    public static final int HEIGHT_CASE = 25;
    private int _posX;
    private int _posY;
    private Color _defaultColor;
    private final static Font _defaultFont = new Font("sansserif",Font.BOLD,12);
    private final ImageIcon imgMine = new ImageIcon(getClass().getResource("images/mine.png"));
    private final ImageIcon imgFlag = new ImageIcon(getClass().getResource("images/flag.png"));

    public CaseVue(int posX,int posY){
        super("");
        _posX = posX;
        _posY = posY;
        setMargin(new Insets(1,1,1,1));
        setPreferredSize(new Dimension(WIDTH_CASE,HEIGHT_CASE));
        _defaultColor = getBackground();
    }

    public void decouvrirVide(int num){
        Color c=null;
        setContentAreaFilled(false);
        if(num!= 0)setText(""+num);
        setFont(_defaultFont);
        switch(num){
            case 1: c= new Color(0,163,82); break;
            case 4: c=Color.ORANGE; break;
            case 5: c=Color.RED; break;
            case 6: c=Color.RED; break;
            case 7: c=Color.RED; break;
            case 8: c=Color.RED; break;
        }
        setForeground(c);
    }

    public void decouvrirMine(){
        setIcon(imgMine);
    }

    public void poserDrapeau(){
        setIcon(imgFlag);
    }

    public void enleverDrapeau(){
        setIcon(null);
    }
}
