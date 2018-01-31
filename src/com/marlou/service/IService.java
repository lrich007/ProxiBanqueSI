package com.marlou.service;

import java.util.List;

import com.marlou.dao.ClientOADException;
import com.marlou.dao.CompteOADException;
import com.marlou.domaine.Client;
import com.marlou.domaine.Compte;

public interface IService {

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
	List<Client> getTousLesClients() throws ClientOADException;

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

}
