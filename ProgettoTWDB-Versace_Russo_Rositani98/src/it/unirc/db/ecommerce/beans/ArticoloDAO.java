package it.unirc.db.ecommerce.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;
import it.unirc.db.ecommerce.views.GridProduct;
import it.unirc.db.ecommerce.views.ViewProduct;

public class ArticoloDAO {

	private static Connection conn;

	private Articolo recordToArticolo(ResultSet rs) throws SQLException {
		Articolo articolo = new Articolo();
		articolo.setIdArticolo(rs.getInt("idArticolo"));
		articolo.setPrezzo(rs.getFloat("prezzo"));
		articolo.setQuantita(rs.getInt("quantita"));
		articolo.setIdProdotto(rs.getInt("idProdotto"));
		articolo.setIdVenditore(rs.getInt("idVenditore"));
		return articolo;
	}

	public boolean salva(Articolo articolo) {
		String query = "INSERT INTO articolo (prezzo, quantita, idProdotto, idVenditore) VALUES (?,?,?,?)";
		boolean esito = false;
		if (isExist(articolo)) {
			System.out.println("Esistono");
			return false;
		}
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setFloat(1, articolo.getPrezzo());
			ps.setInt(2, articolo.getQuantita());
			ps.setInt(3, articolo.getIdProdotto());
			ps.setInt(4, articolo.getIdVenditore());

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

	public boolean isExist(Articolo articolo) {
		String query = "SELECT * FROM articolo WHERE idProdotto = ? AND idVenditore=?";
		boolean esito = false;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, articolo.getIdProdotto());
			ps.setInt(2, articolo.getIdVenditore());
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

	public Articolo get(Articolo articolo) {
		String query = "SELECT * FROM articolo WHERE idArticolo = ?";
		Articolo res = null;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, articolo.getIdArticolo());
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

	public boolean elimina(Articolo articolo) {
		String query = "DELETE FROM articolo WHERE idArticolo = ?";
		boolean esito = false;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, articolo.getIdArticolo());
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

public GridProduct getProduct(Articolo articolo) {
		
		String query = "SELECT \r\n" + 
				"        a.idprodotto,\r\n" + 
				"        a.idarticolo,\r\n" + 
				"        a.prezzo,\r\n" + 
				"        a.quantita ,\r\n" + 
				"        V.nomeAzienda, \r\n" + 
				"        p.nome, \r\n" + 
				"        p.marca,\r\n" + 
				"        p.idsottocategoria,\r\n" + 
				"        p.descrizionebreve,\r\n" + 
				"        p.descrizionedettagliata,\r\n" + 
				"        v.idvenditore\r\n" + 
				"        \r\n" + 
				"        \r\n" + 
				"    FROM articolo A JOIN prodotto P ON A.idprodotto=P.idprodotto\r\n" + 
				"                    JOIN sottocategoria S ON p.idsottocategoria=s.idsottocategoria\r\n" + 
				"                    JOIN categoria C ON s.idcategoria=c.idcategoria\r\n" + 
				"                    JOIN venditore V ON a.idvenditore=v.idvenditore\r\n" + 
				"    WHERE a.idarticolo=?";
		GridProduct res = null;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, articolo.getIdArticolo());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = new GridProduct();
				res.setIdProdotto(rs.getInt(1));
				res.setIdArticolo(rs.getInt(2));
				res.setPrezzo(rs.getDouble(3));
				res.setDisponibilita(rs.getInt(4));
				res.setNomeAzienda(rs.getString(5));
				res.setNomeProdotto(rs.getString(6));
				res.setMarca(rs.getString(7));
				res.setIdSottocategoria(rs.getInt(8));	
				res.setDescrizioneBreve(rs.getString(9));	
				res.setDescrizioneDettagliata(rs.getString(10));	
				res.setIdVenditore(rs.getInt(11));	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public boolean modifica(Articolo articolo) {
		String query = "UPDATE articolo SET prezzo=?, quantita=?, idProdotto=?, idVenditore=? WHERE idArticolo=?";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setFloat(1, articolo.getPrezzo());
			ps.setInt(2, articolo.getQuantita());
			ps.setInt(3, articolo.getIdProdotto());
			ps.setInt(4, articolo.getIdVenditore());
			ps.setInt(5, articolo.getIdArticolo());

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

	public Vector<Articolo> getAll() {
		Vector<Articolo> vector = new Vector<Articolo>();
		String query = "SELECT * FROM articolo";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Articolo articolo = new Articolo();
			while (rs.next()) {
				articolo = recordToArticolo(rs);
				vector.add(articolo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public Vector<Articolo> getAllAvailableProducts() {
		Vector<Articolo> vector = new Vector<Articolo>();
		String query = "SELECT * FROM articolo WHERE quantita <> 0";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Articolo articolo = new Articolo();
			while (rs.next()) {
				articolo = recordToArticolo(rs);
				vector.add(articolo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public Vector<GridProduct> getAllAvailableProducts(String queryField, int currentPage, int recordsPerPage,
			HashMap<String, Object> param) {
		Vector<GridProduct> vector = new Vector<GridProduct>();
		int offset = currentPage * recordsPerPage - recordsPerPage;
		System.out.println("Offset " + offset);
		System.out.println("RecordsPerPage " + recordsPerPage);
		String query = "SELECT  a.idprodotto,\r\n" + "        a.idarticolo,\r\n" + "        a.prezzo,\r\n"
				+ "        a.quantita AS disponibilit� ,\r\n" + "        a.nomeAzienda, \r\n" + "        a.nome, \r\n"
				+ "        a.marca,\r\n" + "        a.idsottocategoria \r\n" + "FROM \r\n" + "(\r\n" + "    SELECT \r\n"
				+ "        a.idprodotto,\r\n"
				+ "        ROW_NUMBER() OVER (PARTITION BY a.idprodotto ORDER BY a.idvenditore)as num,\r\n"
				+ "        a.idarticolo,\r\n" + "        a.prezzo,\r\n" + "        a.quantita ,\r\n"
				+ "        V.nomeAzienda, \r\n" + "        p.nome, \r\n" + "        p.marca,\r\n"
				+ "        p.idsottocategoria\r\n" + "        \r\n"
				+ "    FROM articolo A JOIN prodotto P ON A.idprodotto=P.idprodotto\r\n"
				+ "                    JOIN sottocategoria S ON p.idsottocategoria=s.idsottocategoria\r\n"
				+ "                    JOIN categoria C ON s.idcategoria=c.idcategoria\r\n"
				+ "                    JOIN venditore V ON a.idvenditore=v.idvenditore\r\n"
				+ "    WHERE p.idprodotto IN (   SELECT idprodotto \r\n"
				+ "                                                FROM prodotto\r\n"
				+ "                                                WHERE UPPER(nome || ' ' || marca || ' ') like UPPER(CONCAT(CONCAT('%',?),'%')) \r\n"
				+ "                                            ) \r\n";

		for (String i : param.keySet()) {
			if (i.equalsIgnoreCase("category")) {

				query += "AND c.idcategoria=? ";

			} else if (i.equalsIgnoreCase("subcategory")) {

				query += "AND s.idsottocategoria=? ";

			} else if (i.equalsIgnoreCase("brand")) {

				query += "AND UPPER(p.marca)=UPPER(?) ";

			} else if (i.equalsIgnoreCase("priceUb")) {

				query += "AND a.prezzo < ? ";

			} else if (i.equalsIgnoreCase("priceLb")) {
				query += "AND a.prezzo > ? ";
			} else if (i.equalsIgnoreCase("venditore")) {
				query += "AND a.idVenditore = ? ";
			}

		}

		query += ") a\r\n" + "WHERE a.num=1\r\n" + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

		System.out.println(query);

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			int cont = 1;
			ps = conn.prepareStatement(query);
			ps.setString(1, queryField);

			for (String i : param.keySet()) {
				if (i.equalsIgnoreCase("category")) {
					ps.setInt(++cont, (Integer) Integer.parseInt((String) param.get(i)));
				} else if (i.equalsIgnoreCase("subcategory")) {
					ps.setInt(++cont, (Integer) Integer.parseInt((String) param.get(i)));
				} else if (i.equalsIgnoreCase("brand")) {
					ps.setString(++cont, (String) param.get(i));
				} else if (i.equalsIgnoreCase("priceUb")) {
					ps.setInt(++cont, (Integer) Integer.parseInt((String) param.get(i)));
				} else if (i.equalsIgnoreCase("priceLb")) {
					ps.setInt(++cont, (Integer) Integer.parseInt((String) param.get(i)));
				} else if (i.equalsIgnoreCase("venditore")) {
					ps.setInt(++cont, ((Venditore) param.get(i)).getIdVenditore());
				}
			}
			ps.setInt(cont + 1, offset);
			ps.setInt(cont + 2, recordsPerPage);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GridProduct articolo = new GridProduct();
				articolo.setIdProdotto(rs.getInt(1));
				articolo.setIdArticolo(rs.getInt(2));
				articolo.setPrezzo(rs.getDouble(3));
				articolo.setDisponibilita(rs.getInt(4));
				articolo.setNomeAzienda(rs.getString(5));
				articolo.setNomeProdotto(rs.getString(6));
				articolo.setMarca(rs.getString(7));
				articolo.setIdSottocategoria(rs.getInt(8));
				vector.add(articolo);
				System.out.println("Articolo : " + articolo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public Vector<GridProduct> getAllRandomProducts() {
		Vector<GridProduct> vector = new Vector<GridProduct>();
		String query = 	"SELECT  *\r\n" + 
						"FROM   (\r\n" + 
						"    SELECT  a.idprodotto,\r\n" + 
						"            a.idarticolo,\r\n" + 
						"            a.prezzo,\r\n" + 
						"            a.quantita AS disponibilit�,\r\n" + 
						"            V.nomeAzienda, \r\n" + 
						"            p.nome, \r\n" + 
						"            p.marca,\r\n" + 
						"            p.idsottocategoria,\r\n" + 
						"            p.descrizionebreve,\r\n" + 
						"            p.descrizionedettagliata,\r\n" + 
						"            v.idvenditore \r\n" + 
						"FROM articolo A JOIN prodotto P ON A.idprodotto=P.idprodotto\r\n" + 
						"                JOIN sottocategoria S ON p.idsottocategoria=s.idsottocategoria\r\n" + 
						"                JOIN categoria C ON s.idcategoria=c.idcategoria\r\n" + 
						"                JOIN venditore V ON a.idvenditore=v.idvenditore\r\n" + 
						"    ORDER BY DBMS_RANDOM.RANDOM) a\r\n" + 
						"WHERE  rownum < 21";



		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GridProduct articolo = new GridProduct();
				articolo.setIdProdotto(rs.getInt(1));
				articolo.setIdArticolo(rs.getInt(2));
				articolo.setPrezzo(rs.getDouble(3));
				articolo.setDisponibilita(rs.getInt(4));
				articolo.setNomeAzienda(rs.getString(5));
				articolo.setNomeProdotto(rs.getString(6));
				articolo.setMarca(rs.getString(7));
				articolo.setIdSottocategoria(rs.getInt(8));
				vector.add(articolo);
				System.out.println("Articolo : " + articolo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public int getAllAvailableProductsRows(String queryField, HashMap<String, Object> param) {
		int rows = 0;
		String query = "SELECT COUNT(*) \r\n" + "FROM \r\n" + "(\r\n" + "    SELECT \r\n" + "        a.idprodotto,\r\n"
				+ "        ROW_NUMBER() OVER (PARTITION BY a.idprodotto ORDER BY a.idvenditore)as num,\r\n"
				+ "        a.idarticolo,\r\n" + "        a.prezzo,\r\n" + "        a.quantita ,\r\n"
				+ "        V.nomeAzienda, \r\n" + "        p.nome, \r\n" + "        p.marca,\r\n"
				+ "        p.idsottocategoria\r\n" + "        \r\n"
				+ "    FROM articolo A JOIN prodotto P ON A.idprodotto=P.idprodotto\r\n"
				+ "                    JOIN sottocategoria S ON p.idsottocategoria=s.idsottocategoria\r\n"
				+ "                    JOIN categoria C ON s.idcategoria=c.idcategoria\r\n"
				+ "                    JOIN venditore V ON a.idvenditore=v.idvenditore\r\n"
				+ "    WHERE  p.idprodotto IN (   SELECT idprodotto \r\n"
				+ "                                                FROM prodotto\r\n"
				+ "                                                WHERE UPPER(nome || ' ' || marca || ' ') like UPPER(CONCAT(CONCAT('%',?),'%')) \r\n"
				+ "                                            ) \r\n";
		for (String i : param.keySet()) {
			if (i.equalsIgnoreCase("category")) {

				query += "AND c.idcategoria=? ";

			} else if (i.equalsIgnoreCase("subcategory")) {

				query += "AND s.idsottocategoria=? ";

			} else if (i.equalsIgnoreCase("brand")) {

				query += "AND UPPER(p.marca)=UPPER(?) ";

			} else if (i.equalsIgnoreCase("priceUb")) {

				query += "AND a.prezzo < ? ";

			} else if (i.equalsIgnoreCase("priceLb")) {
				query += "AND a.prezzo > ? ";
			} else if (i.equalsIgnoreCase("venditore")) {
				query += "AND a.idVenditore = ? ";
			}

		}

		query += ") a\r\n" + "WHERE a.num=1\r\n";

		System.out.println(query);

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			int cont = 1;
			ps = conn.prepareStatement(query);
			ps.setString(1, queryField);

			for (String i : param.keySet()) {
				if (i.equalsIgnoreCase("category")) {
					ps.setInt(++cont, (Integer) Integer.parseInt((String) param.get(i)));
				} else if (i.equalsIgnoreCase("subcategory")) {
					ps.setInt(++cont, (Integer) Integer.parseInt((String) param.get(i)));
				} else if (i.equalsIgnoreCase("brand")) {
					ps.setString(++cont, (String) param.get(i));
				} else if (i.equalsIgnoreCase("priceUb")) {
					ps.setInt(++cont, (Integer) Integer.parseInt((String) param.get(i)));
				} else if (i.equalsIgnoreCase("priceLb")) {
					ps.setInt(++cont, (Integer) Integer.parseInt((String) param.get(i)));
				} else if (i.equalsIgnoreCase("venditore")) {
					ps.setInt(++cont, ((Venditore) param.get(i)).getIdVenditore());
				}
			}
			;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				rows = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	public Vector<ViewProduct> getAllArticoliCliente(Cliente cliente) {
		Vector<ViewProduct> vector = new Vector<ViewProduct>();
		String query = "SELECT A.idArticolo, p.nome, CO.quantita, CO.prezzoAcquisto, V.idVenditore, V.nomeAzienda, C.idCarrello, A.quantita as disponibilit�\r\n"
				+ "FROM carrello C JOIN cliente CL ON C.idCliente=CL.idCliente\r\n"
				+ "				JOIN compone CO ON C.idCarrello=CO.idCarrello\r\n"
				+ "                JOIN articolo A ON CO.idArticolo=A.idArticolo\r\n"
				+ "                JOIN prodotto P ON A.idProdotto=P.idProdotto\r\n"
				+ "                JOIN venditore V ON A.idVenditore=V.idVenditore\r\n"
				+ "WHERE CL.idCliente=? AND C.attivo=1";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getIdCliente());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ViewProduct viewProduct = new ViewProduct();
				viewProduct.setNomeProdotto(rs.getString("nome"));
				viewProduct.setIdVenditore(rs.getInt("idVenditore"));
				viewProduct.setIdArticolo(rs.getInt("idArticolo"));
				viewProduct.setQuantita(rs.getInt("quantita"));
				viewProduct.setPrezzoAcquisto(rs.getDouble("prezzoAcquisto"));
				viewProduct.setNomeAzienda(rs.getString("nomeAzienda"));
				viewProduct.setDisponibilita(rs.getInt("disponibilit�"));
				viewProduct.setIdCarrello(rs.getInt("idCarrello"));
				vector.add(viewProduct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public boolean aggiornaQuantita(Articolo articolo, int quantita) { // Da richiamare soltanto con il
																		// vincoloA1(quantit�)
		String query = "UPDATE articolo SET quantita=? WHERE idArticolo=?";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, quantita);
			ps.setInt(2, articolo.getIdArticolo());

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

	public int vincoloA1(Articolo articolo) {
		/*
		 * La quantit� di articolo deve essere sempre uguale alla somma delle quantit�
		 * in dispone associate allo stesso articolo
		 */
		String query = "SELECT SUM(quantita) AS quantita \r\n" + "FROM dispone\r\n" + "GROUP BY idArticolo\r\n"
				+ "HAVING idArticolo=?";
		int qTot = 0;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, articolo.getIdArticolo());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				qTot = rs.getInt("quantita");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return qTot;
	}
	
	public Vector<String> getAllBrand(String queryField, HashMap<String, Object> param) {
		Vector<String> vector = new Vector<String>();
		String query = "SELECT \r\n"
				+ "       DISTINCT p.marca\r\n"
				+ "    FROM articolo A JOIN prodotto P ON A.idprodotto=P.idprodotto\r\n"
				+ "                    JOIN sottocategoria S ON p.idsottocategoria=s.idsottocategoria\r\n"
				+ "                    JOIN categoria C ON s.idcategoria=c.idcategoria\r\n"
				+ "                    JOIN venditore V ON a.idvenditore=v.idvenditore\r\n"
				+ "    WHERE p.idprodotto IN (   SELECT idprodotto \r\n"
				+ "                                                FROM prodotto\r\n"
				+ "                                                WHERE UPPER(nome || ' ' || marca || ' ') like UPPER(CONCAT(CONCAT('%',?),'%')) \r\n"
				+ "                                            ) \r\n";

		for (String i : param.keySet()) {
			if (i.equalsIgnoreCase("category")) {

				query += "AND c.idcategoria=? ";

			} else if (i.equalsIgnoreCase("subcategory")) {

				query += "AND s.idsottocategoria=? ";

			} else if (i.equalsIgnoreCase("priceUb")) {

				query += "AND a.prezzo < ? ";

			} else if (i.equalsIgnoreCase("priceLb")) {
				query += "AND a.prezzo > ? ";
			}

		}

		System.out.println(query);

		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			int cont = 1;
			ps = conn.prepareStatement(query);
			
			if (queryField==null) {
				ps.setString(1, "");
			}else {
				ps.setString(1, queryField);
			}

			for (String i : param.keySet()) {
				if (i.equalsIgnoreCase("category")) {
					ps.setInt(++cont, (Integer) Integer.parseInt((String) param.get(i)));
				} else if (i.equalsIgnoreCase("subcategory")) {
					ps.setInt(++cont, (Integer) Integer.parseInt((String) param.get(i)));
				} else if (i.equalsIgnoreCase("priceUb")) {
					ps.setInt(++cont, (Integer) Integer.parseInt((String) param.get(i)));
				} else if (i.equalsIgnoreCase("priceLb")) {
					ps.setInt(++cont, (Integer) Integer.parseInt((String) param.get(i)));
				}
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String articolo = new String();
				articolo=rs.getString(1);
				vector.add(articolo);
				System.out.println("Articolo : " + articolo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

}
