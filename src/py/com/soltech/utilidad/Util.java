package py.com.soltech.utilidad;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;

public class Util {
	public java.awt.event.KeyEvent soloMayusculas(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
        return evt;
    }

	public java.awt.event.KeyEvent soloNumeros(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
        }
        return evt;
    }

    public String aplicarFormatoNumerico(Double valor) {
    	DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator(',');
        simbolo.setGroupingSeparator('.');
        DecimalFormat formato = new DecimalFormat("###,###", simbolo);

        return formato.format(valor);
    }

    public double removerFormatoNumerico(String valor) {

    	DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator(',');
        simbolo.setGroupingSeparator('.');
        DecimalFormat formato = new DecimalFormat("###,###.###", simbolo);
        Number numero = 0;
        try {
            numero = formato.parse(valor);
        } catch (ParseException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numero.doubleValue();
    }
    
    
}
