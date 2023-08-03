package org.bcie.fenix.view.documentosclientes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.UnsupportedEncodingException;

import java.nio.charset.Charset;

import java.sql.SQLException;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTree;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.server.ViewObjectImpl;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;

import org.bcie.documentomo.CargarDocumentoResponseType;
import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.vo.DocumentoVORowImpl;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.view.documentos.TreeItem;
import java.net.URLEncoder;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.context.Agent;
import org.apache.myfaces.trinidad.context.RequestContext;

import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.apache.myfaces.trinidad.model.TreeModel;

import org.bcie.fenix.view.documentos.DocumentosBean;

public class DocumentosClientesActionsBean {
    
    private static ADFLogger logger = null;
    private RichPopup popupEliminar;
    private RichPopup popupAgregar;
    private RichPopup popupReemplazar;
    private RichOutputText otInitForm;
    private RichTable tableDocumentosUIC;
    private RichTree treeArbolUIC;
    private RichPopup popupEnviarDocumento;
    
    private String searchCriteria = null;
    private RichPanelGroupLayout formDocumentosUIC;

    public DocumentosClientesActionsBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void treeRowSelectionListener(SelectionEvent selectionEvent) {
        // Obtenemos valor seleccionado en tree y seteamos data para mostrar en formulario
        logger.log(ADFLogger.WARNING, "Inside treeRowSelectionListener: " + selectionEvent.getComponent().getId());
        DocumentosClientesBean documentosClientesBean = null;
        TreeItem rootTreeItem = null;
        TreeItem selectedItem = null;
        RichTree tree = (RichTree)selectionEvent.getSource(); // get the tree component from the event 
        RowKeySet rowKeySet = selectionEvent.getAddedSet(); //get selected nodes
        Iterator rksIterator = rowKeySet.iterator();
                
        List keyPathList = (List)rksIterator.next();
        CollectionModel collectionModel = (CollectionModel)tree.getValue();
        ArrayList treeArray = (ArrayList)collectionModel.getWrappedData();

        rootTreeItem = (TreeItem)treeArray.get(0);
        selectedItem = getSelectedItemByPath(keyPathList, rootTreeItem);
        
        documentosClientesBean = (DocumentosClientesBean)JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}");
        documentosClientesBean.setDocumentData(selectedItem);
        documentosClientesBean.setAccionDeTablaArbol("arbol");
        documentosClientesBean.setTextoFichaDoc("Ficha del documento");
        
        
        if (tableDocumentosUIC.getSelectedRowKeys() != null) {
            tableDocumentosUIC.getSelectedRowKeys().clear();
        }
        
