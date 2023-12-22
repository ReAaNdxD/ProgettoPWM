package it.unirc.pwm.actions;

import com.opensymphony.xwork2.ActionSupport;

import it.unirc.pwm.ht.Cliente;
import it.unirc.pwm.ht.dao.ClienteDAO;
import it.unirc.pwm.ht.dao.ClienteDAOFactory;

public class RegistraNuovoCliente extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	
	public String execute(){
		ClienteDAO cDAO = ClienteDAOFactory.getDAO();
		if(cDAO.salva(cliente))
			return SUCCESS;
		else 
			return ERROR;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente=cliente;
	}
	
	
}
