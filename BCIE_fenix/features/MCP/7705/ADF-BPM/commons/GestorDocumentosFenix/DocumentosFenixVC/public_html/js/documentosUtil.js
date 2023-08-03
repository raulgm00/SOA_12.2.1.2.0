function descargarDocumentoOnbase(evt){
    
    var idOnbase = evt.getSource().getProperty("idOnbase");
    var rutaOnbase = evt.getSource().getProperty("rutaOnbase");
    var urlDescarga = rutaOnbase+"?docid=" + idOnbase;
    
    ventanaVerExpediente = window.open(urlDescarga, "descargarDocumento","width=800px,height=600px,resize=0,location=0"); 
}
