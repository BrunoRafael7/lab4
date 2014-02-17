package models;

public enum HTMLResult {
	
	STATUS_OK("ok"), 
	DISCIPLINA_PODE_SER_ALOCADA("<div><status>" + STATUS_OK.getMessage() +"</status><message>pode ser alocada</message></div>"), 
	DISCIPLINA_PODE_SER_DESALOCADA("<div><status>" + STATUS_OK.getMessage() +"</status><message>pode ser desalocada</message></div>"),
	NAO_PODE_ALOCAR_DISCIPLINAS_DO_PRIMEIRO_PERIODO("<div><status>error</status><message>Não deve alocar disciplinas do primeiro período.</message></div>"),
	NAO_PODE_DESALOCAR_DISCIPLINAS_DO_PRIMEIRO_PERIODO("<div><status>error</status><message>Não deve desalocar disciplinas do primeiro período.</message></div>"),
	LIMITE_DE_CREDITOS("<div><status>error</status><message>Limite de créditos atingido.</message></div>"),
	PRE_REQUISITOS_NAO_CUMPRIDOS("<div><status>error</status><message>Pré-requisitos não cumpridos.</message></div>"),
	PERIODO_ANTERIOR_COM_CREDITOS_MINIMOS_NAO_CUMPRIDOS("<div><status>ok</status><message>O período anterior está com o total de créditos abaixo do permitido</message></div>");
	
	private String message;
	
	HTMLResult(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
}
