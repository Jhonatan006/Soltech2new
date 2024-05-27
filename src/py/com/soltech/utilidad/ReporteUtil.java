package py.com.soltech.utilidad;

import java.awt.Dialog.ModalExclusionType;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteUtil {

    private static JasperViewer viewer;
/*
    public static void imprimir(List<?> lista, Map<String, Object> map, String nombreReporte) {
        String urlReporte = "/py/com/soltech/jasper/" + nombreReporte + ".jasper";
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(ReporteUtil.class.getResource(urlReporte));
            JasperPrint print = JasperFillManager.fillReport(report, map, dataSource);
            mostrarReporte(print);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    */

    public static void imprimirCompilando(List<?> lista, Map<String, Object> map, String nombreReporte) {
        String urlReporte = "/py/com/soltech/jasper/" + nombreReporte + ".jrxml";
        try (InputStream stream = ReporteUtil.class.getResourceAsStream(urlReporte)) {
            JasperReport report = JasperCompileManager.compileReport(stream);
            JasperPrint print = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(lista));
            mostrarReporte(print);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarReporte(JasperPrint print) {
        viewer = new JasperViewer(print, false);
        viewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        viewer.setVisible(true);
    }
}
