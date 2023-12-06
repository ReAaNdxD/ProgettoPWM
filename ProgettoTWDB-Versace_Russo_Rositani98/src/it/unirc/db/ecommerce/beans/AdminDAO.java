package it.unirc.db.ecommerce.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unirc.db.ecommerce.utils.DBManager;

public class AdminDAO {
	private static Connection conn;

	private Admin recordToArticolo(ResultSet rs) throws SQLException {
		Admin admin= new Admin();
		admin.setNomeAdmin(rs.getString("nomeAdmin"));
		admin.setPasswordAdmin(rs.getString("password"));
		return admin;
	}
	public boolean salva(Admin admin) {
		String query = "INSERT INTO admin (nomeAdmin, password) VALUES (?,?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, admin.getNomeAdmin());
			ps.setString(2, admin.getPasswordAdmin());

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
	public Admin login(Admin admin) {
		String query = "SELECT * FROM admin WHERE nomeAdmin = ? and PASSWORD=?";
		Admin res = null;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, admin.getNomeAdmin());
			ps.setString(2, admin.getPasswordAdmin());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToArticolo(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

}
