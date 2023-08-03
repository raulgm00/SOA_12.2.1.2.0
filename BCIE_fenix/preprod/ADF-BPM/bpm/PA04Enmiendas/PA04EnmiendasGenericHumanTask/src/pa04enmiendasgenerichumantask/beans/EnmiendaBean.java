package pa04enmiendasgenerichumantask.beans;

import com.tangosol.dev.assembler.Return;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.jbo.Row;

import oracle.xml.parser.v2.XMLElement;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;


public class EnmiendaBean extends FenixPanelBean implements Serializable {
    @SuppressWarnings("compatibility:8926499292625740174")
    private static final long serialVersionUID = 1L;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(EnmiendaBean.class);

    private static final String CODIGO_OPERACION = "CodigoOperacion";
    private static final String CODIGO_TAREA = "CodigoTarea";
    private static final String REQ_GERENCIA = "requiereOGC";
    private static final String REQ_ASJUR = "requiereASJUR";
    private static final String REQ_COFI = "requiereCOFI";
    private static final String ID_ENMIENDA = "idEnmienda";
    private String idOperacion;
    private String codigoTarea;
    private String instanciaProceso;
    private Boolean requiereGerenciaCredito;
    private Boolean requiereASJUR;
    private Boolean requiereCOFI;
    private Boolean requiereCof;
    private Boolean formalizarEmnienda;
    private Boolean esDesobligacion;
    private Boolean esCambioEnPlazo;           
    private Boolean revisoJefatura;
    private Boolean activaChecRCF= Boolean.TRUE;
    private Boolean activaCheckFE= Boolean.TRUE;
    private Boolean activaChecED= Boolean.TRUE;
    private Boolean activaCheckEC= Boolean.TRUE;
    private Long idEnmienda;
    private Boolean activaCheckOCG;
    private Long claveOperacion;
    private Boolean requiereOT;
    private Boolean activaCheckOT;
    private Boolean activaCheckASJUR;
    private Boolean esEstadoCompletado = Boolean.FALSE;//Variable Estado BPM
    private String justificacion;
    
