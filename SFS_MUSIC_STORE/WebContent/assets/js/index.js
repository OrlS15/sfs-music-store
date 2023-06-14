$(document).ready(() => {
	//placeholder animation
	function startSearchbarAnimation(avantiSpeed, indietroSpeed) {
		const searchbar = document.querySelector("#searchbar input");

		function placeholderTypingEffect(input, text, speed, avanti = true) {
			return new Promise((resolve, _) => {
				let i = avanti ? 0 : text.length - 1;
				let interval = setInterval(() => {
					if (i >= 0 && i <= text.length) {
						input.placeholder = text.slice(0, i) + " |";
						if (avanti) i++;
						else i--;
					} else {
						clearInterval(interval);
						resolve(true);
					}
				}, speed);
			});
		}

		function startLoop(instrumentsList, avanti, j) {
			placeholderTypingEffect(
				searchbar,
				instrumentsList[j],
				avanti ? avantiSpeed : indietroSpeed,
				avanti
			).then(() => {
				startLoop(instrumentsList, !avanti, !avanti ? (j + 1) % instrumentsList.length : j);
			});
		}

		startLoop(["Telecaster Relic", "Gibson Les Paul", "Fender Stratocaster", "Gibson SG"], true, 0);
	}
	startSearchbarAnimation(200, 50);

	//ajax
	function updateProducts() {
		$.post("AjaxSearchProduct", { "search": $("#searchbar input").val() }, (data) => {
			console.log(data.products);

			if (!data?.error) {
				let ul = document.querySelector(".searchbar-ajax ul");
				data.products.forEach((d) => {
					let li = document.createElement("li");
					li.textContent = d.nome;
					li.onclick = () => window.location = location.pathname + "/pagina-prodotto?id=" + d.id
					ul.append(li);
				})
			}
		});
	}
	$("#searchbar input").on("input", () => {
		let val = $("#searchbar input").val();
		if (val == null || val == "") {
			document.querySelector(".searchbar-ajax").style.display = "none";
		} else {
			document.querySelector(".searchbar-ajax").style.display = "block";
			document.querySelector(".searchbar-ajax ul").innerHTML = "";
			updateProducts()
		}
	});
})