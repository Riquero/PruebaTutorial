package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.google.common.collect.Lists;

import jugadores.MovimientoEnCurso;
import piezas.Pieza;
import tablero.Casilla;
import tablero.Movimiento;
import tablero.Tablero;
import tablero.UtilidadesTablero;

public class Tabla {
	private final JFrame estructuraDelJuego;
	private final PanelHistoricoJuego panelHistorialJuego;
	private final PanelDePiezasCapturadas panelDePiezasCapturadas;
	private final PanelDeTablero panelDeTablero;
	private Tablero tableroDeAjedrez;
	private final HistorialDeMovimientos historialMovimientos;

	private Casilla casillaFuente;
	private Casilla casillaDeDestino;
	private Pieza piezaMovidaPorHumano;
	private DireccionTablero direccionDelTablero;
	
	private boolean subrayarMovimientosLegales;

	private final static Dimension DIMENSION_ESTRUCTURA_EXTERIOR = new Dimension(800, 800);
	private final static Dimension DIMENSION_PANEL_TABLERO = new Dimension(550, 500);
	private final static Dimension DIMENSION_PANEL_CASILLA = new Dimension(12, 12);

	private final Color casillaColorClaro = Color.decode("#FFFACD");
	private final Color casillaColorOscuro = Color.decode("#593E1A");

	public Tabla() {
		this.estructuraDelJuego = new JFrame("Ajedrez Java");
		this.estructuraDelJuego.setLayout(new BorderLayout());
		final JMenuBar menuBarJuego = creacionDelMenu();
		this.estructuraDelJuego.setJMenuBar(menuBarJuego);
		this.estructuraDelJuego.setSize(DIMENSION_ESTRUCTURA_EXTERIOR);
		this.tableroDeAjedrez = Tablero.crearTableroEstandar();
		this.panelHistorialJuego = new PanelHistoricoJuego();
		this.panelDePiezasCapturadas = new PanelDePiezasCapturadas();
		this.panelDeTablero = new PanelDeTablero();
		this.historialMovimientos = new HistorialDeMovimientos();
		this.direccionDelTablero = DireccionTablero.NORMAL;
		this.subrayarMovimientosLegales = false;
		this.estructuraDelJuego.add(this.panelDePiezasCapturadas, BorderLayout.WEST);
		this.estructuraDelJuego.add(this.panelDeTablero, BorderLayout.CENTER);
		this.estructuraDelJuego.add(this.panelHistorialJuego, BorderLayout.EAST);
		this.estructuraDelJuego.setVisible(true);
	}

