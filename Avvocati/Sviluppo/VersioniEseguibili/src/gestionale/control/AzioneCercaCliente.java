/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.model.Gestionale;
import gestionale.view.PannelloFascicoli;
import gestionale.view.PannelloPrincipale;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author Nicola
 */
public class AzioneCercaCliente extends AbstractAction {

    private Controllo controllo;
    //private Log logger = LogFactory.getLog(this.getClass());

    public AzioneCercaCliente(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Cerca Cliente");
        this.putValue(Action.SHORT_DESCRIPTION, "Cerca un cliente");
    }

    public void actionPerformed(ActionEvent e) {
       Gestionale gestionale = (Gestionale) this.controllo.getModello().getBean(Constants.GESTIONALE);
       PannelloPrincipale pp = (PannelloPrincipale) this.controllo.getVista().getSottoVista(Constants.PANNELLO_PRINCIPALE);
       PannelloFascicoli pf = (PannelloFascicoli) this.controllo.getVista().getSottoVista(Constants.PANNELLO_FASCICOLI);
       if (!pp.ottieniTesto().equals("")) {
           if (gestionale.cercaCliente(pp.ottieniTesto()) != null) {
               this.controllo.getModello().putBean(Constants.CLIENTE, gestionale.cercaCliente(pp.ottieniTesto()));
               this.controllo.getVista().visualizzaFascicoli();
               pf.visualizzaDati();
           } else {
               this.controllo.getVista().schermoErrore("Cliente inesistente");
           }
       } else {
           this.controllo.getVista().schermoErrore("Attenzione inserisci il nome di un cliente da ricercare");
       }
    }

}
