package org.bcie.fenix.view.estructuracionprestamo;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.text.DecimalFormat;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.view.lineacredito.AdministradorLineaCreditoBean;


public class AdministradorEstructuracionPrestamoActionsBean {
    
    private static ADFLogger logger = null;
    //popup´s TramoFormalizar
    private RichPopup popupAgregarTramo;
    private RichPopup popupEliminarTramo;
    private RichPopup popupActualizarTramo;
    //popup´s ParticipanteTramoFormalizar
    private RichPopup popupAgregarParticipanteTramo;
    private RichPopup popupEliminarParticipanteTramo;
    private RichPopup popupActualizarParticipanteTramo;
    // Inicializacion de componentes visuales en pantalla
    private RichOutputText otInitForm;
    
    public AdministradorEstructuracionPrestamoActionsBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void consultarParticipanteTramoFormalizarSelectionListener(SelectionEvent selectionEvent) {
        Long idTramoFormalizar = new Long(0);
        logger.log(ADFLogger.TRACE, "Inside ConsultarParticipanteTramoFormalizarSelectionListener");
        // Actualizamos row seleccionado en tabla
        JSFUtils.resolveMethodExpression("#{bindings.TramoFormalizarVO.collectionModel.makeCurrent}", 
                                         Object.class, new Class[] { SelectionEvent.class }, 
                                         new Object[] { selectionEvent });
        //Recupera el "ID" de tramoFormalizarVO seleccionada en la tabla
        if(null != JSFUtils.resolveExpression("#{bindings.Id.inputValue}")){
            idTramoFormalizar=(Long)JSFUtils.resolveExpression("#{bindings.Id.inputValue}");
        }
        //Consultar ParticipanteTramoFormalizar por idTramoFormalizar
        consultarParticipanteTramoFormalizar(idTramoFormalizar);
    }
    
    public void consultarParticipanteTramoFormalizar(Long idTramoFormalizar){
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        logger.log(ADFLogger.WARNING,"value idTramo :" + idTramoFormalizar);
        //Se invoca el metodo "setIdTramo"
        operationBinding = bindings.getOperationBinding("setIdTramo");
        //Se envian los parametros del metodo "setIdTramo"
        operationBinding.getParamsMap().put("value", idTramoFormalizar);
        // Invocamos método
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
    }

