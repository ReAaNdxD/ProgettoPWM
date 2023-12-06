package it.unirc.db.ecommerce.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;

public class ProdottoDAO {

	Connection conn = null;

	private Prodotto recordToProdotto(ResultSet rs) throws SQLException {
		Prodotto prodotto = new Prodotto();
		prodotto.setIdProdotto(rs.getInt("idProdotto"));
		prodotto.setNome(rs.getString("nome"));
		prodotto.setDescrBr(rs.getString("descrizioneBreve"));
		prodotto.setDescrDet(rs.getString("descrizioneDettagliata"));
		prodotto.setMarca(rs.getString("marca"));
		prodotto.setIdSottocategoria(rs.getInt("idSottocategoria"));
		return prodotto;
	}

	public boolean salva(Prodotto p) {
		boolean esito = false;
		String query = "INSERT INTO prodotto (nome, descrizioneBreve, descrizioneDettagliata, marca, idSottocategoria) VALUES (?,?,?,?,?)";
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, p.getNome());
			ps.setString(2, p.getDescrBr());
			ps.setString(3, p.getDescrDet());
			ps.setString(4, p.getMarca());

			if (p.getIdSottocategoria() != null)
				ps.setInt(5, p.getIdSottocategoria());
			else
				ps.setNull(5, java.sql.Types.INTEGER);

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

	public Prodotto get(Prodotto p) {
		String query = "SELECT * FROM prodotto WHERE idProdotto = ?";
		Prodotto prodotto = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, p.getIdProdotto());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				prodotto = recordToProdotto(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return prodotto;

	}

	public Prodotto getNotById(Prodotto p) {
		String query = "SELECT * FROM prodotto WHERE nome=? AND descrizioneBreve=? AND descrizioneDettagliata=? AND marca=? AND idSottocategoria=?";
		Prodotto prodotto = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, p.getNome());
			ps.setString(2, p.getDescrBr());
			ps.setString(3, p.getDescrDet());
			ps.setString(4, p.getMarca());
			ps.setInt(5, p.getIdSottocategoria());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				prodotto = recordToProdotto(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return prodotto;

	}

	public int getIdProdottoByNome(String p) {
		String query = "SELECT * FROM prodotto WHERE nome = ?";
		Prodotto prodotto = null;
		int id = 0;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, p);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				prodotto = recordToProdotto(rs);
				id = prodotto.getIdProdotto();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();

