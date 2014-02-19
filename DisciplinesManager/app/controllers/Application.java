package controllers;

import models.LimiteDeCreditosException;
import models.HTMLResult;
import models.PlanoDeCurso;
import models.PreRequisitosException;
import play.mvc.Controller;
import play.mvc.Result;

/*
 * CONTROLLER : Classe Application é a controladora do sistema
 */
public class Application extends Controller{
	
	private static PlanoDeCurso planoDeCurso = new PlanoDeCurso();
	
	public static Result index(){
		return ok(views.html.index.render(planoDeCurso));
	}
	
	public static Result maximoDeCreditosPorPeriodo(){
		return ok(String.valueOf(planoDeCurso.MAXIMO_DE_CREDITOS_POR_PERIODO));
	}
	
	public static Result alocarDisciplina(String nome, Integer periodo){
		try {
			planoDeCurso.alocaDisciplina(nome, periodo);
		} catch (PreRequisitosException e) {
			return created(e.getMessage());
		} catch (LimiteDeCreditosException e) {
			return created(e.getMessage());
		}
		//mudar
		return ok(HTMLResult.DISCIPLINA_PODE_SER_DESALOCADA.getMessage());
	}
	
	public static Result desalocarDisciplina(String nome, Integer periodo){
		try {
			planoDeCurso.desalocaDisciplina(nome, periodo);
		} catch (LimiteDeCreditosException e) {
			return created(e.getMessage());
		} catch (PreRequisitosException e) {
			e.printStackTrace();
		}
		return ok(HTMLResult.DISCIPLINA_PODE_SER_ALOCADA.getMessage());
	}
	
	public static Result verificaSeDisciplinaPodeSerAlocada(String nomeDaDisciplina, Integer periodo){
		try {
			planoDeCurso.disciplinaPodeSerAlocada(nomeDaDisciplina, periodo);
			
		} catch (LimiteDeCreditosException e) {
			return created(e.getMessage());
		} catch (PreRequisitosException e) {
			return created(e.getMessage());
		}
		System.out.println(HTMLResult.DISCIPLINA_PODE_SER_ALOCADA.getMessage());
		
		return ok(HTMLResult.DISCIPLINA_PODE_SER_ALOCADA.getMessage());
	}
	
	public static Result verificaSeDisciplinaPodeSerDesalocada(String nomeDaDisciplina, Integer periodo){
		try {
			planoDeCurso.disciplinaPodeSerDesalocada(nomeDaDisciplina, periodo);
			
		} catch (PreRequisitosException e) {
			return created(e.getMessage());
		} catch (LimiteDeCreditosException e) {
			return created(e.getMessage());
		}
				
		return ok(HTMLResult.DISCIPLINA_PODE_SER_DESALOCADA.getMessage());
	}
	
	public static Result refresh(){
		planoDeCurso = new PlanoDeCurso();
		return ok("ok");
	}
	
	public static Result getMensagensDeStatus(){
		return ok(HTMLResult.TODAS_AS_MENSAGENS_DE_STATUS.getMessage());
	}
}