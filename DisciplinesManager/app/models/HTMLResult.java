package models;

//Pure Fabrication

public enum HTMLResult {
	
	TODAS_AS_MENSAGENS_DE_STATUS("<div> <ok>ok</ok><confirm>confirm</confirm></div>"),
	DISCIPLINA_PODE_SER_ALOCADA("<div><status>ok</status><message>pode ser alocada</message></div>"), 
	DISCIPLINA_PODE_SER_DESALOCADA("<div><status>ok</status><message>pode ser desalocada</message></div>"),
	NAO_PODE_ALOCAR_DISCIPLINAS_DO_PRIMEIRO_PERIODO("<div><status>error</status><message>Disciplinas do primeiro período não podem ser alocadas.</message></div>"),
	NAO_PODE_DESALOCAR_DISCIPLINAS_DO_PRIMEIRO_PERIODO("<div><status>error</status><message>Disciplinas do primeiro período não podem ser desalocadas.</message></div>"),
	LIMITE_DE_CREDITOS("<div><status>error</status><message>Limite de créditos atingido.</message></div>"),
	PRE_REQUISITOS_NAO_CUMPRIDOS("<div><status>error</status><message>Pré-requisitos não cumpridos.</message></div>"),
	PERIODO_ANTERIOR_COM_CREDITOS_MINIMOS_NAO_CUMPRIDOS("<div><status>ok</status><message>O período anterior está com o total de créditos abaixo do permitido</message></div>"),
	
	DESEJA_DESALOCAR_AS_SEGUINTES_DISCIPLINAS("<div><status>confirm</status>" +
										"<mensagem1>Existem disciplinas dependentes alocadas.</mensagem1>"+
										"<mensagem2>Deseja realmente desalocar as seguintes disciplinas : </mensagem2>"+
										"<disciplinas></disciplinas></div>");
	
	private String message;
	
	HTMLResult(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
}
