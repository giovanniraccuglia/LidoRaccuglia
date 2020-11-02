$(document).ready(function () {

	$('#formLogin').submit(function (e) {
		e.preventDefault();
		login();
	});

	$('#formRegistrazione').submit(function (e) {
		e.preventDefault();
		registrazione();
	});

	$('#linkResetPassword').click(function () {
		$('#divAlertLogin').empty();
		$('#formLogin').trigger('reset');
		$('#modalLogin').modal('hide');
	});

	$('#closeLogin').click(function () {
		$('#divAlertLogin').empty();
		$('#formLogin').trigger('reset');
	});

	$('#closeResetPassword').click(function () {
		$('#divAlertResetPassword').empty();
		$('#formResetPassword').trigger('reset');
	});

	$('#closeRegistrazione').click(function () {
		$('#divAlertRegistrazione').empty();
		$('#formRegistrazione').trigger('reset');
	});

	$('#formResetPassword').submit(function (e) {
		e.preventDefault();
		resetPassword();
	});

});

function login() {
	let email = $('#emailLogin').val();
	let password = $('#passwordLogin').val();
	if (email != null && password != null) {
		$.ajax({
			url: './login',
			dataType: 'json',
			type: 'post',
			data: {
				'email': email,
				'password': password
			},
			success: function (data) {
				if (data.LOGIN == 'true') {
					window.location.replace('./areaUtente');
				} else {
					$('#formLogin').trigger('reset');
					$('#divAlertLogin').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>');
				}
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#formLogin').trigger('reset');
		$('#divAlertLogin').html('<div class="alert alert-info center"><strong>Errore!</strong> Inserire i dati nei relativi campi.</div>');
	}
}

function registrazione() {
	let nome = $('#nomeRegistrazione').val();
	let cognome = $('#cognomeRegistrazione').val();
	let cellulare = $('#cellulareRegistrazione').val();
	let email = $('#emailRegistrazione').val();
	let password = $('#passwordRegistrazione').val();
	let ripassword = $('#ripasswordRegistrazione').val();
	if (nome != null && cognome != null && cellulare != null && email != null && password != null && ripassword != null) {
		$.ajax({
			url: './registrazione',
			dataType: 'json',
			type: 'post',
			data: {
				'nome': nome,
				'cognome': cognome,
				'cellulare': cellulare,
				'email': email,
				'password': password,
				'ripassword': ripassword
			},
			success: function (data) {
				$('#formRegistrazione').trigger('reset');
				$('#divAlertRegistrazione').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>');
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#formRegistrazione').trigger('reset');
		$('#divAlertRegistrazione').html('<div class="alert alert-info center"><strong>Errore!</strong> Inserire i dati nei relativi campi.</div>');
	}
}

function resetPassword() {
	let email = $('#emailResetPassword').val();
	if (email != null) {
		$.ajax({
			url: './resetPassword',
			dataType: 'json',
			type: 'post',
			data: {
				'email': email
			},
			success: function (data) {
				$('#formResetPassword').trigger('reset');
				$('#divAlertResetPassword').html('<div class="alert alert-info center"><strong>' + data.TYPE + '</strong> ' + data.NOTIFICATION + '</div>');
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	} else {
		$('#formResetPassword').trigger('reset');
		$('#divAlertResetPassword').html('<div class="alert alert-info center"><strong>Errore!</strong> Inserire i dati nei relativi campi.</div>');
	}
}