package tablero;

public class UtilidadesTablero {

	public static final boolean[] PRIMERA_COLUMNA = iniciarColumna(0);
	public static final boolean[] SEGUNDA_COLUMNA = iniciarColumna(1);
	public static final boolean[] SEPTIMA_COLUMNA = iniciarColumna(6);
	public static final boolean[] OCTAVA_COLUMNA = iniciarColumna(7);
	
	public static final boolean[] SEGUNDA_FILA = null;
	public static final boolean[] SEPTIMA_FILA = null;
	
	public static final int NUMERO_DE_CASILLAS = 64;
	public static final int NUMERO_DE_CASILLAS_POR_FILA = 8; 
	
	//Hola Jesús

	private UtilidadesTablero() {
		throw new RuntimeException("No puedes instanciarme");
	}
	
	private static boolean[] iniciarColumna(int numeroDeColumna) {
		final boolean[] columna = new boolean[NUMERO_DE_CASILLAS];
		do {
			columna[numeroDeColumna]= true;
			numeroDeColumna += NUMERO_DE_CASILLAS_POR_FILA; //para permanecer en la misma columna
		} while(numeroDeColumna<NUMERO_DE_CASILLAS);
		return columna;
	}
	
	public static boolean esCasillaValida(final int coordenada) {
		return coordenada >= 0 && coordenada < NUMERO_DE_CASILLAS;
	}

}
