package py.com.soltech.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import py.com.soltech.dao.PrestacionServicioDAO;
import py.com.soltech.modelo.entidades.PrestacionServicio;
import py.com.soltech.reportes.VentanaListadoPrestacionServicio;
import py.com.soltech.utilidad.ReporteUtil;
import py.com.soltech.vistas.modelo_tablas.ModeloTablaPrestacion;
import py.com.soltech.vistas.modelo_tablas.ModeloTablaPrestacionDetalle;

public class VentanaListadoPresentacionServicioController implements ActionListener {
	private VentanaListadoPrestacionServicio vListadoPrestacionServicio;
	private PrestacionServicioDAO dao;
	private List<PrestacionServicio> lista;
	private ModeloTablaPrestacion mtPrestacion;
	
	
	public VentanaListadoPresentacionServicioController(VentanaListadoPrestacionServicio vListadoPrestacionServicio) {
	this.vListadoPrestacionServicio = vListadoPrestacionServicio;
	
	mtPrestacion = new ModeloTablaPrestacion();
	this.vListadoPrestacionServicio.getTable().setModel(mtPrestacion);
	
	setUpEvents();
	}

	private void setUpEvents() {
		vListadoPrestacionServicio.getBtnProcesar().addActionListener(this);  
		vListadoPrestacionServicio.getBtnImprimir().addActionListener(this);
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
		
		 String idDesde = vListadoPrestacionServicio.getTfIdDesde().getText();
		 String idHasta = vListadoPrestacionServicio.getTfIdHasta().getText() + "zzz";
		   
	    String descripcionDesde = vListadoPrestacionServicio.getTfFechaInicioDesde().getText();
	    String descripcionHasta = vListadoPrestacionServicio.getTfFechainicioHasta().getText() + "zzz";
	    
	    String estadoDesde = vListadoPrestacionServicio.getTfEstadoDesde().getText();
	    String estadoHasta = vListadoPrestacionServicio.getTfEstadoHasta().getText() + "zzz";
	    
	    String funcionarioDesde = vListadoPrestacionServicio.getTfFuncionarioDesde().getText();
	    String funcionarioHasta = vListadoPrestacionServicio.getTfFuncionarioHasta().getText() + "999";
	    
	    String marcaDesde = vListadoPrestacionServicio.getTfEquipoDesde().getText();
	    String marcaHasta = vListadoPrestacionServicio.getTfEstadoDesde().getText() + "zzz";
	    
	    
	    String costoDesde = vListadoPrestacionServicio.getTfCostoDesde().getText();
	    String costoHasta = vListadoPrestacionServicio.getTfCostoHasta().getText() + "999";
	    
	    dao = new PrestacionServicioDAO();
	    lista = dao.recuperarPorRango(descripcionDesde, descripcionHasta, estadoDesde, estadoHasta, funcionarioDesde, funcionarioHasta, costoDesde, costoHasta, vListadoPrestacionServicio.getCbOrden().getSelectedIndex());
	  mtPrestacion.setLista(lista);
	    mtPrestacion.fireTableDataChanged();
	}


	private void imprimir() {
	    if (lista == null) {
	        return;
	    }
	    
	    StringBuilder filtros = new StringBuilder();
	    
	    if (!vListadoPrestacionServicio.getTfIdDesde().getText().isEmpty() || !vListadoPrestacionServicio.getTfIdHasta().getText().isEmpty()) {
	        filtros.append("Id[").append(vListadoPrestacionServicio.getTfIdDesde().getText()).append("][").append(vListadoPrestacionServicio.getTfIdHasta().getText()).append("] ");
	    }
	    
	    if (!vListadoPrestacionServicio.getTfFechaInicioDesde().getText().isEmpty() || !vListadoPrestacionServicio.getTfFechainicioHasta().getText().isEmpty()) {
	        filtros.append("Descripcion[").append(vListadoPrestacionServicio.getTfFechaInicioDesde().getText()).append("][").append(vListadoPrestacionServicio.getTfFechainicioHasta().getText()).append("] ");
	    }
	    if (!vListadoPrestacionServicio.getTfEstadoDesde().getText().isEmpty() || !vListadoPrestacionServicio.getTfEstadoHasta().getText().isEmpty()) {
	        filtros.append("Estado[").append(vListadoPrestacionServicio.getTfEstadoDesde().getText()).append("][").append(vListadoPrestacionServicio.getTfCostoHasta().getText()).append("] ");
	    }
	    if (!vListadoPrestacionServicio.getTfFuncionarioDesde().getText().isEmpty() || !vListadoPrestacionServicio.getTfFuncionarioHasta().getText().isEmpty()) {
	        filtros.append("Funcionario[").append(vListadoPrestacionServicio.getTfFuncionarioDesde().getText()).append("][").append(vListadoPrestacionServicio.getTfFuncionarioHasta().getText()).append("] ");
	        
	        
	        if (!vListadoPrestacionServicio.getTfEquipoDesde().getText().isEmpty() || !vListadoPrestacionServicio.getTfEquipoHasta().getText().isEmpty()) {
		        filtros.append("Equipo[").append(vListadoPrestacionServicio.getTfEquipoDesde().getText()).append("][").append(vListadoPrestacionServicio.getTfEquipoHasta()	.getText()).append("] ");
		    }
	    }
	    if (!vListadoPrestacionServicio.getTfCostoDesde().getText().isEmpty() || !vListadoPrestacionServicio.getTfCostoHasta().getText().isEmpty()) {
	        filtros.append("Costo[").append(vListadoPrestacionServicio.getTfCostoDesde().getText()).append("][").append(vListadoPrestacionServicio.getTfCostoHasta().getText()).append("] ");
	    }
	    if (vListadoPrestacionServicio.getCbOrden().getSelectedItem() != null) {
	        filtros.append("Orden:[").append(vListadoPrestacionServicio.getCbOrden().getSelectedItem().toString()).append("] ");
	    }
	    
	    Map<String, Object> map = new HashMap<>();
	    map.put("filtros", filtros.toString());
	    ReporteUtil.imprimirCompilando(lista, map, "prestacionservicios");
	}
}