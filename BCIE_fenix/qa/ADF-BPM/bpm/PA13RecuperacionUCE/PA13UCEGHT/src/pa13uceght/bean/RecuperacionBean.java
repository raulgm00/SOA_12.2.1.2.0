package pa13uceght.bean;

import java.io.IOException;
import java.io.Serializable;

import java.sql.SQLException;

import oracle.adf.share.logging.ADFLogger;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;

import org.bcie.fenix.common.utils.JSFUtils;

import org.w3c.dom.NodeList;

public class RecuperacionBean implements Serializable{
    @SuppressWarnings("compatibility:8095398990156431191")
    private static final long serialVersionUID = 1L;
    private static ADFLogger logger = null;
    private static final String CODIGO_OPERACION = "CodigoOperacion";
    private static final String CODIGO_TAREA = "CodigoTarea";
    private static final String CODIGO_CLIENTE= "IdCliente";
    private static final String INSTANCIA_PROCESO="InstanciaProceso";
    private static final String TIPO_INICIO="tipoInicio";
    private static final String CLIENTE_UCE="clienteUCE";
    private static final String NUM_SERIE_DOCUMENTO="IdFlujo";
    private String codigoTarea;
    private oracle.jbo.domain.Number idCliente;
    private String instanciaProceso;
    private String tipoInicio;
    private oracle.jbo.domain.Number clienteUCE;
    private oracle.jbo.domain.Number numeroSerieDocumento;

    public RecuperacionBean() {
        super();
        
        if (logger == null)
        {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    
    public void getPayloadInformation()
          throws WorkflowException, IOException, SQLException {
          XMLElement xmlPayload = BPMUtils.getPayloadInformation();

          if (logger.isWarning()) // if(LOGGER.isFiner())
          {
            //get the payload as a simple string, useful for debugging
            java.io.StringWriter writer = new java.io.StringWriter();
            xmlPayload.print(writer);
            String payloadAsString = writer.toString();
            logger.log(ADFLogger.WARNING, payloadAsString);
          }
          NodeList nl;
          
            // recuperar codigo de tarea BPM
            nl = xmlPayload.getElementsByTagName(CODIGO_TAREA);
            if (nl.getLength() > 0)
            {
            setCodigoTarea(nl.item(0).getTextContent());
            }
            
            // recuperar IdCliente
            nl = xmlPayload.getElementsByTagName(CODIGO_CLIENTE);
            if (nl.getLength() > 0)
            {
                try {
                setIdCliente(new oracle.jbo.domain.Number(nl.item(0).getTextContent()));
                } catch (Exception e) {
                logger.log(ADFLogger.ERROR, "Error al inicializar el valor de idCliente: " + e.getMessage());
                }
            }
            
            // recuperar Instancia del proceso BPM
            setInstanciaProceso(BPMUtils.getCurrentTask().getProcessInfo().getInstanceId());
            // instancia de prueba para el proceso.
            //setInstanciaProceso("987654321");
            
            // recuperar Motivo de solicitud para el proceso de UCE
            nl = xmlPayload.getElementsByTagName(TIPO_INICIO);
            if(nl != null && (nl.getLength()>0)) {
                if(nl.item(0).getTextContent()!= ""){
                    tipoInicio = nl.item(0).getTextContent();
                }else{
                    logger.log(ADFLogger.ERROR,"No se recupero el valor de ´tipoInicio´ del payLoad");
                    tipoInicio = " ";
                }
            }else{
                logger.log(ADFLogger.ERROR,"No se recupero el valor de ´tipoInicio´ del payLoad");
                tipoInicio = " ";
            }
            
            // recuperar cliente en deterioro 
            nl = xmlPayload.getElementsByTagName(CLIENTE_UCE);
            if(nl != null && (nl.getLength()>0)) {
                if(Boolean.valueOf(nl.item(0).getTextContent())){
                    clienteUCE = new oracle.jbo.domain.Number("1");
                }else{
                    clienteUCE = new oracle.jbo.domain.Number("0");
                }
            }else{
                //default value
                clienteUCE = new oracle.jbo.domain.Number("0");
                logger.log(ADFLogger.ERROR,"No se recupero el valor de ´clienteUCE´ del payLoad");
            }
            
            //recupera el valor de idFlujo, para el componente GestorDocumentosCliente
            nl = xmlPayload.getElementsByTagName(NUM_SERIE_DOCUMENTO);
            if (nl.getLength() > 0){
                try {
                    setNumeroSerieDocumento(new oracle.jbo.domain.Number(nl.item(0).getTextContent()));
                } catch (Exception e) {
                    logger.log(ADFLogger.ERROR, "Error al inicializar el valor de IdFlujo: " + e.getMessage());
                }
            }
            
            logger.log(ADFLogger.WARNING,"numeroSerieDocumento value :"+numeroSerieDocumento);

        }



    public String getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }


    public oracle.jbo.domain.Number getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(oracle.jbo.domain.Number idCliente) {
        this.idCliente = idCliente;
    }


    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }


    public String getTipoInicio() {
        return tipoInicio;
    }

    public void setTipoInicio(String tipoInicio) {
        this.tipoInicio = tipoInicio;
    }


    public oracle.jbo.domain.Number getClienteUCE() {
        return clienteUCE;
    }

    public void setClienteUCE(oracle.jbo.domain.Number clienteUCE) {
        this.clienteUCE = clienteUCE;
    }


    public oracle.jbo.domain.Number getNumeroSerieDocumento() {
        return numeroSerieDocumento;
    }

    public void setNumeroSerieDocumento(oracle.jbo.domain.Number numeroSerieDocumento) {
        this.numeroSerieDocumento = numeroSerieDocumento;
    }
}
