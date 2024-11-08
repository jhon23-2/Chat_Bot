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