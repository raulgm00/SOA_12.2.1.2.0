package envioalcobro;

import java.io.Serializable;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.sql.Timestamp;

import java.util.ArrayList;
import oracle.jbo.domain.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import org.bcie.fenix.common.utils.ADFUtils;
import oracle.jbo.RowSetIterator;


import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

import org.bcie.fenix.common.view.beans.FenixActionBean;


import org.bcie.fenix.common.view.beans.FenixActionBean.OperationExecuteException;

import pa11imppctght.bean.ImplementacionPCTBean;

public class EnvioAlCobroBean extends FenixActionBean implements Serializable {
    
    @SuppressWarnings("compatibility:7251447521940706289")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    
    public EnvioAlCobroBean() {
        super();
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    private static final String BUNDLE = "pa11imppctght.PA11ImpPCTGHTBundle";
    
    private String idTareaBPM;

    private Long codigoOperacion;
    private oracle.jbo.domain.Number vValorTasa;
    private oracle.jbo.domain.Date vFechaFinalizacionEstudios;
    private oracle.jbo.domain.Number vIdLineaCreditoSeleccionada;
      
    private Long idLineaCreditoSeleccionada = null;
    private String numeroLineaSeleccionada = null;
    private String mensajePopupCrearContrato;
    private Long idContratoActivo;
    private Integer idProcesoBPM;
    private Integer idTareaBPMInt;
    private String instanciaProceso;
    private Long idOperacion;
    private Long idImplementacion;
    private BigDecimal montoAmpliacion;
    private Long idSeleccionaLineaCredito;
    private BigDecimal totalEnvioCobro = BigDecimal.ZERO;   

    //Variables Configuracion de pantallas Envio al cobro, Validacion envio al cobro, Liquidar contrato
      private Boolean renderBtnCrearContrato;
      private Boolean soloLecturaBtnCrearContrato;
      private Boolean esModoLecturaTreeTable;
      private Boolean soloLecturaJustificacion;
      private Boolean esVisibleTreeEnvioALCobro = Boolean.FALSE;
      private Boolean esVisibleTreeLiquidarContrato = Boolean.FALSE;
      private Boolean esTareaValidarTasaFecha = Boolean.FALSE;
    
    //
      
      private Date fechaFlex = null;
    //Se agregan bandera para ocultar datos en la tarea Liquidar desembolsos anteriores
    private Boolean esMostrarEnLiquidarContrato = Boolean.TRUE;
    private Boolean esVisibleBotonActualizar = Boolean.FALSE;
    
    private Integer idTipoTasa = null;
    
    public static ArrayList<Long> lineasTreeTable = null;
    
    private List<TreeEnvioAlCobro> ramasList = new ArrayList<TreeEnvioAlCobro>();
    ChildPropertyTreeModel treeModel;
    
    private BigDecimal motoTotalLineas = BigDecimal.ZERO;
    private BigDecimal interesTotalContratos = BigDecimal.ZERO;   

    public void precargarDatosFinalizacionEstudios(){
        logger.warning("Inicia metodo precargarDatosFinalizacionEstudios");
        Long idOperacion = null;
        Map<String, Object> params = new HashMap<String, Object>();
        Row row = null;
        oracle.jbo.domain.Date fechaFinalizacionEstudios = null;
        oracle.jbo.domain.Number valorTasa = null;
        ImplementacionPCTBean implementacionPCTBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression(ImplementacionPCTBean.BEAN_EXPRESSION);
        
        if(null != implementacionPCTBean){
            if(null != implementacionPCTBean.getCodigoOperacion()){
                try{
                    idOperacion = new Long(implementacionPCTBean.getCodigoOperacion());
                }catch(Exception e){
                    logger.warning("No se pudo castear el valor de codigoOperacion a Long.");
                }
                
                logger.warning("IdOperacion se recupero con valor: " + idOperacion);
                if(null != idOperacion){
                    params.put("idOperacion", idOperacion);
                    try {
                        row = execute(params, "obtenerDatosFinalizacionEstudiosOperacion");
                    } catch (Exception e) {
                        logger.warning("Error al ejecutar el OperationBinding para cargar formulario de finalizacion de estudios.");
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_CARGAR_PANTALLA_TASA_FECHA"));
                    }
                    
                    if(null != row){
                        if(null != row.getAttribute("IdTipoTasa")){
                            try{
                                idTipoTasa = Integer.valueOf(row.getAttribute("IdTipoTasa").toString());
                                setIdTipoTasa(idTipoTasa);
                            }catch(Exception e){
                                logger.warning("No se pudo obtener el atributo IdTipoTasa.", e);
                            }
                        }else{
                            logger.warning("El atributo IdTipoTasa es NULL.");
                        }
                        
                        if(null != row.getAttribute("Tasa")){
                            try{
                                valorTasa = new oracle.jbo.domain.Number(row.getAttribute("Tasa").toString());
                                setVValorTasa(valorTasa);
                            }catch(Exception e){
                                logger.warning("No se pudo obtener el atributo TASA.", e);
                            }
                        }else{
                            logger.warning("El atributo TASA es NULL.");
                        }
                        
                        if(null != row.getAttribute("FechaFinalizacionEstudios")){
                            try{
                                fechaFinalizacionEstudios = (oracle.jbo.domain.Date) row.getAttribute("FechaFinalizacionEstudios");
                                setVFechaFinalizacionEstudios(fechaFinalizacionEstudios);
                            }catch(Exception e){
                                logger.warning("No se pudo obtener el atributo FechaFinalizacionEstudios.", e);
                            }
                        }else{
                            logger.warning("El atributo FechaFinalizacionEstudios es NULL.");
                        }
                    }else{
                        logger.warning("EL row recuperado es NULL.");
                    }
                }else{
                    logger.warning("idOperacion es NULL, no se pudo ejecutar el operationBinding");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_CARGAR_PANTALLA_TASA_FECHA"));
                }
            }else{
                logger.warning("No se pudo recuperar el codigoOperacion de implementacionPCTBean.");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_CARGAR_PANTALLA_TASA_FECHA"));
            }
        }else{
            logger.warning("implementacionPCTBean es NULL.");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_CARGAR_PANTALLA_TASA_FECHA"));
        }
        
        logger.warning("Valor de tasa: " + getVValorTasa() + ", FechaFinalizacionEstudios: " + getVFechaFinalizacionEstudios());
        logger.warning("Termina metodo precargarDatosFinalizacionEstudios");
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
    
    public void precargaDatosContrato() {
        logger.warning("*Inf, Inicia metodo precargaDatosContrato");

        idOperacion =
            (null == JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}")) ? null :
            new Long(JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}").toString());

        idTareaBPMInt =
            (null == JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}")) ? null :
            Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}").toString());

        idProcesoBPM =
            (null == JSFUtils.resolveExpression("#{pageFlowScope.idProcesoBPM}") ||
             JSFUtils.resolveExpression("#{pageFlowScope.idProcesoBPM}").toString().equals("")) ? null :
            Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.idProcesoBPM}").toString());

        instanciaProceso =
            (null == JSFUtils.resolveExpression("#{pageFlowScope.instanciaProceso}")) ? null :
            JSFUtils.resolveExpression("#{pageFlowScope.instanciaProceso}").toString();

        idImplementacion =
            (null == JSFUtils.resolveExpression("#{pageFlowScope.idImplementacion}")) ? null :
            Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.idImplementacion}").toString());

