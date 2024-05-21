package Backend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import Clases.CiudadBoton;
import Clases.Ciudades;
import Clases.Vacuna;
import Clases.controlDatos;
import Clases.controlPartida;
import Clases.datosPartida;
import UI.Informacion;
import UI.LanzadorPartida;
import UI.NuevaPartida;
import UI.PanelBotonesMenuAbajo;
import UI.PanelJuegoDerecha;
import UI.PanelJuegoIzquierda;
import UI.PanelMapa;
import UI.PanelPrincipal;
import UI.Puntuaciones;
import UI.hasGanado;
import UI.hasPerdido;

public class logicaJuego {
	public static controlDatos cd = new controlDatos();
	public static datosPartida dp = new datosPartida();
	public static controlPartida cp = new controlPartida();
	

	// LÓGICA GENERAL PARA LA PARTIDA


	// Crear Array Ciudades - Extreu del txt info sobre ciutats, genera els objectes i els exporta a la arraylist
	public static void crearArrayCiudades() {
		String linea = "";

		String nombreFichero = "ciudades.txt";
		try {
			// Creamos un objeto de tipo FileReader para abrir un fichero de lectura
			FileReader fileReader = new FileReader(nombreFichero);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			do {
				// Leemos el contenido del fichero
				linea = bufferedReader.readLine();
				if (linea != null) {

					dp.ciudades.add(TxtCiudades.asignarCiudades(linea));

				}

			} while (linea != null);
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			System.out.println("Ha habido un error al intentar abrir el fichero" + e);
		}

	}

