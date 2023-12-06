package it.unirc.db.ecommerce.beans;

import java.sql.Date;

public class Ordine {

	private int numeroOrdine;
	private double costoTotale;
	private Date dataCreazione;
	private int idCarrello;
	private int idMetodo;
	private int idStato;
	private int idIndirizzoSpedizione;

	public Ordine() {
		super();
	}

	public Ordine(int numeroOrdine) {
		super();
		this.numeroOrdine = numeroOrdine;
	}

	

	public Ordine(int idCarrello, int idMetodo) {
		super();
		this.idCarrello = idCarrello;
		this.idMetodo = idMetodo;
	}

	public Ordine(int idCarrello, int idMetodo, int idStato) {
		super();
		this.idCarrello = idCarrello;
		this.idMetodo = idMetodo;
		this.idStato = idStato;
	}
	

	public Ordine(double costoTotale, Date dataCreazione, int idCarrello, int idMetodo, boolean a,
			int idIndirizzoSpedizione) {
		super();
		this.costoTotale = costoTotale;
		this.dataCreazione = dataCreazione;
		this.idCarrello = idCarrello;
		this.idMetodo = idMetodo;
		this.idIndirizzoSpedizione = idIndirizzoSpedizione;
	}

	public Ordine(double costoTotale, Date dataCreazione, int idCarrello, int idMetodo, int idStato) {
		super();
		this.costoTotale = costoTotale;
		this.dataCreazione = dataCreazione;
		this.idCarrello = idCarrello;
		this.idMetodo = idMetodo;
		this.idStato = idStato;
	}

	public Ordine(int numeroOrdine, double costoTotale, Date dataCreazione, int idCarrello, int idMetodo, int idStato,
			int idIndirizzoSpedizione) {
		super();
		this.numeroOrdine = numeroOrdine;
		this.costoTotale = costoTotale;
		this.dataCreazione = dataCreazione;
		this.idCarrello = idCarrello;
		this.idMetodo = idMetodo;
		this.idStato = idStato;
		this.idIndirizzoSpedizione = idIndirizzoSpedizione;
	}

	public int getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(int numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public double getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(double costoTotale) {
		this.costoTotale = costoTotale;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public int getIdCarrello() {
		return idCarrello;
	}

	public void setIdCarrello(int idCarrello) {
		this.idCarrello = idCarrello;
	}

	public int getIdMetodo() {
		return idMetodo;
	}

	public void setIdMetodo(int idMetodo) {
		this.idMetodo = idMetodo;
	}

	public int getIdStato() {
		return idStato;
	}

	public void setIdStato(int idStato) {
		this.idStato = idStato;
	}

	public int getIdIndirizzoSpedizione() {
		return idIndirizzoSpedizione;
	}

	public void setIdIndirizzoSpedizione(int idIndirizzoSpedizione) {
		this.idIndirizzoSpedizione = idIndirizzoSpedizione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(costoTotale);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dataCreazione == null) ? 0 : dataCreazione.hashCode());
		result = prime * result + idCarrello;
		result = prime * result + idIndirizzoSpedizione;
		result = prime * result + idMetodo;
		result = prime * result + idStato;
		result = prime * result + numeroOrdine;
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
		Ordine other = (Ordine) obj;
		if (Double.doubleToLongBits(costoTotale) != Double.doubleToLongBits(other.costoTotale))
			return false;
		if (dataCreazione == null) {
			if (other.dataCreazione != null)
				return false;
		} else if (!dataCreazione.equals(other.dataCreazione))
			return false;
		if (idCarrello != other.idCarrello)
			return false;
		if (idIndirizzoSpedizione != other.idIndirizzoSpedizione)
			return false;
		if (idMetodo != other.idMetodo)
			return false;
		if (idStato != other.idStato)
			return false;
		if (numeroOrdine != other.numeroOrdine)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ordine [numeroOrdine=" + numeroOrdine + ", costoTotale=" + costoTotale + ", dataCreazione="
				+ dataCreazione + ", idCarrello=" + idCarrello + ", idMetodo=" + idMetodo + ", idStato=" + idStato
				+ ", idIndirizzoSpedizione=" + idIndirizzoSpedizione + "]";
	}

}
