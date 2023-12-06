package it.unirc.db.ecommerce.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;

public class IndirizzoSpedizioneDAO {

	private static Connection conn = null;

	private IndirizzoSpedizione recordToIndirizzoSpedizione(ResultSet rs) throws SQLException {
		IndirizzoSpedizione is = new IndirizzoSpedizione();
		is.setIdIndirizzoSpedizione(rs.getInt("idIndirizzoSpedizione"));
		is.setPreferito(rs.getBoolean("preferito"));
		is.setRegione(rs.getString("regione"));
		is.setProvincia(rs.getString("provincia"));
		is.setCitta(rs.getString("citta"));
		is.setVia(rs.getString("via"));
		is.setNCivico(rs.getString("nCivico"));
		is.setCap(rs.getInt("cap"));
		is.setTelefono(rs.getBigDecimal("telefono"));
		is.setIdCliente(rs.getInt("idCliente")); // idCliente non può essere null
		return is;
	}

	public IndirizzoSpedizione get(IndirizzoSpedizione is) {
		String query = "SELECT * FROM indirizzospedizione WHERE idIndirizzoSpedizione= ?";
		IndirizzoSpedizione res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, is.getIdIndirizzoSpedizione());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToIndirizzoSpedizione(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public IndirizzoSpedizione getIndirizzoByCliente(IndirizzoSpedizione is) {
		String query = "SELECT * FROM indirizzospedizione WHERE idCliente=? ORDER BY idIndirizzoSpedizione DESC";
		IndirizzoSpedizione res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, is.getIdCliente());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToIndirizzoSpedizione(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean salva(IndirizzoSpedizione is) {
		String query = "INSERT INTO indirizzospedizione (preferito, regione, provincia, citta, via, nCivico, cap, telefono, idCliente) VALUES (?,?,?,?,?,?,?,?,?)";
		boolean esito = false;
		boolean b = nonCiSonoIndirizzi(is.getIdCliente());
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setBoolean(1, b);
			ps.setString(2, is.getRegione());
			ps.setString(3, is.getProvincia());
			ps.setString(4, is.getCitta());
			ps.setString(5, is.getVia());
			ps.setString(6, is.getNCivico());
			ps.setInt(7, is.getCap());
			ps.setBigDecimal(8, is.getTelefono());
			ps.setInt(9, is.getIdCliente());
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

	public boolean nonCiSonoIndirizzi(int idCliente) {
		String query = "SELECT * FROM indirizzoSpedizione WHERE idCliente=? ";
		Vector<IndirizzoSpedizione> res = new Vector<IndirizzoSpedizione>();
		boolean preferito = false;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, idCliente);
			ResultSet rs = ps.executeQuery();
			IndirizzoSpedizione temp = new IndirizzoSpedizione();
			while (rs.next()) {
				temp = recordToIndirizzoSpedizione(rs);
				res.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		if (res.size() == 0) // Se non ci sono indirizzi, il nuovo indirizzo in automatico sarà il preferito
			preferito = true;
		return preferito;
	}

	public boolean elimina(IndirizzoSpedizione is) {
		String query = "DELETE FROM indirizzoSpedizione WHERE idIndirizzoSpedizione= ?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, is.getIdIndirizzoSpedizione());
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

	public boolean modifica(IndirizzoSpedizione is) {
		String query = "UPDATE indirizzoSpedizione SET regione=?, provincia=?, citta=?, via=?, ncivico=?, cap=?, telefono=?, idCliente=? WHERE idIndirizzoSpedizione=?";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, is.getRegione());
			ps.setString(2, is.getProvincia());
			ps.setString(3, is.getCitta());
			ps.setString(4, is.getVia());
			ps.setString(5, is.getNCivico());
			ps.setInt(6, is.getCap());
			ps.setBigDecimal(7, is.getTelefono());
			ps.setInt(8, is.getIdCliente());
			ps.setInt(9, is.getIdIndirizzoSpedizione());
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

	public Vector<IndirizzoSpedizione> getAll(Cliente cliente) {
		String query = "SELECT * FROM indirizzoSpedizione WHERE idCliente=?";
		Vector<IndirizzoSpedizione> res = new Vector<IndirizzoSpedizione>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getIdCliente());
			ResultSet rs = ps.executeQuery();
			IndirizzoSpedizione temp = new IndirizzoSpedizione();
			while (rs.next()) {
				temp = recordToIndirizzoSpedizione(rs);
				res.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean hasIndirizzi(Cliente cliente) {
		String query = "SELECT * FROM indirizzoSpedizione WHERE idCliente=?";
		boolean esito = false;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getIdCliente());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				esito = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean modificaPreferito(IndirizzoSpedizione indirizzo) {
		System.out.println(indirizzo);
		if (!eliminaPreferito(indirizzo.getIdCliente()))
			return false;
		String query = "UPDATE indirizzospedizione set preferito=1 WHERE idIndirizzoSpedizione=? AND idCliente=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, indirizzo.getIdIndirizzoSpedizione());
			ps.setInt(2, indirizzo.getIdCliente());
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

	public boolean eliminaPreferito(int idCliente) {
		String query = "UPDATE indirizzospedizione set preferito=0 WHERE preferito=1 AND idCliente=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, idCliente);
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		System.out.println(esito);
		return esito;
	}

	public IndirizzoSpedizione getIndirizzoPreferito(Cliente cl) {
		String query = "SELECT * FROM indirizzospedizione WHERE idCliente=? AND preferito=1";
		IndirizzoSpedizione res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cl.getIdCliente());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToIndirizzoSpedizione(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public IndirizzoSpedizione getById(int is) {
		String query = "SELECT * FROM indirizzospedizione WHERE idIndirizzoSpedizione= ?";
		IndirizzoSpedizione res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, is);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToIndirizzoSpedizione(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

}
