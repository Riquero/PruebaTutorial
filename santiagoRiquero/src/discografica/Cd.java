/** 1�DAM IES Donoso Cort�s Programaci�n
 * @author Santiago Riquero Mart�nez 
 * @version 1.0
 * @date 21/04/2020
 * Clase para crear objetos de tipo Cd.
 */

package discografica;

import java.util.Calendar;

/**
 * Clase que define la forma de los objetos de tipo Cd, heredados de la clase
 * Disco.
 */

public class Cd extends Disco {

	// Constructor por defecto.
	public Cd() {
		super();
		this.setFormato(formato.CD);
	}

	// Constructor parametrizado.
	public Cd(String artista, String titulo, String selloDisc, genero estilo, Calendar fecha, double precio) {
		super(artista, titulo, selloDisc, estilo, fecha, precio);
		this.setFormato(formato.CD);
	}

	// M�todo toString para la impresi�n por consola.
	public String toString() {
		return super.toString() + "\n Formato: " + getFormato();
	}
}