	// Funció que crea els textos de la caixa, agafant una string per parametre i 
	// els mostra a la caixa inferior fent servir un efecte d'escriptura
	public static void textosCaja(String texto) {
		// Creamos un hilo para el efecto de escritura
		new Thread(() -> {
			for (int i = 0; i <= texto.length(); i++) {
				final int index = i;
				// Actualizamos la caja de texto en el hilo de la interfaz de usuario (Swing)
				SwingUtilities.invokeLater(() -> {
					PanelBotonesMenuAbajo.Caja.setText(texto.substring(0, index));
					PanelBotonesMenuAbajo.Caja.setForeground(Color.WHITE);
				});
				try {
					// Simulamos un pequeño retraso entre cada caracter para el efecto de escritura
					TimeUnit.MILLISECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	// El mateix pero amb color diferent amb SetForeground(color) per param
	public static void textosCajaColor(String texto, Color color) {
		// Creamos un hilo para el efecto de escritura
		new Thread(() -> {
			for (int i = 0; i <= texto.length(); i++) {
				final int index = i;
				// Actualizamos la caja de texto en el hilo de la interfaz de usuario (Swing)
				SwingUtilities.invokeLater(() -> {
					PanelBotonesMenuAbajo.Caja.setText(texto.substring(0, index));
					PanelBotonesMenuAbajo.Caja.setForeground(color);
				});
				try {
					// Simulamos un pequeño retraso entre cada caracter para el efecto de escritura
					TimeUnit.MILLISECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	// FUNCIONES PARA CREAR Y AGREGAR FUNCIONES A LOS BOTONES
	public static void crearBotonesCiudades() {
		for (int i = 0; i < dp.ciudades.size(); i++) {
			String rutaImg = "";
			switch (dp.ciudades.get(i).getEnfermedad()) {
			case "Alfa":
				rutaImg = "img/IconoCiudadAzul.png";
				break;
			case "Beta":
				rutaImg = "img/IconoCiudadRojo.png";
				break;
			case "Gama":
				rutaImg = "img/IconoCiudadVerde.png";
				break;
			case "Delta":
				rutaImg = "img/IconoCiudadAmarillo.png";
				break;

			default:
				break;
			}

			CiudadBoton c = new CiudadBoton();
			c.setCiudad(dp.ciudades.get(i));
			crearBotonciudad(LanzadorPartida.p, c, rutaImg, dp.ciudades.get(i).getCoords()[0],
					dp.ciudades.get(i).getCoords()[1], 35, 35);

		}
	}
	
	// Crea el boton/icono de la ciudad -- Entra por parametro el panel, la ciudad y la imagen de fondo + pos + tamaño
	public static void crearBotonciudad(PanelMapa p, CiudadBoton Ciudad, String rutaimg, int x, int y, int w, int h) {

		Ciudad.setOpaque(false);
		Ciudad.setBorderPainted(false);
		Ciudad.setContentAreaFilled(false);
		Ciudad.setBounds(x, y, w, h);
		ImageIcon icono = new ImageIcon(new ImageIcon(rutaimg).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
		Ciudad.setIcon(icono);
		PanelMapa.IconosCiudades.add(Ciudad);

		p.add(Ciudad);
		// System.out.println("Botón " + rutaimg + "se intentó crear en " + p);
		Ciudad.addActionListener(p);
		PanelMapa.botonesCiudadesArray.add(Ciudad);
	}

	// CREAR VACUNAS -- crea 4 obj de vacunes i les mostra al panell dret
	public static void crearVacunas(ButtonGroup vacunas, PanelJuegoDerecha p) {

		Vacuna v = new Vacuna("Alpha", "azul", 0);
		Vacuna v2 = new Vacuna("Beta", "verde", 0);
		Vacuna v3 = new Vacuna("Gamma", "rojo", 0);
		Vacuna v4 = new Vacuna("Delta", "amarillo", 0);

		PanelJuegoDerecha.radioAzul.setVacuna(v);
		PanelJuegoDerecha.radioAzul.setOpaque(false);
		PanelJuegoDerecha.radioAzul.setContentAreaFilled(false);
		PanelJuegoDerecha.radioAzul.setBorderPainted(false);
		PanelJuegoDerecha.radioAzul.setFocusPainted(true);
		PanelJuegoDerecha.radioAzul.setForeground(Color.black);
		PanelJuegoDerecha.radioAzul.setPreferredSize(new Dimension(100, 100));
		vacunas.add(PanelJuegoDerecha.radioAzul);

		PanelJuegoDerecha.radioAzul.addActionListener(p);
		PanelJuegoDerecha.radioAzul.addItemListener(p);
		PanelJuegoDerecha.radioAzul.setIcon(PanelJuegoDerecha.icono_azul);
		p.add(PanelJuegoDerecha.radioAzul);
		p.add(PanelJuegoDerecha.labelAzul);
		PanelJuegoDerecha.labelAzul.setForeground(Color.white);

		PanelJuegoDerecha.radioVerde.setVacuna(v2);
		PanelJuegoDerecha.radioVerde.setOpaque(false);
		PanelJuegoDerecha.radioVerde.setContentAreaFilled(false);
		PanelJuegoDerecha.radioVerde.setBorderPainted(false);
		PanelJuegoDerecha.radioVerde.setFocusPainted(true);
		PanelJuegoDerecha.radioVerde.setForeground(Color.black);
		PanelJuegoDerecha.radioVerde.setPreferredSize(new Dimension(100, 100));
		vacunas.add(PanelJuegoDerecha.radioVerde);
		PanelJuegoDerecha.radioVerde.addActionListener(p);
		PanelJuegoDerecha.radioVerde.addItemListener(p);
		PanelJuegoDerecha.radioVerde.setIcon(PanelJuegoDerecha.icono_verde);
		p.add(PanelJuegoDerecha.radioVerde);
		p.add(PanelJuegoDerecha.labelVerde);
		PanelJuegoDerecha.labelVerde.setForeground(Color.white);

		PanelJuegoDerecha.radioRojo.setVacuna(v3);
		PanelJuegoDerecha.radioRojo.setOpaque(false);
		PanelJuegoDerecha.radioRojo.setContentAreaFilled(false);
		PanelJuegoDerecha.radioRojo.setBorderPainted(false);
		PanelJuegoDerecha.radioRojo.setFocusPainted(true);
		PanelJuegoDerecha.radioRojo.setForeground(Color.black);
		PanelJuegoDerecha.radioRojo.setPreferredSize(new Dimension(100, 100));
		vacunas.add(PanelJuegoDerecha.radioRojo);
		PanelJuegoDerecha.radioRojo.addActionListener(p);
		PanelJuegoDerecha.radioRojo.addItemListener(p);
		PanelJuegoDerecha.radioRojo.setIcon(PanelJuegoDerecha.icono_rojo);
		p.add(PanelJuegoDerecha.radioRojo);
		p.add(PanelJuegoDerecha.labelRojo);
		PanelJuegoDerecha.labelRojo.setForeground(Color.white);

		PanelJuegoDerecha.radioAmarillo.setVacuna(v4);
		PanelJuegoDerecha.radioAmarillo.setOpaque(false);
		PanelJuegoDerecha.radioAmarillo.setContentAreaFilled(false);
		PanelJuegoDerecha.radioAmarillo.setBorderPainted(false);
		PanelJuegoDerecha.radioAmarillo.setFocusPainted(true);
		PanelJuegoDerecha.radioAmarillo.setForeground(Color.black);
		PanelJuegoDerecha.radioAmarillo.setPreferredSize(new Dimension(100, 100));
		vacunas.add(PanelJuegoDerecha.radioAmarillo);
		PanelJuegoDerecha.radioAmarillo.addActionListener(p);
		PanelJuegoDerecha.radioAmarillo.addItemListener(p);
		PanelJuegoDerecha.radioAmarillo.setIcon(PanelJuegoDerecha.icono_amarillo);
		p.add(PanelJuegoDerecha.radioAmarillo);
		p.add(PanelJuegoDerecha.labelAmarillo);
		PanelJuegoDerecha.labelAmarillo.setForeground(Color.white);

	}

	// El mateix que a dalt pero amb els botons de desarrollar cura i curar ciutat
	public static void BotonesDesarollar_curar(PanelJuegoDerecha p) {

		PanelJuegoDerecha.curarCiudad.setOpaque(false);
		PanelJuegoDerecha.curarCiudad.setBorderPainted(false);
		PanelJuegoDerecha.curarCiudad.setContentAreaFilled(false);
		PanelJuegoDerecha.curarCiudad.setBounds(500, 550, 85, 85); // Desplaza el botón curarCiudad hacia abajo
		PanelJuegoDerecha.curarCiudad.setToolTipText("Curar");
		PanelJuegoDerecha.curarCiudad.addActionListener(p);

		ImageIcon curarCiudadimg = new ImageIcon(
				new ImageIcon("img/cur_ciudad.png").getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH));
		PanelJuegoDerecha.curarCiudad.setIcon(curarCiudadimg);
		PanelJuegoDerecha.curarCiudad.setFocusPainted(false);
		p.add(PanelJuegoDerecha.curarCiudad);

		PanelJuegoDerecha.DesarollarVacuna.setBorderPainted(false);
		PanelJuegoDerecha.DesarollarVacuna.addActionListener(p);
		PanelJuegoDerecha.DesarollarVacuna.setOpaque(false);
		PanelJuegoDerecha.DesarollarVacuna.setContentAreaFilled(false);
		PanelJuegoDerecha.DesarollarVacuna.setFocusPainted(false);
		PanelJuegoDerecha.DesarollarVacuna.setBounds(500, 650, 85, 85); // Desplaza el botón DesarollarVacuna hacia
		PanelJuegoDerecha.DesarollarVacuna.setToolTipText("Desarrollar Vacuna");
		// abajo
		ImageIcon desvacunaimg = new ImageIcon(
				new ImageIcon("img/des_vacuna.png").getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH));
		PanelJuegoDerecha.DesarollarVacuna.setIcon(desvacunaimg);
		p.add(PanelJuegoDerecha.DesarollarVacuna);

	}
	
	// Funció similar a la del textarea que actualitza la etiqueta del % de desenvolupament
	public static void animateJLabel(JLabel label, int percentage, int sumar, Vacuna v, Runnable callback) {
		int resultado_final = percentage + sumar;

		if (resultado_final > 100) {
			resultado_final = 100;
		}

		final int finalResultado = resultado_final;

		new Thread(() -> {
			for (int i = percentage; i <= finalResultado; i++) {
				final int currentPercentage = i; // Almacenar el valor actual de i

				try {
					SwingUtilities.invokeLater(() -> {
						label.setText(currentPercentage + "%"); // Acceder a la variable final dentro del lambda
						if (currentPercentage == 100) {
							label.setForeground(Color.GREEN); // Cambiar el color del texto a verde cuando el porcentaje
																// es 100
						}
					});

					Thread.sleep(10); // Pausa de 50 milisegundos entre cada actualización
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// Actualizar el porcentaje en la instancia de Vacuna al final de la animación
			v.setPorcentaje(finalResultado);

			// Ejecutar la función de retrollamada después de la animación
			if (callback != null) {
				callback.run();
			}
		}).start();
	}

	// GAMEPLAY - entra dificultad per parametro, que viene de parametros.xml
	public static void empezarPartida(int dificultad) {
		Random rand = new Random();
		int numCiudadesInfectadas = 0;

		dp.cargarDatos();

		// Definir el tamaño del array según la dificultad
		// Defineix quines ciutats infecta, el numero ja ve definit
		int[] ciudadesInfectadasComienzo = new int[dp.getPropagacion_inicio()];

		for (int i = 0; i < dp.getPropagacion_inicio(); i++) {
			int r = rand.nextInt(0, dp.ciudades.size());
			ciudadesInfectadasComienzo[i] = r;
		}

		// Poner acciones en 4
		dp.setAcciones(4);
		PanelBotonesMenuAbajo.lblAcciones.setText("Acciones: " + dp.getAcciones());

		// Turno en 1
		dp.setRondas(1);
		PanelBotonesMenuAbajo.lblTurno.setText("Turno: " + dp.getRondas());

		for (int i = 0; i < ciudadesInfectadasComienzo.length; i++) {

			String enfermedad = PanelMapa.botonesCiudadesArray.get(ciudadesInfectadasComienzo[i]).getCiudad()
					.getEnfermedad();
			switch (enfermedad) {
			case "Alfa":
				PanelMapa.botonesCiudadesArray.get(ciudadesInfectadasComienzo[i]).setIcon(PanelMapa.ciudad_azul1);
				dp.ciudades.get(ciudadesInfectadasComienzo[i]).setInfeccion(1);
				break;
			case "Beta":
				PanelMapa.botonesCiudadesArray.get(ciudadesInfectadasComienzo[i]).setIcon(PanelMapa.ciudad_roja1);
				dp.ciudades.get(ciudadesInfectadasComienzo[i]).setInfeccion(1);
				break;
			case "Gama":
				PanelMapa.botonesCiudadesArray.get(ciudadesInfectadasComienzo[i]).setIcon(PanelMapa.ciudad_verde1);
				dp.ciudades.get(ciudadesInfectadasComienzo[i]).setInfeccion(1);
				break;
			case "Delta":
				PanelMapa.botonesCiudadesArray.get(ciudadesInfectadasComienzo[i]).setIcon(PanelMapa.ciudad_amarilla1);
				dp.ciudades.get(ciudadesInfectadasComienzo[i]).setInfeccion(1);
				break;
			default:
				break;
			}
		}
	}
	
	// Reinicia las acciones restantes, actualitza el turno i infecta de nou ciutats 
	public static void nuevoTurno() {
		Random rand = new Random();
		ArrayList<Boolean> colindantesYaInfectadas = new ArrayList<>();
		for (int i = 0; i < dp.ciudades.size(); i++) {
			colindantesYaInfectadas.add(true);
		}

		ArrayList<Ciudades> menores3 = new ArrayList<>();

		// Poner acciones a 4
		dp.setAcciones(4);
		PanelBotonesMenuAbajo.lblAcciones.setText("Acciones: " + dp.getAcciones());

		// Sumar turno
		dp.setRondas(dp.getRondas() + 1);
		PanelBotonesMenuAbajo.lblTurno.setText("Turno: " + dp.getRondas());

		
		//Spawnear nuevas infecciones
		int[] ciudadesInfectadasComienzoTurno = new int[dp.getPropagacion_ronda()];
		ArrayList<Integer> ints_ciudades = new ArrayList<>();

		for (int i = 0; i < dp.ciudades.size(); i++) {
		    ints_ciudades.add(i);
		}

		for (int i = 0; i < dp.getPropagacion_ronda(); i++) {
		    int r;
		    do {
		        r = rand.nextInt(dp.ciudades.size());
		    } while (!ints_ciudades.contains(r));
		   
		    if (dp.ciudades.get(r).getInfeccion() == 0) {
			    ciudadesInfectadasComienzoTurno[i] = r;
			    ints_ciudades.remove((Integer)r);
			}

		}

	
		// Subir de nivel las ciudades menores de 3
		boolean broteHecho = false;
		for (int i = 0; i < dp.ciudades.size(); i++) {
			if (dp.ciudades.get(i).getInfeccion() < 3 && dp.ciudades.get(i).getInfeccion() != 0) {

				switch (dp.ciudades.get(i).getEnfermedad()) {
				case "Alfa": // azul
					switch (dp.ciudades.get(i).getInfeccion()) {
					case 0:
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_azul1);
						break;
					case 1:
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_azul2);
						break;
					case 2:
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_azul3);
						break;
					}
					break;
				case "Beta": // roja
					switch (dp.ciudades.get(i).getInfeccion()) {
					case 0:
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_roja1);
						break;
					case 1:
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_roja2);
						break;
					case 2:
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_roja3);
						break;
					}

					break;
				case "Gama": // verde
					switch (dp.ciudades.get(i).getInfeccion()) {
					case 0:
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_verde1);
						break;
					case 1:
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_verde2);
						break;
					case 2:
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_verde3);
						System.out.println();
						break;
					}

					break;
				case "Delta": // amarillo
					switch (dp.ciudades.get(i).getInfeccion()) {
					case 0:
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_amarilla1);
						break;
					case 1:
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_amarilla2);
						break;
					case 2:
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_amarilla3);
						break;
					}

					break;

				default:
					break;
				}

				dp.ciudades.get(i).setInfeccion(dp.ciudades.get(i).getInfeccion() + 1);

			} else if (dp.ciudades.get(i).getInfeccion() == 3) {
				// Crear un array de ciudades colindantes
				ArrayList<Ciudades> colindantes = new ArrayList<>();
				
				ArrayList<Integer> posicionesColindantes = new ArrayList<>();
				
				if (PanelMapa.botonesCiudadesArray.get(i).getHaBrotado() == true) {
					switch (dp.ciudades.get(i).getEnfermedad()) {
					case "Alfa": // azul
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_azul3);
						PanelMapa.botonesCiudadesArray.get(i).setHaBrotado(false);
						break;
					case "Beta": // roja
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_roja3);
						PanelMapa.botonesCiudadesArray.get(i).setHaBrotado(false);
						break;
					case "Gama": // verde
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_verde3);
						PanelMapa.botonesCiudadesArray.get(i).setHaBrotado(false);
						break;
					case "Delta": // amarillo
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_amarilla3);
						PanelMapa.botonesCiudadesArray.get(i).setHaBrotado(false);
						break;

