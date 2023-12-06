package it.unirc.db.ecommerce.beans;

import java.math.BigDecimal;

public class IndirizzoSpedizione {

	private int idIndirizzoSpedizione;
	private boolean preferito;
	private String regione;
	private String provincia;
	private String citta;
	private String via;
	private String nCivico;
	private int cap;
	private BigDecimal telefono;
	private int idCliente;

	public IndirizzoSpedizione() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public IndirizzoSpedizione(Integer idCliente) {
		super();
		this.idCliente = idCliente;
	}


	public IndirizzoSpedizione(int idIndirizzoSpedizione) {
		super();
		this.idIndirizzoSpedizione = idIndirizzoSpedizione;
	}

	public IndirizzoSpedizione(int idIndirizzoSpedizione, boolean preferito, String regione, String provincia,
			String citta, String via, String nCivico, int cap, BigDecimal telefono, int idCliente) {
		super();
		this.idIndirizzoSpedizione = idIndirizzoSpedizione;
		this.preferito = preferito;
		this.regione = regione;
		this.provincia = provincia;
		this.citta = citta;
		this.via = via;
		this.nCivico = nCivico;
		this.cap = cap;
		this.telefono = telefono;
		this.idCliente = idCliente;
	}

	public int getIdIndirizzoSpedizione() {
		return idIndirizzoSpedizione;
	}

	public void setIdIndirizzoSpedizione(int idIndirizzoSpedizione) {
		this.idIndirizzoSpedizione = idIndirizzoSpedizione;
	}

	public boolean isPreferito() {
		return preferito;
	}

	public void setPreferito(boolean preferito) {
		this.preferito = preferito;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getNCivico() {
		return nCivico;
	}

	public void setNCivico(String nCivico) {
		this.nCivico = nCivico;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public BigDecimal getTelefono() {
		return telefono;
	}

	public void setTelefono(BigDecimal telefono) {
		this.telefono = telefono;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cap;
		result = prime * result + ((citta == null) ? 0 : citta.hashCode());
		result = prime * result + idCliente;
		result = prime * result + idIndirizzoSpedizione;
		result = prime * result + ((nCivico == null) ? 0 : nCivico.hashCode());
		result = prime * result + (preferito ? 1231 : 1237);
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((regione == null) ? 0 : regione.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((via == null) ? 0 : via.hashCode());
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
		IndirizzoSpedizione other = (IndirizzoSpedizione) obj;
		if (cap != other.cap)
			return false;
		if (citta == null) {
			if (other.citta != null)
				return false;
		} else if (!citta.equals(other.citta))
			return false;
		if (idCliente != other.idCliente)
			return false;
		if (idIndirizzoSpedizione != other.idIndirizzoSpedizione)
			return false;
		if (nCivico == null) {
			if (other.nCivico != null)
				return false;
		} else if (!nCivico.equals(other.nCivico))
			return false;
		if (preferito != other.preferito)
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (regione == null) {
			if (other.regione != null)
				return false;
		} else if (!regione.equals(other.regione))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (via == null) {
			if (other.via != null)
				return false;
		} else if (!via.equals(other.via))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  via +" "+ nCivico +", "+ cap +", "+ citta +", "+ provincia;
	}
	
	public String toString(boolean a) {
		return "IndirizzoSpedizione [idIndirizzoSpedizione=" + idIndirizzoSpedizione + ", preferito=" + preferito
				+ ", regione=" + regione + ", provincia=" + provincia + ", citta=" + citta + ", via=" + via
				+ ", nCivico=" + nCivico + ", cap=" + cap + ", telefono=" + telefono + ", idCliente=" + idCliente + "]";
	}

}
