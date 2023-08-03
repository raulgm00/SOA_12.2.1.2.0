package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.server.ViewRowImpl;
import oracle.jbo.server.ViewRowSetImpl;

import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.am.FenixGestorDesembolsosAMImpl;
import org.bcie.fenix.common.model.vo.common.TreSolicitudLineaCreditoVO;


public class TreSolicitudLineaCreditoVOImpl extends ViewObjectImpl implements TreSolicitudLineaCreditoVO {
    
    private static ADFLogger logger = ADFLogger.createADFLogger(TreSolicitudLineaCreditoVOImpl.class);
 
   
    public TreSolicitudLineaCreditoVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    
    public Boolean crearRegistroTreeSolicitudLineaCredito(Long idSolicitud, Long idLinea, Long idContratoDesembolso){
        logger.warning("*Inf, Inicia el metodo crearRegistroTreeSolicitudLineaCredito Parametros: ");
           Boolean resultado = Boolean.FALSE;
            Row treeSolicitudLineaCreditoRow = null;
            oracle.jbo.domain.Number idTreeSolicitudLineaCredito = null;
            SequenceImpl sequenceTreeSolicitudLineaCredito = null;
            NameValuePairs nvpTreeSolicitudLineaCredito = null;
                      
       
            logger.warning("*Inf idSolicitud: "+idSolicitud);
            logger.warning("*Inf idLinea: "+idLinea);
            logger.warning("*Inf idContratoDesembolso: "+idContratoDesembolso);
            
        if(idSolicitud == null || idLinea == null || idContratoDesembolso == null){              
                return Boolean.FALSE;
            }
       
       
            logger.warning("*Inf, Creando row en treSolicitud y seteando parametros.");
            sequenceTreeSolicitudLineaCredito = new SequenceImpl("TRE_SOLICITUD_LINEA_CRED_SEQ", getDBTransaction());
            idTreeSolicitudLineaCredito = sequenceTreeSolicitudLineaCredito.getSequenceNumber();
            nvpTreeSolicitudLineaCredito = new NameValuePairs();
        
        
            nvpTreeSolicitudLineaCredito.setAttribute("Id",idTreeSolicitudLineaCredito.longValue());
            nvpTreeSolicitudLineaCredito.setAttribute("IdContratoDesembolso",idContratoDesembolso.longValue());
            nvpTreeSolicitudLineaCredito.setAttribute("IdLinea", idLinea.longValue());
            nvpTreeSolicitudLineaCredito.setAttribute("IdSolicitud", idSolicitud.longValue());
            
            treeSolicitudLineaCreditoRow = this.createAndInitRow(nvpTreeSolicitudLineaCredito);   
            
            if(null == treeSolicitudLineaCreditoRow){
                logger.warning("*Inf, Important! El row a insertar es NULL");
            }else{
                logger.warning("*Inf, Row a insertar con datos. ID: " 
                               + treeSolicitudLineaCreditoRow.getAttribute("Id") + ", CONTRATO: " 
                               + treeSolicitudLineaCreditoRow.getAttribute("IdContratoDesembolso") + ", LINEA: "
                               + treeSolicitudLineaCreditoRow.getAttribute("IdLinea") + ", SOLICITUD: "
                               + treeSolicitudLineaCreditoRow.getAttribute("IdSolicitud"));
                insertRow(treeSolicitudLineaCreditoRow);
                setCurrentRow(treeSolicitudLineaCreditoRow);
            }                                
       try{    
            getDBTransaction().commit();            
            resultado = Boolean.TRUE;
            logger.warning("primer commit exitoso");
        }catch(Exception e){ 
            logger.warning("error en commit para crear registro TreeSolicitudLineaCredito:"+e);
           try{
               getDBTransaction().commit();
               resultado = Boolean.TRUE;
               logger.warning("segundo commit exitoso");
           }catch(Exception ex){
                logger.warning("***Error critico No se a podido crear el registro del contrato de desembolso devido a : ", ex);
                getDBTransaction().rollback(); 
                resultado = Boolean.FALSE;
                throw(ex);    
           }
        }                     

        this.executeQuery();                  
        logger.warning("Termina el metodo crearRegistroTreeSolicitudLineaCredito");
        return resultado;      
        }
    
