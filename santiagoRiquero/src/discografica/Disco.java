/** 1ºDAM IES Donoso Cortés Programación
 * @author Santiago Riquero Martínez 
 * @version 1.0
 * @date 22/04/2020
 */

package discografica;

import java.text.NumberFormat;
import java.util.Calendar;

/**
 * Clase que define la forma de los objetos de tipo Disco. Incluye los
 * siguientes atributos:
 * <ul>
 * <li>nombreArtista: String que almacenará el nombre del artista.
 * <li>titulo: String que almacenará el título del disco.
 * <li>selloDiscográfico: String que almacenará el nombre del Sello
 * Discográfico.
 * <li>estiloMusica: String que almacenará el estilo/género del disco.
 * <li>formato: String que almacena el formato del disco, CD, MP3 o Vinilo.
 * <li>fecha: String que almacenará la fecha en este formato: dd-mm-yyyy
 * <li>precio: Double que almacenará el precio del disco.
 * </ul>
 */

public class Disco implements Comparable<Disco>, Formato, EstiloMusical {

	// Atributos de clase
	private String nombreArtista, titulo, selloDiscografico;
	private EstiloMusical.genero genero;
	private Formato.formato formato;
	private Calendar fecha;
	private Double precio;

	// Constructor por defecto
	public Disco() {

	}

	// Constructor parametrizado
	public Disco(String artista, String titulo, String selloDisc, genero estilo, Calendar fecha, double precioDisco) {
		nombreArtista = artista;
		this.titulo = titulo;
		selloDiscografico = selloDisc;
		genero = estilo;
		this.fecha = fecha;
		precio = precioDisco;
	}

	// Setters & Getters

	/*
	 * Obtiene el nombre del artista.
	 * 
	 * @return String con el nombre del artista.
	 */
	public String getNombreArtista() {
		return nombreArtista;
	}

	/*
	 * Permite modificar el nombre del artista. En esta primera versión no se
	 * utilizará (pensado para un menú de modificación).
	 * 
	 * @param nombreArtista String que modificará el nombre del artista.
	 */
	public void setNombreArtista(String nombreArtista) {
		this.nombreArtista = nombreArtista;
	}

	/*
	 * Obtiene el título del disco.
	 * 
	 * @return String con el título del disco.
	 */
	public String getTitulo() {
		return titulo;
	}

	/*
	 * Permite modificar el título del disco. En esta primera versión no se
	 * utilizará (pensado para un menú de modificación).
	 * 
	 * @param titulo String que modificará el título del disco.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/*
	 * Obtiene el nombre del sello discográfico del disco.
	 * 
	 * @return String con el nombre del sello discográfico.
	 */
	public String getSelloDiscografico() {
		return selloDiscografico;
	}

	/*
	 * Permite modificar el sello discográfico. En esta primera versión no se
	 * utilizará (pensado para un menú de modificación).
	 * 
	 * @param selloDiscográfico String que modificará el sello discográfico.
	 */
	public void setSelloDiscografico(String selloDiscografico) {
		this.selloDiscografico = selloDiscografico;
	}

	/*
	 * Obtiene el estilo/género del disco.
	 * 
	 * @return String con el estilo/género del disco.
	 */
	public genero getEstiloMusica() {
		return genero;
	}

	/*
	 * Permite modificar el estilo de música del disco. En esta primera versión no
	 * se utilizará (pensado para un menú de modificación).
	 * 
	 * @param estiloMusica String que modificará el estilo/género del disco.
	 */
	public void setEstiloMusica(genero estiloMusica) {
		this.genero = estiloMusica;
	}

	/*
	 * Obtiene el formato del disco.
	 * 
	 * @return String con el formato del disco
	 */
	public Formato.formato getFormato() {
		return formato;
	}

	/*
	 * Permite modificar el formato del disco. El usuario no tendrá acceso a este
	 * método en esta primera versión.
	 * 
	 * @param formato String que modificará el formato del disco.
	 */
	public void setFormato(Formato.formato formato) {
		this.formato = formato;
	}

	/*
	 * Obtiene la fecha de publicación de este disco.
	 * 
	 * @return String con la fecha de publicación de este disco.
	 */
	public Calendar getFecha() {
		return fecha;
	}

	/*
	 * Permite modificar la fecha de publicación del disco. En esta primera versión
	 * no se utilizará (pensado para un menú de modificación).
	 * 
	 * @param fecha String que modificará la fecha de publicación del disco.
	 */
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	/*
	 * Obtiene el precio del disco.
	 * 
	 * @return Double con el precio del disco.
	 */
	public double getPrecio() {
		return precio;
	}

	/*
	 * Permite modificar el precio del disco. En esta primera versión no se
	 * utilizará (pensado para un menú de modificación).
	 * 
	 * @param precio Double que modificará el precio del disco.
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	// Método toString
	public String toString() {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		String formatoDePrecio = nf.format(getPrecio());
		return "Disco " + "\n Artista: " + getNombreArtista() + "\n Titulo: " + getTitulo() + "\n Sello discografico: "
				+ getSelloDiscografico() + "\n Estilo de musica: " + getEstiloMusica() + "\n Precio: " + formatoDePrecio
				+ "€" + "\n--------------------------------" + "\n Fecha: " + fecha.get(Calendar.YEAR) + "-"
				+ fecha.get(Calendar.MONTH) + "-" + fecha.get(Calendar.DAY_OF_MONTH);
	}

	/*
	 * Método proveniente de la interfaz Comparable, el cual será utilizado por la
	 * colección dinámica TreeSet para ordenar según 4 atributos en respectivo
	 * orden: nombreArtista, titulo, precio y formato.
	 * 
	 * @param o Disco utilizado para ordenar la colección. Sus atributos estarán
	 * sujetos a comparación con el objeto que llama a este método.
	 * 
	 * @return Un entero con el resultado de la comparación entre los atributos de
	 * los dos objetos.
	 */
	public int compareTo(Disco o) {
		int comparacion = this.getNombreArtista().compareToIgnoreCase(o.getNombreArtista());
		if (comparacion == 0) {
			comparacion = this.getTitulo().compareToIgnoreCase(o.getTitulo());
			if (comparacion == 0) {
				comparacion = (int) Math.round(this.getPrecio() - this.getPrecio());
				if (comparacion == 0) {
					return this.getFormato().compareTo(o.getFormato());
				} else {
					return comparacion;
				}
			} else {
				return comparacion;
			}
		} else {
			return comparacion;
		}
	}
}
