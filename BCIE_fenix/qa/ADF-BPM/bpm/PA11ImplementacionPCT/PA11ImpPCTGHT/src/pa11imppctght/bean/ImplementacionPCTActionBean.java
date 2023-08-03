package pa11imppctght.bean;

import envioalcobro.EnvioAlCobroBean;

import envioalcobro.TreeEnvioAlCobro;

import envioalcobro.TreeLiquidarDesembolso;

import java.math.BigDecimal;

import java.math.MathContext;
import java.math.RoundingMode;

import java.sql.Timestamp;

import java.text.DecimalFormat;

import java.text.DecimalFormatSymbols;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.ResourceBundle;

import java.util.StringTokenizer;

import javax.el.ELContext;

import javax.el.ExpressionFactory;

import javax.el.MethodExpression;

import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Key;
import oracle.jbo.Row;

import oracle.jbo.RowSet;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteria;

import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.apache.myfaces.trinidad.model.TreeModel;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.vo.AsociadasVOImpl;
import org.bcie.fenix.common.model.vo.FT05VOImpl;
import org.bcie.fenix.common.model.vo.JustificacionEnvioOperacionImpl;
import org.bcie.fenix.common.model.vo.LineaCreditoDesembolsoImpEnvioGastoVOImpl;
import org.bcie.fenix.common.model.vo.LineaCreditoDesembolsoVOImpl;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

import vercrearformularioimppct.VerCrearFormularioImpPctBean;

public class ImplementacionPCTActionBean extends FenixActionBean {

    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(ImplementacionPCTActionBean.class);
    private static final String BUNDLE = "pa11imppctght.PA11ImpPCTGHTBundle";
    private static final String INVOKE_ACTION_BEAN_NAME = "invokeActionBean";
    private static final String VAR_IS_CONTRATO_SELECCIONADO = "IsContratoSeleccionadoVar1";
    private static final String VAR_ID_CONTRATO_SELECCIONADO = "IdContratoSeleccionadoVar1";
    private static final String VAR_JUSTIFICACION = "JustificacionVar1";
    private static final int ID_ESTADO_DESEMBOLSADO = 18;
    private static final long ID_NUEVO_REGISTRO = 0L;
    private static final Integer ID_TCA_TERMINO_ORDEN_INICIO = 39;
    private static final Integer TCA_TIPO_TASA_VARIABLE = 3;
    private static final String FORMATO_MONTO_LINEA = "###,###,###,###.00";
    private static final char CARACTER_COMA = ',';
    private static final char CARACTER_PUNTO = '.';
    private RichPopup popupFinalizarTarea;
    private RichPopup popupMasInformacion;
    private RichInputText justificacionUIC;
    private String justificacion;
    private BigDecimal totalEnvioGasto;
    private RichTreeTable treeTable;

    private RichPopup popupRealizarAjustes;

    private static Integer banderaElementosVacios = null;
    private static Integer banderaMontoPresupuestado = null;

    private RichRegion validaInformacionImplementacion;

    private static Long idImplementacion = null;
    private static Boolean tipoConcurso = null;
    private static Boolean ordenInicio = null;
    private RichTable table;
    private RichTable tableParticiante;
    private RichTable tableAdjudicatario;
    private static Long idLoteObtenido = Long.valueOf(0);
    private static Long idParticipanteObtenido = Long.valueOf(0);
    private static Long idAdjudicatarioObtenido = Long.valueOf(0);
    private RichPanelGroupLayout pglFinalizacionEstudiosUIC;

    private RichPopup popupEliminarLote;
    private RichPopup popupEliminarParticipante;
    private RichPopup popupEliminarAdjudicatario;
    private RichPopup popupCrearDesembolsoUIC;
    private RichRegion regionContratoUIC;
    private RichPanelGroupLayout pglRegionContratoUIC;
    private RichPanelGroupLayout panelLineasConContratosUIC;
    private RichPanelGroupLayout contenedorBtnCrearContrato;
    private RichInputText uiValorTasa;
    private RichPanelGroupLayout botonActualizarUI;
    private RichButton botonActualizarBindingUI;
    private RichPanelGroupLayout refrescarTreeTableEnvioCobroBinding;
    
    private static final int ADJUDICADO = 1;
    private static final int ANULADO = 2;
    private static final int DESIERTO = 3;
    private static final int FRACASADO = 4;
    private static final int NUEVO = 5;
    
    private static final String LIQUIDAR_DESEMBOLSOS = "136";
    private RichButton botonActualizarEnvioAlGastoUI;
    private RichInputComboboxListOfValues codigoTasaUI;
    private RichInputText spreadUI;
    private RichInputText totalEnvioCobroUIC;
    private RichOutputText onInitForm;
    private RichInputDate fechaInteresesUIC;
    //Inicialicion de lista de advertencia
    //jenamorado 30/08/2021
    private List<String> listaAdvertencias = new ArrayList<String>(); 
    
     //Set de lista de advertencia
     //jenamorado 30/08/2021
     public void setListaAdvertencias(List<String> listaAdvertencias) {
         this.listaAdvertencias = listaAdvertencias;
     }
     //Get de lista de advertencia
     //jenamorado 30/08/2021
     public List<String> getListaAdvertencias() {
         return listaAdvertencias;
     }
    
    public ImplementacionPCTActionBean() {
        super();
    }


