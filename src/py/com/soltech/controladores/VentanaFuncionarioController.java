package py.com.soltech.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import py.com.soltech.dao.FuncionarioDAO;
import py.com.soltech.modelo.entidades.Funcionario;
import py.com.soltech.utilidad.Util;
import py.com.soltech.vistas.abms.VentanaFuncionario;
import py.com.soltech.vistas.transaccion.Buscador;

public class VentanaFuncionarioController {
	private final FuncionarioDAO DAO = new FuncionarioDAO();
	private final Util UTIL = new Util();

	private static VentanaFuncionario ventana;

	private static Funcionario funcionario;
	private static Boolean accion;

	public VentanaFuncionarioController(VentanaFuncionario vista) {
		VentanaFuncionarioController.ventana = vista;
		
		soloMayusculas(ventana.getNombre());
		soloMayusculas(ventana.getApellido());

		moverConEnter(ventana.getNombre(), ventana.getApellido());
		moverConEnter(ventana.getApellido(), ventana.getDocumento());
		moverConEnter(ventana.getDocumento(), ventana.getTelefono());
		moverConEnter(ventana.getTelefono(), ventana.getCargo());
		moverConEnter(ventana.getCargo(), ventana.getEmail());
		moverConEnter(ventana.getEmail(), ventana.getEstado());
		moverConEnter(ventana.getEstado(), ventana.getBtnGuardar());

		VentanaFuncionarioController.ventana.addBuscarListener(new BuscarListener());
		VentanaFuncionarioController.ventana.addNuevoListener(new NuevoListener());
		VentanaFuncionarioController.ventana.addGuardarListener(new GuardarListener());
		VentanaFuncionarioController.ventana.addCancelarListener(new CancelarListener());
		VentanaFuncionarioController.ventana.addEliminarListener(new EliminarListener());
		VentanaFuncionarioController.ventana.addSalirListener(new SalirListener());
		VentanaFuncionarioController.ventana.addEstadoListeder(new EstadoListener());

		estadoInicial(true);
	}

	private static void estadoInicial(Boolean inicial) {
		ventana.getNombre().setEnabled(!inicial);
		ventana.getApellido().setEnabled(!inicial);
		ventana.getDocumento().setEnabled(!inicial);
		ventana.getEmail().setEnabled(!inicial);
		ventana.getTelefono().setEnabled(!inicial);
		ventana.getCargo().setEnabled(!inicial);
		ventana.getEstado().setEnabled(!inicial);
		ventana.getBtnBuscar().setEnabled(inicial);
		ventana.getBtnNuevo().setEnabled(inicial);
		ventana.getBtnGuardar().setEnabled(!inicial);
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
		ventana.getCargo().setText("");
		ventana.getEstado().setSelected(true);
		ventana.getEstado().setText("Activo");
	}

	public static void setearDesdeBuscador(Funcionario fun) {
		funcionario = fun;

		ventana.getId().setText(funcionario.getId() + "");
		ventana.getNombre().setText(funcionario.getNombre());
		ventana.getApellido().setText(funcionario.getApellido());
		ventana.getDocumento().setText(funcionario.getDocumento());
		ventana.getTelefono().setText(funcionario.getTelefono());
		ventana.getEmail().setText(funcionario.getEmail());
		ventana.getCargo().setText(funcionario.getCargo());
		ventana.getEstado().setSelected(funcionario.getEstado());
		if (ventana.getEstado().isSelected()) {
			ventana.getEstado().setText("Activo");
		} else {
			ventana.getEstado().setText("Inactivo");
		}
		estadoInicial(false);
		accion = false;
	}

	class BuscarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Buscador buscador = new Buscador();
			BuscadorController controller = new BuscadorController(buscador);
			controller.bandera = 4;
			controller.setearModeloTablaFuncionario();
			buscador.setAlwaysOnTop(true);
			buscador.setModal(true);
			buscador.setTitle("Buscar funcionario");
			buscador.setLocationRelativeTo(buscador);
			buscador.setVisible(true);
		}
	}

	class NuevoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			estadoInicial(false);
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
				String cargo = ventana.getCargo().getText();
				Boolean estado = ventana.getEstado().isSelected();

				if (accion) {
					funcionario = new Funcionario();
				} else {
					String id = ventana.getId().getText();
					funcionario.setId(Integer.parseInt(id));
				}

				funcionario.setNombre(nombre);
				funcionario.setApellido(apellido);
				funcionario.setDocumento(documento);
				funcionario.setEmail(email);
				funcionario.setTelefono(telefono);
				funcionario.setCargo(cargo);
				funcionario.setEstado(estado);

				try {
					if (accion) {
						DAO.insertar(funcionario);
					} else {
						DAO.actualizar(funcionario);
					}
					estadoInicial(true);
					vaciarFormulario();
				} catch (Exception x) {
					JOptionPane.showMessageDialog(ventana, "Ocurrio un error al Guardar al funcionario" + x);
					x.printStackTrace();
				}
			}
		}
	}

	class ModificarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (ventana.getId().getText().isEmpty()) {
				JOptionPane.showMessageDialog(ventana,
						"Primero debe seleccionar un funcionario antes de intentar modificar un registro");
			} else {
				estadoInicial(false);
				ventana.getNombre().requestFocus();
				accion = false;
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
					DAO.eliminar(funcionario);
				}
				estadoInicial(true);
				vaciarFormulario();
			}
		}
	}

	class EstadoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (ventana.getEstado().isSelected()) {
				ventana.getEstado().setText("Activo");
			} else {
				ventana.getEstado().setText("Inactivo");
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
		} else if (ventana.getCargo().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Este campo no puede estar vacío");
			ventana.getCargo().requestFocus();
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
