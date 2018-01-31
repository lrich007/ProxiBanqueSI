package com.marlou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.marlou.domaine.Client;

/** @author Étienne, Sophia et Maria */
public final class ClientOAD implements IClientOAD {

	/** Singleton de l’instance. */
	private static ClientOAD instance;
	/** Objet pour la connexon à la base de donnée. */
	private DataSource dataSource;

	/**
	 * @param jndiBase
	 *            référence de la base de donnée
	 */
	private ClientOAD(String jndiBase) {

		try {
			InitialContext ic = new InitialContext();
			dataSource = (DataSource) ic.lookup("java:comp/env/" + jndiBase);
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Découverte de la clé primaire d’un conseiller en fonction de son
	 * identifiant.
	 */
	private static final String SELECT_CONSEILLER_BY_AUTHNAME = "SELECT id FROM Conseiller where courriel = ?";
	/**
	 * Découverte des clients d’un conseiller en fonction de sa clé primaire.
	 */
	private static final String SELECT_CLIENTS_BY_CONSEILLER_ID = "SELECT c.* FROM Gerer g JOIN Client c ON g.id_client=c.id WHERE g.id = ?";

	@Override
	public ArrayList<Client> getClientsByConseillerAuthName(String authName) throws ClientOADException {

		/*
		 * On récupèr l’identifiant du conseiller.
		 */
		int idConseiller = 0;
		try (Connection connexion = dataSource.getConnection();
				PreparedStatement prstmt = connexion.prepareStatement(SELECT_CONSEILLER_BY_AUTHNAME);) {

			prstmt.setString(1, authName);
			try (ResultSet rs = prstmt.executeQuery();) {
				// TODO : Voir si on test le cas où plus d’un conseiller correspond à un
				// identifiant
				rs.next();
				idConseiller = rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new ClientOADException("Erreur SQL lors de la récupération de l’identifiant du conseiller");
		}

		/*
		 * On récupère la liste des clients.
		 */
		ArrayList<Client> clients = new ArrayList<Client>();
		try (Connection connexion = dataSource.getConnection();
				PreparedStatement prstmt = connexion.prepareStatement(SELECT_CLIENTS_BY_CONSEILLER_ID);) {

			prstmt.setInt(1, idConseiller);
			try (ResultSet rs = prstmt.executeQuery();) {
				// TODO : Coder une méthode générique pour récupérer les comptes
				while (rs.next()) {
					clients.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), null));
				}
			}

		} catch (SQLException e) {
			throw new ClientOADException("Erreur SQL lors de la récupération des clients du conseiller");
		}

		return clients;
	}

	/** Découverte de tous les clients. */
	private static final String SELECT_TOUS_LES_CLIENTS = "SELECT * FROM Client";

	@Override
	public ArrayList<Client> getTousLesClients() throws ClientOADException {

		ArrayList<Client> clients = new ArrayList<Client>();
		try (Connection connexion = dataSource.getConnection();
				PreparedStatement prstmt = connexion.prepareStatement(SELECT_TOUS_LES_CLIENTS);
				ResultSet rs = prstmt.executeQuery();) {

			// TODO : Coder une méthode générique pour récupérer les comptes
			while (rs.next()) {
				clients.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						null));
			}

		} catch (SQLException e) {
			throw new ClientOADException("Erreur SQL lors de la récupération des clients du conseiller");
		}

		return clients;
	}

	/** Découverte d’un client en fonction de sa clé primaire. */
	private static final String SELECT_CLIENT_BY_ID = "SELECT * FROM Client WHERE id = ?";

	@Override
	public Client getClientByID(int id) throws ClientOADException {

		Client client = null;
		try (Connection connexion = dataSource.getConnection();
				PreparedStatement prstmt = connexion.prepareStatement(SELECT_CLIENT_BY_ID)) {

			prstmt.setInt(1, id);
			try (ResultSet rs = prstmt.executeQuery();) {
				rs.next();
				client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						null);
			}

		} catch (SQLException e) {
			throw new ClientOADException("Erreur SQL lors de la récupération du client");
		}

		return client;

	}

	/** Mise à jour d’un client à partir de sa clé primaire. */
	private static final String UPDATE_NOM_CLIENT = "UPDATE Client SET nom=?, prenom=?, courriel=?, adresse=? WHERE id = ?";

	@Override
	public void majClient(Client client) throws ClientOADException {
		try (Connection connexion = dataSource.getConnection();
				PreparedStatement prstmt = connexion.prepareStatement(UPDATE_NOM_CLIENT)) {

			prstmt.setString(1, client.getNom());
			prstmt.setString(2, client.getPrenom());
			prstmt.setString(3, client.getCourriel());
			prstmt.setString(4, client.getAdresse());
			prstmt.setInt(5, client.getIdentifiant());
			prstmt.executeUpdate();

		} catch (SQLException e) {
			throw new ClientOADException("Erreur SQL lors de la modification d’un client");
		}

	}

	/**
	 * @param jndiBase
	 *            référence de la base de donnée
	 * @return le singleton de l’instance
	 */
	public static ClientOAD getInstance(String jndiBase) {
		if (instance == null) {
			instance = new ClientOAD(jndiBase);
		}
		return instance;
	}

}
