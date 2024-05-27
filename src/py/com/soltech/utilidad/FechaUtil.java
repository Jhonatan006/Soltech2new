package py.com.soltech.utilidad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.MaskFormatter;

public class FechaUtil {
	private static final SimpleDateFormat FORMATO = 
											new SimpleDateFormat("dd/MM/yyyy");
	private static MaskFormatter mascara;
	
	public static MaskFormatter getMascara() {
		if(mascara == null){
			try {
				mascara = new MaskFormatter("##/##/####");
				mascara.setPlaceholderCharacter('_');
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mascara;
	}
	
	public static Date convertirStringADate(String s){
		FORMATO.setLenient(false);
		try {
			return FORMATO.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String convertirDateAString(Date d){
		if(d == null) return null;
		else return FORMATO.format(d);
	}
}







