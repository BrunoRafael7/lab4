package models;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe PlanoDeCurso
 * 
 * @author
 * 
 */
public class PlanoDeCurso {
	public final int MINIMO_DE_CREDITOS_POR_PERIODO = 14;
	public final int MAXIMO_DE_CREDITOS_POR_PERIODO = 28;
	public final int PRIMEIRO_PERIODO = 1;
	public final int QUANTIDADE_DE_PERIODOS = 8;
	
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
		alocaDisciplinaParaOPrimeiroPeriodo();
		for (int i = 1; i < QUANTIDADE_DE_PERIODOS; i++) {
			periodos.add(new Periodo());
		}
	}

	/**
	 * Método que aloca as disciplinas do primeiro periodo
	 */
	private void alocaDisciplinaParaOPrimeiroPeriodo() {
		List<Disciplina> disciplinas = gradeCurricular
				.getDisciplinasDoPeriodo(PRIMEIRO_PERIODO);
		for (Disciplina disc : disciplinas) {
			disc.setAlocada(true);
		}
		periodos.add(new Periodo(disciplinas));
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
	 * 
	 * @param periodo
	 * @return lista de disciplinas do período
	 */
	public List<Disciplina> getDisciplinasDoPeriodo(int periodo) {
		int indiceDoPeriodo = periodo - 1;
		return periodos.get(indiceDoPeriodo).getDisciplinas();
	}

	/**
	 * Método que calcula total de créditos do período
	 * 
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
	 * Método que procura todas as disciplinas que ainda não foram alocadas em
	 * algum período
	 * 
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

	/**
	 * Método que aloca disciplina em um determinado periodo
	 * 
	 * @param disciplina
	 *            a ser alocada
	 * @param periodo
	 *            para alocar disciplina
	 * @throws MaximoDeCreditosExcedidoException 
	 */
	public void alocaDisciplina(String disciplina, Integer periodo) throws PreRequisitosException, LimiteDeCreditosException {
		if(periodo == PRIMEIRO_PERIODO){
			throw new PreRequisitosException("Não deve alocar disciplinas do primeiro período.");
		}
		
		Periodo p = periodos.get(periodo - 1);
		Disciplina d = gradeCurricular.get(disciplina);
		System.out.println(periodo + " p");
		System.out.println("total de creditos " + p.getTotalDeCreditos());
		if(p.getTotalDeCreditos() <= MAXIMO_DE_CREDITOS_POR_PERIODO){
			if(d.getPeriodo() != PRIMEIRO_PERIODO){
				d.setAlocada(true);
				p.add(d);
			}else{
				throw new PreRequisitosException("Não deve alocar disciplinas do primeiro período.");
			}
		}else{
			throw new LimiteDeCreditosException("Limite de créditos atingido.");
		}
	}

	/**
	 * Método que desaloca disciplina em um determinado periodo
	 * @param nome da disciplina a ser desalocada
	 * @param periodo que a discplina a ser desalocada esta
	 * @return true se desalocou, se não, return false
	 * @throws PreRequisitosException 
	 * @throws LimiteDeCreditosException 
	 */
	public void desalocaDisciplina(String nome, Integer periodo) throws PreRequisitosException, LimiteDeCreditosException {
		if(periodo == PRIMEIRO_PERIODO){
			throw new PreRequisitosException("Não deve desalocar disciplinas do primeiro período.");
		}
		Periodo p = periodos.get(periodo - 1);
		if(p.getTotalDeCreditos() >= MINIMO_DE_CREDITOS_POR_PERIODO){
			Disciplina d = gradeCurricular.get(nome);
			d.setAlocada(false);
			removeDisciplinasDependentes(d);
			p.remove(d);
		}else{
			throw new LimiteDeCreditosException("Mínimo de créditos não atingido");
		}
	}

	private String removeDisciplinasDependentes(Disciplina disciplina) {
		String nomesDasDisciplinasRemovidas = "";
		for(Periodo periodo : periodos){
			for(Disciplina disc : periodo.getDisciplinas()){
				if(disc.contemPreRequisito(disciplina)){
					nomesDasDisciplinasRemovidas += disc.getNome() + "/";
					periodo.remove(disc);
				}
			}
		}
		return nomesDasDisciplinasRemovidas;
	}
}