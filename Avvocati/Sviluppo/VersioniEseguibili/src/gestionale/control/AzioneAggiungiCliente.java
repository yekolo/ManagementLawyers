/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.view.FinestraNuovoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 *
 * @author nicolafelicecapece
 */
public class AzioneAggiungiCliente extends AbstractAction {

    private Controllo controllo;
    private FinestraNuovoCliente fnc;

    public AzioneAggiungiCliente(Controllo controllo) {
        this.controllo = controllo;
        //this.putValue(Action.NAME, "Nuovo Cliente");
        this.putValue(Action.SHORT_DESCRIPTION, "Apre la finestra di inserimento nuovo cliente");
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    public void actionPerformed(ActionEvent ae) {
        this.controllo.getVista().setInizializzaStato(2);
        fnc = (FinestraNuovoCliente) this.controllo.getVista().getSottoVista(Constants.FINESTRA_NUOVO_CLIENTE);
        fnc.controlloErrori();
        fnc.setStato(false);
        fnc.visualizzati(true);
        fnc.disabilitaInserimento(true);
        fnc.pulisciCampi();
        fnc.controllaStatoSalvataggio();
        fnc.controllaSeSalvato();

        controllaTipoCliente();
    }

    private void controllaTipoCliente() {
        fnc.modificaRadioButton();
    }
}
