package pa02admoncomisionesght.beans;

import java.io.IOException;
import java.io.Serializable;

import oracle.adf.model.BindingContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

public class ComisionesBean  extends FenixPanelBean  implements Serializable
{
  @SuppressWarnings("compatibility:985025655589912927")
  private static final long serialVersionUID = 1L;

  public static final ADFLogger LOGGER = ADFLogger.createADFLogger(ComisionesBean.class);

  /**
   * Constates para manejo de Payload
   */
  private static final String CODIGO_OPERACION = "CodigoOperacion";
  private static final String CODIGO_TAREA = "CodigoTarea";
  private static final String CODIGO_COMISION="idComision";
  private String idOperacion;
  private String codigoTarea;
  private Long idComision;
  private String botonSalida;
  private Boolean avisoCobro;
  private String colorEstado;
  private String mensajeConfirmacion;
  private String instanciaProceso;
  private Boolean esEstadoCompletado = Boolean.FALSE;//Variable Estado BPM
    
  public ComisionesBean()
  {
    super();
  }

  public void getPayloadInformation()
    throws WorkflowException, IOException
  {
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
    if (nl.getLength() > 0)
    {
      codigoTarea = nl.item(0).getTextContent();
    }

    nl = xmlPayload.getElementsByTagName(CODIGO_OPERACION);
    if (nl.getLength() > 0)
    {
      idOperacion = nl.item(0).getTextContent();
    }
      nl = xmlPayload.getElementsByTagName(CODIGO_COMISION);
      if (nl.getLength() > 0 && null!=nl.item(0).getTextContent() && !nl.item(0).getTextContent().equalsIgnoreCase(""))
      {
        idComision = Long.parseLong(nl.item(0).getTextContent());
      }

      botonSalida="Finalizar tarea";
      mensajeConfirmacion="¿Confirma finalizar tarea?";
      colorEstado="Orange";
      
      obtenerInstanciaProceso();


    LOGGER.warning("codigoTarea :" + codigoTarea);
    LOGGER.warning("idOperacion :" + idOperacion);
    LOGGER.warning("idComision :" + idComision);
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

  public void setCodigoTarea(String codigoTarea)
  {
    this.codigoTarea = codigoTarea;
  }

  public String getCodigoTarea()
  {
    return codigoTarea;
  }

    public Long getIdComision() {
        return idComision;
    }

    public void setIdComision(Long idComision) {
        this.idComision = idComision;
    }

    public String getBotonSalida() {
        return botonSalida;
    }

    public void setBotonSalida(String botonSalida) {
        this.botonSalida = botonSalida;
    }

    public Boolean getAvisoCobro() {
        return avisoCobro;
    }

    public void setAvisoCobro(Boolean avisoCobro) {
        this.avisoCobro = avisoCobro;
    }

    public String getColorEstado() {
        return colorEstado;
    }

    public void setColorEstado(String colorEstado) {
        this.colorEstado = colorEstado;
    }

    public String getMensajeConfirmacion() {
        return mensajeConfirmacion;
    }

    public void setMensajeConfirmacion(String mensajeConfirmacion) {
        this.mensajeConfirmacion = mensajeConfirmacion;
    }
    
    public Boolean getEsEstadoCompletado() {
            return esEstadoCompletado;
        }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
            this.esEstadoCompletado = esEstadoCompletado;
        }
        
    public void consultarComision() {
        LOGGER.warning("Into consultarComision");
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        ComisionesBean comisionesBean = (ComisionesBean) 
            JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");
        LOGGER.warning("idOperacion:"+comisionesBean.getIdOperacion());
        LOGGER.warning("idComision:"+comisionesBean.getIdComision());
        try{
            operationBinding = bindings.getOperationBinding("obtenerComision");
            operationBinding.getParamsMap().put("idOperacion", comisionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("idComision", comisionesBean.getIdComision());
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                LOGGER.warning("obtenerComision with errors"); 
            }else{
                LOGGER.warning("obtenerComision execute successfully");
            }
        }catch(Exception e){
            LOGGER.warning("Error in consultarComision :"+e);
        }
        LOGGER.warning("Leave consultarComision");
    }
    
    
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
}
