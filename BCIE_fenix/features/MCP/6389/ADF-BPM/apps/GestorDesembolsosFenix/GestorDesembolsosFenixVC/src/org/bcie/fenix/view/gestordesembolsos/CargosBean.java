package org.bcie.fenix.view.gestordesembolsos;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import org.bcie.fenix.common.model.vo.CargosContratoDesembolsoVORowImpl;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;
import org.bcie.fenix.common.view.beans.FenixActionBean.OperationExecuteException;

public class CargosBean extends FenixActionBean implements Serializable {
    @SuppressWarnings("compatibility:5913076200428819134")
    private static final long serialVersionUID = 1L;

    public static final String BUNDLE = "view.GestorDesembolsosFenixVCBundle";
    private static ADFLogger logger = null;
    private Boolean validaPreinversion = Boolean.FALSE;
    private RichTable tablaCargosContratoDesembolsoUIC;

    public CargosBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void consultarCargosWSDesembolsos() {
        logger.warning("inside consultarCargosWSDesembolsos");
        
        long idDesembolso = getIdContratoDesembolso();
        
        if(idDesembolso != 0){
        
                String codigoExterno = getCodigoExterno();                
                logger.warning("idDesembolso " + idDesembolso);
                logger.warning("codigoExterno " + codigoExterno);
                
                this.consultarCargosDesembolsosWS("consultarCargosWS", idDesembolso, codigoExterno);
                this.consultarCargosDesembolsosWS("consultarDesembolsosWS", idDesembolso, codigoExterno);
                
                /*  Saldo de la preinversion.
                    Esta sección solo debe mostrarse cuando la operación a la que se le ha creado el desembolso no tenga un contrato de
                    desembolso registrado o desembolsado y  tenga asociada como padre una preinversión.
                    - Se obtiene la operación padre u operaciones padre de la operación actual.
                    - Si la/las operacion/nes padre tienen un podructo de tipo Preinversion se obtienen las operaciones asociadas a la operacion padre.
                    - Se obtienen verifica el estados de los contratos desembolsos para esas operaciones.
                */                
                 validaPreinversion();
                
                if(validaPreinversion)
                   recuperarValoresPreinversion();
                
        }else{
            logger.warning("No se recibio idDesembolso en tab de cargos! no se realizara ninguna accion");
        }               
    }
    
