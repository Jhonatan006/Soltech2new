package py.com.soltech.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import py.com.soltech.dao.EquipoDAO;
import py.com.soltech.modelo.entidades.Equipo;
import py.com.soltech.utilidad.Util;
import py.com.soltech.vistas.abms.VentanaEquipo;
import py.com.soltech.vistas.transaccion.Buscador;

public class VentanaEquipoController {
	private final EquipoDAO DAO = new EquipoDAO();
	private final Util UTIL = new Util();

	private static VentanaEquipo ventana;

	private static Equipo equipo;
	private static Boolean accion;
	private static int bandera;

	public VentanaEquipoController(VentanaEquipo vista) {
		VentanaEquipoController.ventana = vista;

		soloMayusculas(ventana.getModelo());
		soloMayusculas(ventana.getMarca());

		moverConEnter(ventana.getMarca(), ventana.getModelo());
		moverConEnter(ventana.getModelo(), ventana.getEstado());
		moverConEnter(ventana.getEstado(), ventana.getBtnGuardar());

		VentanaEquipoController.ventana.addBuscarListener(new BuscarListener());
		VentanaEquipoController.ventana.addNuevoListener(new NuevoListener());
		VentanaEquipoController.ventana.addGuardarListener(new GuardarListener());
		VentanaEquipoController.ventana.addCancelarListener(new CancelarListener());
		VentanaEquipoController.ventana.addEliminarListener(new EliminarListener());
		VentanaEquipoController.ventana.addSalirListener(new SalirListener());
		VentanaEquipoController.ventana.addEstadoListener(new EstadoListener());		
	}

	public static void estadoInicial(Boolean inicial, int band) {
		bandera = band;
		ventana.getModelo().setEnabled(!inicial);
		ventana.getMarca().setEnabled(!inicial);
		ventana.getBtnBuscar().setEnabled(inicial);
		ventana.getBtnNuevo().setEnabled(inicial);
		ventana.getBtnGuardar().setEnabled(!inicial);
		ventana.getBtnEliminar().setEnabled(!inicial);
		ventana.getEstado().setEnabled(!inicial);
		accion = true;
	}

	private void vaciarFormulario() {
		ventana.getId().setText("");
		ventana.getModelo().setText("");
		ventana.getMarca().setText("");
		ventana.getEstado().setText("");
		ventana.getEstado().setSelected(true);
		ventana.getEstado().setText("Activo");
	}

	public static void setearDesdeBuscador(Equipo equi) {
		equipo = equi;
		ventana.getId().setText(equipo.getId() + "");
		ventana.getModelo().setText(equipo.getModelo());
		ventana.getMarca().setText(equipo.getMarca());
		ventana.getEstado().setSelected(equipo.getEstado());
		estadoInicial(false, bandera);
		accion = false;
	}

	class BuscarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Buscador buscador = new Buscador();
			BuscadorController controller = new BuscadorController(buscador);
			controller.bandera = 2;
			controller.setearModeloTablaEquipo();
			buscador.setAlwaysOnTop(true);
			buscador.setModal(true);
			buscador.setTitle("Buscar equipo");
			buscador.setLocationRelativeTo(buscador);
			buscador.setVisible(true);
		}
	}

	class NuevoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			estadoInicial(false, bandera);
			vaciarFormulario();
			ventana.getMarca().requestFocus();
		}
	}

	class GuardarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (validar()) {
				String modelo = ventana.getModelo().getText();
				String marca = ventana.getMarca().getText();
				Boolean estado = ventana.getEstado().isSelected();

				if (accion) {
					equipo = new Equipo();
				} else {
					String id = ventana.getId().getText();
					equipo.setId(Integer.parseInt(id));
				}

				equipo.setModelo(modelo);
				equipo.setMarca(marca);
				equipo.setEstado(estado);

				if (accion) {
					DAO.insertar(equipo);
				} else {
					DAO.actualizar(equipo);
				}

				switch (bandera) {
				case 0:
					estadoInicial(true, bandera);
					vaciarFormulario();
					break;
				case 2:
					ventana.dispose();
					VentanaOrdenServicioController.setearDesdeBuscarEquipo(equipo);
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
			if (ventana.getId().getText().isEmpty()) {
				JOptionPane.showMessageDialog(ventana,
						"Primero debe seleccionar un equipo antes de intentar eliminar un registro");
			} else {
				int opcion = JOptionPane.showConfirmDialog(ventana, "Desea realmente eliminar este registro?",
						"ATENCION!!!", JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					DAO.eliminar(equipo);
				}
				estadoInicial(true, bandera);
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
		if (ventana.getModelo().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Este campo no puede estar vacío");
			ventana.getModelo().requestFocus();
			return false;
		} else if (ventana.getMarca().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Este campo no puede estar vacío");
			ventana.getMarca().requestFocus();
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
