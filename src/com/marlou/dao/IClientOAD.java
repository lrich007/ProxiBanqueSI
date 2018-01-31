package com.marlou.dao;

import java.util.ArrayList;

import com.marlou.domaine.Client;

/** @author Étienne, Sophia et Maria */
public interface IClientOAD {

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
	ArrayList<Client> getClientsByConseillerAuthName(String authName) throws ClientOADException;

	/**
	 * Fournit la liste de tous les clients de la banque.
	 *
	 * @return la liste de tous les clients de la banque
	 * @throws ClientOADException
	 *             erreur si la requête SQL échoue
	 */
	ArrayList<Client> getTousLesClients() throws ClientOADException;

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
