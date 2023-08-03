
package pa09adquisicionght.bean;

import java.io.IOException;
import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.sql.TIMESTAMP;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;
import org.w3c.dom.NodeList;

public class AdquisicionBean extends FenixPanelBean implements Serializable{

    @SuppressWarnings("compatibility:-4227689755034822546")

    public static ADFLogger logger = null;
    private static final long serialVersionUID = 1L;
    
    private static final String CODIGO_TAREA = "CodigoTarea";
    private static final String CODIGO_OPERACION = "CodigoOperacion";
    private static final String INSTANCIA_TAREA = "InstanciaProceso";
    private static final String NUM_SERIE_DOCUMENTO="IdFlujo";
    private static final String CODIGO_NO_OBJECION="idNoObjecion";
    private static final String NUM_ADQUISICION="idAdquisicion";

    private String idTarea = "";
    private String idOperacion = "";
    private String instanciaTarea = "";
    private oracle.jbo.domain.Number numeroSerieDocumento;
    private String codigoNoObjecion;

    private String codigoAdquisicion;
    private Long idNoObjecion;
    private Long numAdquisicion;
    private Boolean heRevisado;
    private Boolean otorgaObjecion;
    private TIMESTAMP fechaFirma;
    private String numeroCorrespondencia;

    private Boolean disableBotones = Boolean.FALSE;
    private Boolean disableFormulario = Boolean.TRUE;
    
    private long startTime;
    private long endTime;
    
    private String instanciaProceso;
    
    public void setCodigoNoObjecion(String codigoNoObjecion) {
        this.codigoNoObjecion = codigoNoObjecion;
    }

    public String getCodigoNoObjecion() {
        return codigoNoObjecion;
    }

    public void setCodigoAdquisicion(String codigoAdquisicion) {
        this.codigoAdquisicion = codigoAdquisicion;
    }

    public String getCodigoAdquisicion() {
        return codigoAdquisicion;
    }

    public void setIdNoObjecion(Long idNoObjecion) {
        this.idNoObjecion = idNoObjecion;
    }

    public Long getIdNoObjecion() {
        return idNoObjecion;
    }

    public void setNumAdquisicion(Long numAdquisicion) {
        this.numAdquisicion = numAdquisicion;
    }

    public Long getNumAdquisicion() {
        return numAdquisicion;
    }
    
    public void setDisableBotones(Boolean disableBotones) {
        this.disableBotones = disableBotones;
    }

    public Boolean getDisableBotones() {
        return disableBotones;
    }

    public void setDisableFormulario(Boolean disableFormulario) {
        this.disableFormulario = disableFormulario;
    }

    public Boolean getDisableFormulario() {
        return disableFormulario;
    }

