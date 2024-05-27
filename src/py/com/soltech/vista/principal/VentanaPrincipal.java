package py.com.soltech.vista.principal;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import py.com.soltech.conexion.HibernateUtil;
import py.com.soltech.controladores.VentanaClienteController;
import py.com.soltech.controladores.VentanaClienteController;
import py.com.soltech.vistas.abms.VentanaCliente;
import py.com.soltech.vistas.abms.VentanaCliente;
import py.com.soltech.vistas.abms.VentanaEquipo;
import py.com.soltech.vistas.abms.VentanaEquipo;
import py.com.soltech.vistas.abms.VentanaFuncionario;
import py.com.soltech.vistas.abms.VentanaOrdenServicio;
import py.com.soltech.vistas.transaccion.VentanaPrestacion;
import py.com.soltech.controladores.VentanaEquipoController;
import py.com.soltech.controladores.VentanaEquipoController;
import py.com.soltech.controladores.VentanaFuncionarioController;
import py.com.soltech.controladores.VentanaListadoClienteController;
import py.com.soltech.controladores.VentanaListadoEquipoController;
import py.com.soltech.controladores.VentanaListadoFuncionarioController;
import py.com.soltech.controladores.VentanaListadoPresentacionServicioController;
import py.com.soltech.controladores.VentanaOrdenServicioController;
import py.com.soltech.controladores.VentanaPrestacionController;
import py.com.soltech.reportes.VentanaListadoCliente;
import py.com.soltech.reportes.VentanaListadoEquipo;
import py.com.soltech.reportes.VentanaListadoFuncionario;
import py.com.soltech.reportes.VentanaListadoPrestacionServicio;
import py.com.soltech.utilidad.Constants;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static String mac;
    private static String look;
    private static int selectedLaf;
	private JPanel contentPane;

	public static void main(String[] args) {
		
		try {
			 HibernateUtil.recuperarPropiedades();
	            mac = HibernateUtil.macStyle;
	            look = HibernateUtil.lookAndFeel;
	            selectedLaf = HibernateUtil.selectedLaf;
	            setLookAndFeel();			
			new VentanaPrincipal().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public VentanaPrincipal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//int opcion = JOptionPane.showConfirmDialog(null, "Desea salir del sistema?", "Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			//	if (opcion != JOptionPane.NO_OPTION) {
					
					System.exit(0);
					
			//	}
			}
		});
		//para que la ventana agarra la pantalla completa.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMaximumSize(new Dimension(1080, 720));
		//setExtendedState(MAXIMIZED_BOTH);
		
		
		HibernateUtil.recuperarPropiedades();
