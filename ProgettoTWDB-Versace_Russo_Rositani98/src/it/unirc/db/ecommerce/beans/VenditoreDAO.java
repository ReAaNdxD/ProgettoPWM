package it.unirc.db.ecommerce.beans;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;
import it.unirc.db.ecommerce.views.GuadagniVenditore;
import it.unirc.db.ecommerce.views.Seller;
import it.unirc.db.ecommerce.views.ViewProductSeller;

public class VenditoreDAO {

	private static Connection conn = null;

	private Venditore recordToVenditore(ResultSet rs) throws SQLException {
		Venditore venditore = new Venditore();
		venditore.setIdVenditore(rs.getInt("idVenditore"));
		venditore.setEmail(rs.getString("email"));
		venditore.setPassword(rs.getString("password"));
		venditore.setNomeAzienda(rs.getString("nomeAzienda"));
		venditore.setPIVA(rs.getBigDecimal("pIva"));
		return venditore;
	}

	public boolean salva(Venditore venditore) {
		String query = "INSERT INTO venditore VALUES (?,?,?,?,?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, venditore.getIdVenditore());
			ps.setString(2, venditore.getEmail());
			ps.setString(3, venditore.getPassword());
			ps.setString(4, venditore.getNomeAzienda());
			ps.setBigDecimal(5, venditore.getPIVA());

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

	public Venditore get(Venditore venditore) {
		String query = "SELECT * FROM venditore WHERE idVenditore = ?";
		Venditore res = null;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, venditore.getIdVenditore());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToVenditore(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean elimina(Venditore venditore) {
		String query = "DELETE FROM venditore WHERE idVenditore = ?";
		boolean esito = false;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, venditore.getIdVenditore());
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
	
	public Venditore getNotById(Venditore venditore) {
	    String query = "SELECT * FROM venditore WHERE email=? AND password=? AND nomeAzienda=? AND pIva=? ";
	    Venditore res = null;
	    
	    conn = DBManager.startConnection();
	    try {
	      PreparedStatement ps = conn.prepareStatement(query);
	      ps.setString(1, venditore.getEmail());
	      ps.setString(2, venditore.getPassword());
	      ps.setString(3, venditore.getNomeAzienda());
	      ps.setBigDecimal(4, venditore.getPIVA());
	      ResultSet rs = ps.executeQuery();
	      if (rs.next()) {
	        res = recordToVenditore(rs);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    DBManager.closeConnection();
	    return res;
	  }

	public boolean modifica(Venditore venditore) {
		String query = "UPDATE venditore SET password=?, nomeAzienda=?, pIVA=? WHERE idVenditore=?";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, venditore.getPassword());
			ps.setString(2, venditore.getNomeAzienda());
			ps.setBigDecimal(3, venditore.getPIVA());
			ps.setInt(4, venditore.getIdVenditore());

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
	public boolean modificaPiva(BigDecimal al,int id) {
		String query = "UPDATE venditore SET pIVA=? WHERE idVenditore=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setBigDecimal(1, al);
			ps.setInt(2, id);
			
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

	public Vector<Venditore> getAll() {
		Vector<Venditore> vector = new Vector<Venditore>();
		String query = "SELECT * FROM venditore";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Venditore venditore = new Venditore();
			while (rs.next()) {
				venditore = recordToVenditore(rs);
				vector.add(venditore);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public Vector<Venditore> cercaVenditoreById(String id) {
		String query = "SELECT * FROM venditore WHERE idVenditore LIKE '%' ? '%' ORDER BY idVenditore";
		Vector<Venditore> res = new Vector<Venditore>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Venditore venditore = recordToVenditore(rs);
				res.add(venditore);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<Venditore> cercaVenditoreByNome_Email(String email) {
//		String query = "SELECT * FROM prodotto WHERE nome like '%' ? '%' order by idProdotto";
		String query = "SELECT *\r\n" + "FROM venditore\r\n"
				+ "WHERE CONCAT(nomeAzienda,\" \",email,\" \") LIKE '%' ? '%' ORDER BY idVenditore";
		Vector<Venditore> res = new Vector<Venditore>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Venditore venditore = recordToVenditore(rs);
				res.add(venditore);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public void prodottiPiuVenduti() {

	}

	public Vector<Seller> VenditoreCheHaVendutoDiPiu() {
		// Restituisce il/i venditore/i che ha venduto il maggior numero di articoli
		String query = "SELECT Y.* FROM (SELECT V.* , sum(IF( S.nome= \"Spedito\" or S.nome=\"Consegnato\" ,C.quantita ,0)) as qtaArticoliVenduti  FROM  venditore V\r\n"
				+ "join articolo A ON V.idVenditore=A.idVenditore\r\n"
				+ "JOIN compone C ON C.idArticolo = A.idArticolo\r\n"
				+ "JOIN carrello Ca On C.idCarrello = Ca.idCarrello\r\n"
				+ "JOIN ordine O ON O.idCarrello = Ca.idCarrello\r\n"
				+ "JOIN statoordine S ON O.idStato = S.idStatoOrdine\r\n"
				+ "group by V.idVenditore ) AS Y WHERE Y.qtaArticoliVenduti = (\r\n"
				+ "SELECT max(qtaArticoliVenduti)\r\n"
				+ "FROM (SELECT V.* , sum(IF( S.nome= \"Spedito\" or S.nome=\"Consegnato\" ,C.quantita ,0)) as qtaArticoliVenduti  FROM  venditore V\r\n"
				+ "join articolo A ON V.idVenditore=A.idVenditore\r\n"
				+ "JOIN compone C ON C.idArticolo = A.idArticolo\r\n"
				+ "JOIN carrello Ca On C.idCarrello = Ca.idCarrello\r\n"
				+ "JOIN ordine O ON O.idCarrello = Ca.idCarrello\r\n"
				+ "JOIN statoordine S ON O.idStato = S.idStatoOrdine\r\n" + "group by V.idVenditore )  AS X)";
		Vector<Seller> res = new Vector<Seller>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Seller vecSeller = new Seller();
				vecSeller.setIdVenditore(rs.getInt("idVenditore"));
				vecSeller.setEmail(rs.getString("email"));
				vecSeller.setPassword(rs.getString("password"));
				vecSeller.setNomeAzienda(rs.getString("nomeAzienda"));
				vecSeller.setPassword(rs.getString("password"));
				vecSeller.setQtaArticoliVenduti(rs.getInt("qtaArticoliVenduti"));
				vecSeller.setpIva(rs.getBigDecimal("pIva"));
				res.add(vecSeller);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<GuadagniVenditore> venditoreMaxGuadagno() {
		// Restituisce i venditori e i loro guadagni
		String query = "SELECT v.idVenditore,v.nomeAzienda, pr.nome as \"Nome prodotto\", sum(cm.quantita) as \"Quantità Venduta\", ROUND(sum(cm.prezzoAcquisto*cm.quantita),2) as Guadagno from venditore v\r\n"
				+ "JOIN articolo ar ON ar.idVenditore=v.idVenditore\r\n"
				+ "JOIN prodotto pr ON pr.idProdotto=ar.idProdotto\r\n"
				+ "JOIN compone cm ON cm.idArticolo=ar.idArticolo\r\n"
				+ "JOIN carrello cr ON cr.idCarrello=cm.idCarrello\r\n"
				+ "JOIN ordine od ON od.idCarrello= cr.idCarrello\r\n"
				+ "JOIN statoordine st ON st.idStatoOrdine=od.idStato\r\n"
				+ "WHERE  od.idStato NOT IN (SELECT idStatoOrdine FROM statoordine\r\n"
				+ "WHERE nome=\"Annullato\" OR nome=\"Fallito\")\r\n"
				+ "GROUP BY v.idVenditore, pr.nome ORDER BY  v.idVenditore, guadagno desc";
		Vector<GuadagniVenditore> res = new Vector<GuadagniVenditore>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GuadagniVenditore guadagniVenditore = new GuadagniVenditore();
				guadagniVenditore.setIdVenditore(rs.getInt(1));
				guadagniVenditore.setNomeAzienda(rs.getString(2));
				guadagniVenditore.setNomeProdotto(rs.getString(3));
				guadagniVenditore.setProdottiVenduti(rs.getInt(4));
				guadagniVenditore.setGuadagnoTot(rs.getDouble(5));
				res.add(guadagniVenditore);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<ViewProductSeller> prodottiVenduti(Venditore venditore) {
		
		Vector<ViewProductSeller> vector = new Vector<ViewProductSeller>();
		String query = "SELECT p.nome,p.marca, a.prezzo,a.quantita, a.idarticolo FROM prodotto p\r\n" + 
				"JOIN articolo a ON a.idprodotto= p.idprodotto\r\n" + 
				"JOIN venditore v ON v.idvenditore= a.idvenditore\r\n" + 
				"where v.idvenditore=?\r\n" ;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, venditore.getIdVenditore());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ViewProductSeller viewProductSeller = new ViewProductSeller();
				viewProductSeller.setNomeProdotto(rs.getString("nome"));
				viewProductSeller.setMarcaProdotto(rs.getString("marca"));
				viewProductSeller.setPrezzo(rs.getDouble("prezzo"));
				viewProductSeller.setQuantita(rs.getInt("quantita"));
				viewProductSeller.setIdArticolo(rs.getInt("idArticolo"));
			
				vector.add(viewProductSeller);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;

	}
	public boolean checkEmail(String email) {
		String query = "Select * FROM venditore WHERE email = ?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
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
	public Venditore login(Venditore venditore) {
		String query = "Select * FROM venditore WHERE email = ? and Password=?";
		Venditore esito = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, venditore.getEmail());
			ps.setString(2, venditore.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				esito = recordToVenditore(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

}
