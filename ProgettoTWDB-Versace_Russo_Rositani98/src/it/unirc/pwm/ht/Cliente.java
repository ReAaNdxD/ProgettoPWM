package it.unirc.pwm.ht;
// Generated 7 dic 2023, 12:33:12 by Hibernate Tools 6.1.3.Final

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cliente generated by hbm2java
 */
public class Cliente implements java.io.Serializable {

	private Integer idCliente;
	private String email;
	private String password;
	private String nome;
	private String cognome;
	private Date dataDiNascita;
	private Clienteprime clienteprime;
	private Set carrellos = new HashSet(0);
	private Set indirizzospediziones = new HashSet(0);
	private Set cartacreditos = new HashSet(0);

	public Cliente() {
	}

	public Cliente(String email, String password, String nome, String cognome, Date dataDiNascita) {
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
	}

	public Cliente(String email, String password, String nome, String cognome, Date dataDiNascita,
			Clienteprime clienteprime, Set carrellos, Set indirizzospediziones, Set cartacreditos) {
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.clienteprime = clienteprime;
		this.carrellos = carrellos;
		this.indirizzospediziones = indirizzospediziones;
		this.cartacreditos = cartacreditos;
	}

	public Integer getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataDiNascita() {
		return this.dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public Clienteprime getClienteprime() {
		return this.clienteprime;
	}

	public void setClienteprime(Clienteprime clienteprime) {
		this.clienteprime = clienteprime;
	}

	public Set getCarrellos() {
		return this.carrellos;
	}

	public void setCarrellos(Set carrellos) {
		this.carrellos = carrellos;
	}

	public Set getIndirizzospediziones() {
		return this.indirizzospediziones;
	}

	public void setIndirizzospediziones(Set indirizzospediziones) {
		this.indirizzospediziones = indirizzospediziones;
	}

	public Set getCartacreditos() {
		return this.cartacreditos;
	}

	public void setCartacreditos(Set cartacreditos) {
		this.cartacreditos = cartacreditos;
	}

}