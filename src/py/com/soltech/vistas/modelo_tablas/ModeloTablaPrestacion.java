package py.com.soltech.vistas.modelo_tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.com.soltech.modelo.entidades.Funcionario;
import py.com.soltech.modelo.entidades.PrestacionServicio;
import py.com.soltech.modelo.entidades.ServicioDetalle;

public class ModeloTablaPrestacion extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "ID","FECHA INICIO","FECHA FIN","ESTADO","FUNCIONARIO","EQUIPO", "COSTO"};
	private List<PrestacionServicio> lista = new ArrayList<>();
	
	public void setLista(List<PrestacionServicio> lista) {
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
			return lista.get(r).getId();
		case 1:
			return lista.get(r).getFechaInicio();
		case 2:
			return lista.get(r).getFechaFinalizacion();
		case 3:
			return lista.get(r).getEstado();
		case 4:
			return lista.get(r).getFuncionario().getNombre();
			
		case 5:
			return lista.get(r).getOrdenServicio().getEquipo().getModelo()+" "+lista.get(r).getOrdenServicio().getEquipo().getMarca();

		case 6:
			return lista.get(r).getCostoTotal();
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



