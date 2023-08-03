package pc05formalizaciongenerichumantask.bean;

import java.io.IOException;
import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.vo.consultarconfigcondicionesfinancieras.clases.TablaDinamicaVOBean;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;
import org.bcie.fenix.common.FenixConstants;

import org.bcie.fenix.common.utils.JSFUtils;

import org.w3c.dom.NodeList;

public class FormalizacionBean extends FenixPanelBean implements Serializable {
    
    @SuppressWarnings("compatibility:-3004599809761991878")
    private static final long serialVersionUID = -6090517373497148100L;
    
    private static ADFLogger logger = null;
    
    /**
     * Constantes para manejo de Payload "FormalizacionErrorPayload.xsd"
     * OSB > MDS > Resources > BPM > PC05
     */

    /**
     * Header > Operacion
     */
    public static final String CODIGO_OPERACION = "CodigoOperacion";
    

    /**
     * Header > Tarea
     */
    public static final String CODIGO_TAREA = "CodigoTarea";
    public static final String CODIGO_PRODUCTO = "CodigoProducto";
    public static final String SOLICITUD_CONTRATACION = "solicitudContratacion";
    public static final String ID_LOTE = "idEnmienda";
    /**
     * FormalizacionPayload > ConfigGateways
     */
    public static final String ES_RETORNO = "esRetorno";

    /**
     * Configuración de botones 
     * Respetar Orden de botones conforme al Pantalla PC05ElaborarBorradorContrarto
     */
    public static final String EBC_ENVIAR_COFI = "ENVIAR_COFI";
    public static final String EBC_ENVIAR_RESPONSABLE = "ENVIAR_RESPONSABLE";
    public static final String EBC_FINALIZAR = "FINALIZAR";
    public static final String EBC_SOLICITAR_AJUSTE_POR_FUENTES_EXTERNAS = "SOLICITAR_AJUSTE_POR_FUENTES_EXTERNAS";
    /**
     * Variables agregadas para nuevo metodo getPayLoadInformation
     */
    private oracle.jbo.domain.Number operacionId;
    private String idOperacion;
    private String idTarea;
    private String instanciaProceso;
    private Long idContrato;
    private Integer idProducto;
    private Boolean solicitudContratacion;
    private Long idLote; //FNXII-6103 solo aplica para cuando incia proceso de Foramlización
    private Boolean esRetorno;
    

    //Variables custodiar contrato
    private String numeroCustodia;

    // Renders de página Definir recursos externos y condiciones
    private Integer cuentaConRecExternos; // Radio "Se cuenta con recursos externos para esta operación"
    
    // Renders de página Adjuntar contrato firmado
    private Boolean esFechaVigenciaIgualFechaFirma = Boolean.FALSE; // Checkbox Fecha de vigencia es igual a fecha de firma.
    
    // Renders de página Asignación de recursos
    private Boolean esObtenidoEnFecha = Boolean.FALSE; // Checkbox Obtenido en fecha en popup Agregar Fuente
    
    private String numeroResolucion;
    /**
     * US 2012: Variables de Modelamiento para agregar logica de visibilidad y enrutamiento de peticiones en el proceso BPM
     * @param :mapaDinamico, tipoProducto, estaConfiguradoProducto, estaConfiguradoProducto
     * @since 04/05/2021
     * @by Raul Garcia
     */
    //Variable que determinara si algunos componentes de la vista deben ser o no cargados a traves de todo el flujo del prtoceso
    private Map<String, TablaDinamicaVOBean> mapaDinamico = new HashMap<String, TablaDinamicaVOBean>();
    private Integer estaConfiguradoProducto;
    private Integer v_render_btn_cofi;
    private Integer v_render_btn_resp;
    private Integer v_render_btn_final;
    private Integer v_render_btn_sol;
    private String accionBoton;

