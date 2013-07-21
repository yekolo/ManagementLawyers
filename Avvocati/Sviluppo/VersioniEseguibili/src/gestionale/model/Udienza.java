/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.model;

import gestionale.persistence.DAOCliente;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Nicola
 */
public class Udienza implements Comparable<Udienza>{

    private int id;
    private int externId;
    private Calendar data;
    private Causa causa;
    private Log logger = LogFactory.getLog(this.getClass());

    /**
     * @return the data
     */
    public Calendar getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Calendar data) {
        this.data = data;
    }

    public String formattaData() {
        DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.FULL);   
        return df.format(this.data.getTime());
    }

    public String formattaDataShort() {
        DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        return df.format(this.data.getTime());
    }


    /**
     * @return the causa
     */
    public Causa getCausa() {
        return causa;
    }

    /**
     * @param causa the causa to set
     */
    public void setCausa(Causa causa) {
        this.causa = causa;
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

    public int compareTo(Udienza t) {
        return this.data.compareTo(t.getData());
    }
    
}