		return id;

	}

	public boolean getIdProdottoByNomeAndMarca(Prodotto p) {
		String query = "SELECT * FROM prodotto WHERE nome = ? AND marca=? AND idSottocategoria=?";
		boolean prodotto = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, p.getNome());
			ps.setString(2, p.getMarca());
			ps.setInt(3, p.getIdSottocategoria());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				prodotto = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();

		return prodotto;

	}

	public boolean elimina(Prodotto p) {
		String query = "DELETE FROM prodotto WHERE idProdotto = ?";
		boolean esito = false;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, p.getIdProdotto());
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

	public boolean modifica(Prodotto p) {
		String query = "UPDATE prodotto SET nome=?, descrizioneBreve=?, descrizioneDettagliata=?, marca=?, idSottocategoria=? WHERE idProdotto=?";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, p.getNome());
			ps.setString(2, p.getDescrBr());
			ps.setString(3, p.getDescrDet());
			ps.setString(4, p.getMarca());

			if (p.getIdSottocategoria() != null)
				ps.setInt(5, p.getIdSottocategoria());
			else
				ps.setNull(5, java.sql.Types.INTEGER);

			ps.setInt(6, p.getIdProdotto());
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

	public boolean ultimo(Prodotto p) {
		String query = "SELECT COUNT(*) AS X\r\n" + "FROM prodotto P\r\n" + "WHERE P.marca= \"?\"";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, p.getMarca());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 1) {
					esito = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;

	}

	public Vector<Prodotto> getAll() {
		String query = "SELECT * FROM prodotto";
		Vector<Prodotto> res = new Vector<Prodotto>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Prodotto p = new Prodotto();
			while (rs.next()) {
				p = recordToProdotto(rs);
				res.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<Prodotto> getProdottiBySottocategoria(Sottocategoria s) {
		// Restituiscimi i prodotti appartenenti ad una determinata sottocategoria
		String query = "SELECT * FROM prodotto WHERE idSottocategoria=?;";
		Vector<Prodotto> res = new Vector<Prodotto>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, s.getIdSottocategoria());
			ResultSet rs = ps.executeQuery();
			Prodotto p = new Prodotto();
			while (rs.next()) {
				p = recordToProdotto(rs);
				res.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public List<Prodotto> getProdotti4API(Sottocategoria s) {
		// Restituiscimi i prodotti appartenenti ad una determinata sottocategoria
		String query = "SELECT * FROM prodotto WHERE idSottocategoria=?";
		List<Prodotto> res = new Vector<Prodotto>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, s.getIdSottocategoria());
			ResultSet rs = ps.executeQuery();
			Prodotto p = new Prodotto();
			while (rs.next()) {
				p = recordToProdotto(rs);
				res.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public List<Prodotto> getProdottiByCategory4API(Categoria c) {
		// Restituiscimi i prodotti appartenenti ad una determinata sottocategoria
		String query = "SELECT pr.* FROM PRODOTTO pr\r\n"
				+ "JOIN SOTTOCATEGORIA SO ON so.idsottocategoria=pr.idsottocategoria\r\n"
				+ "WHERE so.idcategoria=?";
		List<Prodotto> res = new Vector<Prodotto>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, c.getIdCategoria());
			ResultSet rs = ps.executeQuery();
			Prodotto p = new Prodotto();
			while (rs.next()) {
				p = recordToProdotto(rs);
				res.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<Prodotto> getProdottiByIdSottocategoria(int s) {
		// Restituiscimi i prodotti appartenenti ad una determinata sottocategoria
		String query = "SELECT * FROM prodotto WHERE idSottocategoria=?";
		Vector<Prodotto> res = new Vector<Prodotto>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, s);
			ResultSet rs = ps.executeQuery();
			Prodotto p = new Prodotto();
			while (rs.next()) {
				p = recordToProdotto(rs);
				res.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<Prodotto> cercaProdottoById(int id) {
		String query = "SELECT * FROM prodotto WHERE idProdotto like CONCAT( '%',?,'%') order by idProdotto";
		Vector<Prodotto> res = new Vector<Prodotto>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto prodotto = recordToProdotto(rs);
				res.add(prodotto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public List<Prodotto> cercaProdottoByNome(String testo) {
		String query = "SELECT p.* FROM prodotto p WHERE UPPER(nome) like upper(CONCAT(CONCAT('%',?),'%')) ORDER BY idProdotto";
		List<Prodotto> res = new Vector<Prodotto>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, testo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto prodotto = recordToProdotto(rs);
				res.add(prodotto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public List<Prodotto> cercaProdottoByNomeWhereSottocategoriaIs(String testo, int sottocategoria) {
		String query = "SELECT p.* FROM prodotto p WHERE UPPER(nome) like upper(CONCAT(CONCAT('%',?),'%')) AND p.idSottocategoria=? ORDER BY idProdotto";
		List<Prodotto> res = new Vector<Prodotto>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, testo);
			ps.setInt(2, sottocategoria);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto prodotto = recordToProdotto(rs);
				res.add(prodotto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public List<Prodotto> cercaProdottoByNomeWhereCategoriaIs(String testo,int categoria) {
		String query = "SELECT p.* FROM prodotto p \r\n"
				+ "JOIN sottocategoria s ON s.idsottocategoria=p.idsottocategoria\r\n"
				+ "WHERE UPPER(p.nome) like upper(CONCAT(CONCAT('%',?),'%')) AND s.idcategoria=?\r\n"
				+ "ORDER BY idProdotto";
		List<Prodotto> res = new Vector<Prodotto>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, testo);
			ps.setInt(2, categoria);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto prodotto = recordToProdotto(rs);
				res.add(prodotto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

}
