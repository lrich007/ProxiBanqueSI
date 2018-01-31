package com.marlou.domaine;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

/** @author Étienne, Sophia, Maria, Louis */
@Entity
@Inheritance
@DiscriminatorColumn(name = "compte_type")
public abstract class Compte implements java.io.Serializable {

	public double getAutorisationDecouvert() {
		return autorisationDecouvert;
	}

	public void setAutorisationDecouvert(double autorisationDecouvert) {
		this.autorisationDecouvert = autorisationDecouvert;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	private static final long serialVersionUID = 4368759148699972357L;
	/** Identifiant unique du compte provenant de la base de donnée. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int identifiant;
	/** Solde du compte. */
	private double solde;
	/** Le montant positif maximal de découvert. */
	protected double autorisationDecouvert;
	/** Client qui possède le compte. */
	private Client client;

	/** Nécessaire pour la sérialisation de la classe. */
	public Compte() {
		this.identifiant = 0;
		this.solde = 0;
		this.autorisationDecouvert = 0;
		this.client = null;
	}

	/**
	 * Compte en banque d’un client de la banque.
	 *
	 * @param identifiant
	 *            Identifiant du compte
	 * @param solde
	 *            Solde du compte
	 * @param client
	 *            Client possédant le compte
	 */
	public Compte(int identifiant, double solde, Client client) {
		this.identifiant = identifiant;
		this.solde = solde;
		this.autorisationDecouvert = 0;
		this.client = client;
	}

	/*
	 * Méthodes d’accès aux attributs
	 */

	/** @return le solde du compte en euros */
	public double getSolde() {
		return solde;
	}

	/** @return le client qui détient le compte */
	public Client getClient() {
		return client;
	}

	/** @return l’identifiant dans la base de donnée */
	public int getIdentifiant() {
		return identifiant;
	}

	/*
	 * Méthodes génériques
	 */

	@Override
	public String toString() {
		return "[solde=" + solde + ", client=" + client + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + identifiant;
		long temp;
		temp = Double.doubleToLongBits(solde);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Compte other = (Compte) obj;
		if (client == null) {
			if (other.client != null) {
				return false;
			}
		} else if (!client.equals(other.client)) {
			return false;
		}
		if (identifiant != other.identifiant) {
			return false;
		}
		if (Double.doubleToLongBits(solde) != Double.doubleToLongBits(other.solde)) {
			return false;
		}
		return true;
	}

	/*
	 * Méthodes spécialisées
	 */

	/**
	 * Modifie le solde après vérification de la faisabilité.
	 *
	 * @param montant
	 *            Montant à ajouter au solde courant
	 * @throws CompteException
	 *             Exception si le découvert est trop important
	 */
	public void modifierSolde(double montant) throws CompteException {
		double soldeFinal;
		soldeFinal = solde + montant;
		if (soldeFinal > -autorisationDecouvert) {
			solde = soldeFinal;
		} else {
			throw new CompteException("Autorisation de découvert insuffisante — virement refusé");
		}
	}

}
