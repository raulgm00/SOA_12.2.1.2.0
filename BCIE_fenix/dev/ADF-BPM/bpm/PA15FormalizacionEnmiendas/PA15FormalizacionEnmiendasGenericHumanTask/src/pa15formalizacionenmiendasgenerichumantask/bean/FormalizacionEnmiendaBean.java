package pa15formalizacionenmiendasgenerichumantask.bean;

import java.io.IOException;
import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Map;
import java.util.logging.Level;

import oracle.adf.model.BindingContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

import pa15formalizacionenmiendasgenerichumantask.util.FormalizacionEnmiendaPayloadReader;
import pa15formalizacionenmiendasgenerichumantask.util.FormalizacionEnmiendaPayloadWriter;
import pa15formalizacionenmiendasgenerichumantask.util.PA15Constants;

public class FormalizacionEnmiendaBean extends FenixPanelBean implements Serializable {
    @SuppressWarnings("compatibility:8926499292625740174")
    private static final long serialVersionUID = 1L;
    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(FormalizacionEnmiendaBean.class);

    /** Código de la tarea en el sistema Fenix. Su valor proviene del Payload de la tarea.*/
    private int codigoTarea;

    /** Identificador de la operación. Su valor proviene del Payload de la tarea.*/
    private long idOperacion;

    /** Instancia en ejecución del proceso.*/
    private String instanciaProceso;

    /** Identificador de la enmienda. Su valor proviene del Payload de la tarea.*/
    private long idEnmienda;
    
    /** Identificador del producto.*/
    private Integer idProducto;
    
    /** Identifica el origen de la formalización de Enmiendas. Sus valores se encuentran en FenixConstants.*/
    private int origen;
    
    /** Num de custodia del contrato.*/
    private String numCustodia;
    
    /** Controla si ya se realizó la actualización de información de las líneas de crédito.*/
    private boolean infoLineaCreditoActualizada;
    
    /** Su valor proviene del Payload de la tarea.*/
    private boolean solicitudContratacion;

    /** Su valor proviene del Payload de la tarea.*/
    private boolean esDesobligacion;
    
    /** Su valor proviene del Payload de la tarea.*/
    private boolean esCambioPlazo;
    
    /** 
     * Mapa que contiene los montos a desobligar de las líneas de crédito que se almacenan en el payload. 
     * Key=ID Línea crédito, Value = Monto a Desobligar
     */
    private Map<Long, BigDecimal> mapMontosDesobligar;
    
    public FormalizacionEnmiendaBean() {
        super();
    }

    /**
     * Lee el Payload de la tarea para extraer valores de los atributos de este ManagedBean.
     */
    public void getPayloadInformation() throws WorkflowException {
        LOGGER.info("Inicia carga del payload: getPayloadInformation");
        
        String instance = BPMUtils.getCurrentTask().getProcessInfo().getInstanceId();
        this.setInstanciaProceso(instance);
        
        LOGGER.info("Instancia proceso: {0}", instance);
        
        FormalizacionEnmiendaPayloadReader reader = FormalizacionEnmiendaPayloadReader.readCurrentTaskPayload();

        this.setCodigoTarea(reader.getCodigoTarea());
        this.setIdOperacion(reader.getIdOperacion());
        this.setIdEnmienda(reader.getIdEnmienda());
        this.setIdProducto(reader.getIdProducto());
        this.setOrigen(reader.getOrigen());
        this.setSolicitudContratacion(reader.isSolicitudContratacion());
        this.setEsDesobligacion(reader.isDesobligacion());
        this.setEsCambioPlazo(reader.isCambioPlazo());
        this.setMapMontosDesobligar(reader.getMapMontosDesobligar());
        
        LOGGER.info("Finaliza carga del payload: getPayloadInformation");
    }


    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public long getIdOperacion() {
        return this.idOperacion;
    }
    
    public String getIdOperacionAsString() {
        return String.valueOf(this.idOperacion);
    }

    public void setCodigoTarea(int codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public int getCodigoTarea() {
        return this.codigoTarea;
    }
    
    public String getCodigoTareaAsString() {
        return String.valueOf(this.codigoTarea);
    }

    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }

    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    public void setIdEnmienda(Long idEnmienda) {
        this.idEnmienda = idEnmienda;
    }

