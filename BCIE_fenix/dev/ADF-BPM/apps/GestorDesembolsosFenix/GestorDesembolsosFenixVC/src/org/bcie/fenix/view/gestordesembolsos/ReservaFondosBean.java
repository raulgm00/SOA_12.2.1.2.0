package org.bcie.fenix.view.gestordesembolsos;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class ReservaFondosBean implements Serializable {
    @SuppressWarnings("compatibility:-2129723468478816894")
    private static final long serialVersionUID = 1L;

    public static ADFLogger logger = null;
        
    private Long idContrato = null;

    private Integer idTcaMoneda = null;       
    private Date fechaDisponibilidadFondos = null;    
    private Boolean tienFechaDisFondos = Boolean.FALSE;
    private Long idTransferencia = null;   
    private String codigoBanco = null;
    private String nombreBanco = null;
    private String codigoCuenta = null;
    private String nombreCuenta = null;
    
    private Timestamp fechaSolicitud;

    private Integer idTcaTipoMoneda = null;
    private String descripcionMonedaContrato = null;
       
    private Boolean tieneDatosCuenta = Boolean.FALSE;    
    private Boolean componenteVisible = Boolean.TRUE; 
    private Boolean componenteLectura = Boolean.TRUE;   
    private Boolean aplicarTransferenciasRestantes = Boolean.FALSE;

    private Long idSolicitud =(JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitud}") == null )? null
                                  :  Long.parseLong(JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitud}").toString());
    
    private Integer modoEjecucionVar = (JSFUtils.resolveExpression("#{pageFlowScope.modoEjecucion}") == null )? null
                                  :  Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.modoEjecucion}").toString());

    private Integer idTareaBPM = (JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}") == null )? null
                                  :  Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}").toString());
  

    private String usuario = JSFUtils.resolveExpression("#{securityContext.userName}").toString();
   
   
    
    public ReservaFondosBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }


    /**
     * Este metodo es el que carga la informacion nesesaria para iniciar el tab
     * -define el comportamiento del tab de acuerdo al modo de ejecucion
     * -carga los datos del contrato current
     * -carga la tabla de transferenciasBancariasTablaVO
     * -carga el objeto de transferenciasBancariasVO
     * @since 08/11/2016
     */

    public void precarga() {
        logger.warning("INICIAN metodos de precarga en TAB RESERVA DE FONDOS");
        modoEjecucion();
        recuperarDatosContrato();
        obtenerDescripcionMoneda();
        cargarTablaTransferencias(idContrato);
        recuperarTransferenciaParaReservarFondos(idTransferencia, descripcionMonedaContrato);
        obtenerFechaSolicitud();
        //recuperarDatosCurrentCueta();
        //setDatosCuenta(idTransferencia, codigoBanco, nombreBanco, codigoCuenta, nombreCuenta);
        inicializarCatBancosByMonedaVO();    
        setValueClaveNombreBanco();
        
        logger.warning("TERMINAN metodos de precarga en TAB RESERVA DE FONDOS");
    }
    
    /**
     * metodo para inicializar la lista auxiliar para el suggest del Banco a debitar
     * */
    public void inicializarCatBancosByMonedaVO(){
        logger.warning("Into inicializarCatBancosByMonedaVO");
        logger.warning("moneda :"+descripcionMonedaContrato);
        try{
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("cargarCatalogoBancosByMoneda");
            operBinding.getParamsMap().put("Moneda", descripcionMonedaContrato);
            operBinding.execute();              
                                      
         if(!operBinding.getErrors().isEmpty()){
             logger.warning("*OperationBinding recuperarDatosCurrentCueta devuelve errores");
         }     
        }catch(Exception e){
            logger.severe("Error in inicializarCatBancosByMonedaVO:"+e);
        }
        logger.warning("Leave inicializarCatBancosByMonedaVO");
    }

    public void modoEjecucion(){
        logger.warning("Inicia metodo modoEjecucion.");
         double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
         TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio            
        if(modoEjecucionVar == FenixConstants.MODO_EJECUCION_LECTURA){
              logger.warning("SE INVOCO EL TAB EN MODO DE EJECUCION LECTURA"); 
                componenteVisible = Boolean.FALSE;
                componenteLectura =  Boolean.TRUE;
                tieneDatosCuenta = Boolean.TRUE;
          }
        if(modoEjecucionVar == FenixConstants.MODO_EJECUCION_RESERVA_DE_FONDOS){
              logger.warning("SE INVOCO EL TAB EN MODO DE EJECUCION RESERVA DE FONDOS");
                  componenteVisible = Boolean.TRUE;
                  componenteLectura =  Boolean.FALSE;
                  tieneDatosCuenta = Boolean.FALSE;
          }
        
         TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
         tiempo = (TFin - TInicio)/ 1000; //Calculamos los milisegundos de diferencia
         logger.warning("Termina metodo modoEjecucion con una duracion de: "+tiempo+" segundos");
     }
    
    
    
    public void obtenerDescripcionMoneda() {
        logger.warning("Inicia metodo obtenerDescripcionMoneda");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        Map mapaDatos = new HashMap();
        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("obtenerIdTcaTipoYDescripcionMoneda");
            mapaDatos = (Map) operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding obtenerIdTcaTipoYDescripcionMoneda devuelve errores");
                // JSFUtils.addFacesErrorMessage("Error, operationBinding devuelve errores");
            }
            logger.warning(" OperationBinding result ->" + operBinding.getResult());

            if (mapaDatos != null && mapaDatos.size() > 0) {

                if (null != mapaDatos.get("idTipoMoneda"))
                    idTcaTipoMoneda = Integer.parseInt(mapaDatos.get("idTipoMoneda").toString());

                if (null != mapaDatos.get("descripcionMoneda"))
                    descripcionMonedaContrato = mapaDatos.get("descripcionMoneda").toString();

            }

        } catch (Exception e) {
            logger.warning("Tab transferencias, Error al consultar la moneda  BHQ del cliente " + e.getClass() + " ->" +
                           e.getMessage());
        }

        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning("Termina metodo obtenerDescripcionMoneda con una duracion de: " + tiempo + " segundos");
    }
    
  
    /**
     * Este metodo recupera el row activo en crearActualizarCOntratoDesembolsoVO
     *
     * @since 08/11/2016
     */

    public void recuperarDatosContrato() {
        logger.warning("inicia metodo recuperarDatosContrato");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        Row fila = null;
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("getContratoSeleccionado");
            fila = (Row) operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning(" :( OperationBinding getContratoSeleccionado devuelve errores");
            }

            if (fila != null) {

                idContrato = (fila.getAttribute("Id") == null) ? null : new Long(fila.getAttribute("Id").toString());
                idTcaMoneda =
                    (fila.getAttribute("IdTcaTipoMoneda") == null) ? null :
                    Integer.parseInt(fila.getAttribute("IdTcaTipoMoneda").toString());

                if (modoEjecucionVar.compareTo(FenixConstants.MODO_EJECUCION_LECTURA) == 0) {
                    tienFechaDisFondos = Boolean.TRUE;
                } else {
                    if(null != idTareaBPM){
                        if(idTareaBPM.compareTo(FenixConstants.CGD_RESERVAR_FONDOS)==0){
                            tienFechaDisFondos = Boolean.FALSE;
                        }else{
                            tienFechaDisFondos =
                                (fila.getAttribute("FechaDisponibilidadFondos") == null) ? Boolean.FALSE : Boolean.TRUE;
                        }
                    }else{
                        tienFechaDisFondos =
                            (fila.getAttribute("FechaDisponibilidadFondos") == null) ? Boolean.FALSE : Boolean.TRUE;
                    }
                }

                logger.warning("Datos del contrato recuperados idContrato:" + idContrato + " idTcaMoneda:" +
                               idTcaMoneda);

            } else {
                logger.warning("*** Error no hay un contrato current");
            }

        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                       "***Error al cargar la tabla de transferencias " + e.getClass() + ":" + e.getMessage());
        }

        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning("termina metodo recuperarDatosContrato con una duracion de: " + tiempo + " segundos");
    }
    
    
    /**
     * Este metodo es el que inicia la tabla de transferenciasBancariasTablaVO mediante un idContratoDesembolso
     * @param IdContrato
     * @since 08/11/2016
     */

    public void cargarTablaTransferencias(Long idContrato) {
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
                logger.warning("OperationBinding iniciarTablaTansacciones devuelve errores no se recupero idTransferencia");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "***Error al cargar la tabla de transferencias " + e);
        }

        if (fila != null) {
            idTransferencia = (Long) fila.getAttribute("IdTransferencia");
        } else {
            logger.warning("La tabla de transferencias esta vacia no se recupero ninguna fila");
        }
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning(" Termina el metodo para cargar la tabla de transferencias con una duracion de: " + tiempo +
                       " segundos");
    }
    
    /**
     * En caso de que la tabla de transferencias ya cuente con registros este metodo prepara el row
     * con el primer id de la transferencia encontrado al iniciar la tabla 
     * 
     * @param IdContrato, descripcionMoneda
     * @since 08/11/2016   
     */
    
    public void recuperarTransferenciaParaReservarFondos(Long idTransferencia, String descripcionMoneda){
            logger.warning(" inicia el metodo recuperarTransferenciaParaReservarFondos idTransferencia->"+idTransferencia);
            double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
            TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

            try{
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("buscarTransferenciaParaReservarFondos");
                    operationBinding.getParamsMap().put("idTransferencia", idTransferencia);
                    operationBinding.getParamsMap().put("descripcionMoneda", descripcionMoneda);
                     tieneDatosCuenta = (Boolean)operationBinding.execute();   
            
                     if(!operationBinding.getErrors().isEmpty()){
                        logger.warning(" :( OperationBinding devuelve errores");                      
                       }
            
            
              if(modoEjecucionVar == FenixConstants.MODO_EJECUCION_LECTURA){
                    tieneDatosCuenta = Boolean.TRUE;
                }
                if(modoEjecucionVar == FenixConstants.MODO_EJECUCION_RESERVA_DE_FONDOS){
                      logger.warning("SE INVOCO EL TAB EN MODO DE EJECUCION RESERVA DE FONDOS");
                      tieneDatosCuenta = Boolean.FALSE;
                      }
                
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "***Error en buscarTransferenciaParaReservarFondos " + e.getClass() + ":" + e.getMessage());
            } 
            
            TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
            tiempo = (TFin - TInicio)/ 1000; //Calculamos los milisegundos de diferencia
            logger.warning(" Termina el metodo recuperarTransferenciaParaReservarFondos con una duracion de: "+tiempo+" segundos");
        }
    
    /**
     * Este metodo se utiliza para cargar y recuperar los datos current de banco y cuenta 
     * para setearlos en el metodo setDatosCuenta a las campos de transferencia y no aparescan en blanco 
     *      
     * @since 08/11/2016   
     */
    
    public void recuperarDatosCurrentCueta(){
            logger.warning("---recuperando current de CatBancoByMoneda cat modelo...");          
           
            Row filaBanco = null; 
            Row filaCuenta = null;
                            
              try{
                    BindingContainer binding = ADFUtils.getBindingContainer();
                    OperationBinding operBinding = binding.getOperationBinding("cargarCatalogoBancosByMoneda");
                    operBinding.getParamsMap().put("Moneda", descripcionMonedaContrato);
                    filaBanco = (Row)operBinding.execute();              
                                                  
                     if(!operBinding.getErrors().isEmpty()){
                         logger.warning("*OperationBinding recuperarDatosCurrentCueta devuelve errores");
                     }             
                }catch(Exception e){             
                     logger.warning("Error al ejecutar el operationBinding recuperarDatosCurrentCueta : ", e);
                 }  
            
                    if(filaBanco != null){             
                          codigoBanco = (String)filaBanco.getAttribute("Cliente");
                          nombreBanco = (String)filaBanco.getAttribute("Banco");  
                          logger.warning("codigoBanco->"+codigoBanco);
                          logger.warning("nombreBanco->"+nombreBanco);
                         
                      }else{
                          logger.warning("*** Error, La fila recuperada en catBancoByMoneda es null");    
                        }  
                     
              logger.warning("Termina seleccion de row Banco");      
              logger.warning("recuperarFilaActualCatCuentasNostroByBanco ...");      
            try{
                  BindingContainer binding = ADFUtils.getBindingContainer();
                  OperationBinding operBinding = binding.getOperationBinding("cargarCatalogoCuentasNostroByBanco");                        
                  operBinding.getParamsMap().put("Banco", codigoBanco);
                  operBinding.getParamsMap().put("moneda", descripcionMonedaContrato);
                  filaCuenta = (Row)operBinding.execute();                                            
                                                       
                  if(!operBinding.getErrors().isEmpty()){
                      logger.warning("*** OperationBinding recuperarFilaActualCatCuentasNostroByBanco devuelve errores");
                  }                                     
                                                                       
              }catch(Exception e){             
                   logger.warning(" :( Error al ejecutar el operationBinding recuperarFilaActualCatCuentasNostroByBanco: ", e);
              }      
                        
                if(filaCuenta != null){
                   codigoCuenta = (String)filaCuenta.getAttribute("CuentaNostro");
                   nombreCuenta =(String)filaCuenta.getAttribute("NombreCuenta"); 
                    logger.warning("codigoCuenta->"+codigoCuenta);
                    logger.warning("nombreCuenta->"+nombreCuenta);
                   
                }else{
                    logger.warning("*** Error, La fila recuperada en CatCuentasNostroByBanco es null");    
                }

            logger.warning("Termina seleccion de CatCuentasNostroByBanco");   
        }
    
   
       public void setDatosCuenta(Long idTransferencia, String codigoBanco, String nombreBanco, String codigoCuenta, String nombreCuenta ){           
            logger.warning("inicia el metodo setDatosCuenta para transferencia ->"+idTransferencia);            
            try{
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("establecerCuentaNostroATransferencia");
                    operationBinding.getParamsMap().put("idTransferencia", idTransferencia);
                    operationBinding.getParamsMap().put("codigoBanco", codigoBanco);
                    operationBinding.getParamsMap().put("nombreBanco", nombreBanco);                    
                    operationBinding.getParamsMap().put("cuenta", nombreCuenta);
                    operationBinding.getParamsMap().put("numeroCuenta", codigoCuenta );
                     tieneDatosCuenta = (Boolean)operationBinding.execute();   
                                                   
                     if(!operationBinding.getErrors().isEmpty()){
                        logger.warning("*** :( OperationBinding setDatosCuenta devuelve errores");
                    }
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "***Error en buscarTransferenciaParaReservarFondos " + e.getClass() + ":" + e.getMessage());
            }  
             
            if(tieneDatosCuenta){
                    logger.warning(" La primera transferencia recuperada de la tabla "+idTransferencia+" ya tiene datos cuenta, no se precargaron datos");                    
                    tieneDatosCuenta = Boolean.TRUE;
                }
            
            
            logger.warning(" termina el metodo setDatosCuenta");              
        }
        
 /** -----------------------Accesors------------------------------ **/


    
    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }
    
    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    public Long getIdContrato() {
        return idContrato;
    }

    public void setIdTcaMoneda(Integer idTcaMoneda) {
        this.idTcaMoneda = idTcaMoneda;
    }

    public Integer getIdTcaMoneda() {
        return idTcaMoneda;
    }

    public void setFechaDisponibilidadFondos(Date fechaDisponibilidadFondos) {
        this.fechaDisponibilidadFondos = fechaDisponibilidadFondos;
    }

    public Date getFechaDisponibilidadFondos() {
        return fechaDisponibilidadFondos;
    }

    public void setIdTransferencia(Long idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public Long getIdTransferencia() {
        return idTransferencia;
    }

    public void setDescripcionMonedaContrato(String descripcionMonedaContrato) {
        this.descripcionMonedaContrato = descripcionMonedaContrato;
    }

    public String getDescripcionMonedaContrato() {
        return descripcionMonedaContrato;
    }
    
    public void setTieneDatosCuenta(Boolean tieneDatosCuenta) {
        this.tieneDatosCuenta = tieneDatosCuenta;
    }

    public Boolean getTieneDatosCuenta() {
        return tieneDatosCuenta;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }
    
    public void setComponenteVisible(Boolean componenteVisible) {
        this.componenteVisible = componenteVisible;
    }

    public Boolean getComponenteVisible() {
        return componenteVisible;
    }
  
  
    public void setModoEjecucionVar(Integer modoEjecucionVar) {
        this.modoEjecucionVar = modoEjecucionVar;
    }

    public Integer getModoEjecucionVar() {
        return modoEjecucionVar;
    }

    public void setComponenteLectura(Boolean componenteLectura) {
        this.componenteLectura = componenteLectura;
    }

    public Boolean getComponenteLectura() {
        return componenteLectura;
    }
    
    public void setTienFechaDisFondos(Boolean tienFechaDisFondos) {
        this.tienFechaDisFondos = tienFechaDisFondos;
    }

    public Boolean getTienFechaDisFondos() {
        return tienFechaDisFondos;
    }
    
    public void setAplicarTransferenciasRestantes(Boolean aplicarTransferenciasRestantes) {
        this.aplicarTransferenciasRestantes = aplicarTransferenciasRestantes;
    }

    public Boolean getAplicarTransferenciasRestantes() {
        return aplicarTransferenciasRestantes;
    }

    public void setIdTareaBPM(Integer idTareaBPM) {
        this.idTareaBPM = idTareaBPM;
    }

    public Integer getIdTareaBPM() {
        return idTareaBPM;
    }

    public Timestamp getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Timestamp fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    public void obtenerFechaSolicitud(){
            try{
                BindingContainer bindings = ADFUtils.getBindingContainer();
                if(null!= idSolicitud){
                        OperationBinding operationBinding = bindings.getOperationBinding("fechaSolicitud");
                            operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
                            operationBinding.execute();   
                                                           
                             if(!operationBinding.getErrors().isEmpty()){
                                logger.warning("*** :( OperationBinding setDatosCuenta devuelve errores");
                            } 
                             else{
                                 fechaSolicitud=(Timestamp)operationBinding.getResult();
                                 logger.warning("Fecha solicitud obtenida: " + fechaSolicitud);
                                 }
                    }
               
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "***Error en buscarTransferenciaParaReservarFondos " + e.getClass() + ":" + e.getMessage());
            }  
        
        }
    
    
    public void setValueClaveNombreBanco(){
        logger.warning("into method setValueClaveNombreBanco");
        
        try{
            BindingContainer bindings = ADFUtils.getBindingContainer();
            if(null!= idSolicitud){
                OperationBinding operationBinding = bindings.getOperationBinding("setValueClaveNombreBanco");                     
                operationBinding.execute();
                                                   
                     if(!operationBinding.getErrors().isEmpty()){
                        logger.warning("Error al ejecutar operBinding setValueClaveNombreBanco ->"
                                       +operationBinding.getErrors().toString());
                    }
                }
           
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "***Error en setValueClaveNombreBanco ", e);
        }  
        
        logger.warning("end method setValueClaveNombreBanco");
    }

}
