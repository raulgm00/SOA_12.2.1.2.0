package pc02preparaciongenerichumantask.beans;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class PreparacionActionsBean extends FenixActionBean {

    private RichPopup popupAgregarModificar;
    private RichPopup popupDetalle;
    private RichPopup popupEliminar;
    private RichPopup popupDispensar;
    private RichPopup popupFinalizarTarea;
    private RichPopup popupMasInformacion;
    private RichPopup popupError;
    private UIComponent montoComision;

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(PreparacionActionsBean.class);
    public static final String BUNDLE = "pc02preparaciongenerichumantask.PC02PreparacionGenericHumanTaskBundle";
    private static final Integer TIPO_VENCIMIENTO = 4;

    public Boolean validaComsiones() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarComisionesDispensadasPagadas");
        operationBinding.getParamsMap().put("estadoDispensado", FenixConstants.ESTADO_COMISION_DISPENSADO);
        operationBinding.getParamsMap().put("estadoPagado", FenixConstants.ESTADO_COMISION_PAGADO);
        operationBinding.getParamsMap().put("momentoActual", FenixConstants.MOMENTO_COBRO_ANALISIS);

        Boolean result = (Boolean) operationBinding.execute();
        return result;
    }

    public void desactivaChecks() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("activarComisionesValidadas");
        Object result = operationBinding.execute();

    }

    public String invokeFinalizarTarea() {
        LOGGER.warning("inicia metodo invokeFinalizarTarea.");
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        Boolean comisionSugeridaMandatoria = Boolean.TRUE;
        Boolean validarEquipos;
        Boolean isValidDocumentos = Boolean.FALSE;
        String accion = "FinalizarTarea";
        
        int codigoTarea = getCodigoTarea();
        isValidDocumentos = validarDocumentosTarea(getIdOperacion(), codigoTarea,accion); //JURBINA@16062021 validar los documentos por operacion, tarea y accion 
        
        preparacion.setDesactivaEquipos(Boolean.FALSE);
        String bundleKey = "";
        bundleKey = obtenerNombreDocumento();
        Long idOperacion = (!preparacion.getIdOperacion().equals("")) ? Long.parseLong(preparacion.getIdOperacion()) : null;
        final Integer ID_PROCESO_EVALUACION = new Integer(6);
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_CONFORMAR_EQUIPO_DE_PREPARACION)) {
            validarEquipos = guardaEquipos();
            if (validarEquipos) {
                Boolean validaComisionTramite = Boolean.TRUE;
                //Boolean validaComisionTramite = validaTramite();
                if (validaComisionTramite) {
                    comisionSugeridaMandatoria = validarComisionesMandatoriasSugeridas();
                    if (comisionSugeridaMandatoria) {
                        Boolean requiereIBCIE = obtenerColumna(preparacion.getIdProducto(), FenixConstants.PC03_IBCIE);
                        Boolean requiereSIEMAS =
                            obtenerColumna(preparacion.getIdProducto(), FenixConstants.PC03_SIEMAS);
                        Boolean requiereEval = Boolean.FALSE;
                        if (requiereIBCIE && requiereSIEMAS) {
                            requiereEval = Boolean.TRUE;
                        }                        
                        //se agrega para FNXII-7393
                        if(existeProcesoEvaluacionesIniciado(idOperacion,ID_PROCESO_EVALUACION)){
                            requiereEval = Boolean.FALSE;
                        }
                        LOGGER.warning("requiereEval: "+requiereEval);
                        
                        AttributeBinding attributeBinding;
                        attributeBinding = ADFUtils.findControlBinding("requiereProcesoEvaluaciones");
                        attributeBinding.setInputValue(requiereEval);
                        if(isValidDocumentos)
                        {
                            invocarPopupFinalizarTarea();
                        }
                    } else {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_MANDATORIA_SUGERIDA"));
                    }
                } else {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("CONFIRMA_COMSIONES_TRAMITE"));
                }

            } else {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("CONFIRMAR_EQUIPO"));
            }
        }

        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ENVIAR_OPERACION_ANALISIS)) {
            preparacion.setValidaAnalisis(validaComsiones()); // esta linea se descomenta
            //preparacion.setValidaAnalisis(Boolean.TRUE); //Esta linea se quita
            if (preparacion.getValidaAnalisis()) {

                BindingContainer bindings = getBindings();
                OperationBinding operationBinding = bindings.getOperationBinding("consultarDeclaracionJurada");
                Object result = operationBinding.execute();
                AttributeBinding objecion;
                objecion = ADFUtils.findControlBinding("NombreNoObjecion");
                String validaNombreObjecion = (String) objecion.getInputValue();

                objecion = ADFUtils.findControlBinding("ObjecionNo");
                Boolean validaObjecion = Boolean.FALSE;
                if ((Boolean) objecion.getInputValue() != null) {
                    validaObjecion = (Boolean) objecion.getInputValue();
                }
                LOGGER.warning("Objecion: " + validaObjecion);
                // Boolean validaObjecion = Boolean.FALSE;
                //if (validaObjecion.equalsIgnoreCase("NO")) {
                if (!validaObjecion && validaNombreObjecion.equalsIgnoreCase("No")) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("CONFIRMA_NO_OBJECION"));
                } else {
                    if(isValidDocumentos)
                    {
                        invocarPopupFinalizarTarea();
                    }
                }
            } else {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("CONFIRMA_COMISIONES"));
            }
        }

        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_EMITIR_OPINION_TECNICA)) {
            if (isValidDocumentos) {
                invocarPopupFinalizarTarea();
            }
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_RECOPILAR_INFORMACION)) {
            comisionSugeridaMandatoria = validarComisionesMandatoriasSugeridas();
            if (comisionSugeridaMandatoria) {
                if(isValidDocumentos)
                {
                    invocarPopupFinalizarTarea();
                }
            } else {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_MANDATORIA_SUGERIDA"));
            }

        }

        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_HOJA_TERMINOS_SEPRI) ||
            preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_DETERMINAR_RAROC) ||
            preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_HOJA_DE_TERMINOS_PCT) ||
            preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ANALIZAR_ADQUISICIONES)||
            preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ELABORAR_DOCUMENTO_TECNICO_OPD)||
            preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_GENERAR_PLAN_MONITOREO_EVALUACION_OPD)||
            preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_REVISAR_EVIDENCIA_CUMPLIMIENTO_OPD)) {
            
            if (isValidDocumentos) {

                if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_DETERMINAR_RAROC)) {

                    if (null != preparacion.getRAROC()) {
                        Boolean validarRaroc = validarFormatoRaroc();
                        if (validarRaroc) {
                            invocarPopupFinalizarTarea();
                        } else {
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_TXT_ERROR_RAROC"));
                        }

                    } else {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_TXT_ERROR_RAROC"));
                    }

                } else {
                    invocarPopupFinalizarTarea();
                }
            }
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_REVISAR_COMISIONES)) {
            Boolean validaComisiones = validarComisionesValidadas(Boolean.FALSE);

            if (validaComisiones) {
                comisionSugeridaMandatoria = validarComisionesMandatoriasSugeridas();
                if (comisionSugeridaMandatoria) {
                        BindingContainer bindings = getBindings();
                        OperationBinding operationBinding = bindings.getOperationBinding("consultarDeclaracionJurada");
                        Object result = operationBinding.execute();
                        AttributeBinding objecion;
                        objecion = ADFUtils.findControlBinding("NombreNoObjecion");
                        String validaNombreObjecion = (String) objecion.getInputValue();

                        objecion = ADFUtils.findControlBinding("ObjecionNo");
                        Boolean validaObjecion = Boolean.FALSE;
                        if ((Boolean) objecion.getInputValue() != null) {
                            validaObjecion = (Boolean) objecion.getInputValue();
                        }
                        LOGGER.warning("Objecion: " + validaObjecion);
                        // Boolean validaObjecion = Boolean.FALSE;
                        //if (validaObjecion.equalsIgnoreCase("NO")) {
                        if (!validaObjecion && validaNombreObjecion.equalsIgnoreCase("No")) {
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("CONFIRMA_NO_OBJECION"));
                        } else {
                            if(isValidDocumentos)
                            {
                                invocarPopupFinalizarTarea();
                            }
                        }
                } else {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_MANDATORIA_SUGERIDA"));
                }
            } else {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("CONFIRMA_VALIDAR_COMISIONES"));
            }
        }

        return null;
    }


    public Boolean existeProcesoEvaluacionesIniciado(Long idOperacion, Integer idProceso){
      LOGGER.warning("inicia metodo existeProcesoEvaluacionesIniciado");
        Boolean existeProcesoIniciado = Boolean.FALSE;    
        BindingContainer bindings = getBindings();
        
        try{
           OperationBinding operationBinding = bindings.getOperationBinding("existeProcesoIniciadoByOperacion");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("idProceso", idProceso);
            existeProcesoIniciado = (Boolean) operationBinding.execute();
        }catch(Exception e){
            LOGGER.warning("Ha ocurrido un error en el metodo existeProcesoEvaluacionesIniciado ");
            JSFUtils.addFacesErrorMessage("Error al validar proceso de evaluacion existente");
        }
            
      LOGGER.warning("termina inicia metodo existeProcesoEvaluacionesIniciado");
      return existeProcesoIniciado;
    }
 

    public Boolean validarComisionesValidadas(Boolean finaliza) {
        Boolean retorna = Boolean.TRUE;
        DCIteratorBinding voComisionesValidadas =
            ADFUtils.getDCBindingContainer().findIteratorBinding("CargoVOIterator");
        ViewObject eq = voComisionesValidadas.getViewObject();
        Row[] comisionValidadoFalso = eq.getAllRowsInRange();
        LOGGER.warning("Codigo esta actualizado su Estado: " + comisionValidadoFalso.length);
        //eq.getFilteredRows("Validar", false);

        for (Row row : comisionValidadoFalso) {
            if (retorna) {
                long codigoComison;
                Boolean validar = Boolean.FALSE;
                Integer estadoComision = 0;
                codigoComison = (Long) row.getAttribute("Id");
                estadoComision = (Integer) row.getAttribute("IdTcaEstadoTcc");
                validar = (Boolean) row.getAttribute("Validar");
                LOGGER.warning("Codigo: " + codigoComison);
                LOGGER.warning("Codigo Estado: " + estadoComision);

                if (finaliza) {
                    if ((estadoComision.compareTo(FenixConstants.ESTADO_COMISION_DISPENSADO) == 0) ||
                        estadoComision.compareTo(FenixConstants.ESTADO_COMISION_VALIDADO) == 0 ||
                        estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0 ||
                        estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0) {
                        LOGGER.warning("Codigo esta actualizado su Estado: " + codigoComison);

                    } else {
                        if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_POR_APLICAR) == 0) {
                            retorna =
                                actualizarComisionTCC(FenixConstants.ESTADO_COMISION_VALIDADO.longValue(),
                                                      codigoComison,
                                                      FenixConstants.SUBESTADO_COMISION_VALIDADO.longValue());
                        }
                        if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PORDISPENSAR) == 0) {
                            retorna =
                                actualizarComisionTCC(FenixConstants.ESTADO_COMISION_DISPENSADO.longValue(),
                                                      codigoComison,
                                                      FenixConstants.SUBESTADO_COMISION_VALIDADO.longValue());
                        }
                        if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_SUGERIDO) == 0) {
                            retorna =
                                actualizarComisionTCC(FenixConstants.ESTADO_COMISION_VALIDADO.longValue(),
                                                      codigoComison,
                                                      FenixConstants.SUBESTADO_COMISION_VALIDADO.longValue());
                        }
                        if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_MANDATORIO) == 0) {
                            retorna =
                                actualizarComisionTCC(FenixConstants.ESTADO_COMISION_VALIDADO.longValue(),
                                                      codigoComison, null);
                        }
                    }
                } else {
                    if (validar) {
                        LOGGER.warning("Codigo esta validada: " + codigoComison);
                    } else {
                        retorna = Boolean.FALSE;
                    }
                }
            }
        }
        return retorna;
    }

    public Boolean cambiarEstadoAplicar() {

        Boolean retorna = Boolean.TRUE;
        DCIteratorBinding voComisionesValidadas =
            ADFUtils.getDCBindingContainer().findIteratorBinding("CargoVOIterator");

        ViewObject eq = voComisionesValidadas.getViewObject();
        Row[] comisionValidadoFalso = eq.getAllRowsInRange();
        LOGGER.warning("Codigo esta actualizado su Estado: " + comisionValidadoFalso.length);
        if(comisionValidadoFalso.length>0){
                for (Row row : comisionValidadoFalso) {
                    if (retorna) {
                        long codigoComison;
                        Integer estadoComision = 0;
                        codigoComison = (Long) row.getAttribute("Id");
                        estadoComision = (Integer) row.getAttribute("IdTcaEstadoTcc");
                        LOGGER.warning("Codigo: " + codigoComison);
                        LOGGER.warning("Codigo Estado: " + estadoComision);

                        if ((estadoComision.compareTo(FenixConstants.ESTADO_COMISION_DISPENSADO) == 0) ||
                            estadoComision.compareTo(FenixConstants.ESTADO_COMISION_VALIDADO) == 0 ||
                            estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0 ||
                            estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0 ||
                            estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PORDISPENSAR) == 0 ||
                            estadoComision.compareTo(FenixConstants.ESTADO_COMISION_POR_APLICAR) == 0) {
                            LOGGER.warning("Codigo esta actualizado su Estado: " + codigoComison);

                        } else {
                            if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_SUGERIDO) == 0) {
                                retorna =
                                    actualizarComisionTCC(FenixConstants.ESTADO_COMISION_POR_APLICAR.longValue(), codigoComison,
                                                          null);
                            }
                            if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_MANDATORIO) == 0) {
                                retorna =
                                    actualizarComisionTCC(FenixConstants.ESTADO_COMISION_POR_APLICAR.longValue(), codigoComison,
                                                          null);
                            }
                        }
                    }
                }
            }
        return retorna;
    }


    public Boolean validarComisionesMandatoriasSugeridas() {
        Boolean retorna = Boolean.TRUE;
        DCIteratorBinding voComisionesValidadas =
            ADFUtils.getDCBindingContainer().findIteratorBinding("CargoVOIterator");
        ViewObject eq = voComisionesValidadas.getViewObject();
        Row[] comisionValidadoFalso = eq.getFilteredRows("IdTcaSubEstadoTcc", null);
        LOGGER.warning("Codigo esta actualizado su Estado: " + comisionValidadoFalso.length);
        if (comisionValidadoFalso.length > 0) {
            retorna = Boolean.FALSE;
        }
        return retorna;
    }


    public Boolean validaTramite() {
        Boolean resultado=Boolean.TRUE;
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        DCIteratorBinding voComisiones = ADFUtils.getDCBindingContainer().findIteratorBinding("CargoVOIterator");
        ViewObject eq = voComisiones.getViewObject();

        Row[] comisionesTramite = eq.getAllRowsInRange();
        LOGGER.warning("Lista de comision " + comisionesTramite.length);
        preparacion.setContadorComisionesActuales(comisionesTramite.length);
        if (comisionesTramite.length > 0) {
            resultado=Boolean.TRUE;
        }
        return resultado;
    }
    
    public Boolean existenComisiones() {
        Boolean resultado=Boolean.FALSE;
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        DCIteratorBinding voComisiones = ADFUtils.getDCBindingContainer().findIteratorBinding("CargoVOIterator");
        ViewObject eq = voComisiones.getViewObject();

        Row[] comisionesTramite = eq.getAllRowsInRange();
        LOGGER.warning("Lista de comision " + comisionesTramite.length);
        preparacion.setContadorComisionesActuales(comisionesTramite.length);
        if (comisionesTramite.length > 0) {
            resultado=Boolean.TRUE;
        }
        return resultado;
    }

    public Boolean guardarRAROC() {
        Integer ContadorErrores = 0;
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        LOGGER.warning("RAROC a Ingresar " + preparacion.getRAROC());
        try {
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("ingresarRAROC");

            operationBinding.getParamsMap().put("RAROC", preparacion.getRAROC());
            operationBinding.getParamsMap().put("idOperacion", preparacion.getIdOperacion());
            Boolean result = (Boolean) operationBinding.execute();
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            } else {
                String valorNuevoRAROC = Float.toString(preparacion.getRAROC());
                BigDecimal nuevoRAROC = new BigDecimal(valorNuevoRAROC);
                AttributeBinding raroc;
                raroc = ADFUtils.findControlBinding("RAROC");
                raroc.setInputValue(nuevoRAROC);

            }
            if (!operationBinding.getErrors().isEmpty()) {
                LOGGER.warning("Error");
                return null;
            }
            if (ContadorErrores > 0) {
                return false;
            }

            return true;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en guardar RAROC " + e.getClass() + ":" + e.getMessage());
            return false;
        }
    }

    public String obtenerNombreDocumento() {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");

        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_HOJA_TERMINOS_SEPRI)) {
            return "CONFIRMA_DOCUMENTO_SEPRI";
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_REVISAR_COMISIONES)) {
            return "CONFIRMA_DOCUMENTO_REVISION";
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_DETERMINAR_RAROC)) {
            return "CONFIRMA_DOCUMENTO_RAROC";
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_HOJA_DE_TERMINOS_PCT)) {
            return "CONFIRMA_DOCUMENTO_PCT";
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_EMITIR_OPINION_TECNICA)) {
            return "CONFIRMA_DOCUMENTO_OPINION";
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ANALIZAR_ADQUISICIONES)) {
            return "CONFIRMA_DOCUMENTO_ADQUISICION";
        }
        return "Soporte";
    }

    public Boolean validarFormatoRaroc() {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");

        // Float floatRaroc = preparacion.getRAROC() * 100;
        Float numeroAbsoluto=null;
        if(null!=preparacion.getRAROC()){
                numeroAbsoluto=Math.abs(preparacion.getRAROC());
                LOGGER.warning("numero absoluto: "+ numeroAbsoluto);
                
                String rarocString = numeroAbsoluto.toString();

                int indiceDePunto = rarocString.indexOf('.');

                if (indiceDePunto <= 3) {
                    return true;
                } else {
                    return false;
                }
            }
        else{
                return false;
            }
            
    }

    public Boolean validarDocumento() {

        Integer tipoDocumento;
        tipoDocumento = obtenerTipoDocumento();
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_EMITIR_OPINION_TECNICA)) {
            if (documentoValidado(FenixConstants.DOCUMENTO_EMITIR_OPINION_TECNICA,
                                  Long.parseLong(preparacion.getIdOperacion())) ||
                documentoValidado(FenixConstants.DOCUMENTO_EMITIR_OPINION_TECNICA2,
                                  Long.parseLong(preparacion.getIdOperacion()))) {
                return true;
            } else {
                return false;
            }
        } else {
            return documentoValidado(tipoDocumento, Long.parseLong(preparacion.getIdOperacion()));
        }
    }

    public Boolean documentoValidado(Integer documentoTipo, Long operacionID) {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarDocumento");

        operationBinding.getParamsMap().put("idTipoDocumento", documentoTipo);
        operationBinding.getParamsMap().put("idOperacion", operacionID);

        Boolean result = (Boolean) operationBinding.execute();
        LOGGER.warning("resultado " + result);
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }

        return result;
    }

    public Integer obtenerTipoDocumento() {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");

        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_HOJA_TERMINOS_SEPRI)) {
            return FenixConstants.DOCUMENTO_HOJA_TERMINOS_SEPRI;
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_REVISAR_COMISIONES)) {
            return FenixConstants.DOCUMENTO_REVISAR_COMISIONES;
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_DETERMINAR_RAROC)) {
            return FenixConstants.DOCUMENTO_DETERMINAR_RAROC;
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_HOJA_DE_TERMINOS_PCT)) {
            return FenixConstants.DOCUMENTO_HOJA_DE_TERMINOS_PCT;
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_EMITIR_OPINION_TECNICA)) {
            return FenixConstants.DOCUMENTO_EMITIR_OPINION_TECNICA;
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ANALIZAR_ADQUISICIONES)) {
            return FenixConstants.DOCUMENTO_ANALIZAR_ADQUISICIONES;
        }
        return 0;
    }

    public void invocarPopupFinalizarTarea() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupFinalizarTarea.show(hints);
    }

    public void activaChecks() {
        DCIteratorBinding voComisiones = ADFUtils.getDCBindingContainer().findIteratorBinding("CargoVOIterator");
        voComisiones.executeQuery();

        RowSetIterator iq = voComisiones.getViewObject().createRowSetIterator(null);
        iq.reset();
        LOGGER.warning("comisiones: " + voComisiones.getEstimatedRowCount());
        Boolean validarComision = Boolean.TRUE;
        while (iq.hasNext()) {
            Row row = iq.next();
            row.setAttribute("Validar", validarComision);
        }
        iq.closeRowSetIterator();
    }

    public Boolean inicioComision() {
        Boolean retorno = Boolean.TRUE;
        Integer ContadorErrores = 0;

        DCIteratorBinding voComisiones = ADFUtils.getDCBindingContainer().findIteratorBinding("CargoVOIterator");
        //  voComisiones.executeQuery();

        RowSetIterator iq = voComisiones.getViewObject().createRowSetIterator(null);
        iq.reset();
        LOGGER.warning("comisiones: " + voComisiones.getEstimatedRowCount());
        try {
            while (iq.hasNext()) {
                Row row = iq.next();
                long codigoComison;
                String contratoCodigo = "";
                Integer estadoComision = 0;
                Integer subEstadoComision = 0;
                if (retorno) {
                    codigoComison = (Long) row.getAttribute("Id");
                    contratoCodigo = (String) row.getAttribute("CodigoContrato");
                    estadoComision = (Integer) row.getAttribute("IdTcaEstadoTcc");
                    subEstadoComision = (Integer) row.getAttribute("IdTcaSubEstadoTcc");
                    LOGGER.warning("SubEstado comision: " + subEstadoComision);
                    if (subEstadoComision.compareTo(FenixConstants.SUBESTADO_COMISION_VALIDADO) == 0 ||
                        subEstadoComision.compareTo(FenixConstants.SUBESTADO_COMISION_EDITADO_MANDATORIO) == 0) {

                        if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_DISPENSADO) == 0 ||
                            estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0 ||
                            estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0) {

                            if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0 ||
                                estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0) {
                                LOGGER.warning("Codigo comision: " + codigoComison +
                                               " se intenta inciar el proceso de comisiones");
                                BindingContainer bindings = null;
                                bindings = getBindings();
                                Long idComi = codigoComison;

                                OperationBinding operationBinding =
                                    bindings.getOperationBinding("servicioInicioComisiones");
                                LOGGER.warning("Codigo comision: " + idComi);
                                operationBinding.getParamsMap().put("idComision", idComi);

                                retorno = (Boolean) operationBinding.execute();
                                LOGGER.warning("resultado: " + retorno);
                                if (!retorno) {
                                    ContadorErrores = ContadorErrores + 1;
                                }
                                if (!operationBinding.getErrors().isEmpty()) {
                                    LOGGER.warning("Error: ");
                                    return null;
                                }
                                if (ContadorErrores > 0) {
                                    retorno = Boolean.FALSE;
                                } else {
                                    retorno = Boolean.TRUE;
                                }
                            }
                            LOGGER.warning("Codigo comision: " + codigoComison +
                                           " se encuentra dispensada o ya inicio el proceso ");

                        } else {
                            retorno = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_ESTADO_COMISION"));
                        }
                    } else {

                        if (subEstadoComision.compareTo(FenixConstants.SUBESTADO_COMISION_PORVALIDAR) == 0) {
                            if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0 ||
                                estadoComision.compareTo(FenixConstants.ESTADO_COMISION_DISPENSADO) == 0 ||
                                estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0) {
                                LOGGER.warning("Codigo comision: " + codigoComison +
                                               " se encuentra en el proceso de comisiones");
                            } else {
                                retorno = Boolean.FALSE;
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_ESTADO_COMISION"));
                            }

                        } else {
                            retorno = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_SUBESTADO_COMISION"));
                        }
                    }
                }
            }
            iq.closeRowSetIterator();
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioComisiones " + e.getClass() + ":" + e.getMessage());
            retorno = Boolean.FALSE;
        }
        return retorno;
    }

    public void rowComisionActiva() {
        DCIteratorBinding pruebaItteratorBindings = ADFUtils.findIterator("CargoVOIterator");
        ViewObject voRowComision = pruebaItteratorBindings.getViewObject();
        Row currentRow = voRowComision.getCurrentRow();

        LOGGER.warning("IdTcaComision: " + (Integer) currentRow.getAttribute("IdTcaComision"));
        LOGGER.warning("Id: " + (Long) currentRow.getAttribute("Id"));
        LOGGER.warning("Nombre: " + (String) currentRow.getAttribute("Nombre"));
        LOGGER.warning("IdTcaMoneda: " + (Integer) currentRow.getAttribute("IdTcaMoneda"));
        LOGGER.warning("PorcentajeSobreMontoBase: " + (BigDecimal) currentRow.getAttribute("PorcentajeSobreMontoBase"));
        LOGGER.warning("IdTcaMontoBase: " + (Integer) currentRow.getAttribute("IdTcaMontoBase"));
        LOGGER.warning("MontoBase: " + (BigDecimal) currentRow.getAttribute("MontoBase"));
        LOGGER.warning("MontoComision: " + (BigDecimal) currentRow.getAttribute("MontoComision"));
        LOGGER.warning("FechaVencimiento: " + (Date) currentRow.getAttribute("FechaVencimiento"));
        LOGGER.warning("IdTcaMomentoCobro: " + (Integer) currentRow.getAttribute("IdTcaMomentoCobro"));
        LOGGER.warning("IdTcaTipoFrecuencia: " + (Integer) currentRow.getAttribute("IdTcaTipoFrecuencia"));
        LOGGER.warning("FrecuenciaPago: " + (Integer) currentRow.getAttribute("FrecuenciaPago"));
        LOGGER.warning("IdTcaFondo: " + (Integer) currentRow.getAttribute("IdTcaFondo"));
        LOGGER.warning("Observaciones: " + (String) currentRow.getAttribute("Descripcion"));
        LOGGER.warning("IdTcaEstadoTcc: " + (Integer) currentRow.getAttribute("IdTcaEstadoTcc"));
        LOGGER.warning("BanSugerida: " + (Integer) currentRow.getAttribute("BanSugerida"));
        LOGGER.warning("IdTcaSubEstadoTcc: " + (Integer) currentRow.getAttribute("IdTcaSubEstadoTcc"));
    }

    public Boolean regresarEstadoComision() {
        Boolean retorno = Boolean.TRUE;

        DCIteratorBinding voComisiones = ADFUtils.getDCBindingContainer().findIteratorBinding("CargoVOIterator");
        voComisiones.executeQuery();
        RowSetIterator iq = voComisiones.getViewObject().createRowSetIterator(null);
        iq.reset();
        LOGGER.warning("comisiones: " + voComisiones.getEstimatedRowCount());

        try {
            while (iq.hasNext()) {
                Row row = iq.next();
                long codigoComison;
                String contratoCodigo = "";
                Integer estadoComision = 0;
                Integer subEstadoComision = 0;
                Integer ban_sugerida = 0;
                Integer aplicaMandatorio = 1;

                if (retorno) {
                    codigoComison = (Long) row.getAttribute("Id");
                    contratoCodigo = (String) row.getAttribute("CodigoContrato");
                    estadoComision = (Integer) row.getAttribute("IdTcaEstadoTcc");
                    subEstadoComision = (Integer) row.getAttribute("IdTcaSubEstadoTcc");
                    ban_sugerida = (Integer) row.getAttribute("BanSugerida");
                    LOGGER.warning("SubEstado comision: " + subEstadoComision);
                    if (subEstadoComision.compareTo(FenixConstants.SUBESTADO_COMISION_EDITADO_MANDATORIO) == 0 ||
                        (subEstadoComision.compareTo(FenixConstants.SUBESTADO_COMISION_VALIDADO) == 0 &&
                         ban_sugerida.compareTo(aplicaMandatorio) == 0)) {
                        if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_DISPENSADO) == 0 ||
                            estadoComision.compareTo(FenixConstants.ESTADO_COMISION_VALIDADO) == 0 ||
                            estadoComision.compareTo(FenixConstants.ESTADO_COMISION_MANDATORIO) == 0 ||
                            estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0 ||
                            estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0 ||
                            estadoComision.compareTo(FenixConstants.ESTADO_COMISION_POR_APLICAR) == 0) {

                            if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_DISPENSADO) == 0) {
                                LOGGER.warning("regresar a su Estado anterior: " + codigoComison);
                                actualizarComisionTCC(FenixConstants.ESTADO_COMISION_PORDISPENSAR.longValue(),
                                                      codigoComison, null);

                            }
                            if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_VALIDADO) == 0 ||
                                estadoComision.compareTo(FenixConstants.ESTADO_COMISION_POR_APLICAR) == 0) {
                                LOGGER.warning("regresar a su Estado anterior: " + codigoComison);
                                actualizarComisionTCC(FenixConstants.ESTADO_COMISION_MANDATORIO.longValue(),
                                                      codigoComison,
                                                      FenixConstants.SUBESTADO_COMISION_EDITADO_MANDATORIO.longValue());
                            }
                            if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0 ||
                                estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0 ||
                                estadoComision.compareTo(FenixConstants.ESTADO_COMISION_MANDATORIO) == 0) {
                                LOGGER.warning("No actualiza su Estado: " + codigoComison);

                            }
                        } else {
                            if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PORDISPENSAR) == 0) {
                                LOGGER.warning("No actualiza su Estado: " + codigoComison);
                            } else {
                                retorno = Boolean.FALSE;
                                JSFUtils.addFacesErrorMessage("Estado comision incorrecto para poder solicitar mas informacion");
                            }
                        }
                    }

                    else {
                        if (((estadoComision.compareTo(FenixConstants.ESTADO_COMISION_DISPENSADO) == 0) ||
                             estadoComision.compareTo(FenixConstants.ESTADO_COMISION_VALIDADO) == 0 ||
                             estadoComision.compareTo(FenixConstants.ESTADO_COMISION_SUGERIDO) == 0 ||
                             estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0 ||
                             estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0 ||
                             estadoComision.compareTo(FenixConstants.ESTADO_COMISION_POR_APLICAR) == 0) &&
                            ban_sugerida.compareTo(0) == 0) {

                            if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_DISPENSADO) == 0) {
                                LOGGER.warning("regresar a su Estado anterior: " + codigoComison);
                                actualizarComisionTCC(FenixConstants.ESTADO_COMISION_PORDISPENSAR.longValue(),
                                                      codigoComison,
                                                      FenixConstants.SUBESTADO_COMISION_EDITADO.longValue());

                            }
                            if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_VALIDADO) == 0 ||
                                estadoComision.compareTo(FenixConstants.ESTADO_COMISION_POR_APLICAR) == 0) {
                                LOGGER.warning("regresar a su Estado anterior: " + codigoComison);
                                actualizarComisionTCC(FenixConstants.ESTADO_COMISION_SUGERIDO.longValue(),
                                                      codigoComison,
                                                      FenixConstants.SUBESTADO_COMISION_EDITADO.longValue());
                            }
                            if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0 ||
                                estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0) {
                                LOGGER.warning("No actualiza su Estado: " + codigoComison);

                            }
                        } else {
                            if (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_PORDISPENSAR) == 0 ||
                                estadoComision.compareTo(FenixConstants.ESTADO_COMISION_SUGERIDO) == 0) {
                                LOGGER.warning("No actualiza su Estado: " + codigoComison);
                            } else {
                                retorno = Boolean.FALSE;
                                JSFUtils.addFacesErrorMessage("Estado comision incorrecto para poder solicitar mas informacion");
                            }
                        }
                    }
                }
            }
            iq.closeRowSetIterator();
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioComisiones " + e.getClass() + ":" + e.getMessage());
            retorno = Boolean.FALSE;
        }
        return retorno;
    }

    public Boolean servicioComisiones() {
        Boolean retorno = Boolean.FALSE;
        Integer ContadorErrores = 0;
        Integer estadoSugerido = 1;
        Integer estadoMandatorio = 8;
        Integer estadoValidado = 26;
        DCIteratorBinding voComisiones = ADFUtils.getDCBindingContainer().findIteratorBinding("CargoVOIterator");
        ViewObject eq = voComisiones.getViewObject();
        Row[] comisionesActuales = eq.getAllRowsInRange();
        try {
            for (Row row : comisionesActuales) {
                long codigoComison;
                String descripcion;
                String contratoCodigo = "";
                Integer estadoComision = 0;
                codigoComison = (Long) row.getAttribute("Id");
                descripcion = (String) row.getAttribute("Nombre");
                contratoCodigo = (String) row.getAttribute("CodigoContrato");
                estadoComision = (Integer) row.getAttribute("IdTcaEstadoTcc");
                if (contratoCodigo != null && contratoCodigo.trim().length() > 0 ||
                    (estadoComision.compareTo(FenixConstants.ESTADO_COMISION_POR_APLICAR) != 0 &&
                     estadoComision.compareTo(FenixConstants.ESTADO_COMISION_VALIDADO) != 0 &&
                     estadoComision.compareTo(FenixConstants.ESTADO_COMISION_SUGERIDO) != 0 &&
                     estadoComision.compareTo(FenixConstants.ESTADO_COMISION_MANDATORIO) != 0)) {
                    if (contratoCodigo != null && contratoCodigo.trim().length() > 0) {
                        LOGGER.warning("Codigo comision: " + codigoComison + " " + descripcion +
                                       " ya se encuentra en FLEXCUBE");
                    }
                    if ((estadoComision.compareTo(FenixConstants.ESTADO_COMISION_POR_APLICAR) != 0 &&
                         estadoComision.compareTo(FenixConstants.ESTADO_COMISION_VALIDADO) != 0 &&
                         estadoComision.compareTo(FenixConstants.ESTADO_COMISION_SUGERIDO) != 0 &&
                         estadoComision.compareTo(FenixConstants.ESTADO_COMISION_MANDATORIO) != 0)) {
                        LOGGER.warning("Codigo comision: " + codigoComison + " " + descripcion +
                                       " se encuentra en otro tipo de estado diferente a la de aplicar, estado de la comision: " +
                                       estadoComision);
                    }
                    retorno = Boolean.TRUE;
                } else {

                    LOGGER.warning("Codigo comision: " + codigoComison + " " + descripcion +
                                   " se intenta mandar a Flexcube");
                    BindingContainer bindings = null;
                    bindings = getBindings();
                    Long idComi = codigoComison;

                    OperationBinding operationBinding = bindings.getOperationBinding("crearComision");
                    LOGGER.warning("Codigo comision: " + idComi);
                    operationBinding.getParamsMap().put("idComision", idComi);
                    operationBinding.getParamsMap().put("descripcion", descripcion);

                    Boolean result = (Boolean) operationBinding.execute();
                    LOGGER.warning("resultado: " + result);
                    if (!result) {
                        ContadorErrores = ContadorErrores + 1;
                    }
                    if (!operationBinding.getErrors().isEmpty()) {
                        LOGGER.warning("Error");
                        JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                        return Boolean.FALSE;
                    }
                    if (ContadorErrores > 0) {
                        retorno = Boolean.FALSE;
                    } else {
                        retorno = Boolean.TRUE;
                    }

                }

            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING, "Error en crearComision " + e.getClass() + ":" + e.getMessage());
            retorno = Boolean.FALSE;
        }
        
        return retorno;
    }


    public Boolean guardaEquipos() {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        DCIteratorBinding voEquipoConformado =
            ADFUtils.getDCBindingContainer().findIteratorBinding("ConformarEquiposVOIterator");
        ViewObject eq = voEquipoConformado.getViewObject();
        Row[] equipoRowsActivadosValida = eq.getFilteredRows("Participa", true);
        Integer contadorDocumento = 0;
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ENVIAR_OPERACION_ANALISIS)) {
            Row[] equipoRowsActivadosValidaDocumento = eq.getFilteredRows("Documento", true);
            contadorDocumento = equipoRowsActivadosValidaDocumento.length;
        }
        if ((equipoRowsActivadosValida.length > 0 &&
             preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_CONFORMAR_EQUIPO_DE_PREPARACION)) ||
            (contadorDocumento > 0 &&
             preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ENVIAR_OPERACION_ANALISIS))) {
            Row[] equipoRowsActivados = eq.getAllRowsInRange();
            for (Row row : equipoRowsActivados) {
                int tareaClave;
                String tareaEquipo;
                Boolean valorParticipa;
                Boolean valorDocumento = Boolean.FALSE;
                valorParticipa = (Boolean) row.getAttribute("Participa");
                if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ENVIAR_OPERACION_ANALISIS)) {
                    valorDocumento = (Boolean) row.getAttribute("Documento");
                    if (!valorParticipa && valorDocumento) {
                        valorParticipa = Boolean.TRUE;
                    }
                }
                tareaClave = (Integer) row.getAttribute("Id");
                tareaEquipo = Integer.toString(tareaClave);
                if (preparacion.getDesactivaEquipos()) {
                    equiposPayload(tareaEquipo, valorParticipa);
                }
            }
            return true;
        }

        return false;
    }

    public void equiposPayload(String tarea, Boolean participan) {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");

        LOGGER.warning("Tarea: " + tarea + " " + participan);
        AttributeBinding attributeBinding;

        if (tarea.equalsIgnoreCase(FenixConstants.TAREA_DETERMINAR_RAROC)) {
            attributeBinding = ADFUtils.findControlBinding("requiereDeterminarRAROC");
            attributeBinding.setInputValue(participan);
            LOGGER.warning("RAROC: " + attributeBinding.getInputValue());
        }

        if (tarea.equalsIgnoreCase(FenixConstants.TAREA_HOJA_DE_TERMINOS_PCT)) {
            attributeBinding = ADFUtils.findControlBinding("requiereHojaTerminosPCT");
            attributeBinding.setInputValue(participan);
            LOGGER.warning("PCT: " + attributeBinding.getInputValue());
        }

        if (tarea.equalsIgnoreCase(FenixConstants.TAREA_EMITIR_OPINION_TECNICA)) {
            attributeBinding = ADFUtils.findControlBinding("requiereOpinionTecnica");
            attributeBinding.setInputValue(participan);
            LOGGER.warning("Opinion: " + attributeBinding.getInputValue());
        }

        if (tarea.equalsIgnoreCase(FenixConstants.TAREA_ANALIZAR_ADQUISICIONES)) {
            attributeBinding = ADFUtils.findControlBinding("requiereAnalisisAdquisiciones");
            attributeBinding.setInputValue(participan);
            LOGGER.warning("Adquisiciones: " + attributeBinding.getInputValue());
        }

        if (tarea.equalsIgnoreCase(FenixConstants.TAREA_REVISAR_COMISIONES)) {
            attributeBinding = ADFUtils.findControlBinding("requiereRevisionComisiones");
            if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_CONFORMAR_EQUIPO_DE_PREPARACION)) {
                participan = validaTramite(); //Siempre pasar por revisar Comisiones JIRA FNXII-1990
            }
            attributeBinding.setInputValue(participan);
            LOGGER.warning("Revisar comisiones: " + attributeBinding.getInputValue());
        }
        
        if (tarea.equalsIgnoreCase(FenixConstants.TAREA_ELABORAR_DOCUMENTO_TECNICO_OPD)) {
            attributeBinding = ADFUtils.findControlBinding("requiereElaborarDocumentoTecnicoOPD");
            attributeBinding.setInputValue(participan);
            LOGGER.warning("Elaborar Documento Tecnico OPD: " + attributeBinding.getInputValue());
        }
        
        if (tarea.equalsIgnoreCase(FenixConstants.TAREA_GENERAR_PLAN_MONITOREO_EVALUACION_OPD)) {
            attributeBinding = ADFUtils.findControlBinding("requiereGenerarPlanMonitoreoOPD");
            attributeBinding.setInputValue(participan);
            LOGGER.warning("Generar Plan Monitoreo y Evaluacion OPD: " + attributeBinding.getInputValue());
        }
        
        if (tarea.equalsIgnoreCase(FenixConstants.TAREA_REVISAR_EVIDENCIA_CUMPLIMIENTO_OPD)) {
            attributeBinding = ADFUtils.findControlBinding("requiereRevisarEvidenciaCumplimientoOPD");
            attributeBinding.setInputValue(participan);
            LOGGER.warning("Revisar Evidencia Cumplimiento OPD: " + attributeBinding.getInputValue());
        }
    }

    public String finalizarAceptar() {
        //IF OK
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        Boolean comisionServicio = Boolean.FALSE;
        Boolean actualizarEstadosComision = Boolean.FALSE;
        
        LOGGER.warning("Inicia el finalizar aceptar en preparacion");
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_REVISAR_COMISIONES)) {
            actualizarEstadosComision = validarComisionesValidadas(Boolean.TRUE);
            if (actualizarEstadosComision) {
                //comisionServicio =Boolean.TRUE;
                comisionServicio = servicioComisiones();
                if (comisionServicio) {
                    //  desactivaChecks();
                    activaChecks();
                    if (inicioComision()) {
                        popupFinalizarTarea.hide();
                        BindingContainer bindings = getBindings();
                        OperationBinding operationBinding = bindings.getOperationBinding("cargarDocumentos");
                        Object result = operationBinding.execute();
                        InvokeActionBean invokeActionBean =
                            (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                        return invokeActionBean.invokeOperation();
                    }
                }
            }

        } else {
            if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_DETERMINAR_RAROC)) {

                if (null == preparacion.getRAROC()) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_RAROC_CERO"));
                } else {
                    Boolean validadGuardarRAROC = guardarRAROC();
                    LOGGER.warning("Actual: " + validadGuardarRAROC);

                    if (validadGuardarRAROC) {
                        popupFinalizarTarea.hide();
                        BindingContainer bindings = getBindings();
                        OperationBinding operationBinding = bindings.getOperationBinding("cargarDocumentos");
                        Object result = operationBinding.execute();
                        InvokeActionBean invokeActionBean =
                            (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                        return invokeActionBean.invokeOperation();
                    } else {
                        // JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_RAROC"));
                    }
                }
            } else {
                Boolean guardarEstadoComision = Boolean.TRUE;
                Boolean agregoComision = Boolean.TRUE;
                Boolean siFinaliza = Boolean.TRUE;
                if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_CONFORMAR_EQUIPO_DE_PREPARACION)) {
                    preparacion.setDesactivaEquipos(Boolean.TRUE);
                    guardarEstadoComision = guardaEquipos();
                    // agregoComision=cambiarEstadoAplicar();
                }
                if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_CONFORMAR_EQUIPO_DE_PREPARACION) ||
                    preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_RECOPILAR_INFORMACION)) {
                    agregoComision = validaTramite();
                    guardarEstadoComision = cambiarEstadoAplicar();
                    if (agregoComision) {
                        if(existenComisiones()){
                        equiposPayload(FenixConstants.TAREA_REVISAR_COMISIONES, Boolean.TRUE);
                        }
                    }
                    if (preparacion.getContadorComisiones() > 0 &&
                        preparacion.getContadorComisionesActuales() > preparacion.getContadorComisiones()) {
                        guardarSolicitar(FenixConstants.TAREA_REVISAR_COMISIONES, Boolean.TRUE);
                    }
                }
                siFinaliza = guardarEstadoComision && agregoComision;
                LOGGER.warning("Si finaliza preparacion: " + siFinaliza);
                if (siFinaliza) {
                    popupFinalizarTarea.hide();
                    LOGGER.warning("Carga a on base");
                    BindingContainer bindings = getBindings();
                    OperationBinding operationBinding = bindings.getOperationBinding("cargarDocumentos");
                    Object result = operationBinding.execute();
                    LOGGER.warning("Inicia invoke");
                    InvokeActionBean invokeActionBean =
                        (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                    return invokeActionBean.invokeOperation();
                }

            }
        }
        
        
        return null;
    }


    public String finalizarCancelar() {
        //IF OK
        popupFinalizarTarea.hide();
        return null;
    }

    private Long getIdOperacion() {
        PreparacionBean preparacionBean =
            (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");

        if (null != preparacionBean.getIdOperacion() && preparacionBean.getIdOperacion().trim().length() > 0) {
            return Long.parseLong(preparacionBean.getIdOperacion());
        }
        return 0L;
    }

    private Integer getCodigoTarea() {
        PreparacionBean preparacionBean =
            (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");

        if (null != preparacionBean.getCodigoTarea() && preparacionBean.getCodigoTarea().trim().length() > 0) {
            return Integer.parseInt(preparacionBean.getCodigoTarea());
        }

        return 0;
    }


    public String invokeMasInformacionAction() {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        Boolean validaCometnario;
        validaCometnario = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
        LOGGER.warning("Inicia metodo de mas informacion action preparacion");
        LOGGER.warning("respuesta de la validacion: "+validaCometnario);
        if (validaCometnario) {
            Boolean validarEquipos = Boolean.FALSE;
            if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ENVIAR_OPERACION_ANALISIS)) {
                preparacion.setDesactivaEquipos(Boolean.FALSE);
                validarEquipos = guardaEquipos();
                if (validarEquipos) {
                    masInformacionPopup();
                } else {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("CONFIRMAR_EQUIPO"));
                }
            } else {

                masInformacionPopup();
            }

        } else {

            if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_HOJA_DE_TERMINOS_PCT) ||
                preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_REVISAR_COMISIONES) ||
                preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_DETERMINAR_RAROC) ||
                preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ANALIZAR_ADQUISICIONES) ||
                preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_EMITIR_OPINION_TECNICA) ||
                preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ENVIAR_OPERACION_ANALISIS)||
                preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ELABORAR_DOCUMENTO_TECNICO_OPD)||
                preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_GENERAR_PLAN_MONITOREO_EVALUACION_OPD)||
                preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_REVISAR_EVIDENCIA_CUMPLIMIENTO_OPD)) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("CONFIRMA_COMENTARIO"));
            } else {
                LOGGER.warning("Inicia popup de confirmacion de mas informacion preparacion");
                masInformacionPopup();
            }
        }

        return null;
    }

    public void tablaEquipos() {
        BindingContainer bindings = null;
        bindings = getBindings();
        //setConformarEquipoValuesByIdOperacion
        LOGGER.warning("tablaEquipos setConformarEquipoValuesByIdOperacion");
        OperationBinding operationBinding = bindings.getOperationBinding("setConformarEquipoValuesByIdOperacion");
        Object result = operationBinding.execute();
    }

    public void guardarSolicitar(String tareaCodigo, Boolean requerido) {
        LOGGER.warning("aparece metodo de activar banderas ");
        AttributeBinding attributeBinding;
        if (tareaCodigo.equalsIgnoreCase(FenixConstants.TAREA_DETERMINAR_RAROC)) {
            attributeBinding = ADFUtils.findControlBinding("solicitaMasInfoDeterminarRAROC");
            attributeBinding.setInputValue(requerido);
            LOGGER.warning("RAROC: " + attributeBinding.getInputValue());
        }

        if (tareaCodigo.equalsIgnoreCase(FenixConstants.TAREA_HOJA_DE_TERMINOS_PCT)) {
            attributeBinding = ADFUtils.findControlBinding("solicitaMasInfoHojaTerminosPCT");
            attributeBinding.setInputValue(requerido);
            LOGGER.warning("PCT: " + attributeBinding.getInputValue());
        }

        if (tareaCodigo.equalsIgnoreCase(FenixConstants.TAREA_EMITIR_OPINION_TECNICA)) {
            LOGGER.warning("Activa la bandera de emitir opinion tecnica");
            attributeBinding = ADFUtils.findControlBinding("solicitaMasInfoOpinionTecnica");
            attributeBinding.setInputValue(requerido);
            LOGGER.warning("Opinion solicita mas informacion?  " + attributeBinding.getInputValue());
        }

        if (tareaCodigo.equalsIgnoreCase(FenixConstants.TAREA_ANALIZAR_ADQUISICIONES)) {
            attributeBinding = ADFUtils.findControlBinding("solicitaMasInfoAnalisisAdquisiciones");
            attributeBinding.setInputValue(requerido);
            LOGGER.warning("Adquisiciones: " + attributeBinding.getInputValue());
        }

        if (tareaCodigo.equalsIgnoreCase(FenixConstants.TAREA_REVISAR_COMISIONES)) {
            attributeBinding = ADFUtils.findControlBinding("solicitaMasInfoRevisionComisiones");
            attributeBinding.setInputValue(requerido);
            LOGGER.warning("Revisar comisiones: " + attributeBinding.getInputValue());
        }
        
        if (tareaCodigo.equalsIgnoreCase(FenixConstants.TAREA_ELABORAR_DOCUMENTO_TECNICO_OPD)) {
            attributeBinding = ADFUtils.findControlBinding("solicitaMasInfoElaborarDocumentoTecnicoOPD");
            attributeBinding.setInputValue(requerido);
            LOGGER.warning("Elaborar Documento Tecnico OPD: " + attributeBinding.getInputValue());
        }
        
        if (tareaCodigo.equalsIgnoreCase(FenixConstants.TAREA_GENERAR_PLAN_MONITOREO_EVALUACION_OPD)) {
            attributeBinding = ADFUtils.findControlBinding("solicitaMasInfoGenerarPlanMonitoreoOPD");
            attributeBinding.setInputValue(requerido);
            LOGGER.warning("Generar Plan Monitoreo Evaluacion OPD: " + attributeBinding.getInputValue());
        }
        
        if (tareaCodigo.equalsIgnoreCase(FenixConstants.TAREA_REVISAR_EVIDENCIA_CUMPLIMIENTO_OPD)) {
            attributeBinding = ADFUtils.findControlBinding("solicitaMasInfoRevisarEvidenciaCumplimientoOPD");
            attributeBinding.setInputValue(requerido);
            LOGGER.warning("Revisar Evidencia Cumplimiento OPD: " + attributeBinding.getInputValue());
        }
    }

    public void guadarMasInformacion() {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        LOGGER.warning("Tarea: " + preparacion.getCodigoTarea());
        LOGGER.warning("Inicia activar banderas de mas informacion");
        Boolean solicitaValidado = Boolean.TRUE;

        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_DETERMINAR_RAROC)) {
            guardarSolicitar(FenixConstants.TAREA_DETERMINAR_RAROC, solicitaValidado);
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_HOJA_DE_TERMINOS_PCT)) {
            guardarSolicitar(FenixConstants.TAREA_HOJA_DE_TERMINOS_PCT, solicitaValidado);
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_EMITIR_OPINION_TECNICA)) {
            LOGGER.warning("Inicia activar banderas de mas informacion en opinion tecnica");
            guardarSolicitar(FenixConstants.TAREA_EMITIR_OPINION_TECNICA, solicitaValidado);
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ANALIZAR_ADQUISICIONES)) {
            guardarSolicitar(FenixConstants.TAREA_ANALIZAR_ADQUISICIONES, solicitaValidado);
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_REVISAR_COMISIONES)) {
            guardarSolicitar(FenixConstants.TAREA_REVISAR_COMISIONES, solicitaValidado);
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ELABORAR_DOCUMENTO_TECNICO_OPD)) {
            guardarSolicitar(FenixConstants.TAREA_ELABORAR_DOCUMENTO_TECNICO_OPD, solicitaValidado);
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_GENERAR_PLAN_MONITOREO_EVALUACION_OPD)) {
            guardarSolicitar(FenixConstants.TAREA_GENERAR_PLAN_MONITOREO_EVALUACION_OPD, solicitaValidado);
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_REVISAR_EVIDENCIA_CUMPLIMIENTO_OPD)) {
            guardarSolicitar(FenixConstants.TAREA_REVISAR_EVIDENCIA_CUMPLIMIENTO_OPD, solicitaValidado);
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ENVIAR_OPERACION_ANALISIS)) {
            AttributeBinding attributeBinding;
            attributeBinding = ADFUtils.findControlBinding("solicitaMasInfoEnviarOperAnalisis");
            attributeBinding.setInputValue(Boolean.TRUE);
            LOGGER.warning("Revisar comisiones: " + attributeBinding.getInputValue());

            DCIteratorBinding voEquipoConformado =
                ADFUtils.getDCBindingContainer().findIteratorBinding("ConformarEquiposVOIterator");
            ViewObject eq = voEquipoConformado.getViewObject();
            Row[] equipoRowsActivados = eq.getAllRowsInRange();
            for (Row row : equipoRowsActivados) {
                int tareaClave;
                String tareaEquipo;
                tareaClave = (Integer) row.getAttribute("Id");
                tareaEquipo = Integer.toString(tareaClave);
                Boolean valorDocumento;
                valorDocumento = (Boolean) row.getAttribute("Documento");
                guardarSolicitar(tareaEquipo, valorDocumento);
            }
        }
    }

    public void masInformacionPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupMasInformacion.show(hints);
    }

    public String invokeMasInformacion() {
        Boolean siGuarda = Boolean.TRUE;
        LOGGER.warning("Inicia metodo de confirmar mas informacion en preparacion");
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_DETERMINAR_RAROC)) {


            if (preparacion.getRAROC() == null) {
                Float cero = Float.parseFloat("0");
                preparacion.setRAROC(cero);
                LOGGER.warning("RAROC a Ingresar " + preparacion.getRAROC());
            } else {
                siGuarda = validarFormatoRaroc();
                if (siGuarda) {
                } else {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_TXT_ERROR_RAROC"));
                }
            }
            siGuarda = guardarRAROC();


        }

        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ENVIAR_OPERACION_ANALISIS)) {
            preparacion.setDesactivaEquipos(Boolean.TRUE);
            siGuarda = guardaEquipos();
        }
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_REVISAR_COMISIONES)) {
            siGuarda = regresarEstadoComision();
        }
        if (siGuarda) {
            guadarMasInformacion();
            //TODO Apply BR here
            popupMasInformacion.hide();
            LOGGER.warning("inicia metodo de carga a on base en preparacion");
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("cargarDocumentos");
            Object result = operationBinding.execute();

            LOGGER.warning("Inicia el invoke action de solicitar mas informacion");
            //IF OK
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        }
        return null;
    }

    public String cancelarMasInformacion() {
        popupMasInformacion.hide();
        return null;
    }

    private Boolean comisionValida() {
        Boolean valida = true;
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        LOGGER.warning("Validando campos comision");
        rowComisionActiva();
        Boolean tasa = preparacion.getTasa();
        BigDecimal valorPorcentaje = preparacion.getPorcentajeBase();
        AttributeBinding idTipoMoneda;
        idTipoMoneda = ADFUtils.findControlBinding("IdTcaMoneda1");
        AttributeBinding idMontoBase;
        idMontoBase = ADFUtils.findControlBinding("IdTcaMontoBase");
        AttributeBinding tipoFrecuencia;
        tipoFrecuencia = ADFUtils.findControlBinding("IdTcaTipoFrecuencia1");
        AttributeBinding frecuenciaPago;
        frecuenciaPago = ADFUtils.findControlBinding("FrecuenciaPago");
        AttributeBinding montoComision;
        montoComision = ADFUtils.findControlBinding("MontoComision");
        preparacion.setErrorFrecuenciaPago(Boolean.FALSE);
        preparacion.setErrorMontoComision(Boolean.FALSE);
        preparacion.setErrorPorcentaje(Boolean.FALSE);
        preparacion.setErrorTipoFrecuncia(Boolean.FALSE);
        preparacion.setErrorTipoMonto(Boolean.FALSE);
        preparacion.setErrorTipomoneda(Boolean.FALSE);
        preparacion.setErrorTipoObservacion(Boolean.FALSE);
        AttributeBinding attributeBinding;
        attributeBinding = ADFUtils.findControlBinding("IdTcaEstadoTcc");
        LOGGER.warning("Estado: " + attributeBinding.getInputValue());

        LOGGER.warning("Estado: " + preparacion.getEstadoComisionValidar());
        LOGGER.warning("Observaciones: " + preparacion.getObservaciones());

        if (FenixConstants.ESTADO_COMISION_DISPENSADO.compareTo(preparacion.getEstadoComisionValidar()) == 0 ||
            FenixConstants.ESTADO_COMISION_PORDISPENSAR.compareTo(preparacion.getEstadoComisionValidar()) == 0) {
            if (null != preparacion.getObservaciones() && preparacion.getObservaciones().length() > 0) {
                AttributeBinding observaciones;
                observaciones = ADFUtils.findControlBinding("Descripcion");
                observaciones.setInputValue(preparacion.getObservaciones());
            } else {
                preparacion.setErrorTipoObservacion(Boolean.TRUE);
                valida = false;
            }
        }

        if (tasa) {
            if (null == valorPorcentaje ||
                (valorPorcentaje.doubleValue() <= 0 || valorPorcentaje.doubleValue() >= 100)) {

                preparacion.setErrorPorcentaje(Boolean.TRUE);
                valida = false;
            }
            if (null == idMontoBase ||
                (null == idMontoBase.getInputValue() || idMontoBase.getInputValue().toString().equals(""))) {

                preparacion.setErrorTipoMonto(Boolean.TRUE);
                valida = false;
            }
        }
        if (null == idTipoMoneda ||
            (null == idTipoMoneda.getInputValue() || idTipoMoneda.getInputValue().toString().equals(""))) {

            preparacion.setErrorTipomoneda(Boolean.TRUE);
            valida = false;
        } 
        
        if (null == tipoFrecuencia ||
            (null == tipoFrecuencia.getInputValue() || tipoFrecuencia.getInputValue().toString().equals(""))) {

            preparacion.setErrorTipoFrecuncia(Boolean.TRUE);
            valida = false;
        } else {
            if (!TIPO_VENCIMIENTO.equals(Integer.valueOf(tipoFrecuencia.getInputValue().toString())) &&
                (null == frecuenciaPago || null == frecuenciaPago.getInputValue() ||
                 (Integer.valueOf(frecuenciaPago.getInputValue().toString()) == 0))) {

                preparacion.setErrorFrecuenciaPago(Boolean.TRUE);
                valida = false;
            }
            else{
                if(TIPO_VENCIMIENTO.compareTo(Integer.valueOf(tipoFrecuencia.getInputValue().toString()))==0){
                    Integer frecuenciaDefault=1;
                    //frecuenciaPago.setInputValue(frecuenciaDefault);
                    }
                
                }
        }
        if (null == montoComision || null == montoComision.getInputValue() ||
            Double.valueOf(montoComision.getInputValue().toString()) <= 0 || montoComision.getInputValue().toString().equals("")) {

            preparacion.setErrorMontoComision(Boolean.TRUE);
            valida = false;
        }
        return valida;
    }

    public void guardarComision(ActionEvent actionEvent) {
        if (comisionValida()) {
            Integer tcaTasa = 0;
            PreparacionBean preparacion =
                (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");

            AttributeBinding tcaTipoTasa;
            tcaTipoTasa = ADFUtils.findControlBinding("IdTcaTipoTasa");

            if (preparacion.getTasa()) {
                tcaTasa = FenixConstants.COMISION_TASA;
            } else {
                tcaTasa = FenixConstants.COMISION_MONTO;
            }
            tcaTipoTasa.setInputValue(tcaTasa);
            //Aqui se invoca la fecha de vencimiento para ser guardado en la fecja Inicio Comision y Fecha Inicio capital
            AttributeBinding fechaVencimiento;
            fechaVencimiento = ADFUtils.findControlBinding("FechaVencimiento");

            AttributeBinding fecjaInicioComision;
            fecjaInicioComision = ADFUtils.findControlBinding("FechaInicioComision");
            AttributeBinding fechaInicioCapital;
            fechaInicioCapital = ADFUtils.findControlBinding("FechaInicioCapital");
            fecjaInicioComision.setInputValue(fechaVencimiento.getInputValue());
            fechaInicioCapital.setInputValue(fechaVencimiento.getInputValue());

            AttributeBinding porcentaje;
            porcentaje = ADFUtils.findControlBinding("PorcentajeSobreMontoBase");
            porcentaje.setInputValue(preparacion.getPorcentajeBase());
            AttributeBinding claveComision;
            claveComision = ADFUtils.findControlBinding("Id");
            Long valorId = (Long) claveComision.getInputValue();
            guardarCommit();
            DCBindingContainer dcBindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding cargosIterator = dcBindings.findIteratorBinding("CargoVOIterator");
            cargosIterator.executeQuery();

            popupAgregarModificar.hide();
        } else {
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupError().show(popupHints);
        }
    }

    public String cancelarGuardarComision() {
        cancelarRollback();
        popupAgregarModificar.hide();
        return null;
    }

    public String detalleComision() {
        popupDetalle.hide();
        return null;
    }

    public String cancelarEliminarComision() {
        cancelarRollback();
        popupEliminar.hide();
        return null;
    }

    public String eliminarComision() {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");

        AttributeBinding attributeBinding;
        attributeBinding = ADFUtils.findControlBinding("BanEstatus");
        LOGGER.warning("Estatus: " + attributeBinding.getInputValue());
        LOGGER.warning("Eliminar: " + preparacion.getEliminar());

        guardarCommit();

        DCBindingContainer dcBindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding comentariosIterator = dcBindings.findIteratorBinding("CargoVOIterator");
        comentariosIterator.executeQuery();
        popupEliminar.hide();
        JSFUtils.addFacesInformationMessage(getStringFromBundle("MSG_COMISION_ELIMINADA"));
        return null;
    }

    public String guardarDispensar() {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        AttributeBinding objecion;
        preparacion.setErrorTipoObservacion(Boolean.FALSE);
        if (preparacion.getObservaciones() instanceof String && preparacion.getObservaciones().length() > 0) {
            objecion = ADFUtils.findControlBinding("Descripcion");
            objecion.setInputValue(preparacion.getObservaciones());
            guardarCommit();
            popupDispensar.hide();
        } else {
            preparacion.setErrorTipoObservacion(Boolean.TRUE);
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupError().show(popupHints);
        }

        return null;
    }

    public void guardarCommit() {
        LOGGER.warning("guardando comision");
        rowComisionActiva();
        OperationBinding commit = null;
        BindingContainer bindings = null;

        bindings = ADFUtils.getBindingContainer();
        commit = bindings.getOperationBinding("Commit");

        commit.execute();
        if (!commit.getErrors().isEmpty()) {
            // Manejo de errores
        }
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_CONFORMAR_EQUIPO_DE_PREPARACION)) {
            tablaEquipos();
        }
    }

    public void cancelarRollback() {
        OperationBinding rollback = null;
        BindingContainer bindings = null;

        bindings = ADFUtils.getBindingContainer();
        rollback = bindings.getOperationBinding("Rollback");

        rollback.execute();
        if (!rollback.getErrors().isEmpty()) {
        }
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_CONFORMAR_EQUIPO_DE_PREPARACION)) {
            tablaEquipos();
        }
    }

    public String cancelarDispensar() {
        cancelarRollback();
        popupDispensar.hide();
        return null;
    }


    public void agregarNuevoCargo(ActionEvent actionEvent) {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        preparacion.setTasa(Boolean.TRUE);
        preparacion.setMonto(Boolean.FALSE);
        preparacion.setAgregarModificar(FenixConstants.TITULO_AGREGAR_COMISION);
        preparacion.setPorcentajeBase(null);
        preparacion.setMontoOcomision(null);
        preparacion.setMontoBase(null);

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("datosCargo");

        Object result = operationBinding.execute();

        AttributeBinding estadoDComision;
        estadoDComision = ADFUtils.findControlBinding("IdTcaEstadoTcc");
        preparacion.setEstadoComisionValidar((Integer) estadoDComision.getInputValue());

        AttributeBinding fechaValor = ADFUtils.findControlBinding("FechaValor");
        fechaValor.setInputValue(preparacion.getFechaValorFlexcube());

        LOGGER.warning("Estado: " + preparacion.getEstadoComisionValidar());
        LOGGER.warning("Observaciones: " + preparacion.getObservaciones());
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupAgregarModificar().show(popupHints);
        LOGGER.warning("nueva comision");
        rowComisionActiva();
    }

    public void cambioPorcentaje(ValueChangeEvent vce) {
        AttributeBinding porcentajeMontoBase;
        porcentajeMontoBase = ADFUtils.findControlBinding("PorcentajeSobreMontoBase");
        BigDecimal valorNuevo = (BigDecimal) vce.getNewValue();
        porcentajeMontoBase.setInputValue(valorNuevo);
        LOGGER.warning("Validando cambio de porcentaje");
        rowComisionActiva();
    }

    public Boolean obtenerColumna(Integer producto, Integer columna) {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("obtenerCampo");
        operationBinding.getParamsMap().put("idProducto", producto);
        operationBinding.getParamsMap().put("columna", columna);
        Boolean result = (Boolean) operationBinding.execute();
        return result;

    }

    public void cambioValorTasa(ValueChangeEvent vce) {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        Boolean tasa = (Boolean) preparacion.getTasa();
        preparacion.setPorcentajeBase(new BigDecimal(0));
        if (null != vce && null != vce.getNewValue()) {
            BigDecimal nuevoValor = (BigDecimal) vce.getNewValue();
            AttributeBinding montoComision = ADFUtils.findControlBinding("MontoComision");
            if (nuevoValor.doubleValue() <= 0 || nuevoValor.doubleValue() > 100) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_PORCENTAJE"));
                montoComision.setInputValue(BigDecimal.ZERO);
            } else {
                preparacion.setPorcentajeBase(nuevoValor);
                montoComision.setInputValue(calculaMontoComision());

            }
        }
    }

    /**
     * Method for calculate comision amount
     * @return
     */
    private BigDecimal calculaMontoComision() {
        LOGGER.warning("calculaMontoComision()");

        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        AttributeBinding montoBase = ADFUtils.findControlBinding("MontoBase");
        LOGGER.warning("Monto base: " + montoBase.getInputValue());
        LOGGER.warning("Porcentaje base: " + preparacion.getPorcentajeBase());

        BigDecimal montoComision = new BigDecimal(0);
        BigDecimal porcentaje = new BigDecimal("100");
        if (null != preparacion.getPorcentajeBase()) {
            if (preparacion.getPorcentajeBase().doubleValue() > 0 &&
                preparacion.getPorcentajeBase().doubleValue() < 100L) {
                if (null != montoBase && null != montoBase.getInputValue()) {
                    BigDecimal monto = (BigDecimal) montoBase.getInputValue();
                    if (monto.doubleValue() > 0.00) {
                        LOGGER.warning("porcentaje:" + preparacion.getPorcentajeBase());
                        monto = monto.multiply(preparacion.getPorcentajeBase());
                        montoComision = monto.divide(porcentaje);
                    }
                }
            }
        }
        LOGGER.warning("Monto comision calculado: " + montoComision);
        LOGGER.warning("calculando monto comision");
        rowComisionActiva();
        return montoComision.setScale(2, RoundingMode.CEILING);
    }
    public void cambioComision(ValueChangeEvent montoCambioo){
            montoCambioo.getComponent().processUpdates(FacesContext.getCurrentInstance());
        }

    public void cambioMontoBase(ValueChangeEvent vce) {
        vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");

        BigDecimal porcentaje = new BigDecimal("100");
        int valorMax = 1;
        int valorMin = -1;
        if (null != preparacion.getPorcentajeBase()) {
            valorMax = preparacion.getPorcentajeBase().compareTo(porcentaje);
            valorMin = preparacion.getPorcentajeBase().compareTo(BigDecimal.ZERO);
        }
        AttributeBinding montoBase;
        montoBase = ADFUtils.findControlBinding("MontoBase");

        AttributeBinding montoSolicitadoPay;
        montoSolicitadoPay = ADFUtils.findControlBinding("MontoSolicitado");
        AttributeBinding montoComisionPay;
        montoComisionPay = ADFUtils.findControlBinding("MontoComision");

        LOGGER.warning("Done");
        BigDecimal montoBaseD = new BigDecimal(0);
        BigDecimal montoComision;
        LOGGER.warning("ID monto " + vce.getNewValue());
        Integer idMontoBase = (Integer) vce.getNewValue();

        switch (idMontoBase) {
        case 1:
            montoBaseD = (BigDecimal) montoSolicitadoPay.getInputValue();
            montoBase.setInputValue(montoBaseD);
            if (valorMax <= 0 && valorMin >= 0) {
                montoComisionPay.setInputValue(calculaMontoComision());
                LOGGER.warning("despues de calcular comision cuando se elige el monto base");
                rowComisionActiva();
            } else {
                montoComisionPay.setInputValue(BigDecimal.ZERO);
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_PORCENTAJE"));
            }
            break;
        default:
            JSFUtils.addFacesErrorMessage(getStringFromBundle("CONFIRMA_NO_MONTO"));
            montoBase.setInputValue(montoBaseD);
            montoComisionPay.setInputValue(calculaMontoComision());
            LOGGER.warning("despues de calcular monto comision despues de elegir una opcion incorrecta en el monto base");
            rowComisionActiva();
            break;
        }
    }

    public void cambioTasa(ValueChangeEvent vce) {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        AttributeBinding montoBase;
        montoBase = ADFUtils.findControlBinding("MontoBase");
        AttributeBinding montoComision;
        montoComision = ADFUtils.findControlBinding("MontoComision");
        AttributeBinding idMontoBase;
        idMontoBase = ADFUtils.findControlBinding("IdTcaMontoBase");
        preparacion.setMontoBase(BigDecimal.ZERO);
        Integer valorNulo = null;
        montoBase.setInputValue(null);
        preparacion.setPorcentajeBase(null);
        montoComision.setInputValue("");
        idMontoBase.setInputValue(valorNulo);
        LOGGER.warning("cambiando monto/tasa");
        rowComisionActiva();
    }

    public void iniciarAdquisiciones(ActionEvent actionEvent) {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");

        if (!preparacion.getValidaAdquisiciones()) {
            BindingContainer bindings = null;
            bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("servicioAdquisiciones");

            operationBinding.getParamsMap().put("idOperacion", preparacion.getIdOperacion());

            Boolean result = (Boolean) operationBinding.execute();
            if (result) {
                preparacion.setValidaAdquisiciones(Boolean.TRUE);
            }
        } else {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_TXT_ERROR_ADQUISICION"));
        }
    }

    public void detalleCargo(ActionEvent actionEvent) {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");

        AttributeBinding porcentajeBase;
        porcentajeBase = ADFUtils.findControlBinding("PorcentajeSobreMontoBase");
        LOGGER.warning("PorcentajeSobreMontoBase " + (BigDecimal) porcentajeBase.getInputValue());

        if ((null == porcentajeBase.getInputValue()) ||
            ((BigDecimal) porcentajeBase.getInputValue()) == BigDecimal.ZERO) {
            preparacion.setTasa(Boolean.FALSE);
            preparacion.setMonto(Boolean.TRUE);
            preparacion.setPorcentajeBase(BigDecimal.ZERO);
        } else {
            preparacion.setTasa(Boolean.TRUE);
            preparacion.setMonto(Boolean.FALSE);
            preparacion.setPorcentajeBase((BigDecimal) porcentajeBase.getInputValue());
        }
        LOGGER.warning("Porcentaje " + preparacion.getPorcentajeBase());
        LOGGER.warning("Monto " + preparacion.getMonto());
        LOGGER.warning("Tasa " + preparacion.getTasa());


        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupDetalle().show(popupHints);

    }

    public void modificarCargo(ActionEvent actionEvent) {

        Integer estadoActual = 0;

        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        preparacion.setAgregarModificar(FenixConstants.TITULO_MODIFICAR_COMISION);
        String contratoCodigo = "";
        AttributeBinding estadoComision;
        estadoComision = ADFUtils.findControlBinding("IdTcaEstadoTcc");
        AttributeBinding codigoContractual;
        codigoContractual = ADFUtils.findControlBinding("CodigoContrato");

        estadoActual = (Integer) estadoComision.getInputValue();

        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_RECOPILAR_INFORMACION)) {
            if (null != codigoContractual.getInputValue()) {
                contratoCodigo = (String) codigoContractual.getInputValue();
            }
        }

        if (((contratoCodigo != null && contratoCodigo.trim().length() > 0) ||
             estadoActual.compareTo(FenixConstants.ESTADO_COMISION_DISPENSADO) == 0 ||
             estadoActual.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0 ||
             estadoActual.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0) &&
            preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_RECOPILAR_INFORMACION)) {
            if (contratoCodigo != null && contratoCodigo.trim().length() > 0) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FLEXCUBE"));
            }
            if (estadoActual.compareTo(FenixConstants.ESTADO_COMISION_DISPENSADO) == 0 ||
                estadoActual.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0 ||
                estadoActual.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_MODIFICACION"));
            }
        } else {
            fondoPorDefecto();
            AttributeBinding subEstadoComision;
            subEstadoComision = ADFUtils.findControlBinding("IdTcaSubEstadoTcc");
            AttributeBinding banSugerida;
            banSugerida = ADFUtils.findControlBinding("BanSugerida");
            Integer esMandatorio = 1;
            if (estadoActual.compareTo(FenixConstants.ESTADO_COMISION_MANDATORIO) == 0) {
                banSugerida.setInputValue(esMandatorio);
                subEstadoComision.setInputValue(FenixConstants.SUBESTADO_COMISION_EDITADO_MANDATORIO);

                AttributeBinding fechaValor = ADFUtils.findControlBinding("FechaValor");
                fechaValor.setInputValue(preparacion.getFechaValorFlexcube());

                AttributeBinding comisionCompartida = ADFUtils.findControlBinding("ComisionCompartida");
                comisionCompartida.setInputValue(FenixConstants.VALOR_CERO); //hace referencia al 2 que pertenece al la comsion compartida "no"
            }
            if (estadoActual.compareTo(FenixConstants.ESTADO_COMISION_SUGERIDO) == 0) {
                subEstadoComision.setInputValue(FenixConstants.SUBESTADO_COMISION_EDITADO);
            }
            AttributeBinding porcentajeBase;
            porcentajeBase = ADFUtils.findControlBinding("PorcentajeSobreMontoBase");
            LOGGER.warning("PorcentajeSobreMontoBase " + (BigDecimal) porcentajeBase.getInputValue());

            if (((BigDecimal) porcentajeBase.getInputValue()) == BigDecimal.ZERO ||
                ((BigDecimal) porcentajeBase.getInputValue()) == null) {
                preparacion.setTasa(Boolean.FALSE);
                preparacion.setMonto(Boolean.TRUE);
                preparacion.setPorcentajeBase(null);
            } else {
                preparacion.setTasa(Boolean.TRUE);
                preparacion.setMonto(Boolean.FALSE);
                preparacion.setPorcentajeBase((BigDecimal) porcentajeBase.getInputValue());
            }

            LOGGER.warning("Porcentaje: " + preparacion.getPorcentajeBase());
            LOGGER.warning("Monto: " + preparacion.getMonto());
            LOGGER.warning("Tasa: " + preparacion.getTasa());
            AttributeBinding estadoDComision;
            estadoDComision = ADFUtils.findControlBinding("IdTcaEstadoTcc");
            preparacion.setEstadoComisionValidar((Integer) estadoDComision.getInputValue());
            AttributeBinding claveComision;
            preparacion.setObservaciones(null);
            if (FenixConstants.ESTADO_COMISION_DISPENSADO.compareTo(preparacion.getEstadoComisionValidar()) == 0 ||
                FenixConstants.ESTADO_COMISION_PORDISPENSAR.compareTo(preparacion.getEstadoComisionValidar()) == 0) {
                AttributeBinding observaciones;
                observaciones = ADFUtils.findControlBinding("Descripcion");
                preparacion.setObservaciones((String) observaciones.getInputValue());
            }
            LOGGER.warning("Estado: " + preparacion.getEstadoComisionValidar());
            LOGGER.warning("Observaciones: " + preparacion.getObservaciones());
            claveComision = ADFUtils.findControlBinding("Id");
            Long valorId = (Long) claveComision.getInputValue();

            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer binding = bindingctx.getCurrentBindingsEntry();
            OperationBinding opt = binding.getOperationBinding("setCurrentRowWithKey");
            opt.getParamsMap().put("Id", Long.toString(valorId));

            opt.execute();
            LOGGER.warning("modificar comision");
            rowComisionActiva();
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupAgregarModificar().show(popupHints);

        }
    }

    public void eliminarCargo(ActionEvent actionEvent) {

        Integer estadoActual = 0;

        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        String contratoCodigo = "";

        AttributeBinding contrato;
        contrato = ADFUtils.findControlBinding("CodigoContrato");
        AttributeBinding estadoComision;
        estadoComision = ADFUtils.findControlBinding("IdTcaEstadoTcc");

        estadoActual = (Integer) estadoComision.getInputValue();
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_RECOPILAR_INFORMACION)) {
            if (null != (String) contrato.getInputValue()) {
                contratoCodigo = (String) contrato.getInputValue();
            }
        }

        if ((null != contratoCodigo && contratoCodigo.trim().length() > 0) ||
            estadoActual.compareTo(FenixConstants.ESTADO_COMISION_DISPENSADO) == 0 ||
            estadoActual.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0 ||
            estadoActual.compareTo(FenixConstants.ESTADO_COMISION_MANDATORIO) == 0 ||
            estadoActual.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0) {
            if (null != contratoCodigo && contratoCodigo.trim().length() > 0) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FLEXCUBE"));
            }
            if (estadoActual.compareTo(FenixConstants.ESTADO_COMISION_DISPENSADO) == 0 ||
                estadoActual.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0 ||
                estadoActual.compareTo(FenixConstants.ESTADO_COMISION_MANDATORIO) == 0 ||
                estadoActual.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_ELIMINACION"));
            }
        } else {
            AttributeBinding banSugerida;
            banSugerida = ADFUtils.findControlBinding("BanSugerida");
            LOGGER.warning("Estado: " + (Integer) banSugerida.getInputValue());
            preparacion.setEliminar((Integer) banSugerida.getInputValue());

            if (preparacion.getEliminar() != 1) {
                AttributeBinding attributeBinding;
                attributeBinding = ADFUtils.findControlBinding("BanEstatus");
                LOGGER.warning("Estatus: " + (Integer) attributeBinding.getInputValue());
                Integer Status = FenixConstants.VALOR_CERO;
                attributeBinding.setInputValue(Status);

                AttributeBinding claveComision;
                claveComision = ADFUtils.findControlBinding("Id");
                Long valorId = (Long) claveComision.getInputValue();

                BindingContext bindingctx = BindingContext.getCurrent();
                BindingContainer binding = bindingctx.getCurrentBindingsEntry();
                OperationBinding opt = binding.getOperationBinding("setCurrentRowWithKey");
                opt.getParamsMap().put("Id", Long.toString(valorId));
                opt.execute();

                RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
                getPopupEliminar().show(popupHints);
            } else {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("CONFIRMA_ELIMINAR"));
            }
        }
    }

    public void dispensarCargo(ActionEvent actionEvent) {
        PreparacionBean preparacion = (PreparacionBean) JSFUtils.resolveExpression("#{pageFlowScope.preparacionBean}");
        String contratoCodigo = "";
        Integer estadoActual = 0;
        AttributeBinding contrato;
        contrato = ADFUtils.findControlBinding("CodigoContrato");
        AttributeBinding estadoComision;
        estadoComision = ADFUtils.findControlBinding("IdTcaEstadoTcc");
        estadoActual = (Integer) estadoComision.getInputValue();
        if (preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_RECOPILAR_INFORMACION)) {
            if (null != contrato.getInputValue()) {
                contratoCodigo = (String) contrato.getInputValue();
            }
        }

        if (((contratoCodigo != null && contratoCodigo.trim().length() > 0) ||
             estadoActual.compareTo(FenixConstants.ESTADO_COMISION_DISPENSADO) == 0 ||
             estadoActual.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0 ||
             estadoActual.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0) &&
            preparacion.getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_RECOPILAR_INFORMACION)) {
            if (contratoCodigo != null && contratoCodigo.trim().length() > 0) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FLEXCUBE"));
            }
            if (estadoActual.compareTo(FenixConstants.ESTADO_COMISION_DISPENSADO) == 0 ||
                estadoActual.compareTo(FenixConstants.ESTADO_COMISION_SUGERIDO) == 0 ||
                estadoActual.compareTo(FenixConstants.ESTADO_COMISION_PAGADO) == 0 ||
                estadoActual.compareTo(FenixConstants.ESTADO_COMISION_PORPAGAR) == 0) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_DISPENSADA"));
            }
        } else {

            AttributeBinding attributeBinding;
            attributeBinding = ADFUtils.findControlBinding("IdTcaEstadoTcc");
            LOGGER.warning("Estado: " + attributeBinding.getInputValue());
            preparacion.setEstadoComisionValidar((Integer) attributeBinding.getInputValue());

            if (FenixConstants.ESTADO_COMISION_PORDISPENSAR.compareTo(preparacion.getEstadoComisionValidar()) != 0) {
                AttributeBinding subEstadoComision;
                subEstadoComision = ADFUtils.findControlBinding("IdTcaSubEstadoTcc");

                AttributeBinding banSugerida;
                banSugerida = ADFUtils.findControlBinding("BanSugerida");
                Integer esMandatorio = 1;
                if (estadoActual.compareTo(FenixConstants.ESTADO_COMISION_MANDATORIO) == 0) {
                    banSugerida.setInputValue(esMandatorio);
                    subEstadoComision.setInputValue(FenixConstants.SUBESTADO_COMISION_EDITADO_MANDATORIO);
                    AttributeBinding fechaValor = ADFUtils.findControlBinding("FechaValor");
                    fechaValor.setInputValue(preparacion.getFechaValorFlexcube());

                    AttributeBinding comisionCompartida = ADFUtils.findControlBinding("ComisionCompartida");
                    comisionCompartida.setInputValue(FenixConstants.VALOR_CERO); //hace referencia al 2 que pertenece al la comsion compartida "no"

                }
                if (estadoActual.compareTo(FenixConstants.ESTADO_COMISION_SUGERIDO) == 0) {
                    subEstadoComision.setInputValue(FenixConstants.SUBESTADO_COMISION_EDITADO);
                }

                attributeBinding.setInputValue(FenixConstants.ESTADO_COMISION_PORDISPENSAR);
                AttributeBinding claveComision;
                claveComision = ADFUtils.findControlBinding("Id");
                Long valorId = (Long) claveComision.getInputValue();

                BindingContext bindingctx = BindingContext.getCurrent();
                BindingContainer binding = bindingctx.getCurrentBindingsEntry();
                OperationBinding opt = binding.getOperationBinding("setCurrentRowWithKey");
                opt.getParamsMap().put("Id", Long.toString(valorId));
                opt.execute();
                preparacion.setObservaciones(null);
                RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
                getPopupDispensar().show(popupHints);

            } else {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_DISPENSAR"));
            }
        }
    }

    public PreparacionActionsBean() {
        super();
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public Boolean validarComisiones() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarComisionesDispensadasPagadas");

        operationBinding.getParamsMap().put("estadoPagado", FenixConstants.ESTADO_COMISION_PAGADO);
        operationBinding.getParamsMap().put("estadoDispensado", FenixConstants.ESTADO_COMISION_DISPENSADO);

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

    public RichPopup getPopupAgregarModificar() {
        return popupAgregarModificar;
    }

    public void setPopupAgregarModificar(RichPopup popupAgregarModificar) {
        this.popupAgregarModificar = popupAgregarModificar;
    }

    public RichPopup getPopupDetalle() {
        return popupDetalle;
    }

    public void setPopupDetalle(RichPopup popupDetalle) {
        this.popupDetalle = popupDetalle;
    }

    public RichPopup getPopupEliminar() {
        return popupEliminar;
    }

    public void setPopupEliminar(RichPopup popupEliminar) {
        this.popupEliminar = popupEliminar;
    }

    public RichPopup getPopupDispensar() {
        return popupDispensar;
    }

    public void setPopupDispensar(RichPopup popupDispensar) {
        this.popupDispensar = popupDispensar;
    }

    public RichPopup getPopupFinalizarTarea() {
        return popupFinalizarTarea;
    }

    public void setPopupFinalizarTarea(RichPopup popupFinalizarTarea) {
        this.popupFinalizarTarea = popupFinalizarTarea;
    }

    public RichPopup getPopupMasInformacion() {
        return popupMasInformacion;
    }

    public void setPopupMasInformacion(RichPopup popupMasInformacion) {
        this.popupMasInformacion = popupMasInformacion;
    }

    public UIComponent getMontoComision() {
        return montoComision;
    }

    public void setMontoComision(UIComponent montoComision) {
        this.montoComision = montoComision;
    }

    /**
     * Method to evalute if kind is expiration
     * @return Boolean
     */
    public Boolean getEsTipoVencimiento() {
        Boolean respuesta = false;
        AttributeBinding tipoFrecuencia;
        tipoFrecuencia = ADFUtils.findControlBinding("IdTcaTipoFrecuencia1");
        Integer value = 0;
        if (null != tipoFrecuencia.getInputValue()) {
            value = (Integer) tipoFrecuencia.getInputValue();
            if (TIPO_VENCIMIENTO.equals(value)) {
                AttributeBinding frecuencia;
                frecuencia = ADFUtils.findControlBinding("FrecuenciaPago");
                frecuencia.setInputValue("");
                respuesta = true;
            }
        }
        return respuesta;
    }

    public RichPopup getPopupError() {
        return popupError;
    }

    public void setPopupError(RichPopup popupError) {
        this.popupError = popupError;
    }

    public Boolean actualizarComisionTCC(Long estado, Long claveComision, Long subEstado) {
        Boolean result = Boolean.FALSE;
        BindingContainer bindings = getBindings();
        try {
            OperationBinding operationBinding = bindings.getOperationBinding("actualizarTCCAdmon");

            operationBinding.getParamsMap().put("idTCC", claveComision);
            operationBinding.getParamsMap().put("idEstadoTcc", estado);
            if (null != subEstado) {
                operationBinding.getParamsMap().put("subEstado", subEstado);
            }
            operationBinding.getParamsMap().put("tipoTCC", FenixConstants.COMISION);

            result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                LOGGER.warning("Error");
                return false;
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en actualizarEstadoComision " + e.getClass() + ":" + e.getMessage());

        }

        return result;
    }
    
    public void fondoPorDefecto(){
        LOGGER.warning("Dentro fondoPorDefecto");
        DCIteratorBinding cargoVOIteratorDC = ADFUtils.findIterator("CargoVOIterator");
        ViewObject voRowComision = cargoVOIteratorDC.getViewObject();
        Row currentRow = voRowComision.getCurrentRow();
        Integer idTcaComision = null;
        Integer idTcaEstadoTcc = null;
        Integer idTcaSubEstadoTcc = null;
        if(null != currentRow){
            
            if(null != currentRow.getAttribute("IdTcaComision")){
                idTcaComision = (Integer) currentRow.getAttribute("IdTcaComision");
            }else{
                LOGGER.warning("IdTcaComision es nulo");
            }
            if(null != currentRow.getAttribute("IdTcaEstadoTcc")){
                idTcaEstadoTcc = (Integer) currentRow.getAttribute("IdTcaEstadoTcc");
            }else{
                LOGGER.warning("IdTcaEstadoTcc es nulo");
            }
            
            if(null != currentRow.getAttribute("IdTcaSubEstadoTcc")){
                idTcaSubEstadoTcc = (Integer) currentRow.getAttribute("IdTcaSubEstadoTcc");
            }else{
                LOGGER.warning("IdTcaSubEstadoTcc es nulo");
            }
            
            LOGGER.warning("idTcaComision :"+idTcaComision);
            LOGGER.warning("idTcaEstadoTcc :"+idTcaEstadoTcc);
            LOGGER.warning("idTcaSubEstadoTcc :"+idTcaSubEstadoTcc);
            
            if(null != idTcaComision && null != idTcaEstadoTcc){
                if(idTcaComision.equals(new Integer("9")))
                    LOGGER.warning("idTcaComision es igual a 9");
                if(idTcaEstadoTcc.equals(new Integer("8")))
                    LOGGER.warning("idTcaEstadoTcc es igual a 8");
                if(idTcaSubEstadoTcc == null)
                    LOGGER.warning("idTcaSubEstadoTcc es igual a null");
                
                if(idTcaComision.equals(new Integer("9")) && idTcaEstadoTcc.equals(new Integer("8"))
                        && idTcaSubEstadoTcc == null){
                    LOGGER.warning("Se selecciona un fondo por defecto");
                    //Fondo ordinario
                    currentRow.setAttribute("IdTcaFondo",new Integer("1"));   
                }else{
                    LOGGER.warning("No se selecciona fondo por defecto");
                }
            }else{
                LOGGER.warning("no se puede realizar la validacion para fondo por defecto");
            }
        }else{
            LOGGER.warning("row es nulo");
        }
        LOGGER.warning("Fuera fondoPorDefecto");
    }
}
