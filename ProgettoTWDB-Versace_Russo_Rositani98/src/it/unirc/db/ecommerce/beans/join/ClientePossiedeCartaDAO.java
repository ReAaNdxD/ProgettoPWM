package it.unirc.db.ecommerce.beans.join;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unirc.db.ecommerce.beans.CartaCredito;
import it.unirc.db.ecommerce.beans.Cliente;
import it.unirc.db.ecommerce.utils.DBManager;

public class ClientePossiedeCartaDAO {

	private static Connection conn;

	public boolean get(Cliente cliente, CartaCredito cartaCredito) { // Esiste -> true
		String query = "SELECT * FROM possiede WHERE idCliente = ? AND idCartaCredito = ?";
		boolean esito = false;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getIdCliente());
			ps.setInt(2, cartaCredito.getIdCartaCredito());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				esito = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean salva(Cliente cliente, CartaCredito cartaCredito) {
		
		System.out.println("Cliente : "+cliente);
		System.out.println("Carta : "+cartaCredito);
		String query = "INSERT INTO possiede VALUES (?,?)";
		boolean esito = false;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cartaCredito.getIdCartaCredito());
			ps.setInt(2, cliente.getIdCliente());
			int tmp = ps.executeUpdate();
			if (tmp == 1)
				esito = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean elimina(Cliente cliente, CartaCredito cartaCredito) {
		String query = "DELETE FROM possiede WHERE idCliente=? AND idCartaCredito=?";
		boolean esito = false;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getIdCliente());
			ps.setInt(2, cartaCredito.getIdCartaCredito());
			int tmp = ps.executeUpdate();
			if (tmp == 1)
				esito = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

}
