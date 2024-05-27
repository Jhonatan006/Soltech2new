package py.com.soltech.vistas.abms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import py.com.soltech.controladores.VentanaOrdenServicioController;
import py.com.soltech.interfaces.AccionesABM;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import com.github.lgooddatepicker.components.DatePicker;
import java.awt.Dimension;

public class VentanaOrdenServicio extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel pnlFormulario;
	private JTextField tfCliente;
	
	
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnSalir;
	private JTextField tfId;
	private JButton btnNuevo;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnBuscarCliente;
	private JLabel lblEquipo;
	private JTextField tfEquipo;
	private JLabel lblColor;
	private JTextField tfColor;
	private JLabel lblSerial;
	private JTextField tfSerial;
	private JLabel lblPass;
	private JTextField tfPasswword;
	private JButton btnAgregarCliente;
	private JButton btnAgregarEquipo;
	private JButton btnBuscarEquipo;
	private DatePicker dpFecha;
	private JTextArea taDescripcion;	

	public VentanaOrdenServicio() {
		setModal(true);
		setBounds(100, 100, 800, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			pnlFormulario = new JPanel();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, pnlFormulario, 15, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, pnlFormulario, 15, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, pnlFormulario, -15, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, pnlFormulario, -15, SpringLayout.EAST, contentPanel);
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
		
		JLabel lblFecha = new JLabel("Fecha:");
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblFecha, 10, SpringLayout.NORTH, pnlFormulario);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblFecha, 10, SpringLayout.EAST, tfId);
		pnlFormulario.add(lblFecha);
		
		dpFecha = new DatePicker();
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, dpFecha, 10, SpringLayout.SOUTH, lblFecha);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, dpFecha, 10, SpringLayout.EAST, tfId);
		pnlFormulario.add(dpFecha);
		
		JLabel lblCliente = new JLabel("Cliente:");
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblCliente, 10, SpringLayout.SOUTH, dpFecha);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblCliente, 10, SpringLayout.WEST, pnlFormulario);
		pnlFormulario.add(lblCliente);
		
		tfCliente = new JTextField();
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, tfCliente, 10, SpringLayout.SOUTH, lblCliente);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, tfCliente, 10, SpringLayout.WEST, pnlFormulario);
		tfCliente.setEnabled(false);
		tfCliente.setEditable(false);
		pnlFormulario.add(tfCliente);
		tfCliente.setColumns(10);
		
		lblEquipo = new JLabel("Equipo:");
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblEquipo, 10, SpringLayout.SOUTH, tfCliente);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblEquipo, 10, SpringLayout.WEST, pnlFormulario);
		pnlFormulario.add(lblEquipo);
		
		tfEquipo = new JTextField();
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, tfEquipo, 10, SpringLayout.SOUTH, lblEquipo);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, tfEquipo, 10, SpringLayout.WEST, pnlFormulario);
		tfEquipo.setEnabled(false);
		tfEquipo.setEditable(false);
		pnlFormulario.add(tfEquipo);
		tfEquipo.setColumns(10);
		
		lblColor = new JLabel("Color:");
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblColor, 10, SpringLayout.SOUTH, tfEquipo);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblColor, 10, SpringLayout.WEST, pnlFormulario);
		pnlFormulario.add(lblColor);
		
		tfColor = new JTextField();
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, tfColor, 10, SpringLayout.SOUTH, lblColor);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, tfColor, 10, SpringLayout.WEST, pnlFormulario);
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, tfColor, -600, SpringLayout.EAST, pnlFormulario);
		pnlFormulario.add(tfColor);
		tfColor.setColumns(10);
		
		lblSerial = new JLabel("Serial:");
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblSerial, 10, SpringLayout.SOUTH, tfEquipo);
		
		pnlFormulario.add(lblSerial);
		
		tfSerial = new JTextField();
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblSerial, 0, SpringLayout.WEST, tfSerial);
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, tfSerial, 10, SpringLayout.SOUTH, lblColor);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, tfSerial, 10, SpringLayout.EAST, tfColor);
		pnlFormulario.add(tfSerial);
		tfSerial.setColumns(10);
		
		lblPass = new JLabel("Password:");
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblPass, 10, SpringLayout.SOUTH, tfEquipo);
		pnlFormulario.add(lblPass);
		
		tfPasswword = new JTextField();
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, tfSerial, -10, SpringLayout.WEST, tfPasswword);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, tfPasswword, -250, SpringLayout.EAST, pnlFormulario);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblPass, 0, SpringLayout.WEST, tfPasswword);
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, tfPasswword, 10, SpringLayout.SOUTH, lblColor);
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, tfPasswword, -10, SpringLayout.EAST, pnlFormulario);
		pnlFormulario.add(tfPasswword);
		tfPasswword.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblDescripcion, 10, SpringLayout.SOUTH, tfPasswword);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblDescripcion, 10, SpringLayout.WEST, pnlFormulario);
		
		pnlFormulario.add(lblDescripcion);
		
		taDescripcion = new JTextArea();
		taDescripcion.setLineWrap(true);
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, taDescripcion, 10, SpringLayout.SOUTH, lblDescripcion);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, taDescripcion, 10, SpringLayout.WEST, pnlFormulario);
		sl_pnlFormulario.putConstraint(SpringLayout.SOUTH, taDescripcion, -10, SpringLayout.SOUTH, pnlFormulario);
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, taDescripcion, -10, SpringLayout.EAST, pnlFormulario);
		pnlFormulario.add(taDescripcion);
		
		btnBuscarCliente = new JButton("Buscar");
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, tfCliente, -10, SpringLayout.WEST, btnBuscarCliente);
		sl_pnlFormulario.putConstraint(SpringLayout.SOUTH, btnBuscarCliente, 0, SpringLayout.SOUTH, tfCliente);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnBuscarCliente, 36, SpringLayout.WEST, pnlFormulario);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnBuscarCliente, -62, SpringLayout.SOUTH, pnlFormulario);
		pnlFormulario.add(btnBuscarCliente);
		
		btnAgregarCliente = new JButton("Agregar");
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, btnBuscarCliente, -100, SpringLayout.WEST, btnAgregarCliente);
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, btnBuscarCliente, -10, SpringLayout.WEST, btnAgregarCliente);
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, btnAgregarCliente, 0, SpringLayout.NORTH, btnBuscarCliente);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, btnAgregarCliente, -100, SpringLayout.EAST, pnlFormulario);
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, btnAgregarCliente, -10, SpringLayout.EAST, pnlFormulario);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnAgregarCliente, -37, SpringLayout.SOUTH, pnlFormulario);
		sl_contentPanel.putConstraint(SpringLayout.EAST, btnAgregarCliente, -91, SpringLayout.EAST, pnlFormulario);
		pnlFormulario.add(btnAgregarCliente);
		
		btnBuscarEquipo = new JButton("Buscar");
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, tfEquipo, -10, SpringLayout.WEST, btnBuscarEquipo);
		sl_pnlFormulario.putConstraint(SpringLayout.SOUTH, btnBuscarEquipo, 0, SpringLayout.SOUTH, tfEquipo);
		
		pnlFormulario.add(btnBuscarEquipo);
		
		btnAgregarEquipo = new JButton("Agregar");
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, btnBuscarEquipo, -100, SpringLayout.WEST, btnAgregarEquipo);
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, btnBuscarEquipo, -10, SpringLayout.WEST, btnAgregarEquipo);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, btnAgregarEquipo, -100, SpringLayout.EAST, pnlFormulario);
		sl_pnlFormulario.putConstraint(SpringLayout.SOUTH, btnAgregarEquipo, 0, SpringLayout.SOUTH, tfEquipo);
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, btnAgregarEquipo, -10, SpringLayout.EAST, pnlFormulario);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnAgregarEquipo, 331, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnAgregarEquipo, -36, SpringLayout.SOUTH, pnlFormulario);
		sl_contentPanel.putConstraint(SpringLayout.EAST, btnAgregarEquipo, -115, SpringLayout.EAST, pnlFormulario);
		pnlFormulario.add(btnAgregarEquipo);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			btnNuevo = new JButton("Nuevo");
			btnNuevo.setPreferredSize(new Dimension(100, 36));
			buttonPane.add(btnNuevo);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setPreferredSize(new Dimension(100, 36));
				btnGuardar.setActionCommand("Guardar");
				buttonPane.add(btnGuardar);
			}
			
			btnModificar = new JButton("Modificar");
			btnModificar.setPreferredSize(new Dimension(100, 36));
			buttonPane.add(btnModificar);
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
	
	//Métodos para manejar los controles del formulario
	public JTextField getId() {
		return tfId;
	}

	public DatePicker getFecha() {
        return dpFecha;
    }

    public JTextField getCliente() {
        return tfCliente;
    }

    public JTextField getEquipo() {
        return tfEquipo;
    }
    
    public JTextField getColor() {
        return tfColor;
    }

    public JTextField getSerial() {
        return tfSerial;
    }
    
    public JTextField getPassword() {
        return tfPasswword;
    }
    
    public JTextArea getDescripcion() {
		return taDescripcion;
	}
    
    public JButton getBtnBuscarCliente() {
    	return btnBuscarCliente;
    }
    
    public JButton getBtnNuevo() {
    	return btnNuevo;
    }
    
    public JButton getBtnAgregarCliente() {
		return btnAgregarCliente;
	}


	public JButton getBtnAgregarEquipo() {
		return btnAgregarEquipo;
	}


	public JButton getBtnBuscarEquipo() {
		return btnBuscarEquipo;
	}


	public JButton getBtnGuardar() {
    	return btnGuardar;
    }
    
    public JButton getBtnModificar() {
    	return btnModificar;
    }
    
    public JButton getBtnCancelar() {
    	return btnCancelar;
    }
    
    public JButton getBtnEliminar() {
    	return btnEliminar;
    }

	// Métodos para manejar eventos de los botones
    public void addBuscarClienteListener(ActionListener listener) {
    	btnBuscarCliente.addActionListener(listener);
    }

    public void addBuscarEquipoListener(ActionListener listener) {
    	btnBuscarEquipo.addActionListener(listener);
    }

    public void addAgregarClienteListener(ActionListener listener) {
    	btnAgregarCliente.addActionListener(listener);
    }

    public void addAgregarEquipoListener(ActionListener listener) {
    	btnAgregarEquipo.addActionListener(listener);
    }

    public void addNuevoListener(ActionListener listener) {
    	btnNuevo.addActionListener(listener);
    }

    public void addGuardarListener(ActionListener listener) {
    	btnGuardar.addActionListener(listener);
    }

    public void addModificarListener(ActionListener listener) {
        btnModificar.addActionListener(listener);
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
