package com.marlou.service;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.marlou.dao.ClientOADException;
import com.marlou.dao.CompteOADException;
import com.marlou.dao.IDao;
import com.marlou.domaine.Client;
import com.marlou.domaine.Compte;

@Stateless(name="ProxiBanque")
public class ServiceImpl implements IService {

	@EJB
    private IDao dao;
	
	@Override
	public boolean addCompte(Compte compte) {
		// TODO Auto-generated method stub
		return dao.addCompte(compte);
	}

	@Override
	public ArrayList<Compte> getComptesByID(int idClient) throws CompteOADException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compte getCompteById(int id) throws CompteOADException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void majCompte(int idCompte, double nouveauSolde) throws CompteOADException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void majComptesAtomique(Compte compte1, Compte compte2) throws CompteOADException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Client> getClientsByConseillerAuthName(String authName) throws ClientOADException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Client> getTousLesClients() throws ClientOADException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client getClientByID(int id) throws ClientOADException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void majClient(Client client) throws ClientOADException {
		// TODO Auto-generated method stub
		
	}

}
