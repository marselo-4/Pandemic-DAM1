package UI;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import Clases.controlDatos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Puntuaciones extends JPanel implements ActionListener { // Pantalla puntuaciones

    JButton volver; // Boton volver
    private Image Fondo; // fondo de la pantalla
    JRadioButton facilRadio, normalRadio, dificilRadio; // botones 
    ButtonGroup dificultadGroup; // Botones dificultad
   static JTextArea textArea;

    
    public Puntuaciones() {
        setLayout(new FlowLayout());
        try {
            Fondo = ImageIO.read(new File("src/assets/scr_puntuaciones.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        volver = new JButton();
        volver.setIcon(new ImageIcon(new ImageIcon("src/assets/5.png").getImage().getScaledInstance(300, 30, java.awt.Image.SCALE_SMOOTH)));
        volver.addActionListener(this);
        volver.setContentAreaFilled(false);
        volver.setBorderPainted(false);
        volver.setFocusPainted(false);
        
        BotoneraYCajatxtRanking();

        
        



    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image fondoescalado = Fondo.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);

        // Dibujamos la imagen de fondo
        g.drawImage(fondoescalado, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Cargar el archivo de sonido
            File soundFile = new File("src/assets/gfx/selec.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            
            // Obtener un clip de sonido
            Clip clip = AudioSystem.getClip();
            
            // Abrir el flujo de audio y cargar datos
            clip.open(audioIn);
            
            // Reproducir el sonido
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
            e1.printStackTrace();
        }
        if (e.getSource() == volver) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Puntuaciones.this);
            frame.remove(Puntuaciones.this);
            frame.add(new PanelPrincipal()); // Suponiendo que PreviousPanel es el panel al que quieres volver
            frame.setVisible(true);
        } else if (e.getSource() == facilRadio) {
        	controlDatos.cargarRecordFacil(controlDatos.con);
        } else if (e.getSource() == normalRadio) {
        	controlDatos.cargarRecordNormal(controlDatos.con);
        }else if (e.getSource() == dificilRadio) {
        	controlDatos.cargarRecordDificil(controlDatos.con);

        }
    }
    
    public void BotoneraYCajatxtRanking () {

    	facilRadio = new JRadioButton();
    	facilRadio.setIcon(new ImageIcon(new ImageIcon("src/assets/facil.png").getImage().getScaledInstance(180, 180, java.awt.Image.SCALE_SMOOTH)));
    	facilRadio.addActionListener(this);
    	facilRadio.setContentAreaFilled(false);
    	facilRadio.setBorderPainted(false);
    	facilRadio.setFocusPainted(false);
        add(facilRadio);

        normalRadio = new JRadioButton();
        normalRadio.setIcon(new ImageIcon(new ImageIcon("src/assets/medio.png").getImage().getScaledInstance(180, 180, java.awt.Image.SCALE_SMOOTH)));
        normalRadio.addActionListener(this);
        normalRadio.setContentAreaFilled(false);
        normalRadio.setBorderPainted(false);
        normalRadio.setFocusPainted(false);
        add(normalRadio);

        dificilRadio = new JRadioButton();
        dificilRadio.setIcon(new ImageIcon(new ImageIcon("src/assets/dificil.png").getImage().getScaledInstance(180, 180, java.awt.Image.SCALE_SMOOTH)));
        dificilRadio.addActionListener(this);
        dificilRadio.setContentAreaFilled(false);
        dificilRadio.setBorderPainted(false);
        dificilRadio.setFocusPainted(false);
        add(dificilRadio);
        
        textArea = new JTextArea(20, 60);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setForeground(Color.WHITE); 
        add(textArea);
        
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(200)); // Añadimos margen
        box.add(facilRadio);
        box.add(Box.createVerticalStrut(50)); // Añadimos margen
        box.add(normalRadio);
        box.add(Box.createVerticalStrut(50)); // Añadimos margen
        box.add(dificilRadio);
        box.add(Box.createVerticalStrut(50)); // Añadimos margen
        
        Box box2 = Box.createHorizontalBox();
        box2.add(Box.createHorizontalStrut(50));
        box2.add(volver);
        
        add(Box.createVerticalGlue());
        add(box);
        add(Box.createVerticalGlue());
        
        add(Box.createVerticalGlue());
        add(box2);
        add(Box.createVerticalGlue());
        File fontFile = new File("fonts/DisposableDroidBB.ttf");
        Font fuentePersonalizada = null;
        
        try {
            fuentePersonalizada = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            fuentePersonalizada = fuentePersonalizada.deriveFont(29f); 
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        textArea.setFont(fuentePersonalizada);
        
        textArea.setText("SELECCIONE UNA DIFICULTAD PARA MOSTRAR LAS 10 MEJORES PUNTUACIONES");

    }
    
    public static void textosCajaRanking(String texto) {
		// Creamos un hilo para el efecto de escritura
//		new Thread(() -> {
//			for (int i = 0; i <= texto.length(); i++) {
//				final int index = i;
//				// Actualizamos la caja de texto en el hilo de la interfaz de usuario (Swing)
//				SwingUtilities.invokeLater(() -> {
//					textArea.setText(texto.substring(0, index));
//				});
//				try {
//					// Simulamos un pequeño retraso entre cada caracter para el efecto de escritura
//					TimeUnit.MILLISECONDS.sleep(5);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
    	textArea.setText(texto);
	}
    
}