	private JMenuBar creacionDelMenu() {
		final JMenuBar menuBarJuego = new JMenuBar();
		menuBarJuego.add(crearMenuArchivo());
		menuBarJuego.add(crearMenuPreferencias());
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
	
	private JMenu crearMenuPreferencias() {
		final JMenu menuPreferencias = new JMenu ("Preferencias");
		final JMenuItem voltearTablero = new JMenuItem("Voltear tablero");
		voltearTablero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				direccionDelTablero = direccionDelTablero.opuesto();
				panelDeTablero.pintarTablero(tableroDeAjedrez);
			}
		});
		menuPreferencias.add(voltearTablero);
		menuPreferencias.addSeparator();
		final JCheckBoxMenuItem mostrarMovimientosLegales = new JCheckBoxMenuItem("Mostrar movimientos legales");
		mostrarMovimientosLegales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				subrayarMovimientosLegales = mostrarMovimientosLegales.isSelected();
			}
		});
		menuPreferencias.add(mostrarMovimientosLegales);
		return menuPreferencias;
	}
	
	public enum DireccionTablero {
		NORMAL{
			@Override
			List<PanelDeCasilla> voltear(List<PanelDeCasilla> casillasTablero) {
				return casillasTablero;
			}
			@Override
			DireccionTablero opuesto() {
				return VOLTEADO;
			}			
		},
		VOLTEADO{
			@Override
			List<PanelDeCasilla> voltear(List<PanelDeCasilla> casillasTablero) {
				return Lists.reverse(casillasTablero);
			}
			@Override
			DireccionTablero opuesto() {
				return NORMAL;
			}			
		};
		
		abstract List<PanelDeCasilla> voltear(final List<PanelDeCasilla> casillasTablero);
		abstract DireccionTablero opuesto();
		
	}

	public static class HistorialDeMovimientos {
		
		private final List<Movimiento> movimientos;
		
		public HistorialDeMovimientos() {
			this.movimientos = new ArrayList<>();
		}
		
		public List<Movimiento> getMovimientos(){
			return this.movimientos;
		}
		
		public void anadirMovimiento(final Movimiento movimiento) {
			this.movimientos.add(movimiento);
		}
		
		public int tamanio() {
			return this.movimientos.size();
		}
		
		public void limpiar() {
			this.movimientos.clear();
		}
		
		public Movimiento eliminarMovimiento(int index) {
			return this.movimientos.remove(index);
		}
		
		public boolean eliminarMovimiento (final Movimiento movimiento) {
			return this.movimientos.remove(movimiento);
		}
		
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

		public void pintarTablero(final Tablero tablero) {
			removeAll();
			for (final PanelDeCasilla panelDeCasilla : direccionDelTablero.voltear(casillasDeTabla)) {
				panelDeCasilla.pintarCasilla(tablero);
				add(panelDeCasilla);
			}
			validate();
			repaint();
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

			addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(final MouseEvent e) {
				}

				@Override
				public void mousePressed(final MouseEvent e) {
				}

				@Override
				public void mouseExited(final MouseEvent e) {
				}

				@Override
				public void mouseEntered(final MouseEvent e) {
				}

				@Override
				public void mouseClicked(final MouseEvent e) {
					if (javax.swing.SwingUtilities.isRightMouseButton(e)) {
						casillaFuente = null;
						casillaDeDestino = null;
						piezaMovidaPorHumano = null;
					} else if (javax.swing.SwingUtilities.isLeftMouseButton(e)) {
						if (casillaFuente == null) {
							// Primer click
							casillaFuente = tableroDeAjedrez.getCasilla(idCasilla);
							piezaMovidaPorHumano = casillaFuente.getPieza();
							if (piezaMovidaPorHumano == null) {
								casillaFuente = null;
							}
							casillaFuente = tableroDeAjedrez.getCasilla(idCasilla);
							piezaMovidaPorHumano = casillaFuente.getPieza();
							if (piezaMovidaPorHumano == null) {
								casillaFuente = null;
							}
						} else {
							// Segundo click
							casillaDeDestino = tableroDeAjedrez.getCasilla(idCasilla);
							final Movimiento movimiento = Movimiento.FactoriaMovimiento.crearMovimiento(
									tableroDeAjedrez, casillaFuente.getCoordenadaCasilla(),
									casillaDeDestino.getCoordenadaCasilla());
							final MovimientoEnCurso transicion = tableroDeAjedrez.getJugadorActual()
									.hacerMovimiento(movimiento);
							if (transicion.getEstadoDeMovimiento().haTerminado()) {
								tableroDeAjedrez = transicion.getTableroDeTransicion();
								historialMovimientos.anadirMovimiento(movimiento);
							}
							casillaFuente = null;
							casillaDeDestino = null;
							piezaMovidaPorHumano = null;
						}
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								panelHistorialJuego.rehacer(tableroDeAjedrez, historialMovimientos);
								panelDePiezasCapturadas.rehacer(historialMovimientos);
								panelDeTablero.pintarTablero(tableroDeAjedrez);
							}
						});
					}
				}
			});
			validate();
		}

		private void mostrarMovimientosLegales (final Tablero tablero) {
			if(subrayarMovimientosLegales) {
				for (final Movimiento movimiento : movimientosLegalesPieza(tablero)) {
					if(movimiento.getCoordenadaDeDestino() == this.idCasilla) {
						try {
							add(new JLabel (new ImageIcon(getClass().getResource("/arte/green_dot.png"))));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		private Collection<Movimiento> movimientosLegalesPieza(final Tablero tablero) {
			if(piezaMovidaPorHumano != null && piezaMovidaPorHumano.getBando() == tablero.getJugadorActual().getBando()) {
				return piezaMovidaPorHumano.calculaMovimientosLegales(tablero);
			}
			return Collections.emptyList();
		}

		public void pintarCasilla(final Tablero tablero) {
			asignarColorACasilla();
			asignarIconoDePiezaACasilla(tablero);
			mostrarMovimientosLegales(tablero);
			validate();
			repaint();
		}

		private void asignarIconoDePiezaACasilla(final Tablero tablero) {
			this.removeAll();
			if (tablero.getCasilla(this.idCasilla).estaCasillaOcupada()) {
				System.out.println(tablero.getCasilla(this.idCasilla).getPieza().toString());
				add(new JLabel(new ImageIcon(getClass().getResource(
						"/arte/" + tablero.getCasilla(this.idCasilla).getPieza().getBando().toString().substring(0, 1)
								+ tablero.getCasilla(this.idCasilla).getPieza().toString() + ".gif"))));
			}
		}

		private void asignarColorACasilla() {
			boolean isLight = ((idCasilla + idCasilla / 8) % 2 == 0);
			setBackground(isLight ? casillaColorClaro : casillaColorOscuro);
		}
	}
}
