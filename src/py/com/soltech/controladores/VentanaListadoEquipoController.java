package py.com.soltech.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import py.com.soltech.dao.EquipoDAO;
import py.com.soltech.modelo.entidades.Equipo;
import py.com.soltech.reportes.VentanaListadoEquipo;
import py.com.soltech.utilidad.ReporteUtil;
import py.com.soltech.vistas.modelo_tablas.ModeloTablaEquipo;

public class VentanaListadoEquipoController implements ActionListener {
    private VentanaListadoEquipo vListadoEquipo;
    private EquipoDAO dao;
    private List<Equipo> lista;

    private ModeloTablaEquipo mtEquipo;

    public VentanaListadoEquipoController(VentanaListadoEquipo vListadoEquipo) {
        this.vListadoEquipo = vListadoEquipo;

        mtEquipo = new ModeloTablaEquipo();
        this.vListadoEquipo.getTable().setModel(mtEquipo);

        setUpEvents();
    }

    private void setUpEvents() {
        vListadoEquipo.getBtnProcesar().addActionListener(this);
        vListadoEquipo.getBtnImprimir().addActionListener(this);
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
            idDesde = Integer.parseInt(vListadoEquipo.getTfCodigoDesde().getText());
        } catch (NumberFormatException ex) {
        }
        try {
            idHasta = Integer.parseInt(vListadoEquipo.getTfEstadoDesde().getText());
        } catch (NumberFormatException ex) {
        }

        String modeloDesde = vListadoEquipo.getTfModeloDesde().getText();
        String modeloHasta = vListadoEquipo.getTfModeloHasta().getText() + "zzz";

        String marcaDesde = vListadoEquipo.getTfMarcaDesde().getText();
        String marcaHasta = vListadoEquipo.getTfMarcaHasta().getText() + "zzz";

      
        String estadoDesde = vListadoEquipo.getTfCodigoDesde().getText();
        String estadoHasta = vListadoEquipo.getTfEstadoHasta().getText() + "zzz";
        
       

        dao = new EquipoDAO();
        lista = dao.recuperarPorRango(modeloDesde, modeloHasta, marcaDesde, marcaHasta, estadoDesde, estadoHasta,
                idDesde, idHasta, vListadoEquipo.getCbOrden().getSelectedIndex());
        mtEquipo.setLista(lista);
        mtEquipo.fireTableDataChanged();
    }

    private void imprimir() {
        if (lista == null) {
            return;
        }
        
        StringBuilder filtros = new StringBuilder();
        
        if (!vListadoEquipo.getTfCodigoDesde().getText().isEmpty() || !vListadoEquipo.getTfCodigoHasta().getText().isEmpty()) {
            filtros.append("id[").append(vListadoEquipo.getTfCodigoDesde().getText()).append("][").append(vListadoEquipo.getTfCodigoHasta().getText()).append("] ");
        }
        if (!vListadoEquipo.getTfModeloDesde().getText().isEmpty() || !vListadoEquipo.getTfModeloHasta().getText().isEmpty()) {
            filtros.append("Nombre:[").append(vListadoEquipo.getTfModeloDesde().getText()).append("][").append(vListadoEquipo.getTfModeloHasta().getText()).append("] ");
        }
        if (!vListadoEquipo.getTfMarcaDesde().getText().isEmpty() || !vListadoEquipo.getTfMarcaHasta().getText().isEmpty()) {
            filtros.append("Marca[").append(vListadoEquipo.getTfMarcaDesde().getText()).append("][").append(vListadoEquipo.getTfMarcaHasta().getText()).append("] ");
        }
        if (!vListadoEquipo.getTfEstadoDesde().getText().isEmpty() || !vListadoEquipo.getTfEstadoHasta().getText().isEmpty()) {
            filtros.append("Estado[").append(vListadoEquipo.getTfEstadoDesde().getText()).append("][").append(vListadoEquipo.getTfEstadoHasta().getText()).append("] ");
        }
        if (vListadoEquipo.getCbOrden().getSelectedItem() != null) {
            filtros.append("Orden:[").append(vListadoEquipo.getCbOrden().getSelectedItem().toString()).append("] ");
        }
        
        Map<String, Object> map = new HashMap<>();
        map.put("filtros", filtros.toString());
        ReporteUtil.imprimirCompilando(lista, map, "equipos");
    }
}