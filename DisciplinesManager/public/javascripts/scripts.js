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

//REFATORAR
function connectToLists(){
	$('#colunasDeDisciplinas .sortable-list').sortable({
			connectWith: '#colunasDeDisciplinas .sortable-list',
			stop :  function (ev, ui) {
				calculaTotalDeCreditosDoPeriodoAtual();
				var nomeDaDisciplina = new String(ui.item.children("nome").html());
				if(periodoAtual != 1){
					if(ui.item.hasClass("descricaoDeDisciplinaAlocada")){
		              	if ( (totalDeCreditosAtual > maximoDeCreditosPorPeriodo )) {
		              		_cancelSortable();
		                }else{
		                	_updateTotalDeCreditos();
		                	alocaDisciplina(nomeDaDisciplina, periodoAtual);
		                }
	                }else{
	                		_updateTotalDeCreditos();
	                		desalocaDisciplina(nomeDaDisciplina, periodoAtual);
	                }
                }else{
                	_cancelSortable();
                }
            },
			receive : function(ev, ui){
				if ( periodoAtual != 1) {
					if(ui.item.hasClass("descricaoDeDisciplinaNaoAlocada")){
						ui.item.attr('class','descricaoDeDisciplinaAlocada');
					}else{
						ui.item.attr('class','descricaoDeDisciplinaNaoAlocada');
					}
				}
			}
				
		}).disableSelection();
}

function _cancelSortable(){
//$( '#listaDeDisciplinasNaoAlocadas' ).sortable( 'cancel' );
  $( '#colunasDeDisciplinas .sortable-list' ).sortable( 'cancel' );
}

function _updateTotalDeCreditos(){
	$("#periodoAtual totalDeCreditos").html(totalDeCreditosAtual);
}

function desalocaDisciplina(nomeDaDisciplina, periodoDaDisciplina){
	var url = "/DisciplinesManager/"+ nomeDaDisciplina + "/" + periodoDaDisciplina + "/desalocaDisciplina";
       
	$.post(url,{"nome":nomeDaDisciplina, "periodo":periodoDaDisciplina}, 
		function(result){
		  alert(result);	
		}
	);
}

function alocaDisciplina(nomeDaDisciplina, periodoDaDisciplina){
	var url = "/DisciplinesManager/"+ nomeDaDisciplina + "/" + periodoDaDisciplina + "/alocaDisciplina";
       
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
