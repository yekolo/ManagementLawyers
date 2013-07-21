/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Nicola
 */
public class Privato extends ClienteAstratto {

    private String cognome;
    private String codFisc;
    private String luogoNascita;
    private String provNascita;
    private Calendar dataNascita;

    /**
     * @return the cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param cognome the cognome to set
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @return the codFisc
     */
    public String getCodFisc() {
        return codFisc;
    }

    /**
     * @param codFisc the codFisc to set
     */
    public void setCodFisc(String codFisc) {
        this.codFisc = codFisc;
    }

    public String formatDataNascita() {
        DateFormat df = new SimpleDateFormat().getDateInstance(DateFormat.SHORT);
        return df.format(this.dataNascita.getTime());
    }

    @Override
    public String toString() {

        String stringa =   this.getNome() + " - " + this.getCognome() + " - " + this.getCellulare() + " - \n"
                         + this.getCodFisc() + " - " + this.getLuogoNascita() + " - " + formatDataNascita() + " - \n"
                         + this.getProvNascita() + " - " + this.getNumTel() + " - " + this.getRecapito() + " - \n"
                         + this.getReato();
        return stringa;
    }

    public String stampaDati() {
        return this.getNome() + "   " + this.getCognome();
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

    /**
     * @return the luogoNascita
     */
    public String getLuogoNascita() {
        return luogoNascita;
    }

    /**
     * @param luogoNascita the luogoNascita to set
     */
    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    /**
     * @return the provNascita
     */
    public String getProvNascita() {
        return provNascita;
    }

    /**
     * @param provNascita the provNascita to set
     */
    public void setProvNascita(String provNascita) {
        this.provNascita = provNascita;
    }

    /**
     * @return the dataNascita
     */
    public Calendar getDataNascita() {
        return dataNascita;
    }

    /**
     * @param dataNascita the dataNascita to set
     */
    public void setDataNascita(Calendar dataNascita) {
        this.dataNascita = dataNascita;
    }
}
