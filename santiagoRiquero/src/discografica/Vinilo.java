/** 1ºDAM IES Donoso Cortés Programación
 * @author Santiago Riquero Martínez 
 * @version 1.0
 * @date 21/04/2020
 * Clase para crear objetos de tipo disco.
 */

package discografica;

import java.util.Calendar;

/**
 * Clase que define la forma de los objetos de tipo Vinilo, heredados de la
 * clase Disco.
 */

public class Vinilo extends Disco {

	// Constructor por defecto.
	public Vinilo() {
		super();
		this.setFormato(formato.Vinilo);
	}

	// Constructor parametrizado.
	public Vinilo(String artista, String titulo, String selloDisc, genero estilo, Calendar fecha, double precioDisco) {
		super(artista, titulo, selloDisc, estilo, fecha, precioDisco);
		this.setFormato(formato.Vinilo);
	}

	// Método toString, utilizado para la impresión por consola del objeto de tipo
	// vinilo.
	public String toString() {
		return super.toString() + "\n Formato: " + getFormato();
	}
}
