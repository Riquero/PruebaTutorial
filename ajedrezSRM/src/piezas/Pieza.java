package piezas;

import java.util.Collection;

import jugadores.Bando;
import tablero.Movimiento;
import tablero.Tablero;

/**
 * 
 * @author Santiago Riquero Martínez
 *
 */
public abstract class Pieza {

	protected final TipoDePieza tipoDePieza;
	protected final int posicionPieza;
	protected final Bando bandoDeLaPieza;
	protected final boolean esPrimerMovimiento;
	private final int hashCodeCache;

	Pieza(final int posicionPieza, final Bando bandoDeLaPieza, final TipoDePieza tipoDePieza) {
		this.bandoDeLaPieza = bandoDeLaPieza;
		this.posicionPieza = posicionPieza;
		this.esPrimerMovimiento = false;
		this.tipoDePieza = tipoDePieza;
		this.hashCodeCache = computarHashCode();
	}

	private int computarHashCode() {
		int resultado = tipoDePieza.hashCode();
		resultado = 31* resultado + bandoDeLaPieza.hashCode();
		resultado = 31* resultado + posicionPieza;
		resultado = 31* resultado + (esPrimerMovimiento ? 1 : 0);
		return resultado;
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

	public TipoDePieza getTipoDePieza() {
		return this.tipoDePieza;
	}

	@Override
	public boolean equals(final Object otro) {
		if (this == otro) {
			return true;
		}
		if (!(otro instanceof Pieza)) {
			return false;
		}
		final Pieza otraPieza = (Pieza) otro;
		return posicionPieza == otraPieza.getPosicionPieza() && tipoDePieza == otraPieza.getTipoDePieza()
				&& bandoDeLaPieza == otraPieza.getBando() && esPrimerMovimiento == otraPieza.esPrimerMovimiento();
	}

	@Override
	public int hashCode() {
		return this.hashCodeCache;
	}

	public abstract Collection<Movimiento> calculaMovimientosLegales(final Tablero tablero);

	public abstract Pieza moverPieza(Movimiento movimiento);

	public enum TipoDePieza {

		PEON("P") {
			@Override
			public boolean esRey() {
				return false;
			}

			@Override
			public boolean esTorre() {
				return false;
			}
		},
		CABALLO("K") {
			@Override
			public boolean esRey() {
				return false;
			}
			@Override
			public boolean esTorre() {
				return false;
			}
		},
		ALFIL("A") {
			@Override
			public boolean esRey() {
				return false;
			}
			@Override
			public boolean esTorre() {
				return false;
			}
		},
		TORRE("T") {
			@Override
			public boolean esRey() {
				return false;
			}
			@Override
			public boolean esTorre() {
				return true;
			}
		},
		REINA("Q") {
			@Override
			public boolean esRey() {
				return false;
			}
			@Override
			public boolean esTorre() {
				return false;
			}
		},
		REY("R") {
			@Override
			public boolean esRey() {
				return true;
			}
			@Override
			public boolean esTorre() {
				return false;
			}
		};

		private String nombreDePieza;

		TipoDePieza(String nombreDePieza) {
			this.nombreDePieza = nombreDePieza;
		}

		@Override
		public String toString() {
			return this.nombreDePieza;
		}

		public abstract boolean esRey();

		public abstract boolean esTorre();
	}
}
