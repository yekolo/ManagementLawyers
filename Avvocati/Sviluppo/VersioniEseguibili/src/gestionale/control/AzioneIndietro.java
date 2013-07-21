/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.view.PannelloPrincipale;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 *
 * @author Nicola
 */
public class AzioneIndietro extends AbstractAction {

    private Controllo controllo;

    public AzioneIndietro(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Indietro");
        this.putValue(Action.SHORT_DESCRIPTION, "Torna alla schermata precedente");
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl I"));
    }

    public void actionPerformed(ActionEvent e) {
        this.controllo.getVista().nascondiFascicoli();
        PannelloPrincipale pp = (PannelloPrincipale) this.controllo.getVista().getSottoVista(Constants.PANNELLO_PRINCIPALE);
        pp.pulisciPannello();
    }

}