    public String requiereOgc_res ;
    public String requiereAsjur_res ;


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
            codigoTarea = nl.item(0).getTextContent();
        }

        nl = xmlPayload.getElementsByTagName(CODIGO_OPERACION);
        if (nl.getLength() > 0) {
            idOperacion = nl.item(0).getTextContent();
            if (null != idOperacion && !idOperacion.isEmpty()) {
                claveOperacion = Long.parseLong(idOperacion);
            }
        }

        nl = xmlPayload.getElementsByTagName(ID_ENMIENDA);
        if (nl.getLength() > 0) {
            String enmiendaId = nl.item(0).getTextContent();
            if (null != enmiendaId && enmiendaId.trim().length() > 0) {
                idEnmienda = Long.parseLong(enmiendaId);
            }
        }

        nl = xmlPayload.getElementsByTagName(REQ_GERENCIA);
        if (nl.getLength() > 0) {
            requiereGerenciaCredito = Boolean.parseBoolean(nl.item(0).getTextContent());
        } else {
            setRequiereGerenciaCredito(Boolean.FALSE);
        }
        activaCheckOCG = Boolean.FALSE;
        activaCheckASJUR  = Boolean.FALSE;
        /*
        nl = xmlPayload.getElementsByTagName(REQ_ASJUR);
        if (nl.getLength() > 0)
        {
          requiereASJUR = Boolean.parseBoolean(nl.item(0).getTextContent());
        }
        else{
        setRequiereASJUR(Boolean.FALSE);
        }*/
        setRequiereASJUR(Boolean.TRUE);
        nl = xmlPayload.getElementsByTagName(REQ_COFI);
        if (nl.getLength() > 0) {
            requiereCOFI = Boolean.parseBoolean(nl.item(0).getTextContent());
        } else {
            setRequiereCOFI(Boolean.FALSE);
        }
        revisoJefatura = Boolean.FALSE;

        instanciaProceso = BPMUtils.getCurrentTask().getProcessInfo().getInstanceId();
        // instanciaProceso="329961";
        //  instanciaProceso="10";
   
    }

    public void ingresaEnmienda() {
        // Valores predeterminados
        requiereASJUR = Boolean.FALSE;
        
        // Valores por validacion
        if (siRequiereOGC()) {
            requiereGerenciaCredito = Boolean.TRUE;
            activaCheckOCG = Boolean.TRUE;
        }
        
        if (siRequiereOT()) {
            requiereOT = Boolean.TRUE;
            activaCheckOT = Boolean.TRUE;
        }
        
        if (siRequiereASJUR()) {
            requiereASJUR = Boolean.TRUE;
            activaCheckASJUR = Boolean.TRUE;
        }
    }

    public EnmiendaBean() {
        super();
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

    public Boolean getRequiereGerenciaCredito() {
        return requiereGerenciaCredito;
    }

    public void setRequiereGerenciaCredito(Boolean requiereGerenciaCredito) {
        this.requiereGerenciaCredito = requiereGerenciaCredito;
    }

    public Boolean getRequiereASJUR() {
        return requiereASJUR;
    }

    public void setRequiereASJUR(Boolean requiereASJUR) {
        this.requiereASJUR = requiereASJUR;
    }

    public Boolean getRequiereCOFI() {
        return requiereCOFI;
    }

    public void setRequiereCOFI(Boolean requiereCOFI) {
        this.requiereCOFI = requiereCOFI;
    }

    public Boolean getRevisoJefatura() {
        return revisoJefatura;
    }

    public void setRevisoJefatura(Boolean revisoJefatura) {
        this.revisoJefatura = revisoJefatura;
    }

    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }

    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    public void setIdEnmienda(Long idEnmienda) {
        this.idEnmienda = idEnmienda;
    }

    public Long getIdEnmienda() {
        return idEnmienda;
    }

    


    public Boolean siRequiereOGC() {
        LOGGER.warning("Inicia metodo de obtener requiere OGC");
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("precargaOGCPorIdOperacionAndIdEnmienda");
        Long operacion = Long.parseLong(idOperacion);
        Number codigoDOperacion = (Number) operacion;
        Number codigoEnmienda = (Number) idEnmienda;
        LOGGER.warning("codigo operacion: " + codigoDOperacion);
        operationBinding.getParamsMap().put("idOperacion", codigoDOperacion);
        operationBinding.getParamsMap().put("idEnmienda", codigoEnmienda);
        LOGGER.warning("Inicia metodo AM determinar si rquiere CheckOCG");
        resultado = (Boolean) operationBinding.execute();
        LOGGER.warning("Resultado= " + resultado);
        if (!operationBinding.getErrors().isEmpty()) {
            resultado = Boolean.FALSE;
        }
        
    
        LOGGER.warning("resultado: " + resultado);
        return resultado;
    }
    
    public Boolean siRequiereOT() {
        LOGGER.warning("Inicia metodo de obtener requiere OGC");
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("precargaOT");
        Long operacion = Long.parseLong(idOperacion);
        Number codigoDOperacion = (Number) operacion;
        LOGGER.warning("codigo operacion: " + codigoDOperacion);
        operationBinding.getParamsMap().put("idOperacion", codigoDOperacion);
        LOGGER.warning("Inicia metodo AM determinar si rquiere CheckOCG");
        resultado = (Boolean) operationBinding.execute();
        LOGGER.warning("Resultado= " + resultado);
        if (!operationBinding.getErrors().isEmpty()) {
            resultado = Boolean.FALSE;
        }
        LOGGER.warning("resultado: " + resultado);
        return resultado;
    }
    
    public Boolean siRequiereASJUR() {
        LOGGER.warning("Inicia metodo siRequiereASJUR");
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("precargaASJURPorIdOperacionAndIdEnmienda");
        Long operacion = Long.parseLong(idOperacion);
        Number codigoDOperacion = (Number) operacion;
        Number codigoEnmienda = (Number) idEnmienda;
        LOGGER.warning("codigo operacion: " + codigoDOperacion);
        operationBinding.getParamsMap().put("idOperacion", codigoDOperacion);
        operationBinding.getParamsMap().put("idEnmienda", codigoEnmienda);
        LOGGER.warning("Inicia metodo AM determinar si rquiere CheckASJUR");
        resultado = (Boolean) operationBinding.execute();
        LOGGER.warning("Resultado= " + resultado);
        if (!operationBinding.getErrors().isEmpty()) {
            resultado = Boolean.FALSE;
        }
        LOGGER.warning("resultado: " + resultado);
        return resultado;
    }

    public void obtenerIdEnmienda() {
        LOGGER.warning("idEnmienda: " + idEnmienda);
        LOGGER.warning("instanciaProceso: " + instanciaProceso);
        if (null != idEnmienda &&
            (null != instanciaProceso && instanciaProceso.trim().length() > 0 && !instanciaProceso.isEmpty())) {
            BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding operationBinding = bindings.getOperationBinding("actualizarInstanciaEnmienda");

            operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
            operationBinding.getParamsMap().put("idEnmienda", idEnmienda);
            LOGGER.log(ADFLogger.WARNING, "execute actualizarInstanciaEnmienda");
            Boolean result = (Boolean) operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                LOGGER.log(ADFLogger.ERROR, "Error al ejecutar actualizarInstanciaEnmienda");
            } else {
                LOGGER.log(ADFLogger.WARNING, "Enmienda se actualizo correctamente");
                // idEnmienda = (Long) operationBinding.getResult();
            }
            if (!result) {
                LOGGER.log(ADFLogger.WARNING, "Error: Enmienda no se actualizo correctamente");
            }
            
        
            
        }
        
        
        try{

            LOGGER.log(ADFLogger.WARNING, "inicio execute getRowEnmiendaTCC");
            LOGGER.warning("idEnmienda: " + idEnmienda); 
            if (null != idEnmienda) {
                BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
                OperationBinding operationBinding = bindings.getOperationBinding("getRowEnmiendaTCC");
 
                operationBinding.getParamsMap().put("idEnmienda", idEnmienda);
                LOGGER.log(ADFLogger.WARNING, "execute getRowEnmiendaTCC");
                Row result = (Row) operationBinding.execute();
                
                requiereOgc_res = result.getAttribute("RequiereOgc").toString(); 
                requiereAsjur_res = result.getAttribute("RequiereAsjur").toString(); 
                LOGGER.log(ADFLogger.ERROR, "requiereOgc_res: "+requiereOgc_res);                
                LOGGER.log(ADFLogger.ERROR, "requiereAsjur_res: "+requiereAsjur_res);
                
                if(requiereAsjur_res.equals("1"))
                {
                    setRequiereASJUR(Boolean.TRUE);
                    LOGGER.log(ADFLogger.ERROR, "true requiereAsjur_res: "+requiereAsjur_res);
                }
                else{
                    setRequiereASJUR(Boolean.FALSE);
                    LOGGER.log(ADFLogger.ERROR, "false requiereAsjur_res: "+requiereAsjur_res);
                }
                
                if(requiereOgc_res.equals("1"))
                {
                    setRequiereGerenciaCredito(Boolean.TRUE);
                    LOGGER.log(ADFLogger.ERROR, "true requiereOgc_res: "+requiereOgc_res);
                }
                else
                {
                    setRequiereGerenciaCredito(Boolean.FALSE);
                    LOGGER.log(ADFLogger.ERROR, "false requiereOgc_res: "+requiereOgc_res);
                }
                 
                /*
                DCIteratorBinding voEnmiendaTcc = null;
                voEnmiendaTcc = ADFUtils.getDCBindingContainer().findIteratorBinding("EnmiendaTccVOIterator");
                
                voEnmiendaTcc.getRowAtRangeIndex(0).setAttribute("RequiereOgc", requiereOgc_res);                 
                voEnmiendaTcc.getRowAtRangeIndex(0).setAttribute("RequiereAsjur", requiereAsjur_res); 
                */
                LOGGER.log(ADFLogger.WARNING, "cargado execute getRowEnmiendaTCC");
            }
            LOGGER.log(ADFLogger.WARNING, "fin execute getRowEnmiendaTCC");
        }
        catch(Exception exp){
                LOGGER.log(ADFLogger.WARNING, "Error21: "+exp.getMessage());
            }
 

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


    public Boolean getActivaCheckOCG() {
        return activaCheckOCG;
    }

    public void setActivaCheckOCG(Boolean activaCheckOCG) {
        this.activaCheckOCG = activaCheckOCG;
    }

    public Long getClaveOperacion() {
        return claveOperacion;
    }

    public void setClaveOperacion(Long claveOperacion) {
        this.claveOperacion = claveOperacion;
    }

    public void setRequiereOT(Boolean requiereOT) {
        this.requiereOT = requiereOT;
    }

    public Boolean getRequiereOT() {
        return requiereOT;
    }

    public void setActivaCheckOT(Boolean activaCheckOT) {
        this.activaCheckOT = activaCheckOT;
    }

    public Boolean getActivaCheckOT() {
        return activaCheckOT;
    }

    public void setActivaCheckASJUR(Boolean activaCheckASJUR) {
        this.activaCheckASJUR = activaCheckASJUR;
    }

    public Boolean getActivaCheckASJUR() {
        return activaCheckASJUR;
    }
    
    public Boolean getEsEstadoCompletado() {
            return esEstadoCompletado;
        }
  

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
            this.esEstadoCompletado = esEstadoCompletado;
        }
    public void setRequiereCof(Boolean requiereCof) {
        this.requiereCof = requiereCof;
    }

  
    public void loadvalues(DisclosureEvent disclosureEvent){
        loadFlags();      
    }
    public void loadFlags(){
        Row row = null;
          Integer  IntCambiodePlazo= null;
          Integer  IntEsDesobligacion= null;
          Integer  IntRequiereCof= null;
          Integer  IntBanderaFormalizacion= null;
          String   StrJustificacion;
        
        row = getEnmiendaTccById();
        if (row.getAttribute("CambiodePlazo")!= null){
        System.out.println("obteniendo valores de CambiodePlazo :" +row.getAttribute("CambiodePlazo") );
        IntCambiodePlazo = (Integer)row.getAttribute("CambiodePlazo");
        
            if( IntCambiodePlazo ==1){
            esCambioEnPlazo =  Boolean.TRUE;    }
        }  
           
        if (row.getAttribute("EsDesobligacion")!= null){
        System.out.println("obteniendo valores de EsDesobligacion :" +row.getAttribute("EsDesobligacion") );
        IntEsDesobligacion = (Integer)row.getAttribute("EsDesobligacion");  
            if(IntEsDesobligacion ==1 ){
            esDesobligacion =  Boolean.TRUE;   }
               } 
     
        if (row.getAttribute("BanderaFormalizacion")!= null){                                                      
        IntBanderaFormalizacion = (Integer)row.getAttribute("BanderaFormalizacion");  
        
            if(IntBanderaFormalizacion ==1 ){
            formalizarEmnienda =  Boolean.TRUE;
                }  
        }
        if (row.getAttribute("RequiereCof")!= null){
            System.out.println("obteniendo valores de RequiereCof :" +row.getAttribute("RequiereCof") );
            IntRequiereCof = (Integer)row.getAttribute("RequiereCof");
            if(IntRequiereCof ==1 ){
            requiereCof =  Boolean.TRUE;  } 
               }
      
        if (row.getAttribute("Justificacion")!= null){
            System.out.println("obteniendo valores de Justificacion :" +row.getAttribute("Justificacion") );
            StrJustificacion = (String)row.getAttribute("Justificacion");
            justificacion = StrJustificacion;
        }
      
    }
        
   
    public  Boolean getRequiereCof() {

        return requiereCof;
     }
   
  
    public Row getEnmiendaTccById(){
        LOGGER.warning("Dentro de getEnmiendaTccById");
        Row row = null;
  
        System.out.println("ENTRANDO AL GgetEnmiendaTccById v2.2" );
      
      System.out.println("VALOR DE IDENMIEND "+ idEnmienda );
      BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();   
      OperationBinding operationBinding = bindings.getOperationBinding("getRowEnmiendaTCC");
      operationBinding.getParamsMap().put("idEnmienda", idEnmienda);
       
        row = (Row) operationBinding.execute();
        LOGGER.warning("row :" + row);
        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.warning("Error al ejecutar getEnmiendaTccById");
        }      
        LOGGER.warning("Fuera de getEnmiendaTccById");     
        return row;
    }



    public void setFormalizarEmnienda(Boolean formalizarEmnienda) {
        this.formalizarEmnienda = formalizarEmnienda;
    }

    public Boolean getFormalizarEmnienda() {
       
        return formalizarEmnienda;
    }

    public void setEsDesobligacion(Boolean esDesobligacion) {
        this.esDesobligacion = esDesobligacion;
    }

    public Boolean getEsDesobligacion() {
       
     
        return esDesobligacion;
    }

    public void setEsCambioEnPlazo(Boolean esCambioEnPlazo) {
        this.esCambioEnPlazo = esCambioEnPlazo;
    }

    public Boolean getEsCambioEnPlazo() {
       
        return esCambioEnPlazo;
    }

    public void setActivaChecRCF(Boolean activaChecRCF) {
        this.activaChecRCF = activaChecRCF;
    }

    public Boolean getActivaChecRCF() {
        return activaChecRCF;
    }

    public void setActivaCheckFE(Boolean activaCheckFE) {
        this.activaCheckFE = activaCheckFE;
    }

    public Boolean getActivaCheckFE() {
        return activaCheckFE;
    }

    public void setActivaChecED(Boolean activaChecED) {
        this.activaChecED = activaChecED;
    }

    public Boolean getActivaChecED() {
        return activaChecED;
    }

    public void setActivaCheckEC(Boolean activaCheckEC) {
        this.activaCheckEC = activaCheckEC;
    }

    public Boolean getActivaCheckEC() {
        return activaCheckEC;
    }


    public void valueListenerEC(ValueChangeEvent valueChangeEvent) {
      
        esCambioEnPlazo =(Boolean)valueChangeEvent.getNewValue();
       
    }
    public void valueListenerRC(ValueChangeEvent valueChangeEvent) {
      
        requiereCof =(Boolean)valueChangeEvent.getNewValue();
       
    }
    
    public void valueListenerFE(ValueChangeEvent valueChangeEvent) {
      
        formalizarEmnienda =(Boolean)valueChangeEvent.getNewValue();
       
    }
    
    public void valueListenerED(ValueChangeEvent valueChangeEvent) {
      
        esDesobligacion =(Boolean)valueChangeEvent.getNewValue();
       
    }
    
    public void valueListenerJUST(ValueChangeEvent valueChangeEvent) {
      
        justificacion =(String)valueChangeEvent.getNewValue();
       
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
}