    public Row getTreSolicitudLineaCredito(Long idContrato){
        logger.log(ADFLogger.WARNING, "Inside getTreSolicitudLineaCredito");
        Row row = null;
        ViewCriteria criteria = null;
        if(null == idContrato){
            logger.log(ADFLogger.WARNING, "IdContrato NULL - getTreSolicitudLineaCredito");
            return row;
        }
        try{
            criteria = this.getViewCriteriaManager().getViewCriteria("TreSolicitudLineaCreditoVOCriteria");
            criteria.ensureVariableManager().setVariableValue("pIdContratoDesembolso", idContrato);
            applyViewCriteria(criteria);
            executeQuery();
            if(getEstimatedRowCount() > 0){
                setCurrentRow(first());
                row = getCurrentRow();
                logger.warning("Datos recuperados asociados al contrato "+row.getAttribute("IdContratoDesembolso")+" IdLinea->"+row.getAttribute("IdLinea")+" IdSolicitud->"+row.getAttribute("IdSolicitud"));
            }else{
                logger.warning("El row es NULL");                
            }
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error en getTreSolicitudLineaCredito " + e.getClass() + ":" + e.getMessage());
        }
        
        this.getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoVOCriteria");
        return row;
    }


  //se modifico metodo el 09/02/2017
    public Long obtenerLineaCredito (String idContrato, String idSolicitud){
        logger.warning("*Inf, Inicia metodo obtenerLineaCredito");
            Long contrato = null;
            Long solicitud = null;
            Long resultado = null;
             ViewCriteria criteria = null;

            
            if(idContrato == null || idContrato.equals("") || idSolicitud == null || idSolicitud.equals("")){
               logger.warning("***Error , no se recibieron los parametros requeridos");
                 logger.warning("*Inf , idContrato : "+idContrato);
                 logger.warning("*Inf , idSolicitud : "+idSolicitud);
                return null;
            }else{
                   
                try{    
                    logger.warning("*Inf, parseando parametros recividos de String a Long");
                        contrato = new Long(idContrato);
                        solicitud = new Long(idSolicitud);
                }catch(Exception e){
                        logger.warning("***Error, No se pudo parsear los parametros String a Long : "+e);
                    }
                
                
                try{
                    logger.warning("*Inf, aplicando criteria TreSolicitudLineaCreditoVOCriteria1");
                    criteria = this.getViewCriteriaManager().getViewCriteria("TreSolicitudLineaCreditoVOCriteria1");
                    criteria.ensureVariableManager().setVariableValue("pIdContratoDesembolso", contrato);
                    criteria.ensureVariableManager().setVariableValue("pIdSolicitud", solicitud);
                    applyViewCriteria(criteria);
                    executeQuery();
                    if(getEstimatedRowCount() > 0){
                        logger.warning("*Inf, numero de resultados de la busqueda  " + getEstimatedRowCount());
                        setCurrentRow(first());
                        Row row = getCurrentRow();
                        if(null != (Long)row.getAttribute("IdLinea")){
                                resultado = (Long)row.getAttribute("IdLinea");
                                logger.warning("*Inf, id del la linea recuperado "+resultado);
                        }else{
                                logger.warning("*Inf, el atributo IdLinea en el Current es resuelto a null ");
                            }

                    }else{
                        logger.warning("*Inf, no se encontraton coincidencias en la busqueda El row debuelto es NULL");                
                    }
                }catch(Exception e){
                    logger.warning("***Error, al ejecutar al aplicar el criteria" + e);
                }
                logger.warning("*Inf, removiendo criteria TreSolicitudLineaCreditoVOCriteria1");
                this.getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoVOCriteria1");
                
            }
        logger.warning("*Inf, Termina metodo obtenerLineaCredito");
        return resultado;
    }
    
    public Boolean validarContratosSolicitud(Long idSolicitud){
        logger.warning("Inicia metodo validarContratosSolicitud");
        Boolean resultado = Boolean.FALSE;
        Row row = null;
        FenixGestorDesembolsosAMImpl fenixGestorDesembolsosAM =
            (FenixGestorDesembolsosAMImpl) this.getApplicationModule();
        ViewCriteria criteria = null;
        
        if(null == idSolicitud){
            logger.warning("idSolicitud llego NULL");
            return resultado;
        }
        
        criteria = this.getViewCriteriaManager().getViewCriteria("TreSolicitudLineaCreditoPorSolicitudVOCriteria");
        criteria.ensureVariableManager().setVariableValue("pIdSolicitud", idSolicitud);
        applyViewCriteria(criteria);
        executeQuery();
        
        logger.warning("Registros de contratos encontrados para la solicitud: " + getEstimatedRowCount());
        if(getEstimatedRowCount() > 0){
            Boolean registrosEncontrados = Boolean.FALSE;
            Long contratosEncontrados = null;
            RowSetIterator iter = createRowSetIterator(null);
            fenixGestorDesembolsosAM.getContratoDesembolsoVO().ejecutarQuerty();
            if (null != iter) {
                iter.reset();
                while (iter.hasNext()) {
                    row = iter.next();
                    if (null != row) {
                        if (null != row.getAttribute("IdContratoDesembolso")) {
                            Long idContrato = null;
                            try{
                                idContrato = (Long) row.getAttribute("IdContratoDesembolso");
                            }catch(Exception e){
                                logger.warning("No se pudo obtener el idContrato.");
                            }
                            
                            registrosEncontrados =
                                fenixGestorDesembolsosAM.getContratoDesembolsoVO().encontrarContratosDesestimados(idContrato);
                            
                            if(registrosEncontrados){
                                logger.warning("Se encontro al menos un contrato.");
                                resultado = Boolean.TRUE;
                                break;
                            }
                        } else {
                            logger.warning("IdContrato es NULL.");
                        }
                    } else {
                        logger.warning("Row es NULL.");
                    }
                }
            } else {
                logger.warning("Iterador vacio.");
            }
            iter.closeRowSetIterator();
        }
        
        this.getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoPorSolicitudVOCriteria");
        logger.warning("Inicia metodo validarContratosSolicitud");
        return resultado;
    }
    
