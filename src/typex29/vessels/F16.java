package typex29.vessels;

import java.util.Date;
import java.util.Random;
import typex29.applet.Main;
import typex29.bullets.Harpoon;
import typex29.panels.GamePanel;

/**
 *
 * @author tsuki
 */
public class F16 extends Vessel {
    private static final int MISSILE_AMMO = 4;
    private static final int MISSILE_RELOAD = 100;       // ms
    private static final int MISSILE_PROBABILITY = 75;    // %
    private static final int MISSILE_FIRE_AT_RANGE = 300; // px

    private long lastMissileFireTime;
    private int missileAmmoLeft = MISSILE_AMMO;

    public F16(double xPos, double yPos, double speed, double angle, Main applet, GamePanel panel) {
        super("F16", xPos, yPos, speed, angle, false, applet, panel);
    }

    @Override
    public void iterate() {
        super.iterate();
        
        double playerXPos = panel.getPlayerShip().xPos;
        if ((Math.abs(xPos - playerXPos) < MISSILE_FIRE_AT_RANGE) && (missileAmmoLeft > 0)) {
            long currentTime = new Date().getTime();
            if ((lastMissileFireTime + MISSILE_RELOAD < currentTime)) {
                if (new Random().nextInt(100) < MISSILE_PROBABILITY) {
                    panel.addEnemyBullet(new Harpoon(xPos + 20, yPos, applet));
                    missileAmmoLeft--;
                }
                lastMissileFireTime = currentTime;
            }
        }
    }

}
