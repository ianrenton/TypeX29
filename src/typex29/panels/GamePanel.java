package typex29.panels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import typex29.applet.Main;
import typex29.bullets.Bullet;
import typex29.incidentals.Cloud;
import typex29.incidentals.Incidental;
import typex29.vessels.B2;
import typex29.vessels.F16;
import typex29.vessels.PlayerShip;
import typex29.vessels.Trafalgar;
import typex29.vessels.Vessel;

/**
 *
 * @author irenton
 */
public class GamePanel extends JPanel {

    private Main applet;
    private Timer updateTimer;
    private Timer eventTimer;
    private int time;
    private Image background;
    private PlayerShip playerShip;
    private ArrayList<Bullet> playerBullets = new ArrayList<Bullet>();
    private ArrayList<Bullet> enemyBullets = new ArrayList<Bullet>();
    private ArrayList<Vessel> enemyVessels = new ArrayList<Vessel>();
    private ArrayList<Incidental> incidentals = new ArrayList<Incidental>();


    private class Updater extends TimerTask {

        @Override
        public void run() {
            playerShip.iterate();
            for (int i = 0; i < playerBullets.size(); i++) {
                playerBullets.get(i).iterate();
                if (playerBullets.get(i).isDestroyed()) {
                    playerBullets.remove(i);
                }
            }
            for (int i = 0; i < enemyBullets.size(); i++) {
                enemyBullets.get(i).iterate();
                if (enemyBullets.get(i).isDestroyed()) {
                    enemyBullets.remove(i);
                }
            }
            for (int i = 0; i < enemyVessels.size(); i++) {
                enemyVessels.get(i).iterate();
                if (enemyVessels.get(i).isDestroyed()) {
                    enemyVessels.remove(i);
                }
            }
            for (int i = 0; i < incidentals.size(); i++) {
                incidentals.get(i).iterate();
                if (incidentals.get(i).isDestroyed()) {
                    incidentals.remove(i);
                }
            }
            repaint();
        }
    }

    private class EventTimer extends TimerTask {

        private int nextTrafalgarTime = 5;
        private int nextB2Time = 10;
        private int nextF16Time = 15;
        private int nextCloudTime = 0;
        private Random r = new Random();
        private final GamePanel panel;

        EventTimer(GamePanel panel) {
            this.panel = panel;
        }

        @Override
        public void run() {
            if (time == nextTrafalgarTime) {
                boolean dir = false; //r.nextBoolean();
                double xPos = dir ? -100 : 800;
                double yPos = r.nextInt(150) + 300;
                double speed = r.nextDouble() + 0.5;
                double angle = dir ? 90 : 270;
                enemyVessels.add(new Trafalgar(xPos, yPos, speed, angle, applet, panel));
                nextTrafalgarTime += (2 + r.nextInt(10));
            }
            if (time == nextB2Time) {
                boolean dir = false; //r.nextBoolean();
                double xPos = dir ? -100 : 800;
                double yPos = 20;
                double speed = r.nextDouble() + 1.5;
                double angle = dir ? 90 : 270;
                enemyVessels.add(new B2(xPos, yPos, speed, angle, applet, panel));
                nextB2Time += (3 + r.nextInt(10));
            }
            if (time == nextF16Time) {
                boolean dir = false; //r.nextBoolean();
                double xPos = dir ? -100 : 800;
                double yPos = r.nextInt(100) + 50;
                double speed = r.nextDouble() + 3.5;
                double angle = dir ? 90 : 270;
                enemyVessels.add(new F16(xPos, yPos, speed, angle, applet, panel));
                nextF16Time += (3 + r.nextInt(10));
            }
            if (time == nextCloudTime) {
                boolean dir = false;
                double xPos = dir ? -200 : 800;
                double yPos = r.nextInt(150);
                double speed = 0.5 + (yPos / 300);
                double angle = dir ? 90 : 270;
                incidentals.add(new Cloud(xPos, yPos, speed, angle, applet, panel));
                nextCloudTime += (1 + r.nextInt(1));
            }
            if (!playerShip.isDestroyed()) {
                time++;
            }
        }
    }

    public GamePanel(Main applet) {
        this.applet = applet;
    }

    public void start() {
        playerShip = new PlayerShip(applet, this);
        background = applet.getResource("BG Ocean Day");

        updateTimer = new Timer();
        updateTimer.scheduleAtFixedRate(new Updater(), 0, 20);
        eventTimer = new Timer();
        eventTimer.scheduleAtFixedRate(new EventTimer(this), 0, 1000);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Typical Swing approach
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(background, 0, 0, this);
        for (Incidental i : incidentals) {
            i.render(g2d, this);
        }
        playerShip.render(g2d, this);
        for (Bullet b : playerBullets) {
            b.render(g2d, this);
        }
        for (Bullet b : enemyBullets) {
            b.render(g2d, this);
        }
        for (Vessel v : enemyVessels) {
            v.render(g2d, this);
        }
        g2d.drawString("Survived for " + time + " seconds.", 5, 15);
    }
    /**
     * @return the playerShip
     */
    public PlayerShip getPlayerShip() {
        return playerShip;
    }

    public void addPlayerBullet(Bullet bullet) {
        playerBullets.add(bullet);
    }

    public void addEnemyBullet(Bullet bullet) {
        enemyBullets.add(bullet);
    }

    public void addEnemyVessel(Vessel vessel) {
        enemyVessels.add(vessel);
    }

    /**
     * @return the playerBullets
     */
    public ArrayList<Bullet> getPlayerBullets() {
        return playerBullets;
    }

    /**
     * @return the enemyBullets
     */
    public ArrayList<Bullet> getEnemyBullets() {
        return enemyBullets;
    }

    /**
     * @return the enemyVessels
     */
    public ArrayList<Vessel> getEnemyVessels() {
        return enemyVessels;
    }
}
