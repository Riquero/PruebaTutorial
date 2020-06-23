package tablero;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import jugadores.Bando;
import jugadores.Jugador;
import jugadores.JugadorBlancas;
import jugadores.JugadorNegras;
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

	private final JugadorBlancas jugadorBlancas;
	private final JugadorNegras jugadorNegras;
	private final Jugador jugadorActual;

	private final Peon peonEnPassant;

	private Tablero(final Constructor constructor) {
		this.tableroDelJuego = crearTableroDelJuego(constructor);
		this.piezasBlancas = calcularPiezasActivas(this.tableroDelJuego, Bando.BLANCAS);
		this.piezasNegras = calcularPiezasActivas(this.tableroDelJuego, Bando.NEGRAS);
		this.peonEnPassant = constructor.peonEnPassant;
		final Collection<Movimiento> movimientosEstandarLegalesBlancas = calcularMovimientosLegales(this.piezasBlancas);
		final Collection<Movimiento> movimientosEstandarLegalesNegras = calcularMovimientosLegales(this.piezasNegras);

		this.jugadorBlancas = new JugadorBlancas(this, movimientosEstandarLegalesBlancas,
				movimientosEstandarLegalesNegras);
		this.jugadorNegras = new JugadorNegras(this, movimientosEstandarLegalesNegras,
				movimientosEstandarLegalesBlancas);
		this.jugadorActual = constructor.bandoDelJugadorQueMueve.elegirJugador(this.jugadorBlancas, this.jugadorNegras);
	}

	private Collection<Movimiento> calcularMovimientosLegales(Collection<Pieza> piezas) {

		final List<Movimiento> movimientosLegales = new ArrayList<>();
		for (final Pieza pieza : piezas) {
			movimientosLegales.addAll(pieza.calculaMovimientosLegales(this));
		}

		return ImmutableList.copyOf(movimientosLegales);
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

	public Collection<Pieza> getPiezasNegras() {
		return this.piezasNegras;
	}

	public Collection<Pieza> getPiezasBlancas() {
		return this.piezasBlancas;
	}

	public Casilla getCasilla(final int coordenadaDeLaPieza) {
		return tableroDelJuego.get(coordenadaDeLaPieza);
	}

	public Jugador getJugadorActual() {
		return this.jugadorActual;
	}
	
	public Peon getPeonEnPassant() {
		return peonEnPassant;
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

	public Jugador jugadorBlancas() {
		return this.jugadorBlancas;
	}

	public Jugador jugadorNegras() {
		return this.jugadorNegras;
	}

	@Override
	public String toString() {
		final StringBuilder constructor = new StringBuilder();
		for (int i = 0; i < UtilidadesTablero.NUMERO_DE_CASILLAS; i++) {
			final String textoDeCasilla = this.tableroDelJuego.get(i).toString();
			constructor.append(String.format("%3s", textoDeCasilla));
			if ((i + 1) % UtilidadesTablero.NUMERO_DE_CASILLAS_POR_FILA == 0) {
				constructor.append(System.getProperty("line.separator"));
			}
		}
		return constructor.toString();
	}

	public Iterable<Movimiento> getTodosLosMovimientosLegales() {
		return Iterables.unmodifiableIterable(Iterables.concat(this.jugadorBlancas.getMovimientosLegales(),
				this.jugadorNegras.getMovimientosLegales()));
	}

	public static class Constructor {

		Map<Integer, Pieza> configuracionDeTablero;
		Bando bandoDelJugadorQueMueve;
		Peon peonEnPassant;

		public Constructor() {
			this.configuracionDeTablero = new HashMap<>();
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

		public void setPeonEnPassant(Peon peonEnPassant) {
			this.peonEnPassant = peonEnPassant;
		}
	}

}
