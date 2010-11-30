package typex29.vessels;

import java.util.Date;
import typex29.applet.Main;
import typex29.bullets.Bullet;
import typex29.bullets.DepthCharge;
import typex29.bullets.SAM;
import typex29.bullets.Shell;
import typex29.panels.GamePanel;

/**
 *
 * @author irenton
 */
public class PlayerShip extends Vessel {

    private static final double SPEED = 3;                  // px/frame
    private static final int START_X_POSITION = 360;        // px
    private static final int START_Y_POSITION = 228;        // px
    private static final int SAM_RELOAD = 250;              // ms
    private static final int GUN_RELOAD = 350;              // ms
    private static final int DEPTH_CHARGE_RELOAD = 800;     // ms
    private boolean goingLeft = false;
    private boolean goingRight = false;
    private long lastSAMFireTime;
    private boolean firingSAM = false;
    private long lastShellFireTime;
    private boolean firingShells = false;
    private long lastDepthChargeFireTime;
    private boolean firingDepthCharges = false;
    private boolean samSlotAlternate;

    public PlayerShip(Main applet, GamePanel panel) {
        super("Type X29", START_X_POSITION, START_Y_POSITION, SPEED, 0, false, applet, panel);
    }

    @Override
    public void iterate() {
        if (!destroyed) {
            if (goingLeft) {
                xPos -= speed;
            }
            if (goingRight) {
                xPos += speed;
            }
            long currentTime = new Date().getTime();
            if ((lastSAMFireTime + SAM_RELOAD < currentTime) && firingSAM) {
                samSlotAlternate = !samSlotAlternate;
                panel.addPlayerBullet(new SAM(xPos + (samSlotAlternate ? 63 : 67), yPos, applet));
                lastSAMFireTime = currentTime;
            }
            if ((lastShellFireTime + GUN_RELOAD < currentTime) && firingShells) {
                panel.addPlayerBullet(new Shell(xPos + 80, yPos + 10, applet));
                lastShellFireTime = currentTime;
            }
            if ((lastDepthChargeFireTime + DEPTH_CHARGE_RELOAD < currentTime) && firingDepthCharges) {
                panel.addPlayerBullet(new DepthCharge(xPos, yPos + 32, applet));
                lastDepthChargeFireTime = currentTime;
            }

            for (Bullet b : panel.getEnemyBullets()) {
                destroyed = destroyed || isBeingHitBy(b);
            }
        } else {
            image = applet.getResource("Type X29 (Destroyed)");
        }
    }

    /**
     * @param goingLeft the goingLeft to set
     */
    public void setGoingLeft(boolean goingLeft) {
        this.goingLeft = goingLeft;
    }

    /**
     * @param goingRight the goingRight to set
     */
    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    /**
     * @param firingSAM the firingSAM to set
     */
    public void setFiringSAM(boolean firingSAM) {
        this.firingSAM = firingSAM;
    }

    /**
     * @param firingShells the firingShells to set
     */
    public void setFiringShells(boolean firingShells) {
        this.firingShells = firingShells;
    }

    /**
     * @param firingDepthCharges the firingDepthCharges to set
     */
    public void setFiringDepthCharges(boolean firingDepthCharges) {
        this.firingDepthCharges = firingDepthCharges;
    }
}
