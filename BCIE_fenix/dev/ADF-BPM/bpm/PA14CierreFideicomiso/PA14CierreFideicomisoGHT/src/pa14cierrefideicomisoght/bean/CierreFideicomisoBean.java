package pa14cierrefideicomisoght.bean;

import java.io.IOException;
import oracle.bpel.services.workflow.WorkflowException;
import org.w3c.dom.NodeList;
import oracle.xml.parser.v2.XMLElement;
import java.io.StringWriter;

import java.util.HashMap;

import oracle.adf.model.BindingContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class CierreFideicomisoBean {
    
    ADFLogger LOGGER = ADFLogger.createADFLogger(CierreFideicomisoBean.class);
    
    private static final String CODIGO_TAREA = "CodigoTarea";
    private static final String CODIGO_OPERACION = "CodigoOperacion";
    
    private String codigoTarea;
    private String codigoOperacion;
    private String idOperacion;
    
    private oracle.jbo.domain.Number operacionId;
    
    private String justificacion;
    private String numeroCustodiaDocCierre;

    public static ADFLogger logger = null;
    
    private String instanciaProceso;

    public CierreFideicomisoBean() {
        super();
        if (logger == null){
            logger = ADFLogger.createADFLogger(CierreFideicomisoActionBean.class);
        }
    }

    public void getPayloadCierreFideicomiso() throws WorkflowException, IOException {
        
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();
        
        if (LOGGER.isWarning()) { //
            //get the payload as a simple string, useful for debugging
            java.io.StringWriter writer = new StringWriter();
            xmlPayload.print(writer);
            String payloadAsString = writer.toString();
            LOGGER.log(ADFLogger.WARNING, payloadAsString);
            
            NodeList nl;
            
            nl = xmlPayload.getElementsByTagName(CODIGO_TAREA);
            if (nl.getLength() > 0) {
                codigoTarea = nl.item(0).getTextContent();
            }
            
            nl = xmlPayload.getElementsByTagName(CODIGO_OPERACION);
            if(nl.getLength() > 0){
                idOperacion = nl.item(0).getTextContent();
                
                try {
                    if((idOperacion != null) && (idOperacion.trim().length() > 0))
                        operacionId = new oracle.jbo.domain.Number(Long.valueOf(idOperacion));
                } catch (Exception e) {
                    LOGGER.log(ADFLogger.ERROR, "Error al inicializar el valor de operacionId: " + e.getMessage());
                }
            }
            
            obtenerInstanciaProceso();
            
        }
    }
    
    public void obtenerInstanciaProceso(){
        LOGGER.warning("Dentro de obtenerInstanciaProceso");        
        String instancia = null;
        try{
            if(BPMUtils.getCurrentTask() != null &&
                BPMUtils.getCurrentTask().getProcessInfo() != null &&
                    BPMUtils.getCurrentTask().getProcessInfo().getInstanceId() != null){
                instancia = BPMUtils.getCurrentTask().getProcessInfo().getInstanceId();
            } 
        }catch(Exception e){
            LOGGER.severe("Error al obtener la Instancia del Proceso", e);
        }
                
        if(instancia != null){
            setInstanciaProceso(instancia);
        }
        LOGGER.warning("instanciaProceso :"+instanciaProceso);
        LOGGER.warning("Fuera de obtenerInstanciaProceso");      
    }
            
    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }
    
    public String getInstanciaProceso() {
        return instanciaProceso;
    }
    
    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public String getCodigoTarea() {
        return codigoTarea;
    }
    
    public void setIdOperacion(String idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getIdOperacion() {
        return idOperacion;
    }
    
    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getJustificacion() {
        return justificacion;
    }
    
    public void setNumeroCustodiaDocCierre(String numeroCustodiaDocCierre) {
        this.numeroCustodiaDocCierre = numeroCustodiaDocCierre;
    }

    public String getNumeroCustodiaDocCierre() {
        return numeroCustodiaDocCierre;
    }

    /**
    ???? * Se crea metodo iniciarJustificacion 
    ???? * @param 
    ???? * @since 08/08/2016
    ???? * @by José Hernández Cruz
    ???? */
    public void iniciarJustificacion() {
        // Add event code here...
        // Add event code here...
        logger.log(ADFLogger.WARNING, "Inside CierreFideicomisoBean obtenerJustificacion." );
        String justificar = null; 
        String numeroCustodia = null;
        HashMap<String, String> map = null;
        BindingContainer bindings = getBindings();
        try{
            if(this.getIdOperacion() != null && !this.getIdOperacion().equals("")){
        OperationBinding operationBinding = bindings.getOperationBinding("obtenerJustificacion");
        operationBinding.getParamsMap().put("idOperacion", this.getIdOperacion());
        operationBinding.execute();
        map =(HashMap<String, String>) operationBinding.getResult();
        justificar = map.get("justificacion");
        numeroCustodia = map.get("numeroCustodiaDocCierre");
        this.setJustificacion(justificar);
        this.setNumeroCustodiaDocCierre(numeroCustodia);
            }
            else{
                logger.log(ADFLogger.WARNING, "Error al obtener el Id de la operacion, valor nulo. " + this.getIdOperacion());
            }
        }catch(Exception ex){
            logger.log(ADFLogger.WARNING, "Error en obtenerJustificacion " + ex.getClass() + ":" + ex.getMessage());
            JSFUtils.addFacesErrorMessage("Error al guardar la Justificacion. Por favor intente más tarde.");          
        }
        
    }
    
    public BindingContainer getBindings(){
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
}
