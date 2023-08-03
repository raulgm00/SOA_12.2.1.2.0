package pa03admoncondicionesght.beans;

import java.io.IOException;
import java.io.Serializable;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.jbo.Row;

import oracle.jbo.ViewObject;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

/**
 * Managed Bean de Flujo para el proceso de Administracion de Condiciones.
 * 
 * @author Latbc.
 */
public class CondicionesBean  extends FenixPanelBean  implements Serializable
{
  @SuppressWarnings("compatibility:4209037933012793557")
  private static final long serialVersionUID = 1L;
  
  /**
   * Log de la aplicacion
   */
  private static ADFLogger logger = null;
  
  /**
   * Constates para manejo de Payload
   */
  private static final String CODIGO_OPERACION = "CodigoOperacion";
  private static final String CODIGO_TAREA = "CodigoTarea";
  private static final String CODIGO_ROL = "CodigoRol";
    
    // Variables de apoyo
    private oracle.jbo.domain.Number operacionId;
    private String observacionValidarCondicion;
    private Boolean esRetornoCumplirCondiciones = Boolean.FALSE; // Indica si entro como un retorno a la página Cumplir condiciones
    
    // Variables del Payload
    private String idOperacion;
    private String idTarea;
    private String idRol;
    //private Long idEvento = 0L; // Se inicializa con cero por si viene vacío
    private Boolean esRequiereAsjur = Boolean.FALSE;
    private Boolean esRequiereSepri = Boolean.FALSE;
    private Boolean esRequiereAed = Boolean.FALSE;
    private Boolean esRequierePct = Boolean.FALSE;
    private Boolean esRequiereSupervision = Boolean.FALSE;
    private Boolean esRequiereCofi = Boolean.FALSE;
    
    //Se agregan nuevas banderas por nuevas categorias de condiciones para requerir sus validadores
    private Boolean esRequiereAnalistaDaeci = Boolean.FALSE;
    private Boolean esRequiereAnalistaOfic = Boolean.FALSE;
    private Boolean esRequiereAnalistaFinam = Boolean.FALSE;
    private Boolean esRequiereEspAmbiental = Boolean.FALSE;
    private Boolean esRequiereEjecutivoFinam = Boolean.FALSE;
    private Boolean esRequiereSegCred = Boolean.FALSE;

    private Long idAgrupador = 0L; // Se crea variable idAgrupador para sustituir a IdEvento
    private Boolean validaNumeroCondicion = Boolean.FALSE;
    private Long numeroCondicion = null;
    private String mensajeValidar = null;
    private Boolean condicionValidada = Boolean.FALSE;
    private Integer idCondicionSeleccionada;
    
    private String instanciaProceso;
    private Boolean esEstadoCompletado = Boolean.FALSE;//Variable Estado BPM

