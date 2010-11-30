/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package typex29.bullets;

import typex29.applet.Main;

/**
 *
 * @author irenton
 */
public class JDAM extends Bullet {

    private static final double SPEED = 4;                   // px/frame
    private static final double ANGLE = 180;                 // deg

    public JDAM(double xPos, double yPos, Main applet) {
        super(xPos, yPos, SPEED, ANGLE, false);
        setImage(applet.getResource("JDAM"));
    }
}
