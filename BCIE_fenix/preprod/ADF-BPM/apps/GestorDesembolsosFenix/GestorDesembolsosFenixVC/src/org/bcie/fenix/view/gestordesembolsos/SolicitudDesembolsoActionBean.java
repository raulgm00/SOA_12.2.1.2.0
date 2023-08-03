package org.bcie.fenix.view.gestordesembolsos;

import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.RowKeySet;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.events.ProcessContextualEventInterface;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;
import org.bcie.fenix.modelo.CondicionDesembolso;

public class SolicitudDesembolsoActionBean extends FenixActionBean implements Serializable,ProcessContextualEventInterface {
    @SuppressWarnings("compatibility:-5315494974631840203")
    private static final long serialVersionUID = 1L;
    public static final String BUNDLE = "view.GestorDesembolsosFenixVCBundle";

    public static ADFLogger logger = null;
    private BigDecimal montoSolicitado = null;
    
    public static final String COD_EXT_MONEDA_USD = "USD";
    public static final Integer ID_TIPO_MONEDA_USD = 1;
    public static final String MSG_01 = "Es necesario capturar los datos obligatorios.";
    public static final String MSG_02 =  "El monto de la solicitud no debe exceder el monto disponible a desembolsar de la operación.";
    public static final String MSG_03 = "Se creará un desembolso para la línea";
    public static final String MSG_06 = "No es posible crear el contrato de desembolso para la línea. Esta se encuentra vencida.";
    public static final String MSG_08 = "Contrato de desembolso creado satisfactoriamente ID: ";
    public static final String MSG_10 = "No es posible crear el contrato de desembolso para la línea ya que no cuenta con disponibilidad.";
    public static final String MSG_12 = "Se perderán los datos sin guardar. ¿Desea continuar?";
    public static final String MSG_Error_Binding ="Error, No se a podido ejecutar la transaccion crear contrato OperationBinding devuelve errores.";
    public static final String MSG_Error_Creacion_Contrato = "Error, No se ha podido crear el contrato. Verifique el tipo de moneda asociada a la operación.";
    public static final String MSG_Error_Creacion_Commit = "Error, No se ha podido crear el contrato.";

    public Long idContratoSeleccionado = null;
    public Long idContratoDesemboldoSeleccionado = null;
   
    private RichPopup popUpNotificacionCreacionContrato;
    private RichPopup popUpNotificacionContratoDesembolsoCreado;
    private RichPanelGroupLayout accionesTreSolicitudDesembolso;
    private RichPanelGroupLayout contenedorDetalleLinea;
    private RichPanelGroupLayout pglRegionContratoUIC;
    private RichTreeTable treeTable;
    private RichPopup popUpCambiarDeContrato;
    private Integer rowKey = null;
    private RichRegion regionContratosUIC;
    private RichPanelGroupLayout contenedorTree;
    private RichPopup popupIniciarCumplimientoCondiciones;
    private RichPopup popupIniciarValidacionAsignaccon;
    private RichPopup popUpDesestimarSolicitudDesembolso;
    private RichPopup popUpDesestimarDesembolso;   
        
    private String operacionSolicitud = (null == JSFUtils.resolveExpression("#{pageFlowScope.operacionSolicitud}")) ? null 
                                      : JSFUtils.resolveExpression("#{pageFlowScope.operacionSolicitud}").toString();
    private RichPanelGroupLayout contenedorDatosEncabezadoSolicitud;
    private RichPanelGroupLayout contenedorDatosLinea;
    private RichPopup popUpDetalleLinea;
    private RichOutputText initValidcionesSolicitud;
    //Inicialicion de lista de advertencia
    //jenamorado 05/08/2021
    private List<String> listaAdvertencias = new ArrayList<String>(); 
    
     //Set de lista de advertencia
     //jenamorado 05/08/2021
     public void setListaAdvertencias(List<String> listaAdvertencias) {
         this.listaAdvertencias = listaAdvertencias;
     }
     //Get de lista de advertencia
     //jenamorado 05/08/2021
     public List<String> getListaAdvertencias() {
         return listaAdvertencias;
     }
    private Boolean primerDesembolsoEnviado=Boolean.FALSE;

    public SolicitudDesembolsoActionBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(SolicitudDesembolsoActionBean.class);
        }
    }

    public void seleccionarLinea(SelectionEvent selectionEvent) {
        logger.warning("Inicia metodo seleccionarLinea");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        Long idSolicitud = null;
        // Actualizamos row seleccionado en tabla
        JSFUtils.resolveMethodExpression("#{bindings.LineaCreditoDesembolsoVO.collectionModel.makeCurrent}",
                                         Object.class, new Class[] { SelectionEvent.class }, new Object[] {
                                         selectionEvent });
        SolicitudDesembolsosBean solicitudBean =
            (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        
        RichTreeTable treeTable = this.getTreeTable();
        RowKeySet rks = treeTable.getSelectedRowKeys();
        Iterator keys = rks.iterator();
        solicitudBean.setRequiereValidacionAsignacion(Boolean.FALSE);
        while (keys.hasNext()) {
            List key = (List) keys.next();
            treeTable.setRowKey(key);
            rowKey = (Integer) treeTable.getActiveRowKey();
            logger.log(ADFLogger.WARNING, "IdRowKey: " + rowKey + " rks: " + rks.toString());
            JUCtrlHierNodeBinding node = (JUCtrlHierNodeBinding) treeTable.getRowData();
            Row rw = node.getRow();
            Long idSeleccionado = (Long) rw.getAttribute("Id");
            
            Long idLineaSel = null;
            String numLinea = null;
            String descripcion = null;
            
            try{
                logger.warning("validando row seleccionada... ");
               descripcion = (String)rw.getAttribute("Descripcion");                
            }catch(Exception e){                   
                 logger.warning("Row data sin descripcion row definido a linea");
             }
   
            if(descripcion == null){
                //Se a seleccionado el nodo de linea
                Long idLineaSeleccionada = idSeleccionado;
                numLinea = (null == rw.getAttribute("NumeroLineaCredito")) ? null : (String) rw.getAttribute("NumeroLineaCredito");
                //consultaLineaCredito(idLineaSeleccionada);
                consultaLineaCreditoByIdLinea(idLineaSeleccionada);
                solicitudBean.setIdLineaCreditoSeleccionada(idLineaSeleccionada);
                solicitudBean.setNumeroLineaCredito(numLinea);
                solicitudBean.setIdContratoSeleccionado(null);
                logger.warning("se ha seleccionado una Linea id: " + idLineaSeleccionada);  
                logger.warning("numero de la Linea: " + numLinea);  
                solicitudBean.setDesabilitarBtnDesestimarDesembolso(Boolean.TRUE); 
                solicitudBean.setDesHabilitarBtnDetalleLinea(Boolean.FALSE);
            }else{
                 Long idContratoSeleccionado = idSeleccionado;
                 Row rowLina = node.getParent().getRow();
                
                if(rowLina.getAttribute("Id") != null){
                     idLineaSel = (Long) rowLina.getAttribute("Id");
                     solicitudBean.setIdLineaCreditoSeleccionada(idLineaSel);
                     consultaLineaCreditoByIdLinea(idLineaSel);
                     
                    numLinea = (null == rowLina.getAttribute("NumeroLineaCredito")) ? null
                             :(String)rowLina.getAttribute("NumeroLineaCredito");
                    
                    solicitudBean.setNumeroLineaCredito(numLinea);
                     
                }else{
                    logger.warning("Error, no se pudo recuperar el IdLineaCredito");
                    JSFUtils.addFacesErrorMessage("Error no se pudo recuperar el id de la linea de credito");
                }
                     
                  String estadoContrato = (null == rw.getAttribute("Descripcion")) ? ""
                                        : (String)rw.getAttribute("Descripcion");
                idContratoDesemboldoSeleccionado        = idContratoSeleccionado;            
                 logger.warning("se ha seleccionado un Contrato id: " + idContratoSeleccionado);
                 logger.warning("se ha seleccionado un Contrato id x: " + idContratoDesemboldoSeleccionado);
                 logger.warning("estado del contrato seleccionado: " + estadoContrato);
                 logger.warning("idLinea de Credito: " + idLineaSel);
                
                 //se creo la variable de session por que tambien se requiere el parametro del TK de contrato de desembolso
                 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("estadoContratoSeleccionado", estadoContrato);                  
                 solicitudBean.setIdContrato(idContratoSeleccionado);
                 solicitudBean.setDesabilitarBtnDesestimarDesembolso(Boolean.FALSE);
                 solicitudBean.setIdContratoSeleccionado(idContratoSeleccionado);                
                 habilitarBtnDesestimarDesembolso();
                 solicitudBean.setDesHabilitarBtnDetalleLinea(Boolean.TRUE);
                
                TFin = System.currentTimeMillis();
                tiempo = (TFin - TInicio)/ 1000;
                logger.warning("Antes de llamar al metodo requiereValidacionAsignacionSolicitudn. idSolicitud:"+ solicitudBean.getIdSolicitud()+" - idOperacion:"+solicitudBean.getIdOperacion()+" Tiempo:"+tiempo);
                try{
                    
                    final Map<String, Object> params = new HashMap<String, Object>();
                    params.put("idSolicitud", solicitudBean.getIdSolicitud());
                    params.put("idOperacion", solicitudBean.getIdOperacion());
                    Boolean resultado = execute(params, "requiereValidacionAsignacionSolicitud");
                    
                    if(resultado!=null){
                        logger.warning("Resulado metodo requiereValidacionAsignacionSolicitudn. idSolicitud:"+ solicitudBean.getIdSolicitud()+" - idOperacion:"+solicitudBean.getIdOperacion()+" Res:"+resultado);
                        solicitudBean.setRequiereValidacionAsignacion(resultado);
                    }
                    
                }catch(Exception e){
                    logger.log(ADFLogger.ERROR, "Error en requiereValidacionAsignacionSolicitud.", e);
                    JSFUtils.addFacesErrorMessage("Error al Consultar si require validación de asignación. Por favor intente más tarde.");
                }
                
                TFin = System.currentTimeMillis();
                tiempo = (TFin - TInicio)/ 1000;
                logger.warning("luego de llamar al metodo requiereValidacionAsignacionSolicitudn. idSolicitud:"+ solicitudBean.getIdSolicitud()+" - idOperacion:"+solicitudBean.getIdOperacion()+" Tiempo:"+tiempo);
                
                final BindingContainer bindings = ADFUtils.getBindingContainer();
                final OperationBinding operationBinding = bindings.getOperationBinding("condicionDesembolsoPorValidar"); 
                operationBinding.getParamsMap().put("pnID_Contrato_Desembolso", idContratoSeleccionado);
                operationBinding.execute(); 
                
                final DCBindingContainer bindingsVO = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
                final DCIteratorBinding dcIterator = bindingsVO.findIteratorBinding("CondicionDesembolsoPorValidarVOIterator");
                final RowSetIterator it = dcIterator.getRowSetIterator();
                final Integer cant=it.getRowCount();
                it.closeRowSetIterator();
                TFin = System.currentTimeMillis();
                tiempo = (TFin - TInicio)/ 1000;
                logger.warning("Cantidad de condiciones: "+cant+" - "+solicitudBean.getIdOperacion()+" - Tiempo:"+TFin);
            }   
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(accionesTreSolicitudDesembolso);
        }
        
        //recuperar el id de solicitud del desembolso
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.idSolicitud}")){
            try{
                idSolicitud = new Long(JSFUtils.resolveExpression("#{pageFlowScope.idSolicitud}").toString());
            }catch(Exception e){
                logger.warning("ERROR al recuperar el valor de la solicitud del TF.", e);
            }
            logger.warning("idSolicitud : "+idSolicitud);
            //obtener el monto total solicitado
            obtenerMontosSolicitud(idSolicitud);
        }else{
            logger.severe("No se recupero idSolicitud");
        }
        
        
        obtenerRow(); // se obtienen los datos de la linea
       /* logger.warning("Refrescando region de contrato.");
          AdfFacesContext.getCurrentInstance().addPartialTarget(getRegionContratosUIC());
        
            logger.warning("Refrescando pantalla...");
            FacesContext fctx = FacesContext.getCurrentInstance();
            String page = fctx.getViewRoot().getViewId();
            ViewHandler ViewH = fctx.getApplication().getViewHandler();
            UIViewRoot UIV = ViewH.createView(fctx, page);
            UIV.setViewId(page);
            fctx.setViewRoot(UIV);
        */
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/ 1000; //Calculamos los milisegundos de diferencia
        logger.warning("termina el metodo seleccionarLinea con una duracion de: "+tiempo+" segundos");
    }


    public void habilitarBtnDesestimarDesembolso(){
        logger.warning("*Inf, inicia metodo habilitarBtnDesestimarDesembolso");
         String estadoContratoSeleccionado = "";
         SolicitudDesembolsosBean solicitudBean =
            (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");            
        
         try{                 
            estadoContratoSeleccionado =  
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("estadoContratoSeleccionado").toString();  
         }catch(Exception e){
             logger.warning("ha ocurrido un error al recuperar el estado del contrato selecciondo");
         }
         
        logger.warning("*Inf, se recibio un estado del contrato seleccionado: " + estadoContratoSeleccionado);
        
        if(estadoContratoSeleccionado.equals("")){  
            solicitudBean.setDesabilitarBtnDesestimarDesembolso(Boolean.TRUE);
            logger.warning("btn de desestimar contrato disable: "+solicitudBean.getDesabilitarBtnDesestimarDesembolso());            
        }else if(estadoContratoSeleccionado.equals("Creado")){
            solicitudBean.setDesabilitarBtnDesestimarDesembolso(Boolean.FALSE);
            solicitudBean.setBtnDesestimarDesembolsoVisible(Boolean.TRUE);
            logger.warning("habilitando btn destimar contrato");            
        }else if(estadoContratoSeleccionado.equals("Cumplimiento de condiciones")){
            solicitudBean.setDesabilitarBtnDesestimarDesembolso(Boolean.FALSE);
            solicitudBean.setBtnDesestimarDesembolsoVisible(Boolean.TRUE);
            logger.warning("habilitando btn destimar contrato");            
        }else if(estadoContratoSeleccionado.equals("Preparado")){
            solicitudBean.setDesabilitarBtnDesestimarDesembolso(Boolean.FALSE);
            solicitudBean.setBtnDesestimarDesembolsoVisible(Boolean.TRUE);
            logger.warning("habilitando btn destimar contrato");            
        }else{             
            solicitudBean.setDesabilitarBtnDesestimarDesembolso(Boolean.TRUE);
            logger.warning("deshabilitando btn destimar contrato");            
        }
          
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getAccionesTreSolicitudDesembolso());      
        logger.warning("*Inf, termina metodo habilitarBtnDesestimarDesembolso");
    }

    public void pasarContratoDesembolso() {

        // Actualizamos row seleccionado en tabla
        /*         JSFUtils.resolveMethodExpression("#{bindings.LineaCreditoDesembolsosVO11.collectionModel.makeCurrent}",
                                         Object.class, new Class[] { SelectionEvent.class },
                                         new Object[] { selectionEvent });  */

        RichTreeTable treeTable = this.getTreeTable();
        RowKeySet rks = treeTable.getSelectedRowKeys();
        Iterator keys = rks.iterator();
        while (keys.hasNext()) {
            List key = (List) keys.next();
            treeTable.setRowKey(key);
            JUCtrlHierNodeBinding node = (JUCtrlHierNodeBinding) treeTable.getRowData();
            Row rw = node.getRow();
            //idContratoSeleccionado = (Long) rw.getAttribute("Id");
            logger.log(ADFLogger.WARNING, "*-* idContrato Seleccionado: " + idContratoSeleccionado);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getPglRegionContratoUIC());
        }
        obtenerRow();

    }

    public void consultaLineaCredito(Long idLinea) {
        SolicitudDesembolsosBean solicitudBean =
            (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        try {
            BindingContainer bindings = getBindings();
            logger.log(ADFLogger.WARNING,
                       " Evento de cambio de linea en el AccionBean  " + idLinea + " tMoneda " +
                       solicitudBean.getTipoMoneda());
            OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCredito");
            operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLinea);
            operationBindingLineaCredito.getParamsMap().put("tipoMoneda", solicitudBean.getTipoMoneda());
            operationBindingLineaCredito.execute();
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                       "Error al llamar al servicio ConsultarLineaCredito" + e.getClass() + " : " + e.getMessage());
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorDetalleLinea());
    }
    
    public void consultaLineaCreditoByIdLinea(Long idLinea) {
        SolicitudDesembolsosBean solicitudBean =
            (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        
        BigDecimal montoDisponible = null;
        BigDecimal montoTransito = null;
        BigDecimal montoDisponibleDesembolso = null;
        BigDecimal montoProgramado = null;
        //recuperan fechas
        java.sql.Timestamp fecharegistro = null;
        java.sql.Timestamp fechaVencimiento = null;
        java.sql.Timestamp fechaMaximaDesembolso = null;
        
        if(idLinea == null){
            JSFUtils.addFacesErrorMessage("Error, no se ha podido recuperar el id de la linea de credito");
            return;
        }
        
        
        try{
            Map<String,Object> resultMap = new HashMap<String,Object>();
            BindingContainer bindings = getBindings(); 
            logger.log(ADFLogger.WARNING,
                       " Evento de cambio de linea en el AccionBean  " + idLinea + " tMoneda " +
                       solicitudBean.getTipoMoneda());
            OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");
            operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLinea);
            if(null!=solicitudBean.getTipoMoneda()){
                    operationBindingLineaCredito.getParamsMap().put("tipoMoneda", solicitudBean.getTipoMoneda());
            }
            operationBindingLineaCredito.execute();
            resultMap = (Map) operationBindingLineaCredito.getResult();
            //Recuperan montos
            BigDecimal montoTotal1=BigDecimal.ZERO;
            montoTotal1= resultMap.get("idMontoTotal") != null ? (BigDecimal)resultMap.get("idMontoTotal") : BigDecimal.ZERO;
            if(montoTotal1.compareTo(BigDecimal.ZERO)!=0){
                    //montoLinea=montoTotal1.doubleValue();
                solicitudBean.setMontoLinea(montoTotal1.doubleValue());
            }
            montoDisponible = resultMap.get("DISPONIBLE") != null ? (BigDecimal)resultMap.get("DISPONIBLE") : null;
            montoTransito = resultMap.get("TRANSITO") != null ? (BigDecimal)resultMap.get("TRANSITO") : null;
            montoDisponibleDesembolso = resultMap.get("DISPONIBLE_DESEMBOLSO") != null ? (BigDecimal)resultMap.get("DISPONIBLE_DESEMBOLSO") : null;
            montoProgramado = resultMap.get("PROGRAMADO") != null ? (BigDecimal)resultMap.get("PROGRAMADO") : null;
            //recuperan fechas
            fecharegistro = resultMap.get("FECHAREGISTRO") != null ? (java.sql.Timestamp)resultMap.get("FECHAREGISTRO") : null;
            fechaVencimiento = resultMap.get("FECHAVENCIMIENTO") != null ? (java.sql.Timestamp)resultMap.get("FECHAVENCIMIENTO") : null;
            fechaMaximaDesembolso = resultMap.get("FECHAMAXIMADESEMBOLSO") != null ? (java.sql.Timestamp)resultMap.get("FECHAMAXIMADESEMBOLSO") : null;
            
            logger.warning("Asignando valores de Linea a variables de bean de Pagina.");
            solicitudBean.setMontoDisponible(montoDisponible);
            solicitudBean.setMontoTransito(montoTransito);
            solicitudBean.setMontoDisponibleDesembolso(montoDisponibleDesembolso);
            solicitudBean.setMontoProgramado(montoProgramado);
            solicitudBean.setFecharegistro(fecharegistro);
            solicitudBean.setFechaVencimiento(fechaVencimiento);
            solicitudBean.setFechaMaximaDesembolso(fechaMaximaDesembolso);
            
            // Se asignan valores para popup Detalle de Linea.
            solicitudBean.setNumeroLineaCreditoRespOut((resultMap.get("numeroLineaCreditoRespOut") == null) ? null :
                                                       resultMap.get("numeroLineaCreditoRespOut").toString());

            solicitudBean.setNumeroContrato((resultMap.get("numeroContrato") == null) ? null :
                                            resultMap.get("numeroContrato").toString());

            solicitudBean.setDescripcionLineaString((resultMap.get("descripcionLineaString") == null) ? null :
                                                    resultMap.get("descripcionLineaString").toString());

            solicitudBean.setMoneda((resultMap.get("moneda") == null) ? null : resultMap.get("moneda").toString());

            solicitudBean.setMontoAprobadoString((resultMap.get("montoAprobadoString") == null) ? null :
                                                 resultMap.get("montoAprobadoString").toString());

            solicitudBean.setCodigoCliente((resultMap.get("codigoCliente") == null) ? null :
                                           resultMap.get("codigoCliente").toString());

            solicitudBean.setRevolvencia((resultMap.get("revolvencia") == null) ? null :
                                         resultMap.get("revolvencia").toString());

            solicitudBean.setFondo((resultMap.get("fondo") == null) ? null : resultMap.get("fondo").toString());

            solicitudBean.setLineaFinanciera((resultMap.get("lineaFinanciera") == null) ? null :
                                             resultMap.get("lineaFinanciera").toString());

            solicitudBean.setDestino((resultMap.get("destino") == null) ? null : resultMap.get("destino").toString());

            solicitudBean.setTipoRecurso((resultMap.get("tipoRecurso") == null) ? null :
                                         resultMap.get("tipoRecurso").toString());

            solicitudBean.setPais((resultMap.get("pais") == null) ? null : resultMap.get("pais").toString());

            solicitudBean.setActividadEconomicaString((resultMap.get("actividadEconomicaString") == null) ? null :
                                                      resultMap.get("actividadEconomicaString").toString());

            solicitudBean.setSectorInstitucional((resultMap.get("sectorInstitucional") == null) ? null :
                                                 resultMap.get("sectorInstitucional").toString());

            solicitudBean.setEjecutivo((resultMap.get("ejecutivo") == null) ? null :
                                       resultMap.get("ejecutivo").toString());

            solicitudBean.setEjeEstrategicoString((resultMap.get("ejeEstrategicoString") == null) ? null :
                                                  resultMap.get("ejeEstrategicoString").toString());

            solicitudBean.setAreaFocalizacionString((resultMap.get("areaFocalizacionString") == null) ? null :
                                                    resultMap.get("areaFocalizacionString").toString());

            solicitudBean.setObjeticoEstrategico((resultMap.get("objeticoEstrategico") == null) ? null :
                                                 resultMap.get("objeticoEstrategico").toString());

            solicitudBean.setPlantillaLimite((resultMap.get("plantillaLimite") == null) ? null :
                                             resultMap.get("plantillaLimite").toString());

            solicitudBean.setSerialLimite((resultMap.get("serialLimite") == null) ? null :
                                          resultMap.get("serialLimite").toString());

            solicitudBean.setSaldo((resultMap.get("saldo") == null) ? null : resultMap.get("saldo").toString());

            solicitudBean.setDisponibilidad((resultMap.get("disponibilidad") == null) ? null :
                                            resultMap.get("disponibilidad").toString());

            solicitudBean.setDisponibilidadIfacil((resultMap.get("disponibilidadIfacil") == null) ? null :
                                                  resultMap.get("disponibilidadIfacil").toString());

            solicitudBean.setTieneCondEspeciales((resultMap.get("tieneCondEspeciales") == null) ? null :
                                                 resultMap.get("tieneCondEspeciales").toString());

            solicitudBean.setDescCondEspeciales((resultMap.get("descCondEspeciales") == null) ? null :
                                                resultMap.get("descCondEspeciales").toString());

            solicitudBean.setFechaMaxDesembolsar((resultMap.get("fechaMaxDesembolsar") == null) ? null :
                                                 resultMap.get("fechaMaxDesembolsar").toString());

            solicitudBean.setNumeroDesembolsos((resultMap.get("numeroDesembolsos") == null) ? null :
                                               resultMap.get("numeroDesembolsos").toString());
            
            solicitudBean.setEsVisibleDatosLineaCredito(Boolean.TRUE);
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito", e);                
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorDetalleLinea());
    }

    public void obtenerRow() {
        logger.warning("*Inicia el metodo seleccion linea contrato desembolso*");
        SolicitudDesembolsosBean solicitudBean =
            (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        VerCrearSolicitudDesembolsosBean desembolsosBean =
            (VerCrearSolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.VerCrearSolicitudDesembolsosBean}");
       
        Long idLineaCredito = solicitudBean.getIdLineaCreditoSeleccionada();
        
        if (null != idLineaCredito) {
                
            solicitudBean.setIdLineaCredito(idLineaCredito);            
            desembolsosBean.setIdLineaCredito(idLineaCredito);

            // Refrescar los datos de verLineaCreditoVO para el tree
            try {
                logger.warning("Refrescando los datos de verLineaCreditoVO para tree idLinea:" + idLineaCredito);
                BindingContainer binding = getBindings();
                OperationBinding operBindingLinea = binding.getOperationBinding("setlineaComoCurrent");
                operBindingLinea.getParamsMap().put("idLineaCredito", idLineaCredito);
                operBindingLinea.execute();
                if (!operBindingLinea.getErrors().isEmpty()) {
                    logger.warning("OperationBinding devuelve errores");
                }
            } catch (Exception e) {
                logger.warning("OperationBinding getLineaSeleccionada regresa erorres :( ");
                JSFUtils.addFacesErrorMessage(MSG_Error_Binding);
                logger.warning("ERROR al ejecutar operationBinding filtraLineaById", e);
            }
        } else {
            logger.warning("*** Error, El metodo getRowSeleccionada esta debolviendo un row nulo :( ");
            JSFUtils.addFacesErrorMessage("Error, No se puede recuperar el Id de la linea de credito ");
        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorDatosLinea());
         
        logger.warning("**** Se ha seleccionado la fila con un ID ->" + idLineaCredito);
    }

    public void validarParaCrearContratoDesembolso(ActionEvent actionEvent) {
        logger.warning("**** Inicia el metodo validarParaCrearContratoDesembolso ");
        obtenerRow();
                
         Boolean validarTermino406 = validarTermino406();
         Boolean validacion0_Ex01 = validarCamposObligatorios();
         Boolean validacion1_Ex02 = validarMontoSolicitudMenorAMontoOperacion();
         Boolean validacion2_EX05 = validarVigenciaLinea();
         Boolean validacion3_EX07 = validarDisponibilidad();
     
         Boolean crearContrato = validarTermino406 && validacion0_Ex01 && validacion1_Ex02 && validacion2_EX05 && validacion3_EX07;                        
        
        if (crearContrato) {
            logger.warning("Abriendo popUp de confirmacion");
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopUpNotificacionCreacionContrato().show(popupHints);
        }
    }
    
    
    
    public Boolean validarTermino406(){            
        logger.warning("*Inf, inicia metodo validarTermino406");
        Boolean respuesta = Boolean.FALSE;
        Map datos = new HashMap();
        
        BindingContainer binding = getBindings();
        OperationBinding operBinding = null;                
        
        try{
            
            Long idOperacion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}").toString());            
            operBinding =  binding.getOperationBinding("validarTermino406");
            operBinding.getParamsMap().put("idOperacion", idOperacion);                
            operBinding.execute();
            
        }catch(Exception e){
          JSFUtils.addFacesErrorMessage("Error al validar termino 406 ->"+e.getMessage());
        }
        
        if (!operBinding.getErrors().isEmpty()) {
           logger.warning("*Error, OperationBinding crearContratoDesembolso devuelve errores ->"+operBinding.getErrors());
           JSFUtils.addFacesErrorMessage("Error: "+operBinding.getErrors());
        }else{
        
        if(operBinding.getResult() !=null){
                datos = (Map)operBinding.getResult();
                respuesta = (Boolean)datos.get("respuesta");
                Long clietneEnMora = (Long)datos.get("clietneEnMora");
               
                if(!respuesta){
                    JSFUtils.addFacesErrorMessage("El cliente "+clietneEnMora+" se encuentra en Mora y forma parte del Grupo Solidaroio: ");   
                }                        
        }
        
         logger.warning("*Inf, termina el metodo validarTermino406" +respuesta );
        } 
       return respuesta;
    }

    public Long getLineaCredito(Long idOperacion, Long idSolicitud) {
        logger.log(ADFLogger.WARNING, "-- Inside getLineaCredito --");
        logger.log(ADFLogger.WARNING, "-- idOperacion: " + idOperacion);
        logger.log(ADFLogger.WARNING, "-- idSOlicitud: " + idSolicitud);
        Long idLinea = null;
        try {
            Long pageFlowIdOperacion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}").toString());
            Long pageFlowIdSolicitud = new Long(JSFUtils.resolveExpression("#{pageFlowScope.idSolicitud}").toString());
            logger.log(ADFLogger.WARNING, "-- pageFlowIdOperacion: " + pageFlowIdOperacion);
            logger.log(ADFLogger.WARNING, "-- pageFlowIdSolicitud: " + pageFlowIdSolicitud);

            BindingContainer binding = getBindings();
            OperationBinding operBinding = binding.getOperationBinding("filtraLineaByIdOperacion");
            operBinding.getParamsMap().put("idOperacion", idOperacion);
            idLinea = (Long) operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding1 devuelve errores ContratoDesembolsoVOImpl");
                JSFUtils.addFacesErrorMessage(MSG_Error_Binding);
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "ERROR en getLineaCredito - " + e.getMessage());
        }

        return idLinea;
    }

    public void crearContratoDesembolso(ActionEvent actionEvent) {
        SolicitudDesembolsosBean solicitudBean = (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        logger.warning("*Inf, Inicia el metodo crearContratoDesembolso - "+solicitudBean.getIdOperacion());
        getPopUpNotificacionCreacionContrato().hide();

        
        
        String loginUsuario = null;
        Long idOperacion = null;
        Integer tarea = null;
        Long idSolicitud = null;
        Long idLineaCredito = null;
        Long idContratoDesembolso = null;
        
        try{
            loginUsuario = ADFContext.getCurrent().getSecurityContext().getUserName();
            idOperacion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}").toString());       
            tarea= new Integer(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}").toString());        
            idSolicitud = new Long(JSFUtils.resolveExpression("#{pageFlowScope.idSolicitud}").toString());        
            idLineaCredito = solicitudBean.getIdLineaCredito();
        }catch(Exception e){
                logger.warning("*Error, ha ocurrido un error al intenter recuperar parametros del Tsk : "+e);
            }
         
                
            BindingContainer binding = getBindings();
            OperationBinding operBinding = null;
      
          try{      
                operBinding =  binding.getOperationBinding("crearContratoDesembolso");
                operBinding.getParamsMap().put("idOperacion", idOperacion);
                operBinding.getParamsMap().put("idSolicitud", idSolicitud);        
                operBinding.getParamsMap().put("idLineaCredito", idLineaCredito);
                operBinding.getParamsMap().put("loginUsuario", loginUsuario);
                operBinding.getParamsMap().put("tarea", tarea);                    
                operBinding.execute();
            }catch(Exception e){
              JSFUtils.addFacesErrorMessage("Error al crear el contrato de desembolso : "+e.getMessage());
             }
          
        if (!operBinding.getErrors().isEmpty()) {
            logger.warning("***Error, OperationBinding crearContratoDesembolso devuelve errores - "+solicitudBean.getIdOperacion());
            JSFUtils.addFacesErrorMessage("Error al crear el contrato de desembolso : "+operBinding.getErrors());
        } else {
            
            if(operBinding.getResult() !=null){
                  idContratoDesembolso = (Long)operBinding.getResult();
                }
      
             logger.warning("*Inf, ID del contato de desembolso recuperado->" + idContratoDesembolso+" - "+solicitudBean.getIdOperacion());

                if (idContratoDesembolso != null){
                    solicitudBean.setIdContratoDesembolso(idContratoDesembolso);
                    solicitudBean.setIdContratoSeleccionado(idContratoDesembolso);
    
                        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
                        getPopUpNotificacionContratoDesembolsoCreado().show(popupHints);
    
                        solicitudBean.setDesabilitarBtnDesestimarDesembolso(Boolean.FALSE);
                        solicitudBean.setDesabilitarBtnIniciarCumplimientoCon(Boolean.FALSE);     
                        
                        try{
                            
                            final Map<String, Object> params = new HashMap<String, Object>();
                            params.put("idSolicitud", solicitudBean.getIdSolicitud());
                            params.put("idOperacion", solicitudBean.getIdOperacion());
                            Boolean resultado = execute(params, "requiereValidacionAsignacionSolicitud");
                            
                            if(resultado!=null){
                                logger.warning("Resulado metodo requiereValidacionAsignacionSolicitu 2. idSolicitud:"+ solicitudBean.getIdSolicitud()+" - idOperacion:"+solicitudBean.getIdOperacion()+" Res:"+resultado);
                                solicitudBean.setRequiereValidacionAsignacion(resultado);
                            }
                            
                        }catch(Exception e){
                            logger.log(ADFLogger.ERROR, "Error en requiereValidacionAsignacionSolicitud. 2", e);
                            JSFUtils.addFacesErrorMessage("Error al Consultar si require validación de asignación. Por favor intente más tarde.");
                        }
                            
                        solicitudBean.inicializarTree(idOperacion, idSolicitud);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getAccionesTreSolicitudDesembolso());
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getRegionContratosUIC());
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorTree());                                      
                    
                    RichTreeTable treeTable = this.getTreeTable();
                    RowKeySet rks = treeTable.getSelectedRowKeys();
                    rowKey = (Integer) treeTable.getActiveRowKey();
                    logger.log(ADFLogger.WARNING, "IdRowKey: " + rowKey + " rks: " + rks.toString());
                    
                    
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("estadoContratoSeleccionado", "Creado");
                    habilitarBtnDesestimarDesembolso();
                }else{
                    JSFUtils.addFacesErrorMessage("Error no se a podido crear el contrato");                
                }
        }
       
        logger.warning("**** Termina el metodo crearContratoDesembolso - "+solicitudBean.getIdOperacion());
    }


    public Date getDate(Date fecha) {
        logger.warning("Inicia metodo de getCurrentDate valor recibido...-> " + fecha);
        Date fechaFormateada = null;

        if (fecha == null)
            fecha = new Date();

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String FechaActual = (dateFormat.format(fecha));
            fechaFormateada = dateFormat.parse(FechaActual);

        } catch (Exception e) {
            logger.warning("Error al parsear el objeto Date ", e);
        }
        logger.warning("Termina metodo de getCurrentDate");
        return fechaFormateada;
    }


    // este metodo se descarto debido a un cambio en el Caso de uso en donde ya no se requiere esta validacion

    public Boolean validarFechasSolicitudmayorFechaActual() {
        logger.warning("Inicia metodo de validarFechasSolicitudmayorFechaActual");
        Boolean respuesta = Boolean.FALSE;

        Date fechaSolicitudRecuperada =
            (null == ADFUtils.getBoundAttributeValue("FechaSolicitud")) ? null :
            (Date) ADFUtils.getBoundAttributeValue("FechaSolicitud");

        if (null == fechaSolicitudRecuperada) {
            JSFUtils.addFacesErrorMessage("Error, Se esta recibiendo un valor nulo en fecha solicitud ");
            return false;
        }

        Date fechaActual = getDate(null);
        Date fechaSolicitud = getDate(fechaSolicitudRecuperada);

        logger.warning("fechaActual    -> " + fechaActual);
        logger.warning("fechaSolicitud -> " + fechaSolicitud);

        if (null == fechaActual) {
            JSFUtils.addFacesErrorMessage("Error, no se ha podido calcular la fecha actual");
            return false;
        }

        int res = fechaActual.compareTo(fechaSolicitud);
        logger.warning("Valor del CompareTo: " + res);

        if (res == 1) {
            JSFUtils.addFacesErrorMessage("Error, La fecha de la solicitud no puede ser menor a la fecha actual");
            return null;
        } else {
            respuesta = Boolean.TRUE;
        }

        logger.warning("Termina metodo de validarFechasSolicitudmayorFechaActual");
        return respuesta;
    }


    public Boolean validarVigenciaLinea() {
        logger.warning("**** Inicia el metodo validarVigenciaLinea ");
            
        Boolean respuesta = Boolean.FALSE;
        String fechaVigencia = null;
        String fechaVencimiento = null;
        String fechaMax = null;
        SolicitudDesembolsosBean solicitudBean =
            (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        
        /* String fechaVencimiento = (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null 
                                : ADFUtils.getBoundAttributeValue("FechaVencimiento").toString();
              
                String fechaMax = (null == ADFUtils.getBoundAttributeValue("FechaMaximaDesembolso")) ? null 
                                : ADFUtils.getBoundAttributeValue("FechaMaximaDesembolso").toString(); */
        try{
            fechaVencimiento = solicitudBean.getFechaVencimiento().toString();
        }catch(Exception e){
            logger.warning("ERROR al recuperar la Fecha de vencimiento del BEAN.", e);
        }
        
        try{
            fechaMax = solicitudBean.getFechaMaximaDesembolso().toString();
        }catch(Exception e){
            logger.warning("ERROR al recuperar la Fecha de vencimiento del BEAN.", e);
        }
       
        logger.warning("** Valores recuperados  fechaVencimiento->" + fechaVencimiento + 
                                                                " fecha fechaMaxima->" + fechaMax);

        if (fechaVencimiento == null && fechaMax == null) {
            //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_VALIDACIONES_CREAR_CONTRATO"));
            logger.warning("La Fecha de vencimiento y fecha maxima son NULL. Se validará volviendo a consultar el servicio de linea de credito.");
            respuesta = validarFechaVencimiento();  
            return respuesta;
        }

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String FechaActual = (dateFormat.format(new Date()));

            if (fechaVencimiento != null) {
                fechaVigencia = fechaVencimiento;
            } else {
                fechaVigencia = fechaMax;
            }
            Date FechaActualF = dateFormat.parse(FechaActual);
            Date fechaVigenciaF = dateFormat.parse(fechaVigencia);

            int res = FechaActualF.compareTo(fechaVigenciaF);

            logger.warning("**** Valor de la comparacion de fechas  fecha vigencia ->" + fechaVigencia +
                                                     " fecha actual->" + FechaActual + "valor de res: " + res);

                if (res == 1) {
                    logger.warning("**La liena no tiene vigencia :( ");
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("GD_CREAR_CONTRATO_MSG_06"));                       
                    return false;
                } else {
                    logger.warning("** ok la linea cuanta con vigencia ");
                    respuesta = Boolean.TRUE;
                }
        } catch (Exception ex) {
            logger.warning("ERROR al validar la fecha de figencia.");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_VALIDACIONES_CREAR_CONTRATO"));
        }
        logger.warning("**** Termina el metodo validarVigenciaLinea ");
        return respuesta;
    }

    public Boolean validarDisponibilidad() {
        logger.warning("** Inicia el metodo validarDisponibilidad ");
        Boolean respuesta = Boolean.FALSE;
        BigDecimal montoMinimo = new BigDecimal("1");
        BigDecimal montoDisponible = null;
        SolicitudDesembolsosBean solicitudBean =
            (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");

        /* BigDecimal montoDisponible = (null == ADFUtils.getBoundAttributeValue("DISPONIBLE")) ? null : 
                                     (BigDecimal) ADFUtils.getBoundAttributeValue("DISPONIBLE"); */
        
        try{
            montoDisponible = solicitudBean.getMontoDisponible();
        }catch(Exception e){
            logger.warning("ERROR al recuperar el Monto disponible del BEAN.", e);
        }
        
        logger.warning("** valor recuperado montoDisponible->" + montoDisponible);

        if (montoDisponible == null) {
            logger.warning("**se esta recibiendo un null en binding con nombre .:DISPONIBLE:. Se validará de acuerdo a servicio de LineaCredito.");
            consultarLineaCreditoPorServicio();
            
            montoDisponible = solicitudBean.getMontoDisponibleService();
            if (montoDisponible == null) {
                logger.warning("MontoDisponible es null.");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_VALIDACIONES_CREAR_CONTRATO"));
                return false;
            } else {
                if (montoDisponible.compareTo(montoMinimo) == 1) {
                    respuesta = Boolean.TRUE;
                } else {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("GD_CREAR_CONTRATO_MSG_10"));
                }
            }
        } else {
            if (montoDisponible.compareTo(montoMinimo) == 1) {
                respuesta = Boolean.TRUE;
            } else {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("GD_CREAR_CONTRATO_MSG_10"));
            }
        }
        logger.warning("** Termina el metodo validarDisponibilidad ");
        return respuesta;
    }
    
    public Boolean validarFechaVencimiento(){
        logger.warning("Inicia metodo validarFechaVencimiento.");
        Boolean resultado = Boolean.FALSE;
        Date fechaVencimiento = null;
        Date fechaMaximaDesembolso = null;
        Date fechaVigencia = null;
        Date fechaActual = new Date();
        SolicitudDesembolsosBean solicitudBean =
            (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        
        consultarLineaCreditoPorServicio();
        
        fechaVencimiento = solicitudBean.getFechaVencimientoService();
        fechaMaximaDesembolso = solicitudBean.getFechaMaximaService();
        
        if(null == fechaVencimiento && null == fechaMaximaDesembolso){
            logger.warning("Fechas para validar la linea de credito son NULL.");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_VALIDACIONES_CREAR_CONTRATO"));
        }else{
            if (fechaVencimiento != null) {
                logger.warning("Tomando la fecha de Vencimiento para la validacion.");
                fechaVigencia = fechaVencimiento;
            } else if(null != fechaMaximaDesembolso){
                logger.warning("Tomando la fecha maxima para la validacion.");
                fechaVigencia = fechaMaximaDesembolso;
            }else{
                logger.warning("La fecha de vigencia no se pudo obtener.");
            }
        }
        
        if(null != fechaVigencia){
            if(fechaActual.compareTo(fechaVigencia)==1){
                logger.warning("**La liena no tiene vigencia :( ");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("GD_CREAR_CONTRATO_MSG_06"));
            } else {
                logger.warning("** ok la linea cuanta con vigencia ");
                resultado = Boolean.TRUE;
            }
        }else{
            logger.warning("La fecha de vigencia es NULL. No se pudo validar la fecha correctamente.");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_VALIDACIONES_CREAR_CONTRATO"));
        }
        
        logger.warning("Termina metodo validarFechaVencimiento.");
        return resultado;
    }
    
    public void consultarLineaCreditoPorServicio() {
        logger.warning("Inicia metodo consultarLineaCreditoPorServicio.");
        Map mapaDatosLinea = new HashMap();
        BindingContainer binding = getBindings();
        OperationBinding operBinding = null;
        SolicitudDesembolsosBean solicitudBean =
            (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        Long idLineaCredito = solicitudBean.getIdLineaCredito();
        Date fechaVencimiento = null;
        Date fechaMaximaDesembolso = null;
        BigDecimal montoDisponible = null;

        try {
            operBinding = binding.getOperationBinding("obtenerFechaVencimientoMaximaMontoDisponible");
            operBinding.getParamsMap().put("idLineaCredito", idLineaCredito);
            operBinding.getParamsMap().put("tipoMoneda", null);
            mapaDatosLinea = (Map) operBinding.execute();
        } catch (Exception e) {
            JSFUtils.addFacesErrorMessage("Error al crear el contrato de desembolso : " + e.getMessage());
        }
        
        if(operBinding.getErrors().isEmpty()){
            if(null != mapaDatosLinea && !mapaDatosLinea.isEmpty()){
                try{
                    fechaVencimiento = (Date) mapaDatosLinea.get("FechaVencimiento");
                    fechaMaximaDesembolso = (Date) mapaDatosLinea.get("FechaMaxima");
                    montoDisponible = (BigDecimal) mapaDatosLinea.get("MontoDisponible");
                    
                    logger.warning("FECHA DE VENCIMIENTO: " + fechaVencimiento);
                    logger.warning("FECHA MAXIMA: " + fechaMaximaDesembolso);
                    logger.warning("MONTO DISPONIBLE: " + montoDisponible);
                }catch(Exception e){
                    logger.warning("ERROR al recuperar los datos para validar Fecha de vencimiento y Monto disponible.", e);
                }
            }else{
                logger.warning("El mapa con los datos para validar La fecha de venicmiento y el monto disponible es NULL o VACIO.");
            }
        }else{
            logger.warning("ERROR al ejecutar el OperationBinding obtenerFechaVencimientoMaximaMontoDisponible." + 
                           operBinding.getErrors().toString());
        }
        
        logger.warning("Seteando fechas y monto a variables de ManagedBean.");
        solicitudBean.setFechaMaximaService(fechaMaximaDesembolso);
        solicitudBean.setFechaVencimientoService(fechaVencimiento);
        solicitudBean.setMontoDisponibleService(montoDisponible);

        logger.warning("Termina metodo consultarLineaCreditoPorServicio.");
    }

    public Boolean validarCamposObligatorios() {                
       
        String fechaSolicitud = null;           
        
        if(null != ADFUtils.getBoundAttributeValue("FechaSolicitud")){      
                fechaSolicitud = ADFUtils.getBoundAttributeValue("FechaSolicitud").toString();
        }else{
            logger.severe("La fecha de solicitud obtenida es nula");
        }

        Boolean resultado = Boolean.TRUE;

            if(fechaSolicitud == null || fechaSolicitud.equals("")){                      
               resultado = Boolean.FALSE;                  
                JSFUtils.addFacesErrorMessage(getStringFromBundle("DATOS_REQ_ERROR_MSG"));
              }                         
        return resultado;
    }

    public Boolean validarMontoSolicitudMenorAMontoOperacion() {
        logger.warning("Inf, Inicia el metodo validarmontoSolicitudMenorAMontoOperacion ");
        Boolean respuesta = Boolean.FALSE;
        BigDecimal montoDesembolsarOperacion = null;
        Integer idTipoMonedaSolicitud = null;
        BigDecimal montoTotalSolicitudUSD = null;
        Double montoDesembolsarSession = null;  
        try{     
            montoSolicitado = (BigDecimal)ADFUtils.getBoundAttributeValue("montoTotalSolicitud");                                                          
        }catch(Exception e){
            logger.warning("ERROR al recuperar valor de montoTotalSolicitud.", e);
        }

        logger.warning("Inf, Monto Solicitud ->" + montoSolicitado);

        if(validarRegistrosContratos()){
            if (montoSolicitado != null) {
                try {
                    try{
                        montoDesembolsarSession = (Double)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("montoDesembolsarSession");
                        logger.warning("Valor del monto a desembolsar en sesion : " + montoDesembolsarSession);
                    }catch(Exception e){
                        logger.severe("Error al obtener la instancia de datosSesionManagedBean.", e);
                    }
//                    BindingContainer binding = ADFUtils.getBindingContainer();
//                    OperationBinding operBinding = binding.getOperationBinding("getMontoDisponibleDesembolsar");
//                    montoDesembolsarOperacion = (BigDecimal) operBinding.execute();
//                    if (!operBinding.getErrors().isEmpty()) {
//                        logger.warning("  OperationBinding getMontoDisponibleDesembolsar devuelve errores");                      
//                    }
                    montoDesembolsarOperacion = validarMontoADesembolsar();
                    if(montoDesembolsarOperacion == null){
                        montoDesembolsarOperacion = BigDecimal.valueOf(montoDesembolsarSession); 
                    }
                } catch (Exception e) {
                    logger.log(ADFLogger.ERROR,
                               "*** Error, en consultarContratoDesembolso " + e.getClass() + ":" + e.getMessage());
                }
                if (montoDesembolsarOperacion == null) {                 
                    logger.warning("*** Error, montoDesembolsar es NULL ");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MONTO_DESEMBOLSAR_NULO"));
                    return false;
                } else {
        
                    idTipoMonedaSolicitud = recuperarIdMonedaSolicitud();
                    
                    if(null != idTipoMonedaSolicitud){
                        montoTotalSolicitudUSD = 
                            convertirMonedas(montoSolicitado, idTipoMonedaSolicitud, COD_EXT_MONEDA_USD);
                        logger.warning("monto total solicitud en USD : "+montoTotalSolicitudUSD);
                        logger.warning("monto desembolso operacion : "+montoDesembolsarOperacion);
                        if(null != montoTotalSolicitudUSD){
                            int res = montoTotalSolicitudUSD.compareTo(montoDesembolsarOperacion);
                            logger.warning("**** montoDesembolsar ->" + montoDesembolsarOperacion + " valor de res->" + res);
                            if (res == 1) {
                                //El monto de la solicitud no debe exceder el monto disponible a desembolsar de la operación.
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("GD_CREAR_CONTRATO_MSG_02"));
                                return false;
                            } else {
                                respuesta = Boolean.TRUE;
                            }
                        }else{
                            logger.warning("El montoTotalSolicitudUSD es NULL.");
                            JSFUtils.addFacesErrorMessage("Monto total de la solcitud en USD es NULL. " +
                                "No se puede validar contra el Monto disponible a desembolsar de la operacion.");
                        }
                    }else{
                        logger.warning("IdTipoMoneda es NULL. No se puede hacer la validacion de montos.");
                        JSFUtils.addFacesErrorMessage("No se pudo recuperar el tipo de moneda de la solicitud. " +
                                "No se puede validar el Monto total de la solicitud contra el Monto disponible a desembolsar de la operacion.");
                    }
                }

            } else {
                logger.warning("Error, montoDesembolsar es resuelto a NULL ");              
            }
        }else{
            logger.warning("No existen registros de contratos para la solicitud. Se permitira crear contratos.");
            respuesta = Boolean.TRUE;
        }
        
        logger.warning("Inf, Termina el metodo validarmontoSolicitudMenorAMontoOperacion , return : "+respuesta);
        return respuesta;
    }
    
    public BigDecimal validarMontoADesembolsar(){
        logger.warning("Entra en validarMontoADesembolsar.");
        BigDecimal montoDisponible = null;
        Long idOperacion = null;
        try{
            SolicitudDesembolsosBean solicitudBean =
                (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
            if(solicitudBean.getIdOperacion() != null){
                idOperacion = solicitudBean.getIdOperacion();
                logger.warning("Valor de la operacion : " + idOperacion);
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("obtenerMontoDisponibleOperacion");
                operationBinding.getParamsMap().put("idOperacion", idOperacion);
                montoDisponible = (BigDecimal) operationBinding.execute();
                
                if (!operationBinding.getErrors().isEmpty()) {
                    logger.warning("  OperationBinding getMontoDisponibleDesembolsar devuelve errores");                      
                }
                
            }else{
                logger.warning("El id de la operacion es nula.");
            }
        }catch(Exception e){
            logger.warning("Error en validarMontoADesembolsar.", e);
        }
        logger.warning("Existe el monto : " + montoDisponible);
        return montoDisponible;
    }
    
    public BigDecimal convertirMonedas(BigDecimal montoAConvertir, Integer idTipoMoneda, String codigoMonedaConvertir){
        logger.warning("Inicia metodo convertirMonedas.");
        BigDecimal montoConvertido = null;
        Map<String,Object> params = new HashMap<String,Object>();
        
        params.put("claveTipo", idTipoMoneda);
        params.put("codigoA", codigoMonedaConvertir);
        params.put("monto", montoAConvertir);
        try{
            montoConvertido = execute(params, "convertirMonedas");
            logger.warning("Monto convertido: " + montoConvertido);
        }catch(Exception e){
            logger.warning("ERROR al convertir el monto a otro tipo de moneda.", e);
        }
        
        logger.warning("Termina metodo convertirMonedas.");
        return montoConvertido;
    }
    
    public Integer recuperarIdMonedaSolicitud(){
        logger.warning("Inicia metodo recuperarIdMonedaSolicitud.");
        Integer idTipoMoneda = null;
        Long idSolicitud = null;
        Map<String, Object> params = new HashMap<String, Object>();
        SolicitudDesembolsosBean solicitudBean =
            (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        
        idSolicitud = solicitudBean.getIdSolicitud();
        
        params.put("idSolicitud", idSolicitud);
        try{
            idTipoMoneda = execute(params, "recuperarIdMonedaSolicitud");
        }catch(Exception e){
            logger.warning("ERROR al ejecutar el operBinding recuperarIdMonedaSolicitud.", e);
        }
        
        logger.warning("Termina metodo recuperarIdMonedaSolicitud.");
        return idTipoMoneda;
    }

    public void cerrarPopUpNotificacionContratoDesembolsoCreado(ActionEvent actionEvent) {
        getPopUpNotificacionContratoDesembolsoCreado().hide();
    }


        public void showPopUpDesestimarSolicitud(ActionEvent actionEvent) {
           logger.warning("Inf, Inicia metodo showPopUpDesestimarSolicitud"); 
              Boolean pasoValidacion =  validarEstadoContratosBySolicitud();
            
              if(pasoValidacion){
                  logger.warning("Inf, ok paso validacion mostrando popUp de confirmacion de accion"); 
                      RichPopup.PopupHints popupHints = new RichPopup.PopupHints();        
                      getPopUpDesestimarSolicitudDesembolso().show(popupHints); 
              }else{
                  logger.warning("Inf, No se podra desestimar la solicitud, no paso validacion"); 
                  JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_DESESTIMAR_SOLICITUD"));
              }
            
           logger.warning("Inf, Termina metodo showPopUpDesestimarSolicitud"); 
        }
        
        public void closePopUpDesestimarSolicitudDesembolso(ActionEvent actionEvent) {
            popUpDesestimarSolicitudDesembolso.hide();
        }
        
        public Boolean validarEstadoContratosBySolicitud(){
           logger.warning("Inf, inicia metodo validarEstadoContratosBySolicitud");
              Boolean respuesta  = Boolean.FALSE;
             SolicitudDesembolsosBean solicitudBean =
                                      (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");            
                    Long idSolicitud = solicitudBean.getIdSolicitud();
                
                  BindingContainer bindings = getBindings();
                try {
                    OperationBinding operationBinding = bindings.getOperationBinding("validarEstadoContratosBySolicitud");
                                     operationBinding.getParamsMap().put("idSolicitud", idSolicitud );
                    respuesta = (Boolean) operationBinding.execute();
                } catch (Exception e) {
                    logger.log(ADFLogger.ERROR,
                               "Error al ejecutar " + e.getClass() + ":" + e.getMessage());                    
                }
            
            logger.warning("Inf, inicia metodo validarEstadoContratosBySolicitud");
            return respuesta;
            }
        
        public Integer recperarEstadoContratoDesembolso(Long IdContrato){
          logger.warning("Inicia el metodo recperarEstadoContratoDesembolso :"+IdContrato);
            Row contrato = null;
            Integer idTcaEstado = null;
            
            if(IdContrato == null){                
               logger.warning("El parametro IdContrato es requerido");
               return idTcaEstado;
            }
           
            BindingContainer bindings = getBindings();
            
            try {  
                
                OperationBinding operationBinding =  bindings.getOperationBinding("obtenerContratoPorId");
                operationBinding.getParamsMap().put("idContrato", IdContrato);               
                operationBinding.execute();
                                 
                if (!operationBinding.getErrors().isEmpty()) {
                    logger.warning("OperationBinding obtenerContratoPorId devuelve errores ->"
                                                       +operationBinding.getErrors().toString());
                }else{
                     contrato = (Row)operationBinding.getResult(); 
                    
                        idTcaEstado = (null == contrato.getAttribute("IdTcaEstado"))? null
                                    : (Integer)contrato.getAttribute("IdTcaEstado");
                    
                     logger.warning("*Inf, estado recuperado del contrato de desembolso: "+idTcaEstado);                
                }
                
            } catch (Exception e) {
                logger.warning("OperationBinding obtenerContratoPorId ->", e);                
                logger.warning("***Error, al recuperar el estado del contrato de desembolso");
            } 
                                      
          logger.warning("Termina el metodo recperarEstadoContratoDesembolso");
          return idTcaEstado;
        }
        
        
        public Boolean validarRegistrosContratos(){
            logger.warning("Inicia metodo validarRegistrosContratos");
            Boolean resultado = Boolean.FALSE;
            Long registrosContratos = null;
            Map<String, Object> params = new HashMap<String, Object>();
            SolicitudDesembolsosBean solicitudBean =
                (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
            
            params.put("idSolicitud", solicitudBean.getIdSolicitud());
            try{
                resultado = execute(params, "validarContratosSolicitud");
            }catch(Exception e){
                logger.warning("Error al ejecutar el metodo para palidar contratos de la solicitud.", e);
            }
            
            logger.warning("Termina metodo validarRegistrosContratos: " + resultado);
            return resultado;
        }


    public Boolean validarDocumentosGestor(Long idOperacion, Integer idTareaBpm, String descripcionCorta) {
          logger.warning("Inicia metodo validarDocumentosGestor");
          
          BindingContainer bindings = getBindings();
          OperationBinding operationBinding = bindings.getOperationBinding("validarDocumentosGestor");

          operationBinding.getParamsMap().put("idTarea", idTareaBpm);
          operationBinding.getParamsMap().put("idOperacion", idOperacion.intValue());
          operationBinding.getParamsMap().put("descripcionCorta", descripcionCorta);
        
          Boolean result = false;
          List<String> resultado = (List<String>) operationBinding.execute();
          logger.warning("Mensajes devueltos: " + resultado.size());
          if(resultado.isEmpty())
          {
              result = true;
          }
          else
          {
              result = false;
              for(String mensaje : resultado)
              {
                  logger.warning("Mensaje: " + mensaje);
                  JSFUtils.addFacesErrorMessage(mensaje);   
              }
          }
                    
          if (!operationBinding.getErrors().isEmpty())
          {   
              return false;
          }else{
              if(operationBinding.getResult() != null &&
                 resultado == null){
                  resultado = (List<String>) operationBinding.execute();
                  logger.warning("Mensajes devueltos: " + resultado.size());
                  
                  if(resultado.isEmpty())
                  {
                      result = true;
                  }
                  else
                  {
                      result = false;
                      for(String mensaje : resultado)
                      {
                          logger.warning("Mensaje: " + mensaje);
                          JSFUtils.addFacesErrorMessage(mensaje);   
                      }
                  }
              }
          }
          
          logger.warning("Finaliza metodo validarDocumentosTarea");

          return result;
      }
        
        public Boolean validarFuentesSinIdLineaPasiva(){
            logger.warning("Inicia metodo validarFuentesSinIdLineaPasiva.");
            Boolean esValido = Boolean.FALSE;
            Long idLineaCredito = null;
            Map<String, Object> params = new HashMap<String, Object>();
            SolicitudDesembolsosBean solicitudBean =
                (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
            idLineaCredito = solicitudBean.getIdLineaCredito();
            
            logger.warning("IdLineaCredito: " + idLineaCredito);
            params.put("idLineaCredito", idLineaCredito);
            try{
                esValido = execute(params, "validarFuentesSinIdLineaPasiva");
            }catch(Exception e){
                logger.warning("ERROR al ejecutar el operBinding validarFuentesSinIdLineaPasiva.", e);
            }
            
            logger.warning("Termina metodo validarFuentesSinIdLineaPasiva.");
            return esValido;
        }

        public void showPopUpDesestimarDesembolso(ActionEvent actionEvent) {
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();        
            getPopUpDesestimarDesembolso().show(popupHints); 
        }
        
        public void closePopUpDesestimarDesembolso(ActionEvent actionEvent) {
            popUpDesestimarDesembolso.hide();
        }   
        
        
      public Boolean validarDesestimarContrato(Long idContrato){
         logger.warning("Inf, inicia metodo validarDesestimarContrato");
            Boolean respuesta = Boolean.FALSE;
              BindingContainer bindings = getBindings();
              Integer idTcaEstadoContrato = null;
              
              SolicitudDesembolsosBean solicitudBean =
                  (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
              
              try{
                  OperationBinding operationBinding =  bindings.getOperationBinding("recuperarIdTcaEstadoContrato");
                  operationBinding.getParamsMap().put("idContrato", idContrato);
                   idTcaEstadoContrato = (Integer)operationBinding.execute();
                     
                  if(idTcaEstadoContrato.compareTo(FenixConstants.ESTADO_CONTRATO_DESEMBOLSO_CREADO) == 0 ){
                          solicitudBean.setDesabilitarBtnDesestimarDesembolso(Boolean.FALSE);                         
                         logger.warning("Inf. ok se permite desestimar el desembolso");
                  }else{
                          solicitudBean.setDesabilitarBtnDesestimarDesembolso(Boolean.TRUE);
                          logger.warning("Inf. No permitira desestimar el desembolso idTcaEstadoContrato : "+idTcaEstadoContrato);
                      }
                  
              }catch(Exception e){
                  logger.log(ADFLogger.ERROR, "Error en Obtener el Desestimar Contrato" +
                                                                             e.getClass() +  ":" + e.getMessage());               
              }     
              logger.warning("Inf, termina validarDesestimarContrato");
           return respuesta;
          }
        
      public void desestimarContrato(ActionEvent actionEvent){
          logger.warning("Inf, inicia metodo desestimarContrato");
          SolicitudDesembolsosBean solicitudBean =
              (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
          
             Long idContrato = solicitudBean.getIdContrato();
          
             Boolean seDesestimoContrato = Boolean.FALSE;
             Boolean seRealizoCommit= Boolean.FALSE;
             BindingContainer bindings = getBindings();
            
            try{
                OperationBinding operationBinding =  bindings.getOperationBinding("cambiarContratoABanstatusCero");
                operationBinding.getParamsMap().put("idContrato", idContrato);
                seDesestimoContrato = (Boolean) operationBinding.execute();
                
                 
                if(seDesestimoContrato){
                      OperationBinding operationBinding2 =  bindings.getOperationBinding("comitDatosContrato");                
                      seRealizoCommit = (Boolean) operationBinding2.execute();                 
                }else{
                        logger.warning("***Error, No se pudo cambiar el BanStatus del contrato,  : "+idContrato);
                    }
                
                  if(seDesestimoContrato && seRealizoCommit){
                     logger.warning("Inf, ok se desestimo el contrato idContrato : "+idContrato+" refrescando tree..");
                      solicitudBean.refrescarTree();                    
                      // dehabilitamos el boton de desestimar solicitud                                        
                      solicitudBean.setDesabilitarBtnDesestimarDesembolso(Boolean.TRUE);
                      AdfFacesContext.getCurrentInstance().addPartialTarget(accionesTreSolicitudDesembolso);
                      // refrescamos la region de datos del contrato sin un id seleccionado
                      solicitudBean.setIdContratoSeleccionado(null);
                      AdfFacesContext.getCurrentInstance().addPartialTarget(getTreeTable());
                      AdfFacesContext.getCurrentInstance().addPartialTarget(getPglRegionContratoUIC());                                        
                 
                  }else{
                          logger.warning("*** Error, No se pudo desestimar el contrato,  : "+idContrato);
                      }
                
            }catch(Exception e){
                logger.warning("***Error al Desestimar Contrato" + e.getClass() + ":" + e.getMessage());              
            } 
            
          popUpDesestimarDesembolso.hide();
          logger.warning("Inf, termina metodo desestimarContrato");
      }
        
     
        /**
         * Obtiene cadena valor del Resource Bundle
         * @param psKey contiene clave de bundle
         * @return devuelve cadena
         */
        private String getStringFromBundle(String psKey) {
            ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
            String txt = null;
            if (null != resourceBundle) {
                txt = resourceBundle.getString(psKey);
            }
            return txt;
        }
        
   /**********---------------- Accesors  -------------------------***************/
   
    public void setMontoSolicitado(BigDecimal montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setPopUpNotificacionCreacionContrato(RichPopup popUpNotificacionCreacionContrato) {
        this.popUpNotificacionCreacionContrato = popUpNotificacionCreacionContrato;
    }

    public RichPopup getPopUpNotificacionCreacionContrato() {
        return popUpNotificacionCreacionContrato;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void cerrarPopUpNotificacionCreacionContrato(ActionEvent actionEvent) {
        getPopUpNotificacionCreacionContrato().hide();
    }

    public void setPopUpNotificacionContratoDesembolsoCreado(RichPopup popUpNotificacionContratoDesembolsoCreado) {
        this.popUpNotificacionContratoDesembolsoCreado = popUpNotificacionContratoDesembolsoCreado;
    }

    public RichPopup getPopUpNotificacionContratoDesembolsoCreado() {
        return popUpNotificacionContratoDesembolsoCreado;
    }


    public void setAccionesTreSolicitudDesembolso(RichPanelGroupLayout accionesTreSolicitudDesembolso) {
        this.accionesTreSolicitudDesembolso = accionesTreSolicitudDesembolso;
    }

    public RichPanelGroupLayout getAccionesTreSolicitudDesembolso() {
        return accionesTreSolicitudDesembolso;
    }

    public void setContenedorDetalleLinea(RichPanelGroupLayout contenedorDetalleLinea) {
        this.contenedorDetalleLinea = contenedorDetalleLinea;
    }

    public RichPanelGroupLayout getContenedorDetalleLinea() {
        return contenedorDetalleLinea;
    }

    public void setPglRegionContratoUIC(RichPanelGroupLayout pglRegionContratoUIC) {
        this.pglRegionContratoUIC = pglRegionContratoUIC;
    }

    public RichPanelGroupLayout getPglRegionContratoUIC() {
        logger.warning("Into getRegionContratosUIC refrescando tree solicitud ..."); 
        SolicitudDesembolsosBean solicitudBean = null;
        try{
            solicitudBean =
               (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        }catch(Exception e){
            logger.warning("Ha ocurrido un error al instanciar la clase de solicitud ->",e);
        }                
        
        if(solicitudBean != null){
            solicitudBean.refrescarTree();    
        }else{
            logger.warning("no se ha podido refrescar el tree de desembolsos");
        }
        
        contenedorTree.processUpdates(FacesContext.getCurrentInstance());        
        AdfFacesContext.getCurrentInstance().addPartialTarget(contenedorTree);
        return pglRegionContratoUIC;
    }

    public void setTreeTable(RichTreeTable treeTable) {
        this.treeTable = treeTable;
    }

    public RichTreeTable getTreeTable() {
        return treeTable;
    }

    public void setIdContratoSeleccionado(Long idContratoSeleccionado) {
        this.idContratoSeleccionado = idContratoSeleccionado;
    }

    public Long getIdContratoSeleccionado() {
        return idContratoSeleccionado;
    }

    public void setPopUpCambiarDeContrato(RichPopup popUpCambiarDeContrato) {
        this.popUpCambiarDeContrato = popUpCambiarDeContrato;
    }

    public RichPopup getPopUpCambiarDeContrato() {
        return popUpCambiarDeContrato;
    }

    public void onSeleccionarContratoDesembolso(ActionEvent actionEvent) {
        pasarContratoDesembolso();
        popUpCambiarDeContrato.hide();
        AdfFacesContext.getCurrentInstance().addPartialTarget(getRegionContratosUIC());
    }

    public void showPopUpCambiarContrato(SelectionEvent selectionEvent) {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpCambiarDeContrato().show(popupHints);
    }   

    public void setRowKey(Integer rowKey) {
        this.rowKey = rowKey;
    }

    public Integer getRowKey() {
        return rowKey;
    }

    public void setRegionContratosUIC(RichRegion regionContratosUIC) {
        this.regionContratosUIC = regionContratosUIC;
    }

    public RichRegion getRegionContratosUIC() {
        return regionContratosUIC;
    }

    public void setContenedorTree(RichPanelGroupLayout contenedorTree) {
        this.contenedorTree = contenedorTree;
    }

    public RichPanelGroupLayout getContenedorTree() {        
        return contenedorTree;
    }

    public void setPopupIniciarCumplimientoCondiciones(RichPopup popupIniciarCumplimientoCondiciones) {
        this.popupIniciarCumplimientoCondiciones = popupIniciarCumplimientoCondiciones;
    }

    public RichPopup getPopupIniciarCumplimientoCondiciones() {   
        return popupIniciarCumplimientoCondiciones;
    }

    public void setPopUpDesestimarSolicitudDesembolso(RichPopup popUpDesestimarSolicitudDesembolso) {
        this.popUpDesestimarSolicitudDesembolso = popUpDesestimarSolicitudDesembolso;
    }

    public RichPopup getPopUpDesestimarSolicitudDesembolso() {
        return popUpDesestimarSolicitudDesembolso;
    }
    
      public void setPopUpDesestimarDesembolso(RichPopup popUpDesestimarDesembolso) {
          this.popUpDesestimarDesembolso = popUpDesestimarDesembolso;
      }
    
      public RichPopup getPopUpDesestimarDesembolso() {
          return popUpDesestimarDesembolso;
      }

    public void setContenedorDatosEncabezadoSolicitud(RichPanelGroupLayout contenedorDatosEncabezadoSolicitud) {
        this.contenedorDatosEncabezadoSolicitud = contenedorDatosEncabezadoSolicitud;
    }

    public RichPanelGroupLayout getContenedorDatosEncabezadoSolicitud() {
        return contenedorDatosEncabezadoSolicitud;
    }

    public String onDesestimarSolicitud() {
        logger.warning("Inicia onDesestimarSolicitud.");
        
        BindingContainer bindings = getBindings();
        Boolean seDesestimoSolicitud = Boolean.FALSE;

        SolicitudDesembolsosBean solicitudBean =
            (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        Long idSolicitud = solicitudBean.getIdSolicitud();
        String goAction =  null;
        
        try {
            OperationBinding operationBinding = bindings.getOperationBinding("desestimarSolicitud");
            operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
            seDesestimoSolicitud = (Boolean) operationBinding.execute();

            if (!seDesestimoSolicitud) {
                popUpDesestimarSolicitudDesembolso.hide();
                logger.warning("***Error, No fue posible desestimar la solicitud");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("No se pudo procesar la solicitud intente mas tarde"));
            } else {
                goAction = "irRegresarBusqueda";
                logger.warning("Solicitud " + idSolicitud + "desestimada exitosamente.");
            }

        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error al desestimar solicitud" + e.getClass() + ":" + e.getMessage());
        }
        
        popUpDesestimarSolicitudDesembolso.hide();
        logger.warning("Termina onDesestimarSolicitud");

        return goAction;
    }
    /**
     * Calcular el montoTotalSolicitud y  montoDesembolsadoSolicitud
     */
    public void obtenerMontosSolicitud (Long idSolicitud){
        logger.warning("Dentro de obtenerMontosSolicitud ");                                                                           
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("idSolicitud", idSolicitud);
        try{
            execute(params, "obtenerMontosSolicitud");
        }catch(Exception e){
            logger.severe("Error al ejcutar obtenerMontosSolicitud ",e);
        }
        logger.warning("Fuera de obtenerMontosSolicitud");
    }
    
    public void abrirPopUpDetalleLinea(ActionEvent actionEvent){
      logger.warning("*Inf, abriendo PopUp Detalle de la linea...");
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpDetalleLinea().show(popupHints);
    }
    
    public void cerrarPopUpDetalleLinea(ActionEvent actionEvent){
      logger.warning("*Inf, cerrando PopUp Detalle de la linea...");        
        getPopUpDetalleLinea().hide();
    }

    public void setPopUpDetalleLinea(RichPopup popUpDetalleLinea) {
        this.popUpDetalleLinea = popUpDetalleLinea;
    }

    public RichPopup getPopUpDetalleLinea() {
        return popUpDetalleLinea;
    }
    
    public void setContenedorDatosLinea(RichPanelGroupLayout contenedorDatosLinea) {
        this.contenedorDatosLinea = contenedorDatosLinea;
    }

    public RichPanelGroupLayout getContenedorDatosLinea() {
        return contenedorDatosLinea;
    }

    public void setInitValidcionesSolicitud(RichOutputText initValidcionesSolicitud) {
        this.initValidcionesSolicitud = initValidcionesSolicitud;
    }

    public RichOutputText getInitValidcionesSolicitud() {
        habilitarBtnDesestimarDesembolso();
        return initValidcionesSolicitud;
    }
    //Advertencia de fecha que esta en el futuro
    //jenamorado 05/08/2021
    public void AdvetenciaFechasSolicitudmayorFechaActual() {
            logger.warning("Inicia metodo de AdvetenciaFechasSolicitudmayorFechaActual");
          
            listaAdvertencias = new ArrayList<String>();
            Date fechaSolicitudRecuperada =
                (null == ADFUtils.getBoundAttributeValue("FechaSolicitud")) ? null :
                (Date) ADFUtils.getBoundAttributeValue("FechaSolicitud");

            if (null == fechaSolicitudRecuperada) {
                listaAdvertencias.add("Error, Se esta recibiendo un valor nulo en fecha solicitud ");
               
            }

            Date fechaActual = getDate(null);
            Date fechaSolicitud = getDate(fechaSolicitudRecuperada);

            logger.warning("fechaActual    -> " + fechaActual);
            logger.warning("fechaSolicitud -> " + fechaSolicitud);

            if (null == fechaActual) {
                listaAdvertencias.add("Error, no se ha podido calcular la fecha actual");
               
            }

            int res = fechaActual.compareTo(fechaSolicitud);
            logger.warning("Valor del CompareTo: " + res);

            if (res == -1) {
                listaAdvertencias.add(" La fecha de solicitud se encuentra en el futuro, puede existir un conflicto con la fecha de disponibilidad de fondos.");
                
            } 

            logger.warning("Termina metodo de AdvetenciaFechasSolicitudmayorFechaActual");
           
        }
    public void setPopupIniciarValidacionAsignaccon(RichPopup popupIniciarValidacionAsignaccon) {
        this.popupIniciarValidacionAsignaccon = popupIniciarValidacionAsignaccon;
    }

    public RichPopup getPopupIniciarValidacionAsignaccon() {
        return popupIniciarValidacionAsignaccon;
    }

    @SuppressWarnings("unchecked")
    public void iniciarCumplimientoCondiciones(ActionEvent actionEvent) {
        SolicitudDesembolsosBean solicitudBean = (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        final String idOperacion = solicitudBean.getIdOperacion().toString();
        final Long idContrato =  solicitudBean.getIdContratoSeleccionado();
        logger.warning("Inicia metodo iniciarCumplimientoCondiciones "+idOperacion);
        try{
            logger.warning("Cantidad de condiciones: "+solicitudBean.getListaCondiciones().size()+" - "+solicitudBean.getIdOperacion());
            if(solicitudBean.getListaCondiciones().size()<=0 || solicitudBean.getSoloCondicionesCalendario()){
                logger.warning("Se inicia validacion de asignacion desde iniciar condiciones 2: - "+solicitudBean.getIdOperacion());
                logger.warning("Cambiando el estado del contrato preparado "+idOperacion);
                final BindingContainer bindings = ADFUtils.getBindingContainer();               
                final OperationBinding operationBinding = bindings.getOperationBinding("actualizarEstado");
                operationBinding.getParamsMap().put("idContrato", idContrato.toString());
                operationBinding.getParamsMap().put("estado", FenixModelConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_PREPARADO);
                                
                final Boolean res=(Boolean)operationBinding.execute();
                logger.warning("Resultado del cambio de estado preparado: "+res+" - "+idOperacion);
                if(!res || !operationBinding.getErrors().isEmpty()){
                    logger.warning("Error al cambiar el estado del contrato preparado "+idOperacion);
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                    return;
                }
                        
                solicitudBean.setEsCumplimientoCondicion(Boolean.TRUE);
                solicitudBean.setBtnDesestimarSolicitudDeshabilitar(Boolean.TRUE);
                
            }
            if(solicitudBean.getListaCondiciones().size()>0 && !solicitudBean.getSoloCondicionesCalendario()){
                final List<Long> listaCondicionesIniciar= new ArrayList<Long>();
                final Iterator<CondicionDesembolso> it= solicitudBean.getListaCondiciones().iterator();
                while(it.hasNext()){
                    final CondicionDesembolso item=it.next();
                    if(item.getSeleccionado()!=null && item.getSeleccionado()){
                        listaCondicionesIniciar.add(item.getIdCondicion());
                    }
                }
                logger.warning("Se inica el camino normal de administracion de condiciones: - "+solicitudBean.getIdOperacion());
                final String idSolicitud = solicitudBean.getIdSolicitud().toString();
                String error = null;
                if(listaCondicionesIniciar.size()<=0){
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_INICIO_CUMPLIMIENTO_CONDICIONES"));
                    return;
                }
                
                logger.warning("Llamado condicionDesemboslo: "+idOperacion);
                final Map<String, Object> params = new HashMap<String, Object>();
                params.put("idOperacion", Long.parseLong(idOperacion));
                params.put("idSolicitud", Long.parseLong(idSolicitud));
                params.put("idContrato", idContrato);
                params.put("listaCondiciones",listaCondicionesIniciar);
                final Boolean resultado = execute(params, "iniciarCondicionDesembolso");
                logger.warning("Respuesta condicionDesemboslo "+idOperacion+" - "+resultado);
                if(null != error && !error.equals("")){
                    logger.warning("Error al condicionDesemboslo: " + error+" - "+idOperacion);
                    JSFUtils.addFacesErrorMessage("condicionDesemboslo(): " + error);
                    ADFUtils.setBoundAttributeValue("MensajeErrorServicio", null);
                    return;
                }
                
                
                logger.warning("Cambiando el estado del contrato "+idOperacion);
                final BindingContainer bindings = ADFUtils.getBindingContainer();               
                final OperationBinding operationBinding = bindings.getOperationBinding("actualizarEstado");
                operationBinding.getParamsMap().put("idContrato", idContrato.toString());
                operationBinding.getParamsMap().put("estado", FenixModelConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_CUMPLIMIENTO_CONDICIONES);
                                
                final Boolean res=(Boolean)operationBinding.execute();
                logger.warning("Resultado del cambio de estado: "+res+" - "+idOperacion);
                if(!res || !operationBinding.getErrors().isEmpty()){
                    logger.warning("Error al cambiar el estado del contrato "+idOperacion);
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                    return;
                }
            }
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(getRegionContratosUIC());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getTreeTable());
            AdfFacesContext.getCurrentInstance().addPartialTarget(accionesTreSolicitudDesembolso);
            
            getPopupIniciarCumplimientoCondiciones().hide();
            
            logger.warning("Resfrescar pantalla iniciarCumplimientoCondiciones -"+idOperacion);
            FacesContext fctx = FacesContext.getCurrentInstance();
            String page = fctx.getViewRoot().getViewId();
            ViewHandler ViewH = fctx.getApplication().getViewHandler();
            UIViewRoot UIV = ViewH.createView(fctx, page);
            UIV.setViewId(page);
            fctx.setViewRoot(UIV);
        }catch(Exception e){
            e.printStackTrace();
            JSFUtils.addFacesErrorMessage("iniciarCumplimientoCondiciones: " + e.getMessage());
        }
        logger.warning("Termina metodo iniciarCumplimientoCondiciones");
    }
    public void iniciarValidacionAsignacion(ActionEvent actionEvent) {
        final SolicitudDesembolsosBean solicitudBean = (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        logger.warning("Inicia metodo iniciarValidacionAsignaicon "+solicitudBean.getIdOperacion());
        try{
            Map<String, Object> params = new HashMap<String, Object>();
            String idOperacion = null;
            String idSolicitud = null;
            Long idSolicitudLong = null;
            Boolean resultado = Boolean.FALSE;
            String loginUsuario = ADFContext.getCurrent().getSecurityContext().getUserName();
            String errorVerificarValidacion = null;
            final Long idContrato =  solicitudBean.getIdContratoSeleccionado();
            
            if (null != solicitudBean) {
                idOperacion = solicitudBean.getIdOperacion().toString();
                idSolicitud = solicitudBean.getIdSolicitud().toString();
                
                try{
                    idSolicitudLong = new Long(idSolicitud);
                }catch(Exception e){
                    logger.warning("No se pudo obtener el idSOlicitud Long "+solicitudBean.getIdOperacion());
                }
                
                params.put("idSolicitud", idSolicitud);
                params.put("idOperacion", idOperacion);                
                try {
                    logger.warning("Ejecutando OperationBinding para cambiar el estado de los contratos de la solicitud. "+solicitudBean.getIdOperacion());
                    resultado = execute(params, "obtenerIdContratoSolicitudCambiarEstado");
                    errorVerificarValidacion = (String) ADFUtils.getBoundAttributeValue("MensajeErrorServicio");
                    if(null != errorVerificarValidacion && !errorVerificarValidacion.equals("")){
                        logger.warning("Mensaje de errorValidacion: " + errorVerificarValidacion+" - "+solicitudBean.getIdOperacion());
                        JSFUtils.addFacesErrorMessage("IniciarCumplimientoCondiciones(): " + errorVerificarValidacion);
                        ADFUtils.setBoundAttributeValue("MensajeErrorServicio", null);
                    }
                } catch (Exception e) {
                    logger.warning("ERROR al ejecutar el inicio de validacion de asignacion de recursos.", e);
                    resultado = Boolean.FALSE;
                }
        
                if (resultado) {
                    params.clear();
                    params.put("idOperacion", idOperacion);
                    params.put("loginUsuario", loginUsuario);
                    params.put("idSolicitud", idSolicitud);
                    params.put("idContrato", idContrato);
                    
                    try {
                        logger.warning("Ejecutando OperationBinding para iniciar validacion asignacion de recursos de la solicitud. "+solicitudBean.getIdOperacion());
                        resultado = execute(params, "inicioValidacionAsignacionRecursos");
                    } catch (Exception e) {
                        logger.warning("ERROR al ejecutar el inicio de validacion de asignacion de recursos.", e);
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR inicio de validacion de asignacion de recursos: "+e.getMessage()));
                        return;
                    }
                            
                    if(resultado){
                        logger.warning("Se ha iniciado el proceso de validacion de asignacion de recursos y el estado de los contrados fue actualizado correctamente. "+solicitudBean.getIdOperacion());
                    }else{
                        logger.warning("No se pudo actualizar el estado de los contratos de desembolso de la solicitud. "+solicitudBean.getIdOperacion());
                    }
                        
                    solicitudBean.setEsCumplimientoCondicion(Boolean.TRUE);
                    solicitudBean.setBtnDesestimarSolicitudDeshabilitar(Boolean.TRUE);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getRegionContratosUIC());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getTreeTable());
                } else {
                    logger.warning("No se pudo iniciar el proceso de Validacion de asignacion de recursos. "+solicitudBean.getIdOperacion());
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_INICIO_VALIDACION"));
                }
            }else{
                logger.warning("Instancia de solicitudBean es NULL "+solicitudBean.getIdOperacion());
                 JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_INICIO_VALIDACION"));
              }
            
            
            logger.warning("*Inf, recuperando estado del contrato "+solicitudBean.getIdOperacion());
          
            Integer idTcaEstadoDesembolso = recperarEstadoContratoDesembolso(idContrato);
            
            logger.warning("idTcaEstadoDesembolso: "+idTcaEstadoDesembolso);            
                                    
            if(idTcaEstadoDesembolso == null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("estadoContratoSeleccionado", "");
            }else if(idTcaEstadoDesembolso.compareTo(11) == 0){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("estadoContratoSeleccionado", "Validación de recursos");
            }else{
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("estadoContratoSeleccionado", idTcaEstadoDesembolso.toString());
            }
            
            habilitarBtnDesestimarDesembolso();
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(accionesTreSolicitudDesembolso);
            
            getPopupIniciarValidacionAsignaccon().hide();
            
            logger.warning("Refrescando pantalla. "+solicitudBean.getIdOperacion());
            FacesContext fctx = FacesContext.getCurrentInstance();
            String page = fctx.getViewRoot().getViewId();
            ViewHandler ViewH = fctx.getApplication().getViewHandler();
            UIViewRoot UIV = ViewH.createView(fctx, page);
            UIV.setViewId(page);
            fctx.setViewRoot(UIV);
        }catch(Exception e){
            e.printStackTrace();
            JSFUtils.addFacesErrorMessage("iniciarValidacionAsignaicon: " + e.getMessage());
        }
        logger.warning("Termina metodo iniciarValidacionAsignaicon "+solicitudBean.getIdOperacion());
    }

    @SuppressWarnings("unchecked")
    public void abrirPopupCumplimientoCondiciones(ActionEvent actionEvent) {
        final SolicitudDesembolsosBean solicitudBean = (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        final Long idOperacion =  solicitudBean.getIdOperacion();
        solicitudBean.setSoloCondicionesCalendario(Boolean.TRUE);
        logger.warning("Inicia metodo abrirPopupCumplimientoCondiciones :"+idOperacion);
        try{
            final Map<String, Object> params = new HashMap<String, Object>(); 
            final Long idContrato =  solicitudBean.getIdContratoSeleccionado();
            final String idSolicitud = solicitudBean.getIdSolicitud().toString(); 
            
            Boolean resultado = Boolean.FALSE;
            Boolean pasoValidacionDatosContratos = Boolean.FALSE;
            
            if(solicitudBean==null){
                logger.warning("Instancia de solicitudBean es NULL "+idOperacion);
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_INICIO_VALIDACION"));
                return;
            }
            logger.warning("Datos idOperacion :"+idOperacion+" - idSolicitud:"+idSolicitud+" - idContrato_x:"+idContrato+" - idContrato_x2:"+idContratoDesemboldoSeleccionado);
            if(!validarMontoSolicitudMenorAMontoOperacion()){
                logger.warning("Inf, No paso la validacion de montos");
                return;
            }
            
            params.put("idSolicitud", Long.parseLong(idSolicitud));
            logger.warning("validando campos obligatorios de los contratos de la solicitud..."+idOperacion);
            //se valida que a todos los contratos de desembolso de la solicitud cuenten con los datos obligatorios
            pasoValidacionDatosContratos = execute(params, "validarCamopsObligatoriosContratos");
            if(!pasoValidacionDatosContratos){
                logger.warning("***Error, no todos los contratos tienen los datos obligatorios "+idOperacion);
                JSFUtils.addFacesErrorMessage(getStringFromBundle("CAPTURAR_DATOS_OBLIGATORIOS"));
                return;
            }
            logger.warning("Ejecutando OperationBinding para validar montos de contratos vs asignacion. "+idOperacion);
            resultado = execute(params, "validarMontoContratoVsLineaPasiva");
            if(!resultado){
                logger.warning("No se pudo validar montos de contratos vs asignacion. "+idOperacion);
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_MONTO_ASIGNACION"));
                return;
            }
            resultado = validarFuentesSinIdLineaPasiva();   
            if(resultado==null){
                logger.warning("La validacion de Fuentes sin Id linea pasiva no se realizo correctamente. "+idOperacion);
                JSFUtils.addFacesErrorMessage("La validacion de Fuentes sin Id linea pasiva no se realizo correctamente.");
                return;
            }          
            if(!resultado){
                logger.warning("La validacion de Fuentes sin Id Linea Pasiva no se cumplio. "+idOperacion);
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_FUENTES_PENDIENTES"));
                return;
            }  
            
            Boolean validar = Boolean.FALSE; 
            final BindingContainer bindings = ADFUtils.getBindingContainer();
            final OperationBinding operationBinding = bindings.getOperationBinding("condicionDesembolsoPorValidar"); 
            operationBinding.getParamsMap().put("pnID_Contrato_Desembolso", idContrato);
            operationBinding.execute(); 
            
            final Integer estado=(Integer)JSFUtils.resolveExpression("#{bindings.IdTcaEstado1.inputValue}");
            logger.warning("Estado del contrato . "+estado+" - "+idOperacion);
            solicitudBean.getListaCondiciones().clear();
            
            logger.warning("Estado inicial llenar por defecto los check "+idOperacion);
            final DCBindingContainer bindingsVO = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            final DCIteratorBinding dcIterator = bindingsVO.findIteratorBinding("CondicionDesembolsoPorValidarVOIterator");
            final RowSetIterator it = dcIterator.getViewObject().createRowSetIterator(null);
            if(it!=null){
                it.reset();
                logger.warning("Antes de recorrer el iterador "+it.getRowCount()+" - "+idOperacion);
                while(it.hasNext()){
                    final Row tmp=it.next();
                    final CondicionDesembolso item= new CondicionDesembolso();
                    final Long id=(Long)tmp.getAttribute("IdCondicion");
                    item.setIdCondicion(id);
                    item.setIdOperacion((Long)tmp.getAttribute("IdOperacion"));
                    item.setNombre((String)tmp.getAttribute("Nombre"));
                    item.setBanEstatus((String)tmp.getAttribute("BanEstatus"));
                    item.setEstadoTcc((String)tmp.getAttribute("EstadoTcc"));
                    item.setControlCondicion((String)tmp.getAttribute("ControlCondicion"));
                    item.setEvento((String)tmp.getAttribute("Evento"));
                    item.setTipoDesembolso((String)tmp.getAttribute("TipoDesembolso"));
                    item.setFechaRegistro((Timestamp)tmp.getAttribute("FechaRegistro"));
                    item.setDescripcionCalendario((String)tmp.getAttribute("DescripcionCalendario"));
                    item.setCategorias((String)tmp.getAttribute("Categorias"));
                    item.setValidadores((String)tmp.getAttribute("Validadores"));
                    item.setAgrupador((Long)tmp.getAttribute("Agrupador"));
                    item.setEstadoTransaccion((Integer)tmp.getAttribute("EstadoTransaccion"));
                    item.setEnProceso((Integer)tmp.getAttribute("EnProceso"));
                    if(item.getEvento()!=null){
                        solicitudBean.setSoloCondicionesCalendario(Boolean.FALSE);
                    }
                    logger.warning("se agrega la condicion "+id+" - "+idOperacion);
                    if((estado.equals(FenixConstants.ESTADO_SOLICITUD_CREADO) || estado.equals(FenixConstants.ESTADO_SOLICITUD_VALIDACION_DE_RECURSOS)) && item.getEvento()!=null){
                        logger.warning("Se marca seleccionado "+id+" - "+idOperacion);
                        item.setSeleccionado(Boolean.TRUE);
                    }
                    solicitudBean.getListaCondiciones().add(item);
                }
                it.closeRowSetIterator();
            }
            if(operationBinding.getResult()==null){
                logger.warning("El valor de retorno de obtenerCondicionPorValidar es nulo.");
            }
            if(operationBinding.getResult()!=null){
                logger.warning( "Se han cargado datos de obtenerCondicionPorValidarSolicitud.");
                validar = Boolean.TRUE;
            }
            if(validar) {
                RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
                getPopupIniciarCumplimientoCondiciones().show(popupHints); 
            }             
            solicitudBean.setFechaSolicitudLectura(Boolean.TRUE);
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorDatosEncabezadoSolicitud());
                
        }catch(Exception e){
            logger.severe(e);
            JSFUtils.addFacesErrorMessage("abrirPopupCumplimientoCondiciones: +"+e.getMessage());
        }
        logger.warning("Termina metodo abrirPopupCumplimientoCondiciones "+idOperacion);
    }
    public void abrirPopupValidarAsignacion(ActionEvent actionEvent) {
        final SolicitudDesembolsosBean solicitudBean = (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        final Long idOperacion =  solicitudBean.getIdOperacion();
        logger.warning("Inicia metodo abrirPopupValidarAsignacion :"+idOperacion);
        try{
            final Map<String, Object> params = new HashMap<String, Object>(); 
            final Long idContrato =  solicitudBean.getIdContratoSeleccionado();
            final String idSolicitud = solicitudBean.getIdSolicitud().toString(); 
            
            Boolean resultado = Boolean.FALSE;
            Boolean pasoValidacionDatosContratos = Boolean.FALSE;
            
            if(solicitudBean==null){
                logger.warning("Instancia de solicitudBean es NULL "+idOperacion);
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_INICIO_VALIDACION"));
                return;
            }
            logger.warning("Datos idOperacion :"+idOperacion+" - idSolicitud:"+idSolicitud+" - idContrato_x:"+idContrato+" - idContrato_x2:"+idContratoDesemboldoSeleccionado);
            if(!validarMontoSolicitudMenorAMontoOperacion()){
                logger.warning("Inf, No paso la validacion de montos "+idOperacion);
                return;
            }
            
            params.put("idSolicitud", Long.parseLong(idSolicitud));
            logger.warning("validando campos obligatorios de los contratos de la solicitud..."+idOperacion);
            //se valida que a todos los contratos de desembolso de la solicitud cuenten con los datos obligatorios
            pasoValidacionDatosContratos = execute(params, "validarCamopsObligatoriosContratos");
            if(!pasoValidacionDatosContratos){
                logger.warning("***Error, no todos los contratos tienen los datos obligatorios "+idOperacion);
                JSFUtils.addFacesErrorMessage(getStringFromBundle("CAPTURAR_DATOS_OBLIGATORIOS"));
                return;
            }
            logger.warning("Ejecutando OperationBinding para validar montos de contratos vs asignacion.");
            resultado = execute(params, "validarMontoContratoVsLineaPasiva");
            if(!resultado){
                logger.warning("No se pudo validar montos de contratos vs asignacion. "+idOperacion);
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_MONTO_ASIGNACION"));
                return;
            }
            resultado = validarFuentesSinIdLineaPasiva();   
            if(resultado==null){
                logger.warning("La validacion de Fuentes sin Id linea pasiva no se realizo correctamente. "+idOperacion);
                JSFUtils.addFacesErrorMessage("La validacion de Fuentes sin Id linea pasiva no se realizo correctamente.");
                return;
            }          
            if(!resultado){
                logger.warning("La validacion de Fuentes sin Id Linea Pasiva no se cumplio. "+idOperacion);
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_FUENTES_PENDIENTES"));
                return;
            }
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            this.getPopupIniciarValidacionAsignaccon().show(popupHints); 
            
            solicitudBean.setFechaSolicitudLectura(Boolean.TRUE);
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorDatosEncabezadoSolicitud());
                
        }catch(Exception e){
            logger.severe(e);
            JSFUtils.addFacesErrorMessage(e.getMessage());
        }
        logger.warning("Termina metodo abrirPopupValidarAsignacion "+idOperacion);
    }
    
    public void cerrarPopupCumplimientoCondiciones(ActionEvent actionEvent) {
        this.getPopupIniciarCumplimientoCondiciones().hide();
    }
    public void cerrarPopupValidacionAsignacion(ActionEvent actionEvent) {
        this.getPopupIniciarValidacionAsignaccon().hide();
    }

    public void setPrimerDesembolsoEnviado(Boolean primerDesembolsoEnviado) {
        this.primerDesembolsoEnviado = primerDesembolsoEnviado;
    }

    public Boolean getPrimerDesembolsoEnviado() {
        return primerDesembolsoEnviado;
    }
    @Override
    public void processOnClick(final Object payload, final Object temp) {
        final SolicitudDesembolsosBean solicitudBean = (SolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.SolicitudDesembolsoBean}");
        logger.warning("Inicia metodo processOnClick "+solicitudBean.getIdOperacion());
        try{
            final BindingContainer bindings = ADFUtils.getBindingContainer();
            final OperationBinding operationBinding = bindings.getOperationBinding("condicionDesembolsoPorValidar"); 
            operationBinding.getParamsMap().put("pnID_Contrato_Desembolso", solicitudBean.getIdContrato());
            operationBinding.execute(); 
            
            final Integer estado=(Integer)JSFUtils.resolveExpression("#{bindings.IdTcaEstado1.inputValue}");
            logger.warning("Estado del contrato . "+estado+" - "+solicitudBean.getIdOperacion());
            
            final Map<String, Object> params = new HashMap<String, Object>();
            params.put("idSolicitud", solicitudBean.getIdSolicitud());
            params.put("idOperacion", solicitudBean.getIdOperacion());
            final Boolean resultado = execute(params, "requiereValidacionAsignacionSolicitud");
            
            if(resultado!=null){
                logger.warning("Resulado metodo requiereValidacionAsignacionSolicitudn. idSolicitud:"+ solicitudBean.getIdSolicitud()+" - idOperacion:"+solicitudBean.getIdOperacion()+" Res:"+resultado);
                solicitudBean.setRequiereValidacionAsignacion(resultado);
            }
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.accionesTreSolicitudDesembolso);
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.warning("Termina metodo processOnClick "+solicitudBean.getIdOperacion());
    }
    @Override
    public void processOnRefresh(Object payload, Object temp) {
       
    }

    @Override
    public void processOnChange(Object payload, Object temp) {
        
    }

    
}