    public FormalizacionBean() {
        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void getPayloadInformation() throws WorkflowException, IOException { 
        logger.log(ADFLogger.WARNING, "Inside getPayloadInformation.");
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();
        NodeList nl;
         
        try {
            java.io.StringWriter writer = new java.io.StringWriter();
            xmlPayload.print(writer);
            String payloadAsString = writer.toString();
            logger.log(ADFLogger.WARNING, "- Payload Formalizacion -");
            logger.log(ADFLogger.WARNING, payloadAsString);
        }
        catch(IOException e){
            logger.log(ADFLogger.ERROR, e.getMessage());
        }
        
        nl = xmlPayload.getElementsByTagName(CODIGO_OPERACION);
        if(nl != null && (nl.getLength()>0)) {
            idOperacion = nl.item(0).getTextContent();
            
            try {
                if((idOperacion != null) && (idOperacion.trim().length() > 0))
                    operacionId = new oracle.jbo.domain.Number(Long.valueOf(idOperacion));
            } catch (Exception e) {
                logger.log(ADFLogger.ERROR, "Error al inicializar el valor de operacionId: " + e.getMessage());
            }
        }
        
        nl = xmlPayload.getElementsByTagName(CODIGO_TAREA);
        if(nl != null && (nl.getLength()>0)) {
            idTarea=nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(SOLICITUD_CONTRATACION);
        if (nl != null) {
            if (nl.getLength() > 0) {
                setSolicitudContratacion(Boolean.parseBoolean(nl.item(0).getTextContent()));    
            } else {
                setSolicitudContratacion(Boolean.FALSE);    
            }
        } else {
            setSolicitudContratacion(Boolean.FALSE);
        }
        
        // FNXII-7128, se valida para procesos anteriores de Formalizacion
        if (getSolicitudContratacion()) {
            //FNXII-6103 se agrega el idEnmienda como idLote para el proceso de implementacion
            nl = xmlPayload.getElementsByTagName(ID_LOTE);
            if (nl != null) {
                if (!nl.item(0).getTextContent().equalsIgnoreCase("") && nl.item(0).getTextContent().length() > 0) {
                    setIdLote(Long.parseLong(nl.item(0).getTextContent()));
                }
            }
        } else {
            logger.warning("Solicitud contratacion es falso, no se obtendra idLote/idEnmienda.");
        }
        
        instanciaProceso = BPMUtils.getCurrentTask().getProcessInfo().getInstanceId();
        //test gabriel
       // instanciaProceso ="4277246";
        
        nl = xmlPayload.getElementsByTagName(CODIGO_PRODUCTO);
        if(nl != null && (nl.getLength()>0)) {
            String idProductoString=nl.item(0).getTextContent().trim();
            if(idProductoString!=null && idProductoString!="")
            {
                setIdProducto(Integer.parseInt(nl.item(0).getTextContent()));
		
            }
        }
        // US :2012
        // Busqueda de parametros en Payload BPM

        nl = xmlPayload.getElementsByTagName(ES_RETORNO);
        if (nl != null) {

            if (nl.getLength() > 0) {
                setEsRetorno(Boolean.parseBoolean(nl.item(0).getTextContent()));
            } else {
                setEsRetorno(Boolean.FALSE);
            }
        } else {
            setEsRetorno(Boolean.FALSE);
        }
        logger.log(ADFLogger.WARNING, "Id Operacion: " + idOperacion);
        logger.log(ADFLogger.WARNING, "Id producto: " + getIdProducto());
        logger.log(ADFLogger.WARNING, "Es Retorno: " + getEsRetorno());
        logger.log(ADFLogger.WARNING, "El producto esta configurado para botones dinamicos: "  + getEstaConfiguradoProducto());
        logger.log(ADFLogger.WARNING, "Id Tarea Bpm: " + idTarea);
        logger.log(ADFLogger.WARNING, "Instancia Proceso: " + instanciaProceso);
        logger.log(ADFLogger.WARNING, "solicitudContratacion: " + getSolicitudContratacion());
    }
    
    @SuppressWarnings("oracle.jdeveloper.java.unchecked-conversion-or-cast")
    public void obtenerNumeroResolucion() {
        logger.warning("Entra en obtenerNumeroResolucion.");
        
        OperationBinding numResolucionBinding = null;
        try{
            logger.warning("Valor de la operacion : " + Long.valueOf(getIdOperacion()));
            numResolucionBinding = ADFUtils.findOperation("obtenerUltimoNumeroResolucion");
            numResolucionBinding.getParamsMap().put("idOperacion", Long.valueOf(getIdOperacion()));
            Object numResolucionObject = numResolucionBinding.execute();
            
            if(null != numResolucionObject){
                numeroResolucion = (String)numResolucionObject;
                logger.warning("Numero de la resolucion obtenida : " + numeroResolucion);
            }else{
                logger.warning("No se recupero el numero de la resolucion");
            }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en obtenerNumeroResolucion.", e);
        }
    }
    /**
     * Busca la parametrización de los botones definidos para la tarea (Dinamicamente)
     * @param String p_nombreTabla, Integer p_idTareaBpm
     * @idTareas =   No.55 , ... ,
     * @since 04/05/2021
     * @by Raul Garcia
     */
    @SuppressWarnings("unchecked")
    public void obtenerDatosTablaDinamica() {
        logger.warning("Entra en obtenerDatosTablaDinamica.");
        logger.log(ADFLogger.WARNING, "¿Es Retorno?> "  + this.getEsRetorno() );
        
        //Validar siempre y cuando sea retorno sobre la configuración de los productos a dinamicos a conciderar
        if(this.getEsRetorno()){
        
        OperationBinding opb = null;
        String nombreTabla = null;
        Integer identificadorTarea = 0;
        boolean ban = false;
        Object mapaObjetos = null;
        Map<String, TablaDinamicaVOBean> mapaDinamicoLocal = new HashMap<String, TablaDinamicaVOBean>();

        try {
            logger.warning("Valor de la operacion : " + Long.valueOf(this.getIdOperacion()));
            logger.warning("Valor de IdTarea: " + this.getIdTarea());
            logger.warning("Valor de idProducto: " + this.getIdProducto());
            
            switch (this.getIdTarea()) {
            // ¿Estas dentro del proceso de Borrador de contrato?
            case FenixConstants.PC05_ELABORAR_BORRADOR_CONTRATO:
                nombreTabla = FenixConstants.TCA_TAREA_CON_BTN_DIN;
                identificadorTarea = Integer.valueOf(getIdTarea());
                ban = true;
                break;
            default:
                ban = false;
            }
            
            logger.warning("Valor de ban: " + ban );
                try{
                /* Si encuentra un identificador valida para aplicar logica de visibilidad de componentes ejecuta la busqueda de la tabla de verdad(visibilidad)*/
                //Se obtiene el metodo publicado en la pagina de Definiciones
                opb = ADFUtils.findOperation("obtenerDatos");
                //Se inyectan parametros de busqeuda : String , Integer
                opb.getParamsMap().put("p_nombreTabla", nombreTabla);
                opb.getParamsMap().put("p_idTareaBpm", identificadorTarea);
                opb.getParamsMap().put("p_IdProducto", this.getIdProducto());    
                mapaObjetos = opb.execute();
                    
                /*logger.log(ADFLogger.WARNING, "La ejecución del método BINDINGS ¿ fue ejecutado con exito?: " + mapaObjetos);*/
                
                
                        if (null != mapaObjetos) {
                            //Se abstrae el Mapa de valores asociado al webservices
                            mapaDinamicoLocal = (Map<String, TablaDinamicaVOBean>) mapaObjetos;
                            //this.setMapaDinamico(mapaDinamicoLocal);
                            /* Validar la disponibilidad del atributo Payload "esRetorno"
                            /* Valioda la longuitud del mapa*/
                            if (mapaDinamicoLocal.size() > 1) {
                               //Candidato a validar visibilidad de botones.
                               setConfigurtacionVisibilidad(mapaDinamicoLocal, ban);
                            } else {
                                /*Seteo de visibilidad normal de bótones*/
                                setConfigurtacionVisibilidad(null, Boolean.FALSE);
                                logger.warning("No se se logro recuperar los datos dinamicos de la tabla " + nombreTabla);
                                logger.warning("Error " + mapaDinamicoLocal);
                                logger.log(ADFLogger.ERROR, "Error " + mapaDinamicoLocal);
                                
                            }
                        } else {
                            /*Seteo de visibilidad normal de bótones*/
                            setConfigurtacionVisibilidad(null, Boolean.FALSE);
                            logger.log(ADFLogger.ERROR, "No se se logro recuperar los datos dinamicos de la tabla " + nombreTabla);
                        }
                }catch(Exception e){
                    setConfigurtacionVisibilidad(null, Boolean.FALSE);
                    logger.log(ADFLogger.ERROR, "Ocurrio un error al ejecutar la operación obtenerDatos directamente de los bindings: " + e);
                
                }



        } catch (Exception e) {
            /*Seteo de visibilidad normal de bótones*/
            setConfigurtacionVisibilidad(null, Boolean.FALSE);
            logger.log(ADFLogger.ERROR, "Error en ejecutar el método obtenerDatosTablaDinamica : " + e);
       }
        }else{
            logger.warning("Valor de la operacion : " + Long.valueOf(getIdOperacion()));
            logger.warning("Valor de IdTarea: " + this.getIdTarea());
            logger.log(ADFLogger.WARNING, "Se aplicará la configuración por default de botones");
            setConfigurtacionVisibilidad(null, Boolean.FALSE);
        }

        logger.warning("Fuera de obtenerDatosTablaDinamica.");
    }
    
    /**
     * Asigna la visibilidad por casa boton disponible para Pantala ADF (Dinamicamente)
     * @param Map<String, TablaDinamicaVOBean>
     * @since 04/05/2021
     * @by Raul Garcia
     */
    public void setConfigurtacionVisibilidad(Map<String, TablaDinamicaVOBean> m, Boolean ban) {
        logger.log(ADFLogger.WARNING, "Dentro setConfigurtacionVisibilidad");
         String [] arrayTiposProducto ;
         Boolean enviar = Boolean.FALSE;
         //¿Es candidato a buscar el tipo de producto a configurar?
         if(ban){
             //Recorrido de objetos (botones)
             for(Map.Entry <String,TablaDinamicaVOBean> entry : m.entrySet()){
                 
                //String a = entry.getValue().getProductosConfigurados();
                 Integer a = entry.getValue().getIdproductoConfigurado();
                 //arrayTiposProducto = a.split(",");
                 //for(String producto: arrayTiposProducto){
                 //logger.log(ADFLogger.WARNING, "ArrayTiposProducto :"  + Integer.parseInt(producto) + " ProductoBean: " + this.getIdProducto() );
                         
                     if( this.getIdProducto() ==  a ){
                         enviar = Boolean.TRUE;
                         this.setEstaConfiguradoProducto(1);
                         logger.log(ADFLogger.WARNING, "Producto encontrado :"  + a + " Accion: " + entry.getValue().getAccion());
                         //break;
                     }else{
                         this.setEstaConfiguradoProducto(0);
                        }
                 //}
                 
                 Boolean esActivo = getEstaConfiguradoProducto() == 1 ? Boolean.TRUE : Boolean.FALSE;
                 logger.log(ADFLogger.WARNING, "¿Cumple con alguno de los productos configurados [PrestamoTradicional/Deuda Subordinada/Prestamo de Operacion]? : "  + esActivo );
                 
                 if(esActivo){
                     asignacionVisibilidadPorProducto(entry);
                 }else{
                     //Configuración por default de botosnes
                     logger.log(ADFLogger.WARNING, "Seteo de visibilidad de  botones por default ");
                     this.setV_render_btn_final(1);
                     this.setV_render_btn_sol(1);
                     this.setV_render_btn_cofi(0);
                     this.setV_render_btn_resp(0);
                 }
             }
         }else{
                //Configuración por default de botosnes
                logger.log(ADFLogger.WARNING, "Seteo de visibilidad de  botones por default ");
                this.setV_render_btn_final(1);
                this.setV_render_btn_sol(1);
                this.setV_render_btn_cofi(0);
                this.setV_render_btn_resp(0);
            }
        logger.log(ADFLogger.WARNING, "Fuera de setConfigurtacionVisibilidad");
    }
    
    public void asignacionVisibilidadPorProducto(Map.Entry <String, TablaDinamicaVOBean> entry){
        int retorno = 0;
        Integer render = 0;
        String accionBtn = null;
        logger.log(ADFLogger.WARNING, "Dentro asignacionVisibilidadPorProducto");
        logger.log(ADFLogger.WARNING, "Comienza configuración de botones U2012");
        
        // ¿La tarrea Actual tiene un retorno?
        retorno = this.getEsRetorno() == Boolean.TRUE ? 1 : 0 ;
        logger.warning("KEY = " + entry.getKey() + ", VALUE = " + entry.getValue().getAccion() );
        //Valor proveniente de la base de datos: Nombre de Btn.
        accionBtn = entry.getValue().getAccion();
        try{        
                TablaDinamicaVOBean objHasMap = entry.getValue();
                Integer [][] visibilidad = objHasMap.getTablaVerdad();
                imprimirTablaVerdad(visibilidad, entry.getValue().getAccion().toString());
            
                //render = visibilidad[this.getIdProducto()][retorno];
                //Siempre objHasMap.getbanStatus() == this.getEstaConfiguradoProducto() == 1
                render = visibilidad[objHasMap.getbanStatus()][retorno];
                //render = visibilidad[objHasMap.getIdProducto()][retorno];-
                
                logger.log(ADFLogger.WARNING, "[" + objHasMap.getbanStatus()+ "],[" + retorno  + "] = " + "Render = " + render );
                //logger.log(ADFLogger.WARNING, "[" + objHasMap.getIdProducto()+ "],[" + retorno  + "] = " + "Render = " + render );
                
                logger.log(ADFLogger.WARNING, "¿Es retorno? : " + retorno  + " ¿Se debe renderizar? :" + render);
                
                switch (accionBtn) {
                // Renderiza conforme a al configuración de la base de datos.
                    case EBC_ENVIAR_COFI:
                        this.setV_render_btn_cofi(render);
                        logger.log(ADFLogger.WARNING,"SW: " + EBC_ENVIAR_COFI + " render: " + render);
                    break;
                    case EBC_ENVIAR_RESPONSABLE:
                        this.setV_render_btn_resp(render);
                    logger.log(ADFLogger.WARNING,"SW: " + EBC_ENVIAR_RESPONSABLE +" render: " + render);
                    break;
                    case EBC_FINALIZAR:
                        this.setV_render_btn_final(render);
                    logger.log(ADFLogger.WARNING,"SW: " + EBC_FINALIZAR +" render: " + render);
                    break;
                    case EBC_SOLICITAR_AJUSTE_POR_FUENTES_EXTERNAS:
                        this.setV_render_btn_sol(render);
                    logger.log(ADFLogger.WARNING,"SW: " + EBC_SOLICITAR_AJUSTE_POR_FUENTES_EXTERNAS +" render: " + render);
                    break;
                default:
                    this.setV_render_btn_cofi(0);
                    this.setV_render_btn_resp(0);
                    this.setV_render_btn_final(1);
                    this.setV_render_btn_sol(1);
                    logger.log(ADFLogger.WARNING,"SWD: " + EBC_ENVIAR_COFI + " render: ");
                    logger.log(ADFLogger.WARNING,"SWD: " + EBC_ENVIAR_RESPONSABLE + " render: ");
                    logger.log(ADFLogger.WARNING,"SWD: " + EBC_FINALIZAR + " render: ");
                    logger.log(ADFLogger.WARNING,"SWD: " + EBC_SOLICITAR_AJUSTE_POR_FUENTES_EXTERNAS + " render: ");
                    logger.log(ADFLogger.WARNING, "Seteo por Default Switch");
                }// End Switch
            
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en ejecutar el seteo de la visibilidad de botones en : asignacionVisibilidadPorProducto");
            logger.log(ADFLogger.ERROR, "Mapa: ");
            logger.warning("KEY = " + entry.getKey() + ", VALUE = " + entry.getValue().getAccion() );
            logger.log(ADFLogger.ERROR, "Error : " + e);
        }
    
    logger.log(ADFLogger.WARNING, "Termino asignacionVisibilidadPorProducto");
    }
    
    public void imprimirTablaVerdad(Integer[][] t, String btn) {

        System.out.println("=========== " + btn + " ================================");
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                System.out.println("[" + x + "]" + "[" + y + "]" + " = " + t[x][y]);
            }
        }

    }
    
