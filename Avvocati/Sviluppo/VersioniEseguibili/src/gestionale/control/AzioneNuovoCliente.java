/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionale.control;

import gestionale.Constants;
import gestionale.model.Ente;
import gestionale.model.Gestionale;
import gestionale.model.Privato;
import gestionale.view.FinestraNuovoCliente;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author nicolafelicecapece
 */
public class AzioneNuovoCliente extends AbstractAction {

    private Controllo controllo;
    private FinestraNuovoCliente fnc;
    private Gestionale gestionale;
    private List<Boolean> listaBooleani = new ArrayList<Boolean>();
    private Log logger = LogFactory.getLog(this.getClass());

    public AzioneNuovoCliente(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "Crea Cliente");
        this.putValue(Action.SHORT_DESCRIPTION, "Crea un nuovo cliente");
    }

    public void actionPerformed(ActionEvent ae) {
        fnc = (FinestraNuovoCliente) this.controllo.getVista().getSottoVista(Constants.FINESTRA_NUOVO_CLIENTE);
        gestionale = (Gestionale) this.controllo.getModello().getBean(Constants.GESTIONALE);

        if (!fnc.restituisciNome().equals("")) {
        
            controllaErrori();
        }

        if (restituisciErrore()) {

            creaCliente();

        }
    }

    private void creaCliente() {
        if (fnc.selezionaTipo() == 1) {
            if (fnc.restituisciNome().equals("")) {

                this.controllo.getVista().schermoErrore("Attenzione inserisci almeno il nome");
            } else {
                Ente ente = new Ente();
                ente.setNome(fnc.restituisciNome());
                ente.setCellulare(fnc.restituisciNumCell());
                ente.setPartitaIva(fnc.restituisciDatoAstratto());
                ente.setNumTel(fnc.restituisciNumTel());
                ente.setReato(fnc.restituisciReato());
                ente.setRecapito(fnc.restituisciRecapito());
                int max = gestionale.restituisciIdCliente();
                ente.setId(max + 1);
                this.controllo.getModello().putBean(Constants.CLIENTE, ente);
                gestionale.addClienteAstratto(ente);
                fnc.riempiLabelRiepilogo(ente.stampaDati());
                fnc.disabilitaInserimento(false);
                fnc.abilitaFascicolo(true);
            }
        } else if (fnc.selezionaTipo() == 2) {
            if (fnc.restituisciNome().equals("")) {

                this.controllo.getVista().schermoErrore("Attenzione inserisci almeno il nome");
            } else {
                logger.info("Sto creando un nuovo utente privato");
                Privato privato = new Privato();
                privato.setNome(fnc.restituisciNome());
                privato.setCognome(fnc.restituisciCognome());
                privato.setCellulare(fnc.restituisciNumCell());
                privato.setCodFisc(fnc.restituisciDatoAstratto());
                privato.setNumTel(fnc.restituisciNumTel());
                privato.setReato(fnc.restituisciReato());
                privato.setRecapito(fnc.restituisciRecapito());
                privato.setLuogoNascita(fnc.restituisciLuogoNascita());
                privato.setProvNascita(fnc.restituisciProvNascita());
                privato.setDataNascita(fnc.restituisciDataNascita());
                int max = gestionale.restituisciIdCliente();
                privato.setId(max + 1);
                this.controllo.getModello().putBean(Constants.CLIENTE, privato);
                gestionale.addClienteAstratto(privato);
                fnc.riempiLabelRiepilogo(privato.stampaDati());
                fnc.disabilitaInserimento(false);
                fnc.abilitaFascicolo(true);

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
            numero = Integer.parseInt(fnc.restituisciNome());
            fnc.abilitaAsterischi(true, "nome");
            fnc.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        } catch (NumberFormatException nfe) {
            fnc.abilitaAsterischi(false, "nome");
            fnc.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        }

        try {
            numero = Integer.parseInt(fnc.restituisciCognome());
            fnc.abilitaAsterischi(true, "cognome");
            fnc.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        } catch (NumberFormatException nfe) {
            fnc.abilitaAsterischi(false, "cognome");
            fnc.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        }

        try {
            numero = Integer.parseInt(fnc.restituisciRecapito());
            fnc.abilitaAsterischi(true, "recapito");
            fnc.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        } catch (NumberFormatException nfe) {
            fnc.abilitaAsterischi(false, "recapito");
            fnc.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        }

        try {
            numero = Integer.parseInt(fnc.restituisciReato());
            fnc.abilitaAsterischi(true, "reato");
            fnc.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        } catch (NumberFormatException nfe) {
            fnc.abilitaAsterischi(false, "reato");
            fnc.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        }

        try {
            numero = Integer.parseInt(fnc.restituisciNumTel());
            fnc.abilitaAsterischi(false, "numTel");
            fnc.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        } catch (NumberFormatException nfe) {
            fnc.abilitaAsterischi(true,"numTel");
            fnc.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        }

        try {
            numero = (int)Long.parseLong(fnc.restituisciNumCell());
            
            fnc.abilitaAsterischi(false, "numCell");
            fnc.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        } catch (NumberFormatException nfe) {
            fnc.abilitaAsterischi(true, "numCell");
            fnc.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        }
        
        try {
            numero = Integer.parseInt(fnc.restituisciLuogoNascita());
            fnc.abilitaAsterischi(true, "luogoNascita");
            fnc.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        } catch (NumberFormatException nfe) {
            fnc.abilitaAsterischi(false, "luogoNascita");
            fnc.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        }
        
        try {
            numero = Integer.parseInt(fnc.restituisciProvNascita());
            fnc.abilitaAsterischi(true, "provNascita");
            fnc.abilitaErroreGenerico(true);
            listaBooleani.add(false);
        } catch (NumberFormatException nfe) {
            fnc.abilitaAsterischi(false, "provNascita");
            fnc.abilitaErroreGenerico(false);
            listaBooleani.add(true);
        }


//        try {
//            numero = (int)Long.parseLong(fnc.restituisciDatoAstratto());
//            fnc.abilitaAsterischi(false, "datoAstratto");
//            fnc.abilitaErroreGenerico(false);
//            listaBooleani.add(true);
//        } catch (NumberFormatException nfe) {
//            fnc.abilitaAsterischi(true, "datoAstratto");
//            fnc.abilitaErroreGenerico(true);
//            listaBooleani.add(false);
//        }


        if (fnc.selezionaTipo() == 1) {
            if (fnc.restituisciDatoAstratto().length() != 11) {
                fnc.abilitaAsterischi(true, "datoAstratto");
                fnc.riempiLabel("Attenzione la Partita IVA deve essere di 11 caratteri");
                listaBooleani.add(false);
            } else {
                fnc.abilitaAsterischi(false, "datoAstratto");
                fnc.riempiLabel("");
                listaBooleani.add(true);
            }
        } else {
            if (fnc.restituisciDatoAstratto().length() != 17) {
                fnc.abilitaAsterischi(true, "datoAstratto");
                fnc.riempiLabel("Attenzione il Codice fiscale deve essere di 16 caratteri");
                listaBooleani.add(false);
            } else {
                fnc.abilitaAsterischi(false, "datoAstratto");
                fnc.riempiLabel("");
                listaBooleani.add(true);
            }
        }
    }
}
