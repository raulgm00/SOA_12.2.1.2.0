package org.bcie.fenix.view.gestioncomisionprepago;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class GestionComisionActionBean extends FenixActionBean{
    private static ADFLogger logger = null;
    public static final String BUNDLE = "pa12prepagoght.PA12PrepagoGHTBundle";
    public static final Integer MONTO_SOLICITADO = 2;
    public static final Integer MONTO_APROBADO = 3;
    public static final Integer MONTO_FORMALIZADO = 5;
    Boolean isValidar = Boolean.FALSE;
    private RichInputDate uiFechaInicioCapital;
    private RichInputDate uiFechaVencimiento;
    private RichInputDate uiFechaVigencia;

    public void setIsValidar(Boolean isValidar) {
        this.isValidar = isValidar;
    }

    public Boolean getIsValidar() {
        return isValidar;
    }

    /**
     * Define el ID de TCA Control Condicion para Calendario
     */
    public static final Integer ID_CONTROL_CONDICION_CALENDARIO = 2;
    
    /**
     * Define el ID de Tipo Fecha de Inicio para Fecha Especifica
     */
    public static final Integer ID_TIPO_FECHA_INICIO_FECHA_ESPECIFICA = 7;
    
    /**
     * Define si el tipo frecuencia en comision es al VENCIMIENTO
     */
    public static final Integer TIPO_VENCIMIENTO = 4;
    
    /**
     * Define si el tipo VALOR  en comision es el MONTO
     * */
    public static final Integer TIPO_VALOR_MONTO = 1;
    private RichPopup popupGuardarComision;
    private RichPopup popupActualizarComision;

    public GestionComisionActionBean() {
        super();
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void cargarPantallaComision(){
        logger.log(ADFLogger.WARNING, "INTO cargarPantallaComision en Gestionar Comision Action Bean.......");
        GestionComisionBean gestionComisionBean = (GestionComisionBean)JSFUtils.resolveExpression("#{pageFlowScope.gestionComisionBean}");
        OperationBinding oper = null;
        
        logger.log(ADFLogger.WARNING, "Valor de Id Comision en Inicializar Busqueda de comision......." + gestionComisionBean.getIdCom());

            Map params = null;
              params = new HashMap();
              params.put("", 1);
                
              oper = null;
              oper = executeOperBinding(null, "asignarConfigTipoComision");
              if(oper != null &&
                 !oper.getErrors().isEmpty()){
                  logger.severe("Error al obtener y asignar configuracion del catalogo de tipo comision");
              }
              
              oper = executeOperBinding(params, "obtenerConfigucacionAtributosTcc");
               
              if(oper.getErrors().isEmpty())
              {
                  Map<String,Map> configuracionAtributosTCCMap = (Map<String,Map>) oper.getResult();
                  
                  if(null!=gestionComisionBean)
                  {
                    gestionComisionBean.setAtributeValues(configuracionAtributosTCCMap);
                  }
              }
              else
              {
                String msg = "Error al obtener configuracion de atributos TCC ";
                logger.severe(msg);
                JSFUtils.addFacesErrorMessage(msg);
              }
    }
    
    public void crearComisionPrepago() {
        logger.log(ADFLogger.WARNING, "INTO crearComisionPrepago");
        
        GestionComisionBean gestionComisionBean = (GestionComisionBean)JSFUtils.resolveExpression("#{pageFlowScope.gestionComisionBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding oper = null;
        Long idOperacion = null;
        
        try{
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")){
                idOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
                logger.log(ADFLogger.WARNING, "Valor de Id idOperacion.." + idOperacion);
            }else{
                logger.log(ADFLogger.WARNING, "Valor de Id idOperacion es nulo.");
            }
            oper = bindings.getOperationBinding("crearRowComision");
            oper.getParamsMap().put("idOperacion", idOperacion);
            oper.execute();
            
            oper = null;

                Map params = null;
                  params = new HashMap();
                  params.put("", 1);
                    
                  oper = null;
                  oper = executeOperBinding(null, "asignarConfigTipoComision");
                  if(oper != null &&
                     !oper.getErrors().isEmpty()){
                      logger.severe("Error al obtener y asignar configuracion del catalogo de tipo comision");
                  }
                  
                  oper = executeOperBinding(params, "obtenerConfigucacionAtributosTcc");
                   
                  if(oper.getErrors().isEmpty())
                  {
                      Map<String,Map> configuracionAtributosTCCMap = (Map<String,Map>) oper.getResult();
                      
                      if(null!=gestionComisionBean)
                      {
                        gestionComisionBean.setAtributeValues(configuracionAtributosTCCMap);
                      }
                  }
                  else
                  {
                    String msg = "Error al obtener configuracion de atributos TCC ";
                    logger.severe(msg);
                    JSFUtils.addFacesErrorMessage(msg);
                  }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en crearComisionPrepago." + e.getClass() + "." + e.getMessage());
        }
        logger.log(ADFLogger.ERROR, "Valor is Comision." + gestionComisionBean.getIsComision());
    }
    private OperationBinding executeOperBinding(Map params,
                                                String operBindName){
        
        logger.entering(GestionComisionActionBean.class.getName(),
                        "executeOperBinding",
                        new Object[]{params, operBindName});
        
        BindingContainer bindings = getBindings();
        OperationBinding oper = null;
        oper = bindings.getOperationBinding(operBindName);
        if (null != oper) {
            
            if(params != null){
                oper.getParamsMap().putAll(params);    
            }
            try {
                logger.warning("Ejecuta metodo ".concat(operBindName));
                oper.execute();
            } catch (Exception e) {
                logger.severe("Error al ejecutar Operator Bindings: " + operBindName, e);                
            }

            if (!oper.getErrors().isEmpty()) {
                logger.severe("Operator Bindings devuelve errores: " + operBindName);
            }else{
                logger.warning("Operator Bindings se ejecuto correctamente");
            }
        }else{
            logger.severe("Error el operator Binding " + operBindName + 
                          " no fue encontrado");
        }
        
        logger.exiting(GestionComisionActionBean.class.getName(), 
                       "executeOperBinding",
                       oper);
        return oper;
    }
    
    ///Se replican metodos del Action bean 
    
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
    
    /**
    * Method for calculate comision amount
    * @return
    */
    private BigDecimal calculaComision() {
    logger.warning("calculaMontoComision()");

    AttributeBinding montoBase = ADFUtils.findControlBinding("MontoBase");
    AttributeBinding porcentajeBase= ADFUtils.findControlBinding("PorcentajeSobreMontoBase");

    logger.warning("Monto base: "+ montoBase.getInputValue());
    logger.warning("Porcentaje base: "+porcentajeBase.getInputValue());

    BigDecimal montoComision = new BigDecimal(0);
    BigDecimal porcentaje = new BigDecimal("100");
    if(null != porcentajeBase.getInputValue() && !"".equalsIgnoreCase(porcentajeBase.getInputValue().toString())){
        BigDecimal porcentajeComision = (BigDecimal)porcentajeBase.getInputValue();
    if(porcentajeComision.doubleValue() >0 && porcentajeComision.doubleValue()<100L) {
        if(null != montoBase && null != montoBase.getInputValue()) {
            BigDecimal monto = (BigDecimal)montoBase.getInputValue();
            if(monto.doubleValue() >0.00) {
                logger.warning("porcentaje:"+ porcentajeComision);
                monto = monto.multiply(porcentajeComision);
                montoComision = monto.divide(porcentaje);
            }
        }
    }
    }
    logger.warning("Monto comision calculado: " + montoComision);
    return montoComision.setScale(2, RoundingMode.CEILING);
    }
    
    public BigDecimal obtenerMontoBase(Integer tipoMonto){
            BigDecimal resultado = BigDecimal.ZERO;
                
        try{
            resultado= BigDecimal.ZERO;
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("obtenerMontoPorTipo");
            operationBinding.getParamsMap().put("varTipoMonto",tipoMonto);
            operationBinding.execute();
            resultado=(BigDecimal)operationBinding.getResult();
            //guarda el monto base
                              
            if(!operationBinding.getErrors().isEmpty()){
            resultado=BigDecimal.ZERO;
            }
            logger.warning("Monto obtenido:" +resultado);
            }catch(Exception e){
                logger.log(ADFLogger.WARNING, "Error al obtener la operacion" + e.getClass() + "." + e.getMessage());
            }
            return resultado;
        }
    
    //Metodos Value change Listener
    
    public void cambioTasa(ValueChangeEvent vce) {
        logger.log(ADFLogger.WARNING, "INTO cambioTasa.");
         AttributeBinding montoBase;
         montoBase = ADFUtils.findControlBinding("MontoBase");
         AttributeBinding montoComision;
         montoComision = ADFUtils.findControlBinding("MontoComision");
         AttributeBinding idMontoBase;
         idMontoBase = ADFUtils.findControlBinding("IdTcaMontoBase");
         AttributeBinding porcentajeBase;
         porcentajeBase = ADFUtils.findControlBinding("PorcentajeSobreMontoBase");

         Integer valorNulo = null;
         montoBase.setInputValue(null);
         porcentajeBase.setInputValue(null);
         montoComision.setInputValue(null);
         idMontoBase.setInputValue(valorNulo);
         
         Integer valorDefecto=1;
         Integer tipoTasa=1;
         Integer valorTipoTasa=(Integer)vce.getNewValue();
         AttributeBinding tasa;
         tasa = ADFUtils.findControlBinding("IdTcaTipoTasa");
         if(valorTipoTasa.compareTo(tipoTasa)==0){
             logger.log(ADFLogger.WARNING, "Entra en monto fijo." + valorTipoTasa);
                 porcentajeBase.setInputValue(null);
                 montoBase.setInputValue(null);
                 tasa.setInputValue(valorDefecto);
                 montoComision.setInputValue(null);
             }
         else{
              logger.log(ADFLogger.WARNING, "Valor por defecto");
                 porcentajeBase.setInputValue(null);
                 idMontoBase.setInputValue(valorNulo);
                 montoBase.setInputValue(null);
             }
     }
    
    public Boolean getEsTipoValor() {
    Boolean respuesta = true;
    AttributeBinding tipoValorTasa;
    tipoValorTasa = ADFUtils.findControlBinding("IdTcaTipoTasa");
    Integer value = 0;
    if(null != tipoValorTasa.getInputValue()) {
    value = (Integer)tipoValorTasa.getInputValue();
    if(TIPO_VALOR_MONTO.equals(value)) {
       respuesta = false;
    }
    }
    return respuesta;
    }
    
    public void cambioValorTasa(ValueChangeEvent vce) {
            logger.warning("Porcentaje: "+ vce.getNewValue());
            AttributeBinding montoComision = ADFUtils.findControlBinding("MontoComision");
            if(null != vce && null != vce.getNewValue()) {
                BigDecimal nuevoValor = (BigDecimal)vce.getNewValue();
                if(nuevoValor.doubleValue() <= 0 || nuevoValor.doubleValue() >100) {
                    montoComision.setInputValue(BigDecimal.ZERO);
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_MSG_PORCENTAJE"));
                } else {
                    //preparacion.setPorcentajeBase(nuevoValor);
                    AttributeBinding porcentajeBase;
                    porcentajeBase = ADFUtils.findControlBinding("PorcentajeSobreMontoBase");
                    porcentajeBase.setInputValue(nuevoValor);
                }
            }
            montoComision.setInputValue(calculaComision());
        }
    
    public void cambioMontoBase(ValueChangeEvent vce) {

        logger.log(ADFLogger.WARNING, "INTO cambioMontoBase");
        vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Integer monedaDefault = 1;
        Integer idMoneda;
        BigDecimal montoBase = null;
        Integer idMontoBaseComision = null;
        BigDecimal porcentajeBase = null;
        BigDecimal montoComisionPay = null;
        try {
            if (null != ADFUtils.getBoundAttributeValue("IdTcaMoneda")) {
                ADFUtils.setBoundAttributeValue("IdTcaMoneda", monedaDefault);
            } else {
                logger.log(ADFLogger.WARNING, "Valor de idMoneda es nulo.");
            }
            if (null != ADFUtils.getBoundAttributeValue("IdTcaMontoBase")) {
                idMontoBaseComision = (Integer) ADFUtils.getBoundAttributeValue("IdTcaMontoBase");
            } else {
                logger.log(ADFLogger.WARNING, "El valor de IdTcaMontoBase es nulo.");
            }
            if (null != ADFUtils.getBoundAttributeValue("MontoBase")) {
                montoBase = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoBase");
            } else {
                logger.log(ADFLogger.WARNING, "Valor de MontoBase es nulo.");
            }
            if (null != ADFUtils.getBoundAttributeValue("PorcentajeSobreMontoBase")) {
                porcentajeBase = (BigDecimal) ADFUtils.getBoundAttributeValue("PorcentajeSobreMontoBase");
            } else {
                logger.log(ADFLogger.WARNING, "El valor de PorcentajeSobreMontoBase es nulo.");
            }
            if (null != ADFUtils.getBoundAttributeValue("MontoComision")) {
                montoComisionPay = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoComision");
            } else {
                logger.log(ADFLogger.WARNING, "El valor de MontoComision es nulo.");
            }
            BigDecimal porcentajeComision = BigDecimal.ZERO;
            BigDecimal porcentaje = new BigDecimal("100");
            int valorMax = 1;
            int valorMin = -1;
            if (null != porcentajeBase && !"".equalsIgnoreCase(porcentajeBase.toString())) {
                porcentajeComision = porcentajeBase;
                valorMax = porcentajeComision.compareTo(porcentaje);
                valorMin = porcentajeComision.compareTo(BigDecimal.ZERO);
            }
            logger.warning("Done");
            BigDecimal montoBaseD = BigDecimal.ZERO;
            BigDecimal montoComision;
            logger.warning("ID monto " + vce.getNewValue());
            Integer idMontoBase = (Integer) vce.getNewValue();
            ADFUtils.setBoundAttributeValue("IdTcaMontoBase", idMontoBase);
            switch (idMontoBase) {
            case 1:
                logger.warning("Monto Solicitado");

                montoBaseD = obtenerMontoBase(MONTO_SOLICITADO);
                ADFUtils.setBoundAttributeValue("MontoBase", montoBaseD);
                if (valorMax <= 0 && valorMin >= 0) {
                    montoComisionPay = calculaComision();
                    ADFUtils.setBoundAttributeValue("MontoComision", montoComisionPay);
                } else {
                    montoComisionPay = BigDecimal.ZERO;
                    ADFUtils.setBoundAttributeValue("MontoComision", montoComisionPay);
                }
                if (null == montoBaseD || montoBaseD.compareTo(BigDecimal.ZERO) == 0) {
                    // JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_CONFIRMA_NO_MONTO"));
                    if (null == montoBaseD) {
                        montoBaseD = BigDecimal.ZERO;
                    }
                    ADFUtils.setBoundAttributeValue("MontoBase", montoBaseD);
                    montoComisionPay = calculaComision();
                    ADFUtils.setBoundAttributeValue("MontoComision", montoComisionPay);
                }
                break;
            case 2:
                logger.warning("Monto Aprobado");
                montoBaseD = obtenerMontoBase(MONTO_APROBADO);
                ADFUtils.setBoundAttributeValue("MontoBase", montoBaseD);
                if (valorMax <= 0 && valorMin >= 0) {
                    montoComisionPay = calculaComision();
                    ADFUtils.setBoundAttributeValue("MontoComision", montoComisionPay);
                } else {
                    montoComisionPay = BigDecimal.ZERO;
                    ADFUtils.setBoundAttributeValue("MontoComision", montoComisionPay);
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_MSG_PORCENTAJE"));
                }
                if (null == montoBaseD || montoBaseD.compareTo(BigDecimal.ZERO) == 0) {
                    // JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_CONFIRMA_NO_MONTO"));
                    if (null == montoBaseD) {
                        montoBaseD = BigDecimal.ZERO;
                    }
                    ADFUtils.setBoundAttributeValue("MontoBase", montoBaseD);
                    montoComisionPay = calculaComision();
                    ADFUtils.setBoundAttributeValue("MontoComision", montoComisionPay);
                }
                break;
            case 3:
                logger.warning("Monto Formalizado");
                montoBaseD = obtenerMontoBase(MONTO_FORMALIZADO);
                ADFUtils.setBoundAttributeValue("MontoBase", montoBaseD);
                if (valorMax <= 0 && valorMin >= 0) {
                    montoComisionPay = calculaComision();
                    ADFUtils.setBoundAttributeValue("MontoComision", montoComisionPay);
                } else {
                    montoComisionPay = BigDecimal.ZERO;
                    ADFUtils.setBoundAttributeValue("MontoComision", montoComisionPay);
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_MSG_PORCENTAJE"));
                }
                if (null == montoBaseD || montoBaseD.compareTo(BigDecimal.ZERO) == 0) {
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_CONFIRMA_NO_MONTO"));
                    if (null == montoBaseD) {
                        montoBaseD = BigDecimal.ZERO;
                    }
                    ADFUtils.setBoundAttributeValue("MontoBase", montoBaseD);
                    montoComisionPay = calculaComision();
                    ADFUtils.setBoundAttributeValue("MontoComision", montoComisionPay);
                }
                break;
            case 4:
                logger.log(ADFLogger.WARNING, "Saldo de la linea.");
                break;
            case 5:
                logger.log(ADFLogger.WARNING, "Total sindicado.");
                break;
            default:
                //JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_CONFIRMA_NO_MONTO"));
                ADFUtils.setBoundAttributeValue("MontoBase", BigDecimal.ZERO);
                montoComisionPay = calculaComision();
                ADFUtils.setBoundAttributeValue("MontoComision", montoComisionPay);
                break;
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en cambioMontoBase." + e.getClass() + "." + e.getMessage());
        }
    }
    
    public void cambioFrecuencia(ValueChangeEvent vce) {
        Integer valorDefecto=1;
        Integer frecuenciaVencimiento=4;
        Integer valorTipoFrecuencia=(Integer)vce.getNewValue();
        AttributeBinding frecuencia;
        frecuencia = ADFUtils.findControlBinding("FrecuenciaPago");
        if(valorTipoFrecuencia.compareTo(frecuenciaVencimiento)==0){
                frecuencia.setInputValue(valorDefecto);
            }
        else{
                frecuencia.setInputValue(null);
            }

    }

    public void setPopupGuardarComision(RichPopup popupGuardarComision) {
        this.popupGuardarComision = popupGuardarComision;
    }

    public RichPopup getPopupGuardarComision() {
        return popupGuardarComision;
    }

    public void aceptarCrearComision(ActionEvent actionEvent) {
        // Add event code here...
    }

    public String agregarComisionAction() {
        logger.log(ADFLogger.WARNING, "INTO aceptarCrearComisionPopup.");
        GestionComisionBean gestionComisionBean = (GestionComisionBean)JSFUtils.resolveExpression("#{pageFlowScope.gestionComisionBean}");
        Map<String,Object> atributosComisionMap = new HashMap<String,Object>();
        List<String> bundleKeyErrorList = new ArrayList<String>();
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long comision = null;
        Long idPrepago = null;
        Boolean isValidaFechas = Boolean.FALSE;
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}") != null){
            idPrepago = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}").toString());
            }else{
                logger.log(ADFLogger.WARNING, "Objeto pageFlowScope.pIdPrepago Nulo.");
            }
            logger.log(ADFLogger.WARNING, "Valor del prepago." + idPrepago);
            logger.log(ADFLogger.WARNING, "Valor de la comision." + gestionComisionBean.getIdCom());
            if(null != gestionComisionBean.getIdCom()){
                logger.log(ADFLogger.WARNING, "Valor de la comision que se va a actualizar." + gestionComisionBean.getIdCom());
                actualizarComisionPrepagoActionBean(gestionComisionBean.getIdCom());
                resetValueDateComponent(uiFechaVigencia);
                resetValueDateComponent(uiFechaInicioCapital);
            }else{
                logger.log(ADFLogger.WARNING, "Se agrega una nueva comision.");
            operationBinding = bindings.getOperationBinding("agregarComisionPrepago");
            operationBinding.execute();

                if (null != operationBinding.getResult()) {
                    atributosComisionMap = (Map) operationBinding.getResult();

                    if (null != atributosComisionMap.get("idComision")) {
                        comision = (Long) atributosComisionMap.get("idComision");
                        gestionComisionBean.setIdCom(comision);
                    } else {
                        logger.log(ADFLogger.WARNING, "Valor id de la Comision es nulo.");
                    }
                    if (null != atributosComisionMap.get("isInformacionRequerida")) {
                        isValidar = (Boolean) atributosComisionMap.get("isInformacionRequerida");
                    } else {
                        logger.log(ADFLogger.WARNING, "Valor de validacion de Commit." + isValidar);
                    }
                    if (null != atributosComisionMap.get("isValidaFechas")) {
                        isValidaFechas = (Boolean) atributosComisionMap.get("isValidaFechas");
                    } else {
                        logger.log(ADFLogger.WARNING, "Valor de validacion de Commit." + isValidar);
                    }
                    if (isValidar && isValidaFechas) {

                        gestionComisionBean.setIsComision(Boolean.TRUE);
                        resetValueDateComponent(uiFechaVigencia);
                        resetValueDateComponent(uiFechaInicioCapital);

                        operationBinding = null;
                        operationBinding = bindings.getOperationBinding("insertarRowCargoPrepago");
                        operationBinding.getParamsMap().put("idPrepago", idPrepago);
                        operationBinding.getParamsMap().put("idComision", comision);
                        operationBinding.execute();

                    } else {
                        bundleKeyErrorList.add("MSG_COMISION_PREPAGO_FAVOR_INGRESAR_INFORMACION_REQUERIDA");
//                        FacesMessage msg =
//                            new FacesMessage(FacesMessage.SEVERITY_INFO, null,
//                                             "Favor de ingresar la informacion requerida.");
//                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        JSFUtils.addFacesErrorMessage("MSG_COMISION_PREPAGO_FAVOR_INGRESAR_INFORMACION_REQUERIDA");
                        if(!isValidaFechas){
                            JSFUtils.addFacesErrorMessage("MSG_FECHA_INICIO_CAPITAL_MAYOR_O_IGUAL_QUE_FECHA_VIGENCIA_Y_MENOR_O_IGUAL_QUE_FECHA_VENCIMIENTO");
                            JSFUtils.addFacesErrorMessage("MSG_FECHA_VIGENCIA_MENOR_O_IGUAL_QUE_FECHA_INICIO_CAPITAL_Y_MENOR_QUE_FECHA_VENCIMIENTO");
                        }

                    }
                } else {
                    logger.log(ADFLogger.WARNING, "Error al crear la comision.");
                }
            }
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error en aceptarCrearComisionPopup." + e.getClass() + "." + e.getMessage());
        }
        logger.log(ADFLogger.WARNING, "Valor Boolean ::" + isValidar);
        return null;
    }
    
    public Boolean actualizarComisionPrepagoActionBean(Long idComision){
        logger.log(ADFLogger.WARNING, "Entra en actualizarComisionPrepagoActionBean.");
        Boolean isValidaDatos = Boolean.FALSE;
        List<String> bundleKeyErrorList = new ArrayList<String>();
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try{
            operationBinding = bindings.getOperationBinding("actualizarComisionPrepago");
            operationBinding.getParamsMap().put("idComision", idComision);
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                isValidaDatos = (Boolean)operationBinding.getResult();
                if(isValidaDatos){
                    JSFUtils.addFacesInformationMessage("La comision se ha actualizado correctamente");
                }else{
                    bundleKeyErrorList.add("MSG_COMISION_PREPAGO_FAVOR_INGRESAR_INFORMACION_REQUERIDA");
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Favor de ingresar la informacion requerida.");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }else{
                logger.log("Valor de retorno es nulo.");
            }
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error al actualizar la comision." + e.getClass() + "." + e);
        }
        return isValidaDatos;
    }

    public void cancelarCrearComisionPopup(ActionEvent actionEvent) {
        // Add event code here...
    }

