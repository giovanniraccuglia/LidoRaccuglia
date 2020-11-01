$(document).ready(function () {

	loadPrenotazioniOrdini();

	let date = new Date(),
		dformat = [date.getFullYear(), ('0' + (date.getMonth() + 1)).slice(-2), ('0' + date.getDate()).slice(-2)].join('-');
	$("#date").val(dformat);
	$('input[type="date"]').prop('min', dformat);

	$('#linkNuovaPrenotazione').click(function () {
		updateMappa();
	});

	$('#date').change(function () {
		updateMappa();
	});

	setInterval(function () {
		updateMappa();
	}, 300000); //richiesta ogni 3 minuti

	$('#nuovaPrenotazione').click(function () {
		if ($('#date').val() != null && postazioniSelezionate.length > 0) {
			$('#modalNuovaPrenotazione').modal('hide');
			$('#modalPagamento').modal('show');
		} else {
			$('#divAlertNuovaPrenotazione').html('<div class="alert alert-info center"><strong>Errore!</strong> Inserire una data valida e selezionare una o pi&ugrave; postazioni.</div>');
		}
	});

	$('#nuovoOrdine').click(function () {
		if (!(isEmpty(prodottiSelezionati))) {
			$('#modalNuovoOrdine').modal('hide');
			$('#modalPagamento').modal('show');
		} else {
			$('#divAlertNuovoOrdine').html('<div class="alert alert-info center"><strong>Errore!</strong> Selezionare uno o pi&ugrave; prodotti.</div>');
		}
	});

	$('#formPagamento').submit(function (e) {
		e.preventDefault();
		if (postazioniSelezionate.length > 0) {
			pagaPrenotazione();
		} else if (!(isEmpty(prodottiSelezionati))) {
			pagaOrdine();
		} else {}
	});

	$('#closePagamento').click(function () {
		if (postazioniSelezionate.length > 0) {
			$('#formPagamento').trigger('reset');
			$('#modalPagamento').modal('hide');
			$('#modalNuovaPrenotazione').modal('show');
		} else if (!(isEmpty(prodottiSelezionati))) {
			$('#formPagamento').trigger('reset');
			$('#modalPagamento').modal('hide');
			$('#modalNuovoOrdine').modal('show');
		} else {}
	});

	$('#closeNuovaPrenotazione').click(function () {
		let date = new Date(),
			dformat = [date.getFullYear(), ('0' + (date.getMonth() + 1)).slice(-2), ('0' + date.getDate()).slice(-2)].join('-');
		$("#date").val(dformat);
		$('input[type="date"]').prop('min', dformat);
		$('#divAlertNuovaPrenotazione').empty();
		postazioniNonPrenotabili = [];
		postazioniSelezionate = [];
		totalePrenotazioni = 0;
		$('#totaleNuovoOrdine').html('<h3><strong>Totale:&nbsp;&nbsp;' + totalePrenotazioni.toFixed(2) + '&nbsp;&euro;</strong></h3>');
	});

	$('#linkNuovoOrdine').click(function () {
		loadMenu();
	});

	$('#linkColazione').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divColazione').show();
	});

	$('#linkPanini').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divPanini').show();
	});

	$('#linkRustici').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divRustici').show();
	});

	$('#linkInsalatone').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divInsalatone').show();
	});

	$('#linkFruttaDessert').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divFruttaDessert').show();
	});

	$('#linkAntipastiMare').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divAntipastiMare').show();
	});

	$('#linkAntipastiTerra').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divAntipastiTerra').show();
	});

	$('#linkPrimiMare').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divPrimiMare').show();
	});

	$('#linkPrimiTerra').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divPrimiTerra').show();
	});

	$('#linkSecondiMare').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divSecondiMare').show();
	});

	$('#linkSecondiTerra').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divSecondiTerra').show();
	});

	$('#linkDessert').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divDessert').show();
	});

	$('#linkCaffetteria').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divCaffetteria').show();
	});

	$('#linkBirre').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divBirre').show();
	});

	$('#linkCocktails').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divCocktails').show();
	});

	$('#linkViniBianchi').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divViniBianchi').show();
	});

	$('#linkViniRossi').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divViniRossi').show();
	});

	$('#linkDistillati').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divDistillati').show();
	});

	$('#linkAnalcolici').click(function () {
		$('#divNuovoOrdine').children('div').hide();
		$('#divAnalcolici').show();
	});

	$('#closeNuovoOrdine').click(function () {
		prodottiSelezionati = {};
		totaleOrdini = 0;
		$('#divAlertNuovoOrdine').empty();
		$('#totaleNuovoOrdine').html('<h3><strong>Totale:&nbsp;&nbsp;' + totaleOrdini.toFixed(2) + '&nbsp;&euro;</strong></h3>');
	});

});

