package pa02admoncomisionesght.beans;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.FacesEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTree;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichToolbar;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSet;
import oracle.jbo.ViewObject;

import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

import org.bcie.atributobo.Accion;
import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.view.matriztcc.ComisionManagedBean;
import org.bcie.fenix.view.matriztcc.MatrizTccBean;
import org.bcie.fenix.view.matriztcc.MattrizTccActionsBean;

public class RegistroComisionActionBean {
    
    public static final String BUNDLE = "pa02admoncomisionesght.PA02AdmonComisionesGHTBundle";
    
    public static final ADFLogger logger = ADFLogger.createADFLogger(RegistroComisionActionBean.class);
    
    public static final Integer ID_GESTION_TIPO_VER_COMISION = 5;
    public static final Integer ID_GESTION_TIPO_EDITAR_COMISION = 6;
    public static final Integer TIPO_VALOR_MONTO = 1;
    public static final String KEY_MENSAJE_COMISION_FALSE = "false";
    public static final String KEY_MENSAJE_COMISION_TRUE = "true";
    public static final String KEY_MENSAJE_COMISION_REGISTRADA = "registrada";
    private RichTree comisionTreeUI;
    private RichOutputText otInitForm;
    private RichPopup agregarComisionPopup;
    private RichPopup comisionNoRegistradaPopup;
    private RichToolbar accionesToolbarUI;
    private RichPanelGroupLayout panelRegistroFormUIC;

    public RegistroComisionActionBean() {
        super();
    }
    
    public void treeRowComisionSelectionListener(SelectionEvent selectionEvent) {
        logger.warning("Entra en treeRowSelectionListener.");
        Boolean esEstadoCompletado = Boolean.FALSE;
        ComisionesBean comisionesBean = null;
        try {
            esEstadoCompletado = (Boolean) JSFUtils.resolveExpression("#{pageFlowScope.esEstadoCompletado}");
            Boolean isDirty = ADFUtils.getDCBindingContainer().getDataControl().isTransactionDirty();
            logger.warning("esEstadoCompletado :"+esEstadoCompletado);
            if (isDirty) {
                if(esEstadoCompletado !=null && esEstadoCompletado == false){
                    logger.warning("Cargar solo datos de la tabla,no cargar taskflow secundario");
                    //Inicializa Componentes de Grid por Id de Operacion
                    restaurarSeleccionAnterior();
                }
            } else {
                if(esEstadoCompletado !=null && esEstadoCompletado == false){
                    logger.warning("Cargar solo datos de la tabla,no cargar taskflow secundario");
                // Reasignamos las bind variables de los VOs hijos de TccTreeRootComision
                //reinicializarTree(); // Se tiene que invocar antes del makeCurrent o NO funciona (se pierde el valor del current)
                inicializarTreeTcc();

                // Actualizamos row seleccionado en tree
                JSFUtils.resolveMethodExpression("#{bindings.TccTreeRootComisionVO1.treeModel.makeCurrent}", Object.class, new Class[] {
                                                 SelectionEvent.class }, new Object[] { selectionEvent });

                // Asignamos variables de viewScope: pIdTipoGestionTree y pIdTccTree
                treeRowSelectionListener(selectionEvent);
                logger.warning("Termina metodo correctamente.");
                }
            }
        } catch (Exception e) {
            logger.warning("Error al ejecutar treeRowComisionSelectionListener.", e);
        }
    }
    
    private void restaurarSeleccionAnterior() {
        logger.warning("Entra en restaurarSeleccionAnterior.");
        
        RegistroComisionBean registroComisionBean =
            (RegistroComisionBean) JSFUtils.resolveExpression("#{pageFlowScope.registroComisionBean}");
        
        RichTree comisionTree = null;
        
        if(!registroComisionBean.getEsSelectionEventQueue()) {
            logger.warning("Entra al IF");
            comisionTree = getComisionTreeUI();
            // 2. Regresamos el current row del tree a su estado anterior. Para ello, creamos un nuevo evento de 
            // selección y lo encolamos al tree indicado. Además, reasignamos el selected row en pantalla.         
            SelectionEvent selectionEvent = new SelectionEvent(new RowKeySetImpl() , registroComisionBean.getDisclosedRowKeysTcc(), comisionTree);
            selectionEvent.queue(); //queue event on component, es decir, llama de nuevo al Selectionlistener
            comisionTree.setSelectedRowKeys(registroComisionBean.getDisclosedRowKeysTcc()); // Reasigna (pinta) el selected row en pantalla
            
            // 3. Asigna flag de control 
            registroComisionBean.setEsSelectionEventQueue(Boolean.TRUE); // Con este flag evitamos que entre repetidamente al Selectionlistener para el mismo evento
            //AdfFacesContext.getCurrentInstance().addPartialTarget(tccTree); // PPR the tree (no es necesario puesto que viene de un Selectionlistener del mismo tree)      
        } else {
            logger.warning("Selection NULL carga mensaje");
            // 1. Mostramos mensaje informando que hay cambios pendientes
            JSFUtils.addFacesErrorMessage(getStringFromBundle("transaccion.pendiente.confirmar"));
        }
        logger.warning("Termina restaurarSeleccionAnterior.");
    }
    
    private void treeRowSelectionListener(SelectionEvent selectionEvent) {
        logger.warning("Entra en treeRowSelectionListener");
        
        RegistroComisionBean registroComisionBean =
            (RegistroComisionBean) JSFUtils.resolveExpression("#{pageFlowScope.registroComisionBean}");
        
        Boolean isDirty = ADFUtils.getDCBindingContainer().getDataControl().isTransactionDirty();
        Boolean esEditar = null;
        RichTree tree = null;
        RowKeySet rowKeySet = null;
        Iterator rksIterator = null;
        
        oracle.jbo.domain.Number idTcc = null;
        Integer idTipoGestion = null;
                
        // Asignación de variables
        esEditar = Boolean.FALSE; //Para visualizar las pantallas de Ver al seleccionar un elemento en tree
        
        tree = (RichTree)selectionEvent.getSource(); // get the tree component from the event
        rowKeySet = selectionEvent.getAddedSet(); //get selected nodes
        rksIterator = rowKeySet.iterator();
        
        //Validating for single select only. Need to check for multiselect
        while (rksIterator.hasNext()) {
            logger.warning("Entra en el while.");
            List key = (List)rksIterator.next();
            JUCtrlHierBinding treeBinding = null;
            CollectionModel collectionModel = (CollectionModel)tree.getValue();
            treeBinding = (JUCtrlHierBinding)collectionModel.getWrappedData();           
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinding.findNodeByKeyPath(key);
            Row row = null;
            if(nodeBinding != null){
                row = nodeBinding.getRow();
                if(row != null){
                    
                    List<String> atributos = Arrays.asList(row.getAttributeNames());
                    
                   if (atributos.contains("Tipo") && row.getAttribute("Tipo") != null && row.getAttribute("Tipo").equals("COMISION")) {
                       logger.warning("Entra a asignar valores de view scope.");
                        logger.warning("Valor del tipo : " + row.getAttribute("Tipo"));
                        logger.warning("Valor atributos : " + atributos.contains("Tipo"));
//                        ADFContext.getCurrent().getViewScope().put("pIdTipoGestionTree", obtenerIdTipoGestion(3, esEditar));
//                        ADFContext.getCurrent().getViewScope().put("pIdTccTree", new Number(Long.valueOf(row.getAttribute("IdComision").toString())));
                       idTipoGestion = obtenerIdTipoGestion(3, esEditar);
                       idTcc = new Number(Long.valueOf(row.getAttribute("IdComision").toString()));
                       registroComisionBean.setPIdTipoGestion(idTipoGestion);
                        registroComisionBean.setIdTccTree(idTcc);
                    } else{
                       logger.warning("Se ha seleccionado el padre.");
                        // Se seleccionó un nodo padre, es decir NO es una Comisión
//                        ADFContext.getCurrent().getViewScope().put("pIdTipoGestionTree", null); 
//                        ADFContext.getCurrent().getViewScope().put("pIdTccTree", null); 
                        registroComisionBean.setPIdTipoGestion(null);
                        registroComisionBean.setIdTccTree(null);
                    }
                   
                   logger.warning("Valor de pIdTipoGestionTree : " + registroComisionBean.getPIdTipoGestion());
                    logger.warning("Valor de pIdTccTree : " + registroComisionBean.getIdTccTree());
                    
                    if(!isDirty) {
                        RowKeySetImpl rksNew = new RowKeySetImpl();
                        rksNew.add(key);
                        registroComisionBean.setDisclosedRowKeysTcc(rksNew);
                        registroComisionBean.setEsSelectionEventQueue(Boolean.FALSE); // limpiamos flag de control de entrada infinita al Selectionlistener
                    }
                }
            }
        }
    }
    
