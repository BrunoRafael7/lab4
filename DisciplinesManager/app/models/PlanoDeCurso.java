package models;

import java.util.LinkedList;
import java.util.List;

public class PlanoDeCurso {

	private List<Periodo> periodos;
	private GradeCurricular gradeCurricular;

	public PlanoDeCurso() {
		periodos = new LinkedList<Periodo>();
		gradeCurricular = new GradeCurricular();
		/*
		 * CREATOR : Classe PlanoDeCurso registra objetos do tipo Periodo pois
		 * planoDeCurso é composta de Periodos
		 */
		periodos.add(new Periodo(gradeCurricular.getDisciplinasDoPeriodo(1)));
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public List<Disciplina> getDisciplinasDoPeriodo(int periodo) {
		int indiceDoPeriodo = periodo - 1;
		return periodos.get(indiceDoPeriodo).getDisciplinas();
	}

	// tem que ser modificado, pois não é ele quem vai calcular
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