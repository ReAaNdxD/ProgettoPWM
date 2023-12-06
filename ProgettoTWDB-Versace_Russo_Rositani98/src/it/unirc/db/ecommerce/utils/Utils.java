package it.unirc.db.ecommerce.utils;

import java.awt.Component;
import java.awt.Container;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;

public class Utils {

	Connection conn = null;

//	public static void lookAndFeelM() { // Mi imposta la grafica di FlatLight
//		try {
////			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Windows 10 (Grafica del sistema corrente)
//			UIManager.setLookAndFeel(new FlatLightLaf());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public static boolean isEmailValid(String s) { // Mi controlla che l'email sia valida - che rispetti il pattern
													// ?@?.?
		final String regex = "^[^\\W_]+(?:\\.[^\\W_]+)*@(?:[a-zA-Z0-9]+\\.)+[a-zA-Z]{2,6}$";
		// initialize the Pattern object
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	public static boolean isTelNumber(String s) {
		return s != null && s.matches("^\\d{9,10}$");
	}

	public static void removeArrow(Container container) {
		Component[] c = container.getComponents();
		for (Component res : c) {
			if (res instanceof AbstractButton) {
				container.remove(res);
			}
		}
	}

	public static boolean isCreditCard(String s) {
		return s != null && s.matches("^\\d{13,16}$");

	}

	public static boolean isCvv(String s) {
		return s != null && s.matches("^\\d{3}$");
	}

}
