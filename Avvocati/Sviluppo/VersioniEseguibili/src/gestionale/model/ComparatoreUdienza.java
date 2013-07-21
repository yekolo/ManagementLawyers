/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.model;

import java.util.Comparator;

/**
 *
 * @author nicolafelicecapece
 */
public class ComparatoreUdienza implements Comparator<Udienza> {

    public int compare(Udienza t, Udienza t1) {
        if (t.getId() > t1.getId()) {
            return 1;
        } else if (t.getId() < t1.getId()) {
            return -1;
        } else {
            return 0;
        }
    }
}