    public Integer obtenerIdTipoGestion(Integer idLinkTipoTcc, 
                                        boolean editar){
        logger.warning("Entra en obtenerIdTipoGestion");
        Integer idGestionTipo = null;
        logger.warning("Es editar : " + editar);
        if(idLinkTipoTcc != null){
            
            switch(idLinkTipoTcc){
                case 3:
                    idGestionTipo = editar ? ID_GESTION_TIPO_EDITAR_COMISION : ID_GESTION_TIPO_VER_COMISION;
                break;
                default:
                    idGestionTipo = null;
                break;
            }
        }
        logger.warning("Valor del tipo de gestion : " + idGestionTipo);
        return idGestionTipo;
    }
   
   /**
    * Se utiliza metodo igual al de matrizTcc
    * Metodo inicializarTreeTcc
    */
    private void inicializarTreeTcc() {
        logger.warning("Entra en inicializarTreeTcc.");
        Long idOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
        DCIteratorBinding treeRootIteratorVO = null;
        ViewObject treeRootVO = null;
        ViewObject tcaTipoVO = null;
        ViewObject tcaVO = null;
        try{
            logger.warning("Valor de la operacion : " + idOperacion);
            // Asignación de variables
            treeRootIteratorVO = ADFUtils.findIterator("TccTreeRootComisionVOIterator");
            treeRootVO = treeRootIteratorVO.getViewObject();
            
            // Obtenemos los child VOs via the parent view object binding
            tcaTipoVO = ADFUtils.getChildViewObjectFromParent(treeRootVO, "TccTreeTcaTipoComisionVO");
            tcaVO =  ADFUtils.getChildViewObjectFromParent(tcaTipoVO, "TccTreeTcaComisionVO");
            
            // Asignamos los bind variable a todos los VOs hijos de Comisiones
            tcaTipoVO.ensureVariableManager().setVariableValue("varIdOperacionTcaTipoComision", idOperacion);        
            tcaVO.ensureVariableManager().setVariableValue("varIdOperacionTcaComision", idOperacion);
            
            // Re-ejecutamos los queries de los padres
            treeRootIteratorVO.executeQuery();
            
            this.expandirTodoArbol();// Mandar abrir todos los nodos
        }catch(Exception e){
            logger.warning("Error en inicializarTreeTcc.", e);
        }
    }
    
    private void expandirTodoArbol() {
        logger.warning("Entra en expandirTodoArbol.");
        RichTree rt = getComisionTreeUI();
        try{
            if (rt != null) {
                int rowCount = rt.getRowCount();
                List<Key> rowKey;
                for (int j = 0; j < rowCount; j++) {
                    // expand the main nodes
                    FacesCtrlHierNodeBinding node = (FacesCtrlHierNodeBinding) rt.getRowData(j);
                    rowKey = new ArrayList<Key>();
                    rowKey.add(node.getRowKey());
                    rt.getDisclosedRowKeys().add(rowKey);
                    rt.setRowKey(rowKey);
                    // expand the child nodes of the main nodes
                    expandTreeChildrenNode(rt, node, rowKey);
                }
            }else{
                logger.warning("No se obtuvo el compo");
            }
        }catch(Exception e){
            logger.warning("Error en expandirTodoArbol.", e);
        }
    }
    
    private void expandTreeChildrenNode(RichTree rt, FacesCtrlHierNodeBinding node, List<Key> parentRowKey) {
        logger.warning("Entra en expandTreeChildrenNode.");
        ArrayList children = node.getChildren();
        List<Key> rowKey;
        try{
            if (children != null) {
                for (int i = 0; i < children.size(); i++) {
                    rowKey = new ArrayList<Key>();
                    rowKey.addAll(parentRowKey);
                    rowKey.add(((FacesCtrlHierNodeBinding) children.get(i)).getRowKey());
                    rt.getDisclosedRowKeys().add(rowKey);
                    if (((FacesCtrlHierNodeBinding) (children.get(i))).getChildren() == null)
                        continue;
                    expandTreeChildrenNode(rt, (FacesCtrlHierNodeBinding) (node.getChildren().get(i)), rowKey);
                }
            }
        }catch(Exception e){
            logger.warning("Error en expandTreeChildrenNode.", e);
        }
    }

    public void agregarComisionActionListener(ActionEvent actionEvent) {
        logger.warning("Entra agregarComisionActionListener: " + actionEvent.getComponent().getId());
        Long idOperacion = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        RichPopup.PopupHints popupHints = null;
        try{
            RegistroComisionBean registroComisionBean =
                (RegistroComisionBean) JSFUtils.resolveExpression("#{pageFlowScope.registroComisionBean}");
            
            if(null != registroComisionBean.getIdOperacion()){
                idOperacion = registroComisionBean.getIdOperacion();
            }else{
                logger.warning("La operacion es nula.");
            }
            
            operationBinding = bindings.getOperationBinding("inicializarAgregarComision");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
            popupHints = new RichPopup.PopupHints();
            getAgregarComisionPopup().show(popupHints);
            logger.warning("Termina metodo agregarComisionActionListener correctamente.");
        }catch(Exception e){
            logger.warning("Error en agregarComisionActionListener.", e);
        }
    }
    
