package typex29.panels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;
import typex29.applet.Main;

/**
 *
 * @author irenton
 */
public class MenuPanel extends JPanel {

    private Main applet;

    private Image background;

    public MenuPanel(Main applet) {
        this.applet = applet;
        background = applet.getResource("BG Ocean Day");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Typical Swing approach
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(background, 0, 0, this);
        g2d.drawString("Type X29", 5, 15);
        g2d.drawString("Click to start.", 5, 30);
        g2d.drawString("Left/right arrows move your ship.", 5, 45);
        g2d.drawString("Z = depth charges, X = cannon, C = surface-to-air missiles", 5, 60);
        g2d.drawString("Kill stuff, don't get killed.", 5, 75);
        g2d.drawString("This game is in very early development.  Gameplay is not representative, graphics not finished etc.", 5, 90);
    }

}
