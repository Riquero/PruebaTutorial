/** 1ºDAM IES Donoso Cortés Programación
 * @author Santiago Riquero Martínez 
 * @version 1.0
 * @date 22/04/2020
 * Clase principal del proyecto Discográfico.
 */

package discografica;

/**
 * Clase que creará objetos de tipo Disco, ya sean Mp3, Cd, o vinilos, y definirá la forma 
 * en la que interactúan mediante métodos estáticos.Incluye los siguientes atributos:
 * La relación entre esta clase y las demás es de asociación.
 * <ul>
 * 
 * 	<li> TECLADO: objeto de tipo BufferedReader, utilizado para leer por teclado.
 * 
 * 	<li> coleccionDeDiscos: colección de tipo TreeSet, utilizado para almacenar los objetos
 * 	de tipo Disco creados por el usuario en tiempo de ejecución.
 * 
 * 	<li> coleccionDeMP3: colección de tipo TreeSet, utilizado para almacenar los objetos 
 * 	preexistentes (para la carga automática) de tipo MP3.
 * 
 * 	<li> coleccionDeCD: colección de tipo TreeSet, utilizado para almacenar los objetos 
 * 	preexistentes (para la carga automática) de tipo Cd.
 * 
 * 	<li> coleccionDeVinilos: colección de tipo TreeSet, utilizado para almacenar los objetos 
 * 	preexistentes (para la carga automática) de tipo Vinilo.
 * 
 * 	<li> primeraAdicion: booleano utilizado para la carga automática. Nos piden que haya una carga
 * 	automática de dos objetos de cada tipo (Cd, Mp3 y vinilo). Dado que se han almacenado 4 objetos
 * 	de cada tipo, se ha establecido el número de cargas en 2. Este booleano permitirá realizar la 2ª carga
 * 	una vez realizada la primera.
 * 
 * </ul>
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;
import java.util.TreeSet;

import discografica.Calidad.Calidades;
import discografica.EstiloMusical.genero;
import discografica.Formato.formato;

public class Principal {

	// Atributos de clase
	static BufferedReader TECLADO = new BufferedReader(new InputStreamReader(System.in));
	static TreeSet<Disco> coleccionDeDiscos = new TreeSet<Disco>();
	static TreeSet<Mp3> coleccionDeMP3 = new TreeSet<Mp3>();
	static TreeSet<Cd> coleccionDeCD = new TreeSet<Cd>();
	static TreeSet<Vinilo> coleccionDeVinilos = new TreeSet<Vinilo>();
	static boolean primeraAdicion = false;

	// Métodos
	/*
	 * Método que imprime por pantalla la bienvenida al usuario.
	 */
	public static void Bienvenida() {
		System.out.println("Bienvenido a Discomanía, una compañía de Santiago Riquero Martínez.");
	}

	/*
	 * Método utilizado para cargar las colecciones auxiliares denominadas
	 * coleccionDeMP3, coleccionDeCD y coleccionDeVinilos a través de los métodos
	 * cargarMP3, cargarCD y cargarVinilo, respectivamente.
	 */
	public static void cargarColecciones() {
		cargarMP3();
		cargarCD();
		cargarVinilo();
	}

	/*
	 * Método que crea 4 objetos de tipo Mp3 y los almacena en coleccionDeMP3. Estos
	 * objetos serán utilizados para la carga automática.
	 */
	public static void cargarMP3() {
		Mp3 Single = new Mp3("Fito y los fitipaldis", "Por la boca vive el pez", "Discos Radiactivos Organizados",
				genero.Rock, new GregorianCalendar(2006, 9, 11), 3.99, Calidades.MEDIA);
		coleccionDeMP3.add(Single);
		Mp3 discoDos = new Mp3("Manolo Escobar", "30 Aniversario: Manolo Escobar", "Sony Music", genero.Pop,
				new GregorianCalendar(1988, 4, 25), 6.99, Calidades.MEDIA);
		coleccionDeMP3.add(discoDos);
		Mp3 discoTres = new Mp3("Isabel Pantoja", "Enamórate", "Altafonte", genero.Pop,
				new GregorianCalendar(2020, 2, 14), 6.99, Calidades.BAJA);
		coleccionDeMP3.add(discoTres);
		Mp3 discoCuatro = new Mp3("Rosalía", "El mal querer", "Sony Local", genero.Pop,
				new GregorianCalendar(2018, 11, 30), 9.99, Calidades.ALTA);
		coleccionDeMP3.add(discoCuatro);
	}

	/*
	 * Método que crea 4 objetos de tipo CD y los almacena en coleccionDeCD. Estos
	 * objetos serán utilizados para la carga automática.
	 */
	public static void cargarCD() {
		Cd primerCD = new Cd("Mago De Oz", "Gaia 1", "Warner Music Spain", genero.Rock,
				new GregorianCalendar(2003, 12, 6), 7.09);
		Cd segundoCD = new Cd("Gorillaz", "Gorillaz", "Parlophone", genero.Internacional,
				new GregorianCalendar(2001, 03, 26), 0.89);
		Cd tercerCD = new Cd("Post Malone", "Stoney", "Republic Records", genero.Hip_Hop,
				new GregorianCalendar(2016, 12, 9), 10.99);
		Cd cuartoCD = new Cd("Tamino", "Amir", "Communion Music", genero.Internacional,
				new GregorianCalendar(2018, 10, 19), 15.99);
		coleccionDeCD.add(primerCD);
		coleccionDeCD.add(segundoCD);
		coleccionDeCD.add(tercerCD);
		coleccionDeCD.add(cuartoCD);
	}

	/*
	 * Método que crea 4 objetos de tipo Vinilo y los almacena en
	 * coleccionDeVinilos. Estos objetos serán utilizados para la carga automática.
	 */
	public static void cargarVinilo() {
		Vinilo primerVinilo = new Vinilo("The Beatles", "Abbey Road Anniversary", "Capitol", genero.Rock,
				new GregorianCalendar(2019, 9, 27), 33.05);
		Vinilo segundoVinilo = new Vinilo("Michael Jackson", "Thriller", "Legacy", genero.RnB,
				new GregorianCalendar(2016, 5, 6), 19.31);
		Vinilo tercerVinilo = new Vinilo("Pink Floyd", "The Dark Side of the Moon", "Parlophone", genero.Electronica,
				new GregorianCalendar(1973, 9, 27), 65.00);
		Vinilo cuartoVinilo = new Vinilo("Bob Marley", "Legend", "Tuff Gong", genero.Internacional,
				new GregorianCalendar(2009, 6, 7), 18.95);
		coleccionDeVinilos.add(primerVinilo);
		coleccionDeVinilos.add(segundoVinilo);
		coleccionDeVinilos.add(tercerVinilo);
		coleccionDeVinilos.add(cuartoVinilo);
	}

	/*
	 * Método utilizado para mostrar el menú principal, a través del método
	 * mostrarMenuPrincipal, y para devolver por cabecera un entero con la selección
	 * del usuario.
	 * 
	 * @return Entero con la selección del usuario sobre el menú principal.
	 */
	public static int seleccionParaMenuPrincipal() {
		int seleccion = 0;
		while (seleccion <= 0 || seleccion > 4) {
			mostrarMenuPrincipal();
			System.out.println("Seleccione una opción: ");
			try {
				seleccion = Integer.parseInt(TECLADO.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Por favor, introduzca solamente números enteros.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return seleccion;
	}

	/*
	 * Método utilizado para mostrar por consola el menú principal de la
	 * discográfica.
	 */
	public static void mostrarMenuPrincipal() {
		System.out.println("Pulse 1 para insertar disco.");
		System.out.println("Pulse 2 para mostrar discos.");
		System.out.println("Pulse 3 para buscar un disco.");
		System.out.println("Pulse 4 para salir.");
	}

	/*
	 * Método agregarDisco que se ejecuta al seleccionar insertar disco en el menú
	 * principal. Primero mostrará al usuario las distintas opciones que este menú
	 * ofrece, el usuario seleccionará qué opción desea mediante la introducción por
	 * teclado de un número del 1 al 3 (uno para cada opción) a través del método
	 * seleccionParaSubmenuDeInsercion, y se ejecutará la opción seleccionada.
	 * 
	 * Este método ofrece un menú en el cual el usuario puede seleccionar: 1º
	 * agregar disco de forma interactiva. 2º agregar disco de forma automática (dos
	 * discos de cada tipo). 3º salir de este menú.
	 */
	public static void agregarDisco() {
		boolean continuar = true;
		while (continuar) {
			int selector = seleccionParaSubmenuDeInsercion();
			switch (selector) {
			case 1:
				agregarDiscoInteractivamente();
				break;
			case 2:
				agregarDiscosAutomaticamente();
				continuar = false;
				break;
			case 3:
				continuar = false;
				break;
			}
		}
	}

	/*
	 * Método utilizado para mostrar el submenú de inserción, a través del método
	 * mostrarSubmenuInsercion, y para devolver por cabecera un entero con la
	 * selección del usuario.
	 * 
	 * @return Entero con la selección del usuario sobre el menú de insertar Disco.
	 */
	public static int seleccionParaSubmenuDeInsercion() {
		int seleccion = 0;
		while (seleccion <= 0 || seleccion > 3) {
			mostrarSubmenuInsercion();
			System.out.println("Seleccione una opción: ");
			try {
				seleccion = Integer.parseInt(TECLADO.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Por favor, introduzca solamente números enteros.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return seleccion;
	}

	/*
	 * Método utilizado para mostrar por consola el menú de insertar disco.
	 */
	public static void mostrarSubmenuInsercion() {
		System.out.println("Pulse 1 para insertar disco interactivamente.");
		System.out.println("Pulse 2 para cargar 2 discos de cada tipo automáticamente.");
		System.out.println("Pulse 3 para salir del menú de inserción.");
	}

	/*
	 * Método agregarDiscoInteractivamente que se ejecuta al seleccionar insertar
	 * disco interactivamente en el menú agregarDisco. Primero mostrará al usuario
	 * las distintas opciones que este menú ofrece, el usuario seleccionará qué
	 * opción desea mediante la introducción por teclado de un número del 1 al 4
	 * (uno para cada opción) a través del método seleccionDeFormato, y se ejecutará
	 * la opción seleccionada.
	 * 
	 * Este método ofrece un menú en el cual el usuario puede seleccionar: 1º
	 * agregar MP3. 2º agregar CD. 3º agregar Vinilo. 4º salir de este menú.
	 */
	public static void agregarDiscoInteractivamente() {
		boolean continuar = true;
		while (continuar) {
			int selector = seleccionDeFormato();
			switch (selector) {
			case 1:
				agregarMP3();
				break;
			case 2:
				agregarCD();
				break;
			case 3:
				agregarVinilo();
				break;
			case 4:
				continuar = false;
				break;
			}
		}
	}

	/*
	 * Método utilizado para mostrar los distintos formatos de Disco, a través del
	 * método opcionesFormatoDeDisco, y para devolver por cabecera un entero con la
	 * selección del usuario.
	 * 
	 * @return Entero con la selección del usuario sobre el menú de Formatos de
	 * disco.
	 */
	public static int seleccionDeFormato() {
		int seleccion = 0;
		while (seleccion <= 0 || seleccion > 4) {
			opcionesFormatosDeDisco();
			System.out.println("Seleccione una opción: ");
			try {
				seleccion = Integer.parseInt(TECLADO.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Por favor, introduzca solamente números enteros.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return seleccion;
	}

	/*
	 * Método utilizado para mostrar por consola el menú de formatos de disco,
	 * ubicado en el menú de insertar disco, y en el método
	 * agregarDiscoInteractivamente.
	 */
	public static void opcionesFormatosDeDisco() {
		System.out.println("Pulse 1 para agregar un MP3.");
		System.out.println("Pulse 2 para agregar un CD.");
		System.out.println("Pulse 3 para agregar un vinilo.");
		System.out.println("Pulse 4 para salir del menú de carga interactiva.");
	}

	/*
	 * Método utilizado para agregar discos de tipo Mp3 de forma interactiva. 
	 * 1º Crea un objeto de tipo MP3 llamado nuevoDisco. 
	 * 2º Utiliza el método pedirDatos para darle forma. Este método preguntará 
	 * al usuario por los datos y se lo asignará al objeto nuevoDisco. 
	 * 3º Asigna la calidad del objeto nuevoDisco a través de un String que devuelve 
	 * el método pedirCalidad. Para más información, desplácese al comentario de ese método. 
	 * 4º Añade el nuevoDisco a la coleccion de discos 
	 * 5º Imprime por pantalla que se ha realizado la operación con éxito.
	 */
	public static void agregarMP3() {
		Mp3 nuevoDisco = new Mp3();
		pedirDatos(nuevoDisco);
		nuevoDisco.setCalidad(pedirCalidad());
		coleccionDeDiscos.add((Mp3) nuevoDisco);
		System.out.println("MP3 añadido correctamente.");
	}

	/*
	 * Método utilizado para asignar cada atributo de un disco que viene por
	 * argumento.
	 * 
	 * @param ejemplar Disco proveniente del método anterior, sin forma (creado a
	 * partir del constructor por defecto).
	 */
	public static void pedirDatos(Disco ejemplar) {
		ejemplar.setNombreArtista(pedirArtista());
		ejemplar.setTitulo(pedirTitulo());
		ejemplar.setSelloDiscografico(pedirDiscografica());
		ejemplar.setEstiloMusica(pedirGenero());
		ejemplar.setFecha(pedirFecha());
		ejemplar.setPrecio(pedirPrecio());
	}

	/*
	 * Método utilizado para agregar discos de tipo CD de forma interactiva. 
	 * 1º Crea un objeto de tipo CD llamado nuevoCd. 
	 * 2º Utiliza el método pedirDatos para darle forma. Este método preguntará al 
	 * usuario por los datos y se lo asignará al objeto nuevoCd. 
	 * 3º Añade el nuevoCd a la coleccion de discos. 
	 * 4º Imprime por pantalla que se ha realizado la operación con éxito.
	 */
	public static void agregarCD() {
		Cd nuevoCD = new Cd();
		pedirDatos(nuevoCD);
		coleccionDeDiscos.add((Cd) nuevoCD);
		System.out.println("CD añadido correctamente.");
	}

	/*
	 * Método utilizado para agregar discos de tipo Vinilo de forma interactiva. 
	 * 1º Crea un objeto de tipo Vinilo llamado nuevoVinilo. 
	 * 2º Utiliza el método pedirDatos para darle forma. Este método preguntará al 
	 * usuario por los datos y se lo asignará al objeto nuevoVinilo. 
	 * 3º Añade el nuevoVinilo a la coleccion de discos 
	 * 4º Imprime por pantalla que se ha realizado la operación con éxito.
	 */
	public static void agregarVinilo() {
		Vinilo nuevoVinilo = new Vinilo();
		pedirDatos(nuevoVinilo);
		coleccionDeDiscos.add((Vinilo) nuevoVinilo);
		System.out.println("Vinilo añadido correctamente.");
	}

	/*
	 * Método que agrega automáticamente 2 discos de cada tipo. 
	 * 1º Comprueba que no se han añadido ya todos los ejemplos disponibles para esta carga. 
	 * 2º Comprueba si se han añadido ya una primera vez los discos. Diferencia entre
	 * 1º carga automática y 2º carga automática. En caso de no haber añadido una
	 * primera vez los discos, se ejecutará el método cargar discos1, el cual
	 * ejecutará la carga de 2 discos preestablecidos de cada tipo. Después
	 * imprimirá por pantalla que se ha realizado la carga con éxito. Finalmente,
	 * cambiará el estado del booleano primeraAdicion a verdadero, para que la
	 * próxima vez que se ejecute este método en tiempo de ejecución, se realice la
	 * 2º carga automática (son discos distintos). 
	 * 3º Se ejecutará el método cargarDiscos2 solamente cuando se haya ejecutado la 
	 * primera carga de discos (método cargarDiscos1). Sucesivos intentos de carga 
	 * automática ejecutarán el primer paso.
	 */
	public static void agregarDiscosAutomaticamente() {
		if (coleccionDeDiscos.containsAll(coleccionDeMP3) && coleccionDeDiscos.containsAll(coleccionDeCD)
				&& coleccionDeDiscos.containsAll(coleccionDeVinilos)) {
			System.out.println("Ya se han añadido todas las colecciones disponibles para la carga automática.");
		} else {
			if (primeraAdicion == false) {
				cargarDiscos1();
				System.out.println("La 1ª carga automática se ha realizado adecuadamente.");
				primeraAdicion = true;
			} else {
				cargarDiscos2();
				System.out.println("La 2ª carga automática (y la última) se ha realizado adecuadamente.");
			}
		}
	}

	/*
	 * Método que añadirá a la TreeSet principal el 1º y 2º Disco de cada colección
	 * auxiliar.
	 */
	public static void cargarDiscos1() {
		coleccionDeDiscos.add(coleccionDeMP3.first());
		coleccionDeDiscos.add(coleccionDeCD.first());
		coleccionDeDiscos.add(coleccionDeVinilos.first());
		coleccionDeDiscos.add(coleccionDeMP3.higher(coleccionDeMP3.first()));
		coleccionDeDiscos.add(coleccionDeCD.higher(coleccionDeCD.first()));
		coleccionDeDiscos.add(coleccionDeVinilos.higher(coleccionDeVinilos.first()));
	}

	/*
	 * Método que añadirá a la TreeSet principal el 3º y 4º Disco de cada colección
	 * auxiliar.
	 */
	public static void cargarDiscos2() {
		coleccionDeDiscos.add(coleccionDeMP3.lower(coleccionDeMP3.last()));
		coleccionDeDiscos.add(coleccionDeCD.lower(coleccionDeCD.last()));
		coleccionDeDiscos.add(coleccionDeVinilos.lower(coleccionDeVinilos.last()));
		coleccionDeDiscos.add(coleccionDeMP3.last());
		coleccionDeDiscos.add(coleccionDeCD.last());
		coleccionDeDiscos.add(coleccionDeVinilos.last());
	}

	/*
	 * Método que pedirá al usuario que introduzca el nombre del artista. El nombre
	 * se almacenará en la variable local nombre y se devolverá por cabecera.
	 * 
	 * @return String con el nombre del artista.
	 */
	public static String pedirArtista() {
		String nombre = "";
		while (nombre == "") {
			try {
				System.out.println("Inserte el nombre del artista.");
				nombre = TECLADO.readLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return nombre;
	}

	/*
	 * Método que pedirá al usuario que introduzca el título del disco. El título se
	 * almacenará en la variable local nombre y se devolverá por cabecera.
	 * 
	 * @return String con el título del disco.
	 */
	public static String pedirTitulo() {
		String nombre = "";
		while (nombre == "") {
			try {
				System.out.println("Inserte el título del disco.");
				nombre = TECLADO.readLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return nombre;
	}

	/*
	 * Método que pedirá al usuario que introduzca el sello discográfico del disco.
	 * El sello se almacenará en la variable local nombre y se devolverá por
	 * cabecera.
	 * 
	 * @return String con el sello discográfico del disco.
	 */
	public static String pedirDiscografica() {
		String nombre = "";
		while (nombre == "") {
			try {
				System.out.println("Inserte el nombre del sello discográfico.");
				nombre = TECLADO.readLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return nombre;
	}

	/*
	 * Método que pedirá al usuario que introduzca un número que simbolizará el
	 * género musical. El usuario seleccionará qué genero es y devolverá por
	 * cabecera el enum correspondiente.
	 * 
	 * @return enumerado genero con el género del disco.
	 */
	public static genero pedirGenero() {
		int opciones = 0;
		while (opciones <= 0 || opciones > 10) {
			mostrarEstilosMusicales();
			try {
				opciones = Integer.parseInt(TECLADO.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Introduzca solamente un número del 1 al 3 (inclusive).");
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		switch (opciones) {
		case 1:
			return genero.Clasica;
		case 2:
			return genero.Dubstep;
		case 3:
			return genero.Electronica;
		case 4:
			return genero.Hip_Hop;
		case 5:
			return genero.Internacional;
		case 6:
			return genero.Jazz;
		case 7:
			return genero.Pop;
		case 8:
			return genero.RnB;
		case 9:
			return genero.Rock;
		case 10:
			return genero.Techno;
		default:
			return genero.Pop;
		}
	}

	/**
	 * Método utilizado para mostrar todos los enums genero de la interfaz
	 * EstiloMusical con el objetivo de que el usuario seleccione cual desea
	 * utilizar en el método pedirGenero.
	 */
	public static void mostrarEstilosMusicales() {
		genero[] misEnums = genero.class.getEnumConstants();
		for (int i = 0; i < misEnums.length; i++) {
			System.out.println("Pulse " + (i + 1) + " si el estilo es " + misEnums[i]);
		}
	}

	/*
	 * Método que pedirá al usuario que introduzca la fecha de publicación del
	 * disco. La fecha se almacenará en las variables local anio, mes y dia y
	 * devolverá por cabecera un objeto de tipo GregorianCalendar con estas
	 * variables como argumento.
	 * 
	 * @return GregorianCalendar con la fecha de publicación del disco.
	 */
	public static GregorianCalendar pedirFecha() {
		int anio = 0, mes = 0, dia = 0;

		while (anio >= 2021) {
			try {
				System.out.println("Inserte el año de publicación del disco: ");
				anio = Integer.parseInt(TECLADO.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Introduzca solamente un número hasta el 2020 (inclusive).");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		while (mes <= 0 || mes >= 13) {
			try {
				System.out.println("Inserte el mes de publicación del disco: ");
				mes = Integer.parseInt(TECLADO.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Introduzca solamente un número del 1 al 12 (inclusive).");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		while (dia <= 0 || dia >= 32) {
			try {
				System.out.println("Inserte el día de publicación del disco: ");
				dia = Integer.parseInt(TECLADO.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Introduzca solamente un número del 1 al 31 (inclusive).");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return new GregorianCalendar(anio, mes, dia);
	}

	/*
	 * Método que pedirá al usuario que seleccione la calidad del Mp3. La selección
	 * del usuario se utilizará para devolver un enumerado con la calidad del Mp3.
	 * 
	 * @return enumerado Calidades con la calidad del disco.
	 */
	public static Calidades pedirCalidad() {
		int opciones = 0;
		while (opciones <= 0 || opciones > 3) {
			System.out.println("Pulse 1 si la calidad es baja (128 kbps).");
			System.out.println("Pulse 2 si la calidad es media (250 kbps).");
			System.out.println("Pulse 3 si la calidad es alta (320 kbps).");
			try {
				opciones = Integer.parseInt(TECLADO.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Introduzca solamente un número del 1 al 3 (inclusive).");
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		if (opciones == 1) {
			return Calidades.BAJA;
		} else {
			if (opciones == 2) {
				return Calidades.MEDIA;
			} else {
				return Calidades.ALTA;
			}
		}
	}

	/*
	 * Método que pedirá al usuario que introduzca el precio del disco. El precio se
	 * almacenará en la variable local precio y se devolverá por cabecera.
	 * 
	 * @return Double con el precio del disco.
	 */
	public static Double pedirPrecio() {
		Double precio = 0.0;
		while (precio <= 0.0) {
			try {
				System.out.println("Inserte el precio del disco.");
				precio = Double.parseDouble(TECLADO.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Por favor, introduzca solamente números.");
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		return precio;
	}

	/*
	 * Método utilizado para mostrar el menú principal del método mostrar, mediante
	 * el cual el usuario podrá mostrar los discos almacenados en coleccionDeDiscos
	 * a través de varios criterios de ordenación. 
	 * 1º Comprueba si la coleccionDeDiscos está vacío (no tiene sentido intentar 
	 * mostrar algo vacío). 
	 * 2º Mostrará las distintas opciones de selección al usuario por consola y
	 * devolverá su selección a través del método seleccionParaSubmenuDeMostrar. 
	 * 3º Se ejecutará la opción deseada por el usuario.
	 */
	public static void mostrarDiscos() {
		if (coleccionDeDiscos.isEmpty()) {
			System.out.println("La colección está vacía. Pruebe a añadir algunos discos antes.");
		} else {
			boolean continuar = true;
			while (continuar) {
				int selector = seleccionParaSubmenuDeMostrar();
				switch (selector) {
				case 1:
					mostrarOrdenadoPorDefecto();
					break;
				case 2:
					mostrarOrdenadosPorArtista();
					break;
				case 3:
					mostrarOrdenadosPorTitulo();
					break;
				case 4:
					mostrarOrdenadosPorSelloDiscografico();
					break;
				case 5:
					mostrarOrdenadosPorGenero();
					break;
				case 6:
					mostrarOrdenadosPorFechaPublicacion();
					break;
				case 7:
					mostrarOrdenadosPorFormato();
					break;
				case 8:
					mostrarOrdenadosPorPrecio();
					break;
				case 9:
					continuar = false;
					break;
				}
			}
		}
	}

	/*
	 * Método utilizado para mostrar el menú de Mostrar, a través del método
	 * mostrarSubmenuMostrar, y para devolver por cabecera un entero con la
	 * selección del usuario.
	 * 
	 * @return Entero con la selección del usuario sobre el menú de Mostrar.
	 */
	public static int seleccionParaSubmenuDeMostrar() {
		int seleccion = 0;
		while (seleccion <= 0 || seleccion > 9) {
			mostrarSubmenuMostrar();
			System.out.println("Seleccione una opción: ");
			try {
				seleccion = Integer.parseInt(TECLADO.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Por favor, introduzca solamente números enteros.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return seleccion;
	}

	/*
	 * Método utilizado para mostrar por consola el menú de mostrar de forma
	 * ordenada.
	 */
	public static void mostrarSubmenuMostrar() {
		System.out.println("Pulse 1 para mostrar los discos ordenados por defecto.");
		System.out.println("Pulse 2 para mostrar los discos ordenados por artista.");
		System.out.println("Pulse 3 para mostrar los discos ordenados por titulo.");
		System.out.println("Pulse 4 para mostrar los discos ordenados por sello discográfico.");
		System.out.println("Pulse 5 para mostrar los discos ordenados por género musical.");
		System.out.println("Pulse 6 para mostrar los discos ordenados por fecha de publicación.");
		System.out.println("Pulse 7 para mostrar los discos ordenados por formato.");
		System.out.println("Pulse 8 para mostrar los discos ordenados por precio.");
		System.out.println("Pulse 9 para salir del submenú mostrar.");
	}

	/*
	 * Método creado para mostrar los objetos almacenados en la coleccionDeDiscos
	 * por defecto.
	 */
	public static void mostrarOrdenadoPorDefecto() {
		for (Disco disco : coleccionDeCD) {
			System.out.println(disco);
		}
	}

	/*
	 * Método creado para mostrar los objetos almacenados en la coleccionDeDiscos
	 * según los criterios de ordenación definidos en la clase ComparadorArtista,
	 * almacenados en la interfaz Comparadores. Ordenará los objetos según el nombre
	 * del artista, de forma ascendente alfabéticamente.
	 */
	public static void mostrarOrdenadosPorArtista() {
		TreeSet<Disco> coleccionOrdenada = new TreeSet<Disco>(new Comparadores.ComparadorArtista());
		coleccionOrdenada.addAll(coleccionDeDiscos);
		for (Object disco : coleccionOrdenada) {
			System.out.println(disco);
		}
	}

	/*
	 * Método utilizado para mostrar los objetos almacenados en la coleccionDeDiscos
	 * según los criterios de ordenación definidos en la clase ComparadorTitulo,
	 * almacenados en la interfaz Comparadores. Ordenará los objetos según el
	 * título, de forma ascendente alfabéticamente.
	 */
	public static void mostrarOrdenadosPorTitulo() {
		TreeSet<Disco> coleccionOrdenada = new TreeSet<Disco>(new Comparadores.ComparadorTitulo());
		coleccionOrdenada.addAll(coleccionDeDiscos);
		for (Disco disco : coleccionOrdenada) {
			System.out.println(disco);
		}
	}

	/*
	 * Método utilizado para mostrar los objetos almacenados en la coleccionDeDiscos
	 * según los criterios de ordenación definidos en la clase
	 * ComparadorSelloDiscografico, almacenados en la interfaz Comparadores.
	 * Ordenará los objetos según el sello discográfico, de forma ascendente
	 * alfabéticamente.
	 */
	public static void mostrarOrdenadosPorSelloDiscografico() {
		TreeSet<Disco> coleccionOrdenada = new TreeSet<Disco>(new Comparadores.ComparadorSelloDiscografico());
		coleccionOrdenada.addAll(coleccionDeDiscos);
		for (Disco disco : coleccionOrdenada) {
			System.out.println(disco);
		}
	}

	/*
	 * Método utilizado para mostrar los objetos almacenados en la coleccionDeDiscos
	 * según los criterios de ordenación definidos en la clase ComparadorGenero,
	 * almacenados en la interfaz Comparadores. Ordenará los objetos según el
	 * género/estilo musical, de forma ascendente alfabéticamente.
	 */
	public static void mostrarOrdenadosPorGenero() {
		TreeSet<Disco> coleccionOrdenada = new TreeSet<Disco>(new Comparadores.ComparadorGenero());
		coleccionOrdenada.addAll(coleccionDeDiscos);
		for (Disco disco : coleccionOrdenada) {
			System.out.println(disco);
		}
	}

	/*
	 * Método utilizado para mostrar los objetos almacenados en la coleccionDeDiscos
	 * según los criterios de ordenación definidos en la clase
	 * ComparadorFechaPublicacion, almacenados en la interfaz Comparadores. Ordenará
	 * los objetos según la fecha de publicación, de forma ascendente en antiguedad.
	 */
	public static void mostrarOrdenadosPorFechaPublicacion() {
		TreeSet<Disco> coleccionOrdenada = new TreeSet<Disco>(new Comparadores.ComparadorFechaPublicacion());
		coleccionOrdenada.addAll(coleccionDeDiscos);
		for (Disco disco : coleccionOrdenada) {
			System.out.println(disco);
		}
	}

	/*
	 * Método utilizado para mostrar los objetos almacenados en la coleccionDeDiscos
	 * según los criterios de ordenación definidos en la clase ComparadorFormatos,
	 * almacenados en la interfaz Comparadores. Ordenará los objetos según el
	 * formato, de forma ascendente alfabéticamente.
	 */
	public static void mostrarOrdenadosPorFormato() {
		TreeSet<Disco> coleccionOrdenada = new TreeSet<Disco>(new Comparadores.ComparadorFormato());
		coleccionOrdenada.addAll(coleccionDeDiscos);
		for (Disco disco : coleccionOrdenada) {
			System.out.println(disco);
		}
	}

	/*
	 * Método utilizado para mostrar los objetos almacenados en la coleccionDeDiscos
	 * según los criterios de ordenación definidos en la clase ComparadorPrecios,
	 * almacenados en la interfaz Comparadores. Ordenará los objetos según los
	 * precios, de forma ascendente.
	 */
	public static void mostrarOrdenadosPorPrecio() {
		TreeSet<Disco> coleccionOrdenada = new TreeSet<Disco>(new Comparadores.ComparadorPrecio());
		coleccionOrdenada.addAll(coleccionDeDiscos);
		for (Disco disco : coleccionOrdenada) {
			System.out.println(disco);
		}
	}

	/*
	 * Método utilizado para seleccionar la función deseada por el usuario,
	 * posterior a la comprobación del contenido de la colección, y de mostrar al
	 * usuario las opciones que se le presentan en el método
	 * seleccionParaSubmenuDeBuscar.
	 */
	public static void buscarDisco() {
		if (coleccionDeDiscos.isEmpty()) {
			System.out.println("La colección está vacía. Pruebe a añadir algunos discos antes.");
		} else {
			boolean continuar = true;
			while (continuar) {
				int selector = seleccionParaSubmenuDeBuscar();
				switch (selector) {
				case 1:
					buscarPorArtista();
					break;
				case 2:
					buscarPorTitulo();
					break;
				case 3:
					buscarPorSelloDiscografico();
					break;
				case 4:
					buscarPorGenero();
					break;
				case 5:
					buscarPorFormato();
					break;
				case 6:
					continuar = false;
					break;
				}
			}
		}
	}

	/*
	 * Método que recogerá la opción elegida por el usuario después de mostrar por
	 * consola las opciones (mostrarSubmenuBusqueda) y que se almacenará en la
	 * variable seleccion. Esta se devolverá por cabecera cuando sea una opción
	 * válida.
	 */
	public static int seleccionParaSubmenuDeBuscar() {
		int seleccion = 0;
		while (seleccion <= 0 || seleccion > 6) {
			mostrarSubmenuBusqueda();
			System.out.println("Seleccione una opción: ");
			try {
				seleccion = Integer.parseInt(TECLADO.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Por favor, introduzca solamente números enteros.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return seleccion;
	}

	/*
	 * Método que mostrará por consola el submenú de búsqueda del programa.
	 */
	public static void mostrarSubmenuBusqueda() {
		System.out.println("Pulse 1 para buscar por artista.");
		System.out.println("Pulse 2 para buscar por titulo.");
		System.out.println("Pulse 3 para buscar por sello discográfico.");
		System.out.println("Pulse 4 para buscar por género musical.");
		System.out.println("Pulse 5 para buscar por formato.");
		System.out.println("Pulse 6 para salir del submenú mostrar.");
	}

	/*
	 * Método que pedirá al usuario que escriba por teclado el nombre del artista
	 * que desea buscar, para posteriormente asignarlo a la variable nombreArtista.
	 * Esta variable se utilizará en el método buscarArtista para buscar dicho
	 * artista. En caso de encontrarlo, se mostrará en qué posición se encuentra a
	 * través de la variable resultadoDeBusqueda. En caso de no encontrarlo, el
	 * método buscarArtista devolverá un 0, por lo que el programa mostrará por
	 * pantalla que no se encuentra disponible dicho artista actualmente.
	 * 
	 * ATENCION: el resultado de la búsqueda será la posición tomando como primera
	 * posición el 1 y no el cero, como es propio en los vectores.
	 */
	public static void buscarPorArtista() {
		String nombreArtista = pedirArtista();
		int resultadoDeBusqueda = buscarArtista(nombreArtista);
		if (resultadoDeBusqueda == 0) {
			System.out.println("No tenemos disponible ese disco actualmente.");
		} else {
			System.out.println("El disco " + nombreArtista + " se encuentra en la posición " + resultadoDeBusqueda);
		}
	}

	/**
	 * Método que comprobará el nombre del artista de cada disco almacenado en la
	 * coleccionDeDiscos y devolverá la posición de aquél que coincida con la
	 * variable que recibe por argumento denomi- nada nombre. En caso de no
	 * encontrar dicho artista, se devolverá el valor 0 para ser tratado en el
	 * método que lo llama.
	 * 
	 * @param nombre: String que almacena el nombre del artista a buscar.
	 * 
	 * @return posicion entero que o bien es cero (no encontrado) o bien es la
	 *         posición actual del disco con el artista correspondiente.
	 */
	public static int buscarArtista(String nombre) {
		int posicion = 1;
		for (Disco disco : coleccionDeDiscos) {
			if (disco.getNombreArtista().equalsIgnoreCase(nombre)) {
				return posicion;
			}
			posicion++;
		}
		return 0;
	}

	/*
	 * Método similar al de buscar por artista, pero con el atributo titulo.
	 * ATENCION: el resultado de la búsqueda será la posición tomando como primera
	 * posición el 1 y no el cero, como es propio en los vectores.
	 */
	public static void buscarPorTitulo() {
		String titulo = pedirTitulo();
		int resultadoDeBusqueda = buscarTitulo(titulo);
		if (resultadoDeBusqueda == 0) {
			System.out.println("No tenemos disponible ese disco actualmente.");
		} else {
			System.out.println("El disco se encuentra en la posición " + resultadoDeBusqueda);
		}
	}

	/**
	 * Método similar al de buscarArtista pero con el atributo titulo.
	 * 
	 * @param titulo: String que almacena el título del disco a buscar.
	 * 
	 * @return posicion entero que o bien es cero (no encontrado) o bien es la
	 *         posición actual del disco con el título correspondiente.
	 */
	public static int buscarTitulo(String titulo) {
		int posicion = 1;
		for (Disco disco : coleccionDeDiscos) {
			if (disco.getTitulo().equalsIgnoreCase(titulo)) {
				return posicion;
			}
			posicion++;
		}
		return 0;
	}

	/*
	 * Método similar al de buscar por artista, pero con el atributo
	 * selloDiscografico. ATENCION: el resultado de la búsqueda será la posición
	 * tomando como primera posición el 1 y no el cero, como es propio en los
	 * vectores.
	 */
	public static void buscarPorSelloDiscografico() {
		String sello = pedirDiscografica();
		int resultadoDeBusqueda = buscarSello(sello);
		if (resultadoDeBusqueda == 0) {
			System.out.println("No tenemos disponible ese disco actualmente.");
		} else {
			System.out.println("El disco se encuentra en la posición " + resultadoDeBusqueda);
		}
	}

	/*
	 * Método similar al de buscarArtista pero con el atributo selloDiscografico.
	 * 
	 * @param sello: String que almacena el sello discográfico del disco.
	 * 
	 * @return posicion, entero que o bien es cero (no encontrado) o bien es la
	 * posición actual del disco con el sello correspondiente.
	 */
	public static int buscarSello(String sello) {
		int posicion = 1;
		for (Disco disco : coleccionDeDiscos) {
			if (disco.getSelloDiscografico().equalsIgnoreCase(sello)) {
				return posicion;
			}else {
				posicion++;				
			}
		}
		return 0;
	}

	/*
	 * Método similar al de buscar por artista, pero con el atributo genero.
	 * ATENCION: el resultado de la búsqueda será la posición tomando como primera
	 * posición el 1 y no el cero, como es propio en los vectores.
	 */
	public static void buscarPorGenero() {
		genero genero = pedirGenero();
		int resultadoDeBusqueda = buscarGenero(genero);
		if (resultadoDeBusqueda == 0) {
			System.out.println("No tenemos disponible ese disco actualmente.");
		} else {
			System.out.println("El disco se encuentra en la posición " + resultadoDeBusqueda);
		}
	}

	/**
	 * Método similar al de buscarArtista pero con el atributo EstiloMusical
	 * (representado por genero).
	 * 
	 * @param estilo: enum de la interfaz EstiloMusical que representa el genero del
	 *                disco.
	 * @return entero que o bien es cero (no encontrado) o bien es la posición
	 *         actual del disco con el sello correspondiente.
	 */
	public static int buscarGenero(genero estilo) {
		int posicion = 1;
		for (Disco disco : coleccionDeDiscos) {
			if ((int) disco.getEstiloMusica().compareTo(estilo) == 0) {
				return posicion;
			}else {
				posicion++;				
			}
		}
		return 0;
	}

	/*
	 * Método similar al de buscar por artista, pero con el atributo formato.
	 * ATENCION: el resultado de la búsqueda será la posición tomando como primera
	 * posición el 1 y no el cero, como es propio en los vectores.
	 */
	public static void buscarPorFormato() {
		formato formato = pedirFormato();
		int resultadoDeBusqueda = buscarFormato(formato);
		if (resultadoDeBusqueda == 0) {
			System.out.println("No tenemos disponible ese disco actualmente.");
		} else {
			System.out.println("El disco se encuentra en la posición " + resultadoDeBusqueda);
		}
	}

	/**
	 * Método que pide al usuario que seleccione qué formato desea, y lo devolverá
	 * por cabecera.
	 * 
	 * @return formato enum con el formato que el usuario haya deseado.
	 */
	public static formato pedirFormato() {
		int opciones = 0;
		while (opciones <= 0 || opciones > 3) {
			System.out.println("Pulse 1 para seleccionar el formato CD.");
			System.out.println("Pulse 2 para seleccionar el formato MP3.");
			System.out.println("Pulse 3 para seleccionar el formato Vinilo.");
			try {
				opciones = Integer.parseInt(TECLADO.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Introduzca solamente un número del 1 al 3 (inclusive).");
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		switch (opciones) {
		case 1:
			return formato.CD;
		case 2:
			return formato.MP3;
		case 3:
			return formato.Vinilo;
		default:
			return formato.MP3;
		}
	}

	/**
	 * Método similar al de buscarArtista pero con el atributo formato.
	 * 
	 * @param formato: enum de la interfaz Formato que representa el formato del
	 *                 disco.
	 * @return entero que o bien es cero (no encontrado) o bien es la posición
	 *         actual del disco con el sello correspondiente.
	 */
	public static int buscarFormato(formato formato) {
		int posicion = 1;
		for (Disco disco : coleccionDeDiscos) {
			if ((int) disco.getFormato().compareTo(formato) == 0) {
				return posicion;
			}else {
				posicion++;				
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		boolean continuar = true;
		Bienvenida();
		cargarColecciones();
		while (continuar) {
			int selector = seleccionParaMenuPrincipal();
			switch (selector) {
			case 1:
				agregarDisco();
				break;
			case 2:
				mostrarDiscos();
				break;
			case 3:
				buscarDisco();
				break;
			case 4:
				continuar = false;
				System.out.println("¡Hasta luego!");
				break;
			}
		}

	}

}