					default:
						break;
					}
				}else {
					switch (dp.ciudades.get(i).getEnfermedad()) {
					case "Alfa": // azul
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_azulBrote);
						PanelMapa.botonesCiudadesArray.get(i).setHaBrotado(true);
						break;
					case "Beta": // roja
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_rojaBrote);
						PanelMapa.botonesCiudadesArray.get(i).setHaBrotado(true);
						break;
					case "Gama": // verde
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_verdeBrote);
						PanelMapa.botonesCiudadesArray.get(i).setHaBrotado(true);
						break;
					case "Delta": // amarillo
						PanelMapa.botonesCiudadesArray.get(i).setIcon(PanelMapa.ciudad_amarillaBrote);
						PanelMapa.botonesCiudadesArray.get(i).setHaBrotado(true);
						break;

					default:
						break;
					}
				}
				
				if (!PanelMapa.botonesCiudadesArray.get(i).getHaBrotado()) {
					//HAGO UN FOR DEL TAMAÑO DE LA CANTIDAD DE COLINDANTES QUE TIENE LA CIUDAD
					for (int j = 0; j < dp.ciudades.get(i).getColindantes().size(); j++) {
						String ciudadC = dp.ciudades.get(i).getColindantes().get(j);

						//OTRO FOR DEL TAMAÑO DE LA ARRAYLIST DE CIUDADES PARA AÑADIR A LA ARRAYLIST DE COLINDANTES LAS QUE TIENE LA CIUDAD
						for (int k = 0; k < dp.ciudades.size(); k++) {
							if (dp.ciudades.get(k).getNombre().equals(ciudadC)) {
								colindantes.add(dp.ciudades.get(k));
								posicionesColindantes.add(k);
							}

						}
					}
					
					//EMPIEZO A INFECTAR LAS COLINDANTES / CHECKEAR SI YA HA SIDO INFECTADA POR UN BROTE ANTERIOR
					for (int j = 0; j < colindantes.size(); j++) {

						if (colindantes.get(j).getInfeccion() <= 2) {					
							
							
							if (colindantesYaInfectadas.get(obtenerPosicionInfectada(colindantes.get(j)))) {
								switch (colindantes.get(j).getEnfermedad()) {
								case "Alfa": // azul
									switch (colindantes.get(j).getInfeccion()) {
									case 0:
										PanelMapa.botonesCiudadesArray.get(posicionesColindantes.get(j))
												.setIcon(PanelMapa.ciudad_azul1);
										break;
									case 1:
										PanelMapa.botonesCiudadesArray.get(posicionesColindantes.get(j))
												.setIcon(PanelMapa.ciudad_azul2);
										break;
									case 2:
										PanelMapa.botonesCiudadesArray.get(posicionesColindantes.get(j))
												.setIcon(PanelMapa.ciudad_azul3);
										break;
									}
									break;
								case "Beta": // roja
									switch (colindantes.get(j).getInfeccion()) {
									case 0:
										PanelMapa.botonesCiudadesArray.get(posicionesColindantes.get(j))
												.setIcon(PanelMapa.ciudad_roja1);
										break;
									case 1:
										PanelMapa.botonesCiudadesArray.get(posicionesColindantes.get(j))
												.setIcon(PanelMapa.ciudad_roja2);
										break;
									case 2:
										PanelMapa.botonesCiudadesArray.get(posicionesColindantes.get(j))
												.setIcon(PanelMapa.ciudad_roja3);
										break;
									}

									break;
								case "Gama": // verde
									switch (colindantes.get(j).getInfeccion()) {
									case 0:
										PanelMapa.botonesCiudadesArray.get(posicionesColindantes.get(j))
												.setIcon(PanelMapa.ciudad_verde1);
										break;
									case 1:
										PanelMapa.botonesCiudadesArray.get(posicionesColindantes.get(j))
												.setIcon(PanelMapa.ciudad_verde2);
										break;
									case 2:
										PanelMapa.botonesCiudadesArray.get(posicionesColindantes.get(j))
												.setIcon(PanelMapa.ciudad_verde3);
										break;
									}

									break;
								case "Delta": // amarillo
									switch (colindantes.get(j).getInfeccion()) {
									case 0:
										PanelMapa.botonesCiudadesArray.get(posicionesColindantes.get(j))
												.setIcon(PanelMapa.ciudad_amarilla1);
										break;
									case 1:
										PanelMapa.botonesCiudadesArray.get(posicionesColindantes.get(j))
												.setIcon(PanelMapa.ciudad_amarilla2);
										break;
									case 2:
										PanelMapa.botonesCiudadesArray.get(posicionesColindantes.get(j))
												.setIcon(PanelMapa.ciudad_amarilla3);
										break;
									}

									break;
								}
								colindantesYaInfectadas.set(obtenerPosicionInfectada(colindantes.get(j)), false);
								colindantes.get(j).setInfeccion(colindantes.get(j).getInfeccion() + 1);
								broteHecho = true;
							}
						}
				}
				


				}

			}
		}
		
		// infectar nuevas ciudades
		ArrayList<Ciudades> ciudadesCero = new ArrayList<>();
		for (int i = 0; i < dp.ciudades.size(); i++) {
			if (dp.ciudades.get(i).getInfeccion() == 0) {
				ciudadesCero.add(dp.ciudades.get(i));
			}
		}
		
		for (int i = 0; i < ciudadesInfectadasComienzoTurno.length; i++) {

			String enfermedad = PanelMapa.botonesCiudadesArray.get(ciudadesInfectadasComienzoTurno[i]).getCiudad().getEnfermedad();
			switch (enfermedad) {
			case "Alfa":
				PanelMapa.botonesCiudadesArray.get(ciudadesInfectadasComienzoTurno[i]).setIcon(PanelMapa.ciudad_azul1);
				dp.ciudades.get(ciudadesInfectadasComienzoTurno[i]).setInfeccion(1);
				break;
			case "Beta":
				PanelMapa.botonesCiudadesArray.get(ciudadesInfectadasComienzoTurno[i]).setIcon(PanelMapa.ciudad_roja1);
				dp.ciudades.get(ciudadesInfectadasComienzoTurno[i]).setInfeccion(1);
				break;
			case "Gama":
				PanelMapa.botonesCiudadesArray.get(ciudadesInfectadasComienzoTurno[i]).setIcon(PanelMapa.ciudad_verde1);
				dp.ciudades.get(ciudadesInfectadasComienzoTurno[i]).setInfeccion(1);
				break;
			case "Delta":
				PanelMapa.botonesCiudadesArray.get(ciudadesInfectadasComienzoTurno[i]).setIcon(PanelMapa.ciudad_amarilla1);
				dp.ciudades.get(ciudadesInfectadasComienzoTurno[i]).setInfeccion(1);
				break;
			default:
				break;
			}
		}

		if (PanelJuegoIzquierda.circulosActuales < PanelJuegoIzquierda.circulosMaximos && broteHecho) {
			LanzadorPartida.p3.setNumeroCirculosActuales(PanelJuegoIzquierda.circulosActuales + 1);
			if (PanelJuegoIzquierda.circulosActuales == PanelJuegoIzquierda.circulosMaximos) {
				resultadoPartida(false);
			}
		}
		
		
		
		

		PanelMapa.ciudadSeleccionada = null;
		textosCaja("");

	}
	// Coge colindantes por parametros y quiero comodin BAZOKA PORFAVOR COMENTA
