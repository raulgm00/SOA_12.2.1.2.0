//Objetivo: Validar se ingresen solo numeros
function soloNumeros(event) {  
    var ignoredKeys = new Array(AdfKeyStroke.BACKSPACE_KEY, AdfKeyStroke.DELETE_KEY, AdfKeyStroke.ARROWLEFT_KEY,
    ARROWRIGHT_KEY);  
    var keyCode = event.getKeyCode();  
    
    if(ignoredKeys.indexOf(keyCode) > -1){  
        return;  
    }  
    
    if(keyCode < 48 || keyCode > 57){  
        event.cancel();  
    }  
}


function filterForNumbers(event) {  
    var ignoredKeys = new Array(AdfKeyStroke.BACKSPACE_KEY, AdfKeyStroke.DELETE_KEY);  
    var keyCode = event.getKeyCode();  
    
    if(ignoredKeys.indexOf(keyCode) > -1){  
        return;  
    }  
    
    if(keyCode > 31 && (keyCode < 48 || keyCode > 57)){  
        event.cancel();  
    }  
} 