package py.com.soltech.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import py.com.soltech.dao.FuncionarioDAO;
import py.com.soltech.modelo.entidades.Funcionario;
import py.com.soltech.reportes.VentanaListadoFuncionario;
import py.com.soltech.utilidad.ReporteUtil;
import py.com.soltech.vistas.modelo_tablas.ModeloTablaFuncionario;

public class VentanaListadoFuncionarioController implements ActionListener {
	private VentanaListadoFuncionario vListadoFuncionario;	
	private FuncionarioDAO dao;
	private List<Funcionario> lista;
	private ModeloTablaFuncionario mtFuncionario;
	
	public VentanaListadoFuncionarioController(VentanaListadoFuncionario vListadoFuncionario) {
	this.vListadoFuncionario = vListadoFuncionario;
	
	mtFuncionario = new ModeloTablaFuncionario();
	this.vListadoFuncionario.getTable().setModel(mtFuncionario);
	
	setUpEvents();
	}

	private void setUpEvents() {
		vListadoFuncionario.getBtnProcesar().addActionListener(this);  
		vListadoFuncionario.getBtnImprimir().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Imprimir":
			imprimir();
			break;
		case "Procesar":
			procesar();
			break;
			
		default:
			break;
		}
		
	}

	private void procesar() {
			int idDesde = 0;
			int idHasta = 999999999;
			try {
				idDesde = Integer.parseInt(vListadoFuncionario.getTfldDesde().getText());
			} catch (Exception e) {	}
			try {
				idHasta = Integer.parseInt(vListadoFuncionario.getTfldHasta().getText());
			} catch (Exception e) {}
	
		String nombreDesde = vListadoFuncionario.getTfNombreDesde().getText();
		String nombreHasta = vListadoFuncionario.getTfNombreHasta().getText()+ "zzz";
		
		String apellidoDesde = vListadoFuncionario.getTfApellidoDesde().getText();
		String apellidoHasta = vListadoFuncionario.getTfApellidoHasta().getText()+ "zzz";
		
		String documentoDesde = vListadoFuncionario.getTfDocumentoDesde().getText();
		String documentoHasta = vListadoFuncionario.getTfDocumentoHasta().getText()+ "999";
		
		String cargoDesde = vListadoFuncionario.getTfCargoDesde().getText();
		String cargoHasta = vListadoFuncionario.getTfCargoHasta().getText()+ "zzz";
	 
		dao = new FuncionarioDAO();
        lista = dao.recuperarPorRango(nombreDesde, nombreHasta, apellidoDesde, apellidoHasta, documentoDesde, documentoHasta, cargoDesde, cargoHasta,
                idDesde, idHasta, vListadoFuncionario.getCbOrden().getSelectedIndex());
        mtFuncionario.setLista(lista);
        mtFuncionario.fireTableDataChanged();
	  
	}

	private void imprimir() {
	    if (lista == null) {
	        return;
	    }
	    
	    StringBuilder filtros = new StringBuilder();
	    
	    if (!vListadoFuncionario.getTfldDesde().getText().isEmpty() || !vListadoFuncionario.getTfldHasta().getText().isEmpty()) {
	        filtros.append("id[").append(vListadoFuncionario.getTfldDesde().getText()).append("][").append(vListadoFuncionario.getTfldHasta().getText()).append("] ");
	    }
	    if (!vListadoFuncionario.getTfNombreDesde().getText().isEmpty() || !vListadoFuncionario.getTfNombreHasta().getText().isEmpty()) {
	        filtros.append("Nombre[").append(vListadoFuncionario.getTfNombreDesde().getText()).append("][").append(vListadoFuncionario.getTfNombreHasta().getText()).append("] ");
	    }
	    if (!vListadoFuncionario.getTfApellidoDesde().getText().isEmpty() || !vListadoFuncionario.getTfApellidoHasta().getText().isEmpty()) {
	        filtros.append("Apellido[").append(vListadoFuncionario.getTfApellidoDesde().getText()).append("][").append(vListadoFuncionario.getTfApellidoHasta().getText()).append("] ");
	    }
	    if (!vListadoFuncionario.getTfDocumentoDesde().getText().isEmpty() || !vListadoFuncionario.getTfDocumentoHasta().getText().isEmpty()) {
	        filtros.append("Documento[").append(vListadoFuncionario.getTfDocumentoDesde().getText()).append("][").append(vListadoFuncionario.getTfDocumentoHasta().getText()).append("] ");
	    }
	    if (!vListadoFuncionario.getTfCargoDesde().getText().isEmpty() || !vListadoFuncionario.getTfCargoHasta().getText().isEmpty()) {
	        filtros.append("Cargo[").append(vListadoFuncionario.getTfCargoDesde().getText()).append("][").append(vListadoFuncionario.getTfCargoHasta().getText()).append("] ");
	    }
	    if (vListadoFuncionario.getCbOrden().getSelectedItem() != null) {
	        filtros.append("Orden:[").append(vListadoFuncionario.getCbOrden().getSelectedItem().toString()).append("] ");
	    }
	    
	    Map<String, Object> map = new HashMap<>();
	    map.put("filtros", filtros.toString());
	    ReporteUtil.imprimirCompilando(lista, map, "funcionarios");
	}
}