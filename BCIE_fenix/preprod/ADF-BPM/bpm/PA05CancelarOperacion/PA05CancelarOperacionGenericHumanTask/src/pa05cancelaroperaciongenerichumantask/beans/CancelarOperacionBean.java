package pa05cancelaroperaciongenerichumantask.beans;

import java.io.IOException;
import java.io.Serializable;

import oracle.adf.model.BindingContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.jbo.domain.Number;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

public class CancelarOperacionBean extends FenixPanelBean implements Serializable {
    
    @SuppressWarnings("compatibility:985025655589912927")
    private static final long serialVersionUID = 1L;
    
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(CancelarOperacionBean.class);
    
    private static final String CODIGO_OPERACION = "CodigoOperacion";
    private static final String CODIGO_TAREA = "CodigoTarea";
    private static final String RAZON = "Razon";
    private static final String SOLICITA_CANCELAR = "solicitaCancelar";
    private static final String USUARIO = "ResponsableOperacion";
    
    private String idOperacion;
    private String idTarea;
    private String razon;
    private String usuario;
    private Boolean solicitaCancelar;
    private String usuarioVista;
    private String instanciaProceso;
    private Boolean esEstadoCompletado = Boolean.FALSE;//Variable Estado BPM
    
    public CancelarOperacionBean(){
        super();
    }
    
    public void getPayloadInformation() throws WorkflowException, IOException{
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();
        
        if (LOGGER.isWarning()) // if(LOGGER.isFiner())
        {
          //get the payload as a simple string, useful for debugging
          java.io.StringWriter writer = new java.io.StringWriter();
          xmlPayload.print(writer);
          String payloadAsString = writer.toString();
          LOGGER.log(ADFLogger.WARNING, payloadAsString);
        }
        NodeList nl;
        
        nl = xmlPayload.getElementsByTagName(CODIGO_TAREA);
        if (nl.getLength() > 0){
          idTarea = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(CODIGO_OPERACION);
        if (nl.getLength() > 0){
          idOperacion = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(RAZON);
        if(nl.getLength() > 0) {
            razon = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(USUARIO);
        if(nl.getLength() > 0) {
            usuario = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(SOLICITA_CANCELAR);
        if(nl.getLength() > 0) {
            solicitaCancelar = Boolean.parseBoolean(nl.item(0).getTextContent());
        } 
        
        obtenerInstanciaProceso();
        
        
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

    public void setIdOperacion(String idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getIdOperacion() {
        return idOperacion;
    }

    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }

    public String getIdTarea() {
        return idTarea;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getRazon() {
        return razon;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setSolicitaCancelar(Boolean solicitaCancelar) {
        this.solicitaCancelar = solicitaCancelar;
    }

    public Boolean getSolicitaCancelar() {
        return solicitaCancelar;
    }


    public void setUsuarioVista(String usuarioVista) {
        LOGGER.warning(usuarioVista);
        String usuarioMayus = null;
        if(null != usuarioVista){
            usuarioMayus = usuarioVista.substring(0, 1).toUpperCase() + usuarioVista.substring(1);
            LOGGER.warning(usuarioMayus);
        }
        this.usuarioVista = usuarioMayus;
    }

    public String getUsuarioVista() {
        return usuarioVista;
    }
    
    public void cargarPantallaCancelarSuspender(){
        LOGGER.warning("Entra a metodo cargarPantallaCancelarSuspender");
        String codigoOperacion = getIdOperacion();
        String responsableOperacion = getUsuario();
        
        setUsuarioVista(responsableOperacion);
        BindingContainer bindings = getBindings();
        try {
            LOGGER.warning("Ejecutando OperationBinding");
            Number idOperacionNumber = new Number(codigoOperacion);
            OperationBinding operationBinding = bindings.getOperationBinding("buscarOperacionPorId");
    
            operationBinding.getParamsMap().put("id", idOperacionNumber);
            operationBinding.execute();
            LOGGER.warning("OperationBinding ejecutado correctamente");
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en cargarPantallaCancelarSuspender " + e.getClass() + ":" + e.getMessage());
            JSFUtils.addFacesErrorMessage("Error al consultar los datos. Por favor intente más tarde.");
        }
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
    
    public BindingContainer getBindings()
    {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public Boolean getEsEstadoCompletado() {
            return esEstadoCompletado;
        }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
            this.esEstadoCompletado = esEstadoCompletado;
        }


}

