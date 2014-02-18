package models;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Recebe as disciplinas coletadas e formam a grade curricular do curso
 * @author
 *
 */
public class GradeCurricular {
	
	private Map<String, Disciplina> disciplinas;
	
	/*
	 * CREATOR : ColetorDeDisciplinas será instanciada em GradeCurricular ,
	 * pois GradeCurricular é responsável pelas disciplinas que são recebidas pelo ColetorDeDisciplinas
	 */
	private ColetorDeDisciplinas coletorDeDisciplinas;
	
	/**
	 * Construtor
	 */
	public GradeCurricular(){
		disciplinas = new HashMap<String, Disciplina>();
		coletorDeDisciplinas = new ColetorDeDisciplinas();
		addDisciplinas(coletorDeDisciplinas.coletar()); //ADD mapa de disciplinas à grade curricular
	}
	
	/**
	 * Mapeia nome da disciplina(String) : Disciplina (Disciplina)
	 * @param listaDeDisciplinas
	 */
	public void addDisciplinas(List<Disciplina> listaDeDisciplinas) {
		for(Disciplina dsp : listaDeDisciplinas){
			disciplinas.put(dsp.getNome(), dsp);
		}
	}
	
	/**
	 * Método de pesquisar disciplinas por nome
	 * @param nome da disciplina a ser buscada
	 * @return
	 */
	public Disciplina get(String nome){
		return disciplinas.get(nome);
	}

	/**
	 * Método que retorna todas as disciplinas de um determinado período
	 * @param periodo
	 * @return lista de disciplinas do período
	 */
	public List<Disciplina> getDisciplinasDoPeriodo(int periodo){
		List<Disciplina> todasAsDisciplinas = new LinkedList<Disciplina>();
		for(Disciplina dsp : disciplinas.values()){
			if(dsp.getPeriodo() == periodo){
				todasAsDisciplinas.add(dsp);
			}
		}
		return todasAsDisciplinas;
	}
	
	/**
	 * Método que retorna todas as disciplinas da grade curricular
	 * @return lista de todas as disciplinas
	 */
	public List<Disciplina> todasAsDisciplinas(){
		List<Disciplina> allDisciplines = new LinkedList<Disciplina>();
		for(Disciplina dsp : disciplinas.values()){
			allDisciplines.add(dsp);
		}
		return allDisciplines; 
	}
	
}