//		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/py/com/soltech/img/icono.png")));
		setTitle("SolTech-Ventana Principal ");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1126, 701);
		//centrar la ventana principal de SolTech (setLocationRelativeTo(this)).
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 0, 1104, 21);
		menuBar.setBackground(Color.LIGHT_GRAY);
		contentPane.add(menuBar);
		
		JMenu mnCliente = new JMenu("Registros");
		menuBar.add(mnCliente);
		
		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirABMCliente();
			}
		});
		mnCliente.add(mntmCliente);
		
		JMenuItem mntmEquipo = new JMenuItem("Equipo");
		mntmEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirABMEquipos();
			}
		});
		mnCliente.add(mntmEquipo);
		
		JMenuItem mntmFuncionario = new JMenuItem("Funcionario");
		mntmFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirABMFuncionarios();
			}
		});
		mnCliente.add(mntmFuncionario);
		
		JMenuItem mntmOrdendelServicio = new JMenuItem("Orden del Servicio");
		mntmOrdendelServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirABMOrdenServicio();
			}
		});
		mnCliente.add(mntmOrdendelServicio);
		
		JMenu mnTransaccion = new JMenu("Transaccion");
		menuBar.add(mnTransaccion);
		
		JMenuItem mntmPrestacionDeServicios = new JMenuItem("Prestacion de Servicios ");
		mntmPrestacionDeServicios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirPrestacion();
				
			}
		});
		mnTransaccion.add(mntmPrestacionDeServicios);
		
		JMenu mnInformes = new JMenu("Informes");
		menuBar.add(mnInformes);
		
		JMenuItem mntmCliente_Informe = new JMenuItem("Cliente");
		mntmCliente_Informe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirInformeCliente();
			}
		});
		mnInformes.add(mntmCliente_Informe);
		
		JMenuItem mntmEquipo_Informe = new JMenuItem("Equipo");
		mntmEquipo_Informe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirInformeEquipo();
			}
		});
		mnInformes.add(mntmEquipo_Informe);
		
		JMenuItem mntmFuncionario_Informe = new JMenuItem("Funcionario");
		mntmFuncionario_Informe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirInformeFuncionario();
			}
		});
		mnInformes.add(mntmFuncionario_Informe);
		
		JMenuItem mntmOrdenServicio_informe = new JMenuItem("Prestacion de Servicios");
		mntmOrdenServicio_informe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirInformePrestacionServicio();
			}
		});
		mnInformes.add(mntmOrdenServicio_informe);
		
		JMenu mnConfiguracion = new JMenu("Configuracion");
		menuBar.add(mnConfiguracion);
		
		JMenuItem mntmEstilos = new JMenuItem("Estilos");
		mntmEstilos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EstilosVista ventana = new EstilosVista();
				ventana.setLocationRelativeTo(VentanaPrincipal.this);
				ventana.setVisible(true);
			}
		});
		mnConfiguracion.add(mntmEstilos);
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirABMCliente();
			}
		});
		btnCliente.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnCliente.setBounds(10, 89, 162, 37);
		contentPane.add(btnCliente);
		
		JButton btnEquipo = new JButton("Equipo");
		btnEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirABMEquipos();
			}
		});
		btnEquipo.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnEquipo.setBounds(10, 137, 162, 37);
		contentPane.add(btnEquipo);
		
		JButton btnDetalleServicio = new JButton("Detalle del Servicio");
		btnDetalleServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirPrestacion();
			}
		});
		btnDetalleServicio.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnDetalleServicio.setBounds(10, 235, 162, 37);
		contentPane.add(btnDetalleServicio);
		
		JButton btnOrdenDeServicio = new JButton("Orden de Servicio");
		btnOrdenDeServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirABMOrdenServicio();
			}
		});
		btnOrdenDeServicio.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnOrdenDeServicio.setBounds(10, 186, 162, 37);
		contentPane.add(btnOrdenDeServicio);
		
		JLabel lblLucasFlorentin = new JLabel("Lucas Florentin - Jhonatan Gonzalez");
		lblLucasFlorentin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblLucasFlorentin.setBounds(26, 637, 290, 14);
		contentPane.add(lblLucasFlorentin);
		
		JLabel lblVersion = new JLabel("Version: 3");
		lblVersion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblVersion.setBounds(111, 619, 78, 14);
		contentPane.add(lblVersion);
		
		JLabel label = new JLabel(Constants.URLICON + "soporte1");
		
		label.setBounds(605, 215, 799, 555);
		
		ImageIcon ico = new ImageIcon(getClass().getResource(Constants.URLICON + "klipartz.com.png"));
		ImageIcon img = new ImageIcon(ico.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		
		label.setIcon(img);
		contentPane.add(label);
		
	}
	
	private void abrirABMCliente() {
		//JOptionPane.showMessageDialog(null, "hola");
		VentanaCliente ventana  = new VentanaCliente();
		VentanaClienteController controller = new VentanaClienteController(ventana);
    	controller.estadoInicial(true, 0);
		ventana.setLocationRelativeTo(VentanaPrincipal.this);
		ventana.setTitle("Registro de clientes");
		ventana.setVisible(true);
	}
	
	private void abrirABMFuncionarios() {
		VentanaFuncionario funcionario  = new VentanaFuncionario();
		VentanaFuncionarioController controller = new VentanaFuncionarioController(funcionario);
		funcionario.setLocationRelativeTo(VentanaPrincipal.this);
		funcionario.setTitle("Registro de Funcionarios");
		funcionario.setVisible(true);
	}
	
	
	
	private void abrirABMEquipos() {
		VentanaEquipo ventana  = new VentanaEquipo();
		VentanaEquipoController controller = new VentanaEquipoController(ventana);
		controller.estadoInicial(true, 0);
		ventana.setLocationRelativeTo(VentanaPrincipal.this);
		ventana.setTitle("Registro de Equipos");
		ventana.setVisible(true);
		
	}

	private void abrirABMOrdenServicio() {
		VentanaOrdenServicio ventana  = new VentanaOrdenServicio();
		VentanaOrdenServicioController controller = new VentanaOrdenServicioController(ventana);
		ventana.setLocationRelativeTo(VentanaPrincipal.this);
		ventana.setTitle("Orden de servicio");
		ventana.setVisible(true);
	}
	
	private void abrirPrestacion() {
		VentanaPrestacion ventana = new VentanaPrestacion();
		VentanaPrestacionController controller = new VentanaPrestacionController(ventana);
		ventana.setLocationRelativeTo(VentanaPrincipal.this);
		ventana.setTitle("Prestación de servicio");
		ventana.setVisible(true);
	}
	
	private void abrirInformeCliente() {
		VentanaListadoCliente ventana = new VentanaListadoCliente();
		VentanaListadoClienteController controller = new VentanaListadoClienteController(ventana);
		ventana.setLocationRelativeTo(VentanaPrincipal.this);
		ventana.setTitle("Listado de Clientes");
		ventana.setVisible(true);
	}
	
	private void abrirInformeEquipo() {
		VentanaListadoEquipo ventana = new VentanaListadoEquipo();
		VentanaListadoEquipoController controller = new VentanaListadoEquipoController(ventana);
		ventana.setLocationRelativeTo(VentanaPrincipal.this);
		ventana.setTitle("Listado de Equipo");
		ventana.setVisible(true);
	}
	
	private void abrirInformeFuncionario() {
		VentanaListadoFuncionario ventana = new VentanaListadoFuncionario();
		VentanaListadoFuncionarioController controller = new VentanaListadoFuncionarioController(ventana);
		ventana.setLocationRelativeTo(VentanaPrincipal.this);
		ventana.setTitle("Listado de Funcionario");
		ventana.setVisible(true);
	}
	
	private void abrirInformePrestacionServicio() {
		VentanaListadoPrestacionServicio ventana = new VentanaListadoPrestacionServicio();
		VentanaListadoPresentacionServicioController controller = new VentanaListadoPresentacionServicioController(ventana);
		ventana.setLocationRelativeTo(VentanaPrincipal.this);
		ventana.setTitle("Listado de Prestacion de Servicios");
		ventana.setVisible(true);
	}
	
	public static Properties getLAFProps() {
		Properties props = new Properties();
		props.put("macStyleWindowDecoration", mac);
		return props;
	}

	private static void setLookAndFeel() {
		try {
			Properties props = getLAFProps();
			switch (selectedLaf) {
			case Constants.LAF_ACRYL:
				com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
				break;

			case Constants.LAF_AERO:
				com.jtattoo.plaf.aero.AeroLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
				break;

			case Constants.LAF_ALUMINIUM:
				com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
				break;

			case Constants.LAF_BERNSTEIN:
				com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
				break;

			case Constants.LAF_FAST:
				com.jtattoo.plaf.fast.FastLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
				break;

			case Constants.LAF_GRAPHITE:
				com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
				break;

			case Constants.LAF_HIFI:
				com.jtattoo.plaf.hifi.HiFiLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
				break;

			case Constants.LAF_LUNA:
				com.jtattoo.plaf.luna.LunaLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
				break;
			case Constants.LAF_MCWIN:
				com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
				break;

			case Constants.LAF_MINT:
				com.jtattoo.plaf.mint.MintLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
				break;

			case Constants.LAF_NOIRE:
				com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
				break;

			case Constants.LAF_SMART:
				com.jtattoo.plaf.smart.SmartLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
				break;

			case Constants.LAF_TEXTURE:
				com.jtattoo.plaf.texture.TextureLookAndFeel.setTheme(props);
				UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
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
