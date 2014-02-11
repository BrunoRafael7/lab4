package models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GradeCurricular {
	private Map<String, Disciplina> disciplinas;
	
	/*
	 * CREATOR : ColetorDeDisciplinas será instanciada em GradeCurricular ,
	 * pois GradeCurricular é responsável pelas disciplinas que são recebidas pelo ColetorDeDisciplinas
	 */
	private ColetorDeDisciplinas coletorDeDisciplinas;
	
	public GradeCurricular(){
		disciplinas = new HashMap<String, Disciplina>();
		coletorDeDisciplinas = new ColetorDeDisciplinas();
		addDisciplinas(coletorDeDisciplinas.coletar());
	}
	
	private void addDisciplinas(List<Disciplina> listaDeDisciplinas) {
		for(Disciplina dsp : listaDeDisciplinas){
			disciplinas.put(dsp.getNome(), dsp);
		}
	}
	public Disciplina get(String nome){
		return disciplinas.get(nome);
	}

	public List<Disciplina> getDisciplinasDoPeriodo(int periodo){
		List<Disciplina> todasAsDisciplinas = new LinkedList<Disciplina>();
		for(Disciplina dsp : disciplinas.values()){
			if(dsp.getPeriodo() == periodo){
				todasAsDisciplinas.add(dsp);
			}
		}
		return todasAsDisciplinas;
	}
	
	public List<Disciplina> todasAsDisciplinas(){
		List<Disciplina> allDisciplines = new LinkedList<Disciplina>();
		for(Disciplina dsp : disciplinas.values()){
			allDisciplines.add(dsp);
		}
		return allDisciplines; 
	}
}