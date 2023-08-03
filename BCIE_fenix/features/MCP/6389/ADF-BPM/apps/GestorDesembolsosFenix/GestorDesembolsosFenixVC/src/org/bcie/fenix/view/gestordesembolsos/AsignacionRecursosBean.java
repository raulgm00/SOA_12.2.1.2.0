package org.bcie.fenix.view.gestordesembolsos;

import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class AsignacionRecursosBean extends FenixActionBean implements Serializable {
    @SuppressWarnings("compatibility:1823423704535359724")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    
    public AsignacionRecursosBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    BigDecimal montoTotal;


    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public BigDecimal getMontoTotal() {
        RowSetIterator iter = null;
        BigDecimal totalMonto = null;
        BigDecimal montoDesembolsar = null;
        AsignacionRecursosBean asignacionRecursosBean = (AsignacionRecursosBean) JSFUtils.resolveExpression("#{pageFlowScope.AsignacionRecursosManagedBean}");
        
        logger.warning("Obteniendo Iterator de FuentesExternasContratoDesembolsoVOIterator");
        iter =
            ADFUtils.getDCBindingContainer().findIteratorBinding("FuentesExternasContratoDesembolsoVOIterator").getRowSetIterator();

        if (null != iter) {
            logger.warning("Iterando FuentesExternasContratoDesembolsoVOIterator");
            for (Row row : iter.getAllRowsInRange()) {
                if (null != row.getAttribute("MontoDesembolsar")) {
                    logger.warning("Sumando Monto a desembolsar de Linea Pasiva " + row.getAttribute("IdLineaPasiva") +
                                   ": " + row.getAttribute("MontoDesembolsar"));
                    montoDesembolsar = new BigDecimal(row.getAttribute("MontoDesembolsar").toString());
                    logger.warning("Monto a desembolsar: " + montoDesembolsar);
                    if(null == totalMonto){
                        totalMonto = montoDesembolsar;
                    }else{
                        totalMonto = totalMonto.add(montoDesembolsar);
                    }
                }
            }
        } else {
            logger.warning("Iterador es NULL");
        }
        
        logger.warning("Total de montos: " + totalMonto);
        
        return totalMonto;
    }
    
    public void precargaInformacionAsignacionRecursos(){
        logger.warning("Inicia metodo precargaInformacionAsignacionRecursos");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        final Integer REGISTRAR_DESEMBOLSO = new Integer(157);
        Map<String, Object> mapaParametros = new HashMap<String, Object>();
        
        Boolean cargarInformacion = Boolean.FALSE;
        
        Integer pIdTareaBPM = null;
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}")){
            pIdTareaBPM =
                Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}").toString());                    
                                           
        }else{
            logger.warning("idTareaBPM es NULL");
        }
        
        logger.warning("idTareaBPM: "+pIdTareaBPM);
        
        try {          
            
            if(pIdTareaBPM.compareTo(REGISTRAR_DESEMBOLSO) == 0){
                logger.warning("Cargando asignacion de recursos para tarea REGISTRAR_DESEMBOLSO");
                cargarInformacion = Boolean.TRUE;
            }else{
                cargarInformacion =
                    new Boolean(JSFUtils.resolveExpression("#{pageFlowScope.cargarInformacion}").toString());
            }
            
            logger.warning("Atributo CargarInformacion: " + cargarInformacion);
        } catch (Exception e) {
            logger.warning("Error al castear parametro cargarInformacion");
        }
        
        if(null != cargarInformacion){
            if(!cargarInformacion){
                logger.warning("Atributo CargarInformacion es FALSE. No se cargara Informacion.");
                return;
            }
        }else{
            logger.warning("Atributo CargarInformacion es NULL. NO se cargara informacion.");
            return;
        }
        
        oracle.jbo.domain.Number pIdLineaCredito = null;
        try {
            pIdLineaCredito =
                new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.idLineaCredito}").toString());
        } catch (Exception e) {
            logger.warning("Error al castear parametro pIdLineaCredito");
        }
        
        Long pIdContratoDesembolso = null;
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.idContratoDesembolso}")){
            pIdContratoDesembolso =
                new Long(JSFUtils.resolveExpression("#{pageFlowScope.idContratoDesembolso}").toString());
        }else{
            logger.warning("IdContrato es NULL");
        }
        
        
        
        logger.warning("IdLineaCredito de TaskFlow: " + pIdLineaCredito);
        logger.warning("IdContrato de TaskFlow: " + pIdContratoDesembolso);
        logger.warning("IdTareaBPM de TaskFlow: " + pIdTareaBPM);
        
        if(null != pIdContratoDesembolso){
            mapaParametros.put("idContrato", pIdContratoDesembolso);
            mapaParametros.put("idLineaCredito", pIdLineaCredito);
            mapaParametros.put("idTareaBPM", pIdTareaBPM);
            
            try{
                //Datos de prueba
                //pIdTareaBPM = 153;
                if (cargarFuentesExternas(pIdTareaBPM)) {
                    logger.warning("Ejecutando OperationBinding de obtenerDatosFuentesExternas");
                    super.execute(mapaParametros, "obtenerDatosFuentesExternas");
                } else {
                    logger.warning("No se ejecuta obtenerDatosFuentesExternas");
                }
            }catch(Exception e){
                logger.warning("Error al ejecutar operationBinding ", e);
                JSFUtils.addFacesErrorMessage("ERROR al ejecutar obtenerDatosFuentesExternas: " + e);
            }
            
            if(null != pIdTareaBPM){
                logger.warning("Inicializando TransferenciaRecursosVO");
                if(pIdTareaBPM.compareTo(FenixConstants.CGD_GESTIONAR_DESEMBOLSO_DE_FUENTES_EXTERNAS)==0){
                    try {
                        super.execute(null, "executarQueryTransfereciaRecursos");
                    } catch (OperationExecuteException e) {
                        logger.warning("Error al ejecutar operationBinding ", e);   
                    }
                }
            }
        }
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/ 1000; //Calculamos los milisegundos de diferencia
        logger.warning("Termina metodo precargaInformacionAsignacionRecursos con una duracion de: "+tiempo+" segundos");
    }
    
    private boolean cargarFuentesExternas(Integer idTarea) {
        logger.warning("Entrando en cargarFuentesExternas.");
        logger.warning("idTarea: " + idTarea);
        
        boolean cargarFuentesExternas = Boolean.TRUE;
        
        if(null != idTarea){
            
            switch(idTarea.intValue()) {
            case FenixConstants.CGD_LIQUIDAR_CONTRATO_DE_DESEMBOLSO:
                cargarFuentesExternas = Boolean.FALSE;
                break;
            case FenixConstants.CGD_VALIDAR_FONDOS_PRESUPUESTARIOS:
                cargarFuentesExternas = Boolean.FALSE;
                break;
            }
            
        }
        
        return cargarFuentesExternas;
    }
}
