package pa04enmiendasgenerichumantask.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.jbo.Row;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class EnmiendaActionsBean extends FenixActionBean {

    public static final String BUNDLE = "pa04enmiendasgenerichumantask.PA04EnmiendasGenericHumanTaskBundle";
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(EnmiendaActionsBean.class);

    private RichPopup popupFinalizarTarea;
    private RichPopup popupMasInformacion;
    private RichPopup popupCancelarEnmienda;
    private RichPopup popupEnviarAprobacion;
    private RichButton btnRefreshOgc;
    private String clientIdBtnRefreshOgc;    
    private boolean mostrarLabelRequiereAsjur=false;
    private boolean ValidaDocument =false;
    public EnmiendaActionsBean() {
        super();
    }

    public String invocarFinalizarTarea() {
        LOGGER.warning("Inicia validacion para mostrar mensaje de confirmacion de Finalizar tarea");
        EnmiendaBean enmiendaBean = (EnmiendaBean) JSFUtils.resolveExpression("#{pageFlowScope.enmiendaBean}");

        List<String> budleKeyErroList = new ArrayList<String>();
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        Boolean condicion1 = Boolean.FALSE;
        Boolean condicion2 = Boolean.FALSE;

        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PAC04_INGRESAR_ENMIENDA:
            LOGGER.fine("PC04_INGRESAR_ENMIENDA");
            LOGGER.warning("Ingresar Enmienda");
            //No existen niguna validacion
            isValidFinalizarTarea = validarSinMovimientos();
            LOGGER.warning("Fue correcto? " + isValidFinalizarTarea);
            if (isValidFinalizarTarea) {
                ValidaDocument=true;
            }
            
            
            
         // if (!isValidFinalizarTarea) {
             //   budleKeyErroList.add("MSG_ERROR_SIN_MOVIMIENTOS");
       // }
            //isValidFinalizarTarea = Boolean.TRUE;
            break;
        case FenixConstants.PAC04_REVISAR_ASPECTOS_LEGALES:
            LOGGER.fine("PC04_REVISAR_ASPECTOS_LEGALES");
            LOGGER.warning("Revisar Aspectos Legales");
            //RN06	Para poder finalizar la tarea de �Ingresar enmienda� es necesario que ninguna de
            //las enmiendas registradas se encuentre �Sin Movimiento� en el campo �acci�n�.
            //EX02 Enmiendas con estatus de acci�n: �Sin Movimiento�.
            //El sistema valida que no existan enmiendas registradas con el campo �acci�n�
            //se encuentre como �Sin movimiento� MSG02.
            //MSG02 Es obligatorio asignar movimiento a todas las enmiendas registradas.
            //Se comenta por que aun no es posible realizar dicha validacion, el MSG ya existe
            //budleKeyErroList.add("MSG02_REVISAR_ASPECTOS_LEGALES_DE_ENMIENDAS");

            //RN08  Debe anexar a la solicitud de enmiendas el documento con la �Opini�n y/o Proyecto de
            //Resoluci�n� obligatoriamente para terminar la tarea.
            //EX01 Informaci�n Incompleta.
            //El sistema valida que la informaci�n est� incompleta MSG01.
            //MSG01 Debe ingresar el documento con la opini�n y/o proyecto de resoluci�n.
            condicion1 =
                validateDocumento(getIdOperacion(), FenixConstants.DOCUMENTO_PROY_RES) ||
                validateDocumento(getIdOperacion(), FenixConstants.DOCUMENTO_OPINION_JURID);
            if (!condicion1) {
                budleKeyErroList.add("MSG01_REVISAR_ASPECTOS_LEGALES_DE_ENMIENDAS");
            }
            condicion2 = validarSinMovimientos();
            LOGGER.warning("Fue correcto? " + condicion2);
            if (!condicion2) {
                budleKeyErroList.add("MSG_ERROR_SIN_MOVIMIENTOS");
            }
            isValidFinalizarTarea = condicion1 && condicion2;

            break;
        case FenixConstants.PAC04_EMITIR_OPINION_GERENCIA_CREDITO:
            LOGGER.warning("Emitir openion de gerencia de credito");
            LOGGER.fine("PC04_PC04_EMITIR_OPINION_GERENCIA_CREDITO");
            //RN01  Se debe haber anexado a la solicitud de enmiendas el documento con la �opini�n de la
            //Gerencia de Cr�dito� o en su defecto la justificaci�n correspondiente a que no requiere del
            //documento. De lo contrario, el flujo no puede continuar.
            //EX01 Informaci�n Incompleta.
            //El sistema valida que la informaci�n est� incompleta.  MSG01
            //MSG01 Debe ingresar el anexo con la �opini�n de la Gerencia de Cr�dito�.
            condicion1 = enmiendaBean.getRevisoJefatura();
            if (!condicion1) {
                budleKeyErroList.add("MSG02_EMITIR_OPINION_DE_LA_GERENCIA_DE_CREDITO");
            }
            condicion2 = validateDocumento(getIdOperacion(), FenixConstants.DOCUMENTO_OPINION_GERENCIA_CREDITO);
            if (!condicion2) {
                budleKeyErroList.add("MSG01_EMITIR_OPINION_DE_LA_GERENCIA_DE_CREDITO");
            }
            isValidFinalizarTarea = condicion1 && condicion2;
            //RN02	Se debe haber seleccionado la opci�n de �se revis� con la jefatura�.
            //De lo contrario el flujo no puede continuar.
            //EX02 No ha revisado con la Jefatura y desea �Finalizar tarea�.
            //El sistema valida que no se haya seleccionado la opci�n de �Revis� con la jefatura� MSG02.
            //MSG02 Debe revisar con la jefatura para poder finalizar la tarea.
            //Se comenta por que aun no es posible realizar dicha validacion, el MSG ya existe
            //budleKeyErroList.add("MSG02_EMITIR_OPINION_DE_LA_GERENCIA_DE_CREDITO");

            //RN06	Una solicitud de enmienda no podr� ser asignada a la tarea de
            //�Opini�n de la Gerencia de Cr�dito� en m�s de una ocasi�n excepto si la solicitud regresa del
            //proceso de aprobaci�n.
            break;
        case FenixConstants.PAC04_REALIZAR_AJUSTES:
            LOGGER.warning("Realizar ajustes");
            LOGGER.fine("PC04_REALIZAR_AJUSTES");
            //EX01 � Informaci�n incompleta.
            //El sistema valida que la informaci�n este completa MSG01.
            //MSG01 Debe completar la informaci�n  requerida.
            isValidFinalizarTarea =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            if (isValidFinalizarTarea != null) {

                if (!isValidFinalizarTarea) {
                    budleKeyErroList.add("MSG01_REALIZAR_AJUSTES");
                }
            } else {
                LOGGER.severe("Resultado de la validacion de comentario es NULL");
                budleKeyErroList.add("MSG01_ERROR_VAL_COMENTARIO");
            }

            break;
        case FenixConstants.PAC04_AJUSTAR_ENMIENDA:
            LOGGER.warning("Ajustar enmienda");
            LOGGER.fine("PC04_AJUSTAR_ENMIENDA");
            String invocarFinalizarTareaOrigen = null;
            invocarFinalizarTareaOrigen =
                (String) JSFUtils.resolveExpression("#{viewScope.invocarFinalizarTareaOrigen}");
            LOGGER.warning("Ajustar enmienda? " + invocarFinalizarTareaOrigen);
            if (invocarFinalizarTareaOrigen.equalsIgnoreCase("enviarGerencia")) {
                //RN05 Para poder finalizar la tarea es necesario que ninguna de las enmiendas
                //registradas se encuentre �Sin Movimiento� en la acci�n.
                //EX02 Enmiendas con acci�n �Sin Movimiento� y selecciona opci�n �Enviar a Gerente�.
                //El sistema valida que no existan enmiendas registradas cuya acci�n se
                //encuentre como �Sin movimiento�. MSG02
                //MSG02	Es obligatorio asignar movimiento a todas las enmiendas registradas.
                //TO DO
                //Realizar la validacion.
                isValidFinalizarTarea = validarSinMovimientos();
                enmiendaRequeridos();
                LOGGER.warning("Fue correcto? " + isValidFinalizarTarea);
                if (!isValidFinalizarTarea) {
                    budleKeyErroList.add("MSG02_AJUSTAR_ENMIENDAS");
                }

                //TO DO
                //Asignar valor a la bandera Payload "enviarGerencia"
            }

            if (invocarFinalizarTareaOrigen.equalsIgnoreCase("enviarCOFI")) {
                //RN05 Para poder finalizar la tarea es necesario que ninguna de las enmiendas
                //registradas se encuentre �Sin Movimiento� en la acci�n.
                //Asignar valor a la bandera Payload "enviarCOFI"
                //EX02 Enmiendas con acci�n �Sin Movimiento� y selecciona opci�n �Enviar a Gerente�.
                //El sistema valida que no existan enmiendas registradas cuya acci�n se
                //encuentre como �Sin movimiento�. MSG02
                //MSG02	Es obligatorio asignar movimiento a todas las enmiendas registradas.
                //TO DO
                //Realizar la validacion.
                isValidFinalizarTarea = validarSinMovimientos();
                LOGGER.warning("Fue correcto? " + isValidFinalizarTarea);
                enmiendaRequeridos();
                if (!isValidFinalizarTarea) {
                    budleKeyErroList.add("MSG02_AJUSTAR_ENMIENDAS");
                }
                //TO DO
                //Asignar valor a la bandera Payload "enviarCOFI"

            }
            break;
        case FenixConstants.PAC04_EVALUAR_ENMIENDA:
            LOGGER.warning("Evaluar enmienda");
            LOGGER.fine("PC04_EVALUAR_ENMIENDA");
            isValidFinalizarTarea = Boolean.TRUE;
            break;
        case FenixConstants.PAC04_VALIDAR_REGISTRO_ENMIENDA:
            LOGGER.warning("Validar registro enmienda");
            LOGGER.fine("PC04_VALIDAR_REGISTRO_ENMIENDA");
            isValidFinalizarTarea = Boolean.TRUE;
            //                    String valida="validarTCC";
            //                    //El sistema actualiza la operaci�n en Flexcube con la informaci�n de la enmienda.
            //                     isValidFinalizarTarea = aprobarEnmiendas(valida);
            //                            if(null==isValidFinalizarTarea || !isValidFinalizarTarea){
            //                                    budleKeyErroList.add("Error de procesar los TCC");
            //                                }
            break;
        default:
            break;
        }

        LOGGER.warning("Finaliza e invoca mensaje de confirmacion? " + isValidFinalizarTarea);
        if (!isValidFinalizarTarea) {
          //  if (budleKeyErroList.size() > 0) {
           //     for (String bundleKey : budleKeyErroList)
             //       JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
             LOGGER.warning("No hubo documento ");
            //}
            finalizarTareaPopup();
        } else {
            finalizarTareaPopup();
        }
        return null;
    }

    public void finalizarTareaPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupFinalizarTarea.show(hints);
    }

    public boolean getLabelRequiereAsjur() {
        return this.mostrarLabelRequiereAsjur;
    }
    public void setLabelRequiereAsjur(boolean p_mostrarLabelRequiereAsjur) {
        this.mostrarLabelRequiereAsjur = p_mostrarLabelRequiereAsjur;
    } 
    
    public String invocarMasInformacion() {
        LOGGER.warning("Se valida las condiciones para mostrar mensaje de confirmacion al solicitar m�s informacion");
        EnmiendaBean enmiendaBean = (EnmiendaBean) JSFUtils.resolveExpression("#{pageFlowScope.enmiendaBean}");
        Boolean isValidMasInformacion = Boolean.FALSE;
        List<String> budleKeyErroList = new ArrayList<String>();
        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PAC04_EMITIR_OPINION_GERENCIA_CREDITO:
            LOGGER.warning("Emitir Opinion Gerencia credito");
            LOGGER.fine("PC04_EMITIR_OPINION_GERENCIA_CREDITO");
            //RN03 Si la solicitud  se va a �Retornar� entonces El analista de cr�dito debe ingresar comentarios.
            //de lo contrario, no puede continuar con el flujo.
            //EX03 No ha ingresado comentarios y selecciona la opci�n �Retornar�.
            //El sistema detecta que no se ha ingresado comentarios y selecciona la opci�n de �Retornar� MSG03.
            //MSG03 Es necesario ingresar las observaciones para poder finalizar la tarea.
            isValidMasInformacion =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            budleKeyErroList.add("MSG03_EMITIR_OPINION_DE_LA_GERENCIA_DE_CREDITO");
            break;
        case FenixConstants.PAC04_AJUSTAR_ENMIENDA:
            LOGGER.fine("PC04_AJUSTAR_ENMIENDA");
            LOGGER.warning("Ajustar enmienda");
            //RN03 La obligatoriedad de los campos requeridos en el registro de enmienda
            //depender� de la definici�n del t�rmino, condici�n o comisi�n correspondiente
            //definida en la matriz TCC.
            //EX03 No ha ingresado comentarios y selecciona la opci�n �Enviar a ASJUR�.
            //El sistema detecta que no se ha ingresado comentarios y selecciona la opci�n
            //de �Enviar a ASJUR�. MSG03
            //MSG03 Es necesario ingresar las observaciones para poder finalizar la tarea.
            boolean requiereAsjur = enmiendaBean.getRequiereASJUR();
            
            if (!requiereAsjur){    
                setLabelRequiereAsjur(true);
                LOGGER.warning("FINALIZA Ajustar enmienda requiereASJUR es "+enmiendaBean.getRequiereASJUR());
            }
            else
            {
                setLabelRequiereAsjur(false);    
            }
            
            isValidMasInformacion =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            enmiendaRequeridos();
            budleKeyErroList.add("MSG03_AJUSTAR_ENMIENDAS");
            //TO DO
            //Asignar valor a la bandera Payload "enviarASJUR"
            break;
        case FenixConstants.PAC04_EVALUAR_ENMIENDA:
            LOGGER.fine("PC04_EVALUAR_ENMIENDA");
            LOGGER.warning("Evaluar enmienda");
            //RN02	Si la solicitud requiere �Solicitar ajustes� o requiere ser �Cancelada� entonces
            //Obligatoriamente el gerente de pa�s debe ingresar comentarios.De lo contrario, el flujo no
            //puede continuar.
            //EX01 Informaci�n Incompleta.
            //El sistema valida que la informaci�n est� incompleta.  MSG01
            //MSG01	Es necesario ingresar las observaciones para poder finalizar la tarea.
            isValidMasInformacion =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            budleKeyErroList.add("MSG01_EVALUAR_ENMIENDAS");
            break;
        case FenixConstants.PAC04_VALIDAR_REGISTRO_ENMIENDA:
            LOGGER.warning("validar registro de enmienda");
            LOGGER.fine("PC04_VALIDAR_REGISTRO_ENMIENDA");
            //RN02 Si la solicitud requiere �Solicitar ajustes� entonces,Se deber� ingresar comentarios
            //obligatoriamente.De lo contrario, no se podr� continuar con el flujo.
            //EX01 Informaci�n Incompleta.
            //El sistema valida que la informaci�n est� incompleta.  MSG01
            //MSG01	Debe ingresar la informaci�n requerida.
            isValidMasInformacion =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            budleKeyErroList.add("MSG01_REGISTRO_DE_ENMIENDA");
            break;
        default:
            break;
        }
        LOGGER.warning("Mas informacion fue correcto para mostrar mensaje de confirmacion? " + isValidMasInformacion);
        if (!isValidMasInformacion) {

            if (budleKeyErroList.size() > 0) {
                for (String bundleKey : budleKeyErroList)
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

    public String invocarCancelarEnmienda() {
        LOGGER.warning("Se invoca validaci�n de cancelar enmienda antes de mostrar el popup de confirmacion");
        EnmiendaBean enmiendaBean = (EnmiendaBean) JSFUtils.resolveExpression("#{pageFlowScope.enmiendaBean}");
        Boolean isValidCancelarEnmienda = Boolean.FALSE;
        List<String> budleKeyErroList = new ArrayList<String>();
        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PAC04_AJUSTAR_ENMIENDA:
            LOGGER.fine("PC04_AJUSTAR_ENMIENDA");
            LOGGER.warning("Ajustar enmienda");
            //RN12 Si la solicitud requiere ser �Cancelada� entonces Obligatoriamente debe ingresar comentarios.
            //de lo contrario, el flujo no puede continuar.
            //EX01 Informaci�n incompleta del registro de enmienda.
            //El sistema valida que no se han ingresado los datos obligatorios. MSG01
            //MSG01 Debe ingresar la informaci�n requerida.
            isValidCancelarEnmienda =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            budleKeyErroList.add("MSG01_AJUSTAR_ENMIENDA");
            break;
        case FenixConstants.PAC04_EVALUAR_ENMIENDA:
            LOGGER.fine("PC04_EVALUAR_ENMIENDA");
            LOGGER.warning("Evaluar enmienda");
            //RN02 Si la solicitud requiere �Solicitar ajustes� o requiere ser �Cancelada� entonces
            //Obligatoriamente el gerente de pa�s debe ingresar comentarios.
            //De lo contrario, el flujo no puede continuar.
            //EX01 Informaci�n Incompleta.
            //El sistema valida que la informaci�n est� incompleta.  MSG01
            //MSG01	Es necesario ingresar las observaciones para poder finalizar la tarea.
            isValidCancelarEnmienda =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            budleKeyErroList.add("MSG01_EVALUAR_ENMIENDAS");
            break;
            //DEFAULT CASE
        default:
            break;
        }
        LOGGER.warning("Cancelar enmienda fue correcto para mostrar el mensaje de confirmacion? " +
                       isValidCancelarEnmienda);
        if (!isValidCancelarEnmienda) {
            if (budleKeyErroList.size() > 0) {
                for (String bundleKey : budleKeyErroList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }

        } else {

            cancelarEnmiendaPopup();

        }
        return null;
    }

    public void cancelarEnmiendaPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupCancelarEnmienda.show(hints);
    }

    public String invocarEnviarAprobacion() {
        LOGGER.warning("Se hacen las validaciones para mostrar mensaje de confirmacion al Enviar a aprobacion");
        Boolean isValidEnviarAprobacion = Boolean.FALSE;
        List<String> budleKeyErroList = new ArrayList<String>();
        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PAC04_EVALUAR_ENMIENDA:
            LOGGER.warning("Evaluar enmienda");
            LOGGER.fine("PC04_EVALUAR_ENMIENDA");
            //FA01 Enviar a aprobaci�n.
            //String valida="aprobacion";
            isValidEnviarAprobacion = Boolean.TRUE;
            break;
            //DEFAULT CASE
        default:
            break;
        }
        LOGGER.warning("Enviar aprobacion fue correcto para mostrar el mensaje de confirmacion? " +
                       isValidEnviarAprobacion);
        if (!isValidEnviarAprobacion) {
            if (budleKeyErroList.size() > 0) {
                for (String bundleKey : budleKeyErroList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        } else {

            enviarAprobacionPopup();

        }

        return null;
    }

    public void enviarAprobacionPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupEnviarAprobacion.show(hints);
    }

    public String aceptarFinalizarTarea() {
        LOGGER.warning("Inicia confirmacion de Finalizar tarea");
        Boolean continua = Boolean.TRUE;
        EnmiendaBean enmiendaBean = (EnmiendaBean) JSFUtils.resolveExpression("#{pageFlowScope.enmiendaBean}");
        LOGGER.warning("Id de enmienda= " + enmiendaBean.getIdEnmienda());
        List<String> budleKeyErroList = new ArrayList<String>();
        int codigoTarea = getCodigoTarea();
        LOGGER.warning("Codigo de tarea" + codigoTarea);
        switch (codigoTarea) {
        case FenixConstants.PAC04_INGRESAR_ENMIENDA:
            LOGGER.warning("Ingresar enmienda:");
            Boolean requiereOT = Boolean.FALSE;
            Boolean requiereASJUR = Boolean.FALSE;
            System.out.println("Inicia Ingresar enmienda");
            if (enmiendaRequeridos()) {
                LOGGER.warning("requiereOGC: " + enmiendaBean.getRequiereGerenciaCredito());
                actualizarPayloadElement("requiereOGC", enmiendaBean.getRequiereGerenciaCredito());
                //siRequiereASJUR(null);
                siRequiereOT(null);
                
                LOGGER.warning("requiereASJUR: " + enmiendaBean.getRequiereASJUR());
                actualizarPayloadElement("requiereASJUR", enmiendaBean.getRequiereASJUR());
                
                //TODO pendiente actualizar elemento requiereOT falta definir por parte de BPM

                //actualizarPayloadElement("idEnmienda",enmiendaBean.getIdEnmienda().intValue());
         
            } else {
                budleKeyErroList.add("MSG_ERROR_REQUERIDO");
                continua = Boolean.FALSE;
            }
            break;
        case FenixConstants.PAC04_VALIDAR_REGISTRO_ENMIENDA:
            LOGGER.warning("Validar registro enmienda");
            String valida = "flexcube";
            //El sistema actualiza la operaci�n en Flexcube con la informaci�n de la enmienda.
            continua = aprobarEnmiendas(valida);
            if (null == continua || !continua) {
                budleKeyErroList.add("MSG_ERROR_ACTUALIZAR_TCC");
                LOGGER.warning("Error de procesar los TCC");
            }
            break;
        case FenixConstants.PAC04_EVALUAR_ENMIENDA:
            LOGGER.warning("Evaluar enmienda");
            String validaEvaluar = "aprobacion";
            continua = aprobarEnmiendas(validaEvaluar);
            if (null == continua || !continua) {
                budleKeyErroList.add("MSG_ERROR_ACTUALIZAR_TCC");
                LOGGER.warning("Error de procesar los TCC");
            }
            break;

        case FenixConstants.PAC04_REVISAR_ASPECTOS_LEGALES:
            LOGGER.warning("Revisar Aspectos Legales");
            siRequiereASJUR(null);
            siRequiereOT(null);
            actualizarPayloadElement("requiereASJUR", enmiendaBean.getRequiereASJUR());
            //TODO pendiente actualizar elemento requiereOT falta definir por parte de BPM
            continua = Boolean.TRUE;
            break;
        case FenixConstants.PAC04_EMITIR_OPINION_GERENCIA_CREDITO:
            LOGGER.warning("Emitir opinion de gerencia de credito");
            LOGGER.fine("PC04_PC04_EMITIR_OPINION_GERENCIA_CREDITO");
            continua = Boolean.TRUE;
            break;
        case FenixConstants.PAC04_REALIZAR_AJUSTES:
            LOGGER.warning("Realizar ajustes");
            LOGGER.fine("PC04_REALIZAR_AJUSTES");
            if (enmiendaRequeridos()) {
                LOGGER.warning("requiereOGC: " + enmiendaBean.getRequiereGerenciaCredito());
                actualizarPayloadElement("requiereOGC", enmiendaBean.getRequiereGerenciaCredito());
                //siRequiereASJUR(null);
               // siRequiereOT(null);
                
                LOGGER.warning("requiereASJUR: " + enmiendaBean.getRequiereASJUR());
                actualizarPayloadElement("requiereASJUR", enmiendaBean.getRequiereASJUR());
                
                //TODO pendiente actualizar elemento requiereOT falta definir por parte de BPM
               
            } else {
                budleKeyErroList.add("MSG_ERROR_REQUERIDO");
                continua = Boolean.FALSE;
            }
    
            continua = Boolean.TRUE;
            break;
        case FenixConstants.PAC04_AJUSTAR_ENMIENDA:
            LOGGER.warning("Ajustar enmienda");
            LOGGER.fine("PC04_AJUSTAR_ENMIENDA");
            
            //siRequiereASJUR(null);
            //siRequiereOT(null);
            actualizarPayloadElement("requiereOGC", enmiendaBean.getRequiereGerenciaCredito());
            actualizarPayloadElement("requiereASJUR", enmiendaBean.getRequiereASJUR());
            
            try
            { 
                LOGGER.warning("Ajustar enmienda actualizarRequeridosEnmienda idEnmienda "+enmiendaBean.getIdEnmienda());
                LOGGER.warning("Ajustar enmienda actualizarRequeridosEnmienda requiereASJUR "+enmiendaBean.getRequiereASJUR());
                LOGGER.warning("Ajustar enmienda actualizarRequeridosEnmienda reqOpinionGerenciaCredito "+enmiendaBean.getRequiereGerenciaCredito());
                
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("actualizarRequeridosEnmienda");
                operationBinding.getParamsMap().put("idEnmienda", enmiendaBean.getIdEnmienda());
                operationBinding.getParamsMap().put("requiereASJUR", enmiendaBean.getRequiereASJUR());
                operationBinding.getParamsMap().put("reqOpinionGerenciaCredito",  enmiendaBean.getRequiereGerenciaCredito());
                operationBinding.execute();
                
                //TODO pendiente actualizar elemento requiereOT falta definir por parte de BPM
           
               
                LOGGER.warning("Ajustar enmienda actualizarRequeridosEnmienda termino = "+operationBinding.toString());
            } 
            catch(Exception exp)
            {
                    LOGGER.warning("Ajustar enmienda error: "+exp.getMessage());
            }
            //TODO pendiente actualizar elemento requiereOT falta definir por parte de BPM
            continua = Boolean.TRUE;
            break;
        default:
            continua = Boolean.FALSE;
            budleKeyErroList.add("Error de la tarea de enmienda");
            break;

        }
        LOGGER.warning("Finaliza tarea popup?" + continua);
        popupFinalizarTarea.hide();
        if (null == continua || !continua) {
            if (budleKeyErroList.size() > 0) {
                for (String bundleKey : budleKeyErroList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
            LOGGER.warning("Se cancela la confirmacion de finalizar tarea");
            return null;
        } else {
            
          
            LOGGER.warning("Fue correcto? " + ValidaDocument);
            if (ValidaDocument) {
            LOGGER.warning("Carga Documentos");
                cargarDocumentosOnBase();//
            }
          
            LOGGER.warning("Inicia Invoke de Finalizar tarea");
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();

        }
    }

    public String cancelarFinalizarTarea() {
        LOGGER.warning("Cancela confirmacion de finalizar tarea");
        popupFinalizarTarea.hide();
        return null;

    }

    public String aceptarCancelarEnmienda() {
        LOGGER.warning("Inicia Confirmacion de Cancelar enmiendas");
        InvokeActionBean invokeActionBean = null;
        Boolean esEliminado = Boolean.FALSE;
        popupCancelarEnmienda.hide();
        try{
            esEliminado = eliminarTccEnmendado();
            if(esEliminado){
                LOGGER.warning("Carga documentos");
                cargarDocumentosOnBase();
                LOGGER.warning("Inicia invoke de Cancelar enmienda");
                invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            }else{
                LOGGER.warning("Ocurrio un error al eliminar los tcc, no se finaliza la tarea.");
            }
        }catch(Exception e){
            LOGGER.warning("Error en aceptarCancelarEnmienda.", e);
        }
        return invokeActionBean.invokeOperation();
    }

    public String cancelarCancelarEnmienda() {
        LOGGER.warning("Cancela la cancelacion de enmienda");
        popupCancelarEnmienda.hide();
        return null;
    }
    
    private Boolean eliminarTccEnmendado(){
        LOGGER.warning("Entra en eliminarTccEnmendado.");
        BindingContainer bindings = null;
        OperationBinding operationBinding = null;
        Boolean esEliminado = Boolean.FALSE;
        try{
            bindings = ADFUtils.getBindingContainer();
            
            operationBinding = bindings.getOperationBinding("eliminarTccAlCancelarEnmiendas");
            operationBinding.execute();
            
            if(null != operationBinding.getResult()){
                esEliminado = (Boolean)operationBinding.getResult();   
            }else{
                LOGGER.warning("Error al obtener el resultado.");
            }
            if(!esEliminado){
                JSFUtils.addFacesErrorMessage("Error al eliminar los Tcc enmendados.");
            }else{
                LOGGER.warning("Tcc enmendados eliminados correctamente.");
            }
        }catch(Exception e){
            LOGGER.warning("Error en eliminarTccEnmendado.", e);
        }
        return esEliminado;
    }
    public String aceptarEnviarAprobacion() {
        LOGGER.warning("Inicia la confirmacion de enviar aprobacion");
        EnmiendaBean enmiendaBean = (EnmiendaBean) JSFUtils.resolveExpression("#{pageFlowScope.enmiendaBean}");
        LOGGER.warning("Id de enmienda= " + enmiendaBean.getIdEnmienda());
        Boolean isValidEnviarAprobacion = Boolean.TRUE;
        String valida = "aprobacion";
        
        // Inicializos el grid con la transaccion actual de la tarea para la FNXII-6767
        // El componente de matrizTcc no comparte su transaccion
        // Para la tarea evaluar enmienda solo se carga la modalida 2 (Grid)
        if (cargarGridConTransaccionDeTarea()) {
            isValidEnviarAprobacion = aprobarEnmiendas(valida);
            popupEnviarAprobacion.hide();
            LOGGER.warning("Se confirma el envio de aprobacion? " + isValidEnviarAprobacion);
            if (isValidEnviarAprobacion) {
                LOGGER.warning("Carga documentos");
                cargarDocumentosOnBase();
                LOGGER.warning("Incia invoke de Enviar aprobacion");
                InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                return invokeActionBean.invokeOperation();
            } else {
                LOGGER.warning("se cancela la confirmacion de enviar a aprobacion");
                return null;
            }
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public boolean cargarGridConTransaccionDeTarea() {
        LOGGER.warning("Entrando en cargarGridConTransaccionDeTarea.");
        EnmiendaBean enmiendaBean = (EnmiendaBean) JSFUtils.resolveExpression("#{pageFlowScope.enmiendaBean}");
        oracle.jbo.domain.Number idOperacionNumber = null;
        oracle.jbo.domain.Number idEnmiendaNumber = null;
        boolean cargaExitosa = Boolean.FALSE;
            
        try {
            idOperacionNumber = new oracle.jbo.domain.Number(enmiendaBean.getIdOperacion());
            idEnmiendaNumber = new oracle.jbo.domain.Number(enmiendaBean.getIdEnmienda());
        } catch (Exception e) {
            LOGGER.warning("Error al obtener el idOperacion o idEnmienda.", e);
        }
        
        LOGGER.warning("idOperacionNumber: " + idOperacionNumber);
        LOGGER.warning("idEnmiendaNumber: " + idEnmiendaNumber);
        
        if (null != idOperacionNumber && null != idEnmiendaNumber) {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("iniciaMatrizTccGrid");
            operationBinding.getParamsMap().put("idOperacion", idOperacionNumber);
            operationBinding.getParamsMap().put("idEnmienda", idEnmiendaNumber);
            operationBinding.execute();
            
            if (!operationBinding.getErrors().isEmpty()) {
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else {
                cargaExitosa = Boolean.TRUE;
            }
        }
        
        return cargaExitosa;
    }

    public String cancelarEnviarAprobacion() {
        popupEnviarAprobacion.hide();
        return null;
    }

  





    public String aceptarMasInformacion() {
        LOGGER.warning("Se inicia la confirmacion de mas informacion");
        popupMasInformacion.hide();
        int codigoTarea = getCodigoTarea();
        Row row = null;
        Boolean requiereOgc = null;
        Boolean requiereAsjur = null;
        
        Integer requiereOgcInt = null;
        Integer requiereAsjurInt = null;

        EnmiendaBean enmiendaBean = (EnmiendaBean) JSFUtils.resolveExpression("#{pageFlowScope.enmiendaBean}");
        
        boolean requiereAsjurX = enmiendaBean.getRequiereASJUR();
        
        if (!requiereAsjurX && codigoTarea==FenixConstants.PAC04_AJUSTAR_ENMIENDA){  
            LOGGER.warning("INICIA Ajustar enmienda requiereASJUR es "+enmiendaBean.getRequiereASJUR());
            enmiendaBean.setRequiereASJUR(Boolean.TRUE);
            actualizarPayloadElement("requiereASJUR", enmiendaBean.getRequiereASJUR());
            LOGGER.warning("FINALIZA Ajustar enmienda requiereASJUR es "+enmiendaBean.getRequiereASJUR());
        }
               
        LOGGER.warning("Codigo de tarea" + codigoTarea);
        switch (codigoTarea) {
        
        case FenixConstants.PAC04_AJUSTAR_ENMIENDA:
            LOGGER.warning("Ajustar enmienda");
            
            row = getEnmiendaTccById();
            
            if(null != row){
                
                if(row.getAttribute("RequiereOgc") != null){
                    LOGGER.warning("requiereOGC: " + row.getAttribute("RequiereOgc"));
                    requiereOgcInt = (Integer) row.getAttribute("RequiereOgc");
                    if(requiereOgcInt == 1 || requiereOgcInt.equals(new Integer(1))){
                        LOGGER.warning("RequiereOGC es true");
                        requiereOgc = Boolean.TRUE;
                    }else{
                        LOGGER.warning("RequiereOGC es false");
                        requiereOgc = Boolean.FALSE;
                    }
                }else{
                    LOGGER.warning("RequiereOGC es nulo");
                    requiereOgc = Boolean.FALSE;
                }
                
                actualizarPayloadElement("requiereOGC", requiereOgc);
                
                
                if(row.getAttribute("RequiereAsjur") != null){
                    LOGGER.warning("requiereASJUR: " + row.getAttribute("RequiereAsjur"));
                    requiereAsjurInt = (Integer) row.getAttribute("RequiereAsjur");
                    if(requiereAsjurInt == 1 || requiereAsjurInt.equals(new Integer(1))){
                        LOGGER.warning("RequiereAsjur es true");
                        requiereAsjur = Boolean.TRUE;
                    }else{
                        LOGGER.warning("RequiereAsjur es false");
                        requiereAsjur = Boolean.FALSE;
                    }
                }else{
                    LOGGER.warning("RequiereAsjur es nulo");
                    requiereAsjur = Boolean.FALSE;
                }
                
                actualizarPayloadElement("requiereASJUR",requiereAsjur);
                
            }else{
                LOGGER.severe("Row es null, no set RequiereOGC y requiereASJUR");
            }
            
            break;
        default:
            LOGGER.warning("Cualquier otra tarea de Enmienda");
            break;

        }
        LOGGER.warning("Carga documentos");
        cargarDocumentosOnBase();
        LOGGER.warning("Inicia el invoke de solicitar mas informacion");
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    
    public Row getEnmiendaTccById(){
        LOGGER.warning("Dentro de getEnmiendaTccById");
        Row row = null;
        Long idEnmienda = null;
 
        BindingContainer bindings = null;
        EnmiendaBean enmiendaBean = (EnmiendaBean) JSFUtils.resolveExpression("#{pageFlowScope.enmiendaBean}");
        
        idEnmienda = enmiendaBean.getIdEnmienda();
        
        LOGGER.warning("idEnmienda :"+idEnmienda);
        
        bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("getRowEnmiendaTCC");
        operationBinding.getParamsMap().put("idEnmienda", idEnmienda);
        
        row = (Row) operationBinding.execute();
        LOGGER.warning("row :" + row);
        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.warning("Error al ejecutar getEnmiendaTccById");
        }
        
        LOGGER.warning("Fuera de getEnmiendaTccById");
        
        return row;
    }

    public String cancelarMasInformacion() {
        popupMasInformacion.hide();
        return null;
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

    public RichPopup getPopupCancelarEnmienda() {
        return popupCancelarEnmienda;
    }

    public void setPopupCancelarEnmienda(RichPopup popupCancelarEnmienda) {
        this.popupCancelarEnmienda = popupCancelarEnmienda;
    }

    public RichPopup getPopupEnviarAprobacion() {
        return popupEnviarAprobacion;
    }

    public void setPopupEnviarAprobacion(RichPopup popupEnviarAprobacion) {
        this.popupEnviarAprobacion = popupEnviarAprobacion;
    }

    private Integer getCodigoTarea() {
        EnmiendaBean enmiendaBean = (EnmiendaBean) JSFUtils.resolveExpression("#{pageFlowScope.enmiendaBean}");

        if (null != enmiendaBean.getCodigoTarea() && enmiendaBean.getCodigoTarea().trim().length() > 0) {
            return Integer.parseInt(enmiendaBean.getCodigoTarea());
        }
        return 0;
    }

    private Long getIdOperacion() {
        EnmiendaBean enmiendaBean = (EnmiendaBean) JSFUtils.resolveExpression("#{pageFlowScope.enmiendaBean}");

        if (null != enmiendaBean.getIdOperacion() && enmiendaBean.getIdOperacion().trim().length() > 0) {
            return Long.parseLong(enmiendaBean.getIdOperacion());
        }

        return 0L;
    }

    public Boolean enmiendaRequeridos() {
      
        LOGGER.warning("Inicia metodo de enmiendas Rqueridos");
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("actualizarRequeridosEnmienda");
        LOGGER.warning("Inicia metodo AM de actualizar Requeridos enmienda");
        resultado = (Boolean) operationBinding.execute();
        System.out.println("Resultado= " + resultado);
        LOGGER.warning("Resultado= " + resultado);
        return resultado;
    }

    public Boolean validarSinMovimientos() {
        LOGGER.warning("Inicia metodo de validar enmiendas sin movimientos");
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("enmiendasSinMovimento");
        LOGGER.warning("Se ejecuta metodo AM Enmiendas sin movimientos");
        resultado = (Boolean) operationBinding.execute();
        LOGGER.warning("resultado= " + resultado);
        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.warning("Error al ejecutar e metodo de enmiendas sin movimiento");
            resultado=Boolean.FALSE;
        }
        LOGGER.warning("Resultado metodo AM Enmiendas sin movimientos: "+resultado);
        return resultado;
    }

    public Boolean aprobarEnmiendas(String comisionFlexcube) {
        LOGGER.warning("Inicia metodo de aprobar enmiendas y mandar a flexcube");
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("enmiendasAprobarYFlexcube");
        operationBinding.getParamsMap().put("comision", comisionFlexcube);
        LOGGER.warning("Inicia metodo AM enmiendas Aprobar y enmiendas a Flexcube");
        resultado = (Boolean) operationBinding.execute();
        LOGGER.warning("Resultado= " + resultado);
        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.warning("Error");
            return false;
        }
        return resultado;
    }
    


    public void siRequiereOGC(ActionEvent actionEvent) { 
        EnmiendaBean enmiendaBean = null;
        LOGGER.warning("************Into siRequiereOGC************");
        enmiendaBean = (EnmiendaBean) JSFUtils.resolveExpression("#{pageFlowScope.enmiendaBean}");
        if(null != enmiendaBean){
            
            if (enmiendaBean.siRequiereOGC()) {
                enmiendaBean.setRequiereGerenciaCredito(Boolean.TRUE);
                enmiendaBean.setActivaCheckOCG(Boolean.TRUE);
            } else {
                enmiendaBean.setRequiereGerenciaCredito(Boolean.FALSE);
                enmiendaBean.setActivaCheckOCG(Boolean.FALSE);
            }
            
            if (enmiendaBean.siRequiereASJUR()) {
                enmiendaBean.setRequiereASJUR(Boolean.TRUE);
                enmiendaBean.setActivaCheckASJUR(Boolean.TRUE);
            } else {
                enmiendaBean.setRequiereASJUR(Boolean.FALSE);
                enmiendaBean.setActivaCheckASJUR(Boolean.FALSE);
            }
            
            LOGGER.warning("activaCheckOCG :" + enmiendaBean.getActivaCheckOCG());
            LOGGER.warning("requiereGerenciaCredito :" + enmiendaBean.getRequiereGerenciaCredito());
            
            LOGGER.warning("activaCheckASJUR :" + enmiendaBean.getActivaCheckASJUR());
            LOGGER.warning("requiereASJUR :" + enmiendaBean.getRequiereASJUR());
            
        }
//        LOGGER.warning("Inicia metodo de obtener requiere OGC");
//        Boolean resultado = Boolean.TRUE;
//        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
//        OperationBinding operationBinding = bindings.getOperationBinding("precargaOGC");
//        Long operacion = Long.parseLong(enmiendaBean.getIdOperacion());
//        Number codigoDOperacion = (Number) operacion;
//        LOGGER.warning("codigo operacion: " + codigoDOperacion);
//        operationBinding.getParamsMap().put("idOperacion", codigoDOperacion);
//        LOGGER.warning("Inicia metodo AM determinar si rquiere CheckOCG");
//        resultado = (Boolean) operationBinding.execute();
//        LOGGER.warning("Resultado= " + resultado);
//        LOGGER.warning("Resultado2= " + (Boolean) operationBinding.getResult());
//        if (!operationBinding.getErrors().isEmpty() || !resultado) {
//            enmiendaBean.setActivaCheckOCG(Boolean.FALSE);
//            enmiendaBean.setRequiereGerenciaCredito(Boolean.FALSE);
//        } else {
//            enmiendaBean.setActivaCheckOCG(Boolean.TRUE);
//            enmiendaBean.setRequiereGerenciaCredito(Boolean.TRUE);
//        }
        
        LOGGER.warning("************Termina siRequiereOGC************");
    }
    
    public void siRequiereOT(ActionEvent actionEvent) { //
        EnmiendaBean enmiendaBean = (EnmiendaBean) JSFUtils.resolveExpression("#{pageFlowScope.enmiendaBean}");
        LOGGER.warning("Inicia metodo de obtener requiere OT");
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("precargaOT");
        Long operacion = Long.parseLong(enmiendaBean.getIdOperacion());
        Number codigoDOperacion = (Number) operacion;
        LOGGER.warning("codigo operacion: " + codigoDOperacion);
        operationBinding.getParamsMap().put("idOperacion", codigoDOperacion);
        resultado = (Boolean) operationBinding.execute();
        LOGGER.warning("Resultado= " + resultado);
        LOGGER.warning("Resultado2= " + (Boolean) operationBinding.getResult());
        if (!operationBinding.getErrors().isEmpty() || !resultado) {
            enmiendaBean.setActivaCheckOT(Boolean.FALSE);
            enmiendaBean.setRequiereOT(Boolean.FALSE);
        } else {
            enmiendaBean.setActivaCheckOT(Boolean.TRUE);
            enmiendaBean.setRequiereOT(Boolean.TRUE);
        }
        LOGGER.warning("checkOT= " + enmiendaBean.getActivaCheckOT());
        LOGGER.warning("requiereOT= " + enmiendaBean.getRequiereOT());
    }
    
    public void siRequiereASJUR(ActionEvent actionEvent) { //
        EnmiendaBean enmiendaBean = (EnmiendaBean) JSFUtils.resolveExpression("#{pageFlowScope.enmiendaBean}");
        LOGGER.warning("Inicia metodo de obtener requiere ASJUR");
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("precargaASJUR");
        Long operacion = Long.parseLong(enmiendaBean.getIdOperacion());
        Number codigoDOperacion = (Number) operacion;
        LOGGER.warning("codigo operacion: " + codigoDOperacion);
        operationBinding.getParamsMap().put("idOperacion", codigoDOperacion);
        resultado = (Boolean) operationBinding.execute();
        LOGGER.warning("Resultado= " + resultado);
        LOGGER.warning("Resultado2= " + (Boolean) operationBinding.getResult());
        if (!operationBinding.getErrors().isEmpty() || !resultado) {
            enmiendaBean.setActivaCheckASJUR(Boolean.FALSE);
            enmiendaBean.setRequiereASJUR(Boolean.FALSE);
        } else {
            enmiendaBean.setActivaCheckASJUR(Boolean.TRUE);
            enmiendaBean.setRequiereASJUR(Boolean.TRUE);
        }
        LOGGER.warning("checkASJUR= " + enmiendaBean.getActivaCheckASJUR());
        LOGGER.warning("requiereASJUR= " + enmiendaBean.getRequiereASJUR());
    }
    
    public void setBtnRefreshOgc(RichButton btnRefreshOgc) {
        this.btnRefreshOgc = btnRefreshOgc;
    }

    public RichButton getBtnRefreshOgc() {
        return btnRefreshOgc;
    }

    public String getClientIdBtnRefreshOgc() {
        this.clientIdBtnRefreshOgc =
            (getBtnRefreshOgc() == null) ? null : getBtnRefreshOgc().getClientId(); // Client Id para bot�n de refresh
        return clientIdBtnRefreshOgc;
    }
}
