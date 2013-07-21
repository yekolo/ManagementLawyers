/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.view.FinestraNuovoCliente;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 *
 * @author nicolafelicecapece
 */
public class AzioneIndietroFinestraAC extends AbstractAction {

    private Controllo controllo;

    public AzioneIndietroFinestraAC(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Indietro...");
        this.putValue(Action.SHORT_DESCRIPTION, "Torna alla schermata precedente");
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl I"));
    }

    public void actionPerformed(ActionEvent ae) {
        FinestraNuovoCliente fnc = (FinestraNuovoCliente) this.controllo.getVista().getSottoVista(Constants.FINESTRA_NUOVO_CLIENTE);
        fnc.pulisciCampi();
        fnc.abilitaFascicolo(false);
        fnc.visualizzati(false);
    }

}
