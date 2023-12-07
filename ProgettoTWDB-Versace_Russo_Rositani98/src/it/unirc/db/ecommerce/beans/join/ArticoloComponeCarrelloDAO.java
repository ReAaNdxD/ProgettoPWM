package it.unirc.db.ecommerce.beans.join;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.db.ecommerce.beans.Articolo;
import it.unirc.db.ecommerce.beans.Carrello;
import it.unirc.db.ecommerce.utils.DBManager;
import it.unirc.db.ecommerce.views.Compone;

public class ArticoloComponeCarrelloDAO {

	private static Connection conn;

	private Compone recordToCompone(ResultSet rs) throws SQLException {
		Compone vectorCompone = new Compone();
		vectorCompone.setIdArticolo(rs.getInt("idArticolo"));
		vectorCompone.setPrezzoAcquisto(rs.getFloat("prezzoAcquisto"));
		vectorCompone.setquantita(rs.getInt("quantita"));
		return vectorCompone;
	}

	public boolean aggiungiArticoloCarrello(Articolo articolo, Carrello carrello, int quantita) {
		String query = "INSERT INTO compone VALUES(?,?,?,?)";
		boolean esito = false;
		if (isArticoloInCarrello(articolo, carrello)) {
			System.out.println("Sono dentro l'if di isArticolo");
			if (!aggiornaQuantita(articolo, carrello, quantita)) {
				System.out.println("Sono dentro l'if di aggiorna");
				System.out.println("Quantità aggiornata perchè il prodotto è già esistente");
				return true;
			}
			return true;
		}
		conn = DBManager.startConnection();
		try {
			System.out.println("Articolo :" + articolo);
			System.out.println("Carrello :" + carrello);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, articolo.getIdArticolo());
			ps.setInt(2, carrello.getIdCarrello());
			ps.setFloat(3, articolo.getPrezzo());
			ps.setInt(4, quantita);
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

	public boolean modifica(Articolo articolo, Carrello carrello, int quantita) {
		String query = "UPDATE compone SET quantita=? WHERE idArticolo=? AND idCarrello=?";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setFloat(1, quantita);
			ps.setInt(2, articolo.getIdArticolo());
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

	public boolean elimina(Articolo articolo, Carrello c) {
		System.out.println("Articolo : " + articolo);
		System.out.println("Carrello : " + c);
		String query = "DELETE FROM compone WHERE idArticolo=? AND idCarrello=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, articolo.getIdArticolo());
			ps.setInt(2, c.getIdCarrello());
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return esito;
	}

	public Vector<Compone> articoliCarrello(Carrello c) {
		String query = "SELECT C.idArticolo, C.prezzoAcquisto, C.quantita FROM compone C WHERE idCarrello= ?";
		Vector<Compone> res = new Vector<Compone>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, c.getIdCarrello());
			ResultSet rs = ps.executeQuery();
			Compone vc = new Compone();
			while (rs.next()) {
				vc = recordToCompone(rs);
				res.add(vc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}

	public boolean isArticoloInCarrello(Articolo articolo, Carrello carrello) {
		System.out.println("sono in isArticoloInCarrello()");
		String query = "SELECT * FROM compone WHERE idCarrello=? AND idArticolo=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, carrello.getIdCarrello());
			System.out.println("idCarrello " + carrello.getIdCarrello());
			ps.setInt(2, articolo.getIdArticolo());
			System.out.println("idArticolo " + articolo.getIdArticolo());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				esito = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		System.out.println("Esito " + esito);

		return esito;
	}

	public boolean prova(Carrello carrello, Articolo articolo) {
		String query = "SELECT * FROM compone WHERE idCarrello=? AND idArticolo=?";

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, carrello.getIdCarrello());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean aggiornaQuantita(Articolo articolo, Carrello carrello, int quantita) {
		System.out.println("sono in aggiornaQuantita()");
		String query = "UPDATE compone SET quantita = (quantita + ?)\r\n" + "WHERE idArticolo=? AND idCarrello=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, quantita);
			ps.setInt(2, articolo.getIdArticolo());
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

	public boolean aggiornaPrezzo(Articolo articolo, Carrello carrello) {
		System.out.println("sono in aggiornaQuantita()");
		String query = "UPDATE compone SET prezzoAcquisto = ?\r\n" + "WHERE idArticolo=? AND idCarrello=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDouble(1, articolo.getPrezzo());
			ps.setInt(2, articolo.getIdArticolo());
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
	public double calcoloCosto(Carrello carrello) {
		  // La somma del prodotto costoAcquisto*quantità 
		  double res = 0.0;
		  System.out.println("Sono in calcoloCostoTotale");
		  String query = "SELECT TRUNC(SUM(COSTI.costo),2) AS costoTotale\r\n" + 
		    "  FROM (SELECT idArticolo, prezzoAcquisto, quantita, (prezzoAcquisto*quantita) AS costo\r\n" + 
		    "    FROM compone\r\n" + 
		    "  WHERE idCarrello=? ) COSTI";
		  conn = DBManager.startConnection();
		  try {
		   PreparedStatement ps = conn.prepareStatement(query);
		   ps.setInt(1, carrello.getIdCarrello());
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
		  return res;
		 }
}
