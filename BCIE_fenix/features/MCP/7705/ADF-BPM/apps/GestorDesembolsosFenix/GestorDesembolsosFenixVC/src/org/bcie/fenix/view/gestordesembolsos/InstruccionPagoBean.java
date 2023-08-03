package org.bcie.fenix.view.gestordesembolsos;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class InstruccionPagoBean implements Serializable {
    @SuppressWarnings("compatibility:1480795895801197896")
    private static final long serialVersionUID = 1L;

    public static ADFLogger logger = null;
    
    private Long idOperacion = null;
    private Long idContratoDesembolso = null;
    private Long idTransferencia = null;
    private Integer modoEjecucion = null;
    private Integer estadoContrato;
    private String usuario;
    private String instanciaDProceso;
    private Long idInstrucionPago = null;
    private String numCuentaBenef = null;
    private Integer idTcaTipoMoneda = null;    
    private String descripcionMoneda = null;

    private Boolean accionesTransferenciasVisible = Boolean.TRUE;
    private Boolean contenedorCamposTransferencia = Boolean.TRUE;
    private Boolean accionesInstruccionPagoVisible = Boolean.FALSE;    
    private Boolean modoLetura = Boolean.TRUE;
    private Boolean modoLeturaDatTrans = Boolean.TRUE;
    private Boolean formularioVisible = Boolean.TRUE;
    
    private BigDecimal totalDesembolso = null;
    private BigDecimal montoDescuento = null;
    private BigDecimal cargosDesembolso = null;
    private BigDecimal comisionesDesembolso = null;
    private BigDecimal totalTransferir = null;
    private BigDecimal montoTotalTransferencias = null;
    private BigDecimal disponibleTransferir = null;
    
    private Boolean montoDescuentoVisible = Boolean.FALSE;
    
    private Boolean deshabilitarBtnEdicionTran = Boolean.TRUE;
    private Boolean deshabilitarBtnEliminarTran = Boolean.TRUE;
    private Boolean deshabilitarBtnEdicionInst = Boolean.TRUE;
    private Boolean deshabilitarBtnEliminarInst= Boolean.TRUE;
    private Boolean deshabilitarBtnGuardar = Boolean.TRUE;
    private Boolean btnCancelarVisible = Boolean.FALSE;
    private Boolean btnGuardarVisible = Boolean.FALSE;

    private Boolean modoAplicacionTrans = Boolean.FALSE;
    private Integer idTcaEstadoContrato = null;
    private Boolean esNuevaTransferencia = Boolean.FALSE;
    private BigDecimal montoTransfrenciaSeleccionada = BigDecimal.ZERO;
    private Boolean contratoRegistrado = Boolean.FALSE;

    private Long idTransferenciaActal = null;
    private Boolean accionAgregar = Boolean.FALSE;

    private Boolean errorParametros = Boolean.FALSE;
    public static final Integer MODO_EJECUCION_LECTURA = 1;
    public static final Integer MODO_EJECUCION_EDICION_DE_TRANSFERENCIAS = 2;
    public static final Integer MODO_EJECUCION_APLICACION_TRANSFERENCIAS = 3;
   
    public InstruccionPagoBean() {
        if (logger == null){
            logger = ADFLogger.createADFLogger(InstruccionPagoBean.class);
        }
    }
   
   
   public void precarga() {
        logger.warning("***Inicia metodo de precarga en InstruccionesPago");

              idOperacion = (null == JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}")) ? null
                          : new Long(JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}").toString());

     idContratoDesembolso = (null == JSFUtils.resolveExpression("#{pageFlowScope.idContratoDesembolso}")) ? null 
                          : new Long(JSFUtils.resolveExpression("#{pageFlowScope.idContratoDesembolso}").toString());

            modoEjecucion = (null == JSFUtils.resolveExpression("#{pageFlowScope.modoEjecucion}") ? null 
                          : Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.modoEjecucion}").toString()));

          idTcaTipoMoneda = (null == JSFUtils.resolveExpression("#{pageFlowScope.idTcaTipoMoneda}") ? null 
                          : Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.idTcaTipoMoneda}").toString()));

        descripcionMoneda = (null == JSFUtils.resolveExpression("#{pageFlowScope.descripcionMoneda}")) ? null 
                          : JSFUtils.resolveExpression("#{pageFlowScope.descripcionMoneda}").toString();

        instanciaDProceso = (null == JSFUtils.resolveExpression("#{pageFlowScope.varInstanciaProceso}") ? null 
                          : (JSFUtils.resolveExpression("#{pageFlowScope.varInstanciaProceso}").toString()));
        
      idTcaEstadoContrato = (null == JSFUtils.resolveExpression("#{pageFlowScope.idEstadoContrato}") ? null 
                          : Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.idEstadoContrato}").toString()));
       
            
        logger.warning("*Inf, idContratoDesembolso ->"+idContratoDesembolso);
        logger.warning("*Inf, idOperacion ->"+idOperacion);
        logger.warning("*Inf, modoEjecucion ->"+modoEjecucion);
        logger.warning("*Inf, instanciaProceso ->"+instanciaDProceso);
        logger.warning("*Inf, idTcaTipoMoneda ->"+idTcaTipoMoneda);
        logger.warning("*Inf, descripcionMoneda ->"+descripcionMoneda);            
        
        if( idTcaTipoMoneda == null || descripcionMoneda == null){            
             recuperararDatosMoneda(idContratoDesembolso);
         }
        

        if (idContratoDesembolso == null || idOperacion == null || idTcaTipoMoneda == null ||
            descripcionMoneda == null) {
            logger.warning("*Inf, Important! Instrucciones pago en tab de transferencias no recibio todos los parametros de entrada del Task");
            errorParametros = Boolean.TRUE;
            contenedorCamposTransferencia = Boolean.FALSE;
            formularioVisible = Boolean.FALSE;
            accionesInstruccionPagoVisible = Boolean.FALSE;
            accionesTransferenciasVisible = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("Error en Tab Transferencias, Instrucciones pago no recibio todos los parametros de entrada requeridos");
        } else {

            if (idContratoDesembolso != null) {
              
               
                cargarTablaTransferencias(idContratoDesembolso);

                logger.warning("*Inf, inicia el switch de modo de ejecucion...");
                switch (modoEjecucion) {
                case FenixConstants.MODO_EJECUCION_LECTURA_INT:
                    logger.warning("MODO DE EJECUCION LECTURA DE TRANSFERENCIAS");
                    accionesInstruccionPagoVisible = Boolean.FALSE;
                    accionesTransferenciasVisible = Boolean.FALSE;
                    break;
                case FenixConstants.MODO_EJECUCION_ESCRITURA_INT:
                    logger.warning("MODO DE EJECUCION EDICION DE TRANSFERENCIAS");
                    accionesInstruccionPagoVisible = Boolean.TRUE;
                    accionesTransferenciasVisible = Boolean.TRUE; 
                    cargarCatalogoInstruccionPago(idOperacion);
                    break;

                case FenixConstants.MODO_EJECUCION_TRANSFERENCIAS_APLICACION_INT:
                    logger.warning("MODO DE APLICACION DE TRANSFERENCIAS");
                    modoAplicacionTrans = Boolean.TRUE;
                    accionesInstruccionPagoVisible = Boolean.FALSE;
                    accionesTransferenciasVisible = Boolean.FALSE;
                    obtenerDatosContrato();
                    break;
                }

                logger.warning("*Inf termina el switch de modo de ejecion");

            } else {
                logger.warning("Error, No se pudo cargar la tabla de transferencias el IdContrato es NULL");
            }
            logger.warning("*Inf valor de readOnly para Mensaje y Monto transferencia: " + modoLeturaDatTrans);

        }
        //recuperamos la sesión de usuario
        if (null != JSFUtils.resolveExpression("#{securityContext.userName}")) {
            setUsuario((String) JSFUtils.resolveExpression("#{securityContext.userName}"));
        }
        logger.warning("usuario: " + usuario);

        logger.warning("*Inf, termina metodo de precarga en InstruccionesPago");
        logger.warning("*Inf, termina metodos de precarga y ejecucion en EL TAB TRANSFERENCIAS");
    }
    
    
   public void calcularDatosTabla() {
        logger.warning("Inicia metodo calcularDatosTabla.");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        recuperarMontoDesembolsarContrato();
        recuperarTotalCargosDesembolso(idContratoDesembolso);
        recuperarTotalComisionesOtrosCargos(idContratoDesembolso);
        recuperarMontoTotalTransferencias();
        recuperarMontoDescuento(idContratoDesembolso);

        logger.warning("*--Monto cargosDesembolso recuperado->" + cargosDesembolso);
        logger.warning("*--Monto descuento recuperado->" + montoDescuento);
        logger.warning("*--Comisiones de Desembolso recuperados->" + comisionesDesembolso);
        logger.warning("*--Monto total de la transferencia recuperado->" + montoTotalTransferencias);

        try {
            if (totalDesembolso != null) {

                totalTransferir =
                    (cargosDesembolso != null && comisionesDesembolso != null) ?
                    totalDesembolso.subtract(cargosDesembolso.add(comisionesDesembolso)) : null;
                
                if(montoDescuento == null)
                {
                    montoDescuento =  BigDecimal.ZERO;
                    logger.warning("*--Monto descuento se asigno cero->" + montoDescuento);
                }
                
                if(montoDescuentoVisible)
                       totalTransferir = (null != totalTransferir)? totalTransferir.subtract(montoDescuento) : totalTransferir;

                disponibleTransferir =
                    (totalTransferir != null && montoTotalTransferencias != null) ?
                    totalTransferir.subtract(montoTotalTransferencias) : null;
              
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("montoDisponibleTransferir", disponibleTransferir);  
                
            } else {
                logger.warning("*** El parametro totalDesembolso es null");
            }
        } catch (Exception e) {
            logger.warning("Error al obtener totalTransferir y disponibleTransferir ", e);
        }

        logger.warning("*--MontoTotalTransferencias recuperado->" + montoTotalTransferencias);
        logger.warning("*--DisponibleTransferir recuperado->" + disponibleTransferir);


        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning("Termina metodo calcularDatosTabla con una duracion de: " + tiempo + " segundos");
    }
    
    
    public Boolean cargarTablaTransferencias(Long idContrato) {
      logger.warning("*Inf, inicia el metodo cargarTablaTransferencias idContrato->" + idContrato);
        Row transferenciaCurrent = null;
        Boolean existenRegistrosDeTransferencias = Boolean.FALSE;
        
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("recuperarDatos");
            operationBinding.getParamsMap().put("idContrato", idContrato);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding recuperardatos devuelve errores->"+operationBinding.getErrors());
            }else{                
               
                transferenciaCurrent = (null == operationBinding.getResult() )? null 
                                     : (Row)operationBinding.getResult();                                 
             }            
        
        }catch(Exception e) {
            logger.warning("***Error en metodo cargarTablaTransferencias ->", e);
        }
        
        if (transferenciaCurrent != null) {
            deshabilitarBtnEdicionTran = Boolean.FALSE;
            deshabilitarBtnEliminarTran = Boolean.FALSE;
            deshabilitarBtnEdicionInst = Boolean.FALSE;
            deshabilitarBtnEliminarInst = Boolean.FALSE;
            deshabilitarBtnGuardar = Boolean.FALSE;
            existenRegistrosDeTransferencias = Boolean.TRUE;
            
            idTransferencia = (Long)transferenciaCurrent.getAttribute("IdTransferencia");
            
        }else{
            deshabilitarBtnEdicionTran = Boolean.TRUE;
            deshabilitarBtnEliminarTran = Boolean.TRUE;
            contenedorCamposTransferencia = Boolean.FALSE;
            formularioVisible = Boolean.FALSE;
            logger.warning("*Inf, No hay una transferencia Current");
            
        }
    
        calcularDatosTabla();
        
                
      logger.warning("*Inf, termina el metodo cargarTablaTransferencias");
      return existenRegistrosDeTransferencias;
    }
    
    
    public void cargarTablaTransferencias2(Long idContrato) {
        logger.warning(" inicia el metodo para cargar la tabla de transferencias idContrato->" + idContrato);
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        Row fila = null;
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("iniciarTablaTansacciones");
            operationBinding.getParamsMap().put("idContrato", idContrato);
            fila = (Row) operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning(" :( OperationBinding iniciarTablaTansacciones devuelve errores");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                       "***Error en consultarContratoDesembolso " + e.getClass() + ":" + e.getMessage());
        }

        if (fila != null) {

            deshabilitarBtnEdicionTran = Boolean.FALSE;
            deshabilitarBtnEliminarTran = Boolean.FALSE;
            deshabilitarBtnEdicionInst = Boolean.FALSE;
            deshabilitarBtnEliminarInst = Boolean.FALSE;
            deshabilitarBtnGuardar = Boolean.FALSE;

            Long idInstruccion = new Long(fila.getAttribute("IdInstruccionPago").toString());
            logger.warning("El primer idInstruccionPago recuperado es ->" + idInstruccion);
            idInstrucionPago = idInstruccion;

            establecerInstruccionActual(idInstrucionPago);

            idTransferencia =
                (null == fila.getAttribute("IdTransferencia")) ? null : (Long) fila.getAttribute("IdTransferencia");
            numCuentaBenef =
                (null == fila.getAttribute("NumeroCtaBenef")) ? null : fila.getAttribute("NumeroCtaBenef").toString();

            BigDecimal montoTransf =
                (null == fila.getAttribute("MontoTransferencia")) ? BigDecimal.ZERO :
                (BigDecimal) fila.getAttribute("MontoTransferencia");

            logger.warning("*Inf, El id de transferencia recuperado es ->" + idTransferencia);
            establecerTransferenciaActual(idTransferencia);
            logger.warning("*Inf, Monto transferencia recuperado : " + montoTransf);

            setMontoTransfrenciaSeleccionada(montoTransf);

            accionesInstruccionPagoVisible = Boolean.TRUE;
            if (numCuentaBenef != null) {
                logger.warning("numero cuenta recuperado es ->" + numCuentaBenef);
                recuperarDatosCuenta(numCuentaBenef);
            } else {
                logger.warning("El numero de cuenta del primer registro es resuelto null");
            }
        } else {
            deshabilitarBtnEdicionTran = Boolean.TRUE;
            deshabilitarBtnEliminarTran = Boolean.TRUE;
            contenedorCamposTransferencia = Boolean.FALSE;
            formularioVisible = Boolean.FALSE;
            logger.warning("El row de transferencias asociadas al contrato de desembolso es null");
        }
        calcularDatosTabla();
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning(" ******  Termina el metodo de cargarTablaTransferencias con una duracion de: " + tiempo +
                       " segundos");
    }
    
    
    public void establecerTransferenciaActual(Long idTransferencia){
        logger.warning("*Inf, Inicia metodo establecerTransferenciaActual idTransferencia : "+idTransferencia);
            try{
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("establecerFilaComoActual");
                    operationBinding.getParamsMap().put("idTransaccion", idTransferencia);
                    operationBinding.execute();    
                
                     if(!operationBinding.getErrors().isEmpty()){
                        logger.warning(" :( OperationBinding devuelve errores");
                        JSFUtils.addFacesErrorMessage("**Error, operationBinding establecerFilaComoActual devuelve errores no se recuperaro transferencia ");  
                    }
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "***Error ->" + e.getClass() + ":" + e.getMessage());
            }    
        logger.warning("*Inf, termina metodo establecerTransferenciaActual");        
        }
    
    
    
    public void establecerInstruccionActual(Long idInstruccion) {
        logger.warning("Inf, Inicia metodo establecerInstruccionActual ->" + idInstruccion);
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("establecerFilaActual");
            operationBinding.getParamsMap().put("idInstruccionPago", idInstruccion);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning(" :( OperationBinding devuelve errores");
                JSFUtils.addFacesErrorMessage("**Error, operationBinding establecerInstruccionActual regresa errores ");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "***Error -> " + e);
        }

        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning(" Termina metodo establecerInstruccionActual con una duracion de: " + tiempo + " segundos");
    }
   
    public void recuperarDatosCuenta(String numCuentaBeneficiario) {
        logger.warning("*** inicia method recuperarDatosCuenta");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        Row rowInstruccionPago = null;
        logger.warning("*** Numero de cuenta recuperado->" + numCuentaBeneficiario);
        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("buscarRecuperarNumCuentaBnf");
            operBinding.getParamsMap().put("numCuenta", numCuentaBeneficiario);
            rowInstruccionPago = (Row) operBinding.execute();

            String tipoOpcionBeneficiario = (String) rowInstruccionPago.getAttribute("TipoOpcionBenef");
            String tipoOpcionBancoBenef = (String) rowInstruccionPago.getAttribute("TipoOpcionBanBenef");
            String tipoOpcionBancoInter = (String) rowInstruccionPago.getAttribute("TipoOpcionBanInter");

            //TransferenciasBean transferenciasBean = (TransferenciasBean)JSFUtils.resolveExpression("#{pageFlowScope.TransferenciasBean}");
            //                 transferenciasBean.setTipoOpcionBenef(tipoOpcionBeneficiario);
            //                  tipoOpcionBenef = tipoOpcionBeneficiario;
            //                  tipoOpcionBanBenef = tipoOpcionBancoBenef;
            //                  tipoOpcionBancInter = tipoOpcionBancoInter;

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning(" :( OperationBinding devuelve errores");
                JSFUtils.addFacesErrorMessage("Error, operationBinding devuelve errores");
            }

        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                       "***Error en consultarContratoDesembolso " + e.getClass() + ":" + e.getMessage());
        }

        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning("Termina metodo recuperarDatosCuenta con una duracion de: " + tiempo + " segundos");
    }
   
   
    public void recuperarTotalCargosDesembolso(Long idContrato) {
        logger.warning(" Inicia metodo recuperarTotalCargosDesembolso");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        BigDecimal totalCargos = null;
        BindingContainer bindings = null;
        OperationBinding operationBinding = null;
        
        logger.warning("idContrato: " + idContrato);
        try {
            bindings = ADFUtils.getBindingContainer();
            operationBinding = bindings.getOperationBinding("obtenerTotalMontosPorIdContratoDesembolso");
            operationBinding.getParamsMap().put("idContratoDesembolso", idContrato);
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                logger.severe("No se ejecuto el metodo recuperarTotalCargosDesembolso");
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }else {
                if(operationBinding.getResult() != null){
                    totalCargos = (BigDecimal) operationBinding.getResult();
                    logger.warning("totalCargos : "+totalCargos);
                }
            }
            
            if (totalCargos == null){
                logger.warning("totalCargos es nulo");
                totalCargos = new BigDecimal(0);
                logger.warning("asigna valor a totalCargos : "+totalCargos);
            }

            cargosDesembolso = totalCargos;

            logger.warning("*Inf, cargosDesembolso : " + cargosDesembolso);

            if (cargosDesembolso == null) {
                cargosDesembolso = new BigDecimal(0);
            }

            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("*Inf, OperationBinding recuperarTotalCargosDesembolso devuelve errores");
                JSFUtils.addFacesErrorMessage("**Error, operationBinding devuelve errores no se recuperaron los cargos del contrato");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "***Error en consultar los cargos del Desembolso ", e);
        }

        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning(" Termina metodo recuperarTotalCargosDesembolso con una duracion de: " + tiempo + " segundos");
    }
   
    public void recuperarTotalComisionesOtrosCargos(Long idContrato) {
        logger.warning(" Inicia metodo recuperarTotalComisionesOtrosCargos");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("cargarConsultaComisionesOtrosCargos");
            operationBinding.getParamsMap().put("IdContrato", idContrato);
            comisionesDesembolso = (BigDecimal) operationBinding.execute();

            if (comisionesDesembolso == null) {
                comisionesDesembolso = new BigDecimal(0);
            }

            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning(" :( OperationBinding devuelve errores");
                JSFUtils.addFacesErrorMessage("Error, operationBinding devuelve errores no se recuperaron los cargos del contrato");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                       "***Error en consultar las comisiones del Desembolso " + e.getClass() + ":" + e.getMessage());
        }

        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning("Termina metodo recuperarTotalComisionesOtrosCargos con una duracion de: " + tiempo +
                       " segundos");
    }
    
    public void recuperarMontoDescuento(Long idContrato){
        logger.warning("Inicia metodo recuperarMontoDescuento ");
        
        Map datosMontoDescuento = new HashMap();
        BigDecimal montoDesc = new BigDecimal(0);
        Integer tipoTasaDesembolso = null;
        
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("recuperarMontoDescuento");
            operationBinding.getParamsMap().put("idDesembolso", idContrato);
            operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()) {
                logger.warning(" OperationBinding devuelve errores");
                JSFUtils.addFacesErrorMessage("Error, al recuperar MontoDescuento: ");
            }else{
                datosMontoDescuento = (Map)operationBinding.getResult();
                 
                tipoTasaDesembolso = (Integer)datosMontoDescuento.get("tipoTasaDesembolso"); 
                logger.warning("tipoTasaDesembolso: " + tipoTasaDesembolso);
            }
            
        } catch (Exception e) {
            logger.warning("***Error al recuperar MontoDescuento ",  e);
        }
        
        try {
            logger.warning("***Inicio de obtenerMontoDescuento");
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("obtenerMontoDescuento");
            operationBinding.getParamsMap().put("idContratoDesembolso", idContrato);
            operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()) {
                logger.warning(" OperationBinding obtener Monto Descuento devuelve errores");
                JSFUtils.addFacesErrorMessage("Error, al obtener Monto Descuento");
            }else{
                logger.warning("***Se obtuvo un valor de obtenerMontoDescuento");
                montoDesc = (BigDecimal)operationBinding.getResult();
                logger.warning("montoDesc: " + montoDesc); 
            }
            
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "***Error al obtenerMontoDescuento: ",  e);
        }
        
        
        if(tipoTasaDesembolso != null && tipoTasaDesembolso.compareTo(1) == 0)
             montoDescuentoVisible = Boolean.TRUE;
        else
            montoDescuentoVisible = Boolean.FALSE;
        
        montoDescuento = montoDesc;
    }
    
   
   
    public void recuperarMontoDesembolsarContrato() {
        logger.warning("Inicia metodo recuperarMontoDesembolsarContrato ");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("getContratoSeleccionado");
            Row fila = (Row) operationBinding.execute();

            if (fila != null) {
                totalDesembolso = (BigDecimal) fila.getAttribute("MontoDesembolsar");
                //Aprovechamos y traemos el estado del contrato de desembolso
                idTcaEstadoContrato = (Integer) fila.getAttribute("IdTcaEstado");

                if (idTcaEstadoContrato.compareTo(FenixConstants.ESTADO_CONTRATO_DESEMBOLSO_REGISTRADO) == 0 ||
                    idTcaEstadoContrato.compareTo(FenixConstants.ESTADO_CONTRATO_DESEMBOLSO_DESEMBOLSADO) == 0) {
                    logger.warning("Mostrar Modo aplicacion Trans.");
                    modoAplicacionTrans = Boolean.TRUE;
                }
            }

            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning(" :( OperationBinding devuelve errores");
                JSFUtils.addFacesErrorMessage("Error, operationBinding devuelve errores no se recupero el monto a desembolsar del contrato de desembolso");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                       "***Error en consultar el monto total de las transacciones " + e.getClass() + ":" +
                       e.getMessage());
        }

        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning("Termina metodo recuperarMontoDesembolsarContrato con una duracion de: " + tiempo + " segundos");
    }

    public void recuperarMontoTotalTransferencias() {
        logger.warning(" Inicia metodo recuperarMontoTotalTransferencias");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("recuperarMontoTotalDeTransferencias");
            operationBinding.execute();
           
            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding recuperarMontoTotalTransferencias devuelve errores->"
                                                                  +operationBinding.getErrors().toString());
                JSFUtils.addFacesErrorMessage("Error, operationBinding  recuperarMontoTotalTransferencias devuelve errores ");
            }else{
                montoTotalTransferencias = (null == operationBinding.getResult()) ? BigDecimal.ZERO
                                          : (BigDecimal) operationBinding.getResult();  
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                       "***Error en consultar el monto total de las transferencias ->", e);
        }

        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning("Termina metodo recuperarMontoTotalTransferencias con una duracion de: " + tiempo + " segundos");
    }
   
   
    public void cargarCatalogoInstruccionPago(Long idOperacion) {
        logger.warning("*Inf, Inicia metodo cargarCatalogoInstruccionPago : " + idOperacion);
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        if (idOperacion != null) {

            try {
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("cargarTabla");
                operationBinding.getParamsMap().put("idOperacion", idOperacion);
                operationBinding.execute();

                if (!operationBinding.getErrors().isEmpty()) {
                    logger.warning("*Error, OperationBinding cargarTabla devuelve errores metodo cargarCatalogoInstruccionPago");
                    JSFUtils.addFacesErrorMessage("Error al ejecutar al cargar el catalogo de instrucciones de pago " +
                                                  operationBinding.getErrors());
                }

            } catch (Exception e) {
                logger.warning("***Error al filtrarInstruccionesByIdOperacion " + e);
            }

        } else {
            logger.warning("***Error, IdOperacion es resuelto a null no se filtraran las operaciones de pago");
        }
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/ 1000; //Calculamos los milisegundos de diferencia
        logger.warning("*Inf, Termina metodo cargarCatalogoInstruccionPago con una duracion de: "+tiempo+" segundos");
    }
    
    
    public void obtenerDatosContrato() {
        logger.warning("*Inf, Inicia metodo obtenerDatosContrato");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        Row fila = null;
        String numeroContrato = null;
        java.sql.Timestamp fechaEfectiva = null;
        
        String numeroContratoflexcube = null;
        
        try{
            logger.warning("Ejecutando recuperarContratoFlexcube");
            Long idContratoDesembolso = getIdContratoDesembolso();
            logger.warning("idContratoDesembolso: " + idContratoDesembolso);
            
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("recuperarContratoFlexcube");
            operBinding.getParamsMap().put("idContrato", idContratoDesembolso);
            operBinding.execute();
            
            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("***Error, operationBinding recuperarContratoFlexcube " + operBinding.getErrors());
            } else {
                numeroContratoflexcube = (String) operBinding.getResult();
                logger.warning("numeroContratoflexcube: " + numeroContratoflexcube);
            }
            
        }catch(Exception exp)
        {
            logger.warning("Error ejecutando recuperarContratoFlexcube: " + exp);
        }
        
        try {

            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("getContratoSeleccionado");
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("***Error, operationBinding getContratoSeleccionado " + operBinding.getErrors());
                JSFUtils.addFacesErrorMessage("Error, " + operBinding.getErrors());
            } else {

                fila = (Row) operBinding.getResult();

                if (fila != null) {

                    numeroContrato =
                        (null == fila.getAttribute("ContratoFlexcube")) ? null :
                        fila.getAttribute("ContratoFlexcube").toString();
                    
                    if(numeroContrato == null){
                        numeroContrato = numeroContratoflexcube;
                        logger.warning("numeroContratoflexcube se asigno a numeroContrato: " + numeroContrato);
                    }

                    fechaEfectiva =
                        (null == fila.getAttribute("FechaEfectiva")) ? null :
                        (java.sql.Timestamp) fila.getAttribute("FechaEfectiva"); 

                    logger.warning("numeroContrato recuperado del contrato->" + numeroContrato);
                    logger.warning("fechaEfectiva recuperado del contrato->" + fechaEfectiva);
                    
                    //if (numeroContrato != null && fechaEfectiva != null)
                    if (numeroContrato != null)
                    {
                        setContratoRegistrado(Boolean.TRUE);
                    }
                    else
                        logger.warning("*Inf, Importante! El contrato no esta registrado no se podran aplicar transferencias");

                } else {
                    logger.warning("*** Error, No se pudo recuperar un current en contrato desembolso>");
                }


            }
        } catch (Exception e) {
            logger.warning("***Error, al consultar los datos del desembolso ", e);

        }

        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning("*Inf, Termina metodo obtenerDatosContrato con una duracion de: " + tiempo + " segundos");
    }
    
    
    public void recuperararDatosMoneda(Long idDesembolso){         
        logger.warning("*Inf, inicia metodo recuperararDatosMoneda");
        HashMap datosMoneda = new HashMap();
        
        try{
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("obtenerDatosMoneda");
            operBinding.getParamsMap().put("idDesembolso", idDesembolso);
             datosMoneda = (HashMap)operBinding.execute();
            
            
                idTcaTipoMoneda = (Integer)datosMoneda.get("idTipoMoneda");
                descripcionMoneda = (String)datosMoneda.get("descripcionMoneda");                                        
            
            
        }catch(Exception e){
            logger.warning("ERROR al ejecutar OperationBinding obtenerDescripcionMoneda.", e);
        }
        logger.warning("*Inf, termina metodo recuperararDatosMoneda");            
    }
    
    
    
   
    /********      Accesors       *****/
    
    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }
    public void setIdInstrucionPago(Long idInstrucionPago) {
        this.idInstrucionPago = idInstrucionPago;
    }

    public Long getIdInstrucionPago() {
        return idInstrucionPago;
    }
    
    public void setNumCuentaBenef(String numCuentaBenef) {
        this.numCuentaBenef = numCuentaBenef;
    }

    public String getNumCuentaBenef() {
        return numCuentaBenef;
    }
    
    public void setModoLetura(Boolean modoLetura) {
        this.modoLetura = modoLetura;
    }

    public Boolean getModoLetura() {
        return modoLetura;
    }

    public void setFormularioVisible(Boolean formularioVisible) {
        this.formularioVisible = formularioVisible;
    }

    public Boolean getFormularioVisible() {
        return formularioVisible;
    }
    public void setDescripcionMoneda(String descripcionMoneda) {
        this.descripcionMoneda = descripcionMoneda;
    }

    public String getDescripcionMoneda() {
        return descripcionMoneda;
    }
    
    public void setIdTransferencia(Long idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public Long getIdTransferencia() {
        return idTransferencia;
    }
    public void setModoLeturaDatTrans(Boolean modoLeturaDatTrans) {
        this.modoLeturaDatTrans = modoLeturaDatTrans;
    }

    public Boolean getModoLeturaDatTrans() {
        return modoLeturaDatTrans;
    }
    public void setIdContratoDesembolso(Long idContratoDesembolso) {
        this.idContratoDesembolso = idContratoDesembolso;
    }

    public Long getIdContratoDesembolso() {
        return idContratoDesembolso;
    }

    public void setIdTcaTipoMoneda(Integer idTcaTipoMoneda) {
        this.idTcaTipoMoneda = idTcaTipoMoneda;
    }

    public Integer getIdTcaTipoMoneda() {
        return idTcaTipoMoneda;
    }
    
    public void setContenedorCamposTransferencia(Boolean contenedorCamposTransferencia) {
        this.contenedorCamposTransferencia = contenedorCamposTransferencia;
    }

    public Boolean getContenedorCamposTransferencia() {
        return contenedorCamposTransferencia;
    }
    
    public void setAccionesInstruccionPagoVisible(Boolean accionesInstruccionPagoVisible) {
        this.accionesInstruccionPagoVisible = accionesInstruccionPagoVisible;
    }

    public Boolean getAccionesInstruccionPagoVisible() {
        return accionesInstruccionPagoVisible;
    }
    public void setTotalDesembolso(BigDecimal totalDesembolso) {
        this.totalDesembolso = totalDesembolso;
    }

    public BigDecimal getTotalDesembolso() {
        return totalDesembolso;
    }

    public void setCargosDesembolso(BigDecimal cargosDesembolso) {
        this.cargosDesembolso = cargosDesembolso;
    }

    public BigDecimal getCargosDesembolso() {
        return cargosDesembolso;
    }

    public void setComisionesDesembolso(BigDecimal comisionesDesembolso) {
        this.comisionesDesembolso = comisionesDesembolso;
    }

    public BigDecimal getComisionesDesembolso() {
        return comisionesDesembolso;
    }

    public void setTotalTransferir(BigDecimal totalTransferir) {
        this.totalTransferir = totalTransferir;
    }

    public BigDecimal getTotalTransferir() {
        return totalTransferir;
    }

    public void setDisponibleTransferir(BigDecimal disponibleTransferir) {
        this.disponibleTransferir = disponibleTransferir;
    }

    public BigDecimal getDisponibleTransferir() {
        return disponibleTransferir;
    }
    public void setMontoTotalTransferencias(BigDecimal montoTotalTransferencias) {
        this.montoTotalTransferencias = montoTotalTransferencias;
    }

    public BigDecimal getMontoTotalTransferencias() {
        return montoTotalTransferencias;
    }

    public void setErrorParametros(Boolean errorParametros) {
        this.errorParametros = errorParametros;
    }

    public Boolean getErrorParametros() {
        return errorParametros;
    }
    public void setModoEjecucion(Integer modoEjecucion) {
        this.modoEjecucion = modoEjecucion;
    }

    public Integer getModoEjecucion() {
        return modoEjecucion;
    }
    
    public void setAccionesTransferenciasVisible(Boolean accionesTransferenciasVisible) {
        this.accionesTransferenciasVisible = accionesTransferenciasVisible;
    }

    public Boolean getAccionesTransferenciasVisible() {
        return accionesTransferenciasVisible;
    }
    
    public void setDeshabilitarBtnEdicionTran(Boolean deshabilitarBtnEdicionTran) {
        this.deshabilitarBtnEdicionTran = deshabilitarBtnEdicionTran;
    }

    public Boolean getDeshabilitarBtnEdicionTran() {
        return deshabilitarBtnEdicionTran;
    }

    public void setDeshabilitarBtnEliminarTran(Boolean deshabilitarBtnEliminarTran) {
        this.deshabilitarBtnEliminarTran = deshabilitarBtnEliminarTran;
    }

    public Boolean getDeshabilitarBtnEliminarTran() {
        return deshabilitarBtnEliminarTran;
    }

    public void setBtnCancelarVisible(Boolean btnCancelarVisible) {
        this.btnCancelarVisible = btnCancelarVisible;
    }

    public Boolean getBtnCancelarVisible() {
        return btnCancelarVisible;
    }
    
    public void setDeshabilitarBtnEdicionInst(Boolean deshabilitarBtnEdicionInst) {
        this.deshabilitarBtnEdicionInst = deshabilitarBtnEdicionInst;
    }

    public Boolean getDeshabilitarBtnEdicionInst() {
        return deshabilitarBtnEdicionInst;
    }

    public void setDeshabilitarBtnEliminarInst(Boolean deshabilitarBtnEliminarInst) {
        this.deshabilitarBtnEliminarInst = deshabilitarBtnEliminarInst;
    }

    public Boolean getDeshabilitarBtnEliminarInst() {
        return deshabilitarBtnEliminarInst;
    }
    
    public void setModoAplicacionTrans(Boolean modoAplicacionTrans) {
        this.modoAplicacionTrans = modoAplicacionTrans;
    }

    public Boolean getModoAplicacionTrans() {
        return modoAplicacionTrans;
    }

    public void setDeshabilitarBtnGuardar(Boolean deshabilitarBtnGuardar) {
        this.deshabilitarBtnGuardar = deshabilitarBtnGuardar;
    }

    public Boolean getDeshabilitarBtnGuardar() {
        return deshabilitarBtnGuardar;
    }   

    public void setIdTcaEstadoContrato(Integer idTcaEstadoContrato) {
        this.idTcaEstadoContrato = idTcaEstadoContrato;
    }

    public Integer getIdTcaEstadoContrato() {
        return idTcaEstadoContrato;
    }

    public Integer getEstadoContrato() {
        return estadoContrato;
    }

    public void setEstadoContrato(Integer estadoContrato) {
        this.estadoContrato = estadoContrato;
    }
    
    public void setMontoTransfrenciaSeleccionada(BigDecimal montoTransfrenciaSeleccionada) {
        this.montoTransfrenciaSeleccionada = montoTransfrenciaSeleccionada;
    }

    public BigDecimal getMontoTransfrenciaSeleccionada() {
        return montoTransfrenciaSeleccionada;
    }
    
    public void setEsNuevaTransferencia(Boolean esNuevaTransferencia) {
        this.esNuevaTransferencia = esNuevaTransferencia;
    }

    public Boolean getEsNuevaTransferencia() {
        return esNuevaTransferencia;
    }
    
    public void setBtnGuardarVisible(Boolean btnGuardarVisible) {
        this.btnGuardarVisible = btnGuardarVisible;
    }

    public Boolean getBtnGuardarVisible() {
        return btnGuardarVisible;
    }
    
    public void setContratoRegistrado(Boolean contratoRegistrado) {
        this.contratoRegistrado = contratoRegistrado;
    }

    public Boolean getContratoRegistrado() {
        return contratoRegistrado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getInstanciaDProceso() {
        return instanciaDProceso;
    }

    public void setInstanciaDProceso(String instanciaDProceso) {
        this.instanciaDProceso = instanciaDProceso;
    }
    
    public void setIdTransferenciaActal(Long idTransferenciaActal) {
        this.idTransferenciaActal = idTransferenciaActal;
    }

    public Long getIdTransferenciaActal() {
        return idTransferenciaActal;
    }
    
    public void setAccionAgregar(Boolean accionAgregar) {
        this.accionAgregar = accionAgregar;
    }

    public Boolean getAccionAgregar() {
        return accionAgregar;
    }

    public void setMontoDescuento(BigDecimal montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    public BigDecimal getMontoDescuento() {
        return montoDescuento;
    }
    
    public void setMontoDescuentoVisible(Boolean montoDescuentoVisible) {
        this.montoDescuentoVisible = montoDescuentoVisible;
    }

    public Boolean getMontoDescuentoVisible() {
        return montoDescuentoVisible;
    }

}
