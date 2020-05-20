package jugadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import piezas.Pieza;
import piezas.Torre;
import tablero.Casilla;
import tablero.Movimiento;
import tablero.Movimiento.EnroqueLadoDeReina;
import tablero.Movimiento.EnroqueLadoDelRey;
import tablero.Tablero;

/**
 * 
 * @author Santiago Riquero MartÃ­nez
 *
 */
public class JugadorBlancas extends Jugador {

	public JugadorBlancas(final Tablero tablero, final Collection<Movimiento> movimientosEstandarLegalesBlancas,
			final Collection<Movimiento> movimientosEstandarLegalesNegras) {
		super(tablero, movimientosEstandarLegalesBlancas, movimientosEstandarLegalesNegras);
	}

	@Override
	public Collection<Pieza> getPiezasActivas() {
		return this.tablero.getPiezasBlancas();
	}

	@Override
	public Bando getBando() {

		return Bando.BLANCAS;
	}

	@Override
	public Jugador getOponente() {
		return this.tablero.jugadorNegras();
	}

	@Override
	protected Collection<Movimiento> calcularEnroquesDelRey(final Collection<Movimiento> jugadorLegal,
			final Collection<Movimiento> oponenteLegal) {
		
		final List<Movimiento> enroquesDelRey = new ArrayList<>();

		if (this.reyDelJugador.esPrimerMovimiento() && !this.estaEnJaque()) {
			// enroque corto del rey de blancas
			if (!this.tablero.getCasilla(61).estaCasillaOcupada()
					&& !this.tablero.getCasilla(62).estaCasillaOcupada()) {
				final Casilla casillaDeTorre = this.tablero.getCasilla(63);

				if (casillaDeTorre.estaCasillaOcupada() && casillaDeTorre.getPieza().esPrimerMovimiento()) {
					if (Jugador.calcularAtaquesEnCasilla(61, oponenteLegal).isEmpty()
							&& Jugador.calcularAtaquesEnCasilla(62, oponenteLegal).isEmpty()
							&& casillaDeTorre.getPieza().getTipoDePieza().esTorre()) {
						// TODO añadir un movimiento de enroque
						enroquesDelRey.add(new EnroqueLadoDelRey(this.tablero, this.reyDelJugador, 62, (Torre) casillaDeTorre.getPieza(), casillaDeTorre.getCoordenadaCasilla(), 61));
					}
				}
			}
			// Enroque largo del rey de blancas
			//Falta comprobar ataques en casilla
			if (!this.tablero.getCasilla(59).estaCasillaOcupada() && !this.tablero.getCasilla(58).estaCasillaOcupada()
					&& !this.tablero.getCasilla(57).estaCasillaOcupada()) {
				final Casilla casillaDeTorre = this.tablero.getCasilla(56);

				if (casillaDeTorre.estaCasillaOcupada() && casillaDeTorre.getPieza().esPrimerMovimiento()) {
					// TODO añadir un movimiento de enroque
					if (Jugador.calcularAtaquesEnCasilla(59, oponenteLegal).isEmpty()
							&& Jugador.calcularAtaquesEnCasilla(58, oponenteLegal).isEmpty()
							&& Jugador.calcularAtaquesEnCasilla(57, oponenteLegal).isEmpty()
							&& casillaDeTorre.getPieza().getTipoDePieza().esTorre()) {
						// TODO añadir un movimiento de enroque
						enroquesDelRey.add(new EnroqueLadoDeReina(this.tablero, this.reyDelJugador, 58, (Torre) casillaDeTorre.getPieza(), casillaDeTorre.getCoordenadaCasilla(), 59));
					}
				}
			}
		}
		return ImmutableList.copyOf(enroquesDelRey);
	}
}
