package py.com.soltech.vistas.modelo_tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.com.soltech.modelo.entidades.Equipo;

public class ModeloTablaEquipo extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "MARCA", "MODELO","ESTADO"};
	private List<Equipo> lista = new ArrayList<>();
	
	public void setLista(List<Equipo> lista) {
		this.lista = lista;
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int c) {
		return columnas[c];
	}
	
	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public Object getValueAt(int r, int c) {
	    switch (c) {
	        case 0:
	            return lista.get(r).getMarca();  
	        case 1:
	            return lista.get(r).getModelo(); 
	        case 2:
	            return lista.get(r).getEstado();
	        default:
	            return null;
	    }
	}

	
	
	@Override
	public Class<?> getColumnClass(int c) {
		if(lista.size()>0){
			return getValueAt(0, c).getClass();
		}
		return super.getColumnClass(c);
	}
	
}



