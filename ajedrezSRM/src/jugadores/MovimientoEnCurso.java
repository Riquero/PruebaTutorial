package jugadores;

import tablero.Movimiento;
import tablero.Tablero;

public class MovimientoEnCurso {

	private final Tablero tableroDeTransicion;
	private final Movimiento movimiento;
	private final EstadoDeMovimiento estadoDeMovimiento;

	public MovimientoEnCurso(final Tablero tableroDeTransicion, final Movimiento movimiento,
			final EstadoDeMovimiento estadoDeMovimiento) {
		this.tableroDeTransicion = tableroDeTransicion;
		this.movimiento = movimiento;
		this.estadoDeMovimiento = estadoDeMovimiento;
	}
	
	public EstadoDeMovimiento getEstadoDeMovimiento() {
		return this.estadoDeMovimiento;
	}
}
