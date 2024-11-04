(function changeClass(doc){
    const btnSignIn = doc.getElementById("sign-in"),
              btnSignUp = doc.getElementById("sign-up"),
              containerFormRegister = doc.querySelector(".register"),
              containerFormLogin = doc.querySelector(".login");

        btnSignIn.addEventListener("click", e => {
            containerFormRegister.classList.add("hide");
            containerFormLogin.classList.remove("hide")
        })


        btnSignUp.addEventListener("click", e => {
            containerFormLogin.classList.add("hide");
            containerFormRegister.classList.remove("hide")
        })
})(document);

/*function enviarFormulario() {

    const formData = {
        name_user: document.getElementById('username').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value
    };

    fetch('/application/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
    .then(response => {
        if (response.ok) {
            alert("Registro exitoso");
            clear();
        } else {
            alert("Error en el registro");
        }
    })
    .catch(error => {
        alert("Error " + error)
        console.error("Error:", error);
    });

    console.log("peticion al formulario de registro");
}

function clear(){
        document.getElementById('nameUser').value = "";
        document.getElementById('email').value="";
        document.getElementById('password').value="";
}*/