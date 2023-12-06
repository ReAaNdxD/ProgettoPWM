package it.unirc.db.ecommerce.beans;

public class Prodotto {

	private int idProdotto;
	private String nome;
	private String marca;
	private String descrBr;
	private String descrDet;
	private Integer idSottocategoria;

	public Prodotto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Prodotto(int idProdotto) {
		super();
		this.idProdotto = idProdotto;
	}

	public Prodotto(int idProdotto, String nome, String marca, String descrBr, String descrDet,
			Integer idSottocategoria) {
		super();
		this.idProdotto = idProdotto;
		this.nome = nome;
		this.marca = marca;
		this.descrBr = descrBr;
		this.descrDet = descrDet;
		this.idSottocategoria = idSottocategoria;
	}
	public Prodotto(String nome, String marca, String descrBr, String descrDet,
			Integer idSottocategoria) {
		super();
		this.nome = nome;
		this.marca = marca;
		this.descrBr = descrBr;
		this.descrDet = descrDet;
		this.idSottocategoria = idSottocategoria;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescrBr() {
		return descrBr;
	}

	public void setDescrBr(String descrBr) {
		this.descrBr = descrBr;
	}

	public String getDescrDet() {
		return descrDet;
	}

	public void setDescrDet(String descrDet) {
		this.descrDet = descrDet;
	}

	public Integer getIdSottocategoria() {
		return idSottocategoria;
	}

	public void setIdSottocategoria(Integer idSottocategoria) {
		this.idSottocategoria = idSottocategoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrBr == null) ? 0 : descrBr.hashCode());
		result = prime * result + ((descrDet == null) ? 0 : descrDet.hashCode());
		result = prime * result + idProdotto;
		result = prime * result + ((idSottocategoria == null) ? 0 : idSottocategoria.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Prodotto other = (Prodotto) obj;
		if (descrBr == null) {
			if (other.descrBr != null)
				return false;
		} else if (!descrBr.equals(other.descrBr))
			return false;
		if (descrDet == null) {
			if (other.descrDet != null)
				return false;
		} else if (!descrDet.equals(other.descrDet))
			return false;
		if (idProdotto != other.idProdotto)
			return false;
		if (idSottocategoria == null) {
			if (other.idSottocategoria != null)
				return false;
		} else if (!idSottocategoria.equals(other.idSottocategoria))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

	public String toString(boolean a) {
		return "Prodotto [idProdotto=" + idProdotto + ", nome=" + nome + ", marca=" + marca + ", descrBr=" + descrBr
				+ ", descrDet=" + descrDet + ", idSottocategoria=" + idSottocategoria + "]";
	}

}
