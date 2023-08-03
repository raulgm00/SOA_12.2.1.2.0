package org.bcie.fenix.view.cuestionarios;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.bcie.evaluacionmo.CrearEvaluacionResponseType;
import org.bcie.evaluacionmo.EliminarCuestionarioResponseType;
import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class CuestionarioActionsBean implements Serializable {

    @SuppressWarnings("compatibility:-1954446446941586280")
    private static final long serialVersionUID = 1L;
    private static ADFLogger logger = null;
    public static final String BUNDLE = "org.bcie.fenix.view.AdministradorCuestionariosVCBundle";


    public CuestionarioActionsBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }


    }


    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (resourceBundle != null) {
            txt = resourceBundle.getString(psKey);
        }

        return txt;
    }

    /**
     *Método que se ejecuta cuando el valor del selectChoice de Sector cambia
     * @param valueChangeEvent
     */
    public void sectoresValueChangeEvent(ValueChangeEvent valueChangeEvent) {
        logger.warning("Entra en sectoresValueChangeEvent.");
        //logger.warning("Inside cargaCuestionariosValueChangeListener: " + valueChangeEvent.getComponent().getId());

        //Refrescamos el valor que se selecciono en el combo de Sector
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String valueSelec = valueChangeEvent.getNewValue().toString();
        System.out.println("valueChangeEvent.Value = " + valueSelec);

        //Recuperamos el Bean cuestionarios
        CuestionariosBean cuestionariosBean = (CuestionariosBean) JSFUtils.resolveExpression("#{pageFlowScope.CuestionariosManagedBean}");
        //Validamos el valor seleccionado en el combo Sector

        Integer IdSector = getSingleKeyFromValue(cuestionariosBean.getMapSectorIbcie(), valueSelec);
        System.out.println(" CuestionariosBean ===> ID Sector " + IdSector);
        System.out.println(" CuestionariosBean ===> Des Sector " + valueSelec);

        if ((valueChangeEvent.getNewValue() == null) || (valueChangeEvent.getNewValue().toString().trim().length() == 0)) {

            cuestionariosBean.setIdSector(null);
        } else {
            
            logger.warning("Asignamos el valor de Sector en el Bean de cuestionarios.");
            //Asignamos el valor de Sector en el Bean de cuestionarios
            setSectorEnBean(IdSector,valueSelec );
            
            if (consultaCuestionariosAction()) {
                //Mostramos la tabla de cuestionarios
                cuestionariosBean.setMuestraCuestionarios(true);
            } else {
                //Si la OP es candita == false (Significa que es multisectorial) debe esperar a mandar el mesnaje de advertencia para dar oportunidad a seleccionar el Combo No.2 SubSector
                if(  cuestionariosBean.isBanPrecarga()  )
                {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("GENERAR_CUESTIONARIOS_MSG02"));
                    
                }else{
                    cuestionariosBean.setBanPrecarga(Boolean.TRUE);
                }
                //Ocultamos la tabla de cuestionarios
                cuestionariosBean.setMuestraCuestionarios(false);
                
                
            }
        }


    }

    public void imprimirMapa(Map<Integer, String> map, String value) {
        System.out.println("=== Key ===");
        System.out.println(getSingleKeyFromValue(map, value));
        System.out.println("=== End Key ===");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(" ======================== " + entry.getKey() + " ================================== ");
            System.out.println(entry.getValue().toString());
        }

    }

    /**
     * Método que agrega el id del Sector seleccionado en el Bean
     */
    public void setSectorEnBean(Integer sector, String descripcion) {
        /**
        //Recuperamos el bean de cuestionarios
        
        //Recupera el iterador de Sector
        DCIteratorBinding voCatSectorIterator =
            ADFUtils.getDCBindingContainer().findIteratorBinding("CatSectorEvaluacionVOIterator");
        //Tomanos el row seleccionado en vista
        Row currentRowSector = voCatSectorIterator.getCurrentRow();
        //Asignamos los valores idSector y descripcionSector al bean cuestionarios
        System.out.println("=== === > ID Sector " + new Integer(currentRowSector.getAttribute("CodigoSectorIbcie").toString()));
        System.out.println("=== === > Des Sector " + currentRowSector.getAttribute("Descripcion").toString());
        **/
        CuestionariosBean cuestionariosBean = (CuestionariosBean) JSFUtils.resolveExpression("#{pageFlowScope.CuestionariosManagedBean}");
        cuestionariosBean.setIdSector(sector);
        cuestionariosBean.setDescripcionSector(descripcion);
    }


    /**
     * Metodo que consulta los cuestionarios por idSector, idSubSector, idOperacion
     * @return
     */
    @SuppressWarnings("unchecked")
    public Boolean consultaCuestionariosAction() {
        logger.warning("Entra en consultaCuestionariosAction.");
        Integer rowsCuestionarios = null;
        boolean resultadoConsultaCuestionarios = false;
        //Recuperamos el bean de cuestionarios
        CuestionariosBean cuestionariosBean = (CuestionariosBean) JSFUtils.resolveExpression("#{pageFlowScope.CuestionariosManagedBean}");
        
        if(  cuestionariosBean.getIdSector() > 0 && cuestionariosBean.getIdSubSector() > 0 ){
            BindingContainer bindings = getBindings();
            //Invocamos el metodo consultarCuestionarios(String idSector, String idSubSector, int idOperacion)
            OperationBinding operationBinding = bindings.getOperationBinding("consultarCuestionarios");
            //Mandamos los parametro idOperacion,idSector,idSubSector al metodo
            //consultarCuestionarios(String idSector, String idSubSector, int idOperacion)
            System.out.println("=== === === > ID Op " + cuestionariosBean.getIdOperacion());
            System.out.println("=== === === > ID Sector " + cuestionariosBean.getIdSector());
            System.out.println("=== === === > ID Sub-Sector " + cuestionariosBean.getIdSubSector());

            
            operationBinding.getParamsMap().put("idOperacion", cuestionariosBean.getIdOperacion());
            operationBinding.getParamsMap().put("idSector", cuestionariosBean.getIdSector());
            operationBinding.getParamsMap().put("idSubSector", cuestionariosBean.getIdSubSector());
            //Ejecutamos el metodo consultarCuestionarios(String idSector, String idSubSector, int idOperacion)
            Object result = operationBinding.execute();
            //Se recupera la respuesta del metodo
            //consultarCuestionarios(String idSector, String idSubSector, int idOperacion)
            if (null != operationBinding.getResult()) {
                rowsCuestionarios = (Integer) operationBinding.getResult();
            } else {
                logger.warning("El numero de rows es nulo.");
            }
            logger.warning("rowsCuestionarios : " + rowsCuestionarios);
            //validamos que  la consulta retorne mas de un Row
            if (null != rowsCuestionarios && rowsCuestionarios > 0) {
                //Consulta con Rows
                resultadoConsultaCuestionarios = true;
            } else {
                //Consulta vacia
                resultadoConsultaCuestionarios = false;
            }
        }else{
            resultadoConsultaCuestionarios = false;
        }
        
        return resultadoConsultaCuestionarios;
    }


    /**
     *ValueChangeValue SubSectores
     * @param valueChangeEvent
     */
    public void subSectoresValueChangeValue(ValueChangeEvent valueChangeEvent) {
        //Refresca el row seleccionado en la vista
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String valueSelec = valueChangeEvent.getNewValue().toString();
        System.out.println("valueChangeEvent.Value = " + valueSelec);
        //Recupera el bean cuestionarios
        CuestionariosBean cuestionariosBean = (CuestionariosBean) JSFUtils.resolveExpression("#{pageFlowScope.CuestionariosManagedBean}");
        Integer IdSubSector = getSingleKeyFromValue(cuestionariosBean.getMapSubSectorIbcie(), valueSelec);
        System.out.println(" CuestionariosBean ===> ID Sub Sector " + IdSubSector);
        System.out.println(" CuestionariosBean ===> Des Sub Sector " + valueSelec);
        //validamos  el valor que se seleccion en el combo subSector
        if ((valueChangeEvent.getNewValue() == null) || (valueChangeEvent.getNewValue().toString().trim().length() == 0)) {

            cuestionariosBean.setIdSubSector(null);
        } else {
            //Invoca el metodo que asigna el valor del SubSector en el bean de cuestionarios
            setSubSectorEnBean(IdSubSector,valueSelec );
            if (consultaCuestionariosAction()) {
                //Muestra la tabla de cuestionarios
                cuestionariosBean.setMuestraCuestionarios(true);
            } else {
                
                //Si la OP es candita == false (Significa que es multisectorial) debe esperar a mandar el mesnaje de advertencia para dar oportunidad a seleccionar el Combo No.1 Sector
                if(  cuestionariosBean.isBanPrecarga()  )
                {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("GENERAR_CUESTIONARIOS_MSG02"));
                    
                }else{
                    cuestionariosBean.setBanPrecarga(Boolean.TRUE);
                }
                //Ocultamos la tabla de cuestionarios
                cuestionariosBean.setMuestraCuestionarios(false);
            }
        }
    }

    /**
     * Metodo que agrega el id del SubSector al bean
     */
    public void setSubSectorEnBean(Integer idSubSector, String descripcion) {
        //Recuperamos el Bean de cuestionarios
        CuestionariosBean cuestionariosBean = (CuestionariosBean) JSFUtils.resolveExpression("#{pageFlowScope.CuestionariosManagedBean}");
        /**
        //Recupera el iterador del SubSector
        DCIteratorBinding voSubSectorIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("CatSubSectorEvaluacionVOIterator");
        //Se toma el Row seleccionado en vista
        Row currentRowSubSector = voSubSectorIterator.getCurrentRow();
        //Asignamos los valores idSubSector y descripcionSubSector en el Bean de cuestionarios
        */
        
        cuestionariosBean.setIdSubSector(  idSubSector );
        cuestionariosBean.setDescripcionSubSector( descripcion);
    }


    public void consultaEvaluacionesAction() {
        HashMap<String, CrearEvaluacionResponseType> respuestaServicio = null;
        CrearEvaluacionResponseType response = new CrearEvaluacionResponseType();
        BindingContainer bindings = getBindings();
        //recuperamos el bean de cuestionarios
        CuestionariosBean cuestionariosBean = (CuestionariosBean) JSFUtils.resolveExpression("#{pageFlowScope.CuestionariosManagedBean}");
        DCIteratorBinding voCuestionarioIterator;
        Map<String, Object> mapEvaluacion = new HashMap<String, Object>();
        List<Long> lista = new ArrayList<Long>();
        //Invoca el metodo crearEvaluacion(Map<String,Object> mapEvaluacion) declarado en el AM
        OperationBinding operationBinding = bindings.getOperationBinding("crearEvaluacion");
        //Recupera el iterador de CuestionarioVO
        voCuestionarioIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("CuestionarioVOIterator");
        ViewObject voCuestionario = voCuestionarioIterator.getViewObject();
        //Filtramos todos los cuestionarios que esten seleccionados en al vista, para saber cual vamos a generar por medio de WS
        Row[] cuestionariosSeleccionadosRows = voCuestionario.getFilteredRows("seleccionado", true);
        for (Row row : cuestionariosSeleccionadosRows) {
            Integer idCuestionario;
            idCuestionario = (Integer) row.getAttribute("idCuestionario");
            lista.add(Long.valueOf(idCuestionario));
        }
        
        if (lista.size() != 0) {
            //Se asginan los parametros para el metodo crearEvaluacion(Map<String,Object> mapEvaluacion)
            System.out.println("consultaEvaluacionesAction.idOperacion =" + cuestionariosBean.getIdOperacion());
            mapEvaluacion.put("idOperacion", cuestionariosBean.getIdOperacion());
            System.out.println("consultaEvaluacionesAction.idSector =" + cuestionariosBean.getIdSector() );
            mapEvaluacion.put("idSector", cuestionariosBean.getIdSector());
            System.out.println("consultaEvaluacionesAction.descripcionSector =" + cuestionariosBean.getDescripcionSector() );
            mapEvaluacion.put("descripcionSector", cuestionariosBean.getDescripcionSector());
            System.out.println("consultaEvaluacionesAction.idSubsector =" + cuestionariosBean.getIdSubSector());
            mapEvaluacion.put("idSubsector", cuestionariosBean.getIdSubSector());
            System.out.println("consultaEvaluacionesAction.descripcionSubSector =" + cuestionariosBean.getDescripcionSubSector() );
            mapEvaluacion.put("descripcionSubSector", cuestionariosBean.getDescripcionSubSector());
            System.out.println("consultaEvaluacionesAction.cuestionarios =" + lista);
            mapEvaluacion.put("cuestionarios", lista);
            System.out.println("consultaEvaluacionesAction.enAnalisis =" + 1);
            mapEvaluacion.put("enAnalisis", 1);
            System.out.println("consultaEvaluacionesAction.version =" + 0);
            mapEvaluacion.put("version", 0);
            //Se agregan nuevos parametros
            System.out.println("consultaEvaluacionesAction.tipoEvaluacion =" + cuestionariosBean.getEventoEvaluacion());
            mapEvaluacion.put("tipoEvaluacion", cuestionariosBean.getEventoEvaluacion());
            System.out.println("consultaEvaluacionesAction.instanciaProceso =" + cuestionariosBean.getInstanciaProceso() );
            /**
             * Modificacion para muestreo de tabla con registros relacioados feature 5003
             */
            mapEvaluacion.put("instanciaProceso", cuestionariosBean.getInstanciaProceso());
            /*mapEvaluacion.put("instanciaProceso", null);*/
            System.out.println("Steo de consultaEvaluacionesAction.instanciaProceso = null"  );
            //Se recupera el usuario en sesión
            System.out.println("consultaEvaluacionesAction.usuario =" + JSFUtils.resolveExpression("#{pageFlowScope.pLoginUsuario}") );
            mapEvaluacion.put("usuario", JSFUtils.resolveExpression("#{pageFlowScope.pLoginUsuario}"));
            operationBinding.getParamsMap().put("mapEvaluacion", mapEvaluacion);
            //Ejecuta el metodo crearEvaluacion(Map<String,Object> mapEvaluacion){
            operationBinding.execute();
            if (operationBinding.getErrors().size() != 0) {
                // Manejo de errores
                

            } else {


                if (operationBinding.getResult() != null) {
                    respuestaServicio = (HashMap<String, CrearEvaluacionResponseType>) operationBinding.getResult();
                    response = respuestaServicio.get("response");
                    //valida si el resultado del servicio SOA crearEvaluacion es OK
                    if (response.getResultado().getResult().toString().equals("OK")) {
                        
                        //invoca el metodo para regresar Sector y SubSector a su estado original
                        restablecerSelectOneChoice();
                        //Invocamos el metodo pra consultar las evaluaciones
                        consultaEvaluaciones();
                        //Mostramos la tabla de evaluaciones
                        cuestionariosBean.setMuestraEvaluaciones(true);
                        //Ocultamos los cuestionarios
                        //if(cuestionariosBean.){
                            cuestionariosBean.setMuestraCuestionarios(true);
                        //}else{
                        //    cuestionariosBean.setMuestraCuestionarios(false);
                        //}
                        //Muestra el mensaje que regresa el servicio SOA crearEvaluacion
                        JSFUtils.addFacesInformationMessage(response.getResultado().getResult() + " " + response.getResultado().getMessage());
                        
                        
                    } else {
                        //invoca el metodo para regresar Sector y SubSector a su estado original
                        restablecerSelectOneChoice();
                        //Ocultamos los cuestionarios
                        cuestionariosBean.setMuestraCuestionarios(true);
                        //Muestra el mensaje que regresa el servicio SOA crearEvaluacion
                        JSFUtils.addFacesErrorMessage(response.getResultado().getResult() + " " + response.getResultado().getMessage());
                        
                    }
                } else {
                    //Muestra mensajes de error con la respuesta del servicio SOA crearEvaluacion
                    JSFUtils.addFacesErrorMessage("No se tiene respuesta del servicio - CrearEvaluacion ");
                    //Ocultamos los cuestionarios
                    cuestionariosBean.setMuestraCuestionarios(false);
                }

            }

        } else {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("GENERAR_CUESTIONARIOS_MSG01"));
        }


    }
    /**
     * Metodo que ejecuta la generacion del cuestionario
     * Execute No.1
     */
    public void restablecerSelectOneChoice() {
        //Recupera los iteradores de Sector y SubSector (combos sector y subSector)
        DCIteratorBinding voCatSectorIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("CatSectorEvaluacionVOIterator");
        DCIteratorBinding voCatSubSectorIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("CatSubSectorEvaluacionVOIterator");
        //Ejecutamos el query para que retome sus valores iniciales
        voCatSectorIterator.executeQuery();
        voCatSubSectorIterator.executeQuery();
    }

    public String getEnlacePagina() {
        logger.log(ADFLogger.WARNING, "Inside getEnlacePagina");
        AttributeBinding idEvaluacion;
        idEvaluacion = ADFUtils.findControlBinding("idEvaluacion");
        AttributeBinding codigoModelo;
        oracle.jbo.domain.Number valorAgregado;
        oracle.jbo.domain.Number valorComparar = null;
        Long nuevoValor = 0L;
        codigoModelo = ADFUtils.findControlBinding("codigoExterno");

        DCIteratorBinding configuracionVOIterator = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
        ViewObject configuracionVoImpl = configuracionVOIterator.getViewObject();
        Row row = configuracionVoImpl.getRow(new Key(new Object[] { "URL_APP_EXTERNA_EVAL" }));
        String ruta = row == null ? null : (String) row.getAttribute("Valor");

        if (valorComparar != (oracle.jbo.domain.Number) idEvaluacion.getInputValue()) {
            valorAgregado = (oracle.jbo.domain.Number) idEvaluacion.getInputValue();
            nuevoValor = valorAgregado.longValue();
            ruta = ruta.concat("?CodigoEvaluacion=");
            ruta = ruta.concat(Long.toString(nuevoValor));
        }
        if (null != (String) codigoModelo.getInputValue()) {
            //valorAgregado=(oracle.jbo.domain.Number) idCuestionario.getInputValue();
            //nuevoValor=valorAgregado.longValue();
            ruta = ruta.concat("&CodigoModelo=");
            ruta = ruta.concat((String) codigoModelo.getInputValue());
        }
        logger.log(ADFLogger.WARNING, "valor de url : " + ruta);
        return ruta;
    }
    
    /**
     * Metodo que ejecuta la generacion del cuestionario
     * Execute No.2
     */
    public String consultaEvaluaciones() {
        //Recupera en Bean de cuestionarios
        CuestionariosBean evaluacionesBean = (CuestionariosBean) JSFUtils.resolveExpression("#{pageFlowScope.CuestionariosManagedBean}");

        Long eventoEvaluacion = null;
        String instanciaproceso = null;
        String idOperacion = null;
        String tipoEvaluacion = null;
        String idTarea = null;

        if (null != evaluacionesBean.getEventoEvaluacion()) {
            eventoEvaluacion = new Long(evaluacionesBean.getEventoEvaluacion());
        } else {
            logger.log(ADFLogger.WARNING, "El valor del evento evaluacion es nulo.");
        }
        if (null != evaluacionesBean.getInstanciaProceso()) {
            instanciaproceso = evaluacionesBean.getInstanciaProceso();
        } else {
            logger.log(ADFLogger.WARNING, "El valor de la instancia del proceso es nulo.");
        }
        if (null != evaluacionesBean.getIdOperacion()) {
            idOperacion = evaluacionesBean.getIdOperacion();
        } else {
            logger.log(ADFLogger.WARNING, "El valor de la operacion es nulo.");
        }
        if (null != evaluacionesBean.getTipoEvaluacion()) {
            tipoEvaluacion = evaluacionesBean.getTipoEvaluacion();
        } else {
            logger.log(ADFLogger.WARNING, "El valor del tipo de evaluacion es nulo.");
        }
        if (null != evaluacionesBean.getIdTarea()) {
            idTarea = evaluacionesBean.getIdTarea();
        } else {
            logger.log(ADFLogger.WARNING, "El id de la tarea es nula.");
        }

        BindingContainer bindings = getBindings();

        if (idTarea != null && idTarea.trim().length() > 0) {
            
            if (idTarea.compareTo(FenixConstants.PC06_VALIDAR_CUESTIONARIO_ODE_GENERAR_INFORME_IBCIE) == 0) {
                logger.log(ADFLogger.WARNING, "Entra en metodo para duplicar cuestionario.");

                //Invocar el metodo consultarEvaluacionDuplicado
                OperationBinding operationBinding = bindings.getOperationBinding("consultarEvaluacionDuplicado");
                //Enviamos los parametros para el metodo consultarEvaluacion idOperacion y tipoEvaluacion(SIEMAS o IBCIE)
                operationBinding.getParamsMap().put("idOperacion", Long.parseLong(idOperacion));
                operationBinding.getParamsMap().put("idTarea", Long.parseLong(idTarea));
                operationBinding.getParamsMap().put("tipoEvaluacion", tipoEvaluacion);
                operationBinding.getParamsMap().put("instanciaProceso", instanciaproceso);
                operationBinding.getParamsMap().put("eventoEvaluacion", eventoEvaluacion);

                //ejecutamos el metodo consultarEvaluacionDuplicado
                Object result = operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    return null;
                }
            } else {
                //Invocar el metodo consultarEvaluacion(Long idOperacion,String tipoEvaluacion)
                OperationBinding operationBinding = bindings.getOperationBinding("consultarEvaluacion");
                //Enviamos los parametros para el metodo consultarEvaluacion idOperacion y tipoEvaluacion(SIEMAS o IBCIE)
                operationBinding.getParamsMap().put("idOperacion", Long.parseLong(idOperacion));
                operationBinding.getParamsMap().put("idTarea", Long.parseLong(idTarea));
                operationBinding.getParamsMap().put("tipoEvaluacion", tipoEvaluacion);
                operationBinding.getParamsMap().put("instanciaProceso", instanciaproceso);
                operationBinding.getParamsMap().put("eventoEvaluacion", eventoEvaluacion);
                //ejecutamos el metodo consultarEvaluacion(Long idOperacion,String tipoEvaluacion)
                Object result = operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    return null;
                }
            }
        } else {
            logger.log(ADFLogger.WARNING, "El id de tarea es nulo.");
        }
        //Retorna null, no redirecciona a ninguna pagina
        return null;
    }

    public String eliminaCuestionarioEvaluacion() {
        logger.warning("Entra en eliminaCuestionarioEvaluacion");
        BindingContainer bindings = getBindings();
        //Metodo eliminarCuestionarioEvaluacion(Long idEvaluacion, String codigoExterno,String nombreUsuario)
        //declarado en el AM
        OperationBinding operationBinding = bindings.getOperationBinding("eliminarCuestionarioEvaluacion");
        HashMap<String, EliminarCuestionarioResponseType> respuestaServicio = null;
        EliminarCuestionarioResponseType response = new EliminarCuestionarioResponseType();
        //Recuperar el iterador y tomar el Row seleccionado en la vista
        DCIteratorBinding voEvaluacionIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("EvaluacionVOIterator");
        Row currentRowEvaluacion = voEvaluacionIterator.getCurrentRow();
        //Recuperar el idEvaluacion y codigoExterno, son necesarios para el servicio SOA EliminarCuestionario
        operationBinding.getParamsMap().put("idEvaluacion",new Long(currentRowEvaluacion.getAttribute("idEvaluacion").toString()));
        operationBinding.getParamsMap().put("codigoExterno",new Long(currentRowEvaluacion.getAttribute("codigoExterno").toString()));
        //Recuperar el usuario en sesión
        operationBinding.getParamsMap().put("nombreUsuario", JSFUtils.resolveExpression("#{pageFlowScope.pLoginUsuario}"));
        //Ejecutar el metodo eliminarCuestionarioEvaluacion(Long idEvaluacion, String codigoExterno,String nombreUsuario)
        operationBinding.execute();
        if (operationBinding.getErrors().size() != 0) {
            // Manejo de errores

        } else if (operationBinding.getResult() != null) {
            respuestaServicio = (HashMap<String, EliminarCuestionarioResponseType>) operationBinding.getResult();
            response = respuestaServicio.get("response");
            if (response.getResultado() != null) {
                //Muestra el mensaje de la respuesta del servicio SOA EliminarCuestionario
                if (response.getResultado().getResult().toString() == "ERROR") {
                    //Muestra la descripcion del "ERROR"
                    JSFUtils.addFacesErrorMessage(response.getResultado().getError().getErrorDescription());

                } else {
                    logger.log(ADFLogger.WARNING, "El resultado del servicio es correcto.");
                    //Muestra la descripcion del "OK"
                    JSFUtils.addFacesInformationMessage(response.getResultado().getMessage());
                    operationBinding = null;
                    operationBinding = bindings.getOperationBinding("eliminarCuestionario");
                    operationBinding.execute();
                    //Consultamos evaluaciones nuevamente
                    consultaEvaluaciones();
                }

            } else {
                //Muestra mensaje de error
                JSFUtils.addFacesErrorMessage("Error en la respuesta del servicio EliminarCuestionario");

            }
        }
        //return null, por que no redirecciona a ninguna pagina
        return null;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public <K, V> K getSingleKeyFromValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

}
