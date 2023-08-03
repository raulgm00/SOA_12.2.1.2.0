package pa15formalizacionenmiendasgenerichumantask.util;

import java.io.IOException;

import java.util.List;
import java.util.Objects;

import oracle.adf.share.logging.ADFLogger;

import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.task.ITaskService;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.util.WorkflowServiceUtil;
import oracle.bpel.services.workflow.verification.IWorkflowContext;

import oracle.xml.parser.v2.XMLDocument;
import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.utils.JSFUtils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Clase inmutable encargada de escribir ParameterType en el payload de Formalizaci�n de Enmiendas.
 * Hace uso del bindings para obtener los objetos requeridos.
 */
public final class FormalizacionEnmiendaPayloadWriter {
    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(FormalizacionEnmiendaPayloadWriter.class);

    /** Tarea que ser� actualizada.*/
    private Task task;

    /**
     * Crea un FormalizacionEnmiendaPayloadWriter.
     * Carga la tarea a ser actualizada.
     */
    public FormalizacionEnmiendaPayloadWriter() {
        try {
            this.task = BPMUtils.getCurrentTask();
        } catch (WorkflowException ex) {
            LOGGER.severe("No se pudo obtener la tarea desde API Java", ex);
        }
        Objects.requireNonNull(this.task);
    }

    /**
     * Permite la adici�n/edicion de ParameterType al payload de la tarea.
     * Primero se busca un ParameterType con el nombre pasado como par�metro. Si se encuentra se actualiza, en caso de
     * que no se encuentre, se adiciona.
     *
     * @param name Nombre que se usar� para el ParameterName.
     * @param value Valor que se usar� en el ParameterValue.
     */
    public void putParameterType(final String name, final String value) {
        LOGGER.warning("Dentro de putParameterType");

        LOGGER.warning("Obteniendo payload de la tarea");
        XMLElement taskPayloadElement = (XMLElement) this.task.getPayloadAsElement();

        LOGGER.warning("Buscando ParameterType {0}", name);
        Node paramTypeNode = findParameterType(name, taskPayloadElement);
        if (paramTypeNode != null) {
            LOGGER.warning("Actualizando ParameterType: {0}=>{1}", new Object[] { name, value });
            updateParameterType(paramTypeNode, value);
        } else {
            LOGGER.warning("No se encuentr� ParameterType {0}. Creando uno nuevo...", name);
            createParameterType(name, value, taskPayloadElement);
        }

        LOGGER.warning("Asignando nuevo payload a la tarea");
        this.task.setPayloadAsElement(taskPayloadElement);
    }

    /**
     * Actualiza el valor de un nodo tipo ParameterType.
     * @param paramTypeNode Nodo a actualizar.
     * @param value Valor a asignar al nodo pasado por param.
     */
    private void updateParameterType(final Node paramTypeNode, final String value) {
        LOGGER.warning("Dentro de updateParameterType");

        Node paramTypeValue = findParameterTypeChildNode(paramTypeNode, PA15Constants.PAYLOAD_PARAMETERTYPE_VALUE_NODE);
        paramTypeValue.setTextContent(value);
    }

    /**
     * Crea un nodo tipo ParameterType y lo adiciona al payload de la tarea.
     * Un nodo de tipo ParameterType contiene dos nodos: PARAMETERNAME y PARAMETERVALUE.
     *
     * @param name Nombre que recibir� el nuevo nodo.
     * @param value Valor a asignar al nuevo nodo.
     * @param taskPayload Payload de la tarea donde se adicionar� el nuevo nodo.
     */
    private void createParameterType(final String name, final String value, final XMLElement taskPayload) {
        LOGGER.warning("Dentro de createParameterType");

        XMLDocument doc = taskPayload.getDocument();

        Element newParamTypeNameNode = doc.createElement(PA15Constants.PAYLOAD_PARAMETERTYPE_NAME_NODE);
        newParamTypeNameNode.setTextContent(name);

        Element newParamTypeValueNode = doc.createElement(PA15Constants.PAYLOAD_PARAMETERTYPE_VALUE_NODE);
        newParamTypeValueNode.setTextContent(value);

        Element newParamTypeNode = doc.createElement(PA15Constants.PAYLOAD_PARAMETERTYPE_NODE);
        newParamTypeNode.appendChild(newParamTypeNameNode);
        newParamTypeNode.appendChild(newParamTypeValueNode);

        Node child = taskPayload.getFirstChild();
        //LOGGER.warning("Puede insertar en payloadElement child? {0}", child.canAppendChild(newParamTypeNode));
        LOGGER.warning("Insertando ParameterType en payloadElement child:\n{0}", newParamTypeNode.getTextContent());
        child.appendChild(newParamTypeNode);
        
        if (LOGGER.isInfo()) {
            //get the payload as a simple string, useful for debugging
            try {
                java.io.StringWriter writer = new java.io.StringWriter();
                taskPayload.print(writer);
                String payloadAsString = writer.toString();
                LOGGER.warning("Escribiendo FormalizacionEnmiendaPayload");
                LOGGER.warning(payloadAsString);
            } catch (IOException ex) {
                LOGGER.severe("Error al escribir el Payload a este logger", ex);
            }
        }
    }

