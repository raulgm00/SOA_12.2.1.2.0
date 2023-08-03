package pa07segcrediticioght.beans;

import java.io.IOException;

import java.io.Serializable;
import java.io.StringWriter;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;

import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

public class SeguimientoCrediticioBean extends FenixPanelBean  implements Serializable {
    @SuppressWarnings("compatibility:-87612789858418645")
    private static final long serialVersionUID = 1L;

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(SeguimientoCrediticioBean.class);
        
        private static final String CODIGO_CLIENTE = "IdCliente";
        private static final String CODIGO_TAREA = "CodigoTarea";
        private static final String TIPO_INICIO = "tipoInicio";
        private static final String TIPO_REVISION = "tipoRevision";
        private static final String SCR_VIGENTE = "scrVigente";
        private static final String PERSPECTIVA = "perspectiva";
        private static final String IDFELXCUBE = "IdFlexCube";
        private static final String NUM_SERIE_DOCUMENTO = "IdFlujo";
        private static final String CANCELAR_REVISION = "cancelarRevision";
        private static final String USUARIO_PROCESO = "UsuarioIniciaProceso";
        private String codigoTarea;
        private String idCliente;
        private String tipoInicio; 
        private String idFlexcube;
        
        private String tipoRevision;
        private String scrVigente;
        private String perspectiva;
        private String instanciaProceso;
        private String idTipoRevision;
        
        private Long idClienteFlex;
        private Integer scrVig;
        private Integer perspectivaVig;
        private oracle.jbo.domain.Number numeroSerieDocumento;
        
        private Boolean cancelar;
        private Boolean cambiaSCR;
        private Boolean cambiaPerspectiva;
        private Boolean cambioAmbos;
        private Integer claveTarea;
        private Long claveFlujo;
        
        private String loginUsuario;
        private String nombreUsuario;
        private Boolean esEstadoCompletado = Boolean.FALSE;//Variable Estado BPM



