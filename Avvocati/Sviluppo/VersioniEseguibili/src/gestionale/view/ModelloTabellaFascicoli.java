/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.view;

import gestionale.model.ClienteAstratto;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Nicola
 */
public class ModelloTabellaFascicoli extends AbstractTableModel {

    private ClienteAstratto cliente;

    public ModelloTabellaFascicoli(ClienteAstratto cliente) {
        this.cliente = cliente;
    }

    public int getRowCount() {
        return this.cliente.numeroFascicoli();
    }

    public int getColumnCount() {
        return 5;
    }

    public Object getValueAt(int x, int y) {
        if(y == 0) {
            return this.cliente.getFascicolo(x).getNotizieRato();
        } else if (y == 1) {
            return this.cliente.getFascicolo(x).getRgnr();
        } else if (y == 2) {
            return this.cliente.getFascicolo(x).getRgGip();
        } else if (y == 3) {
            return this.cliente.getFascicolo(x).getNumeroSentenza();
        } else if (y == 4) {
            return  this.cliente.getFascicolo(x).getAutoritaGiudiziaria();
        } else {
            return null;
        }
    }

    public String getColumnName(int y) {
        if (y == 0) {
            return "Notizia di reato";
        } else if (y == 1) {
            return "R.G.N.R";
        } else if (y == 2) {
            return "R.G.GIP";
        } else if (y == 3) {
            return "Numero sentenza";
        } else if (y == 4) {
            return "Autorita' giudiziaria";
        } else {
            return null;
        }
    }
}
