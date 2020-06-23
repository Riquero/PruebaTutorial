package Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Interfaz.Tabla.HistorialDeMovimientos;
import tablero.Movimiento;
import tablero.Tablero;

public class PanelHistoricoJuego extends JPanel {

	private final ModeloDatos modelo;
	private final JScrollPane panelDeslizamiento;
	private static final Dimension DIMENSION_PANEL_HISTORICO = new Dimension(100, 400);

	PanelHistoricoJuego() {
		this.setLayout(new BorderLayout());
		this.modelo = new ModeloDatos();
		final JTable tabla = new JTable(modelo);
		tabla.setRowHeight(15);
		this.panelDeslizamiento = new JScrollPane(tabla);
		panelDeslizamiento.setColumnHeaderView(tabla.getTableHeader());
		panelDeslizamiento.setPreferredSize(DIMENSION_PANEL_HISTORICO);
		this.add(panelDeslizamiento, BorderLayout.CENTER);
		this.setVisible(true);
	}

	void rehacer(final Tablero tablero, final HistorialDeMovimientos historialMovimientos) {

		int filaActual = 0;
		this.modelo.clear();
		for (final Movimiento movimiento : historialMovimientos.getMovimientos()) {
			final String textoMovimiento = movimiento.toString();
			if (movimiento.getPiezaMovida().getBando().esBlanca()) {
				this.modelo.setValueAt(textoMovimiento, filaActual, 0);
			} else if (movimiento.getPiezaMovida().getBando().esNegra()) {
				this.modelo.setValueAt(textoMovimiento, filaActual, 1);
				filaActual++;
			}
		}

		if (historialMovimientos.getMovimientos().size() > 0) {
			final Movimiento ultimoMovimiento = historialMovimientos.getMovimientos()
					.get(historialMovimientos.tamanio() - 1);
			final String textoMovimiento = ultimoMovimiento.toString();

			if (ultimoMovimiento.getPiezaMovida().getBando().esBlanca()) {
				this.modelo.setValueAt(textoMovimiento + calcularJaques(tablero), filaActual, 0);
			} else if (ultimoMovimiento.getPiezaMovida().getBando().esNegra()) {
				this.modelo.setValueAt(textoMovimiento + calcularJaques(tablero), filaActual - 1, 1);
			}

			final JScrollBar vertical = panelDeslizamiento.getVerticalScrollBar();
			vertical.setValue(vertical.getMaximum());
		}
	}

	private String calcularJaques(final Tablero tablero) {
		if (tablero.getJugadorActual().estaEnJaqueMate()) {
			return "#";
		} else if (tablero.getJugadorActual().estaEnJaque()) {
			return "+";
		}
		return "";
	}

	private static class ModeloDatos extends DefaultTableModel {

		private final List<Fila> valores;
		private static final String[] NOMBRES = { "Blanca, Negra" };

		ModeloDatos() {
			this.valores = new ArrayList<>();
		}

		public void clear() {
			this.valores.clear();
			setRowCount(0);
		}

		@Override
		public int getRowCount() {
			if (this.valores == null) {
				return 0;
			}
			return this.valores.size();
		}

		@Override
		public int getColumnCount() {
			return NOMBRES.length;
		}

		@Override
		public Object getValueAt(final int fila, final int columna) {
			final Fila filaActual = this.valores.get(fila);
			if (columna == 0) {
				return filaActual.getMovimientoBlancas();
			} else if (columna == 1) {
				return filaActual.getMovimientoNegras();
			}
			return null;
		}

		@Override
		public void setValueAt(final Object unValor, final int fila, final int columna) {
			final Fila filaActual;
			if (this.valores.size() <= fila) {
				filaActual = new Fila();
			} else {
				filaActual = this.valores.get(fila);
			}
			if (columna == 0) {
				filaActual.setMovimientoBlancas((String) unValor);
				fireTableRowsInserted(fila,fila);
			} else if (columna == 1) {
				filaActual.setMovimientoNegras((String) unValor);
				fireTableCellUpdated(fila, columna);
			}
		}

		@Override
		public Class<?> getColumnClass(final int columna) {
			return Movimiento.class;
		}

		@Override
		public String getColumnName(final int columna) {
			return NOMBRES[columna];
		}
	}

	private static class Fila {

		private String movimientoBlancas;
		private String movimientoNegras;

		Fila() {

		}

		public String getMovimientoBlancas() {
			return movimientoBlancas;
		}

		public void setMovimientoBlancas(final String movimiento) {
			this.movimientoBlancas = movimiento;
		}

		public String getMovimientoNegras() {
			return movimientoNegras;
		}

		public void setMovimientoNegras(final String movimiento) {
			this.movimientoNegras = movimiento;
		}

	}
}
