package pa11imppctght.bean;

import java.io.IOException;
import java.io.Serializable;

import java.io.StringWriter;

import java.math.BigDecimal;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

public class ImplementacionPCTBean extends FenixPanelBean implements Serializable {
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 1L;
    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(ImplementacionPCTBean.class);
    public static final String BEAN_EXPRESSION = "#{pageFlowScope.implementacionPCTBean}";
    private static final String CODIGO_TAREA = "CodigoTarea";
    private static final String CODIGO_OPERACION = "CodigoOperacion";
    private static final String CODIGO_IMPLEMENTACION = "IdImplementacion"; /* Utilizado para recuperar el idSolicitud*/
    private static final String CODIGO_LOTE_IMPLEMENTACION = "IdLote";
    private static final String RESPONSABLE_OPERACION = "ResponsableOperacion";
    private static final String CODIGO_PROCESO_BPM = "CodigoProceso";
    private static final String INSTANCIA_PROCESO = "InstanciaProceso";
    private static final String CODIGO_PRODUCTO = "CodigoProducto";
    private static final String REQUIERE_FIN_CONCURSO = "requiereFinCurso";
    private static final String GESTIONA_CLIENTE = "gestionaCliente";
    private static final String TIPO_CONCURSO = "tipoConcurso";
    private static final String ORDEN_INICIO = "ordenInicio";
    private static final String NUMERO_SERIE_DOCUMENTO = "IdFlujo";
    private static final String TIPO_IMPLEMENTACION = "tipoImplementacion";
    private static final int ADJUDICADO = 1;
    private static final int ANULADO = 2;
    private static final int DESIERTO = 3;
    private static final int FRACASADO = 4;
    private static final int NUEVO = 5;
    private String idImplementacion;
    private String codigoTarea;
    private String codigoOperacion;
    private String idLote;
    private String responsableOperacion;
    private String idProcesoBPM;
    private String instanciaProceso;
    private String codigoProducto;
    private Boolean requiereFinConcurso;
    private String tipoConcurso;
    private String ordenInicio;
    private String tipoimplementacion;
    private Integer idProducto;
    private Long idContrato;
    private Long idOperacion;
    private Integer idContratoPorLote;
    private Boolean isGestionaCliente;
    private String gestionaClienteContratacion = null;
    private oracle.jbo.domain.Number numeroSerieDocumento;
    
    public static final String BUNDLE = "pa11imppctght.PA11ImpPCTGHTBundle";
    private String finalizarTarea;
    
    private Boolean habilitaBotonAgregar = Boolean.FALSE;
    private Boolean habilitaBotonGuardarEliminar = Boolean.TRUE;
    private Boolean soloLectura = Boolean.FALSE;
    
    private Boolean muestraPanelLoteImplementacion = Boolean.FALSE;
    private RichInputText insertIdLote;
    
    private Long varIdLoteReadOnly = null;
    private Boolean muestraSeccionParticipantes = Boolean.FALSE;
    private Boolean muestraSeccionAdjudicatario = Boolean.FALSE;
    private Long varIdParticipanteReadOnly = null;
    private Long varIdAdjudicatarioReadOnly = null;
    
    private Boolean habilitaBotonAgregarAdjudicatario = Boolean.FALSE;
    private Boolean habilitaBotonGuardarEliminarAdjudicatario = Boolean.TRUE;
    
    private Integer registrarResultadoAdquisicion = null;
    
    private BigDecimal montoEscriturado = BigDecimal.ZERO;
    private Integer idMontoEscriturado = null;
    private Boolean salidaRegistrarResultadoAdquisicion = null;
    
    public String mensajeError = null;
    
    private Boolean habilitarBtnValidarFT05 = Boolean.FALSE;
    private Boolean esValorNulo = Boolean.FALSE;
    private Long idLineaCredito = null;
    
    public ImplementacionPCTBean() {
        super();
    }

