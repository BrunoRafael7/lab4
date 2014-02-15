var divs = new Array();
var totalDeCreditosAtual;
var periodoAtual = 1;
var maximoDeCreditosPorPeriodo;
var messageOk;

$(function(){
	criarDivs();
	connectToLists();
	_updateTotalDeCreditos();
	totalDeCreditosAtual = parseInt($("totalDeCreditos").html());
	$.get("/DisciplinesManager/maximoDeCreditos", function(result){
		maximoDeCreditosPorPeriodo = parseInt(result);
	});
	$.get("/DisciplinesManager/refresh", function(result){});
	$.get("/DisciplinesManager/getMessageOk", function(result){
		messageOk = result;
	});
});

//REFATORAR
function connectToLists(){
	var listSortable = $('#colunasDeDisciplinas .sortable-list');
	listSortable.sortable({
			connectWith: '#colunasDeDisciplinas .sortable-list',

			receive : function(ev, ui){
				var nomeDaDisciplina = new String(ui.item.children("nome").html());
				if(ui.item.hasClass("descricaoDeDisciplinaNaoAlocada")){
					$.get("/DisciplinesManager/verificaSeDisciplinaPodeSerAlocada",{"nome":nomeDaDisciplina, "periodo":periodoAtual}, 
					function(result){
						if(result != messageOk){
							$("#listaDeDisciplinasNaoAlocadas").sortable( 'cancel' );
							alert(result);
						}else{
							alocaDisciplina(nomeDaDisciplina, periodoAtual, ui);	
						}
					});
				}else{
					$.get("/DisciplinesManager/verificaSeDisciplinaPodeSerDesalocada",{"nome":nomeDaDisciplina, "periodo":periodoAtual},
					function(result){
						desalocaDisciplina(nomeDaDisciplina, periodoAtual, ui);
					});
				}
			}
		}).disableSelection();
}

function alocaDisciplina(nomeDaDisciplina, periodoDaDisciplina, ui){
	var url = "/DisciplinesManager/alocaDisciplina/"+ nomeDaDisciplina + "/" + periodoDaDisciplina;
	$.post(url,{"nome":nomeDaDisciplina, "periodo":periodoDaDisciplina}, 
		function(result){
		  alert(result + ", alocada");
		  ui.item.attr('class','descricaoDeDisciplinaAlocada');
		  _updateTotalDeCreditos();	
		}
	);
}

function desalocaDisciplina(nomeDaDisciplina, periodoDaDisciplina, ui){
	var url = "/DisciplinesManager/desalocaDisciplina/"+ nomeDaDisciplina + "/" + periodoDaDisciplina;
       
	$.post(url,{"nome":nomeDaDisciplina, "periodo":periodoDaDisciplina}, 
		function(result){
		  alert(result + ", desalocada");
		  ui.item.attr('class','descricaoDeDisciplinaNaoAlocada');
		  _updateTotalDeCreditos();
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