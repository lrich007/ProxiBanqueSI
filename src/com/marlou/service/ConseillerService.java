//package com.marlou.service;
//
//import java.util.ArrayList;
//
//import com.marlou.dao.ClientOAD;
//import com.marlou.dao.ClientOADException;
//import com.marlou.dao.CompteOAD;
//import com.marlou.dao.CompteOADException;
//import com.marlou.domaine.Client;
//import com.marlou.domaine.Compte;
//import com.marlou.domaine.CompteException;
//
///** @author Étienne, Sophia et Maria */
//public final class ConseillerService {
//
//	/** Singleton pour l’accès la classe. */
//	private static ConseillerService instance;
//	/** Singleton pour les comptes dans la base de donnée. */
//	private CompteOAD compteOAD;
//	/** Singleton pour les clients dans la base de donnée. */
//	private ClientOAD clientOAD;
//
//	/**
//	 * @param jndiBase
//	 *            référence de la base de donnée
//	 */
//	private ConseillerService(String jndiBase) {
//		compteOAD = CompteOAD.getInstance(jndiBase);
//		clientOAD = ClientOAD.getInstance(jndiBase);
//	}
//
//	/**
//	 * Effectue un virement de compte à compte. Il faut que les deux comptes soient
//	 * domiciliés dans la banque. Les conseillers peuvent effectuer des virement
//	 * depuis et vers des comptes de clients qui ne leur sont pas assignés.
//	 *
//	 * @param compteADebiter
//	 *            le compte à débiter
//	 * @param compteACrediter
//	 *            le compte à créditer
//	 * @param montant
//	 *            le montant, positif et en euros, du virement
//	 * @throws ConseillerServiceException
//	 *             erreur si le montant est négatif
//	 * @throws ConseillerServiceException
//	 *             erreur si la modification du solde a échouée
//	 */
//	public void effectuerVirement(Compte compteADebiter, Compte compteACrediter, double montant)
//			throws ConseillerServiceException {
//
//		if (montant <= 0) {
//			throw new ConseillerServiceException("Le montant à débiter doit être positif");
//		}
//
//		if (compteADebiter.equals(compteACrediter)) {
//			throw new ConseillerServiceException("On ne peut pas effectuer un virement entre même compte");
//		}
//
//		double montantADebiter = -montant;
//		double montantACrediter = +montant;
//
//		try {
//			compteADebiter.modifierSolde(montantADebiter);
//			compteACrediter.modifierSolde(montantACrediter);
//		} catch (CompteException e) {
//			throw new ConseillerServiceException("Erreur lors de la mise à jour du solde");
//		}
//
//		/*
//		 * La transaction est effectuée de manière atomique pour éviter une mise à
//		 * jour partielle des comptes.
//		 *
//		 * Idéalement on utiliserai les JTA, mais Tomcat ne les fournit pas ; il faut
//		 * donc utiliser une unique méthode OAD.
//		 */
//		try {
//			/*
//			 * La mise à jour atomique remplace :
//			 *
//			 * compteOAD.majCompte(compteADebiter.getIdentifiant(),
//			 * compteADebiter.getSolde());
//			 * compteOAD.majCompte(compteACrediter.getIdentifiant(),
//			 * compteACrediter.getSolde());
//			 */
//			compteOAD.majComptesAtomique(compteADebiter, compteACrediter);
//		} catch (CompteOADException e) {
//			throw new ConseillerServiceException("Erreur lors de la mise à jour du compte dans la base de donnée");
//		}
//
//	}
//
//	/**
//	 * Fournit la liste des clients d’un conseiller en fonction de son identifiant
//	 * d’authentification.
//	 *
//	 * @param authName
//	 *            l’identifiant renseigner au moment de l’authentification
//	 * @return la liste des clients du conseiller
//	 * @throws ConseillerServiceException
//	 *             erreur si la récupération a échouée
//	 */
//	public ArrayList<Client> getClientsByConseillerAuthName(String authName) throws ConseillerServiceException {
//		try {
//			return clientOAD.getClientsByConseillerAuthName(authName);
//		} catch (ClientOADException e) {
//			throw new ConseillerServiceException("La récupération de la liste de clients a échouée");
//		}
//	}
//
//	/**
//	 * Fournit la liste de tous les clients de la banque.
//	 *
//	 * @return la liste de tous les clients
//	 * @throws ConseillerServiceException
//	 *             erreur si la récupération a échouée
//	 */
//	public ArrayList<Client> getTousLesClients() throws ConseillerServiceException {
//		try {
//			return clientOAD.getTousLesClients();
//		} catch (ClientOADException e) {
//			throw new ConseillerServiceException("La récupération de la liste de clients a échouée");
//		}
//	}
//
//	/**
//	 * Retourne la liste des comptes d’un client.
//	 *
//	 * @param idClient
//	 *            la clé primaire du client
//	 * @return la liste de ses comptes
//	 * @throws ConseillerServiceException
//	 *             l’erreur si la récupération a échouée
//	 */
//	public ArrayList<Compte> getComptes(int idClient) throws ConseillerServiceException {
//
//		try {
//			return compteOAD.getComptesByID(idClient);
//
//		} catch (CompteOADException e) {
//			throw new ConseillerServiceException("Erreur lors de la récupération des comptes du client");
//		}
//
//	}
//
//	/**
//	 * Récupère un compte à partir de sa clé primaire.
//	 *
//	 * @param idCompte
//	 *            la clé primaire du compte
//	 * @return le compte correspondant
//	 * @throws ConseillerServiceException
//	 *             l’erreur si la récupération a échouée
//	 */
//	public Compte getCompteById(int idCompte) throws ConseillerServiceException {
//		try {
//			return compteOAD.getCompteById(idCompte);
//		} catch (CompteOADException e) {
//			throw new ConseillerServiceException("Erreur lors de la récupération du compte");
//		}
//
//	}
//
//	/**
//	 * Renvoie un client à partir de son identifiant unique dans la base de
//	 * donnée.
//	 *
//	 * @param id
//	 *            l’identifiant du client à retourner
//	 * @return le client
//	 * @throws ConseillerServiceException
//	 *             erreur si la récupération a échouée
//	 */
//	public Client getClientByID(int id) throws ConseillerServiceException {
//		try {
//			return clientOAD.getClientByID(id);
//		} catch (ClientOADException e) {
//			throw new ConseillerServiceException("La récupération du client a échouée");
//		}
//
//	}
//
//	/**
//	 * Modifie le nom d’un client.
//	 *
//	 * @param client
//	 *            le client à modifier
//	 * @param nom
//	 *            le nouveau nom
//	 * @throws ConseillerServiceException
//	 *             erreur si la récupération a échouée
//	 */
//	public void modifierNomClient(Client client, String nom) throws ConseillerServiceException {
//		client.setNom(nom);
//		majClient(client);
//	}
//
//	/**
//	 * Modifie le prénom d’un client.
//	 *
//	 * @param client
//	 *            le client à modifier
//	 * @param prenom
//	 *            le nouveau prénom
//	 * @throws ConseillerServiceException
//	 *             erreur si la récupération a échouée
//	 */
//	public void modifierPrenomClient(Client client, String prenom) throws ConseillerServiceException {
//		client.setPrenom(prenom);
//		majClient(client);
//	}
//
//	/**
//	 * Modifie l’adresse de messagerie d’un client.
//	 *
//	 * @param client
//	 *            le client à modifier
//	 * @param courriel
//	 *            la nouvelle adresse de messagerie
//	 * @throws ConseillerServiceException
//	 *             erreur si la récupération a échouée
//	 */
//	public void modifierCourrielClient(Client client, String courriel) throws ConseillerServiceException {
//		client.setCourriel(courriel);
//		majClient(client);
//	}
//
//	/**
//	 * Modifie l’adresse d’un client.
//	 *
//	 * @param client
//	 *            le client à modifier
//	 * @param adresse
//	 *            la nouvelle adresse
//	 * @throws ConseillerServiceException
//	 *             erreur si la récupération a échouée
//	 */
//	public void modifierAdresseClient(Client client, String adresse) throws ConseillerServiceException {
//		client.setAdresse(adresse);
//		majClient(client);
//	}
//
//	/**
//	 * Mise à jour d’un client.
//	 *
//	 * @param client
//	 *            le client à mettre à jour
//	 * @throws ConseillerServiceException
//	 *             erreur si la récupération a échouée
//	 */
//	public void majClient(Client client) throws ConseillerServiceException {
//		try {
//			clientOAD.majClient(client);
//		} catch (ClientOADException e) {
//			throw new ConseillerServiceException("La mise à jour du client a échouée");
//		}
//	}
//
//	/**
//	 * @param jndiBase
//	 *            la référence de la base de donnée
//	 * @return le singleton de l’instance
//	 */
//	public static ConseillerService getInstance(String jndiBase) {
//		if (instance == null) {
//			instance = new ConseillerService(jndiBase);
//		}
//		return instance;
//	}
//
//}
