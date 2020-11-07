$(document).ready(function () {

	loadDipendentiProdotti();

	$('#formNuovoDipendente').submit(function (e) {
		e.preventDefault();
		addDipendente();
	});

	$('#closeNuovoDipendente').click(function () {
		$('#divAlertNuovoDipendente').empty();
		$('#formNuovoDipendente').trigger('reset');
	});

	$('#formNuovoProdotto').submit(function (e) {
		e.preventDefault();
		addProdotto();
	});

	$('#closeNuovoProdotto').click(function () {
		$('#divAlertNuovoProdotto').empty();
		$('#formNuovoProdotto').trigger('reset');
	});

});

function checkInput(val) {
	if (val != null && val.trim() != "") {
		return true;
	} else {
		return false;
	}
}

function addDipendente() {
	let nome = $('#nomeNuovoDipendente').val();
	let cognome = $('#cognomeNuovoDipendente').val();
	let cellulare = $('#cellulareNuovoDipendente').val();
	let email = $('#emailNuovoDipendente').val();
	let ruolo = $('#ruoloNuovoDipendente').val();
	if (checkInput(nome) && checkInput(cognome) && checkInput(cellulare) && checkInput(email) && checkInput(ruolo)) {
		$.ajax({
			url: './gestioneDipendente',
			dataType: 'json',
			type: 'post',
			data: {
				'nome': nome,
				'cognome': cognome,
				'cellulare': cellulare,
				'email': email,
				'ruolo': ruolo
			},
			success: function (data) {
				if (data.INSERIMENTO == 'true') {
					loadDipendentiProdotti();
				}
				$('#formNuovoDipendente').trigger('reset');
				$('#divAlertNuovoDipendente').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>');
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#formNuovoDipendente').trigger('reset');
		$('#divAlertNuovoDipendente').html('<div class="alert alert-info center"><strong>Errore!</strong> Inserire i dati nei relativi campi.</div>');
	}
}

function addProdotto() {
	let nome = $('#nomeNuovoProdotto').val();
	let descrizione = $('#descrizioneNuovoProdotto').val();
	let prezzo = $('#prezzoNuovoProdotto').val();
	let categoria = $('#categoriaNuovoProdotto').val();
	if (checkInput(nome) && checkInput(descrizione) && checkInput(prezzo) && checkInput(categoria)) {
		$.ajax({
			url: './gestioneProdotto',
			dataType: 'json',
			type: 'post',
			data: {
				'nome': nome,
				'descrizione': descrizione,
				'prezzo': prezzo,
				'categoria': categoria
			},
			success: function (data) {
				if (data.INSERIMENTO == 'true') {
					loadDipendentiProdotti();
				}
				$('#formNuovoProdotto').trigger('reset');
				$('#divAlertNuovoProdotto').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>');
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#formNuovoProdotto').trigger('reset');
		$('#divAlertNuovoProdotto').html('<div class="alert alert-info center"><strong>Errore!</strong> Inserire i dati nei relativi campi.</div>');
	}
}

function loadDipendentiProdotti() {
	$.ajax({
		url: './areaUtente',
		dataType: 'json',
		type: 'post',
		success: function (data) {
			let str1 = '';
			let str2 = '';
			$.each(data[0], function (key, val) {
				str1 += '<tr><td>' + val.nome + '</td><td>' + val.cognome + '</td><td>' + val.cellulare + '</td><td>' + val.email + '</td><td>' + val.ruolo + '</td><td class="text-center"><button onclick="deleteDipendente(' + val.idUtente + ')" data-id="' + val.idUtente + '" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-trash"></i></button></td></tr>';
			});
			$.each(data[1], function (key, val) {
				str2 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo.toFixed(2) + '&nbsp;&euro;</td><td>' + val.categoria + '</td><td class="text-center"><button onclick="deleteProdotto(' + val.idProdotto + ')" data-id="' + val.idProdotto + '" type="button" class="btn bg-info" style="color: whitesmoke"><i class="fa fa-trash"></i></button></td></tr>';
			});
			$('#tableDipendenti').html(str1);
			$('#tableProdotti').html(str2);
		},
		error: function (errorThrown) {
			console.log(errorThrown);
		}
	});
}

function deleteDipendente(id) {
	if (id != null) {
		$.ajax({
			url: './gestioneDipendente?id=' + id,
			dataType: 'json',
			type: 'delete',
			success: function (data) {
				if(data.CANCELLAZIONE == 'true') {
					$('button[data-id=' + id + ']').parent().parent().remove();
				}
				$('#divAlertDipendente').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>').show();
				$('#divAlertDipendente').delay(3000).fadeOut();
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#divAlertDipendente').html('<div class="alert alert-info center"><strong>Errore!</strong> Impossibile eliminare l\'account dell\'utente selezionato.</div>').show();
		$('#divAlertDipendente').delay(3000).fadeOut();
	}
}

function deleteProdotto(id) {
	if (id != null) {
		$.ajax({
			url: './gestioneProdotto?id=' + id,
			dataType: 'json',
			type: 'delete',
			success: function (data) {
				if(data.CANCELLAZIONE == 'true') {
					$('button[data-id=' + id + ']').parent().parent().remove();
				}
				$('#divAlertProdotto').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>').show();
				$('#divAlertProdotto').delay(3000).fadeOut();
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#divAlertProdotto').html('<div class="alert alert-info center"><strong>Errore!</strong> Impossibile eliminare il prodotto selezionato.</div>').show();
		$('#divAlertProdotto').delay(3000).fadeOut();
	}
}