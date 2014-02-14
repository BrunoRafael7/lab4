var divs = new Array();
var totalDeCreditosAtual;
var periodoAtual = 1;
var maximoDeCreditosPorPeriodo;

$(function(){
	criarDivs();
	connectToLists();
	_updateTotalDeCreditos();
	totalDeCreditosAtual = parseInt($("totalDeCreditos").html());
	$.get("/DisciplinesManager/maximoDeCreditos", function(result){
		maximoDeCreditosPorPeriodo = parseInt(result);
	});
	$.get("/DisciplinesManager/refresh", function(result){});
});

//REFATORAR
function connectToLists(){
	var listSortable = $('#colunasDeDisciplinas .sortable-list');
	listSortable.sortable({
			connectWith: '#colunasDeDisciplinas .sortable-list',
			receive :  function (ev, ui) {
					var nomeDaDisciplina = new String(ui.item.children("nome").html());
		            if(ui.item.hasClass("descricaoDeDisciplinaNaoAlocada")){
						ui.item.attr('class','descricaoDeDisciplinaAlocada');
						alocaDisciplina(nomeDaDisciplina, periodoAtual);
					}else{
						desalocaDisciplina(nomeDaDisciplina, periodoAtual);
						ui.item.attr('class','descricaoDeDisciplinaNaoAlocada');
					}
		            _updateTotalDeCreditos();
	                
	        },
			beforeStop : function(ev, ui){
				if(	periodoAtual != 1 ){
					var creditosDaDisciplinaAtual = parseInt(ui.item.children("totalDeCreditosDaDisciplina").html());
					if(creditosDaDisciplinaAtual + totalDeCreditosAtual > maximoDeCreditosPorPeriodo){
						$("#listaDeDisciplinasNaoAlocadas").sortable( 'cancel' );
					}
                }else{
                	listSortable.sortable( 'cancel' );
                }
			}
				
		}).disableSelection();
}


function desalocaDisciplina(nomeDaDisciplina, periodoDaDisciplina){
	var url = "/DisciplinesManager/desalocaDisciplina/"+ nomeDaDisciplina + "/" + periodoDaDisciplina;
       
	$.post(url,{"nome":nomeDaDisciplina, "periodo":periodoDaDisciplina}, 
		function(result){
		  alert(result);	
		}
	);
}

function alocaDisciplina(nomeDaDisciplina, periodoDaDisciplina){
	var url = "/DisciplinesManager/alocaDisciplina/"+ nomeDaDisciplina + "/" + periodoDaDisciplina;
       
	$.post(url,{"nome":nomeDaDisciplina, "periodo":periodoDaDisciplina}, 
		function(result){
		  alert(result);	
		}
	);
}

function _updateTotalDeCreditos(){
	var soma = 0;
	$("ul[id='list" + periodoAtual + "'] li totalDeCreditosDaDisciplina").each(
		function(){
			soma += parseInt($(this).html());
		}
	);
	totalDeCreditosAtual = soma;
	$("totalDeCreditos").html(totalDeCreditosAtual);
}

function criarDivs(){
	for(var i = 0 ; i < 8 ; i++){
		conteudoDoPeriodo =	" <br></br><br><br/> " +
							  "<titulo>" +  (i + 1)  + "º Período</titulo>" +
				    	          "<ul id=\"list" + (i + 1) + "\" class=\"sortable-list\"></ul>"+
				    	          "<creditos> Créditos : <totalDeCreditos></totalDeCreditos> </creditos> ";
		divs[i] = conteudoDoPeriodo;
	}

}

function alterTable(proximoPeriodo){
	divs[ periodoAtual - 1 ] = $('#periodoAtual').html();
	$('#periodoAtual').html(divs[proximoPeriodo - 1]);
	periodoAtual = proximoPeriodo;
	connectToLists();
	_updateTotalDeCreditos();
	var creditosDoPeriodoAtual = parseInt($("#periodoAtual totalDeCreditos").html());
}