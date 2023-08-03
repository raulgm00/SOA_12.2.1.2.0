package vercrearformularioimppct;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class VerCrearFormularioImpPctBean{
    
    private static ADFLogger logger = null;
    
    public VerCrearFormularioImpPctBean() {
        super();
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    private Long idTarea;
    private Long idImplementacion;
    private Long idOperacion;
    private Boolean muestraTipoProceso = Boolean.FALSE;
    private Boolean muestraNombreAdquisicion = Boolean.FALSE;
    private Boolean muestraMontoPresupuestado = Boolean.FALSE;
    private Boolean muestraObservaciones = Boolean.FALSE;
    private Boolean muestraPanelObservaciones = Boolean.FALSE;
    private Boolean muestraFechaPublicacion = Boolean.FALSE;
    private Boolean muestraLimiteRecepcion = Boolean.FALSE;
    private Boolean muestraMoneda = Boolean.FALSE;
    private Boolean muestraTramos = Boolean.FALSE;
    private Boolean muestraResultado = Boolean.FALSE;
    private Boolean muestraFechaOrdenIncio = Boolean.FALSE;
    private static Boolean validaIdImplementacion = Boolean.FALSE;
    
    private Boolean soloLectura = Boolean.FALSE;
    private Boolean soloLecturaObservacion = Boolean.FALSE;
    private Boolean soloLecturaNombreAdquisicion = Boolean.FALSE;
    private Boolean renderizarEspacio = Boolean.TRUE;
    private Boolean soloLecturaMoneda = Boolean.FALSE;
    
    public Boolean inicializarFlujo(){
        
        logger.log(ADFLogger.WARNING, "INTO inicializarFlujo.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        
        try {
            try{
                if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}") != null){
                idTarea = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}").toString());
                }else{
                    logger.log(ADFLogger.WARNING, "Objeto pageFlowScope.pIdTarea Nulo.");
                }
                if(JSFUtils.resolveExpression("#{pageFlowScope.pIdImplementacion}") != null){
                    idImplementacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdImplementacion}").toString());
                }else{
                    logger.log(ADFLogger.WARNING, "Objeto pageFlowScope.pIdImplementacion Nulo.");
                }
                if(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null){
                    idOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
                }else{
                    logger.log(ADFLogger.WARNING, "Objeto pageFlowScope.pIdOperacion Nulo.");
                }
                
                logger.log(ADFLogger.WARNING, "Valor id Tarea." + idTarea);
                logger.log(ADFLogger.WARNING, "Valor id Implementacion." + idImplementacion);
                logger.log(ADFLogger.WARNING, "Valor id Operacion." + idOperacion);
                
                OperationBinding operationBinding = bindings.getOperationBinding("obtenerImplementacionByIdImplementacion");
                operationBinding.getParamsMap().put("idImplementacion", idImplementacion);
                operationBinding.execute();
                validaIdImplementacion = (Boolean)operationBinding.getResult();
                
            }catch(Exception e){
                
                validaIdImplementacion = Boolean.FALSE;
                logger.log(ADFLogger.ERROR, "Error en obtenerImplementacionByIdImplementacion." + e.getClass() + "." + e.getMessage());
            
            }
            
            logger.log(ADFLogger.WARNING, "Valor de validaIdImplementacion...." + validaIdImplementacion);
            asignarAtributosPoridTarea();
        
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en inicializarFlujo." + e.getClass() + "." + e.getMessage());
        }
        
        return validaIdImplementacion;
    }
    
    public void asignarAtributosPoridTarea() {
        try {
              switch (Integer.parseInt(idTarea.toString())) {
              case FenixConstants.PA11_SOLICITAR_CONTRATACION_CONSULTORIA:
                  muestraTipoProceso = Boolean.TRUE;
                  muestraNombreAdquisicion = Boolean.TRUE;
                  muestraMontoPresupuestado = Boolean.TRUE;
                  renderizarEspacio = Boolean.FALSE;
                  break;
              case FenixConstants.PA11_GESTIONAR_CONTRATACION_CONSULTORIA:
                  muestraTipoProceso = Boolean.TRUE;
                  muestraNombreAdquisicion = Boolean.TRUE;
                  muestraMontoPresupuestado = Boolean.TRUE;
                  muestraPanelObservaciones = Boolean.TRUE;
                  muestraObservaciones = Boolean.TRUE;
                  soloLectura = Boolean.TRUE;
                  soloLecturaNombreAdquisicion = Boolean.TRUE;
                  renderizarEspacio = Boolean.FALSE;
                  break;
              case FenixConstants.PA11_ELABORAR_DOCUMENTO_BASE:
                  muestraTipoProceso = Boolean.TRUE;
                  muestraNombreAdquisicion = Boolean.TRUE;
                  muestraMontoPresupuestado = Boolean.TRUE;
                  muestraPanelObservaciones = Boolean.TRUE;
                  muestraObservaciones = Boolean.TRUE;
                  soloLectura = Boolean.TRUE;
                  soloLecturaObservacion = Boolean.TRUE;
                  soloLecturaNombreAdquisicion = Boolean.TRUE;
                  renderizarEspacio = Boolean.FALSE;
                  break;
              case FenixConstants.PA11_COMPLETAR_DOCUMENTO_BASE:
                  muestraTipoProceso = Boolean.TRUE;
                  muestraNombreAdquisicion = Boolean.TRUE;
                  muestraMontoPresupuestado = Boolean.TRUE;
                  muestraPanelObservaciones = Boolean.TRUE;
                  muestraObservaciones = Boolean.TRUE;
                  soloLectura = Boolean.TRUE;
                  soloLecturaObservacion = Boolean.TRUE;
                  soloLecturaNombreAdquisicion = Boolean.TRUE;
                  renderizarEspacio = Boolean.FALSE;
                  break;
              case FenixConstants.PA11_VALIDAR_DOCUMENTO_BASE:
                  muestraTipoProceso = Boolean.TRUE;
                  muestraNombreAdquisicion = Boolean.TRUE;
                  muestraMontoPresupuestado = Boolean.TRUE;
                  muestraPanelObservaciones = Boolean.TRUE;
                  muestraObservaciones = Boolean.TRUE;
                  soloLectura = Boolean.TRUE;
                  soloLecturaObservacion = Boolean.TRUE;
                  soloLecturaNombreAdquisicion = Boolean.TRUE;
                  renderizarEspacio = Boolean.FALSE;
                  break;
              case FenixConstants.PA11_INICIAR_ADQUISICION:
                  muestraNombreAdquisicion = Boolean.TRUE;
                  muestraFechaPublicacion = Boolean.TRUE;
                  muestraLimiteRecepcion = Boolean.TRUE;
                  muestraMontoPresupuestado = Boolean.TRUE;
                  muestraMoneda = Boolean.TRUE;
                  muestraTramos = Boolean.TRUE;
                  soloLecturaNombreAdquisicion = Boolean.TRUE;
                  soloLectura = Boolean.TRUE;
                  renderizarEspacio = Boolean.FALSE;
                  break;
              case FenixConstants.PA11_REGISTRAR_RESULTADO_ADQUISICION:
                  muestraLimiteRecepcion = Boolean.TRUE;
                  break;
              case FenixConstants.PA11_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO:
                  muestraFechaOrdenIncio = Boolean.TRUE;
                  muestraMontoPresupuestado = Boolean.TRUE;
                  muestraMoneda = Boolean.TRUE;
                  break;
              default:
                  break;
              }
          } catch (Exception e) {
              logger.log(ADFLogger.WARNING, "Error en asignarAtributosPoridTarea." + e.getClass() + "." + e.getMessage());
          }
    }

    public void setMuestraTipoProceso(Boolean muestraTipoProceso) {
        this.muestraTipoProceso = muestraTipoProceso;
    }

    public Boolean getMuestraTipoProceso() {
        return muestraTipoProceso;
    }

    public void setMuestraNombreAdquisicion(Boolean muestraNombreAdquisicion) {
        this.muestraNombreAdquisicion = muestraNombreAdquisicion;
    }

    public Boolean getMuestraNombreAdquisicion() {
        return muestraNombreAdquisicion;
    }

    public void setMuestraMontoPresupuestado(Boolean muestraMontoPresupuestado) {
        this.muestraMontoPresupuestado = muestraMontoPresupuestado;
    }

    public Boolean getMuestraMontoPresupuestado() {
        return muestraMontoPresupuestado;
    }

    public void setMuestraObservaciones(Boolean muestraObservaciones) {
        this.muestraObservaciones = muestraObservaciones;
    }

    public Boolean getMuestraObservaciones() {
        return muestraObservaciones;
    }

    public void setMuestraPanelObservaciones(Boolean muestraPanelObservaciones) {
        this.muestraPanelObservaciones = muestraPanelObservaciones;
    }

    public Boolean getMuestraPanelObservaciones() {
        return muestraPanelObservaciones;
    }

    public void setMuestraFechaPublicacion(Boolean muestraFechaPublicacion) {
        this.muestraFechaPublicacion = muestraFechaPublicacion;
    }

    public Boolean getMuestraFechaPublicacion() {
        return muestraFechaPublicacion;
    }

    public void setMuestraLimiteRecepcion(Boolean muestraLimiteRecepcion) {
        this.muestraLimiteRecepcion = muestraLimiteRecepcion;
    }

    public Boolean getMuestraLimiteRecepcion() {
        return muestraLimiteRecepcion;
    }

    public void setMuestraMoneda(Boolean muestraMoneda) {
        this.muestraMoneda = muestraMoneda;
    }

    public Boolean getMuestraMoneda() {
        return muestraMoneda;
    }

    public void setMuestraTramos(Boolean muestraTramos) {
        this.muestraTramos = muestraTramos;
    }

    public Boolean getMuestraTramos() {
        return muestraTramos;
    }

    public void setMuestraResultado(Boolean muestraResultado) {
        this.muestraResultado = muestraResultado;
    }

    public Boolean getMuestraResultado() {
        return muestraResultado;
    }

    public void setMuestraFechaOrdenIncio(Boolean muestraFechaOrdenIncio) {
        this.muestraFechaOrdenIncio = muestraFechaOrdenIncio;
    }

    public Boolean getMuestraFechaOrdenIncio() {
        return muestraFechaOrdenIncio;
    }

    public void setValidaIdImplementacion(Boolean validaIdImplementacion) {
        this.validaIdImplementacion = validaIdImplementacion;
    }

    public Boolean getValidaIdImplementacion() {
        return validaIdImplementacion;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdImplementacion(Long idImplementacion) {
        this.idImplementacion = idImplementacion;
    }

    public Long getIdImplementacion() {
        return idImplementacion;
    }

    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }

    public void setSoloLectura(Boolean soloLectura) {
        this.soloLectura = soloLectura;
    }

    public Boolean getSoloLectura() {
        return soloLectura;
    }

    public void setSoloLecturaObservacion(Boolean soloLecturaObservacion) {
        this.soloLecturaObservacion = soloLecturaObservacion;
    }

    public Boolean getSoloLecturaObservacion() {
        return soloLecturaObservacion;
    }

    public void setSoloLecturaNombreAdquisicion(Boolean soloLecturaNombreAdquisicion) {
        this.soloLecturaNombreAdquisicion = soloLecturaNombreAdquisicion;
    }

    public Boolean getSoloLecturaNombreAdquisicion() {
        return soloLecturaNombreAdquisicion;
    }

    public void setRenderizarEspacio(Boolean renderizarEspacio) {
        this.renderizarEspacio = renderizarEspacio;
    }

    public Boolean getRenderizarEspacio() {
        return renderizarEspacio;
    }

    public void setSoloLecturaMoneda(Boolean soloLecturaMoneda) {
        this.soloLecturaMoneda = soloLecturaMoneda;
    }

    public Boolean getSoloLecturaMoneda() {
        return soloLecturaMoneda;
    }

    public void agregarRegistroFormulario(){
        logger.log(ADFLogger.WARNING, "INTO agregarRegistroFormulario.");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        
        try{
            
            OperationBinding operationBinding = bindings.getOperationBinding("crearRowFormImpPctInsertarActualizar");
            operationBinding.execute();
            
        
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al agregar datos en agregarRegistroFormulario." + e.getClass() + e.getMessage());
        }
    }

    public void cargarDatosImplementacion() {
        logger.log(ADFLogger.WARNING, "INTO cargarDatosImplementacion.");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        
        try{
            
            OperationBinding operationBinding = bindings.getOperationBinding("llenarFormularioImplementacion");
            operationBinding.execute();
            
        
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al agregar datos en cargarDatosImplementacion." + e.getClass() + e.getMessage());
        }
    }
}
