package tablero;

import piezas.Peon;
import piezas.Pieza;
import piezas.Torre;

public abstract class Movimiento {

	final Tablero tablero;
	final Pieza piezaMovida;
	final int coordenadaDeDestino;

	public static final Movimiento MOVIMIENTO_NULO = new MovimientoInvalido();

	public Movimiento(Tablero tablero, Pieza piezaMovida, int coordenadaDeDestino) {
		this.tablero = tablero;
		this.piezaMovida = piezaMovida;
		this.coordenadaDeDestino = coordenadaDeDestino;
	}

	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + this.coordenadaDeDestino;
		resultado = primo * resultado + this.piezaMovida.hashCode();
		return resultado;
	}

	@Override
	public boolean equals(final Object otro) {
		if (this == otro) {
			return true;
		}
		if (!(otro instanceof Movimiento)) {
			return false;
		}
		final Movimiento otroMovimiento = (Movimiento) otro;
		return getCoordenadaDeDestino() == otroMovimiento.getCoordenadaDeDestino()
				&& getPiezaMovida().equals(otroMovimiento.getPiezaMovida());
	}

	public int getCoordenadaDeDestino() {
		return this.coordenadaDeDestino;
	}

	public int getCoordenadaActual() {
		return this.piezaMovida.getPosicionPieza();
	}

	public boolean esAtaque() {
		return false;
	}

	public boolean esMovimientoDeEnroque() {
		return false;
	}

	public Pieza getPiezaAtacada() {
		return null;
	}

	public Pieza getPiezaMovida() {
		return this.piezaMovida;
	}

	public Tablero ejecutar() {

		final Tablero.Constructor constructor = new Tablero.Constructor();

		for (final Pieza pieza : this.tablero.getJugadorActual().getPiezasActivas()) {
			// TODO hash y equals() para las piezas
			if (!this.piezaMovida.equals(pieza)) {
				constructor.setPieza(pieza);
			}
		}

		for (final Pieza pieza : this.tablero.getJugadorActual().getOponente().getPiezasActivas()) {
			constructor.setPieza(pieza);
		}
		// Ejecuta el movimiento de la pieza a mover
		constructor.setPieza(this.piezaMovida.moverPieza(this));
		constructor.setBandoDelJugadorQueMueve(this.tablero.getJugadorActual().getOponente().getBando());
		return constructor.construir();
	}

	public static final class MovimientoPiezaMayor extends Movimiento {

		public MovimientoPiezaMayor(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino) {
			super(tablero, piezaMovida, coordenadaDeDestino);
		}
	}

	public static class MovimientoDeAtaque extends Movimiento {

		final Pieza piezaAtacada;

		public MovimientoDeAtaque(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino,
				final Pieza piezaAtacada) {
			super(tablero, piezaMovida, coordenadaDeDestino);
			this.piezaAtacada = piezaAtacada;
		}

		@Override
		public int hashCode() {
			return this.piezaAtacada.hashCode() + super.hashCode();
		}

		@Override
		public boolean equals(final Object otro) {
			if (this == otro) {
				return true;
			}
			if (!(otro instanceof Movimiento)) {
				return false;
			}
			final MovimientoDeAtaque otroMovimientoDeAtaque = (MovimientoDeAtaque) otro;
			return super.equals(otroMovimientoDeAtaque)
					&& getPiezaAtacada().equals(otroMovimientoDeAtaque.getPiezaAtacada());
		}

		@Override
		public Tablero ejecutar() {
			return null;
		}

		@Override
		public boolean esAtaque() {
			return true;
		}

		@Override
		public Pieza getPiezaAtacada() {
			return this.piezaAtacada;
		}
	}

	public static final class MovimientoDePeon extends Movimiento {

		public MovimientoDePeon(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino) {
			super(tablero, piezaMovida, coordenadaDeDestino);
		}
	}

	public static final class MovimientoDeAtaqueDePeon extends MovimientoDeAtaque {

		public MovimientoDeAtaqueDePeon(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino,
				final Pieza piezaAtacada) {
			super(tablero, piezaMovida, coordenadaDeDestino, piezaAtacada);
		}
	}

	public static final class MovimientoDePeonEnPassant extends MovimientoDeAtaque {

		public MovimientoDePeonEnPassant(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino,
				final Pieza piezaAtacada) {
			super(tablero, piezaMovida, coordenadaDeDestino, piezaAtacada);
		}
	}

	public static final class SaltoDePeon extends Movimiento {

		public SaltoDePeon(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino) {
			super(tablero, piezaMovida, coordenadaDeDestino);
		}

		@Override
		public Tablero ejecutar() {
			final Tablero.Constructor constructor = new Tablero.Constructor();
			for (final Pieza pieza : this.tablero.getJugadorActual().getPiezasActivas()) {
				if (!this.piezaMovida.equals(pieza)) {
					constructor.setPieza(pieza);
				}
			}
			for (final Pieza pieza : this.tablero.getJugadorActual().getOponente().getPiezasActivas()) {
				constructor.setPieza(pieza);
			}
			final Peon peonMovido = (Peon) this.piezaMovida.moverPieza(this);
			constructor.setPeonEnPassant(peonMovido);
			constructor.setBandoDelJugadorQueMueve(this.tablero.getJugadorActual().getOponente().getBando());
			return constructor.construir();
		}
	}

	static abstract class Enrocar extends Movimiento {

		protected final Torre torreAEnrocar;
		protected final int coordenadaInicioTorre, coordenadaFinalTorre;
		
		public Enrocar(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino,final Torre torreAEnrocar, final int coordenadaInicioTorre, final int coordenadaFinalTorre) {
			super(tablero, piezaMovida, coordenadaDeDestino);
			this.torreAEnrocar = torreAEnrocar;
			this.coordenadaInicioTorre = coordenadaInicioTorre;
			this.coordenadaFinalTorre = coordenadaFinalTorre;
		}
		
		public Torre getTorreAEnrocar() {
			return this.torreAEnrocar;
		}

		@Override
		public boolean esMovimientoDeEnroque() {
			return true;
		}

		@Override
		public Tablero ejecutar() {
			final Tablero.Constructor constructor = new Tablero.Constructor();
			for (final Pieza pieza : this.tablero.getJugadorActual().getPiezasActivas()) {
				if (!this.piezaMovida.equals(pieza) && !this.torreAEnrocar.equals(pieza)) {
					constructor.setPieza(pieza);
				}
			}
			for (final Pieza pieza : this.tablero.getJugadorActual().getOponente().getPiezasActivas()) {
				constructor.setPieza(pieza);
			}
			constructor.setPieza(this.piezaMovida.moverPieza(this));
			//TODO mirar el primer movimiento de piezas normales
			constructor.setPieza(new Torre(this.coordenadaFinalTorre, this.torreAEnrocar.getBando()));
			constructor.setBandoDelJugadorQueMueve(this.tablero.getJugadorActual().getOponente().getBando());
			return constructor.construir();
		}
	}

	public static final class EnroqueLadoDelRey extends Enrocar {

		public EnroqueLadoDelRey(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino,
				final Torre torreAEnrocar, final int coordenadaInicioTorre, final int coordenadaFinalTorre) {
			super(tablero, piezaMovida, coordenadaDeDestino, torreAEnrocar, coordenadaInicioTorre, coordenadaFinalTorre);
		}
		
		@Override
		public String toString() {
			return "0-0";
		}
	}

	public static final class EnroqueLadoDeReina extends Enrocar {

		public EnroqueLadoDeReina(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino, final Torre torreAEnrocar, final int coordenadaInicioTorre, final int coordenadaFinalTorre) {
			super(tablero, piezaMovida, coordenadaDeDestino, torreAEnrocar, coordenadaInicioTorre, coordenadaFinalTorre);
		}
		
		@Override
		public String toString() {
			return "0-0-0";
		}
	}

	public static final class MovimientoInvalido extends Movimiento {

		public MovimientoInvalido() {
			super(null, null, -1);
		}

		@Override
		public Tablero ejecutar() {
			throw new RuntimeException("No se puede ejecutar un movimiento inválido!");
		}
	}

	public static class FactoriaMovimiento {

		private FactoriaMovimiento() {
			throw new RuntimeException("No instanciable!");
		}

		public static Movimiento crearMovimiento(final Tablero tablero, final int coordenadaActual,
				final int coordenadaDeDestino) {
			for (final Movimiento movimiento : tablero.getTodosLosMovimientosLegales()) {
				if (movimiento.getCoordenadaActual() == coordenadaActual
						&& movimiento.getCoordenadaDeDestino() == coordenadaDeDestino) {
					return movimiento;
				}
			}
			return MOVIMIENTO_NULO;
		}
	}
}
