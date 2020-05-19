/** 1�DAM IES Donoso Cort�s Programaci�n
 * @author Santiago Riquero Mart�nez 
 * @version 1.0
 * @date 22/04/2020
 */

package discografica;

/**
 * Interfaz que almacena las clases cuyo objetivo ser� definir la forma de ordenamiento
 * a trav�s del m�todo compare, heredado de la interfaz Comparator.
 */

import java.util.Comparator;

public interface Comparadores extends Comparator<Disco> {

	public class ComparadorArtista implements Comparator<Disco> {
		/*
		 * M�todo proveniente de la interfaz Comparator, el cual ser� utilizado por la
		 * colecci�n din�mica TreeSet para ordenar seg�n el atributo nombreArtista.
		 * 
		 * @param o1 Disco utilizado para ordenar la colecci�n mediante comparaci�n con
		 * el siguiente par�metro.
		 * 
		 * @param o2 Disco objeto similar al anterior, cuyo fin es ordenar a trav�s de
		 * la comparaci�n entre atributos.
		 * 
		 * @return Un entero con el resultado de la comparaci�n entre los atributos de
		 * los dos objetos.
		 */
		public int compare(Disco o1, Disco o2) {
			int comparacion = o1.getNombreArtista().compareToIgnoreCase(o2.getNombreArtista());
			if(comparacion == 0) {
				return (int) Math.round(o1.getPrecio() - o2.getPrecio());
			}else {
				return comparacion;				
			}
		}
	}

	public class ComparadorTitulo implements Comparator<Disco> {
		/*
		 * M�todo proveniente de la interfaz Comparator, el cual ser� utilizado por la
		 * colecci�n din�mica TreeSet para ordenar seg�n el atributo t�tulo.
		 * 
		 * @param o1 Disco utilizado para ordenar la colecci�n mediante comparaci�n con
		 * el siguiente par�metro.
		 * 
		 * @param o2 Disco objeto similar al anterior, cuyo fin es ordenar a trav�s de
		 * la comparaci�n entre atributos.
		 * 
		 * @return Un entero con el resultado de la comparaci�n entre los atributos de
		 * los dos objetos.
		 */
		public int compare(Disco o1, Disco o2) {
			return o1.getTitulo().compareToIgnoreCase(o2.getTitulo());
		}
	}

	public class ComparadorSelloDiscografico implements Comparator<Disco> {
		/*
		 * M�todo proveniente de la interfaz Comparator, el cual ser� utilizado por la
		 * colecci�n din�mica TreeSet para ordenar seg�n el atributo selloDiscogr�fico.
		 * 
		 * @param o1 Disco utilizado para ordenar la colecci�n mediante comparaci�n con
		 * el siguiente par�metro.
		 * 
		 * @param o2 Disco objeto similar al anterior, cuyo fin es ordenar a trav�s de
		 * la comparaci�n entre atributos.
		 * 
		 * @return Un entero con el resultado de la comparaci�n entre los atributos de
		 * los dos objetos.
		 */
		public int compare(Disco o1, Disco o2) {
			return o1.getSelloDiscografico().compareToIgnoreCase(o2.getSelloDiscografico());
		}
	}

	public class ComparadorGenero implements Comparator<Disco> {
		/*
		 * M�todo proveniente de la interfaz Comparator, el cual ser� utilizado por la
		 * colecci�n din�mica TreeSet para ordenar seg�n el atributo estiloMusica.
		 * 
		 * @param o1 Disco utilizado para ordenar la colecci�n mediante comparaci�n con
		 * el siguiente par�metro.
		 * 
		 * @param o2 Disco objeto similar al anterior, cuyo fin es ordenar a trav�s de
		 * la comparaci�n entre atributos.
		 * 
		 * @return Un entero con el resultado de la comparaci�n entre los atributos de
		 * los dos objetos.
		 */
		public int compare(Disco o1, Disco o2) {
			return o1.getEstiloMusica().compareTo(o2.getEstiloMusica());
		}
	}

	public class ComparadorFechaPublicacion implements Comparator<Disco> {
		/*
		 * M�todo proveniente de la interfaz Comparator, el cual ser� utilizado por la
		 * colecci�n din�mica TreeSet para ordenar seg�n el atributo fecha. 
		 * 
		 * @param o1 Disco utilizado para ordenar la colecci�n mediante comparaci�n con
		 * el siguiente par�metro.
		 * 
		 * @param o2 Disco objeto similar al anterior, cuyo fin es ordenar a trav�s de
		 * la comparaci�n entre atributos.
		 * 
		 * @return Un entero con el resultado de la comparaci�n entre los atributos de
		 * los dos objetos.
		 */
		public int compare(Disco o1, Disco o2) {
			return o1.getFecha().compareTo(o2.getFecha());
		}
	}

	public class ComparadorFormato implements Comparator<Disco> {
		/*
		 * M�todo proveniente de la interfaz Comparator, el cual ser� utilizado por la
		 * colecci�n din�mica TreeSet para ordenar seg�n el atributo formato.
		 * 
		 * @param o1 Disco utilizado para ordenar la colecci�n mediante comparaci�n con
		 * el siguiente par�metro.
		 * 
		 * @param o2 Disco objeto similar al anterior, cuyo fin es ordenar a trav�s de
		 * la comparaci�n entre atributos.
		 * 
		 * @return Un entero con el resultado de la comparaci�n entre los atributos de
		 * los dos objetos.
		 */
		public int compare(Disco o1, Disco o2) {
			int comparacion = o1.getFormato().compareTo(o2.getFormato());
			if(comparacion == 0) {
				return o1.getTitulo().compareToIgnoreCase(o2.getTitulo());
			}else {
				return comparacion;				
			} 
		}
	}

	public class ComparadorPrecio implements Comparator<Disco> {
		/*
		 * M�todo proveniente de la interfaz Comparator, el cual ser� utilizado por la
		 * colecci�n din�mica TreeSet para ordenar seg�n el atributo precio.
		 * 
		 * @param o1 Disco utilizado para ordenar la colecci�n mediante comparaci�n con
		 * el siguiente par�metro.
		 * 
		 * @param o2 Disco objeto similar al anterior, cuyo fin es ordenar a trav�s de
		 * la comparaci�n entre atributos.
		 * 
		 * @return Un entero con el resultado de la comparaci�n entre los atributos de
		 * los dos objetos.
		 */
		public int compare(Disco o1, Disco o2) {
			int comparacion = (int) Math.round(o1.getPrecio() - o2.getPrecio());
			if(comparacion == 0) {
				return o1.getTitulo().compareToIgnoreCase(o2.getTitulo());
			}else {
				return comparacion;				
			}
		}
	}
}
