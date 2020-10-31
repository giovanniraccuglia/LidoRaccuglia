$(document).ready(function () {

	$('#linkMenu').click(function () {
		$.ajax({
			url: './menu',
			dataType: 'json',
			type: 'post',
			success: function (data) {
				let j = 0;
				let str = '';
				let lista1 = ['Colazione', 'Panini', 'Rustici', 'Insalatone', 'Frutta e Dessert', 'Antipasti Di Mare', 'Antipasti Di Terra', 'Primi Di Mare', 'Primi Di Terra', 'Secondi Di Mare', 'Secondi Di Terra', 'Dessert', 'Caffetteria', 'Birre', 'Cocktails', 'Vini Bianchi', 'Vini Rossi', 'Distillati', 'Analcolici'];
				let lista2 = ['divColazione', 'divPanini', 'divRustici', 'divInsalatone', 'divFruttaDessert', 'divAntipastiMare', 'divAntipastiTerra', 'divPrimiMare', 'divPrimiTerra', 'divSecondiMare', 'divSecondiTerra', 'divDessert', 'divCaffetteria', 'divBirre', 'divCocktails', 'divViniBianchi', 'divViniRossi', 'divDistillati', 'divAnalcolici'];
				for (i = 0; i < lista1.length; ++i) {
					if (getListaCategoria(data, lista1[i]).length > 0) {
						str += '<div id="' + lista2[j] + '"><h3 class="w3-opacity">' + lista1[i] + '</h3><table class="table table-hover"><thead></thead><tbody>';
						$.each(getListaCategoria(data, lista1[i]), function (key, val) {
							str += '<tr><td><strong>' + val.nome + '</strong><span style="display: block"></span><i>' + val.descrizione + '</i></td><td>' + val.prezzo + '&nbsp;&euro;</td></tr>';
						});
						str += '</tbody></table></div>';
					}
					j = j + 1;
				}
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

function getListaCategoria(lista, c) {
	return lista.filter(function (x) {
		return x.categoria == c;
	});
}