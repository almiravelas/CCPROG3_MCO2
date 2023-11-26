//for the bg image

import javax.swing.*;
import java.awt.*;
public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    // Constructor to set the image as background
    public BackgroundPanel(String filename) {
        backgroundImage = new ImageIcon(filename).getImage();
    }

    // Overriding the paintComponent method to draw the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}