    /**
     * Constructor por defecto
     */
    public CondicionesBean()
    {
        if (logger == null) {
          logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
  
    public void getPayloadInformation() throws WorkflowException, IOException { 
        logger.log(ADFLogger.TRACE, "Inside getPayloadInformation.");
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();
        NodeList nl;

        try {
            java.io.StringWriter writer = new java.io.StringWriter();
            xmlPayload.print(writer);
            String payloadAsString = writer.toString();
            logger.log(ADFLogger.WARNING, "- Payload Admon Condiciones -");
            logger.log(ADFLogger.WARNING, payloadAsString);
        }
        catch(IOException e){
            logger.log(ADFLogger.ERROR, e.getMessage());
        }

        nl = xmlPayload.getElementsByTagName(CODIGO_OPERACION);
        if(nl != null && (nl.getLength()>0)) {
            idOperacion = nl.item(0).getTextContent();
          
            try {
              if((idOperacion != null) && (idOperacion.trim().length() > 0))
                  operacionId = new oracle.jbo.domain.Number(Long.valueOf(idOperacion));
            } catch (Exception e) {
              logger.log(ADFLogger.ERROR, "Error al inicializar el valor de operacionId: " + e.getMessage());
            }
        }

        nl = xmlPayload.getElementsByTagName(CODIGO_TAREA);
        if(nl != null && (nl.getLength()>0)) {
            idTarea=nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(CODIGO_ROL);
        if(nl != null && (nl.getLength()>0)) {
            idRol=nl.item(0).getTextContent();
        }
        /* Se  comenta el valor Evento obtenido del payload en caso de ser utilizado nuevamente */
//        nl = xmlPayload.getElementsByTagName("evento");
//        if(nl != null && (nl.getLength() > 0) && (nl.item(0).getTextContent().trim().length() > 0)) {
//            idEvento = Long.valueOf(nl.item(0).getTextContent().trim());
//        }
        /* Se obtiene el valor agrupamiento del payload para sustituir al evento */
        nl = xmlPayload.getElementsByTagName("agrupamiento");
        if(nl != null && (nl.getLength() > 0) && (nl.item(0).getTextContent().trim().length() > 0)) {
            idAgrupador = Long.valueOf(nl.item(0).getTextContent().trim());
        }
        
        nl = xmlPayload.getElementsByTagName("requiereASJUR");
        if(nl != null && (nl.getLength() > 0)) {
            this.setEsRequiereAsjur(Boolean.valueOf(nl.item(0).getTextContent()));
        }
        
        nl = xmlPayload.getElementsByTagName("requiereSEPRI");
        if(nl != null && (nl.getLength() > 0)) {
            this.setEsRequiereSepri(Boolean.valueOf(nl.item(0).getTextContent()));
        }
        
        nl = xmlPayload.getElementsByTagName("requiereAED");
        if(nl != null && (nl.getLength() > 0)) {
            this.setEsRequiereAed(Boolean.valueOf(nl.item(0).getTextContent()));
        }
        
        nl = xmlPayload.getElementsByTagName("requierePCT");
        if(nl != null && (nl.getLength() > 0)) {
            this.setEsRequierePct(Boolean.valueOf(nl.item(0).getTextContent()));
        }
        
        nl = xmlPayload.getElementsByTagName("requiereSupervision");
        if(nl != null && (nl.getLength() > 0)) {
            this.setEsRequiereSupervision(Boolean.valueOf(nl.item(0).getTextContent()));
        }
        
        nl = xmlPayload.getElementsByTagName("requiereCOFI");
        if(nl != null && (nl.getLength() > 0)) {
            this.setEsRequiereCofi(Boolean.valueOf(nl.item(0).getTextContent()));
        }
        
        nl = xmlPayload.getElementsByTagName("requiereDAECI");
        if(nl != null && (nl.getLength() > 0)) {
            this.setEsRequiereAnalistaDaeci(Boolean.valueOf(nl.item(0).getTextContent()));
        }
        
        nl = xmlPayload.getElementsByTagName("requiereOFIC");
        if(nl != null && (nl.getLength() > 0)) {
            this.setEsRequiereAnalistaOfic(Boolean.valueOf(nl.item(0).getTextContent()));
        }
        
        nl = xmlPayload.getElementsByTagName("requiereAnalistaFINAM");
        if(nl != null && (nl.getLength() > 0)) {
            this.setEsRequiereAnalistaFinam(Boolean.valueOf(nl.item(0).getTextContent()));
        }
        
        nl = xmlPayload.getElementsByTagName("requiereAmbiental");
        if(nl != null && (nl.getLength() > 0)) {
            this.setEsRequiereEspAmbiental(Boolean.valueOf(nl.item(0).getTextContent()));
        }
        
        nl = xmlPayload.getElementsByTagName("requiereEjecutivoFINAM");
        if(nl != null && (nl.getLength() > 0)) {
            this.setEsRequiereEjecutivoFinam(Boolean.valueOf(nl.item(0).getTextContent()));
        }
        
        nl = xmlPayload.getElementsByTagName("requiereSegCred");
        if(nl != null && (nl.getLength() > 0)) {
            this.setEsRequiereSegCred(Boolean.valueOf(nl.item(0).getTextContent()));
        }
        
        /***** Si algún flag de más información está prendido, se trata de un retorno - START *****/
        nl = xmlPayload.getElementsByTagName("requiereMasInfoASJUR");
        if(nl != null && (nl.getLength() > 0)) {
            
            if(Boolean.valueOf(nl.item(0).getTextContent())) {this.setEsRetornoCumplirCondiciones(Boolean.TRUE);}
        }
        
        nl = xmlPayload.getElementsByTagName("requiereMasInfoSEPRI");
        if(nl != null && (nl.getLength() > 0)) {
            
            if(Boolean.valueOf(nl.item(0).getTextContent())) {this.setEsRetornoCumplirCondiciones(Boolean.TRUE);}
        }
        
        nl = xmlPayload.getElementsByTagName("requiereMasInfoAED");
        if(nl != null && (nl.getLength() > 0)) {
            
            if(Boolean.valueOf(nl.item(0).getTextContent())) {this.setEsRetornoCumplirCondiciones(Boolean.TRUE);}
        }
        
        nl = xmlPayload.getElementsByTagName("requiereMasInfoPCT");
        if(nl != null && (nl.getLength() > 0)) {
            
            if(Boolean.valueOf(nl.item(0).getTextContent())) {this.setEsRetornoCumplirCondiciones(Boolean.TRUE);}
        }
        
        nl = xmlPayload.getElementsByTagName("requiereMasInfoSupervision");
        if(nl != null && (nl.getLength() > 0)) {
            
            if(Boolean.valueOf(nl.item(0).getTextContent())) {this.setEsRetornoCumplirCondiciones(Boolean.TRUE);}
        }
        
        nl = xmlPayload.getElementsByTagName("requiereMasInfoCOFI");
        if(nl != null && (nl.getLength() > 0)) {
            
            if(Boolean.valueOf(nl.item(0).getTextContent())) {this.setEsRetornoCumplirCondiciones(Boolean.TRUE);}
        }
        
        nl = xmlPayload.getElementsByTagName("requiereMasInfoDAECI");
        if(nl != null && (nl.getLength() > 0)) {
            
            if(Boolean.valueOf(nl.item(0).getTextContent())) {this.setEsRetornoCumplirCondiciones(Boolean.TRUE);}
        }
        
        nl = xmlPayload.getElementsByTagName("requiereMasInfoOFIC");
        if(nl != null && (nl.getLength() > 0)) {
            
            if(Boolean.valueOf(nl.item(0).getTextContent())) {this.setEsRetornoCumplirCondiciones(Boolean.TRUE);}
        }
        
        nl = xmlPayload.getElementsByTagName("requiereMasInfoAnalistaFINAM");
        if(nl != null && (nl.getLength() > 0)) {
            
            if(Boolean.valueOf(nl.item(0).getTextContent())) {this.setEsRetornoCumplirCondiciones(Boolean.TRUE);}
        }
        
        nl = xmlPayload.getElementsByTagName("requiereMasInfoAmbiental");
        if(nl != null && (nl.getLength() > 0)) {
            
            if(Boolean.valueOf(nl.item(0).getTextContent())) {this.setEsRetornoCumplirCondiciones(Boolean.TRUE);}
        }
        
        nl = xmlPayload.getElementsByTagName("requiereMasInfoEjecutivoFINAM");
        if(nl != null && (nl.getLength() > 0)) {
            
            if(Boolean.valueOf(nl.item(0).getTextContent())) {this.setEsRetornoCumplirCondiciones(Boolean.TRUE);}
        }
        
        nl = xmlPayload.getElementsByTagName("requiereMasInfoSegCred");
        if(nl != null && (nl.getLength() > 0)) {
            
            if(Boolean.valueOf(nl.item(0).getTextContent())) {this.setEsRetornoCumplirCondiciones(Boolean.TRUE);}
        }
        
        
        obtenerInstanciaProceso();
        
        
        /***** Si algún flag de más información está prendido, se trata de un retorno - END *****/

        logger.warning("idOperacion :" + idOperacion);
        logger.warning("codigoTarea :" + idTarea);
        logger.warning("idRol :" + idRol);
        logger.warning("idAgrupador :" + idAgrupador);
        logger.warning("esRetornoCumplirCondiciones :" + esRetornoCumplirCondiciones);
        logger.warning("instanciaProceso: " + instanciaProceso);
    }
    
    public void obtenerInstanciaProceso(){
        logger.warning("Dentro de obtenerInstanciaProceso");        
        String instancia = null;
        try{
            if(BPMUtils.getCurrentTask() != null &&
                BPMUtils.getCurrentTask().getProcessInfo() != null &&
                    BPMUtils.getCurrentTask().getProcessInfo().getInstanceId() != null){
                instancia = BPMUtils.getCurrentTask().getProcessInfo().getInstanceId();
            } 
        }catch(Exception e){
            logger.severe("Error al obtener la Instancia del Proceso", e);
        }
                
        if(instancia != null){
            setInstanciaProceso(instancia);
        }
        logger.warning("instanciaProceso :"+instanciaProceso);
        logger.warning("Fuera de obtenerInstanciaProceso");      
    }
    
    public void evaluarParametroPStateBpm()  {
            logger.warning("Dentro de evaluarParametroPStateBpm State");
            
            String state =  (null != ADFUtils.getBoundAttributeValue("state")) 
                        ? (String)ADFUtils.getBoundAttributeValue("state"): null; 
            logger.warning("Var State: "+state);
            
            try {
                if (state != null) {
                    logger.warning("state :" + state);                
                    setEsEstadoCompletado(state.equalsIgnoreCase("COMPLETED") ? Boolean.TRUE : Boolean.FALSE);
                }else{
                    logger.warning("pState es nulo");
                    setEsEstadoCompletado(Boolean.FALSE);
                }
            } catch (Exception ex) {
                logger.warning("Error al recuperar NodeList state :",ex);
                logger.warning("pState no obtenido");
            }

            logger.warning("esEstadoCompletado : " + getEsEstadoCompletado());
            logger.warning("Fuera de evaluarParametroPStateBpm");        
        }
            
    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }
    
    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    public void setIdOperacion(String idOperacion)
    {
        this.idOperacion = idOperacion;
    }
    
    public String getIdOperacion()
    {
        return idOperacion;
    }
    
    public oracle.jbo.domain.Number getOperacionId() {
        return operacionId;
    }
    
    public void setIdTarea(String codigoTarea)
    {
        this.idTarea = codigoTarea;
    }
    
    public String getIdTarea()
    {
        return idTarea;
    }
    
    public void setObservacionValidarCondicion(String observacionValidarCondicion) {
        this.observacionValidarCondicion = observacionValidarCondicion;
    }

    public String getObservacionValidarCondicion() {
        return observacionValidarCondicion;
    }
    
    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getIdRol() {
        return idRol;
    }
    
    public void setEsRequiereAsjur(Boolean esRequiereAsjur) {
        this.esRequiereAsjur = esRequiereAsjur;
    }

    public Boolean getEsRequiereAsjur() {
        return esRequiereAsjur;
    }

    public void setEsRequiereSepri(Boolean esRequiereSepri) {
        this.esRequiereSepri = esRequiereSepri;
    }

    public Boolean getEsRequiereSepri() {
        return esRequiereSepri;
    }

    public void setEsRequiereAed(Boolean esRequiereAed) {
        this.esRequiereAed = esRequiereAed;
    }

    public Boolean getEsRequiereAed() {
        return esRequiereAed;
    }

    public void setEsRequierePct(Boolean esRequierePct) {
        this.esRequierePct = esRequierePct;
    }

    public Boolean getEsRequierePct() {
        return esRequierePct;
    }

    public void setEsRequiereSupervision(Boolean esRequiereSupervision) {
        this.esRequiereSupervision = esRequiereSupervision;
    }

    public Boolean getEsRequiereSupervision() {
        return esRequiereSupervision;
    }

    public void setEsRequiereCofi(Boolean esRequiereCofi) {
        this.esRequiereCofi = esRequiereCofi;
    }

    public Boolean getEsRequiereCofi() {
        return esRequiereCofi;
    }
    
    public void setEsRequiereAnalistaDaeci(Boolean esRequiereAnalistaDaeci) {
        this.esRequiereAnalistaDaeci = esRequiereAnalistaDaeci;
    }

    public Boolean getEsRequiereAnalistaDaeci() {
        return esRequiereAnalistaDaeci;
    }

    public void setEsRequiereAnalistaOfic(Boolean esRequiereAnalistaOfic) {
        this.esRequiereAnalistaOfic = esRequiereAnalistaOfic;
    }

    public Boolean getEsRequiereAnalistaOfic() {
        return esRequiereAnalistaOfic;
    }

    public void setEsRequiereAnalistaFinam(Boolean esRequiereAnalistaFinam) {
        this.esRequiereAnalistaFinam = esRequiereAnalistaFinam;
    }

    public Boolean getEsRequiereAnalistaFinam() {
        return esRequiereAnalistaFinam;
    }

    public void setEsRequiereEspAmbiental(Boolean esRequiereEspAmbiental) {
        this.esRequiereEspAmbiental = esRequiereEspAmbiental;
    }

    public Boolean getEsRequiereEspAmbiental() {
        return esRequiereEspAmbiental;
    }
     
     /*   Se comentan los accesors de idEvento
    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public Long getIdEvento() {
        return idEvento;
    }*/
    
    public void setEsRetornoCumplirCondiciones(Boolean esRetornoCumplirCondiciones) {
        this.esRetornoCumplirCondiciones = esRetornoCumplirCondiciones;
    }

    public Boolean getEsRetornoCumplirCondiciones() {
        return esRetornoCumplirCondiciones;
    }
    
    public void setIdAgrupador(Long idAgrupador) {
        this.idAgrupador = idAgrupador;
    }

    public Long getIdAgrupador() {
        return idAgrupador;
    }

    public void consultarCondicionesByIdOperacionIdRolIdEvento() {
        logger.log(ADFLogger.WARNING, "INTO consultarCondicionesByIdOperacionIdRolIdEvento.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        DCIteratorBinding voCondicionesOperacion = null;
        ViewObject operacionResultadoVO = null;
        Row rowCondicion = null;
        Long idOperacion = null;
        Integer idRol = null;
        Long idAgrupador = null;
        Row[] condiciones = null;


        try {
            logger.log(ADFLogger.WARNING, "INTO try");

            operationBinding = bindings.getOperationBinding("consultarCondicionesByIdOperacionIdRolIdEvento");
            operationBinding.execute();
            logger.log(ADFLogger.WARNING, "El metodo consultarCondicionesByIdOperacionIdRolIdEvento se ejecuto correctamente");
            if(null != bindings.getOperationBinding("obtenerCondiciones")){
            OperationBinding operationBinding1 = bindings.getOperationBinding("obtenerCondiciones");
            operationBinding1.execute();
            logger.log(ADFLogger.WARNING, "El metodo obtenerCondiciones se ejecuto correctamente.");
            if(null != operationBinding1.getResult()){
                condiciones = (Row[])operationBinding1.getResult();
            }else{
                logger.log(ADFLogger.WARNING, "Arreglo de rows");
            }
            }else{
                logger.log(ADFLogger.WARNING, "El metodo es nulo");
            }
//            voCondicionesOperacion =
//                ADFUtils.getDCBindingContainer().findIteratorBinding("obtenerCondiciones");
//            operacionResultadoVO = voCondicionesOperacion.getViewObject();
            logger.log(ADFLogger.WARNING, "Numero de rows." + condiciones.length);
            if (condiciones.length > 1) {
                logger.log(ADFLogger.WARNING, "INTO if.");
                validaNumeroCondicion = Boolean.TRUE;
                numeroCondicion = new Long(condiciones.length);
                logger.log(ADFLogger.WARNING, "Numero de rows de condiciones :" + numeroCondicion);
            } else {
                logger.log(ADFLogger.WARNING, "El numero de rows es menor que uno");
            }
            
        } catch (Exception e) {
            logger.warning("Error en consultarCondicionesByIdOperacionIdRolIdEvento.", e);
        }
        
        try{
            operationBinding = null;
            Boolean condicionValida = Boolean.FALSE;
            Integer contador = 0;
            Long idCondicion = null;
            
            //Se obtiene el codigo de usuario de la sesion actual 17sep2019
            String userId = ADFContext.getCurrent().getSecurityContext().getUserName().toLowerCase(); 
            logger.log(ADFLogger.WARNING, "userLogin consultarCondicionesByIdOperacionIdRolIdEvento: "+userId);
            if(condiciones.length > 0){
                for(Row row : condiciones){
                    if(null != row.getAttribute("IdCondicion")){
                        idCondicion = (Long)row.getAttribute("IdCondicion");
                        logger.log(ADFLogger.WARNING, "Valor de la condicion :::" + idCondicion);
                        
                        operationBinding = bindings.getOperationBinding("existeValidadorCondicion");
                        operationBinding.getParamsMap().put("idCondicion", idCondicion);
                        operationBinding.getParamsMap().put("idRolBpm", getIdRol());
                        operationBinding.getParamsMap().put("loginUsuario", userId);
                        operationBinding.getParamsMap().put("idAgrupador", getIdAgrupador());
                        operationBinding.execute();
                        
                        condicionValida = (Boolean)operationBinding.getResult();
                        if(condicionValida){
                            logger.log(ADFLogger.WARNING, "Condicion validada.." + idCondicion);
                        }else{
                            logger.log(ADFLogger.WARNING, "Condicion sin validar" + idCondicion);
                            contador++;
                        }
                    }
                }
                if(contador > 0){
                    logger.log(ADFLogger.WARNING, "Faltan condicones por validar" + contador);
                    setCondicionValidada(Boolean.FALSE);
                }else{
                    logger.log(ADFLogger.WARNING, "Las condicones han sido validadas" + contador);
                    setCondicionValidada(Boolean.TRUE);
                }
                logger.log("Error al consultar las condciones validadas.");
            }
            else{
                logger.log("No se encontraron registros de condiciones");
            }
        }catch(Exception e){
            logger.warning("Error al consultar las condiciones validadas.", e);
        }
        consultarCondicionesEnModelo();
    }
    
    public void consultarCondicionesEnModelo(){
        logger.warning("Entra en consultarCondicionesEnModelo.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Row[] condiciones = null;
        try{
            operationBinding = bindings.getOperationBinding("obtenerCondiciones");
            operationBinding.execute();
            logger.log(ADFLogger.WARNING, "El metodo obtenerCondiciones se ejecuto correctamente.");
            if(null != operationBinding.getResult()){
                condiciones = (Row[])operationBinding.getResult();
                logger.warning("Numero de rows encontrados en el modelo." + condiciones.length);
            }else{
                logger.warning("Arreglo de rows es nulo.");
            } 
        }catch(Exception e){
            logger.warning("Error en consultarCondicionesEnModelo.", e);
        }
    }
    
    public void setNumeroCondicion(Long numeroCondicion) {
        this.numeroCondicion = numeroCondicion;
    }

    public Long getNumeroCondicion() {
        return numeroCondicion;
    }
    
    public void setValidaNumeroCondicion(Boolean validaNumeroCondicion) {
        this.validaNumeroCondicion = validaNumeroCondicion;
    }

    public Boolean getValidaNumeroCondicion() {
        return validaNumeroCondicion;
    }
    
    public void setMensajeValidar(String mensajeValidar) {
        this.mensajeValidar = mensajeValidar;
    }

    public String getMensajeValidar() {
        return mensajeValidar;
    }


    public void setCondicionValidada(Boolean condicionValidada) {
        this.condicionValidada = condicionValidada;
    }

    public Boolean getCondicionValidada() {
        return condicionValidada;
    }

    public void setIdCondicionSeleccionada(Integer idCondicionSeleccionada) {
        this.idCondicionSeleccionada = idCondicionSeleccionada;
    }

    public Integer getIdCondicionSeleccionada() {
        return idCondicionSeleccionada;
    }


    public void setEsRequiereEjecutivoFinam(Boolean esRequiereEjecutivoFinam) {
        this.esRequiereEjecutivoFinam = esRequiereEjecutivoFinam;
    }

    public Boolean getEsRequiereEjecutivoFinam() {
        return esRequiereEjecutivoFinam;
    }


    public void setEsRequiereSegCred(Boolean esRequiereSegCred) {
        this.esRequiereSegCred = esRequiereSegCred;
    }

    public Boolean getEsRequiereSegCred() {
        return esRequiereSegCred;
    }
    
    public Boolean getEsEstadoCompletado() {
            return esEstadoCompletado;
        }
        
    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
            this.esEstadoCompletado = esEstadoCompletado;
        }
}
