package piezas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import jugadores.Bando;
import tablero.Movimiento;
import tablero.Tablero;
import tablero.UtilidadesTablero;

public class Peon extends Pieza {

	private final static int[] VECTOR_COORDENADA_MOVIMIENTO_CANDIDATA = { 8, 16, 7, 9 };

	public Peon(final int posicionPieza, final Bando bandoDeLaPieza) {
		super(posicionPieza, bandoDeLaPieza);
	}

	public Collection<Movimiento> calculaMovimientosLegales(final Tablero tablero) {

		final List<Movimiento> movimientosLegales = new ArrayList<>();

		for (final int candidatoActual : VECTOR_COORDENADA_MOVIMIENTO_CANDIDATA) {

			final int destinoCoordenadaCandidata = this.posicionPieza
					+ (this.getBando().getDirection() * candidatoActual);

			if (!UtilidadesTablero.esCasillaValida(destinoCoordenadaCandidata)) {
				continue;
			}

			if (candidatoActual == 8 && !Tablero.getCasilla(destinoCoordenadaCandidata).estaCasillaOcupada()) {
				// TODO más trabajo aquí (promociones)
				movimientosLegales.add(new Movimiento.MovimientoPiezaMayor(tablero, this, destinoCoordenadaCandidata));
			} else if (candidatoActual == 16 && this.esPrimerMovimiento()
					&& (UtilidadesTablero.SEGUNDA_FILA[this.posicionPieza] && this.getBando().esNegra())
					|| (UtilidadesTablero.SEPTIMA_FILA[this.posicionPieza] && this.getBando().esBlanca())) {
				final int coordenadaDeDetrasDeCasillaCandidata = this.posicionPieza
						+ (this.bandoDeLaPieza.getDirection() * 8);
				if (!Tablero.getCasilla(coordenadaDeDetrasDeCasillaCandidata).estaCasillaOcupada()
						&& !Tablero.getCasilla(destinoCoordenadaCandidata).estaCasillaOcupada()) {
					movimientosLegales
							.add(new Movimiento.MovimientoPiezaMayor(tablero, this, destinoCoordenadaCandidata));
				}
			} else if (candidatoActual == 7 && 
					!((UtilidadesTablero.OCTAVA_COLUMNA[this.posicionPieza] && this.bandoDeLaPieza.esBlanca()  || 
					(UtilidadesTablero.PRIMERA_COLUMNA[this.posicionPieza] && this.bandoDeLaPieza.esNegra()))  )) {
				if(Tablero.getCasilla(destinoCoordenadaCandidata).estaCasillaOcupada()) {
					final Pieza piezaEnCandidata = Tablero.getCasilla(destinoCoordenadaCandidata).getPieza();
					if(this.bandoDeLaPieza != piezaEnCandidata.getBando()) {
						//TODO
						movimientosLegales.add(new Movimiento.MovimientoPiezaMayor(tablero, this, destinoCoordenadaCandidata));
					}
				}
			} else if (candidatoActual == 9 &&
					!((UtilidadesTablero.PRIMERA_COLUMNA[this.posicionPieza] && this.bandoDeLaPieza.esBlanca()  || 
					 (UtilidadesTablero.OCTAVA_COLUMNA[this.posicionPieza] && this.bandoDeLaPieza.esNegra()))  )) {
				if(Tablero.getCasilla(destinoCoordenadaCandidata).estaCasillaOcupada()) {
					final Pieza piezaEnCandidata = Tablero.getCasilla(destinoCoordenadaCandidata).getPieza();
					if(this.bandoDeLaPieza != piezaEnCandidata.getBando()) {
						//TODO
						movimientosLegales.add(new Movimiento.MovimientoPiezaMayor(tablero, this, destinoCoordenadaCandidata));
					}
				}
			}
		}
		return ImmutableList.copyOf(movimientosLegales);
	}

}