    public void imprimirMapaCapaWeb(Map<String, TablaDinamicaVOBean> map){

        for (Map.Entry<String, TablaDinamicaVOBean> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
            TablaDinamicaVOBean o = entry.getValue();
        }


    }

    public String getIdTarea() {
        return this.idTarea;
    }
    
    public String getIdOperacion() {
        return this.idOperacion;
    }
    
    public String getInstanciaProceso() {
        return instanciaProceso;
    }
    
    public oracle.jbo.domain.Number getOperacionId() {
        return operacionId;
    }
    
    public void setCuentaConRecExternos(Integer cuentaConRecExternos) {
        this.cuentaConRecExternos = cuentaConRecExternos;
    }

    public Integer getCuentaConRecExternos() {
        return cuentaConRecExternos;
    } 
    
    public void setEsFechaVigenciaIgualFechaFirma(Boolean esFechaVigenciaIgualFechaFirma) {
        this.esFechaVigenciaIgualFechaFirma = esFechaVigenciaIgualFechaFirma;
    }

    public Boolean getEsFechaVigenciaIgualFechaFirma() {
        return esFechaVigenciaIgualFechaFirma;
    }

    public String getNumeroCustodia() {
        return numeroCustodia;
    }

    public void setNumeroCustodia(String numeroCustodia) {
        this.numeroCustodia = numeroCustodia;
    }
    
    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    public Long getIdContrato() {
        return idContrato;
    }
    
    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    
    public void setEsObtenidoEnFecha(Boolean esObtenidoEnFecha) {
        this.esObtenidoEnFecha = esObtenidoEnFecha;
    }

