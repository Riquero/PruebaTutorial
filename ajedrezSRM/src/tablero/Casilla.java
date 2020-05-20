package tablero;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import piezas.Pieza;

/**
 * @author Santiago Riquero MartÃ­nez
 * @date 24/04/2020
 */

public abstract class Casilla {

	// Atributos de clase.
	protected final int coordenadaCasilla;
	// Quiero que sea protegido e inmutable (final).
	private static final Map<Integer, CasillaVacia> CACHE_DE_CASILLAS_VACIAS = crearTodasLasCasillasVaciasPosibles();

	// Constructores
	public Casilla(int coordenadaCasilla) {
		this.coordenadaCasilla = coordenadaCasilla;
	}

	public int getCoordenadaCasilla() {
		return this.coordenadaCasilla;
	}
	
	public abstract boolean estaCasillaOcupada();

	public abstract Pieza getPieza();

	public static Casilla crearCasilla(final int coordenadaCasilla, final Pieza pieza) {
		return pieza != null ? new CasillaOcupada(coordenadaCasilla, pieza)
				: CACHE_DE_CASILLAS_VACIAS.get(coordenadaCasilla);
	}

	/*
	 * Este método devolverá un mapa inmutable (seguro) para ser utilizado en el
	 * constructor de tablero.
	 */
	private static Map<Integer, CasillaVacia> crearTodasLasCasillasVaciasPosibles() {
		final Map<Integer, CasillaVacia> mapaCasillaVacia = new HashMap<>();

		for (int i = 0; i < UtilidadesTablero.NUMERO_DE_CASILLAS; i++) {
			mapaCasillaVacia.put(i, new CasillaVacia(i));
		}

		return ImmutableMap.copyOf(mapaCasillaVacia);
	}

	public static final class CasillaVacia extends Casilla {

		CasillaVacia(final int coordenada) {
			super(coordenada);
		}

		@Override
		public String toString() {
			return "-";
		}

		@Override
		public Pieza getPieza() {
			return null;
		}

		@Override
		public boolean estaCasillaOcupada() {
			return false;
		}

	}

	public static final class CasillaOcupada extends Casilla {

		private final Pieza piezaEnCasilla;

		CasillaOcupada(int coordenada, final Pieza pieza) {
			super(coordenada);
			this.piezaEnCasilla = pieza;
		}

		@Override
		public String toString() {
			return getPieza().getBando().esNegra() ? getPieza().toString().toLowerCase() : getPieza().toString();
		}

		@Override
		public boolean estaCasillaOcupada() {
			return true;
		}

		@Override
		public Pieza getPieza() {
			return this.piezaEnCasilla;
		}
	}
}
