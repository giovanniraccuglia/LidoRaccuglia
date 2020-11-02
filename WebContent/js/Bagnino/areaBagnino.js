$(document).ready(function () {

	let date = new Date(),
		dformat = [date.getFullYear(), ('0' + (date.getMonth() + 1)).slice(-2), ('0' + date.getDate()).slice(-2)].join('-');
	$("#date").val(dformat);
	$('input[type="date"]').prop('min', dformat);

	updateMappa();

	$('#date').change(function () {
		$('[data-toggle="popover"]').popover('hide');
		updateMappa();
	});

	setInterval(function () {
		updateMappa();
	}, 500000); //richiesta ogni 5 minuti

});

$(document).ajaxComplete(function () {
	$('[data-toggle="popover"]').popover();
});

function updateMappa() {
	if ($('#date').val() != null) {
		$.ajax({
			url: './areaUtente',
			dataType: 'json',
			type: 'post',
			data: {
				'dataPrenotazione': $('#date').val()
			},
			success: function (data) {
				buildMappa(data[2]);
				setPostazioni(data);
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	}
}

function setPostazioni(data) {
	let i = 0;
	$.each(data[0], function (key, val) {
		let info = data[1][i].nome + ' ' + data[1][i].cognome + '\u000A' + data[1][i].cellulare + '\u000A' + data[1][i].email;
		let str = '<div class="postazione" id="postazione' + val.idPostazione + '" style="background-color: lightcoral"><a data-toggle="popover" title="Info" data-content="' + info + '" class="btn"><img src="img/postazione.png" style="width: 40px; height: 40px;"></a></div>';
		$('#postazione' + val.idPostazione).html(str);
		i = i + 1;
	});
}

function buildMappa(data) {
	let mare = '<div class="text-center" style="background-color: lightskyblue; grid-row: 1; grid-column: 1 / span 10;"><small class="w3-opacity">MARE</small></div>';
	let battigia = '<div class="text-center" style=" background-color: #F2D16B; grid-row: 2; grid-column: 1 / span 10;"><small class="w3-opacity">BATTIGIA</small></div>';
	let postazioni = '';
	$.each(data, function (key, val) {
		postazioni += '<div class="postazione" id="postazione' + val.idPostazione + '"><a class="btn"><img src="img/postazione.png" style="width: 40px; height: 40px;"></a></div>';
	});
	$('#mappaLido').html(mare + battigia + postazioni);
}