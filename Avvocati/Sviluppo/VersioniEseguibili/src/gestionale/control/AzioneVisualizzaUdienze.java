/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.model.ClienteAstratto;
import gestionale.model.Fascicolo;
import gestionale.view.FinestraCausa;
import gestionale.view.PannelloFascicoli;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author nicolafelicecapece
 */
public class AzioneVisualizzaUdienze extends AbstractAction {

    private Controllo controllo;

    public AzioneVisualizzaUdienze(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Cause");
        this.putValue(Action.SHORT_DESCRIPTION, "Visualizza l'elenco di cause");
    }

    public void actionPerformed(ActionEvent ae) {
        PannelloFascicoli pf = (PannelloFascicoli) this.controllo.getVista().getSottoVista(Constants.PANNELLO_FASCICOLI);
        FinestraCausa fc = (FinestraCausa) this.controllo.getVista().getSottoVista(Constants.FINESTRA_CAUSA);
        ClienteAstratto cliente = (ClienteAstratto) this.controllo.getModello().getBean(Constants.CLIENTE);
        int index = pf.indiceSeleziona();
        if (index >= 0) {
            Fascicolo fascicolo = cliente.getFascicolo(index);
            this.controllo.getModello().putBean(Constants.FASCICOLO, fascicolo);
            fc.visualizzaUdienze();
            fc.visualizzati(true);
        } else {
            this.controllo.getVista().schermoErrore("Attenzione seleziona un fascicolo");
        }
    }


}
