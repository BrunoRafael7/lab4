var divs = new Array();
var totalDeCreditosAtual;
var indiceDoPeriodoAtual = 1;
var maximoDeCreditosPorPeriodo;

$(function(){
	criarDivs();
	connectToLists();
	calculaTotalDeCreditosDoPeriodoAtual();
	totalDeCreditosAtual = parseInt($("#totalDeCreditos").html());
	$.get("/DisciplinesManager/maximoDeCreditos", function(result){
		maximoDeCreditosPorPeriodo = parseInt(result);
	});
});

function connectToLists(){
	$('#colunasDeDisciplinas .sortable-list').sortable({
			connectWith: '#colunasDeDisciplinas .sortable-list',
			containment: '#colunasDeDisciplinas',
			stop :  function (ev, ui) {
				calculaTotalDeCreditosDoPeriodoAtual();
              	if ( (totalDeCreditosAtual > maximoDeCreditosPorPeriodo ) || indiceDoPeriodoAtual == 1) {
              		$( '#colunasDeDisciplinas .sortable-list' ).sortable( 'cancel' );
                }else{
                	$("#periodoAtual totalDeCreditos").html(totalDeCreditosAtual);
                }
            }	
		}).disableSelection();
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
				    	          "<creditos> Créditos :<totalDeCreditos> 0 </totalDeCreditos> </creditos> "
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
	
}
