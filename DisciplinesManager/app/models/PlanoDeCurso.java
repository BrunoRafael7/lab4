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
		periodos.add(new Periodo(gradeCurricular.getDisciplinasDoPeriodo(1)));
		for(int i = 1 ; i < 8 ; i++){
			periodos.add(new Periodo());
		}
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
	 * "selecionar" as disciplinas não alocadas, pois ela quem conhece os
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

	public void alocaDisciplina(String disciplina, int periodo) {
		Periodo p = periodos.get(periodo-1);
		Disciplina d = gradeCurricular.get(disciplina);
		d.setAlocada(true);
		p.add(d);
	}
}