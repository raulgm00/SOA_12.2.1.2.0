package pa03admoncondicionesght.beans;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import java.util.ResourceBundle;

import java.util.logging.Logger;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.RichQuery;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.QueryEvent;
import oracle.adf.view.rich.model.AttributeCriterion;
import oracle.adf.view.rich.model.AttributeDescriptor;
import oracle.adf.view.rich.model.ConjunctionCriterion;
import oracle.adf.view.rich.model.Criterion;
import oracle.adf.view.rich.model.QueryDescriptor;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Key;
import oracle.jbo.Row;

import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;



/**
 * Managed Bean para acciones del proceso de Administracion de Condiciones
 *
 * @author Latbc.
 */
public class CondicionesActionsBean extends FenixActionBean
{
  
  /**
   * Log de la aplicacion
   */
  private static ADFLogger logger = null;
  
  public static final String BUNDLE ="pa03admoncondicionesght.PA03AdmonCondicionesGHTBundle";
  
  /**
   * Define el nombre del operator bindings para inicializar la tarea de cumplir condiciones
   */
  public static final String INI_CUMPLIR_COND_OPER = "iniTareaCumplirCondiciones";
  
  /**
   * Define el nombre del operator bindings para inicializar la tarea de validar condiciones
   */
  public static final String INI_VALIDAR_COND_OPER = "iniTareaValidarCondiciones";
  
  /**
   * Define clave de bundle para obtener el Id de Estado TCC correspondiente a una TCC Validada parcialmente
   */
  public static final String ID_ESTADO_TCC_VALIDADA_PARCIALMENTE_BKEY = "pa03admoncondiciones.validarcondiciones.id.estado.tcc.validada.parcialmente";
  
  /**
   * Define clave de Bundle para obtener el Id de Estado TCC correspondiente a una TCC con Evidencia
   */
  public static final String ID_ESTADO_TCC_CON_EVIDENCIA_BKEY = "pa03admoncondiciones.cumplircondiciones.id.estado.tcc.con.evidencia";
  
    private static final Integer ID_ESTADO_TCC_POR_VALIDAR = 21;
    private static final Integer ID_ESTADO_TCC_POR_CUMPLIR = 29;
    
    private RichPopup popupConfirmarFinalizarTarea;
    private RichPopup popupAgregarEvidencia;
    private RichPopup popupEliminarEvidencia;
    private RichPopup popupConfirmarValidar;
    private RichPopup popupEliminarValidacion;
    private RichPopup popupDesestimarCondiciones;

    // Inicializacion de componentes visuales en pantallas
    private RichOutputText otInitForm; // Inicializa lista de Evidencias, de la condición, en Cumplir condiciones y Validar cumplimiento
    
    /**
     * Contiene valor para el atributo Visible o Rendered del boton Validar, y tambien sirve para aplicar el criterio 
     * contrario para al boton Eliminar
     */
    private boolean esBtnValidarVisible;
    
    /**
     * Contiene valor para el atributo Visible o Rendered del boton Lista para Validar o Por validar.
     */
    private boolean esBtnPorValidarVisible;
    
    private String docpop;
    private RichPopup popupConfirmarValidarTodasCondiciones;
    private RichQuery actualizarComponenteQuery;
    private RichTable condicionesTableUIC;
    private RichPopup popUpConfirmarCondicionesAValidar;

    public CondicionesActionsBean()
  {
      if (logger == null) {
          logger = ADFLogger.createADFLogger(this.getClass());
      }
  }

    public void setPopupConfirmarFinalizarTarea(RichPopup popupFinalizarTarea) {
        this.popupConfirmarFinalizarTarea = popupFinalizarTarea;
    }
    
    public RichPopup getPopupConfirmarFinalizarTarea() {
        return popupConfirmarFinalizarTarea;
    }
  
    public void setPopupAgregarEvidencia(RichPopup popupAgregarEvidencia) {
        this.popupAgregarEvidencia = popupAgregarEvidencia;
    }

    public RichPopup getPopupAgregarEvidencia() {
        return popupAgregarEvidencia;
    }
    
    public void setPopupEliminarEvidencia(RichPopup popupEliminarEvidencia) {
        this.popupEliminarEvidencia = popupEliminarEvidencia;
    }

    public RichPopup getPopupEliminarEvidencia() {
        return popupEliminarEvidencia;
    }
    
    public void setPopupConfirmarValidar(RichPopup popupConfirmarListaParaValidar) {
        this.popupConfirmarValidar = popupConfirmarListaParaValidar;
    }

    public RichPopup getPopupConfirmarValidar() {
        return popupConfirmarValidar;
    }
    
    public void setPopupEliminarValidacion(RichPopup popupEliminarValidacion) {
        this.popupEliminarValidacion = popupEliminarValidacion;
    }

    public RichPopup getPopupEliminarValidacion() {
        return popupEliminarValidacion;
    }
    
    public void setOtInitForm(RichOutputText otInitForm) {
        this.otInitForm = otInitForm;
    }

    public void setEsBtnValidarVisible(boolean esBtnValidarVisible) {
        this.esBtnValidarVisible = esBtnValidarVisible;
    }

    public boolean getEsBtnValidarVisible() {
        
        //Por defecto el boton es visible o habilitado siempre y cuando el estado TCC no sea el de validada
        esBtnValidarVisible = true;
        Long idSubEstadoTcc = null;
        try{
            idSubEstadoTcc = (Long) ADFUtils.getBoundAttributeValue("IdSubEstadoTcc");
        }catch(Exception e){
            logger.severe("Error al obtener Id de Estado TCC", e);
        }
        
        if(idSubEstadoTcc != null){
            oracle.jbo.domain.Number idValidada = null;
            String strIdValidada = ADFUtils.getStringFromBundle(ID_ESTADO_TCC_VALIDADA_PARCIALMENTE_BKEY, BUNDLE);
            try{
                idValidada = new oracle.jbo.domain.Number(strIdValidada);
            }catch(Exception e){
                logger.severe("Error al obtener Id de Estado TCC validada");
            }
            
            if(idValidada != null){
                if(idSubEstadoTcc.equals(idValidada)){
                    //Asigna valor para que el boton de validar NO sea visible o habilitado
                    esBtnValidarVisible = false;
                }    
            }
        }
        
        return esBtnValidarVisible;
    }
    
    public void setEsBtnPorValidarVisible(boolean esBtnPorValidarVisible) {
        this.esBtnPorValidarVisible = esBtnPorValidarVisible;
    }

    public boolean isEsBtnPorValidarVisible() {
        
        //Por defecto el boton es visible o habilitado siempre y cuando el estado TCC no sea el de Con Evidencia
        esBtnPorValidarVisible = true;
        Long idSubEstadoTcc = null;
        try{
            idSubEstadoTcc = (Long) ADFUtils.getBoundAttributeValue("IdSubEstadoTcc");
        }catch(Exception e){
            logger.severe("Error al obtener Id de Estado TCC", e);
        }
        
        if(idSubEstadoTcc != null){
            oracle.jbo.domain.Number idConEvidencia = null;
            String strIdConEvidencia = ADFUtils.getStringFromBundle(ID_ESTADO_TCC_CON_EVIDENCIA_BKEY, BUNDLE);
            try{
                idConEvidencia = new oracle.jbo.domain.Number(strIdConEvidencia);
            }catch(Exception e){
                logger.severe("Error al obtener Id de Estado TCC validada");
            }
            
            if(idConEvidencia != null){
                if(idSubEstadoTcc.equals(idConEvidencia)){
                    //Asigna valor para que el boton de Listo para validar NO sea visible o habilitado
                    esBtnPorValidarVisible = false;
                }    
            }
        }
        
        return esBtnPorValidarVisible;
    }
  
  private Integer getCodigoTarea()
  {
      CondicionesBean managedBean = (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.condicionesBean}");

      if (null != managedBean.getIdTarea() && managedBean.getIdTarea().trim().length() > 0)
      {
          return Integer.parseInt(managedBean.getIdTarea());
      }

      return 0;
  }
    
  public void finalizarTareaPopup()
  {
      RichPopup.PopupHints hints = new RichPopup.PopupHints();
      popupConfirmarFinalizarTarea.show(hints);
  }
  
  public String cancelarFinalizarTarea()
  {
      popupConfirmarFinalizarTarea.hide();
      return null;
  }
      
  public String invocarFinalizarTarea()
  {
      List<String> budleKeyErroList = new ArrayList<String>();
      Boolean esValidoFinalizarTarea = Boolean.FALSE;
      
      int codigoTarea = getCodigoTarea();

      switch (codigoTarea)
      {
          case FenixConstants.PC01_COMPLETARCUESTIONARIO: //TODO AJUSTAR
              logger.fine("PC01_COMPLETARCUESTIONARIO");
              esValidoFinalizarTarea = Boolean.TRUE;
            /*
              if(!isRNValid)
              { 
                budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
              }
              else
              {            
                isValidSolicitarMasInformacion = Boolean.TRUE;
              }
            */
          break;
      
          case FenixConstants.PC01_VERIFICARREQLEGALES: //TODO AJUSTAR
            logger.fine("PC01_COMPLETARCUESTIONARIO");
            esValidoFinalizarTarea = Boolean.TRUE;
          /*
            if(!isRNValid)
            { 
              budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
            }
            else
            {            
              isValidSolicitarMasInformacion = Boolean.TRUE;
            }
          */
          break;
    
          default:
              logger.log(ADFLogger.ERROR, "invokeFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
          break;
      }
        
      if (!esValidoFinalizarTarea)
      {
          if(budleKeyErroList.size()>0)
          {
            for(String bundleKey : budleKeyErroList)
              JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
          }
      } else
      {
        finalizarTareaPopup();
      }
    
    return null;
  }
    
    /**
     * Ejecuta un operator bindings
     * @param params contiene mapa de objetos de la operacion
     * @param operBindName contiene el nombre de la operacion
     * @return devuelve objeto Operator Bindings
     */
    @SuppressWarnings("unchecked")
    private OperationBinding executeOperBinding(Map params,
                                                String operBindName){
        
        logger.entering(CondicionesActionsBean.class.getName(),
                        "executeOperBinding",
                        new Object[]{params, operBindName});
        
        BindingContainer bindings = getBindings();
        OperationBinding oper = null;
        oper = bindings.getOperationBinding(operBindName);
        if (null != oper) {
            
            if(params != null){
                oper.getParamsMap().putAll(params);    
            }
            try {
                logger.warning("EJECUTA METODO ".concat(operBindName));
                oper.execute();
            } catch (Exception e) {
                logger.severe("Error al ejecutar Operator Bindings: " + operBindName, e);                
            }

            if (!oper.getErrors().isEmpty()) {
                logger.severe("Operator Bindings devuelve errores: " + operBindName);
            }else{
                logger.warning("Operator Bindings se ejecuto correctamente");
            }
        }else{
            logger.severe("Error el operator Binding " + operBindName + 
                          " no fue encontrado");
        }
        
        logger.exiting(CondicionesActionsBean.class.getName(), 
                       "executeOperBinding",
                       oper);
        return oper;
    }
    
    /**
     * Ejecuta la inicializacion de la tarea Cumplir Condiciones
     */
    @SuppressWarnings("unchecked")
    public void iniCumplirCondiciones(){
        
        CondicionesBean condicionesBean = 
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.condicionesBean}");
        
        boolean continuar = true;
        if(condicionesBean != null){
            if(condicionesBean.getIdOperacion() != null){
                if("".equals(condicionesBean.getIdOperacion())){
                    logger.severe("Error el valor de Id Operacion es Vacio");
                    continuar = false;
                }
            }else{
                logger.severe("Error el valor de Id Operacion es NULL");
                continuar = false;
            }
            
            if(condicionesBean.getIdTarea() != null){
                if("".equals(condicionesBean.getIdTarea())){
                    logger.severe("Error el valor de Codigo Tarea es Vacio");
                    continuar = false;
                }
            }else{
                logger.severe("Error el valor de Codigo Tarea es NULL");
                continuar = false;
            }
        }else{
            logger.severe("No se pudo obtener Managed Bean de PageFlowScope por expresion");
            continuar = false;
        }
        
        if(continuar){
            //Asigna parametros de la operacion
            Map params = new HashMap();
            params.put("idOperacion", condicionesBean.getIdOperacion());
            params.put("codigoTarea", condicionesBean.getIdTarea());
            
            OperationBinding oper = executeOperBinding(params, INI_CUMPLIR_COND_OPER);
            if(!oper.getErrors().isEmpty()){
                Boolean result = null;
                result = (Boolean) oper.getResult();
                if(result != null){
                    if(result){
                        logger.warning("Tarea inicializada con exito");
                    }else{
                        logger.severe("Error al inicializar la tarea");
                    }
                }else{
                    logger.severe("Error no se pudo obtener resultado de la inicializacion");
                }
            }
        }
    }
    
    /**
     * Ejecuta la inicializacion de la tarea de Validar Condiciones
     */
    @SuppressWarnings("unchecked")
    public void iniValidarCondiciones(){
        
        CondicionesBean condicionesBean = 
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.condicionesBean}");
        
        boolean continuar = true;
        if(condicionesBean != null){
            if(condicionesBean.getIdOperacion() != null){
                if("".equals(condicionesBean.getIdOperacion())){
                    logger.severe("Error el valor de Id Operacion es Vacio");
                    continuar = false;
                }
            }else{
                logger.severe("Error el valor de Id Operacion es NULL");
                continuar = false;
            }
            
            if(condicionesBean.getIdTarea() != null){
                if("".equals(condicionesBean.getIdTarea())){
                    logger.severe("Error el valor de Codigo Tarea es Vacio");
                    continuar = false;
                }
            }else{
                logger.severe("Error el valor de Codigo Tarea es NULL");
                continuar = false;
            }
        }else{
            logger.severe("No se pudo obtener Managed Bean de PageFlowScope por expresion");
            continuar = false;
        }
        
