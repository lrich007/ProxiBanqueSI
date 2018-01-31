package com.marlou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.marlou.domaine.Compte;
import com.marlou.domaine.CompteCourant;
import com.marlou.domaine.CompteEpargne;
import com.marlou.domaine.TypeCompte;

/** @author Étienne, Sophia et Maria */
public final class CompteOAD implements ICompteOAD {

	/** Singleton de l’instance. */
	private static CompteOAD instance;
	/** Objet pour la connexon à la base de donnée. */
	private DataSource dataSource;

	/**
	 * @param jndiBase
	 *            référence de la base de donnée
	 */
	private CompteOAD(String jndiBase) {

		try {
			InitialContext ic = new InitialContext();
			dataSource = (DataSource) ic.lookup("java:comp/env/" + jndiBase);
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	/** Découvert des comptes d’un client à partir de sa clé primaire. */
	private static final String SELECT_COMPTE_BY_CLIENT_ID = "SELECT id, solde, type FROM Compte WHERE id_client = ?";

	@Override
	public ArrayList<Compte> getComptesByID(int idClient) throws CompteOADException {

		/*
		 * On récupère les comptes du client.
		 */
		ArrayList<Compte> comptes = new ArrayList<Compte>();
		try (Connection connexion = dataSource.getConnection();
				PreparedStatement prstmt = connexion.prepareStatement(SELECT_COMPTE_BY_CLIENT_ID)) {

			prstmt.setInt(1, idClient);
			try (ResultSet rs = prstmt.executeQuery();) {

				while (rs.next()) {
					int id = rs.getInt(1);
					double solde = rs.getDouble(2);
					int type = rs.getInt(3);

					if (type == TypeCompte.COMPTECOURANT.getValeur()) {
						// TODO : Récupérer le Client à l'aide de l'idClient
						comptes.add(new CompteCourant(id, solde, null));
					} else {
						comptes.add(new CompteEpargne(id, solde, null));
					}
				}

			}

		} catch (SQLException e) {
			throw new CompteOADException("Erreur lors de la récupération des comptes du client");
		}

		return comptes;
	}

	/** Découvert d’un compte par sa clé primaire. */
	private static final String SELECT_COMPTE_BY_ID = "SELECT solde, type FROM Compte WHERE id = ?";

	@Override
	public Compte getCompteById(int idCompte) throws CompteOADException {

		try (Connection connexion = dataSource.getConnection();
				PreparedStatement prstmt = connexion.prepareStatement(SELECT_COMPTE_BY_ID)) {

			prstmt.setInt(1, idCompte);
			try (ResultSet rs = prstmt.executeQuery();) {

				// Il n’y a qu’un seul compte correspondant à une clé primaire.
				rs.next();
				double solde = rs.getDouble(1);
				int type = rs.getInt(2);

				if (type == TypeCompte.COMPTECOURANT.getValeur()) {
					// TODO : Récupérer le Client à l'aide de l'idClient
					return new CompteCourant(idCompte, solde, null);
				} else {
					return new CompteEpargne(idCompte, solde, null);
				}
			}

		} catch (SQLException e) {
			throw new CompteOADException("Erreur lors de la récupération du compte");
		}

	}

	/** Mise à jour du solde d’un compte par sa clé primaire. */
	private static final String UPDATE_SOLDE_COMPTE_BY_ID = "UPDATE Compte SET solde = ? WHERE id = ?";

	@Override
	public void majCompte(int idCompte, double nouveauSolde) throws CompteOADException {

		try (Connection connexion = dataSource.getConnection();
				PreparedStatement prstmt = connexion.prepareStatement(UPDATE_SOLDE_COMPTE_BY_ID)) {

			prstmt.setInt(2, idCompte);
			prstmt.setDouble(1, nouveauSolde);
			prstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CompteOADException("La mise à jour du compte a échouée");
		}

	}

	@Override
	public void majComptesAtomique(Compte compte1, Compte compte2) throws CompteOADException {

		try (Connection connexion = dataSource.getConnection();) {

			// On se garde le droit de revenir en arrière en cas d’erreur.
			connexion.setAutoCommit(false);

			try (PreparedStatement prstmt = connexion.prepareStatement(UPDATE_SOLDE_COMPTE_BY_ID)) {

				// Mise à jour du premier compte.
				prstmt.setInt(2, compte1.getIdentifiant());
				prstmt.setDouble(1, compte1.getSolde());
				prstmt.executeUpdate();

				// Mise à jour du second compte.
				prstmt.setInt(2, compte2.getIdentifiant());
				prstmt.setDouble(1, compte2.getSolde());
				prstmt.executeUpdate();

				// Exécution de la modification.
				connexion.commit();

			} catch (SQLException e) {
				connexion.rollback();
				throw new CompteOADException(
						"La mise à jour atomique des deux comptes à échouée ; aucun compte n’a été modifié");
			}

		} catch (SQLException e) {
			throw new CompteOADException(
					"La mise à jour des deux comptes a échouée ; aucun compte n’a été modifié");
		}

	}

	/**
	 * @param jndiBase
	 *            référence de la base de donnée
	 * @return le singleton de l’instance
	 */
	public static CompteOAD getInstance(String jndiBase) {
		if (instance == null) {
			instance = new CompteOAD(jndiBase);
		}
		return instance;
	}

}
