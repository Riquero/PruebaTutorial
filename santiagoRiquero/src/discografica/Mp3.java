/** 1ºDAM IES Donoso Cortés Programación
 * @author Santiago Riquero Martínez 
 * @version 1.0
 * @date 21/04/2020
 * Clase para crear objetos de tipo Mp3.
 */

package discografica;

import java.util.Calendar;

/**
 * Clase que define la forma de los objetos de tipo Mp3, heredados de la clase
 * Disco. Incluye el atributo calidad, de tipo String. Enum.
 */

public class Mp3 extends Disco implements Calidad {

	// Atributos de clase
	private Calidad.Calidades calidad;

	// Constructor por defecto.
	public Mp3() {
		super();
		this.setFormato(formato.MP3);
	}

	// Constructor parametrizado
	public Mp3(String artista, String titulo, String selloDisc, genero estilo, Calendar fecha, double precio,
			Calidades calidad) {
		super(artista, titulo, selloDisc, estilo, fecha, precio);
		this.setFormato(formato.MP3);
		this.calidad = calidad;
	}

	// Setters & Getters

	/*
	 * Obtiene la calidad del Mp3.
	 * 
	 * @return Calidades.calidad el enum de calidad.
	 */
	public Calidad.Calidades getCalidad() {
		return calidad;
	}

	/*
	 * Permite modificar la calidad del Mp3. Se utilizará en una versión posterior
	 * del programa.
	 * 
	 * @param calidad enum que modificará la calidad del Mp3.
	 */
	public void setCalidad(Calidad.Calidades calidad) {
		this.calidad = calidad;
	}

	// Método toString para la impresión por consola de objetos de este tipo.
	public String toString() {
		return super.toString() + "\n Formato: " + getFormato() + "\n Calidad: " + this.getCalidad();
	}

}
