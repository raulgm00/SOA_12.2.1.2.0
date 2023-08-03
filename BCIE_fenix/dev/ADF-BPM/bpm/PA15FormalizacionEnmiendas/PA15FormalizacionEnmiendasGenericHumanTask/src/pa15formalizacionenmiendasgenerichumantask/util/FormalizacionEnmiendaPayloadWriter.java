package pa15formalizacionenmiendasgenerichumantask.util;

import java.io.IOException;

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
 * Clase inmutable encargada de escribir ParameterType en el payload de Formalización de Enmiendas.
 * Hace uso del bindings para obtener los objetos requeridos.
 */
public final class FormalizacionEnmiendaPayloadWriter {
    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(FormalizacionEnmiendaPayloadWriter.class);

    /** Tarea que será actualizada.*/
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
     * Permite la adición/edicion de ParameterType al payload de la tarea.
     * Primero se busca un ParameterType con el nombre pasado como parámetro. Si se encuentra se actualiza, en caso de
     * que no se encuentre, se adiciona.
     *
     * @param name Nombre que se usará para el ParameterName.
     * @param value Valor que se usará en el ParameterValue.
     */
    public void putParameterType(final String name, final String value) {
        LOGGER.info("Dentro de putParameterType");

        LOGGER.warning("Obteniendo payload de la tarea");
        XMLElement taskPayloadElement = (XMLElement) this.task.getPayloadAsElement();

        LOGGER.info("Buscando ParameterType {0}", name);
        Node paramTypeNode = findParameterType(name, taskPayloadElement);
        if (paramTypeNode != null) {
            LOGGER.info("Actualizando ParameterType: {0}=>{1}", new Object[] { name, value });
            updateParameterType(paramTypeNode, value);
        } else {
            LOGGER.warning("No se encuentró ParameterType {0}. Creando uno nuevo...", name);
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
        LOGGER.info("Dentro de updateParameterType");

        Node paramTypeValue = findParameterTypeChildNode(paramTypeNode, PA15Constants.PAYLOAD_PARAMETERTYPE_VALUE_NODE);
        paramTypeValue.setTextContent(value);
    }

    /**
     * Crea un nodo tipo ParameterType y lo adiciona al payload de la tarea.
     * Un nodo de tipo ParameterType contiene dos nodos: PARAMETERNAME y PARAMETERVALUE.
     *
     * @param name Nombre que recibirá el nuevo nodo.
     * @param value Valor a asignar al nuevo nodo.
     * @param taskPayload Payload de la tarea donde se adicionará el nuevo nodo.
     */
    private void createParameterType(final String name, final String value, final XMLElement taskPayload) {
        LOGGER.info("Dentro de createParameterType");

        XMLDocument doc = taskPayload.getDocument();

        Element newParamTypeNameNode = doc.createElement(PA15Constants.PAYLOAD_PARAMETERTYPE_NAME_NODE);
        newParamTypeNameNode.setTextContent(name);

        Element newParamTypeValueNode = doc.createElement(PA15Constants.PAYLOAD_PARAMETERTYPE_VALUE_NODE);
        newParamTypeValueNode.setTextContent(value);

        Element newParamTypeNode = doc.createElement(PA15Constants.PAYLOAD_PARAMETERTYPE_NODE);
        newParamTypeNode.appendChild(newParamTypeNameNode);
        newParamTypeNode.appendChild(newParamTypeValueNode);

        Node child = taskPayload.getFirstChild();
        //LOGGER.info("Puede insertar en payloadElement child? {0}", child.canAppendChild(newParamTypeNode));
        LOGGER.info("Insertando ParameterType en payloadElement child:\n{0}", newParamTypeNode.getTextContent());
        child.appendChild(newParamTypeNode);
        
        if (LOGGER.isInfo()) {
            //get the payload as a simple string, useful for debugging
            try {
                java.io.StringWriter writer = new java.io.StringWriter();
                taskPayload.print(writer);
                String payloadAsString = writer.toString();
                LOGGER.info("Escribiendo FormalizacionEnmiendaPayload");
                LOGGER.info(payloadAsString);
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
        LOGGER.info("Dentro de removeParameterType");

        LOGGER.warning("Obteniendo payload de la tarea");
        XMLElement taskPayloadElement = (XMLElement) this.task.getPayloadAsElement();

        LOGGER.info("Buscando ParameterType {0}", name);
        Node paramTypeNode = findParameterType(name, taskPayloadElement);
        if (paramTypeNode != null) {
            LOGGER.info("Eliminando ParameterType: {0}", name);
            Node parentNode = paramTypeNode.getParentNode();
            parentNode.removeChild(paramTypeNode);
        } else {
            LOGGER.warning("ParameterType {0} no encontrado para eliminación", name);
        }

        LOGGER.warning("Asignando nuevo payload a la tarea");
        this.task.setPayloadAsElement(taskPayloadElement);
    }


    /**
     * Busca un ParameterType bajo el nombre pasado por parámetro.
     * @param name Nombre del ParameterType a buscar.
     * @return Nodo cuyo nombre es el mismo pasado por parámetro o nulo si no se encontró.
     */
    private Node findParameterType(final String name, final XMLElement taskPayload) {
        LOGGER.info("Dentro de findParameterType");

        Node paramTypeFound = null;

        LOGGER.info("Obteniendo nodos tipo ParameterType");
        NodeList nl = taskPayload.getElementsByTagName(PA15Constants.PAYLOAD_PARAMETERTYPE_TAGNAME);
        if (nl == null) {
            LOGGER.info("No se encontraron nodos tipo ParameterType");
        } else {

            if (nl.getLength() == 0) {
                LOGGER.info("0 nodos tipo ParameterType encontrados");
            } else {
                LOGGER.info("{0} nodos tipo ParameterType encontrados", nl.getLength());
                //Recorre todos los nodos tipo ParameterType
                for (int i = 0; i < nl.getLength(); i++) {
                    Node paramTypeNode = nl.item(i);
                    Node paramNameNode = findParameterTypeChildNode(paramTypeNode, PA15Constants.PAYLOAD_PARAMETERTYPE_NAME_NODE);
                    if (paramNameNode != null && name.equals(paramNameNode.getTextContent())) {
                        LOGGER.info("ParameterType {0} encontrado!", name);
                        paramTypeFound = paramTypeNode;
                        break;
                    }
                }
            }

            if (paramTypeFound == null) {
                LOGGER.info("ParameterType {0} NO encontrado!", name);
            }
        }

        return paramTypeFound;
    }

    /**
     * Busca un nodo hijo de un nodo tipo ParameterType.
     *
     * @param paramTypeNode Nodo tipo ParameterType en donde se buscará.
     * @param nodeType Nodo hijo que se busca.
     * @return Nodo hijo del paramTypeNode con el nombre childNodeName o nulo si no se encuentra.
     */
    private Node findParameterTypeChildNode(final Node paramTypeNode, final String nodeType) {
        LOGGER.info("Dentro de findParameterTypeChildNode");
        LOGGER.info("Buscando ChildNode tipo {0}", nodeType);

        Node paramTypeChildFound = null;
        NodeList paramChildNodes = paramTypeNode.getChildNodes();
        for (int j = 0; j < paramChildNodes.getLength(); j++) {
            Node childNode = paramChildNodes.item(j);
            String childNodeType = childNode.getNodeName();
            if (childNodeType != null) {
                if (childNodeType.equalsIgnoreCase(nodeType)) {
                    LOGGER.info("ParameterTypeChild tipo {0} encontrado! Text = {1}", new Object[] {
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
        LOGGER.info("Dentro de updateTask");
        try {
            IWorkflowServiceClient workflowSvcClient = WorkflowServiceUtil.getWorkflowServiceClient();
            ITaskService iTaskService = workflowSvcClient.getTaskService();
            ITaskQueryService wfQueryService = workflowSvcClient.getTaskQueryService();
            String bmpwlctx = (String) JSFUtils.resolveExpression("#{pageFlowScope.bpmWorklistContext}");
            IWorkflowContext wfContext = wfQueryService.getWorkflowContext(bmpwlctx);

            LOGGER.info("Actualizando tarea...");
            iTaskService.updateTask(wfContext, this.task);
            LOGGER.info("Actualización finalizada");
        } catch (Exception ex) {
            LOGGER.severe("Falla actualizar payload de la tarea usando API Java");
            LOGGER.severe(ex);
        }
    }
}
