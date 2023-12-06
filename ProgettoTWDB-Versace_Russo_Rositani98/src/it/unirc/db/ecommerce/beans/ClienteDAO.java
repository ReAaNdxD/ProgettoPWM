package it.unirc.db.ecommerce.beans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.Vector;

import it.unirc.db.ecommerce.utils.DBManager;
import it.unirc.db.ecommerce.views.Customer;

public class ClienteDAO {

	private static Connection conn = null;

	private Cliente recordToCliente(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(rs.getInt("idCliente"));
		cliente.setEmail(rs.getString("email"));
		cliente.setPassword(rs.getString("password"));
		cliente.setNome(rs.getString("nome"));
		cliente.setCognome(rs.getString("cognome"));
		cliente.setDataNascita(rs.getDate("dataDiNascita"));
		if (cliente.getDataNascita() != null)
			cliente.setDataNascita(rs.getDate("dataDiNascita"));
		return cliente;
	}

	public boolean salva(Cliente cliente) {
		String query = "INSERT INTO cliente (email,password,nome,cognome,dataDiNascita) VALUES (?,?,?,?,?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, cliente.getEmail());
			ps.setString(2, cliente.getPassword());
			ps.setString(3, cliente.getNome());
			ps.setString(4, cliente.getCognome());
			if (cliente.getDataNascita() != null)
				ps.setDate(5, cliente.getDataNascita(), Calendar.getInstance());
			else
				ps.setNull(5, java.sql.Types.INTEGER);

			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		} catch (Exception e) {
			return false;
		}
		DBManager.closeConnection();

