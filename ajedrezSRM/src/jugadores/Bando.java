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
	}; 
	public abstract int getDirection();
	public abstract boolean esNegra();
	public abstract boolean esBlanca();
}
