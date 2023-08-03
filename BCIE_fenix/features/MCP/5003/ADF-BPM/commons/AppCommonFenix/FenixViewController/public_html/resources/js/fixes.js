
// El workaround se aplica unicamente para Google Chrome y Opera
if (esGoogleChrome() || esOpera()) {
    AdfLogger.LOGGER.logMessage(AdfLogger.WARNING, "Inicia configuracion de solucion para FNXII-6683");
    
    // Se inicia configuracion de solucion para incidencia FNXII-6683
    configurarSolucionFNXII6683();
}

function configurarSolucionFNXII6683() {

    // Se agrega la solucion a la funcionalidad actual del evento animate
    AdfDhtmlElementAnimator.animate = function (a,b,d,e,f,g,h){
        AdfLogger.LOGGER.logMessage(AdfLogger.WARNING, "Inicia animacion de componente");
        
        // Se aplica la solucion dentro del evento animate
        aplicarSolucionFNXII6683(d,g);
        
        // Se realiza la animacion normal del framework
        realizarAnimacion(a,b,d,e,f,g,h);
    };
}

function aplicarSolucionFNXII6683(d,g) {
    // Se valida que el componente recibido se trate de un calendario de AdfInputDate
    try {
        if (g.wrapper != undefined) {
            if (g.wrapper.firstChild.getAttribute('data-afr-popupid').includes('pop')) {
                AdfLogger.LOGGER.logMessage(AdfLogger.WARNING, "Inicia remplazo de propiedad top");
                
                // Se obtiene icono de calendario de AdfInputDate
                var iconoCalendarioBtn = document.getElementById(g.myself._alignElementId);
                
                // Se calcula la posicion abosuluta top
                var posicionAbsoluta = obtenerPosicionAbsoluta(iconoCalendarioBtn);
                
                // Se identifica si el calendario se muestra por encima o por debajo de la caja de fecha
                var posicionTop;
                var mostrarDebajoCajaFecha = mostrarDebajoCaja(iconoCalendarioBtn);
                if (mostrarDebajoCajaFecha) {
                    posicionTop = posicionAbsoluta.top + 18;
                } else {
                    posicionTop = posicionAbsoluta.top -167;
                }
                
                AdfLogger.LOGGER.logMessage(AdfLogger.WARNING, "Nueva posicion top: " + posicionTop);
                AdfLogger.LOGGER.logMessage(AdfLogger.WARNING, "Nueva posicion: " + (mostrarDebajoCajaFecha ? 'Debajo' : 'Arriba' ));
                
                // Se actualiza la posicion top del componente recibido
                d[0].element.style.top = posicionTop + "px";
                d[0].properties.top = posicionTop;
            }
        }
    } catch(ex) {
        AdfLogger.LOGGER.logMessage(AdfLogger.WARNING, ex);
    }
    
}

function realizarAnimacion(a,b,d,e,f,g,h) {
    for(var k=AdfAgent.AGENT,l=d.length,m=Array(l),n=0;n<l;n++){var o=d[n],p=o.properties,p=void 0===p?o.properties:p,q=o.element,q=void 0===q?o.element:q,o={};AdfDhtmlElementAnimator._gatherSizeState(o,p,q,"width","offsetWidth","borderLeftWidth","borderRightWidth");AdfDhtmlElementAnimator._gatherSizeState(o,p,q,"height","offsetHeight","borderTopWidth","borderBottomWidth");var s=p.alpha;if(null!=s&&!isNaN(s)){var t=q.style.opacity;if(q.ownerDocument.all&&
null==t)try{t=q.filters.alpha.opacity/100}catch(u){}if(null==t||""==""+t)t=1;o.opacity=[t,s/100,!0]}s=p.zIndex;null!=s&&!isNaN(s)&&(o.zIndex=[AdfDhtmlElementAnimator._getElementZIndex(k,q),s,!0]);s=p.top;null!=s&&!isNaN(s)&&(t=(t=q.style.top)&&"auto"!=t?parseInt(t):k.getElementTop(q),o.top=[t,s]);s=p.left;null!=s&&!isNaN(s)&&(t=(t=q.style.left)&&"auto"!=t?parseInt(t):k.getElementLeft(q),o.left=[t,s]);s=p.right;null!=s&&!isNaN(s)&&(t=(t=q.style.right)&&"auto"!=t?parseInt(t):k.getElementLeft(q),o.right=
[t,s]);s=p.offsetTop;null!=s&&!isNaN(s)&&(o.offsetTop=[q.offsetTop,s]);s=p.offsetLeft;null!=s&&!isNaN(s)&&(o.offsetLeft=[q.offsetLeft,s]);s=p.scrollLeft;null!=s&&!isNaN(s)&&(o.scrollLeft=[q.scrollLeft,s,!0]);p=p.scrollTop;null!=p&&!isNaN(p)&&(o.scrollTop=[q.scrollTop,p,!0]);m[n]={element:q,state:o}}b=Math.max(1,Math.round(b*AdfDhtmlElementAnimator._FRAMES_PER_MILLISECOND));AdfPage.PAGE.isAnimationEnabled()||(b=1);return(new AdfDhtmlElementAnimator(m,e,f,g,h,a,b))._start();
}

function esGoogleChrome() {
    var isChrome = false;
    // Chrome 1+
    if (window.chrome != undefined) {
        if (window.chrome.webstore != undefined) {
            isChrome = true;
        }
    }
    return isChrome;
}

function esOpera() {
    var isOperaNav = false;
    isOperaNav = (navigator.userAgent.match(/Opera|OPR\//) ? true : false);
    return isOperaNav;
}

function obtenerPosicionAbsoluta(elemento) {
    var top = 0, left = 0;
    
    // Se itera cada elemento padre hasta llegar al elemento raiz
    do {
        // Se acumula la propiedad top y left
        top += elemento.offsetTop  || 0;
        left += elemento.offsetLeft || 0;
        
        elemento = elemento.offsetParent;
    } while(elemento);

    // Se retorna la posicion absoluta para top y left
    return {
        top: top,
        left: left
    };
}

function mostrarDebajoCaja(element) {
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
    
    AdfLogger.LOGGER.logMessage(AdfLogger.WARNING, "Altura de ventana: " + alturaVentana);
    AdfLogger.LOGGER.logMessage(AdfLogger.WARNING, "Ubiccion de la caja: " + ubicacionActualCaja);
    AdfLogger.LOGGER.logMessage(AdfLogger.WARNING, "Ubicacion en porcentaje: " + porcentaje);
    
    return mostrarDebajo;
}