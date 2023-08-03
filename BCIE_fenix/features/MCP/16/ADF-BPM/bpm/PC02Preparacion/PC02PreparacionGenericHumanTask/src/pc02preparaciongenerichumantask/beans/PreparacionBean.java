package pc02preparaciongenerichumantask.beans;

import java.io.IOException;
import java.io.Serializable;

import java.math.BigDecimal;

import oracle.adf.share.logging.ADFLogger;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

public class PreparacionBean extends FenixPanelBean implements Serializable {
   
    @SuppressWarnings("compatibility:5390647036034960473")
    private static final long serialVersionUID = 1L;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(PreparacionBean.class);

    private String idOperacion;
    private String codigoTarea;
    private Boolean desactivaEquipos;
    private Boolean tasa;
    private BigDecimal porcentajeBase;
    private BigDecimal montoSolicitado;
    private Integer eliminar;
    private String agregarModificar;
    private String instancia;
    private Float RAROC;
    private Boolean validarDocumento;
    private Boolean validarComentario;
    private Boolean validaAdquisiciones;
     
    private Boolean determinarRAROC;
    private Boolean hojaTerminosPCT;
    private Boolean revisarComisiones;
    private Boolean opinionTecnica;
    private Boolean analizarAdquisicion;
    private Boolean elaborarDoctumentoTecnicoOPD;
    private Boolean generarPlanMonitoreoEvaluacionOPD;
    private Boolean revisarEvidenciaCumplimientoOPD;
    
    private Integer contadorComisiones;
    private Integer contadorComisionesActuales;
    private Boolean solicitarDeterminarRAROC;
    private Boolean solicitarHojaTerminosPCT;
    private Boolean solicitarRevisarComisiones;
    private Boolean solicitarOpinionTecnica;
    private Boolean solicitarAnalizarAdquisicion;
    private Boolean solicitarElaborarDoctumentoTecnicoOPD;
    private Boolean solicitarGenerarPlanMonitoreoEvaluacionOPD;
    private Boolean solicitarRevisarEvidenciaCumplimientoOPD;

    private Boolean monto;
    private BigDecimal montoOcomision;
    private BigDecimal montoBase;
    private Boolean validaAnalisis;
    private Boolean errorMontoComision;
    private Boolean errorFrecuenciaPago;
    private Boolean errorPorcentaje;
    private Boolean errorTipoFrecuncia;
    private Boolean errorTipoMonto;
    private Boolean errorTipoObservacion;
    private Boolean errorTipomoneda;
    private Integer idProducto;
    private String observaciones;
    private Integer estadoComisionValidar;
    private String instanciaProceso;
    private Boolean esEstadoCompletado = Boolean.FALSE;

    private oracle.jbo.domain.Timestamp fechaValorFlexcube;
    public void setValidaAdquisiciones(Boolean validaAdquisiciones) {
        this.validaAdquisiciones = validaAdquisiciones;
    }

    public Boolean getValidaAdquisiciones() {
        return validaAdquisiciones;
    }


