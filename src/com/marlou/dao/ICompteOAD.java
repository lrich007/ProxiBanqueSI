package com.marlou.dao;

import java.util.ArrayList;

import com.marlou.domaine.Compte;

/** @author Étienne, Sophia et Maria */
public interface ICompteOAD {

	/**
	 * Renvoie la liste des comptes d’un client en fonction de sa clé primaire.
	 *
	 * @param idClient
	 *            la clé primaire du client
	 * @return la liste des compte du client
	 * @throws CompteOADException
	 *             erreur SQL en cas d’échec de la récupération
	 */
	ArrayList<Compte> getComptesByID(int idClient) throws CompteOADException;

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
}