        documentosClientesBean.setEstadoDocumento(null);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableDocumentosUIC);
    }
    
    public void adjuntarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside adjuntarActionListener: " + actionEvent.getComponent().getId());
        DocumentosClientesBean documentosClientesBean = (DocumentosClientesBean)JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}");
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        oracle.jbo.domain.Date currentDateTime = new oracle.jbo.domain.Date(new java.sql.Timestamp(System.currentTimeMillis()));
        DCIteratorBinding voTiposDocIterator = null;
        
        // Limpiamos valores de idOnbase (se asignan al hacer click en el tree y sino se limpia truena en escenario de justificación) 
        documentosClientesBean.setIdExterno(null);
            
        // Asignamos al campo Fecha (dentro del popup) el valor de sysdate
        documentosClientesBean.setFechaArchivo(currentDateTime.getValue());
        
        // Si se encuentra en el Gestor de Operaciones, pre-seleccionamos el combo Tipo de Documento con valor "General" 
        if(documentosClientesBean.getEsGestorOp()) {
            voTiposDocIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("TiposDocumentoLOVIterator");
            voTiposDocIterator.setCurrentRowWithKeyValue(FenixConstants.TD_GENERAL.toString());
        }
        
        // Mostramos popup
        getPopupAgregar().show(popupHints);
    }
    
    /**
     * Obtiene el elemento seleccionado
     * @param keyPathList Ruta del elemento seleccionado
     * @param rootTreeItem Nodo raíz del tree
     * @return El elemento seleccionado en el tree
     */
    private TreeItem getSelectedItemByPath(List keyPathList, TreeItem rootTreeItem){
        /* Recorremos el tree a partir del root item para obtener el elemento seleccionado 
         * En la lista keyPathList viene la ruta del elemento seleccionado, de la forma {0, 0,.., n},
         * siendo n el índice del elemento. Ej. si se elige el hijo 3 el valor de n es 2. */
        TreeItem selectedItem = rootTreeItem;
        Integer pathListSize = keyPathList.size();
        Integer pathListIndex = 0;
        
        while(pathListSize > 0){   
            pathListSize--; //Para el caso que el elemento seleccionado sea la raíz del tree
            pathListIndex++;
            
            if(pathListSize > 0)
                selectedItem = selectedItem.getChildren().get((Integer)keyPathList.get(pathListIndex));           
        }
        
        return selectedItem;
    }
    
    public void documentosAdjuntadosSelectionListener(SelectionEvent selectionEvent) {
        // Evento al que entra cuando hace click en el row de la tabla
        logger.log(ADFLogger.TRACE, "Inside documentosAdjuntadosSelectionListener");     
        
        // 1) Evento por default (para actualizar filas)
        JSFUtils.resolveMethodExpression("#{bindings.DocumentosAdjuntadosClientesVO.collectionModel.makeCurrent}", 
                                         Object.class, new Class[] { SelectionEvent.class }, 
                                         new Object[] { selectionEvent });

        // 2) Actualizamos valores en formulario
        setDatosDeTablaEnFormulario();
        
        if (treeArbolUIC.getSelectedRowKeys() != null) {
            treeArbolUIC.getSelectedRowKeys().clear();
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(treeArbolUIC);
    }
    
    private void setDatosDeTablaEnFormulario(){
        // Actualizamos valores en formulario
        logger.warning("Dentro de setDatosDeTablaEnFormulario");
        DocumentosClientesBean documentosClientesBean = (DocumentosClientesBean)JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}");
        DocumentoVORowImpl documentoRow = null;
        oracle.jbo.domain.Number idDocumento = null;
        Map<String, String> valoresDocumentoMap = new HashMap<String, String>();
        
        // 1) Obtenemos Id del documento seleccionado
        try {
            if(JSFUtils.resolveExpression("#{bindings.IdDocumento.inputValue}") != null)
                idDocumento = new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{bindings.IdDocumento.inputValue}"));
        } catch (SQLException e) {
            logger.log(ADFLogger.ERROR, e.getMessage());
        }
        logger.warning("idDocumento : "+idDocumento);
        try{
            // 2) Seteamos datos en bean
            documentoRow = getDocumentoRowByIdDocumento(idDocumento);
        }catch(Exception e){
            logger.severe("Error al recuperar row del documento en setDatosDeTablaEnFormulario",e);
        }
        if(documentoRow != null){
            documentosClientesBean.setDocumentData(documentoRow);
            documentosClientesBean.setAccionDeTablaArbol("tabla");
            documentosClientesBean.setTextoFichaDoc("Documento agregado a la tarea");
        }else{
            logger.severe("documentoRow es nulo");
        }
        
        valoresDocumentoMap = obtenerDatosDocumento();

        if (valoresDocumentoMap.size() > 0) {
            documentosClientesBean.setEstadoDocumento(valoresDocumentoMap.get("estadoDocumento"));
        } else {
            documentosClientesBean.setEstadoDocumento(null);
        }

        logger.warning(documentosClientesBean.getEstadoDocumento());
        
        logger.warning("Fuera de setDatosDeTablaEnFormulario");
    }
    
    private DocumentoVORowImpl getDocumentoRowByIdDocumento(oracle.jbo.domain.Number idDocumento){
        /* Obtenemos row de Documento (el cual contiene a DocumentoS y Adjunto) en base al Id. 
         * Lo anterior para usarlo en evento de seleccionar fila en tabla Documentos adjuntados a la tarea y botón Eliminar.
         * Así como para el evento Modificar.*/
        logger.warning("Dentro de getDocumentoRowByIdDocumento, idDocumento : "+idDocumento);
        DocumentoVORowImpl documentoRow = null;
        FenixAMImpl fenixAM = null;
        ViewObjectImpl documentoVO = null;
        ViewCriteria criteriaByIdDocumento = null;
        
        if(idDocumento != null) { // idDocumento es requerido por el ViewCriteria
            // 1) Ejecutamos view criteria para obtener row en base al Id del documento
            fenixAM = (FenixAMImpl) JSFUtils.resolveExpression("#{data.FenixAMDataControl.dataProvider}");
            documentoVO = fenixAM.getDocumentoVO();
            
            criteriaByIdDocumento = documentoVO.getViewCriteriaManager().getViewCriteria("DocumentoVOCriteriaByIdDocumento");
            criteriaByIdDocumento.ensureVariableManager().setVariableValue("varIdDocumento", idDocumento);
            
            documentoVO.applyViewCriteria(criteriaByIdDocumento);
            documentoVO.executeQuery();
            
            // 2) Asignamos valor de retorno
            if(documentoVO.getEstimatedRowCount() > 0){
                documentoRow = (DocumentoVORowImpl)documentoVO.getRowAtRangeIndex(0);
            }else{
                logger.severe("No se encontraron row con el criterio de bsuqueda idDocumento : "+idDocumento);
            }
    
            // 3) Removemos criteria del VO
            documentoVO.getViewCriteriaManager().removeApplyViewCriteriaName("DocumentoVOCriteriaByIdDocumento");
        }
        
        logger.warning("Termina getDocumentoRowByIdDocumento");
        // 4) Retorna valor
        return documentoRow;
    }

    public void descargarDocumento(FacesContext facesContext, OutputStream outputStream) {
        logger.warning("Inf, inicia metodo descargarDocumento");
        
        DCIteratorBinding voDocumentosAdjuntadosIterator = null;
        Row currentRowDocumentosAdjuntados = null;
        InputStream in = null;
        BlobDomain blobDomain = null;
                
        // Descargamos documento de Base de datos
        voDocumentosAdjuntadosIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("DocumentosAdjuntadosClientesVOIterator");
        currentRowDocumentosAdjuntados = voDocumentosAdjuntadosIterator.getCurrentRow();
        currentRowDocumentosAdjuntados.getAttribute("Content"); 
        
        Long IdCliente = (null == currentRowDocumentosAdjuntados.getAttribute("IdCliente"))? null 
                       : (Long)currentRowDocumentosAdjuntados.getAttribute("IdCliente"); 
        
         logger.warning("Inf, IdCliente : " + IdCliente);
        
        String fileName = (String) ADFUtils.getBoundAttributeValue("Filename");        
        applyIE11Fix(fileName);
        
        blobDomain = (BlobDomain)currentRowDocumentosAdjuntados.getAttribute("Content"); // the value is a BlobDomain data type
        
        if(blobDomain != null){
            try {
                in = blobDomain.getInputStream();
                byte[] buffer = new byte[8192];
                int bytesRead = 0;

                while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                    
                }
      
                blobDomain.closeInputStream(); // close the blob to release the recources
                outputStream.flush(); // flush the output stream                
                                
            } catch (IOException e) {
                logger.log(ADFLogger.ERROR, e.getMessage()); 
            }

            
        }else{
                logger.warning("Error, blobDomain es resuelto a null"); 
            }
    }
        
    
    private void applyIE11Fix(String docName){
        logger.warning("Inicia el metodo applyIE11Fix");
        RequestContext requestCtx = RequestContext.getCurrentInstance();
        Agent agent = requestCtx.getAgent();
        String version = agent.getAgentVersion();
        String browser = agent.getAgentName();

        if (browser != null && "ie".equals(browser) && version != null && version.startsWith("11")){
            logger.warning("Inf, Navegador IE detectado codificando nombre ...");
            HttpServletResponse resp =
                (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            String finalName = docName;

            try{
                finalName = URLEncoder.encode(docName, "UTF-8");
            } catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }

            resp.setHeader("content-disposition", "attachment; filename=\"" + finalName + "\"");
            resp.setContentType("application/octet-stream");
        }
        logger.warning("Termina el metodo applyIE11Fix");
    }
    
    public void modificarFichaActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside modificarFichaActionListener: " + actionEvent.getComponent().getId());
        DocumentosClientesBean documentosClientesBean = (DocumentosClientesBean)JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}");
        DCIteratorBinding voTiposDocIterator = null;
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        
        // Seteamos datos en componentes con la información del elemento de la tabla/tree seleccionado        
        documentosClientesBean.setArchivoData();
        
        // Prendemos checkbox de popup si es un documento del tipo Justificación
        if((documentosClientesBean.getEsJustificacionDoc() != null) && documentosClientesBean.getEsJustificacionDoc())
            documentosClientesBean.setIsJustificacion(Boolean.TRUE);
        
        // Si la acción proviene del tree entonces puede tener un Tipo de documento diferente al que se muestra en el combo.
        // Lo anterior debido a que el combo de Tipos se filtra en base al Id Tarea Bpm.
        // Por lo tanto, si el IdTareaBpm es diferente de null, quitamos el filtro en los Tipos
    //        voTiposDocIterator.executeQuery();
        
        // Asignamos al combo de Tipo de Documento el valor correspondiente, sólo cuando el IdTipoDoc NO es null,
        // y el Documento NO se subió desde el Gestor de Operaciones
        if((documentosClientesBean.getIdTipoDoc() != null) && (!documentosClientesBean.getEsDocumentoSubidoEnGestorOp())) {
            voTiposDocIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("TiposDocumentoLOVIterator");
            voTiposDocIterator.setCurrentRowWithKeyValue(documentosClientesBean.getIdTipoDoc().toString());
        }
        
        // Mostramos popup
        getPopupAgregar().show(popupHints);
    }
    
    public void tiposDocumentoValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE, "Inside tiposDocumentoValueChangeListener: " + valueChangeEvent.getComponent().getId());
        DocumentosClientesBean documentosClientesBean = (DocumentosClientesBean)JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}");
        
        // Actualizamos valores con lo selecconado en el combo (pushes data to Model)
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        
        if((valueChangeEvent.getNewValue() == null) 
           || (valueChangeEvent.getNewValue().toString().trim().length()==0))
            documentosClientesBean.setIdTipo(null);
        else
            setTipoDocumentoEnBean();
    }
    
    private void setTipoDocumentoEnBean(){
        logger.log(ADFLogger.TRACE, "Inside setTipoDocumentoEnBean.");
        DocumentosClientesBean documentosClientesBean = (DocumentosClientesBean)JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}");
        // AUN NO SE EXPONE EL ITERATOR DE TIPOSDOCUMENTOLOCITERATOR
        DCIteratorBinding voTiposDocIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("TiposDocumentoLOVIterator");
        Row currentRowTiposDoc = voTiposDocIterator.getCurrentRow();
        
        documentosClientesBean.setIdTipo(new Integer(currentRowTiposDoc.getAttribute("Id").toString()));        
    }
    
    //GOTO CONSTRUCCION
    public void aceptarAdjuntarModificarActionListener(ActionEvent actionEvent) {
        // Insertamos documento en base de datos
        logger.log(ADFLogger.TRACE, "Inside aceptarAdjuntarActionListener: " + actionEvent.getComponent().getId());
        DocumentosClientesBean documentosClientesBean = (DocumentosClientesBean)JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}");
        oracle.jbo.domain.Number idCliente = null;
        oracle.jbo.domain.Number idTareaBpm = null;
        oracle.jbo.domain.Number numeroSerieDocumento = null;
        String instanciaProceso = null;
        
        Boolean adjuntarNumeroSerieDocumento = (null != JSFUtils.resolveExpression("#{pageFlowScope.pAdjuntarNumeroSerieDocumento}")) ?
            (Boolean)JSFUtils.resolveExpression("#{pageFlowScope.pAdjuntarNumeroSerieDocumento}") : Boolean.FALSE;
        
        if(documentosClientesBean != null){
            
            if(validarAdjuntarModificar() && validarTipoDocumentoExistente()) {
                
                if(documentosClientesBean.getIsJustificacion()) {
                    // Si marca check de justificación limpiamos variables de fecha, código, y content (archivo)
                    documentosClientesBean.setFechaArchivo(null);
                    documentosClientesBean.setCodigoArchivo(null);
                    documentosClientesBean.setDocumentoAdjunto(null);
                }
                
                try {
                    idCliente = new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdCliente}"));
                    idTareaBpm = ((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null) 
                                  && (JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString().trim().length() > 0))
                        ? new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}")) : null;
                    numeroSerieDocumento = ((JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}") != null) 
                                  && (JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}").toString().trim().length() > 0))
                        ? new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}")) : null; 
                    
                    if(JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}") != null){
                        instanciaProceso = JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}").toString();
                        logger.warning("pInstanciaProceso :"+instanciaProceso);
                    }else{
                        logger.warning("pInstanciaProceso es nulo");
                    }
                    
                    if((documentosClientesBean.getAccionDeTablaArbol().equalsIgnoreCase("arbol")) 
                       && (documentosClientesBean.getIdTarea() != null)) {
                        // Para los documentos del árbol usamos el IdTareaBpm que viene del servicio, si no es null.
                        // Si el IdTareaBpm es null, entonces se trata del escenario en el que se suben documentos desde
                        // el Gestor de Operaciones, por lo tanto, usamos el IdTareaBpm de la tarea actual (ya asignado).
                        idTareaBpm = documentosClientesBean.getIdTarea();
                    }
                } catch (SQLException e) {
                    logger.log(ADFLogger.ERROR, e.getMessage());
                }
                if(documentosClientesBean.getOpAdjuntarModificar().equalsIgnoreCase("adjuntar")){
                    if(adjuntarNumeroSerieDocumento && numeroSerieDocumento == null){
                        JSFUtils.addFacesErrorMessage("Es necesario el número de serie de documento para poder adjuntar un documento.");
                    }else{
                    adjuntarDocumento(documentosClientesBean, idCliente, idTareaBpm, new oracle.jbo.domain.Number(1),numeroSerieDocumento,instanciaProceso);
                    }
                }else{
                    modificarDocumento(documentosClientesBean, idCliente, idTareaBpm,numeroSerieDocumento);
                }
                
                // Refresh de datos
                this.actualizarAfterInsert();
                
                Object rowKey = tableDocumentosUIC.getAttributes().get("scrollTopRowKey");
                RowKeySet rks = tableDocumentosUIC.getSelectedRowKeys();
                rks.clear();
                rks.add(rowKey);
                
                // Cerramos popup
                getPopupAgregar().hide();
            }
        }
    }
    
    public void cancelarAdjuntarModificarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarAdjuntarActionListener: " + actionEvent.getComponent().getId());

        // Limpiamos variables en popup y lo cerramos
        limpiarVariablesPopup();
        getPopupAgregar().hide();
    }
    
    private Boolean validarAdjuntarModificar(){
        // Validación de campos obligatorios
        Boolean esDatosCorrectos = Boolean.TRUE;
        DocumentosClientesBean documentosClientesBean = (DocumentosClientesBean)JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}");
         
        // Si NO es justificación...
        if(!documentosClientesBean.getIsJustificacion()) {
            
            // Y la acción proviene de la tabla, y la acción es adjuntar, entonces el archivo es requerido. 
            // Nota 1 by FCP: para cuando es modificar el archivo ya está guardado en el row y si elige otro se actualiza de forma automática
            // Nota 2 by FCP: la nota 1 no aplica cuando era un archivo del tipo Justificación, en ese caso no hay archivo guardado
            if(documentosClientesBean.getAccionDeTablaArbol().equalsIgnoreCase("tabla")
               && (documentosClientesBean.getDocumentoAdjunto() == null)) {
                
                if(documentosClientesBean.getOpAdjuntarModificar().equalsIgnoreCase("adjuntar") 
                   || (documentosClientesBean.getOpAdjuntarModificar().equalsIgnoreCase("modificar") 
                       && (documentosClientesBean.getEsJustificacionDoc() != null) && documentosClientesBean.getEsJustificacionDoc())) {
                    
                    esDatosCorrectos = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("Debe completar la información requerida: Seleccionar documento.");
                }
            }
            
            // El nombre de archivo es requerido
            if((documentosClientesBean.getNombreArchivo() == null) || (documentosClientesBean.getNombreArchivo().trim().length() == 0)){
                
                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage("Debe completar la información requerida: Nombre."); 
            }
            
            // La fecha es requerida
            if(documentosClientesBean.getFechaArchivo() == null){
                
                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage("Debe completar la información requerida: Fecha documento.");   
            }
        }
        
        return esDatosCorrectos;
    }
    
    private Boolean validarTipoDocumentoExistente(){
        // El Tipo de documento no se puede repetir. 
        // Nota by FCP 18/11/2015: A petición de Eduardo se comenta esta validación (probada y funcional).
        Boolean esDatosCorrectos = Boolean.TRUE;
    //        DocumentosBean documentosBean = (DocumentosBean)JSFUtils.resolveExpression("#{pageFlowScope.DocumentosManagedBean}");
    //        DCIteratorBinding documentosAdjuntadosIterator = null;
    //        ViewObject documentosAdjuntadosVO = null;
    //        RichPopup.PopupHints popupHints = null;
    //        DocumentoVORowImpl documentoRow = null;
    //        Long idDocumento = null;
    //
    //        // Esta validación sólo aplica para los Documentos de la tabla
    //        if(documentosBean.getAccionDeTablaArbol().equalsIgnoreCase("tabla")) {
    //
    //            documentosAdjuntadosIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("DocumentosAdjuntadosVOIterator");
    //            documentosAdjuntadosVO = documentosAdjuntadosIterator.getViewObject();
    //
    //            // Inicialización de IdTipo por si eligió el elemento por default en el combo o es modificar
    //            setTipoDocumentoEnBean();
    //
    //            // Verificamos si en los documentos adjuntados ya existe uno con el mismo tipo
    //            Row[] filteredRows = documentosAdjuntadosVO.getFilteredRows("IdTipoDocumento", documentosBean.getIdTipo());
    //
    //            if(filteredRows.length > 0) {
    //
    //                esDatosCorrectos = Boolean.FALSE;
    //
    //                if(documentosBean.getOpAdjuntarModificar().equalsIgnoreCase("modificar")) {
    //
    //                    // Si es modificar, verificamos que no se esté modificando a sí mismo
    //                    documentoRow = getDocumentoRowByIdDocumento(documentosBean.getIdDocumento());
    //                    idDocumento = (Long)filteredRows[0].getAttribute("IdDocumento");
    //
    //                    if(documentosBean.getIdDocumento().compareTo(idDocumento.doubleValue()) == 0)
    //                        esDatosCorrectos = Boolean.TRUE;
    //                }
    //            }
    //        }
    //
    //        if(!esDatosCorrectos) {
    //
    //            popupHints = new RichPopup.PopupHints();
    //            getPopupReemplazar().show(popupHints); // Mostramos popup
    //        }
        
        return esDatosCorrectos;   
    }
    
    //GOTO CONSTRUCCION
    private Long adjuntarDocumento(DocumentosClientesBean documentosClientesBean, oracle.jbo.domain.Number idCliente, 
                                   oracle.jbo.domain.Number idTareaBpm, oracle.jbo.domain.Number idAccion,
                                            oracle.jbo.domain.Number numeroSerieDocumento,String instanciaProceso){
        logger.log(ADFLogger.WARNING, "Inside adjuntarDocumento.");
        BindingContainer bindings = ADFUtils.getBindingContainer();   
        OperationBinding adjuntarDocumento = bindings.getOperationBinding("adjuntarDocumentoCliente");
        Long idDocumento = null;
        
        if(documentosClientesBean != null){            
            adjuntarDocumento.getParamsMap().put("idCliente", idCliente);
            adjuntarDocumento.getParamsMap().put("idTareaBpm", idTareaBpm);
            adjuntarDocumento.getParamsMap().put("esJustificacion", documentosClientesBean.getIsJustificacion());
            
            if((documentosClientesBean.getDocumentoAdjunto() != null) 
                && (documentosClientesBean.getDocumentoInputStream() != null)){ // Check that file is not empty
               
                adjuntarDocumento.getParamsMap().put
                    ("content", createBlobDomain(documentosClientesBean.getDocumentoInputStream()));
                adjuntarDocumento.getParamsMap().put
                    ("mimeType", documentosClientesBean.getDocumentoAdjunto().getContentType().toString());
            }
            
            adjuntarDocumento.getParamsMap().put("fileName", documentosClientesBean.getNombreArchivo());
            adjuntarDocumento.getParamsMap().put("codigo", documentosClientesBean.getCodigoArchivo());
            adjuntarDocumento.getParamsMap().put("fecha", documentosClientesBean.getFechaArchivo());
            
            // Inicialización de IdTipo por si eligió el elemento por default en el combo, i.e. no pasó por el evento de cambio de valor
            if(documentosClientesBean.getIdTipo() == null){
                setTipoDocumentoEnBean();
            }
            
            adjuntarDocumento.getParamsMap().put("idTipo", documentosClientesBean.getIdTipo());
            adjuntarDocumento.getParamsMap().put("resumen", documentosClientesBean.getResumenArchivo());
            adjuntarDocumento.getParamsMap().put("idAccion", idAccion); // 1=Agregado, 2=Modificado 3=Eliminado. Según catálogo TCA_ACCION_DOCUMENTO
            adjuntarDocumento.getParamsMap().put("loginCreadoPor", ADFContext.getCurrent().getSecurityContext().getUserName());  // Creado por
            adjuntarDocumento.getParamsMap().put("nombreCreadoPor", ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName()); // Nombre creado por
            adjuntarDocumento.getParamsMap().put("idOnbase", documentosClientesBean.getIdExterno());
            adjuntarDocumento.getParamsMap().put("numeroSerieDocumento", numeroSerieDocumento);
            adjuntarDocumento.getParamsMap().put("instanciaProceso", instanciaProceso);
            
            //Si la etapa es es En Transito se quita la instancia del proceso, se agrega el mimetype desde onbase y se pone la accion en sincronizado
            if(documentosClientesBean.getDocumentoAdjunto() == null && !documentosClientesBean.getIsJustificacion() && documentosClientesBean.getEtapaDoc().equalsIgnoreCase("En Transito")) //JURBINA@02092019 si el mimetype viene se envia
            {
                adjuntarDocumento.getParamsMap().put("mimeType", documentosClientesBean.getMimeType());
                //adjuntarDocumento.getParamsMap().put("instanciaProceso", null);
                adjuntarDocumento.getParamsMap().put("idAccion", 4); // 1=Agregado, 2=Modificado 3=Eliminado. Según catálogo TCA_ACCION_DOCUMENTO
            }
            
            logger.log(ADFLogger.WARNING, "idCliente : " + idCliente);
            logger.log(ADFLogger.WARNING, "idTareaBpm : " + idTareaBpm);
            logger.log(ADFLogger.WARNING, "numeroSerieDocumento : " + numeroSerieDocumento);
            logger.log(ADFLogger.WARNING, "instanciaProceso : " + instanciaProceso);
            
            logger.log(ADFLogger.WARNING, "Estado : " + documentosClientesBean.getEstadoDocumento());
            logger.log(ADFLogger.WARNING, "Etapa : " + documentosClientesBean.getEtapaDoc());
            
            adjuntarDocumento.execute();
            if(!adjuntarDocumento.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(adjuntarDocumento.getErrors().toString());
            }
            else if(adjuntarDocumento.getResult() != null) {
                idDocumento = (Long)adjuntarDocumento.getResult();
                //JURBINA@02092019 cuando sea un documento en transito se inicializa de nuevo el arbol
                if(documentosClientesBean.getDocumentoAdjunto() == null && !documentosClientesBean.getIsJustificacion() && documentosClientesBean.getEtapaDoc().equalsIgnoreCase("En Transito")) 
                {
                    documentosClientesBean.inicializarArbolDocumentos();
                }
            }
        }
        logger.log(ADFLogger.WARNING, "idDocumento agregado Cliente: " + idDocumento);
        return idDocumento;
    }
    
    
    private BlobDomain createBlobDomain(InputStream in) {
        logger.log(ADFLogger.TRACE, "Inside createBlobDomain");
        
        BlobDomain blobDomain = null;
        OutputStream out = null;

        try {
            blobDomain = new BlobDomain();
            out = blobDomain.getBinaryOutputStream();
            byte[] buffer = new byte[8192];
            int bytesRead = 0;

            while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            in.close();

        } catch (IOException e) {
            logger.log(ADFLogger.ERROR, e.getMessage()); 
        } catch (SQLException e) {
            logger.log(ADFLogger.ERROR, e.getMessage()); 
        }

        return blobDomain;
    }
    
    private void modificarDocumento(DocumentosClientesBean documentosClientesBean, 
                                   oracle.jbo.domain.Number idCliente, oracle.jbo.domain.Number idTareaBpm,
                                            oracle.jbo.domain.Number numeroSerieDocumento){
        logger.log(ADFLogger.TRACE, "Inside modificarDocumento.");
        Row adjuntoRow = null;
        DocumentoVORowImpl documentoRow = null;
        DocumentoVORowImpl documentoRowTree = null;
        Integer justificacion = null;
        Boolean esDoCommit = Boolean.TRUE;
        Long idDocumentoTree = null;
        Long idAdjuntoTree = null;
        oracle.jbo.domain.Number idTareaBpmTree = null;
        String instanciaProceso = null;
         
        // 1) Verificamos que el documento se pueda modificar 
        if((documentosClientesBean.getEsModificable() != null) && (documentosClientesBean.getEsModificable())){
            
            if(JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}") != null){
                    instanciaProceso = JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}").toString();
                    logger.warning("pInstanciaProceso :"+instanciaProceso);
            }else{
                    logger.warning("pInstanciaProceso es nulo");
            }
         
            // 2) Verificamos si existe el documento en base de datos
            if(documentosClientesBean.getIdDocumento() != null) // Si IdDocumento es null entonces NO está en BD
                documentoRow = getDocumentoRowByIdDocumento(documentosClientesBean.getIdDocumento());
            
            if((documentoRow == null) || (documentoRow.getAdjuntoVO().getRowAtRangeIndex(0) == null)) {
                
                if(documentosClientesBean.getAccionDeTablaArbol().equalsIgnoreCase("tabla")) {
                    // Si no existe el documento/adjunto, lo insertamos
                    adjuntarDocumento(documentosClientesBean, idCliente, idTareaBpm, new oracle.jbo.domain.Number(2),
                                                        numeroSerieDocumento,instanciaProceso);
                    
                    // Para el caso de que SÍ existía el Documento pero NO tenía un Adjunto (el caso de Justificación), 
                    // eliminamos el documento anterior de la base de datos
                    if(documentoRow != null) {
                        documentoRow.remove();
                    }
                }
                else{                    
                    // Escenario para documentos del árbol que se encuentran en Tránsito y no tienen un Id Adjunto
                    // Si no tiene un Id de Adjunto checamos si tiene un IdOnBase (IdExterno) y lo insertamos en la BD
                    if(documentosClientesBean.getIdExterno() != null) {
                        // Para este caso el IdTareaBpm que viene del servicio es null, debido a que se insertaron por la digitalizadora, 
                        // por lo tanto le asignamos el IdTareaBpm de la tarea actual
                        try {
                            idTareaBpmTree = ((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null) 
                                          && (JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString().trim().length() > 0))
                                ? new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}")) : null; 
                        } catch (SQLException e) {
                            logger.log(ADFLogger.ERROR, e.getMessage());
                        }
                    
                        idDocumentoTree = adjuntarDocumento(documentosClientesBean, idCliente, idTareaBpmTree, new oracle.jbo.domain.Number(2),numeroSerieDocumento,instanciaProceso);
                        documentoRowTree = getDocumentoRowByIdDocumento(new oracle.jbo.domain.Number(idDocumentoTree));
                        idAdjuntoTree = (Long)documentoRowTree.getAdjuntoVO().getRowAtRangeIndex(0).getAttribute("IdAdjunto");
                    }
                    else{
                        esDoCommit = Boolean.FALSE;
                        JSFUtils.addFacesErrorMessage("El documento seleccionado en el árbol no existe en la tabla " +
                            "Adjunto de la Base de Datos ni tiene un Id de OnBase y, por lo tanto, no se puede modificar.");
                    }
                }
            }
            else{
                // Si existe el documento, lo modificamos
                adjuntoRow = documentoRow.getAdjuntoVO().getRowAtRangeIndex(0);
                
                if(documentosClientesBean.getAccionDeTablaArbol().equalsIgnoreCase("tabla")){
                    // Sólo se puede modificar el contenido y check de justificación de los archivos de la tabla (que son los que están en BD)
                    if((documentosClientesBean.getDocumentoAdjunto() != null) 
                        && (documentosClientesBean.getDocumentoInputStream() != null)){ // Check that file is not empty
                       
                        adjuntoRow.setAttribute("Content", createBlobDomain(documentosClientesBean.getDocumentoInputStream()));
                        adjuntoRow.setAttribute("MimeType", documentosClientesBean.getDocumentoAdjunto().getContentType().toString());
                    }
                    
                    if((documentosClientesBean.getIsJustificacion() != null) && (documentosClientesBean.getIsJustificacion()))
                        justificacion = 1;
                    else
                        justificacion = 0;
                    
                    documentoRow.setEsJustificacion(justificacion);
                }
                
                adjuntoRow.setAttribute("Filename", documentosClientesBean.getNombreArchivo());
                documentoRow.setCodigoDocumento(documentosClientesBean.getCodigoArchivo());
                
                // Inicialización de IdTipo, en caso de que el valor del combo Tipo de Documento no haya cambiado, 
                // i.e. no pasó por el evento de cambio de valor
                if(documentosClientesBean.getIdTipo() == null) {
                    setTipoDocumentoEnBean();
                }
                
                documentoRow.setIdTipoDocumento(documentosClientesBean.getIdTipo());
                
                if(documentosClientesBean.getFechaArchivo() != null)
                    documentoRow.setFechaDocumento(new Timestamp(documentosClientesBean.getFechaArchivo().getTime()));
                
                documentoRow.setComentario(documentosClientesBean.getResumenArchivo());
                documentoRow.setIdTcaAccion(2); // 2=Modificado
                documentoRow.setLoginUsuarioModifica(ADFContext.getCurrent().getSecurityContext().getUserName()); // Modificado por
                documentoRow.setNombreUsuarioModifica(ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName()); // Nombre modificado por
                
                if(idTareaBpm != null) // El campo idTareaBpm no es obligatorio
                    documentoRow.setIdTareaBpm(idTareaBpm.intValue()); // El IdTareaBpm debería ser el mismo puesto que es un documento existente.
            }
            
            // Realizamos Commit para guardar modificaciones
            if(esDoCommit)
                this.commitData();
            
            // Para los documentos del árbol, cuando no hubo errores
            if(documentosClientesBean.getAccionDeTablaArbol().equalsIgnoreCase("arbol") && esDoCommit) {
               
                // Cuando el documento NO se subió desde el Gestor de Operaciones
                if(!documentosClientesBean.getEsDocumentoSubidoEnGestorOp()) {
            
                    // Si NO se creó un nuevo documento entonces tomamos el Id de Adjunto existente
                    if(idAdjuntoTree == null)
                        idAdjuntoTree = documentosClientesBean.getIdAdjunto().longValue();
                    
                    // Invocamos servicio para actualizar documento del árbol
                    if(modificarEliminarDocumento(idAdjuntoTree, true)) {
                        // Actualizamos el árbol de documentos si el servicio se ejecutó de forma exitosa 
                        documentosClientesBean.inicializarArbolDocumentos(); 
                    }
                    else if(documentoRowTree != null) {
                        // Si falló el servicio y se insertó un documento, lo eliminamos
                        documentoRowTree.remove();
                        this.commitData();
                    }
                }
                else {
                    // Actualizamos el árbol de documentos
                    documentosClientesBean.inicializarArbolDocumentos(); 
                }
            }
        }
        else{
            JSFUtils.addFacesErrorMessage("El documento seleccionado no se puede modificar.");
        }
    }
    
    
    private void commitData() {
        logger.log(ADFLogger.TRACE, "Inside commitData");
        BindingContainer bindings = null;
        OperationBinding commit = null;
        
        bindings = ADFUtils.getBindingContainer();                   
        commit = bindings.getOperationBinding("Commit");
        
        commit.execute();
        if(!commit.getErrors().isEmpty()){
            // Manejo de errores
        }
    }
    
    /**
     * Método que invoca la operación actualizarDocumento o eliminarDocumento dentro del servicio de Documentos
     * @param idAdjunto
     * @param esActualizar
     * @return
     */
    private Boolean modificarEliminarDocumento(Long idAdjunto, Boolean esActualizar) {
        logger.log(ADFLogger.TRACE, "Inside modificarEliminarDocumento");
        Boolean isValidModificarEliminarDocumento = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();   
        HashMap<String, CargarDocumentoResponseType> respuestaServicio = null;
        CargarDocumentoResponseType response = null;
        OperationBinding operationBinding = null;
        String errorCode = null;
        String message = null;
        
        if(esActualizar)
            operationBinding = bindings.getOperationBinding("actualizarDocumentoTree");
        else
            operationBinding = bindings.getOperationBinding("eliminarDocumentoTree");
        
        operationBinding.getParamsMap().put("idAdjunto", idAdjunto);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else if(operationBinding.getResult() != null) {
            
            respuestaServicio = (HashMap<String, CargarDocumentoResponseType>)operationBinding.getResult();
            response = respuestaServicio.get("response");
            
            if(response.getResultado().getError() != null) {
                errorCode = response.getResultado().getError().getErrorCode() == null ? "" :  
                    response.getResultado().getError().getErrorCode();
            }
            else{ errorCode = ""; }
                        
            if((response.getResultado().getResult() != null) 
               && (response.getResultado().getResult().value().equalsIgnoreCase("OK")) 
               && (errorCode.trim().length() == 0)) {
                        
                isValidModificarEliminarDocumento = Boolean.TRUE;         
            }
            else{
                // Error al ejecutar el servicio
                message = response.getResultado().getMessage() == null ? "" : 
                    ("Mensaje: " + response.getResultado().getMessage()) + ". ";
                JSFUtils.addFacesErrorMessage(message + "Código de error: " 
                                              + response.getResultado().getError().getErrorCode() + ". Descripción: " 
                                              + response.getResultado().getError().getErrorDescription() + ".");
                logger.log(ADFLogger.ERROR, response.getResultado().getError().getErrorDescription());
            }
        }
        
        return isValidModificarEliminarDocumento;
    }
    
    public void aceptarReemplazarDocumentoActionListener(ActionEvent actionEvent) {
        // Eliminamos Documento con tipo repetido y lo reeemplazamos con el nuevo
        logger.log(ADFLogger.TRACE, "Inside aceptarReemplazarDocumentoActionListener: " + actionEvent.getComponent().getId());
        DCIteratorBinding documentosAdjuntadosClientesIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("DocumentosAdjuntadosClientesVOIterator");
        ViewObject documentosAdjuntadosClientesVO = documentosAdjuntadosClientesIterator.getViewObject();
        DocumentosClientesBean documentosClientesBean = (DocumentosClientesBean)JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}");
        DocumentoVORowImpl documentoRow = null;
        Long idDocumento = null;
        oracle.jbo.domain.Number idCliente = null;
        oracle.jbo.domain.Number idTareaBpm = null;
        oracle.jbo.domain.Number numeroSerieDocumento = null;
        String instanciaProceso = null;
        
        try {
            idCliente = new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdCliente}"));
            
            if((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null) 
               && (JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString().trim().length() > 0)) // pIdTareaBpm no es obligatorio
                idTareaBpm = new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}"));
            
            if((JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}") != null) 
               && (JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}").toString().trim().length() > 0)) // pNumeroSerieDocumentos no es obligatorio
                numeroSerieDocumento = new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pNumeroSerieDocumento}"));
            
            if(JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}") != null){
                instanciaProceso = JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}").toString();
                logger.warning("pInstanciaProceso :"+instanciaProceso);
            }else{
                logger.warning("pInstanciaProceso es nulo");
            }
            
        } catch (SQLException e) {
            logger.log(ADFLogger.ERROR, e.getMessage());
        }
        
        // 1) Insertamos nuevo documento
        if(documentosClientesBean.getOpAdjuntarModificar().equalsIgnoreCase("adjuntar"))
            adjuntarDocumento(documentosClientesBean, idCliente, idTareaBpm, new oracle.jbo.domain.Number(1),numeroSerieDocumento,instanciaProceso);
        else
            modificarDocumento(documentosClientesBean, idCliente, idTareaBpm,numeroSerieDocumento);
        
        // 2) Obtenemos documento anterior que será reemplazado
        Row[] filteredRows = documentosAdjuntadosClientesVO.getFilteredRows("IdTipoDocumento", documentosClientesBean.getIdTipo());

        if(filteredRows.length > 0) {
             
            // 3) Lo eliminamos
            idDocumento = (Long)filteredRows[0].getAttribute("IdDocumento");
            
            if(idDocumento != null) {
                
                documentoRow = getDocumentoRowByIdDocumento(new oracle.jbo.domain.Number(idDocumento));
                
                if(documentoRow != null)
                    documentoRow.remove();
                
                // Realizamos Commit para eliminar Documento anterior o guardar modificaciones
                this.commitData();
            }
        }
        
        // 4) Refresh de datos
        this.actualizarAfterInsert();
        
        // 5) Cerramos popups
        getPopupAgregar().hide();
        getPopupReemplazar().hide();
    }
    
    public void cancelarReemplazarDocumentoActionListener(ActionEvent actionEvent) {
        // Cerramos popup de confirmación
        logger.log(ADFLogger.TRACE, "Inside cancelarReemplazarDocumentoActionListener: " + actionEvent.getComponent().getId());
        getPopupReemplazar().hide();
    }
    
    private void actualizarAfterInsert(){
        DocumentosClientesBean documentosClientesBean = (DocumentosClientesBean)JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}");
        
        // Hacemos un executeQuery en DocumentosAdjuntadosVO
        actualizarDocumentosAdjuntados();
        
        // Limpiamos variables del formulario (para que no se quede con los datos del documento modificado)
        documentosClientesBean.cleanDocumentData();
        
        // Actualizamos valores en formulario con el nuevo current row de la tabla 
        // (después de ejecutar el método actualizarDocumentosAdjuntados cambia el current row)
        setDatosDeTablaEnFormulario();
        
        limpiarVariablesPopup();
    }
    
    private void actualizarDocumentosAdjuntados(){
        // Actualizamos tabla con los Documentos adjuntados a la tarea después de un insert/delete
        DCIteratorBinding voDocAdjuntadosIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("DocumentosAdjuntadosClientesVOIterator");
        voDocAdjuntadosIterator.executeQuery();
    }
    
    private void limpiarVariablesPopup(){
        // Una vez actualizado o modificado el documento limpiamos las variables (puesto que son scope PageFlow)
        DocumentosClientesBean documentosClientesBean = null;
        DCIteratorBinding voTiposDocIterator = null;
        
        documentosClientesBean = (DocumentosClientesBean)JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}");
        documentosClientesBean.setDocumentoAdjunto(null);
        documentosClientesBean.setIsJustificacion(false);
        documentosClientesBean.setNombreArchivo(null);
        documentosClientesBean.setFechaArchivo(null);
        documentosClientesBean.setCodigoArchivo(null);
        documentosClientesBean.setResumenArchivo(null);
        documentosClientesBean.setIdTipo(null);
        
        // Limpiamos valor seleccionado en combo de Tipo de Documento
        voTiposDocIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("TiposDocumentoLOVIterator");
        voTiposDocIterator.executeQuery();
    }
    
    
    public void confirmEliminarDocumentoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside confirmEliminarDocumentoActionListener: " + actionEvent.getComponent().getId());
        DocumentosClientesBean documentosClientesBean = (DocumentosClientesBean)JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}");
        DocumentoVORowImpl documentoRow = null;
        
        // 1) Verificamos que el documento se pueda eliminar
        if((documentosClientesBean.getEsBorrable() != null) && (documentosClientesBean.getEsBorrable())){
        
            if(documentosClientesBean.getAccionDeTablaArbol().equalsIgnoreCase("tabla")){
            
                documentoRow = getDocumentoRowByIdDocumento(documentosClientesBean.getIdDocumento()); // Obtenemos documento por Id
                if(documentoRow != null){
                    
                    // Borrado en base de datos para los documentos de la tabla
                    documentoRow.remove(); // Delete on cascade para eliminar de tablas Adjunto, Documento y Documentos
                    
                    // Realizamos commit
                    this.commitData();
                }
            }
            else{
                // Para el caso de los documentos del arbol se invoca servicio que elimina documento
                if((documentosClientesBean.getIdAdjunto() != null) 
                   && (modificarEliminarDocumento(documentosClientesBean.getIdAdjunto().longValue(), false))) {
                       
                    // Actualizamos el árbol de documentos
                    documentosClientesBean.inicializarArbolDocumentos();
                }
            }
            
            // Actualizamos tabla con Documentos adjuntados a la tarea 
            actualizarDocumentosAdjuntados(); 
            
            // Limpiamos variables del formulario (para que no se quede con los datos del documento eliminado)
            documentosClientesBean.cleanDocumentData();
            
            // Actualizamos valores en formulario con el nuevo current row actualizado en la tabla
            setDatosDeTablaEnFormulario();
        }
        else{
            JSFUtils.addFacesErrorMessage("El documento seleccionado no se puede eliminar.");
        }
        
        getPopupEliminar().hide();
    }
    
    public void cancelEliminarDocumentoActionListener(ActionEvent actionEvent) {
        // Cerramos popup de eliminar
        logger.log(ADFLogger.TRACE, "Inside cancelEliminarDocumentoActionListener: " + actionEvent.getComponent().getId());
        getPopupEliminar().hide();
    }


    public RichPopup getPopupEliminar() {
        return popupEliminar;
    }

    public void setPopupEliminar(RichPopup popupEliminar) {
        this.popupEliminar = popupEliminar;
    }

    public RichPopup getPopupAgregar() {
        return popupAgregar;
    }

    public void setPopupAgregar(RichPopup popupAgregar) {
        this.popupAgregar = popupAgregar;
    }

    public RichPopup getPopupReemplazar() {
        return popupReemplazar;
    }

    public void setPopupReemplazar(RichPopup popupReemplazar) {
        this.popupReemplazar = popupReemplazar;
    }

    public RichOutputText getOtInitForm() {
        logger.warning("Dentro de getOtInitForm");
        // Ejecuta método que llena el el formulario con los datos de la fila seleccionada por default (current row) 
        // (este outputText está al final de la página)
        setDatosDeTablaEnFormulario();
        return otInitForm;
    }

    public void setOtInitForm(RichOutputText otInitForm) {
        this.otInitForm = otInitForm;
    }


    public void setTableDocumentosUIC(RichTable tableDocumentosUIC) {
        this.tableDocumentosUIC = tableDocumentosUIC;
    }

    public RichTable getTableDocumentosUIC() {
        return tableDocumentosUIC;
    }

    public void setTreeArbolUIC(RichTree treeArbolUIC) {
        this.treeArbolUIC = treeArbolUIC;
    }

    public RichTree getTreeArbolUIC() {
        return treeArbolUIC;
    }
    
    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }
    
    public void enviarDocumentoClienteOnBase(ActionEvent actionEvent) {
        logger.warning("Inside enviarDocumentoClienteOnBase.");
        
        DocumentosClientesBean documentosClientesBean = (DocumentosClientesBean)JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}");
        
        cargarDocumentosClienteOnBase();

        getPopupEnviarDocumento().hide();
        
        documentosClientesBean.setBtnActualizarDocs(Boolean.TRUE);
        
        actualizarDocumentosCliente(actionEvent);
    }
    
    public void actualizarDocumentosCliente(ActionEvent actionEvent) {
        logger.warning("Inside actualizarDocumentosCliente.");
        
        Map<Long, String> docAjuntosBeforeRefresh = new HashMap<Long, String>();
        Map<Long, String> docAjuntosAfterRefresh = new HashMap<Long, String>();
        List<String> documentosEnviados = new ArrayList<String>();
        
        docAjuntosBeforeRefresh = verificarDocumentosAdjuntos();
        
        refreshComponentDocuments();
        
        docAjuntosAfterRefresh = verificarDocumentosAdjuntos();
        
        documentosEnviados = obtenerDocumentosEnviados(docAjuntosBeforeRefresh, docAjuntosAfterRefresh);
        
        documentsMessage(documentosEnviados);
    }
    
    public Map cargarDocumentosClienteOnBase(){
        logger.warning("Inside cargarDocumentosClienteOnBase.");
        
        Map<Long, String> nameDocEnviados = null;
        String usuario = null;
        try{
            usuario = ADFContext.getCurrent().getSecurityContext().getUserName();  
        }catch (Exception e){
            logger.warning("Error en number numeroSerieDocumento.", e);
        }
        
        BindingContainer bindings = ADFUtils.getBindingContainer();                   
        OperationBinding operationBinding = bindings.getOperationBinding("enviarDocumentoOnBaseCliente");
        operationBinding.getParamsMap().put("usuario", usuario);
        operationBinding.execute();
        
        if (operationBinding.getResult() != null) {
            nameDocEnviados = new HashMap<Long, String>();
            nameDocEnviados = (HashMap<Long, String>) operationBinding.getResult();
        } else {
            logger.warning("No se recupero nombre de documentos enviados en request.");
        }
        
        return nameDocEnviados;
    }
    
    public Map verificarDocumentosAdjuntos(){
        logger.warning("Inside verificarDocumentosAdjuntos.");
        
        Map<Long, String> documentosAdjuntados = null;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();                   
        OperationBinding operationBinding = bindings.getOperationBinding("currentDocumentsClient");
        operationBinding.execute();
        
        if (operationBinding.getResult() != null) {
            documentosAdjuntados = new HashMap<Long, String>();
            documentosAdjuntados = (HashMap<Long, String>) operationBinding.getResult();
        } else {
            logger.warning("No se recuperaron documentos adjuntos.");
        }
        
        logger.warning("Documentos adjuntos: " + documentosAdjuntados.size());
        
        return documentosAdjuntados;
    }
    
    public List<String> obtenerDocumentosEnviados(Map<Long, String> documentosOld, Map<Long, String> documentosNow) {
        logger.warning("Inside obtenerDocumentosEnviados.");
        
        List<String> documentosEnviados = new ArrayList<String>();
        
        if (documentosOld != null && documentosNow != null) {
            if (documentosOld.size() > 0) {
                if (documentosNow.size() > 0) {
                    for (Long keyOld : documentosOld.keySet()) {
                        if (documentosNow.containsKey(keyOld)) {
                            logger.warning("Documento en espera: " + documentosOld.get(keyOld));
                        } else {
                            logger.warning("Documento enviado: " + documentosOld.get(keyOld));
                            documentosEnviados.add(documentosOld.get(keyOld));
                        }
                    }
                } else {
                    for (Long keyOld : documentosOld.keySet()) {
                        logger.warning("Documento enviado: " + documentosOld.get(keyOld));
                        documentosEnviados.add(documentosOld.get(keyOld));
                    }
                }
            } else {
                logger.warning("No se encuentran documentos en documentosOld.");
            }
        } else {
            logger.warning("Lista de documentos es Null.");
        }
        
        return documentosEnviados;
    }
    
    public void refreshComponentDocuments() {
        logger.warning("Inside refreshComponentDocuments");
        
        DocumentosClientesBean documentosClientesBean = (DocumentosClientesBean)JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}");
        
        // Si se tiene seleccionado un Documento en el arbol
        if (treeArbolUIC.getSelectedRowKeys().size() > 0) {
            // Actualizamos el árbol de documentos
            documentosClientesBean.inicializarArbolDocumentosOnly();
            
            // Actualizamos tabla con Documentos adjuntados a la tarea 
            actualizarDocumentosAdjuntados(); 
        } else {
            // Actualizamos el árbol de documentos
            documentosClientesBean.inicializarArbolDocumentos();
            
            // Actualizamos tabla con Documentos adjuntados a la tarea 
            actualizarDocumentosAdjuntados(); 
            
            // Limpiamos variables del formulario (para que no se quede con los datos del documento eliminado)
            documentosClientesBean.cleanDocumentData();
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(formDocumentosUIC);
            
            // Actualizamos valores en formulario con el nuevo current row actualizado en la tabla
            setDatosDeTablaEnFormulario();
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(treeArbolUIC);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(tableDocumentosUIC);
    }
    
    public void documentsMessage(List<String> documentsList) {
        logger.warning("Inside documentsMessage.");
        
        if (documentsList != null) {
            if (documentsList.size() > 0) {
                JSFUtils.addFacesInformationMessage("Se han cargado los documentos en Onbase exitosamente");
                logger.warning("Documentos enviados: " + documentsList.size());
                for (String docName : documentsList) {
                    JSFUtils.addFacesInformationMessage(docName);
                }        
            } else {
                logger.warning("No se enviaron documentos a OnBase.");
            }
        } else {
            logger.warning("Lista de documentos Null.");
        }
    }
    
    public void doSearch(ActionEvent actionEvent) {
        logger.warning("Inside doSearch.");
        
        if (getSearchCriteria() != null) {
            TreeModel treeModelRenew = (TreeModel) treeArbolUIC.getValue();
            RowKeySet disclosedTreeRowKeySet = new RowKeySetImpl();
            RowKeySet currentDocument = new RowKeySetImpl();
            SelectionEvent selectionEvent = null;
            Boolean disclouseNode = Boolean.FALSE;
            Integer firstDocument = 0;
            
            treeModelRenew.setRowIndex(0); // Nos posicionamos en nodo raíz - Documentos
            
            disclosedTreeRowKeySet.add(treeModelRenew.getRowKey());
            
            if (treeModelRenew.isContainer()) { // Verificamos si el current row (raíz) es un container, i.e. contiene otros rows (hijos - Etapas)
                treeModelRenew.enterContainer(); // Accesamos a los child rows (hijos-Etapas)
                if (treeModelRenew.getRowCount() > 0) { // Verificamos que exista al menos un hijo - Etapas
                    
                    logger.warning("Etapas: " + treeModelRenew.getRowCount());
                    for (Integer etapa = 0; etapa < treeModelRenew.getRowCount() ; etapa++) { //Iteramos por el numero de Etapas
                        treeModelRenew.setRowIndex(etapa);
                        logger.warning("etapa: " + etapa);
                        if (treeModelRenew.isContainer()) { // Verificamos si el current row (raíz) es un container, i.e. contiene otros rows (hijos - Tipos de Documento)
                            treeModelRenew.enterContainer(); // Accesamos a los child rows (hijos-Tipos de Documento)
                            if (treeModelRenew.getRowCount() > 0) { // Verificamos que exista al menos un hijo - Tipos de Documento
                                
                                logger.warning("Tipos Documento: " + treeModelRenew.getRowCount());
                                for (Integer tiposDoc = 0; tiposDoc < treeModelRenew.getRowCount() ; tiposDoc++) { //Iteramos por el numero de Tipos de Documento
                                    treeModelRenew.setRowIndex(tiposDoc);
                                    logger.warning("TiposDoc: " + tiposDoc);
                                    if (treeModelRenew.isContainer()) { // Verificamos si el current row (raíz) es un container, i.e. contiene otros rows (hijos - Documento)
                                        treeModelRenew.enterContainer(); // Accesamos a los child rows (hijos-Documento)
                                        
                                        if (treeModelRenew.getRowCount() > 0) { // Verificamos que exista al menos un hijo - Documento
                                            
                                            Iterator iterDocumentos = treeModelRenew.iterator();
                                            while (iterDocumentos.hasNext()) { // Iteramos documentos
                                                TreeItem itemDocumentos = (TreeItem) iterDocumentos.next();
                                                
                                                if (searchWord(itemDocumentos.getNombreDoc())) {
                                                    
                                                    disclouseNode = Boolean.TRUE;
                                                    firstDocument++;
                                                    
                                                    if (firstDocument.compareTo(1) == 0) {  // Si es el primer documento lo toma como current.
                                                        Integer indexDocument = new Integer(treeModelRenew.getRowIndex());
                                                        List keySet = (ArrayList) treeModelRenew.getRowKey(); // Se asigna index de Documento a rowKey para hacerlo current
                                                        keySet.set(3, (indexDocument - 1));
                                                        currentDocument.add(keySet);
                                                    }
                                                }
                                            }
                                        }
                                        treeModelRenew.exitContainer();
                                        
                                        // Si se encuentra concidencia en Documento se agregan keys para abrir carpeta
                                        if (disclouseNode) {
                                            disclouseNode = Boolean.FALSE;
                                            logger.warning("key to save - Nodo Tipo Documento: " + treeModelRenew.getRowKey());
                                            logger.warning("key to save - Nodo Tipo Etapa: " + treeModelRenew.getContainerRowKey());
                                            disclosedTreeRowKeySet.add(treeModelRenew.getRowKey());
                                            disclosedTreeRowKeySet.add(treeModelRenew.getContainerRowKey());
                                        }
                                    }
                                }
                            }
                            treeModelRenew.exitContainer();
                        }
                    }
                }
            }
            logger.warning("Documentos encontrados: " + firstDocument);
            logger.warning("Documento actual: " + currentDocument.toString());
            
            if (currentDocument.toString().equals(treeArbolUIC.getSelectedRowKeys().toString())) {
                logger.warning("Documento a buscar es el actual.");
            } else {
                // Si se encuentra por lo menos una coincidencia se actualiza el arbol
                if (firstDocument > 0) {
                    treeArbolUIC.setDisclosedRowKeys(disclosedTreeRowKeySet);        
                    
                    // Se selecciona primer documento encontrado en busqueda.
                    selectionEvent = new SelectionEvent(treeArbolUIC.getSelectedRowKeys(), currentDocument, treeArbolUIC);
                    selectionEvent.queue();
                    
                    AdfFacesContext.getCurrentInstance().addPartialTarget(treeArbolUIC);
                    
                    // Se selecciona en arbol primer documento encontrado en busqueda
                    treeArbolUIC.setSelectedRowKeys(currentDocument);
                } else {
                    JSFUtils.addFacesInformationMessage("No se encontraron coincidencias con el nombre del documento.");
                }
            }
        } else {
            logger.warning("El criterio de busqueda es Null.");
        }
    }
    
    public Boolean searchWord(String cadena) {
        logger.warning("Inside searchWord.");
        
        Boolean result = Boolean.FALSE;
        String schCriteria = getSearchCriteria();
        
        if (cadena != null) {
            if (schCriteria != null) {
                if (cadena.toLowerCase().contains(schCriteria.toLowerCase())) {
                    result = Boolean.TRUE;
                    logger.warning("Documento: " + cadena);
                    logger.warning("Concidencia.");
                }
            }    
        } else {
            logger.warning("Cadena la cual buscar es Null.");
        }
        return result;
    }
    
    public Map obtenerDatosDocumento() {
        logger.warning("Inside obtenerDatosDocumento.");

        Map<String, String> valoresDocumentoMap = new HashMap<String, String>();
        DCIteratorBinding voDocumentosAdjuntadosIterator =
            ADFUtils.getDCBindingContainer().findIteratorBinding("DocumentosAdjuntadosClientesVOIterator");
        Row currentDocumentosAdjuntados = voDocumentosAdjuntadosIterator.getCurrentRow();

        logger.warning("voDocumentosAdjuntadosIterator: " + voDocumentosAdjuntadosIterator);
        logger.warning("currentDocumentosAdjuntados: " + currentDocumentosAdjuntados);

        if (currentDocumentosAdjuntados != null) {
            logger.warning("currentDocumentosAdjuntados.getAttribute: " +
                           currentDocumentosAdjuntados.getAttribute("Estado"));
            if (currentDocumentosAdjuntados.getAttribute("Estado") != null) {
                valoresDocumentoMap.put("estadoDocumento", (String) currentDocumentosAdjuntados.getAttribute("Estado"));
            }
        }

        return valoresDocumentoMap;
    }
    
    public void consultarDocumentosValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.warning("Dentro de consultarDocumentosValueChangeListener");
        // Add event code here...
        DocumentosClientesBean documentosClientesBean = null;
        
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}")){
            documentosClientesBean = (DocumentosClientesBean)
                JSFUtils.resolveExpression("#{pageFlowScope.documentosClientesBean}"); 
            try{
                Boolean valor = (Boolean)valueChangeEvent.getNewValue();
                logger.warning("valor :"+valor);
                if(valor){
                    logger.warning("consultar documentos filtrados por la instancia del proceso");
                    documentosClientesBean.inicializarArbolDocumentosPorInstanciaProceso();
                }else{
                    logger.warning("consultar los documentos sin la instancia de proceso");
                    documentosClientesBean.inicializarArbolDocumentos();
                }
            }catch(Exception e){
                logger.warning("Error al recuperar newValue:"+e);
            }
            logger.warning("actualizar tree");
            //actualizar tree
            if (treeArbolUIC.getSelectedRowKeys() != null) {
                treeArbolUIC.getSelectedRowKeys().clear();
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(treeArbolUIC);
        
        }else{
            logger.severe("DocumentosManagedBean no esta definido,no se puede realizar consulta");
        }
        logger.warning("Fuera de consultarDocumentosValueChangeListener");
    }
    
    public void enviarDocumentoClienteActionListener(ActionEvent actionEvent) {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupEnviarDocumento().show(popupHints);
    }
    
    public void cancelarEnviarDocumentoClienteActionListener(ActionEvent actionEvent) {
        getPopupEnviarDocumento().hide();
    }
    
    public void setPopupEnviarDocumento(RichPopup popupEnviarDocumento) {
        this.popupEnviarDocumento = popupEnviarDocumento;
    }

    public RichPopup getPopupEnviarDocumento() {
        return popupEnviarDocumento;
    }

    public void setFormDocumentosUIC(RichPanelGroupLayout formDocumentosUIC) {
        this.formDocumentosUIC = formDocumentosUIC;
    }

    public RichPanelGroupLayout getFormDocumentosUIC() {
        return formDocumentosUIC;
    }
}
