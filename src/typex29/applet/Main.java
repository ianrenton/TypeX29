package typex29.applet;

import java.awt.CardLayout;
import java.awt.Image;
import java.util.HashMap;
import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import typex29.panels.GamePanel;
import typex29.panels.MenuPanel;

/**
 *
 * @author irenton
 */
public class Main extends JApplet {

    private AppletKeyListener listener = new AppletKeyListener(this);
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private HashMap<String, Image> resources = new HashMap<String, Image>();
    private String mode = "Menu";

    @Override
    public void init() {
        // Load resources
        // Vessels
        resources.put("Type X29", getImage(getCodeBase(), "resources/typex29.png"));
        resources.put("Type X29 (Destroyed)", getImage(getCodeBase(), "resources/typex29-destroyed.png"));
        resources.put("Trafalgar", getImage(getCodeBase(), "resources/trafalgar.png"));
        resources.put("B2", getImage(getCodeBase(), "resources/b2.png"));
        resources.put("F16", getImage(getCodeBase(), "resources/f16.png"));
        // Bullets
        resources.put("SAM Vertical", getImage(getCodeBase(), "resources/sam.png"));
        resources.put("SAM 30deg", getImage(getCodeBase(), "resources/sam-30deg.png"));
        resources.put("Shell", getImage(getCodeBase(), "resources/shell.png"));
        resources.put("Depth Charge", getImage(getCodeBase(), "resources/depthcharge.png"));
        resources.put("Spearfish", getImage(getCodeBase(), "resources/spearfish.png"));
        resources.put("JDAM", getImage(getCodeBase(), "resources/jdam.png"));
        resources.put("Harpoon", getImage(getCodeBase(), "resources/harpoon-240deg.png"));
        // Incidentals
        resources.put("Cloud 1", getImage(getCodeBase(), "resources/cloud1.png"));
        resources.put("Cloud 2", getImage(getCodeBase(), "resources/cloud2.png"));
        resources.put("Cloud 3", getImage(getCodeBase(), "resources/cloud3.png"));
        resources.put("Cloud 4", getImage(getCodeBase(), "resources/cloud4.png"));
        resources.put("Cloud 5", getImage(getCodeBase(), "resources/cloud5.png"));
        // Backgrounds
        resources.put("BG Ocean Day", getImage(getCodeBase(), "resources/bg-ocean-day.png"));

        //Execute a job on the event-dispatching thread; creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {

                public void run() {
                    createGUI();
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }

        // Bind keyboard & mouse listeners
        this.addKeyListener(listener);
        this.addMouseListener(listener);

    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void destroy() {
    }

    private void createGUI() {
        //Create and set up the content pane.
        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(this);
        getContentPane().setLayout(new java.awt.CardLayout());
        mode = "Menu";
        getContentPane().add(menuPanel, "menuPanel");
        getContentPane().add(gamePanel, "gamePanel");
    }

    public Image getResource(String name) {
        return resources.get(name);
    }


    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    void startGame() {
        mode = "Game";
        ((CardLayout)(getContentPane().getLayout())).show(this.getContentPane(), "gamePanel");
        gamePanel.start();
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }
}
