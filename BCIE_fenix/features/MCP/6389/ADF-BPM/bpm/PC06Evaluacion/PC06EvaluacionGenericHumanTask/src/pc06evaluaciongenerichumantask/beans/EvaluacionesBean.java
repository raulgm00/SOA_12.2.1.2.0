package pc06evaluaciongenerichumantask.beans;

import java.io.IOException;
import java.io.Serializable;

import java.util.ResourceBundle;

import oracle.adf.share.logging.ADFLogger;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

public class EvaluacionesBean extends FenixPanelBean implements Serializable{
    @SuppressWarnings("compatibility:-1959532557471908250")
    private static final long serialVersionUID = -3658509681492834366L;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(EvaluacionesBean.class);
    public static final String BUNDLE = "pc06evaluaciongenerichumantask.PC06EvaluacionGenericHumanTaskBundle";

    public EvaluacionesBean() {
        super();
    }
    
    private Integer idSector;
    private Integer idSubSector;
    private boolean muestraCuestionarios = false;
    private boolean muestraEvaluaciones = false;
    //variables agregadas para nuevo metodo getPayLoadInformation
    private String idOperacion;
    private String idTarea;
    private String tipoEvaluacion;
    private Integer eventoEvaluacion;
    private String instanciaProceso;
    private String loginUsuario;
    private oracle.jbo.domain.Number numeroSerieDocumento;
    private Integer idProceso;

    //variable producto, parametro para herramienta estrategica
    private Integer idProducto;
    
    private String nombreTarea;
    private String finalizarTarea;
    
    private Integer aplicaAcciones;

    public void getPayloadInformation() throws WorkflowException, IOException
    {          
         XMLElement xmlPayload = BPMUtils.getPayloadInformation();
         
          if(LOGGER.isWarning())//if(LOGGER.isFiner())
         {
             //get the payload as a simple string, useful for debugging
             java.io.StringWriter writer = new java.io.StringWriter();
             xmlPayload.print(writer);
             String payloadAsString = writer.toString();
             LOGGER.log(ADFLogger.WARNING,payloadAsString);
         }
         
         
        NodeList nl;
        
        nl = xmlPayload.getElementsByTagName("CodigoTarea");
        if(nl.getLength()>0){
            idTarea=nl.item(0).getTextContent();
            printValue(idTarea);
        }
        
        nl = xmlPayload.getElementsByTagName("CodigoOperacion");
        if(nl.getLength()>0){
            idOperacion = nl.item(0).getTextContent();;
        }
        
        nl = xmlPayload.getElementsByTagName("tipoEvaluacion");
        if(nl.getLength()>0){
            tipoEvaluacion = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName("eventoEvaluacion");
        if(nl.getLength()>0 && !nl.equals("")){
            String idEvaluacion=nl.item(0).getTextContent();
            if(idEvaluacion != null && idEvaluacion != ""){
            eventoEvaluacion = Integer.parseInt(nl.item(0).getTextContent());
            }
        }
        //Se obtiene la instancia del proceso
        nl = xmlPayload.getElementsByTagName("InstanciaProceso");
        if(nl.getLength()>0){
            instanciaProceso = nl.item(0).getTextContent();
        }
        //Recuperar el CodigoProducto del payLoad para la herramienta estrategica valor de 
        //entrada para el Task Flow actualizarOperacionBTF
        nl = xmlPayload.getElementsByTagName("CodigoProducto");
        if(nl.getLength()>0 && !nl.equals(""))
        {   String idProductoString=nl.item(0).getTextContent();
            if(idProductoString!=null && idProductoString!="")
            {
            idProducto = Integer.parseInt(nl.item(0).getTextContent());
            }
        }
        
        nl = xmlPayload.getElementsByTagName("IdFlujo");
        if (nl.getLength() > 0) {
            try {
                numeroSerieDocumento = new oracle.jbo.domain.Number(nl.item(0).getTextContent());
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR, "Error al recibir el idFlujo :" + e.getClass() + "." + e);
            }
        }
        
        if(null != JSFUtils.resolveExpression("#{securityContext.userName}")){
            setLoginUsuario((String)JSFUtils.resolveExpression("#{securityContext.userName}"));
            //recuperar el nombre completo del usuario en sesión
            //setNombreUsuario(this.obtenerNombreCompleto(getLoginUsuario()));
        }
        
        nl = xmlPayload.getElementsByTagName("IdProceso");
        if(nl.getLength()>0 && !nl.equals("")){
            String codigoProceso=nl.item(0).getTextContent();
            if(codigoProceso != null && codigoProceso != ""){
            idProceso = Integer.parseInt(nl.item(0).getTextContent());
            }
        }
        
        LOGGER.warning("codigoTarea En evaluacion " + idTarea);
        LOGGER.warning("idOperacion En evaluacion " + idOperacion);
        LOGGER.warning("tipoEvaluacion En evaluacion " + tipoEvaluacion);
        LOGGER.warning("idProducto En evaluacion " + idProducto);
        LOGGER.warning("eventoEvaluacion En evaluaion " + eventoEvaluacion);
        LOGGER.warning("instanciaProceso En evaluacion " + instanciaProceso);
        LOGGER.warning("id Flujo En evaluacion " + numeroSerieDocumento);
        LOGGER.warning("idProceso: " + idProceso);
        //LOGGER.warning("loginUsuario " + loginUsuario);
    }
    
