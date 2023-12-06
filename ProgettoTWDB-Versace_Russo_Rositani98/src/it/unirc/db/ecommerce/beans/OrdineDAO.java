package it.unirc.db.ecommerce.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;
import it.unirc.db.ecommerce.views.Order;
import it.unirc.db.ecommerce.views.ViewOrderProducts;

public class OrdineDAO {

	private static Connection conn = null;

	private Ordine recordToOrdine(ResultSet rs) throws SQLException {
		Ordine ordine = new Ordine();
		ordine.setNumeroOrdine(rs.getInt("numeroOrdine"));
		ordine.setCostoTotale(rs.getDouble("costoTotale"));
		ordine.setDataCreazione(rs.getDate("dataCreazione"));
		ordine.setIdCarrello(rs.getInt("idCarrello"));
		ordine.setIdMetodo(rs.getInt("idMetodo"));
		ordine.setIdStato(rs.getInt("idStato"));
		ordine.setIdIndirizzoSpedizione(rs.getInt("idIndirizzoSpedizione"));
		return ordine;
	}

	public boolean salva(Ordine ordine) {
		System.out.println(ordine);
		String query = "INSERT INTO Ordine (costoTotale, dataCreazione, idCarrello, idMetodo, idStato, idIndirizzoSpedizione) \r\n"
				+ "VALUES (?,?,?,?,?,?)";
		boolean esito = false;
		CarrelloDAO carrelloDAO = new CarrelloDAO();
		Carrello carrello = carrelloDAO.getById(ordine.getIdCarrello());
		if (!carrelloDAO.isCartFull(ordine)) {
			System.out.println("Il carrello è vuoto");
			return false;
		}
		StatoOrdineDAO statoOrdineDAO = new StatoOrdineDAO();
		int idStato = statoOrdineDAO.vincoloSO2();
		double costo = calcoloCosto(ordine);
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDouble(1, costo);
			ps.setDate(2, new Date(System.currentTimeMillis()), Calendar.getInstance());
			ps.setInt(3, ordine.getIdCarrello());
			ps.setInt(4, ordine.getIdMetodo());
			ps.setInt(5, idStato);
			ps.setInt(6, ordine.getIdIndirizzoSpedizione());
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
			vincoloCA1(carrello);
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente(carrello.getIdCliente());
			clienteDAO.assegnaCarrello(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;

	}

	public Ordine get(Ordine or) {
		String query = "SELECT * FROM ordine WHERE numeroOrdine = ?";
		Ordine ordine = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, or.getNumeroOrdine());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ordine = recordToOrdine(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return ordine;
	}

	public boolean elimina(Ordine or) {
		String query = "DELETE FROM ordine WHERE numeroOrdine = ?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, or.getNumeroOrdine());
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

	public boolean modifica(Ordine or) {
		String query = "UPDATE ordine SET costoTotale=?, dataCreazione=?, idCarrello=?, idMetodo=?, idStato=?, idIndirizzoSpedizione=? WHERE numeroOrdine=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		System.out.println(or);

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDouble(1, or.getCostoTotale());
			ps.setDate(2, or.getDataCreazione());
			ps.setInt(3, or.getIdCarrello());
			ps.setInt(4, or.getIdMetodo());
			ps.setInt(5, or.getIdStato());
			ps.setInt(6, or.getIdIndirizzoSpedizione());
			ps.setInt(7, or.getNumeroOrdine());

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

	public Vector<Ordine> getAll() {
		Vector<Ordine> vector = new Vector<Ordine>();
		String query = "SELECT * FROM ordine";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Ordine ordine = new Ordine();
			while (rs.next()) {
				ordine = recordToOrdine(rs);
				vector.add(ordine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public boolean modificaMetodoSpedizione(Ordine o, MetodoSpedizione m) {
		SpedizioneDAO sDAO = new SpedizioneDAO();
		boolean esito = false;
		if (!sDAO.getSpedizioneByNumOrd(o)) {
			String query = " UPDATE ordine SET idMetodo = ? WHERE numeroordine = ?  ";
			conn = DBManager.startConnection();
			try {

				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, m.getIdMetodoSpedizione());
				ps.setInt(2, o.getNumeroOrdine());

				int tmp = ps.executeUpdate();
				if (tmp == 1) {
					esito = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
		}
		return esito;
	}

	public boolean modificaStato(Ordine or, StatoOrdine s) {
		boolean esito = false;
		StatoOrdineDAO sDAO = new StatoOrdineDAO();
		SpedizioneDAO spDAO = new SpedizioneDAO();
		int idAnnullato = sDAO.vincoloSO1();
		String query = " UPDATE ordine SET idStato = ? WHERE numeroOrdine = ?  ";
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, s.getIdStatoOrdine());
			ps.setInt(2, or.getNumeroOrdine());
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		if (or.getIdStato() == idAnnullato && spDAO.getSpedizioneByNumOrd(or))
			esito = false;
		return esito;
	}

	public double calcoloCosto(Ordine ordine) {
		// La somma del prodotto costoAcquisto*quantità + spese di spedizione
		double res = 0.0;
		System.out.println("Sono in calcoloCostoTotale");
		String query = "SELECT TRUNC(SUM(COSTI.costo),2) AS costoTotale\r\n" + 
				"				FROM (SELECT idArticolo, prezzoAcquisto, quantita, (prezzoAcquisto*quantita) AS costo\r\n" + 
				"				FROM compone WHERE idCarrello=? ) COSTI";
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, ordine.getIdCarrello());
			ResultSet rs = ps.executeQuery();
			System.out.println("Sono dopo resultset");
			if (rs.next()) {
				res = rs.getDouble("costoTotale");
				System.out.println("Prezzo totale" + res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("sto uscendo dal metodo calcoloCostoTotale");
		DBManager.closeConnection();
		if (res == 0.0) {
			return res;
		}
		return res + vincoloMS1(ordine);
	}

	private double vincoloMS1(Ordine ordine) {
		double res = 0.0;
		ClientePrimeDAO clientePrimeDAO = new ClientePrimeDAO();
		CarrelloDAO carrelloDAO = new CarrelloDAO();
		Carrello carrello = carrelloDAO.get(new Carrello(ordine.getIdCarrello()));
		System.out.println(carrello);
		if (clientePrimeDAO.isPrime(new Cliente(carrello.getIdCliente()))) {
			System.out.println("DEntro if");
			return res;
		}
		MetodoSpedizioneDAO metodoSpedizioneDAO = new MetodoSpedizioneDAO();
		MetodoSpedizione metodoSpedizione = metodoSpedizioneDAO
				.getMetodoSpedizione(new MetodoSpedizione(ordine.getIdMetodo()));
		res = metodoSpedizione.getSpeseSpedizione();
		System.out.println(res);
		return res;
	}

	private boolean vincoloCA1(Carrello carrello) {
		CarrelloDAO carrelloDAO = new CarrelloDAO();
		return carrelloDAO.modificaStato(carrello);
	}

	public Vector<Ordine> ordiniDaPagare(Cliente cliente) {
		Vector<Ordine> orders = new Vector<Ordine>();
		String query = "SELECT o.* FROM ordine o\r\n" + "join statoordine so ON so.idStatoOrdine=o.idStato\r\n"
				+ "join carrello ca ON ca.idCarrello=o.idCarrello\r\n"
				+ "join cliente cl ON cl.idCliente= ca.idCliente\r\n"
				+ "WHERE cl.idCliente=? AND so.idStatoOrdine in (SELECT so.idStatoOrdine from statoordine\r\n"
				+ "WHERE so.nome= \"SospesoPerPagamento\" or so.nome=\"Fallito\")";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getIdCliente());
			ResultSet rs = ps.executeQuery();
			Ordine ordine = new Ordine();
			while (rs.next()) {
				ordine = recordToOrdine(rs);
				orders.add(ordine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	public Ordine ordineByCarrello(int idCarrello) {
		String query = "SELECT * FROM ordine WHERE idCarrello = ?";
		Ordine ordine = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idCarrello);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ordine = recordToOrdine(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return ordine;
	}

	public Vector<Ordine> getAllOrdiniDaSpedire() {
		Vector<Ordine> vector = new Vector<Ordine>();
		String query = "SELECT o.* FROM ordine o\r\n" + "join statoordine so ON so.idStatoOrdine=o.idStato\r\n"
				+ "join carrello ca ON ca.idCarrello=o.idCarrello\r\n"
				+ "join cliente cl ON cl.idCliente= ca.idCliente\r\n"
				+ "WHERE  so.idStatoOrdine in (SELECT so.idStatoOrdine from statoordine\r\n"
				+ "where so.nome= \"In Elaborazione\" )";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Ordine ordine = new Ordine();
			while (rs.next()) {
				ordine = recordToOrdine(rs);
				vector.add(ordine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public Vector<Order> failedOrders(Cliente cliente) {
		Vector<Order> vector = new Vector<Order>();
		String query = "SELECT O.numeroOrdine, o.costoTotale, o.dataCreazione, ms.tipo as \"Spedizione\",st.nome as \"Stato \" from ordine o\r\n"
				+ "JOIN carrello ca ON ca.idCarrello= o.idCarrello\r\n"
				+ "JOIN cliente cl ON cl.idCliente= ca.idCliente\r\n"
				+ "JOIN metodospedizione ms ON ms.idMetodoSpedizione= o.idMetodo\r\n"
				+ "JOIN statoordine st ON st.idStatoOrdine=o.idStato\r\n"
				+ "WHERE cl.idCliente=? AND o.idStato IN (SELECT idStatoOrdine FROM statoordine\r\n"
				+ "WHERE nome=\"Annullato\" OR nome=\"Fallito\");";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getIdCliente());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order ord = new Order();
				ord.setNumOrd(rs.getInt(1));
				ord.setCostTot(rs.getDouble(2));
				ord.setData(rs.getDate(3));
				ord.setTipo(rs.getString(4));
				ord.setStato(rs.getString(5));
				vector.add(ord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public Ordine getOrder(Cliente cliente) {
		Ordine vector = new Ordine();
		String query = "SELECT O.*"
				+ "		from ordine o\r\n"
				+ "				JOIN carrello ca ON ca.idCarrello= o.idCarrello\r\n"
				+ "				JOIN carrello ca ON ca.idCarrello= o.idCarrello\r\n"
				+ "				JOIN cliente cl ON cl.idCliente= ca.idCliente\r\n"
				+ "				WHERE cl.idCliente=? order by o.numeroOrdine desc ";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getIdCliente());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				vector = recordToOrdine(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public Vector<Order> orders(Cliente cliente) {
		Vector<Order> vector = new Vector<Order>();
		String query = "SELECT O.numeroOrdine, o.costoTotale, o.dataCreazione, ms.tipo as \"Spedizione\",st.nome as \"Stato \" from ordine o\r\n"
				+ "JOIN carrello ca ON ca.idCarrello= o.idCarrello\r\n"
				+ "JOIN cliente cl ON cl.idCliente= ca.idCliente\r\n"
				+ "JOIN metodospedizione ms ON ms.idMetodoSpedizione= o.idMetodo\r\n"
				+ "JOIN statoordine st ON st.idStatoOrdine=o.idStato\r\n"
				+ "WHERE cl.idCliente=? AND o.idStato NOT IN (SELECT idStatoOrdine FROM statoordine\r\n"
				+ "WHERE nome=\"Annullato\" OR nome=\"Fallito\");";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getIdCliente());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order ord = new Order();
				ord.setNumOrd(rs.getInt(1));
				ord.setCostTot(rs.getDouble(2));
				ord.setData(rs.getDate(3));
				ord.setTipo(rs.getString(4));
				ord.setStato(rs.getString(5));
				vector.add(ord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public Vector<Order> getAllOrdini(Cliente cliente) {
		Vector<Order> vector = new Vector<Order>();
		String query = "SELECT O.numeroOrdine, o.costoTotale, o.dataCreazione, ms.tipo as Spedizione,st.nome as Stato  from ordine o\r\n"
				+ "				JOIN carrello ca ON ca.idCarrello= o.idCarrello\r\n"
				+ "				JOIN cliente cl ON cl.idCliente= ca.idCliente\r\n"
				+ "				JOIN metodospedizione ms ON ms.idMetodoSpedizione= o.idMetodo\r\n"
				+ "				JOIN statoordine st ON st.idStatoOrdine=o.idStato  WHERE cl.idCliente=?";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getIdCliente());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order ord = new Order();
				ord.setNumOrd(rs.getInt(1));
				ord.setCostTot(rs.getDouble(2));
				ord.setData(rs.getDate(3));
				ord.setTipo(rs.getString(4));
				ord.setStato(rs.getString(5));
				vector.add(ord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public Vector<ViewOrderProducts> prodottiOrdine(Ordine ordine) {
		Vector<ViewOrderProducts> vector = new Vector<ViewOrderProducts>();
		String query = "SELECT A.idArticolo, P.nome, P.marca, V.nomeAzienda, CO.prezzoAcquisto, CO.quantita, ROUND((CO.prezzoAcquisto*CO.quantita),2) AS CostoTot\r\n"
				+ "FROM ordine O JOIN carrello C ON O.idCarrello=C.idCarrello\r\n"
				+ "			   JOIN compone CO ON C.idCarrello=CO.idCarrello\r\n"
				+ "              JOIN articolo A ON CO.idArticolo=A.idArticolo\r\n"
				+ "              JOIN prodotto P ON A.idProdotto=P.idProdotto\r\n"
				+ "              JOIN venditore V ON A.idVenditore=V.idVenditore\r\n" + "WHERE O.numeroOrdine=?";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ordine.getNumeroOrdine());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ViewOrderProducts orderProducts = new ViewOrderProducts();
				orderProducts.setIdArticolo(rs.getInt(1));
				orderProducts.setNomeProdotto(rs.getString(2));
				orderProducts.setMarca(rs.getString(3));
				orderProducts.setNomeAzienda(rs.getString(4));
				orderProducts.setPrezzoAcquisto(rs.getDouble(5));
				orderProducts.setQuantita(rs.getInt(6));
				orderProducts.setCostoTot(rs.getDouble(7));
				vector.add(orderProducts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

}