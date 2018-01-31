package com.marlou.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.marlou.dao.CompteOADException;
import com.marlou.domaine.Compte;
import com.marlou.domaine.CompteCourant;
import com.marlou.service.IService;
import com.marlou.service.ServiceImpl;

@Path("/ProxiBanque")
public class WebService {
	
	@EJB
	IService iService;

//	@GET
//	@Path("/add/{solde}/{decouvert}")
//	public boolean addCompte(@PathParam("solde") double solde, @PathParam("decouvert") double decouvert) {
//		Compte c = new CompteCourant();
//		c.setSolde(solde);
//		c.setAutorisationDecouvert(decouvert);
//		return iService.addCompte(c);
//	}
	
	@GET
	@Path("/listcompte/{idclient}")
	@Produces(MediaType.TEXT_PLAIN)
	public List<Compte> getComptesByID(@PathParam("idclient") int idClient) throws CompteOADException{
		System.out.println("Coucou" + iService.getComptesByID(idClient) );
		return iService.getComptesByID(idClient);
	}
	
}