    public long getIdEnmienda() {
        return idEnmienda;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getOrigen() {
        return origen;
    }

    public String getNumCustodia() {
        return numCustodia;
    }

    public void setNumCustodia(String numCustodia) {
        this.numCustodia = numCustodia;
    }

    public void setInfoLineaCreditoActualizada(boolean infoLineaCreditoActualizada) {
        this.infoLineaCreditoActualizada = infoLineaCreditoActualizada;
    }

    public boolean isInfoLineaCreditoActualizada() {
        return infoLineaCreditoActualizada;
    }

    public void setSolicitudContratacion(boolean solicitudContratacion) {
        this.solicitudContratacion = solicitudContratacion;
    }

    public boolean isSolicitudContratacion() {
        return solicitudContratacion;
    }

    public boolean isDesobligacion() {
        return esDesobligacion;
    }

    public void setEsDesobligacion(boolean esDesobligacion) {
        this.esDesobligacion = esDesobligacion;
    }

    public boolean isCambioPlazo() {
        return esCambioPlazo;
    }

    public void setEsCambioPlazo(boolean esCambioPlazo) {
        this.esCambioPlazo = esCambioPlazo;
    }

    public Map<Long, BigDecimal> getMapMontosDesobligar() {
        return mapMontosDesobligar;
    }

    public void setMapMontosDesobligar(Map<Long, BigDecimal> mapMontosDesobligar) {
        this.mapMontosDesobligar = mapMontosDesobligar;
    }

    /**
     * Se encarga de solicitar la actuaización de la instancia del proceso en la enmienda.
     * Quién hace la actualización es el código común, en este método solo s ehace la solicitud y se pasan los params necesarios.
     */
    public void actulizarEnmienda() {
        LOGGER.info("Dentro de actulizarEnmienda");
        LOGGER.info("idEnmienda: " + idEnmienda);
        LOGGER.info("instanciaProceso: " + instanciaProceso);

        if (-1 != idEnmienda && (null != instanciaProceso && !instanciaProceso.trim().isEmpty())) {
            LOGGER.info("Obteniendo operación que actualiza instancia proceso de la enmienda");

            BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding actualizarEnmiendaOp = bindings.getOperationBinding("actualizarInstanciaEnmienda");
            actualizarEnmiendaOp.getParamsMap().put("idEnmienda", idEnmienda);
            actualizarEnmiendaOp.getParamsMap().put("instanciaProceso", instanciaProceso);

            LOGGER.info("execute actualizarInstanciaEnmienda");
            Boolean result = (Boolean) actualizarEnmiendaOp.execute();

            if (!actualizarEnmiendaOp.getErrors().isEmpty()) {
                LOGGER.severe("Error al ejecutar actualizarInstanciaEnmienda");
            } else {
                LOGGER.info("Enmienda se actualizó correctamente. Valor retornado al actualizar = {0}", result);
            }
        }

        LOGGER.info("Finaliza actulizarEnmienda");
    }
    
    /**
     * Gestiona el evento lanzado por la región de líneas de crédito cuando se actualizan las líneas y entonces se deben
     * actualizar los montos a desobligar en el payload del proceso.
     * Se actualizan todos los montos independientemente de la línea que se haya actualizado.
     *
     * @param idLineaCredito ID de la línea de crédito que se actualizó.
     */
    public void handleUpdateLCEvent(Long idLineaCredito) {
        LOGGER.info("Dentro de handleUpdateLCEvent");
        
        if(idLineaCredito == null){
            LOGGER.warning("idLineaCredito es nulo. No se procesa este evento.");
            return;
        }
        
        LOGGER.info("Obteniendo monto a desobligar");
        BigDecimal montoDesobligar = this.getMapMontosDesobligar().get(idLineaCredito);

        LOGGER.info("Creando writer para modificar payload de la tarea");
        FormalizacionEnmiendaPayloadWriter writer = new FormalizacionEnmiendaPayloadWriter();

        //Arma el nombre con el que se guardan los montos a desobligar en el payload
        String paramName = PA15Constants.PALYOAD_PREFIXDESOBLIGAR + idLineaCredito;
        if (montoDesobligar != null) {
            writer.putParameterType(paramName, montoDesobligar.toString());
        } else {
            writer.removeParameterType(paramName);
        }

        LOGGER.info("Invocando actualización de la tarea...");
        writer.updateTask();
    }

    /**
     * Gestiona el evento lanzado por la región de líneas de crédito cuando se propagan las líneas y entonces se deben
     * actualizar la bandera que permite finalizar el proceso.
     *
     * @param eventPayLoad Payload del evento lanzado por la región de líneas de crédito.
     */
    public void handlePropagarLCEvent(Object eventPayLoad) {
        LOGGER.info("Dentro de handleUpdateLCEvent");

        LOGGER.info("Actualizando bandera InfoLineaCreditoActualizada");
        //actualiza bandera que controla que se haya actualizado la informacion
        //Esto es requerido para finalizar tarea
        this.setInfoLineaCreditoActualizada(true);
    }
}
