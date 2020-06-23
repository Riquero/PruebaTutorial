package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.google.common.primitives.Ints;

import Interfaz.Tabla.HistorialDeMovimientos;
import piezas.Pieza;
import tablero.Movimiento;

public class PanelDePiezasCapturadas extends JPanel {

	private final JPanel panelNorte;
	private final JPanel panelSur;

	private static final Color COLOR_PANEL = Color.decode("0xFDFE6");
	private static final EtchedBorder BORDE_PANEL = new EtchedBorder(EtchedBorder.RAISED);
	private static final Dimension DIMENSION_PIEZAS_CAPTURADAS = new Dimension(40, 80);

	public PanelDePiezasCapturadas() {
		super(new BorderLayout());
		setBackground(COLOR_PANEL);
		setBorder(BORDE_PANEL);
		this.panelNorte = new JPanel(new GridLayout(8, 2));
		this.panelSur = new JPanel(new GridLayout(8, 2));
		this.panelNorte.setBackground(COLOR_PANEL);
		this.panelSur.setBackground(COLOR_PANEL);
		this.add(this.panelNorte, BorderLayout.NORTH);
		this.add(this.panelSur, BorderLayout.SOUTH);
		setPreferredSize(DIMENSION_PIEZAS_CAPTURADAS);
	}

	public void rehacer(final HistorialDeMovimientos historialDeMovimientos) {
		this.panelNorte.removeAll();
		this.panelSur.removeAll();

		final List<Pieza> piezasBlancasCapturadas = new ArrayList<>();
		final List<Pieza> piezasNegrasCapturadas = new ArrayList<>();

		for (final Movimiento movimiento : historialDeMovimientos.getMovimientos()) {
			if (movimiento.esAtaque()) {
				final Pieza piezaCapturada = movimiento.getPiezaAtacada();
				if (piezaCapturada.getBando().esBlanca()) {
					piezasBlancasCapturadas.add(piezaCapturada);
				} else if (piezaCapturada.getBando().esNegra()) {
					piezasNegrasCapturadas.add(piezaCapturada);
				} else {
					throw new RuntimeException("No debe llegar aquí");
				}
			}
		}

		Collections.sort(piezasBlancasCapturadas, new Comparator<Pieza>() {
			@Override
			public int compare(Pieza p1, Pieza p2) {
				return Ints.compare(p1.getValorPieza(), p2.getValorPieza());
			}
		});
		Collections.sort(piezasNegrasCapturadas, new Comparator<Pieza>() {
			@Override
			public int compare(Pieza p1, Pieza p2) {
				return Ints.compare(p1.getValorPieza(), p2.getValorPieza());
			}
		});

		for (Pieza piezaCapturada : piezasBlancasCapturadas) {
			try {
				final BufferedImage imagen = ImageIO.read(new File(
						"/arte/" + piezaCapturada.getBando().toString().substring(0, 1) + piezaCapturada.toString()));
				final ImageIcon icono = new ImageIcon(imagen);
				final JLabel clasificacionImagen = new JLabel();
				this.panelSur.add(clasificacionImagen);
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		
		for (Pieza piezaCapturada : piezasNegrasCapturadas) {
			try {
				final BufferedImage imagen = ImageIO.read(new File(
						"/arte/" + piezaCapturada.getBando().toString().substring(0, 1) + piezaCapturada.toString()));
				final ImageIcon icono = new ImageIcon(imagen);
				final JLabel clasificacionImagen = new JLabel();
				this.panelSur.add(clasificacionImagen);
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		
		validate();
	}
}
