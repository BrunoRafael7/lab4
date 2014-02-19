var divs = [];
var totalDeCreditosAtual;
var periodoAtual = 1;
var maximoDeCreditosPorPeriodo;

var messageOk;
var confirm;

$(function(){
	criarDivs();
	connectToLists();
	_updateTotalDeCreditos();
	_updateTooltipsAndEffects();
	totalDeCreditosAtual = parseInt($("totalDeCreditos").html());
	
	$.get("/DisciplinesManager/maximoDeCreditos", function(result){
		maximoDeCreditosPorPeriodo = parseInt(result);
	});
	$.get("/DisciplinesManager/getMensagensDeStatus", function(result){
		messageOk = $(result).children("ok").html();
		confirm = $(result).children("confirm").html();
	});
	
	$.get("/DisciplinesManager/refresh", function(result){
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
						var status = $(result).children('status').html();
						if(status == messageOk){
							alocaDisciplina(nomeDaDisciplina, periodoAtual, ui);
						}else{
							$('.sortable-list').sortable( 'cancel' );
						}
					});
				}else{
					$.get("/DisciplinesManager/verificaSeDisciplinaPodeSerDesalocada",{"nome":nomeDaDisciplina, "periodo":periodoAtual},
						function(result){
							var status = $(result).children('status').html();
							var mensagem1 = $(result).children('message').html();
							var mensagem2 = $(result).children('mensagem2').html();
							var nomesDasDisciplinas = $(result).children('disciplinas').html();
							if(status == messageOk){
								desalocaDisciplina(nomeDaDisciplina, periodoAtual, ui);
							}else if(status == confirm){
								var mensagemCompleta = mensagem1 + mensagem2 + nomesDasDisciplinas;
								var nomesSemEspacos = nomesDasDisciplinas.trim();
								alert(nomesSemEspacos);
								var split = nomesSemEspacos.split(" ");
								alert(split);
								createConfirmMessage("/" + split + "/", mensagemCompleta);
							}else{
								$('.sortable-list').sortable( 'cancel' );
							}
						}
					);
				}
			}
		}).disableSelection();
}

function alocaDisciplina(nomeDaDisciplina, periodoDaDisciplina, ui){
	var url = "/DisciplinesManager/alocaDisciplina/"+ nomeDaDisciplina + "/" + periodoDaDisciplina;
	$.post(url,{"nome":nomeDaDisciplina, "periodo":periodoDaDisciplina}, 
		function(result){
		  var tooltipMessage = $(result).children('message').html();
		  ui.item.attr('class','descricaoDeDisciplinaAlocada');
		  ui.item.attr('title',tooltipMessage);
		  _updateTotalDeCreditos();	
		}
	);
}

function desalocaDisciplina(nomeDaDisciplina, periodoDaDisciplina, ui){
	var url = "/DisciplinesManager/desalocaDisciplina/"+ nomeDaDisciplina + "/" + periodoDaDisciplina;
       
	$.post(url,{"nome":nomeDaDisciplina, "periodo":periodoDaDisciplina}, 
		function(result){
		  var tooltipMessage = $(result).children('message').html();
		  ui.item.attr('class','descricaoDeDisciplinaNaoAlocada');
		  ui.item.attr('title',tooltipMessage);
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
	for(var i = 0 ; i < 10 ; i++){
		conteudoDoPeriodo =	" <br></br><br><br/> " +
							  "<titulo>" +  (i + 1)  + "º Período</titulo>" +
				    	          "<ul id=\"list" + (i + 1) + "\" class=\"sortable-list\"></ul>"+
				    	          "<creditos> Créditos : <totalDeCreditos></totalDeCreditos> </creditos> ";
		divs.push(conteudoDoPeriodo);
	}

}

function alterTable(proximoPeriodo){
	divs[ periodoAtual - 1 ] = $('#periodoAtual').html();
	$('#periodoAtual').html(divs[proximoPeriodo - 1]);
	periodoAtual = proximoPeriodo;
	connectToLists();
	_updateTotalDeCreditos();
	_updateTooltipsAndEffects();
}

function _updateTooltipsAndEffects(){
	$('#colunasDeDisciplinas li').hover(function(){
				var disciplina = $(this);
				
                disciplina.stop().animate({"width" : 530, "height" : 91},100,function(){
                    disciplina.animate({"width" : 532, "height" : 93},100, function(){
                        disciplina.animate({"width" : 530, "height" : 91},100);
                    });
                });
                var nomeDaDisciplina = new String(disciplina.children("nome").html());
                if(disciplina.hasClass("descricaoDeDisciplinaNaoAlocada")){
			   		$.get("/DisciplinesManager/verificaSeDisciplinaPodeSerAlocada",{"nome":nomeDaDisciplina, "periodo":periodoAtual},
				       	function(result){
				       		var message = $(result).children('message').html();
				       		disciplina.attr('title', message);
				       	}
			    	);
			    }else{
			    	$.get("/DisciplinesManager/verificaSeDisciplinaPodeSerDesalocada",{"nome":nomeDaDisciplina, "periodo":periodoAtual},
				       	function(result){
				       		var tooltipMessage = $(result).children("message").html();
				       		disciplina.attr('title', tooltipMessage);
				       	}
			    	);
			    }
				
            }, function(){
                $(this).stop().animate({"width" : 520, "height" : 81},100);
            });
	
}

function createConfirmMessage(nomesDasDisciplinas, mensagemCompleta){
	var c = confirm('dfsdfsdfsdf');
	if(c){
		alert("Disciplinas excluídas");
	}else{
		alert("sdfsdf");
	}
	alert(3);

/*
	 var dialog = "<div id=\"dialog-confirm\" title=\"Remoção de disciplinas\">"+
			"<p><span class=\"ui-icon ui-icon-alert\"></span>" + mensagemCompleta +"</p></div>";
	 alert(nomesDasDisciplinas);
	 $("#divPrincipal").append(dialog);

	 $( "#dialog-confirm" ).dialog({
		resizable: false,
		height:140,
		modal: true,
		buttons: {
				"remover": function() {
					for(var conteudoDoPeriodo in divs){
						for(var nome in nomesDasDisciplnas){
							$(conteudoDoPeriodo).remove("#" + nome);
						}
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
		}
	});
	$( "#dialog-confirm" ).dialog("open");*/
}