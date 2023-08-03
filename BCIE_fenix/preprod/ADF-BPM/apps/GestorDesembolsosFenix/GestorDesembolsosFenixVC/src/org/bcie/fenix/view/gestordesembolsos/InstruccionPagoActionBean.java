package org.bcie.fenix.view.gestordesembolsos;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
public class InstruccionPagoActionBean {
    
    public static ADFLogger logger = null;
    private RichPanelGroupLayout formulariosInstruccionesPago;   
    
    private RichPanelGroupLayout contenedorCamposTransferencia;
    private RichPanelGroupLayout contenedorPrincipal;
    private RichPanelGridLayout contenedorAccionesTransaccionesPago;
    
    private RichPanelGroupLayout contenedorAccionesTransferencia;
    private RichPanelGroupLayout contenedorAccionesAInstrucciones;
    private RichPopup popUpMensajes;
    private RichPopup popUpConfirmarEliminar;
    private RichPopup popUpCancelarGuardado;

    private String idTransferenciaAplicar = null;    
    private String bhqTransferencia = null;
    private RichPopup popUpErrorAplicarTransferencia;
    
    private int eventoCLick = 0;
    private RichPopup popUpEliminarInstruccion;
    private RichPopup popUpErroresInstruccion;
    
    private RichPanelGroupLayout form1;
    private RichPanelGroupLayout form2;
    private RichPanelGroupLayout form3;



