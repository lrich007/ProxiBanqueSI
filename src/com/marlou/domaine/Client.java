package com.marlou.domaine;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/** @author Étienne, Sophia, Maria, Louis */
@Entity
public class Client implements java.io.Serializable {

	private static final long serialVersionUID = -525793213189035105L;
	/** Identifiant unique du client provenant de la base de donnée. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int identifiant;
	/** Nom du client. */
	private String nom;
	/** Prénom du client. */
	private String prenom;
	/** Adresse de messagerie électronique du client. */
	private String courriel;
	/** Adresse du client. */
	private String adresse;
	/** Liste des comptes d’un client, entre un et deux. */
	@OneToMany(mappedBy = "client")
	private List<Compte> comptes;
	@ManyToOne
	private Conseiller conseiller;

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	/** Nécessaire pour la sérialisation de la classe. */
	public Client() {

	}

	/**
	 * Création d’un client avec la liste des comptes qu’il détient.
	 *
	 * @param identifiant
	 *            Identifiant unique provenant de la base de donnée
	 * @param nom
	 *            Nom du client
	 * @param prenom
	 *            Prénom du client
	 * @param courriel
	 *            Courriel du client
	 * @param adresse
	 *            Adresse du client
	 * @param comptes
	 *            Liste des compte du client
	 */
	public Client(int identifiant, String nom, String prenom, String courriel, String adresse, List<Compte> comptes) {
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
		this.courriel = courriel;
		this.adresse = adresse;
		this.comptes = comptes;
	}

	/*
	 * Méthodes d’accès aux attributs
	 */

	/** @return le nom */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 *            le nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** @return le prénom */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom
	 *            le prénom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/** @return l’adresse de messagerie électronique */
	public String getCourriel() {
		return courriel;
	}

	/**
	 * @param courriel
	 *            l’adresse de messagerie électronique
	 */
	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	/** @return l’adresse */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse
	 *            l’adresse
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/** @return la liste des comptes */
	public List<Compte> getComptes() {
		return comptes;
	}

	/**
	 * @param comptes
	 *            la liste des comptes
	 */
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	/** @return l’identifiant dans la base de donnée */
	public int getIdentifiant() {
		return identifiant;
	}

	/*
	 * Méthodes génériques
	 */

	// @Override
	// public String toString() {
	// return "Client [identifiant=" + identifiant + ", nom=" + nom + ", prenom=" +
	// prenom + ", courriel=" + courriel
	// + ", adresse=" + adresse + ", comptes=" + comptes + "]";
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((courriel == null) ? 0 : courriel.hashCode());
		result = prime * result + identifiant;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
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
		Client other = (Client) obj;
		if (adresse == null) {
			if (other.adresse != null) {
				return false;
			}
		} else if (!adresse.equals(other.adresse)) {
			return false;
		}
		if (courriel == null) {
			if (other.courriel != null) {
				return false;
			}
		} else if (!courriel.equals(other.courriel)) {
			return false;
		}
		if (identifiant != other.identifiant) {
			return false;
		}
		if (nom == null) {
			if (other.nom != null) {
				return false;
			}
		} else if (!nom.equals(other.nom)) {
			return false;
		}
		if (prenom == null) {
			if (other.prenom != null) {
				return false;
			}
		} else if (!prenom.equals(other.prenom)) {
			return false;
		}
		return true;
	}

}
