/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionale.control;

import gestionale.Constants;
import gestionale.model.Causa;
import gestionale.model.Fascicolo;
import gestionale.model.Gestionale;
import gestionale.model.Udienza;
import gestionale.persistence.DAOSalva;
import gestionale.persistence.IDAOSalva;
import gestionale.view.FinestraCausa;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author Nicola
 */
public class AzioneRimuoviUdienza extends AbstractAction {
    
    private Controllo controllo;
    
    public AzioneRimuoviUdienza(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Rimuovi");
        this.putValue(Action.SHORT_DESCRIPTION, "Elimina un' udienza");
    }

    public void actionPerformed(ActionEvent e) {
        Fascicolo fascicolo = (Fascicolo) this.controllo.getModello().getBean(Constants.FASCICOLO);
        FinestraCausa fc = (FinestraCausa) this.controllo.getVista().getSottoVista(Constants.FINESTRA_CAUSA);
        int index = fc.indiceSelezionaUdienza();
        if (index >= 0) {
            Udienza udienza = fascicolo.getUdienza(index);
            fascicolo.removeUdienza(udienza);
            IDAOSalva idaos = new DAOSalva();
            idaos.salva((Gestionale)this.controllo.getModello().getBean(Constants.GESTIONALE));
            fc.visualizzaUdienze();
        } else {
            this.controllo.getVista().schermoErrore("Attenzione devi selezionare prima un udienza");
        }
    }
    
}