    public Boolean getEsObtenidoEnFecha() {
        return esObtenidoEnFecha;
    }
    
    public void setSolicitudContratacion(Boolean solicitudContratacion) {
        this.solicitudContratacion = solicitudContratacion;
    }

    public Boolean getSolicitudContratacion() {
        return solicitudContratacion;
    }

    public Long getIdLote() {
        return idLote;
    }

    public void setIdLote(Long idLote) {
        this.idLote = idLote;
    }

    public void setNumeroResolucion(String numeroResolucion) {
        this.numeroResolucion = numeroResolucion;
    }

    public String getNumeroResolucion() {
        return numeroResolucion;
    }

    public void setMapaDinamico(Map<String, TablaDinamicaVOBean> mapaDinamico) {
        this.mapaDinamico = mapaDinamico;
    }

    public Map<String, TablaDinamicaVOBean> getMapaDinamico() {
        return mapaDinamico;
    }

    public void setEstaConfiguradoProducto(Integer estaConfiguradoProducto) {
        this.estaConfiguradoProducto = estaConfiguradoProducto;
    }

    public Integer getEstaConfiguradoProducto() {
        return estaConfiguradoProducto;
    }

    public void setEsRetorno(Boolean esRetorno) {
        this.esRetorno = esRetorno;
    }

    public Boolean getEsRetorno() {
        return esRetorno;
    }
    
    public void setV_render_btn_cofi(Integer v_render_btn_cofi) {
        this.v_render_btn_cofi = v_render_btn_cofi;
    }

    public Integer getV_render_btn_cofi() {
        return v_render_btn_cofi;
    }

    public void setV_render_btn_resp(Integer v_render_btn_resp) {
        this.v_render_btn_resp = v_render_btn_resp;
    }

    public Integer getV_render_btn_resp() {
        return v_render_btn_resp;
    }

    public void setV_render_btn_final(Integer v_render_btn_final) {
        this.v_render_btn_final = v_render_btn_final;
    }

    public Integer getV_render_btn_final() {
        return v_render_btn_final;
    }

    public void setV_render_btn_sol(Integer v_render_btn_sol) {
        this.v_render_btn_sol = v_render_btn_sol;
    }

    public Integer getV_render_btn_sol() {
        return v_render_btn_sol;
    }
    
    public void setAccionBoton(String accionBoton) {
        this.accionBoton = accionBoton;
    }

    public String getAccionBoton() {
        return accionBoton;
    }
}