    public BigDecimal obtenerSumaMontoContratos(Long idSolicitud){
        logger.warning("Inicio metodo obtenerSumaMontoContratos");
        BigDecimal sumaMontos = null;
        
        if(null == idSolicitud){
            logger.warning("idSolicitud llego NULL");
            return sumaMontos;
        }
        
        Row row = null;
        FenixGestorDesembolsosAMImpl fenixGestorDesembolsosAM =
            (FenixGestorDesembolsosAMImpl) this.getApplicationModule();
        ViewCriteria criteria = null;
        
        criteria = this.getViewCriteriaManager().getViewCriteria("TreSolicitudLineaCreditoPorSolicitudVOCriteria");
        criteria.ensureVariableManager().setVariableValue("pIdSolicitud", idSolicitud);
        applyViewCriteria(criteria);
        executeQuery();
        
        logger.warning("Registros de contratos encontrados para la solicitud: " + getEstimatedRowCount());
        if(getEstimatedRowCount() > 0){
            BigDecimal montoDesembolsar = null;;
            RowSetIterator iter = createRowSetIterator(null);
            if (null != iter) {
                iter.reset();
                while (iter.hasNext()) {
                    row = iter.next();
                    if (null != row) {
                        if (null != row.getAttribute("IdContratoDesembolso")) {
                            Long idContrato = null;
                            try{
                                idContrato = (Long) row.getAttribute("IdContratoDesembolso");
                            }catch(Exception e){
                                logger.warning("No se pudo obtener el idContrato.");
                            }
                            
                            montoDesembolsar =
                                fenixGestorDesembolsosAM.getContratoDesembolsoQueryVO().obtenerMontoDesembolsarContrato(idContrato);
                            
                            if(null != sumaMontos){
                                if(null != montoDesembolsar){
                                    sumaMontos = sumaMontos.add(montoDesembolsar);
                                }
                            }else{
                                sumaMontos = montoDesembolsar;
                            }
                        } else {
                            logger.warning("IdContrato es NULL.");
                        }
                    } else {
                        logger.warning("Row es NULL.");
                    }
                }
            } else {
                logger.warning("Iterador vacio.");
            }
            iter.closeRowSetIterator();
        }
        
        this.getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoPorSolicitudVOCriteria");
        
        logger.warning("Termina metodo obtenerSumaMontoContratos. MontoTotal: " + sumaMontos);
        return sumaMontos;
    }
    
