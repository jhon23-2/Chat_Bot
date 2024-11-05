function uploadExcel(){
      const fileInput = document.getElementById('fileInput');
      const resultsDiv = document.getElementById('results');
      const file = fileInput.files[0];

      if (!file) {
            alert('Por favor selecciona un archivo Excel');
            return;
      }

      const formData = new FormData();
      formData.append('file', file);
      resultsDiv.innerHTML = 'Procesando archivo...';


    fetch('/application/read', {
        method: 'POST',
        //credentials: 'same-origin',
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
        resultsDiv.innerHTML = "Archivo Procesado Exitosamente";
    })
    .catch(error => {
        console.error('Error:', error);
        resultsDiv.innerHTML = 'Error al procesar el archivo';
    });

}