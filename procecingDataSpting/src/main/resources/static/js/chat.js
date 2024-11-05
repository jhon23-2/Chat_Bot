function sendMessage(){

   const text = document.getElementById("messageText").value;

    const formData = new FormData();
    formData.append('message', text);

    fetch('/application/message',{
        method: 'POST',
        //credentials: 'same-origin',
        body: formData
    }).then(response=>{
        return response.json();
    }).then(data=>{
        console.log(data);
    }).catch(error=>console.log(error))

}
