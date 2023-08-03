package org.bcie.fenix.view.gestordesembolsos;

import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.controller.binding.TaskFlowBindingAttributes;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

import org.bcie.fenix.common.view.beans.FenixActionBean;
import org.bcie.fenix.view.acciones.AccionesBean;
import org.bcie.fenix.common.FenixConstants;

public class VerCrearSolicitudDesembolsosBean extends FenixActionBean implements Serializable {
    @SuppressWarnings("compatibility:3748446980789321264")
    private static final long serialVersionUID = 1L;
    
    public static final String BUNDLE = "view.GestorDesembolsosFenixVCBundle";

    public static ADFLogger logger = null;
    private RichRegion gestorDocumentosUIC;
    private boolean cargaExitosa;
    private String mensajeError;

    public VerCrearSolicitudDesembolsosBean() {
        if (logger == null){
            logger = ADFLogger.createADFLogger(VerCrearSolicitudDesembolsosActionBean.class);
        }
    }

    private Long idOperacion = null;
    private Integer idEstadoOperacion = null;
    private Long idSolicitud = null;
    private Integer idEstadoSolicitud = null;
    private Long idLineaCredito = null;
    private Long idContratoDesembolso = null;
    private String operacionSolicitud = null;
    private Integer idTarea = FenixConstants.CG10_GESTOR_DESEMBOLSOS;
    private Boolean seHaMostrarPopUp = Boolean.FALSE;
   
    private Boolean cuentaConPreinversion = Boolean.FALSE;

    private Boolean btnBuscarOperacion = Boolean.FALSE;
    
    private List<TaskFlowBindingAttributes> multiTaskFlowGesDocBindings = new ArrayList<TaskFlowBindingAttributes>();
    private Integer cargarRegionDocumentos = Integer.valueOf(0);

