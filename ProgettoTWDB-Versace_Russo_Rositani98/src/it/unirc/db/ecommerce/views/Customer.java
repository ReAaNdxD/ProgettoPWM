package it.unirc.db.ecommerce.views;

public class Customer {
	private int idCliente;
	private int ordiniEffettuati;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int idVenditore, int ordiniEffettuati) {
		super();
		this.idCliente = idVenditore;
		this.ordiniEffettuati = ordiniEffettuati;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idVenditore) {
		this.idCliente = idVenditore;
	}
	public int getOrdiniEffettuati() {
		return ordiniEffettuati;
	}
	public void setOrdiniEffettuati(int ordiniEffettuati) {
		this.ordiniEffettuati = ordiniEffettuati;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCliente;
		result = prime * result + ordiniEffettuati;
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
		Customer other = (Customer) obj;
		if (idCliente != other.idCliente)
			return false;
		if (ordiniEffettuati != other.ordiniEffettuati)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Customer [idVenditore=" + idCliente + ", ordiniEffettuati=" + ordiniEffettuati + "]";
	}
	
	

}