public static int obtenerPosicionInfectada(Ciudades colindante) {
	int posicionArray = -1;
	
	for (int i = 0; i < dp.ciudades.size(); i++) {
		if (dp.ciudades.get(i).getNombre().equals(colindante.getNombre())) {
			posicionArray = i;
			break;
		}
	}
	return posicionArray;
}	
	
	// Comprueba el resultado actual por si has ganado o aún no
	public static void resultadoFinal(){
				
		int suma1 = PanelJuegoDerecha.radioVerde.getVacuna().getPorcentaje();
		int suma2 = PanelJuegoDerecha.radioAzul.getVacuna().getPorcentaje();
		int suma3 = PanelJuegoDerecha.radioAmarillo.getVacuna().getPorcentaje();
		int suma4 = PanelJuegoDerecha.radioRojo.getVacuna().getPorcentaje();
		
		int sumafinal = suma1+suma2+suma3+suma4;
		
		if(sumafinal >= 400) {controlDatos.guardarRecord(controlDatos.con); resultadoPartida(true); }else {System.out.println("No has ganado aun");}
	}
	// Función para curar las ciudades, obligatoriamente selecciona una y cuando curas, si la vacuna esta desarrollada, si lo esta te baja a 0 y sino
	// te baja 1. Actualiza el icono de la ciudad dependiendo del nivel de infección
