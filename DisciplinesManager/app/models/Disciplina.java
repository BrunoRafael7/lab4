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
	 * Verifica se a disciplina está nos pré requisitos
	 * @param disciplina a ser verificada
	 * @return true se conter pre requisito, se não, return false
	 */
	public boolean contemPreRequisito(Disciplina disc){
		return preRequisitos.contains(disc);
	}
	
	/**
	 * aloca um disciplina ou desaloca uma disciplina conforme valor passado por parâmetro,
	 * sendo true para alocada e false para desalocada
	 */
	public void setAlocada(boolean alocada) {
		this.alocada = alocada;
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
	public boolean equals(Object obj){
		if(!(obj instanceof Disciplina)){
			return false;
		}
		Disciplina disc = (Disciplina)obj;
		return this.getNome().equals(disc.getNome());
	}
}