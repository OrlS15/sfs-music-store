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

  //main
  startLoop(["Telecaster Relic", "Gibson Les Paul", "Fender Stratocaster", "Gibson SG"], true, 0);
}
startSearchbarAnimation(200, 50);
