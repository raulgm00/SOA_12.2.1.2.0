package org.bcie.fenix.view.consolidadocargopenalidadprepago;

import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.HashMap;
import java.util.Map;

import java.util.ResourceBundle;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.FenixConstants;

public class ConsolidadoCargoPenalidadPrepagoBean extends FenixConstants implements Serializable{
    @SuppressWarnings("compatibility:4295542902769704910")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    public static final String BUNDLE = "pa12prepagoght.PA12PrepagoGHTBundle";
    private Boolean isTipoMoneda = Boolean.FALSE;
    private String nombreMoneda = null;
    private Timestamp fechaVigentePenalizacion = null;
    private Boolean isResolucion = Boolean.FALSE;
    private Boolean isFechaPenalizacion = Boolean.TRUE;
    private String mensajeCargosPenalidad;
    private Integer resolucion;

    public ConsolidadoCargoPenalidadPrepagoBean() {
        super();
        if(logger == null){
           logger = ADFLogger.createADFLogger(this.getClass()); 
        }
    }

    public void cargarDatosConsolidadoCargoPenalidad() {
        
        logger.log(ADFLogger.WARNING, "INTO cargarDatosConsolidadoCargoPenalidad.");
        Integer tipoMoneda = null;
        Map<String,Object> valoresConsolidado = new HashMap<String, Object>();
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Long pIdPrepago = null;
        Integer idTcaTipoResolucion = null;
        Boolean isMontoMayor = Boolean.FALSE;
        Boolean isMontoMenor = Boolean.FALSE;
        Map<String, Object> fuentes = new HashMap<>();
        BigDecimal montoFuentesExternasDolar = new BigDecimal(0);
        BigDecimal montoFuentesExternasLocal = new BigDecimal(0);
        
        try{
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}")){
                pIdPrepago = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}").toString());
                logger.log(ADFLogger.WARNING, "Valor de Id Prepago.." + pIdPrepago);
            }
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdTcaTipoResolucion}")){
                idTcaTipoResolucion = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdTcaTipoResolucion}").toString());
                resolucion = idTcaTipoResolucion;
                logger.log(ADFLogger.WARNING, "Valor de Id de la resolucion.." + idTcaTipoResolucion);
            }
            OperationBinding operationBindingConsolidado = bindings.getOperationBinding("cargarDatosConsolidadoPenalidad");
            operationBindingConsolidado.getParamsMap().put("idPrepago", pIdPrepago);
            operationBindingConsolidado.getParamsMap().put("idTcaTipoResolucion", idTcaTipoResolucion);
            operationBindingConsolidado.execute();
            if(null != operationBindingConsolidado.getResult()){
                valoresConsolidado = (Map)operationBindingConsolidado.getResult();
            }else{
                logger.log(ADFLogger.WARNING, "Valor de retorno nulo.");
            }

