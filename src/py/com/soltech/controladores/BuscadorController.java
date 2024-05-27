package py.com.soltech.controladores;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import py.com.soltech.dao.ClienteDAO;
import py.com.soltech.dao.EquipoDAO;
import py.com.soltech.dao.FuncionarioDAO;
import py.com.soltech.dao.OrdenServicioDAO;
import py.com.soltech.modelo.entidades.Cliente;
import py.com.soltech.modelo.entidades.Equipo;
import py.com.soltech.modelo.entidades.Funcionario;
import py.com.soltech.modelo.entidades.OrdenServicio;
import py.com.soltech.utilidad.Util;
import py.com.soltech.vistas.modelo_tablas.ModeloTablaBuscarFuncionario;
import py.com.soltech.vistas.modelo_tablas.ModeloTablaBuscarOrden;
import py.com.soltech.vistas.modelo_tablas.ModeloTablaCliente;
import py.com.soltech.vistas.modelo_tablas.ModeloTablaEquipo;
import py.com.soltech.vistas.transaccion.Buscador;

public class BuscadorController {

	private final ModeloTablaCliente mtCliente = new ModeloTablaCliente();
	private final ModeloTablaEquipo mtEquipo = new ModeloTablaEquipo();
	private final ModeloTablaBuscarFuncionario mtFuncionario = new ModeloTablaBuscarFuncionario();
	private final ModeloTablaBuscarOrden mtOrden = new ModeloTablaBuscarOrden();
	private final Util UTIL = new Util();

	private List<Cliente> clientes;
	private List<Equipo> equipos;
	private List<Funcionario> funcionarios;
	private List<OrdenServicio> ordenes;

	private Cliente cliente;
	private Equipo equipo;
	private Funcionario funcionario;
	private OrdenServicio orden;

	private Buscador buscador;
	public int bandera;

	public BuscadorController(Buscador buscador) {
		this.buscador = buscador;
		this.buscador.addBuscarRegistroListener(new BuscarListener());

		setEventMouseClicket(buscador.getTable());
	}

