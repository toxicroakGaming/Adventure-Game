import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(Image image) {
        this.backgroundImage = image;
        // Optional: set size if you're using null layout
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image scaled to fill the panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}