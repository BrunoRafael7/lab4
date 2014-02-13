package controllers;

import models.PlanoDeCurso;
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
		System.out.println(nome);
		System.out.println(periodo);
		planoDeCurso.alocaDisciplina(nome, periodo);
		return ok("ok");
	}
	
	public static Result desalocarDisciplina(String nome, Integer periodo){
		planoDeCurso.desalocaDisciplina(nome, periodo);
		return ok("OK");
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
