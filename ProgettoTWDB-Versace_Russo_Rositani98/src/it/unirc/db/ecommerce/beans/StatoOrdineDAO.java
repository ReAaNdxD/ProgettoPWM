package it.unirc.db.ecommerce.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;

public class StatoOrdineDAO {

	private static Connection conn = null;

	private StatoOrdine recordToStatoOrdine(ResultSet rs) throws SQLException {
		StatoOrdine so = new StatoOrdine();
		so.setIdStatoOrdine(rs.getInt("idStatoOrdine"));
		so.setNome(rs.getString("nome"));
		return so;
	}

	public boolean salvaStatoOrdine(StatoOrdine so) {
		String query = "INSERT INTO statoOrdine VALUES (?,?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, so.getIdStatoOrdine());
			ps.setString(2, so.getNome());
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

	public boolean eliminaStatoOrdine(StatoOrdine so) {
		String query = "DELETE FROM statoOrdine WHERE idStatoOrdine = ?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, so.getIdStatoOrdine());
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

	public boolean modificaStatoOrdine(StatoOrdine so) {
		String query = "UPDATE statoOrdine SET nome=? WHERE idStatoOrdine=?";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, so.getNome());
			ps.setInt(2, so.getIdStatoOrdine());
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

	public Vector<StatoOrdine> getAllStatoOrdine() {
		String query = "SELECT * FROM statoOrdine";
		Vector<StatoOrdine> res = new Vector<StatoOrdine>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			StatoOrdine temp = new StatoOrdine();
			while (rs.next()) {
				temp = recordToStatoOrdine(rs);
				res.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<StatoOrdine> getAllStatiPossibili(StatoOrdine so) {
		System.out.println("Sono qui");
		Vector<StatoOrdine> res = new Vector<StatoOrdine>();
		PreparedStatement ps;
		String stato = so.getNome();
		conn = DBManager.startConnection();
		if (stato.contentEquals("SospesoPerPagamento")) {
			String query = "SELECT * \r\n" + 
					"FROM statoOrdine so \r\n" + 
					"WHERE so.nome=\"Fallito\" or so.nome=\"Confermato\" or so.nome=\"Annullato\"";
			try {
				ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					StatoOrdine temp = new StatoOrdine();
					temp = recordToStatoOrdine(rs);
					res.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (stato.contentEquals("Fallito")) {
			String query = "SELECT * FROM statoOrdine so "
					+ "WHERE so.nome=\"Confermato\" or so.nome=\"Annullato\" ";
			try {
				ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					StatoOrdine temp = new StatoOrdine();
					temp = recordToStatoOrdine(rs);
					res.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if (stato.contentEquals("Pagato")) {
			String query = "SELECT * FROM statoOrdine so "
					+ "WHERE so.nome=\"In Elaborazione\" or so.nome=\"Annullato\" ";
			try {
				ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					StatoOrdine temp = new StatoOrdine();
					temp = recordToStatoOrdine(rs);
					res.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (stato.contentEquals("Confermato")) {
			String query = "SELECT * FROM statoOrdine so " + "WHERE so.nome=\"Pagato\" or so.nome=\"Annullato\" ";
			try {
				ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					StatoOrdine temp = new StatoOrdine();
					temp = recordToStatoOrdine(rs);
					res.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (stato.contentEquals("In Elaborazione")) {
			String query = "SELECT * FROM statoOrdine so " + "WHERE so.nome=\"Annullato\" ";
			try {
				ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					StatoOrdine temp = new StatoOrdine();
					temp = recordToStatoOrdine(rs);
					res.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (stato.contentEquals("Spedito")) {
			String query = "SELECT * FROM statoOrdine so " + "WHERE so.nome=\"Consegnato\" ";
			try {
				ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					StatoOrdine temp = new StatoOrdine();
					temp = recordToStatoOrdine(rs);
					res.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Res "+res);
		DBManager.closeConnection();
		return res;
	}

	public StatoOrdine getStatoOrdine(StatoOrdine so) {
		String query = "SELECT * FROM statoordine WHERE idStatoOrdine = ?";
		StatoOrdine res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, so.getIdStatoOrdine());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToStatoOrdine(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public int vincoloSO1() {
		String query = "SELECT s.idStatoOrdine FROM statoordine s WHERE s.nome='annullato'";
		int id = 0;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("idStatoOrdine");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return id;
	}

	public int vincoloSO2() {
		String query = "SELECT s.idStatoOrdine FROM statoordine s WHERE s.nome='SospesoPerPagamento'";
		int id = 0;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("idStatoOrdine");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return id;
	}

	public int vincoloSO3() {
		// Ritornami l'id dello stato che ha come nome "spedito".
		String query = "SELECT s.idStatoOrdine FROM statoordine s WHERE s.nome='spedito';";
		int id = 0;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("idStatoOrdine");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return id;
	}

}