        logger.warning("*Inf, idOperacion recuperado : " + idOperacion);
        logger.warning("*Inf, idTareaBPMInt recuperado : " + idTareaBPMInt);
        logger.warning("*Inf, idProcesoBPM recuperado : " + idProcesoBPM);
        logger.warning("*Inf, instanciaProceso recuperado : " + instanciaProceso);
        logger.warning("*Inf, idImplementacion recuperado : " + idImplementacion);

        if (idOperacion != null) {
            configuracionPantallas();
        } else {
            logger.warning("*Inf, parametro requerido idOperacion es resuelto a null no se construira el treev");
        }

        logger.warning("*Inf, Termina metodo precargaDatosContrato");
    }
    
    private boolean precargaSolicitudLineasCredito() {
        logger.warning("Inicia metodo precargaSolicitudLineasCredito");
        Long idSolicitud = null;
        
        /*
        JSFUtils.setExpressionValue("#{pageFlowScope.idSolicitud}", rowSolicitud.getAttribute("Id"));
        JSFUtils.setExpressionValue("#{pageFlowScope.idEstadoSolicitud}", rowSolicitud.getAttribute("IdEstado"));
        JSFUtils.setExpressionValue("#{pageFlowScope.idOperacion}", rowSolicitud.getAttribute("IdOperacion"));*/
        
        Map<String, Object> params = new HashMap<String, Object>();
        Row row = null;
                
        logger.warning("IdOperacion se recupero con valor: " + idOperacion);
        if(null != idOperacion){
            params.put("id", idOperacion);
            try {
                row = execute(params, "buscarOperacionPorId");
            } catch (Exception e) {
                logger.warning("Error al ejecutar el OperationBinding para cargar formulario de enviar al cobro (Justificacion envio)");
            }
        }
                    
        logger.warning("Termina metodo precargarDatosEnviarCobro");
        return true;
    }


  

    /**
     * -Este metodo construye la estructura del tree en base a la clase TreeEnvioAlCobro
     * -La clase ChildPropertyTreeModel extiende de TreeModel (org.apache.myfaces.trinidad.model)
     *
     * @since 19/12/2016
     * @author Carlos Lopez
     */

    public void construirTree(Long idOperacion){
           logger.warning("*Inf, Inicia metodo construirTree(Long).");
       
            EnvioAlCobroBean envioAlCobroBean =
                (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");
            
            Row[] contratos = recuperarContratos(idOperacion);
            Row[] lineas = recuperarLineas(idOperacion);
                    
            logger.warning("*Inf, ok numero de lineas recuperadas : " + (null != lineas ? lineas.length : null));
            logger.warning("*Inf, ok numero de Contratos recuperados : " + (null != contratos ? contratos.length : null));
            TreeEnvioAlCobro nodo = null;
            Integer idEstadoContrato = null;
            BigDecimal montoContrato = null;
            Integer contador = 0;
            Integer contadorContrato = null;
            //Se inicializa monto total en cero 
            motoTotalLineas = BigDecimal.ZERO;
            totalEnvioCobro  = BigDecimal.ZERO;
            interesTotalContratos = BigDecimal.ZERO;
            // Se inicializan listas para su recarga
            ramasList = new ArrayList<TreeEnvioAlCobro>();
            lineasTreeTable = new ArrayList<Long>();
            Timestamp fechaMaximaDesembolsar = null;
            BigDecimal montoAmpliacionRecuperado = null;

            
            if (null != lineas) {
                for(int i = 0 ; i < lineas.length ; i++){
                    Row lineaCredito = lineas[i];
                    
                          Long idLineaLinea = (Long)lineaCredito.getAttribute("IdLinea");
                    String numeroLineaLinea = (String)lineaCredito.getAttribute("Id");               
                    BigDecimal interesLinea = null;
                      BigDecimal montoLinea = (BigDecimal)lineaCredito.getAttribute("MontoPorLiquidar");                                   
                 BigDecimal montoAmpliacion = (BigDecimal)lineaCredito.getAttribute("MontoAmpliacionLinea");
                    Date fechaVigenciaLinea = (Date)lineaCredito.getAttribute("FechaVigencia");
                        fechaMaximaDesembolsar = (Timestamp)lineaCredito.getAttribute("FechaMaximaDesembolsar");
                    
                        logger.warning("Valor  idLineaLinea: " + idLineaLinea);
                        logger.warning("Valor  numeroLineaLinea: " + numeroLineaLinea);
                        logger.warning("Valor  montoLinea: " + montoLinea);
                        logger.warning("Valor  fechaMaximaDesembolsar: " + fechaMaximaDesembolsar);
                        logger.warning("Valor  montoAmpliacion: " + montoAmpliacion);
                   
                        
                        
                          interesLinea = interesLinea(contratos, idLineaLinea);
                          logger.warning("Valor  interesLinea: " + interesLinea);

                   // if(montoLinea != null){
                               // motoTotalLineas = motoTotalLineas.add(montoLinea);
                   //            motoTotalLineas = motoTotalLineas.add(montoLineaMasInteres);
                   // }
                            logger.warning("Valor  motoTotalLineas: " + motoTotalLineas); 
                    
                    if(null != montoAmpliacion){
                        logger.warning("La linea ya cuenta con un monto de ampliacion : " + montoAmpliacion);
                        crearRegistroMontoAmpliacion(idLineaLinea, montoAmpliacion);
                    }else{
                        montoAmpliacionRecuperado = obtenerMontoAmpliacion(idLineaLinea);
                        if(montoAmpliacionRecuperado != null){
                            montoAmpliacionRecuperado = montoLinea.add(interesLinea);
                            montoAmpliacion = montoAmpliacionRecuperado;
                        }else{
                            montoAmpliacion = BigDecimal.ZERO;
                            if(interesLinea != null){
                                if(montoLinea==null)
                                    montoLinea = BigDecimal.ZERO;
                                  montoAmpliacion = montoLinea.add(interesLinea);
                              }else{
                                  montoAmpliacion = montoLinea;
                              }
                            crearRegistroMontoAmpliacion(idLineaLinea, montoAmpliacion);
                        } 
                    }                      
                        logger.warning("Valor  montoLinea: " + montoLinea);     
                       //asignamos valor a idLineaCreditoSeleccionada la primera linea que tenga monto de ampliacion 
                       
                                if(idLineaCreditoSeleccionada == null){
                                    
                                     idLineaCreditoSeleccionada = idLineaLinea;
                                     numeroLineaSeleccionada =  numeroLineaLinea;
                                     
                                     if(montoAmpliacion != null && montoAmpliacion != BigDecimal.ZERO ){
                                           setMontoAmpliacion(montoAmpliacion);
                                     }
                                     
                                     idSeleccionaLineaCredito = idLineaLinea;
                                    
                                   }else{
                                      //  idLineaCreditoSeleccionada = null;                                    
                                   } 
                    
                    
                    motoTotalLineas = motoTotalLineas.add(montoAmpliacion);
                    interesTotalContratos = interesTotalContratos.add(interesLinea);
                    totalEnvioCobro = totalEnvioCobro.add(montoAmpliacion);
                    
                    TreeEnvioAlCobro linea = 
                        new TreeEnvioAlCobro(numeroLineaLinea ,montoLinea , interesLinea, montoAmpliacion ,fechaMaximaDesembolsar, "linea", idLineaLinea);
                        contadorContrato = 0;
                        //Contratos
                    if (null != contratos) {
                        for(int a = 0 ; a < contratos.length ; a++){                        
                                
                            Row contrato = contratos[a];
                            
                                    Long idLineaContrato = (Long)contrato.getAttribute("IdLinea");
                                       String idContrato =  contrato.getAttribute("Id").toString();
                              BigDecimal interesContrato = (BigDecimal)contrato.getAttribute("Interes");
                            //Solo se sumara el interes de los contratos con estado por liquidar.
                            if (null != contrato.getAttribute("IdTcaEstado")) {
                                
                                idEstadoContrato = (Integer) contrato.getAttribute("IdTcaEstado");
                                //Solo se sumara los intereses de los contratos con estado 'Por liquidar' 
                                //y en caso de que existan con estado 'liquidado'
                                if (idEstadoContrato.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_POR_LIQUIDAR) == 0 || 
                                    idEstadoContrato.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_LIQUIDADO) == 0) {
                                    if(null == contrato.getAttribute("Monto")){
                                        logger.warning("El monto es nulo." + contrato.getAttribute("Monto"));
                                        contador++;
                                    }
                                        montoContrato = (BigDecimal)contrato.getAttribute("Monto");
                                    if (interesContrato != null) {
                                        interesTotalContratos = interesTotalContratos.add(interesContrato);
                                    }
                                    
                                } else {
                                    montoContrato = (BigDecimal)contrato.getAttribute("MontoDesembolsar");
                                    logger.warning("El estado del contrato es creado, no se suma al total de Intereses.");
                                }
                            } else {
                            logger.warning("El estado delcontrato es nulo.");
                            }
                        // logger.warning("Inf, *****  idLineaLinea : " + idLineaLinea + " idLineaContrato : " + idLineaContrato);
                          
                            if(idLineaLinea.compareTo(idLineaContrato) == 0 ){
                                contadorContrato++;
                                logger.warning("Inf, ***** creando nodo para linea : " + numeroLineaLinea + " con idContrato : "+idContrato);                               
                                nodo = new TreeEnvioAlCobro(idContrato, montoContrato, interesContrato, null, null, "contrato", idLineaLinea);                               
                                linea.addNodo(nodo);
                                }                               
                            }
                        
                    }else{
                        logger.warning("La linea no es agregada ya que no cuenta con contratos desembolsados." + idLineaLinea);
                    }
                    if(contadorContrato > 0){
                        logger.warning("El id de la linea a agregar al arbol es :" + idLineaLinea);
                        ramasList.add(linea);
                        lineasTreeTable.add(idLineaLinea);
                    }else{
                        logger.warning("La linea :" + idLineaLinea + "No cuenta con contratos desembolsados.");
                    }
                        
                    }
                if(contador > 0){
                    logger.warning("Se encontraron contratos con montos vacios." + contador);
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, null, getStringFromBundle("MSG_WARNING_VALIDA_MONTO_DOLAR_NULO"));
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }else{
                    soloLecturaBtnCrearContrato = Boolean.TRUE;
                }
            
            treeModel = new ChildPropertyTreeModel(ramasList, "nodos");            
            try{
                logger.warning("Valores recuperados para lineasTreeTable a enviar a ImplementacionActionBean");
                if(lineasTreeTable != null){
                    logger.warning("lineasTreeTable.size()" + lineasTreeTable.size());
                    if(lineasTreeTable.size() > 0){
                        for(int i=0;i<lineasTreeTable.size();i++){
                            logger.warning("lineasTreeTable posicion i=" + i + ", idLinea: " + lineasTreeTable.get(i));    
                        }
                    }
                    else{
                        logger.warning("lineasTreeTable su valor es 0, no contiene elementos");
                    }
                }
                else{
                    logger.warning("lineasTreeTable es null");
                }
            }catch(Exception e){
                logger.warning("ERROR, al recuperar las lineas del treeTable que se envian a ImplementacionActionBean para la tarea Enviar al Cobro");
            }
       logger.warning("Inf, Termina metodo construirTree");
        }
    
    /**
     * -Este metodo construye la estructura del tree en base a la clase TreeEnvioAlCobro
     * -La clase ChildPropertyTreeModel extiende de TreeModel (org.apache.myfaces.trinidad.model)
     *
     * @since 19/12/2016
     * @author Carlos Lopez
     */

    public void construirTreeActual(Long idOperacion, Date fechaFlex){
       logger.warning("*Inf, Inicia metodo construirTreeActual(Long, Date)");
       
            EnvioAlCobroBean envioAlCobroBean =
                (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");
            
            Row[] contratos = recuperarContratosActual(idOperacion, fechaFlex);
            Row[] lineas = recuperarLineas(idOperacion);
                    
            logger.warning("*Inf, ok numero de lineas recuperadas : " + (null != lineas ? lineas.length : null));
            logger.warning("*Inf, ok numero de Contratos recuperados : " + (null != contratos ? contratos.length : null));
            TreeEnvioAlCobro nodo = null;
            Integer idEstadoContrato = null;
            BigDecimal montoContrato = null;
            Integer contador = 0;
            Integer contadorContrato = null;
            //Se inicializa monto total en cero 
            motoTotalLineas = BigDecimal.ZERO;
            interesTotalContratos = BigDecimal.ZERO;
            totalEnvioCobro = BigDecimal.ZERO;
            // Se inicializan listas para su recarga
            ramasList = new ArrayList<TreeEnvioAlCobro>();
            lineasTreeTable = new ArrayList<Long>();
            Timestamp fechaMaximaDesembolsar = null;
            
            

            if (null != lineas) {
                for(int i = 0 ; i < lineas.length ; i++){
                    
                    Row lineaCredito = lineas[i];
                    
                          Long idLineaLinea = (Long)lineaCredito.getAttribute("IdLinea");
                    String numeroLineaLinea = (String)lineaCredito.getAttribute("Id");               
                    BigDecimal interesLinea = null;
                      BigDecimal montoLinea = (BigDecimal)lineaCredito.getAttribute("MontoPorLiquidar");                                   
                 BigDecimal montoAmpliacion = (BigDecimal)lineaCredito.getAttribute("MontoAmpliacionLinea");
                    Date fechaVigenciaLinea = (Date)lineaCredito.getAttribute("FechaVigencia");
                        fechaMaximaDesembolsar = (Timestamp)lineaCredito.getAttribute("FechaMaximaDesembolsar");
                    
                        logger.warning("Valor  idLineaLinea: " + idLineaLinea);
                        logger.warning("Valor  numeroLineaLinea: " + numeroLineaLinea);
                        logger.warning("Valor  montoLinea: " + montoLinea);
                        logger.warning("Valor  fechaMaximaDesembolsar: " + fechaMaximaDesembolsar);
                        logger.warning("Valor  montoAmpliacion: " + montoAmpliacion);
                 
                          interesLinea = interesLinea(contratos, idLineaLinea);
                          logger.warning("Valor  interesLinea: " + interesLinea);
                          

                    
                    if(null != montoAmpliacion){
                        logger.warning("La linea ya cuenta con un monto de ampliacion : " + montoAmpliacion);
                        crearRegistroMontoAmpliacion(idLineaLinea, montoAmpliacion);
                    }else{
                        BigDecimal montoAmpliacionRecuperado = obtenerMontoAmpliacion(idLineaLinea);
                        if(montoAmpliacionRecuperado != null){
                            montoAmpliacionRecuperado = montoLinea.add(interesLinea);
                            montoAmpliacion = montoAmpliacionRecuperado;
                            logger.warning("Valor  montoAmpliacionRecuperado: " + montoAmpliacionRecuperado); 
                        }else{
                            montoAmpliacion = BigDecimal.ZERO;
                            if(interesLinea != null){
                                if(montoLinea==null)
                                    montoLinea = BigDecimal.ZERO;
                                  montoAmpliacion = montoLinea.add(interesLinea);
                                  logger.warning("Valor  montoAmpliacion2: " + montoAmpliacion); 
                              }else{
                                  montoAmpliacion = montoLinea;
                                  logger.warning("Valor  igualado montoAmpliacion = montoLinea: " + montoAmpliacion); 
                              }
                            logger.warning("Creando registro crearRegistroMontoAmpliacion " + idLineaLinea +","+montoAmpliacion);
                            crearRegistroMontoAmpliacion(idLineaLinea, montoAmpliacion);
                        } 
                    }                      
                        logger.warning("Valor  montoLinea: " + montoLinea);     
                       //asignamos valor a idLineaCreditoSeleccionada la primera linea que tenga monto de ampliacion                                         
                    
                                if(idLineaCreditoSeleccionada == null && montoAmpliacion != BigDecimal.ZERO ){
                                    
                                     idLineaCreditoSeleccionada = idLineaLinea;
                                     numeroLineaSeleccionada =  numeroLineaLinea;
                                     setMontoAmpliacion(montoAmpliacion);
                                     idSeleccionaLineaCredito = idLineaLinea;
                                    
                                   }else{
                                        idLineaCreditoSeleccionada = null;
                                    
                                   } 
                        motoTotalLineas = motoTotalLineas.add(montoAmpliacion);
                        interesTotalContratos = interesTotalContratos.add(interesLinea);
                        totalEnvioCobro = totalEnvioCobro.add(montoAmpliacion);
                        logger.warning("Valor  nuevo objeto: " + numeroLineaLinea+","+montoLinea+ "," +interesLinea+","+montoAmpliacion +","+fechaMaximaDesembolsar+","+ "linea"+","+ idLineaLinea);     
                    TreeEnvioAlCobro linea = 
                        new TreeEnvioAlCobro(numeroLineaLinea ,montoLinea , interesLinea, montoAmpliacion ,fechaMaximaDesembolsar, "linea", idLineaLinea);
                        contadorContrato = 0;
                        //Contratos
                    if (null != contratos) {
                        for(int a = 0 ; a < contratos.length ; a++){                        
                                
                            Row contrato = contratos[a];
                            
                                    Long idLineaContrato = (Long)contrato.getAttribute("IdLinea");
                                       String idContrato =  contrato.getAttribute("Id").toString();
                              BigDecimal interesContrato = (BigDecimal)contrato.getAttribute("Interes");
                            //Solo se sumara el interes de los contratos con estado por liquidar.
                            if (null != contrato.getAttribute("IdTcaEstado")) {
                                
                                idEstadoContrato = (Integer) contrato.getAttribute("IdTcaEstado");
                                //Solo se sumara los intereses de los contratos con estado 'Por liquidar' 
                                //y en caso de que existan con estado 'liquidado'
                                if (idEstadoContrato.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_POR_LIQUIDAR) == 0 || 
                                    idEstadoContrato.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_LIQUIDADO) == 0) {
                                    if(null == contrato.getAttribute("Monto")){
                                        logger.warning("El monto es nulo." + contrato.getAttribute("Monto"));
                                        contador++;
                                    }
                                        montoContrato = (BigDecimal)contrato.getAttribute("Monto");
                                    if (interesContrato != null) {
                                        interesTotalContratos = interesTotalContratos.add(interesContrato);
                                    }
                                    
                                } else {
                                    montoContrato = (BigDecimal)contrato.getAttribute("MontoDesembolsar");
                                    logger.warning("El estado del contrato es creado, no se suma al total de Intereses.");
                                }
                            } else {
                            logger.warning("El estado delcontrato es nulo.");
                            }
                        // logger.warning("Inf, *****  idLineaLinea : " + idLineaLinea + " idLineaContrato : " + idLineaContrato);
                          
                            if(idLineaLinea.compareTo(idLineaContrato) == 0 ){
                                contadorContrato++;
                                logger.warning("Inf, ***** creando nodo para linea : " + numeroLineaLinea + " con idContrato : "+idContrato);                               
                                nodo = new TreeEnvioAlCobro(idContrato, montoContrato, interesContrato, null, null, "contrato", idLineaLinea);                               
                                linea.addNodo(nodo);
                                }                               
                            }
                        
                    }else{
                        logger.warning("La linea no es agregada ya que no cuenta con contratos desembolsados." + idLineaLinea);
                    }
                    if(contadorContrato > 0){
                        logger.warning("El id de la linea a agregar al arbol es :" + idLineaLinea);
                        ramasList.add(linea);
                        lineasTreeTable.add(idLineaLinea);
                    }else{
                        logger.warning("La linea :" + idLineaLinea + "No cuenta con contratos desembolsados.");
                    }
                        
                    }
                if(contador > 0){
                    logger.warning("Se encontraron contratos con montos vacios." + contador);
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, null, getStringFromBundle("MSG_WARNING_VALIDA_MONTO_DOLAR_NULO"));
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }else{
                    soloLecturaBtnCrearContrato = Boolean.TRUE;
                }
            
            treeModel = new ChildPropertyTreeModel(ramasList, "nodos");            
            try{
                logger.warning("Valores recuperados para lineasTreeTable a enviar a ImplementacionActionBean");
                if(lineasTreeTable != null){
                    logger.warning("lineasTreeTable.size()" + lineasTreeTable.size());
                    if(lineasTreeTable.size() > 0){
                        for(int i=0;i<lineasTreeTable.size();i++){
                            logger.warning("lineasTreeTable posicion i=" + i + ", idLinea: " + lineasTreeTable.get(i));    
                        }
                    }
                    else{
                        logger.warning("lineasTreeTable su valor es 0, no contiene elementos");
                    }
                }
                else{
                    logger.warning("lineasTreeTable es null");
                }
            }catch(Exception e){
                logger.warning("ERROR, al recuperar las lineas del treeTable que se envian a ImplementacionActionBean para la tarea Enviar al Cobro");
            }
       logger.warning("Inf, Termina metodo construirTree");
        }

    /**
     * -Este metodo retorna el tree a la vista
     *
     * @since 19/12/2016
     * @author Carlos Lopez
     */

    public ChildPropertyTreeModel getCharatcerVal() {
        return treeModel;
    }    

    /**
     * -Este metodo recupera las lineas de credito en base a el id de operacion   
     * 
     * @param idSolicitud
     * @since 19/12/2016
     * @author Carlos Lopez
     */

    public Row[] recuperarLineas(Long idOperacion){
       logger.warning("Inf, Inicia metodo precargaTree");                        
       logger.warning("Inf, Recuperando lineas de la solicitud: " + idOperacion);
            Row[] lineas = null;
           try{
              BindingContainer bindings = getBindings();           
               OperationBinding operBinding = bindings.getOperationBinding("cargarLineasPorOperacion");
                operBinding.getParamsMap().put("idOperacion", idOperacion);
                operBinding.execute();
                 
               if(!operBinding.getErrors().isEmpty()){
                       JSFUtils.addFacesErrorMessage(operBinding.getErrors().toString());
               }else{
                     lineas = (Row[])operBinding.getResult();
                   }
               
           }catch(Exception e){
               logger.log(ADFLogger.ERROR,
                          " *** Error en el OperationBinding de cargarLineasPorOperacion" + e.getClass() + " : " + e.getMessage());           
           }                           
           
       logger.warning("Termina metodo precargaTree");  
       return lineas;
        }

    /**
     * -Este metodo recupera los contratos que le pertenecen a la operacion 
     * 
     * @param idOperacion
     * @since 19/12/2016
     * @author Carlos Lopez
     */
    public Row[] recuperarContratos(Long idOperacion){
            logger.warning("*Inf, Inicia metodo recuperarContratos");                        
              logger.warning("*Inf, Recuperando Contratos de la solicitud.. " + idImplementacion);
                 Row[] contratos = null;
                try{
                   BindingContainer bindings = getBindings();           
                    OperationBinding operBinding = bindings.getOperationBinding("recuperarInteresDeContratos");                    
                    operBinding.getParamsMap().put("idOperacion", idOperacion);
                    operBinding.getParamsMap().put("idSolicitud", idImplementacion);
                    operBinding.execute();
                       
                    if(!operBinding.getErrors().isEmpty()){
                            JSFUtils.addFacesErrorMessage(operBinding.getErrors().toString());
                    }else{
                          contratos = (Row[])operBinding.getResult();
                        }
                    
                }catch(Exception e){
                    logger.warning("***Error, OperationBinding  recuperarInteresDeContratos", e);           
                }                           
                
            logger.warning("*Inf, Termina metodo precargaTree");  
            return contratos;
        }
    
    /**
     * -Este metodo recupera los contratos que le pertenecen a la operacion 
     * 
     * @param idOperacion
     * @since 19/12/2016
     * @author Carlos Lopez
     */
    public Row[] recuperarContratosActual(Long idOperacion, Date fechaFlex){
            logger.warning("*Inf, Inicia metodo recuperarContratos");                        
              logger.warning("*Inf, Recuperando Contratos de la solicitud.. " + idImplementacion);
                 Row[] contratos = null;
                try{
                   BindingContainer bindings = getBindings();           
                    OperationBinding operBinding = bindings.getOperationBinding("recuperarInteresDeContratosActual");                    
                    operBinding.getParamsMap().put("idOperacion", idOperacion);
                    operBinding.getParamsMap().put("idSolicitud", idImplementacion);
                    operBinding.getParamsMap().put("fechaFlexActual", fechaFlex);
                    operBinding.execute();
                       
                    if(!operBinding.getErrors().isEmpty()){
                            JSFUtils.addFacesErrorMessage(operBinding.getErrors().toString());
                    }else{
                          contratos = (Row[])operBinding.getResult();
                        }
                    
                }catch(Exception e){
                    logger.warning("***Error, OperationBinding  recuperarInteresDeContratos", e);           
                }                           
                
            logger.warning("*Inf, Termina metodo precargaTree");  
            return contratos;
        }

    /**
     * -Con este metodo iteramos sobre los contratos que le correspondan a la linea
     * acumulamos el interes de cada contrato para obtener el interes de la linea
     * @param Row[] contratos, Long idLineaLinea
     * @since 19/12/2016
     * @author Carlos Lopez
     */
    public BigDecimal interesLinea(Row[] contratos, Long idLineaLinea) {
        logger.warning("Inf, inicia metodo para recuperar el interes de la Linea");
        BigDecimal interesLinea = BigDecimal.ZERO;
        Integer idEstadoContrato = null;
        
        if (null != contratos && contratos.length > 0) {
            
            logger.warning("Tamaño de arreglo de contratos :" + contratos.length);
            
            for (int a = 0; a < contratos.length; a++) {
                
                Row contrato = contratos[a];
                
                if (null != contrato.getAttribute("IdTcaEstado")) {
                    
                    idEstadoContrato = (Integer) contrato.getAttribute("IdTcaEstado");
                    
                    if (idEstadoContrato.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_POR_LIQUIDAR) == 0 ||
                        idEstadoContrato.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_LIQUIDADO) == 0) {
                        
                        Long idLineaContrato = (Long) contrato.getAttribute("IdLinea");
                        BigDecimal interesContrato = (BigDecimal) contrato.getAttribute("Interes");
                        logger.warning("Inf, *****  idLineaLinea : " + idLineaLinea + " idLineaContrato : " +
                                       idLineaContrato);
                        
                        if (null != idLineaLinea && null != idLineaContrato) {
                            if (idLineaLinea.compareTo(idLineaContrato) == 0) {

                                if (interesContrato != null) {

                                    interesLinea = interesLinea.add(interesContrato);
                                    logger.warning("Inf, interes del contrato : " + interesContrato);
                                    logger.warning("Inf, interes de la linea : " + interesLinea);
                                    
                                }
                            }
                        } else {
                            logger.warning("Se obtuvo algun valor en null :" + idLineaLinea + "-" + idLineaContrato);
                        }
                    } else {
                        logger.warning("El estado del contrato es creado, no se agrega interes.");
                    }
                }
            }
        } else {
            logger.warning("No se recuperaron contratos.");
        }
        
        if (null != interesLinea) {
            interesLinea = interesLinea.setScale(2,RoundingMode.FLOOR);
        }
        
        logger.warning("Inf, termina metodo para recuperar el interes de la Linea");
        return interesLinea;
    }
    
    public void crearRegistroMontoAmpliacion(Long idLinea, BigDecimal montoAmpliacion){
        logger.warning("Entra en crearRowMontoAmpliacion.");
        BindingContainer bindings = getBindings();
        OperationBinding operBinding = null;
        try{
                       
             operBinding = bindings.getOperationBinding("crearRowMonto");                    
             operBinding.getParamsMap().put("idLinea", idLinea);
             operBinding.getParamsMap().put("montoAmpliacion", montoAmpliacion);  
             operBinding.execute();
                
             if(!operBinding.getErrors().isEmpty()){
                     logger.warning("Error al crear el row en MontoAmpliacionLineaCredtoVO");
             }else{
                   logger.warning("Se crearon los registros en MontoAmpliacionLineaCredtoVO correctamente");
                 }
        }catch(Exception e){
            logger.warning("Error en crearRowMontoAmpliacion.", e);
        }
    }
    
    public BigDecimal obtenerMontoAmpliacion(Long idLinea){
        logger.warning("Entra en obtenerMontoAmpliacion.");
        BigDecimal montoAmpliacion = null;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        
        try{
            operationBinding = bindings.getOperationBinding("buscarRowMontoAmpliacion");                    
            operationBinding.getParamsMap().put("idLinea", idLinea);
            operationBinding.execute();
               
            if(!operationBinding.getErrors().isEmpty()){
                    logger.warning("Error al crear el row en MontoAmpliacionLineaCredtoVO");
            }else{
                montoAmpliacion = (BigDecimal)operationBinding.getResult();
            }   
        }catch(Exception e){
            logger.warning("Error en obtenerMontoAmpliacion.", e);
        }
        logger.warning("Valor del monto de ampliacion obtenido : " + montoAmpliacion);
        return montoAmpliacion;
    }
     /**
      * -Este metodo construye la estructura del tree en base a la clase TreeLiquidarDesembolso
      * -La clase ChildPropertyTreeModel extiende de TreeModel (org.apache.myfaces.trinidad.model)
      *
      * @since 09/01/2017
      * @author Carlos Lopez
      */

    private List<TreeLiquidarDesembolso> ramasT2List = new ArrayList<TreeLiquidarDesembolso>();
    ChildPropertyTreeModel treeModel2;
    
    public void construirTreeLiquidarDesembolso(){
        logger.warning("Inf, inicia metodo construirTreeLiquidarDesembolso");
        Row[] lineas = recuperarLineas(idOperacion);
        Row[] contratos = ContratosPorLiquidarByOperacion(idOperacion);
        BigDecimal montoPorLiquidar = null;
        BigDecimal montoLiquidado = null;
        BigDecimal montoLinea = null;
        
        logger.warning("Inf, ok numero de lineas recuperadas : " + (null != lineas ? lineas.length : null));
        logger.warning("Inf, ok numero de Contratos recuperados : " + (null != contratos ? contratos.length : null));
        TreeLiquidarDesembolso nodo = null;
        
        Timestamp fechaEfectiva = null;
        
        //Se limpia tree para su recarga
        ramasT2List = new ArrayList<TreeLiquidarDesembolso>();
        
        if (null != lineas) {
            for(int i = 0 ; i < lineas.length ; i++){            
                Row lineaCredito = lineas[i];
                    
                Long idLineaLinea = (Long)lineaCredito.getAttribute("IdLinea");
                String numeroLineaLinea = (String)lineaCredito.getAttribute("Id"); 
                montoPorLiquidar = (BigDecimal)lineaCredito.getAttribute("MontoPorLiquidar");
                montoLiquidado = (BigDecimal)lineaCredito.getAttribute("MontoLiquidado");
                logger.warning("idLineaLinea " + idLineaLinea);
                logger.warning("numeroLineaLinea" + numeroLineaLinea);
                logger.warning("montoPorLiquidar" + montoPorLiquidar);
                logger.warning("montoLiquidado" + montoLiquidado);
//                if(null != montoLiquidado){
                    if(montoPorLiquidar==null)
                        montoPorLiquidar = BigDecimal.ZERO;
                    montoLinea = montoPorLiquidar;  
//                }else{
//                    logger.warning("El monto liquidado es nulo.");
//                    montoLinea = montoPorLiquidar;
//                }
                TreeLiquidarDesembolso linea = new TreeLiquidarDesembolso(idLineaLinea, numeroLineaLinea, montoLinea, null, "", null, "linea");
                
                //Contratos
                if (null != contratos) {
                    for(int a = 0 ; a < contratos.length ; a++){
                        Row contrato = contratos[a];

                        Long idLineaContrato =
                            (null == contrato.getAttribute("IdLinea")) ? null : (Long) contrato.getAttribute("IdLinea");

                        Long id = (null == contrato.getAttribute("Id")) ? null : (Long) contrato.getAttribute("Id");

                        String idContrato =
                            (null == contrato.getAttribute("Id")) ? null : contrato.getAttribute("Id").toString();

                        Integer idEstado =
                            (null == contrato.getAttribute("IdEstado")) ? null :
                            (Integer) contrato.getAttribute("IdEstado");

                        String estado =
                            (null == contrato.getAttribute("Estado")) ? null :
                            contrato.getAttribute("Estado").toString();

                        BigDecimal montoContrato =
                            (null == contrato.getAttribute("Monto")) ? null :
                            (BigDecimal) contrato.getAttribute("Monto");
                        
                        if(null != contrato.getAttribute("FechaEfectiva")){
                            fechaEfectiva  = (Timestamp)contrato.getAttribute("FechaEfectiva");
                        }else{
                            logger.warning("Fecha efectiva nula.");
                            fechaEfectiva = null;
                        }

                        if(idLineaLinea.compareTo(idLineaContrato) == 0 ){                                                               
                            logger.warning("Inf, **** creando nodo para linea : " + numeroLineaLinea + " con idContrato : "+idContrato);                               
                            nodo = new TreeLiquidarDesembolso(id, idContrato, montoContrato, idEstado, estado , fechaEfectiva, "contrato");
                            linea.addNodo(nodo);
                        } 
                    }
                }
                ramasT2List.add(linea);
            }
        }
        treeModel2 = new ChildPropertyTreeModel(ramasT2List, "nodos"); 
               
        logger.warning("Inf, termina metodo construirTreeLiquidarDesembolso");
    }
    
    /**
     * -Este metodo retorna el tree a la vista     
     * 
     * @since 09/01/2017
     * @author Carlos Lopez
     */

    public ChildPropertyTreeModel getCharatcerVal2() {
            return treeModel2;
        }    


    /**
     * -Con este metodo recuperamos todos los contratos que esten en un estado por liquidar para
     * alimentar a el Tree de Liquidar Desembolso 
     * @param idOperacion
     * @since 06/01/2017
     * @author Carlos Lopez
     */
    
    public Row[] ContratosPorLiquidarByOperacion(Long idOperacion){
        logger.warning("Inf, inicia metodo recuperarContratosByOperacion");                    
          Row[] contratos = null;
                try{
                   BindingContainer bindings = getBindings();           
                    OperationBinding operBinding = bindings.getOperationBinding("recuperarContratosPorLiquidar");                    
                    operBinding.getParamsMap().put("idOperacion", idOperacion);   
                    operBinding.getParamsMap().put("idSolicitud", idImplementacion); 
                      contratos = (Row[])operBinding.execute();
                }catch(Exception e){
                    logger.log(ADFLogger.ERROR,
                               " *** Error en el OperationBinding de recuperarInteresDeContratos", e);           
                }        
        logger.warning("Inf, inicia metodo recuperarContratosByOperacion");
      return contratos;
    }


    public void precargarEnvioAlCobro(){
           logger.warning("Inicia metodo precargarEnvioAlCobro");
           
           configuracionPantallas();
           
           logger.warning("Termina metodo precargarEnvioAlCobro");
       }
       
       public void configuracionPantallas(){
           logger.warning("Inicia metodo configuracionPantallas");
           Integer idTareaBPM = null;
           ImplementacionPCTBean implementacionPCTBean = 
               (ImplementacionPCTBean) JSFUtils.resolveExpression(ImplementacionPCTBean.BEAN_EXPRESSION);
            
          EnvioAlCobroBean envioAlCobroBean = (EnvioAlCobroBean) JSFUtils.resolveExpression("#{pageFlowScope.envioAlCobro}");

           //if(null != implementacionPCTBean){
              // if(null != implementacionPCTBean.getCodigoTarea()){
                   try{
                       //idTareaBPM = Integer.parseInt(implementacionPCTBean.getCodigoTarea());
                       idTareaBPM = getIdTareaBPMInt();
                   }catch(Exception e){
                       logger.warning("No se pudo castear el valor del idTareaBPM a Integer.");
                   }
              // }else{
                  // logger.warning("getCodigoOperacion es NULL");
              // }
          // }else{
               //logger.warning("ImplementacionPCTBean es NULL.");
           //}
           
           logger.warning("IdTareaBPM: " + idTareaBPM);
           if(null != idTareaBPM){
               switch(idTareaBPM){
               case FenixConstants.PA11_ENVIAR_AL_COBRO:
                   logger.warning("Inf, Configurando pantalla para envio al cobro.... ");
                   setRenderBtnCrearContrato(Boolean.TRUE);
                   setSoloLecturaBtnCrearContrato(Boolean.FALSE);
                   setEsModoLecturaTreeTable(Boolean.FALSE);
                   setSoloLecturaJustificacion(Boolean.FALSE);
                   construirTree(idOperacion);
                   setEsVisibleTreeLiquidarContrato(Boolean.FALSE);
                   setEsVisibleTreeEnvioALCobro(Boolean.TRUE);  
                   //obtenerFechaFlex();
                   obtenerFechaFlexActual();
                   asginarFechaFlexActual();
                   break;
               case FenixConstants.PA11_VALIDAR_ENVIO_COBRO:
                   logger.warning("Inf, Configurando pantalla para validar envio al cobro.... ");
                   setRenderBtnCrearContrato(Boolean.FALSE);
                   setSoloLecturaBtnCrearContrato(Boolean.TRUE);
                   setEsModoLecturaTreeTable(Boolean.TRUE);
                   setSoloLecturaJustificacion(Boolean.TRUE);
                   construirTree(idOperacion);
                   setEsVisibleTreeLiquidarContrato(Boolean.FALSE);
                   setEsVisibleTreeEnvioALCobro(Boolean.TRUE);  
                   //obtenerFechaFlex();
                   obtenerFechaFlexActual();
                   asginarFechaFlexActual();
                   break;
               case FenixConstants.PA11_LIQUIDAR_DESEMOBOLSOS_ANTERIORES:
                   logger.warning("Inf, Configurando pantalla para liquidar desembolso.... ");
                   construirTreeLiquidarDesembolso();
                   setEsVisibleTreeEnvioALCobro(Boolean.FALSE);
                   setEsVisibleTreeLiquidarContrato(Boolean.TRUE);
                   setEsMostrarEnLiquidarContrato(Boolean.FALSE);
                   break;
               case FenixConstants.PA11_VALIDAR_TASA_FECHA:
                   logger.warning("Inf, Configurando pantalla para liquidar desembolso.... ");
                   setRenderBtnCrearContrato(Boolean.FALSE);
                   setSoloLecturaBtnCrearContrato(Boolean.TRUE);
                   setEsModoLecturaTreeTable(Boolean.TRUE);
                   setSoloLecturaJustificacion(Boolean.TRUE);
                   construirTree(idOperacion);
                   setEsVisibleTreeLiquidarContrato(Boolean.FALSE);
                   setEsVisibleTreeEnvioALCobro(Boolean.TRUE); 
                   setEsTareaValidarTasaFecha(Boolean.TRUE);
                   setEsMostrarEnLiquidarContrato(Boolean.TRUE);
                  // envioAlCobroBean.setEsMostrarEnLiquidarContrato(Boolean.FALSE);
                  
                   break;
                                  
               }
           }else{
               logger.warning("idTareaBPM es NULL, no se pudo configurar la pantalla.");
           }
           
           logger.warning("Termina metodo configuracionPantallas");
       }
       
       public Integer getIdTareaBPMInt(){
           Integer idTareaBPMInt = null;
           
           try{
               idTareaBPMInt = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}").toString());
           }catch(Exception e){
               logger.warning("Error al recuperar el idTareaBPM.", e);
           }
           
           logger.warning("IdTareaBPM recuperado: " + idTareaBPMInt);        
           return idTareaBPMInt;
       }


       public void obtenerFechaFlex(){
           logger.warning("*Inf, inicia metodo obtenerFechaFlex");
            try{
               BindingContainer bindings = getBindings();           
                OperationBinding operBinding = bindings.getOperationBinding("obtenerFechaFlex");               
                   fechaFlex = (Date)operBinding.execute();
            }catch(Exception e){
                logger.log(ADFLogger.ERROR,
                           " ***Error en el OperationBinding de obtenerFechaFlex" + e);
            }           
            logger.warning("*Inf, termina metodo obtenerFechaFlex");
        }
       
    public void obtenerFechaFlexActual(){
        logger.warning("*Inf, inicia metodo obtenerFechaFlex");
         try{
            BindingContainer bindings = getBindings();           
             OperationBinding operBinding = bindings.getOperationBinding("obtenerFechaFlexActual");  
             operBinding.getParamsMap().put("idOperacion", idOperacion);
             fechaFlex = (Date)operBinding.execute();
         }catch(Exception e){
             logger.log(ADFLogger.ERROR,
                        " ***Error en el OperationBinding de obtenerFechaFlex" + e);
         }           
         logger.warning("*Inf, termina metodo obtenerFechaFlex");
     }
    
    public void asginarFechaFlexActual(){
        logger.warning("Inside asginarFechaFlexActual.");
        
         try{
            BindingContainer bindings = getBindings();           
             OperationBinding operBinding = bindings.getOperationBinding("asginarFechaFlexActual");  
             operBinding.getParamsMap().put("idOperacion", idOperacion);
             operBinding.getParamsMap().put("fechaFlexActual", fechaFlex);
             operBinding.execute();
         }catch(Exception e){
             logger.log(ADFLogger.ERROR,
                        " ***Error en el OperationBinding de asginarFechaFlexActual" + e);
         }           
        
        logger.warning("Inside asginarFechaFlexActual.");
     }

    public void precargarDatosEnviarCobro(){
        logger.warning("Inicia metodo precargarDatosEnviarCobro");
        Long idOperacion = null;
        Map<String, Object> params = new HashMap<String, Object>();
        Row row = null;
        ImplementacionPCTBean implementacionPCTBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression(ImplementacionPCTBean.BEAN_EXPRESSION);
        
        try{
            idOperacion = new Long(implementacionPCTBean.getCodigoOperacion());
        }catch(Exception e){
            logger.warning("No se pudo castear el valor de codigoOperacion a Long.");
        }
        
        logger.warning("IdOperacion se recupero con valor: " + idOperacion);
        if(null != idOperacion){
            params.put("id", idOperacion);
            try {
                row = execute(params, "buscarOperacionPorId");
            } catch (Exception e) {
                logger.warning("Error al ejecutar el OperationBinding para cargar formulario de enviar al cobro (Justificacion envio)");
            }
        }
                    
        logger.warning("Termina metodo precargarDatosEnviarCobro");
    }
    
    public void precargarDatosValidarEnviarCobro(){
        logger.warning("Inicia metodo precargarDatosValidarEnviarCobro");
        Long idOperacion = null;
        Map<String, Object> params = new HashMap<String, Object>();
        Row row = null;
        ImplementacionPCTBean implementacionPCTBean =
            (ImplementacionPCTBean) JSFUtils.resolveExpression(ImplementacionPCTBean.BEAN_EXPRESSION);
        
        try{
            idOperacion = new Long(implementacionPCTBean.getCodigoOperacion());
        }catch(Exception e){
            logger.warning("No se pudo castear el valor de codigoOperacion a Long.");
        }
        
        logger.warning("IdOperacion se recupero con valor: " + idOperacion);
        if(null != idOperacion){
            params.put("id", idOperacion);
            try {
                row = execute(params, "buscarOperacionPorId");
            } catch (Exception e) {
                logger.warning("Error al ejecutar el OperationBinding para cargar formulario de validar enviar al cobro (Justificacion envio)");
            }
        }
                    
        logger.warning("Termina metodo precargarDatosValidarEnviarCobro");
    }


 /**------------------- Accesors ---------------------------**/

    public BigDecimal getTotalEnvioCobro() {
        
        //try {
            //totalEnvioCobro = motoTotalLineas.add(interesTotalContratos);
            //if (montoAmpliacion != null) {
            //    totalEnvioAlCobro = montoAmpliacion;    
            //}
       // } catch(Exception e) {
       //     logger.log(ADFLogger.WARNING, "No se puede objete el total de envio al cobro: ", e);  
       // }
        return totalEnvioCobro;
    }
    
    public void setTotalEnvioCobro(BigDecimal totalEnvioCobro) {
        this.totalEnvioCobro = totalEnvioCobro;
    }
    
    public BigDecimal getTotalMontoAmpliacion() {
        ChildPropertyTreeModel treeModel = getCharatcerVal();

        BigDecimal totalMontoAmpliacion = BigDecimal.ZERO;
        int count = treeModel.getRowCount();
        logger.log(ADFLogger.WARNING, "Num de montos de ampliacion: " + count);
        try {
            for (int index = 0; index < count; index++) {
                TreeEnvioAlCobro treeEnvioAlCobro = (TreeEnvioAlCobro) treeModel.getRowData(index);

                if (null != treeEnvioAlCobro) {
                    BigDecimal monto = treeEnvioAlCobro.getMontoAmpliacion();

                    logger.log(ADFLogger.WARNING, "monto[" + index + "]: " + monto);
                    if (null != monto) {
                        totalMontoAmpliacion = totalMontoAmpliacion.add(monto);
                    }
                }
            }
        } catch(Exception e) {
            logger.log(ADFLogger.WARNING, "Error al recuperar TotalMontoAmpliacion.", e);
        }
        
        logger.log(ADFLogger.WARNING, "totalMontoAmpliacion: " + totalMontoAmpliacion);
        
        return totalMontoAmpliacion;
    }
 
    public void setCodigoOperacion(Long codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public Long getCodigoOperacion() {
        return codigoOperacion;
    }

    public void setVValorTasa(oracle.jbo.domain.Number vValorTasa) {
        this.vValorTasa = vValorTasa;
    }

    public oracle.jbo.domain.Number getVValorTasa() {
        return vValorTasa;
    }

    public void setVFechaFinalizacionEstudios(oracle.jbo.domain.Date vFechaFinalizacionEstudios) {
        this.vFechaFinalizacionEstudios = vFechaFinalizacionEstudios;
    }

    public oracle.jbo.domain.Date getVFechaFinalizacionEstudios() {
        return vFechaFinalizacionEstudios;
    }
     public void setMotoTotalLineas(BigDecimal motoTotalLineas) {
        this.motoTotalLineas = motoTotalLineas;
    }

    public BigDecimal getMotoTotalLineas() {
        return motoTotalLineas;
    }
    
    public void setInteresTotalContratos(BigDecimal interesTotalContratos) {
        this.interesTotalContratos = interesTotalContratos;
    }

    public BigDecimal getInteresTotalContratos() {
        return interesTotalContratos;
    }
    
    public void setRenderBtnCrearContrato(Boolean renderBtnCrearContrato) {
           this.renderBtnCrearContrato = renderBtnCrearContrato;
       }

       public Boolean getRenderBtnCrearContrato() {
           return renderBtnCrearContrato;
       }

       public void setSoloLecturaBtnCrearContrato(Boolean soloLecturaBtnCrearContrato) {
           this.soloLecturaBtnCrearContrato = soloLecturaBtnCrearContrato;
       }

       public Boolean getSoloLecturaBtnCrearContrato() {
           return soloLecturaBtnCrearContrato;
       }

       public void setEsModoLecturaTreeTable(Boolean esModoLecturaTreeTable) {
           this.esModoLecturaTreeTable = esModoLecturaTreeTable;
       }

       public Boolean getEsModoLecturaTreeTable() {
           return esModoLecturaTreeTable;
       }

       public void setSoloLecturaJustificacion(Boolean soloLecturaJustificacion) {
           this.soloLecturaJustificacion = soloLecturaJustificacion;
       }

       public Boolean getSoloLecturaJustificacion() {
           return soloLecturaJustificacion;
       }

       public void setIdTareaBPM(String idTareaBPM) {
           this.idTareaBPM = idTareaBPM;
       }

       public String getIdTareaBPM() {
           return idTareaBPM;
       }
       
        public void setEsVisibleTreeEnvioALCobro(Boolean esVisibleTreeEnvioALCobro) {
            this.esVisibleTreeEnvioALCobro = esVisibleTreeEnvioALCobro;
        }
    
        public Boolean getEsVisibleTreeEnvioALCobro() {
            return esVisibleTreeEnvioALCobro;
        }
    
        public void setEsVisibleTreeLiquidarContrato(Boolean esVisibleTreeLiquidarContrato) {
            this.esVisibleTreeLiquidarContrato = esVisibleTreeLiquidarContrato;
        }
    
        public Boolean getEsVisibleTreeLiquidarContrato() {
            return esVisibleTreeLiquidarContrato;
        }

    public void setIdLineaCreditoSeleccionada(Long idLineaCreditoSeleccionada) {
        this.idLineaCreditoSeleccionada = idLineaCreditoSeleccionada;
    }

    public Long getIdLineaCreditoSeleccionada() {
        return idLineaCreditoSeleccionada;
        //return 5323L;
    }

    public void setMensajePopupCrearContrato(String mensajePopupCrearContrato) {
        this.mensajePopupCrearContrato = mensajePopupCrearContrato;
    }

    public String getMensajePopupCrearContrato() {
        return mensajePopupCrearContrato;
    }

    public void setIdContratoActivo(Long idContratoActivo) {
        this.idContratoActivo = idContratoActivo;
    }

    public Long getIdContratoActivo() {
        return idContratoActivo;
    }

    public void setIdProcesoBPM(Integer idProcesoBPM) {
        this.idProcesoBPM = idProcesoBPM;
    }

    public Integer getIdProcesoBPM() {
        return idProcesoBPM;
    }

    public void setIdTareaBPMInt(Integer idTareaBPMInt) {
        this.idTareaBPMInt = idTareaBPMInt;
    }

    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }

    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }
        
    public void setFechaFlex(Date fechaFlex) {
        this.fechaFlex = fechaFlex;
    }

    public Date getFechaFlex() {
        return fechaFlex;
    }
    
    public void setNumeroLineaSeleccionada(String numeroLineaSeleccionada) {
        this.numeroLineaSeleccionada = numeroLineaSeleccionada;
    }

    public String getNumeroLineaSeleccionada() {
        return numeroLineaSeleccionada;
    }


    public void setMontoAmpliacion(BigDecimal montoAmpliacion) {
        this.montoAmpliacion = montoAmpliacion;
    }

    public BigDecimal getMontoAmpliacion() {
        return this.getTotalMontoAmpliacion();
    }

    public void setIdSeleccionaLineaCredito(Long idSeleccionaLineaCredito) {
        this.idSeleccionaLineaCredito = idSeleccionaLineaCredito;
    }

    public Long getIdSeleccionaLineaCredito() {
        return idSeleccionaLineaCredito;
    }

    public void setEsMostrarEnLiquidarContrato(Boolean esMostrarEnLiquidarContrato) {
        this.esMostrarEnLiquidarContrato = esMostrarEnLiquidarContrato;
    }

    public Boolean getEsMostrarEnLiquidarContrato() {
        return esMostrarEnLiquidarContrato;
    }

    public void setEsVisibleBotonActualizar(Boolean esVisibleBotonActualizar) {
        this.esVisibleBotonActualizar = esVisibleBotonActualizar;
    }

    public Boolean getEsVisibleBotonActualizar() {
        return esVisibleBotonActualizar;
    }
    
    public void setIdTipoTasa(Integer idTipoTasa) {
        this.idTipoTasa = idTipoTasa;
    }

    public Integer getIdTipoTasa() {
        return idTipoTasa;
    }

    public oracle.jbo.domain.Number getVIdLineaCreditoSeleccionada() {
        return vIdLineaCreditoSeleccionada;
    }

    public void setVIdLineaCreditoSeleccionada(oracle.jbo.domain.Number vIdLineaCreditoSeleccionada) {
        this.vIdLineaCreditoSeleccionada = vIdLineaCreditoSeleccionada;
    }

    public static void setLineasTreeTable(ArrayList<Long> lineasTreeTable) {
        EnvioAlCobroBean.lineasTreeTable = lineasTreeTable;
    }

    public static ArrayList<Long> getLineasTreeTable() {
        return lineasTreeTable;
    }
    
    public void setEsTareaValidarTasaFecha(Boolean esTareaValidarTasaFecha) {
        this.esTareaValidarTasaFecha = esTareaValidarTasaFecha;
    }

    public Boolean getEsTareaValidarTasaFecha() {
        return esTareaValidarTasaFecha;
    }
}
