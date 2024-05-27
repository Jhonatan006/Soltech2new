package py.com.soltech.vistas.modelo_tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.com.soltech.modelo.entidades.Funcionario;

public class ModeloTablaFuncionario extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "NOMBRE", "APELLIDO", "DOCUMENTO", "EMAIL", "TELEFONO", "CARGO"};
	private List<Funcionario> lista = new ArrayList<>();
	
	public void setLista(List<Funcionario> lista) {
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
			return lista.get(r).getNombre();
		case 1:
			return lista.get(r).getApellido();
		case 2:
			return lista.get(r).getDocumento();
		case 3:
			return lista.get(r).getEmail();
		case 4:
			return lista.get(r).getTelefono();
		case 5: 
			return lista.get(r).getCargo();
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



