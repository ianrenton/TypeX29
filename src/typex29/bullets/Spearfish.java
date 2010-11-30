package typex29.bullets;

import typex29.applet.Main;

/**
 *
 * @author irenton
 */
public class Spearfish extends Bullet {

    private static final double SPEED = 2.5;                   // px/frame
    private static final double ANGLE = 0;                     // deg
    
    public Spearfish(double xPos, double yPos, Main applet) {
        super(xPos, yPos, SPEED, ANGLE, true);
        setImage(applet.getResource("Spearfish"));
    }

}
