package piezas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import jugadores.Bando;
import tablero.Casilla;
import tablero.Movimiento;
import tablero.Movimiento.MovimientoPiezaMayor;
import tablero.Movimiento.MovimientoPiezaMayor.MovimientoDeAtaque;
import tablero.Tablero;
import tablero.UtilidadesTablero;

public class Torre extends Pieza {

	private static final int[] VECTOR_COORDENADA_MOVIMIENTO_CANDIDATA = { -8, -1, 1, 8 };

	Torre(int posicionPieza, Bando bandoDeLaPieza) {
		super(posicionPieza, bandoDeLaPieza);
	}

	public Collection<Movimiento> calculaMovimientosLegales(final Tablero tablero) {

		final List<Movimiento> movimientosLegales = new ArrayList<>();

		for (final int candidatoActual : VECTOR_COORDENADA_MOVIMIENTO_CANDIDATA) {

			int destinoCoordenadaCandidata = this.posicionPieza;

			while (UtilidadesTablero.esCasillaValida(destinoCoordenadaCandidata)) {
				// ????
				if (exclusionDePrimeraColumna(destinoCoordenadaCandidata, candidatoActual)
						|| exclusionDeOctavaColumna(destinoCoordenadaCandidata, candidatoActual)) {
					break;
				}

				destinoCoordenadaCandidata += candidatoActual;

				if (UtilidadesTablero.esCasillaValida(destinoCoordenadaCandidata)) {

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
							movimientosLegales.add(
									new MovimientoDeAtaque(tablero, this, destinoCoordenadaCandidata, piezaEnDestino));
						}
						break; // permite seguir la diagonal al no encontrar ninguna pieza bloqueando 
					}
				}
			}
		}
		return ImmutableList.copyOf(movimientosLegales);
	}

	private boolean exclusionDeOctavaColumna(final int posicionactual,final int posicionCandidata) {
		return UtilidadesTablero.PRIMERA_COLUMNA[posicionactual] && (posicionCandidata == -1);
	}

	private boolean exclusionDePrimeraColumna(final int posicionactual,final int posicionCandidata) {
		return UtilidadesTablero.OCTAVA_COLUMNA[posicionactual] && (posicionCandidata == 1);
	}

}
