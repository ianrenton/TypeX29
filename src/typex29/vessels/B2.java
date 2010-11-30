package typex29.vessels;

import java.util.Date;
import java.util.Random;
import typex29.applet.Main;
import typex29.bullets.JDAM;
import typex29.panels.GamePanel;

/**
 *
 * @author tsuki
 */
public class B2 extends Vessel {
    private static final int BOMB_AMMO = 20;
    private static final int BOMB_RELOAD = 200;       // ms
    private static final int BOMB_PROBABILITY = 75;    // %
    private static final int BOMB_FIRE_AT_RANGE = 150; // px

    private long lastBombFireTime;
    private int bombAmmoLeft = BOMB_AMMO;

    public B2(double xPos, double yPos, double speed, double angle, Main applet, GamePanel panel) {
        super("B2", xPos, yPos, speed, angle, false, applet, panel);
    }

    @Override
    public void iterate() {
        super.iterate();
        
        double playerXPos = panel.getPlayerShip().xPos;
        if ((Math.abs(xPos - playerXPos) < BOMB_FIRE_AT_RANGE) && (bombAmmoLeft > 0)) {
            long currentTime = new Date().getTime();
            if ((lastBombFireTime + BOMB_RELOAD < currentTime)) {
                if (new Random().nextInt(100) < BOMB_PROBABILITY) {
                    panel.addEnemyBullet(new JDAM(xPos + 20, yPos, applet));
                    bombAmmoLeft--;
                }
                lastBombFireTime = currentTime;
            }
        }
    }

}
