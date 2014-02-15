package models;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe Periodo
 * @author 
 *
 */
public class Periodo {

	private List<Disciplina> disciplinas;
	
	/*
	 * INFORMATION EXPERT : A classe Periodo deve ter a responsabilidade 
	 * de somar o total de créditos das disciplinas por ter as disciplinas no qual serão
	 * calculados os totais de créditos
	 */
	private int totalDeCreditos;
	
	/**
	 * Construtor
	 */
	public Periodo(){
		disciplinas = new LinkedList<Disciplina>();
	}
	
	/**
	 * Método que calcula o total de créditos do período
	 * @param disciplinas (lista)
	 */
	public Periodo(List<Disciplina> disciplinas){
		this.disciplinas = disciplinas;
		for(Disciplina dsp : disciplinas){
			totalDeCreditos += dsp.getCreditos();
		}
	}
	
	/**
	 * Adiciona uma nova disciplina ao período
	 * @param disciplina
	 */
	public void add(Disciplina disciplina){
		totalDeCreditos += disciplina.getCreditos();
		disciplinas.add(disciplina);
	}
	
	/**
	 * Remove uma disciplina do período
	 * @param disciplina
	 */
	public void remove(Disciplina disciplina){
		totalDeCreditos -= disciplina.getCreditos();
		disciplinas.remove(disciplina);
	}
	
	/**
	 * 
	 * @return lista de disciplinas do período
	 */
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	/**
	 * 
	 * @return o total de créditos do período
	 */
	public int getTotalDeCreditos() {
		return totalDeCreditos;
	}
	
	public boolean contains(Disciplina disciplina){
		return disciplinas.contains(disciplina);
	}
}