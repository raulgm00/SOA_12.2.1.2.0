function verCuestionario(evt){ 
    var idCuestionario = evt.getSource().getProperty("idCuestionario");
    var idEvaluacion = evt.getSource().getProperty("idEvaluacion");
    var rutaAplicacion = evt.getSource().getProperty("rutaAplicacion");
    var urlVerCuestionario = rutaAplicacion+"?CodigoEvaluacion=" + idEvaluacion +"&CodigoModelo=" + idCuestionario;
    
    ventanaVerExpediente = window.open(urlVerCuestionario,"width=500px,height=400px,resize=0,location=0"); 
}