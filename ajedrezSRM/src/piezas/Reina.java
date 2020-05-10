package piezas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import jugadores.Bando;
import tablero.Casilla;
import tablero.Movimiento;
import tablero.Tablero;
import tablero.UtilidadesTablero;
import tablero.Movimiento.MovimientoPiezaMayor;
import tablero.Movimiento.MovimientoPiezaMayor.MovimientoDeAtaque;

public class Reina extends Pieza{

	// Combinación de torre+alfil
	private final static int[] VECTOR_COORDENADA_MOVIMIENTO_CANDIDATA = { -9, -8, -7, -1, 1, 7, 8, 9 };
	
	public Reina(final int posicionPieza, final Bando bandoDeLaPieza) {
		super(posicionPieza, bandoDeLaPieza);
	}

	public Collection<Movimiento> calculaMovimientosLegales(final Tablero tablero) {
		
		final List<Movimiento> movimientosLegales = new ArrayList<>();
		
		for (final int candidatoActual : VECTOR_COORDENADA_MOVIMIENTO_CANDIDATA) {
			
			int destinoCoordenadaCandidata = this.posicionPieza;

			while (UtilidadesTablero.esCasillaValida(destinoCoordenadaCandidata)) {
				if(exclusionDePrimeraColumna(destinoCoordenadaCandidata, candidatoActual) || exclusionDeOctavaColumna(destinoCoordenadaCandidata, candidatoActual)) {
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
						break; //permite seguir la diagonal al no encontrar ninguna pieza bloqueando la diagonal
					}
				}
			}
		}
		return ImmutableList.copyOf(movimientosLegales);
	}

	//movimientos no legales, que rompen la regla de movimiento habitual
	private static boolean exclusionDePrimeraColumna(final int posicionActual, final int posicionCandidata) {
		return UtilidadesTablero.PRIMERA_COLUMNA[posicionActual] && (posicionCandidata == -9 || posicionCandidata == 9 || posicionActual == 7);
	}
	
	private static boolean exclusionDeOctavaColumna(final int posicionActual, final int posicionCandidata) {
		return UtilidadesTablero.OCTAVA_COLUMNA[posicionActual] && (posicionCandidata == -7 || posicionCandidata == 1 || posicionActual == 9);
	}
}