function getListaCategoria(lista, c) {
	return lista.filter(function (x) {
		return x.categoria == c;
	});
}

function isEmpty(obj) {
	return Object.keys(obj).length === 0;
}

function incrProdotto(id) {
	if (isEmpty(prodottiSelezionati)) {
		prodottiSelezionati[id] = 1;
	} else {
		if (id in prodottiSelezionati) {
			prodottiSelezionati[id] += 1;
		} else {
			prodottiSelezionati[id] = 1;
		}
	}
	totaleOrdini += parseFloat($('#incrProdotto' + id).attr('data-id'));
	$('#totaleNuovoOrdine').html('<h3><strong>Totale:&nbsp;&nbsp;' + totaleOrdini.toFixed(2) + '&nbsp;&euro;</strong></h3>');
}

function decrProdotto(id) {
	if (id in prodottiSelezionati) {
		let val = prodottiSelezionati[id];
		if (val > 1) {
			prodottiSelezionati[id] = prodottiSelezionati[id] - 1;
		} else {
			delete prodottiSelezionati[id];
		}
		totaleOrdini -= parseFloat($('#decrProdotto' + id).attr('data-id'));
		$('#totaleNuovoOrdine').html('<h3><strong>Totale:&nbsp;&nbsp;' + totaleOrdini.toFixed(2) + '&nbsp;&euro;</strong></h3>');
	}
}

function loadMenu() {
	$.ajax({
		url: './menu',
		dataType: 'json',
		type: 'post',
		success: function (data) {
			prodottiSelezionati = {};
			totaleOrdini = 0;
			$('#totaleNuovoOrdine').html('<h3><strong>Totale:&nbsp;&nbsp;' + totaleOrdini.toFixed(2) + '&nbsp;&euro;</strong></h3>');
			let j = 0;
			let str = '';
			let lista1 = ['Colazione', 'Panini', 'Rustici', 'Insalatone', 'Frutta e Dessert', 'Antipasti Di Mare', 'Antipasti Di Terra', 'Primi Di Mare', 'Primi Di Terra', 'Secondi Di Mare', 'Secondi Di Terra', 'Dessert', 'Caffetteria', 'Birre', 'Cocktails', 'Vini Bianchi', 'Vini Rossi', 'Distillati', 'Analcolici'];
			let lista2 = ['divColazione', 'divPanini', 'divRustici', 'divInsalatone', 'divFruttaDessert', 'divAntipastiMare', 'divAntipastiTerra', 'divPrimiMare', 'divPrimiTerra', 'divSecondiMare', 'divSecondiTerra', 'divDessert', 'divCaffetteria', 'divBirre', 'divCocktails', 'divViniBianchi', 'divViniRossi', 'divDistillati', 'divAnalcolici'];
			for (i = 0; i < lista1.length; ++i) {
				if (getListaCategoria(data, lista1[i]).length > 0) {
					str += '<div id="' + lista2[j] + '"><h3 class="w3-opacity">' + lista1[i] + '</h3><table class="table table-hover"><thead></thead><tbody>';
					$.each(getListaCategoria(data, lista1[i]), function (key, val) {
						str += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo.toFixed(2) + '&nbsp;&euro;</td>';
						str += '<td class="text-center"><button id="incrProdotto' + val.idProdotto + '" data-id="' + val.prezzo + '" onclick="incrProdotto(' + val.idProdotto + ')" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-plus"></i></button></td>';
						str += '<td class="text-center"><button id="decrProdotto' + val.idProdotto + '" data-id="' + val.prezzo + '" onclick="decrProdotto(' + val.idProdotto + ')" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-minus"></i></button></td></tr>';
					});
					str += '</tbody></table></div>';
				}
				j = j + 1;
			}
			$('#divNuovoOrdine').html(str);
		},
		error: function (errorThrown) {
			console.log(errorThrown);
		}
	});
}

var prenotazioni = {};
var ordini = {};
var postazioniNonPrenotabili = [];
var postazioniSelezionate = [];
var prodottiSelezionati = {};
var totaleOrdini = 0;
var totalePrenotazioni = 0;