//            OperationBinding operationBindingPenalidad = bindings.getOperationBinding("cargarDatosDetalleCargoPenalidadPrepago");
//            operationBindingPenalidad.getParamsMap().put("idPrepago", pIdPrepago);
//            operationBindingPenalidad.getParamsMap().put("montoFuentesExternasDolar", montoFuentesExternasDolar);
//            operationBindingPenalidad.getParamsMap().put("montoFuentesExternasLocal", montoFuentesExternasLocal);
//            operationBindingPenalidad.execute();
            
            if(null != valoresConsolidado.get("fechaVigentePenalizacion")){
                fechaVigentePenalizacion = (Timestamp)valoresConsolidado.get("fechaVigentePenalizacion");;
            }else{
                logger.log(ADFLogger.WARNING, "Valor de la fecha de penalizacion es nula.");
            }
            
            if (null != valoresConsolidado.get("descripcionMonedaPrincipal")) {
                nombreMoneda = (String) valoresConsolidado.get("descripcionMonedaPrincipal");
                if (nombreMoneda.compareTo(FenixModelConstants.MONEDA_DESCRIPCION_USD) == 0) {
                    isTipoMoneda = Boolean.FALSE;
                } else {
                    isTipoMoneda = Boolean.TRUE;
                }
            } else {
                logger.warning("Valor del nombre de la moneda es nula.");
            }
            
            if(null != valoresConsolidado.get("isMontoMayor")){
                isMontoMayor = (Boolean)valoresConsolidado.get("isMontoMayor");
                logger.warning("Valor monto fondos :" + isMontoMayor);
            }else{
                logger.warning("Valor comparacion montos de fuentes es nulo.");
            }
            
            if (idTcaTipoResolucion.compareTo(FenixConstants.OTRAS_CONDICIONES) == 0) {
                logger.warning("inside OTRAS_CONDICIONES.");
                isResolucion = Boolean.FALSE;
            }
            if (idTcaTipoResolucion.compareTo(FenixConstants.PRE10_2008) == 0) {
                logger.warning("inside PRE10_2008.");
                if(isMontoMayor){
                    this.setMensajeCargosPenalidad(getStringFromBundle("MSG_COFI_PRE10_GESTIONAR_PREPAGO_CLIENTE"));
                    isResolucion = Boolean.TRUE;
                }else
                    isResolucion = Boolean.FALSE;
            }
            if (idTcaTipoResolucion.compareTo(FenixConstants.PRE28_2003) == 0) {
                logger.log(ADFLogger.WARNING, "Valor de Id de la resolucion.." + idTcaTipoResolucion);
                logger.warning("inside PRE28_2003.");
                if(isMontoMayor){
                    this.setMensajeCargosPenalidad(getStringFromBundle("MSG_COFI_PRE28_GESTIONAR_PREPAGO_CLIENTE"));
                    isResolucion = Boolean.TRUE;
                }else
                    isResolucion = Boolean.FALSE;
            }
            
            logger.log(ADFLogger.WARNING, "isResolucion: " + isResolucion);
//            if(tipoMoneda.compareTo(FenixConstants.TIPO_MONEDA_DOLAR) == 0){
//                isTipoMoneda = Boolean.FALSE;
//            }else{
//                isTipoMoneda = Boolean.TRUE;
//            }
//            if(null == fechaVigentePenalizacion){
//                isFechaPenalizacion = Boolean.TRUE;
//            }else{
//                isFechaPenalizacion = Boolean.FALSE;
//            }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en cargarDatosConsolidadoCargoPenalidad." + e.getClass() + "." + e.getMessage());
        }
    }
    
    private String getStringFromBundle(String psKey) {
        logger.warning("inside getStringFromBundle.");
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (null != resourceBundle) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }
    
    public void setIsTipoMoneda(Boolean isTipoMoneda) {
        this.isTipoMoneda = isTipoMoneda;
    }

    public Boolean getIsTipoMoneda() {
        return isTipoMoneda;
    }

    public void setNombreMoneda(String nombreMoneda) {
        this.nombreMoneda = nombreMoneda;
    }

    public String getNombreMoneda() {
        return nombreMoneda;
    }
    public void setFechaVigentePenalizacion(Timestamp fechaVigentePenalizacion) {
        this.fechaVigentePenalizacion = fechaVigentePenalizacion;
    }

    public Timestamp getFechaVigentePenalizacion() {
        return fechaVigentePenalizacion;
    }

    public void setIsResolucion(Boolean isResolucion) {
        this.isResolucion = isResolucion;
    }

    public Boolean getIsResolucion() {
        return isResolucion;
    }

    public void setIsFechaPenalizacion(Boolean isFechaPenalizacion) {
        this.isFechaPenalizacion = isFechaPenalizacion;
    }

    public Boolean getIsFechaPenalizacion() {
        return isFechaPenalizacion;
    }
    
    public void setMensajeCargosPenalidad(String mensajeCargosPenalidad) {
        this.mensajeCargosPenalidad = mensajeCargosPenalidad;
    }

    public String getMensajeCargosPenalidad() {
        return mensajeCargosPenalidad;
    }

    public void setResolucion(Integer resolucion) {
        this.resolucion = resolucion;
    }

    public Integer getResolucion() {
        return resolucion;
    }
}
