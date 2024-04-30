	package UI;
	
	import java.awt.*;
	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.ItemEvent;
	import java.awt.event.ItemListener;
	
	import javax.swing.*;
	import javax.swing.ImageIcon;
	import javax.swing.JPanel;
	import javax.swing.JRadioButton;
	
	import Clases.CiudadBoton;
import Clases.Ciudades;
import programa.TxtCiudades;
	
	public class PanelMapa extends JPanel implements ActionListener {
		
	
	
	    private ImageIcon icon;
	    private Image scaledImage;
	    ButtonGroup IconosCiudades = new ButtonGroup();
	
	
	    public PanelMapa() { // testea botones si funcionan todos con print
	        icon = new ImageIcon("img/mapa.jpg");  //Ruta actualizada
	       scaleImage();
	       setLayout(null);
	       setDoubleBuffered(true);
	       
	     
	       CiudadBoton SanFrancisco = new CiudadBoton(linkearCiudad("San Francisco"));
	       crearBotonciudad(SanFrancisco, "img/IconoCiudadAzul.png", 100, 295, 35, 35);

	       CiudadBoton Chicago = new CiudadBoton(linkearCiudad("Chicago"));
	       crearBotonciudad(Chicago, "img/IconoCiudadAzul.png", 269, 279, 35, 35);

	       CiudadBoton Atlanta = new CiudadBoton(linkearCiudad("Atlanta"));
	       crearBotonciudad(Atlanta, "img/IconoCiudadAzul.png", 270, 315, 35, 35);

	       CiudadBoton Montreal = new CiudadBoton(linkearCiudad("Montreal"));
	       crearBotonciudad(Montreal, "img/IconoCiudadAzul.png", 350, 230, 35, 35);

	       CiudadBoton NuevaYork = new CiudadBoton(linkearCiudad("Nueva York"));
	       crearBotonciudad(NuevaYork, "img/IconoCiudadAzul.png", 350, 280, 35, 35);

	       CiudadBoton Washington = new CiudadBoton(linkearCiudad("Washington"));
	       crearBotonciudad(Washington, "img/IconoCiudadAzul.png", 310, 300, 35, 35);

	       CiudadBoton Londres = new CiudadBoton(linkearCiudad("Londres"));
	       crearBotonciudad(Londres, "img/IconoCiudadAzul.png", 700, 220, 35, 35);

	       CiudadBoton Madrid = new CiudadBoton(linkearCiudad("Madrid"));
	       crearBotonciudad(Madrid, "img/IconoCiudadAzul.png", 687, 290, 35, 35);

	       CiudadBoton Paris = new CiudadBoton(linkearCiudad("Paris"));
	       crearBotonciudad(Paris, "img/IconoCiudadAzul.png", 727, 250, 35, 35);

	       CiudadBoton Essen = new CiudadBoton(linkearCiudad("Essen"));
	       crearBotonciudad(Essen, "img/IconoCiudadAzul.png", 740, 225, 35, 35);

	       CiudadBoton Milan = new CiudadBoton(linkearCiudad("Milan"));
	       crearBotonciudad(Milan, "img/IconoCiudadAzul.png", 755, 255, 35, 35);

	       CiudadBoton SanPetersburgo = new CiudadBoton(linkearCiudad("San Petersburgo"));
	       crearBotonciudad(SanPetersburgo, "img/IconoCiudadAzul.png", 835, 200, 35, 35);

	       CiudadBoton LosAngeles = new CiudadBoton(linkearCiudad("Los Angeles"));
	       crearBotonciudad(LosAngeles, "img/IconoCiudadAmarillo.png", 110, 325, 35, 35);

	       CiudadBoton Miami = new CiudadBoton(linkearCiudad("Miami"));
	       crearBotonciudad(Miami, "img/IconoCiudadAmarillo.png", 290, 370, 35, 35);

	       // Continuar con el resto de los botones usando linkearCiudad
	       CiudadBoton MexicoDF = new CiudadBoton(linkearCiudad("Mexico DF"));
	       crearBotonciudad(MexicoDF, "img/IconoCiudadAmarillo.png", 180, 410, 35, 35);

	       CiudadBoton Bogota = new CiudadBoton(linkearCiudad("Bogota"));
	       crearBotonciudad(Bogota, "img/IconoCiudadAmarillo.png", 305, 490, 35, 35);

	       CiudadBoton Lima = new CiudadBoton(linkearCiudad("Lima"));
	       crearBotonciudad(Lima, "img/IconoCiudadAmarillo.png", 290, 575, 35, 35);

	       CiudadBoton Santiago = new CiudadBoton(linkearCiudad("Santiago"));
	       crearBotonciudad(Santiago, "img/IconoCiudadAmarillo.png", 345, 685, 35, 35);

	       CiudadBoton BuenosAires = new CiudadBoton(linkearCiudad("Buenos Aires"));
	       crearBotonciudad(BuenosAires, "img/IconoCiudadAmarillo.png", 410, 720, 35, 35);

	       CiudadBoton SaoPaulo = new CiudadBoton(linkearCiudad("São Paulo"));
	       crearBotonciudad(SaoPaulo, "img/IconoCiudadAmarillo.png", 460, 650, 35, 35);

	       CiudadBoton Lagos = new CiudadBoton(linkearCiudad("Lagos"));
	       crearBotonciudad(Lagos, "img/IconoCiudadAmarillo.png", 665, 305, 35, 35);

	       CiudadBoton Kinsasa = new CiudadBoton(linkearCiudad("Kinshasa"));
	       crearBotonciudad(Kinsasa, "img/IconoCiudadAmarillo.png", 795, 540, 35, 35);

	       CiudadBoton Jartum = new CiudadBoton(linkearCiudad("Jartum"));
	       crearBotonciudad(Jartum, "img/IconoCiudadAmarillo.png", 875, 430, 35, 35);

	       CiudadBoton Johannesburgo = new CiudadBoton(linkearCiudad("Johannesburgo"));
	       crearBotonciudad(Johannesburgo, "img/IconoCiudadAmarillo.png", 840, 655, 35, 35);

	       CiudadBoton Argel = new CiudadBoton(linkearCiudad("Argel"));
	       crearBotonciudad(Argel, "img/IconoCiudadVerde.png", 725, 330, 35, 35);

	       CiudadBoton ElCairo = new CiudadBoton(linkearCiudad("El Cairo"));
	       crearBotonciudad(ElCairo, "img/IconoCiudadVerde.png", 855, 365, 35, 35);

	       // Continúa con el resto de los botones usando linkearCiudad
	       CiudadBoton Riad = new CiudadBoton(linkearCiudad("Riad"));
	       crearBotonciudad(Riad, "img/IconoCiudadVerde.png", 945, 385, 35, 35);

	       CiudadBoton Estambul = new CiudadBoton(linkearCiudad("Estambul"));
	       crearBotonciudad(Estambul, "img/IconoCiudadVerde.png", 835, 290, 35, 35);

	       CiudadBoton Bagdad = new CiudadBoton(linkearCiudad("Bagdad"));
	       crearBotonciudad(Bagdad, "img/IconoCiudadVerde.png", 925, 340, 35, 35);

	       CiudadBoton Moscu = new CiudadBoton(linkearCiudad("Moscu"));
	       crearBotonciudad(Moscu, "img/IconoCiudadVerde.png", 895, 215, 35, 35);

	       CiudadBoton Teheran = new CiudadBoton(linkearCiudad("Teheran"));
	       crearBotonciudad(Teheran, "img/IconoCiudadVerde.png", 965, 325, 35, 35);

	       CiudadBoton Karachi = new CiudadBoton(linkearCiudad("Karachi"));
	       crearBotonciudad(Karachi, "img/IconoCiudadVerde.png", 1050, 375, 35, 35);

	       CiudadBoton Bombay = new CiudadBoton(linkearCiudad("Bombay"));
	       crearBotonciudad(Bombay, "img/IconoCiudadVerde.png", 1100, 415, 35, 35);

	       CiudadBoton NuevaDelhi = new CiudadBoton(linkearCiudad("Nueva Delhi"));
	       crearBotonciudad(NuevaDelhi, "img/IconoCiudadVerde.png", 1120, 370, 35, 35);

	       CiudadBoton Calcuta = new CiudadBoton(linkearCiudad("Calcuta"));
	       crearBotonciudad(Calcuta, "img/IconoCiudadVerde.png", 1160, 405, 35, 35);

	       CiudadBoton Madras = new CiudadBoton(linkearCiudad("Madras"));
	       crearBotonciudad(Madras, "img/IconoCiudadVerde.png", 1130, 450, 35, 35);

	       CiudadBoton Yakarta = new CiudadBoton(linkearCiudad("Yakarta"));
	       crearBotonciudad(Yakarta, "img/IconoCiudadRojo.png", 1285, 565, 35, 35);

	       CiudadBoton Bangkok = new CiudadBoton(linkearCiudad("Bangkok"));
	       crearBotonciudad(Bangkok, "img/IconoCiudadRojo.png", 1245, 450, 35, 35);

	       CiudadBoton HongKong = new CiudadBoton(linkearCiudad("Hong Kong"));
	       crearBotonciudad(HongKong, "img/IconoCiudadRojo.png", 1315, 395, 35, 35);

	       CiudadBoton Shanghai = new CiudadBoton(linkearCiudad("Shanghai"));
	       crearBotonciudad(Shanghai, "img/IconoCiudadRojo.png", 1325, 350, 35, 35);

	       CiudadBoton Pekin = new CiudadBoton(linkearCiudad("Pekin"));
	       crearBotonciudad(Pekin, "img/IconoCiudadRojo.png", 1290, 295, 35, 35);

	       CiudadBoton Seul = new CiudadBoton(linkearCiudad("Seul"));
	       crearBotonciudad(Seul, "img/IconoCiudadRojo.png", 1350, 320, 35, 35);

	       CiudadBoton Tokio = new CiudadBoton(linkearCiudad("Tokio"));
	       crearBotonciudad(Tokio, "img/IconoCiudadRojo.png", 1415, 315, 35, 35);

	       CiudadBoton Osaka = new CiudadBoton(linkearCiudad("Osaka"));
	       crearBotonciudad(Osaka, "img/IconoCiudadRojo.png", 1390, 330, 35, 35);

	       CiudadBoton Taipei = new CiudadBoton(linkearCiudad("Taipei"));
	       crearBotonciudad(Taipei, "img/IconoCiudadRojo.png", 1350, 375, 35, 35);

	       CiudadBoton HoChiMinh = new CiudadBoton(linkearCiudad("Ho Chi Minh"));
	       crearBotonciudad(HoChiMinh, "img/IconoCiudadRojo.png", 1280, 450, 35, 35);

	       CiudadBoton Manila = new CiudadBoton(linkearCiudad("Manila"));
	       crearBotonciudad(Manila, "img/IconoCiudadRojo.png", 1350, 440, 35, 35);

	       CiudadBoton Sidney = new CiudadBoton(linkearCiudad("Sídney"));
	       crearBotonciudad(Sidney, "img/IconoCiudadRojo.png", 1480, 710, 35, 35);
       
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
	    
	    public void crearBotonciudad(CiudadBoton Ciudad, String rutaimg, int x, int y, int w, int h) {
	
	        Ciudad.setOpaque(false);
	        Ciudad.setBorderPainted(false);
	        Ciudad.setContentAreaFilled(false);
	        Ciudad.setBounds(x, y, w, h );
	        ImageIcon icono = new ImageIcon(new ImageIcon(rutaimg).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
	        Ciudad.setIcon(icono);
	        IconosCiudades.add(Ciudad);
	        
	        
	        add(Ciudad);
	        Ciudad.addActionListener(this);
	    }
	    
	    private static Ciudades linkearCiudad(String nombre) {
			return null;
	    	
	    }
	    
	    public void actionPerformed(ActionEvent e) {
	    	CiudadBoton selectedButton = (CiudadBoton) e.getSource();
	
	    	if (e.getSource() instanceof CiudadBoton) {
	            
	        } 
	    }
  
	}
