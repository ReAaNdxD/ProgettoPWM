package it.unirc.db.ecommerce.beans;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class CartaCredito {

	private int idCartaCredito;
	private BigDecimal numeroCarta;
	private short codiceSicurezza;
	private Date dataScadenza;
	private String intestatario;

	public CartaCredito() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartaCredito(int idCartaCredito) {
		super();
		this.idCartaCredito = idCartaCredito;
	}

	public CartaCredito(BigDecimal numeroCarta, short codiceSicurezza, Date dataScadenza, String intestatario) {
		super();
		this.numeroCarta = numeroCarta;
		this.codiceSicurezza = codiceSicurezza;
		this.dataScadenza = dataScadenza;
		this.intestatario = intestatario;
	}

	public CartaCredito(int idCartaCredito, BigDecimal numeroCarta, short codiceSicurezza, Date dataScadenza,
			String intestatario) {
		super();
		this.idCartaCredito = idCartaCredito;
		this.numeroCarta = numeroCarta;
		this.codiceSicurezza = codiceSicurezza;
		this.dataScadenza = dataScadenza;
		this.intestatario = intestatario;
	}

	public int getIdCartaCredito() {
		return idCartaCredito;
	}

	public void setIdCartaCredito(int idCartaCredito) {
		this.idCartaCredito = idCartaCredito;
	}

	public BigDecimal getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(BigDecimal numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public short getCodiceSicurezza() {
		return codiceSicurezza;
	}

	public void setCodiceSicurezza(short codiceSicurezza) {
		this.codiceSicurezza = codiceSicurezza;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codiceSicurezza;
		result = prime * result + ((dataScadenza == null) ? 0 : dataScadenza.hashCode());
		result = prime * result + ((intestatario == null) ? 0 : intestatario.hashCode());
		result = prime * result + ((numeroCarta == null) ? 0 : numeroCarta.hashCode());
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
		CartaCredito other = (CartaCredito) obj;
		LocalDate date1 = dataScadenza.toLocalDate();
		LocalDate date2 = other.getDataScadenza().toLocalDate();
		if (codiceSicurezza != other.codiceSicurezza)
			return false;
		if (dataScadenza == null) {
			if (other.dataScadenza != null)
				return false;
		} else if (!date1.isEqual(date2))
			return false;
		if (intestatario == null) {
			if (other.intestatario != null)
				return false;
		} else if (!intestatario.equals(other.intestatario))
			return false;
		if (numeroCarta == null) {
			if (other.numeroCarta != null)
				return false;
		} else if (!numeroCarta.equals(other.numeroCarta))
			return false;
		return true;
	}
	
	
	private String maskCarta(BigDecimal numeroCarta) {
		String card = numeroCarta.toString();
		
		String maskCard = "";
		for (int i = 0; i < card.length()-4; i++) {
			maskCard += "*";
		}
		
		return maskCard+card.substring(card.length()-4);
	}
	

	@Override
	public String toString() {
		return "Carta : " + maskCarta(numeroCarta) + ", intestatario :" + intestatario;
	}

}
