$(document).ready(function () {
	
	$('#linkMenu').click(function () {
		$.ajax({
			url: './menu',
			dataType: 'json',
			type: 'post',
			success: function (data) {
				let str1 = '<div id="divColazione"><h3 class="w3-opacity">Colazione</h3><table class="table table-hover"><thead></thead><tbody>';
				let str2 = '<div id="divPanini"><h3 class="w3-opacity">Panini</h3><table class="table table-hover"><thead></thead><tbody>';
				let str3 = '<div id="divRustici"><h3 class="w3-opacity">Rustici</h3><table class="table table-hover"><thead></thead><tbody>';
				let str4 = '<div id="divInsalatone"><h3 class="w3-opacity">Insalatone</h3><table class="table table-hover"><thead></thead><tbody>';
				let str5 = '<div id="divFruttaDessert"><h3 class="w3-opacity">Frutta e Dessert</h3><table class="table table-hover"><thead></thead><tbody>';
				let str6 = '<div id="divAntipastiMare"><h3 class="w3-opacity">Antipasti Di Mare</h3><table class="table table-hover"><thead></thead><tbody>';
				let str7 = '<div id="divAntipastiTerra"><h3 class="w3-opacity">Antipasti Di Terra</h3><table class="table table-hover"><thead></thead><tbody>';
				let str8 = '<div id="divPrimiMare"><h3 class="w3-opacity">Primi Di Mare</h3><table class="table table-hover"><thead></thead><tbody>';
				let str9 = '<div id="divPrimiTerra"><h3 class="w3-opacity">Primi Di Terra</h3><table class="table table-hover"><thead></thead><tbody>';
				let str10 = '<div id="divSecondiMare"><h3 class="w3-opacity">Secondi Di Mare</h3><table class="table table-hover"><thead></thead><tbody>';
				let str11 = '<div id="divSecondiTerra"><h3 class="w3-opacity">Secondi Di Terra</h3><table class="table table-hover"><thead></thead><tbody>';
				let str12 = '<div id="divDessert"><h3 class="w3-opacity">Dessert</h3><table class="table table-hover"><thead></thead><tbody>';
				let str13 = '<div id="divCaffetteria"><h3 class="w3-opacity">Caffetteria</h3><table class="table table-hover"><thead></thead><tbody>';
				let str14 = '<div id="divBirre"><h3 class="w3-opacity">Birre</h3><table class="table table-hover"><thead></thead><tbody>';
				let str15 = '<div id="divCocktails"><h3 class="w3-opacity">Cocktails</h3><table class="table table-hover"><thead></thead><tbody>';
				let str16 = '<div id="divViniBianchi"><h3 class="w3-opacity">Vini Bianchi</h3><table class="table table-hover"><thead></thead><tbody>';
				let str17 = '<div id="divViniRossi"><h3 class="w3-opacity">Vini Rossi</h3><table class="table table-hover"><thead></thead><tbody>';
				let str18 = '<div id="divDistillati"><h3 class="w3-opacity">Distillati</h3><table class="table table-hover"><thead></thead><tbody>';
				let str19 = '<div id="divAnalcolici"><h3 class="w3-opacity">Analcolici</h3><table class="table table-hover"><thead></thead><tbody>';

				$.each(data, function (key, val) {
					if (val.categoria == 'Colazione Bar') {
						str1 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Panini Bar') {
						str2 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Rustici Bar') {
						str3 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Insalatone Bar') {
						str4 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Frutta e Dessert Bar') {
						str5 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Antipasti Di Mare') {
						str6 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Antipasti Di Terra') {
						str7 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Primi Di Mare') {
						str8 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Primi Di Terra') {
						str9 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Secondi Di Mare') {
						str10 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Secondi Di Terra') {
						str11 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Dessert') {
						str12 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Caffetteria') {
						str13 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Birre') {
						str14 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Cocktails') {
						str15 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Vini Bianchi') {
						str16 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Vini Rossi') {
						str17 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Distillati') {
						str18 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					} else if (val.categoria == 'Analcolici') {
						str19 += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
					}
				});
				str1 += '</tbody></table></div>';
				str2 += '</tbody></table></div>';
				str3 += '</tbody></table></div>';
				str4 += '</tbody></table></div>';
				str5 += '</tbody></table></div>';
				str6 += '</tbody></table></div>';
				str7 += '</tbody></table></div>';
				str8 += '</tbody></table></div>';
				str9 += '</tbody></table></div>';
				str10 += '</tbody></table></div>';
				str11 += '</tbody></table></div>';
				str12 += '</tbody></table></div>';
				str13 += '</tbody></table></div>';
				str14 += '</tbody></table></div>';
				str15 += '</tbody></table></div>';
				str16 += '</tbody></table></div>';
				str17 += '</tbody></table></div>';
				str18 += '</tbody></table></div>';
				str19 += '</tbody></table></div>';
				let str = str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8 + str9 + str10 + str11 + str12 + str13 + str14 + str15 + str16 + str17 + str18 + str19;
				$('#divMenu').html(str);
			},
			error: function (errorThrown) {
				console.log(errorThrown);
			}
		});
	});

	$('#linkColazione').click(function () {
		$('#divMenu').children('div').hide();
		$('#divColazione').show();
	});

	$('#linkPanini').click(function () {
		$('#divMenu').children('div').hide();
		$('#divPanini').show();
	});

	$('#linkRustici').click(function () {
		$('#divMenu').children('div').hide();
		$('#divRustici').show();
	});

	$('#linkInsalatone').click(function () {
		$('#divMenu').children('div').hide();
		$('#divInsalatone').show();
	});

	$('#linkFruttaDessert').click(function () {
		$('#divMenu').children('div').hide();
		$('#divFruttaDessert').show();
	});

	$('#linkAntipastiMare').click(function () {
		$('#divMenu').children('div').hide();
		$('#divAntipastiMare').show();
	});

	$('#linkAntipastiTerra').click(function () {
		$('#divMenu').children('div').hide();
		$('#divAntipastiTerra').show();
	});

	$('#linkPrimiMare').click(function () {
		$('#divMenu').children('div').hide();
		$('#divPrimiMare').show();
	});

	$('#linkPrimiTerra').click(function () {
		$('#divMenu').children('div').hide();
		$('#divPrimiTerra').show();
	});

	$('#linkSecondiMare').click(function () {
		$('#divMenu').children('div').hide();
		$('#divSecondiMare').show();
	});

	$('#linkSecondiTerra').click(function () {
		$('#divMenu').children('div').hide();
		$('#divSecondiTerra').show();
	});

	$('#linkDessert').click(function () {
		$('#divMenu').children('div').hide();
		$('#divDessert').show();
	});

	$('#linkCaffetteria').click(function () {
		$('#divMenu').children('div').hide();
		$('#divCaffetteria').show();
	});

	$('#linkBirre').click(function () {
		$('#divMenu').children('div').hide();
		$('#divBirre').show();
	});

	$('#linkCocktails').click(function () {
		$('#divMenu').children('div').hide();
		$('#divCocktails').show();
	});

	$('#linkViniBianchi').click(function () {
		$('#divMenu').children('div').hide();
		$('#divViniBianchi').show();
	});

	$('#linkViniRossi').click(function () {
		$('#divMenu').children('div').hide();
		$('#divViniRossi').show();
	});

	$('#linkDistillati').click(function () {
		$('#divMenu').children('div').hide();
		$('#divDistillati').show();
	});

	$('#linkAnalcolici').click(function () {
		$('#divMenu').children('div').hide();
		$('#divAnalcolici').show();
	});
	
});