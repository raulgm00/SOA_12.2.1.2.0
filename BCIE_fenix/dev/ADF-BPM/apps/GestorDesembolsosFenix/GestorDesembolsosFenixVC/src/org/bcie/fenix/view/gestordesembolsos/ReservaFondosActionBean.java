package org.bcie.fenix.view.gestordesembolsos;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.GregorianCalendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ResourceBundle;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;

import oracle.jbo.RowSetIterator;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class ReservaFondosActionBean {
   
    public static ADFLogger logger = null;
        
    public static final String BUNDLE = "view.GestorDesembolsosFenixVCBundle";
    private RichPanelGroupLayout contenedorDatosCuenta;
    private RichPanelGroupLayout contenedorNombreCuenta;
    private RichPanelGroupLayout contenedorNombreBanco;
    private RichPanelGroupLayout contenedorCuentas;
    private RichPanelGroupLayout contenedorAcciones;
    private RichPanelGroupLayout contenedorFecha;
    private RichPopup popUpConfirmaGuardarFecha;    


    public ReservaFondosActionBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    /**
     * Este metodo responde al cambio de valor del selectOneChoice que contiene la lista de bancos,
     * se utiliza para refrescar los demas componentes que tienen valores de la cuenta
     * @since 09/11/2016   
     */    

    public void seleccionBanco(ValueChangeEvent valueChangeEvent) {      
          String Banco = (String)valueChangeEvent.getNewValue();
          logger.warning("inicia metodo de seleccionBanco valor de Clave de Banco recuperado es->"+Banco);                          
           AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNombreBanco());
           AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorCuentas());  
           ADFUtils.setBoundAttributeValue("NombreCuenta", "");
         logger.warning("Termina metodo de seleccionBanco");        
    }
    
    /**
     * Este metodo responde al cambio de valor del selectOneChoice que contiene el listado de cuentas 
     * correspondientes al banco seleccionado, se utiliza para refrescar el nombre de la cuenta     
     * @since 09/11/2016   
     */ 
    
    public void seleccionCuenta(ValueChangeEvent valueChangeEvent) {      
          String Cuenta = (String)valueChangeEvent.getNewValue();
          logger.warning("Inicia metodo de seleccionCuenta valor de Clave de Cuenta recuperado es->"+Cuenta);                          
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNombreCuenta());              
          logger.warning("Termina metodo de seleccionCuenta");        
    }
    
    /**
     * Este metodo se utiliza para capturar el valor del check en una variable 
     * booleana que se ocupara en el metodo de asignarDatosCuenta, que es activado por un boton 
     * @since 09/11/2016   
     */ 
    
    public void eventoCheck(ValueChangeEvent valueChangeEvent) {
        try{
          ReservaFondosBean reservaBean = (ReservaFondosBean)JSFUtils.resolveExpression("#{pageFlowScope.ReservaFondosBean}");            
          reservaBean.setAplicarTransferenciasRestantes((Boolean)valueChangeEvent.getNewValue());
          logger.warning("Valor del check recuperado->"+reservaBean.getAplicarTransferenciasRestantes());
        }catch(Exception e){
            logger.warning("Error al asignar valor de check ->", e);
        }
    }    
    
    public void abirPopUpConfirmacion(){
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();        
            getPopUpConfirmaGuardarFecha().show(popupHints); 
        }
    
    public void cerrarPopUpConfirmacion(ActionEvent actionEvent){          
            getPopUpConfirmaGuardarFecha().hide(); 
        }

    public void confirmaAccion(ActionEvent actionEvent) {
        logger.warning("inicia metodo confirmaAccion");
        ReservaFondosBean reservaBean =
            (ReservaFondosBean) JSFUtils.resolveExpression("#{pageFlowScope.ReservaFondosBean}");

        String idBancoTransferencia = null;
        String numeroCuenta = null;
        Timestamp fechaDisponibilidadFondos = null;
        List<String> bundleKeyErrorList = new ArrayList<String>();

        if (ADFUtils.getBoundAttributeValue("FechaDisponibilidadFondos") != null)
            fechaDisponibilidadFondos =
                Timestamp.valueOf(ADFUtils.getBoundAttributeValue("FechaDisponibilidadFondos").toString());
        else
            bundleKeyErrorList.add("Fecha disponibilidad de fondos: Debe introducir un valor.");

        if (ADFUtils.getBoundAttributeValue("IdBancoTransferencia1") != null)
            idBancoTransferencia = ADFUtils.getBoundAttributeValue("IdBancoTransferencia1").toString();
        else
            bundleKeyErrorList.add("Banco a debitar: Debe realizar una selección.");

        if (ADFUtils.getBoundAttributeValue("NumeroCuenta1") != null)
            numeroCuenta = ADFUtils.getBoundAttributeValue("NumeroCuenta1").toString();
        else
            bundleKeyErrorList.add("Cuenta Nostro: Debe realizar una selección.");
        
        logger.warning("Valor de fechaDisponibilidadFondos: " + fechaDisponibilidadFondos);
        logger.warning("Valor de idBancoTransferencia: " + idBancoTransferencia);
        logger.warning("Valor de numeroCuenta: " + numeroCuenta);
        
        if (bundleKeyErrorList.size() > 0) {
            for (String bundleKey : bundleKeyErrorList)
                JSFUtils.addFacesErrorMessage(bundleKey);
        } else {
            if (!reservaBean.getTienFechaDisFondos()) {
                logger.warning("fecha disponibilidad fondos no guardada abriendo popUp...");
                if (validarPopupAplicar()) {
                    abirPopUpConfirmacion();
                } else {
                    JSFUtils.addFacesErrorMessage("La fecha de disponibilidad de recursos es menor a la fecha de solicitud de desembolso.");
                }
            } else {
                logger.warning("fecha disponibilidad fondos guardada asignando datos cuenta..");
                asignarDatosCuenta();
            }
        }

        logger.warning("Termina metodo confirmaAccion");
    }

    public void aceptarConfirmacion(ActionEvent actionEvent){
       logger.warning("Inicia metodo aceptarConfirmacion"); 
            getPopUpConfirmaGuardarFecha().hide();
            asignarDatosCuenta();
       logger.warning("Termina metodo aceptarConfirmacion");    
        }

    /**
     * Este metodo se utiliza para decidir que operationBinding se va a ejecutar evaluando la
     * variable de aplicarTransferenciasRestantes, esa variable se obtiene en el metodo eventoCheck
     * @since 09/11/2016   
     */ 
    
    public void asignarDatosCuenta() {
       ReservaFondosBean reservaBean = (ReservaFondosBean)JSFUtils.resolveExpression("#{pageFlowScope.ReservaFondosBean}");            
       logger.warning("inicia el metodo asignarDatosCuenta valor del check para aplicar a transferencias restantes -> "+reservaBean.getAplicarTransferenciasRestantes()); 
        String valorMoneda=null;
        
               AttributeBinding monedaDisponible;
               monedaDisponible = ADFUtils.findControlBinding("DescripcionMoneda");
               if(null!=monedaDisponible.getInputValue()){
                   logger.warning("Moneda a aplicar: " +monedaDisponible.getInputValue());
                   valorMoneda=(String)monedaDisponible.getInputValue();
                   }      
        String usuario = reservaBean.getUsuario();
        
        if(reservaBean.getAplicarTransferenciasRestantes()){
            logger.warning("Los datos cuenta se aplicara a todas las transferencias del contrato que no cuenten con datos cuenta");
           
                  Long idContrato   =  reservaBean.getIdContrato();
                                      
                String codigoBanco  = (ADFUtils.getBoundAttributeValue("IdBancoTransferencia") == null)? null 
                                    :  ADFUtils.getBoundAttributeValue("IdBancoTransferencia").toString();
                
                String nombreBanco  = (ADFUtils.getBoundAttributeValue("NombreBancoTransferencia") == null) ? null 
                                    :  ADFUtils.getBoundAttributeValue("NombreBancoTransferencia").toString();
                
                String codigoCuenta = (ADFUtils.getBoundAttributeValue("NumeroCuenta") == null) ? null 
                                    :  ADFUtils.getBoundAttributeValue("NumeroCuenta").toString();
                
                String nombreCuenta = (ADFUtils.getBoundAttributeValue("NombreCuenta") == null)? null 
                                    :  ADFUtils.getBoundAttributeValue("NombreCuenta").toString();
                             
                if(idContrato == null || codigoBanco == null || nombreBanco == null || codigoCuenta == null || nombreCuenta == null || usuario == null){
                      logger.warning("Los parametros requeridos son null");
                      JSFUtils.addFacesErrorMessage("Se deben completar todos los datos obligatorios.");                      
                 }else{
                    asignarCuantaATransferenciasRestantes(idContrato, codigoBanco, nombreBanco, codigoCuenta, nombreCuenta, usuario);    
                 }                           
        }else{             
              logger.warning("La cuanta seleccionada se aplicara a la transferencia seleccionada ->"+reservaBean.getIdTransferencia());
              asignarCuentaATransferencia(usuario); 
        }   
        if(valorMoneda!=null){
                monedaDisponible.setInputValue(valorMoneda);
            }
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorFecha());  
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAcciones());  
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorDatosCuenta());  
        
        logger.warning("termina el metodo asignarDatosCuenta"); 
    }
       
       
    public void reservarFonndosCallService(){
        logger.warning("termina el metodo ReservarFonndosCallService"); 
           String respuesta = null;
            ReservaFondosBean reservaBean = (ReservaFondosBean)JSFUtils.resolveExpression("#{pageFlowScope.ReservaFondosBean}");
        
                          Long idContrato   =  reservaBean.getIdContrato();
                             
                             String usuario = reservaBean.getUsuario();
                             
             Date FechaDisponibilidadFondos  = (ADFUtils.getBoundAttributeValue("FechaDisponibilidadFondos") == null)? null 
                                            :  (Date)ADFUtils.getBoundAttributeValue("FechaDisponibilidadFondos");
            
            try{
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("servicioReservarFondos");
                    operationBinding.getParamsMap().put("idDesembolso", idContrato);
                    operationBinding.getParamsMap().put("usuarioAutoriza", usuario);
                    operationBinding.getParamsMap().put("fechaRequerido", FechaDisponibilidadFondos);       
                
                
                      respuesta = (String) operationBinding.execute();   
            
                     if(!operationBinding.getErrors().isEmpty()){
                        logger.warning(" :( OperationBinding servicioReservarFondos devuelve errores");                      
                     }
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "***Error en agregarDatosCuantaAllTransferencias " + e.getClass() + ":" + e.getMessage());
            }
        logger.warning("termina el metodo ReservarFonndosCallService"+respuesta);         
        }
       
    public void refreshCampos(Long idTransferencia, String moneda) {
        logger.warning("Inside refreshCampos.");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("refreshCampos");
        operationBinding.getParamsMap().put("idTransferencia", idTransferencia);
        operationBinding.getParamsMap().put("descripcionMoneda", moneda);
        
        operationBinding.execute();
    }
       
    /**
     * Este metodo ejecuta un operationBinding que apunta al metodo    de la     VO  para asignar
     * los datos de la cuenta seleccionados a la transferencia seleccionada en la tabla
     * @since 09/11/2016   
     */ 
    
    public void asignarCuentaATransferencia(String usuario){
       logger.warning("*Inf, Inicia metodo asignar cuenta");   
            Boolean  reservoFondos = Boolean.FALSE;
            try{
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("guardarDatosCuentaTransferenciaCurrent");                   
                     operationBinding.getParamsMap().put("usuarioAutoriza", usuario);
                     operationBinding.execute();   
            
                     if(!operationBinding.getErrors().isEmpty()){
                        logger.warning("***Error, OperationBinding guardarDatosCuentaTransferenciaCurrent devuelve errores"
                                       +operationBinding.getErrors());     
                         //JSFUtils.addFacesErrorMessage("Error al asignar cuanta a tranferencia ->"+operationBinding.getErrors());                      
                     }else{
                             reservoFondos =  (Boolean)operationBinding.getResult();
                         }
                
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "***Error en agregarDatosCuantaAllTransferencias " + e);
            } 
            
            if(reservoFondos){
                    ReservaFondosBean reservaBean = (ReservaFondosBean)JSFUtils.resolveExpression("#{pageFlowScope.ReservaFondosBean}");
                    reservaBean.setTienFechaDisFondos(Boolean.TRUE);
                    reservaBean.setTieneDatosCuenta(Boolean.TRUE);
                    reservaBean.setComponenteLectura(Boolean.TRUE);
                }
            
            
       logger.warning("*Inf, Termina metodo asignar cuenta");
        }
    
    /**
     * Este metodo ejecuta un operationBinding que apunta al metodo  de la  VO  para asignar
     * los datos de la cuenta a todas las transferencias del contrato que no cuenten con datos cuenta
     * @since 09/11/2016    
     */ 
    
    public void asignarCuantaATransferenciasRestantes(Long idContrato, String codigoBanco, String nombreBanco, String codigoCuenta, String nombreCuenta, String usuario){
        logger.warning("Inicia metodo asignarCuantaATransferenciasRestantes para contrato:"+idContrato);            
            Boolean  reservoFondos = Boolean.FALSE;
            try{
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("agregarDatosCuantaAllTransferencias");
                    operationBinding.getParamsMap().put("idContrato", idContrato);
                    operationBinding.getParamsMap().put("codigoBanco", codigoBanco);
                    operationBinding.getParamsMap().put("nombreBanco", nombreBanco);
                    operationBinding.getParamsMap().put("nombreCuenta", nombreCuenta);
                    operationBinding.getParamsMap().put("numCuenta", codigoCuenta);
                    operationBinding.getParamsMap().put("usuarioAutoriza", usuario);
                    operationBinding.execute();   
            
                     if(!operationBinding.getErrors().isEmpty()){
                        logger.warning("OperationBinding agregarDatosCuantaAllTransferencias devuelve errores: "+operationBinding.getErrors());                      
                     }else{
                             reservoFondos =  (Boolean)operationBinding.getResult();
                         }
                
                
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "***Error en agregarDatosCuantaAllTransferencias " + e.getClass() + ":" + e.getMessage());
            } 
            
            if(reservoFondos){
                    ReservaFondosBean reservaBean = (ReservaFondosBean)JSFUtils.resolveExpression("#{pageFlowScope.ReservaFondosBean}");
                        reservaBean.setTienFechaDisFondos(Boolean.TRUE);
                        reservaBean.setTieneDatosCuenta(Boolean.TRUE);
                        reservaBean.setComponenteLectura(Boolean.TRUE);
            }
                        
            logger.warning(" Termina el metodo recuperarTransferenciaParaReservarFondos");

        logger.warning("Termina metodo asignarCuantaATodasLasTransferencias");
        }
    
    /**
     * Este metodo responde al selectionListener de la tabla capturando la fila current y recuperando los
     * valores de esa fila y seteandolo a la clase bean de tipo pageFlowScope,
     * @since 09/11/2016
     */

    public void seleccionarTransferencia(SelectionEvent selectionEvent) {
        logger.warning("Inicia metodo seleccionarTransferencia");
        ReservaFondosBean reservaBean =
            (ReservaFondosBean) JSFUtils.resolveExpression("#{pageFlowScope.ReservaFondosBean}");

        Long idTransferencia = new Long(0);
        String numeroReserva = null;

        // Actualizamos row seleccionado en tabla
        JSFUtils.resolveMethodExpression("#{bindings.TransferenciasBancariasTablaVO1.collectionModel.makeCurrent}",
                                         Object.class, new Class[] { SelectionEvent.class }, new Object[] {
                                         selectionEvent });
        //Recupera el "ID" de la fila seleccionada en la tabla
        if (null != JSFUtils.resolveExpression("#{bindings.TransferenciasBancariasTablaVO1.IdTransferencia}")) {
            idTransferencia =
                (Long) JSFUtils.resolveExpression("#{bindings.TransferenciasBancariasTablaVO1.IdTransferencia}");
        }
        if (null != JSFUtils.resolveExpression("#{bindings.TransferenciasBancariasTablaVO1.NumeroReserva}")) {
            numeroReserva =
                JSFUtils.resolveExpression("#{bindings.TransferenciasBancariasTablaVO1.NumeroReserva}").toString();
        }
        
        String moneda = reservaBean.getDescripcionMonedaContrato();
        
        reservaBean.setIdTransferencia(idTransferencia);

        if (reservaBean.getModoEjecucionVar() == FenixConstants.MODO_EJECUCION_LECTURA) {
            logger.warning("seleccion de transferencia en modo lectura de componente  ");
            
            refreshCampos(idTransferencia, moneda);
            //reservaBean.setTieneDatosCuenta(Boolean.TRUE);
        } else {

            if (numeroReserva != null) {
                logger.warning("La transferencia seleccionada ya cuenta con numero reserva desabilitando seleccion de campos");
                reservaBean.setTieneDatosCuenta(Boolean.TRUE);
                reservaBean.setTienFechaDisFondos(Boolean.TRUE);
                reservaBean.recuperarTransferenciaParaReservarFondos(idTransferencia, moneda);
            } else {
                logger.warning("La transferencia seleccionada no tiene numero reserva");
                reservaBean.recuperarTransferenciaParaReservarFondos(idTransferencia, moneda);
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAcciones());
            
        }
        reservaBean.setValueClaveNombreBanco();
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorDatosCuenta());
        
        logger.warning("El idTransferencia seleccionado es: " + idTransferencia + "numero de reserva: " +
                       numeroReserva);
        logger.warning("Termina metodo seleccionarTransferencia");
    }

    
    public void comitTransferencia(){
        logger.warning("Inicia metodo comitTransferencia");
        ReservaFondosBean reservaBean = (ReservaFondosBean)JSFUtils.resolveExpression("#{pageFlowScope.ReservaFondosBean}");
        Boolean commit = Boolean.FALSE;
            try{
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("comitTransferencia");                    
                    commit = (Boolean)operationBinding.execute();   
            
                     if(!operationBinding.getErrors().isEmpty()){
                        logger.warning(" :( OperationBinding devuelve errores");                      
                    }
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "***Error en buscarTransferenciaParaReservarFondos " + e.getClass() + ":" + e.getMessage());
            } 
                        
            if(commit){
                logger.warning("ok comit realizado con exito");            
                reservaBean.setTieneDatosCuenta(Boolean.TRUE);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAcciones());  
                AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorDatosCuenta());  
            }else{
                logger.warning("*** Error, no se pudo realizar el comit en transferencias");
                }
            
            
        logger.warning("Termina metodo comitTransferencia");
        }

    /**
     * Este metodo este metodo se ocupa en la clase bean del la tarea BPM de reservar fondos,
     * se genero aqui para probar la respuesta del metodo
     * 
     * @since 14/11/2016   
     */ 
    public void validarReglasReservaFondos(ActionEvent actionEvent){
        logger.warning("**** inicia metodo de validarReglasReservaFondos");
            Map mapaReglasValidadas = new HashMap();
        
            try{ 
                BindingContainer binding  = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding("validarReglasPorTareaService");   
                    operBinding.getParamsMap().put("idContratoDesembolso", 12L);
                    operBinding.getParamsMap().put("idTarea", 156L);
                    operBinding.getParamsMap().put("instanciaTarea", "17");
                   mapaReglasValidadas = (Map) operBinding.execute(); 
                                
                
                if(!operBinding.getErrors().isEmpty()){
                    logger.warning(" :( OperationBinding devuelve errores");
                    JSFUtils.addFacesErrorMessage("Error, operationBinding devuelve errores");  
                } 
                 
            }catch(Exception e){                
               logger.warning("Tab transferencias, Error al consultar la moneda  BHQ del cliente "+ e.getClass() +" ->"+e.getMessage());
            }
            
            
             
        logger.warning("**** Termina metodo de validarReglasReservaFondos");               
        }




    public void validarFechaRecursos(ValueChangeEvent valueChangeEvent) {
        logger.warning("**** inicia metodo de validarFechaRecursos");               
               
                Date fechaDisponibilidadFondos  = (ADFUtils.getBoundAttributeValue("FechaDisponibilidadFondos") == null)? null 
                                               :  (Date)ADFUtils.getBoundAttributeValue("FechaDisponibilidadFondos");
               
               
                  logger.warning("*** valor recuperado de fecha solicitud->"+fechaDisponibilidadFondos);           
                if(null == fechaDisponibilidadFondos){
                   JSFUtils.addFacesErrorMessage("Error, Se esta recibiendo un valor nulo en fecha solicitud :( ");                  
                 } 
                try{
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String FechaActual = (dateFormat.format(new Date()));
                        Date FechaActualF = dateFormat.parse(FechaActual);
                    
                     int res = fechaDisponibilidadFondos.compareTo(FechaActualF);
                     if (res == -1) {
                         JSFUtils.addFacesErrorMessage("La fecha de la fecha de Disponibilidad de Fondos no puede ser menor a la fecha actual");  
                     }
                    logger.warning("**** Termina metodo de validarFechaRecursos res->"+res+"fecha actual->"+FechaActualF);    
                
                }catch(Exception ex){
                    ex.printStackTrace();            
                }
                              
    }

    public void FechaRecursosMayorFechaActual(ActionEvent actionEvent){        
   
        } 




    public Date parseFecha(){
            SimpleDateFormat formatter = new SimpleDateFormat("MMM dd z yyyy");
            Calendar cal = new GregorianCalendar();
            Date FechaActual = new Date();
                try {
                   
                    logger.warning("Fecha actual->"+FechaActual);
                    String fecha = FechaActual.toString().substring(4).replaceAll(" 00:00:00", "");
                    logger.warning(fecha);
                    Date fechaEstimadaDisponibilidad = formatter.parse(fecha);
                   
                } catch (ParseException e) {
                    logger.warning("Error al convertir fecha" + e);
                }
                
        return null;
        }



    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (null != resourceBundle) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }
    
    
    public List onSuggestBancoTransferencia(String string) {
        logger.warning("Into onSuggestBancoTransferencia");
        logger.warning("string :"+string);
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();;
        SelectItem element = null;
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("CatBancosByMonedaVO");         
        
         RowSetIterator iter = list.getListRowSetIterator();                  
         iter.reset();
         Row row = null;
         int contador = 0;

        logger.warning("Cantidad de bancos recuperadas:"+list.getAllRowsInRange().length);
         if(null != iter){            
            
            for(int i = 0; i<= list.getAllRowsInRange().length; i++){
                  row = iter.getRowAtRangeIndex(i);
                    if(null != row){
                       String value = (String) row.getAttribute("ClaveNombreBanco");
                       logger.warning("ClaveNombreBanco:"+value);
                       if(null != string &&  null != value){                             
                          if(value.toUpperCase().contains(string.toUpperCase())){
                               contador = contador +1;                                
                               element = new SelectItem(value);
                               logger.warning("value add:"+element);
                               resultItems.add(element);
                             }
                         }else{
                               logger.warning("value in view : "+string+" and value in list : "+value);
                         }
                    }
                }                
            
        }else{
          logger.warning("Iterator is null");
          element = new SelectItem("");
          resultItems.add(element);
        }
         iter.closeRowSetIterator();

        logger.warning("records found:"+contador);
        logger.warning("Leave onSuggestBancoTransferencia");
        return resultItems;
    }


    /** -----------------------Accesors------------------------------ **/
    public void setContenedorDatosCuenta(RichPanelGroupLayout contenedorDatosCuenta) {
        this.contenedorDatosCuenta = contenedorDatosCuenta;
    }

    public RichPanelGroupLayout getContenedorDatosCuenta() {
        return contenedorDatosCuenta;
    }

    public void setContenedorNombreCuenta(RichPanelGroupLayout contenedorNombreCuenta) {
        this.contenedorNombreCuenta = contenedorNombreCuenta;
    }

    public RichPanelGroupLayout getContenedorNombreCuenta() {
        return contenedorNombreCuenta;
    }

    public void setContenedorNombreBanco(RichPanelGroupLayout contenedorNombreBanco) {
        this.contenedorNombreBanco = contenedorNombreBanco;
    }

    public RichPanelGroupLayout getContenedorNombreBanco() {
        return contenedorNombreBanco;
    }

    public void setContenedorCuentas(RichPanelGroupLayout contenedorCuentas) {
        this.contenedorCuentas = contenedorCuentas;
    }

    public RichPanelGroupLayout getContenedorCuentas() {
        return contenedorCuentas;
    }
       
    public void setContenedorAcciones(RichPanelGroupLayout contenedorAcciones) {
        this.contenedorAcciones = contenedorAcciones;
    }

    public RichPanelGroupLayout getContenedorAcciones() {
        return contenedorAcciones;
    }


    public void setContenedorFecha(RichPanelGroupLayout contenedorFecha) {
        this.contenedorFecha = contenedorFecha;
    }

    public RichPanelGroupLayout getContenedorFecha() {
        return contenedorFecha;
    }

    public void setPopUpConfirmaGuardarFecha(RichPopup popUpConfirmaGuardarFecha) {
        this.popUpConfirmaGuardarFecha = popUpConfirmaGuardarFecha;
    }

    public RichPopup getPopUpConfirmaGuardarFecha() {
        return popUpConfirmaGuardarFecha;
    }
    
    public Boolean validarPopupAplicar(){
        Boolean respuesta=Boolean.FALSE;
            ReservaFondosBean reservaBean = (ReservaFondosBean)JSFUtils.resolveExpression("#{pageFlowScope.ReservaFondosBean}");
            
            AttributeBinding fechaDisponible;
            fechaDisponible = ADFUtils.findControlBinding("FechaDisponibilidadFondos");
            if(null!=fechaDisponible.getInputValue()){
                Date valorActual=(Date)fechaDisponible.getInputValue();
                logger.warning("Fecha actualmente de disponibilidad de fondos: " + valorActual);
                Date valorSolicitud=null;
                if(null!=reservaBean.getFechaSolicitud()){
                        valorSolicitud=new Date(reservaBean.getFechaSolicitud().getTime());
                        logger.warning("getFechaSolicitud actualmente : " + valorSolicitud);
                        if(valorSolicitud.compareTo(valorActual)>0){
                            logger.warning("La fecha de disponibilidad de recursos es menor a la fecha de solicitud de desembolso.");
                            }
                        else{
                                respuesta=Boolean.TRUE;
                            }
                    }
                }
        
            Date fechaDisponibilidadFondos  = (ADFUtils.getBoundAttributeValue("FechaDisponibilidadFondos") == null)? null 
                                           :  (Date)ADFUtils.getBoundAttributeValue("FechaDisponibilidadFondos");
            
        return respuesta;
        }

}