function pagaOrdine() {
	$.ajax({
		url: './pagamento',
		dataType: 'json',
		type: 'post',
		success: function (data) {
			if (data.PAGATO == 'true') {
				$('#formPagamento').trigger('reset');
				$('#modalPagamento').modal('hide');
				$('#modalNuovoOrdine').modal('show');
				ordina();
			} else {
				$('#divAlertPagamento').html('<div class="alert alert-info center"><strong>Errore</strong> Pagamento non riuscito.</div>');
			}
		},
		error: function (errorThrown) {
			console.log(errorThrown);
		}
	});
}

function pagaPrenotazione() {
	$.ajax({
		url: './pagamento',
		dataType: 'json',
		type: 'post',
		success: function (data) {
			if (data.PAGATO == 'true') {
				$('#formPagamento').trigger('reset');
				$('#modalPagamento').modal('hide');
				$('#modalNuovaPrenotazione').modal('show');
				prenota();
			} else {
				$('#divAlertPagamento').html('<div class="alert alert-info center"><strong>Errore</strong> Pagamento non riuscito.</div>');
			}
		},
		error: function (errorThrown) {
			console.log(errorThrown);
		}
	});
}

function ordina() {
	if (!(isEmpty(prodottiSelezionati))) {
		$.ajax({
			url: './gestioneOrdine',
			dataType: 'json',
			type: 'post',
			data: {
				'prodotti': Object.keys(prodottiSelezionati).toString(),
				'quantita': Object.values(prodottiSelezionati).toString()
			},
			success: function (data) {
				if (data.ORDINATO == 'true') {
					loadPrenotazioniOrdini();
					prodottiSelezionati = {};
					totaleOrdini = 0;
					$('#totaleNuovoOrdine').html('<h3><strong>Totale:&nbsp;&nbsp;' + totaleOrdini.toFixed(2) + '&nbsp;&euro;</strong></h3>');
				}
				$('#divAlertNuovoOrdine').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>');
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#divAlertNuovoOrdine').html('<div class="alert alert-info center"><strong>Errore!</strong> Selezionare uno o pi&ugrave; prodotti.</div>');
	}
}

function prenota() {
	if ($('#date').val() != null && postazioniSelezionate.length > 0) {
		$.ajax({
			url: './gestionePrenotazione',
			dataType: 'json',
			type: 'post',
			data: {
				'dataPrenotazione': $('#date').val(),
				'postazioni': postazioniSelezionate.sort().toString()
			},
			success: function (data) {
				if (data.PRENOTATO == 'true') {
					updateMappa();
					loadPrenotazioniOrdini();
				}
				$('#divAlertNuovaPrenotazione').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>');
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#divAlertNuovaPrenotazione').html('<div class="alert alert-info center"><strong>Errore!</strong> Inserire una data valida e selezionare una o pi&ugrave; postazioni.</div>');
	}
}

function selectPostazione(id) {
	if (postazioniNonPrenotabili.includes(id)) {} 
	else if (postazioniSelezionate.includes(id)) {
		postazioniSelezionate.splice(postazioniSelezionate.indexOf(id), 1);
		$('#postazione' + id).removeAttr('style');
		totalePrenotazioni -= parseFloat($('#postazione' + id).attr('data-id'));
		$('#totaleNuovaPrenotazione').html('<h3><strong>Totale:&nbsp;&nbsp;' + totalePrenotazioni.toFixed(2) + '&nbsp;&euro;</strong></h3>');
	} else {
		postazioniSelezionate.push(id);
		$('#postazione' + id).css('background-color', 'lightgreen');
		totalePrenotazioni += parseFloat($('#postazione' + id).attr('data-id'));
		$('#totaleNuovaPrenotazione').html('<h3><strong>Totale:&nbsp;&nbsp;' + totalePrenotazioni.toFixed(2) + '&nbsp;&euro;</strong></h3>');
	}
}

function updateMappa() {
	if ($('#date').val() != null) {
		$.ajax({
			url: './spiaggia',
			dataType: 'json',
			type: 'post',
			data: {
				'dataPrenotazione': $('#date').val()
			},
			success: function (data) {
				buildMappa(data[0]);
				setPostazioni(data[1]);
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		buildMappa(data[0]);
	}
}

function setPostazioni(data) {
	postazioniNonPrenotabili = [];
	postazioniSelezionate = [];
	totalePrenotazioni = 0;
	$('#totaleNuovaPrenotazione').html('<h3><strong>Totale:&nbsp;&nbsp;' + totalePrenotazioni.toFixed(2) + '&nbsp;&euro;</strong></h3>');
	$.each(data, function (key, val) {
		postazioniNonPrenotabili.push(val.idPostazione);
		let str = '<div class="postazione" id="postazione' + val.idPostazione + '" data-id="' + val.prezzo + '" style="background-color: lightcoral"><button onclick="selectPostazione(' + val.idPostazione + ')" class="btn"><img src="img/postazione.png" style="width: 40px; height: 40px;"></button></div>';
		$('#postazione' + val.idPostazione).html(str);
	});
}

function buildMappa(data) {
	let mare = '<div class="text-center" style="background-color: lightskyblue; grid-row: 1; grid-column: 1 / span 10;"><small class="w3-opacity">MARE</small></div>';
	let battigia = '<div class="text-center" style=" background-color: #F2D16B; grid-row: 2; grid-column: 1 / span 10;"><small class="w3-opacity">BATTIGIA</small></div>';
	let postazioni = '';
	$.each(data, function (key, val) {
		postazioni += '<div class="postazione" id="postazione' + val.idPostazione + '" data-id="' + val.prezzo + '"><button onclick="selectPostazione(' + val.idPostazione + ')" class="btn"><img src="img/postazione.png" style="width: 40px; height: 40px;"></button></div>';
	});
	$('#mappaLido').html(mare + battigia + postazioni);
}

function loadPrenotazioniOrdini() {
	$.ajax({
		url: './areaUtente',
		dataType: 'json',
		type: 'post',
		success: function (data) {
			let str1 = '';
			let str2 = '';
			$.each(data[0], function (key, val) {
				prenotazioni[val.idPrenotazione] = val;
				let today = new Date();
				let day = 86400000;
				let d = new Date(val.data),
					dformat = [('0' + d.getDate()).slice(-2), ('0' + (d.getMonth() + 1)).slice(-2), d.getFullYear()].join('-') + ' ' + [('0' + d.getHours()).slice(-2), ('0' + d.getMinutes()).slice(-2), ('0' + d.getSeconds()).slice(-2)].join(':');
				if (val.rimborsato == 1) {
					str1 += '<tr><td>' + dformat + '</td><td><i>annullata</i></td>';
					str1 += '<td class="text-center"><button onclick="getPrenotazione(' + val.idPrenotazione + ')" data-toggle="modal" data-target="#modalVisualizzaPrenotazione" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-eye"></i></button></td>';
					str1 += '<td></td></tr>';
				} else if (val.dataPrenotazione < (today.getTime() + day)) {
					str1 += '<tr><td>' + dformat + '</td><td><i>confermata</i></td>';
					str1 += '<td class="text-center"><button onclick="getPrenotazione(' + val.idPrenotazione + ')" data-toggle="modal" data-target="#modalVisualizzaPrenotazione" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-eye"></i></button></td>';
					str1 += '<td></td></tr>';
				} else {
					str1 += '<tr><td>' + dformat + '</td><td><i>in corso</i></td>';
					str1 += '<td class="text-center"><button onclick="getPrenotazione(' + val.idPrenotazione + ')" data-toggle="modal" data-target="#modalVisualizzaPrenotazione" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-eye"></i></button></td>';
					str1 += '<td class="text-center"><button onclick="deletePrenotazione(' + val.idPrenotazione + ')" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-trash"></i></button></td></tr>';
				}
			});
			$.each(data[1], function (key, val) {
				ordini[val.idOrdine] = val;
				let d = new Date(val.data),
					dformat = [('0' + d.getDate()).slice(-2), ('0' + (d.getMonth() + 1)).slice(-2), d.getFullYear()].join('-') + ' ' + [('0' + d.getHours()).slice(-2), ('0' + d.getMinutes()).slice(-2), ('0' + d.getSeconds()).slice(-2)].join(':');
				if (val.preparato == 1 && val.ritirato == 0) {
					str2 += '<tr><td>' + dformat + '</td><td><i>da ritirare</i></td>';
					str2 += '<td class="text-center"><button onclick="getOrdine(' + val.idOrdine + ')" data-toggle="modal" data-target="#modalVisualizzaOrdine" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-eye"></i></button></td>';
				} else if (val.preparato == 1 && val.ritirato == 1) {
					str2 += '<tr><td>' + dformat + '</td><td><i>ritirato</i></td>';
					str2 += '<td class="text-center"><button onclick="getOrdine(' + val.idOrdine + ')" data-toggle="modal" data-target="#modalVisualizzaOrdine" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-eye"></i></button></td>';
				} else {
					str2 += '<tr><td>' + dformat + '</td><td><i>in preparazione</i></td>';
					str2 += '<td class="text-center"><button onclick="getOrdine(' + val.idOrdine + ')" data-toggle="modal" data-target="#modalVisualizzaOrdine" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-eye"></i></button></td>';
				}
			});
			$('#tablePrenotazioni').html(str1);
			$('#tableOrdini').html(str2);
		},
		error: function (errorThrown) {
			console.log(errorThrown);
		}
	});
}

function getOrdine(id) {
	if (id != null) {
		let i = 0;
		let d = ordini[id];
		let str = '<h3 class="text-center"><strong>Info Ordine #' + d.idOrdine + '</strong></h3><br>';
		let str1 = '<div class="row"><div class="col-6"><p><strong>Prodotto</strong></p><p>';
		let str2 = '<div class="col-6"><p><strong>qnt.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;prezzo</strong></p><p>';
		$.each(d.prodotti, function (key, val) {
			str1 += val.nome + '<br>';
			str2 += 'x' + d.quantita[i] + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + val.prezzo.toFixed(2) + '&nbsp;&euro;<br>';
			i = i + 1;
		});
		str1 += '</p></div>';
		str2 += '</p></div></div>';
		str += str1 + str2 + '<div><p><strong>Totale:&nbsp;&nbsp;&nbsp;' + d.totale.toFixed(2) + '&nbsp;&euro;</strong></p></div>';
		$('#modalBodyVisualizzaOrdine').html(str);
	} else {
		$('#divAlertOrdine').html('<div class="alert alert-info center"><strong>Errore</strong> Impossibile visualizzare i dettagli dell\'ordine.</div>').show();
		$('#divAlertOrdine').delay(3000).fadeOut();
	}
}

function getPrenotazione(id) {
	if (id != null) {
		let d = prenotazioni[id];
		let date = new Date(d.dataPrenotazione),
			dformat = [('0' + date.getDate()).slice(-2), ('0' + (date.getMonth() + 1)).slice(-2), date.getFullYear()].join('-');
		let str = '<h3 class="text-center"><strong>Info Prenotazione #' + d.idPrenotazione + '</strong></h3><br><p>Data:&nbsp;&nbsp;&nbsp;' + dformat + '<br>';
		$.each(d.postazioni, function (data, val) {
			str += 'Postazione n&ordm;:&nbsp;&nbsp;&nbsp;' + val.idPostazione + '&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;costo:&nbsp;&nbsp;&nbsp;' + val.prezzo.toFixed(2) + '&nbsp;&euro;<br>';
		});
		if (d.rimborsato == 1) {
			str += '</p><p><strong>Prenotazione annullata.<br>Importo rimborsato:&nbsp;&nbsp;&nbsp;' + d.totale.toFixed(2) + '&nbsp;&euro;</strong></p>';
		} else {
			str += '</p><p><strong>Totale:&nbsp;&nbsp;&nbsp;' + d.totale.toFixed(2) + '&nbsp;&euro;</strong></p>';
		}
		$('#modalBodyVisualizzaPrenotazione').html(str);
	} else {
		$('#divAlertPrenotazione').html('<div class="alert alert-info center"><strong>Errore</strong> Impossibile visualizzare i dettagli della prenotazione.</div>').show();
		$('#divAlertPrenotazione').delay(3000).fadeOut();
	}
}

function deletePrenotazione(id) {
	if (id != null) {
		$.ajax({
			url: './gestionePrenotazione?id=' + id,
			dataType: 'json',
			type: 'delete',
			success: function (data) {
				$('#divAlertPrenotazione').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>').show();
				loadPrenotazioniOrdini();
				$('#divAlertPrenotazione').delay(3000).fadeOut();
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#divAlertPrenotazione').html('<div class="alert alert-info center"><strong>Errore</strong> Impossibile annullare la prenotazione.</div>').show();
		$('#divAlertPrenotazione').delay(3000).fadeOut();
	}
}