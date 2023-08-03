function inputLista(){
console.log('Entra en inputLista');
if(esChrome() || esOperaNav()){
console.log('Entra en if de validacion de navegador');

console.log('Id input component : ' + document.getElementById('pageTemplateID:t1:1:iclov1::content'));
var test = document.getElementById('pageTemplateID:t1:1:iclov1::content');
console.log('test : \n');
console.log(test);

if(test != null){
console.log('Entra en if de objeto nulo');

    configurarSolucionInputLista(test);

}else{
    console.log('Es nulo el objeto');
}
}else{
    console.log('No es chrome.');    
}

}
var posicionTop;
function configurarSolucionInputLista(elemento) {
    console.log('Entra en configurarSolucionInputLista');
    console.log(elemento.getAttribute("style"));
    var posicionAbsoluta;
    var mostrarDebajoCajaFecha;
    posicionAbsoluta = obtenerPosicionAbsolutaText(elemento);
    
    mostrarDebajoCajaFecha = mostrarDebajoCajaText(elemento);
    console.log('posicionAbsoluta : ' + posicionAbsoluta.top);
    if (mostrarDebajoCajaFecha) {
        posicionTop = posicionAbsoluta.top + 19;
    }
    else {
        posicionTop = posicionAbsoluta.top - 60;
    }

    console.log('Nueva posicion top: ' + posicionTop);
    console.log('Nueva posicion: ' + (mostrarDebajoCajaFecha ? 'Debajo' : 'Arriba'));
    
    setTimeout ('cambiarEstilosTablaPais()', 4000); 
    //cambiarEstilosTablaPais();
}

function cambiarEstilosTablaPais(){
    console.log('Entra en cambiarEstiloTablaPais.');
    var input;
    var listaPais;
    var nodoPadre;
    var nodoHijo1;
    var nodoHijo2;
    
    nodoPadre = document.getElementsByClassName("AFZOrderLayerContainer");
    console.log("nodo padre AFZOrderLayerContainer.");
    //console.log(nodoPadre);
    console.log(nodoPadre[0].childNodes);
    //nodoPadre.getElementsByClassName("AFZOrderLayer")[1].childNodes;
    nodoHijo1 = nodoPadre[0].childNodes;
    console.log('Nodo Hijo uno:\n');
    console.log(nodoHijo1);
    
    nodoHijo2 = nodoHijo1[1].childNodes;
    console.log('Nodo Hijo dos:\n');
    console.log(nodoHijo2);
    
    listaPais = nodoHijo2[0];
//    console.log(posicionTop);
//    var posicionTabla = '\'' + posicionTop + 'px;' + '\'';
//    console.log(posicionTabla);
var estiloOriginal = listaPais.getAttribute('style');
var splitStyle = estiloOriginal.split(';');
var estilos = "";
for(i = 0; i<splitStyle.length -1; i++){
    if(splitStyle[i].includes('top')){
        console.log('Se elimina el top del estilo.');
    }else{
        estilos = estilos + splitStyle[i] + ';';
    }
}

estilos = estilos + ' top: ' + posicionTop + 'px;';
console.log(estilos);

    listaPais.setAttribute('style', estilos);
    console.log('hijo con tabla paises');
    console.log(listaPais);
    console.log(listaPais.getAttribute('style'));
}

function esChrome() {
    var isChrome = false;
    // Chrome 1+
    console.log('Es chrome : ' + window.chrome);
    if (window.chrome != undefined) {
        if (window.chrome.webstore != undefined) {
            isChrome = true;
        }
    }
    console.log('Es chrome : ' + isChrome);
    return isChrome;
}

function esOperaNav() {
    var isOperaNav = false;
    isOperaNav = (navigator.userAgent.match(/Opera|OPR\//) ? true : false);
    return isOperaNav;
}

function obtenerPosicionAbsolutaText(elemento) {
    var top = 0, left = 0;
    
    do {    
        top += elemento.offsetTop  || 0;
        left += elemento.offsetLeft || 0;
        
        elemento = elemento.offsetParent;
    } while(elemento);
    return {
        top: top,
        left: left
    };
}

function mostrarDebajoCajaText(element) {
    var mostrarDebajo = true;
    var alturaVentana = document.documentElement.clientHeight;
    var ubicacionActualCaja = element.getBoundingClientRect().top;
    
    var porcentaje = 0;
    if (ubicacionActualCaja > 0) {
        porcentaje = (ubicacionActualCaja * 100) / alturaVentana;
    }
    
    if (porcentaje > 73) {
        mostrarDebajo = false;
    }
    
    console.log('Altura de ventana: ' + alturaVentana);
    console.log('Ubiccion de la caja: ' + ubicacionActualCaja);
    console.log('Ubicacion en porcentaje: ' + porcentaje);
    
    return mostrarDebajo;
}