//    public String agregarComisionAction() {
//        RichPopup.PopupHints hints = new RichPopup.PopupHints();
//        popupGuardarComision.show(hints);
//        return null;
//    }

    public String actualizarComisionAction() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupActualizarComision.show(hints);
        return null;
    }

    public void setPopupActualizarComision(RichPopup popupActualizarComision) {
        this.popupActualizarComision = popupActualizarComision;
    }

    public RichPopup getPopupActualizarComision() {
        return popupActualizarComision;
    }

    public void cancelarActualizarComisionPopup(ActionEvent actionEvent) {
        popupActualizarComision.hide();
    }

    public String aceptarActualizarComision() {
        popupActualizarComision.hide();
        return null;
    }

    public void validarFechaVancimiento(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "INTO validarFechaVancimiento.");
        Date fechaVencimiento = null;
        AttributeBinding flagFechaVencimiento = null;
        Date fechaVigencia = null;
        Date fechaInicioCapital = null;
        Boolean fecha1 = Boolean.TRUE;
        Boolean fecha2 = Boolean.TRUE;

        try {
            if (null != valueChangeEvent.getNewValue()) {
                fechaVencimiento = (Date) valueChangeEvent.getNewValue();
                logger.log(ADFLogger.WARNING, "Valor FechaVencimiento :" + fechaVencimiento);
            } else {
                logger.log(ADFLogger.WARNING, "Valor FechaVencimiento es nulo:");
            }
            if (null != ADFUtils.findControlBinding("FechaValor")) {
                fechaVigencia = (Date) ADFUtils.findControlBinding("FechaValor").getInputValue();
                logger.log(ADFLogger.WARNING, "Valor FechaValor :" + fechaVigencia);
            } else {
                logger.log(ADFLogger.WARNING, "Valor FechaValor es nulo:");
            }
            if (null != ADFUtils.findControlBinding("FechaInicioCapital")) {
                fechaInicioCapital = (Date) ADFUtils.findControlBinding("FechaInicioCapital").getInputValue();
                logger.log(ADFLogger.WARNING, "Valor FechaInicioCapital :" + fechaInicioCapital);
            } else {
                logger.log(ADFLogger.WARNING, "Valor FechaInicioCapital es nulo:");
            }
            if (null != fechaVencimiento) {
                if (null != fechaInicioCapital) {
                    if (fechaVencimiento.compareTo(fechaInicioCapital) >= 0) {
                        logger.log(ADFLogger.WARNING,
                                   "El valor de la fecha vencimiento es mayor que o igual fechaInicioCapital.");
                    } else {
                        flagFechaVencimiento = ADFUtils.findControlBinding("FechaVencimiento");
                        flagFechaVencimiento.setInputValue(null);
                        resetValueDateComponent(uiFechaVencimiento);
                        fecha1 = Boolean.FALSE;
                    }
                } else {
                    logger.log(ADFLogger.WARNING, "La fecha inicio capital es nula " + fechaInicioCapital);
                }
                if (null != fechaVigencia) {
                    if (fechaVencimiento.compareTo(fechaVigencia) > 0) {
                        logger.log(ADFLogger.WARNING,
                                   "El valor de la fecha vencimiento es mayor que o igual fechaInicioCapital.");
                    } else {
                        flagFechaVencimiento = ADFUtils.findControlBinding("FechaVencimiento");
                        flagFechaVencimiento.setInputValue(null);
                        resetValueDateComponent(uiFechaVencimiento);
                        fecha2 = Boolean.FALSE;
                    }
                } else {
                    logger.log(ADFLogger.WARNING, "La fecha de vigencia es nula." + fechaVigencia);
                }
            } else {
                logger.log(ADFLogger.WARNING, "La fecha de venciiento es nula.");
            }
            if (!fecha1 || !fecha2) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FECHA_DE_VENCIMIENTO_MAYOR_A_FECHA_DE_VIGENCIA"));
            } else {
                logger.log(ADFLogger.WARNING, "La validacion es correcta" + fecha1 + "--" + fecha2);
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en validarFechaVancimiento." + e.getClass() + "." + e.getMessage());
        }
    }
    
    public void resetValueDateComponent(RichInputDate uiValue) {
        logger.warning("inside resetValueDateComponent.");
        
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        uiValue.resetValue();
        adfFacesContext.addPartialTarget(uiValue);
    }

    public void setuiFechaInicioCapital(RichInputDate uiFechaInicioCapital) {
        this.uiFechaInicioCapital= uiFechaInicioCapital;
    }

    public RichInputDate getuiFechaInicioCapital() {
        return uiFechaInicioCapital;
    }

    public void validarFechaInicioCapital(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "INTO validarFechaInicioCapital.");
        Date fechaVencimiento = null;
        Date fechaVigencia = null;
        Date fechaInicioCapital = null;
        AttributeBinding flagfechaInicioCapital = null;

        Boolean fecha1 = Boolean.TRUE;
        Boolean fecha2 = Boolean.TRUE;
        try {
            if (null != valueChangeEvent.getNewValue()) {
                fechaInicioCapital = (Date) valueChangeEvent.getNewValue();
                logger.log(ADFLogger.WARNING, "Valor FechaInicioCapital :" + fechaInicioCapital);
            } else {
                logger.log(ADFLogger.WARNING, "Valor FechaInicioCapital es nulo:");
            }
            if (null != ADFUtils.findControlBinding("FechaValor")) {
                fechaVigencia = (Date) ADFUtils.findControlBinding("FechaValor").getInputValue();
                ;
                logger.log(ADFLogger.WARNING, "Valor FechaValor :" + fechaVigencia);
            } else {
                logger.log(ADFLogger.WARNING, "Valor FechaValor es nulo:");
            }
            if (null != ADFUtils.findControlBinding("FechaVencimiento")) {
                fechaVencimiento = (Date) ADFUtils.findControlBinding("FechaVencimiento").getInputValue();
                logger.log(ADFLogger.WARNING, "Valor FechaVencimiento :" + fechaVencimiento);
            } else {
                logger.log(ADFLogger.WARNING, "Valor FechaVencimiento es nulo:");
            }
            if (null != fechaInicioCapital) {
                if (null != fechaVencimiento) {
                    if (fechaInicioCapital.compareTo(fechaVencimiento) <= 0) {
                        logger.log(ADFLogger.WARNING,
                                   "La fecha inicio de capital debe ser mayor o igual que la fecha de vigencia y menor o igual que la fecha de vencimiento.");
                    } else {
                        flagfechaInicioCapital = ADFUtils.findControlBinding("FechaInicioCapital");
                        flagfechaInicioCapital.setInputValue(null);
                        resetValueDateComponent(uiFechaInicioCapital);
                        fecha1 = Boolean.FALSE;
                    }
                } else {
                    logger.log(ADFLogger.WARNING, "La fecha de vencimiento es nula." + fechaVencimiento);
                }

                if (null != fechaVigencia) {
                    if (fechaInicioCapital.compareTo(fechaVigencia) >= 0) {
                        logger.log(ADFLogger.WARNING,
                                   "La fecha inicio de capital debe ser mayor o igual que la fecha de vigencia y menor o igual que la fecha de vencimiento.");
                    } else {
                        flagfechaInicioCapital = ADFUtils.findControlBinding("FechaInicioCapital");
                        flagfechaInicioCapital.setInputValue(null);
                        resetValueDateComponent(uiFechaInicioCapital);
                        fecha2 = Boolean.FALSE;
                    }
                } else {
                    logger.log(ADFLogger.WARNING, "La fecha de vigencia es nula :" + fechaVigencia);
                }
            } else {
                logger.log(ADFLogger.WARNING, "La fecha de inicio de capital es nula.");
            }
            if (!fecha1 || !fecha2) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FECHA_INICIO_CAPITAL_MAYOR_O_IGUAL_QUE_FECHA_VIGENCIA_Y_MENOR_O_IGUAL_QUE_FECHA_VENCIMIENTO"));
            } else {
                logger.log(ADFLogger.WARNING, "Validacion de fechas correcta.");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en validarFechaInicioCapital." + e.getClass() + "." + e.getMessage());
        }
    }

    public void validarFechaVigencia(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "INTO validarFechaVigencia.");
        Date fechaVencimiento = null;
        Date fechaVigencia = null;
        AttributeBinding flagfechaVigencia = null;
        Date fechaInicioCapital = null;
        Boolean isFecha1 = Boolean.TRUE;
        Boolean isFecha2 = Boolean.TRUE;

        try {
            if (null != ADFUtils.findControlBinding("FechaInicioCapital")) {
                fechaInicioCapital = (Date) ADFUtils.findControlBinding("FechaInicioCapital").getInputValue();
                logger.log(ADFLogger.WARNING, "Valor FechaInicioCapital :" + fechaInicioCapital);
            } else {
                logger.log(ADFLogger.WARNING, "Valor FechaInicioCapital es nulo:");
            }
            if (null != valueChangeEvent.getNewValue()) {
                fechaVigencia = (Date) valueChangeEvent.getNewValue();
                logger.log(ADFLogger.WARNING, "Valor FechaValor :" + fechaVigencia);
            } else {
                logger.log(ADFLogger.WARNING, "Valor FechaValor es nulo:");
            }
            if (null != ADFUtils.findControlBinding("FechaVencimiento")) {
                fechaVencimiento = (Date) ADFUtils.findControlBinding("FechaVencimiento").getInputValue();
                logger.log(ADFLogger.WARNING, "Valor FechaVencimiento :" + fechaVencimiento);
            } else {
                logger.log(ADFLogger.WARNING, "Valor FechaVencimiento es nulo:");
            }
            if (null != fechaVigencia) {
                if (null != fechaVencimiento) {
                    if (fechaVigencia.compareTo(fechaVencimiento) < 0) {
                        logger.log(ADFLogger.WARNING,
                                   "El valor de la fecha vencimiento es mayor que o igual fechaInicioCapital.");
                    } else {
                        flagfechaVigencia = ADFUtils.findControlBinding("FechaValor");
                        flagfechaVigencia.setInputValue(null);
                        resetValueDateComponent(uiFechaVigencia);
                        isFecha1 = Boolean.FALSE;
                    }
                } else {
                    logger.log(ADFLogger.WARNING, "La fecha de vencimiento es nula :" + fechaVencimiento);
                }
                if (null != fechaInicioCapital) {
                    if (fechaVigencia.compareTo(fechaInicioCapital) <= 0) {
                        logger.log(ADFLogger.WARNING, "La fecha de vigencia es menor que la fecha de vencimiento.");
                    } else {
                        flagfechaVigencia = ADFUtils.findControlBinding("FechaValor");
                        flagfechaVigencia.setInputValue(null);
                        resetValueDateComponent(uiFechaVigencia);
                        isFecha2 = Boolean.FALSE;
                    }
                } else {
                    logger.log(ADFLogger.WARNING, "La fecha inicio capital es nula." + fechaInicioCapital);
                }
            } else {
                logger.log(ADFLogger.WARNING, "El valor de la fecha de vigencia es nula.");
            }

            if (!isFecha1 || !isFecha2) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FECHA_VIGENCIA_MENOR_O_IGUAL_QUE_FECHA_INICIO_CAPITAL_Y_MENOR_QUE_FECHA_VENCIMIENTO"));
            }

        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en validarFechaInicioCapital." + e.getClass() + "." + e.getMessage());
        }
    }

    public void setUiFechaVencimiento(RichInputDate uiFechaVencimiento) {
        this.uiFechaVencimiento = uiFechaVencimiento;
    }

    public RichInputDate getUiFechaVencimiento() {
        return uiFechaVencimiento;
    }

    public void setUiFechaVigencia(RichInputDate uiFechaVigencia) {
        this.uiFechaVigencia = uiFechaVigencia;
    }

    public RichInputDate getUiFechaVigencia() {
        return uiFechaVigencia;
    }

    public void crearComisionPrepago(ActionEvent actionEvent) {
        // Add event code here...
        
        logger.log(ADFLogger.WARNING, "INTO aceptarCrearComisionPopup.");
        GestionComisionBean gestionComisionBean = (GestionComisionBean)JSFUtils.resolveExpression("#{pageFlowScope.gestionComisionBean}");
        Map<String,Object> atributosComisionMap = new HashMap<String,Object>();
        List<String> bundleKeyErrorList = new ArrayList<String>();
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long comision = null;
        Long idPrepago = null;
        Boolean isValidaFechas = Boolean.FALSE;
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}") != null){
            idPrepago = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}").toString());
            }else{
                logger.log(ADFLogger.WARNING, "Objeto pageFlowScope.pIdPrepago Nulo.");
            }
            logger.log(ADFLogger.WARNING, "Valor del prepago." + idPrepago);
            logger.log(ADFLogger.WARNING, "Valor de la comision." + gestionComisionBean.getIdCom());
            if(null != gestionComisionBean.getIdCom()){
                logger.log(ADFLogger.WARNING, "Valor de la comision que se va a actualizar." + gestionComisionBean.getIdCom());
                actualizarComisionPrepagoActionBean(gestionComisionBean.getIdCom());
                resetValueDateComponent(uiFechaVigencia);
                resetValueDateComponent(uiFechaInicioCapital);
            }else{
                logger.log(ADFLogger.WARNING, "Se agrega una nueva comision.");
            operationBinding = bindings.getOperationBinding("agregarComisionPrepago");
            operationBinding.execute();

                if (null != operationBinding.getResult()) {
                    atributosComisionMap = (Map) operationBinding.getResult();

                    if (null != atributosComisionMap.get("idComision")) {
                        comision = (Long) atributosComisionMap.get("idComision");
                        gestionComisionBean.setIdCom(comision);
                    } else {
                        logger.log(ADFLogger.WARNING, "Valor id de la Comision es nulo.");
                    }
                    if (null != atributosComisionMap.get("isInformacionRequerida")) {
                        isValidar = (Boolean) atributosComisionMap.get("isInformacionRequerida");
                    } else {
                        logger.log(ADFLogger.WARNING, "Valor de validacion de Commit." + isValidar);
                    }
                    if (null != atributosComisionMap.get("isValidaFechas")) {
                        isValidaFechas = (Boolean) atributosComisionMap.get("isValidaFechas");
                    } else {
                        logger.log(ADFLogger.WARNING, "Valor de validacion de Commit." + isValidar);
                    }
                    if (isValidar && isValidaFechas) {

                        gestionComisionBean.setIsComision(Boolean.TRUE);
                        resetValueDateComponent(uiFechaVigencia);
                        resetValueDateComponent(uiFechaInicioCapital);

                        operationBinding = null;
                        operationBinding = bindings.getOperationBinding("insertarRowCargoPrepago");
                        operationBinding.getParamsMap().put("idPrepago", idPrepago);
                        operationBinding.getParamsMap().put("idComision", comision);
                        operationBinding.execute();

                    } else {
                        bundleKeyErrorList.add("MSG_COMISION_PREPAGO_FAVOR_INGRESAR_INFORMACION_REQUERIDA");
        //                        FacesMessage msg =
        //                            new FacesMessage(FacesMessage.SEVERITY_INFO, null,
        //                                             "Favor de ingresar la informacion requerida.");
        //                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_COMISION_PREPAGO_FAVOR_INGRESAR_INFORMACION_REQUERIDA"));
                        if(!isValidaFechas){
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FECHA_INICIO_CAPITAL_MAYOR_O_IGUAL_QUE_FECHA_VIGENCIA_Y_MENOR_O_IGUAL_QUE_FECHA_VENCIMIENTO"));
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FECHA_VIGENCIA_MENOR_O_IGUAL_QUE_FECHA_INICIO_CAPITAL_Y_MENOR_QUE_FECHA_VENCIMIENTO"));
                        }

                    }
                } else {
                    logger.log(ADFLogger.WARNING, "Error al crear la comision.");
                }
            }
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error en aceptarCrearComisionPopup." + e.getClass() + "." + e.getMessage());
        }
        logger.log(ADFLogger.WARNING, "Valor Boolean ::" + isValidar);
    }
}
