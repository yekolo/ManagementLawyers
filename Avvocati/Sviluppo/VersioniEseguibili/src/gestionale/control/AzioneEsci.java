/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 *
 * @author Nicola
 */
public class AzioneEsci extends AbstractAction {

    private Controllo controllo;

    public AzioneEsci(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Esci");
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
        this.putValue(Action.SHORT_DESCRIPTION, "Esce dall'applicazione");
    }

    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

}
