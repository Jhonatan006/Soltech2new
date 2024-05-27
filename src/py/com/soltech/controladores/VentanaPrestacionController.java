package py.com.soltech.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import py.com.soltech.dao.FuncionarioDAO;
import py.com.soltech.dao.OrdenServicioDAO;
import py.com.soltech.dao.PrestacionServicioDAO;
import py.com.soltech.modelo.entidades.Funcionario;
import py.com.soltech.modelo.entidades.OrdenServicio;
import py.com.soltech.modelo.entidades.PrestacionServicio;
import py.com.soltech.modelo.entidades.ServicioDetalle;
import py.com.soltech.utilidad.Util;
import py.com.soltech.vistas.modelo_tablas.ModeloTablaPrestacionDetalle;
import py.com.soltech.vistas.transaccion.Buscador;
import py.com.soltech.vistas.transaccion.VentanaPrestacion;

public class VentanaPrestacionController {

	private static VentanaPrestacion ventana;
	private static Funcionario funcionario;
	private static OrdenServicio orden;
	private PrestacionServicio prestacion;

	private final PrestacionServicioDAO DAO = new PrestacionServicioDAO();
	private final OrdenServicioDAO DAOSERV = new OrdenServicioDAO();
	private final FuncionarioDAO DAOFUN = new FuncionarioDAO();
	private final ModeloTablaPrestacionDetalle MTPRESTACION = new ModeloTablaPrestacionDetalle();
	private final Util UTIL = new Util();

	private List<ServicioDetalle> detalles;
	private ServicioDetalle detalle;
	private double totalCosto;

	public VentanaPrestacionController(VentanaPrestacion ventana) {
		VentanaPrestacionController.ventana = ventana;

		ventana.addBuscarOrdenListener(new BuscarOrdenListener());
		ventana.addBuscarFuncionarioListener(new BuscarFuncionarioListener());
		ventana.addAgregarListener(new AgregarListener());
		ventana.addQuitarListener(new QuitarListener());
		ventana.addGuardarListener(new GuardarListener());
		ventana.addCancelarListener(new CancelarListener());

		ventana.getTable().setModel(MTPRESTACION);

		soloNumeros(ventana.getTfCosto());
		soloMayusculas(ventana.getTfDescripcion());

		moverConEnter(ventana.getTfOrden(), ventana.getBtnBuscarOrden());
		moverConEnter(ventana.getTfFuncionarioId(), ventana.getTfDescripcion());
		moverConEnter(ventana.getTfDescripcion(), ventana.getTfCosto());
		clickConEnter(ventana.getTfCosto(), ventana.getBtnAgregar());

		recuperarOrdenPorCodigo(ventana.getTfOrden());
		recuperarFuncionarioPorCodigo(ventana.getTfFuncionarioId());

		limpiarFormulario();
	}

