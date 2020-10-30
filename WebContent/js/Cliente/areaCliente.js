$(document).ready(function () {

	loadPrenotazioni();

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
	}, 100000); //richiesta ogni 1 minuti

	$('#nuovaPrenotazione').click(function () {
		if ($('#date').val() != null && listaPostazioni.length > 0) {
			$('#modalNuovaPrenotazione').modal('hide');
			$('#modalPagamento').modal('show');
		} else {
			$('#divAlertNuovaPrenotazione').html('<div class="alert alert-info center"><strong>Errore</strong> Inserire una data valida e selezionare una o più postazioni.</div>');
		}
	});

	$('#formPagamento').submit(function (e) {
		e.preventDefault();
		paga();
	});

	$('#closePagamento').click(function () {
		$('#formPagamento').trigger('reset');
		$('#modalPagamento').modal('hide');
		$('#modalNuovaPrenotazione').modal('show');
	});

	$('#closeNuovaPrenotazione').click(function () {
		let date = new Date(),
			dformat = [date.getFullYear(), ('0' + (date.getMonth() + 1)).slice(-2), ('0' + date.getDate()).slice(-2)].join('-');
		$("#date").val(dformat);
		$('input[type="date"]').prop('min', dformat);
		$('#divAlertNuovaPrenotazione').empty();
		listaPostazioniNonPrenotabili = [];
		listaPostazioni = [];
	});

});

var prenotazioni = {};
var listaPostazioniNonPrenotabili = []
var listaPostazioni = [];

