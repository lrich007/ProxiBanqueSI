package com.marlou.service;

/** @author Étienne, Sophia et Maria */
public class ConseillerServiceException extends Exception {

	private static final long serialVersionUID = 3525265711824562831L;

	/**
	 * @param msg
	 *            le message d’erreur
	 */
	public ConseillerServiceException(final String msg) {
		super(msg);
	}

}
