package it.unirc.pwm.actions;

import com.opensymphony.xwork2.ActionSupport;

import it.unirc.pwm.ht.Cliente;
import it.unirc.pwm.ht.dao.ClienteDAO;
import it.unirc.pwm.ht.dao.ClienteDAOFactory;

public class RegistraNuovoCliente2 extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Cliente cliente;

	public String execute(){
		ClienteDAO cDAO = ClienteDAOFactory.getDAO();
		if (!cDAO.checkEmail(this.cliente.getEmail())) {
			System.out.println("ciao");
//			se non ha inserito un email che già c'è nel DB
			if (cDAO.salva(cliente))
				return SUCCESS;
		}
		return ERROR;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
