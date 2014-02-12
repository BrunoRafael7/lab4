package models;

import java.util.List;

/**
 * Classe Disciplina
 * @author
 *
 */
public class Disciplina{
	
	private String nome;
	private List<String> preRequisitos;
	private int creditos;
	private int periodo;
	private boolean alocada;
	
	/**
	 * Construtor
	 * @param preRequisitos da disciplina.
	 * @param nome da disciplina.
	 * @param creditos da disciplina.
	 * @param periodo em que a disciplina pertence.
	 */
	public Disciplina(List<String> preRequisitos, String nome, int creditos, int periodo){
		this.nome = nome;
		this.preRequisitos = preRequisitos;
		this.creditos = creditos;
		this.periodo = periodo;
		this.alocada = false;
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
	
	/**
	 * 
	 * @return true se a disciplina já estiver alocada em algum período, se não, return false
	 */
	public boolean isAlocada() {
		return alocada;
	}

	/**
	 * aloca um disciplina
	 */
	public void alocaDisciplina() {
			this.alocada = true;
	}
	
	/**
	 * desaloca a disciplina
	 */
	public void desalocaDisciplina(){
		this.alocada = false;
	}
	
	/**
	 * return uma String dos dados da disciplina
	 */
	@Override
	public String toString() {
		return "Disciplina [nome=" + nome + ", preRequisitos=" + preRequisitos
				+ ", creditos=" + creditos + ", periodo=" + periodo + "]";
	}
}