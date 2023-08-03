package pc07gestioncobroght.bean;

import java.awt.event.ActionEvent;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlAttrsBinding;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.vo.LineasDeCreditoOperacionLOVImpl;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

public class GestionCobroBean extends FenixPanelBean implements Serializable {
    @SuppressWarnings("compatibility:8586312878244609114")
    private static final long serialVersionUID = 1L;

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(GestionCobroBean.class);
    public static final String BEAN_EXPRESSION = "#{pageFlowScope.gestionCobroBean}";
    private static final String CODIGO_CLIENTE = "IdCliente";
    private static final String CODIGO_CLIENTE_FLEX = "IdFlexCube";
    private static final String RESPONSABLE_CLIENTE = "ResponsableCliente";
    private static final String CODIGO_TAREA = "CodigoTarea";
    private static final String ANONYMOUS = "anonymous";
    private static final String COMMA = ",";
    
    private static final Integer SECTOR_PUBLICO = 1;
    private static final Integer SECTOR_PRIVADO = 2;


    private String codigoTarea;
    private String idCliente;
    private Long lngIdCliente;
    private List<Long> operacionesSeleccionadas; 
    private List<String> lineasDeCreditoSeleccionadas;
    private List<String> fondosSeleccionados;
    private Long idFlujo;
    private Boolean botonAviso;
    private String bhqCliente;
    private String nombreUsuario;
    private String instanciaProceso;
    private Integer sectorInstitucional;
    private RichInputText precargarNivelDeDetalle;
    private String nivelDetalle = null;

    public GestionCobroBean() {
        super();
    }

    /**Gets the payload information and initialize the bean atributes
     * @throws WorkflowException
     * @throws IOException
     */
    public void getPayloadInformation() throws WorkflowException, IOException {
        LOGGER.warning("getPayloadInformation starts");
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();

        if (LOGGER.isWarning()) {
            //get the payload as a simple string, useful for debugging
            java.io.StringWriter writer = new StringWriter();
            xmlPayload.print(writer);
            String payloadAsString = writer.toString();
            LOGGER.log(ADFLogger.WARNING, payloadAsString);
        }

        NodeList n1;

        n1 = xmlPayload.getElementsByTagName(CODIGO_TAREA);
        if (n1.getLength() > 0) {
            codigoTarea = n1.item(0).getTextContent();
        }

        n1 = xmlPayload.getElementsByTagName(CODIGO_CLIENTE);
        if (n1.getLength() > 0) {
            idCliente = n1.item(0).getTextContent();
        }
        
        n1 = xmlPayload.getElementsByTagName(CODIGO_CLIENTE_FLEX);
        if (n1.getLength() > 0) {
            bhqCliente = n1.item(0).getTextContent();
        }
        
        n1 = xmlPayload.getElementsByTagName("IdFlujo");
        if (n1.getLength() > 0) {
            try {
                oracle.jbo.domain.Number claveFlujo=new oracle.jbo.domain.Number(n1.item(0).getTextContent());
                idFlujo=claveFlujo.longValue();
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR, "Error al inicializar el valor de IdFlujo: " + e.getMessage());
            }
        }
        
        if (null != JSFUtils.resolveExpression("#{securityContext.userName}")) {
            String login = (String) JSFUtils.resolveExpression("#{securityContext.userName}");
            LOGGER.warning("login usuario :" + login);
            if (login.equalsIgnoreCase(ANONYMOUS)) {
                LOGGER.warning("El usuario es anonymous, Se toma el usuario del payload");
                n1 = xmlPayload.getElementsByTagName(RESPONSABLE_CLIENTE);
                if (n1.getLength() > 0) {
                    setNombreUsuario(n1.item(0).getTextContent().toLowerCase());
                }
            } else {
                    setNombreUsuario(login);
            }
        }else{
            LOGGER.warning("Se obtiene nulo el login del usuario en sesion.");
        }
        
