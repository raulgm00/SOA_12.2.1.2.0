package pa16administraroperacionght.beans;

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

//import org.bcie.fenix.view.gestoroperaciones.GestorOperacionesBean;

import org.w3c.dom.NodeList;

public class OperacionBean extends FenixPanelBean implements Serializable  {
    
    @SuppressWarnings("compatibility:8935141361672817038")
    private static final long serialVersionUID = 1L;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(OperacionBean.class);
    
    
    private Boolean esEstadoCompletado = Boolean.FALSE;//Variable Estado BPM
    
    private String idOperacion;
    private String codigoTarea;
    //variable producto, parametro para herramienta estrategica
    private String codigoProducto;
    //idCliente
    private String codigoCliente;
    private String instanciaProceso;
    
    private Long idSector;
    
    // Variables Sector publico
    private Boolean esUnidadEjecutoraSecPublico=false;
    private Boolean esTipoDeGarantiaSecPublico=false;
    
    //Variables para mostrar campos de operacion segun config de producto
    private Boolean esMontoTotal=false;
    private Boolean esTipoDeGarantia=false;
    private Boolean esComponenteDeConsecionalidad=false;
    private Boolean esEstructurador=false;
    private Boolean esBeneficiario=false;
    private Boolean esPrograma=false;
    
