package jugadores;

public enum EstadoDeMovimiento {
	TERMINADO {
		@Override
		boolean haTerminado() {
			return true;
		}
	}, 
	MOVIMIENTO_ILEGAL {
		@Override
		boolean haTerminado() {
			
			return false;
		}
	}, DEJA_AL_JUGADOR_EN_JAQUE {
		@Override
		boolean haTerminado() {
			
			return false;
		}
	};
	
	abstract boolean haTerminado();
}