    public String invocarFinalizarTarea() {
        LOGGER.entering(this.getClass().getName(), "invocarFinalizarTarea");

        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        Boolean existeDocumento = Boolean.FALSE;
        Boolean existeComentario = Boolean.FALSE;
        Boolean condicion1 = Boolean.FALSE;
        Boolean condicion2 = Boolean.FALSE;
        Boolean condicion3 = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();
        Long codigoOperacion = getCodigoOperacion();
        oracle.jbo.domain.Number numeroSerieDocumento = getNumeroSerieDocumento();

        switch (codigoTarea) {
        case FenixConstants.PA11_SOLICITUD_ENVIO_GASTO:
            LOGGER.fine("PA11_SOLICITUD_ENVIO_GASTO");
            LOGGER.warning("Solicitud de envio al gasto.");
            if (justificacionUIC.getValue() == null || justificacionUIC.getValue().toString().trim().length() == 0) {
                bundleKeyErrorList.add("MSG_ERROR_DATOS_OBLIGATORIOS_PA11SOLICITUDENVIOGASTO");
            } else {
                LOGGER.warning("Justificacion: " + justificacionUIC.getValue().toString());
                ADFUtils.setBoundAttributeValue(VAR_JUSTIFICACION, justificacionUIC.getValue().toString());
                condicion1 = Boolean.TRUE;
            }
            condicion2 =
                validarDocumentoPorNumeroSerieDocumento(codigoOperacion,
                                                        FenixConstants.TD_SOLICITUD_DE_ENVIO_AL_GASTO,
                                                        numeroSerieDocumento);
            if(!condicion2){
                    bundleKeyErrorList.add("MSG_ERROR_DOCUMENTO_SOPORTE_ENVIO_AL_GASTO");
                }
            isValidFinalizarTarea=condicion1 && condicion2;
            break;
        case FenixConstants.PA11_VALIDAR_SOLICITUD:
            LOGGER.fine("PA11_VALIDAR_SOLICITUD");
            LOGGER.warning("Validar solicitud.");
            isValidFinalizarTarea = Boolean.TRUE;
            break;
        case FenixConstants.PA11_LIQUIDAR_DESEMBOLSO:
            LOGGER.fine("PA11_LIQUIDAR_DESEMBOLSO");
            LOGGER.warning("Liquidar desembolsos.");
            //boolean isTransferenciasAplicadas = validarTransferenciasAplicadas();
            boolean isContratosSaldados = validarContratosSaldados();
            /*if (!isTransferenciasAplicadas) {
                bundleKeyErrorList.add("MSG_ERROR_TRANSF_NO_APLICADA_PA11LIQUIDARDESENBOLSOS");
            }*/
            if (!isContratosSaldados) {
                bundleKeyErrorList.add("MSG_ERROR_CONTRATO_NO_SALDADO_PA11LIQUIDARDESENBOLSOS");
            }
            /*if (isTransferenciasAplicadas && isContratosSaldados) {
                isValidFinalizarTarea = Boolean.TRUE;
            }*/
            if (isContratosSaldados) {
                isValidFinalizarTarea = Boolean.TRUE;
            }
            break;
        case FenixConstants.PA11_VALIDAR_TASA_FECHA:
            LOGGER.fine("*Inf, SE INVOCO FINALIZAR TAREA PARA PA11_VALIDAR_TASA_FECHA");
            LOGGER.warning("*Inf, SE INVOCO FINALIZAR TAREA PARA PA11_VALIDAR_TASA_FECHA");

            isValidFinalizarTarea = Boolean.TRUE;
            break;
        case FenixConstants.PA11_REALIZAR_AJUSTES_TASA_FECHA:
            LOGGER.fine("PA11_REALIZAR_AJUSTES_TASA_FECHA");
            LOGGER.warning("Realizar ajustes de tasa y fecha.");
            EnvioAlCobroBean envioAlCobroBean =
                (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobroBean}");
            Date fechaFinalizacionEstudios = null;
            Date fechaFinalizacionAttr = null;
            oracle.jbo.domain.Number valorTasa = null;
            oracle.jbo.domain.Number valorTasaAttr = null;
            Integer idTipoTasa = null;
            Boolean campoRequerido = Boolean.TRUE;

            if (null != envioAlCobroBean) {
                fechaFinalizacionEstudios = envioAlCobroBean.getVFechaFinalizacionEstudios();
                valorTasa = envioAlCobroBean.getVValorTasa();

                try {
                    valorTasaAttr = new oracle.jbo.domain.Number(ADFUtils.getBoundAttributeValue("Tasa").toString());
                } catch (Exception e) {
                    LOGGER.warning("No se pudo obtener el valor del BoundAttribute de valorTasa.");
                }

                try {
                    fechaFinalizacionAttr = (Date) ADFUtils.getBoundAttributeValue("FechaFinalizacionEstudios");
                } catch (Exception e) {
                    LOGGER.warning("No se pudo obtener el valor del BoundAttribute de fechaFinalizacionEstudios.");
                }
                
                try {
                    idTipoTasa = (Integer) ADFUtils.getBoundAttributeValue("IdTipoTasa");
                } catch (Exception e) {
                    LOGGER.warning("No se pudo obtener el valor del BoundAttribute de fechaFinalizacionEstudios.");
                }
                
                if(idTipoTasa.compareTo(TCA_TIPO_TASA_VARIABLE) == 0){
                    LOGGER.warning("El tipo de tasa es variable entra a validar campos.");
                    AttributeBinding codigoTasa = null;
                    codigoTasa = ADFUtils.findControlBinding("CodTasRef");
                    if(null != codigoTasa.getInputValue()){
                        LOGGER.warning("El campo tiene valor.");
                        getCodigoTasaUI().setContentStyle(null);
                    }else{
                        LOGGER.warning("El campo codigo tasa es nulo."); 
                        getCodigoTasaUI().setContentStyle("border: 2px #C70000 solid;");
                        campoRequerido = Boolean.FALSE;
                    }
                    
                    AttributeBinding spread = null;
                    spread = ADFUtils.findControlBinding("Spread");
                    LOGGER.warning("Valor del spread." + spread);
                    if(null != spread.getInputValue()){
                        LOGGER.warning("El campo tiene valor.");
                        getSpreadUI().setContentStyle(null);
                    }else{
                        LOGGER.warning("El campo spread es nulo.");
                        getSpreadUI().setContentStyle("border: 2px #C70000 solid;");
                        campoRequerido = Boolean.FALSE;
                    }
                }else{
                    LOGGER.warning("El tipo de tasa es fijo, No se valida codigo tasa y spread.");
                }
                
                if(!campoRequerido){
                    LOGGER.warning("Codigo tasa o spread son nulos.");
                    isValidFinalizarTarea = Boolean.FALSE;
                }else{
                    if (null != fechaFinalizacionEstudios && null != valorTasa) {
                        if (null != valorTasaAttr && null != fechaFinalizacionAttr) {
                            LOGGER.warning("FechaFinalizacionEstudios: " + fechaFinalizacionEstudios +
                                           ", FechaFinalizacionAttr: " + fechaFinalizacionAttr);
                            LOGGER.warning("ValorTasa: " + valorTasa + ", ValorTasaAttr: " + valorTasaAttr);
                            if (valorTasa.compareTo(valorTasaAttr) == 0 &&
                                fechaFinalizacionEstudios.compareTo(fechaFinalizacionAttr) == 0) {
                                if (validateComentario(getCodigoOperacion(), String.valueOf(getCodigoTarea()),
                                                       getInstanciaTarea())) {
                                    isValidFinalizarTarea = Boolean.TRUE;
                                } else {
                                    LOGGER.warning("Validacion de comentario false");
                                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_VALIDACION_CAMBIOS_DATOS"));
                                }
                            } else {
                                isValidFinalizarTarea = Boolean.TRUE;
                            }
                        } else {
                            LOGGER.warning("Los nuevos valores de Fecha y Tasa son NULL.");

                            if (null == valorTasaAttr) {
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_VALOR_TASA"));
                            }

                            if (null == fechaFinalizacionAttr) {
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_FECHA_FINALIZACION_ESTUDIOS"));
                            }
                        }
                    } else {
                        LOGGER.warning("Los valores de fechaFinalizacionEstudios y valorTasa para comparar nuevos valores son NULL." + fechaFinalizacionEstudios + "." +valorTasa);
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_VALIDACION_CAMBIO_DATOS"));
                    }
                }
            } else {
                LOGGER.warning("Instancia de envioAlCobroBean es NULL.");
            }
            LOGGER.warning("Finalizar tarea : " + isValidFinalizarTarea);
            break;
        case FenixConstants.PA11_ENVIAR_AL_COBRO:
            LOGGER.fine("PA11_ENVIAR_AL_COBRO");
            LOGGER.warning("Enviar al cobro.");
            Boolean validaDatos = Boolean.FALSE;
            if (null != codigoOperacion) {
                existeDocumento =
                    validarDocumentoPorNumeroSerieDocumento(codigoOperacion,
                                                            FenixConstants.DOCUMENTO_SOPORTE_ENVIO_COBRO,
                                                            numeroSerieDocumento);
            }
            // Datos de prueba
            //existeDocumento = Boolean.TRUE;
            
            validaDatos = validaDatosRequeridosCondicionesFinancieras();
            
            // Se agrega validacion por FNXII-6097
            if (cuentaConContratoCreadoPorImpl()) {
                if (existeDocumento) {
                    if (validarJustificacionEnvioCobro()) {
                        if (validarDatosContrato()) {
                            if(validaDatos){
                                isValidFinalizarTarea = Boolean.TRUE; 
                            } 
                        } else {
                            LOGGER.warning("La validacion de los datos del contrato es FALSE.");
                            bundleKeyErrorList.add("MSG_ERROR_INFORMACION_REQUERIDA");
                        }
                    } else {
                        LOGGER.warning("La validacion de la justificacion es FALSE.");
                        bundleKeyErrorList.add("MSG_ERROR_INFORMACION_REQUERIDA");
                    }
                } else {
                    LOGGER.warning("No existe el documento Soporte de Envio al cobro.");
                    bundleKeyErrorList.add("MSG_ERROR_DOCUMENTO_SOPORTE_ENVIO_COBRO");
                }
            } else {
                LOGGER.warning("Es necesario que cada linea cuente con contrato creado por impl.");
                bundleKeyErrorList.add("MSG_ERROR_FALTA_CONTRATO_CREADO_IMPL");
            }
            
            break;
        case FenixConstants.PA11_VALIDAR_ENVIO_COBRO:
            LOGGER.fine("PA11_VALIDAR_ENVIO_COBRO");
            LOGGER.warning("Validar envio al cobro");
            isValidFinalizarTarea = Boolean.TRUE;

            LOGGER.warning("Validar envio al cobro.");
            break;
        case FenixConstants.PA11_LIQUIDAR_DESEMOBOLSOS_ANTERIORES:
            LOGGER.fine("PA11_LIQUIDAR_DESEMOBOLSOS_ANTERIORES");
            LOGGER.warning("Liquidar desembolsos anteriores.");
            if (existenContratosPorLiquidar()) {
                bundleKeyErrorList.add("DEBE_LIQUIDAR_TODOS_LOS_CONTRATOS");
            } else {
                finalizarTareaPopup();
            }

            break;
            //Invocar finalizar tareas de Implementacion PCT core
        case FenixConstants.PA11_SOLICITAR_CONTRATACION_CONSULTORIA:
            LOGGER.fine("PA11_SOLICITAR_CONTRATACION_CONSULTORIA");
            LOGGER.warning("Solicitar contratacion de consutoria.");
            obtenerBanderasValidacion();
            if (null != banderaElementosVacios) {
                if (banderaElementosVacios == 1) {
                    if (null != codigoOperacion) {
                        if (null != banderaMontoPresupuestado) {
                            switch (banderaMontoPresupuestado) {
                            case 1:
                                existeDocumento =
                                    validarDocumentoPorNumeroSerieDocumento(codigoOperacion,
                                                                            FenixConstants.ID_TIPO_DOCUMENTO_ACTA_EVALUACION_MIEMBROS_COMITE,
                                                                            numeroSerieDocumento);
                                if (existeDocumento) {
                                    isValidFinalizarTarea = Boolean.TRUE;
                                    tipoConcurso = Boolean.FALSE;
                                    ordenInicio = Boolean.FALSE;
                                } else {
                                    LOGGER.warning("No existe el documento Acta evaluacion miembros comite.");
                                    bundleKeyErrorList.add("MSG_03_SCC_VALIDACION");
                                }
                                break;
                            case 2:
                                existeDocumento =
                                    validarDocumentoPorNumeroSerieDocumento(codigoOperacion,
                                                                            FenixConstants.ID_TIPO_DOCUMENTO_ACTA_EVALUACION_MIEMBROS_COMITE,
                                                                            numeroSerieDocumento);
                                if (existeDocumento) {
                                    //isValidFinalizarTarea = Boolean.TRUE;
                                    ;
                                } else {
                                    LOGGER.warning("No existe el documento Acta evaluacion miembros comite.");
                                    //bundleKeyErrorList.add("MSG_04_SCC_VALIDACION");
                                }

                                existeComentario =
                                    validateComentario(getCodigoOperacion(), String.valueOf(getCodigoTarea()),
                                                       getInstanciaTarea());
                                if (!existeComentario) {
                                    //bundleKeyErrorList.add("MSG_04_SCC_VALIDACION");
                                    LOGGER.warning("No se ha agregado un comentario.");
                                    ;
                                }
                                if (existeDocumento == true || existeComentario == true) {
                                    isValidFinalizarTarea = Boolean.TRUE;
                                    tipoConcurso = Boolean.FALSE;
                                    ordenInicio = Boolean.FALSE;
                                } else {
                                    bundleKeyErrorList.add("MSG_04_SCC_VALIDACION");
                                }
                                break;
                            case 3:
                                existeDocumento =
                                validarDocumentoPorNumeroSerieDocumento(codigoOperacion,
                                                                        FenixConstants.ID_TIPO_DOCUMENTO_TERMINOS_REFERENCIA_DEFINITIVOS,
                                                                        numeroSerieDocumento);
                                if (existeDocumento) {
                                    isValidFinalizarTarea = Boolean.TRUE;
                                    tipoConcurso = Boolean.TRUE;
                                    ordenInicio = Boolean.TRUE;
                                } else {
                                    LOGGER.warning("No existe el documento Terminos referencia definitivos.");
                                    bundleKeyErrorList.add("MSG_05_SCC_VALIDACION");
                                }
                                break;
                            default:
                                isValidFinalizarTarea = Boolean.FALSE;
                                break;
                            }
                        }
                    }
                } else {
                    LOGGER.log(ADFLogger.WARNING, "Faltan campos por capturar.");
                    bundleKeyErrorList.add("MSG_02_SCC_VALIDACION");
                }
            } else {
                LOGGER.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
            }
            break;
        case FenixConstants.PA11_GESTIONAR_CONTRATACION_CONSULTORIA:
            LOGGER.fine("PA11_GESTIONAR_CONTRATACION_CONSULTORIA");
            LOGGER.warning("Gestionar proceso de contratacion.");
            obtenerBanderasValidacion();
            if (null != banderaElementosVacios) {
                if (banderaElementosVacios == 1) {
                    if (null != codigoOperacion) {
                        if (null != banderaMontoPresupuestado) {
                            if (banderaMontoPresupuestado == 1 || banderaMontoPresupuestado == 2) {
                                implementacionBean.setFinalizarTarea(getStringFromBundle("MSG_02_GPC_FINALIZAR_TAREA"));
                                isValidFinalizarTarea = Boolean.TRUE;
                            } else if (banderaMontoPresupuestado == 3) {
                                implementacionBean.setFinalizarTarea(getStringFromBundle("MSG_01_GPC_FINALIZAR_TAREA"));
                                isValidFinalizarTarea = Boolean.TRUE;
                            }
                        }
                    }
                } else {
                    LOGGER.log(ADFLogger.WARNING, "Faltan campos por capturar.");
                    bundleKeyErrorList.add("MSG_03_GPC_VALIDACION");
                }
            }
            break;
        case FenixConstants.PA11_ELABORAR_DOCUMENTO_BASE:
            if (null != codigoOperacion) {
                existeDocumento =
                    validarDocumentoPorNumeroSerieDocumento(codigoOperacion,
                                                            FenixConstants.ID_TIPO_DOCUMENTO_BORRADOR_DOCUMENTO_BASE,
                                                            numeroSerieDocumento);
            }

            if (existeDocumento) {
                isValidFinalizarTarea = Boolean.TRUE;
            } else {
                LOGGER.warning("No existe el documento Borrador de documento base.");
                bundleKeyErrorList.add("MSG_02_EDB_VALIDACION");
            }
            break;
        case FenixConstants.PA11_COMPLETAR_DOCUMENTO_BASE:
            if (null != codigoOperacion) {
                existeDocumento =
                    validarDocumentoPorOperacionIdTareaNumeroSerieDocumento(codigoOperacion,
                                                                            FenixConstants.PA11_COMPLETAR_DOCUMENTO_BASE,
                                                                            FenixConstants.ID_TIPO_DOCUMENTO_DOCUMENTO_BASE,
                                                                            numeroSerieDocumento);
            }

            if (existeDocumento) {
                isValidFinalizarTarea = Boolean.TRUE;
            } else {
                LOGGER.warning("No existe el documento Borrador de documento base.");
                bundleKeyErrorList.add("MSG_02_CDB_VALIDACION");
            }
            break;
        case FenixConstants.PA11_VALIDAR_DOCUMENTO_BASE:
            if (null != codigoOperacion) {
                existeDocumento =
                    validarDocumentoPorOperacionIdTareaNumeroSerieDocumento(codigoOperacion,
                                                                            FenixConstants.PA11_VALIDAR_DOCUMENTO_BASE,
                                                                            FenixConstants.ID_TIPO_DOCUMENTO_DOCUMENTO_BASE,
                                                                            numeroSerieDocumento);
            }

            if (existeDocumento) {
                isValidFinalizarTarea = Boolean.TRUE;
            } else {
                LOGGER.warning("No existe el documento Documento base.");
                bundleKeyErrorList.add("MSG_02_VDB_VALIDACION");
            }
            break;
        case FenixConstants.PA11_INICIAR_ADQUISICION:
            LOGGER.fine("PA11_INICIAR_ADQUISICION");
            LOGGER.warning("Iniciar adquisicion.");
            obtenerBanderaValidacionAdquisicion();
            if (null != banderaElementosVacios) {
                if (banderaElementosVacios == 1) {
                    if (obtenerBanderaValidacionRequiereLotes() == true) {
                        if (obtenerBanderaContadorImplementacionPCT() == true) {
                            if (obtenerBanderaSumaLotes() == true) {
                                if (null != codigoOperacion) {
                                    isValidFinalizarTarea = Boolean.TRUE;
                                }
                            } else {
                                LOGGER.warning("La suma de los lotes es mayor al monto presupuestado aprobado en la operacion.");
                                bundleKeyErrorList.add("MSG_04_IA_VALIDACION");
                            }
                        } else {
                            LOGGER.warning("No existe por lo menos un idLote para el idImplementacion.");
                            bundleKeyErrorList.add("MSG_02_IA_VALIDACION_AGREGAR_LOTE");
                        }
                    } else {
                        LOGGER.log(ADFLogger.WARNING, "CheckBox RequiereLotes no se encuentra activo.");
                        isValidFinalizarTarea = Boolean.TRUE;
                        //Implementar metodo para crear e insertar un lote para el id implementacion
                        //en caso de que no este seleccionado el checkbox con el total del monto presupuestado
                    }
                } else {
                    LOGGER.log(ADFLogger.WARNING, "Faltan campos por capturar.");
                    bundleKeyErrorList.add("MSG_05_IA_VALIDACION_CAMPOS_VACIOS");
                }
            }
            break;
        case FenixConstants.PA11_REGISTRAR_RESULTADO_ADQUISICION:
            LOGGER.fine("PA11_REGISTRAR_RESULTADO_ADQUISICION");
            LOGGER.warning("Registrar resultado adquisicion.");
            obtenerBanderaValidacionAdquisicion();
            if (null != banderaElementosVacios) {
                if (banderaElementosVacios == 1) {
                    if (obtenerBanderaContadorImplementacionPCT() == true) {
                        Integer registrarResultadoAdquisicion = implementacionBean.getRegistrarResultadoAdquisicion();
                        if (registrarResultadoAdquisicion == ADJUDICADO) {
                            if (obtenerBanderaSumaAdjudicatario() == true) {
                                if (null != codigoOperacion) {
                                    isValidFinalizarTarea = Boolean.TRUE;
                                }
                            } else {
                                LOGGER.warning("La suma de los montos de las adjudicaciones no puede ser mayor al monto presupuestado.");
                                bundleKeyErrorList.add("MSG_05_RRA_VALIDACION_MONTOS");
                            }
                        } else if (registrarResultadoAdquisicion == ANULADO || registrarResultadoAdquisicion == DESIERTO ||
                                   registrarResultadoAdquisicion == FRACASADO || registrarResultadoAdquisicion == NUEVO) {
                            if (null != codigoOperacion) {
                                isValidFinalizarTarea = Boolean.TRUE;
                            }
                        }
                    } else {
                        LOGGER.warning("No existe por lo menos un Participante y un Adjudicatario para el idLoteImplementacion.");
                        bundleKeyErrorList.add("MSG_04_RRA_VALIDACION_CAMPOS");
                    }
                } else {
                    LOGGER.log(ADFLogger.WARNING, "Faltan campos por capturar.");
                    bundleKeyErrorList.add("MSG_02_RRA_VALIDACION_CAMPOS_VACIOS");
                }
            }
            break;
        case FenixConstants.PA11_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO:
            LOGGER.fine("PA11_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO");
            LOGGER.warning("Registrar orden de inicio y datos de linea de credito.");
            Boolean esValido = Boolean.FALSE;
            
            /* RN_01 Se tiene que agregar por lo menos un participante con un adjudicatario para poder finalizar la tarea.
            * RN_02 La suma de los montos de las adjudicaciones no puede ser mayor al monto presupuestado.
            * EX01 Valida que las tablas “Participante” y “Adjudicatario” contengan por lo menos un registro cada una.
            * EX02 Valida que la información requerida este correctamente agregada.
            * MSG_02 Debe ingresar la información requerida para poder finalizar la tarea. MSG_02_ROIDLC_FINALIZAR_TAREA
            * MSG_04 Para finalizar la tarea es necesario tener un Participante con un Adjudicatario asociado. MSG_04_ROIDLC_FINALIZAR_TAREA
            */
            LOGGER.warning("Valor isGestionaCliente: " + implementacionBean.getIsGestionaCliente());
            if(!implementacionBean.getIsGestionaCliente())
            {
                condicion1 = Boolean.TRUE;
                condicion2 = Boolean.TRUE;
                condicion3 = this.validaInformacionRequeridaROIDLC();
            }
            else{
                condicion1 = this.exiteParticipanteConAdjudicatario();
                condicion2 = this.validaMontosAdjudicacionesNoMayorPresupuestado();
                condicion3 = this.validaInformacionRequeridaROIDLC();
            }

            if (!condicion1) {
                bundleKeyErrorList.add("MSG_04_ROIDLC_FINALIZAR_TAREA");
            }

            if (!condicion2) {
                bundleKeyErrorList.add("MSG_RN_02_ROIDLC_FINALIZAR_TAREA");
            }

            if (!condicion3) {
                bundleKeyErrorList.add("MSG_02_ROIDLC_FINALIZAR_TAREA");
            }
            
            /*REGLAS DE NEGOCIO REGISTRAR DATOS DE LA LINEA DE CREDITO
             * EX03: El sistema valida que no agrego lineas de credito*/
            esValido = validarLineaCreditoAgregada();
            
            //EX06: El sistema valida que el monto total de las lineas de credito ingresadas es mayor al monto escriturado.
            if(esValido){
                
              //  esValido = validarMontoEscrituracionContraMontoTotal();
              /*
               *se crea validacion de monto presupuestado con linea de creditos 
               * jenamorado 30/08/2021
               * */  
                esValido = validarMontoPresupuestadoContraMontoTotal();
            }
            
            isValidFinalizarTarea = condicion1 && condicion2 && condicion3 && esValido;
            break;
        case FenixConstants.PA11_VALIDAR_DATOS_LINEA_CREDITO:
            LOGGER.fine("PA11_VALIDAR_DATOS_LINEA_CREDITO");
            LOGGER.warning("Validar datos de linea de credito.");

            // No aplican reglas para Finalizar Tarea.
            isValidFinalizarTarea = Boolean.TRUE;

            break;
        default:
            LOGGER.log(ADFLogger.ERROR, "invocarFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }

        if (!isValidFinalizarTarea) {
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        } else {
            finalizarTareaPopup();
        }
        LOGGER.exiting(this.getClass().getName(), "invocarFinalizarTarea");
        return null;
    }

    public void finalizarTareaPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupFinalizarTarea.show(hints);
    }

    public String aceptarFinalizarTarea() {
        LOGGER.entering(this.getClass().getName(), "aceptarFinalizarTarea");
        LOGGER.warning("Ingresa a aceptarFinalizarTarea");
        popupFinalizarTarea.hide();
        cargarDocumentosOnBase();

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean isAceptarFinalizarTareaValid = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {
        case FenixConstants.PA11_SOLICITUD_ENVIO_GASTO:
            LOGGER.fine("PA11_SOLICITUD_ENVIO_GASTO");
            LOGGER.warning("Solicitud envio al gasto");
            guardarJustificacionEnvioOperacion();
            isAceptarFinalizarTareaValid = Boolean.TRUE;
            break;
        case FenixConstants.PA11_VALIDAR_SOLICITUD:
            LOGGER.fine("PA11_VALIDAR_SOLICITUD");
            LOGGER.warning("Validar solicitud");
            isAceptarFinalizarTareaValid = Boolean.TRUE;
            break;
        case FenixConstants.PA11_LIQUIDAR_DESEMBOLSO:
            LOGGER.fine("PA11_LIQUIDAR_DESEMBOLSO");
            LOGGER.warning("Liquidar desembolso");
            isAceptarFinalizarTareaValid = Boolean.TRUE;
            break;
        case FenixConstants.PA11_ENVIAR_AL_COBRO:
            LOGGER.fine("PA11_ENVIAR_AL_COBRO");
            LOGGER.warning("Enviar al cobro");
            Boolean datosGenerales = Boolean.FALSE;
            Boolean condicionesFinancieras = Boolean.FALSE;
            Boolean cargos = Boolean.FALSE;
            if (guardarDatosOperacion()) {
                //FNXII-6452 y FNXII-6496
                //Se genera fecha de dispobibilidad de fondos
                //Y se generan las fecha de vencimientos de condiciones financieras
                
                if (generarFechaDisponibilidadFondosPCT()) {
                    if (guardarDatosContratoEnvioCobro()) {
                    //                    datosGenerales = guardarDatosGeneralesTab();
                    //                    condicionesFinancieras = guardarCondicionesFinancierasTab();
                    //                    cargos = guardarCargos();
                    //isAceptarFinalizarTareaValid =Boolean.TRUE;
                        if(actualizarMontoAmpliacionLineaCredito()){
                            isAceptarFinalizarTareaValid =Boolean.TRUE;
                        }else{
                            JSFUtils.addFacesErrorMessage("Error al actualizar el monto de ampliacion de la linea de credito");
                        }
                    }else{
                        LOGGER.warning("No se guardaron los datos del contrato correctamente.");
                        bundleKeyErrorList.add("MSG_ERROR_FINALIZAR_TAREA");
                    }
                } else {
                    LOGGER.warning("No se pueden generar las fechas al finalizar.");
                }
                
            } else {
                LOGGER.warning("No se guardaron los datos de la operacion correctamente.");
                bundleKeyErrorList.add("MSG_ERROR_FINALIZAR_TAREA");
                //JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_FINALIZAR_TAREA"));
            }
            if(isAceptarFinalizarTareaValid){
                isAceptarFinalizarTareaValid=actualizarEstadosDesembolso(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_CREADO_POR_IMPLEMENTACION, FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_VALIDADO_POR_IMPLEMENTACION);
                if(!isAceptarFinalizarTareaValid){
                        bundleKeyErrorList.add("MSG_ERROR_FINALIZAR_TAREA");
                    }
                }
            break;
        case FenixConstants.PA11_VALIDAR_ENVIO_COBRO:
            LOGGER.fine("PA11_VALIDAR_ENVIO_COBRO");
            LOGGER.warning("validar envio al cobro");
            Boolean actualiza = Boolean.FALSE;
            actualiza = actualizarDatosFlexcube();
            isAceptarFinalizarTareaValid = (actualiza) ? Boolean.TRUE : Boolean.FALSE;
            //Si no se realiza la actualizacion de los datos se manda error.
            if(!isAceptarFinalizarTareaValid){
                LOGGER.warning("Error en aceptar finalizar la tarea.");
                JSFUtils.addFacesErrorMessage("Error al actualizar el estado de los contratos.");
                    bundleKeyErrorList.add("MSG_ERROR_FINALIZAR_TAREA");
            }else{
                LOGGER.warning("Se realiza la actualizacion de los datos correctamente");
            }
            //Se comenta metodo ya que SOA realiza la acctualizacion del estado del contrato por validacion.
//            if(isAceptarFinalizarTareaValid){
//                isAceptarFinalizarTareaValid=actualizarEstadosDesembolsoACreado();
//                }
        break;
        case FenixConstants.PA11_SOLICITAR_CONTRATACION_CONSULTORIA:
            LOGGER.fine("PA11_SOLICITAR_CONTRATACION_CONSULTORIA");
            LOGGER.warning("Solicitar contratacion consultoria");
            Boolean banderaValidarIdImplementacionExiste = Boolean.FALSE;
            try {
               // VerCrearFormularioImpPctBean verCrearFormularioImpPctBean = new VerCrearFormularioImpPctBean();
                VerCrearFormularioImpPctBean verCrearFormularioImpPctBean = (VerCrearFormularioImpPctBean)  
                    JSFUtils.resolveExpression("#{pageFlowScope.VerCrearFormularioImpPctBean}");
                banderaValidarIdImplementacionExiste = verCrearFormularioImpPctBean.getValidaIdImplementacion();
                LOGGER.warning("banderaValidarIdImplementacionExiste. " + banderaValidarIdImplementacionExiste);
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR,
                           "Error en banderaValidarIdImplementacionExiste." + e.getClass() + "." + e.getMessage());
            }
            if (banderaValidarIdImplementacionExiste == true) {
                isAceptarFinalizarTareaValid = actualizarDatosFormulario();
            } else {
                isAceptarFinalizarTareaValid = insertarDatosFormulario();
            }
            try {
                if (isAceptarFinalizarTareaValid == true && banderaValidarIdImplementacionExiste == true) {
                    idImplementacion = getCodigoImplementacion();
                    LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion actualizar payload." + idImplementacion);
                    AttributeBinding attributeBindingIdImplementacion = null;
                    attributeBindingIdImplementacion = ADFUtils.findControlBinding("IdImplementacion");
                    actualizarPayloadElement("IdImplementacion", idImplementacion);
                    LOGGER.severe("Revisar Valor del payload en true Despues de insertar." +
                                  attributeBindingIdImplementacion.getInputValue());
                } else if (isAceptarFinalizarTareaValid == true && banderaValidarIdImplementacionExiste == false) {
                    LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion actualizar payload." + idImplementacion);
                    LOGGER.log(ADFLogger.WARNING, "Valor tipoConcurso actualizar payload." + tipoConcurso);
                    LOGGER.log(ADFLogger.WARNING, "Valor ordenInicio actualizar payload." + ordenInicio);

                    AttributeBinding attributeBindingIdImplementacion = null;
                    attributeBindingIdImplementacion = ADFUtils.findControlBinding("IdImplementacion");
                    actualizarPayloadElement("IdImplementacion", idImplementacion);
                    LOGGER.severe("Revisar Valor del payload en true Despues de insertar." +
                                  attributeBindingIdImplementacion.getInputValue());
                }
                AttributeBinding attributeBindingTipoConcurso = null;
                attributeBindingTipoConcurso = ADFUtils.findControlBinding("tipoConcurso");
                actualizarPayloadElement("tipoConcurso", tipoConcurso);
                LOGGER.severe("Revisar Valor del payload en true Despues de insertar." +
                              attributeBindingTipoConcurso.getInputValue());

                AttributeBinding attributeBindingOrdenInicio = null;
                attributeBindingOrdenInicio = ADFUtils.findControlBinding("ordenInicio");
                actualizarPayloadElement("ordenInicio", ordenInicio);
                LOGGER.severe("Revisar Valor del payload en true Despues de insertar." +
                              attributeBindingOrdenInicio.getInputValue());
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): Valor del payload no actualizado - " + e);
            }
            break;
        case FenixConstants.PA11_GESTIONAR_CONTRATACION_CONSULTORIA:
            LOGGER.fine("PA11_GESTIONAR_CONTRATACION_CONSULTORIA");
            LOGGER.warning("Gestionar contratacion consultoria");
            Boolean ordenInicio = null;
            banderaValidarIdImplementacionExiste = Boolean.FALSE;
            try {
                //VerCrearFormularioImpPctBean verCrearFormularioImpPctBean = new VerCrearFormularioImpPctBean();
                VerCrearFormularioImpPctBean verCrearFormularioImpPctBean = (VerCrearFormularioImpPctBean)  
                    JSFUtils.resolveExpression("#{pageFlowScope.VerCrearFormularioImpPctBean}");
                banderaValidarIdImplementacionExiste = verCrearFormularioImpPctBean.getValidaIdImplementacion();
                LOGGER.warning("banderaValidarIdImplementacionExiste. " + banderaValidarIdImplementacionExiste);
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR,
                           "Error en banderaValidarIdImplementacionExiste." + e.getClass() + "." + e.getMessage());
            }
            if (banderaValidarIdImplementacionExiste == true) {
                isAceptarFinalizarTareaValid = actualizarObservacion();
            }
            try {
                if (isAceptarFinalizarTareaValid == true && banderaValidarIdImplementacionExiste == true) {
                    idImplementacion = getCodigoImplementacion();
                    LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion actualizar payload." + idImplementacion);
                    AttributeBinding attributeBindingIdImplementacion = null;
                    attributeBindingIdImplementacion = ADFUtils.findControlBinding("IdImplementacion");
                    actualizarPayloadElement("IdImplementacion", idImplementacion);
                    LOGGER.warning("Revisar Valor del payload en true Despues de insertar." +
                                  attributeBindingIdImplementacion.getInputValue());
                    
                    //Se hace busqueda del Termino T704 - Orden de inicio
                    ordenInicio = obtenerValorOrdenInicio();
                    
                    if(null != ordenInicio){  //*->
                                                                                          
                        Long idLote = recuperarIdLote();
                        
                        LOGGER.warning("Actualizando valor IdLote al Payload.");
                        if(idLote != null){
                            actualizarPayloadElement("IdLote", idLote);
                            LOGGER.warning("payload IdLoteDespues de insertar." + ADFUtils.findControlBinding("IdLote"));                        
                        }else{
                            LOGGER.warning("Importante no se pudo recuperar el IdLote");
                            isAceptarFinalizarTareaValid = Boolean.FALSE; 
                        }
                                                
                        LOGGER.warning("Asignando valor ordenInicio al Payload.");
                        actualizarPayloadElement("ordenInicio", ordenInicio);
                    }else{
                        LOGGER.warning("Orden de inicio es NULL.");
                        isAceptarFinalizarTareaValid = Boolean.FALSE;
                    }
                } else {
                    LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion actualizar payload es null.");
                }
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): Valor del payload no actualizado - " + e);
            }
            break;
        case FenixConstants.PA11_ELABORAR_DOCUMENTO_BASE:
            LOGGER.fine("PA11_ELABORAR_DOCUMENTO_BASE");
            LOGGER.warning("Elaborar documento base");
            isAceptarFinalizarTareaValid = Boolean.TRUE;
            try {
                if (isAceptarFinalizarTareaValid == true) {
                    idImplementacion = getCodigoImplementacion();
                    LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion actualizar payload." + idImplementacion);
                    AttributeBinding attributeBindingIdImplementacion = null;
                    attributeBindingIdImplementacion = ADFUtils.findControlBinding("IdImplementacion");
                    actualizarPayloadElement("IdImplementacion", idImplementacion);
                    LOGGER.severe("Revisar Valor del payload en true Despues de insertar." +
                                  attributeBindingIdImplementacion.getInputValue());
                } else {
                    LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion actualizar payload es null.");
                }
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): Valor del payload no actualizado - " + e);
            }
            break;
        case FenixConstants.PA11_COMPLETAR_DOCUMENTO_BASE:
            LOGGER.fine("PA11_COMPLETAR_DOCUMENTO_BASE");
            LOGGER.warning("Completar documento base");
            isAceptarFinalizarTareaValid = Boolean.TRUE;
            try {
                if (isAceptarFinalizarTareaValid == true) {
                    idImplementacion = getCodigoImplementacion();
                    LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion actualizar payload." + idImplementacion);
                    AttributeBinding attributeBindingIdImplementacion = null;
                    attributeBindingIdImplementacion = ADFUtils.findControlBinding("IdImplementacion");
                    actualizarPayloadElement("IdImplementacion", idImplementacion);
                    LOGGER.severe("Revisar Valor del payload en true Despues de insertar." +
                                  attributeBindingIdImplementacion.getInputValue());
                } else {
                    LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion actualizar payload es null.");
                }
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): Valor del payload no actualizado - " + e);
            }
            break;
        case FenixConstants.PA11_VALIDAR_DOCUMENTO_BASE:
            LOGGER.fine("PA11_VALIDAR_DOCUMENTO_BASE");
            LOGGER.warning("Validar documento base");
            isAceptarFinalizarTareaValid = Boolean.TRUE;
            try {
                if (isAceptarFinalizarTareaValid == true) {
                    idImplementacion = getCodigoImplementacion();
                    LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion actualizar payload." + idImplementacion);
                    AttributeBinding attributeBindingIdImplementacion = null;
                    attributeBindingIdImplementacion = ADFUtils.findControlBinding("IdImplementacion");
                    actualizarPayloadElement("IdImplementacion", idImplementacion);
                    LOGGER.severe("Revisar Valor del payload en true Despues de insertar." +
                                  attributeBindingIdImplementacion.getInputValue());
                } else {
                    LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion actualizar payload es null.");
                }
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): Valor del payload no actualizado - " + e);
            }
            break;
        case FenixConstants.PA11_INICIAR_ADQUISICION:
            LOGGER.fine("PA11_INICIAR_ADQUISICION");
            LOGGER.warning("Iniciar adquisicion");
            BindingContainer bindingsIA = ADFUtils.getBindingContainer();
            OperationBinding operationBindingIA = null;
            banderaValidarIdImplementacionExiste = Boolean.FALSE;
            idImplementacion = getCodigoImplementacion();
            try {
                operationBindingIA = bindingsIA.getOperationBinding("obtenerImplementacionByIdImplementacion");
                operationBindingIA.getParamsMap().put("idImplementacion", idImplementacion);
                operationBindingIA.execute();
                banderaValidarIdImplementacionExiste = (Boolean)operationBindingIA.getResult();
                
                /*//VerCrearFormularioImpPctBean verCrearFormularioImpPctBean = new VerCrearFormularioImpPctBean();
                VerCrearFormularioImpPctBean verCrearFormularioImpPctBean = (VerCrearFormularioImpPctBean)  
                    JSFUtils.resolveExpression("#{pageFlowScope.VerCrearFormularioImpPctBean}");
                banderaValidarIdImplementacionExiste = verCrearFormularioImpPctBean.getValidaIdImplementacion();*/
                LOGGER.warning("banderaValidarIdImplementacionExiste. " + banderaValidarIdImplementacionExiste);
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR,
                           "Error en banderaValidarIdImplementacionExiste." + e.getClass() + "." + e.getMessage());
            }
            if (banderaValidarIdImplementacionExiste == true) {
                isAceptarFinalizarTareaValid = actualizarInicioAdquisicion();
            }
            else{
                isAceptarFinalizarTareaValid = Boolean.TRUE;
            }
            try {
                if (isAceptarFinalizarTareaValid == true) {

                    if (obtenerBanderaValidacionRequiereLotes() == false) {
                        String nombrePorDefault = " ";
                        try {
                            DCIteratorBinding iteratorBinding =
                                (DCIteratorBinding) bindingsIA.get("FormularioImplementacionPctVOIterator");
                            if (iteratorBinding != null) {
                                Row currentRow = iteratorBinding.getCurrentRow();
                                if (currentRow != null) {
                                    nombrePorDefault = (String) currentRow.getAttribute("NombreAdquisicion");
                                }
                            }
                            LOGGER.warning("El valor seleccionado nombrePorDefault: " + nombrePorDefault);
                        } catch (Exception e) {
                            nombrePorDefault = " ";
                            LOGGER.severe("Error al obtener NombreAdquisicion.", e);
                            LOGGER.warning("Excepcion al obtener NombreAdquisicion." + e.getClass(), ".", e.getMessage());
                        }
                        
                        guardarLotePorDefectoAction(nombrePorDefault);
                    }

                    idImplementacion = getCodigoImplementacion();
                    LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion actualizar payload." + idImplementacion);
                    AttributeBinding attributeBindingIdImplementacion = null;
                    attributeBindingIdImplementacion = ADFUtils.findControlBinding("IdImplementacion");
                    actualizarPayloadElement("IdImplementacion", idImplementacion);
                    LOGGER.severe("Revisar Valor del payload en true Despues de insertar." +
                                  attributeBindingIdImplementacion.getInputValue());
                } else {
                    LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion actualizar payload es null...");
                }
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): Valor del payload no actualizado - " + e);
            }
            break;
        case FenixConstants.PA11_REGISTRAR_RESULTADO_ADQUISICION:
            LOGGER.fine("PA11_REGISTRAR_RESULTADO_ADQUISICION");
            LOGGER.warning("Registrar resultado adquisicion");
            BindingContainer bindingsRRA = ADFUtils.getBindingContainer();
            OperationBinding operationBindingRRA = null;
            banderaValidarIdImplementacionExiste = Boolean.FALSE;
            idImplementacion = getCodigoImplementacion();
            try {
                operationBindingRRA = bindingsRRA.getOperationBinding("obtenerImplementacionByIdImplementacion");
                operationBindingRRA.getParamsMap().put("idImplementacion", idImplementacion);
                operationBindingRRA.execute();
                banderaValidarIdImplementacionExiste = (Boolean)operationBindingRRA.getResult();
                
                /*
               // VerCrearFormularioImpPctBean verCrearFormularioImpPctBean = new VerCrearFormularioImpPctBean();
               VerCrearFormularioImpPctBean verCrearFormularioImpPctBean = (VerCrearFormularioImpPctBean)  
                   JSFUtils.resolveExpression("#{pageFlowScope.VerCrearFormularioImpPctBean}");
                banderaValidarIdImplementacionExiste = verCrearFormularioImpPctBean.getValidaIdImplementacion();*/
                LOGGER.warning("banderaValidarIdImplementacionExiste. " + banderaValidarIdImplementacionExiste);
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR,
                           "Error en banderaValidarIdImplementacionExiste.", e);
            }
            if (banderaValidarIdImplementacionExiste == true) {
                Long idLoteImp = getCodigoLoteImplementacion();
                isAceptarFinalizarTareaValid = actualizarRegistrarResultadoAdquisicion(idLoteImp);
            }else{
                LOGGER.warning("Error, No existe el registro de la implementacion.");
                JSFUtils.addFacesErrorMessage("Error, No existe el registro de la implementacion.");
            }
            
            if(isAceptarFinalizarTareaValid){
                
                //Se hace busqueda del Termino T704 - Orden de inicio
                ordenInicio = obtenerValorOrdenInicio();
                
                if(null != ordenInicio){
                    LOGGER.warning("Asignando valor ordenInicio al Payload.");
                    actualizarPayloadElement("ordenInicio", ordenInicio);                        
                }else{
                    LOGGER.warning("Orden de inicio es NULL.");
                    isAceptarFinalizarTareaValid = Boolean.FALSE;
                }
            }
            
            break;
        case FenixConstants.PA11_VALIDAR_TASA_FECHA:
            //TODO: Ejecutar VAMI
            LOGGER.fine("PA11_VALIDAR_TASA_FECHA");
            LOGGER.warning("Validar tasa fecha.");
            isAceptarFinalizarTareaValid = ejecutarOperacionVami();
            LOGGER.warning("Valor de retorno del metodo ejecutarOperacionVami() :" + isAceptarFinalizarTareaValid);
            if (!isAceptarFinalizarTareaValid) {
                LOGGER.warning("La tarea no puede finalizar, error en el servicio.");
                //JSFUtils.addFacesErrorMessage("La tarea no puede finalizar");
            }
            break;
            case FenixConstants.PA11_REALIZAR_AJUSTES_TASA_FECHA:
                LOGGER.fine("PA11_REALIZAR_AJUSTES_TASA_FECHA");
                LOGGER.warning("Realizar ajustes tasa fecha.");
                Map<String, Object> params = new HashMap<String, Object>();
                
                EnvioAlCobroBean envioAlCobroBean =
                    (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobroBean}");
                Integer idTipoTasa = null;
                idTipoTasa = envioAlCobroBean.getIdTipoTasa();
                
                if(idTipoTasa != null){
                    LOGGER.warning("Value idTipoTasa: " + idTipoTasa.toString());
                    if(idTipoTasa.compareTo(3) == 0){
                        String codigoTasa = obtenerCodigoTasaReferencia();
                        if (null != codigoTasa && !codigoTasa.isEmpty()) {
                            params.put("idOperacion", getCodigoOperacion());
                            params.put("codigoTasa", codigoTasa);
                            try {
                                isAceptarFinalizarTareaValid = execute(params, "guardarDatosConTasaOperacion");
                            } catch (Exception e) {
                                LOGGER.warning("Error al ejecutar el operBinding de guardado de finalizacion de estudios.", e);
                            }
                        } else {
                            isAceptarFinalizarTareaValid = Boolean.FALSE;
                        }
                    }
                    else{
                        params.put("idOperacion", getCodigoOperacion());
                        try {
                            isAceptarFinalizarTareaValid = execute(params, "guardarDatosSinTasaOperacion");
                        } catch (Exception e) {
                            isAceptarFinalizarTareaValid = Boolean.FALSE;
                            LOGGER.warning("Error al ejecutar el operBinding de guardado sin tasa de finalizacion de estudios.", e);
                        }
                    }
                }
                else{
                    JSFUtils.addFacesErrorMessage("No es posible finalizar la tarea, Tipo tasa es null.");
                }
                
                LOGGER.warning("Es valido aceptar Finalizar Tarea: " + isAceptarFinalizarTareaValid);
                break;
        case FenixConstants.PA11_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO:
            LOGGER.fine("PA11_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO");
            LOGGER.warning("Registrar orden de inicio y datos de linea de credito.");

            isAceptarFinalizarTareaValid = guardarInformacion();
            break;
        case FenixConstants.PA11_VALIDAR_DATOS_LINEA_CREDITO:
            LOGGER.fine("PA11_VALIDAR_DATOS_LINEA_CREDITO");
            LOGGER.warning("Validar datos de linea de credito.");
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("propagarLineaCredito");
            Boolean resultado = (Boolean) operationBinding.execute();

            LOGGER.log(ADFLogger.WARNING, "propagarLineaCredito resultado: " + resultado);

            if (operationBinding.getErrors().isEmpty()) {
                isAceptarFinalizarTareaValid = Boolean.TRUE;
            } else {
                LOGGER.warning("La propagacion de lineas de credito contiene errores");
                bundleKeyErrorList.add("ERROR_PROPAGAR_LINEA");
            }
            
            break;
        case FenixConstants.PA11_LIQUIDAR_DESEMOBOLSOS_ANTERIORES:
            LOGGER.fine("PA11_LIQUIDAR_DESEMOBOLSOS_ANTERIORES");
            LOGGER.warning("Liquidar desembolsos anteriores.");
            isAceptarFinalizarTareaValid = Boolean.TRUE;
            break;
        default:
            LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }

        if (!isAceptarFinalizarTareaValid) {
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
            LOGGER.warning("Se cancela la confirmacion de Finalizar tarea");
            return null;
        } else {
            InvokeActionBean invokeActionBean =
                (InvokeActionBean) JSFUtils.getManagedBeanValue(INVOKE_ACTION_BEAN_NAME);
            LOGGER.exiting(this.getClass().getName(), "aceptarFinalizarTarea");
            LOGGER.warning("Finaliza la tarea correctamente.");
            return invokeActionBean.invokeOperation();
        }
    }
    
    
    public Long recuperarIdLote(){
      LOGGER.warning("inicia metodo recuperarIdLote.");
      Long idLote = null;
      Long idImplementacion = null;
      BindingContainer bindings = ADFUtils.getBindingContainer();
      OperationBinding operationBinding = null;
      
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        
        idImplementacion = (implementacionBean.getIdImplementacion() == null || implementacionBean.getIdImplementacion().equals(""))
                         ? null : new Long(implementacionBean.getIdImplementacion());
        
        operationBinding = bindings.getOperationBinding("agregarLoteGestionarProcesContrat");
        operationBinding.getParamsMap().put("idImplementacion", idImplementacion);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {            
            JSFUtils.addFacesErrorMessage("Error ->"+operationBinding.getErrors().toString());
        }else{
           idLote = (Long)operationBinding.getResult();
        }
        
     LOGGER.warning("termina metodo recuperarIdLote idLote->"+idLote);
     return idLote;
    }
    
    
    public String obtenerCodigoTasaReferencia() {
        LOGGER.warning("Inside obtenerCodigoTasaReferencia.");
        
        AttributeBinding bindingValorTasa;
        bindingValorTasa = ADFUtils.findControlBinding("CodTasRef");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String codigo = null;
        BigDecimal valorActual = null;

        String descripcionTasa = (String) bindingValorTasa.getInputValue();
        LOGGER.warning("descripcionTasa: " + descripcionTasa);
        
        operationBinding = bindings.getOperationBinding("obtenerDatosTasaReferencia");
        operationBinding.getParamsMap().put("descripcionTasa", descripcionTasa);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            Map<String, Object> resultado = new HashMap<String, Object>();
            resultado = (HashMap<String, Object>) operationBinding.getResult();

            codigo = (String) resultado.get("Codigo");

            LOGGER.warning("codigo: " + codigo);
        }
        
        return codigo;
    }
    
    
    public Boolean actualizarEstadosDesembolso(Integer estadoAnterior, Integer estadoNuevo) {
        LOGGER.warning("Inside actualizarEstadosDesembolso.");
        Boolean respuesta=Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding("actualizarEstadoDesembolso");
        operationBinding.getParamsMap().put("estadoAnterior", estadoAnterior);
        operationBinding.getParamsMap().put("estadoNuevo", estadoNuevo);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            respuesta=(Boolean)operationBinding.getResult();
        }
        LOGGER.warning("respuesta " +respuesta);
        return respuesta;
    }
    
    public Boolean actualizarEstadosDesembolsoACreado() {
        LOGGER.warning("Inside actualizarEstadosDesembolsoACreado.");
        Boolean respuesta=Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding("actualizarEstadoDesembolsoCreado");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            respuesta=(Boolean)operationBinding.getResult();
        }
        LOGGER.warning("respuesta " +respuesta);
        return respuesta;
    }
    
    public Boolean obtenerValorOrdenInicio(){
        LOGGER.warning("Inicia metodo obtenerValorOrdenInicio.");
        Boolean valorOrdenInicio = Boolean.FALSE;
        Integer valorOrdenInicioInt = null;
        Map<String, Object> params = new HashMap<String, Object>();
        Long idOperacion = getCodigoOperacion();
        Row row = null;
        
        //Se busca el registro del termino T704 - Orden de inicio con ID = 39
        params.put("idOperacion", idOperacion);
        params.put("idTcaTermino", ID_TCA_TERMINO_ORDEN_INICIO);
        try{
            row = execute(params, "obtenerTerminoPorIdOperacionTcaTermino");
        }catch(Exception e){
            LOGGER.warning("ERROR al ejecutar operBinding obtenerTerminoPorIdOperacionTcaTermino.", e);
            JSFUtils.addFacesErrorMessage("No se pudo recuperar el Orden de Inicio: " + e.getMessage());
            valorOrdenInicio = null;
        }
        
        if(null != row){
            
            valorOrdenInicioInt = (Integer) row.getAttribute("IdTcaTermino");
            if(null != valorOrdenInicioInt){
                if(valorOrdenInicioInt.compareTo(0)==0){
                    valorOrdenInicio = Boolean.FALSE;
                }else{
                    valorOrdenInicio = Boolean.TRUE;
                }
            }
            
            LOGGER.warning("Orden de inicio: " + valorOrdenInicio);
            if(null == valorOrdenInicio){
                LOGGER.warning("El Termino t704 no tiene el valor OrdenInicio.");
            }
        }else{
            LOGGER.warning("No se encontro registro de T704.");
        }
                        
        LOGGER.warning("Termina metodo obtenerValorOrdenInicio.");
        return valorOrdenInicio;
    }

    public String cancelarFinalizarTarea() {
        popupFinalizarTarea.hide();
        return null;
    }

    public String invocarMasInformacion() {
        LOGGER.entering(this.getClass().getName(), "invocarMasInformacion");
        LOGGER.warning("Ingresa metodo invocarMasInformacion");

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean isValidMasInformacion = Boolean.FALSE;
        Boolean existeDocumento = Boolean.FALSE;
        Boolean existeComentario = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {
        case FenixConstants.PA11_SOLICITUD_ENVIO_GASTO:
            LOGGER.warning("Ingresa case Solicitud envio al gasto MasInformacion");
            existeComentario =
                validateComentario(getCodigoOperacion(), String.valueOf(getCodigoTarea()), getInstanciaTarea());
            if (!existeComentario) {
                bundleKeyErrorList.add("MSG_ERROR_COMENTARIO_OBLIGATORIO_PA11SOLICITUDENVIOGASTO");
            }
            boolean existeOperacionAsociada = validarOperacionesAsociadas(getCodigoOperacion());
            if (!existeOperacionAsociada) {
                bundleKeyErrorList.add("MSG_ERROR_OP_ASOC_OBLIGATORIA_PA11SOLICITUDENVIOGASTO");
            }
            
            if (existeComentario && existeOperacionAsociada) {
                isValidMasInformacion = Boolean.TRUE;
            }
            break;
        case FenixConstants.PA11_VALIDAR_SOLICITUD:
            LOGGER.warning("Ingresa case Validar Solicitud envio al gasto MasInformacion");
            existeComentario =
                validateComentario(getCodigoOperacion(), String.valueOf(getCodigoTarea()), getInstanciaTarea());
            if (!existeComentario) {
                bundleKeyErrorList.add("MSG_ERROR_COMENTARIO_OBLIGATORIO_PA11VALIDARSOLICITUDENVIOGASTO");
            } else {
                isValidMasInformacion = Boolean.TRUE;
            }
            break;
        case FenixConstants.PA11_VALIDAR_TASA_FECHA:
            LOGGER.warning("Ingresa case Validar tasa fecha MasInformacion");
            existeComentario =
                validateComentario(getCodigoOperacion(), String.valueOf(getCodigoTarea()), getInstanciaTarea());
            if (!existeComentario) {
                bundleKeyErrorList.add("MSG_ERROR_COMENTARIO_TASA_FECHA");
            } else {
                isValidMasInformacion = Boolean.TRUE;
            }
            break;
        case FenixConstants.PA11_LIQUIDAR_DESEMOBOLSOS_ANTERIORES:
            LOGGER.warning("Ingresa case Liquidar desembolsos anteriores MasInformacion");
            existeComentario =
                validateComentario(getCodigoOperacion(), String.valueOf(getCodigoTarea()), getInstanciaTarea());
            if (!existeComentario) {
               // isValidMasInformacion = Boolean.TRUE;
                bundleKeyErrorList.add("MSG_ERROR_COMENTARIO_PARA_AJUSTES");
            } else {
                isValidMasInformacion = Boolean.TRUE;
            }
            break;
        case FenixConstants.PA11_VALIDAR_ENVIO_COBRO:
            LOGGER.warning("Ingresa case Validar envio al cobro MasInformacion");
            existeComentario =
                validateComentario(getCodigoOperacion(), String.valueOf(getCodigoTarea()), getInstanciaTarea());
            if (!existeComentario) {
               // isValidMasInformacion = Boolean.TRUE;
                bundleKeyErrorList.add("MSG_ERROR_COMENTARIO_PARA_AJUSTES");
            } else {
                isValidMasInformacion = Boolean.TRUE;
            }
            break;
        default:
            LOGGER.log(ADFLogger.ERROR, "invocarFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        if (!isValidMasInformacion) {
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        } else {
            masInformacionPopup();
        }
        LOGGER.warning("Finaliza metodo invocarMasInformacion");
        LOGGER.exiting(this.getClass().getName(), "invocarMasInformacion");
        return null;
    }

    public void  masInformacionPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupMasInformacion.show(hints);
    }

    public String aceptarMasInformacion() {
        LOGGER.entering(this.getClass().getName(), "aceptarMasInformacion");
        LOGGER.warning("Entra en aceptarMasInformacion");
        popupMasInformacion.hide();
        cargarDocumentosOnBase();
        // InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue(INVOKE_ACTION_BEAN_NAME);
        // return invokeActionBean.invokeOperation();
        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean isAceptarFinalizarTareaValid = Boolean.TRUE;
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {
        case FenixConstants.PA11_LIQUIDAR_DESEMOBOLSOS_ANTERIORES:
            LOGGER.fine("PA11_LIQUIDAR_DESEMOBOLSOS_ANTERIORES");
            LOGGER.warning("Liquidar desembolso Anteriores");
            isAceptarFinalizarTareaValid =
                actualizarEstadosDesembolso(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_CREADO_POR_IMPLEMENTACION,
                                            FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_VALIDADO_POR_IMPLEMENTACION);
            if (!isAceptarFinalizarTareaValid) {
                bundleKeyErrorList.add("MSG_ERROR_MAS_INFO");
            }
            break;
        case FenixConstants.PA11_VALIDAR_ENVIO_COBRO:
            LOGGER.fine("PA11_VALIDAR_ENVIO_COBRO");
            LOGGER.warning("validar envio al cobro");
//            Boolean actualiza = Boolean.FALSE;
//            actualiza = actualizarDatosFlexcube();
//            isAceptarFinalizarTareaValid = (actualiza) ? Boolean.TRUE : Boolean.FALSE;

//            if (isAceptarFinalizarTareaValid) {
            try{
                isAceptarFinalizarTareaValid =
                    actualizarEstadosDesembolsoACreado();
                if (!isAceptarFinalizarTareaValid) {
                    bundleKeyErrorList.add("MSG_ERROR_MAS_INFO");
                }
            }catch(Exception e){
                LOGGER.warning("Error en actualizar estados de los contratos.", e);
            }
 //           }
            break;
        }
        if (!isAceptarFinalizarTareaValid) {
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
            LOGGER.warning("Se cancela la confirmacion de Mas Información");
            return null;
        } else {
            InvokeActionBean invokeActionBean =
                (InvokeActionBean) JSFUtils.getManagedBeanValue(INVOKE_ACTION_BEAN_NAME);
            LOGGER.exiting(this.getClass().getName(), "aceptarMasInformacion");
            LOGGER.warning("Termina Mas Información correctamente");
            return invokeActionBean.invokeOperation();
        }


    }

    public String cancelarMasInformacion() {
        popupMasInformacion.hide();
        return null;
    }

    private BigDecimal totalizarMontosLineasCreditoDesembolso() {
        LOGGER.entering(this.getClass().getName(), "totalizarMontosLineasCreditoDesembolso");
        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer binding = bindingctx.getCurrentBindingsEntry();
        DCBindingContainer bindingsImpl = (DCBindingContainer) binding;
        DCIteratorBinding dciter = bindingsImpl.findIteratorBinding("LineaCreditoDesembolsoImpEnvioGastoVOIterator");
        LineaCreditoDesembolsoImpEnvioGastoVOImpl vo =
            (LineaCreditoDesembolsoImpEnvioGastoVOImpl) dciter.getViewObject();
        Row[] lineasCreditoDesembolso = vo.getAllRowsInRange();
        BigDecimal montoTotalLineasCreditoDesembolso = new BigDecimal(0);
        for (Row row : lineasCreditoDesembolso) {
            LOGGER.warning("Id: " + row.getAttribute("Id"));
            LOGGER.warning("NumeroLineaCredito: " + row.getAttribute("NumeroLineaCredito"));
            LOGGER.warning("IdOperacion: " + row.getAttribute("IdOperacion"));
            LOGGER.warning("MontoLinea: " + row.getAttribute("MontoLinea"));
            if(null != row.getAttribute("MontoLinea")){
                LOGGER.warning("Sumar MontoLinea");
                montoTotalLineasCreditoDesembolso =
                    montoTotalLineasCreditoDesembolso.add((BigDecimal) row.getAttribute("MontoLinea"));
                LOGGER.warning("Valor montoTotalLineasCreditoDesembolso: " + montoTotalLineasCreditoDesembolso);
            }
            else{
                LOGGER.warning("Valor montoTotalLineasCreditoDesembolso: " + montoTotalLineasCreditoDesembolso);
            }
        }
        LOGGER.exiting(this.getClass().getName(), "totalizarMontosLineasCreditoDesembolso");
        return montoTotalLineasCreditoDesembolso;
    }
    
    private BigDecimal totalizarMontosPorContratosDesembolso() {
        LOGGER.entering(this.getClass().getName(), "totalizarMontosPorContratosDesembolso");
        
        BigDecimal montoTotalPorContratosDesembolso = new BigDecimal(0);
        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Long idOperacion = getCodigoOperacion();
        
        try {
            operationBinding = bindings.getOperationBinding("setpIdOperacionMontoTotalEnvioGastoPorDesembolso");
            operationBinding.getParamsMap().put("value", idOperacion);
            operationBinding.execute();
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING, "Error al ejecutar actualizarDatosEscrituracion :" + e);
        }

        try {
            operationBinding = bindings.getOperationBinding("obtenerMontoTotalPorContratosDesembolso");
            operationBinding.execute();
            montoTotalPorContratosDesembolso = (BigDecimal) operationBinding.getResult();
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING, "Error al ejecutar actualizarDatosEscrituracion :" + e);
        }
        
        LOGGER.exiting(this.getClass().getName(), "totalizarMontosPorContratosDesembolso");
        return montoTotalPorContratosDesembolso;
    }

    private boolean validarTransferenciasAplicadas() {
        LOGGER.entering(this.getClass().getName(), "validarTransferenciasAplicadas");
        LOGGER.warning("ingresa metodo validarTransferenciasAplicadas");

        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer binding = bindingctx.getCurrentBindingsEntry();
        DCBindingContainer bindingsImpl = (DCBindingContainer) binding;
        DCIteratorBinding dciter = bindingsImpl.findIteratorBinding("LineaCreditoDesembolsoImpEnvioGastoVOIterator");
        //Master VO LineaCreditoDesembolsoImpEnvioGastoVOImpl
        LineaCreditoDesembolsoImpEnvioGastoVOImpl vo =
            (LineaCreditoDesembolsoImpEnvioGastoVOImpl) dciter.getViewObject();
        //Master VO rows
        Row[] lineasCreditoDesembolso = vo.getAllRowsInRange();
        Integer contador = 0;
        for (Row row : lineasCreditoDesembolso) {
            //ChildRow accessor
            RowSet rs = (RowSet) row.getAttribute("ContratoDesembolsoTreeEnvioGastoVO");

            while (rs.hasNext()) {
                Row r = rs.next();
                if(null != r.getAttribute("IdTransferenciaFt05")){
                    Long transferencia = (Long) r.getAttribute("IdTransferenciaFt05");
                    LOGGER.warning("La transferencia ha sido aplicada para el contrato :" + transferencia);
            }else{
                    LOGGER.warning("La transferencia no se ha aplicado para el contrato");
                    contador++;
                }
            }
        }

        LOGGER.warning("termina metodo validarTransferenciasAplicadas");
        LOGGER.exiting(this.getClass().getName(), "validarTransferenciasAplicadas");
        if(contador > 0){
            return false;
        }else{
            return true;  
        }
        
    }

    @SuppressWarnings("oracle.jdeveloper.java.unchecked-conversion-or-cast")
    private boolean validarContratosSaldados() {
        LOGGER.entering(this.getClass().getName(), "validarContratosSaldados");
        LOGGER.warning("ingresa metodo validarContratosSaldados");
        //Se obtiene los contratos de desembolso y sus montos a desembolsar
        Map contratosDesembolso = new HashMap();
        BindingContext bindingctx0 = BindingContext.getCurrent();
        BindingContainer binding0 = bindingctx0.getCurrentBindingsEntry();
        DCBindingContainer bindingsImpl0 = (DCBindingContainer) binding0;
        DCIteratorBinding dciter0 = bindingsImpl0.findIteratorBinding("LineaCreditoDesembolsoImpEnvioGastoVOIterator");
        //Master VO LineaCreditoDesembolsoImpEnvioGastoVOImpl
        LineaCreditoDesembolsoImpEnvioGastoVOImpl vo =
            (LineaCreditoDesembolsoImpEnvioGastoVOImpl) dciter0.getViewObject();
        //Master VO rows
        Row[] lineasCreditoDesembolso = vo.getAllRowsInRange();

        for (Row row : lineasCreditoDesembolso) {
            //ChildRow accessor
            RowSet rs = (RowSet) row.getAttribute("ContratoDesembolsoTreeEnvioGastoVO");

            while (rs.hasNext()) {
                Row r = rs.next();
                contratosDesembolso.put(r.getAttribute("Id"), r.getAttribute("MontoDesembolsar"));
            }
        }

        //Se ejecuta la validacion de Contratos Saldados
        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer binding = bindingctx.getCurrentBindingsEntry();
        DCBindingContainer bindingsImpl = (DCBindingContainer) binding;
        DCIteratorBinding dciter = bindingsImpl.findIteratorBinding("FT05VO1Iterator");
        FT05VOImpl vo1 = (FT05VOImpl) dciter.getViewObject();
        boolean result = vo1.validarSaldoContratosDesembolso(contratosDesembolso);

        LOGGER.warning("termina metodo validarContratosSaldados");
        LOGGER.exiting(this.getClass().getName(), "validarContratosSaldados");
        return result;
    }

    private Boolean validarOperacionesAsociadas(Long idOperacion) {
        LOGGER.entering(this.getClass().getName(), "validarOperacionesAsociadas");
        LOGGER.warning("Ingresa metodo validarOperacionesAsociadas");
        Boolean existeOperacionAsociada = Boolean.FALSE;
        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer binding = bindingctx.getCurrentBindingsEntry();
        DCBindingContainer bindingsImpl = (DCBindingContainer) binding;
        DCIteratorBinding dciter = bindingsImpl.findIteratorBinding("AsociadasVOIterator");
        AsociadasVOImpl vo = (AsociadasVOImpl) dciter.getViewObject();

        try {
            ViewCriteria asociadasVOCriteria = vo.getViewCriteriaManager().getViewCriteria("AsociadasVOCriteria");
            asociadasVOCriteria.ensureVariableManager().setVariableValue("idOperacionBind", idOperacion);

            vo.applyViewCriteria(asociadasVOCriteria);
            vo.executeQuery();

            if (vo.getEstimatedRowCount() > 0) {
                existeOperacionAsociada = Boolean.TRUE;
            } else {
                existeOperacionAsociada = Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.log(ADFLogger.ERROR,
                       "Error en validarOperacionesAsociadas " + ex.getClass() + ":" + ex.getMessage());
        }
        LOGGER.warning("Valor a retornar existeOperacionAsociada: " + existeOperacionAsociada);
        LOGGER.warning("Finaliza metodo validarOperacionesAsociadas");
        LOGGER.exiting(this.getClass().getName(), "validarOperacionesAsociadas");
        return existeOperacionAsociada;
    }

    public void onLineaCreditoTreeTableSelect(SelectionEvent selectionEvent) {
        LOGGER.entering(this.getClass().getName(), "onLineaCreditoTreeTableSelect");
        
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");

        LOGGER.warning("Ingresa onLineaCreditoTreeTableSelect");
        
        //original selection listener set by ADF
        //#{bindings.LineaCreditoDesembolsoVO.treeModel.makeCurrent}
        //#{bindings.LineaCreditoDesembolsoImpEnvioGastoVO.treeModel.makeCurrent}"
        String adfSelectionListener = "#{bindings.LineaCreditoDesembolsoImpEnvioGastoVO.treeModel.makeCurrent}";

        //make sure the default selection listener functionality is preserved.
        /* START PRESERVER DEFAULT ADF SELECT BEHAVIOR */
        FacesContext fctx = FacesContext.getCurrentInstance();
        Application application = fctx.getApplication();
        ELContext elCtx = fctx.getELContext();
        ExpressionFactory exprFactory = application.getExpressionFactory();

        MethodExpression me = null;
        me = exprFactory.createMethodExpression(elCtx, adfSelectionListener, Object.class, new Class[] {
                                                SelectionEvent.class });
        me.invoke(elCtx, new Object[] { selectionEvent });
        /* END PRESERVER DEFAULT ADF SELECT BEHAVIOR */

        RichTreeTable tree = (RichTreeTable) selectionEvent.getSource();
        TreeModel model = (TreeModel) tree.getValue();

        //get selected nodes
        RowKeySet rowKeySet = selectionEvent.getAddedSet();
        Iterator rksIterator = rowKeySet.iterator();
        while (rksIterator.hasNext()) {
            List key = (List) rksIterator.next();
            JUCtrlHierBinding treeBinding = null;
            CollectionModel collectionModel = (CollectionModel) tree.getValue();
            treeBinding = (JUCtrlHierBinding) collectionModel.getWrappedData();
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinding.findNodeByKeyPath(key);
            Row rw = nodeBinding.getRow();

            String rowType = rw.getStructureDef().getDefName();
            if (rowType.equalsIgnoreCase("LineaCreditoDesembolsoImpEnvioGastoVO")) {
                LOGGER.warning("Valor id Linea seleccionada seleccionada : " + (Long) rw.getAttribute("Id"));
                ADFUtils.setBoundAttributeValue(VAR_IS_CONTRATO_SELECCIONADO, false);
                ADFUtils.setBoundAttributeValue(VAR_ID_CONTRATO_SELECCIONADO, 0L);
                implementacionBean.setIdLineaCredito(Long.valueOf(rw.getAttribute("Id").toString()));
                implementacionBean.setIdContrato(null);
            } else if (rowType.equalsIgnoreCase("ContratoDesembolsoTreeEnvioGastoVO")) {
                LOGGER.warning("Valor id contrato seleccionado: " + (Long) rw.getAttribute("Id"));
                ADFUtils.setBoundAttributeValue(VAR_ID_CONTRATO_SELECCIONADO, (Long) rw.getAttribute("Id"));
                ADFUtils.setBoundAttributeValue(VAR_IS_CONTRATO_SELECCIONADO, true);
                implementacionBean.setIdContrato(Long.valueOf(rw.getAttribute("Id").toString()));
                implementacionBean.setIdLineaCredito(null);
            }
        }
        
        if(implementacionBean.getCodigoTarea().equalsIgnoreCase(LIQUIDAR_DESEMBOLSOS))
        {
            String cuentaCustomer = null;
            String cuentaPuente = null;
            cuentaPuente = obtenerCuentaPuente();
            implementacionBean.setEsValorNulo(Boolean.FALSE);
            
            Map resultado = null;
            String contratoFlexcube = null;
            Timestamp fechaEfectiva = null;
            BindingContainer bindings1 = ADFUtils.getBindingContainer();
            OperationBinding operationBinding1 = null;
            operationBinding1 = bindings1.getOperationBinding("obtenerContratoFlexFechaEfectivaFT05");
            operationBinding1.getParamsMap().put("idContrato", ADFUtils.getBoundAttributeValue(VAR_ID_CONTRATO_SELECCIONADO));
            operationBinding1.execute();
            if (!operationBinding1.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding1.getErrors().toString());
            } else {
                resultado = (Map)operationBinding1.getResult();
                LOGGER.warning("Valor obtenido resultado(contratoFlexcube): " + resultado.get("contratoFlexcube"));
                LOGGER.warning("Valor obtenido resultado(fechaEfectiva): " + resultado.get("fechaEfectiva"));
                LOGGER.warning("Valor obtenido resultado(cuentaCliente): " + resultado.get("cuentaCliente"));
                if(resultado.get("contratoFlexcube") != null)
                    contratoFlexcube = (String)resultado.get("contratoFlexcube");
                if(resultado.get("fechaEfectiva") != null)
                    fechaEfectiva = (Timestamp)resultado.get("fechaEfectiva");
                if(null != resultado.get("cuentaCliente")){
                    cuentaCustomer = (String)resultado.get("cuentaCliente");
                }else{
                    LOGGER.warning("La cuenta del cliente es nula");
                    implementacionBean.setEsValorNulo(Boolean.TRUE);
                }
                if(contratoFlexcube != null || fechaEfectiva != null){
                    LOGGER.warning("Ya existe un contratoFlexcube o una fecha efectiva en base de datos, se cargan los valores en los campos restantes");
                    operationBinding1 = bindings1.getOperationBinding("insertarRowValidarFT");
                    operationBinding1.getParamsMap().put("idContratoDesembolso", ADFUtils.getBoundAttributeValue(VAR_ID_CONTRATO_SELECCIONADO));
                    operationBinding1.getParamsMap().put("contratoFelxcube", contratoFlexcube);
                    operationBinding1.getParamsMap().put("fechaEfectiva", fechaEfectiva);
                    operationBinding1.getParamsMap().put("cuentaCustomer", cuentaCustomer);
                    operationBinding1.getParamsMap().put("cuentaPuente", cuentaPuente);
                    operationBinding1.execute();
                    implementacionBean.setHabilitarBtnValidarFT05(Boolean.TRUE);
                    /*
                    ADFUtils.setBoundAttributeValue("idContratoDesembolso", idContratoSeleccionado);
                    ADFUtils.setBoundAttributeValue("BHQTransferencia", contratoFlexcube);
                    ADFUtils.setBoundAttributeValue("FechaEfectiva", fechaEfectiva);*/
                }
                else{
                    operationBinding1 = bindings1.getOperationBinding("insertarRowValidarFT");
                    operationBinding1.getParamsMap().put("idContratoDesembolso", ADFUtils.getBoundAttributeValue(VAR_ID_CONTRATO_SELECCIONADO));
                    operationBinding1.getParamsMap().put("contratoFelxcube", contratoFlexcube);
                    operationBinding1.getParamsMap().put("fechaEfectiva", fechaEfectiva);
                    operationBinding1.getParamsMap().put("cuentaCustomer", cuentaCustomer);
                    operationBinding1.getParamsMap().put("cuentaPuente", cuentaPuente);
                    operationBinding1.execute();
                    implementacionBean.setHabilitarBtnValidarFT05(Boolean.FALSE);
                }
            }
            LOGGER.warning("Valor bandera HabilitarBtnValidarFT05: " + implementacionBean.getHabilitarBtnValidarFT05());
        }
        LOGGER.warning("Termina onLineaCreditoTreeTableSelect ");
        LOGGER.exiting(this.getClass().getName(), "onLineaCreditoTreeTableSelect");
    }

    public void validarFT() {
        LOGGER.entering(this.getClass().getName(), "validarFT");
        LOGGER.warning("Ingresa metodo validarFT");
        
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        String errorMessageService = null;

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean isContratoSeleccionado = (Boolean) ADFUtils.getBoundAttributeValue(VAR_IS_CONTRATO_SELECCIONADO);
        Long idContratoSeleccionado = (Long) ADFUtils.getBoundAttributeValue(VAR_ID_CONTRATO_SELECCIONADO);
        String cuentaPuente = null;
        implementacionBean.setEsValorNulo(Boolean.FALSE);
        
        LOGGER.warning("Valor obtenido isContratoSeleccionado: " + isContratoSeleccionado);
        LOGGER.warning("Valor obtenido idContratoSeleccionado: " + idContratoSeleccionado);

        if (isContratoSeleccionado == null || idContratoSeleccionado == null ||
            (isContratoSeleccionado != null && isContratoSeleccionado == false)) {
            bundleKeyErrorList.add("MSG_ERROR_SELECCION_CONTRATO_PA11LIQUIDARDESENBOLSOS");
        } else {
            try {
                cuentaPuente = obtenerCuentaPuente();
                /*
                BindingContext bindingctx = BindingContext.getCurrent();
                BindingContainer binding = bindingctx.getCurrentBindingsEntry();
                DCBindingContainer bindingsImpl = (DCBindingContainer) binding;
                DCIteratorBinding dciter = bindingsImpl.findIteratorBinding("FT05VO1Iterator");
                FT05VOImpl vo = (FT05VOImpl) dciter.getViewObject();
                vo.validarFT(idContratoSeleccionado);*/
                Map resultado = null;
                String contratoFlexcube = null;
                Timestamp fechaEfectiva = null;
                String cuentaCustomer = null;
                BindingContainer bindings1 = ADFUtils.getBindingContainer();
                OperationBinding operationBinding1 = null;
                operationBinding1 = bindings1.getOperationBinding("obtenerContratoFlexFechaEfectivaFT05");
                operationBinding1.getParamsMap().put("idContrato", idContratoSeleccionado);
                operationBinding1.execute();
                if (!operationBinding1.getErrors().isEmpty()) {
                    // Manejo de errores
                    JSFUtils.addFacesErrorMessage(operationBinding1.getErrors().toString());
                } else {
                    resultado = (Map)operationBinding1.getResult();
                    LOGGER.warning("Valor obtenido resultado(contratoFlexcube): " + resultado.get("contratoFlexcube"));
                    LOGGER.warning("Valor obtenido resultado(fechaEfectiva): " + resultado.get("fechaEfectiva"));
                    LOGGER.warning("Valor obtenido resultado(cuentaCliente): " + resultado.get("cuentaCliente"));
                    if(resultado.get("contratoFlexcube") != null)
                        contratoFlexcube = (String)resultado.get("contratoFlexcube");
                    if(resultado.get("fechaEfectiva") != null)
                        fechaEfectiva = (Timestamp)resultado.get("fechaEfectiva");
                    if(null != resultado.get("cuentaCliente")){
                        cuentaCustomer = (String)resultado.get("cuentaCliente");
                    }else{
                        LOGGER.warning("La cuenta del cliente es nula");
                        implementacionBean.setEsValorNulo(Boolean.TRUE);
                    }
                    if(contratoFlexcube != null){
                        LOGGER.warning("Ya existe un contratoFlexcube y una fecha efectiva en base de datos, no se ejecuta el servicio.");
                        operationBinding1 = bindings1.getOperationBinding("insertarRowValidarFT");
                        operationBinding1.getParamsMap().put("idContratoDesembolso", idContratoSeleccionado);
                        operationBinding1.getParamsMap().put("contratoFelxcube", contratoFlexcube);
                        operationBinding1.getParamsMap().put("fechaEfectiva", fechaEfectiva);
                        operationBinding1.getParamsMap().put("cuentaCustomer", cuentaCustomer);
                        operationBinding1.getParamsMap().put("cuentaPuente", cuentaPuente);
                        operationBinding1.execute();
                        /*
                        ADFUtils.setBoundAttributeValue("idContratoDesembolso", idContratoSeleccionado);
                        ADFUtils.setBoundAttributeValue("BHQTransferencia", contratoFlexcube);
                        ADFUtils.setBoundAttributeValue("FechaEfectiva", fechaEfectiva);*/
                    }else{
                        BindingContainer bindings = ADFUtils.getBindingContainer();
                        //manda ejecutar el methodAction 'validarFT'
                        OperationBinding operationBinding = bindings.getOperationBinding("validarFT");
                        operationBinding.getParamsMap().put("idContratoDesembolso", idContratoSeleccionado);
                        operationBinding.getParamsMap().put("cuentaCustomer", cuentaCustomer);
                        operationBinding.getParamsMap().put("cuentaPuente", cuentaPuente);
                        operationBinding.execute();
                        if(!operationBinding.getErrors().isEmpty()) {
                            // Manejo de errores
                            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.severe("Error en validarFT : ",e);
                bundleKeyErrorList.add("MSG_ERROR_VALIDAR_FT_PA11LIQUIDARDESENBOLSOS");
                errorMessageService = e.getMessage();
            }
        }

        if (bundleKeyErrorList.size() > 0) {
            for (String bundleKey : bundleKeyErrorList)
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            if(errorMessageService != null)
                JSFUtils.addFacesErrorMessage(errorMessageService);
        }
        LOGGER.warning("Termina metodo validarFT");
        LOGGER.exiting(this.getClass().getName(), "validarFT");
    }
    
    public String obtenerCuentaPuente(){
        LOGGER.warning("Entra en obtenerCuentaPuente");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        String cuentaPuente = null;
        try{
            OperationBinding operationBindingObtenerCuentaPuente =
                bindings.getOperationBinding("getCuentaPuenteDesembolso");
            operationBindingObtenerCuentaPuente.execute();
            cuentaPuente = (String) operationBindingObtenerCuentaPuente.getResult();
            LOGGER.warning("Valor obtenido de cuenta puente. " + cuentaPuente);
            return cuentaPuente;
        }catch(Exception e){
            LOGGER.warning("Error al ejecutar metodo getCuentaPuenteDesembolso.", e);
            return null;
        }
    }
    
    public void guardarJustificacionEnvioOperacion() {
        LOGGER.entering(this.getClass().getName(), "guardarJustificacionEnvioOperacion");
        LOGGER.warning("Ingresa metodo guardarJustificacionEnvioOperacion");

        try {
            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer binding = bindingctx.getCurrentBindingsEntry();
            DCBindingContainer bindingsImpl = (DCBindingContainer) binding;
            DCIteratorBinding dciter = bindingsImpl.findIteratorBinding("JustificacionEnvioOperacion1Iterator");
            JustificacionEnvioOperacionImpl vo = (JustificacionEnvioOperacionImpl) dciter.getViewObject();
            LOGGER.warning("Justificacion: " + (String) ADFUtils.getBoundAttributeValue(VAR_JUSTIFICACION));
            vo.actualizarJustificacionEnvioOperacion(getCodigoOperacion(),
                                                     (String) ADFUtils.getBoundAttributeValue(VAR_JUSTIFICACION));
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR,
                       "Error en guardarJustificacionEnvioOperacion " + e.getClass() + ":" + e.getMessage());
        }
        LOGGER.warning("Finaliza metodo guardarJustificacionEnvioOperacion");
        LOGGER.exiting(this.getClass().getName(), "guardarJustificacionEnvioOperacion");
    }

    public Boolean validarJustificacionEnvioCobro() {
        LOGGER.warning("Inicia metodo validarJustificacionEnvioCobro");
        Boolean resultado = Boolean.FALSE;
        String justificacionEnvio = null;

        try {
            justificacionEnvio = (String) ADFUtils.getBoundAttributeValue("JustificacionEnvio");
        } catch (Exception e) {
            LOGGER.warning("ERROR al recuperar valor de Justificacion.", e);
        }

        LOGGER.warning("JustificacionEnvio: " + justificacionEnvio);
        if (null != justificacionEnvio) {
            resultado = Boolean.TRUE;
        }

        LOGGER.warning("Termina metodo validarJustificacionEnvioCobro");
        return resultado;
    }

    public Boolean validarDatosContrato() {
        LOGGER.warning("Inicia metodo validarDatosContrato");
        Boolean resultado = Boolean.FALSE;
        String fondo = null;
        BigDecimal montoDesembolsar = null;
        Integer idTcaTipoMoneda = null;
        Timestamp fechaEfectiva = null;

        try {
            fondo = (String) ADFUtils.getBoundAttributeValue("Fondo");
        } catch (Exception e) {
            LOGGER.warning("ERROR al recuperar valor de Fondo.", e);
        }

        try {
            montoDesembolsar = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoDesembolsar");
        } catch (Exception e) {
            LOGGER.warning("ERROR al recuperar valor de MontoDesembolsar.", e);
        }

        try {
            idTcaTipoMoneda = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda");
        } catch (Exception e) {
            LOGGER.warning("ERROR al recuperar valor de IdTcaTipoMoneda.", e);
        }

        try {
            
            fechaEfectiva = (Timestamp) ADFUtils.getBoundAttributeValue("FechaEfectiva");
        } catch (Exception e) {
            LOGGER.warning("ERROR al recuperar valor de FechaEfectiva.", e);
        }

        LOGGER.warning("MontoDesembolsar: " + montoDesembolsar);
        if (null != montoDesembolsar) {
            resultado = Boolean.TRUE;
        } else {
            resultado = Boolean.FALSE;
            return resultado;
        }

        LOGGER.warning("IdTcaTipoMoneda: " + idTcaTipoMoneda);
        if (null != idTcaTipoMoneda) {
            resultado = Boolean.TRUE;
        } else {
            resultado = Boolean.FALSE;
            return resultado;
        }

        //Se comentan atributos ya que no son obligatorios
//        LOGGER.warning("Fondo: " + fondo);
//        if (null != fondo) {
//            resultado = Boolean.TRUE;
//        } else {
//            resultado = Boolean.FALSE;
//            return resultado;
//        }
//
//        LOGGER.warning("FechaEfectiva: " + fechaEfectiva);
//        if (null != fechaEfectiva) {
//            resultado = Boolean.TRUE;
//        } else {
//            resultado = Boolean.FALSE;
//            return resultado;
//        }

        LOGGER.warning("Termina metodo validarDatosContrato");
        return resultado;
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


    public void setJustificacionUIC(RichInputText justificacionUIC) {
        this.justificacionUIC = justificacionUIC;
    }

    public RichInputText getJustificacionUIC() {
        return justificacionUIC;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setTotalEnvioGasto(BigDecimal totalEnvioGasto) {
        this.totalEnvioGasto = totalEnvioGasto;
    }

    public BigDecimal getTotalEnvioGasto() {
        if (totalEnvioGasto == null) {
            //totalEnvioGasto = totalizarMontosLineasCreditoDesembolso();
            totalEnvioGasto = totalizarMontosPorContratosDesembolso();
        }
        return totalEnvioGasto;
    }

    private Integer getCodigoTarea() {
        LOGGER.entering(this.getClass().getName(), "getCodigoTarea");
        ImplementacionPCTBean implementacionPCTBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression(ImplementacionPCTBean.BEAN_EXPRESSION);
        if (null != implementacionPCTBean.getCodigoTarea() &&
            implementacionPCTBean.getCodigoTarea().trim().length() > 0) {
            LOGGER.exiting(this.getClass().getName(), "getCodigoTarea");
            return Integer.parseInt(implementacionPCTBean.getCodigoTarea());
        }
        LOGGER.log(ADFLogger.ERROR, "<No se pudo obtener el Codigo tarea>");
        LOGGER.exiting(this.getClass().getName(), "getCodigoTarea");
        return 0;
    }

    private Long getCodigoOperacion() {
        LOGGER.entering(this.getClass().getName(), "getCodigoOperacion");
        ImplementacionPCTBean implementacionPCTBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression(ImplementacionPCTBean.BEAN_EXPRESSION);
        if (null != implementacionPCTBean.getCodigoOperacion() &&
            implementacionPCTBean.getCodigoOperacion().trim().length() > 0) {
            LOGGER.exiting(this.getClass().getName(), "getCodigoOperacion");
            return Long.parseLong(implementacionPCTBean.getCodigoOperacion());
        }
        LOGGER.log(ADFLogger.ERROR, "<No se pudo obtener el Codigo operacion>");
        LOGGER.exiting(this.getClass().getName(), "getCodigoOperacion");
        return 0L;
    }
    
    private oracle.jbo.domain.Number getNumeroSerieDocumento() {
        LOGGER.warning("Entra en getNumeroSerieDocumento");
        ImplementacionPCTBean implementacionPCTBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression(ImplementacionPCTBean.BEAN_EXPRESSION);
        if (null != implementacionPCTBean.getNumeroSerieDocumento()) {
            LOGGER.exiting(this.getClass().getName(), "getNumeroSerieDocumento");
            return implementacionPCTBean.getNumeroSerieDocumento();
        }else{
            LOGGER.warning("El numero de serie del documento es nulo.");
        }
        LOGGER.log(ADFLogger.ERROR, "<No se pudo obtener el Numero de serie del documento.");
        LOGGER.exiting(this.getClass().getName(), "getNumeroSerieDocumento");
        return new oracle.jbo.domain.Number (0);
    }

    private Long getCodigoImplementacion() {
        LOGGER.entering(this.getClass().getName(), "getCodigoImplementacion");
        ImplementacionPCTBean implementacionPCTBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression(ImplementacionPCTBean.BEAN_EXPRESSION);
        if (null != implementacionPCTBean.getIdImplementacion() &&
            implementacionPCTBean.getIdImplementacion().trim().length() > 0) {
            LOGGER.exiting(this.getClass().getName(), "getCodigoImplementacion");
            return Long.parseLong(implementacionPCTBean.getIdImplementacion());
        }
        LOGGER.log(ADFLogger.ERROR, "<No se pudo obtener el Codigo implementacion>");
        LOGGER.exiting(this.getClass().getName(), "getCodigoImplementacion");
        return Long.valueOf(0);
    }

    private Long getCodigoLoteImplementacion() {
        LOGGER.entering(this.getClass().getName(), "getCodigoLoteImplementacion");
        ImplementacionPCTBean implementacionPCTBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression(ImplementacionPCTBean.BEAN_EXPRESSION);
        if (null != implementacionPCTBean.getIdLote() && implementacionPCTBean.getIdLote().trim().length() > 0) {
            LOGGER.exiting(this.getClass().getName(), "getCodigoLoteImplementacion");
            return Long.parseLong(implementacionPCTBean.getIdLote());
        }
        LOGGER.log(ADFLogger.ERROR, "<No se pudo obtener el Codigo lote implementacion>");
        LOGGER.exiting(this.getClass().getName(), "getCodigoLoteImplementacion");
        return Long.valueOf(0);
    }

    private Long getLineaCreditoSeleccionada() {
        LOGGER.log(ADFLogger.WARNING, "Entrando en getLineaCreditoSeleccionada.");
        EnvioAlCobroBean envioAlCobroBean =
            (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");
        if (null != envioAlCobroBean.getIdLineaCreditoSeleccionada()) {
            LOGGER.log(ADFLogger.WARNING, "retorno LineaCreditoSeleccionada: " + envioAlCobroBean.getIdLineaCreditoSeleccionada());
            return envioAlCobroBean.getIdLineaCreditoSeleccionada();
        }
        LOGGER.log(ADFLogger.WARNING, "<No se pudo obtener el id linea seleccionada>");
        return 0L;
    }

    private String getResponsableOperacion() {
        LOGGER.entering(this.getClass().getName(), "getLoginUsuario");
        ImplementacionPCTBean implementacionPCTBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression(ImplementacionPCTBean.BEAN_EXPRESSION);
        if (null != implementacionPCTBean.getResponsableOperacion() &&
            implementacionPCTBean.getResponsableOperacion().trim().length() > 0) {
            LOGGER.exiting(this.getClass().getName(), "getLoginUsuario");
            return implementacionPCTBean.getResponsableOperacion();
        }
        LOGGER.log(ADFLogger.ERROR, "<No se pudo obtener el responsable de la operacion>");
        LOGGER.exiting(this.getClass().getName(), "getLoginUsuario");
        return null;
    }

    public void setTreeTable(RichTreeTable treeTable) {
        this.treeTable = treeTable;
    }

    public RichTreeTable getTreeTable() {
        return treeTable;
    }

    public String invocarRealizarAjustes() {
        LOGGER.entering(this.getClass().getName(), "invocarRealizarAjustes");

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean isValidRealizarAjustes = Boolean.FALSE;
        Boolean existeDocumento = Boolean.FALSE;
        Boolean existeComentario = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {
        case FenixConstants.PA11_GESTIONAR_CONTRATACION_CONSULTORIA:
            existeComentario =
                validateComentario(getCodigoOperacion(), String.valueOf(getCodigoTarea()), getInstanciaTarea());
            if (!existeComentario) {
                bundleKeyErrorList.add("MSG_04_GPC_VALIDACION");
            }
            if (existeComentario == true) {
                isValidRealizarAjustes = Boolean.TRUE;
            }
            break;
        case FenixConstants.PA11_ELABORAR_DOCUMENTO_BASE:
            existeComentario =
                validateComentario(getCodigoOperacion(), String.valueOf(getCodigoTarea()), getInstanciaTarea());
            if (!existeComentario) {
                bundleKeyErrorList.add("MSG_03_EDB_VALIDACION");
            }
            if (existeComentario == true) {
                isValidRealizarAjustes = Boolean.TRUE;
            }
            break;
        case FenixConstants.PA11_VALIDAR_DOCUMENTO_BASE:
            existeComentario =
                validateComentario(getCodigoOperacion(), String.valueOf(getCodigoTarea()), getInstanciaTarea());
            if (!existeComentario) {
                bundleKeyErrorList.add("MSG_03_VDB_VALIDACION");
            }
            if (existeComentario == true) {
                isValidRealizarAjustes = Boolean.TRUE;
            }
            break;
        case FenixConstants.PA11_VALIDAR_DATOS_LINEA_CREDITO:
            LOGGER.fine("PA11_VALIDAR_DATOS_LINEA_CREDITO");
            LOGGER.warning("Validar datos de linea de credito.");
            existeComentario =
                validateComentario(getCodigoOperacion(), String.valueOf(getCodigoTarea()), getInstanciaTarea());
            if (!existeComentario) {
                bundleKeyErrorList.add("MSG_02_VDLC_MAS_INFORMACION");
            } else {
                isValidRealizarAjustes = Boolean.TRUE;
            }
            break;
        case FenixConstants.PA11_INICIAR_ADQUISICION:
            existeComentario =
                    validateComentario(getCodigoOperacion(), String.valueOf(getCodigoTarea()), getInstanciaTarea());
            if (!existeComentario) {
                bundleKeyErrorList.add("MSG_03_VDB_VALIDACION");
            }
            if (existeComentario == true) {
                isValidRealizarAjustes = Boolean.TRUE;
            }
            break;
        default:
            LOGGER.log(ADFLogger.ERROR, "invocarRealizarAjustes(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        if (!isValidRealizarAjustes) {
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        } else {
            realizarAjustesPopup();
        }
        LOGGER.exiting(this.getClass().getName(), "invocarRealizarAjustes");
        return null;
    }

    public void realizarAjustesPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupRealizarAjustes.show(hints);
    }

    public String aceptarRealizarAjustes() {
        LOGGER.entering(this.getClass().getName(), "aceptarRealizarAjustes");
        popupRealizarAjustes.hide();
        cargarDocumentosOnBase();
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {
        case FenixConstants.PA11_GESTIONAR_CONTRATACION_CONSULTORIA:
            actualizarObservacion();
            break;
        case FenixConstants.PA11_ELABORAR_DOCUMENTO_BASE:
            break;
        case FenixConstants.PA11_VALIDAR_DOCUMENTO_BASE:
            break;
        case FenixConstants.PA11_VALIDAR_DATOS_LINEA_CREDITO:
            break;
        case FenixConstants.PA11_INICIAR_ADQUISICION:
            break;
        default:
            LOGGER.log(ADFLogger.ERROR, "aceptarRealizarAjustes(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        if (codigoTarea != FenixConstants.PA11_VALIDAR_DATOS_LINEA_CREDITO) {
            LOGGER.warning("No se actualiza idImplementacion.");
            try {
                idImplementacion = getCodigoImplementacion();
                LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion actualizar payload." + idImplementacion);
                AttributeBinding attributeBindingIdImplementacion = null;
                attributeBindingIdImplementacion = ADFUtils.findControlBinding("IdImplementacion");
                actualizarPayloadElement("IdImplementacion", idImplementacion);
                LOGGER.severe("Revisar Valor del payload en true Despues de insertar." +
                              attributeBindingIdImplementacion.getInputValue());
            } catch (Exception e) {
                LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion actualizar payload es null." + e);
            }
        }

        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue(INVOKE_ACTION_BEAN_NAME);
        LOGGER.exiting(this.getClass().getName(), "aceptarRealizarAjustes");
        return invokeActionBean.invokeOperation();
    }

    public void setPopupRealizarAjustes(RichPopup popupRealizarAjustes) {
        this.popupRealizarAjustes = popupRealizarAjustes;
    }

    public RichPopup getPopupRealizarAjustes() {
        return popupRealizarAjustes;
    }

    public String cancelarRealizarAjustes() {
        popupRealizarAjustes.hide();
        return null;
    }

    public static void setBanderaElmentosVacios(Integer banderaElmentosVacios) {
        ImplementacionPCTActionBean.banderaElementosVacios = banderaElmentosVacios;
    }

    public static Integer getBanderaElmentosVacios() {
        return banderaElementosVacios;
    }

    public static void setBanderaMontoPresupuestado(Integer banderaMontoPresupuestado) {
        ImplementacionPCTActionBean.banderaMontoPresupuestado = banderaMontoPresupuestado;
    }

    public static Integer getBanderaMontoPresupuestado() {
        return banderaMontoPresupuestado;
    }

    public void setValidaInformacionImplementacion(RichRegion validaInformacionImplementacion) {
        this.validaInformacionImplementacion = validaInformacionImplementacion;
    }

    public RichRegion getValidaInformacionImplementacion() {
        return validaInformacionImplementacion;
    }

    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (resourceBundle != null) {
            txt = resourceBundle.getString(psKey);
        }

        return txt;
    }

    private void obtenerBanderasValidacion() {
        LOGGER.log(ADFLogger.WARNING, "INTO obtenerBanderasValidacion.");

        Map<String, Object> valoresInsertarImplementacion = new HashMap<String, Object>();
        int codigoTarea = getCodigoTarea();

        try {
            Long idOp = getCodigoOperacion();
            LOGGER.log(ADFLogger.WARNING, "Valor id Operacion." + idOp);

            switch (codigoTarea) {
            case FenixConstants.PA11_SOLICITAR_CONTRATACION_CONSULTORIA:
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBindingValidar =
                    bindings.getOperationBinding("validarFormularioImplementacion");
                operationBindingValidar.execute();
                valoresInsertarImplementacion = (HashMap<String, Object>) operationBindingValidar.getResult();
                break;
            case FenixConstants.PA11_GESTIONAR_CONTRATACION_CONSULTORIA:
                BindingContainer bindingsObservacion = ADFUtils.getBindingContainer();
                OperationBinding operationBindingValidarObservacion =
                    bindingsObservacion.getOperationBinding("validarFormularioImplementacionObservacion");
                operationBindingValidarObservacion.execute();
                valoresInsertarImplementacion =
                    (HashMap<String, Object>) operationBindingValidarObservacion.getResult();
                break;
            default:
                LOGGER.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
                break;
            }

            banderaElementosVacios =
                Integer.parseInt(valoresInsertarImplementacion.get("banderaElementosVacios").toString());
            banderaMontoPresupuestado =
                Integer.parseInt(valoresInsertarImplementacion.get("banderaMontoPresupuestado").toString());

            if (null != banderaElementosVacios) {
                LOGGER.log(ADFLogger.WARNING, "Valor banderaElmentosVacios." + banderaElementosVacios);
                LOGGER.log(ADFLogger.WARNING, "Valor banderaMontoPresupuestado." + banderaMontoPresupuestado);
            } else {
                LOGGER.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en validacionesFormulario." + e.getClass() + "." + e.getMessage());
        }
    }

    private Boolean insertarDatosFormulario() {
        LOGGER.entering(this.getClass().getName(), "insertarDatosFormulario");
        Boolean validaInsercionDatos = Boolean.FALSE;

        BindingContainer bindings = ADFUtils.getBindingContainer();
        Long idOp = getCodigoOperacion();
        LOGGER.log(ADFLogger.WARNING, "Valor id Operacion." + idOp);

        try {
            OperationBinding operationBindingInsertar =
                bindings.getOperationBinding("insertarFormularioImplementacion");
            operationBindingInsertar.getParamsMap().put("idOperacion", idOp);
            operationBindingInsertar.execute();
            idImplementacion = Long.parseLong(operationBindingInsertar.getResult().toString());
            LOGGER.log(ADFLogger.WARNING, "Valor idImplementacion recuperado." + idImplementacion);
            validaInsercionDatos = Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en insertarDatosFormulario." + e.getClass() + "." + e.getMessage());
        }
        return validaInsercionDatos;
    }

    private Boolean actualizarDatosFormulario() {
        LOGGER.entering(this.getClass().getName(), "actualizarDatosFormulario");

        Boolean validaActualizarDatos = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();

        try {
            OperationBinding operationBindingActualizar =
                bindings.getOperationBinding("actualizarFormularioImplementacionDatosInicio");
            operationBindingActualizar.execute();
            validaActualizarDatos = Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en actualizarDatosFormulario." + e.getClass() + "." + e.getMessage());
        }

        return validaActualizarDatos;
    }

    private Boolean actualizarObservacion() {
        LOGGER.entering(this.getClass().getName(), "actualizarObservacion");

        Boolean validaActualizarDatos = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();

        try {
            OperationBinding operationBindingActualizar =
                bindings.getOperationBinding("actualizarFormularioImplementacionObservacion");
            operationBindingActualizar.execute();
            validaActualizarDatos = Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en actualizarDatosFormulario." + e.getClass() + "." + e.getMessage());
        }

        return validaActualizarDatos;
    }

    private void obtenerBanderaValidacionAdquisicion() {
        LOGGER.log(ADFLogger.WARNING, "INTO obtenerBanderaValidacionAdquisicion.");
        Boolean resultadoBandera = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();

        try {
            Long idOp = getCodigoOperacion();
            LOGGER.log(ADFLogger.WARNING, "Valor id Operacion." + idOp);

            switch (codigoTarea) {
            case FenixConstants.PA11_INICIAR_ADQUISICION:
                BindingContainer bindingsIniciarAdquision = ADFUtils.getBindingContainer();
                OperationBinding operationBindingValidarInicioAdquisicion =
                    bindingsIniciarAdquision.getOperationBinding("validarFormularioImplementacionIniciarAdquisicion");
                operationBindingValidarInicioAdquisicion.execute();
                resultadoBandera = (Boolean) operationBindingValidarInicioAdquisicion.getResult();
                LOGGER.log(ADFLogger.WARNING, "Valor resultado bandera Iniciar Adquisicion." + resultadoBandera);
                break;
            case FenixConstants.PA11_REGISTRAR_RESULTADO_ADQUISICION:
                BindingContainer bindingsRegistrarResultadoAdquision = ADFUtils.getBindingContainer();
                OperationBinding operationBindingValidarRegistrarResultadoAdquision =
                    bindingsRegistrarResultadoAdquision.getOperationBinding("validarFormularioImplementacionRegistrarResultadoAdquisicion");
                operationBindingValidarRegistrarResultadoAdquision.execute();
                resultadoBandera = (Boolean) operationBindingValidarRegistrarResultadoAdquision.getResult();
                LOGGER.log(ADFLogger.WARNING,
                           "Valor resultado bandera Registrar resultado adquiscion." + resultadoBandera);
                break;
            default:
                LOGGER.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
                break;
            }

            if (null != resultadoBandera) {
                LOGGER.log(ADFLogger.WARNING, "Valor banderaElmentosVacios." + resultadoBandera);
                if (resultadoBandera == true)
                    banderaElementosVacios = 1;
                else
                    banderaElementosVacios = 0;
            } else {
                LOGGER.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en validacionesFormulario." + e.getClass() + "." + e.getMessage());
        }
    }

    private Boolean actualizarInicioAdquisicion() {
        LOGGER.entering(this.getClass().getName(), "actualizarInicioAdquisicion");

        Boolean validaActualizarDatos = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();

        try {
            OperationBinding operationBindingActualizar =
                bindings.getOperationBinding("actualizarFormularioImplementacionInicioAdquisicion");
            operationBindingActualizar.execute();
            validaActualizarDatos = Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING, "Error en actualizarDatosFormulario." + e);
        }

        return validaActualizarDatos;
    }

    private Boolean actualizarRegistrarResultadoAdquisicion(Long idLoteImplementacion) {
        LOGGER.entering(this.getClass().getName(), "actualizarRegistrarResultadoAdquisicion");

        Boolean validaActualizarDatos = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();

        try {
            OperationBinding operationBindingActualizar =
                bindings.getOperationBinding("actualizarFormularioImplementacionRegistrarResultadoAdquisicion");
            operationBindingActualizar.getParamsMap().put("idLoteImplementacion", idLoteImplementacion);
            operationBindingActualizar.execute();
            validaActualizarDatos = Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en actualizarDatosFormulario." + e.getClass() + "." + e.getMessage());
        }

        return validaActualizarDatos;
    }

    private Boolean obtenerBanderaValidacionRequiereLotes() {
        LOGGER.log(ADFLogger.WARNING, "INTO obtenerBanderaValidacionRequiereLotes.");
        Boolean resultadoBandera = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();

        try {
            Long idOp = getCodigoOperacion();
            LOGGER.log(ADFLogger.WARNING, "Valor id Operacion." + idOp);

            switch (codigoTarea) {
            case FenixConstants.PA11_INICIAR_ADQUISICION:
                BindingContainer bindingsIniciarAdquision = ADFUtils.getBindingContainer();
                OperationBinding operationBindingValidarInicioAdquisicion =
                    bindingsIniciarAdquision.getOperationBinding("validarFormularioImplementacionRequiereLotes");
                operationBindingValidarInicioAdquisicion.execute();
                resultadoBandera = (Boolean) operationBindingValidarInicioAdquisicion.getResult();
                LOGGER.log(ADFLogger.WARNING, "Valor resultado bandera." + resultadoBandera);
                break;
            default:
                LOGGER.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
                break;
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR,
                       "Error en validacionesFormularioRequiereLotes." + e.getClass() + "." + e.getMessage());
        }
        return resultadoBandera;
    }

    private Boolean obtenerBanderaContadorImplementacionPCT() {
        LOGGER.log(ADFLogger.WARNING, "INTO obtenerBanderaContadorImplementacionPCT.");
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        Boolean resultadoBandera = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();
        Long idImplementacion = getCodigoImplementacion();
        
        try {
            Long idOp = getCodigoOperacion();
            LOGGER.log(ADFLogger.WARNING, "Valor id Operacion." + idOp);

            switch (codigoTarea) {
            case FenixConstants.PA11_INICIAR_ADQUISICION:
                BindingContainer bindingsIniciarAdquision = ADFUtils.getBindingContainer();
                OperationBinding operationBindingValidarContadorLotes =
                    bindingsIniciarAdquision.getOperationBinding("obtenerLotesByIdImplementacion");
                operationBindingValidarContadorLotes.getParamsMap().put("idImplementacion", idImplementacion);
                operationBindingValidarContadorLotes.execute();
                resultadoBandera = (Boolean) operationBindingValidarContadorLotes.getResult();
                LOGGER.log(ADFLogger.WARNING, "Valor resultado bandera." + resultadoBandera);
                break;
            case FenixConstants.PA11_REGISTRAR_RESULTADO_ADQUISICION:
                Long idLoteImplementacion = getCodigoLoteImplementacion();
                Boolean resultadoBanderaParticipantes = Boolean.FALSE;
                BindingContainer bindingsRegResAdquision = ADFUtils.getBindingContainer();
                OperationBinding operationBindingValidarContadorParticipante =
                    bindingsRegResAdquision.getOperationBinding("obtenerParticipantesByIdLoteImplementacion");
                operationBindingValidarContadorParticipante.getParamsMap().put("idLoteImplementacion",
                                                                               idLoteImplementacion);
                operationBindingValidarContadorParticipante.execute();
                resultadoBanderaParticipantes = (Boolean) operationBindingValidarContadorParticipante.getResult();
                LOGGER.log(ADFLogger.WARNING, "Valor resultado bandera." + resultadoBandera);

                Boolean resultadoBanderaAdjudicatarios = Boolean.FALSE;
                bindingsRegResAdquision = ADFUtils.getBindingContainer();
                OperationBinding operationBindingValidarContadorAdjudicatario =
                    bindingsRegResAdquision.getOperationBinding("obtenerAdjudicatariosByIdLoteImplementacion");
                operationBindingValidarContadorAdjudicatario.getParamsMap().put("idLoteImplementacion",
                                                                                idLoteImplementacion);
                operationBindingValidarContadorAdjudicatario.execute();
                resultadoBanderaAdjudicatarios = (Boolean) operationBindingValidarContadorAdjudicatario.getResult();
                LOGGER.log(ADFLogger.WARNING, "Valor resultado bandera." + resultadoBandera);

                Integer registrarResultadoAdquisicion = implementacionBean.getRegistrarResultadoAdquisicion();
                if (registrarResultadoAdquisicion == ADJUDICADO) {
                    if (resultadoBanderaParticipantes == true && resultadoBanderaAdjudicatarios == true) {
                        resultadoBandera = Boolean.TRUE;
                    } else {
                        resultadoBandera = Boolean.FALSE;
                    }
                } else if (registrarResultadoAdquisicion == ANULADO || registrarResultadoAdquisicion == DESIERTO ||
                           registrarResultadoAdquisicion == FRACASADO || registrarResultadoAdquisicion == NUEVO) {
                    resultadoBandera = Boolean.TRUE;
                }
                break;
            default:
                LOGGER.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
                break;
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR,
                       "Error en validacionesFormularioRequiereLotes." + e.getClass() + "." + e.getMessage());
        }
        return resultadoBandera;
    }

    private Boolean obtenerBanderaSumaLotes() {
        LOGGER.log(ADFLogger.WARNING, "INTO obtenerBanderaSumaLotes.");
        Boolean resultadoBandera = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();
        Long idImplementacion = getCodigoImplementacion();
        BigDecimal sumaTotal = null;

        try {
            BindingContainer bindingsIniciarAdquision = ADFUtils.getBindingContainer();
            OperationBinding operationBindingValidarSumaLotes =
                bindingsIniciarAdquision.getOperationBinding("obtenerSumaMontosPresupuestadosByIdImplementacion");
            operationBindingValidarSumaLotes.getParamsMap().put("idImplementacion", idImplementacion);
            operationBindingValidarSumaLotes.execute();
            sumaTotal = (BigDecimal) operationBindingValidarSumaLotes.getResult();
            LOGGER.log(ADFLogger.WARNING, "Valor suma total montos presupuestados de todos los lotes." + sumaTotal);
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR,
                       "Error en obtenerSumaMontosPresupuestadosByIdImplementacion." + e.getClass() + "." +
                       e.getMessage());
        }
        BigDecimal montoPresupuestado = null;
        try {
            BindingContainer bindingsIniciarAdquision = ADFUtils.getBindingContainer();
            DCIteratorBinding iteratorBinding =
                (DCIteratorBinding) bindingsIniciarAdquision.get("FormularioImplementacionPctVOIterator");
            if (iteratorBinding != null) {
                Row currentRow = iteratorBinding.getCurrentRow();
                if (currentRow != null) {
                    montoPresupuestado = (BigDecimal) currentRow.getAttribute("MontoPresupuestado");
                }
            }
            LOGGER.warning("El valor del monto presupuestado es: " + montoPresupuestado);
        } catch (Exception e) {
            LOGGER.severe("Error al obtener MontoPresupuestado.", e);
            LOGGER.warning("Excepcion al obtener MontoPresupuestado." + e.getClass(), ".", e.getMessage());
        }
        try {
            Integer resultado = sumaTotal.compareTo(montoPresupuestado);
            if (resultado == 1)
                resultadoBandera = Boolean.FALSE;
            else if (resultado == -1)
                resultadoBandera = Boolean.TRUE;
            else if (resultado == 0)
                resultadoBandera = Boolean.TRUE;
            else
                resultadoBandera = Boolean.FALSE;
        } catch (Exception e) {
            LOGGER.severe("Error al obtener idLote.", e);
            LOGGER.warning("Excepcion al obtener idLote." + e.getClass(), ".", e.getMessage());
        }
        return resultadoBandera;
    }

    public void seleccionarRegistro(SelectionEvent selectionEvent) {
        LOGGER.warning("Entra a seleccionarRegistro");
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        invokeTable("#{bindings.FormularioLoteImplementacionVO.collectionModel.makeCurrent}", new Class[] {
                    SelectionEvent.class }, new Object[] { selectionEvent });
        Row selectedRow = (Row) evaluateTable("#{bindings.FormularioLoteImplementacionVOIterator.currentRow}");
        try {
            LOGGER.warning("Valor idLote seleccionado: " + selectedRow.getAttribute("Id").toString());
            LOGGER.warning("Valor nombreLote seleccionado: " + selectedRow.getAttribute("NombreLote").toString());
            LOGGER.warning("Valor montoPresupuestado seleccionado: " +
                           selectedRow.getAttribute("MontoPresupuestado").toString());
            LOGGER.warning("Valor idTcaTipoMoneda seleccionado: " +
                           selectedRow.getAttribute("IdTcaTipoMoneda").toString());

            implementacionBean.setHabilitaBotonAgregar(Boolean.TRUE);
            implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.FALSE);
            implementacionBean.setSoloLectura(Boolean.FALSE);

            idLoteObtenido = Long.parseLong(selectedRow.getAttribute("Id").toString());
            implementacionBean.setVarIdLoteReadOnly(idLoteObtenido);
        } catch (Exception e) {
            LOGGER.warning("El row seleccionarRegistro es nuevo con valor null.");
            idLoteObtenido = Long.valueOf(0);
            implementacionBean.setVarIdLoteReadOnly(null);
        }
    }

    public String agregarLoteAction() {
        LOGGER.warning("INTO agregarLoteAction");
        List<String> bundleKeyErrorList = new ArrayList<String>();
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try {
            operationBinding = bindings.getOperationBinding("crearRowFormularioLoteImplementacionInsertarLote");
            operationBinding.execute();
            implementacionBean.setHabilitaBotonAgregar(Boolean.TRUE);
            implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.FALSE);
            LOGGER.warning("El row se creo correctamente.");
        } catch (Exception e) {
            LOGGER.severe("Error al crear un row.", e);
            LOGGER.warning("Excepcion al crear row." + e.getClass(), ".", e.getMessage());
        }
        Integer idMoneda = null;
        try {
            DCIteratorBinding iteratorBinding =
                (DCIteratorBinding) bindings.get("FormularioImplementacionPctVOIterator");
            if (iteratorBinding != null) {
                Row currentRow = iteratorBinding.getCurrentRow();
                if (currentRow != null) {
                    idMoneda = (Integer) currentRow.getAttribute("IdTcaTipoMoneda");
                }
            }
            LOGGER.warning("El valor seleccionado de la moneda es: " + idMoneda);
        } catch (Exception e) {
            LOGGER.severe("Error al obtener idLote.", e);
            LOGGER.warning("Excepcion al obtener idLote." + e.getClass(), ".", e.getMessage());
        }
        try {
            DCIteratorBinding iteratorBinding =
                (DCIteratorBinding) bindings.get("FormularioLoteImplementacionVOIterator");
            if (iteratorBinding != null) {
                Row currentRow = iteratorBinding.getCurrentRow();
                if (currentRow != null) {
                    currentRow.setAttribute("IdTcaTipoMoneda", idMoneda);
                }
            }
        } catch (Exception e) {
            LOGGER.severe("Error al ingresar valor idTcaTipoMoneda en FormularioLoteImplementacionVO.", e);
            LOGGER.warning("Excepcion al ingresar idTcaTipoMoneda." + e.getClass(), ".", e.getMessage());
        }

        implementacionBean.setVarIdLoteReadOnly(null);

        //RowKeySet rowSet = getTable().getSelectedRowKeys();
        //rowSet.clear();
        //AdfFacesContext.getCurrentInstance().addPartialTarget(table);

        return null;
    }

    public String guardarLoteAction() {
        LOGGER.warning("Entra en guardarLoteAction.");
        List<String> bundleKeyErrorList = new ArrayList<String>();
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long idImplementacion = null;

        Integer idMoneda = null;
        Boolean esValidoMontoPresupuestado = null;
        try {
            operationBinding = bindings.getOperationBinding("validarMontoPresupuestado");
            operationBinding.execute();
            esValidoMontoPresupuestado = (Boolean) operationBinding.getResult();
            LOGGER.warning("esValidoMontoPresupuestado : " + esValidoMontoPresupuestado);
        } catch (Exception e) {
            LOGGER.severe("Error en ejecutar validarMontoPresupuestado", e);
        }
//        try {
//            DCIteratorBinding iteratorBinding =
//                (DCIteratorBinding) bindings.get("FormularioImplementacionPctVOIterator");
//            if (iteratorBinding != null) {
//                Row currentRow = iteratorBinding.getCurrentRow();
//                if (currentRow != null) {
//                    idMoneda = (Integer) currentRow.getAttribute("IdTcaTipoMoneda");
//                }
//            }
//            LOGGER.warning("El valor seleccionado de la moneda es: " + idMoneda);
//        } catch (Exception e) {
//            LOGGER.severe("Error al obtener idLote.", e);
//            LOGGER.warning("Excepcion al obtener idLote." + e.getClass(), ".", e.getMessage());
//        }
//        try {
//            DCIteratorBinding iteratorBinding =
//                (DCIteratorBinding) bindings.get("FormularioLoteImplementacionVOIterator");
//            if (iteratorBinding != null) {
//                Row currentRow = iteratorBinding.getCurrentRow();
//                if (currentRow != null) {
//                    currentRow.setAttribute("IdTcaTipoMoneda", idMoneda);
//                }
//            }
//        } catch (Exception e) {
//            LOGGER.severe("Error al ingresar valor idTcaTipoMoneda en FormularioLoteImplementacionVO.", e);
//            LOGGER.warning("Excepcion al ingresar idTcaTipoMoneda." + e.getClass(), ".", e.getMessage());
//        }
        
        if(esValidoMontoPresupuestado){
        Boolean banderaValidarCampos = Boolean.FALSE;
        try {
            operationBinding = bindings.getOperationBinding("validarFormularioLoteImplementacionInsertarLote");
            operationBinding.execute();
            banderaValidarCampos = (Boolean) operationBinding.getResult();
            LOGGER.warning("El valor de banderaValidarCampos de Lote Implementacion es: " + banderaValidarCampos);
        } catch (Exception e) {
            LOGGER.severe("Error en banderaValidarCampos.", e);
            LOGGER.warning("Excepcion en banderaValidarCampos." + e.getClass(), ".", e.getMessage());
        }
        try {
            if (banderaValidarCampos == true) {
                try {
                    if (idLoteObtenido == 0) {
                        idImplementacion = getCodigoImplementacion();
                        operationBinding = bindings.getOperationBinding("agregarLote");
                        operationBinding.getParamsMap().put("idImplementacion", idImplementacion);
                        operationBinding.execute();
                        implementacionBean.setHabilitaBotonAgregar(Boolean.FALSE);
                        implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);
                        LOGGER.warning("El row se creo correctamente.");
                        idLoteObtenido = Long.valueOf(0);

                        implementacionBean.setVarIdLoteReadOnly(null);
                        
                        try {
                            BindingContainer binding = ADFUtils.getBindingContainer();
                            operationBinding = binding.getOperationBinding("limpiarFormLoteImplementacion");
                            operationBinding.execute();

                            if (!operationBinding.getErrors().isEmpty()) {
                                LOGGER.warning("OperationBinding devuelve errores metodo limpiarFormLoteImplementacion");
                            } else {
                                LOGGER.warning("***Carga Lotes Listo, redireccionando... ");
                            }
                        } catch (Exception e) {
                            LOGGER.log(ADFLogger.ERROR, "Error en guardarLoteAction " + e);
                        }
                        
                        try {
                            BindingContainer binding = ADFUtils.getBindingContainer();
                            operationBinding = binding.getOperationBinding("setValoresInicializarLotes");
                            operationBinding.getParamsMap().put("idImplementacion", getCodigoImplementacion());
                            operationBinding.execute();

                            if (!operationBinding.getErrors().isEmpty()) {
                                LOGGER.warning("OperationBinding devuelve errores metodo setValoresInicializarLotes");
                            } else {
                                LOGGER.warning("***Carga Lotes Listo, redireccionando... ");
                            }
                        } catch (Exception e) {
                            LOGGER.log(ADFLogger.ERROR, "Error en guardarLoteAction " + e);
                        }

                        //RowKeySet rowSet = getTable().getSelectedRowKeys();
                        //rowSet.clear();
                        //AdfFacesContext.getCurrentInstance().addPartialTarget(table);

                    } else {
                        operationBinding = bindings.getOperationBinding("actualizarLoteImplementacionAM");
                        operationBinding.execute();
                        implementacionBean.setHabilitaBotonAgregar(Boolean.FALSE);
                        implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);
                        LOGGER.warning("El row se actualizo correctamente.");
                        idLoteObtenido = Long.valueOf(0);

                        implementacionBean.setVarIdLoteReadOnly(null);

                        try {
                            BindingContainer binding = ADFUtils.getBindingContainer();
                            operationBinding = binding.getOperationBinding("limpiarFormLoteImplementacion");
                            operationBinding.execute();

                            if (!operationBinding.getErrors().isEmpty()) {
                                LOGGER.warning("OperationBinding devuelve errores metodo limpiarFormLoteImplementacion");
                            } else {
                                LOGGER.warning("***Carga Lotes Listo, redireccionando... ");
                            }
                        } catch (Exception e) {
                            LOGGER.log(ADFLogger.ERROR, "Error en guardarLoteAction " + e);
                        }
                        
                        try {
                            BindingContainer binding = ADFUtils.getBindingContainer();
                            operationBinding = binding.getOperationBinding("setValoresInicializarLotes");
                            operationBinding.getParamsMap().put("idImplementacion", getCodigoImplementacion());
                            operationBinding.execute();

                            if (!operationBinding.getErrors().isEmpty()) {
                                LOGGER.warning("OperationBinding devuelve errores metodo setValoresInicializarLotes");
                            } else {
                                LOGGER.warning("***Carga Lotes Listo, redireccionando... ");
                            }
                        } catch (Exception e) {
                            LOGGER.log(ADFLogger.ERROR, "Error en guardarLoteAction " + e);
                        }
                        
                        //RowKeySet rowSet = getTable().getSelectedRowKeys();
                        //rowSet.clear();
                        //AdfFacesContext.getCurrentInstance().addPartialTarget(table);

                    }
                } catch (Exception e) {
                    LOGGER.log(ADFLogger.WARNING,
                               "Error al guardar datos lote implementacion." + e.getClass() + "." + e);
                }
            } else {
                /*bundleKeyErrorList.add("MSG_ERROR_FALTAN_CAMPOS_LOTE_IMPLEMENTACION");
                if (bundleKeyErrorList.size() > 0) {
                    for (String bundleKey : bundleKeyErrorList)
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
                }*/
                LOGGER.log(ADFLogger.WARNING, "NombreLote, MontoPresupuestado y IdTcaTipoMoneda no pueden ser null");
            }
        } catch (Exception e) {
            LOGGER.severe("Error en agregarLoteAction.", e);
            LOGGER.warning("Excepcion al ingresar en agregarLoteAction." + e.getClass(), ".", e.getMessage());
        }

        implementacionBean.setVarIdLoteReadOnly(null);

        RowKeySet rowSet = getTable().getSelectedRowKeys();
        rowSet.clear();
        //AdfFacesContext.getCurrentInstance().addPartialTarget(table);

        idLoteObtenido = Long.valueOf(0);
        }else{
            LOGGER.warning("montoPresupuestado es nulo");
            bundleKeyErrorList.add("MSG_ERROR_MONTO_PRESUPUESTADO");
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList){
                    LOGGER.warning(bundleKey);
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
                }
            }
        }

        return null;
    }

    public String eliminarLoteAction() {
        LOGGER.warning("Entra en eliminarLoteAction.");
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        try {
            LOGGER.warning(" Valor idLoteObtenido es: " + idLoteObtenido);
            if (idLoteObtenido == 0) {
                LOGGER.warning("Ingresa al if");
                operationBinding = bindings.getOperationBinding("eliminarRowFormularioLoteImplementacion");
                operationBinding.execute();
                implementacionBean.setHabilitaBotonAgregar(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);
                LOGGER.warning("El row nuevo se elimino correctamente.");
                idLoteObtenido = Long.valueOf(0);

                implementacionBean.setVarIdLoteReadOnly(null);

                RowKeySet rowSet = getTable().getSelectedRowKeys();
                rowSet.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(table);
            } else {
                LOGGER.warning("Ingresa al else");
                operationBinding = bindings.getOperationBinding("eliminarLoteImplementacion");
                operationBinding.getParamsMap().put("idLoteImplementacion", idLoteObtenido);
                operationBinding.execute();
                operationBinding = bindings.getOperationBinding("eliminarRowFormularioLoteImplementacion");
                operationBinding.execute();
                implementacionBean.setHabilitaBotonAgregar(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);
                LOGGER.warning("El row existente  se elimino correctamente.");
                idLoteObtenido = Long.valueOf(0);

                implementacionBean.setVarIdLoteReadOnly(null);

                RowKeySet rowSet = getTable().getSelectedRowKeys();
                rowSet.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(table);
            }
        } catch (Exception e) {
            LOGGER.severe("Error al eliminar Lote.", e);
            LOGGER.warning("Excepcion al eliminar Lote." + e.getClass(), ".", e.getMessage());
        }

        popupEliminarLote.hide();

        return null;
    }

    public void seleccionarRegistroParticipante(SelectionEvent selectionEvent) {
        LOGGER.warning("Entra a seleccionarRegistroParticipante");
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        invokeTable("#{bindings.ImplementacionParticipanteVO.collectionModel.makeCurrent}", new Class[] {
                    SelectionEvent.class }, new Object[] { selectionEvent });
        Row selectedRow = (Row) evaluateTable("#{bindings.ImplementacionParticipanteVOIterator.currentRow}");
        try {
            LOGGER.warning("Valor idParticipante seleccionado: " + selectedRow.getAttribute("Id"));
            LOGGER.warning("Valor nombreParticipante seleccionado: " +
                           selectedRow.getAttribute("NombreParticipante"));
            LOGGER.warning("Valor idCatPaisParticipante seleccionado: " +
                           selectedRow.getAttribute("IdCatPaisParticipante"));

            implementacionBean.setHabilitaBotonAgregar(Boolean.TRUE);
            implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.FALSE);
            //implementacionBean.setSoloLectura(Boolean.FALSE);
            implementacionBean.setHabilitaBotonAgregarAdjudicatario(Boolean.FALSE);
            implementacionBean.setHabilitaBotonGuardarEliminarAdjudicatario(Boolean.TRUE);

            if (null != selectedRow.getAttribute("Id")) {
                idParticipanteObtenido = Long.parseLong(selectedRow.getAttribute("Id").toString());
            } else {
                idParticipanteObtenido = ID_NUEVO_REGISTRO;
            }
//            
//            if (null != selectedRow.getAttribute("Id")) {
//                idAdjudicatarioObtenido = Long.parseLong(selectedRow.getAttribute("Id").toString());
//            } else {
//                idAdjudicatarioObtenido = ID_NUEVO_REGISTRO;
//            }
//            
//            implementacionBean.setVarIdParticipanteReadOnly(idParticipanteObtenido);
//            implementacionBean.setVarIdAdjudicatarioReadOnly(idAdjudicatarioObtenido);
//            
            LOGGER.warning("idParticipanteObtenido: " + idParticipanteObtenido);
            LOGGER.warning("idAdjudicatarioObtenido: " + idAdjudicatarioObtenido);
        } catch (Exception e) {
            LOGGER.warning("El row seleccionarRegistroParticipante es nuevo con valor null.", e);
        }
    }

    public String agregarParticipanteAction() {
        LOGGER.warning("INTO agregarParticipanteAction");
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try {
            operationBinding = bindings.getOperationBinding("crearRowParticipante");
            operationBinding.execute();
            implementacionBean.setHabilitaBotonAgregar(Boolean.TRUE);
            implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.FALSE);
            implementacionBean.setSoloLectura(Boolean.FALSE);
            LOGGER.warning("El row se creo correctamente.");
        } catch (Exception e) {
            LOGGER.severe("Error al crear un row.", e);
            LOGGER.warning("Excepcion al crear row." + e.getClass(), ".", e.getMessage());
        }
        //implementacionBean.setHabilitaBotonAgregarAdjudicatario(Boolean.TRUE);
        implementacionBean.setHabilitaBotonGuardarEliminarAdjudicatario(Boolean.TRUE);
        RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
        rowSet.clear();
        
        setParticipanteActiveRowKey();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);
        return null;
    }

    @SuppressWarnings("unchecked")
    private void setParticipanteActiveRowKey() {
        DCIteratorBinding it = ADFUtils.findIterator("ImplementacionParticipanteVOIterator");
        RowSetIterator rsi = it.getRowSetIterator();
        RowKeySet oldSelection = tableParticiante.getSelectedRowKeys();
        RowKeySetImpl newSelection = new RowKeySetImpl(); 
        ArrayList lst = new ArrayList(1);
        if (rsi.first() != null) {
            Row r = rsi.first();
            Key key = r.getKey();
            lst.add(key);
            tableParticiante.setActiveRowKey(lst);  
            newSelection.add(lst); 
            setParticipanteMakeCurrent(newSelection, oldSelection);
        }
    }
    
    private void setParticipanteMakeCurrent(RowKeySet newCurrentRow, RowKeySet oldCurrentRow) {
        SelectionEvent selectionEvent = new SelectionEvent(oldCurrentRow, newCurrentRow, tableParticiante);
        selectionEvent.queue();
    }

    public String guardarParticipanteAction() {
        LOGGER.warning("Entra en guardarParticipanteAction.");
        List<String> bundleKeyErrorList = new ArrayList<String>();
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        Long idLote = null;
        String nombreParticipante = null;
        String nacionalidad = null;
        Boolean validaCampos = Boolean.TRUE;

        try {
            if (null != ADFUtils.getBoundAttributeValue("NombreParticipante") &&
                !"".equals(ADFUtils.getBoundAttributeValue("NombreParticipante").toString())) {
                nombreParticipante = (String) ADFUtils.getBoundAttributeValue("NombreParticipante");
                LOGGER.warning("El nombre del participante es : " + nombreParticipante);
            } else {
                LOGGER.warning("Nombre participante es nulo.");
                validaCampos = Boolean.FALSE;
                bundleKeyErrorList.add(getStringFromBundle("MSG_ERROR_NOMBRE_PARTICIPANTE"));
            }
            if (null != ADFUtils.getBoundAttributeValue("PaisDesc") &&
                !"".equals(ADFUtils.getBoundAttributeValue("PaisDesc").toString())) {
                nacionalidad = (String) ADFUtils.getBoundAttributeValue("PaisDesc");
                LOGGER.warning("La nacionalidad del participante es : " + nacionalidad);
            } else {
                LOGGER.warning("Nacionalidad participante es nulo.");
                validaCampos = Boolean.FALSE;
                bundleKeyErrorList.add(getStringFromBundle("MSG_ERROR_NACIONALIDAD_PARTICIPANTE"));
            }
            if(validaCampos){
            if (idParticipanteObtenido == 0) {
                idLote = Long.parseLong(implementacionBean.getIdLote());
                operationBinding = bindings.getOperationBinding("agregarParticipante");
                operationBinding.getParamsMap().put("idLote", idLote);
                operationBinding.execute();
                
                implementacionBean.setHabilitaBotonAgregar(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);
                implementacionBean.setHabilitaBotonAgregarAdjudicatario(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminarAdjudicatario(Boolean.TRUE);
                LOGGER.warning("El row se creo correctamente.");
                LOGGER.warning("Valor bandera boton agregar :" + implementacionBean.getHabilitaBotonAgregar());
                LOGGER.warning("Valor bandera boton Guardar Eliminar :" +
                               implementacionBean.getHabilitaBotonGuardarEliminar());
                LOGGER.warning("Valor bandera boton Guardar Eliminar adjudicatario :" +
                               implementacionBean.getHabilitaBotonGuardarEliminarAdjudicatario());
                idParticipanteObtenido = Long.valueOf(0);

                implementacionBean.setVarIdParticipanteReadOnly(null);
                RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
                rowSet.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);
                
            } else {
                operationBinding = bindings.getOperationBinding("actualizarConcursanteImplementacion");
                operationBinding.execute();
                implementacionBean.setHabilitaBotonAgregar(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);
                implementacionBean.setHabilitaBotonAgregarAdjudicatario(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminarAdjudicatario(Boolean.TRUE);
                LOGGER.warning("El row se actualizo correctamente.");
                idParticipanteObtenido = Long.valueOf(0);

                implementacionBean.setVarIdParticipanteReadOnly(null);
                RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
                rowSet.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);
            }
            }else{
              LOGGER.warning("Error, se identificaron valores nulos.");
                for(String mensaje : bundleKeyErrorList){
                    JSFUtils.addFacesErrorMessage(mensaje);
                }
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING,
                       "Error al guardar datos participante implementacion." + e.getClass() + "." + e);
        }

        implementacionBean.setVarIdParticipanteReadOnly(null);
        implementacionBean.setVarIdAdjudicatarioReadOnly(Long.parseLong("-1"));

        RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
        rowSet.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);

        RowKeySet rowSet2 = getTableAdjudicatario().getSelectedRowKeys();
        rowSet2.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableAdjudicatario);

        idParticipanteObtenido = Long.valueOf(0);
        idAdjudicatarioObtenido = Long.valueOf(0);

        return null;
    }

    public String eliminarParticipanteAction() {
        LOGGER.warning("Entra en eliminarParticipanteAction.");
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        try {
            LOGGER.warning(" Valor idParticipanteObtenido es: " + idParticipanteObtenido);
            if (idParticipanteObtenido == 0) {
                LOGGER.warning("Ingresa al if");
                operationBinding = bindings.getOperationBinding("eliminarRowImplementacionParticipante");
                operationBinding.execute();
                implementacionBean.setHabilitaBotonAgregar(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);
                implementacionBean.setHabilitaBotonAgregarAdjudicatario(Boolean.TRUE);
                implementacionBean.setHabilitaBotonGuardarEliminarAdjudicatario(Boolean.TRUE);
                LOGGER.warning("El row nuevo se elimino correctamente.");
                idParticipanteObtenido = Long.valueOf(0);

                implementacionBean.setVarIdParticipanteReadOnly(null);

                RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
                rowSet.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);
            } else {
                LOGGER.warning("Ingresa al else");
                operationBinding = bindings.getOperationBinding("eliminarConcursanteImplementacion");
                operationBinding.getParamsMap().put("id", idParticipanteObtenido);
                operationBinding.execute();
                operationBinding = bindings.getOperationBinding("eliminarRowImplementacionParticipante");
                operationBinding.execute();
                implementacionBean.setHabilitaBotonAgregar(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);
                //implementacionBean.setHabilitaBotonAgregarAdjudicatario(Boolean.TRUE);
                implementacionBean.setHabilitaBotonGuardarEliminarAdjudicatario(Boolean.TRUE);
                LOGGER.warning("El row existente  se elimino correctamente.");
                idParticipanteObtenido = Long.valueOf(0);

                implementacionBean.setVarIdParticipanteReadOnly(null);

                RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
                rowSet.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);
            }
        } catch (Exception e) {
            LOGGER.severe("Error al eliminar Participante.", e);
            LOGGER.warning("Excepcion al eliminar Participante." + e.getClass(), ".", e.getMessage());
        }

        popupEliminarParticipante.hide();
        implementacionBean.setVarIdParticipanteReadOnly(null);
        implementacionBean.setVarIdAdjudicatarioReadOnly(Long.parseLong("-1"));

        RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
        rowSet.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);

        RowKeySet rowSet2 = getTableAdjudicatario().getSelectedRowKeys();
        rowSet2.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableAdjudicatario);

        idParticipanteObtenido = Long.valueOf(0);
        idAdjudicatarioObtenido = Long.valueOf(0);
        return null;
    }

    public void seleccionarRegistroAdjudicatario(SelectionEvent selectionEvent) {
        LOGGER.warning("Entra a seleccionarRegistroAdjudicatario");
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        invokeTable("#{bindings.ImplementacionAdjudicatarioVO.collectionModel.makeCurrent}", new Class[] {
                    SelectionEvent.class }, new Object[] { selectionEvent });
        Row selectedRow = (Row) evaluateTable("#{bindings.ImplementacionAdjudicatarioVOIterator.currentRow}");
        try {
            LOGGER.warning("Valor idParticipante seleccionado: " + selectedRow.getAttribute("Id"));
            LOGGER.warning("Valor nombreParticipante seleccionado: " +
                           selectedRow.getAttribute("NombreParticipante"));
            LOGGER.warning("Valor idCatPaisParticipante seleccionado: " +
                           selectedRow.getAttribute("IdCatPais"));
            LOGGER.warning("Valor montoAdjudicado seleccionado: " +
                           selectedRow.getAttribute("MontoAdjudicado"));
            LOGGER.warning("Valor idTcaTipoMoneda seleccionado: " +
                           selectedRow.getAttribute("IdTcaTipoMoneda"));

            implementacionBean.setHabilitaBotonAgregarAdjudicatario(Boolean.TRUE);
            implementacionBean.setHabilitaBotonGuardarEliminarAdjudicatario(Boolean.FALSE);

            idAdjudicatarioObtenido = Long.parseLong(selectedRow.getAttribute("Id").toString());
            implementacionBean.setVarIdAdjudicatarioReadOnly(idAdjudicatarioObtenido);
        } catch (Exception e) {
            LOGGER.warning("El row seleccionarRegistroAdjudicatario es nuevo con valor null.", e);
        }
    }

    public String agregarAdjudicatarioAction() {
        LOGGER.warning("Entra en agregarAdjudicatarioAction");
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        Long idParticipante = null;
        String nombreParticipante = null;
        Integer idCatPaisParticipante = null;

        DCIteratorBinding implementacionParticipanteVOIterator = null;
        ViewObject implementacionParticipanteVO = null;

        Boolean banderaParticipanteDuplicado = Boolean.TRUE;

        DCIteratorBinding voImplementacionAdjudicatario = null;
        RowSetIterator rowSetIterator = null;
        Row filaRecuperada = null;

        voImplementacionAdjudicatario =
            ADFUtils.getDCBindingContainer().findIteratorBinding("ImplementacionAdjudicatarioVOIterator");

        rowSetIterator = voImplementacionAdjudicatario.getRowSetIterator();
        filaRecuperada = rowSetIterator.first();
        while (filaRecuperada != null) {
            if (Long.parseLong(filaRecuperada.getAttribute("Id").toString()) == idParticipanteObtenido) {
                LOGGER.warning("Ya existe un registro con el Id: " + filaRecuperada.getAttribute("Id").toString());
                banderaParticipanteDuplicado = Boolean.FALSE;
                break;
            }
            filaRecuperada = rowSetIterator.next();
        }
        rowSetIterator.closeRowSetIterator();

        LOGGER.warning("VALOR DE idParticipanteObtenido: " + idParticipanteObtenido);
        
//        if (idParticipanteObtenido != 0 && banderaParticipanteDuplicado == true) {
            implementacionParticipanteVOIterator =
                ADFUtils.getDCBindingContainer().findIteratorBinding("ImplementacionParticipanteVOIterator");
            implementacionParticipanteVO = implementacionParticipanteVOIterator.getViewObject();
            Row row = implementacionParticipanteVO.getCurrentRow();
            if (null != row.getAttribute("Id")) {
                idParticipante = (Long) row.getAttribute("Id");
                LOGGER.warning("EL id del participante es :" + idParticipante);
                if (null != row.getAttribute("NombreParticipante")) {
                    nombreParticipante = (String) row.getAttribute("NombreParticipante");
                } else {
                    LOGGER.log(ADFLogger.WARNING, "El NombreParticipante es nulo");
                }
                if (null != row.getAttribute("IdCatPaisParticipante")) {
                    idCatPaisParticipante = (Integer) row.getAttribute("IdCatPaisParticipante");
                } else {
                    LOGGER.log(ADFLogger.WARNING, "El IdCatPaisParticipante es nulo");
                }
                Integer idMoneda = null;
                try {
                    DCIteratorBinding iteratorBinding =
                        (DCIteratorBinding) bindings.get("LoteImplementacionVOIterator");
                    if (iteratorBinding != null) {
                        Row currentRow = iteratorBinding.getCurrentRow();
                        if (currentRow != null) {
                            idMoneda = (Integer) currentRow.getAttribute("IdTcaTipoMoneda");
                        }
                    }
                    LOGGER.warning("El valor seleccionado de la moneda es: " + idMoneda);
                } catch (Exception e) {
                    LOGGER.severe("Error al obtener idMoneda.", e);
                    LOGGER.warning("Excepcion al obtener idMoneda." + e.getClass(), ".", e.getMessage());
                }

                try {
                    idParticipanteObtenido = idParticipante;
                    LOGGER.warning("Valor id adjudicatario :" + idParticipanteObtenido);
                    operationBinding = bindings.getOperationBinding("crearRowAdjudicatario");
                    operationBinding.getParamsMap().put("id", idParticipanteObtenido);
                    operationBinding.getParamsMap().put("nombreParticipante", nombreParticipante);
                    operationBinding.getParamsMap().put("idTcaPaisConcursante", idCatPaisParticipante);
                    operationBinding.getParamsMap().put("IdTcaTipoMoneda", idMoneda);
                    operationBinding.execute();

                    implementacionBean.setHabilitaBotonAgregarAdjudicatario(Boolean.TRUE);
                    implementacionBean.setHabilitaBotonGuardarEliminarAdjudicatario(Boolean.FALSE);
                    implementacionBean.setSoloLectura(Boolean.FALSE);
                    implementacionBean.setHabilitaBotonAgregar(Boolean.TRUE);
                    implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);

                    implementacionBean.setVarIdParticipanteReadOnly(null);
                    //implementacionBean.setVarIdAdjudicatarioReadOnly(null);

                    RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
                    rowSet.clear();
                    AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);

                    RowKeySet rowSet2 = getTableAdjudicatario().getSelectedRowKeys();
                    rowSet2.clear();
                    AdfFacesContext.getCurrentInstance().addPartialTarget(tableAdjudicatario);

                    LOGGER.warning("El row se creo correctamente.");
                } catch (Exception e) {
                    LOGGER.severe("Error al crear un row.", e);
                    LOGGER.warning("Excepcion al crear row." + e.getClass(), ".", e.getMessage());
                }
            } else {
                LOGGER.log(ADFLogger.WARNING, "El Id es nulo");
            }

            implementacionBean.setVarIdParticipanteReadOnly(null);
            //implementacionBean.setVarIdAdjudicatarioReadOnly(null);

            RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
            rowSet.clear();
            AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);

            RowKeySet rowSet2 = getTableAdjudicatario().getSelectedRowKeys();
            rowSet2.clear();
            AdfFacesContext.getCurrentInstance().addPartialTarget(tableAdjudicatario);

            idParticipanteObtenido = Long.valueOf(0);
            idAdjudicatarioObtenido = Long.valueOf(0);
