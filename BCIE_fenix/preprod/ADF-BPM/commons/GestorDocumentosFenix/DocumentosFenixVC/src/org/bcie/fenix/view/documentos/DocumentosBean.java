package org.bcie.fenix.view.documentos;

import com.bcie.xmlns.documentoservice.Documento12BndQSService;
import com.bcie.xmlns.documentoservice.DocumentoPT;

import java.beans.IntrospectionException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.ResourceBundle;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;



import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.TreeModel;
import org.apache.myfaces.trinidad.model.UploadedFile;

import org.bcie.documentobo.Documento;
import org.bcie.documentobo.Etapa;
import org.bcie.documentobo.TipoDcoumento;
import org.bcie.documentomo.ConsultaDocumentosRequestType;
import org.bcie.documentomo.ConsultaDocumentosResponseType;
import org.bcie.errorbo.Error;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.utils.ModelUtils;
import org.bcie.fenix.common.model.vo.DocumentoVORowImpl;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.resultbo.SuccessType;

public class DocumentosBean implements Serializable {
    @SuppressWarnings("compatibility:8882949479184774165")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    private transient Object instance = null;
    private transient TreeModel treeModel = null;
    private String WSDL_DOCUMENTO = IWsdlLocation.DOCUMENTO;
    private String wsdlLocation = null; // Variable donde se guarda la URL del servicio de Documentos
    private static final String URL_DOCPOP = IWsdlLocation.DOCPOP;
    private String urlDocpop = null; // Variable donde se guarda la URL del docpop (rutaOnbase)
    private static final String BUNDLE = "org.bcie.fenix.view.DocumentosFenixVCBundle";
       
    // Variables para cambiar contenido de forma dinámica
    private String textoFichaDoc = null; // Texto "Ficha del documento"/"Documento agregado a la tarea"
    private String opAdjuntarModificar = null; // Cambia texto en popup para Adjuntar o Modificar documento
    private String accionDeTablaArbol = null; // Indica si el usuario hizo click en la tabla o en el árbol    
    private Boolean esIdOperacionValido = false; // Variable para deshabilitar botones si el id es null o -1
    private Integer numDocumentosTree = -1; // Variable para ocultar botones si el tree no tiene Documentos
    private Boolean esDocumentoSubidoEnGestorOp = false; // Variable para identificar a los documentos del árbol, subidos desde el Gestor de Operaciones
    private Boolean esGestorOp = false; // Variable para identificar si se encuentra en el Gestor de Operaciones
    private Boolean rolesEnviarDocumento = Boolean.FALSE;

    // Variables para adjuntar/modificar archivo en popup
    private UploadedFile documentoAdjunto;
    private InputStream documentoInputStream;
    private String nombreArchivo;
    private String codigoArchivo;
    private Date fechaArchivo;
    private Integer idTipo; // Combo Tipo
    private String resumenArchivo;
    private Boolean isJustificacion = false; // Checkbox de justificación para adjuntar/modificar archivo
    
    // Mapeo para datos de documento (provenientes del servicio)
    private oracle.jbo.domain.Number idDocumento = null;
    private oracle.jbo.domain.Number idTipoDoc = null;
    private String tipoDoc = null;
    private String etapaDoc = null;
    private String codigoDoc = null; 
    private oracle.jbo.domain.Number idExterno = null;
    private oracle.jbo.domain.Number idOperacion = null;
    private String nombreDoc = null;
    private String fileName = null;
    private String mimeType = null;
    private oracle.jbo.domain.Number idAdjunto = null;
    private Boolean esJustificacionDoc;
    private String resumenDoc = null;
    private String fechaDoc = null;
    private String fechaRegistro = null;
    private String tareaDoc = null;
    private String creadoPor = null;
    private String modificadoPor = null;
    private String nombreCreadoPor = null; 
    private String nombreModificadoPor = null;
    private Boolean esModificable = null;
    private Boolean esBorrable = null;
    private oracle.jbo.domain.Number idTarea = null;
    private String estadoDocumento = null;

    //variable para habilitar consulta por numeroSerieDocumento (idFLujo)
    private Boolean esConsultarPorNumeroSerieDocumento = null;
    
    //Accion - Enviar Documento a OnBase
    private static final Integer ENVIAR_DOCUMENTO_ONBASE = 2;
    
    //Tareas Gestores
    private static final Integer GESTOR_OPERACIONES = 65;
    private static final Integer GESTOR_CLIENTES = 112;
    private static final Integer GESTOR_DESEMBOLSOS = 192;
    
    private Boolean btnActualizarDocs = Boolean.FALSE;
    
    private Boolean esEstadoCompletado;

    public DocumentosBean() {
        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void inicializarArbolDocumentos() {
        
        oracle.jbo.domain.Number idTareaBpm = null;
        
        setTextoFichaDoc("Documento agregado a la tarea");
        setAccionDeTablaArbol("tabla"); // Por default inicia con un row seleccionado en tabla
        
        if((JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null) 
           && (!JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString().equalsIgnoreCase("-1")) 
           && (JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString().trim().length() > 0)) {
            
            //validar si "pConsultarPorNumeroSerieDocumento" viene "TRUE".
            if(Boolean.TRUE ==(Boolean)JSFUtils.resolveExpression("#{pageFlowScope.pConsultarPorNumeroSerieDocumento}")){
                setEsConsultarPorNumeroSerieDocumento(Boolean.TRUE);
            }
            // Id de operación válido
            this.generarArbolDocumentos();
            setEsIdOperacionValido(true);
        }
        else{
            // Mostramos un tree vacío, solo con nodo padre si el id de operación es null o -1 (se usa para probar)
            ArrayList<TreeItem> rootTreeItems = new ArrayList<TreeItem>();
            TreeItem treeItem1 = new TreeItem("folder", "Documentos");
            rootTreeItems.add(treeItem1);    
            this.setListInstance(rootTreeItems);
        }
        
        if((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") == null) 
           || (JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString().trim().length() == 0)) {
            
            try {
                idTareaBpm =
                    ((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null) &&
                     (JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString().trim().length() > 0)) ?
                    new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}")) : null;
            } catch (SQLException e) {
                logger.warning("Casting error idTarea.");
            }
            
            Integer idTarea = new Integer (idTareaBpm.intValue());
            
            if (idTareaBpm.compareTo(GESTOR_OPERACIONES) == 0 ||
                idTareaBpm.compareTo(GESTOR_CLIENTES) == 0 ||
                idTareaBpm.compareTo(GESTOR_DESEMBOLSOS) == 0) {
                logger.warning("idTarea: " + idTarea);
                
                // Si NO se manda un IdTareaBpm entonces nos encontramos en el Gestor de Operaciones
                setEsGestorOp(true);   
            }
        }
        
        // Reset a variable que refresca el Gestor de Documentos desde el proceso de LAFT - Recopilar Información
        ADFContext.getCurrent().getSessionScope().put("refreshGestorDoc", false);
        
        //metodo para evaluar el parametro de entrada #{pageFlowScope.pState}
        evaluarParametroPStateBpm();
        
        logger.log(ADFLogger.WARNING, "pIdOperacion: " + JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
        logger.log(ADFLogger.WARNING, "pIdTareaBpm: " + JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}"));
        logger.log(ADFLogger.WARNING, "pNumeroSerieDocumento: " + JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}"));
        logger.log(ADFLogger.WARNING, "pConsultarPorNumeroSerieDocumento: " + JSFUtils.resolveExpression("#{pageFlowScope.pConsultarPorNumeroSerieDocumento}"));
    }
    
    public void evaluarParametroPStateBpm(){
        logger.warning("Dentro de evaluarParametroPStateBpm");
        String state = null;
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pState}") != null) {
                state = (String) JSFUtils.resolveExpression("#{pageFlowScope.pState}");
                logger.warning("state :" + state);
                setEsEstadoCompletado(state.equals("COMPLETED") ? Boolean.TRUE : Boolean.FALSE);
            }else{
                logger.warning("pState es nulo");
                setEsEstadoCompletado(Boolean.FALSE);
            }
        } catch (Exception ex) {
            logger.severe("Error al recuperar #{pageFlowScope.pState} :",ex);
            logger.warning("pState no obtenido");
        }

