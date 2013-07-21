/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicola
 */
public class Fascicolo implements Comparable<Fascicolo> {

    private int id;
    private int externId;
    private String notizieRato;
    private String rgnr;
    private String rgGip;
    private String numeroSentenza;
    private String autoritaGiudiziaria;
    private ClienteAstratto cliente;

    private List<Udienza> listaUdienza = new ArrayList<Udienza>();

    /**
     * @return the notizieRato
     */
    public String getNotizieRato() {
        return notizieRato;
    }

    /**
     * @param notizieRato the notizieRato to set
     */
    public void setNotizieRato(String notizieRato) {
        this.notizieRato = notizieRato;
    }

    /**
     * @return the rgnr
     */
    public String getRgnr() {
        return rgnr;
    }

    /**
     * @param rgnr the rgnr to set
     */
    public void setRgnr(String rgnr) {
        this.rgnr = rgnr;
    }

    /**
     * @return the rgGip
     */
    public String getRgGip() {
        return rgGip;
    }

    /**
     * @param rgGip the rgGip to set
     */
    public void setRgGip(String rgGip) {
        this.rgGip = rgGip;
    }

    /**
     * @return the numeroSentenza
     */
    public String getNumeroSentenza() {
        return numeroSentenza;
    }

    /**
     * @param numeroSentenza the numeroSentenza to set
     */
    public void setNumeroSentenza(String numeroSentenza) {
        this.numeroSentenza = numeroSentenza;
    }

    /**
     * @return the autoritaGiudiziaria
     */
    public String getAutoritaGiudiziaria() {
        return autoritaGiudiziaria;
    }

    /**
     * @param autoritaGiudiziaria the autoritaGiudiziaria to set
     */
    public void setAutoritaGiudiziaria(String autoritaGiudiziaria) {
        this.autoritaGiudiziaria = autoritaGiudiziaria;
    }

    /**
     * @return the listaUdienza
     */
    public List<Udienza> getListaUdienza() {
        return listaUdienza;
    }

    /**
     * @param listaUdienza the listaUdienza to set
     */
    public void setListaUdienze(List<Udienza> listaUdienza) {
        this.listaUdienza = listaUdienza;
    }

    public int numeroUdienze() {
        return this.getListaUdienza().size();
    }

    public Udienza getUdienza(int i) {
        return this.getListaUdienza().get(i);
    }

    public void addUdienza(Udienza udienza) {
        this.getListaUdienza().add(udienza);
    }
    
    public void removeUdienza(Udienza udienza) {
        this.getListaUdienza().remove(udienza);
    }

    /**
     * @return the cliente
     */
    public ClienteAstratto getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteAstratto cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the externId
     */
    public int getExternId() {
        return externId;
    }

    /**
     * @param externId the externId to set
     */
    public void setExternId(int externId) {
        this.externId = externId;
    }

    public int compareTo(Fascicolo o) {
        if (this.getId() > o.getId()) {
            return 1;
        } else if (this.getId() < o.getId()) {
            return -1;
        } else {
            return 0;
        }
    }
}
