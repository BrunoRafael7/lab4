package models;

public enum Dificuldades {
	MUITO_FACIL(1,"Muito Fácil"), FACIL(2,"Fácil"), MEDIANA(3,"Mediana"), DIFICIL(4,"Difícil"), MUITO_DIFICIL(5,"Muito difícil");
	
	private int num_dificuldade;
	private String dificuldade;
	Dificuldades(int num_dificuldade, String dificuldade){
		this.setNum_dificuldade(num_dificuldade);
		this.setDificuldade(dificuldade);
	}
	public int getNum_dificuldade() {
		return num_dificuldade;
	}
	public void setNum_dificuldade(int num_dificuldade) {
		this.num_dificuldade = num_dificuldade;
	}
	public String getDificuldade() {
		return dificuldade;
	}
	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}
}
