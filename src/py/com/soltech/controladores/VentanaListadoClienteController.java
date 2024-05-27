package py.com.soltech.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import py.com.soltech.dao.ClienteDAO;
import py.com.soltech.modelo.entidades.Cliente;
import py.com.soltech.reportes.VentanaListadoCliente;
import py.com.soltech.utilidad.ReporteUtil;
import py.com.soltech.vistas.modelo_tablas.ModeloTablaCliente;

public class VentanaListadoClienteController implements ActionListener {
    private VentanaListadoCliente vListadoCliente; 
    private ClienteDAO dao;
    private List<Cliente> lista;
    private ModeloTablaCliente mtCliente;
    
    public VentanaListadoClienteController(VentanaListadoCliente vListadoCliente) {
        this.vListadoCliente = vListadoCliente;
        
        mtCliente = new ModeloTablaCliente();
        this.vListadoCliente.getTable().setModel(mtCliente);
        
        setUpEvents();
    }

    private void setUpEvents() {
        vListadoCliente.getBtnProcesar().addActionListener(this);  
        vListadoCliente.getBtnImprimir().addActionListener(this);
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
            idDesde = Integer.parseInt(vListadoCliente.getTfldDesde().getText());
        } catch (Exception e) {  }
        try {
            idHasta = Integer.parseInt(vListadoCliente.getTfldHasta().getText());
        } catch (Exception e) {}
    
        String nombreDesde = vListadoCliente.getTfNombreDesde().getText();
        String nombreHasta = vListadoCliente.getTfNombreHasta().getText()+ "zzz";
        
        String apellidoDesde = vListadoCliente.getTfApellidoDesde().getText();
        String apellidoHasta = vListadoCliente.getTfApellidoHasta().getText()+ "zzz";
        
        String documentoDesde = vListadoCliente.getTfDocumentoDesde().getText();
        String documentoHasta = vListadoCliente.getTfDocumentoHasta().getText()+ "999";
        
        dao = new ClienteDAO();
        lista = dao.recuperarPorRango(nombreDesde,nombreHasta,apellidoDesde,apellidoHasta,documentoDesde,documentoHasta,
                                      idDesde,idHasta,vListadoCliente.getCbOrden().getSelectedIndex());
        mtCliente.setLista(lista);
        mtCliente.fireTableDataChanged();
    }

    private void imprimir() {
        if (lista == null) {
            return;
        
        }
            StringBuilder filtros = new StringBuilder();
            
            if (!vListadoCliente.getTfldDesde().getText().isEmpty() || !vListadoCliente.getTfldHasta().getText().isEmpty()) {
                filtros.append("id[").append(vListadoCliente.getTfldDesde().getText()).append("][").append(vListadoCliente.getTfldHasta().getText()).append("] ");
            }
            if (!vListadoCliente.getTfNombreDesde().getText().isEmpty() || !vListadoCliente.getTfNombreHasta().getText().isEmpty()) {
                filtros.append("Nombre[").append(vListadoCliente.getTfNombreDesde().getText()).append("][").append(vListadoCliente.getTfNombreHasta().getText()).append("] ");
            }
            if (!vListadoCliente.getTfApellidoDesde().getText().isEmpty() || !vListadoCliente.getTfApellidoHasta().getText().isEmpty()) {
                filtros.append("Apellido[").append(vListadoCliente.getTfApellidoDesde().getText()).append("][").append(vListadoCliente.getTfApellidoHasta().getText()).append("] ");
            }
            if (!vListadoCliente.getTfDocumentoDesde().getText().isEmpty() || !vListadoCliente.getTfDocumentoHasta().getText().isEmpty()) {
                filtros.append("Documento[").append(vListadoCliente.getTfDocumentoDesde().getText()).append("][").append(vListadoCliente.getTfDocumentoHasta().getText()).append("] ");
            }
            if (vListadoCliente.getCbOrden().getSelectedItem() != null) {
                filtros.append("Orden:[").append(vListadoCliente.getCbOrden().getSelectedItem().toString()).append("] ");
            }
            
            Map<String, Object> map = new HashMap<>();
            map.put("filtros", filtros.toString());
            ReporteUtil.imprimirCompilando(lista, map, "clientes");
        }
    }
