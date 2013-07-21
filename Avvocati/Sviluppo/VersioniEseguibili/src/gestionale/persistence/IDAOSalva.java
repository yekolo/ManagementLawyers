/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.persistence;

import gestionale.model.Gestionale;

/**
 *
 * @author nicolafelicecapece
 */
public interface IDAOSalva {

    void salva(Gestionale gestionale);
    void scriviFileSalva(int numeroAvii);
    void scriviContratto();

}
