package it.unirc.db.ecommerce.beans;

import java.sql.Date;

public class Spedizione {

	private int idSpedizione;
	private Date dataPartenza;
	private Date dataArrivo;
	private int codiceTracciamento;
	private String regione;
	private String provincia;
	private String citta;
	private String via;
	private String nCivico;
	private int cap;
	private int numeroOrdine;

	public Spedizione(int idSpedizione, Date dataPartenza, Date dataArrivo, int codiceTracciamento, String regione,
			String provincia, String citta, String via, String nCivico, int cap, int numeroOrdine) {
		super();
		this.idSpedizione = idSpedizione;
		this.dataPartenza = dataPartenza;
		this.dataArrivo = dataArrivo;
		this.codiceTracciamento = codiceTracciamento;
		this.regione = regione;
		this.provincia = provincia;
		this.citta = citta;
		this.via = via;
		this.nCivico = nCivico;
		this.cap = cap;
		this.numeroOrdine = numeroOrdine;
	}

	public Spedizione(int idSpedizione) {
		super();
		this.idSpedizione = idSpedizione;
	}

	public Spedizione() {
		super();
	}

	public int getIdSpedizione() {
		return idSpedizione;
	}

	public void setIdSpedizione(int idSpedizione) {
		this.idSpedizione = idSpedizione;
	}

	public Date getDataPartenza() {
		return dataPartenza;
	}

	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}

	public Date getDataArrivo() {
		return dataArrivo;
	}

	public void setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	public int getCodiceTracciamento() {
		return codiceTracciamento;
	}

	public void setCodiceTracciamento(int codiceTracciamento) {
		this.codiceTracciamento = codiceTracciamento;
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

	public int getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(int numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cap;
		result = prime * result + ((citta == null) ? 0 : citta.hashCode());
		result = prime * result + codiceTracciamento;
		result = prime * result + ((dataArrivo == null) ? 0 : dataArrivo.hashCode());
		result = prime * result + ((dataPartenza == null) ? 0 : dataPartenza.hashCode());
		result = prime * result + idSpedizione;
		result = prime * result + ((nCivico == null) ? 0 : nCivico.hashCode());
		result = prime * result + numeroOrdine;
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((regione == null) ? 0 : regione.hashCode());
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
		Spedizione other = (Spedizione) obj;
		if (cap != other.cap)
			return false;
		if (citta == null) {
			if (other.citta != null)
				return false;
		} else if (!citta.equals(other.citta))
			return false;
		if (codiceTracciamento != other.codiceTracciamento)
			return false;
		if (dataArrivo == null) {
			if (other.dataArrivo != null)
				return false;
		} else if (!dataArrivo.equals(other.dataArrivo))
			return false;
		if (dataPartenza == null) {
			if (other.dataPartenza != null)
				return false;
		} else if (!dataPartenza.equals(other.dataPartenza))
			return false;
		if (idSpedizione != other.idSpedizione)
			return false;
		if (nCivico == null) {
			if (other.nCivico != null)
				return false;
		} else if (!nCivico.equals(other.nCivico))
			return false;
		if (numeroOrdine != other.numeroOrdine)
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
		if (via == null) {
			if (other.via != null)
				return false;
		} else if (!via.equals(other.via))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Spedizione [idSpedizione=" + idSpedizione + ", dataPartenza=" + dataPartenza + ", dataArrivo="
				+ dataArrivo + ", codiceTracciamento=" + codiceTracciamento + ", regione=" + regione + ", provincia="
				+ provincia + ", citta=" + citta + ", via=" + via + ", nCivico=" + nCivico + ", cap=" + cap
				+ ", numeroOrdine=" + numeroOrdine + "]";
	}

}