    public SeguimientoCrediticioBean() {
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
            
            NodeList nl;
            
            nl = xmlPayload.getElementsByTagName(IDFELXCUBE);
            if (nl.getLength() > 0) {
                LOGGER.warning("IdFLEXCUBE del payload: "+ nl.item(0).getTextContent());
            setIdFlexcube(nl.item(0).getTextContent());
                LOGGER.warning("IdFLEXCUBE: "+ getIdFlexcube());
            }
            
            nl = xmlPayload.getElementsByTagName(CODIGO_TAREA);
            if (nl.getLength() > 0) {
                codigoTarea = nl.item(0).getTextContent();
                if(null!=codigoTarea && codigoTarea.length()>0){
                    claveTarea=Integer.parseInt(codigoTarea);
                    }
            }
                
            nl = xmlPayload.getElementsByTagName(CODIGO_CLIENTE);
            if (nl.getLength() > 0) {
                idCliente = nl.item(0).getTextContent();
                if(null!=idCliente && idCliente.length()>0){
                        idClienteFlex=Long.parseLong(idCliente);
                    }
                else{
                        idClienteFlex=null;
                    }
            }
            
            nl = xmlPayload.getElementsByTagName(TIPO_INICIO);
            if (nl.getLength() > 0) {
                tipoInicio = nl.item(0).getTextContent();
                if(null!=tipoInicio){
                        tipoInicio=tipoInicio.toUpperCase();
                    }
            }
            
            nl = xmlPayload.getElementsByTagName(TIPO_REVISION);
            if (nl.getLength() > 0) {
                tipoRevision = nl.item(0).getTextContent();
            }
            
            nl = xmlPayload.getElementsByTagName(SCR_VIGENTE);
            if (nl.getLength() > 0) {
                String scrActual= nl.item(0).getTextContent();
               // scrVigente = nl.item(0).getTextContent();
                if(null!=scrActual && scrActual.length()>0){
                        scrVig=Integer.parseInt(scrActual);
                        scrVigente="SCR-".concat(scrActual);
                    }
                else{
                        scrVig=null;
                        scrVigente="";
                    }
            }
            
            nl = xmlPayload.getElementsByTagName(PERSPECTIVA);
            if (nl.getLength() > 0) {
                String perspectivaActual= nl.item(0).getTextContent();
               // perspectiva = nl.item(0).getTextContent();
                if(null!=perspectivaActual && perspectivaActual.length()>0){
                        perspectivaVig=Integer.parseInt(perspectivaActual);
                        if(perspectivaVig.compareTo(1)==0){perspectiva="Positiva";}
                        if(perspectivaVig.compareTo(2)==0){perspectiva="Negativa";}
                        if(perspectivaVig.compareTo(3)==0){perspectiva="Indefinida";}
                        if(perspectivaVig.compareTo(4)==0){perspectiva="Estable";}
                    }
                else{
                        perspectivaVig=null;
                        perspectiva="";
                    }
            }
            
            nl = xmlPayload.getElementsByTagName(NUM_SERIE_DOCUMENTO);
            if (nl.getLength() > 0) {
                try {
                    setNumeroSerieDocumento(new oracle.jbo.domain.Number(nl.item(0).getTextContent()));
                    claveFlujo=numeroSerieDocumento.longValue();
                } catch (Exception e) {
                    LOGGER.log(ADFLogger.ERROR, "Error al inicializar el valor de IdFlujo: " + e.getMessage());
                }
            }
            String usuario=null;
            nl = xmlPayload.getElementsByTagName(USUARIO_PROCESO);
            if (nl.getLength() > 0) {
                usuario = nl.item(0).getTextContent();
            }
            
            nl = xmlPayload.getElementsByTagName(CANCELAR_REVISION);
              
                              if(null!=nl && nl.getLength() >0){
                                                if(Boolean.parseBoolean(nl.item(0).getTextContent())){
                                                    setCancelar(Boolean.TRUE);
                                                }
                                                    else{
                                                    setCancelar(Boolean.FALSE);
                                                    }
                                                }   
                                            else{
                                            setCancelar(Boolean.FALSE);
                                            }
            
            instanciaProceso = BPMUtils.getCurrentTask().getProcessInfo().getInstanceId();
            //instanciaProceso = "600001";
            cambiaSCR=Boolean.FALSE;
            cambiaPerspectiva=Boolean.FALSE;
            if(null != JSFUtils.resolveExpression("#{securityContext.userName}")){
                setLoginUsuario((String)JSFUtils.resolveExpression("#{securityContext.userName}"));
                if(getLoginUsuario().equalsIgnoreCase("anonymous") || !getLoginUsuario().equalsIgnoreCase("weblogic")){
                        if(usuario!=null){
                                setLoginUsuario(usuario);
                            }
                    }
            }
            else{
                if(usuario!=null){
                        setLoginUsuario(usuario);
                    }
                }
        
            LOGGER.warning("cancelar :" + cancelar);
            LOGGER.warning("CodigoTarea :" + codigoTarea);
            LOGGER.warning("IdCliente: " + idCliente);
            LOGGER.warning("tipoInicio: " + tipoInicio);
            LOGGER.warning("tipoRevision: " + tipoRevision);
            LOGGER.warning("scrVigente: " + scrVigente);
            LOGGER.warning("perspectiva: " + perspectiva);
            LOGGER.warning("IdFlujo" + numeroSerieDocumento);
            LOGGER.warning("tipoInicio" + tipoInicio);
            cargaMora(idCliente);
            cargaSeguimiento(loginUsuario);
        }
        
        public void setIdCliente(String idCliente) {
            this.idCliente = idCliente;
        }

        public String getIdCliente() {
            return idCliente;
        }
        
        public void setCodigoTarea(String codigoTarea) {
            this.codigoTarea = codigoTarea;
        }

        public String getCodigoTarea() {
            return codigoTarea;
        }
        
        public void setTipoInicio(String tipoInicio) {
            this.tipoInicio = tipoInicio;
        }

        public String getTipoInicio() {
            return tipoInicio;
        }
        
        public void setTipoRevision(String tipoRevision) {
            this.tipoRevision = tipoRevision;
        }

        public String getTipoRevision() {
            return tipoRevision;
        }

        public void setScrVigente(String scrVigente) {
            this.scrVigente = scrVigente;
        }

        public String getScrVigente() {
            return scrVigente;
        }

        public void setPerspectiva(String perspectiva) {
            this.perspectiva = perspectiva;
        }

