package it.unirc.db.ecommerce.beans;

public class Admin {
	private String nomeAdmin;
	private String passwordAdmin;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(String nomeAdmin, String passwordAdmin) {
		super();
		this.nomeAdmin = nomeAdmin;
		this.passwordAdmin = passwordAdmin;
	}
	public String getNomeAdmin() {
		return nomeAdmin;
	}
	public void setNomeAdmin(String nomeAdmin) {
		this.nomeAdmin = nomeAdmin;
	}
	public String getPasswordAdmin() {
		return passwordAdmin;
	}
	public void setPasswordAdmin(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
	}
	@Override
	public String toString() {
		return nomeAdmin;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeAdmin == null) ? 0 : nomeAdmin.hashCode());
		result = prime * result + ((passwordAdmin == null) ? 0 : passwordAdmin.hashCode());
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
		Admin other = (Admin) obj;
		if (nomeAdmin == null) {
			if (other.nomeAdmin != null)
				return false;
		} else if (!nomeAdmin.equals(other.nomeAdmin))
			return false;
		if (passwordAdmin == null) {
			if (other.passwordAdmin != null)
				return false;
		} else if (!passwordAdmin.equals(other.passwordAdmin))
			return false;
		return true;
	}
	

}
