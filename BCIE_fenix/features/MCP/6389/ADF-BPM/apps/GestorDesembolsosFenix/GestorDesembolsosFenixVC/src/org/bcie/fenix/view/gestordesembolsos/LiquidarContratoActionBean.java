package org.bcie.fenix.view.gestordesembolsos;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import java.util.ResourceBundle;

import javax.el.ELContext;

import javax.el.ExpressionFactory;

import javax.el.MethodExpression;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.apache.myfaces.trinidad.model.CollectionModel;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class LiquidarContratoActionBean extends FenixActionBean{
    
    public static ADFLogger logger = null;
    
    public LiquidarContratoActionBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void generarFT05ActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside generarFT05ActionListener");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        Row row = null;
        LiquidarContratoBean liquidarContratoBean= (LiquidarContratoBean)JSFUtils.resolveExpression("#{pageFlowScope.LiquidarContratoManagedBean}");
        Long idContratoDesembolso = null;
        String contratoFlexcube = null;
        Timestamp fechaEfectiva = null;
        String cuentaPuente = null;
        String cuentaCliente = null;
        String bhqTransferenciaFt05 = null;
        Timestamp fechaEfectivaFt05 = null;
        Long idTransferenciaFT05 = null;
        Boolean respuestaGenerarFT05 = Boolean.FALSE;
        Boolean respuestaInsertarFt05 = Boolean.FALSE;
        
        try {
            try{
                if (ADFUtils.getDCBindingContainer().findIteratorBinding("FormularioGeneracionFT05VOIterator") != null) {
                    row = ADFUtils.getDCBindingContainer().findIteratorBinding("FormularioGeneracionFT05VOIterator").getCurrentRow();
                    if(row != null)
                    {
                        idContratoDesembolso = Long.parseLong(row.getAttribute("Id").toString());
                        contratoFlexcube = row.getAttribute("ContratoFlexcube").toString();
                        fechaEfectiva =  Timestamp.valueOf(row.getAttribute("FechaEfectiva").toString());
                        cuentaPuente = row.getAttribute("CuentaPuente").toString();
                        cuentaCliente = row.getAttribute("CuentaCliente").toString();
                        
                        logger.warning("idContratoDesembolso: " + idContratoDesembolso);
                        logger.warning("contratoFlexcube: " + contratoFlexcube);
                        logger.warning("fechaEfectiva: " + fechaEfectiva);
                        logger.warning("cuentaPuente: " + cuentaPuente);
                        logger.warning("cuentaCliente: " + cuentaCliente);
                    }
                }
                
                OperationBinding operationBindingGenerarFT05 = bindings.getOperationBinding("generarFT05Desembolso");
                operationBindingGenerarFT05.getParamsMap().put("idContratoDesembolso", idContratoDesembolso);
                operationBindingGenerarFT05.getParamsMap().put("contratoFlexcube", contratoFlexcube);
                operationBindingGenerarFT05.getParamsMap().put("fechaEfectiva", fechaEfectiva);
                operationBindingGenerarFT05.getParamsMap().put("cuentaPuente", cuentaPuente);
                operationBindingGenerarFT05.getParamsMap().put("cuentaCliente", cuentaCliente);
                operationBindingGenerarFT05.execute();
                respuestaGenerarFT05 = (Boolean)operationBindingGenerarFT05.getResult();
                logger.warning("Valor obtenido de respuestaGenerarFT05 " + respuestaGenerarFT05);
                
            }catch(Exception e){   
                logger.log(ADFLogger.ERROR, "Error en metodo generarFT05Desembolso.", e);
            }
            
            try{
                if(respuestaGenerarFT05 != null && respuestaGenerarFT05 == true)
                {
//                    if (ADFUtils.getDCBindingContainer().findIteratorBinding("FormularioGeneracionFT05VOIterator") != null) {
//                        row = ADFUtils.getDCBindingContainer().findIteratorBinding("FormularioGeneracionFT05VOIterator").getCurrentRow();
//                        if(row != null)
//                        {
//                            idContratoDesembolso = Long.parseLong(row.getAttribute("Id").toString());
//                            contratoFlexcube = row.getAttribute("ContratoFlexcube").toString();
//                            fechaEfectiva =  Timestamp.valueOf(row.getAttribute("FechaEfectiva").toString());
//                            cuentaPuente = row.getAttribute("CuentaPuente").toString();
//                            cuentaCliente = row.getAttribute("CuentaCliente").toString();
//                            bhqTransferenciaFt05 = row.getAttribute("BhqTransferenciaFt05").toString();
//                            fechaEfectivaFt05 = Timestamp.valueOf(row.getAttribute("FechaEfectivaFt05").toString());
//                            idTransferenciaFT05 = Long.parseLong(row.getAttribute("IdTransferenciaFT05").toString());
//                            
//                            logger.warning("idContratoDesembolso: " + idContratoDesembolso);
//                            logger.warning("contratoFlexcube: " + contratoFlexcube);
//                            logger.warning("fechaEfectiva: " + fechaEfectiva);
//                            logger.warning("cuentaPuente: " + cuentaPuente);
//                            logger.warning("cuentaCliente: " + cuentaCliente);
//                            logger.warning("bhqTransferenciaFt05: " + bhqTransferenciaFt05);
//                            logger.warning("fechaEfectivaFt05: " + fechaEfectivaFt05);
//                            logger.warning("idTransferenciaFT05: " + idTransferenciaFT05);
//                        }
//                    }
//                    
//                    OperationBinding operationBindingInsertarFT05 = bindings.getOperationBinding("insertarCamposFT05");
//                    operationBindingInsertarFT05.getParamsMap().put("idContratoDesembolso", idContratoDesembolso);
//                    operationBindingInsertarFT05.getParamsMap().put("bhqTransferenciaFT05", bhqTransferenciaFt05);
//                    operationBindingInsertarFT05.getParamsMap().put("fechaEfectivaFT05", fechaEfectivaFt05);
//                    operationBindingInsertarFT05.getParamsMap().put("idTransferenciaFT05", idTransferenciaFT05);
//                    operationBindingInsertarFT05.execute();
//                    respuestaInsertarFt05 = (Boolean)operationBindingInsertarFT05.getResult();
//                    logger.warning("Valor obtenido de respuestaInsertarFt05 " + respuestaInsertarFt05);
                    
                    
                    OperationBinding operationBindingInsertarFT05 = bindings.getOperationBinding("obtenerFT05");
                    operationBindingInsertarFT05.getParamsMap().put("idContrato", idContratoDesembolso);
                    operationBindingInsertarFT05.execute();
                    Map obtuvoFT05 = (HashMap)operationBindingInsertarFT05.getResult();
                    if(null!=obtuvoFT05){
                        if(null!=obtuvoFT05.get("respuesta")){
                                Boolean recuperoDatos=(Boolean)obtuvoFT05.get("respuesta");
                                if(recuperoDatos){
                                    if(null!=obtuvoFT05.get("fecha")){
                                        Timestamp fechaft05= (Timestamp) obtuvoFT05.get("fecha");
                                            AttributeBinding fechaEfectiva05;
                                            fechaEfectiva05 = ADFUtils.findControlBinding("FechaEfectivaFt05");
                                            fechaEfectiva05.setInputValue(fechaft05);
                                        }
                                    if(null!=obtuvoFT05.get("bhq")){
                                        String codigoft05=(String) obtuvoFT05.get("bhq");
                                            AttributeBinding codigobhq05;
                                            codigobhq05 = ADFUtils.findControlBinding("BhqTransferenciaFt05");
                                            codigobhq05.setInputValue(codigoft05);
                                        }
                                    }
                                else{
                                        logger.warning("no obtuvieron datos del contrato, respuesta del metodo incorrecto");
                                    }
                            }
                        else{
                                logger.warning("no obtuvieron datos del contrato, respuesta que recibio fue vacio");
                            }
                        }
                    else{
                        logger.warning("no obtuvieron datos del contrato");
                        }
                }
                else{
                    logger.warning("No se ha podido Generar la FT05");
                    String msg = "No ha sido posible realizar la generación de la FT05, por favor inténtelo de nuevo.";
                    JSFUtils.addFacesErrorMessage(msg);
                }
            }catch(Exception e){   
                logger.log(ADFLogger.ERROR, "Error en metodo insertarCamposFT05." + e.getClass() + "." + e.getMessage());
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en generarFT05ActionListener." + e.getClass() + "." + e.getMessage());
        }
    }
}
