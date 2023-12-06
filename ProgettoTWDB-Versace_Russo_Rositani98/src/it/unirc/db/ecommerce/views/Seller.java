package it.unirc.db.ecommerce.views;

import java.math.BigDecimal;

public class Seller {
	private int idVenditore;
	private String email;
	private String password;
	private String nomeAzienda;
	private BigDecimal pIva;
	private int qtaArticoliVenduti;
	public Seller() {
		super();
	}
	public Seller(int idVenditore, String email, String password, String nomeAzienda, BigDecimal pIva,
			int qtaArticoliVenduti) {
		super();
		this.idVenditore = idVenditore;
		this.email = email;
		this.password = password;
		this.nomeAzienda = nomeAzienda;
		this.pIva = pIva;
		this.qtaArticoliVenduti = qtaArticoliVenduti;
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
	public BigDecimal getpIva() {
		return pIva;
	}
	public void setpIva(BigDecimal pIva) {
		this.pIva = pIva;
	}
	public int getQtaArticoliVenduti() {
		return qtaArticoliVenduti;
	}
	public void setQtaArticoliVenduti(int qtaArticoliVenduti) {
		this.qtaArticoliVenduti = qtaArticoliVenduti;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + idVenditore;
		result = prime * result + ((nomeAzienda == null) ? 0 : nomeAzienda.hashCode());
		result = prime * result + ((pIva == null) ? 0 : pIva.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + qtaArticoliVenduti;
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
		Seller other = (Seller) obj;
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
		if (pIva == null) {
			if (other.pIva != null)
				return false;
		} else if (!pIva.equals(other.pIva))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (qtaArticoliVenduti != other.qtaArticoliVenduti)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Seller [idVenditore=" + idVenditore + ", email=" + email + ", password=" + password + ", nomeAzienda="
				+ nomeAzienda + ", pIva=" + pIva + ", qtaArticoliVenduti=" + qtaArticoliVenduti + "]";
	}
	
	

}
