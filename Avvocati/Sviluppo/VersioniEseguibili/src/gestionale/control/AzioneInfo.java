/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import java.awt.event.ActionEvent;
import java.security.KeyStore;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 *
 * @author Nicola
 */
public class AzioneInfo extends AbstractAction {

    private Controllo controllo;

    public AzioneInfo(Controllo controllo) {
        this.controllo = controllo;
        this.putValue(Action.NAME, "?");
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl I"));
        this.putValue(Action.SHORT_DESCRIPTION, "Informazioni sull'applicazione");
    }

    public void actionPerformed(ActionEvent e) {
        this.controllo.getVista().schermoInfo("Questa applicazione e' stata sviluppata da Nicola Capece \n"
                                              + "con la collaborazione di Vito Genovese");
    }

}
