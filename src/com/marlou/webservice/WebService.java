package com.marlou.webservice;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.marlou.domaine.Compte;
import com.marlou.domaine.CompteCourant;
import com.marlou.service.IService;

@Path("/ProxiBanque")
public class WebService {
	@EJB
	IService iService;

	@GET
	@Path("/add/{solde}/{decouvert}")
	public boolean addCompte(@PathParam("solde") double solde, @PathParam("decouvert") double decouvert) {
		Compte c = new CompteCourant();
		c.setSolde(solde);
		c.setAutorisationDecouvert(decouvert);
		return iService.addCompte(c);
	}
}
