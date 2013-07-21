/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Nicola
 */
public class Causa implements Comparable<Causa>{

    private int id;
    private int externId;
    private String numProc;
    private String autGiud;
    private Fascicolo fascicolo;

    private List<Udienza> listaUdienze = new ArrayList<Udienza>();

    /**
     * @return the numProc
     */
    public String getNumProc() {
        return numProc;
    }

    /**
     * @param numProc the numProc to set
     */
    public void setNumProc(String numProc) {
        this.numProc = numProc;
    }

    /**
     * @return the autGiud
     */
    public String getAutGiud() {
        return autGiud;
    }

    /**
     * @param autGiud the autGiud to set
     */
    public void setAutGiud(String autGiud) {
        this.autGiud = autGiud;
    }

    /**
     * @return the listaUdienze
     */
    public List<Udienza> getListaUdienze() {
        return listaUdienze;
    }

    /**
     * @param listaUdienze the listaUdienze to set
     */
    public void setListaUdienze(List<Udienza> listaUdienze) {
        this.listaUdienze = listaUdienze;
    }

    public int numeroUdienze() {
        return this.listaUdienze.size();
    }

    public Udienza getUdienza(int i) {
        return this.listaUdienze.get(i);
    }

    public void addUdienza(Udienza udienza) {
        this.listaUdienze.add(udienza);
    }
    
    public void removeUdienza(Udienza udienza) {
        this.listaUdienze.remove(udienza);
    }

    /**
     * @return the fascicolo
     */
    public Fascicolo getFascicolo() {
        return fascicolo;
    }

    /**
     * @param fascicolo the fascicolo to set
     */
    public void setFascicolo(Fascicolo fascicolo) {
        this.fascicolo = fascicolo;
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

    public void ordinaDate() {
        Collections.sort(this.listaUdienze);
    }

    public int compareTo(Causa t) {
        if (this.getId() > t.getId()) {
            return 1;
        } else if (this.getId() < t.getId()) {
            return -1;
        } else {
            return 0;
        }
    }
}
