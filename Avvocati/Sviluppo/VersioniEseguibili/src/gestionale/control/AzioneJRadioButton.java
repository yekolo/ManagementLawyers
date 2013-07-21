/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.control;

import gestionale.Constants;
import gestionale.control.Controllo;
import gestionale.view.FinestraNuovoCliente;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author nicolafelicecapece
 */
public class AzioneJRadioButton extends AbstractAction {

    private Controllo controllo;

    public AzioneJRadioButton(Controllo controllo) {
        this.controllo = controllo;
    }

    public void actionPerformed(ActionEvent ae) {
        FinestraNuovoCliente fnc = (FinestraNuovoCliente) this.controllo.getVista().getSottoVista(Constants.FINESTRA_NUOVO_CLIENTE);
        fnc.aggiustaLabel();
    }
}
