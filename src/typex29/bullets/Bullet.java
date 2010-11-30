package typex29.bullets;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author irenton
 */
public abstract class Bullet {

    Image image;
    private double xPos;
    private double yPos;
    private double speed;
    private double angle;
    private boolean underwater;
    private boolean destroyed;

    public Bullet(double xPos, double yPos, double speed, double angle, boolean underwater) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = speed;
        this.angle = (angle/360.0)*2*Math.PI;
        this.underwater = underwater;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void iterate() {
        xPos = xPos + (speed * Math.sin(angle));
        yPos = yPos - (speed * Math.cos(angle));

        destroyed = destroyed || isOffEdge();
    }

    public void render(Graphics2D g2d, JPanel observer) {
        g2d.drawImage(image, (int)xPos, (int)yPos, observer);
    }

    public boolean isOffEdge() {
        if (underwater) {
            return ((xPos<0) || (xPos > 800) || (yPos < 250) || (yPos > 500));
        } else {
            return ((xPos<0) || (xPos > 800) || (yPos < 0) || (yPos > 250));
        }
    }

    /**
     * @return the xPos
     */
    public double getxPos() {
        return xPos;
    }

    /**
     * @return the yPos
     */
    public double getyPos() {
        return yPos;
    }

    /**
     * @return the destroyed
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * @param destroyed the destroyed to set
     */
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

}
