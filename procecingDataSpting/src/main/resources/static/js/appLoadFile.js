function uploadExcel(){
      const fileInput = document.getElementById('fileInput');
      const resultsDiv = document.getElementById('results');
      const file = fileInput.files[0];

      if (!file) {
          swal({
                title: "with success!",
                text: "You must select a file",
                icon: "warning",
                button: "Try again",
          });

        return;
      }

      const formData = new FormData();
      formData.append('file', file);

      resultsDiv.setAttribute("class","spinner-border text-primary");
      resultsDiv.innerHTML = `
        <span class="visually-hidden">Loading...</span>
      `;

    fetch('/application/read', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('No autorizado');
        }
        return response.json();
    })
    .then(data => {
        console.log(data);

        resultsDiv.setAttribute("class","");
        resultsDiv.innerHTML = "";
        swal({
              title: "¡Uploaded!",
              text: "File Uploaded Successfully",
              icon: "success",
              button: "Success",
        });
    })
    .catch(error => {
        console.log(error);
        swal({
            title: "¡Error!",
            text: "Error to Process file. try again",
            icon: "error",
            button: "Success",
        });
    });

}

(()=>{
    const hamBurger = document.querySelector(".toggle-btn");

    hamBurger.addEventListener("click", function () {
      document.querySelector("#sidebar").classList.toggle("expand");
    });
})();