//        } else {
//            LOGGER.warning("No se a seleccionado ningun registro de la tabla Participante");
//        }
        return null;
    }

    public String guardarAdjudicatarioAction() {
        LOGGER.warning("Entra en guardarAdjudicatarioAction.");
        List<String> bundleKeyErrorList = new ArrayList<String>();
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        BigDecimal montoAdjudicado = null;
        
        try {
            if(null != ADFUtils.getBoundAttributeValue("MontoAdjudicado")){
                montoAdjudicado = (BigDecimal)ADFUtils.getBoundAttributeValue("MontoAdjudicado");
            }
            if(null != montoAdjudicado){
            if (idAdjudicatarioObtenido == 0) {
                operationBinding = bindings.getOperationBinding("actualizarAdjudicatarioImplementacion");
                operationBinding.execute();

                implementacionBean.setHabilitaBotonAgregar(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);
                implementacionBean.setHabilitaBotonAgregarAdjudicatario(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminarAdjudicatario(Boolean.TRUE);

                LOGGER.warning("El row se creo correctamente.");

                idParticipanteObtenido = Long.valueOf(0);
                idAdjudicatarioObtenido = Long.valueOf(0);

                implementacionBean.setVarIdParticipanteReadOnly(null);
                implementacionBean.setVarIdAdjudicatarioReadOnly(null);

                RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
                rowSet.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);

                RowKeySet rowSet2 = getTableAdjudicatario().getSelectedRowKeys();
                rowSet2.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(tableAdjudicatario);
            } else {
                operationBinding = bindings.getOperationBinding("actualizarAdjudicatarioImplementacion");
                operationBinding.execute();

                implementacionBean.setHabilitaBotonAgregar(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);
                implementacionBean.setHabilitaBotonAgregarAdjudicatario(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminarAdjudicatario(Boolean.TRUE);

                LOGGER.warning("El row se actualizo correctamente.");

                idParticipanteObtenido = Long.valueOf(0);
                idAdjudicatarioObtenido = Long.valueOf(0);

                implementacionBean.setVarIdParticipanteReadOnly(null);
                implementacionBean.setVarIdAdjudicatarioReadOnly(null);

                RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
                rowSet.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);

                RowKeySet rowSet2 = getTableAdjudicatario().getSelectedRowKeys();
                rowSet2.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(tableAdjudicatario);
            }
            }else{
                LOGGER.warning("El monto adjudicado es nulo");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_MONTO_PARTICIPANTE"));
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING,
                       "Error al guardar datos adjudicatario implementacion." + e.getClass() + "." + e);
        }

        implementacionBean.setVarIdParticipanteReadOnly(null);
        implementacionBean.setVarIdAdjudicatarioReadOnly(Long.parseLong("-1"));

        RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
        rowSet.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);

        RowKeySet rowSet2 = getTableAdjudicatario().getSelectedRowKeys();
        rowSet2.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableAdjudicatario);

        idParticipanteObtenido = Long.valueOf(0);
        idAdjudicatarioObtenido = Long.valueOf(0);

        return null;
    }

    public String eliminarAdjudicatarioAction() {
        LOGGER.warning("Entra en eliminarAdjudicatarioAction.");
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        try {
            LOGGER.warning(" Valor idAdjudicatarioObtenido es: " + idAdjudicatarioObtenido);
            if (idAdjudicatarioObtenido == 0) {
                LOGGER.warning("Ingresa al if");
                operationBinding = bindings.getOperationBinding("eliminarRowImplementacionAdjudicatario");
                operationBinding.execute();

                implementacionBean.setHabilitaBotonAgregar(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);
                implementacionBean.setHabilitaBotonAgregarAdjudicatario(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminarAdjudicatario(Boolean.TRUE);

                LOGGER.warning("El row nuevo se elimino correctamente.");

                idParticipanteObtenido = Long.valueOf(0);
                idAdjudicatarioObtenido = Long.valueOf(0);

                implementacionBean.setVarIdParticipanteReadOnly(null);
                implementacionBean.setVarIdAdjudicatarioReadOnly(null);

                RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
                rowSet.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);

                RowKeySet rowSet2 = getTableAdjudicatario().getSelectedRowKeys();
                rowSet2.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(tableAdjudicatario);

            } else {
                LOGGER.warning("Ingresa al else");
                operationBinding = bindings.getOperationBinding("eliminarAdjudicatario");
                operationBinding.getParamsMap().put("id", idAdjudicatarioObtenido);
                operationBinding.execute();
                operationBinding = bindings.getOperationBinding("eliminarRowImplementacionAdjudicatario");
                operationBinding.execute();

                implementacionBean.setHabilitaBotonAgregar(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);
                implementacionBean.setHabilitaBotonAgregarAdjudicatario(Boolean.FALSE);
                implementacionBean.setHabilitaBotonGuardarEliminarAdjudicatario(Boolean.TRUE);

                LOGGER.warning("El row existente  se elimino correctamente.");

                idParticipanteObtenido = Long.valueOf(0);
                idAdjudicatarioObtenido = Long.valueOf(0);

                implementacionBean.setVarIdParticipanteReadOnly(null);
                implementacionBean.setVarIdAdjudicatarioReadOnly(null);

                RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
                rowSet.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);

                RowKeySet rowSet2 = getTableAdjudicatario().getSelectedRowKeys();
                rowSet2.clear();
                AdfFacesContext.getCurrentInstance().addPartialTarget(tableAdjudicatario);

            }
        } catch (Exception e) {
            LOGGER.severe("Error al eliminar Participante.", e);
            LOGGER.warning("Excepcion al eliminar Participante." + e.getClass(), ".", e.getMessage());
        }

        popupEliminarAdjudicatario.hide();
        implementacionBean.setVarIdParticipanteReadOnly(null);
        implementacionBean.setVarIdAdjudicatarioReadOnly(Long.parseLong("-1"));

        RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
        rowSet.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);

        RowKeySet rowSet2 = getTableAdjudicatario().getSelectedRowKeys();
        rowSet2.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableAdjudicatario);

        idParticipanteObtenido = Long.valueOf(0);
        idAdjudicatarioObtenido = Long.valueOf(0);
        return null;
    }

    private Boolean obtenerBanderaSumaAdjudicatario() {
        LOGGER.log(ADFLogger.WARNING, "INTO obtenerBanderaSumaAdjudicatario.");
        Boolean resultadoBandera = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();
        Long idLoteImplementacion = getCodigoLoteImplementacion();
        BigDecimal sumaTotal = null;

        try {
            BindingContainer bindingsRegResAdquision = ADFUtils.getBindingContainer();
            OperationBinding operationBindingValidarSumaAdjudicado =
                bindingsRegResAdquision.getOperationBinding("obtenerSumaMontosAdjudicadosByIdLoteImplementacion");
            operationBindingValidarSumaAdjudicado.getParamsMap().put("idLoteImplementacion", idLoteImplementacion);
            operationBindingValidarSumaAdjudicado.execute();
            sumaTotal = (BigDecimal) operationBindingValidarSumaAdjudicado.getResult();
            LOGGER.log(ADFLogger.WARNING, "Valor suma total montos adjudicados de todos los concursantes." + sumaTotal);
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR,
                       "Error en obtenerSumaMontosAdjudicadosByIdLoteImplementacion." + e.getClass() + "." +
                       e.getMessage());
        }
        BigDecimal montoPresupuestado = null;
        try {
            BindingContainer bindingsIniciarAdquision = ADFUtils.getBindingContainer();
            DCIteratorBinding iteratorBinding =
                (DCIteratorBinding) bindingsIniciarAdquision.get("LoteImplementacionVOIterator");
            if (iteratorBinding != null) {
                Row currentRow = iteratorBinding.getCurrentRow();
                if (currentRow != null) {
                    montoPresupuestado = (BigDecimal) currentRow.getAttribute("MontoPresupuestado");
                }
            }
            LOGGER.warning("El valor del monto presupuestado es: " + montoPresupuestado);
        } catch (Exception e) {
            LOGGER.severe("Error al obtener MontoPresupuestado.", e);
            LOGGER.warning("Excepcion al obtener MontoPresupuestado." + e.getClass(), ".", e.getMessage());
        }
        try {
            int resultado = sumaTotal.compareTo(montoPresupuestado);
            if (resultado == 1){
                LOGGER.warning("resultado = 1 bandera false");
                resultadoBandera = Boolean.FALSE;
            }else if (resultado == -1){
                LOGGER.warning("resultado = -1 bandera true");
                resultadoBandera = Boolean.TRUE;
            }else if (resultado == 0){
                LOGGER.warning("resultado = 0 bandera true");
                resultadoBandera = Boolean.TRUE;
            }else
                resultadoBandera = Boolean.FALSE;
        } catch (Exception e) {
            LOGGER.severe("Error al obtener idLote.", e);
            LOGGER.warning("Excepcion al obtener idLote." + e.getClass(), ".", e.getMessage());
        }
        return resultadoBandera;
    }

    public void cambiarFormularioTipoTasa(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Inicia metodo cambiarFormularioTipoTasa");
        Integer idTipoTasa = null;
        EnvioAlCobroBean envioAlCobroBean =
            (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobroBean}");

        if (null != valueChangeEvent) {
            try {
                idTipoTasa = (Integer) valueChangeEvent.getNewValue();
            } catch (Exception e) {
                LOGGER.warning("No se pudo obtener el nuevo id de tipo de tasa.");
            }
        } else {
            LOGGER.warning("ValueChangeEvent es NULL.");
        }

        LOGGER.warning("EL nuevo tipo de tasa: " + idTipoTasa);
        if (null != idTipoTasa) {
            if (idTipoTasa.compareTo(2) == 0) {
                ADFUtils.setBoundAttributeValue("CodigoTasaReferencia", null);
                ADFUtils.setBoundAttributeValue("Spread", null);
                ADFUtils.setBoundAttributeValue("TasaTotal", null);
            }

            if (idTipoTasa.compareTo(3) == 0) {
                ADFUtils.setBoundAttributeValue("Tasa", null);
            }
        } else {
            idTipoTasa = (Integer) valueChangeEvent.getOldValue();
            LOGGER.warning("idTipoTasa es NULL." + idTipoTasa);
        }
        
        envioAlCobroBean.setIdTipoTasa(idTipoTasa);
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPglFinalizacionEstudiosUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getCodigoTasaUI());
        LOGGER.warning("Termina metodo cambiarFormularioTipoTasa");
    }

    public List onSuggest(String string) {
        LOGGER.warning("Inside onSuggestTasaReferencia");
        LOGGER.warning("tasaDesc: " + string);
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();
        
        SelectItem element = null;

        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("DescripcionTasaListAttr");
        
        Row[] values = list.getAllRowsInRange();
        if (null != values) {
            for (Row obj : values) {
                String value = (String) obj.getAttribute("DescripcionTasa");
                //LOGGER.warning("descripcion: " + value);
                if (null != string && null != value) {
                    if (value.toUpperCase().contains(string.toUpperCase())) {
                        element = new SelectItem(value);
                        resultItems.add(element);
                    }
                }
            }
        } else {
            element = new SelectItem("");
            resultItems.add(element);
        }
        
        return resultItems;
    }
    
    public void calcularTasaVariable(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Inicia metodo calcularTasaVariable");
        String codigoTasaReferencia = null;
        BigDecimal tasaFlexcube = null;
        oracle.jbo.domain.Number tasaNumber = null;
        String tasaString = null;
        //FIX FNXII-6157 se agrega la moneda como validacion para poder obtener la tasa
        String codigoMoneda= FenixConstants.MONEDA_USD;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String codigo = null;
        BigDecimal valorActual = null;
        String moneda=obtenerDescripcionMoneda();
        if (null != valueChangeEvent) {
            LOGGER.warning("Ingresa if valueChangeEvent");
            if (null != valueChangeEvent.getNewValue()) {
                LOGGER.warning("Ingresa if valueChangeEvent.getNewValue");
                try {
                    codigoTasaReferencia = (String) valueChangeEvent.getNewValue();
                } catch (Exception e) {
                    LOGGER.warning("No se pudo obtener el valor de Codigo de tasa de referencia.");
                }
                
                operationBinding = bindings.getOperationBinding("obtenerDatosTasaReferencia2");
                operationBinding.getParamsMap().put("descripcionTasa", codigoTasaReferencia);
                operationBinding.getParamsMap().put("moneda", moneda);
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    // Manejo de errores
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                } else if (operationBinding.getResult() != null) {
                    Map<String, Object> resultado = new HashMap<String, Object>();
                    resultado = (HashMap<String, Object>) operationBinding.getResult();

                    valorActual = (BigDecimal) resultado.get("ValorActual");
                    codigo = (String) resultado.get("Codigo");

                    LOGGER.warning("valorActual: " + valorActual);
                    LOGGER.warning("codigo: " + codigo);
                }
                try {
                    tasaFlexcube = valorActual;
                    LOGGER.warning("Valor de tasa Flexcube: " + tasaFlexcube);
                    tasaFlexcube.setScale(5, RoundingMode.FLOOR);
                    LOGGER.warning("Valor de tasa Flexcube: " + tasaFlexcube);
                } catch (Exception e) {
                    LOGGER.warning("No se pudo obtener el valor de tasa de Flexcube.");
                }

                try {
                    tasaString = tasaFlexcube.toString();
                    LOGGER.warning("Valor TasaString" + tasaString);
                    //                                        tasaString = tasaString.substring(0, tasaString.indexOf('.') + 5);
                    //                                        LOGGER.warning("Valor Tasa substring" + tasaString);
                    tasaNumber = new oracle.jbo.domain.Number(tasaString);
                    LOGGER.warning("Tasa Flexcube: " + tasaString);
                } catch (Exception e) {
                    LOGGER.warning("No se pudo obtener la tasa flexcube.", e);
                }
                ADFUtils.setBoundAttributeValue("Tasa", tasaNumber);
                sumarTasaSpread();
            }
        }
        LOGGER.warning("Termina metodo calcularTasaVariable");
    }

