package pa17leccionesaprendidasght.beans;

import java.io.IOException;
import java.io.Serializable;

import java.math.BigDecimal;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.vo.ConfigVisibilidadVORowImpl;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

public class LeccionAprendidaBean extends FenixPanelBean implements Serializable {
    @SuppressWarnings("compatibility:8935141361672817038")
    private static final long serialVersionUID = 1L;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(LeccionAprendidaBean.class);
    
    
    private Boolean esEstadoCompletado = Boolean.FALSE;//Variable Estado BPM
    
    private String idOperacion;
    private String codigoTarea;
    private String idLeccionAprendida;
    //idCliente
    private String codigoCliente;
    private String instanciaProceso;
    
    public LeccionAprendidaBean() {
        super();
    }
    
    public void getPayloadInformation() throws WorkflowException, IOException
    {          
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();
        
        if(LOGGER.isWarning())// if(LOGGER.isFiner())
        {
            //get the payload as a simple string, useful for debugging
            java.io.StringWriter writer = new java.io.StringWriter();
            xmlPayload.print(writer);
            String payloadAsString = writer.toString();
            LOGGER.log(ADFLogger.WARNING,payloadAsString);
        }
        NodeList nl;
        
        nl = xmlPayload.getElementsByTagName("CodigoTarea");
        if(nl.getLength()>0){
           codigoTarea = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName("CodigoOperacion");
        if(nl.getLength()>0){
           idOperacion = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName("idLeccionAprendida");
        if(nl.getLength()>0)
        {   
            idLeccionAprendida = nl.item(0).getTextContent();
        }
        
        // Recuperar idCliente del Payload
        nl = xmlPayload.getElementsByTagName("CodigoCliente");
        if(nl.getLength()>0)
        {
            codigoCliente = nl.item(0).getTextContent();
        }
        
        obtenerInstanciaProceso();
        
        LOGGER.warning("codigoTarea: " + codigoTarea);
        LOGGER.warning("idOperacion: " + idOperacion);
        LOGGER.warning("idLeccionAprendida: " + idLeccionAprendida);
        LOGGER.warning("idCliente: " + codigoCliente);
        LOGGER.warning("instanciaProceso: " + instanciaProceso);
        
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
    
    public void evaluarParametroPStateBpm()  {
    LOGGER.warning("Dentro de evaluarParametroPStateBpm State");
    
    String state =  (null != ADFUtils.getBoundAttributeValue("state")) 
                ? (String)ADFUtils.getBoundAttributeValue("state"): null; 
    LOGGER.warning("Var State: "+state);
    
    try {
        if (state != null) {
            LOGGER.warning("state :" + state);                
            setEsEstadoCompletado(state.equalsIgnoreCase("COMPLETED") ? Boolean.TRUE : Boolean.FALSE);
        }else{
            LOGGER.warning("pState es nulo");
            setEsEstadoCompletado(Boolean.FALSE);
        }
    } catch (Exception ex) {
        LOGGER.warning("Error al recuperar NodeList state :",ex);
        LOGGER.warning("pState no obtenido");
    }

    LOGGER.warning("esEstadoCompletado : " + getEsEstadoCompletado());
    LOGGER.warning("Fuera de evaluarParametroPStateBpm");        
    }
    
    public Boolean getEsEstadoCompletado() {
              return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
              this.esEstadoCompletado = esEstadoCompletado;
    }
    
    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }
    
    public String getInstanciaProceso() {
        return instanciaProceso;
    }
    
    public void printValue(String psObject) {
        System.out.println("Parameter: "  + psObject);
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

    public void setIdLeccionAprendida(String idLeccionAprendida)
    {
        this.idLeccionAprendida = idLeccionAprendida;
    }

    public String getIdLeccionAprendida()
    {
    return idLeccionAprendida;
    }
    
    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
}
