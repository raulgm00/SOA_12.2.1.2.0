package org.bcie.fenix.view.adquisiciones.noobjecion;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.view.adquisiciones.constantes.ResultadoProcesoEnum;
import org.bcie.fenix.view.adquisiciones.constantes.TipoObjecionEnum;

public class NoObjecionBean implements Serializable {
    @SuppressWarnings("compatibility:1160092950518462238")
    private static final long serialVersionUID = 1L;

    public NoObjecionBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    private Long idAdquisiones;
    private Boolean lectura;
    private Integer tarea;
    private static ADFLogger logger = null;
    private Boolean categoria1;
    private Boolean categoria2;
    private Boolean categoria3;
    private Boolean obtuvo;
    private Integer modalidad;
    private Long precargadoNoObjecion;
    private Boolean habilitarCampos;
    private Boolean pasoAprobacion;
    private String loginUsuario;
    private Integer tipoObjecion;
    private boolean oficialParticipa = Boolean.FALSE;
    private boolean abogadoParticipa = Boolean.FALSE;
    private boolean analistaParticipa = Boolean.FALSE;
    
    private boolean oficialObligatorio = Boolean.FALSE;
    private boolean abogadoObligatorio = Boolean.FALSE;
    private boolean analistaObligatorio = Boolean.FALSE;
    
    private Boolean mostrarBotonGuardar = null;
    private String fileNamePlantilla = null;
    private String btnDescargarPlantillaDisabled = "false";
    private String pathPLantillaNoObjecion = "";

    private Boolean esFinanciero = Boolean.TRUE;
    
    private Boolean esEstadoCompletado;
    
    //agregan variables para incidencia FNXII-7223

    private Boolean esResponsableOperacion;
    
    private Boolean esGerentePais;

