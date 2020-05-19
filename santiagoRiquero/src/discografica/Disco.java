/** 1�DAM IES Donoso Cort�s Programaci�n
 * @author Santiago Riquero Mart�nez 
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
 * <li>nombreArtista: String que almacenar� el nombre del artista.
 * <li>titulo: String que almacenar� el t�tulo del disco.
 * <li>selloDiscogr�fico: String que almacenar� el nombre del Sello
 * Discogr�fico.
 * <li>estiloMusica: String que almacenar� el estilo/g�nero del disco.
 * <li>formato: String que almacena el formato del disco, CD, MP3 o Vinilo.
 * <li>fecha: String que almacenar� la fecha en este formato: dd-mm-yyyy
 * <li>precio: Double que almacenar� el precio del disco.
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
	 * Permite modificar el nombre del artista. En esta primera versi�n no se
	 * utilizar� (pensado para un men� de modificaci�n).
	 * 
	 * @param nombreArtista String que modificar� el nombre del artista.
	 */
	public void setNombreArtista(String nombreArtista) {
		this.nombreArtista = nombreArtista;
	}

	/*
	 * Obtiene el t�tulo del disco.
	 * 
	 * @return String con el t�tulo del disco.
	 */
	public String getTitulo() {
		return titulo;
	}

	/*
	 * Permite modificar el t�tulo del disco. En esta primera versi�n no se
	 * utilizar� (pensado para un men� de modificaci�n).
	 * 
	 * @param titulo String que modificar� el t�tulo del disco.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/*
	 * Obtiene el nombre del sello discogr�fico del disco.
	 * 
	 * @return String con el nombre del sello discogr�fico.
	 */
	public String getSelloDiscografico() {
		return selloDiscografico;
	}

	/*
	 * Permite modificar el sello discogr�fico. En esta primera versi�n no se
	 * utilizar� (pensado para un men� de modificaci�n).
	 * 
	 * @param selloDiscogr�fico String que modificar� el sello discogr�fico.
	 */
	public void setSelloDiscografico(String selloDiscografico) {
		this.selloDiscografico = selloDiscografico;
	}

	/*
	 * Obtiene el estilo/g�nero del disco.
	 * 
	 * @return String con el estilo/g�nero del disco.
	 */
	public genero getEstiloMusica() {
		return genero;
	}

	/*
	 * Permite modificar el estilo de m�sica del disco. En esta primera versi�n no
	 * se utilizar� (pensado para un men� de modificaci�n).
	 * 
	 * @param estiloMusica String que modificar� el estilo/g�nero del disco.
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
	 * Permite modificar el formato del disco. El usuario no tendr� acceso a este
	 * m�todo en esta primera versi�n.
	 * 
	 * @param formato String que modificar� el formato del disco.
	 */
	public void setFormato(Formato.formato formato) {
		this.formato = formato;
	}

	/*
	 * Obtiene la fecha de publicaci�n de este disco.
	 * 
	 * @return String con la fecha de publicaci�n de este disco.
	 */
	public Calendar getFecha() {
		return fecha;
	}

	/*
	 * Permite modificar la fecha de publicaci�n del disco. En esta primera versi�n
	 * no se utilizar� (pensado para un men� de modificaci�n).
	 * 
	 * @param fecha String que modificar� la fecha de publicaci�n del disco.
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
	 * Permite modificar el precio del disco. En esta primera versi�n no se
	 * utilizar� (pensado para un men� de modificaci�n).
	 * 
	 * @param precio Double que modificar� el precio del disco.
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	// M�todo toString
	public String toString() {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		String formatoDePrecio = nf.format(getPrecio());
		return "Disco " + "\n Artista: " + getNombreArtista() + "\n Titulo: " + getTitulo() + "\n Sello discografico: "
				+ getSelloDiscografico() + "\n Estilo de musica: " + getEstiloMusica() + "\n Precio: " + formatoDePrecio
				+ "�" + "\n--------------------------------" + "\n Fecha: " + fecha.get(Calendar.YEAR) + "-"
				+ fecha.get(Calendar.MONTH) + "-" + fecha.get(Calendar.DAY_OF_MONTH);
	}

	/*
	 * M�todo proveniente de la interfaz Comparable, el cual ser� utilizado por la
	 * colecci�n din�mica TreeSet para ordenar seg�n 4 atributos en respectivo
	 * orden: nombreArtista, titulo, precio y formato.
	 * 
	 * @param o Disco utilizado para ordenar la colecci�n. Sus atributos estar�n
	 * sujetos a comparaci�n con el objeto que llama a este m�todo.
	 * 
	 * @return Un entero con el resultado de la comparaci�n entre los atributos de
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
