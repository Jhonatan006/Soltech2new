package py.com.soltech.reportes;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import py.com.soltech.controladores.VentanaListadoClienteController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaListadoCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField tfNombreDesde;
	private JTextField tfNombreHasta;
	private JTextField tfApellidoDesde;
	private JTextField tfApellidoHasta;
	private JTextField tfDocumentoDesde;
	private JTextField tfDocumentoHasta;
	private JButton btnImprimir;
	private JButton btnProcesar;
	private JLabel lblDocumento;
	private JLabel lblApellido;
	private JLabel lblNombre;
	private VentanaListadoClienteController controller;
	private JComboBox cbOrden;
	private JTextField tfldDesde;
	private JTextField tfldHasta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoCliente dialog = new VentanaListadoCliente();
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
	controller = new VentanaListadoClienteController(this);

	}

	/**
	 * Create the dialog.
	 */
	public VentanaListadoCliente() {
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 764, 319);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(84, 11, 46, 14);
		getContentPane().add(lblNombre);
		
		tfNombreDesde = new JTextField();
		tfNombreDesde.setBounds(84, 25, 132, 20);
		getContentPane().add(tfNombreDesde);
		tfNombreDesde.setColumns(10);
		
		tfNombreHasta = new JTextField();
		tfNombreHasta.setColumns(10);
		tfNombreHasta.setBounds(84, 50, 132, 20);
		getContentPane().add(tfNombreHasta);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(226, 11, 46, 14);
		getContentPane().add(lblApellido);
		
		tfApellidoDesde = new JTextField();
		tfApellidoDesde.setColumns(10);
		tfApellidoDesde.setBounds(226, 25, 189, 20);
		getContentPane().add(tfApellidoDesde);
		
		tfApellidoHasta = new JTextField();
		tfApellidoHasta.setColumns(10);
		tfApellidoHasta.setBounds(226, 50, 189, 20);
		getContentPane().add(tfApellidoHasta);
		
		lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(445, 11, 73, 15);
		getContentPane().add(lblDocumento);
		
		tfDocumentoDesde = new JTextField();
		tfDocumentoDesde.setColumns(10);
		tfDocumentoDesde.setBounds(447, 25, 109, 20);
		getContentPane().add(tfDocumentoDesde);
		
		tfDocumentoHasta = new JTextField();
		tfDocumentoHasta.setColumns(10);
		tfDocumentoHasta.setBounds(447, 50, 109, 21);
		getContentPane().add(tfDocumentoHasta);
		
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
		cbOrden.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nombre", "Apellido", "Documento"}));
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

	public JTextField getTfNombreDesde() {
		return tfNombreDesde;
	}

	public JTextField getTfNombreHasta() {
		return tfNombreHasta;
	}

	public JTextField getTfApellidoDesde() {
		return tfApellidoDesde;
	}

	public JTextField getTfApellidoHasta() {
		return tfApellidoHasta;
	}

	public JTextField getTfDocumentoDesde() {
		return tfDocumentoDesde;
	}

	public JTextField getTfDocumentoHasta() {
		return tfDocumentoHasta;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public JButton getBtnProcesar() {
		return btnProcesar;
	}

	public JLabel getLblDocumento() {
		return lblDocumento;
	}

	public JLabel getLblApellido() {
		return lblApellido;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public VentanaListadoClienteController getController() {
		return controller;
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
	

}