function paga() {
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

function prenota() {
	if ($('#date').val() != null && listaPostazioni.length > 0) {
		$.ajax({
			url: './gestionePrenotazione',
			dataType: 'json',
			type: 'post',
			data: {
				'dataPrenotazione': $('#date').val(),
				'postazioni': listaPostazioni.sort().toString(),
			},
			success: function (data) {
				if (data.PRENOTATO == 'true') {
					updateMappa();
					loadPrenotazioni();
				}
				$('#divAlertNuovaPrenotazione').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>');
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#divAlertNuovaPrenotazione').html('<div class="alert alert-info center"><strong>Errore</strong> Inserire una data valida e selezionare una o più postazioni.</div>');
	}
}

function selectPostazione(id) {
	if (listaPostazioniNonPrenotabili.includes(id)) {} 
	else if (listaPostazioni.includes(id)) {
		listaPostazioni.splice(listaPostazioni.indexOf(id), 1);
		$('#postazione' + id).removeAttr('style');
	} else {
		listaPostazioni.push(id);
		$('#postazione' + id).css('background-color', 'lightgreen');
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
				buildMappa();
				setPostazioni(data);
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		buildMappa();
	}
}

function setPostazioni(data) {
	listaPostazioniNonPrenotabili = [];
	listaPostazioni = [];
	$.each(data[0], function (key, val) {
		listaPostazioniNonPrenotabili.push(val.idPostazione);
		let str1 = '<div class="postazione" id="postazione' + val.idPostazione + '" style="background-color: lightgrey"><button onclick="selectPostazione(' + val.idPostazione + ')" class="btn"><img src="img/postazione.png" style="width: 40px; height: 40px;"></button></div>';
		$('#postazione' + val.idPostazione).html(str1);
	});
	$.each(data[1], function (key, val) {
		listaPostazioniNonPrenotabili.push(val.idPostazione);
		let str1 = '<div class="postazione" id="postazione' + val.idPostazione + '" style="background-color: lightcoral"><button onclick="selectPostazione(' + val.idPostazione + ')" class="btn"><img src="img/postazione.png" style="width: 40px; height: 40px;"></button></div>';
		$('#postazione' + val.idPostazione).html(str1);
	});
}

function buildMappa() {
	let mare = '<div class="text-center" style="background-color: lightskyblue; grid-row: 1; grid-column: 1 / span 10;"><small class="w3-opacity">MARE</small></div>';
	let battigia = '<div class="text-center" style=" background-color: #F2D16B; grid-row: 2; grid-column: 1 / span 10;"><small class="w3-opacity">BATTIGIA</small></div>';
	let postazioni = '';
	for (let i = 1; i < 51; i++) {
		postazioni += '<div class="postazione" id="postazione' + i + '"><button onclick="selectPostazione(' + i + ')" class="btn"><img src="img/postazione.png" style="width: 40px; height: 40px;"></button></div>';
	}
	$('#mappaLido').html(mare + battigia + postazioni);
}


function loadPrenotazioni() {
	$.ajax({
		url: './areaUtente',
		dataType: 'json',
		type: 'post',
		success: function (data) {
			let str = '';
			$.each(data, function (key, val) {
				prenotazioni[val.idPrenotazione] = val;
				let today = new Date();
				let day = 86400000;
				let d = new Date(val.data),
					dformat = [('0' + d.getDate()).slice(-2), ('0' + (d.getMonth() + 1)).slice(-2), d.getFullYear()].join('-') + ' ' + [('0' + d.getHours()).slice(-2), ('0' + d.getMinutes()).slice(-2), ('0' + d.getSeconds()).slice(-2)].join(':');
				if (val.rimborsato == 1) {
					str += '<tr><td>' + dformat + '</td><td><i>annullata</i></td>';
					str += '<td class="text-center"><button onclick="getPrenotazione(' + val.idPrenotazione + ')" data-toggle="modal" data-target="#modalVisualizzaPrenotazione" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-eye"></i></button></td>';
					str += '<td></td></tr>';
				} else if (val.dataPrenotazione < (today.getTime() + day)) {
					str += '<tr><td>' + dformat + '</td><td><i>completata</i></td>';
					str += '<td class="text-center"><button onclick="getPrenotazione(' + val.idPrenotazione + ')" data-toggle="modal" data-target="#modalVisualizzaPrenotazione" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-eye"></i></button></td>';
					str += '<td></td></tr>';
				} else {
					str += '<tr><td>' + dformat + '</td><td><i>in corso</i></td>';
					str += '<td class="text-center"><button onclick="getPrenotazione(' + val.idPrenotazione + ')" data-toggle="modal" data-target="#modalVisualizzaPrenotazione" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-eye"></i></button></td>';
					str += '<td class="text-center"><button onclick="deletePrenotazione(' + val.idPrenotazione + ')" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-trash"></i></button></td></tr>';
				}
			});
			$('#tablePrenotazioni').html(str);
		},
		error: function (errorThrown) {
			console.log(errorThrown);
		}
	});
}

function getPrenotazione(id) {
	if (id != null) {
		let d = prenotazioni[id];
		let date = new Date(d.dataPrenotazione),
			dformat = [('0' + date.getDate()).slice(-2), ('0' + (date.getMonth() + 1)).slice(-2), date.getFullYear()].join('-');
		let str = '<h3 class="text-center"><strong>Informazioni Prenotazione #' + d.idPrenotazione + '</strong></h3><br><p>Data:&nbsp;&nbsp;&nbsp;' + dformat + '<br>';
		$.each(d.postazioni, function (data, val) {
			str += 'Postazione n&ordm;:&nbsp;&nbsp;&nbsp;' + val.idPostazione + '&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;costo:&nbsp;&nbsp;&nbsp;' + val.prezzo + '&nbsp;&euro;<br>';
		});
		if (d.rimborsato == 1) {
			str += '<p><strong>Prenotazione annullata.<br>Importo rimborsato:&nbsp;&nbsp;&nbsp;' + d.totale + '&nbsp;&euro;</strong></p>';
		} else {
			str += '<p><strong>Totale:&nbsp;&nbsp;&nbsp;' + d.totale + '&nbsp;&euro;</strong></p>';
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
				loadPrenotazioni();
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