package jugadores;

public enum EstadoDeMovimiento {
	TERMINADO {
		@Override
		public
		boolean haTerminado() {
			return true;
		}
	}, 
	MOVIMIENTO_ILEGAL {
		@Override
		public
		boolean haTerminado() {
			
			return false;
		}
	}, DEJA_AL_JUGADOR_EN_JAQUE {
		@Override
		public
		boolean haTerminado() {
			
			return false;
		}
	};
	
	public abstract boolean haTerminado();
}
