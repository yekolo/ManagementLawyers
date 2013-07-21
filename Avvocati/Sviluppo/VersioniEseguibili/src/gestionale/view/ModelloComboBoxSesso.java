/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.view;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author nicolafelicecapece
 */
public class ModelloComboBoxSesso extends DefaultComboBoxModel {
    @Override
        public String getElementAt(int x) {
            for(int i = 0; i < 1; i++) {
                if(x == 0) {
                    return "M";
                } else if (x == 1) {
                    return "F";
                }
            }
            return null;
        }

        @Override
        public int getSize() {
            return 2;
        }
}
