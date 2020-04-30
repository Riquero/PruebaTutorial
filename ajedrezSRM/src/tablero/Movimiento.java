package tablero;

import piezas.Pieza;

public abstract class Movimiento {

	final Tablero tablero;
	final Pieza piezaMovida;
	final int coordenadaDeDestino;
	
	public Movimiento(Tablero tablero, Pieza piezaMovida, int coordenadaDeDestino) {
		this.tablero = tablero;
		this.piezaMovida = piezaMovida;
		this.coordenadaDeDestino = coordenadaDeDestino;
	}
	
	public static final class MovimientoPiezaMayor extends Movimiento{

		public MovimientoPiezaMayor(final Tablero tablero,final  Pieza piezaMovida, final int coordenadaDeDestino) {
			super(tablero, piezaMovida, coordenadaDeDestino);
		}
		
		public static final class MovimientoDeAtaque extends Movimiento{

			final Pieza piezaAtacada;
			
			public MovimientoDeAtaque(final Tablero tablero, final Pieza piezaMovida, final int coordenadaDeDestino, final Pieza piezaAtacada) {
				super(tablero, piezaMovida, coordenadaDeDestino);
				this.piezaAtacada = piezaAtacada;
			}
			
		}
	}
	
}
