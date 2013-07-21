/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.view;

import gestionale.model.Ente;
import gestionale.model.Gestionale;
import gestionale.model.Privato;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nicolafelicecapece
 */
public class ModelloTabellaClienti extends AbstractTableModel {

    private Gestionale gestionale;

    public ModelloTabellaClienti(Gestionale gestionale) {
        this.gestionale = gestionale;
    }

    public int getRowCount() {
        return this.gestionale.numeroClienti();
    }

    public int getColumnCount() {
        return 11;
    }

    public Object getValueAt(int x, int y) {
        if (y == 0) {
            return this.gestionale.getClienteAstratto(x).getNome();
        } else if (y == 1) {
            if (this.gestionale.getClienteAstratto(x) instanceof Privato) {
                Privato p = (Privato) this.gestionale.getClienteAstratto(x);
                return p.getCognome();
            } else {
                return "";
            }
        } else if (y == 2) {
            return this.gestionale.getClienteAstratto(x).getNumTel();
        } else if (y == 3) {
            return this.gestionale.getClienteAstratto(x).getCellulare();
        } else if (y == 4) {
            return this.gestionale.getClienteAstratto(x).getReato();
        } else if (y == 5) {
            return this.gestionale.getClienteAstratto(x).getRecapito();
        } else if (y == 6) {
            if (this.gestionale.getClienteAstratto(x) instanceof Privato) {
                Privato p = (Privato) gestionale.getClienteAstratto(x);
                return p.getCodFisc();
            } else {
                return "";
            }
        } else if (y == 7) {
            if (this.gestionale.getClienteAstratto(x) instanceof Ente) {
                Ente e = (Ente) gestionale.getClienteAstratto(x);
                return e.getPartitaIva();
            } else {
                return "";
            }
        } else if (y == 8) {
            if (this.gestionale.getClienteAstratto(x) instanceof Privato) {
                Privato p = (Privato) this.gestionale.getClienteAstratto(x);
                return p.getLuogoNascita();
            } else {
                return "";
            }
        } else if (y == 9) {
            if (this.gestionale.getClienteAstratto(x) instanceof Privato) {
                Privato p = (Privato) this.gestionale.getClienteAstratto(x);
                return p.getProvNascita();
            } else {
                return "";
            }
        } else if (y == 10) {
            if (this.gestionale.getClienteAstratto(x) instanceof Privato) {
                Privato p = (Privato) this.gestionale.getClienteAstratto(x);
                return p.formatDataNascita();
            } else {
                return "";
            }
        } else {
            return null;
        }
    }

    @Override
    public String getColumnName(int y) {
        if (y == 0) {
            return "Nome";
        } else if (y == 1) {
            return "Cognome";
        } else if (y == 2) {
            return "Num. Tel.";
        } else if (y == 3) {
            return "Num. Cell.";
        } else if (y == 4) {
            return "Reato";
        } else if (y == 5) {
            return "Recapito";
        } else if (y == 6) {
            return "Cod. Fisc.";
        } else if (y == 7) {
            return "Partita IVA";
        } else if (y == 8) {
            return "Luogo di Nascita";
        } else if (y == 9) {
            return "Provincia di Nascita";
        } else if (y == 10) {
            return "Data di Nascita";
        } else {
            return null;
        }
    }

}
