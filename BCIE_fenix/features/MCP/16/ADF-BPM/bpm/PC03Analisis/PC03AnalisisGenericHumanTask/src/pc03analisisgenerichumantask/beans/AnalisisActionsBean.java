package pc03analisisgenerichumantask.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class AnalisisActionsBean extends FenixActionBean {
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(AnalisisActionsBean.class);
    public static final String BUNDLE = "pc03analisisgenerichumantask.PC03AnalisisGenericHumanTaskBundle";
    
    public static final String VALIDAR_ESTADOS_TCC_OPER = "validarEstadosTCCAnalisis";
    
    private String urlProyectoResolucion = getStringFromBundle("URL_PROYECTO_RESOLUCION");

    private RichPopup popupConfFinalizarTarea;
    private RichPopup popupConfMasInfo;
    private RichPopup popupConfReformular;
    private RichPopup popupConfModOpinionJur;
    private RichPopup popupConfRevProyecto;
    private RichPopup popupConfCancelarOper;
    private RichPopup popupConfArgumentarSCR;
    private RichPopup popupConfirmaCalificacionNegativa;
    private RichSelectOneRadio tipoOpinion;
    private Boolean validadoASJUR;
    private Boolean validadoJefatura;
    private Boolean ValorOpinion;
   // SPS | 07/01/2020 | Se anexa para atender T14286
    /* ==============      CONSTANTES  CA     ============= */
    private static final String ID_PANELCMP = "pgCAEditable";
    private static final String ID_SECTOR = "socSCTREd";
    private static final String ID_APORTE = "socAPRTEd";
    private static final String ID_CATEGORIA = "socCATEd";
    private static final String ID_SUBCATEGORIA = "socSUBCEd";
    private static final String ID_PANELPADRE = "pglCmpCAPE";
    private static final String ID_LBL_SECTOR = "lblSector1";
    private ArrayList<String> erroresCA;
    //---------------------------------------------------------
    public AnalisisActionsBean() {
        super();
        validadoASJUR = Boolean.FALSE;
        validadoJefatura = Boolean.FALSE;
       /*
        try
        {
            AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");
            if(analisisBean.getOpinionTipo()==null){
                    getTipoOpinion().setValue("");
                }
            else{
            getTipoOpinion().setValue(analisisBean.getOpinionTipo());
            }
                   getTipoOpinion().setValue(analisisBean.getOpinionTipo());
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();    
        }*/
        
    }

    public void setPopupConfFinalizarTarea(RichPopup popupConfFinalizarTarea) {
        this.popupConfFinalizarTarea = popupConfFinalizarTarea;
    }

    public RichPopup getPopupConfFinalizarTarea() {
        return popupConfFinalizarTarea;
    }

    public void setPopupConfMasInfo(RichPopup popupConfMasInfo) {
        this.popupConfMasInfo = popupConfMasInfo;
    }

    public RichPopup getPopupConfMasInfo() {
        return popupConfMasInfo;
    }

    public void setPopupConfReformular(RichPopup popupConfReformular) {
        this.popupConfReformular = popupConfReformular;
    }

    public RichPopup getPopupConfReformular() {
        return popupConfReformular;
    }

    public void setPopupConfModOpinionJur(RichPopup popupConfModOpinionJur) {
        this.popupConfModOpinionJur = popupConfModOpinionJur;
    }

    public RichPopup getPopupConfModOpinionJur() {
        return popupConfModOpinionJur;
    }

    public void setPopupConfRevProyecto(RichPopup popupConfRevProyecto) {
        this.popupConfRevProyecto = popupConfRevProyecto;
    }

    public RichPopup getPopupConfRevProyecto() {
        return popupConfRevProyecto;
    }

    public void setPopupConfCancelarOper(RichPopup popupConfCancelarOper) {
        this.popupConfCancelarOper = popupConfCancelarOper;
    }

    public RichPopup getPopupConfCancelarOper() {
        return popupConfCancelarOper;
    }

    public void setPopupConfArgumentarSCR(RichPopup popupConfArgumentarSCR) {
        this.popupConfArgumentarSCR = popupConfArgumentarSCR;
    }

    public RichPopup getPopupConfArgumentarSCR() {
        return popupConfArgumentarSCR;
    }

    public void setValidadoJefatura(Boolean validadoJefatura) {
        this.validadoJefatura = validadoJefatura;
    }

    public Boolean getValidadoJefatura() {
        return validadoJefatura;
    }

    public void setTipoOpinion(RichSelectOneRadio tipoOpinion) {
        this.tipoOpinion = tipoOpinion;
    }

    public RichSelectOneRadio getTipoOpinion() {
        return tipoOpinion;
    }

    public void setValidadoASJUR(Boolean validadoASJUR) {
        this.validadoASJUR = validadoASJUR;
    }

    public Boolean getValidadoASJUR() {
        return validadoASJUR;
    }

    public Boolean getEsLineaGlobalCredito() {

        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");

        if (null != analisisBean) {
            return analisisBean.getEsLineaGlobalCreditoIFI();
        }

        return Boolean.FALSE;
    }

    public Boolean validarProductoDocumento() {
        Integer documentosValidados = 0;
        Integer documentosPorValidar = 0;

        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");
        analisisBean.setReqIBCIE(obtenerColumna(analisisBean.getIdProducto(), FenixConstants.PC03_IBCIE));
        analisisBean.setReqSiemas(obtenerColumna(analisisBean.getIdProducto(), FenixConstants.PC03_SIEMAS));

        if (analisisBean.getReqOpinionJuridica()) {
            documentosValidados = documentosValidados + 1;
            if (validateDocumento(getIdOperacion(), FenixConstants.TD_OPINIONJURIDICA)) {
                documentosPorValidar = documentosPorValidar + 1;
            }
        }
        if (analisisBean.getRequiereSCR()) {
            //documentosValidados = documentosValidados + 1;
            //if (validateDocumento(getIdOperacion(), FenixConstants.TD_BORRADORREPCALIFICACION)) {

                //documentosPorValidar = documentosPorValidar + 1;
            //}
        }
        if (analisisBean.getReqIBCIE()) {
            documentosValidados = documentosValidados + 1;
            if (validateDocumento(getIdOperacion(), FenixConstants.DOCUMENTO_I_BCIE)) {

                documentosPorValidar = documentosPorValidar + 1;
            }
        }
        if (analisisBean.getReqSiemas()) {
            documentosValidados = documentosValidados + 1;
            if (validateDocumento(getIdOperacion(), FenixConstants.DOCUMENTO_SIEMAS)) {

                documentosPorValidar = documentosPorValidar + 1;
            }
        }

        if (documentosValidados == documentosPorValidar) {
            return true;
        }
        return false;
    }

    public String documentoFaltante() {
        String faltante = "Falta el(los) documento(s)";
        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");

        if (analisisBean.getReqOpinionJuridica()) {
            if (!validateDocumento(getIdOperacion(), FenixConstants.TD_OPINIONJURIDICA)) {
                faltante = faltante.concat(" Opinión juridica,");
                ;
            }
        }
        if (analisisBean.getRequiereSCR()) {
            //if (!validateDocumento(getIdOperacion(), FenixConstants.TD_BORRADORREPCALIFICACION)) {
              //  faltante = faltante.concat(" Borrador de calificación inicial de riesgo,");
            //}
        }
        if (analisisBean.getReqIBCIE()) {
            if (!validateDocumento(getIdOperacion(), FenixConstants.DOCUMENTO_I_BCIE)) {
                faltante = faltante.concat(" I-BCIE,");
            }
        }
        if (analisisBean.getReqSiemas()) {
            if (!validateDocumento(getIdOperacion(), FenixConstants.DOCUMENTO_SIEMAS)) {
                faltante = faltante.concat(" SIEMAS,");
            }
        }
        faltante = faltante.concat(" cuando apliquen, de acuerdo al tipo de análisis de la operación");
        return faltante;
    }

    public Boolean obtenerColumna(Integer producto, Integer columna) {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("obtenerCampo");
        operationBinding.getParamsMap().put("idProducto", producto);
        operationBinding.getParamsMap().put("columna", columna);
        Boolean result = (Boolean) operationBinding.execute();
        return result;

    }

    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (resourceBundle != null) {
            txt = resourceBundle.getString(psKey);
        }

        return txt;
    }

    private Integer getCodigoTarea() {
        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");

        if (null != analisisBean.getCodigoTarea() && analisisBean.getCodigoTarea().trim().length() > 0) {
            return Integer.parseInt(analisisBean.getCodigoTarea());
        }

        return 0;
    }

    private Long getIdOperacion() {
        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");

        if (null != analisisBean.getIdOperacion() && analisisBean.getIdOperacion().trim().length() > 0) {
            return Long.parseLong(analisisBean.getIdOperacion());
        }

        return 0L;
    }
    
    public Boolean validaComsiones() {
         BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarComisionesDispensadasPagadas");
        operationBinding.getParamsMap().put("estadoDispensado", FenixConstants.ESTADO_COMISION_DISPENSADO);
        operationBinding.getParamsMap().put("estadoPagado", FenixConstants.ESTADO_COMISION_PAGADO);
        operationBinding.getParamsMap().put("momentoActual", FenixConstants.MOMENTO_COBRO_APROBACION);

        Boolean result = (Boolean) operationBinding.execute();
        return result;
    }

    private boolean getTipoOpinionValue() {
        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");
        
        setValorOpinion(Boolean.FALSE);
        if (null == analisisBean.getOpinionTipo()) {
            setValorOpinion(Boolean.TRUE);
        }
        if(null != analisisBean.getOpinionTipo() && analisisBean.getOpinionTipo().equalsIgnoreCase("1")){
            DCIteratorBinding voMotivos= ADFUtils.getDCBindingContainer().findIteratorBinding("TcaMotivoIterator");
            voMotivos.executeQuery();
        }
        return (null == analisisBean.getOpinionTipo() ? false :
                analisisBean.getOpinionTipo().equalsIgnoreCase("0") ? false : true);
    }

    private Boolean esValidoSCRoPerspectivaOtir(int tarea) {
        LOGGER.warning("Entra en esValidoSCRoPerspectivaOtir.");
        Boolean valido = Boolean.TRUE;
        try {
            Integer scr = (Integer) JSFUtils.resolveExpression("#{bindings.SCR.inputValue}");
            Integer perpectiva = (Integer) JSFUtils.resolveExpression("#{bindings.Prespectiva.inputValue}");
            String tir = (String) JSFUtils.resolveExpression("#{bindings.TIR.inputValue}");
            LOGGER.warning("scr : " + scr);
            LOGGER.warning("tarea : " + tarea);
            if(tarea == FenixConstants.PC03_ELABORARCALIFICACIONIFI) {
                valido = ((null != scr && scr > 0) && (null != perpectiva && perpectiva > 0));
            } else {
                valido = ( ((null != scr && scr > 0) && (null != perpectiva && perpectiva > 0))
                    && esValidoTIR());
            }
        } catch(java.lang.ClassCastException ex) {
            valido = Boolean.FALSE;
        }
        LOGGER.warning("valido : " + valido);
        return valido;
    }
    
    private Boolean esValidoTIR() {
        LOGGER.warning("Inside esValidoTIR");
        
        Boolean valido = Boolean.TRUE;
        try {
            String tir = (String) JSFUtils.resolveExpression("#{bindings.TIR.inputValue}");
            System.out.println("Pasó TIR");
            valido = ((null != tir) && tir.length() > 0);
        } catch(java.lang.ClassCastException ex) {
            valido = Boolean.FALSE;
        }
        return valido;
    }
    // SPS | 07/01/2020 | Se modifica para atender T14286 
    public String invokeFinalizarTarea() {
        if (validacionesCA()) {
        LOGGER.warning("*Inf, Analisis invokeFinalizarTarea");
        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");
        analisisBean.setReqOpinion(Boolean.TRUE);
        String bundleKeyError = "";
        String accion = "FinalizarTarea";
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        Boolean isValidDocumentos = Boolean.FALSE;
        boolean messageAdded = false;
        
        int codigoTarea = getCodigoTarea();
        
        LOGGER.warning("codigoTarea: " + codigoTarea);
        
        if(codigoTarea != FenixConstants.PC03_ELABORARDICTAMENAC && codigoTarea != FenixConstants.PC03_ELABORARDICTAMENEAT)
        {
            isValidDocumentos = validarDocumentosTarea(getIdOperacion(), codigoTarea,accion); //JURBINA@20092019 validar los documentos por operación, tarea y accion 
        }
        switch (codigoTarea) {

        case FenixConstants.PC03_ANALIZARDICTAMEN:
            LOGGER.warning("PC03_ANALIZARDICTAMEN");
            Boolean validaNoObjecion=validaNoObjecion();
            Boolean validaComisiones=validaComsiones();
            isValidFinalizarTarea = validaNoObjecion && validaComisiones;
            if (!validaNoObjecion) {
            bundleKeyError = "CONFIRMA_NO_OBJECION";
            }
            else{
            if(!validaComisiones){
                    bundleKeyError = "MSG_COMISIONES";
                }
            }
            
            isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos; //JURBINA@20092019 Si las validaciones previas de la tarea son validas y los documentos son validos la tarea es valida
            
            if(!isValidFinalizarTarea){
                    messageAdded = true;
                }
            
            analisisBean.setExisteCondicion(existenCondicionesPorIdOperacion());
            if(analisisBean.getExisteCondicion()){
                //si existe condcion es true mostrar el mensaje normal de finalizar tarea
                LOGGER.warning("Existen condciones mostrar el mensaje normal de finalizar tarea");
            }else{
                // se existe condicion es false mostrar el mensaje de finalizar tarea sin condicion
                LOGGER.warning("No existen condiciones, mostrar finalizar tarea sin condiciones");
            }
            
            break;

        case FenixConstants.PC03_APROBARCALIFICACION:
            LOGGER.warning("PC03_APROBARCALIFICACION");
            /*isValidFinalizarTarea = validateDocumento(getIdOperacion(), FenixConstants.TD_REPCALIFICACION);
            if(!isValidFinalizarTarea){
                bundleKeyError = "MSG_DCTM_REPORTECALIFICACION";
            }*/
            isValidFinalizarTarea = Boolean.TRUE;
            isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;

            break;

        case FenixConstants.PC03_REVISARDOCUMENTOS:
            LOGGER.warning("PC03_REVISARDOCUMENTOS");
            isValidFinalizarTarea = Boolean.TRUE;
            isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
            break;

        case FenixConstants.PC03_ELABORARCALIFICACION:
            LOGGER.warning("PC03_ELABORARCALIFICACION/PC03_ELABORARCALIFICACION");
            //isValidFinalizarTarea = validateDocumento(getIdOperacion(), FenixConstants.TD_BORRADORREPCALIFICACION);
            isValidFinalizarTarea = Boolean.TRUE;
            isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
            if (!isValidFinalizarTarea) {
                //bundleKeyError = "MSG_DCTM_BORRADORCALIFICACION";
            } else {
                isValidFinalizarTarea = esValidoSCRoPerspectivaOtir(FenixConstants.PC03_ELABORARCALIFICACION);
                if (!isValidFinalizarTarea) {
                    bundleKeyError = "MSG_COMPLETAR_INFO";
                }
            }
            break;
        case FenixConstants.PC03_ELABORARCALIFICACIONIFI:
            LOGGER.warning("PC03_ELABORARCALIFICACION/PC03_ELABORARCALIFICACIONIFI");
            LOGGER.warning("PC03_ELABORARCALIFICACION/PC03_ELABORARCALIFICACIONIFI");
            //isValidFinalizarTarea = validateDocumento(getIdOperacion(), FenixConstants.TD_BORRADORREPCALIFICACION);
            isValidFinalizarTarea = Boolean.TRUE;
            isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
            if (!isValidFinalizarTarea) {
                //bundleKeyError = "MSG_DCTM_BORRADORCALIFICACION";
            } else {
                isValidFinalizarTarea = esValidoSCRoPerspectivaOtir(FenixConstants.PC03_ELABORARCALIFICACIONIFI);
                if (!isValidFinalizarTarea) {
                    bundleKeyError = "MSG_COMPLETAR_INFO";
                }
            }
            break;
        case FenixConstants.PC03_RECOPILARINFO:
            LOGGER.warning("PC03_RECOPILARINFO");
            // Incidencia FNXII-3113: Se elimina validación de obligatoriedad de Comentarios
            isValidFinalizarTarea = Boolean.TRUE;
            isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
            break;
        case FenixConstants.PC03_ELABORAROPINIONJUR:
        case FenixConstants.PC03_ELABORAROPINIONJURT:
            LOGGER.warning("PC03_ELABORAROPINIONJUR");
            //Validate that the document required fot this task is already in DB
            //isValidFinalizarTarea = validateDocumento(getIdOperacion(), FenixConstants.TD_OPINIONJURIDICA);
            //bundleKeyError = "MSG_DCMT_OPINIONJURIDICA";
            isValidFinalizarTarea = Boolean.TRUE;
            isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
            break;
        case FenixConstants.PC03_REVISARPROYECTO:
            LOGGER.warning("PC03_REVISARPROYECTO");
            Boolean documentoAdjunto = Boolean.FALSE;
            Boolean comentario = Boolean.FALSE;

            //documentoAdjunto = validateDocumento(getIdOperacion(), FenixConstants.TD_PROYECTORESOLUCION);
            documentoAdjunto = isValidDocumentos;
            comentario = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());

            if (documentoAdjunto || comentario) {
                isValidFinalizarTarea = Boolean.TRUE;
                analisisBean.setExisteCondicion(existenCondicionesPorIdOperacion());
                if(analisisBean.getExisteCondicion()){
                    //si existe condcion es true mostrar el mensaje normal de finalizar tarea
                    LOGGER.warning("Existen condciones mostrar el mensaje normal de finalizar tarea");
                }else{
                    // se existe condicion es false mostrar el mensaje de finalizar tarea sin condicion
                    LOGGER.warning("No existen condiciones, mostrar finalizar tarea sin condiciones");
                }
                
            }

            bundleKeyError = "MSG_DCMT_REVISAR_PROYECTO";
            break;

        case FenixConstants.PC03_ELABORARDICTAMENEAT:
        case FenixConstants.PC03_ELABORARDICTAMENAC:
            
            LOGGER.warning("PC03_ELABORARDICTAMENAC");
            // De ser negativa la opinión no hay validación ASJUR
            // Valida TIR cuando se requiere
            LOGGER.warning(" BanderaTIR: "+analisisBean.getBanderaTIR());
            
            Integer requiereTir = null;
            try{
                requiereTir = (Integer)ADFUtils.getBoundAttributeValue("RequiereTir");
                LOGGER.warning(" requiereTir: "+requiereTir);
            }catch(Exception e){
                LOGGER.warning(" ha ocurrido un error al intentar validar  el TIR: ",e);
                JSFUtils.addFacesErrorMessage(" ha ocurrido un error al intentar validar  el TIR");
            }
                        
            Boolean ValidarTir = Boolean.FALSE;
            
            if(requiereTir != null){                 
                  ValidarTir = (requiereTir.compareTo(1) == 0) ?  Boolean.TRUE: Boolean.FALSE;
            }else{
                JSFUtils.addFacesErrorMessage("Error al evaluar TIR");
            }
            
            if (codigoTarea == FenixConstants.PC03_ELABORARDICTAMENAC) {
                if(ValidarTir && !esValidoTIR()) {
                    LOGGER.warning("validacion: se requiere TIR");
                    messageAdded = true;
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_TIR_REQ"));
                }  
            }
            
            setValidadoASJUR(analisisBean.getCuentaConProyectoResolucion());
            //validadoASJUR = analisisBean.getCuentaConProyectoResolucion();
            boolean opinionPositiva = getTipoOpinionValue();
            messageAdded = false;
            LOGGER.warning("isRiesgoCredito: " + analisisBean.getIsRiesgoCredito());
            if (analisisBean.getIsRiesgoCredito()) {
                //validadoASJUR=getValidadoJefatura();
                isValidFinalizarTarea = analisisBean.getJefatura();
                if (!isValidFinalizarTarea) {
                    messageAdded = true;
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_VALIDACIONJEFATURA"));
                }
            }
            System.out.println("Valor opinion" + getValorOpinion());
            if (getValorOpinion()) {
                messageAdded = true;
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SIN_OPINION"));
            } else {
                if (opinionPositiva) {
                    
                    //isValidFinalizarTarea = validateDocumento(getIdOperacion(), FenixConstants.TD_DICTAMEN);
                    
                    isValidDocumentos = validarDocumentosTarea(getIdOperacion(), codigoTarea,accion);
                    isValidFinalizarTarea = isValidDocumentos;
                    
                    if (!isValidFinalizarTarea) {
                        messageAdded = true;
                        //JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_DCTM_DICTAMEN"));
                    }

                    /*isValidFinalizarTarea = validateDocumento(getIdOperacion(), FenixConstants.TD_PROYECTORESOLUCION);
                    if (!isValidFinalizarTarea) {
                        messageAdded = true;
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_DCTM_PROYECTORESOLUCION"));
                    }*/

                    isValidFinalizarTarea = getValidadoASJUR();
                    if (!isValidFinalizarTarea) {
                        messageAdded = true;
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ASJUR_REQ"));
                    }

                    isValidFinalizarTarea = validarProductoDocumento();

                    if (!isValidFinalizarTarea) {
                        messageAdded = true;
                        JSFUtils.addFacesErrorMessage(documentoFaltante());
                    }

                } else { // Opinion Negatva
                    List<Integer> motivosList = getCheckedMotivos();

                    isValidFinalizarTarea = (motivosList.size() > 0) ? true : false;
                    if (!isValidFinalizarTarea) {
                        messageAdded = true;
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SIN_MOTIVOS"));
                    }

                    isValidFinalizarTarea = validarProductoDocumento();

                    if (!isValidFinalizarTarea) {
                        messageAdded = true;
                        JSFUtils.addFacesErrorMessage(documentoFaltante());
                    }

                }
                
                //Valida TCC
                if(isValidFinalizarTarea){
                    isValidFinalizarTarea = validarTCC();
                }
            }
            if (messageAdded) {
                isValidFinalizarTarea = Boolean.FALSE;
                bundleKeyError = null;
            }

            analisisBean.setExisteCondicion(existenCondicionesPorIdOperacion());
            if(analisisBean.getExisteCondicion()){
                //si existe condcion es true mostrar el mensaje normal de finalizar tarea
                LOGGER.warning("Existen condciones mostrar el mensaje normal de finalizar tarea");
            }else{
                // se existe condicion es false mostrar el mensaje de finalizar tarea sin condicion
                LOGGER.warning("No existen condiciones, mostrar finalizar tarea sin condiciones");
            }
            
            break;

        case FenixConstants.PC03_VALIDARRESULTADO:
            LOGGER.warning("PC03_VALIDARRESULTADO");
            isValidFinalizarTarea = Boolean.TRUE;
            isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
            break;
        
        case FenixConstants.PC03_ERROR_PROPAGAR_SCR:
            LOGGER.warning("PC03_ERROR_PROPAGAR_SCR");
            isValidFinalizarTarea = Boolean.TRUE;
            isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
            break;        

        default:
            LOGGER.log(ADFLogger.ERROR, "invokeFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            isValidFinalizarTarea = Boolean.FALSE;
            break;
        }

        if (!isValidFinalizarTarea) {
            if (bundleKeyError != null && !bundleKeyError.equals(""))
                JSFUtils.addFacesErrorMessage(getStringFromBundle(bundleKeyError));

        } else {
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            popupConfFinalizarTarea.show(popupHints);
        }
        }else{
            //Abrir popup
            LOGGER.log(ADFLogger.WARNING, "No se ejecutan acciones del metodo invokeFinalizarTarea por validaciones de CA");
            if (!erroresCA.isEmpty()){
                for (String msg : erroresCA) {
                    JSFUtils.addFacesErrorMessage(msg);
                }
            }  
        }
        return null;
    }
    
    
    
    public Boolean requiereTir(Long idOperacion){
      LOGGER.warning("inicia metodo requiereTir en Bean");
      Integer requiere = null;
      Boolean respuesta = Boolean.FALSE;
      
      if(idOperacion != null){
    
                try{
                       BindingContainer binding = ADFUtils.getBindingContainer();
                       OperationBinding operBinding = binding.getOperationBinding("requiereTir");
                       operBinding.getParamsMap().put("idOperacion", idOperacion);
                       operBinding.execute();
                          
                    if(!operBinding.getErrors().isEmpty()){
                       LOGGER.warning("OperationBinding requiereTir devuelve errores"
                                                                                   +operBinding.getErrors().toString());
                       JSFUtils.addFacesErrorMessage("Error, ->"+operBinding.getErrors().toString());  
                    }else{
                        
                        if((Integer)operBinding.getResult() != null ){
                            
                            requiere = (Integer)operBinding.getResult();                    
                            respuesta = (requiere.compareTo(1) == 0) ? Boolean.TRUE : Boolean.FALSE;
                            
                        }else{
                            JSFUtils.addFacesErrorMessage("Error al evaluar el campo TIR");  
                        }
                        
                    }
                      
                }catch(Exception e){
                   LOGGER.warning("Error metodo requiereTir -> ",e);
                }
        }else{
            LOGGER.warning("Error al evaluar TIR no se pudo recuperar el IdOperacion");
            JSFUtils.addFacesErrorMessage("Error al evaluar TIR no se pudo recuperar el IdOperacion");  
        }
      
      LOGGER.warning("requiereTIR: " + respuesta);
      LOGGER.warning("termina metodo requiereTir en Bean");
      
      return respuesta;
    }
    
    /**
     * Ejecuta la validacion de elementos TCC
     * @return devuelve valor booleano, true si la validacion es positiva o false en caso contrario
     */
    @SuppressWarnings("unchecked")
    public boolean validarTCC(){
        
        boolean esValido = false;
        OperationBinding oper = null;
        
        Long idOperacion = null;
        try{
            if(ADFUtils.getBoundAttributeValue("CodigoOperacion") != null){
                idOperacion = Long.valueOf(ADFUtils.getBoundAttributeValue("CodigoOperacion").toString());    
            }else{
                LOGGER.severe("Error el Codigo de Operacion es NULL");
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener el codigo de operacion");
        }
        
        Map params = null;
        if(idOperacion != null){
            params = new HashMap();
            params.put("idOperacion", idOperacion);
        }
        
        BindingContainer bindings = getBindings();
        oper = bindings.getOperationBinding(VALIDAR_ESTADOS_TCC_OPER);
        if(oper != null){
            oper.getParamsMap().putAll(params);
            oper.execute();
            if(oper.getErrors().isEmpty()){
                
                if(oper.getResult() != null){
                    try{
                        esValido = Boolean.valueOf(oper.getResult().toString());    
                    }catch(Exception e){
                        LOGGER.severe("Error al obtener el resultado de la Operacion: " + 
                                      VALIDAR_ESTADOS_TCC_OPER);
                    }
                    
                    if(esValido){
                        LOGGER.warning("El resultado de la validacion de TCC es positiva");    
                    }else{
                        LOGGER.severe("El resultado de la validacion de TCC es negativa");
                        String msg = getStringFromBundle("VALIDAR_TCC_NEGATIVO");
                        JSFUtils.addFacesErrorMessage(msg);
                    }
                }else{
                    String msg = getStringFromBundle("VALIDAR_TCC_ERROR");
                    JSFUtils.addFacesErrorMessage(msg);
                }
            }else{
                LOGGER.severe("Error al ejecutar la operacion");
                JSFUtils.addFacesErrorMessage(oper.getErrors().toString());
            }
        }else{
            LOGGER.severe("Error operacion no definida");
            String msg = getStringFromBundle("VALIDAR_TCC_ERROR");
            JSFUtils.addFacesErrorMessage(msg);
        }
        
        return esValido;
    }

    public Boolean validaNoObjecion(){
        Boolean resultado=Boolean.FALSE;
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("consultarDeclaracionJurada");
            Object result = operationBinding.execute();
            AttributeBinding objecion;
            objecion = ADFUtils.findControlBinding("NombreNoObjecion");
            String validaNombreObjecion = (String) objecion.getInputValue();
            
            objecion = ADFUtils.findControlBinding("ObjecionNo");
            Boolean validaObjecion=Boolean.FALSE;
            if((Boolean)objecion.getInputValue()!=null){
                    validaObjecion=(Boolean)objecion.getInputValue();
                }
            LOGGER.warning("Objecion: " + validaObjecion);
            if (!validaObjecion && validaNombreObjecion.equalsIgnoreCase("No")) {
                                    resultado=Boolean.FALSE;
            } else {
                resultado=Boolean.TRUE;
            }
            return resultado;
        }

    public String invokeMasInformacion() {
        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");
        analisisBean.setReqOpinion(Boolean.TRUE);

        String bundleKeyError = "";
        Boolean isValidMasInformacion = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {
        case FenixConstants.PC03_REVISARDOCUMENTOS:
            LOGGER.fine("PC03_REVISARDOCUMENTOS");
            isValidMasInformacion =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            bundleKeyError = "MSG_NO_COMMENTS";
            break;

        case FenixConstants.PC03_ELABORAROPINIONJUR:
            LOGGER.fine("PC03_ELABORAROPINIONJUR");
            isValidMasInformacion =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            //Se aplica nuevo mensaje de validacion, atiende incidencia FNXII-3441
            bundleKeyError = "msg_validacion_comentario_requerido"; 
            break;
        
        case FenixConstants.PC03_ELABORAROPINIONJURT:
            LOGGER.fine("PC03_ELABORAROPINIONJURT");
            isValidMasInformacion =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            bundleKeyError = "MSG_NO_OBSERVACIONES";
            break;

        case FenixConstants.PC03_ELABORARDICTAMENEAT:
        case FenixConstants.PC03_ELABORARDICTAMENAC:
            LOGGER.fine("PC03_ELABORARDICTAMENEAT");
            isValidMasInformacion =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            bundleKeyError = "MSG_NO_OBSERVACIONES2";
            break;
        case FenixConstants.PC03_ANALIZARDICTAMEN:
            LOGGER.fine("PC03_ELABORARDICTAMENEAT");
            isValidMasInformacion = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            bundleKeyError = "MSG_NO_OBSERVACIONES2";
            break;
        default:
            LOGGER.log(ADFLogger.ERROR, "invokeMasInformacion(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }

        if (!isValidMasInformacion) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle(bundleKeyError));

        } else {
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            popupConfMasInfo.show(popupHints);
        }

        return null;
    }

    public String invokeReformular() {
        String bundleKeyError = "";
        Boolean isValidReformular = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {
        case FenixConstants.PC03_EVALUARACCIONSEGUIR:
            LOGGER.fine("PC03_EVALUARACCIONSEGUIR");
            isValidReformular = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            bundleKeyError = "MSG_NO_COMMENTS";

            break;

        case FenixConstants.PC03_ANALIZARDICTAMEN:
            LOGGER.fine("PC03_ANALIZARDICTAMEN");
            isValidReformular = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            bundleKeyError = "MSG_NO_OBSERVACIONES2";
            break;

        case FenixConstants.PC03_REVISARDOCUMENTOS:
            LOGGER.fine("PC03_REVISARDOCUMENTOS");
            isValidReformular = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            bundleKeyError = "MSG_NO_COMMENTS";
            break;

        default:
            LOGGER.log(ADFLogger.ERROR, "invokeReformular(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }

        if (!isValidReformular) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle(bundleKeyError));

        } else {
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            popupConfReformular.show(popupHints);
        }

        return null;
    }

    public String invokeModificarOpinionJur() {
        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");
        analisisBean.setReqOpinion(Boolean.FALSE);
        String bundleKeyError = "";
        Boolean isValidModificar = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {
        case FenixConstants.PC03_ANALIZARDICTAMEN:
            LOGGER.fine("PC03_ANALIZARDICTAMEN");
            isValidModificar = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            bundleKeyError = "MSG_NO_OBSERVACIONES2";
            break;

        case FenixConstants.PC03_APROBARCALIFICACION:
            LOGGER.fine("PC03_APROBARCALIFICACION");
            isValidModificar = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            bundleKeyError = "MSG_NO_OBSERVACIONES2";
            break;

        case FenixConstants.PC03_ELABORARDICTAMENAC:
        case FenixConstants.PC03_ELABORARDICTAMENEAT:
            LOGGER.fine("PC03_ELABORARDICTAMEN");
            isValidModificar = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            bundleKeyError = "MSG_NO_OBSERVACIONES2";

            break;
        case FenixConstants.PC03_VALIDARRESULTADO:
            LOGGER.fine("PC03_VALIDARRESULTADO");
            isValidModificar = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            bundleKeyError = "MSG_NO_OBSERVACIONES2";
            break;
        default:
            LOGGER.log(ADFLogger.ERROR, "invokeModificarOpinionJur(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }

        if (!isValidModificar) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle(bundleKeyError));

        } else {
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            popupConfModOpinionJur.show(popupHints);
        }


        return null;
    }

    public String invokeRevisarProyecto() {
        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");
        String bundleKeyError = "";
        Boolean isValidRevisarProy = Boolean.FALSE;
        Boolean isValidDocumentos = Boolean.FALSE;
        String accion = "RevisarProyectoResolucion";
        int codigoTarea = getCodigoTarea();
        boolean messageAdded = false;
        
        switch (codigoTarea) {
        case FenixConstants.PC03_ELABORARDICTAMENEAT: //
        case FenixConstants.PC03_ELABORARDICTAMENAC: //
            LOGGER.warning("PC03_ELABORARDICTAMENEAT");

            boolean opinionPositiva = getTipoOpinionValue();
            messageAdded = false;

            // Valida TIR cuando se requiere           
            Integer requiereTir = null;
            try{
                requiereTir = (Integer)ADFUtils.getBoundAttributeValue("RequiereTir");
                LOGGER.warning(" requiereTir: "+requiereTir);
            }catch(Exception e){
                LOGGER.warning(" ha ocurrido un error al intentar validar  el TIR: ",e);
                JSFUtils.addFacesErrorMessage(" ha ocurrido un error al intentar validar  el TIR");
            }
                        
            Boolean ValidarTir = Boolean.FALSE;
            
            if(requiereTir != null){                 
                  ValidarTir = (requiereTir.compareTo(1) == 0) ?  Boolean.TRUE: Boolean.FALSE;
            }else{
                JSFUtils.addFacesErrorMessage("Error al evaluar TIR");
            }           
            
            if (codigoTarea == FenixConstants.PC03_ELABORARDICTAMENAC) {
                if(ValidarTir && !esValidoTIR()) {
                    LOGGER.warning("validacion: se requiere TIR");
                    messageAdded = true;
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_TIR_REQ"));
                }
            }
            
            if (analisisBean.getIsRiesgoCredito()) {    
                isValidRevisarProy = Boolean.TRUE; //getValidadoJefatura(); se elimina validacion FNXII-2086
                if (!isValidRevisarProy) {
                    messageAdded = true;
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_VALIDACIONJEFATURA"));
                }
            }

            if (getValorOpinion()) {
                messageAdded = true;
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SIN_OPINION"));
            } else {
                if (opinionPositiva) {
                 
                    //isValidRevisarProy = validateDocumento(getIdOperacion(), FenixConstants.TD_DICTAMEN);
                    isValidDocumentos = validarDocumentosTarea(getIdOperacion(), codigoTarea,accion); //JURBINA@20092019 validar los documentos por operación, tarea y accion 
                    isValidRevisarProy = isValidDocumentos;

                    if (!isValidRevisarProy) {
                        messageAdded = true;
                        //JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_DCTM_DICTAMEN"));
                    }

                    
                /*isValidRevisarProy = validateDocumento(getIdOperacion(), FenixConstants.TD_BORRADORPROYECTORESOLUCION);

                if (!isValidRevisarProy) {
                    messageAdded = true;
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_DCTM_BORRADORPROYECTORESOLUCION"));

                }*/
                } else { // Opinion Negatva
                    /*
                    List<Integer> motivosList = getCheckedMotivos();

                    isValidRevisarProy = (motivosList.size() > 0) ? true : false;
                    if (!isValidRevisarProy) {
                        messageAdded = true;
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SIN_MOTIVOS"));
                    }

                    isValidRevisarProy = validarProductoDocumento();

                    if (!isValidRevisarProy) {
                        messageAdded = true;
                        JSFUtils.addFacesErrorMessage(documentoFaltante());

                    }
*/
                    messageAdded = true;
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_OPINION_NEGATIVA"));

                }
            }

            if (messageAdded) {
                isValidRevisarProy = Boolean.FALSE;
                bundleKeyError = null;
            }

            break;

        default:
            LOGGER.log(ADFLogger.ERROR, "invokeRevisarProyecto(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }

        if (!isValidRevisarProy) {
            if (bundleKeyError != null && !bundleKeyError.equals(""))
                JSFUtils.addFacesErrorMessage(getStringFromBundle(bundleKeyError));
        } else {
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            popupConfRevProyecto.show(popupHints);
        }

        return null;
    }

    public String invokeCancelarOperacion() {
        String bundleKeyError = "";
        Boolean isValidCancelar = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {
        case FenixConstants.PC03_EVALUARACCIONSEGUIR:
            LOGGER.fine("PC03_EVALUARACCIONSEGUIR");
            isValidCancelar = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            bundleKeyError = "MSG_NO_COMMENTS";
            break;
        case FenixConstants.PC03_ANALIZARDICTAMEN:
            LOGGER.fine("PC03_ANALIZARDICTAMEN");
            isValidCancelar = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            bundleKeyError = "MSG_NO_OBSERVACIONES2";
            break;

        default:
            LOGGER.log(ADFLogger.ERROR, "invokeCancelarOperacion(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        if (!isValidCancelar) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle(bundleKeyError));

        } else {
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            popupConfCancelarOper.show(popupHints);
        }

        return null;
    }

    public String invokeArgumentarSCR() {
        String bundleKeyError = "";
        Boolean isValidArgumentarSCR = Boolean.FALSE;
        Boolean isValidDocumentos = Boolean.FALSE;
        String accion = "SoporteArgumentacion";
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {
        case FenixConstants.PC03_EVALUARACCIONSEGUIR:
            LOGGER.fine("PC03_EVALUARACCIONSEGUIR");
            //isValidArgumentarSCR = validateDocumento(getIdOperacion(), FenixConstants.TD_SOPORTEARGUMENTACIONSCR);
            //bundleKeyError = "MSG_DCMT_SOPORTEARG";
            isValidArgumentarSCR = validarDocumentosTarea(getIdOperacion(), codigoTarea,accion);
            break;

        default:
            LOGGER.log(ADFLogger.ERROR, "invokeArgumentarSCR(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }

        if (!isValidArgumentarSCR) {
            //JSFUtils.addFacesErrorMessage(getStringFromBundle(bundleKeyError));
        } else {
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            popupConfArgumentarSCR.show(popupHints);
        }

        return null;
    }

    public String invokeSubmit() {
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }

    public String acceptFinalizarTarea() {
        LOGGER.warning("Entra en acceptFinalizarTarea.");
        Boolean siFinaliza = Boolean.FALSE;

        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");

        BindingContainer bindings = getBindings();
        AttributeBinding attributeLoginUsuario;
        attributeLoginUsuario = ADFUtils.findControlBinding("ResponsableOperacion");
        String strTir = null;

        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PC03_EVALUARACCIONSEGUIR:
        case FenixConstants.PC03_REVISARDOCUMENTOS:
        case FenixConstants.PC03_RECOPILARINFO:
        case FenixConstants.PC03_ERROR_PROPAGAR_SCR:
            siFinaliza = Boolean.TRUE;
            //TODO Guardar documentos en Onbase
            break;
        case FenixConstants.PC03_APROBARCALIFICACION:
           
                siFinaliza = guardarSCR();

            break;
        case FenixConstants.PC03_ANALIZARDICTAMEN:
            //Se habilita actualizacion de estados TCC para atender incidencia FNXII-3505
            siFinaliza = actualizarTCCEstadoPorAprobar();
            break;
        case FenixConstants.PC03_VALIDARRESULTADO:
        case FenixConstants.PC03_REVISARPROYECTO:
            siFinaliza = Boolean.TRUE;
            break;
        case FenixConstants.PC03_ELABORAROPINIONJUR:
        case FenixConstants.PC03_ELABORAROPINIONJURT:
            System.out.println("PC03_ELABORAROPINIONJUR");
            siFinaliza = Boolean.TRUE;
            break;
        case FenixConstants.PC03_ELABORARCALIFICACION:
            LOGGER.warning("PC03_ELABORARCALIFICACION");
                siFinaliza = guardarSCR();
             
            break;
        case FenixConstants.PC03_ELABORARCALIFICACIONIFI:
                siFinaliza = guardarSCR();

            break;
        case FenixConstants.PC03_ELABORARDICTAMENEAT:
        case FenixConstants.PC03_ELABORARDICTAMENAC:
                        
            Integer requiereTir = null;
            Boolean ValidarTir = Boolean.FALSE;
            
            try{
                requiereTir = (Integer)ADFUtils.getBoundAttributeValue("RequiereTir");
                LOGGER.warning(" requiereTir: "+requiereTir);
            }catch(Exception e){
                LOGGER.warning(" ha ocurrido un error al intentar validar  el TIR: ",e);
                JSFUtils.addFacesErrorMessage(" ha ocurrido un error al intentar validar  el TIR");
            }
                                
            if(requiereTir != null){                 
                  ValidarTir = (requiereTir.compareTo(1) == 0) ?  Boolean.TRUE: Boolean.FALSE;
            }else{
                JSFUtils.addFacesErrorMessage("Error al evaluar TIR");
            }
            
 
            if (ValidarTir) {
                siFinaliza = guardarSCR();
            } else {
                siFinaliza = Boolean.TRUE;
            }
            //TODO Guardar documentos en Onbase
            guardarAnalisis(getIdOperacion(), getTipoOpinionValue(), analisisBean.getJefatura(), getValidadoASJUR(),
                            getCheckedMotivos());
            
            //Se deshabilita actualizacion de Estados TCC para atender incidencia FNXII-3505
            //siFinaliza = actualizarTCCEstadoPorAprobar();
            break;
        default:
            LOGGER.log(ADFLogger.ERROR, "acceptFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        //invocar metodo para cargar documentos onBase
        if (siFinaliza) {
            cargarDocumentosOnBase();
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        } else {
            return null;
        }
    }

    public Boolean guardarSCR() {
        LOGGER.warning("Entra en guardarSCR.");
        Boolean regresa= Boolean.FALSE;
        Integer ContadorErrores = 0;
        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");
        try {
            AttributeBinding attributeValorSCR;
            AttributeBinding attributeSCR;
            AttributeBinding attributePerspectiva;
            AttributeBinding attributeTIR;

            BindingContainer bindings = getBindings();
            AttributeBinding attributeLoginUsuario;
            attributeLoginUsuario = ADFUtils.findControlBinding("ResponsableOperacion");
            String strTir = null;
            OperationBinding operationBinding = bindings.getOperationBinding("actualizarOperacionAnalisis");

            if (analisisBean.getCodigoTarea().equalsIgnoreCase(Integer.toString(FenixConstants.PC03_ELABORARDICTAMENAC))) {
                LOGGER.warning("case PC03_ELABORARDICTAMENAC.");
                attributeTIR = ADFUtils.findControlBinding("TIR");
                if (attributeTIR.getInputValue().toString().indexOf(',') >= 0) {
                    strTir = attributeTIR.getInputValue().toString().replace(',', '.');
                } else {
                    strTir = attributeTIR.getInputValue().toString();
                }
                operationBinding.getParamsMap().put("idOperacion", getIdOperacion());
                operationBinding.getParamsMap().put("tir", Float.valueOf(strTir));
                operationBinding.getParamsMap().put("loginUsuario", attributeLoginUsuario.getInputValue().toString());
                operationBinding.getParamsMap().put("aprobar", Boolean.TRUE);

            }
            if (analisisBean.getCodigoTarea().equalsIgnoreCase(Integer.toString(FenixConstants.PC03_APROBARCALIFICACION))) {
                attributeValorSCR = ADFUtils.findControlBinding("ValorSCR");
                operationBinding.getParamsMap().put("idOperacion", getIdOperacion());
                operationBinding.getParamsMap().put("src", Integer.valueOf(attributeValorSCR.getInputValue().toString()));
                operationBinding.getParamsMap().put("loginUsuario", attributeLoginUsuario.getInputValue().toString());
                operationBinding.getParamsMap().put("aprobar", Boolean.TRUE);
            }
            if (analisisBean.getCodigoTarea().equalsIgnoreCase(Integer.toString(FenixConstants.PC03_ELABORARCALIFICACION))) {
                attributeValorSCR = ADFUtils.findControlBinding("ValorSCR");
                attributeSCR = ADFUtils.findControlBinding("SCR");
                attributeValorSCR.setInputValue((Integer) attributeSCR.getInputValue());
                attributePerspectiva = ADFUtils.findControlBinding("Prespectiva");
                attributeTIR = ADFUtils.findControlBinding("TIR");

                if (attributeTIR.getInputValue().toString().indexOf(',') >= 0) {
                    strTir = attributeTIR.getInputValue().toString().replace(',', '.');
                } else {
                    strTir = attributeTIR.getInputValue().toString();
                }
                operationBinding.getParamsMap().put("idOperacion", getIdOperacion());
                operationBinding.getParamsMap().put("perspectiva", attributePerspectiva.getInputValue().toString());
                operationBinding.getParamsMap().put("src", Integer.valueOf(attributeSCR.getInputValue().toString()));
                operationBinding.getParamsMap().put("tir", Float.valueOf(strTir));
                operationBinding.getParamsMap().put("loginUsuario", attributeLoginUsuario.getInputValue().toString());

            }
            if (analisisBean.getCodigoTarea().equalsIgnoreCase(Integer.toString(FenixConstants.PC03_ELABORARCALIFICACIONIFI))) {
                attributeValorSCR = ADFUtils.findControlBinding("ValorSCR");
                attributeSCR = ADFUtils.findControlBinding("SCR");
                attributeValorSCR.setInputValue((Integer) attributeSCR.getInputValue());
                attributePerspectiva = ADFUtils.findControlBinding("Prespectiva");

                operationBinding.getParamsMap().put("idOperacion", getIdOperacion());
                operationBinding.getParamsMap().put("perspectiva", attributePerspectiva.getInputValue().toString());
                operationBinding.getParamsMap().put("src", Integer.valueOf(attributeSCR.getInputValue().toString()));
                operationBinding.getParamsMap().put("loginUsuario", attributeLoginUsuario.getInputValue().toString());

            }
            Boolean result = (Boolean) operationBinding.execute();
            LOGGER.warning("result : " + result);
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }
            if (!operationBinding.getErrors().isEmpty()) {
                LOGGER.warning("El metodo regresa errores.");
                regresa= null;
            }
            LOGGER.warning("ContadorErrores : " + ContadorErrores);
            if (ContadorErrores > 0) {
                regresa=Boolean.FALSE;
            }

            regresa=Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error al actualizar la operacion ",e);
            regresa=Boolean.FALSE;
        }
        LOGGER.warning("regresa : " + regresa);
       return regresa;
    }

    /*public Boolean validarComentario(Long idOperacion, String idTcaTareaBpm)
    {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarComentario");

        operationBinding.getParamsMap().put("idTcaTareaBpm", idTcaTareaBpm);
        operationBinding.getParamsMap().put("idOperacion", idOperacion);

        Boolean result = (Boolean) operationBinding.execute();

        if (!operationBinding.getErrors().isEmpty())
        {
            return null;
        }

        return result;
    }*/

    public String acceptRevisarProyecto() {
        Boolean siFinaliza = Boolean.FALSE;
        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");
        
        
        Integer requiereTir = null;
        Boolean ValidarTir = Boolean.FALSE;
        
        try{
            requiereTir = (Integer)ADFUtils.getBoundAttributeValue("RequiereTir");
            LOGGER.warning(" requiereTir: "+requiereTir);
        }catch(Exception e){
            LOGGER.warning(" ha ocurrido un error al intentar validar  el TIR: ",e);
            JSFUtils.addFacesErrorMessage(" ha ocurrido un error al intentar validar  el TIR");
        }
                            
        if(requiereTir != null){                 
              ValidarTir = (requiereTir.compareTo(1) == 0) ?  Boolean.TRUE: Boolean.FALSE;
        }else{
            JSFUtils.addFacesErrorMessage("Error al evaluar TIR");
        }
        
        if (ValidarTir && getCodigoTarea() == FenixConstants.PC03_ELABORARDICTAMENAC) {

                siFinaliza = guardarSCR();
                
        } else {
            siFinaliza = Boolean.TRUE;
        }
        //invocar metodo para cargar documentos onBase
        if (siFinaliza) {
            Boolean operationResult =
                guardarAnalisis(getIdOperacion(), getTipoOpinionValue(), analisisBean.getJefatura(), getValidadoASJUR(),
                                getCheckedMotivos());
            cargarDocumentosOnBase();

            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        } else {
            return null;
        }
    }

    public String acceptMasInformacion() {
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }

    public String acceptReformular() {
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }

    public String acceptModificarOpinionJur() {
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }

    public String acceptCancelarOperacion() {
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }

    public String acceptArgumentarSCR() {
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }

    public void cancelFinalizarTarea(ActionEvent ev) {
        popupConfFinalizarTarea.hide();
    }

    public void cancelMasInformacion(ActionEvent ev) {
        popupConfMasInfo.hide();
    }

    public void cancelReformular(ActionEvent ev) {
        popupConfReformular.hide();
    }

    public void cancelModificarOpinionJur(ActionEvent ev) {
        popupConfModOpinionJur.hide();
    }

    public void cancelRevisarProyecto(ActionEvent ev) {
        popupConfRevProyecto.hide();
    }

    public void cancelCancelarOperacion(ActionEvent ev) {
        popupConfCancelarOper.hide();
    }

    public void cancelArgumentarSCR(ActionEvent ev) {
        popupConfArgumentarSCR.hide();
    }


    private Boolean guardarAnalisis(Long idOperacion, Boolean tipoOpinion, Boolean isValidadoJefatura,
                                    Boolean isValidadoASJUR, List listaMotivos) {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("crearAnalisis");

        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("tipoOpinion", tipoOpinion);
        operationBinding.getParamsMap().put("isValidadoJefatura", isValidadoJefatura);
        operationBinding.getParamsMap().put("isValidadoASJUR", isValidadoASJUR);
        operationBinding.getParamsMap().put("listaMotivos", listaMotivos);

        Boolean result = (Boolean) operationBinding.execute();

        Object object = operationBinding.getResult();

        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }

        return result;
    }

    private List<Integer> getCheckedMotivos() {
        List<Integer> motivosList = new ArrayList<Integer>();
        ViewObject motivosVO = null;
        DCIteratorBinding motivosIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("TcaMotivoIterator");

        motivosVO = motivosIterator.getViewObject();
        Row[] motivoRows = motivosVO.getFilteredRows("selected", true);
        for (Row motivoR : motivoRows) {
            motivosList.add((Integer) motivoR.getAttribute("Id"));
        }

        return motivosList;
    }

    public String deleteme_action() {
        AttributeBinding attributeBinding;

        attributeBinding = ADFUtils.findControlBinding("requireSCR");
        attributeBinding.setInputValue(true);
        return null;
    }

    public Boolean getValorOpinion() {
        return ValorOpinion;
    }

    public void setValorOpinion(Boolean ValorOpinion) {
        this.ValorOpinion = ValorOpinion;
    }

    public boolean existeProyectoResolucion() {
        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");
        return analisisBean.getCuentaConProyectoResolucion();
    }


    public void setPopupConfirmaCalificacionNegativa(RichPopup popupConfirmaCalificacionNegativa) {
        this.popupConfirmaCalificacionNegativa = popupConfirmaCalificacionNegativa;
    }

    public RichPopup getPopupConfirmaCalificacionNegativa() {
        return popupConfirmaCalificacionNegativa;
    }


    public void valueChangeTipoOpinion(ValueChangeEvent valueChangeEvent) {
        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");
        LOGGER.warning("Cuenta con doc  Riesgo " + analisisBean.getCuentaConProyectoResolucion());
        LOGGER.warning("Tipo de Opinion " + valueChangeEvent.getNewValue());
        LOGGER.warning("Tipo de Opinion " + valueChangeEvent.getOldValue());
        String oldValueTipoOpinion = (String) valueChangeEvent.getOldValue();
        analisisBean.setOldTipoOpinion((String) valueChangeEvent.getNewValue());
        analisisBean.setOldTipoOpinion(oldValueTipoOpinion);
        if (getTipoOpinionValue() && analisisBean.getCuentaConProyectoResolucion()) {
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            popupConfirmaCalificacionNegativa.show(popupHints);
        }
        if (getTipoOpinionValue()){
                BindingContainer bindings;
                bindings = getBindings();
                OperationBinding operationBinding;
                operationBinding = bindings.getOperationBinding("llenarMotivos");
                operationBinding.getParamsMap().put("idOperacion", getIdOperacion());
                Object result = operationBinding.execute();
        }
    }

    @SuppressWarnings("unchecked")
    public String botonAceptarOpinionNegativa() {
        AnalisisBean analisisBean;
        analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");
        BindingContainer bindings;
        bindings = getBindings();
        OperationBinding operationBinding;
        operationBinding = bindings.getOperationBinding("eliminarDocumento");
        operationBinding.getParamsMap().put("idTipoDocumento", analisisBean.getIdTipoDocumentoProyectoResolucion());
        operationBinding.getParamsMap().put("idOperacion", getIdOperacion());
        Boolean result = (Boolean) operationBinding.execute();
        if (operationBinding.getErrors().isEmpty()) {
            if (null != operationBinding.getResult()) {
                result = (Boolean) operationBinding.getResult();
                if (result) {
                    OperationBinding operationBindingValidarDocumento;
                    operationBindingValidarDocumento = bindings.getOperationBinding("validarDocumento");
                    operationBindingValidarDocumento.getParamsMap().put("idOperacion", getIdOperacion());
                    operationBindingValidarDocumento.getParamsMap().put("idTipoDocumento",
                                                                        analisisBean.getIdTipoDocumentoProyectoResolucion());
                    Boolean resultado = (Boolean) operationBindingValidarDocumento.execute();
                    if (operationBindingValidarDocumento.getErrors().isEmpty()) {
                        Boolean respuesta;
                        respuesta = (Boolean) operationBindingValidarDocumento.getResult();
                        analisisBean.setCuentaConProyectoResolucion(respuesta);
                    }
                } else {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_NO_SE_ENCONTRO_DOCUMENTO_A_ELIMINAR"));
                }
            } else {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_NO_SE_PUDO_ELIMINAR_DOCUMENTO"));
            }
        } else {
            List errors = operationBinding.getErrors();
            for (int l = 0; l < errors.size(); l++) {
                Throwable t = (Throwable) errors.get(l);
                JSFUtils.addFacesErrorMessage(getStringFromBundle(t.getMessage()));
            }
        }
        popupConfirmaCalificacionNegativa.hide();
        return null;
    }

    public String botonCancelarOpinionNegativa() {
        AnalisisBean analisisBean = (AnalisisBean) JSFUtils.resolveExpression("#{pageFlowScope.analisisBean}");
        if (null == analisisBean.getOldTipoOpinion() || analisisBean.getOldTipoOpinion().equals("null")) {
            //getTipoOpinion().setValue("");
            analisisBean.setOpinionTipo(null);
        } else {
            //getTipoOpinion().setValue(analisisBean.getOldTipoOpinion());
            analisisBean.setOpinionTipo(analisisBean.getOldTipoOpinion());
        }
        popupConfirmaCalificacionNegativa.hide();
        return null;
    }
    
    public String getUrlProyectoResolucion() {
        return urlProyectoResolucion;
    }
    
    /**
     * Realiza la actualizacion de TCC cambiando sus Estados a Aprobado,
     * al finalizar la tarea de Actualizar TCC
     */
    @SuppressWarnings("unchecked")
    public boolean actualizarTCCEstadoPorAprobar(){
        
        LOGGER.entering(AnalisisActionsBean.class.getName(),
                        "actualizarTCCEstadoPorAprobar");
        
        boolean exito = false;
        
        Long idOperacion = null;
        try{
            if(ADFUtils.getBoundAttributeValue("CodigoOperacion") != null){
                idOperacion = Long.valueOf(ADFUtils.getBoundAttributeValue("CodigoOperacion").toString());
            }
                    
            if(idOperacion != null){
                LOGGER.severe("Error el valor del ID de Operacion es NULL");
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener el ID de Operacion");
        }
        
        if(idOperacion != null){
            Map params = new HashMap();
            params.put("idOperacion", idOperacion);
            
            BindingContainer bindings = getBindings();
            OperationBinding oper = null;
            try{
                oper = bindings.getOperationBinding("actualizarEstadosTCCAnalisis");    
            }catch(Exception e){
                LOGGER.severe("Error al obtener el Operator Binding: actualizarEstadosTCCAprobacion");
                JSFUtils.addFacesErrorMessage(e.getMessage());
            }
            
            if(oper != null){
                //Asigna parametros
                oper.getParamsMap().putAll(params);
                
                try{
                    //Ejecuta la operacion
                    oper.execute();    
                }catch(Exception e){
                    LOGGER.severe("Error al ejecutar Operator Binding: actualizarEstadosTCCAnalisis");
                    JSFUtils.addFacesErrorMessage(e.getMessage());
                }
                
                if(oper.getErrors().isEmpty()){
                    
                    Boolean result = (Boolean) oper.getResult();
                    if(result != null){
                        if(result){
                            LOGGER.warning("Actualizacion de Estados TCC exitosa");
                            exito = true;
                        }else{
                            LOGGER.severe("Resultado de la actualizacion de Estados TCC no exitosa");
                            exito = false;
                            String msg = getStringFromBundle("actualizar_estados_tcc_oper_msg_error_no_realizado");
                            JSFUtils.addFacesErrorMessage(msg);
                        }
                    }else{
                        LOGGER.severe("Resultado de actualizacion de Estados TCC NULL");
                        exito = false;
                        String msg = getStringFromBundle("actualizar_estados_tcc_oper_msg_error_no_resultado");
                        JSFUtils.addFacesErrorMessage(msg);
                    }
                }else{
                    LOGGER.severe("Error en la operacion de actualizar Estados TCC. " + 
                                  oper.getErrors().toString());
                    exito = false;
                    String msg = getStringFromBundle("actualizar_estados_tcc_oper_msg_error_contiene_fallo");
                    msg = msg + ". " + oper.getErrors().toString();
                    JSFUtils.addFacesErrorMessage(msg);
                }
            }else{
                LOGGER.severe("Error Operacion de Actualizar Estados TCC no definida");
                exito = false;
                String msg = getStringFromBundle("actualizar_estados_tcc_oper_msg_error_no_resultado");
                JSFUtils.addFacesErrorMessage(msg);
            }
        }
        
        LOGGER.exiting(AnalisisActionsBean.class.getName(),
                       "actualizarTCCEstadoPorAprobar",
                       exito);
        return exito;
    }
    
    private Boolean existenCondicionesPorIdOperacion() {
        LOGGER.warning("Entra en existenCondicionesPorIdOperacion");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("existenCondicionesPorIdOperacion");

        Boolean existenCondiciones = null;
        
        if(getIdOperacion()!= null){
            operationBinding.getParamsMap().put("idOperacion", getIdOperacion());
        }else{
            LOGGER.severe("Error al obtener el Id de Operacion desde Managed Bean");
        }
        
        operationBinding.execute();
        if(operationBinding.getErrors().isEmpty()) {
            // Asignación para Monto aprobado
            existenCondiciones = (Boolean)operationBinding.getResult();
        } else {
            LOGGER.severe("Error al ejecutar el operation binding existenCondicionesPorIdOperacion");
        }
        
        LOGGER.warning("Fuera de existenCondicionesPorIdOperacion,existenCondiciones :"+existenCondiciones);
        return existenCondiciones;
    }
    
    //========== SPS | 07/01/2020 | Metodos de utilerias para la validacion de CA  ============= //
    /**
     * Método empleado para la validacion de los componentes de Clasificacion Ambiental al finalizar la tarea.
     * @author : S&P Solutions
     * @param  : 
     * @return : boolean
     * @version: v1.0
     * @Fecha  : 05/12/2019
     */
    public boolean validacionesCA() {
        boolean banError = true;
        if(esEditable()){
            int cont = obtenerNoComponentes();
            LOGGER.log(ADFLogger.WARNING, "validacionesCA - #Componentes:"+cont);
            erroresCA = new ArrayList<String>();
            erroresCA.clear();
            for (int x = 1; x <=cont; x++) {
                obtenerValoresComponentes(x);
            }
            
            if (!erroresCA.isEmpty()){
                banError = false;
              
            } 
        }
       
    return banError;
    }
    /**
     * Método auxiliar empleado para validar si la seccion de Clasificacion Ambiental es editable o no.
     * @author : S&P Solutions
     * @param  : 
     * @return : boolean
     * @version: v1.0
     * @Fecha  : 06/01/2020
     */
    public boolean esEditable(){
        boolean editable = true;
        UIComponent ui =  obtenerComponente(ID_LBL_SECTOR);
        if(ui!=null){
            editable = false;
        }
        
        LOGGER.log(ADFLogger.WARNING, "esEditable:"+editable);
        return editable;
    } 
    /**
     * Método auxiliar empleado para obtener el # de elementos en la seccion de Clasificacion Ambiental.
     * @author : S&P Solutions
     * @param  : 
     * @return : int
     * @version: v1.0
     * @Fecha  : 02/01/2020
     */
    public int obtenerNoComponentes() {
        UIComponent panelPadre = obtenerComponente(ID_PANELCMP);
        List<UIComponent> hijos = panelPadre.getChildren();
        LOGGER.log(ADFLogger.WARNING, "obtenerNoComponentes | hijos:"+hijos.size());
        int cont = 0;
        for(UIComponent ui : hijos){
            if(ui.getId().startsWith(ID_PANELPADRE)){
                cont++;
            }
        }
        
        LOGGER.log(ADFLogger.WARNING, "panelPadre | cont:"+cont);
        return cont;
    }

    /**
     * Método que busca y retorna un componente dado su id.
     * @author : S&P Solutions
     * @param  : String
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 29/06/2019
     */
    public UIComponent obtenerComponente(String id) {
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        UIComponent ui = findComponent(facesCtx.getViewRoot(), id);
        return ui;
    }

    /**
     * Método que busca y retorna un componente dado un componente base y el id del componente a encontrar.
     * @author :
     * @param  : UIComponent
     * @param  : String
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 29/06/2019
     */
    private UIComponent findComponent(UIComponent base, String id) {

        if (id.equals(base.getId())) {
            return base;
        }

        UIComponent children = null;
        UIComponent result = null;
        Iterator childrens = base.getFacetsAndChildren();
        while (childrens.hasNext() && (result == null)) {
            children = (UIComponent) childrens.next();
            if (id.equals(children.getId())) {
                result = children;
                break;
            }
            result = findComponent(children, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }
    
    /**
     * Método que obtiene los valores de cada componente de los formularios dinámicos.
     * @author : S&P Solutions
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public Boolean obtenerValoresComponentes(int index) {
        Boolean esDatosCorrectos = Boolean.TRUE;
       // logger.warning("\n=======  obtenerValoresComponentes index:" + index);
        //Sector
        String sector = getValorTxtSector(obtenerComponente(ID_PANELCMP), index);
        if (sector.equals("-1")) {
            esDatosCorrectos = Boolean.FALSE;
            //JSFUtils.addFacesErrorMessage("El campo Sector es requerido.");
            erroresCA.add("El campo Sector es requerido.");
        } 
        //Aporte
        String aporte = getValorTxtAporte(obtenerComponente(ID_PANELCMP), index);
        if (aporte.equals("-1")) {
            esDatosCorrectos = Boolean.FALSE;
            //JSFUtils.addFacesErrorMessage("El campo Aporte es requerido.");
            erroresCA.add("El campo Aporte es requerido.");
        }
        //Categoria
        String categoria = getValorTxtCategoria(obtenerComponente(ID_PANELCMP), index);
        if (categoria.equals("-1")) {
            esDatosCorrectos = Boolean.FALSE;
          // JSFUtils.addFacesErrorMessage("El campo Categoría es requerido.");
          erroresCA.add("El campo Categoría es requerido.");
        } 
        //SubCategoria
        String subcategoria = getValorTxtSubCategoria(obtenerComponente(ID_PANELCMP), index);
        if (subcategoria.equals("-1")) {
            esDatosCorrectos = Boolean.FALSE;
         //   JSFUtils.addFacesErrorMessage("El campo Subcategoría es requerido.");
         erroresCA.add("El campo Subcategoría es requerido.");
        } 
        
        return esDatosCorrectos;
       
    }
    
    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtSector(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_SECTOR + index);
        String valor = "-1";
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        //logger.warning("\n===  getValorTxtSector  | index:" + index + "| SECTOR value:" + valor);
        return valor;
    }

    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtAporte(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_APORTE + index);
        String valor = "-1";
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        //logger.warning("\n===  getValorTxtAporte  | index:" + index + "| APORTE value:" + valor);
        return valor;

    }

    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtCategoria(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_CATEGORIA + index);
        String valor = "-1";
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        //logger.warning("\n===  getValorTxtCategoria  | index:" + index + "| CATEGORIA value:" + valor);
        return valor;
    }


    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtSubCategoria(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_SUBCATEGORIA + index);
        String valor = "-1";
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        //logger.warning("\n===  getValorTxtSubCategoria  | index:" + index + "| SUBCATEGORIA value:" + valor);
        return valor;
    }
    
    
    public void setErroresCA(ArrayList<String> erroresCA) {
        this.erroresCA = erroresCA;
    }

    public ArrayList<String> getErroresCA() {
        return erroresCA;
    }
    //========== SPS | 07/01/2020 | Fin metodos ============= //
    
}