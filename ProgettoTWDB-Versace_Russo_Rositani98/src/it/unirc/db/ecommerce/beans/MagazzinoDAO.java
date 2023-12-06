package it.unirc.db.ecommerce.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;

public class MagazzinoDAO {

	private static Connection conn = null;

	protected Magazzino recordToMagazzino(ResultSet rs) throws SQLException {
		Magazzino magazzino = new Magazzino();
		magazzino.setCodiceMagazzino(rs.getInt("codiceMagazzino"));
		magazzino.setRegione(rs.getString("regione"));
		magazzino.setProvincia(rs.getString("provincia"));
		magazzino.setCitta(rs.getString("citta"));
		magazzino.setVia(rs.getString("via"));
		magazzino.setNcivico(rs.getString("nCivico"));
		magazzino.setCap(rs.getInt("Cap"));
		return magazzino;
	}

	public Magazzino getMagazzino(Magazzino magazzino) {
		String query = "SELECT * FROM magazzino WHERE codiceMagazzino=?;";
		Magazzino res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, magazzino.getCodiceMagazzino());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToMagazzino(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean salvaMagazzino(Magazzino magazzino) {
		String query = "INSERT INTO magazzino VALUES (?,?,?,?,?,?,?)";
		boolean esito = false;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, magazzino.getCodiceMagazzino());
			ps.setString(2, magazzino.getRegione());
			ps.setString(3, magazzino.getProvincia());
			ps.setString(4, magazzino.getCitta());
			ps.setString(5, magazzino.getVia());
			ps.setString(6, magazzino.getNcivico());
			ps.setInt(7, magazzino.getCap());
			int tmp = ps.executeUpdate();
			if (tmp == 1)
				esito = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean eliminaMagazzino(Magazzino magazzino) {
		String query = "DELETE FROM magazzino WHERE codiceMagazzino=?";
		boolean esito = false;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, magazzino.getCodiceMagazzino());
			int tmp = ps.executeUpdate();
			if (tmp == 1)
				esito = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean modificaMagazzino(Magazzino magazzino) {
		String query = "UPDATE magazzino SET regione=?, provincia=?, citta=?, via=?, nCivico=?, cap=? WHERE codiceMagazzino=?";
		boolean esito = false;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, magazzino.getRegione());
			ps.setString(2, magazzino.getProvincia());
			ps.setString(3, magazzino.getCitta());
			ps.setString(4, magazzino.getVia());
			ps.setString(5, magazzino.getNcivico());
			ps.setInt(6, magazzino.getCap());
			ps.setInt(7, magazzino.getCodiceMagazzino());
			int tmp = ps.executeUpdate();
			if (tmp == 1)
				esito = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public Vector<Magazzino> getAll() {
		String query = "SELECT * FROM magazzino;";
		Vector<Magazzino> res = new Vector<Magazzino>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Magazzino temp = new Magazzino();
			while (rs.next()) {
				temp = recordToMagazzino(rs);
				res.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
}
