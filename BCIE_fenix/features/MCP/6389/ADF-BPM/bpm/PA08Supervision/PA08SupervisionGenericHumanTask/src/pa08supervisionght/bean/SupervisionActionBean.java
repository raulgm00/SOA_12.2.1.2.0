package pa08supervisionght.bean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class SupervisionActionBean extends FenixActionBean {

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(SupervisionActionBean.class);
    public static final String BUNDLE = "pa08supervisionght.PA08SupervisionGHTBundle";

    public static final String COLOR_CALIFICACION_OPTIMO = "background-color:#17A5FC; color:Black;";
    public static final String COLOR_CALIFICACION_ESTANDAR = "background-color:#26BE0B; color:Black;";
    public static final String COLOR_CALIFICACION_ACEPTABLE = "background-color:#3AFC17; color:Black;";
    public static final String COLOR_CALIFICACION_CON_DEFICIENCIAS = "background-color:#FAFE02; color:Black;";
    public static final String COLOR_CALIFICACION_DETERIORADO = "background-color:#FE8D02; color:Black; ";
    public static final String COLOR_CALIFICACION_CRITICO = "background-color:#F20317; color:White;";

    private RichPopup popupFinalizarTarea;
    private RichPopup popupMasInformacion;

    public SupervisionActionBean() {
        super();
    }

    public String invocarFinalizarTarea() {
        LOGGER.warning("Inicia validacion para mostrar mensaje de confirmacion de Finalizar tarea");

        SupervisionBean supervisionBean =
            (SupervisionBean) JSFUtils.resolveExpression("#{pageFlowScope.supervisionBean}");

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        Boolean esProyectoIFI = Boolean.FALSE;
        Boolean esProyectoOperacion = Boolean.FALSE;
        Boolean condicion1 = Boolean.FALSE;
        Boolean condicion2 = Boolean.FALSE;
        Boolean condicion3 = Boolean.FALSE;

        esProyectoIFI = supervisionBean.getEsIFI();
        esProyectoOperacion = esProyectoOperacion();

        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {
        case FenixConstants.PA08_SUPERVISAR_ASPECTOS_TECNICOS_OPERACION:
            LOGGER.fine("PA08_SUPERVISAR_ASPECTOS_TECNICOS_OPERACION");
            LOGGER.warning("Supervisar aspectos tecnicos de la operacion");

            /* RN_01 Cuando se trate de un proyecto en ejecución se debe adjuntar el documento de tipo “Informe de supervisión de proyecto en ejecución” para poder enviar el resultado de la supervisión al Responsable de la Operación.
             * RN_02 Cuando se trate de un cliente de tipo IFI se debe adjuntar el documento de tipo “Informe de supervisión de uso de recursos” para poder enviar el resultado de la supervisión al Responsable de la Operación.
             * RN_04 Cuando se trate proyecto en operación se debe adjuntar el documento de tipo “Informe de supervisión de proyecto en operación” para poder enviar el resultado de la supervisión al Responsable de la Operación.
             * EX02 Identifica que no se cuenta con el documento de tipo “Informe de Supervisión de proyecto en ejecución”.
             * EX03 Identifica que no se ha capturado la información requerida.
             * EX04 Identifica que no se cuenta con el documento de tipo “Informe de supervisión de uso de recursos”.
             * EX05 Identifica que no se cuenta con el documento de tipo “Informe de supervisión de proyecto en operación”.
             * MSG_01 Es necesario agregar el documento de tipo “Informe de supervisión de proyecto en ejecución”.
             * MSG_02 Debe completar la información requerida.
             * MSG_03 Es necesario agregar el documento de tipo “Informe de supervisión de uso de recursos”.
             * MSG_04 Es necesario agregar el documento de tipo “Informe de supervisión de proyecto en operación”.
             */

            LOGGER.warning("esProyectoIFI: " + esProyectoIFI);
            LOGGER.warning("esProyectoOperacion: " + esProyectoOperacion);

            if (esProyectoIFI) {
                
                condicion1 = validarDocumentoPorNumeroSerieDocumento(getIdOperacion(), FenixConstants.DOCUMENTO_INFORME_SUPERVISION_USO_RECURSOS, supervisionBean.getNumeroSerieDocumento());
                LOGGER.warning("validarDocumentoPorNumeroSerieDocumento: " + condicion1);
                if (!condicion1) {
                    bundleKeyErrorList.add("MSG03_SUPERVISAR_ASPECTOS_TECNICOS_OPERACION");
                }
            }

            if (esProyectoOperacion &&
                !esProyectoIFI) {
            
                condicion1 = validarDocumentoPorNumeroSerieDocumento(getIdOperacion(), FenixConstants.DOCUMENTO_INFORME_SUPERVISION_PROYECTO_OPERACION, supervisionBean.getNumeroSerieDocumento());
                LOGGER.warning("validarDocumentoPorNumeroSerieDocumento: " + condicion1);
                if (!condicion1) {
                    bundleKeyErrorList.add("MSG04_SUPERVISAR_ASPECTOS_TECNICOS_OPERACION");
                }
            }

            if ((!esProyectoOperacion && (esProyectoIFI == false))) {
                
                condicion1 = validarDocumentoPorNumeroSerieDocumento(getIdOperacion(), FenixConstants.DOCUMENTO_INFORME_SUPERVISION_PROYECTO_EJECUCION, supervisionBean.getNumeroSerieDocumento());
                
                LOGGER.warning("validarDocumentoPorNumeroSerieDocumento: " + condicion1);
                if (!condicion1) {
                    bundleKeyErrorList.add("MSG01_SUPERVISAR_ASPECTOS_TECNICOS_OPERACION");
                }
            }

            condicion2 = validaCamposRequeridos();
            if (!condicion2) {
                bundleKeyErrorList.add("MSG02_SUPERVISAR_ASPECTOS_TECNICOS_OPERACION");
            }

            LOGGER.warning("condicion2: " + condicion2);
            LOGGER.warning("supervisionBean.getEsIFI(): " + supervisionBean.getEsIFI());
            LOGGER.warning("esProyectoOperacion(): " + esProyectoOperacion());

            if (condicion2 && (!supervisionBean.getEsIFI() && (!esProyectoOperacion()))) {
                LOGGER.warning("Entra condicion 3");
                condicion3 = validaRangoValor();
            }

            isValidFinalizarTarea = condicion1 && condicion2;

            break;
        case FenixConstants.PA08_REVISAR_CONCLUSIONES_RECOMENDACIONES_SUPERVISION:
            LOGGER.fine("PA08_REVISAR_CONCLUSIONES_RECOMENDACIONES_SUPERVISION");
            LOGGER.warning("Revisar conclusiones y recomendaciones de la supervision");

            /* No existen niguna validación
             */

            isValidFinalizarTarea = Boolean.TRUE;
            break;
        default:
            break;
        }

        LOGGER.warning("Finaliza e invoca mensaje de confirmacion: " + isValidFinalizarTarea);

        if (!condicion3) {
            if (!isValidFinalizarTarea) {
                LOGGER.warning("Entra imprimir bundle Error Principal");
                if (bundleKeyErrorList.size() > 0) {
                    for (String bundleKey : bundleKeyErrorList)
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
                }
            } else {
                finalizarTareaPopup();
            }
        }

        return null;
    }

    public void finalizarTareaPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupFinalizarTarea.show(hints);
    }

    public String invocarMasInformacion() {
        LOGGER.warning("Se validan las condiciones para mostrar mensaje de confirmacion al solicitar Mas informacion");
        SupervisionBean supervisionBean =
            (SupervisionBean) JSFUtils.resolveExpression("#{pageFlowScope.supervisionBean}");

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean isValidMasInformacion = Boolean.FALSE;

        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {
        case FenixConstants.PA08_SUPERVISAR_ASPECTOS_TECNICOS_OPERACION:
            LOGGER.fine("PA08_SUPERVISAR_ASPECTOS_TECNICOS_OPERACION");
            LOGGER.warning("Supervisar aspectos tecnicos de la operacion");

            /* RN_03   Solo se podrá cancelar el proceso de supervisión siempre y cuando no se cuente con un retorno por parte del Responsable de la Operación.
             * EX01 Identifica que no se ha agregado un comentario.
             * MSG_05 Es necesario agregar un comentario indicando los motivos de cancelación.
             */

            isValidMasInformacion =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());

            LOGGER.warning("isValidMasInformacion" + isValidMasInformacion);

            if (!isValidMasInformacion) {
                bundleKeyErrorList.add("MSG05_SUPERVISAR_ASPECTOS_TECNICOS_OPERACION");
            } else {
                isValidMasInformacion = Boolean.TRUE;
            }

            break;
        case FenixConstants.PA08_REVISAR_CONCLUSIONES_RECOMENDACIONES_SUPERVISION:
            LOGGER.fine("PA08_REVISAR_CONCLUSIONES_RECOMENDACIONES_SUPERVISION");
            LOGGER.warning("Revisar conclusiones y recomendaciones de la supervision");

            /* EX01 Identifica que no se ha agregado un comentario.
             * MSG_01 Es necesario agregar un comentario con las observaciones encontradas en la supervisión realizada por el Analista de Supervisión.
             */

            isValidMasInformacion =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());

            if (!isValidMasInformacion) {
                bundleKeyErrorList.add("MSG01_REVISAR_CONCLUSIONES_RECOMENDACIONES_SUPERVISION");
            } else {
                isValidMasInformacion = Boolean.TRUE;
            }

            break;
        default:
            break;
        }

        LOGGER.warning("Mas informacion fue correcto para mostrar mensaje de confirmacion: " + isValidMasInformacion);

        if (!isValidMasInformacion) {
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        } else {
            masInformacionPopup();
        }

        return null;
    }

    public void masInformacionPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupMasInformacion.show(hints);
    }

    public String aceptarFinalizarTarea() {
        LOGGER.warning("Inside aceptarFinalizarTarea");

        popupFinalizarTarea.hide();

        SupervisionBean supervisionBean =
            (SupervisionBean) JSFUtils.resolveExpression("#{pageFlowScope.supervisionBean}");

        Boolean isValidFinalizarTarea = Boolean.FALSE;
        List<String> bundleKeyErrorList = new ArrayList<String>();

        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PA08_SUPERVISAR_ASPECTOS_TECNICOS_OPERACION:
            LOGGER.fine("PA08_SUPERVISAR_ASPECTOS_TECNICOS_OPERACION");
            LOGGER.warning("Supervisar aspectos tecnicos de la operacion");

            LOGGER.warning("Carga Documentos");
            cargarDocumentosOnBase();

            validarTipoProyecto();
            
            if (!(supervisionBean.getRequiereAjustes())) {
                asignarFechaInforme();   
            }

            isValidFinalizarTarea = actualizarSupervisionRetorno();

            if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                bundleKeyErrorList.add("MSG_ACTUALIZAR_SUPERVISION");
            }
            break;
        case FenixConstants.PA08_REVISAR_CONCLUSIONES_RECOMENDACIONES_SUPERVISION:
            LOGGER.fine("PA08_REVISAR_CONCLUSIONES_RECOMENDACIONES_SUPERVISION");
            LOGGER.warning("Revisar conclusiones y recomendaciones de la supervision");
                        
            Integer status = 1;
            
            AttributeBinding banEstatus;
            banEstatus = ADFUtils.findControlBinding("BanEstatus");
            
            LOGGER.warning("Carga Documentos");
            cargarDocumentosOnBase();
            
            banEstatus.setInputValue(status);
            LOGGER.warning("BanEstatus: " + banEstatus.getInputValue());
            
            isValidFinalizarTarea = actualizarSupervision();

            if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                bundleKeyErrorList.add("MSG_ACTUALIZAR_SUPERVISION");
            }
            break;
        default:
            break;
        }

        if (isValidFinalizarTarea) {
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        } else {
            LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): " + isValidFinalizarTarea);
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
            LOGGER.warning("Se cancela la confirmacion de Finalizar tarea");
            return null;
        }
    }

    public String cancelarFinalizarTarea() {
        LOGGER.warning("Cancela confirmacion de finalizar tarea");

        popupFinalizarTarea.hide();
        return null;
    }

    public String aceptarMasInformacion() {
        LOGGER.warning("inside aceptarMasInformacion");

        popupMasInformacion.hide();

        SupervisionBean supervisionBean =
            (SupervisionBean) JSFUtils.resolveExpression("#{pageFlowScope.supervisionBean}");

        Boolean isValidMasInformacion = Boolean.FALSE;

        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PA08_SUPERVISAR_ASPECTOS_TECNICOS_OPERACION:
            LOGGER.fine("PA08_SUPERVISAR_ASPECTOS_TECNICOS_OPERACION");
            LOGGER.warning("Supervisar aspectos tecnicos de la operacion");

            eliminarSupervision();
            isValidMasInformacion = Boolean.TRUE;
            break;
        case FenixConstants.PA08_REVISAR_CONCLUSIONES_RECOMENDACIONES_SUPERVISION:
            LOGGER.fine("PA08_REVISAR_CONCLUSIONES_RECOMENDACIONES_SUPERVISION");
            LOGGER.warning("Revisar conclusiones y recomendaciones de la supervision");
            Boolean requiereAjus = Boolean.TRUE;
            supervisionBean.setRequiereAjustes(requiereAjus);
            actualizarPayloadElement("requiereAjustes", supervisionBean.getRequiereAjustes());
            isValidMasInformacion = Boolean.TRUE;
            break;
        default:
            break;
        }

        if (isValidMasInformacion) {
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        } else {
            LOGGER.log(ADFLogger.ERROR, "aceptarMasInformacion(): " + isValidMasInformacion);
            return null;
        }
    }

    public String cancelarmasInformacion() {
        LOGGER.warning("inside cancelarmasInformacion");

        popupMasInformacion.hide();
        return null;
    }

    private Integer getCodigoTarea() {
        LOGGER.warning("inside getCodigoTarea");

        SupervisionBean supervisionBean =
            (SupervisionBean) JSFUtils.resolveExpression("#{pageFlowScope.supervisionBean}");

        if (null != supervisionBean.getCodigoTarea() && supervisionBean.getCodigoTarea().trim().length() > 0) {
            return Integer.parseInt(supervisionBean.getCodigoTarea());
        }
        return 0;
    }

    private Long getIdOperacion() {
        LOGGER.warning("inside getIdOperacion");

        SupervisionBean supervisionBean =
            (SupervisionBean) JSFUtils.resolveExpression("#{pageFlowScope.supervisionBean}");

        if (null != supervisionBean.getIdOperacion() && supervisionBean.getIdOperacion().trim().length() > 0) {
            return Long.parseLong(supervisionBean.getIdOperacion());
        }

        return 0L;
    }

    public void setPopupFinalizarTarea(RichPopup popupFinalizarTarea) {
        this.popupFinalizarTarea = popupFinalizarTarea;
    }

    public RichPopup getPopupFinalizarTarea() {
        return popupFinalizarTarea;
    }

    public void setPopupMasInformacion(RichPopup popupMasInformacion) {
        this.popupMasInformacion = popupMasInformacion;
    }

    public RichPopup getPopupMasInformacion() {
        return popupMasInformacion;
    }

    private Boolean validaCamposRequeridos() {
        LOGGER.warning("inside validaNulos");

        SupervisionBean supervisionBean =
            (SupervisionBean) JSFUtils.resolveExpression("#{pageFlowScope.supervisionBean}");
        Boolean result = Boolean.FALSE;

        AttributeBinding calificacionFinal;
        calificacionFinal = ADFUtils.findControlBinding("CalificacionFinal");
        AttributeBinding avanceFisico;
        avanceFisico = ADFUtils.findControlBinding("AvanceFisico");
        AttributeBinding avanceFinanciero;
        avanceFinanciero = ADFUtils.findControlBinding("AvanceFinanciero");
        AttributeBinding fechaAvance;
        fechaAvance = ADFUtils.findControlBinding("FechaAvance");

        Integer califFinal = (Integer) calificacionFinal.getInputValue();
        BigDecimal avFisico = (BigDecimal) avanceFisico.getInputValue();
        BigDecimal avFinanciero = (BigDecimal) avanceFinanciero.getInputValue();
        java.sql.Timestamp fechAvance = (java.sql.Timestamp) fechaAvance.getInputValue();

        LOGGER.warning("califFinal: " + califFinal);
        LOGGER.warning("avFisico: " + avFisico);
        LOGGER.warning("avFinanciero: " + avFinanciero);
        LOGGER.warning("fechAvance: " + fechaAvance);

        if (!supervisionBean.getEsIFI() && !esProyectoOperacion()) {
            if ((califFinal != null) && (avFisico != null) && (avFinanciero != null) && (fechAvance != null)) {
                result = Boolean.TRUE;
            }
        } else {
            if (fechAvance != null && fechAvance.toString().trim().length() > 0) {
                result = Boolean.TRUE;
            }
        }
        return (result);
    }

    public Boolean validaRangoValor() {
        LOGGER.warning("inside validaRango");

        SupervisionBean supervisionBean =
            (SupervisionBean) JSFUtils.resolveExpression("#{pageFlowScope.supervisionBean}");

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean result = Boolean.FALSE;
        Boolean valor1 = Boolean.FALSE;
        Boolean valor2 = Boolean.FALSE;
        Boolean valor3 = Boolean.FALSE;

        AttributeBinding calificacionFinal;
        calificacionFinal = ADFUtils.findControlBinding("CalificacionFinal");
        AttributeBinding avanceFisico;
        avanceFisico = ADFUtils.findControlBinding("AvanceFisico");
        AttributeBinding avanceFinanciero;
        avanceFinanciero = ADFUtils.findControlBinding("AvanceFinanciero");

        Integer califFinal = (Integer) calificacionFinal.getInputValue();
        BigDecimal avFisico = (BigDecimal) avanceFisico.getInputValue();
        BigDecimal avFinanciero = (BigDecimal) avanceFinanciero.getInputValue();

        if (califFinal.intValue() < 0 || califFinal.intValue() > 100) {
            LOGGER.warning("Calificacion Final fuera de rango");
            bundleKeyErrorList.add("MSG01_RANGO_VALOR");
            valor1 = Boolean.TRUE;
        }
        if (avFisico.intValue() < 0 || avFisico.intValue() > 100) {
            LOGGER.warning("Avance Fisico fuera de rango");
            bundleKeyErrorList.add("MSG02_RANGO_VALOR");
            valor2 = Boolean.TRUE;
        }
        if (avFinanciero.intValue() < 0 || avFinanciero.intValue() > 100) {
            LOGGER.warning("Avance Financiero fuera de rango");
            bundleKeyErrorList.add("MSG03_RANGO_VALOR");
            valor3 = Boolean.TRUE;
        }

        result = valor1 || valor2 || valor3;
        if (result) {
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        }
        LOGGER.warning("Resultado: " + result);

        return (result);
    }

    public Boolean actualizarSupervision() {
        LOGGER.warning("inside actualizarSupervision");

        Boolean finaliza = Boolean.TRUE;

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("actualizarSupervision");
        LOGGER.warning("Se ejecuta metodo AM Actualizar Supervision");
        finaliza = (Boolean) operationBinding.execute();
        LOGGER.warning("Resultado: " + finaliza);
        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.warning("Error");
            return false;
        }

        return finaliza;
    }

    public Boolean esProyectoOperacion() {
        LOGGER.warning("inside esProyectoOperacion");

        Boolean result = Boolean.FALSE;

        AttributeBinding esProyectoOperacion;
        esProyectoOperacion = ADFUtils.findControlBinding("EsProyectoEnOperacion");

        result = (Boolean) esProyectoOperacion.getInputValue();
        LOGGER.warning("Resultado: " + result);

        return (result);
    }

    public void validarTipoProyecto() {
        LOGGER.warning("validarTipoProyecto");

        SupervisionBean supervisionBean =
            (SupervisionBean) JSFUtils.resolveExpression("#{pageFlowScope.supervisionBean}");

        if ((supervisionBean.getEsIFI()) || esProyectoOperacion()) {
            AttributeBinding calificacionFinal;
            calificacionFinal = ADFUtils.findControlBinding("CalificacionFinal");
            AttributeBinding avanceFisico;
            avanceFisico = ADFUtils.findControlBinding("AvanceFisico");
            AttributeBinding avanceFinanciero;
            avanceFinanciero = ADFUtils.findControlBinding("AvanceFinanciero");
            AttributeBinding idTcaSct;
            idTcaSct = ADFUtils.findControlBinding("IdTcaSct");
            
            if (calificacionFinal != null || avanceFisico != null || avanceFinanciero != null) {
                calificacionFinal.setInputValue(null);
                avanceFisico.setInputValue(null);
                avanceFinanciero.setInputValue(null);
                idTcaSct.setInputValue(null);           
            }
        }
    }

    public void validaCamposCancelarSupervision() {
        LOGGER.warning("validaCamposCancelarSupervision");

        SupervisionBean supervisionBean =
            (SupervisionBean) JSFUtils.resolveExpression("#{pageFlowScope.supervisionBean}");

        AttributeBinding calificacionFinal;
        calificacionFinal = ADFUtils.findControlBinding("CalificacionFinal");
        AttributeBinding avanceFisico;
        avanceFisico = ADFUtils.findControlBinding("AvanceFisico");
        AttributeBinding avanceFinanciero;
        avanceFinanciero = ADFUtils.findControlBinding("AvanceFinanciero");
        AttributeBinding fechaAvance;
        fechaAvance = ADFUtils.findControlBinding("FechaAvance");
        AttributeBinding fechaVisita;
        fechaVisita = ADFUtils.findControlBinding("FechaVisita");
        AttributeBinding idTcaSct;
        idTcaSct = ADFUtils.findControlBinding("IdTcaSct");

        calificacionFinal.setInputValue(null);
        avanceFisico.setInputValue(null);
        avanceFinanciero.setInputValue(null);
        fechaAvance.setInputValue(null);
        fechaVisita.setInputValue(null);
        idTcaSct.setInputValue(null);

    }

    public void cambioCalificacion(ValueChangeEvent vce) {
        LOGGER.warning("inside cambioCalficacion");
        LOGGER.warning("Nueva calificacion: " + vce.getNewValue());

        vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
        SupervisionBean supervisionBean =
            (SupervisionBean) JSFUtils.resolveExpression("#{pageFlowScope.supervisionBean}");
        AttributeBinding idTcaSct;
        idTcaSct = ADFUtils.findControlBinding("IdTcaSct");

        Integer calificacionFinal = (Integer) vce.getNewValue();

        if (null != calificacionFinal && (calificacionFinal >= 0 && calificacionFinal <= 100)) {
            Integer idSCT = buscarIdRangoCalificacion(calificacionFinal);
            idTcaSct.setInputValue(idSCT);
            LOGGER.warning("idSCT cambioCalificacion: " + idSCT);
            colocarColorCalificacion(idSCT);
        } else {
            idTcaSct.setInputValue(null);
        }
    }

    public Integer buscarIdRangoCalificacion(Integer calificacionFinal) {
        LOGGER.warning("inside buscarIdRangoCalificacion");
        LOGGER.warning("calificacionFinal: " + calificacionFinal);

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("getIdCalificacion");

        operationBinding.getParamsMap().put("varCalificacion", calificacionFinal);

        Integer result = (Integer) operationBinding.execute();
        LOGGER.warning("result buscarIdRangoCalificacion: " + result);
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }

        return (result);
    }

    public void colocarColorCalificacion(Integer idSCT) {
        LOGGER.warning("inside colocarCalificacionColor");
        LOGGER.warning("idSCT: " + +idSCT);

        SupervisionBean supervisionBean =
            (SupervisionBean) JSFUtils.resolveExpression("#{pageFlowScope.supervisionBean}");
        String color = null;

        switch (idSCT) {
        case 1:
            color = COLOR_CALIFICACION_OPTIMO;
            break;
        case 2:
        case 3:
        case 4:
            color = COLOR_CALIFICACION_ESTANDAR;
            break;
        case 5:
        case 6:
        case 7:
            color = COLOR_CALIFICACION_ACEPTABLE;
            break;
        case 8:
        case 9:
        case 10:
            color = COLOR_CALIFICACION_CON_DEFICIENCIAS;
            break;
        case 11:
        case 12:
        case 13:
            color = COLOR_CALIFICACION_DETERIORADO;
            break;
        case 14:
            color = COLOR_CALIFICACION_CRITICO;
            break;
        default:
            break;
        }
        LOGGER.warning("color: " + color);
        if (color != null) {
            AttributeBinding colorCalificacion;
            colorCalificacion = ADFUtils.findControlBinding("ColorCalificacion");

            colorCalificacion.setInputValue(color);

            supervisionBean.setColorCalificacion(color);
        }
    }

    public void asignarFechaInforme() {
        LOGGER.warning("inside asignarFechaInforme");

        AttributeBinding fechaInforme;
        fechaInforme = ADFUtils.findControlBinding("FechaInforme");

        fechaInforme.setInputValue(new java.sql.Timestamp(System.currentTimeMillis()));
    }
    
    public Boolean eliminarSupervision() {
        LOGGER.warning("Inside eliminarSupervision.");

        Boolean finaliza = Boolean.TRUE;

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("eliminarSupervision");
        
        finaliza = (Boolean) operationBinding.execute();
        
        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.warning("Error");
            return false;
        }
        
        LOGGER.warning("eliminarSupervision: " + finaliza);

        return finaliza;
    }
    
    public Boolean actualizarSupervisionRetorno() {
        LOGGER.warning("inside actualizarSupervisionRetorno");

        SupervisionBean supervisionBean =
            (SupervisionBean) JSFUtils.resolveExpression("#{pageFlowScope.supervisionBean}");
        Boolean finaliza = Boolean.TRUE;

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("actualizarSupervision");
        operationBinding.getParamsMap().put("esRetorno", supervisionBean.getRequiereAjustes());
        LOGGER.warning("Se ejecuta metodo AM Actualizar Supervision");
        finaliza = (Boolean) operationBinding.execute();
        LOGGER.warning("Resultado: " + finaliza);
        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.warning("Error");
            return false;
        }

        return finaliza;
    }
}
