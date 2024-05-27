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

import py.com.soltech.controladores.VentanaListadoPresentacionServicioController;

public class VentanaListadoPrestacionServicio extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField tfEstadoDesde;
	private JTextField tfEstadoHasta;
	private JTextField tfFuncionarioDesde;
	private JTextField tfFuncionarioHasta;
	private JTextField tfCostoDesde;
	private JTextField tfCostoHasta;
	private JButton btnImprimir;
	private JButton btnProcesar;
	private JLabel lblCosto;
	private JLabel lblFuncionario;
	private JLabel lblEstado;
	private VentanaListadoPresentacionServicioController controller;
	private JComboBox cbOrden;
	private JTextField tfFechainicioDesde;
	private JTextField tfFechainicioHasta;
	private JTextField tfEquipoDesde;
	private JTextField tfEquipoHasta;
	private JTextField tfIdHasta;
	private JTextField tfIdDesde;
	private JLabel lblId;
	private JLabel lblFechafin;
	private JTextField tfFechafinDesde;
	private JTextField tfFechafinHasta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoPrestacionServicio dialog = new VentanaListadoPrestacionServicio();
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
	controller = new VentanaListadoPresentacionServicioController(this);

	}

	/**
	 * Create the dialog.
	 */
	public VentanaListadoPrestacionServicio() {
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(-31, 81, 764, 319);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblEstado = new JLabel("ESTADO");
		lblEstado.setBounds(239, 12, 73, 14);
		getContentPane().add(lblEstado);
		
		tfEstadoDesde = new JTextField();
		tfEstadoDesde.setBounds(239, 26, 62, 20);
		getContentPane().add(tfEstadoDesde);
		tfEstadoDesde.setColumns(10);
		
		tfEstadoHasta = new JTextField();
		tfEstadoHasta.setColumns(10);
		tfEstadoHasta.setBounds(239, 51, 62, 20);
		getContentPane().add(tfEstadoHasta);
		
		lblFuncionario = new JLabel("FUNCIONARIO");
		lblFuncionario.setBounds(311, 11, 102, 14);
		getContentPane().add(lblFuncionario);
		
		tfFuncionarioDesde = new JTextField();
		tfFuncionarioDesde.setColumns(10);
		tfFuncionarioDesde.setBounds(311, 25, 102, 20);
		getContentPane().add(tfFuncionarioDesde);
		
		tfFuncionarioHasta = new JTextField();
		tfFuncionarioHasta.setColumns(10);
		tfFuncionarioHasta.setBounds(311, 50, 102, 20);
		getContentPane().add(tfFuncionarioHasta);
		
		lblCosto = new JLabel("COSTO");
		lblCosto.setBounds(491, 12, 73, 15);
		getContentPane().add(lblCosto);
		
		tfCostoDesde = new JTextField();
		tfCostoDesde.setColumns(10);
		tfCostoDesde.setBounds(493, 26, 62, 20);
		getContentPane().add(tfCostoDesde);
		
		tfCostoHasta = new JTextField();
		tfCostoHasta.setColumns(10);
		tfCostoHasta.setBounds(493, 51, 62, 21);
		getContentPane().add(tfCostoHasta);
		
		btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnProcesar.setBounds(693, 26, 89, 46);
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
		cbOrden.setModel(new DefaultComboBoxModel(new String[] {"FECHA INICIO", "Estado", "Funcionario", "Costo"}));
		cbOrden.setBounds(602, 24, 73, 21);
		getContentPane().add(cbOrden);
		
		JLabel lblFechainicio = new JLabel("FECHA INICIO\r\n");
		lblFechainicio.setBounds(80, 12, 73, 15);
		getContentPane().add(lblFechainicio);
		
		tfFechainicioDesde = new JTextField();
		tfFechainicioDesde.setColumns(10);
		tfFechainicioDesde.setBounds(80, 26, 62, 20);
		getContentPane().add(tfFechainicioDesde);
		
		tfFechainicioHasta = new JTextField();
		tfFechainicioHasta.setColumns(10);
		tfFechainicioHasta.setBounds(80, 51, 62, 20);
		getContentPane().add(tfFechainicioHasta);
		
		JLabel lblEquipo = new JLabel("EQUIPO\r\n");
		lblEquipo.setBounds(425, 12, 73, 14);
		getContentPane().add(lblEquipo);
		
		tfEquipoDesde = new JTextField();
		tfEquipoDesde.setColumns(10);
		tfEquipoDesde.setBounds(425, 26, 62, 20);
		getContentPane().add(tfEquipoDesde);
		
		tfEquipoHasta = new JTextField();
		tfEquipoHasta.setColumns(10);
		tfEquipoHasta.setBounds(423, 51, 62, 20);
		getContentPane().add(tfEquipoHasta);
		
		tfIdHasta = new JTextField();
		tfIdHasta.setColumns(10);
		tfIdHasta.setBounds(10, 51, 62, 20);
		getContentPane().add(tfIdHasta);
		
		tfIdDesde = new JTextField();
		tfIdDesde.setColumns(10);
		tfIdDesde.setBounds(10, 26, 62, 20);
		getContentPane().add(tfIdDesde);
		
		lblId = new JLabel("ID\r\n");
		lblId.setBounds(10, 12, 73, 15);
		getContentPane().add(lblId);
		
		lblFechafin = new JLabel("FECHA FIN");
		lblFechafin.setBounds(156, 12, 73, 15);
		getContentPane().add(lblFechafin);
		
		tfFechafinDesde = new JTextField();
		tfFechafinDesde.setColumns(10);
		tfFechafinDesde.setBounds(156, 26, 62, 20);
		getContentPane().add(tfFechafinDesde);
		
		tfFechafinHasta = new JTextField();
		tfFechafinHasta.setColumns(10);
		tfFechafinHasta.setBounds(156, 51, 62, 20);
		getContentPane().add(tfFechafinHasta);

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
	public VentanaListadoPresentacionServicioController getController() {
		return controller;
	}

	public JComboBox getCbOrden() {
		return cbOrden;
	}

	public JTextField getTfEstadoDesde() {
		return tfEstadoDesde;
	}

	public JTextField getTfEstadoHasta() {
		return tfEstadoHasta;
	}

	public JTextField getTfFuncionarioDesde() {
		return tfFuncionarioDesde;
	}

	public JTextField getTfFuncionarioHasta() {
		return tfFuncionarioHasta;
	}

	public JTextField getTfCostoDesde() {
		return tfCostoDesde;
	}

	public JTextField getTfCostoHasta() {
		return tfCostoHasta;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public JButton getBtnProcesar() {
		return btnProcesar;
	}

	public JLabel getLblCosto() {
		return lblCosto;
	}

	public JLabel getLblFuncionario() {
		return lblFuncionario;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JTextField getTfFechaInicioDesde() {
		return tfFechainicioDesde;
	}

	public JTextField getTfFechainicioHasta() {
		return tfFechainicioHasta;
	}

	public JTextField getTfFechainicioDesde() {
		return tfFechainicioDesde;
	}

	public JTextField getTfEquipoDesde() {
		return tfEquipoDesde;
	}

	public JTextField getTfEquipoHasta() {
		return tfEquipoHasta;
	}

	public JTextField getTfIdHasta() {
		return tfIdHasta;
	}

	public JTextField getTfIdDesde() {
		return tfIdDesde;
	}

	public JLabel getLblId() {
		return lblId;
	}
	
}
