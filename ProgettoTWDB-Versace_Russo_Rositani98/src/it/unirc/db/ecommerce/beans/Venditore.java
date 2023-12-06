package it.unirc.db.ecommerce.beans;

import java.math.BigDecimal;

public class Venditore {

	private int idVenditore;
	private String email;
	private String password;
	private String nomeAzienda;
	private BigDecimal pIVA;

	public Venditore() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Venditore(int idVenditore) {
		super();
		this.idVenditore = idVenditore;
	}

	public Venditore(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Venditore(int idVenditore, String password, String nomeAzienda, BigDecimal pIVA) {
		super();
		this.idVenditore = idVenditore;
		this.password = password;
		this.nomeAzienda = nomeAzienda;
		this.pIVA = pIVA;
	}

	public Venditore(int idVenditore, String email, String password, String nomeAzienda, BigDecimal pIVA) {
		super();
		this.idVenditore = idVenditore;
		this.email = email;
		this.password = password;
		this.nomeAzienda = nomeAzienda;
		this.pIVA = pIVA;
	}

	public int getIdVenditore() {
		return idVenditore;
	}

	public void setIdVenditore(int idVenditore) {
		this.idVenditore = idVenditore;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNomeAzienda() {
		return nomeAzienda;
	}

	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}

	public BigDecimal getPIVA() {
		return pIVA;
	}

	public void setPIVA(BigDecimal pIVA) {
		this.pIVA = pIVA;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + idVenditore;
		result = prime * result + ((nomeAzienda == null) ? 0 : nomeAzienda.hashCode());
		result = prime * result + ((pIVA == null) ? 0 : pIVA.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venditore other = (Venditore) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idVenditore != other.idVenditore)
			return false;
		if (nomeAzienda == null) {
			if (other.nomeAzienda != null)
				return false;
		} else if (!nomeAzienda.equals(other.nomeAzienda))
			return false;
		if (pIVA == null) {
			if (other.pIVA != null)
				return false;
		} else if (!pIVA.equals(other.pIVA))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeAzienda;
	}

	public String toString(boolean a) {
		return "Venditore [idVenditore=" + idVenditore + ", email=" + email + ", password=" + password
				+ ", nomeAzienda=" + nomeAzienda + ", pIVA=" + pIVA + "]";
	}

}
