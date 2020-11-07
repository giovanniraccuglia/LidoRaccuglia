$(document).ready(function () {

	$('#formInfoAccount').submit(function (e) {
		e.preventDefault();
		updateInfoAccount();
	});

	$('#formModificaPassword').submit(function (e) {
		e.preventDefault();
		updatePassword();
	});

	$('#closeModificaPassword').click(function () {
		$('#divAlertModificaPassword').empty();
		$('#formModificaPassword').trigger('reset');
	});

});

function checkInput(val) {
	if (val != null && val.trim() != "") {
		return true;
	} else {
		return false;
	}
}

function updateInfoAccount() {
	let nome = $('#nomeInfoAccount').val();
	let cognome = $('#cognomeInfoAccount').val();
	let cellulare = $('#cellulareInfoAccount').val();
	let email = $('#emailInfoAccount').val();
	if (checkInput(nome) && checkInput(cognome) && checkInput(cellulare) && checkInput(email)) {
		$.ajax({
			url: './infoAccount',
			dataType: 'json',
			type: 'post',
			data: {
				'nome': nome,
				'cognome': cognome,
				'cellulare': cellulare,
				'email': email
			},
			success: function (data) {
				if (data.ACCOUNT == 'false') {
					$('#divInfoAccount').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>').show();
					$('#formInfoAccount').trigger('reset');
					$('#divInfoAccount').delay(3000).fadeOut();
				} else if (data.ACCOUNT == 'equal') {
					$('#formInfoAccount').trigger('reset');
				} else {
					$('#divInfoAccount').html('<div class="alert alert-info center"><strong>Successo!</strong> Account correttamente aggiornato.</div>').show();
					$('#nomeInfoAccount').attr('value', data.nome);
					$('#cognomeInfoAccount').attr('value', data.cognome);
					$('#cellulareInfoAccount').attr('value', data.cellulare);
					$('#emailInfoAccount').attr('value', data.email);
					$('#divInfoAccount').trigger('reset');
					$('#divInfoAccount').delay(3000).fadeOut();
				}
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#divInfoAccount').html('<div class="alert alert-info center"><strong>Errore!</strong> Inserire i dati nei relativi campi.</div>').show();
		$('#formInfoAccount').trigger('reset');
		$('#divInfoAccount').delay(3000).fadeOut();
	}
}

function updatePassword() {
	let vecchiaPassword = $('#vecchiaPassword').val();
	let nuovaPassword = $('#nuovaPassword').val();
	let confermaPassword = $('#confermaPassword').val();
	if (checkInput(vecchiaPassword) && checkInput(nuovaPassword) && checkInput(confermaPassword)) {
		$.ajax({
			url: './modificaPassword',
			dataType: 'json',
			type: 'post',
			data: {
				'vecchiaPassword': vecchiaPassword,
				'nuovaPassword': nuovaPassword,
				'confermaPassword': confermaPassword
			},
			success: function (data) {
				$('#formModificaPassword').trigger('reset');
				$('#divAlertModificaPassword').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>');
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#formModificaPassword').trigger('reset');
		$('#divAlertModificaPassword').html('<div class="alert alert-info center"><strong>Errore!</strong> Inserire i dati nei relativi campi.</div>');
	}
}