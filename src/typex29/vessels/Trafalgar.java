package typex29.vessels;

import java.util.Date;
import java.util.Random;
import typex29.applet.Main;
import typex29.bullets.Spearfish;
import typex29.panels.GamePanel;

/**
 *
 * @author tsuki
 */
public class Trafalgar extends Vessel {
    private static final int SPEARFISH_AMMO = 20;
    private static final int SPEARFISH_RELOAD = 1000;       // ms
    private static final int SPEARFISH_PROBABILITY = 50;    // %
    private static final int SPEARFISH_FIRE_AT_RANGE = 150; // px

    private long lastSpearfishFireTime;
    private int spearfishAmmoLeft = SPEARFISH_AMMO;

    public Trafalgar(double xPos, double yPos, double speed, double angle, Main applet, GamePanel panel) {
        super("Trafalgar", xPos, yPos, speed, angle, true, applet, panel);
    }

    @Override
    public void iterate() {
        super.iterate();
        
        double playerXPos = panel.getPlayerShip().xPos;
        if ((Math.abs(xPos - playerXPos) < SPEARFISH_FIRE_AT_RANGE) && (spearfishAmmoLeft > 0)) {
            long currentTime = new Date().getTime();
            if ((lastSpearfishFireTime + SPEARFISH_RELOAD < currentTime)) {
                if (new Random().nextInt(100) < SPEARFISH_PROBABILITY) {
                    panel.addEnemyBullet(new Spearfish(xPos + 90 - (spearfishAmmoLeft * 2), yPos, applet));
                    spearfishAmmoLeft--;
                }
                lastSpearfishFireTime = currentTime;
            }
        }
    }

}
