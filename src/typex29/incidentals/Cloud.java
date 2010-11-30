package typex29.incidentals;

import java.util.Random;
import typex29.applet.Main;
import typex29.panels.GamePanel;

/**
 *
 * @author tsuki
 */
public class Cloud extends Incidental {

    public Cloud(double xPos, double yPos, double speed, double angle, Main applet, GamePanel panel) {
        super("Cloud " + (new Random().nextInt(4)+1), xPos, yPos, speed, angle, false, applet, panel);
    }
}
