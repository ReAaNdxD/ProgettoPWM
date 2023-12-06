package it.unirc.db.ecommerce.views;

public class VecAdmin {

	private int idAdmin;
	private String email;
	private String password;
	private int numSottocategorie;

	

	public VecAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VecAdmin(int idAdmin, String email, String password, int numSottocategorie) {
		super();
		this.idAdmin = idAdmin;
		this.email = email;
		this.password = password;
		this.numSottocategorie = numSottocategorie;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumSottocategorie() {
		return numSottocategorie;
	}

	public void setNumSottocategorie(int numSottocategorie) {
		this.numSottocategorie = numSottocategorie;
	}

	@Override
	public String toString() {
		return "VecAdmin [idAdmin=" + idAdmin + ", email=" + email + ", password=" + password + ", numSottocategorie="
				+ numSottocategorie + "]";
	}

}
