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
public abstract class ClienteAstratto  implements Comparable<ClienteAstratto> {

    //id del cliente è random;
    private int id;
    private String nome;
    private String recapito;
    private String reato;
    private String numTel;
    private String cellulare;
    private List<Fascicolo> listaFascicoli = new ArrayList<Fascicolo>();

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the recapito
     */
    public String getRecapito() {
        return recapito;
    }

    /**
     * @param recapito the recapito to set
     */
    public void setRecapito(String recapito) {
        this.recapito = recapito;
    }

    /**
     * @return the reato
     */
    public String getReato() {
        return reato;
    }

    /**
     * @param reato the reato to set
     */
    public void setReato(String reato) {
        this.reato = reato;
    }

    /**
     * @return the numTel
     */
    public String getNumTel() {
        return numTel;
    }

    /**
     * @param numTel the numTel to set
     */
    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    /**
     * @return the cellulare
     */
    public String getCellulare() {
        return cellulare;
    }

    /**
     * @param cellulare the cellulare to set
     */
    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public int numeroFascicoli() {
        return this.getListaFascicoli().size();
    }

    public Fascicolo getFascicolo(int i) {
        return this.getListaFascicoli().get(i);
    }

    public void addFascicolo(Fascicolo fascicolo) {

        this.getListaFascicoli().add(fascicolo);
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
     * @return the listaFascicoli
     */
    public List<Fascicolo> getListaFascicoli() {
        return listaFascicoli;
    }

    public void rimuoviFascicolo(Fascicolo fascicolo) {
        this.listaFascicoli.remove(fascicolo);
    }
}