    public Boolean obtenerIdContratoSolicitudCambiarEstado(Long idSolicitud, Long idOperacion){
        logger.warning("Inicio metodo obtenerIdContratoSolicitudCambiarEstado "+idOperacion);
        Boolean resultado = Boolean.FALSE;
        Boolean esCambioEstado = Boolean.FALSE;
        //Boolean requiereValidacionAsignacion = Boolean.FALSE;
        
        if(null == idSolicitud || null == idOperacion){
            logger.warning("Parametros requeridos son NULL "+idOperacion);
            return resultado;
        }
        
        Row row = null;
        FenixGestorDesembolsosAMImpl fenixGestorDesembolsosAM =
            (FenixGestorDesembolsosAMImpl) this.getApplicationModule();
        ViewCriteria criteria = null;
        
        criteria = this.getViewCriteriaManager().getViewCriteria("TreSolicitudLineaCreditoPorSolicitudVOCriteria");
        criteria.ensureVariableManager().setVariableValue("pIdSolicitud", idSolicitud);
        applyViewCriteria(criteria);
        executeQuery();
        
        logger.warning("Registros de contratos encontrados para la solicitud: " + getEstimatedRowCount()+" - "+idOperacion);
        if(getEstimatedRowCount() > 0){
            RowSetIterator iter = createRowSetIterator(null);
            //fenixGestorDesembolsosAM.getContratoDesembolsoVO().ejecutarQuerty();
            
            try{
                //requiereValidacionAsignacion = fenixGestorDesembolsosAM.getSolicitudDesembolsoVO().requiereValidacionAsignacionRecursos(idSolicitud, idOperacion);
                logger.warning("Cambiando estado de contratos SIN error en servicio de verificarValidacionAsignacion."+idOperacion);
                if (null != iter) {
                    iter.reset();
                    while (iter.hasNext()) {
                        row = iter.next();
                        if (null != row) {
                            if (null != row.getAttribute("IdContratoDesembolso")) {
                                Long idContrato = null;
                                try{
                                    idContrato = (Long) row.getAttribute("IdContratoDesembolso");
                                }catch(Exception e){
                                    logger.warning("No se pudo obtener el idContrato. "+idOperacion);
                                }
                                
                                if(null != idContrato){
                                    logger.warning("Antes cambiado el estado del contrato "+idOperacion+" contrato "+idContrato);
                                    resultado = fenixGestorDesembolsosAM.getContratoDesembolsoVO().cambiarEstadoContratoValidacion(idContrato, Boolean.TRUE);                                    
                                    if(resultado){
                                        logger.warning("Se ha cambiado el estado del contrato "+idOperacion);
                                        
                                        logger.warning("Realizando COMMIT para el cambio de estado de los contratos a Validacion de recursos. "+idOperacion);
                                        try{
                                            getDBTransaction().commit();
                                            logger.warning("primer commit exitoso "+idOperacion);
                                        }catch(Exception ex){
                                            logger.warning("error en commit para el metodo obtenerIdContratoSolicitudCambiarEstado:"+ex);
                                            try{
                                                getDBTransaction().commit();
                                                logger.warning("segundo commit exitoso "+idOperacion);
                                            }catch(Exception ex1){
                                                logger.warning("No se pudo realizar el commit correctamente. Ejecutando ROLLBACK.", ex1);
                                                resultado = Boolean.FALSE;
                                            
                                                if(fenixGestorDesembolsosAM.getContratoDesembolsoVO().getCurrentRow() != null){
                                                    fenixGestorDesembolsosAM.getContratoDesembolsoVO().getCurrentRow().refresh(Row.REFRESH_WITH_DB_FORGET_CHANGES);
                                                }
                                                break;
                                            }
                                        }
                                    }else{
                                        if(fenixGestorDesembolsosAM.getContratoDesembolsoVO().getCurrentRow() != null){
                                            fenixGestorDesembolsosAM.getContratoDesembolsoVO().getCurrentRow().refresh(Row.REFRESH_WITH_DB_FORGET_CHANGES);
                                        }
                                        break;
                                    }
                                }else{
                                    logger.warning("El idContrato es NULL. "+idOperacion);
                                }
                            } else {
                                logger.warning("IdContrato es NULL. "+idOperacion);
                            }
                        } else {
                            logger.warning("Row es NULL. "+idOperacion);
                        }
                    }
                } else {
                    logger.warning("Iterador vacio.");
                }
            }catch(Exception e){
                logger.warning("Cambiando estado de contratos CON error en servicio de verificarValidacionAsignacion. "+idOperacion);
                    if (null != iter) {
                        iter.reset();
                        while (iter.hasNext()) {
                            row = iter.next();
                            if (null != row) {
                                if (null != row.getAttribute("IdContratoDesembolso")) {
                                    Long idContrato = null;
                                    try{
                                        idContrato = (Long) row.getAttribute("IdContratoDesembolso");
                                    }catch(Exception ex){
                                        logger.warning("No se pudo obtener el idContrato. "+idOperacion);
                                    }
                                    
                                    if(null != idContrato){
                                        resultado = fenixGestorDesembolsosAM.getContratoDesembolsoVO().cambiarEstadoContratoValidacion(idContrato, Boolean.TRUE);
                                        if(resultado){
                                            logger.warning("Se ha cambiado el estado del contrato "+idOperacion);
                                            
                                            logger.warning("Realizando COMMIT para el cambio de estado de los contratos a Validacion de recursos. "+idOperacion);
                                            try{
                                                getDBTransaction().commit();
                                                logger.warning("primer commit exitoso "+idOperacion);
                                            }catch(Exception ex){
                                                logger.warning("error en el primer commit : "+ex);
                                                try{
                                                    getDBTransaction().commit();
                                                    logger.warning("segundo commit exitoso "+idOperacion);
                                                }catch(Exception ex1){
                                                    logger.warning("No se pudo realizar el commit correctamente. Ejecutando ROLLBACK."+ex1);
                                                    resultado = Boolean.FALSE;
                                                
                                                    if(fenixGestorDesembolsosAM.getContratoDesembolsoVO().getCurrentRow() != null){
                                                        fenixGestorDesembolsosAM.getContratoDesembolsoVO().getCurrentRow().refresh(Row.REFRESH_WITH_DB_FORGET_CHANGES);
                                                    }
                                                    break;
                                                }
                                            }
                                        }else{
                                            if(fenixGestorDesembolsosAM.getContratoDesembolsoVO().getCurrentRow() != null){
                                               fenixGestorDesembolsosAM.getContratoDesembolsoVO().getCurrentRow().refresh(Row.REFRESH_WITH_DB_FORGET_CHANGES);
                                            }
                                            break;
                                        }
                                    }else{
                                        logger.warning("El idContrato es NULL.");
                                    }
                                } else {
                                    logger.warning("IdContrato es NULL.");
                                }
                            } else {
                                logger.warning("Row es NULL.");
                            }
                        }                        
                    } else {
                        logger.warning("Iterador vacio.");
                    }
            }
            if(iter!=null){
                iter.closeRowSetIterator();
            }
        }
        
        this.getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoPorSolicitudVOCriteria");
        logger.warning("Termina metodo obtenerIdContratoSolicitudCambiarEstado "+idOperacion);
        return resultado;
    }
    