	private void recuperarOrdenPorCodigo(JTextField textField) {
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!ventana.getTfOrden().getText().isEmpty()) {
					orden = DAOSERV.recuperarPorId(Integer.parseInt(ventana.getTfOrden().getText()));
					try {
						if (orden.getEstado().equals("EN PROCESO")) {
							ventana.getTfOrden().setText(orden.getId() + "");
							ventana.getTfOrden().setEnabled(false);
							ventana.getTfEstado().setText(orden.getEstado());
							ventana.getDpFechaIni().setDate(orden.getFecha());
							ventana.getTfDescripcion().setEnabled(true);
							ventana.getTfCosto().setEnabled(true);
							ventana.getBtnBuscarFuncionario().setEnabled(true);
							ventana.getTfFuncionarioId().setEnabled(true);
							ventana.getTfFuncionarioId().requestFocus();
						} else {
							JOptionPane.showMessageDialog(null, "No existe esta orden de servicio o ya fue finalizada");
							ventana.getTfOrden().setText("");
							ventana.getTfOrden().requestFocus();
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "No existe esta orden de servicio o ya fue finalizada");
						ventana.getTfOrden().setText("");
						ventana.getTfOrden().requestFocus();
					}
				}
			}
		});
	}

	private void recuperarFuncionarioPorCodigo(JTextField textField) {
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!ventana.getTfFuncionarioId().getText().isEmpty()) {
					funcionario = DAOFUN.recuperarPorId(Integer.parseInt(ventana.getTfFuncionarioId().getText()));
					try {
						ventana.getTfFuncionarioId().setText(funcionario.getId() + "");
						ventana.getTfFuncionarioNombre()
								.setText(funcionario.getNombre() + " " + funcionario.getApellido());
						ventana.getTfDescripcion().requestFocus();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "No existe este funcionario");
						ventana.getTfFuncionarioId().setText("");
						ventana.getTfFuncionarioNombre().setText("");
						ventana.getTfFuncionarioId().requestFocus();
					}
				}
			}
		});
	}

	private void limpiarFormulario() {

		ventana.getTfOrden().setText("");
		ventana.getTfOrden().setEnabled(true);
		ventana.getTfOrden().requestFocus();
		ventana.getTfFuncionarioId().setText("");
		ventana.getTfFuncionarioId().setEnabled(false);
		ventana.getTfFuncionarioNombre().setText("");
		ventana.getTfFuncionarioNombre().setEnabled(false);
		ventana.getBtnBuscarFuncionario().setEnabled(false);
		ventana.getTfDescripcion().setText("");
		ventana.getTfDescripcion().setEnabled(false);
		ventana.getTfCosto().setText("");
		ventana.getTfCosto().setEnabled(false);
		ventana.getTfEstado().setText("");
		ventana.getDpFechaIni().setDate(LocalDate.now());
		ventana.getDpFechaFin().setDate(LocalDate.now());
		ventana.getTfTotal().setText("");
		detalles = new ArrayList<ServicioDetalle>();
		detalle = new ServicioDetalle();
		prestacion = new PrestacionServicio();
		orden = new OrdenServicio();
		funcionario = new Funcionario();
		totalCosto = 0d;

		MTPRESTACION.setLista(detalles);
		ventana.getTable().setModel(MTPRESTACION);
	}

	class BuscarOrdenListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Buscador buscador = new Buscador();
			BuscadorController controller = new BuscadorController(buscador);
			controller.bandera = 6;
			controller.setearModeloTablaOrden();
			buscador.setModal(true);
			buscador.setTitle("Buscar orden");
			buscador.setLocationRelativeTo(buscador);
			buscador.setVisible(true);
		}
	}

	class BuscarFuncionarioListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Buscador buscador = new Buscador();
			BuscadorController controller = new BuscadorController(buscador);
			controller.bandera = 5;
			controller.setearModeloTablaFuncionario();
			buscador.setModal(true);
			buscador.setTitle("Buscar funcionario");
			buscador.setLocationRelativeTo(buscador);
			buscador.setVisible(true);
		}
	}

	class AgregarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!ventana.getTfCosto().getText().isEmpty() || !ventana.getTfDescripcion().getText().isEmpty()) {
				detalle = new ServicioDetalle();
				detalle.setCosto(Double.parseDouble(ventana.getTfCosto().getText()));
				detalle.setDescripcion(ventana.getTfDescripcion().getText());
				detalles.add(detalle);
				totalCosto = totalizarPrestacion();
				ventana.getTfTotal().setText(UTIL.aplicarFormatoNumerico(totalCosto));
				MTPRESTACION.setLista(detalles);
				ventana.getTable().removeAll();
				ventana.getTable().setModel(MTPRESTACION);
				ventana.getTfCosto().setText("");
				ventana.getTfDescripcion().setText("");
				ventana.getTfDescripcion().requestFocus();
			} else {
				JOptionPane.showMessageDialog(ventana, "Verifique los datos a ingresar");
			}
		}
	}

	class QuitarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			detalles.remove(ventana.getTable().getSelectedRow());
			totalCosto = totalizarPrestacion();
			ventana.getTfTotal().setText(UTIL.aplicarFormatoNumerico(totalCosto));
			MTPRESTACION.setLista(detalles);
			ventana.getTable().removeAll();
			ventana.getTable().setModel(MTPRESTACION);
			ventana.getTfDescripcion().requestFocus();
		}
	}

	class GuardarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (validarFormulario() && validarFechas()) {
				prestacion.setFechaInicio(orden.getFecha());
				prestacion.setFechaFinalizacion(ventana.getDpFechaFin().getDate());
				prestacion.setCostoTotal(totalCosto);
				prestacion.setEstado("FINALIZADO");
				prestacion.setOrdenServicio(orden);
				prestacion.setFuncionario(funcionario);
				prestacion.setServicioDetalleList(detalles);
				for (int i = 0; i < detalles.size(); i++) {
					detalles.get(i).setPrestacionServicio(prestacion);
				}
				DAO.insertar(prestacion);

				orden.setEstado("FINALIZADO");
				DAOSERV.actualizar(orden);
				limpiarFormulario();

			}
		}
	}

	class CancelarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			limpiarFormulario();
		}

	}

	public static void setearDesdeBuscadorFuncionario(Funcionario fun) {
		funcionario = fun;

		ventana.getTfFuncionarioId().setText(funcionario.getId() + "");
		ventana.getTfFuncionarioNombre().setText(funcionario.getNombre() + " " + funcionario.getApellido());
		ventana.getTfDescripcion().requestFocus();

	}

	public static void setearDesdeBuscadorOrden(OrdenServicio ord) {
		orden = ord;

		ventana.getTfOrden().setText(orden.getId() + "");
		ventana.getTfOrden().setEnabled(false);
		ventana.getTfEstado().setText(orden.getEstado());
		ventana.getDpFechaIni().setDate(orden.getFecha());
		ventana.getTfDescripcion().setEnabled(true);
		ventana.getTfCosto().setEnabled(true);
		ventana.getBtnBuscarFuncionario().setEnabled(true);
		ventana.getTfFuncionarioId().setEnabled(true);
		ventana.getTfFuncionarioId().requestFocus();
	}

	private boolean validarFormulario() {
		if (ventana.getTfOrden().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Debe seleccionar una Orden de Servicio");
			ventana.getTfOrden().requestFocus();
			return false;
		} else if (ventana.getTfFuncionarioId().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "Debe seleccionar un Funcionario");
			ventana.getTfFuncionarioId().requestFocus();
			return false;
		} else if (detalles.size() == 0) {
			JOptionPane.showMessageDialog(ventana, "Debe agregar un detalle a la lista de prestaciones");
			ventana.getTfDescripcion().requestFocus();
			return false;
		}
		return true;
	}

	private boolean validarFechas() {
		LocalDate fechaInicio = ventana.getDpFechaIni().getDate();
		LocalDate fechaFin = ventana.getDpFechaFin().getDate();

		if (fechaFin.isBefore(fechaInicio)) {
			JOptionPane.showMessageDialog(null, "La fecha de finalizacion no puede ser menor a la fecha de inicio");
			return false;
		}
		return true;
	}

	private double totalizarPrestacion() {
		double total = 0d;
		for (int i = 0; i < detalles.size(); i++) {
			Double costo = detalles.get(i).getCosto();
			total += costo;
		}
		return total;
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

	private void clickConEnter(JTextField textField, JButton boton) {
		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					boton.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}

}
