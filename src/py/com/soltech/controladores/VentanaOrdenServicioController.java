package py.com.soltech.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import py.com.soltech.dao.OrdenServicioDAO;
import py.com.soltech.modelo.entidades.Cliente;
import py.com.soltech.modelo.entidades.Equipo;
import py.com.soltech.modelo.entidades.OrdenServicio;
import py.com.soltech.utilidad.Util;
import py.com.soltech.vistas.abms.VentanaCliente;
import py.com.soltech.vistas.abms.VentanaCliente;
import py.com.soltech.vistas.abms.VentanaEquipo;
import py.com.soltech.vistas.abms.VentanaOrdenServicio;
import py.com.soltech.vistas.transaccion.Buscador;

public class VentanaOrdenServicioController {
	private final OrdenServicioDAO DAO = new OrdenServicioDAO();
	private final Util UTIL = new Util();

	private static VentanaOrdenServicio ventana;
	private static Cliente cliente;
	private static Equipo equipo;

	private OrdenServicio orden;
	private List<OrdenServicio> lista;
	private String accion;

	public VentanaOrdenServicioController(VentanaOrdenServicio ventana) {
		VentanaOrdenServicioController.ventana = ventana;
		
		soloMayusculas(ventana.getDescripcion());
		soloMayusculas(ventana.getSerial());

		moverConEnter(ventana.getFecha(), ventana.getCliente());
		moverConEnter(ventana.getCliente(), ventana.getEquipo());
		moverConEnter(ventana.getEquipo(), ventana.getColor());
		moverConEnter(ventana.getColor(), ventana.getSerial());
		moverConEnter(ventana.getSerial(), ventana.getPassword());
		moverConEnter(ventana.getPassword(), ventana.getDescripcion());
		moverConEnter(ventana.getDescripcion(), ventana.getBtnGuardar());

		ventana.addBuscarClienteListener(new BuscarClienteListener());
		ventana.addBuscarEquipoListener(new BuscarEquipoListener());
		ventana.addAgregarClienteListener(new AgregarClienteListener());
		ventana.addAgregarEquipoListener(new AgregarEquipoListener());
		ventana.addNuevoListener(new NuevoListener());
		ventana.addGuardarListener(new GuardarListener());
		ventana.addCancelarListener(new CancelarListener());
		ventana.addEliminarListener(new EliminarListener());
		ventana.addSalirListener(new SalirListener());

		estadoInicial(true);
	}

	private void estadoInicial(Boolean inicial) {
		ventana.getFecha().setEnabled(!inicial);
		ventana.getCliente().setEnabled(!inicial);
		ventana.getEquipo().setEnabled(!inicial);
		ventana.getColor().setEnabled(!inicial);
		ventana.getSerial().setEnabled(!inicial);
		ventana.getPassword().setEnabled(!inicial);
		ventana.getDescripcion().setEnabled(!inicial);
		ventana.getBtnBuscarCliente().setEnabled(!inicial);
		ventana.getBtnAgregarCliente().setEnabled(!inicial);
		ventana.getBtnBuscarEquipo().setEnabled(!inicial);
		ventana.getBtnAgregarEquipo().setEnabled(!inicial);
		ventana.getBtnNuevo().setEnabled(inicial);
		ventana.getBtnGuardar().setEnabled(!inicial);
		ventana.getBtnModificar().setVisible(false);
		ventana.getBtnEliminar().setVisible(false);

		accion = "NUEVO";
	}

	private void vaciarFormulario() {
		ventana.getId().setText("");
		ventana.getFecha().setText("");
		ventana.getCliente().setText("");
		ventana.getEquipo().setText("");
		ventana.getSerial().setText("");
		ventana.getColor().setText("");
		ventana.getPassword().setText("");
		ventana.getDescripcion().setText("");
		equipo = new Equipo();
		cliente = new Cliente();

		ventana.getFecha().setDate(LocalDate.now());
	}

