package UI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panel extends JPanel {
    private ImageIcon icon; // imagen
    private Image scaledImage; // imagen

    public Panel() {
        icon = new ImageIcon("img/world_map.png"); // Ruta actualizada
        scaleImage();
    }

    private void scaleImage() {
        if (getWidth() > 0 && getHeight() > 0) {
            scaledImage = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (scaledImage != null) {
            g.drawImage(scaledImage, 0, 0, this);
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        scaleImage();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(icon.getIconWidth(), icon.getIconHeight());
    }
}
