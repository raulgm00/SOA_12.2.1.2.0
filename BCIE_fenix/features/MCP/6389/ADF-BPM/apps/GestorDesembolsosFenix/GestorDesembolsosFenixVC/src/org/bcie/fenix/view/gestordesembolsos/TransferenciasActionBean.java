package org.bcie.fenix.view.gestordesembolsos;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.dms.instrument.PhaseEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class TransferenciasActionBean {
    
    public static ADFLogger logger = null;
    private RichPanelGroupLayout contenedorInstruccionesPago;
    private RichOutputText validacionesIniciales;
    private RichSelectOneChoice cuentaClienteLOVUIC;
    private RichSelectOneChoice cuentaClienteTransientLOVUIC;

    public TransferenciasActionBean() {
        if (logger == null){
            logger = ADFLogger.createADFLogger(TransferenciasActionBean.class);
        }
    }

    public void calcularFechaEstimadaDisponibilidad(Date fechaOrigen, String moneda) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd z yyyy");
        Calendar cal = new GregorianCalendar();
        if(null != fechaOrigen){
            try {
                logger.warning("La fecha a calcular es ->"+fechaOrigen);
                String fecha = fechaOrigen.toString().substring(4).replaceAll(" 00:00:00", "");
                logger.warning(fecha);
                Date fechaEstimadaDisponibilidad = formatter.parse(fecha);
                cal.setTimeInMillis(fechaEstimadaDisponibilidad.getTime());
                
                if(moneda == "USD")
                cal.add(Calendar.DATE, 5);
                
                
                Date date = new Date(cal.getTimeInMillis());
              //  setFechaEstimadaDisponibilidad(date);
               // AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaEstimDisponibilidadUIC());
            } catch (ParseException e) {
                logger.warning("Error al convertir fecha" + e);
            }
        }
    }
    
    public void cambioOrigenTransferencia(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inicio al metodo cambioOrigenTransferencia");
       String origenTransferenca = (String)valueChangeEvent.getNewValue();
       
       try{
           ADFUtils.setBoundAttributeValue("OrigenTranferenciaCliente", origenTransferenca);
       }catch(Exception e){
           logger.warning("ERROR al asignar origenTransferenca a AttributeValue OrigenTranferenciaCliente: ", e);
       }
       
        TransferenciasBean bean = (TransferenciasBean)JSFUtils.resolveExpression("#{pageFlowScope.TransferenciasBean}");   
       if(origenTransferenca.equals("DIRECTO_CLIENTE")){
            logger.warning("valor recuperado DIRECTO_CLIENTE");
            bean.setCargarTransferenciasInstrucciones(Boolean.FALSE);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("origenTransferencia", "DIRECTO_CLIENTE");  
        }
       if(origenTransferenca.equals("CUENTAS_BCIE")){    
            logger.warning("valor recuperado CUENTAS_BCIE");                        
            bean.setCargarTransferenciasInstrucciones(Boolean.TRUE);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("origenTransferencia", "CUENTAS_BCIE");  
        }
       if(origenTransferenca.equals("SIN_SALIDA_FONDOOS")){    
            logger.warning("valor recuperado CUENTAS_BCIE");                        
            bean.setCargarTransferenciasInstrucciones(Boolean.FALSE);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("origenTransferencia", "SIN_SALIDA_FONDOOS");  
        }
        
        //Forza la actualizacion del modelo
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String seObtienecuenta=null;
        String cuentaCliente = (String) ADFUtils.getBoundAttributeValue("CuentaCliente");
        
        if(bean != null &&
           bean.getIdOperacion() != null){
            bean.ObtenerBHQCliente(bean.getIdOperacion());
            Boolean registroObtenidos=Boolean.FALSE;
            
            String nombreMoneda=null;
            
            if(null!= bean.getBHQCliente()){
                logger.warning("Asigna BHQ Cliente a Attribute Value BHQCliente: " + 
                               bean.getBHQCliente());
                ADFUtils.setBoundAttributeValue("BHQCliente", bean.getBHQCliente());
                registroObtenidos=Boolean.TRUE;
            }else{
                logger.severe("No hay valor en el atributo BHQCliente del Managed Bean");
            }
            
            if(null != bean.getDescripcionMonedaContrato()){
                logger.warning("Asigna Descripcion de Modena del Contrato a Attribute Value: " + 
                               bean.getDescripcionMonedaContrato());
                ADFUtils.setBoundAttributeValue("DescripcionMoneda", bean.getDescripcionMonedaContrato());
                if(registroObtenidos){
                    seObtienecuenta=obtieneCuenta(bean.getBHQCliente(),bean.getDescripcionMonedaContrato());
                        if(null!=seObtienecuenta){
                            ADFUtils.setBoundAttributeValue("CuentaCliente", seObtienecuenta);
                            //cuentaCliente.setInputValue(seObtienecuenta);
                            AdfFacesContext.getCurrentInstance().addPartialTarget(getCuentaClienteLOVUIC());
                        }
                        else{
                            logger.warning("No se puede cargar datos de la cuenta");
                        }
                    }
                else{
                        logger.warning("No se puede refrescar las cuentas");
                    }
            }else{
                AttributeBinding nombreMoneda2=ADFUtils.findControlBinding("DescripcionMoneda");
                if(null!=  (String) nombreMoneda2.getInputValue()){
                    nombreMoneda=(String) nombreMoneda2.getInputValue();
                        seObtienecuenta=obtieneCuenta(bean.getBHQCliente(),nombreMoneda);
                            if(null!=seObtienecuenta){
                                    ADFUtils.setBoundAttributeValue("CuentaCliente", seObtienecuenta);
                                    //cuentaCliente.setInputValue(seObtienecuenta);
                                    AdfFacesContext.getCurrentInstance().addPartialTarget(getCuentaClienteLOVUIC());
                                }
                            else{
                                    logger.warning("No se puede cargar datos de la cuenta");
                                }
                    }
                else{
                
                logger.severe("No hay valor en el atributo DescripcionMonedaContrato del Managed Bean");
                }
            }
            if(getCuentaClienteLOVUIC() != null){
                AdfFacesContext.getCurrentInstance().addPartialTarget(getCuentaClienteLOVUIC());
            }
            else{
                    logger.warning("No se puede obtener datos");
                }
        }else{
            logger.warning("No se puede actualizar consulta de Cuenta Cliente, parametros requeridos de TransferenciasBean son NULL");
        }
       
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorInstruccionesPago());
        logger.warning("Termino al metodo cambioOrigenTransferencia");
    }
    


    public void cambioCuentaCliente(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inicia metodo cambioCuentaCliente.");
        
        String cuentaCliente = null;
        if(null != valueChangeEvent){
            try{
                cuentaCliente = (String) valueChangeEvent.getNewValue();
            }catch(Exception e){
                logger.warning("ERROR al recuperar cuentaCliente de ValueChangeEvent.", e);
            }
        }
        
        try{
            ADFUtils.setBoundAttributeValue("CuentaClienteAttrValue", cuentaCliente);
        }catch(Exception e){
            logger.warning("ERROR al asingnar CuentaClienteAttrValue.", e);
        }
        
        logger.warning("BoundAttribute CuentaClienteAttrValue: " + ADFUtils.getBoundAttributeValue("CuentaClienteAttrValue"));
        logger.warning("Termina metodo cambioCuentaCliente.");
    }
    
    public String obtieneCuenta(String codigoCliente, String moneda){
        String resultado=null;
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("obtenerCuenta");
            operationBinding.getParamsMap().put("pCodigoCliente", codigoCliente);
            operationBinding.getParamsMap().put("pMoneda", moneda);
            //operationBinding.execute();
            resultado = (String) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
               logger.warning("Error al obtener los datos, error al ejecutar el metodo intente más tarde");
            }
           return resultado;
        }
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
     /***    Accesors   ***/

    public void setContenedorInstruccionesPago(RichPanelGroupLayout contenedorInstruccionesPago) {
        this.contenedorInstruccionesPago = contenedorInstruccionesPago;
    }

    public RichPanelGroupLayout getContenedorInstruccionesPago() {
        return contenedorInstruccionesPago;
    }

    public void setValidacionesIniciales(RichOutputText validacionesIniciales) {
        this.validacionesIniciales = validacionesIniciales;
    }

    public RichOutputText getValidacionesIniciales() {      
        return validacionesIniciales;
    }


    public void setCuentaClienteLOVUIC(RichSelectOneChoice cuentaClienteLOVUIC) {
        this.cuentaClienteLOVUIC = cuentaClienteLOVUIC;
    }

    public RichSelectOneChoice getCuentaClienteLOVUIC() {
        return cuentaClienteLOVUIC;
    }

    public void setCuentaClienteTransientLOVUIC(RichSelectOneChoice cuentaClienteTransientLOVUIC) {
        this.cuentaClienteTransientLOVUIC = cuentaClienteTransientLOVUIC;
    }

    public RichSelectOneChoice getCuentaClienteTransientLOVUIC() {
        return cuentaClienteTransientLOVUIC;
    }
}
