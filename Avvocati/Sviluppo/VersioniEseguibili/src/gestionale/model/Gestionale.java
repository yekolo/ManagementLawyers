/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Nicola
 */
public class Gestionale {

    private List<ClienteAstratto> listaClienti = new ArrayList<ClienteAstratto>();
    private Log logger = LogFactory.getLog(this.getClass());

    public int numeroClienti() {
        return this.listaClienti.size();
    }

    public ClienteAstratto getClienteAstratto(int i) {

        if (this.listaClienti.size() >= 0) {

            return this.listaClienti.get(i);
        }
        return null;
    }

    public void addClienteAstratto(ClienteAstratto clienteAstratto) {

        if (this.listaClienti.contains(clienteAstratto)) {
            this.listaClienti.remove(clienteAstratto);
        }
        
        this.listaClienti.add(clienteAstratto);
    }

    public ClienteAstratto cercaCliente(String nome) {

        for (ClienteAstratto ca : this.listaClienti) {

            if (ca.getNome().equalsIgnoreCase(nome)) {
                return ca;
            }
        }
        return null;
    }

    public List<Fascicolo> listaFascicoli() {

        List<Fascicolo> lista = new ArrayList<Fascicolo>();

        for(ClienteAstratto cliente : this.listaClienti) {
            lista.addAll(cliente.getListaFascicoli());
        }

        return lista;
    }
    
    public List<Udienza> listaUdienze() {
        List<Udienza> listaUdienze = new ArrayList<Udienza>();

        for (Fascicolo fascicolo : this.listaFascicoli()) {
            listaUdienze.addAll(fascicolo.getListaUdienza());
        }

        return listaUdienze;
    }

    public void rimuoviCliente(ClienteAstratto cliente) {
        this.listaClienti.remove(cliente);
    }

    public int restituisciIdCliente() {

        int max = 0;

        for (int i = 1; i < this.listaClienti.size(); i++) {
            if (this.listaClienti.get(i).getId() > max) {
                max = this.listaClienti.get(i).getId();
            }
        }
        return max;
    }

    public int resituisiciIdFascicolo() {

        int max = 0;

        for(int i = 1; i < listaFascicoli().size(); i++) {
            if (listaFascicoli().get(i).getId() > max) {
                max = listaFascicoli().get(i).getId();
            }
        }
        return max;
    }
    
    public int restituisciIdUdienza() {
        int max = 0;
        List<Udienza> listaTempUdienze = this.listaUdienze();
        for (int i = 0; i < listaTempUdienze.size(); i++) {
            if (listaTempUdienze.get(i).getId() > max) {
                max = listaTempUdienze.get(i).getId();
            }
        }
        return max;
    }
    
    public void ordinaClienti() {
        Collections.sort(this.listaClienti);
    } 
}
