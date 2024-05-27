package py.com.soltech.reportes;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import py.com.soltech.controladores.VentanaListadoClienteController;
import py.com.soltech.controladores.VentanaListadoOrdenServicioController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class VentanaListadoOrdenServicio extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField tfFechaDesde;
	private JTextField tfFechaHasta;
	private JTextField tfEstadoDesde;
	private JTextField tfEstadoHasta;
	private JTextField tfDescripcionDesde;
	private JTextField tfDescripcionHasta;
	private JButton btnImprimir;
	private JButton btnProcesar;
	private JLabel lblDescripcion;
	private JLabel lblEstado;
	private VentanaListadoOrdenServicio controller;
	private JComboBox cbOrden;
	private JTextField tfldDesde;
	private JTextField tfldHasta;
	private JLabel lblFecha;
	private JTextField tfColorDesde;
	private JTextField tfColorHasta;
	private JLabel lblColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoOrdenServicio dialog = new VentanaListadoOrdenServicio();
					dialog.setUpController();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setUpController() {
		new VentanaListadoOrdenServicioController(this);

	}

	/**
	 * Create the dialog.
	 */
	public VentanaListadoOrdenServicio() {
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 764, 319);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		tfFechaDesde = new JTextField();
		tfFechaDesde.setBounds(84, 25, 132, 20);
		getContentPane().add(tfFechaDesde);
		tfFechaDesde.setColumns(10);

		tfFechaHasta = new JTextField();
		tfFechaHasta.setColumns(10);
		tfFechaHasta.setBounds(84, 50, 132, 20);
		getContentPane().add(tfFechaHasta);

		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(226, 11, 46, 14);
		getContentPane().add(lblEstado);

		tfEstadoDesde = new JTextField();
		tfEstadoDesde.setColumns(10);
		tfEstadoDesde.setBounds(226, 25, 125, 20);
		getContentPane().add(tfEstadoDesde);

		tfEstadoHasta = new JTextField();
		tfEstadoHasta.setColumns(10);
		tfEstadoHasta.setBounds(226, 50, 125, 20);
		getContentPane().add(tfEstadoHasta);

		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(483, 11, 73, 15);
		getContentPane().add(lblDescripcion);

		tfDescripcionDesde = new JTextField();
		tfDescripcionDesde.setColumns(10);
		tfDescripcionDesde.setBounds(483, 25, 109, 20);
		getContentPane().add(tfDescripcionDesde);

		tfDescripcionHasta = new JTextField();
		tfDescripcionHasta.setColumns(10);
		tfDescripcionHasta.setBounds(483, 49, 109, 21);
		getContentPane().add(tfDescripcionHasta);

		btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnProcesar.setBounds(687, 25, 89, 46);
		getContentPane().add(btnProcesar);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnImprimir.setBounds(672, 404, 89, 46);
		getContentPane().add(btnImprimir);

		JLabel lblOrdenarPor = new JLabel("Ordenar Por");
		lblOrdenarPor.setBounds(610, 12, 73, 15);
		getContentPane().add(lblOrdenarPor);

		cbOrden = new JComboBox();
		cbOrden.setModel(new DefaultComboBoxModel(new String[] { "Id", "Fecha", "Estado", "Color", "Descripcion" }));
		cbOrden.setBounds(602, 24, 73, 21);
		getContentPane().add(cbOrden);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 11, 73, 15);
		getContentPane().add(lblCodigo);

		tfldDesde = new JTextField();
		tfldDesde.setColumns(10);
		tfldDesde.setBounds(10, 25, 62, 20);
		getContentPane().add(tfldDesde);

		tfldHasta = new JTextField();
		tfldHasta.setColumns(10);
		tfldHasta.setBounds(10, 50, 62, 20);
		getContentPane().add(tfldHasta);

		lblFecha = new JLabel("fecha");
		lblFecha.setBounds(84, 12, 46, 14);
		getContentPane().add(lblFecha);

		tfColorDesde = new JTextField();
		tfColorDesde.setColumns(10);
		tfColorDesde.setBounds(361, 25, 109, 20);
		getContentPane().add(tfColorDesde);

		tfColorHasta = new JTextField();
		tfColorHasta.setColumns(10);
		tfColorHasta.setBounds(364, 50, 109, 21);
		getContentPane().add(tfColorHasta);

		lblColor = new JLabel("Color");
		lblColor.setBounds(359, 11, 73, 15);
		getContentPane().add(lblColor);

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTable getTable() {
		return table;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JTextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public JTextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	public JTextField getTfEstadoDesde() {
		return tfEstadoDesde;
	}

	public JTextField getTfEstadoHasta() {
		return tfEstadoHasta;
	}

	public JTextField getTfDescripcionDesde() {
		return tfDescripcionDesde;
	}

	public JTextField getTfDescripcionHasta() {
		return tfDescripcionHasta;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public JButton getBtnProcesar() {
		return btnProcesar;
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JComboBox getCbOrden() {
		return cbOrden;
	}

	public JTextField getTfldDesde() {
		return tfldDesde;
	}

	public JTextField getTfldHasta() {
		return tfldHasta;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public JTextField getTfColorDesde() {
		return tfColorDesde;
	}

	public JTextField getTfColorHasta() {
		return tfColorHasta;
	}

	public JLabel getLblColor() {
		return lblColor;
	}

	public VentanaListadoOrdenServicio getController() {
		return controller;
	}

}
