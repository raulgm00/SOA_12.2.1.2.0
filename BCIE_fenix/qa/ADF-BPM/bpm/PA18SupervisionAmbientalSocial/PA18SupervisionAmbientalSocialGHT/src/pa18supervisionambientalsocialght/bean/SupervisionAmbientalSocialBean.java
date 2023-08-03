package pa18supervisionambientalsocialght.bean;

import java.io.IOException;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;

import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

public class SupervisionAmbientalSocialBean extends FenixPanelBean implements Serializable {
    @SuppressWarnings("compatibility:4456211808668913547")
    private static final long serialVersionUID = 1L;

    public SupervisionAmbientalSocialBean() {
        super();
    }

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(SupervisionAmbientalSocialBean.class);

    private static final String CODIGO_OPERACION = "CodigoOperacion";
    private static final String CODIGO_TAREA = "CodigoTarea";
    private static final String REQUIERE_AJUSTES = "requiereAjustes";
    private static final String CODIGO_PRODUCTO = "CodigoProducto";
    private static final String NUM_SERIE_DOCUMENTO = "IdFlujo";

    private String codigoTarea;
    private String idOperacion;
    private String instanciaProceso;
    private Boolean requiereAjustes;
    private Integer codigoProducto;

    private Long codigoOperacion;
    private String colorCalificacion;

    private Boolean esEstadoCompletado = Boolean.FALSE; //Variable Estado BPM

    private oracle.jbo.domain.Number numeroSerieDocumento;

    private Boolean esIFI;

    public void getPayloadInformation() throws WorkflowException, IOException {
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();

        if (LOGGER.isWarning()) // if(LOGGER.isFiner())
        {
            //get the payload as a simple string, useful for debugging
            java.io.StringWriter writer = new java.io.StringWriter();
            xmlPayload.print(writer);
            String payloadAsString = writer.toString();
            LOGGER.log(ADFLogger.WARNING, payloadAsString);
        }

        NodeList nl;

        nl = xmlPayload.getElementsByTagName(CODIGO_TAREA);
        if (nl.getLength() > 0) {
            setCodigoTarea(nl.item(0).getTextContent());
        }

        nl = xmlPayload.getElementsByTagName(CODIGO_OPERACION);
        if (nl.getLength() > 0) {
            setIdOperacion(nl.item(0).getTextContent());
            if ((null != getIdOperacion()) && (getIdOperacion().length() > 0)) {
                setCodigoOperacion(Long.parseLong(getIdOperacion()));
            } else {
                setCodigoOperacion(null);
            }
        }

        nl = xmlPayload.getElementsByTagName(CODIGO_PRODUCTO);
        if (nl != null && (nl.getLength() > 0)) {
            String codigoProducto = nl.item(0).getTextContent().trim();
            if (codigoProducto != null && codigoProducto != "") {
                setCodigoProducto(Integer.parseInt(nl.item(0).getTextContent()));
            }
        }

        nl = xmlPayload.getElementsByTagName(REQUIERE_AJUSTES);
        if (nl.getLength() > 0) {
            setRequiereAjustes(Boolean.parseBoolean(nl.item(0).getTextContent()));
        } else {
            setRequiereAjustes(Boolean.FALSE);
        }

        nl = xmlPayload.getElementsByTagName(NUM_SERIE_DOCUMENTO);
        if (nl.getLength() > 0) {
            try {
                setNumeroSerieDocumento(new oracle.jbo.domain.Number(nl.item(0).getTextContent()));
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR, "Error al inicializar el valor de IdFlujo: " + e.getMessage());
            }
        }

        setEsIFI(Boolean.FALSE);

        instanciaProceso = BPMUtils.getCurrentTask().getProcessInfo().getInstanceId();

        LOGGER.warning("Datos del Payload");
        LOGGER.warning("CodigoTarea: " + getCodigoTarea());
        LOGGER.warning("idOperacion: " + getIdOperacion());
        LOGGER.warning("instanciaProceso: " + instanciaProceso);
        LOGGER.warning("requiereAjustes: " + requiereAjustes);
        LOGGER.warning("CodigoProducto: " + codigoProducto);
        LOGGER.warning("IdFlujo: " + getNumeroSerieDocumento());
        LOGGER.warning("esIFI: " + esIFI);
    }

    public void evaluarParametroPStateBpm() {
        LOGGER.warning("Dentro de evaluarParametroPStateBpm State");

        String state =
            (null != ADFUtils.getBoundAttributeValue("state")) ? (String) ADFUtils.getBoundAttributeValue("state") :
            null;
        LOGGER.warning("Var State: " + state);

        try {
            if (state != null) {
                LOGGER.warning("state :" + state);
                setEsEstadoCompletado(state.equalsIgnoreCase("COMPLETED") ? Boolean.TRUE : Boolean.FALSE);
            } else {
                LOGGER.warning("pState es nulo");
                setEsEstadoCompletado(Boolean.FALSE);
            }
        } catch (Exception ex) {
            LOGGER.warning("Error al recuperar NodeList state :", ex);
            LOGGER.warning("pState no obtenido");
        }

        LOGGER.warning("esEstadoCompletado : " + getEsEstadoCompletado());
        LOGGER.warning("Fuera de evaluarParametroPStateBpm");
    }

    public void setIdOperacion(String idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getIdOperacion() {
        return idOperacion;
    }

    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public String getCodigoTarea() {
        return codigoTarea;
    }
    
    public void setCodigoOperacion(Long codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public Long getCodigoOperacion() {
        return codigoOperacion;
    }
    
    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }

    public String getInstanciaProceso() {
        return instanciaProceso;
    }
    
    public void setRequiereAjustes(Boolean requiereAjustes) {
        this.requiereAjustes = requiereAjustes;
    }

    public Boolean getRequiereAjustes() {
        return requiereAjustes;
    }
    
    public void setEsIFI(Boolean esIFI) {
        this.esIFI = esIFI;
    }

    public Boolean getEsIFI() {
        return esIFI;
    }
    
    public void setCodigoProducto(Integer codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Integer getCodigoProducto() {
        return codigoProducto;
    }
    
    public void setColorCalificacion(String colorCalificacion) {
        this.colorCalificacion = colorCalificacion;
    }

    public String getColorCalificacion() {
        return colorCalificacion;
    }
    
    public void setNumeroSerieDocumento(oracle.jbo.domain.Number numeroSerieDocumento) {
        this.numeroSerieDocumento = numeroSerieDocumento;
    }

    public oracle.jbo.domain.Number getNumeroSerieDocumento() {
        return numeroSerieDocumento;
    }
    
    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }
}
