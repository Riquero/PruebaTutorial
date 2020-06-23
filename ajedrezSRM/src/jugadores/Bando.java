package jugadores;

import tablero.UtilidadesTablero;

public enum Bando {
	BLANCAS{
		@Override
		public int getDireccion() {
			return -1;		
		}
		@Override
		public int getDireccionOpuesta() {
			return 1;
		}
		@Override
		public boolean esNegra() {	
			return false;
		}
		@Override
		public boolean esBlanca() {
			return true;
		}
		@Override
		public boolean esCasillaValidaParaPromocion(int posicion) {			
			return UtilidadesTablero.OCTAVA_FILA[posicion];
		}
		@Override
		public Jugador elegirJugador(final JugadorBlancas jugadorBlancas,final JugadorNegras jugadorNegras) {			
			return jugadorBlancas;
		}
	},
	NEGRAS{
		@Override
		public int getDireccion() {
			return 1;
		}
		@Override
		public int getDireccionOpuesta() {			
			return -1;
		}
		@Override
		public boolean esNegra() {			
			return true;
		}
		@Override
		public boolean esBlanca() {			
			return false;
		}
		@Override
		public boolean esCasillaValidaParaPromocion(int posicion) {			
			return UtilidadesTablero.PRIMERA_FILA[posicion];
		}
		@Override
		public Jugador elegirJugador(final JugadorBlancas jugadorBlancas,final JugadorNegras jugadorNegras) {			
			return jugadorNegras;
		}
	};
	
	public abstract int getDireccion();
	public abstract int getDireccionOpuesta();
	public abstract boolean esNegra();
	public abstract boolean esBlanca();	
	public abstract boolean esCasillaValidaParaPromocion(int posicion);
	
	public abstract Jugador elegirJugador(final JugadorBlancas jugadorBlancas,final JugadorNegras jugadorNegras);
}
