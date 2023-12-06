package it.unirc.db.ecommerce.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;

public class CarrelloDAO {

	private static Connection conn = null;

	private Carrello recordToCarrello(ResultSet rs) throws SQLException {
		Carrello carrello = new Carrello();
		carrello.setIdCarrello(rs.getInt("idCarrello"));
		carrello.setAttivo(rs.getBoolean("attivo"));
		carrello.setIdCliente(rs.getInt("idCliente"));
		return carrello;
	}

	public boolean salva(Carrello carrello) {
		System.out.println(carrello);
		String query = "INSERT INTO carrello (attivo, idCliente) VALUES (?,?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setBoolean(1, true);
			ps.setInt(2, carrello.getIdCliente());
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

	public Carrello get(Carrello carrello) {
		String query = "SELECT * FROM carrello WHERE idCarrello = ?";
		Carrello res = new Carrello();

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, carrello.getIdCarrello());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToCarrello(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Carrello getCarrelloByCliente(Cliente cliente) {
		String query = "SELECT * FROM carrello WHERE idCliente = ? AND attivo=1";
		Carrello res = new Carrello();

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getIdCliente());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToCarrello(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Carrello getById(int carrello) {
		String query = "SELECT * FROM carrello WHERE idCarrello = ?";
		Carrello res = new Carrello();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, carrello);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToCarrello(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean elimina(Carrello carrello) {
		String query = "DELETE FROM carrello WHERE idCarrello = ?";
		boolean esito = false;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, carrello.getIdCarrello());
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

	public boolean modifica(Carrello carrello) {
		String query = "UPDATE carrello SET attivo=?, idCliente=? WHERE idCarrello=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setBoolean(1, carrello.getAttivo());
			ps.setInt(2, carrello.getIdCliente());
			ps.setInt(3, carrello.getIdCarrello());
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

	public boolean modificaStato(Carrello carrello) {
		String query = "UPDATE carrello SET attivo=0 WHERE idCarrello=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, carrello.getIdCarrello());
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

	public Vector<Carrello> getAll() {
		String query = "SELECT * FROM carrello WHERE attivo=true";
		Vector<Carrello> res = new Vector<Carrello>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Carrello carrello = new Carrello();
			while (rs.next()) {
				carrello = recordToCarrello(rs);
				res.add(carrello);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean isCartFull(Ordine ordine) {
		String query = "SELECT * " + "FROM compone WHERE idCarrello= ?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, ordine.getIdCarrello());
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

	public Carrello getAttivo(int cliente) {
		String query = "SELECT * FROM carrello WHERE idCliente = ? and attivo=1";
		Carrello res = new Carrello();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, cliente);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToCarrello(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Carrello getUltimoAcquisto(int cliente) {
		String query = "SELECT * FROM carrello WHERE idCliente = ? and attivo=false ORDER BY idCarrello desc ";
		Carrello res = new Carrello();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, cliente);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToCarrello(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

}
