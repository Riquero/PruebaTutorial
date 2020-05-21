package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tablero.Tablero;
import tablero.UtilidadesTablero;

public class Tabla {
	private final JFrame estructuraDelJuego;
	private final PanelDeTablero panelDeTablero;
	private final Tablero tableroDeAjedrez;

	private final static Dimension DIMENSION_ESTRUCTURA_EXTERIOR = new Dimension(800, 800);
	private final static Dimension DIMENSION_PANEL_TABLERO = new Dimension(550, 500);
	private final static Dimension DIMENSION_PANEL_CASILLA = new Dimension(12, 12);
	private static String rutaPorDefectoImagenesPiezas = "arte/simple/";

	private final Color casillaColorClaro = Color.decode("#FFFACD");
	private final Color casillaColorOscuro = Color.decode("#593E1A");

	public Tabla() {
		this.estructuraDelJuego = new JFrame("Ajedrez Java");
		this.estructuraDelJuego.setLayout(new BorderLayout());
		final JMenuBar menuBarJuego = creacionDelMenu();
		this.estructuraDelJuego.setJMenuBar(menuBarJuego);
		this.estructuraDelJuego.setSize(DIMENSION_ESTRUCTURA_EXTERIOR);
		this.panelDeTablero = new PanelDeTablero();
		this.tableroDeAjedrez = Tablero.crearTableroEstandar();
		this.estructuraDelJuego.add(this.panelDeTablero, BorderLayout.CENTER);
		this.estructuraDelJuego.setVisible(true);
	}

	private JMenuBar creacionDelMenu() {
		final JMenuBar menuBarJuego = new JMenuBar();
		menuBarJuego.add(crearMenuArchivo());
		return menuBarJuego;
	}

	private JMenu crearMenuArchivo() {
		final JMenu menuArchivo = new JMenu("Archivo");
		final JMenuItem abrirPGN = new JMenuItem("Cargar archivo PGN");
		abrirPGN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Abre ese archivo PGN!");
			}
		});
		menuArchivo.add(abrirPGN);
		final JMenuItem salirMenu = new JMenuItem("Salir");
		salirMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuArchivo.add(salirMenu);
		return menuArchivo;
	}

	private class PanelDeTablero extends JPanel {
		final List<PanelDeCasilla> casillasDeTabla;

		PanelDeTablero() {
			super(new GridLayout(8, 8));
			this.casillasDeTabla = new ArrayList<>();

			for (int i = 0; i < UtilidadesTablero.NUMERO_DE_CASILLAS; i++) {
				final PanelDeCasilla panelDeCasillas = new PanelDeCasilla(this, i);
				this.casillasDeTabla.add(panelDeCasillas);
				add(panelDeCasillas);
			}
			setPreferredSize(DIMENSION_PANEL_TABLERO);
			validate();
		}
	}

	private class PanelDeCasilla extends JPanel {

		private final int idCasilla;

		PanelDeCasilla(final PanelDeTablero panelDeTablero, final int idCasilla) {
			super(new GridBagLayout());
			this.idCasilla = idCasilla;
			setPreferredSize(DIMENSION_PANEL_CASILLA);
			asignarColorACasilla();
			asignarIconoDePiezaACasilla(tableroDeAjedrez);
			validate();
		}

		private void asignarIconoDePiezaACasilla(final Tablero tablero) {
			this.removeAll();
			System.out.println(rutaPorDefectoImagenesPiezas);
			if (tablero.getCasilla(this.idCasilla).estaCasillaOcupada()) {
				try {
					System.out.println(rutaPorDefectoImagenesPiezas);
					final BufferedImage imagen = ImageIO.read(new File(rutaPorDefectoImagenesPiezas
							+ tablero.getCasilla(this.idCasilla).getPieza().getBando().toString().substring(0, 1)
							+ tablero.getCasilla(this.idCasilla).getPieza().toString() + ".gif"));
					add(new JLabel(new ImageIcon(imagen)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void asignarColorACasilla() {
			boolean isLight = ((idCasilla + idCasilla / 8) % 2 == 0);
			setBackground(isLight ? casillaColorClaro : casillaColorOscuro);
		}
	}
}
