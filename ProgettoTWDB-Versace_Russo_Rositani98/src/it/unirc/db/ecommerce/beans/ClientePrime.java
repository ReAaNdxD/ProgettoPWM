package it.unirc.db.ecommerce.beans;

import java.sql.Date;

public class ClientePrime {

	private int idCliente;
	private Date dataInizioIscrizione;
	private Date dataFineIscrizione;
	private boolean mensile;

	public ClientePrime() {
		super();
	}

	public ClientePrime(int idCliente) {
		super();
		this.idCliente = idCliente;
	}

	public ClientePrime(int idCliente, Date dataInizioIscrizione, Date dataFineIscrizione, boolean mensile) {
		super();
		this.idCliente = idCliente;
		this.dataInizioIscrizione = dataInizioIscrizione;
		this.dataFineIscrizione = dataFineIscrizione;
		this.mensile = mensile;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Date getDataInizioIscrizione() {
		return dataInizioIscrizione;
	}

	public void setDataInizioIscrizione(Date dataInizioIscrizione) {
		this.dataInizioIscrizione = dataInizioIscrizione;
	}

	public Date getDataFineIscrizione() {
		return dataFineIscrizione;
	}

	public void setDataFineIscrizione(Date dataFineIscrizione) {
		this.dataFineIscrizione = dataFineIscrizione;
	}

	public boolean getMensile() {
		return mensile;
	}

	public void setMensile(boolean mensile) {
		this.mensile = mensile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFineIscrizione == null) ? 0 : dataFineIscrizione.hashCode());
		result = prime * result + ((dataInizioIscrizione == null) ? 0 : dataInizioIscrizione.hashCode());
		result = prime * result + idCliente;
		result = prime * result + (mensile ? 1231 : 1237);
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
		ClientePrime other = (ClientePrime) obj;
		if (dataFineIscrizione == null) {
			if (other.dataFineIscrizione != null)
				return false;
		} else if (!dataFineIscrizione.equals(other.dataFineIscrizione))
			return false;
		if (dataInizioIscrizione == null) {
			if (other.dataInizioIscrizione != null)
				return false;
		} else if (!dataInizioIscrizione.equals(other.dataInizioIscrizione))
			return false;
		if (idCliente != other.idCliente)
			return false;
		if (mensile != other.mensile)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClientePrime [idCliente=" + idCliente + ", dataInizioIscrizione=" + dataInizioIscrizione
				+ ", dataFineIscrizione=" + dataFineIscrizione + ", mensile=" + mensile + "]";
	}

}