    public void recuperarDatosParaVerDetalleDesembolso() {
        logger.log(ADFLogger.WARNING, "*** Inicia metodo recuperarDatosParaVerDetalleDesembolso para el flujo de ver detalle de desembolso");
        operacionSolicitud = JSFUtils.resolveExpression("#{pageFlowScope.operacionSolicitud}").toString();

        logger.warning("Recuperando valores solicitados ....");
       
        idOperacion = (null == JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}"))? null :  new Long(JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}").toString());
        idSolicitud = (null == JSFUtils.resolveExpression("#{pageFlowScope.idSolicitud}") )? null : new Long(JSFUtils.resolveExpression("#{pageFlowScope.idSolicitud}").toString());
        idEstadoSolicitud = (null == JSFUtils.resolveExpression("#{pageFlowScope.idEstadoSolicitud}"))? null : Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.idEstadoSolicitud}").toString());
        idLineaCredito = (null == JSFUtils.resolveExpression("#{pageFlowScope.idLineaCredito}") )? null : new Long(JSFUtils.resolveExpression("#{pageFlowScope.idLineaCredito}").toString());
        idContratoDesembolso = (null == JSFUtils.resolveExpression("#{pageFlowScope.idContratoDesembolso}") )? null : new Long(JSFUtils.resolveExpression("#{pageFlowScope.idContratoDesembolso}").toString());
                   
        verDatosDisponibles();
                                        
        logger.log(ADFLogger.WARNING, "*** Termina metodo recuperarDatosParaVerDetalleDesembolso Datos Recuperados: idOperacion->"+this.idOperacion+" idSolicitud->"+idSolicitud);
    }
   
    public void verDatosDisponibles(){
            logger.log(ADFLogger.WARNING, "*** Inicia metodo verDatosDisponibles");
            logger.warning("idOperacion->"+idOperacion);
            logger.warning("idSolicitud->"+idSolicitud);
            logger.warning("idEstadoSolicitud->"+idEstadoSolicitud);
            logger.warning("idLineaCredito->"+idLineaCredito);
            logger.warning("idContratoDesembolso->"+idContratoDesembolso);
            logger.log(ADFLogger.WARNING, "*** Termina metodo verDatosDisponibles");
        }
   
    public void consultarDatosSolicitud() {
        logger.log(ADFLogger.WARNING, "INTO consultarDatosSolicitud");
        Row rowDetalleDesembolsosOperacion = null;
        
        cargaExitosa = Boolean.TRUE;
        
        if (this.getIdOperacion() != null) {
            BindingContainer bindings = getBindings();

            try {
                OperationBinding operationBinding = bindings.getOperationBinding("consultarOperacionPorIdOperacion");
                operationBinding.getParamsMap().put("idOperacion", this.getIdOperacion());
                operationBinding.execute();
                
                if (!operationBinding.getErrors().isEmpty()) {
                    setCargaExitosa(Boolean.FALSE);
                    setMensajeError(operationBinding.getErrors().toString());
                } else {
                    Boolean resultado = (Boolean)operationBinding.getResult();
                    
                    if (!resultado) {
                        setCargaExitosa(Boolean.FALSE);
                        setMensajeError(getStringFromBundle("ERROR_AL_CARGAR_OPERACION"));
                    }
                }

            } catch (Exception e) {
                setCargaExitosa(Boolean.FALSE);
                setMensajeError(getStringFromBundle("ERROR_AL_CARGAR_OPERACION"));
                logger.log(ADFLogger.ERROR, "Error en consultarDatosSolicitud.", e);
                JSFUtils.addFacesErrorMessage("Error al Consultar datos de la operacion. Por favor intente más tarde.");
            }

            if (isCargaExitosa()) {
                try {
                    rowDetalleDesembolsosOperacion =
                        ADFUtils.getDCBindingContainer().findIteratorBinding("DetalleDesembolsosOperacionVOIterator").getViewObject().getCurrentRow();
                } catch (Exception e) {
                    setCargaExitosa(Boolean.FALSE);
                    setMensajeError(getStringFromBundle("ERROR_AL_CARGAR_OPERACION"));
                    logger.warning("ERROR al recuperar el VO del iterador de DetalleDesembolsosOperacionVOIterator.", e);
                }
            }
            
            if (isCargaExitosa()) {
                if(null != rowDetalleDesembolsosOperacion){
                    logger.warning("El rowDetalleDesembolsosOperacion se recupero correctamente. Asignando valores a Bean de sesion.");
                    asignarDatosOperacionClienteBeanSesion(rowDetalleDesembolsosOperacion);
                }else{
                    setCargaExitosa(Boolean.FALSE);
                    setMensajeError(getStringFromBundle("ERROR_AL_CARGAR_OPERACION"));
                    logger.warning("El DetalleDesembolsosOperacionVOIterator");
                }
            }
            
            if (isCargaExitosa()) {
                /** FIXME -- Convertir a metodo booleano **/
                validarPreinversion();
            }
            
            if (isCargaExitosa()) {
                //setCargarRegionDocumentos(1)--->bandera en cero, no se carga el taskflow del gestor de documentos en la precarga de la pantalla, solo hasta el evento DisclosureListener
                setCargarRegionDocumentos(Integer.valueOf(1));
                renderRegionGestorDocumentos();
            }
        } else {
            setCargaExitosa(Boolean.FALSE);
            setMensajeError(getStringFromBundle("ERROR_AL_CARGAR_OPERACION"));
            logger.warning("Error el en el metodo consultarDatosSolicitud se requiere un id de operacion");
        }
    }
    
    public Boolean asignarDatosOperacionClienteBeanSesion(Row row) {
        logger.warning("Inicia metodo asignarDatosOperacionClienteBeanSesion.");
        Boolean resultado = Boolean.FALSE;
        String operacion = null, cliente = null, producto = null, pais = null, flexcube = null,
            noObjecion = null, scr = null, mora = null, diasMora = null,
            moneda = null, sectorDescripcion = null, descSectorMercado = null;
        
        Long IdOperacion = null, IdCliente = null, Sector = null, IdProducto = null, IdSectorMercado = null;
        
        Double MontoMora = null, MontoFormalizado = null, MontoDesembolsar = null;
        
        oracle.jbo.domain.Date VigenciaNoObjecionLaft = null;

        if (null == row) {
            logger.warning("El row de DetalleDesembolsosOperacionVO es NULL.");
            return resultado;
        }

        DatosSesionManagedBean datosSesionBean =
            (DatosSesionManagedBean) JSFUtils.resolveExpression("#{DatosSesionManagedBean}");

            logger.warning("Recuperando datos de DetalleDesembolsosOperacionVO.");
            try{
                operacion = (String) row.getAttribute("Operacion");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Operacion.", e);
            }
            
            try{
                cliente = (String) row.getAttribute("Cliente");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Cliente.", e);
            }
            
            try{
                producto = (String) row.getAttribute("Producto");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Producto.", e);
            }
            
            try{
                pais = (String) row.getAttribute("Pais");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Pais.", e);
            }
            
            try{
                flexcube = (String) row.getAttribute("Flexcube");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Flexcube.", e);
            }
            
            try{
                noObjecion = (String) row.getAttribute("NoObjecion");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo NoObjecion.", e);
            }
            
            try{
                //if (row.getAttribute("Scr") != null ){
                    scr = (String) row.getAttribute("Scr");    
                //}
                /*  FNXII-6516 - 05/10/2017
                if(null == scr || "".compareTo(scr) == 0 || scr.trim().length() == 0){
                    logger.warning("El scr es nulo o cadena vacia, se le asigna '0'");
                    scr = "0";
                }*/
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Scr.", e);
            }
            
            try{
                mora = (String) row.getAttribute("Mora");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Mora.", e);
            }
            
            try{
                diasMora = (String) row.getAttribute("DiasMora");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo DiasMora.", e);
            }
            
            try{
                moneda = (String) row.getAttribute("Moneda");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Moneda.", e);
            }
            
            try{
                sectorDescripcion = (String) row.getAttribute("SectorDescripcion");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo SectorDescripcion.", e);
            }
            
            try{
                descSectorMercado = (String) row.getAttribute("DescSectorMercado");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo DescSectorMercado.", e);
            }
            
            try{
                IdOperacion = (Long) row.getAttribute("IdOperacion");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo DescSectorMercado.", e);
            }
            
            try{
                IdCliente = (Long) row.getAttribute("IdCliente");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo IdCliente.", e);
            }
            
            try{
                Sector = (Long) row.getAttribute("Sector");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Sector.", e);
            }
            
            try{
                IdProducto = (Long) row.getAttribute("IdProducto");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo IdProducto.", e);
            }
            
            try{
                IdSectorMercado = (Long) row.getAttribute("IdSectorMercado");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo IdSectorMercado.", e);
            }
            
            try{
                MontoMora = (Double) row.getAttribute("MontoMora");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo MontoMora.", e);
            }
            
            try{
                MontoFormalizado = (Double) row.getAttribute("MontoFormalizado");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo MontoFormalizado.", e);
            }
            
            try{
                MontoDesembolsar = (Double) row.getAttribute("MontoDesembolsar");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo MontoDesembolsar.", e);
            }
            
            try{
                VigenciaNoObjecionLaft = (oracle.jbo.domain.Date) row.getAttribute("VigenciaNoObjecionLaft");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo VigenciaNoObjecionLaft.", e);
            }
        
        logger.warning("operacion" + operacion);
        logger.warning("cliente" + cliente);
        logger.warning("producto" + producto);
        logger.warning("pais" + pais);
        logger.warning("flexcube" + flexcube);
        logger.warning("noObjecion" + noObjecion);
        logger.warning("scr" + scr);
        logger.warning("mora" + mora);
        logger.warning("diasMora" + diasMora);
        logger.warning("moneda: " + moneda);
        logger.warning("sectorDescripcion" + sectorDescripcion);
        logger.warning("descSectorMercado" + descSectorMercado);
        logger.warning("IdOperacion" + IdOperacion);
        logger.warning("IdCliente" + IdCliente);
        logger.warning("Sector" + Sector);
        logger.warning("IdProducto" + IdProducto);
        logger.warning("IdSectorMercado" + IdSectorMercado);
        logger.warning("MontoMora" + MontoMora);
        logger.warning("MontoFormalizado" + MontoFormalizado);
        logger.warning("MontoDesembolsar" + MontoDesembolsar);
        logger.warning("VigenciaNoObjecionLaft" + VigenciaNoObjecionLaft);
        
        logger.warning("Asignando datos de DetalleDesembolsosOperacionVO a DatosSesionManagedBean.");
        datosSesionBean.setOperacion(operacion);
        datosSesionBean.setCliente(cliente);
        datosSesionBean.setProducto(producto);
        datosSesionBean.setPais(pais);
        datosSesionBean.setFlexcube(flexcube);
        datosSesionBean.setNoObjecion(noObjecion);
        datosSesionBean.setScr(scr);
        datosSesionBean.setMora(mora);
        datosSesionBean.setDiasMora(diasMora);
        datosSesionBean.setMoneda(moneda);
        datosSesionBean.setSectorDescripcion(sectorDescripcion);
        datosSesionBean.setDescSectorMercado(descSectorMercado);
        datosSesionBean.setIdOperacion(IdOperacion);
        datosSesionBean.setIdCliente(IdCliente);
        datosSesionBean.setSector(Sector);
        datosSesionBean.setIdProducto(IdProducto);
        datosSesionBean.setIdSectorMercado(IdSectorMercado);
        datosSesionBean.setMontoMora(MontoMora);
        datosSesionBean.setMontoFormalizado(MontoFormalizado);
        datosSesionBean.setMontoDesembolsar(MontoDesembolsar);
        datosSesionBean.setVigenciaNoObjecionLaft(VigenciaNoObjecionLaft);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("montoDesembolsarSession", MontoDesembolsar);
        logger.warning("Termina metodo asignarDatosOperacionClienteBeanSesion.");
        return resultado;
    }
    
    
    public void validarPreinversion() {
        logger.warning("*Inf, inicia metodo validarPreinversion.");
         
        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("validaPreinversion");
            operBinding.getParamsMap().put("idOperacion", getIdOperacion());
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("Error al ejecutar binding validaPreinversion: " + operBinding.getErrors());
            } else {
                cuentaConPreinversion = (Boolean) operBinding.getResult();
            }

        } catch (Exception e) {
            logger.warning("Error en validaPreinversion" + e);
        }
        logger.warning("*Inf, cuentaConPreinversion: " + cuentaConPreinversion);
    }
    

    public String obtenerMontoDesembolsarOperacion(){
        String montoDesembolsar = (null == ADFUtils.getBoundAttributeValue("MontoDesembolsar"))? null : ADFUtils.getBoundAttributeValue("MontoDesembolsar").toString();                
            return montoDesembolsar;
        }
    
    public void renderRegionGestorDocumentos(){
        logger.warning("Inicia renderRegionGestorDocumentos en VerCrearSolicitudDesembolsoBean");
        Integer bandera = getCargarRegionDocumentos();
        logger.warning("Valor cargarRegionDocumentos: " + bandera);
        if(bandera == 1){
            logger.warning("Precarga de la pantalla, no se realiza ninguna accion para cargar el taskFlow gestorDocumentosBTF");
        }
        else if(bandera == 2){
            logger.warning("Se ejecuta el evento DisclosureListener, se ingresa al if... cargando taskFlow gestorDocumentosBTF");
            multiTaskFlowGesDocBindings = new ArrayList<TaskFlowBindingAttributes>();
            
            TaskFlowBindingAttributes taskFlowBindingAttributes = new TaskFlowBindingAttributes();
            taskFlowBindingAttributes.setId("ID_GestorDocumentos");
            taskFlowBindingAttributes.setTaskFlowId(new TaskFlowId("/WEB-INF/org/bcie/fenix/view/documentos/gestorDocumentosBTF.xml",
                                                    "gestorDocumentosBTF"));
            multiTaskFlowGesDocBindings.add(taskFlowBindingAttributes);
        }
        logger.warning("Finaliza renderRegionGestorDocumentos en VerCrearSolicitudDesembolsoBean");
    }
    
    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (null != resourceBundle) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }

    /****************  Accesors  ********************/
  
    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Long getIdSolicitud() {
        return idSolicitud;
    }  
    
    public void setBtnBuscarOperacion(Boolean btnBuscarOperacion) {
        this.btnBuscarOperacion = btnBuscarOperacion;
    }

    public Boolean getBtnBuscarOperacion() {
        return btnBuscarOperacion;
    }

    public void setIdEstadoSolicitud(Integer idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public Integer getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }
    public void setIdLineaCredito(Long idLineaCredito) {
        this.idLineaCredito = idLineaCredito;
    }

    public Long getIdLineaCredito() {
        return idLineaCredito;
    }

    public void setIdContratoDesembolso(Long idContratoDesembolso) {
        this.idContratoDesembolso = idContratoDesembolso;
    }

    public Long getIdContratoDesembolso() {
        return idContratoDesembolso;
    }
    
    public void setOperacionSolicitud(String operacionSolicitud) {
        this.operacionSolicitud = operacionSolicitud;
    }

    public String getOperacionSolicitud() {
        return operacionSolicitud;
    }
    
    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Integer getIdTarea() {
        return idTarea;
    }
    
    public void setSeHaMostrarPopUp(Boolean seHaMostrarPopUp) {
        this.seHaMostrarPopUp = seHaMostrarPopUp;
    }

    public Boolean getSeHaMostrarPopUp() {
        return seHaMostrarPopUp;
    }
    
    public void setCuentaConPreinversion(Boolean cuentaConPreinversion) {
        this.cuentaConPreinversion = cuentaConPreinversion;
    }

    public Boolean getCuentaConPreinversion() {
        return cuentaConPreinversion;
    }

    public void setMultiTaskFlowGesDocBindings(List<TaskFlowBindingAttributes> multiTaskFlowGesDocBindings) {
        this.multiTaskFlowGesDocBindings = multiTaskFlowGesDocBindings;
    }

    public List<TaskFlowBindingAttributes> getMultiTaskFlowGesDocBindings() {
        return multiTaskFlowGesDocBindings;
    }

    public void setCargarRegionDocumentos(Integer cargarRegionDocumentos) {
        this.cargarRegionDocumentos = cargarRegionDocumentos;
    }

    public Integer getCargarRegionDocumentos() {
        return cargarRegionDocumentos;
    }

    public void setGestorDocumentosUIC(RichRegion gestorDocumentosUIC) {
        this.gestorDocumentosUIC = gestorDocumentosUIC;
    }

    public RichRegion getGestorDocumentosUIC() {
        return gestorDocumentosUIC;
    }

    public boolean isCargaExitosa() {
        return cargaExitosa;
    }

    public void setCargaExitosa(boolean cargaExitosa) {
        this.cargaExitosa = cargaExitosa;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public Integer getIdEstadoOperacion() {
        return idEstadoOperacion;
    }

    public void setIdEstadoOperacion(Integer idEstadoOperacion) {
        this.idEstadoOperacion = idEstadoOperacion;
    }
}
