package jugadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import piezas.Pieza;
import piezas.Torre;
import tablero.Casilla;
import tablero.Movimiento;
import tablero.Tablero;
import tablero.Movimiento.EnroqueLadoDeReina;
import tablero.Movimiento.EnroqueLadoDelRey;

/**
 * 
 * @author Santiago Riquero MartÃ­nez
 *
 */
public class JugadorNegras extends Jugador {

	public JugadorNegras(final Tablero tablero,final Collection<Movimiento> movimientosEstandarLegalesNegras,
			final Collection<Movimiento> movimientosEstandarLegalesBlancas) {
		super(tablero, movimientosEstandarLegalesNegras, movimientosEstandarLegalesBlancas);
	}

	@Override
	public Collection<Pieza> getPiezasActivas() {
		return this.tablero.getPiezasNegras();
	}

	@Override
	public Bando getBando() {
		return Bando.NEGRAS;
	}

	@Override
	public Jugador getOponente() {
		return this.tablero.jugadorBlancas();
	}

	@Override
	protected Collection<Movimiento> calcularEnroquesDelRey(final Collection<Movimiento> jugadorLegal,
			final Collection<Movimiento> oponenteLegal) {
		
		final List<Movimiento> enroquesDelRey = new ArrayList<>();

		if (this.reyDelJugador.esPrimerMovimiento() && !this.estaEnJaque()) {
			// enroque corto del rey de negras
			if (!this.tablero.getCasilla(5).estaCasillaOcupada()
					&& !this.tablero.getCasilla(6).estaCasillaOcupada()) {
				final Casilla casillaDeTorre = this.tablero.getCasilla(7);

				if (casillaDeTorre.estaCasillaOcupada() && casillaDeTorre.getPieza().esPrimerMovimiento()) {
					if (Jugador.calcularAtaquesEnCasilla(5, oponenteLegal).isEmpty()
							&& Jugador.calcularAtaquesEnCasilla(6, oponenteLegal).isEmpty()
							&& casillaDeTorre.getPieza().getTipoDePieza().esTorre()) {
						// TODO añadir un movimiento de enroque
						enroquesDelRey.add(new EnroqueLadoDelRey(this.tablero, this.reyDelJugador, 6, (Torre) casillaDeTorre.getPieza(), casillaDeTorre.getCoordenadaCasilla(), 5));
					}
				}
			}
			// Enroque largo del rey de negras
			//Falta comprobar ataques en casilla 
			if (!this.tablero.getCasilla(1).estaCasillaOcupada() && !this.tablero.getCasilla(2).estaCasillaOcupada()
					&& !this.tablero.getCasilla(3).estaCasillaOcupada()) {
				final Casilla casillaDeTorre = this.tablero.getCasilla(0);

				if (casillaDeTorre.estaCasillaOcupada() && casillaDeTorre.getPieza().esPrimerMovimiento()) {
					// TODO añadir un movimiento de enroque
					if (Jugador.calcularAtaquesEnCasilla(1, oponenteLegal).isEmpty()
							&& Jugador.calcularAtaquesEnCasilla(2, oponenteLegal).isEmpty()
							&& Jugador.calcularAtaquesEnCasilla(3, oponenteLegal).isEmpty()
							&& casillaDeTorre.getPieza().getTipoDePieza().esTorre()) {
						// TODO añadir un movimiento de enroque
						enroquesDelRey.add(new EnroqueLadoDeReina(this.tablero, this.reyDelJugador, 2, (Torre) casillaDeTorre.getPieza(), casillaDeTorre.getCoordenadaCasilla(), 3));
					}
				}
			}
		}
		return ImmutableList.copyOf(enroquesDelRey);
	}
}
