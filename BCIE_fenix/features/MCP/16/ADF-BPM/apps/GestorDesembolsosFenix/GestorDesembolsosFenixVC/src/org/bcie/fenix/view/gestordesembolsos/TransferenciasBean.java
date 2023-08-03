package org.bcie.fenix.view.gestordesembolsos;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.dms.instrument.PhaseEvent;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class TransferenciasBean implements Serializable {
    @SuppressWarnings("compatibility:-5677989401997624402")
    private static final long serialVersionUID = 1L;

    public static ADFLogger logger = null;
    
    private Long idOperacion = null;
    private Long idContratoDesembolso = null;   
    private Integer modoEjecucion = null;
    private String BHQCliente = null;
    private Integer idTcaTipoMoneda = null;
    private String descripcionMonedaContrato = null;
    private BigDecimal montoDesembolsarContrato = null;
    
    private String instanciaProceso;
   
    private Boolean cuantaConFuente = Boolean.FALSE;   
    private String cuentaClienteCurrent = null;

    private String origenTransferencia = null;
    private String cuentaCliete = null;
    private String fechaDisponibilidadRecursos = null;
    private Boolean datosTrnsferenciasLectura = Boolean.FALSE;
    private Boolean cargarTransferenciasInstrucciones = Boolean.TRUE;    


    public TransferenciasBean() {
        if (logger == null){
            logger = ADFLogger.createADFLogger(VerCrearSolicitudDesembolsosActionBean.class);
        }
    }


    public void precargaInformacion(){
         logger.warning("*Inf, Inicia el metodo de precarga para el TAB TRANSFERENCIAS ");
          Map mapaDatos = new HashMap();
   // 1--->// Recuperamos los parametros de entrada del taskflow
            idContratoDesembolso = (null == JSFUtils.resolveExpression("#{pageFlowScope.idcontratoDesembolso}"))? null  
                                  : new Long(JSFUtils.resolveExpression("#{pageFlowScope.idcontratoDesembolso}").toString());
         
                     idOperacion = (null == JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}")) ? null
                                  :  new Long((JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}").toString()));
         
                   modoEjecucion = (null == JSFUtils.resolveExpression("#{pageFlowScope.modoEjecucion}") ? null
                                  : Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.modoEjecucion}").toString()));
                  
                 instanciaProceso = (null == JSFUtils.resolveExpression("#{pageFlowScope.instanciaProceso}") ? null
                                  : (JSFUtils.resolveExpression("#{pageFlowScope.instanciaProceso}").toString()));
                    
            logger.warning("*Inf, idContratoDesembolso ->"+idContratoDesembolso);
            logger.warning("*Inf, idOperacion ->"+idOperacion);
            logger.warning("*Inf, modoEjecucion ->"+modoEjecucion);
            logger.warning("*Inf, instanciaProceso ->"+instanciaProceso);
          
            if(idContratoDesembolso == null || idOperacion == null || modoEjecucion == null){  
                logger.warning("*Inf, Important! no se estan recibiendo todos los parametros solicitados para el tab de transferencias");             
               // JSFUtils.addFacesErrorMessage("Tab Transferencias, No recibio todos los parametros de entrada del Task");    
            }else{       
                
        // Desde este switch se invocan todos los modos de ejecucion del tab de transferencias         
                switch(modoEjecucion){
                  case 1:
                    logger.warning("MODO DE EJECUCION LECTURA DE TRANSFERENCIAS");  
                    precargaModoLecturaTransferencias();                    
                  break;
                  case 2:
                    logger.warning("MODO DE EJECUCION EDICION DE TRANSFERENCIAS"); 
                      precargaModoEdicionTransferencias();                    
                  break;
                  case 3:
                    logger.warning("MODO DE APLICACION DE TRANSFERENCIAS");  
                    precargaModoAplicacionTransferencias();                    
                  break;
                }
                
                logger.warning("*Inf, cuantaConFuente: "+cuantaConFuente);
            }
            logger.warning("*Inf, termina el metodo de precarga para el TAB TRANSFERENCIAS ");
        }


    public void precargaModoLecturaTransferencias() {
        logger.warning("*Inicia metodo precargaModoLecturaTransferencias. ..");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio =
            System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        obtenerDatosContrato();
        datosTrnsferenciasLectura = Boolean.TRUE;

        if (origenTransferencia != null) {
            
             setFechaByDesembolso();
            
            if (origenTransferencia.equals("DIRECTO_CLIENTE")) {
                logger.warning("Se invoco el tab de transferencias en modo lectura origen de transferencia DIRECTO_CLIENTE");
                cargarTransferenciasInstrucciones = Boolean.FALSE;      
                cuantaConFuente = Boolean.TRUE;
            }
            if (origenTransferencia.equals("CUENTAS_BCIE")) {
                logger.warning("Se invoco el tab de transferencias en modo lectura origen de transferencia CUENTAS_BCIE");               
            }
            if (origenTransferencia.equals("SIN_SALIDA_FONDOS")) {
                logger.warning("Se invoco el tab de transferencias en modo lectura origen de transferencia SIN_SALIDA_FONDOS");               
            }
            if (origenTransferencia.equals("")) {
                logger.warning("*** Error, se esta invocando transferencias en modo lectura con un origen de transferencias null en BD");
                cargarTransferenciasInstrucciones = Boolean.FALSE;            
            }
            
            
            if(cuentaCliete == null || cuentaCliete.equals("")){
                logger.warning("importante, cueta cliente resuelta a null, se intentara recuperar nuevamente ");
                
                 if(BHQCliente == null || BHQCliente.equals("")){
                    logger.warning("** Error BHQ resuelto a null recuperando para filtrar cuentas cliente");
                    ObtenerBHQCliente(idOperacion);
                 }
                 
                if (descripcionMonedaContrato == null || descripcionMonedaContrato.equals("")) {
                   logger.warning("** Error descripcion de la moneda asociada al desembolso resuelto a null");
                    obtenerDescripcionMoneda();
                }
                                                  
                logger.warning(" BHQCliente recuperado->" + BHQCliente + " moneda asociada al contrato->" +  descripcionMonedaContrato);
                
                if(BHQCliente != null && !BHQCliente.equals("") && descripcionMonedaContrato != null && !descripcionMonedaContrato.equals("")){
                    logger.warning(" Recuperado cuantas cliente ....");
                    setAtributosTransientContrato(BHQCliente, descripcionMonedaContrato);
                    buscarCuentasCliente(BHQCliente, descripcionMonedaContrato);
                }else{
                    logger.warning("Parametros requeridos resueltos a null no se recuperaran cuentas cliente");
                }
                
                 
            }
            
                

            


            
            
        } else {
            logger.warning("*** Error, el origen de la transferencia es resuelto a null");
        }
               

        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning("*Inf, Termina metodo precargaModoLecturaTransferencias con una duracion de: " + tiempo + " segundos");
    }



    public void precargaModoEdicionTransferencias() {
        logger.warning("Inside precargaModoEdicionTransferencias.");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        obtenerDatosContrato();
        
        // 1 Ejecutamos metodo en ContratoDesembolsoVO para obtener el BHQ del cliente
        ObtenerBHQCliente(idOperacion);
        
        if (BHQCliente == null) {
            logger.warning("** Error no se pudo recuperar el BHQ del cliente");
        } else {
            //2  Ejecutamos metodo en ContratoDesembolsoVOImpl para la descripcion de la moneda asociada al contrato de desembolso
            obtenerDescripcionMoneda();
            
            if (descripcionMonedaContrato == null) {
                logger.warning("** Error no se pudo recuperar la descripcion de la moneda asociada al contrato de desembolso");
                //JSFUtils.addFacesErrorMessage("Tab transferencias , Error no se pudo recuperar la moneda asociada al contrato de desembolso");
                try{
                    BindingContainer binding = ADFUtils.getBindingContainer();
                    OperationBinding operBinding = binding.getOperationBinding("obtenerDescripcionMoneda");
                    descripcionMonedaContrato = (String) operBinding.execute();
                }catch(Exception e){
                    logger.warning("ERROR al ejecutar OperationBinding obtenerDescripcionMoneda.", e);
                }
                setAtributosTransientContrato(BHQCliente, descripcionMonedaContrato);
            } else {
                logger.warning(" BHQCliente recuperado->" + BHQCliente + " moneda asociada al contrato->" + descripcionMonedaContrato);
                logger.warning(" Recuperado cuantas cliente ....");
                //3  Ejecutamos metodo en ContratoDesembolsoImpl para setear valores a atributtos transient
                setAtributosTransientContrato(BHQCliente, descripcionMonedaContrato);
            }
        }
        
        //4  Ejecutamos metodo en TreLineapasivaVO para verificar si el contrato cuenta con fuente
        verificarFuenteContrato(idContratoDesembolso);
        
        //5  Ejecutamos metodo en VcaCuentasCliente para filtrar las cuentas cliente
        buscarCuentasCliente(BHQCliente, descripcionMonedaContrato);
        
        //7 cargamos los componentes deacuerdo al origen de transferencia
        cargaDeComponentes();
        
               
        setFechaByDesembolso();
                    
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
        logger.warning("*Inf, Termina metodo precargaModoEdicionTransferencias con una duracion de: "+tiempo+" segundos");
    }


    public void precargaModoAplicacionTransferencias() {
        logger.warning("Inside precargaModoAplicacionTransferencias.");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        obtenerDatosContrato();
        
        if (origenTransferencia != null) {
            setFechaByDesembolso();
            //1 Ejecutamos metodo en ContratoDesembolsoVO para obtener el BHQ del cliente
            ObtenerBHQCliente(idOperacion);
            
            if (origenTransferencia.equals("DIRECTO_CLIENTE")) {
                logger.warning("Se invoco el tab de transferencias en modo Aplicacion origen de transferencia DIRECTO_CLIENTE");
                cargarTransferenciasInstrucciones = Boolean.FALSE;
                datosTrnsferenciasLectura = Boolean.FALSE;
                cuantaConFuente = Boolean.TRUE;
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("origenTransferencia", "DIRECTO_CLIENTE");  
            }
            
            if (origenTransferencia.equals("CUENTAS_BCIE")) {
                logger.warning("Se invoco el tab de transferencias en modo Aplicacion origen de transferencia CUENTAS_BCIE");
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("origenTransferencia", "CUENTAS_BCIE");  
                datosTrnsferenciasLectura = Boolean.TRUE;                
                
                if (BHQCliente == null) {
                    JSFUtils.addFacesErrorMessage("Error, no se pudo recuperar el BHQ del cliente");
                    logger.warning("** Error no se pudo recuperar el BHQ del cliente");
                } else {
                    //2  Ejecutamos metodo en ContratoDesembolsoVOImpl para la descripcion de la moneda asociada al contrato de desembolso
                    obtenerDescripcionMoneda();
                }
            }
            
            if (origenTransferencia.equals("SIN_SALIDA_FONDOS")) {
                logger.warning("Se invoco el tab de transferencias en modo Aplicacion origen de transferencia SIN_SALIDA_FONDOS");
                cargarTransferenciasInstrucciones = Boolean.FALSE;
                datosTrnsferenciasLectura = Boolean.FALSE;
                cuantaConFuente = Boolean.FALSE;
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("origenTransferencia", "SIN_SALIDA_FONDOS");  
            }

            if (origenTransferencia.equals("")) {
                logger.warning("*** Error, se esta invocando transferencias en modo Aplicacion con un origen de transferencias null en BD");
                cargarTransferenciasInstrucciones = Boolean.FALSE;
                datosTrnsferenciasLectura = Boolean.TRUE;
            }
            
            if (BHQCliente == null) {
                logger.warning("** Error no se pudo recuperar el BHQ del cliente");
            } else {
                //  Ejecutamos metodo en ContratoDesembolsoVOImpl para la descripcion de la moneda asociada al contrato de desembolso
                obtenerDescripcionMoneda();
                
                if (descripcionMonedaContrato == null) {
                    logger.warning("** Error no se pudo recuperar la descripcion de la moneda asociada al contrato de desembolso");                  
                } else {
                    logger.warning(" BHQCliente recuperado->" + BHQCliente + " moneda asociada al contrato->" + descripcionMonedaContrato);
                    logger.warning(" Recuperado cuantas cliente ....");
                //  Ejecutamos metodo en ContratoDesembolsoImpl para setear valores a atributtos transient
                    setAtributosTransientContrato(BHQCliente, descripcionMonedaContrato);
                }
            }
            
            buscarCuentasCliente(BHQCliente, descripcionMonedaContrato);
            
        } else {
            logger.warning("*** Error, se esta invocando transferencias en modo Aplicacion con un origen de transferencias null en BD");
        }
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
        logger.warning("*Inf, Termina metodo precargaModoAplicacionTransferencias con una duracion de: "+tiempo+" segundos");
    }


    public void cargaDeComponentes() {
        logger.warning("*Inf, inicia el metodo cargaDeComponentes");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        if (origenTransferencia == null) {
            logger.warning("*Inf, Importante!, tab en modo edicion, no cuenta con un origen de transferencias ");            

            if (cuantaConFuente) {
                logger.warning("*Inf, ok el contrato cuenta con fuentes externas");
                origenTransferencia = "DIRECTO_CLIENTE";
                logger.warning("*Inf, establecido origenTransferencia como: " + origenTransferencia);
                cargarTransferenciasInstrucciones = Boolean.FALSE;               
              
                if(cuentaCliete == null){
                    logger.warning("*Inf, cuenta cliente resuelto a null se recuperara de vca_cuanta_cliente");
                    obtenerCuentaCurrent();
                    setAContratoDesembolsoCurrent(origenTransferencia, cuentaClienteCurrent); 
                }else{
                    // Ejecutamos metodo en VcaCuentasCliente para obtener cuenta Cuerrent
                    setAContratoDesembolsoCurrent(origenTransferencia, cuentaCliete); 
                }

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("origenTransferencia", "DIRECTO_CLIENTE");  
                
            } else {
                logger.warning("*Inf, el contrato no cuenta con fuentes externas");
                origenTransferencia = "CUENTAS_BCIE";
                logger.warning("*Inf, establecido origenTransferencia como: " + origenTransferencia);
                
                if(cuentaCliete == null){
                    logger.warning("*Inf, cuenta cliente resuelto a null se recuperara de vca_cuanta_cliente");
                    obtenerCuentaCurrent();
                    setAContratoDesembolsoCurrent(origenTransferencia, cuentaClienteCurrent); 
                }else{
                    // Ejecutamos metodo en VcaCuentasCliente para obtener cuenta Cuerrent 
                    setAContratoDesembolsoCurrent(origenTransferencia, cuentaCliete); 
                }
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("origenTransferencia", "CUENTAS_BCIE");  
                
            }
            
        } else {           
            if (origenTransferencia.equals("DIRECTO_CLIENTE")) {
                logger.warning("*Inf, origen de transferencia DIRECTO_CLIENTE");
                cargarTransferenciasInstrucciones = Boolean.FALSE;
                datosTrnsferenciasLectura = Boolean.TRUE;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("origenTransferencia", "DIRECTO_CLIENTE");  
            }
            if (origenTransferencia.equals("CUENTAS_BCIE")) {
                logger.warning("*Inf, origen de transferencia CUENTAS_BCIE");
                datosTrnsferenciasLectura = Boolean.TRUE;
                cargarTransferenciasInstrucciones = Boolean.TRUE;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("origenTransferencia", "CUENTAS_BCIE");  
            }
            if (origenTransferencia.equals("SIN_SALIDA_FONDOS")) {
                logger.warning("*Inf, origen de transferencia SIN_SALIDA_FONDOS");
                datosTrnsferenciasLectura = Boolean.FALSE;
                cargarTransferenciasInstrucciones = Boolean.FALSE;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("origenTransferencia", "SIN_SALIDA_FONDOS");  
            }
            
            /* if(cuentaCliete == null){
                logger.warning("*Inf, cuenta cliente resuelto a null se recuperara de vca_cuanta_cliente");
                obtenerCuentaCurrent();
                setAContratoDesembolsoCurrent(origenTransferencia, cuentaClienteCurrent); 
            }else{
                // Ejecutamos metodo en VcaCuentasCliente para obtener cuenta Cuerrent 
                setAContratoDesembolsoCurrent(origenTransferencia, cuentaCliete); 
            } */
        }
        datosTrnsferenciasLectura = Boolean.FALSE;
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia
        logger.warning("*Inf, termina el metodo cargaDeComponentes con una duracion de: "+tiempo+" segundos");
    }
   
    public void obtenerDatosContrato() {
        logger.warning("Inf, inicia metodo obtenerDatosContrato");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("getContratoSeleccionado");
            Row fila = (Row) operBinding.execute();

            if (fila != null) {

                origenTransferencia =
                    (null == fila.getAttribute("OrigenTranferenciaCliente")) ? null :
                    (String) fila.getAttribute("OrigenTranferenciaCliente");

                cuentaCliete =
                    (null == fila.getAttribute("CuentaCliente")) ? null : (String) fila.getAttribute("CuentaCliente");

                fechaDisponibilidadRecursos =  (null == fila.getAttribute("FechaEstimadaDisponibilidadRecursos")) 
                                            ? null :  fila.getAttribute("FechaEstimadaDisponibilidadRecursos").toString();

                logger.warning("origenTransferencia recuperado del contrato->" + origenTransferencia);
                logger.warning("cuentaCliete recuperado del contrato->" + cuentaCliete);
                logger.warning("fechaDisponibilidadRecursos recuperado del contrato->" + fechaDisponibilidadRecursos);

            } else {
                logger.warning("*** Error, No se pudo recuperar un current en contrato desembolso>");
            }

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning(" :( OperationBinding devuelve errores");
                JSFUtils.addFacesErrorMessage("Error al ejecutar operationBinding consultar datos del contrato desembolso ");
            }

        } catch (Exception e) {
            logger.warning("*** Tab transferencias, Error al consultar los datos del contrato de desembolso " +
                           e.getClass() + " ->" + e.getMessage());
            // JSFUtils.addFacesErrorMessage("Tab transferencias , Error no se pudo recuperar las cuentas del cliente");
        }
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
        logger.warning("*Inf, Termina metodo obtenerDatosContrato con una duracion de: "+tiempo+" segundos");
    }

    
    public void recuperarIdContrato(){
     
    Long idContratoDesembolso = null;
    Date fechaEstimadaDesembolso = null;         
    Row rowContrato = null;
    try{
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("getContratoSeleccionado");
         rowContrato = (Row)operationBinding.execute();               
            if(!operationBinding.getErrors().isEmpty()){
                logger.warning(" :( OperationBinding devuelve errores");
                JSFUtils.addFacesErrorMessage("Error, operationBinding devuelve errores");  
            }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en consultarContratoDesembolso " + e.getClass() + ":" + e.getMessage());
        }
                if(rowContrato == null){                            
                    logger.warning(" :( Error,  El row que regresa el metodo getContratoSeleccionado es nulo");
                    JSFUtils.addFacesErrorMessage("Error, el row de datos de contrato desembolso es nulo");                        
                }else{ 
                       idContratoDesembolso = new Long(rowContrato.getAttribute("Id").toString() );  
                       fechaEstimadaDesembolso = new Date(rowContrato.getAttribute("FechaEstimadaDesembolsar").toString());
                    logger.warning("**Datos recuperados del contrato-> idContrato: "+idContratoDesembolso+" fechaEstimadaDesembolso: "+fechaEstimadaDesembolso);
                }                                            
    
    }

    //metodo para obtener el BHQ del cliente

    public void ObtenerBHQCliente(Long idOperacion) {
        logger.warning("Inicia metodo ObtenerBHQCliente.");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        try{
            BHQCliente = (String) ADFUtils.getBoundAttributeValue("Flexcube");
            if(null == BHQCliente){
                //consultar Flexcube en session
                logger.warning("BHQCliente es nulo en DetalleDesembolsosOperacionVO");
                logger.warning("Consultar BHQCliente en session");
                try{
                    DatosSesionManagedBean datosSesionBean =
                                    (DatosSesionManagedBean) JSFUtils.resolveExpression("#{DatosSesionManagedBean}");
                    
                    BHQCliente = datosSesionBean.getFlexcube();
                }catch(Exception e){
                    logger.severe("Error recuperandoBHQCliente en  DatosSesionManagedBean.", e);
                }
            }
        }catch(Exception e){
            logger.warning("ERROR al recuperar el atributo BHQCliente del CrearActualizarContratoDesembolsoVO.", e);
            JSFUtils.addFacesErrorMessage("ERROR al recuperar el atributo BHQCliente del CrearActualizarContratoDesembolsoVO: " + e.getMessage());
        }
        
        /* try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("obtenerCodigoBHQClientePorIdOperacion");
            operBinding.getParamsMap().put("idOperacion", idOperacion);
            BHQCliente = (String) operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning(" :( OperationBinding devuelve errores");
                JSFUtils.addFacesErrorMessage("Error, operationBinding devuelve errores: " + operBinding.getErrors().toString());
            }

        } catch (Exception e) {
            logger.warning("Error al consultar el BHQ del cliente " + e.getClass() + " ->" + e.getMessage());
        } */
        
        logger.warning("BHQ flexcube de cliente: " + BHQCliente);
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
        logger.warning("*Inf, Termina metodo ObtenerBHQCliente con una duracion de: "+tiempo+" segundos");
    }

    // metodo para obtener la descripcion de la moneda asociada al contrato de desembolso

    public void obtenerDescripcionMoneda() {
        logger.warning("Inicia metodo obtenerDescripcionMoneda.");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        Map mapaDatos = new HashMap();
        logger.warning("** BHQCliente recuperado->" + BHQCliente + " buscando moneda asociada al contrato");
        try {
            
            idTcaTipoMoneda = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda");
        } catch (Exception e) {
            logger.warning("Tab transferencias, Error al recuperar el BoundAttribute IdTcaTipoMoneda" + e.getClass() + " ->" +
                           e.getMessage());
        }
        
        try {
            
            descripcionMonedaContrato = (String) ADFUtils.getBoundAttributeValue("DescripcionMoneda");
        } catch (Exception e) {
            logger.warning("Tab transferencias, Error al recuperar el BoundAttribute DescripcionMoneda" + e.getClass() + " ->" +
                           e.getMessage());
        }
        logger.warning("IDTCATIPOMONEDA: " + idTcaTipoMoneda + ", DESCRIPCIONMONEDA: " + descripcionMonedaContrato);
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
        logger.warning("*Inf, Termina metodo obtenerDescripcionMoneda con una duracion de: "+tiempo+" segundos");
    }
       
    //metodo para accesor en contrato  requiere cliente by BQH && descripcion moneda
    public void setAtributosTransientContrato(String BHQCliente, String descripcionMonedaContrato) {
        logger.warning("Inicia metodo setAtributosTransientContrato.");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("setBhqDescripcionMoneda");
            operBinding.getParamsMap().put("bhqCliente", BHQCliente);
            operBinding.getParamsMap().put("descMoneda", descripcionMonedaContrato);
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning(" :( OperationBinding devuelve errores");
                JSFUtils.addFacesErrorMessage("Error, operationBinding buscar cuentas devuelve errores");
            }

        } catch (Exception e) {
            logger.warning("Tab transferencias, Error al consultar cuentas cliente " + e.getClass() + " ->" +
                           e.getMessage());
            // JSFUtils.addFacesErrorMessage("Tab transferencias , Error no se pudo recuperar las cuentas del cliente");
        }
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
        logger.warning("*Inf, Termina metodo setAtributosTransientContrato con una duracion de: "+tiempo+" segundos");
    }

    //metodo para filtrar las cuantas asociadas al BHQ del cliente en la vista VcaCuentasCiente

    public void buscarCuentasCliente(String BHQCliente, String descripcionMonedaContrato) {
        logger.warning("Inicia metodo buscarCuentasCliente");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("ejecutarBusquedaCuentas");
            operationBinding.getParamsMap().put("pCodigoCliente", BHQCliente);
            operationBinding.getParamsMap().put("pMoneda", descripcionMonedaContrato);
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding ejecutarBusquedaCuentas devuelve errores");
                JSFUtils.addFacesErrorMessage("Error, " + operationBinding.getErrors());
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en consultarContratoDesembolso ", e);
        }
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia
        logger.warning("Termina metodo buscarCuentasCliente con una duracion de: "+tiempo+" segundos");
    } 
    

    public void setFechaByDesembolso() {
        logger.warning("Inicia metodo setFechaByDesembolso");       
        
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("setFechaByDesembolso");
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding setFechaByDesembolso devuelve errores");
                JSFUtils.addFacesErrorMessage("Error, " + operationBinding.getErrors());
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en setFechaByDesembolso ->", e);
        }
        
        logger.warning("Termina metodo setFechaByDesembolso");
    }    
    
    
    
        
    // metodo obtenerCuentaCliente en VcaCuentasCliente
    
    public String obtenerCuentaCurrent() {
        logger.warning("Inicia metodo obtenerCuentaCurrent");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        String respueesta = null;
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("obtenerFilaActual");
            Row fila = (Row) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning(" :( OperationBinding devuelve errores");
                JSFUtils.addFacesErrorMessage("Error, operationBinding, consulta cuenteCliete current en VcaCuentasCliente");
            }
            if (fila != null) {
                cuentaClienteCurrent = (String) fila.getAttribute("Cuenta");
            } else {
                logger.warning("*** No se recupero una cuenta Current en VcaCuentasCliente");
            }

        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en consultarContratoDesembolso " + e.getClass() + ":" + e.getMessage());
        }
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia
        logger.warning("Termina metodo obtenerCuentaCurrent con una duracion de: "+tiempo+" segundos");
        return respueesta;
    }
    
    // En este metodo seteamos valores a los atributtos que necesitamos esten llenos

    public void setAContratoDesembolsoCurrent(String origenTransferencia, String cuentaCliete) {
        logger.warning("Inicia metodo setAContratoDesembolsoCurrent.");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("setAtributosACurrent");
            operationBinding.getParamsMap().put("OrigenTranferenciaCliente", origenTransferencia);
            operationBinding.getParamsMap().put("CuentaCliente", cuentaCliete);
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning(" :( OperationBinding devuelve errores");
                JSFUtils.addFacesErrorMessage("Error, operationBinding set de atributos a current Contrato devuelve errores");
            }

        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en consultarContratoDesembolso " + e.getClass() + ":" + e.getMessage());
        }
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia
        logger.warning("Termina metodo verificarFuenteContrato con una duracion de: "+tiempo+" segundos");
    }
    
    //metodo para saber si el contrato cuenta con una fuente externa
    
    public Boolean verificarFuenteContrato(Long idContrato) {
        logger.warning("Inicia metodo verificarFuenteContrato idContrato->" + idContrato);
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio =
            System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        Boolean respueesta = Boolean.FALSE;        

        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("validarContratoTieneFuentesExternas");
            operationBinding.getParamsMap().put("idDesembolso", idContrato);
            operationBinding.execute();
                        
            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning(" :( OperationBinding devuelve errores");
                JSFUtils.addFacesErrorMessage("Error, operationBinding consulta fuente en transferencias devuelve errores");
            }else{
              
                respueesta = (Boolean)operationBinding.getResult();  
                cuantaConFuente = respueesta;                            
                
            }
          
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en consultarContratoDesembolso " + e.getClass() + ":" + e.getMessage());
        }

        TFin =
            System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning("Termina metodo verificarFuenteContrato con una duracion de: " + tiempo + " segundos");
        return respueesta;
    }


    /********      Accesors       *****/
    
    public void setIdContratoDesembolso(Long idContratoDesembolso) {
        this.idContratoDesembolso = idContratoDesembolso;
    }
    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }

    public void setModoEjecucion(Integer modoEjecucion) {
        this.modoEjecucion = modoEjecucion;
    }

    public Integer getModoEjecucion() {
        return modoEjecucion;
    }

    public void setBHQCliente(String BHQCliente) {
        this.BHQCliente = BHQCliente;
    }

    public String getBHQCliente() {
        return BHQCliente;
    }

    public void setDescripcionMonedaContrato(String descripcionMonedaContrato) {
        this.descripcionMonedaContrato = descripcionMonedaContrato;
    }

    public String getDescripcionMonedaContrato() {
        return descripcionMonedaContrato;
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
    public void setMontoDesembolsarContrato(BigDecimal montoDesembolsarContrato) {
        this.montoDesembolsarContrato = montoDesembolsarContrato;
    }

    public BigDecimal getMontoDesembolsarContrato() {
        return montoDesembolsarContrato;
    }
    
    public void setCuantaConFuente(Boolean cuantaConFuente) {
        this.cuantaConFuente = cuantaConFuente;
    }

    public Boolean getCuantaConFuente() {
        return cuantaConFuente;
    }
    
    public void setCuentaClienteCurrent(String cuentaClienteCurrent) {
        this.cuentaClienteCurrent = cuentaClienteCurrent;
    }

    public String getCuentaClienteCurrent() {
        return cuentaClienteCurrent;
    }

    public void setOrigenTransferencia(String origenTransferencia) {
        this.origenTransferencia = origenTransferencia;
    }

    public String getOrigenTransferencia() {
        return origenTransferencia;
    }

    public void setCuentaCliete(String cuentaCliete) {
        this.cuentaCliete = cuentaCliete;
    }

    public String getCuentaCliete() {
        return cuentaCliete;
    }

    public void setFechaDisponibilidadRecursos(String fechaDisponibilidadRecursos) {
        this.fechaDisponibilidadRecursos = fechaDisponibilidadRecursos;
    }

    public String getFechaDisponibilidadRecursos() {
        return fechaDisponibilidadRecursos;
    }
    
    public void setCargarTransferenciasInstrucciones(Boolean cargarTransferenciasInstrucciones) {
        this.cargarTransferenciasInstrucciones = cargarTransferenciasInstrucciones;
    }

    public Boolean getCargarTransferenciasInstrucciones() {
        return cargarTransferenciasInstrucciones;
    }
    
    public void setDatosTrnsferenciasLectura(Boolean datosTrnsferenciasLectura) {
        this.datosTrnsferenciasLectura = datosTrnsferenciasLectura;
    }

    public Boolean getDatosTrnsferenciasLectura() {
        return datosTrnsferenciasLectura;
    }

    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }
}