    public Boolean validarMontoContratoVsLineaPasiva(Long idSolicitud){
        logger.warning("Inicia metodo validarMontoContratoVsLineaPasiva");
        Boolean esIgual = Boolean.FALSE;
        
        if(null == idSolicitud){
            logger.warning("Parametro idSolicitud requerido es NULL.");
            return esIgual;
        }
        
        Row row = null;
        FenixGestorDesembolsosAMImpl fenixGestorDesembolsosAM =
            (FenixGestorDesembolsosAMImpl) this.getApplicationModule();
        ViewCriteria criteria = null;
        
        criteria = this.getViewCriteriaManager().getViewCriteria("TreSolicitudLineaCreditoPorSolicitudVOCriteria");
        criteria.ensureVariableManager().setVariableValue("pIdSolicitud", idSolicitud);
        applyViewCriteria(criteria);
        executeQuery();
        
        logger.warning("Registros de contratos encontrados para la solicitud: " + getEstimatedRowCount());
        if(getEstimatedRowCount() > 0){
            RowSetIterator iter = createRowSetIterator(null);
            fenixGestorDesembolsosAM.getContratoDesembolsoVO().ejecutarQuerty();
            if (null != iter) {
                iter.reset();
                while (iter.hasNext()) {
                    row = iter.next();
                    if (null != row) {
                        if (null != row.getAttribute("IdContratoDesembolso")) {
                            Long idContrato = null;
                            try{
                                idContrato = (Long) row.getAttribute("IdContratoDesembolso");
                            }catch(Exception e){
                                logger.warning("No se pudo obtener el idContrato.");
                            }
                            
                            if(null != idContrato){
                                esIgual = fenixGestorDesembolsosAM.getTreLineaPasivaVO().montoDesembolsarLineaPasivaVsContrato(idContrato);
                                if(esIgual){
                                    logger.warning("Igualdad encontrada.");
                                }else{
                                    logger.warning("Igualdad NO encontrada.");
                                    break;
                                }
                            }else{
                                logger.warning("El idContrato es NULL.");
                            }
                        } else {
                            logger.warning("IdContrato es NULL.");
                        }
                    } else {
                        logger.warning("Row es NULL.");
                    }
                }
            } else {
                logger.warning("Iterador vacio.");
            }
            iter.closeRowSetIterator();
        }
        
        this.getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoPorSolicitudVOCriteria");
        
        logger.warning("Los montos de contrato y de la linea pasiva son iguales: " + esIgual);
        logger.warning("Termina metodo validarMontoContratoVsLineaPasiva");
        return esIgual;
    }

