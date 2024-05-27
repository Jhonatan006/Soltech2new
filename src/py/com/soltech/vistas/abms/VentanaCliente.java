package py.com.soltech.vistas.abms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import py.com.soltech.controladores.VentanaClienteController;
import py.com.soltech.interfaces.AccionesABM;

import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import java.awt.Dimension;

public class VentanaCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel pnlFormulario;
	private JTextField tfNombre;
	private JTextField tfApellido;

	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnSalir;
	private JTextField tfId;
	private JButton btnNuevo;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JLabel lblDocumento;
	private JTextField tfDocumento;
	private JLabel lblTelefono;
	private JTextField tfTelefono;
	private JLabel lblEmail;
	private JTextField tfEmail;

	public VentanaCliente() {
		setModal(true);
		setBounds(100, 100, 685, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			pnlFormulario = new JPanel();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, pnlFormulario, 10, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, pnlFormulario, 10, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, pnlFormulario, -15, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, pnlFormulario, -10, SpringLayout.EAST, contentPanel);
			contentPanel.add(pnlFormulario);
		}
		SpringLayout sl_pnlFormulario = new SpringLayout();
		pnlFormulario.setLayout(sl_pnlFormulario);

		JLabel lblId = new JLabel("Id:");
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblId, 10, SpringLayout.NORTH, pnlFormulario);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblId, 10, SpringLayout.WEST, pnlFormulario);
		pnlFormulario.add(lblId);

		tfId = new JTextField();
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, tfId, 10, SpringLayout.SOUTH, lblId);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, tfId, 10, SpringLayout.WEST, pnlFormulario);
		tfId.setEnabled(false);
		tfId.setEditable(false);
		pnlFormulario.add(tfId);
		tfId.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblNombre, 10, SpringLayout.SOUTH, tfId);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblNombre, 10, SpringLayout.WEST, pnlFormulario);
		pnlFormulario.add(lblNombre);

		tfNombre = new JTextField();
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, tfNombre, 10, SpringLayout.SOUTH, lblNombre);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, tfNombre, 10, SpringLayout.WEST, pnlFormulario);
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, tfNombre, -10, SpringLayout.EAST, pnlFormulario);
		pnlFormulario.add(tfNombre);
		tfNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido:");
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblApellido, 10, SpringLayout.SOUTH, tfNombre);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblApellido, 10, SpringLayout.WEST, pnlFormulario);
		pnlFormulario.add(lblApellido);

		tfApellido = new JTextField();
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, tfApellido, 10, SpringLayout.SOUTH, lblApellido);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, tfApellido, 10, SpringLayout.WEST, pnlFormulario);
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, tfApellido, -10, SpringLayout.EAST, pnlFormulario);
		pnlFormulario.add(tfApellido);
		tfApellido.setColumns(10);

		lblDocumento = new JLabel("Documento:");
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblDocumento, 10, SpringLayout.SOUTH, tfApellido);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblDocumento, 10, SpringLayout.WEST, pnlFormulario);
		pnlFormulario.add(lblDocumento);

		tfDocumento = new JTextField();
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, tfDocumento, 10, SpringLayout.SOUTH, lblDocumento);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, tfDocumento, 10, SpringLayout.WEST, pnlFormulario);
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, tfDocumento, 280, SpringLayout.WEST, pnlFormulario);
		pnlFormulario.add(tfDocumento);
		tfDocumento.setColumns(10);

		lblTelefono = new JLabel("Tel\u00E9fono:");
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblTelefono, 10, SpringLayout.SOUTH, tfApellido);
		pnlFormulario.add(lblTelefono);

		tfTelefono = new JTextField();
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblTelefono, 0, SpringLayout.WEST, tfTelefono);
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, tfTelefono, 10, SpringLayout.SOUTH, lblTelefono);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, tfTelefono, 50, SpringLayout.EAST, tfDocumento);
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, tfTelefono, -10, SpringLayout.EAST, pnlFormulario);
		pnlFormulario.add(tfTelefono);
		tfTelefono.setColumns(10);

		lblEmail = new JLabel("Email:");
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblEmail, 10, SpringLayout.SOUTH, tfDocumento);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblEmail, 10, SpringLayout.WEST, pnlFormulario);
		pnlFormulario.add(lblEmail);

		tfEmail = new JTextField();
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, tfEmail, 10, SpringLayout.SOUTH, lblEmail);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, tfEmail, 10, SpringLayout.WEST, pnlFormulario);
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, tfEmail, -10, SpringLayout.EAST, pnlFormulario);
		pnlFormulario.add(tfEmail);
		
					btnBuscar = new JButton("Buscar");
					sl_pnlFormulario.putConstraint(SpringLayout.NORTH, btnBuscar, 26, SpringLayout.NORTH, pnlFormulario);
					sl_pnlFormulario.putConstraint(SpringLayout.WEST, btnBuscar, 21, SpringLayout.EAST, tfId);
					pnlFormulario.add(btnBuscar);
					btnBuscar.setPreferredSize(new Dimension(100, 36));
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

			btnNuevo = new JButton("Nuevo");
			btnNuevo.setPreferredSize(new Dimension(100, 36));
			btnNuevo.setMinimumSize(new Dimension(63, 36));
			buttonPane.add(btnNuevo);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setPreferredSize(new Dimension(100, 36));
				btnGuardar.setActionCommand("Guardar");
				buttonPane.add(btnGuardar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setPreferredSize(new Dimension(100, 36));
				btnCancelar.setActionCommand("Cancelar");
				buttonPane.add(btnCancelar);
			}

			btnEliminar = new JButton("Eliminar");
			btnEliminar.setPreferredSize(new Dimension(100, 36));
			buttonPane.add(btnEliminar);
			{
				btnSalir = new JButton("Salir");
				btnSalir.setPreferredSize(new Dimension(100, 36));
				btnSalir.setActionCommand("Salir");
				buttonPane.add(btnSalir);
			}
		}
	}

	// Métodos para manejar los controles del formulario
	public JTextField getId() {
		return tfId;
	}

	public JTextField getNombre() {
		return tfNombre;
	}

	public JTextField getApellido() {
		return tfApellido;
	}

	public JTextField getDocumento() {
		return tfDocumento;
	}

	public JTextField getEmail() {
		return tfEmail;
	}

	public JTextField getTelefono() {
		return tfTelefono;
	}

	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	// Métodos para manejar eventos de los botones
	public void addNuevoListener(ActionListener listener) {
		btnNuevo.addActionListener(listener);
	}

	public void addGuardarListener(ActionListener listener) {
		btnGuardar.addActionListener(listener);
	}

	public void addBuscarListener(ActionListener listener) {
		btnBuscar.addActionListener(listener);
	}

	public void addCancelarListener(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}

	public void addEliminarListener(ActionListener listener) {
		btnEliminar.addActionListener(listener);
	}

	public void addSalirListener(ActionListener listener) {
		btnSalir.addActionListener(listener);
	}
}
