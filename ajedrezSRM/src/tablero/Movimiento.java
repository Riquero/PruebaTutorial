package tablero;

import piezas.Peon;
import piezas.Pieza;
import piezas.Torre;
import tablero.Tablero.Constructor;

public abstract class Movimiento {

	final Tablero tablero;
	final Pieza piezaMovida;
	final int coordenadaDeDestino;
	protected final boolean esPrimerMovimiento;

	public static final Movimiento MOVIMIENTO_NULO = new MovimientoInvalido();

	private Movimiento(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino) {
		this.tablero = tablero;
		this.piezaMovida = piezaMovida;
		this.coordenadaDeDestino = coordenadaDeDestino;
		this.esPrimerMovimiento = piezaMovida.esPrimerMovimiento();
	}

	private Movimiento(final Tablero tablero, final int coordenadaDeDestino) {
		this.tablero = tablero;
		this.coordenadaDeDestino = coordenadaDeDestino;
		this.piezaMovida = null;
		this.esPrimerMovimiento = false;
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

	public Tablero getTablero() {
		return this.tablero;
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

	public static class MovimientoAtaquePiezaMayor extends MovimientoDeAtaque {

		public MovimientoAtaquePiezaMayor(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino,
				final Pieza piezaAtacada) {
			super(tablero, piezaMovida, coordenadaDeDestino, piezaAtacada);
		}

		@Override
		public boolean equals(final Object otro) {
			return this == otro || otro instanceof MovimientoAtaquePiezaMayor && super.equals(otro);
		}

		@Override
		public String toString() {
			return piezaMovida.getTipoDePieza() + UtilidadesTablero.getPosicionEnCoordenada(this.coordenadaDeDestino);
		}
	}

	public static final class MovimientoPiezaMayor extends Movimiento {

		public MovimientoPiezaMayor(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino) {
			super(tablero, piezaMovida, coordenadaDeDestino);
		}

		@Override
		public boolean equals(final Object otro) {
			return this == otro || otro instanceof MovimientoPiezaMayor && super.equals(otro);
		}

		@Override
		public String toString() {
			return piezaMovida.getTipoDePieza().toString()
					+ UtilidadesTablero.getPosicionEnCoordenada(this.coordenadaDeDestino);
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

		@Override
		public boolean equals(final Object otro) {
			return this == otro || otro instanceof MovimientoDePeon && super.equals(otro);
		}

		@Override
		public String toString() {
			return UtilidadesTablero.getPosicionEnCoordenada(this.coordenadaDeDestino);
		}
	}

	public static final class MovimientoDeAtaqueDePeon extends MovimientoDeAtaque {

		public MovimientoDeAtaqueDePeon(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino,
				final Pieza piezaAtacada) {
			super(tablero, piezaMovida, coordenadaDeDestino, piezaAtacada);
		}

		@Override
		public boolean equals(final Object otro) {
			return this == otro || otro instanceof MovimientoDeAtaqueDePeon && super.equals(otro);
		}

		@Override
		public String toString() {
			return UtilidadesTablero.getPosicionEnCoordenada(this.piezaMovida.getPosicionPieza()).substring(0, 1) + "x"
					+ UtilidadesTablero.getPosicionEnCoordenada(this.coordenadaDeDestino);
		}
	}

	public static final class MovimientoDePeonEnPassant extends MovimientoDeAtaque {

		public MovimientoDePeonEnPassant(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino,
				final Pieza piezaAtacada) {
			super(tablero, piezaMovida, coordenadaDeDestino, piezaAtacada);
		}

		@Override
		public boolean equals(final Object otro) {
			return this == otro || otro instanceof MovimientoDePeonEnPassant && super.equals(otro);
		}

		@Override
		public Tablero ejecutar() {
			final Constructor constructor = new Constructor();
			for (final Pieza pieza : this.tablero.getJugadorActual().getPiezasActivas()) {
				if(!this.piezaMovida.equals(pieza)) {
					constructor.setPieza(pieza);
				}
			}
			for (final Pieza pieza : this.tablero.getJugadorActual().getOponente().getPiezasActivas()) {
				if(!pieza.equals(this.getPiezaAtacada())) {
					constructor.setPieza(pieza);
				}
			}
			constructor.setPieza(this.piezaMovida.moverPieza(this));
			constructor.setBandoDelJugadorQueMueve(this.tablero.getJugadorActual().getOponente().getBando());
			return constructor.construir();
		}
	}
	
	public static class PromocionDePeon extends Movimiento{
		
		final Movimiento movimientoDecorado;
		final Peon peonPromovido;
		
		public PromocionDePeon(final Movimiento movimientoDecorado) {
			super(movimientoDecorado.getTablero(), movimientoDecorado.getPiezaMovida(), movimientoDecorado.getCoordenadaDeDestino());
			this.movimientoDecorado = movimientoDecorado;
			this.peonPromovido = (Peon) movimientoDecorado.getPiezaMovida();
		}
		
		@Override
		public int hashCode() {
			return movimientoDecorado.hashCode() + (31* peonPromovido.hashCode());
		}
		
		@Override 
		public boolean equals(final Object otro) {
			return this == otro || otro instanceof PromocionDePeon && (super.equals(otro));
		}
		
		@Override
		public Tablero ejecutar() {
			
			final Tablero tableroPeonMovido = this.movimientoDecorado.ejecutar();
			final Tablero.Constructor constructor = new Constructor();
			for (final Pieza pieza : tableroPeonMovido.getJugadorActual().getPiezasActivas()) {
				if(!this.peonPromovido.equals(pieza)) {
					constructor.setPieza(pieza);
				}
			}
			for (final Pieza pieza : tableroPeonMovido.getJugadorActual().getOponente().getPiezasActivas()){
				constructor.setPieza(pieza);
			}
			constructor.setPieza(this.peonPromovido.getPiezaPromocion().moverPieza(this));
			constructor.setBandoDelJugadorQueMueve(tableroPeonMovido.getJugadorActual().getBando());
			return constructor.construir();
		}
		
		@Override
		public boolean esAtaque() {
			return this.movimientoDecorado.esAtaque();
		}
		
		@Override
		public Pieza getPiezaAtacada() {
			return this.movimientoDecorado.getPiezaAtacada();
		}
		
		@Override
		public String toString(){
			return "";
		}
	}

	public static final class SaltoDePeon extends Movimiento {

		public SaltoDePeon(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino) {
			super(tablero, piezaMovida, coordenadaDeDestino);
		}

		@Override
		public Tablero ejecutar() {
			final Constructor constructor = new Constructor();
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

		@Override
		public String toString() {
			return UtilidadesTablero.getPosicionEnCoordenada(this.coordenadaDeDestino);
		}
	}

	static abstract class Enrocar extends Movimiento {

		protected final Torre torreAEnrocar;
		protected final int coordenadaInicioTorre, coordenadaFinalTorre;

		public Enrocar(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino,
				final Torre torreAEnrocar, final int coordenadaInicioTorre, final int coordenadaFinalTorre) {
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
			// TODO mirar el primer movimiento de piezas normales
			constructor.setPieza(new Torre(this.coordenadaFinalTorre, this.torreAEnrocar.getBando()));
			constructor.setBandoDelJugadorQueMueve(this.tablero.getJugadorActual().getOponente().getBando());
			return constructor.construir();
		}
		
		@Override
		public int hashCode() {
			final int primo = 31;
			int resultado = super.hashCode();
			resultado = primo * resultado + this.torreAEnrocar.hashCode();
			resultado = primo * resultado + this.coordenadaFinalTorre;
			return resultado;
		}
		
		@Override
		public boolean equals(final Object otro) {
			if(this == otro) {
				return true;
			}
			if(!(otro instanceof Enrocar)) {
				return false;
			}
			final Enrocar otroEnroque = (Enrocar) otro;
			return super.equals(otroEnroque) && this.torreAEnrocar.equals(otroEnroque.getTorreAEnrocar());
		}
	}

	public static final class EnroqueLadoDelRey extends Enrocar {

		public EnroqueLadoDelRey(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino,
				final Torre torreAEnrocar, final int coordenadaInicioTorre, final int coordenadaFinalTorre) {
			super(tablero, piezaMovida, coordenadaDeDestino, torreAEnrocar, coordenadaInicioTorre,
					coordenadaFinalTorre);
		}
		
		@Override
		public boolean equals(final Object otro) {
			return this == otro || otro instanceof EnroqueLadoDelRey && super.equals(otro);
		}

		@Override
		public String toString() {
			return "0-0";
		}
	}

	public static final class EnroqueLadoDeReina extends Enrocar {

		public EnroqueLadoDeReina(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino,
				final Torre torreAEnrocar, final int coordenadaInicioTorre, final int coordenadaFinalTorre) {
			super(tablero, piezaMovida, coordenadaDeDestino, torreAEnrocar, coordenadaInicioTorre,
					coordenadaFinalTorre);
		}
		
		@Override
		public boolean equals(final Object otro) {
			return this == otro || otro instanceof EnroqueLadoDeReina && super.equals(otro);
		}

		@Override
		public String toString() {
			return "0-0-0";
		}
	}

	public static final class MovimientoInvalido extends Movimiento {

		public MovimientoInvalido() {
			super(null, -1);
		}

		@Override
		public Tablero ejecutar() {
			throw new RuntimeException("No se puede ejecutar un movimiento inválido!");
		}

		@Override
		public int getCoordenadaActual() {
			return -1;
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
