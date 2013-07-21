/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.view;

import gestionale.Constants;
import gestionale.control.AzioneAggiungiCliente;
import gestionale.control.AzioneEsci;
import gestionale.control.AzioneIndietro;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author nicolafelicecapece
 */
public class PopupMenu extends JPopupMenu {

    private JMenuItem item1;
    private JMenuItem item2;
    private Vista vista;
    //private Log logger = LogFactory.getLog(this.getClass());

    public PopupMenu(Vista vista) {
        this.vista = vista;
        scegliMenu();
    }

    private void scegliMenu() {
        if (this.vista.getInizializzaStato() == 1) {
            inizializzaItem();
        } else if (this.vista.getInizializzaStato() == 2) {
            inizializzaItem2();
        }
    }

    private void inizializzaItem() {
        item1 = new JMenuItem();
        AzioneEsci ae = (AzioneEsci) this.vista.getControllo().getAzione(Constants.AZIONE_ESCI);
        item1.setAction(ae);
        add(item1);
        item2 = new JMenuItem();
        AzioneAggiungiCliente aac = (AzioneAggiungiCliente) this.vista.getControllo().getAzione(Constants.AZIONE_AGGIUNGI_CLIENTE);
        item2.setAction(aac);
        item2.setText("Nuovo Cliente");
        add(item2);
    }

    private void inizializzaItem2() {
        item1 = new JMenuItem();
        AzioneIndietro ai = (AzioneIndietro) this.vista.getControllo().getAzione(Constants.AZIONE_INDIETRO);
        item1.setAction(ai);
        add(item1);
    }
}
