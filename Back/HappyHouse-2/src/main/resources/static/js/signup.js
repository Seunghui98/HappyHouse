const form = document.querySelector("form");
eField = form.querySelector(".email"),
eInput = eField.querySelector("input"),
pField = form.querySelector(".password"),
pInput = pField.querySelector("input");
nField = form.querySelector("#form_name"),
nInput = nField.querySelector("input");
aField = form.querySelector("#form_address"),
aInput = aField.querySelector("input");
phField = form.querySelector("#form_phoneNumber"),
phInput = phField.querySelector("input");


form.onsubmit = (e) => {
    e.preventDefault(); //preventing from form submitting
    //if email and password is blank then add shake class in it else call specified function
    if (eInput.value == "") {
        eField.classList.add("shake", "error");
        return;
    }

    if (pInput.value == "") {
        pField.classList.add("shake", "error");
        return;
    }

    if (!nInput.value) {
        nField.classList.add("shake", "error");
        return;
    }

    if (!aInput.value) {
        aField.classList.add("shake", "error");
        return;
    }

    if (!phInput.value) {
        phField.classList.add("shake", "error");
        return;
    }
    

    setTimeout(() => { //remove shake class after 500ms
        eField.classList.remove("shake");
        pField.classList.remove("shake");
        nField.classList.remove("shake");
        aField.classList.remove("shake");
        phField.classList.remove("shake");
    }, 500);

    const user = {
        id: eInput.value,
        password: pInput.value,
        name: nInput.value,
        address: aInput.value,
        phone: phInput.value
    };

    
    localStorage.setItem("user", JSON.stringify(user));
    alert("회원가입 성공 !");
    location.replace("index.html");
  

  //if eField and pField doesn't contains error class that mean user filled details properly
    if (!eField.classList.contains("error") && !pField.classList.contains("error")
        && !e2Field.classList.contains("error")  && !aField.classList.contains("error")) {
    window.location.href = form.getAttribute("action"); //redirecting user to the specified url which is inside action attribute of form tag
  }
}