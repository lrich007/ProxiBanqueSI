package com.marlou.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.marlou.domaine.Client;
import com.marlou.domaine.Compte;
import com.marlou.domaine.CompteException;
import com.marlou.service.ConseillerServiceException;

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
		
		List<Compte> comptes = new ArrayList<>();
		Query query = em.createQuery("select alias from Compte alias WHERE alias.client = :idClient ");
		comptes = query.getResultList();
		return comptes;
	}

	@Override
	public Compte getCompteById(int id) throws CompteOADException {
		// TODO Auto-generated method stub
		return em.find(Compte.class, id);
	}

	@Override
	public void majCompte(int idCompte, double nouveauSolde) throws CompteOADException {
		
		// TODO Auto-generated method stub
		Compte compte = em.find(Compte.class, idCompte);
		compte.setSolde(nouveauSolde);
		em.merge(compte);
	}

	@Override
	public void majComptesAtomique(Compte compte1, Compte compte2) throws CompteOADException {
		// TODO Auto-generated method stub
		em.merge(compte1);
		em.merge(compte2);
	}

	@Override
	public List<Client> getClientsByConseillerAuthName(String authName) throws ClientOADException {
		// TODO Auto-generated method stub
		List<Client> clients = new ArrayList<>();
		Query query = em.createQuery("select alias from Conseiller alias where alias.clients like :authName ");
		clients = query.getResultList();
		return clients;
	}

	@Override
	public List<Client> getTousLesClients() throws ClientOADException {
		// TODO Auto-generated method stub
		List<Client> clients = new ArrayList<>();
		Query query = em.createQuery("select alias from Client alias");
		clients = query.getResultList();
		return clients;
	}

	@Override
	public Client getClientByID(int id) throws ClientOADException {
		// TODO Auto-generated method stub
		return em.find(Client.class, id);
	}

	@Override
	public void majClient(Client client) throws ClientOADException {
		// TODO Auto-generated method stub
		em.merge(client);
	}

	//===================================================================================================================================================
	
	@Override
	public void effectuerVirement(Compte compteADebiter, Compte compteACrediter, double montant) throws ConseillerServiceException {
		// TODO Auto-generated method stub
		if (montant <= 0) {
			throw new ConseillerServiceException("Le montant à débiter doit être positif");
		}

		if (compteADebiter.equals(compteACrediter)) {
			throw new ConseillerServiceException("On ne peut pas effectuer un virement entre même compte");
		}

		double montantADebiter = -montant;
		double montantACrediter = +montant;

		try {
			compteADebiter.modifierSolde(montantADebiter);
			compteACrediter.modifierSolde(montantACrediter);
		} catch (CompteException e) {
			throw new ConseillerServiceException("Erreur lors de la mise à jour du solde");
		}

		/*
		 * La transaction est effectuée de manière atomique pour éviter une mise à
		 * jour partielle des comptes.
		 *
		 * Idéalement on utiliserai les JTA, mais Tomcat ne les fournit pas ; il faut
		 * donc utiliser une unique méthode OAD.
		 */
		try {
			/*
			 * La mise à jour atomique remplace :
			 *
			 * compteOAD.majCompte(compteADebiter.getIdentifiant(),
			 * compteADebiter.getSolde());
			 * compteOAD.majCompte(compteACrediter.getIdentifiant(),
			 * compteACrediter.getSolde());
			 */
			this.majComptesAtomique(compteADebiter, compteACrediter);
		} catch (CompteOADException e) {
			throw new ConseillerServiceException("Erreur lors de la mise à jour du compte dans la base de donnée");
		}
	}

	@Override
	public List<Compte> getComptes(int idClient) throws ConseillerServiceException {
		// TODO Auto-generated method stub
		List<Compte> comptes = new ArrayList<>();
		Query query = em.createQuery("select alias from Compte alias");
		comptes = query.getResultList();
		return comptes;
	}

	@Override
	public void modifierNomClient(Client client, String nom) throws ConseillerServiceException {
		// TODO Auto-generated method stub
		client.setNom(nom);
		em.merge(client);
	}

	@Override
	public void modifierPrenomClient(Client client, String prenom) throws ConseillerServiceException {
		// TODO Auto-generated method stub
		client.setPrenom(prenom);
		em.merge(client);
	}

	@Override
	public void modifierCourrielClient(Client client, String courriel) throws ConseillerServiceException {
		// TODO Auto-generated method stub
		client.setCourriel(courriel);
		em.merge(client);
	}

	@Override
	public void modifierAdresseClient(Client client, String adresse) throws ConseillerServiceException {
		// TODO Auto-generated method stub
		client.setAdresse(adresse);
		em.merge(client);
	}
	


}
