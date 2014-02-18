package models;

import java.util.List;

/**
 * Classe Disciplina
 * @author
 *
 */
public class Disciplina implements Comparable<Disciplina>{
	
	private String nome;
	private List<String> preRequisitos;
	private int creditos;
	private int periodo;
	private int dificuldade;
	
	/**
	 * Construtor
	 * @param preRequisitos da disciplina.
	 * @param nome da disciplina.
	 * @param creditos da disciplina.
	 * @param periodo em que a disciplina pertence.
	 */
	public Disciplina(List<String> preRequisitos, String nome, int creditos, int periodo, int dificuldade){
		this.nome = nome;
		this.preRequisitos = preRequisitos;
		this.creditos = creditos;
		this.periodo = periodo;
		this.dificuldade = dificuldade;
	}
	
	/**
	 * 
	 * @return nome da disciplina.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @return uma lista com os pré requisitos da disciplina.
	 */
	public List<String> getPreRequisitos() {
		return preRequisitos;
	}
	
	/**
	 * 
	 * @return quantidade de créditos da disciplina.
	 */
	public int getCreditos() {
		return creditos;
	}

	/**
	 * 
	 * @return periodo que a disciplina pertence.
	 */
	public int getPeriodo() {
		return periodo;
	}

	public boolean contemPreRequisito(Disciplina disc){
		return preRequisitos.contains(disc);
	}
	
	/**
	 * return uma String dos dados da disciplina
	 */
	@Override
	public String toString() {
		return "Disciplina [nome=" + nome + ", preRequisitos=" + preRequisitos
				+ ", creditos=" + creditos + ", periodo=" + periodo + "]";
	}
	
	/**
	 * Duas disciplinas são iguais se forem da mesma instancia e se tiverem o mesmo nome
	 * return true se forem iguais, se não, return false
	 */
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Disciplina)){
			return false;
		}
		Disciplina disc = (Disciplina)obj;
		return this.getNome().equals(disc.getNome());
	}

	@Override
	public int compareTo(Disciplina disc) {
		if(this.getPeriodo() >= disc.getPeriodo()){
			return 1;
		}
		return -1;
	}

	public int getDificuldade() {
		return dificuldade;
	}

}