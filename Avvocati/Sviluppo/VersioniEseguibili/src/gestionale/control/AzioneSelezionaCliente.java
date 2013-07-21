/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.model.ClienteAstratto;
import gestionale.model.Gestionale;
import gestionale.view.PannelloFascicoli;
import gestionale.view.PannelloPrincipale;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author nicolafelicecapece
 */
public class AzioneSelezionaCliente extends AbstractAction {

    private Controllo controllo;

    public AzioneSelezionaCliente(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Seleziona");
        this.putValue(Action.SHORT_DESCRIPTION, "Seleziona un cliente");
    }

    public void actionPerformed(ActionEvent ae) {
        PannelloPrincipale pp = (PannelloPrincipale) this.controllo.getVista().getSottoVista(Constants.PANNELLO_PRINCIPALE);
        PannelloFascicoli pf = (PannelloFascicoli) this.controllo.getVista().getSottoVista(Constants.PANNELLO_FASCICOLI);
        Gestionale gestionale = (Gestionale) this.controllo.getModello().getBean(Constants.GESTIONALE);
        int index = pp.indiceSeleziona();
        if (index >= 0) {
            ClienteAstratto cliente = gestionale.getClienteAstratto(index);
            this.controllo.getModello().putBean(Constants.CLIENTE, cliente);
            this.controllo.getVista().visualizzaFascicoli();
            pf.visualizzaDati();
        } else {
            this.controllo.getVista().schermoErrore("Attenzione, seleziona prima un cliente");
        }
    }

}
