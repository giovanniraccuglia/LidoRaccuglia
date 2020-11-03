$(document).ready(function () {

	loadOrdini();

	setInterval(function () {
		loadOrdini();
	}, 300000); //richiesta ogni 3 minuti

});

var ordini = {};

function loadOrdini() {
	$.ajax({
		url: './areaUtente',
		dataType: 'json',
		type: 'post',
		success: function (data) {
			let str = '';
			$.each(data, function (key, val) {
				ordini[val.idOrdine] = val;
				let d = new Date(val.data),
					dformat = [('0' + d.getDate()).slice(-2), ('0' + (d.getMonth() + 1)).slice(-2), d.getFullYear()].join('-') + ' ' + [('0' + d.getHours()).slice(-2), ('0' + d.getMinutes()).slice(-2), ('0' + d.getSeconds()).slice(-2)].join(':');
				if (val.preparato == 0 && val.ritirato == 0) {
					str += '<tr><td>' + dformat + '</td><td><i>in preparazione</i></td>';
					str += '<td class="text-center"><button onclick="getOrdine(' + val.idOrdine + ')" data-toggle="modal" data-target="#modalVisualizzaOrdine" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-eye"></i></button></td>';
					str += '<td class="text-center"><button onclick="setPronto(' + val.idOrdine + ')" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-check"></i></button></td><td></td></tr>';
				} else if (val.preparato == 1 && val.ritirato == 0) {
					str += '<tr><td>' + dformat + '</td><td><i>da ritirare</i></td>';
					str += '<td class="text-center"><button onclick="getOrdine(' + val.idOrdine + ')" data-toggle="modal" data-target="#modalVisualizzaOrdine" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-eye"></i></button></td><td></td>';
					str += '<td class="text-center"><button onclick="setRitirato(' + val.idOrdine + ')" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-check"></i></button></td></tr>';
				} else {}
			});
			$('#tableOrdini').html(str);
		},
		error: function (errorThrown) {
			console.log(errorThrown);
		}
	});
}

function getOrdine(id) {
	if (id != null) {
		let i = 0;
		let o = ordini[id];
		let str = '<div><h3 class="text-center"><strong>Info Ordine #' + o.idOrdine + '</strong></h3></div><br><table class="table"><thead><tr><th>Prodotto</th><th>qnt.</th><th>prezzo</th></tr></thead><tbody>';
		$.each(o.prodotti, function (key, val) {
			str += '<tr><td>' + val.nome + '</td><td>x' + o.quantita[i] + '</td><td>' + val.prezzo.toFixed(2) + '&nbsp;&euro;</td></tr>';
			i += 1;
		});
		str += '</tbody></table><div><p><strong>Totale:&nbsp;&nbsp;&nbsp;' + o.totale.toFixed(2) + '&nbsp;&euro;</strong></p></div>';
		$('#modalBodyVisualizzaOrdine').html(str);
	} else {
		$('#divAlertOrdini').html('<div class="alert alert-info center"><strong>Errore!</strong> Impossibile visualizzare i dettagli dell\'ordine.</div>').show();
		$('#divAlertOrdini').delay(3000).fadeOut();
	}
}

function setPronto(id) {
	if (id != null) {
		$.ajax({
			url: './gestioneOrdini',
			dataType: 'json',
			type: 'post',
			data: {
				'id': id,
				'pronto': 'true',
				'ritirato': 'false'
			},
			success: function (data) {
				if (data.CHECK == 'true') {
					loadOrdini();
				} else {
					$('#divAlertOrdini').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>').show();
					$('#divAlertOrdini').delay(3000).fadeOut();
				}
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#divAlertOrdini').html('<div class="alert alert-info center"><strong>Errore!</strong> Impossibile effettuare l\'operazione.</div>').show();
		$('#divAlertOrdini').delay(3000).fadeOut();
	}
}

function setRitirato(id) {
	if (id != null) {
		$.ajax({
			url: './gestioneOrdini',
			dataType: 'json',
			type: 'post',
			data: {
				'id': id,
				'pronto': 'true',
				'ritirato': 'true'
			},
			success: function (data) {
				if (data.CHECK == 'true') {
					loadOrdini();
				} else {
					$('#divAlertOrdini').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>').show();
					$('#divAlertOrdini').delay(3000).fadeOut();
				}
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#divAlertOrdini').html('<div class="alert alert-info center"><strong>Errore!</strong> Impossibile effettuare l\'operazione.</div>').show();
		$('#divAlertOrdini').delay(3000).fadeOut();
	}
}