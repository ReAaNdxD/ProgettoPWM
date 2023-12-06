package it.unirc.db.ecommerce.beans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;

public class ClientePrimeDAO {

	private static Connection conn = null;

	private ClientePrime recordToClientePrime(ResultSet rs) throws SQLException {
		ClientePrime clientePrime = new ClientePrime();
		clientePrime.setIdCliente(rs.getInt("idCliente"));
		clientePrime.setDataInizioIscrizione(rs.getDate("dataInizioIscrizione"));
		clientePrime.setDataFineIscrizione(rs.getDate("dataFineIscrizione"));
		clientePrime.setMensile(rs.getBoolean("mensile"));
		return clientePrime;
	}

	public boolean salva(ClientePrime clientePrime) {
		String query = "INSERT INTO clienteprime VALUES (?,?,?,?)";
		boolean esito = false;
		Date dataFine = calcoloFineIscrizione(clientePrime.getMensile());
		if (!vincoloCL1(clientePrime.getIdCliente())) {
			System.out.println("Il cliente non possiede una carta di credito");
			return false;
		}
		if (isPrime(new Cliente(clientePrime.getIdCliente()))) {
			System.out.println("Il cliente è già prime");
			return false;
		} else if (modifica(clientePrime)) {
			return true;
		}
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, clientePrime.getIdCliente());
			ps.setDate(2, clientePrime.getDataInizioIscrizione(), Calendar.getInstance());
			ps.setDate(3, dataFine, Calendar.getInstance());
			ps.setBoolean(4, clientePrime.getMensile());
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

	public ClientePrime get(ClientePrime clientePrime) {
		String query = "SELECT * FROM clienteprime WHERE idCliente = ?";
		ClientePrime res = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, clientePrime.getIdCliente());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToClientePrime(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean elimina(ClientePrime clientePrime) {
		String query = "DELETE FROM clienteprime WHERE idCliente = ?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, clientePrime.getIdCliente());
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

	public boolean modifica(ClientePrime clientePrime) {
		System.out.println("Dentro modifica");
		String query = "UPDATE clienteprime SET dataInizioIscrizione=?, dataFineIscrizione=?, mensile=? WHERE idCliente=?";
		boolean esito = false;
		Date data = calcoloFineIscrizione(clientePrime.getMensile());
		System.out.println("DATA:" + data);
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1, clientePrime.getDataInizioIscrizione(), Calendar.getInstance());
			ps.setDate(2, data);
//			ps.setDate(2, calcoloFineIscrizione(clientePrime.getMensile()));
			ps.setBoolean(3, clientePrime.getMensile());
			ps.setInt(4, clientePrime.getIdCliente());
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

	public Vector<ClientePrime> getAll() {
		Vector<ClientePrime> vector = new Vector<ClientePrime>();
		String query = "SELECT * FROM clienteprime";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ClientePrime clientePrime = new ClientePrime();
			while (rs.next()) {
				clientePrime = recordToClientePrime(rs);
				vector.add(clientePrime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public Date calcoloFineIscrizione(boolean mensile) {
		System.out.println("SOno dentro la funzione calcoloDataFine");
		Date datafine = null;
		conn = DBManager.startConnection();
		try {
			CallableStatement cs;
			cs = conn.prepareCall("call CALCOLODATAFINE(?) INTO ?");
			cs.setBoolean(1, mensile);
			cs.registerOutParameter(2, Types.DATE);
			cs.execute();
			datafine = cs.getDate(2);
			System.out.println(datafine);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		System.out.println("SOno dentro la funzione calcoloDataFine: " + datafine);
		return (datafine);
	}

	public Date calcoloFineIscrizione1(ClientePrime cp) {
		System.out.println("DAtaInizio:" + cp.getDataInizioIscrizione());
		Date data = null;
		String query = "SELECT DATE_ADD(TO_DATE(?,'yyyy-mm-dd'), INTERVAL ? DAY) AS data";
		ResultSet rs;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		if (!cp.getMensile()) {
			try {
				ps = conn.prepareStatement(query);
				ps.setDate(1, cp.getDataInizioIscrizione(), Calendar.getInstance());
				ps.setInt(2, 365);
				rs = ps.executeQuery();
				if (rs.next()) {
					data = rs.getDate("data");
					System.out.println(rs.getDate("data"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;
		}
		try {
			ps = conn.prepareStatement(query);
			ps.setDate(1, cp.getDataInizioIscrizione(), Calendar.getInstance());
			ps.setInt(2, 30);
			rs = ps.executeQuery();
			if (rs.next()) {
				data = rs.getDate("data");
				System.out.println(rs.getDate("data"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public boolean isPrime(Cliente cliente) { // Vincolo MS1
		System.out.println(cliente);
		String query = "SELECT * FROM clienteprime WHERE idCliente =? AND (current_date BETWEEN  dataInizioIscrizione AND dataFineIscrizione)";
		boolean esito = false;
		try {
			conn = DBManager.startConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getIdCliente());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				esito = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		System.out.println(esito);
		return esito; // true è Prime false No
	}

	public boolean vincoloCL1(int cliente) {
		CartaCreditoDAO cartaCreditoDAO = new CartaCreditoDAO();
		if (cartaCreditoDAO.hasCartaCredito(cliente))
			return true;
		return false;

	}
}
