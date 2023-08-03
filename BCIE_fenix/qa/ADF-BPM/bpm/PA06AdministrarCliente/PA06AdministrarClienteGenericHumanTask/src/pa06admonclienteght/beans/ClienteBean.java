package pa06admonclienteght.beans;

import java.io.IOException;
import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.jbo.domain.Number;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.model.vo.CrearClienteVORowImpl.AttributesEnum;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

public class ClienteBean extends FenixPanelBean  implements Serializable
{
  @SuppressWarnings("compatibility:-399878459590134272")
  private static final long serialVersionUID = 1L;

  public static final ADFLogger LOGGER = ADFLogger.createADFLogger(ClienteBean.class);

  /**
   * Constates para manejo de Payload
   */
  private static final String CODIGO_CLIENTE = "idCliente";
  private static final String CODIGO_TAREA = "CodigoTarea";
  private static final String CAMBIO_DEFINITIVO = "cambioDefinitivo";
  
  private static final String RAZON_SOCIAL = "razonSocial";
  private static final String TIPO_PERSONA = "idTipoPersona";
  private static final String SECTOR = "idSector";
  private static final String PAIS = "idPais";
  private static final String DIA_PAGO = "diaPago";
  private static final String OFICINA = "idOficina";
  private static final String ABREVIATURA = "abreviatura";
  private static final String TIPO_CLIENTE = "idTipoCliente";
  private static final String TIPO_INSTITUCION = "idTipoInstitucion";
  private static final String GRUPO_ECONOMICO = "idGrupoEconomico";
  private static final String NUMERO_IDENTIFICACION = "numeroIdentificacion";
  private static final String BIC_CODE = "bicCode";
  private static final String DIRECCION = "direccion";
  private static final String REQ_ENVIO_AVISO_COBRO_AUT = "reqEnvioAvisoCobroAut";
  private static final String ID_FLEXCUBE = "idFlexcube";  
  private static final String RESPONSABLE_CLIENTE = "responsableCliente";
  private Boolean esEstadoCompletado = Boolean.FALSE;//Variable Estado BPM

  private String codigoTarea;
  private String codigoCliente;
  private Boolean esCambioDefinitivo;
  
  private Number nIdCliente;
  private String sRazonSocial;
  private String sBicCode;
  private String sDireccion;
  private Number nTipoPersona;
  private Number nSector; 
  private Number nPais;
  private Number nDiaPago;
  private Number nOficina;
  private String sAbreviatura;
  private Number nTipoCliente;
  private Number nTipoInstitucion;
  private Number nGrupoEconomico;
  private String sNumeroIdentificacion;
  private Number nReqEnvioAvisoCobroAut;
  private String sIdFlexcube;
  private String sEjecutivo;
  public ClienteBean()
  {
    super();
  }
  
