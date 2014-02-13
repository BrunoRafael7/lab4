package models;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe PlanoDeCurso
 * @author
 *
 */
public class PlanoDeCurso {
//        private final int TOTAL_DE_PERIODOS = 10;
//        private final int MINIMO_DE_CREDITOS = 14;
        public final int MAXIMO_DE_CREDITOS_POR_PERIODO = 28;


	private List<Periodo> periodos;
	private GradeCurricular gradeCurricular;
	private final int PRIMEIRO_PERIODO = 1;
	
	/**
	 * Construtor
	 */
	public PlanoDeCurso() {
		periodos = new LinkedList<Periodo>();
		gradeCurricular = new GradeCurricular();
		/*
		 * CREATOR : Classe PlanoDeCurso registra objetos do tipo Periodo pois
		 * planoDeCurso é composta de Periodos
		 */
		periodos.add(new Periodo(gradeCurricular.getDisciplinasDoPeriodo(PRIMEIRO_PERIODO)));
		
		periodos.add(new Periodo()); //SEGUNDO PERIODO VAZIO
		periodos.add(new Periodo()); //TERCEIRO PERIODO VAZIO
		periodos.add(new Periodo()); //QUARTO PERIODO VAZIO
		periodos.add(new Periodo()); //QUINTO PERIODO VAZIO
		periodos.add(new Periodo()); //SEXTO PERIODO VAZIO
		periodos.add(new Periodo()); //SETIMO PERIODO VAZIO
		periodos.add(new Periodo()); //OITAVO PERIODO VAZIO
	}
	
	/**
	 * 
	 * @return lista de períodos do curso
	 */
	public List<Periodo> getPeriodos() {
		return periodos;
	}

	/**
	 * Método que retorna todas as disciplinas de um determinado período
	 * @param periodo
	 * @return lista de disciplinas do período
	 */
	public List<Disciplina> getDisciplinasDoPeriodo(int periodo) {
		int indiceDoPeriodo = periodo - 1;
		return periodos.get(indiceDoPeriodo).getDisciplinas();
	}

	// tem que ser modificado, pois não é ele quem vai calcular
	/**
	 * Método que calcula total de créditos do período
	 * @param periodo
	 * @return total de créditos de um determinado período
	 */
	public int getTotalDeCreditosDoPeriodo(int periodo) {
		int indiceDoPeriodo = periodo - 1;
		return periodos.get(indiceDoPeriodo).getTotalDeCreditos();
	}

	/*
	 * INFORMATION EXPERT: Para que o sistema tenha Alta coesão e baixo
	 * acoplamento, Classe Plano de curso é quem deve ter a responsablidade de
	 * "selecionar" as disciplinas não alocadas, pois ela que conhece os
	 * períodos e estes são compostos de disciplinas.
	 */
	/**
	 * Método que procura todas as disciplinas que ainda não foram alocadas em algum período
	 * @return lista das disciplinas não alocadas
	 */
	public List<Disciplina> getDisciplinasNaoAlocadas() {
		List<Disciplina> disciplinasNaoAlocadas = new LinkedList<Disciplina>();
		for (Disciplina disc : gradeCurricular.todasAsDisciplinas()) {
			if (!disc.isAlocada()) {
				disciplinasNaoAlocadas.add(disc);
			}
		}
		return disciplinasNaoAlocadas;
	}
}