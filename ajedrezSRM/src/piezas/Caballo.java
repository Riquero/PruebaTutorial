package piezas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import jugadores.Bando;
import tablero.Casilla;
import tablero.Movimiento;
import tablero.Movimiento.MovimientoAtaquePiezaMayor;
import tablero.Movimiento.MovimientoPiezaMayor;
import tablero.Tablero;
import tablero.UtilidadesTablero;

public class Caballo extends Pieza {

	private final static int[] COORDENADAS_MOVIMIENTO_CANDIDATAS = { -17, -15, -10, -6, 6, 10, 15, 17 };

	public Caballo(final int posicionPieza, final Bando bandoDeLaPieza) {
		super(posicionPieza, bandoDeLaPieza, TipoDePieza.CABALLO);
	}

	public Collection<Movimiento> calculaMovimientosLegales(final Tablero tablero) {

		final List<Movimiento> movimientosLegales = new ArrayList<>();

		for (final int candidatoActual : COORDENADAS_MOVIMIENTO_CANDIDATAS) {

			final int destinoCoordenadaCandidata = this.posicionPieza + candidatoActual;

			if (UtilidadesTablero.esCasillaValida(destinoCoordenadaCandidata)) {

				if (exclusionDePrimeraColumna(this.posicionPieza, candidatoActual)
						|| exclusionDeSegundaColumna(this.posicionPieza, candidatoActual)
						|| exclusionDeSeptimaColumna(this.posicionPieza, candidatoActual)
						|| exclusionDeOctavaColumna(this.posicionPieza, candidatoActual)) {
					continue;
				}

				final Casilla destinoCasillaCandidata = tablero.getCasilla(destinoCoordenadaCandidata);
				if (!destinoCasillaCandidata.estaCasillaOcupada()) {
					// no ocupada
					movimientosLegales.add(new MovimientoPiezaMayor(tablero, this, destinoCoordenadaCandidata));
				} else {
					// ocupada
					final Pieza piezaEnDestino = destinoCasillaCandidata.getPieza();
					final Bando bandoDeLaPieza = piezaEnDestino.getBando();
					if (this.bandoDeLaPieza != bandoDeLaPieza) {
						// Si no son del mismo bando
						movimientosLegales.add(new MovimientoAtaquePiezaMayor(tablero, this, destinoCoordenadaCandidata,
								piezaEnDestino));
					}
				}
			}
		}

		return ImmutableList.copyOf(movimientosLegales);
	}

	/*
	 * Métodos que comprobarán la posición Actual del caballo para comprobar si la
	 * casilla candidata es válida para el movimiento. Parará el calculo de
	 * movimientos legales en el momento en el que la casilla de destino se sitúe
	 * fuera del tablero.
	 */
	private static boolean exclusionDePrimeraColumna(final int posicionActual, final int candidatoAExclusion) {
		return UtilidadesTablero.PRIMERA_COLUMNA[posicionActual] && (candidatoAExclusion == -17
				|| candidatoAExclusion == -10 || candidatoAExclusion == 6 || candidatoAExclusion == 15);
	}

	private static boolean exclusionDeSegundaColumna(final int posicionActual, final int candidatoAExclusion) {
		return UtilidadesTablero.SEGUNDA_COLUMNA[posicionActual]
				&& (candidatoAExclusion == -10 || candidatoAExclusion == 6);
	}

	private static boolean exclusionDeSeptimaColumna(final int posicionActual, final int candidatoAExclusion) {
		return UtilidadesTablero.SEPTIMA_COLUMNA[posicionActual]
				&& (candidatoAExclusion == 10 || candidatoAExclusion == -6);
	}

	private static boolean exclusionDeOctavaColumna(final int posicionActual, final int candidatoAExclusion) {
		return UtilidadesTablero.OCTAVA_COLUMNA[posicionActual] && (candidatoAExclusion == 17
				|| candidatoAExclusion == 10 || candidatoAExclusion == -6 || candidatoAExclusion == -15);
	}

	@Override
	public String toString() {
		return tipoDePieza.CABALLO.toString();
	}

	@Override
	public Caballo moverPieza(Movimiento movimiento) {
		return new Caballo(movimiento.getCoordenadaDeDestino(), movimiento.getPiezaMovida().getBando());
	}
}