    public void aceptarAgregarComisionActionListener(ActionEvent actionEvent) {
        logger.warning("Entra en aceptarAgregarComisionActionListener: " + actionEvent.getComponent().getId());
        Long idOperacion = null;
        Integer idTcaTcc = null;
        String nombreTcc = null;
        String concatTcas = null;
        Long idTccCurrentRow = null;
        ExtendedRenderKitService erks = null;
        try{
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")){
                idOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
            }else{
                logger.warning("El valor de la operacion es nula.");
            }
            if(null != JSFUtils.resolveExpression("#{bindings.IdTcaComision.inputValue}")){
                idTcaTcc = (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaComision.inputValue}");
            }else{
                logger.warning("El id Tca de la comision es nula.");
            }
            if(null != JSFUtils.resolveExpression("#{bindings.ConcatTcasComision.inputValue}")){
                concatTcas = (String)JSFUtils.resolveExpression("#{bindings.ConcatTcasComision.inputValue}");
                nombreTcc = concatTcas.replace("/", "-");
                logger.warning("Nombre de la Comisión: " + nombreTcc);
            }else{
                logger.warning("El valor de la concatenacion es nula.");
            }
            if(null != ADFContext.getCurrent().getViewScope().get("pIdTccTree")){
                idTccCurrentRow = Long.valueOf(ADFContext.getCurrent().getViewScope().get("pIdTccTree").toString());
            }else{
                logger.warning("El valor del pIdTccTree es nulo");
            }
            crearComision(idOperacion, nombreTcc, idTcaTcc, FenixConstants.ESTADO_TCC_NUEVA, FenixConstants.SUBESTADO_COMISION_CREADA , idTccCurrentRow);
            
            // Reiniciamos Tree
            refreshTccTree("TccTreeRootComisionVOIterator", Integer.valueOf(3), "TccTreeTcaTipoComisionVO");

            // Cerramos popup - agregar para el tree
            logger.warning("Cerrando popup del tree");
            getAgregarComisionPopup().hide();
            
        }catch(Exception e){
            logger.warning("Error al agregar la comision.", e);
        }
        logger.warning("Termina metodo de agregar la comision correctamente.");
    }
    
    private void refreshTccTree(String rootIteratorName, Integer idLink, String accessorName) {
        DCIteratorBinding treeRootIteratorVO = null;
        ViewObject treeRootVO = null;
        
        inicializarTreeTcc();
        
        // Asignación de variables
        treeRootIteratorVO = ADFUtils.findIterator(rootIteratorName);
        treeRootVO = treeRootIteratorVO.getViewObject(); // Get Master ViewObject
                
        // Filtramos el VO padre por su Key Attribute "IdLink" (3 = Comisión)
        Row[] rootTccRows = treeRootVO.findByKey(new Key(new Object[] { idLink }), 1);  
        
        // Realizamos el executeQuery de los hijos
        if(rootTccRows.length>0) {
            // Get Child Rows using ViewLink Accessor. 
            // "accessorName" es el ViewLinkAccessor name definido en el VO padre (ej. TccTreeTcaTipoTerminoVO para el
            // padre TccTreeRootTerminoVO).
            RowSet childRows = (RowSet)rootTccRows[0].getAttribute(accessorName);  
            childRows.executeQuery(); // Execute Child Rowset 
        }
    }
    
    private void crearComision(Long idOperacion, String nombreTcc, Integer idTcaTcc, Integer idTcaEstadoTcc, 
                          Integer idTcaSubEstadoTcc, Long idTccCurrentRow) {
        logger.warning("Entra en crearComision.");
        
        logger.warning("idOperacion : " + idOperacion);
        logger.warning("nombreTcc : " + nombreTcc);
        logger.warning("idTcaTcc : " + idTcaTcc);
        logger.warning("idTcaEstadoTcc : " + idTcaEstadoTcc);
        logger.warning("idTcaSubEstadoTcc : " + idTcaSubEstadoTcc);
        logger.warning("idTccCurrentRow : " + idTccCurrentRow);
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        try{
            // Ejecutamos método para crear la comision
            operationBinding = bindings.getOperationBinding("crearRegistroComision");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("nombreTcc", nombreTcc);
            operationBinding.getParamsMap().put("idTcaTcc", idTcaTcc);
            operationBinding.getParamsMap().put("idTcaEstadoTcc", idTcaEstadoTcc);
            operationBinding.getParamsMap().put("idTcaSubEstadoTcc", idTcaSubEstadoTcc);
            operationBinding.getParamsMap().put("idTccCurrentRow", idTccCurrentRow);
            
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }else{ 
                if((operationBinding.getResult() != null) && ((Boolean)operationBinding.getResult())){
                    logger.warning("Comision creada exitosamente");
                }else{
                    JSFUtils.addFacesErrorMessage("Error al crear la comisión.");
                }
            }
        }catch(Exception e){
            logger.warning("Error en crearComision", e);
        }
    }
    
    public void agregarComisionNoRegistradaActionListener(ActionEvent actionEvent) {
        logger.warning("Entra en agregarComisionNoRegistradaActionListener.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        List<Row> comisiones = null;
        Long idOperacion = null;
        Boolean listaLlena = Boolean.FALSE;
        try{
            RegistroComisionBean registroComisionBean =
                (RegistroComisionBean) JSFUtils.resolveExpression("#{pageFlowScope.registroComisionBean}");

            if (null != registroComisionBean.getIdOperacion()) {
                idOperacion = registroComisionBean.getIdOperacion();
            } else {
                logger.warning("La operacion es nula.");
            }
            operationBinding = bindings.getOperationBinding("obtenerListaComision");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.execute();
            
            if(null != operationBinding.getResult()){
                comisiones = (List<Row>)operationBinding.getResult();
                logger.warning("Lista de comisiones : " + comisiones.size());
                listaLlena = llenarListaComision(comisiones);
                if(listaLlena){
                    logger.warning("La lista se lleno correctamente");
                }else{
                    logger.warning("No fue posible llenar la lista de comisiones.");
                }
            }else{
                logger.warning("No se obtuvo resultado del metodo.");
            }
        }catch(Exception e){
            logger.warning("Error en agregarComisionNoRegistradaActionListener");
        }
        RichPopup.PopupHints popupHints = null;
        popupHints = new RichPopup.PopupHints();
        getComisionNoRegistradaPopup().show(popupHints);
    }
    
    private Boolean llenarListaComision(List<Row> comisiones){
        logger.warning("Entra en llenarListaComision.");
        Boolean listaLlena = Boolean.FALSE;
        List<SelectItem> llenaLista = new ArrayList<SelectItem>();
        try{
            
            RegistroComisionBean registroComisionBean =
                (RegistroComisionBean) JSFUtils.resolveExpression("#{pageFlowScope.registroComisionBean}");
            
            for(Row row : comisiones){
                if(null != row.getAttribute("IdComision")){
                    llenaLista.add(new SelectItem(row.getAttribute("IdComision"), (String)row.getAttribute("ConcatTcasComision")));
                }
            }
            logger.warning("Tamaño de la lista : " + llenaLista.size());
            if(llenaLista.size() > 0){
                registroComisionBean.setListaComsiones(llenaLista);
                listaLlena = Boolean.TRUE;
            }
        }catch(Exception e){
            logger.warning("Error en llenarListaComision.", e);
        }
        return listaLlena;
    }
    
    public void aceptarAgregarComisionNoRegistradaActionListener(ActionEvent actionEvent) {
        logger.warning("Entra en aceptarAgregarComisionNoRegistradaActionListener.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        List<SelectItem> lista = new ArrayList<SelectItem>();
        Long idComision = null;
        Boolean comisionActualizada = Boolean.FALSE;
        try{
            RegistroComisionBean registroComisionBean =
                (RegistroComisionBean) JSFUtils.resolveExpression("#{pageFlowScope.registroComisionBean}");
            
            idComision = registroComisionBean.getIdComision();
            logger.warning("ID de la comision : " + idComision);
            if(null != idComision){
                comisionActualizada = actualizarSubEstadoComision(idComision, FenixConstants.SUBESTADO_COMISION_EN_REGISTRO);
            }
            refreshTccTree("TccTreeRootComisionVOIterator", Integer.valueOf(3), "TccTreeTcaTipoComisionVO");
        }catch(Exception e){
            logger.warning("Error en aceptarAgregarComisionNoRegistradaActionListener");
        }
        getComisionNoRegistradaPopup().hide();
    }
    
    public Boolean actualizarSubEstadoComision(Long idComision, Integer idTcaSubEstado){
        logger.warning("Entra en actualizarSubEstadoComision.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Boolean comisionActualizada = Boolean.FALSE;
        try {
            
            operationBinding = bindings.getOperationBinding("actualizarSubEstadoComision");
            operationBinding.getParamsMap().put("idComision", idComision);
            operationBinding.getParamsMap().put("idTcaSubEstadoTcc", idTcaSubEstado);
            operationBinding.execute();

            if (null != operationBinding.getResult()) {
                comisionActualizada = (Boolean) operationBinding.getResult();
                if (comisionActualizada) {
                    logger.warning("La comision se actualizo correctamente.");
                } else {
                    logger.warning("Error al actualizar la comision.");
                }
            }
        } catch (Exception e) {
            logger.warning("Error en actualizarSubEstadoComision", e);
        }
        return comisionActualizada;
    }
    public void activarFechaFlexcube(ActionEvent actionEvent) {
        logger.warning("Entra en activarFechaFlexcube.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        try{
            operationBinding = bindings.getOperationBinding("obtenerFechaFlexcube");
            oracle.jbo.domain.Timestamp result = (oracle.jbo.domain.Timestamp) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("Error");
                JSFUtils.addFacesErrorMessage("Error al obtener la fecha Flexcube");
            } else {
                GestionComisionBean gestionComisionBean =
                    (GestionComisionBean) JSFUtils.resolveExpression("#{pageFlowScope.gestionComisionBean}");
                gestionComisionBean.setFechaFlexcubeActual(result);
            }
        }catch(Exception e){
            logger.warning("Error en activarFechaFlexcube.", e);
        }
    }
    
    public Boolean getEsTipoValor() {
        Boolean respuesta = true;
        AttributeBinding tipoValorTasa;
        tipoValorTasa = ADFUtils.findControlBinding("IdTcaTipoTasa");
        Integer value = 0;
        if (null != tipoValorTasa.getInputValue()) {
            value = (Integer) tipoValorTasa.getInputValue();
            if (TIPO_VALOR_MONTO.equals(value)) {
                respuesta = false;
            }
        }
        return respuesta;
    }
    
    public String getEsTextoComision() {
        String respuesta = "Monto comisión:";
        AttributeBinding tipoValorTasa;
        tipoValorTasa = ADFUtils.findControlBinding("IdTcaTipoTasa");
        Integer value = 0;
        if (null != tipoValorTasa.getInputValue()) {
            value = (Integer) tipoValorTasa.getInputValue();
            if (TIPO_VALOR_MONTO.equals(value)) {
                respuesta = "Valor:";
            }
        }
        return respuesta;
    }
    
    public void aceptarGuardarComisionActionListener(ActionEvent actionEvent) {
        logger.warning("Entra en aceptarGuardarComisionActionListener.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        try{
            if (comisionValida()) {
                operationBinding = bindings.getOperationBinding("guardarEditarComisionMatrizTCC");
                operationBinding.execute();

                if (operationBinding.getErrors().isEmpty()) {
                    reiniciarComisionModel();

                    navigate(actionEvent, "retornaVerComision");
                } else {
                    reiniciarComisionModel();
                }
                this.refreshTccTree("TccTreeRootComisionVOIterator", Integer.valueOf(3), "TccTreeTcaTipoComisionVO");
            }else{
                logger.warning("La comision no fue validada.");
            }
        }catch(Exception e){
            logger.warning("Error en aceptarGuardarComisionActionListener.", e);
        }
            
                
    }
    
    public void aceptarCancelarEditarComisionActionListener(ActionEvent actionEvent) {
        logger.warning("Entra en aceptarCancelarEditarComisionActionListener.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try{
            //Codigo para cancelar los cambios realizados
            operationBinding = bindings.getOperationBinding("Rollback");
            operationBinding.execute();

            reiniciarComisionModel();
            this.refreshTccTree("TccTreeRootComisionVOIterator", Integer.valueOf(3), "TccTreeTcaTipoComisionVO");
            navigate(actionEvent, "retornaVerComision");
        }catch(Exception e){
            logger.warning("Error en aceptarCancelarEditarComisionActionListener.", e);
        }
    }

    public void aceptarEliminarComisionActionListener(ActionEvent actionEvent) {
        logger.warning("Entra em aceptarEliminarComisionActionListener.");

        Boolean esExitoEliminarComision = Boolean.TRUE;
        //                Integer idProcesoBpm = null;
        String strIdTcc = null;
        Long idComision = null;
        try {
            //                    idProcesoBpm = (Integer) ADFUtils.getBoundAttributeValue("IdProcesoBpm");
        } catch (Exception e) {
            logger.warning("Error al obtener el valor del Atributo Id Proceso BPM de Bindings", e);
        }

        Integer idEstadoTcc = null;
        try {
            //Obtiene el Id de Estado TCC
            if (ADFUtils.findControlBinding("IdTcaEstadoTcc") != null) {
                idEstadoTcc = (Integer) ADFUtils.getBoundAttributeValue("IdTcaEstadoTcc");
            }
        } catch (Exception e) {
            logger.warning("No se obtuvo Atributo Id de Estado TCC");
        }

        Integer idSubEstadoTcc = null;
        try {
            //Obtiene el Id de Sub Estado TCC
            if (ADFUtils.findControlBinding("IdTcaSubEstadoTcc") != null) {
                idSubEstadoTcc = (Integer) ADFUtils.getBoundAttributeValue("IdTcaSubEstadoTcc");
            }
        } catch (Exception e) {
            logger.warning("No se obtuvo Atributo Id de Sub Estado TCC");
        }
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pIdTreeComision}") != null) {
                strIdTcc = JSFUtils.resolveExpression("#{pageFlowScope.pIdTreeComision}").toString();
                logger.warning("Numero de la comision : " + strIdTcc);
                idComision = Long.valueOf(strIdTcc);
            } else {
                logger.warning("El id de la comision es nula.");
            }
        } catch (Exception e) {
            logger.warning("Error al obtener el id de la comision seleccionada.", e);
        }

        //                if(idProcesoBpm != null){
        if (FenixConstants.ESTADO_TCC_NUEVA.compareTo(idEstadoTcc) == 0) {
            logger.warning("Llama método de eliminación física de Comisión");
            esExitoEliminarComision = eliminarComision();
        } else {
            // Actualizamos el estado de la Comisión mediante el servicio actualizarTCC
            esExitoEliminarComision = actualizarSubEstadoComision(idComision, null);
        }
        //                }

        if (esExitoEliminarComision) {
            //Reinicia el Modelo de la comision
            reiniciarComisionModel();

            // Reentramos al flujo de gestión para recargar los datos
            navigate(actionEvent, "goReturn");
        } else {
            logger.warning("Error al eliminar la comision.");
        }
        this.refreshTccTree("TccTreeRootComisionVOIterator", Integer.valueOf(3), "TccTreeTcaTipoComisionVO");
    }
    
    /**
     * Realiza la eliminación física de la Comisión
     */
    public Boolean eliminarComision(){
        logger.warning("Entra en eliminarComision");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Boolean exito = Boolean.FALSE;
        try{
            operationBinding = bindings.getOperationBinding("eliminarComisionActual");
            operationBinding.execute();
            
            if((operationBinding != null) && operationBinding.getErrors().isEmpty()){ 
                logger.warning("Ejecuta metodo eliminarComisionActual correctamente.");
                exito = Boolean.TRUE;
            }else{
                logger.warning("Muestra los errores al ejecutar el Operator Bindings: " + 
                              operationBinding.getErrors().toString());
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
            
            if(!exito){
                logger.warning("Muestra mensaje de eliminacion fallida de la Comision");
                String msg = getStringFromBundle("ERROR_ELIMINAR_COMISION");
                JSFUtils.addFacesErrorMessage(msg);
            }
        }catch(Exception e){
            logger.warning("Error al ejecutar eliminarComisionActual.", e);
        }        
        return exito;
    }
    
    public Boolean actualizarEstadoComision(Accion accion){
        logger.warning("Entra en actualizarEstadoComision");
        String msg = null;
        String keyMsg = null;
        Boolean esError = Boolean.FALSE;
        
        Integer idProceso = null;
        try{
            idProceso = (Integer) ADFUtils.getBoundAttributeValue("IdProcesoBpm");
        }catch(Exception e){
            logger.warning("Error al obtener el Id del Proceso BPM", e);
        }
        try{
            OperationBinding oper = actualizarEstadoComision(accion, idProceso, FenixModelConstants.COMISION);
            
            if(oper != null){
                
                if(oper.getErrors().isEmpty()){
                    
                    Boolean result = null;
                    result = (Boolean) oper.getResult();
                    if(result != null){
                        
                        if(result){
                            logger.warning("El Estado TCC de la Comision fue actualizado correctamente");
                            //keyMsg = "tcc.ver.comision.actualizar.estado.tcc.msg.exito";
                        }else{
                            logger.warning("Error el Estado TCC de la Comision no fue actualizado");    
                            esError = Boolean.TRUE;
                            keyMsg = "ERROR_ESTADO_COMISION_SIN_ACTUALIZAR";
                        }
                    }else{
                        logger.warning("Error el resultado obtenido no definido");
                        esError = Boolean.TRUE;    
                        keyMsg = "ERROR_ESTADO_COMISION_SIN_ACTUALIZAR";
                    }
                }else{
                    logger.warning("Error el Estado TCC de la Comision no fue actualizado");
                    esError = Boolean.TRUE;
                    keyMsg = "ERROR_ESTADO_COMISION_SIN_ACTUALIZAR";
                }
            }else{
                logger.warning("El operation binding es nulo.");
            }
            if(keyMsg != null){
                logger.warning("Obtiene mensaje para mostrar");
                msg = getStringFromBundle(keyMsg);
            }
            
            if(esError){
                logger.warning("Muestra mensaje de error");
                JSFUtils.addFacesErrorMessage(msg);
            }else{
                logger.warning("Muestra mensaje de exito");
                //JSFUtils.addFacesInformationMessage(msg);
            }
        }catch(Exception e){
            logger.warning("Error en actualizarEstadoComision.", e);
        }        
        return !esError;
    }
    
    public OperationBinding actualizarEstadoComision(Accion accion, Integer idProcesoBpm, String tipoTcc) {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        try{
            if (accion != null && idProcesoBpm != null && tipoTcc != null) {
                operationBinding = bindings.getOperationBinding("actualizarComisionAccionesMatrizTCC");
                operationBinding.getParamsMap().put("sIdProceso", idProcesoBpm.toString());
                operationBinding.getParamsMap().put("sTipoTCC", tipoTcc.toString());
                operationBinding.getParamsMap().put("sAccion", accion.toString());
                
                operationBinding.execute(); 
            }else {
            logger.warning("Parametros requeridos no recibidos");
            }
        }catch(Exception e){
            logger.warning("Error en actualizarEstadoComision.", e);
        }
        return operationBinding;
    }
    
    private Boolean comisionValida() {
        logger.warning("Entra en comisionValida.");
        Boolean valida = true;
        String msg="";               
        GestionComisionBean gestionComisionBean = null;
        
        AttributeBinding idTcaTasa= null;
        AttributeBinding porcentaje= null;      
        AttributeBinding montoComision = null;
        AttributeBinding tipoMontoBase = null;
        AttributeBinding montoBase = null;
        AttributeBinding idTipoFrecuencia = null;
        AttributeBinding frecuenciaPago = null;
        
        AttributeBinding fechaValor = null;
        AttributeBinding fechaInicioCapital = null;
        AttributeBinding fechaVencimiento = null;
        
        Timestamp valorFecha = null;
        Timestamp inicioCapitalFecha = null;
        Timestamp vencimientoFecha = null;
        Timestamp flexcubeFecha=null;
        Integer valorIdTasa = null;
        
        logger.warning("Validando campos comision");
        try{
            
            gestionComisionBean = (GestionComisionBean)JSFUtils.resolveExpression("#{pageFlowScope.gestionComisionBean}");
            
            idTcaTasa= ADFUtils.findControlBinding("IdTcaTipoTasa");
            porcentaje= ADFUtils.findControlBinding("PorcentajeSobreMontoBase");      
            montoComision = ADFUtils.findControlBinding("MontoComision");
            tipoMontoBase = ADFUtils.findControlBinding("IdTcaMontoBase");
            montoBase = ADFUtils.findControlBinding("MontoBase");
            idTipoFrecuencia = ADFUtils.findControlBinding("IdTcaTipoFrecuencia");
            frecuenciaPago = ADFUtils.findControlBinding("FrecuenciaPago");
            
            fechaValor = ADFUtils.findControlBinding("FechaValor");
            fechaInicioCapital = ADFUtils.findControlBinding("FechaInicioCapital");
            fechaVencimiento = ADFUtils.findControlBinding("FechaVencimiento");
            
            valorFecha=(Timestamp)fechaValor.getInputValue();
            inicioCapitalFecha=(Timestamp)fechaInicioCapital.getInputValue();
        }catch(Exception e){
            logger.warning("Error al obtener los campos de la comision.", e);
        }
        if(null!=fechaVencimiento.getInputValue()){
        vencimientoFecha=(Timestamp)fechaVencimiento.getInputValue();
        }else{
            logger.warning("Lafecha de vencimiento es nula.");
        }
        try {
            flexcubeFecha = gestionComisionBean.getFechaFlexcubeActual().timestampValue();
        } catch (SQLException e) {
            logger.warning("La fecha flexcube es nula.", e);
        }
    
        logger.warning("Valida tasa");
        if (null != idTcaTasa.getInputValue()) {
            
            try {
                logger.warning("Valor Id TCA Tasa: " + idTcaTasa.getInputValue().toString());
                valorIdTasa = Integer.parseInt(idTcaTasa.getInputValue().toString());
            } catch (Exception e) {
                logger.severe("Error al obtener y convertir valor Id TCA Tasa", e);
            }
            
            if (valorIdTasa != null && valorIdTasa.compareTo(FenixConstants.COMISION_TASA) == 0) {

                if (null != porcentaje.getInputValue()) {

                    BigDecimal valorPorcentaje = null;
                    try {
                        //valorPorcentaje = (BigDecimal) porcentaje.getInputValue();
                        valorPorcentaje = BigDecimal.valueOf(Long.parseLong(porcentaje.getInputValue().toString()));
                    } catch (Exception e) {
                        logger.severe("Error al obtener y convertir valor de PorcentajeSobreMontoBase", e);
                    }


                    if (null == porcentaje ||
                        (valorPorcentaje.doubleValue() <= 0 || valorPorcentaje.doubleValue() >= 100)) {

                        msg = getStringFromBundle("MSG_ERROR_PORCENTAJE_SOBREPASA");
                        JSFUtils.addFacesErrorMessage(msg);
                        valida = false;
                    }

                } else {
                    msg = getStringFromBundle("MSG_ERROR_PORCENTAJE_VACIO");
                    JSFUtils.addFacesErrorMessage(msg);
                    valida = false;
                }

            }else{
                logger.warning("La tasa es nula o no es comision de tipo tasa.");
            }
            if (valorIdTasa == null) {
                logger.warning("El valor IdTcaTipoTasa es NULL");
                msg = getStringFromBundle("MSG_ERROR_COMISION");
                JSFUtils.addFacesErrorMessage(msg);
                valida = false;
            } else {
                if (null == montoComision.getInputValue() &&
                    (null == tipoMontoBase.getInputValue() ||
                     valorIdTasa.compareTo(FenixConstants.COMISION_MONTO) == 0)) {
                    msg = getStringFromBundle("MSG_ERROR_COMISION");
                    JSFUtils.addFacesErrorMessage(msg);
                    valida = false;
                }
            }
        }
       else{
       msg = getStringFromBundle("MSG_ERROR_ID_TASA");
       JSFUtils.addFacesErrorMessage(msg);
        valida = false;
        }
                        
        if(null!=idTipoFrecuencia.getInputValue()){
           Integer valorTipoFrecuencia=(Integer) idTipoFrecuencia.getInputValue();
           Integer frecuenciaVencimiento=4;
              
               if(valorTipoFrecuencia.compareTo(frecuenciaVencimiento)!=0){
                        if(null==frecuenciaPago.getInputValue() || 
                        (Integer.valueOf(frecuenciaPago.getInputValue().toString()).compareTo(0) == 0)){
                        msg = getStringFromBundle("MSG_ERROR_FRECUENCUA");
                        JSFUtils.addFacesErrorMessage(msg);
                        valida = false;
                        }
                }
        }
        else{
        msg = getStringFromBundle("MSG_ERROR_ID_FRECUENCUA");
       JSFUtils.addFacesErrorMessage(msg);
       valida = false;
      }  
        logger.warning("Validando fecha vencimiento:" + vencimientoFecha);
        logger.warning("Validando fecha valor:" + valorFecha);
        logger.warning("Validando fecha capital:" + inicioCapitalFecha);
        logger.warning("Validando fecha flexcube:" + flexcubeFecha);
        
        logger.warning("Valida comision : " + valida);
          
            return valida;
    }
    
    public void reiniciarComisionModel(){
        logger.warning("Entra en reiniciarComisionModel"); 
     
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String strIdOperacion = null;
        oracle.jbo.domain.Number idOperacion = null;

        try{
            
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null){
                strIdOperacion = JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString();
                idOperacion = new oracle.jbo.domain.Number(strIdOperacion);
            }else{
                logger.warning("Error el Id de Operacion es NULL");
            }
            
        }catch(Exception e){
            logger.warning("Error al obtener el Id de Operacion", e);
        }
        try{
            if (idOperacion != null) {
                logger.warning("Ejecuta inicializacion de Comision Tree");
                operationBinding = bindings.getOperationBinding("setvarIdOperacionComision");
                operationBinding.getParamsMap().put("value", idOperacion.longValue());
                operationBinding.execute();
            }
        }catch(Exception e){
            logger.warning("Error al ejecutar el tree.", e);
        }
    }
    
    public void cambioTasaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.warning("Entra en cambioTasaValueChangeListener.");
        try{
            AttributeBinding montoBase;
            montoBase = ADFUtils.findControlBinding("MontoBase");
            AttributeBinding montoComision;
            montoComision = ADFUtils.findControlBinding("MontoComision");
            AttributeBinding idMontoBase;
            idMontoBase = ADFUtils.findControlBinding("IdTcaMontoBase");
            AttributeBinding porcentajeBase;
            porcentajeBase = ADFUtils.findControlBinding("PorcentajeSobreMontoBase");

            Integer valorNulo = null;
            montoBase.setInputValue(null);
            porcentajeBase.setInputValue(null);
            montoComision.setInputValue(null);
            idMontoBase.setInputValue(valorNulo);
        }catch(Exception e){
            logger.warning("arg0");
        }
    }

    public void cambioValorTasaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.warning("Entra en cambioValorTasaValueChangeListener.");
        logger.warning("Porcentaje: "+ valueChangeEvent.getNewValue());
        AttributeBinding montoComision = ADFUtils.findControlBinding("MontoComision");
        if(null != valueChangeEvent && null != valueChangeEvent.getNewValue()) {
            BigDecimal nuevoValor = (BigDecimal)valueChangeEvent.getNewValue();
            if(nuevoValor.doubleValue() <= 0 || nuevoValor.doubleValue() >100) {
                montoComision.setInputValue(BigDecimal.ZERO);
                JSFUtils.addFacesErrorMessage(getStringFromBundle("COMISION_MSG_PORCENTAJE"));
            } else {
                //preparacion.setPorcentajeBase(nuevoValor);
                AttributeBinding porcentajeBase;
                porcentajeBase = ADFUtils.findControlBinding("PorcentajeSobreMontoBase");
                porcentajeBase.setInputValue(nuevoValor);
            }
        }
        montoComision.setInputValue(calculaComision());
    }
    
    private BigDecimal calculaComision() {
    logger.warning("Entra en calculaComision");

    AttributeBinding montoBase = ADFUtils.findControlBinding("MontoBase");
    AttributeBinding porcentajeBase= ADFUtils.findControlBinding("PorcentajeSobreMontoBase");

    logger.warning("Monto base: "+ montoBase.getInputValue());
    logger.warning("Porcentaje base: "+porcentajeBase.getInputValue());

    BigDecimal montoComision = new BigDecimal(0);
    BigDecimal porcentaje = new BigDecimal("100");
    if(null != porcentajeBase.getInputValue() && !"".equalsIgnoreCase(porcentajeBase.getInputValue().toString())){
        BigDecimal porcentajeComision = (BigDecimal)porcentajeBase.getInputValue();
    if(porcentajeComision.doubleValue() >0 && porcentajeComision.doubleValue()<100L) {
        if(null != montoBase && null != montoBase.getInputValue()) {
            BigDecimal monto = (BigDecimal)montoBase.getInputValue();
            if(monto.doubleValue() >0.00) {
                logger.warning("porcentaje:"+ porcentajeComision);
                monto = monto.multiply(porcentajeComision);
                montoComision = monto.divide(porcentaje);
            }
        }
    }
    }
    logger.warning("Monto comision calculado: " + montoComision);
    return montoComision.setScale(2, RoundingMode.CEILING);
    }
    
    public void cambioMontoBaseValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.warning("Entra en cambioMontoBaseValueChangeListener.");
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Integer monedaDefault=1;
        AttributeBinding idMoneda;
        idMoneda = ADFUtils.findControlBinding("IdTcaMoneda");
        idMoneda.setInputValue(monedaDefault);
        
        AttributeBinding idMontoBaseComision;
        idMontoBaseComision = ADFUtils.findControlBinding("IdTcaMontoBase");
        
        AttributeBinding montoBase;
        montoBase = ADFUtils.findControlBinding("MontoBase");
        AttributeBinding porcentajeBase;
        porcentajeBase = ADFUtils.findControlBinding("PorcentajeSobreMontoBase");
        AttributeBinding montoComisionPay;
        montoComisionPay = ADFUtils.findControlBinding("MontoComision");
                
        BigDecimal porcentajeComision=BigDecimal.ZERO;
        BigDecimal porcentaje = new BigDecimal("100");
        int valorMax = 1; 
        int valorMin = -1;
        if(null != porcentajeBase.getInputValue() && !"".equalsIgnoreCase(porcentajeBase.getInputValue().toString())) {
        porcentajeComision=(BigDecimal)porcentajeBase.getInputValue();
        valorMax=porcentajeComision.compareTo(porcentaje);
        valorMin=porcentajeComision.compareTo(BigDecimal.ZERO);
        }
        
        logger.warning("Monto Solicitado: "+(BigDecimal) JSFUtils.resolveExpression("#{pageFlowScope.pMontoSolicitado}"));
        
        logger.warning("Done"); 
        BigDecimal montoBaseD = BigDecimal.ZERO;
        BigDecimal montoComision;
        Integer montoAprobado=3;
        Integer montoFormalizado=4;
        logger.warning("ID monto " + valueChangeEvent.getNewValue());
        Integer idMontoBase = (Integer) valueChangeEvent.getNewValue();
        idMontoBaseComision.setInputValue(idMontoBase);
        switch (idMontoBase){
        case 1:
                   logger.warning("Monto Solicitado");
                   montoBaseD = (BigDecimal) JSFUtils.resolveExpression("#{pageFlowScope.pMontoSolicitado}");
                   montoBase.setInputValue(montoBaseD);
                   if(valorMax<=0 && valorMin>=0){
                      montoComisionPay.setInputValue(calculaComision());
                   }
                   else{
                       montoComisionPay.setInputValue(BigDecimal.ZERO);
                       JSFUtils.addFacesErrorMessage(getStringFromBundle("COMISION_MSG_PORCENTAJE"));
                   }
               break;
        case 2:
                   logger.warning("Monto Aprobado");
                   montoBaseD=obtenerMontoBase(montoAprobado);
                   montoBase.setInputValue(montoBaseD);
                   if(valorMax<=0 && valorMin>=0){
                      montoComisionPay.setInputValue(calculaComision());
                   }
                   else{
                       montoComisionPay.setInputValue(BigDecimal.ZERO);
                       JSFUtils.addFacesErrorMessage(getStringFromBundle("COMISION_MSG_PORCENTAJE"));
                   }
                   if(null==montoBaseD || montoBaseD.compareTo(BigDecimal.ZERO)==0){
                          // JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_CONFIRMA_NO_MONTO"));
                           if(null==montoBaseD){
                                   montoBaseD=BigDecimal.ZERO;
                               }
                           montoBase.setInputValue(montoBaseD);
                           montoComisionPay.setInputValue(calculaComision());
                       }
        break;
        case 3:
                   logger.warning("Monto Formalizado");
                   montoBaseD=obtenerMontoBase(4);
                   montoBase.setInputValue(montoBaseD);
                   if(valorMax<=0 && valorMin>=0){
                      montoComisionPay.setInputValue(calculaComision());
                   }
                   else{
                       montoComisionPay.setInputValue(BigDecimal.ZERO);
                       JSFUtils.addFacesErrorMessage(getStringFromBundle("COMISION_MSG_PORCENTAJE"));
                   }
                           if(null==montoBaseD || montoBaseD.compareTo(BigDecimal.ZERO)==0){
                                   //JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_CONFIRMA_NO_MONTO"));
                           if(null==montoBaseD){
                                   montoBaseD=BigDecimal.ZERO;
                               }
                                   montoBase.setInputValue(montoBaseD);
                                   montoComisionPay.setInputValue(calculaComision());
                       }
        break;
               default:
                   //JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_CONFIRMA_NO_MONTO"));
                   montoBase.setInputValue(montoBaseD);
                   montoComisionPay.setInputValue(calculaComision());
                   break;
               }
    }
    
    public void cambioFrecuenciaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        Integer valorDefecto=1;
        Integer frecuenciaVencimiento=4;
        Integer valorTipoFrecuencia=(Integer)valueChangeEvent.getNewValue();
        AttributeBinding frecuencia;
        frecuencia = ADFUtils.findControlBinding("FrecuenciaPago");
        if(valorTipoFrecuencia.compareTo(frecuenciaVencimiento)==0){
                frecuencia.setInputValue(valorDefecto);
            }
        else{
             //   frecuencia.setInputValue(null);
            }

    }
    
    public BigDecimal obtenerMontoBase(Integer tipoMonto) {
        logger.warning("Entra en obtenerMontoBase.");
        Long idOperacion = null;
        BigDecimal resultado = null;

        if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")) {
            idOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
            logger.warning("Valor de la operacion : " + idOperacion);
        } else {
            logger.warning("La operacion es nula.");
        }
        try{
            resultado = BigDecimal.ZERO;
            BindingContainer bindings = ADFUtils.getBindingContainer();
            //Hace falta el parametro de operacion
            OperationBinding operationBinding = bindings.getOperationBinding("obtenerMontoPorTipo");
            operationBinding.getParamsMap().put("varIdOperacion", idOperacion);
            operationBinding.getParamsMap().put("varTipoMonto", tipoMonto);
            operationBinding.execute();
            resultado = (BigDecimal) operationBinding.getResult();
            //guarda el monto base

            if (!operationBinding.getErrors().isEmpty()) {
                resultado = BigDecimal.ZERO;
            }
        }catch(Exception e){
            logger.warning("Error en obtenerMontoBase.", e);
        }
        logger.warning("Monto obtenido:" + resultado);
        return resultado;
    }
    
    public void validarComisionTodasActionListener(ActionEvent actionEvent) {
        logger.warning("Entra en validarComisionTodasActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        HashMap<String, Object> comisionMap = new HashMap<>();
        Long idOperacion = null;
        Boolean actualiza = Boolean.FALSE;
        String key = null;
        RegistroComisionBean registroComisionBean = null;
        
        try {
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString() &&
               !"".equalsIgnoreCase(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString())){
                   idOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
                   logger.warning("El valor de la operacion es : " + idOperacion);
                   
                   operationBinding = bindings.getOperationBinding("registrarComisionesFlexcube");
                   operationBinding.getParamsMap().put("idOperacion", idOperacion);
                   operationBinding.execute();
                   if (null != operationBinding.getResult()) {
                       comisionMap = (HashMap) operationBinding.getResult();
                       Iterator iteratorMap = comisionMap.entrySet().iterator();
                        logger.warning("Tamaño del mapa : " + comisionMap.size());
                           while (iteratorMap.hasNext()) {
                               Map.Entry comision = (Map.Entry)iteratorMap.next();
                               key = comision.getKey().toString().toLowerCase();
                               logger.warning("Texto key : " + key);
                               if(key.contains(KEY_MENSAJE_COMISION_FALSE)){
                                   logger.warning("Comision no registrada");
                                   JSFUtils.addFacesErrorMessage((String)comision.getValue());
                               }
                              if(key.contains(KEY_MENSAJE_COMISION_TRUE)){
                                  logger.warning("Comision registrada");
                                  JSFUtils.addFacesInformationMessage((String)comision.getValue());
                              }
                              if(key.contains(KEY_MENSAJE_COMISION_REGISTRADA)){
                                  logger.warning("Mensaje : " + (String)comision.getValue());
                              }
                          }
                       registroComisionBean = (RegistroComisionBean) JSFUtils.resolveExpression("#{pageFlowScope.registroBean}");
                       if (null != registroComisionBean) {
                       logger.warning("********CARGAR COMISION NUEVAMENTE**********");
                        try {
                            registroComisionBean.cargarPantallaComision();
                            } catch (Exception e) {
                            logger.severe("Error en cargarPantallaComision :" + e);
                            }
                            logger.warning("********TERMINA DE CARGAR COMISION**********");
                            }
                            AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelRegistroFormUIC());
                   } else {
                       logger.warning("El valor de retorno es nulo.");
                   }
               }else{
                logger.warning("El valor de la operacion es nulo. No se registran las comisiones");
            }
        } catch (Exception e) {
            logger.warning("Error en validarComisionTodasActionListener.", e);
        }
        //return actualiza;
    }

    public void validarComisionActionListener(ActionEvent actionEvent) {
        logger.warning("Entra en validarComisionActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String strIdTcc = null;
        Long idComision = null;
        String codigoContrato = null;
        Boolean actualiza = Boolean.FALSE;
        RegistroComisionBean registroComisionBean = null;
        
        try {
            if(null != JSFUtils.resolveExpression("#{bindings.CodigoContrato.inputValue}") &&
               !"".equalsIgnoreCase(JSFUtils.resolveExpression("#{bindings.CodigoContrato.inputValue}").toString())){
                   codigoContrato = JSFUtils.resolveExpression("#{bindings.CodigoContrato.inputValue}").toString();
               }
            if (JSFUtils.resolveExpression("#{pageFlowScope.pIdTreeComision}") != null &&
                !"".equals(JSFUtils.resolveExpression("#{pageFlowScope.pIdTreeComision}"))) {
                strIdTcc = JSFUtils.resolveExpression("#{pageFlowScope.pIdTreeComision}").toString();
                idComision = Long.valueOf(strIdTcc);
                logger.warning("Numero de la comision : " + strIdTcc);
                if(null != codigoContrato){
                    logger.warning("La comision ya fue registrada en Flexcube.");
                }else{
                    logger.warning("Se registra la comision.");
                    operationBinding = bindings.getOperationBinding("registraValidarComisionFlexcube");
                    operationBinding.getParamsMap().put("idComision", idComision);
                    operationBinding.execute();
                    
                    if (null !=operationBinding) {
                        if (operationBinding.getErrors().isEmpty()) {
                            if (null != operationBinding.getResult()) {
                                actualiza = (Boolean) operationBinding.getResult();
                                if (actualiza) {
                                    logger.warning("La comision fue registrada correctamente.");
                                    registroComisionBean=(RegistroComisionBean) JSFUtils.resolveExpression("#{pageFlowScope.registroBean}");
                                    if(null !=registroComisionBean){
                                        logger.warning("********CARGAR COMISION NUEVAMENTE**********");
                                        try{
                                            registroComisionBean.cargarPantallaComision();
                                        }catch(Exception e){
                                            logger.severe("Error en cargarPantallaComision :"+e);
                                        }
                                        logger.warning("********TERMINA DE CARGAR COMISION**********");
                                    }
                                    AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelRegistroFormUIC());
                                    JSFUtils.addFacesInformationMessage(getStringFromBundle("MSG_03_VALIDAR_REGISTRO_COMISION"));
                                } else {
                                    logger.warning("Error al registrar la comision ->"+operationBinding.getErrors().toString());                               
                                }
                            } else {
                                logger.warning("El valor de getResult es nulo.");
                            }
                        } else {
                            logger.warning("Error -> "+operationBinding.getErrors().toString());                      
                        }
                    } else {
                        logger.warning("El valor de retorno es nulo.");
                    }
                }
            } else {
                logger.warning("El id de la comision es nula.");
            }
        } catch (Exception e) {
            logger.warning("Error en validarComisionTodasActionListener.", e);
        }
    }
    
    private void navigate(FacesEvent event, String outcome) {
        logger.warning("Entra en navigate");
        RichRegion regionComponent = null;
        
        for (UIComponent uic = event.getComponent().getParent(); uic != null; uic = uic.getParent()) {
            logger.warning("Entra en FOR de navigate");
            if (uic instanceof RichRegion) {
                logger.warning("Entra en IF de navigate");
                regionComponent = (RichRegion) uic;
                break;
            }else{
                logger.warning("No se realizo el instanceof");
            }
        }
        
        if (regionComponent != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExpressionFactory ef = fc.getApplication().getExpressionFactory();
            ELContext elc = fc.getELContext();
            MethodExpression me = 
                ef.createMethodExpression(elc, outcome, String.class, new Class[] { });
            regionComponent.queueActionEventInRegion(me, null, null, false, -1, -1, event.getPhaseId());
        }
    } 
    
    private String getStringFromBundle(String psKey) {
        logger.warning("Entra en getStringFromBundle");
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        try{
            if (null != resourceBundle) {
                txt = resourceBundle.getString(psKey);
            }else{
                logger.warning("No se recupero el BUNDLE");
            }
        }catch(Exception e){
            logger.warning("Error en getStringFromBundle.", e);
        }
        return txt;
    }
    
    public void setComisionTreeUI(RichTree comisionTreeUI) {
        this.comisionTreeUI = comisionTreeUI;
    }
    
    public RichTree getComisionTreeUI() {
        return comisionTreeUI;
    }

    public void setOtInitForm(RichOutputText otInitForm) {
        this.otInitForm = otInitForm;
    }

    public RichOutputText getOtInitForm() {
        inicializarTreeTcc();
        return otInitForm;
    }

    public void setAgregarComisionPopup(RichPopup agregarComisionPopup) {
        this.agregarComisionPopup = agregarComisionPopup;
    }

    public RichPopup getAgregarComisionPopup() {
        return agregarComisionPopup;
    }

    public void setComisionNoRegistradaPopup(RichPopup comisionNoRegistradaPopup) {
        this.comisionNoRegistradaPopup = comisionNoRegistradaPopup;
    }

    public RichPopup getComisionNoRegistradaPopup() {
        return comisionNoRegistradaPopup;
    }

    public void setAccionesToolbarUI(RichToolbar accionesToolbarUI) {
        this.accionesToolbarUI = accionesToolbarUI;
    }

    public RichToolbar getAccionesToolbarUI() {
        return accionesToolbarUI;
    }

    public void cancelarCrearNuevaComision(ActionEvent actionEvent) {
        logger.warning("Entra en cancelarCrearNuevaComision.");
        getAgregarComisionPopup().hide();
    }

    public void cancelarAgregarComisionNoRegistrada(ActionEvent actionEvent) {
        logger.warning("Entra en cancelarAgregarComisionNoRegistrada");
        getComisionNoRegistradaPopup().hide();
    }

    public void setPanelRegistroFormUIC(RichPanelGroupLayout panelRegistroFormUIC) {
        this.panelRegistroFormUIC = panelRegistroFormUIC;
    }

    public RichPanelGroupLayout getPanelRegistroFormUIC() {
        return panelRegistroFormUIC;
    }
}
