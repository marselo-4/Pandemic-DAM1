	package UI;
	
	import java.awt.*;
	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.ItemEvent;
	import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
	import javax.swing.ImageIcon;
	import javax.swing.JPanel;
	import javax.swing.JRadioButton;

import Backend.TxtCiudades;
import Backend.logicaJuego;
import Clases.CiudadBoton;
import Clases.Ciudades;
import Clases.controlDatos;
	
	public class PanelMapa extends JPanel implements ActionListener { // Panel del mapa
		public static ImageIcon ciudad_azul0 = new ImageIcon(new ImageIcon("img/IconoCiudadAzul.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_azul1 = new ImageIcon(new ImageIcon("img/IconoCiudadAzul1.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_azul2 = new ImageIcon(new ImageIcon("img/IconoCiudadAzul2.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_azul3 = new ImageIcon(new ImageIcon("img/IconoCiudadAzul3.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_azulBrote = new ImageIcon(new ImageIcon("img/IconoCiudadAzulBrote.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

		public static ImageIcon ciudad_verde0 = new ImageIcon(new ImageIcon("img/iconoCiudadVerde.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_verde1 = new ImageIcon(new ImageIcon("img/iconoCiudadVerde1.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_verde2 = new ImageIcon(new ImageIcon("img/iconoCiudadVerde2.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_verde3 = new ImageIcon(new ImageIcon("img/iconoCiudadVerde3.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_verdeBrote = new ImageIcon(new ImageIcon("img/iconoCiudadVerdeBrote.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

		public static ImageIcon ciudad_roja0 = new ImageIcon(new ImageIcon("img/IconoCiudadRojo.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_roja1 = new ImageIcon(new ImageIcon("img/IconoCiudadRojo1.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_roja2 = new ImageIcon(new ImageIcon("img/IconoCiudadRojo2.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_roja3 = new ImageIcon(new ImageIcon("img/IconoCiudadRojo3.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_rojaBrote = new ImageIcon(new ImageIcon("img/IconoCiudadRojoBrote.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

		public static ImageIcon ciudad_amarilla0 = new ImageIcon(new ImageIcon("img/IconoCiudadAmarillo.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_amarilla1 = new ImageIcon(new ImageIcon("img/IconoCiudadAmarillo1.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_amarilla2 = new ImageIcon(new ImageIcon("img/IconoCiudadAmarillo2.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		public static ImageIcon ciudad_amarilla3 = new ImageIcon(new ImageIcon("img/IconoCiudadAmarillo3.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));		
		public static ImageIcon ciudad_amarillaBrote = new ImageIcon(new ImageIcon("img/IconoCiudadAmarilloBrote.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));		

		public static CiudadBoton ciudadSeleccionada;
	    private ImageIcon icon; // icono
	    private Image scaledImage; // imagen
	    public static ButtonGroup IconosCiudades = new ButtonGroup();
	    public static ArrayList<CiudadBoton> botonesCiudadesArray = new ArrayList<>();
	
	
	    public PanelMapa() { // testea botones si funcionan todos con print
	       icon = new ImageIcon("img/mapa.jpg");  //Ruta actualizada
	       scaleImage();
	       setLayout(null);
	       setDoubleBuffered(true);
	       

       
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
	    

	    
//	    private static Ciudades linkearCiudad(String nombre) {
//	    	for (int i = 0; i < array.length; i++) {
//				
//			}
//			return null;
//	    	
//	    }
	    
	    public void actionPerformed(ActionEvent e) {
	    	CiudadBoton selectedButton = (CiudadBoton) e.getSource();
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
	
	    	if (e.getSource() instanceof CiudadBoton) {
	    		logicaJuego.textosCaja("Ciudad " + selectedButton.getCiudad().getNombre() + " seleccionada. Su nivel de infeccion es "
	    				+ selectedButton.getCiudad().getInfeccion()); 
	    		ciudadSeleccionada = selectedButton;
	        } 
	    }
  
	}
