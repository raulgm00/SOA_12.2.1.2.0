function abrirDetalleOperacion(evt){
    
    var pIdOperacion = evt.getSource().getProperty("pIdOperacion");
    var pUrl = evt.getSource().getProperty("pUrl");
    var url  = pUrl+"?pIdOperacion=" + pIdOperacion;    
    ventanaGestorOperaciones = window.open(url, "DetalleOperacion","width=1024px,height=1024px,resizable=1,location=0,scrollbars=1,fullscreen=1"); 
}