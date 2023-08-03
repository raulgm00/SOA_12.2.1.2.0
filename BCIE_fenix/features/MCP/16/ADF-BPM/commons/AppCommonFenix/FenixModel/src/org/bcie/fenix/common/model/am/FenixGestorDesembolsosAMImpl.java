package org.bcie.fenix.common.model.am;

import com.bcie.xmlns.lineacreditoservice.LineaCredito;

import com.oracle.xmlns.bpmn.bpmnprocess.procesosprincipales.ProcesosPrincipalesBindingQSService;
import com.oracle.xmlns.bpmn.bpmnprocess.procesosprincipales.ProcesosPrincipalesPortType;

import java.awt.Container;

import java.io.StringWriter;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import oracle.adf.share.logging.ADFLogger;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.server.ApplicationModuleImpl;

import oracle.jbo.server.ViewLinkImpl;


import oracle.jbo.server.ViewObjectImpl;

import org.bcie.catalogobo.Catalogo;
import org.bcie.conversormonedas.ConversorMonedasPT;
import org.bcie.conversormonedas.ConversorMonedasPT12BndQSService;
import org.bcie.conversormonedasbo.ConvierteMonedas;
import org.bcie.conversormonedasbo.ConvierteMonedasImporte;
import org.bcie.conversormonedasmo.ConvertirMonedasImportesRequestType;
import org.bcie.conversormonedasmo.ConvertirMonedasImportesResponseType;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.common.FenixGestorDesembolsosAM;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.utils.ModelUtils;
import org.bcie.fenix.common.model.vo.AdjuntoDocumentoVOImpl;
import org.bcie.fenix.common.model.vo.AdquisicionesVOImpl;
import org.bcie.fenix.common.model.vo.AprobacionVOImpl;
import org.bcie.fenix.common.model.vo.CalcularMontosSolicitudImpl;
import org.bcie.fenix.common.model.vo.CalendarioComplejoVOImpl;
import org.bcie.fenix.common.model.vo.CargoDesemnolsoVOImpl;
import org.bcie.fenix.common.model.vo.CargosContratoDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.CatBancosByMonedaVOImpl;
import org.bcie.fenix.common.model.vo.CatCuentasNostroByBancoImpl;
import org.bcie.fenix.common.model.vo.CatFondoContableVOImpl;
import org.bcie.fenix.common.model.vo.CatInstruccionesPagoValidadasVOImpl;
import org.bcie.fenix.common.model.vo.CoberturasPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.ComisionesOtrosCargosImpl;
import org.bcie.fenix.common.model.vo.CondicionesFinancierasVOImpl;
import org.bcie.fenix.common.model.vo.ConfiguracionVOImpl;
import org.bcie.fenix.common.model.vo.ConsultaFechaTransRecursosBydesembolsoImpl;
import org.bcie.fenix.common.model.vo.ConsultaJustificacionExcepcionVOImpl;
import org.bcie.fenix.common.model.vo.ConsultaLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.ConsultaOperacionPadreAsociadaVOImpl;
import org.bcie.fenix.common.model.vo.ConsultaSolicitudDesembolsoFilterVOImpl;
import org.bcie.fenix.common.model.vo.ConsultaSolicitudDesembolsoTreeTableVOImpl;
import org.bcie.fenix.common.model.vo.ConsultaTransferenciasBanByDesembolsoImpl;
import org.bcie.fenix.common.model.vo.ConsultarContratoDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarContratoDesembolsoVORowImpl;
import org.bcie.fenix.common.model.vo.ConsultarContratoTreSolicitudLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarDatosContratoVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarDatosOperacionVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarFuenteVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarLineaCreditoDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarOperacionVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarOperacionesAsocidasVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarProductoOperacionImpl;
import org.bcie.fenix.common.model.vo.ConsultarProgramaOperacionVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarSolicitudDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarSpreadReferenciaVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarTasaReferenciaVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarTransferenciasByDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarVtaProductoDesembolsoFlexcubeVOImpl;
import org.bcie.fenix.common.model.vo.ContradoDesembolsoInfoVOImpl;
import org.bcie.fenix.common.model.vo.ContratoDesembolsoPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.ContratoDesembolsoQueryVOImpl;
import org.bcie.fenix.common.model.vo.ContratoDesembolsoQueryVORowImpl;
import org.bcie.fenix.common.model.vo.ContratoDesembolsoSumMontoDesembolsarVOImpl;
import org.bcie.fenix.common.model.vo.ContratoDesembolsoTreeVOImpl;
import org.bcie.fenix.common.model.vo.ContratoDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.ContratosDesembolsoConInteresVOImpl;
import org.bcie.fenix.common.model.vo.ContratosDesembolsoOperacionVOImpl;
import org.bcie.fenix.common.model.vo.DatoProyectoVOImpl;
import org.bcie.fenix.common.model.vo.DatosActividadF1VOImpl;
import org.bcie.fenix.common.model.vo.DatosActividadVOImpl;
import org.bcie.fenix.common.model.vo.DatosAreaVOImpl;
import org.bcie.fenix.common.model.vo.DatosContratoDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.DatosDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.DatosEjeVOImpl;
import org.bcie.fenix.common.model.vo.DatosGeneralesDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.DatosGeneralesIntermediacionVOImpl;
import org.bcie.fenix.common.model.vo.DatosHerramientaVOImpl;
import org.bcie.fenix.common.model.vo.DatosLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.DatosSupervisionVOImpl;
import org.bcie.fenix.common.model.vo.DatosTransferenciaSinRecursosVOImpl;
import org.bcie.fenix.common.model.vo.DescripcionExcepcionVOImpl;
import org.bcie.fenix.common.model.vo.DesembolsoServiceVOImpl;
import org.bcie.fenix.common.model.vo.DestinoVOImpl;
import org.bcie.fenix.common.model.vo.DetalleCalendarioVOImpl;
import org.bcie.fenix.common.model.vo.DetalleDesembolsosOperacionVOImpl;
import org.bcie.fenix.common.model.vo.DetallesCalendarioVOImpl;
import org.bcie.fenix.common.model.vo.DiasHabilesVOImpl;
import org.bcie.fenix.common.model.vo.DiasInhabilesDesembolsosVOImpl;
import org.bcie.fenix.common.model.vo.DocumentoCalendarioVOImpl;
import org.bcie.fenix.common.model.vo.FechasMayorYMenorCalendarioComplejoVOImpl;
import org.bcie.fenix.common.model.vo.FormularioGeneracionFT05VOImpl;
import org.bcie.fenix.common.model.vo.FormularioJustificacionExcepcionVOImpl;
import org.bcie.fenix.common.model.vo.FormularioJustificacionExcepcionVORowImpl;
import org.bcie.fenix.common.model.vo.FormularioObservacionPrepagoVORowImpl;
import org.bcie.fenix.common.model.vo.DocumentosTrazabilidadOperacionFiltroVOImpl;
import org.bcie.fenix.common.model.vo.DocumentosTrazabilidadOperacionVOImpl;
import org.bcie.fenix.common.model.vo.DocumentosTrazabilidadOperacionFiltroVOImpl;
import org.bcie.fenix.common.model.vo.EvidenciasSolicitudVOImpl;
import org.bcie.fenix.common.model.vo.FormularioGeneracionFT05VORowImpl;
import org.bcie.fenix.common.model.vo.FormularioPrepagoVORowImpl;
import org.bcie.fenix.common.model.vo.FuentesExternasContratoDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.JustificacionExcepcionVOImpl;
import org.bcie.fenix.common.model.vo.LineaCreditoDesembolsoImpEnvioGastoVOImpl;
import org.bcie.fenix.common.model.vo.LineaCreditoDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.LineaCreditoDesembolsosTreeQueryVOImpl;
import org.bcie.fenix.common.model.vo.LineaCreditoDesembolsosVOImpl;
import org.bcie.fenix.common.model.vo.ManejoDeErroresVOImpl;
import org.bcie.fenix.common.model.vo.MonedaContratoVOImpl;
import org.bcie.fenix.common.model.vo.MontoTotalSolicitudVOImpl;
import org.bcie.fenix.common.model.vo.ObservacionPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.OperacionDatosSolicitudVOImpl;
import org.bcie.fenix.common.model.vo.OperacionFiltroDatosSolicitudVOImpl;
import org.bcie.fenix.common.model.vo.PrepagoVOImpl;
import org.bcie.fenix.common.model.vo.ProgramaOperacionVOImpl;
import org.bcie.fenix.common.model.vo.PropagarContratoDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.RecuperarDiaPagoClienteByDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.RegistroSolicitudDesembolsoExcepcionVOImpl;
import org.bcie.fenix.common.model.vo.SaldoPreinversionDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.SectorGarantiaVOImpl;
import org.bcie.fenix.common.model.vo.SolicitudDesembolsoQueryVOImpl;
import org.bcie.fenix.common.model.vo.SolicitudDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.SpreadReferenciaVOImpl;
import org.bcie.fenix.common.model.vo.SumaCargosDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.SumaComisionesyOtrosCargosDesembolsoImpl;
import org.bcie.fenix.common.model.vo.TasaReferenciaVOImpl;
import org.bcie.fenix.common.model.vo.TbiReglaDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.TcaBaseCalculoVOImpl;
import org.bcie.fenix.common.model.vo.TcaEspecificacionFechaVOImpl;
import org.bcie.fenix.common.model.vo.TcaFormaTransferenciaLOVImpl;
import org.bcie.fenix.common.model.vo.TcaTipoCalendarioVOImpl;
import org.bcie.fenix.common.model.vo.TcaTipoMonedaVOImpl;
import org.bcie.fenix.common.model.vo.TcaTipoTasaDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.TerminoLineaVOImpl;
import org.bcie.fenix.common.model.vo.TipoMonedaDeSolicitudDesembolsosVOImpl;
import org.bcie.fenix.common.model.vo.TransferenciaBancariaVOImpl;
import org.bcie.fenix.common.model.vo.TransferenciaRecursosByDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.TransferenciaRecursosVOImpl;
import org.bcie.fenix.common.model.vo.TransferenciasBancariasFormVOImpl;
import org.bcie.fenix.common.model.vo.TransferenciasBancariasTablaVOImpl;
import org.bcie.fenix.common.model.vo.TransferenciasConInstruccionVOImpl;
import org.bcie.fenix.common.model.vo.TransferenciasConsolidadasVOImpl;
import org.bcie.fenix.common.model.vo.TransferenciasConsolidarVOImpl;
import org.bcie.fenix.common.model.vo.TransferrenciaAConsolidarVOImpl;
import org.bcie.fenix.common.model.vo.TreLineaCreditoAprobacionVOImpl;
import org.bcie.fenix.common.model.vo.TreReglasDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.TreEvidenciaSolicitudVOImpl;
import org.bcie.fenix.common.model.vo.TreSolicitudLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.TreLineaPasivaVOImpl;
import org.bcie.fenix.common.model.vo.ValidarFunetesExternasContratoDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.ValidarOperacionFormalizadaVOImpl;
import org.bcie.fenix.common.model.vo.ValidarT406ByDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.ValidarT406ByOperacionVOImpl;
import org.bcie.fenix.common.model.vo.ValoresPreinversionTablaByDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.VcaCuentaClienteVOImpl;
import org.bcie.fenix.common.model.vo.TrePrepagoContratoVOImpl;
import org.bcie.fenix.common.model.vo.VcaCtasClientePasivasVOImpl;
import org.bcie.fenix.common.model.vo.VcaCuentaContablePasivaVOImpl;
import org.bcie.fenix.common.model.vo.VcaCuentasNostroBcieVOImpl;
import org.bcie.fenix.common.model.vo.VcaTasaDesembolsoFlexcubeVOImpl;
import org.bcie.fenix.common.model.vo.VerDetallesLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.VtaProdFlexComponenteDesemVOImpl;
import org.bcie.fenix.common.model.vo.VtaProductoDesemFlexcubeVOImpl;
import org.bcie.fenix.common.model.vo.common.SaldoPreinversionDesembolsoVO;
import org.bcie.productobo.ReglasOperacion;
import org.bcie.reglabo.ReglaBasicType;
import org.bcie.reglatarea.ReglaTareaPT;
import org.bcie.reglatarea.ReglaTareaPTSOAP12BindingQSService;
import org.bcie.reglatareabo.ReglaEvaluacionType;
import org.bcie.reglatareabo.TareaReglas;
import org.bcie.reglatareamo.ValidarReglasRequestType;
import org.bcie.reglatareamo.ValidarReglasResponseType;
import org.bcie.reglatareamo.ValidarTareaReglasRequestType;
import org.bcie.reglatareamo.ValidarTareaReglasResponseType;
import org.bcie.resultbo.Resultado;
import org.bcie.xmlns.objetoproceso.comun.header.v1.Header;
import org.bcie.xmlns.objetoproceso.comun.parameter.v1.ParameterType;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Aug 23 17:14:15 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FenixGestorDesembolsosAMImpl extends ApplicationModuleImpl implements FenixGestorDesembolsosAM {
    
    private static ADFLogger logger = null;
    /**
     * This is the default constructor (do not remove).
     */
    public FenixGestorDesembolsosAMImpl() {
        
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
            }
    }    

    public Boolean iniciarProcesoDesembolso(Long idOperacion, String responsableOperacion, Long idContrato, Long idSolicitud){
        logger.warning("Inicia metodo iniciarProcesoDesembolso");
        Boolean resultado = Boolean.FALSE;
        Integer idOperacionInt = null;
        String mensaje = null;
        StringWriter xmlEntrada = null;
        StringWriter xmlSalida = null;
        FenixAMImpl fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
        
        if(null == idOperacion || null == responsableOperacion || null == idContrato || null == idSolicitud){
            logger.warning("Parametros requeridos son NULL. No se pudo iniciar el proceso de desembolso.");
            logger.warning("idOperacion: " + idOperacion);
            logger.warning("responsableOperacion: " + responsableOperacion);
            logger.warning("idContrato: " + idContrato);       
            logger.warning("idSOlicitud: " + idSolicitud); 
            return resultado;
        }
        
        String wsdl = fenixAMImpl.getWsdl(IWsdlLocation.PROCESOS_PRINCIPALES);
        //String wsdl = "WSDL_PROCESOS_PRINCIPALES";
        ProcesosPrincipalesBindingQSService procesosPrincipalesBindingQSService =
            IWsdlLocation.Service.getInstance(ProcesosPrincipalesBindingQSService.class, wsdl);
        ProcesosPrincipalesPortType procesosPrincipalesPortType =
            procesosPrincipalesBindingQSService.getProcesosPrincipalesBindingQSPort();
        
        Header header = new Header();
        org.bcie.xmlns.objetoproceso.comun.operacion.v1.Operacion operacion =
            new org.bcie.xmlns.objetoproceso.comun.operacion.v1.Operacion();
        
        try{
            idOperacionInt = idOperacion.intValue();
        }catch(Exception e){
            logger.warning("ERROR al convertir idOperacion", e);
        }
        
        logger.warning("Seteando valores a objetos de request de servicio InicioDesembolso");
        logger.warning("IdOperacion integer: " + idOperacionInt);
        operacion.setCodigoOperacion(idOperacionInt);
        logger.warning("Responsable operacion: " + responsableOperacion);
        operacion.setResponsableOperacion(responsableOperacion);        
        header.setOperacion(operacion);
        
        logger.warning("idContratoDesembolso: " + idContrato);
        ParameterType parmeterType = new ParameterType();
        parmeterType.setParameterName(FenixModelConstants.IDENTIFICADOR_PARAMETRO_PROCESO_DESEMBOLSO);
        parmeterType.setParameterValue(idContrato.toString());
        header.getParameterType().add(parmeterType);        
        
        logger.warning("idSolcitudDesembolso: " + idSolicitud);
        ParameterType parmetersType = new ParameterType();
        parmetersType.setParameterName(FenixModelConstants.IDENTIFICADOR_PARAMETRO_PROCESO_DESEMBOLSO_SOLICITUD);
        parmetersType.setParameterValue(idSolicitud.toString());
        header.getParameterType().add(parmetersType);               
        Resultado response = null;
        
        try{
            mensaje = "Invocando Servicio - iniciar Cierre-";
            xmlEntrada = this.writeXMLRequest(header, header.getClass());
            logger.log(ADFLogger.WARNING, ">HNWS" + xmlEntrada.toString());
            
            response = procesosPrincipalesPortType.inicioDesembolso(header);
            
            mensaje = "Respuesta servicio - inicioCierre -";
            xmlSalida = this.writeXMLRequest(response, response.getClass());
            logger.log(ADFLogger.WARNING, ">HNWS" + xmlSalida.toString());
        }catch(Exception e){
            logger.warning("ERROR al ejecutar servicio inicioDesembolso ", e);
            JboException ex = new JboException("");
            ex.addToExceptions(new Exception("Error al ejecutar servicio inicioDesembolso: " + e.getMessage()));
            throw ex;
        }
        
        
        if(response != null){
            if (null != response.getError() ||
                (response.getResult() != null && 
                response.getResult().value() == "ERROR")) {
                
                String mensajeError = "";
                String detalleError = "";
                
                try{
                    if (null != response.getMessage() && 
                        response.getMessage().trim().length() > 0) {
                        mensajeError = response.getMessage();
                    }
                }catch(Exception e){
                    logger.warning("ERROR al evaluar response.getMessage() ",e);
                }
                   
                try{            
                    if (null != response.getError().getErrorDescription() &&
                        response.getError().getErrorDescription().trim().length() > 0) {
                        detalleError = response.getError().getErrorDescription();
                    }
                }catch(Exception e){
                    logger.warning("Error al evaluar response.getError().getErrorDescription()" ,e);
                }
                    
                if(response.getError() != null){
                    logger.warning("Error: "+response.getError().toString());
                }
                    
                logger.warning("ERROR response: " + mensajeError + ": " + detalleError);
                resultado = Boolean.FALSE;
                
                JboException ex = new JboException("");
                ex.addToExceptions(new Exception("Servicio InicioDesembolso devuelve ERROR >> " +
                               mensajeError + ": " + detalleError));
                throw ex;
            } else {
                logger.warning("El servicio de InicioDesembolso ejecutado correctamente.");
                resultado = Boolean.TRUE;
            }
        }else{            
            logger.warning("Error el response es NULL");
            JboException ex = new JboException("");
            ex.addToExceptions(new Exception("El response del servicio InicioDesembolso es NULL."));
            throw ex;
        }
        
        logger.warning("Termina metodo iniciarProcesoDesembolso");
        return resultado;
    }

    @SuppressWarnings("unchecked")
    public Map obtenerReglasNegocio(List listaReglas, Long idContratoDesembolso, String instanciaTarea, Integer idTarea,
                                    Integer idProceso) {
        logger.warning("Inicia metodo obtenerReglasNegocio");
        Map reglasConsultadasMap = new HashMap();
        FenixAMImpl fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
        ValidarReglasRequestType request = new ValidarReglasRequestType();
        ValidarReglasResponseType response = null;

        if (null == listaReglas || listaReglas.size() <= 0) {
            logger.warning("La lista de reglas a validar esta vacia.");
            return reglasConsultadasMap;
        }

        List<Long> listId = listaReglas;
        logger.warning("Registros de ids a evaluar: " + listId.size());
        if (null != fenixAMImpl) {
            String wsdl = fenixAMImpl.getWsdl(IWsdlLocation.REGLA_TAREA);
            //String wsdl = "WSDL_REGLA_TAREA";
            for (Long idList : listId) {
                logger.warning("Id de regla: " + idList);
                ReglaEvaluacionType regla = new ReglaEvaluacionType();
                regla.setId(idList);

                logger.warning("Seteando valor a lista de reglas: " + regla.getId());
                request.getReglas().add(regla);
            }
            logger.warning("Numero de reglas listas para validar: " + request.getReglas().size());

            org.bcie.commonbo.ParameterType parametrosIdDesembolso = new org.bcie.commonbo.ParameterType();
            org.bcie.commonbo.ParameterType parametrosInstancia = new org.bcie.commonbo.ParameterType();
            org.bcie.commonbo.ParameterType parametrosTarea = new org.bcie.commonbo.ParameterType();
            org.bcie.commonbo.ParameterType parametrosProceso = new org.bcie.commonbo.ParameterType();

            logger.warning("Seteando valores de Contrato a objeto parametrosIdDesembolso");
            parametrosIdDesembolso.setName(FenixModelConstants.IDENTIFICADOR_PARAMETRO_REGLAS_DESEMBOLSO);
            parametrosIdDesembolso.setValue(idContratoDesembolso.toString());

            logger.warning("Seteando valores de Contrato a objeto parametrosInstancia");
            parametrosInstancia.setName(FenixModelConstants.IDENTIFICADOR_PARAMETRO_REGLAS_INSTANCIA);
            parametrosInstancia.setValue(instanciaTarea);

            logger.warning("Seteando valores de Contrato a objeto parametrosTarea");
            parametrosTarea.setName(FenixModelConstants.IDENTIFICADOR_PARAMETRO_REGLAS_TAREA);
            parametrosTarea.setValue(idTarea.toString());

            logger.warning("Seteando valores de Contrato a objeto parametrosProceso");
            parametrosProceso.setName(FenixModelConstants.IDENTIFICADOR_PARAMETRO_REGLAS_PROCESO);
            parametrosProceso.setValue(idProceso.toString());

            logger.warning("Añadiendo objetos ParameterType a request");
            request.getParametros().add(parametrosIdDesembolso);
            request.getParametros().add(parametrosInstancia);
            request.getParametros().add(parametrosTarea);
            request.getParametros().add(parametrosProceso);

            ReglaTareaPTSOAP12BindingQSService reglasService =
                IWsdlLocation.Service.getInstance(ReglaTareaPTSOAP12BindingQSService.class, wsdl);
            ReglaTareaPT reglaTareaPT = reglasService.getReglaTareaPTSOAP12BindingQSPort();

            try {
                logger.warning("Ejecutando servicio " + FenixModelConstants.WSC_VALIDAR_REGLAS);
                Date horaInicio = ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_VALIDAR_REGLAS);
                response = reglaTareaPT.validarReglas(request);
                ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_VALIDAR_REGLAS, horaInicio);                
                logger.warning("Servicio " + FenixModelConstants.WSC_VALIDAR_REGLAS + " ejecutado correctamente");
            } catch (Exception e) {
                logger.warning("Error al ejecutar servicio " + FenixModelConstants.WSC_VALIDAR_REGLAS);
                JboException ex = new JboException("");
                ex.addToExceptions(new Exception("Error al ejecutar servicio validarReglas: " + e.getMessage()));
                throw ex;
            }

            logger.warning("Evaluando response del Servicio " + FenixModelConstants.WSC_VALIDAR_REGLAS);
            if (null != response) {
                if (null != response.getResultado()) {
                    if (response.getResultado().getResult().value() == "OK") {
                        if (null != response.getTareaReglas()) {
                            TareaReglas tareaReglas = response.getTareaReglas();
                            if (null != tareaReglas.getReglasEvaluacion() ||
                                tareaReglas.getReglasEvaluacion().size() <= 0) {
                                for (ReglaEvaluacionType reglaEvaluacion : tareaReglas.getReglasEvaluacion()) {
                                    if (null != reglaEvaluacion.getEstado()) {
                                        logger.warning("Evaluando el objeto Estado para obtener la descripcion del estado de la Regla");
                                        Catalogo estado = reglaEvaluacion.getEstado();
                                        if (null != estado.getDescripcionCorta() &&
                                            !estado.getDescripcionCorta().equalsIgnoreCase("")) {
                                            logger.warning("Obteniendo DescripcionCorta de Estado para la regla: " +
                                                           reglaEvaluacion.getId());
                                            String descripcionEstado = estado.getDescripcionCorta();
                                            Map mapaReglasSCR = new HashMap<>();

                                            if (null != reglaEvaluacion.getTransaccion()) {
                                                if (reglaEvaluacion.getTransaccion().equalsIgnoreCase(FenixModelConstants.TRANSACCION_REGLA_SCR)) {

                                                    //validar las Reglas de SCR por aparte. Regresa mapa con ambas reglas con sus respectivos estados.
                                                    mapaReglasSCR =
                                                        validarReglasSCR(reglaEvaluacion.getId(),
                                                                         reglaEvaluacion.getTransaccion(),
                                                                         descripcionEstado);
                                                    if (mapaReglasSCR.size() > 0) {
                                                        logger.warning("Setando reglas de SCR a mapa de reglas.");
                                                        reglasConsultadasMap.put(2L,
                                                                                 mapaReglasSCR.get(2L)); //2L es el id de regla de scr igual a 5 con excepcion.
                                                        reglasConsultadasMap.put(5L,
                                                                                 mapaReglasSCR.get(5L)); //5L es el id de regla de scr mayor a 5.
                                                    } else {
                                                        logger.warning("No se recuperaron reglas de SCR.");
                                                    }
                                                } else {
                                                    logger.warning("Creando un nuevo registro en reglasConsultadasMap");                                                   
                                                    
                                                    reglasConsultadasMap.put(reglaEvaluacion.getId(), descripcionEstado);
                                                    
                                                    if(descripcionEstado.equals("ERROR")){
                                                       
                                                        String descripcionError = (null == reglaEvaluacion.getMensaje().getDescripcion())
                                                                                ? "Estado de la Regla en ERROR"
                                                                                : reglaEvaluacion.getMensaje().getDescripcion().toString();
                                                            
                                                            
                                                         reglasConsultadasMap.put("descripcionEstadoReglaError", descripcionError);
                                                    }
                                                    
                                                    if(reglaEvaluacion.getId() == FenixModelConstants.ID_REGLA_TRANSACCION_LIMITE_GLOBAL){
                                                        if(descripcionEstado.equals("NO_CUMPLIDA")){
                                                           
                                                            String mensajeErrorRegla22 = (null == reglaEvaluacion.getMensaje().getDescripcion())
                                                                                    ? "Estado de la Regla en ERROR"
                                                                                    : reglaEvaluacion.getMensaje().getDescripcion().toString();
                                                                
                                                                
                                                             reglasConsultadasMap.put("mensajeErrorRegla22", mensajeErrorRegla22);
                                                        } 
                                                    }
                                                    
                                                }
                                            } else {
                                                logger.warning("No se recibió la Transaccion de la regla.");
                                            }
                                        } else {
                                            logger.warning("La descripcionCorta del estado es NULL o vacia.");
                                        }
                                    } else {
                                        logger.warning("El objeto Estado de ReglaEvaluacion es NULL");
                                    }
                                }
                                logger.warning("El mapa regresa " + reglasConsultadasMap.size() +
                                               " validaciones de reglas.");
                            } else {
                                logger.warning("La lista de ReglasEvaluacion del Objeto TareaReglas del servicio " +
                                               FenixModelConstants.WSC_VALIDAR_REGLAS);
                            }
                        } else {
                            logger.warning("Objeto TareaReglas del response es NULL.");
                        }
                    } else {
                        logger.warning("Servicio " + FenixModelConstants.WSC_VALIDAR_REGLAS + " devuelve ERROR >> " +
                                       response.getResultado().getMessage());
                        JboException ex = new JboException("");
                        ex.addToExceptions(new Exception("Servicio validarReglas devuelve ERROR >> " +
                                       response.getResultado().getMessage()));
                        throw ex;
                    }
                } else {
                    logger.warning("getResultado de servicio " + FenixModelConstants.WSC_VALIDAR_REGLAS + " es NULL.");
                    JboException ex = new JboException("");
                    ex.addToExceptions(new Exception("El objeto getResutado del servicio validarReglas es NULL."));
                    throw ex;
                }
            } else {
                logger.warning("ERROR: Response es null.");
                JboException ex = new JboException("");
                ex.addToExceptions(new Exception("El response del servicio validarReglas es NULL."));
                throw ex;
            }
        } else {
            logger.warning("La instancia de FenixAMImpl es NULL.");
        }

        logger.warning("Termina metodo obtenerReglasNegocio");
        return reglasConsultadasMap;
    }
    
    
    public Map validarReglasPorTareaService(Long idContratoDesembolso, Long idTarea, String instanciaTarea){
        logger.warning("Inicia metodo validarReglasPorTarea");
        
        Map reglasConsultadasMap = new HashMap();
        if(idContratoDesembolso == null || idTarea == null || instanciaTarea == null ){
            logger.warning("No se estan recuperando todos los parametros necesarios para ejecutar el sevicio");      
            return reglasConsultadasMap;
        }                  
            org.bcie.commonbo.ParameterType parametrosIdDesembolso = new org.bcie.commonbo.ParameterType();
            org.bcie.commonbo.ParameterType parametrosInstancia = new org.bcie.commonbo.ParameterType();
        
                
                FenixAMImpl fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
                String wsdl = fenixAMImpl.getWsdl(IWsdlLocation.REGLA_TAREA);                        
               //  String wsdl = "WSDL_REGLA_TAREA";
                ReglaTareaPTSOAP12BindingQSService reglasService = IWsdlLocation.Service.getInstance(ReglaTareaPTSOAP12BindingQSService.class, wsdl);
                ReglaTareaPT reglaTareaPT = reglasService.getReglaTareaPTSOAP12BindingQSPort();
              
                ValidarTareaReglasRequestType request = new ValidarTareaReglasRequestType();
                ValidarTareaReglasResponseType response = null;
                request.setIdTarea(idTarea);
            
                logger.warning("Seteando valores de Contrato a objeto parametrosIdDesembolso");
                parametrosIdDesembolso.setName(FenixModelConstants.IDENTIFICADOR_PARAMETRO_REGLAS_DESEMBOLSO);
                parametrosIdDesembolso.setValue(idContratoDesembolso.toString());
                
                logger.warning("Seteando valores de Contrato a objeto parametrosInstancia");
                parametrosInstancia.setName(FenixModelConstants.IDENTIFICADOR_PARAMETRO_REGLAS_INSTANCIA);
                parametrosInstancia.setValue(instanciaTarea);
                
                logger.warning("Anadiendo objetos ParameterType a request");
                request.getParametros().add(parametrosIdDesembolso);
                request.getParametros().add(parametrosInstancia);
                  
           
               try {
                    logger.warning("Ejecutando servicio " + FenixModelConstants.WSC_VALIDAR_REGLAS);
                    Date horaInicio = ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_VALIDAR_REGLAS);
                    response = reglaTareaPT.validarTareaReglas(request);
                    ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_VALIDAR_REGLAS, horaInicio);
                    logger.warning("Servicio " + FenixModelConstants.WSC_VALIDAR_REGLAS + " ejecutado correctamente");
                } catch (Exception e) {
                    logger.warning("Error al ejecutar servicio " + FenixModelConstants.WSC_VALIDAR_REGLAS);
                    JboException ex = new JboException("");
                    ex.addToExceptions(new Exception("Error al ejecutar servicio validarTareaReglas: " + e.getMessage()));
                    throw ex;
                }
            
                logger.warning("Evaluando response del Servicio " + FenixModelConstants.WSC_VALIDAR_REGLAS);
                if(null != response){
                    if (null != response.getResultado()) {
                        if (response.getResultado().getResult().value() == "OK") {
                            if(null != response.getReglaTarea()){
                                TareaReglas tareaReglas = response.getReglaTarea();
                                if(null != tareaReglas.getReglasEvaluacion() || 
                                   tareaReglas.getReglasEvaluacion().size() <= 0){
                                    for(ReglaEvaluacionType reglaEvaluacion : tareaReglas.getReglasEvaluacion()){
                                        if(null != reglaEvaluacion.getEstado()){
                                            logger.warning("Evaluando el objeto Estado para obtener la descripcion del estado de la Regla");
                                            Catalogo estado = reglaEvaluacion.getEstado();
                                            if(null != estado.getDescripcionCorta() && !estado.getDescripcionCorta().equalsIgnoreCase("")){
                                                logger.warning("Obteniendo DescripcionCorta de Estado para la regla: " + reglaEvaluacion.getId());
                                                String descripcionEstado = estado.getDescripcionCorta();
                                                Map mapaReglasSCR = new HashMap<>();
                                                
                                                if(null != reglaEvaluacion.getTransaccion()){
                                                    if(reglaEvaluacion.getTransaccion().equalsIgnoreCase(FenixModelConstants.TRANSACCION_REGLA_SCR)){
                                                        
                                                        //validar las Reglas de SCR por aparte. Regresa mapa con ambas reglas con sus respectivos estados.
                                                        mapaReglasSCR = validarReglasSCR(reglaEvaluacion.getId(), reglaEvaluacion.getTransaccion(), descripcionEstado);
                                                        if(mapaReglasSCR.size()>0){
                                                            logger.warning("Setando reglas de SCR a mapa de reglas.");
                                                            reglasConsultadasMap.put(2L, mapaReglasSCR.get(2L));//2L es el id de regla de scr igual a 5 con excepcion.
                                                            reglasConsultadasMap.put(5L, mapaReglasSCR.get(5L));//5L es el id de regla de scr mayor a 5.
                                                        }else{
                                                            logger.warning("No se recuperaron reglas de SCR.");
                                                        }
                                                    }else{
                                                        logger.warning("Creando un nuevo registro en reglasConsultadasMap");
                                                        descripcionEstado = descripcionEstado.equals("NO_CUMPLIDA") && reglaEvaluacion.isEsAdvertencia() ? "NO_CUMPLIDA_ADVERTENCIA" : descripcionEstado;
                                                        reglasConsultadasMap.put(reglaEvaluacion.getId(), descripcionEstado);
                                                    }

                                            if (reglaEvaluacion.getId() == FenixModelConstants.ID_REGLA_TRANSACCION_LIMITE_GLOBAL) {
                                                if (descripcionEstado.equals("NO_CUMPLIDA")) {

                                                    String mensajeErrorRegla22 = (null == reglaEvaluacion.getMensaje().getDescripcion()) ? "Estado de la Regla en ERROR" : reglaEvaluacion.getMensaje().getDescripcion().toString();


                                                    reglasConsultadasMap.put("mensajeErrorRegla22", mensajeErrorRegla22);
                                                }
                                            }

                                        }else{
                                                    logger.warning("No se recibió la Transaccion de la regla.");
                                                }
                                            }else{
                                                logger.warning("La descripcionCorta del estado es NULL o vacia.");
                                            }
                                        }else{
                                            logger.warning("El objeto Estado de ReglaEvaluacion es NULL");
                                        }
                                        }
                                    logger.warning("El mapa regresa " + reglasConsultadasMap.size() + " validaciones de reglas.");
                                }else{
                                    logger.warning("La lista de ReglasEvaluacion del Objeto TareaReglas del servicio "
                                                   + FenixModelConstants.WSC_VALIDAR_REGLAS);
                                }
                            }else{
                                logger.warning("Objeto TareaReglas del response es NULL.");
                            }
                        }else{
                            logger.warning("Servicio " + FenixModelConstants.WSC_VALIDAR_REGLAS + " devuelve ERROR >> " +
                                           response.getResultado().getMessage());
                            JboException ex = new JboException("");
                            ex.addToExceptions(new Exception("Servicio validarReglas devuelve ERROR >> " +
                                           response.getResultado().getMessage()));
                            throw ex;
                        }
                    } else {
                        logger.warning("getResultado de servicio validarTareaReglas es NULL.");
                        JboException ex = new JboException("");
                        ex.addToExceptions(new Exception("El objeto getResutado del servicio validarTareaReglas es NULL."));
                        throw ex;
                    }
                } else {
                    logger.warning("ERROR: Response es null.");
                    JboException ex = new JboException("");
                    ex.addToExceptions(new Exception("El response del servicio validarTareaReglas es NULL."));
                    throw ex;
                }
            logger.warning("Termina metodo obtenerReglasNegocio");
                
        return reglasConsultadasMap;
    }
    
    public Map validarReglasSCR(Long idRegla, String transaccion, String descripcionEstado){
        logger.warning("Inicia metodo validarReglasSCR.");
        Map mapaReglasSCR = new HashMap<>();
        
        logger.warning("Datos a evaluar. ID: " + idRegla + ", TRANSACCION: " 
                       + transaccion + ", ESTATUS: " + descripcionEstado);
        
        if(null != transaccion){
            if(transaccion.equalsIgnoreCase(FenixModelConstants.TRANSACCION_REGLA_SCR)){
                logger.warning("Evaluando regla de SCR.");
                if(null != descripcionEstado){
                    if(descripcionEstado.equalsIgnoreCase(FenixModelConstants.TRANSACCION_SCR_CUMPLIDA)
                       || descripcionEstado.equalsIgnoreCase(FenixModelConstants.TRANSACCION_SCR_EXCEPTUADA)){
                        logger.warning("Las reglas de SCR son CUMPLIDAS o EXCEPTUADAS.");
                        mapaReglasSCR.put(2L, descripcionEstado);
                        mapaReglasSCR.put(5L, descripcionEstado);
                    }else{
                        logger.warning("Las reglas NO han sido CUMPLIDAS. Evaluando estado de reglas.");
                        if(descripcionEstado.equalsIgnoreCase(FenixModelConstants.TRANSACCION_SCR_NO_CMPLIDA)){
                            logger.warning("Una de las reglas de SCR NO CUMPLIDA.");                        
                            if(idRegla.compareTo(2L)==0){
                                logger.warning("Regla 2 de SCR NO CUMPLIDA.");
                                mapaReglasSCR.put(2L, descripcionEstado);
                                mapaReglasSCR.put(5L, FenixModelConstants.TRANSACCION_SCR_CUMPLIDA);
                            }
                            
                            if(idRegla.compareTo(5L)==0){
                                logger.warning("Regla 5 de SCR NO CUMPLIDA.");
                                mapaReglasSCR.put(2L, FenixModelConstants.TRANSACCION_SCR_CUMPLIDA);
                                mapaReglasSCR.put(5L, descripcionEstado);
                            }
                        }else{
                            logger.warning("El estado de las reglas de SCR es NO EVALUADA O ERROR.");
                            mapaReglasSCR.put(2L, descripcionEstado);
                            mapaReglasSCR.put(5L, descripcionEstado);
                        }
                    }
                }else{
                    logger.warning("Descripcion de estado es NULL. No se pudo evaluar la rega de SCR.");
                }
            }
        }else{
            logger.warning("La transaccion de la regla es NULL.");
        }
        
        
        logger.warning("Termina metodo validarReglasSCR.");
        return mapaReglasSCR;
    }
    
    public StringWriter writeXMLRequest(Object request, Class requestClass) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(requestClass);
        Marshaller m = context.createMarshaller();
        m.marshal(new JAXBElement(new QName(requestClass.getName()), requestClass, request), writer);
        return writer;
    }

    /**
     * Container's getter for DetalleDesembolsosOperacionVO1.
     * @return DetalleDesembolsosOperacionVO1
     */
    public DetalleDesembolsosOperacionVOImpl getDetalleDesembolsosOperacionVO() {
        return (DetalleDesembolsosOperacionVOImpl) findViewObject("DetalleDesembolsosOperacionVO");
    }


    /**
     * Container's getter for OperacionDatosSolicitudVO1.
     * @return OperacionDatosSolicitudVO1
     */
    public OperacionDatosSolicitudVOImpl getOperacionDatosSolicitudVO() {
        return (OperacionDatosSolicitudVOImpl) findViewObject("OperacionDatosSolicitudVO");
    }

  
    /**
     * Container's getter for OperacionFiltroDatosSolicitudVO1.
     * @return OperacionFiltroDatosSolicitudVO1
     */
    public OperacionFiltroDatosSolicitudVOImpl getOperacionFiltroDatosSolicitudVO() {
        return (OperacionFiltroDatosSolicitudVOImpl) findViewObject("OperacionFiltroDatosSolicitudVO");
}

    /**
     * Container's getter for SolicitudDesembolsoVO1.
     * @return SolicitudDesembolsoVO1
     */
    public SolicitudDesembolsoVOImpl getSolicitudDesembolsoVO() {
        return (SolicitudDesembolsoVOImpl) findViewObject("SolicitudDesembolsoVO");
    }

    /**
     * Container's getter for TreSolicitudLineaCreditoVO1.
     * @return TreSolicitudLineaCreditoVO1
     */
    public TreSolicitudLineaCreditoVOImpl getTreSolicitudLineaCreditoVO() {
        return (TreSolicitudLineaCreditoVOImpl) findViewObject("TreSolicitudLineaCreditoVO");

    }


    /**
     * Container's getter for ConsultaSolicitudDesembolsoTreeTableVO1.
     * @return ConsultaSolicitudDesembolsoTreeTableVO1
     */
    public ConsultaSolicitudDesembolsoTreeTableVOImpl getConsultaSolicitudDesembolsoTreeTableVO() {
        return (ConsultaSolicitudDesembolsoTreeTableVOImpl) findViewObject("ConsultaSolicitudDesembolsoTreeTableVO");
    }

    /**
     * Container's getter for ConsultaSolicitudLineaCreditoTreeTableVO1.
     * @return ConsultaSolicitudLineaCreditoTreeTableVO1
     */
    public ViewObjectImpl getConsultaSolicitudLineaCreditoTreeTableVO() {
        return (ViewObjectImpl) findViewObject("ConsultaSolicitudLineaCreditoTreeTableVO");
    }

    /**
     * Container's getter for RelacionSolicitudLineaDesembolsoVL1.
     * @return RelacionSolicitudLineaDesembolsoVL1
     */
    public ViewLinkImpl getRelacionSolicitudLineaDesembolsoVL() {
        return (ViewLinkImpl) findViewLink("RelacionSolicitudLineaDesembolsoVL");
    }

    /**
     * Container's getter for ConsultaSolicitudContratoDesembolsoTreeTableVO1.
     * @return ConsultaSolicitudContratoDesembolsoTreeTableVO1
     */
    public ViewObjectImpl getConsultaSolicitudContratoDesembolsoTreeTableVO() {
        return (ViewObjectImpl) findViewObject("ConsultaSolicitudContratoDesembolsoTreeTableVO");
    }

    /**
     * Container's getter for RelacionSolicitudLineaContratoDesembolsoVL1.
     * @return RelacionSolicitudLineaContratoDesembolsoVL1
     */
    public ViewLinkImpl getRelacionSolicitudLineaContratoDesembolsoVL() {
        return (ViewLinkImpl) findViewLink("RelacionSolicitudLineaContratoDesembolsoVL");
    }

    /**
     * Container's getter for DaqtosGenralesDesembolsoVO1.
     * @return DaqtosGenralesDesembolsoVO1
     */
    public DatosGeneralesDesembolsoVOImpl getDatosGeneralesDesembolsoVO() {
        return (DatosGeneralesDesembolsoVOImpl) findViewObject("DatosGeneralesDesembolsoVO");
    }


    /**
     * Container's getter for FuentesExternasContratoDesembolsoVO1.
     * @return FuentesExternasContratoDesembolsoVO1
     */
    public FuentesExternasContratoDesembolsoVOImpl getFuentesExternasContratoDesembolsoVO() {
        return (FuentesExternasContratoDesembolsoVOImpl) findViewObject("FuentesExternasContratoDesembolsoVO");
    }

    /**
     * Container's getter for TreLineaPasivaVO1.
     * @return TreLineaPasivaVO1
     */
    public TreLineaPasivaVOImpl getTreLineaPasivaVO() {
        return (TreLineaPasivaVOImpl) findViewObject("TreLineaPasivaVO");
    }
    
    /**
       * Container's getter for ContratoDesembolsoSumMontoDesembolsarVO1.
       * @return ContratoDesembolsoSumMontoDesembolsarVO1
       */
      public ContratoDesembolsoSumMontoDesembolsarVOImpl getContratoDesembolsoSumMontoDesembolsarVO() {
          return (ContratoDesembolsoSumMontoDesembolsarVOImpl) findViewObject("ContratoDesembolsoSumMontoDesembolsarVO");
      }


    /**
     * Container's getter for ConsultaSolicitudDesembolsoFilterVO.
     * @return ConsultaSolicitudDesembolsoFilterVO
     */
    public ConsultaSolicitudDesembolsoFilterVOImpl getConsultaSolicitudDesembolsoFilterVO() {
        return (ConsultaSolicitudDesembolsoFilterVOImpl) findViewObject("ConsultaSolicitudDesembolsoFilterVO");
    }

    /**
     * Container's getter for DatosDesembolsoVO1.
     * @return DatosDesembolsoVO1
     */
    public DatosDesembolsoVOImpl getDatosDesembolsoVO() {
        return (DatosDesembolsoVOImpl) findViewObject("DatosDesembolsoVO");
    }

    /**
     * Container's getter for ContratoDesembolsoTreeVO.
     * @return ContratoDesembolsoTreeVO
     */
    public ContratoDesembolsoTreeVOImpl getContratoDesembolsoTreeVO() {
        return (ContratoDesembolsoTreeVOImpl) findViewObject("ContratoDesembolsoTreeVO");
    }

    /**
     * Container's getter for LineaCreditoDesembolsosVO1.
     * @return LineaCreditoDesembolsosVO1
     */
    public LineaCreditoDesembolsosVOImpl getLineaCreditoDesembolsosVO1() {
        return (LineaCreditoDesembolsosVOImpl) findViewObject("LineaCreditoDesembolsosVO1");
    }

    /**
     * Container's getter for ContratoDesembolsoTreeVO1.
     * @return ContratoDesembolsoTreeVO1
     */
    public ContratoDesembolsoTreeVOImpl getContratoDesembolsoTreeVO1() {
        return (ContratoDesembolsoTreeVOImpl) findViewObject("ContratoDesembolsoTreeVO1");
    }

    /**
     * Container's getter for LineaCreditoAndContratoDesembolsoVL1.
     * @return LineaCreditoAndContratoDesembolsoVL1
     */
    public ViewLinkImpl getLineaCreditoAndContratoDesembolsoVL1() {
        return (ViewLinkImpl) findViewLink("LineaCreditoAndContratoDesembolsoVL1");
    }

    /**
     * Container's getter for ConsultaLineaCreditoVO1.
     * @return ConsultaLineaCreditoVO1
     */
    public ConsultaLineaCreditoVOImpl getConsultaLineaCreditoVO() {
        return (ConsultaLineaCreditoVOImpl) findViewObject("ConsultaLineaCreditoVO");
    }
    
    /**
     * Container's getter for ContratoDesembolsoVO1.
     * @return ContratoDesembolsoVO1
     */
    public ContratoDesembolsoVOImpl getCrearActualizarContratoDesembolsoVO() {
        return (ContratoDesembolsoVOImpl) findViewObject("CrearActualizarContratoDesembolsoVO");
    }

    /**
     * Container's getter for ContratoDesembolsoVO1.
     * @return ContratoDesembolsoVO1
     */
    public ContratoDesembolsoVOImpl getContratoDesembolsoVO() {
        return (ContratoDesembolsoVOImpl) findViewObject("ContratoDesembolsoVO");
    }

    /**
     * Container's getter for LineaCreditoDesembolsosVO1.
     * @return LineaCreditoDesembolsosVO1
     */
    public LineaCreditoDesembolsosVOImpl getLineaCreditoDesembolsosVO() {
        return (LineaCreditoDesembolsosVOImpl) findViewObject("LineaCreditoDesembolsosVO");
    }

    /**
     * Container's getter for ContratoDesembolsoVO1.
     * @return ContratoDesembolsoVO1
     */
    public ContratoDesembolsoVOImpl getContratoDesembolsoVO1() {
        return (ContratoDesembolsoVOImpl) findViewObject("ContratoDesembolsoVO1");
    }

    /**
     * Container's getter for LineaCreditoAndContratoDesembolso1.
     * @return LineaCreditoAndContratoDesembolso1
     */
    public ViewLinkImpl getLineaCreditoAndContratoDesembolso1() {
        return (ViewLinkImpl) findViewLink("LineaCreditoAndContratoDesembolso1");
    }

    /**
     * Container's getter for TransferenciaRecursosVO1.
     * @return TransferenciaRecursosVO1
     */
    public TransferenciaRecursosVOImpl getTransferenciaRecursosVO() {
        return (TransferenciaRecursosVOImpl) findViewObject("TransferenciaRecursosVO");
    }

    /**
     * Container's getter for VcaCuentasNostroBcieVO1.
     * @return VcaCuentasNostroBcieVO1
     */
    public VcaCuentasNostroBcieVOImpl getVcaCuentasNostroBcieVO() {
        return (VcaCuentasNostroBcieVOImpl) findViewObject("VcaCuentasNostroBcieVO");
    }

    /**
     * Container's getter for VcaCtasClientePasivasVO1.
     * @return VcaCtasClientePasivasVO1
     */
    public VcaCtasClientePasivasVOImpl getVcaCtasClientePasivasVO() {
        return (VcaCtasClientePasivasVOImpl) findViewObject("VcaCtasClientePasivasVO");
    }

    /**
     * Container's getter for TipoMonedaDeSolicitudDesembolsosVO1.
     * @return TipoMonedaDeSolicitudDesembolsosVO1
     */
    public TipoMonedaDeSolicitudDesembolsosVOImpl getTipoMonedaDeSolicitudDesembolsosVO() {
        return (TipoMonedaDeSolicitudDesembolsosVOImpl) findViewObject("TipoMonedaDeSolicitudDesembolsosVO");
    }


    /**
     * Container's getter for DatosContratoDesembolsoVO1.
     * @return DatosContratoDesembolsoVO1
     */
    public DatosContratoDesembolsoVOImpl getDatosContratoDesembolsoVO() {
        return (DatosContratoDesembolsoVOImpl) findViewObject("DatosContratoDesembolsoVO");
    }


    /**
     * Container's getter for DatosHerramientaVO1.
     * @return DatosHerramientaVO1
     */
    public DatosHerramientaVOImpl getDatosHerramientaVO() {
        return (DatosHerramientaVOImpl) findViewObject("DatosHerramientaVO");
    }


    /**
     * Container's getter for TransferenciaBancariaVO1.
     * @return TransferenciaBancariaVO1
     */
    public TransferenciaBancariaVOImpl getTransferenciaBancariaVO() {
        return (TransferenciaBancariaVOImpl) findViewObject("TransferenciaBancariaVO");
    }

    /**
     * Container's getter for TipoOpcionInstruccionPagoLOV1.
     * @return TipoOpcionInstruccionPagoLOV1
     */
    public ViewObjectImpl getTipoOpcionInstruccionPagoLOV() {
        return (ViewObjectImpl) findViewObject("TipoOpcionInstruccionPagoLOV");
    }

    /**
     * Container's getter for TipoMensajeInstruccionPagoLOV1.
     * @return TipoMensajeInstruccionPagoLOV1
     */
    public ViewObjectImpl getTipoMensajeInstruccionPagoLOV() {
        return (ViewObjectImpl) findViewObject("TipoMensajeInstruccionPagoLOV");
    }

    /**
     * Container's getter for TransferenciasBancariasTablaVO1.
     * @return TransferenciasBancariasTablaVO1
     */
    public TransferenciasBancariasTablaVOImpl getTransferenciasBancariasTablaVO() {
        return (TransferenciasBancariasTablaVOImpl) findViewObject("TransferenciasBancariasTablaVO");
    }

    /**
     * Container's getter for ProgramaOperacionVO1.
     * @return ProgramaOperacionVO1
     */
    public ProgramaOperacionVOImpl getProgramaOperacionVO() {
        return (ProgramaOperacionVOImpl) findViewObject("ProgramaOperacionVO");
    }

    /**
     * Container's getter for DestinoVO1.
     * @return DestinoVO1
     */
    public DestinoVOImpl getDestinoVO() {
        return (DestinoVOImpl) findViewObject("DestinoVO");
    }

    /**
     * Container's getter for CargosContratoDesembolsoVO1.
     * @return CargosContratoDesembolsoVO1
     */
    public CargosContratoDesembolsoVOImpl getCargosContratoDesembolsoVO() {
        return (CargosContratoDesembolsoVOImpl) findViewObject("CargosContratoDesembolsoVO");
    }
      /**
     * Container's getter for DatosActividadVO1.
     * @return DatosActividadVO1
     */
    public DatosActividadVOImpl getDatosActividadVO() {
        return (DatosActividadVOImpl) findViewObject("DatosActividadVO");
    }

    /**
     * Container's getter for ComisionesOtrosCargos1.
     * @return ComisionesOtrosCargos1
     */
    public ComisionesOtrosCargosImpl getComisionesOtrosCargos() {
        return (ComisionesOtrosCargosImpl) findViewObject("ComisionesOtrosCargos");
    }
      /**
     * Container's getter for SectorGarantiaVO1.
     * @return SectorGarantiaVO1
     */
    public SectorGarantiaVOImpl getSectorGarantiaVO() {
        return (SectorGarantiaVOImpl) findViewObject("SectorGarantiaVO");
    }
    
    /**
     * Container's getter for DatosEjeVO1.
     * @return DatosEjeVO1
     */
    public DatosEjeVOImpl getDatosEjeVO() {
        return (DatosEjeVOImpl) findViewObject("DatosEjeVO");
    }

    /**
     * Container's getter for DatosAreaVO1.
     * @return DatosAreaVO1
     */
    public DatosAreaVOImpl getDatosAreaVO() {
        return (DatosAreaVOImpl) findViewObject("DatosAreaVO");
    }

    /**
     * Container's getter for DatosActividadF1VO1.
     * @return DatosActividadF1VO1
     */
    public DatosActividadF1VOImpl getDatosActividadF1VO() {
        return (DatosActividadF1VOImpl) findViewObject("DatosActividadF1VO");
    }

    /**
     * Container's getter for DatoProyectoVO1.
     * @return DatoProyectoVO1
     */
    public DatoProyectoVOImpl getDatoProyectoVO() {
        return (DatoProyectoVOImpl) findViewObject("DatoProyectoVO");
    }

    /**
     * Container's getter for TreSolicitudLineaCreditoVO1.
     * @return TreSolicitudLineaCreditoVO1
     */
    public TreSolicitudLineaCreditoVOImpl getTreSolicitudLineaCreditoConsultaVO() {
        return (TreSolicitudLineaCreditoVOImpl) findViewObject("TreSolicitudLineaCreditoConsultaVO");
    }

    /**
     * Container's getter for TcaTipoMonedaVO1.
     * @return TcaTipoMonedaVO1
     */
    public TcaTipoMonedaVOImpl getTcaTipoMonedaVO() {
        return (TcaTipoMonedaVOImpl) findViewObject("TcaTipoMonedaVO");
    }
    
     public Long insertarFormulariosJustificacionExcepcion(){
        logger.log(ADFLogger.WARNING,"Into insertarFormulariosJustificacionExcepcion");
       
        //variable de los formularios que se muestran en la vista 
        FormularioJustificacionExcepcionVORowImpl formularioJustificacionVORowImpl = (FormularioJustificacionExcepcionVORowImpl) this.getFormularioJustificacionExcepcionVO().getCurrentRow();

        //variable para realisar insert en el modelo, estas VO´s estan basadas en una EO
        JustificacionExcepcionVOImpl justificacionExcepcionVOImpl = this.getJustificacionExcepcionVO();;

        ConsultaJustificacionExcepcionVOImpl consultarJustificacionExcepcionVOImpl = this.getConsultaJustificacionExcepcionVO();
        
        //variable del 'id' de la tabla 'JUSTIFICACION_EXCEPCION'
        Long idJustificacionExcepcion = null;
        
        //variables temporales 'formularioJustificacionExcepcion'
        Integer idTcaTareaBpm = null;
        String observacion = null;
        String loginUsuario = null;
        String nombreUsuario = null;
        Long idSolicitud = null;
        Timestamp fechaRegistro = null;
        String instanciaProceso = null;
        Integer banEstatus = null;
        Integer idRol = null;
        
        
        //validar que los formularios de la vista no sean null
        if(null != formularioJustificacionVORowImpl ){ 
            //recuperar los valorés de 'rowFormularioObservacionPrepago'
            if(null != formularioJustificacionVORowImpl.getIdTcaTareaBpm()){
                idTcaTareaBpm = formularioJustificacionVORowImpl.getIdTcaTareaBpm();
            }
            if(null != formularioJustificacionVORowImpl.getObservacion()){
                observacion = formularioJustificacionVORowImpl.getObservacion();
            }
            if(null != formularioJustificacionVORowImpl.getLoginUsuario()){
                loginUsuario = formularioJustificacionVORowImpl.getLoginUsuario();
            }
            if(null != formularioJustificacionVORowImpl.getNombreUsuario()){
                nombreUsuario = formularioJustificacionVORowImpl.getNombreUsuario();
            }
            
            if(null != formularioJustificacionVORowImpl.getInstanciaProceso()){
                instanciaProceso = formularioJustificacionVORowImpl.getInstanciaProceso();
            }
            
            if(null != formularioJustificacionVORowImpl.getIdSolicitud()){
                idSolicitud = formularioJustificacionVORowImpl.getIdSolicitud();
            }
            
            if(null != formularioJustificacionVORowImpl.getBanEstatus()){
                banEstatus = formularioJustificacionVORowImpl.getBanEstatus();
            }
            
            if(null != formularioJustificacionVORowImpl.getFechaRegistro()){
                fechaRegistro = formularioJustificacionVORowImpl.getFechaRegistro();
            }
            
            if(null != formularioJustificacionVORowImpl.getIdRol()){
                idRol = formularioJustificacionVORowImpl.getIdRol();
            }
            
            //Crear el registro en 'JUSTIFICACION_EXCEPCION' y obtener el 'id' que se creo
            idJustificacionExcepcion = justificacionExcepcionVOImpl.crearJustificacionExcepcion(idTcaTareaBpm, observacion, 
                                                                                                loginUsuario, nombreUsuario, 
                                                                                                instanciaProceso, idSolicitud, idRol);
            
            logger.log(ADFLogger.WARNING,"ID value of the table 'JUSTIFICACION_EXCEPCION' is : " + idJustificacionExcepcion);

            //logger.log(ADFLogger.WARNING,"Antes de realizar consulta");            
            //consultarJustificacionExcepcionVOImpl.setpIdSolicitud(idSolicitud);
            //logger.log(ADFLogger.WARNING,"Despues de realizar consulta");                
                                                  
        }else{
            
            if(null == formularioJustificacionVORowImpl){
                logger.log(ADFLogger.ERROR,"formularioJustificacionVORowImpl is null.");
            }
            
                logger.log(ADFLogger.ERROR,"Row in formularioJustificacionVORowImpl no insert.");
        }
        
        logger.log(ADFLogger.WARNING,"successful method insertarFormulariosDesembolso, value idJustificacionExcepcion is : "+ idJustificacionExcepcion);
        
        return idJustificacionExcepcion;
    }
     
    public Long llenarFormularioGeneracionFT05ModoLectura(String cuentaPuente){
        logger.log(ADFLogger.WARNING,"INTO llenarFormularioGeneracionFT05ModoLectura");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        Long id = null;
        String contratoFlexcube = null; 
        String cuentaCliente = null; 
        String bhqTransferenciaFt05 = null; 
        Timestamp fechaEfectiva = null; 
        Timestamp fechaEfectivaFt05 = null;
        Long idTransferenciaFT05 = null;
        
        FormularioGeneracionFT05VOImpl formularioGeneracionFt05 = this.getFormularioGeneracionFT05VO();
        
        ContratoDesembolsoQueryVORowImpl consultarContratoDesembolsoVORowImpl = (ContratoDesembolsoQueryVORowImpl)
            this.getContratoDesembolsoQueryVO().getRowAtRangeIndex(0);
        
        if(consultarContratoDesembolsoVORowImpl != null){
            
            //FORMULARIO GENERACION FT05
            
            if(null != consultarContratoDesembolsoVORowImpl.getId()){
                id = consultarContratoDesembolsoVORowImpl.getId(); 
            }
            if(null != consultarContratoDesembolsoVORowImpl.getContratoFlexcube()){
                contratoFlexcube = consultarContratoDesembolsoVORowImpl.getContratoFlexcube(); 
            }
            if(null != consultarContratoDesembolsoVORowImpl.getCuentaCliente()){
                cuentaCliente = consultarContratoDesembolsoVORowImpl.getCuentaCliente();
            }
            if(null != consultarContratoDesembolsoVORowImpl.getBhqTransferenciaFt05()){
                bhqTransferenciaFt05 = consultarContratoDesembolsoVORowImpl.getBhqTransferenciaFt05();
            }
            if(null != consultarContratoDesembolsoVORowImpl.getFechaEfectiva()){
                fechaEfectiva = consultarContratoDesembolsoVORowImpl.getFechaEfectiva();
            }
            if(null != consultarContratoDesembolsoVORowImpl.getFechaEfectivaFt05()){
                fechaEfectivaFt05 = consultarContratoDesembolsoVORowImpl.getFechaEfectivaFt05();
            }
            if(null != consultarContratoDesembolsoVORowImpl.getIdTransferenciaFt05()){
                idTransferenciaFT05 = consultarContratoDesembolsoVORowImpl.getIdTransferenciaFt05();
            }
            
            formularioGeneracionFt05.crearRowFormularioGeneracionFT05ModoLectura(id, contratoFlexcube, cuentaCliente, 
                                                                      bhqTransferenciaFt05, fechaEfectiva, 
                                                                      fechaEfectivaFt05, cuentaPuente, idTransferenciaFT05);
        }else{
            if(null == consultarContratoDesembolsoVORowImpl){
                logger.log(ADFLogger.ERROR,"consultarContratoDesembolsoVORowImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"consultarContratoDesembolsoVORowImpl es nulo.");
        }
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/ 1000; //Calculamos los milisegundos de diferencia
        logger.log(ADFLogger.WARNING, "El metodo para llenar el Formulario de Generacion FT05 modo lectura finalizo con una duracion de: "+tiempo+" segundos");
        return id;
    }
    
    public Long llenarFormularioGeneracionFT05ModoGenerar(String cuentaPuente){
        logger.log(ADFLogger.WARNING,"INTO llenarFormularioGeneracionFT05ModoGenerar");
        
        Long id = null;
        String contratoFlexcube = null; 
        String cuentaCliente = null; 
        Timestamp fechaEfectiva = null; 
        
        FormularioGeneracionFT05VOImpl formularioGeneracionFt05 = this.getFormularioGeneracionFT05VO();
        
        ContratoDesembolsoQueryVORowImpl consultarContratoDesembolsoVORowImpl = (ContratoDesembolsoQueryVORowImpl)
            this.getContratoDesembolsoQueryVO().getRowAtRangeIndex(0);
        
        if(consultarContratoDesembolsoVORowImpl != null){
            
            //FORMULARIO GENERACION FT05
            
            if(null != consultarContratoDesembolsoVORowImpl.getId()){
                id = consultarContratoDesembolsoVORowImpl.getId(); 
            }
            if(null != consultarContratoDesembolsoVORowImpl.getContratoFlexcube()){
                contratoFlexcube = consultarContratoDesembolsoVORowImpl.getContratoFlexcube(); 
            }
            if(null != consultarContratoDesembolsoVORowImpl.getCuentaCliente()){
                cuentaCliente = consultarContratoDesembolsoVORowImpl.getCuentaCliente();
            }
            if(null != consultarContratoDesembolsoVORowImpl.getFechaEfectiva()){
                fechaEfectiva = consultarContratoDesembolsoVORowImpl.getFechaEfectiva();
            }
            
            formularioGeneracionFt05.crearRowFormularioGeneracionFT05ModoGenerar(id, contratoFlexcube, cuentaCliente, 
                                                                      fechaEfectiva, cuentaPuente);
        }else{
            if(null == consultarContratoDesembolsoVORowImpl){
                logger.log(ADFLogger.ERROR,"consultarContratoDesembolsoVORowImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"consultarContratoDesembolsoVORowImpl es nulo.");
        }
        logger.log(ADFLogger.WARNING, "El metodo para llenar el Formulario de Generacion FT05 modo generar finalizo.");
        
        return id;
    }
    
    public Boolean validaPreinversion(Long idOperacion) {
        logger.log(ADFLogger.WARNING, "El metodo para llenar el Formulario de Generacion FT05 modo generar finalizo.");  
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        Boolean result = Boolean.FALSE;
        
        //consultamos el id del o los padres de la operacion (una operacion puede tener muchos padres)
        ConsultaOperacionPadreAsociadaVOImpl consultaOperacionPadreAsociadaVO = this.getConsultaOperacionPadreAsociadaVO();        
        consultaOperacionPadreAsociadaVO.setvarIdOperacion(idOperacion);
        
        //Iteramos sobre las operaciones encontradas
        RowSetIterator operacionPadreiterator = consultaOperacionPadreAsociadaVO.createRowSetIterator(null);        
        operacionPadreiterator.reset();
        
        while (operacionPadreiterator.hasNext()) {
            Row row = operacionPadreiterator.next();
            
            if (row.getAttribute("IdOperacion") != null) {
                Long idOperacionPadre = (Long) row.getAttribute("IdOperacion");                
                logger.warning("idOperacionPadre: " + idOperacionPadre);
           
             //  ahora verificamos si la operacion tiene un producto de tipo preinvercion
                if (operacionPadrePreinversion(idOperacionPadre)) {
           
            //  ahora verificamos si tiene al menos un contrato de de desembolso en estado registrado o desembolsado
                    result = consultarOperacionesAsociadas(idOperacionPadre, result);    
                } else {
                    logger.warning("Operacion padre no es Preinversion.");
                }
            }
        }
        
        operacionPadreiterator.closeRowSetIterator();
        
        logger.warning("validaPreinversion: " + result);
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
        logger.warning("*Inf, Termina metodo validaPreinversion con una duracion de: "+tiempo+" segundos");
        return result;
    }
    
    public Boolean consultarOperacionesAsociadas(Long idOperacionPadre, Boolean result) {
        logger.warning("Inside consultarOperacionesAsociadas.");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        ConsultarOperacionesAsocidasVOImpl consultarOperacionesAsocidasVO = this.getConsultarOperacionesAsocidasVO();        
        consultarOperacionesAsocidasVO.setidOperacion(idOperacionPadre);
        
        RowSetIterator operacionAsociadaiterator = consultarOperacionesAsocidasVO.createRowSetIterator(null);    
        operacionAsociadaiterator.reset();
        
        while (operacionAsociadaiterator.hasNext()) {
            Row row = operacionAsociadaiterator.next();
            if (row.getAttribute("IdOpAsoc") != null) {
                Long idOperacionAsociada = (Long) row.getAttribute("IdOpAsoc");
                logger.warning("idOperacionAsociada: " + idOperacionAsociada);
                //revisamos si al menos un contrato de la operacion se encuentra en un estado
                //registrado o desembolsado
                result = consultarContratosOperacion(idOperacionAsociada, result);
            }
        }
        
        operacionAsociadaiterator.closeRowSetIterator();
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
        logger.warning("*Inf, Termina metodo consultarOperacionesAsociadas con una duracion de: "+tiempo+" segundos");
        return result;
    }
    
    public Boolean consultarContratosOperacion (Long idOperacionAsociada, Boolean result) {
        logger.warning("Inside consultarContratosOperacion.");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        ContratosDesembolsoOperacionVOImpl contratosDesembolsoOperacionVO = this.getContratosDesembolsoOperacionVO();        
        contratosDesembolsoOperacionVO.setvIdOperacion(idOperacionAsociada);
        
        if (contratosDesembolsoOperacionVO.getEstimatedRowCount() > 0) {
           
        }else{
                result = Boolean.TRUE;
            }
        
        logger.warning("validaPreinversionContratos: " + result);
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
        logger.warning("*Inf, Termina metodo consultarContratosOperacion con una duracion de: "+tiempo+" segundos");
        return result;
    }
    
    public Boolean operacionPadrePreinversion(Long idOperacionPadre) {
        logger.warning("Inside operacionPadrePreinversion.");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        logger.warning("idOperacionPadre: " + idOperacionPadre);
        
        Boolean result = Boolean.FALSE;
        Integer PRODUCTO_PREINVERSION = 16;
        Integer idProducto = null;
        
        //Consultamos el producto de la operacion para saber si es de tipo preinvercion
        ConsultarProductoOperacionImpl consultarProductoOperacionImpl = this.getConsultarProductoOperacion();        
        idProducto = consultarProductoOperacionImpl.obtenerProductoOperacion(idOperacionPadre);
        
        if (idProducto != null){
            if (idProducto.compareTo(PRODUCTO_PREINVERSION) == 0) {
                result = Boolean.TRUE;
                logger.warning("El tipo de Producto de la Operacion es Preinversion: " + idProducto);
            } else {
                logger.warning("El tipo de Producto de la Operacion No es Preinversion: " + idProducto);
            }
        }
        
        logger.warning("operacionPadrePreinversion: " + result);
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
        logger.warning("*Inf, Termina metodo operacionPadrePreinversion con una duracion de: "+tiempo+" segundos");
        return result;
    }
    
    
    
    public String recuperarValoresPreinversion(Long idOperacion, Long idDesembolso){
            logger.warning("*Inf, inicia el metodo recuperarValoresPreinversion");          
            double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
            TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
            
            logger.warning("*Inf, buscando operaciones padre...");
            String error = "";
            //consultamos el id del o los padres de la operacion (una operacion puede tener muchos padres)
            ConsultaOperacionPadreAsociadaVOImpl consultaOperacionPadreAsociadaVO = this.getConsultaOperacionPadreAsociadaVO();        
            consultaOperacionPadreAsociadaVO.setvarIdOperacion(idOperacion);
            
            //Iteramos sobre las operaciones encontradas
            RowSetIterator operacionPadreiterator = consultaOperacionPadreAsociadaVO.createRowSetIterator(null);        
            operacionPadreiterator.reset();
                        
            
            while (operacionPadreiterator.hasNext()) {
                Row row = operacionPadreiterator.next();
                
                if (row.getAttribute("IdOperacion") != null) {
                    Long idOperacionPadre = (Long) row.getAttribute("IdOperacion");                
                    logger.warning("*Inf, --- OperacionPadre: " + idOperacionPadre+"---");
               
                 //  ahora verificamos si la operacion tiene un producto de tipo preinvercion
                    if (operacionPadrePreinversion(idOperacionPadre)) {
                                  
                    //  recupoeramos la suma de los contratos de desembolso en estado registrado o desembolsado
                       ContratosDesembolsoOperacionVOImpl contratosDesembolsoOperacionVO = this.getContratosDesembolsoOperacionVO();
                        BigDecimal  montoPreinversion = contratosDesembolsoOperacionVO.sumaMontoContratos(idOperacionPadre);   
                        BigDecimal  tasaInteres = getConsultarDatosOperacionVO().recuperarTasaInteres(idOperacionPadre);                    
                        BigDecimal  montoTotal = montoPreinversion.add(tasaInteres);
                        
                        logger.warning("*---Inf valores recuperados---");
                        logger.warning("idOperacionPadre: " + idOperacionPadre);
                        logger.warning("montoPreinversion: " + montoPreinversion);
                        logger.warning("tasaInteres: " + tasaInteres);
                        logger.warning("montoTotal: " + montoTotal);
                        
                        SaldoPreinversionDesembolsoVO SaldoPreinversionVO = this.getSaldoPreinversionDesembolsoVO();
                        error = SaldoPreinversionVO.recuperarRegistro(idOperacionPadre, idDesembolso, montoPreinversion, tasaInteres, montoTotal, "crearActualizar");                        
                                               
                    }else{
                        logger.warning("*Inf, important! Operacion padre "+idOperacionPadre+" no es Preinversion.");
                        SaldoPreinversionDesembolsoVO SaldoPreinversionVO = this.getSaldoPreinversionDesembolsoVO();
                    String string = error = SaldoPreinversionVO.recuperarRegistro(idOperacion, idOperacionPadre, null, null, null, "eliminar");
                        
                    }
                }
                
            }            
            operacionPadreiterator.closeRowSetIterator();
            
            ValoresPreinversionTablaByDesembolsoVOImpl tablaPreinvercion = this.getValoresPreinversionTablaByDesembolsoVO();
            tablaPreinvercion.cargartabla(idDesembolso);
            
            TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
            tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia            
            logger.warning("*Inf, Termina metodo recuperarValoresPreinversion con una duracion de: "+tiempo+" segundos");
            return error;
        }
    
    public Map buscarCondicionFinancieraPorIdContrato(Long idContrato){
        logger.warning("Entra en validarCondicionesFinancieras.");
        Map resultado = new HashMap<String, Object>();
        Boolean esValidado = Boolean.TRUE;
        Row rowCondicionFinanciera = null;
        CondicionesFinancierasVOImpl condicionesFinancierasVOImpl = this.getCondicionesFinancierasVO();
        try{
            logger.warning("Buscando condicion financiera para contrato :" + idContrato) ;
            
            // FNXII-6558, se corrige perdida de informacion. 
            // Importante: el currentRow debe encontrarse precargado al cargar la pantalla,
            // tanto en GestorDesembolsos como en el Proceso.
            rowCondicionFinanciera = condicionesFinancierasVOImpl.getCurrentRow();
            
            resultado = validarCamposCondicionFinanciera(rowCondicionFinanciera, null, idContrato);
            if(null!= resultado){
                    esValidado = (Boolean)resultado.get("esValidado");
                    logger.warning("Es validado :" + esValidado);  
                }
            else{
                logger.warning("No se obtuvo el row de condiciones financieras");
                }
        }catch(Exception e){
            logger.warning("Error al obtener el contrato.", e);
        }
        logger.warning("Termina metodo Validar campos de condiciones financieras." + (Boolean)resultado.get("esValidado"));
        return resultado;
    }
    
    public Map buscarCondicionFinancieraPorIdContrato(Long idContrato,Row newRow){
        logger.warning("Entra en validarCondicionesFinancieras.");
        Map resultado = new HashMap<String, Object>();
        Boolean esValidado = Boolean.TRUE;
        Row rowCondicionFinanciera = null;
        CondicionesFinancierasVOImpl condicionesFinancierasVOImpl = this.getCondicionesFinancierasVO();
        try{
            logger.warning("Buscando condicion financiera para contrato :" + idContrato) ;
            
            // FNXII-6558, se corrige perdida de informacion. 
            // Importante: el currentRow debe encontrarse precargado al cargar la pantalla,
            // tanto en GestorDesembolsos como en el Proceso.
            rowCondicionFinanciera = condicionesFinancierasVOImpl.getCurrentRow();
            
            resultado = validarCamposCondicionFinanciera(rowCondicionFinanciera, newRow, idContrato);
            if(null!= resultado){
                    esValidado = (Boolean)resultado.get("esValidado");
                    logger.warning("Es validado :" + esValidado);  
                }
            else{
                logger.warning("No se obtuvo el row de condiciones financieras");
                }
        }catch(Exception e){
            logger.warning("Error al obtener el contrato.", e);
        }
        logger.warning("Termina metodo Validar campos de condiciones financieras." + (Boolean)resultado.get("esValidado"));
        return resultado;
    }
    
    private Map validarCamposCondicionFinanciera(Row rowCondicionFinanciera, Row newRow,Long idContrato){
        logger.warning("Entra en validarCamposCondicionFinanciera para el contrato :"+idContrato);
        Map resultado = new HashMap<String, Object>();
        String mensaje = null;
        String mje = null;
        String replace = null;
        Boolean esValidado = Boolean.TRUE;
        Integer idTcaTipoCalendario = null;
        Integer idEspecificacionFecha = null;
        Integer idTcaTipoTasaDesembolso = null;
        
        try{
            ResourceBundle rb = BundleFactory.getBundle("org.bcie.fenix.common.model.FenixModelBundle");
            mje = rb.getString("MSG_VALORES_NULOS");;
            mensaje = mje.replace("{0}", idContrato.toString());
            if(null != rowCondicionFinanciera){
                if(null != rowCondicionFinanciera.getAttribute("IdTcaTipoCalendario")){
                    idTcaTipoCalendario = (Integer)rowCondicionFinanciera.getAttribute("IdTcaTipoCalendario");
                    if (idTcaTipoCalendario.compareTo(1) == 0) {
                         logger.warning("Valida campos de tipo de calendario complejo.");
                         resultado = validaDatosGeneralesCondicionFinanciera(rowCondicionFinanciera,newRow, mensaje);
                         esValidado = (Boolean) resultado.get("esValidado");
                    } else {
                         logger.warning("Valida campos de tipo de calendario simple.");
                         resultado = validaDatosGeneralesCondicionFinanciera(rowCondicionFinanciera,newRow, mensaje);
                         esValidado = (Boolean) resultado.get("esValidado");
                        if(newRow!=null){
                            if (null == newRow.getAttribute("IdTcaFrecuenciaPagoCapital") || null == newRow.getAttribute("FrecuenciaPagoCapital")) {
                                logger.warning(mensaje + " Frecuencia de capital.");
                                esValidado = Boolean.FALSE;
                                resultado.put("mjeFrecuenciaPagoCapital", mensaje + " Frecuencia de capital.");
                            }
                        }
                        
                         if(null != rowCondicionFinanciera.getAttribute("IdTcaEspecificacionFecha")){
                             idEspecificacionFecha = (Integer)rowCondicionFinanciera.getAttribute("IdTcaEspecificacionFecha");
                             logger.warning("Valor de IdTcaEspecificacionFecha :" + idEspecificacionFecha);
                             if(idEspecificacionFecha.compareTo(1) == 0){
                                     if(newRow!=null){
                                         if (null == newRow.getAttribute("FechaPrimerPagoCapital")) {
                                             logger.warning(mensaje + " Fecha de primer pago de capital.");
                                             esValidado = Boolean.FALSE;
                                             resultado.put("mjeFechaPagoCapital", mensaje + " Fecha de primer pago de capital.");
                                         }
                                     }
                                     
                                     if (null == rowCondicionFinanciera.getAttribute("FechaVencimiento")) {
                                         logger.warning(mensaje + " Fecha de vencimiento.");
                                         esValidado = Boolean.FALSE;
                                         resultado.put("mjeFechaVencimiento", mensaje + " Fecha de vencimiento.");
                                     }
                                     
                                     idTcaTipoTasaDesembolso = (Integer)rowCondicionFinanciera.getAttribute("IdTcaTipoTasaDesembolso");
                                     
                                    logger.warning("idTcaTipoTasaDesembolso: "+idTcaTipoTasaDesembolso);
                                     
                                     if(idTcaTipoTasaDesembolso != null && idTcaTipoTasaDesembolso.compareTo(new Integer(1)) != 0){
                                         if(newRow!=null){
                                             if (null == newRow.getAttribute("FechaProximoPagoInteres")) {
                                                 logger.warning(mensaje + " Fecha de proximo pago de interes.");
                                                 esValidado = Boolean.FALSE;
                                                 resultado.put("mjeFechaPagoInteres", mensaje + " Fecha de proximo pago de interes.");
                                             }
                                         }
                                     }else{
                                        logger.warning("no se validan fecha proximo de pago de interes para un tipo de tasa especial");     
                                    }
                                     
                                     
                                     
                             }else{
                                 logger.warning("La especificacion no es por fecha, no se validan los campos.");
                             }
                         }
                         if(idTcaTipoTasaDesembolso != null && idTcaTipoTasaDesembolso.compareTo(new Integer(1)) != 0){
                             if(newRow!=null){
                                 if (null == newRow.getAttribute("FrecuenciaPagoInteres") || null == newRow.getAttribute("IdTcaFrecuenciaPagoInteres")) {
                                     logger.warning(mensaje + " Frecuencia de pago de interes.");
                                     esValidado = Boolean.FALSE;
                                     resultado.put("mjeFrecuenciaPagoInteres", mensaje + " Frecuencia de pago de interes.");
                                 }
                             }
                         }else{
                             logger.warning("no se validan frecuencia de pago de interes para un tipo de tasa especial");
                         }
                        
                     }
                }else{
                    if(null == rowCondicionFinanciera.getAttribute("IdTcaTipoCalendario")){
                        logger.warning(mensaje + " Tipo de calendario.");
                        esValidado = Boolean.FALSE;
                        resultado.put("mjeTipoCalendario", mensaje + " Tipo de calendario.");
                    }
                }
            }else{
                logger.warning("El registro de condiciones financieras no existe para el contrato : " + idContrato);
                esValidado = Boolean.FALSE;
                mensaje = "Es necesario ingresar las condiciones financieras para el contrato : " + idContrato;
                resultado.put("condicionFinanciera", mensaje);
            }
            resultado.put("esValidado", esValidado);
        }catch(Exception e){
            logger.warning("Error al validar campos.", e);
        }
        return resultado;
    }
    
    private Map validaDatosGeneralesCondicionFinanciera(Row rowCondicionFinanciera, Row newRow, String mensaje){
        logger.warning("Entra en validaDatosGeneralesCondicionFinanciera");
        Map resultado = new HashMap<String, Object>();
        Boolean esValidado = Boolean.TRUE;
        Boolean validaProducto = Boolean.FALSE;
        String idProducto = null;
        Integer idEspecificacionFecha = null;
        try{
            if(null == rowCondicionFinanciera.getAttribute("IdTcaTipoTasaDesembolso")){
                logger.warning(mensaje + " Tipo de tasa.");
                esValidado = Boolean.FALSE;
                resultado.put("mjeTipoTasa", mensaje + " Tipo de tasa.");
            }else{
                 Integer tasa = (Integer)rowCondicionFinanciera.getAttribute("IdTcaTipoTasaDesembolso");
                
                 switch(tasa){
                 case 1:                        
                     logger.warning("Evaluando los campos para un tipo de tasa especial");                        
                     logger.warning("No hay campos obligatorios para el tipo de tasa");
                 break;
                 case 2:
                     logger.warning("Evaluando los campos para un tipo de tasa fija");
                     if(newRow!=null){
                         if(null == newRow.getAttribute("ValorTasaI")){
                             logger.warning(mensaje + " Valor Tasa.");
                             esValidado = Boolean.FALSE;
                             resultado.put("mjeSpreadTasa", mensaje + " Valor Tasa.");
                         }else{
                             logger.warning("ok campo ValorTasa validados ");
                         }
                     }
                 break;
                 case 3:
                     logger.warning("Evaluando los campos para un tipo de tasa variable");
                     if(newRow!=null){
                         // Validacion componente Interes
                             if(null == newRow.getAttribute("SpreadI")){
                                 logger.warning(mensaje + " Spread.");
                                 esValidado = Boolean.FALSE;
                                 resultado.put("mjeSpreadTasa", mensaje + " Spread del componente Interés.");
                             }else{
                                 logger.warning("ok campo SpreadTasa validado");
                             }
                         
                             if (null == newRow.getAttribute("FrecuenciaRevisionTasaI") || null == newRow.getAttribute("IdTcaFrecuenciaRevTasaI")) {
                                  logger.warning(mensaje + " Frecuencia de revision de tasa.");
                                  esValidado = Boolean.FALSE;
                                  resultado.put("mjeFrecuenciaRevTasa", mensaje + " Frecuencia de revision de tasa del componente Interés.");
                             }else{
                                 logger.warning("ok campo FrecuenciaRevisionTasa validado");
                             }
                         
                             if(null == newRow.getAttribute("CodigoTasaReferenciaI")){
                                 logger.warning(mensaje + " Codigo de tasa de referncia.");
                                 esValidado = Boolean.FALSE;
                                 resultado.put("mjeDescripcionTasa2", mensaje + " Codigo de tasa de referencia del componente Interés.");
                             }else{
                                 logger.warning("ok campo CodigoTasaReferencia validado");
                             }
                         
                         // Componente mora
                         //Caso4
                         if("No" == newRow.getAttribute("esDependiente") && "Variable" == newRow.getAttribute("tipoTasa")){
                             if(null == newRow.getAttribute("SpreadTasa")){
                                 logger.warning(mensaje + " Spread.");
                                 esValidado = Boolean.FALSE;
                                 resultado.put("mjeSpreadTasaM", mensaje + " Spread del componente Mora.");
                             }else{
                                 logger.warning("ok campo SpreadTasa validado");
                             }
                             
                             if (null == newRow.getAttribute("FrecuenciaRevisionTasa") || null == newRow.getAttribute("IdTcaFrecuenciaRevTasa")) {
                                  logger.warning(mensaje + " Frecuencia de revision de tasa.");
                                  esValidado = Boolean.FALSE;
                                  resultado.put("mjeFrecuenciaRevTasaM", mensaje + " Frecuencia de revision de tasa del componente Mora.");
                             }else{
                                 logger.warning("ok campo FrecuenciaRevisionTasa validado");
                             }
                             
                             if(null == newRow.getAttribute("CodigoTasaReferencia")){
                                 logger.warning(mensaje + " Codigo de tasa de referncia.");
                                 esValidado = Boolean.FALSE;
                                 resultado.put("mjeDescripcionTasa2M", mensaje + " Codigo de tasa de referencia del componente Mora.");
                             }else{
                                 logger.warning("ok campo CodigoTasaReferencia validado");
                             }
                         }
                         
                         //Caso3
                         if("No" == newRow.getAttribute("esDependiente") && "Fijo" == newRow.getAttribute("tipoTasa")){
                             if(null == newRow.getAttribute("ValorTasa")){
                                 logger.warning(mensaje + " Tasa.");
                                 esValidado = Boolean.FALSE;
                                 resultado.put("mjeTasaM", mensaje + " Tasa del componente Mora.");
                             }else{
                                 logger.warning("ok campo Tasa validado");
                             }
                         }
                         
                         //Caso2
                         if("Si" == newRow.getAttribute("esFactor") && "Si" == newRow.getAttribute("esDependiente")){
                             if(null == newRow.getAttribute("SpreadTasa")){
                                 logger.warning(mensaje + " Factor%.");
                                 esValidado = Boolean.FALSE;
                                 resultado.put("mjeFactorM", mensaje + " Factor% del componente Mora.");
                             }else{
                                 logger.warning("ok campo Factor% validado");
                             }
                         }
                         
                         //Caso1
                         if("No" == newRow.getAttribute("esFactor") && "Si" == newRow.getAttribute("esDependiente")){
                             if(null == newRow.getAttribute("SpreadTasa")){
                                 logger.warning(mensaje + " Spread.");
                                 esValidado = Boolean.FALSE;
                                 resultado.put("mjeSpreadM", mensaje + " Spread del componente Mora.");
                             }else{
                                 logger.warning("ok campo Spread validado");
                             }
                         }
                     }
                 break;  
                 }                   
            }               
                           
            if(null == rowCondicionFinanciera.getAttribute("IdTcaEspecificacionFecha")){
                logger.warning(mensaje + " Especificacion de fechas.");
                esValidado = Boolean.FALSE;
                resultado.put("mjeFechaEspecificacion", mensaje + " Especificacion de fechas.");
            }else{
                idEspecificacionFecha = (Integer)rowCondicionFinanciera.getAttribute("IdTcaEspecificacionFecha");
                logger.warning("Valor de IdTcaEspecificacionFecha :" + idEspecificacionFecha);
                Integer tasa = (Integer)rowCondicionFinanciera.getAttribute("IdTcaTipoTasaDesembolso");
                logger.warning("Valor de IdTcaTipoTasaDesembolso :" + tasa);
                if(idEspecificacionFecha.compareTo(1) == 0 && tasa.compareTo(3) == 0){
                    if(newRow!=null){
                        if(null == newRow.getAttribute("FechaProximaRevisionTasaI")){
                            logger.warning(mensaje + " Fecha de revision de tasa.");
                            esValidado = Boolean.FALSE;
                            resultado.put("mjeFechaRevTasa", mensaje + " Fecha de revision de tasa del componente Interés.");
                        }
                        
                        //Fecha Revision Mora
                        if("No" == newRow.getAttribute("esDependiente") && "Variable" == newRow.getAttribute("tipoTasa")){
                            if(null == newRow.getAttribute("FechaProximaRevisionTasa")){
                                logger.warning(mensaje + " Fecha de revision de tasa.");
                                esValidado = Boolean.FALSE;
                                resultado.put("mjeFechaRevTasaM", mensaje + " Fecha de revision de tasa del componente Mora.");
                            }
                        }
                    }
                }else{
                    logger.warning("La especificacion no es por fecha, no se valida el campo.");
                }
                
                //Se verifica que cuando la especificacion sea fechas, y la frecuencia de pago no sea al vencimiento, la fecha de pago y la de vencimeinto no sean iguales JURBINA@26082020                
                if(newRow != null)
                {
                    Integer  IdTcaFrecuenciaPagoCap = null;
                    Integer  IdTcaFrecuenciaPagoInteres = null;
                    java.sql.Timestamp  FechaPrimerPagoCapital = null;
                    java.sql.Timestamp  FechaVencimiento = null;
                    java.sql.Timestamp  FechaProximoPagoInteres = null;   
                    
                    logger.warning("IdTcaFrecuenciaPagoCapital " + newRow.getAttribute("IdTcaFrecuenciaPagoCapital"));
                    logger.warning("IdTcaFrecuenciaPagoInteres " + newRow.getAttribute("IdTcaFrecuenciaPagoInteres"));
                    logger.warning("FechaPrimerPagoCapital " + newRow.getAttribute("FechaPrimerPagoCapital"));
                    logger.warning("FechaVencimiento " + rowCondicionFinanciera.getAttribute("FechaVencimiento"));
                    logger.warning("FechaProximoPagoInteres " + newRow.getAttribute("FechaProximoPagoInteres"));
                    
                    if(null != newRow.getAttribute("IdTcaFrecuenciaPagoCapital"))
                    {
                        IdTcaFrecuenciaPagoCap = (newRow.getAttribute("IdTcaFrecuenciaPagoCapital").toString().equals(""))? null
                                                            : (Integer)newRow.getAttribute("IdTcaFrecuenciaPagoCapital");
                    }
                    
                    if(null != newRow.getAttribute("IdTcaFrecuenciaPagoInteres"))
                    {
                        IdTcaFrecuenciaPagoInteres = (newRow.getAttribute("IdTcaFrecuenciaPagoInteres").toString().equals(""))? null
                                                            : (Integer)newRow.getAttribute("IdTcaFrecuenciaPagoInteres");
                    }
                    
                    FechaPrimerPagoCapital   = (null == newRow.getAttribute("FechaPrimerPagoCapital"))? null
                                                            : (java.sql.Timestamp )newRow.getAttribute("FechaPrimerPagoCapital");
                    
                    FechaVencimiento         = (null ==  rowCondicionFinanciera.getAttribute("FechaVencimiento"))? null
                                                                    : (java.sql.Timestamp ) rowCondicionFinanciera.getAttribute("FechaVencimiento"); 
                    
                    FechaProximoPagoInteres  = (null == newRow.getAttribute("FechaProximoPagoInteres"))? null
                                                                    : (java.sql.Timestamp )newRow.getAttribute("FechaProximoPagoInteres");   
                
                
                    if(IdTcaFrecuenciaPagoCap != 4 && FechaPrimerPagoCapital != null && FechaVencimiento != null )
                    {
                        if(FechaPrimerPagoCapital.equals(FechaVencimiento))
                        {
                            esValidado = Boolean.FALSE;
                            resultado.put("mjeFechaCapVencIgual", "La fecha de primer pago de capital("+ FechaPrimerPagoCapital +") no puede ser igual que la fecha de vencimiento("+ FechaVencimiento +"), en este caso debe usar una frecuencia al vencimiento.");
                        }
                    }
                    
                    if(IdTcaFrecuenciaPagoInteres != 4  && FechaProximoPagoInteres != null && FechaVencimiento != null)
                    {
                        if(FechaProximoPagoInteres.equals(FechaVencimiento))
                        {
                            esValidado = Boolean.FALSE;
                            resultado.put("mjeFechaIntVencIgual", "La fecha de primer pago de interes("+ FechaProximoPagoInteres +") no puede ser igual que la fecha de vencimiento("+ FechaVencimiento +"), en este caso debe usar una frecuencia al vencimiento.");
                        }
                    }
                }
                if(idEspecificacionFecha.compareTo(2) == 0){
                    if(null == rowCondicionFinanciera.getAttribute("FrecuenciaPeriodoGracia") || null == rowCondicionFinanciera.getAttribute("IdTcaFrecuenciaPeriodoGra")){
                        //Solo se valida el periodo de gracia si no es al vencimiento:
                        Integer  IdTcaFrecuenciaPagoCapital = (null == rowCondicionFinanciera.getAttribute("IdTcaFrecuenciaPagoCapital") || rowCondicionFinanciera.getAttribute("IdTcaFrecuenciaPagoCapital").toString().equals(""))? null
                                                            : (Integer)rowCondicionFinanciera.getAttribute("IdTcaFrecuenciaPagoCapital");
                        
                        //cuando no es al vencimiento la frecuencia de pago de capital, se devuelve error si el campo periodo de gracia es null
                        if(IdTcaFrecuenciaPagoCapital != 4)
                        {
                            logger.warning(mensaje + " Periodo de gracia.");
                            esValidado = Boolean.FALSE;
                            resultado.put("mjePeriodoGracia", mensaje + " Periodo de gracia.");
                        }
                    }
                    if(null == rowCondicionFinanciera.getAttribute("FrecuenciaPlazo") || null == rowCondicionFinanciera.getAttribute("IdTcaFrecuenciaPlazo")){
                        logger.warning(mensaje + " Plazo.");
                        esValidado = Boolean.FALSE;
                        resultado.put("mjeFrecuenciaPlazo", mensaje + " Plazo.");
                    }
                    else
                    {
                        Integer  FrecuenciaPlazo = (null == rowCondicionFinanciera.getAttribute("FrecuenciaPlazo") || rowCondicionFinanciera.getAttribute("FrecuenciaPlazo").toString().equals(""))? null
                                                            : (Integer)rowCondicionFinanciera.getAttribute("FrecuenciaPlazo");
                        if(!(FrecuenciaPlazo>0))
                        {
                            logger.warning(mensaje + " Plazo.");
                            esValidado = Boolean.FALSE;
                            resultado.put("mjeFrecuenciaPlazo", "La frecuencia de plazo de capital debe ser mayor que cero.");
                        }
                    }
                }
            }
            if(null == rowCondicionFinanciera.getAttribute("IdProductoFlexcube")){
                logger.warning(mensaje + " Producto." + idProducto);
                esValidado = Boolean.FALSE;
                resultado.put("mjeProducto", mensaje + " Producto.");
            }else{
                idProducto = (String)rowCondicionFinanciera.getAttribute("IdProductoFlexcube");
                logger.warning(" Producto." + idProducto);
            }
            if(null == rowCondicionFinanciera.getAttribute("IdTcaBaseCalculo")){
                logger.warning(mensaje + " Base de calculo.");
                esValidado = Boolean.FALSE;
                resultado.put("mjeBaseCalculo", mensaje + " Base de calculo.");
            }
            if(null == rowCondicionFinanciera.getAttribute("TratamientoDiasFeriados")){
                logger.warning(mensaje + " Dias feriados.");
                esValidado = Boolean.FALSE;
                resultado.put("mjeDiasFeriados", mensaje + " Dias feriados");
            }               
            Integer tasa = (Integer)rowCondicionFinanciera.getAttribute("IdTcaTipoTasaDesembolso");
            logger.warning("Valor de IdTcaTipoTasaDesembolso :" + tasa);
            /*
            if (null == rowCondicionFinanciera.getAttribute("SpreadMora") && tasa.compareTo(1) != 0) {
                 logger.warning(mensaje + " Valor del Spread.");
                 esValidado = Boolean.FALSE;
                 resultado.put("mjeSpreadMora", mensaje + " Valor del Spread.");
            }
            */
            validaProducto = validaspreadVariable(idProducto);
            if(validaProducto){
                logger.warning("El Valor validaProducto es true");
                if(newRow!=null){
                    if(null == newRow.getAttribute("CodigoTasaReferenciaSpread")){
                        logger.warning(mensaje + " Codigo de tasa de referncia (Spread Variable).");
                        esValidado = Boolean.FALSE;
                        resultado.put("mjeCodigoTasaReferenciaSpread", mensaje + " Codigo de tasa de referencia del componente Spread Variable.");
                    }else{
                        logger.warning("ok campo CodigoTasaReferenciaSpread validado");
                    }
                
                    idEspecificacionFecha = (Integer)rowCondicionFinanciera.getAttribute("IdTcaEspecificacionFecha");
                    logger.warning("Valor de IdTcaEspecificacionFecha :" + idEspecificacionFecha);
                    if(idEspecificacionFecha.compareTo(1) == 0){
                        if (null == newRow.getAttribute("FechaProximaRevisionSpread")) {
                             logger.warning(mensaje + " Fecha proxima de revision spread.");
                             esValidado = Boolean.FALSE;
                             resultado.put("mjeFechaProximaRevisionSpread", mensaje + " Fecha proxima de revision spread.");
                         }
                    }
                    if (null == newRow.getAttribute("FrecuenciaRevisionSpread") || null == newRow.getAttribute("IdTcaFrecuenciaRevSpread")) {
                         logger.warning(mensaje + " Valor del Spread.");
                         esValidado = Boolean.FALSE;
                         resultado.put("mjeFrecuenciaRevisionSpread", mensaje + " Frecuencia de revision spread.");
                     }
                }
            }else{
                logger.warning("El valor validaProducto es false, no se realiza validacion de campos : FechaProximaRevisionSpread, FrecuenciaRevisionSpread.");
            }
            if (null != rowCondicionFinanciera.getAttribute("IdTcaTipoCalendario")) {
                Integer tipoCalendario = (Integer)rowCondicionFinanciera.getAttribute("IdTcaTipoCalendario");
                logger.warning("Valor IdTcaTipoCalendario(tipoCalendario): " + tipoCalendario);
                if(tipoCalendario.compareTo(1) == 0){
                    logger.warning("Es calendario complejo, validando documentos");
                    Integer respuesta = validaDocumentosPorConcicionFinanciera((Long)rowCondicionFinanciera.getAttribute("Id"));
                    if(respuesta == null){
                        logger.warning("Se obtiene el valor null al validar los documentos de calendario complejo para la condicion");
                        esValidado = Boolean.FALSE;
                        resultado.put("mjeCalendarioComplejoDocumentos", mensaje + " Documentos sin cargar.");
                    }
                    else if(respuesta.compareTo(1) != 0){
                        esValidado = Boolean.FALSE;
                        logger.warning("Valor obtenido de respuesta al validar los documentos: " + respuesta);
                        resultado.put("mjeCalendarioComplejoDocumentos", mensaje + " Documentos sin cargar.");
                    }
                }
                else{
                    logger.warning("No es calendario complejo, no se validan documentos");
                }
            }
            else{
                logger.warning("IdTcaTipoCalendario es null");
            }
            
            resultado.put("esValidado", esValidado);
        }catch(Exception e){
            logger.warning("Error al validar los campos generales.", e);
        }
        return resultado;
    }
    
    public Boolean validaspreadVariable(String idProductolex){
        logger.warning("Entra en validaspreadVariable");
        
        Boolean isValido = Boolean.FALSE;
        try{
            logger.warning("Entra al try.");
         isValido =
             getVtaProdFlexComponenteDesemVO().consultarSpreadVariableById(idProductolex);
        }catch(Exception e){
            logger.warning("Error en validaspreadVariable", e);
        }
        logger.warning("Valor de retorno :" + isValido);
        return isValido;
    }
    
    public Integer validaDocumentosPorConcicionFinanciera(Long idCondicion){
        logger.warning("Ingresa metodo validaDocumentosPorConcicionFinanciera");
        Integer resultado = null;
        CalendarioComplejoVOImpl calendarioComplejoVOImpl = this.getCalendarioComplejoVO();
        try{
            logger.warning("Buscando documentos para condicion financiera :" + idCondicion) ;
            resultado = calendarioComplejoVOImpl.consultarDocumentosPorIdCondicion(idCondicion);
        }catch(Exception e){
            logger.warning("Error al obtener el contrato.", e);
        }
        logger.warning("Valor a retornar: " + resultado);
        logger.warning("Finaliza metodo validaDocumentosPorConcicionFinanciera");
        return resultado;
    }
    
    /**
     * Container's getter for FormularioJustificacionExcepcionVO1.
     * @return FormularioJustificacionExcepcionVO1
     */
    public FormularioJustificacionExcepcionVOImpl getFormularioJustificacionExcepcionVO() {
        return (FormularioJustificacionExcepcionVOImpl) findViewObject("FormularioJustificacionExcepcionVO");
    }

    /**
     * Container's getter for JustificacionExcepcionVO1.
     * @return JustificacionExcepcionVO1
     */
    public JustificacionExcepcionVOImpl getJustificacionExcepcionVO() {
        return (JustificacionExcepcionVOImpl) findViewObject("JustificacionExcepcionVO");
    }

    /**
     * Container's getter for ConsultaJustificacionExcepcionVO1.
     * @return ConsultaJustificacionExcepcionVO1
     */
    public ConsultaJustificacionExcepcionVOImpl getConsultaJustificacionExcepcionVO() {
        return (ConsultaJustificacionExcepcionVOImpl) findViewObject("ConsultaJustificacionExcepcionVO");
    }
    
        /**
     * Container's getter for TcaTipoTasaDesembolso1.
     * @return TcaTipoTasaDesembolso1
     */
    public TcaTipoTasaDesembolsoVOImpl getTcaTipoTasaDesembolso() {
        return (TcaTipoTasaDesembolsoVOImpl) findViewObject("TcaTipoTasaDesembolso");
    }

    /**
     * Container's getter for TcaEspecificacionFecha1.
     * @return TcaEspecificacionFecha1
     */
    public TcaEspecificacionFechaVOImpl getTcaEspecificacionFechaVO() {
        return (TcaEspecificacionFechaVOImpl) findViewObject("TcaEspecificacionFechaVO");
    }

    /**
     * Container's getter for TcaTipoCalendatio1.
     * @return TcaTipoCalendatio1
     */
    public TcaTipoCalendarioVOImpl getTcaTipoCalendatioVO() {
        return (TcaTipoCalendarioVOImpl) findViewObject("TcaTipoCalendatioVO");
    }


    /**
     * Container's getter for RegistroSolicitudDesembolsoExcepcionVO1.
     * @return RegistroSolicitudDesembolsoExcepcionVO1
     */
    public RegistroSolicitudDesembolsoExcepcionVOImpl getRegistroSolicitudDesembolsoExcepcionVO() {
        return (RegistroSolicitudDesembolsoExcepcionVOImpl) findViewObject("RegistroSolicitudDesembolsoExcepcionVO");
    }


    /**
     * Container's getter for DocumentosTrazabilidadOperacionVO1.
     * @return DocumentosTrazabilidadOperacionVO1
     */
    public DocumentosTrazabilidadOperacionVOImpl getDocumentosTrazabilidadOperacionVO() {
        return (DocumentosTrazabilidadOperacionVOImpl) findViewObject("DocumentosTrazabilidadOperacionVO");
    }

    /**
     * Container's getter for DocumentosTrazabilidadOperacionFiltroVO1.
     * @return DocumentosTrazabilidadOperacionFiltroVO1
     */
    public DocumentosTrazabilidadOperacionFiltroVOImpl getDocumentosTrazabilidadOperacionFiltroVO() {
        return (DocumentosTrazabilidadOperacionFiltroVOImpl) findViewObject("DocumentosTrazabilidadOperacionFiltroVO");
    }

    /**
     * Container's getter for EvidenciasSolicitudVO1.
     * @return EvidenciasSolicitudVO1
     */
    public EvidenciasSolicitudVOImpl getEvidenciasSolicitudVO() {
        return (EvidenciasSolicitudVOImpl) findViewObject("EvidenciasSolicitudVO");
    }

    /**
    /**
     * Container's getter for VcaCuentaClienteVO1.
     * @return VcaCuentaClienteVO1
     */
    public VcaCuentaClienteVOImpl getVcaCuentaClienteVO() {
        return (VcaCuentaClienteVOImpl) findViewObject("VcaCuentaClienteVO");
    }
    /**
     * Container's getter for TreReglasDesembolsoVO1.
     * @return TreReglasDesembolsoVO1
     */
    public TreReglasDesembolsoVOImpl getTreReglasDesembolsoVO() {
        return (TreReglasDesembolsoVOImpl) findViewObject("TreReglasDesembolsoVO");
    }
    /**
     * Container's getter for CargoDesemnolsoVO1.
     * @return CargoDesemnolsoVO1
     */
    public CargoDesemnolsoVOImpl getCargoDesemnolsoVO() {
        return (CargoDesemnolsoVOImpl) findViewObject("CargoDesemnolsoVO");
    }
    /**
     * Container's getter for TbiReglaDesembolsoVO1.
     * @return TbiReglaDesembolsoVO1
     */
    public TbiReglaDesembolsoVOImpl getTbiReglaDesembolsoVO() {
        return (TbiReglaDesembolsoVOImpl) findViewObject("TbiReglaDesembolsoVO");
    }
    
    /**
     * Container's getter for DiasHabilesVO1.
     * @return DiasHabilesVO1
     */
    public DiasHabilesVOImpl getDiasHabilesVO() {
        return (DiasHabilesVOImpl) findViewObject("DiasHabilesVO");
    }
    /**
     * Container's getter for TreEvidenciaSolicitudVO1.
     * @return TreEvidenciaSolicitudVO1
     */
    public TreEvidenciaSolicitudVOImpl getTreEvidenciaSolicitudVO() {
        return (TreEvidenciaSolicitudVOImpl) findViewObject("TreEvidenciaSolicitudVO");
    }


    /**
     * Container's getter for TcaBaseCalculoVO1.
     * @return TcaBaseCalculoVO1
     */
    public TcaBaseCalculoVOImpl getTcaBaseCalculoVO() {
        return (TcaBaseCalculoVOImpl) findViewObject("TcaBaseCalculoVO");
    }

    /**
     * Container's getter for DocumentoCalendarioVO1.
     * @return DocumentoCalendarioVO1
     */
    public DocumentoCalendarioVOImpl getDocumentoCalendarioVO() {
        return (DocumentoCalendarioVOImpl) findViewObject("DocumentoCalendarioVO");
    }

    /**
     * Container's getter for AdjuntoDocumentoVO1.
     * @return AdjuntoDocumentoVO1
     */
    public AdjuntoDocumentoVOImpl getAdjuntoDocumentoVO() {
        return (AdjuntoDocumentoVOImpl) findViewObject("AdjuntoDocumentoVO");
    }

    /**
     * Container's getter for SumaCargosDesembolsoVO1.
     * @return SumaCargosDesembolsoVO1
     */
    public SumaCargosDesembolsoVOImpl getSumaCargosDesembolsoVO() {
        return (SumaCargosDesembolsoVOImpl) findViewObject("SumaCargosDesembolsoVO");
    }

    /**
     * Container's getter for SumaComisionesyOtrosCargosDesembolso1.
     * @return SumaComisionesyOtrosCargosDesembolso1
     */
    public SumaComisionesyOtrosCargosDesembolsoImpl getSumaComisionesyOtrosCargosDesembolso() {
        return (SumaComisionesyOtrosCargosDesembolsoImpl) findViewObject("SumaComisionesyOtrosCargosDesembolso");
         }

    /**
     * Container's getter for TransferenciasConsolidarVO1.
     * @return TransferenciasConsolidarVO1
     */
    public TransferenciasConsolidarVOImpl getTransferenciasConsolidarVO() {
        return (TransferenciasConsolidarVOImpl) findViewObject("TransferenciasConsolidarVO");
    }
     /**
     * Container's getter for CatBancosByMonedaVO1.
     * @return CatBancosByMonedaVO1
     */
    public CatBancosByMonedaVOImpl getCatBancosByMonedaVO() {
        return (CatBancosByMonedaVOImpl) findViewObject("CatBancosByMonedaVO");
    }

    /**
     * Container's getter for CatCuentasNostroByBanco1.
     * @return CatCuentasNostroByBanco1
     */
    public CatCuentasNostroByBancoImpl getCatCuentasNostroByBanco() {
        return (CatCuentasNostroByBancoImpl) findViewObject("CatCuentasNostroByBanco");
    }

    /**
     * Container's getter for TransferenciasConsolidadasVO1.
     * @return TransferenciasConsolidadasVO1
     */
    public TransferenciasConsolidadasVOImpl getTransferenciasConsolidadasVO() {
        return (TransferenciasConsolidadasVOImpl) findViewObject("TransferenciasConsolidadasVO");
    }

    /**
     * Container's getter for VcaCuentaContablePasivaVO1.
     * @return VcaCuentaContablePasivaVO1
     */
    public VcaCuentaContablePasivaVOImpl getVcaCuentaContablePasivaVO() {
        return (VcaCuentaContablePasivaVOImpl) findViewObject("VcaCuentaContablePasivaVO");
    }


    /**
     * Container's getter for ContradoDesembolsoInfoVO1.
     * @return ContradoDesembolsoInfoVO1
     */
    public ContradoDesembolsoInfoVOImpl getContradoDesembolsoInfoVO() {
        return (ContradoDesembolsoInfoVOImpl) findViewObject("ContradoDesembolsoInfoVO");
    }

    /**
     * Container's getter for TransferenciaRecursosVO1.
     * @return TransferenciaRecursosVO1
     */
    public TransferenciaRecursosVOImpl getTransferenciaRecursosVO1() {
        return (TransferenciaRecursosVOImpl) findViewObject("TransferenciaRecursosVO1");
    }

    /**
     * Container's getter for TcaFormaTransferenciaLOV1.
     * @return TcaFormaTransferenciaLOV1
     */
    public TcaFormaTransferenciaLOVImpl getTcaFormaTransferenciaLOV() {
        return (TcaFormaTransferenciaLOVImpl) findViewObject("TcaFormaTransferenciaLOV");
    }


    /**
     * Container's getter for DatosLineaCreditoVO1.
     * @return DatosLineaCreditoVO1
     */
    public DatosLineaCreditoVOImpl getDatosLineaCreditoVO() {
        return (DatosLineaCreditoVOImpl) findViewObject("DatosLineaCreditoVO");
    }


    /**
     * Container's getter for VtaProductoDesemFlexcubeVO1.
     * @return VtaProductoDesemFlexcubeVO1
     */
    public VtaProductoDesemFlexcubeVOImpl getVtaProductoDesemFlexcubeVO() {
        return (VtaProductoDesemFlexcubeVOImpl) findViewObject("VtaProductoDesemFlexcubeVO");
    }


    /**
     * Container's getter for VcaTasaDesembolsoFlexcubeVO1.
     * @return VcaTasaDesembolsoFlexcubeVO1
     */
    public VcaTasaDesembolsoFlexcubeVOImpl getVcaTasaDesembolsoFlexcubeVO() {
        return (VcaTasaDesembolsoFlexcubeVOImpl) findViewObject("VcaTasaDesembolsoFlexcubeVO");
    }

    /**
     * Container's getter for VcaTasaSpreadDesemFlexVO1.
     * @return VcaTasaSpreadDesemFlexVO1
     */
    public ViewObjectImpl getVcaTasaSpreadDesemFlexVO() {
        return (ViewObjectImpl) findViewObject("VcaTasaSpreadDesemFlexVO");
    }
    
    /**
     * Container's getter for ConsultarContratoTreSolicitudLineaCreditoVO1.
     * @return ConsultarContratoTreSolicitudLineaCreditoVO1
     */
    public ConsultarContratoTreSolicitudLineaCreditoVOImpl getConsultarContratoTreSolicitudLineaCreditoVO() {
        return (ConsultarContratoTreSolicitudLineaCreditoVOImpl) findViewObject("ConsultarContratoTreSolicitudLineaCreditoVO");
    }

    /**
     * Container's getter for DescripcionExcepcionVO1.
     * @return DescripcionExcepcionVO1
     */
    public DescripcionExcepcionVOImpl getDescripcionExcepcionVO1() {
        return (DescripcionExcepcionVOImpl) findViewObject("DescripcionExcepcionVO1");
    }

    /**
     * Container's getter for TreLineaCreditoAprobacionVO1.
     * @return TreLineaCreditoAprobacionVO1
     */
    public TreLineaCreditoAprobacionVOImpl getTreLineaCreditoAprobacionVO() {
        return (TreLineaCreditoAprobacionVOImpl) findViewObject("TreLineaCreditoAprobacionVO");
    }

    /**
     * Container's getter for AprobacionVO1.
     * @return AprobacionVO1
     */
    public AprobacionVOImpl getAprobacionVO() {
        return (AprobacionVOImpl) findViewObject("AprobacionVO");
    }

    /**
     * Container's getter for LineaCreditoDesembolsoVO1.
     * @return LineaCreditoDesembolsoVO1
     */
    public LineaCreditoDesembolsoVOImpl getLineaCreditoDesembolsoVO() {
        return (LineaCreditoDesembolsoVOImpl) findViewObject("LineaCreditoDesembolsoVO");
    }


    /**
     * Container's getter for VerDetallesLineaCreditoVO1.
     * @return VerDetallesLineaCreditoVO1
     */
    public VerDetallesLineaCreditoVOImpl getVerDetallesLineaCreditoVO() {
        return (VerDetallesLineaCreditoVOImpl) findViewObject("VerDetallesLineaCreditoVO");
    }

    /**
     * Container's getter for CalendarioComplejoVO.
     * @return CalendarioComplejoVO
     */
    public CalendarioComplejoVOImpl getCalendarioComplejoVO() {
        return (CalendarioComplejoVOImpl) findViewObject("CalendarioComplejoVO");
    }

    /**
     * Container's getter for DetalleCalendarioVO.
     * @return DetalleCalendarioVO
     */
    public DetalleCalendarioVOImpl getDetalleCalendarioVO() {
        return (DetalleCalendarioVOImpl) findViewObject("DetalleCalendarioVO");
    }

    /**
     * Container's getter for AdquisicionesVO1.
     * @return AdquisicionesVO1
     */
    public AdquisicionesVOImpl getAdquisicionesTablaVO() {
        return (AdquisicionesVOImpl) findViewObject("AdquisicionesTablaVO");
        }


    /**
     * Container's getter for CondicionesFinancierasVO1.
     * @return CondicionesFinancierasVO1
     */
    public CondicionesFinancierasVOImpl getCondicionesFinancierasVO() {
        return (CondicionesFinancierasVOImpl) findViewObject("CondicionesFinancierasVO");
    }

    /**
     * Container's getter for DetallesCalendarioVO.
     * @return DetallesCalendarioVO
     */
    public DetallesCalendarioVOImpl getDetallesCalendarioVO() {
        return (DetallesCalendarioVOImpl) findViewObject("DetallesCalendarioVO");
    }

    /**
     * Container's getter for CondicionesFinancierasVO1.
     * @return CondicionesFinancierasVO1
     */
    public CondicionesFinancierasVOImpl getCondicionesFinancieras() {
        return (CondicionesFinancierasVOImpl) findViewObject("CondicionesFinancierasVO");
    }

    /**
     * Container's getter for ConfiguracionVO1.
     * @return ConfiguracionVO1
     */
    public ConfiguracionVOImpl getConfiguracionVO() {
        return (ConfiguracionVOImpl) findViewObject("ConfiguracionVO");
    }

    /**
     * Container's getter for PropagarContratoDesembolsoVO1.
     * @return PropagarContratoDesembolsoVO1
     */
    public PropagarContratoDesembolsoVOImpl getPropagarContratoDesembolsoVO() {
        return (PropagarContratoDesembolsoVOImpl) findViewObject("PropagarContratoDesembolsoVO");
    }

    /**
     * Container's getter for MontoTotalSolicitudVO1.
     * @return MontoTotalSolicitudVO1
     */
    public MontoTotalSolicitudVOImpl getMontoTotalSolicitudVO() {
        return (MontoTotalSolicitudVOImpl) findViewObject("MontoTotalSolicitudVO");
    }

    /**
     * Container's getter for ContratoDesembolsoTreeVO2.
     * @return ContratoDesembolsoTreeVO2
     */
    public ContratoDesembolsoTreeVOImpl getContratoDesembolsoTreeVO2() {
        return (ContratoDesembolsoTreeVOImpl) findViewObject("ContratoDesembolsoTreeVO2");
    }

    /**
     * Container's getter for CalcularMontosSolicitud1.
     * @return CalcularMontosSolicitud1
     */
    public CalcularMontosSolicitudImpl getCalcularMontosSolicitud() {
        return (CalcularMontosSolicitudImpl) findViewObject("CalcularMontosSolicitud");
    }

    /**
     * Container's getter for LineaCreditoToContratoDesembolsoVL1.
     * @return LineaCreditoToContratoDesembolsoVL1
     */
    public ViewLinkImpl getLineaCreditoToContratoDesembolsoVL1() {
        return (ViewLinkImpl) findViewLink("LineaCreditoToContratoDesembolsoVL1");
    }

    /**
     * Container's getter for DesembolsoServiceVO1.
     * @return DesembolsoServiceVO1
     */
    public DesembolsoServiceVOImpl getDesembolsoServiceVO() {
        return (DesembolsoServiceVOImpl) findViewObject("DesembolsoServiceVO");
    }

    /**
     * Container's getter for ValidarOperacionFormalizadaVO1.
     * @return ValidarOperacionFormalizadaVO1
     */
    public ValidarOperacionFormalizadaVOImpl getValidarOperacionFormalizadaVO() {
        return (ValidarOperacionFormalizadaVOImpl) findViewObject("ValidarOperacionFormalizadaVO");
    }

    /**
     * Container's getter for ConsultarSpreadReferenciaVO1.
     * @return ConsultarSpreadReferenciaVO1
     */
    public ConsultarSpreadReferenciaVOImpl getConsultarSpreadReferenciaVO() {
        return (ConsultarSpreadReferenciaVOImpl) findViewObject("ConsultarSpreadReferenciaVO");
    }

    /**
     * Container's getter for ConsultarTasaReferenciaVO1.
     * @return ConsultarTasaReferenciaVO1
     */
    public ConsultarTasaReferenciaVOImpl getConsultarTasaReferenciaVO() {
        return (ConsultarTasaReferenciaVOImpl) findViewObject("ConsultarTasaReferenciaVO");
    }

    /**
     * Container's getter for TasaReferenciaVO1.
     * @return TasaReferenciaVO1
     */
    public TasaReferenciaVOImpl getTasaReferenciaVO() {
        return (TasaReferenciaVOImpl) findViewObject("TasaReferenciaVO");
    }

    /**
     * Container's getter for SpreadReferenciaVO1.
     * @return SpreadReferenciaVO1
     */
    public SpreadReferenciaVOImpl getSpreadReferenciaVO() {
        return (SpreadReferenciaVOImpl) findViewObject("SpreadReferenciaVO");
    }

    /**
     * Container's getter for ConsultarVtaProductoDesembolsoFlexcubeVO1.
     * @return ConsultarVtaProductoDesembolsoFlexcubeVO1
     */
    public ConsultarVtaProductoDesembolsoFlexcubeVOImpl getConsultarVtaProductoDesembolsoFlexcubeVO() {
        return (ConsultarVtaProductoDesembolsoFlexcubeVOImpl) findViewObject("ConsultarVtaProductoDesembolsoFlexcubeVO");
    }


    /**
     * Container's getter for FormularioGeneracionFT05VO1.
     * @return FormularioGeneracionFT05VO1
     */
    public FormularioGeneracionFT05VOImpl getFormularioGeneracionFT05VO() {
        return (FormularioGeneracionFT05VOImpl) findViewObject("FormularioGeneracionFT05VO");
    }

    /**
     * Container's getter for ConsultarContratoDesembolsoVO1.
     * @return ConsultarContratoDesembolsoVO1
     */
    public ConsultarContratoDesembolsoVOImpl getConsultarContratoDesembolsoVO() {
        return (ConsultarContratoDesembolsoVOImpl) findViewObject("ConsultarContratoDesembolsoVO");
    }

    /**
     * Container's getter for VtaProdFlexComponenteDesemVO1.
     * @return VtaProdFlexComponenteDesemVO1
     */
    public VtaProdFlexComponenteDesemVOImpl getVtaProdFlexComponenteDesemVO() {
        return (VtaProdFlexComponenteDesemVOImpl) findViewObject("VtaProdFlexComponenteDesemVO");
    }


    /**
     * Container's getter for CoberturasPrepagoVO1.
     * @return CoberturasPrepagoVO1
     */
    public CoberturasPrepagoVOImpl getCoberturasPrepagoVO() {
        return (CoberturasPrepagoVOImpl) findViewObject("CoberturasPrepagoVO");
    }


    /**
     * Container's getter for ConsultaOperacionPadreAsociadaVO1.
     * @return ConsultaOperacionPadreAsociadaVO1
     */
    public ConsultaOperacionPadreAsociadaVOImpl getConsultaOperacionPadreAsociadaVO() {
        return (ConsultaOperacionPadreAsociadaVOImpl) findViewObject("ConsultaOperacionPadreAsociadaVO");
    }

    /**
     * Container's getter for ConsultarOperacionesAsocidasVO1.
     * @return ConsultarOperacionesAsocidasVO1
     */
    public ConsultarOperacionesAsocidasVOImpl getConsultarOperacionesAsocidasVO() {
        return (ConsultarOperacionesAsocidasVOImpl) findViewObject("ConsultarOperacionesAsocidasVO");
    }

    /**
     * Container's getter for ContratosDesembolsoOperacionVO1.
     * @return ContratosDesembolsoOperacionVO1
     */
    public ContratosDesembolsoOperacionVOImpl getContratosDesembolsoOperacionVO() {
        return (ContratosDesembolsoOperacionVOImpl) findViewObject("ContratosDesembolsoOperacionVO");
    }

    /**
     * Container's getter for ConsultarProductoOperacion1.
     * @return ConsultarProductoOperacion1
     */
    public ConsultarProductoOperacionImpl getConsultarProductoOperacion() {
        return (ConsultarProductoOperacionImpl) findViewObject("ConsultarProductoOperacion");
    }

    /**
     * Container's getter for SaldoPreinversionDesembolsoVO1.
     * @return SaldoPreinversionDesembolsoVO1
     */
    public SaldoPreinversionDesembolsoVOImpl getSaldoPreinversionDesembolsoVO() {
        return (SaldoPreinversionDesembolsoVOImpl) findViewObject("SaldoPreinversionDesembolsoVO");
    }

    /**
     * Container's getter for ConsultarDatosOperacionVO1.
     * @return ConsultarDatosOperacionVO1
     */
    public ConsultarDatosOperacionVOImpl getConsultarDatosOperacionVO() {
        return (ConsultarDatosOperacionVOImpl) findViewObject("ConsultarDatosOperacionVO");
    }

    /**
     * Container's getter for ValoresPreinversionTablaByDesembolsoVO1.
     * @return ValoresPreinversionTablaByDesembolsoVO1
     */
    public ValoresPreinversionTablaByDesembolsoVOImpl getValoresPreinversionTablaByDesembolsoVO() {
        return (ValoresPreinversionTablaByDesembolsoVOImpl) findViewObject("ValoresPreinversionTablaByDesembolsoVO");
    }

    /**
     * Container's getter for ContratoDesembolsoVO2.
     * @return ContratoDesembolsoVO2
     */
    public ContratoDesembolsoVOImpl getContratoDesembolsoValidarFondosPresupuestariosVO() {
        return (ContratoDesembolsoVOImpl) findViewObject("ContratoDesembolsoValidarFondosPresupuestariosVO");
    }

    /**
     * Container's getter for CatFondoContableVO1.
     * @return CatFondoContableVO1
     */
    public CatFondoContableVOImpl getCatFondoLineaVO() {
        return (CatFondoContableVOImpl) findViewObject("CatFondoLineaVO");
    }

    /**
     * Container's getter for TransferenciaBancariaVO1.
     * @return TransferenciaBancariaVO1
     */
    public TransferenciaBancariaVOImpl getConsolidarTransferenciaVO() {
        return (TransferenciaBancariaVOImpl) findViewObject("ConsolidarTransferenciaVO");
    }

    /**
     * Container's getter for TransferenciasBancariasTablaVO1.
     * @return TransferenciasBancariasTablaVO1
     */
    public TransferenciasBancariasTablaVOImpl getConsolidarTransferenciasTablaVO() {
        return (TransferenciasBancariasTablaVOImpl) findViewObject("ConsolidarTransferenciasTablaVO");
    }

    /**
     * Container's getter for ConsultaFechaTransRecursosBydesembolso1.
     * @return ConsultaFechaTransRecursosBydesembolso1
     */
    public ConsultaFechaTransRecursosBydesembolsoImpl getConsultaFechaTransRecursosBydesembolso() {
        return (ConsultaFechaTransRecursosBydesembolsoImpl) findViewObject("ConsultaFechaTransRecursosBydesembolso");
    }

    /**
     * Container's getter for FechasMayorYMenorCalendarioComplejoVO1.
     * @return FechasMayorYMenorCalendarioComplejoVO1
     */
    public FechasMayorYMenorCalendarioComplejoVOImpl getFechasMayorYMenorCalendarioComplejoVO() {
        return (FechasMayorYMenorCalendarioComplejoVOImpl) findViewObject("FechasMayorYMenorCalendarioComplejoVO");
    }

    /**
     * Container's getter for LineaCreditoDesembolsoImpEnvioGastoVO1.
     * @return LineaCreditoDesembolsoImpEnvioGastoVO1
     */
    public LineaCreditoDesembolsoImpEnvioGastoVOImpl getLineaCreditoDesembolsoImpEnvioGastoVO() {
        return (LineaCreditoDesembolsoImpEnvioGastoVOImpl) findViewObject("LineaCreditoDesembolsoImpEnvioGastoVO");
    }

    /**
     * Container's getter for ContratoDesembolsoTreeEnvioGastoVO1.
     * @return ContratoDesembolsoTreeEnvioGastoVO1
     */
    public ViewObjectImpl getContratoDesembolsoTreeEnvioGastoVO() {
        return (ViewObjectImpl) findViewObject("ContratoDesembolsoTreeEnvioGastoVO");
    }

    /**
     * Container's getter for LinCredDesemImpEnvioGastoAndContratoDesemTreeVL1.
     * @return LinCredDesemImpEnvioGastoAndContratoDesemTreeVL1
     */
    public ViewLinkImpl getLinCredDesemImpEnvioGastoAndContratoDesemTreeVL1() {
        return (ViewLinkImpl) findViewLink("LinCredDesemImpEnvioGastoAndContratoDesemTreeVL1");
    }
    
    /**
     * Container's getter for ConsultarDatosContratoVO1.
     * @return ConsultarDatosContratoVO1
     */
    public ConsultarDatosContratoVOImpl getConsultarDatosContratoVO() {
        return (ConsultarDatosContratoVOImpl) findViewObject("ConsultarDatosContratoVO");
    }

    /**
     * Container's getter for SolicitudDesembolsoVO1.
     * @return SolicitudDesembolsoVO1
     */
    public SolicitudDesembolsoVOImpl getSolicitudDesembolsoEstadosVO() {
        return (SolicitudDesembolsoVOImpl) findViewObject("SolicitudDesembolsoEstadosVO");
    }

    /**
     * Container's getter for TerminoLineaVO1.
     * @return TerminoLineaVO1
     */
    public TerminoLineaVOImpl getTerminoLineaVO() {
        return (TerminoLineaVOImpl) findViewObject("TerminoLineaVO");
    }

    /**
     * Container's getter for ConsultarFuenteVO1.
     * @return ConsultarFuenteVO1
     */
    public ConsultarFuenteVOImpl getConsultarFuenteVO() {
        return (ConsultarFuenteVOImpl) findViewObject("ConsultarFuenteVO");
    }

    /**
     * Container's getter for ManejoDeErroresVO1.
     * @return ManejoDeErroresVO1
     */
    public ManejoDeErroresVOImpl getManejoDeErroresVO() {
        return (ManejoDeErroresVOImpl) findViewObject("ManejoDeErroresVO");
    }

    /**
     * Container's getter for DiasInhabilesDesembolsosVO1.
     * @return DiasInhabilesDesembolsosVO1
     */
    public DiasInhabilesDesembolsosVOImpl getDiasInhabilesDesembolsosVO() {
        return (DiasInhabilesDesembolsosVOImpl) findViewObject("DiasInhabilesDesembolsosVO");
    }

    /**
     * Container's getter for LineaCreditoDesembolsosTreeQueryVO1.
     * @return LineaCreditoDesembolsosTreeQueryVO1
     */
    public LineaCreditoDesembolsosTreeQueryVOImpl getLineaCreditoDesembolsosTreeQueryVO() {
        return (LineaCreditoDesembolsosTreeQueryVOImpl) findViewObject("LineaCreditoDesembolsosTreeQueryVO");
    }

    /**
     * Container's getter for ContratoDesembolsoVO2.
     * @return ContratoDesembolsoVO2
     */
    public ContratoDesembolsoVOImpl getContratoDesembolsoVO2() {
        return (ContratoDesembolsoVOImpl) findViewObject("ContratoDesembolsoVO2");
    }

    /**
     * Container's getter for LineaCreditoContratoDesembolsoTreeTableVL1.
     * @return LineaCreditoContratoDesembolsoTreeTableVL1
     */
    public ViewLinkImpl getLineaCreditoContratoDesembolsoTreeTableVL1() {
        return (ViewLinkImpl) findViewLink("LineaCreditoContratoDesembolsoTreeTableVL1");
    }

    /**
     * Container's getter for ConsultarLineaCreditoDesembolsoVO1.
     * @return ConsultarLineaCreditoDesembolsoVO1
     */
    public ConsultarLineaCreditoDesembolsoVOImpl getConsultarLineaCreditoDesembolsoVO() {
        return (ConsultarLineaCreditoDesembolsoVOImpl) findViewObject("ConsultarLineaCreditoDesembolsoVO");
    }

    /**
     * Container's getter for ConsultarSolicitudDesembolsoVO1.
     * @return ConsultarSolicitudDesembolsoVO1
     */
    public ConsultarSolicitudDesembolsoVOImpl getConsultarSolicitudDesembolsoVO() {
        return (ConsultarSolicitudDesembolsoVOImpl) findViewObject("ConsultarSolicitudDesembolsoVO");
    }

    /**
     * Container's getter for MonedaContratoVO1.
     * @return MonedaContratoVO1
     */
    public MonedaContratoVOImpl getMonedaContratoVO1() {
        return (MonedaContratoVOImpl) findViewObject("MonedaContratoVO");
    }

    /**
     * Container's getter for MonedaContratoVO.
     * @return MonedaContratoVO
     */
    public MonedaContratoVOImpl getMonedaContratoVO() {
        return (MonedaContratoVOImpl) findViewObject("MonedaContratoVO");
    }

    /**
     * Container's getter for ContratoDesembolsoQueryVO1.
     * @return ContratoDesembolsoQueryVO1
     */
    public ContratoDesembolsoQueryVOImpl getContratoDesembolsoQueryVO() {
        return (ContratoDesembolsoQueryVOImpl) findViewObject("ContratoDesembolsoQueryVO");
    }
    /**
     * Container's getter for ValidarFunetesExternasContratoDesembolsoVO1.
     * @return ValidarFunetesExternasContratoDesembolsoVO1
     */
    public ValidarFunetesExternasContratoDesembolsoVOImpl getValidarFunetesExternasContratoDesembolsoVO() {
        return (ValidarFunetesExternasContratoDesembolsoVOImpl) findViewObject("ValidarFunetesExternasContratoDesembolsoVO");
    }

    /**
     * Container's getter for SolicitudDesembolsoQueryVO1.
     * @return SolicitudDesembolsoQueryVO1
     */
    public SolicitudDesembolsoQueryVOImpl getSolicitudDesembolsoQueryVO() {
        return (SolicitudDesembolsoQueryVOImpl) findViewObject("SolicitudDesembolsoQueryVO");
    }
    
     /**
     * Container's getter for TransferenciasBancariasFormVO1.
     * @return TransferenciasBancariasFormVO1
     */
    public TransferenciasBancariasFormVOImpl getTransferenciasBancariasFormVO() {
        return (TransferenciasBancariasFormVOImpl) findViewObject("TransferenciasBancariasFormVO");
    }


    /**
     * Container's getter for CatInstruccionesPagoValidadasVO1.
     * @return CatInstruccionesPagoValidadasVO1
     */
    public CatInstruccionesPagoValidadasVOImpl getCatInstruccionesPagoValidadasVO() {
        return (CatInstruccionesPagoValidadasVOImpl) findViewObject("CatInstruccionesPagoValidadasVO");
    }

    /**
     * Container's getter for ConsultaTransferenciasBanByDesembolso1.
     * @return ConsultaTransferenciasBanByDesembolso1
     */
    public ConsultaTransferenciasBanByDesembolsoImpl getConsultaTransferenciasBanByDesembolso() {
        return (ConsultaTransferenciasBanByDesembolsoImpl) findViewObject("ConsultaTransferenciasBanByDesembolso");
    }

    /**
     * Container's getter for TransferenciasConInstruccionVO1.
     * @return TransferenciasConInstruccionVO1
     */
    public TransferenciasConInstruccionVOImpl getTransferenciasConInstruccionVO() {
        return (TransferenciasConInstruccionVOImpl) findViewObject("TransferenciasConInstruccionVO");
    }

    /**
     * Container's getter for TransferrenciaAConsolidarVO1.
     * @return TransferrenciaAConsolidarVO1
     */
    public TransferrenciaAConsolidarVOImpl getTransferrenciaAConsolidarVO() {
        return (TransferrenciaAConsolidarVOImpl) findViewObject("TransferrenciaAConsolidarVO");
    }

    /**
     * Container's getter for RecuperarDiaPagoClienteByDesembolsoVO1.
     * @return RecuperarDiaPagoClienteByDesembolsoVO1
     */
    public RecuperarDiaPagoClienteByDesembolsoVOImpl getRecuperarDiaPagoClienteByDesembolsoVO() {
        return (RecuperarDiaPagoClienteByDesembolsoVOImpl) findViewObject("RecuperarDiaPagoClienteByDesembolsoVO");
    }

    /**
     * Container's getter for ConsultarTransferenciasByDesembolsoVO1.
     * @return ConsultarTransferenciasByDesembolsoVO1
     */
    public ConsultarTransferenciasByDesembolsoVOImpl getConsultarTransferenciasByDesembolsoVO() {
        return (ConsultarTransferenciasByDesembolsoVOImpl) findViewObject("ConsultarTransferenciasByDesembolsoVO");
    }

    /**
     * Container's getter for ConsultarProgramaOperacionVO1.
     * @return ConsultarProgramaOperacionVO1
     */
    public ConsultarProgramaOperacionVOImpl getConsultarProgramaOperacionVO() {
        return (ConsultarProgramaOperacionVOImpl) findViewObject("ConsultarProgramaOperacionVO");
    }

    /**
     * Container's getter for TransferenciaRecursosByDesembolsoVO1.
     * @return TransferenciaRecursosByDesembolsoVO1
     */
    public TransferenciaRecursosByDesembolsoVOImpl getTransferenciaRecursosByDesembolsoVO() {
        return (TransferenciaRecursosByDesembolsoVOImpl) findViewObject("TransferenciaRecursosByDesembolsoVO");
    }

    /**
     * Container's getter for ConsultarOperacionVO1.
     * @return ConsultarOperacionVO1
     */
    public ConsultarOperacionVOImpl getConsultarOperacionVO() {
        return (ConsultarOperacionVOImpl) findViewObject("ConsultarOperacionVO");
    }

    /**
     * Container's getter for ValidarT406ByDesembolsoVO1.
     * @return ValidarT406ByDesembolsoVO1
     */
    public ValidarT406ByDesembolsoVOImpl getValidarT406ByDesembolsoVO() {
        return (ValidarT406ByDesembolsoVOImpl) findViewObject("ValidarT406ByDesembolsoVO");
    }

    /**
     * Container's getter for ValidarT406ByOperacionVO1.
     * @return ValidarT406ByOperacionVO1
     */
    public ValidarT406ByOperacionVOImpl getValidarT406ByOperacionVO() {
        return (ValidarT406ByOperacionVOImpl) findViewObject("ValidarT406ByOperacionVO");
    }
	
	/**
     * Metodo de convertir monedas sin logica especial, es el metodo convencional con logica generica para realizar
     * la conversion normal de monedas sin obtener de sesiï¿½n o del contexto algun valor.
     * @param codigoDe
     * @param codigoA
     * @param monto
     * @return
     */
    public BigDecimal convertirMonedasNew(String codigoDe, String codigoA, BigDecimal monto) {
        logger.warning("Inicia convertirMonedas");
        logger.warning("codigoDe " + codigoDe);
        logger.warning("codigoA " + codigoA);
        logger.warning("monto " + monto);
        BigDecimal resultado = null;

        if (codigoDe.toUpperCase().equalsIgnoreCase(codigoA.toUpperCase())) {
           resultado = monto;
        } else {
           try {
               // String codigoDe=null;
               String wsdl = getWsdl(IWsdlLocation.IMPORTES);
               //ConversorMonedasPT12BndQSService conversorMonedasPT12BndQSService = new ConversorMonedasPT12BndQSService();
               ConversorMonedasPT12BndQSService conversorMonedasPT12BndQSService =
                            IWsdlLocation.Service.getInstance(ConversorMonedasPT12BndQSService.class, wsdl);
               ConversorMonedasPT conversorMonedasPT =
                            conversorMonedasPT12BndQSService.getConversorMonedasPT12BndQSPort();
               // Add your code to call the desired methods.
               ConvertirMonedasImportesRequestType request = new ConvertirMonedasImportesRequestType();
               Catalogo monedaA = new Catalogo();
               Catalogo monedaDe = new Catalogo();

               monedaDe.setCodigoExterno(codigoDe);
               monedaA.setCodigoExterno(codigoA);

               ConvierteMonedas convertirMonedas = new ConvierteMonedas();
               convertirMonedas.setMonedaA(monedaA);
               convertirMonedas.setMonedaDe(monedaDe);
               Double montoTotal = monto.doubleValue();
               convertirMonedas.setMonto(montoTotal);
               request.getConvierteMonedas().add(convertirMonedas);

               Date horaInicio = ModelUtils.logStartWS(logger, request, "CONVERTIR MONEDAS");

               ConvertirMonedasImportesResponseType response =
                            conversorMonedasPT.convertirMonedasImportes(request);
               ModelUtils.logEndWS(logger, response, "CONVERTIR MONEDAS", horaInicio);

               ConvierteMonedasImporte resultadoObtenido = response.getConvierteMonedasImporte().get(0);
               Double convertir = resultadoObtenido.getMontoConvertido();
               BigDecimal obtenido = new BigDecimal(convertir);
               int decimales = 2;
               resultado = obtenido.setScale(decimales, RoundingMode.CEILING);
               logger.warning("Total " + obtenido.setScale(decimales, RoundingMode.CEILING));
           } catch (Exception ex) {
                logger.warning("Error al consultar el convertidor de monedas");
           }
        }

        if (null != resultado) {
            logger.log(ADFLogger.WARNING, "Finaliza convertirMonedas. Valor de retorno :" + resultado);
        } else {
            logger.warning("Error al obtener la moneda viene nula");
        }
        return resultado;
    }
    
    public String getWsdl(String key) {
        ConfiguracionVOImpl vo = this.getConfiguracionVO();
        Row row = vo.getValor(key);
        String wsdl = row == null ? null : (String) row.getAttribute("Valor");
        return wsdl;
    }
	

    /**
     * Container's getter for DatosGeneralesIntermediacionVO1.
     * @return DatosGeneralesIntermediacionVO1
     */
    public DatosGeneralesIntermediacionVOImpl getDatosGeneralesIntermediacionVO() {
        return (DatosGeneralesIntermediacionVOImpl) findViewObject("DatosGeneralesIntermediacionVO");
    }

    /**
     * Container's getter for DatosTransferenciaSinRecursos1.
     * @return DatosTransferenciaSinRecursos1
     */
    public DatosTransferenciaSinRecursosVOImpl getDatosTransferenciaSinRecursosVO() {
        return (DatosTransferenciaSinRecursosVOImpl) findViewObject("DatosTransferenciaSinRecursosVO");
    }
}
