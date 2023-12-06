package it.unirc.db.ecommerce.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;

public class MetodoSpedizioneDAO {

	private static Connection conn = null;

	private MetodoSpedizione recordToMetodoSpedizione(ResultSet rs) throws SQLException {
		MetodoSpedizione ms = new MetodoSpedizione();
		ms.setIdMetodoSpedizione(rs.getInt("idMetodoSpedizione"));
		ms.setTipo(rs.getString("tipo"));
		ms.setSpeseSpedizione(rs.getDouble("speseSpedizione"));
		return ms;
	}

	public MetodoSpedizione getMetodoSpedizione(MetodoSpedizione ms) {
		String query = "SELECT * FROM metodospedizione WHERE idMetodoSpedizione= ?";
		MetodoSpedizione res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ms.getIdMetodoSpedizione());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToMetodoSpedizione(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean salvaMetodoSpedizione(MetodoSpedizione ms) {
		String query = "INSERT INTO metodoSpedizione VALUES (?,?,?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, ms.getIdMetodoSpedizione());
			ps.setString(2, ms.getTipo());
			ps.setDouble(3, ms.getSpeseSpedizione());
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean eliminaMetodoSpedizione(MetodoSpedizione ms) {
		String query = "DELETE FROM metodoSpedizione WHERE idMetodoSpedizione= ?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, ms.getIdMetodoSpedizione());
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean modificaMetodoSpedizione(MetodoSpedizione ms) {
		String query = "UPDATE metodoSpedizione SET tipo=? , spesespedizione=? WHERE idMetodoSpedizione=?";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, ms.getTipo());
			ps.setDouble(2, ms.getSpeseSpedizione());
			ps.setDouble(3, ms.getIdMetodoSpedizione());
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;

	}

	public Vector<MetodoSpedizione> getAllMetodoSpedizione() {
		String query = "SELECT * FROM metodoSpedizione";
		Vector<MetodoSpedizione> res = new Vector<MetodoSpedizione>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			MetodoSpedizione temp = new MetodoSpedizione();
			while (rs.next()) {
				temp = recordToMetodoSpedizione(rs);
				res.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	

}
