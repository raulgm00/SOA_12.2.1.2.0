function enviarArchivo(idPregunta){
    //Some code
    console.log("value respuesta :"+idPregunta);

    //Se da click al boton de ADF
    //este id es cuando se prueba el componente comun
    //var button = AdfPage.PAGE.findComponentByAbsoluteId("r1:0:btnAbrePopUpEvidencia");
    //este id es cuando se prueba en el GHT
    var button = AdfPage.PAGE.findComponentByAbsoluteId("pTID:r5:0:btnAbrePopUpEvidencia");
    //pTID:r5:0:btnAbrePopUpEvidencia
    AdfActionEvent.queue(button,true);
    
    //Se agrega valor del idPregunta al input de ADF
    //este id es cuando se prueba el componente comun
    //var input = document.getElementById('r1:0:inputTextIdPregunta::content');
    //este id es cuando se prueba en el GHT
    var input = document.getElementById('pTID:r5:0:inputTextIdPregunta::content');
    //pTID:r5:0:inputTextIdPregunta::content
    input.value = idPregunta;

}

function insertarNombreEvidenciaHtml(idElemento,nombreEvidencia){
    console.log("idElemento :"+idElemento);
    console.log("nombreEvidencia :"+nombreEvidencia);
    var elemento = document.getElementById(idElemento);
    elemento.innerHTML = nombreEvidencia;
}

function soloNumeros(e){
	var key = window.Event ? e.which : e.keyCode
	return (key >= 48 && key <= 57)
}

var height = 50;
var width = 290;

         
function dimensionar(element) {
var text = document.getElementById(element.id); 


 if((event.ctrlKey || event.metaKey) && (event.which == 40 || event.keyCode == 40)){
        height = height +10;
        text.style.height = (height)+"px";
 }
 
 if ((event.ctrlKey || event.metaKey) && (event.which == 38 || event.keyCode == 38)){
    if(height > 50){     
      height = height - 10;
      text.style.height = (height)+"px";
    }
  }
    
    
  if((event.ctrlKey || event.metaKey) && (event.which == 39 || event.keyCode == 39)){
    width = width +10;
    text.style.width = (width)+"px";
  }
  if((event.ctrlKey || event.metaKey) && (event.which == 37 || event.keyCode == 37)){

    if(width > 290){     
      width = width - 10;
      text.style.width = (width)+"px";
    }
  }

console.log("height: "+height+"width: "+width);

}