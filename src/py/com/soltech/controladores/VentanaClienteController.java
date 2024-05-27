package py.com.soltech.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import py.com.soltech.dao.ClienteDAO;
import py.com.soltech.modelo.entidades.Cliente;
import py.com.soltech.utilidad.Util;
import py.com.soltech.vistas.abms.VentanaCliente;
import py.com.soltech.vistas.transaccion.Buscador;

public class VentanaClienteController {
	private final ClienteDAO DAO = new ClienteDAO();
	private final Util UTIL = new Util();

	private static VentanaCliente ventana;

	public static Cliente cliente;
	private static Boolean accion;
	private static int bandera;

	public VentanaClienteController(VentanaCliente vista) {
		VentanaClienteController.ventana = vista;
		
		soloMayusculas(ventana.getNombre());
		soloMayusculas(ventana.getApellido());

		moverConEnter(ventana.getNombre(), ventana.getApellido());
		moverConEnter(ventana.getApellido(), ventana.getDocumento());
		moverConEnter(ventana.getDocumento(), ventana.getTelefono());
		moverConEnter(ventana.getTelefono(), ventana.getEmail());
		moverConEnter(ventana.getEmail(), ventana.getBtnGuardar());

		VentanaClienteController.ventana.addNuevoListener(new NuevoListener());
		VentanaClienteController.ventana.addGuardarListener(new GuardarListener());
		VentanaClienteController.ventana.addBuscarListener(new BuscarListener());
		VentanaClienteController.ventana.addCancelarListener(new CancelarListener());
		VentanaClienteController.ventana.addEliminarListener(new EliminarListener());
		VentanaClienteController.ventana.addSalirListener(new SalirListener());

	}

	public static void estadoInicial(Boolean inicial, int band) {
		bandera = band;
		ventana.getNombre().setEnabled(!inicial);
		ventana.getApellido().setEnabled(!inicial);
		ventana.getDocumento().setEnabled(!inicial);
		ventana.getEmail().setEnabled(!inicial);
		ventana.getTelefono().setEnabled(!inicial);
		ventana.getBtnNuevo().setEnabled(inicial);
		ventana.getBtnGuardar().setEnabled(!inicial);
		ventana.getBtnBuscar().setEnabled(inicial);
		ventana.getBtnEliminar().setEnabled(!inicial);
		accion = true;
	}

	private void vaciarFormulario() {
		ventana.getId().setText("");
		ventana.getNombre().setText("");
		ventana.getApellido().setText("");
		ventana.getDocumento().setText("");
		ventana.getTelefono().setText("");
		ventana.getEmail().setText("");
	}

	public static void setearDesdeBuscador(Cliente cli) {
		cliente = cli;
		ventana.getId().setText(cliente.getId() + "");
		ventana.getNombre().setText(cliente.getNombre());
		ventana.getApellido().setText(cliente.getApellido());
		ventana.getDocumento().setText(cliente.getDocumento());
		ventana.getTelefono().setText(cliente.getTelefono());
		ventana.getEmail().setText(cliente.getEmail());
		estadoInicial(false, bandera);
		accion = false;
	}

	class BuscarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Buscador buscador = new Buscador();
			BuscadorController controller = new BuscadorController(buscador);
			controller.bandera = 0;
			controller.setearModeloTablaCliente();
			buscador.setAlwaysOnTop(true);
			buscador.setModal(true);
			buscador.setTitle("Buscar cliente");
			buscador.setLocationRelativeTo(buscador);
			buscador.setVisible(true);
		}
	}

	class NuevoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			estadoInicial(false, bandera);
			vaciarFormulario();
			ventana.getNombre().requestFocus();
		}
	}

	class GuardarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (validar()) {
				String nombre = ventana.getNombre().getText();
				String apellido = ventana.getApellido().getText();
				String documento = ventana.getDocumento().getText();
				String telefono = ventana.getTelefono().getText();
				String email = ventana.getEmail().getText();

				if (accion) {
					cliente = new Cliente();
				} else {
					String id = ventana.getId().getText();
					cliente.setId(Integer.parseInt(id));
				}

				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setDocumento(documento);
				cliente.setEmail(email);
				cliente.setTelefono(telefono);

				if (accion) {
					DAO.insertar(cliente);
				} else {
					DAO.actualizar(cliente);
				}

				switch (bandera) {
				case 0:
					estadoInicial(true, bandera);
					vaciarFormulario();
					break;
				case 1:
					ventana.dispose();
					VentanaOrdenServicioController.setearDesdeBuscarCliente(cliente);
					break;
				}

			}
		}
	}

	class CancelarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			estadoInicial(true, bandera);
			vaciarFormulario();
		}
	}

	class EliminarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			int opcion = JOptionPane.showConfirmDialog(ventana, "Desea realmente eliminar este registro?",
					"ATENCION!!!", JOptionPane.OK_CANCEL_OPTION);
			if (opcion == JOptionPane.YES_OPTION) {
				DAO.eliminar(cliente);
			}
			estadoInicial(true, bandera);
			vaciarFormulario();
		}
	}

	class SalirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ventana.dispose();
		}
	}

	private boolean validar() {
		if (ventana.getNombre().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Este campo no puede estar vacío");
			ventana.getNombre().requestFocus();
			return false;
		} else if (ventana.getApellido().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Este campo no puede estar vacío");
			ventana.getApellido().requestFocus();
			return false;
		} else if (ventana.getDocumento().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Este campo no puede estar vacío");
			ventana.getDocumento().requestFocus();
			return false;
		} else if (ventana.getEmail().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Este campo no puede estar vacío");
			ventana.getEmail().requestFocus();
			return false;
		} else if (ventana.getTelefono().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Este campo no puede estar vacío");
			ventana.getTelefono().requestFocus();
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
}