    /**Gets the payload information and initialize the bean atributes
     * @throws WorkflowException
     * @throws IOException
     */
    public void getPayloadInformation()throws WorkflowException, IOException {
        LOGGER.entering(this.getClass().getName(),"getPayloadInformation");
        
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();
        NodeList n1;
        
        if (LOGGER.isWarning()) {
            //get the payload as a simple string, useful for debugging
            java.io.StringWriter writer = new StringWriter();
            xmlPayload.print(writer);
            String payloadAsString = writer.toString();
            LOGGER.log(ADFLogger.WARNING, payloadAsString);
        }        

        n1 = xmlPayload.getElementsByTagName(CODIGO_TAREA);
        if (n1.getLength() > 0) {
            codigoTarea = n1.item(0).getTextContent();
        }
        
        n1 = xmlPayload.getElementsByTagName(CODIGO_OPERACION);
        if (n1.getLength() > 0) {
            codigoOperacion = n1.item(0).getTextContent();
            try{
                setIdOperacion(new Long(codigoOperacion));
            }catch(Exception e){
                LOGGER.warning("ERROR al convertir CODIGO_TAREA.");
            }
        }
        
        n1 = xmlPayload.getElementsByTagName(CODIGO_IMPLEMENTACION);
        if (n1.getLength() > 0) {
            idImplementacion = n1.item(0).getTextContent();
        }
        
        n1 = xmlPayload.getElementsByTagName(CODIGO_LOTE_IMPLEMENTACION);
        if (n1.getLength() > 0) {
            idLote = n1.item(0).getTextContent();
        }
        
        n1 = xmlPayload.getElementsByTagName(RESPONSABLE_OPERACION);
        if (n1.getLength() > 0) {
            responsableOperacion = n1.item(0).getTextContent();
        }
        
        n1 = xmlPayload.getElementsByTagName(CODIGO_PROCESO_BPM);
        if (n1.getLength() > 0) {
            idProcesoBPM = n1.item(0).getTextContent();
        }
        
        n1 = xmlPayload.getElementsByTagName(INSTANCIA_PROCESO);
        if (n1.getLength() > 0) {
            instanciaProceso = n1.item(0).getTextContent();
        }
        
        n1 = xmlPayload.getElementsByTagName(CODIGO_PRODUCTO);
        if (n1.getLength() > 0) {
            codigoProducto = n1.item(0).getTextContent();;
        }
        n1 = xmlPayload.getElementsByTagName(REQUIERE_FIN_CONCURSO);
        if (n1.getLength() > 0) {
            requiereFinConcurso = Boolean.valueOf(n1.item(0).getTextContent());;
        }
        n1 = xmlPayload.getElementsByTagName(TIPO_CONCURSO);
        if (n1.getLength() > 0) {
            tipoConcurso = n1.item(0).getTextContent();;
        }
        n1 = xmlPayload.getElementsByTagName(ORDEN_INICIO);
        if (n1.getLength() > 0) {
            ordenInicio = n1.item(0).getTextContent();;
        }
        n1 = xmlPayload.getElementsByTagName(TIPO_IMPLEMENTACION);
        if (n1.getLength() > 0) {
            tipoimplementacion = n1.item(0).getTextContent();;
        }
        
        n1 = xmlPayload.getElementsByTagName(GESTIONA_CLIENTE);
        if (n1.getLength() > 0) {
            //gestionaClienteContratacion = n1.item(0).getTextContent();
            isGestionaCliente = Boolean.valueOf(n1.item(0).getTextContent());
            if(isGestionaCliente == null){
                LOGGER.warning("Indicador de Cliente gestiona contratacion no recibido, se toma valor por defecto");
                isGestionaCliente = Boolean.FALSE;
            }
        }else{
            LOGGER.warning("Indicador de Cliente gestiona contratacion no recibido, se toma valor por defecto");
            isGestionaCliente = Boolean.FALSE;
        }
        
        n1 = xmlPayload.getElementsByTagName(NUMERO_SERIE_DOCUMENTO);
        if (n1.getLength() > 0){
            try {
                setNumeroSerieDocumento(new oracle.jbo.domain.Number(n1.item(0).getTextContent()));
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR, "Error al inicializar el valor de IdFlujo: " + e.getMessage());
            }
        }
        LOGGER.warning("CODIGO_TAREA: "+codigoTarea);
        LOGGER.warning("CODIGO_OPERACION: "+codigoOperacion);
        LOGGER.warning("CODIGO_IMPLEMENTACION: "+idImplementacion);
        LOGGER.warning("CODIGO_LOTE_IMPLEMENTACION: "+idLote);
        LOGGER.warning("RESPONSABLE_OPERACION: "+responsableOperacion);
        LOGGER.warning("CODIGO_PROCESO_BPM: "+idProcesoBPM);
        LOGGER.warning("INSTANCIA_PROCESO: "+instanciaProceso);
        LOGGER.warning("CODIGO_PRODUCTO: "+codigoProducto);
        LOGGER.warning("ID_OPERACION: "+idOperacion);
        //Se agregan nuevos valores obtenidos del payload
        LOGGER.warning("GESTIONA_CLIENTE: "+isGestionaCliente);
        LOGGER.warning("REQUIERE_FIN_CONCURSO: "+requiereFinConcurso);
        LOGGER.warning("TIPO_CONCURSO: "+tipoConcurso);
        LOGGER.warning("ORDEN_INICIO: "+ordenInicio);
        LOGGER.warning("TIPO_IMPLEMENTACION: "+tipoimplementacion);
        LOGGER.warning("NUMERO_SERIE_DOCUMENTO (Id flujo): "+getNumeroSerieDocumento());
        
