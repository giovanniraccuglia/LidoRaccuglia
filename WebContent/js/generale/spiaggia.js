$(document).ready(function () {
	
	$("#date").val(setDate());
	$('input[type="date"]').prop('min', setDate());

	$('#linkSpiaggia').click(function () {
		updateMappa();
	});

	$('#date').change(function () {
		updateMappa();
	});

	setInterval(function () {
		updateMappa();
	}, 500000); //richiesta ogni 5 minuti

	$('#closeSpiaggia').click(function () {
		$("#date").val(setDate());
		$('input[type="date"]').prop('min', setDate());
	});

});

function checkInput(val) {
	if (val != null && val.trim() != "") {
		return true;
	} else {
		return false;
	}
}

function setDate() {
	let date = new Date(),
		dformat = [date.getFullYear(), ('0' + (date.getMonth() + 1)).slice(-2), ('0' + date.getDate()).slice(-2)].join('-');
	let time = 'T19:00';
	let day = 86400000;
	if (date.getTime() > Date.parse(dformat + time)) {
		let newDate = new Date(Date.parse(dformat) + day),
			newDformat = [newDate.getFullYear(), ('0' + (newDate.getMonth() + 1)).slice(-2), ('0' + newDate.getDate()).slice(-2)].join('-');
		return newDformat;
	} else {
		return dformat;
	}
}

function updateMappa() {
	let date = $('#date').val();
	if (checkInput(date)) {
		$.ajax({
			url: './spiaggia',
			dataType: 'json',
			type: 'post',
			data: {
				'dataPrenotazione': date
			},
			success: function (data) {
				buildMappa(data[0]);
				setPostazioni(data[1]);
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	}
}

function setPostazioni(data) {
	$.each(data, function (key, val) {
		let str = '<div class="postazione" id="postazione' + val.idPostazione + '" style="background-color: lightcoral"><a class="btn"><img src="img/postazione.png" style="width: 40px; height: 40px;"></a></div>';
		$('#postazione' + val.idPostazione).html(str);
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