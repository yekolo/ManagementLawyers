/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.view;

import gestionale.Constants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author nicolafelicecapece
 */
public class PopClickListener extends MouseAdapter {

    private Vista vista;
    
    public PopClickListener(Vista vista) {
        this.vista = vista;
    }

    @Override
    public void mousePressed(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    @Override
    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e){
        PopupMenu menu = (PopupMenu) this.vista.getSottoVista(Constants.POPUP_MENU);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}

