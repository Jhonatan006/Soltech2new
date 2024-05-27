package py.com.soltech.vista.principal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import py.com.soltech.conexion.HibernateUtil;
import py.com.soltech.utilidad.Constants;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SpringLayout;

public class EstilosVista extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	protected int selectedLaf = 6;
	protected ListSelectionListener lafListener = null;
	private JList<String> lafList;
	private JCheckBox macStyleCheckBox;
	private SpringLayout sl_contentPanel;

	public EstilosVista() {
		setAlwaysOnTop(true);
		setModal(true);

		setBounds(100, 100, 400, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			macStyleCheckBox = new JCheckBox("Estilo Mac");
			macStyleCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setLookAndFeel();
				}
			});
			sl_contentPanel = new SpringLayout();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, macStyleCheckBox, 17, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, macStyleCheckBox, -167, SpringLayout.EAST, contentPanel);
			contentPanel.setLayout(sl_contentPanel);
			contentPanel.add(macStyleCheckBox);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			sl_contentPanel.putConstraint(SpringLayout.WEST, macStyleCheckBox, 0, SpringLayout.WEST, scrollPane);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, macStyleCheckBox, -6, SpringLayout.NORTH, scrollPane);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, scrollPane, 46, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, scrollPane, 229, SpringLayout.WEST, contentPanel);
			contentPanel.add(scrollPane);

			lafList = new JList<String>();
			scrollPane.setViewportView(lafList);

			lafList.setModel(new javax.swing.AbstractListModel<String>() {
				String[] strings = { "Acryl", "Aero", "Aluminium", "Bernstein", "Fast", "Graphite", "HiFi", "Luna", "McWin", "Mint", "Noire", "Smart", "Texture" };

				public int getSize() {
					return strings.length;
				}

				public String getElementAt(int i) {
					return strings[i];
				}
			});
			
			lafList.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					lafListMouseClicked(evt);
				}

				public void mousePressed(java.awt.event.MouseEvent evt) {
					lafListMousePressed(evt);
				}
			});
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton exitButton = new JButton("Salir");
				exitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				exitButton.setActionCommand("Exit");
				buttonPane.add(exitButton);
			}
		}

	}

	private void lafListMouseClicked(java.awt.event.MouseEvent evt) {
		cambiarLoockAndFill();
	}

	private void lafListMousePressed(java.awt.event.MouseEvent evt) {
		cambiarLoockAndFill();
	}

	private void cambiarLoockAndFill() {
		lafListener = (ListSelectionEvent e) -> {
			if (!e.getValueIsAdjusting()) {
				if (lafList.getSelectedIndex() != -1) {
					if (selectedLaf != lafList.getSelectedIndex()) {
						selectedLaf = lafList.getSelectedIndex();
						SwingUtilities.invokeLater(() -> {
							setLookAndFeel();
						});
					}
				} else {
					lafList.setSelectedIndex(selectedLaf);
				}
			}
		};
		lafList.addListSelectionListener(lafListener);
	}

	public Properties getLAFProps() {
		Properties props = new Properties();
		props.put("macStyleWindowDecoration", macStyleCheckBox.isSelected() ? "on" : "off");
		return props;
	}

	public void setLookAndFeel() {
		try {
			Properties props = getLAFProps();
			switch (selectedLaf) {
			case Constants.LAF_ACRYL:
				com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
				HibernateUtil.escribirPropiedades(macStyleCheckBox.isSelected() ? "on" : "off",
						"com.jtattoo.plaf.acryl.AcrylLookAndFeel", 0);
				break;

			case Constants.LAF_AERO:
				com.jtattoo.plaf.aero.AeroLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
				HibernateUtil.escribirPropiedades(macStyleCheckBox.isSelected() ? "on" : "off",
						"com.jtattoo.plaf.aero.AeroLookAndFeel", 1);
				break;

			case Constants.LAF_ALUMINIUM:
				com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
				HibernateUtil.escribirPropiedades(macStyleCheckBox.isSelected() ? "on" : "off",
						"com.jtattoo.plaf.aluminium.AluminiumLookAndFeel", 2);
				break;

			case Constants.LAF_BERNSTEIN:
				com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
				HibernateUtil.escribirPropiedades(macStyleCheckBox.isSelected() ? "on" : "off",
						"com.jtattoo.plaf.bernstein.BernsteinLookAndFeel", 3);
				break;

			case Constants.LAF_FAST:
				com.jtattoo.plaf.fast.FastLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
				HibernateUtil.escribirPropiedades(macStyleCheckBox.isSelected() ? "on" : "off",
						"com.jtattoo.plaf.fast.FastLookAndFeel", 4);
				break;

			case Constants.LAF_GRAPHITE:
				com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
				HibernateUtil.escribirPropiedades(macStyleCheckBox.isSelected() ? "on" : "off",
						"com.jtattoo.plaf.graphite.GraphiteLookAndFeel", 5);
				break;

			case Constants.LAF_HIFI:
				com.jtattoo.plaf.hifi.HiFiLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
				HibernateUtil.escribirPropiedades(macStyleCheckBox.isSelected() ? "on" : "off",
						"com.jtattoo.plaf.hifi.HiFiLookAndFeel", 6);
				break;

			case Constants.LAF_LUNA:
				com.jtattoo.plaf.luna.LunaLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
				HibernateUtil.escribirPropiedades(macStyleCheckBox.isSelected() ? "on" : "off",
						"com.jtattoo.plaf.luna.LunaLookAndFeel", 7);
				break;

			case Constants.LAF_MCWIN:
				com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
				HibernateUtil.escribirPropiedades(macStyleCheckBox.isSelected() ? "on" : "off",
						"com.jtattoo.plaf.mcwin.McWinLookAndFeel", 8);
				break;

			case Constants.LAF_MINT:
				com.jtattoo.plaf.mint.MintLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
				HibernateUtil.escribirPropiedades(macStyleCheckBox.isSelected() ? "on" : "off",
						"com.jtattoo.plaf.mint.MintLookAndFeel", 9);
				break;

			case Constants.LAF_NOIRE:
				com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
				HibernateUtil.escribirPropiedades(macStyleCheckBox.isSelected() ? "on" : "off",
						"com.jtattoo.plaf.noire.NoireLookAndFeel", 10);
				break;

			case Constants.LAF_SMART:
				com.jtattoo.plaf.smart.SmartLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
				HibernateUtil.escribirPropiedades(macStyleCheckBox.isSelected() ? "on" : "off",
						"com.jtattoo.plaf.smart.SmartLookAndFeel", 11);
				break;

			case Constants.LAF_TEXTURE:
				com.jtattoo.plaf.texture.TextureLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
				HibernateUtil.escribirPropiedades(macStyleCheckBox.isSelected() ? "on" : "off",
						"com.jtattoo.plaf.texture.TextureLookAndFeel", 12);
				break;
			default:
				break;
			}

			Window windows[] = Window.getWindows();
			for (Window window : windows) {
				if (window.isDisplayable()) {
					SwingUtilities.updateComponentTreeUI(window);
				}
			}

		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException
				| UnsupportedLookAndFeelException ex) {
		}
	}
}