  public void getPayloadInformation()
    throws WorkflowException, IOException
  {
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
    if (nl.getLength() > 0)
    {
      codigoTarea = nl.item(0).getTextContent();
    }
    
    nl = xmlPayload.getElementsByTagName(CODIGO_CLIENTE);
    if (nl.getLength() > 0)
    {
      codigoCliente = nl.item(0).getTextContent();
    }
    
    nl = xmlPayload.getElementsByTagName(RAZON_SOCIAL);
    if (nl.getLength() > 0)
    {
      sRazonSocial = nl.item(0).getTextContent();
    }
    
    nl = xmlPayload.getElementsByTagName(BIC_CODE);
    if (nl.getLength() > 0)
    {
        sBicCode = nl.item(0).getTextContent();
    }
    
    nl = xmlPayload.getElementsByTagName(DIRECCION);
    if (nl.getLength() > 0)
    {
        sDireccion = nl.item(0).getTextContent();
    }
    
    nl = xmlPayload.getElementsByTagName(TIPO_PERSONA);
    if (nl.getLength() > 0)
    {
      try
      {
        nTipoPersona = new Number (nl.item(0).getTextContent());
      }
      catch(Exception ex)
      {
        nTipoPersona = null;
      }
    }
    
    nl = xmlPayload.getElementsByTagName(SECTOR);
    if (nl.getLength() > 0)
    {
      try
      {
        nSector = new Number (nl.item(0).getTextContent());
      }
      catch(Exception ex)
      {
        nSector = null;
      }
    }
    
    nl = xmlPayload.getElementsByTagName(PAIS);
    if (nl.getLength() > 0)
    {
      try
      {
        nPais = new Number (nl.item(0).getTextContent());
      }
      catch(Exception ex)
      {
        nPais = null;
      }
    }
    
    nl = xmlPayload.getElementsByTagName(DIA_PAGO);
    if (nl.getLength() > 0)
    {
      try
      {
        nDiaPago = new Number (nl.item(0).getTextContent());
      }
      catch(Exception ex)
      {
        nDiaPago = null;
      }
    }
    
    nl = xmlPayload.getElementsByTagName(OFICINA);
    if (nl.getLength() > 0)
    {
      try
      {
        nOficina = new Number (nl.item(0).getTextContent());
      }
      catch(Exception ex)
      {
        nOficina = null;
      }
    }
    
    nl = xmlPayload.getElementsByTagName(ABREVIATURA);
    if (nl.getLength() > 0)
    {
      sAbreviatura = nl.item(0).getTextContent();
    }
    
    nl = xmlPayload.getElementsByTagName(TIPO_CLIENTE);
    if (nl.getLength() > 0)
    {
      try
      {
        nTipoCliente = new Number (nl.item(0).getTextContent());
      }
      catch(Exception ex)
      {
        nTipoCliente = null;
      }
    }
    
    nl = xmlPayload.getElementsByTagName(TIPO_INSTITUCION);
    if (nl.getLength() > 0)
    {
      try
      {
        nTipoInstitucion = new Number (nl.item(0).getTextContent());
      }
      catch(Exception ex)
      {
        nTipoInstitucion = null;
      }
    }
    
    nl = xmlPayload.getElementsByTagName(GRUPO_ECONOMICO);
    if (nl.getLength() > 0)
    {
      try
      {
        nGrupoEconomico = new Number (nl.item(0).getTextContent());
      }
      catch(Exception ex)
      {
        nGrupoEconomico = null;
      }
    }
    
    nl = xmlPayload.getElementsByTagName(NUMERO_IDENTIFICACION);
    if (nl.getLength() > 0)
    {
      sNumeroIdentificacion = nl.item(0).getTextContent();
    }

      nl = xmlPayload.getElementsByTagName(REQ_ENVIO_AVISO_COBRO_AUT);
      try
      {
        nReqEnvioAvisoCobroAut = new Number (nl.item(0).getTextContent());
      }
      catch(Exception ex)
      {
        nReqEnvioAvisoCobroAut = null;
      }
    
    nl = xmlPayload.getElementsByTagName(CODIGO_CLIENTE);
    if (nl.getLength() > 0)
    {
      try
      {
        nIdCliente = new Number (nl.item(0).getTextContent());
      }
      catch(Exception ex)
      {
        nIdCliente = null;
      }
    }
    
    esCambioDefinitivo = Boolean.FALSE;
    nl = xmlPayload.getElementsByTagName(CAMBIO_DEFINITIVO);
    if (nl.getLength() > 0)
    {
      if(nl.item(0).getTextContent().equalsIgnoreCase("true"))
      {
        esCambioDefinitivo = Boolean.TRUE;
      }
    }
      
      nl = xmlPayload.getElementsByTagName(RESPONSABLE_CLIENTE);
      if (nl.getLength() > 0)
      {
        sEjecutivo = nl.item(0).getTextContent();
      }
      
      nl = xmlPayload.getElementsByTagName(ID_FLEXCUBE);
      if (nl.getLength() > 0)
      {
        sIdFlexcube = nl.item(0).getTextContent();
      }

    LOGGER.warning("codigoTarea :" + codigoTarea);
    LOGGER.warning("codigoCliente :" + codigoCliente);
    LOGGER.warning("esCambioDefinitivo :" + esCambioDefinitivo);
    LOGGER.warning("nIdCliente :" + nIdCliente);
    LOGGER.warning("sRazonSocial :" + sRazonSocial);
    LOGGER.warning("sBicCode :" + sBicCode);
    LOGGER.warning("sDireccion :" + sDireccion);
    LOGGER.warning("nTipoPersona :" + nTipoPersona);
    LOGGER.warning("nSector :" + nSector);
    LOGGER.warning("nPais :" + nPais);
    LOGGER.warning("nDiaPago :" + nDiaPago);
    LOGGER.warning("nOficina :" + nOficina);
    LOGGER.warning("sAbreviatura :" + sAbreviatura);
    LOGGER.warning("nTipoCliente :" + nTipoCliente);
    LOGGER.warning("nTipoInstitucion :" + nTipoInstitucion);
    LOGGER.warning("nGrupoEconomico :" + nGrupoEconomico);
    LOGGER.warning("sNumeroIdentificacion :" + sNumeroIdentificacion);
    LOGGER.warning("nReqEnvioAvisoCobroAut :" + nReqEnvioAvisoCobroAut);
    LOGGER.warning("sEjecutivo :" + sEjecutivo);
    LOGGER.warning("sIdFlexcube :" + sIdFlexcube);      
  }
  