		if (assegnaCarrello(getIdByEmail(cliente))) {
			esito = true;
		}
		return esito;
	}

	public Cliente get(Cliente customer) {
		String query = "SELECT * FROM cliente WHERE idCliente = ?";
		Cliente cliente = null;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, customer.getIdCliente());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cliente = recordToCliente(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return cliente;
	}
	
	public Cliente getByNotId(Cliente customer) {
	    String query = "SELECT * FROM cliente WHERE email=? AND password=? AND nome=? AND cognome=? AND dataDiNascita=?";
	    Cliente cliente = null;
	    
	    conn = DBManager.startConnection();
	    try {
	      PreparedStatement ps = conn.prepareStatement(query);
	      ps.setString(1, customer.getEmail());
	      ps.setString(2, customer.getPassword());
	      ps.setString(3, customer.getNome());
	      ps.setString(4, customer.getCognome());
	      ps.setDate(5, customer.getDataNascita());
	      ResultSet rs = ps.executeQuery();
	      if (rs.next()) {
	        cliente = recordToCliente(rs);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    DBManager.closeConnection();
	    return cliente;
	  }

	public Cliente getIdByEmail(Cliente customer) {
		String query = "SELECT idCliente FROM cliente WHERE email = ?";
		Cliente cliente = null;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, customer.getEmail());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cliente = new Cliente(rs.getInt("idCliente"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(cliente);
		DBManager.closeConnection();
		return cliente;
	}

	public Cliente getIdByCarrello(Carrello carrello) {
		String query = "SELECT C.idCliente\r\n" + "FROM cliente C JOIN carrello CA ON C.idCliente=CA.idCliente\r\n"
				+ "WHERE CA.idCarrello=?";
		Cliente cliente = null;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, carrello.getIdCarrello());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cliente = new Cliente(rs.getInt("idCliente"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(cliente);
		DBManager.closeConnection();
		return cliente;
	}

	public boolean elimina(Cliente cliente) {
		System.out.println(cliente.toString1());
		String query = "DELETE FROM cliente WHERE idCliente = ?";
		boolean esito = false;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getIdCliente());
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		} catch (Exception e) {
			return false;
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean modifica(Cliente cliente) {
		String query = "UPDATE cliente SET password=?, nome=?, cognome=?, email=?, dataDiNascita=? WHERE idCliente=?";
		boolean esito = false;
		conn = DBManager.startConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, cliente.getPassword());
			ps.setString(2, cliente.getNome());
			ps.setString(3, cliente.getCognome());
			ps.setString(4, cliente.getEmail());
			ps.setDate(5, cliente.getDataNascita(), Calendar.getInstance());
			ps.setInt(6, cliente.getIdCliente());
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

	public Vector<Cliente> getAll() {
		String query = "SELECT * FROM cliente;";
		Vector<Cliente> res = new Vector<Cliente>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Cliente temp = new Cliente();
			while (rs.next()) {
				temp = recordToCliente(rs);
				res.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<Cliente> getAllClientiNonPrime() {
		Vector<Cliente> vector = new Vector<>();
		String query = "SELECT * \r\n" + "FROM cliente WHERE idCliente NOT IN (SELECT idCliente FROM clienteprime \r\n"
				+ "									WHERE ( current_date() BETWEEN  dataInizioIscrizione AND dataFineIscrizione))";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Cliente cliente = new Cliente();
			while (rs.next()) {
				cliente = recordToCliente(rs);
				vector.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	public boolean disdiciPrime1(Cliente cl) {
		System.out.println(cl);
		boolean esito = false;
		ClientePrimeDAO cpDAO = new ClientePrimeDAO();
		ClientePrime cp = cpDAO.get(new ClientePrime(cl.getIdCliente()));
		if (!cpDAO.isPrime(cl))
			return esito;
		esito = true;
		String query = "UPDATE clienteprime SET dataFineIscrizione=DATE_ADD(datainizioIscrizione, INTERVAL ((TIMESTAMPDIFF(MONTH, datainizioIscrizione, current_date()))*30+30) DAY)  WHERE idCliente=?";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		if (cp.getMensile()) {
			System.out.println("Rigo 195 disdiciPrime" + esito);
			return esito;
		}
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cp.getIdCliente());
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		System.out.println(esito);
		return esito;
	}

	public boolean assegnaCarrello(Cliente cliente) {
		CarrelloDAO carrelloDAO = new CarrelloDAO();
		return carrelloDAO.salva(new Carrello((Integer) cliente.getIdCliente()));
	}

	public Vector<Cliente> cercaClienteById(String id) {
		String query = "SELECT * FROM cliente WHERE idCliente LIKE '%' ? '%' ORDER BY idCliente";
		Vector<Cliente> res = new Vector<>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente cliente = recordToCliente(rs);
				res.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<Cliente> cercaClienteByNome_Email(String string) {
		String query = "SELECT *\r\n" + "FROM cliente\r\n"
				+ "WHERE CONCAT(nome,\" \", cognome, \" \", email,\" \") LIKE '%' ? '%' ORDER BY idCliente";
		Vector<Cliente> res = new Vector<>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, string);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente cliente = recordToCliente(rs);
				res.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Vector<Customer> clientiMaxAcquisti() {
		String query = "SELECT CL.idCliente, count(O.numeroOrdine) as \"OrdiniEffettuati\"\r\n"
				+ "FROM ordine O 	JOIN carrello C ON O.idCarrello=C.idCarrello\r\n"
				+ "				JOIN Cliente CL ON C.idCliente=CL.idCliente\r\n"
				+ "WHERE O.idStato IN (	SELECT idStatoOrdine from statoordine\r\n"
				+ "						WHERE nome= \"Spedito\" or nome=\"Consegnato\")\r\n"
				+ "GROUP BY CL.idCliente  HAVING count(O.numeroOrdine) = (	SELECT count(O.numeroOrdine) as \"OrdiniEffettuati\"\r\n"
				+ "														FROM ordine O 	JOIN carrello C ON O.idCarrello=C.idCarrello\r\n"
				+ "																		JOIN Cliente CL ON C.idCliente=CL.idCliente\r\n"
				+ "														WHERE O.idStato IN (	SELECT idStatoOrdine from statoordine\r\n"
				+ "																				WHERE nome = \"Spedito\" or nome =\"Consegnato\")\r\n"
				+ "														GROUP BY CL.idCliente ORDER BY OrdiniEffettuati DESC\r\n"
				+ "                                                        LIMIT 1)";
		Vector<Customer> res = new Vector<>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Customer cliente = new Customer();
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setOrdiniEffettuati(rs.getInt("OrdiniEffettuati"));
				res.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public Cliente ordineEffettuatoDa(Ordine ordine) {

		String query = "SELECT cl.* FROM cliente AS cl\r\n" + "JOIN carrello c ON c.idCliente= cl.idCliente\r\n"
				+ "JOIN ordine o ON o.idCarrello=c.idCarrello\r\n" + "WHERE  o.numeroOrdine=?";
		Cliente cliente = null;

		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, ordine.getNumeroOrdine());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cliente = recordToCliente(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return cliente;

	}

	public boolean checkEmail(String email) {
		String query = "Select * FROM cliente WHERE email = ?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
//			if (cliente.getDataNascita() != null)
//				ps.setDate(5, cliente.getDataNascita(), Calendar.getInstance());
//			else
//				ps.setNull(5, java.sql.Types.INTEGER);
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

	public boolean checkEmailForUpdate(String email, int id) {
		String query = "SELECT * FROM (SELECT * FROM cliente WHERE idCliente <> ?) WHERE email=? ";
		boolean esito = false;
		conn = DBManager.startConnection();
		System.out.println(email);
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, email);
//			if (cliente.getDataNascita() != null)
//				ps.setDate(5, cliente.getDataNascita(), Calendar.getInstance());
//			else
//				ps.setNull(5, java.sql.Types.INTEGER);
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

	public Date disdiciPrime(Date dataInizio) {
		Date datafine = null;
		conn = DBManager.startConnection();
		try {
			CallableStatement cs;
			cs = conn.prepareCall("call DISDICIPRIME(?) INTO ?");
			cs.setDate(1, dataInizio);
			cs.registerOutParameter(2, Types.DATE);
			cs.execute();
			datafine = cs.getDate(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return (datafine);
	}

	public Integer login(String email, String password) {
		String query = "SELECT idCliente FROM CLIENTE WHERE email =? and password=?";
		Integer idCliente = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				idCliente = rs.getInt("idCliente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return idCliente;
	}

}