    public Boolean eliminarRegistroByIdContrato(Long idContrato){
      logger.warning("Inf, inicia metodo eliminarRegistroByIdContrato en treSolicitudLineaCredito");
        Boolean respuesta = Boolean.FALSE;
        ViewCriteria criteria = null;
        Row fila = null;
         if(idContrato == null){
             logger.warning("El parametro idContrato es requerido para la busqueda");                           
         }else{  
             
             try{                      
                 criteria = getViewCriteriaManager().getViewCriteria("FiltrarRegistroByIdContrato");
                 criteria.ensureVariableManager().setVariableValue("pIdContratoDesembolso", idContrato);
                 applyViewCriteria(criteria);
                 executeQuery();
             
                 if(getEstimatedRowCount() > 0){               
                    setCurrentRow(first());     
                     fila = getCurrentRow();                     
                     fila.remove();    
                     respuesta = Boolean.TRUE;
                     
                 }else{
                      logger.warning("***El row recuperado es Null no hay coincidencias en la busqueda"); 
                      getViewCriteriaManager().removeApplyViewCriteriaName("FiltrarRegistroByIdContrato");          
                      executeQuery();                                        
                     }
                 
             }catch(Exception e){
                 logger.log(ADFLogger.WARNING, "*** Error al Bucar por idContrato  ->" + e.getClass() + ":" + e.getMessage());     
             }finally{
                getViewCriteriaManager().removeApplyViewCriteriaName("FiltrarRegistroByIdContrato");                          
                 executeQuery();
             }    
         }
     
        
       logger.warning("Inf, inicia metodo eliminarRegistroByIdContrato en treSolicitudLineaCredito");
        return respuesta;
     }
    
    public Long obtenerLineaCreditoPorIdContrato(Long idContrato){
        logger.warning("Inicia metodo obtenerLineaCreditoPorIdContrato");
        Long idLineaCredito = null;
        ViewCriteria vc = null;
        Row row = null;
        
        logger.warning("IdContrato: " + idContrato);
        if(null == idContrato){
            logger.warning("Parametro requerido idContrato es NULL.");
            return idLineaCredito;
        }
        
        setpIdContratoDesembolso(idContrato);
        try{
            vc = getViewCriteriaManager().getViewCriteria("TreSolicitudLineaCreditoVOCriteria");
            applyViewCriteria(vc);
            executeQuery();
        }catch(Exception e){
            logger.warning("ERROR al ejecutar VC.", e);
            getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoVOCriteria");          
        }
        
        logger.warning("Registros encontratdos: " + getEstimatedRowCount());
        if(getEstimatedRowCount() > 0){
            row = first();
            if(null != row){
                try{
                    idLineaCredito = (Long) row.getAttribute("IdLinea");
                }catch(Exception e){
                    logger.warning("ERROR al obtener linea de credito de row.", e);
                }
            }else{
                logger.warning("El row es NULL.");
            }
        }else{
            logger.warning("No hay registros encontrados para el contratos: " + idContrato);
        }
        
        getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoVOCriteria");          
        executeQuery();
        
        logger.warning("IdLineaCredito obtenida: " + idLineaCredito);
        logger.warning("Termina metodo obtenerLineaCreditoPorIdContrato");
        return idLineaCredito;
    }
    
    
    
    public Long obtenerSolicitudByIdDesembolso(Long idContrato){
         logger.warning("*Inf, Inicia metodo obtenerSolicitudByIdDesembolso");
            Long idSolicitud = null;
            ViewCriteria vc = null;
            Row row = null;
            
            logger.warning("*Inf, IdContrato: " + idContrato);
            if(null == idContrato){
                logger.warning("***Error ,Parametro requerido idContrato es NULL.");
                return idSolicitud;
            }
        
            setpIdContratoDesembolso(idContrato);
            
            try{
                vc = getViewCriteriaManager().getViewCriteria("TreSolicitudLineaCreditoVOCriteria");
                applyViewCriteria(vc);
                executeQuery();
            }catch(Exception e){
                logger.warning("ERROR al ejecutar VC.", e);
                getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoVOCriteria");
            }
            
            logger.warning("*Inf, Registros encontratdos: " + getEstimatedRowCount());
            if(getEstimatedRowCount() > 0){
                row = first();
                if(null != row){
                    try{
                        idSolicitud = (Long) row.getAttribute("IdSolicitud");
                    }catch(Exception e){
                        logger.warning("*Inf, ERROR al obtener linea de credito de row.", e);
                    }
                }else{
                    logger.warning("*Inf, El row es NULL.");
                }
            }else{
                logger.warning("*Inf, No hay registros de solicitudes encontrados para el contratos: " + idContrato);
            }
            
            getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoVOCriteria");
            executeQuery();
            
            logger.warning("*Inf, idSolicitud recuperado: " + idSolicitud);
            logger.warning("*Inf, Termina metodo obtenerSolicitudByIdDesembolso");
            return idSolicitud;
        
        }
    
