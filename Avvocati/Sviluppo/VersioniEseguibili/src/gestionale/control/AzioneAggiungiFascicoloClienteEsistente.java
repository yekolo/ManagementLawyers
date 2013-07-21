/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.view.FinestraAggiungiFascicoloClienteEsistente;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author nicolafelicecapece
 */
public class AzioneAggiungiFascicoloClienteEsistente extends AbstractAction {

    private Controllo controllo;
    //private Log logger = LogFactory.getLog(this.getClass());

    public AzioneAggiungiFascicoloClienteEsistente(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Nuovo Fascicolo");
        this.putValue(Action.SHORT_DESCRIPTION, "Crea un nuovo fascicolo");
    }

    public void actionPerformed(ActionEvent ae) {
        //logger.info("Sono nell'action performed");
        FinestraAggiungiFascicoloClienteEsistente fafce = (FinestraAggiungiFascicoloClienteEsistente) this.controllo.getVista().getSottoVista(Constants.FINESTRA_AGGIUNGI_FASCICOLO_CLIENTE_ESISTENTE);
        fafce.riempiNome();
        fafce.setVisible(true);
    }

}
