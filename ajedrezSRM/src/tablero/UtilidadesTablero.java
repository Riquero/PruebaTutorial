package tablero;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class UtilidadesTablero {

	public static final boolean[] PRIMERA_COLUMNA = iniciarColumna(0);
	public static final boolean[] SEGUNDA_COLUMNA = iniciarColumna(1);
	public static final boolean[] SEPTIMA_COLUMNA = iniciarColumna(6);
	public static final boolean[] OCTAVA_COLUMNA = iniciarColumna(7);

	public static final boolean[] PRIMERA_FILA = iniciarFila(56);
	public static final boolean[] SEGUNDA_FILA = iniciarFila(48);
	public static final boolean[] TERCERA_FILA = iniciarFila(40);
	public static final boolean[] CUARTA_FILA = iniciarFila(32);
	public static final boolean[] QUINTA_FILA = iniciarFila(24);
	public static final boolean[] SEXTA_FILA = iniciarFila(16);
	public static final boolean[] SEPTIMA_FILA = iniciarFila(8);
	public static final boolean[] OCTAVA_FILA = iniciarFila(0);

	public static final String[] NOTACION_ALGEBRAICA = inicializarNotacionAlgebraica();
	public static final Map<String, Integer> POSICION_A_COORDENADA = inicializarMapaPosicionACoordenada();

	public static final int NUMERO_DE_CASILLAS = 64;
	public static final int NUMERO_DE_CASILLAS_POR_FILA = 8;

	private UtilidadesTablero() {
		throw new RuntimeException("No puedes instanciarme");
	}

	private static String[] inicializarNotacionAlgebraica() {
		return new String[] { "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8", "a7", "b7", "c7", "d7", "e7", "f7", "g7",
				"h7", "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6", "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
				"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4", "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3", "a2",
				"b2", "c2", "d2", "e2", "f2", "g2", "h2", "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1" };
	}

	private static Map<String, Integer> inicializarMapaPosicionACoordenada() {
		final Map<String, Integer> posicionACoordenada = new HashMap<>();
		for (int i = 0; i < NUMERO_DE_CASILLAS; i++) {
			posicionACoordenada.put(NOTACION_ALGEBRAICA[i], i);
		}
		return ImmutableMap.copyOf(posicionACoordenada);
	}

	private static boolean[] iniciarColumna(int numeroDeColumna) {
		final boolean[] columna = new boolean[NUMERO_DE_CASILLAS];
		do {
			columna[numeroDeColumna] = true;
			numeroDeColumna += NUMERO_DE_CASILLAS_POR_FILA; // para permanecer en la misma columna
		} while (numeroDeColumna < NUMERO_DE_CASILLAS);
		return columna;
	}

	private static boolean[] iniciarFila(int numeroDeFila) {
		final boolean[] fila = new boolean[NUMERO_DE_CASILLAS];
		do {
			fila[numeroDeFila] = true;
			numeroDeFila++;
		} while (numeroDeFila % NUMERO_DE_CASILLAS != 0);
		return fila;
	}

	public static boolean esCasillaValida(final int coordenada) {
		return coordenada >= 0 && coordenada < NUMERO_DE_CASILLAS;
	}

	public static int getCoordenadaEnPosicion(final String posicion) {
		return POSICION_A_COORDENADA.get(posicion);
	}

	public static String getPosicionEnCoordenada(final int coordenada) {
		return NOTACION_ALGEBRAICA[coordenada];
	}
}