        if(continuar){
            //Asigna parametros de la operacion
            Map params = new HashMap();
            params.put("idOperacion", condicionesBean.getIdOperacion());
            params.put("codigoTarea", condicionesBean.getIdTarea());
            
            OperationBinding oper = executeOperBinding(params, INI_VALIDAR_COND_OPER);
            if(!oper.getErrors().isEmpty()){
                Boolean result = null;
                result = (Boolean) oper.getResult();
                if(result != null){
                    if(result){
                        logger.warning("Tarea inicializada con exito");
                    }else{
                        logger.severe("Error al inicializar la tarea");
                    }
                }else{
                    logger.severe("Error no se pudo obtener resultado de la inicializacion");
                }
            }
        }
    }
    
    /*** START Pantalla Cumplir condiciones ***/
    public void agregarEvidenciaActionListener(ActionEvent actionEvent) {
        logger.warning("Inside agregarEvidenciaActionListener: " + actionEvent.getComponent().getId());
        CondicionesBean condicionesBean = (CondicionesBean)JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding voDocumentosCondiciones = null;
        OperationBinding operationBinding = null;
        RichPopup.PopupHints popupHints = null;
        Long idAgrupador = null;
        try{
            idAgrupador = condicionesBean.getIdAgrupador();
            // Asignación de variables
            voDocumentosCondiciones = ADFUtils.getDCBindingContainer().findIteratorBinding("DocumentosCondicionesVOIterator");

            // Invocamos método para filtrar DocumentosCondicionesVO en base al IdOperacion e IdCondicion
            operationBinding = bindings.getOperationBinding("setvarIdOperacionIdCondicion");
            operationBinding.getParamsMap().put("idOperacion", condicionesBean.getOperacionId());
            operationBinding.getParamsMap().put("idCondicion", 
                                                (Long)JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}"));
            operationBinding.getParamsMap().put("idAgrupador", idAgrupador);
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
            
            // Debido a que cambiaron los bind variables, hacemos un execute query
            voDocumentosCondiciones.executeQuery();
            
            // Abrimos popup
            popupHints = new RichPopup.PopupHints();
            getPopupAgregarEvidencia().show(popupHints);
        }catch(Exception e){
            logger.warning("Error en agregarEvidenciaActionListener");
        }
    }
    
    public void aceptarAgregarEvidenciaActionListener(ActionEvent actionEvent) {
        logger.warning("Inside aceptarAgregarEvidenciaActionListener: " + actionEvent.getComponent().getId());
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding voEvidenciasCondiciones = null;
        OperationBinding operationBinding = null;
        Long idCondicion = null;
        Long idAgrupador = null;

        Boolean resultado = Boolean.FALSE;
        // Asignación de variables
//        voEvidenciasCondiciones =
//            ADFUtils.getDCBindingContainer().findIteratorBinding("EvidenciasCondicionesVOIterator");
        idAgrupador = condicionesBean.getIdAgrupador();
        // Invocamos método que agrega las evidencias seleccionadas
        operationBinding = bindings.getOperationBinding("agregarEvidencias");
        operationBinding.getParamsMap().put("idCondicion",
                                            (Long) JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}"));
        operationBinding.getParamsMap().put("idAgrupador", idAgrupador);

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        // Actualizamos lista de evidencias, se modifica de acuerdo a FNXII-6342
        //voEvidenciasCondiciones.executeQuery();
        actualizarEvidenciasCondicion();

        // Actualizamos el estado de la condición a "Por validar"
        //En el caso de uso no estipula que se debe realizar algun cambio de Estado para atender 
        //Incidencia FNXII-5456
        //actualizarEstadoCondicionPorValidar();

        // Prendemos bander(as) con los rol(es) de Validador(es) que tiene asignada la Condición (sólo cuando es Retorno)
        if ((condicionesBean.getEsRetornoCumplirCondiciones() != null) &&
            (condicionesBean.getEsRetornoCumplirCondiciones()))
            prenderBanderasRolesCondicion();

        //Se actualiza la tabla VALIDAR_CONDICION columna FINALIZO_TAREA a 0, en todos los registros asociados
        //a la condicion y el agrupador.
        idCondicion = (Long) JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}");
        logger.warning("Valor de la condicion." + idCondicion);
        logger.warning("Valor del agrupador." + idAgrupador);
        resultado = actualizarFinalizaTareaCondicion(idCondicion, null, idAgrupador);
        if (resultado) {
            logger.log(ADFLogger.WARNING, "actualizacion correcta.");
        } else {
            logger.log(ADFLogger.WARNING, "Error al actualizar la columna FINALIZO_TAREA.");
        }
        consultarCondicionesEnModelo();
        // Cerramos popup
        getPopupAgregarEvidencia().hide();
    }
    
    public void cancelarAgregarEvidenciaActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarAgregarEvidenciaActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup de Agregar evidencia
        getPopupAgregarEvidencia().hide();
    }
    
    public void eliminarEvidenciaActionListener(ActionEvent actionEvent) {
        logger.warning("Inside eliminarEvidenciaActionListener: " + actionEvent.getComponent().getId());

        // Abrimos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupEliminarEvidencia().show(popupHints);
    }
    
    public void confirmEliminarEvidenciaActionListener(ActionEvent actionEvent) {
        logger.warning("Inside confirmEliminarEvidenciaActionListener: " + actionEvent.getComponent().getId());
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding voEvidenciasCondiciones = null;
        OperationBinding operationBinding = null;
        Long idCondicion = null;
        Long idAgrupador = null;
        Boolean resultado = Boolean.FALSE;

        // Asignación de variables
//        voEvidenciasCondiciones =
//            ADFUtils.getDCBindingContainer().findIteratorBinding("EvidenciasCondicionesVOIterator");

        // Invocamos método que elimina la evidencia seleccionada
        operationBinding = bindings.getOperationBinding("eliminarTreEvidenciaCondicion");
        operationBinding.getParamsMap().put("idTreEvidenciaCondicion",
                                            (Integer) JSFUtils.resolveExpression("#{viewScope.idEvidenciaCondicion}"));

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        // Actualizamos lista de evidencias por id condicion y id de agrupador.
        //voEvidenciasCondiciones.executeQuery();
        actualizarEvidenciasCondicion();

        // Actualizamos el estado de la condición a "Por validar"
        //actualizarEstadoCondicionPorValidar();

        // Actualizamos el estado de la condición a "Por cumplir"
        //para atender la incidencia FNXII-4683 debido a la actualizacion de la regla de negocio RN_07
        //documentada en los comentarios de la propia incidencia
        actualizarEstadoCondicionPorCumplir();


        // Prendemos bander(as) con los rol(es) de Validador(es) que tiene asignada la Condición (sólo cuando es Retorno)
        if ((condicionesBean.getEsRetornoCumplirCondiciones() != null) &&
            (condicionesBean.getEsRetornoCumplirCondiciones()))
            prenderBanderasRolesCondicion();
        //Se actualiza la tabla VALIDAR_CONDICION columna FINALIZO_TAREA a 0, en todos los registros asociados
        //a la condicion y el agrupador.
        idCondicion = (Long) JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}");
        idAgrupador = condicionesBean.getIdAgrupador();
        logger.warning("Valor de la condicion." + idCondicion);
        logger.warning("Valor del agrupador." + idAgrupador);
        resultado = actualizarFinalizaTareaCondicion(idCondicion, null, idAgrupador);
        if (resultado) {
            logger.warning("actualizacion correcta.");
        } else {
            logger.warning("Error al actualizar la columna FINALIZO_TAREA.");
        }
        consultarCondicionesEnModelo();
        // Cerramos popup
        getPopupEliminarEvidencia().hide();
    }
    
    private void prenderBanderasRolesCondicion() {
        logger.warning("Inside prenderBanderasRolesCondicion.");
        CondicionesBean condicionesBean = (CondicionesBean)JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        DCIteratorBinding voCondicionesOperacion = null;
        Row rowCondicion = null;
        
        // Asignación de variables
        voCondicionesOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CondicionesOperacionVOIterator");
        rowCondicion = voCondicionesOperacion.getCurrentRow();
        
        // Obtenemos los roles que tiene asignado la condición actual (a la que se le Agregó / Eliminó evidencia)
        if((rowCondicion.getAttribute("RequiereAsjur") != null) && ((Boolean)rowCondicion.getAttribute("RequiereAsjur")))
            condicionesBean.setEsRequiereAsjur(true);
        
        if((rowCondicion.getAttribute("RequiereSepri") != null) && ((Boolean)rowCondicion.getAttribute("RequiereSepri")))
            condicionesBean.setEsRequiereSepri(true);
        
        if((rowCondicion.getAttribute("RequiereAed") != null) && ((Boolean)rowCondicion.getAttribute("RequiereAed")))
            condicionesBean.setEsRequiereAed(true);
        
        if((rowCondicion.getAttribute("RequierePct") != null) && ((Boolean)rowCondicion.getAttribute("RequierePct")))
            condicionesBean.setEsRequierePct(true);
        
        if((rowCondicion.getAttribute("RequiereSupervision") != null) && ((Boolean)rowCondicion.getAttribute("RequiereSupervision")))
            condicionesBean.setEsRequiereSupervision(true);
        
        if((rowCondicion.getAttribute("RequiereCofi") != null) && ((Boolean)rowCondicion.getAttribute("RequiereCofi")))
            condicionesBean.setEsRequiereCofi(true);
        
        //Se agregan nuevas bandera para Fase 3.
        if((rowCondicion.getAttribute("RequiereAnalistaDaeci") != null) && ((Boolean)rowCondicion.getAttribute("RequiereAnalistaDaeci")))
            condicionesBean.setEsRequiereAnalistaDaeci(true);
        
        if((rowCondicion.getAttribute("RequiereAnalistaOfic") != null) && ((Boolean)rowCondicion.getAttribute("RequiereAnalistaOfic")))
            condicionesBean.setEsRequiereAnalistaOfic(true);
        
        if((rowCondicion.getAttribute("RequiereAnalistaFinam") != null) && ((Boolean)rowCondicion.getAttribute("RequiereAnalistaFinam")))
            condicionesBean.setEsRequiereAnalistaFinam(true);
        
        if((rowCondicion.getAttribute("RequiereEspAmbiental") != null) && ((Boolean)rowCondicion.getAttribute("RequiereEspAmbiental")))
            condicionesBean.setEsRequiereEspAmbiental(true);
        
        if((rowCondicion.getAttribute("RequiereEjecutivoFinam") != null) && ((Boolean)rowCondicion.getAttribute("RequiereEjecutivoFinam")))
            condicionesBean.setEsRequiereEjecutivoFinam(true);
        
        if((rowCondicion.getAttribute("RequiereSegCred") != null) && ((Boolean)rowCondicion.getAttribute("RequiereSegCred")))
            condicionesBean.setEsRequiereSegCred(true);
    }
    
    private void actualizarEstadoCondicionPorValidar() {
        Long idCondicion = (Long)JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}");
        Long idEstadoTcc = (JSFUtils.resolveExpression("#{bindings.IdEstadoTcc.inputValue}") == null) ? -1 :
            (Long)JSFUtils.resolveExpression("#{bindings.IdEstadoTcc.inputValue}");
        Long idSubEstadoTcc = (JSFUtils.resolveExpression("#{bindings.IdSubEstadoTcc.inputValue}") == null) ? -1 :
            (Long)JSFUtils.resolveExpression("#{bindings.IdSubEstadoTcc.inputValue}");
        
        // RN: Cuando una evidencia se ve afectada por una eliminación por el mismo identificador, 
        // el estado de la condición tiene que cambiar a "Por validar". 
        // Lo anterior aplica para los eventos Agregar/Eliminar Evidencia
        // Incidencia FNXII-3160: sólo se cambia si, para la Condición actual, el Estado es diferente a (23) 
        // o el Sub-Estado es diferente a (21). Puesto que el servicio falla si se manda actualizar al mismo estado/sub estado.
        if((idEstadoTcc.intValue() != 23) || (idSubEstadoTcc.intValue() != 21))
            actualizarEstadoTCC(idCondicion, Long.valueOf("23"), Long.valueOf("21"));
        
        // Cambiamos el atributo "Accion", de la Condición seleccionada, para actualizar la imagen
        ADFUtils.findControlBinding("Accion").setInputValue("porvalidar");
    }
    
    private void actualizarEstadoCondicionPorCumplir() {
        logger.warning("Entra en actualizarEstadoCondicionPorCumplir");
        CondicionesBean condicionesBean = (CondicionesBean)JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        Long idCondicion = (Long)JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}");
        Long idEstadoTcc = (JSFUtils.resolveExpression("#{bindings.IdEstadoTcc.inputValue}") == null) ? -1 :
            (Long)JSFUtils.resolveExpression("#{bindings.IdEstadoTcc.inputValue}");
        Long idSubEstadoTcc = (JSFUtils.resolveExpression("#{bindings.IdSubEstadoTcc.inputValue}") == null) ? -1 :
            (Long)JSFUtils.resolveExpression("#{bindings.IdSubEstadoTcc.inputValue}");
        
        Long idAgrupador = null;
        if(null != condicionesBean.getIdAgrupador()){
        idAgrupador = condicionesBean.getIdAgrupador();
            logger.log(ADFLogger.WARNING, "Valor agrupador." + idAgrupador);
        }else{
            logger.log(ADFLogger.WARNING, "El idAgrupador es nulo.");
        }
        
        // RN: Cuando una evidencia se ve afectada por una eliminación por el mismo identificador, 
        // el estado de la condición tiene que cambiar a "Por validar". 
        // Lo anterior aplica para los eventos Agregar/Eliminar Evidencia
        // Incidencia FNXII-3160: sólo se cambia si, para la Condición actual, el Estado es diferente a (23) 
        // o el Sub-Estado es diferente a (21). Puesto que el servicio falla si se manda actualizar al mismo estado/sub estado.
        if((idEstadoTcc.intValue() == 23) && (idSubEstadoTcc.intValue() == 29))
            actualizaEstadoCondicionPorCumplir(Integer.valueOf("23"), Integer.valueOf("29"), idAgrupador, idCondicion);
        
        // Cambiamos el atributo "Accion", de la Condición seleccionada, para actualizar la imagen
        ADFUtils.findControlBinding("Accion").setInputValue("porvalidar");
    }
    
    public void cancelarEliminarEvidenciaActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarEliminarEvidenciaActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup
        getPopupEliminarEvidencia().hide();
    }
    
    public String aceptarFinalizarTareaCumplirCondicionesAction() {
        logger.log(ADFLogger.TRACE, "Inside aceptarFinalizarTareaCumplirCondicionesAction.");
        CondicionesBean condicionesBean = (CondicionesBean)JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        
        // RN: Se podrá finalizar la tarea siempre y cuando: 1) se haya agregado la evidencia en cada condición o 
        // se haya ingresado una observación de cumplimiento en sustitución de la evidencia 
        // 2) y el estado de todas las condiciones sea "Con evidencia". 
        // Modificación de la RN 11/feb/2016, en base a junta con Eduardo 
        // Se podrá finalizar la tarea siempre y cuando: 
        // 1) No exista una Condicion en estado "Por validar" 
        // 2) El estado de todas las Condiciones en ValidacionCondicion sea true
        //if(!existeCondicionConEstado("porvalidar")) {
            
            if(validarCondicionesEstadoValidacion()) { // Se valida que EstadoValidacionCondicion sea true para todas las condiciones 
                
                if(!condicionesBean.getEsRetornoCumplirCondiciones()) {
                    // Actualizamos flag de roles por Validador - sólo cuando NO es retorno
                    // (verificamos si algunos de los roles se debe ir como true en el payload)
                    actualizarFlagRol("RequiereAsjur", "requiereASJUR");
                    actualizarFlagRol("RequiereSepri", "requiereSEPRI");
                    actualizarFlagRol("RequiereAed", "requiereAED");
                    actualizarFlagRol("RequierePct", "requierePCT");
                    actualizarFlagRol("RequiereSupervision", "requiereSupervision");
                    actualizarFlagRol("RequiereCofi", "requiereCOFI");
                    
                    //Se agregan nuevas asignaciones de bandera por nuevas catagorias de condicion y validadores de Fase 3.
                    actualizarFlagRol("RequiereAnalistaDaeci", "requiereDAECI");
                    actualizarFlagRol("RequiereAnalistaOfic", "requiereOFIC");
                    actualizarFlagRol("RequiereAnalistaFinam", "requiereAnalistaFINAM");
                    actualizarFlagRol("RequiereEspAmbiental", "requiereAmbiental");
                    actualizarFlagRol("RequiereEjecutivoFinam", "requiereEjecutivoFINAM");
                    actualizarFlagRol("RequiereSegCred", "requiereSegCred");
                }
                else{
                    
                    // Para el caso de un Retorno, regresamos los roles que se prendieron al Agregar/Eliminar una Evidencia
                    // Aquí se realiza el merge con todos los roles prendidos
                    actualizarFlagPayload("requiereASJUR", condicionesBean.getEsRequiereAsjur());
                    actualizarFlagPayload("requiereSEPRI", condicionesBean.getEsRequiereSepri());
                    actualizarFlagPayload("requiereAED", condicionesBean.getEsRequiereAed());
                    actualizarFlagPayload("requierePCT", condicionesBean.getEsRequierePct());
                    actualizarFlagPayload("requiereSupervision", condicionesBean.getEsRequiereSupervision());
                    actualizarFlagPayload("requiereCOFI", condicionesBean.getEsRequiereCofi());
                    
                    //Se agregan nuevas asignaciones de bandera por nuevas catagorias de condicion y validadores de Fase 3.
                    actualizarFlagPayload("requiereDAECI", condicionesBean.getEsRequiereAnalistaDaeci());
                    actualizarFlagPayload("requiereOFIC", condicionesBean.getEsRequiereAnalistaOfic());
                    actualizarFlagPayload("requiereAnalistaFINAM", condicionesBean.getEsRequiereAnalistaFinam());
                    actualizarFlagPayload("requiereAmbiental", condicionesBean.getEsRequiereEspAmbiental());
                    actualizarFlagPayload("requiereEjecutivoFINAM", condicionesBean.getEsRequiereEjecutivoFinam());
                    actualizarFlagPayload("requiereSegCred", condicionesBean.getEsRequiereSegCred());
                }
    
                // Invocar método para cargar documentos en onBase
                cargarDocumentosOnBase();
                
                // Se comenta por la FNXII-6734
                // El guardado de la fecha de vigencia se realiza automaticamente en el eventChangeListener
                //actualizarCampos();
                
                invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                returnAction = invokeActionBean.invokeOperation();
            }
            else{ 
                //JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_LISTA_VALIDARCONDICIONES"));
                logger.warning("No se puede finalizar las condiciones si no esta validadas");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_05_CUMPLIR_CONDICIONES"));
            }
//        }
//        else{
//            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_05_CUMPLIR_CONDICIONES"));
//        }
         
        // Cerramos popup de confirmar finalizar tarea
        getPopupConfirmarFinalizarTarea().hide();
        
        return returnAction;
    }
    
    private void actualizarFlagRol(String nombreAtributoRol, String nombreFlagPayload) {
        // Actualizamos flag de rol por Validador de todas las Condiciones. 
        // Estos son usados para levantar pantalla Validar Condiciones
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Verificamos si el rol requiereASJUR se debe ir como true en el payload
        operationBinding = bindings.getOperationBinding("requiereRol");
        operationBinding.getParamsMap().put("nombreAtributoRol", nombreAtributoRol);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else if((operationBinding.getResult() != null) && ((Boolean)operationBinding.getResult())) {
            logger.warning("Actualizar variable " + nombreFlagPayload);
            actualizarFlagPayload(nombreFlagPayload, true);
        }   
    }
    
    public void confirmaListaParaValidarTodas(ActionEvent actionEvent) {
        logger.warning("*Inf, inicia metodo confirmaListaParaValidarTodas ");
        String userId = null;
        String nombreUsuario = null;
        String idTarea = null;
        String idRol = null;
        Long idAgrupador = null;
        String observacion = null;
        
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        Integer idRowSeleccionado = condicionesBean.getIdCondicionSeleccionada();
        Long idCondicionSeleccionada = 0L;
        logger.warning("*Inf, idCondicion seleccionada = " + idRowSeleccionado);
        
        DCIteratorBinding condicionesOperacionVOIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("CondicionesOperacionVOIterator");                    
        Row[] CondicionesOperacionRows = condicionesOperacionVOIterator.getAllRowsInRange();        
        logger.warning("condiciones a validar: "+CondicionesOperacionRows.length);
        
        if(condicionesOperacionVOIterator == null){
            logger.warning("*Inf, No se recuperaron condiciones del iterador");
            return;
        }
            
            
            if (null != ADFContext.getCurrent().getSecurityContext().getUserName()) { 
                //Se obtiene el codigo de usuario de la sesion actual 17sep2019
                userId = ADFContext.getCurrent().getSecurityContext().getUserName().toLowerCase();
                logger.log(ADFLogger.WARNING, "userLogin refreshDetalle: "+userId);
            } else {
                logger.log(ADFLogger.WARNING, "El id de usuario es nulo.");
            }
            if (null != ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName()) {
                nombreUsuario = ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName();
                logger.log(ADFLogger.WARNING, "Valor nombreUsuario." + nombreUsuario);
            } else {
                logger.log(ADFLogger.WARNING, "El nombre de usuario es nulo.");
            }
            if (null != condicionesBean.getIdTarea()) {
                idTarea = condicionesBean.getIdTarea();
                logger.log(ADFLogger.WARNING, "Valor idTarea." + idTarea);
            } else {
                logger.log(ADFLogger.WARNING, "El id de tarea es nulo");
            }
            if (null != condicionesBean.getIdRol()) {
                idRol = condicionesBean.getIdRol();
                logger.log(ADFLogger.WARNING, "Valor idRol." + idRol);
            } else {
                logger.log(ADFLogger.WARNING, "El id de Rol es nulo.");
            }
            if (null != condicionesBean.getIdAgrupador()) {
                idAgrupador = condicionesBean.getIdAgrupador();
                logger.log(ADFLogger.WARNING, "Valor agrupador." + idAgrupador);
            } else {
                logger.log(ADFLogger.WARNING, "El idAgrupador es nulo.");
            }
            //if (null != condicionesBean.getObservacionValidarCondicion()) {
                observacion = condicionesBean.getObservacionValidarCondicion();
                logger.log(ADFLogger.WARNING, "Valor observacion." + observacion);
            //} //else {
            //    logger.log(ADFLogger.WARNING, "La observacion es nula");
            // }
            
            try{
                
                for(Row row : CondicionesOperacionRows){
                   if(idRowSeleccionado == null){
                        idRowSeleccionado = (Integer)row.getAttribute("Id");
                    }
                    if (idRowSeleccionado != null && idRowSeleccionado.intValue() == ((Integer)row.getAttribute("Id")).intValue() ){
                        idCondicionSeleccionada = (Long)row.getAttribute("IdCondicion");
                    }
                    
                      Boolean estadoCondicion = (Boolean)row.getAttribute("EstadoValidacionCondicion");
                      Long idCondicion = (Long)row.getAttribute("IdCondicion");
                          
                      logger.warning("Condicion: "+idCondicion+" EstadoValidacionCondicion: "+estadoCondicion);
                      
                    if((estadoCondicion != null) && estadoCondicion)
                        logger.log(ADFLogger.WARNING, "El estado de la condicion no es por validar");
                    else
                        validarCondicion(idCondicion, userId, nombreUsuario, idTarea, idRol, idAgrupador);                                                            
                }
                //Hacemos que consulte toda la tabla
                consultarCondicionesByIdOperacionIdRolIdEvento(0L, condicionesBean.getOperacionId().longValue(),
                                                                                   Long.valueOf(idRol), idAgrupador, userId, false);
                //obtenerFilaSeleccionada(idRowSeleccionado);
                
                Row row = null;
                if (condicionesBean.getIdCondicionSeleccionada()!=null){
                    logger.warning( "refresh detalle by idRow seleccionado: " + condicionesBean.getIdCondicionSeleccionada() );
                    row = getRowByIdRow(condicionesBean.getIdCondicionSeleccionada(), condicionesBean);
                }else{
                    logger.warning( "refresh detalle by idCondicion: " + idCondicionSeleccionada );
                    row = getRowByIdCondicion(idCondicionSeleccionada.intValue(), condicionesBean);
                }
                
                refreshRow(row, idCondicionSeleccionada, condicionesBean.getOperacionId().longValue(), 
                                                                condicionesBean.getIdAgrupador(),
                                                               Long.valueOf(condicionesBean.getIdRol()), 
                                                                userId);
                
                
            }catch(Exception e){
                logger.warning("error al recpoerar las condiciones: ", e);
            }
        
             //Limpiamos valor de observación
             condicionesBean.setObservacionValidarCondicion(null);
             consultarCondicionesEnModelo();
             
       logger.warning("*inf, termina metodo confirmaListaParaValidarTodas");
    }
    
    
    private void validarCondicion( Long idCondicion, String userId, String nombreUsuario, String idTarea, String idRol, Long idAgrupador){
        logger.warning("*Inf, Inicia metodo validarCondicion: ");
        logger.warning("*Inf, idCondicion: "+idCondicion);
        logger.warning("*Inf, idAgrupador: "+idAgrupador);
        Boolean esValidador = null;
        String observacion = null;
        Boolean estado = null;
        
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        
        // RN: El sistema detecta que no se ingresaron las evidencias y/o observaciones de cumplimiento.
        try {
            if (validarListaParaValidar(idCondicion)) {
                // Asignación de variables               
                esValidador = Boolean.FALSE;              
                estado = Boolean.TRUE;
              
                // Invocamos método que actualiza estado de la Condición
                if (validarCondicion(idCondicion, userId, nombreUsuario, esValidador, observacion, estado, idTarea,
                                     idRol, idAgrupador)) {

                    JSFUtils.addFacesInformationMessage(getStringFromBundle("MSG_VALIDACION_DE_ESTADO"));
                    //actualizarEstadoTCC(idCondicion, idEstadoTcc, subEstado);
                    // Debido a que se actualizó el estado de la Condición, regeneramos tabla con la listas de las mismas
                    // Puesto que se llama desde la pantalla "Cumplir condiciones", la bandera esIdRolNecesario se manda false
                    // El parametro condicionesBean.getIdAgrupador()sustituye el valor del idEvento
                    logger.log(ADFLogger.WARNING, "Valor idRol." + idRol);
                    logger.log(ADFLogger.WARNING, "Valor idAgrupador." + idAgrupador);
                    logger.log(ADFLogger.WARNING, "Valor userId." + userId);
                    //consultarCondicionesByIdOperacionIdRolIdEvento(idCondicion, condicionesBean.getOperacionId().longValue(),
                    //                                               Long.valueOf(idRol), idAgrupador, userId, false);

                    //Se deja la condicion como current para mantenerla seleccionada
                    //obtenerFilaSeleccionada(condicionesBean.getIdCondicionSeleccionada());
                } else {
                    JSFUtils.addFacesErrorMessage("Error al validar la condicion.");
                }
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al validar las condiciones", e);
        }
       
        // Cerramos popup
        getPopUpConfirmarCondicionesAValidar().hide();
    }
    
    
    
    
    public void listaParaValidarEnableOpEvidenciaActionListener(ActionEvent actionEvent) {
        logger.warning("Inside listaParaValidarEnableOpEvidenciaActionListener: " + actionEvent.getComponent().getId());
        Boolean esEstadoValidacion = (Boolean)JSFUtils.resolveExpression("#{bindings.EstadoValidacionCondicion.inputValue}");
        // Verificamos si la Condición está validada. Si lo está, le actualizamos el estado a false.
        if((esEstadoValidacion != null) && esEstadoValidacion)
            logger.log(ADFLogger.WARNING, "El estado de la condicion no es por validar");
        else
            listaParaValidar();
    }
    
    public void editarCondicionEnableOpEvidenciaActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside listaParaValidarEnableOpEvidenciaActionListener: " + actionEvent.getComponent().getId());
        Boolean esEstadoValidacion = (Boolean)JSFUtils.resolveExpression("#{bindings.EstadoValidacionCondicion.inputValue}");
        logger.log(ADFLogger.WARNING, "Valor de Estado de la evaluacion :" + esEstadoValidacion);
        // Verificamos si la Condición está validada. Si lo está, le actualizamos el estado a false.
        if((esEstadoValidacion != null) && esEstadoValidacion)
            enableOperacionesEvidencia();
        else
            logger.log(ADFLogger.WARNING, "El estado de la condicion no esta validada");
    }
    
    private void enableOperacionesEvidencia() {
        logger.warning("Ingresa metodo enableOperacionesEvidencia");
        CondicionesBean condicionesBean = (CondicionesBean)JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        Long idCondicion = 0L;
        String userId = null;
        String nombreUsuario = null;
        Boolean esValidador = null;
        String observacion = null;
        Boolean estado = null;
        String idTarea = null;
        String idRol = null;
        Long idAgrupador = null;
        
        // Asignación de variables
        if (JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}") != null){
            idCondicion = (Long)JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}");
        }
        
        //userId = ADFContext.getCurrent().getSecurityContext().getUserName();
        //Se obtiene el codigo de usuario de la sesion actual 17sep2019
        userId = ADFContext.getCurrent().getSecurityContext().getUserName().toLowerCase();
        logger.log(ADFLogger.WARNING, "userLogin enableOperacionesEvidencia: "+userId);
        
        nombreUsuario = ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName();
        esValidador = Boolean.FALSE;
        observacion = condicionesBean.getObservacionValidarCondicion();
        estado = Boolean.FALSE;
        idTarea = condicionesBean.getIdTarea();
        idRol = condicionesBean.getIdRol();
        idAgrupador = condicionesBean.getIdAgrupador();
        // Actualizamos el estado de la Condición a false
        //Se actualiza la tabla VALIDAR_CONDICION columna FINALIZO_TAREA a 0, en todos los registros asociados 
        //a la condicion y el agrupador.
        if(validarCondicion(idCondicion, userId, nombreUsuario, esValidador, observacion, estado, idTarea, idRol, idAgrupador)) {
            
            JSFUtils.addFacesInformationMessage(getStringFromBundle("MSG_ELIMINAR_VALIDACION_DE_ESTADO"));
            
            
            // Debido a que se actualizó el estado de la Condición, regeneramos tabla con la listas de las mismas
            // Puesto que se llama desde la pantalla "Cumplir condiciones", la bandera esIdRolNecesario se manda false
            // El parametro condicionesBean.getIdAgrupador()sustituye el valor del idEvento
        }
        /*
        consultarCondicionesByIdOperacionIdRolIdEvento(idCondicion, condicionesBean.getOperacionId().longValue(), 
                                                       Long.valueOf(condicionesBean.getIdRol()), 
                                                       condicionesBean.getIdAgrupador(), userId, false);
        */
        Row row = null;
        if (condicionesBean.getIdCondicionSeleccionada()!=null){
            logger.warning( "refresh detalle by idRow seleccionado: " + idCondicion );
            row = getRowByIdRow(condicionesBean.getIdCondicionSeleccionada(), condicionesBean);
        }else{
            logger.warning( "refresh detalle by idCondicion: " + idCondicion );
            row = getRowByIdCondicion(idCondicion.intValue(), condicionesBean);
        }
        
        refreshRow(row, idCondicion, condicionesBean.getOperacionId().longValue(), 
                                                        condicionesBean.getIdAgrupador(),
                                                       Long.valueOf(condicionesBean.getIdRol()), 
                                                        
                                                        userId);
        
        consultarCondicionesEnModelo();
        logger.warning("Finaliza metodo enableOperacionesEvidencia");
    }
    
    private void refreshRow(Row row, long idCondicion, long idOperacion, long idAgrupador, Long idRol, String loginUsuario){
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("obtenerRow");
        operationBinding.getParamsMap().put("row", row);
        operationBinding.getParamsMap().put("idCondicion", idCondicion);
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("idAgrupador", idAgrupador);
        operationBinding.getParamsMap().put("idRol", idRol);
        operationBinding.getParamsMap().put("loginUsuario", loginUsuario);
        Row refreshRow = (Row) operationBinding.execute();
        
    }
    
    private void listaParaValidar() {
        logger.warning("Inside listaParaValidar.");

        // Abrimos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarValidar().show(popupHints);
    }
    
    public void confirmListaParaValidarActionListener2(ActionEvent actionEvent) {
        
        logger.log(ADFLogger.WARNING, "Inside confirmListaParaValidarActionListener2.");
        logger.warning(
                   "Inside confirmListaParaValidarActionListener2: " + actionEvent.getComponent().getId());
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        Long idCondicion = null;
        String userId = null;
        String nombreUsuario = null;
        Boolean esValidador = null;
        String observacion = null;
        Boolean estado = null;
        String idTarea = null;
        String idRol = null;
        Long idAgrupador = null;
        Long idEstadoTcc = null;
        Long subEstado = null;
        Boolean cambioEstado = Boolean.FALSE;
        // RN: El sistema detecta que no se ingresaron las evidencias y/o observaciones de cumplimiento.
        try {
            if (validarListaParaValidar(null)) {
                // Asignación de variables
                if (null != JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}")) {
                    idCondicion = (Long) JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}");
                    logger.log(ADFLogger.WARNING, "Valor idCondicion." + idCondicion);
                } else {
                    logger.log(ADFLogger.WARNING, "El id de la condicion es nulo.");
                }
                if (null != ADFContext.getCurrent().getSecurityContext().getUserName()) { 
                    //Se obtiene el codigo de usuario de la sesion actual 17sep2019
                    userId = ADFContext.getCurrent().getSecurityContext().getUserName().toLowerCase();
                    logger.log(ADFLogger.WARNING, "userLogin confirmListaParaValidarActionListener2: "+userId);
                } else {
                    logger.log(ADFLogger.WARNING, "El id de usuario es nulo.");
                }
                if (null != ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName()) {
                    nombreUsuario = ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName();
                    logger.log(ADFLogger.WARNING, "Valor nombreUsuario." + nombreUsuario);
                } else {
                    logger.log(ADFLogger.WARNING, "El nombre de usuario es nulo.");
                }
                esValidador = Boolean.FALSE;
                if (null != condicionesBean.getObservacionValidarCondicion()) {
                    observacion = condicionesBean.getObservacionValidarCondicion();
                    logger.log(ADFLogger.WARNING, "Valor observacion." + observacion);
                } else {
                    logger.log(ADFLogger.WARNING, "La observacion es nula");
                }
                estado = Boolean.TRUE;
                if (null != condicionesBean.getIdTarea()) {
                    idTarea = condicionesBean.getIdTarea();
                    logger.log(ADFLogger.WARNING, "Valor idTarea." + idTarea);
                } else {
                    logger.log(ADFLogger.WARNING, "El id de tarea es nulo");
                }
                if (null != condicionesBean.getIdRol()) {
                    idRol = condicionesBean.getIdRol();
                    logger.log(ADFLogger.WARNING, "Valor idRol." + idRol);
                } else {
                    logger.log(ADFLogger.WARNING, "El id de Rol es nulo.");
                }
                if (null != condicionesBean.getIdAgrupador()) {
                    idAgrupador = condicionesBean.getIdAgrupador();
                    logger.log(ADFLogger.WARNING, "Valor agrupador." + idAgrupador);
                } else {
                    logger.log(ADFLogger.WARNING, "El idAgrupador es nulo.");
                }

                // Invocamos método que actualiza estado de la Condición
                if (validarCondicion(idCondicion, userId, nombreUsuario, esValidador, observacion, estado, idTarea,
                                     idRol, idAgrupador)) {

                    JSFUtils.addFacesInformationMessage(getStringFromBundle("MSG_VALIDACION_DE_ESTADO"));
                    //actualizarEstadoTCC(idCondicion, idEstadoTcc, subEstado);
                    // Debido a que se actualizó el estado de la Condición, regeneramos tabla con la listas de las mismas
                    // Puesto que se llama desde la pantalla "Cumplir condiciones", la bandera esIdRolNecesario se manda false
                    // El parametro condicionesBean.getIdAgrupador()sustituye el valor del idEvento
                    logger.log(ADFLogger.WARNING, "Valor idCondicion." + idCondicion);
                    logger.log(ADFLogger.WARNING, "Valor idRol." + idRol);
                    logger.log(ADFLogger.WARNING, "Valor idAgrupador." + idAgrupador);
                    logger.log(ADFLogger.WARNING, "Valor userId." + userId);
                    //consultarCondicionesByIdOperacionIdRolIdEvento(idCondicion,
                    //                                               condicionesBean.getOperacionId().longValue(),
                    //                                               Long.valueOf(idRol), idAgrupador, userId, false);


                    /*if (condicionesBean.getIdCondicionSeleccionada()!=null){
                        logger.warning( "refresh detalle by idRow seleccionado: " + idCondicion );
                        refreshDetalleFilaSeleccionada(condicionesBean.getIdCondicionSeleccionada(), condicionesBean);
                    }else{
                        logger.warning( "refresh detalle by idCondicion: " + idCondicion );
                        refreshDetalleByIdCondicion(idCondicion.intValue(), condicionesBean);
                    }*/
                    
                    Row row = null;
                    if (condicionesBean.getIdCondicionSeleccionada()!=null){
                        logger.warning( "refresh detalle by idRow seleccionado: " + idCondicion );
                        row = getRowByIdRow(condicionesBean.getIdCondicionSeleccionada(), condicionesBean);
                    }else{
                        logger.warning( "refresh detalle by idCondicion: " + idCondicion );
                        row = getRowByIdCondicion(idCondicion.intValue(), condicionesBean);
                    }
                    
                    refreshRow(row, idCondicion, condicionesBean.getOperacionId().longValue(), 
                                                                    condicionesBean.getIdAgrupador(),
                                                                   Long.valueOf(condicionesBean.getIdRol()), 
                                                                    userId);
                    
                } else {
                    JSFUtils.addFacesErrorMessage("Error al validar la condicion.");
                }
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al validar las condiciones", e);
        }
        // Limpiamos valor de observación
        condicionesBean.setObservacionValidarCondicion(null);
        consultarCondicionesEnModelo();
        // Cerramos popup
        getPopupConfirmarValidar().hide();
    }
    
    /*
    public void confirmListaParaValidarActionListener(ActionEvent actionEvent) {
        logger.warning(
                   "Inside confirmListaParaValidarActionListener: " + actionEvent.getComponent().getId());
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        Long idCondicion = null;
        String userId = null;
        String nombreUsuario = null;
        Boolean esValidador = null;
        String observacion = null;
        Boolean estado = null;
        String idTarea = null;
        String idRol = null;
        Long idAgrupador = null;
        Long idEstadoTcc = null;
        Long subEstado = null;
        Boolean cambioEstado = Boolean.FALSE;
        // RN: El sistema detecta que no se ingresaron las evidencias y/o observaciones de cumplimiento.
        try {
            if (validarListaParaValidar(null)) {
                // Asignación de variables
                if (null != JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}")) {
                    idCondicion = (Long) JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}");
                    logger.log(ADFLogger.WARNING, "Valor idCondicion." + idCondicion);
                } else {
                    logger.log(ADFLogger.WARNING, "El id de la condicion es nulo.");
                }
                if (null != ADFContext.getCurrent().getSecurityContext().getUserName()) {
                    userId = ADFContext.getCurrent().getSecurityContext().getUserName();
                    logger.log(ADFLogger.WARNING, "Valor userId." + userId);
                } else {
                    logger.log(ADFLogger.WARNING, "El id de usuario es nulo.");
                }
                if (null != ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName()) {
                    nombreUsuario = ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName();
                    logger.log(ADFLogger.WARNING, "Valor nombreUsuario." + nombreUsuario);
                } else {
                    logger.log(ADFLogger.WARNING, "El nombre de usuario es nulo.");
                }
                esValidador = Boolean.FALSE;
                if (null != condicionesBean.getObservacionValidarCondicion()) {
                    observacion = condicionesBean.getObservacionValidarCondicion();
                    logger.log(ADFLogger.WARNING, "Valor observacion." + observacion);
                } else {
                    logger.log(ADFLogger.WARNING, "La observacion es nula");
                }
                estado = Boolean.TRUE;
                if (null != condicionesBean.getIdTarea()) {
                    idTarea = condicionesBean.getIdTarea();
                    logger.log(ADFLogger.WARNING, "Valor idTarea." + idTarea);
                } else {
                    logger.log(ADFLogger.WARNING, "El id de tarea es nulo");
                }
                if (null != condicionesBean.getIdRol()) {
                    idRol = condicionesBean.getIdRol();
                    logger.log(ADFLogger.WARNING, "Valor idRol." + idRol);
                } else {
                    logger.log(ADFLogger.WARNING, "El id de Rol es nulo.");
                }
                if (null != condicionesBean.getIdAgrupador()) {
                    idAgrupador = condicionesBean.getIdAgrupador();
                    logger.log(ADFLogger.WARNING, "Valor agrupador." + idAgrupador);
                } else {
                    logger.log(ADFLogger.WARNING, "El idAgrupador es nulo.");
                }

                // Invocamos método que actualiza estado de la Condición
                if (validarCondicion(idCondicion, userId, nombreUsuario, esValidador, observacion, estado, idTarea,
                                     idRol, idAgrupador)) {

                    JSFUtils.addFacesInformationMessage(getStringFromBundle("MSG_VALIDACION_DE_ESTADO"));
                    //actualizarEstadoTCC(idCondicion, idEstadoTcc, subEstado);
                    // Debido a que se actualizó el estado de la Condición, regeneramos tabla con la listas de las mismas
                    // Puesto que se llama desde la pantalla "Cumplir condiciones", la bandera esIdRolNecesario se manda false
                    // El parametro condicionesBean.getIdAgrupador()sustituye el valor del idEvento
                    logger.log(ADFLogger.WARNING, "Valor idRol." + idRol);
                    logger.log(ADFLogger.WARNING, "Valor idAgrupador." + idAgrupador);
                    logger.log(ADFLogger.WARNING, "Valor userId." + userId);
                    consultarCondicionesByIdOperacionIdRolIdEvento(condicionesBean.getOperacionId().longValue(),
                                                                   Long.valueOf(idRol), idAgrupador, userId, false);

                    //Se deja la condicion como current para mantenerla seleccionada
                    obtenerFilaSeleccionada(condicionesBean.getIdCondicionSeleccionada());
                } else {
                    JSFUtils.addFacesErrorMessage("Error al validar la condicion.");
                }
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al validar las condiciones", e);
        }
        // Limpiamos valor de observación
        condicionesBean.setObservacionValidarCondicion(null);
        consultarCondicionesEnModelo();
        // Cerramos popup
        getPopupConfirmarValidar().hide();
    }
*/
    
    public void cancelarListaParaValidarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarListaParaValidarActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup
        getPopupConfirmarValidar().hide();
    }
    
    private Boolean validarListaParaValidar(Long idCondicion) {
        logger.warning("Entra en validarListaParaValidar");
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        Boolean esDatosCorrectos = Boolean.TRUE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long numEvidencias = null;
        Long numObservaciones = null;
       
         
        Long idAgrupador = null;        
        idAgrupador = condicionesBean.getIdAgrupador();
        
        idCondicion = (idCondicion != null) ? idCondicion 
                    :(Long)JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}");
        
        // Obtenemos el número de evidencias agregadas
        //numEvidencias = (Long) JSFUtils.resolveExpression("#{bindings.EvidenciasCondicionesVO.estimatedRowCount}");        
        operationBinding = bindings.getOperationBinding("obtenerEvidenciasCondicionPorIdAgrupador");
        operationBinding.getParamsMap().put("idCondicion", idCondicion);
        operationBinding.getParamsMap().put("idAgrupador", idAgrupador);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            numEvidencias = Long.valueOf(0);
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else {
            numEvidencias = (Long)operationBinding.getResult();
        }
        // Obtenemos el número de observaciones agregadas
        //operationBinding = bindings.getOperationBinding("obtenerEstimatedRowCount");
        //Se obtienen el numero de observaciones agregada por condicion y agrupador
        operationBinding = bindings.getOperationBinding("obtenerObservacionCondicionPorIdAgrupador");
        operationBinding.getParamsMap().put("idCondicion", idCondicion);
        operationBinding.getParamsMap().put("idAgrupador", idAgrupador);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            numObservaciones = Long.valueOf(0);
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else {
            numObservaciones = (Long)operationBinding.getResult();
        }
        
        // Validación
        if((numEvidencias.intValue() < 1) && (numObservaciones.intValue() < 1)) {
            
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_02_CUMPLIR_CONDICIONES"));
        }
        
        return esDatosCorrectos;
    }
    /*** END Pantalla Cumplir condiciones ***/
    
    
    /*** START Pantalla Validar condiciones ***/
    public void validarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside validarActionListener: " + actionEvent.getComponent().getId());
        logger.log(ADFLogger.WARNING, "Inside validarActionListener: " + actionEvent.getComponent().getId());

        // Abrimos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarValidar().show(popupHints);
    }
    
    public void eliminarValidacionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside eliminarValidacionActionListener: " + actionEvent.getComponent().getId());

        // Abrimos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupEliminarValidacion().show(popupHints);
    }
    
    /**
     *Metodo de validacion individual de la condicion
     * @param actionEvent
     */
    public void confirmValidarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside confirmValidarActionListener: " + actionEvent.getComponent().getId());
        CondicionesBean condicionesBean = (CondicionesBean)JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        List<String> budleKeyErroList = new ArrayList<String>();
        
        Long idCondicion = null;
        String userId = null;
        String nombreUsuario = null;
        Boolean esValidador = null;
        String observacion = null;
        Boolean estado = null;
        String idTarea = null;
        String idRol = null;
        Long idAgrupador = null;
        Boolean isValidador = Boolean.FALSE;
        
        // Asignación de variables
        idCondicion = (Long)JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}");
        //Se obtiene el codigo de usuario de la sesion actual 17sep2019
        userId = ADFContext.getCurrent().getSecurityContext().getUserName().toLowerCase();
        logger.log(ADFLogger.WARNING, "userLogin confirmValidarActionListener: "+userId);
        nombreUsuario = ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName();
        esValidador = Boolean.TRUE;
        observacion = condicionesBean.getObservacionValidarCondicion();
        estado = Boolean.TRUE;
        idTarea = condicionesBean.getIdTarea();
        idRol = condicionesBean.getIdRol();
        idAgrupador = condicionesBean.getIdAgrupador(); 
        logger.log(ADFLogger.WARNING, "Valor de la condicion : " + idCondicion);
        logger.log(ADFLogger.WARNING, "Valor del rol : " + idRol);
        logger.log(ADFLogger.WARNING, "id de usuario : " + userId);
        // Invocamos método que actualiza estado de la Condición
        if(validarCondicion(idCondicion, userId, nombreUsuario, esValidador, observacion, estado, idTarea, idRol, idAgrupador)) {
            JSFUtils.addFacesInformationMessage(getStringFromBundle("MSG_VALIDACION_DE_ESTADO"));
            
            //Metodo que indica si el validador ha validado la condicion.
            isValidador = existeValidador(idCondicion, idRol, userId);
            if(!isValidador){
                condicionesBean.setCondicionValidada(Boolean.FALSE);
            }else{
                condicionesBean.setCondicionValidada(Boolean.TRUE);
            }
            // Debido a que se actualizó el estado de la Condición, regeneramos tabla con la listas de las mismas
            // El parametro condicionesBean.getIdAgrupador()sustituye el valor del idEvento
            //consultarCondicionesByIdOperacionIdRolIdEvento(idCondicion, condicionesBean.getOperacionId().longValue(), 
            //                                               Long.valueOf(condicionesBean.getIdRol()), 
            //                                               condicionesBean.getIdAgrupador(), userId, true);
            
            //Se deja la condicion como current para mantenerla seleccionada
            //obtenerFilaSeleccionada(condicionesBean.getIdCondicionSeleccionada());
            
            Row row = null;
            if (condicionesBean.getIdCondicionSeleccionada()!=null){
                logger.warning( "refresh detalle by idRow seleccionado: " + idCondicion );
                row = getRowByIdRow(condicionesBean.getIdCondicionSeleccionada(), condicionesBean);
            }else{
                logger.warning( "refresh detalle by idCondicion: " + idCondicion );
                row = getRowByIdCondicion(idCondicion.intValue(), condicionesBean);
            }
            
            refreshRow(row, idCondicion, condicionesBean.getOperacionId().longValue(), 
                                                            condicionesBean.getIdAgrupador(),
                                                           Long.valueOf(condicionesBean.getIdRol()), 
                                                            userId);
            
        }
        
        // Limpiamos valor de observación
        condicionesBean.setObservacionValidarCondicion(null);
        consultarCondicionesEnModelo();     
        // Cerramos popup
        getPopupConfirmarValidar().hide();
    }
    
    public void cancelarValidarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarValidarActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup
        getPopupConfirmarValidar().hide();
    }
    
    public void confirmEliminarValidacionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside confirmEliminarValidacionActionListener: " + actionEvent.getComponent().getId());
        CondicionesBean condicionesBean = (CondicionesBean)JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        Long idCondicion = null;
        String userId = null;
        String nombreUsuario = null;
        Boolean esValidador = null;
        String observacion = null;
        Boolean estado = null;
        String idTarea = null;
        String idRol = null;
        Long idAgrupador = null;
        Boolean isValidador = Boolean.FALSE;
        // Asignación de variables
        idCondicion = (Long)JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}");
        //Se obtiene el codigo de usuario de la sesion actual 17sep2019
        userId = ADFContext.getCurrent().getSecurityContext().getUserName().toLowerCase();
        logger.log(ADFLogger.WARNING, "userLogin confirmEliminarValidacionActionListener: "+userId);
        nombreUsuario = ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName();
        esValidador = Boolean.TRUE;
        observacion = condicionesBean.getObservacionValidarCondicion();
        estado = Boolean.FALSE;
        idTarea = condicionesBean.getIdTarea();
        idRol = condicionesBean.getIdRol();
        idAgrupador = condicionesBean.getIdAgrupador();
        
        logger.warning("Iniciara metodo validarCondicion.");
        // Invocamos método que actualiza estado de la Condición
        if(validarCondicion(idCondicion, userId, nombreUsuario, esValidador, observacion, estado, idTarea, idRol, idAgrupador)) {
            logger.warning("Condicion validada por metodo validarCondicion.");
            JSFUtils.addFacesInformationMessage(getStringFromBundle("MSG_ELIMINAR_VALIDACION_DE_ESTADO"));
            
            //Se realiza la consulta a la tabla de VALIDACION_CONDICION para habilitar boton de Validar.
            isValidador = existeValidador(idCondicion, idRol, userId);
                        if(!isValidador){
                            logger.warning("isValidador: " + isValidador);
                            condicionesBean.setCondicionValidada(Boolean.FALSE);
                        }else{
                            condicionesBean.setCondicionValidada(Boolean.TRUE);
                        }
            // Debido a que se actualizó el estado de la Condición, regeneramos tabla con la listas de las mismas
            // El parametro condicionesBean.getIdAgrupador()sustituye el valor del idEvento
            //consultarCondicionesByIdOperacionIdRolIdEvento(idCondicion, condicionesBean.getOperacionId().longValue(), 
            //                                               Long.valueOf(condicionesBean.getIdRol()), 
            //                                               condicionesBean.getIdAgrupador(), userId, true);
            
            //Se deja la condicion como current para mantenerla seleccionada
            //obtenerFilaSeleccionada(condicionesBean.getIdCondicionSeleccionada());
            //logger.warning("Termina condicion al validarCondicion ser true.");
            Row row = null;
            if (condicionesBean.getIdCondicionSeleccionada()!=null){
                logger.warning( "refresh detalle by idRow seleccionado: " + idCondicion );
                row = getRowByIdRow(condicionesBean.getIdCondicionSeleccionada(), condicionesBean);
            }else{
                logger.warning( "refresh detalle by idCondicion: " + idCondicion );
                row = getRowByIdCondicion(idCondicion.intValue(), condicionesBean);
            }
            
            refreshRow(row, idCondicion, condicionesBean.getOperacionId().longValue(), 
                                                            condicionesBean.getIdAgrupador(),
                                                           Long.valueOf(condicionesBean.getIdRol()), 
                                                            userId);
                        
        }
        consultarCondicionesEnModelo();
        logger.warning("Termina metodo confirmEliminarValidacionActionListener.");
        // Cerramos popup
        getPopupEliminarValidacion().hide();
    }
    
    public void cancelarEliminarValidacionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarEliminarValidacionActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup
        getPopupEliminarValidacion().hide();
    }
    
    public String aceptarFinalizarTareaValidarCondicionesAction() {
        logger.warning("Inside aceptarFinalizarTareaValidarCondicionesAction.");
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        Boolean finalizarTarea = Boolean.FALSE;
        
        String idRol = null;
        Long idAgrupador = null;
        idRol = condicionesBean.getIdRol();
        idAgrupador = condicionesBean.getIdAgrupador();
        Boolean resultado = Boolean.FALSE;
        // RN: 1) Para finalizar la tarea se debe verificar que todas las condiciones del Validador de condición se validen
        // (es decir, que el valor de ESTADO en la tabla VALIDACION_CONDICION sea 1; este dato lo retorna el servicio)
        // 2) y el estado de todas las condiciones sea "Valida parcialmente" o "Validada". (este punto se cubre con el punto 1)
        // Nota: al validar las se cambia el estado de la condición a "Valida parcialmente"
        if (validarCondicionesEstadoValidacion()) { // Valida 1)
            
            //Se actualiza la columna FINALIZO_TAREA en todos los registros de las condiciones validadas.
            resultado = actualizarFinalizaTareaCondicion(null, idRol, idAgrupador);
            if(resultado){
                logger.log(ADFLogger.WARNING, "Se actualizan todos los registros obtenidos.");
            }else{
                logger.log(ADFLogger.WARNING, "No se realizo alguna actualización");
            }
            // Invocamos servicio actualizarEstadoTCC con Estado (26) y Sub estado (28), estos ids los asignó Eduardo Rodríguez
            if (actualizaEstadoCondicion(Integer.valueOf("26"), Integer.valueOf("28"), Boolean.FALSE, idAgrupador)) {
            
                try {
                    // Regresamos los flags de roles como llegaron
                    actualizarFlagPayload("requiereASJUR", condicionesBean.getEsRequiereAsjur());
                    actualizarFlagPayload("requiereSEPRI", condicionesBean.getEsRequiereSepri());
                    actualizarFlagPayload("requiereAED", condicionesBean.getEsRequiereAed());
                    actualizarFlagPayload("requierePCT", condicionesBean.getEsRequierePct());
                    actualizarFlagPayload("requiereSupervision", condicionesBean.getEsRequiereSupervision());
                    actualizarFlagPayload("requiereCOFI", condicionesBean.getEsRequiereCofi());
                    
                    //Se agregan nuevas asignaciones de bandera por nuevas catagorias de condicion y validadores de Fase 3.
                    actualizarFlagPayload("requiereDAECI", condicionesBean.getEsRequiereAnalistaDaeci());
                    actualizarFlagPayload("requiereOFIC", condicionesBean.getEsRequiereAnalistaOfic());
                    actualizarFlagPayload("requiereAnalistaFINAM", condicionesBean.getEsRequiereAnalistaFinam());
                    actualizarFlagPayload("requiereAmbiental", condicionesBean.getEsRequiereEspAmbiental());
                    actualizarFlagPayload("requiereEjecutivoFINAM", condicionesBean.getEsRequiereEjecutivoFinam());
                    actualizarFlagPayload("requiereSegCred", condicionesBean.getEsRequiereSegCred());

                    finalizarTarea = Boolean.TRUE;
                    // Invocar método para cargar documentos en onBase
                    cargarDocumentosOnBase();
                } catch (Exception e) {
                    finalizarTarea = Boolean.FALSE;
                    logger.log(ADFLogger.WARNING, "Error al setear valores en el payload." + e);
                    //throw e;
                }

                if (!finalizarTarea) {
                    returnAction = "";
                    logger.log(ADFLogger.WARNING, "Error al finalizar la tarea");
                } else {
                    invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                    returnAction = invokeActionBean.invokeOperation();
                }
            }
        } else {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_03_VALIDAR_CONDICIONES"));
        }

        // Cerramos popup de confirmar finalizar tarea
        getPopupConfirmarFinalizarTarea().hide();

        return returnAction;
    }
    
    public String aceptarRetornarValidarCondicionesAction() {
        logger.log(ADFLogger.TRACE, "Inside aceptarRetornarValidarCondicionesAction.");
        logger.log(ADFLogger.WARNING, "Inside aceptarRetornarValidarCondicionesAction.");
        CondicionesBean condicionesBean = (CondicionesBean)JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        List<String> budleKeyErroList = new ArrayList<String>();
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        Long idAgrupador = null;
        idAgrupador = condicionesBean.getIdAgrupador();
        
        // RN: El sistema solicita los comentarios obligatoriamente. 
        if(validateComentario(Long.valueOf(condicionesBean.getIdOperacion()), 
                              condicionesBean.getIdTarea(), getInstanciaTarea())) {
            // RN: NO se puede Retornar si TODAS las condiciones están validadas
            // Se cambia metodo para validar que exista una condicion en estado por validar por cada validador
            // validarEstadoValidacionCondicion()
            if(validaEstadoPorValidar()) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_08_VALIDAR_CONDICIONES"));
            }
            else{
                // Invocamos servicio actualizarEstadoTCC con Estado (23=Por validar) y Sub estado (21=Por validar), estos ids los asignó Eduardo Rodríguez
                /*Se atiende incidencia FNXII-5138, Validar Condiciones, EA01 Retornar, RN_10, Se cambia el sub estado (21=Por Validar a 29=Por Cumplir) de acuerdo*/
                //if(actualizarEstadoCondiciones(Integer.valueOf("23"), Integer.valueOf("21"), Boolean.TRUE)) {
                if(actualizaEstadoCondicion(Integer.valueOf("23"), Integer.valueOf("29"), Boolean.TRUE, idAgrupador)) {
                    // Regresamos los flags de roles como llegaron
                    actualizarFlagPayload("requiereASJUR", condicionesBean.getEsRequiereAsjur());
                    actualizarFlagPayload("requiereSEPRI", condicionesBean.getEsRequiereSepri());
                    actualizarFlagPayload("requiereAED", condicionesBean.getEsRequiereAed());
                    actualizarFlagPayload("requierePCT", condicionesBean.getEsRequierePct());
                    actualizarFlagPayload("requiereSupervision", condicionesBean.getEsRequiereSupervision());
                    actualizarFlagPayload("requiereCOFI", condicionesBean.getEsRequiereCofi());
                    
                    //Se agregan nuevas asignaciones de bandera por nuevas catagorias de condicion y validadores de Fase 3.
                    actualizarFlagPayload("requiereDAECI", condicionesBean.getEsRequiereAnalistaDaeci());
                    actualizarFlagPayload("requiereOFIC", condicionesBean.getEsRequiereAnalistaOfic());
                    actualizarFlagPayload("requiereAnalistaFINAM", condicionesBean.getEsRequiereAnalistaFinam());
                    actualizarFlagPayload("requiereAmbiental", condicionesBean.getEsRequiereEspAmbiental());
                    actualizarFlagPayload("requiereEjecutivoFINAM", condicionesBean.getEsRequiereEjecutivoFinam());
                    actualizarFlagPayload("requiereSegCred", condicionesBean.getEsRequiereSegCred());
                    
                    // Invocar método para cargar documentos en onBase
                    cargarDocumentosOnBase();
                                
                    invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                    returnAction = invokeActionBean.invokeOperation();
                }
            }
        }
        else {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_07_VALIDAR_CONDICIONES"));
        }
        
        // Cerramos popup de Confirmar Finalizar Tarea
        getPopupConfirmarFinalizarTarea().hide();
        return returnAction;
    }
    /*** END Pantalla Validar condiciones ***/
    
    
    /*** START acciones genéricas ***/

    public void confirmarFinalizarTareaActionListener(ActionEvent actionEvent) {
        // Abre popup de confirmar finalizar tarea en todas las pantallas
        logger.log(ADFLogger.TRACE, "Inside confirmarFinalizarTareaActionListener: " + actionEvent.getComponent().getId());
        logger.log(ADFLogger.WARNING, "Inside confirmarFinalizarTareaActionListener: " + actionEvent.getComponent().getId());
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarFinalizarTarea().show(popupHints);
    }
    
    public void cancelarFinalizarTareaActionListener(ActionEvent actionEvent) {
        // Cerramos popup de confirmar finalizar tarea en todas las pantallas
        logger.log(ADFLogger.TRACE, "Inside cancelarFinalizarTareaRecExternosActionListener: " + actionEvent.getComponent().getId());
        getPopupConfirmarFinalizarTarea().hide();
    }

    public String invocarCancelarOutcome() {
        logger.warning("Inicia proceso de Invocar Cancelar Condiciones");

        List<String> budleKeyErroList = new ArrayList<String>();

        Boolean isValidCancelarOutcome = Boolean.FALSE;
        
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        int codigoTarea = Integer.parseInt(condicionesBean.getIdTarea());
        boolean esComentario = false;

        switch (codigoTarea) {
        case FenixConstants.PA03_CUMPLIR_CONDICIONES:
            logger.warning("Aplica validacion para tarea PC06_APROBAR_DESEMBOLSOS");
            esComentario =
                validateComentario(Long.valueOf(condicionesBean.getIdOperacion()), condicionesBean.getIdTarea(),
                                   getInstanciaTarea());

            if (esComentario) {
                isValidCancelarOutcome = Boolean.TRUE;
            }
            else
            {
                budleKeyErroList.add(getStringFromBundle("MSG_09_CANCELAR_CONDICIONES"));
            }
            break;
        default:
            logger.log(ADFLogger.ERROR, "invocarMasInformacion(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }

        if (!isValidCancelarOutcome) {
            if (budleKeyErroList.size() > 0) {
                for (String bundleMessage : budleKeyErroList)
                    JSFUtils.addFacesErrorMessage(bundleMessage);
            }
        } else {
            solicitarCancelarOutcomePopup();
        }

        return null;
    }

    public void solicitarCancelarOutcomePopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupDesestimarCondiciones.show(hints);
    }

    public String confirmarCancelarOutcome() {
        popupDesestimarCondiciones.hide();
        return null;
    }
    
    public String solicitarCancelarOutcome() {
        logger.warning("Entra a solicitarCancelarOutcome()");
        String retorno = null;

        popupDesestimarCondiciones.hide();
        //invocar metodo para cargar documentos onBase

        cargarDocumentosOnBase();

        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        retorno = invokeActionBean.invokeOperation();

        return retorno;
    }

    public void condicionesOperacionSelectionListener(SelectionEvent selectionEvent) {
        // Evento al que entra cuando hace click en el row de la tabla
        logger.warning("Inside condicionesOperacionSelectionListener");
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        Boolean estadoCondicion = Boolean.FALSE;
        Boolean isValidador = Boolean.FALSE;
        Long idCondicion = null;
        Integer id = null;
        String user = null;
        // 1) Evento por default (para actualizar filas)
        JSFUtils.resolveMethodExpression("#{bindings.CondicionesOperacionVO.collectionModel.makeCurrent}", Object.class, new Class[] {
                                         SelectionEvent.class }, new Object[] { selectionEvent });
        
        // 2) Actualizamos lista de evidencias en base al nuevo IdCondicion
        actualizarEvidenciasCondicion();

        if(null != ADFUtils.getBoundAttributeValue("IdCondicion")){
            idCondicion = (Long) ADFUtils.getBoundAttributeValue("IdCondicion");
            logger.warning("El id de la condicion es :" + idCondicion);
        }else{
            logger.warning("El id de la condicion es nula.");
        }
        if(null != ADFUtils.getBoundAttributeValue("Id")){
            id = (Integer) ADFUtils.getBoundAttributeValue("Id");
            logger.warning("El id es :" + id);
        }else{
            logger.warning("El id es nulo.");
        }
        
        user = ADFContext.getCurrent().getSecurityContext().getUserName();
        logger.log(ADFLogger.WARNING, "Valor de la condcicion :" + idCondicion);
        logger.log(ADFLogger.WARNING, "Valor del Rol :" + condicionesBean.getIdRol());
        logger.log(ADFLogger.WARNING, "Valor del Login Usuario :" + user);
        logger.warning("Valor del estado :" + (Boolean)ADFUtils.getBoundAttributeValue("EstadoValidacionCondicion"));
        isValidador = existeValidador(idCondicion, condicionesBean.getIdRol(), user);
        condicionesBean.setIdCondicionSeleccionada(id);
        if (!isValidador) {
            condicionesBean.setCondicionValidada(Boolean.FALSE);
        } else {
            condicionesBean.setCondicionValidada(Boolean.TRUE);
        }
        consultarCondicionesEnModelo();
    }

    private String refreshDetalle(CondicionesBean condicionesBean) {
        String detalle = "";
        logger.log(ADFLogger.WARNING, "Inside refresh detalle.");
        Long idCondicion = 0L;
        String userId = null;
        String nombreUsuario;
        Boolean esValidador;
        Boolean estado;
        String observacion;
        String idTarea;
        String idRol = null;
        Long idAgrupador = 0L;


        try {
           // CondicionesBean condicionesBean =
           //    (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");

            if (null != JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}")) {
                idCondicion = (Long) JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}");
                logger.log(ADFLogger.WARNING, "Valor idCondicion." + idCondicion);
            } else {
                logger.log(ADFLogger.WARNING, "El id de la condicion es nulo.");
            }
            if (null != ADFContext.getCurrent().getSecurityContext().getUserName()) { 
                //Se obtiene el codigo de usuario de la sesion actual 17sep2019
                userId = ADFContext.getCurrent().getSecurityContext().getUserName().toLowerCase();
                logger.log(ADFLogger.WARNING, "userLogin refreshDetalle: "+userId);
            } else {
                logger.log(ADFLogger.WARNING, "El id de usuario es nulo.");
            }
            if (null != ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName()) {
                nombreUsuario = ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName();
                logger.log(ADFLogger.WARNING, "Valor nombreUsuario." + nombreUsuario);
            } else {
                logger.log(ADFLogger.WARNING, "El nombre de usuario es nulo.");
            }
            esValidador = Boolean.FALSE;
            if (null != condicionesBean.getObservacionValidarCondicion()) {
                observacion = condicionesBean.getObservacionValidarCondicion();
                logger.log(ADFLogger.WARNING, "Valor observacion." + observacion);
            } else {
                logger.log(ADFLogger.WARNING, "La observacion es nula");
            }
            estado = Boolean.TRUE;
            if (null != condicionesBean.getIdTarea()) {
                idTarea = condicionesBean.getIdTarea();
                logger.log(ADFLogger.WARNING, "Valor idTarea." + idTarea);
            } else {
                logger.log(ADFLogger.WARNING, "El id de tarea es nulo");
            }
            if (null != condicionesBean.getIdRol()) {
                idRol = condicionesBean.getIdRol();
                logger.log(ADFLogger.WARNING, "Valor idRol." + idRol);
            } else {
                logger.log(ADFLogger.WARNING, "El id de Rol es nulo.");
            }
            if (null != condicionesBean.getIdAgrupador()) {
                idAgrupador = condicionesBean.getIdAgrupador();
                logger.log(ADFLogger.WARNING, "Valor agrupador." + idAgrupador);
            } else {
                logger.log(ADFLogger.WARNING, "El idAgrupador es nulo.");
            }
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding =
                bindings.getOperationBinding("obtenerDetalle");
            operationBinding.getParamsMap().put("idCondicion", idCondicion);
            operationBinding.getParamsMap().put("idOperacion", condicionesBean.getOperacionId().longValue());
            operationBinding.getParamsMap().put("idAgrupador", idAgrupador);
            
            operationBinding.execute();
            if (null != operationBinding.getResult()) {
                detalle = (String) operationBinding.getResult();
                logger.warning("Detalle TEXTO :" + detalle);
            }

        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al obtener el detalle: ", e);
        }

        return detalle;
    }

    public void condicionesOperacionSelectionListener2(SelectionEvent selectionEvent) {
        // Evento al que entra cuando hace click en el row de la tabla
        logger.warning("Inside condicionesOperacionSelectionListener2");
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        Boolean estadoCondicion = Boolean.FALSE;
        Boolean isValidador = Boolean.FALSE;
        Long idCondicion = null;
        Integer id = null;
        String user = null;
        // 1) Evento por default (para actualizar filas)
        JSFUtils.resolveMethodExpression("#{bindings.CondicionesOperacionVO.collectionModel.makeCurrent}", Object.class, new Class[] {
                                         SelectionEvent.class }, new Object[] { selectionEvent });
        
        // 2) Actualizamos lista de evidencias en base al nuevo IdCondicion
        actualizarEvidenciasCondicion();
        
        if(null != ADFUtils.getBoundAttributeValue("IdCondicion")){
            idCondicion = (Long) ADFUtils.getBoundAttributeValue("IdCondicion");
            logger.warning("El id de la condicion es :" + idCondicion);
        }else{
            logger.warning("El id de la condicion es nula.");
        }
        if(null != ADFUtils.getBoundAttributeValue("Id")){
            id = (Integer) ADFUtils.getBoundAttributeValue("Id");
            logger.warning("El id es :" + id);
            refreshDetalleFilaSeleccionada(id, condicionesBean);
        }else{
            logger.warning("El id es nulo.");
        }
        
        user = ADFContext.getCurrent().getSecurityContext().getUserName();
        logger.log(ADFLogger.WARNING, "Valor de la condcicion :" + idCondicion);
        logger.log(ADFLogger.WARNING, "Valor del Rol :" + condicionesBean.getIdRol());
        logger.log(ADFLogger.WARNING, "Valor del Login Usuario :" + user);
        logger.warning("condicionesOperacionSelectionListener2 - Valor del estado :" 
                       + (Boolean)ADFUtils.getBoundAttributeValue("EstadoValidacionCondicion"));
        isValidador = existeValidador(idCondicion, condicionesBean.getIdRol(), user);
        condicionesBean.setIdCondicionSeleccionada(id);
        if (!isValidador) {
            condicionesBean.setCondicionValidada(Boolean.FALSE);
        } else {
            condicionesBean.setCondicionValidada(Boolean.TRUE);
        }
        consultarCondicionesEnModelo();
    }
    /*
    private void refreshDetalleByIdCondicion(Integer idCondicion, CondicionesBean condicionesBean){
        logger.warning("Entra en obtenerFilaSeleccionada. Id: " + idCondicion);
        if (condicionesBean == null){
             condicionesBean =
                (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        }
        
        DCIteratorBinding condicionesOperacion = null;
        ViewObject condicionesVO = null;
        Row[] obtieneRows = null;
        try{
            condicionesOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CondicionesOperacionVOIterator");
            condicionesVO = condicionesOperacion.getViewObject(); 
            
            logger.warning("Valor de idCondicion seleccionada :" + idCondicion);
            if(null == condicionesVO){
               logger.warning("No se obtuvo el objeto VO");
               return;
            }
            
            obtieneRows = condicionesVO.getAllRowsInRange();
            logger.warning("Valor los rows :" + obtieneRows);
            for(Row row : obtieneRows){
                if(null != row.getAttribute("Id") && null != idCondicion){
                    logger.warning("Comparando fila :" + row.getAttribute("Id"));
                    if(idCondicion.compareTo(((Long)row.getAttribute("IdCondicion")).intValue()) == 0){
                        logger.warning("Fila obtenida Id :" + row.getAttribute("Id"));
                        //Se pone fila selccionada como current.
                        row.setAttribute("Detalle", refreshDetalle(condicionesBean));
                        condicionesVO.setCurrentRow(row);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.warning("Error al ejecutar obtenerFilaSeleccionada", e);
        }
    }
*/
    
    private Row getRowByIdCondicion(Integer idCondicion, CondicionesBean condicionesBean){
        Row returnRow = null;
        logger.warning("Entra en obtenerFilaSeleccionada. Id: " + idCondicion);
        if (condicionesBean == null){
             condicionesBean =
                (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        }
        
        DCIteratorBinding condicionesOperacion = null;
        ViewObject condicionesVO = null;
        Row[] obtieneRows = null;
        try{
            condicionesOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CondicionesOperacionVOIterator");
            condicionesVO = condicionesOperacion.getViewObject(); 
            
            logger.warning("Valor de idCondicion seleccionada :" + idCondicion);
            if(null == condicionesVO){
               logger.warning("No se obtuvo el objeto VO");
               return null;
            }
            
            obtieneRows = condicionesVO.getAllRowsInRange();
            logger.warning("Valor los rows :" + obtieneRows);
            for(Row row : obtieneRows){
                if(null != row.getAttribute("Id") && null != idCondicion){
                    logger.warning("Comparando fila :" + row.getAttribute("Id"));
                    if(idCondicion.compareTo(((Long)row.getAttribute("IdCondicion")).intValue()) == 0){
                        logger.warning("Fila obtenida Id :" + row.getAttribute("Id"));
                        //Se pone fila selccionada como current.
                        //row.setAttribute("Detalle", refreshDetalle(condicionesBean));
                        returnRow = row;
                        condicionesVO.setCurrentRow(row);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.warning("Error al ejecutar obtenerFilaSeleccionada", e);
        }
        return returnRow;
    }
    
    private Row getRowByIdRow(Integer id, CondicionesBean condicionesBean){
        Row returnRow = null;
        logger.warning("Entra en obtenerFilaSeleccionada. Id: " + id);
        if (condicionesBean == null){
             condicionesBean =
                (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        }
        
        
        DCIteratorBinding condicionesOperacion = null;
                ViewObject condicionesVO = null;
                Row[] obtieneRows = null;
                try{
                    condicionesOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CondicionesOperacionVOIterator");
                    condicionesVO = condicionesOperacion.getViewObject(); 
                    
                    logger.warning("Valor de la condicion seleccionada :" + id);
                    if(null != condicionesVO){
                        obtieneRows = condicionesVO.getFilteredRows("Id", id);
                        if(obtieneRows.length == 0){
                            logger.warning("No se recuperaron condiciones.");
                        }else{
                            logger.warning("Numero de rows obtenidos : " + obtieneRows.length);
                        }
                        for(Row row : obtieneRows){
                            if(null != row.getAttribute("Id") && null != id){
                                if(id.compareTo((Integer)row.getAttribute("Id")) == 0){
                                    logger.warning("Fila obtenida :" + row.getAttribute("IdCondicion"));
                                    //Se pone fila selccionada como current.
                                    //row.setAttribute("Detalle", refreshDetalle(condicionesBean));
                                    returnRow =  row;
                                    condicionesVO.setCurrentRow(row);
                                    break;
                                }
                            }
                        }
                    }else{
                        logger.warning("No se obtuvo el objeto VO");
                    }
        } catch (Exception e) {
            logger.warning("Error al ejecutar obtenerFilaSeleccionada", e);
        }
        return returnRow;
    }

    private void refreshDetalleFilaSeleccionada(Integer id, CondicionesBean condicionesBean){
        logger.warning("Entra en obtenerFilaSeleccionada. Id: " + id);
        if (condicionesBean == null){
             condicionesBean =
                (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        }
        
        
        DCIteratorBinding condicionesOperacion = null;
                ViewObject condicionesVO = null;
                Row[] obtieneRows = null;
                try{
                    condicionesOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CondicionesOperacionVOIterator");
                    condicionesVO = condicionesOperacion.getViewObject(); 
                    
                    logger.warning("Valor de la condicion seleccionada :" + id);
                    if(null != condicionesVO){
                        obtieneRows = condicionesVO.getFilteredRows("Id", id);
                        if(obtieneRows.length == 0){
                            logger.warning("No se recuperaron condiciones.");
                        }else{
                            logger.warning("Numero de rows obtenidos : " + obtieneRows.length);
                        }
                        for(Row row : obtieneRows){
                            if(null != row.getAttribute("Id") && null != id){
                                if(id.compareTo((Integer)row.getAttribute("Id")) == 0){
                                    logger.warning("Fila obtenida :" + row.getAttribute("IdCondicion"));
                                    //Se pone fila selccionada como current.
                                    row.setAttribute("Detalle", refreshDetalle(condicionesBean));
                                    condicionesVO.setCurrentRow(row);
                                    break;
                                }
                            }
                        }
                    }else{
                        logger.warning("No se obtuvo el objeto VO");
                    }
        } catch (Exception e) {
            logger.warning("Error al ejecutar obtenerFilaSeleccionada", e);
        }
        
    }
    
    public void obtenerFilaSeleccionada(Integer id){
        logger.warning("Entra en obtenerFilaSeleccionada. Id: " + id);
        DCIteratorBinding condicionesOperacion = null;
                ViewObject condicionesVO = null;
                //Row row = null;
                Row[] obtieneRows = null;
                try{
                    condicionesOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CondicionesOperacionVOIterator");
                    condicionesVO = condicionesOperacion.getViewObject(); 
                    
                    logger.warning("Valor de la condicion seleccionada :" + id);
                    if(null != condicionesVO){
                        obtieneRows = condicionesVO.getFilteredRows("Id", id);
                        if(obtieneRows.length == 0){
                            logger.warning("No se recuperaron condiciones.");
                        }else{
                            logger.warning("Numero de rows obtenidos : " + obtieneRows.length);
                        }
                        for(Row row : obtieneRows){
                            if(null != row.getAttribute("Id") && null != id){
                                if(id.compareTo((Integer)row.getAttribute("Id")) == 0){
                                    logger.warning("Fila obtenida :" + row.getAttribute("IdCondicion"));
                                    //Se pone fila selccionada como current.
                                    condicionesVO.setCurrentRow(row);
                                    break;
                                }
                            }
                        }
                    }else{
                        logger.warning("No se obtuvo el objeto VO");
                    }
        } catch (Exception e) {
            logger.warning("Error al ejecutar obtenerFilaSeleccionada", e);
        }
        
    }
    
    public RichOutputText getOtInitForm() {
        // Inicializamos componentes visuales. 
        // Usado cuando queremos acceder a un componente que existe hasta que se desplegó en pantalla 
 
        // Outputtext insertado en pantallas Cumplir condiciones y Validar condiciones
        actualizarEvidenciasCondicion(); // Actualizamos lista de evidencias en base al nuevo IdCondicion
        
        refreshCondiciones();
        
        return otInitForm;
    }
    
    private void actualizarEvidenciasCondicion(){
        // Actualizamos lista de evidencias en base al nuevo IdCondicion
        logger.log(ADFLogger.WARNING, "Inside actualizarEvidenciasCondicion");
        
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding voEvidenciasCondiciones = null;
        OperationBinding operationBinding = null;
        
        Long numeroEvidencias = null;
        Long idAgrupador = null;
        idAgrupador = condicionesBean.getIdAgrupador();
        
        // Asignación de variables
        //voEvidenciasCondiciones = ADFUtils.getDCBindingContainer().findIteratorBinding("EvidenciasCondicionesVOIterator");
        
        // Invocamos metodo para obtener las evidencias por id condicion y id agrupador en EvidenciasCondicionesVO
        operationBinding = bindings.getOperationBinding("obtenerEvidenciasCondicionPorIdAgrupador");
        operationBinding.getParamsMap().put("idCondicion", (Long)JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}"));
        operationBinding.getParamsMap().put("idAgrupador", idAgrupador);
        try{
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                numeroEvidencias = (Long)operationBinding.getResult();
                logger.warning("Numero de evidencias :" + numeroEvidencias);
            }
        }catch(Exception e){
            logger.warning("Error al buscar las evidencias", e);
        }
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        
        // Actualizamos lista de evidencias, se comenta metodo para atender FNXII-6342
        //voEvidenciasCondiciones.executeQuery();
        
    }
    
    private Boolean validarCondicion(Long idCondicion, String userId, String nombreUsuario, Boolean esValidador,
                                     String observacion, Boolean estado, String idTarea, String idRol, Long idAgrupador) {
        logger.log(ADFLogger.WARNING, "Inside validarCondicion2");
        Boolean esEjecucionExitosa = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        // Invocamos método que valida la condición desde el servicio
        operationBinding = bindings.getOperationBinding("validarCondicion");
        operationBinding.getParamsMap().put("plIdCondicion", idCondicion);
        operationBinding.getParamsMap().put("psUserId", userId);
        operationBinding.getParamsMap().put("nombreUsuario", nombreUsuario);
        operationBinding.getParamsMap().put("pbEsValidador", esValidador);
        operationBinding.getParamsMap().put("psObservacion", observacion);
        operationBinding.getParamsMap().put("pbEstado", estado);
        operationBinding.getParamsMap().put("idTarea", idTarea);
        operationBinding.getParamsMap().put("idRol", idRol);
        operationBinding.getParamsMap().put("idAgrupador", idAgrupador);

        try {
            operationBinding.execute();
            logger.warning("Ejecuta validarCondicion.");
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error :" + e.getClass() + "." + e.getMessage());
        }
        if (!operationBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            logger.warning(operationBinding.getErrors().toString());
        } else {
            esEjecucionExitosa = (Boolean) operationBinding.getResult();
        }
        logger.log(ADFLogger.WARNING, "Valor de retorno :" + esEjecucionExitosa);
        logger.warning("Termina metodo validarCondicion.");
        return esEjecucionExitosa;
    }
    
    // Se cambia el parametro condicionesBean.getIdAgrupador()sustituye el valor del idEvento
    private void consultarCondicionesByIdOperacionIdRolIdEvento(Long idCondicion, Long idOperacion, Long idRol, Long idAgrupador, 
                                                                String loginUsuario, Boolean esIdRolNecesario) {
        logger.log(ADFLogger.TRACE, "Inside consultarCondicionesByIdOperacionIdRolIdEvento");
        logger.log(ADFLogger.WARNING, "Inside consultarCondicionesByIdOperacionIdRolIdEvento");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Invocamos método que consulta las Condiciones con el servicio ConsultarValidacionCondicionByRol
        // El atributo idRol se usa para usar el nodo ValidacionCondicion correspondiente al loginUsuario e idRol en 
        // la respuesta. Sin embargo, el idRol NO se asigna en request de servicio ConsultarValidacionCondicionByRol 
        // cuando se trata de la tarea Cumplir condiciones.
        if(esIdRolNecesario) {
            operationBinding = bindings.getOperationBinding("consultarCondicionesByIdOperacionIdRolIdEvento");
            logger.log(ADFLogger.WARNING, "consultarCondicionesByIdOperacionIdRolIdEvento bingding: " + operationBinding);
        } else {
            operationBinding = bindings.getOperationBinding("consultarCondicionesByIdOperacionIdEvento2");
            logger.log(ADFLogger.WARNING, "consultarCondicionesByIdOperacionIdEvento2 binding: " + operationBinding);
        }
        logger.log(ADFLogger.WARNING, "Valor idRol." + idRol);
        logger.log(ADFLogger.WARNING, "Valor idAgrupador." + idAgrupador);
        logger.log(ADFLogger.WARNING, "Valor userId." + loginUsuario);
        operationBinding.getParamsMap().put("idCondicion", idCondicion);
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("idRol", idRol);
        operationBinding.getParamsMap().put("agrupador", idAgrupador);
        operationBinding.getParamsMap().put("loginUsuario", loginUsuario);
        operationBinding.getParamsMap().put("esRetornoCumplirCondiciones", false);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
    }
    
    // Se cambia el parametro condicionesBean.getIdAgrupador()sustituye el valor del idEvento
    private void consultarCondicionesByIdOperacionIdRolIdEvento(Long idOperacion, Long idRol, Long idAgrupador, 
                                                                String loginUsuario, Boolean esIdRolNecesario) {
        logger.log(ADFLogger.TRACE, "Inside consultarCondicionesByIdOperacionIdRolIdEvento");
        logger.log(ADFLogger.WARNING, "Inside consultarCondicionesByIdOperacionIdRolIdEvento");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Invocamos método que consulta las Condiciones con el servicio ConsultarValidacionCondicionByRol
        // El atributo idRol se usa para usar el nodo ValidacionCondicion correspondiente al loginUsuario e idRol en 
        // la respuesta. Sin embargo, el idRol NO se asigna en request de servicio ConsultarValidacionCondicionByRol 
        // cuando se trata de la tarea Cumplir condiciones.
        if(esIdRolNecesario)
            operationBinding = bindings.getOperationBinding("consultarCondicionesByIdOperacionIdRolIdEvento");
        else
            operationBinding = bindings.getOperationBinding("consultarCondicionesByIdOperacionIdEvento");
        logger.log(ADFLogger.WARNING, "Valor idRol." + idRol);
        logger.log(ADFLogger.WARNING, "Valor idAgrupador." + idAgrupador);
        logger.log(ADFLogger.WARNING, "Valor userId." + loginUsuario);
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("idRol", idRol);
        operationBinding.getParamsMap().put("agrupador", idAgrupador);
        operationBinding.getParamsMap().put("loginUsuario", loginUsuario);
        operationBinding.getParamsMap().put("esRetornoCumplirCondiciones", false);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
    }
    
    private Boolean validarCondicionesByEstado(String estado) {
        logger.log(ADFLogger.TRACE, "Inside validarCondicionesByEstado");
        Boolean esCondicionesValidas = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Invocamos método que valida que el estado de cada condición sea el indicado en el parámetro "estado"
        operationBinding = bindings.getOperationBinding("validarCondicionesByEstado");
        operationBinding.getParamsMap().put("estado", estado);

        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else{
            esCondicionesValidas = (Boolean)operationBinding.getResult();
        }
        
        return esCondicionesValidas;
    }
    
    private Boolean existeCondicionConEstado(String estado) {
        logger.log(ADFLogger.TRACE, "Inside existeCondicionConEstado");
        Boolean esExisteCondicionConEstado = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Invocamos método que valida si existe al menos una condición cuyo estado sea el indicado en el parámetro "estado"
        operationBinding = bindings.getOperationBinding("existeCondicionConEstado");
        operationBinding.getParamsMap().put("estado", estado);

        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else{
            esExisteCondicionConEstado = (Boolean)operationBinding.getResult();
        }
        
        return esExisteCondicionConEstado;
    }
    
    private Boolean validarCondicionesEstadoValidacion() {
        logger.log(ADFLogger.TRACE, "Inside validarCondicionesEstadoValidacion");
        logger.log(ADFLogger.WARNING, "Inside validarCondicionesEstadoValidacion");
        Boolean esDatosCorrectos = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Invocamos método que verifica que el Estado de Validación de cada condición sea true
        operationBinding = bindings.getOperationBinding("validarCondicionesEstadoValidacion");

        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else{
            esDatosCorrectos = (Boolean)operationBinding.getResult();
        }
        logger.log(ADFLogger.WARNING, "Valor del estado de la condicion :" + esDatosCorrectos);
        return esDatosCorrectos;
    }
    
    public Boolean validarEstadoValidacionCondicion() {
        logger.log(ADFLogger.TRACE, "Inside validarEstadoValidacionCondicion");
        logger.log(ADFLogger.WARNING, "Inside validarEstadoValidacionCondicion");
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        Boolean esDatosCorrectos = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String idRol = null;
        Long idAgrupador = null;
        idRol = condicionesBean.getIdRol();
        idAgrupador = condicionesBean.getIdAgrupador();
        
        // Invocamos método que verifica que el Estado de Validación de cada condición sea true
        //Se cambia metodo para verificar si el estado de cada condicion esta validada
        try{
            operationBinding = bindings.getOperationBinding("obtenerCondicionEstadoPorValidar");
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
            else{
                esDatosCorrectos = (Boolean)operationBinding.getResult();
            }
        }catch(Exception e){
            logger.warning("Error al obtener condiciones con estado por validar.", e);
        }
        logger.log(ADFLogger.WARNING, "Valor del estado de la condicion :" + esDatosCorrectos);
        return esDatosCorrectos;
    }
    
    public void consultarCondicionesEnModelo(){
        logger.warning("Entra en consultarCondicionesEnModelo.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Row[] condiciones = null;
        try{
            operationBinding = bindings.getOperationBinding("obtenerCondiciones");
            operationBinding.execute();
            logger.log(ADFLogger.WARNING, "El metodo obtenerCondiciones se ejecuto correctamente.");
            if(null != operationBinding.getResult()){
                condiciones = (Row[])operationBinding.getResult();
                logger.warning("Numero de rows encontrados en el modelo." + condiciones.length);
            }else{
                logger.warning("Arreglo de rows es nulo.");
            } 
        }catch(Exception e){
            logger.warning("Error en consultarCondicionesEnModelo.", e);
        }
    }
    
    public Boolean validaEstadoPorValidar(){
         logger.warning("Entra en validarEstadoPorValidar.");
         DCIteratorBinding voCondicionesOperacion = null;
         Boolean porValidar = Boolean.TRUE;
         ViewObject operacionResultadoVO = null;
         Row[] rowCondicion = null;

         CondicionesBean condicionesBean =
             (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");

         Long idCondicion = null;
         String loginUsuario = null;
         String RolBpm = null;
         
         //loginUsuario = ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName();
         //Se obtiene el codigo de usuario de la sesion actual 17sep2019
         loginUsuario = ADFContext.getCurrent().getSecurityContext().getUserName().toLowerCase();
         logger.log(ADFLogger.WARNING, "userLogin validaEstadoPorValidar: "+loginUsuario);

         RolBpm = condicionesBean.getIdRol();
         try{
             voCondicionesOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CondicionesOperacionVOIterator");
             operacionResultadoVO = voCondicionesOperacion.getViewObject();
             if (null != operacionResultadoVO.getAllRowsInRange()) {
                 rowCondicion = operacionResultadoVO.getAllRowsInRange();
             } else {
                 logger.log(ADFLogger.WARNING, "No hay rows en el objeto.");
             }
             if(rowCondicion.length > 0){
             for (Row row : rowCondicion) {
                 logger.log(ADFLogger.WARNING, "id de los rows :" + row.getAttribute("IdCondicion"));
                 if (null != row.getAttribute("IdCondicion")) {
                     idCondicion = (Long) row.getAttribute("IdCondicion");
                 } else {
                     logger.log(ADFLogger.WARNING, "Valor de condicion es nulo");
                 }
                 if (existeValidador(idCondicion, RolBpm, loginUsuario)) {
                     logger.log(ADFLogger.WARNING, "La condicion ha sido validada por el validador");
                 }else{
                     logger.warning("No se ha validado la condicion por el validador :" + idCondicion);
                     porValidar = Boolean.FALSE;
                 }

             }
             }else{
                 logger.log(ADFLogger.WARNING, "No hay condiciones para validar.");
             }
         }catch(Exception e){
             logger.warning("Error en validarEstadoPorValidar.", e);
         }
         logger.log(ADFLogger.WARNING, "Existen condiciones por validar :" + porValidar);
         return porValidar;
     }
    
    private Boolean actualizarEstadoTCC(Long idCondicion, Long idEstadoTcc, Long subEstado) {
        logger.log(ADFLogger.TRACE, "Inside actualizarEstadoTCC");
        Boolean esEjecucionExitosa = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Invocamos servicio actualizarEstadoTCC para una sola Condición
        operationBinding = bindings.getOperationBinding("actualizarTCCAdmon");
        operationBinding.getParamsMap().put("idTCC", idCondicion);
        operationBinding.getParamsMap().put("idEstadoTcc", idEstadoTcc);
        operationBinding.getParamsMap().put("subEstado", subEstado);
        operationBinding.getParamsMap().put("tipoTCC", FenixConstants.CONDICION);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else{
            esEjecucionExitosa = (Boolean)operationBinding.getResult();
        }
        
        return esEjecucionExitosa;
    }
    
    private Boolean actualizarEstadoCondiciones(Integer estado, Integer subEstado, Boolean esRetorno, Long idAgrupador) {
        logger.log(ADFLogger.TRACE, "Inside actualizarEstadoCondiciones");
        logger.log(ADFLogger.WARNING, "Inside actualizarEstadoCondiciones");
        Boolean esEjecucionExitosa = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Invocamos servicio actualizarEstadoTCC para todas las Condiciones de la tabla
        operationBinding = bindings.getOperationBinding("actualizarEstadoCondiciones");
        operationBinding.getParamsMap().put("estado", estado);
        operationBinding.getParamsMap().put("subEstado", subEstado);
        operationBinding.getParamsMap().put("esRetorno", esRetorno);
        operationBinding.getParamsMap().put("idAgrupador", idAgrupador);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else{
            esEjecucionExitosa = (Boolean)operationBinding.getResult();
        }
        logger.log(ADFLogger.TRACE, "Valor de retorno actualizar condiciones" + esEjecucionExitosa);
        return esEjecucionExitosa;
    }
    
    /**
     * Metodo para actualizar el estado de las condiciones, se utiliza a partir de la fecha 23/03/2017
     * @param estado
     * @param subEstado
     * @param esRetorno
     * @param idAgrupador
     * @return esEjecucionExitosa
     */
    private Boolean actualizaEstadoCondicion(Integer estado, Integer subEstado, Boolean esRetorno, Long idAgrupador) {
        logger.log(ADFLogger.TRACE, "Inside actualizarEstadoCondiciones");
        logger.log(ADFLogger.WARNING, "Inside actualizarEstadoCondiciones");
        Boolean esEjecucionExitosa = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        // Invocamos servicio actualizarEstadoTCC para todas las Condiciones de la tabla
        operationBinding = bindings.getOperationBinding("actualizaEstadoCondicion");
        operationBinding.getParamsMap().put("estado", estado);
        operationBinding.getParamsMap().put("subEstado", subEstado);
        operationBinding.getParamsMap().put("esRetorno", esRetorno);
        operationBinding.getParamsMap().put("idAgrupador", idAgrupador);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else{
            esEjecucionExitosa = (Boolean)operationBinding.getResult();
        }
        logger.log(ADFLogger.TRACE, "Valor de retorno actualizar condiciones" + esEjecucionExitosa);
        return esEjecucionExitosa;
    }
    
    private Boolean actualizaEstadoCondicionPorCumplir(Integer estado, Integer subEstado, Long idAgrupador, Long idCondicion) {
        logger.log(ADFLogger.TRACE, "Inside actualizaEstadoCondicionPorCumplir");
        logger.log(ADFLogger.WARNING, "Inside actualizaEstadoCondicionPorCumplir");
        Boolean esEjecucionExitosa = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        // Invocamos servicio actualizarEstadoTCC para todas las Condiciones de la tabla
        operationBinding = bindings.getOperationBinding("actualizaEstadoCondicionPorCumplir");
        operationBinding.getParamsMap().put("estado", estado);
        operationBinding.getParamsMap().put("subEstado", subEstado);
        operationBinding.getParamsMap().put("idCondicion", idCondicion);
        operationBinding.getParamsMap().put("idAgrupador", idAgrupador);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else{
            esEjecucionExitosa = (Boolean)operationBinding.getResult();
        }
        logger.log(ADFLogger.TRACE, "Valor de retorno actualizar condiciones" + esEjecucionExitosa);
        return esEjecucionExitosa;
    }
    
    private void actualizarFlagPayload(String variable, Object valor) {
        AttributeBinding attributeBinding = null;
        
        attributeBinding = ADFUtils.findControlBinding(variable);
        
        if(variable.equals("requiereCOFI")){
            if((Boolean)valor){
                attributeBinding.setInputValue(valor);
            }  
        }
        else{
            attributeBinding.setInputValue(valor);
        }
    }
    
    /*** END acciones genéricas ***/
    
    public String getDocpop() {
        DCIteratorBinding configuracionVOIterator = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
        ViewObject configuracionVoImpl = configuracionVOIterator.getViewObject();
        Row row = configuracionVoImpl.getRow(new Key(new Object[]{"URL_DOCPOP"}));
        String url = row == null ? null : (String) row.getAttribute("Valor");
        return url;
    }

    public String validarTodasCondicionesActionListener(ActionEvent actionEvent) {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarValidarTodasCondiciones().show(popupHints);
        return null;
    }

    public void setPopupConfirmarValidarTodasCondiciones(RichPopup popupConfirmarValidarTodasCondiciones) {
        this.popupConfirmarValidarTodasCondiciones = popupConfirmarValidarTodasCondiciones;
    }

    public RichPopup getPopupConfirmarValidarTodasCondiciones() {
        return popupConfirmarValidarTodasCondiciones;
    }

    public void aceptarValidarTodasCondicionesActionListener(ActionEvent actionEvent) {
        DCIteratorBinding voCondicionesOperacion = null;
        ViewObject operacionResultadoVO = null;
        Row[] rowCondicion = null;

        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        List<String> budleKeyErroList = new ArrayList<String>();

        Long idCondicion = null;
        String userId = null;
        String nombreUsuario = null;
        Boolean esValidador = null;
        String observacion = null;
        Boolean estado = null;
        String idTarea = null;
        String idRol = null;
        Long idAgrupador = null;
        
        //userId = ADFContext.getCurrent().getSecurityContext().getUserName();
        //Se obtiene el codigo de usuario de la sesion actual 17sep2019
        userId = ADFContext.getCurrent().getSecurityContext().getUserName().toLowerCase();
        logger.log(ADFLogger.WARNING, "userLogin aceptarValidarTodasCondicionesActionListener: "+userId);
        nombreUsuario = ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName();
        esValidador = Boolean.TRUE;
        observacion = condicionesBean.getObservacionValidarCondicion();
        estado = Boolean.TRUE;
        idTarea = condicionesBean.getIdTarea();
        idRol = condicionesBean.getIdRol();
        idAgrupador = condicionesBean.getIdAgrupador();

        voCondicionesOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CondicionesOperacionVOIterator");
        operacionResultadoVO = voCondicionesOperacion.getViewObject();
        if (null != operacionResultadoVO.getAllRowsInRange()) {
            rowCondicion = operacionResultadoVO.getAllRowsInRange();
        } else {
            logger.log(ADFLogger.WARNING, "No hay rows en el objeto.");
        }
        if(rowCondicion.length > 0){
        for (Row row : rowCondicion) {
            logger.log(ADFLogger.WARNING, "id de los rows :" + row.getAttribute("IdCondicion"));
            if (null != row.getAttribute("IdCondicion")) {
                idCondicion = (Long) row.getAttribute("IdCondicion");
            } else {
                logger.log(ADFLogger.WARNING, "Valor de condicion es nulo");
            }
            if (validarCondicion(idCondicion, userId, nombreUsuario, esValidador, observacion, estado, idTarea,
                                 idRol, idAgrupador)) {
                logger.log(ADFLogger.WARNING, "Se realiza la validacion correctamente");

            }

        }
        JSFUtils.addFacesInformationMessage(getStringFromBundle("MSG_VALIDACION_DE_ESTADO"));
        }else{
            logger.log(ADFLogger.WARNING, "No hay condiciones para validar.");
        }
        // Debido a que se actualizó el estado de la Condición, regeneramos tabla con la listas de las mismas
        // El parametro condicionesBean.getIdAgrupador()sustituye el valor del idEvento
        consultarCondicionesByIdOperacionIdRolIdEvento(idCondicion, condicionesBean.getOperacionId().longValue(),
                                                       Long.valueOf(condicionesBean.getIdRol()),
                                                       condicionesBean.getIdAgrupador(), userId, true);
        
        Row row = null;
        if (condicionesBean.getIdCondicionSeleccionada()!=null){
            //JURBINA@29062020  Se corrige el id de condicion, si hay una seleccionada debe mantenerse el id seleccionado y no el ultimo id del ciclo for lo que ocasionaba problemas en las validaciones cuando no eran iguales
            idCondicion = condicionesBean.getIdCondicionSeleccionada().longValue();
            logger.warning( "refresh detalle by idRow seleccionado: " + idCondicion );
            row = getRowByIdRow(condicionesBean.getIdCondicionSeleccionada(), condicionesBean);
        }else{
            logger.warning( "refresh detalle by idCondicion: " + idCondicion );
            row = getRowByIdCondicion(idCondicion.intValue(), condicionesBean);
        }
        
        refreshRow(row, idCondicion, condicionesBean.getOperacionId().longValue(), 
                                                        condicionesBean.getIdAgrupador(),
                                                       Long.valueOf(condicionesBean.getIdRol()), 
                                                        userId);
        
        // Limpiamos valor de observación
        condicionesBean.setObservacionValidarCondicion(null);
        // Deshabilitar el boton de validar
        condicionesBean.setCondicionValidada(Boolean.TRUE);
        consultarCondicionesEnModelo();
        getPopupConfirmarValidarTodasCondiciones().hide();
    }

    public String cancelarValidarTodasCondicionesActionListener(ActionEvent actionEvent) {
        getPopupConfirmarValidarTodasCondiciones().hide();
        return null;
    }
    
    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (null != resourceBundle) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }
    
    public void filtroFechaProccess(QueryEvent queryEvent) {
        logger.log(ADFLogger.WARNING, "Entra en filtroFechaProccess");
        
        Timestamp fechaInicio = null;
        Timestamp fechaFinal = null;
        
        QueryDescriptor qd = queryEvent.getDescriptor();
        ConjunctionCriterion conCrit = qd.getConjunctionCriterion();
        //acceso a la lista de filtros
        List<Criterion> criterionList = conCrit.getCriterionList();
            
        //itera filtros de la lista
        for (Criterion criterion : criterionList) {
            AttributeDescriptor attrDescriptor = ((AttributeCriterion) criterion).getAttribute();
            if (attrDescriptor.getName().equalsIgnoreCase("FechaInicio")) {
                fechaInicio = (Timestamp) ((AttributeCriterion) criterion).getValues().get(0);
                logger.log(ADFLogger.WARNING, "Valor de Fecha inicio." + fechaInicio);
            }
            if (attrDescriptor.getName().equalsIgnoreCase("FechaFinal")) {
                fechaFinal = (Timestamp) ((AttributeCriterion) criterion).getValues().get(0);
                if (null == fechaFinal) {
                    fechaFinal = new Timestamp(System.currentTimeMillis());
                    ((AttributeCriterion) criterion).setValue(fechaFinal);
                    logger.log(ADFLogger.WARNING,
                               "Valor seteado fecha Final :" + ((AttributeCriterion) criterion).getValues().get(0));
                }
                
                Timestamp fechaFinalEnviar = null;
                Calendar calendario = Calendar.getInstance();
                Calendar calendarioAux = Calendar.getInstance();
                calendarioAux.setTimeInMillis(fechaFinal.getTime());
                calendario.set(calendarioAux.get(Calendar.YEAR),calendarioAux.get(Calendar.MONTH), calendarioAux.get(Calendar.DATE),23,59,59);
                fechaFinalEnviar = new Timestamp(calendario.getTimeInMillis());
                ((AttributeCriterion) criterion).setValue(fechaFinalEnviar);
                logger.log(ADFLogger.WARNING, "Valor de Fecha final a enviar al filtro." + fechaFinalEnviar);
            }
        }
        
        //logger.log(ADFLogger.WARNING, "Valor de la fecha Inicio :" + fechaInicio);
        //logger.log(ADFLogger.WARNING, "Valor de la fecha Final :" + fechaFinal);
        
        if(null != fechaInicio){
        if (fechaFinal.compareTo(fechaInicio) < 0) {
            logger.log(ADFLogger.WARNING, "La fecha inicio no puede ser mayor a la fecha fin.");
            FacesMessage msg = new FacesMessage(getStringFromBundle("MSG_06_CUMPLIR_CONDICION"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            logger.log(ADFLogger.WARNING, "Se ejecutan criterio de busqueda con filtro de fechas.");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getActualizarComponenteQuery());
            invokeEL("#{bindings.DocumentosCondicionesVOCriteriaQuery.processQuery}", new Class[] { QueryEvent.class }, new Object[] {
                     queryEvent });
        }
        }else{
            logger.log(ADFLogger.WARNING, "Se ejecutan criterio de busqueda con filtro de fechas.");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getActualizarComponenteQuery());
            invokeEL("#{bindings.DocumentosCondicionesVOCriteriaQuery.processQuery}", new Class[] { QueryEvent.class }, new Object[] {
                     queryEvent });
        }
        setearValorOriginalFechaFinal(queryEvent, fechaFinal);
    }
    
    public void setearValorOriginalFechaFinal(QueryEvent queryEvent, Timestamp fechafinal) {
        logger.log(ADFLogger.WARNING, "Entra en setearValorOriginalFechaFinal");
        
        QueryDescriptor qd = queryEvent.getDescriptor();
        ConjunctionCriterion conCrit = qd.getConjunctionCriterion();
        //acceso a la lista de filtros
        List<Criterion> criterionList = conCrit.getCriterionList();
            
        //itera filtros de la lista
        for (Criterion criterion : criterionList) {
            AttributeDescriptor attrDescriptor = ((AttributeCriterion) criterion).getAttribute();
            if (attrDescriptor.getName().equalsIgnoreCase("FechaFinal")) {
                ((AttributeCriterion) criterion).setValue(fechafinal);
                logger.log(ADFLogger.WARNING,
                    "Valor original seteado fecha Final:" + ((AttributeCriterion) criterion).getValues().get(0));
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(getActualizarComponenteQuery());
    }
    
    public Boolean existeValidador(Long idCondicion, String RolBpm, String loginUsuario){
        logger.log(ADFLogger.WARNING, "Entra en existeValidador");
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Boolean esValidador = Boolean.FALSE;
        Integer idRolBpm = null; 
        Long idAgrupador = null;
        
        try{
            if(null != condicionesBean.getIdAgrupador()){
                idAgrupador = condicionesBean.getIdAgrupador();
            } else {
                logger.warning("No se obtuvo el agrupador.");
            }
            idRolBpm = Integer.parseInt(RolBpm);
            
            logger.log(ADFLogger.WARNING, "Valor de la condcicion :" + idCondicion);
            logger.log(ADFLogger.WARNING, "Valor del Rol :" + idRolBpm);
            logger.log(ADFLogger.WARNING, "Valor del Login Usuario :" + loginUsuario);
            logger.log(ADFLogger.WARNING, "Valor del Agrupador :" + idAgrupador);
            
            operationBinding = bindings.getOperationBinding("existeValidadorCondicion");
            operationBinding.getParamsMap().put("idCondicion", idCondicion);
            operationBinding.getParamsMap().put("idRolBpm", idRolBpm);
            operationBinding.getParamsMap().put("loginUsuario", loginUsuario);
            operationBinding.getParamsMap().put("idAgrupador", idAgrupador);
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                esValidador = (Boolean)operationBinding.getResult();
            }else{
                logger.log("Error al obtener el valor de retorno.");
            }
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error al ejecutar la busqueda del validador :" + e.getClass() + "." + e);
        }
        logger.log(ADFLogger.WARNING, "La busqueda del validador es :" + esValidador);
        return esValidador;
    }
    
    public Boolean actualizarFinalizaTareaCondicion(Long idCondicion, String idRol, Long idAgrupador){
        logger.log(ADFLogger.WARNING, "Entra en actualizarFinalizaTareaCondicion.");
        
        CondicionesBean condicionesBean =
            (CondicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionesManagedBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String loginUsuario = null;
        
        Integer idTarea = Integer.parseInt(condicionesBean.getIdTarea());
        logger.log(ADFLogger.WARNING, "id de la tarea." + idTarea);
        Boolean finalizoTarea = Boolean.FALSE;
        Integer idRolBpm = null;
        
        try{ 
            //Se obtiene el codigo de usuario de la sesion actual 17sep2019
            loginUsuario = ADFContext.getCurrent().getSecurityContext().getUserName().toLowerCase();
            logger.log(ADFLogger.WARNING, "userLogin actualizarFinalizaTareaCondicion: "+loginUsuario);
        }catch(Exception e){
            logger.severe("Error en actualizarFinalizaTareaCondicion al recuperar usuario en session",e);
        }
        
        try {
            if (null != idRol) {
                idRolBpm = Integer.parseInt(idRol);
            } else {
                logger.log(ADFLogger.WARNING,
                           "El rol es nulo, no se necesita para actualizar la columna FINALIZO_TAREA.");
            }
            
            operationBinding = bindings.getOperationBinding("actualizarFinalizoTarea");
            operationBinding.getParamsMap().put("idCondicion", idCondicion);
            operationBinding.getParamsMap().put("idAgrupador", idAgrupador);
            operationBinding.getParamsMap().put("idRol", idRolBpm);
            operationBinding.getParamsMap().put("idTarea", idTarea);
            operationBinding.getParamsMap().put("loginUsuario", loginUsuario);
            operationBinding.execute();
            if (null != operationBinding.getResult()) {
                finalizoTarea = (Boolean) operationBinding.getResult();
            } else {
                logger.log(ADFLogger.WARNING, "El valor de respuesta es nulo.");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en actualizarFinalizaTareaCondicion" + e);
        }
        return finalizoTarea;
    }

    public static Object invokeEL(String el, Class[] paramTypes, Object[] params) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        MethodExpression exp = expressionFactory.createMethodExpression(elContext, el, Object.class, paramTypes);

        return exp.invoke(elContext, params);
    }
    
    public void actualizarCampos() {
        logger.warning("Inside actualizarCampos.");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("actualizarFechaVigenciaRows");
        operationBinding.execute();            
    }

    @SuppressWarnings("unchecked")
    public void fechaVigenciaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.warning("Entrando en fechaVigenciaValueChangeListener.");
        
        Timestamp fechaVigencia = null;
        Long idCondicion = (Long)JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}");
        
        try {
            fechaVigencia = (Timestamp) valueChangeEvent.getNewValue();
        } catch(Exception e) {
            logger.warning("Error al castear la fecha de vigencia", e);
        }
        
        logger.warning("fechaVigencia: " + fechaVigencia);
        logger.warning("idCondicion: " + idCondicion);
        
        //if (null != idCondicion && null != fechaVigencia) {
        if (null != idCondicion){
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            operationBinding = bindings.getOperationBinding("actualizarFechaVigencia");
            operationBinding.getParamsMap().put("idCondicion", idCondicion);
            operationBinding.getParamsMap().put("fechaVigencia", fechaVigencia);
            operationBinding.execute(); 
        } else {
            logger.warning("La fechaVigencia o idCondicion es null.");
        }
        consultarCondicionesEnModelo();
         
    }
    
    public void refreshCondiciones() {
        logger.warning("Inside refreshCondiciones.");
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getCondicionesTableUIC());
        
        DCIteratorBinding voCondicionesOperacion = null;
        ViewObject condicionesVO = null;
        
        voCondicionesOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CondicionesOperacionVOIterator");
        
        if (voCondicionesOperacion != null) {
            logger.warning("Condiciones encontradas: " + voCondicionesOperacion.getEstimatedRowCount());
            
            if (voCondicionesOperacion.getEstimatedRowCount() > 0) {
                condicionesVO = voCondicionesOperacion.getViewObject();
                
                if (condicionesVO != null) {
                    condicionesVO.setCurrentRow(voCondicionesOperacion.getRowAtRangeIndex(0));
                    refreshDetalleFilaSeleccionada((Integer)voCondicionesOperacion.getRowAtRangeIndex(0).getAttribute("Id"), null);
                } else {
                    logger.warning("CondicionesVO es Null.");
                }   
            } else {
                logger.warning("No existen Condiciones.");
            }
        } else {
            logger.warning("CondicionesOperacionVOIterator es Null.");
        }
        consultarCondicionesEnModelo();
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setActualizarComponenteQuery(RichQuery actualizarComponenteQuery) {
        this.actualizarComponenteQuery = actualizarComponenteQuery;
    }

    public RichQuery getActualizarComponenteQuery() {
        return actualizarComponenteQuery;
    }

    public void setCondicionesTableUIC(RichTable condicionesTableUIC) {
        this.condicionesTableUIC = condicionesTableUIC;
    }

    public RichTable getCondicionesTableUIC() {
        return condicionesTableUIC;
    }

    public void setPopUpConfirmarCondicionesAValidar(RichPopup popUpConfirmarCondicionesAValidar) {
        this.popUpConfirmarCondicionesAValidar = popUpConfirmarCondicionesAValidar;
    }

    public RichPopup getPopUpConfirmarCondicionesAValidar() {
        return popUpConfirmarCondicionesAValidar;
    }

    public void confirmValidarTodas(ActionEvent actionEvent) {
        logger.warning("Inside confirmValidarTodas");
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpConfirmarCondicionesAValidar().show(popupHints);
    }

    public void cerrarPopUpConfirmarValidarTodo(ActionEvent actionEvent) {
        logger.warning("Inside cerrarPopUpConfirmarValidarTodo.");               
        getPopUpConfirmarCondicionesAValidar().hide();
    }
    
    public void setPopupDesestimarCondiciones(RichPopup popupDesestimarCondiciones) {
         this.popupDesestimarCondiciones = popupDesestimarCondiciones;
    }

    public RichPopup getPopupDesestimarCondiciones() {
        return popupDesestimarCondiciones;
    }
}
