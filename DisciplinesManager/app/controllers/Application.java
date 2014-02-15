package controllers;

import models.LimiteDeCreditosException;
import models.PlanoDeCurso;
import models.PreRequisitosException;
import play.mvc.Controller;
import play.mvc.Result;

/*
 * CONTROLLER : Classe Application é a controladora do sistema
 */
public class Application extends Controller{
	
	private static PlanoDeCurso planoDeCurso = new PlanoDeCurso();
	private static String MESSAGE_OK = "ok";
	
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
		return ok(MESSAGE_OK);
	}
	
	public static Result desalocarDisciplina(String nome, Integer periodo){
		try {
			planoDeCurso.desalocaDisciplina(nome, periodo);
		} catch (PreRequisitosException e) {
			return created(e.getMessage());
		} catch (LimiteDeCreditosException e) {
			return created(e.getMessage());
		}
		return ok(MESSAGE_OK);
	}
	
	public static Result verificaSeDisciplinaPodeSerAlocada(String nomeDaDisciplina, Integer periodo){
		try {
			planoDeCurso.verificaSeDisciplinaPodeSerAlocada(nomeDaDisciplina, periodo);
			return ok(MESSAGE_OK);
			
		} catch (LimiteDeCreditosException e) {
			return created(e.getMessage());
		} catch (PreRequisitosException e) {
			return created(e.getMessage());
		}
	}
	
	public static Result verificaSeDisciplinaPodeSerDesalocada(String nomeDaDisciplina, Integer periodo){
		try {
			planoDeCurso.verificaSeDisciplinaPodeSerDesalocada(nomeDaDisciplina, periodo);
		} catch (PreRequisitosException e) {
			return created(e.getMessage());
		} catch (LimiteDeCreditosException e) {
			return created(e.getMessage());
		}
		return ok(MESSAGE_OK);
	}
	
	public static Result refresh(){
		planoDeCurso.refresh();
		return ok();
	}
	
	public static Result getMessageOk(){
		return ok(MESSAGE_OK);
	}
}