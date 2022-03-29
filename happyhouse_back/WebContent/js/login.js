const form = document.querySelector("#loginform");
(eField = form.querySelector(".email")),
  (eInput = eField.querySelector("input")),
  (pField = form.querySelector(".password")),
  (pInput = pField.querySelector("input"));

form.onsubmit = (e) => {
  e.preventDefault(); //preventing from form submitting
  //if email and password is blank then add shake class in it else call specified function
  if (eInput.value == "") {
    eField.classList.add("shake", "error");
  }

  if (pInput.value == "") {
    pField.classList.add("shake", "error");
  }

  setTimeout(() => {
    //remove shake class after 500ms
    eField.classList.remove("shake");
    pField.classList.remove("shake");
  }, 500);





};
