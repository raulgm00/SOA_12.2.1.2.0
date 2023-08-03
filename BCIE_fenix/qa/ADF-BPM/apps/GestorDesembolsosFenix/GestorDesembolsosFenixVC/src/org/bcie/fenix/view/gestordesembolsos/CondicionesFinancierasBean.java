package org.bcie.fenix.view.gestordesembolsos;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;


import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;
import org.bcie.fenix.common.FenixConstants;                                        
public class CondicionesFinancierasBean extends FenixActionBean implements Serializable {
    @SuppressWarnings("compatibility:344874506778821095")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;

    public CondicionesFinancierasBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    //-------------  Estos datos son parametros de entrada del taskflow  ---------------->

    private Long idOperacion =
        (null == JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}")) ? null :
        new Long(JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}").toString());

    private Long idSolicitud =
        (null == JSFUtils.resolveExpression("#{pageFlowScope.idSolicitud}")) ? null :
        new Long(JSFUtils.resolveExpression("#{pageFlowScope.idSolicitud}").toString());

    private Integer idTareaBPM =
        (null == JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}")) ? null :
        Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}").toString());

    private Long idLineaCredito =
        (null == JSFUtils.resolveExpression("#{pageFlowScope.idLineaCredito}")) ? null :
        new Long(JSFUtils.resolveExpression("#{pageFlowScope.idLineaCredito}").toString());

    private Integer modoEjecucionTab =
        (null == JSFUtils.resolveExpression("#{pageFlowScope.idModoEjecucion}")) ? null :
        Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.idModoEjecucion}").toString());

    private Long idDesembolso =
        (null == JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}")) ? null :
        new Long(JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}").toString());

    //----- Variables Condiciones financieras----------->
    private Boolean tasaVariableVisible = Boolean.TRUE;
    private Boolean tasaFijaVisible = Boolean.FALSE;
    private Boolean tasaEspecialVisible = Boolean.FALSE;

    private Boolean especificadoFechasVisible = Boolean.TRUE;
    private Boolean especificadoPlazosVisible = Boolean.FALSE;
    private Long idCondicionFinanciera = null;
    private Boolean seccionMoraVisible = true;
    private Boolean calendarioComplejoVisible = Boolean.FALSE;
    private Boolean calendarioSencilloVisible = Boolean.TRUE;
    private Boolean moverEntreMeses = null;
    private String productoFlex = null;
    private Boolean alinearDiaPago = null;
    private Boolean exceptoVencimiento = null;
    private Integer idTcaTipoCalendario = null;
    private RichPopup popUpDescargarFormato;
    private Boolean mostrarSoloCalendarioSencillo = Boolean.FALSE;
    private Boolean lecturaIFI = Boolean.FALSE;
    private Integer idTipoTasaCondicion;
    private String nombreArchivoExcelCapital;
    private String nombreArchivoExcelInteres;
    private Boolean existeArchivoCapital;
    private Boolean existeArchivoInteres;
    private Boolean esTipoCapital;
    private Boolean esTipoInteres;
    private String fileNameArchivoCapital;
    private String fileNameArchivoInteres;
    private Boolean esOnSuggestTasaReferencia;

    private static Integer idTipoTasaMora = 0;
    private static Boolean mostrarSpreadVariable = Boolean.FALSE;
    
    /*Se atiende incidencia FNXII-5171 */
    private Boolean mostrarComponentesModoLectura = null;
    private Boolean mostrarComponentesModoEscritura = null;
    /*Fin incidencia FNXII-5171*/

   
    private Boolean frecPrincipalVisible = Boolean.TRUE;
    private Boolean frecInteresVisible = Boolean.TRUE;
    private Boolean frecRevSpreadVisible = Boolean.TRUE;
    BindingContainer bindings = getBindings();
    
    // se agrean campos es dpendiente y es factor 
    //valores pr defecto al redenrizar
    private String esDependiente = "Si";
    private String esFactor = "No";
    private String tipoTasaCB = "Variable";
    //--
    private String valorFactor;
    private String valorTasa;
    
    
    //Csmpo de dia de pago del cliente
    private Integer diaPagoCliente = null;


    /**
     * °RN_02 | El contrato de desembolso debe encontrarse en la programación del mes vigente.
     * RN_03 | Para un cliente diferente de sector público con garantía soberana el valor de SCR no debe
     *         ser mayor 5 para poder registrar el contrato de desembolso.
     * °RN_04 | Para realizar el registro del contrato de desembolso, el cliente no debe encontrarse en mora técnica.
     * °RN_05 | Para realizar el registro del contrato de desembolso, la fecha de disponibilidad de recursos debe
     *         ser igual a la fecha del sistema (Preguntar a Mariano) al momento de solicitar el registro del contrato.
     * °RN_06 | Se podrá registrar un contrato de desembolso solo si la línea de crédito sobre la que se realizará
     *         se encuentra vigente.
     * °RN_07 | Se podrá realizar el registro de un contrato de desembolso, dicho contrato debe
     *         encontrarse en el estado: “Aprobado por gerente” o “Fondos reservados”.
     * °RN_08 | Se podrá realizar el registro de un contrato de desembolso solo cuando la hora actual del
     *         sistema, sea previa a la hora de cierre de banca de la moneda del contrato de desembolso.
     * °RN_09 | Se podrá realizar el registro de un contrato de desembolso solo si la operación cuenta con
     *         la No objeción de LAFT vigente.
     * °RN_10 | Se podrá realizar el registro del desembolso solo si se cuenta con las condiciones correspondientes
     *         cumplidas tanto para la solicitud, el contrato de desembolso, generales y periódicas.
     *
     * @since 12/10/2016
     * @return Si cumple con las RN se retorna TRUE
     */

    public void registrarCondicionesFinancieras() {
        logger.warning("Inicia Tab Condiciones Financieras - Modo Ejecucion : Registra Condiciones financieras. 1");
        condicionesFinancieras(idDesembolso, "precarga");
        logger.warning("Finaliza Tab Condiciones Financieras - Modo Ejecucion : Registro Condiciones financieras.");
    }
        
    public void registrarDesembolsoCondicionesFinancieras() {
        logger.warning("Inicia Tab Condiciones Financieras - Modo Ejecucion : Registro Desembolso.");
        condicionesFinancieras(idDesembolso, "precarga");
        logger.warning("Finaliza Tab Condiciones Financieras - Modo Ejecucion : Registro Desembolso."); 
    }
        
    public void cargarCondicionesFinancieras() {
        logger.warning("Inf, Inicia Tab Condiciones financienras, modo de ejecucion Ver Condiciones");
        condicionesFinancieras(idDesembolso, "consulta");
        logger.warning("Inf, Finaliza Tab Condiciones financienras, modo de ejecucion Ver Condiciones"); 
    }
    
    public void condicionesFinancieras(Long pIdDesembolso, String ptipoVista) {
        logger.warning("Inicia Metodo condicionesFinancieras");
        logger.warning("pIdDesembolso: " + pIdDesembolso);
        logger.warning("ptipoVista: " + ptipoVista); 
        verValoresCarga();
        if(idDesembolso != null){ 
            logger.warning("cargarCFTempVO1----------------------------------------");
            cargarCFTempVO();
            logger.warning("iniciarValoresDePrecarga----------------------------------------");
            iniciarValoresDePrecarga();
            logger.warning("cargarVistaProductoOperacion----------------------------------------");
            cargarVistaProductoOperacion(ptipoVista); 
            logger.warning("filtrarTasaReferencia----------------------------------------");
            filtrarTasaReferencia(); 
            logger.warning("obtenerTipoOperacion----------------------------------------");
            logger.warning("getIdOperacion(): " + getIdOperacion());
            obtenerTipoOperacion(getIdOperacion());
            logger.warning("recuperarDescripcionProducto----------------------------------------");
            recuperarDescripcionProducto(); 
            logger.warning("validarProductoMostrarSpreadVariable----------------------------------------");
            validarProductoMostrarSpreadVariable();
            
            logger.warning(" cargarVistaProductoOperacion al final de los demas----------------------------------------");
            cargarVistaProductoOperacion(ptipoVista); 
            
            //Se carga el dia de pago del cliente a la variable
            logger.warning("cargar dia de pago al final de los demas----------------------------------------");
            initDiaPago();
        }else{
            logger.warning("El idDesembolso es nulo");
        }        
        logger.warning("Finaliza Metodo condicionesFinancieras"); 
    }
    
    public void initDiaPago() {
        logger.warning("*Inicia, method binding InitDiaPago");
        try{
        Integer dia_pago = obtenerDiaPagoCliente();
        logger.warning("*Inf, method binding getInitDiaPago = " + dia_pago);
        
            this.diaPagoCliente = dia_pago == 0 ? null : dia_pago;
        logger.warning("dia_pago: " + dia_pago);
        }
        catch(Exception exp)
        {
            logger.warning("Exception this.initDiaPago.setValue:" + exp);
        }
        
        logger.warning("*Finaliza, method binding InitDiaPago");
    }
    
    public Integer obtenerDiaPagoCliente() {
        logger.warning("*Inf, Termina metodo obtenerDiaPagoCliente");
        Integer diaPagoCliente = null;

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("obtenerDiaPagoCliente");
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("*Error, operationBinding obtenerDiaPagoCliente ->" +
                               operBinding.getErrors().toString());
                JSFUtils.addFacesErrorMessage("Error, " + operBinding.getErrors());
            } else {

                if (operBinding.getResult() != null) {
                    diaPagoCliente = (Integer) operBinding.getResult();
                    logger.warning("*Inf, dia pago cliente recuperado ->" + diaPagoCliente);
                    try{
                        logger.warning("***nuevo e: " + diaPagoCliente);
                        if(diaPagoCliente == null){
                            logger.warning("***diaPagoCliente es nulo");
                            diaPagoCliente = 0;    
                        }
    //                        ADFUtils.setBoundAttributeValue("DiaPago", diaPagoCliente);
    //                        String XdiaPagoCliente = ADFUtils.getBoundAttributeValue("DiaPago").toString();
    //                        logger.warning("***nuevo s: " + XdiaPagoCliente);
                    } catch (Exception ex) {
                        logger.warning("***Error nuevo: ", ex);
                    }
                } else {
                    logger.warning("*** Error obtenerDiaPagoCliente, No se pudo recuperar un current en contrato desembolso");
                }
            }
        } catch (Exception e) {
            logger.warning("***Error obtenerDiaPagoCliente, al consultar los datos del desembolso ", e);
            diaPagoCliente = 0;    
        }


        logger.warning("*Inf, Termina metodo obtenerDiaPagoCliente");
        return diaPagoCliente;
    }


    public void validarProductoMostrarSpreadVariable() {
        logger.warning("Inicia metodo validarProductoMostrarSpreadVariable.");
        String id = null;
        
        try{
            id = (String) ADFUtils.getBoundAttributeValue("IdProductoFlexcube");
        }catch(Exception e){
            logger.warning("ERROR al recuperar el boundAttribute de IdProductoFlexcube.", e);
        }
        
        try {
            if (id != null) {
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding("consultarSpreadVariableById");
                operBinding.getParamsMap().put("id", id);
                Boolean respuesta = (Boolean) operBinding.execute();
                
                logger.warning("Valor obtenido en consultarSpreadVariableById: " + respuesta);
                setMostrarSpreadVariable(respuesta);
                if (!operBinding.getErrors().isEmpty()) {
                    logger.warning("***Error, al ejecutar operation Binding consultarSpreadVariableById " +
                                   operBinding.getErrors());
                }
            } else {
                logger.warning("El id obtenido en recuperarIdByDescripcion es null, el valor de consultarSpreadVariableById es false");
                setMostrarSpreadVariable(Boolean.FALSE);
            }
        } catch (Exception e) {
            logger.warning("***Error, al ejecutar operation Binding consultarSpreadVariableById " + e);
        }

        logger.warning("Termina metodo validarProductoMostrarSpreadVariable.");
    }

    public void verValoresCarga() {
        logger.warning("* Inf, --- valores recuperados del TaskFlow --");
        logger.log(ADFLogger.WARNING, "idOperacion: " + idOperacion);
        logger.log(ADFLogger.WARNING, "idSolicitud: " + idSolicitud);
        logger.log(ADFLogger.WARNING, "idDesembolso: " + idDesembolso);
        logger.log(ADFLogger.WARNING, "idTareaBPM: " + idTareaBPM);
        logger.log(ADFLogger.WARNING, "idLineaCredito: " + idLineaCredito);
        logger.log(ADFLogger.WARNING, "modoEjecucionTab: " + modoEjecucionTab);
        logger.warning(" ---*                                    *---");
    }

    public void validarProductoFlex() {
        //TODO propagarContratoDesembolso(idDesembolso);
        String productoBHQ = "BHQLD02072720012";
        setProductoFlex(productoBHQ);

    }

    public void iniciarValoresDePrecarga() {
            logger.warning("*Inf, inicia metodo iniciarValoresDePrecarga ");
            double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
            TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

            try {

                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding("crearRow");
                operBinding.getParamsMap().put("idContratoDesembolso", idDesembolso);
                operBinding.getParamsMap().put("idOperacion", idOperacion);
                operBinding.execute();           
                
                    if (!operBinding.getErrors().isEmpty()) {
                        logger.warning("***Error, al ejecutar operation Binding crearRow " + operBinding.getErrors());
                    }
                    idCondicionFinanciera = (null == operBinding.getResult())? null
                                           : (Long) operBinding.getResult();
                    
                    logger.warning("Inf, idCondicionFinanciera" + idCondicionFinanciera);
                
                if(null != idCondicionFinanciera){
                    Integer idTipoTasa = null;
                    JSFUtils.storeOnSession("idCondicionFinanciera",idCondicionFinanciera);
                    try{
                        idTipoTasa = execute(null, "obtenerIdTipoTasa");
                        setIdTipoTasaCondicion(idTipoTasa);
                        setIdTipoTasaMora(2); //Se inicializa por defecto en fixed
                        mostrarCamposTasa(idTipoTasa);
                        
                        logger.warning("Valor idTipoTasaMora al inicializar la carga: " + idTipoTasaMora);
                    }catch(Exception e){
                        logger.warning("Error a ejecutar el OperBinding de obtenerIdTipoTasa.", e);
                    }
                    
                    Integer idTipoCalendario = null;
                    try{
                        idTipoCalendario = execute(null, "obtenerIdTipoCalendario");
                        if(null != idTipoCalendario){
                            if(idTipoCalendario.compareTo(1)==0){ //calendaroo complejo
                                setCalendarioComplejoVisible(Boolean.TRUE);
                                setCalendarioSencilloVisible(Boolean.FALSE);
                            }else{                                //calendario sencillo
                                setCalendarioComplejoVisible(Boolean.FALSE);
                                setCalendarioSencilloVisible(Boolean.TRUE);
                            }
                        }else{
                            logger.warning("EL idTipoCalendario recuperado es NULL.");
                            setCalendarioComplejoVisible(Boolean.FALSE);
                            setCalendarioSencilloVisible(Boolean.TRUE);
                        }                        
                    }catch(Exception e){
                        logger.warning("Error a ejecutar el OperBinding de obtenerIdTipoCalendario.", e);
                    }
                    
                    Integer tipoEspecificacionFechas = null;
                    try{
                        tipoEspecificacionFechas = execute(null, "obtenerTipoEspecificacionFechas");
                        if(null != tipoEspecificacionFechas){
                            
                            if(tipoEspecificacionFechas.compareTo(1)==0){ //Especificacion por fechas
                                logger.warning(" Tipo de especificacion por fechas indicado");
                                setEspecificadoFechasVisible(Boolean.TRUE);
                                setEspecificadoPlazosVisible(Boolean.FALSE);
                            }else{                                         //Especificacion por plazos
                                logger.warning(" Tipo de especificacion por plazos indicado");
                                setEspecificadoFechasVisible(Boolean.FALSE);
                                setEspecificadoPlazosVisible(Boolean.TRUE);                                
                                setMostrarSoloCalendarioSencillo(Boolean.TRUE);
                            }
                        }else{
                            logger.warning("EL tipoEspecificacionFechas recuperado es NULL.");
                            setEspecificadoFechasVisible(Boolean.TRUE);
                            setEspecificadoPlazosVisible(Boolean.FALSE);
                        } 
                    }catch(Exception e){
                        logger.warning("Error a ejecutar el OperBinding de obtenerTipoEspecificacionFechas.", e);
                    }
                }
                /*
                //BindingContainer bindings = ADFUtils.getBindingContainer();
                //ContratoDesembolsosBean contratoDesembolsoBean =(ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
                //Long idContratoSeleccionado = (Long) ADFUtils.getBoundAttributeValue("Id");
                if(null!=getIdDesembolso() && null!=getIdLineaCredito()){
                    //Long idLineaCredito=contratoDesembolsoBean.getIdLinea();
                    logger.warning("idDesembolso --> "+getIdDesembolso());
                    logger.warning("idLineaCredito --> "+getIdLineaCredito());
                    OperationBinding cargarVO = bindings.getOperationBinding("cargarCFTempVO");
                    cargarVO.getParamsMap().put("idContrato", String.valueOf(getIdDesembolso()));
                    cargarVO.getParamsMap().put("idLineaCredito", String.valueOf(getIdLineaCredito()));
                    cargarVO.execute();  
                }
                */
                
            } catch (Exception e) {
                logger.log(ADFLogger.WARNING, "Error en setCurrentRow", e);
            }
            
            
            TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
            tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
            logger.warning("Inf, Termina metodo iniciarValoresDePrecarga con una duracion de: "+tiempo+" segundos");  
        }
    
    public void cargarCFTempVO() {
            logger.warning("*Inf, inicia metodo cargarCFTempVO ");
            double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
            TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

            try {

                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding("cargarCFTempVO");
                operBinding.getParamsMap().put("idContrato", idDesembolso);
                operBinding.getParamsMap().put("idLineaCredito", idLineaCredito);
                operBinding.execute();           
                
                    if (!operBinding.getErrors().isEmpty()) {
                        logger.warning("***Error, al ejecutar operation Binding cargarCFTempVO " + operBinding.getErrors());
                    }
                
                }
                catch (Exception e) {
                    logger.log(ADFLogger.WARNING, "Error en cargarCFTempVO", e);
                }
            
            
            TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
            tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
            logger.warning("Inf, Termina metodo cargarCFTempVO con una duracion de: "+tiempo+" segundos");  
        }
    
    
    public void mostrarCamposTasa(Integer tasa){
       logger.warning("*Inf, inicia metodo mostrarCamposTasa para tasa:"+tasa);
       
       final int especial = 1;
       final int fija = 2;
       final int variable = 3;
       
                 if(null != tasa){                                            
                                              
                         switch(tasa){
                         case especial:                             
                             this.setTasaEspecialVisible(true);
                             this.setTasaFijaVisible(false);
                             this.setTasaVariableVisible(false);                                
                             this.setSeccionMoraVisible(true);
                             logger.warning("*Inf, mostrando campos para tipo de tasa especial");
                         break;
                         case fija:                             
                             this.setTasaFijaVisible(true);
                             this.setTasaVariableVisible(false);
                             this.setTasaEspecialVisible(false);  
                             this.setSeccionMoraVisible(true);
                             logger.warning("*Inf, mostrando campos para tipo de tasa fija");
                         break;
                         case variable:                            
                             this.setTasaVariableVisible(true);
                             this.setTasaFijaVisible(false);                                
                             this.setTasaEspecialVisible(false);
                             this.setSeccionMoraVisible(true);
                             logger.warning("*Inf, mostrando campos para tipo de tasa variable");
                         break;  
                         }                        
                         
                     }else{
                          logger.warning("*Error, la variable tasa es null *");
                     }
                           
       logger.warning("*Inf, inicia metodo mostrarCamposTasa ");
     }

    public void crearSequenceCondicion() {
        logger.warning("* Inf, inicia metodo crearSequenceCondicion ");

        try {
            OperationBinding operationBinding = executeOperation("createSequenceCondicion");
            operationBinding.execute();

            if (operationBinding.getResult() != null) {
                if (!operationBinding.getErrors().isEmpty()) {
                    logger.warning("***Error, al ejecutar operation Binding crearRow " + operationBinding.getErrors());
                }
                idCondicionFinanciera = (Long) operationBinding.getResult();
                logger.warning("Inf, idCondicionFinanciera" + idCondicionFinanciera);
            } else {
                logger.warning("***Error , operationBinding.getResult() de crearRow es resuelto a Null ");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en setCurrentRow" + e.getClass() + ":" + e.getMessage());
        }
    }


    public String createInsertCondicion() {
        logger.log(ADFLogger.WARNING, "--- Inside createInsertCondicion");
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
        Object results = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setCurrentRow() {
        logger.log(ADFLogger.WARNING, " --- Inside setCurrentRow");
        BindingContainer bindings = getBindings();
        Boolean error = null;
        try {
            OperationBinding operationBinding = executeOperation("setCurrentRow");
            operationBinding.getParamsMap().put("idDesembolso", idDesembolso);
            operationBinding.execute();
            if (operationBinding.getResult() != null) {
                logger.warning("Revisar respuesta OK");
                if (!operationBinding.getErrors().isEmpty()) {
                    logger.log(ADFLogger.ERROR, "Error al Retornar setCurrentRow " + operationBinding.getErrors());
                }
                error = (Boolean) operationBinding.getResult();

            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en setCurrentRow" + e.getClass() + ":" + e.getMessage());
        }
    }

    public void getCondicionesFinancieras(){
        logger.log(ADFLogger.WARNING, " --- Inside getCondicionesFinancieras");
        BindingContainer bindings = getBindings();
        Row row = null;
        try {
            OperationBinding operationBinding = executeOperation("getCondicionesFinancieras");
            operationBinding.getParamsMap().put("idContrato", idDesembolso);
            operationBinding.execute();
            if (operationBinding.getErrors() == null) {
                row = (Row) operationBinding.getResult();
                logger.log(ADFLogger.WARNING, "VAlidar que el Row no venga null");
                if (row != null) {
                    logger.log(ADFLogger.WARNING, "----------- DATOS_RECUPERADOS -----------");
                    logger.log(ADFLogger.WARNING, "-- Id: " + row.getAttribute("Id"));
                    logger.log(ADFLogger.WARNING, "-- IdDesembolso: " + row.getAttribute("IdDesembolso"));
                }
            } else {
                if (operationBinding.getResult() != null) {
                    logger.log(ADFLogger.WARNING, "Revisar respuesta OK");
                    if (!operationBinding.getErrors().isEmpty()) {
                        logger.log(ADFLogger.ERROR,
                                   "Error al Retornar getCondicionesFinancieras " + operationBinding.getErrors());
                    }
                }
            }

        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en getCondicionesFinancieras" + e.getClass() + ":" + e.getMessage());
        }
    }


    public void cambioCalendario(ValueChangeEvent valueChangeEvent) {
        logger.warning("--- Inside cambioCalendario valor: " + valueChangeEvent.getNewValue());
        logger.log(ADFLogger.WARNING, "--- Inside cambioCalendario");
        CondicionesFinancierasBean cambioCalendario = null;
        cambioCalendario =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
        if (null != cambioCalendario) {
            if (null != valueChangeEvent.getNewValue()) {
                String tipoCalendario = valueChangeEvent.getNewValue().toString();
                switch (tipoCalendario) {
                case "2":
                    cambioCalendario.setCalendarioSencilloVisible(true);
                    cambioCalendario.setCalendarioComplejoVisible(false);
                    break;
                case "1":
                    cambioCalendario.setCalendarioSencilloVisible(false);
                    cambioCalendario.setCalendarioComplejoVisible(true);
                    break;
                }
                logger.warning(" * ValueChangeEvent es " + valueChangeEvent.getNewValue());
            } else {
                logger.warning(" * ValueChangeEvent es null");
            }
        } else {
            logger.warning(" * Instancia bean es null");
        }
    }

    public void getTipoTasa() {
        logger.log(ADFLogger.WARNING, "Inside getTipoTasa");
        BindingContainer bindings = getBindings();
        Boolean error = Boolean.TRUE;
        try {
            Integer idOperacion = 1;
            OperationBinding operationBinding = bindings.getOperationBinding("getTipoTasaConBanEstatus");
            //operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.execute();
            if (operationBinding.getResult() != null) {
                error = (Boolean) operationBinding.getResult();
                if (!operationBinding.getErrors().isEmpty()) {
                    logger.log(ADFLogger.ERROR, "Error al Retornar getTipoTasa " + operationBinding.getErrors());
                }
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en getTipoTasa" + e.getClass() + ":" + e.getMessage());
            JSFUtils.addFacesErrorMessage("Error al cargar Tipo de tasa. Por favor intente más tarde.");
        }
    }

    public void getEspecificacionFecha() {
        logger.log(ADFLogger.WARNING, "Inside getEspecificacionFecha");
        Boolean error = Boolean.TRUE;
        try {
            OperationBinding operationBinding = bindings.getOperationBinding("getEspecificacionFecha");
            operationBinding.execute();
            error = (Boolean) operationBinding.getResult();
            if (!operationBinding.getErrors().isEmpty()) {
                logger.log(ADFLogger.ERROR, "Error al Retornar getEspecificacionFecha " + operationBinding.getErrors());
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en getEspecificacionFecha" + e.getClass() + ":" + e.getMessage());
            JSFUtils.addFacesErrorMessage("Error al cargar especificacion de fecha. Por favor intente más tarde.");
        }
    }

    public void getBaseCalculo() {
        logger.log(ADFLogger.WARNING, "Inside getBaseCalculo");
        Boolean error = Boolean.TRUE;
        try {
            OperationBinding operationBinding = bindings.getOperationBinding("getBaseCalculo");
            operationBinding.execute();
            error = (Boolean) operationBinding.getResult();
            if (!operationBinding.getErrors().isEmpty()) {
                logger.log(ADFLogger.ERROR, "Error al Retornar getBaseCalculo " + operationBinding.getErrors());
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en getBaseCalculo" + e.getClass() + ":" + e.getMessage());
            JSFUtils.addFacesErrorMessage("Error al cargar base de calculo. Por favor intente más tarde.");
        }
    }

    public void getTipoCalendario() {
        logger.log(ADFLogger.WARNING, "Inside getTipoCalendario");
        Boolean error = Boolean.TRUE;
        try {
            OperationBinding operationBinding = bindings.getOperationBinding("getTipoCalendario");
            operationBinding.execute();
            error = (Boolean) operationBinding.getResult();
            if (!operationBinding.getErrors().isEmpty()) {
                logger.log(ADFLogger.ERROR, "Error al Retornar getTipoCalendario " + operationBinding.getErrors());
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en getTipoCalendario" + e.getClass() + ":" + e.getMessage());
            JSFUtils.addFacesErrorMessage("Error al cargar el tipo de calendario. Por favor intente más tarde.");
        }
    }


    public Boolean validarRegistroDesembolso() {
        Boolean esValido = Boolean.FALSE;
        Boolean scrValido = Boolean.FALSE;
        Boolean moraTecnica = Boolean.TRUE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;

        //------------------ VALIDACION | RN_03 ----------------------------
        operationBinding = bindings.getOperationBinding("obtenerSectorGarantia");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            Map<String, Object> resultado = new HashMap<String, Object>();
            resultado = (HashMap<String, Object>) operationBinding.getResult();
            Integer sector = (Integer) resultado.get("SECTOR");
            Integer garantia = (Integer) resultado.get("TIPO_GARANTIA");
            Integer idTcaScr = (Integer) resultado.get("IdTcaScr");
            logger.log(ADFLogger.WARNING, "Sector: " + sector + " Tipo garantia: " + garantia + " Scr: " + idTcaScr);
            //Validamos que el SECTOR sea publico
            if (sector != null && sector == 1) {
                //Validamos que tenga una Garantia Soberana
                if (garantia != null && garantia == 2) {
                    //Validamos que el SCR sea mayor a 5
                    if (idTcaScr != null && idTcaScr < 6) {
                        scrValido = Boolean.TRUE;
                    } else {
                        logger.log(ADFLogger.WARNING, "EL SCR no cumple la VALIDACION" + idTcaScr);
                    }
                } else {
                    logger.log(ADFLogger.WARNING, "LA GARANTIA no cumple la VALIDACIION: " + garantia);
                }
            } else {
                logger.log(ADFLogger.WARNING, "EL SECTOR no cumple la VALIDACIION: " + sector);
            }
        }
        //TODO VALIDACION | RN_04
        if (1 == 1) {
            moraTecnica = Boolean.FALSE;
        }

        logger.log(ADFLogger.WARNING, "RN_03: " + scrValido);
        return esValido;
    }

    public String obtenerDescripcionMoneda() {
        logger.warning("Inside obtenerDescripcionMoneda.");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        String descripcionMoneda = null;

        try {

            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("obtenerDescripcionMoneda");
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("Error al ejecutar binding obtenerDescripcionMoneda: " + operBinding.getErrors());
            }

            if (operBinding.getResult() == null) {
                logger.warning("Result operBinding es nulo.");
            } else {
                descripcionMoneda = (String) operBinding.getResult();
                setDescripcionMonedaCondicion(descripcionMoneda);
            }

        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en obtenerDescripcionMoneda" + e.getClass() + ":" + e.getMessage());
        }

        logger.warning("descripcionMoneda: " + descripcionMoneda);
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia
        logger.warning("*Inf, Termina metodo obtenerDescripcionMoneda con una duracion de: "+tiempo+" segundos");
        return descripcionMoneda;
    }
    
    public void setDescripcionMonedaCondicion(String descripcionMoneda){
      logger.warning("*Inf, Inicia metodo setDescripcionMonedaCondicion ");
        
        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("setDescripcionMoneda");
            operBinding.getParamsMap().put("descripcionMoneda", descripcionMoneda);
            operBinding.execute();            

        } catch (Exception e) {
            logger.warning("ha ocurrido un error en el metodo setDescripcionMonedaCondicion ->",e);
        }
         
      logger.warning("*Inf, termina metodo setDescripcionMonedaCondicion ");
    }

    public void filtrarTasaReferencia() {
        logger.warning("Inside obtenerDescripcionMoneda.");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        String descripcionMoneda = obtenerDescripcionMoneda();

        if (descripcionMoneda != null) {
            try {
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding("setvarCodigoMoneda");
                operBinding.getParamsMap().put("value", descripcionMoneda);
                operBinding.execute();

                if (!operBinding.getErrors().isEmpty()) {
                    logger.warning("Error al ejecutar binding obtenerDescripcionMoneda: " + operBinding.getErrors());
                }

            } catch (Exception e) {
                logger.log(ADFLogger.WARNING,
                           "Error en obtenerDescripcionMoneda" + e.getClass() + ":" + e.getMessage());
            }
        } else {
            logger.warning("La descripcion de la moneda es null.");
        }
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia
        logger.warning("*Inf, Termina metodo filtrarTasaReferencia con una duracion de: "+tiempo+" segundos");
    }

    
    /**
     * Con este metodo ejecutamos el Query de vtaProductoDesemFlexcubeVO que recibe dos parametros 
     * el tipo de taza y el id de la operacion, la variable tipo llamado indica si es para precarga
     * o para la accion del cambio de taza.
     * 
     * @author Carlos L.
     * @since 20/01/2017
     */
    public void cargarVistaProductoOperacion(String tipoLlamado){
            logger.warning("*Inf, Inicia metodo cargarVistaProductoOperacion");
            logger.warning("tipoLlamado: " + tipoLlamado);
            double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
            TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
                                  
            String tipoTaza = null;
            Integer idTcaTaza = null;
               
            if(tipoLlamado.equalsIgnoreCase("precarga") ){
                logger.warning("*Inf, se precarga la vista producto para una taza fija");
                tipoTaza = FenixConstants.GD_TIPO_TAZA_FIJA; 
                   
                idTcaTaza = getIdTipoTasaCondicion();
                
                if(null != idTcaTaza){
                    switch(idTcaTaza){
                    case 1:
                        tipoTaza = FenixConstants.GD_TIPO_TAZA_ESPECIAL;
                    break;
                    case 2:
                        tipoTaza = FenixConstants.GD_TIPO_TAZA_FIJA;
                    break;
                    case 3:
                        tipoTaza = FenixConstants.GD_TIPO_TAZA_VARIABLE;
                    break;                        
                    } 
                }
            }else if(tipoLlamado.equalsIgnoreCase("cambioValorTaza") ){
                if(null != idDesembolso){
                    idTcaTaza  = (null == ADFUtils.getBoundAttributeValue("IdTcaTipoTasaDesembolso")
                              || ADFUtils.getBoundAttributeValue("IdTcaTipoTasaDesembolso").equals(""))? null
                               : Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaTipoTasaDesembolso").toString()); 
        
                    if(null != idTcaTaza){
                        switch(idTcaTaza){
                        case 1:
                            tipoTaza = FenixConstants.GD_TIPO_TAZA_ESPECIAL;
                        break;
                        case 2:
                            tipoTaza = FenixConstants.GD_TIPO_TAZA_FIJA;
                        break;
                        case 3:
                            tipoTaza = FenixConstants.GD_TIPO_TAZA_VARIABLE;
                        break;                        
                        } 
                    }
                }
            }else{
                idTcaTaza = getIdTipoTasaCondicion();
                logger.warning("Tipo de tasa de condicion: " + idTcaTaza);
                if(null != idTcaTaza){
                    switch(idTcaTaza){
                    case 1:
                        tipoTaza = FenixConstants.GD_TIPO_TAZA_ESPECIAL;
                    break;
                    case 2:
                        tipoTaza = FenixConstants.GD_TIPO_TAZA_FIJA;
                    break;
                    case 3:
                        tipoTaza = FenixConstants.GD_TIPO_TAZA_VARIABLE;
                    break;                        
                    } 
                }
            }
                
            if(null != tipoTaza){
                try {
                    BindingContainer binding = ADFUtils.getBindingContainer();
                    OperationBinding operBinding = binding.getOperationBinding("cargarDastosVCAProducto");
                    operBinding.getParamsMap().put("idOperacion", idOperacion);
                    operBinding.getParamsMap().put("tipoTaza", tipoTaza);
                    operBinding.getParamsMap().put("idDesembolso", idDesembolso);
                    operBinding.execute();

                    if (!operBinding.getErrors().isEmpty()) {
                        logger.warning("Error al ejecutar binding cargarVistaProductoOperacion: " + operBinding.getErrors());
                    }

                } catch (Exception e) {
                    logger.log(ADFLogger.WARNING,
                               "Error en cargarVistaProductoOperacion.", e);
                }
               
               logger.warning("Cargando atributtos operacion y tasa transient..");
                
               try {
                   BindingContainer binding = ADFUtils.getBindingContainer();
                   OperationBinding operBinding = binding.getOperationBinding("setOperacionTasaTransient");
                   operBinding.getParamsMap().put("codigoOperacion", idOperacion);
                   operBinding.getParamsMap().put("codigoTasa", tipoTaza);
                   operBinding.execute();

                   if (!operBinding.getErrors().isEmpty()) {
                       JSFUtils.addFacesErrorMessage("Error, "+operBinding.getErrors().toString());
                       logger.warning("Error al ejecutar binding setOperacionTasaTransient: " + operBinding.getErrors());
                   }

               } catch (Exception e) {
                   logger.log(ADFLogger.WARNING,
                              "Error en cargarVistaProductoOperacion.", e);
               }
            }else{
                logger.warning("TipoTaza es NULL.");
            }
            
            TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
            tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
            logger.warning("*Inf, Termina metodo cargarVistaProductoOperacion con una duracion de: "+tiempo+" segundos");
    }
        
    public void obtenerTipoOperacion(Long pIdOperacion) {
        logger.warning("Into obtenerTipoOperacion. pIdOperacion: "+pIdOperacion);
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        if (pIdOperacion != null) {

            //Obtener idProducto
            BindingContainer bindings = null;
            OperationBinding operationBinding = null;

            bindings = ADFUtils.getBindingContainer();
            operationBinding = bindings.getOperationBinding("obtenerProductoOperacion");
            operationBinding.getParamsMap().put("idOperacion", getIdOperacion());
            Integer idProducto = (Integer) operationBinding.execute();
            
            if (!operationBinding.getErrors().isEmpty()) {
                //Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else {
                if (idProducto != null) {
                    operationBinding = bindings.getOperationBinding("obtenerCampo");
                    operationBinding.getParamsMap().put("idProducto", idProducto);
                    operationBinding.getParamsMap().put("columna", 8);
                    operationBinding.execute();
                    if(!operationBinding.getErrors().isEmpty()){
                        //Manejo de errores
                        JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                    } else if (operationBinding.getResult() != null) {
                        Boolean resultado =(Boolean)operationBinding.getResult();
                        setLecturaIFI(resultado);
                    }
                }    
            }
        }
        
        logger.warning("lecturaIFI: " + getLecturaIFI());
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
        logger.warning("*Inf, Termina metodo obtenerTipoOperacion con una duracion de: "+tiempo+" segundos");
    }
    
    public void precargarInformacionCalendarioComplejo(){
        logger.warning("Inicia metodo precargarInformacionCalendarioComplejo");
        Long idCondicionFinancieraTF = null;
        String tipoInvocacion = null;
        Integer modoEjecucion = null;
        Map listaCalendarios = new HashMap();
        Map<String, Object> params = new HashMap<String, Object>();
        String fileNameCapital = null;
        String fileNameInteres = null;
        
        BindingContainer bindings = getBindings();
        String tipoPagoCapital = FenixConstants.PATH_CALENDARIO_COMPLEJO_CAPITAL;
        String tipoPagoInteres = FenixConstants.PATH_CALENDARIO_COMPLEJO_INTERES;
        
        try {
            OperationBinding operationBinding = executeOperation("getValorWsdl");
            operationBinding.getParamsMap().put("llave", tipoPagoCapital);
            operationBinding.execute();
            if (operationBinding.getResult() != null) {
                if (!operationBinding.getErrors().isEmpty()) {
                    logger.log(ADFLogger.ERROR,
                               "Error al Retornar getFormatoCapitalPath " + operationBinding.getErrors());
                }else{
                    fileNameCapital = (String) operationBinding.getResult();
                    fileNameCapital = fileNameCapital.substring(fileNameCapital.lastIndexOf("/")+1);
                    logger.warning("FileName de capital a descargar: " + fileNameCapital);
                    setFileNameArchivoCapital(fileNameCapital);
                }
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en getFormatoCapitalPath" + e.getClass() + ":" + e.getMessage());
        }
        
        try {
            OperationBinding operationBinding = executeOperation("getValorWsdl");
            operationBinding.getParamsMap().put("llave", tipoPagoInteres);
            operationBinding.execute();
            if (operationBinding.getResult() != null) {
                if (!operationBinding.getErrors().isEmpty()) {
                    logger.log(ADFLogger.ERROR,
                               "Error al Retornar getFormatoCapitalPath " + operationBinding.getErrors());
                }else{
                    fileNameInteres = (String) operationBinding.getResult();
                    fileNameInteres = fileNameInteres.substring(fileNameInteres.lastIndexOf("/")+1);
                    logger.warning("FileName de interes a descargar: " + fileNameInteres);
                    setFileNameArchivoInteres(fileNameInteres);
                }
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en getFormatoCapitalPath" + e.getClass() + ":" + e.getMessage());
        }
        
        try{
            idCondicionFinancieraTF =
                new Long(JSFUtils.resolveExpression("#{pageFlowScope.idCondicionFinanciera}").toString());
        }catch(Exception e){
            logger.warning("ERROR al recuperar IdCOndicionFinanciera de TF.", e.getMessage());
        }
        
        try{
            tipoInvocacion = JSFUtils.resolveExpression("#{pageFlowScope.tipoInvocacion}").toString();;
        }catch(Exception e){
            logger.warning("ERROR al recuperar el tipoInvocacion de TF.", e.getMessage());
        }
        
        try{
            modoEjecucion = Integer.valueOf((JSFUtils.resolveExpression("#{pageFlowScope.idModoEjecucion}").toString()));
        }catch(Exception e){
            logger.warning("ERROR al recuperar el idModoEjecucion de TF.", e.getMessage());
        }
        
        /*Se atiende incidencia FNXII-5171 */
        logger.warning("modo de Ejecucion: " + modoEjecucion);
        try {
            if (modoEjecucion != null && modoEjecucion == 2){
                setMostrarComponentesModoEscritura(Boolean.TRUE);
                setMostrarComponentesModoLectura(Boolean.FALSE);
            }
            else{
                setMostrarComponentesModoEscritura(Boolean.FALSE);
                setMostrarComponentesModoLectura(Boolean.TRUE);
            }
            
            logger.warning("Valor mostrarComponentesModoEscritura: " + getMostrarComponentesModoEscritura());
            logger.warning("Valor mostrarComponentesModoLectura: " + getMostrarComponentesModoLectura());
            
        } catch (Exception e) {
            logger.warning("Error al cargar el valor de mostrarComponentesModo Lectura y Escritura" + e.getClass() + e.getMessage());
        }
        /* Fin incidencia FNXII-5171*/
        
        logger.warning("Tipo de invocacion: " + tipoInvocacion);
        if(null != tipoInvocacion){
            logger.warning("Evaluando tipo de invocacion.");
            switch(tipoInvocacion){
            case FenixConstants.TIPO_PAGO_CAPITAL:
                setEsTipoCapital(Boolean.TRUE);
            break;
            case FenixConstants.TIPO_PAGO_INTERES:
                setEsTipoInteres(Boolean.TRUE);
            break;
            default:
            }
        }else{
            logger.warning("tipoInvocacion es NULL.");
        }
        
        logger.warning("IdCondicionFinanciera: " + idCondicionFinancieraTF);
        if(null != idCondicionFinancieraTF){
            logger.warning("Invocando metodo para recuperar lista de Calendarios registrados para la Condicion Financiera.");
            params.put("idCondicioFinanciera", idCondicionFinancieraTF);
            try{
                listaCalendarios = execute(params, "recuperarListaCalendariosRegistrados");
            }catch(Exception e){
                logger.warning("ERROR al ejecutar el OperationBinding recuperarListaCalendariosRegistrados");
            }
            
            logger.warning("Evaluando lista recuperada.");
            if(null != listaCalendarios){
                if(listaCalendarios.size() > 0){
                    String tipoCalendarioCapital = null;
                    String tipoCalendarioInteres = null;
                    
                    try{
                        tipoCalendarioCapital = (String) listaCalendarios.get(FenixConstants.TIPO_PAGO_CAPITAL);
                        tipoCalendarioInteres = (String) listaCalendarios.get(FenixConstants.TIPO_PAGO_INTERES);
                    }catch(Exception e){
                        logger.warning("ERROR al iterar la lista.", e);
                    }
                    
                    logger.warning("Nombre archivo capital: " + tipoCalendarioCapital);
                    logger.warning("Nombre archivo interes: " + tipoCalendarioInteres);
                    if(null != tipoCalendarioCapital){
                        setNombreArchivoExcelCapital(tipoCalendarioCapital);
                        setExisteArchivoCapital(Boolean.TRUE);
                    }else{
                        logger.warning("TipoCalendarioCapital es NULL. No existe registro de CAPITAL.");
                        setNombreArchivoExcelCapital(null);
                        setExisteArchivoCapital(Boolean.FALSE);
                    }
                    
                    if(null != tipoCalendarioInteres){
                        setNombreArchivoExcelInteres(tipoCalendarioInteres);
                        setExisteArchivoInteres(Boolean.TRUE);
                    }else{
                        logger.warning("TipoCalendarioInteres es NULL. No existe registro de INTERES.");
                        setNombreArchivoExcelInteres(null);
                        setExisteArchivoInteres(Boolean.FALSE);
                    }
                }else{
                    logger.warning("El mapa de tipo de calendario es NULL.");
                    setNombreArchivoExcelCapital(null);
                    setExisteArchivoCapital(Boolean.FALSE);
                    setNombreArchivoExcelInteres(null);
                    setExisteArchivoInteres(Boolean.FALSE);
                }
            }else{
                logger.warning("Mapa de calendarios recuperada es NULL.");
                setNombreArchivoExcelCapital(null);
                setExisteArchivoCapital(Boolean.FALSE);
                setNombreArchivoExcelInteres(null);
                setExisteArchivoInteres(Boolean.FALSE);
            }
        }else{
            logger.warning("IdCondicionFinanciera es NULL.");
            setNombreArchivoExcelCapital(null);
            setExisteArchivoCapital(Boolean.FALSE);
            setNombreArchivoExcelInteres(null);
            setExisteArchivoInteres(Boolean.FALSE);
        }
        
        logger.warning("Termina metodo precargarInformacionCalendarioComplejo");
    }

    public void abrirPopUpdescargarFormato(ActionEvent actionEvent) {
        logger.warning("Inside: <abrirPopUpdescargarFormato>");
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpDescargarFormato().show(popupHints);
    }

    public void cerrarPopUpDescargarFormato(ActionEvent actionEvent) {
        getPopUpDescargarFormato().hide();
    }


    public OperationBinding executeOperation(String operation) {
        OperationBinding createParam = getBindingsCont().getOperationBinding(operation);
        return createParam;
    }

    public BindingContainer getBindingsCont() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    
    public void recuperarDescripcionProducto(){
     logger.warning("*Inf, Inicia metodo recuperarDescripcionProducto");
     
        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("setDescripcionProducto");
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("Error al ejecutar operBinding setDescripcionProducto: " + operBinding.getErrors().toString());
            }

        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en setear la Descripcion del Producto.", e);
        }
     
     logger.warning("*Inf, termina metodo recuperarDescripcionProducto");   
    }
    
    public void selectDependiente(ValueChangeEvent valueChangeEvent){
        logger.warning("Inicio selectDependiente");   
        Object value = valueChangeEvent.getNewValue();
        CondicionesFinancierasBean cf = (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
        if(cf!=null){
            if(value !=null){
           //esDependiente = (String) value;
           //JSFUtils.storeOnSession("esDependiente", esDependiente);
               cf.setEsDependiente((String) value);
               
               if(value.toString().equals("Si"))
               {
                   logger.warning("esFactore No");   
                   actualizarPayloadElement("esFactor", "No");
               }
               else
               {
                   logger.warning("tipoTasa Fijo");   
                   //Se actualiza el tipo de tasa de mora a Fijo
                   actualizarPayloadElement("tipoTasa", "Fijo");
               }
           }
        }
        System.out.println("EsDependiente ---> "+value);
    }
    
    public void actualizarPayloadElement(String psElementName, Object poValue) {
      AttributeBinding attributeBinding = null;
    
      attributeBinding = ADFUtils.findControlBinding(psElementName);
      
      if(attributeBinding!=null)
          attributeBinding.setInputValue(poValue);
      
    }
    
    public void selectFactor(ValueChangeEvent valueChangeEvent){
        Object value = valueChangeEvent.getNewValue();
        CondicionesFinancierasBean cf = (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
        if(cf!=null){
            if(value !=null){
           //esFactor = (String) value;
           //JSFUtils.storeOnSession("esFactor", esFactor);
                cf.setEsFactor((String) value);
            }
        }
        System.out.println("EsFactor ---> "+value);
    }
    
    public void selectDiasSpotTasa(ValueChangeEvent valueChangeEvent){
        Integer DiasSpotTasaVO = 1;
        Object value = valueChangeEvent.getNewValue();
        if(value != null){
            try{
                DiasSpotTasaVO = Integer.valueOf((String) value);
            }catch(NumberFormatException ex){
                DiasSpotTasaVO =  null;
            }
         
           JSFUtils.storeOnSession("DiasSpotTasaVO", DiasSpotTasaVO);
        }
        System.out.println("DiasSpotTasaVO ---> "+DiasSpotTasaVO);
    }
    
    public void selectBaseCalculo(ValueChangeEvent valueChangeEvent){
        Integer idBaseCalculo = 1;
        Object value = valueChangeEvent.getNewValue();
        if(value !=null){
           idBaseCalculo = (Integer) value;
           JSFUtils.storeOnSession("idBaseCalculo", idBaseCalculo);
        }
        System.out.println("idBaseCalculo ---> "+idBaseCalculo);
    }
    
    
    /** ------------- ACCESORS ----------------**/

    public void setMoverEntreMeses(Boolean moverEntreMeses) {
        this.moverEntreMeses = moverEntreMeses;
    }

    public Boolean getMoverEntreMeses() {
        return moverEntreMeses;
    }

    public void setAlinearDiaPago(Boolean alinearDiaPago) {
        this.alinearDiaPago = alinearDiaPago;
    }

    public Boolean getAlinearDiaPago() {
        return alinearDiaPago;
    }

    public void setExceptoVencimiento(Boolean exceptoVencimiento) {
        this.exceptoVencimiento = exceptoVencimiento;
    }

    public Boolean getExceptoVencimiento() {
        return exceptoVencimiento;
    }

    public void setTasaVariableVisible(Boolean tasaVariableVisible) {
        this.tasaVariableVisible = tasaVariableVisible;
    }

    public Boolean getTasaVariableVisible() {
        return tasaVariableVisible;
    }

    public void setTasaFijaVisible(Boolean tasaFijaVisible) {
        this.tasaFijaVisible = tasaFijaVisible;
    }

    public Boolean getTasaFijaVisible() {
        return tasaFijaVisible;
    }

    public void setTasaEspecialVisible(Boolean tasaEspecialVisible) {
        this.tasaEspecialVisible = tasaEspecialVisible;
    }

    public Boolean getTasaEspecialVisible() {
        return tasaEspecialVisible;
    }

    public void setEspecificadoFechasVisible(Boolean especificadoFechasVisible) {
        this.especificadoFechasVisible = especificadoFechasVisible;
    }

    public Boolean getEspecificadoFechasVisible() {
        return especificadoFechasVisible;
    }

    public void setEspecificadoPlazosVisible(Boolean especificadoPlazosVisible) {
        this.especificadoPlazosVisible = especificadoPlazosVisible;
    }

    public Boolean getEspecificadoPlazosVisible() {
        return especificadoPlazosVisible;
    }

    public void setSeccionMoraVisible(Boolean seccionMoraVisible) {
        this.seccionMoraVisible = seccionMoraVisible;
    }

    public Boolean getSeccionMoraVisible() {
        return seccionMoraVisible;
    }

    public void setCalendarioComplejoVisible(Boolean calendarioComplejoVisible) {
        this.calendarioComplejoVisible = calendarioComplejoVisible;
    }

    public Boolean getCalendarioComplejoVisible() {
        return calendarioComplejoVisible;
    }

    public void setCalendarioSencilloVisible(Boolean calendarioSencilloVisible) {
        this.calendarioSencilloVisible = calendarioSencilloVisible;
    }

    public Boolean getCalendarioSencilloVisible() {
        return calendarioSencilloVisible;
    }

    public void setPopUpDescargarFormato(RichPopup popUpDescargarFormato) {
        this.popUpDescargarFormato = popUpDescargarFormato;
    }

    public RichPopup getPopUpDescargarFormato() {
        return popUpDescargarFormato;
    }


    public static void setLogger(ADFLogger logger) {
        CondicionesFinancierasBean.logger = logger;
    }

    public static ADFLogger getLogger() {
        return logger;
    }

    public void setBindings(BindingContainer bindings) {
        this.bindings = bindings;
    }


    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Long getIdSolicitud() {
        return idSolicitud;
    }


    public void setIdTareaBPM(Integer idTareaBPM) {
        this.idTareaBPM = idTareaBPM;
    }

    public Integer getIdTareaBPM() {
        return idTareaBPM;
    }

    public void setIdLineaCredito(Long idLineaCredito) {
        this.idLineaCredito = idLineaCredito;
    }

    public Long getIdLineaCredito() {
        return idLineaCredito;
    }

    public void setModoEjecucionTab(Integer modoEjecucionTab) {
        this.modoEjecucionTab = modoEjecucionTab;
    }

    public Integer getModoEjecucionTab() {
        return modoEjecucionTab;
    }

    public void setIdDesembolso(Long idDesembolso) {
        this.idDesembolso = idDesembolso;
    }

    public Long getIdDesembolso() {
        return idDesembolso;
    }

    public void setIdCondicionFinanciera(Long idCondicionFinanciera) {
        this.idCondicionFinanciera = idCondicionFinanciera;
    }

    public Long getIdCondicionFinanciera() {
        return idCondicionFinanciera;
    }

    public void setProductoFlex(String productoFlex) {
        this.productoFlex = productoFlex;
    }

    public String getProductoFlex() {
        return productoFlex;
    }
    
    public void setMostrarSoloCalendarioSencillo(Boolean mostrarSoloCalendarioSencillo) {
        this.mostrarSoloCalendarioSencillo = mostrarSoloCalendarioSencillo;
    }

    public Boolean getMostrarSoloCalendarioSencillo() {
        return mostrarSoloCalendarioSencillo;
    }
    
    public void setLecturaIFI(Boolean lecturaIFI) {
        this.lecturaIFI = lecturaIFI;
    }

    public Boolean getLecturaIFI() {
        return lecturaIFI;
    }

    public void setIdTipoTasaCondicion(Integer idTipoTasaCondicion) {
        this.idTipoTasaCondicion = idTipoTasaCondicion;
    }

    public Integer getIdTipoTasaCondicion() {
        return idTipoTasaCondicion;
    }

    public void setIdTipoTasaMora(Integer idTipoTasaMora) {
        CondicionesFinancierasBean.idTipoTasaMora = idTipoTasaMora;
    }

    public Integer getIdTipoTasaMora() {
        return idTipoTasaMora;
    }

    public void setMostrarSpreadVariable(Boolean mostrarSpreadVariable) {
        this.mostrarSpreadVariable = mostrarSpreadVariable;
    }

    public Boolean getMostrarSpreadVariable() {
        return mostrarSpreadVariable;
    }

    public void setNombreArchivoExcelCapital(String nombreArchivoExcelCapital) {
        this.nombreArchivoExcelCapital = nombreArchivoExcelCapital;
    }

    public String getNombreArchivoExcelCapital() {
        return nombreArchivoExcelCapital;
    }

    public void setNombreArchivoExcelInteres(String nombreArchivoExcelInteres) {
        this.nombreArchivoExcelInteres = nombreArchivoExcelInteres;
    }

    public String getNombreArchivoExcelInteres() {
        return nombreArchivoExcelInteres;
    }

    public void setExisteArchivoCapital(Boolean existeArchivoCapital) {
        this.existeArchivoCapital = existeArchivoCapital;
    }

    public Boolean getExisteArchivoCapital() {
        return existeArchivoCapital;
    }

    public void setExisteArchivoInteres(Boolean existeArchivoInteres) {
        this.existeArchivoInteres = existeArchivoInteres;
    }

    public Boolean getExisteArchivoInteres() {
        return existeArchivoInteres;
    }

    public void setEsTipoCapital(Boolean esTipoCapital) {
        this.esTipoCapital = esTipoCapital;
    }

    public Boolean getEsTipoCapital() {
        return esTipoCapital;
    }

    public void setEsTipoInteres(Boolean esTipoInteres) {
        this.esTipoInteres = esTipoInteres;
    }

    public Boolean getEsTipoInteres() {
        return esTipoInteres;
    }
    
    public void setFileNameArchivoCapital(String fileNameArchivoCapital) {
        this.fileNameArchivoCapital = fileNameArchivoCapital;
    }

    public String getFileNameArchivoCapital() {
        return fileNameArchivoCapital;
    }

    public void setFileNameArchivoInteres(String fileNameArchivoInteres) {
        this.fileNameArchivoInteres = fileNameArchivoInteres;
    }

    public String getFileNameArchivoInteres() {
        return fileNameArchivoInteres;
    }

    public void setMostrarComponentesModoLectura(Boolean mostrarComponentesModoLectura) {
        this.mostrarComponentesModoLectura = mostrarComponentesModoLectura;
    }

    public Boolean getMostrarComponentesModoLectura() {
        return mostrarComponentesModoLectura;
    }

    public void setMostrarComponentesModoEscritura(Boolean mostrarComponentesModoEscritura) {
        this.mostrarComponentesModoEscritura = mostrarComponentesModoEscritura;
    }

    public Boolean getMostrarComponentesModoEscritura() {
        return mostrarComponentesModoEscritura;
    }
    
    public void setFrecPrincipalVisible(Boolean frecPrincipalVisible) {
        this.frecPrincipalVisible = frecPrincipalVisible;
    }

    public Boolean getFrecPrincipalVisible() {
        return frecPrincipalVisible;
    }
    
    public void setFrecInteresVisible(Boolean frecInteresVisible) {
        this.frecInteresVisible = frecInteresVisible;
    }

    public Boolean getFrecInteresVisible() {
        return frecInteresVisible;
    }

    public void setEsOnSuggestTasaReferencia(Boolean esOnSuggestTasaReferencia) {
        this.esOnSuggestTasaReferencia = esOnSuggestTasaReferencia;
    }

    public Boolean getEsOnSuggestTasaReferencia() {
        return esOnSuggestTasaReferencia;
    }
     public void setFrecRevSpreadVisible(Boolean frecRevSpreadVisible) {
        this.frecRevSpreadVisible = frecRevSpreadVisible;
    }

    public Boolean getFrecRevSpreadVisible() {
        return frecRevSpreadVisible;
    }
    public void setEsDependiente(String esDependiente) {
        this.esDependiente = esDependiente;
    }

    public String getEsDependiente() {
        return esDependiente;
    }

    public void setEsFactor(String esFactor) {
        this.esFactor = esFactor;
    }

    public String getEsFactor() {
        return esFactor;
    }

    public void setValorFactor(String valorFactor) {
        this.valorFactor = valorFactor;
    }

    public String getValorFactor() {
        return valorFactor;
    }

    public void setValorTasa(String valorTasa) {
        this.valorTasa = valorTasa;
    }

    public String getValorTasa() {
        return valorTasa;
    }

    public void setTipoTasaCB(String tipoTasaCB) {
        this.tipoTasaCB = tipoTasaCB;
    }

    public String getTipoTasaCB() {
        return tipoTasaCB;
    }
    
    public void setDiaPagoCliente(Integer diaPagoCliente) {
        this.diaPagoCliente = diaPagoCliente;
    }

    public Integer getDiaPagoCliente() {
        return diaPagoCliente;
    }
}