package pc08cierreght.beans;

import java.io.IOException;

import java.io.Serializable;
import java.io.StringWriter;

import oracle.adf.share.logging.ADFLogger;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.BPMUtils;

import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

public class CierreBean extends FenixPanelBean  implements Serializable {
    @SuppressWarnings("compatibility:120645233037466964")
    private static final long serialVersionUID = 1L;

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(CierreBean.class);
    
    private static final String CODIGO_TAREA = "CodigoTarea";
    private static final String CODIGO_OPERACION = "CodigoOperacion";
    private static final String CERTIFICAR_ADEUDOS = "requiereCierre";
    private static final String ENVIAR_ASJUR = "requiereASJUR";
    
    private oracle.jbo.domain.Number operacionId;
    
    private String codigoTarea;
    private String valueLiberarGarantia = null;
    private String mensajeFinalizar;
    private String idOperacion;
    private String instanciaProceso;
    private String certificarAdeudos;
    private String enviarAsjur;

    public void setValueLiberarGarantia(String valueLiberarGarantia) {
        this.valueLiberarGarantia = valueLiberarGarantia;
    }

    public String getValueLiberarGarantia() {
        return valueLiberarGarantia;
    }

    public void setMensajeFinalizar(String mensajeFinalizar) {
        this.mensajeFinalizar = mensajeFinalizar;
    }

    public String getMensajeFinalizar() {
        return mensajeFinalizar;
    }

    public CierreBean() {
        super();
    }

    public void getPayloadInformation()
        throws WorkflowException, IOException
    {
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();
        
        if (LOGGER.isWarning()) { //if (LOGGER.isFiner())
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
            
        instanciaProceso = BPMUtils.getCurrentTask().getProcessInfo().getInstanceId();
        
        n1 = xmlPayload.getElementsByTagName(CODIGO_OPERACION);
        if(n1 != null && (n1.getLength()>0)) {
            idOperacion = n1.item(0).getTextContent();
            
            try {
                if((idOperacion != null) && (idOperacion.trim().length() > 0))
                    operacionId = new oracle.jbo.domain.Number(Long.valueOf(idOperacion));
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR, "Error al inicializar el valor de operacionId: " + e.getMessage());
            }
        }
        
            n1 = xmlPayload.getElementsByTagName(CERTIFICAR_ADEUDOS);
            if (n1.getLength() > 0) {
                certificarAdeudos = n1.item(0).getTextContent();
            }
        
        n1 = xmlPayload.getElementsByTagName(ENVIAR_ASJUR);
        if (n1.getLength() > 0) {
            enviarAsjur = n1.item(0).getTextContent();
            if(enviarAsjur == null){
                LOGGER.warning("Indicador de Enviar ASJUR no recibido, se toma valor por defecto");
                enviarAsjur = Boolean.FALSE.toString();
            }
        }else{
            LOGGER.warning("Indicador de Enviar ASJUR no recibido, se toma valor por defecto");
            enviarAsjur = Boolean.FALSE.toString();
        }
    }
    
    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public String getCodigoTarea() {
        return codigoTarea;
    }
    
    public void setIdOperacion(String idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getIdOperacion() {
        return idOperacion;
    }
    public String getInstanciaProceso() {
        return instanciaProceso;
    }
    
    public void setCertificarAdeudos(String certificarAdeudos) {
        this.certificarAdeudos = certificarAdeudos;
    }

    public String getCertificarAdeudos() {
        return certificarAdeudos;
    }

    public void setEnviarAsjur(String enviarAsjur) {
        this.enviarAsjur = enviarAsjur;
    }

    public String getEnviarAsjur() {
        return enviarAsjur;
    }
}
