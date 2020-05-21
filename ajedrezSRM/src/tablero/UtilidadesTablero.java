package tablero;

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

	public static final int NUMERO_DE_CASILLAS = 64;
	public static final int NUMERO_DE_CASILLAS_POR_FILA = 8;

	private UtilidadesTablero() {
		throw new RuntimeException("No puedes instanciarme");
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

}
