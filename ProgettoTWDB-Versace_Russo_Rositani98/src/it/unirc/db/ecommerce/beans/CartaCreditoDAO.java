package it.unirc.db.ecommerce.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import it.unirc.db.ecommerce.beans.join.ClientePossiedeCartaDAO;
import it.unirc.db.ecommerce.utils.DBManager;

public class CartaCreditoDAO {

	private static Connection conn = null;
	private boolean choose;

	public CartaCredito recordToCartaCredito(ResultSet rs) throws SQLException {
		CartaCredito cc = new CartaCredito();
		cc.setIdCartaCredito(rs.getInt("idCartaCredito"));
		cc.setNumeroCarta(rs.getBigDecimal("numeroCarta"));
		cc.setCodiceSicurezza(rs.getShort("codiceSicurezza"));
		cc.setDataScadenza(rs.getDate("dataScadenza"));
		cc.setIntestatario(rs.getString("intestatario"));
		return cc;
	}

	public boolean salva(CartaCredito cc, Cliente cl) {
		choose = false;
		String query = "INSERT INTO cartacredito (numeroCarta,codiceSicurezza, dataScadenza, intestatario) VALUES (?,?,?,?)";
		boolean esito = false;
		if (!vincoloCC1(cc, cl) && !choose) { // Se è false allora non salvo niente
			System.out.println("I valori inseriti non corrispondono a quelli presenti nel database o già esistenti");
			return false;
		}
		if (choose) {
			return true;
		}
		conn = DBManager.startConnection();
		try {
			System.out.println("try");
			PreparedStatement ps = conn.prepareStatement(query);
//			ps.setInt(1, cc.getIdCartaCredito());z
			ps.setBigDecimal(1, cc.getNumeroCarta());
			ps.setShort(2, cc.getCodiceSicurezza());
			ps.setDate(3, cc.getDataScadenza(), Calendar.getInstance());
			ps.setString(4, cc.getIntestatario());
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		System.out.println("Cliente : " + cl.toString1());
		System.out.println("Carta : " + cc);
		CartaCreditoDAO cartaDAO = new CartaCreditoDAO();
		CartaCredito cartaCredito = cartaDAO.getByNumCarta(cc);
		ClientePossiedeCartaDAO possiedeDAO = new ClientePossiedeCartaDAO();
		possiedeDAO.salva(cl, cartaCredito);
		return esito;
	}

	public CartaCredito get(CartaCredito creditCard) {
		String query = "SELECT * FROM cartaCredito WHERE idCartaCredito=?";
		CartaCredito cc = null;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, creditCard.getIdCartaCredito());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cc = recordToCartaCredito(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return cc;
	}

	public CartaCredito getByNumCarta(CartaCredito creditCard) {
		String query = "SELECT * FROM cartaCredito WHERE numeroCarta=?";
		CartaCredito cc = null;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setBigDecimal(1, creditCard.getNumeroCarta());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cc = recordToCartaCredito(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return cc;
	}

	public boolean elimina(CartaCredito cc) {
		String query = "DELETE FROM cartacredito WHERE idCartaCredito = ?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, cc.getIdCartaCredito());
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

	public boolean modifica(CartaCredito cc) {
		String query = "UPDATE cartacredito SET numeroCarta=?, codiceSicurezza=?, dataScadenza=?, intestatario=?  WHERE idCartaCredito=?";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setBigDecimal(1, cc.getNumeroCarta());
			ps.setShort(2, cc.getCodiceSicurezza());
			ps.setDate(3, cc.getDataScadenza(), Calendar.getInstance());
			ps.setString(4, cc.getIntestatario());
			ps.setInt(5, cc.getIdCartaCredito());
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

	public Vector<CartaCredito> getAll() {
		String query = "SELECT * FROM cartacredito;";
		Vector<CartaCredito> res = new Vector<CartaCredito>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			CartaCredito temp = new CartaCredito();
			while (rs.next()) {
				temp = recordToCartaCredito(rs);
				res.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<CartaCredito> getAllByCliente(Cliente cliente) {
		String query = "SELECT C.*, P.idCliente \r\n"
				+ "FROM cartacredito C JOIN possiede P ON C.idCartaCredito=P.idCartaCredito\r\n"
				+ "WHERE P.idCliente=?";
		Vector<CartaCredito> res = new Vector<CartaCredito>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getIdCliente());
			ResultSet rs = ps.executeQuery();
			CartaCredito temp = new CartaCredito();
			while (rs.next()) {
				temp = recordToCartaCredito(rs);
				res.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean hasCartaCredito(int cliente) {
		boolean esito = false;
		String query = "SELECT C.*\r\n" + "FROM cartacredito C JOIN possiede P ON C.idCartaCredito=P.idCartaCredito\r\n"
				+ "					JOIN cliente Ca ON P.idCliente=Ca.idCliente \r\n" + "WHERE Ca.idCliente = ?";
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, cliente);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				esito = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(esito + " hasCartaCredito");
		DBManager.closeConnection();
		return esito;
	}

	public boolean vincoloCC1(CartaCredito cartaCredito, Cliente cliente) { // true - può inserire, false - no
		/**
		 * Se esiste devo chiamare possiede e far associare quella carta al nuovo
		 * cliente
		 **/
		boolean esito = false;
		CartaCredito res = getByNumCarta(cartaCredito);
		System.out.println("res : " + res);
		ClientePossiedeCartaDAO possiedeDAO = new ClientePossiedeCartaDAO();
		if (res == null) { // posso salvare
			return true;
		}
		System.out.println(res.getDataScadenza().equals(cartaCredito.getDataScadenza()) + " prova");
		System.out.println("Equals : " + res.equals(cartaCredito));
		if (res.equals(cartaCredito)) {
			System.out.println("SOno su equals ( valori corrispondono)");
			if (!possiedeDAO.get(cliente, res)) {
				// Posso chiamare possiedeDAO.salva(). Ma non gli devo permettere di salvare la
				// carta quindi ritorno false
				possiedeDAO.salva(cliente, res);
				choose = true;
			}
		}
		System.out.println(esito);
		return esito;
	}

	public Vector<CartaCredito> carteDelCliente(int cliente) {
		String query = "SELECT C.*  FROM cartacredito C JOIN possiede P ON C.idCartaCredito=P.idCartaCredito\r\n"
				+ "                  JOIN cliente Ca ON P.idCliente=Ca.idCliente   WHERE Ca.idCliente =?";
		Vector<CartaCredito> res = new Vector<CartaCredito>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cliente);
			ResultSet rs = ps.executeQuery();
			CartaCredito temp = new CartaCredito();
			while (rs.next()) {
				temp = recordToCartaCredito(rs);
				res.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		System.out.println(res);
		return res;
	}
}