        public String getPerspectiva() {
            return perspectiva;
        }
        
        public void setInstanciaProceso(String instanciaProceso) {
            this.instanciaProceso = instanciaProceso;
        }

        public String getInstanciaProceso() {
            return instanciaProceso;
        }

    public String getIdFlexcube() {
        return idFlexcube;
    }

    public void setIdFlexcube(String idFlexcube) {
        this.idFlexcube = idFlexcube;
    }

    public Long getIdClienteFlex() {
        return idClienteFlex;
    }

    public void setIdClienteFlex(Long idClienteFlex) {
        this.idClienteFlex = idClienteFlex;
    }

    public Integer getScrVig() {
        return scrVig;
    }

    public void setScrVig(Integer scrVig) {
        this.scrVig = scrVig;
    }

    public Integer getPerspectivaVig() {
        return perspectivaVig;
    }

    public void setPerspectivaVig(Integer perspectivaVig) {
        this.perspectivaVig = perspectivaVig;
    }
    
    public void setNumeroSerieDocumento(oracle.jbo.domain.Number numeroSerieDocumento) {
        this.numeroSerieDocumento = numeroSerieDocumento;
    }

    public oracle.jbo.domain.Number getNumeroSerieDocumento() {
        return numeroSerieDocumento;
    }

    public Boolean getCambiaSCR() {
        return cambiaSCR;
    }

    public void setCambiaSCR(Boolean cambiaSCR) {
        this.cambiaSCR = cambiaSCR;
    }

    public Boolean getCambiaPerspectiva() {
        return cambiaPerspectiva;
    }

    public void setCambiaPerspectiva(Boolean cambiaPerspectiva) {
        this.cambiaPerspectiva = cambiaPerspectiva;
    }

    public Boolean getCambioAmbos() {
        return cambioAmbos;
    }

    public void setCambioAmbos(Boolean cambioAmbos) {
        this.cambioAmbos = cambioAmbos;
    }

    public String getIdTipoRevision() {
        return idTipoRevision;
    }

    public void setIdTipoRevision(String idTipoRevision) {
        this.idTipoRevision = idTipoRevision;
    }

    public Integer getClaveTarea() {
        return claveTarea;
    }

    public void setClaveTarea(Integer claveTarea) {
        this.claveTarea = claveTarea;
    }

    public Long getClaveFlujo() {
        return claveFlujo;
    }

    public void setClaveFlujo(Long claveFlujo) {
        this.claveFlujo = claveFlujo;
    }

    public Boolean getCancelar() {
        return cancelar;
    }

    public void setCancelar(Boolean cancelar) {
        this.cancelar = cancelar;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }
    
    public String nombreUsuario(String login){
        LOGGER.warning("Inicia metodo de nombreUsuario");
        String respuesta=login;
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = binding.getOperationBinding("obtenerNombre");
            operationBinding.getParamsMap().put("login", login);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                LOGGER.warning("Error al obtener el nombre de usuario");
                }
            else{
                    respuesta=(String)operationBinding.getResult();
                    LOGGER.warning("Nombre de usuario obtenido: " + respuesta);
                }
            LOGGER.warning("Termina metodo de nombreUsuario");
            LOGGER.warning("valor obtenido: " +respuesta);
        return respuesta;
        }
    
    public void cargaSeguimiento(String login){
            LOGGER.warning("Inicia metodo de cargaSeguimiento");
        setNombreUsuario(nombreUsuario(login));
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = binding.getOperationBinding("getIdSeguimientoCredicio");
            operationBinding.getParamsMap().put("login", login);
            operationBinding.getParamsMap().put("nombreUsuario", getNombreUsuario());
            operationBinding.getParamsMap().put("idCliente", idClienteFlex);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                LOGGER.warning("Error al obtener el registro de seguimiento crediticio");
                }
            LOGGER.warning("Termina metodo de cargaSeguimiento");
        }
    
    public void cargaMora(String idCliente){
            LOGGER.warning("Inicia metodo de cargaMora");
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = binding.getOperationBinding("setvarIdClienteFlex");
            operationBinding.getParamsMap().put("value", idCliente);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                LOGGER.warning("Error al obtener el registro de la mora del cliente");
                }
            LOGGER.warning("Termina metodo de cargaMora");
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }
}
