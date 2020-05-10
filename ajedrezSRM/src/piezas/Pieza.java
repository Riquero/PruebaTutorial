package piezas;

import java.util.Collection;

import jugadores.Bando;
import tablero.Movimiento;
import tablero.Tablero;

public abstract class Pieza {

	protected final int posicionPieza;
	protected final Bando bandoDeLaPieza;
	protected final boolean esPrimerMovimiento;

	Pieza(final int posicionPieza, final Bando bandoDeLaPieza) {
		this.bandoDeLaPieza = bandoDeLaPieza;
		this.posicionPieza = posicionPieza;
		this.esPrimerMovimiento= false;
	}
	
	public int getPosicionPieza() {
		return posicionPieza;
	}
	
	public Bando getBando() {
		return this.bandoDeLaPieza;
	}
	
	public boolean esPrimerMovimiento() {
		return this.esPrimerMovimiento;
	}

	public abstract Collection<Movimiento> calculaMovimientosLegales(final Tablero tablero);
}