    public void precarga() {
        logger.warning("Ingresa metodo precarga NoObjecion TF");
        long startTime = System.currentTimeMillis();                    
                        startTime = System.currentTimeMillis();
                logger.warning("Tiempo de inicio carga no objecion del: "
                 + startTime);
        setIdAdquisiones(null);
        precargadoNoObjecion=null;
        habilitarCampos=Boolean.TRUE;
        String state = null;
        
        JSFUtils.setExpressionValue("#{sessionScope.noObjecion}", habilitarCampos);
        
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pTarea}") != null) {
                setTarea(Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pTarea}").toString()));
                logger.warning("Tarea " + getTarea()); 
                if(tarea.compareTo(FenixConstants.PA09_REALIZAR_AJUSTES)==0){
                    habilitarCampos=Boolean.FALSE;
                    }
            } else {
                logger.warning("El valor de la Tarea es nulo."); 
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("Tarea no obtenida");
        }
        
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pAprobado}") != null) {
                setPasoAprobacion(Boolean.parseBoolean(JSFUtils.resolveExpression("#{pageFlowScope.pAprobado}").toString()));
                logger.warning("Lectura " + getLectura());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("el obtenerAprobacion no fue obtenida");
        }
        
        try{
            if(null!=JSFUtils.resolveExpression("#{pageFlowScope.pLoginUsuario}")){
                    loginUsuario = (String) JSFUtils.resolveExpression("#{pageFlowScope.pLoginUsuario}");
                }

        }catch(Exception ex){
        logger.warning("login no obtenido");
        }
        
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pLectura}") != null) {
                setLectura(Boolean.parseBoolean(JSFUtils.resolveExpression("#{pageFlowScope.pLectura}").toString()));
                logger.warning("Lectura " + getLectura());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("Lectura no obtenida");
        }

        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pAdquisicion}") != null) {
                setIdAdquisiones(Long.parseLong(JSFUtils.resolveExpression("#{pageFlowScope.pAdquisicion}").toString()));
                logger.warning("id pAdquisicion  " + getIdAdquisiones());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            setIdAdquisiones(null);
            logger.warning("idAdquisiones no obtenida");
        }
        
        try {
            if (null == getIdAdquisiones()) {
                if (JSFUtils.resolveExpression("#{pageFlowScope.pIdAdquisicion}") != null) {
                    setIdAdquisiones(Long.parseLong(JSFUtils.resolveExpression("#{pageFlowScope.pIdAdquisicion}").toString()));
                    logger.warning("id pIdAdquisicion seleccionada  " + getIdAdquisiones());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            setIdAdquisiones(null);
            logger.warning("idAdquisiones seleccionada no obtenida");
        }
        
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pNoObjecion}") != null) {
                setPrecargadoNoObjecion(Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pNoObjecion}").toString()));
                logger.warning("pNoObjecion " + getPrecargadoNoObjecion());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("pNoObjecion no obtenida");
        }
        
        Map respuesta = new HashMap<String, Object>();
        respuesta=cargaObjecion();
        
        Boolean opcion;
        logger.warning("lectura= "+ lectura);
        logger.warning("tarea= "+ tarea);
        logger.warning("idAdquisiones= "+ idAdquisiones);
        
        logger.warning("idNoObjecion= "+ (Long)respuesta.get("claveobjecion"));
        logger.warning("idTcaTipoNoObjecion= "+ (Integer)respuesta.get("tipoobjecion"));
        logger.warning("resultadoobjecion= "+ (Integer)respuesta.get("resultadoobjecion"));
        
        logger.warning("resultado= "+ (Boolean)respuesta.get("resultado"));
        logger.warning("categoria1= "+ (Boolean)respuesta.get("categoria1"));
        logger.warning("categoria2= "+ (Boolean)respuesta.get("categoria2"));
        logger.warning("categoria3= "+ (Boolean)respuesta.get("categoria3"));
        logger.warning("modalidad= "+ (Integer)respuesta.get("modalidad"));
        if(null!=(Boolean)respuesta.get("resultado")){
                obtuvo=(Boolean)respuesta.get("resultado");
            }
        if(null!=(Integer)respuesta.get("tipoobjecion")){
                tipoObjecion=(Integer)respuesta.get("tipoobjecion");
            }
        if(null!=(Boolean)respuesta.get("categoria1")){
                categoria1=(Boolean)respuesta.get("categoria1");
            }
        if(null!=(Boolean)respuesta.get("categoria2")){
                categoria2=(Boolean)respuesta.get("categoria2");
            }
        if(null!=(Boolean)respuesta.get("categoria3")){
                categoria3=(Boolean)respuesta.get("categoria3");
            }
        if(null!=(Integer)respuesta.get("modalidad")){
                modalidad=(Integer)respuesta.get("modalidad");
            }
        
        /*Se atiende incidencia FNXII-5189*/
        try{
            Boolean obtenerEsPrevia = (Boolean)respuesta.get("previa");
            logger.warning("Valor obtenerEsPrevia= " + obtenerEsPrevia);
            if(obtenerEsPrevia != null && obtenerEsPrevia.compareTo(true) == 0)
                setMostrarBotonGuardar(Boolean.TRUE);
            else
                setMostrarBotonGuardar(Boolean.FALSE);
            logger.warning("Valor mostrarBotonGuardar= " + getMostrarBotonGuardar());
        }
        catch(Exception e){
            logger.warning("Error al obtener el valor esPrevia..." + e.getClass() + e.getMessage());
        }
        /*Finaliza incidencia FNXII-5189*/
        
        // Se inicializan las variables para la precarga de concursantes
        Long idNoObjecion = null;
        Integer idTcaTipoNoObjecion = null;
        Integer resultadoProceso = null;
        if (respuesta != null) {
            if (respuesta.get("claveobjecion") != null) {
                idNoObjecion = (Long)respuesta.get("claveobjecion");
                JSFUtils.setExpressionValue("#{sessionScope.idNoObjecion}", idNoObjecion);
                setPrecargadoNoObjecion(idNoObjecion);
            }
            if (respuesta.get("tipoobjecion") != null) {
                idTcaTipoNoObjecion = (Integer)respuesta.get("tipoobjecion");
            }
            
            if (respuesta.get("resultadoobjecion") != null) {
                resultadoProceso = (Integer)respuesta.get("resultadoobjecion");
            }
            long startTime2 = System.currentTimeMillis();
            logger.warning("Tiempo de inicio de concursantes: " + startTime2);
            logger.warning("Tiempo de diferencia con el inicio de la no objecion "
            + (startTime2 - startTime)/1000 + " segundos");
            // Se solicita la precarga de concursantes
            precargaConcursantes(idNoObjecion, idTcaTipoNoObjecion, resultadoProceso);
            
            long endTime2 = System.currentTimeMillis();
            logger.warning("Tiempo de fin de inicio de los concursantes: " + endTime2);
            logger.warning("Tiempo de respuesta del inicio al fin de la carga de concursantes "
            + (endTime2 - startTime2)/1000 + " segundos");
            
            long startTime3 = System.currentTimeMillis();
            logger.warning("Tiempo de inicio de concursantes: " + startTime3);
            logger.warning("Tiempo de diferencia con el inicio de la no objecion "
            + (startTime3 - startTime)/1000 + " segundos");
            datosParticipantesMapping();
            long endTime3 = System.currentTimeMillis();
            logger.warning("Tiempo de fin de inicio de los concursantes: " + endTime3);
            logger.warning("Tiempo de respuesta del inicio al fin de la carga de concursantes "
            + (endTime3 - startTime3)/1000 + " segundos");
        }
        // se recarga la tabla de no objecion
        if(null!=modalidad){
                long startTime4 = System.currentTimeMillis();
                logger.warning("Tiempo de inicio de carga modalidad: " + startTime4);
                logger.warning("Tiempo de diferencia con el inicio de la no objecion "
                + (startTime4 - startTime)/1000 + " segundos");
                cargaModalidad(modalidad);
                long endTime4 = System.currentTimeMillis();
                logger.warning("Tiempo de fin de inicio de los concursantes: " + endTime4);
                logger.warning("Tiempo de respuesta del la carga modalidad "
                + (endTime4 - startTime4)/1000 + " segundos");
            }
        if(null!=idNoObjecion){
                long startTime5 = System.currentTimeMillis();
                logger.warning("Tiempo de inicio de carga del elemento seleccionado: " + startTime5);
                logger.warning("Tiempo de diferencia con el inicio de la no objecion "
                + (startTime5 - startTime)/1000 + " segundos");
                changeNoObjecionCurrentRow(idNoObjecion, modalidad);
                long endTime5 = System.currentTimeMillis();
                logger.warning("Tiempo de fin de inicio de los concursantes: " + endTime5);
                logger.warning("Tiempo de respuesta del elemento seleccionado: "
                + (endTime5 - startTime5)/1000 + " segundos");
            }
        //Metodo para validar que el sector es publico no financiero
        validarSectorPublicoNoFinanciero();
        
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pState}") != null) {
                state = (String) JSFUtils.resolveExpression("#{pageFlowScope.pState}");
                logger.warning("state :" + state);
                setEsEstadoCompletado(state.equals("COMPLETED") ? Boolean.TRUE : Boolean.FALSE);
            }else{
                logger.warning("pState es nulo");
                setEsEstadoCompletado(Boolean.FALSE);
            }
        } catch (Exception ex) {
            logger.severe("Error al recuperar #{pageFlowScope.pState} :",ex);
            logger.warning("pState no obtenido");
            setEsEstadoCompletado(Boolean.FALSE);
        }
        
        logger.warning("esEstadoCompletado No objecion : " + getEsEstadoCompletado());
        
        logger.warning("habilitarCampos :"+habilitarCampos);
        
        //cambios relacionado con incidencia FNXII-7223
        try{
            if (JSFUtils.resolveExpression("#{pageFlowScope.esResponsableOperacion}") != null) {
                logger.warning("#{pageFlowScope.esResponsableOperacion} no es nulo");
                logger.warning("#{pageFlowScope.esResponsableOperacion} :"+JSFUtils.resolveExpression("#{pageFlowScope.esResponsableOperacion}"));
                setEsResponsableOperacion(Boolean.parseBoolean(JSFUtils.resolveExpression("#{pageFlowScope.esResponsableOperacion}").toString()));
            }else{
                logger.warning("#{pageFlowScope.esResponsableOperacion} es nulo");
                setEsResponsableOperacion(Boolean.TRUE);
            }
        }catch(Exception e){
            logger.severe("Error obtener esResponsableOperacion :",e);
        }
        
        logger.warning("esResponsableOperacion :" + getEsResponsableOperacion());  
        
        try{
            if (JSFUtils.resolveExpression("#{pageFlowScope.esGerentePais}") != null) {
                logger.warning("#{pageFlowScope.esGerentePais} no es nulo");
                logger.warning("#{pageFlowScope.esGerentePais} :"+JSFUtils.resolveExpression("#{pageFlowScope.esGerentePais}"));
                setEsGerentePais(Boolean.parseBoolean(JSFUtils.resolveExpression("#{pageFlowScope.esGerentePais}").toString()));
            }else{
                logger.warning("#{pageFlowScope.esGerentePais} es nulo");
                setEsGerentePais(Boolean.TRUE);
            }
        }catch(Exception e){
            logger.severe("Error obtener esGerentePais :",e);
        }
        
        logger.warning("esGerentePais :" + getEsGerentePais());
        
        long endTime = System.currentTimeMillis();
        logger.warning("Tiempo de fin respuesta: " + endTime);
        logger.warning("Tiempo de respuesta del fin de la carga de la no objecion: "
        + (endTime - startTime)/1000 + " segundos");
    }
    
    private void validarSectorPublicoNoFinanciero(){
        logger.warning("Entra en validarSectorPublicoNoFinanciero");
        String idOperacion;
        BindingContainer binding = null;
        OperationBinding operBinding = null;
        try{
            binding = ADFUtils.getBindingContainer();
            
            if (JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null) {
                idOperacion = JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString();
                logger.warning("idOperacion " + idOperacion); 
                
                operBinding = binding.getOperationBinding("obtenerSectorMercado");
                operBinding.getParamsMap().put("idOperacion", idOperacion);
                operBinding.execute();
                
                if(null != operBinding.getResult()){
                    esFinanciero =  (Boolean)operBinding.getResult();
                    logger.warning("validarSectorPublicoNoFinanciero esFinanciero: " + esFinanciero);
                }
            } else {
                logger.warning("El valor de la operacion es nulo."); 
            }
            logger.warning("Sale en validarSectorPublicoNoFinanciero");
        }catch(Exception e){
            logger.warning("Error en validarSectorPublicoNoFinanciero.", e);
        }
    }
    
    public void precargaConcursantes() {
    }

    public Long getIdAdquisiones() {
        return idAdquisiones;
    }

    public void setIdAdquisiones(Long idAdquisiones) {
        this.idAdquisiones = idAdquisiones;
    }

    public Boolean getLectura() {
        return lectura;
    }

    public void setLectura(Boolean lectura) {
        this.lectura = lectura;
    }

    public Integer getTarea() {
        return tarea;
    }

    public void setTarea(Integer tarea) {
        this.tarea = tarea;
    }
    
    public Map cargaObjecion() {
        logger.warning("Ingresa metodo cargaObjecion");
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("precargarAdquisicion");
        operationBinding.getParamsMap().put("idAdquisicion", idAdquisiones);
        
        if (this.getTarea().compareTo(new Integer(65)) != 0) {
            operationBinding.getParamsMap().put("idNoObjecion", this.getPrecargadoNoObjecion());
        }
        
        HashMap<String, Object> result = (HashMap<String, Object>) operationBinding.execute();

        logger.warning("resultado= " + result);
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return null;
        }
        return result;
    }
    
    public void cargaModalidad(Integer modalidad) {
        //FenixConstants.TAREA_GESTOR_OPERACIONES
        if (null != getTarea() && getTarea().intValue() != FenixConstants.TAREA_GESTOR_OPERACIONES) {
            //Carga de la modalidad por tarea
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("precargaModalidadTarea");
            operationBinding.getParamsMap().put("modalidad", modalidad);
            operationBinding.getParamsMap().put("idNoObjecion", getPrecargadoNoObjecion());
            operationBinding.execute();
        } else {
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("precargaModalidad");
            operationBinding.getParamsMap().put("modalidad", modalidad);
            operationBinding.execute();
        }
    }
    
    /**
     * M&eacute;todo para establecer los par&aacute;metros where de las consultas de
     * concursantes
     * @author Jonathan Ruiz
     * @param idNoObjecion
     * @param idTcaTipoNoObjecion
     */
    public boolean precargaConcursantes(Long idNoObjecion, Integer idTcaTipoNoObjecion, Integer resultadoProceso) {
        
        logger.warning("Dentro de precargaConcursantes");
        logger.warning("idNoObjecion : "+idNoObjecion);
        logger.warning("idTcaTipoNoObjecion : " +idTcaTipoNoObjecion);
        logger.warning("resultadoProceso : " +resultadoProceso);
        boolean resultado = Boolean.FALSE;
        TipoObjecionEnum tipoObjecionEnum = null;
        
        // FIXME - Incluir el bloque en un try catch para cachar excepcion de precargaConcursantesPorPerfil
        if (null != idTcaTipoNoObjecion) {
            tipoObjecionEnum = TipoObjecionEnum.loockup(idTcaTipoNoObjecion);
            logger.warning("tipoObjecionEnum :" + tipoObjecionEnum);
            if (null != tipoObjecionEnum) {
                switch(tipoObjecionEnum) {
                case INFORME_RESULTADOS:
                    ResultadoProcesoEnum resultadoProcesoEnum = ResultadoProcesoEnum.loockup(resultadoProceso);
                    
                    if (resultadoProcesoEnum != null) {
                        switch(resultadoProcesoEnum) {
                        case ADJUDICADO:
                            logger.warning("Dentro opcion ADJUDICADO, value  : "+resultadoProcesoEnum);
                            precargaConcursantesPorPerfil(idNoObjecion, FenixModelConstants.ID_TIPO_PERFIL_OFERENTE);
                            precargaConcursantesPorPerfil(idNoObjecion, FenixModelConstants.ID_TIPO_PERFIL_ADJUDICATARIO);
                            // Limpiamos VO(s) no utilizadas(s)
                            precargaConcursantesPorPerfil(0L, FenixModelConstants.ID_TIPO_PERFIL_CONTRATADO);
                            break;
                        
                        case FRACASADO:
                            logger.warning("Dentro opcion FRACASADO, value  : "+resultadoProcesoEnum);
                            precargaConcursantesPorPerfil(idNoObjecion, FenixModelConstants.ID_TIPO_PERFIL_OFERENTE);
                            // Limpiamos VO(s) no utilizadas(s)
                            precargaConcursantesPorPerfil(0L, FenixModelConstants.ID_TIPO_PERFIL_ADJUDICATARIO);
                            precargaConcursantesPorPerfil(0L, FenixModelConstants.ID_TIPO_PERFIL_CONTRATADO);
                            break;
                        }
                    }
                    break;
                case CONTRATADO:
                    logger.warning("Dentro opcion CONTRATADO");
                    precargaConcursantesPorPerfil(idNoObjecion, FenixModelConstants.ID_TIPO_PERFIL_ADJUDICATARIO);
                    precargaConcursantesPorPerfil(idNoObjecion, FenixModelConstants.ID_TIPO_PERFIL_CONTRATADO);
                    // Limpiamos VO(s) no utilizadas(s)
                    precargaConcursantesPorPerfil(0L, FenixModelConstants.ID_TIPO_PERFIL_OFERENTE);
                    break;
                case RESULTADO_PROCESO:
                    logger.warning("Dentro opcion RESULTADO_PROCESO");
                    precargaConcursantesPorPerfil(idNoObjecion, FenixModelConstants.ID_TIPO_PERFIL_ADJUDICATARIO);
                    // Limpiamos VO(s) no utilizadas(s)
                    precargaConcursantesPorPerfil(0L, FenixModelConstants.ID_TIPO_PERFIL_OFERENTE);
                    precargaConcursantesPorPerfil(0L, FenixModelConstants.ID_TIPO_PERFIL_CONTRATADO);
                    break;
                case ORDEN_COMPRA:
                    logger.warning("Dentro opcion ORDEN_COMPRA");
                    precargaConcursantesPorPerfil(idNoObjecion, FenixModelConstants.ID_TIPO_PERFIL_CONTRATADO);
                    // Limpiamos VO(s) no utilizadas(s)
                    precargaConcursantesPorPerfil(0L, FenixModelConstants.ID_TIPO_PERFIL_OFERENTE);
                    precargaConcursantesPorPerfil(0L, FenixModelConstants.ID_TIPO_PERFIL_ADJUDICATARIO);
                    break;
                default:
                    logger.warning("Dentro default in tipoObjecionEnum opc : "+idTcaTipoNoObjecion);
                    // Limpiamos VO(s) no utilizadas(s)
                    precargaConcursantesPorPerfil(0L, FenixModelConstants.ID_TIPO_PERFIL_CONTRATADO);
                    precargaConcursantesPorPerfil(0L, FenixModelConstants.ID_TIPO_PERFIL_OFERENTE);
                    precargaConcursantesPorPerfil(0L, FenixModelConstants.ID_TIPO_PERFIL_ADJUDICATARIO);
                    break;
                }
            }else{
                logger.warning("tipoObjecionEnum es nulo, limpiar tablas de concursantes");
                // Limpiamos VO(s) no utilizadas(s)
                precargaConcursantesPorPerfil(0L, FenixModelConstants.ID_TIPO_PERFIL_CONTRATADO);
                precargaConcursantesPorPerfil(0L, FenixModelConstants.ID_TIPO_PERFIL_OFERENTE);
                precargaConcursantesPorPerfil(0L, FenixModelConstants.ID_TIPO_PERFIL_ADJUDICATARIO);
            }
            
            resultado = Boolean.TRUE;
        }
        
        logger.warning("Fuera de precargaConcursantes result : " +resultado);

        return resultado;
    }

    @SuppressWarnings("unchecked")
    private Boolean precargaConcursantesPorPerfil(Long idNoObjecion, Integer idTcaTipoPerfil) {
        logger.warning("Inside precargaConcursantesPorPerfil.idNoObjecion:"+idNoObjecion+", idTcaTipoPerfil:"+idTcaTipoPerfil);
        Boolean resultado=Boolean.TRUE;
        
        if (idNoObjecion != null && idTcaTipoPerfil != null) {
            BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
            OperationBinding operationBindingIdOp = null;
            
            // Establecemos el binding por el tipo del contratante
            if (idTcaTipoPerfil.equals(FenixModelConstants.ID_TIPO_PERFIL_OFERENTE)) {
                operationBindingIdOp = bindingsIdOp.getOperationBinding("oferentesWhereParams");
            } else if (idTcaTipoPerfil.equals(FenixModelConstants.ID_TIPO_PERFIL_ADJUDICATARIO)) {
                operationBindingIdOp = bindingsIdOp.getOperationBinding("adjudicatariosWhereParams");
            } else if (idTcaTipoPerfil.equals(FenixModelConstants.ID_TIPO_PERFIL_CONTRATADO)) {
                operationBindingIdOp = bindingsIdOp.getOperationBinding("contratadosWhereParams");
            }
            
            if (null != operationBindingIdOp) {
                operationBindingIdOp.getParamsMap().put("idNoObjecion", idNoObjecion);
                operationBindingIdOp.getParamsMap().put("idTcaTipoPerfil", idTcaTipoPerfil);
                resultado=(Boolean)operationBindingIdOp.execute();
                if(!operationBindingIdOp.getErrors().isEmpty()){
                    logger.warning("No se pudo precargar los concursantes");
                    resultado=Boolean.FALSE;
                }
            }
        }
        
        return resultado;
    }
    
    public void datosParticipantesMapping() {
        logger.log(ADFLogger.WARNING, "INTO datosParticipantesMapping.");
        
        try {
            DCIteratorBinding voParticipantes =
                                ADFUtils.getDCBindingContainer().findIteratorBinding("NoObjecionParticipantesVOIterator");
            RowSetIterator iq = voParticipantes.getViewObject().createRowSetIterator(null);
            iq.reset();
            while (iq.hasNext()) { 
                Row row = iq.next();
                if (null != row) {
                    Integer rowId = (Integer) row.getAttribute("Id");
                    if (null != rowId && rowId.compareTo(FenixModelConstants.ROL_OFICIAL_ADQUISICIONES) == 0) {
                        setOficialParticipa(((Boolean) row.getAttribute("Participa")));
                        setOficialObligatorio(((Boolean) row.getAttribute("Obligatorio")));
                    }
                    
                    if (null != rowId && rowId.compareTo(FenixModelConstants.ROL_ABOGADO) == 0) {
                        setAbogadoParticipa(((Boolean) row.getAttribute("Participa")));
                        setAbogadoObligatorio(((Boolean) row.getAttribute("Obligatorio")));
                    }
                    
                    if (null != rowId && rowId.compareTo(FenixModelConstants.ROL_ANALISTA_SUPERVISION) == 0) {
                        setAnalistaParticipa(((Boolean) row.getAttribute("Participa")));
                        setAnalistaObligatorio(((Boolean) row.getAttribute("Obligatorio")));
                    }
                }
            }
            
            loggerParticipantes();
        } catch(Exception e) {
            logger.log(ADFLogger.ERROR, "Error al generar mapeo de participantes");
        }
        
    }
    
    private void loggerParticipantes() {
        logger.log(ADFLogger.WARNING, "+++++++++++++++ Carga de participantes +++++++++++++++");

        logger.log(ADFLogger.WARNING, "oficialParticipa:"+isOficialParticipa());
        logger.log(ADFLogger.WARNING, "oficialObligatorio:"+isOficialObligatorio());
        
        logger.log(ADFLogger.WARNING, "abogadoParticipa:"+isAbogadoParticipa());
        logger.log(ADFLogger.WARNING, "abogadoObligatorio:"+isAbogadoObligatorio());
        
        logger.log(ADFLogger.WARNING, "analistaParticipa:"+isAnalistaParticipa());
        logger.log(ADFLogger.WARNING, "analistaObligatorio:"+isAnalistaObligatorio());
    }
    
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public Boolean getCategoria1() {
        return categoria1;
    }

    public void setCategoria1(Boolean categoria1) {
        this.categoria1 = categoria1;
    }

    public Boolean getCategoria2() {
        return categoria2;
    }

    public void setCategoria2(Boolean categoria2) {
        this.categoria2 = categoria2;
    }

    public Boolean getCategoria3() {
        return categoria3;
    }

    public void setCategoria3(Boolean categoria3) {
        this.categoria3 = categoria3;
    }

    public Boolean getObtuvo() {
        return obtuvo;
    }

    public void setObtuvo(Boolean obtuvo) {
        this.obtuvo = obtuvo;
    }

    public Integer getModalidad() {
        return modalidad;
    }

    public void setModalidad(Integer modalidad) {
        this.modalidad = modalidad;
    }

    public Long getPrecargadoNoObjecion() {
        return precargadoNoObjecion;
    }

    public void setPrecargadoNoObjecion(Long precargadoNoObjecion) {
        this.precargadoNoObjecion = precargadoNoObjecion;
    }

    public Boolean getHabilitarCampos() {
        return habilitarCampos;
    }

    public void setHabilitarCampos(Boolean habilitarCampos) {
        logger.warning("setHabilitarCampos: " + habilitarCampos);
        this.habilitarCampos = habilitarCampos;
    }

    public Boolean getPasoAprobacion() {
        return pasoAprobacion;
    }

    public void setPasoAprobacion(Boolean pasoAprobacion) {
        this.pasoAprobacion = pasoAprobacion;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public boolean isOficialParticipa() {
        return oficialParticipa;
    }

    public void setOficialParticipa(boolean oficialParticipa) {
        this.oficialParticipa = oficialParticipa;
    }

    public boolean isAbogadoParticipa() {
        return abogadoParticipa;
    }

    public void setAbogadoParticipa(boolean abogadoParticipa) {
        this.abogadoParticipa = abogadoParticipa;
    }

    public boolean isAnalistaParticipa() {
        return analistaParticipa;
    }

    public void setAnalistaParticipa(boolean analistaParticipa) {
        this.analistaParticipa = analistaParticipa;
    }

    public boolean isOficialObligatorio() {
        return oficialObligatorio;
    }

    public void setOficialObligatorio(boolean oficialObligatorio) {
        this.oficialObligatorio = oficialObligatorio;
    }

    public boolean isAbogadoObligatorio() {
        return abogadoObligatorio;
    }

    public void setAbogadoObligatorio(boolean abogadoObligatorio) {
        this.abogadoObligatorio = abogadoObligatorio;
    }

    public boolean isAnalistaObligatorio() {
        return analistaObligatorio;
    }

    public void setAnalistaObligatorio(boolean analistaObligatorio) {
        this.analistaObligatorio = analistaObligatorio;
    }

    public void setMostrarBotonGuardar(Boolean mostrarBotonGuardar) {
        this.mostrarBotonGuardar = mostrarBotonGuardar;
    }

    public Boolean getMostrarBotonGuardar() {
        return mostrarBotonGuardar;
    }
    
    public void changeNoObjecionCurrentRow(Long idNoObjecion, Integer modalidad) {
        logger.warning("Inside changeNoObjecionCurrentRow.");
        logger.warning("idNoObjecion:" + idNoObjecion);
        
        Boolean resultadoSeleccion = Boolean.TRUE;
        BindingContainer bindingsSeleccion = ADFUtils.getBindingContainer();
        OperationBinding operationSeleccion = null;
        try {
            bindingsSeleccion = ADFUtils.getBindingContainer();
            operationSeleccion = bindingsSeleccion.getOperationBinding("seleccionarNoObjecion2");
            operationSeleccion.getParamsMap().put("idNoObjecion", idNoObjecion);
            operationSeleccion.getParamsMap().put("modalidad", modalidad);
            resultadoSeleccion = (Boolean) operationSeleccion.execute();
        } catch (Exception e) {
            logger.warning("Error seleccionar nueva NO objecion.", e);
        }
    }

    public Integer getTipoObjecion() {
        return tipoObjecion;
    }

    public void setTipoObjecion(Integer tipoObjecion) {
        this.tipoObjecion = tipoObjecion;
    }
    public void setFileNamePlantilla(String fileNamePlantilla) {
        this.fileNamePlantilla = fileNamePlantilla;
    }

    public String getFileNamePlantilla() {
        return fileNamePlantilla;
    }
    
    public void setBtnDescargarPlantillaDisabled(String btnDescargarPlantillaDisabled) {
        this.btnDescargarPlantillaDisabled = btnDescargarPlantillaDisabled;
    }

    public String getBtnDescargarPlantillaDisabled() {
        return btnDescargarPlantillaDisabled;
    }
    
    public void setPathPLantillaNoObjecion(String pathPLantillaNoObjecion) {
        this.pathPLantillaNoObjecion = pathPLantillaNoObjecion;
    }

    public String getPathPLantillaNoObjecion() {
        return pathPLantillaNoObjecion;
    }

    public void setEsFinanciero(Boolean esFinanciero) {
        logger.warning("setEsFinanciero: " + esFinanciero);
        this.esFinanciero = esFinanciero;
    }

    public Boolean getEsFinanciero() {
        return esFinanciero;
    }


    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }


    public Boolean getEsResponsableOperacion() {
        return esResponsableOperacion;
    }

    public void setEsResponsableOperacion(Boolean esResponsableOperacion) {
        this.esResponsableOperacion = esResponsableOperacion;
    }

    public Boolean getEsGerentePais() {
        return esGerentePais;
    }

    public void setEsGerentePais(Boolean esGerentePais) {
        this.esGerentePais = esGerentePais;
    }
}
