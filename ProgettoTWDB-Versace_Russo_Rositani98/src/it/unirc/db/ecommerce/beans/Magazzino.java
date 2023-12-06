package it.unirc.db.ecommerce.beans;

public class Magazzino {

	private int codiceMagazzino;
	private String regione;
	private String provincia;
	private String citta;
	private String via;
	private String nCivico;
	private int cap;

	public Magazzino() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Magazzino(int codiceMagazzino) {
		super();
		this.codiceMagazzino = codiceMagazzino;
	}

	public Magazzino(int codiceMagazzino, String regione, String provincia, String citta, String via,
			String nCivico, int cap) {
		super();
		this.codiceMagazzino = codiceMagazzino;
		this.regione = regione;
		this.provincia = provincia;
		this.citta = citta;
		this.via = via;
		this.nCivico = nCivico;
		this.cap = cap;
	}

	public int getCodiceMagazzino() {
		return codiceMagazzino;
	}

	public void setCodiceMagazzino(int codiceMagazzino) {
		this.codiceMagazzino = codiceMagazzino;
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

	public String getNcivico() {
		return nCivico;
	}

	public void setNcivico(String nCivico) {
		this.nCivico = nCivico;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cap;
		result = prime * result + ((citta == null) ? 0 : citta.hashCode());
		result = prime * result + codiceMagazzino;
		result = prime * result + ((nCivico == null) ? 0 : nCivico.hashCode());
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
		Magazzino other = (Magazzino) obj;
		if (cap != other.cap)
			return false;
		if (citta == null) {
			if (other.citta != null)
				return false;
		} else if (!citta.equals(other.citta))
			return false;
		if (codiceMagazzino != other.codiceMagazzino)
			return false;
		if (nCivico == null) {
			if (other.nCivico != null)
				return false;
		} else if (!nCivico.equals(other.nCivico))
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
		return codiceMagazzino +" "+ regione+" "+ provincia +" "+ citta +" "+  via +" "+  nCivico +" "+ cap ;
	}

}