        logger.warning("esEstadoCompletado : " + getEsEstadoCompletado());
        logger.warning("Fuera de evaluarParametroPStateBpm");
    }
    
    private void generarArbolDocumentos(){
        
        ArrayList<TreeItem> rootTreeItems = null;
        ArrayList<TreeItem> rootChildren = null;
        ArrayList<TreeItem> etapaChildren = null;
        ArrayList<TreeItem> tipoDocumentoChildren = null;
        TreeItem treeItemRoot = null;
        TreeItem treeItemEtapa = null;
        TreeItem treeItemTipoDocumento = null;
        TreeItem treeItemDocumento = null;
        Documento12BndQSService documento12BndQSService = null;
        DocumentoPT documentoPT = null;
        ConsultaDocumentosRequestType request = null;
        ConsultaDocumentosResponseType arbolDocumentos = null;
        SuccessType result = null;
        Error error = null;
        oracle.jbo.domain.Number idOperacion = null;
        oracle.jbo.domain.Number idTareaBpm = null;
        String errorCode = null;
        String errorDescription = null;
        List<Etapa> etapas = null;
        SimpleDateFormat simpleDateFormat = null;
        //variable numero de serie del documento
        oracle.jbo.domain.Number  numeroSerieDocumento = null;
        
        // Inicialización de listas para nodo raíz
        rootTreeItems = new ArrayList<TreeItem>();
        
        // Nodo raíz por default
        treeItemRoot = new TreeItem("folder", "Documentos");
        
        // Cargamos árbol de documentos desde el servicio
        boolean servicioDisponible = Boolean.FALSE;
        try {
            documento12BndQSService = this.initDocumentoService();
            documentoPT = documento12BndQSService.getDocumento12BndQSPort();

            servicioDisponible = Boolean.TRUE;
        } catch (Exception e) {
            logger.warning("El servicio de documentos no se encuentra disponible.", e);
            JSFUtils.addFacesErrorMessage(getStringFromBundle("SERVICIO_DOCUMENTOS_NO_DISPONIBLE"));
        }

        if (servicioDisponible) {
            request = new ConsultaDocumentosRequestType();
            
            try {
                idOperacion = new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
                
                if((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null) 
                   && (JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString().trim().length() > 0))
                    idTareaBpm = new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}"));
                //validacion para revisar si viene el parametro del taskFlow 'pNumeroSerieDocumento' viene 'null'
                if((JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}") != null) 
                   && (JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}").toString().trim().length() > 0))
                    numeroSerieDocumento = new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}"));
            } catch (SQLException e) {
                logger.log(ADFLogger.ERROR, e.getMessage());
            }
            
            request.setIdOperacion(idOperacion.longValue());
            request.setIdTarea((idTareaBpm != null ? idTareaBpm.intValue(): null));
            //Agrega al request "numeroSerieDocumento" si la bandera "esConsultarPorNumeroSerieDocumento" viene "TRUE"
            if(Boolean.TRUE == getEsConsultarPorNumeroSerieDocumento()){
                request.setIdFlujoNegocio((numeroSerieDocumento != null ? numeroSerieDocumento.longValue() : null));
            }
            Date horaInicio = ModelUtils.logStartWS(logger, request,"Consultar Documentos");
            try{
                arbolDocumentos = documentoPT.consultarDocumentos(request); // bloque try Por si se cae el servicio
                ModelUtils.logEndWS(logger, arbolDocumentos,"Consultar Documentos", horaInicio);
            } catch (Exception e) { 
                logger.log(ADFLogger.ERROR, e.getMessage()); 
            }
               
            if(arbolDocumentos != null) {
                
                if(arbolDocumentos.getResultado() != null &&
                   arbolDocumentos.getResultado().getResult() != null){
                    
                    result = arbolDocumentos.getResultado().getResult();
                    error = arbolDocumentos.getResultado().getError();
                    errorCode = (error.getErrorCode() == null) ? "" : error.getErrorCode();
                    
                    if((result.value().equalsIgnoreCase("OK")) && (errorCode.trim().length() == 0)){
                        
                        // Cada Etapa es una rama del tipo folder en el árbol
                        etapas = arbolDocumentos.getDocumentos().getEtapa();
                        rootChildren = new ArrayList<TreeItem>();
                        for(Etapa etapa:etapas){
                    
                            treeItemEtapa = new TreeItem("folder", etapa.getNombreEtapa());
                            etapaChildren = new ArrayList<TreeItem>();
                            
                            // Cada Etapa tiene uno o más Tipos de documento
                            for(TipoDcoumento tipoDocumento:etapa.getTipoDocumento()){
                                
                                treeItemTipoDocumento = new TreeItem("folder", tipoDocumento.getNombreTipoDocumento());
                                tipoDocumentoChildren = new ArrayList<TreeItem>();
                                
                                // Cada Tipo de documento tiene uno o más Documentos
                                for(Documento documento:tipoDocumento.getDocumento()){
                                    
                                    simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                    // Se están regresando ceros en lugar de null para los ids, por lo tanto se valida también ese valor
                                    // Si el filename viene empty entonces le asignamos el valor del nombre 
                                    treeItemDocumento = new TreeItem("file", 
                                                    ((documento.getIdDocumento() != null) && (documento.getIdDocumento().intValue() != 0)) ? 
                                                                new oracle.jbo.domain.Number(documento.getIdDocumento()) : null,
                                                    documento.getNombre(), 
                                                    ((documento.getFilename() != null) ? documento.getFilename() : documento.getNombre()), 
                                                    documento.getMimeType(),
                                                    ((documento.getIdAdjunto() != null) && (documento.getIdAdjunto().intValue() != 0)) ?
                                                                new oracle.jbo.domain.Number(documento.getIdAdjunto()) : null,  
                                                    documento.isEsJustificacion(),         
                                                    documento.getEtapa(), 
                                                    documento.getCodigoDocumento(), 
                                                    ((documento.getIdExterno() != null) && (documento.getIdExterno().intValue() != 0)) ? 
                                                                new oracle.jbo.domain.Number(documento.getIdExterno()) : null,
                                                    ((documento.getIdOperacion() != null) && (documento.getIdOperacion().intValue() != 0)) ?
                                                                new oracle.jbo.domain.Number(documento.getIdOperacion()) : null,
                                                    documento.getUsuarioAgrega(), 
                                                    ((documento.getIdTipoDocumento() != null) && (documento.getIdTipoDocumento().intValue() != 0)) ?
                                                                new oracle.jbo.domain.Number(documento.getIdTipoDocumento()) : null, 
                                                    documento.getNombreTipoDocumento(), documento.getTarea(),
                                                    documento.getFechaDocumento() != null ? 
                                                                simpleDateFormat.format(documento.getFechaDocumento().toGregorianCalendar().getTime()) : null,
                                                    documento.getFechaRegistro() != null ?
                                                                simpleDateFormat.format(documento.getFechaRegistro().toGregorianCalendar().getTime()): null,        
                                                    documento.getUsuarioUltimaActualizacion(),
                                                    documento.getComentario(), documento.isPuedeModificar(), documento.isPuedeBorrar(),
                                                    ((documento.getIdtarea() != null) && (documento.getIdtarea().intValue() != 0)) ? 
                                                                new oracle.jbo.domain.Number(documento.getIdtarea()) : null);
                                    
                                    tipoDocumentoChildren.add(treeItemDocumento);
                                }
                                
                                treeItemTipoDocumento.setChildren(tipoDocumentoChildren);
                                etapaChildren.add(treeItemTipoDocumento);
                            }
                            
                            treeItemEtapa.setChildren(etapaChildren); 
                            rootChildren.add(treeItemEtapa);
                        }
                        
                        treeItemRoot.setChildren(rootChildren);
                    }
                    else{
                        // Error al ejecutar el servicio
                        errorDescription = error.getErrorDescription();
                        JSFUtils.addFacesErrorMessage("Código de error: " + errorCode + ". Descripción: " + errorDescription);
                        logger.log(ADFLogger.ERROR, errorDescription);
                    }    
                }else{
                    logger.severe("Error, el atributo Resultado de la consulta de WS de Documentos para la generacion del arbol de documentos es NULL");
                }
            }
            
            // Asignamos valor a variable que guarda el número de Documentos del tree
            if((treeItemRoot.getChildren() != null) && (treeItemRoot.getChildren().size() > 0))
                setNumDocumentosTree(treeItemRoot.getChildren().size());
            
            // Seteamos árbol con lista
            rootTreeItems.add(treeItemRoot);
            this.setListInstance(rootTreeItems);
        } else {
            // Inicializamos el arbol de documentos cuando no este disponible el servicio de documentos
            rootChildren = new ArrayList<TreeItem>();
            
            // Nodo raíz por default
            treeItemRoot = new TreeItem("folder", "Documentos");
            treeItemRoot.setChildren(rootChildren);
            
            rootTreeItems = new ArrayList<TreeItem>();
            rootTreeItems.add(treeItemRoot);
            
            // Seteamos árbol con lista
            setListInstance(rootTreeItems);
        }
    }
    
    public String obtenerWSDL_DOCUMENTO() {
        return WSDL_DOCUMENTO;
    }
    
    public void setWsdlLocation(String wsdlLocation) {
        this.wsdlLocation = wsdlLocation;
    }

    public String getWsdlLocation() {
        return wsdlLocation;
    }
    
    public String obtenerURL_DOCPOP() {
        return URL_DOCPOP;
    }
    
    public void setUrlDocpop(String urlDocpop) {
        this.urlDocpop = urlDocpop;
    }

    public String getUrlDocpop() {
        return urlDocpop;
    }

    public void setTextoFichaDoc(String textoFichaDoc) {
        this.textoFichaDoc = textoFichaDoc;
    }

    public String getTextoFichaDoc() {
        return textoFichaDoc;
    }

    public void setNombreDoc(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }

    public String getNombreDoc() {
        return nombreDoc;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setIdAdjunto(oracle.jbo.domain.Number idAdjunto) {
        this.idAdjunto = idAdjunto;
    }

    public oracle.jbo.domain.Number getIdAdjunto() {
        return idAdjunto;
    }

    public void setEtapaDoc(String etapaDoc) {
        this.etapaDoc = etapaDoc;
    }

    public String getEtapaDoc() {
        return etapaDoc;
    }

    public void setCodigoDoc(String codigoDoc) {
        this.codigoDoc = codigoDoc;
    }

    public String getCodigoDoc() {
        return codigoDoc;
    }
    
    public void setIdExterno(oracle.jbo.domain.Number idExterno) {
        this.idExterno = idExterno;
    }

    public oracle.jbo.domain.Number getIdExterno() {
        return idExterno;
    }
    
    public void setIdOperacion(oracle.jbo.domain.Number idOperacion) {
        this.idOperacion = idOperacion;
    }

    public oracle.jbo.domain.Number getIdOperacion() {
        return idOperacion;
    }
    
    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getCreadoPor() {
        return creadoPor;
    }
    
    public void setIdTipoDoc(oracle.jbo.domain.Number idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public oracle.jbo.domain.Number getIdTipoDoc() {
        return idTipoDoc;
    }
    
    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTareaDoc(String tareaDoc) {
        this.tareaDoc = tareaDoc;
    }

    public String getTareaDoc() {
        return tareaDoc;
    }
    
    public void setFechaDoc(String fechaDoc) {
        this.fechaDoc = fechaDoc;
    }

    public String getFechaDoc() {
        return fechaDoc;
    }
    
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }
    
    public void setNombreCreadoPor(String nombreCreadoPor) {
        this.nombreCreadoPor = nombreCreadoPor;
    }

    public String getNombreCreadoPor() {
        return nombreCreadoPor;
    }

    public void setNombreModificadoPor(String nombreModificadoPor) {
        this.nombreModificadoPor = nombreModificadoPor;
    }

    public String getNombreModificadoPor() {
        return nombreModificadoPor;
    }
    
    public void setEsModificable(Boolean esModificable) {
        this.esModificable = esModificable;
    }

    public Boolean getEsModificable() {
        return esModificable;
    }

    public void setEsBorrable(Boolean esBorrable) {
        this.esBorrable = esBorrable;
    }

    public Boolean getEsBorrable() {
        return esBorrable;
    }
    
    public void setIdTarea(oracle.jbo.domain.Number idTarea) {
        this.idTarea = idTarea;
    }

    public oracle.jbo.domain.Number getIdTarea() {
        return idTarea;
    }
    
    public void setEsJustificacionDoc(Boolean esJustificacionDoc) {
        this.esJustificacionDoc = esJustificacionDoc;
    }

    public Boolean getEsJustificacionDoc() {
        return esJustificacionDoc;
    }

    public void setResumenDoc(String resumenDoc) {
        this.resumenDoc = resumenDoc;
    }

    public String getResumenDoc() {
        return resumenDoc;
    }
    
    public void setOpAdjuntarModificar(String opAdjuntarModificar) {
        this.opAdjuntarModificar = opAdjuntarModificar;
    }

    public String getOpAdjuntarModificar() {
        return opAdjuntarModificar;
    }
    
    public void setAccionDeTablaArbol(String accionDeTablaArbol) {
        this.accionDeTablaArbol = accionDeTablaArbol;
    }

    public String getAccionDeTablaArbol() {
        return accionDeTablaArbol;
    } 
    
    public void setEsIdOperacionValido(Boolean esIdOperacionValido) {
        this.esIdOperacionValido = esIdOperacionValido;
    }

    public Boolean getEsIdOperacionValido() {
        return esIdOperacionValido;
    } 
    
    public void setNumDocumentosTree(Integer numDocumentosTree) {
        this.numDocumentosTree = numDocumentosTree;
    }

    public Integer getNumDocumentosTree() {
        return numDocumentosTree;
    }
    
    public void setEsDocumentoSubidoEnGestorOp(Boolean esDocumentoSubidoEnGestorOp) {
        this.esDocumentoSubidoEnGestorOp = esDocumentoSubidoEnGestorOp;
    }

    public Boolean getEsDocumentoSubidoEnGestorOp() {
        return esDocumentoSubidoEnGestorOp;
    }
    
    public void setEsGestorOp(Boolean esGestorOp) {
        this.esGestorOp = esGestorOp;
    }

    public Boolean getEsGestorOp() {
        return esGestorOp;
    }
    
    public void setIsJustificacion(Boolean isJustificacion) {
        this.isJustificacion = isJustificacion;
    }

    public Boolean getIsJustificacion() {
        return isJustificacion;
    }

    public void setRolesEnviarDocumento(Boolean rolesEnviarDocumento) {
        this.rolesEnviarDocumento = rolesEnviarDocumento;
    }

    public Boolean getRolesEnviarDocumento() {
        return rolesEnviarDocumento;
    }

    public void setDocumentoAdjunto(UploadedFile documentoAdjunto) {
        this.documentoAdjunto = documentoAdjunto;
        
        if(documentoAdjunto != null){
            
            setNombreArchivo(getDocumentoAdjunto().getFilename()); // Valor por default para el nombre del archivo
            
            try {
                this.documentoInputStream = documentoAdjunto.getInputStream();
            } catch (IOException e) {
                logger.log(ADFLogger.ERROR, e.getMessage());
            }
        }
    }

    public UploadedFile getDocumentoAdjunto() {
        return documentoAdjunto;
    }

    public void setDocumentoInputStream(InputStream documentoInputStream) {
        this.documentoInputStream = documentoInputStream;
    }

    public InputStream getDocumentoInputStream() {
        return documentoInputStream;
    }
    
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setCodigoArchivo(String codigoArchivo) {
        this.codigoArchivo = codigoArchivo;
    }

    public String getCodigoArchivo() {
        return codigoArchivo;
    }

    public void setFechaArchivo(Date fechaArchivo) {
        this.fechaArchivo = fechaArchivo;
    }

    public Date getFechaArchivo() {
        return fechaArchivo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }
    
    public void setIdDocumento(oracle.jbo.domain.Number idDocumento) {
        this.idDocumento = idDocumento;
    }

    public oracle.jbo.domain.Number getIdDocumento() {
        return idDocumento;
    }

    public void setResumenArchivo(String resumenArchivo) {
        this.resumenArchivo = resumenArchivo;
    }

    public String getResumenArchivo() {
        return resumenArchivo;
    }
    
    public void setBtnActualizarDocs(Boolean btnActualizarDocs) {
        this.btnActualizarDocs = btnActualizarDocs;
    }

    public Boolean getBtnActualizarDocs() {
        return btnActualizarDocs;
    }
    
    public TreeModel getTreeModel() throws IntrospectionException {
        if (treeModel == null) {
            treeModel = new ChildPropertyTreeModel(instance, "children");
        }
      
        return treeModel;
    }
    
    public void setListInstance(List instance) {
        this.instance = instance;
        treeModel = null;
    }
    
    public void setEstadoDocumento(String estadoDocumento) {
        this.estadoDocumento = estadoDocumento;
    }

    public String getEstadoDocumento() {
        return estadoDocumento;
    }
    
    public void cleanDocumentData() {
        
        // Limpia variables usadas en el formulario con detalle del Documento/tree
        setIdDocumento(null);
        setIdTipoDoc(null);
        setTipoDoc(null);
        setCodigoDoc(null);
        setIdExterno(null);
        setIdOperacion(null);
        setNombreDoc(null);
        setFileName(null);
        setMimeType(null);
        setIdAdjunto(null);
        setEsJustificacionDoc(null);
        setResumenDoc(null);
        setEtapaDoc(null);
        setTareaDoc(null);
        setFechaDoc(null);
        setFechaRegistro(null);
        setCreadoPor(null);
        setModificadoPor(null);
        setNombreCreadoPor(null);
        setNombreModificadoPor(null);
        setEsModificable(null);
        setEsBorrable(null);
        setIdTarea(null);
        setEsDocumentoSubidoEnGestorOp(false);
    }
    
    public void setDocumentData(TreeItem selectedItem){
        
        Boolean esTipoFile = Boolean.FALSE;
        
        if((selectedItem != null) && (selectedItem.getTipoElemento() != null) 
           && (selectedItem.getTipoElemento().equalsIgnoreCase("file"))){
            
            esTipoFile = Boolean.TRUE;
        }
        
        setIdDocumento(esTipoFile ? selectedItem.getIdDocumento() : null);
        setIdTipoDoc(esTipoFile ? selectedItem.getIdTipoDoc() : null);
        setTipoDoc(esTipoFile ? selectedItem.getTipoDoc() : null);
        setCodigoDoc(esTipoFile ? selectedItem.getCodigoDoc() : null);
        setIdExterno(esTipoFile ? selectedItem.getIdExterno() : null);
        setIdOperacion(esTipoFile ? selectedItem.getIdOperacion() : null);
        setNombreDoc(esTipoFile ? selectedItem.getNombreDoc() : null);
        setFileName(esTipoFile ? selectedItem.getFileName() : null);
        setMimeType(esTipoFile ? selectedItem.getMimeType() : null);
        setIdAdjunto(esTipoFile ? selectedItem.getIdAdjunto() : null);
        setEsJustificacionDoc(esTipoFile ? selectedItem.getEsJustificacion() : null);
        setResumenDoc(esTipoFile ? selectedItem.getResumenDoc() : null);
        setFechaDoc(esTipoFile ? selectedItem.getFechaDoc() : null);
        setFechaRegistro(esTipoFile ? selectedItem.getFechaRegistro() : null);
        setEtapaDoc(esTipoFile ? selectedItem.getEtapaDoc() : null);
        setTareaDoc(esTipoFile ? selectedItem.getTareaDoc() : null);
        setCreadoPor(null); // login del usuario que crea. TODO dato no regresado por el servicio
        setModificadoPor(null); // login del usuario que modifica. TODO dato no regresado por el servicio
        setNombreCreadoPor(esTipoFile ? selectedItem.getCreadoPor() : null); 
        setNombreModificadoPor(esTipoFile ? selectedItem.getModificadoPor() : null); 
        setEsModificable(esTipoFile ? selectedItem.getEsModificable() : null);
        setEsBorrable(esTipoFile ? selectedItem.getEsBorrable() : null);
        setIdTarea(esTipoFile ? selectedItem.getIdTarea() : null);
        
        // Para soportar la funcionalidad de editar los documentos subidos desde el Gestor de Operaciones, 
        // la bandera esModificable se hace true cuando el IdDocumento NO sea null
        if(getIdDocumento() != null) {
            setEsModificable(true);
            setEsDocumentoSubidoEnGestorOp(true);
        }
    }
    
    public void setDocumentData(DocumentoVORowImpl documentoRow){
        Row adjuntoRow = null;
        SimpleDateFormat simpleDateFormat = null;
        HashMap<String, String> etapaTarea = null;
        
        if(documentoRow != null) {
            
            setIdDocumento(new oracle.jbo.domain.Number(documentoRow.getIdDocumento()));            
            setCodigoDoc(documentoRow.getCodigoDocumento());
            setIdTipoDoc(new oracle.jbo.domain.Number(documentoRow.getIdTipoDocumento().longValue()));
            setTipoDoc(this.getTipoDocumentoById(documentoRow.getIdTipoDocumento()));
            
            if(documentoRow.getIdTareaBpm() != null) {
                etapaTarea = this.getEtapaTareaById(documentoRow.getIdTareaBpm());
                setEtapaDoc(etapaTarea.get("etapa"));
                setTareaDoc(etapaTarea.get("tarea"));
            }
            else{
                setEtapaDoc(null);
                setTareaDoc(null);
            }
            
            if(documentoRow.getFechaDocumento() != null){
                simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                setFechaDoc(simpleDateFormat.format(documentoRow.getFechaDocumento()));
            }
            else{
                setFechaDoc(null);
            }
            
            setCreadoPor(documentoRow.getLoginUsuarioCrea());
            setModificadoPor(documentoRow.getLoginUsuarioModifica());
            setNombreCreadoPor(documentoRow.getNombreUsuarioCrea());
            setNombreModificadoPor(documentoRow.getNombreUsuarioModifica());
            setResumenDoc(documentoRow.getComentario());
            
            adjuntoRow = documentoRow.getAdjuntoVO().getRowAtRangeIndex(0);
            if(adjuntoRow != null) {
                setNombreDoc((String)adjuntoRow.getAttribute("Filename"));
                
                if((String)adjuntoRow.getAttribute("IdOnbase") == null) {
                    setEsBorrable(Boolean.TRUE); // Sólo se pueden borrar los documentos sin IdOnbase
                    setEsModificable(Boolean.TRUE); // TODO checar si es misma lógica para modificable
                }
                else{
                    setEsBorrable(Boolean.FALSE);
                    setEsModificable(Boolean.FALSE);
                }
                
                setEsJustificacionDoc(Boolean.FALSE);
            }
            else{
                setNombreDoc("Justificación");
                setEsBorrable(Boolean.TRUE);
                setEsModificable(Boolean.TRUE);
                setEsJustificacionDoc(Boolean.TRUE);
            }
        }
    }
    
    public void setArchivoData(){
        SimpleDateFormat simpleDateFormat = null;
        
        setNombreArchivo(this.getNombreDoc());
        setCodigoArchivo(this.getCodigoDoc());
        
        if(this.getIdTipoDoc() != null)
            setIdTipo(this.getIdTipoDoc().intValue());
                    
        if(this.getFechaDoc() != null){
            
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            
            try{
                setFechaArchivo(simpleDateFormat.parse(this.getFechaDoc()));
            } catch (java.text.ParseException e) { logger.log(ADFLogger.ERROR, e.getMessage()); }
        }
        else{
            setFechaArchivo(null);
        }
        
        setResumenArchivo(this.getResumenDoc());
    }
    
    public void setArchivoData(DocumentoVORowImpl documentoRow){
        Row adjuntoRow = null;
        // TODO eliminar este método
        
        if(documentoRow != null){
            
            adjuntoRow = documentoRow.getAdjuntoVO().getRowAtRangeIndex(0);
            
            setNombreArchivo((String)adjuntoRow.getAttribute("Filename"));
            setCodigoArchivo(documentoRow.getCodigoDocumento());
            setIdTipo(documentoRow.getIdTipoDocumento());
                        
            if(documentoRow.getFechaDocumento() != null){
                
                setFechaArchivo(new Date(documentoRow.getFechaDocumento().getTime()));
            }
            else{
                setFechaArchivo(null);
            }
            
            setResumenArchivo(documentoRow.getComentario());
        }
    }
    
    private String getTipoDocumentoById(Integer idTipo){
        String tipoDocumento = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();                   
        OperationBinding opTipoDocumentoById = bindings.getOperationBinding("getTipoDocumentoById");
        
        opTipoDocumentoById.getParamsMap().put("idTipo", idTipo);
        opTipoDocumentoById.execute();
        if(!opTipoDocumentoById.getErrors().isEmpty()){
            // Manejo de errores
        }
        else{
            tipoDocumento = (String)opTipoDocumentoById.getResult();
        }
        
        return tipoDocumento;
    }
    
    private HashMap<String, String> getEtapaTareaById(Integer idTareaBpm){
        HashMap<String, String> etapaTarea = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();                   
        OperationBinding opEtapaTareaById = bindings.getOperationBinding("getEtapaTareaByIdTareaBpm");
        
        opEtapaTareaById.getParamsMap().put("idTareaBpm", idTareaBpm);
        opEtapaTareaById.execute();
        if(!opEtapaTareaById.getErrors().isEmpty()){
            // Manejo de errores
        }
        else{
            etapaTarea = (HashMap<String, String>)opEtapaTareaById.getResult();
        }
        
        return etapaTarea;
    }
  
    private Documento12BndQSService initDocumentoService() {
        
        return IWsdlLocation.Service.getInstance(Documento12BndQSService.class, this.getWsdlLocation());
    }

    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (resourceBundle != null) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }

    public Boolean getEsConsultarPorNumeroSerieDocumento() {
        return esConsultarPorNumeroSerieDocumento;
    }

    public void setEsConsultarPorNumeroSerieDocumento(Boolean esConsultarPorNumeroSerieDocumento) {
        this.esConsultarPorNumeroSerieDocumento = esConsultarPorNumeroSerieDocumento;
    }

    public void asignarPermisos() {
        logger.warning("Inside asignarPermisos.");
        
        List<String> listRolesAccion = new ArrayList<String>();
        List<String> listRolesUsuario = new ArrayList<String>();
        
        listRolesAccion = obtenerRoles();
        listRolesUsuario = obtenerGruposUsuario();
        
        setRolesEnviarDocumento(validaRolesAccionEnviarDocumento(listRolesUsuario, listRolesAccion));
        setBtnActualizarDocs(verificarEstadoDocumento());
    }

    public Boolean validaRolesAccionEnviarDocumento(List<String> permisosPorUsuario,
                                                                 List<String> rolesConPermisos) {
        logger.warning("Inside validaRolesAccionEnviarDocumento.");
        Boolean respuesta = Boolean.FALSE;
        
        if (permisosPorUsuario != null && rolesConPermisos != null) {
            if (permisosPorUsuario.size() > 0 && rolesConPermisos.size() > 0) {
                for (String rolUsuario : permisosPorUsuario) {
                    for (String rolConPermiso : rolesConPermisos) {
                        if ((rolUsuario.equalsIgnoreCase(rolConPermiso)) ||
                            (rolUsuario.startsWith("FENIX_REPRESENTANTE") && rolConPermiso.startsWith("FENIX_REPRESENTANTE"))) {
                            logger.warning("Rol Obtenido del usuario: " + rolUsuario);
                            logger.warning("Rol Obtenido de la lista de roles con permiso: " + rolConPermiso);
                            logger.warning("Se encuentra una coincidencia, se habilita bandera para mostrar el boton Enviar documento.");
                            respuesta = Boolean.TRUE;
                            break;
                        }
                    }
                }
                if (!respuesta) {
                    logger.warning("El usuario no tiene un rol con permisos para Enviar documento a OnBase, no se muestra el boton" +
                        "Enviar documento.");
                }
            } else {
                logger.warning("Alguna de las listas es vacia, no tiene elementos, no se ejecuta la comparacion de roles " +
                    "permitidos para Enviar documento a OnBase.");
            }   
        } else {
            logger.warning("Lista de roles null.");
        }

        logger.warning("Valor a retornar respuesta: " + respuesta);
        
        return respuesta;
    }
    
    public List<String> obtenerRoles() {
        logger.warning("Inside obtenerRoles.");

        List<String> listRoles = new ArrayList<String>();

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("obtenerRoles");
        operationBinding.getParamsMap().put("idAccion", ENVIAR_DOCUMENTO_ONBASE);
        operationBinding.execute();

        if (!operationBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            listRoles = (ArrayList <String>) operationBinding.getResult();
        }

        return listRoles;
    }
    
    public List<String> obtenerGruposUsuario(){
        logger.warning("Inside obtenerGruposUsuario");
        
        List<String> listGruposUsuario = new ArrayList<String>();
        Object resultado = null;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("getGruposUsuario");
        String usuario = ADFContext.getCurrent().getSecurityContext().getUserName();
        operationBinding.getParamsMap().put("pUsuario", usuario);
        operationBinding.execute();
        
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error en getGruposUsuario.");
        } else {
            resultado = operationBinding.getResult(); 
            listGruposUsuario = (ArrayList <String>) resultado;
            
            logger.warning("Roles Usuario:");
            if (listGruposUsuario != null) {
                if (listGruposUsuario.size() > 0) {
                    for (String rolUsuario : listGruposUsuario) {
                        logger.warning(rolUsuario);
                    }    
                } else {
                    logger.warning("Roles de usuario es vacio.");
                }
            } else {
                logger.warning("Roles de usuario es null.");
            }   
        }
        
        return listGruposUsuario;
    }
    
    public Boolean verificarEstadoDocumento() {
        logger.warning("Inside verificarEstadoDocumento.");
        
        Boolean result = Boolean.FALSE;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("verificarEstadoDocumento");
        operationBinding.execute();

        if (!operationBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            result = (Boolean) operationBinding.getResult();
        }
        
        logger.warning("verificarEstadoDocumento: " + result);
        
        return result;
    }
    
    public void inicializarArbolDocumentosOnly() {
        logger.warning("Inside inicializarArbolDocumentosOnly.");
        
        oracle.jbo.domain.Number idTareaBpm = null;
        
        if((JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null) 
           && (!JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString().equalsIgnoreCase("-1")) 
           && (JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString().trim().length() > 0)) {
            
            //validar si "pConsultarPorNumeroSerieDocumento" viene "TRUE".
            if(Boolean.TRUE ==(Boolean)JSFUtils.resolveExpression("#{pageFlowScope.pConsultarPorNumeroSerieDocumento}")){
                setEsConsultarPorNumeroSerieDocumento(Boolean.TRUE);
            }
            // Id de operación válido
            this.generarArbolDocumentos();
            setEsIdOperacionValido(true);
        }
        else{
            // Mostramos un tree vacío, solo con nodo padre si el id de operación es null o -1 (se usa para probar)
            ArrayList<TreeItem> rootTreeItems = new ArrayList<TreeItem>();
            TreeItem treeItem1 = new TreeItem("folder", "Documentos");
            rootTreeItems.add(treeItem1);    
            this.setListInstance(rootTreeItems);
        }
        
        if((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") == null) 
           || (JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString().trim().length() == 0)) {

            try {
                idTareaBpm =
                    ((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null) &&
                     (JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString().trim().length() > 0)) ?
                    new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}")) : null;
            } catch (SQLException e) {
                logger.warning("Casting error idTarea.");
            }
            
            Integer idTarea = new Integer (idTareaBpm.intValue());
            
            if (idTareaBpm.compareTo(GESTOR_OPERACIONES) == 0 ||
                idTareaBpm.compareTo(GESTOR_CLIENTES) == 0 ||
                idTareaBpm.compareTo(GESTOR_DESEMBOLSOS) == 0) {
                logger.warning("idTarea: " + idTarea);
                
                // Si NO se manda un IdTareaBpm entonces nos encontramos en el Gestor de Operaciones
                setEsGestorOp(true);   
            }
        }
        
        
        // Reset a variable que refresca el Gestor de Documentos desde el proceso de LAFT - Recopilar Información
        ADFContext.getCurrent().getSessionScope().put("refreshGestorDoc", false);
        
        logger.log(ADFLogger.WARNING, "pIdOperacion: " + JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
        logger.log(ADFLogger.WARNING, "pIdTareaBpm: " + JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}"));
        logger.log(ADFLogger.WARNING, "pNumeroSerieDocumento: " + JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}"));
        logger.log(ADFLogger.WARNING, "pConsultarPorNumeroSerieDocumento: " + JSFUtils.resolveExpression("#{pageFlowScope.pConsultarPorNumeroSerieDocumento}"));
    }
    
    public void inicializarArbolDocumentosPorInstanciaProceso() {
        logger.warning("Dentro de inicializarArbolDocumentosPorInstanciaProceso");
        
        oracle.jbo.domain.Number idTareaBpm = null;
        
        setTextoFichaDoc("Documento agregado a la tarea");
        setAccionDeTablaArbol("tabla"); // Por default inicia con un row seleccionado en tabla
        
        if((JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null) 
           && (!JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString().equalsIgnoreCase("-1")) 
           && (JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString().trim().length() > 0)) {
            
            logger.warning("Se recupera idOperacion");
            //validar si "pConsultarPorNumeroSerieDocumento" viene "TRUE".
            if(Boolean.TRUE ==(Boolean)JSFUtils.resolveExpression("#{pageFlowScope.pConsultarPorNumeroSerieDocumento}")){
                setEsConsultarPorNumeroSerieDocumento(Boolean.TRUE);
            }
            // Id de operación válido
            this.generarArbolDocumentosPorInstanciaProceso();
            setEsIdOperacionValido(true);
        }else{
            logger.warning("pIdOperacion is null");
            // Mostramos un tree vacío, solo con nodo padre si el id de operación es null o -1 (se usa para probar)
            ArrayList<TreeItem> rootTreeItems = new ArrayList<TreeItem>();
            TreeItem treeItem1 = new TreeItem("folder", "Documentos");
            rootTreeItems.add(treeItem1);    
            this.setListInstance(rootTreeItems);
        }
        
        if((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") == null) 
           || (JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString().trim().length() == 0)) {
            
            try {
                idTareaBpm =
                    ((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null) &&
                     (JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString().trim().length() > 0)) ?
                    new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}")) : null;
            } catch (SQLException e) {
                logger.warning("Casting error idTarea.");
            }
            
            Integer idTarea = new Integer (idTareaBpm.intValue());
            
            if (idTareaBpm.compareTo(GESTOR_OPERACIONES) == 0 ||
                idTareaBpm.compareTo(GESTOR_CLIENTES) == 0 ||
                idTareaBpm.compareTo(GESTOR_DESEMBOLSOS) == 0) {
                logger.warning("idTarea: " + idTarea);
                
                // Si NO se manda un IdTareaBpm entonces nos encontramos en el Gestor de Operaciones
                setEsGestorOp(true);   
            }
        }
        
        // Reset a variable que refresca el Gestor de Documentos desde el proceso de LAFT - Recopilar Información
        ADFContext.getCurrent().getSessionScope().put("refreshGestorDoc", false);
        
        //metodo para evaluar el parametro de entrada #{pageFlowScope.pState}
        evaluarParametroPStateBpm();
            
        logger.warning("pIdOperacion: " + JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
        logger.warning("pIdTareaBpm: " + JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}"));
        logger.warning("pNumeroSerieDocumento: " + JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}"));
        logger.warning("pConsultarPorNumeroSerieDocumento: " + JSFUtils.resolveExpression("#{pageFlowScope.pConsultarPorNumeroSerieDocumento}"));
        logger.warning("pInstanciaProceso: "+JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}"));
        
        logger.warning("Fuera de inicializarArbolDocumentosPorInstanciaProceso");
    }
    
    private void generarArbolDocumentosPorInstanciaProceso(){
        
        logger.warning("Dentro de generarArbolDocumentosPorInstanciaProceso");
        
        ArrayList<TreeItem> rootTreeItems = null;
        ArrayList<TreeItem> rootChildren = null;
        ArrayList<TreeItem> etapaChildren = null;
        ArrayList<TreeItem> tipoDocumentoChildren = null;
        TreeItem treeItemRoot = null;
        TreeItem treeItemEtapa = null;
        TreeItem treeItemTipoDocumento = null;
        TreeItem treeItemDocumento = null;
        Documento12BndQSService documento12BndQSService = null;
        DocumentoPT documentoPT = null;
        ConsultaDocumentosRequestType request = null;
        ConsultaDocumentosResponseType arbolDocumentos = null;
        SuccessType result = null;
        Error error = null;
        oracle.jbo.domain.Number idOperacion = null;
        oracle.jbo.domain.Number idTareaBpm = null;
        String errorCode = null;
        String errorDescription = null;
        List<Etapa> etapas = null;
        SimpleDateFormat simpleDateFormat = null;
        //variable numero de serie del documento
        oracle.jbo.domain.Number  numeroSerieDocumento = null;
        //variable para almacenar instancia del proceso
        String instanciaProceso = null;
        
        // Inicialización de listas para nodo raíz
        rootTreeItems = new ArrayList<TreeItem>();
        
        // Nodo raíz por default
        treeItemRoot = new TreeItem("folder", "Documentos");
        
        // Cargamos árbol de documentos desde el servicio
        boolean servicioDisponible = Boolean.FALSE;
        try {
            documento12BndQSService = this.initDocumentoService();
            documentoPT = documento12BndQSService.getDocumento12BndQSPort();

            servicioDisponible = Boolean.TRUE;
        } catch (Exception e) {
            logger.warning("El servicio de documentos no se encuentra disponible.", e);
            JSFUtils.addFacesErrorMessage(getStringFromBundle("SERVICIO_DOCUMENTOS_NO_DISPONIBLE"));
        }

        if (servicioDisponible) {
            request = new ConsultaDocumentosRequestType();
            
            try {
                idOperacion = new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
                
                if((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null) 
                   && (JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString().trim().length() > 0))
                    idTareaBpm = new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}"));
                //validacion para revisar si viene el parametro del taskFlow 'pNumeroSerieDocumento' viene 'null'
                if((JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}") != null) 
                   && (JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}").toString().trim().length() > 0))
                    numeroSerieDocumento = new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}"));
                //recuperar instancia del proceso
                if(null != JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}")){
                    instanciaProceso = JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}").toString();
                    logger.warning("instanciaProceso :"+instanciaProceso);
                }else{
                    logger.warning("pInstanciaProceso es null");
                }
            } catch (SQLException e) {
                logger.log(ADFLogger.ERROR, e.getMessage());
            }
            
            request.setIdOperacion(idOperacion.longValue());
            request.setIdTarea((idTareaBpm != null ? idTareaBpm.intValue(): null));
            //agrega al request la instancia del proceso
            request.setInstanciaProceso(instanciaProceso);
            //Agrega al request "numeroSerieDocumento" si la bandera "esConsultarPorNumeroSerieDocumento" viene "TRUE"
            if(Boolean.TRUE == getEsConsultarPorNumeroSerieDocumento()){
                request.setIdFlujoNegocio((numeroSerieDocumento != null ? numeroSerieDocumento.longValue() : null));
            }
            Date horaInicio = ModelUtils.logStartWS(logger, request,"Consultar Documentos");
            try{
                arbolDocumentos = documentoPT.consultarDocumentos(request); // bloque try Por si se cae el servicio
                ModelUtils.logEndWS(logger, arbolDocumentos,"Consultar Documentos", horaInicio);
            } catch (Exception e) { 
                logger.log(ADFLogger.ERROR, e.getMessage()); 
            }
               
            if(arbolDocumentos != null) {
                
                if(arbolDocumentos.getResultado() != null &&
                   arbolDocumentos.getResultado().getResult() != null){
                    
                    result = arbolDocumentos.getResultado().getResult();
                    error = arbolDocumentos.getResultado().getError();
                    errorCode = (error.getErrorCode() == null) ? "" : error.getErrorCode();
                    
                    if((result.value().equalsIgnoreCase("OK")) && (errorCode.trim().length() == 0)){
                        
                        // Cada Etapa es una rama del tipo folder en el árbol
                        etapas = arbolDocumentos.getDocumentos().getEtapa();
                        rootChildren = new ArrayList<TreeItem>();
                        for(Etapa etapa:etapas){
                    
                            treeItemEtapa = new TreeItem("folder", etapa.getNombreEtapa());
                            etapaChildren = new ArrayList<TreeItem>();
                            
                            // Cada Etapa tiene uno o más Tipos de documento
                            for(TipoDcoumento tipoDocumento:etapa.getTipoDocumento()){
                                
                                treeItemTipoDocumento = new TreeItem("folder", tipoDocumento.getNombreTipoDocumento());
                                tipoDocumentoChildren = new ArrayList<TreeItem>();
                                
                                // Cada Tipo de documento tiene uno o más Documentos
                                for(Documento documento:tipoDocumento.getDocumento()){
                                    
                                    simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                    // Se están regresando ceros en lugar de null para los ids, por lo tanto se valida también ese valor
                                    // Si el filename viene empty entonces le asignamos el valor del nombre 
                                    treeItemDocumento = new TreeItem("file", 
                                                    ((documento.getIdDocumento() != null) && (documento.getIdDocumento().intValue() != 0)) ? 
                                                                new oracle.jbo.domain.Number(documento.getIdDocumento()) : null,
                                                    documento.getNombre(), 
                                                    ((documento.getFilename() != null) ? documento.getFilename() : documento.getNombre()), 
                                                    documento.getMimeType(),
                                                    ((documento.getIdAdjunto() != null) && (documento.getIdAdjunto().intValue() != 0)) ?
                                                                new oracle.jbo.domain.Number(documento.getIdAdjunto()) : null,  
                                                    documento.isEsJustificacion(),         
                                                    documento.getEtapa(), 
                                                    documento.getCodigoDocumento(), 
                                                    ((documento.getIdExterno() != null) && (documento.getIdExterno().intValue() != 0)) ? 
                                                                new oracle.jbo.domain.Number(documento.getIdExterno()) : null,
                                                    ((documento.getIdOperacion() != null) && (documento.getIdOperacion().intValue() != 0)) ?
                                                                new oracle.jbo.domain.Number(documento.getIdOperacion()) : null,
                                                    documento.getUsuarioAgrega(), 
                                                    ((documento.getIdTipoDocumento() != null) && (documento.getIdTipoDocumento().intValue() != 0)) ?
                                                                new oracle.jbo.domain.Number(documento.getIdTipoDocumento()) : null, 
                                                    documento.getNombreTipoDocumento(), documento.getTarea(),
                                                    documento.getFechaDocumento() != null ? 
                                                                simpleDateFormat.format(documento.getFechaDocumento().toGregorianCalendar().getTime()) : null,
                                                    documento.getFechaRegistro() != null ?
                                                                simpleDateFormat.format(documento.getFechaRegistro().toGregorianCalendar().getTime()): null,        
                                                    documento.getUsuarioUltimaActualizacion(),
                                                    documento.getComentario(), documento.isPuedeModificar(), documento.isPuedeBorrar(),
                                                    ((documento.getIdtarea() != null) && (documento.getIdtarea().intValue() != 0)) ? 
                                                                new oracle.jbo.domain.Number(documento.getIdtarea()) : null);
                                    
                                    tipoDocumentoChildren.add(treeItemDocumento);
                                }
                                
                                treeItemTipoDocumento.setChildren(tipoDocumentoChildren);
                                etapaChildren.add(treeItemTipoDocumento);
                            }
                            
                            treeItemEtapa.setChildren(etapaChildren); 
                            rootChildren.add(treeItemEtapa);
                        }
                        
                        treeItemRoot.setChildren(rootChildren);
                    }
                    else{
                        // Error al ejecutar el servicio
                        errorDescription = error.getErrorDescription();
                        JSFUtils.addFacesErrorMessage("Código de error: " + errorCode + ". Descripción: " + errorDescription);
                        logger.log(ADFLogger.ERROR, errorDescription);
                    }    
                }else{
                    logger.severe("Error, el atributo Resultado de la consulta de WS de Documentos para la generacion del arbol de documentos es NULL");
                }
            }
            
            // Asignamos valor a variable que guarda el número de Documentos del tree
            if((treeItemRoot.getChildren() != null) && (treeItemRoot.getChildren().size() > 0))
                setNumDocumentosTree(treeItemRoot.getChildren().size());
            
            // Seteamos árbol con lista
            rootTreeItems.add(treeItemRoot);
            this.setListInstance(rootTreeItems);
        } else {
            // Inicializamos el arbol de documentos cuando no este disponible el servicio de documentos
            rootChildren = new ArrayList<TreeItem>();
            
            // Nodo raíz por default
            treeItemRoot = new TreeItem("folder", "Documentos");
            treeItemRoot.setChildren(rootChildren);
            
            rootTreeItems = new ArrayList<TreeItem>();
            rootTreeItems.add(treeItemRoot);
            
            // Seteamos árbol con lista
            setListInstance(rootTreeItems);
        }
        
        logger.warning("Fuera de generarArbolDocumentosPorInstanciaProceso");
    }


    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }
}
