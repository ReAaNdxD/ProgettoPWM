package it.unirc.db.ecommerce.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;

public class SpedizioneDAO {

	private static Connection conn = null;

	private Spedizione recordToSpedizione(ResultSet rs) throws SQLException {
		Spedizione spedizione = new Spedizione();
		spedizione.setIdSpedizione(rs.getInt("idSpedizione"));
		spedizione.setDataPartenza(rs.getDate("dataPartenza"));
		spedizione.setDataArrivo(rs.getDate("dataArrivo"));
		spedizione.setCodiceTracciamento(rs.getInt("codiceTracciamento"));
		spedizione.setRegione(rs.getString("regione"));
		spedizione.setProvincia(rs.getString("provincia"));
		spedizione.setCitta(rs.getString("citta"));
		spedizione.setVia(rs.getString("via"));
		spedizione.setNCivico(rs.getString("nCivico"));
		spedizione.setCap(rs.getInt("cap"));
		spedizione.setNumeroOrdine(rs.getInt("numeroOrdine"));
		return spedizione;
	}

	public boolean salva(Spedizione spedizione, Ordine ordine) {
		String query = "INSERT INTO spedizione (dataPartenza, dataArrivo, regione, provincia, citta, via, nCivico, cap, numeroOrdine) VALUES (?,?,?,?,?,?,?,?,?)";
		boolean esito = false;
		OrdineDAO oDAO = new OrdineDAO();
		IndirizzoSpedizioneDAO indirizzoSpedizioneDAO = new IndirizzoSpedizioneDAO();
		IndirizzoSpedizione indirizzo = indirizzoSpedizioneDAO.getById(ordine.getIdIndirizzoSpedizione());
		System.out.println("Indirizzo "+indirizzo);
		StatoOrdineDAO soDAO = new StatoOrdineDAO();
		int idStatoSpedito = soDAO.vincoloSO3();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1, spedizione.getDataPartenza(), Calendar.getInstance());
			ps.setDate(2, spedizione.getDataArrivo(), Calendar.getInstance());
			ps.setString(3, indirizzo.getRegione());
			ps.setString(4, indirizzo.getProvincia());
			ps.setString(5, indirizzo.getCitta());
			ps.setString(6, indirizzo.getVia());
			ps.setString(7, indirizzo.getNCivico());
			ps.setInt(8, indirizzo.getCap());
			ps.setInt(9, ordine.getNumeroOrdine());

			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		if (esito) {
			StatoOrdine s = new StatoOrdine(idStatoSpedito);
			oDAO.modificaStato(ordine, s);
		}
		return esito;
	}

	public Spedizione get(Spedizione spedizione) {
		String query = "SELECT * FROM spedizione WHERE idSpedizione = ?";
		Spedizione res = null;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, spedizione.getNumeroOrdine());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToSpedizione(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean elimina(Spedizione spedizione) {
		String query = "DELETE FROM spedizione WHERE idSpedizione = ?";
		boolean esito = false;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, spedizione.getIdSpedizione());
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

	public boolean modifica(Spedizione spedizione) { /* Potremmo pensare di modificare solo la data di arrivo */
		String query = "UPDATE spedizione SET dataArrivo=? WHERE idSpedizione=?";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1, spedizione.getDataArrivo(), Calendar.getInstance());
			ps.setInt(2, spedizione.getIdSpedizione());

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

	public Vector<Spedizione> getAll() {
		Vector<Spedizione> vector = new Vector<Spedizione>();
		String query = "SELECT * FROM spedizione";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Spedizione spedizione = new Spedizione();
			while (rs.next()) {
				spedizione = recordToSpedizione(rs);
				vector.add(spedizione);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public boolean getSpedizioneByNumOrd(Ordine o) {
		// Dato un ordine dimmi se è stato spedito
		String query = "SELECT * FROM spedizione WHERE numeroOrdine=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, o.getNumeroOrdine());
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

	public int metodoSpedizione(Ordine o) {
		// Dato un ordine dimmi se è stato spedito
		String query = "SELECT tipo\r\n"
				+ "FROM metodospedizione MS JOIN ordine O ON O.idMetodo=MS.idMetodoSpedizione\r\n"
				+ "WHERE O.numeroOrdine=?";
		String metodo = "";
		int esito = 0;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, o.getNumeroOrdine());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				metodo = rs.getString("tipo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();

		if (metodo.contentEquals("1 Giorno")) {
			esito = (int) (Math.random() * (2 - 1 + 1) + 1);
			System.out.println("1");
		} else if (metodo.contentEquals("Veloce")) {
			esito = (int) (Math.random() * (3 - 2 + 1) + 2);
			System.out.println("2");

		} else if (metodo.contentEquals("Standard")) {
			esito = (int) (Math.random() * (5 - 3 + 1) + 3);
			System.out.println("3");

		} else {
			esito = (int) (Math.random() * (7 - 5 + 1) + 5);
			System.out.println("4");
		}
		System.out.println(esito);
		return esito;

	}

	public Date getDate(Date date, Ordine ordine) {
		// Dato un ordine dimmi se è stato spedito
		String query = "SELECT DATE_ADD(?, INTERVAL ? DAY) AS data";
		Date esito = Date.valueOf("1901-01-01");
		int day = metodoSpedizione(ordine);

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1, date);
			ps.setInt(2, day);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				esito = rs.getDate("data");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		System.out.println(esito);
		return esito;
	}

}