    public Boolean cambiarMonedaContratosSolicitud(Long idSolicitud){
        logger.warning("Inicia metodo cambiarMonedaContratosSolicitud");
        Boolean resultado = Boolean.FALSE;        
        Row rowPrimerContrato = null;
        FenixGestorDesembolsosAMImpl fenixGestorDesembolsosAM =
            (FenixGestorDesembolsosAMImpl) this.getApplicationModule();
        ViewCriteria criteria = null;
        Long idPrimerContrato = null;
        Integer idTipoMonedaPrimerContrato = null;
        
        criteria = this.getViewCriteriaManager().getViewCriteria("TreSolicitudLineaCreditoPorSolicitudVOCriteria");
        criteria.ensureVariableManager().setVariableValue("pIdSolicitud", idSolicitud);
        applyViewCriteria(criteria);
        executeQuery();
        
        logger.warning("Registros de contratos encontrados para la solicitud: " + getEstimatedRowCount());
        if(getEstimatedRowCount() > 0){
            RowSetIterator iter = createRowSetIterator(null);
            fenixGestorDesembolsosAM.getContratoDesembolsoVO().ejecutarQuerty();
            
            logger.warning("Buscando primer contrato de la solicitud.");
            idPrimerContrato = fenixGestorDesembolsosAM.getConsultarContratoTreSolicitudLineaCreditoVO().obtenerPrimerContratoActivo(idSolicitud);
            if(null != idPrimerContrato){
                rowPrimerContrato = fenixGestorDesembolsosAM.getContratoDesembolsoVO().obtenerContratoPorId(idPrimerContrato);
                if(null != rowPrimerContrato){
                    try{
                        idTipoMonedaPrimerContrato = (Integer) rowPrimerContrato.getAttribute("IdTcaTipoMoneda");
                    }catch(Exception e){
                        logger.warning("ERROR al recuperar moneda de primer contrato de la solicitud.");
                    }
                    
                    if(null == idTipoMonedaPrimerContrato){
                        return resultado;
                    }
                }else{
                    logger.warning("No se encontró registro para el primer contrato activo de la solicitud.");
                    return resultado;
                }
            }else{
                logger.warning("No se encontró primer contrato de la solicitud.");
                return resultado;
            }
            
            try{
                Row row = null;
                Long idContrato = null;
                logger.warning("Cambiando moneda de contratos.");
                if (null != iter) {
                    iter.reset();
                    while(iter.hasNext()) {
                        row = iter.next();
                        if (null != row) {
                            if (null != row.getAttribute("IdContratoDesembolso")) {
                                try{
                                    idContrato = (Long) row.getAttribute("IdContratoDesembolso");
                                }catch(Exception e){
                                    logger.warning("No se pudo obtener el idContrato.");
                                }
                                
                                if(null != idContrato){
                                    logger.warning("Cambiando moneda de contrato: " + idContrato);
                                    resultado = fenixGestorDesembolsosAM.getContratoDesembolsoVO().guardarMonedaPrimerContrato(idContrato, idTipoMonedaPrimerContrato);
                                    if(resultado){
                                        logger.warning("Se ha cambiado la moneda del contrato");
                                    }else{
                                        logger.warning("No se pudo cambiar la moneda del contrato.");
                                        break;
                                    }
                                }else{
                                    logger.warning("El idContrato es NULL.");
                                }
                            } else {
                                logger.warning("IdContrato es NULL.");
                            }
                        } else {
                            logger.warning("Row es NULL.");
                        }
                    }
                } else {
                    logger.warning("Iterador vacio.");
                }
            }catch(Exception e){
                logger.warning("Cambiando moneda de contratos.");
                
            }
            iter.closeRowSetIterator();
        }
        
        this.getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoPorSolicitudVOCriteria");
        logger.warning("Termina metodo cambiarMonedaContratosSolicitud");
        return resultado;
    }
    
