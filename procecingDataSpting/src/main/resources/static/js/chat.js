((d, w) => {
  const button = d.getElementById("btn")
  const textArea = d.querySelector(".div-panel")
  const inputMessage = d.getElementById("txt-request")

  const toggler = document.querySelector(".toggler-btn");
  toggler.addEventListener("click", function () {
    document.querySelector("#sidebar").classList.toggle("collapsed");
  });

  button.addEventListener("click", (e) => {
    addRequestToPanel(textArea, inputMessage);
    addResponseToPanel(textArea,inputMessage.value);
    clear(inputMessage);
  })

  inputMessage.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
      addRequestToPanel(textArea, inputMessage);
      addResponseToPanel(textArea,inputMessage.value);
      clear(inputMessage);
    }
  })


})(document, window)

function clear(elemet) {
  elemet.value = "";
}

function addRequestToPanel(textArea, inputText = "") {
  const p = document.createElement("p")
  const div = document.createElement("div")

  $(p).attr("id", "request").text(inputText.value);
  $(div).attr("class", "content-div-request").append(p);

  $(textArea).append(div)
}

async function addResponseToPanel(textArea = null,question = "") {

    let responseText = "";
   const formData = new FormData();
   formData.append('message', question);

   const responseAsy = await fetch('/application/message',{
                                       method: 'POST',
                                       body: formData
                                     });

   const responseJson = await responseAsy.json();

   for(const element in responseJson){
        responseText = responseJson[element];
   }

  if (textArea != null) {

    //TODO... loader

    setTimeout(()=>{
        const p = document.createElement("p");
        const div = document.createElement("div");

        $(p).attr("id", "response").text(responseText);
        $(div).attr("class", "content-div-response").append(p);

        $(textArea).append(div);
    },2000);

  }

}


(()=>{
    const dataList = document.getElementById("encodings");
    let arr ="";

    fetch('/application/questions',{
            method: 'GET',
    }).then(response=>{
        return response.json();
    }).then(data=>{
        for(const element in data){
            arr += data[element] +",";
        }
        const lista = arr.split(",");
        addParamenter(dataList,...lista);
    }).catch(error=>console.log(error))

})();

function addParamenter(datalist,...params) {

    for (let i = 0; i < params.length; i++) {
        const option = document.createElement("option");
        option.innerText = params[i];
        option.setAttribute("value",params[i]);
        datalist.appendChild(option);
    }

}



/*
function sendMessage(text){

   let response = "";
   const formData = new FormData();
   formData.append('message', text);

   fetch('/application/message',{
          method: 'POST',
          body: formData
        }).then(response=>{
            return response.json();
        }).then(data=>{
            console.log(data);
        }).catch(error=>console.log(error))
}

*/

