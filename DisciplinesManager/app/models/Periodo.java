package models;

import java.util.LinkedList;
import java.util.List;

public class Periodo {

	private List<Disciplina> disciplinas;
	/*
	 * INFORMATION EXPERT : A classe Periodo deve ter a responsabilidade 
	 * de somar o total de créditos das disciplinas por ter as disciplinas no qual serão
	 * calculados os totais de créditos
	 */
	private int totalDeCreditos;
	
	public Periodo(){
		disciplinas = new LinkedList<Disciplina>();
	}
	
	public Periodo(List<Disciplina> disciplinas){
		this.disciplinas = disciplinas;
		for(Disciplina dsp : disciplinas){
			totalDeCreditos += dsp.getCreditos();
		}
	}
	
	public void adicionaUmaDisciplina(Disciplina disciplina){
		disciplinas.add(disciplina);
	}
	
	public void removeDisciplina(Disciplina disciplina){
		disciplinas.remove(disciplina);
	}
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public int getTotalDeCreditos() {
		return totalDeCreditos;
	}
}