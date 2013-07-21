/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.persistence;

import gestionale.model.Gestionale;

/**
 *
 * @author Nicola
 */
public interface IDAOCliente {

    Gestionale caricaClienti();
    String numeroAvvii();
    //void generaFileTemp();
}
