package py.com.soltech.vistas.transaccion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Buscador2 extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfBusar;
	private JTable table;
	private JButton btnBuscar;
	private JScrollPane scrollPane;

	public Buscador2() {
		setBounds(100, 100, 540, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		scrollPane = new JScrollPane();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, scrollPane, 15, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, scrollPane, 15, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, scrollPane, -15, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, scrollPane, -15, SpringLayout.EAST, contentPanel);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.NORTH);
			{
				tfBusar = new JTextField();
				buttonPane.add(tfBusar);
				tfBusar.setColumns(30);
			}
			{
				btnBuscar = new JButton("Buscar");
				btnBuscar.setActionCommand("Cancel");
				buttonPane.add(btnBuscar);
			}
		}
	}

	public JTextField getTftBuscar() {
		return tfBusar;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	
	public void addBuscarRegistroListener(ActionListener listener) {
    	btnBuscar.addActionListener(listener);
    }

    
	
	
}