public static void curarCiudad(CiudadBoton c) {
    if (PanelMapa.ciudadSeleccionada == null) {
        textosCaja("Selecciona una ciudad para poder curarla");
    } else {
        if (c.getCiudad().getInfeccion() > 0) {
            boolean cost = updateAP(1);
            if (cost) {
                switch (c.getCiudad().getEnfermedad()) {
                    case "Alfa": // azul
                        if (PanelJuegoDerecha.radioAzul.getVacuna().getPorcentaje() == 100) {
                            c.setIcon(PanelMapa.ciudad_azul0);
                            c.getCiudad().setInfeccion(0);
                        } else {

                            switch (c.getCiudad().getInfeccion()) {
                                case 1:
                                    c.setIcon(PanelMapa.ciudad_azul0);
                                    break;
                                case 2:
                                    c.setIcon(PanelMapa.ciudad_azul1);
                                    break;
                                case 3:
                                    c.setIcon(PanelMapa.ciudad_azul2);
                                    break;
                            }
                            c.getCiudad().setInfeccion(c.getCiudad().getInfeccion() - 1);
                        }
                        break;
                    case "Beta": // roja
                        if (PanelJuegoDerecha.radioRojo.getVacuna().getPorcentaje() == 100) {
                            c.setIcon(PanelMapa.ciudad_roja0);
                            c.getCiudad().setInfeccion(0);
                        } else {
                            
                            switch (c.getCiudad().getInfeccion()) {
                                case 1:
                                    c.setIcon(PanelMapa.ciudad_roja0);
                                    break;
                                case 2:
                                    c.setIcon(PanelMapa.ciudad_roja1);
                                    break;
                                case 3:
                                    c.setIcon(PanelMapa.ciudad_roja2);
                                    break;
                            }
                            c.getCiudad().setInfeccion(c.getCiudad().getInfeccion() - 1);
                        }
                        break;
                    case "Gama": // verde
                        if (PanelJuegoDerecha.radioVerde.getVacuna().getPorcentaje() == 100) {
                            c.setIcon(PanelMapa.ciudad_verde0);
                            c.getCiudad().setInfeccion(0);
                        } else {
                            
                            switch (c.getCiudad().getInfeccion()) {
                                case 1:
                                    c.setIcon(PanelMapa.ciudad_verde0);
                                    break;
                                case 2:
                                    c.setIcon(PanelMapa.ciudad_verde1);
                                    break;
                                case 3:
                                    c.setIcon(PanelMapa.ciudad_verde2);
                                    break;
                            }
                            c.getCiudad().setInfeccion(c.getCiudad().getInfeccion() - 1);
                        }
                        break;
                    case "Delta": // amarillo
                        if (PanelJuegoDerecha.radioAmarillo.getVacuna().getPorcentaje() == 100) {
                            c.setIcon(PanelMapa.ciudad_amarilla0);
                            c.getCiudad().setInfeccion(0);
                        } else {
                           
                            switch (c.getCiudad().getInfeccion()) {
                                case 1:
                                    c.setIcon(PanelMapa.ciudad_amarilla0);
                                    break;
                                case 2:
                                    c.setIcon(PanelMapa.ciudad_amarilla1);
                                    break;
                                case 3:
                                    c.setIcon(PanelMapa.ciudad_amarilla2);
                                    break;
                            }
                            c.getCiudad().setInfeccion(c.getCiudad().getInfeccion() - 1);
                        }
                        break;
                }
                textosCajaColor("Ciudad curada correctamente, su nivel ha bajado a " + c.getCiudad().getInfeccion(), Color.green);
            }
            try {
                File soundFile = new File("src/assets/gfx/curar.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                e1.printStackTrace();
            }
        } else {
            textosCaja("¡La ciudad ya esta sana!");
}
    }
}
	
	
	public static void cambiarImagenCiudad(CiudadBoton c, int nivInfeccion, String id) {
		switch (id) {
		case "Alfa":
			if (nivInfeccion == 1) {

			} else {

			}
			break;
		case "Beta":

			break;

		default:
			break;
		}
	}


	// Actualitza el comptador de accions.
	public static boolean updateAP(int cost) {

		if (dp.getAcciones() >= cost) {
			logicaJuego.dp.setAcciones(logicaJuego.dp.getAcciones() - cost);
			String s = "Acciones: " + logicaJuego.dp.getAcciones();
			System.out.println(dp.getAcciones());
			PanelBotonesMenuAbajo.lblAcciones.setText(s);
			return true;
		} else {
			System.out.println(dp.getAcciones());
			logicaJuego.textosCaja("No tienes suficientes acciones!!!");
			return false;
		}

	}
	
	// muestra el resultado de la partida y llama a las pantallas referenciadas. Usa por parametro la hasGanado(), el cual hemos explicado mas arriba
	public static void resultadoPartida(boolean hasGanado) {

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(LanzadorPartida.p6);

		// con esto "funciona"
//		LanzadorPartida.p.setVisible(false);
//		LanzadorPartida.p2.setVisible(false);
//		LanzadorPartida.p3.setVisible(false);
//		LanzadorPartida.p4.setVisible(false);
		
		if( hasGanado == true){
		LanzadorPartida.mec = true;
		LanzadorPartida.test();
		System.out.println("Has Ganado");
        try {
            File soundFile = new File("src/assets/gfx/ganaste.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
            e1.printStackTrace();
        }
		}else{
			LanzadorPartida.mecmec = true;
			LanzadorPartida.test();
			System.out.println("Has Perdido");
	        try {
	            File soundFile = new File("src/assets/gfx/perdiste.wav");
	            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
	            Clip clip = AudioSystem.getClip();
	            clip.open(audioIn);
	            clip.start();
	        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
	            e1.printStackTrace();
	        }
		}
	}

}
