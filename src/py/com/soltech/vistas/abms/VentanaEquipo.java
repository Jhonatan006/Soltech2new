package py.com.soltech.vistas.abms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import py.com.soltech.controladores.VentanaEquipoController;
import javax.swing.JCheckBox;
import java.awt.Dimension;

public class VentanaEquipo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel pnlFormulario;
	private JTextField tfMarca;
	private JTextField tfModelo;
	
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnSalir;
	private JTextField tfId;
	private JButton btnNuevo;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JCheckBox chkActivo;
	
	public VentanaEquipo() {
		setModal(true);
		setBounds(100, 100, 645, 365);
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
		
		JLabel lblMarca = new JLabel("Marca:");
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblMarca, 10, SpringLayout.WEST, pnlFormulario);
		pnlFormulario.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo:");
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblModelo, 10, SpringLayout.WEST, pnlFormulario);
		pnlFormulario.add(lblModelo);
		
		JLabel lblEstado = new JLabel("Estado:");
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, lblEstado, 10, SpringLayout.WEST, pnlFormulario);
		pnlFormulario.add(lblEstado);
		
		tfId = new JTextField();
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblMarca, 10, SpringLayout.SOUTH, tfId);
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, tfId, 10, SpringLayout.SOUTH, lblId);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, tfId, 10, SpringLayout.WEST, pnlFormulario);
		tfId.setEnabled(false);
		tfId.setEditable(false);
		pnlFormulario.add(tfId);
		tfId.setColumns(10);
		
		tfMarca = new JTextField();
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, tfMarca, -10, SpringLayout.EAST, pnlFormulario);
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblModelo, 10, SpringLayout.SOUTH, tfMarca);
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, tfMarca, 10, SpringLayout.SOUTH, lblMarca);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, tfMarca, 10, SpringLayout.WEST, pnlFormulario);
		pnlFormulario.add(tfMarca);
		tfMarca.setColumns(10);
		
		tfModelo = new JTextField();
		sl_pnlFormulario.putConstraint(SpringLayout.EAST, tfModelo, -10, SpringLayout.EAST, pnlFormulario);
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, lblEstado, 10, SpringLayout.SOUTH, tfModelo);
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, tfModelo, 10, SpringLayout.SOUTH, lblModelo);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, tfModelo, 10, SpringLayout.WEST, pnlFormulario);
		pnlFormulario.add(tfModelo);
		tfModelo.setColumns(10);
		
		chkActivo = new JCheckBox("Activo");
		sl_pnlFormulario.putConstraint(SpringLayout.SOUTH, lblEstado, 0, SpringLayout.SOUTH, chkActivo);
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, chkActivo, 10, SpringLayout.SOUTH, tfModelo);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, chkActivo, 10, SpringLayout.EAST, lblEstado);
		chkActivo.setSelected(true);
		pnlFormulario.add(chkActivo);
		
		btnBuscar = new JButton("Buscar");
		sl_pnlFormulario.putConstraint(SpringLayout.NORTH, btnBuscar, 10, SpringLayout.SOUTH, lblId);
		sl_pnlFormulario.putConstraint(SpringLayout.WEST, btnBuscar, 10, SpringLayout.EAST, tfId);
		pnlFormulario.add(btnBuscar);
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

	public JTextField getModelo() {
        return tfModelo;
    }

    public JTextField getMarca() {
        return tfMarca;
    }

    public JCheckBox getEstado() {
        return chkActivo;
    }
     
    public JButton getBtnBuscar() {
    	return btnBuscar;
    }
    
    public JButton getBtnNuevo() {
    	return btnNuevo;
    }
    
    public JButton getBtnGuardar() {
    	return btnGuardar;
    }
    
    public JButton getBtnCancelar() {
    	return btnCancelar;
    }
    
    public JButton getBtnEliminar() {
    	return btnEliminar;
    }
    
    // Métodos para manejar eventos de los botones
    public void addBuscarListener(ActionListener listener) {
    	btnBuscar.addActionListener(listener);
    }

    public void addNuevoListener(ActionListener listener) {
    	btnNuevo.addActionListener(listener);
    }

    public void addGuardarListener(ActionListener listener) {
    	btnGuardar.addActionListener(listener);
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
    
    public void addEstadoListener(ActionListener listener) {
        chkActivo.addActionListener(listener);
    }
}
