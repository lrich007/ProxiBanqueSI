package com.marlou.domaine;

/** @author Étienne, Sophia et Maria */
public enum TypeCompte {
	/** Compte courant. */
	COMPTECOURANT(0),
	/** Compte bancaire. */
	COMPTEEPARGNE(1);

	/** Entier correspondant à l’enum. */
	private final int valeur;

	/**
	 * @param valeur
	 *            la valeur correspondante dans la base de donnée
	 */
	TypeCompte(final int valeur) {
		this.valeur = valeur;
	}

	/*
	 * Méthodes d’accès aux attributs
	 */

	/** @return l’entier correspondant */
	public int getValeur() {
		return valeur;
	}

}
