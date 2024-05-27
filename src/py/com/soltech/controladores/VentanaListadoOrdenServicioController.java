package py.com.soltech.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import py.com.soltech.dao.ClienteDAO;
import py.com.soltech.dao.OrdenServicioDAO;
import py.com.soltech.modelo.entidades.OrdenServicio;
import py.com.soltech.reportes.VentanaListadoCliente;
import py.com.soltech.reportes.VentanaListadoOrdenServicio;
import py.com.soltech.utilidad.ReporteUtil;
import py.com.soltech.vistas.modelo_tablas.ModeloTablaBuscarOrden;
import py.com.soltech.vistas.modelo_tablas.ModeloTablaCliente;

public class VentanaListadoOrdenServicioController implements ActionListener {
 
    private VentanaListadoOrdenServicio vListadoOrdenServicio;
    private OrdenServicioDAO dao;
    private List<OrdenServicio> lista;
    private ModeloTablaBuscarOrden mtOrden;
  
    
    public VentanaListadoOrdenServicioController(VentanaListadoOrdenServicio vListadoOrdenServicio) {
        this.vListadoOrdenServicio = vListadoOrdenServicio;
        
        mtOrden = new ModeloTablaBuscarOrden();
		this.vListadoOrdenServicio.getTable().setModel(mtOrden);
        
        setUpEvents();
    }

    private void setUpEvents() {
        vListadoOrdenServicio.getBtnProcesar().addActionListener(this);  
        vListadoOrdenServicio.getBtnImprimir().addActionListener(this);
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
            idDesde = Integer.parseInt(vListadoOrdenServicio.getTfldDesde().getText());
        } catch (Exception e) {  }
        try {
            idHasta = Integer.parseInt(vListadoOrdenServicio.getTfldHasta().getText());
        } catch (Exception e) {}
    
        String fechaDesde = vListadoOrdenServicio.getTfFechaDesde().getText();
        String fechaHasta = vListadoOrdenServicio.getTfFechaHasta().getText()+ "zzz";
        
        String estadoDesde = vListadoOrdenServicio.getTfEstadoDesde().getText();
        String estadoHasta = vListadoOrdenServicio.getTfEstadoHasta().getText()+ "zzz";
        
        String colorDesde = vListadoOrdenServicio.getTfColorDesde().getText();
        String colorHasta = vListadoOrdenServicio.getTfColorHasta().getText()+ "zzz";
        
        String descripcionDesde = vListadoOrdenServicio.getTfDescripcionDesde().getText();
        String descripcionHasta = vListadoOrdenServicio.getTfDescripcionHasta().getText()+ "zzz";
        
        dao = new OrdenServicioDAO();
        lista = dao.recuperarPorRango(fechaDesde,fechaHasta,estadoDesde,estadoHasta,colorDesde,colorHasta,descripcionDesde,descripcionHasta
        		,idDesde,idHasta,vListadoOrdenServicio.getCbOrden().getSelectedIndex());
        mtOrden.setLista(lista);
        mtOrden.fireTableDataChanged();
    }

    private void imprimir() {
        if (lista == null) {
            return;
        
        }
            StringBuilder filtros = new StringBuilder();
            
            if (!vListadoOrdenServicio.getTfldDesde().getText().isEmpty() || !vListadoOrdenServicio.getTfldHasta().getText().isEmpty()) {
                filtros.append("id[").append(vListadoOrdenServicio.getTfldDesde().getText()).append("][").append(vListadoOrdenServicio.getTfldHasta().getText()).append("] ");
            }
            if (!vListadoOrdenServicio.getTfFechaDesde().getText().isEmpty() || !vListadoOrdenServicio.getTfFechaHasta().getText().isEmpty()) {
                filtros.append("Fecha[").append(vListadoOrdenServicio.getTfFechaDesde().getText()).append("][").append(vListadoOrdenServicio.getTfFechaHasta().getText()).append("] ");
            }
            if (!vListadoOrdenServicio.getTfEstadoDesde().getText().isEmpty() || !vListadoOrdenServicio.getTfEstadoHasta().getText().isEmpty()) {
                filtros.append("Estado[").append(vListadoOrdenServicio.getTfEstadoDesde().getText()).append("][").append(vListadoOrdenServicio.getTfEstadoHasta().getText()).append("] ");
            }
            if (!vListadoOrdenServicio.getTfColorDesde().getText().isEmpty() || !vListadoOrdenServicio.getTfColorHasta().getText().isEmpty()) {
                filtros.append("Color[").append(vListadoOrdenServicio.getTfColorDesde().getText()).append("][").append(vListadoOrdenServicio.getTfColorHasta().getText()).append("] ");
            }
            if (!vListadoOrdenServicio.getTfDescripcionDesde().getText().isEmpty() || !vListadoOrdenServicio.getTfDescripcionHasta().getText().isEmpty()) {
            filtros.append("Descripcion[").append(vListadoOrdenServicio.getTfDescripcionDesde().getText()).append("][").append(vListadoOrdenServicio.getTfDescripcionHasta().getText()).append("] ");
            }
         
            if (vListadoOrdenServicio.getCbOrden().getSelectedItem() != null) {
                filtros.append("Orden:[").append(vListadoOrdenServicio.getCbOrden().getSelectedItem().toString()).append("] ");
            }
            
            Map<String, Object> map = new HashMap<>();
            map.put("filtros", filtros.toString());
            ReporteUtil.imprimirCompilando(lista, map, "orderservicios");
        }
    }
