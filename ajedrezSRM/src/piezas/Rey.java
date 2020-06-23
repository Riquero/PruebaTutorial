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

public class Rey extends Pieza {

	private final static int[] VECTOR_COORDENADA_MOVIMIENTO_CANDIDATA = { -9, -8, -7, -1, 1, 7, 8, 9 };

	public Rey(final int posicionPieza, final Bando bandoDeLaPieza) {
		super(posicionPieza, bandoDeLaPieza, TipoDePieza.REY);
	}

	public Collection<Movimiento> calculaMovimientosLegales(Tablero tablero) {

		final List<Movimiento> movimientosLegales = new ArrayList<>();

		for (final int candidatoActual : VECTOR_COORDENADA_MOVIMIENTO_CANDIDATA) {
			final int destinoCoordenadaCandidata = this.posicionPieza + candidatoActual;
			if (UtilidadesTablero.esCasillaValida(destinoCoordenadaCandidata)) {
				final Casilla destinoCasillaCandidata = tablero.getCasilla(destinoCoordenadaCandidata);

				if (exclusionDePrimeraColumna(this.posicionPieza, candidatoActual)
						|| exclusionDeOctavaColumna(this.posicionPieza, candidatoActual)) {
					continue;
				}

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

	private static boolean exclusionDePrimeraColumna(final int posicionActual, final int candidatoAExclusion) {
		return UtilidadesTablero.PRIMERA_COLUMNA[posicionActual]
				&& (candidatoAExclusion == -9 || candidatoAExclusion == -1 || candidatoAExclusion == 7);
	}

	private static boolean exclusionDeOctavaColumna(final int posicionActual, final int candidatoAExclusion) {
		return UtilidadesTablero.OCTAVA_COLUMNA[posicionActual]
				&& (candidatoAExclusion == -7 || candidatoAExclusion == 1 || candidatoAExclusion == 9);
	}

	@Override
	public String toString() {
		return tipoDePieza.REY.toString();
	}

	@Override
	public Rey moverPieza(Movimiento movimiento) {
		return new Rey(movimiento.getCoordenadaDeDestino(), movimiento.getPiezaMovida().getBando());
	}
}
