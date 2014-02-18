package models;

/**
 * Classe de Exceção para Créditos insuficientes.
 * @author
 *
 */
public class CreditosInsuficientesException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CreditosInsuficientesException(String message){
		super(message);
	}

}
