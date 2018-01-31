package com.marlou.domaine;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/** @author Étienne, Sophia et Maria */
@Entity
@DiscriminatorValue("compte_epargne")
public class CompteEpargne extends Compte {

	private static final long serialVersionUID = -2684741663640307269L;
	/** Le taux de rémunération du compte épargne par défaut. */
	private static final double TAUX_REMUNERATION_DEFAUT = 3;
	/**
	 * Le taux de rémunération du compte épargne ; elle est par défaut de 3 %.
	 */
	private double tauxRemuneration;

	/** Pour la sérialisation. */
	public CompteEpargne() {
		tauxRemuneration = TAUX_REMUNERATION_DEFAUT;
	}

	/**
	 * @param identifiant
	 *            l’identifiant unique provenant de la base de donnée
	 * @param solde
	 *            le solde du compte
	 * @param client
	 *            le client détenant le compte
	 */
	public CompteEpargne(int identifiant, double solde, Client client) {
		super(identifiant, solde, client);
	}

	/*
	 * Méthodes d’accès aux attributs
	 */

	/** @return le taux de rémunération annuel du compte */
	public double getTauxRemuneration() {
		return tauxRemuneration;
	}

	/**
	 * @param tauxRemuneration
	 *            le taux de rémunération annuel du compte
	 */
	public void setTauxRemuneration(double tauxRemuneration) {
		this.tauxRemuneration = tauxRemuneration;
	}

	/*
	 * Méthodes génériques
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(tauxRemuneration);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CompteEpargne other = (CompteEpargne) obj;
		if (Double.doubleToLongBits(tauxRemuneration) != Double.doubleToLongBits(other.tauxRemuneration)) {
			return false;
		}
		return true;
	}

}
