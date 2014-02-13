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
		return ok(MESSAGE_OK + ", alocado");
	}
	
	public static Result desalocarDisciplina(String nome, Integer periodo){
		try {
			planoDeCurso.desalocaDisciplina(nome, periodo);
		} catch (PreRequisitosException e) {
			return created(e.getMessage());
		} catch (LimiteDeCreditosException e) {
			return created(e.getMessage());
		}
		//mudar
		return ok(MESSAGE_OK + ", desalocado");
	}
	
	//método para verificação  
	public static Result preRequisitosEstaoAlocados(String disciplina){
		return ok();
	}
	
//	public static Result alocaDisciplinaParaOPeriodo(String disciplina, int periodo){
//		for(Disciplina disc : planoDeCurso.getDisciplinasNaoAlocadas()){
//			if(disc.getNome().equals(disciplina)){
//				disc.setAlocada(true);
//				planoDeCurso.adicionaDisciplinaAPeriodo(disc, periodo);;
//				break;
//			}
//		}
//		return redirect(routes.Application.index());
//	}
	
}
