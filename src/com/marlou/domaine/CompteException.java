package com.marlou.domaine;

/** @author Étienne, Sophia et Maria */
public class CompteException extends Exception {

	private static final long serialVersionUID = -458451376748647609L;

	/**
	 * @param msg
	 *            le message d’erreur
	 */
	public CompteException(final String msg) {
		super(msg);
	}
}