    public void agregarTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside agregarTramoFormalizarActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        //ejecutar metodo crear row
        operationBinding = bindings.getOperationBinding("crearRowAgregarTramoFormalizar");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        // Abrimos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupAgregarTramo().show(popupHints);
    }
    
    public void cancelarAgregarTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarAgregarTramoFormalizarActionListener");
        //cerramos popup
        getPopupAgregarTramo().hide();
    }
    
    public void aceptarAgregarTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside aceptarAgregarTramoFormalizarActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String nombreTramo= null;
        BigDecimal monto = null;
        BigDecimal porcentaje = null;
        Long idContrato = null;
        BigDecimal montoTotal = null;
        AdministradorEstructuracionPrestamoBean administradorEstructuracionPrestamoBean = null;
        /*
         *     FNXII-2966 
         *     Pruebas-PC05-Formalización-Monto tramos mayor a sindicados
         *     Gabriel Niño Rosales
         *     01/03/2016
         */
        // Generar una instancia de administradorEstructuracionPrestamoBean
        administradorEstructuracionPrestamoBean = (AdministradorEstructuracionPrestamoBean)
                            JSFUtils.resolveExpression("#{pageFlowScope.AdministradorEstructuracionPrestamoMB}");
        // Recupera el montoTotal de tramoFormalizarVO
        if(null != JSFUtils.resolveExpression("#{bindings.MontoTotal.inputValue}")){
            montoTotal=(BigDecimal)JSFUtils.resolveExpression("#{bindings.MontoTotal.inputValue}");
        }else{
            //Si viene null, se tomara como "0"
            montoTotal = new BigDecimal(0);
        }
        //Recuperar los valores de AgregarTramoFormalizarVOIterator
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.idContrato}")){
            Integer idContratoLong = (Integer)JSFUtils.resolveExpression("#{pageFlowScope.idContrato}");
            idContrato =idContratoLong.longValue();
        }
        if(null != JSFUtils.resolveExpression("#{bindings.NombreTramo1.inputValue}")){
            nombreTramo=(String)JSFUtils.resolveExpression("#{bindings.NombreTramo1.inputValue}");
        }
        if(null != JSFUtils.resolveExpression("#{bindings.Monto1.inputValue}")){
            monto=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Monto1.inputValue}");
        }
        if(null != JSFUtils.resolveExpression("#{bindings.Porcentaje1.inputValue}")){
            porcentaje=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Porcentaje1.inputValue}");
        }
        operationBinding = bindings.getOperationBinding("crearTramoFormalizar");
        //Se envian los parametros del metodo crearTramoFormalizar
        operationBinding.getParamsMap().put("idContrato", idContrato);
        operationBinding.getParamsMap().put("nombreTramo", nombreTramo);
        operationBinding.getParamsMap().put("montoTramo", monto);
        operationBinding.getParamsMap().put("porcentaje",porcentaje);
        /*
         *     FNXII-2966 
         *     Pruebas-PC05-Formalización-Monto tramos mayor a sindicados
         *     Gabriel Niño Rosales
         *     01/03/2016
         */
        // Sumar el monto del nuevo tramoForalizacion y sumarlo al montoTotal de vista
        Double montoTotalComparar=montoTotal.doubleValue() + monto.doubleValue();
        // Validar que el montoTotal no valla a superar al monto total sindicado, antes de insertar el
        // nuevo row en tramoFormalizarVO
        if(montoTotalComparar.doubleValue()<=administradorEstructuracionPrestamoBean.getMontoTotalSindicado()){
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }else{
                // Muestra mensaje de exito
                JSFUtils.addFacesInformationMessage("Se agrego con éxito.");
                // Cierra el popupAgregarTramo
                getPopupAgregarTramo().hide();
            }
        }else{
            //Muestra mensaje
            JSFUtils.addFacesInformationMessage("La suma de los montos no puede ser mayor al monto total sindicado.");
            // Cierra el popupAgregarTramo
            getPopupAgregarTramo().hide();
        }
    }

    public void eliminarTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside eliminarTramoFormalizarActionListener");
        // Abrimos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupEliminarTramo().show(popupHints);
    }
    
    public void aceptarEliminarTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside aceptarEliminarTramoFormalizarActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long idTramoFormalizar = null;
        //idTramoFormalizar 
        if(null != JSFUtils.resolveExpression("#{bindings.Id.inputValue}")){
            idTramoFormalizar=(Long)JSFUtils.resolveExpression("#{bindings.Id.inputValue}");
        }
        operationBinding = bindings.getOperationBinding("eliminarTramoFormalizar");
        //Se envian los parametros del metodo crearTramoFormalizar
        operationBinding.getParamsMap().put("idTramoFormalizar", idTramoFormalizar); //InstanciaProceso(BPM)
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else{
            // Recuperar el id de TramoFormalizar que es el current en el TramoFormalizarVO
            if(null != JSFUtils.resolveExpression("#{bindings.Id.inputValue}")){
                idTramoFormalizar=(Long)JSFUtils.resolveExpression("#{bindings.Id.inputValue}");
            }
            // Consultar los participantesTramoFormalizar por idTramoFormalizar
            consultarParticipanteTramoFormalizar(idTramoFormalizar);
            // Muestra mensaje de exito
            JSFUtils.addFacesInformationMessage("Se elimino con éxito.");
            // Cierra el PopupEliminarTramo
            getPopupEliminarTramo().hide();
        }
        
    }
    
    public void cancelarEliminarTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarEliminarTramoFormalizarActionListener");
        // Cierra el popupEliminarTramo
        getPopupEliminarTramo().hide();
    }

    public void actualizarTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside actualizarTramoFormalizarActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        //ejecutar metodo crear row
        operationBinding = bindings.getOperationBinding("crearRowAgregarTramoFormalizar");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else{
        //Recuperar valores de tramoFormalizarVO currentRow
        //Recuperar nombreTramo
        if(JSFUtils.resolveExpression("#{bindings.NombreTramo.inputValue}") != null)
        {   
            String nombreTramo=(String)JSFUtils.resolveExpression("#{bindings.NombreTramo.inputValue}");
            //Asignar el valor al inputValue del dialog
            JSFUtils.setExpressionValue("#{bindings.NombreTramo1.inputValue}",nombreTramo);
        }
        //Recuperar monto
        if(JSFUtils.resolveExpression("#{bindings.Monto.inputValue}") != null)
        {   
            BigDecimal monto=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Monto.inputValue}");
            //Asignar el valor al inputValue del dialog
            JSFUtils.setExpressionValue("#{bindings.Monto1.inputValue}",monto);
        }
        //Recuperar porcentaje
        if(JSFUtils.resolveExpression("#{bindings.Porcentaje.inputValue}") != null)
        {   
            BigDecimal porcentaje=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Porcentaje.inputValue}");
            //Asignar el valor al inputValue del dialog
            JSFUtils.setExpressionValue("#{bindings.Porcentaje1.inputValue}",porcentaje);
        }
        // Abrimos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupActualizarTramo().show(popupHints);
        }
    }
    
    public void aceptarActualizarTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside aceptarActualizarTramoFormalizarActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long idTramoFormalizar = null;
        String nombreTramo= null;
        BigDecimal monto = null;
        BigDecimal porcentaje = null;
        AdministradorEstructuracionPrestamoBean administradorEstructuracionPrestamoBean = null;
        BigDecimal montoTotal = null;
        // Generar una instancia de administradorEstructuracionPrestamoBean
        administradorEstructuracionPrestamoBean = (AdministradorEstructuracionPrestamoBean)
                                    JSFUtils.resolveExpression("#{pageFlowScope.AdministradorEstructuracionPrestamoMB}");
        // Recupera el montoTotal de tramoFormalizarVO
        if(null != JSFUtils.resolveExpression("#{bindings.MontoTotal.inputValue}")){
            montoTotal=(BigDecimal)JSFUtils.resolveExpression("#{bindings.MontoTotal.inputValue}");
        }else{
            //Si viene null, se tomara como "0"
            montoTotal = new BigDecimal(0);
        }
        //idTramoFormalizar 
        if(null != JSFUtils.resolveExpression("#{bindings.Id.inputValue}")){
            idTramoFormalizar=(Long)JSFUtils.resolveExpression("#{bindings.Id.inputValue}");
        }
        if(null != JSFUtils.resolveExpression("#{bindings.NombreTramo1.inputValue}")){
            nombreTramo=(String)JSFUtils.resolveExpression("#{bindings.NombreTramo1.inputValue}");
        }
        if(null != JSFUtils.resolveExpression("#{bindings.Monto1.inputValue}")){
            monto=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Monto1.inputValue}");
        }
        if(null != JSFUtils.resolveExpression("#{bindings.Porcentaje1.inputValue}")){
            porcentaje=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Porcentaje1.inputValue}");
        }
        operationBinding = bindings.getOperationBinding("actualizarTramoFormalizar");
        //Se envian los parametros del metodo crearTramoFormalizar
        operationBinding.getParamsMap().put("idTramoFormalizar", idTramoFormalizar); //InstanciaProceso(BPM)
        operationBinding.getParamsMap().put("nombreTramo", nombreTramo);
        operationBinding.getParamsMap().put("monto", monto);
        operationBinding.getParamsMap().put("porcentaje", porcentaje);
        // Sumar el monto del nuevo tramoForalizacion y sumarlo al montoTotal de vista
        Double montoTotalComparar=montoTotal.doubleValue() + monto.doubleValue();
        // Validar que el montoTotal no valla a superar al monto total sindicado, antes de insertar el
        // nuevo row en tramoFormalizarVO
        if(montoTotalComparar.doubleValue()<=administradorEstructuracionPrestamoBean.getMontoTotalSindicado()){
        operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }else{
                // Muestra mensaje de exito
                JSFUtils.addFacesInformationMessage("Se actualizo con éxito.");
                // Cierra el PopupActualizarTramo
                getPopupActualizarTramo().hide();
            }
            
        }else{
             //Muestra mensaje
            JSFUtils.addFacesInformationMessage("La suma de los montos no puede ser mayor al monto total sindicado.");
            // Cierra el PopupActualizarTramo
            getPopupActualizarTramo().hide();
        }
    }
    
    public void cancelarActualizarTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarActualizarTramoFormalizarActionListener");
        // Cierra el PopupActualizarTramo
        getPopupActualizarTramo().hide();
    }

    public void agregarParticipanteTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside agregarParticipanteTramoFormalizarActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        //ejecutar metodo crear row
        operationBinding = bindings.getOperationBinding("crearRowAgregarParticipanteTramoFormalizar");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        // Abrimos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupAgregarParticipanteTramo().show(popupHints);
    }
    
    public void cancelarAgregarParticipanteTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarAgregarParticipanteTramoFormalizarActionListener");
        //cerramos popup
        getPopupAgregarParticipanteTramo().hide();
    }
    
    public void aceptarAgregarParticipanteTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside aceptarAgregarParticipanteTramoFormalizarActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        BigDecimal monto = null;
        BigDecimal porcentaje = null;
        Long idTramoFormalizar = null;
        Long idCliente = null;
        BigDecimal montoTotalParticipante = null;
        BigDecimal montoTramo = null;
        
        // Recuperar el valor del monto total de los participantes
        if(null != JSFUtils.resolveExpression("#{bindings.MontoTotal1.inputValue}")){
            montoTotalParticipante=(BigDecimal)JSFUtils.resolveExpression("#{bindings.MontoTotal1.inputValue}");
        }else{
            //Si viene null, se tomara como "0"
            montoTotalParticipante = new BigDecimal(0);
        }
        
        // Recuperar el valor del monto del tramo
        if(null != JSFUtils.resolveExpression("#{bindings.Monto.inputValue}")){
            montoTramo=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Monto.inputValue}");
        }
        
        //idTramoFormalizar 
        if(null != JSFUtils.resolveExpression("#{bindings.Id.inputValue}")){
            idTramoFormalizar=(Long)JSFUtils.resolveExpression("#{bindings.Id.inputValue}");
        }
        //idCliente
        if(null != JSFUtils.resolveExpression("#{bindings.IdCliente.inputValue}")){
            idCliente=(Long)JSFUtils.resolveExpression("#{bindings.IdCliente.inputValue}");
        }
        //monto
        if(null != JSFUtils.resolveExpression("#{bindings.Monto2.inputValue}")){
            monto=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Monto2.inputValue}");
        }
        //porcentaje
        if(null != JSFUtils.resolveExpression("#{bindings.Porcentaje2.inputValue}")){
            porcentaje=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Porcentaje2.inputValue}");
        }
        
        operationBinding = bindings.getOperationBinding("crearParticipanteTramoFormalizar");
        //Se envian los parametros del metodo crearTramoFormalizar
        operationBinding.getParamsMap().put("idCliente", idCliente);
        operationBinding.getParamsMap().put("idTramoFormalizar", idTramoFormalizar); //InstanciaProceso(BPM)
        operationBinding.getParamsMap().put("monto", monto);
        operationBinding.getParamsMap().put("porcentaje", porcentaje);
        
        // Sumar el monto del nuevo participante y sumarlo al montoTotal de vista
        Double montoTotalComparar=montoTotalParticipante.doubleValue() + monto.doubleValue();
        // Validar que el montoTotal no valla a superar al monto del tramo, antes de insertar el nuevo Row
        if(montoTotalComparar.doubleValue()<=montoTramo.doubleValue()){
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }else{
                // Muestra mensaje de exito
                JSFUtils.addFacesInformationMessage("Se agrego con éxito.");
                // Cierra el PopupAgregarParticipanteTramo
                getPopupAgregarParticipanteTramo().hide();
            }
        }else{
            //Muestra mensaje
            JSFUtils.addFacesInformationMessage("La suma de los participantes no puede ser mayor al monto del tramo.");
            // Cierra el PopupAgregarParticipanteTramo
            getPopupAgregarParticipanteTramo().hide();
        }
        
    }

    public void eliminarParticipanteTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside eliminarParticipanteTramoFormalizarActionListener");
        // Abrimos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupEliminarParticipanteTramo().show(popupHints);
    }
    
    public void aceptarEliminarParticipanteTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside aceptarEliminarParticipanteTramoFormalizarActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long idParticipanteTramoFormalizar = null;
        //idParticipanteTramoFormalizar 
        if(null != JSFUtils.resolveExpression("#{bindings.Id1.inputValue}")){
            idParticipanteTramoFormalizar=(Long)JSFUtils.resolveExpression("#{bindings.Id1.inputValue}");
        }
        operationBinding = bindings.getOperationBinding("eliminarParticipanteTramoFormalizar");
        //Se envian los parametros del metodo crearTramoFormalizar
        operationBinding.getParamsMap().put("idParticipanteTramoFormalizar", idParticipanteTramoFormalizar);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else{
            // Muestra mensaje de exito
            JSFUtils.addFacesInformationMessage("Se elimino con éxito.");
            // Cierra el popupEliminarTramo
            getPopupEliminarParticipanteTramo().hide();
        }
    }
    
    public void cancelarEliminarParticipanteTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarEliminarParticipanteTramoFormalizarActionListener");
        // Cierra el popupEliminarTramo
        getPopupEliminarParticipanteTramo().hide();
    }

    public void actualizarParticipanteTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside actualizarParticipanteTramoFormalizarActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        //ejecutar metodo crear row
        operationBinding = bindings.getOperationBinding("crearRowAgregarParticipanteTramoFormalizar");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else{
            //Recuperar valores de tramoFormalizarVO currentRow
            //Recuperar idCliente
            if(JSFUtils.resolveExpression("#{bindings.IdCliente1.inputValue}") != null)
            {   
                Long idCliente=(Long)JSFUtils.resolveExpression("#{bindings.IdCliente1.inputValue}");
                //Asignar el valor al inputValue del dialog
                JSFUtils.setExpressionValue("#{bindings.IdCliente.inputValue}",idCliente);
            }
            //Recuperar monto
            if(JSFUtils.resolveExpression("#{bindings.Monto3.inputValue}") != null)
            {   
                BigDecimal monto=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Monto3.inputValue}");
                //Asignar el valor al inputValue del dialog
                JSFUtils.setExpressionValue("#{bindings.Monto2.inputValue}",monto);
            }
            //Recuperar porcentaje
            if(JSFUtils.resolveExpression("#{bindings.Porcentaje3.inputValue}") != null)
            {   
                BigDecimal porcentaje=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Porcentaje3.inputValue}");
                //Asignar el valor al inputValue del dialog
                JSFUtils.setExpressionValue("#{bindings.Porcentaje2.inputValue}",porcentaje);
            }
        // Abrimos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupActualizarParticipanteTramo().show(popupHints);
            
        }
    }
    
    public void aceptarActualizarParticipanteTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside aceptarActualizarParticipanteTramoFormalizarActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        BigDecimal monto = null;
        BigDecimal porcentaje = null;
        Long idParticipanteTramoFormalizar = null;
        Long idCliente = null;
        BigDecimal montoTotalParticipante = null;
        BigDecimal montoTramo = null;
        // Recuperar el valor del monto total de los participantes
        if(null != JSFUtils.resolveExpression("#{bindings.MontoTotal1.inputValue}")){
            montoTotalParticipante=(BigDecimal)JSFUtils.resolveExpression("#{bindings.MontoTotal1.inputValue}");
        }else{
            //Si viene null, se tomara como "0"
            montoTotalParticipante = new BigDecimal(0);
        }
        // Recuperar el valor del monto del tramo
        if(null != JSFUtils.resolveExpression("#{bindings.Monto.inputValue}")){
            montoTramo=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Monto.inputValue}");
        }
        //idParticipanteTramoFormalizar 
        if(null != JSFUtils.resolveExpression("#{bindings.Id1.inputValue}")){
            idParticipanteTramoFormalizar=(Long)JSFUtils.resolveExpression("#{bindings.Id1.inputValue}");
        }
        //idCliente
        if(null != JSFUtils.resolveExpression("#{bindings.IdCliente.inputValue}")){
            idCliente=(Long)JSFUtils.resolveExpression("#{bindings.IdCliente.inputValue}");
        }
        //monto
        if(null != JSFUtils.resolveExpression("#{bindings.Monto2.inputValue}")){
            monto=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Monto2.inputValue}");
        }
        //porcentaje
        if(null != JSFUtils.resolveExpression("#{bindings.Porcentaje2.inputValue}")){
            porcentaje=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Porcentaje2.inputValue}");
        }
        
        operationBinding = bindings.getOperationBinding("actualizarParticipanteTramoFormalizar");
        //Se envian los parametros del metodo crearTramoFormalizar
        operationBinding.getParamsMap().put("idCliente", idCliente);
        operationBinding.getParamsMap().put("idParticipanteTramoFormalizar", idParticipanteTramoFormalizar);
        operationBinding.getParamsMap().put("monto", monto);
        operationBinding.getParamsMap().put("porcentaje", porcentaje);
        // Sumar el monto del nuevo participante y sumarlo al montoTotal de vista
        Double montoTotalComparar=montoTotalParticipante.doubleValue() + monto.doubleValue();
        // Validar que el montoTotal no valla a superar al monto del tramo, antes de insertar el nuevo Row
        if(montoTotalComparar.doubleValue()<=montoTramo.doubleValue()){
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }else{
                // Muestra mensaje de exito
                JSFUtils.addFacesInformationMessage("Se actualizo con éxito.");
                // Cierra el PopupActualizarParticipanteTramo
                getPopupActualizarParticipanteTramo().hide();
            }
        }else{
            //Muestra mensaje
            JSFUtils.addFacesInformationMessage("La suma de los participantes no puede ser mayor al monto del tramo.");
            // Cierra el PopupActualizarParticipanteTramo
            getPopupActualizarParticipanteTramo().hide();
        }
    }
    
    public void cancelarActualizarParticipanteTramoFormalizarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarActualizarParticipanteTramoFormalizarActionListener");
        // Cierra el actualizarParticipanteTramo
        getPopupActualizarParticipanteTramo().hide();
    }
    
    public void montoTramoValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "Inside montoTramoValueChangeListener");
        //Crear instancia de AdministradorEstructuracionPrestamoBean
        AdministradorEstructuracionPrestamoBean administradorEstructuracionPrestamoBean = 
            (AdministradorEstructuracionPrestamoBean)
                JSFUtils.resolveExpression("#{pageFlowScope.AdministradorEstructuracionPrestamoMB}");
        Double montoSindicado = null;
        BigDecimal montoTramo = null;
        //actualiza el valor seleccionado
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        
        if(null == administradorEstructuracionPrestamoBean.getMontoTotalSindicado()){
            //Cierra popUpAgregarTramo
            popupAgregarTramo.hide();
            //Muestra mensaje, se necesita el Monto sindicado.
            JSFUtils.addFacesInformationMessage("Se necesita ingresar Monto sindicado.");
        }else{
            montoSindicado = (Double)administradorEstructuracionPrestamoBean.getMontoTotalSindicado();
            montoTramo = new BigDecimal(valueChangeEvent.getNewValue().toString());
            //Monto Sindicado ----  100%
            //Monto tramo     ----  x
            BigDecimal porcentajeTramo = null;
            porcentajeTramo= new  BigDecimal ((montoTramo.longValue()*100)/montoSindicado);
            porcentajeTramo=porcentajeTramo.setScale(2, RoundingMode.CEILING);
            JSFUtils.setExpressionValue("#{bindings.Porcentaje1.inputValue}",porcentajeTramo);
        }
        
    }
    
    public void montoParticipanteValueChangeListener(ValueChangeEvent valueChangeEvent){
        logger.log(ADFLogger.TRACE, "Inside montoParticipanteValueChangeListener");
        //Crear instancia de AdministradorEstructuracionPrestamoBean
        AdministradorEstructuracionPrestamoBean administradorEstructuracionPrestamoBean = 
            (AdministradorEstructuracionPrestamoBean)
                JSFUtils.resolveExpression("#{pageFlowScope.AdministradorEstructuracionPrestamoMB}");
        Double montoSindicado = null;
        BigDecimal montoTramo = null;
        BigDecimal montoParticipante = null;
        //actualiza el valor seleccionado
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        
        if(null == administradorEstructuracionPrestamoBean.getMontoTotalSindicado()){
            //Cierra popUpAgregarTramo
            popupAgregarTramo.hide();
            //Muestra mensaje, se necesita el Monto sindicado.
            JSFUtils.addFacesInformationMessage("Se necesita ingresar Monto sindicado.");
        }else{
            //recuperar el monto total sindicado
            montoSindicado = (Double)administradorEstructuracionPrestamoBean.getMontoTotalSindicado();
            //recuperar el monto del tramo asociado al participante
            montoTramo=(BigDecimal)JSFUtils.resolveExpression("#{bindings.Monto.inputValue}");
            //recuperar el monto del participante
            montoParticipante = new BigDecimal(valueChangeEvent.getNewValue().toString());
            BigDecimal porcentajeParticipante= null;
            porcentajeParticipante= new  BigDecimal ((montoParticipante.doubleValue()*100)/montoTramo.doubleValue());
            porcentajeParticipante = porcentajeParticipante.setScale(2, RoundingMode.CEILING);
            JSFUtils.setExpressionValue("#{bindings.Porcentaje2.inputValue}",porcentajeParticipante);
        }
        
    }
    
    public void setPopupAgregarTramo(RichPopup popupAgregarTramo) {
        this.popupAgregarTramo = popupAgregarTramo;
    }

    public RichPopup getPopupAgregarTramo() {
        return popupAgregarTramo;
    }
    
    public void setPopupAgregarParticipanteTramo(RichPopup popupAgregarParticipanteTramo) {
        this.popupAgregarParticipanteTramo = popupAgregarParticipanteTramo;
    }

    public RichPopup getPopupAgregarParticipanteTramo() {
        return popupAgregarParticipanteTramo;
    }
    
    public void setPopupEliminarParticipanteTramo(RichPopup popupEliminarParticipanteTramo) {
        this.popupEliminarParticipanteTramo = popupEliminarParticipanteTramo;
    }

    public RichPopup getPopupEliminarParticipanteTramo() {
        return popupEliminarParticipanteTramo;
    }
    
    public void setPopupActualizarParticipanteTramo(RichPopup popupActualizarParticipanteTramo) {
        this.popupActualizarParticipanteTramo = popupActualizarParticipanteTramo;
    }

    public RichPopup getPopupActualizarParticipanteTramo() {
        return popupActualizarParticipanteTramo;
    }
    
    public void setPopupEliminarTramo(RichPopup popupEliminarTramo) {
        this.popupEliminarTramo = popupEliminarTramo;
    }

    public RichPopup getPopupEliminarTramo() {
        return popupEliminarTramo;
    }
    
    public void setPopupActualizarTramo(RichPopup popupActualizarTramo) {
        this.popupActualizarTramo = popupActualizarTramo;
    }

    public RichPopup getPopupActualizarTramo() {
        return popupActualizarTramo;
    }
    
    public void setOtInitForm(RichOutputText otInitForm) {
        this.otInitForm = otInitForm;
    }

    public RichOutputText getOtInitForm() {
        // Inicializamos ParticipanteTramoFormalizarVO by IdTramo
        Long idTramoFormalizar = null;
        if(null != JSFUtils.resolveExpression("#{bindings.Id.inputValue}")){
            idTramoFormalizar=(Long)JSFUtils.resolveExpression("#{bindings.Id.inputValue}");
        }
        //Consultar ParticipanteTramoFormalizar por idTramoFormalizar
        consultarParticipanteTramoFormalizar(idTramoFormalizar);
        return otInitForm;
    }
    
    
}
