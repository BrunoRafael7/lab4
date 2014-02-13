var divs = new Array();
var totalDeCreditosAtual;
var periodoAtual = 1;
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
			stop :  function (ev, ui) {
				calculaTotalDeCreditosDoPeriodoAtual();
              	if ( (totalDeCreditosAtual > maximoDeCreditosPorPeriodo ) || periodoAtual == 1) {
              		$( '#colunasDeDisciplinas .sortable-list' ).sortable( 'cancel' );
                }else{
                	var nomeDaDisciplina = new String(ui.item.children("nome").html());
                	$("#periodoAtual totalDeCreditos").html(totalDeCreditosAtual);
                	alocaDisciplina(nomeDaDisciplina, periodoAtual);
                }
            }	
		}).disableSelection();
}

function alocaDisciplina(nomeDaDisciplina, periodoDaDisciplina){
	var url = "/DisciplinesManager/"+ nomeDaDisciplina + "/" + periodoDaDisciplina;
       
	$.post(url,{"nome":nomeDaDisciplina, "periodo":periodoDaDisciplina}, 
		function(result){
		  alert(result);	
		}
	);
}

function calculaTotalDeCreditosDoPeriodoAtual(){
	var soma = 0;
	$("ul[id='list" + periodoAtual + "'] li descricaoDeDisciplina").each(
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
				    	          "<creditos> Créditos : <totalDeCreditos> 0 </totalDeCreditos> </creditos> "
				    	    ;
		divs[i] = conteudoDoPeriodo;
	}

}

function alterTable(proximoPeriodo){
	divs[ periodoAtual - 1 ] = $('#periodoAtual').html();
	$('#periodoAtual').html(divs[proximoPeriodo - 1]);
	periodoAtual = proximoPeriodo;
	connectToLists();
	calculaTotalDeCreditosDoPeriodoAtual();
	
}
