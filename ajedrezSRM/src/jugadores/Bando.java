package jugadores;

public enum Bando {
	BLANCAS{
		public int getDirection() {
			return -1;		
		}
		public boolean esNegra() {	
			return false;
		}
		public boolean esBlanca() {
			return true;
		}
		@Override
		public Jugador elegirJugador(final JugadorBlancas jugadorBlancas,final JugadorNegras jugadorNegras) {			
			return jugadorBlancas;
		}
	},
	NEGRAS{
		public int getDirection() {
			return 1;
		}
		public boolean esNegra() {
			
			return true;
		}
		public boolean esBlanca() {
			
			return false;
		}
		@Override
		public Jugador elegirJugador(final JugadorBlancas jugadorBlancas,final JugadorNegras jugadorNegras) {			
			return jugadorNegras;
		}
	}; 
	public abstract int getDirection();
	public abstract boolean esNegra();
	public abstract boolean esBlanca();
	
	public abstract Jugador elegirJugador(final JugadorBlancas jugadorBlancas,final JugadorNegras jugadorNegras);
}
