/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.model.ClienteAstratto;
import gestionale.model.Fascicolo;
import gestionale.model.Gestionale;
import gestionale.view.FinestraNuovoCliente;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author nicolafelicecapece
 */
public class AzioneAggiungiFascicolo extends AbstractAction {

    private Controllo controllo;
    private FinestraNuovoCliente fnc;
    private ClienteAstratto cliente;
    private Gestionale gestionale;
    private Fascicolo fascicolo;
    private List<Boolean> listaBooleani = new ArrayList<Boolean>();

    public AzioneAggiungiFascicolo(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Nuovo Fascicolo");
        this.putValue(Action.SHORT_DESCRIPTION, "Inserisce la causa creata");
    }

    public void actionPerformed(ActionEvent ae) {
        fnc = (FinestraNuovoCliente) this.controllo.getVista().getSottoVista(Constants.FINESTRA_NUOVO_CLIENTE);
        cliente = (ClienteAstratto) this.controllo.getModello().getBean(Constants.CLIENTE);
        gestionale = (Gestionale) this.controllo.getModello().getBean(Constants.GESTIONALE);
        fascicolo = new Fascicolo();
        
        int id = cliente.numeroFascicoli() + 1;

        if (!fnc.restituisciRGGIP().equals("") || !fnc.restituisciRGNR().equals("") || !fnc.restituisciNumSentenza().equals("")) {
            controllaErrori();
        }
        if (restituisciErrore()) {
            creaFascicolo();
        }

    }

    private void creaFascicolo() {
        if (fnc.restituisciNotReato().equals("")) {
            this.controllo.getVista().schermoErrore("Attenzione inserisci almeno la notizia di reato");
        } else {
            int max = gestionale.resituisiciIdFascicolo();
            fascicolo.setId(max + 1);
            fascicolo.setExternId(cliente.getId());
            fascicolo.setAutoritaGiudiziaria(fnc.restituisciAutGiud());
            fascicolo.setNotizieRato(fnc.restituisciNotReato());
            fascicolo.setRgGip(fnc.restituisciRGGIP());
            fascicolo.setRgnr(fnc.restituisciRGNR());
            fascicolo.setNumeroSentenza(fnc.restituisciNumSentenza());
            cliente.addFascicolo(fascicolo);
            //gestionale.addClienteAstratto(cliente);

            this.controllo.getModello().putBean(Constants.GESTIONALE, gestionale);
            fnc.pulisciCampi();
            fnc.riempiLabelStatoFascicolo(fascicolo.getNotizieRato());
            //fnc.abilitaFascicolo(false);
        }
    }
    
    private boolean restituisciErrore() {
        for (Boolean b : listaBooleani) {
            if (b == false) {
                return false;
            }
        }
        return true;
    }

    private void controllaErrori() {
        listaBooleani.clear();
        int numero;
        try {
            numero = Integer.parseInt(fnc.restituisciNotReato());
            fnc.abilitaAsterischi(true, "notReato");
            fnc.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        } catch (NumberFormatException nfe) {
            fnc.abilitaAsterischi(false, "notReato");
            fnc.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        }

        try {
            numero = Integer.parseInt(fnc.restituisciRGNR());
            fnc.abilitaAsterischi(true, "rgnr");
            fnc.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        } catch (NumberFormatException nfe) {
            fnc.abilitaAsterischi(false, "rgnr");
            fnc.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        }

        try {
            numero = Integer.parseInt(fnc.restituisciRGGIP());
            fnc.abilitaAsterischi(true, "rg-gip");
            fnc.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        } catch (NumberFormatException nfe) {
            fnc.abilitaAsterischi(false, "rg-gip");
            fnc.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        }

        try {
            numero = Integer.parseInt(fnc.restituisciNumSentenza());
            fnc.abilitaAsterischi(false, "numSent");
            fnc.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        } catch (NumberFormatException nfe) {
            fnc.abilitaAsterischi(true, "numSent");
            fnc.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        }
    }
}
