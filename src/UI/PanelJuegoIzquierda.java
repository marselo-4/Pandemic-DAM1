package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PanelJuegoIzquierda extends JPanel{

    public PanelJuegoIzquierda() {
        setLayout(new FlowLayout());

        this.setBackground(new Color(20, 20, 30)); // Fondo negro azulado
        setPreferredSize(new Dimension(130, 400));
        setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 3));

    }

}