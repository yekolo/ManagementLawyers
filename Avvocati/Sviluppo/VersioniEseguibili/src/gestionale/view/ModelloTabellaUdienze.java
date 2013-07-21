/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.view;

import gestionale.model.Fascicolo;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nicolafelicecapece
 */
public class ModelloTabellaUdienze extends AbstractTableModel {

    public Fascicolo fascicolo;

    public ModelloTabellaUdienze(Fascicolo fascicolo) {
        this.fascicolo = fascicolo;
    }

    public int getRowCount() {
        return this.fascicolo.numeroUdienze();
    }

    public int getColumnCount() {
        return 1;
    }

    public Object getValueAt(int x, int y) {
        if (y == 0) {
//           Calendar dataCorrente = this.fascicolo.getUdienza(x).getData();
//           DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);  
//            Calendar c = new Calendar() {};    
//            int giorno = dataCorrente.DAY_OF_MONTH-1; 
//            int mese = c.get(dataCorrente.MONTH);
//            int anno = c.get(dataCorrente.YEAR);
//
//            Calendar dataUdienza = new GregorianCalendar(c.get(dataCorrente.YEAR), c.get(dataCorrente.MONTH), dataCorrente.DAY_OF_MONTH - 1);
//   
//            System.err.println("Data: " + giorno + " - " + mese + " " + anno);
//            
//            return df.format(dataUdienza.getTime());
            return this.fascicolo.getUdienza(x).formattaData();
        } else {
            return null;
        }
    }

    @Override
    public String getColumnName(int y) {
        if(y == 0) {
            return "Data udienza";
        } else {
            return null;
        }
    }

}
