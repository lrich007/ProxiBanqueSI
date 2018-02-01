package com.marlou.dao;

import java.util.List;

import com.marlou.domaine.Client;
import com.marlou.domaine.Compte;
import com.marlou.service.ConseillerServiceException;

public interface IDao {

	boolean addCompte(Compte compte);

	/**
	 * Renvoie la liste des comptes d’un client en fonction de sa clé primaire.
	 *
	 * @param idClient
	 *            la clé primaire du client
	 * @return la liste des compte du client
	 * @throws CompteOADException
	 *             erreur SQL en cas d’échec de la récupération
	 */
	List<Compte> getComptesByID(int idClient) throws CompteOADException;

	/**
	 * Récupère un compte en fonction de sa clé primaire.
	 *
	 * @param id
	 *            la clé primaire du compte
	 * @return le compte correspondant
	 * @throws CompteOADException
	 *             erreur en cas d’échec de la récupération
	 */
	Compte getCompteById(int id) throws CompteOADException;

	/**
	 * Changement du solde d’un compte.
	 *
	 * @param idCompte
	 *            clé primaire du compte à modifier
	 * @param nouveauSolde
	 *            nouveau solde en euros
	 * @throws CompteOADException
	 *             erreur en cas d’échec de la mise à jour
	 */
	void majCompte(int idCompte, double nouveauSolde) throws CompteOADException;

	/**
	 * Changement du solde de deux comptes de manière atomique.
	 *
	 * Utile dans le cas de virement.
	 *
	 * @param compte1
	 *            le premier compte à mettre à jour
	 * @param compte2
	 *            le second compte à mettre à jour
	 * @throws CompteOADException
	 *             erreur en cas d’échec de la mise à jour
	 */
	void majComptesAtomique(Compte compte1, Compte compte2) throws CompteOADException;

	/**
	 * Fournit la liste des clients d’un conseiller en fonction de son identifiant
	 * d’authentification.
	 *
	 * @param authName
	 *            l’identifiant renseigner au moment de l’authentification
	 * @return la liste des clients du conseiller
	 * @throws ClientOADException
	 *             erreur si la requête SQL a échouée
	 */
	List<Client> getClientsByConseillerAuthName(String authName) throws ClientOADException;

	/**
	 * Fournit la liste de tous les clients de la banque.
	 *
	 * @return la liste de tous les clients de la banque
	 * @throws ClientOADException
	 *             erreur si la requête SQL échoue
	 */
	List<Client> getTousLesClients();

	/**
	 * Renvoie un client à partir de son identifiant unique dans la base de
	 * donnée.
	 *
	 * @param id
	 *            l’identifiant dans la base de donnée du client à retourner
	 * @return le client
	 * @throws ClientOADException
	 *             erreur si la requête SQL échoue
	 */
	Client getClientByID(int id) throws ClientOADException;

	/**
	 * Met à jour un client dans la base de donnée.
	 *
	 * @param client
	 *            le client à mettre à jour
	 * @throws ClientOADException
	 *             erreur si la mise à jour a échouée
	 */
	void majClient(Client client) throws ClientOADException;
	
	//================================================================================================================================================================
	
	/**
	 * Effectue un virement de compte à compte. Il faut que les deux comptes soient
	 * domiciliés dans la banque. Les conseillers peuvent effectuer des virement
	 * depuis et vers des comptes de clients qui ne leur sont pas assignés.
	 *
	 * @param compteADebiter
	 *            le compte à débiter
	 * @param compteACrediter
	 *            le compte à créditer
	 * @param montant
	 *            le montant, positif et en euros, du virement
	 * @throws ConseillerServiceException
	 *             erreur si le montant est négatif
	 * @throws ConseillerServiceException
	 *             erreur si la modification du solde a échouée
	 */
	public void effectuerVirement(Compte compteADebiter, Compte compteACrediter, double montant) throws ConseillerServiceException;
	

	
	/**
	 * Retourne la liste des comptes d’un client.
	 *
	 * @param idClient
	 *            la clé primaire du client
	 * @return la liste de ses comptes
	 * @throws ConseillerServiceException
	 *             l’erreur si la récupération a échouée
	 */
	public List<Compte> getComptes(int idClient) throws ConseillerServiceException;
	

	
	/**
	 * Modifie le nom d’un client.
	 *
	 * @param client
	 *            le client à modifier
	 * @param nom
	 *            le nouveau nom
	 * @throws ConseillerServiceException
	 *             erreur si la récupération a échouée
	 */
	public void modifierNomClient(Client client, String nom) throws ConseillerServiceException;
	
	/**
	 * Modifie le prénom d’un client.
	 *
	 * @param client
	 *            le client à modifier
	 * @param prenom
	 *            le nouveau prénom
	 * @throws ConseillerServiceException
	 *             erreur si la récupération a échouée
	 */
	public void modifierPrenomClient(Client client, String prenom) throws ConseillerServiceException;
	
	/**
	 * Modifie l’adresse de messagerie d’un client.
	 *
	 * @param client
	 *            le client à modifier
	 * @param courriel
	 *            la nouvelle adresse de messagerie
	 * @throws ConseillerServiceException
	 *             erreur si la récupération a échouée
	 */
	public void modifierCourrielClient(Client client, String courriel) throws ConseillerServiceException;
	
	/**
	 * Modifie l’adresse d’un client.
	 *
	 * @param client
	 *            le client à modifier
	 * @param adresse
	 *            la nouvelle adresse
	 * @throws ConseillerServiceException
	 *             erreur si la récupération a échouée
	 */
	public void modifierAdresseClient(Client client, String adresse) throws ConseillerServiceException;
	

}
