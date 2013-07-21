/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.model.ClienteAstratto;
import gestionale.model.Fascicolo;
import gestionale.model.Gestionale;
import gestionale.view.FinestraAggiungiFascicoloClienteEsistente;
import gestionale.view.PannelloFascicoli;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author nicolafelicecapece
 */
public class AzioneNuovoFascicoloClienteEsistente extends AbstractAction {

    private Controllo controllo;
    private FinestraAggiungiFascicoloClienteEsistente fafce;
    private List<Boolean> listaBooleani = new ArrayList<Boolean>();

    public AzioneNuovoFascicoloClienteEsistente(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Aggiungi Fascicolo");
        this.putValue(Action.SHORT_DESCRIPTION, "Aggiunge un nuovo fascicolo alla lista");
    }

    public void actionPerformed(ActionEvent ae) {
        Gestionale gestionale = (Gestionale) this.controllo.getModello().getBean(Constants.GESTIONALE);
        ClienteAstratto cliente = (ClienteAstratto) this.controllo.getModello().getBean(Constants.CLIENTE);
        fafce = (FinestraAggiungiFascicoloClienteEsistente) this.controllo.getVista().getSottoVista(Constants.FINESTRA_AGGIUNGI_FASCICOLO_CLIENTE_ESISTENTE);

        controllaErrori();

        if (restituisciErrore()) {

            if (!fafce.restituisciNotiziaReato().equals("") || !fafce.restituisciRGGIP().equals("") || !fafce.restituisciRGNR().equals("") || !fafce.restituisciNumeroSentenza().equals("")) {
                Fascicolo fascicolo = new Fascicolo();
                fascicolo.setId(gestionale.resituisiciIdFascicolo() + 1);
                fascicolo.setAutoritaGiudiziaria(fafce.restituisciAutGiud());
                fascicolo.setNotizieRato(fafce.restituisciNotiziaReato());
                fascicolo.setNumeroSentenza(fafce.restituisciNumeroSentenza());
                fascicolo.setRgGip(fafce.restituisciRGGIP());
                fascicolo.setRgnr(fafce.restituisciRGNR());
                fascicolo.setExternId(cliente.getId());
                fafce.pulisciCampi();
                cliente.addFascicolo(fascicolo);
                //gestionale.addClienteAstratto(cliente);
                PannelloFascicoli pf = (PannelloFascicoli) this.controllo.getVista().getSottoVista(Constants.PANNELLO_FASCICOLI);
                pf.riempiTabella();
                this.controllo.getVista().schermoInfo("Il fascicolo n. " + fascicolo.getNumeroSentenza() + " è stato aggiunto");
            } else {
                this.controllo.getVista().schermoErrore("Attenzione tutti i campi devono essere riempiti");
            }
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
            numero = Integer.parseInt(fafce.restituisciNotiziaReato());
            fafce.visualizzaAsterisco1(true);
            fafce.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        } catch (NumberFormatException nfe) {
            fafce.visualizzaAsterisco1(false);
            fafce.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        }

        try {
            numero = Integer.parseInt(fafce.restituisciRGNR());
            fafce.visualizzaAsterisco2(true);
            fafce.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        } catch (NumberFormatException nfe) {
            fafce.visualizzaAsterisco2(false);
            fafce.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        }

        try {
            numero = Integer.parseInt(fafce.restituisciRGGIP());
            fafce.visualizzaAsterisco3(true);
            fafce.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        } catch (NumberFormatException nfe) {
            fafce.visualizzaAsterisco3(false);
            fafce.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        }

        try {
            numero = Integer.parseInt(fafce.restituisciNumeroSentenza());
            fafce.visualizzaAsterisco4(false);
            fafce.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        } catch (NumberFormatException nfe) {
            fafce.visualizzaAsterisco4(true);
            fafce.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        }
    }
}
