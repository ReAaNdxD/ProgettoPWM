package it.unirc.db.ecommerce.beans;

import java.sql.Date;

public class Pagamento {

	private int numeroOrdine;
	private Date data;
	private Integer idCarta;

	public Pagamento() {
		super();
	}

	public Pagamento(int numeroOrdine) {
		super();
		this.numeroOrdine = numeroOrdine;
	}

	public Pagamento(int numeroOrdine, Date data, Integer idCarta) {
		super();
		this.numeroOrdine = numeroOrdine;
		this.data = data;
		this.idCarta = idCarta;
	}

	public int getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(int numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getIdCarta() {
		return idCarta;
	}

	public void setIdCarta(Integer idCarta) {
		this.idCarta = idCarta;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (idCarta == null) {
			if (other.idCarta != null)
				return false;
		} else if (!idCarta.equals(other.idCarta))
			return false;
		if (numeroOrdine != other.numeroOrdine)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pagamento [numeroOrdine=" + numeroOrdine + ", data=" + data + ", idCarta="
				+ idCarta + "]";
	}

}
