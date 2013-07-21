/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.model.CalcolaCodiceFiscale;
import gestionale.persistence.DAOCliente;
import gestionale.view.FinestraNuovoCliente;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author nicolafelicecapece
 */
public class AzioneCodiceFiscale extends AbstractAction {

    private Controllo controllo;
    private Log logger = LogFactory.getLog(this.getClass());

    public AzioneCodiceFiscale(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Codice fiscale");
        this.putValue(Action.SHORT_DESCRIPTION, "Calcola il codice fiscale");
    }

    public void actionPerformed(ActionEvent ae) {
        FinestraNuovoCliente fnc = (FinestraNuovoCliente) this.controllo.getVista().getSottoVista(Constants.FINESTRA_NUOVO_CLIENTE);
        String luogoNascita = fnc.restituisciLuogoNascita();
        String provincia = fnc.restituisciProvNascita();
        String dataNascita = "";
        if (fnc.restituisciDataNascita() != null) {
            DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
            dataNascita = df.format(fnc.restituisciDataNascita().getTime());
        } else {
            this.controllo.getVista().schermoErrore("Attenzione inserisci una data di nascita");
        }
        String sesso = fnc.getSesso();
        logger.info("Valore di sesso: " + sesso);
        String nome = fnc.restituisciNome();
        String cognome = fnc.restituisciCognome();

        if (!luogoNascita.equals("") && !provincia.equals("") && !nome.equals("") && !cognome.equals("")) {
            String codiceCatastale = DAOCliente.comuniItaliani(luogoNascita, provincia);

            if (codiceCatastale != null) {
                CalcolaCodiceFiscale ccf = new CalcolaCodiceFiscale();
                try {
                    String codiceFiscale = ccf.calcola(nome, cognome, dataNascita, codiceCatastale, sesso);
                    fnc.riempiCodFisc(codiceFiscale);
                } catch (Exception ex) {
                    logger.info("Exception: " + ex.getMessage());
                }
            } else {
                this.controllo.getVista().schermoErrore("Attenzione controlla il luogo di nascita");
            }
           
        } else {
            this.controllo.getVista().schermoErrore("I campi Nome, Cognome, Luogo di nascita, Provincia e Data di nascita devono essere riempiti");
        }
    }
}
