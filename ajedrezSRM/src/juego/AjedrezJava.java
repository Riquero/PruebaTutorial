package juego;

import Interfaz.Tabla;
import tablero.Tablero;

public class AjedrezJava {

	public static void main(String[] args) {
		
		Tablero tablero = Tablero.crearTableroEstandar();
		
		System.out.println(tablero);
		
		Tabla tabla = new Tabla();
	}

}
