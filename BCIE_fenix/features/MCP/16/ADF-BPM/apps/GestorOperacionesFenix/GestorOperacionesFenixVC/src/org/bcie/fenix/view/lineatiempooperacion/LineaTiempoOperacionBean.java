package org.bcie.fenix.view.lineatiempooperacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import oracle.adf.share.logging.ADFLogger;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;
import oracle.jbo.Row;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.ModelUtils;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class LineaTiempoOperacionBean implements Serializable {
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    
    private static final BigDecimal ROW_FECHA_INICIO = BigDecimal.ONE;
    private static final BigDecimal ROW_FECHA_FINAL = new BigDecimal(8);
    
    private transient CollectionModel lineaTiempoOperacionCM;
    private ArrayList list = null;
    
    private Date fechaInicio = new Date();
    private Date fechafinal = new Date();
    
    public LineaTiempoOperacionBean() {
        super();
        if (logger == null){
            logger = ADFLogger.createADFLogger(LineaTiempoOperacionBean.class);
        }
    }

    @SuppressWarnings("unchecked")
    public void inicializarLineaTiempoOperacion() {
        logger.warning("Dentro inicializarLineaTiempoOperacion");
        // Add event code here...
        list = new ArrayList();
        Row[]  rows = null;
        Row rowUltimoDesembolso = null;
        BindingContainer bindings = null;
        OperationBinding operationBinding = null;
        Long idoperacion = null;
        try{
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")){
                try{
                    String idFatherStr = null;
                    idFatherStr = JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString();
                    if(null != idFatherStr){
                        idoperacion = Long.parseLong(idFatherStr);
                        logger.warning("idoperacion : "+idoperacion);
                    }
                }catch(Exception e){
                    logger.severe("Error al recuperar idoperacion : ",e);
                }
            }else{
                logger.severe("El parametro pIdOperacion es nulo");
            }
            
            bindings = ADFUtils.getBindingContainer();
            
            try{
                operationBinding = bindings.getOperationBinding("obtenerFechaTimeLineOperacion");
                operationBinding.getParamsMap().put("idOperacion", idoperacion);
                operationBinding.execute();
                if(null != operationBinding.getResult()){
                    rows = (Row[]) operationBinding.getResult();
                    for (Row row : rows) {
                        agregarElemento(row);

                        if (null != row.getAttribute("Id") && null != row.getAttribute("Fecha")) {
                            BigDecimal id = (BigDecimal) row.getAttribute("Id");
                            
                            if (null != id) {
                                if (id.compareTo(ROW_FECHA_INICIO) == 0) {
                                    Timestamp fecha = (Timestamp) row.getAttribute("Fecha");
                                    Calendar c = Calendar.getInstance();
                                    c.setTime(fecha);
                                    c.add(Calendar.MINUTE, -1);
                                    fechaInicio = c.getTime();
                                    logger.warning("Fecha inicial del timeline : " + fechaInicio);
                                }
                            }
                        }
                    }
                }
            }catch(Exception e){
                logger.severe("Error en obtenerFechasOperacion : ",e);
            }
            
            try{
                operationBinding = bindings.getOperationBinding("obtenerFechaTimeLineEnmienda");
                operationBinding.getParamsMap().put("idOperacion", idoperacion);
                operationBinding.execute();
                if(null != operationBinding.getResult()){
                    rows = (Row[]) operationBinding.getResult();
                    logger.warning("Encontrados en obtenerFechasEnmiendaOperacion: " + rows.length);
                    for(Row row : rows){
                        agregarElemento(row);
                    }
                }
            }catch(Exception e){
                logger.warning("Error en obtenerFechasEnmiendaOperacion : ",e);
            }
            
            try{
                operationBinding = bindings.getOperationBinding("obtenerFechaTimeLineEvaluacionSiemas");
                operationBinding.getParamsMap().put("idOperacion", idoperacion);
                operationBinding.execute();
                if(null != operationBinding.getResult()){
                    rows = (Row[]) operationBinding.getResult();
                    for(Row row : rows){
                        agregarElemento(row);      
                    }
                }
            }catch(Exception e){
                logger.severe("Error en obtenerFechaTimeLineEvaluacionSiemas : ",e);
            }
            
            try{
                operationBinding = bindings.getOperationBinding("obtenerFechaTimeLineEvaluacionExpost");
                operationBinding.getParamsMap().put("idOperacion", idoperacion);
                operationBinding.execute();
                if(null != operationBinding.getResult()){
                    rows = (Row[]) operationBinding.getResult();
                    for(Row row : rows){
                        agregarElemento(row);      
                    }
                }
            }catch(Exception e){
                logger.severe("Error en obtenerFechaTimeLineEvaluacionExpost : ",e);
            }
            
            try{
                operationBinding = bindings.getOperationBinding("obtenerFechaEfectivaUltimoDesembolso");
                operationBinding.getParamsMap().put("idOperacion", idoperacion);
                operationBinding.execute();
                if(null != operationBinding.getResult()){
                    rowUltimoDesembolso = (Row) operationBinding.getResult();
                    agregarElemento(rowUltimoDesembolso);      
                }else{
                    logger.warning("Se obtuvo objeto nulo, no se muestra la fecha en el time line");
                }
            }catch(Exception e){
                logger.severe("Error en obtenerFechaTimeLineEvaluacionExpost : ",e);
            }
            
            //[14120] Adiciona los procesos de formalizacion de enmiendas
            try{
                operationBinding = bindings.getOperationBinding("obtenerFechaTimeLineFormalizacionEnmienda");
                operationBinding.getParamsMap().put("idOperacion", idoperacion);
                operationBinding.execute();
                if(null != operationBinding.getResult()){
                    rows = (Row[]) operationBinding.getResult();
                    logger.warning("Encontrados en obtenerFechaTimeLineFormalizacionEnmienda: " + rows.length);
                    for(Row row : rows){
                        agregarElemento(row);
                    }
                }
            }catch(Exception e){
                logger.severe("Error en obtenerFechaTimeLineFormalizacionEnmienda : ",e);
            }
            
        }catch(Exception e){
            logger.severe("Error en inicializarLineaTiempoOperacion : ",e);
        }

        logger.warning("Cantidad de registros obtenidos en la lista : "+list.size());

        lineaTiempoOperacionCM = ModelUtils.toCollectionModel(list);
        
        fechafinal = calcularFechaMayor();
        logger.warning("valor de fechaInicio : "+fechaInicio);
        logger.warning("valor de fechaFinal : "+fechafinal);
        //aumentar años para evitar problemas con el componente
        if(fechaInicio.compareTo(fechafinal)== 0){
            logger.warning("fechaInicio es igual a fechaFinal,aumentar años");
            Calendar fortyNinersSuck = Calendar.getInstance();
            fortyNinersSuck.setTimeInMillis(fechaInicio.getTime());
            fortyNinersSuck.add(Calendar.YEAR, 5);
            fechafinal = new Date(fortyNinersSuck.getTimeInMillis());
            logger.warning("fechafinal con años aumentados: " + fechafinal);

        }
        
        logger.warning("Fuera inicializarLineaTiempoOperacion");
    }

    @SuppressWarnings("unchecked")
    public void agregarElemento(Row row){
        BigDecimal id = null;
        String descripcion = null;
        Timestamp fecha = null;
        try{
            descripcion = row.getAttribute("Descripcion") != null ? 
                (String)row.getAttribute("Descripcion") : null;
            fecha = row.getAttribute("Fecha") != null ?
                (Timestamp)row.getAttribute("Fecha") : null;
            id = row.getAttribute("Id") != null ?
                (BigDecimal)row.getAttribute("Id") : null;
            if(descripcion != null && fecha != null && id != null){
                logger.warning("Inserta registro descripcion : "+descripcion + " con fecha : "+fecha);
                list.add(new LineaTiempoOperacion(descripcion,new Date(fecha.getTime()),id));
            }   
        }catch(Exception e){
            logger.severe("Error en agregarElemento : ",e);
        }
    }
    
    
    public Date calcularFechaMayor(){
        logger.warning("Dentro de calcularFechaMayor");
        logger.warning("cantidad de registros a evaluar:"+list.size());
        Date fechaMayor = new Date();
        Date fechaAux = null;
        for (int i = 0; i < list.size(); i++){
            LineaTiempoOperacion row =(LineaTiempoOperacion)list.get(i);
            fechaAux = row.getFecha();
            if(fechaAux.compareTo(fechaMayor)>0){
                logger.warning("fechaAux :"+fechaAux+" es mayor a fechaMayor :"+fechaMayor);
                fechaMayor = fechaAux;
            }else{
                logger.warning("fechaAux :"+fechaAux+" es menor a fechaMayor :"+fechaMayor);
            }
        }
        
        logger.warning("incrementar un dia a la fecha mayor :"+fechaMayor);
        fechaMayor = addDays(fechaMayor, 1);
        logger.warning("Fuera de calcularFechaMayor,fechaMayor es :"+fechaMayor);
        return fechaMayor;
    }
    
    public static Date addDays(Date date, int days){
        logger.warning("Dentro de addDays");
        logger.warning("fecha :"+date);
        logger.warning("cantidad de dias a sumar:"+days);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        logger.warning("Fuera de addDays,return :"+cal.getTime());
        return cal.getTime();
    }


    public CollectionModel getLineaTiempoOperacionCM() {
        return lineaTiempoOperacionCM;
    }

    public void setLineaTiempoOperacionCM(CollectionModel lineaTiempoOperacionCM) {
        this.lineaTiempoOperacionCM = lineaTiempoOperacionCM;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }
}
