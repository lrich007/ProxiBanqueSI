package com.marlou.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.marlou.domaine.Client;
import com.marlou.domaine.Compte;

@Singleton
public class DaoImpl implements IDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public boolean addCompte(Compte compte) {
		// TODO Auto-generated method stub
		try {

			em.persist(compte);

			return true;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<Compte> getComptesByID(int idClient) throws CompteOADException {
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
	public List<Client> getClientsByConseillerAuthName(String authName) throws ClientOADException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getTousLesClients() throws ClientOADException {
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
