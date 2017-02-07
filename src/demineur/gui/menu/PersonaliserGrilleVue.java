package demineur.gui.menu;

import demineur.Grille;
import demineur.InterfaceChoixGrille;
import demineur.tools.MyException;
import demineur.tools.MyFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Maxime GASTON, Alexis DEBERG
 */
public class PersonaliserGrilleVue extends MyFrame implements InterfaceChoixGrille,ActionListener
{
    /**
     * Constante correspondant au nom de la fenêtre.
     */
    private static final String NOM_FENETRE = "Personaliser la grille";
    /**
     * Intitulé du message précédant la saisie du nombre de lignes.
     */
    private static final String LBL_LIGNES = "Nombre de lignes : ";
    /**
     * Intitulé du message précédant la saisie du nombre de colonnes.
     */
    private static final String LBL_COLONNES = "Nombre de colonnes : ";
    /**
     * Intitulé du message précédant la saisie du nombre de mines.
     */
    private static final String LBL_MINES = "Nombre de mines : ";
    /**
     * Champ de saisie du nombre de lignes.
     */
    private JTextField _nbLignes;
    /**
     * Champ de saisie du nombre de colonnes
     */
    private JTextField _nbColonnes;
    /**
     * Champ de saisie du nombre de mines.
     */
    private JTextField _nbMines;
    /**
     * Bouton valider permettant de vérifier la cohérence des données saisies et la création d'une grille correspondant.
     */
    private JButton _btnOk;
    /**
     * Bouton annuler permettant de revenir à la fenêtre principale.
     */
    private JButton _btnAnnuler;

    private DemineurMenu _parent;
    /**
     * Constructeur de la fenêtre permettant de choisir les dimensions d'une grille.
     */
    public PersonaliserGrilleVue(DemineurMenu m)
    {
        super(PersonaliserGrilleVue.NOM_FENETRE);
        _parent = m;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,2));
        this.add(panel);

        JLabel lblLignes = new JLabel(PersonaliserGrilleVue.LBL_LIGNES);
        JLabel lblColonnes = new JLabel(PersonaliserGrilleVue.LBL_COLONNES);
        JLabel lblMines = new JLabel(PersonaliserGrilleVue.LBL_MINES);

        _nbLignes = new JTextField(3);
        _nbColonnes = new JTextField(3);
        _nbMines = new JTextField(3);
        _btnAnnuler = new JButton("Annuler");
        _btnOk = new JButton("OK");

        panel.add(lblLignes); panel.add(_nbLignes);
        panel.add(lblColonnes); panel.add(_nbColonnes);
        panel.add(lblMines); panel.add(_nbMines);
        panel.add(new JLabel()); panel.add(new JLabel());
        panel.add(_btnAnnuler); panel.add(_btnOk);

        _btnOk.addActionListener(this);
        _btnAnnuler.addActionListener(this);
        setMinimumSize(new Dimension(300,150));
       setProprietes();
    }
    /**
     * Méthode permettant de définir les propriétés de la fenêtre.
     * @see MyFrame
     */
    private void setProprietes()
    {
        super.setProperties();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    /**
     * Méthode permettant de limiter grâce à des constantes les dimensions de la grille.
     * @throws message d'erreur en cas de grille aux dimensions trop grandes, nulles ou négatives.
     */
    public boolean verificationTaille()
    {
        if(_nbLignes.getText().isEmpty() || _nbColonnes.getText().isEmpty() || _nbMines.getText().isEmpty())
                return false;

        if ((Integer.parseInt(this._nbLignes.getText()) > Grille.NB_MAX_LIGNES) || (Integer.parseInt(this._nbLignes.getText()) <= 0)
                || (Integer.parseInt(this._nbColonnes.getText()) > Grille.NB_MAX_COLONNES) || (Integer.parseInt(this._nbColonnes.getText()) <= 0)
                || (Integer.parseInt(this._nbMines.getText()) > Grille.NB_MAX_MINES) || (Integer.parseInt(this._nbMines.getText()) <= 0))
        {
            return false;
        }

        return true;
    }
    /**
     * Méthode permettant de vérifier qu'il n'y a pas plus de mines que de cases.
     * @throws message d'erreur si plus de mines que de cases.
     */
    public boolean verificationNbMines()
    {
        if (Integer.parseInt(this._nbMines.getText()) > (Integer.parseInt(this._nbLignes.getText())) * (Integer.parseInt(this._nbColonnes.getText())))
        {
            return false;
        }

        return true;
    }
    /**
     * Méthode appelant les 2 méthodes de vérification et renvoyant la grille si les tests sont passés.
     * @return grille personalisée
     * @throws message d'erreur si tests échoués.
     */
    public boolean valider()
    {
        return (verificationTaille() && verificationNbMines());
    }

    public void annuler(){
        dispose();
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(_btnOk)){
            try{ 
                if(!valider())
                    throw new MyException("Dimensions incorrectes");
                else{
                    int lig = Integer.parseInt(_nbLignes.getText());
                    int col = Integer.parseInt(_nbColonnes.getText());
                    int mines = Integer.parseInt(_nbMines.getText());
                    _parent.nouvellePartiePerso(lig,col,mines);
                    dispose();
                }
            }catch(MyException me){ me.show_erreur(); }
        }
        else if(e.getSource().equals(_btnAnnuler)){
            annuler();
        }
    }
}
