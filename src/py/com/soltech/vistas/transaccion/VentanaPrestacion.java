package py.com.soltech.vistas.transaccion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Component;

public class VentanaPrestacion extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lbTitulo;
	private JTextField tfOrden;
	private JTextField tfFuncionarioId;
	private JTextField tfFuncionarioNombre;
	private JTextField tfDescripcion;
	private JTextField tfCosto;
	private JTextField tfEstado;
	private JTable table;
	private JButton btnBuscarOrden;
	private JButton btnBuscarFuncionario;
	private JButton btnAgregar;
	private JButton btnQuitar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private DatePicker dpFechaIni;
	private DatePicker dpFechaFin;
	private JTextField tfTotal;

	public VentanaPrestacion() {
		setModal(true);
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lbTitulo = new JLabel("Prestacion de Servicios");
			lbTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lbTitulo, 10, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lbTitulo, 10, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, lbTitulo, -10, SpringLayout.EAST, contentPanel);
			contentPanel.add(lbTitulo);
		}

		JLabel lbOrden = new JLabel("Orden");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lbOrden, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lbOrden);

		JLabel lbFuncionario = new JLabel("Funcionario");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lbFuncionario, 0, SpringLayout.WEST, lbTitulo);
		contentPanel.add(lbFuncionario);

		JLabel lbDescripcion = new JLabel("Descripcion");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lbDescripcion, 0, SpringLayout.WEST, lbTitulo);
		contentPanel.add(lbDescripcion);

		JLabel lbCosto = new JLabel("Costo");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lbCosto, 0, SpringLayout.WEST, lbTitulo);
		contentPanel.add(lbCosto);

		JLabel lbEstado = new JLabel("Estado");
		contentPanel.add(lbEstado);

		JLabel lbFechaIni = new JLabel("Fecha de inicio");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lbEstado, 0, SpringLayout.WEST, lbFechaIni);
		contentPanel.add(lbFechaIni);

		JLabel lbFechaFin = new JLabel("Fecha de finalizaci\u00F3n");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lbFechaIni, 0, SpringLayout.WEST, lbFechaFin);
		contentPanel.add(lbFechaFin);

		tfOrden = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lbOrden, 0, SpringLayout.NORTH, tfOrden);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lbOrden, 0, SpringLayout.SOUTH, tfOrden);
		sl_contentPanel.putConstraint(SpringLayout.WEST, tfOrden, 120, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, tfOrden, 200, SpringLayout.WEST, contentPanel);
		contentPanel.add(tfOrden);
		tfOrden.setColumns(10);

		tfFuncionarioId = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lbFuncionario, 0, SpringLayout.NORTH, tfFuncionarioId);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lbFuncionario, 0, SpringLayout.SOUTH, tfFuncionarioId);
		sl_contentPanel.putConstraint(SpringLayout.WEST, tfFuncionarioId, 120, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, tfFuncionarioId, 200, SpringLayout.WEST, contentPanel);
		contentPanel.add(tfFuncionarioId);
		tfFuncionarioId.setColumns(10);

		tfFuncionarioNombre = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.WEST, tfFuncionarioNombre, 10, SpringLayout.EAST, tfFuncionarioId);
		tfFuncionarioNombre.setEditable(false);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, tfFuncionarioId, 0, SpringLayout.NORTH, tfFuncionarioNombre);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, tfFuncionarioId, 0, SpringLayout.SOUTH, tfFuncionarioNombre);
		contentPanel.add(tfFuncionarioNombre);
		tfFuncionarioNombre.setColumns(10);

		tfDescripcion = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lbDescripcion, 0, SpringLayout.NORTH, tfDescripcion);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lbDescripcion, 0, SpringLayout.SOUTH, tfDescripcion);
		sl_contentPanel.putConstraint(SpringLayout.WEST, tfDescripcion, 120, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, tfDescripcion, -150, SpringLayout.WEST, lbFechaFin);
		contentPanel.add(tfDescripcion);
		tfDescripcion.setColumns(10);

		tfCosto = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lbCosto, 0, SpringLayout.NORTH, tfCosto);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lbCosto, 0, SpringLayout.SOUTH, tfCosto);
		sl_contentPanel.putConstraint(SpringLayout.WEST, tfCosto, 120, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, tfCosto, 300, SpringLayout.WEST, contentPanel);
		contentPanel.add(tfCosto);
		tfCosto.setColumns(10);

		tfEstado = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lbEstado, 0, SpringLayout.NORTH, tfEstado);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lbEstado, 0, SpringLayout.SOUTH, tfEstado);
		sl_contentPanel.putConstraint(SpringLayout.EAST, lbEstado, -10, SpringLayout.WEST, tfEstado);
		tfEstado.setEditable(false);
		tfEstado.setFocusable(false);
		sl_contentPanel.putConstraint(SpringLayout.EAST, tfEstado, -15, SpringLayout.EAST, contentPanel);
		contentPanel.add(tfEstado);
		tfEstado.setColumns(10);

		dpFechaIni = new DatePicker();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lbFechaIni, 0, SpringLayout.NORTH, dpFechaIni);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lbFechaIni, 0, SpringLayout.SOUTH, dpFechaIni);
		sl_contentPanel.putConstraint(SpringLayout.EAST, lbFechaIni, -10, SpringLayout.WEST, dpFechaIni);
		dpFechaIni.getComponentDateTextField().setEnabled(false);
		dpFechaIni.getComponentToggleCalendarButton().setEnabled(false);
		dpFechaIni.getComponentDateTextField().setEditable(false);
		dpFechaIni.setFocusable(false);
		sl_contentPanel.putConstraint(SpringLayout.WEST, dpFechaIni, 700, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, tfEstado, 0, SpringLayout.WEST, dpFechaIni);
		sl_contentPanel.putConstraint(SpringLayout.EAST, dpFechaIni, -15, SpringLayout.EAST, contentPanel);
		contentPanel.add(dpFechaIni);

		dpFechaFin = new DatePicker();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lbFechaFin, 0, SpringLayout.NORTH, dpFechaFin);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, tfDescripcion, 0, SpringLayout.NORTH, dpFechaFin);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, tfDescripcion, 0, SpringLayout.SOUTH, dpFechaFin);
		sl_contentPanel.putConstraint(SpringLayout.WEST, dpFechaFin, 700, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lbFechaFin, 0, SpringLayout.SOUTH, dpFechaFin);
		sl_contentPanel.putConstraint(SpringLayout.EAST, lbFechaFin, -10, SpringLayout.WEST, dpFechaFin);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, dpFechaFin, 10, SpringLayout.SOUTH, dpFechaIni);
		sl_contentPanel.putConstraint(SpringLayout.EAST, dpFechaFin, -15, SpringLayout.EAST, contentPanel);
		contentPanel.add(dpFechaFin);

		btnAgregar = new JButton("Agregar");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, tfCosto, 0, SpringLayout.NORTH, btnAgregar);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, tfCosto, 0, SpringLayout.SOUTH, btnAgregar);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnAgregar, 10, SpringLayout.SOUTH, dpFechaFin);
		contentPanel.add(btnAgregar);

		btnQuitar = new JButton("Quitar");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnQuitar, 10, SpringLayout.SOUTH, dpFechaFin);
		sl_contentPanel.putConstraint(SpringLayout.EAST, btnAgregar, -10, SpringLayout.WEST, btnQuitar);
		sl_contentPanel.putConstraint(SpringLayout.EAST, btnQuitar, -15, SpringLayout.EAST, contentPanel);
		contentPanel.add(btnQuitar);

		JPanel panel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, panel, 20, SpringLayout.SOUTH, btnAgregar);
		sl_contentPanel.putConstraint(SpringLayout.WEST, panel, 30, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, panel, -30, SpringLayout.EAST, contentPanel);
		contentPanel.add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JScrollPane scrollPane = new JScrollPane();
		sl_panel.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, panel);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnBuscarOrden = new JButton("Buscar");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, tfEstado, 0, SpringLayout.NORTH, btnBuscarOrden);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, tfEstado, 0, SpringLayout.SOUTH, btnBuscarOrden);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, tfOrden, 0, SpringLayout.NORTH, btnBuscarOrden);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, tfOrden, 0, SpringLayout.SOUTH, btnBuscarOrden);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, tfFuncionarioNombre, 10, SpringLayout.SOUTH, btnBuscarOrden);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnBuscarOrden, 6, SpringLayout.SOUTH, lbTitulo);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnBuscarOrden, 10, SpringLayout.EAST, tfOrden);
		contentPanel.add(btnBuscarOrden);

		btnBuscarFuncionario = new JButton("Buscar");
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnBuscarFuncionario, 406, SpringLayout.WEST,
				contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, tfFuncionarioNombre, -6, SpringLayout.WEST, btnBuscarFuncionario);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lbFechaFin, 20, SpringLayout.EAST, btnBuscarFuncionario);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, dpFechaIni, 0, SpringLayout.NORTH, btnBuscarFuncionario);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, dpFechaIni, 0, SpringLayout.SOUTH, btnBuscarFuncionario);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, tfFuncionarioNombre, 0, SpringLayout.SOUTH,
				btnBuscarFuncionario);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnBuscarFuncionario, 10, SpringLayout.SOUTH, btnBuscarOrden);
		btnBuscarFuncionario.setFocusable(false);
		contentPanel.add(btnBuscarFuncionario);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			
			JLabel lblTotal = new JLabel("Total");
			lblTotal.setAlignmentY(Component.TOP_ALIGNMENT);
			lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
			buttonPane.add(lblTotal);
			
			tfTotal = new JTextField();
			tfTotal.setAlignmentY(Component.TOP_ALIGNMENT);
			tfTotal.setAlignmentX(Component.LEFT_ALIGNMENT);
			tfTotal.setFont(new Font("Dialog", Font.BOLD, 16));
			tfTotal.setHorizontalAlignment(SwingConstants.RIGHT);
			tfTotal.setEditable(false);
			buttonPane.add(tfTotal);
			tfTotal.setColumns(20);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setAlignmentY(Component.TOP_ALIGNMENT);
				btnGuardar.setPreferredSize(new Dimension(100, 36));
				btnGuardar.setActionCommand("");
				buttonPane.add(btnGuardar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setAlignmentY(Component.TOP_ALIGNMENT);
				btnCancelar.setPreferredSize(new Dimension(100, 36));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

	// Obtener componentes
	public JTextField getTfOrden() {
		return tfOrden;
	}

	public JTextField getTfFuncionarioId() {
		return tfFuncionarioId;
	}

	public JTextField getTfFuncionarioNombre() {
		return tfFuncionarioNombre;
	}

	public JTextField getTfDescripcion() {
		return tfDescripcion;
	}

	public JTextField getTfCosto() {
		return tfCosto;
	}

	public JTextField getTfTotal() {
		return tfTotal;
	}

	public JTextField getTfEstado() {
		return tfEstado;
	}

	public JTable getTable() {
		return table;
	}

	public DatePicker getDpFechaIni() {
		return dpFechaIni;
	}

	public DatePicker getDpFechaFin() {
		return dpFechaFin;
	}

	public JButton getBtnBuscarOrden() {
		return btnBuscarOrden;
	}
	
	public JButton getBtnBuscarFuncionario() {
		return btnBuscarFuncionario;
	}
	
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public void addBuscarOrdenListener(ActionListener listener) {
		btnBuscarOrden.addActionListener(listener);
	}

	public void addBuscarFuncionarioListener(ActionListener listener) {
		btnBuscarFuncionario.addActionListener(listener);
	}

	public void addAgregarListener(ActionListener listener) {
		btnAgregar.addActionListener(listener);
	}

	public void addQuitarListener(ActionListener listener) {
		btnQuitar.addActionListener(listener);
	}

	public void addGuardarListener(ActionListener listener) {
		btnGuardar.addActionListener(listener);
	}

	public void addCancelarListener(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
}