        obtenerInstanciaProceso();
        
        
        botonAviso=Boolean.FALSE;
        LOGGER.warning("bhqCliente: " + bhqCliente);
        LOGGER.warning("idFlujo: " + idFlujo);
        LOGGER.warning("Codigo Cliente: " + idCliente);
        LOGGER.warning("Id Tarea: " + codigoTarea);
        LOGGER.warning("nombre de usuario : " + nombreUsuario);
        LOGGER.warning("instanciaProceso: " + instanciaProceso);
        LOGGER.warning("getPayloadInformation ends");
    }

    public void obtenerInstanciaProceso() {

        String instancia = null;
        try {
            if (BPMUtils.getCurrentTask() != null && BPMUtils.getCurrentTask().getProcessInfo() != null &&
                BPMUtils.getCurrentTask().getProcessInfo().getInstanceId() != null) {
                instancia = BPMUtils.getCurrentTask().getProcessInfo().getInstanceId();
            }
        } catch (Exception e) {
            LOGGER.severe("Error al obtener la Instancia del Proceso", e);
        }

        if (instancia != null) {
            setInstanciaProceso(instancia);
        }
    }
    
    
    public void consultarSectorInstitucionalPorIdCliente() {
        LOGGER.warning("Dentro consultarSectorInstitucionalPorIdCliente");
        //declaracion de variables
        Integer sector = null;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        Integer idCliente = null;
        //obtener error en el cast de idCLiente String a Integer
        try{
            idCliente = new Integer(this.getIdCliente());
            LOGGER.warning("idCliente :"+idCliente);
        }catch(Exception e){
            LOGGER.severe("Error al convertir idCLiente en Integer:"+e);
        }
        
        //obtener si ocurre un error en el modelo
        try{
            //asignar nombre del metodo "getSectorInstitucionalByIdCliente" que se encuentra implementado en "ClienteLVOImpl"
            operationBinding = bindings.getOperationBinding("getSectorInstitucionalByIdCliente");
            //asignar parametros para el metodo "getSectorInstitucionalByIdCliente"
            //identificador unico de cliente
            operationBinding.getParamsMap().put("idCliente",idCliente);
            //ejecutar el metodo y obtener el resultado 
            sector = (Integer) operationBinding.execute();
            //asignar el valor obtenido a la variable "sectorInstitucional"
            this.setSectorInstitucional(sector);
            
        }catch(Exception e){
            //pintar error severe ya que el modelo obtuvo un error
            LOGGER.severe("Error en consultarSectorInstitucionalPorIdCliente :"+e);
        }
        //mostrar valor de "sectorInstitucional"
        LOGGER.warning("sectorInstitucional :"+this.getSectorInstitucional());
        LOGGER.warning("Fuera consultarSectorInstitucionalPorIdCliente");
    }
    
    public void precargarNivelDeDetallePorSectorInstitucional(){
        LOGGER.warning("Dentro de precargarNivelDeDetallePorSectorInstitucional");
        LOGGER.warning("sectorInstitucional :"+this.getSectorInstitucional());
        String nivelDetalleValueDefault = null;
        
        if(SECTOR_PRIVADO.compareTo(this.getSectorInstitucional()) == 0){
            LOGGER.warning("El sector es privado valor default para nivelDetalle es N");
            nivelDetalleValueDefault = "N";
        }
        
        if(SECTOR_PUBLICO.compareTo(this.getSectorInstitucional()) == 0){
            LOGGER.warning("El sector es publico valor default para nivelDetalle es S");
            nivelDetalleValueDefault = "S";
        }
        
       GestionCobroBean gestionCobroBean = (GestionCobroBean) JSFUtils.resolveExpression(GestionCobroBean.BEAN_EXPRESSION);
       
        if(gestionCobroBean.getNivelDetalle() != null && gestionCobroBean.getNivelDetalle() != ""){
            LOGGER.warning("Se detecto un nivel de detalle seleccionado: "+gestionCobroBean.getNivelDetalle());
            nivelDetalleValueDefault = gestionCobroBean.getNivelDetalle();
        }
        
        JSFUtils.setExpressionValue("#{bindings.NivelDetalle.inputValue}", nivelDetalleValueDefault);
        
        LOGGER.warning("Fuera de precargarNivelDeDetallePorSectorInstitucional");
    }
    
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }

    public String getInstanciaProceso() {
        return instanciaProceso;
    }
        
    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public String getCodigoTarea() {
        return codigoTarea;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }
    public void setOperacionesSeleccionadas(List<Long> operacionesSeleccionadas) {
        this.operacionesSeleccionadas = operacionesSeleccionadas;
    }

    public List<Long> getOperacionesSeleccionadas() {
        return operacionesSeleccionadas;
    }

    public void setLineasDeCreditoSeleccionadas(List<String> lineasDeCreditoSeleccionadas) {
        this.lineasDeCreditoSeleccionadas = lineasDeCreditoSeleccionadas;
    }

    public List<String> getLineasDeCreditoSeleccionadas() {
        return lineasDeCreditoSeleccionadas;
    }

    public void setFondosSeleccionados(List<String> fondosSeleccionados) {
        this.fondosSeleccionados = fondosSeleccionados;
    }

    public List<String> getFondosSeleccionados() {
        return fondosSeleccionados;
    }
    
    public void setLngIdCliente(Long lngIdCliente) {
        this.lngIdCliente = lngIdCliente;
    }

    public Long getLngIdCliente() {
        Long idCliente = null;
        if(getIdCliente() != null){
            try{
                idCliente = new Long(getIdCliente());
            }catch(Exception e){
                LOGGER.warning("No se pudo convertir cadena Id Cliente a Long");
            }
            lngIdCliente = idCliente;
        }
        return lngIdCliente;
    }

    public Long getIdFlujo() {
        return idFlujo;
    }

    public void setIdFlujo(Long idFlujo) {
        this.idFlujo = idFlujo;
    }

    public Boolean getBotonAviso() {
        return botonAviso;
    }

    public void setBotonAviso(Boolean botonAviso) {
        this.botonAviso = botonAviso;
    }

    public String getBhqCliente() {
        return bhqCliente;
    }

    public void setBhqCliente(String bhqCliente) {
        this.bhqCliente = bhqCliente;
    }


    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Integer getSectorInstitucional() {
        return sectorInstitucional;
    }

    public void setSectorInstitucional(Integer sectorInstitucional) {
        this.sectorInstitucional = sectorInstitucional;
    }

    public void setPrecargarNivelDeDetalle(RichInputText precargarNivelDeDetalle) {
        this.precargarNivelDeDetalle = precargarNivelDeDetalle;
    }

    public RichInputText getPrecargarNivelDeDetalle() {
        precargarNivelDeDetallePorSectorInstitucional();
        return precargarNivelDeDetalle;
    }
    
    public void setNivelDetalle(String nivelDetalle) {
        this.nivelDetalle = nivelDetalle;
    }

    public String getNivelDetalle() {
        return nivelDetalle;
    }

   
}
