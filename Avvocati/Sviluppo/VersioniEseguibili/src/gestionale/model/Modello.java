/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nicola
 */
public class Modello {

    private Map mappaBeans = new HashMap();

    public Object getBean(String key) {
        return this.mappaBeans.get(key);
    }

    public void putBean(String key, Object o) {
        this.mappaBeans.put(key, o);
    }
}
