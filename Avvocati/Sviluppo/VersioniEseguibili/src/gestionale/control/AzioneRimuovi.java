/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.model.ClienteAstratto;
import gestionale.model.Gestionale;
import gestionale.persistence.DAOSalva;
import gestionale.persistence.IDAOSalva;
import gestionale.view.PannelloPrincipale;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author nicolafelicecapece
 */
public class AzioneRimuovi extends AbstractAction {
        
    private Controllo controllo;

    public AzioneRimuovi(Controllo controllo) {
        this.controllo = controllo;
        //this.putValue(Action.NAME, "Rimuovi Cliente");
        this.putValue(Action.SHORT_DESCRIPTION, "Elimina Cliente");
    }

    public void actionPerformed(ActionEvent ae) {
        PannelloPrincipale pp = (PannelloPrincipale) this.controllo.getVista().getSottoVista(Constants.PANNELLO_PRINCIPALE);
        Gestionale gestionale = (Gestionale) this.controllo.getModello().getBean(Constants.GESTIONALE);
        int index = pp.indiceSeleziona();
        if (index >= 0) {
            ClienteAstratto cliente = gestionale.getClienteAstratto(index);
            gestionale.rimuoviCliente(cliente);
            this.controllo.getModello().putBean(Constants.GESTIONALE, gestionale);
            IDAOSalva idaos = new DAOSalva();
            idaos.salva(gestionale);
            pp.repaint();
        } else {
            this.controllo.getVista().schermoErrore("Attenzione seleziona un cliente da eliminare");
        }
    }
}
