package it.unirc.db.ecommerce.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;

public class CategoriaDAO {

	private static Connection conn = null;

	private Categoria recordToCategoria(ResultSet rs) throws SQLException {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(rs.getInt("idCategoria"));
		categoria.setNome(rs.getString("nome"));
		categoria.setDescrizione(rs.getString("descrizione"));
		return categoria;
	}

	public boolean salva(Categoria categoria) {
		String query = "INSERT INTO categoria (nome,descrizione) VALUES (?,?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, categoria.getNome());
			ps.setString(2, categoria.getDescrizione());

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

	public Categoria get(Categoria categoria) {
		String query = "SELECT * FROM categoria WHERE idCategoria = ?";
		Categoria res = null;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, categoria.getIdCategoria());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToCategoria(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public Categoria getByNome(Categoria categoria) {
		String query = "SELECT * FROM categoria WHERE upper(nome) =upper(?)";
		Categoria res = null;
		
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, categoria.getNome());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToCategoria(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean elimina(Categoria categoria) {
		String query = "DELETE FROM categoria WHERE idCategoria = ?";
		boolean esito = false;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, categoria.getIdCategoria());
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

	public boolean modifica(Categoria category) {
		String query = "UPDATE categoria SET nome=?, descrizione=? WHERE idCategoria=?";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, category.getNome());
			ps.setString(2, category.getDescrizione());
			ps.setInt(3, category.getIdCategoria());

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
	
	public Vector<Categoria> getAll() {
		Vector<Categoria> vector = new Vector<Categoria>();
		String query = "SELECT * FROM categoria";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Categoria categoria = new Categoria();
			while (rs.next()) {
				categoria = recordToCategoria(rs);
				vector.add(categoria);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

}
