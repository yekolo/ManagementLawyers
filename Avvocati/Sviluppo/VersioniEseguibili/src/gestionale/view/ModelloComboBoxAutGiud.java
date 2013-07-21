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
public class ModelloComboBoxAutGiud extends DefaultComboBoxModel {

    @Override
    public String getElementAt(int x) {
        for(int i = 0; i < 5; i++) {
            if(x == 0) {
                return "Giudice udienza preliminare";
            } else if (x == 1) {
                return "Tribunale collegiale";
            } else if (x == 2) {
                return "Tribunale monocratico";
            } else if (x == 3) {
                return "Corte d'appello";
            } else if (x == 4) {
                return "Corte di cassazione";
            } else if (x == 5) {
                return "Giudice di pace";
            }
        }
        return null;
    }

    @Override
    public int getSize() {
        return 6;
    }

}