    /**
     * Elimina un nodo de tipo ParameterType del payload de la tarea.
     *
     * @param Nombre del nodo ParameterType a eliminar.
     */
    public void removeParameterType(final String name) {
        LOGGER.warning("Dentro de removeParameterType");

        LOGGER.warning("Obteniendo payload de la tarea");
        XMLElement taskPayloadElement = (XMLElement) this.task.getPayloadAsElement();

        LOGGER.warning("Buscando ParameterType {0}", name);
        Node paramTypeNode = findParameterType(name, taskPayloadElement);
        if (paramTypeNode != null) {
            LOGGER.warning("Eliminando ParameterType: {0}", name);
            Node parentNode = paramTypeNode.getParentNode();
            parentNode.removeChild(paramTypeNode);
        } else {
            LOGGER.warning("ParameterType {0} no encontrado para eliminaci�n", name);
        }

        LOGGER.warning("Asignando nuevo payload a la tarea");
        this.task.setPayloadAsElement(taskPayloadElement);
    }


    /**
     * Busca un ParameterType bajo el nombre pasado por par�metro.
     * @param name Nombre del ParameterType a buscar.
     * @return Nodo cuyo nombre es el mismo pasado por par�metro o nulo si no se encontr�.
     */
    private Node findParameterType(final String name, final XMLElement taskPayload) {
        LOGGER.warning("Dentro de findParameterType");

        Node paramTypeFound = null;

        LOGGER.warning("Obteniendo nodos tipo ParameterType");
        NodeList nl = taskPayload.getElementsByTagName(PA15Constants.PAYLOAD_PARAMETERTYPE_TAGNAME);
        if (nl == null) {
            LOGGER.warning("No se encontraron nodos tipo ParameterType");
        } else {

            if (nl.getLength() == 0) {
                LOGGER.warning("0 nodos tipo ParameterType encontrados");
            } else {
                LOGGER.warning("{0} nodos tipo ParameterType encontrados", nl.getLength());
                //Recorre todos los nodos tipo ParameterType
                for (int i = 0; i < nl.getLength(); i++) {
                    Node paramTypeNode = nl.item(i);
                    Node paramNameNode = findParameterTypeChildNode(paramTypeNode, PA15Constants.PAYLOAD_PARAMETERTYPE_NAME_NODE);
                    if (paramNameNode != null && name.equals(paramNameNode.getTextContent())) {
                        LOGGER.warning("ParameterType {0} encontrado!", name);
                        paramTypeFound = paramTypeNode;
                        break;
                    }
                }
            }

            if (paramTypeFound == null) {
                LOGGER.warning("ParameterType {0} NO encontrado!", name);
            }
        }

        return paramTypeFound;
    }

    /**
     * Busca un nodo hijo de un nodo tipo ParameterType.
     *
     * @param paramTypeNode Nodo tipo ParameterType en donde se buscar�.
     * @param nodeType Nodo hijo que se busca.
     * @return Nodo hijo del paramTypeNode con el nombre childNodeName o nulo si no se encuentra.
     */
    private Node findParameterTypeChildNode(final Node paramTypeNode, final String nodeType) {
        LOGGER.warning("Dentro de findParameterTypeChildNode");
        LOGGER.warning("Buscando ChildNode tipo {0}", nodeType);

        Node paramTypeChildFound = null;
        NodeList paramChildNodes = paramTypeNode.getChildNodes();
        for (int j = 0; j < paramChildNodes.getLength(); j++) {
            Node childNode = paramChildNodes.item(j);
            String childNodeType = childNode.getNodeName();
            if (childNodeType != null) {
                if (childNodeType.equalsIgnoreCase(nodeType)) {
                    LOGGER.warning("ParameterTypeChild tipo {0} encontrado! Text = {1}", new Object[] {
                                nodeType, childNode.getTextContent() });
                    paramTypeChildFound = childNode;
                    break;
                }
            }
        }

        if (paramTypeChildFound == null) {
            LOGGER.warning("ParameterTypeChild tipo {0} NO encontrado!", nodeType);
        }

        return paramTypeChildFound;
    }

    /**
     * Actualiza la tarea.
     */
    public void updateTask() {
        LOGGER.warning("Dentro de updateTask");
        try {
            IWorkflowServiceClient workflowSvcClient = WorkflowServiceUtil.getWorkflowServiceClient();
            ITaskService iTaskService = workflowSvcClient.getTaskService();
            ITaskQueryService wfQueryService = workflowSvcClient.getTaskQueryService();
            String bmpwlctx = (String) JSFUtils.resolveExpression("#{pageFlowScope.bpmWorklistContext}");
            

            LOGGER.warning("bmpwlctx: " + bmpwlctx);
            IWorkflowContext wfContext = wfQueryService.getWorkflowContext(bmpwlctx);

            try{
                LOGGER.warning("Actualizando tarea...");  
                iTaskService.updateTask(wfContext, this.task);
            }
            catch (Exception exp) {
                LOGGER.warning("Error al ejecutar updateTask: " + exp);
                LOGGER.warning("Se ejecutara mergeAndUpdateTask con task");
                iTaskService.mergeAndUpdateTask(wfContext, this.task); 
                LOGGER.warning("e ejecuto updateTask con task null");
            }
            
            LOGGER.warning("Actualizaci�n finalizada");
        } catch (Exception ex) {
            LOGGER.warning("Falla actualizar payload de la tarea usando API Java");
            LOGGER.warning(ex);
        }
    }
}