    public InstruccionPagoActionBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(VerCrearSolicitudDesembolsosActionBean.class);
        }
    }

    private String tipoMensaje = null;


    public void selccionarTransferencia(SelectionEvent selectionEvent) {
        logger.warning("*** inicia metodo selccionarTransferencia");
        InstruccionPagoBean instruccionBean = (InstruccionPagoBean) JSFUtils.resolveExpression("#{pageFlowScope.InstruccionPagoBean}");

        // Actualizamos row seleccionado en tabla
        JSFUtils.resolveMethodExpression("#{bindings.ConsultaTransferenciasBanByDesembolso.collectionModel.makeCurrent}",
            Object.class, new Class[] { SelectionEvent.class },
            new Object[] { selectionEvent });

        //Recupera el "ID" de la fila seleccionada en la tabla
        if (null != JSFUtils.resolveExpression(
                    "#{bindings.ConsultaTransferenciasBanByDesembolso.IdTransferencia}")) {
            Long idTransferencia = (Long) JSFUtils.resolveExpression(
                    "#{bindings.ConsultaTransferenciasBanByDesembolso.IdTransferencia}");
            logger.warning(" Valor recuperado idTransferencia ->" +
                idTransferencia);
            instruccionBean.setIdTransferencia(idTransferencia);

            if (null != JSFUtils.resolveExpression(
                        "#{bindings.ConsultaTransferenciasBanByDesembolso.MontoTransferencia}")) {
                String MontoTransferencia = JSFUtils.resolveExpression(
                        "#{bindings.ConsultaTransferenciasBanByDesembolso.MontoTransferencia}")
                                                    .toString();
                instruccionBean.setMontoTransfrenciaSeleccionada(new BigDecimal(
                        MontoTransferencia));
                logger.warning("Monto de la transferencia seleccionada ->" +
                    instruccionBean.getMontoTransfrenciaSeleccionada());
            } else {
                logger.warning(
                    "El monto de la transferencia seleccionada es resuelta a null");
            }

            try {
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding(
                        "selectAndInsertRowRTransferenciasForm");
                operBinding.getParamsMap()
                           .put("idTransferencia", idTransferencia);
                operBinding.execute();

                if (!operBinding.getErrors().isEmpty()) {
                    logger.warning(
                        "OperationBinding selectAndInsertRowRTransferenciasForm devuelve errores" +
                        operBinding.getErrors().toString());
                    JSFUtils.addFacesErrorMessage(
                        "Error, operationBinding devuelve errores");
                }
            } catch (Exception e) {
                logger.log(ADFLogger.ERROR,
                    "Error en establecer transferenciao ", e);
            }

            InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                    "#{pageFlowScope.InstruccionPagoBean}");

            Boolean esEditable = instruccionBean.getAccionesTransferenciasVisible();
            logger.warning("selccionarTransferencia esEditable" + esEditable);

            if (esEditable) {
                logger.warning("Se actualizaran las banderas de edicion");
                instruccionesBean.setModoLetura(Boolean.FALSE);
                instruccionesBean.setModoLeturaDatTrans(Boolean.FALSE);
                instruccionBean.setBtnCancelarVisible(Boolean.TRUE);
                instruccionesBean.setBtnGuardarVisible(Boolean.TRUE);
            }

            AdfFacesContext.getCurrentInstance()
                           .addPartialTarget(getContenedorCamposTransferencia());
            AdfFacesContext.getCurrentInstance()
                           .addPartialTarget(getFormulariosInstruccionesPago());
            AdfFacesContext.getCurrentInstance()
                           .addPartialTarget(getContenedorAccionesAInstrucciones());
        } else {
            JSFUtils.addFacesErrorMessage(
                "Ocurrio un error al seleccionar la transferencia");
        }
    }

    public void sobtenerMontoTranferenciaActual() {
        logger.warning("*** inicia metodo sobtenerMontoTranferenciaActual");

        InstruccionPagoBean instruccionBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");

        //Recupera el "ID" de la fila seleccionada en la tabla
        if (null != JSFUtils.resolveExpression(
                    "#{bindings.ConsultaTransferenciasBanByDesembolso.IdTransferencia}")) {
            Long idTransferencia = (Long) JSFUtils.resolveExpression(
                    "#{bindings.ConsultaTransferenciasBanByDesembolso.IdTransferencia}");
            logger.warning(" Valor recuperado idTransferencia ->" +
                idTransferencia);
            instruccionBean.setIdTransferencia(idTransferencia);

            if (null != JSFUtils.resolveExpression(
                        "#{bindings.ConsultaTransferenciasBanByDesembolso.MontoTransferencia}")) {
                String MontoTransferencia = JSFUtils.resolveExpression(
                        "#{bindings.ConsultaTransferenciasBanByDesembolso.MontoTransferencia}")
                                                    .toString();
                instruccionBean.setMontoTransfrenciaSeleccionada(new BigDecimal(
                        MontoTransferencia));
                logger.warning("Monto de la transferencia seleccionada ->" +
                    instruccionBean.getMontoTransfrenciaSeleccionada());
            } else {
                logger.warning(
                    "El monto de la transferencia seleccionada es resuelta a null");
            }
        } else {
            JSFUtils.addFacesErrorMessage(
                "Ocurrio un error al seleccionar la transferencia");
        }

        logger.warning("*** finalizo metodo sobtenerMontoTranferenciaActual");
    }

    public List onSuggest(String string) {
        logger.warning("*Inf, Into method onSuggest");

        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();
        ;

        SelectItem element = null;

        BindingContainer bindings = BindingContext.getCurrent()
                                                  .getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get(
                "NumeroCtaBenef2");

        RowSetIterator iter = list.getListRowSetIterator();
        iter.reset();

        Row row = null;

        logger.warning("*Inf, Num de cuentas recuperado para la operacion : " +
            list.getAllRowsInRange().length);

        if (null != iter) {
            for (int i = 0; i < list.getAllRowsInRange().length; i++) {
                logger.warning("*Inf, Iterando el row " + i + "...");
                row = iter.getRowAtRangeIndex(i);

                if (null != row) {
                    String value = (String) row.getAttribute("NumeroCtaBenef");

                    if ((null != string) && (null != value)) {
                        logger.warning("*Inf, comparando String " + string +
                            " con elemento " + value + " en la lista");

                        if (value.toUpperCase().startsWith(string.toUpperCase())) {
                            logger.warning("*Inf, ok cadena a buscar  " +
                                string +
                                " coincide en el inicio con el valor : " +
                                value + " en la lista agregando a respuesta..");
                            element = new SelectItem(value);
                            resultItems.add(element);
                        }
                    } else {
                        logger.warning("*Inf, Important! String " + string +
                            " valor en lista " + value);
                    }
                }
            }
        } else {
            logger.warning("*Inf, Important! Iterador NULL");
            element = new SelectItem("");
            resultItems.add(element);
        }

        iter.closeRowSetIterator();

        return resultItems;
    }

    public void recuperarDatosCuenta(String numCuentaBeneficiario) {
        logger.warning(
            "*Inf, Inicia metodo recuperarDatosCuenta para noCuenta: " +
            numCuentaBeneficiario);

        BindingContainer binding = ADFUtils.getBindingContainer();
        OperationBinding operBinding = null;

        try {
            operBinding = binding.getOperationBinding(
                    "recuperarEstablecerInstruccionPago");
            operBinding.getParamsMap().put("noCuenta", numCuentaBeneficiario);
            operBinding.execute();
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error al recuperar datos cuenta " + e);
        }

        if (!operBinding.getErrors().isEmpty()) {
            logger.warning(
                "OperationBinding recuperarEstablecerInstruccionPago devuelve errores ->" +
                operBinding.getErrors().toString());
        } else {
            if (operBinding.getResult() != null) {
                if ((Boolean) operBinding.getResult()) {
                    logger.warning(
                        "ok instruccion de pago recuperada refrescando formulario..");
                    AdfFacesContext.getCurrentInstance()
                                   .addPartialTarget(getFormulariosInstruccionesPago());
                } else {
                    logger.warning(
                        "*Inf, Importante! no se pudo recuerar la instruccion de pago");
                }
            } else {
                logger.warning(
                    "*Error, operBinding.getResult() resuelto a null");
            }
        }

        logger.warning("*Inf, termina metodo recuperarDatosCuenta");
    }

    public void recuperarDatosCuenta2(String numCuentaBeneficiario) {
        logger.warning("*Inf, Inicia metodo recuperarDatosCuenta");

        Row rowInstruccionPago = null;
        Long idInstruccion = null;
        logger.warning("*** Numero de cuenta recuperado->" +
            numCuentaBeneficiario);

        InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding(
                    "buscarRecuperarNumCuentaBnf");
            operBinding.getParamsMap().put("numCuenta", numCuentaBeneficiario);
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning(
                    "OperationBinding buscarRecuperarNumCuentaBnf devuelve errores ->" +
                    operBinding.getErrors());
            }

            if (operBinding.getResult() != null) {
                rowInstruccionPago = (Row) operBinding.getResult();

                idInstruccion = (rowInstruccionPago.getAttribute("Id") == null)
                    ? null : (Long) rowInstruccionPago.getAttribute("Id");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error al recuperar datos cuenta " + e);
        }

        if (idInstruccion != null) {
            instruccionesBean.setModoLetura(Boolean.TRUE);
            instruccionesBean.setEsNuevaTransferencia(Boolean.FALSE);

            //Esto se hace para mostrar u ocultar los campos dependiendo del tipo de opcion de la instruccion de pago 
            //restaurarCamposByInstruccion();                                    
            //  asociarInstruccionPago(idInstruccion);                       
        } else {
            //if(!instruccionesBean.getEsNuevaTransferencia())
            //            asociarInstruccionPago(idInstruccion);                      
            instruccionesBean.setModoLetura(Boolean.FALSE);
        }

        logger.warning("*Inf, es nueva Transferencia : " +
            instruccionesBean.getEsNuevaTransferencia());

        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getFormulariosInstruccionesPago());
        logger.warning("*Inf, termina metodo recuperarDatosCuenta");
    }

    public void recuperarDatosCuentaSeleccionada(
        ValueChangeEvent valueChangeEvent) {
        logger.warning("*** inicia method recuperarDatosCuentaSeleccionada");

        String numCuentaBeneficiario = (String) valueChangeEvent.getNewValue();
        recuperarDatosCuenta(numCuentaBeneficiario);
    }

    public Boolean existenTransferenciaByIdContrato(Long IdContrato) {
        Boolean respuesta = null;

        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operBinding = bindings.getOperationBinding(
                    "existenTransferenciasRegistradasByIdContrato");
            operBinding.getParamsMap().put("idContrato", IdContrato);
            respuesta = (Boolean) operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding devuelve errores");
            }
        } catch (Exception e) {
            logger.warning(" :( Error al ejecutar el operationBinding: ", e);
        }

        return respuesta;
    }

    //este metodo por el momento no se ocupa la asignacion de la instruccio se cambio al modelo
    /*
      public void asociarInstruccionPago(Long idInstruccion){
           logger.warning("*Inf, Inicia el metodo asociarInstruccionPago :"+idInstruccion);
           Long  idOperacion = null;
           InstruccionPagoBean transferenciasBean
                                 = (InstruccionPagoBean)JSFUtils.resolveExpression("#{pageFlowScope.TransferenciasBean}");
    
                 try{
    
                      idOperacion = (null == JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}"))? null
                                  : new Long(JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}").toString());
    
                      logger.warning("*Inf, idOperacion recuperado :"+idOperacion);
    
                  }catch(Exception e){
                         logger.warning("***Error, no se pudo castear el idOperacion : ", e);
                  }
    
                   try{
                       BindingContainer bindings = ADFUtils.getBindingContainer();
                       OperationBinding operBinding = bindings.getOperationBinding("establecerInstruccionPago");
                       operBinding.getParamsMap().put("idInstruccionPago", idInstruccion);
                       operBinding.getParamsMap().put("idOperacion", idOperacion);
                       operBinding.execute();
    
                     if(!operBinding.getErrors().isEmpty()){
                       logger.warning("OperationBinding establecerInstruccionPago devuelve errores");
                      }
                   }catch(Exception e){
                       logger.warning("***Error, al ejecutar el operationBinding establecerInstruccionPago : ", e);
                   }
    
           logger.warning("*Inf, Termina el metodo  asociarInstruccionPago");
       }
     */
    public void agregarTransferencia(ActionEvent actionEvent) {
        logger.warning("*Inf, inicia metodo nuevaTransferencia");

        InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");

        Long idOperacion = (null == JSFUtils.resolveExpression(
                "#{pageFlowScope.idOperacion}")) ? null
                                                 : new Long(JSFUtils.resolveExpression(
                    "#{pageFlowScope.idOperacion}").toString());

        Long idDesembolso = (null == JSFUtils.resolveExpression(
                "#{pageFlowScope.idContratoDesembolso}")) ? null
                                                          : new Long(JSFUtils.resolveExpression(
                    "#{pageFlowScope.idContratoDesembolso}").toString());

        Integer idTcaTipoMoneda = ((null == JSFUtils.resolveExpression(
                "#{pageFlowScope.idTcaTipoMoneda}")) ? null
                                                     : Integer.parseInt(JSFUtils.resolveExpression(
                    "#{pageFlowScope.idTcaTipoMoneda}").toString()));

        logger.warning("idOperacion: " + idOperacion);
        logger.warning("idDesembolso: " + idDesembolso);

        BigDecimal montoTransferencia = null;

        BigDecimal disponibleTransferir = (null == instruccionesBean.getDisponibleTransferir())
            ? BigDecimal.ZERO : instruccionesBean.getDisponibleTransferir();

        BigDecimal totalTransferir = (null == instruccionesBean.getTotalTransferir())
            ? BigDecimal.ZERO : instruccionesBean.getTotalTransferir();

        logger.warning("disponibleTransferir: " + disponibleTransferir);
        logger.warning("totalTransferir: " + totalTransferir);

        if (disponibleTransferir.compareTo(totalTransferir) == 0) {
            montoTransferencia = disponibleTransferir;
        } else {
            montoTransferencia = BigDecimal.ZERO;
        }

        //Se asigna TRUE a valor para conocer si se esta agregando una Transferencia
        instruccionesBean.setAccionAgregar(Boolean.TRUE);

        if ((idOperacion == null) || (idDesembolso == null)) {
            JSFUtils.addFacesErrorMessage(
                "Error al recuperar idOperacion, IdContratoDesembolso");
            logger.warning("*Error parametros requeridos resueltos a null");
        } else {
            //  InstruccionesPagoBean.getMontoTotalTransferencias();
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operBinding = null;

            try {
                operBinding = bindings.getOperationBinding(
                        "nuevoRegistroTransferencia");
                operBinding.getParamsMap().put("idContrato", idDesembolso);
                operBinding.getParamsMap().put("idOperacion", idOperacion);
                operBinding.getParamsMap()
                           .put("IdTcaTipoMoneda", idTcaTipoMoneda);
                operBinding.getParamsMap()
                           .put("montoTransferencia", montoTransferencia);
                operBinding.execute();
            } catch (Exception e) {
                logger.warning("***Error, al ejecutar el operationBinding establecerInstruccionPago : ",
                    e);
            }

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning(
                    "OperationBinding establecerInstruccionPago devuelve errores->" +
                    operBinding.getErrors().toString());
            } else {
                instruccionesBean.setMontoTransfrenciaSeleccionada(BigDecimal.ZERO);
                instruccionesBean.setContenedorCamposTransferencia(Boolean.TRUE);
                instruccionesBean.setFormularioVisible(Boolean.TRUE);
                instruccionesBean.setAccionesInstruccionPagoVisible(Boolean.TRUE);
                instruccionesBean.setModoLetura(Boolean.FALSE);
                instruccionesBean.setModoLeturaDatTrans(Boolean.FALSE);
                instruccionesBean.setBtnCancelarVisible(Boolean.TRUE);
                instruccionesBean.setDeshabilitarBtnGuardar(Boolean.FALSE);
                instruccionesBean.setEsNuevaTransferencia(Boolean.TRUE);
                instruccionesBean.setBtnGuardarVisible(Boolean.TRUE);
                instruccionesBean.setBtnCancelarVisible(Boolean.TRUE);
                ADFUtils.setBoundAttributeValue("TipoOpcionBancoBeneficiario",
                    "OPCION_DEFAULT");
                ADFUtils.setBoundAttributeValue("TipoOpcionBancoIntermediario",
                    "OPCION_DEFAULT");
                AdfFacesContext.getCurrentInstance()
                               .addPartialTarget(getContenedorAccionesAInstrucciones());
                AdfFacesContext.getCurrentInstance()
                               .addPartialTarget(getContenedorCamposTransferencia());
                AdfFacesContext.getCurrentInstance()
                               .addPartialTarget(getFormulariosInstruccionesPago());
            }
        }

        logger.warning("Inicializacion por defecto inicio");
        ADFUtils.setBoundAttributeValue("TipoOpcionBeneficiario", "OPCION_D");
        logger.warning("Inicializacion por defecto fin");
    }

    public void agregarTransferencia2(ActionEvent actionEvent) {
        logger.warning("*** inicia metodo agregarInstruccionPago");

        InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");
        instruccionesBean.setMontoTransfrenciaSeleccionada(BigDecimal.ZERO);

        if ((instruccionesBean.getTotalDesembolso() == null) ||
                (instruccionesBean.getTotalDesembolso()
                                      .compareTo(BigDecimal.ZERO) == 0)) {
            logger.warning("*** Error monto a desembolsar del contrato ->" +
                instruccionesBean.getTotalDesembolso());
            JSFUtils.addFacesErrorMessage(
                "No se cuenta con monto disponible para la transferencia");
        } else {
            Boolean respuesta = null;
            Long newIdTransaccion = null;
            InstruccionPagoBean transferenciasBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                    "#{pageFlowScope.InstruccionPagoBean}");
            Long idOperacion = transferenciasBean.getIdOperacion();
            Long idContratoDesembolso = transferenciasBean.getIdContratoDesembolso();
            Integer tcaTipoMoneda = transferenciasBean.getIdTcaTipoMoneda();
            BigDecimal montoTransferencia = BigDecimal.ZERO;
            Boolean exist = null;
            exist = existenTransferenciaByIdContrato(idContratoDesembolso);

            if (!exist) {
                montoTransferencia = instruccionesBean.getDisponibleTransferir();
            }

            try {
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operBinding = bindings.getOperationBinding(
                        "nuevaTransaccion");
                operBinding.getParamsMap().put("idOperacion", idOperacion);
                operBinding.getParamsMap()
                           .put("idContrato", idContratoDesembolso);
                operBinding.getParamsMap().put("tcaTipoMoneda", tcaTipoMoneda);
                operBinding.getParamsMap()
                           .put("montoTransferencia", montoTransferencia);
                newIdTransaccion = (Long) operBinding.execute();
                transferenciasBean.setIdTransferencia(newIdTransaccion);

                if (!operBinding.getErrors().isEmpty()) {
                    logger.warning("OperationBinding devuelve errores");
                }
            } catch (Exception e) {
                logger.warning(" :( Error al ejecutar el operationBinding: ", e);
            }

            if (newIdTransaccion == null) {
                JSFUtils.addFacesErrorMessage(
                    "Error, no se pudo crear la nueva tranferenca");
            }

            instruccionesBean.setContenedorCamposTransferencia(Boolean.TRUE);
            instruccionesBean.setFormularioVisible(Boolean.TRUE);
            instruccionesBean.setAccionesInstruccionPagoVisible(Boolean.TRUE);
            instruccionesBean.setModoLetura(Boolean.FALSE);
            instruccionesBean.setModoLeturaDatTrans(Boolean.FALSE);
            instruccionesBean.setBtnCancelarVisible(Boolean.TRUE);
            instruccionesBean.setDeshabilitarBtnGuardar(Boolean.FALSE);
            instruccionesBean.setEsNuevaTransferencia(Boolean.TRUE);
            instruccionesBean.setBtnGuardarVisible(Boolean.TRUE);
            instruccionesBean.setBtnCancelarVisible(Boolean.TRUE);

            AdfFacesContext.getCurrentInstance()
                           .addPartialTarget(getContenedorAccionesAInstrucciones());
            AdfFacesContext.getCurrentInstance()
                           .addPartialTarget(getContenedorAccionesTransaccionesPago());
            AdfFacesContext.getCurrentInstance()
                           .addPartialTarget(getContenedorCamposTransferencia());
            AdfFacesContext.getCurrentInstance()
                           .addPartialTarget(getFormulariosInstruccionesPago());

            logger.warning("*** Termina metodo agregarInstruccionPago  -:" +
                instruccionesBean.getEsNuevaTransferencia());
        }
    }

    private boolean validacionBloqueInstrucciones(String bloque,
        String TipoOpcion, String NumeroCuenta, String Identificador,
        String NombreDireccion) {
        logger.warning("Inicio validacionBloqueInstrucciones bloque: " +
            bloque);

        logger.warning("bloque: " + bloque);
        logger.warning("TipoOpcion: " + TipoOpcion);
        logger.warning("NumeroCuenta: " + NumeroCuenta);
        logger.warning("Identificador: " + Identificador);
        logger.warning("NombreDireccion: " + NombreDireccion);

        boolean camposObligatorios = Boolean.TRUE;
        char guion = '-';
        int longitudLineaDireccion = 35;
        int cantidadLineasDireccion = 4;

        Integer longitudMinimaIdentificador = 8;
        Integer longitudMaximaIdentificador = 11;

        String msgTemp = "";

        if (TipoOpcion.equals("OPCION_A")) {
            if (Identificador == null) {
                msgTemp = "El identificador del " + bloque +
                    " no puede estar vacio.";
                guardarErrorInstrucciones("Identificador" + bloque, msgTemp);
                camposObligatorios = Boolean.FALSE;
            } else {
                Integer longitudIdentificador = Identificador.length();
                logger.warning(
                    "longitudIdentificador.compareTo(longitudMinimaIdentificador): " +
                    longitudIdentificador.compareTo(longitudMinimaIdentificador));

                if (longitudIdentificador.compareTo(longitudMinimaIdentificador) == -1) {
                    msgTemp = "El identificador del " + bloque +
                        " no puede tener menos de " +
                        longitudMinimaIdentificador +
                        " carácteres actualmente tiene " +
                        longitudIdentificador + " carácteres.";
                    guardarErrorInstrucciones("Identificador" + bloque, msgTemp);
                    camposObligatorios = Boolean.FALSE;
                }

                logger.warning(
                    "longitudIdentificador.compareTo(longitudMaximaIdentificador): " +
                    longitudIdentificador.compareTo(longitudMaximaIdentificador));

                if (longitudIdentificador.compareTo(longitudMaximaIdentificador) == 1) {
                    msgTemp = "El identificador del " + bloque +
                        " no puede tener más de " +
                        longitudMaximaIdentificador +
                        " carácteres actualmente tiene " +
                        longitudIdentificador + " carácteres.";
                    guardarErrorInstrucciones("Identificador" + bloque, msgTemp);
                    camposObligatorios = Boolean.FALSE;
                }
            }
        } else if (TipoOpcion.equals("OPCION_D")) {
            logger.warning("Nombre y Direccion " + bloque + ": " +
                NombreDireccion);

            if (NombreDireccion == null) {
                msgTemp = "El Nombre y Dirección del " + bloque +
                    " no puede estar vacio.";
                guardarErrorInstrucciones("NombreDireccion" + bloque, msgTemp);
                camposObligatorios = Boolean.FALSE;
            } else {
                String[] partsDireccion = NombreDireccion.split("(\r\n|\r|\n)");

                for (int i = 0; i < partsDireccion.length; i++) {
                    String linea = partsDireccion[i];
                    logger.warning("partsDireccion[" + (i + 1) + "] = " +
                        linea);

                    int longitudLinea = linea.length();

                    if (longitudLinea > longitudLineaDireccion) {
                        msgTemp = "La longitud de la línea " + (i + 1) +
                            " para el Nombre y Dirección del " + bloque +
                            " es de " + longitudLinea +
                            ". El limite permitido es de " +
                            longitudLineaDireccion + ".";
                        guardarErrorInstrucciones("longitudLinea" + bloque +
                            "(" + (i + 1) + ")", msgTemp);
                        camposObligatorios = Boolean.FALSE;
                    } else {
                        msgTemp = "La longitud de la línea " + (i + 1) +
                            " es de " + longitudLinea + ".";
                        logger.warning(msgTemp);
                    }

                    if (linea.charAt(0) == guion) {
                        msgTemp = "La línea " + (i + 1) +
                            " para el Nombre y Dirección del " + bloque +
                            " no puede comenzar con un guión.";
                        guardarErrorInstrucciones("guion" + bloque + "(" +
                            (i + 1) + ")", msgTemp);
                        camposObligatorios = Boolean.FALSE;
                    }
                }

                if (partsDireccion.length > cantidadLineasDireccion) {
                    msgTemp = "La cantidad de líneas es " +
                        partsDireccion.length +
                        " para el Nombre y Dirección del " + bloque +
                        " no puede ser mayor a " + cantidadLineasDireccion +
                        ".";
                    guardarErrorInstrucciones("cantidadLineasDireccion" +
                        bloque, msgTemp);
                    camposObligatorios = Boolean.FALSE;
                }
            }
        } else {
            msgTemp = "No es obligatorio seleccionar algún tipo de opcion para el " +
                bloque + ".";

            //guardarErrorInstrucciones("TipoOpcion"+bloque,msgTemp);
            //camposObligatorios = Boolean.FALSE;
        }

        logger.warning("Fin validacionBloqueInstrucciones bloqu: " + bloque +
            " retorna " + camposObligatorios);

        return camposObligatorios;
    }

    private void guardarErrorInstrucciones(String keyError, String msgError) {
        logger.warning(msgError);
        //this.mapTable.put(keyError, msgError);
        JSFUtils.addFacesErrorMessage(msgError);
    }

 
 

    public void borrarCamposNoUtilizadosTransferencias() {
        String TipoOpcionBeneficiario = (String) ADFUtils.getBoundAttributeValue(
                "TipoOpcionBeneficiario");
        String TipoOpcionBancoBeneficiario = (String) ADFUtils.getBoundAttributeValue(
                "TipoOpcionBancoBeneficiario");
        String TipoOpcionBancoIntermediario = (String) ADFUtils.getBoundAttributeValue(
                "TipoOpcionBancoIntermediario");

        //********************************************************************************
        if (TipoOpcionBeneficiario.equals("OPCION_A")) {
            ADFUtils.setBoundAttributeValue("NombreBeneficiario", null);
            ADFUtils.setBoundAttributeValue("DireccionBeneficiario", null);
        }

        if (TipoOpcionBeneficiario.equals("OPCION_D")) {
            ADFUtils.setBoundAttributeValue("IdentificadorBeneficiario", null);
            ADFUtils.setBoundAttributeValue("DireccionBeneficiario", null);
        }

        //********************************************************************************
        if (TipoOpcionBancoBeneficiario.equals("OPCION_A")) {
            ADFUtils.setBoundAttributeValue("NombreBancoBeneficiario", null);
            ADFUtils.setBoundAttributeValue("DireccionBancoBeneficiario", null);
        }

        if (TipoOpcionBancoBeneficiario.equals("OPCION_D")) {
            ADFUtils.setBoundAttributeValue("IdentificadorBancoBeneficiario",
                null);
            ADFUtils.setBoundAttributeValue("DireccionBancoBeneficiario", null);
        }

        if (TipoOpcionBancoBeneficiario.equals("OPCION_DEFAULT")) {
            ADFUtils.setBoundAttributeValue("NombreBancoBeneficiario", null);
            ADFUtils.setBoundAttributeValue("IdentificadorBancoBeneficiario",
                null);
            ADFUtils.setBoundAttributeValue("DireccionBancoBeneficiario", null);
            ADFUtils.setBoundAttributeValue("NumeroCuentaBancoBeneficiario",
                null);
        }

        //********************************************************************************
        if (TipoOpcionBancoIntermediario.equals("OPCION_A")) {
            ADFUtils.setBoundAttributeValue("NombreBancoIntermediario", null);
            ADFUtils.setBoundAttributeValue("DireccionBancoIntermediario", null);
        }

        if (TipoOpcionBancoIntermediario.equals("OPCION_D")) {
            ADFUtils.setBoundAttributeValue("IdentificadorBancoIntermediario",
                null);
            ADFUtils.setBoundAttributeValue("DireccionBancoIntermediario", null);
        }

        if (TipoOpcionBancoIntermediario.equals("OPCION_DEFAULT")) {
            ADFUtils.setBoundAttributeValue("NombreBancoIntermediario", null);
            ADFUtils.setBoundAttributeValue("IdentificadorBancoIntermediario",
                null);
            ADFUtils.setBoundAttributeValue("DireccionBancoIntermediario", null);
            ADFUtils.setBoundAttributeValue("NumeroCuentaBancoIntermediario",
                null);
        }

        //********************************************************************************
    }

    public void guardarTransferencia(ActionEvent actionEvent) {
        logger.warning("Inicia metodo guardarTransferencia.");

        Boolean camposObligatorios = Boolean.TRUE;
        String msgError = "";

        String TipoOpcionBeneficiario = (String) ADFUtils.getBoundAttributeValue(
                "TipoOpcionBeneficiario");
        String NumeroCtaBenef = (String) ADFUtils.getBoundAttributeValue(
                "NumeroCuenteBeneficiario");
        String IdentificadorBeneficiario = (String) ADFUtils.getBoundAttributeValue(
                "IdentificadorBeneficiario");
        String NombreDireccionBeneficiario = (String) ADFUtils.getBoundAttributeValue(
                "NombreBeneficiario");

        String TipoOpcionBancoBeneficiario = (String) ADFUtils.getBoundAttributeValue(
                "TipoOpcionBancoBeneficiario");
        String NumeroCuentaBancoBeneficiario = (String) ADFUtils.getBoundAttributeValue(
                "NumeroCuentaBancoBeneficiario");
        String IdentificadorBancoBeneficiario = (String) ADFUtils.getBoundAttributeValue(
                "IdentificadorBancoBeneficiario");
        String NombreDireccionBancoBeneficiario = (String) ADFUtils.getBoundAttributeValue(
                "NombreBancoBeneficiario");

        String TipoOpcionBancoIntermediario = (String) ADFUtils.getBoundAttributeValue(
                "TipoOpcionBancoIntermediario");
        String NumeroCuentaBancoIntermediario = (String) ADFUtils.getBoundAttributeValue(
                "NumeroCuentaBancoIntermediario");
        String IdentificadorBancoIntermediario = (String) ADFUtils.getBoundAttributeValue(
                "IdentificadorBancoIntermediario");
        String NombreDireccionBancoIntermediario = (String) ADFUtils.getBoundAttributeValue(
                "NombreBancoIntermediario");

        logger.warning("Valores Campos guardarTransferencia");

        logger.warning("TipoOpcionBeneficiario: " + TipoOpcionBeneficiario);
        logger.warning("NumeroCtaBenef: " + NumeroCtaBenef);
        logger.warning("IdentificadorBeneficiario: " +
            IdentificadorBeneficiario);
        logger.warning("NombreDireccionBeneficiario: " +
            NombreDireccionBeneficiario);

        logger.warning("TipoOpcionBancoBeneficiario: " +
            TipoOpcionBancoBeneficiario);
        logger.warning("NumeroCuentaBancoBeneficiario: " +
            NumeroCuentaBancoBeneficiario);
        logger.warning("IdentificadorBancoBeneficiario: " +
            IdentificadorBancoBeneficiario);
        logger.warning("NombreDireccionBancoBeneficiario: " +
            NombreDireccionBancoBeneficiario);

        logger.warning("TipoOpcionBancoIntermediario: " +
            TipoOpcionBancoIntermediario);
        logger.warning("NumeroCuentaBancoIntermediario: " +
            NumeroCuentaBancoIntermediario);
        logger.warning("IdentificadorBancoIntermediario: " +
            IdentificadorBancoIntermediario);
        logger.warning("NombreDireccionBancoIntermediario: " +
            NombreDireccionBancoIntermediario);

        boolean vb_InstBeneficiarioValidas = validacionBloqueInstrucciones("Beneficiario",
                TipoOpcionBeneficiario, NumeroCtaBenef,
                IdentificadorBeneficiario, NombreDireccionBeneficiario);

        boolean vb_InstBancoBeneficiarioValidas = validacionBloqueInstrucciones("Banco Beneficiario",
                TipoOpcionBancoBeneficiario, NumeroCuentaBancoBeneficiario,
                IdentificadorBancoBeneficiario, NombreDireccionBancoBeneficiario);

        boolean vb_InstBancoIntermediarioValidas = validacionBloqueInstrucciones("Banco Intermediario",
                TipoOpcionBancoIntermediario, NumeroCuentaBancoIntermediario,
                IdentificadorBancoIntermediario,
                NombreDireccionBancoIntermediario);

        logger.warning("vb_InstBeneficiarioValidas: " +
            vb_InstBeneficiarioValidas);
        logger.warning("vb_InstBancoBeneficiarioValidas: " +
            vb_InstBancoBeneficiarioValidas);
        logger.warning("vb_InstBancoIntermediarioValidas: " +
            vb_InstBancoIntermediarioValidas);

        camposObligatorios = vb_InstBeneficiarioValidas &&
            vb_InstBancoBeneficiarioValidas &&
            vb_InstBancoIntermediarioValidas;

        BigDecimal montoTransferencia = (BigDecimal) ADFUtils.getBoundAttributeValue(
                "MontoTransferencia1");
        InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");

        if ((montoTransferencia == null) ||
                (montoTransferencia == BigDecimal.ZERO)) {
            msgError = "Error, se requiere un valor para el monto de la transferencia.";
            guardarErrorInstrucciones("montoTransferencia", msgError);
            camposObligatorios = Boolean.FALSE;
        }

        logger.warning("camposObligatorios: " + camposObligatorios);

        if (camposObligatorios) {
            if (validarMontoTransferencia(montoTransferencia)) {
                validarCamposNumeroCuenta();
                borrarCamposNoUtilizadosTransferencias();
                comitTransferencia();
                instruccionesBean.setBtnGuardarVisible(Boolean.FALSE);
            }
        }

        //Se asigna valor 0 a Monto de Transferencia para obtener el valor actual al no seleccionar un elemento en tabla.
        instruccionesBean.setMontoTransfrenciaSeleccionada(BigDecimal.ZERO);
        instruccionesBean.setAccionAgregar(Boolean.FALSE);

        logger.warning(" Termina metodo guardarTransferencia");
    }

    public Boolean guardarInformacionTransferencia() {
        logger.warning("*Inf, Inicia metodo guardarInformacionTransferencia");

        Boolean respuesta = Boolean.FALSE;

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding(
                    "valiarCamposCuentaInstruccionPago");
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning(
                    "*** OperationBinding valiarCamposCuentaInstruccionPago devuelve errores-> " +
                    operBinding.getErrors().toString());
            }
        } catch (Exception e) {
            logger.warning("***Error al ejecutar el operationBinding valiarCamposCuentaInstruccionPago ->",
                e);
        }

        logger.warning("*Inf, Inicia metodo guardarInformacionTransferencia");

        return respuesta;
    }

    public void validarCamposNumeroCuenta() {
        logger.warning("*Inf, Inicia metodo validarCamposNumeroCuenta");

        String numeroCuentaBancoBenef = (null == ADFUtils.getBoundAttributeValue(
                "NumeroCuentaBancoBeneficiario")) ? null
                                                  : ADFUtils.getBoundAttributeValue(
                "NumeroCuentaBancoBeneficiario").toString();

        if ((null == numeroCuentaBancoBenef) ||
                numeroCuentaBancoBenef.equals("")) {
            logger.warning(
                "*Inf, el campo NumeroCuentaBancoBeneficiario es null no se validara");
        } else {
            if (numeroCuentaBancoBenef.substring(0, 1).equals("/")) {
                logger.warning(
                    "*Inf,ok NumeroCuentaBancoBeneficiario ya cuenta con el caracter /");
            } else {
                logger.warning(
                    "*Inf, agregando el caracter / al numero de cuenta de NumeroCtaBanBenef");
                numeroCuentaBancoBenef = "/" + numeroCuentaBancoBenef;
                ADFUtils.setBoundAttributeValue("NumeroCuentaBancoBeneficiario",
                    numeroCuentaBancoBenef);
            }
        }

        //--------------------                                     ------------------>
        String numeroCuentaBancoInter = (null == ADFUtils.getBoundAttributeValue(
                "NumeroCuentaBancoIntermediario")) ? null
                                                   : ADFUtils.getBoundAttributeValue(
                "NumeroCuentaBancoIntermediario").toString();

        if ((null == numeroCuentaBancoInter) ||
                numeroCuentaBancoInter.equals("")) {
            logger.warning(
                "*Inf, el campo NumeroCuentaBancoIntermediario es null no se validara");
        } else {
            if (numeroCuentaBancoInter.substring(0, 1).equals("/")) {
                logger.warning(
                    "*Inf,ok NumeroCuentaBancoIntermediario ya cuenta con el caracter /");
            } else {
                logger.warning(
                    "*Inf, agregando el caracter / al numero de cuenta de NumeroCuentaBancoIntermediario");
                numeroCuentaBancoInter = "/" + numeroCuentaBancoInter;
                ADFUtils.setBoundAttributeValue("NumeroCuentaBancoIntermediario",
                    numeroCuentaBancoInter);
            }
        }

        logger.warning("*Inf, termina metodo validarCamposNumeroCuenta");
    }

    public void validarCamposNumeroCuenta2() {
        logger.warning("*Inf, Inicia metodo validarCamposNumeroCuenta");

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding(
                    "valiarCamposCuentaInstruccionPago");
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning(
                    "*** OperationBinding valiarCamposCuentaInstruccionPago devuelve errores-> " +
                    operBinding.getErrors().toString());
            }
        } catch (Exception e) {
            logger.warning("***Error al ejecutar el operationBinding valiarCamposCuentaInstruccionPago ->",
                e);
        }

        logger.warning("*Inf, termina metodo validarCamposNumeroCuenta");
    }

    public void comitTransferencia() {
        logger.warning("*Inf, Inicia metodo comitTransferencia");

        InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");

        BindingContainer binding = ADFUtils.getBindingContainer();
        OperationBinding operBinding = null;

        try {
            operBinding = binding.getOperationBinding(
                    "guardarInformacionTransferencia");
            operBinding.execute();
        } catch (Exception e) {
            logger.warning("**Error al ejecutar el operationBinding comitTransferencia: ",
                e);
        }

        if (!operBinding.getErrors().isEmpty()) {
            logger.warning(
                "*Inf, OperationBinding comitTransferencia devuelve errores ->" +
                operBinding.getErrors().toString());
        } else {
            Long idDesembolso = instruccionesBean.getIdContratoDesembolso();

            if (idDesembolso != null) {
                instruccionesBean.cargarTablaTransferencias(idDesembolso);
            } else {
                logger.warning(
                    "*Error, No se pudo recargar la tabla de transferencias idDesembolso resuelto a NUll");
                JSFUtils.addFacesErrorMessage(
                    "Error, No se pudo recuperar el id del contrato de desembolso");
            }

            instruccionesBean.setEsNuevaTransferencia(Boolean.FALSE);
            instruccionesBean.setModoLetura(Boolean.TRUE);
            instruccionesBean.setModoLeturaDatTrans(Boolean.TRUE);
            instruccionesBean.setBtnCancelarVisible(Boolean.FALSE);
            instruccionesBean.setBtnGuardarVisible(Boolean.FALSE);

            instruccionesBean.setAccionAgregar(Boolean.FALSE);
            AdfFacesContext.getCurrentInstance()
                           .addPartialTarget(getContenedorPrincipal());
        }

        logger.warning("*Inf, Termina metodo comitTransferencia");
    }

    public void comitTransferencia2() {
        logger.warning("*** inicia metodo comitTransferencia");

        Long idOperacion = null;

        try {
            idOperacion = (null == JSFUtils.resolveExpression(
                    "#{pageFlowScope.idOperacion}")) ? null
                                                     : new Long(JSFUtils.resolveExpression(
                        "#{pageFlowScope.idOperacion}").toString());
        } catch (Exception e) {
            logger.warning(
                "***Error, no se pudo recuperar el id de la operacion");
        }

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding(
                    "comitTransferencia");
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning(
                    "*Inf, OperationBinding comitTransferencia devuelve errores");
                logger.warning("***Error -> " +
                    operBinding.getErrors().toString());
            }
        } catch (Exception e) {
            logger.warning("**Error al ejecutar el operationBinding comitTransferencia: ",
                e);
        }

        InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");
        instruccionesBean.setModoLetura(Boolean.TRUE);
        instruccionesBean.setModoLeturaDatTrans(Boolean.TRUE);

        Long idContrato = instruccionesBean.getIdContratoDesembolso();
        instruccionesBean.cargarTablaTransferencias(idContrato);
        instruccionesBean.setEsNuevaTransferencia(Boolean.FALSE);
        instruccionesBean.setBtnGuardarVisible(Boolean.FALSE);
        instruccionesBean.setBtnCancelarVisible(Boolean.FALSE);

        logger.warning("*Inf, log preparando la restauracion de lso campos");
        restaurarCamposByInstruccion();

        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getContenedorPrincipal());
        logger.warning("*** Termina metodo comitTransferencia");
    }

    public void actualiarInstruccionPago(ActionEvent actionEvent) {
        logger.warning("*** inicia metodo actualiarInstruccionPago");

        InstruccionPagoBean transferenciasBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.TransferenciasBean}");
        Long IdOperacion = transferenciasBean.getIdOperacion();

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding(
                    "actualizarInstruccionPago");
            operBinding.getParamsMap().put("idOperacion", IdOperacion);
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("*** OperationBinding devuelve errores");
            }
        } catch (Exception e) {
            logger.warning(" :( Error al ejecutar el operationBinding: ", e);
        }

        logger.warning("*** Termina metodo actualiarInstruccionPago");
    }

    public void editarDatosTransferencia(ActionEvent actionEvent) {
        Boolean vbEditarDeshabilitado = (Boolean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean.deshabilitarBtnEdicionTran}");

        if (vbEditarDeshabilitado) {
            logger.warning("vbEditarDeshabilitado es true");
        } else {
            logger.warning("vbEditarDeshabilitado es false");

            InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                    "#{pageFlowScope.InstruccionPagoBean}");
            instruccionesBean.setModoLetura(Boolean.FALSE);
            instruccionesBean.setModoLeturaDatTrans(Boolean.FALSE);
            instruccionesBean.setBtnCancelarVisible(Boolean.TRUE);
            instruccionesBean.setBtnGuardarVisible(Boolean.TRUE);

            AdfFacesContext.getCurrentInstance()
                           .addPartialTarget(getContenedorAccionesAInstrucciones());
            AdfFacesContext.getCurrentInstance()
                           .addPartialTarget(getContenedorCamposTransferencia());
            AdfFacesContext.getCurrentInstance()
                           .addPartialTarget(getFormulariosInstruccionesPago());
        }

        logger.warning("*** Termina el metodo selccionarTransferencia");
    }

    public void editarInstruccionPago(ActionEvent actionEvent) {
        logger.warning("*** inicia metodo editarInstruccionPago");

        InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");
        instruccionesBean.setModoLetura(Boolean.FALSE);
        instruccionesBean.setModoLeturaDatTrans(Boolean.TRUE);
        instruccionesBean.setBtnCancelarVisible(Boolean.TRUE);
        instruccionesBean.setBtnGuardarVisible(Boolean.TRUE);

        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getContenedorAccionesAInstrucciones());
        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getContenedorCamposTransferencia());
        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getFormulariosInstruccionesPago());
        logger.warning("*** Termina metodo editarInstruccionPago");
    }

    public void abrirPopUpEliminarInstruccion(ActionEvent actionEvent) {
        logger.warning("*** inicia metodo abrirPopUpEliminarInstruccion");

        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpEliminarInstruccion().show(popupHints);
        logger.warning("*** inicia metodo abrirPopUpEliminarInstruccion");
    }

    public void aceptarEliminarInstruccion(ActionEvent actionEvent) {
        logger.warning("*** inicia metodo aceptarEliminarInstruccion");

        InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");
        instruccionesBean.setModoLetura(Boolean.FALSE);
        instruccionesBean.setEsNuevaTransferencia(Boolean.FALSE);

        ADFUtils.setBoundAttributeValue("TipoOpcionBeneficiario", "OPCION_A");
        ADFUtils.setBoundAttributeValue("NumeroCuenteBeneficiario", "");
        ADFUtils.setBoundAttributeValue("IdentificadorBeneficiario", "");
        ADFUtils.setBoundAttributeValue("NombreBeneficiario", "");
        ADFUtils.setBoundAttributeValue("DireccionBeneficiario", "");

        ADFUtils.setBoundAttributeValue("TipoOpcionBancoBeneficiario",
            "OPCION_A");
        ADFUtils.setBoundAttributeValue("NumeroCuentaBancoBeneficiario", "");
        ADFUtils.setBoundAttributeValue("IdentificadorBancoBeneficiario", "");
        ADFUtils.setBoundAttributeValue("NombreBancoBeneficiario", "");
        ADFUtils.setBoundAttributeValue("DireccionBancoBeneficiario", "");

        ADFUtils.setBoundAttributeValue("TipoOpcionBancoIntermediario",
            "OPCION_A");
        ADFUtils.setBoundAttributeValue("NumeroCuentaBancoIntermediario", "");
        ADFUtils.setBoundAttributeValue("IdentificadorBancoIntermediario", "");
        ADFUtils.setBoundAttributeValue("NombreBancoIntermediario", "");
        ADFUtils.setBoundAttributeValue("DireccionBancoIntermediario", "");

        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getFormulariosInstruccionesPago());
        getPopUpEliminarInstruccion().hide();
        logger.warning("*** Termina metodo aceptarEliminarInstruccion");
    }

    public void cancelarEliminarInstruccion(ActionEvent actionEvent) {
        logger.warning("*** inicia metodo cancelarEliminarInstruccion");
        getPopUpEliminarInstruccion().hide();
        logger.warning("*** Termina metodo cancelarEliminarInstruccion");
    }

    public void cancelarErroresInstruccion(ActionEvent actionEvent) {
        logger.warning("*** inicia metodo cancelarErroresInstruccion");
        getPopUpErroresInstruccion().hide();
        logger.warning("*** Termina metodo cancelarErroresInstruccion");
    }

    public void eliminarInstruccionPago(ActionEvent actionEvent) {
        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding(
                    "actualizarInstruccionPago");
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("*** OperationBinding devuelve errores");
            }
        } catch (Exception e) {
            logger.warning(" :( Error al ejecutar el operationBinding: ", e);
        }

        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getContenedorPrincipal());
    }

    public void eliminarTransferencia(Long idContrato, Long idTransferencia) {
        logger.warning("*Inf, Inicia metodo eliminarTransferencia");

        InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");
        Boolean seEliminoTransferencia = Boolean.FALSE;
        Boolean quedanRegistrosTrans = Boolean.FALSE;

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding(
                    "transferenciaABanEstatusCero");
            operBinding.getParamsMap().put("idTransferencia", idTransferencia);
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning(
                    "*Inf, OperationBinding transferenciaABanEstatusCero devuelve errores" +
                    operBinding.getErrors().toString());
            } else {
                seEliminoTransferencia = (null == operBinding.getResult())
                    ? Boolean.FALSE : (Boolean) operBinding.getResult();
            }
        } catch (Exception e) {
            logger.warning("***Error al ejecutar el operationBinding transferenciaABanEstatusCero: ",
                e);
        }

        if (seEliminoTransferencia) {
            quedanRegistrosTrans = instruccionesBean.cargarTablaTransferencias(idContrato);

            if (quedanRegistrosTrans) {
                logger.warning(
                    "*Inf, aun hay transferencias estado del formulario visible");
            } else {
                logger.warning("*Inf, ocultando formulario transferencias");
                instruccionesBean.setContenedorCamposTransferencia(Boolean.FALSE);
                instruccionesBean.setFormularioVisible(Boolean.FALSE);
                instruccionesBean.setAccionesInstruccionPagoVisible(Boolean.FALSE);
                AdfFacesContext.getCurrentInstance()
                               .addPartialTarget(getContenedorPrincipal());
            }
        } else {
            logger.warning("*Inf, No se pudo eliminar la transferencia");
        }

        logger.warning("*Inf, Inicia metodo eliminarTransferencia");
    }

    public void validarMontoAction(ValueChangeEvent valueChangeEvent) {
        BigDecimal montoTransferencia = (BigDecimal) valueChangeEvent.getNewValue();
        validarMontoTransferencia(montoTransferencia);
    }

    public Boolean validarMontoTransferencia(BigDecimal montoTransferencia) {
        logger.warning(
            " Inicia metodo validarMontoTransferencia valor recuperado-->" +
            montoTransferencia);

        Boolean respuesta = Boolean.TRUE;

        if ((montoTransferencia == null) ||
                (montoTransferencia == BigDecimal.ZERO)) {
            JSFUtils.addFacesErrorMessage(
                "Error, se requiere un valor para el monto de la transferencia.");
        }

        InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");

        BigDecimal disponible = (null == instruccionesBean.getDisponibleTransferir())
            ? BigDecimal.ZERO : instruccionesBean.getDisponibleTransferir();

        logger.warning("Agregar: " + instruccionesBean.getAccionAgregar());
        logger.warning("disponible: " + disponible);

        //Cuando se encuentra agregando una nueva Transferencia se valida el monto contra el disponible actual.
        if (instruccionesBean.getAccionAgregar()) {
            int res = montoTransferencia.compareTo(disponible);
            logger.warning("res: " + res);

            if (res == 1) {
                respuesta = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage(
                    "No se cuenta con el monto disponible para la transferencia.");
            }
        } else {
            logger.warning("ejecutar sobtenerMontoTranferenciaActual");
            sobtenerMontoTranferenciaActual();

            if (instruccionesBean.getMontoTransfrenciaSeleccionada() == BigDecimal.ZERO) {
                if (null != JSFUtils.resolveExpression(
                            "#{bindings.ConsultaTransferenciasBanByDesembolso.MontoTransferencia}")) {
                    String montoTransferenciaSelect = JSFUtils.resolveExpression(
                            "#{bindings.ConsultaTransferenciasBanByDesembolso.MontoTransferencia}")
                                                              .toString();
                    instruccionesBean.setMontoTransfrenciaSeleccionada(new BigDecimal(
                            montoTransferenciaSelect));
                } else {
                    logger.warning(
                        "El monto de la transferencia seleccionada es a Null");
                }
            }

            logger.warning("montoTransSelect : " +
                instruccionesBean.getMontoTransfrenciaSeleccionada());

            BigDecimal disponibleAtransferir = disponible.add(instruccionesBean.getMontoTransfrenciaSeleccionada());

            int res = montoTransferencia.compareTo(disponibleAtransferir);
            logger.warning("res: " + res);

            if (res == 1) {
                respuesta = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage(
                    "No se cuenta con el monto disponible para la transferencia...");
            }
        }

        logger.warning(" Termina metodo validarMontoTransferencia ");

        return respuesta;
    }

    public Boolean validarMontoTransferencia2(BigDecimal montoTransferencia) {
        logger.warning(
            " Inicia metodo validarMontoTransferencia valor recuperado->" +
            montoTransferencia);

        Boolean respuesta = Boolean.TRUE;

        InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");

        BigDecimal disponible = instruccionesBean.getDisponibleTransferir();
        logger.warning("disponible: " + disponible);
        logger.warning(" MontoTransfrenciaSeleccionada : " +
            instruccionesBean.getMontoTransfrenciaSeleccionada());

        BigDecimal disponibleAtransferir = disponible.add(instruccionesBean.getMontoTransfrenciaSeleccionada());
        logger.warning("disponibleAtransferir: " + disponibleAtransferir);

        int res = montoTransferencia.compareTo(disponibleAtransferir);
        logger.warning("res: " + res);

        if (res == 1) {
            respuesta = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage(
                "No se cuenta con el monto disponible para la transferencia....");
        }

        logger.warning(" Termina metodo validarMontoTransferencia ");

        return respuesta;
    }

    //Este metodo no se ocupa por el momento
    public BigDecimal obtenerMontoTransferenciaActual() {
        logger.warning("*Inf, Inicia metodo obtenerMontoTransferenciaActual ");

        BigDecimal montoTransferencia = BigDecimal.ZERO;

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding(
                    "obtenerTransferenciaActual");
            Row fila = (Row) operBinding.execute();

            if (fila != null) {
                logger.warning(
                    "*Inf, monto actual de la Transferencia recuperado : " +
                    fila.getAttribute("MontoTransferencia"));

                if (fila.getAttribute("MontoTransferencia") != null) {
                    montoTransferencia = (BigDecimal) fila.getAttribute(
                            "MontoTransferencia");
                }
            } else {
                logger.warning(
                    "*Inf, Current en transferencia null, monto de la transferencia null");
            }

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning(
                    "*** OperationBinding obtenerMontoTransferenciaActual devuelve errores");
            }
        } catch (Exception e) {
            logger.warning("***Error, al ejecutar el operationBinding: ", e);
        }

        logger.warning("*Inf, Termina metodo obtenerMontoTransferenciaActual ");

        return montoTransferencia;
    }

    public void cambioTipoBeneficiario(ValueChangeEvent valueChangeEvent) {
        logger.warning("*** inicia metodo cambioTipoBeneficiario");

        String tipoOpcion = (String) valueChangeEvent.getNewValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(getForm1());
    }

    public void cambioTipoBnacoBeneficiario(ValueChangeEvent valueChangeEvent) {
        logger.warning("*** inicia metodo cambioTipoBeneficiario");

        String tipoOpcion = (String) valueChangeEvent.getNewValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(getForm2());
    }

    public void cambioTipoBancoIntermediario(ValueChangeEvent valueChangeEvent) {
        logger.warning("*** inicia metodo cambioTipoBeneficiario");

        String tipoOpcion = (String) valueChangeEvent.getNewValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(getForm3());
    }

    //---------- popUp Cancelar guardado  ------------------------>
    public void confirmarCancelarGuardar(ActionEvent actionEvent) {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpCancelarGuardado().show(popupHints);
    }

    public void aceptarCancelarGuardado(ActionEvent actionEvent) {
        logger.warning("*Inf, Inicia metodo aceptarCancelarGuardado");

        InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");

        Long idDesembolso = instruccionesBean.getIdContratoDesembolso();

        if (idDesembolso != null) {
            instruccionesBean.cargarTablaTransferencias(idDesembolso);
        } else {
            logger.warning(
                "*Error, No se pudo recargar la tabla de transferencias idDesembolso resuelto a NUll");
            JSFUtils.addFacesErrorMessage(
                "Error, No se pudo recuperar el id del contrato de desembolso");
        }

        instruccionesBean.setEsNuevaTransferencia(Boolean.FALSE);
        instruccionesBean.setModoLetura(Boolean.TRUE);
        instruccionesBean.setModoLeturaDatTrans(Boolean.TRUE);
        instruccionesBean.setBtnCancelarVisible(Boolean.FALSE);
        instruccionesBean.setBtnGuardarVisible(Boolean.FALSE);

        instruccionesBean.setAccionAgregar(Boolean.TRUE);
        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getContenedorPrincipal());

        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getContenedorAccionesAInstrucciones());
        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getContenedorCamposTransferencia());
        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getFormulariosInstruccionesPago());

        getPopUpCancelarGuardado().hide();

        logger.warning("*Inf, Termina metodo aceptarCancelarGuardado");
    }

    public void aceptarCancelarGuardado2(ActionEvent actionEvent) {
        logger.warning("*** inicia metodo aceptarCancelarGuardado");

        InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");

        if (instruccionesBean.getEsNuevaTransferencia()) {
            try {
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding(
                        "cambiarTransferenciaABanEstatusCero");
                Boolean res = (Boolean) operBinding.execute();

                if (!operBinding.getErrors().isEmpty()) {
                    logger.warning(
                        "*** OperationBinding cambiarTransferenciaABanEstatusCero devuelve errores");
                }
            } catch (Exception e) {
                logger.warning(" :( Error al ejecutar el operationBinding cambiarTransferenciaABanEstatusCero : ",
                    e);
            }
        }

        Long idContrato = instruccionesBean.getIdContratoDesembolso();
        instruccionesBean.cargarTablaTransferencias(idContrato);
        instruccionesBean.setEsNuevaTransferencia(Boolean.FALSE);
        instruccionesBean.setModoLetura(Boolean.TRUE);
        instruccionesBean.setModoLeturaDatTrans(Boolean.TRUE);
        instruccionesBean.setBtnCancelarVisible(Boolean.FALSE);
        instruccionesBean.setBtnGuardarVisible(Boolean.FALSE);

        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getContenedorPrincipal());

        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getContenedorAccionesAInstrucciones());
        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getContenedorCamposTransferencia());
        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getFormulariosInstruccionesPago());

        getPopUpCancelarGuardado().hide();

        logger.warning("*** Termina metodo aceptarCancelarGuardado");
    }

    public void cerrarPopUpCancelarGuardado(ActionEvent actionEvent) {
        getPopUpCancelarGuardado().hide();
    }

    // -------- popUp CancelarEliminar ------------------>
    public void confirmarEliminarTransferencia(ActionEvent actionEvent) {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpConfirmarEliminar().show(popupHints);
    }

    public void cerrarPopUpEliminarTransferencia(ActionEvent actionEvent) {
        getPopUpConfirmarEliminar().hide();
    }

    public void aceptarEliminarTransferencia(ActionEvent actionEvent) {
        logger.warning(" inicia metodo aceptarEliminarTransferencia");

        InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                "#{pageFlowScope.InstruccionPagoBean}");
        Long idContrato = instruccionesBean.getIdContratoDesembolso();
        Long idTransferencia = instruccionesBean.getIdTransferencia();

        eliminarTransferencia(idContrato, idTransferencia);

        //Se asigna valor 0 a Monto de Transferencia para obtener el valor actual al no seleccionar un elemento en tabla.
        instruccionesBean.setMontoTransfrenciaSeleccionada(BigDecimal.ZERO);

        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getContenedorPrincipal());
        getPopUpConfirmarEliminar().hide();

        logger.warning("Termina metodo aceptarEliminarTransferencia");
    }

    public void aplicarTransferencia(ActionEvent actionEvent) {
        eventoCLick = eventoCLick + 1;

        if (eventoCLick == 1) {
            logger.warning("Inicia metodo aplicarTransferencia");
            logger.warning("Id recuperado para aplicar la transferencia ->" +
                idTransferenciaAplicar);

            InstruccionPagoBean instruccionesBean = (InstruccionPagoBean) JSFUtils.resolveExpression(
                    "#{pageFlowScope.InstruccionPagoBean}");

            logger.warning("ID transferencia: " + idTransferenciaAplicar);
            logger.warning("USUARIO: " + instruccionesBean.getUsuario());

            try {
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding(
                        "aplicarTransferencia");
                operBinding.getParamsMap()
                           .put("idTransferencia", idTransferenciaAplicar);
                operBinding.getParamsMap()
                           .put("usuario", instruccionesBean.getUsuario());
                operBinding.getParamsMap()
                           .put("instanciaProceso",
                    instruccionesBean.getInstanciaDProceso());

                operBinding.execute();

                if (!operBinding.getErrors().isEmpty()) {
                    logger.warning(
                        "*** OperationBinding aplicarTransferencia devuelve errores->" +
                        operBinding.getErrors());
                    JSFUtils.addFacesErrorMessage(
                        "Ha ocurrido un error durante la aplicacion de la transferencia. Por favor intentelo de nuevo.");
                } else {
                    bhqTransferencia = (String) operBinding.getResult();

                    if (bhqTransferencia != null) {
                        logger.warning("ok BHQ de transferencia recuperado ->" +
                            bhqTransferencia);

                        Long idContrato = instruccionesBean.getIdContratoDesembolso();
                        instruccionesBean.cargarTablaTransferencias(idContrato);
                        instruccionesBean.cargarTablaTransferencias(idContrato);
                        AdfFacesContext.getCurrentInstance()
                                       .addPartialTarget(getContenedorPrincipal());
                    } else {
                        abrirPopUpErrorAplicarTrans();
                        logger.warning(
                            "*** Error, No se pudo recuperar el BHQ de la transferencia");
                    }
                }
            } catch (Exception e) {
                logger.warning("***Error al ejecutar el operationBinding aplicarTransferencia ",
                    e);
                JSFUtils.addFacesErrorMessage(
                    "Error durante la aplicacion de la transferencia ->" +
                    e.getMessage().toString());
            }

            logger.warning("Termina metodo aplicarTransferencia");
            eventoCLick = 0;
        } else {
            logger.warning("Boton aplicar transferencia presionado:" +
                eventoCLick +
                " aun se esta ejecutando la primera accion espere");
        }
    }

    public void abrirPopUpErrorAplicarTrans() {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpErrorAplicarTransferencia().show(popupHints);
    }

    public void cerrarPopUpErrorAplicarTrans(ActionEvent actionEvent) {
        getPopUpErrorAplicarTransferencia().hide();
    }

    public Row recuperarInstruccionPagoCurrent() {
        logger.warning("*Inf, inicia metodo recuperarInstruccionPagoCurrent");

        Row fila = null;

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding(
                    "getInstruccionPagoCurrent");
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning(
                    "***Error, OperationBinding getInstruccionPagoCurrent devuelve errores" +
                    operBinding.getErrors());
            } else {
                fila = (Row) operBinding.getResult();
            }
        } catch (Exception e) {
            logger.warning(" :( Error al ejecutar el operationBinding: ", e);
        }

        logger.warning("*Inf, termina metodo recuperarInstruccionPagoCurrent");

        return fila;
    }

    public void restaurarCamposByInstruccion() {
        logger.warning("*Inf, Inicia metodo restaurarCamposByInstruccion");

        double TInicio; //Variables para determinar el tiempo de ejecución
        double TFin; //Variables para determinar el tiempo de ejecución
        double tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        Row instruccionCurrent = recuperarInstruccionPagoCurrent();

        String rowAtributetipoOpcionBenef = "OPCION_A";
        String rowAtributetipoOpcionBanInter = "OPCION_A";
        String rowAtributetipoOpcionBanBenef = "OPCION_A";

        if (instruccionCurrent != null) {
            try {
                rowAtributetipoOpcionBenef = instruccionCurrent.getAttribute(
                        "TipoOpcionBenef").toString();
                logger.warning("*Inf, tipo de opcion Beneficiario: " +
                    rowAtributetipoOpcionBenef);
            } catch (Exception e) {
                logger.warning(
                    "*inf, ocurrio un error al castear TipoOpcionBenef de instruccion de pago");
            }

            try {
                rowAtributetipoOpcionBanInter = instruccionCurrent.getAttribute(
                        "TipoOpcionBanInter").toString();
                logger.warning("*Inf, tipo de ocpion Banco intermediario: " +
                    rowAtributetipoOpcionBanInter);
            } catch (Exception e) {
                logger.warning(
                    "*inf, ocurrio un error al castear TipoOpcionBanInter de instruccion de pago");
            }

            try {
                rowAtributetipoOpcionBanBenef = instruccionCurrent.getAttribute(
                        "TipoOpcionBanBenef").toString();
                logger.warning("*Inf, tipo de ocpion Banco Beneficiario: " +
                    rowAtributetipoOpcionBanBenef);
            } catch (Exception e) {
                logger.warning(
                    "*inf, ocurrio un error al castear TipoOpcionBanBenef de instruccion de pago");
            }
        }

        AdfFacesContext.getCurrentInstance()
                       .addPartialTarget(getFormulariosInstruccionesPago());

        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning(
            "*Inf, Termina metodo restaurarCamposByInstruccion con una duracion de: " +
            tiempo + " segundos");
    }

    public void validarCampoNumCuentaTrans(ClientEvent ce) {
        logger.warning("*Inf, Inicia metodo validarCampoNumCuentaTrans");

        String numeroCuenta = (String) ADFUtils.getBoundAttributeValue(
                "NumeroCtaBenef");
        numeroCuenta = (null == numeroCuenta) ? null : numeroCuenta.trim();

        if ((null == numeroCuenta) || numeroCuenta.equals("")) {
            ADFUtils.setBoundAttributeValue("NumeroCtaBenef", "/");
        } else {
            if (numeroCuenta.substring(0, 1).equals("/")) {
                logger.warning("*Inf,ok la cadena ya cuenta con el caracter /");
            } else {
                logger.warning(
                    "*Inf, agregando el caracter / al numero de cuenta");
                numeroCuenta = "/" + numeroCuenta;
                ADFUtils.setBoundAttributeValue("NumeroCtaBenef", numeroCuenta);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getForm2());
            }
        }

        logger.warning("*Inf, termina metodo validarCampoNumCuentaTrans");
    }

    public void validarCampoNumCuentaBanBeneficiario(ClientEvent ce) {
        logger.warning(
            "*Inf, Inicia metodo validarCampoNumCuentaBanBeneficiario");

        String numeroCuenta = (String) ADFUtils.getBoundAttributeValue(
                "NumeroCuentaBancoBeneficiario");
        numeroCuenta = (null == numeroCuenta) ? null : numeroCuenta.trim();

        if ((numeroCuenta == null) || numeroCuenta.equals("")) {
            ADFUtils.setBoundAttributeValue("NumeroCuentaBancoBeneficiario", "/");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getForm2());
        } else {
            if (numeroCuenta.substring(0, 1).equals("/")) {
                logger.warning("*Inf,ok la cadena ya cuenta con el caracter /");
            } else {
                logger.warning(
                    "*Inf, agregando el caracter / al numero de cuenta");
                numeroCuenta = "/" + numeroCuenta;
                ADFUtils.setBoundAttributeValue("NumeroCuentaBancoBeneficiario",
                    numeroCuenta);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getForm2());
            }
        }

        logger.warning(
            "*Inf, termina metodo validarCampoNumCuentaBanBeneficiario");
    }

    public void validarCampoNumCuentaBanIntermediario(ClientEvent ce) {
        logger.warning(
            "*Inf, Inicia metodo validarCampoNumCuentaBanIntermediario");

        String numeroCuenta = (String) ADFUtils.getBoundAttributeValue(
                "NumeroCuentaBancoIntermediario");
        numeroCuenta = (null == numeroCuenta) ? null : numeroCuenta.trim();

        if ((numeroCuenta == null) || numeroCuenta.equals("")) {
            ADFUtils.setBoundAttributeValue("NumeroCuentaBancoIntermediario",
                "/");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getForm3());
        } else {
            if (numeroCuenta.substring(0, 1).equals("/")) {
                logger.warning("*Inf,ok la cadena ya cuenta con el caracter /");
            } else {
                logger.warning(
                    "*Inf, agregando el caracter / al numero de cuenta");
                numeroCuenta = "/" + numeroCuenta;
                ADFUtils.setBoundAttributeValue("NumeroCuentaBancoIntermediario",
                    numeroCuenta);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getForm3());
            }
        }

        logger.warning(
            "*Inf, termina metodo validarCampoNumCuentaBanIntermediario");
    }

    /********      Accesors       *****/
    public void setFormulariosInstruccionesPago(
        RichPanelGroupLayout formulariosInstruccionesPago) {
        this.formulariosInstruccionesPago = formulariosInstruccionesPago;
    }

    public RichPanelGroupLayout getFormulariosInstruccionesPago() {
        return formulariosInstruccionesPago;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void cambioTipoMensaje(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inicia metodo cambioTipoMensaje.");

        String tipoMensaje = "";
        String TipoOpcionBeneficiario = (String) ADFUtils.getBoundAttributeValue(
                "TipoOpcionBeneficiario");
        String TipoOpcionBancoBeneficiario = (String) ADFUtils.getBoundAttributeValue(
                "TipoOpcionBancoBeneficiario");
        String TipoOpcionBancoIntermediario = (String) ADFUtils.getBoundAttributeValue(
                "TipoOpcionBancoIntermediario");

        if (null != valueChangeEvent) {
            try {
                tipoMensaje = (String) valueChangeEvent.getNewValue();
                logger.warning("TipoMensajeAttrValue: " + tipoMensaje);
            } catch (Exception e) {
                logger.warning("ERROR al recuperar tipoMensaje de ValueChangeEvent.",
                    e);
            }
        }

        try {
            if (tipoMensaje.equals("MT202")) {
                ADFUtils.setBoundAttributeValue("TipoOpcionBeneficiario",
                    "OPCION_A");
                logger.warning("TipoOpcionBeneficiario OPCION_A");
            } else if (tipoMensaje.equals("MT103")) {
                ADFUtils.setBoundAttributeValue("TipoOpcionBeneficiario",
                    "OPCION_D");
                logger.warning("TipoOpcionBeneficiario OPCION_D");
            } else {
                logger.warning("TipoOpcionBeneficiariom ninguna opcion");
            }
        } catch (Exception e) {
            logger.warning("ERROR al asingnar TipoMensajeAttrValue.");
        }

        logger.warning("tipoMensaje" + tipoMensaje);
        AdfFacesContext.getCurrentInstance().addPartialTarget(getForm1());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getForm2());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getForm3());

        logger.warning("Termina metodo cambioTipoMensaje.");
    }

    public void setContenedorCamposTransferencia(
        RichPanelGroupLayout contenedorCamposTransferencia) {
        this.contenedorCamposTransferencia = contenedorCamposTransferencia;
    }

    public RichPanelGroupLayout getContenedorCamposTransferencia() {
        return contenedorCamposTransferencia;
    }

    public void setContenedorPrincipal(RichPanelGroupLayout contenedorPrincipal) {
        this.contenedorPrincipal = contenedorPrincipal;
    }

    public RichPanelGroupLayout getContenedorPrincipal() {
        return contenedorPrincipal;
    }

    public void setContenedorAccionesTransaccionesPago(
        RichPanelGridLayout contenedorAccionesTransaccionesPago) {
        this.contenedorAccionesTransaccionesPago = contenedorAccionesTransaccionesPago;
    }

    public RichPanelGridLayout getContenedorAccionesTransaccionesPago() {
        return contenedorAccionesTransaccionesPago;
    }

    public void setContenedorAccionesTransferencia(
        RichPanelGroupLayout contenedorAccionesTransferencia) {
        this.contenedorAccionesTransferencia = contenedorAccionesTransferencia;
    }

    public RichPanelGroupLayout getContenedorAccionesTransferencia() {
        return contenedorAccionesTransferencia;
    }

    public void setContenedorAccionesAInstrucciones(
        RichPanelGroupLayout contenedorAccionesAInstrucciones) {
        this.contenedorAccionesAInstrucciones = contenedorAccionesAInstrucciones;
    }

    public RichPanelGroupLayout getContenedorAccionesAInstrucciones() {
        return contenedorAccionesAInstrucciones;
    }

    public void setPopUpMensajes(RichPopup popUpMensajes) {
        this.popUpMensajes = popUpMensajes;
    }

    public RichPopup getPopUpMensajes() {
        return popUpMensajes;
    }

    public void setPopUpConfirmarEliminar(RichPopup popUpConfirmarEliminar) {
        this.popUpConfirmarEliminar = popUpConfirmarEliminar;
    }

    public RichPopup getPopUpConfirmarEliminar() {
        return popUpConfirmarEliminar;
    }

    public void setPopUpCancelarGuardado(RichPopup popUpCancelarGuardado) {
        this.popUpCancelarGuardado = popUpCancelarGuardado;
    }

    public RichPopup getPopUpCancelarGuardado() {
        return popUpCancelarGuardado;
    }

    public void setIdTransferenciaAplicar(String idTransferenciaAplicar) {
        this.idTransferenciaAplicar = idTransferenciaAplicar;
    }

    public String getIdTransferenciaAplicar() {
        return idTransferenciaAplicar;
    }

    public void setBhqTransferencia(String bhqTransferencia) {
        this.bhqTransferencia = bhqTransferencia;
    }

    public String getBhqTransferencia() {
        return bhqTransferencia;
    }

    public void setPopUpErrorAplicarTransferencia(
        RichPopup popUpErrorAplicarTransferencia) {
        this.popUpErrorAplicarTransferencia = popUpErrorAplicarTransferencia;
    }

    public RichPopup getPopUpErrorAplicarTransferencia() {
        return popUpErrorAplicarTransferencia;
    }

    public void setPopUpEliminarInstruccion(RichPopup popUpEliminarInstruccion) {
        this.popUpEliminarInstruccion = popUpEliminarInstruccion;
    }

    public RichPopup getPopUpEliminarInstruccion() {
        return popUpEliminarInstruccion;
    }

    public void setPopUpErroresInstruccion(RichPopup popUpErroresInstruccion) {
        this.popUpErroresInstruccion = popUpErroresInstruccion;
    }

    public RichPopup getPopUpErroresInstruccion() {
        return popUpErroresInstruccion;
    }

    public void setForm1(RichPanelGroupLayout form1) {
        this.form1 = form1;
    }

    public RichPanelGroupLayout getForm1() {
        return form1;
    }

    public void setForm2(RichPanelGroupLayout form2) {
        this.form2 = form2;
    }

    public RichPanelGroupLayout getForm2() {
        return form2;
    }

    public void setForm3(RichPanelGroupLayout form3) {
        this.form3 = form3;
    }

    public RichPanelGroupLayout getForm3() {
        return form3;
    }
}
