package tablero;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

import jugadores.Bando;
import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Pieza;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;

public class Tablero {

	private final List<Casilla> tableroDelJuego;
	private final Collection<Pieza> piezasBlancas;
	private final Collection<Pieza> piezasNegras;

	private Tablero(Constructor constructor) {
		this.tableroDelJuego = crearTableroDelJuego(constructor);
		this.piezasBlancas = calcularPiezasActivas(this.tableroDelJuego, Bando.BLANCAS);
		this.piezasNegras = calcularPiezasActivas(this.tableroDelJuego, Bando.NEGRAS);
	}

	private Collection<Pieza> calcularPiezasActivas(final List<Casilla> tablero, final Bando bando) {
		
		final List<Pieza> piezasActivas = new ArrayList<>();

		for (Casilla casilla : tableroDelJuego) {
			if (casilla.estaCasillaOcupada()) {
				final Pieza pieza = casilla.getPieza();
				if (pieza.getBando() == bando) {
					piezasActivas.add(pieza);
				}
			}
		}
		return ImmutableList.copyOf(piezasActivas);
	}

	public Casilla getCasilla(final int coordenadaDeLaPieza) {
		return tableroDelJuego.get(coordenadaDeLaPieza);
	}

	private static List<Casilla> crearTableroDelJuego(final Constructor constructor) {
		final Casilla[] casillas = new Casilla[UtilidadesTablero.NUMERO_DE_CASILLAS];
		for (int i = 0; i < UtilidadesTablero.NUMERO_DE_CASILLAS; i++) {
			casillas[i] = Casilla.crearCasilla(i, constructor.configuracionDeTablero.get(i));
		}
		return ImmutableList.copyOf(casillas);
	}

	public static Tablero crearTableroEstandar() {
		final Constructor constructor = new Constructor();
		// Negras
		constructor.setPieza(new Torre(0, Bando.NEGRAS));
		constructor.setPieza(new Caballo(1, Bando.NEGRAS));
		constructor.setPieza(new Alfil(2, Bando.NEGRAS));
		constructor.setPieza(new Reina(3, Bando.NEGRAS));
		constructor.setPieza(new Rey(4, Bando.NEGRAS));
		constructor.setPieza(new Alfil(5, Bando.NEGRAS));
		constructor.setPieza(new Caballo(6, Bando.NEGRAS));
		constructor.setPieza(new Torre(7, Bando.NEGRAS));
		constructor.setPieza(new Peon(8, Bando.NEGRAS));
		constructor.setPieza(new Peon(9, Bando.NEGRAS));
		constructor.setPieza(new Peon(10, Bando.NEGRAS));
		constructor.setPieza(new Peon(11, Bando.NEGRAS));
		constructor.setPieza(new Peon(12, Bando.NEGRAS));
		constructor.setPieza(new Peon(13, Bando.NEGRAS));
		constructor.setPieza(new Peon(14, Bando.NEGRAS));
		constructor.setPieza(new Peon(15, Bando.NEGRAS));
		// Blancas
		constructor.setPieza(new Peon(48, Bando.BLANCAS));
		constructor.setPieza(new Peon(49, Bando.BLANCAS));
		constructor.setPieza(new Peon(50, Bando.BLANCAS));
		constructor.setPieza(new Peon(51, Bando.BLANCAS));
		constructor.setPieza(new Peon(52, Bando.BLANCAS));
		constructor.setPieza(new Peon(53, Bando.BLANCAS));
		constructor.setPieza(new Peon(54, Bando.BLANCAS));
		constructor.setPieza(new Peon(55, Bando.BLANCAS));
		constructor.setPieza(new Torre(56, Bando.BLANCAS));
		constructor.setPieza(new Caballo(57, Bando.BLANCAS));
		constructor.setPieza(new Alfil(58, Bando.BLANCAS));
		constructor.setPieza(new Reina(59, Bando.BLANCAS));
		constructor.setPieza(new Rey(60, Bando.BLANCAS));
		constructor.setPieza(new Alfil(61, Bando.BLANCAS));
		constructor.setPieza(new Caballo(62, Bando.BLANCAS));
		constructor.setPieza(new Torre(63, Bando.BLANCAS));
		// Mueven blancas
		constructor.setBandoDelJugadorQueMueve(Bando.BLANCAS);

		return constructor.construir();
	}

	public static class Constructor {

		Map<Integer, Pieza> configuracionDeTablero;
		Bando bandoDelJugadorQueMueve;

		public Constructor() {

		}

		public Constructor setPieza(final Pieza pieza) {
			this.configuracionDeTablero.put(pieza.getPosicionPieza(), pieza);
			return this;
		}

		public Constructor setBandoDelJugadorQueMueve(final Bando bandoDelJugadorQueMueve) {
			this.bandoDelJugadorQueMueve = bandoDelJugadorQueMueve;
			return this;
		}

		public Tablero construir() {
			return new Tablero(this);
		}
	}
}
