/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.model.Gestionale;
import gestionale.persistence.DAOSalva;
import gestionale.persistence.IDAOSalva;
import gestionale.view.FinestraNuovoCliente;
import gestionale.view.PannelloPrincipale;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 *
 * @author nicolafelicecapece
 */
public class AzioneSalva extends AbstractAction {

    private Controllo controllo;

    public AzioneSalva(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Salva");
        this.putValue(Action.SHORT_DESCRIPTION, "Salva tutte le modifiche");
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
    }

    public void actionPerformed(ActionEvent ae) {
        IDAOSalva idaos = new DAOSalva();
        Gestionale gestionale = (Gestionale) this.controllo.getModello().getBean(Constants.GESTIONALE);
        idaos.salva(gestionale);
        PannelloPrincipale pp = (PannelloPrincipale) this.controllo.getVista().getSottoVista(Constants.PANNELLO_PRINCIPALE);
        pp.riempiTabella();
        FinestraNuovoCliente fnc = (FinestraNuovoCliente) this.controllo.getVista().getSottoVista(Constants.FINESTRA_NUOVO_CLIENTE);
        fnc.setStato(true);
        fnc.controllaSeSalvato();
        this.controllo.getVista().schermoInfo("Le modifiche sono state salvate correttamente");
    }

}