    public void getPayloadInformation() throws WorkflowException, IOException {
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();

        java.io.StringWriter writer = new java.io.StringWriter();
        xmlPayload.print(writer);
        String payloadAsString = writer.toString();
        LOGGER.finest("BPM Payload", payloadAsString);
        LOGGER.warning("payload:  " + payloadAsString);
 
        NodeList nl = xmlPayload.getElementsByTagName("CodigoOperacion");
        setIdOperacion(nl.item(0).getTextContent());
        LOGGER.warning("OperacionId: " + getIdOperacion()); 
            if(null!= getIdOperacion() && getIdOperacion().trim().length()>0){
                          }
            else{
                    setIdOperacion("0");
                }
        LOGGER.warning("OperacionId Actual: " +getIdOperacion());       
         NodeList n2 = xmlPayload.getElementsByTagName("CodigoTarea");
        setCodigoTarea(n2.item(0).getTextContent());
        LOGGER.warning("codigoTarea: "+ getCodigoTarea());
        if(null!= getCodigoTarea() && getCodigoTarea().trim().length()>0){
                      }
        else{
                setCodigoTarea("1");
            
            }
        if(getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_CONFORMAR_EQUIPO_DE_PREPARACION) ||
        getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_HOJA_TERMINOS_SEPRI) ||
        getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_RECOPILAR_INFORMACION) ||
        getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_REVISAR_COMISIONES))
        
        {
          NodeList n3 = xmlPayload.getElementsByTagName("MontoSolicitado");
          
          setMontoSolicitado(BigDecimal.ZERO);
          if(n3.getLength() > 0)
          {
            if(null!= n3.item(0).getTextContent() && n3.item(0).getTextContent().trim().length()>0)
            {
                setMontoSolicitado(new BigDecimal(n3.item(0).getTextContent()));          
            }
          }
        }
        if(getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ENVIAR_OPERACION_ANALISIS) ||
        getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_CONFORMAR_EQUIPO_DE_PREPARACION)){
            
                NodeList n6 = xmlPayload.getElementsByTagName("requiereDeterminarRAROC");
                NodeList n7 = xmlPayload.getElementsByTagName("requiereHojaTerminosPCT");
                NodeList n8 = xmlPayload.getElementsByTagName("requiereOpinionTecnica");
                NodeList n9 = xmlPayload.getElementsByTagName("requiereAnalisisAdquisiciones");
                NodeList n10 = xmlPayload.getElementsByTagName("requiereRevisionComisiones");
                NodeList n11 = xmlPayload.getElementsByTagName("requiereElaborarDocumentoTecnicoOPD");
                NodeList n12 = xmlPayload.getElementsByTagName("requiereGenerarPlanMonitoreoOPD");
                NodeList n13 = xmlPayload.getElementsByTagName("requiereRevisarEvidenciaCumplimientoOPD");
                
                if(null!=n6 && n6.getLength() >0){
                    if(Boolean.parseBoolean(n6.item(0).getTextContent())){
                        setDeterminarRAROC(Boolean.TRUE);
                        }
                    else{
                            setDeterminarRAROC(Boolean.FALSE);
                        }
                    
                    }   
                else{
                    setDeterminarRAROC(Boolean.FALSE);
                }
                
                    if(null!=n7 && n7.getLength() >0){
                    if(Boolean.parseBoolean(n7.item(0).getTextContent())){
                        setHojaTerminosPCT(Boolean.TRUE);
                    }
                        else{
                        setHojaTerminosPCT(Boolean.FALSE);
                        }
                    }   
                else{
                setHojaTerminosPCT(Boolean.FALSE);
                }
                if(null!=n8 && n8.getLength() >0){
                    if(Boolean.parseBoolean(n8.item(0).getTextContent())){
                        setOpinionTecnica(Boolean.TRUE);
                        }
                        else{
                        setOpinionTecnica(Boolean.FALSE);
                        }
                    }   
                else{
                setOpinionTecnica(Boolean.FALSE);
                }
                if(null!=n9 && n9.getLength() >0){
                    if(Boolean.parseBoolean(n9.item(0).getTextContent())){
                        setAnalizarAdquisicion(Boolean.TRUE);
                    }
                        else{
                        setAnalizarAdquisicion(Boolean.FALSE);
                        }
                    }   
                else{
                setAnalizarAdquisicion(Boolean.FALSE);
                }
                
                if(null!=n10 && n10.getLength() >0){
                    if(Boolean.parseBoolean(n10.item(0).getTextContent())){
                    setRevisarComisiones(Boolean.TRUE);
                    }
                        else{
                        setRevisarComisiones(Boolean.FALSE);
                        }
                    }   
                else{
                setRevisarComisiones(Boolean.FALSE);
                }
                
                if(null!=n11 && n11.getLength() >0){
                    if(Boolean.parseBoolean(n11.item(0).getTextContent())){
                    setElaborarDoctumentoTecnicoOPD(Boolean.TRUE);
                    }
                        else{
                        setElaborarDoctumentoTecnicoOPD(Boolean.FALSE);
                        }
                    }   
                else{
                setElaborarDoctumentoTecnicoOPD(Boolean.FALSE);
                }
                
                if(null!=n12 && n12.getLength() >0){
                    if(Boolean.parseBoolean(n12.item(0).getTextContent())){
                    setGenerarPlanMonitoreoEvaluacionOPD(Boolean.TRUE);
                    }
                        else{
                        setGenerarPlanMonitoreoEvaluacionOPD(Boolean.FALSE);
                        }
                    }   
                else{
                setGenerarPlanMonitoreoEvaluacionOPD(Boolean.FALSE);
                }
                
                if(null!=n13 && n13.getLength() >0){
                    if(Boolean.parseBoolean(n13.item(0).getTextContent())){
                    setRevisarEvidenciaCumplimientoOPD(Boolean.TRUE);
                    }
                        else{
                        setRevisarEvidenciaCumplimientoOPD(Boolean.FALSE);
                        }
                    }   
                else{
                setRevisarEvidenciaCumplimientoOPD(Boolean.FALSE);
                }
                
                LOGGER.log(ADFLogger.WARNING,">HNWS"+" Antes de ir al metodo, EquipoRAROC: "+ getDeterminarRAROC()+", EquipoPCT: "+ getHojaTerminosPCT()+", EquipoOpinion: "+ getOpinionTecnica()+", EquipoAdquisicion: "+ getAnalizarAdquisicion()+ ", EquipoComision: "+ getRevisarComisiones()
                           + ", EquipoElaborarDoctumentoTecnicoOPD(): "+ getElaborarDoctumentoTecnicoOPD()+ ", EquipoGenerarPlanMonitoreoEvaluacionOPD: "+ getGenerarPlanMonitoreoEvaluacionOPD()+ ", EquipoRevisarEvidenciaCumplimientoOPD: "+ getRevisarEvidenciaCumplimientoOPD());
        
                n6 = xmlPayload.getElementsByTagName("solicitaMasInfoDeterminarRAROC");
                n7 = xmlPayload.getElementsByTagName("solicitaMasInfoHojaTerminosPCT");
                n8 = xmlPayload.getElementsByTagName("solicitaMasInfoOpinionTecnica");
                n9 = xmlPayload.getElementsByTagName("solicitaMasInfoAnalisisAdquisiciones");
                n10 = xmlPayload.getElementsByTagName("solicitaMasInfoRevisionComisiones");
                n11 = xmlPayload.getElementsByTagName("solicitaMasInfoElaborarDocumentoTecnicoOPD");
                n12 = xmlPayload.getElementsByTagName("solicitaMasInfoGenerarPlanMonitoreoOPD");
                n13 = xmlPayload.getElementsByTagName("solicitaMasInfoRevisarEvidenciaCumplimientoOPD");
                                           
                    if(null!=n6 && n6.getLength() >0){
                                    if(Boolean.parseBoolean(n6.item(0).getTextContent())){
                                        setSolicitarDeterminarRAROC(Boolean.TRUE);
                                        }
                                    else{
                                            setSolicitarDeterminarRAROC(Boolean.FALSE);
                                        }
                                    }   
                                else{
                            setSolicitarDeterminarRAROC(Boolean.FALSE);
                                }
                                
                if(null!=n7 && n7.getLength() >0){
                                    if(Boolean.parseBoolean(n7.item(0).getTextContent())){
                                        setSolicitarHojaTerminosPCT(Boolean.TRUE);
                                    }
                                        else{
                                        setSolicitarHojaTerminosPCT(Boolean.FALSE);
                                        }
                                    }   
                                else{
                                setSolicitarHojaTerminosPCT(Boolean.FALSE);
                                }
                                    if(null!=n8 && n8.getLength() >0){
                                    if(Boolean.parseBoolean(n8.item(0).getTextContent())){
                                        setSolicitarOpinionTecnica(Boolean.TRUE);
                                        }
                                        else{
                                        setSolicitarOpinionTecnica(Boolean.FALSE);
                                        }
                                    }   
                                else{
                                setSolicitarOpinionTecnica(Boolean.FALSE);
                                }
                if(null!=n9 && n9.getLength() >0){
                                    if(Boolean.parseBoolean(n9.item(0).getTextContent())){
                                        setSolicitarAnalizarAdquisicion(Boolean.TRUE);
                                    }
                                        else{
                                        setSolicitarAnalizarAdquisicion(Boolean.FALSE);
                                        }
                                    }   
                                else{
                                setSolicitarAnalizarAdquisicion(Boolean.FALSE);
                                }

                if(null!=n10 && n10.getLength() >0){
                    if(Boolean.parseBoolean(n10.item(0).getTextContent())){
                    setSolicitarRevisarComisiones(Boolean.TRUE);
                    }
                        else{
                        setSolicitarRevisarComisiones(Boolean.FALSE);
                        }
                    }   
                else{
                setSolicitarRevisarComisiones(Boolean.FALSE);
                }
                
                if(null!=n11 && n11.getLength() >0){
                    if(Boolean.parseBoolean(n11.item(0).getTextContent())){
                        setSolicitarElaborarDoctumentoTecnicoOPD(Boolean.TRUE);
                    }
                    else{
                        setSolicitarElaborarDoctumentoTecnicoOPD(Boolean.FALSE);
                    }
                }   
                else{
                    setSolicitarElaborarDoctumentoTecnicoOPD(Boolean.FALSE);
                }
                
                if(null!=n12 && n12.getLength() >0){
                    if(Boolean.parseBoolean(n12.item(0).getTextContent())){
                        setSolicitarGenerarPlanMonitoreoEvaluacionOPD(Boolean.TRUE);
                    }
                    else{
                        setSolicitarGenerarPlanMonitoreoEvaluacionOPD(Boolean.FALSE);
                    }
                }   
                else{
                    setSolicitarGenerarPlanMonitoreoEvaluacionOPD(Boolean.FALSE);
                }
                
                if(null!=n13 && n13.getLength() >0){
                    if(Boolean.parseBoolean(n13.item(0).getTextContent())){
                        setSolicitarRevisarEvidenciaCumplimientoOPD(Boolean.TRUE);
                    }
                    else{
                        setSolicitarRevisarEvidenciaCumplimientoOPD(Boolean.FALSE);
                    }
                }   
                else{
                    setSolicitarRevisarEvidenciaCumplimientoOPD(Boolean.FALSE);
                }
                

                                LOGGER.log(ADFLogger.WARNING,">HNWS"+" Antes de ir al metodo, soliticarEquipoRAROC: "+ getSolicitarDeterminarRAROC()+", SolicitarEquipoPCT: "+getSolicitarHojaTerminosPCT()+", SolicitarEquipoOpinion: "+ getSolicitarOpinionTecnica()+", SolicitarEquipoAdquisicion: "+ getSolicitarAnalizarAdquisicion()+ ", SolicitarEquipoComision: "+ getSolicitarRevisarComisiones()
                                           + ", SolicitarEquipoElaborarDoctumentoTecnicoOPD: "+ getSolicitarElaborarDoctumentoTecnicoOPD()+ ", SolicitarEquipoGenerarPlanMonitoreoEvaluacionOPD: "+ getSolicitarGenerarPlanMonitoreoEvaluacionOPD()+ ", SolicitarRevisarEvidenciaCumplimientoOPD: "+ getSolicitarRevisarEvidenciaCumplimientoOPD());
                        
        
            }
        nl = xmlPayload.getElementsByTagName("CodigoProducto");
        
        if (null!=nl && nl.getLength() > 0)
        {
          String productoId = nl.item(0).getTextContent();
          if (null != productoId && productoId.trim().length() > 0)
          {
            setIdProducto(Integer.parseInt(productoId));
          }
          }
        else{
                setIdProducto(0);
            }
        nl = xmlPayload.getElementsByTagName("requiereDeterminarRAROC");
        n2 = xmlPayload.getElementsByTagName("RAROC");
        if((getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_ENVIAR_OPERACION_ANALISIS) ||
        getCodigoTarea().equalsIgnoreCase(FenixConstants.TAREA_DETERMINAR_RAROC)) && (null!=nl)){
            if(null!=n2 && n2.getLength() > 0){
            String nuevoValor= n2.item(0).getTextContent();
            if(null!= nuevoValor && nuevoValor.trim().length()>0 && !nuevoValor.equalsIgnoreCase("")){
            BigDecimal nuevoRAROC=new BigDecimal(nuevoValor);
            RAROC=nuevoRAROC.floatValue();
            }
                } 
        
        }
        LOGGER.warning("Valor ID Actual: " +getCodigoTarea());
        setTasa(Boolean.TRUE);
        setMonto(Boolean.FALSE);
        setAgregarModificar("");
        setValidarComentario(Boolean.FALSE);
        setValidarDocumento(Boolean.FALSE);
        setDesactivaEquipos(Boolean.FALSE);
        setContadorComisiones(0);
        setContadorComisionesActuales(0);
        
        //setMontoOcomision(BigDecimal.ZERO);
        //setMontoBase(0L);
        setValidaAnalisis(Boolean.FALSE);
        setValidaAdquisiciones(Boolean.FALSE);
        //setPorcentajeBase(BigDecimal.ZERO);
        //setRAROC(Float.parseFloat(null));
        setEliminar(0);
        setErrorFrecuenciaPago(Boolean.FALSE);
        setErrorMontoComision(Boolean.FALSE);
        setErrorPorcentaje(Boolean.FALSE);
        setErrorTipoFrecuncia(Boolean.FALSE);
        setErrorTipoMonto(Boolean.FALSE);
        setErrorTipoObservacion(Boolean.FALSE);
        setErrorTipomoneda(Boolean.FALSE);
        setFechaValorFlexcube(null);
        
        
        obtenerInstanciaProceso();
        
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
        
        String state = (null != ADFUtils.getBoundAttributeValue("state")) 
                     ? (String)ADFUtils.getBoundAttributeValue("state") : null;
        try {
            if (state != null) {
               
                LOGGER.warning("state value:" + state);
                setEsEstadoCompletado(state.equals("COMPLETED") ? Boolean.TRUE : Boolean.FALSE);
            }else{
                LOGGER.warning("pState es nulo");
                setEsEstadoCompletado(Boolean.FALSE);
            }
        } catch (Exception ex) {
            LOGGER.severe("Error al recuperar #{pageFlowScope.pStateBpm} :",ex);
            LOGGER.warning("pState no obtenido");
        }

        //setEsEstadoCompletado(Boolean.TRUE);
        LOGGER.warning("esEstadoCompletado : " + getEsEstadoCompletado());
        LOGGER.warning("Fuera de evaluarParametroPStateBpm");
    }
    
            
    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }
    
    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    public Boolean getTasa() {
        return tasa;
    }

    public void setTasa(Boolean tasa) {
        this.tasa = tasa;
    }

    public String getAgregarModificar() {
        return agregarModificar;
    }

    public void setAgregarModificar(String agregarModificar) {
        this.agregarModificar = agregarModificar;
    }

    public String getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(String idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public BigDecimal getMontoOcomision() {
        return montoOcomision;
    }

    public void setMontoOcomision(BigDecimal montoOcomision) {
        this.montoOcomision = montoOcomision;
    }

    public Boolean getValidarDocumento() {
        return validarDocumento;
    }

    public void setValidarDocumento(Boolean validarDocumento) {
        this.validarDocumento = validarDocumento;
    }

    public Boolean getValidarComentario() {
        return validarComentario;
    }

    public void setValidarComentario(Boolean validarComentario) {
        this.validarComentario = validarComentario;
    }

    public BigDecimal getMontoBase() {
        return montoBase;
    }

    public void setMontoBase(BigDecimal montoBase) {
        this.montoBase = montoBase;
    }

    public BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(BigDecimal montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public Boolean getValidaAnalisis() {
        return validaAnalisis;
    }

    public void setValidaAnalisis(Boolean validaAnalisis) {
        this.validaAnalisis = validaAnalisis;
    }
    
    public Float getRAROC() {
        return RAROC;
    }

    public void setRAROC(Float RAROC) {
        this.RAROC = RAROC;
    }

    public String getInstancia() {
        return instancia;
    }

    public void setInstancia(String instancia) {
        this.instancia = instancia;
    }

    public Boolean getDeterminarRAROC() {
        return determinarRAROC;
    }

    public void setDeterminarRAROC(Boolean determinarRAROC) {
        this.determinarRAROC = determinarRAROC;
    }

    public Boolean getHojaTerminosPCT() {
        return hojaTerminosPCT;
    }

    public void setHojaTerminosPCT(Boolean hojaTerminosPCT) {
        this.hojaTerminosPCT = hojaTerminosPCT;
    }

    public Boolean getRevisarComisiones() {
        return revisarComisiones;
    }

    public void setRevisarComisiones(Boolean revisarComisiones) {
        this.revisarComisiones = revisarComisiones;
    }

    public Boolean getOpinionTecnica() {
        return opinionTecnica;
    }

    public void setOpinionTecnica(Boolean opinionTecnica) {
        this.opinionTecnica = opinionTecnica;
    }

    public Boolean getAnalizarAdquisicion() {
        return analizarAdquisicion;
    }

    public void setAnalizarAdquisicion(Boolean analizarAdquisicion) {
        this.analizarAdquisicion = analizarAdquisicion;
    }

    public Boolean getMonto() {
        return monto;
    }

    public void setMonto(Boolean monto) {
        this.monto = monto;
    }

    public BigDecimal getPorcentajeBase() {
        return porcentajeBase;
    }

    public void setPorcentajeBase(BigDecimal porcentajeBase) {
        this.porcentajeBase = porcentajeBase;
    }

    public Integer getEliminar() {
        return eliminar;
    }

    public void setEliminar(Integer eliminar) {
        this.eliminar = eliminar;
    }
    public final void setDesactivaEquipos(Boolean desactivaEquipos) {
        this.desactivaEquipos = desactivaEquipos;
    }

    public final Boolean getDesactivaEquipos() {
        return desactivaEquipos;
    }
    public Boolean getErrorMontoComision() {
        return errorMontoComision;
    }

    public void setErrorMontoComision(Boolean errorMontoComision) {
        this.errorMontoComision = errorMontoComision;
    }

    public Boolean getErrorFrecuenciaPago() {
        return errorFrecuenciaPago;
    }

    public void setErrorFrecuenciaPago(Boolean errorFrecuenciaPago) {
        this.errorFrecuenciaPago = errorFrecuenciaPago;
    }

    public Boolean getErrorPorcentaje() {
        return errorPorcentaje;
    }

    public void setErrorPorcentaje(Boolean errorPorcentaje) {
        this.errorPorcentaje = errorPorcentaje;
    }

    public Boolean getErrorTipoFrecuncia() {
        return errorTipoFrecuncia;
    }

    public void setErrorTipoFrecuncia(Boolean errorTipoFrecuncia) {
        this.errorTipoFrecuncia = errorTipoFrecuncia;
    }

    public Boolean getErrorTipoMonto() {
        return errorTipoMonto;
    }

    public void setErrorTipoMonto(Boolean errorTipoMonto) {
        this.errorTipoMonto = errorTipoMonto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getContadorComisiones() {
        return contadorComisiones;
    }

    public void setContadorComisiones(Integer contadorComisiones) {
        this.contadorComisiones = contadorComisiones;
    }

    public Boolean getSolicitarDeterminarRAROC() {
        return solicitarDeterminarRAROC;
    }

    public void setSolicitarDeterminarRAROC(Boolean solicitarDeterminarRAROC) {
        this.solicitarDeterminarRAROC = solicitarDeterminarRAROC;
    }

    public Boolean getSolicitarHojaTerminosPCT() {
        return solicitarHojaTerminosPCT;
    }

    public void setSolicitarHojaTerminosPCT(Boolean solicitarHojaTerminosPCT) {
        this.solicitarHojaTerminosPCT = solicitarHojaTerminosPCT;
    }

    public Boolean getSolicitarRevisarComisiones() {
        return solicitarRevisarComisiones;
    }

    public void setSolicitarRevisarComisiones(Boolean solicitarRevisarComisiones) {
        this.solicitarRevisarComisiones = solicitarRevisarComisiones;
    }

    public Boolean getSolicitarOpinionTecnica() {
        return solicitarOpinionTecnica;
    }

    public void setSolicitarOpinionTecnica(Boolean solicitarOpinionTecnica) {
        this.solicitarOpinionTecnica = solicitarOpinionTecnica;
    }

    public Boolean getSolicitarAnalizarAdquisicion() {
        return solicitarAnalizarAdquisicion;
    }

    public void setSolicitarAnalizarAdquisicion(Boolean solicitarAnalizarAdquisicion) {
        this.solicitarAnalizarAdquisicion = solicitarAnalizarAdquisicion;
    }

    public Integer getContadorComisionesActuales() {
        return contadorComisionesActuales;
    }

    public void setContadorComisionesActuales(Integer contadorComisionesActuales) {
        this.contadorComisionesActuales = contadorComisionesActuales;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getErrorTipoObservacion() {
        return errorTipoObservacion;
    }

    public void setErrorTipoObservacion(Boolean errorTipoObservacion) {
        this.errorTipoObservacion = errorTipoObservacion;
    }

    public Integer getEstadoComisionValidar() {
        return estadoComisionValidar;
    }

    public void setEstadoComisionValidar(Integer estadoComisionValidar) {
        this.estadoComisionValidar = estadoComisionValidar;
    }

    public oracle.jbo.domain.Timestamp getFechaValorFlexcube() {
        return fechaValorFlexcube;
    }

    public void setFechaValorFlexcube(oracle.jbo.domain.Timestamp fechaValorFlexcube) {
        this.fechaValorFlexcube = fechaValorFlexcube;
    }

    public Boolean getErrorTipomoneda() {
        return errorTipomoneda;
    }

    public void setErrorTipomoneda(Boolean errorTipomoneda) {
        this.errorTipomoneda = errorTipomoneda;
    }
    
    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }

    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }
    
    public void setElaborarDoctumentoTecnicoOPD(Boolean elaborarDoctumentoTecnicoOPD) {
        this.elaborarDoctumentoTecnicoOPD = elaborarDoctumentoTecnicoOPD;
    }

    public Boolean getElaborarDoctumentoTecnicoOPD() {
        return elaborarDoctumentoTecnicoOPD;
    }

    public void setGenerarPlanMonitoreoEvaluacionOPD(Boolean generarPlanMonitoreoEvaluacionOPD) {
        this.generarPlanMonitoreoEvaluacionOPD = generarPlanMonitoreoEvaluacionOPD;
    }

    public Boolean getGenerarPlanMonitoreoEvaluacionOPD() {
        return generarPlanMonitoreoEvaluacionOPD;
    }

    public void setRevisarEvidenciaCumplimientoOPD(Boolean revisarEvidenciaCumplimientoOPD) {
        this.revisarEvidenciaCumplimientoOPD = revisarEvidenciaCumplimientoOPD;
    }

    public Boolean getRevisarEvidenciaCumplimientoOPD() {
        return revisarEvidenciaCumplimientoOPD;
    }

    public void setSolicitarElaborarDoctumentoTecnicoOPD(Boolean solicitarElaborarDoctumentoTecnicoOPD) {
        this.solicitarElaborarDoctumentoTecnicoOPD = solicitarElaborarDoctumentoTecnicoOPD;
    }

    public Boolean getSolicitarElaborarDoctumentoTecnicoOPD() {
        return solicitarElaborarDoctumentoTecnicoOPD;
    }

    public void setSolicitarGenerarPlanMonitoreoEvaluacionOPD(Boolean solicitarGenerarPlanMonitoreoEvaluacionOPD) {
        this.solicitarGenerarPlanMonitoreoEvaluacionOPD = solicitarGenerarPlanMonitoreoEvaluacionOPD;
    }

    public Boolean getSolicitarGenerarPlanMonitoreoEvaluacionOPD() {
        return solicitarGenerarPlanMonitoreoEvaluacionOPD;
    }

    public void setSolicitarRevisarEvidenciaCumplimientoOPD(Boolean solicitarRevisarEvidenciaCumplimientoOPD) {
        this.solicitarRevisarEvidenciaCumplimientoOPD = solicitarRevisarEvidenciaCumplimientoOPD;
    }

    public Boolean getSolicitarRevisarEvidenciaCumplimientoOPD() {
        return solicitarRevisarEvidenciaCumplimientoOPD;
    }
}
