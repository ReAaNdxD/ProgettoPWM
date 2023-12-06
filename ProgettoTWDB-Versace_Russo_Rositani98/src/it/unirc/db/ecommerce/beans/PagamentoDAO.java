package it.unirc.db.ecommerce.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;

public class PagamentoDAO {

	private static Connection conn = null;

	private Pagamento recordToPagamento(ResultSet rs) throws SQLException {
		Pagamento pagamento = new Pagamento();
		pagamento.setNumeroOrdine(rs.getInt("numeroOrdine"));
		pagamento.setData(rs.getDate("data"));
		pagamento.setIdCarta(rs.getInt("idCarta"));
		return pagamento;
	}

	public boolean salva(Pagamento pagamento) {
		String query = "INSERT INTO pagamento VALUES (?,CURRENT_DATE ,?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		if (cambiaStatoPagato(pagamento)) {
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, pagamento.getNumeroOrdine());
				ps.setDouble(2, pagamento.getIdCarta());

				int tmp = ps.executeUpdate();
				if (tmp == 1) {
					esito = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		DBManager.closeConnection();
		return esito;
	}

	public Pagamento get(Pagamento pagamento) {
		String query = "SELECT * FROM pagamento WHERE numeroOrdine = ?";
		Pagamento res = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, pagamento.getNumeroOrdine());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = recordToPagamento(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();

		return res;
	}

	public boolean elimina(Pagamento pagamento) {
		String query = "DELETE FROM pagamento WHERE numeroOrdine = ?";
		boolean esito = false;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, pagamento.getNumeroOrdine());
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

	public boolean modifica(Pagamento pagamento) {
		String query = "UPDATE pagamento SET data=? WHERE numeroOrdine=?";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1, pagamento.getData());
			ps.setInt(2, pagamento.getNumeroOrdine());

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

	public Vector<Pagamento> getAll() {
		Vector<Pagamento> vector = new Vector<Pagamento>();
		String query = "SELECT * FROM pagamento";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Pagamento pagamento = new Pagamento();
			while (rs.next()) {
				pagamento = recordToPagamento(rs);
				vector.add(pagamento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public boolean pagamentoFallito(int numeroOrdine) {
		String query = "UPDATE Ordine o SET idStato=(\r\n"
				+ "Select idStatoOrdine from statoordine s where s.nome= \"Fallito\") \r\n"
				+ "WHERE o.numeroOrdine=? and  o.idStato!=(Select idStatoOrdine from statoordine s1 \r\n"
				+ "where s1.ordine=\"Pagato\" or s1.nome= \"Spedito\" or s1.nome=\"Annullato\" or s1.nome=\"Consegnato\" or s1.nome=\"In Elaborazione\"\r\n"
				+ "limit 1) ";
		boolean esito = false;
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, numeroOrdine);

			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return esito;
	}

	public boolean cambiaStatoPagato(Pagamento pagamento) {
		String query = "UPDATE Ordine o \r\n" + 
				"		SET idStato =(   Select idStatoOrdine \r\n" + 
				"                from statoordine s \r\n" + 
				"                where s.nome= 'Confermato'\r\n" + 
				"            ) \r\n" + 
				"		WHERE o.numeroOrdine=? and  o.idStato!=(\r\n" + 
				"                                            Select idStatoOrdine \r\n" + 
				"                                            from statoordine s1 \r\n" + 
				"                                            where s1.nome= 'Pagato' \r\n" + 
				"                                                or s1.nome= 'Spedito' \r\n" + 
				"                                                or s1.nome='Annullato' \r\n" + 
				"                                                or s1.nome='Consegnato' \r\n" + 
				"                                                or s1.nome='In Elaborazione'\r\n" + 
				"				OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY)";
		boolean esito = false;
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, pagamento.getNumeroOrdine());
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return esito;

	}

}