    public List validarFechasInicioDesembolsoTotalidadRecursosPorSolicitud(Long idOperacion, Long idSolicitud){
        logger.warning("Inicia metodo validarFechasInicioDesembolsoTotalidadRecursosPorSolicitud.");
        Boolean esValido = Boolean.FALSE;
        Boolean esContratoValido = Boolean.FALSE;
        List listaMensajesError = new ArrayList();
        String mensajeError = null;
        ViewCriteria criteria = null;
        FenixGestorDesembolsosAMImpl fenixGestorDesembolsosAM = (FenixGestorDesembolsosAMImpl) this.getApplicationModule();
        
        if(null == idOperacion || null == idSolicitud){
            logger.warning("Parametros requeridos son NULL. IdOperacion: " + idOperacion + ", IdSolicitud: " + idSolicitud);
            mensajeError = FenixModelConstants.ERROR_VALIDAR_REGLAS_FECHAS;
            listaMensajesError.add(mensajeError);
            return listaMensajesError;
        }
        
        try{
            criteria = this.getViewCriteriaManager().getViewCriteria("TreSolicitudLineaCreditoPorSolicitudVOCriteria");
            criteria.ensureVariableManager().setVariableValue("pIdSolicitud", idSolicitud);
            applyViewCriteria(criteria);
            executeQuery();
        }catch(Exception e){
            logger.warning("ERROR al ejecutar viewCriteria TreSolicitudLineaCreditoPorSolicitudVOCriteria.", e);
            mensajeError = FenixModelConstants.ERROR_VALIDAR_REGLAS_FECHAS;
            listaMensajesError.add(mensajeError);
            this.getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoPorSolicitudVOCriteria");
            return listaMensajesError;
        }
        
        logger.warning("Registros encontrados: " + getEstimatedRowCount());
        if(getEstimatedRowCount() > 0){
            Long idContrato = null, idLineaCredito = null;
            for(Row row : getAllRowsInRange()){
                try{
                    idContrato = (Long) row.getAttribute("IdContratoDesembolso");
                }catch(Exception e){
                    logger.warning("ERROR al recuperar el IdContratoDesembolso del registro. ", e);
                    mensajeError = FenixModelConstants.ERROR_VALIDAR_REGLAS_FECHAS;
                    listaMensajesError.add(mensajeError);
                    this.getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoPorSolicitudVOCriteria");
                    return listaMensajesError;
                }
                
                logger.warning("Validando que contrato no sea DESESTIMADO.");
                fenixGestorDesembolsosAM.getContratoDesembolsoVO().executeQuery();
                esContratoValido = 
                    fenixGestorDesembolsosAM.getContratoDesembolsoVO().encontrarContratosDesestimados(idContrato);
                
                if(esContratoValido){
                    try{
                        idLineaCredito = (Long) row.getAttribute("IdLinea");
                    }catch(Exception e){
                        logger.warning("ERROR al recuperar IdLinea del registro.", e);
                        mensajeError = FenixModelConstants.ERROR_VALIDAR_REGLAS_FECHAS;
                        listaMensajesError.add(mensajeError);
                        this.getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoPorSolicitudVOCriteria");
                        return listaMensajesError;
                    }
                    
                    logger.warning("Invocando metodo de validacion de fechas de inicio desembolso y de la totalidad de los recursos.");
                    listaMensajesError =
                        fenixGestorDesembolsosAM.getContratoDesembolsoVO().recuperaFechasTerminosLineaDesembolso(idOperacion, idLineaCredito);
                    
                    if(listaMensajesError.size() > 0 && !listaMensajesError.isEmpty()){
                        logger.warning("Se encontro algun mensaje de error en las validaciones.");
                        break;
                    }
                }else{
                    logger.warning("El contrato es Desestimado. Linea de crédito no tomada en cuenta.");
                }
            }
        }else{
            logger.warning("No se encontraron registros de TreSolicitudLineaCredito para la solicitud: " + idSolicitud);
            mensajeError = FenixModelConstants.ERROR_VALIDAR_REGLAS_FECHAS;
            listaMensajesError.add(mensajeError);
        }
        
        this.getViewCriteriaManager().removeApplyViewCriteriaName("TreSolicitudLineaCreditoPorSolicitudVOCriteria");        
        logger.warning("Termina metodo validarFechasInicioDesembolsoTotalidadRecursosPorSolicitud.");
        return listaMensajesError;
    }
    
    /**
     * Returns the variable value for pIdContratoDesembolso.
     * @return variable value for pIdContratoDesembolso
     */
    public Long getpIdContratoDesembolso() {
        return (Long) ensureVariableManager().getVariableValue("pIdContratoDesembolso");
    }

    /**
     * Sets <code>value</code> for variable pIdContratoDesembolso.
     * @param value value to bind as pIdContratoDesembolso
     */
    public void setpIdContratoDesembolso(Long value) {
        ensureVariableManager().setVariableValue("pIdContratoDesembolso", value);
    }


    /**
     * Returns the variable value for pIdSolicitud.
     * @return variable value for pIdSolicitud
     */
    public Long getpIdSolicitud() {
        return (Long) ensureVariableManager().getVariableValue("pIdSolicitud");
    }

    /**
     * Sets <code>value</code> for variable pIdSolicitud.
     * @param value value to bind as pIdSolicitud
     */
    public void setpIdSolicitud(Long value) {
        ensureVariableManager().setVariableValue("pIdSolicitud", value);
    }
}

