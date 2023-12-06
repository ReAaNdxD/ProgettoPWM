package it.unirc.db.ecommerce.beans;

public class Carrello {

	private int idCarrello;
	private boolean attivo;
	private Integer idCliente;

	public Carrello() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Carrello(Integer idCliente) {
		super();
		this.idCliente = idCliente;
	}

	public Carrello(int idCarrello) {
		super();
		this.idCarrello = idCarrello;
	}

	public Carrello(int idCarrello, Boolean attivo, Integer idCliente) {
		super();
		this.idCarrello = idCarrello;
		this.attivo = attivo;
		this.idCliente = idCliente;
	}

	public int getIdCarrello() {
		return idCarrello;
	}

	public void setIdCarrello(int idCarrello) {
		this.idCarrello = idCarrello;
	}

	public boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (attivo ? 1231 : 1237);
		result = prime * result + idCarrello;
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
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
		Carrello other = (Carrello) obj;
		if (attivo != other.attivo)
			return false;
		if (idCarrello != other.idCarrello)
			return false;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "idCarrello=" + idCarrello + " idCliente=" + idCliente;
	}

}