    public AdquisicionBean() {
        super();
        if (logger == null)
        {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    
    public void getPayloadInformation() throws WorkflowException, IOException {
        startTime = System.currentTimeMillis();
        logger.warning("Tiempo de inicio respuesta del: "
         + startTime + " segundos");
        
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();
        java.io.StringWriter writer = new java.io.StringWriter();
        xmlPayload.print(writer);
        String payloadAsString = writer.toString();
        logger.warning("BPM Payload " + payloadAsString);
        
        NodeList nl;

        nl = xmlPayload.getElementsByTagName(CODIGO_TAREA);
        if (nl.getLength() > 0) {
            idTarea = nl.item(0).getTextContent();
        }

        nl = xmlPayload.getElementsByTagName(CODIGO_OPERACION);
        if (nl.getLength() > 0) {
            idOperacion = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(INSTANCIA_TAREA);
        if (nl.getLength() > 0) {
            instanciaTarea = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(NUM_SERIE_DOCUMENTO);
        if (nl.getLength() > 0){
            try {
                setNumeroSerieDocumento(new oracle.jbo.domain.Number(nl.item(0).getTextContent()));
            } catch (Exception e) {
                logger.log(ADFLogger.ERROR, "Error al inicializar el valor de IdFlujo: " + e.getMessage());
            }
        }
        
        nl = xmlPayload.getElementsByTagName(CODIGO_NO_OBJECION);
        if(nl.getLength() > 0){
            codigoNoObjecion = nl.item(0).getTextContent();
            
            if((codigoNoObjecion != null) && (codigoNoObjecion.trim().length() > 0)){
                setIdNoObjecion(Long.parseLong(codigoNoObjecion));
            } else {
                logger.log(ADFLogger.WARNING, "idNoObjecion no se recupero , valor es nulo.");
            }
        }
        
        nl = xmlPayload.getElementsByTagName(NUM_ADQUISICION);
        if(nl.getLength() > 0){
            codigoAdquisicion = nl.item(0).getTextContent();
            
            if((codigoAdquisicion != null) && (codigoAdquisicion.trim().length() > 0)){
                setNumAdquisicion(Long.parseLong(codigoAdquisicion));
            } else {
                logger.log(ADFLogger.WARNING, "numAdquisicion no se recupero , valor es nulo.");
            }
        }
        
        obtenerInstanciaProceso();
        
        
        logger.warning(CODIGO_TAREA + idTarea);
        logger.warning(CODIGO_OPERACION + idOperacion);
        logger.warning(NUM_SERIE_DOCUMENTO + numeroSerieDocumento);
        logger.warning(CODIGO_NO_OBJECION + idNoObjecion);
        logger.warning(NUM_ADQUISICION + numAdquisicion);
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
            
    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }
    
    public String getInstanciaProceso() {
        return instanciaProceso;
    }
    
    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }

    public String getIdTarea() {
        return idTarea;
    }

    public void setIdOperacion(String idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getIdOperacion() {
        return idOperacion;
    }

    public void setInstanciaTarea(String instanciaTarea) {
        this.instanciaTarea = instanciaTarea;
    }

    public String getInstanciaTarea() {
        return instanciaTarea;
    }
    
    public void setNumeroSerieDocumento(oracle.jbo.domain.Number numeroSerieDocumento) {
        this.numeroSerieDocumento = numeroSerieDocumento;
    }

    public oracle.jbo.domain.Number getNumeroSerieDocumento() {
        return numeroSerieDocumento;
    }

    public Boolean getHeRevisado() {
        return heRevisado;
    }

    public void setHeRevisado(Boolean heRevisado) {
        this.heRevisado = heRevisado;
    }

    public Boolean getOtorgaObjecion() {
        return otorgaObjecion;
    }

    public void setOtorgaObjecion(Boolean otorgaObjecion) {
        this.otorgaObjecion = otorgaObjecion;
    }

    public TIMESTAMP getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(TIMESTAMP fechaFirma) {
        this.fechaFirma = fechaFirma;
    }
    
    public void precargaEmitirRespuestaCliente() {
        logger.warning("Ingresa a precargaEmitirRespuestaCliente");
        
        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = binding.getOperationBinding("setvarIdAdquisicion");
            operationBinding.getParamsMap().put("value", numAdquisicion);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding devuelve errores metodo setvarIdAdquisicion");
            } else {
                logger.warning("***Carga IdAdquisicion Listo, redireccionando... ");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en setvarIdAdquisicion " + e.getClass() + ":" + e.getMessage());
        }

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = binding.getOperationBinding("obtenerRowNoObjecion");
            operationBinding.getParamsMap().put("idNoObjecion", idNoObjecion);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding devuelve errores metodo obtenerRowNoObjecion");
            } else {
                logger.warning("***Carga IdNoObjecion Listo, redireccionando... ");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en obtenerRowNoObjecion " + e.getClass() + ":" + e.getMessage());
        }
        
        logger.warning("Finaliza precargaEmitirRespuestaCliente");
    }
    
    public String getNumeroCorrespondencia() {
        return numeroCorrespondencia;
    }

    public void setNumeroCorrespondencia(String numeroCorrespondencia) {
        this.numeroCorrespondencia = numeroCorrespondencia;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
