package it.unirc.pwm.actions;

import com.opensymphony.xwork2.ActionSupport;

import it.unirc.pwm.ht.Cliente;

public class RegistraNuovoCliente extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
		
	public String execute(){
		if(Math.random()<0.5) {
			return SUCCESS;
		}
		return ERROR;
		//l'oggetto user � inizializzato da struts
		
	}
	
	public Cliente getCliente(){
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
