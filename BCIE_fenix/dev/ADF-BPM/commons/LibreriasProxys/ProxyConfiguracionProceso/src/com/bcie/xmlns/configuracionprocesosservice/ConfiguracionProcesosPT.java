
package com.bcie.xmlns.configuracionprocesosservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import org.bcie.configuracionprocesosmo.ConfiguracionAdministracionClienteRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionAdministracionClienteResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionAdministracionOperacionRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionAdministracionOperacionResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionAdquisicionRequestType;
import org.bcie.configuracionprocesosmo.ConfiguracionAdquisicionResponseType;
import org.bcie.configuracionprocesosmo.ConfiguracionAnalisisRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionAnalisisResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionAprobacionClienteRequestType;
import org.bcie.configuracionprocesosmo.ConfiguracionAprobacionClienteResponseType;
import org.bcie.configuracionprocesosmo.ConfiguracionAprobacionRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionAprobacionResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionCancelarOperacionRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionCancelarOperacionResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionCierreFideicomisoRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionCierreFideicomisoResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionCierreRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionCierreResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionComisionesRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionComisionesResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionCondicionesRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionCondicionesResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionContratacionFormalizacionRequestType;
import org.bcie.configuracionprocesosmo.ConfiguracionContratacionFormalizacionResponseType;
import org.bcie.configuracionprocesosmo.ConfiguracionDesembolsoExcepcionRequestType;
import org.bcie.configuracionprocesosmo.ConfiguracionDesembolsoExcepcionResponseType;
import org.bcie.configuracionprocesosmo.ConfiguracionDesembolsoRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionDesembolsoResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionElegibilidadRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionElegibilidadResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionEnmiendasRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionEnmiendasResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionEnviarGastoRequestType;
import org.bcie.configuracionprocesosmo.ConfiguracionEnviarGastoResponseType;
import org.bcie.configuracionprocesosmo.ConfiguracionEvaluacionRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionEvaluacionResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionFormalizacionEnmiendasRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionFormalizacionEnmiendasResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionFormalizacionRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionFormalizacionResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionGestionCobroRequestType;
import org.bcie.configuracionprocesosmo.ConfiguracionGestionCobroResponseType;
import org.bcie.configuracionprocesosmo.ConfiguracionImplementacionPCTRequestType;
import org.bcie.configuracionprocesosmo.ConfiguracionImplementacionPCTResponseType;
import org.bcie.configuracionprocesosmo.ConfiguracionInteresesCobroRequestType;
import org.bcie.configuracionprocesosmo.ConfiguracionInteresesCobroResponseType;
import org.bcie.configuracionprocesosmo.ConfiguracionLavadoActivosRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionLavadoActivosResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionLeccionesAprendidasRequestType;
import org.bcie.configuracionprocesosmo.ConfiguracionLeccionesAprendidasResponseType;
import org.bcie.configuracionprocesosmo.ConfiguracionPrepagoRequestType;
import org.bcie.configuracionprocesosmo.ConfiguracionPrepagoResponseType;
import org.bcie.configuracionprocesosmo.ConfiguracionPreparacionRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionPreparacionResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionReasignarClienteRequestType;
import org.bcie.configuracionprocesosmo.ConfiguracionReasignarClienteResponseType;
import org.bcie.configuracionprocesosmo.ConfiguracionReasignarOperacionRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionReasignarOperacionResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionRecuperacionUCERequest;
import org.bcie.configuracionprocesosmo.ConfiguracionRecuperacionUCEResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionSeguimientoCobroRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionSeguimientoCobroResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionSeguimientoCrediticioRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionSeguimientoCrediticioResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionSupervisionRequest;
import org.bcie.configuracionprocesosmo.ConfiguracionSupervisionResponse;
import org.bcie.configuracionprocesosmo.ConfiguracionValidacionAsignacionRequestType;
import org.bcie.configuracionprocesosmo.ConfiguracionValidacionAsignacionResponseType;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140319.1121
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ConfiguracionProcesosPT", targetNamespace = "http://xmlns.bcie.com/ConfiguracionProcesosService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    org.bcie.desembolsobo.ObjectFactory.class,
    org.bcie.configuracionprocesosbo.ObjectFactory.class,
    org.bcie.adquisicionbo.ObjectFactory.class,
    org.bcie.atributobo.ObjectFactory.class,
    org.bcie.catalogobo.ObjectFactory.class,
    org.bcie.clientebo.ObjectFactory.class,
    org.bcie.comisionbo.ObjectFactory.class,
    org.bcie.commonbo.ObjectFactory.class,
    org.bcie.condicionbo.ObjectFactory.class,
    org.bcie.configuracionprocesosmo.ObjectFactory.class,
    org.bcie.contratobo.ObjectFactory.class,
    org.bcie.crearbitacoraprocesobo.ObjectFactory.class,
    org.bcie.declaracionjuradabo.ObjectFactory.class,
    org.bcie.documentobo.ObjectFactory.class,
    org.bcie.errorbo.ObjectFactory.class,
    org.bcie.herramientacebo.ObjectFactory.class,
    org.bcie.implementacionpctbo.ObjectFactory.class,
    org.bcie.leccionaprendidabo.ObjectFactory.class,
    org.bcie.lineacreditobo.ObjectFactory.class,
    org.bcie.matriztccbo.ObjectFactory.class,
    org.bcie.operacionbo.ObjectFactory.class,
    org.bcie.productobo.ObjectFactory.class,
    org.bcie.reglabo.ObjectFactory.class,
    org.bcie.resultbo.ObjectFactory.class,
    org.bcie.terminobo.ObjectFactory.class,
    org.bcie.xmlns.objetoproceso.comun.cliente.v1.ObjectFactory.class,
    org.bcie.xmlns.objetoproceso.comun.header.v1.ObjectFactory.class,
    org.bcie.xmlns.objetoproceso.comun.operacion.v1.ObjectFactory.class,
    org.bcie.xmlns.objetoproceso.comun.parameter.v1.ObjectFactory.class,
    org.bcie.xmlns.objetoproceso.comun.proceso.v1.ObjectFactory.class,
    org.bcie.xmlns.objetoproceso.comun.tarea.v1.ObjectFactory.class
})
public interface ConfiguracionProcesosPT {


    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionElegibilidadResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionElegibilidad")
    @WebResult(name = "configuracionElegibilidadResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionElegibilidadResponse configuracionElegibilidad(
        @WebParam(name = "configuracionElegibilidadRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionElegibilidadRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionPreparacionResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionPreparacion")
    @WebResult(name = "configuracionPreparacionResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionPreparacionResponse configuracionPreparacion(
        @WebParam(name = "configuracionPreparacionRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionPreparacionRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionLavadoActivosResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionLavadoActivos")
    @WebResult(name = "configuracionLavadoActivosResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionLavadoActivosResponse configuracionLavadoActivos(
        @WebParam(name = "configuracionLavadoActivosRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionLavadoActivosRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionAnalisisResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionAnalisis")
    @WebResult(name = "configuracionAnalisisResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionAnalisisResponse configuracionAnalisis(
        @WebParam(name = "configuracionAnalisisRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionAnalisisRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionFormalizacionResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionFormalizacion")
    @WebResult(name = "configuracionFormalizacionResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionFormalizacionResponse configuracionFormalizacion(
        @WebParam(name = "configuracionFormalizacionRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionFormalizacionRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionAprobacionResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionAprobacion")
    @WebResult(name = "configuracionAprobacionResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionAprobacionResponse configuracionAprobacion(
        @WebParam(name = "configuracionAprobacionRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionAprobacionRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionCancelarOperacionResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionCancelarOperacion")
    @WebResult(name = "configuracionCancelarOperacionResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionCancelarOperacionResponse configuracionCancelarOperacion(
        @WebParam(name = "configuracionCancelarOperacionRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionCancelarOperacionRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionEvaluacionResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionEvaluacion")
    @WebResult(name = "configuracionEvaluacionResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionEvaluacionResponse configuracionEvaluacion(
        @WebParam(name = "configuracionEvaluacionRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionEvaluacionRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionEnmiendasResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionEnmiendas")
    @WebResult(name = "configuracionEnmiendasResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionEnmiendasResponse configuracionEnmiendas(
        @WebParam(name = "configuracionEnmiendasRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionEnmiendasRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionAdministracionClienteResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionAdministracionCliente")
    @WebResult(name = "configuracionAdministracionClienteResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionAdministracionClienteResponse configuracionAdministracionCliente(
        @WebParam(name = "configuracionAdministracionClienteRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionAdministracionClienteRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionAdministracionOperacionResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionAdministracionOperacion")
    @WebResult(name = "configuracionAdministracionOperacionResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionAdministracionOperacionResponse configuracionAdministracionOperacion(
        @WebParam(name = "configuracionAdministracionOperacionRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionAdministracionOperacionRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionComisionesResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionComisiones")
    @WebResult(name = "configuracionComisionesResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionComisionesResponse configuracionComisiones(
        @WebParam(name = "configuracionComisionesRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionComisionesRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionCondicionesResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionCondiciones")
    @WebResult(name = "configuracionCondicionesResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionCondicionesResponse configuracionCondiciones(
        @WebParam(name = "configuracionCondicionesRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionCondicionesRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionReasignarOperacionResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionReasignarOperacion")
    @WebResult(name = "configuracionReasignarOperacionResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionReasignarOperacionResponse configuracionReasignarOperacion(
        @WebParam(name = "configuracionReasignarOperacionRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionReasignarOperacionRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionSeguimientoCrediticioResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionSeguimientoCrediticio")
    @WebResult(name = "configurarSeguimientoCrediticioResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionSeguimientoCrediticioResponse configuracionSeguimientoCrediticio(
        @WebParam(name = "configurarSeguimientoCrediticioRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionSeguimientoCrediticioRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionRecuperacionUCEResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionUCE")
    @WebResult(name = "configuracionRecuperacionUCEResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionRecuperacionUCEResponse configuracionUCE(
        @WebParam(name = "configuracionRecuperacionUCERequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionRecuperacionUCERequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionSupervisionResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionSupervision")
    @WebResult(name = "configuracionSupervisionResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionSupervisionResponse configuracionSupervision(
        @WebParam(name = "configuracionSupervisionRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionSupervisionRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionCierreFideicomisoResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionCierreFideicomiso/")
    @WebResult(name = "configuracionCierreFideicomisoResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionCierreFideicomisoResponse configuracionCierreFideicomiso(
        @WebParam(name = "configuracionCierreFideicomisoRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionCierreFideicomisoRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionSeguimientoCobroResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionSeguimientoCobro")
    @WebResult(name = "configuracionSeguimientoCobroResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionSeguimientoCobroResponse configuracionSeguimientoCobro(
        @WebParam(name = "configuracionSeguimientoCobroRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionSeguimientoCobroRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionCierreResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionCierre/")
    @WebResult(name = "configuracionCierreResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionCierreResponse configuracionCierre(
        @WebParam(name = "configuracionCierreRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionCierreRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionDesembolsoResponse
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionDesembolso")
    @WebResult(name = "configuracionDesembolsoResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionDesembolsoResponse configuracionDesembolso(
        @WebParam(name = "configuracionDesembolsoRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionDesembolsoRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionGestionCobroResponseType
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionGestionCobro")
    @WebResult(name = "ConfiguracionGestionCobroResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionGestionCobroResponseType configuracionGestionCobro(
        @WebParam(name = "ConfiguracionGestionCobroRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionGestionCobroRequestType request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionPrepagoResponseType
     */
    @WebMethod(action = "http://xmlns.bcie.com/ConfiguracionProcesosService/configuracionPrepago")
    @WebResult(name = "ConfiguracionPrepagoResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionPrepagoResponseType configuracionPrepago(
        @WebParam(name = "ConfiguracionPrepagoRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionPrepagoRequestType request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionDesembolsoExcepcionResponseType
     */
    @WebMethod(action = "http://www.bcie.org/ConfiguracionDesembolsoExcepcionSOR/ConfiguracionDesembolsoExcepcion")
    @WebResult(name = "ConfiguracionDesembolsoExcepcionResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionDesembolsoExcepcionResponseType configuracionDesembolsoExcepcion(
        @WebParam(name = "ConfiguracionDesembolsoExcepcionRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionDesembolsoExcepcionRequestType request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionValidacionAsignacionResponseType
     */
    @WebMethod(action = "http://www.bcie.org/ConfiguracionValidacionAsignacionSOR/configuracionValidacionAsignacion")
    @WebResult(name = "ConfiguracionValidacionAsignacionResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionValidacionAsignacionResponseType configuracionValidacionAsignacion(
        @WebParam(name = "ConfiguracionValidacionAsignacionRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionValidacionAsignacionRequestType request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionAprobacionClienteResponseType
     */
    @WebMethod(action = "http://www.bcie.org/ConfiguracionProcesosService/configuracionAprobacionCliente")
    @WebResult(name = "CofiguracionAprobacionClienteResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionAprobacionClienteResponseType configuracionAprobacionCliente(
        @WebParam(name = "CofiguracionAprobacionClienteRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionAprobacionClienteRequestType request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionAdquisicionResponseType
     */
    @WebMethod(action = "http://www.bcie.org/ConfiguracionProcesosService/configuracionAdquisicion")
    @WebResult(name = "ConfiguracionAdquisicionResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionAdquisicionResponseType configuracionAdquisicion(
        @WebParam(name = "ConfiguracionAdquisicionRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionAdquisicionRequestType request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionContratacionFormalizacionResponseType
     */
    @WebMethod(action = "http://www.bcie.org/ConfiguracionProcesosService/configuracionContratacionFormalizacion")
    @WebResult(name = "ConfiguracionContratacionFormalizacionResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionContratacionFormalizacionResponseType configuracionContratacionFormalizacion(
        @WebParam(name = "ConfiguracionContratacionFormalizacionRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionContratacionFormalizacionRequestType request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionEnviarGastoResponseType
     */
    @WebMethod(action = "http://www.bcie.org/ConfiguracionProcesosService/configuracionEnviarGasto")
    @WebResult(name = "ConfiguracionEnviarGastoResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionEnviarGastoResponseType configuracionEnviarGasto(
        @WebParam(name = "ConfiguracionEnviarGastoRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionEnviarGastoRequestType request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionImplementacionPCTResponseType
     */
    @WebMethod(action = "http://www.bcie.org/ConfiguracionProcesosService/configuracionImplementacionPCT")
    @WebResult(name = "ConfiguracionImplementacionPCTResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionImplementacionPCTResponseType configuracionImplementacionPCT(
        @WebParam(name = "ConfiguracionImplementacionPCTRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionImplementacionPCTRequestType request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionInteresesCobroResponseType
     */
    @WebMethod(action = "http://www.bcie.org/ConfiguracionProcesosService/configuracionInteresesCobro")
    @WebResult(name = "ConfiguracionInteresesCobroResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionInteresesCobroResponseType configuracionInteresesCobro(
        @WebParam(name = "ConfiguracionInteresesCobroRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionInteresesCobroRequestType request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionReasignarClienteResponseType
     */
    @WebMethod(action = "http://www.bcie.org/ConfiguracionProcesosService/configuracionReasignarCliente")
    @WebResult(name = "configuracionReasignarClienteResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionReasignarClienteResponseType configuracionReasignarCliente(
        @WebParam(name = "configuracionReasignarClienteRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionReasignarClienteRequestType request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionFormalizacionEnmiendasResponse
     */
    @WebMethod(action = "http://www.bcie.org/ConfiguracionProcesosService/configuracionFormalizacionEnmiendas")
    @WebResult(name = "ConfiguracionFormalizacionEnmiendasResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionFormalizacionEnmiendasResponse configuracionFormalizacionEnmiendas(
        @WebParam(name = "ConfiguracionFormalizacionEnmiendasRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionFormalizacionEnmiendasRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.bcie.configuracionprocesosmo.ConfiguracionLeccionesAprendidasResponseType
     */
    @WebMethod(action = "http://www.bcie.org/ConfiguracionProcesosService/configuracionLeccionesAprendidas")
    @WebResult(name = "ConfiguracionLeccionesAprendidasResponse", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "response")
    public ConfiguracionLeccionesAprendidasResponseType configuracionLeccionesAprendidas(
        @WebParam(name = "ConfiguracionLeccionesAprendidasRequest", targetNamespace = "http://www.bcie.org/ConfiguracionProcesosMO", partName = "request")
        ConfiguracionLeccionesAprendidasRequestType request);

}
