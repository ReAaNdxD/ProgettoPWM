package it.unirc.db.ecommerce.views;

import java.sql.Date;

public class Order {
	private int numOrd;
	private double costTot;
	private Date data;
	private String tipo;
	private String stato;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int numOrd, double costTot, Date data, String tipo, String stato) {
		super();
		this.numOrd = numOrd;
		this.costTot = costTot;
		this.data = data;
		this.tipo = tipo;
		this.stato = stato;
	}
	public int getNumOrd() {
		return numOrd;
	}
	public void setNumOrd(int numOrd) {
		this.numOrd = numOrd;
	}
	public double getCostTot() {
		return costTot;
	}
	public void setCostTot(double costTot) {
		this.costTot = costTot;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(costTot);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + numOrd;
		result = prime * result + ((stato == null) ? 0 : stato.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Order other = (Order) obj;
		if (Double.doubleToLongBits(costTot) != Double.doubleToLongBits(other.costTot))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (numOrd != other.numOrd)
			return false;
		if (stato == null) {
			if (other.stato != null)
				return false;
		} else if (!stato.equals(other.stato))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Order [numOrd=" + numOrd + ", costTot=" + costTot + ", data=" + data + ", tipo=" + tipo + ", stato="
				+ stato + "]";
	}
	
}
