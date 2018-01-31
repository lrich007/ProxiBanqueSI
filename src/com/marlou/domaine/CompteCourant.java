package com.marlou.domaine;

/** @author Étienne, Sophia et Maria */
public class CompteCourant extends Compte {

	private static final long serialVersionUID = 2618352047701627204L;
	/** Autorisation de découvert par défaut de 1000 €. */
	private static final int AUTORISATION_DECOUVERT_DEFAUT = 1000;

	/** Pour la sérialisation. */
	public CompteCourant() {
	}

	/**
	 * Initialise un compte courant avec une autorisation de découvert de
	 * 1000 €.
	 *
	 * @param identifiant
	 *            Identifiant unique provenant de la base de donnée
	 * @param solde
	 *            Solde du compte
	 * @param client
	 *            Client possédant le compte
	 */
	public CompteCourant(int identifiant, double solde, Client client) {
		super(identifiant, solde, client);
		this.autorisationDecouvert = AUTORISATION_DECOUVERT_DEFAUT;
	}

	/*
	 * Méthodes d’accès aux attributs
	 */

	/** @return le découvert maximal autorisé en euros */
	public double getAutorisationDecouvert() {
		return autorisationDecouvert;
	}

	/**
	 * @param autorisationDecouvert
	 *            le découvert maximal autorisé en euros
	 */
	public void setAutorisationDecouvert(double autorisationDecouvert) {
		this.autorisationDecouvert = autorisationDecouvert;
	}

}