//    public void calcularTasaVariable(ValueChangeEvent valueChangeEvent) {
//        LOGGER.warning("Inicia metodo calcularTasaVariable");
//        String codigoTasaReferencia = null;
//        BigDecimal tasaFlexcube = null;
//        oracle.jbo.domain.Number tasaNumber = null;
//        String tasaString = null;
//        //FIX FNXII-6157 se agrega la moneda como validacion para poder obtener la tasa
//        String codigoMoneda= FenixConstants.MONEDA_USD;
//
//        if (null != valueChangeEvent) {
//            LOGGER.warning("Ingresa if valueChangeEvent");
//            if (null != valueChangeEvent.getNewValue()) {
//                LOGGER.warning("Ingresa if valueChangeEvent.getNewValue");
//                try {
//                    codigoTasaReferencia = (String) valueChangeEvent.getNewValue();
//                } catch (Exception e) {
//                    LOGGER.warning("No se pudo obtener el valor de Codigo de tasa de referencia.");
//                }
//
//                if (null != codigoTasaReferencia) {
//                    LOGGER.warning("Ingresa if codigoTasaReferencia");
//                    RowSetIterator iter = null;
//                    try {
//                        iter =
//                            ADFUtils.getDCBindingContainer().findIteratorBinding("VcaTasaDesembolsoFlexcubeVOIterator").getRowSetIterator();
//                    } catch (Exception e) {
//                        LOGGER.warning("No se pudo ejecutar correctamente el OperationBeninding.");
//                    }
//
//                    Row row = null;
//                    if (null != iter) {
//                        iter.reset();
//                        while (iter.hasNext()) {
//                            row = iter.next();
//                            if (null != row) {
//                                String codigoTasaRow = null;
//                                String claveMoneda=null;
//                                try {
//                                    codigoTasaRow = (String) row.getAttribute("DescripcionTasa");
//                                    //FIX FNXII-6157 se agrega la moneda como validacion para poder obtener la tasa
//                                    claveMoneda=(String)row.getAttribute("CodigoMoneda");
//                                } catch (Exception e) {
//                                    LOGGER.warning("No se pudo obtener el codigo de tasa del row.");
//                                }
//                                //FIX FNXII-6157 se agrega la moneda como validacion para poder obtener la tasa
//                                if (codigoTasaReferencia.equalsIgnoreCase(codigoTasaRow) && codigoMoneda.equalsIgnoreCase(claveMoneda)) {
//                                    try {
//                                        tasaFlexcube = (BigDecimal) row.getAttribute("ValorActual");
//                                        LOGGER.warning("Valor de tasa Flexcube: " + tasaFlexcube);
//                                        tasaFlexcube.setScale(5, RoundingMode.FLOOR);
//                                        LOGGER.warning("Valor de tasa Flexcube: " + tasaFlexcube);
//                                    } catch (Exception e) {
//                                        LOGGER.warning("No se pudo obtener el valor de tasa de Flexcube.");
//                                    }
//
//                                    try {
//                                        tasaString = tasaFlexcube.toString();
//                                        LOGGER.warning("Valor TasaString" + tasaString);
//                                        //                                        tasaString = tasaString.substring(0, tasaString.indexOf('.') + 5);
//                                        //                                        LOGGER.warning("Valor Tasa substring" + tasaString);
//                                        tasaNumber = new oracle.jbo.domain.Number(tasaString);
//                                        LOGGER.warning("Tasa Flexcube: " + tasaString);
//                                    } catch (Exception e) {
//                                        LOGGER.warning("No se pudo obtener la tasa flexcube.", e);
//                                    }
//
//                                    break;
//                                }
//                            } else {
//                                LOGGER.warning("El row es NULL.");
//                            }
//                        }
//                    } else {
//                        LOGGER.warning("Iterador NULL");
//                    }
//                    iter.closeRowSetIterator();
//
//                    ADFUtils.setBoundAttributeValue("Tasa", tasaNumber);
//                    sumarTasaSpread();
//                } else {
//                    LOGGER.warning("El codigoTasaReferencia es NULL.");
//                }
//            } else {
//                LOGGER.warning("Nuevo valor del VCE es NULL.");
//            }
//        } else {
//            LOGGER.warning("ValueChangeEvent es NULL.");
//        }
//
//        LOGGER.warning("Termina metodo calcularTasaVariable");
//    }

    public void sumarTasaSpread() {
        LOGGER.warning("Inicia metodo sumarTasaSpread");
        oracle.jbo.domain.Number tasa = null;
        oracle.jbo.domain.Number spread = null;
        oracle.jbo.domain.Number tasaTotal = null;

        try {
            tasa = new oracle.jbo.domain.Number(ADFUtils.getBoundAttributeValue("Tasa").toString());
        } catch (Exception e) {
            LOGGER.warning("No se pudo obtener el valor de Tasa.");
        }

        try {
            spread = new oracle.jbo.domain.Number(ADFUtils.getBoundAttributeValue("Spread").toString());
        } catch (Exception e) {
            LOGGER.warning("No se pudo obtener el valor de Spread.");
        }

        if (null != tasa && null != spread) {
            tasaTotal = tasa.add(spread);
        } else {
            LOGGER.warning("Valores de suma nulos. Tasa: " + tasa + ", Spread: " + spread);
        }

        ADFUtils.setBoundAttributeValue("TasaTotal", tasaTotal);
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPglFinalizacionEstudiosUIC());
        LOGGER.warning("Termina metodo sumarTasaSpread");
    }

    public void cambiarSpread(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Inicia metodo cambiarSpread");
        oracle.jbo.domain.Number spreadNumber = null;

        if (null != valueChangeEvent) {
            try {
                spreadNumber = new oracle.jbo.domain.Number(valueChangeEvent.getNewValue().toString());
                LOGGER.warning("Nuevo Spread: " + spreadNumber);
            } catch (Exception e) {
                LOGGER.warning("No se pudo obtener el nuevo valor de spread.");
            }
        } else {
            LOGGER.warning("ValueChangeEvent es NULL");
        }

        ADFUtils.setBoundAttributeValue("Spread", spreadNumber);
        sumarTasaSpread();

        LOGGER.warning("Termina metodo cambiarSpread");
    }


    /**
     *Metodo para crear contrato de desembolso al confirmar en la pantalla Envio al cobro
     * @param actionEvent
     */
    public void crearSolicitudDesembolso(ActionEvent actionEvent) {
        LOGGER.warning("Inicia metodo crearSolicitudDesembolso");
        Long idOperacion = null;
        Long idLineaCredito = getLineaCreditoSeleccionada();
        String responsableOperacion = null;
        Date fechaCreacionSolicitud = new Date();
        Map<String, Object> params = new HashMap<String, Object>();
        Map datosSolicitudMap = new HashMap();
        Long idSolicitud = null;
        Long idContratoCreado = null;
        BigDecimal montoAmpliacion = null;
        EnvioAlCobroBean envioAlCobroBean =
            (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");

        try {
            idOperacion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}").toString());
            if (null != JSFUtils.resolveExpression("#{pageFlowScope.idImplementacion}")) {
                //Se obtiene el id de implementacion del payload ya que el en este campo se regresara el i da la
                //solicitud creada
                idSolicitud = (Long) JSFUtils.resolveExpression("#{pageFlowScope.idImplementacion}");
            } else {
                LOGGER.warning("No se obtuvo el id de la solicitud.");
            }
            if (null != envioAlCobroBean.getMontoAmpliacion()) {
                montoAmpliacion = envioAlCobroBean.getMontoAmpliacion();
            } else {
                LOGGER.warning("El monto de ampliacion de la linea es nula.");
            }
        } catch (Exception e) {
            LOGGER.warning("ERROR al obtener idOperacion.", e);
        }

        try {
            responsableOperacion = (String) JSFUtils.resolveExpression("#{pageFlowScope.responsableOperacion}");
        } catch (Exception e) {
            LOGGER.warning("ERROR al obtener responsableOperacion.", e);
        }

        LOGGER.warning("idOperacion: " + idOperacion);
        LOGGER.warning("idLineaCredito: " + idLineaCredito);
        LOGGER.warning("responsableOperacion: " + responsableOperacion);
        LOGGER.warning("idSolicitud: " + idSolicitud);
        LOGGER.warning("Monto ampliacion de la linea :" + montoAmpliacion);

        if (null != montoAmpliacion) {
            if (null != idOperacion && null != idLineaCredito && null != responsableOperacion && null != idSolicitud) {
                params.put("idOperacion", idOperacion);
                params.put("idLineaCredito", idLineaCredito);
                params.put("loginUsuario", responsableOperacion);
                params.put("idSolicitud", idSolicitud);
                params.put("montoTotalContratos", montoAmpliacion);
                try {
                    idContratoCreado = execute(params, "crearContratoEnvioCobro");
                } catch (Exception e) {
                    LOGGER.warning("No se pudo ejecutar el metodo de creacion de contrato.", e);
                }

                LOGGER.warning("IdContratoCreado: " + idContratoCreado);
                if (null != idContratoCreado) {
                    if (null != envioAlCobroBean) {
                        envioAlCobroBean.construirTree(idOperacion);
                        envioAlCobroBean.setIdContratoActivo(idContratoCreado);
                        
                        /* SE agrega por FNXII-6321*/
                        /* El componente de GestorDesembolsosFenix generaba: Error al castear parametro pIdLineaCredito */
                        /* El tipo de dato recibido es oracle.jbo.domain.Number */
                        envioAlCobroBean.setVIdLineaCreditoSeleccionada(new oracle.jbo.domain.Number(idLineaCredito));
                        
                        //Se hace current el nuevo contrato creado por FNXII-6565
                        establecerContratoActual(idContratoCreado);
                        
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getPglRegionContratoUIC());
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getRefrescarTreeTableEnvioCobroBinding());
                        String contratoCreadoStr = String.valueOf(idContratoCreado);
                        JSFUtils.addFacesInformationMessage(getStringFromBundle("MSG_CONTRATO_CREADO").concat(contratoCreadoStr.concat(".")));
                        LOGGER.warning("Contrato de desembolso creado satisfactoriamente ID: " + idContratoCreado +
                                       ".");
                    } else {
                        LOGGER.warning("Instancia de envioAlCobroBean es NULL.");
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_VALIDACION_CAMBIO_DATOS"));
                    }
                } else {
                    LOGGER.warning("IdContratoCreado es NULL. No se ha creado un contrato.");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_VALIDACION_CAMBIO_DATOS"));
                }
            } else {
                LOGGER.warning("Se encontraron valores nulos, no se puede crear el contrato.");
            }
        } else {
            LOGGER.warning("El monto de ampliacion de la linea es nula, no se puede crear un contrato.");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_CREAR_CONTRATO"));
        }
        popupCrearDesembolsoUIC.hide();
        LOGGER.warning("Termina metodo crearSolicitudDesembolso");
    }
    
    public void establecerContratoActual(Long idContratoDesembolso){
        LOGGER.warning("Entra en establecerContratoActual.");
        Map<String, Object> params = new HashMap<String, Object>();
        try{
            params.put("idContratoDesembolso", idContratoDesembolso);
            execute(params, "setCurrentContratoDesembolsoById");
        }catch(Exception e){
            LOGGER.warning("Error en establecerContratoActual.", e);
        }
    }
    
    public void modificarMontoAmpliacion(BigDecimal montoAmpliacion){
        LOGGER.warning("Entra en modificarMontoAmpliacion.");
        Map<String, Object> params = new HashMap<String, Object>();
        try{
            Long idLinea = getLineaCreditoSeleccionada();
            params.put("idLinea", idLinea);
            params.put("montoAmpliacion", montoAmpliacion);
            execute(params, "modificarMontoAmpliacion");
        }catch(Exception e){
            LOGGER.warning("Error en modificarMontoAmpliacion.", e);
        }
    }
    
    public Boolean actualizarMontoAmpliacionLineaCredito(){
        LOGGER.warning("Entra en actualizarMontoAmpliacionLineaCredito");
        Boolean actualiza = Boolean.FALSE;
        
        try{
            actualiza = execute(null, "actualizarMontoAmpliacionEnLineaCredito");
        }catch(Exception e){
            LOGGER.warning("Error al ejecutar metodo de actualizacion.", e);
        }
        LOGGER.warning("Actualiza Monto ampliacion :" + actualiza);
        return actualiza;
    }
    public Boolean guardarDatosOperacion() {
        LOGGER.warning("Inicia metodo guardarDatosOperacion.");
        Boolean resultado = Boolean.FALSE;
        Long idOperacion = getCodigoOperacion();
        Map<String, Object> params = new HashMap<String, Object>();
        String justificacionEnvio = null;
        oracle.jbo.domain.Date fechaFlexActual = null;

        try {
            justificacionEnvio = (String) ADFUtils.getBoundAttributeValue("JustificacionEnvio");
        } catch (Exception e) {
            LOGGER.warning("Error al obtener el BoundAttribute de Justificacion.");
        }
        
        try {
            fechaFlexActual = (oracle.jbo.domain.Date) ADFUtils.getBoundAttributeValue("FechaCalculoInteres");
        } catch (Exception e) {
            LOGGER.warning("Error al obtener el BoundAttribute de FechaCalculoInteres.");
        }
        
        LOGGER.warning("JustificacionEnvio: " + justificacionEnvio);
        LOGGER.warning("fechaFlexActual: " + fechaFlexActual);

        if (null != justificacionEnvio) {
            LOGGER.warning("IdOperacion: " + idOperacion);
            params.put("idOperacion", idOperacion);
            params.put("justificacion", justificacionEnvio);
            params.put("fechaFlexActual", fechaFlexActual);
            try {
                resultado = execute(params, "guardarJustificacionEnvio");
            } catch (Exception e) {
                LOGGER.warning("Error al ejecutar operation binding para guardar los datos de la operacion.");
            }
        } else {
            LOGGER.warning("JustificacionEnvio es NULL.");
        }

        LOGGER.warning("Datos de operacion guardados correctamente: " + resultado);
        LOGGER.warning("Termina metodo guardarDatosOperacion.");
        return resultado;
    }

    public Boolean guardarDatosContratoEnvioCobro() {
        LOGGER.warning("Inicia metodo guardarDatosContratoEnvioCobro");
        Boolean resultado = Boolean.FALSE;

        try {
            resultado = execute(null, "guardarDatosContratoEnvioCobro");
        } catch (Exception e) {
            LOGGER.warning("ERROR al ejecutar el operationBinding.", e);
        }

        LOGGER.warning("Resultado de guardado de datos de contrato de Enviar al cobro: " + resultado);
        LOGGER.warning("Termina metodo guardarDatosContratoEnvioCobro");
        return resultado;
    }

    public Boolean guardarDatosGeneralesTab(){
        LOGGER.warning("Entra en guardarDatosGeneralesTab");
        Map resultado = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        Boolean esDatosGuardados = Boolean.FALSE;
        Boolean aplicaProyecto = Boolean.FALSE; 
        Integer valida = 0;
        params.put("aplicaProyecto", aplicaProyecto);
        params.put("valida", valida);
        Boolean errorValidacion = Boolean.FALSE;
        Boolean actualiza = Boolean.FALSE;
        String mensaje = null;
        String mensajepro = null;
        String mensajeproy = null;
        String mensajeactf1 = null;
        String mensajedest = null;
        String mensajemod = null;
        String mensajeact = null;
        String mensajearea = null;
        String mensajeeje = null;
    
        try{
            resultado = execute(params, "validarCampos");
            if(null != resultado){
                errorValidacion = (Boolean)resultado.get("valida");
                actualiza = (Boolean)resultado.get("actualiza");
                if(errorValidacion && actualiza){
                    esDatosGuardados = Boolean.TRUE;
                }else{
                    LOGGER.warning("Error al guardar los datos generales");
                    if(!errorValidacion){
                        if(null != resultado.get("mensajeerror")){
                            mensaje = (String)resultado.get("mensajeerror");
                            JSFUtils.addFacesErrorMessage(mensaje);
                        }else{
                            LOGGER.warning("mensajeerror es nulo. No hay error");
                        }
                        if(null != resultado.get("mensajepro")){
                            mensajepro = (String)resultado.get("mensajepro");
                            JSFUtils.addFacesErrorMessage(mensajepro);
                        }else{
                            LOGGER.warning("mensajepro es nulo. No hay error");
                        }
                        if(null != resultado.get("mensajeproy")){
                            mensajeproy = (String)resultado.get("mensajeproy");
                            JSFUtils.addFacesErrorMessage(mensajeproy);
                        }else{
                            LOGGER.warning("mensajeproy es nulo. No hay error");
                        }
                        if(null != resultado.get("mensajeactf1")){
                            mensajeactf1 = (String)resultado.get("mensajeactf1");
                            JSFUtils.addFacesErrorMessage(mensajeactf1);
                        }else{
                            LOGGER.warning("mensajeactf1 es nulo. No hay error");
                        }
                        if(null != resultado.get("mensajedest")){
                            mensajedest = (String)resultado.get("mensajedest");
                            JSFUtils.addFacesErrorMessage(mensajedest);
                        }else{
                            LOGGER.warning("mensajedest es nulo. No hay error");
                        }
                        if(null != resultado.get("mensajemod")){
                            mensajemod = (String)resultado.get("mensajemod");
                            JSFUtils.addFacesErrorMessage(mensajemod);
                        }else{
                            LOGGER.warning("mensajemod es nulo. No hay error");
                        }
                        if(null != resultado.get("mensajeact")){
                            mensajeact = (String)resultado.get("mensajeact");
                            JSFUtils.addFacesErrorMessage(mensajeact);
                        }else{
                            LOGGER.warning("mensajeact es nulo. No hay error");
                        }
                        if(null != resultado.get("mensajearea")){
                            mensajearea = (String)resultado.get("mensajearea");
                            JSFUtils.addFacesErrorMessage(mensajearea);
                        }else{
                            LOGGER.warning("mensajearea es nulo. No hay error");
                        }
                        if(null != resultado.get("mensajeeje")){
                            mensajeeje = (String)resultado.get("mensajeeje");
                            JSFUtils.addFacesErrorMessage(mensajeeje);
                        }else{
                            LOGGER.warning("mensajeeje es nulo. No hay error");
                        }
                    }
                    if(!actualiza)
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_AL_ACTUALIZAR_DATOS_GENERALES"));
                }
            }else{
                LOGGER.warning("Error el valor de retorno es nulo.");
            }
        }catch(Exception e){
            LOGGER.warning("Error al guardar los datos generales.", e);
            esDatosGuardados = Boolean.FALSE;
        }
        return esDatosGuardados;
    }
    
    public Boolean guardarCondicionesFinancierasTab(){
        LOGGER.warning("Entra en guardarCondicionesFinancierasTab");
        Boolean esDatosGuardados = Boolean.FALSE;
        try{
            esDatosGuardados = execute(null, "validarDatosRequeridos");
            if(!esDatosGuardados)
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_AL_GUARDAR_CONDICIONES_FINANCIERAS"));
                
        }catch(Exception e){
            LOGGER.warning("Error al validar los datos de condiciones financieras.", e);
        }
        LOGGER.warning("Valor de retorno al guardar registro :" + esDatosGuardados);
        return esDatosGuardados;
    }
    
    public Boolean guardarCargos(){
        LOGGER.warning("Entra en guardarCargos");
        Boolean esDatosCargos = Boolean.FALSE;
        Long idContrato = null;
        
        try{
            esDatosCargos = execute(null, "guardarCargos");
            if(!esDatosCargos)
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_AL_GUARDAR_CARGOS"));
        }catch(Exception e){
            LOGGER.warning("Error al guardar los cargos.", e);
        }
        LOGGER.warning("Valor de retorno al guardar los Cargos." + esDatosCargos);
        return esDatosCargos;
    }
    public void validaNumeroPostivo(ValueChangeEvent vc) {
        LOGGER.warning("Entra en validaNumeroPostivo.");
        oracle.jbo.domain.Number tasa = null;
        if (null != vc.getNewValue()) {
            tasa = (oracle.jbo.domain.Number) vc.getNewValue();
            LOGGER.warning("Valor de la tasa." + tasa);
            if (tasa.compareTo(0) == -1) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_NUMERO_POSITIVO"));
                uiValorTasa.resetValue();
                AdfFacesContext.getCurrentInstance().addPartialTarget(pglFinalizacionEstudiosUIC);
                LOGGER.warning("Se actualiza componentes.");
            } else {
                LOGGER.warning("El valor de la tasa es correcto");
            }
        }
    }

    public void setPglFinalizacionEstudiosUIC(RichPanelGroupLayout pglFinalizacionEstudiosUIC) {
        this.pglFinalizacionEstudiosUIC = pglFinalizacionEstudiosUIC;
    }

    public RichPanelGroupLayout getPglFinalizacionEstudiosUIC() {
        return pglFinalizacionEstudiosUIC;
    }

    public void setPopupEliminarLote(RichPopup popupEliminarLote) {
        this.popupEliminarLote = popupEliminarLote;
    }

    public RichPopup getPopupEliminarLote() {
        return popupEliminarLote;
    }

    public void setPopupEliminarParticipante(RichPopup popupEliminarParticipante) {
        this.popupEliminarParticipante = popupEliminarParticipante;
    }

    public RichPopup getPopupEliminarParticipante() {
        return popupEliminarParticipante;
    }

    public void setPopupEliminarAdjudicatario(RichPopup popupEliminarAdjudicatario) {
        this.popupEliminarAdjudicatario = popupEliminarAdjudicatario;
    }

    public RichPopup getPopupEliminarAdjudicatario() {
        return popupEliminarAdjudicatario;
    }

    public void eliminarLotePopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupEliminarLote.show(hints);
    }

    public String cancelarEliminarLote() {
        popupEliminarLote.hide();
        return null;
    }

    public void eliminarParticipantePopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupEliminarParticipante.show(hints);
    }

    public String cancelarEliminarParticipante() {
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        popupEliminarParticipante.hide();

        implementacionBean.setHabilitaBotonAgregar(Boolean.FALSE);
        implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);
        implementacionBean.setHabilitaBotonGuardarEliminarAdjudicatario(Boolean.TRUE);

        implementacionBean.setVarIdParticipanteReadOnly(null);
        implementacionBean.setVarIdAdjudicatarioReadOnly(Long.parseLong("-1"));

        RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
        rowSet.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);

        RowKeySet rowSet2 = getTableAdjudicatario().getSelectedRowKeys();
        rowSet2.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableAdjudicatario);

        idParticipanteObtenido = Long.valueOf(0);
        idAdjudicatarioObtenido = Long.valueOf(0);
        return null;
    }

    public void eliminarAdjudicatarioPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupEliminarAdjudicatario.show(hints);
    }

    public void mostrarPopupDesembolso() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupCrearDesembolsoUIC.show(hints);
    }

    public void cancelarCrearDesembolso(ActionEvent actionEvent) {
        popupCrearDesembolsoUIC.hide();
    }

    public String cancelarEliminarAdjudicatario() {
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
        popupEliminarAdjudicatario.hide();

        implementacionBean.setHabilitaBotonAgregar(Boolean.FALSE);
        implementacionBean.setHabilitaBotonGuardarEliminar(Boolean.TRUE);
        implementacionBean.setHabilitaBotonAgregarAdjudicatario(Boolean.FALSE);
        implementacionBean.setHabilitaBotonGuardarEliminarAdjudicatario(Boolean.TRUE);

        implementacionBean.setVarIdParticipanteReadOnly(null);
        implementacionBean.setVarIdAdjudicatarioReadOnly(Long.parseLong("-1"));

        RowKeySet rowSet = getTableParticiante().getSelectedRowKeys();
        rowSet.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableParticiante);

        RowKeySet rowSet2 = getTableAdjudicatario().getSelectedRowKeys();
        rowSet2.clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableAdjudicatario);

        idParticipanteObtenido = Long.valueOf(0);
        idAdjudicatarioObtenido = Long.valueOf(0);
        return null;
    }

    public static Object invokeTable(String cadena, Class[] paramTypes, Object[] params) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext context = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        MethodExpression exp = expressionFactory.createMethodExpression(context, cadena, Object.class, paramTypes);
        return exp.invoke(context, params);
    }

    public static Object evaluateTable(String cadena) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext context = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        ValueExpression exp = expressionFactory.createValueExpression(context, cadena, Object.class);
        return exp.getValue(context);
    }

    public void setTable(RichTable table) {
        this.table = table;
    }

    public RichTable getTable() {
        if (table != null) {
            table.getSelectedRowKeys().clear();
        }
        return table;
    }

    public void setTableParticiante(RichTable tableParticiante) {
        this.tableParticiante = tableParticiante;
    }

    public RichTable getTableParticiante() {
        if (tableParticiante != null) {
            tableParticiante.getSelectedRowKeys().clear();
        }
        return tableParticiante;
    }

    public void setTableAdjudicatario(RichTable tableAdjudicatario) {
        this.tableAdjudicatario = tableAdjudicatario;
    }

    public RichTable getTableAdjudicatario() {
        if (tableAdjudicatario != null) {
            tableAdjudicatario.getSelectedRowKeys().clear();
        }
        return tableAdjudicatario;
    }

    public void setPopupCrearDesembolsoUIC(RichPopup popupCrearDesembolsoUIC) {
        this.popupCrearDesembolsoUIC = popupCrearDesembolsoUIC;
    }

    public RichPopup getPopupCrearDesembolsoUIC() {
        return popupCrearDesembolsoUIC;
    }

    public void solicitarCrearDesembolso(ActionEvent actionEvent) {
        LOGGER.warning("*Inf, Inicia metodo solicitarCrearDesembolso");
        String mensajePopup = getStringFromBundle("CONFIRMAR_CREAR_DESEMBOLSO_LBL");
        EnvioAlCobroBean envioAlCobroBean =
            (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");
        Long idLineaSeleccionada = null;
        Long idOperacion = null;
        String numeroLineaSeleccionada = null;
        BigDecimal montoAmpliacion = null;
        Boolean existeContrato = Boolean.FALSE;

        if (null != envioAlCobroBean) {
            
            idLineaSeleccionada = envioAlCobroBean.getIdSeleccionaLineaCredito();
            numeroLineaSeleccionada = envioAlCobroBean.getNumeroLineaSeleccionada();
            idOperacion = envioAlCobroBean.getIdOperacion();
            LOGGER.warning("Valor de la linea seleccionada :" + idLineaSeleccionada);
            existeContrato = existeContratoCreadoPorimplementacion(idLineaSeleccionada, idOperacion);
            
            if (null != idLineaSeleccionada) {
                if (!existeContrato) {
                    LOGGER.warning("Numero de la linea seleccionada :" + numeroLineaSeleccionada);
                    if(null != numeroLineaSeleccionada){
                        mensajePopup = mensajePopup.concat(numeroLineaSeleccionada.concat("?"));
                    }else{
                        LOGGER.warning("No se ha seleccionado alguna linea.");
                    }
                    
                    if (null != envioAlCobroBean.getMontoAmpliacion() &&
                        envioAlCobroBean.getMontoAmpliacion().compareTo(BigDecimal.ZERO) == 1) {
                        
                        montoAmpliacion = envioAlCobroBean.getMontoAmpliacion();
                        LOGGER.warning("Monto Ampliacion :" + montoAmpliacion);
                        LOGGER.warning("Mensaje para popup de crear contrato: " + mensajePopup);
                        envioAlCobroBean.setMensajePopupCrearContrato(mensajePopup);
                        mostrarPopupDesembolso();
                        
                    } else {
                        LOGGER.warning("El montoAmpliacion es nulo, no se puede crear el contrato.");
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_CREAR_CONTRATO"));
                    }
                } else {
                    LOGGER.warning("Ya ha sido creado un contrato para esta linea");
                    JSFUtils.addFacesInformationMessage(getStringFromBundle("MSG_INFO_CREAR_CONTRATO"));
                }
            } else {
                LOGGER.warning("IdLineaSeleccionada es NULL. No se puede concatenar el numero de linea.");
                JSFUtils.addFacesErrorMessage("Se debe seleccionar una linea para crear un contrato");
            }
        } else {
            LOGGER.warning("Instancia de EnvioAlCobroBean es NULL.");
        }

        LOGGER.warning("*Inf, Termina metodo solicitarCrearDesembolso");
    }

    public Boolean existeContratoCreadoPorimplementacion(Long idLinea, Long idOperacion){
        LOGGER.warning("Entra en existeContratoCreadoPorimplementacion");
        
        Map<String, Object> params = new HashMap<String, Object>();
        Boolean existeRegistro = Boolean.FALSE;
        try{
            params.put("idLinea", idLinea);
            params.put("idOperacion", idOperacion);
            existeRegistro = execute(params, "buscarContratoEstadoCreadoPorImplementacion");
        }catch(Exception e){
            LOGGER.warning("Error en busqueda de contrato creado por implementacion.", e);
        }
        LOGGER.warning("Se encontro registro :" + existeRegistro);
        return existeRegistro;
    }
    private RichTreeTable treeTableEnvioALCobroBind;

    public void setTreeTableEnvioALCobroBind(RichTreeTable treeTableEnvioALCobroBind) {
        this.treeTableEnvioALCobroBind = treeTableEnvioALCobroBind;
    }

    public RichTreeTable getTreeTableEnvioALCobroBind() {
        return treeTableEnvioALCobroBind;
    }

    /**
     * -Este metodo recupera el id de la fila que se seleccione del tree de envio al cobro
     * @since 10/01/2017
     * @author Carlos Lopez
     */

    public void seleccionarRegistroTreeTableLineaContrato(SelectionEvent selectionEvent) {
        LOGGER.warning("Inf, Inicia metodo seleccionarRegistroTreeTableLineaContrato");
        EnvioAlCobroBean envioAlCobroBean =
            (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");

        CollectionModel treeModel = null;
        treeModel = (CollectionModel) this.getTreeTableEnvioALCobroBind().getValue();
        //Get selected row keys from treeTable
        RowKeySet selectedChildKeys = getTreeTableEnvioALCobroBind().getSelectedRowKeys();

        if (!selectedChildKeys.isEmpty()) {
            List<TreeEnvioAlCobro> LineasList = (List<TreeEnvioAlCobro>) treeModel.getWrappedData();
            //Create iterator from RowKeySet
            Iterator selectedCharIter = selectedChildKeys.iterator();
            //Iterate over RowKeySet to get all selected childs of treeTable
            while (selectedCharIter.hasNext()) {
                List val = (List) selectedCharIter.next();
                //Get Lineas (Parent) List of selected row
                TreeEnvioAlCobro s = LineasList.get(Integer.parseInt(val.get(0).toString()));
                //Get nodos list of selected seasons
                List<TreeEnvioAlCobro> nodosList = s.getNodos();

                Long idContratoActivo = null;
                Long idLinea = null;
                String numeroLinea = null;
                BigDecimal montoAmpliacion = null;

                //If selected record is child (Character)
                if (val.size() > 1) {
                    TreeEnvioAlCobro character = nodosList.get(Integer.parseInt(val.get(1).toString()));
                    //LOGGER.warning("*Inf, Contrato seleccionado: " + character.getNumeroLinea());
                    //LOGGER.warning("*Inf, IdLinea seleccionada: " + character.getId());


                    String claveLineaContrato = null;
                    if (null != character.getNumeroLinea()) {
                        claveLineaContrato = character.getNumeroLinea();
                        try {
                            idContratoActivo = new Long(claveLineaContrato);
                        } catch (Exception e) {
                            LOGGER.warning("***ERROR al convertir el claveLineaContrato.", e);
                        }
                    } else {
                        LOGGER.warning("*Inf, Character.getNumeroLinea() es NULL.");
                    }

                    if (null != character.getId()) {
                        idLinea = character.getId();
                    } else {
                        LOGGER.warning("character.getNumeroLinea() es NULL.");
                    }

                } else {
                    //tratamos los datos de la linea por separado
                    if (null != s.getId()) {
                        idLinea = s.getId();
                        montoAmpliacion = s.getMontoAmpliacion();
                        numeroLinea = s.getNumeroLinea();

                        if (montoAmpliacion != null) {
                            envioAlCobroBean.setSoloLecturaBtnCrearContrato(Boolean.FALSE);
                        } else {
                            envioAlCobroBean.setSoloLecturaBtnCrearContrato(Boolean.TRUE);
                        }

                        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorBtnCrearContrato());
                        LOGGER.warning("*Inf, Monto Ampliacion: " + s.getMontoAmpliacion());
                    } else {
                        LOGGER.warning("*Inf, character.getNumeroLinea() es NULL.");
                    }
                }

                LOGGER.warning("*Inf, IdLinea seleccionada: " + idLinea);
                LOGGER.warning("*Inf, Contrato seleccionado: " + idContratoActivo);
                LOGGER.warning("Monto de ampliacion de la linea: " + montoAmpliacion);


                if (null != envioAlCobroBean) {
                    envioAlCobroBean.setIdContratoActivo(idContratoActivo);
                    envioAlCobroBean.setIdLineaCreditoSeleccionada(idLinea);
//                    String lineaActual= Long.toString(idLinea);
//                    oracle.jbo.domain.Number valorLinea=new oracle.jbo.domain.Number(Long.valueOf(lineaActual));
                    envioAlCobroBean.setVIdLineaCreditoSeleccionada(new oracle.jbo.domain.Number(idLinea));
                    if (null == idContratoActivo) {
                        envioAlCobroBean.setMontoAmpliacion(montoAmpliacion);
                        envioAlCobroBean.setIdSeleccionaLineaCredito(idLinea);
                        envioAlCobroBean.setVIdLineaCreditoSeleccionada(new oracle.jbo.domain.Number(idLinea));
                        envioAlCobroBean.setNumeroLineaSeleccionada(numeroLinea);
                    } else {
                        LOGGER.warning("Es un contrato el que esta seleccionado.");
                        envioAlCobroBean.setIdSeleccionaLineaCredito(null);
                        envioAlCobroBean.setMontoAmpliacion(null);
                    }
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getPglRegionContratoUIC());
                } else {
                    LOGGER.warning("*Inf, Instancia de EnvioAlCobroBean es NULL.");
                }
            }
        }
        LOGGER.warning("*Inf, Termina metodo seleccionarRegistroTreeTableLineaContrato");
        LOGGER.warning("Valor del contrato seleccionado :" + envioAlCobroBean.getIdContratoActivo());
    }


    private RichTreeTable treeTableLiquidarDesembolso;

    public void setTreeTableLiquidarDesembolso(RichTreeTable treeTableLiquidarDesembolso) {
        this.treeTableLiquidarDesembolso = treeTableLiquidarDesembolso;
    }

    public RichTreeTable getTreeTableLiquidarDesembolso() {
        return treeTableLiquidarDesembolso;
    }


    /**
     * -Este metodo recupera el id de la fila que se seleccione del tree de liquidarDesembolso
     * @since 12/01/2017
     * @author Carlos Lopez
     */

    public void seleccionarIdTreeLiquidarDesembolso(SelectionEvent selectionEvent) {
        LOGGER.warning("Inf, Inicia metodo seleccionarIdTreeLiquidarDesembolso");
        EnvioAlCobroBean envioAlCobroBean =
            (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");
        CollectionModel treeModel = null;
        treeModel = (CollectionModel) this.getTreeTableLiquidarDesembolso().getValue();
        //obetenemos el id de fila seleccionada del tree
        RowKeySet selectedChildKeys = getTreeTableLiquidarDesembolso().getSelectedRowKeys();

        if (!selectedChildKeys.isEmpty()) {
            List<TreeLiquidarDesembolso> LineasList = (List<TreeLiquidarDesembolso>) treeModel.getWrappedData();
            //Creamos el itrerador para el  RowKeySet
            Iterator selectedCharIter = selectedChildKeys.iterator();
            //Iteramos para obtener el id de todos los nodos del tree
            while (selectedCharIter.hasNext()) {
                List val = (List) selectedCharIter.next();
                //obtenemos el padre row seleccionada
                TreeLiquidarDesembolso s = LineasList.get(Integer.parseInt(val.get(0).toString()));
                //obtenemos una lista de los nodos seleccionados
                List<TreeLiquidarDesembolso> nodosList = s.getNodos();
                //si seleccionamos un hijo
                if (val.size() > 1) {
                    TreeLiquidarDesembolso nodo = nodosList.get(Integer.parseInt(val.get(1).toString()));
                    LOGGER.warning("Inf, Contrato seleccionado: " + nodo.getId());
                    ImplementacionPCTBean implementacionBean =
                        (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");
                    implementacionBean.setIdContrato(nodo.getId());
                    
                    if (null != nodo) {
                        habilitarBotonActulizar(nodo.getIdEstado());
                    } else {
                        LOGGER.warning("La instancia de nodo es nula.");
                    }
                } else {
                    LOGGER.warning("Inf, Linea seleccionada: " + s.getId());
                    
                    //Se selecciono una linea, no un contrato
                    habilitarBotonActulizar(null);
                }
            }
        }

        LOGGER.warning("Inf, Termina metodo seleccionarIdTreeLiquidarDesembolso");
    }

    private void habilitarBotonActulizar(Integer idEstado) {
        EnvioAlCobroBean envioAlCobroBean =
            (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");
        
        if (null != idEstado && idEstado.intValue() == FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_POR_LIQUIDAR) {
            envioAlCobroBean.setEsVisibleBotonActualizar(Boolean.TRUE);
        } else {
            envioAlCobroBean.setEsVisibleBotonActualizar(Boolean.FALSE);
        }
        
        //AdfFacesContext.getCurrentInstance().addPartialTarget(getBotonActualizarUI());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getBotonActualizarBindingUI());
        LOGGER.warning("Muestra el boton actualizar :" + envioAlCobroBean.getEsVisibleBotonActualizar());
    }

    public void setRegionContratoUIC(RichRegion regionContratoUIC) {
        this.regionContratoUIC = regionContratoUIC;
    }

    public RichRegion getRegionContratoUIC() {
        return regionContratoUIC;
    }

    public void setPglRegionContratoUIC(RichPanelGroupLayout pglRegionContratoUIC) {
        this.pglRegionContratoUIC = pglRegionContratoUIC;
    }

    public RichPanelGroupLayout getPglRegionContratoUIC() {
        return pglRegionContratoUIC;
    }

    @SuppressWarnings("unchecked")
    public void actualizarEstadoLiquidado(ActionEvent actionEvent) {
        LOGGER.entering(this.getClass().getName(), "actualizarEstadoLiquidado");

        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");

        Long idDesembolso = implementacionBean.getIdContrato();
        BigDecimal saldo = null;

        if (null != idDesembolso) {
            // Obtenemos el saldo para el contrato seleccionado
            BindingContainer bc = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bc.getOperationBinding("consultarSaldo");
            operationBinding.getParamsMap().put("idDesembolso", idDesembolso);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else {
                saldo = (BigDecimal) operationBinding.getResult();
            }
            // Se valida que el saldo este en cero
            LOGGER.log(ADFLogger.WARNING, "saldo: " + saldo);
            if (null != saldo && saldo.compareTo(new BigDecimal(0)) == 0) {
                // Se actualiza el estado del contrato en Fenix
                if (liquidarContratoEnFenix(idDesembolso)) {
                    // Se deshabilita boton actualizar
                    habilitarBotonActulizar(null);
                }

                EnvioAlCobroBean envioAlCobroBean =
                    (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");

                if (null != envioAlCobroBean) {
                    envioAlCobroBean.construirTreeLiquidarDesembolso();
                    // Se recarga tree
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelLineasConContratosUIC());
                } else {
                    LOGGER.log(ADFLogger.WARNING, "> La instancia de envioAlCobroBean es null <");
                }
            } else {
                // Se notifica que el contrato no esta liquidado
                String msg = getStringFromBundle("DESEMBOLSO_NO_LIQUIDADO");
                msg = msg.replace("{0}", idDesembolso.toString());
                JSFUtils.addFacesErrorMessage(msg);
            }
        }

        LOGGER.exiting(this.getClass().getName(), "actualizarEstadoLiquidado");
    }
    
    public void actualizarEstadoLiquidadoEnvioAlGasto(ActionEvent actionEvent) {
        LOGGER.entering(this.getClass().getName(), "actualizarEstadoLiquidadoEnvioAlGasto");
        LOGGER.warning("Entra en actualizarEstadoLiquidadoEnvioAlGasto");
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");

        Long idDesembolso = implementacionBean.getIdContrato();
        BigDecimal saldo = null;
        LOGGER.warning("Contrato de desembolso :" + idDesembolso);
        if (null != idDesembolso) {
            // Obtenemos el saldo para el contrato seleccionado
            BindingContainer bc = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bc.getOperationBinding("consultarSaldo");
            operationBinding.getParamsMap().put("idDesembolso", idDesembolso);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else {
                saldo = (BigDecimal) operationBinding.getResult();
            }
            // Se valida que el saldo este en cero
            LOGGER.log(ADFLogger.WARNING, "saldo: " + saldo);
            if (null != saldo && saldo.compareTo(new BigDecimal(0)) == 0) {
                // Se actualiza el estado del contrato en Fenix
                liquidarContratoEnvioAlGasto(idDesembolso);

                if (null != implementacionBean) {
                    implementacionBean.inicializarTreeEnvioGasto();
                    // Se recarga tree
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getTreeTable());
                } else {
                    LOGGER.log(ADFLogger.WARNING, "> La instancia de implementacionBean es null <");
                }
            } else {
                // Se notifica que el contrato no esta liquidado
                contratoNoLiquidadoEnvioAlGasto(idDesembolso);
                String msg = getStringFromBundle("DESEMBOLSO_NO_LIQUIDADO");
                msg = msg.replace("{0}", idDesembolso.toString());
                JSFUtils.addFacesInformationMessage(msg);
                if (null != implementacionBean) {
                    implementacionBean.inicializarTreeEnvioGasto();
                    // Se recarga tree
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getTreeTable());
                } else {
                    LOGGER.log(ADFLogger.WARNING, "> La instancia de implementacionBean es null <");
                }
            }
        }else {
            LOGGER.warning("El contrato de desembolso es nulo.");
            JSFUtils.addFacesInformationMessage("Debe seleccionar un contrato de desembolso.");
        }

        LOGGER.exiting(this.getClass().getName(), "actualizarEstadoLiquidadoEnvioAlGasto");
    }

    @SuppressWarnings("unchecked")
    private boolean liquidarContratoEnFenix(Long idDesembolso) {
        LOGGER.entering(this.getClass().getName(), "liquidarContratoEnFenix");
        boolean exito = Boolean.TRUE;
        // Obtenemos el saldo para el contrato seleccionado
        BindingContainer bc = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bc.getOperationBinding("liquidarContratoFenix");
        operationBinding.getParamsMap().put("idContrato", idDesembolso.toString());
        operationBinding.execute();

        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            exito = Boolean.FALSE;
        }

        LOGGER.exiting(this.getClass().getName(), "liquidarContratoEnFenix", exito);
        return exito;
    }
    
    private boolean liquidarContratoEnvioAlGasto(Long idDesembolso) {
        LOGGER.entering(this.getClass().getName(), "liquidarContratoEnFenix");
        boolean exito = Boolean.TRUE;
        // Obtenemos el saldo para el contrato seleccionado
        BindingContainer bc = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bc.getOperationBinding("liquidarContratoEnvioAlGasto");
        operationBinding.getParamsMap().put("idContrato", idDesembolso.toString());
        operationBinding.execute();

        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            exito = Boolean.FALSE;
        }

        LOGGER.exiting(this.getClass().getName(), "liquidarContratoEnFenix", exito);
        return exito;
    }

    private boolean contratoNoLiquidadoEnvioAlGasto(Long idDesembolso) {
        LOGGER.entering(this.getClass().getName(), "contratoNoLiquidadoEnvioAlGasto");
        LOGGER.warning("Entra en contratoNoLiquidadoEnvioAlGasto");
        boolean exito = Boolean.TRUE;
        // Obtenemos el saldo para el contrato seleccionado
        BindingContainer bc = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bc.getOperationBinding("contratoNoLiquidado");
        operationBinding.getParamsMap().put("idContrato", idDesembolso.toString());
        operationBinding.execute();

        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            exito = Boolean.FALSE;
        }

        LOGGER.exiting(this.getClass().getName(), "contratoNoLiquidadoEnvioAlGasto", exito);
        return exito;
    } 
    @SuppressWarnings("unchecked")
    private boolean existenContratosPorLiquidar() {
        LOGGER.entering(this.getClass().getName(), "existenContratosPorLiquidar");
        boolean existenContratos = Boolean.FALSE;
        Long idOperacion = null;

        int contratos;
        try {
            ImplementacionPCTBean implementacionBean =
                (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");

            if (null != implementacionBean.getCodigoOperacion()) {
                LOGGER.log(ADFLogger.WARNING, "idOperacion: " + implementacionBean.getCodigoOperacion());
                idOperacion = new Long(implementacionBean.getCodigoOperacion());
                LOGGER.log(ADFLogger.WARNING, "idOperacion: " + idOperacion);

                BindingContainer bindings = getBindings();
                OperationBinding operBinding = bindings.getOperationBinding("obtenerContratosPorLiquidar");
                operBinding.getParamsMap().put("idOperacion", idOperacion);
                contratos = (Integer) operBinding.execute();
                LOGGER.warning("Numero de contratos obtenidos: " + contratos);
                
                if (contratos > 0) {
                    existenContratos = Boolean.TRUE;
                }
            } else {
                LOGGER.warning("El id de la operacion es nula.");
                //JSFUtils.addFacesErrorMessage("");
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en el OperationBinding de recuperarInteresDeContratos", e);

            //Se evita se continue con el flujo, para su revision en incidencias
            existenContratos = Boolean.TRUE;
        }
        LOGGER.warning("Valor existenContratos a retornar: " + existenContratos);
        LOGGER.exiting(this.getClass().getName(), "existenContratosPorLiquidar", existenContratos);
        return existenContratos;
    }

    public RichPanelGroupLayout getPanelLineasConContratosUIC() {
        return panelLineasConContratosUIC;
    }

    public void setPanelLineasConContratosUIC(RichPanelGroupLayout panelLineasConContratosUIC) {
        this.panelLineasConContratosUIC = panelLineasConContratosUIC;
    }

    /**
     * Valida que las tablas “Participante” y “Adjudicatario” contengan por lo menos un registro cada una.
     * @return Boolean validacion
     */
    private boolean exiteParticipanteConAdjudicatario() {
        LOGGER.warning("exiteParticipanteConAdjudicatario...");

        if (this.exitenRowsByVoIterator("ImplementacionParticipanteVOIterator") &&
            this.exitenRowsByVoIterator("ImplementacionAdjudicatarioVOIterator")) {
            LOGGER.warning("terminó exiteParticipanteConAdjudicatario TRUE.");
            return Boolean.TRUE;
        } else {
            LOGGER.warning("terminó exiteParticipanteConAdjudicatario FALSE.");
            return Boolean.FALSE;
        }
    }

    /**
     * Verifica si existen registros en la VO especificada.
     * @param iterator nombre del iterator declarado
     * @return existe Boolean
     */
    private boolean exitenRowsByVoIterator(String iterator) {
        LOGGER.warning("exitenRowsByVoIterator " + iterator + "...");
        boolean existe = Boolean.FALSE;
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings = bindings.findIteratorBinding(iterator);
        ViewObject vo = dcItteratorBindings.getViewObject();

        if (vo.getRowCount() > 0) {
            existe = Boolean.TRUE;
        }
        LOGGER.warning("valor de exite " + existe);
        return existe;
    }

    /**
     * Valida que la suma de los montos de las adjudicaciones no sea mayor al monto presupuestado.
     * @return Boolean valido
     */
    private boolean validaMontosAdjudicacionesNoMayorPresupuestado() {
        LOGGER.warning("Validando suma de los montos de las adjudicaciones no sea mayor al monto presupuestado...");
        BigDecimal suma = this.getSumaMontosAdjudicaciones();
        BigDecimal monto = this.getMontoPresupuestado();

        if (monto.compareTo(suma) >= 0) {
            LOGGER.warning("La suma de los montos de las adjudicaciones no es mayor al monto presupuestado.");
            return Boolean.TRUE;
        } else {
            LOGGER.warning("La suma de los montos de las adjudicaciones es mayor al monto presupuestado.");
            return Boolean.FALSE;
        }
    }

    /**
     * Obtiene la suma de los montos de las adjudicaciones.
     * @return BigDecimal
     */
    private BigDecimal getSumaMontosAdjudicaciones() {
        LOGGER.warning("Obteniendo la suma de los montos de las adjudicaciones...");
        BigDecimal suma = new BigDecimal(0);
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings = bindings.findIteratorBinding("ImplementacionAdjudicatarioVOIterator");
        ViewObject vo = dcItteratorBindings.getViewObject();

        RowSetIterator rsi = vo.createRowSetIterator(null);
        rsi.reset();
        String nombre = null;
        BigDecimal monto = null;
        LOGGER.warning("Numero de rows :" + rsi.getRowCount());
        while (rsi.hasNext()) {
            Row row = rsi.next();
            LOGGER.warning("Monto del participante " + nombre + " " + monto);
            if (null != row.getAttribute("NombreParticipante")) {
                nombre = (String) row.getAttribute("NombreParticipante");
            } else {
                LOGGER.log(ADFLogger.WARNING, "El NombreParticipante es nulo.");
            }
            if (null != row.getAttribute("MontoAdjudicado")) {
                monto = (BigDecimal) row.getAttribute("MontoAdjudicado");
                suma = suma.add(monto);
            } else {
                LOGGER.log(ADFLogger.WARNING, "El NombreParticipante es nulo.");
            }

        }
        LOGGER.warning("Total de la suma " + suma);
        rsi.closeRowSetIterator();
        LOGGER.warning("Terminó de obtener la suma de los montos de las adjudicaciones.");
        return suma;
    }

    /**
     * Obtiene el monto presupuestado.
     * @return BigDecimal
     */
    private BigDecimal getMontoPresupuestado() {
        LOGGER.warning("Obteniendo monto presupuestado...");
        BigDecimal monto = this.getValueBindingPD("LoteImplementacionVOIterator", "MontoPresupuestado");
        LOGGER.warning("monto presupuestado " + monto);
        LOGGER.warning("Terminó de obtener el monto presupuestado.");
        return monto;
    }

    /**
     * Obtiene el valor de acuerdo al atributo de la VO
     * @param <T> Tipo de dato retornado.
     * @param itarator String Nombre del VOIterator
     * @param attribute String nombre del atributo de la VO
     * @return valor del atributo especificado.
     */
    private <T> T getValueBindingPD(String itarator, String attribute) {
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding(itarator);
        Row r = iterator.getCurrentRow();
        @SuppressWarnings("unchecked")
        T empNameValue = (T) r.getAttribute(attribute);
        return empNameValue;
    }

    /**
     * Valida la información requerida para la tarea Registrar orden de inicio y datos de línea de crédito.
     * @return Boolean
     */
    private Boolean validaInformacionRequeridaROIDLC() {
        LOGGER.warning("Validando información requerida de Registrar orden de inicio y datos de línea de crédito...");
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");

        Boolean validaInformacion = Boolean.FALSE;
        Timestamp fechaOrdenInicio = null;
        Timestamp fechaEscrituracion = null;
        BigDecimal montoEscriturado = null;
        Integer idContrato = null;
        
        if(null != ADFUtils.getBoundAttributeValue("FechaOrdenInicio")){
            fechaOrdenInicio = (Timestamp) ADFUtils.getBoundAttributeValue("FechaOrdenInicio");
        }else{
            LOGGER.warning("La fecha orden de inicio es nula.");
        }
        if(null != this.getValueBindingPD("EncabezadoRegistrarDatosLineaCreditoVOIterator", "FechaEscrituracion")){
            fechaEscrituracion =
                this.getValueBindingPD("EncabezadoRegistrarDatosLineaCreditoVOIterator", "FechaEscrituracion"); 
        }else{
            LOGGER.warning("La fecha de escrituracion es nula.");
        }
        if(null != ADFUtils.getBoundAttributeValue("MontoEscriturado")){
            montoEscriturado = (BigDecimal)ADFUtils.getBoundAttributeValue("MontoEscriturado"); 
        }else{
            LOGGER.warning("El monto escriturado es nulo.");
        }
        if(null != this.getValueBindingPD("EncabezadoRegistrarDatosLineaCreditoVOIterator", "IdContrato")){
            idContrato =
                this.getValueBindingPD("EncabezadoRegistrarDatosLineaCreditoVOIterator", "IdContrato"); 
        }else{
            LOGGER.warning("El IdContrato es nulo.");
        }
        
        LOGGER.warning("fechaOrdenInicio " + fechaOrdenInicio);
        LOGGER.warning("fechaEscrituracion " + fechaEscrituracion);
        LOGGER.warning("montoEscriturado " + montoEscriturado);
        LOGGER.warning("idContrato " + idContrato);

        try {
            if (implementacionBean.getIsGestionaCliente()) {
                if (fechaOrdenInicio != null && fechaEscrituracion != null && montoEscriturado != null) {
                    LOGGER.warning("Terminó de validar la información requerida cuando se trata de una contratación que fue realizada por el cliente");
                    validaInformacion = Boolean.TRUE;
                } else {
                    LOGGER.warning("Se encontraron valores nulos.");
                }
            } else {
                if (fechaOrdenInicio != null) {
                    LOGGER.warning("Terminó de validar la información requerida cuando no es una contratación que fue realizada por el cliente");
                    validaInformacion = Boolean.TRUE;
                } else {
                    LOGGER.warning("La fecha orden de inicio es nula.");
                }
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING, "Error en validaInformacionRequeridaROIDLC." + e);
        }
        LOGGER.log(ADFLogger.WARNING, "La validacion de la informacion es :" + validaInformacion);
        return validaInformacion;
    }

    public Boolean guardarInformacion() {
        LOGGER.log(ADFLogger.WARNING, "Entra en guardarInformacion");
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");

        Long idLoteImplementacion = null;
        Boolean informacionGuardada = Boolean.FALSE;
        Integer idMontoOperacion = null;
        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();

        Timestamp fechaOrdenInicio = (Timestamp)ADFUtils.getBoundAttributeValue("FechaOrdenInicio");
        BigDecimal montoLote = this.getValueBindingPD("LoteImplementacionVOIterator", "MontoPresupuestado");

        Timestamp fechaEscrituracion =
            this.getValueBindingPD("EncabezadoRegistrarDatosLineaCreditoVOIterator", "FechaEscrituracion");
        BigDecimal montoEscriturado = (BigDecimal)ADFUtils.getBoundAttributeValue("MontoEscriturado");
        idLoteImplementacion = Long.valueOf(implementacionBean.getIdLote());

        try {
            LOGGER.log(ADFLogger.WARNING, "Id lote :" + idLoteImplementacion);
            LOGGER.log(ADFLogger.WARNING, "Fecha orden inicio." + fechaOrdenInicio);
            LOGGER.log(ADFLogger.WARNING, "montoEscriturado." + montoEscriturado);

            operationBinding = bindings.getOperationBinding("actualizarFechaOrdenInicio");
            operationBinding.getParamsMap().put("idLoteImplementacion", idLoteImplementacion);
            operationBinding.getParamsMap().put("fechaOrdenInicio", fechaOrdenInicio);
            operationBinding.execute();

            if (null != implementacionBean.getIdMontoEscriturado()) {
                idMontoOperacion = implementacionBean.getIdMontoEscriturado();
                operationBinding = null;
                operationBinding = bindings.getOperationBinding("actualizarMontoImplementacionPCT");
                operationBinding.getParamsMap().put("idMontoOperacion", idMontoOperacion);
                operationBinding.getParamsMap().put("montoEscriturado", montoEscriturado);
                operationBinding.execute();
                LOGGER.warning("Valor idMontoOperacion obtenido: " + idMontoOperacion);
            } else {
                operationBinding = null;
                operationBinding = bindings.getOperationBinding("crearMontoImplementacionPCT");
                operationBinding.getParamsMap().put("idOperacion",
                                                    Long.valueOf(implementacionBean.getCodigoOperacion()));
                operationBinding.getParamsMap().put("monto", montoEscriturado);
                operationBinding.execute();
                idMontoOperacion = (Integer) operationBinding.getResult();
                implementacionBean.setIdMontoEscriturado(idMontoOperacion);
                LOGGER.warning("Valor idMontoOperacion obtenido: " + idMontoOperacion);
            }
            actualizarDatosEscrituracion(implementacionBean.getIdContratoPorLote().longValue(),
                                         implementacionBean.getIdMontoEscriturado(), fechaEscrituracion);
            
            // Commit general para la tarea
            informacionGuardada = Commit();
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING, "Error al ejecutar actualizarFechaOrdenInicio.", e);
        }
        return informacionGuardada;
    }
    
    private boolean Commit() {
        LOGGER.log(ADFLogger.WARNING, "Entrando en Commit.");
        
        boolean exito = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        
        OperationBinding operationBinding = bindings.getOperationBinding("Commit");
        operationBinding.execute();
        
        if (operationBinding.getErrors().isEmpty()) {
            exito = Boolean.TRUE;
        } else {
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        
        LOGGER.log(ADFLogger.WARNING, "exito: " + exito);
        return exito;
    }

    public String guardarLotePorDefectoAction(String nombrePorDefecto) {
        LOGGER.warning("Entra en guardarLotePorDefectoAction.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long idImplementacion = null;

        BigDecimal montoPresupuestado = BigDecimal.ZERO;
        Integer idMoneda = null;
        try {
            DCIteratorBinding iteratorBinding =
                (DCIteratorBinding) bindings.get("FormularioImplementacionPctVOIterator");
            if (iteratorBinding != null) {
                Row currentRow = iteratorBinding.getCurrentRow();
                if (currentRow != null) {
                    montoPresupuestado = (BigDecimal) currentRow.getAttribute("MontoPresupuestado");
                    idMoneda = (Integer) currentRow.getAttribute("IdTcaTipoMoneda");
                }
            }
            LOGGER.warning("El valor obtenido del monto presupuestado es: " + montoPresupuestado);
            LOGGER.warning("El valor obtenido de la moneda es: " + idMoneda);
        } catch (Exception e) {
            LOGGER.severe("Error al obtener monto presupuestado o moneda.", e);
            LOGGER.warning("Excepcion al obtener monto presupuestado o moneda." + e.getClass(), ".", e.getMessage());
        }
        try {
            operationBinding = bindings.getOperationBinding("crearRowFormularioLoteImplementacionInsertarLote");
            operationBinding.execute();

            DCIteratorBinding iteratorBinding =
                (DCIteratorBinding) bindings.get("FormularioLoteImplementacionVOIterator");
            if (iteratorBinding != null) {
                Row currentRow = iteratorBinding.getCurrentRow();
                if (currentRow != null) {
                    currentRow.setAttribute("NombreLote", nombrePorDefecto);
                    currentRow.setAttribute("MontoPresupuestado", montoPresupuestado);
                    currentRow.setAttribute("IdTcaTipoMoneda", idMoneda);
                }
            }
        } catch (Exception e) {
            LOGGER.severe("Error al ingresar valor NombreLote, MontoPresupuestado, idTcaTipoMoneda en FormularioLoteImplementacionVO.",
                          e);
            LOGGER.warning("Excepcion al ingresar NombreLote, MontoPresupuestado, idTcaTipoMoneda en FormularioLoteImplementacionVO." +
                           e.getClass(), ".", e.getMessage());
        }

        try {
            try {
                idImplementacion = getCodigoImplementacion();
                operationBinding = bindings.getOperationBinding("agregarLote");
                operationBinding.getParamsMap().put("idImplementacion", idImplementacion);
                operationBinding.execute();
                LOGGER.warning("El row se creo correctamente.");
            } catch (Exception e) {
                LOGGER.log(ADFLogger.WARNING, "Error al guardar datos lote implementacion." + e.getClass() + "." + e);
            }
        } catch (Exception e) {
            LOGGER.severe("Error en guardarLotePorDefectoAction.", e);
            LOGGER.warning("Excepcion al ingresar en guardarLotePorDefectoAction." + e.getClass(), ".", e.getMessage());
        }

        return null;
    }

    public void obtenerMontoEscrituradoAction(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Ingresa al metodo obtenerMontoEscrituradoAction... ");
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");

        BigDecimal montoEscriturado = (BigDecimal) valueChangeEvent.getNewValue();
        LOGGER.warning("Valor montoEscriturado obtenido: " + montoEscriturado);
        implementacionBean.setMontoEscriturado(montoEscriturado);

        LOGGER.warning("Finaliza el metodo obtenerMontoEscrituradoAction... ");
    }

    public void actualizarDatosEscrituracion(Long idContrato, Integer idTcaTipoMonto, Timestamp fechaEscrituracion) {
        LOGGER.log(ADFLogger.WARNING, "Entra en actualizarDatosEscrituracion");

        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");

        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Boolean datosEscrituracion = Boolean.FALSE;

        try {
            LOGGER.log(ADFLogger.WARNING, "Id Contrato :" + idContrato);
            LOGGER.log(ADFLogger.WARNING, "Fecha Escrituracion." + fechaEscrituracion);
            LOGGER.log(ADFLogger.WARNING, "Id Tca Tipo Monto." + idTcaTipoMonto);

            operationBinding = bindings.getOperationBinding("actualizarDatosEscrituracion");
            operationBinding.getParamsMap().put("idContrato", idContrato);
            operationBinding.getParamsMap().put("idTcaTipoMonto", idTcaTipoMonto);
            operationBinding.getParamsMap().put("fechaEscrituracion", fechaEscrituracion);
            operationBinding.execute();
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING, "Error al ejecutar actualizarDatosEscrituracion :" + e);
        }
        //return datosEscrituracion;
    }

    public Boolean ejecutarOperacionVami() {
        LOGGER.warning("Entra en ejecutarOperacionVami");
        Map mapaDatos2 = new HashMap();
        Boolean obtenerFactibilidadService = Boolean.FALSE;

        LOGGER.warning("*Inf, se invoca a servicio ObtenerFactibilidad");

        try {
            mapaDatos2 = (Map) execute(null, "ObtenerFactibilidadService");
            obtenerFactibilidadService = (Boolean) mapaDatos2.get("respuesta");

            if (!obtenerFactibilidadService){
                LOGGER.warning("Error, obtenerFactibilidadService");
                //JSFUtils.addFacesErrorMessage("Error, obtenerFactibilidadService");
            }  
            if (mapaDatos2.get("MSG_ERROR") != null){
                JSFUtils.addFacesErrorMessage("ERROR al ejecutar servicio ObtenerFactibilidad: " + mapaDatos2.get("MSG_ERROR").toString());
            }else if (mapaDatos2.get("MSG_ERROR_SIN_CONCATENAR") != null){
                JSFUtils.addFacesErrorMessage(mapaDatos2.get("MSG_ERROR_SIN_CONCATENAR").toString());
            }else{
                LOGGER.warning("La tarea finaliza correctamente.");
            }

        } catch (Exception e) {
            LOGGER.warning("***Error, al ejecutar el operBinding ObtenerFactibilidadService.", e);
        }
        LOGGER.warning("Valor de ejecucion del VAMI :" + obtenerFactibilidadService);
        return obtenerFactibilidadService;
    }
    
    public Boolean actualizarDatosFlexcube() {
        LOGGER.warning("Entra en actualzarDatosFlexcube");

        Map mapaDatos = new HashMap();
        Boolean aplicarEnvioCobroService = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try {
            //Se ejecuta servicio que actualiza el Monto de Ampliacion y los nuevos contratos en FLEXCUBE
            //de acuerdo a la regla de negocio RN_03
            operationBinding = bindings.getOperationBinding("AplicarEnvioCobroService");
            operationBinding.execute();
            
            if (!operationBinding.getErrors().isEmpty()) {
                // Recuperacion de excepciones
                LOGGER.warning("Se encontraron errores se mandan a pantalla.");
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else if(operationBinding.getResult() != null){
                mapaDatos = (Map)operationBinding.getResult();
                
                aplicarEnvioCobroService = (Boolean) mapaDatos.get("respuesta");
                if (!aplicarEnvioCobroService){
                    LOGGER.warning("Se obtiene error al ejecutar invocacion de servicio aplicarEnvioCobroService.");
                    JSFUtils.addFacesErrorMessage("Error, aplicarEnvioCobroService");
                }

                if (mapaDatos.get("MSG_ERROR") != null){
                    LOGGER.warning("El servicio regresa mensaje de error.");
                    JSFUtils.addFacesErrorMessage(mapaDatos.get("MSG_ERROR").toString());
                }

            }
        } catch (Exception e) {
            LOGGER.warning("***Error, Al ejecutar el operBinding AplicarEnvioCobroService.", e);
        }
        return aplicarEnvioCobroService;
    }

    public void validarMontoAmpliacionValueChangeListener(ValueChangeEvent valueChangeEvent) {
            LOGGER.warning("Entra en validarMontoAmpliacionValueChangeListener");
            EnvioAlCobroBean envioAlCobroBean =
                (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");
            BigDecimal montoAmpliacionVce = null;
            BigDecimal totalMontoAmpliacion = null;
            BigDecimal montoTotalLineas = new BigDecimal(0); //
            try {
                if (null != valueChangeEvent.getNewValue()) {
                    montoAmpliacionVce = (BigDecimal) valueChangeEvent.getNewValue();
                    LOGGER.warning("Valor del monto ingresado :" + montoAmpliacionVce);

                    BindingContainer bindings = ADFUtils.getBindingContainer(); //

                    LOGGER.warning("*Bindings atributos: " + bindings.getAttributeBindings()); //
                    LOGGER.warning("*Bindings atributos: " + bindings.getControlBindings()); //
                    LOGGER.warning("*Bindings atributos: " + bindings.getOperationBindings()); //
                      
                    montoTotalLineas = actualizarMontolTotalEnvio(montoAmpliacionVce);
                    envioAlCobroBean.setMotoTotalLineas(montoTotalLineas);
                    envioAlCobroBean.setTotalEnvioCobro(montoTotalLineas);
                                    
                    envioAlCobroBean.setMontoAmpliacion(montoAmpliacionVce);
                    modificarMontoAmpliacion(montoAmpliacionVce); 
                    
                    //Asigna Total envio al cobro
                    LOGGER.warning("totalMontoAmpliacion: " + envioAlCobroBean.getMontoAmpliacion());//
                    totalMontoAmpliacion = envioAlCobroBean.getMontoAmpliacion();
                    
                    LOGGER.warning("totalEnvioCobro actual: " + envioAlCobroBean.getTotalEnvioCobro());//
                    
                    envioAlCobroBean.setTotalEnvioCobro(totalMontoAmpliacion); //
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getRefrescarTreeTableEnvioCobroBinding());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getTotalEnvioCobroUIC()); 
                } else {
                    LOGGER.warning("Se setea el valor nulo.");
                    envioAlCobroBean.setMontoAmpliacion(montoAmpliacionVce);
                }
            } catch (Exception e) {
                LOGGER.warning("Error al modificar el valor.", e);
            }
        }
    
    public BigDecimal actualizarMontolTotalEnvio(BigDecimal nuevoMonto) {
                    LOGGER.warning("Inf, Inicia metodo actualizarMontolTotalEnvio");
                    EnvioAlCobroBean envioAlCobroBean = (EnvioAlCobroBean)JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");

                    LOGGER.warning("Monto de ampliacion de la linea: " + envioAlCobroBean.getIdLineaCreditoSeleccionada());
                    Long idLineaCreditoSeleccionada = envioAlCobroBean.getIdLineaCreditoSeleccionada();

                    CollectionModel treeModel = null;
                    treeModel = (CollectionModel)getTreeTableEnvioALCobroBind().getValue();

                    List < TreeEnvioAlCobro > lineasList = (List)treeModel.getWrappedData();

                    Long idContratoActivo = null;
                    Long idLinea = null;
                    String numeroLinea = null;
                    BigDecimal montoAmpliacion = new BigDecimal(0);

                    for (TreeEnvioAlCobro s: lineasList) {
                            LOGGER.warning("*Inf, \"S\" Monto Ampliacion: " + s.getMontoAmpliacion());
                            LOGGER.warning("*Inf, \"S\" Id: " + s.getId());
                            List < TreeEnvioAlCobro > nodos = s.getNodos();
                            if (s.getId() != null && s.getMontoAmpliacion() != null && s.getId() == idLineaCreditoSeleccionada) {
                                    s.setMontoAmpliacion(nuevoMonto);
                                    montoAmpliacion = montoAmpliacion.add(nuevoMonto);
                                    LOGGER.warning("*Inf, S 1ro: " + s.getMontoAmpliacion());
                            } else if (s.getId() != null && s.getMontoAmpliacion() != null) {
                                    s.setMontoAmpliacion(nuevoMonto);
                                    montoAmpliacion = montoAmpliacion.add(s.getMontoAmpliacion());
                                    LOGGER.warning("*Inf, S 2do: " + s.getMontoAmpliacion());
                            } else {
                                    LOGGER.warning("*Inf, Evaluacion de S no util.");
                            }

                            for (TreeEnvioAlCobro nodo: nodos) {
                                    LOGGER.warning("*Inf, \"NODO\" Monto Ampliacion: " + nodo.getMontoAmpliacion());
                                    LOGGER.warning("*Inf, \"NODO\" Id: " + nodo.getId());
                                    if (null != nodo.getId() && nodo.getMontoAmpliacion() != null && idLineaCreditoSeleccionada == nodo.getId()) {
                                            nodo.setMontoAmpliacion(nuevoMonto);
                                            montoAmpliacion = montoAmpliacion.add(nuevoMonto);

                                            LOGGER.warning("*Inf, NODO 1ro: " + s.getMontoAmpliacion());
                                            continue;
                                    }
                                    if (null != nodo.getId() && nodo.getMontoAmpliacion() != null && idLineaCreditoSeleccionada == nodo.getId()) {
                                            nodo.setMontoAmpliacion(nuevoMonto);

                                            montoAmpliacion.add(nodo.getMontoAmpliacion());
                                            LOGGER.warning("*Inf, NODO 2do: " + s.getMontoAmpliacion());
                                            continue;
                                    }
                                    LOGGER.warning("*Inf, Evaluacion de NODO no util.");
                            }
                    }
                     LOGGER.warning("*Inf, IdLinea seleccionada: " + idLinea);
                     LOGGER.warning("*Inf, Contrato seleccionado: " + idContratoActivo);
                     LOGGER.warning("Monto de ampliacion de la linea: " + montoAmpliacion);

                    
                     LOGGER.warning("*Inf, Termina metodo seleccionarRegistroTreeTableLineaContrato");
                     LOGGER.warning("Valor del contrato seleccionado :" + envioAlCobroBean.getIdContratoActivo());
                     return montoAmpliacion;
            }
    
    public Boolean validarLineaCreditoAgregada(){
        LOGGER.warning("Inicia metodo validarLineaCreditoAgregada.");
        Boolean resultado = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding iteratorBinding =
            (DCIteratorBinding) bindings.get("DatosLineaCreditoVOIterator");
        
        if(null != iteratorBinding){
            LOGGER.warning("Registros de Lineas de credito: " + iteratorBinding.getEstimatedRowCount());
            if(iteratorBinding.getEstimatedRowCount() > 0){
                resultado = Boolean.TRUE;
            }else{
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG02_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO"));
            }
        }else{
            LOGGER.warning("El iterador DatosLineaCreditoVOIterator es NULL.");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_VALIDACION") + ": validarLineaCreditoAgregada()-DatosLineaCreditoVOIterator es NULL.");
        }
        
        LOGGER.warning("Termina metodo validarLineaCreditoAgregada.");
        return resultado;
    }
    
    public Boolean validarMontoEscrituracionContraMontoTotal(){
        LOGGER.warning("Inicia metodo validarMontoEscrituracionContraMontoTotal.");
        Boolean resultado = Boolean.FALSE;
        BigDecimal montoEscriturado = null;
        BigDecimal montoTotalLineas = null;
        
        try{
            montoEscriturado = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoEscriturado");
        }catch(Exception e){
            LOGGER.warning("ERROR al recuperar el MontoEscriturado.", e);
        }
        
        try{
            montoTotalLineas = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoTotal");
        }catch(Exception e){
            LOGGER.warning("ERROR al recuperar el MontoTotal.", e);
        }
        
        LOGGER.warning("Monto escriturado: " + montoEscriturado + ", Monto total: " + montoTotalLineas);
        if(null != montoEscriturado && null != montoTotalLineas){
            if(montoTotalLineas.compareTo(montoEscriturado)==1){
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG06_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO"));
            }else{
                resultado = Boolean.TRUE;
            }
        }else{
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_VALIDACION") + ": validarMontoEscrituracionContraMontoTotal()-MontoEscriturado o MontoTotal son NULL.");
        }
        
        LOGGER.warning("Termina metodo validarMontoEscrituracionContraMontoTotal.");
        return resultado;
    }
    
    public Boolean validaDatosRequeridosCondicionesFinancieras(){
        LOGGER.warning("Entra en validaDatosRequeridosCondicionesFinancieras.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Map resultado = new HashMap<String, Object>();
        Boolean esValidado = Boolean.TRUE;
        Long idOperacion = null;
        try{
            idOperacion = getCodigoOperacion();
            operationBinding = bindings.getOperationBinding("buscarCondicionFinancieraPorIdContrato");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                resultado = (Map)operationBinding.getResult();
                esValidado = (Boolean)resultado.get("esValidado");
                if(!esValidado){
                    Iterator it = resultado.entrySet().iterator();

                    while (it.hasNext()) {
                        Map.Entry e = (Map.Entry)it.next();
                        if(null != e.getValue()){
                            String key = (String)e.getKey();
                            LOGGER.warning("Valor del KEY :" + key);
                            if(key.equalsIgnoreCase("esValidado")){
                                LOGGER.warning("El valor es Boolean");
                            }else{
                                LOGGER.warning("mensaje de error :" + (String)e.getValue());
                                JSFUtils.addFacesErrorMessage((String)e.getValue());
                            }
                            
                        }
                       }
                    it.remove();
                }
            }
        }catch(Exception e){
            esValidado = Boolean.FALSE;
            LOGGER.warning("Error al validar los campos.", e);
        }
        LOGGER.warning("La validacion de los campos es :" + esValidado);
        return esValidado;
    }
    public void setContenedorBtnCrearContrato(RichPanelGroupLayout contenedorBtnCrearContrato) {
        this.contenedorBtnCrearContrato = contenedorBtnCrearContrato;
    }

    public RichPanelGroupLayout getContenedorBtnCrearContrato() {
        return contenedorBtnCrearContrato;
    }

    public void setUiValorTasa(RichInputText uiValorTasa) {
        this.uiValorTasa = uiValorTasa;
    }

    public RichInputText getUiValorTasa() {
        return uiValorTasa;
    }

    public void setBotonActualizarUI(RichPanelGroupLayout botonActualizarUI) {
        this.botonActualizarUI = botonActualizarUI;
    }

    public RichPanelGroupLayout getBotonActualizarUI() {
        return botonActualizarUI;
    }

    public void setBotonActualizarBindingUI(RichButton botonActualizarBindingUI) {
        this.botonActualizarBindingUI = botonActualizarBindingUI;
    }

    public RichButton getBotonActualizarBindingUI() {
        return botonActualizarBindingUI;
    }

    public void setRefrescarTreeTableEnvioCobroBinding(RichPanelGroupLayout refrescarTreeTableEnvioCobroBinding) {
        this.refrescarTreeTableEnvioCobroBinding = refrescarTreeTableEnvioCobroBinding;
    }

    public RichPanelGroupLayout getRefrescarTreeTableEnvioCobroBinding() {
        return refrescarTreeTableEnvioCobroBinding;
    }
    public String obtenerDescripcionMoneda() {
        LOGGER.warning("Inside obtenerDescripcionMoneda.");
        ImplementacionPCTBean implementacionBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression("#{pageFlowScope.implementacionPCTBean}");

        Integer idTerminoMontoFormalizado = 12;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("obtenerMonedaTerminoPorId");
        operationBinding.getParamsMap().put("idTcaTermino", idTerminoMontoFormalizado);
        operationBinding.getParamsMap().put("idOperacion", implementacionBean.getIdOperacion());
        String descripcionMoneda = (String)operationBinding.execute();
        
        return descripcionMoneda;
    }
    
    public List onSuggestTasa(String string) {
        LOGGER.warning("*Inf, Into method onSuggestTasa");
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();;
        SelectItem element = null;
        //String moneda="USD";
        String monedaImplementar=null;
        monedaImplementar=obtenerDescripcionMoneda();
        LOGGER.warning("Moneda a implementar: "+monedaImplementar);
        
        List<String> listaObtenida= new ArrayList<String>();
        BindingContainer bindingsIA = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIA = null;

            operationBindingIA = bindingsIA.getOperationBinding("cargarLista");
            operationBindingIA.getParamsMap().put("nombre", string);
            operationBindingIA.getParamsMap().put("moneda", monedaImplementar);
            listaObtenida=(ArrayList<String>)operationBindingIA.execute();
        
            if(null!=listaObtenida){
                    if(listaObtenida.size()>0){
                        for(String valor :listaObtenida){
                                LOGGER.warning("Registro encontrado: " +valor);
                                element=new SelectItem(valor);
                                resultItems.add(element);
                            }
                        }
                }

      
        return resultItems;
    }
    
    public List onSuggestNacionalidad(String string) {
        LOGGER.warning("*Inf, Into method onSuggestNacionalidad");
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();
        SelectItem element = null;
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("PaisDesc");         
        
         RowSetIterator iter = list.getListRowSetIterator();                  
         iter.reset();
         Row row = null;
            
        LOGGER.warning("*Inf, Num de cuentas recuperado para la operacion : "+iter.getAllRowsInRange().length);
         if(null != iter){            
            
            for(int i = 0; i< iter.getAllRowsInRange().length; i++){
                 LOGGER.warning("*Inf, Iterando el row "+i+"...");
                  row = iter.getRowAtRangeIndex(i);
                    if(null != row){
                       String value = (String) row.getAttribute("Descripcion");
                       if(null != string &&  null != value){
                             LOGGER.warning("*Inf, comparando String "+string+" con elemento "+value+" en la lista");
                          if(value.toUpperCase().contains(string.toUpperCase())){
                                 LOGGER.warning("*Inf, ok cadena a buscar  "+string
                                                       +" coincide en el inicio con el valor : "+value+" en la lista agregando a respuesta..");
                               element = new SelectItem(value);
                               resultItems.add(element);
                             }
                         }else{
                               LOGGER.warning("*Inf, Important! String "+string+" valor en lista "+value);
                           }
                    }
                }                
            
        }else{
          LOGGER.warning("*Inf, Important! Iterador NULL");
          element = new SelectItem("");
          resultItems.add(element);
        }
         iter.closeRowSetIterator();
      
        return resultItems;
    }
    
    public List onSuggestInicioNacionalidad(String string) {
        LOGGER.warning("*Inf, Into method onSuggestInicioNacionalidad");
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();;
        SelectItem element = null;
        
        List<String> listaObtenida= new ArrayList<String>();
        BindingContainer bindingsIA = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIA = null;

            operationBindingIA = bindingsIA.getOperationBinding("lista");
            operationBindingIA.getParamsMap().put("nombre", string);
            listaObtenida=(ArrayList<String>)operationBindingIA.execute();
        
            if(null!=listaObtenida){
                    if(listaObtenida.size()>0){
                        for(String valor :listaObtenida){
                                LOGGER.warning("Registro encontrado: " +valor);
                                element=new SelectItem(valor);
                                resultItems.add(element);
                            }
                        }
                }

      
        return resultItems;
    }
    
    public void cambioDeNacionalidad(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Cambio de nacionalidad, Nuevo : "+ valueChangeEvent.getNewValue());
        String valor=null;
        Boolean guardo=Boolean.FALSE;
        if(null!= valueChangeEvent.getNewValue()){
                LOGGER.warning(" Nuevo valor: "+ valueChangeEvent.getNewValue());
                valor= valueChangeEvent.getNewValue().toString();
                BindingContainer bindingsIA = ADFUtils.getBindingContainer();
                OperationBinding operationBindingIA = null;

                    operationBindingIA = bindingsIA.getOperationBinding("cambioPais");
                    operationBindingIA.getParamsMap().put("nombrePais", valor);
                    guardo=(Boolean)operationBindingIA.execute();
                if (!operationBindingIA.getErrors().isEmpty()) {
                     LOGGER.warning("Cambio no realizado : ");

                }
            }
        }
    
    public void cambioDeNacionalidad2(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Cambio de nacionalidad, Nuevo : "+ valueChangeEvent.getNewValue());
            String valor=null;
            Boolean guardo=Boolean.FALSE;
            if(null!= valueChangeEvent.getNewValue()){
                valor=(String)valueChangeEvent.getNewValue();
                    BindingContainer bindingsIA = ADFUtils.getBindingContainer();
                    OperationBinding operationBindingIA = null;

                        operationBindingIA = bindingsIA.getOperationBinding("cambioPais");
                        operationBindingIA.getParamsMap().put("nombrePais", valor);
                        guardo=(Boolean)operationBindingIA.execute();
                    if (!operationBindingIA.getErrors().isEmpty()) {
                         LOGGER.warning("Cambio no realizado : ");

                    }
                }
        }

    public void setBotonActualizarEnvioAlGastoUI(RichButton botonActualizarEnvioAlGastoUI) {
        this.botonActualizarEnvioAlGastoUI = botonActualizarEnvioAlGastoUI;
    }

    public RichButton getBotonActualizarEnvioAlGastoUI() {
        return botonActualizarEnvioAlGastoUI;
    }
    
    @SuppressWarnings("unchecked")
    private boolean cuentaConContratoCreadoPorImpl() {
        LOGGER.log(ADFLogger.WARNING, "entrando en cuentaConContratoCreadoPorImpl.");
        EnvioAlCobroBean envioAlCobroBean =
            (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");
        Boolean seCuentaConContratoCreado = Boolean.FALSE;
        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Long idOperacion = getCodigoOperacion();
        Integer totalLineasRecuperadasTreeTable = Integer.valueOf(0);
        Integer totalLineasObtenidasConsultaSQL = Integer.valueOf(0);
        
        try{
            ArrayList<Long> lineasRecuperadas = new ArrayList<Long>();
            lineasRecuperadas = envioAlCobroBean.getLineasTreeTable();
            if(lineasRecuperadas != null){
                if(lineasRecuperadas.size() > 0){
                    LOGGER.warning("Valor lineasRecuperadas.size(): " + lineasRecuperadas.size());
                    for(int i=0; i < lineasRecuperadas.size(); i++){
                        LOGGER.warning("lineasRecuperadas posicion i=" + i + ", idLinea: " + lineasRecuperadas.get(i));
                    }
                    totalLineasRecuperadasTreeTable = lineasRecuperadas.size();
                    LOGGER.warning("Valor totalLineasRecuperadas: " + totalLineasRecuperadasTreeTable);
                }
                else{
                    LOGGER.warning("El valor de las lineasRecuperadas es 0, no contiene ningun elemto la lista");
                }
            }
            else{
                LOGGER.warning("El valor de las lineasRecuperas del treeTable es null");
            }
        }
        catch(Exception e){
            LOGGER.warning("ERROR al ejecutar el metodo cuentaConContratoCreadoPorImpl");
        }
        
        try {
            operationBinding = bindings.getOperationBinding("cadaLineaCuentaContratoCreadoPorImpl");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.execute();
            
            totalLineasObtenidasConsultaSQL = (Integer)operationBinding.getResult();
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING, "Error al ejecutar actualizarDatosEscrituracion :" + e);
        }
        
        if(totalLineasRecuperadasTreeTable.compareTo(totalLineasObtenidasConsultaSQL) == 0){
            seCuentaConContratoCreado = Boolean.TRUE;
        }
        else{
            seCuentaConContratoCreado = Boolean.FALSE;
        }
        LOGGER.warning("Valor a retornar seCuentaConContratoCreado: " + seCuentaConContratoCreado);
        LOGGER.log(ADFLogger.WARNING, "finalizando cuentaConContratoCreadoPorImpl.");
        return seCuentaConContratoCreado;
    }
    
    private boolean generarFechaDisponibilidadFondosPCT() {
        BindingContainer bindingContainer = ADFUtils.getBindingContainer();
        OperationBinding operationBinding;
        boolean generacionExitosa = Boolean.FALSE;

        try {
            operationBinding = bindingContainer.getOperationBinding("generarFechaDisponibilidadFondosPCT");
            operationBinding.execute();
            
            if (!operationBinding.getErrors().isEmpty()) {
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else {
                generacionExitosa = (Boolean)operationBinding.getResult();
            }
        } catch (Exception e) {
            LOGGER.warning("Error al generar las fechas de disponibilidad de fondos.", e);
        }
        
        return generacionExitosa;
    }
    
    public boolean generarFechasVencimientoPCT() {
        boolean generadasConExito = Boolean.TRUE;
        
        BindingContainer bindingContainer = ADFUtils.getBindingContainer();
        OperationBinding operationBinding;

        try {
            operationBinding = bindingContainer.getOperationBinding("generarFechasVencimientoPCT");
            operationBinding.execute();
            
            if (!operationBinding.getErrors().isEmpty()) {
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else {
                generadasConExito = (Boolean) operationBinding.getResult();
            }
        } catch (Exception e) {
            LOGGER.warning("Error al generar las fechas de disponibilidad de fondos.", e);
        }
        
        return generadasConExito;
    }

    public void setCodigoTasaUI(RichInputComboboxListOfValues codigoTasaUI) {
        this.codigoTasaUI = codigoTasaUI;
    }

    public RichInputComboboxListOfValues getCodigoTasaUI() {
        return codigoTasaUI;
    }

    public void setSpreadUI(RichInputText spreadUI) {
        this.spreadUI = spreadUI;
    }

    public RichInputText getSpreadUI() {
        return spreadUI;
    }

    public void actualizarRegionDesembolsos(ActionEvent actionEvent) {
        // Add event code here...
        //regionContratoUIC
        FacesContext fContext = FacesContext.getCurrentInstance();
        getRegionContratoUIC().refresh(fContext);
        //AdfFacesContext.getCurrentInstance().addPartialTarget(getRegionContratoUIC());
    }

    public void setTotalEnvioCobroUIC(RichInputText totalEnvioCobroUIC) {
        this.totalEnvioCobroUIC = totalEnvioCobroUIC;
    }

    public RichInputText getTotalEnvioCobroUIC() {
        return totalEnvioCobroUIC;
    }

    public void setOnInitForm(RichOutputText onInitForm) {
        this.onInitForm = onInitForm;
    }

    public RichOutputText getOnInitForm() {

        EnvioAlCobroBean envioAlCobroBean =
            (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");
        
        if (envioAlCobroBean.getEsMostrarEnLiquidarContrato()) {
            BigDecimal totalMontoAmpliacion = null;

            //Asigna Total envio al cobro
            LOGGER.warning("totalMontoAmpliacion: " + envioAlCobroBean.getMontoAmpliacion());
            totalMontoAmpliacion = envioAlCobroBean.getMontoAmpliacion();

            LOGGER.warning("totalEnvioCobro actual: " + envioAlCobroBean.getTotalEnvioCobro());

            envioAlCobroBean.setTotalEnvioCobro(totalMontoAmpliacion);
            AdfFacesContext.getCurrentInstance().addPartialTarget(getTotalEnvioCobroUIC());
        }
        
        return onInitForm;
    }

    public void fechaInteresesValueChangeListener(ValueChangeEvent vce) {
        LOGGER.warning("Into fechaInteresesValueChangeListener.");
        LOGGER.warning("fechaFlexAnterior: " + vce.getOldValue());
        LOGGER.warning("fechaFlexActual: " + vce.getNewValue());
        
        AttributeBinding fechaIntereses;
        
        Date fechaFlexCurrent = (Date) vce.getNewValue();
        
        refreshTablaContratos(fechaFlexCurrent);
        
        fechaIntereses = ADFUtils.findControlBinding("FechaCalculoInteres");
        
        if(fechaIntereses != null) {
                LOGGER.warning("fechaIntereses desde binding: " + fechaIntereses);
        }
        fechaIntereses.setInputValue(fechaFlexCurrent);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPglRegionContratoUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getRefrescarTreeTableEnvioCobroBinding());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaInteresesUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getTotalEnvioCobroUIC());
    }
    
    public void guardarFechaFlexCurrent(Date fechaFlexCurrent) {
        LOGGER.warning("Into guardarFechaFlexCurrent.");
        
        BindingContainer bindings = getBindings();      
        
        OperationBinding operBinding = bindings.getOperationBinding("actualizarFechaFlexStatic");                    
        operBinding.getParamsMap().put("fechaFlexCurrent", fechaFlexCurrent);
        operBinding.execute();
    }
    
    public void refreshTablaContratos(Date fechaFlexCurrent) {
        LOGGER.warning("Into refreshTablaContratos.");
        
        EnvioAlCobroBean envioAlCobroBean =
            (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");
        
        Long idOperacion = envioAlCobroBean.getIdOperacion();
        
        envioAlCobroBean.construirTreeActual(idOperacion, fechaFlexCurrent);
        getTotalEnvioCobroUIC().setValue(envioAlCobroBean.getTotalEnvioCobro());
        
    }

    public void setFechaInteresesUIC(RichInputDate fechaInteresesUIC) {
        this.fechaInteresesUIC = fechaInteresesUIC;
    }

    public RichInputDate getFechaInteresesUIC() {
        
        return fechaInteresesUIC;
    }
   /*
    * Creacion de validacion de monto presupuestado con la sumatoria de lineas de creditos
    * jenamorado 30/08/2021
    * */
    public Boolean validarMontoPresupuestadoContraMontoTotal() {
        LOGGER.warning("Inicia metodo validarMontoPresupuestadoContraMontoTotal.");
        Boolean resultado = Boolean.FALSE;
        BigDecimal montoPresupuestado = BigDecimal.ZERO;
        BigDecimal montoTotalLineasPendiente = null;
        BigDecimal montoTotalLineasAplicado = null;
        BigDecimal montoTotal = null;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(CARACTER_COMA);
        symbols.setDecimalSeparator(CARACTER_PUNTO);

       
        DecimalFormat formato = new DecimalFormat(FORMATO_MONTO_LINEA);
         formato.setParseBigDecimal(true);
       
        String montoPresupuestadoFormateado;
        String montoTotalLineasAplicadoFormateado;
        String montoTotalLineasPendienteFormateado;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        listaAdvertencias = new ArrayList<String>();
        try {

            BindingContainer bindingsIniciarAdquision = ADFUtils.getBindingContainer();
            DCIteratorBinding iteratorBinding =
                (DCIteratorBinding) bindingsIniciarAdquision.get("LoteImplementacionVOIterator");

            Row currentRow = iteratorBinding.getCurrentRow();
            if (currentRow != null) {
                // montoEscriturado = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoEscriturado");
                montoPresupuestado = (BigDecimal) currentRow.getAttribute("MontoPresupuestado");
                LOGGER.warning(" montoPresupuestado: " + montoPresupuestado);
            }
        } catch (Exception e) {
            LOGGER.warning("ERROR al recuperar el montoPresupuestado.", e);
        }

        try {
            DCIteratorBinding DatosLineaCreditoVo = null;
            DatosLineaCreditoVo = ADFUtils.getDCBindingContainer().findIteratorBinding("DatosLineaCreditoVOIterator");
            montoTotalLineasPendiente =
                (BigDecimal) DatosLineaCreditoVo.getRowAtRangeIndex(0).getAttribute("MontoTotalPendiente");
            montoTotalLineasAplicado =
                (BigDecimal) DatosLineaCreditoVo.getRowAtRangeIndex(0).getAttribute("MontoTotalAplicado");
            montoTotal = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoTotal");
            LOGGER.warning(" MontoTotalPendiente: " + montoTotalLineasPendiente);
            LOGGER.warning(" MontoTotal: " + montoTotal);
        } catch (Exception e) {
            LOGGER.warning("ERROR al recuperar el MontoTotalPendiente.", e);
        }

        LOGGER.warning("Monto Presupuestado: " + montoPresupuestado + ", Monto total pendiente: " +
                       montoTotalLineasPendiente);
        if (null != montoPresupuestado && null != montoTotalLineasPendiente) {
            if (montoPresupuestado.compareTo(montoTotalLineasPendiente) == -1) {
                // JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG06_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO"));
                listaAdvertencias.add(getStringFromBundle("MSG01_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO_ADV"));
                
            
                montoPresupuestadoFormateado = formato.format(montoPresupuestado);

                listaAdvertencias.add(" El monto Presupuestado es:");
                listaAdvertencias.add("  " + montoPresupuestadoFormateado);

                montoTotalLineasPendienteFormateado = formato.format(montoTotalLineasPendiente);

                listaAdvertencias.add(" El monto pendiente es: ");
                listaAdvertencias.add("  " + montoTotalLineasPendienteFormateado);

                LOGGER.warning(getStringFromBundle("MSG01_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO_ADV"));

            }
            resultado = Boolean.TRUE;
        } else {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_VALIDACION") +
                                          ": validarMontoPresupuestadoContraMontoTotal()-MontoPresupuestado o MontoTotal son NULL.");
        }

        LOGGER.warning("Termina metodo validarMontoPresupuestadoContraMontoTotal.");
        return resultado;
    }
}