    public void printValue(String psObject) {
        System.out.println("Parameter: "  + psObject);
    }

    public void setIdSector(Integer idSector) {
        this.idSector = idSector;
    }

    public Integer getIdSector() {
        return idSector;
    }

    public void setIdSubSector(Integer idSubSector) {
        this.idSubSector = idSubSector;
    }

    public Integer getIdSubSector() {
        return idSubSector;
    }

    public void setMuestraCuestionarios(boolean muestraCuestionarios) {
        this.muestraCuestionarios = muestraCuestionarios;
    }

    public boolean isMuestraCuestionarios() {
        return muestraCuestionarios;
    }

    public void setMuestraEvaluaciones(boolean muestraEvaluaciones) {
        this.muestraEvaluaciones = muestraEvaluaciones;
    }

    public boolean isMuestraEvaluaciones() {
        return muestraEvaluaciones;
    }

    public void setIdOperacion(String idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getIdOperacion() {
        return idOperacion;
    }
    
    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }

    public String getIdTarea() {
        return idTarea;
    }


    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }


    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    
    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }
    
    public void setIdProceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public Integer getIdProceso() {
        return idProceso;
    }
    
    public void inicializarNombreTareaBPMPorTipoEvaluacion() {
        try {
            switch (idTarea) {
            case FenixConstants.PC06_GENERARCUESTIONARIOS:

                break;
            case FenixConstants.PC06_VALIDARDISENOCUESTIONARIOS:
                setNombreTarea(getStringFromBundle("VALIDAR_DISENIO_CUESTIONARIO"));
                break;
            case FenixConstants.PC06_VALIDARCUESTIONARIOS:
                setNombreTarea(getStringFromBundle("VALIDAR_CUESTIONARIOS_COMPLETADOS"));
                break;
            case FenixConstants.PC06_GENERARINFORMES:

                break;
            case FenixConstants.PC06_COMPLETARCUESTIONARIOS:

                break;
            case FenixConstants.PC06_REVISARINFORMES:

                break;
            case FenixConstants.PC06_REVISAR_DISENIO_CUESTIONARIO_IBCIE:
                eventoEvaluacion = getEventoEvaluacion();
                if (eventoEvaluacion == 2) {
                    setNombreTarea(getStringFromBundle("REVISAR_DISENIO_CUESTIONARIOS_MEDIO_TERMINO"));
                }
                if (eventoEvaluacion == 3) {
                    setNombreTarea(getStringFromBundle("REVISAR_DISENIO_CUESTIONARIOS_EX_POST"));
                }
                break;
            case FenixConstants.PC06_VALIDAR_DISENO_CUESTIONARIOS_IBCIE:
                eventoEvaluacion = getEventoEvaluacion();
                if (eventoEvaluacion == 2) {
                    setNombreTarea(getStringFromBundle("VALIDAR_DISENIO_CUESTIONARIOS_MEDIO_TERMINO"));
                }
                if (eventoEvaluacion == 3) {
                    setNombreTarea(getStringFromBundle("VALIDAR_DISENIO_CUESTIONARIO_EXPOST"));
                }
                break;
            case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_SUPERVISION_IBCIE:
                if (eventoEvaluacion == 2) {
                    setNombreTarea(getStringFromBundle("COMPLETAR_CUESTIONARIOS_MEDIO_TERMINO"));
                }
                if (eventoEvaluacion == 3) {
                    setNombreTarea(getStringFromBundle("COMPLETAR_CUESTIONARIOS_EX_POST"));
                }
                break;
            case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_IBCIE:
                if (eventoEvaluacion == 2) {
                    setNombreTarea(getStringFromBundle("COMPLETAR_CUESTIONARIOS_MEDIO_TERMINO"));
                }
                if (eventoEvaluacion == 3) {
                    setNombreTarea(getStringFromBundle("COMPLETAR_CUESTIONARIOS_EX_POST"));
                }
                break;
            case FenixConstants.PC06_VALIDAR_CUESTIONARIO_COMPLETO_IBCIE:
                if (eventoEvaluacion == 2) {
                    setNombreTarea(getStringFromBundle("VALIDAR_CUESTIONARIOS_COMPLETOS_MEDIO_TERMINO"));
                }
                if (eventoEvaluacion == 3) {
                    setNombreTarea(getStringFromBundle("VALIDAR_CUESTIONARIOS_COMPLETOS_EX_POST"));
                }
                break;
            case FenixConstants.PC06_VALIDAR_CUESTIONARIO_ODE_GENERAR_INFORME_IBCIE:
              setNombreTarea(getStringFromBundle("VALIDAR_CUESTIONARIOS_ODE_Y_GENERAR_INFORMES_I_BCIE"));
                break;
            case FenixConstants.PC06_VALIDAR_INFORME_IBCIE:
              eventoEvaluacion = getEventoEvaluacion();
              if(eventoEvaluacion == 2){
              setNombreTarea(getStringFromBundle("VALIDAR_INFORME_MEDIO_TERMINO"));
              }
              if(eventoEvaluacion == 3){
              setNombreTarea(getStringFromBundle("VALIDAR_INFORME_EX_POST"));
              }
                setAplicaAcciones(FenixConstants.PROCESO_IBCIE);
                break;
            case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_SEGUIMIENTO_SIEMAS_SUPERVISION:
                if (eventoEvaluacion == 4) {
                    setNombreTarea(getStringFromBundle("COMPLETAR_CUESTIONARIOS_SEGUIMIENTO_SIEMAS"));
                }
                break;
            case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_SEGUIMIENTO_SIEMAS:
                if (eventoEvaluacion == 4) {
                    setNombreTarea(getStringFromBundle("COMPLETAR_CUESTIONARIOS_SEGUIMIENTO_SIEMAS"));
                }
                break;
            case FenixConstants.PC06_VALIDAR_CUESTIONARIO_COMPLETO_SEGUIMIENTO_SIEMAS:
                if (eventoEvaluacion == 4) {
                    setNombreTarea(getStringFromBundle("VALIDAR_CUESTIONARIOS_COMPLETOS__SEGUIMIENTO_SIEMAS"));
                }
                break;
            case FenixConstants.PC06_GENERAR_INFORME_SEGUIMIENTO_SIEMAS:
              setNombreTarea(getStringFromBundle("GENERAR_INFORMES_SEGUIMIENTO_SIEMAS"));
                break;
            case FenixConstants.PC06_REVISAR_INFORME_SEGUIMIENTO_SIEMAS:
              setNombreTarea(getStringFromBundle("REVISAR_INFORME_SEGUIMIENTO_SIEMAS"));
                break;
            case FenixConstants.PC06_ACTUALIZAR_CONDICIONES_SIEMAS:
              setNombreTarea(getStringFromBundle("ACTUALIZAR_CONDICIONES_SIEMAS"));
                setAplicaAcciones(FenixConstants.PROCESO_SIEMAS);
                break;
            default:
                break;
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING,
                       "Error en inicializarNombreTareaBPMPorTipoEvaluacion." + e.getClass() + "." + e.getMessage());
        }
    }
    private String getStringFromBundle(String psKey)
    {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (resourceBundle != null)
        {
            txt = resourceBundle.getString(psKey);
        }

        return txt;
    }
    
    public void setFinalizarTarea(String finalizarTarea) {
        this.finalizarTarea = finalizarTarea;
    }

    public String getFinalizarTarea() {
        return finalizarTarea;
    }
    
    public void setEventoEvaluacion(Integer eventoEvaluacion) {
        this.eventoEvaluacion = eventoEvaluacion;
    }

    public Integer getEventoEvaluacion() {
        return eventoEvaluacion;
    }


    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }

    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setNumeroSerieDocumento(oracle.jbo.domain.Number numeroSerieDocumento) {
        this.numeroSerieDocumento = numeroSerieDocumento;
    }

    public oracle.jbo.domain.Number getNumeroSerieDocumento() {
        return numeroSerieDocumento;
    }

    public void setAplicaAcciones(Integer aplicaAcciones) {
        this.aplicaAcciones = aplicaAcciones;
    }

    public Integer getAplicaAcciones() {
        return aplicaAcciones;
    }
}
