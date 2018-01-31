package com.marlou.domaine;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/** @author Étienne, Sophia, Maria, Louis */
@Entity
public class Conseiller implements java.io.Serializable {

	private static final long serialVersionUID = 1010647939843647552L;
	/** Le nombre maximal de clients d’un conseiller. */
	private static final int MAX_CLIENTS = 10;
	/** Identifiant unique du conseiller provenant de la base de donnée. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int identifiant;
	/** Nom du conseiller. */
	private String nom;
	/** Prénom du conseiller. */
	private String prenom;
	/**
	 * Adresse de messagerie électronique du conseiller ; elle tient aussi lieu
	 * d’identifiant pour son authentification.
	 */
	private String courriel;
	/** Mot de passe pour l’authentification du conseiller. */
	private String motDePasse;
	/** Clients du conseiller ; il ne doit pas en y avoir plus de dix. */
	@OneToMany(mappedBy = "conseiller")
	private List<Client> clients;

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	/** Nécessaire pour la sérialisation de la classe. */
	public Conseiller() {

	}

	/**
	 * Initialise un conseiller sans fournir une liste de clients.
	 *
	 * @param identifiant
	 *            Identifiant unique provenant de la base de donnée
	 * @param nom
	 *            Nom du conseiller
	 * @param prenom
	 *            Prénom du conseiller
	 * @param courriel
	 *            Courriel du conseiller
	 * @param motDePasse
	 *            Mot de passe pour l’authentification du conseiller
	 */
	public Conseiller(int identifiant, String nom, String prenom, String courriel, String motDePasse) {
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
		this.courriel = courriel;
		this.motDePasse = motDePasse;
	}

	/**
	 * Initialise un conseiller avec une liste de clients.
	 *
	 * @param identifiant
	 *            Identifiant provenant de la base de donnée
	 * @param nom
	 *            Nom du conseiller
	 * @param prenom
	 *            Prénom du conseiller
	 * @param courriel
	 *            Courriel du conseiller
	 * @param motDePasse
	 *            Mot de passe pour l’authentification du conseiller
	 * @param clients
	 *            Liste de clients du conseiller
	 */
	public Conseiller(int identifiant, String nom, String prenom, String courriel, String motDePasse,
			List<Client> clients) {
		this(identifiant, nom, prenom, courriel, motDePasse);
		this.clients = clients;
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

	/** @return le mot de passe */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * @param motDePasse
	 *            le mot de passe
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/** @return la liste de clients */
	public List<Client> getClients() {
		return clients;
	}

	/**
	 * @param clients
	 *            la liste de clients
	 */
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	/** @return l’identifiant dans la base de donnée */
	public int getIdentifiant() {
		return identifiant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clients == null) ? 0 : clients.hashCode());
		result = prime * result + ((courriel == null) ? 0 : courriel.hashCode());
		result = prime * result + identifiant;
		result = prime * result + ((motDePasse == null) ? 0 : motDePasse.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	/*
	 * Méthodes génériques
	 */

	@Override
	public String toString() {
		return prenom + " " + nom + " (conseiller)";
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
		Conseiller other = (Conseiller) obj;
		if (clients == null) {
			if (other.clients != null) {
				return false;
			}
		} else if (!clients.equals(other.clients)) {
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
		if (motDePasse == null) {
			if (other.motDePasse != null) {
				return false;
			}
		} else if (!motDePasse.equals(other.motDePasse)) {
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

	/**
	 * Ajouter un client à la liste des clients du conseiller.
	 *
	 * @param client
	 *            Le client
	 * @throws ClientException
	 *             Exception si le nombre de clients maximum est atteint
	 */
	public void ajouterClient(Client client) throws ClientException {
		if (clients.size() < MAX_CLIENTS) {
			clients.add(client);
		} else {
			throw new ClientException("Un conseiller ne peux avoir plus de dix client");
		}
	}

}