    public void evaluarParametroPStateBpm()  {
    LOGGER.warning("Dentro de evaluarParametroPStateBpm State");
    
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

  public void setCodigoTarea(String codigoTarea)
  {
    this.codigoTarea = codigoTarea;
  }

  public String getCodigoTarea()
  {
    return codigoTarea;
  }


  public void setSRazonSocial(String sRazonSocial)
  {
    this.sRazonSocial = sRazonSocial;
  }

  public String getSRazonSocial()
  {
    return sRazonSocial;
  }

  public void setNTipoPersona(Number nTipoPersona)
  {
    this.nTipoPersona = nTipoPersona;
  }

  public Number getNTipoPersona()
  {
    return nTipoPersona;
  }

  public void setNSector(Number nSector)
  {
    this.nSector = nSector;
  }

  public Number getNSector()
  {
    return nSector;
  }

  public void setNPais(Number nPais)
  {
    this.nPais = nPais;
  }

  public Number getNPais()
  {
    return nPais;
  }

  public void setNDiaPago(Number nDiaPago)
  {
    this.nDiaPago = nDiaPago;
  }

  public Number getNDiaPago()
  {
    return nDiaPago;
  }

  public void setNOficina(Number nOficina)
  {
    this.nOficina = nOficina;
  }

  public Number getNOficina()
  {
    return nOficina;
  }

  public void setSAbreviatura(String sAbreviatura)
  {
    this.sAbreviatura = sAbreviatura;
  }

  public String getSAbreviatura()
  {
    return sAbreviatura;
  }

  public void setNTipoCliente(Number nTipoCliente)
  {
    this.nTipoCliente = nTipoCliente;
  }

  public Number getNTipoCliente()
  {
    return nTipoCliente;
  }

  public void setNTipoInstitucion(Number nTipoInstitucion)
  {
    this.nTipoInstitucion = nTipoInstitucion;
  }

  public Number getNTipoInstitucion()
  {
    return nTipoInstitucion;
  }

  public void setNGrupoEconomico(Number nGrupoEconomico)
  {
    this.nGrupoEconomico = nGrupoEconomico;
  }

  public Number getNGrupoEconomico()
  {
    return nGrupoEconomico;
  }

  public void setSNumeroIdentificacion(String sNumeroIdentificacion)
  {
    this.sNumeroIdentificacion = sNumeroIdentificacion;
  }

  public String getSNumeroIdentificacion()
  {
    return sNumeroIdentificacion;
  }

  public void setCodigoCliente(String codigoCliente)
  {
    this.codigoCliente = codigoCliente;
  }

  public String getCodigoCliente()
  {
    return codigoCliente;
  }

  public void setNIdCliente(Number nIdCliente)
  {
    this.nIdCliente = nIdCliente;
  }

  public Number getNIdCliente()
  {
    return nIdCliente;
  }

  public void setEsCambioDefinitivo(Boolean esCambioDefinitivo)
  {
    this.esCambioDefinitivo = esCambioDefinitivo;
  }

  public Boolean getEsCambioDefinitivo()
  {
    return esCambioDefinitivo;
  }
  
  public Boolean getEsEstadoCompletado() {
            return esEstadoCompletado;
  }

  public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
            this.esEstadoCompletado = esEstadoCompletado;
  }
  
    public void setSBicCode(String sBicCode)
    {
      this.sBicCode = sBicCode;
    }

    public String getSBicCode()
    {
      return sBicCode;
    }
    
    public void setSDireccion(String sDireccion)
    {
      this.sDireccion = sDireccion;
    }

    public String getSDireccion()
    {
      return sDireccion;
    }

    public void setNReqEnvioAvisoCobroAut(Number nReqEnvioAvisoCobroAut)
    {
      this.nReqEnvioAvisoCobroAut = nReqEnvioAvisoCobroAut;
    }

    public Number getNReqEnvioAvisoCobroAut()
    {
      return nReqEnvioAvisoCobroAut;
    }

    public void setSIdFlexcube(String sIdFlexcube) {
        this.sIdFlexcube = sIdFlexcube;
    }

    public String getSIdFlexcube() {
        return sIdFlexcube;
    }

    public void setSEjecutivo(String sEjecutivo) {
        this.sEjecutivo = sEjecutivo;
    }

    public String getSEjecutivo() {
        return sEjecutivo;
    }
}