    public OperacionBean() {
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
        
        nl = xmlPayload.getElementsByTagName("idOperacion");
        if(nl.getLength()>0){
           idOperacion = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName("idProducto");
        if(nl.getLength()>0)
        {   
            codigoProducto = nl.item(0).getTextContent();
        }
        
        // Recuperar idCliente del Payload
        nl = xmlPayload.getElementsByTagName("idCliente");
        if(nl.getLength()>0)
        {
            codigoCliente = nl.item(0).getTextContent();
        }
        
        obtenerInstanciaProceso();
        
        obtenerConfiguracionByIdProducto(Integer.parseInt(codigoProducto));
      
        LOGGER.warning("codigoTarea: " + codigoTarea);
        LOGGER.warning("idOperacion: " + idOperacion);
        LOGGER.warning("idProducto: " + codigoProducto);
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
    
    public void getIdSectorPayload()  {
    LOGGER.warning("Dentro de getIdSectorPayload");
    
    Long sector =  (null != ADFUtils.getBoundAttributeValue("idSector")) 
                ? (Long)ADFUtils.getBoundAttributeValue("idSector"): null; 
    LOGGER.warning("Var sector: "+sector);
    
    try {
        if (sector != null) {
            LOGGER.warning("sector :" + sector);   
            this.setIdSector(sector);
            setEsUnidadEjecutoraSecPublico(sector.equals(1L) ? Boolean.TRUE : Boolean.FALSE);
            setEsTipoDeGarantiaSecPublico(sector.equals(1L) ? Boolean.TRUE : Boolean.FALSE);
        }else{
            LOGGER.warning("sector es nulo");
            setEsUnidadEjecutoraSecPublico(Boolean.FALSE);
            setEsTipoDeGarantiaSecPublico(Boolean.FALSE);
        }
    } catch (Exception ex) {
        LOGGER.warning("Error al recuperar NodeList sector :",ex);
        LOGGER.warning("sector no obtenido");
    }

        LOGGER.warning("sector : " + getIdSector());
        LOGGER.warning("EsUnidadEjecutoraSecPublico : " + getEsUnidadEjecutoraSecPublico());
        LOGGER.warning("EsTipoDeGarantiaSecPublico : " + getEsTipoDeGarantiaSecPublico());
    LOGGER.warning("Fuera de getIdSectorPayload");        
    }
    
    public void obtenerConfiguracionByIdProducto(Integer idProducto) {
        LOGGER.warning( "Inside obtenerConfiguracionByIdProducto");
        ConfigVisibilidadVORowImpl rowConfigVisibilidad = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("recuperarConfiguracionVisibilidad");
        operationBinding.getParamsMap().put("idProducto", idProducto);
        operationBinding.execute();

        rowConfigVisibilidad = (ConfigVisibilidadVORowImpl) operationBinding.getResult();

        LOGGER.warning( "ID TABLA CONFIGURACION: " + rowConfigVisibilidad.getId());
        LOGGER.warning( "ID PRODUCTO: " + rowConfigVisibilidad.getIdProducto());
        LOGGER.warning( "DatosGenerales: " + rowConfigVisibilidad.getDatosGenerales().toString());
        LOGGER.warning( "MontoTotal: " + rowConfigVisibilidad.getMontoTotal().toString());
        LOGGER.warning( "Actividad Economica: " + rowConfigVisibilidad.getActividadEconomica().toString());
        LOGGER.warning(
                   "Iniciativa estrategica: " + rowConfigVisibilidad.getIniciativaEstrategica().toString());
        LOGGER.warning(
                   "Objetivo estrategico: " + rowConfigVisibilidad.getObjetivoEstrategico().toString());
        LOGGER.warning( "Area focalizacion: " + rowConfigVisibilidad.getAreaFocalizacion().toString());
        LOGGER.warning( "Eje estrategico: " + rowConfigVisibilidad.getEjeEstrategico().toString());
        LOGGER.warning( "Tipo de garantia: " + rowConfigVisibilidad.getTipoGarantia().toString());
        LOGGER.warning(
                   "Componente de consecinalidad:" + rowConfigVisibilidad.getComponenteConcesionalidad().toString());
        LOGGER.warning( "Estructurador:" + rowConfigVisibilidad.getEstructurador().toString());
        LOGGER.warning( "Beneficiario:" + rowConfigVisibilidad.getBeneficiario().toString());
        LOGGER.warning( "Programa:" + rowConfigVisibilidad.getPrograma().toString());
        LOGGER.warning(
                   "Operaciones asociadas:" + rowConfigVisibilidad.getOperacionesAsociadas().toString());
        LOGGER.warning(
                   "Responde cuestionario:" + rowConfigVisibilidad.getRespondeCuestionario().toString());
        LOGGER.warning( "Analisis LAFT:" + rowConfigVisibilidad.getAnalisisLaft().toString());
        LOGGER.warning( "Sector mercado:" + rowConfigVisibilidad.getSectorMercado().toString());

        this.mapeoConfiguracionVisibilidad(rowConfigVisibilidad);
    }

    public void mapeoConfiguracionVisibilidad(ConfigVisibilidadVORowImpl rowConfigVisibilidad) {
        LOGGER.log(ADFLogger.TRACE, "Inside mapeoConfiguracionVisibilidad");
        
        if (Integer.parseInt(rowConfigVisibilidad.getMontoTotal().toString()) == 0) {
            setEsMontoTotal(false);
        } else {
            setEsMontoTotal(true);
        }
        
        if (Integer.parseInt(rowConfigVisibilidad.getTipoGarantia().toString()) == 0) {
            setEsTipoDeGarantia(false);
        } else {
            setEsTipoDeGarantia(true);
        }
        
        if (Integer.parseInt(rowConfigVisibilidad.getComponenteConcesionalidad().toString()) == 0) {
            setEsComponenteDeConsecionalidad(false);
        } else {
            setEsComponenteDeConsecionalidad(true);
        }
        
        if (Integer.parseInt(rowConfigVisibilidad.getEstructurador().toString()) == 0) {
            setEsEstructurador(false);
        } else {
            setEsEstructurador(true);
        }
        
        if (Integer.parseInt(rowConfigVisibilidad.getBeneficiario().toString()) == 0) {
            setEsBeneficiario(false);
        } else {
            setEsBeneficiario(true);
        }
        
        if (Integer.parseInt(rowConfigVisibilidad.getPrograma().toString()) == 0) {
            setEsPrograma(false);
        } else {
            setEsPrograma(true);
        }

    }
    
    public Boolean getEsEditable()
    {
        return !this.esEstadoCompletado && this.codigoTarea.equals("222");
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

    public void setCodigoProducto(String codigoProducto)
    {
    this.codigoProducto = codigoProducto;
    }

    public String getCodigoProducto()
    {
    return codigoProducto;
    }
    
    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    
    public Long getIdSector() {
        return idSector;
    }

    public void setIdSector(Long idSector) {
        this.idSector = idSector;
    }
    
    public Boolean getEsUnidadEjecutoraSecPublico() {
        return esUnidadEjecutoraSecPublico;
    }

    public void setEsUnidadEjecutoraSecPublico(Boolean esUnidadEjecutoraSecPublico) {
        this.esUnidadEjecutoraSecPublico = esUnidadEjecutoraSecPublico;
    }

    public Boolean getEsTipoDeGarantiaSecPublico() {
        return esTipoDeGarantiaSecPublico;
    }

    public void setEsTipoDeGarantiaSecPublico(Boolean esTipoDeGarantiaSecPublico) {
        this.esTipoDeGarantiaSecPublico = esTipoDeGarantiaSecPublico;
    }
        
    public Boolean getEsMontoTotal() {
        return esMontoTotal;
    }

    public void setEsMontoTotal(Boolean esMontoTotal) {
        this.esMontoTotal = esMontoTotal;
    }
    
    public void setEsTipoDeGarantia(Boolean esTipoDeGarantia) {
        this.esTipoDeGarantia = esTipoDeGarantia;
    }
    
    public Boolean getEsTipoDeGarantia() {
        return esTipoDeGarantia;
    }

    public void setEsComponenteDeConsecionalidad(Boolean esComponenteDeConsecionalidad) {
        this.esComponenteDeConsecionalidad = esComponenteDeConsecionalidad;
    }

    public Boolean getEsComponenteDeConsecionalidad() {
        return esComponenteDeConsecionalidad;
    }

    public void setEsEstructurador(Boolean esEstructurador) {
        this.esEstructurador = esEstructurador;
    }

    public Boolean getEsEstructurador() {
        return esEstructurador;
    }

    public void setEsBeneficiario(Boolean esBeneficiario) {
        this.esBeneficiario = esBeneficiario;
    }

    public Boolean getEsBeneficiario() {
        return esBeneficiario;
    }

    public void setEsPrograma(Boolean esPrograma) {
        this.esPrograma = esPrograma;
    }

    public Boolean getEsPrograma() {
        return esPrograma;
    }
}
