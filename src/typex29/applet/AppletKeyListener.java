/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package typex29.applet;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author irenton
 */
public class AppletKeyListener implements KeyListener, MouseListener {

    private final Main applet;

    public AppletKeyListener(Main applet) {
        this.applet = applet;
    }

    public void keyPressed(KeyEvent e) {
        if (applet.getMode().equals("Game")) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    applet.getGamePanel().getPlayerShip().setGoingLeft(true);
                    break;

                case KeyEvent.VK_RIGHT:
                    applet.getGamePanel().getPlayerShip().setGoingRight(true);
                    break;

                case KeyEvent.VK_Z:
                    applet.getGamePanel().getPlayerShip().setFiringDepthCharges(true);
                    break;

                case KeyEvent.VK_X:
                    applet.getGamePanel().getPlayerShip().setFiringShells(true);
                    break;

                case KeyEvent.VK_C:
                    applet.getGamePanel().getPlayerShip().setFiringSAM(true);
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (applet.getMode().equals("Game")) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    applet.getGamePanel().getPlayerShip().setGoingLeft(false);
                    break;

                case KeyEvent.VK_RIGHT:
                    applet.getGamePanel().getPlayerShip().setGoingRight(false);
                    break;

                case KeyEvent.VK_Z:
                    applet.getGamePanel().getPlayerShip().setFiringDepthCharges(false);
                    break;

                case KeyEvent.VK_X:
                    applet.getGamePanel().getPlayerShip().setFiringShells(false);
                    break;

                case KeyEvent.VK_C:
                    applet.getGamePanel().getPlayerShip().setFiringSAM(false);
                    break;
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        if (applet.getMode().equals("Menu")) {
            applet.startGame();
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
}
