package it.unirc.db.ecommerce.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;

public class SottocategoriaDAO {

	private static Connection conn = null;

	private Sottocategoria recordToSottoCategoria(ResultSet rs) throws SQLException {
		Sottocategoria sottoCategoria = new Sottocategoria();
		sottoCategoria.setIdSottocategoria(rs.getInt("idSottocategoria"));
		sottoCategoria.setNome(rs.getString("nome"));
		sottoCategoria.setDescrizione(rs.getString("descrizione"));
		sottoCategoria.setIdCategoria(rs.getInt("idCategoria"));
		return sottoCategoria;
	}

	public boolean salva(Sottocategoria subCategory) {
		String query = "INSERT INTO sottocategoria (nome,descrizione,idCategoria) VALUES (?,?,?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, subCategory.getNome());
			ps.setString(2, subCategory.getDescrizione());
			if (subCategory.getIdCategoria() != null)
				ps.setInt(3, subCategory.getIdCategoria());
			else
				ps.setNull(3, java.sql.Types.INTEGER);
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

	public Sottocategoria get(Sottocategoria subCategory) {
		String query = "SELECT * FROM sottocategoria WHERE idSottocategoria = ?";
		Sottocategoria categoria = new Sottocategoria();

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, subCategory.getIdSottocategoria());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				categoria = recordToSottoCategoria(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoria;
	}
	public Sottocategoria getByNome(Sottocategoria subCategory) {
		String query = "SELECT * FROM sottocategoria WHERE upper(nome)= upper(?)";
		Sottocategoria categoria = null;
		
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, subCategory.getNome());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				categoria = recordToSottoCategoria(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoria;
	}

	public boolean elimina(Sottocategoria subcategory) {
		String query = "DELETE FROM sottocategoria WHERE idSottocategoria = ?";
		boolean esito = false;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, subcategory.getIdSottocategoria());
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

	public boolean modifica(Sottocategoria subCategory) {
		String query = "UPDATE sottocategoria SET nome=?, descrizione=?, idCategoria=? WHERE idSottocategoria=?";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, subCategory.getNome());
			ps.setString(2, subCategory.getDescrizione());
			if (subCategory.getIdCategoria() != null)
				ps.setInt(3, subCategory.getIdCategoria());
			else
				ps.setNull(3, java.sql.Types.INTEGER);
			ps.setInt(4, subCategory.getIdSottocategoria());
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

	public Vector<Sottocategoria> getAll() {
		String query = "SELECT * FROM sottocategoria";
		Vector<Sottocategoria> res = new Vector<Sottocategoria>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Sottocategoria sottoc = new Sottocategoria();
			while (rs.next()) {
				sottoc = recordToSottoCategoria(rs);
				res.add(sottoc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<Sottocategoria> getSottocategorieByCategoria(Categoria c) {
//      Dammi tutte le sottocategorie appartenenti ad una categoria
		String query = "SELECT * FROM sottocategoria WHERE idCategoria=?";
		Vector<Sottocategoria> res = new Vector<Sottocategoria>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, c.getIdCategoria());
			ResultSet rs = ps.executeQuery();
			Sottocategoria sottocategoria = new Sottocategoria();
			while (rs.next()) {
				sottocategoria = recordToSottoCategoria(rs);
				res.add(sottocategoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public List<Sottocategoria> getSottocategorie4API(Categoria c) {
//      Dammi tutte le sottocategorie appartenenti ad una categoria
		String query = "SELECT * FROM sottocategoria WHERE idCategoria=?";
		List<Sottocategoria> res = new Vector<Sottocategoria>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, c.getIdCategoria());
			ResultSet rs = ps.executeQuery();
			Sottocategoria sottocategoria = new Sottocategoria();
			while (rs.next()) {
				sottocategoria = recordToSottoCategoria(rs);
				res.add(sottocategoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public Vector<Sottocategoria> getSottocategorieByIdCategoria(int c) {
//      Dammi tutte le sottocategorie appartenenti ad una categoria
		String query = "SELECT * FROM sottocategoria WHERE idCategoria=?";
		Vector<Sottocategoria> res = new Vector<Sottocategoria>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, c);
			ResultSet rs = ps.executeQuery();
			Sottocategoria sottocategoria = new Sottocategoria();
			while (rs.next()) {
				sottocategoria = recordToSottoCategoria(rs);
				res.add(sottocategoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

}
