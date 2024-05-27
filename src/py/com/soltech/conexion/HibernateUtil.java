package py.com.soltech.conexion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import py.com.soltech.modelo.entidades.Cliente;
import py.com.soltech.modelo.entidades.Equipo;
import py.com.soltech.modelo.entidades.Funcionario;
import py.com.soltech.modelo.entidades.OrdenServicio;
import py.com.soltech.modelo.entidades.PrestacionServicio;
import py.com.soltech.modelo.entidades.ServicioDetalle;

public class HibernateUtil {

	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	private static final String SCHEMA = "public";
	private static final String BASE = "soltech";
	private static final String PASS = "1234";

	public static final String FILE = "config.properties";
	public static String route;
	public static String macStyle;
	public static String lookAndFeel;
	public static int selectedLaf;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {

				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				Map<String, Object> settings = new HashMap<>();
				settings.put(Environment.DRIVER, "org.postgresql.Driver");
				settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/" + BASE);
				settings.put(Environment.USER, "postgres");
				settings.put(Environment.PASS, PASS);
				settings.put(Environment.DEFAULT_SCHEMA, SCHEMA);
				settings.put(Environment.HBM2DDL_AUTO, "update");
				settings.put(Environment.SHOW_SQL, true);

				// c3p0 configuration
				settings.put(Environment.C3P0_MIN_SIZE, 5); // Minimum size of pool
				settings.put(Environment.C3P0_MAX_SIZE, 20); // Maximum size of pool
				settings.put(Environment.C3P0_ACQUIRE_INCREMENT, 1);// Number of connections acquired at a time when
																	// pool is exhausted
				settings.put(Environment.C3P0_TIMEOUT, 1800); // Connection idle time
				settings.put(Environment.C3P0_MAX_STATEMENTS, 150); // PreparedStatement cache size

				settings.put(Environment.C3P0_CONFIG_PREFIX + ".initialPoolSize", 5);
				System.out.println("cp30 configurada");
				System.out.println("esquema " + SCHEMA);
				registryBuilder.applySettings(settings);

				registry = registryBuilder.build();
				MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(Cliente.class)
						.addAnnotatedClass(Equipo.class).addAnnotatedClass(Funcionario.class)
						.addAnnotatedClass(OrdenServicio.class).addAnnotatedClass(PrestacionServicio.class)
						.addAnnotatedClass(ServicioDetalle.class);

				Metadata metadata = sources.getMetadataBuilder().build();
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				System.out.println(e);
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	private static void detectarSistemaOperativo() {
		String sistemaOperativo = System.getProperty("os.name");

		if (sistemaOperativo.contains("Windows")) {
			route = "C:\\Soltech\\";
		} else if (sistemaOperativo.contains("Linux")) {
			route = "/opt/soltech/";
		} else if (sistemaOperativo.contains("Mac")) {
			System.out.println("Estas ejecutando la aplicaci�n en macOS.");
		} else {
			System.out.println("Sistema operativo no identificado: " + sistemaOperativo);
		}
	}

	public static String ubicacionEjecutable(String slash) {
		String ubicacionEjecutable = System.getProperty("user.dir");
		return ubicacionEjecutable + slash;
	}

	private static void crearCarpeta() {
        File carpeta = new File(route);
        if (!carpeta.exists()) {
            if (carpeta.mkdirs()) {
                System.out.println("Carpeta creada");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo crear la carpeta");
            }
        }
    }

    private static void crearArchivoProperties() {
        try {
            File file = new File(route + FILE);
            if (!file.exists()) {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                String content = "";
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(content);
                    escribirPropiedades("on", "com.jtattoo.plaf.hifi.HiFiLookAndFeel", 0);
                }
                System.out.println("Archivo de propiedades creado");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear archivo de propiedades" + e.getMessage());
        }
    }

    public static void escribirPropiedades(String mac, String look, int selectedLaf) {
		Properties properties = new Properties();
		FileInputStream archivoEntrada = null;
		FileOutputStream archivoSalida = null;

		try {
			archivoEntrada = new FileInputStream(route + FILE);
			properties.load(archivoEntrada);
			properties.setProperty("conf.macstyle", mac);
			properties.setProperty("conf.lookandfeel", look);
			properties.setProperty("conf.selectedLaf", selectedLaf+"");
			archivoSalida = new FileOutputStream(route + FILE);
			properties.store(archivoSalida, null);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se pudo crear el archivo config.properties:\n" + e);
		} finally {
			try {
				if (archivoEntrada != null) {
					archivoEntrada.close();
				}
				if (archivoSalida != null) {
					archivoSalida.close();
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "No se pudo finalizar el archivo config.properties:\n" + e);
			}
		}
	}

    public static void recuperarPropiedades() {
        detectarSistemaOperativo();
        crearCarpeta();
        crearArchivoProperties();
        try (InputStream read = new FileInputStream(route + FILE)) {
            Properties properties = new Properties();
            properties.load(read);
                macStyle = properties.getProperty("conf.macstyle");
                lookAndFeel = properties.getProperty("conf.lookandfeel");
                selectedLaf = Integer.parseInt(properties.getProperty("conf.selectedLaf"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ocurri� un error: " + ex.getMessage());
        }
    }

}