        int idTarea;
        try{
            idTarea = Integer.valueOf(codigoTarea);
        }
        catch(Exception e){
            LOGGER.warning("En el payload no se recupero el valor codigoTarea");
            idTarea = Integer.valueOf(0);
        }
        
        switch(idTarea){
        //Tareas Implementacion PCT - Core
        case FenixConstants.PA11_SOLICITAR_CONTRATACION_CONSULTORIA:
            LOGGER.warning("Ingresa case Solicitar Contratacion");
            if(requiereFinConcurso){
                if(idLote != null &&
                   !"".equals(idLote) && !idLote.equals("0")){
                    LOGGER.warning("Ingresa if, se hace llamado al metodo obtenerIdImplementacion");
                    obtenerIdImplementacion();
                }
                else{
                    codigoTarea = "0";
                    LOGGER.warning("Ingresa else, el valor del id Lote es cero");
                    mensajeError = "Error: el id de Lote es cero";
                }
            }
            else{
                LOGGER.warning("requiereFinConcurso es false, se continua con el flujo sin realizar busquedas.");
            }
            break;
        case FenixConstants.PA11_GESTIONAR_CONTRATACION_CONSULTORIA:
            LOGGER.warning("Ingresa case Gestionar Contratacion");
            break;
        case FenixConstants.PA11_ELABORAR_DOCUMENTO_BASE:
            LOGGER.warning("Ingresa case Elaborar documento");
            break;
        case FenixConstants.PA11_COMPLETAR_DOCUMENTO_BASE:
            LOGGER.warning("Ingresa case Completar documento");
            break;
        case FenixConstants.PA11_VALIDAR_DOCUMENTO_BASE:
            LOGGER.warning("Ingresa case Validar documento");
            break;
        case FenixConstants.PA11_INICIAR_ADQUISICION:
            LOGGER.warning("Ingresa case Iniciar adquisicion");
            break;
        case FenixConstants.PA11_REGISTRAR_RESULTADO_ADQUISICION:
            LOGGER.warning("Ingresa case Registrar resultado adquisicion");
            validarObtenerIdImplementacion(idImplementacion);
            break;
        case FenixConstants.PA11_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO:
            LOGGER.warning("Ingresa case Registrar orden de inicio y datos de la linea de credito");
            validarObtenerIdImplementacion(idImplementacion);
            break;
        case FenixConstants.PA11_VALIDAR_DATOS_LINEA_CREDITO:
            LOGGER.warning("Ingresa case Validar datos de la linea de credito");
            validarObtenerIdImplementacion(idImplementacion);
            break;
        //Tareas Implementacion PCT - Envio al Gasto
        case FenixConstants.PA11_SOLICITUD_ENVIO_GASTO:
            LOGGER.warning("Ingresa case Solicitud envio al gasto");
            break;
        case FenixConstants.PA11_VALIDAR_SOLICITUD:
            LOGGER.warning("Ingresa case Validar solicitud envio al gasto");
            break;
        case FenixConstants.PA11_LIQUIDAR_DESEMBOLSO:
            LOGGER.warning("Ingresa case Liquidar desembolsos");
            break;
        //Tareas Implementacion PCT - Envio al Cobro
        case FenixConstants.PA11_VALIDAR_TASA_FECHA:
            LOGGER.warning("Ingresa case Validar tasa fecha");
            break;
        case FenixConstants.PA11_REALIZAR_AJUSTES_TASA_FECHA:
            LOGGER.warning("Ingresa case Realizar ajustes tasa fecha");
            break;
        case FenixConstants.PA11_ENVIAR_AL_COBRO:
            LOGGER.warning("Ingresa case Enviar al cobro");
            LOGGER.warning("Valor idImplementacion obtenido del payload: " + idImplementacion);
            break;
        case FenixConstants.PA11_VALIDAR_ENVIO_COBRO:
            LOGGER.warning("Ingresa case Validar envio al cobro");
            LOGGER.warning("Valor idImplementacion obtenido del payload: " + idImplementacion);
            break;
        case FenixConstants.PA11_LIQUIDAR_DESEMOBOLSOS_ANTERIORES:
            LOGGER.warning("Ingresa case Liquidar desembolsos anteriores");
            LOGGER.warning("Valor idImplementacion obtenido del payload: " + idImplementacion);
            break;
        }
        
