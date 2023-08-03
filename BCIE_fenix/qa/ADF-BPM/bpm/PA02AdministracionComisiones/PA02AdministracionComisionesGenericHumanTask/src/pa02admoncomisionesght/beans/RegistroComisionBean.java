package pa02admoncomisionesght.beans;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.faces.model.SelectItem;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.model.RowKeySet;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class RegistroComisionBean implements Serializable{
    @SuppressWarnings("compatibility:-4573632282076705552")
    private static final long serialVersionUID = 1L;
    
    private static ADFLogger logger = null;
    private RowKeySet disclosedRowKeysTcc; // Guarda el elemento seleccionado en el tree
    private Boolean esSelectionEventQueue = Boolean.FALSE; // Evita que se llame repetidamente al Selectionlistener para el mismo evento
    private List<SelectItem> listaComsiones;
    private Long idComision;
    private Integer pIdTipoGestion;
    private oracle.jbo.domain.Number idTccTree;
    private Boolean esModoEscritura;
    private Integer idTareaBpm;
    private Long idOperacion;
    
    public RegistroComisionBean(){
        super();
        if(null == logger){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    /**
     * Metodo que se utiliza para obtener los parametros del pageFlowScope
     */
    public void obtenerParametrosEntrada() {
        logger.warning("Entra en obtenerParametrosEntrada.");
        
        Long idOperacion = null;
        Boolean esModoEscritura = null;
        Integer idTareaBpm = null;
        
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null){
                idOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString().toString());
                logger.warning("idOperacion : " + idOperacion);
            }else{
                logger.warning("Error el Id de Operacion es NULL"); 
            }
        }catch(Exception e){
            logger.warning("Error al obtener el Id de Operacion", e);
        }
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pEsModoEscritura}") != null){
                esModoEscritura = Boolean.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pEsModoEscritura}").toString().toString());
                logger.warning("esModoEscritura : " + esModoEscritura);
            }else{
                logger.warning("Error parametro Es Modo Escritura es NULL");
            }
        }catch(Exception e){
            logger.warning("Error al obtener el parametro de Es Modo Escritura", e);
        }        
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}") != null){
                idTareaBpm = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}").toString().toString());
                logger.warning("idTareaBpm : " + idTareaBpm);
            }else{
                logger.warning("Error el Id de Tarea BPM es NULL");
            }
        }catch(Exception e){
            logger.warning("Error al obtener el Id de Tarea BPM", e);
        }
        
        if(idOperacion != null &&
           esModoEscritura != null &&
           idTareaBpm != null){
            
                logger.warning("Entra a setear los valores del TaskFlow.");
                setIdOperacion(idOperacion);
                setEsModoEscritura(esModoEscritura);
                setIdTareaBpm(idTareaBpm);

            consultaProcesoBPMTaskFlow(idTareaBpm);
        }else{
            logger.warning("Alguno de los parametros es nulo.");
        }
    }
    
    public Integer consultaProcesoBPMTaskFlow(Integer idTareaBpm){
        
        logger.warning("Entra en consultaProcesoBPMTaskFlow");
        
        Integer idProcesoBpm = null;        
        idProcesoBpm = obtenerIdProcesoBPM(idTareaBpm);
        
        logger.warning("Valor del proceso encotrado" + idProcesoBpm);
        logger.warning("retorno de metodo consultaProcesoBPMTaskFlow");
        return idProcesoBpm;
    }
    
    public Integer obtenerIdProcesoBPM(Integer idTareaBpm){
        
        logger.warning("Entra en obtenerIdProcesoBPM. " + idTareaBpm);
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Integer idProcesoBpm = null;
        try{
            if(idTareaBpm != null){
                logger.warning("Invoca metodo executeOperBinding para proceso");
                operationBinding = bindings.getOperationBinding("buscarProcesoPorIdTareaBpm");
                operationBinding.getParamsMap().put("idTareaBpm", idTareaBpm);
                operationBinding.execute();
                if(operationBinding.getErrors().isEmpty()){
                    idProcesoBpm = (Integer) operationBinding.getResult();
                    if(idProcesoBpm != null){
                        logger.warning("Id de Proceso BPM retornado: " + idProcesoBpm);    
                    }else{
                        logger.warning("Id de Proceso BPM no retornado");
                    }
                }
            }else{
                logger.warning("El id de tarea bpm es NULL");
            }
        }catch(Exception e){
            logger.warning("Error en obtenerIdProcesoBPM.", e);
        }
        
        logger.warning("Id Proceso : " + idProcesoBpm);
        logger.warning("Retorna metodo obtenerIdProcesoBPM");
        return idProcesoBpm;
    }
    
    /**
     * Matodo para cargar la configuracion de atributos de la base de acuerdo al tipo de comision
     */
    public void cargarPantallaComision() {
        logger.warning("Entra en cargarPantallaComision.");  
        
        String strIdTcc = null;
        Boolean modoEscritura = Boolean.FALSE;
        Long idOperacion = null;
        Integer tipoGestion = null;
        
        if(JSFUtils.resolveExpression("#{pageFlowScope.pEsModoEscritura}") != null){
            modoEscritura = Boolean.parseBoolean(JSFUtils.resolveExpression("#{pageFlowScope.pEsModoEscritura}").toString());
            logger.warning("pEsModoEscritura : " + modoEscritura);
        }else{
            logger.warning("pEsModoEscritura es nula.");
        }
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null){
            idOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
            logger.warning("pIdOperacion : " + idOperacion);
        }else{
            logger.warning("pIdOperacion es nula.");
        }   
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTreeComision}") != null){
            strIdTcc = JSFUtils.resolveExpression("#{pageFlowScope.pIdTreeComision}").toString();
            logger.warning("Numero de la comision : " + strIdTcc);
        }else{
            logger.warning("El id de la comision es nula.");
        }        
        if(JSFUtils.resolveExpression("#{pageFlowScope.pTipoGestionComision}") != null){
            tipoGestion = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pTipoGestionComision}").toString());
            logger.warning("TipoComision : " + tipoGestion);
        }else{
            logger.warning("El tipo de gestion es nula.");
        }
        
        if(strIdTcc != null){
            logger.warning("Entra a ejecutar metodos");
            Map params = null;
            params = new HashMap();
            oracle.jbo.domain.Number id = null;
            try{
                id = new oracle.jbo.domain.Number(strIdTcc);
            }catch(Exception e){
                logger.warning("Error al convertir el Id de TCC en Number", e);
            }
            params.put("id", id);
            OperationBinding operationBinding = executeOperBinding(params, "buscarComisionPorId");
            
            if(operationBinding.getErrors().isEmpty()){  
              operationBinding = null;
              operationBinding = executeOperBinding(null, "asignarConfigTipoComision");
              if(operationBinding != null && !operationBinding.getErrors().isEmpty()){
                  logger.warning("Error al obtener y asignar configuracion del catalogo de tipo comision");
              }
              
              operationBinding = executeOperBinding(params, "obtenerConfigucacionAtributosTcc");
               
              if(operationBinding.getErrors().isEmpty()){
                  Map<String,Map> configuracionAtributosTCCMap = (Map<String,Map>) operationBinding.getResult();
                  
                  GestionComisionBean gestionComisionBean =
                        (GestionComisionBean) JSFUtils.resolveExpression("#{pageFlowScope.gestionComisionBean}");
                  
                  if(null!=gestionComisionBean){
                    gestionComisionBean.setAtributeValues(configuracionAtributosTCCMap);
                  }
              }else{
                String msg = "Error al obtener configuracion de atributos TCC ";
                logger.warning(msg);
                JSFUtils.addFacesErrorMessage(msg);
              }
               
            }else{
                String msg = "Error al bucar los datos ralacionados con la comision " + strIdTcc;
                logger.warning(msg);
                JSFUtils.addFacesErrorMessage(msg);
            }
        }else{
            logger.warning("Error comision nula.");
        }
    }
    
    private OperationBinding executeOperBinding(Map params,
                                                String operBindName){
        
        logger.warning("Entra en executeOperBinding");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding oper = null;
        oper = bindings.getOperationBinding(operBindName);
        if (null != oper) {
            
            if(params != null){
                oper.getParamsMap().putAll(params);    
            }
            try {
                logger.warning("Ejecuta metodo ".concat(operBindName));
                oper.execute();
            } catch (Exception e) {
                logger.warning("Error al ejecutar Operator Bindings: " + operBindName, e);                
            }

            if (!oper.getErrors().isEmpty()) {
                logger.warning("Operator Bindings devuelve errores: " + operBindName);
            }else{
                logger.warning("Operator Bindings se ejecuto correctamente");
            }
        }else{
            logger.warning("Error el operator Binding " + operBindName + 
                          " no fue encontrado");
        }
        
        logger.warning("Termina ejecucion del metodo : " + oper);
        return oper;
    }

    public void setEsSelectionEventQueue(Boolean esSelectionEventQueue) {
        this.esSelectionEventQueue = esSelectionEventQueue;
    }

    public Boolean getEsSelectionEventQueue() {
        return esSelectionEventQueue;
    }


    public void setDisclosedRowKeysTcc(RowKeySet disclosedRowKeysTcc) {
        this.disclosedRowKeysTcc = disclosedRowKeysTcc;
    }

    public RowKeySet getDisclosedRowKeysTcc() {
        return disclosedRowKeysTcc;
    }


    public void setListaComsiones(List<SelectItem> listaComsiones) {
        this.listaComsiones = listaComsiones;
    }

    public List<SelectItem> getListaComsiones() {
        return listaComsiones;
    }

    public void setIdComision(Long idComision) {
        this.idComision = idComision;
    }

    public Long getIdComision() {
        return idComision;
    }


    public void setPIdTipoGestion(Integer pIdTipoGestion) {
        this.pIdTipoGestion = pIdTipoGestion;
    }

    public Integer getPIdTipoGestion() {
        return pIdTipoGestion;
    }

    public void setIdTccTree(oracle.jbo.domain.Number idTccTree) {
        this.idTccTree = idTccTree;
    }

    public oracle.jbo.domain.Number getIdTccTree() {
        return idTccTree;
    }


    public void setEsModoEscritura(Boolean esModoEscritura) {
        this.esModoEscritura = esModoEscritura;
    }

    public Boolean getEsModoEscritura() {
        return esModoEscritura;
    }

    public void setIdTareaBpm(Integer idTareaBpm) {
        this.idTareaBpm = idTareaBpm;
    }

    public Integer getIdTareaBpm() {
        return idTareaBpm;
    }

    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }
}
