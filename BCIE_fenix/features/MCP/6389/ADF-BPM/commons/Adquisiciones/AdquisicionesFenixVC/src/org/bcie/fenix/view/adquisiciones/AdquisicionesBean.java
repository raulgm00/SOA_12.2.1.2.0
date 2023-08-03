package org.bcie.fenix.view.adquisiciones;

import java.io.Serializable;

import java.math.BigDecimal;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class AdquisicionesBean implements Serializable {
    @SuppressWarnings("compatibility:-8092058068489999102")
    private static final long serialVersionUID = 1L;

    private Long idAdquisiones;
    private Boolean lectura;
    private Long idOperacion;
    private Integer tarea;
    private Integer sector;
    private Boolean operacionAprobada = Boolean.FALSE;
    private Integer edita2agrega1;

    private static ADFLogger logger = null;
    private Boolean disableBotones;
    private Boolean disableBotonesAcciones;
    private Boolean disableFormulario;
    private Boolean agregarAdquisicion;
    private Boolean modificarAdquisicion;
    private Boolean agregarEvidencia;
    private Boolean listaHabilitada;
    private Long idAdquiscionPrincipal;
    private Long idObjecionUnica;
    private static Long idAdquisicionAux;
    
    private String descripcionOperacion;
    private String descripcionCliente;
    private String descripcionProducto;
    private String descripcionPais;
    private Long idCliente;
    private Integer productoOperacion;
    private BigDecimal montoOperacion;
    
    private Boolean esEstadoCompletado;
    
    
    public Boolean mostrarFormularioExtra = Boolean.FALSE;
    
    //agregan variables para incidencia FNXII-7223

    private Boolean esResponsableOperacion;
    
    private Boolean esGerentePais;
    

    public void setListaHabilitada(Boolean listaHabilitada) {
        this.listaHabilitada = listaHabilitada;
    }

    public Boolean getListaHabilitada() {
        return listaHabilitada;
    }

    public void setAgregarEvidencia(Boolean agregarEvidencia) {
        this.agregarEvidencia = agregarEvidencia;
    }

    public Boolean getAgregarEvidencia() {
        return agregarEvidencia;
    }

    public void setModificarAdquisicion(Boolean modificarAdquisicion) {
        this.modificarAdquisicion = modificarAdquisicion;
    }

    public Boolean getModificarAdquisicion() {
        return modificarAdquisicion;
    }


    public AdquisicionesBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void precarga() {
    logger.warning("Dentro de precarga");
    long startTime = System.currentTimeMillis();                    
    startTime = System.currentTimeMillis();
    logger.warning("Tiempo de inicio adquisciones respuesta del: "
    + startTime);
    
      edita2agrega1=1;  
      disableBotones = Boolean.FALSE;
      disableBotonesAcciones = Boolean.TRUE;
      disableFormulario = Boolean.TRUE; 
      agregarAdquisicion = Boolean.FALSE;
      modificarAdquisicion = Boolean.FALSE;
      agregarEvidencia = Boolean.FALSE;
      listaHabilitada = Boolean.TRUE;
      Boolean aplicaPga=Boolean.FALSE;
      String state = null;
        
        JSFUtils.setExpressionValue("#{sessionScope.adquisicionForm}", disableFormulario);
        
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pTarea}") != null) {
                tarea = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pTarea}").toString());
                logger.warning("Tarea " + tarea);    
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("Tarea no obtenida");
        }

        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pLectura}") != null) {
                lectura = Boolean.parseBoolean(JSFUtils.resolveExpression("#{pageFlowScope.pLectura}").toString());
                logger.warning("Lectura " + lectura);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("Lectura no obtenida");
        }

        try {
            idOperacion = Long.parseLong(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
            logger.warning("id operacion  " + idOperacion);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("Operacion no obtenida");
        }
        
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pSector}") != null) {
                sector = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pSector}").toString());
                logger.warning("Sector " + sector);    
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("Sector no obtenido");
        }
        
        
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pOperacionAprobada}") != null) {
                operacionAprobada = Boolean.parseBoolean(JSFUtils.resolveExpression("#{pageFlowScope.pOperacionAprobada}").toString());
                logger.warning("Operacion Aprobada: " + operacionAprobada);    
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("Operacion Aprobada no obtenida");
        }
        
        logger.warning("Antes de crear Adquisicion PGA");
        if(tarea != null && sector != null){
            logger.warning("Los datos para crear Adquisicion PGA no vienen en nulo");
            
            if((sector == 11 || sector == 12) && operacionAprobada && tarea == 65){
               aplicaPga=Boolean.TRUE;
            }   
        }
                
        Long adquisionObtenida=null;
        
        BindingContainer bindingAdquisicon = ADFUtils.getBindingContainer();
        OperationBinding operationBindingAdquisicon = bindingAdquisicon.getOperationBinding("cargaAdquisicion");
        operationBindingAdquisicon.getParamsMap().put("aplicaPGA", aplicaPga);
        adquisionObtenida=(Long)operationBindingAdquisicon.execute();
        
        if(null!=adquisionObtenida){
                idAdquiscionPrincipal=adquisionObtenida;
                JSFUtils.setExpressionValue("#{sessionScope.idAdquisicion}", adquisionObtenida); 
            }
        logger.warning("Entra adquisicion" + adquisionObtenida);
        
        if(tarea != null){
            if(tarea == 65){
                setMostrarFormularioExtra(Boolean.TRUE);
            }
            else{
                setMostrarFormularioExtra(Boolean.FALSE);
            }
        }
        
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pState}") != null) {
                state = (String) JSFUtils.resolveExpression("#{pageFlowScope.pState}");
                logger.warning("state :" + state);   
                esEstadoCompletado = state.equals("COMPLETED") ? Boolean.TRUE : Boolean.FALSE;
            }else{
                logger.warning("pState es nulo");
                esEstadoCompletado = Boolean.FALSE;
            }
        } catch (Exception ex) {
            logger.severe("Error al recuperar #{pageFlowScope.pState} :",ex);
            logger.warning("pState no obtenido");
            esEstadoCompletado = Boolean.FALSE;
        }
        
        logger.warning("esEstadoCompletado : "+esEstadoCompletado);
        
        
        //cambios relacionado con incidencia FNXII-7223
        try{
            if (JSFUtils.resolveExpression("#{pageFlowScope.esResponsableOperacion}") != null) {
                logger.warning("#{pageFlowScope.esResponsableOperacion} no es nulo");
                logger.warning("#{pageFlowScope.esResponsableOperacion} :"+JSFUtils.resolveExpression("#{pageFlowScope.esResponsableOperacion}"));
                esResponsableOperacion = Boolean.parseBoolean(JSFUtils.resolveExpression("#{pageFlowScope.esResponsableOperacion}").toString());
            }else{
                logger.warning("#{pageFlowScope.esResponsableOperacion} es nulo");
                esResponsableOperacion = Boolean.TRUE;
            }
        }catch(Exception e){
            logger.severe("Error obtener esResponsableOperacion :",e);
        }
        
        logger.warning("esResponsableOperacion :" + esResponsableOperacion);  
        
        try{
            if (JSFUtils.resolveExpression("#{pageFlowScope.esGerentePais}") != null) {
                logger.warning("#{pageFlowScope.esGerentePais} no es nulo");
                logger.warning("#{pageFlowScope.esGerentePais} :"+JSFUtils.resolveExpression("#{pageFlowScope.esGerentePais}"));
                esGerentePais = Boolean.parseBoolean(JSFUtils.resolveExpression("#{pageFlowScope.esGerentePais}").toString());
            }else{
                logger.warning("#{pageFlowScope.esGerentePais} es nulo");
                esGerentePais = Boolean.TRUE;
            }
        }catch(Exception e){
            logger.severe("Error obtener esGerentePais :",e);
        }
        
        logger.warning("esGerentePais :" + esGerentePais);
        
        
        long endTime = System.currentTimeMillis();
        logger.warning("Tiempo de fin de carga de adquisciones respuesta: " + endTime);
        logger.warning("Tiempo final de respuesta de precarga de adquisiciones: "
        + (endTime - startTime)/1000 + " segundos");
        
        logger.warning("Fuera de precarga");
       
    }

    public Long getIdAdquisiones() {
        return idAdquisiones;
    }

    public void setIdAdquisiones(Long idAdquisiones) {
        this.idAdquisiones = idAdquisiones;
    }

    public Boolean getLectura() {
        return lectura;
    }

    public void setLectura(Boolean lectura) {
        this.lectura = lectura;
    }
    public Long getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Integer getTarea() {
        return tarea;
    }

    public void setTarea(Integer tarea) {
        this.tarea = tarea;
    }
    
    public void setDisableBotones(Boolean disableBotones) {
        this.disableBotones = disableBotones;
    }

    public Boolean getDisableBotones() {
        return disableBotones;
    }

    public void setDisableFormulario(Boolean disableFormulario) {
        this.disableFormulario = disableFormulario;
    }

    public Boolean getDisableFormulario() {
        return disableFormulario;
    }

    public void setDisableBotonesAcciones(Boolean disableBotonesAcciones) {
        this.disableBotonesAcciones = disableBotonesAcciones;
    }

    public Boolean getDisableBotonesAcciones() {
        return disableBotonesAcciones;
    }   
    
    public void setAgregarAdquisicion(Boolean agregarAdquisicion) {
        this.agregarAdquisicion = agregarAdquisicion;
    }

    public Boolean getAgregarAdquisicion() {
        return agregarAdquisicion;
    }
    

    public void setSector(Integer sector) {
        this.sector = sector;
    }

    public Integer getSector() {
        return sector;
    }
    
    public void setOperacionAprobada(Boolean operacionAprobada) {
        this.operacionAprobada = operacionAprobada;
    }

    public Boolean getOperacionAprobada() {
        return operacionAprobada;
    }

    public Long getIdAdquiscionPrincipal() {
        return idAdquiscionPrincipal;
    }

    public void setIdAdquiscionPrincipal(Long idAdquiscionPrincipal) {
        this.idAdquiscionPrincipal = idAdquiscionPrincipal;
    }

    public Integer getEdita2agrega1() {
        return edita2agrega1;
    }

    public void setEdita2agrega1(Integer edita2agrega1) {
        this.edita2agrega1 = edita2agrega1;
    }

    public Long getIdObjecionUnica() {
        return idObjecionUnica;
    }

    public void setIdObjecionUnica(Long idObjecionUnica) {
        this.idObjecionUnica = idObjecionUnica;
    }

    public void setIdAdquisicionAux(Long idAdquisicionAux) {
        this.idAdquisicionAux = idAdquisicionAux;
    }

    public Long getIdAdquisicionAux() {
        return idAdquisicionAux;
    }

    public void setMostrarFormularioExtra(Boolean mostrarFormularioExtra) {
        this.mostrarFormularioExtra = mostrarFormularioExtra;
    }

    public Boolean getMostrarFormularioExtra() {
        return mostrarFormularioExtra;
    }

    public String getDescripcionOperacion() {
        return descripcionOperacion;
    }

    public void setDescripcionOperacion(String descripcionOperacion) {
        this.descripcionOperacion = descripcionOperacion;
    }

    public String getDescripcionCliente() {
        return descripcionCliente;
    }

    public void setDescripcionCliente(String descripcionCliente) {
        this.descripcionCliente = descripcionCliente;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getDescripcionPais() {
        return descripcionPais;
    }

    public void setDescripcionPais(String descripcionPais) {
        this.descripcionPais = descripcionPais;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getProductoOperacion() {
        return productoOperacion;
    }

    public void setProductoOperacion(Integer productoOperacion) {
        this.productoOperacion = productoOperacion;
    }

    public BigDecimal getMontoOperacion() {
        return montoOperacion;
    }

    public void setMontoOperacion(BigDecimal montoOperacion) {
        this.montoOperacion = montoOperacion;
    }


    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }


    public Boolean getEsResponsableOperacion() {
        return esResponsableOperacion;
    }

    public void setEsResponsableOperacion(Boolean esResponsableOperacion) {
        this.esResponsableOperacion = esResponsableOperacion;
    }

    public Boolean getEsGerentePais() {
        return esGerentePais;
    }

    public void setEsGerentePais(Boolean esGerentePais) {
        this.esGerentePais = esGerentePais;
    }
}
