/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.model.Gestionale;
import gestionale.model.Modello;
import gestionale.persistence.DAOCliente;
import gestionale.persistence.DAOSalva;
import gestionale.persistence.IDAOCliente;
import gestionale.persistence.IDAOSalva;
import gestionale.view.Vista;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Nicola
 */
public class Controllo {

    private Vista vista;
    private Modello modello;
    private int validita;
    private Map mappaAzioni = new HashMap();
    private Log logger = LogFactory.getLog(this.getClass());

    public Controllo() {
        this.modello = new Modello();
        caricaArchivio();
        inizializzaAzioni();
        this.vista = new Vista(this, modello);
    }

    public Object getAzione(String key) {
        return this.mappaAzioni.get(key);
    }

    public void putAzione(String key, Object o) {
        this.mappaAzioni.put(key, o);
    }

     /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Controllo();
            }
        });
    }

    /**
     * @return the vista
     */
    public Vista getVista() {
        return vista;
    }

    /**
     * @return the modello
     */
    public Modello getModello() {
        return modello;
    }

    private void inizializzaAzioni() {
        this.mappaAzioni.put(Constants.AZIONE_ESCI, new AzioneEsci(this));
        this.mappaAzioni.put(Constants.AZIONE_INFO, new AzioneInfo(this));
        this.mappaAzioni.put(Constants.AZIONE_CERCA_CLIENTE, new AzioneCercaCliente(this));
        this.mappaAzioni.put(Constants.AZIONE_INDIETRO, new AzioneIndietro(this));
   //     this.mappaAzioni.put(Constants.AZIONE_VISUALIZZA_CAUSE, new AzioneVisualizzaUdienze(this));
        this.mappaAzioni.put(Constants.AZIONE_VISUALIZZA_UDIENZE, new AzioneVisualizzaUdienze(this));
        this.mappaAzioni.put(Constants.AZIONE_AGGIUNGI_CLIENTE, new AzioneAggiungiCliente(this));
        this.mappaAzioni.put(Constants.AZIONE_NUOVO_CLIENTE, new AzioneNuovoCliente(this));
        this.mappaAzioni.put(Constants.AZIONE_JRADIO_BUTTON, new AzioneJRadioButton(this));
        this.mappaAzioni.put(Constants.AZIONE_AGGIUNGI_FASCICOLO, new AzioneAggiungiFascicolo(this));
        this.mappaAzioni.put(Constants.AZIONE_RIMUOVI, new AzioneRimuovi(this));
        this.mappaAzioni.put(Constants.AZIONE_SALVA, new AzioneSalva(this));
        this.mappaAzioni.put(Constants.AZIONE_INDIETRO_AC, new AzioneIndietroFinestraAC(this));
        this.mappaAzioni.put(Constants.AZIONE_SELEZIONA, new AzioneSelezionaCliente(this));
        this.mappaAzioni.put(Constants.AZIONE_AGGIUNGI_FASCICOLO_CLIENTE_ESISTENTE, new AzioneAggiungiFascicoloClienteEsistente(this));
        this.mappaAzioni.put(Constants.AZIONE_NUOVO_FASCICOLO_CLIENTE_ESISTENTE, new AzioneNuovoFascicoloClienteEsistente(this));
        this.mappaAzioni.put(Constants.AZIONE_RIMUOVI_FASCICOLO, new AzioneRimuoviFascicolo(this));
        this.mappaAzioni.put(Constants.AZIONE_AGGIUNGI_UDIENZA, new AzioneAggiungiUdienza(this));
        this.mappaAzioni.put(Constants.AZIONE_RIMUOVI_UDIENZA, new AzioneRimuoviUdienza(this));
        this.mappaAzioni.put(Constants.AZIONE_CODICE_FISCALE, new AzioneCodiceFiscale(this));
    }

    private void caricaArchivio() {
        IDAOCliente idaoc = new DAOCliente();
        Gestionale gestionale = idaoc.caricaClienti();
        
        logger.info("MD5 di 1000: " + DAOSalva.encrypt(""+1000));
        
        for(int i = 0; i < 1001; i++) {
            if (DAOSalva.encrypt(""+i).equalsIgnoreCase(idaoc.numeroAvvii())) {
                
                validita = i;
            }
        }
        
        setValidita(validita);

        IDAOSalva idaos = new DAOSalva();
        idaos.scriviFileSalva(validita);
        idaos.scriviContratto();
        //idaoc.generaFileTemp();
        this.getModello().putBean(Constants.GESTIONALE, gestionale);
    }

    public int getValidita() {
        return this.validita;
    }

    private void setValidita(int validita) {
        this.validita = validita;
    }
}