        LOGGER.exiting(this.getClass().getName(),"getPayloadInformation");
    }
    
    public void validarObtenerIdImplementacion(String idImplementacion){
        if(idImplementacion != null &&
           !"".equals(idImplementacion)){
            
            Long lngIdImplemetacion = null;
            try{
                lngIdImplemetacion = Long.parseLong(idImplementacion); 
            }catch(Exception e){
                LOGGER.severe("El valor recibido de Id Payload no es numerico", e);
                //JSFUtils.addFacesErrorMessage("El valor recibido de Id Payload no es numerico");
                codigoTarea = "0";
            }
            
            if(lngIdImplemetacion != null &&
               lngIdImplemetacion == 0){
                LOGGER.warning("El Id de Implementacion es cero por lo que se considera consultar el Id " +
                    "Implementacion correspondiente al lote");
                obtenerIdImplementacion();
            }else{
                if(lngIdImplemetacion < 0){
                    LOGGER.severe("El Id de Implementacion no es valido, su valor es menor a 0");
                    //JSFUtils.addFacesErrorMessage("El Id de Implementacion no es valido, su valor es menor o igual a 0");
                    codigoTarea = "0";
                }
            }
        }else{
            obtenerIdImplementacion();
        }
    }
    
    public void obtenerIdImplementacion(){
        String loteCero="0";
        LOGGER.warning("Entra en obtenerIdImplementacion");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long implementacionId = null;
        String implementacionIdAux = null;
        try{
            operationBinding = bindings.getOperationBinding("obtenerIdImplementacionByIdLoteImplementacion");
            operationBinding.getParamsMap().put("idLoteImplementacion", new Long(idLote));
            if(null != idLote){
               // if(idLote.equalsIgnoreCase(FenixConstants.VALOR_CERO_CADENA)){
                if(idLote.equalsIgnoreCase(loteCero)){
                        LOGGER.warning("El id Lote es cero, no se ejecuta metodo para obtener el idImplementacion");
                        mensajeError = "Error: El id Lote es cero, no se ejecuta metodo para obtener el idImplementacion";
                        codigoTarea = "0";
                    }
                else{
                        operationBinding.execute();
                    }

            }else{
                LOGGER.warning("El id Lote es nulo, no se ejecuta metodo para obtener el idImplementacion");
                mensajeError = "Error: El id Lote es nulo, no se ejecuta metodo para obtener el idImplementacion";
                codigoTarea = "0";
            }
            
            if(null != operationBinding.getResult()){
                implementacionId = (Long)operationBinding.getResult();
                implementacionIdAux = String.valueOf(implementacionId);
                idImplementacion = implementacionIdAux;
                LOGGER.warning("Valor del id de Implementacion obtenido :" + idImplementacion);
            }else{
                LOGGER.warning("No se obtuvo el idImplementacion.");
            }
        }catch(Exception e){
            LOGGER.warning("Error en obtenerIdImplementacion.", e);
        }
    }

    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public String getCodigoTarea() {
        return codigoTarea;
    }


    public void setCodigoOperacion(String codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }
    
    public String getCodigoOperacion() {
        return codigoOperacion;
    }
    
    public void setFinalizarTarea(String finalizarTarea) {
        this.finalizarTarea = finalizarTarea;
    }

    public String getFinalizarTarea() {
        return finalizarTarea;
    }

    public void setIdImplementacion(String idImplementacion) {
        this.idImplementacion = idImplementacion;
    }

    public String getIdImplementacion() {
        return idImplementacion;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
    }

    public String getIdLote() {
        return idLote;
    }

    private String getStringFromBundle(String psKey)
    {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (resourceBundle != null)
        {
            txt = resourceBundle.getString(psKey);
        }

        return txt;
      }

    public void setHabilitaBotonAgregar(Boolean habilitaBotonAgregar) {
        this.habilitaBotonAgregar = habilitaBotonAgregar;
    }

    public Boolean getHabilitaBotonAgregar() {
        return habilitaBotonAgregar;
    }

    public void setHabilitaBotonGuardarEliminar(Boolean habilitaBotonGuardarEliminar) {
        this.habilitaBotonGuardarEliminar = habilitaBotonGuardarEliminar;
    }

    public Boolean getHabilitaBotonGuardarEliminar() {
        return habilitaBotonGuardarEliminar;
    }

    public void setHabilitaBotonAgregarAdjudicatario(Boolean habilitaBotonAgregarAdjudicatario) {
        this.habilitaBotonAgregarAdjudicatario = habilitaBotonAgregarAdjudicatario;
    }

    public Boolean getHabilitaBotonAgregarAdjudicatario() {
        return habilitaBotonAgregarAdjudicatario;
    }

    public void setHabilitaBotonGuardarEliminarAdjudicatario(Boolean habilitaBotonGuardarEliminarAdjudicatario) {
        this.habilitaBotonGuardarEliminarAdjudicatario = habilitaBotonGuardarEliminarAdjudicatario;
    }

    public Boolean getHabilitaBotonGuardarEliminarAdjudicatario() {
        return habilitaBotonGuardarEliminarAdjudicatario;
    }

    public void setSoloLectura(Boolean soloLectura) {
        this.soloLectura = soloLectura;
    }

    public Boolean getSoloLectura() {
        return soloLectura;
    }

    public void setMuestraPanelLoteImplementacion(Boolean muestraPanelLoteImplementacion) {
        this.muestraPanelLoteImplementacion = muestraPanelLoteImplementacion;
    }

    public Boolean getMuestraPanelLoteImplementacion() {
        return muestraPanelLoteImplementacion;
    }
    
    public void requiereLotesValueChangeListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        LOGGER.log(ADFLogger.WARNING, "INTO requiereLotesValueChangeListener.");
        try{
            valueChangeEvent.getComponent().processSaveState(FacesContext.getCurrentInstance());
            Boolean bandera = (Boolean)valueChangeEvent.getNewValue();
            LOGGER.warning("Valor obteneido de requiereLotes: " + bandera);
            
            if(null != bandera)
                if(bandera == true)
                    muestraPanelLoteImplementacion = Boolean.TRUE;
                else
                    muestraPanelLoteImplementacion = Boolean.FALSE;
            else
                muestraPanelLoteImplementacion = Boolean.FALSE;
            
            LOGGER.warning("Valor obteneido de bandera de requiereLotes: " + muestraPanelLoteImplementacion);
        }
        catch(Exception e){
            muestraPanelLoteImplementacion = Boolean.FALSE;
            LOGGER.log(ADFLogger.WARNING, "ERROR requiereLotesValueChangeListener." + e.getClass() + e.getStackTrace());
        }
    }
    
    public void resultadoRegistrarAdquisicionValueChangeListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        LOGGER.log(ADFLogger.WARNING, "INTO resultadoRegistrarAdquisicionValueChangeListener.");
        try{
            valueChangeEvent.getComponent().processSaveState(FacesContext.getCurrentInstance());
            Integer valorObtenido = (Integer)valueChangeEvent.getNewValue();
            LOGGER.warning("Valor obteneido de resultado: " + valorObtenido);
            setRegistrarResultadoAdquisicion(valorObtenido);
            
            if(null != valorObtenido){
                if(valorObtenido == ADJUDICADO || valorObtenido == FRACASADO)
                    muestraSeccionParticipantes = Boolean.TRUE;
                else
                    muestraSeccionParticipantes = Boolean.FALSE;
                
                if(valorObtenido == ADJUDICADO)
                    muestraSeccionAdjudicatario = Boolean.TRUE;
                else
                    muestraSeccionAdjudicatario = Boolean.FALSE;
            }
            else{
                muestraSeccionParticipantes = Boolean.FALSE;
                muestraSeccionAdjudicatario = Boolean.FALSE;
            }
            
            if(valorObtenido == ADJUDICADO){
                LOGGER.warning("Se habilita el outcome para la salida ADJUDICADO");
                setSalidaRegistrarResultadoAdquisicion(Boolean.TRUE);
                LOGGER.warning("Valor bandera salidaRegistrarResultadoAdquisicion: " + getSalidaRegistrarResultadoAdquisicion());
            }
            else if(valorObtenido == FRACASADO || valorObtenido == ANULADO || valorObtenido == DESIERTO){
                LOGGER.warning("Se habilita el outcome para la salida FRACASADO, ANULADO, DESIERTO");
                setSalidaRegistrarResultadoAdquisicion(Boolean.FALSE);
                LOGGER.warning("Valor bandera salidaRegistrarResultadoAdquisicion: " + getSalidaRegistrarResultadoAdquisicion());
            }
            else{
                LOGGER.warning("Error, no se obtuvo un valor en el campo Resultado del selectOneChoice... Se manda a llamar el outcome de finalizar tarea");
                setSalidaRegistrarResultadoAdquisicion(Boolean.TRUE);
                LOGGER.warning("Valor bandera salidaRegistrarResultadoAdquisicion: " + getSalidaRegistrarResultadoAdquisicion());
            }
            
            LOGGER.warning("Valor obteneido de bandera de muestraSeccionParticipantes: " + muestraSeccionParticipantes);
            LOGGER.warning("Valor obteneido de bandera de muestraSeccionAdjudicatario: " + muestraSeccionAdjudicatario);
        }
        catch(Exception e){
            muestraPanelLoteImplementacion = Boolean.FALSE;
            LOGGER.log(ADFLogger.WARNING, "ERROR resultadoRegistrarAdquisicionValueChangeListener." + e.getClass() + e.getStackTrace());
        }
    }
    
    public void InicializarLoteImplementacion() {
        try{
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = binding.getOperationBinding("setValoresInicializarLoteImplementacion");
                operationBinding.getParamsMap().put("id", Long.parseLong(idLote));
                operationBinding.execute();
            
                if(!operationBinding.getErrors().isEmpty()){
                    LOGGER.warning("OperationBinding devuelve errores metodo setValoresInicializarLoteImplementacion");
                }else{
                        LOGGER.warning("***Carga IdLoteImplementacion Listo, redireccionando... ");
                    }
            }catch(Exception e){
                    LOGGER.log(ADFLogger.ERROR, "Error en consultarDatosLoteImplementacion " + e.getClass() + ":" + e.getMessage());
            }
        
        try{
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = binding.getOperationBinding("setValoresInicializarConcursanteImplementacion");
                operationBinding.getParamsMap().put("idLoteImplementacion", Long.parseLong(idLote));
                LOGGER.warning("idLoteImplementacion enviado: " + idLote);
                operationBinding.execute();
            
                if(!operationBinding.getErrors().isEmpty()){
                    LOGGER.warning("OperationBinding devuelve errores metodo inicializarParticipanteAdjudicatario");
                }else{
                        LOGGER.warning("***Carga ConcursanteImplementacion Listo, redireccionando... ");
                    }
                
                setVarIdParticipanteReadOnly(null);
                setVarIdAdjudicatarioReadOnly(Long.parseLong("-1"));
                obtenerFechaLimiteRecepcionPropuesta();
            }catch(Exception e){
                    LOGGER.log(ADFLogger.ERROR, "Error en consultarDatosConcursanteImplementacion " + e.getClass() + ":" + e.getMessage());
            }
    }
    
    public void obtenerFechaLimiteRecepcionPropuesta(){
        LOGGER.warning("Entra en obtenerFechaLimiteRecepcionPropuesta");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Boolean datosCorrectos = null;
        try{
            operationBinding = bindings.getOperationBinding("obtenerFechaLimiteRecepcionPropuesta");
            operationBinding.getParamsMap().put("idImplementacion", idImplementacion);
            operationBinding.execute();
            
            if(null != operationBinding.getResult()){
                datosCorrectos = (Boolean)operationBinding.getResult();
                if(datosCorrectos){
                    LOGGER.warning("Se obtienen los datos correctos.");
                }else{
                    LOGGER.warning("No se obtuvieron los datos");
                }
                
            }else{
                LOGGER.warning("Error en obtenerFechaLimiteRecepcionPropuesta");
            }
        }catch(Exception e){
            LOGGER.warning("Error en obtenerFechaLimiteRecepcionPropuesta");
        }
    }

    public void inicializarParticipanteAdjudicatario() {
        LOGGER.warning("Inicializa metodo inicializarParticipanteAdjudicatario");
        try{    
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = binding.getOperationBinding("setValoresInicializarLoteImplementacion");
                operationBinding.getParamsMap().put("id", Long.parseLong(idLote));
                operationBinding.execute();
            
                if(!operationBinding.getErrors().isEmpty()){
                    LOGGER.warning("OperationBinding devuelve errores metodo setValoresInicializarLoteImplementacion");
                }else{
                        LOGGER.warning("***Carga IdLoteImplementacion Listo, redireccionando... ");
                    }
            }catch(Exception e){
                    LOGGER.log(ADFLogger.ERROR, "Error en consultarDatosLoteImplementacion " + e.getClass() + ":" + e.getMessage());
            }
        
        try{
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = binding.getOperationBinding("setValoresInicializarConcursanteImplementacion");
                operationBinding.getParamsMap().put("idLoteImplementacion", Long.parseLong(idLote));
                LOGGER.warning("idLoteImplementacion enviado: " + idLote);
                operationBinding.execute();
            
                if(!operationBinding.getErrors().isEmpty()){
                    LOGGER.warning("OperationBinding devuelve errores metodo inicializarParticipanteAdjudicatario");
                }else{
                        LOGGER.warning("***Carga ConcursanteImplementacion Listo, redireccionando... ");
                    }
                
                setVarIdParticipanteReadOnly(null);
                setVarIdAdjudicatarioReadOnly(Long.parseLong("-1"));
                //Se busca el id de contrato por lote, si no existe el metodo crea un nuevo registro
                obtenerIdContratoPorLote();
            }catch(Exception e){
                    LOGGER.log(ADFLogger.ERROR, "Error en consultarDatosConcursanteImplementacion " + e.getClass() + ":" + e.getMessage());
            }
        
        try{
            if(codigoProducto != null)
                setIdProducto(Integer.parseInt(codigoProducto));
            else
                setIdProducto(Integer.parseInt("0"));
        }
        catch(Exception e){
            LOGGER.warning("Error al setear el valor del codigoProducto en el metodo inicializarParticipanteAdjudicatario " + e.getClass() + e.getMessage());
        }
        LOGGER.warning("Finaliza metodo inicializarParticipanteAdjudicatario");
    }
    
    public void obtenerIdContratoPorLote(){
        LOGGER.log(ADFLogger.WARNING, "Entra en obtenerIdContratoPorLote");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try{
            operationBinding = bindings.getOperationBinding("obtenerContratoPorLote");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                idContratoPorLote = (Integer)operationBinding.getResult();
                LOGGER.log(ADFLogger.WARNING, "Se obtuvo id Contrato :" + idContratoPorLote);
                obtenerIdMontoEscrituradoPorIdContrato(idContratoPorLote);
            }else{
                LOGGER.log(ADFLogger.WARNING, "No se obtuvo registro de contrato.");
            }
        }catch(Exception e){
            LOGGER.log(ADFLogger.WARNING, "Error en obtenerIdContratoPorLote");
        }

    }
    
    public void obtenerIdMontoEscrituradoPorIdContrato(Integer idContrato){
        LOGGER.log(ADFLogger.WARNING, "Entra en obtenerIdMontoEscrituradoPorIdContrato");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try{
            operationBinding = bindings.getOperationBinding("getIdMontoEscrituradoByIdContrato");
            operationBinding.getParamsMap().put("idContrato", idContrato);
            operationBinding.execute();
            Long resultado = (Long)operationBinding.getResult();
            if(null != resultado){
                if(resultado > 0){
                    idMontoEscriturado = Integer.parseInt( resultado.toString() );
                    LOGGER.log(ADFLogger.WARNING, "Se obtuvo id Monto Escriturado :" + idMontoEscriturado);
                }
                else{
                    LOGGER.log(ADFLogger.WARNING, "El idContrato aun no cuenta con un idMontoEscriturado...");
                }
            }else{
                LOGGER.log(ADFLogger.WARNING, "No se obtuvo registro de idMontoEscriturado porque idContrato es null.");
            }
        }catch(Exception e){
            LOGGER.log(ADFLogger.WARNING, "Error en obtenerIdMontoEscrituradoPorIdContrato");
        }
        LOGGER.warning("Finaliza metodo obtenerIdMontoEscrituradoPorIdContrato");
    }
    
    public void clienteGestionaContratacion(){
        LOGGER.log(ADFLogger.WARNING, "Entra en clienteGestionaContratacion");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try{
            operationBinding = bindings.getOperationBinding("obetenerContratacionCliente");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                isGestionaCliente = (Boolean)operationBinding.getResult();
            }else{
                LOGGER.log(ADFLogger.WARNING, "El resultado es nulo.");
            }
        }catch(Exception e){
            LOGGER.log(ADFLogger.WARNING, "Error en obtenerIdMontoEscrituradoPorIdContrato");
        }
        LOGGER.warning("Finaliza metodo obtenerIdMontoEscrituradoPorIdContrato");
    }
    
    public void inicializarValoresLotes() {
        LOGGER.warning("Inicia metodo inicializarValoresLotes");
        Integer obtenerRequiereLotes = Integer.valueOf(0);
        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = binding.getOperationBinding("setValoresInicializarLotes");
            operationBinding.getParamsMap().put("idImplementacion", Long.parseLong(idImplementacion));
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                LOGGER.warning("OperationBinding devuelve errores metodo setValoresInicializarLotes");
            } else {
                LOGGER.warning("***Carga Lotes Listo, redireccionando... ");
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicializarValoresLotes " + e);
        }

        try {
            BindingContainer bindingsIniciarAdquision = ADFUtils.getBindingContainer();
            OperationBinding operationBindingInicioAdquisicion =
                bindingsIniciarAdquision.getOperationBinding("obtenerRequiereLotesByIdImplementacion");
            operationBindingInicioAdquisicion.getParamsMap().put("idImplementacion", Long.parseLong(idImplementacion));
            operationBindingInicioAdquisicion.execute();
            obtenerRequiereLotes = (Integer) operationBindingInicioAdquisicion.getResult();
            LOGGER.warning("Valor obtenerRequiereLotes: " + obtenerRequiereLotes);
            if(obtenerRequiereLotes == 1){
                muestraPanelLoteImplementacion = Boolean.TRUE;
            }
            else{
                muestraPanelLoteImplementacion = Boolean.FALSE;
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en validarFormularioImplementacionRequiereLotes " + e);
        }
        LOGGER.warning("Finaliza metodo inicializarValoresLotes");
    }
    
    public void inicializarTreeEnvioGasto(){
        LOGGER.warning("Inicia metodo inicializarTreeEnvioGasto");
        try{
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = binding.getOperationBinding("filtraLineaByIdOperacion");
                LOGGER.warning("codigoOperacion a enviar: " + Long.valueOf(codigoOperacion));
                operationBinding.getParamsMap().put("idOperacion", Long.valueOf(codigoOperacion));
                operationBinding.execute();
            
                if(!operationBinding.getErrors().isEmpty()){
                    LOGGER.warning("OperationBinding devuelve errores metodo filtraLineaByIdOperacion");
                }else{
                        LOGGER.warning("***Carga IdOperacion Listo, redireccionando... ");
                    }
            }catch(Exception e){
                    LOGGER.log(ADFLogger.ERROR, "Error en filtraLineaByIdOperacion ", e);
            }
        LOGGER.warning("Finaliza metodo inicializarTreeEnvioGasto");
    }

    public void setInsertIdLote(RichInputText insertIdLote) {
        this.insertIdLote = insertIdLote;
    }

    public RichInputText getInsertIdLote() {
        return insertIdLote;
    }

    public void setVarIdLoteReadOnly(Long varIdLoteReadOnly) {
        this.varIdLoteReadOnly = varIdLoteReadOnly;
    }

    public Long getVarIdLoteReadOnly() {
        return varIdLoteReadOnly;
    }

    public void setVarIdParticipanteReadOnly(Long varIdParticipanteReadOnly) {
        this.varIdParticipanteReadOnly = varIdParticipanteReadOnly;
    }

    public Long getVarIdParticipanteReadOnly() {
        return varIdParticipanteReadOnly;
    }

    public void setVarIdAdjudicatarioReadOnly(Long varIdAdjudicatarioReadOnly) {
        this.varIdAdjudicatarioReadOnly = varIdAdjudicatarioReadOnly;
    }

    public Long getVarIdAdjudicatarioReadOnly() {
        return varIdAdjudicatarioReadOnly;
    }

    public void setMuestraSeccionParticipantes(Boolean muestraSeccionParticipantes) {
        this.muestraSeccionParticipantes = muestraSeccionParticipantes;
    }

    public Boolean getMuestraSeccionParticipantes() {
        return muestraSeccionParticipantes;
    }

    public void setMuestraSeccionAdjudicatario(Boolean muestraSeccionAdjudicatario) {
        this.muestraSeccionAdjudicatario = muestraSeccionAdjudicatario;
    }

    public Boolean getMuestraSeccionAdjudicatario() {
        return muestraSeccionAdjudicatario;
    }

    public void setRegistrarResultadoAdquisicion(Integer registrarResultadoAdquisicion) {
        this.registrarResultadoAdquisicion = registrarResultadoAdquisicion;
    }

    public Integer getRegistrarResultadoAdquisicion() {
        return registrarResultadoAdquisicion;
    }
    
    public void setResponsableOperacion(String responsableOperacion) {
        this.responsableOperacion = responsableOperacion;
    }

    public String getResponsableOperacion() {
        return responsableOperacion;
    }

    public void setIdProcesoBPM(String idProcesoBPM) {
        this.idProcesoBPM = idProcesoBPM;
    }

    public String getIdProcesoBPM() {
        return idProcesoBPM;
    }

    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }

    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public Long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }


    public void setIdContratoPorLote(Integer idContratoPorLote) {
        this.idContratoPorLote = idContratoPorLote;
    }

    public Integer getIdContratoPorLote() {
        return idContratoPorLote;
    }

    public void setMontoEscriturado(BigDecimal montoEscriturado) {
        this.montoEscriturado = montoEscriturado;
    }

    public BigDecimal getMontoEscriturado() {
        return montoEscriturado;
    }

    public void setIdMontoEscriturado(Integer idMontoEscriturado) {
        this.idMontoEscriturado = idMontoEscriturado;
    }

    public Integer getIdMontoEscriturado() {
        return idMontoEscriturado;
    }


    public void setIsGestionaCliente(Boolean isGestionaCliente) {
        this.isGestionaCliente = isGestionaCliente;
    }

    public Boolean getIsGestionaCliente() {
        return isGestionaCliente;
    }

    public void setSalidaRegistrarResultadoAdquisicion(Boolean salidaRegistrarResultadoAdquisicion) {
        this.salidaRegistrarResultadoAdquisicion = salidaRegistrarResultadoAdquisicion;
    }

    public Boolean getSalidaRegistrarResultadoAdquisicion() {
        return salidaRegistrarResultadoAdquisicion;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getMensajeError() {
        return mensajeError;
    }


    public void setNumeroSerieDocumento(oracle.jbo.domain.Number numeroSerieDocumento) {
        this.numeroSerieDocumento = numeroSerieDocumento;
    }

    public oracle.jbo.domain.Number getNumeroSerieDocumento() {
        return numeroSerieDocumento;
    }

    public void setHabilitarBtnValidarFT05(Boolean habilitarBtnValidarFT05) {
        this.habilitarBtnValidarFT05 = habilitarBtnValidarFT05;
    }

    public Boolean getHabilitarBtnValidarFT05() {
        return habilitarBtnValidarFT05;
    }


    public void setEsValorNulo(Boolean esValorNulo) {
        this.esValorNulo = esValorNulo;
    }

    public Boolean getEsValorNulo() {
        return esValorNulo;
    }

    public void setIdLineaCredito(Long idLineaCredito) {
        this.idLineaCredito = idLineaCredito;
    }

    public Long getIdLineaCredito() {
        return idLineaCredito;
    }
}