    private void consultarCargosDesembolsosWS(String method, long idDesembolso, String codigoExterno) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("idDesembolso", idDesembolso);
            params.put("codigoExterno", codigoExterno);
            super.execute(params, method);
        } catch (OperationExecuteException e) {
            logger.warning(e);
        }
    }
    
    private Long getIdContratoDesembolso() {
        if (JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}") != null) {
            try {
                return new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}").toString());
            } catch (NumberFormatException e) {
                logger.log(ADFLogger.ERROR, e.getMessage());
                return 0L;
            }
        } else {
            return 0L;
        }
    }
    
    private Long getIdOperacion() {
        if (JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null) {
            try {
                return new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
            } catch (NumberFormatException e) {
                logger.log(ADFLogger.ERROR, e.getMessage());
                return 0L;
            }
        } else {
            return 0L;
        }
    }
    
    private String getCodigoExterno() {
        logger.warning("Inicia metodo getCodigoExterno");
        if (JSFUtils.resolveExpression("#{pageFlowScope.pCodigoExterno}") != null) {
            return JSFUtils.resolveExpression("#{pageFlowScope.pCodigoExterno}").toString();
        } else {
            logger.warning("Codigo externo de bean de contratos es NULL. Recuperando del Modelo.");
            String respuesta = null;
            try {
                respuesta = execute(null, "getIdProductoFlexcubeByIdContrato");
            } catch (Exception e) {
                logger.warning("ERROR al recuperar el codigoExterno.", e);
            }
            logger.warning("Codigo externo recuperado del modelo: " + respuesta);
            return respuesta;
        }
    }

    public void validarMontoCargo(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        logger.warning("OldValue " + valueChangeEvent.getOldValue());
        logger.warning("NewValue " + valueChangeEvent.getNewValue());
        double diferencia = this.getMontoDesembolsar() - this.getMontoTotalCargos();
        
        if (diferencia >= 0) {
            logger.warning("el monto que se ingresa es correcto ");
            
            DCIteratorBinding voCargoDesembolso = null;
            RowSetIterator rowSetIterator = null;
            Row filaRecuperada = null;
            Double montoTotal = Double.valueOf("0");
            
            voCargoDesembolso =
                ADFUtils.getDCBindingContainer().findIteratorBinding("CargoDesemnolsoVOIterator");
            
            rowSetIterator = voCargoDesembolso.getRowSetIterator();
            filaRecuperada = rowSetIterator.first();
            while (filaRecuperada != null) {
                if (filaRecuperada.getAttribute("MontoCargo") != null) {
                    montoTotal = montoTotal + Double.valueOf(filaRecuperada.getAttribute("MontoCargo").toString());
                }
                filaRecuperada = rowSetIterator.next();
            }
            rowSetIterator.closeRowSetIterator();
            
            ADFUtils.setBoundAttributeValue("Total", montoTotal);
            logger.warning("Se setea el valor del montoTotal... ");
            logger.warning("Valor de Total: " + ADFUtils.getBoundAttributeValue("Total"));
            AdfFacesContext.getCurrentInstance().addPartialTarget(tablaCargosContratoDesembolsoUIC);
            
            /*
            if (ADFUtils.getDCBindingContainer().findIteratorBinding("CargoDesemnolsoVOIterator") != null) {
                RowSetIterator rsi = ADFUtils.getDCBindingContainer().findIteratorBinding("CargoDesemnolsoVOIterator").getRowSetIterator();
                
                if (rsi != null) {
                    Row row = rsi.createRow();
                    row.setAttribute("BanEstatus", row.getAttribute(CargosContratoDesembolsoVORowImpl.BANESTATUS));
                    row.setAttribute("Componente", row.getAttribute(CargosContratoDesembolsoVORowImpl.COMPONENTE));
                    row.setAttribute("Descripcion", row.getAttribute(CargosContratoDesembolsoVORowImpl.DESCRIPCION));
                    row.setAttribute("FechaRegistro", row.getAttribute(CargosContratoDesembolsoVORowImpl.FECHAREGISTRO));
                    row.setAttribute("IdContratoDesembolso", row.getAttribute(CargosContratoDesembolsoVORowImpl.IDCONTRATODESEMBOLSO));
                    row.setAttribute("MontoCargo", row.getAttribute(CargosContratoDesembolsoVORowImpl.MONTOCARGO));
                    rsi.insertRow(row);
             
                } else{
                    logger.warning("El RowSetIterator es null");
                }
            } else {
                logger.warning("No encontró CargoDesemnolsoVOIterator");
            }
            */
        } else {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MONTO_CARGOS_MAYOR_MONTO_CONTRATO"));
        }
    }
    
    private Double getMontoTotalCargos() {
        double montoTotalCargos = 0;
        if (ADFUtils.getDCBindingContainer().findIteratorBinding("CargosContratoDesembolsoVOIterator") != null) {
            Row row = ADFUtils.getDCBindingContainer().findIteratorBinding("CargosContratoDesembolsoVOIterator").getCurrentRow();
            
            if (row != null) {
                
                if (row.getAttribute(CargosContratoDesembolsoVORowImpl.MONTOCARGO) != null) {
                    try {
                        montoTotalCargos = (Double) row.getAttribute(CargosContratoDesembolsoVORowImpl.MONTOCARGO);
                    } catch(Exception e) {
                        logger.warning("No se pudo obtener el atributo MONTOCARGO del CargosContratoDesembolsoVOIterator - ", e.getMessage());
                    }
                } else {
                    logger.warning("No se encontró el MONTOCARGO");
                }
            } else {
                logger.warning("El Row es null");
            }
        } else {
            logger.warning("No se encontró el CargosContratoDesembolsoVOIterator");
        }
        return montoTotalCargos;
    }
    
    private Double getMontoDesembolsar() {
        double montoDesembolsar = 0;
        if (ADFUtils.getDCBindingContainer().findIteratorBinding("ContratoDesembolsoVOIterator") != null) {
            RowSetIterator rsi = ADFUtils.getDCBindingContainer().findIteratorBinding("ContratoDesembolsoVOIterator").getRowSetIterator();
            
            if (rsi != null) {
                Row row = rsi.getCurrentRow();
                
                if (row != null) {
                    try {
                        BigDecimal montoDesembolsarBD = (BigDecimal) row.getAttribute("MontoDesembolsar");
                        montoDesembolsar = montoDesembolsarBD.doubleValue();
                    } catch(Exception e) {
                        logger.warning("No se pudo obtener el atributo MontoDesembolsar del ContratoDesembolsoVOIterator - ", e.getMessage());
                    }
                } else {
                    logger.warning("El Row es null");
                }
            } else {
                logger.warning("El RowSetIterator es null");
            }
        } else {
            logger.warning("No se encontró el ContratoDesembolsoVOIterator");
        }
        return montoDesembolsar;
    }
    
    
    public void validaPreinversion() {
        logger.warning("Inside validaPreinversion.");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("validaPreinversion");
            operBinding.getParamsMap().put("idOperacion", getIdOperacion());
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("Error al ejecutar binding validaPreinversion: " + operBinding.getErrors());
            } else {
                setValidaPreinversion((Boolean) operBinding.getResult());
            }

        } catch (Exception e) {
            logger.log(ADFLogger.WARNING,
            "Error en validaPreinversion" + e.getClass() + ":" + e.getMessage());
        }
        logger.warning("validaPreinversion: " + getValidaPreinversion());
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
        logger.warning("*Inf, Termina metodo validaPreinversion con una duracion de: "+tiempo+" segundos");
    }
    
    
    public void recuperarValoresPreinversion() {
        logger.warning("*Inf, Inicia metodo recuperarValoresPreinversion.");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        String error = "";
         
         if(getIdOperacion() == 0L || getIdContratoDesembolso() == 0L){
             logger.warning("*Inf, Important! parametros requeridos idOperacion, idContratoDesemboso ");
             logger.warning("*Inf, idOperacion: "+getIdOperacion());
             logger.warning("*Inf, idContratoDesemboso: "+getIdContratoDesembolso());             
         }else{
                 try {
                     BindingContainer binding = ADFUtils.getBindingContainer();
                     OperationBinding operBinding = binding.getOperationBinding("recuperarValoresPreinversion");
                     operBinding.getParamsMap().put("idOperacion", getIdOperacion());
                     operBinding.getParamsMap().put("idDesembolso", getIdContratoDesembolso());
                     operBinding.execute();

                     if (!operBinding.getErrors().isEmpty()) {
                         logger.warning("***Error al ejecutar operBinding recuperarValoresPreinversion: " + operBinding.getErrors());                     
                     
                     }else{
                          error =  (String)operBinding.getResult();
                     }

                 } catch (Exception e) {
                     logger.warning("***Error al recuperar Valores de la Preinversion" + e);
                 }
             
             
                if(!error.equals(""))
                    JSFUtils.addFacesErrorMessage(""+error);
             
             }

        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
        logger.warning("*Inf, Termina metodo recuperarValoresPreinversion con una duracion de: "+tiempo+" segundos");
    }
    
    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (null != resourceBundle) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }
    
    public void setValidaPreinversion(Boolean validaPreinversion) {
        this.validaPreinversion = validaPreinversion;
    }

    public Boolean getValidaPreinversion() {
        return validaPreinversion;
    }

    public void setTablaCargosContratoDesembolsoUIC(RichTable tablaCargosContratoDesembolsoUIC) {
        this.tablaCargosContratoDesembolsoUIC = tablaCargosContratoDesembolsoUIC;
    }

    public RichTable getTablaCargosContratoDesembolsoUIC() {
        return tablaCargosContratoDesembolsoUIC;
    }
}
