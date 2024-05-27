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

import py.com.soltech.controladores.VentanaListadoClienteController;
import py.com.soltech.controladores.VentanaListadoEquipoController;

public class VentanaListadoEquipo extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JScrollPane scrollPane;
    private JTextField tfModeloDesde;
    private JTextField tfModeloHasta;
    private JTextField tfMarcaDesde;
    private JTextField tfMarcaHasta;
    private JTextField tfEstadoDesde;
    private JTextField tfEstadoHasta;
    private JButton btnImprimir;
    private JButton btnProcesar;
    private JLabel lblEstado;
    private JLabel lblMarca;
    private JLabel lblModelo;
    private VentanaListadoEquipoController controller;
    private JComboBox cbOrden;
    private JTextField tfIdDesde;
    private JTextField tfIdHasta;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoEquipo dialog = new VentanaListadoEquipo();
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
	controller = new VentanaListadoEquipoController(this);
	}
    /**
     * Create the dialog.
     */
    public VentanaListadoEquipo() {
        setBounds(100, 100, 800, 500);
        getContentPane().setLayout(null);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 81, 764, 319);
        getContentPane().add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
        lblModelo = new JLabel("Modelo\r\n");
        lblModelo.setBounds(84, 11, 46, 14);
        getContentPane().add(lblModelo);
        
        tfModeloDesde = new JTextField();
        tfModeloDesde.setBounds(84, 25, 132, 20);
        getContentPane().add(tfModeloDesde);
        tfModeloDesde.setColumns(10);
        
        tfModeloHasta = new JTextField();
        tfModeloHasta.setColumns(10);
        tfModeloHasta.setBounds(84, 50, 132, 20);
        getContentPane().add(tfModeloHasta);
        
        lblMarca = new JLabel("Marca\r\n");
        lblMarca.setBounds(226, 11, 46, 14);
        getContentPane().add(lblMarca);
        
        tfMarcaDesde = new JTextField();
        tfMarcaDesde.setColumns(10);
        tfMarcaDesde.setBounds(226, 25, 189, 20);
        getContentPane().add(tfMarcaDesde);
        
        tfMarcaHasta = new JTextField();
        tfMarcaHasta.setColumns(10);
        tfMarcaHasta.setBounds(226, 50, 189, 20);
        getContentPane().add(tfMarcaHasta);
        
        lblEstado = new JLabel("Estado");
        lblEstado.setBounds(445, 11, 73, 15);
        getContentPane().add(lblEstado);
        
        tfEstadoDesde = new JTextField();
        tfEstadoDesde.setColumns(10);
        tfEstadoDesde.setBounds(447, 25, 109, 20);
        getContentPane().add(tfEstadoDesde);
        
        tfEstadoHasta = new JTextField();
        tfEstadoHasta.setColumns(10);
        tfEstadoHasta.setBounds(447, 50, 109, 21);
        getContentPane().add(tfEstadoHasta);
        
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
        cbOrden.setModel(new DefaultComboBoxModel(new String[] {"Id", "Modelo", "Marca", "Estado"}));
        cbOrden.setBounds(602, 24, 73, 21);
        getContentPane().add(cbOrden);
        
        JLabel lblCodigo = new JLabel("Codigo");
        lblCodigo.setBounds(10, 11, 73, 15);
        getContentPane().add(lblCodigo);
        
        tfIdDesde = new JTextField();
        tfIdDesde.setColumns(10);
        tfIdDesde.setBounds(10, 25, 62, 20);
        getContentPane().add(tfIdDesde);
        
        tfIdHasta = new JTextField();
        tfIdHasta.setColumns(10);
        tfIdHasta.setBounds(10, 50, 62, 20);
        getContentPane().add(tfIdHasta);

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

    public JTextField getTfModeloDesde() {
        return tfModeloDesde;
    }

    public JTextField getTfModeloHasta() {
        return tfModeloHasta;
    }

    public JTextField getTfMarcaDesde() {
        return tfMarcaDesde;
    }

    public JTextField getTfMarcaHasta() {
        return tfMarcaHasta;
    }

    public JTextField getTfEstadoDesde() {
        return tfEstadoDesde;
    }

    public JTextField getTfEstadoHasta() {
        return tfEstadoHasta;
    }

    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public JButton getBtnProcesar() {
        return btnProcesar;
    }

    public JLabel getLblEstado() {
        return lblEstado;
    }

    public JLabel getLblMarca() {
        return lblMarca;
    }

    public JLabel getLblModelo() {
        return lblModelo;
    }

    public VentanaListadoEquipoController getController() {
        return controller;
    }

    public JComboBox getCbOrden() {
        return cbOrden;
    }

    public JTextField getTfCodigoDesde() {
        return tfIdDesde;
    }

    public JTextField getTfCodigoHasta() {
        return tfIdHasta;
    }
}

