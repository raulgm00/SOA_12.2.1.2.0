package pa01lavadoactivosgenerichumantask.bean;

import com.bcie.xmlns.cuestionariobo.Archivo;
import com.bcie.xmlns.cuestionariomo.CrearReporteElegibilidadRequestType;
import com.bcie.xmlns.cuestionariomo.CrearReporteElegibilidadResponseType;
import com.bcie.xmlns.cuestionariomo.CrearReporteElegibilidadV2RequestType;
import com.bcie.xmlns.cuestionariomo.CrearReporteElegibilidadV2ResponseType;
import com.bcie.xmlns.cuestionarioservice.Cuestionario12BndQSService;
import com.bcie.xmlns.cuestionarioservice.CuestionarioPT;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.io.StringWriter;

import java.math.BigDecimal;

import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.BlobDomain;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

import org.bcie.declaracionjuradamo.ActualizarDeclaracionJuradaResponseType;
import org.bcie.declaracionjuradamo.ConsultarDeclaracionByIdOperacionResponseType;
import org.bcie.declaracionjuradamo.CrearDeclaracionJuradaResponseType;
import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class LavadoActivosActionsBean extends FenixActionBean implements Serializable {
    @SuppressWarnings("compatibility:5321460336750295705")
    private static final long serialVersionUID = -8950624752322893883L;


    private static ADFLogger logger = null;
    private transient RichPopup popupConfirmarFinalizarTarea;
    private transient RichPopup popupConfirmarFinalizarProceso;
    private transient RichPopup popupConfirmarEnviarAnalisisLaft;
    private String ejecutivo = null; // Nombre de ejecutivo para actualizar la DJ
    
    // Inicializacion de estado de tarea en pantallas Recopilar Información y Realizar análisis
    private transient RichOutputText otInitForm;
    
    private final String REPORTE_FILE_NAME = "ReporteElegibilidad.pdf";

    public LavadoActivosActionsBean() {
        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
        
    public void setPopupConfirmarFinalizarTarea(RichPopup popupConfirmarFinalizarTarea) {
        this.popupConfirmarFinalizarTarea = popupConfirmarFinalizarTarea;
    }

    public RichPopup getPopupConfirmarFinalizarTarea() {
        return popupConfirmarFinalizarTarea;
    }
    
    public void setPopupConfirmarFinalizarProceso(RichPopup popupConfirmarFinalizarProceso) {
        this.popupConfirmarFinalizarProceso = popupConfirmarFinalizarProceso;
    }

    public RichPopup getPopupConfirmarFinalizarProceso() {
        return popupConfirmarFinalizarProceso;
    }
    
    public void setPopupConfirmarEnviarAnalisisLaft(RichPopup popupConfirmarEnviarAnalisisLaft) {
        this.popupConfirmarEnviarAnalisisLaft = popupConfirmarEnviarAnalisisLaft;
    }

    public RichPopup getPopupConfirmarEnviarAnalisisLaft() {
        return popupConfirmarEnviarAnalisisLaft;
    }
    
    /*** START Pantalla Realizar análisis LAFT fondos externos ***/
    public void finalizarTareaActionListener(ActionEvent actionEvent) {
        // Abre popup de confirmación en pantalla Realizar analisis LAFT fondos externos
        logger.log(ADFLogger.TRACE, "Inside finalizarTarea: " + actionEvent.getComponent().getId());
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarFinalizarTarea().show(popupHints);
    }
    
    public void cancelarFinalizarTareaActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarFinalizarTareaActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup de confirmar en pantalla Realizar analisis LAFT fondos externos
        getPopupConfirmarFinalizarTarea().hide();
    }
    
    public String aceptarFinalizarTareaAction() {
        logger.log(ADFLogger.TRACE, "Inside aceptarFinalizarTareaAction.");
        LavadoActivosBean lavadoActivosBean = (LavadoActivosBean)JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        InvokeActionBean invokeActionBean = null;
        
        // Cerramos popup de confirmar en pantalla Realizar analisis LAFT fondos externos
        getPopupConfirmarFinalizarTarea().hide();
        
        // Invocamos acción correspondiente
        if(crearDeclaracionJurada(Long.valueOf(lavadoActivosBean.getIdOperacion()), 
                                  Boolean.FALSE, FenixConstants.TIPO_ORIGEN_NUEVA, 
                                  lavadoActivosBean.getInstanciaProceso(), 
                                  lavadoActivosBean.getIdTareaBpm())){
            // Invocar método para cargar documentos en onBase
            cargarDocumentosOnBase();
            
            invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        }
        else
            return null;
    }
    /*** END Pantalla Realizar análisis LAFT fondos externos ***/
    
    
    /*** START Pantalla Recopilar Informacion OFIC ***/
    public void enviarAnalisisLaftActionListener(ActionEvent actionEvent) {
        // Abre popup de confirmación en pantalla Recopilar información OFIC
        logger.warning("Inside enviarAnalisisLaftActionListener: " + actionEvent.getComponent().getId());
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarEnviarAnalisisLaft().show(popupHints);
    }
    
    public void cancelarEnviarAnalisisLaftActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarEnviarAnalisisLaftActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup de confirmar en pantalla Recopilar información OFIC
        getPopupConfirmarEnviarAnalisisLaft().hide();
    }
    
    public void actualizarTipoSolicitudValueChangeListener(ValueChangeEvent valueChangeEvent) {
        // Radios Tipo de Solicitud en pantalla Recopilar información OFIC (panel cuando es por Seguimiento y 
        // panel cuando el flag requiereAsociacion/requiereExcepcion del Payload viene prendido.
        logger.log(ADFLogger.TRACE, "Inside actualizarTipoSolicitudValueChangeListener.");
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // update model values
        guardarEstadoTarea();
    }
    
    public void crearNuevaDeclaracionJuradaActionListener(ActionEvent actionEvent) {
        logger.warning("Inside crearNuevaDeclaracionJuradaActionListener: " + actionEvent.getComponent().getId());
        LavadoActivosBean lavadoActivosBean = (LavadoActivosBean)JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        
        // Invocamos crear Declaración Jurada desde pantalla Recopilar Informacion OFIC Sin DJ o Por Seguimiento
        if(crearDeclaracionJurada(Long.valueOf(lavadoActivosBean.getIdOperacion()), Boolean.TRUE, FenixConstants.TIPO_ORIGEN_NUEVA,
                                  lavadoActivosBean.getInstanciaProceso(), lavadoActivosBean.getIdTareaBpm())) {
            
            // Invocamos servicio de ConsultarDeclaracionJurada para mapear los datos recién insertados
            if(consultarDeclaracionJurada(Long.valueOf(lavadoActivosBean.getIdOperacion()))) {
                
                // Cuando es por Seguimiento, incidencia FNXII-2868: 
                // Se cambian en BD los tipos de documento de "Declaración Jurada" a "Histórico Declaración Jurada"
                if(lavadoActivosBean.getEsPorSeguimiento()) {
                    
                    actualizarTiposDocumentoPorOpTareaTipo(Long.valueOf(lavadoActivosBean.getIdOperacion()), 
                                                           Integer.valueOf(lavadoActivosBean.getIdTareaBpm()),
                                                           FenixConstants.ID_TIPO_DOCUMENTO_DECLARACION_JURADA,
                                                           FenixConstants.ID_TIPO_DOCUMENTO_HISTORICO_DECLARACION_JURADA);
                    
                    // Se actualiza el componente (árbol) de documentos
                    ADFContext.getCurrent().getSessionScope().put("refreshGestorDoc", true);
                }
                    
                // La pantalla se transforma ahora en Recopilar Informacion OFIC Con DJ, 
                // con el campo de Fecha Firma Declaración Jurada editable
                lavadoActivosBean.setIdDeclaracion((JSFUtils.resolveExpression("#{bindings.IdDeclaracion.inputValue}") == null) ? null : 
                                                   JSFUtils.resolveExpression("#{bindings.IdDeclaracion.inputValue}").toString()); 
                lavadoActivosBean.setEsConDjOfic(Boolean.TRUE);
                lavadoActivosBean.setEsSinDjOfic(Boolean.FALSE);
                lavadoActivosBean.setEsPanelOficSinDjVisible(Boolean.FALSE);
                
                // y con el botón de "Crear nueva declaración jurada" deshabilitado (y el de "Duplicar declaración jurada" oculto)
                lavadoActivosBean.setEsDjCreada(Boolean.TRUE);
                
                // Abrimos popup con aplicación externa de LAA
                abrirAplicacionExternaLaa();
                
                // Guardamos estado de la tarea (para recordar la selección por si cierra la tarea sin finalizar) 
                guardarEstadoTarea();
            }
        }
        
        logger.warning("Valores al finalizar la tarea.");
        lavadoActivosBean.impresionLogsDeclaracion();
    }
    
    public void duplicarDeclaracionJuradaActionListener(ActionEvent actionEvent) {
        logger.warning("Inside duplicarDeclaracionJuradaActionListener: " + actionEvent.getComponent().getId());
        LavadoActivosBean lavadoActivosBean = (LavadoActivosBean)JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        DCIteratorBinding voDeclaracionJuradaLaft = null;
        oracle.jbo.domain.Date fechaFirma = null;
        
        // Asignación de variables
        voDeclaracionJuradaLaft = ADFUtils.getDCBindingContainer().findIteratorBinding("DeclaracionJuradaLaftVOIterator");
        fechaFirma = (oracle.jbo.domain.Date) voDeclaracionJuradaLaft.getRowAtRangeIndex(0).getAttribute("FechaFirmaDeclaracion");
        
        // Hacemos null la Fecha firma antes de crear la DJ
        voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("FechaFirmaDeclaracion", null);
        
        // Asignamos el atributo TipoOrigen
        voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("TipoOrigen", FenixConstants.TIPO_ORIGEN_DUPLICADA);
        
        // Invocamos duplicar Declaración Jurada desde pantalla Recopilar Informacion OFIC  
        // -flujo de Seguimiento por Actualización-  
        if(duplicarDeclaracionJurada(Long.valueOf(lavadoActivosBean.getIdOperacion()))) {
            
            // Invocamos servicio de ConsultarDeclaracionJurada para mapear los datos recién insertados
            if(consultarDeclaracionJurada(Long.valueOf(lavadoActivosBean.getIdOperacion()))) {
                
                // Cuando es por Seguimiento, incidencia FNXII-2868: 
                // Se cambian en BD los tipos de documento de "Declaración Jurada" a "Histórico Declaración Jurada"
                if(lavadoActivosBean.getEsPorSeguimiento()) {
                    
                    actualizarTiposDocumentoPorOpTareaTipo(Long.valueOf(lavadoActivosBean.getIdOperacion()), 
                                                           Integer.valueOf(lavadoActivosBean.getIdTareaBpm()),
                                                           FenixConstants.ID_TIPO_DOCUMENTO_DECLARACION_JURADA,
                                                           FenixConstants.ID_TIPO_DOCUMENTO_HISTORICO_DECLARACION_JURADA);
                    
                    // Se actualiza el componente (árbol) de documentos
                    ADFContext.getCurrent().getSessionScope().put("refreshGestorDoc", true);
                }
                
                // La pantalla se transforma ahora en Recopilar Informacion OFIC Con DJ, 
                // con el campo de Fecha Firma Declaración Jurada editable
                lavadoActivosBean.setIdDeclaracion((JSFUtils.resolveExpression("#{bindings.IdDeclaracion.inputValue}") == null) ? null : 
                                                   JSFUtils.resolveExpression("#{bindings.IdDeclaracion.inputValue}").toString()); 
                lavadoActivosBean.setEsConDjOfic(Boolean.TRUE);
                
                // y sin radios de "Tipo de Solicitud" (nueva / asociación / excepción)
                lavadoActivosBean.setEsSinDjOfic(Boolean.FALSE);
                lavadoActivosBean.setEsPanelOficSinDjVisible(Boolean.FALSE);
                
                // y con el botón de "Duplicar declaración jurada" deshabilitado (y el de "Crear nueva declaración jurada" oculto)
                lavadoActivosBean.setEsDjDuplicada(Boolean.TRUE);
                    
                // Abrimos popup con aplicación externa de LAA
                abrirAplicacionExternaLaa();
                
                // Guardamos estado de la tarea (para recordar la selección por si cierra la tarea sin finalizar) 
                guardarEstadoTarea();
            }
        }
        else{
            // Si falló al crear la DJ, regresamos la fecha firma a su valor anterior. 
            // TipoOrigen se asigna a null, por si en lugar de duplicar, después se crea una nueva DJ
            voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("FechaFirmaDeclaracion", fechaFirma);
            voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("TipoOrigen", null);
        }
        
        logger.warning("Valores al finalizar la tarea.");
        lavadoActivosBean.impresionLogsDeclaracion();
    }
    
    public String aceptarEnviarAnalisisLaftAction() {
        logger.warning("Dentro de aceptarEnviarAnalisisLaftAction");
        LavadoActivosBean lavadoActivosBean = (LavadoActivosBean)JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        Boolean isValidCrearDJ = Boolean.TRUE;
        Boolean isValidFlujoSeguimiento = Boolean.TRUE;
        Boolean isValidFlujoConExcepcion = Boolean.TRUE;
        DCIteratorBinding voDeclaracionJuradaLaft = null;
        InvokeActionBean invokeActionBean = null;
        Integer estadoDeclaracion = null;
        String returnAction = null;       
        String existeRetorno = getFlagPayload("existeRetorno");
        Boolean  esExcepcionConRetorno = Boolean.FALSE;
        Long idOperacion = null;
                        
        logger.warning("existeRetorno: "+ existeRetorno);
        logger.warning("tipo de solicitud: "+ lavadoActivosBean.getTipoSolicitudSinDj());
        
        if(!existeRetorno.isEmpty() && existeRetorno.equals("true")){            
             if(lavadoActivosBean.getTipoSolicitudSinDj() != null){
              
                if(lavadoActivosBean.getTipoSolicitudSinDj().compareTo( new Integer(3)) == 0)
                     esExcepcionConRetorno = Boolean.TRUE;
                
             }
        }
        
        logger.warning("esExcepcionConRetorno: "+ esExcepcionConRetorno);
        
        // Asignación de variables
        voDeclaracionJuradaLaft = ADFUtils.getDCBindingContainer().findIteratorBinding("DeclaracionJuradaLaftVOIterator");
        estadoDeclaracion = (Integer)voDeclaracionJuradaLaft.getRowAtRangeIndex(0).getAttribute("EstadoDeclaracion");
        
        logger.warning("estadoDeclaracion : "+estadoDeclaracion);
        // Cerramos popup de confirmar en pantalla Recopilar información OFIC
        getPopupConfirmarEnviarAnalisisLaft().hide();
                
        if(validarEnviarAnalisisLaft()) { // Validación de datos obligatorios
            
            try{
                idOperacion = Long.valueOf(lavadoActivosBean.getIdOperacion());
                
                //R3_DEV_FNXII-7384  Se crea nuevamente el cuestionario de elegibilidad
                
            }catch(Exception e){
                logger.severe("Error cast idOperacion, no se genera el cuestionario de elegibilidad :"+e);
            }
            
            // Incidencia FNXII-2933: Flag de retorno se regresa como llegó (en true)
            if(lavadoActivosBean.getEsExisteRetorno())
                actualizarFlagPayload("existeRetorno", true);
            
            // Finalizar tarea: cuando es por Seguimiento o sin DJ
            logger.warning("Finalizar tarea: cuando es por Seguimiento o sin DJ");
            if(((lavadoActivosBean.getEsPorSeguimiento() != null) && (lavadoActivosBean.getEsPorSeguimiento())) 
               || ((lavadoActivosBean.getEsSinDjOfic() != null) && (lavadoActivosBean.getEsSinDjOfic()))) {
                
                // Lógica para Tipo de solicitud: por Asociación o Excepción
                if(lavadoActivosBean.getTipoSolicitudSinDj() != null) {
                                         
                    if(lavadoActivosBean.getTipoSolicitudSinDj().intValue() == 2){
                        logger.warning("Tipo de solicitud : Solicitar asociación de declaración");
                        logger.warning("Tipo de solicitud : "+lavadoActivosBean.getTipoSolicitudSinDj());
                        logger.warning("requiereAsociacion : true");
                        logger.warning("requiereExcepcion : false");
                        // Solicitar asociación de declaración
                        actualizarFlagPayload("requiereAsociacion", true); // Se actualiza el payload
                        actualizarFlagPayload("requiereExcepcion", false);
                    }
                    else if(lavadoActivosBean.getTipoSolicitudSinDj().intValue() == 3) {
                        
                        logger.warning("Tipo de solicitud : Solicitar excepción de declaración");
                        logger.warning("Tipo de solicitud : "+lavadoActivosBean.getTipoSolicitudSinDj());
                        logger.warning("requiereAsociacion : false");
                        logger.warning("requiereExcepcion : true");
                        // Solicitar excepción
                        actualizarFlagPayload("requiereAsociacion", false); // Se actualiza el payload
                        actualizarFlagPayload("requiereExcepcion", true);
                        
                     /*
                       * Este metodo actualiza el atributo de AplicaLaft a 0 en la tabla de operacion 
                       * se comento por FNXII-7048     
                       * 
                            BindingContainer bindings = ADFUtils.getBindingContainer();
                             OperationBinding operationBinding = null;
                                operationBinding = bindings.getOperationBinding("actualizarAplicaLAFT");
                                operationBinding.getParamsMap().put("idOperacion", lavadoActivosBean.getIdOperacion());
                                operationBinding.getParamsMap().put("aplicaLaft", new oracle.jbo.domain.Number(0));
                                operationBinding.execute();
                                if(!operationBinding.getErrors().isEmpty()){
                                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                                }
                      */ 
                       
                       
                    //   Creamos la DJ cuando se haya seleccionado Por Excepción. Incidencia FNXII-2933 - Flujo con excepción
                        isValidCrearDJ = crearDeclaracionJurada(Long.valueOf(lavadoActivosBean.getIdOperacion()), 
                                                                Boolean.TRUE, FenixConstants.TIPO_ORIGEN_EXCEPCION,
                                                                lavadoActivosBean.getInstanciaProceso(),
                                                                lavadoActivosBean.getIdTareaBpm());
                        logger.warning("isValidCrearDJ : "+isValidCrearDJ);
                        // Invocamos servicio de ConsultarDeclaracionJurada para mapear los datos de la nueva DJ
                        if(isValidCrearDJ && consultarDeclaracionJurada(Long.valueOf(lavadoActivosBean.getIdOperacion()))) {
                            logger.warning("isValidCrearDJ es true y consultarDeclaracionJurada regreso true");
                            logger.warning("esExisteRetorno :"+lavadoActivosBean.getEsExisteRetorno());
                            // Inicializar el estado de la DJ. 
                            if(lavadoActivosBean.getEsExisteRetorno()){
                                logger.warning("esExisteRetorno es true, EstadoDeclaracion se inicializa a Revisada : 7");
                                // Se inicializa a "Revisada" (7). Incidencia FNXII-2933 - Flujo con excepción y Retorno.
                                voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("EstadoDeclaracion", 7);
                            }else{
                                logger.warning("esExisteRetorno es false, EstadoDeclaracion se inicializa a Revisada para Excepción : 18");
                                // Se inicializa a "Revisada para Excepción" (18). Incidencia FNXII-3256 - Flujo con excepción, sin Retorno.
                                voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("EstadoDeclaracion", 18);
                            }
                            
                            logger.warning("Se invoca servicio para actualizar declaracion jurada");
                            // Invocamos servicio para actualizar el estado de la DJ.
                            // En caso de que se haya inicializado a "Revisada", el servicio valida que se haya resuelto el cuestionario para la declaración.
                            isValidFlujoConExcepcion = 
                                actualizarDeclaracionJurada(Long.valueOf(lavadoActivosBean.getIdOperacion()), this.getEjecutivo());
                            logger.warning("isValidFlujoConExcepcion :"+isValidFlujoConExcepcion);
                        }
                    }
                }
                
                // Lógica para flujo Por Seguimiento
                if(lavadoActivosBean.getTipoSeguimiento() != null) {
                    logger.warning("tipoSeguimiento es diferente de nulo");
                    // Flag porSeguimiento se regresa como llegó (en true)
                    actualizarFlagPayload("porSeguimiento", true);
                    logger.warning("porSeguimiento : true");
                    // Por Actualización
                    if(lavadoActivosBean.getTipoSeguimiento().intValue() == 1) { 
                        logger.warning("tipoSeguimiento por Actualizacion");
                        logger.warning("tipoSeguimiento :"+lavadoActivosBean.getTipoSeguimiento());
                        logger.warning("porVencimiento : true");
                        logger.warning("porNoticia : false");
                        actualizarFlagPayload("porVencimiento", true); // Se actualiza el payload
                        actualizarFlagPayload("porNoticia", false);
                        
                        if(lavadoActivosBean.getTipoSolicitudSinDj().intValue() == 1) {
                            logger.warning("Tipo de solicitud : Nueva declaración jurada");
                            logger.warning("Tipo de solicitud : "+lavadoActivosBean.getTipoSolicitudSinDj());
                            // Cuando el [Tipo de Seguimiento] es "por Actualización" y [Tipo de Solicitud] es "Nueva declaración jurada"
                            // Se inicializa el estado de la DJ a 7 = Revisada
                            logger.warning("EstadoDeclaracion se inicializa a Revisada : 7");
                            voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("EstadoDeclaracion", 7);
                            
                            // Invocamos servicio para actualizar el estado de la DJ.
                            isValidFlujoSeguimiento = 
                                actualizarDeclaracionJurada(Long.valueOf(lavadoActivosBean.getIdOperacion()), this.getEjecutivo());
                            logger.warning("isValidFlujoSeguimiento :"+isValidFlujoSeguimiento);
                        }
                    }
                    else if(lavadoActivosBean.getTipoSeguimiento().intValue() == 2) { 
                        logger.warning("tipoSeguimiento por Alerta");
                        // Por Alerta
                        actualizarFlagPayload("porVencimiento", false); // Se actualiza el payload
                        actualizarFlagPayload("porNoticia", true);
                    }
                }
                                
                if(isValidCrearDJ && isValidFlujoSeguimiento && isValidFlujoConExcepcion) {
                    logger.warning("isValidCrearDJ :"+isValidCrearDJ);
                    logger.warning("isValidFlujoSeguimiento :"+isValidFlujoSeguimiento);
                    logger.warning("isValidFlujoConExcepcion :"+isValidFlujoConExcepcion);
                    // Invocar método para cargar documentos en onBase
                    cargarDocumentosOnBase();
                    
                    //actualizarReporteElegibilidad(idOperacion);
                    
                    // Finalizar tarea
                    invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                    returnAction = invokeActionBean.invokeOperation();
                }
            }else{
                logger.warning("Finalizar tarea cuando no es por seguimiento, existe una DJ o existe DJ con asiciacion/excepcion");
                // Finalizar tarea: cuando NO es por seguimiento o tiene una DJ o tiene una DJ con asociación/excepción
                logger.warning("Finalizar tarea: cuando NO es por seguimiento o tiene una DJ o tiene una DJ con asociación/excepción");
                if(lavadoActivosBean.getEsConDjAsociacionOfic() 
                   || lavadoActivosBean.getEsConDjExcepcionOfic()) {
                    
                    // Se actualiza el payload
                    actualizarFlagPayload("requiereAsociacion", 
                                          (lavadoActivosBean.getTipoSolicitudAsoc().intValue() == 1) ? true : false);
                    actualizarFlagPayload("requiereExcepcion", 
                                          (lavadoActivosBean.getTipoSolicitudAsoc().intValue() == 2) ? true : false);
                }
                
                
                if(esExcepcionConRetorno){//si es retorno con excepcion entonces EstadoDeclaracion = Revisada para Excepcion
                    voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("EstadoDeclaracion", 18);
                }else{
                    // Incidencia FNXII-3282: Inicializar el estado de la DJ a 7 = Revisada, para el caso de OFIC y que tenga una DJ
                    voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("EstadoDeclaracion", 7);    
                }
                                
                logger.warning("nuevo estado de  la Declaracion : "+estadoDeclaracion);
                
                // Invocamos servicio para actualizar el estado de la DJ.
                if(actualizarDeclaracionJurada(Long.valueOf(lavadoActivosBean.getIdOperacion()), this.getEjecutivo())) { 
                    
                    logger.warning("Dentro de opcion cuando se actualiza la declaracion jurada");
                    // Invocar método para cargar documentos en onBase
                    cargarDocumentosOnBase();
                    
                    //actualizarReporteElegibilidad(idOperacion);
                    
                    invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                    returnAction = invokeActionBean.invokeOperation();
                }
                else{
                    logger.warning("Si falla el servicio regresar el estadoDeclaracion a :"+estadoDeclaracion);
                    // Si falló el servicio regresamos el estado de la DJ a su valor anterior
                    voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("EstadoDeclaracion", estadoDeclaracion);
                }
            }
        }
        logger.warning("Fuera de aceptarEnviarAnalisisLaftAction");
        return returnAction;
    }
    
    private Boolean validarEnviarAnalisisLaft() {
        logger.warning("Dentro de validarEnviarAnalisisLaft");
        Boolean esDatosCorrectos = Boolean.TRUE;
        LavadoActivosBean lavadoActivosBean = 
            (LavadoActivosBean)JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        
        if(!lavadoActivosBean.getEsSinDjOfic() 
           && ((lavadoActivosBean.getTipoSolicitudSinDj() == null) || (lavadoActivosBean.getTipoSolicitudSinDj().intValue() != 2))// Incidencia FNXII-3324: Seguimiento por Actualización - Asociación, NO requiere documento 
           && ((lavadoActivosBean.getTipoSeguimiento() == null) || (lavadoActivosBean.getTipoSeguimiento().intValue() != 2))// Incidencia FNXII-3324: Seguimiento por Alerta, NO requiere documento 
           && !(lavadoActivosBean.getEsConDjExcepcionOfic() && !lavadoActivosBean.getEsNuevaOfic()) // por excepción NO requiere documento (excepto cuando se comporta como nueva DJ)
           && ((lavadoActivosBean.getTipoSolicitudSinDj() == null) || (lavadoActivosBean.getTipoSolicitudSinDj().intValue() != 3)) // por excepción (de Seguimiento) NO requiere documento
           && !validateDocumento(Long.valueOf(lavadoActivosBean.getIdOperacion()), FenixConstants.ID_TIPO_DOCUMENTO_DECLARACION_JURADA)) {
            
            // RN: se debe de tener un documento del tipo DJ
            esDatosCorrectos = Boolean.FALSE;
            logger.warning("Debe insertar al menos un Documento del tipo Declaración Jurada.");
            JSFUtils.addFacesErrorMessage("Debe insertar al menos un Documento del tipo Declaración Jurada.");
        }
        
        if((lavadoActivosBean.getEsPorSeguimiento() != null) && (lavadoActivosBean.getEsPorSeguimiento())) {
            
            // Validación de datos obligatorios - Flujo de Seguimiento
            if((lavadoActivosBean.getTipoSeguimiento() == null) || (lavadoActivosBean.getTipoSeguimiento().intValue() == 0)) {
            
                esDatosCorrectos = Boolean.FALSE;
                logger.warning("Se tiene que realizar una selección en Tipo de Seguimiento.");
                JSFUtils.addFacesErrorMessage("Se tiene que realizar una selección en Tipo de Seguimiento.");
            }
            
            // Tipo de Seguimiento por Actualización(1)  
            if((lavadoActivosBean.getTipoSeguimiento() != null) && (lavadoActivosBean.getTipoSeguimiento().intValue() == 1)) {
                              
                if((lavadoActivosBean.getTipoSolicitudSinDj() == null) 
                   || (lavadoActivosBean.getTipoSolicitudSinDj().intValue() == 0)) {
                    
                    esDatosCorrectos = Boolean.FALSE;
                    logger.warning("Se tiene que realizar una selección en Tipo de Solicitud.");
                    JSFUtils.addFacesErrorMessage("Se tiene que realizar una selección en Tipo de Solicitud.");      
                }
                else if(((lavadoActivosBean.getTipoSolicitudSinDj().intValue() == 2) 
                         || (lavadoActivosBean.getTipoSolicitudSinDj().intValue() == 3)) 
                        && (!validateComentario(Long.valueOf(lavadoActivosBean.getIdOperacion()), 
                                                lavadoActivosBean.getIdTareaBpm(), getInstanciaTarea()))) {
                    
                    // Tipo de Solicitud por Asociación(2) o Excepción(3), requiere comentario obligatorio
                    esDatosCorrectos = Boolean.FALSE;
                    logger.warning("Se tiene que registrar al menos un comentario para poder avanzar.");
                    JSFUtils.addFacesErrorMessage("Se tiene que registrar al menos un comentario para poder avanzar.");
                }
                else if((lavadoActivosBean.getTipoSolicitudSinDj().intValue() == 1) && !lavadoActivosBean.getEsDjCreada() 
                        && !lavadoActivosBean.getEsDjDuplicada()) {
                    
                    // Tipo de Solicitud "Nueva declaración jurada", requiere que se haya creado o duplicado una DJ 
                    // (la cual puede ser diferente a la mostrada en pantalla, puesto que es otra InstanciaProceso)
                    esDatosCorrectos = Boolean.FALSE;
                    logger.warning("Se debe crear o duplicar una Declaración Jurada para poder avanzar.");
                    JSFUtils.addFacesErrorMessage("Se debe crear o duplicar una Declaración Jurada para poder avanzar.");
                }
            }
            
            // Tipo de Seguimiento por Alerta(2)
            if((lavadoActivosBean.getTipoSeguimiento() != null) && (lavadoActivosBean.getTipoSeguimiento().intValue() == 2)) { 
                
                if(!validateComentario(Long.valueOf(lavadoActivosBean.getIdOperacion()), 
                                       lavadoActivosBean.getIdTareaBpm(), getInstanciaTarea())) {
                
                    // Requiere comentario obligatorio
                    esDatosCorrectos = Boolean.FALSE;
                    logger.warning("Se tiene que registrar al menos un comentario para poder avanzar.");
                    JSFUtils.addFacesErrorMessage("Se tiene que registrar al menos un comentario para poder avanzar.");
                }
            }
        }
        else if((lavadoActivosBean.getEsSinDjOfic() != null) && (lavadoActivosBean.getEsSinDjOfic())) {
            
            // Validación de datos obligatorios - Flujo normal sin DJ
            Integer tipoSolicitudSinDj = lavadoActivosBean.getTipoSolicitudSinDj();
            if((tipoSolicitudSinDj == null) || (tipoSolicitudSinDj.intValue() == 0)) {
                
                esDatosCorrectos = Boolean.FALSE;
                logger.warning("Se tiene que realizar una selección en Tipo de Solicitud.");
                JSFUtils.addFacesErrorMessage("Se tiene que realizar una selección en Tipo de Solicitud.");
            }
            else if(tipoSolicitudSinDj.intValue()==1 && !lavadoActivosBean.getEsDjCreada()) {
                    
                // Incidencia FNXII-3351: la opción "Nueva declaración jurada" requiere crear una nueva DJ.
                esDatosCorrectos = Boolean.FALSE;
                logger.warning("Se debe crear una Declaración Jurada para poder avanzar.");
                JSFUtils.addFacesErrorMessage("Se debe crear una Declaración Jurada para poder avanzar.");
            }
            else if((tipoSolicitudSinDj.intValue()==2 || tipoSolicitudSinDj.intValue()==3) 
                    && (!validateComentario(Long.valueOf(lavadoActivosBean.getIdOperacion()), 
                                            lavadoActivosBean.getIdTareaBpm(), getInstanciaTarea()))) {
                    
                // Si es Recopilar Informacion OFIC Sin DJ y es Solicitar asociación de declaración o Solicitar excepción, 
                // se debe de agregar al menos un comentario para ambos casos
                esDatosCorrectos = Boolean.FALSE;
                logger.warning("Se tiene que registrar al menos un comentario para poder avanzar.");
                JSFUtils.addFacesErrorMessage("Se tiene que registrar al menos un comentario para poder avanzar.");
            }
        }
        else if(lavadoActivosBean.getEsConDjExcepcionOfic() && lavadoActivosBean.getEsNuevaOfic()) {
            
            // Validación de datos obligatorios - Flujo con Excepción y Retorno
            // Incidencia FNXII-3428. Se elimina obligatoriedad de comentario para este escenario.
        }
        logger.warning("Dentro de validarEnviarAnalisisLaft,return :"+esDatosCorrectos);
        return esDatosCorrectos;
    }
    
    public String aceptarCancelarRecopilarInfoAction() {
        logger.warning("Inside aceptarCancelarRecopilarInfoAction.");
        LavadoActivosBean lavadoActivosBean = (LavadoActivosBean)JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        DCIteratorBinding voDeclaracionJuradaLaft = null;
        Boolean esEjecucionExitosa = Boolean.TRUE;
        logger.warning("esEjecucionExitosa: "+esEjecucionExitosa);
        // Asignación de variables
        voDeclaracionJuradaLaft = ADFUtils.getDCBindingContainer().findIteratorBinding("DeclaracionJuradaLaftVOIterator");
        
        // Cuando NO es Por alerta (2) se cambia el estado de la declaración a Inactiva (9).
        if((lavadoActivosBean.getTipoSeguimiento() == null) || (lavadoActivosBean.getTipoSeguimiento().intValue() != 2)) {
            logger.warning("se cambia el estado de la declaración a Inactiva (9)");
            voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("EstadoDeclaracion", 9);
            
            // Se invoca servicio para actualizar el estado de la Declaración
            esEjecucionExitosa = 
                actualizarDeclaracionJurada(Long.valueOf(lavadoActivosBean.getIdOperacion()), this.getEjecutivo());
        }
        
        if(esEjecucionExitosa) {
                        
            // Invocar método para cargar documentos en onBase
            cargarDocumentosOnBase();
            
            // Eliminamos de BD los registros guardados con el estado, para las tareas Recopilar Información (tarea actual) 
            // y Realizar Análisis (por si es un Retorno)
            eliminarEstadoTarea(Long.valueOf(lavadoActivosBean.getIdOperacion()), lavadoActivosBean.getInstanciaProceso(),
                                Integer.valueOf(lavadoActivosBean.getIdTareaBpm()));
            eliminarEstadoTarea(Long.valueOf(lavadoActivosBean.getIdOperacion()), lavadoActivosBean.getInstanciaProceso(),
                                Integer.valueOf(FenixConstants.TAREA_REALIZAR_ANALISIS_LAFT));
            
            // Outcome de CANCELAR
            invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            returnAction = invokeActionBean.invokeOperation();
        }
        
        logger.warning("termina metodo aceptarCancelarRecopilarInfoAction.");
        return returnAction;
    }  
    /*** END Pantalla Recopilar Informacion OFIC ***/
    
    
    /*** START Pantalla Realizar análisis LAFT ***/
    public void confirmarSolicitarMasInfoActionListener(ActionEvent actionEvent) {
        // Abre popup de confirmación en pantalla Realizar análisis LAFT
        logger.log(ADFLogger.TRACE, "Inside confirmarSolicitarMasInfoActionListener: " + actionEvent.getComponent().getId());
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarFinalizarProceso().show(popupHints);
    }
    
    public void cancelarFinalizarProcesoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarFinalizarProcesoActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup de confirmar en pantalla Realizar análisis LAFT
        getPopupConfirmarFinalizarProceso().hide();
    }
       
    public String aceptarRetornoLaftAction() {
        logger.warning("Dentro de aceptarRetornoLaftAction");
        Boolean isValidActualizarDJ = Boolean.TRUE;
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        LavadoActivosBean lavadoActivosBean = (LavadoActivosBean)JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        
        // Cerramos popup de confirmar en pantalla Realizar análisis LAFT
        getPopupConfirmarFinalizarProceso().hide();
        
        // Validación de datos obligatorios
        if(validarRetorno()) {
                            
            // Actualizamos la Declaración Jurada, si existe. Incidencia FNXII-3298, retorno de una solicitud de asociación sin DJ.
            if(lavadoActivosBean.getIdDeclaracion() != null){
                logger.warning("idDelaracion es diferente de nulo");
                isValidActualizarDJ = actualizarDeclaracionJurada(Long.valueOf(lavadoActivosBean.getIdOperacion()), this.getEjecutivo());
                logger.warning("isValidActualizarDJ :"+isValidActualizarDJ);
            }
            
            if(isValidActualizarDJ) { 
            
                // Invocar método para cargar documentos en onBase
                cargarDocumentosOnBase();
                
                // Los flags requiereAsociacion / requiereExcepcion se regresan como llegaron
                actualizarFlagPayload("requiereAsociacion", lavadoActivosBean.getEsAsociacionLaft()); // Se actualiza el payload
                actualizarFlagPayload("requiereExcepcion", lavadoActivosBean.getEsExcepcionLaft());
                
                // Incidencia FNXII-2933: Prendemos flag de retorno
                actualizarFlagPayload("existeRetorno", true);
                logger.warning("set value existeRetorno a true");
                // Se actualizan los flags porVencimiento / porNoticia
                if(lavadoActivosBean.getTipoSeguimiento() != null) {
                    logger.warning("tipoSeguimiento es diferente de nulo");
                    // Flag porSeguimiento se regresa como llegó (en true)
                    actualizarFlagPayload("porSeguimiento", true);
                    logger.warning("set true a porSeguimiento");
                    if(lavadoActivosBean.getTipoSeguimiento().intValue() == 1) {
                        
                        actualizarFlagPayload("porVencimiento", true); // Se actualiza el payload
                        actualizarFlagPayload("porNoticia", false);
                    }
                    else if(lavadoActivosBean.getTipoSeguimiento().intValue() == 2) {
                        
                        actualizarFlagPayload("porVencimiento", false); // Se actualiza el payload
                        actualizarFlagPayload("porNoticia", true);
                    }
                }
                            
                // Finalizar tarea
                invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                returnAction = invokeActionBean.invokeOperation();
            }
        }
        logger.warning("Fuera de aceptarRetornoLaftAction");
        return returnAction;
    }
    
    private Boolean validarRetorno() {
        logger.warning("Dentro de validarRetorno");
        Boolean esDatosCorrectos = Boolean.TRUE;
        DCIteratorBinding voDeclaracionJuradaLaft = null;
        Integer estadoDeclaracion = null;
        LavadoActivosBean lavadoActivosBean = (LavadoActivosBean)JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        
        // Asignación de variables
        voDeclaracionJuradaLaft = ADFUtils.getDCBindingContainer().findIteratorBinding("DeclaracionJuradaLaftVOIterator");
        estadoDeclaracion = (voDeclaracionJuradaLaft.getRowAtRangeIndex(0).getAttribute("EstadoDeclaracion") == null) ? 0 : 
            (Integer) voDeclaracionJuradaLaft.getRowAtRangeIndex(0).getAttribute("EstadoDeclaracion");
        
        // RN: El retorno siempre requiere un comentario
        if(!validateComentario(Long.valueOf(lavadoActivosBean.getIdOperacion()), 
                                            lavadoActivosBean.getIdTareaBpm(), getInstanciaTarea())) {
            
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("Se tiene que registrar al menos un comentario para poder avanzar.");
        }
        
        // Incidencia FNXII-3427. Los estados válidos para retorno son: 
        // Retorno por Hallazgos (14), Retorno por Errores (13) y En análisis por alerta (16)
        
       
        if(lavadoActivosBean.getEsNuevaLaft() || lavadoActivosBean.getEsActualizacionLaft() 
           || lavadoActivosBean.getEsExcepcionLaft()) { // NO se incluye getEsAsociacionLaft() puesto que si tiene un estado entonces tiene un DJ
        
            if((estadoDeclaracion.intValue() != 13) && (estadoDeclaracion.intValue() != 14) && (estadoDeclaracion.intValue() != 16)) {
               
               esDatosCorrectos = Boolean.FALSE;
               JSFUtils.addFacesErrorMessage("El estado de la de Declaración jurada debe ser: Retorno por Errores, " +
                   "Retorno por Hallazgos o En análisis por alerta.");
            }
        }
                
        // Si ya se realizó la asociación, NO se puede retornar. Si se muestra la Declaración significa que ya se realizó la asociación
        if(lavadoActivosBean.getEsAsociacionLaft() && (lavadoActivosBean.getIdDeclaracion() != null)) {
            
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("La tarea no se puede retornar debido a que ya existe una Declaración jurada.");
        }
        
        logger.warning("Fuera de validarRetorno,return : "+esDatosCorrectos);
        
        return esDatosCorrectos;
    }
            
    public String aceptarFinalizarTareaLaftAction() {
        logger.warning("Dentro de aceptarFinalizarTareaLaftAction");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        Long idOperacion = null;
        LavadoActivosBean lavadoActivosBean = (LavadoActivosBean)JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        logger.warning("esRequiereExcepcion :"+lavadoActivosBean.getEsRequiereExcepcion());
        // RN: Validación de datos obligatorios excepto si se requiere excepcion
      
        try{
            idOperacion = Long.valueOf(lavadoActivosBean.getIdOperacion());

        }catch(Exception e){
            logger.severe("Error cast idOperacion, no se genera el cuestionario de elegibilidad :"+e);
        }
      
        if(validarFinalizarTareaLaft()) {
            // Invocar método para cargar documentos en onBase
            cargarDocumentosOnBase();
            
            // Los flags requiereAsociacion / requiereExcepcion se regresan como llegaron
            actualizarFlagPayload("requiereAsociacion", lavadoActivosBean.getEsAsociacionLaft()); // Se actualiza el payload
            actualizarFlagPayload("requiereExcepcion", lavadoActivosBean.getEsExcepcionLaft());
            
            // Se actualizan los flags porVencimiento / porNoticia
            if(lavadoActivosBean.getTipoSeguimiento() != null) {
                
                if(lavadoActivosBean.getTipoSeguimiento().intValue() == 1) {
                    
                    actualizarFlagPayload("porVencimiento", true); // Se actualiza el payload
                    actualizarFlagPayload("porNoticia", false);
                }
                else if(lavadoActivosBean.getTipoSeguimiento().intValue() == 2) {
                    
                    actualizarFlagPayload("porVencimiento", false); // Se actualiza el payload
                    actualizarFlagPayload("porNoticia", true);
                }
            }
            
            // Eliminamos de BD los registros guardados con el estado, para las tareas Realizar Análisis (tarea actual)
            // y Recopilar Información (tarea de la que viene) 
            eliminarEstadoTarea(Long.valueOf(lavadoActivosBean.getIdOperacion()), lavadoActivosBean.getInstanciaProceso(),
                                Integer.valueOf(lavadoActivosBean.getIdTareaBpm()));
            eliminarEstadoTarea(Long.valueOf(lavadoActivosBean.getIdOperacion()), lavadoActivosBean.getInstanciaProceso(),
                                Integer.valueOf(FenixConstants.TAREA_RECOPILAR_INFO_OFIC));
            
            if (idOperacion != null) {
                
                actualizarDeclaracionJurada(Long.valueOf(lavadoActivosBean.getIdOperacion()), this.getEjecutivo());
                
                actualizarReporteElegibilidad(idOperacion);    
            } else {
                logger.warning("No es posible actualizar reporte de elegibilidad, idOperacion is null.");
            }
            
            // Finalizar tarea en pantalla Realizar análisis LAFT
            invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            returnAction = invokeActionBean.invokeOperation();
        }
        
        // Cerramos popup de confirmar en pantalla Realizar análisis LAFT
        getPopupConfirmarFinalizarProceso().hide();
        logger.warning("Fuera de aceptarFinalizarTareaLaftAction");
        return returnAction;
    }
    
    private Boolean validarFinalizarTareaLaft() {
        logger.warning("Dentro de validarFinalizarTareaLaft");
        Boolean esDatosCorrectos = Boolean.TRUE;
        DCIteratorBinding voDeclaracionJuradaLaft = null;
        Integer estadoDeclaracion = null;
        BigDecimal estadoBusqueda = null;
        LavadoActivosBean lavadoActivosBean = null;
        
        // Asignación de variables
        lavadoActivosBean = (LavadoActivosBean)JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        voDeclaracionJuradaLaft = ADFUtils.getDCBindingContainer().findIteratorBinding("DeclaracionJuradaLaftVOIterator");
        estadoDeclaracion = (Integer) voDeclaracionJuradaLaft.getRowAtRangeIndex(0).getAttribute("EstadoDeclaracion");
        estadoBusqueda = (BigDecimal) voDeclaracionJuradaLaft.getRowAtRangeIndex(0).getAttribute("EstadoBusqueda");
        logger.warning("estadoDeclaracion :"+estadoDeclaracion);
        logger.warning("estadoBusqueda :"+estadoBusqueda);
        // RN: Al finalizar la tarea se debe validar que 
        // 1) Se cuente con una DJ 
        if(voDeclaracionJuradaLaft.getRowAtRangeIndex(0).getAttribute("IdDeclaracion") == null) {
            logger.warning("No cuenta con una declaracion jurada");
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("Se debe de contar con una Declaración Jurada.");
        }
        
        logger.warning("esExcepcionLaft :"+lavadoActivosBean.getEsExcepcionLaft());  
        logger.warning("esNuevaLaft :"+lavadoActivosBean.getEsNuevaLaft());
        // 2) El estado Operación Con Excepción (12), es válido sólo cuando se solicitó una excepción  
        if((lavadoActivosBean.getEsExcepcionLaft()) && !lavadoActivosBean.getEsNuevaLaft()) {
            logger.warning("Entra a la validacion cuando esExcepcionLaft es true y esNuevaLaft es false");
            if((estadoDeclaracion == null) || (estadoDeclaracion.intValue() != 12)) {
                logger.warning("Entra a la validacion cuando estadoDeclaracion es nulo o estadoDeclaracion es diferente a 12");
                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage("El estado de la Declaración jurada debe ser Operación con excepción.");
            }else{
                logger.warning("No entra a la validacion cuando estadoDeclaracion es nulo o estadoDeclaracion es diferente a 12");
            }
        }
        else if((estadoDeclaracion == null) || ((estadoDeclaracion.intValue() != 2) && (estadoDeclaracion.intValue() != 17))) {
            logger.warning("estadoDeclaracion es nulo o estadoDeclaracion es diferente a 2  y estadoDeclaracion es diferente a 17");
            // 3) El estado de la declaración jurada debe ser Invalidada por hallazgo (17) o Completa (2)
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El estado de la Declaración jurada debe ser Invalidada por hallazgo o Completa.");                
        }
        
        // 4) El estado de la búsqueda sea diferente a No realizada (0) 
        // Incidencia FNXII-3299 - para una solicitud de excepción no aplica el estado de la búsqueda.   
        if(((estadoBusqueda == null) || (estadoBusqueda.intValue() == 0)) && (!lavadoActivosBean.getEsExcepcionLaft())) {
            logger.warning("estadoBusqueda es nulo o estadoBusqueda es igual a 0 y esExcepcionLatg es false");
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El estado de la búsqueda LCP debe ser diferente a No realizada.");                 
        }
        logger.warning("Fuera de validarFinalizarTareaLaft,return :"+esDatosCorrectos);
        return esDatosCorrectos;
    }
    
    public void actualizarEstadoValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inside actualizarEstadoValueChangeListener.");
        LavadoActivosBean lavadoActivosBean = (LavadoActivosBean)JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        Boolean isValidActualizarDJ = Boolean.TRUE;
        DCIteratorBinding voDeclaracionJuradaLaft = null;
        Integer estadoDeclaracion = null;
        BigDecimal estadoBusqueda = null;
        
        // Antes de hacer update model obtenemos valores de combos
        voDeclaracionJuradaLaft = ADFUtils.getDCBindingContainer().findIteratorBinding("DeclaracionJuradaLaftVOIterator");
        estadoDeclaracion = (Integer) voDeclaracionJuradaLaft.getRowAtRangeIndex(0).getAttribute("EstadoDeclaracion");
        estadoBusqueda = (BigDecimal) voDeclaracionJuradaLaft.getRowAtRangeIndex(0).getAttribute("EstadoBusqueda");
        
        // Los estados cambian en línea, por lo tanto es necesario invocar servicio de actualizar DJ
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // update model values
        isValidActualizarDJ = actualizarDeclaracionJurada(Long.valueOf(lavadoActivosBean.getIdOperacion()), this.getEjecutivo());
        
        // Si falló al invocar el servicio regresamos los combos de estados a su valor anterior
        if(!isValidActualizarDJ) {
            
            voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("EstadoDeclaracion", estadoDeclaracion);
            voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("EstadoBusqueda", estadoBusqueda);
        }
    }
    /*** END Pantalla Realizar análisis LAFT ***/    
      
    
    /*** START acciones genéricas ***/
    public void consultarDeclaracionEnLaaActionListener(ActionEvent actionEvent) { 
        logger.log(ADFLogger.TRACE, "Inside consultarDeclaracionEnLaaActionListener: " + actionEvent.getComponent().getId());
        
        beforeAsociar(); // Incidencia FNXII-3474
        abrirAplicacionExternaLaa(); // Abrimos popup con aplicación externa de LAA     
    }
    
    private void beforeAsociar() {
        LavadoActivosBean lavadoActivosBean = (LavadoActivosBean) JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        
        // Incidencia FNXII-3474: pantalla Realizar análisis LAFT, escenario Seguimiento por Actualización(tipoSeguimiento=1), 
        // con solicitud de asociación(tipoSolicitudSinDj=2).
        // Se asigna en bean, en el atributo "IdDjAntesDeAsociar", y se guarda en BD, el Id de la Declaración Jurada actual 
        // (aunque no se muestre en pantalla, existe una). 
        // Con ello sabremos si asocia o no una DJ en la aplicación externa de LAA.
        // Debido a que se puede hacer click n veces en este botón, sólo se asigna cuando "IdDjAntesDeAsociar" es null.
        if(lavadoActivosBean.getIdTareaBpm().equals(FenixConstants.TAREA_REALIZAR_ANALISIS_LAFT) 
           && lavadoActivosBean.getEsPorSeguimiento() && (lavadoActivosBean.getTipoSeguimiento()!=null) 
           && (lavadoActivosBean.getTipoSolicitudSinDj()!=null) && (lavadoActivosBean.getTipoSeguimiento().intValue()==1) 
           && (lavadoActivosBean.getTipoSolicitudSinDj().intValue()==2) && (lavadoActivosBean.getIdDjAntesDeAsociar()==null)) {
            
            logger.log(ADFLogger.WARNING, "asigna idDjAntesDeAsociar: " + 
                                          JSFUtils.resolveExpression("#{bindings.IdDeclaracion.inputValue}"));            
            lavadoActivosBean.setIdDjAntesDeAsociar((oracle.jbo.domain.Number)
                                                    JSFUtils.resolveExpression("#{bindings.IdDeclaracion.inputValue}"));
            guardarEstadoTarea();
        }
    }
    
    private void abrirAplicacionExternaLaa() {
        logger.log(ADFLogger.TRACE, "Inside abrirAplicacionExternaLaa.");
        LavadoActivosBean lavadoActivosBean = (LavadoActivosBean) JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        StringBuilder script = new StringBuilder();
        DCIteratorBinding voDeclaracionJuradaLaft = null;
        Long codigoExternoDeclaracion = null;
        DCIteratorBinding configuracionVOIterator = null;
        ViewObject configuracionVoImpl = null;
        Row row = null;
        String url = null;
        
        // Asignación de variables
        configuracionVOIterator = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
        configuracionVoImpl = configuracionVOIterator.getViewObject();
        row = configuracionVoImpl.getRow(new Key(new Object[]{"URL_APP_EXTERNA_LAA"}));
        url = row == null ? null : (String) row.getAttribute("Valor");
        
        // Abrimos popup con aplicación externa de LAA
        ExtendedRenderKitService erks = Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                                                    ExtendedRenderKitService.class);
        script.append("window.open(\"").append(url);
        
        if(lavadoActivosBean.getEsAsociacionLaft()) {
            
            script.append("?CodigoNegocio=");
            script.append(JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean.idOperacion}")); 
        }
        else{
            voDeclaracionJuradaLaft = ADFUtils.getDCBindingContainer().findIteratorBinding("DeclaracionJuradaLaftVOIterator");
            codigoExternoDeclaracion = (Long) voDeclaracionJuradaLaft.getRowAtRangeIndex(0).getAttribute("CodigoExternoDeclaracion");
            
            script.append("?CodigoDeclaracion=");
            script.append(codigoExternoDeclaracion);
        }
        
        script.append("\"");
        script.append(", \"aplicacionLaa\",\"width=800px,height=600px,resize=0,location=0\");");
        erks.addScript(FacesContext.getCurrentInstance(), script.toString());
    }
    
    private Boolean actualizarDeclaracionJurada(Long idOperacion, String ejecutivo) {
        logger.warning("Dentro de actualizarDeclaracionJurada");
        logger.warning("idOperacion : "+idOperacion);
        logger.warning("ejecutivo : "+ejecutivo);
        Boolean isValidActualizarDJ = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        HashMap<String, ActualizarDeclaracionJuradaResponseType> respuestaServicio = null;
        ActualizarDeclaracionJuradaResponseType response = null;
        String errorCode = null;
        String message = null;
        
        if( ejecutivo == null || ejecutivo.equals("")){                                    
             logger.warning("Error, No se ha podido recuperar el responsable de la operacion");
             JSFUtils.addFacesErrorMessage("*Error,la variable ejecutivo es resuelta a null en actualizarDeclaracionJurada()");
             return Boolean.FALSE;
        }
        
        operationBinding = bindings.getOperationBinding("actualizarDeclaracionJurada");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("ejecutivo", ejecutivo);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else if(operationBinding.getResult() != null) {
                        
            respuestaServicio = (HashMap<String, ActualizarDeclaracionJuradaResponseType>)operationBinding.getResult();
            response = respuestaServicio.get("response");
            
            if(response.getResultado().getError() != null) {
                errorCode = response.getResultado().getError().getErrorCode() == null ? "" :  
                    response.getResultado().getError().getErrorCode();
            }
            else{ errorCode = ""; }
                        
            if((response.getResultado().getResult() != null) 
               && (response.getResultado().getResult().value().equalsIgnoreCase("OK")) 
               && (errorCode.trim().length() == 0)) {
                        
                isValidActualizarDJ = Boolean.TRUE;         
            }else{
                // Error al ejecutar el servicio Actualizar Declaracion Jurada
                message = response.getResultado().getMessage() == null ? "" : 
                    ("Mensaje: " + response.getResultado().getMessage()) + ". ";
                if(response.getResultado().getError().getErrorCode().equals("-1")){
                    JSFUtils.addFacesErrorMessage("No se puede actualizar la declaración debido a que aún no se han contestado las preguntas asociadas a la declaración.");
                }else{
                    JSFUtils.addFacesErrorMessage(message + "Código de error: " 
                                                  + response.getResultado().getError().getErrorCode() + ". Descripción: " 
                                                  + response.getResultado().getError().getErrorDescription() + ".");
                    logger.log(ADFLogger.ERROR, response.getResultado().getError().getErrorDescription());
                }
            }
        }
        
        logger.warning("Fuera de actualizarDeclaracionJurada, return : "+isValidActualizarDJ);
        
        return isValidActualizarDJ;
    }
    
    private Boolean crearDeclaracionJurada(Long idOperacion, Boolean esPropagarEnLaa, String tipoOrigen,
                                           String instanciaProceso, String idTareaBpm) {
        logger.warning("Dentro de crearDeclaracionJurada");
        logger.warning("idOperacion  :"+idOperacion);
        logger.warning("esPropagarEnLaa :"+esPropagarEnLaa);
        logger.warning("tipoOrigen :"+tipoOrigen);
        logger.warning("instanciaProceso :"+instanciaProceso);
        logger.warning("idTareaBpm :"+idTareaBpm);
        
        Boolean isValidCrearDJ = null;
        DCIteratorBinding voDeclaracionJuradaLaft = null;
        String conclusionDeclaracion = null;
        String conclusionBusqueda = null;
        oracle.jbo.domain.Date fechaFirma = null;
        
        // Asignación de variables
        conclusionDeclaracion = (String)ADFUtils.getBoundAttributeValue("ConclusionAnalisisDeclaracion");
        conclusionBusqueda = (String)ADFUtils.getBoundAttributeValue("ConclusionAnalisisBusqueda");
        
        // Asignamos el atributo TipoOrigen
        ADFUtils.setBoundAttributeValue("TipoOrigen", tipoOrigen);
        
        // Incidencia FNXII-3092
        fechaFirma = asignarNullFechaFirma(); 
                
        // Para el caso de Recopilar Información, validar que al crear la nueva declaración los campos 
        // [Conclusión del análisis de la declaración] y [Conclusión del análisis de la búsqueda] no tengan valor.
        // Nota: checamos el IdTareaBpm debido a que este método se llama también en la pantalla Realizar análisis LAFT fondos externos
        if((idTareaBpm != null) && idTareaBpm.equalsIgnoreCase(FenixConstants.TAREA_RECOPILAR_INFO_OFIC) ) {
            
            ADFUtils.setBoundAttributeValue("ConclusionAnalisisDeclaracion", null);
            ADFUtils.setBoundAttributeValue("ConclusionAnalisisBusqueda", null);
        }
        
        // Creamos Declaración Jurada -sólo cuando NO existe-
        if(!existeDeclaracionJurada(idOperacion, tipoOrigen, instanciaProceso)) {
            logger.warning("Dentro de la opcion de crear o duplicar declaracion jurada");
            isValidCrearDJ = crearDuplicarDeclaracionJurada(idOperacion, esPropagarEnLaa, false);
        }
        else{
            logger.warning("Dentro de la opcion de existe declaracion jurada");
            isValidCrearDJ = Boolean.TRUE;
        }
        
        // Obtenemos iterador después de crear la DJ, debido a que al crear se modifica el current row
        voDeclaracionJuradaLaft = 
            ADFUtils.getDCBindingContainer().findIteratorBinding("DeclaracionJuradaLaftVOIterator");
        
        // Si falló al crear la DJ, regresamos las conclusiones, fecha firma y TipoOrigen a su valor anterior
        if((!isValidCrearDJ) && (voDeclaracionJuradaLaft.getRowAtRangeIndex(0) != null)) {
            logger.warning("Si falló al crear la DJ, regresamos las conclusiones, fecha firma y TipoOrigen a su valor anterior");
            voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("ConclusionAnalisisDeclaracion", conclusionDeclaracion);
            voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("ConclusionAnalisisBusqueda", conclusionBusqueda);
            voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("FechaFirmaDeclaracion", fechaFirma);
            voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("TipoOrigen", null);
        }
        logger.warning("Fuera de crearDeclaracionJurada,return :"+isValidCrearDJ);
        return isValidCrearDJ;
    }
    
    private Boolean existeDeclaracionJurada(Long idOperacion, String tipoOrigen, String instanciaProceso) {
        logger.warning("Dentro de existeDeclaracionJurada");
        logger.warning("idOperacion : "+idOperacion);
        logger.warning("tipoOrigen :"+tipoOrigen);
        logger.warning("instanciaProceso :"+instanciaProceso);
        
        Boolean existeDeclaracionJurada = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Verificamos si existe una DJ con ese IdOperacion e InstanciaProceso
        operationBinding = bindings.getOperationBinding("existeDeclaracionJurada");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("tipoOrigen", tipoOrigen);
        operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores (no usamos addFacesErrorMessage debido a que se lanzan desde el Model encapsulados en una JboException)
        }
        else if((operationBinding.getResult() != null) && ((Boolean)operationBinding.getResult()))
            existeDeclaracionJurada = Boolean.TRUE; // Existe al menos una DJ con esos parámetros
        
        logger.warning("Fuera de existeDeclaracionJurada,return :"+existeDeclaracionJurada);
        return existeDeclaracionJurada;
    }
    
    private Boolean duplicarDeclaracionJurada(Long idOperacion) {
        logger.log(ADFLogger.TRACE, "Inside duplicarDeclaracionJurada.");
        Boolean isValidDuplicarDJ = null;
        DCIteratorBinding voDeclaracionJuradaLaft = null;
        oracle.jbo.domain.Date fechaFirma = null;
        
        // Incidencia FNXII-3092
        fechaFirma = asignarNullFechaFirma(); 
        
        // Duplicamos Declaración Jurada 
        isValidDuplicarDJ = crearDuplicarDeclaracionJurada(idOperacion, null, true);
        
        // Obtenemos iterador después de duplicar la DJ, debido a que al crear se modifica el current row
        voDeclaracionJuradaLaft = 
            ADFUtils.getDCBindingContainer().findIteratorBinding("DeclaracionJuradaLaftVOIterator");
        
        // Si falló al duplicar la DJ, regresamos la fecha firma a su valor anterior
        if((!isValidDuplicarDJ) && (voDeclaracionJuradaLaft.getRowAtRangeIndex(0) != null))
            voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("FechaFirmaDeclaracion", fechaFirma);
        
        return isValidDuplicarDJ;
    }
    
    private oracle.jbo.domain.Date asignarNullFechaFirma() {
        logger.log(ADFLogger.TRACE, "Inside asignarNullFechaFirma.");
        DCIteratorBinding voDeclaracionJuradaLaft = null;
        oracle.jbo.domain.Date fechaFirma = null;
        
        // Obtenemos iterador
        voDeclaracionJuradaLaft =  ADFUtils.getDCBindingContainer().findIteratorBinding("DeclaracionJuradaLaftVOIterator");
        
        // Incidencia FNXII-3092
        // Al momento de crear/duplicar una Declaración, NO se envía la fecha de firma.
        if((voDeclaracionJuradaLaft.getRowAtRangeIndex(0) != null) 
           && (voDeclaracionJuradaLaft.getRowAtRangeIndex(0).getAttribute("IdDeclaracion") != null)) {
            
            fechaFirma = (oracle.jbo.domain.Date) voDeclaracionJuradaLaft.getRowAtRangeIndex(0).getAttribute("FechaFirmaDeclaracion");
            voDeclaracionJuradaLaft.getRowAtRangeIndex(0).setAttribute("FechaFirmaDeclaracion", null);
        }
        
        return fechaFirma;
    }
        
    private Boolean crearDuplicarDeclaracionJurada(Long idOperacion, Boolean esPropagarEnLaa, boolean esDuplicarDeclaracion) {
        logger.warning("Dentro de crearDuplicarDeclaracionJurada");
        logger.warning("idOperacion :"+idOperacion);
        logger.warning("esPropagarEnLaa : "+esPropagarEnLaa);
        logger.warning("esDuplicarDeclaracion :"+esDuplicarDeclaracion);
        
        Boolean isValidCrearDuplicarDJ = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        HashMap<String, CrearDeclaracionJuradaResponseType> respuestaServicio = null;
        CrearDeclaracionJuradaResponseType response = null;
        String errorCode = null;
        String message = null;
        
        if(!esDuplicarDeclaracion) {
            logger.warning("se ejecuta el metodo crearDeclaracionJurada");
            operationBinding = bindings.getOperationBinding("crearDeclaracionJurada");
            operationBinding.getParamsMap().put("esPropagarEnLaa", esPropagarEnLaa);
        }
        else{
            logger.warning("se ejecuta el metodo duplicarDeclaracionJurada");
            operationBinding = bindings.getOperationBinding("duplicarDeclaracionJurada");
        }
        
        operationBinding.getParamsMap().put("idOperacion", idOperacion);        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else if(operationBinding.getResult() != null) {
                        
            respuestaServicio = (HashMap<String, CrearDeclaracionJuradaResponseType>)operationBinding.getResult();
            response = respuestaServicio.get("response");
            
            if(response.getResultado().getError() != null) {
                errorCode = response.getResultado().getError().getErrorCode() == null ? "" : 
                    response.getResultado().getError().getErrorCode();
            }
            else{ errorCode = ""; }
                        
            if((response.getResultado().getResult() != null) 
               && (response.getResultado().getResult().value().equalsIgnoreCase("OK")) 
               && (errorCode.trim().length() == 0)) {
                        
                isValidCrearDuplicarDJ = Boolean.TRUE;         
            }
            else{
                // Error al ejecutar el servicio Crear Declaracion Jurada
                message = response.getResultado().getMessage() == null ? "" : 
                    ("Mensaje: " + response.getResultado().getMessage()) + ". ";
                JSFUtils.addFacesErrorMessage(message + "Código de error: " 
                                              + response.getResultado().getError().getErrorCode() + ". Descripción: " 
                                              + response.getResultado().getError().getErrorDescription() + ".");
                logger.log(ADFLogger.WARNING, response.getResultado().getError().getErrorDescription());
            }
        }
        logger.warning("Fuera de crearDuplicarDeclaracionJurada,return :"+isValidCrearDuplicarDJ);
        return isValidCrearDuplicarDJ;
    }
    
    private Boolean consultarDeclaracionJurada(Long idOperacion) {
        logger.warning("Dentro de consultarDeclaracionJurada");
        logger.warning("idOperacion : "+idOperacion);
        Boolean isValidConsultarDJ = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        HashMap<String, Object> respuestaServicio = null;
        ConsultarDeclaracionByIdOperacionResponseType response = null;
        String errorCode = null;
        String message = null;
        
        operationBinding = bindings.getOperationBinding("consultarDeclaracionJurada");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else if(operationBinding.getResult() != null) {
                        
            respuestaServicio = (HashMap<String, Object>)operationBinding.getResult();
            response = (ConsultarDeclaracionByIdOperacionResponseType)respuestaServicio.get("response");
            
            if(response.getResultado().getError() != null) {
                errorCode = response.getResultado().getError().getErrorCode() == null ? "" : 
                    response.getResultado().getError().getErrorCode();
            }
            else{ errorCode = ""; }
                        
            if((response.getResultado().getResult() != null) 
               && (response.getResultado().getResult().value().equalsIgnoreCase("OK")) 
               && (errorCode.trim().length() == 0)) {
                        
                isValidConsultarDJ = Boolean.TRUE;         
            }
            else{
                // Error al ejecutar el servicio Consultar Declaracion Jurada
                message = response.getResultado().getMessage() == null ? "" : 
                    ("Mensaje: " + response.getResultado().getMessage()) + ". ";
                JSFUtils.addFacesErrorMessage(message + "Código de error: " 
                                              + response.getResultado().getError().getErrorCode() + ". Descripción: " 
                                              + response.getResultado().getError().getErrorDescription() + ".");
                logger.log(ADFLogger.ERROR, response.getResultado().getError().getErrorDescription());
            }
        }
        logger.warning("Fuera de consultarDeclaracionJurada,return :"+isValidConsultarDJ);
        return isValidConsultarDJ;
    }
    
    /**
     * Guarda el estado de la tarea/instancia proceso. 
     */
    private void guardarEstadoTarea() {
        LavadoActivosBean lavadoActivosBean = (LavadoActivosBean) JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Guardamos Bean en BD
        operationBinding = bindings.getOperationBinding("guardarEstadoTarea");
        operationBinding.getParamsMap().put("idOperacion", Long.valueOf(lavadoActivosBean.getIdOperacion()));
        operationBinding.getParamsMap().put("instanciaProceso", lavadoActivosBean.getInstanciaProceso());
        operationBinding.getParamsMap().put("idTareaBpm", Integer.valueOf(lavadoActivosBean.getIdTareaBpm()));
        operationBinding.getParamsMap().put("estadoTarea", createBlobDomain(lavadoActivosBean));
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores (no usamos addFacesErrorMessage debido a que se lanzan desde el Model encapsulados en una JboException)
        }
    }
    
    /**
     * Elimina de BD el registro con el estado para una Operación/InstanciaProceso/Tarea específico.
     * @param idOperacion
     * @param instanciaProceso
     * @param idTareaBpm
     * 
     * @author Francisco Cuevas Pineda 
     * @since 20/abril/2016
     */
    private void eliminarEstadoTarea(Long idOperacion, String instanciaProceso, Integer idTareaBpm) {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Guardamos Bean en BD
        operationBinding = bindings.getOperationBinding("eliminarEstadoTarea");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
        operationBinding.getParamsMap().put("idTareaBpm", idTareaBpm);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores (no usamos addFacesErrorMessage debido a que se lanzan desde el Model encapsulados en una JboException)
        }
    }
    
    private BlobDomain createBlobDomain(Object serializedObject) {
        logger.log(ADFLogger.TRACE, "Inside createBlobDomain");
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutput objectOutput = null;
        BlobDomain blobDomain = null;

        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutput = new ObjectOutputStream(byteArrayOutputStream);   
            objectOutput.writeObject(serializedObject);
            byte[] objectBytes = byteArrayOutputStream.toByteArray();
            
            blobDomain = new BlobDomain(objectBytes);

        } catch (IOException e) {
            logger.log(ADFLogger.ERROR, e.getMessage()); 
        } finally {
            try {
                if (objectOutput != null) {
                    objectOutput.close();
                }
            } catch (IOException ex) {
                logger.log(ADFLogger.ERROR, ex.getMessage()); 
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException ex) {
                logger.log(ADFLogger.ERROR, ex.getMessage()); 
            }
        }

        return blobDomain;
    }
    
    public void setOtInitForm(RichOutputText otInitForm) {
        this.otInitForm = otInitForm;
    }

    public RichOutputText getOtInitForm() {
        // Inicializamos componentes visuales. 
        // Usado cuando queremos acceder a un componente que existe hasta que se desplegó en pantalla 
        mappingBlobToBean();
        
        return otInitForm;
    }
    
    private void mappingBlobToBean() {
        logger.log(ADFLogger.WARNING, "Inside mappingBlobToBean.");
        LavadoActivosBean lavadoActivosBean = null;
        LavadoActivosBean lavadoActivosAuxBean = null;
        BlobDomain estadoTarea = null;
        
        // Mapeo del estado guardado en la BD al bean de pageFlowScope
        lavadoActivosBean = (LavadoActivosBean)JSFUtils.resolveExpression("#{pageFlowScope.LavadoActivosBean}");
        estadoTarea = getEstadoTareaByOpProcTarea(Long.valueOf(lavadoActivosBean.getIdOperacion()), 
                                                  lavadoActivosBean.getInstanciaProceso(), 
                                                  Integer.valueOf(lavadoActivosBean.getIdTareaBpm()));
        
        // Si el estadoTarea es null y estamos en la tarea Realizar Análisis LAFT (41), obtenemos la instancia guardada 
        // para la tarea Recopilar Información (40) -puesto que primero pasó por ahí- y asignamos únicamente las variables 
        // que deben sobrevivir entre ambas tareas (para no planchar datos asignados en el Payload al iniciar).
        if((estadoTarea == null) && (lavadoActivosBean.getIdTareaBpm().equals(FenixConstants.TAREA_REALIZAR_ANALISIS_LAFT))) {
            
            estadoTarea = getEstadoTareaByOpProcTarea(Long.valueOf(lavadoActivosBean.getIdOperacion()), 
                                                      lavadoActivosBean.getInstanciaProceso(), 
                                                      Integer.valueOf(FenixConstants.TAREA_RECOPILAR_INFO_OFIC));
        }
                
        lavadoActivosAuxBean = (LavadoActivosBean)getObjectFromBlobDomain(estadoTarea);
        if(lavadoActivosAuxBean != null) {
            
            // -- Variables asignadas en Recopilar Información y que sobreviven en Realizar Análisis LAFT --
            // Se asignan si el componente asociado a las mismas es visible en pantalla. Ej. TipoSeguimiento se asigna 
            // sólo si el flag porSeguimiento viene en true desde el Payload y, por lo tanto, "EsPorSeguimiento" es true.
            if(lavadoActivosBean.getEsPorSeguimiento() || lavadoActivosBean.getEsSinDjOfic()) {
                
                lavadoActivosBean.setTipoSeguimiento(lavadoActivosAuxBean.getTipoSeguimiento()); 
                lavadoActivosBean.setTipoSolicitudSinDj(lavadoActivosAuxBean.getTipoSolicitudSinDj());
                lavadoActivosBean.setEsPanelOficSinDjVisible(lavadoActivosAuxBean.getEsPanelOficSinDjVisible()); 
                lavadoActivosBean.setEsDjCreada(lavadoActivosAuxBean.getEsDjCreada());
                lavadoActivosBean.setEsDjDuplicada(lavadoActivosAuxBean.getEsDjDuplicada());
            }
            
            // Variable modificada en Recopilar Información OFIC. Es editable cuando en el Payload se recibe "requiereAsociacion" 
            // o "requiereExcepcion" en true. Excepto para el escenario Retorno con Excepción, es decir "existeRetorno" y "requiereExcepcion" en true.
            // En la tarea Realizar Análisis LAFT es readonly y su valor se asigna usando flags del Payload. 
            if(lavadoActivosBean.getEsConDjAsociacionOfic() 
               || (lavadoActivosBean.getEsConDjExcepcionOfic() && !lavadoActivosBean.getEsNuevaOfic())) {
                
                lavadoActivosBean.setTipoSolicitudAsoc(lavadoActivosAuxBean.getTipoSolicitudAsoc());
            }
            
            // -- Variables asignadas en Realizar Análisis LAFT y que sólo sobreviven en esa pantalla --
            if(lavadoActivosBean.getIdTareaBpm().equals(FenixConstants.TAREA_REALIZAR_ANALISIS_LAFT)) {
                
                // Incidencia FNXII-3474: escenario Seguimiento por Actualización(tipoSeguimiento=1), con solicitud de asociación(tipoSolicitudSinDj=2).
                if(lavadoActivosBean.getEsPorSeguimiento() && (lavadoActivosBean.getTipoSeguimiento()!=null) 
                   && (lavadoActivosBean.getTipoSolicitudSinDj()!=null) && (lavadoActivosBean.getTipoSeguimiento().intValue()==1) 
                   && (lavadoActivosBean.getTipoSolicitudSinDj().intValue()==2)) {
                    
                    lavadoActivosBean.setIdDjAntesDeAsociar(lavadoActivosAuxBean.getIdDjAntesDeAsociar());
                }
            }
        }
        
        logger.warning("Valores al finalizar la tarea.");
        lavadoActivosBean.impresionLogsDeclaracion();
    }
    
    /**
     * Obtiene el estado de la tarea en BD. 
     */
    private BlobDomain getEstadoTareaByOpProcTarea(Long idOperacion, String instanciaProceso, Integer idTareaBpm) {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        BlobDomain estadoTarea = null;
        OperationBinding operationBinding = null;
        
        // Guardamos Bean en BD
        operationBinding = bindings.getOperationBinding("getEstadoTareaByOpProcTarea");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
        operationBinding.getParamsMap().put("idTareaBpm", idTareaBpm);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores (no usamos addFacesErrorMessage debido a que se lanzan desde el Model encapsulados en una JboException)
        }
        else if(operationBinding.getResult() != null)
            estadoTarea = (BlobDomain)operationBinding.getResult();
        
        return estadoTarea;
    }
    
    private Object getObjectFromBlobDomain(BlobDomain estadoTarea) {
        logger.log(ADFLogger.TRACE, "Inside getObjectFromBlobDomain");
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInput objectInput = null;
        Object objectFromBlob = null;
        
        if(estadoTarea != null) {
            try {
                byteArrayInputStream = new ByteArrayInputStream(estadoTarea.toByteArray());
                objectInput = new ObjectInputStream(byteArrayInputStream);
                objectFromBlob = objectInput.readObject(); 
                
            } catch (Exception e) {
                logger.log(ADFLogger.ERROR, e.getMessage()); 
            } finally {
                try {
                    byteArrayInputStream.close();
                } catch (IOException ex) {
                    logger.log(ADFLogger.ERROR, ex.getMessage()); 
                }
                try {
                    if (objectInput != null) {
                        objectInput.close();
                    }
                } catch (IOException ex) {
                    logger.log(ADFLogger.ERROR, ex.getMessage()); 
                }
            }
        }
        
        return objectFromBlob;
    }
    
    private void actualizarTiposDocumentoPorOpTareaTipo(Long idOperacion, Integer idTareaBpm, Integer idTipoDocAnterior, 
                                                        Integer idTipoDocNuevo) {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Guardamos Bean en BD
        operationBinding = bindings.getOperationBinding("actualizarTiposDocumentoPorOpTareaTipo");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("idTareaBpm", idTareaBpm);
        operationBinding.getParamsMap().put("idTipoDocAnterior", idTipoDocAnterior);
        operationBinding.getParamsMap().put("idTipoDocNuevo", idTipoDocNuevo);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores (no usamos addFacesErrorMessage debido a que se lanzan desde el Model encapsulados en una JboException)
        }
    }
       
    private void actualizarFlagPayload(String variable, Object valor) {
        AttributeBinding attributeBinding = null;
        
        attributeBinding = ADFUtils.findControlBinding(variable);
        attributeBinding.setInputValue(valor);
    }
    
    
    private String getFlagPayload(String variable) {  
        String valorRetorno = null;
         
               valorRetorno = (null == ADFUtils.getBoundAttributeValue(variable)) ? ""
                            : ADFUtils.getBoundAttributeValue(variable).toString();
        return valorRetorno;
    }
    
    public void setEjecutivo(String ejecutivo) {
        this.ejecutivo = ejecutivo;
    }

    public String getEjecutivo() {
        logger.warning("*Inf, Inicia el metodo getEjecutivo()");
        String responsableOperacion = null;
        // Nombre de ejecutivo para actualizar la DJ
        if((ADFContext.getCurrent().getSecurityContext().getUserName() == null) 
           || (ADFContext.getCurrent().getSecurityContext().getUserName().trim().length() < 1) 
           || (ADFContext.getCurrent().getSecurityContext().getUserName().trim().equalsIgnoreCase("anonymous"))
           || (ADFContext.getCurrent().getSecurityContext().getUserName().trim().equalsIgnoreCase("weblogic"))){
            
          logger.warning("No se recupero el usuario en session,recuperarlo de #{securityContext.userName}");

          if(null != JSFUtils.resolveExpression("#{securityContext.userName}")){
              logger.warning("#{securityContext.userName} :"+JSFUtils.resolveExpression("#{securityContext.userName}"));
              responsableOperacion = JSFUtils.resolveExpression("#{securityContext.userName}").toString();
          }else{
              logger.severe("#{securityContext.userName} es nulo");
          }
                        
        }else{
            logger.warning("Se recuperar el usuario en session");            
            responsableOperacion = (null == ADFContext.getCurrent().getSecurityContext().getUserName())? null
                                 : ADFContext.getCurrent().getSecurityContext().getUserName().toString();                    
                       
        }
        
        logger.warning("*Inf, usuario recuperado: "+ responsableOperacion);        
        logger.warning("*Inf, termina el metodo getEjecutivo()");
        return responsableOperacion;
    }

    /*** END acciones genéricas ***/
    
    public void recargarDeclaracionJurada(ActionEvent actionEvent) {
        logger.warning("Entrando en recargarDeclaracionJurada.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Guardamos Bean en BD
        operationBinding = bindings.getOperationBinding("consultarDeclaracionJurada");
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
    }
    
    public void actualizarReporteElegibilidad(Long idOperacion) {
        logger.warning("Inside actualizarReporteElegibilidad.");
        logger.warning("idOperacion: " + idOperacion);
        
        Long idAdjunto = null;
        Long idAdjunto2 = null;
        
        // Devolver lista de adjuntos a eliminar
        idAdjunto = consultarIdAdjunto(idOperacion);
        
        if (idAdjunto != null) {
            logger.warning("idAdjunto: " + idAdjunto);
            eliminarCuestionarioElegibilidadOld(idAdjunto);
        }
        
        idAdjunto2 = consultarIdAdjunto(idOperacion);
        
        if (idAdjunto2 != null) {
            logger.warning("idAdjunto2: " + idAdjunto2);
            eliminarCuestionarioElegibilidadOld(idAdjunto2);
        }
        
        if (idAdjunto != null || idAdjunto2 != null) {
            generarCuestionarioElegibilidad(idOperacion,Boolean.TRUE);
            
            notificarActualizacion(idOperacion);
        }
        
        logger.warning("Finaliza actualizarReporteElegibilidad.");
    }
    
    private byte[] generarCuestionarioElegibilidad(Long idOperacion, Boolean enviarOnBase) {
            logger.warning("Dentro de generarCuestionarioElegibilidad");
            logger.warning("idOperacion :"+idOperacion);
            logger.warning("enviarOnBase :"+enviarOnBase);
            
            String nombreUsuario = consultarNombreUsuario(this.getEjecutivo());
            
            String tipoGen = "LAFT";
            Cuestionario12BndQSService service = this.initCuestionarioService();
            CuestionarioPT port = service.getCuestionario12BndQSPort();
            // Add your code to call the desired methods.
            CrearReporteElegibilidadV2RequestType request = new CrearReporteElegibilidadV2RequestType();
            request.setIdOperacion(idOperacion);
            request.setGuardarReporte(enviarOnBase);
            Archivo file = new Archivo();
            file.setArchivo(REPORTE_FILE_NAME);
            request.setNombreArchivo(file);
            
            // Se agregan parametros para Req 3 de CE
            request.setResponsableOperacion(nombreUsuario);
            request.setTipoGeneracion(tipoGen);
           
            try{logger.log(ADFLogger.WARNING,">HNWS"+ writeXMLRequest(request,request.getClass()).toString());}catch(Exception ex){;}
                CrearReporteElegibilidadV2ResponseType response = port.crearReporteElegibilidadV2(request);
            try{logger.log(ADFLogger.WARNING,">HNWS"+ writeXMLRequest(response,response.getClass()).toString());}catch(Exception ex){;}
            
            logger.warning("cuestionario :"+response.getCuestionario());
            logger.warning("Fuera de generarCuestionarioElegibilidad");
            return response != null ? response.getCuestionario() : null;
    }
    
    public String consultarNombreUsuario(String loginUsuario) {
        logger.warning("Inside consultarNombreUsuario.");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String nombreUsuario = null;
        
        operationBinding = bindings.getOperationBinding("obtenerNombre");
        operationBinding.getParamsMap().put("login", loginUsuario);

        operationBinding.execute();
        
        if (operationBinding.getResult() != null) {
            
            nombreUsuario = (String) operationBinding.getResult();    
        } else {
            logger.warning("nombreUsuario Null.");
        }
        
        return nombreUsuario;
    }
    
    public Long consultarIdAdjunto(Long idOperacion) {
        logger.warning("Inside consultarIdAdjunto.");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long idAdjunto = null;
        
        operationBinding = bindings.getOperationBinding("obtenerIdAdjunto");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);

        operationBinding.execute();
        
        if (operationBinding.getResult() != null) {
            
            idAdjunto = (Long) operationBinding.getResult();    
        } else {
            logger.warning("idAdjunto Null.");
        }
        
        return idAdjunto;
    }
    
    public void eliminarCuestionarioElegibilidadOld(Long idAdjunto) {
        logger.warning("Inside consultarIdAdjunto.");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        operationBinding = bindings.getOperationBinding("eliminarDocumentoTree");
        operationBinding.getParamsMap().put("idAdjunto", idAdjunto);    

        operationBinding.execute();
    }
    
    public void notificarActualizacion(Long idOperacion) {
        logger.warning("Inside notificarActualizacion.");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        operationBinding = bindings.getOperationBinding("enviarCorreoCuestionarioElegibilidad");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("responsable", consultarResponsableOperacion(idOperacion));

        operationBinding.execute();
    }
    
    public String consultarResponsableOperacion(Long idOperacion) {
        logger.warning("Inside consultarResponsableOperacion.");
        
        String responsableOperacion = null;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        operationBinding = bindings.getOperationBinding("obtenerResponsable");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);

        operationBinding.execute();
        
        if (operationBinding.getResult() != null) {
            responsableOperacion = (String) operationBinding.getResult();
        }
        
        logger.warning("responsableOperacion: " + responsableOperacion);
        
        return responsableOperacion;
    }
        
    private Cuestionario12BndQSService initCuestionarioService() {
        DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
        ViewObject configuracionVoImpl = dcItteratorBindings.getViewObject();
        Row row = configuracionVoImpl.getRow(new Key(new Object[]{IWsdlLocation.CUESTIONARIO}));
        String wsdl = row == null ? null : (String) row.getAttribute("Valor");

        return IWsdlLocation.Service.getInstance(Cuestionario12BndQSService.class, wsdl);
    }
    
    public StringWriter writeXMLRequest(Object request, Class requestClass) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(requestClass);       
        Marshaller m = context.createMarshaller();
        m.marshal(new JAXBElement(new QName(requestClass.getName()), requestClass, request), writer);        
        return writer;
    }
}
