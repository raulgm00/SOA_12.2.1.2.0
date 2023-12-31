package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.utils.ModelUtils;
import org.bcie.fenix.common.model.vo.common.TccElementosTreeVO;
import org.bcie.matriztccbo.ListaTCC;
import org.bcie.matriztccbo.TCC;
import org.bcie.matriztccbo.Tipo;
import org.bcie.matriztccmo.ActualizarEstadoTCCRequestType;
import org.bcie.matriztccmo.ActualizarEstadoTCCResponseType;
import org.bcie.matriztccservice.MatrizTCC12BndQSService;
import org.bcie.matriztccservice.MatrizTCCPT;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Feb 03 19:17:56 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TccElementosTreeVOImpl extends ViewObjectImpl implements TccElementosTreeVO {
    
    private static ADFLogger logger = null;
    public static final String BUSCAR_POR_ESTADO_VC = "TccElementosTreeVOCriteriaByEstadoTcc";
    public static final String BUSCAR_POR_ESTADO_DIFERENTE_VC = "TccElementosTreeVOCriteriaByNotInEstadoTcc";

    
    /**
     * This is the default constructor (do not remove).
     */
    public TccElementosTreeVOImpl() {
        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }


    /**
     * Returns the bind variable value for varIdOperacion.
     * @return bind variable value for varIdOperacion
     */
    public Long getvarIdOperacion() {
        return (Long) getNamedWhereClauseParam("varIdOperacion");
    }

    /**
     * Sets <code>value</code> for bind variable varIdOperacion.
     * @param value value to bind as varIdOperacion
     */
    public void setvarIdOperacion(Long value) {
        setNamedWhereClauseParam("varIdOperacion", value);
    }

    /**
     * Returns the variable value for idEstadoTcc.
     * @return variable value for idEstadoTcc
     */
    public Integer getidEstadoTcc() {
        return (Integer) ensureVariableManager().getVariableValue("idEstadoTcc");
    }

    /**
     * Sets <code>value</code> for variable idEstadoTcc.
     * @param value value to bind as idEstadoTcc
     */
    public void setidEstadoTcc(Integer value) {
        ensureVariableManager().setVariableValue("idEstadoTcc", value);
    }

    public List<Map> obtenerTccsPorEstadoIgual(Long idOperacion, Integer idEstadoTcc) {
        logger.log(ADFLogger.TRACE, "Inside obtenerTccsPorEstadoIgual.");
        return obtenerTccsPorEstado(true, idOperacion, idEstadoTcc);
    }
    
    public List<Map> obtenerTccsPorEstadoDiferente(Long idOperacion, Integer idEstadoTcc) {
        logger.log(ADFLogger.TRACE, "Inside obtenerTccsPorEstadoDiferente.");
        return obtenerTccsPorEstado(false, idOperacion, idEstadoTcc);
    }
    
    private List<Map> obtenerTccsPorEstado(Boolean esEstadoIgual, Long idOperacion, Integer idEstadoTcc) {
        logger.log(ADFLogger.WARNING, "Inside obtenerTccsPorEstado. esEstadoIgual: " + esEstadoIgual + 
                                      ". idOperacion: " + idOperacion + ". idEstadoTcc: " + idEstadoTcc);
        List<Map> tccsPorEstado = null;
        ViewCriteria criteriaByNotInEstadoTcc = null;
        RowSetIterator iterator = null;
        
        // Ejecutamos query que obtiene todos los elementos activos TCC por IdOperacion
        setvarIdOperacion(idOperacion);
        this.executeQuery();
        
        // Ejecutamos VC para filtrar el query con registros diferente o igual a un idEstadoTcc
        if(esEstadoIgual)
            criteriaByNotInEstadoTcc = this.getViewCriteriaManager().getViewCriteria(BUSCAR_POR_ESTADO_VC);
        else
            criteriaByNotInEstadoTcc = this.getViewCriteriaManager().getViewCriteria(BUSCAR_POR_ESTADO_DIFERENTE_VC);
        
        setidEstadoTcc(idEstadoTcc);
        this.applyViewCriteria(criteriaByNotInEstadoTcc);
        this.executeQuery();
            
        // Llenamos lista
        if(this.getEstimatedRowCount() > 0) {
            tccsPorEstado = new ArrayList<Map>();
            iterator = this.createRowSetIterator(null);
            iterator.reset();
            
            while (iterator.hasNext()) {
                Row row = iterator.next();
                String tipo = (String)row.getAttribute("Tipo");
                Long idTcc = (Long)row.getAttribute("IdTcc");
                HashMap<String, Object> elementoTcc = new HashMap<String, Object>();
                
                elementoTcc.put("tipo", tipo);
                elementoTcc.put("idTcc", idTcc);
                tccsPorEstado.add(elementoTcc);
            }
            
            iterator.closeRowSetIterator();  
        }
        
        // Removemos VC
        if(esEstadoIgual)
            this.getViewCriteriaManager().removeApplyViewCriteriaName(BUSCAR_POR_ESTADO_VC);
        else
            this.getViewCriteriaManager().removeApplyViewCriteriaName(BUSCAR_POR_ESTADO_DIFERENTE_VC);

        // Re-ejecutamos query
        this.executeQuery();
        
        // Valor de retorno
        return tccsPorEstado;
    }
        
    /**
     * Validaci�n para Formalizaci�n. Pantalla "Elaborar borrador de contrato".
     * RN06: Todos los elementos TCC que se encuentren en un estado diferente a "Por validar" deben pasar al estado 
     * "Formalizada" al finalizar la tarea.
     * @param idOperacion
     * @return
     * @deprecated Seg�n incidencia FNXII-3165, esta validaci�n no aplica.
     */
    public Boolean validarTccFinalizarElaborarBorradorContrato(Long idOperacion) {
        Boolean esEjecucionExitosa = Boolean.TRUE;
        List<Map> tccsPorEstadoDiferente = null;
        
        // 1) Obtenemos lista de elementos a los que se les cambiar� su estado.
        tccsPorEstadoDiferente = obtenerTccsPorEstadoDiferente(idOperacion, FenixModelConstants.ESTADO_TCC_POR_VALIDAR);
        
        // 2) Invocamos servicio para cambiar el estado
        if(tccsPorEstadoDiferente != null)
            esEjecucionExitosa = actualizarEstadoTccs(tccsPorEstadoDiferente, FenixModelConstants.ESTADO_TCC_FORMALIZADA, null);
        
        return esEjecucionExitosa;
    }
    
    /**
     * Validaci�n para Formalizaci�n. Pantalla "Adjuntar contrato firmado".
     * Todos los elementos TCC que se encuentren en un estado diferente a "Por validar" deben pasar al estado 
     * "Formalizada" al finalizar la tarea. Lo anterior de acuerdo a incidencia FNXII-3165.
     * @param idOperacion
     * @return
     */
    public Boolean validarTccAdjuntarContratoFirmado(Long idOperacion) {
        Boolean esEjecucionExitosa = Boolean.TRUE;
        List<Map> tccsPorEstadoDiferente = null;
        
        // 1) Obtenemos lista de elementos a los que se les cambiar� su estado.
        tccsPorEstadoDiferente = obtenerTccsPorEstadoDiferente(idOperacion, FenixModelConstants.ESTADO_TCC_POR_VALIDAR);
        
        // 2) Invocamos servicio para cambiar el estado
        if(tccsPorEstadoDiferente != null)
            esEjecucionExitosa = actualizarEstadoTccs(tccsPorEstadoDiferente, FenixModelConstants.ESTADO_TCC_FORMALIZADA, null);
        
        return esEjecucionExitosa;
    }
    
    /**
     * Validaci�n para Formalizaci�n. Pantalla "Negociar documentaci�n contractual con el cliente".
     * RN05: Los elementos de la Matriz TCC que se encuentren en el estado "Por validar" al finalizar la tarea deben 
     * pasar al estado "Validada".
     * @param idOperacion
     * @return
     */
    public Boolean validarTccFinalizarNegociarDocumentacion(Long idOperacion) {
        Boolean esEjecucionExitosa = Boolean.TRUE;
        List<Map> tccsPorEstadoIgual = null;
        
        // 1) Obtenemos lista de elementos a los que se les cambiar� su estado.
        tccsPorEstadoIgual = obtenerTccsPorEstadoIgual(idOperacion, FenixModelConstants.ESTADO_TCC_POR_VALIDAR);
        
        // 2) Invocamos servicio para cambiar el estado
        if(tccsPorEstadoIgual != null)
            esEjecucionExitosa = actualizarEstadoTccs(tccsPorEstadoIgual, FenixModelConstants.ESTADO_TCC_VALIDADA, null);

        return esEjecucionExitosa;
    }
    
    /**
     * Validaci�n para Formalizaci�n. Pantalla "Elaborar borrador de Contrato"
     * Debido a que es la �ltima pantalla donde se pueden editar los TCC, se verifica que la combinaci�n de Estados 
     * con Sub-estados coincida con el diagrama de estados.
     * @param idOperacion
     * @return
     */
    public Boolean validarTccElaborarBorradorContrato(Long idOperacion) {
        Boolean esSubestadosValidos = Boolean.TRUE;
        RowSetIterator iterator = null;
        
        // Ejecutamos query que obtiene todos los elementos activos TCC por IdOperacion
        setvarIdOperacion(idOperacion);
        this.executeQuery();
        
        // Iteramos lista
        if(this.getEstimatedRowCount() > 0) {
            iterator = this.createRowSetIterator(null);
            iterator.reset();
            
            while (iterator.hasNext()) {
                Row row = iterator.next();
                Integer estado = (Integer)row.getAttribute("IdTcaEstadoTcc");
                Integer subEstado = (Integer)row.getAttribute("IdTcaSubEstadoTcc");
                
                // El TCC debe contar con un sub-estado si su estado es Nueva, Sugerida, Mandatoria o Aprobada.
                if((estado != null) && 
                   ((estado.compareTo(FenixModelConstants.ESTADO_TCC_NUEVA) == 0) ||
                    (estado.compareTo(FenixModelConstants.ESTADO_TCC_SUGERIDA) == 0) || 
                    (estado.compareTo(FenixModelConstants.ESTADO_TCC_MANDATORIA) == 0))) {
                
                    if(subEstado == null)
                        esSubestadosValidos = Boolean.FALSE;
                    else{
                        
                        if(((estado.compareTo(FenixModelConstants.ESTADO_TCC_NUEVA) == 0) 
                            || (estado.compareTo(FenixModelConstants.ESTADO_TCC_SUGERIDA) == 0)) 
                           && (subEstado.compareTo(FenixModelConstants.SUBESTADO_TCC_EDITADA) != 0) 
                           && (subEstado.compareTo(FenixModelConstants.SUBESTADO_TCC_ELIMINADA) != 0)) {
                            
                            // Para los estados Nueva y Sugerida, los sub-estados v�lidos son Editada y Eliminada
                            esSubestadosValidos = Boolean.FALSE;
                        }
                        else if((estado.compareTo(FenixModelConstants.ESTADO_TCC_MANDATORIA) == 0) 
                                && (subEstado.compareTo(FenixModelConstants.SUBESTADO_TCC_MANDATORIA_EDITADA) != 0) 
                                && (subEstado.compareTo(FenixModelConstants.SUBESTADO_TCC_EXCEPTUADA) != 0)) {
                            
                            // Para el estado Mandatoria, los sub-estados v�lidos son "Mandatoria Editada" y Exceptuada
                            esSubestadosValidos = Boolean.FALSE;
                        }
                    }
                }
                
                // En caso de que alg�n sub-estado NO sea v�lido, salimos del loop
                if(!esSubestadosValidos)
                    break;
            }
            
            iterator.closeRowSetIterator();  
        }
        
        return esSubestadosValidos;
    }
    
    public Boolean actualizarEstadoTccs(List<Map> tccsPorEstado, Integer estado, Integer subEstado) {
        Boolean esEjecucionExitosa = Boolean.TRUE;
        Boolean esError = Boolean.FALSE;
        String mensajeError = null;
        ActualizarEstadoTCCRequestType request = null;
        ActualizarEstadoTCCResponseType response = null;   
        ListaTCC listaTcc = new ListaTCC();
        Iterator iterator = tccsPorEstado.iterator();
        
        while (iterator.hasNext()) {
            HashMap elementoTcc = (HashMap)iterator.next();
            TCC tcc = new TCC();
            Long idTcc = (Long)elementoTcc.get("idTcc");
            String tipoTcc = (String)elementoTcc.get("tipo");
            
            // Datos comunes de TCC
            tcc.setId(idTcc.longValue());
            tcc.setTipo(Tipo.fromValue(tipoTcc));
            tcc.setEstado(estado);
            
            if(subEstado != null)
                tcc.setSubEstado(subEstado);
            
            // Se inserta TCC a la lista
            listaTcc.getTCC().add(tcc);
        }
        
        // Invocaci�n a servicio
        try {
            FenixAMImpl fenixAM = (FenixAMImpl)this.getRootApplicationModule();
            String wsdl = fenixAM.getWsdl(IWsdlLocation.MATRIZ);

            MatrizTCC12BndQSService matrizTCC12BndQSService = IWsdlLocation.Service.getInstance(MatrizTCC12BndQSService.class, wsdl);
            MatrizTCCPT matrizTccPT = matrizTCC12BndQSService.getMatrizTCC12BndQSPort();
            
            request = new ActualizarEstadoTCCRequestType();
            request.setListaTCC(listaTcc);
            
            Date horaInicio = ModelUtils.logStartWS(logger, request, FenixModelConstants.ACTUALIZAR_ESTADO_TCC);
                response = matrizTccPT.actualizarEstadoTCC(request);
            ModelUtils.logEndWS(logger, response, FenixModelConstants.ACTUALIZAR_ESTADO_TCC, horaInicio);
            
            if(response.getResultado().getResult().value()=="ERROR"){
                esEjecucionExitosa = Boolean.FALSE;
                esError = Boolean.TRUE;
                mensajeError = response.getResultado().getMessage();
                
                if(response.getResultado().getError() != null)
                    mensajeError = mensajeError.concat(response.getResultado().getError().getErrorDescription());
            }
            
        } catch(Exception e){
            logger.log(ADFLogger.ERROR, FenixModelConstants.ERROR_AL + FenixModelConstants.ACTUALIZAR_ESTADO_TCC + e.getClass() + ":" + e.getMessage());
            esEjecucionExitosa = Boolean.FALSE;  
            esError = Boolean.TRUE;
        } finally {
            if (esError) {
                JboException exception = new JboException(FenixModelConstants.ERROR_AL + FenixModelConstants.ACTUALIZAR_ESTADO_TCC + "- " + mensajeError);
                throw exception;
            }
        }
        
        return esEjecucionExitosa;
    }

    public Boolean actualizarComisionMonto(Long idOperacion, Integer tipoMonto) {
        logger.log(ADFLogger.WARNING,"IdOperacion" + idOperacion + "  TipoMonto" +tipoMonto);
        Boolean retorna = Boolean.TRUE;
        FenixAMImpl fenixAM = null;
        RowSetIterator iterator = null;
        String tipoTcc;
        Long idComision;
        Integer idMontoBase=tipoMonto-1;
        Boolean actualizaComision;

        // Crea una instancia del AM
        fenixAM = (FenixAMImpl) this.getRootApplicationModule();
        BigDecimal montoBase = fenixAM.getMontoOperacionVO().obtenerMontoPorTipo(idOperacion, tipoMonto);
        logger.log(ADFLogger.WARNING,"MontoBase Obtenido" + montoBase);
        if (null != montoBase || montoBase.compareTo(BigDecimal.ZERO) == 0) {
            // Ejecutamos query que obtiene todos los elementos activos TCC por IdOperacion
            setvarIdOperacion(idOperacion);
            this.executeQuery();

            // Iteramos lista
            if (this.getEstimatedRowCount() > 0) {
                iterator = this.createRowSetIterator(null);
                iterator.reset();

                while (iterator.hasNext()) {
                    Row row = iterator.next();
                    idComision= (Long)row.getAttribute("IdTcc");
                    tipoTcc = (String) row.getAttribute("Tipo");
                    if (tipoTcc.equalsIgnoreCase("COMISION")) {
                        actualizaComision =
                            fenixAM.getTccComisionVO().actualizarMonto(idComision, montoBase, idMontoBase);
                        if (null == actualizaComision || !actualizaComision) {
                            retorna = Boolean.FALSE;
                            break;
                        }
                    }

                }
                iterator.closeRowSetIterator();
            }
        }
        logger.log(ADFLogger.WARNING,"resultado actualizar comisiones" + retorna);
        return retorna;
    }
}

