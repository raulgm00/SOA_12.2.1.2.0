package pc07gestioncobroght.bean;


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

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.layout.RichPanelAccordion;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.vo.GestionarAvisoDeCobroVOImpl;
import org.bcie.fenix.common.model.vo.LineasDeCreditoOperacionLOVImpl;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class GestionCobroActionBean extends FenixActionBean {

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(GestionCobroActionBean.class);
    public static final String BUNDLE = "pc07gestioncobroght.PC07GestionCobroGHTBundle";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String GENERAR_AVISO_COBRO_ITERATOR_NAME = "GestionarAvisoDeCobroVOIterator";
    private static final String INVOKE_ACTION_BEAN_NAME = "invokeActionBean";

    private GestionCobroBean gestionCobroBean;
    private RichPopup popupFinalizarTarea;
    private RichPopup popupCancelarPopup;
    private RichPopup popupRetornarCOFI;
    private RichButton uiBoton;
    private RichRegion documentosRegionUIC;
    private RichShowDetailItem panelDocumentosUIC;
    private RichPanelAccordion panelAcordeonUIC;


    public GestionCobroActionBean() {
        super();
    }

    /**
     * @return
     */
    public String invocarFinalizarTarea() {
        LOGGER.warning("Inicia validacion para mostrar mensaje de confirmacion de Finalizar tarea.");

        gestionCobroBean = (GestionCobroBean) JSFUtils.resolveExpression(GestionCobroBean.BEAN_EXPRESSION);

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        Boolean existeDocumento = Boolean.FALSE;
        Boolean existeComentario = Boolean.FALSE;
        int codigoTarea = getCodigoTarea(gestionCobroBean);
        long idCliente = getIdCliente(gestionCobroBean);
        oracle.jbo.domain.Number idFlujo = new oracle.jbo.domain.Number(gestionCobroBean.getIdFlujo());
        switch (codigoTarea) {
        case FenixConstants.PC07_ENVIAR_AVISO_COBRO_MANUAL:
            LOGGER.fine("PC07_ENVIAR_AVISO_COBRO_MANUAL");
            /*EX_02	Identifica que la tabla “Documentos adjuntados a la tarea” del gestor de documentos cuente
             * con un documento adjunto. MSG_05*/
            
            //Se elimina validación de documento ya que de acuerdo con QA en esta tarea no se adjunta ningun documento
            isValidFinalizarTarea = Boolean.TRUE;

            break;
        case FenixConstants.PC07_GENERAR_VALIDAR_AVISO_COBRO:
            LOGGER.fine("PC07_GENERAR_VALIDAR_AVISO_COBRO");
            /*RN_01 Esta regla fue eliminada del caso de uso.
                RN_03 La tabla “Documentos adjuntados a la tarea” del gestor de documentos deberá de contar
                    con un documento para poder finalizar la tarea.
                EX_02 Identifica que la tabla “Documentos adjuntados a la tarea” del gestor de documentos cuente
                    con un documento adjunto con la extensión “.doc” y el tipo de documento
                    “Aviso de cobro” o “Saldos en mora”*/
            existeDocumento =
                validarDocumentoClienteSerieYMimeType(idCliente, FenixConstants.TD_AVISO_DE_COBRO,
                                                      FenixConstants.MT_DOC, gestionCobroBean.getIdFlujo()) ||
                validarDocumentoClienteSerieYMimeType(idCliente, FenixConstants.TD_AVISO_DE_COBRO,
                                                      FenixConstants.MT_DOCX, gestionCobroBean.getIdFlujo()) ||
                validarDocumentoClienteSerieYMimeType(idCliente, FenixConstants.TD_SALDOS_EN_MORA,
                                                      FenixConstants.MT_DOC, gestionCobroBean.getIdFlujo()) ||
                validarDocumentoClienteSerieYMimeType(idCliente, FenixConstants.TD_SALDOS_EN_MORA,
                                                      FenixConstants.MT_DOCX, gestionCobroBean.getIdFlujo()) ? true :
                false;
            if (!existeDocumento) {
                bundleKeyErrorList.add("MSG05_VALIDAR_DOCUMENTOS");
            } else {
                isValidFinalizarTarea = Boolean.TRUE;
            }
            break;
        case FenixConstants.PC07_VALIDAR_RECIBO_COBRO:
            LOGGER.fine("PC07_VALIDAR_RECIBO_COBRO");
            /*EX_01	Identifica que no se ha adjuntado el documento de recibo de pago. MSG_02*/
            if (gestionCobroBean != null) {
                Long numeroSerieDocumentoLong = gestionCobroBean.getIdFlujo();

                if (numeroSerieDocumentoLong != null) {
                    oracle.jbo.domain.Number numeroSerieDocumentoNumber =
                        new oracle.jbo.domain.Number(numeroSerieDocumentoLong);
                    existeDocumento =
                        validarDocumentoClientePorNumeroSerieDocumento(idCliente, FenixConstants.TD_RECIBO_DE_PAGO,
                                                                       numeroSerieDocumentoNumber);

                    if (existeDocumento != null) {

                        if (existeDocumento) {
                            isValidFinalizarTarea = Boolean.TRUE;
                        }
                    } else {
                        LOGGER.warning("existeDocumento es NULL.");
                    }
                } else {
                    LOGGER.warning("numeroSerieDocumentoLong es NULL.");
                }
            } else {
                LOGGER.warning("gestionCobroBean es NULL.");
            }

            if (!isValidFinalizarTarea) {
                bundleKeyErrorList.add("MSG02_VALIDAR_DOCUMENTO_RECIBO");
            }
            break;
        default:
            LOGGER.log(ADFLogger.ERROR, "invocarFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        LOGGER.warning("Finaliza e invoca mensaje de confirmacion: " + isValidFinalizarTarea);

        if (!isValidFinalizarTarea) {
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        } else {
            finalizarTareaPopup();
        }
        return null;
    }

    public void finalizarTareaPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupFinalizarTarea.show(hints);
    }

    public String aceptarFinalizarTarea() {
        LOGGER.warning("Carga Documentos Cliente OnBase");
        popupFinalizarTarea.hide();
        gestionCobroBean = (GestionCobroBean) JSFUtils.resolveExpression(GestionCobroBean.BEAN_EXPRESSION);

        int codigoTarea = getCodigoTarea(gestionCobroBean);

        switch (codigoTarea) {
        case FenixConstants.PC07_GENERAR_VALIDAR_AVISO_COBRO:
            cargarDocumentosAviso(gestionCobroBean.getLngIdCliente(), String.valueOf(codigoTarea),
                                  gestionCobroBean.getIdFlujo());
            recargaDeDocumentos();
            cargarDocumentosClienteOnBase();
            break;
        default:
            cargarDocumentosClienteOnBase();
            break;
        }
        LOGGER.warning("Termina todos los metodos de la pantalla");
        LOGGER.warning("Inicia metodo de invokeActionBean para gestion de cobro");
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue(INVOKE_ACTION_BEAN_NAME);
        return invokeActionBean.invokeOperation();
    }

    public String cancelarFinalizarTarea() {
        LOGGER.warning("Cancela confirmación de finalizar tarea");
        popupFinalizarTarea.hide();
        return null;
    }

    public String invocarCancelar() {
        LOGGER.warning("Se validan las condiciones para mostrar mensaje de confirmacion al solicitar Cancelar Tarea.");
        gestionCobroBean = (GestionCobroBean) JSFUtils.resolveExpression(GestionCobroBean.BEAN_EXPRESSION);

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean isCancelarTarea = Boolean.FALSE;
        Boolean existeDocumento = Boolean.FALSE;
        Boolean existeComentario = Boolean.FALSE;

        int codigoTarea = getCodigoTarea(gestionCobroBean);
        long idCliente = getIdCliente(gestionCobroBean);
        
        switch (codigoTarea) {
        case FenixConstants.PC07_ENVIAR_AVISO_COBRO_MANUAL:
            LOGGER.warning("PC07_ENVIAR_AVISO_COBRO_MANUAL");

            /* RN_01 Para poder cancelar el proceso es necesario haber ingresado al menos un comentario.
             * EX_01 Identifica que no se ha ingresado un comentario.
             * MSG_03 Es necesario agregar al menos un comentario para poder cancelar el proceso.
             */
            existeComentario =
                validarComentarioCliente(idCliente, String.valueOf(codigoTarea), getInstanciaTarea()) == null ? false :
                validarComentarioCliente(idCliente, String.valueOf(codigoTarea), getInstanciaTarea());

            if (!existeComentario) {
                bundleKeyErrorList.add("MSG03_VALIDAR_COMENTARIOS");
            }
            // Se elimina validacion de acuerdo con QA ya que no aplica al cancelar el proceso ya que se 
            // eliminan los documentos
            
            //EX_02 Identifica que la tabla “Documentos adjuntados a la tarea” del gestor de documentos cuente
            //con un documento adjunto. MSG_05*/
//            existeDocumento = 
//                validarDocumentoClienteTareaBpmIdFlujo(idCliente, FenixConstants.TD_AVISO_DE_COBRO, FenixConstants.PC07_ENVIAR_AVISO_COBRO_MANUAL,  idFlujo) == null ? false :
//                validarDocumentoClienteTareaBpmIdFlujo(idCliente, FenixConstants.TD_AVISO_DE_COBRO, FenixConstants.PC07_ENVIAR_AVISO_COBRO_MANUAL,  idFlujo);
//
//            if (!existeDocumento) {
//                bundleKeyErrorList.add("MSG05_VALIDAR_DOCUMENTOS");
//            }

            isCancelarTarea = existeComentario ? Boolean.TRUE : Boolean.FALSE;
            break;
        case FenixConstants.PC07_GENERAR_VALIDAR_AVISO_COBRO:
            LOGGER.warning("PC07_GENERAR_VALIDAR_AVISO_COBRO");
            /* RN_02 Para poder cancelar el proceso es necesario haber ingresado al menos un comentario.
             * EX_01 Identifica que no se ha ingresado un comentario.
             * MSG_03 Es necesario agregar al menos un comentario para poder cancelar el proceso.
            */
            existeComentario =
                validarComentarioCliente(idCliente, String.valueOf(codigoTarea), getInstanciaTarea()) == null ? false :
                validarComentarioCliente(idCliente, String.valueOf(codigoTarea), getInstanciaTarea());

            if (!existeComentario) {
                bundleKeyErrorList.add("MSG03_VALIDAR_COMENTARIOS");
            } else {
                isCancelarTarea = Boolean.TRUE;
            }
            break;
        default:
            LOGGER.log(ADFLogger.ERROR, "invocarCancelar(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }

        LOGGER.warning("Cancelar Tarea fue correcto para mostrar mensaje de confirmacion: " + isCancelarTarea);

        if (!isCancelarTarea) {
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        } else {
            cancelarPopup();
        }
        return null;
    }

    public void cancelarPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupCancelarPopup.show(hints);
    }

    public String aceptarCancelarTarea() {
        LOGGER.warning("Entra en aceptarCancelarTarea");
        gestionCobroBean = (GestionCobroBean) JSFUtils.resolveExpression(GestionCobroBean.BEAN_EXPRESSION);
        Integer idTarea = null;
        Long idCliente = null;
        Long numSerie = null;
        Boolean esEliminado = Boolean .FALSE;

        try{
            if(null != getCodigoTarea(gestionCobroBean)){
                idTarea = getCodigoTarea(gestionCobroBean);
            }else{
                LOGGER.warning("No se recupero el id de la tarea.");
            }
            if(null != gestionCobroBean.getLngIdCliente()){
                idCliente = gestionCobroBean.getLngIdCliente();
            }else{
                LOGGER.warning("No se recupero el id del cliente.");
            }
            if(null != gestionCobroBean.getIdFlujo()){
                numSerie = gestionCobroBean.getIdFlujo();
            }else{
                LOGGER.warning("No se recupero el id del flujo.");
            }
            
            esEliminado = eliminarDocumentoOnbase(idTarea, idCliente, numSerie);
            popupCancelarPopup.hide();
            
            if(esEliminado){
                InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue(INVOKE_ACTION_BEAN_NAME);
                return invokeActionBean.invokeOperation();  
            }else{
                LOGGER.warning("Error al eliminar documentos.");
                JSFUtils.addFacesErrorMessage("Error al eliminar documentos.");
            }  
        }catch(Exception e){
            LOGGER.warning("Error al invocar metod para eliminar documentos.");
        } 
        return null;
    }

    public String cancelarCancelarTarea() {
        popupCancelarPopup.hide();
        return null;
    }

    public String aceptarMasInformacion() {
        popupRetornarCOFI.hide();
        cargarDocumentosClienteOnBase();
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue(INVOKE_ACTION_BEAN_NAME);
        return invokeActionBean.invokeOperation();
    }

    public String cancelarMasInformacion() {
        popupRetornarCOFI.hide();
        return null;
    }

    public String invocarRetornarACOFI() {
        LOGGER.warning("Se validan las condiciones para mostrar mensaje de confirmacion retornar a COFI.");
        gestionCobroBean = (GestionCobroBean) JSFUtils.resolveExpression(GestionCobroBean.BEAN_EXPRESSION);

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean isValidMasInformacion = Boolean.FALSE;
        Boolean existeComentario = Boolean.FALSE;

        int codigoTarea = getCodigoTarea(gestionCobroBean);
        long idCliente = getIdCliente(gestionCobroBean);

        switch (codigoTarea) {
        case FenixConstants.PC07_ENVIAR_AVISO_COBRO_MANUAL:
            LOGGER.warning("PC07_ENVIAR_AVISO_COBRO_MANUAL");
            /* RN_01 Para poder cancelar el proceso es necesario haber ingresado al menos un comentario.
             * EX_01 Identifica que no se ha ingresado un comentario.
             * MSG_03 Es necesario agregar al menos un comentario para poder cancelar el proceso.*/

            existeComentario = validarComentarioCliente(idCliente, String.valueOf(codigoTarea), getInstanciaTarea());
            if (existeComentario == null) {
                LOGGER.warning("Validacion de comentarios retorno NULL");
                existeComentario = false;
            }

            if (!existeComentario) {
                bundleKeyErrorList.add("MSG_COMENTARIOS_COFI");
            } else {
                isValidMasInformacion = Boolean.TRUE;
            }
            break;
        default:
            LOGGER.log(ADFLogger.ERROR, "invocarRetornarACOFI(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }

        LOGGER.warning("Retornar a COFI fue correcto para mostrar mensaje de confirmacion: " + isValidMasInformacion);

        if (!isValidMasInformacion) {
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        } else {
            retornaCOFIPopup();
        }
        return null;
    }


    public void retornaCOFIPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupRetornarCOFI.show(hints);
    }

    public String aceptarRetornaCOFI() {
        popupRetornarCOFI.hide();
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue(INVOKE_ACTION_BEAN_NAME);
        return invokeActionBean.invokeOperation();
    }

    public String cancelarRetornaCOFI() {
        popupRetornarCOFI.hide();
        return null;
    }

    public void setPopupFinalizarTarea(RichPopup popupFinalizarTarea) {
        this.popupFinalizarTarea = popupFinalizarTarea;
    }

    public RichPopup getPopupFinalizarTarea() {
        return popupFinalizarTarea;
    }

    public void setPopupCancelarPopup(RichPopup popupCancelarPopup) {
        this.popupCancelarPopup = popupCancelarPopup;
    }

    public RichPopup getPopupCancelarPopup() {
        return popupCancelarPopup;
    }

    public void setPopupRetornarCOFI(RichPopup popupRetornarCOFI) {
        this.popupRetornarCOFI = popupRetornarCOFI;
    }

    public RichPopup getPopupRetornarCOFI() {
        return popupRetornarCOFI;
    }

    public void invocarGenerarAvisoCobro() {
        LOGGER.warning("invocarGenerarAvisoCobro starts");

        gestionCobroBean = (GestionCobroBean) JSFUtils.resolveExpression(GestionCobroBean.BEAN_EXPRESSION);
        gestionCobroBean.setBotonAviso(Boolean.TRUE);
        AdfFacesContext.getCurrentInstance().addPartialTarget(uiBoton);
        boolean avisoGenerado = false;
        List<String> bundleKeyErrorList = new ArrayList<String>();
        XMLGregorianCalendar fi;
        XMLGregorianCalendar ff;
        String mensajeAviso = null;

        /*EX_03	Valida que el valor capturado en el campo fecha inicial sea menor al capturado en el
          campo fecha final. MSG_06.
          EX_04	Valida que todos los campos obligatorios contengan un valor. MSG_02.*/

        AttributeBinding segregarOperacion = ADFUtils.findControlBinding("SegregarOperacion");
        AttributeBinding tipoSaldos = ADFUtils.findControlBinding("TipoSaldos");
        AttributeBinding tipoGeneracion = ADFUtils.findControlBinding("TipoGeneracion");
        AttributeBinding periodicidad = ADFUtils.findControlBinding("Periodicidad");
        AttributeBinding fechaInicial = ADFUtils.findControlBinding("FechaInicial");
        AttributeBinding fechaFinal = ADFUtils.findControlBinding("FechaFinal");
        //Se agrega parametro por incidencia FNXII-7340
        AttributeBinding nivelDetalle = ADFUtils.findControlBinding("NivelDetalle");
        
        List<Long> operaciones = gestionCobroBean.getOperacionesSeleccionadas();
        List<String> lineasDeCredito = gestionCobroBean.getLineasDeCreditoSeleccionadas();
        List<String> fondos = gestionCobroBean.getFondosSeleccionados();

        LOGGER.warning("getIdCliente: " + getIdCliente(gestionCobroBean));
        LOGGER.warning("tipoSaldos: " + tipoSaldos.getInputValue());
        LOGGER.warning("tipoGeneracion: " + tipoGeneracion.getInputValue());
        LOGGER.warning("periodicidad: " + periodicidad.getInputValue());
        LOGGER.warning("operaciones: " + operaciones);
        LOGGER.warning("lineasDeCredito: " + lineasDeCredito);
        LOGGER.warning("fondos: " + fondos);
        LOGGER.warning("fechaInicial: " + fechaInicial.getInputValue());
        LOGGER.warning("fechaFinal: " + fechaFinal.getInputValue());
        LOGGER.warning("nivelDetalle :"+nivelDetalle.getInputValue());
        
        if (getIdCliente(gestionCobroBean) == null || tipoSaldos.getInputValue() == null ||
            tipoGeneracion.getInputValue() == null || periodicidad.getInputValue() == null ||
            (operaciones == null || (operaciones != null && operaciones.size() == 0)) || 
            (lineasDeCredito == null || (lineasDeCredito != null && lineasDeCredito.size() == 0)) ||
            (fondos == null || (fondos != null && fondos.size() == 0)) || fechaInicial.getInputValue() == null ||
            fechaFinal.getInputValue() == null) {
            bundleKeyErrorList.add("MSG02_VALIDAR_CAMPOS_OBLIGATORIOS");
            LOGGER.warning("invocarGenerarAviso(): existen atributos en null");
        } else {
            fi = stringToXMLGregorianCalendar(fechaInicial.getInputValue().toString());
            ff = stringToXMLGregorianCalendar(fechaFinal.getInputValue().toString());
            if (fi.compare(ff) == DatatypeConstants.GREATER) {
                bundleKeyErrorList.add("MSG06_VALIDAR_FECHAS");
                LOGGER.warning("Fechas no validas starts");
            }

            else {
                Map<Integer, Object> serviceParams = new HashMap<Integer, Object>();
                if(null!=segregarOperacion.getInputValue()){
                        serviceParams.put(GestionarAvisoDeCobroVOImpl.SERVICE_PARAM_KEY_SEGREGAROPERACION,
                                          segregarOperacion.getInputValue());
                    }
                else{
                        serviceParams.put(GestionarAvisoDeCobroVOImpl.SERVICE_PARAM_KEY_SEGREGAROPERACION,
                                          Boolean.FALSE);
                    }
                serviceParams.put(GestionarAvisoDeCobroVOImpl.SERVICE_PARAM_KEY_IDCLIENTE,
                                  getIdCliente(gestionCobroBean));
                serviceParams.put(GestionarAvisoDeCobroVOImpl.SERVICE_PARAM_KEY_FECHAINICIAL, fi);
                serviceParams.put(GestionarAvisoDeCobroVOImpl.SERVICE_PARAM_KEY_FECHAFINAL, ff);
                serviceParams.put(GestionarAvisoDeCobroVOImpl.SERVICE_PARAM_KEY_PERIODICIDAD,
                                  periodicidad.getInputValue());
                serviceParams.put(GestionarAvisoDeCobroVOImpl.SERVICE_PARAM_KEY_TIPOGENERACION,
                                  tipoGeneracion.getInputValue());
                serviceParams.put(GestionarAvisoDeCobroVOImpl.SERVICE_PARAM_KEY_TIPOSALDOS, tipoSaldos.getInputValue());
                serviceParams.put(GestionarAvisoDeCobroVOImpl.SERVICE_PARAM_KEY_OPERACIONES, operaciones);
                serviceParams.put(GestionarAvisoDeCobroVOImpl.SERVICE_PARAM_KEY_LINEASDECREDITO, lineasDeCredito);
                serviceParams.put(GestionarAvisoDeCobroVOImpl.SERVICE_PARAM_KEY_FONDOS, fondos);
                serviceParams.put(GestionarAvisoDeCobroVOImpl.SERVICE_PARAM_KEY_LOGINUSUARIO,
                                  ADFContext.getCurrent().getSecurityContext().getUserName());
                serviceParams.put(GestionarAvisoDeCobroVOImpl.SERVICE_PARAM_KEY_NIVELDETALLE,nivelDetalle.getInputValue());

                Long idFlujo = null;
                try {
                    idFlujo = new Long(ADFUtils.getBoundAttributeValue("IdFlujo").toString());
                } catch (Exception e) {
                    LOGGER.warning("Error al obtener y convertir Id Flujo Attribute Value");
                }
                if (idFlujo != null) {
                    serviceParams.put(GestionarAvisoDeCobroVOImpl.SERVICE_PARAM_KEY_IDFLUJO, idFlujo);
                }

                try {

                    BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
                    OperationBinding operationBindingIdOp = null;
                    //metodo para revertir el guardado de la no objecion
                    operationBindingIdOp = bindingsIdOp.getOperationBinding("generaAvisoCobroMensaje");
                    operationBindingIdOp.getParamsMap().put("serviceParams", serviceParams);
                    mensajeAviso = (String) operationBindingIdOp.execute();

                    if (!operationBindingIdOp.getErrors().isEmpty()) {
                        LOGGER.severe("No se pudo iniciar el aviso de cobro " +
                                      operationBindingIdOp.getErrors().toString());
                        mensajeAviso = operationBindingIdOp.getErrors().toString();
                        JSFUtils.addFacesErrorMessage(mensajeAviso);
                    } else {
                        LOGGER.warning("mensaje: " + mensajeAviso);
                        JSFUtils.addFacesInformationMessage(mensajeAviso);
                        recargaDeDocumentos();
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getDocumentosRegionUIC());
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelDocumentosUIC());
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelAcordeonUIC());
                        recargaDeDocumentos();
                    }

                    //Recargamos la tarea
                    FacesContext fctx = FacesContext.getCurrentInstance();
                    String page = fctx.getViewRoot().getViewId();
                    ViewHandler ViewH = fctx.getApplication().getViewHandler();
                    UIViewRoot UIV = ViewH.createView(fctx, page);
                    UIV.setViewId(page);
                    fctx.setViewRoot(UIV);
                } catch (Exception e) {
                    LOGGER.warning("generarAvisoCobro(): Excepcion de servicio gestion de cobro" + e.getMessage());
                    // JSFUtils.addFacesErrorMessage("Error en servicio web Gestion cobro cliente: "+e.getMessage());
                    gestionCobroBean.setBotonAviso(Boolean.FALSE);
                }

                gestionCobroBean.setBotonAviso(Boolean.FALSE);
                AdfFacesContext.getCurrentInstance().addPartialTarget(uiBoton);
            }
        }

        if (bundleKeyErrorList.size() > 0) {
            for (String bundleKey : bundleKeyErrorList)
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
        }

        gestionCobroBean.setBotonAviso(Boolean.FALSE);
    }



    public void actualizarNivel(ValueChangeEvent actionEvent){      
          LOGGER.warning("Cambio valor atributo Nivel a: "+actionEvent.getNewValue());
          
          if(actionEvent.getNewValue() != null && actionEvent.getNewValue() != ""){                        
              gestionCobroBean = (GestionCobroBean) JSFUtils.resolveExpression(GestionCobroBean.BEAN_EXPRESSION);
              gestionCobroBean.setNivelDetalle(actionEvent.getNewValue().toString());
          }else{
              LOGGER.warning("Error, No se pudo volver a setear el atriuto nivDetalle");
          }
                    
      }

    private void recargaDeDocumentos() {
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        //metodo para recargar los docuemntos
        operationBindingIdOp = bindingsIdOp.getOperationBinding("recargaDocumentos");
        operationBindingIdOp.execute();

        if (!operationBindingIdOp.getErrors().isEmpty()) {
            LOGGER.severe("No se pudo iniciar la carga de documentos" + operationBindingIdOp.getErrors().toString());
        } else {
            LOGGER.warning("Se ejecutó correctamente la carga de documentos");
        }
    }

    private Integer getCodigoTarea(GestionCobroBean gestionCobrobean) {
        if (null != gestionCobrobean.getCodigoTarea() && gestionCobrobean.getCodigoTarea().trim().length() > 0) {
            return Integer.parseInt(gestionCobrobean.getCodigoTarea());
        }
        LOGGER.log(ADFLogger.ERROR, "<No se pudo optener el codigo tarea Codigo tarea>");
        return 0;
    }

    private Long getIdCliente(GestionCobroBean gestionCobrobean) {
        if (null != gestionCobrobean.getIdCliente() && gestionCobrobean.getIdCliente().trim().length() > 0) {
            return Long.parseLong(gestionCobrobean.getIdCliente());
        }
        LOGGER.log(ADFLogger.ERROR, "<No se pudo obtener el idCLiente>");
        return 0L;
    }

    private XMLGregorianCalendar stringToXMLGregorianCalendar(String stringDate) {
        Date tempUtildate = null;
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        try {
            tempUtildate = df.parse(stringDate);
        } catch (ParseException e) {
            tempUtildate = new Date();
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(tempUtildate);
        XMLGregorianCalendar xmlDate;
        try {
            xmlDate =
                DatatypeFactory.newInstance().newXMLGregorianCalendar(cal.get(Calendar.YEAR),
                                                                      cal.get(Calendar.MONTH) + 1,
                                                                      cal.get(Calendar.DAY_OF_MONTH),
                                                                      DatatypeConstants.FIELD_UNDEFINED,
                                                                      DatatypeConstants.FIELD_UNDEFINED,
                                                                      DatatypeConstants.FIELD_UNDEFINED,
                                                                      DatatypeConstants.FIELD_UNDEFINED,
                                                                      DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException e) {
            xmlDate = null;
            LOGGER.log(ADFLogger.ERROR, "<No se pudo convertir fecha>");
        }
        return xmlDate;
    }

    public void operacionesValueChangeListener(ValueChangeEvent actionEvent) {
        LOGGER.warning("operacionesValueChangeListener starts");
        gestionCobroBean = (GestionCobroBean) JSFUtils.resolveExpression(GestionCobroBean.BEAN_EXPRESSION);
        Object[] nuevasOperacionesSeleccionadas = (Object[]) actionEvent.getNewValue();
        gestionCobroBean.setOperacionesSeleccionadas(new ArrayList<Long>());
        if (nuevasOperacionesSeleccionadas != null) {
            for (Object operacion : nuevasOperacionesSeleccionadas) {
                gestionCobroBean.getOperacionesSeleccionadas().add((Long) operacion);
            }
            LOGGER.warning("cantidad de operaciones seleccionadas: " +
                           gestionCobroBean.getOperacionesSeleccionadas().size());
        }
    }

    public void lineasDeCreditoValueChangeListener(ValueChangeEvent actionEvent) {
        LOGGER.warning("lineasDeCreditoValueChangeListener starts");
        gestionCobroBean = (GestionCobroBean) JSFUtils.resolveExpression(GestionCobroBean.BEAN_EXPRESSION);
        Object[] nuevasLineasDeCreditoSeleccionadas = (Object[]) actionEvent.getNewValue();
        gestionCobroBean.setLineasDeCreditoSeleccionadas(new ArrayList<String>());
        if (nuevasLineasDeCreditoSeleccionadas != null) {
            for (Object lineaDeCredito : nuevasLineasDeCreditoSeleccionadas) {
                gestionCobroBean.getLineasDeCreditoSeleccionadas().add((String) lineaDeCredito);
            }
            LOGGER.warning("cantidad de lineas de credito seleccionadas: " +
                           gestionCobroBean.getLineasDeCreditoSeleccionadas().size());
        }
    }

    public void fondosValueChangeListener(ValueChangeEvent actionEvent) {
        LOGGER.warning("fondosValueChangeListener starts");
        gestionCobroBean = (GestionCobroBean) JSFUtils.resolveExpression(GestionCobroBean.BEAN_EXPRESSION);
        Object[] nuevosFondosSeleccionadas = (Object[]) actionEvent.getNewValue();
        gestionCobroBean.setFondosSeleccionados(new ArrayList<String>());
        if (nuevosFondosSeleccionadas != null) {
            for (Object lineaDeCredito : nuevosFondosSeleccionadas) {
                gestionCobroBean.getFondosSeleccionados().add((String) lineaDeCredito);
            }
            LOGGER.warning("cantidad de fondos seleccionados: " + gestionCobroBean.getFondosSeleccionados().size());
        }
    }

    public void fechaInicioValueChangeListener(ValueChangeEvent actionEvent) {
        LOGGER.warning("fechaInicioValueChangeListener starts");

        oracle.jbo.domain.Date fechaInicialSeleccionada = (oracle.jbo.domain.Date) actionEvent.getNewValue();
        AttributeBinding fechaFinal = ADFUtils.findControlBinding("FechaFinal");
        oracle.jbo.domain.Date fechaFinalSeleccionada = (oracle.jbo.domain.Date) fechaFinal.getInputValue();

        if (fechaInicialSeleccionada.compareTo(fechaFinalSeleccionada) == 1) {
            LOGGER.warning("Fecha inicio es mayor que fecha fin");
            RichInputDate inputDate = (RichInputDate) actionEvent.getComponent();
            inputDate.resetValue();
            AdfFacesContext.getCurrentInstance().addPartialTarget(inputDate);
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG06_VALIDAR_FECHAS", BUNDLE));
        }
        LOGGER.warning("fechaInicioValueChangeListener ends");
    }

    public void fechaFinalValueChangeListener(ValueChangeEvent actionEvent) {
        LOGGER.warning("fechaFinalValueChangeListener starts");

        oracle.jbo.domain.Date fechaFinalSeleccionada = (oracle.jbo.domain.Date) actionEvent.getNewValue();
        AttributeBinding fechaInicial = ADFUtils.findControlBinding("FechaInicial");
        oracle.jbo.domain.Date fechaInicialSeleccionada = (oracle.jbo.domain.Date) fechaInicial.getInputValue();

        if (fechaInicialSeleccionada.compareTo(fechaFinalSeleccionada) == 1) {
            LOGGER.warning("Fecha fin es menor que fecha inicio");
            RichInputDate inputDate = (RichInputDate) actionEvent.getComponent();
            inputDate.resetValue();
            AdfFacesContext.getCurrentInstance().addPartialTarget(inputDate);
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG06_VALIDAR_FECHAS", BUNDLE));
        }
        LOGGER.warning("fechaFinalValueChangeListener ends");
    }

    public void setUiBoton(RichButton uiBoton) {
        this.uiBoton = uiBoton;
    }

    public RichButton getUiBoton() {
        return uiBoton;
    }

    public void setDocumentosRegionUIC(RichRegion documentosRegionUIC) {
        this.documentosRegionUIC = documentosRegionUIC;
    }

    public RichRegion getDocumentosRegionUIC() {
        return documentosRegionUIC;
    }

    public void setPanelDocumentosUIC(RichShowDetailItem panelDocumentosUIC) {
        this.panelDocumentosUIC = panelDocumentosUIC;
    }

    public RichShowDetailItem getPanelDocumentosUIC() {
        return panelDocumentosUIC;
    }

    public void setPanelAcordeonUIC(RichPanelAccordion panelAcordeonUIC) {
        this.panelAcordeonUIC = panelAcordeonUIC;
    }

    public RichPanelAccordion getPanelAcordeonUIC() {
        return panelAcordeonUIC;
    }

    public Boolean cargarDocumentosAviso(Long idCliente, String idTcaTareaBpm, Long IdInstanciaTarea) {
        LOGGER.warning("insideMethodValidarComentarioCliente");
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("eliminarDocumentosAviso");

        operationBinding.getParamsMap().put("idFlujo", IdInstanciaTarea);
        LOGGER.warning("IdInstanciaTarea" + IdInstanciaTarea);
        operationBinding.getParamsMap().put("idTarea", idTcaTareaBpm);
        LOGGER.warning("idTcaTareaBpm" + idTcaTareaBpm);
        operationBinding.getParamsMap().put("idCliente", idCliente);
        LOGGER.warning("idCliente" + idCliente);

        Boolean result = (Boolean) operationBinding.execute();
        LOGGER.warning("afterOperationExecute");
        Object object = operationBinding.getResult();
        LOGGER.warning("afteroperationBindingGetResult");
        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.log(ADFLogger.ERROR, "No se pudo guardar");
            return Boolean.FALSE;
        }

        return (result);
    }
    
    public Boolean eliminarDocumentoOnbase(Integer idTarea, Long idCliente, Long numSerie){
        LOGGER.warning("Entra en eliminarDocumentoOnbase.");
        LOGGER.warning("idCliente" + idCliente);
        LOGGER.warning("idTcaTareaBpm" + idTarea);
        LOGGER.warning("IdInstanciaTarea" + numSerie);
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        
        Boolean esEliminado = Boolean.FALSE;
        
        try{
            operationBinding = bindings.getOperationBinding("eliminaDocumentoAdjunto");
            operationBinding.getParamsMap().put("idTarea", idTarea);
            operationBinding.getParamsMap().put("idCliente", idCliente);
            operationBinding.getParamsMap().put("numSerie", numSerie);
            
            esEliminado = (Boolean)operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                JSFUtils.addFacesErrorMessage("ERROR : " + operationBinding.getErrors());
            }else{
                LOGGER.warning("Se eliminaron los documentos correctamente.");
                if(!esEliminado){
                    JSFUtils.addFacesErrorMessage("Ocurrio un error al borrar el documento.");  
                }
            }
        }catch(Exception e){
            LOGGER.warning("Error en eliminarDocumentoOnbase.", e);
        }
        return esEliminado;
    }
}
