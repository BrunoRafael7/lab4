package models;

public class LimiteDeCreditosException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public LimiteDeCreditosException(String message){
		super(message);
	}
	
	public String toString(){
		return this.getMessage();
	}
}
