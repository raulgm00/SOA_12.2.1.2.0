package pa07segcrediticioght.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class SeguimientoCrediticioActionBean extends FenixActionBean {

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(SeguimientoCrediticioActionBean.class);
    public static final String BUNDLE = "pa07segcrediticioght.PA07SegCrediticioGHTBundle";

    private RichPopup popupFinalizarTarea;
    private RichPopup popupMasInformacion;

    public SeguimientoCrediticioActionBean() {
        super();
    }

    public String invocarFinalizarTarea() {
        LOGGER.warning("Inicia validacion para mostrar mensaje de confirmacion de Finalizar tarea.");

        SeguimientoCrediticioBean seguimientoCrediticioBean =
            (SeguimientoCrediticioBean) JSFUtils.resolveExpression("#{pageFlowScope.seguimientoCrediticioBean}");

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        Boolean condicion1 = Boolean.FALSE;
        Boolean condicion2 = Boolean.TRUE;
        Boolean condicion3 = Boolean.FALSE;

        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PA07_REALIZAR_REVISION_CALIFICACION_RIESGO:
            LOGGER.fine("PA07_REALIZAR_REVISION_CALIFICACION_RIESGO");
            LOGGER.warning("Realizar revision de Calificacion de Riesgo");

            /* RN_02 Para enviar a revisión la calificación de riesgo se debe adjuntar el documento de tipo “Borrador de calificación de riesgo”.
                 * RN_03 Solo será posible enviar la revisión cuando se cuente con un valor de SCR y Perspectiva asignados al cliente.
                 * EX02 Identifica que no se cuenta con el documento de tipo “Borrador de calificación de riesgo”.
                 * EX03 Identifica que no se cuenta con un valor de SCR y/o Perspectiva.
                 * EX04 Identifica que no se ha capturado un valor para los datos de carácter obligatorio.
                 * MSG_01 Es necesario agregar el documento “Borrador de calificación de riesgo”.
                 * MSG_02 Se debe asignar un valor de SCR y Perspectiva.
                 * MSG_03 Se debe asignar un valor a los datos de carácter obligatorio. Tipo de revisión, SCR (Cambio en SCR) y Perspectiva (Cambio en Perspectiva y SCR (Operaciones del Cliente).
                */
            
            //FNXII-7426
            //validar que exista un cambio en SCR o PERSPECTIVA
            Boolean esCambioScr = null;
            Boolean esCambioPerspectiva = null;
            
            Integer scrPayload = seguimientoCrediticioBean.getScrVig() != null ? seguimientoCrediticioBean.getScrVig() : 0;
            LOGGER.warning("scrPayload : "+scrPayload);
            
            Integer perspectivaPayload = seguimientoCrediticioBean.getPerspectivaVig() != null ? seguimientoCrediticioBean.getPerspectivaVig() : 0;
            LOGGER.warning("perspectivaPayload :"+perspectivaPayload);
            
            Object boundAttribute = ADFUtils.getBoundAttributeValue("IdTcaScr");
            
            String scrActuals = boundAttribute != null ? boundAttribute.toString() : "";
            Integer scrActual = scrActuals.isEmpty() || scrActuals.equals(null) ? 0 : Integer.parseInt(scrActuals);
            LOGGER.warning("scrActual :"+scrActual);
            
            boundAttribute = ADFUtils.getBoundAttributeValue("IdTcaPerspectiva");
            
            String perspectivaActuals = boundAttribute != null ? boundAttribute.toString() : "";
            LOGGER.warning("perspectivaActuals :"+perspectivaActuals);
            Integer perspectivaActual = perspectivaActuals.isEmpty() || perspectivaActuals.equals(null) ? 0 : Integer.parseInt(perspectivaActuals);
            LOGGER.warning("perspectivaActual :"+perspectivaActual);
            
            if(scrPayload.compareTo(scrActual) != 0){
                LOGGER.warning("scrPayload es diferente a scrActual");
                esCambioScr = Boolean.TRUE;
                seguimientoCrediticioBean.setCambiaSCR(esCambioScr);
            }else{
                LOGGER.warning("scrPayload es igual a scrActual");
                esCambioScr = Boolean.FALSE;
            }
            
            if(perspectivaPayload.compareTo(perspectivaActual) != 0){
                LOGGER.warning("perspectivaPayload es diferente a esCambioPerspectiva");
                esCambioPerspectiva = Boolean.TRUE;
                seguimientoCrediticioBean.setCambiaPerspectiva(esCambioPerspectiva);
            }else{
                LOGGER.warning("perspectivaPayload es igual a perspectivaActual");
                esCambioPerspectiva = Boolean.FALSE;
            }
            
            
            if(esCambioScr || esCambioPerspectiva){
                LOGGER.warning("Existen un cambio en SCR o Perspectiva");
                LOGGER.warning("validar documento Borrador de Reporte de Calificación de Riesgo");
                condicion1 =
                    validarDocumentoClientePorNumeroSerieDocumento(seguimientoCrediticioBean.getIdClienteFlex(),
                                                                    FenixConstants.DOCUMENTO_BORRADOR_REPORTE_CALIFICACION_RIESGO,
                                                                        seguimientoCrediticioBean.getNumeroSerieDocumento());
                if (!condicion1) {
                    LOGGER.warning("Es necesario agregar el documento Borrador de calificacion de riesgo.");
                    bundleKeyErrorList.add("MSG01_REALIZAR_REVISION_CALIFICACION_RIESGO");
                }
            }else{
                LOGGER.warning("No existen cambios en SCR o Perspectiva");
                LOGGER.warning("validar documento Reporte de Calificación de Riesgo");
                
                condicion1 =
                    validarDocumentoClientePorNumeroSerieDocumento(seguimientoCrediticioBean.getIdClienteFlex(),
                                                                    FenixConstants.DOCUMENTO_REPORTE_CALIFICACION_RIESGO,
                                                                        seguimientoCrediticioBean.getNumeroSerieDocumento());
                
                if (!condicion1) {
                    LOGGER.warning("Es necesario agregar el documento Reporte de Calificación de Riesgo.");
                    bundleKeyErrorList.add("MSG05_REALIZAR_REVISION_CALIFICACION_RIESGO");
                }
                
            }

            
            // condicion 2
            Integer valorScr = null;
            Integer vslorPerspectiva = null;
            try {
                boundAttribute = ADFUtils.getBoundAttributeValue("IdTcaScr");
                scrActuals = boundAttribute != null ? boundAttribute.toString() : "";
                valorScr = scrActuals.isEmpty() || scrActuals.equals(null) ? 0 : Integer.parseInt(scrActuals);
                
                boundAttribute = ADFUtils.getBoundAttributeValue("IdTcaPerspectiva");
                perspectivaActuals = boundAttribute != null ? boundAttribute.toString() : "";
                vslorPerspectiva = perspectivaActuals.isEmpty() || perspectivaActuals.equals(null) ? 0 : Integer.parseInt(perspectivaActuals);
            } catch (Exception e) {
                valorScr = null;
                vslorPerspectiva = null;
            }
            
            
            LOGGER.warning("valorScr: " + valorScr);
            LOGGER.warning("vslorPerspectiva: " + vslorPerspectiva);

            if (null == valorScr || null == vslorPerspectiva) {
                LOGGER.warning("Se debe asignar un valor de SCR y Perspectiva.");
                condicion2 = Boolean.FALSE;
                bundleKeyErrorList.add("MSG02_REALIZAR_REVISION_CALIFICACION_RIESGO");
            }
            //condicion 3
            AttributeBinding tipoRevision;
            tipoRevision = ADFUtils.findControlBinding("TipoRevision");
            if (null == tipoRevision.getInputValue()) {
                condicion3 = Boolean.FALSE;
                LOGGER.warning("Se deben asignar un valor a los datos de caracter obligatorio (*).");
                bundleKeyErrorList.add("MSG02_REALIZAR_REVISION_CALIFICACION_RIESGO2");
            } else {
                condicion3 = Boolean.TRUE;
            }
            

            isValidFinalizarTarea = condicion1 && condicion2 && condicion3;
            //isValidFinalizarTarea = condicion2;
            break;
        case FenixConstants.PA07_VALIDAR_CAMBIO_SCR_PERSPECTIVA:
            LOGGER.fine("PA07_VALIDAR_CAMBIO_SCR_PERSPECTIVA");
            LOGGER.warning("Validar cambio SCR y/o Perspectiva");

            /* RN_02 Para enviar al gerente de crédito la revisión de la calificación de riesgo, se debe adjuntar el documento de tipo “Reporte de calificación de riesgo”.
                 * RN_03 Solo será posible enviar la revisión cuando se cuente con un valor de SCR y Perspectiva asignados al cliente.
                 * RN_07 Cuando se trate de una revisión de suspenso y que no se hayan registrado cambios de SCR y/o Perspectiva con respecto al proceso anterior del proceso de suspensión, al finalizar esta tarea se debe finalizar el flujo de seguimiento crediticio.
                 * EX02 Identifica que no se cuenta con el documento de tipo “Reporte de calificación de riesgo”.
                 * EX03 Identifica que no se cuenta con un valor de SCR y/o Perspectiva.
                 * EX04 Identifica que no se ha capturado un valor para los datos de carácter obligatorio.
                 * MSG_01 Es necesario agregar el documento Reporte de calificaci\u00F3n de riesgo.
                 * MSG_02 Se debe asignar un valor de SCR y Perspectiva.
                 * MSG_03 Se deben asignar un valor a los datos de carácter obligatorio.
                */
      
            condicion1 =
                validarDocumentoClientePorNumeroSerieDocumento(seguimientoCrediticioBean.getIdClienteFlex(),
                                                               FenixConstants.DOCUMENTO_REPORTE_CALIFICACION_RIESGO,
                                                               seguimientoCrediticioBean.getNumeroSerieDocumento());

            if (!condicion1) {
                bundleKeyErrorList.add("MSG01_VALIDAR_CAMBIO_SCR_PERSPECTIVA");
            }
            
            // condicion 2
            Long idCiente = null;
            Integer valorScr2 = null;
            Integer vslorPerspectiva2 = null;
                                 
             
            try {
                boundAttribute = ADFUtils.getBoundAttributeValue("IdTcaScr");
                scrActuals = boundAttribute != null ? boundAttribute.toString() : "";
                valorScr2 = scrActuals.isEmpty() || scrActuals.equals(null) ? 0 : Integer.parseInt(scrActuals);
                
                boundAttribute = ADFUtils.getBoundAttributeValue("IdTcaPerspectiva");
                perspectivaActuals = boundAttribute != null ? boundAttribute.toString() : "";
                vslorPerspectiva2 = perspectivaActuals.isEmpty() || perspectivaActuals.equals(null) ? 0 : Integer.parseInt(perspectivaActuals);
            } catch (Exception e) {
                valorScr2 = null;
                vslorPerspectiva2 = null;
            }
           
            try{    
                 idCiente = new Long(seguimientoCrediticioBean.getIdCliente());
            }catch(Exception e){
                LOGGER.warning("ha ocurrido un error al recuperar el idCliente ");
            }  
            
            if (null == valorScr2 || null == vslorPerspectiva2) {
                condicion2 = Boolean.FALSE;
                bundleKeyErrorList.add("MSG02_REALIZAR_REVISION_CALIFICACION_RIESGO");
            }
            
        /*  //Estos cambios se realizaron para atender a FNXII-6733 sin embargo este cambio paso a BPM
              Boolean cambioSCR = (null == ADFUtils.getBoundAttributeValue("CambiarSCR") )? false 
                                : (Boolean)ADFUtils.getBoundAttributeValue("CambiarSCR");
             
              Boolean cambioPerspectiva = ( null == ADFUtils.getBoundAttributeValue("CambiarPerspectiva")) ? false 
                                        : (Boolean)ADFUtils.getBoundAttributeValue("CambiarPerspectiva");
              
           
            LOGGER.warning("cambioSCR: "+cambioSCR+" cambioPerspectiva: "+cambioPerspectiva);
           
            if(!cambioSCR && cambioPerspectiva){       
                LOGGER.warning("actualizando perspectiva.. ");
                
                LOGGER.warning("idCliente: " + idCiente);
                LOGGER.warning("perspectiva: " + vslorPerspectiva2);
                
                Boolean seActualizoPerspectiva = actualizarPerspectiva(idCiente, vslorPerspectiva2);
                isValidFinalizarTarea = condicion1 && condicion2 && seActualizoPerspectiva;
                
            }else{
             */    
               isValidFinalizarTarea = condicion1 && condicion2;    
                
          //  }

            
            break;
        case FenixConstants.PA07_VALIDAR_NUEVO_SCR:
            LOGGER.fine("PA07_VALIDAR_NUEVO_SCR");
            LOGGER.warning("Validar nuevo SCR");

            isValidFinalizarTarea = Boolean.TRUE;
            break;
        case FenixConstants.PA07_ERROR_PROPAGAR_SCR:
            LOGGER.fine("PA07_ERROR_PROPAGAR_SCR");
            LOGGER.warning("Re-intentar propagar SCR");

            isValidFinalizarTarea = Boolean.TRUE;
            break;
        default:
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

    public String invocarMasInformacion() {
        LOGGER.warning("Se validan las condiciones para mostrar mensaje de confirmacion al solicitar Mas informacion.");
        SeguimientoCrediticioBean seguimientoCrediticioBean =
            (SeguimientoCrediticioBean) JSFUtils.resolveExpression("#{pageFlowScope.seguimientoCrediticioBean}");

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean isValidMasInformacion = Boolean.FALSE;

        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {

        case FenixConstants.PA07_REALIZAR_REVISION_CALIFICACION_RIESGO:
            LOGGER.fine("PA07_REALIZAR_REVISION_CALIFICACION_RIESGO");
            LOGGER.warning("Realizar revision de Calificacion de Riesgo");

            /* RN_07 Solo podrá cancelarse la revisión de calificación de riesgo cuando no se trate de un retorno o de una revisión de calificación de riesgo en suspenso.
                  * EX01 Identifica que no se ha agregado un comentario.s
                  * MSG_04 Es necesario agregar un comentario para cancelar la revisión de calificación de riesgo.
                 */

            isValidMasInformacion =
                validarComentarioCliente(getIdCliente(), getCodigoTarea().toString(), getInstanciaTarea());

            LOGGER.warning("afterMethodValidarComentarioCliente - " + isValidMasInformacion);
            if (!isValidMasInformacion) {
                bundleKeyErrorList.add("MSG04_REALIZAR_REVISION_CALIFICACION_RIESGO");
            } else {
                isValidMasInformacion = Boolean.TRUE;
            }
            break;
        case FenixConstants.PA07_VALIDAR_CAMBIO_SCR_PERSPECTIVA:
            LOGGER.fine("PA07_VALIDAR_CAMBIO_SCR_PERSPECTIVA");
            LOGGER.warning("Validar cambio SCR y/o Perspectiva");

            /* EX01 Identifica que no se ha agregado un comentario.
                  * MSG_04 Es necesario agregar un comentario indicando las modificaciones a realizar.
                 */

            isValidMasInformacion =
                validarComentarioCliente(getIdCliente(), getCodigoTarea().toString(), getInstanciaTarea());

            if (!isValidMasInformacion) {
                bundleKeyErrorList.add("MSG04_VALIDAR_CAMBIO_SCR_PERSPECTIVA");
            } else {
                isValidMasInformacion = Boolean.TRUE;
            }
            break;
        case FenixConstants.PA07_VALIDAR_NUEVO_SCR:
            LOGGER.fine("PA07_VALIDAR_NUEVO_SCR");
            LOGGER.warning("Validar nuevo SCR");

            /* EX01 Identifica que no se ha agregado un comentario.
                  * MSG_01 Es necesario agregar un comentario indicando las modificaciones a realizar.
                 */

            isValidMasInformacion =
                validarComentarioCliente(getIdCliente(), getCodigoTarea().toString(), getInstanciaTarea());

            if (!isValidMasInformacion) {
                bundleKeyErrorList.add("MSG01_VALIDAR_NUEVO_SCR");
            } else {
                isValidMasInformacion = Boolean.TRUE;
            }
            break;
        default:
            LOGGER.log(ADFLogger.ERROR, "invocarMasInformacion(): Codigo de tarea no valido - " + codigoTarea);
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

    public void aplicaRetornaDeteriorado(Boolean masInformacion) {
        LOGGER.warning("Entra en aplicaRetornaDeteriorado.");
        SeguimientoCrediticioBean seguimientoCrediticioBean =
            (SeguimientoCrediticioBean) JSFUtils.resolveExpression("#{pageFlowScope.seguimientoCrediticioBean}");

        Integer estadoSCR = 0;
        Boolean esDeterioro = Boolean.FALSE;
        Integer estadoSCR5 = 5;
        Integer scrNuevo = 0;
        Integer estadoVigente = 2;
        Integer estadoDeteriorado = 4;

        AttributeBinding esDeteriorado;
        esDeteriorado = ADFUtils.findControlBinding("EsDeterioro");
        if (esDeteriorado != null && esDeteriorado.getInputValue() != null &&
            !esDeteriorado.getInputValue().toString().isEmpty()) {
            try {
                esDeterioro = (Boolean) esDeteriorado.getInputValue();
            } catch (Exception e) {
                LOGGER.warning("No se pudo obtener bandera Es Deteriorado");
            }
        }else{
            LOGGER.warning("Error el valor de EsDeterioro es nulo.");
        }

        LOGGER.warning("Es Deteriorado: " + esDeterioro);

        AttributeBinding estadoSCRActual;
        estadoSCRActual = ADFUtils.findControlBinding("IdTcaEstadoScr");
        if (estadoSCRActual != null && estadoSCRActual.getInputValue() != null &&
            !estadoSCRActual.getInputValue().toString().isEmpty()) {

            try {
                estadoSCR = (Integer) estadoSCRActual.getInputValue();
            } catch (Exception e) {
                LOGGER.warning("No se pudo obtener Estado SCR Actual");
            }
        }else{
            LOGGER.warning("Error el valor de IdTcaEstadoScr es nulo.");
        }

        LOGGER.warning("Estado SCR Actual: " + estadoSCR);

        AttributeBinding scrActual;
        scrActual = ADFUtils.findControlBinding("IdTcaScr");
        if (scrActual != null && scrActual.getInputValue() != null && !scrActual.getInputValue().toString().isEmpty()) {
            try {
                scrNuevo = (Integer) scrActual.getInputValue();
            } catch (Exception e) {
                LOGGER.warning("No se pudo obtener el SCR Nuevo");
            }
        }else{
            LOGGER.warning("Error el valor de IdTcaScr es nulo.");
        }

        LOGGER.warning("SCR Propuesto: " + scrNuevo);

        if (null != scrNuevo) {
            LOGGER.warning("Actualiza Payload para SCR Propuesto");
            actualizarPayloadElement("scrPropuesto", scrNuevo);
        }

        int codigoTarea = getCodigoTarea();

        if (null == esDeterioro || !esDeterioro) {
            estadoSCRActual.setInputValue(estadoVigente);
        } else {
            estadoSCRActual.setInputValue(estadoDeteriorado);
        }
        
        if (masInformacion) {

            switch (codigoTarea) {
            case FenixConstants.PA07_VALIDAR_CAMBIO_SCR_PERSPECTIVA:
                AttributeBinding cambioSCR;
                cambioSCR = ADFUtils.findControlBinding("CambiarSCR");
                AttributeBinding statusSeguimiento;
                statusSeguimiento = ADFUtils.findControlBinding("BanEstatus");

                Integer status = 1;
                LOGGER.warning("ActualizaPAyload Des y Mejora: " + masInformacion);
                Boolean esMejoraSCR = Boolean.FALSE;
                Boolean esDesmejoraSCR = Boolean.FALSE;
                if (null != cambioSCR.getInputValue()) {
                    if ((Boolean) cambioSCR.getInputValue()) {
                        if (null != seguimientoCrediticioBean.getScrVig()) {
                            if (null != scrNuevo) {
                                if (scrNuevo <= seguimientoCrediticioBean.getScrVig()) { //&& scrNuevo <= estadoSCR5
                                    esMejoraSCR = true;
                                    esDesmejoraSCR = false;
                                } else {
                                    esMejoraSCR = false;
                                    esDesmejoraSCR = true;
                                    if(scrNuevo <= estadoSCR5){
                                            statusSeguimiento.setInputValue(status);
                                        }
                                }

                            } else {
                                LOGGER.severe("No se obtiene SCR propuesto");
                                esMejoraSCR = false;
                                esDesmejoraSCR = true;
                            }
                        } else {
                            LOGGER.severe("No se obtiene SCR vigente");
                        }
                    }
                    else{       
                            statusSeguimiento.setInputValue(status);
                        }
                }
                else{
                        statusSeguimiento.setInputValue(status);
                    }
                LOGGER.warning("Mejora: " + esMejoraSCR);
                LOGGER.warning("DesMejora: " + esDesmejoraSCR);
                LOGGER.warning("Realizar revision de Calificacion de Riesgo");
                actualizarPayloadElement("esMejoraSCR", esMejoraSCR);
                actualizarPayloadElement("esDesmejoraSCR", esDesmejoraSCR);
                // else {
                //                    if (null != scrNuevo && scrNuevo <= estadoSCR5) {
                //                        esMejoraSCR = true;
                //                        esDesmejoraSCR = false;
                //                    } else {
                //                        esMejoraSCR = false;
                //                        esDesmejoraSCR = true;
                //                    }
                //                }
                break;
            default:
                break;
            }
        }
    }

    public String aceptarFinalizarTarea() {
        LOGGER.warning("Inside aceptarFinalizarTarea.");
        
        SeguimientoCrediticioBean seguimientoCrediticioBean =
            (SeguimientoCrediticioBean) JSFUtils.resolveExpression("#{pageFlowScope.seguimientoCrediticioBean}");

        Boolean condicion1 = Boolean.TRUE;
        Boolean condicion2 = Boolean.TRUE;
        Boolean condicion3 = Boolean.TRUE;
        
        aplicaRetornaDeteriorado(Boolean.TRUE);
        popupFinalizarTarea.hide();
        //guardar el estatus de seguimiento
        Integer status = 1;
        AttributeBinding statusSeguimiento;
        statusSeguimiento = ADFUtils.findControlBinding("BanEstatus");
        AttributeBinding nombreUsuario;
        nombreUsuario = ADFUtils.findControlBinding("NombreUsuarioUltimoCambio");
        AttributeBinding loginDUsuario;
        loginDUsuario = ADFUtils.findControlBinding("LoginUsuarioUltimoCambio");
        AttributeBinding fechaCambio;
        fechaCambio = ADFUtils.findControlBinding("FechaUltimoCambio");


        seguimientoCrediticioBean.setCambioAmbos(seguimientoCrediticioBean.getCambiaPerspectiva() ||
                                                 seguimientoCrediticioBean.getCambiaSCR());
        AttributeBinding cambioSCR;
        cambioSCR = ADFUtils.findControlBinding("CambiarSCR");

        LOGGER.warning("cambioSCR Finalizar" + cambioSCR);
        LOGGER.warning("cambioSCR BEAN" + seguimientoCrediticioBean.getCambiaSCR());      

        AttributeBinding cambioPerspectiva;
        cambioPerspectiva = ADFUtils.findControlBinding("CambiarPerspectiva");

        Boolean isValidFinalizarTarea = Boolean.FALSE;
        List<String> budleKeyErroList = new ArrayList<String>();

        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PA07_REALIZAR_REVISION_CALIFICACION_RIESGO:
            LOGGER.fine("PA07_REALIZAR_REVISION_CALIFICACION_RIESGO");
            LOGGER.warning("Realizar revision de Calificacion de Riesgo");

            AttributeBinding estadoSeg;
            estadoSeg = ADFUtils.findControlBinding("EstadoSeguimiento");
            String nuevo = "Nuevo";
            String estadoSeguimiento = (String) estadoSeg.getInputValue();
            LOGGER.warning("Valor EstadoSeguimiento : " + estadoSeguimiento);
            if (null != estadoSeguimiento && estadoSeguimiento.equalsIgnoreCase(nuevo)) {
                condicion1 = guardarCommit();
                if (!condicion1) {
                    LOGGER.warning("Error al guardar el seguimiento crediticio");
                }
            } else {
                if(aplicarGuardado()){
                        nombreUsuario.setInputValue(seguimientoCrediticioBean.getNombreUsuario());
                        loginDUsuario.setInputValue(seguimientoCrediticioBean.getLoginUsuario());
                        condicion1 = actualizarSeguimiento();
                    }
            }
            
            if (condicion1) {
                condicion2 = guardarTablaSCR();
                if (!condicion2) {
                    budleKeyErroList.add("MSG_ACTUALIZAR_SEGUIMIENTO2");

                }
            } else {
                LOGGER.warning("No se invoca guardarTablaSCR al no cumplirse la condicion1.");
            }
            
            LOGGER.warning("condicion1: " + condicion1);
            LOGGER.warning("condicion2: " + condicion2);
            LOGGER.warning("condicion3: " + condicion3);
            
            isValidFinalizarTarea = condicion1 && condicion2 && condicion3;
            if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                budleKeyErroList.add("MSG_ACTUALIZAR_SEGUIMIENTO");
            } else {
                if (null != cambioSCR.getInputValue()) {
                    if ((Boolean) cambioSCR.getInputValue()) {
                        seguimientoCrediticioBean.setCambioAmbos(Boolean.TRUE);
                    } else {
                        if (null != cambioPerspectiva.getInputValue()) {
                            if ((Boolean) cambioPerspectiva.getInputValue()) {
                                seguimientoCrediticioBean.setCambioAmbos(Boolean.TRUE);
                            } else {
                                if (statusSeguimiento != null) {
                                    LOGGER.warning("Status: " + statusSeguimiento.getInputValue());
                                    statusSeguimiento.setInputValue(status);
                                    LOGGER.warning("Status: " + statusSeguimiento.getInputValue());
                                } else {
                                    LOGGER.severe("No se pudo obtener Control Binding BanEstatus");
                                }
                            }
                        } else {
                            if (statusSeguimiento != null) {
                                LOGGER.warning("Status: " + statusSeguimiento.getInputValue());
                                statusSeguimiento.setInputValue(status);
                                LOGGER.warning("Status: " + statusSeguimiento.getInputValue());
                            } else {
                                LOGGER.severe("No se pudo obtener Control Binding BanEstatus");
                            }
                        }
                    }
                } else {
                    if (null != cambioPerspectiva.getInputValue()) {
                        if ((Boolean) cambioPerspectiva.getInputValue()) {
                            seguimientoCrediticioBean.setCambioAmbos(Boolean.TRUE);
                        }
                    }
                }
                AttributeBinding instanciaProceso1;
                instanciaProceso1 = ADFUtils.findControlBinding("InstanciaProceso1");
                instanciaProceso1.setInputValue(seguimientoCrediticioBean.getInstanciaProceso());
                LOGGER.warning("instanciaProceso1" + instanciaProceso1.getInputValue());
                LOGGER.warning("tipoInicio " + seguimientoCrediticioBean.getTipoInicio());
                LOGGER.warning("CAMBIOscr" + cambioSCR);
                LOGGER.warning("CAMBIOPerspectiva" + cambioPerspectiva);
                LOGGER.warning("CAMBIOscrAmbos" + seguimientoCrediticioBean.getCambioAmbos());
                if (seguimientoCrediticioBean.getTipoInicio().equalsIgnoreCase("RETORNO")) {
                    actualizarPayloadElement("cambioSCRoPerspectiva", Boolean.FALSE);
                } else {
                    actualizarPayloadElement("cambioSCRoPerspectiva", seguimientoCrediticioBean.getCambioAmbos());
                }
            }


            /* RN_01 Si el analista no asigna un valor de SCR específico a la operación, el aplicativo le asignará el SCR del cliente.
             * RN_09 Si se indica que es una calificación dividida, no se realizará la propagación de SCR al aplicativo de Reserva Patrimonial.
             * RN_10 Si como resultado de la revisión se determina que el Cliente se encuentra en deterioro, el cliente se marcará como deteriorado.
            */

            LOGGER.warning("Valida SCR de Operacion");
            /* if (!validarScrOperacion()) {
                    //Asignar SCR del cliente a la operacion
                } */
            LOGGER.warning("Valida Calificacion Dividida");
            /* if (validarCalificacionDividida()) {
                    //Propagar SCR
                } */
            LOGGER.warning("Valida Deterioro del Cliente");
            /* if (validarDeterioroCliente()) {
                    //Actualizar cliente como Deteriorado
                } */
            LOGGER.warning("Actualiza Informacion");
            //actualizarInformacion(); Actualizar informacion

            //    isValidFinalizarTarea = Boolean.TRUE;
            break;
        case FenixConstants.PA07_VALIDAR_CAMBIO_SCR_PERSPECTIVA:
            LOGGER.fine("PA07_VALIDAR_CAMBIO_SCR_PERSPECTIVA");
            LOGGER.warning("Validar cambio SCR y/o Perspectiva");
            condicion1 = Boolean.TRUE;
            if(aplicarGuardado()){
                    nombreUsuario.setInputValue(seguimientoCrediticioBean.getNombreUsuario());
                    loginDUsuario.setInputValue(seguimientoCrediticioBean.getLoginUsuario());
                condicion1 = actualizarSeguimiento();
            }

            LOGGER.warning("condicion1 " + condicion1);
            if (null == condicion1 || !condicion1) {
                budleKeyErroList.add("MSG_ACTUALIZAR_SEGUIMIENTO");
            }
            condicion2 = guardarTablaSCR();
            LOGGER.warning("condicion2 " + condicion2);
            if (!condicion2) {
                budleKeyErroList.add("MSG_ACTUALIZAR_SEGUIMIENTO2");

            }
            isValidFinalizarTarea = condicion1 && condicion2;

            break;
        case FenixConstants.PA07_VALIDAR_NUEVO_SCR:
            LOGGER.fine("PA07_VALIDAR_NUEVO_SCR");
            LOGGER.warning("Validar nuevo SCR");

            statusSeguimiento = ADFUtils.findControlBinding("BanEstatus");
            AttributeBinding esDemejorado;
            esDemejorado = ADFUtils.findControlBinding("esDesmejoraSCR");
            if (null != esDemejorado) {
                if ((Boolean) esDemejorado.getInputValue()) {
                    if (statusSeguimiento != null) {
                        LOGGER.warning("Status: " + statusSeguimiento.getInputValue());
                        statusSeguimiento.setInputValue(status);
                        nombreUsuario.setInputValue(seguimientoCrediticioBean.getNombreUsuario());
                        loginDUsuario.setInputValue(seguimientoCrediticioBean.getLoginUsuario());
                        LOGGER.warning("Status: " + statusSeguimiento.getInputValue());
                    } else {
                        LOGGER.severe("No se pudo obtener Control Binding BanEstatus");
                    }
                }
            }


            isValidFinalizarTarea = actualizarSeguimiento();
            if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                budleKeyErroList.add("MSG_ACTUALIZAR_SEGUIMIENTO");
            }

            LOGGER.warning("Actualiza Informacion");
            //actualizarInformacion(); Actualizar informacion

            break;
        case FenixConstants.PA07_ERROR_PROPAGAR_SCR:
                LOGGER.fine("PA07_ERROR_PROPAGAR_SCR");
                LOGGER.warning("Re-intentar propagar SCR");
                isValidFinalizarTarea = Boolean.TRUE;
        default:
            LOGGER.log(ADFLogger.ERROR, "<No se pudo encontrar la tarea>");
            break;
        }


        if (isValidFinalizarTarea) {
            LOGGER.warning("Se se guarda corectamtene el SCR");
            LOGGER.warning("Carga Documentos Cliente OnBase");
            cargarDocumentosClienteOnBase();
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        } else {
            LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): " + isValidFinalizarTarea);
            if (budleKeyErroList.size() > 0) {
                for (String bundleKey : budleKeyErroList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
            LOGGER.warning("Se cancela la confirmacion de finalizar tarea");
            return null;
        }
    }

    public String cancelarFinalizarTarea() {
        LOGGER.warning("Cancela confirmacion de Finalizar tarea.");
        popupFinalizarTarea.hide();
        return null;
    }

    public String aceptarMasInformacion() {
        LOGGER.warning("Inside aceptarMasInformacion.");
        popupMasInformacion.hide();
        aplicaRetornaDeteriorado(Boolean.FALSE);
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        List<String> budleKeyErroList = new ArrayList<String>();

        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PA07_REALIZAR_REVISION_CALIFICACION_RIESGO:
            LOGGER.fine("PA07_REALIZAR_REVISION_CALIFICACION_RIESGO");
            LOGGER.warning("Realizar revision de Calificacion de Riesgo");

            LOGGER.warning("Actualiza Informacion");
            //actualizarInformacion(); Actualizar informacion
          //  isValidFinalizarTarea = Boolean.TRUE;
            isValidFinalizarTarea =eliminarSCRSeguimiento();
            break;
        case FenixConstants.PA07_VALIDAR_CAMBIO_SCR_PERSPECTIVA:
            LOGGER.fine("PA07_VALIDAR_CAMBIO_SCR_PERSPECTIVA");
            LOGGER.warning("Validar cambio SCR y/o Perspectiva");
            isValidFinalizarTarea = actualizarSeguimiento();
            if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                budleKeyErroList.add("MSG_ACTUALIZAR_SEGUIMIENTO");
            }
            LOGGER.warning("Actualiza Informacion");
            //actualizarInformacion(); Actualizar informacion

            isValidFinalizarTarea = Boolean.TRUE;
            break;
        case FenixConstants.PA07_VALIDAR_NUEVO_SCR:
            LOGGER.fine("PA07_VALIDAR_NUEVO_SCR");
            LOGGER.warning("Validar nuevo SCR");

            LOGGER.warning("Actualiza Informacion");
            //actualizarInformacion(); Actualizar informacion

            isValidFinalizarTarea = Boolean.TRUE;
            break;
        default:
            LOGGER.log(ADFLogger.ERROR, "La tarea no esta espesificada");
            break;

        }

        if (isValidFinalizarTarea) {
            LOGGER.log("Se valida para finalizar talida y finaliza la tarea");
            LOGGER.warning("Carga Documentos Cliente OnBase");
            cargarDocumentosClienteOnBase();
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        } else {
            LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): " + isValidFinalizarTarea);
            if (budleKeyErroList.size() > 0) {
                for (String bundleKey : budleKeyErroList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
            return null;
        }
    }

    public String cancelarMasInformacion() {
        LOGGER.warning("Cancela confirmacion de Mas informacion.");
        popupMasInformacion.hide();

        return null;
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

    private Integer getCodigoTarea() {
        SeguimientoCrediticioBean seguimientoCrediticioBean =
            (SeguimientoCrediticioBean) JSFUtils.resolveExpression("#{pageFlowScope.seguimientoCrediticioBean}");

        if (null != seguimientoCrediticioBean.getCodigoTarea() &&
            seguimientoCrediticioBean.getCodigoTarea().trim().length() > 0) {
            return Integer.parseInt(seguimientoCrediticioBean.getCodigoTarea());
        }
        LOGGER.log(ADFLogger.ERROR, "<No se pudo optener el codigo tarea Codigo tarea>");
        return 0;
    }

    private Long getIdCliente() {
        SeguimientoCrediticioBean seguimientoCrediticioBean =
            (SeguimientoCrediticioBean) JSFUtils.resolveExpression("#{pageFlowScope.seguimientoCrediticioBean}");
        if (null != seguimientoCrediticioBean.getIdCliente() &&
            seguimientoCrediticioBean.getIdCliente().trim().length() > 0) {
            return Long.parseLong(seguimientoCrediticioBean.getIdCliente());
        }
        LOGGER.log(ADFLogger.ERROR, "<No se pudo obtener el idCLiente>");
        return 0L;
    }

    public Boolean validarComentarioCliente(Long idCliente, String idTcaTareaBpm, String IdInstanciaTarea) {
        LOGGER.warning("insideMethodValidarComentarioCliente");
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarComentarioCliente");

        operationBinding.getParamsMap().put("IdInstanciaTarea", IdInstanciaTarea);
        LOGGER.warning("IdInstanciaTarea");
        operationBinding.getParamsMap().put("idTcaTareaBpm", idTcaTareaBpm);
        LOGGER.warning("idTcaTareaBpm");
        operationBinding.getParamsMap().put("idCliente", idCliente);
        LOGGER.warning("idCliente");

        Boolean result = (Boolean) operationBinding.execute();
        LOGGER.warning("afterOperationExecute");
        Object object = operationBinding.getResult();
        LOGGER.warning("afteroperationBindingGetResult");
        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.log(ADFLogger.ERROR, "<No se validan los Comentarios del CLiente>");
            return null;
        }

        return (result);
    }

    public Boolean validarScrOperacion() {
        LOGGER.warning("insideMethodValidarScrOperacion");
        Boolean result = Boolean.FALSE;

        return (result);
    }

    public Boolean validarCalificacionDividida() {
        LOGGER.warning("insideMethodValidarCalificacionDividida");
        Boolean result = Boolean.FALSE;

        return (result);
    }

    public Boolean validarDeterioroCliente() {
        LOGGER.warning("insideMethodValidarDeterioroCliente");
        Boolean result = Boolean.FALSE;

        return (result);
    }

    public void cambioSCR(ValueChangeEvent vce) {
        vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
        SeguimientoCrediticioBean seguimientoCrediticioBean =
            (SeguimientoCrediticioBean) JSFUtils.resolveExpression("#{pageFlowScope.seguimientoCrediticioBean}");
        Boolean resultado = (Boolean) vce.getNewValue();
        LOGGER.warning("valor anterior: " + vce.getOldValue());
        LOGGER.warning("valor nuevo: " + vce.getNewValue());
        seguimientoCrediticioBean.setCambiaSCR(resultado);
        AttributeBinding cambioScr;
        cambioScr = ADFUtils.findControlBinding("CambiarSCR");
        LOGGER.warning("valor scr: " + cambioScr.getInputValue());
        AttributeBinding scrActual;
        scrActual = ADFUtils.findControlBinding("IdTcaScr");
        LOGGER.warning("SCR: " + scrActual.getInputValue());
        LOGGER.warning("SCRVig: " + seguimientoCrediticioBean.getScrVig());
        if (!resultado) {
            if (null == scrActual.getInputValue()) {
                scrActual.setInputValue(null);
            } else {
                cambioScr.setInputValue(vce.getNewValue());
                scrActual.setInputValue(seguimientoCrediticioBean.getScrVig() != null ? seguimientoCrediticioBean.getScrVig() : 0);
            }
        }
    }

    public void cambioPerspectiva(ValueChangeEvent cve) {
        cve.getComponent().processUpdates(FacesContext.getCurrentInstance());
        SeguimientoCrediticioBean seguimientoCrediticioBean =
            (SeguimientoCrediticioBean) JSFUtils.resolveExpression("#{pageFlowScope.seguimientoCrediticioBean}");
        Boolean resultado = Boolean.FALSE;
        resultado = (Boolean) cve.getNewValue();
        LOGGER.warning("valor anterior: " + cve.getOldValue());
        LOGGER.warning("valor nuevo: " + cve.getNewValue());
        seguimientoCrediticioBean.setCambiaPerspectiva(resultado);
        AttributeBinding cambioPerspectiva;
        cambioPerspectiva = ADFUtils.findControlBinding("CambiarPerspectiva");
        LOGGER.warning("valor scr: " + cambioPerspectiva.getInputValue());
        AttributeBinding perspectivaActual;
        perspectivaActual = ADFUtils.findControlBinding("IdTcaPerspectiva");
        LOGGER.warning("Perspectiva: " + perspectivaActual.getInputValue());
        LOGGER.warning("PerspectivaVig: " + seguimientoCrediticioBean.getPerspectivaVig());
        if (!resultado) {
            if (null == perspectivaActual.getInputValue()) {
                perspectivaActual.setInputValue(null);
            } else {
                perspectivaActual.setInputValue(seguimientoCrediticioBean.getPerspectivaVig() != null ? seguimientoCrediticioBean.getPerspectivaVig() : 0 );
            }
        }
    }

    public Boolean actualizarSeguimiento() {
        LOGGER.warning("Entra en actualizarSeguimiento");
        Boolean finaliza = Boolean.TRUE;

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("actualizarSeguimiento");
        finaliza = (Boolean) operationBinding.execute();
        LOGGER.warning("resultado= " + finaliza);
        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.warning("Error en actualizarSeguimiento.");
            return false;
        }
        return finaliza;
    }

    public Boolean guardarCommit() {
        LOGGER.warning("Dentro guardarCommit");
        Date fecha = new Date();
        java.sql.Timestamp fechaActual = new java.sql.Timestamp(fecha.getTime());

        AttributeBinding fechaRegistro;
        try{
            fechaRegistro = ADFUtils.findControlBinding("FechaRegistro");
            fechaRegistro.setInputValue(fechaActual);
        }catch(Exception e){
            LOGGER.severe("Error en set FechaRegistro :"+e);
        }

        LOGGER.warning("guardando seguimiento");
        OperationBinding commit = null;
        BindingContainer bindings = null;

        bindings = ADFUtils.getBindingContainer();
        commit = bindings.getOperationBinding("Commit");

        commit.execute();
        if (!commit.getErrors().isEmpty()) {
            // Manejo de errores
            LOGGER.warning("Error: " + commit.getErrors().toString());
            JSFUtils.addFacesErrorMessage(commit.getErrors().toString());
            return false;
        }
        
        LOGGER.warning("Fuera guardarCommit");
        return true;
    }

    public Boolean cancelarRollback() {
        OperationBinding rollback = null;
        BindingContainer bindings = null;

        bindings = ADFUtils.getBindingContainer();
        rollback = bindings.getOperationBinding("Rollback");

        rollback.execute();
        if (!rollback.getErrors().isEmpty()) {
            return false;
        }
        return true;
    }

    public Boolean guardarTablaSCR() {
        LOGGER.warning("Entra en guardarTablaSCR.");
        Boolean resultado = Boolean.TRUE;
        OperationBinding operationBinding = null;
        BindingContainer bindings = null;

        bindings = ADFUtils.getBindingContainer();
        operationBinding = bindings.getOperationBinding("actualizarSCRTabla");

        resultado = (Boolean) operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return false;
        }
        LOGGER.warning("resultado : " + resultado);
        return resultado;
    }
    
    public Boolean eliminarSCRSeguimiento() {
        Boolean resultado = Boolean.TRUE;
        OperationBinding operationBinding = null;
        BindingContainer bindings = null;

        bindings = ADFUtils.getBindingContainer();
        operationBinding = bindings.getOperationBinding("eliminarSeguimiento");

        resultado = (Boolean) operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return false;
        }
        return resultado;
    }
    
    public Boolean actualizarPerspectiva(Long idCliente, Integer idPerspectiva){
     LOGGER.warning("Inicia metodo actualizarPerspectiva"); 
        Boolean respuesta = Boolean.FALSE;
        
        LOGGER.warning("idCliente: "+idCliente); 
        LOGGER.warning("idPerspectiva: "+idPerspectiva); 
        
        if(idCliente == null || idPerspectiva == null){
            LOGGER.warning("*Error, los parametros idCliente y idPerspectiva son requeridos"); 
            JSFUtils.addFacesErrorMessage("Error no se pudo actualizar la perspectiva en cliente");
            return respuesta;
        }
    
        OperationBinding operationBinding = null;
        BindingContainer bindings = null;

        bindings = ADFUtils.getBindingContainer();
        operationBinding = bindings.getOperationBinding("actualizarIdPerspectiva");
        operationBinding.getParamsMap().put("idCliente", idCliente);
        operationBinding.getParamsMap().put("idPerspectiva", idPerspectiva);
        
         operationBinding.execute();
        
        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.warning("Error ->"+operationBinding.getErrors().toString());
            JSFUtils.addFacesErrorMessage("Error al actualizar la perspectiva ->"+operationBinding.getErrors().toString());
            respuesta = Boolean.FALSE;
        }else{
            respuesta = (Boolean)operationBinding.getResult();        
        }
           
        if(!respuesta){
            JSFUtils.addFacesErrorMessage("Error no se ha podido acualizar la perspectiva");
        }
           
    
     LOGGER.warning("Termina metodo actualizarPerspectiva"); 
    return respuesta;
    }
    
    public Boolean aplicarGuardado(){
        LOGGER.warning("Entra en aplicarGuardado.");
        Boolean respuesta=Boolean.FALSE;
        Integer resultado=null;
        Integer valor=1;
        OperationBinding operationBinding = null;
        BindingContainer bindings = null;

        bindings = ADFUtils.getBindingContainer();
        operationBinding = bindings.getOperationBinding("getRowState");
        operationBinding.execute();
        //resultado = (Boolean) operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
                LOGGER.warning("Error al detectar si la VO de seguimiento se encuentra modificada");
        }
        else{
                resultado=(Integer)operationBinding.getResult(); 
                LOGGER.warning("valor obtenido: "+ resultado);
            if(resultado.compareTo(valor)==0){
                    respuesta=Boolean.TRUE;
                }
            }
        LOGGER.warning("respuesta : " + respuesta);
        return respuesta;
        }

    
    
    
    
}
