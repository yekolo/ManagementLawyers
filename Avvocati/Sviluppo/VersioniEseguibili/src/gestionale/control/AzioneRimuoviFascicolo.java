/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.model.ClienteAstratto;
import gestionale.model.Fascicolo;
import gestionale.model.Gestionale;
import gestionale.persistence.DAOSalva;
import gestionale.persistence.IDAOSalva;
import gestionale.view.PannelloFascicoli;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author nicolafelicecapece
 */
public class AzioneRimuoviFascicolo extends AbstractAction {

    private Controllo controllo;

    public AzioneRimuoviFascicolo(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Rimuovi");
        this.putValue(Action.SHORT_DESCRIPTION, "Elimina un fascicolo");
    }

    public void actionPerformed(ActionEvent ae) {
        PannelloFascicoli pf = (PannelloFascicoli) this.controllo.getVista().getSottoVista(Constants.PANNELLO_FASCICOLI);
        Gestionale gestionale = (Gestionale) this.controllo.getModello().getBean(Constants.GESTIONALE);
        int index = pf.indiceSeleziona();
        if (index >= 0) {
            ClienteAstratto cliente = (ClienteAstratto) this.controllo.getModello().getBean(Constants.CLIENTE);
            Fascicolo fascicolo = cliente.getFascicolo(index);
            cliente.rimuoviFascicolo(fascicolo);
            this.controllo.getModello().putBean(Constants.GESTIONALE, gestionale);
            IDAOSalva idaos = new DAOSalva();
            idaos.salva(gestionale);
            pf.repaint();
        } else {
            this.controllo.getVista().schermoErrore("Attenzione seleziona un cliente da eliminare");
        }
    }

}
