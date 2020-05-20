package jugadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import piezas.Pieza;
import piezas.Rey;
import tablero.Movimiento;
import tablero.Tablero;

/**
 * 
 * @author Santiago Riquero MartÃ­nez
 *
 */
public abstract class Jugador {

	protected final Tablero tablero;
	protected final Rey reyDelJugador;
	protected final Collection<Movimiento> movimientosLegales;
	private final boolean estaEnJaque;

	Jugador(final Tablero tablero, final Collection<Movimiento> movimientosLegales,
			final Collection<Movimiento> movimientosDelOponente) {
		this.tablero = tablero;
		this.reyDelJugador = establecerRey();
		this.movimientosLegales = ImmutableList.copyOf(Iterables.concat(movimientosLegales, calcularEnroquesDelRey(movimientosLegales, movimientosDelOponente)));
		this.estaEnJaque = !Jugador
				.calcularAtaquesEnCasilla(this.reyDelJugador.getPosicionPieza(), movimientosDelOponente).isEmpty();
	}

	protected static Collection<Movimiento> calcularAtaquesEnCasilla(int posicionPieza,
			Collection<Movimiento> movimientos) {
		final List<Movimiento> movimientosDeAtaque = new ArrayList<>();
		for (final Movimiento movimiento : movimientos) {
			if (posicionPieza == movimiento.getCoordenadaDeDestino()) {
				movimientosDeAtaque.add(movimiento);
			}
		}
		return ImmutableList.copyOf(movimientosDeAtaque);
	}

	private Rey establecerRey() {
		for (final Pieza pieza : getPiezasActivas()) {
			if (pieza.getTipoDePieza().esRey()) {
				return (Rey) pieza;
			}
		}
		throw new RuntimeException("No debería llegar a este punto. No es un tablero válido");
	}

	public boolean esUnMovimientoLegal(final Movimiento movimiento) {
		return this.movimientosLegales.contains(movimiento);
	}

	public boolean estaEnJaque() {
		return this.estaEnJaque;
	}

	public boolean estaEnJaqueMate() {
		return this.estaEnJaque && !tieneMovimientosDeEscape();
	}

	protected boolean tieneMovimientosDeEscape() {
		for (final Movimiento movimiento : this.movimientosLegales) {
			final MovimientoEnCurso transicion = hacerMovimiento(movimiento);
			if (transicion.getEstadoDeMovimiento().haTerminado()) {
				return true;
			}
		}
		return false;
	}

	public boolean estaAhogado() {
		return !this.estaEnJaque && !tieneMovimientosDeEscape();
	}

	public boolean haEnrocado() {
		return false;
	}

	public MovimientoEnCurso hacerMovimiento(final Movimiento movimiento) {

		if (!esUnMovimientoLegal(movimiento)) {
			return new MovimientoEnCurso(this.tablero, movimiento, EstadoDeMovimiento.MOVIMIENTO_ILEGAL);
		}

		final Tablero tableroDeTransicion = movimiento.ejecutar();

		final Collection<Movimiento> ataquesDelRey = Jugador.calcularAtaquesEnCasilla(
				tableroDeTransicion.getJugadorActual().getOponente().getReyDelJugador().getPosicionPieza(),
				tableroDeTransicion.getJugadorActual().getMovimientosLegales());
		
		if(!ataquesDelRey.isEmpty()) {
			return new MovimientoEnCurso(this.tablero, movimiento, EstadoDeMovimiento.DEJA_AL_JUGADOR_EN_JAQUE);
		}
		return new MovimientoEnCurso(tableroDeTransicion, movimiento, EstadoDeMovimiento.TERMINADO);
	}

	public Rey getReyDelJugador() {
		return this.reyDelJugador;
	}
	
	public Collection<Movimiento> getMovimientosLegales(){
		return this.movimientosLegales;
	}
	
	public abstract Collection<Pieza> getPiezasActivas();

	public abstract Bando getBando();

	public abstract Jugador getOponente();
	
	protected abstract Collection<Movimiento> calcularEnroquesDelRey(Collection<Movimiento> jugadorLegal, Collection<Movimiento> oponenteLegal);

}