	private void setEventMouseClicket(JTable table) {
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {

				if (e.getClickCount() == 2) {

					switch (bandera) {
					case 0:
						if (buscador.getTable().getSelectedRow() < 0)
							return;
						buscador.dispose();
						cliente = clientes.get(buscador.getTable().getSelectedRow());
						VentanaClienteController.setearDesdeBuscador(cliente);
						break;
					case 1:
						if (buscador.getTable().getSelectedRow() < 0)
							return;
						buscador.dispose();
						cliente = clientes.get(buscador.getTable().getSelectedRow());
						VentanaOrdenServicioController.setearDesdeBuscarCliente(cliente);
						break;
					case 2:
						if (buscador.getTable().getSelectedRow() < 0)
							return;
						buscador.dispose();
						equipo = equipos.get(buscador.getTable().getSelectedRow());
						VentanaEquipoController.setearDesdeBuscador(equipo);
						break;
					case 3:
						if (buscador.getTable().getSelectedRow() < 0)
							return;
						buscador.dispose();
						equipo = equipos.get(buscador.getTable().getSelectedRow());
						VentanaOrdenServicioController.setearDesdeBuscarEquipo(equipo);
						break;
					case 4:
						if (buscador.getTable().getSelectedRow() < 0)
							return;
						buscador.dispose();
						funcionario = funcionarios.get(buscador.getTable().getSelectedRow());
						VentanaFuncionarioController.setearDesdeBuscador(funcionario);
						break;
					case 5:
						if (buscador.getTable().getSelectedRow() < 0)
							return;
						buscador.dispose();
						funcionario = funcionarios.get(buscador.getTable().getSelectedRow());
						VentanaPrestacionController.setearDesdeBuscadorFuncionario(funcionario);
						break;
					case 6:
						if (buscador.getTable().getSelectedRow() < 0)
							return;
						buscador.dispose();
						orden = ordenes.get(buscador.getTable().getSelectedRow());
						VentanaPrestacionController.setearDesdeBuscadorOrden(orden);
						break;
					}

				}
			}
		});

	}

	public void setearModeloTablaCliente() {
		ClienteDAO dao = new ClienteDAO();
		clientes = dao.recuperarPorFiltro("");
		mtCliente.setLista(clientes);
		buscador.getTable().setModel(mtCliente);
		formatarTablaCliente();
	}

	public void setearModeloTablaEquipo() {
		EquipoDAO dao = new EquipoDAO();
		equipos = dao.recuperarPorFiltro("");
		mtEquipo.setLista(equipos);
		buscador.getTable().setModel(mtEquipo);
		formatarTablaEquipo();
	}

	public void setearModeloTablaFuncionario() {
		FuncionarioDAO dao = new FuncionarioDAO();
		funcionarios = dao.recuperarPorFiltro("");
		mtFuncionario.setLista(funcionarios);
		buscador.getTable().setModel(mtFuncionario);
		formatarTablaFuncionario();
	}

	public void setearModeloTablaOrden() {
		OrdenServicioDAO dao = new OrdenServicioDAO();
		ordenes = dao.recuperarPorEstado();
		mtOrden.setLista(ordenes);
		buscador.getTable().setModel(mtOrden);
		formatarTablaOrden();
	}

	private void recuperarRegistros() {

		switch (bandera) {
		case 0:
			recuperarClientes(buscador.getTftBuscar().getText());
			break;
		case 1:
			recuperarClientes(buscador.getTftBuscar().getText());
			break;
		case 2:
			recuperarEquipos(buscador.getTftBuscar().getText());
			break;
		case 3:
			recuperarEquipos(buscador.getTftBuscar().getText());
			break;
		case 4:
			recuperarFuncionario(buscador.getTftBuscar().getText());
			break;
		case 5:
			recuperarFuncionario(buscador.getTftBuscar().getText());
			break;
		case 6:
			recuperarOrden(buscador.getTftBuscar().getText());
			break;
		}
		

	}

	private void recuperarClientes(String filtro) {
		ClienteDAO dao = new ClienteDAO();
		clientes = dao.recuperarPorFiltro(filtro);
		if (clientes.size() == 0) {
			JOptionPane.showMessageDialog(buscador, "No se recuperaron registros");

		}
		mtCliente.setLista(clientes);
		buscador.getTable().removeAll();
		buscador.getTable().setModel(mtCliente);
	}

	private void recuperarEquipos(String filtro) {
		EquipoDAO dao = new EquipoDAO();
		equipos = dao.recuperarPorFiltro(filtro);
		if (equipos.size() == 0) {
			JOptionPane.showMessageDialog(buscador, "No se recuperaron registros");

		}
		mtEquipo.setLista(equipos);
		buscador.getTable().removeAll();
		buscador.getTable().setModel(mtEquipo);
	}

	private void recuperarFuncionario(String filtro) {
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> funcionarios = dao.recuperarPorFiltro(filtro);
		if (funcionarios.size() == 0) {
			JOptionPane.showMessageDialog(buscador, "No se recuperaron registros");

		}
		mtFuncionario.setLista(funcionarios);
		buscador.getTable().removeAll();
		buscador.getTable().setModel(mtFuncionario);
	}

	private void recuperarOrden(String filtro) {
		OrdenServicioDAO dao = new OrdenServicioDAO();
		ordenes = new ArrayList<OrdenServicio>();
		if (filtro.isEmpty()) {
			ordenes = dao.recuperarPorEstado();
		} else {
			ordenes = dao.recuperarPorOrdenGeneral(Integer.parseInt(filtro));
		}

		if (ordenes.size() == 0) {
			JOptionPane.showMessageDialog(buscador, "No se recuperaron registros");

		}
		buscador.getTable().removeAll();
		mtOrden.setLista(ordenes);
		buscador.getTable().setModel(mtOrden);
	}

	class BuscarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			recuperarRegistros();
		}
	}
	
	public void formatarTablaCliente() {
		int tableWidth = 450;
		
		System.out.println(tableWidth);
		
		TableColumn col0 = buscador.getTable().getColumnModel().getColumn(0);
		col0.setPreferredWidth((int) (tableWidth * 0.1));

		TableColumn col1 = buscador.getTable().getColumnModel().getColumn(1);
		col1.setPreferredWidth((int) (tableWidth * 0.7));

		TableColumn col2 = buscador.getTable().getColumnModel().getColumn(2);
		col2.setPreferredWidth((int) (tableWidth * 0.1));

		TableColumn col3 = buscador.getTable().getColumnModel().getColumn(3);
		col3.setPreferredWidth((int) (tableWidth * 0.1));
    }
	
	public void formatarTablaEquipo() {
		int tableWidth = 450;
		
		System.out.println(tableWidth);
		
		TableColumn col0 = buscador.getTable().getColumnModel().getColumn(0);
		col0.setPreferredWidth((int) (tableWidth * 0.7));

		TableColumn col1 = buscador.getTable().getColumnModel().getColumn(1);
		col1.setPreferredWidth((int) (tableWidth * 0.3));
    }
	
	public void formatarTablaOrden() {
		int tableWidth = 450;
		
		System.out.println(tableWidth);
		
		TableColumn col0 = buscador.getTable().getColumnModel().getColumn(0);
		col0.setPreferredWidth((int) (tableWidth * 0.15));

		TableColumn col1 = buscador.getTable().getColumnModel().getColumn(1);
		col1.setPreferredWidth((int) (tableWidth * 0.65));

		TableColumn col2 = buscador.getTable().getColumnModel().getColumn(2);
		col2.setPreferredWidth((int) (tableWidth * 0.20));
    }
	
	public void formatarTablaFuncionario() {
		int tableWidth = 450;
		
		System.out.println(tableWidth);
		
		TableColumn col0 = buscador.getTable().getColumnModel().getColumn(0);
		col0.setPreferredWidth((int) (tableWidth * 0.10));

		TableColumn col1 = buscador.getTable().getColumnModel().getColumn(1);
		col1.setPreferredWidth((int) (tableWidth * 0.60));

		TableColumn col2 = buscador.getTable().getColumnModel().getColumn(2);
		col2.setPreferredWidth((int) (tableWidth * 0.30));
    }


	private void soloNumeros(JTextField desde) {
        desde.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                UTIL.soloNumeros(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

}
