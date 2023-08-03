package pc03analisisgenerichumantask.beans;

import java.io.IOException;
import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

public class AnalisisBean
  extends FenixPanelBean
  implements Serializable
{
  @SuppressWarnings("compatibility:3221270033493526060")
  private static final long serialVersionUID = 2L;
  public static final ADFLogger LOGGER = ADFLogger.createADFLogger(AnalisisBean.class);

  private Integer idProducto;
  private String idOperacion;
  private Long idOperacionLong;
  private String codigoTarea;
  private Boolean isRiesgoCredito;
  private Integer analisisId;
  private Boolean cuentaConProyectoResolucion;
  private Integer idTipoDocumentoProyectoResolucion = FenixConstants.TD_PROYECTORESOLUCION;
  private String oldTipoOpinion;
  private Boolean reqOpinionJuridica;
  private Boolean reqIBCIE;
  private Boolean reqSiemas;
  private Boolean reqOpinion;
  private Boolean esLineaGlobalCreditoIFI;
  private Boolean visualizaAprobacion;
    private Boolean visualizaAprobacionTIR;
  private Boolean jefatura;
  private String opinionTipo;
  private Boolean positiva;
  private Boolean negativa;
  
  private Boolean existeCondicion;
  
    private String instanciaProceso;
    
    // Elaborar Dictamen AC/EAT
    private Boolean solicitaMasInfoAnalistaCredito = Boolean.FALSE;
    
    //Variable Estado BPM
    private Boolean esEstadoCompletado = Boolean.FALSE;

    public void setEsLineaGlobalCreditoIFI(Boolean esLineaGlobalCreditoIFI)
  {
    this.esLineaGlobalCreditoIFI = esLineaGlobalCreditoIFI;
  }

  public Boolean getEsLineaGlobalCreditoIFI()
  {
    return esLineaGlobalCreditoIFI;
  }

  public final void setBanderaTIR(Boolean banderaTIR)
  {
    this.banderaTIR = banderaTIR;
  }

  public final Boolean getBanderaTIR()
  {
    return banderaTIR;
  }

  public final void setRequiereSCR(Boolean requiereSCR)
  {
    this.requiereSCR = requiereSCR;
  }

  public final Boolean getRequiereSCR()
  {
    return requiereSCR;
  }
  private Boolean banderaTIR;
  private Boolean requiereSCR;

  public AnalisisBean()
  {
    super();
  }

  public void setIsRiesgoCredito(Boolean isRiesgoCredito)
  {
    this.isRiesgoCredito = isRiesgoCredito;
  }

  public Boolean getIsRiesgoCredito()
  {
    return isRiesgoCredito;
  }

  public void setAnalisisId(Integer analisisId)
  {
    this.analisisId = analisisId;
  }

  public Integer getAnalisisId()
  {
    return analisisId;
  }

  public void setCodigoTarea(String codigoTarea)
  {
    this.codigoTarea = codigoTarea;
  }

  public String getCodigoTarea()
  {
    return codigoTarea;
  }

  public void setIdOperacion(String idOperacion)
  {
    this.idOperacion = idOperacion;
  }

  public String getIdOperacion()
  {
    return idOperacion;
  }
  
    public void setIdOperacionLong(Long idOperacionLong) {
        this.idOperacionLong = idOperacionLong;
    }

    public Long getIdOperacionLong() {
        return idOperacionLong;
    }

  public void getPayloadInformation()
    throws WorkflowException, IOException
  {
    XMLElement xmlPayload = BPMUtils.getPayloadInformation();

    if (LOGGER.isWarning()) // if(LOGGER.isFine())
    {
      //get the payload as a simple string, useful for debugging
      java.io.StringWriter writer = new java.io.StringWriter();
      xmlPayload.print(writer);
      String payloadAsString = writer.toString();
      //LOGGER.fine("BPM Payload: {0}", payloadAsString);
      LOGGER.log(ADFLogger.WARNING, payloadAsString); 
    }
    NodeList nl;

    nl = xmlPayload.getElementsByTagName("CodigoTarea");
    if (nl.getLength() > 0)
    {
      codigoTarea = nl.item(0).getTextContent();
    }

    nl = xmlPayload.getElementsByTagName("CodigoOperacion");
    if (nl.getLength() > 0)
    {
      idOperacion = nl.item(0).getTextContent();
      
      idOperacionLong = (idOperacion.equals("") || idOperacion.isEmpty()) 
                      ? null : new Long(idOperacion);
      
    }
      setEsLineaGlobalCreditoIFI(Boolean.FALSE);
      setReqOpinionJuridica(Boolean.FALSE);
      isRiesgoCredito = Boolean.FALSE;
      setRequiereSCR(Boolean.FALSE);
      setReqIBCIE(Boolean.FALSE);
      setReqSiemas(Boolean.FALSE);
      setReqOpinion(Boolean.FALSE);
      setBanderaTIR(Boolean.FALSE);

      
    nl = xmlPayload.getElementsByTagName("tieneRiesgoCredito");

    if (nl.getLength() > 0)
    {
      String tieneRiesgoCredito = nl.item(0).getTextContent();
      if (null != tieneRiesgoCredito && tieneRiesgoCredito.trim().length() > 0)
      {
        if (Boolean.parseBoolean(nl.item(0).getTextContent()))
        {
          isRiesgoCredito = Boolean.TRUE;
        }
        else
        {
          isRiesgoCredito = Boolean.FALSE;
        }
      }
      else
      {
        isRiesgoCredito = Boolean.FALSE;
      }
    }
    
    nl = xmlPayload.getElementsByTagName("requireSCR");

    if (nl.getLength() > 0)
    {
      String tieneSCR = nl.item(0).getTextContent();
      if (null != tieneSCR && tieneSCR.trim().length() > 0)
      {
  
        if (Boolean.parseBoolean(nl.item(0).getTextContent()))
        {
          setRequiereSCR(Boolean.TRUE);
        }
        else
        {
          setRequiereSCR(Boolean.FALSE);
        }
      }
      else
      {
        setRequiereSCR(Boolean.FALSE);
      }
    }
    
    nl = xmlPayload.getElementsByTagName("CodigoProducto");
    
    if (nl.getLength() > 0)
    {
      String productoId = nl.item(0).getTextContent();
      if (null != productoId && productoId.trim().length() > 0)
      {
        setIdProducto(Integer.parseInt(productoId));
        // START FNXII-2136 Validar Linea Global Credito a IFI
        if (productoId.equalsIgnoreCase("3"))
        {
          esLineaGlobalCreditoIFI = Boolean.TRUE;
        }
        else
        {
          esLineaGlobalCreditoIFI = Boolean.FALSE;
        }
        // END FNXII-2136 Validar Linea Global Credito a IFI
      }
      else
      {
        setIdProducto(0);
      }
    }
    
    nl = xmlPayload.getElementsByTagName("requireOpinionJuridica");

    if (nl.getLength() > 0)
    {
      String tieneOpinion = nl.item(0).getTextContent();
      if (null != tieneOpinion && tieneOpinion.trim().length() > 0)
      {
  
        if (Boolean.parseBoolean(nl.item(0).getTextContent()))
        {
          setReqOpinionJuridica(Boolean.TRUE);
        }
        else
        {
          setReqOpinionJuridica(Boolean.FALSE);
        }
      }
      else
      {
        setReqOpinionJuridica(Boolean.FALSE);
      }
    }
    setVisualizaAprobacion(Boolean.FALSE);
      setVisualizaAprobacionTIR(Boolean.FALSE);
      if (codigoTarea.equalsIgnoreCase(Integer.toString(FenixConstants.PC03_APROBARCALIFICACION)))
      {
              setVisualizaAprobacion(Boolean.TRUE);
              if(!getEsLineaGlobalCreditoIFI()){
                      setVisualizaAprobacionTIR(Boolean.TRUE);
                  }
          }
        
    String elaborarDictamenAC = Integer.toString(FenixConstants.PC03_ELABORARDICTAMENAC);
    
    if (codigoTarea.equalsIgnoreCase(elaborarDictamenAC))
    {
      if (getIsRiesgoCredito() && !getRequiereSCR())
      {
        setBanderaTIR(Boolean.TRUE);
      }
      else
      {
        setBanderaTIR(Boolean.FALSE);
      }
    }
    
      nl = xmlPayload.getElementsByTagName("solicitaMasInfoAnalistaCredito");

      if (nl.getLength() > 0)
      {
        String solicitaMasInfAC = nl.item(0).getTextContent();
        if (null != solicitaMasInfAC && solicitaMasInfAC.trim().length() > 0)
        {
      
          if (Boolean.parseBoolean(nl.item(0).getTextContent()))
          {
            setSolicitaMasInfoAnalistaCredito(Boolean.TRUE);
          }
          else
          {
            setSolicitaMasInfoAnalistaCredito(Boolean.FALSE);
          }
        }
        else
        {
          setSolicitaMasInfoAnalistaCredito(Boolean.FALSE);
        }
      } else {
          setSolicitaMasInfoAnalistaCredito(Boolean.FALSE);
      }
      
      obtenerInstanciaProceso();
    
    LOGGER.warning("RiesgoCredito " + getIsRiesgoCredito());
    LOGGER.warning("RequiereSCR " + getRequiereSCR());
    LOGGER.warning("IdProducto " + getIdProducto());
    LOGGER.warning("RequiereOpinion " + getReqOpinionJuridica());
    LOGGER.warning("codigoTarea"+ codigoTarea);
    LOGGER.warning("idOperacion" + idOperacion);
    LOGGER.warning("idOperacionLong" + idOperacionLong);      
    LOGGER.warning("isRiesgoCredito" + isRiesgoCredito);
    LOGGER.warning("banderaTIR" + banderaTIR);
    LOGGER.warning("solicitaMasInfoAnalistaCredito" + getSolicitaMasInfoAnalistaCredito());
    LOGGER.warning("instanciaProceso: " + instanciaProceso);
      
  }
  
    public void obtenerInstanciaProceso(){
        LOGGER.warning("Dentro de obtenerInstanciaProceso");        
        String instancia = null;
        try{
            if(BPMUtils.getCurrentTask() != null &&
                BPMUtils.getCurrentTask().getProcessInfo() != null &&
                    BPMUtils.getCurrentTask().getProcessInfo().getInstanceId() != null){
                instancia = BPMUtils.getCurrentTask().getProcessInfo().getInstanceId();
            } 
        }catch(Exception e){
            LOGGER.severe("Error al obtener la Instancia del Proceso", e);
        }
                
        if(instancia != null){
            setInstanciaProceso(instancia);
        }
        LOGGER.warning("instanciaProceso :"+instanciaProceso);
        LOGGER.warning("Fuera de obtenerInstanciaProceso");      
    }
    
    public void evaluarParametroPStateBpm(){
        LOGGER.warning("Dentro de evaluarParametroPStateBpm");

        String state =  (null != ADFUtils.getBoundAttributeValue("state")) 
                    ? (String)ADFUtils.getBoundAttributeValue("state"): null; 
        LOGGER.warning("Var State: "+state);

        try {
            if (state != null) {
                LOGGER.warning("state :" + state);                
                setEsEstadoCompletado(state.equalsIgnoreCase("COMPLETED") ? Boolean.TRUE : Boolean.FALSE);
            }else{
                LOGGER.warning("pState es nulo");
                setEsEstadoCompletado(Boolean.FALSE);
            }
        } catch (Exception ex) {
            LOGGER.warning("Error al recuperar NodeList state :",ex);
            LOGGER.warning("pState no obtenido");
        }

        LOGGER.warning("esEstadoCompletado : " + getEsEstadoCompletado());
        LOGGER.warning("Fuera de evaluarParametroPStateBpm");        
    }
            
    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }
    
    public String getInstanciaProceso() {
        return instanciaProceso;
    }

  public void printValue(String psObject)
  {
    System.out.println("Parameter: " + psObject);
  }


    public void setCuentaConProyectoResolucion(Boolean cuentaConProyectoResolucion) {
        this.cuentaConProyectoResolucion = cuentaConProyectoResolucion;
    }
    
  public Integer getIdProducto()
  {
    return idProducto;
  }

    public Boolean getCuentaConProyectoResolucion() {
        return cuentaConProyectoResolucion;
    }

    public Integer getIdTipoDocumentoProyectoResolucion() {
        return idTipoDocumentoProyectoResolucion;
    }
    public void setIdTipoDocumentoProyectoResolucion(Integer v) {
        idTipoDocumentoProyectoResolucion = v;
    }

    public void setOldTipoOpinion(String oldTipoOpinion) {
        this.oldTipoOpinion = oldTipoOpinion;
    }
    
  public void setIdProducto(Integer idProducto)
  {
    this.idProducto = idProducto;
  }

  public Boolean getReqOpinionJuridica()
  {
    return reqOpinionJuridica;
  }

  public void setReqOpinionJuridica(Boolean reqOpinionJuridica)
  {
    this.reqOpinionJuridica = reqOpinionJuridica;
  }

    public String getOldTipoOpinion() {
        return oldTipoOpinion;
    }
    
  public Boolean getReqIBCIE()
  {
    return reqIBCIE;
  }

  public void setReqIBCIE(Boolean reqIBCIE)
  {
    this.reqIBCIE = reqIBCIE;
  }

  public Boolean getReqSiemas()
  {
    return reqSiemas;
  }

  public void setReqSiemas(Boolean reqSiemas)
  {
    this.reqSiemas = reqSiemas;
  }

    public Boolean getReqOpinion() {
        return reqOpinion;
    }

    public void setReqOpinion(Boolean reqOpinion) {
        this.reqOpinion = reqOpinion;
    }

    public Boolean getVisualizaAprobacion() {
        return visualizaAprobacion;
    }

    public void setVisualizaAprobacion(Boolean visualizaAprobacion) {
        this.visualizaAprobacion = visualizaAprobacion;
    }

    public Boolean getVisualizaAprobacionTIR() {
        return visualizaAprobacionTIR;
    }

    public void setVisualizaAprobacionTIR(Boolean visualizaAprobacionTIR) {
        this.visualizaAprobacionTIR = visualizaAprobacionTIR;
    }

    public Boolean getJefatura() {
        return jefatura;
    }

    public void setJefatura(Boolean jefatura) {
        this.jefatura = jefatura;
    }

    public String getOpinionTipo() {
        return opinionTipo;
    }

    public void setOpinionTipo(String opinionTipo) {
        this.opinionTipo = opinionTipo;
    }

    public Boolean getPositiva() {
        return positiva;
    }

    public void setPositiva(Boolean positiva) {
        this.positiva = positiva;
    }

    public Boolean getNegativa() {
        return negativa;
    }

    public void setNegativa(Boolean negativa) {
        this.negativa = negativa;
    }

    public void setSolicitaMasInfoAnalistaCredito(Boolean solicitaMasInfoAnalistaCredito) {
        this.solicitaMasInfoAnalistaCredito = solicitaMasInfoAnalistaCredito;
    }

    public Boolean getSolicitaMasInfoAnalistaCredito() {
        return solicitaMasInfoAnalistaCredito;
    }


    public Boolean getExisteCondicion() {
        return existeCondicion;
    }

    public void setExisteCondicion(Boolean existeCondicion) {
        this.existeCondicion = existeCondicion;
    }


    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }
}