	class BuscarClienteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Buscador buscador = new Buscador();
			BuscadorController controller = new BuscadorController(buscador);
			controller.bandera = 1;
			controller.setearModeloTablaCliente();
			buscador.setAlwaysOnTop(true);
			buscador.setModal(true);
			buscador.setTitle("Buscar cliente");
			buscador.setLocationRelativeTo(buscador);
			buscador.setVisible(true);
		}
	}

	class BuscarEquipoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Buscador buscador = new Buscador();
			BuscadorController controller = new BuscadorController(buscador);
			controller.bandera = 3;
			controller.setearModeloTablaEquipo();
			buscador.setAlwaysOnTop(true);
			buscador.setModal(true);
			buscador.setTitle("Buscar equipo");
			buscador.setLocationRelativeTo(buscador);
			buscador.setVisible(true);

		}
	}

	class AgregarClienteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			VentanaCliente ventana = new VentanaCliente();
			VentanaClienteController controller = new VentanaClienteController(ventana);
			controller.estadoInicial(true, 1);
			ventana.setLocationRelativeTo(null);
			ventana.setTitle("Registro de Clientes");
			ventana.setVisible(true);
		}
	}

	class AgregarEquipoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			VentanaEquipo ventana = new VentanaEquipo();
			VentanaEquipoController controller = new VentanaEquipoController(ventana);
			controller.estadoInicial(true, 2);
			ventana.setLocationRelativeTo(null);
			ventana.setTitle("Registro de Equipos");
			ventana.setVisible(true);
		}
	}

	class NuevoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			estadoInicial(false);
			vaciarFormulario();
			ventana.getCliente().requestFocus();
		}
	}

	class GuardarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (validar()) {
				LocalDate fecha = ventana.getFecha().getDate();
				String serial = ventana.getSerial().getText();
				String color = ventana.getColor().getText();
				String password = ventana.getPassword().getText();
				String descripcion = ventana.getDescripcion().getText();

				if (accion == "NUEVO") {
					orden = new OrdenServicio();
				} else {
					String id = ventana.getId().getText();
					orden.setId(Integer.parseInt(id));
				}

				orden.setFecha(fecha);
				orden.setCliente(cliente);
				orden.setEquipo(equipo);
				orden.setColor(color);
				orden.setSerial(serial);
				orden.setPassword(password);
				orden.setDescripcion(descripcion);
				orden.setEstado("EN PROCESO");

				try {
					if (accion == "NUEVO") {
						DAO.insertar(orden);
					} else {
						DAO.actualizar(orden);
					}
					estadoInicial(true);
					vaciarFormulario();
				} catch (Exception x) {
					JOptionPane.showMessageDialog(ventana, "Ocurrio un error al guardar la Orden del Servicio" + x);
					x.printStackTrace();
				}
			}
		}
	}

	class CancelarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			estadoInicial(true);
			vaciarFormulario();
		}
	}

	class EliminarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (ventana.getId().getText().isEmpty()) {
				JOptionPane.showMessageDialog(ventana,
						"Primero debe seleccionar un funcionario antes de intentar eliminar un registro");
			} else {
				int opcion = JOptionPane.showConfirmDialog(ventana, "Desea realmente eliminar este registro?",
						"ATENCION!!!", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					DAO.eliminar(orden);
				}
				estadoInicial(true);
				vaciarFormulario();
			}
		}
	}

	class SalirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ventana.dispose();
		}
	}

	private boolean validar() {
		if (ventana.getFecha().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Este campo no puede estar vacio");
			ventana.getFecha().requestFocus();
			return false;
		} else if (ventana.getCliente().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Este campo no puede estar vacio");
			ventana.getCliente().requestFocus();
			return false;
		} else if (ventana.getEquipo().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Este campo no puede estar vacio");
			ventana.getEquipo().requestFocus();
			return false;
		} else if (ventana.getColor().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Este campo no puede estar vacio");
			ventana.getColor().requestFocus();
			return false;
		} else if (ventana.getSerial().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Este campo no puede estar vacio");
			ventana.getSerial().requestFocus();
			return false;
		} else if (ventana.getPassword().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Este campo no puede estar vacio");
			ventana.getPassword().requestFocus();
			return false;
		}
		return true;

	}

	private void moverConEnter(JComponent desde, JComponent hasta) {
		desde.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					hasta.requestFocus();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}
	
	private void soloMayusculas(JComponent desde) {
        desde.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                UTIL.soloMayusculas(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

	public static void setearDesdeBuscarCliente(Cliente cli) {
		cliente = cli;
		ventana.getCliente().requestFocus();
		ventana.getCliente().setText(cli.getNombre() + " " + cli.getApellido());
	}

	public static void setearDesdeBuscarEquipo(Equipo equ) {
		equipo = equ;
		ventana.getEquipo().requestFocus();
		ventana.getEquipo().setText(equ.getMarca() + " " + equ.getModelo());
	}

}
