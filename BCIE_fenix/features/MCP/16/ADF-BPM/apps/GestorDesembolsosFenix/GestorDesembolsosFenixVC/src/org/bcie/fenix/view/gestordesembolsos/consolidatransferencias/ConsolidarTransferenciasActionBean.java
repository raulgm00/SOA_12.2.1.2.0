package org.bcie.fenix.view.gestordesembolsos.consolidatransferencias;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class ConsolidarTransferenciasActionBean implements Serializable {
    @SuppressWarnings("compatibility:1295225764595497791")
    private static final long serialVersionUID = 1L;
    private static ADFLogger logger = null;
    private RichButton revertirUI;
    private RichButton consolidarUI;

    public ConsolidarTransferenciasActionBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void eligeConsolidar(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inside eligeConsolidar.");

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());

        ConsolidarTransferenciasBean consolidarTransferenciasBean =
            (ConsolidarTransferenciasBean) JSFUtils.resolveExpression("#{pageFlowScope.consolidarTransferenciasBean}");

        if ((Boolean) valueChangeEvent.getNewValue()) {
            consolidarTransferenciasBean.setContador(consolidarTransferenciasBean.getContador() + 1);
        } else {
            consolidarTransferenciasBean.setContador(consolidarTransferenciasBean.getContador() - 1);
        }
        
        logger.warning("valor obtenido: " + valueChangeEvent.getNewValue());
        
        if (consolidarTransferenciasBean.getContador().compareTo(2) == 0 ||
            consolidarTransferenciasBean.getContador().compareTo(2) == 1) {
            consolidarTransferenciasBean.setConsolidar(Boolean.TRUE);
        } else {
            consolidarTransferenciasBean.setConsolidar(Boolean.FALSE);
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(consolidarUI);
    }

    public void setRevertirUI(RichButton revertirUI) {
        this.revertirUI = revertirUI;
    }

    public RichButton getRevertirUI() {
        return revertirUI;
    }

    public void setConsolidarUI(RichButton consolidarUI) {
        this.consolidarUI = consolidarUI;
    }

    public RichButton getConsolidarUI() {
        return consolidarUI;
    }

    public void consolidarTransferencias(ActionEvent actionEvent) {
        logger.warning("Inside consolidarTransferencias.");

        ConsolidarTransferenciasBean consolidarTransferenciasBean =
            (ConsolidarTransferenciasBean) JSFUtils.resolveExpression("#{pageFlowScope.consolidarTransferenciasBean}");

        DCIteratorBinding voTransferencias =
            ADFUtils.getDCBindingContainer().findIteratorBinding("TransferenciasConsolidarVOIterator");
        //voComisiones.executeQuery();

        RowSetIterator iq = voTransferencias.getViewObject().createRowSetIterator(null);
        iq.reset();

        logger.warning("transferencias: " + voTransferencias.getEstimatedRowCount());
        Long primerTransferencia = null;
        Boolean consolida = Boolean.FALSE;
        Integer contador = 0;
        Long instruccion = null;
        Boolean aplica = Boolean.TRUE;
        String referencia = "";
        String cuenta = null;
        String nombreCuenta = null;
        String referenciaBanco = null;
        String cuentaBanco = null;
        Long idTransferencia = null;
        String numeroCtaBenef = null;
        String beneficiarioBenef = null;
        String beneficiarioBanBenef = null;
        String beneficiarioBanInter = null;

        String estadoInstruccion = null;
        String tipoOpcion = null;
        String tipoOpcionBan = null;
        String tipoOpcionInter = null;
        String numCtabanbenef = null;
        String numCtaInter = null;
        Long idOperacion = null;
        String dirBenef = null;
        String dirBanBenef = null;
        String dirInterBenef = null;
        String idBenef = null;
        String idBanBenef = null;
        String idInterBenef = null;

        BigDecimal montoTotal = BigDecimal.ZERO;
        Integer tipoMoneda = null;
        List<Long> lista = new ArrayList<Long>();

        while (iq.hasNext()) {
            Row row = iq.next();
            if (null != row) {
                logger.warning("IdConsolidacionPadre " + row.getAttribute("IdConsolidacionPadre"));
                logger.warning("Consolida " + row.getAttribute("Consolida"));
                logger.warning("IdTransferencia " + row.getAttribute("IdTransferencia"));
                logger.warning("IdTcaTipoMoneda " + row.getAttribute("IdTcaTipoMoneda"));
                logger.warning("NumeroCuenta  " + row.getAttribute("NumeroCuenta"));
                logger.warning("NombreCuenta  " + row.getAttribute("NombreCuenta"));
                logger.warning("NombreBancoTransferencia " + row.getAttribute("NombreBancoTransferencia"));
                logger.warning("IdBancoTransferencia " + row.getAttribute("IdBancoTransferencia"));
                logger.warning("numeroCtaBenef " + row.getAttribute("NumeroCtaBenef"));
                logger.warning("beneficiarioBenef " + row.getAttribute("BeneficiarioBenef"));
                logger.warning("beneficiarioBanBenef  " + row.getAttribute("BeneficiarioBanBenef"));
                logger.warning("beneficiarioBanInter  " + row.getAttribute("BeneficiarioBanInter"));


                logger.warning("tipoBenef " + row.getAttribute("TipoOpcionBenef"));
                logger.warning("tipoBanBenef " + row.getAttribute("TipoOpcionBanBenef"));
                logger.warning("TipoInter " + row.getAttribute("TipoOpcionBanInter"));
                logger.warning("operacion  " + row.getAttribute("IdOperacion"));
                logger.warning("estadoInstruccion  " + row.getAttribute("EstadoInstruccion"));

                logger.warning("dirbenef " + row.getAttribute("DireccionBenef"));
                logger.warning("dirBanbenef " + row.getAttribute("DireccionBanBenef"));
                logger.warning("dirInterBenef " + row.getAttribute("DireccionBanInter"));
                logger.warning("numCtaBanBenef  " + row.getAttribute("NumeroCtaBanBenef"));
                logger.warning("numCtaInterBenef  " + row.getAttribute("NumeroCtaBanInter"));

                logger.warning("idBenef " + row.getAttribute("IdentificadorBenef"));
                logger.warning("idBanBenef  " + row.getAttribute("IdentificadorBanBenef"));
                logger.warning("idInterBenef  " + row.getAttribute("IdentificadorBanInter"));

                if (null == (Long) row.getAttribute("IdConsolidacionPadre")) {
                    if (null != (Boolean) row.getAttribute("Consolida")) {
                        consolida = (Boolean) row.getAttribute("Consolida");
                        if (null != (Long) row.getAttribute("IdTransferencia")) {

                            idTransferencia = (Long) row.getAttribute("IdTransferencia");
                            tipoMoneda = (Integer) row.getAttribute("IdTcaTipoMoneda");
                            nombreCuenta = (String) row.getAttribute("NombreCuenta");
                            referenciaBanco = (String) row.getAttribute("NombreBancoTransferencia");
                            cuentaBanco = (String) row.getAttribute("IdBancoTransferencia");

                            if (consolida) {
                                referencia = referencia.concat(idTransferencia.toString());
                                logger.warning("referencia: " + referencia);
                                contador = contador + 1;
                                lista.add(idTransferencia);
                                if (null != (BigDecimal) row.getAttribute("MontoTransferencia")) {
                                    montoTotal = montoTotal.add((BigDecimal) row.getAttribute("MontoTransferencia"));
                                }

                                if (contador.compareTo(1) == 0) {
                                    if (null != row.getAttribute("IdTransferencia")) {
                                        primerTransferencia = (Long) row.getAttribute("IdTransferencia");
                                    }
                                    if (null != row.getAttribute("NumeroCuenta")) {
                                        cuenta = (String) row.getAttribute("NumeroCuenta");
                                    }
                                    if (null != row.getAttribute("NumeroCtaBenef")) {
                                        numeroCtaBenef = (String) row.getAttribute("NumeroCtaBenef");
                                    }
                                    if (null != row.getAttribute("BeneficiarioBenef")) {
                                        beneficiarioBenef = (String) row.getAttribute("BeneficiarioBenef");
                                    }
                                    if (null != row.getAttribute("BeneficiarioBanBenef")) {
                                        beneficiarioBanBenef = (String) row.getAttribute("BeneficiarioBanBenef");
                                    }
                                    if (null != row.getAttribute("BeneficiarioBanInter")) {
                                        beneficiarioBanInter = (String) row.getAttribute("BeneficiarioBanInter");
                                    }
                                    if (null != row.getAttribute("TipoOpcionBenef")) {
                                        tipoOpcion = (String) row.getAttribute("TipoOpcionBenef");
                                    }
                                    if (null != row.getAttribute("TipoOpcionBanBenef")) {
                                        tipoOpcionBan = (String) row.getAttribute("TipoOpcionBanBenef");
                                    }
                                    if (null != row.getAttribute("TipoOpcionBanInter")) {
                                        tipoOpcionInter = (String) row.getAttribute("TipoOpcionBanInter");
                                    }
                                    if (null != row.getAttribute("IdOperacion")) {
                                        idOperacion = (Long) row.getAttribute("IdOperacion");
                                    }
                                    if (null != row.getAttribute("EstadoInstruccion")) {
                                        estadoInstruccion = (String) row.getAttribute("EstadoInstruccion");
                                    }
                                    if (null != row.getAttribute("IdentificadorBenef")) {
                                        idBenef = (String) row.getAttribute("IdentificadorBenef");
                                    }
                                    if (null != row.getAttribute("IdentificadorBanBenef")) {
                                        idBanBenef = (String) row.getAttribute("IdentificadorBanBenef");
                                    }
                                    if (null != row.getAttribute("IdentificadorBanInter")) {
                                        idInterBenef = (String) row.getAttribute("IdentificadorBanInter");
                                    }
                                    if (null != row.getAttribute("NumeroCtaBanBenef")) {
                                        numCtabanbenef = (String) row.getAttribute("NumeroCtaBanBenef");
                                    }
                                    if (null != row.getAttribute("NumeroCtaBanInter")) {
                                        numCtaInter = (String) row.getAttribute("NumeroCtaBanInter");
                                    }
                                    if (null != row.getAttribute("DireccionBenef")) {
                                        dirBenef = (String) row.getAttribute("DireccionBenef");
                                    }
                                    if (null != row.getAttribute("DireccionBanBenef")) {
                                        dirBanBenef = (String) row.getAttribute("DireccionBanBenef");
                                    }
                                    if (null != row.getAttribute("DireccionBanInter")) {
                                        dirInterBenef = (String) row.getAttribute("DireccionBanInter");
                                    }

                                }
                                String nombreCampo = null;
                                nombreCampo = "NumeroCuenta";
                                if (contador.compareTo(1) > 0) {
                                    aplica =
                                        aplicaValidaciones(cuenta, ((String) row.getAttribute("NumeroCuenta")),
                                                           nombreCampo);
                                    if (aplica) {
                                        nombreCampo = "NumeroCtaBenef";
                                        aplica =
                                            aplicaValidaciones(numeroCtaBenef,
                                                               ((String) row.getAttribute("NumeroCtaBenef")),
                                                               nombreCampo);
                                        if (aplica) {
                                            nombreCampo = "BeneficiarioBenef";
                                            aplica =
                                                aplicaValidaciones(beneficiarioBenef,
                                                                   ((String) row.getAttribute("BeneficiarioBenef")),
                                                                   nombreCampo);
                                            if (aplica) {
                                                nombreCampo = "BeneficiarioBanBenef";
                                                aplica =
                                                    aplicaValidaciones(beneficiarioBanBenef,
                                                                       ((String) row.getAttribute("BeneficiarioBanBenef")),
                                                                       nombreCampo);
                                                if (aplica) {
                                                    nombreCampo = "BeneficiarioBanInter";
                                                    aplica =
                                                        aplicaValidaciones(beneficiarioBanInter,
                                                                           ((String) row.getAttribute("BeneficiarioBanInter")),
                                                                           nombreCampo);
                                                    if (aplica) {
                                                        nombreCampo = "TipoOpcionBenef";
                                                        aplica =
                                                            aplicaValidaciones(tipoOpcion,
                                                                               ((String) row.getAttribute("TipoOpcionBenef")),
                                                                               nombreCampo);
                                                        if (aplica) {
                                                            nombreCampo = "TipoOpcionBanBenef";
                                                            aplica =
                                                                aplicaValidaciones(tipoOpcionBan,
                                                                                   ((String) row.getAttribute("TipoOpcionBanBenef")),
                                                                                   nombreCampo);
                                                            if (aplica) {
                                                                nombreCampo = "TipoOpcionBanInter";
                                                                aplica =
                                                                    aplicaValidaciones(tipoOpcionInter,
                                                                                       ((String) row.getAttribute("TipoOpcionBanInter")),
                                                                                       nombreCampo);
                                                                if (aplica) {
                                                                    nombreCampo = "EstadoInstruccion";
                                                                    aplica =
                                                                        aplicaValidaciones(estadoInstruccion,
                                                                                           ((String) row.getAttribute("EstadoInstruccion")),
                                                                                           nombreCampo);
                                                                    if (aplica) {
                                                                        nombreCampo = "NumeroCtaBanBenef";
                                                                        aplica =
                                                                            aplicaValidaciones(numCtabanbenef,
                                                                                               ((String) row.getAttribute("NumeroCtaBanBenef")),
                                                                                               nombreCampo);
                                                                        if (aplica) {
                                                                            nombreCampo = "NumeroCtaBanInter";
                                                                            aplica =
                                                                                aplicaValidaciones(numCtaInter,
                                                                                                   ((String) row.getAttribute("NumeroCtaBanInter")),
                                                                                                   nombreCampo);
                                                                            if (aplica) {
                                                                                nombreCampo = "IdentificadorBenef";
                                                                                aplica =
                                                                                    aplicaValidaciones(idBenef,
                                                                                                       ((String) row.getAttribute("IdentificadorBenef")),
                                                                                                       nombreCampo);
                                                                                if (aplica) {
                                                                                    nombreCampo =
                                                                                        "IdentificadorBanBenef";
                                                                                    aplica =
                                                                                        aplicaValidaciones(idBanBenef,
                                                                                                           ((String) row.getAttribute("IdentificadorBanBenef")),
                                                                                                           nombreCampo);
                                                                                    if (aplica) {
                                                                                        nombreCampo =
                                                                                            "IdentificadorBanInter";
                                                                                        aplica =
                                                                                            aplicaValidaciones(idInterBenef,
                                                                                                               ((String) row.getAttribute("IdentificadorBanInter")),
                                                                                                               nombreCampo);
                                                                                        if (aplica) {
                                                                                            nombreCampo = "DireccionBenef";
                                                                                            aplica =
                                                                                                aplicaValidaciones(dirBenef,
                                                                                                                   ((String) row.getAttribute("DireccionBenef")),
                                                                                                                   nombreCampo);
                                                                                            if (aplica) {
                                                                                                nombreCampo =
                                                                                                    "DireccionBanBenef";
                                                                                                aplica =
                                                                                                    aplicaValidaciones(dirBanBenef,
                                                                                                                       ((String) row.getAttribute("DireccionBanBenef")),
                                                                                                                       nombreCampo);
                                                                                                if (aplica) {
                                                                                                    nombreCampo =
                                                                                                        "DireccionBanInter";
                                                                                                    aplica =
                                                                                                        aplicaValidaciones(dirInterBenef,
                                                                                                                           ((String) row.getAttribute("DireccionBanInter")),
                                                                                                                           nombreCampo);
                                                                                                
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    
                                                                                    }
                                                                                }
                                                                            }

                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    if (aplica) {
                                        logger.warning("Son iguales");
                                    }


                                }
                            }
                        }

                    } else {
                        logger.warning("dato no encontrado consolidado");
                    }
                } else {
                    logger.warning("dato consolidado");
                }
            }
        }

        iq.closeRowSetIterator();
        logger.warning("contador " + contador);
        logger.warning("aplica " + aplica);
        logger.warning("num registros  " + lista.size());

        if (contador.compareTo(2) >= 0) {
            if (aplica) {
                Long idConsolidado = null;
                //metodo de crear consolidacion
                idConsolidado =
                    crearConsolidacion(referencia, consolidarTransferenciasBean.getContrato(), primerTransferencia,
                                       montoTotal);
                if (null != idConsolidado) {
                    Boolean actualiza = Boolean.TRUE;
                    Boolean comitTransferencia = Boolean.FALSE;
                    Boolean aplicaCommit = Boolean.TRUE;
                    for (Long contrato : lista) {
                        //metodo de actualizar las transferencias
                        actualiza = actualizarTransferencias(contrato, idConsolidado);
                        if (null != actualiza) {
                            if (!actualiza) {
                                aplicaCommit = Boolean.FALSE;
                                JSFUtils.addFacesErrorMessage("Error al actualizar las transferencias consolidadas");
                                break;
                            }
                        } else {
                            aplicaCommit = Boolean.FALSE;
                            actualiza = Boolean.FALSE;
                        }
                    }
                    if (aplicaCommit) {
                        comitTransferencia = aplicarCommit();
                    }
                    if (!comitTransferencia) {
                        JSFUtils.addFacesErrorMessage("Error al actualizar las transferencias consolidadas");
                    } else {
                        if (actualizarElConsolidado(idConsolidado, cuenta,
                                                    nombreCuenta)) {
                            // Add event code here...
                            DCIteratorBinding voConsolidadas =
         ADFUtils.getDCBindingContainer().findIteratorBinding("TransferenciasConsolidadasVOIterator");
                            voTransferencias.executeQuery();
                            voConsolidadas.executeQuery();
                        } else {
                            JSFUtils.addFacesErrorMessage("Error al actualizar las transferencias consolidadas");
                        }

                    }
                }
                // metodo de aplicar el commit
            } else {
                JSFUtils.addFacesErrorMessage("Las transferencias seleccionadas no pueden ser consolidadas. Las instrucciones de pago deben ser iguales.");
            }
        } else {
            JSFUtils.addFacesErrorMessage("Se deben seleccionar al menos dos transferencias para poder realizar la consolidación.");
        }
    }

    public Boolean aplicaValidaciones(String validador1, String validador2, String campoValidado) {
        Boolean respuesta = Boolean.TRUE;
        if (null != validador1) {
            if (null != validador2) {
                if (!validador1.equalsIgnoreCase(validador2)) {
                    respuesta = Boolean.FALSE;
                    logger.warning("Son diferentes en el campo: " + campoValidado);
                }
            } else {
                respuesta = Boolean.FALSE;
                logger.warning("Son diferentes en el campo: " + campoValidado);
            }
        } else {
            if (null != validador2) {
                respuesta = Boolean.FALSE;
                logger.warning("Son diferentes en el campo: " + campoValidado);
            }
        }
        return respuesta;
    }

    public void revertirConsolidacion(ActionEvent actionEvent) {
        logger.warning("Inside revertirConsolidacion.");

        //AttributeBinding idPadre;
        //idPadre = ADFUtils.findControlBinding("IdConsolidacionPadre");
        
        AttributeBinding idPadre;
        idPadre = ADFUtils.findControlBinding("IdTransferencia");
        
        AttributeBinding bhqTransferencia;
        bhqTransferencia = ADFUtils.findControlBinding("BhqTransferencia");
        
        if(null != idPadre && null != bhqTransferencia){
            logger.warning("idPadre " + (Long) idPadre.getInputValue());
            logger.warning("bhqTransferencia " + (String) bhqTransferencia.getInputValue());
    
            if (null != (Long) idPadre.getInputValue() && null != (String) bhqTransferencia.getInputValue()) {
                JSFUtils.addFacesErrorMessage("La consolidación ya fue aplicada");
            } else {
                Boolean revierte = Boolean.TRUE;
                revierte = revertirConsolidacion((Long) idPadre.getInputValue());
                if (!revierte) {
                    JSFUtils.addFacesErrorMessage("La consolidación ya fue aplicada");
                } else {
                    DCIteratorBinding voTransferencias = ADFUtils.getDCBindingContainer().findIteratorBinding("TransferenciasConsolidadasVOIterator");
                    DCIteratorBinding voConsolidadas = ADFUtils.getDCBindingContainer().findIteratorBinding("TransferenciasConsolidarVOIterator");
                    voTransferencias.executeQuery();
                    voConsolidadas.executeQuery();
                }
            }
        }
    }
    
    public Long crearConsolidacion(String cadenaId, Long contrato, Long  primerTransferencia, 
                                   BigDecimal montoTotal) {
        logger.warning("Inside crearConsolidacion.");
        
        Long resultado = null;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("consolidarTransferencias");
        operationBinding.getParamsMap().put("cadenaId", cadenaId);
        operationBinding.getParamsMap().put("contrato", contrato);
        operationBinding.getParamsMap().put("primerTransferencia", primerTransferencia);
        operationBinding.getParamsMap().put("montoTotal", montoTotal);
        resultado = (Long) operationBinding.execute();

        logger.warning("resultado= " + resultado);
        logger.warning("resultado2= " + operationBinding.getResult());
        
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return null;
        }
        
        return resultado;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public Boolean revertirConsolidacion(Long idConsolidacionPadre) {
        logger.warning("Inside revertirConsolidacion.");
        
        Boolean resultado = Boolean.TRUE;
        
        if (idConsolidacionPadre != null) {    
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("revertirConsolidaciones");
            operationBinding.getParamsMap().put("transferenciaConsolidado", idConsolidacionPadre);
            resultado = (Boolean) operationBinding.execute();

            logger.warning("resultado= " + resultado);
            logger.warning("resultado2= " + operationBinding.getResult());
            
            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("Error");
                return false;
            }    
        } else {
            logger.warning("idConsolidacionPadre es Null.");
        }
        
        return resultado;
    }
    
    public Boolean actualizarTransferencias(Long transferencia, Long consolidado) {
        logger.warning("Inside actualizarTransferencias.");
        
        Boolean resultado = Boolean.TRUE;
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("actualizarTransferencias");
        operationBinding.getParamsMap().put("transferencia", transferencia);
        operationBinding.getParamsMap().put("idConsolidado", consolidado);
        resultado = (Boolean) operationBinding.execute();

        logger.warning("resultado= " + resultado);
        logger.warning("resultado2= " + operationBinding.getResult());
        
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return false;
        }
        
        return resultado;
    }
    
    
    public Boolean actualizarElConsolidado(Long transferencia, String cuenta, String nombreCuenta) {
        logger.warning("Inside actualizarTransferencias consolidado.");
        
        Boolean resultado = Boolean.TRUE;
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("actualizarConsolidado");
        operationBinding.getParamsMap().put("transferencia", transferencia);
        operationBinding.getParamsMap().put("cuenta", cuenta);
        operationBinding.getParamsMap().put("nombreCuenta", nombreCuenta);
        resultado = (Boolean) operationBinding.execute();

        logger.warning("resultado= " + resultado);
        logger.warning("resultado2= " + operationBinding.getResult());
        
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return false;
        }
        
        return resultado;
    }
    
    public Boolean aplicarCommit() {
        logger.warning("Inside aplicarCommit.");
            
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("commitConsolidarTransferencia");
        resultado = (Boolean) operationBinding.execute();

        logger.warning("resultado= " + resultado);
        logger.warning("resultado2= " + operationBinding.getResult());
        
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return false;
        }
        
        return resultado;
    }

    public void aplicarConsolidacion(ActionEvent actionEvent) {
        logger.warning("Inside aplicarConsolidacion.");
        ConsolidarTransferenciasBean consolidarTransferenciasBean =
            (ConsolidarTransferenciasBean) JSFUtils.resolveExpression("#{pageFlowScope.consolidarTransferenciasBean}");
        AttributeBinding bhqTransferencia;
        bhqTransferencia = ADFUtils.findControlBinding("BhqTransferencia1");


        AttributeBinding idTransferencia;
        idTransferencia = ADFUtils.findControlBinding("IdTransferencia");

        if (null ==  bhqTransferencia.getInputValue()) {
            if (null != idTransferencia.getInputValue()) {
                Boolean aplica = Boolean.TRUE;
                aplica = aplicarConsolacion((Long) idTransferencia.getInputValue(), 
                                            consolidarTransferenciasBean.getUsuario(), 
                                            consolidarTransferenciasBean.getInstanciaDProceso());
                DCIteratorBinding voTransferencias = 
                    ADFUtils.getDCBindingContainer().findIteratorBinding("TransferenciasConsolidadasVOIterator");
                DCIteratorBinding voConsolidadas = 
                    ADFUtils.getDCBindingContainer().findIteratorBinding("TransferenciasConsolidarVOIterator");
                voTransferencias.executeQuery();
                voConsolidadas.executeQuery();
                if (aplica) {
                logger.warning("Se ejecuta metodo de manera correcta");
                } else {
                    JSFUtils.addFacesErrorMessage("Error al consolidar intente más tarde");
                }
                
                
            } else {
                JSFUtils.addFacesErrorMessage("Error al obtener el registro de la consolidada intente denuevo");
            }
        } else {
            JSFUtils.addFacesErrorMessage("La consolidación ya fue aplicada");
        }

    }
    
    public Boolean aplicarConsolacion(Long consolidado, String usuario, String instancia) {
        logger.warning("Inside aplicarConsolacion.");
        logger.warning("ID transferencia: " + consolidado);
        logger.warning("USUARIO: " + usuario);
        logger.warning("instancia: " + instancia);

        Boolean resultado = Boolean.TRUE;
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        
        try{    
            operationBinding =  bindings.getOperationBinding("aplicarConsolidacion");
            operationBinding.getParamsMap().put("consolidado", consolidado);
            operationBinding.getParamsMap().put("usuario", usuario); 
            operationBinding.getParamsMap().put("instanciaProceso", instancia); 
            operationBinding.execute();
        }catch(Exception e){
            logger.warning("Error al aplicar la Consolidacion!");
        }
     
        
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error el el metodo de aplicar consolidacion "+ operationBinding.getErrors());
            JSFUtils.addFacesErrorMessage("Error al consolidar: "+operationBinding.getErrors());
            resultado = Boolean.FALSE;            
        }else{
                resultado = (Boolean) operationBinding.getResult();
            }
        
        logger.warning("resultado= " + resultado);
        logger.warning("resultado2= " + operationBinding.getResult());
        return resultado;
    }
}
