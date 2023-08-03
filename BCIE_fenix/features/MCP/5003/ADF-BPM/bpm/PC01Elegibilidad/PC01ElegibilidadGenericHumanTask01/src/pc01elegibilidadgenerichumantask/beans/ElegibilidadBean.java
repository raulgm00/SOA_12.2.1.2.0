package pc01elegibilidadgenerichumantask.beans;

import java.io.IOException;
import java.io.Serializable;

import java.math.BigDecimal;

import oracle.adf.share.logging.ADFLogger;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

public class ElegibilidadBean extends FenixPanelBean implements Serializable 
{
    @SuppressWarnings("compatibility:8935141361672817038")
    private static final long serialVersionUID = 1L;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(ElegibilidadBean.class);
    
    private String idOperacion;
    private String codigoTarea;
    //variable producto, parametro para herramienta estrategica
    private String codigoProducto;
    //idCliente
    private String codigoCliente;
    //montoSolicitado
    private BigDecimal montoSolicitado;
    //unidadEjecutora
    private String unidadEjecutora;
    
    private String instanciaProceso;
    
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
        
        nl = xmlPayload.getElementsByTagName("CodigoProducto");
        if(nl.getLength()>0)
        {   
            codigoProducto = nl.item(0).getTextContent();
        }
        
        // Recuperar idCliente del Payload
        nl = xmlPayload.getElementsByTagName("CodigoCliente");
        if(nl.getLength()>0)
        {
            codigoCliente = nl.item(0).getTextContent();
        }
        
        // Recuperar montoSolicitado del Payload
        nl = xmlPayload.getElementsByTagName("MontoSolicitado");
        if(nl.getLength()>0 && nl.item(0).getTextContent().length()>0)
        {
            montoSolicitado = new BigDecimal(nl.item(0).getTextContent());
        }
        
        // Recuperar unidadEjecutora del Payload
        nl = xmlPayload.getElementsByTagName("unidadEjecutora");
        if(nl.getLength()>0)
        {
            unidadEjecutora = nl.item(0).getTextContent();
        }
        
        obtenerInstanciaProceso();
      
        LOGGER.finer("codigoTarea",codigoTarea);
        LOGGER.finer("idOperacion",idOperacion);
        LOGGER.finer("idProducto",codigoProducto);
        LOGGER.finer("idCliente",codigoCliente);
        LOGGER.finer("montoSolicitado",montoSolicitado);
        LOGGER.finer("unidadEjecutora",unidadEjecutora);
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

  public void setCodigoProducto(String codigoProducto)
  {
    this.codigoProducto = codigoProducto;
  }

  public String getCodigoProducto()
  {
    return codigoProducto;
  }
  

  public ElegibilidadBean() {
          super();
      }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }


    public BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(BigDecimal montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }


    public String getUnidadEjecutora() {
        return unidadEjecutora;
    }

    public void setUnidadEjecutora(String unidadEjecutora) {
        this.unidadEjecutora = unidadEjecutora;
    }
}
