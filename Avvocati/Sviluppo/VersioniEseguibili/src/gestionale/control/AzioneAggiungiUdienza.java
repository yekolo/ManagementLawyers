/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionale.control;

import gestionale.Constants;
import gestionale.model.Fascicolo;
import gestionale.model.Gestionale;
import gestionale.model.Udienza;
import gestionale.view.FinestraCausa;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Nicola
 */
public class AzioneAggiungiUdienza extends AbstractAction {
    
    private Controllo controllo;
    private Log logger = LogFactory.getLog(this.getClass());
    
    public AzioneAggiungiUdienza(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Nuova Udienza");
        this.putValue(Action.SHORT_DESCRIPTION, "Crea una nuova udienza");
    }

    public void actionPerformed(ActionEvent e) {
        FinestraCausa fc = (FinestraCausa) this.controllo.getVista().getSottoVista(Constants.FINESTRA_CAUSA);
        Fascicolo fascicolo = (Fascicolo) this.controllo.getModello().getBean(Constants.FASCICOLO);
        Gestionale gestionale = (Gestionale) this.controllo.getModello().getBean(Constants.GESTIONALE);
        Calendar data = fc.restituisciData();
            
        if (data != null) {
            Udienza udienza = new Udienza();
            udienza.setData(data);
            udienza.setExternId(fascicolo.getId());
            udienza.setId(gestionale.restituisciIdUdienza() + 1);
            logger.info("Udienza: " + udienza.toString());
            fascicolo.addUdienza(udienza);
            fc.visualizzaUdienze();
            //fc.pulisciCampi();
        } else {
            this.controllo.getVista().schermoErrore("Attenzione devi prima scegliere una data");
        }
    }
    
}
