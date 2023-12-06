package it.unirc.db.ecommerce.beans.join;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import it.unirc.db.ecommerce.beans.Articolo;
import it.unirc.db.ecommerce.beans.ArticoloDAO;
import it.unirc.db.ecommerce.beans.Magazzino;
import it.unirc.db.ecommerce.utils.DBManager;
import it.unirc.db.ecommerce.views.ViewDispone;

public class ArticoloDisponeMagazzinoDAO {

	private static Connection conn;

	public boolean salva(Articolo articolo, Magazzino magazzino, int quantita) {
		System.out.println("ohsdiohgsief");
		String query = "INSERT INTO dispone VALUES(?,?,?)";
		boolean esito = false;
		if (get(articolo, magazzino)) {
			rifornisci(articolo, magazzino, quantita);
			return true;
		}
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, articolo.getIdArticolo());
			ps.setInt(2, magazzino.getCodiceMagazzino());
			ps.setInt(3, quantita);
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		ArticoloDAO articoloDAO = new ArticoloDAO();
//		articoloDAO.vincoloA1(articolo);
		if (articoloDAO.aggiornaQuantita(articolo, articoloDAO.vincoloA1(articolo))) {
			esito = true;
		} else {
			esito = false;
		}
		return esito;
	}

	public boolean get(Articolo articolo, Magazzino magazzino) {
		String query = "SELECT * FROM dispone WHERE idArticolo=? AND codiceMagazzino=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, articolo.getIdArticolo());
			ps.setInt(2, magazzino.getCodiceMagazzino());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				esito = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public Vector<ViewDispone> getAll() {
		String query = "SELECT * FROM dispone";
		Vector<ViewDispone> dispone = new Vector<ViewDispone>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ViewDispone a = new ViewDispone();
				a.setArticolo(new Articolo(rs.getInt("idArticolo")));
				a.setMagazzino(new Magazzino(rs.getInt("codiceMagazzino")));
				a.setQuantita(rs.getInt("quantita"));
				dispone.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return dispone;
	}

	public boolean eliminaArticoloMagazzino(Articolo articolo, Magazzino magazzino) {
		String query = "DELETE FROM dispone WHERE idArticolo=? AND codiceMagazzino=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, articolo.getIdArticolo());
			ps.setInt(2, magazzino.getCodiceMagazzino());
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean rifornisci(Articolo articolo, Magazzino magazzino, int quantita) {
		String query = "UPDATE dispone SET quantita= (quantita+?) WHERE idArticolo=? AND codiceMagazzino=?";
		boolean esito = false;
		if (quantita == 0) {
			return false;
		}
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, quantita);
			ps.setInt(2, articolo.getIdArticolo());
			ps.setInt(3, magazzino.getCodiceMagazzino());
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				ArticoloDAO articoloDAO = new ArticoloDAO();
				if (articoloDAO.aggiornaQuantita(articolo, articoloDAO.vincoloA1(articolo))) {
					esito = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		ArticoloDAO articoloDAO = new ArticoloDAO();
		articoloDAO.vincoloA1(articolo);
		return esito;
	}

}
