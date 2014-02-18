package models;

/**
 * Enum com as dificuldades das disciplinas
 * @author
 *
 */
public enum Dificuldades {
	MUITO_FACIL(1,"Muito Fácil"), FACIL(2,"Fácil"), MEDIANA(3,"Mediana"), DIFICIL(4,"Difícil"), MUITO_DIFICIL(5,"Muito difícil");
	
	private int num_dificuldade;
	private String dificuldade;
	
	Dificuldades(int num_dificuldade, String dificuldade){
		this.setNum_dificuldade(num_dificuldade);
		this.setDificuldade(dificuldade);
	}
	
	/**
	 * 
	 * @return valor numérico da dificuldade
	 */
	public int getNum_dificuldade() {
		return num_dificuldade;
	}
	
	/**
	 * Altera o valor numérico da dificuldade
	 * @param num_dificuldade
	 */
	public void setNum_dificuldade(int num_dificuldade) {
		this.num_dificuldade = num_dificuldade;
	}
	
	/**
	 * 
	 * @return a dificuldade(String)
	 */
	public String getDificuldade() {
		return dificuldade;
	}
	
	/**
	 * Altera a dificuldade(String)
	 * @param dificuldade
	 */
	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}
}
