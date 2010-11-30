package typex29.vessels;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;
import typex29.applet.Main;
import typex29.bullets.Bullet;
import typex29.panels.GamePanel;

/**
 *
 * @author tsuki
 */
public abstract class Vessel {
    Image image;
    double xPos;
    double yPos;
    double speed;
    double angle;
    boolean underwater;
    final Main applet;
    final GamePanel panel;
    boolean destroyed;

    public Vessel(String imageName, double xPos, double yPos, double speed, double angle, boolean underwater, Main applet, GamePanel panel) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = speed;
        this.angle = (angle/360.0)*2*Math.PI;
        this.underwater = underwater;
        this.panel = panel;
        this.applet = applet;
        this.image = applet.getResource(imageName);
    }

    public void iterate() {
        xPos = xPos + (speed * Math.sin(angle));
        yPos = yPos - (speed * Math.cos(angle));

        destroyed = destroyed || isOffEdge();
        for (Bullet b : panel.getPlayerBullets()) {
            destroyed = destroyed || isBeingHitBy(b);
        }
    }

    public void render(Graphics2D g2d, JPanel observer) {
        g2d.drawImage(image, (int)xPos, (int)yPos, observer);
    }

    public boolean isOffEdge() {
        if (underwater) {
            return ((xPos<-200) || (xPos > 1000) || (yPos < 250) || (yPos > 600));
        } else {
            return ((xPos<-200) || (xPos > 1000) || (yPos < -100) || (yPos > 250));
        }
    }

    boolean isBeingHitBy(Bullet b) {
        double thisBack = xPos + image.getWidth(null);
        double thisBottom = yPos + image.getHeight(null);
        if ((b.getxPos() > xPos) && (b.getxPos() < thisBack) && (b.getyPos() > yPos) && (b.getyPos() < thisBottom)) {
            b.setDestroyed(true);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the destroyed
     */
    public boolean isDestroyed() {
        return destroyed;
    }
}
