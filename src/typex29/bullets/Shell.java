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
public class Shell extends Bullet {

    private static final double SPEED = 8;                   // px/frame
    private static final double ANGLE = 90;                  // deg

    public Shell(double xPos, double yPos, Main applet) {
        super(xPos, yPos, SPEED, ANGLE, false);
        setImage(applet.getResource("Shell"));
    }
}
