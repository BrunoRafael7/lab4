package models;

import java.util.ArrayList;
import java.util.Collections;
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
	public final int QUANTIDADE_DE_PERIODOS = 10;
	public final String MSG_ERRO_1 = "Não deve alocar disciplinas do primeiro período.";
	public final String MSG_ERRO_2 = "Limite de créditos atingido.";
	public final String MSG_ERRO_3 = "Pré-requisitos não cumpridos.";
	public final String MSG_ERRO_4 = "O período anterior está com o total de créditos abaixo do permitido";
	
	private List<Periodo> periodos;
	private GradeCurricular gradeCurricular;
	private List<String> disciplinasAlocadas;
	private List<Integer> periodosAlocados;

	/**
	 * Construtor
	 */
	public PlanoDeCurso() {
		periodos = new LinkedList<Periodo>();
		gradeCurricular = new GradeCurricular();
		disciplinasAlocadas = new ArrayList<String>();
		periodosAlocados = new ArrayList<Integer>();
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
		List<Disciplina> disciplinas = gradeCurricular.getDisciplinasDoPeriodo(PRIMEIRO_PERIODO);
		for (Disciplina disc : disciplinas) {
			disciplinasAlocadas.add(disc.getNome());
			periodosAlocados.add(PRIMEIRO_PERIODO);
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
			if (!disciplinasAlocadas.contains(disc.getNome())) {
				disciplinasNaoAlocadas.add(disc);
			}
		}
		Collections.sort(disciplinasNaoAlocadas);
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
	public void alocaDisciplina(String nomeDaDisciplina, Integer periodo) throws PreRequisitosException, LimiteDeCreditosException {
			Periodo p = periodos.get(periodo - 1);
			Disciplina disc = gradeCurricular.get(nomeDaDisciplina);
			p.add(disc);
			disciplinasAlocadas.add(disc.getNome());
	}
	

	/**
	 * Método que desaloca disciplina em um determinado periodo
	 * @param nome da disciplina a ser desalocada
	 * @param periodo que a discplina a ser desalocada esta
	 * @return true se desalocou, se não, return false
	 * @throws PreRequisitosException 
	 * @throws LimiteDeCreditosException 
	 */
	public void desalocaDisciplina(String nomeDaDisciplina, Integer periodo) throws LimiteDeCreditosException {
			
		Periodo p = periodos.get(periodo - 1);
		Disciplina d = gradeCurricular.get(nomeDaDisciplina);
		p.remove(d);
		disciplinasAlocadas.remove(d);
		
	}

	private List<Disciplina> getDisciplinasDependentes(Disciplina disciplina) {
		List<Disciplina> disciplinasDependentes = new LinkedList<Disciplina>();
		for(Periodo periodo : periodos){
			for(Disciplina disc : periodo.getDisciplinas()){
				if(disc.contemPreRequisito(disciplina)){
					disciplinasDependentes.add(disc);
				}
			}
		}
		return disciplinasDependentes;
	}

	public void refresh() {
		for(int i = 1 ; i < periodos.size() ; i++){
			List<String> disciplinas = periodos.get(i).getNomesDasDisciplinas();
			disciplinasAlocadas.removeAll(disciplinas);
			periodos.set(i, new Periodo());
		}
	}
	
	public void verificaSeDisciplinaPodeSerAlocada(String nomeDaDisciplina, int periodo) throws LimiteDeCreditosException, PreRequisitosException{
		if(periodo == PRIMEIRO_PERIODO){
			throw new PreRequisitosException(MSG_ERRO_1);
		}
		
		Periodo periodoAtual = periodos.get(periodo - 1);
		Periodo periodoAnterior = periodos.get(periodo - 2);
		Disciplina disciplina = gradeCurricular.get(nomeDaDisciplina);
		
		if( (periodoAtual.getTotalDeCreditos() + disciplina.getCreditos() ) > MAXIMO_DE_CREDITOS_POR_PERIODO){
			throw new LimiteDeCreditosException(MSG_ERRO_2);
		}
		
		if(!preRequisitosEstaoSatisfeitos(disciplina, periodo)){
			throw new PreRequisitosException(MSG_ERRO_3);
		}
		
		if((periodoAnterior.getTotalDeCreditos()) < MINIMO_DE_CREDITOS_POR_PERIODO){
			throw new LimiteDeCreditosException(MSG_ERRO_4);
		}
		
	}
	
	public void verificaSeDisciplinaPodeSerDesalocada(String nomeDaDisciplina, int periodo) throws PreRequisitosException, LimiteDeCreditosException{
		if(periodo == PRIMEIRO_PERIODO){
			throw new PreRequisitosException(MSG_ERRO_1.replace("alocar", "desalocar"));
		}
		
//		Periodo p = periodos.get(periodo-1);
		Disciplina disciplina = gradeCurricular.get(nomeDaDisciplina);
		List<Disciplina> disciplinasDependentes = getDisciplinasDependentes(disciplina);
		
		String message = "Existem disciplinas dependentes alocadas! As sequintes Disciplinas são dependentes : ";
		for(Disciplina dsp : disciplinasDependentes){
			message += dsp.getNome() + " "; 
		}
		
		if(!disciplinasDependentes.isEmpty()){
			throw new PreRequisitosException(message);
		}
		
//		if((p.getTotalDeCreditos() - disc.getCreditos()) < MINIMO_DE_CREDITOS_POR_PERIODO){
//			throw new LimiteDeCreditosException("Mínimo de créditos não será atingido");
//		}
	}
	
	
	private boolean preRequisitosEstaoSatisfeitos(Disciplina disciplina, int periodo) {
		List<String> preRequisitos = disciplina.getPreRequisitos();
		return disciplinasAlocadas.containsAll(preRequisitos) && osPreRequisitosEstaoAlocadasNoPeriodo(preRequisitos, periodo);
	}

	private boolean osPreRequisitosEstaoAlocadasNoPeriodo(List<String> disciplinas , int periodo){
		Periodo p = periodos.get(periodo - 1);
		for(String nome : disciplinas){
			Disciplina disc = gradeCurricular.get(nome);
			if(p.contains(disc)){
				return false;
			}
		}
		return true;
	}
}