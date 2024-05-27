package py.com.soltech.reportes;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import py.com.soltech.controladores.VentanaListadoFuncionarioController;

public class VentanaListadoFuncionario extends JDialog {

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
	private VentanaListadoFuncionarioController controller;
	private JComboBox cbOrden;
	private JTextField tfldDesde;
	private JTextField tfldHasta;
	private JTextField tfCargoDesde;
	private JTextField tfCargoHasta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoFuncionario dialog = new VentanaListadoFuncionario();
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
	controller = new VentanaListadoFuncionarioController(this);

	}

	/**
	 * Create the dialog.
	 */
	public VentanaListadoFuncionario() {
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 69, 764, 319);
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
		tfApellidoDesde.setBounds(226, 25, 133, 20);
		getContentPane().add(tfApellidoDesde);
		
		tfApellidoHasta = new JTextField();
		tfApellidoHasta.setColumns(10);
		tfApellidoHasta.setBounds(226, 50, 133, 20);
		getContentPane().add(tfApellidoHasta);
		
		lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(369, 11, 73, 15);
		getContentPane().add(lblDocumento);
		
		tfDocumentoDesde = new JTextField();
		tfDocumentoDesde.setColumns(10);
		tfDocumentoDesde.setBounds(371, 25, 109, 20);
		getContentPane().add(tfDocumentoDesde);
		
		tfDocumentoHasta = new JTextField();
		tfDocumentoHasta.setColumns(10);
		tfDocumentoHasta.setBounds(371, 50, 109, 21);
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
		cbOrden.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nombre", "Apellido", "Documento","Cargo"}));
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
		
		tfCargoDesde = new JTextField();
		tfCargoDesde.setColumns(10);
		tfCargoDesde.setBounds(492, 24, 109, 20);
		getContentPane().add(tfCargoDesde);
		
		JLabel lblCargo = new JLabel("CARGO");
		lblCargo.setBounds(490, 10, 73, 15);
		getContentPane().add(lblCargo);
		
		tfCargoHasta = new JTextField();
		tfCargoHasta.setColumns(10);
		tfCargoHasta.setBounds(492, 49, 109, 21);
		getContentPane().add(tfCargoHasta);

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

	public VentanaListadoFuncionarioController getController() {
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

	public JTextField getTfCargoDesde() {
		return tfCargoDesde;
	}

	public JTextField getTfCargoHasta() {
		return tfCargoHasta;
	}
	
}
