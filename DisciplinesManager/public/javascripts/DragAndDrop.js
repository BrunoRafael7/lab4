var divs = new Array();
var CREDITOS =  "Créditos : ";
var totalDeCreditosAtual;
var indiceDoPeriodoAtual = 1;
var maximoDeCreditosPorPeriodo;

$(function(){
	criarDivs();
	connectToLists();
	calculaTotalDeCreditosDoPeriodoAtual();
	verificaLimiteDeCreditos();
	
	totalDeCreditosAtual = parseInt($("#totalDeCreditos").html());
	
	$.get("/DisciplinesManager/maximoDeCreditos", function(result){
		maximoDeCreditosPorPeriodo = parseInt(result);
	});
});

function connectToLists(){
	$('#colunasDeDisciplinas .sortable-list').sortable({
			connectWith: '#colunasDeDisciplinas .sortable-list'	
		}).disableSelection();
	alert(indiceDoPeriodoAtual);
	
	$( "#notAlocatedList li" ).mousedown(function() {
		$(this).filter(
			function() {
				var creditos = parseInt($( "descricao" ).html());
				verificaLimiteDeCreditos(creditos);
			}
		);
	});
	

	$( "#list" + indiceDoPeriodoAtual ).on( "sortupdate", 
		function( event, ui ) {
			calculaTotalDeCreditosDoPeriodoAtual();
			$("#periodoAtual totalDeCreditos").html(CREDITOS + totalDeCreditosAtual);
		} 
	);

}

function verificaLimiteDeCreditos(){
	verificaLimiteDeCreditos(0);
}

function verificaLimiteDeCreditos(creditoDaDisciplina){
	if( (totalDeCreditosAtual + creditoDaDisciplina) > 28 ){
		$("#periodoAtual totalDeCreditos").css('color','#000000');
		$( '#colunasDeDisciplinas .sortable-list' ).sortable( 'cancel' );

	}else{
		$("#periodoAtual totalDeCreditos").css('color','#456A8B');
		$( '#colunasDeDisciplinas .sortable-list' ).sortable( 'enable' );
	}
}
function calculaTotalDeCreditosDoPeriodoAtual(){
	var soma = 0;
	$("ul[id='list" + indiceDoPeriodoAtual + "'] li descricao").each(
		function(){
			soma += parseInt($(this).html());
		}
	);
	totalDeCreditosAtual = soma;
	
}

function criarDivs(){
	for(var i = 0 ; i < 8 ; i++){
		conteudoDoPeriodo =	" <br></br><br><br/> " +
							  "<titulo>" +  (i + 1)  + "º Período</titulo>" +
				    	          "<ul id=\"list" + (i + 1) + "\" class=\"sortable-list\"></ul>"+
				    	          "<creditos> " + CREDITOS + "<totalDeCreditos>0 </totalDeCreditos> </creditos> "
				    	    ;
		divs[i] = conteudoDoPeriodo;
	}

}

function alterTable(proximoPeriodo){
	divs[ indiceDoPeriodoAtual - 1 ] = $('#periodoAtual').html();
	$('#periodoAtual').html(divs[proximoPeriodo - 1]);
	indiceDoPeriodoAtual = proximoPeriodo;
	connectToLists();
	calculaTotalDeCreditosDoPeriodoAtual();
	verificaLimiteDeCreditos();
	
}
