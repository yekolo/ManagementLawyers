/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.model;

/**
 *
 * @author Nicola
 */
public class Ente extends ClienteAstratto {

    private String partitaIva;

    /**
     * @return the partitaIva
     */
    public String getPartitaIva() {
        return partitaIva;
    }

    /**
     * @param partitaIva the partitaIva to set
     */
    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    @Override
    public String toString() {

        String stringa =   this.getNome() + " - " + this.getReato() + " - " + this.getCellulare() + " - \n"
                         + this.getPartitaIva() + " - " + this.getNumTel() + " - " + this.getRecapito();

        return stringa;
    }

    public String stampaDati() {
        return this.getNome();
    }

    public int compareTo(ClienteAstratto o) {
        if (this.getId() > o.getId()) {
            return 1;
        } else if (this.getId() < o.getId()) {
            return -1;
        } else {
            return 0;
        }
    }
}
