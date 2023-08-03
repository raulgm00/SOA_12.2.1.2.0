package org.bcie.fenix.common.model.am;

import com.bcie.xmlns.lineacreditoservice.LineaCreditoPT;

import com.bcie.xmlns.operacionservice.Operacion12BndQSService;
import com.bcie.xmlns.operacionservice.Operacion12Port;

import java.math.BigDecimal;

import java.math.BigInteger;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import java.util.ResourceBundle;

import java.util.Set;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import oracle.adf.share.logging.ADFLogger;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.server.ApplicationModuleImpl;

import oracle.jbo.server.ViewLinkImpl;

import oracle.jbo.server.ViewObjectImpl;

import org.bcie.comisionbo.MontoBase;
import org.bcie.contratobo.Contrato;
import org.bcie.contratobo.Cuentas;
import org.bcie.fenix.common.model.FenixModelConstants;
import static org.bcie.fenix.common.model.FenixModelConstants.ES_FINANCIERA_ESPECIAL_TRUE;
import static org.bcie.fenix.common.model.FenixModelConstants.ES_RECURSOS_ORDINARIOS_FALSE;
import static org.bcie.fenix.common.model.FenixModelConstants.ES_REVOLVENTE_TRUE_INT;
import static org.bcie.fenix.common.model.FenixModelConstants.TERMINO_TIPO_FECHA_INICIO_APROBACION;
import static org.bcie.fenix.common.model.FenixModelConstants.TERMINO_TIPO_FECHA_INICIO_ESCRITURACION;
import static org.bcie.fenix.common.model.FenixModelConstants.TERMINO_TIPO_FECHA_INICIO_NOTIFICACION;
import static org.bcie.fenix.common.model.FenixModelConstants.TERMINO_TIPO_FECHA_INICIO_PRIMER_DESEMBOLSO;
import static org.bcie.fenix.common.model.FenixModelConstants.TERMINO_TIPO_FECHA_INICIO_VIGENCIA;
import static org.bcie.fenix.common.model.FenixModelConstants.TIPO_PLAZO_ANIO;
import static org.bcie.fenix.common.model.FenixModelConstants.TIPO_PLAZO_DIA;
import static org.bcie.fenix.common.model.FenixModelConstants.TIPO_PLAZO_MES;
import org.bcie.fenix.common.model.am.common.FenixImplementacionPctAM;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.utils.ModelUtils;
import org.bcie.fenix.common.model.vo.ActualizarOperacionVOImpl;
import org.bcie.fenix.common.model.vo.AgregarComisionVOImpl;
import org.bcie.fenix.common.model.vo.AgregarLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.AgregarVencimientoPlazoVOImpl;
import org.bcie.fenix.common.model.vo.CalcularInteresContratoByIdPrepagoSpVOImpl;
import org.bcie.fenix.common.model.vo.CargoPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.CatCuentaClienteLOVImpl;
import org.bcie.fenix.common.model.vo.CatCuentaClienteLOVRowImpl;
import org.bcie.fenix.common.model.vo.CatDiasFeriadosVOImpl;
import org.bcie.fenix.common.model.vo.CatFondoContableVOImpl;
import org.bcie.fenix.common.model.vo.CatPeriodoGraciaLOVImpl;
import org.bcie.fenix.common.model.vo.CatPlazoFinanciamientoLOVImpl;
import org.bcie.fenix.common.model.vo.CatProductoFlexcubeLOVImpl;
import org.bcie.fenix.common.model.vo.ComisionVOImpl;
import org.bcie.fenix.common.model.vo.ComisionVORowImpl;
import org.bcie.fenix.common.model.vo.ConcursanteImplementacionVOImpl;
import org.bcie.fenix.common.model.vo.ConcursanteImplementacionVORowImpl;
import org.bcie.fenix.common.model.vo.CondicionesFinancierasVOImpl;
import org.bcie.fenix.common.model.vo.ConfiguracionVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarLineasPorOperacionVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarTasaReferenciaVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarTerminoContratacionClienteVOImpl;
import org.bcie.fenix.common.model.vo.ContratoDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.ContratoVOImpl;
import org.bcie.fenix.common.model.vo.ContratoVORowImpl;
import org.bcie.fenix.common.model.vo.ContratosDesembolsoConInteresVOImpl;
import org.bcie.fenix.common.model.vo.ContratosPorLiquidarByOperacionImpl;
import org.bcie.fenix.common.model.vo.DatosLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.DatosLineaCreditoVORowImpl;
import org.bcie.fenix.common.model.vo.EncabezadoRegistrarDatosLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.EncabezadoRegistrarDatosLineaCreditoVORowImpl;
import org.bcie.fenix.common.model.vo.FinalizacionEstudiosVOImpl;
import org.bcie.fenix.common.model.vo.FormularioImplementacionPctVOImpl;
import org.bcie.fenix.common.model.vo.ImplementacionAdjudicatarioVOImpl;
import org.bcie.fenix.common.model.vo.ImplementacionParticipanteVOImpl;
import org.bcie.fenix.common.model.vo.FormularioImplementacionPctVORowImpl;
import org.bcie.fenix.common.model.vo.FormularioLoteImplementacionVOImpl;
import org.bcie.fenix.common.model.vo.FormularioLoteImplementacionVORowImpl;
import org.bcie.fenix.common.model.vo.FormularioObservacionCargoPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.FormularioObservacionCargoPrepagoVORowImpl;
import org.bcie.fenix.common.model.vo.ImplementacionAdjudicatarioVORowImpl;
import org.bcie.fenix.common.model.vo.ImplementacionParticipanteVORowImpl;
import org.bcie.fenix.common.model.vo.ImplementacionVOImpl;
import org.bcie.fenix.common.model.vo.ImplementacionVORowImpl;
import org.bcie.fenix.common.model.vo.LineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.LineasContratosCreadosImplementacionVOImpl;
import org.bcie.fenix.common.model.vo.LoteImplementacionVOImpl;
import org.bcie.fenix.common.model.vo.LoteImplementacionVORowImpl;
import org.bcie.fenix.common.model.vo.MontoAmpliacionLineaCredtoVOImpl;
import org.bcie.fenix.common.model.vo.MontoAmpliacionVOImpl;
import org.bcie.fenix.common.model.vo.MontoOperacionVOImpl;
import org.bcie.fenix.common.model.vo.MontoTotalEnvioGastoPorContratosDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.ObservacionPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.ObtenerDatosPrepagoByContratoFlexcubeImpl;
import org.bcie.fenix.common.model.vo.ObtenerObservacionCargoPrepagoVORowImpl;
import org.bcie.fenix.common.model.vo.RegistrarDatosLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.RegistrarDatosLineaCreditoVORowImpl;
import org.bcie.fenix.common.model.vo.SolicitudDesembolsoVOImpl;
import org.bcie.fenix.common.model.vo.TasaReferenciaVOImpl;
import org.bcie.fenix.common.model.vo.TcaPaisesVOImpl;
import org.bcie.fenix.common.model.vo.TcaTipoMonedaVOImpl;
import org.bcie.fenix.common.model.vo.TccEditarTerminoVOImpl;
import org.bcie.fenix.common.model.vo.TccEditarTerminoVORowImpl;
import org.bcie.fenix.common.model.vo.TccTerminoVOImpl;
import org.bcie.fenix.common.model.vo.TccTerminoVORowImpl;
import org.bcie.fenix.common.model.vo.TiposComisionLOVImpl;
import org.bcie.fenix.common.model.vo.VcaTasaDesembolsoFlexcubeVOImpl;
import org.bcie.fenix.common.model.vo.VencimientoPlazoVOImpl;
import org.bcie.fenix.common.model.vo.VencimientoPlazoVORowImpl;
import org.bcie.fenix.common.model.vo.VtaProdFlexComponenteDesemVOImpl;
import org.bcie.lineacreditobo.Flexcube;
import org.bcie.lineacreditobo.LineaCredito;
import org.bcie.lineacreditomo.ActualizarContratoLineaCreditoRequestType;
import org.bcie.lineacreditomo.ActualizarContratoLineaCreditoResponseType;
import org.bcie.lineacreditobo.ObjectFactory;
import org.bcie.lineacreditomo.PropagarLineaCreditoRequestType;
import org.bcie.lineacreditomo.PropagarLineaCreditoResponsetType;
import org.bcie.operacionmo.AplicarEnvioCobroRequestType;
import org.bcie.operacionmo.AplicarEnvioCobroResponseType;
import org.bcie.operacionmo.ObtenerFactibilidadRequestType;
import org.bcie.operacionmo.ObtenerFactibilidadResponseType;
import org.bcie.terminobo.Termino;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Nov 23 12:35:59 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FenixImplementacionPctAMImpl extends ApplicationModuleImpl implements FenixImplementacionPctAM {
    /**
     * This is the default constructor (do not remove).
     */
    
    private static ADFLogger logger = null;
    
    public FenixImplementacionPctAMImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public Long insertarFormularioImplementacion(Long idOperacion){
        logger.log(ADFLogger.WARNING,"Into insertarFormularioImplementacion");
        
        Long idImplementacion = null;
        String tipoProceso = null;
        String nombreAdquisicion = null;
        BigDecimal montoPresupuestado = null;
        
        FormularioImplementacionPctVOImpl formularioImplementacionVOImpl = this.getFormularioImplementacionPctVO();
        ImplementacionVOImpl implementacionVOImpl = this.getImplementacionVO();
        
        Row rowFormularioImplementacion = formularioImplementacionVOImpl.getCurrentRow();
        
        if(rowFormularioImplementacion != null){
            
            //FORMULARIO IMPLEMENTACION
            
            if(rowFormularioImplementacion.getAttribute(
                                                    FormularioImplementacionPctVORowImpl.TIPOPROCESO) != null){
                tipoProceso = rowFormularioImplementacion.getAttribute(FormularioImplementacionPctVORowImpl.TIPOPROCESO).toString();
                                                    }
            if(rowFormularioImplementacion.getAttribute(
                                                    FormularioImplementacionPctVORowImpl.NOMBREADQUISICION) != null){
                nombreAdquisicion = rowFormularioImplementacion.getAttribute(FormularioImplementacionPctVORowImpl.NOMBREADQUISICION).toString();
                                                    }
            if(rowFormularioImplementacion.getAttribute(
                                                    FormularioImplementacionPctVORowImpl.MONTOPRESUPUESTADO) != null){ 
                montoPresupuestado = (BigDecimal) rowFormularioImplementacion.getAttribute(FormularioImplementacionPctVORowImpl.MONTOPRESUPUESTADO);
                                                    }
            
            logger.log(ADFLogger.WARNING, "Valor tipoProceso." + tipoProceso);
            logger.log(ADFLogger.WARNING, "Valor nombreAdquisicion." + nombreAdquisicion);
            logger.log(ADFLogger.WARNING, "Valor montoPresupuestado." + montoPresupuestado);
                
            idImplementacion = implementacionVOImpl.agregarImplementacion(idOperacion, tipoProceso, nombreAdquisicion, montoPresupuestado);
            logger.log(ADFLogger.WARNING,"El valor del ID Implementacion es : " + idImplementacion);
            
            }
            else{
                if(null == rowFormularioImplementacion){
                    logger.log(ADFLogger.ERROR,"formularioImplementacionVOImpl.getCurrentRow() is null.");
                }
                logger.log(ADFLogger.ERROR,"No se inserto el row en formularioImplementacionVOImpl.");
            }
        
        logger.log(ADFLogger.WARNING, "El metodo para insertar Implementacion finalizo.");
        return idImplementacion;
    }
    
    public Long llenarFormularioImplementacion(){
        logger.log(ADFLogger.WARNING,"INTO llenarFormularioImplementacion");
        
        Long idImplementacion = null;
        String tipoProceso = null;
        String nombreAdquisicion = null;
        Timestamp fechaPublicacion = null;
        Timestamp fechaLimiteRecepcionPropuesta = null;
        BigDecimal montoPresupuestado = null;
        String observacion = null;
        Integer idTcaTipoMoneda = null;
        Integer requiereLotes = null;
        
        FormularioImplementacionPctVOImpl formularioImplementacionVOImpl = this.getFormularioImplementacionPctVO();
        
        ImplementacionVORowImpl implementacionVORowImpl = (ImplementacionVORowImpl)
            this.getImplementacionVO().getRowAtRangeIndex(0);
        
        if(implementacionVORowImpl != null){
            
            //FORMULARIO IMPLEMENTACION
            
            if(null != implementacionVORowImpl.getId()){
                idImplementacion = implementacionVORowImpl.getId(); 
            }
            if(null != implementacionVORowImpl.getTipoProceso()){
                tipoProceso = implementacionVORowImpl.getTipoProceso(); 
            }
            if(null != implementacionVORowImpl.getNombreAdquisicion()){
                nombreAdquisicion = implementacionVORowImpl.getNombreAdquisicion();
                                                    }
            if(null != implementacionVORowImpl.getFechaPublicacion()){
                fechaPublicacion = implementacionVORowImpl.getFechaPublicacion();
                                                    }
            if(null != implementacionVORowImpl.getFechaLimiteRecepcion()){
                fechaLimiteRecepcionPropuesta = implementacionVORowImpl.getFechaLimiteRecepcion();
                                                    }
            if(null != implementacionVORowImpl.getMontoPresupuestado()){
                montoPresupuestado = implementacionVORowImpl.getMontoPresupuestado();
                                                   }
            if(null != implementacionVORowImpl.getObservacion()){
                observacion = implementacionVORowImpl.getObservacion();
                                                   }
            if(null != implementacionVORowImpl.getIdTcaTipoMoneda()){
                idTcaTipoMoneda = implementacionVORowImpl.getIdTcaTipoMoneda();
                                                   }
            if(null != implementacionVORowImpl.getRequiereLotes()){
                requiereLotes = implementacionVORowImpl.getRequiereLotes();
                                                   }
            
            if(tipoProceso.compareTo("ABREVIADO") == 0){
                tipoProceso = "Abreviado";
            }
            else if(tipoProceso.compareTo("CONCURSO") == 0){
                tipoProceso = "Concurso";
            }
            else{
                logger.log(ADFLogger.ERROR, "Error en cambiar valores TipoProceso FenixImplementacion.");
            }
            
            formularioImplementacionVOImpl.crearRowFormImpPctInsertarActualizar(idImplementacion, tipoProceso, 
                                                    nombreAdquisicion, montoPresupuestado, observacion, fechaPublicacion, 
                                                    fechaLimiteRecepcionPropuesta, idTcaTipoMoneda, requiereLotes);
        }else{
            if(null == implementacionVORowImpl){
                logger.log(ADFLogger.ERROR,"implementacionVORowImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"implementacionVORowImpl es nulo.");
        }
        logger.log(ADFLogger.WARNING, "El metodo para llenar el Formulario de Implementacion finalizo.");
        return idImplementacion;
    }
    
    public Boolean obtenerFechaLimiteRecepcionPropuesta(String idImplementacion){
        logger.warning("Entra en obtenerFechaLimiteRecepcionPropuesta");
        ImplementacionVOImpl implementacionVOImpl = this.getImplementacionVO();
        ImplementacionVORowImpl implementacionVORowImpl = null;
        FormularioImplementacionPctVOImpl formularioImplementacionVOImpl = this.getFormularioImplementacionPctVO();

        String tipoProceso = null;
        String nombreAdquisicion = null;
        Timestamp fechaPublicacion = null;
        Timestamp fechaLimiteRecepcionPropuesta = null;
        BigDecimal montoPresupuestado = null;
        String observacion = null;
        Integer idTcaTipoMoneda = null;
        Integer requiereLotes = null;
        Boolean esFecha = Boolean.FALSE;
        Long implementacionId = null; 
        Row row = null;
        try {
            implementacionId = new Long(idImplementacion);
            esFecha = implementacionVOImpl.obtenerImplementacionByIdImplementacion(implementacionId);
            implementacionVORowImpl = (ImplementacionVORowImpl) this.getImplementacionVO().getRowAtRangeIndex(0);
            if(esFecha){
                if(implementacionVORowImpl != null){
                    
                    //FORMULARIO IMPLEMENTACION

                    if(null != implementacionVORowImpl.getTipoProceso()){
                        tipoProceso = implementacionVORowImpl.getTipoProceso(); 
                    }
                    if(null != implementacionVORowImpl.getNombreAdquisicion()){
                        nombreAdquisicion = implementacionVORowImpl.getNombreAdquisicion();
                                                            }
                    if(null != implementacionVORowImpl.getFechaPublicacion()){
                        fechaPublicacion = implementacionVORowImpl.getFechaPublicacion();
                                                            }
                    if (null != implementacionVORowImpl.getFechaLimiteRecepcion()) {
                        fechaLimiteRecepcionPropuesta = implementacionVORowImpl.getFechaLimiteRecepcion();
                        logger.warning("Valor de la fecha : " + fechaLimiteRecepcionPropuesta);
                    }else{
                        logger.warning("No se obtiene el row de implementacion");
                    }
                    if(null != implementacionVORowImpl.getMontoPresupuestado()){
                        montoPresupuestado = implementacionVORowImpl.getMontoPresupuestado();
                                                           }
                    if(null != implementacionVORowImpl.getObservacion()){
                        observacion = implementacionVORowImpl.getObservacion();
                                                           }
                    if(null != implementacionVORowImpl.getIdTcaTipoMoneda()){
                        idTcaTipoMoneda = implementacionVORowImpl.getIdTcaTipoMoneda();
                                                           }
                    if(null != implementacionVORowImpl.getRequiereLotes()){
                        requiereLotes = implementacionVORowImpl.getRequiereLotes();
                                                           }
                    
                    if(tipoProceso.compareTo("ABREVIADO") == 0){
                        tipoProceso = "Abreviado";
                    }
                    else if(tipoProceso.compareTo("CONCURSO") == 0){
                        tipoProceso = "Concurso";
                    }
                    else{
                        logger.log(ADFLogger.ERROR, "Error en cambiar valores TipoProceso FenixImplementacion.");
                    }
                    
                    formularioImplementacionVOImpl.crearRowFormImpPctInsertarActualizar(implementacionId, tipoProceso, 
                                                            nombreAdquisicion, montoPresupuestado, observacion, fechaPublicacion, 
                                                            fechaLimiteRecepcionPropuesta, idTcaTipoMoneda, requiereLotes);
                }else{
                    if(null == implementacionVORowImpl){
                        logger.log(ADFLogger.ERROR,"implementacionVORowImpl is null.");
                        }
                    logger.log(ADFLogger.ERROR,"implementacionVORowImpl es nulo.");
                }
            }else{
                logger.warning("El valor de fechaLimiteRecepcionPropuesta es nula.");
            }
        } catch (Exception e) {
            logger.warning("Error en llenarDatosFormulario");
        }
        return esFecha;
    }
    
    public void actualizarFormularioImplementacionObservacion(){
        logger.log(ADFLogger.WARNING,"INTO actualizarFormularioImplementacionObservacion");
        
        FormularioImplementacionPctVORowImpl formularioImplementacionRowVOImpl = 
            (FormularioImplementacionPctVORowImpl)this.getFormularioImplementacionPctVO().getCurrentRow();
        
        ImplementacionVOImpl implementacionVOImpl = this.getImplementacionVO();
        
        Long idImplementacion = null;
        String tipoProceso = null;
        String nombreAdquisicion = null;
        BigDecimal montoPresupuestado = null;
        String observacion = null;
        
        if(formularioImplementacionRowVOImpl != null){
            
            //FORMULARIO OBSERVACION CARGO PREPAGO
            
            if(null != formularioImplementacionRowVOImpl.getId() ){
                idImplementacion = formularioImplementacionRowVOImpl.getId();
            }
            if(null != formularioImplementacionRowVOImpl.getTipoProceso() ){
                tipoProceso = formularioImplementacionRowVOImpl.getTipoProceso();
            }
            if(null != formularioImplementacionRowVOImpl.getNombreAdquisicion()){
                nombreAdquisicion = formularioImplementacionRowVOImpl.getNombreAdquisicion();
            }
            if(null != formularioImplementacionRowVOImpl.getMontoPresupuestado()){
                montoPresupuestado = formularioImplementacionRowVOImpl.getMontoPresupuestado();
            }
            if(null != formularioImplementacionRowVOImpl.getObservacionGestion() ){
                observacion = formularioImplementacionRowVOImpl.getObservacionGestion();
            }
            
            implementacionVOImpl.actualizarObservacion(idImplementacion, observacion);                   
            logger.log(ADFLogger.WARNING,"El valor del ID observacion es : " + observacion);
            
        }else{
            if(null == formularioImplementacionRowVOImpl){
                logger.log(ADFLogger.ERROR,"ImplementacionVORowImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"ImplementacionVORowImpl es nulo.");
        }
        logger.log(ADFLogger.WARNING, "El metodo para actualizar Implementacion Obervacion finalizo.");
    }
    
    public void actualizarFormularioImplementacionDatosInicio(){
        logger.log(ADFLogger.WARNING,"INTO actualizarFormularioImplementacionDatosInicio");
        
        FormularioImplementacionPctVORowImpl formularioImplementacionRowVOImpl = 
            (FormularioImplementacionPctVORowImpl)this.getFormularioImplementacionPctVO().getCurrentRow();
        
        ImplementacionVOImpl implementacionVOImpl = this.getImplementacionVO();
        
        Long idImplementacion = null;
        String tipoProceso = null;
        String nombreAdquisicion = null;
        BigDecimal montoPresupuestado = null;
        
        if(formularioImplementacionRowVOImpl != null){
            
            //FORMULARIO OBSERVACION CARGO PREPAGO
            
            if(null != formularioImplementacionRowVOImpl.getId() ){
                idImplementacion = formularioImplementacionRowVOImpl.getId();
            }
            if(null != formularioImplementacionRowVOImpl.getTipoProceso() ){
                tipoProceso = formularioImplementacionRowVOImpl.getTipoProceso();
            }
            if(null != formularioImplementacionRowVOImpl.getNombreAdquisicion()){
                nombreAdquisicion = formularioImplementacionRowVOImpl.getNombreAdquisicion();
            }
            if(null != formularioImplementacionRowVOImpl.getMontoPresupuestado()){
                montoPresupuestado = formularioImplementacionRowVOImpl.getMontoPresupuestado();
            }
            
            implementacionVOImpl.actualizarDatosInicio(idImplementacion, tipoProceso,nombreAdquisicion,montoPresupuestado);                   
            logger.log(ADFLogger.WARNING,"El valor de tipoProceso es : " + tipoProceso);
            logger.log(ADFLogger.WARNING,"El valor de nombreAdquisicion es : " + nombreAdquisicion);
            logger.log(ADFLogger.WARNING,"El valor de montoPresupuestado es : " + montoPresupuestado);
            
        }else{
            if(null == formularioImplementacionRowVOImpl){
                logger.log(ADFLogger.ERROR,"ImplementacionVORowImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"ImplementacionVORowImpl es nulo.");
        }
        logger.log(ADFLogger.WARNING, "El metodo para actualizar Implementacion Datos Inicio finalizo.");
    }

    public void agregarParticipante(Long idLote){
        logger.warning("Entra en agregarParticipante");
        
        ImplementacionParticipanteVORowImpl implementacionParticipanteVORowImpl = 
            (ImplementacionParticipanteVORowImpl)this.getImplementacionParticipanteVO().getCurrentRow();
        ConcursanteImplementacionVOImpl concursanteImplementacionVOImpl = this.getConcursanteImplementacionVO();
        
        String nombreParticipante = null;
        Integer idTcaPaisParticipante = null;
        Long idConcursantePrticipante = null;
        
        try{
            if(null != implementacionParticipanteVORowImpl){
                if(null != implementacionParticipanteVORowImpl.getNombreParticipante()){
                    nombreParticipante = implementacionParticipanteVORowImpl.getNombreParticipante();
                }else{
                    logger.warning("El nombre del participante es nulo.");
                }
                if(null != implementacionParticipanteVORowImpl.getIdCatPaisParticipante()){
                    idTcaPaisParticipante = implementacionParticipanteVORowImpl.getIdCatPaisParticipante();
                }else{
                    logger.warning("El id del pais del participante es nulo.");
                }
            }else{
                logger.warning("El current row es nulo.");
            }
            if(null != idLote){
            idConcursantePrticipante = concursanteImplementacionVOImpl.insertarParticipante(idLote, nombreParticipante, idTcaPaisParticipante);
            }else{
                logger.warning("El id lote de implementacion es nulo.");
            }
            if(null != idConcursantePrticipante){
                implementacionParticipanteVORowImpl.setId(idConcursantePrticipante);
            }else{
                logger.warning("El id de concursante recuperado es nulo.");
            }
        }catch(Exception e){
            logger.severe("Error al guardar el participante en base de datos." + e.getClass() + "." + e);
        }
    }
    
    public void crearAdjudicatario(Long idImplementacion, Long idParticipante, String nombreParticipante, Integer idCatPaisParticipante){
        ImplementacionAdjudicatarioVOImpl implementacionAdjudicatarioVOImpl = this.getImplementacionAdjudicatarioVO();
        ImplementacionVOImpl implementacionVOImpl = this.getImplementacionVO();
        
        Integer IdTcaTipoMoneda = null;
        try{
            if(null != idImplementacion){
            IdTcaTipoMoneda = implementacionVOImpl.obtenerTipoMonedaImplementacion(idImplementacion);
            }else{
                logger.warning("El id de implementacion es nulo.");
            }
        }catch(Exception e){
            logger.warning("Error al recuperar el id de Implementacion." + e.getClass() + "." + e);
        }
        try{
            implementacionAdjudicatarioVOImpl.crearRowAdjudicatario(idParticipante, nombreParticipante, idCatPaisParticipante, IdTcaTipoMoneda);
        }catch(Exception e){
            logger.warning("Error al obtener la descripcion de la moneda." + e.getClass() + "." + e);
        }
    }
    
    public void actualizarFormularioImplementacionInicioAdquisicion(){
        logger.log(ADFLogger.WARNING,"INTO actualizarFormularioImplementacionInicioAdquisicion");
        
        FormularioImplementacionPctVORowImpl formularioImplementacionRowVOImpl = 
            (FormularioImplementacionPctVORowImpl)this.getFormularioImplementacionPctVO().getCurrentRow();
        
        ImplementacionVOImpl implementacionVOImpl = this.getImplementacionVO();
        
        Long idImplementacion = null;
        Timestamp fechaPublicacion = null;
        Timestamp fechaLimiteRecepcionPropuestas = null;
        BigDecimal montoPresupuestado = null;
        Integer idTcaTipoMoneda = null;
        Integer requiereLotes = null;
        
        if(formularioImplementacionRowVOImpl != null){
            
            //FORMULARIO OBSERVACION CARGO PREPAGO
            
            if(null != formularioImplementacionRowVOImpl.getId() ){
                idImplementacion = formularioImplementacionRowVOImpl.getId();
            }
            if(null != formularioImplementacionRowVOImpl.getFechaPublicacion()){
                fechaPublicacion = formularioImplementacionRowVOImpl.getFechaPublicacion();
            }
            if(null != formularioImplementacionRowVOImpl.getFechaLimiteRecepcion()){
                fechaLimiteRecepcionPropuestas = formularioImplementacionRowVOImpl.getFechaLimiteRecepcion();
            }
            if(null != formularioImplementacionRowVOImpl.getMontoPresupuestado()){
                montoPresupuestado = formularioImplementacionRowVOImpl.getMontoPresupuestado();
            }
            if(null != formularioImplementacionRowVOImpl.getIdTcaTipoMoneda()){
                idTcaTipoMoneda = formularioImplementacionRowVOImpl.getIdTcaTipoMoneda();
            }
            if(null != formularioImplementacionRowVOImpl.getRequiereLotes()){
                requiereLotes = formularioImplementacionRowVOImpl.getRequiereLotes();
            }else{
                requiereLotes = 0;
            }
            //fechaLimiteRecepcionPropuestas = new java.sql.Timestamp(System.currentTimeMillis());
            //fechaPublicacion = new java.sql.Timestamp(System.currentTimeMillis());
            implementacionVOImpl.actualizarInicioAdquisicion(idImplementacion, fechaPublicacion, 
                                                             fechaLimiteRecepcionPropuestas,montoPresupuestado,
                                                             idTcaTipoMoneda, requiereLotes);                   
            logger.log(ADFLogger.WARNING,"El valor de fechaPublicacion es : " + fechaPublicacion);
            logger.log(ADFLogger.WARNING,"El valor de fechaLimiteRecepcionPropuestas es : " + fechaLimiteRecepcionPropuestas);
            logger.log(ADFLogger.WARNING,"El valor de montoPresupuestado es : " + montoPresupuestado);
            logger.log(ADFLogger.WARNING,"El valor de idTcaTipoMoneda es : " + idTcaTipoMoneda);
            logger.log(ADFLogger.WARNING,"El valor de requiereLotes es : " + requiereLotes);
            
        }else{
            if(null == formularioImplementacionRowVOImpl){
                logger.log(ADFLogger.ERROR,"ImplementacionVORowImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"ImplementacionVORowImpl es nulo.");
        }
        logger.log(ADFLogger.WARNING, "El metodo para actualizar Implementacion Datos Inicio finalizo.");
    }
    
    public void actualizarFormularioImplementacionRegistrarResultadoAdquisicion(Long idLoteImplementacion){
        logger.log(ADFLogger.WARNING,"INTO actualizarFormularioImplementacionRegistrarResultadoAdquisicion");
        
        FormularioImplementacionPctVORowImpl formularioImplementacionRowVOImpl = 
            (FormularioImplementacionPctVORowImpl)this.getFormularioImplementacionPctVO().getCurrentRow();
        
        LoteImplementacionVOImpl loteImplementacionVOImpl = this.getLoteImplementacionVO();
        
        Integer idTcaResultadoProceso = null;
        
        if(formularioImplementacionRowVOImpl != null){
            
            //FORMULARIO OBSERVACION CARGO PREPAGO
            
            if(null != formularioImplementacionRowVOImpl.getIdTcaResultadoProceso() ){
                idTcaResultadoProceso = formularioImplementacionRowVOImpl.getIdTcaResultadoProceso();
            }
            //fechaLimiteRecepcionPropuestas = new java.sql.Timestamp(System.currentTimeMillis());
            //fechaPublicacion = new java.sql.Timestamp(System.currentTimeMillis());
            loteImplementacionVOImpl.actualizarIdTcaResultadoProceso(idLoteImplementacion, idTcaResultadoProceso);                   
            
        }else{
            if(null == formularioImplementacionRowVOImpl){
                logger.log(ADFLogger.ERROR,"LoteImplementacionVORowImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"LoteImplementacionVORowImpl es nulo.");
        }
        logger.log(ADFLogger.WARNING, "El metodo para actualizar LoteImplementacion Datos Inicio finalizo.");
    }
    
    public Long llenarFormularioLoteImplementacion(){
        logger.log(ADFLogger.WARNING,"INTO llenarFormularioLoteImplementacion");
        
        Long id = null;
        Long idImplementacion = null;
        String nombreLote = null;
        BigDecimal montoPresupuestado = null;
        Integer idTcaTipoMoneda = null;
        Integer idTcaResultadoProceso = null;
        
        
        FormularioLoteImplementacionVOImpl formularioLoteImplementacionVOImpl = this.getFormularioLoteImplementacionVO();
        
        LoteImplementacionVORowImpl loteImplementacionVORowImpl = (LoteImplementacionVORowImpl)
            this.getLoteImplementacionVO().getRowAtRangeIndex(0);
        
        if(loteImplementacionVORowImpl != null){
            
            //FORMULARIO LOTE IMPLEMENTACION
            
            if(null != loteImplementacionVORowImpl.getId()){
                id = loteImplementacionVORowImpl.getId();
                //id = id+1;
            }
            if(null != loteImplementacionVORowImpl.getIdImplementacion()){
                idImplementacion = loteImplementacionVORowImpl.getIdImplementacion(); 
            }
            if(null != loteImplementacionVORowImpl.getNombreLote()){
                nombreLote = loteImplementacionVORowImpl.getNombreLote();
                                                    }
            if(null != loteImplementacionVORowImpl.getMontoPresupuestado()){
                montoPresupuestado = loteImplementacionVORowImpl.getMontoPresupuestado();
                                                    }
            if(null != loteImplementacionVORowImpl.getIdTcaTipoMoneda()){
                idTcaTipoMoneda = loteImplementacionVORowImpl.getIdTcaTipoMoneda();
                                                    }
            if(null != loteImplementacionVORowImpl.getIdTcaResultadoProceso()){
                idTcaResultadoProceso = loteImplementacionVORowImpl.getIdTcaResultadoProceso();
                                                   }
            formularioLoteImplementacionVOImpl.crearRowFormularioLoteImplementacionInsertarLote(); 
        }else{
            if(null == loteImplementacionVORowImpl){
                logger.log(ADFLogger.ERROR,"loteImplementacionVORowImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"loteImplementacionVORowImpl es nulo.");
        }
        logger.log(ADFLogger.WARNING, "El metodo para llenar el Formulario de Lote Implementacion finalizo.");
        return id;
    }
    
    public void agregarLote(Long idImplementacion){
        logger.warning("Entra en agregarLote");
        
        FormularioLoteImplementacionVORowImpl formularioLoteImplementacionVORowImpl = 
            (FormularioLoteImplementacionVORowImpl)this.getFormularioLoteImplementacionVO().getCurrentRow();
        LoteImplementacionVOImpl loteImplementacionVOImpl = this.getLoteImplementacionVO();
        
        Long idLote = null;
        String nombreLote = null;
        BigDecimal montoPresupuestado  = null;
        Integer idTcaTipoMoneda = null;
        Integer idTcaResultadoProceso = null;
        
        try{
            if(null != formularioLoteImplementacionVORowImpl){
                if(null != formularioLoteImplementacionVORowImpl.getNombreLote()){
                    nombreLote = formularioLoteImplementacionVORowImpl.getNombreLote();
                }else{
                    logger.warning("El nombre de lote es nulo.");
                }
                if(null != formularioLoteImplementacionVORowImpl.getMontoPresupuestado()){
                    montoPresupuestado = formularioLoteImplementacionVORowImpl.getMontoPresupuestado();
                }else{
                    logger.warning("El monto presupuestado es nulo.");
                }
                if(null != formularioLoteImplementacionVORowImpl.getIdTcaTipoMoneda()){
                    idTcaTipoMoneda = formularioLoteImplementacionVORowImpl.getIdTcaTipoMoneda();
                }else{
                    logger.warning("El id tca tipo moneda es nulo.");
                }
            }else{
                logger.warning("El current row es nulo.");
            }
            idTcaResultadoProceso = formularioLoteImplementacionVORowImpl.getIdTcaResultadoProceso();
            if(null != idImplementacion){
            idLote = loteImplementacionVOImpl.agregarLoteImplementacion(idImplementacion, nombreLote, montoPresupuestado, idTcaTipoMoneda, idTcaResultadoProceso);
            }else{
                logger.warning("El id implementacion es nulo.");
            }
            if(null != idLote){
                formularioLoteImplementacionVORowImpl.setId(idLote);
            }else{
                logger.warning("El id lote recuperado es nulo.");
            }
        }catch(Exception e){
            logger.severe("Error al guardar el lote en base de datos." + e.getClass() + "." + e);
        }
    }
    
    public void actualizarLoteImplementacionAM(){
        logger.log(ADFLogger.WARNING,"INTO actualizarLoteImplementacionAM");
        
        FormularioLoteImplementacionVORowImpl formularioLoteImplementacionRowVOImpl = 
            (FormularioLoteImplementacionVORowImpl)this.getFormularioLoteImplementacionVO().getCurrentRow();
        
        LoteImplementacionVOImpl loteImplementacionVOImpl = this.getLoteImplementacionVO();
        
        Long idLote = null;
        String nombreLote = null;
        BigDecimal montoPresupuestado = null;
        Integer idTcaTipoMoneda = null;
        
        if(formularioLoteImplementacionRowVOImpl != null){
            
            if(null != formularioLoteImplementacionRowVOImpl.getId() ){
                idLote = formularioLoteImplementacionRowVOImpl.getId();
            }
            if(null != formularioLoteImplementacionRowVOImpl.getNombreLote()){
                nombreLote = formularioLoteImplementacionRowVOImpl.getNombreLote();
            }
            if(null != formularioLoteImplementacionRowVOImpl.getMontoPresupuestado()){
                montoPresupuestado = formularioLoteImplementacionRowVOImpl.getMontoPresupuestado();
            }
            if(null != formularioLoteImplementacionRowVOImpl.getIdTcaTipoMoneda()){
                idTcaTipoMoneda = formularioLoteImplementacionRowVOImpl.getIdTcaTipoMoneda();
            }
            
            logger.log(ADFLogger.WARNING,"El valor de nombreLote enviado  es : " + nombreLote);
            logger.log(ADFLogger.WARNING,"El valor de montoPresupuestado enviado es : " + montoPresupuestado);
            logger.log(ADFLogger.WARNING,"El valor de idTcaTipoMoneda enviado es : " + idTcaTipoMoneda);
            
            loteImplementacionVOImpl.actualizarLoteImplementacion(idLote, nombreLote, montoPresupuestado, idTcaTipoMoneda);
            
        }else{
            if(null == loteImplementacionVOImpl){
                logger.log(ADFLogger.ERROR,"loteImplementacionVOImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"loteImplementacionVOImpl es nulo.");
        }
        logger.log(ADFLogger.WARNING, "El metodo para actualizar Lote Implementacion Datos Inicio finalizo.");
    }
    
    public void actualizarConcursanteImplementacion(){
        logger.log(ADFLogger.WARNING,"INTO actualizarConcursanteImplementacion");
        
        ImplementacionParticipanteVORowImpl implementacionParticipanteRowVOImpl =
            (ImplementacionParticipanteVORowImpl)this.getImplementacionParticipanteVO().getCurrentRow();
        
        ConcursanteImplementacionVOImpl concursanteImplementacionVOImpl = this.getConcursanteImplementacionVO();
        
        Long id = null;
        String nombreParticipante = null;
        Integer idCatPais = null;
        
        if(implementacionParticipanteRowVOImpl != null){
            
            if(null != implementacionParticipanteRowVOImpl.getId() ){
                id = implementacionParticipanteRowVOImpl.getId();
            }
            if(null != implementacionParticipanteRowVOImpl.getNombreParticipante()){
                nombreParticipante = implementacionParticipanteRowVOImpl.getNombreParticipante();
            }
            if(null != implementacionParticipanteRowVOImpl.getIdCatPaisParticipante()){
                idCatPais = implementacionParticipanteRowVOImpl.getIdCatPaisParticipante();
            }
            logger.log(ADFLogger.WARNING,"El valor de id enviado es : " + id);
            logger.log(ADFLogger.WARNING,"El valor de nombreParticipante enviado es : " + nombreParticipante);
            logger.log(ADFLogger.WARNING,"El valor de idCatPais enviado es : " + idCatPais);
            concursanteImplementacionVOImpl.actualizarConcursanteImplementacion(id, nombreParticipante, idCatPais);
           
            
        }else{
            if(null == concursanteImplementacionVOImpl){
                logger.log(ADFLogger.ERROR,"concursanteImplementacionVOImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"concursanteImplementacionVOImpl es nulo.");
        }
        logger.log(ADFLogger.WARNING, "El metodo para actualizar Concursante Implementacion finalizo.");
    }
    
    public void actualizarAdjudicatarioImplementacion(){
        logger.log(ADFLogger.WARNING,"INTO actualizarAdjudicatarioImplementacion");
        
        ImplementacionAdjudicatarioVORowImpl implementacionAdjudicatarioRowVOImpl =
            (ImplementacionAdjudicatarioVORowImpl)this.getImplementacionAdjudicatarioVO().getCurrentRow();
        
        ConcursanteImplementacionVOImpl concursanteImplementacionVOImpl = this.getConcursanteImplementacionVO();
        
        Long id = null;
        BigDecimal montoAdjudicado = null;
        Integer idTcaTipoMoneda = null;
        
        if(implementacionAdjudicatarioRowVOImpl != null){
            
            if(null != implementacionAdjudicatarioRowVOImpl.getId() ){
                id = implementacionAdjudicatarioRowVOImpl.getId();
            }
            if(null != implementacionAdjudicatarioRowVOImpl.getMontoAdjudicado()){
                montoAdjudicado = implementacionAdjudicatarioRowVOImpl.getMontoAdjudicado();
            }
            if(null != implementacionAdjudicatarioRowVOImpl.getIdTcaTipoMoneda()){
                idTcaTipoMoneda = implementacionAdjudicatarioRowVOImpl.getIdTcaTipoMoneda();
            }
            logger.log(ADFLogger.WARNING,"El valor de id enviado es : " + id);
            logger.log(ADFLogger.WARNING,"El valor de montoAdjudicado es : " + montoAdjudicado);
            logger.log(ADFLogger.WARNING,"El valor de idTcaTipoMoneda es : " + idTcaTipoMoneda);
            concursanteImplementacionVOImpl.actualizarAdjudicatario(id, montoAdjudicado, idTcaTipoMoneda);
            
            implementacionAdjudicatarioRowVOImpl.setAttribute("HabilitarEdicionMonto", null);
        }else{
            if(null == concursanteImplementacionVOImpl){
                logger.log(ADFLogger.ERROR,"concursanteImplementacionVOImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"concursanteImplementacionVOImpl es nulo.");
        }
        logger.log(ADFLogger.WARNING, "El metodo para actualizar concursante Implementacion Adjudicatario finalizo.");
    }
    
    public Long llenarImplementacionAdjudicatario(){
        logger.log(ADFLogger.WARNING,"INTO llenarFormularioImplementacion");
        
        Long idImplementacion = null;
        String tipoProceso = null;
        String nombreAdquisicion = null;
        Timestamp fechaPublicacion = null;
        Timestamp fechaLimiteRecepcionPropuesta = null;
        BigDecimal montoPresupuestado = null;
        String observacion = null;
        Integer idTcaTipoMoneda = null;
        Integer requiereLotes = null;
        
        FormularioImplementacionPctVOImpl formularioImplementacionVOImpl = this.getFormularioImplementacionPctVO();
        
        ImplementacionVORowImpl implementacionVORowImpl = (ImplementacionVORowImpl)
            this.getImplementacionVO().getRowAtRangeIndex(0);
        
        if(implementacionVORowImpl != null){
            
            //FORMULARIO IMPLEMENTACION
            
            if(null != implementacionVORowImpl.getId()){
                idImplementacion = implementacionVORowImpl.getId(); 
            }
            if(null != implementacionVORowImpl.getTipoProceso()){
                tipoProceso = implementacionVORowImpl.getTipoProceso(); 
            }
            if(null != implementacionVORowImpl.getNombreAdquisicion()){
                nombreAdquisicion = implementacionVORowImpl.getNombreAdquisicion();
                                                    }
            if(null != implementacionVORowImpl.getFechaPublicacion()){
                fechaPublicacion = implementacionVORowImpl.getFechaPublicacion();
                                                    }
            if(null != implementacionVORowImpl.getFechaLimiteRecepcion()){
                fechaLimiteRecepcionPropuesta = implementacionVORowImpl.getFechaLimiteRecepcion();
                                                    }
            if(null != implementacionVORowImpl.getMontoPresupuestado()){
                montoPresupuestado = implementacionVORowImpl.getMontoPresupuestado();
                                                   }
            if(null != implementacionVORowImpl.getObservacion()){
                observacion = implementacionVORowImpl.getObservacion();
                                                   }
            if(null != implementacionVORowImpl.getIdTcaTipoMoneda()){
                idTcaTipoMoneda = implementacionVORowImpl.getIdTcaTipoMoneda();
                                                   }
            if(null != implementacionVORowImpl.getRequiereLotes()){
                requiereLotes = implementacionVORowImpl.getRequiereLotes();
                                                   }
            
            if(tipoProceso.compareTo("ABREVIADO") == 0){
                tipoProceso = "Abreviado";
            }
            else if(tipoProceso.compareTo("CONCURSO") == 0){
                tipoProceso = "Concurso";
            }
            else{
                logger.log(ADFLogger.ERROR, "Error en cambiar valores TipoProceso FenixImplementacion.");
            }
            
            formularioImplementacionVOImpl.crearRowFormImpPctInsertarActualizar(idImplementacion, tipoProceso, 
                                                    nombreAdquisicion, montoPresupuestado, observacion, fechaPublicacion, 
                                                    fechaLimiteRecepcionPropuesta, idTcaTipoMoneda, requiereLotes);
        }else{
            if(null == implementacionVORowImpl){
                logger.log(ADFLogger.ERROR,"implementacionVORowImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"implementacionVORowImpl es nulo.");
        }
        logger.log(ADFLogger.WARNING, "El metodo para llenar el Formulario de Implementacion finalizo.");
        return idImplementacion;
    }
    
    /** 
    * Comienza - IMPLEMENTACION_PCT PROCESO CORE - Registrar datos de linea de credito
    **/
    public void consultarLineaCredito(String idOperacion, Integer idContrato){
        logger.warning("Dentro consultarLineaCredito");
        logger.warning("idOperacion : "+idOperacion);
        logger.warning("idContrato : "+idContrato);
        String wsdl = getWsdl(IWsdlLocation.LINEA_CREDITO);
        
        com.bcie.xmlns.lineacreditoservice.LineaCredito lineaCredito12BndQSService = 
            IWsdlLocation.Service.getInstance(com.bcie.xmlns.lineacreditoservice.LineaCredito.class, wsdl);
        com.bcie.xmlns.lineacreditoservice.LineaCreditoPT lineaCreditoPT =
                        lineaCredito12BndQSService.getLineaCredito12Bnd();

        try {
            logger.log(ADFLogger.WARNING, ">HNWS lineaCreditoPT.toString()" + lineaCreditoPT.toString());
            org.bcie.lineacreditomo.ConsultarLineaCreditoRequestType request =
                            new org.bcie.lineacreditomo.ConsultarLineaCreditoRequestType();
            //se agrega los parametros al request idOperacion
            request.setIdOperacion(Long.parseLong(idOperacion));
            //request.setIdContrato(idContrato);

            Date horaInicio = ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_CONSULTAR_LINEA_CREDITO);
            org.bcie.lineacreditomo.ConsultarLineaCreditoResponseType response =
                        lineaCreditoPT.consultarLineaCredito(request);
            ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_CONSULTAR_LINEA_CREDITO, horaInicio);
            mappingConsultarLineaCredito(response.getClienteContrato(), response.getLineaCreditoAsociada());

            logger.log(ADFLogger.TRACE,"Resultado de ConsultarLineaCredito :"
                                        +response.getResultado().getResult().toString());
            logger.log(ADFLogger.TRACE,"Mensaje de ConsultarLineaCredito :"
                                        +response.getResultado().getMessage().toString());
        } catch (Exception e) {
            logger.warning("Ocurrio un error en consultarLineaCredito", e);
        }
        logger.warning("Termina consultarLineaCredito");
    }
       
        public void  mappingConsultarLineaCredito(List<Contrato> contratos, List<LineaCredito> listaLineaCreditoAsociada){
            logger.warning("Entra en mappingConsultarLineaCredito");
            try {
                EncabezadoRegistrarDatosLineaCreditoVORowImpl encabezadoRegistrarDatosLineaCreditoVO =
                             (EncabezadoRegistrarDatosLineaCreditoVORowImpl) 
                                 getEncabezadoRegistrarDatosLineaCreditoVO().createRow();
                DatosLineaCreditoVORowImpl datosLineaCreditoVO = null;         
                //CuentaClienteVO
                //CatCuentaClienteLOVRowImpl catCuentaCliente=null;
                List<String> listaCuentaCliente = new ArrayList<String>();
                logger.warning("Numero de contratos :"+ contratos.size());
                for(Contrato contrato : contratos){ 
                    logger.warning("Entra en ciclo de contratos.");
                /*****Datos del cliente*****/
                //Catalogo de cuentas del cliente
                if(contrato.getCuentaCliente()!=null){
                    listaCuentaCliente.addAll(contrato.getCuentaCliente().getCuentaCliente());
                    logger.warning("Tamaño de la lista : " + listaCuentaCliente.size());
                }else{
                    logger.warning("El servicio no regreso cuentas de cliente.");
                }
                
                /*****Datos de escrituración*****/
                //Fecha de escrituración
                if(contrato.getFechaFirma()!=null){
                    GregorianCalendar fechaEscrituracion = contrato.getFechaFirma().toGregorianCalendar();
                    encabezadoRegistrarDatosLineaCreditoVO.setAttribute(
                     EncabezadoRegistrarDatosLineaCreditoVORowImpl.FECHAESCRITURACION,
                     new java.sql.Timestamp(fechaEscrituracion.getTimeInMillis()));
                }
                //Tipo de Moneda del Monto escriturado
                if(contrato.getIdTipoMonedaMontoEscriturado()!=null){
                    encabezadoRegistrarDatosLineaCreditoVO.setIdTipoMoneda(contrato.getIdTipoMonedaMontoEscriturado().intValue());
                }
                //Monto escriturado
                if(contrato.getMontoEscriturado()!=null){
                    encabezadoRegistrarDatosLineaCreditoVO.setAttribute(
                         EncabezadoRegistrarDatosLineaCreditoVORowImpl.MONTOESCRITURADO,
                             contrato.getMontoEscriturado());
                }
                //IdContrato
                if(contrato.getIdContrato() >0){
                    encabezadoRegistrarDatosLineaCreditoVO.setAttribute(
                         EncabezadoRegistrarDatosLineaCreditoVORowImpl.IDCONTRATO,contrato.getIdContrato());
                }
                //Agrega el Row del encabezado
                getEncabezadoRegistrarDatosLineaCreditoVO().insertRow(encabezadoRegistrarDatosLineaCreditoVO);
                getEncabezadoRegistrarDatosLineaCreditoVO().setCurrentRow(encabezadoRegistrarDatosLineaCreditoVO);
                
                /*****Linea de credito*****/
                
                if(contrato.getLineaCredito()!=null){
                    List<LineaCredito> listaLineaCredito = contrato.getLineaCredito();
                    logger.warning(">>> Se recuperaron " + listaLineaCredito.size() + " linea(s) de credito");
                    for(LineaCredito lineaCredito : listaLineaCredito ) {
                        logger.warning(">>> IdLineaCredito " + lineaCredito.getIdLineaCredito());
                        logger.warning(">>> NumeroLineaCredito " + lineaCredito.getNumeroLineaCredito());
                        logger.warning(">>> Fondo " + lineaCredito.getFondo());
                        logger.warning(">>> MontoLinea " + lineaCredito.getMontoLinea());
                        logger.warning(">>> Descripcion " + lineaCredito.getDescripcion());
                        
                        if(null != lineaCredito.getIdLineaCredito()){
                            if(0 != lineaCredito.getIdLineaCredito()){
                            datosLineaCreditoVO = null;
                            datosLineaCreditoVO = (DatosLineaCreditoVORowImpl) getDatosLineaCreditoVO().createRow();
                            //Id de linea de credito
                            if(lineaCredito.getIdLineaCredito() != null){
                                datosLineaCreditoVO.setAttribute(DatosLineaCreditoVORowImpl.ID, 
                                                                    lineaCredito.getIdLineaCredito());
                            }
                            //Numero de linea de credito
                            if(lineaCredito.getNumeroLineaCredito() != null){
                                datosLineaCreditoVO.setAttribute(
                                 DatosLineaCreditoVORowImpl.NUMEROLINEACREDITO, lineaCredito.getNumeroLineaCredito());
                            }
                            //Fondo contable
                            if(lineaCredito.getFondo() != null){
                                datosLineaCreditoVO.setAttribute(DatosLineaCreditoVORowImpl.FONDO,lineaCredito.getFondo());
                            }
                            //Monto de la linea
                            if(lineaCredito.getMontoLinea() != null){
                                datosLineaCreditoVO.setAttribute(DatosLineaCreditoVORowImpl.MONTOLINEA, lineaCredito.getMontoLinea());
                            }
                            //Id flexcube 
                            if(lineaCredito.getFlexcube().getId() != null){
                                datosLineaCreditoVO.setAttribute(DatosLineaCreditoVORowImpl.IDFLEXCUBE, lineaCredito.getFlexcube().getId());
                            }
                            //Descripcion de linea de credito
                            //Se agrega la descripcion de la linea de credito por FNXII-3145
                            if(lineaCredito.getDescripcion() != null){
                                datosLineaCreditoVO.setAttribute(DatosLineaCreditoVORowImpl.DESCRIPCION, 
                                                                 lineaCredito.getDescripcion()); 
                            }
                        
                            //agregar row de datosLineaCreditoVO
                            getDatosLineaCreditoVO().insertRow(datosLineaCreditoVO);
                            }else{
                                logger.warning("idLineaCredito es nula");
                            }
                        }else{
                            logger.warning("idLineaCredito es nula");
                        }
                    }
                        
                } else {
                    logger.warning(">>> No se recuperaron lineas de credito");
                }
                }
                
                // Se limpia CatCuentaClienteLOV antes de ser llenarla, para FNXII-6542
                getCatCuentaClienteLOV().executeQuery();
                
                // Se invoca metodo para atender FNXII-6542
                if(listaCuentaCliente.size() > 0){
                    agregarCuentaClienteNoRepetida(listaCuentaCliente);
                }else{
                    logger.warning("No se encontraron cuentas de cliente");
                }
                
                /*****Linea de credito asociada*****/
                cargarLineasCreditoAsociadas(listaLineaCreditoAsociada);
                
                
            } catch(Exception e) {
                logger.warning("Ocurio un error en mappingConsultarLineaCredito", e);
            }
        }
    
        private Boolean agregarCuentaClienteNoRepetida(List<String> listaCuentaCliente){
            logger.warning("Entra en agregarCuentaClienteNoRepetida");
            List<String> listaCuentaMayuscula = new ArrayList<String>();
            CatCuentaClienteLOVRowImpl catCuentaCliente=null;
            Boolean esDatosCorrectos = Boolean.TRUE;
            try {
                //Convierte las cadenas a mayusculas en caso de que llegue alguna con minusculas
                for(String cuentaMayuscula : listaCuentaCliente){
                    cuentaMayuscula = cuentaMayuscula.toUpperCase();
                    logger.warning("Valor en mayusculas cuenta cliente : " + cuentaMayuscula);
                    listaCuentaMayuscula.add(cuentaMayuscula);
                }
                //Se limpia la lista de cuentas de cliente para ignorar los elementos repetidos,se atiende incidencia
                //FNXII-6542.
                Set<String> listaCuentaSinRepetir = new HashSet<>();
                logger.warning("Tamaño de la lista : " + listaCuentaMayuscula.size());
                listaCuentaSinRepetir.addAll(listaCuentaMayuscula);
                listaCuentaMayuscula.clear();
                listaCuentaMayuscula.addAll(listaCuentaSinRepetir);
                logger.warning("Nuevo tamaño de la lista : " +listaCuentaMayuscula.size());
                //Se inserta los rows una vez que se ha limpiado la lista de cuentas repetidas.
                for (String cuentaCliente : listaCuentaMayuscula) {
                    catCuentaCliente = null;
                    catCuentaCliente = (CatCuentaClienteLOVRowImpl) getCatCuentaClienteLOV().createRow();
                    catCuentaCliente.setAttribute(CatCuentaClienteLOVRowImpl.CUENTACLIENTE, cuentaCliente);
                    getCatCuentaClienteLOV().insertRow(catCuentaCliente);
                    getCatCuentaClienteLOV().setCurrentRow(catCuentaCliente);
            }
        } catch (Exception e) {
            logger.warning("Error al convertir las cuentas a mayusculas.", e);
        }
            return esDatosCorrectos;
        }
    private void cargarLineasCreditoAsociadas(List<LineaCredito> listaLineaCreditoAsociada) {
        logger.warning("Entrando en cargarLineasCreditoAsociadas.");
        DatosLineaCreditoVORowImpl datosLineaCreditoVO = null;
        
        try {
            if(null != listaLineaCreditoAsociada){
                logger.warning(">>> Se recuperaron " + listaLineaCreditoAsociada.size() + " linea(s) de credito asociadas");
                for(LineaCredito lineaCredito : listaLineaCreditoAsociada ) {
                    
                    if(null != lineaCredito.getIdLineaCredito()){
                        datosLineaCreditoVO = null;
                        datosLineaCreditoVO = (DatosLineaCreditoVORowImpl) getDatosLineaCreditoVO().createRow();
                    
                        //Id de linea de credito
                        if(lineaCredito.getIdLineaCredito() != null){
                            datosLineaCreditoVO.setAttribute(DatosLineaCreditoVORowImpl.ID, 
                                                         lineaCredito.getIdLineaCredito());
                        }
                    
                        //Numero de linea de credito
                        if(lineaCredito.getNumeroLineaCredito() != null){
                            datosLineaCreditoVO.setAttribute(DatosLineaCreditoVORowImpl.NUMEROLINEACREDITO, 
                                                         lineaCredito.getNumeroLineaCredito());
                        }
                    
                        //Fondo contable
                        if(lineaCredito.getFondo() != null){
                            datosLineaCreditoVO.setAttribute(DatosLineaCreditoVORowImpl.FONDO, 
                                                         lineaCredito.getFondo());
                        }
                    
                        //Monto de la linea
                        if(lineaCredito.getMontoLinea() != null){
                            datosLineaCreditoVO.setAttribute(DatosLineaCreditoVORowImpl.MONTOLINEA, 
                                                         lineaCredito.getMontoLinea());
                        }
                    
                        //Id flexcube 
                        if(lineaCredito.getFlexcube().getId() != null){
                            datosLineaCreditoVO.setAttribute(DatosLineaCreditoVORowImpl.IDFLEXCUBE, 
                                                         lineaCredito.getFlexcube().getId());
                        }
                    
                        //Descripcion de linea de credito
                        //Se agrega la descripcion de la linea de credito por FNXII-3145
                        if(lineaCredito.getDescripcion() != null){
                            datosLineaCreditoVO.setAttribute(DatosLineaCreditoVORowImpl.DESCRIPCION, 
                                                         lineaCredito.getDescripcion()); 
                        }
                    
                        //Se agrega el id de contrato para FNXII-6654
                        datosLineaCreditoVO.setAttribute(DatosLineaCreditoVORowImpl.IDCONTRATO, lineaCredito.getIdContrato());
                        datosLineaCreditoVO.setAttribute(DatosLineaCreditoVORowImpl.IDOPERACION, lineaCredito.getIdOperacion());
                        
                        // Identificador de linea de credito asociada
                        datosLineaCreditoVO.setAttribute(DatosLineaCreditoVORowImpl.LINEAGLOBALIFI, 
                                                     Boolean.TRUE);
                    
                        //agregar row de datosLineaCreditoVO
                        getDatosLineaCreditoVO().insertRow(datosLineaCreditoVO);
                        
                    }else{
                        logger.warning("idLineaCredito es nula");
                    }
                }
            } else {
                logger.warning(">>> No se recuperaron lineas de credito asociadas");
            }
        } catch (Exception e) {
            throw e;
        }
    }
       
    public void consultarLineaCreditoByIdLineaCredito(String idOperacion,Long idLineaCredito,
                                                      Integer idProducto,String idTarea, Integer idContrato){
        logger.warning("Entra en consultarLineaCreditoByIdLineaCredito.");
        String wsdl = getWsdl(IWsdlLocation.LINEA_CREDITO);

        com.bcie.xmlns.lineacreditoservice.LineaCredito lineaCredito12BndQSService = 
            IWsdlLocation.Service.getInstance(com.bcie.xmlns.lineacreditoservice.LineaCredito.class, wsdl);
        com.bcie.xmlns.lineacreditoservice.LineaCreditoPT lineaCreditoPT =
                                    lineaCredito12BndQSService.getLineaCredito12Bnd();
        try {
            logger.log(ADFLogger.WARNING, ">HNWS" + lineaCreditoPT.toString());
        } catch (Exception ex) {
        }
        org.bcie.lineacreditomo.ConsultarLineaCreditoRequestType request = 
                                    new org.bcie.lineacreditomo.ConsultarLineaCreditoRequestType();
        //se agrega los parametros al request idOperacion e instanciaProceso
        //idOperacion
        request.setIdOperacion(Long.parseLong(idOperacion));
        //request.setIdContrato(idContrato);
        
        try {
            Date horaInicio = ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_CONSULTAR_LINEA_CREDITO_BY_ID_LINEA_CREDITO);
            org.bcie.lineacreditomo.ConsultarLineaCreditoResponseType response =
                                    lineaCreditoPT.consultarLineaCredito(request);
            ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_CONSULTAR_LINEA_CREDITO_BY_ID_LINEA_CREDITO, horaInicio);
            //Recupera la lista de lineas de credito,Se envia el idLineaCredito para filtrar y
            //recuperar solo los datos de la lineaCredito que se selecciono en la vista.
            //Manda idProducto para ejecutar el query de CatProductoFlexcubeLOV
            //Invoca el metodo mappingConsultarLineaCredito
            mappingConsultarLineaCredito(response.getClienteContrato(),
                                         idLineaCredito,idProducto,idTarea, 
                                         response.getLineaCreditoAsociada());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void  mappingConsultarLineaCredito(List<Contrato> contratos,
                                              Long idLineaCredito,Integer idProducto,String idTarea, 
                                              List<LineaCredito> listaLineaCreditoAsociada){
        logger.log(ADFLogger.WARNING, "Into mappingConsultarLineaCredito");
        //VO de datosLineaCredito
        //Limpiar VO
        //getRegistrarDatosLineaCreditoVO().executeQuery();
        //Inicialisar VO
        RegistrarDatosLineaCreditoVORowImpl registrarDatosLineaCreditoVO = null;
        //Inicialisar VO vencimientos
        VencimientoPlazoVORowImpl vencimientoPlazoVO = null;
        //Inicialisar VO comisiones
        ComisionVORowImpl comisionVO = null;
        //Variable para generar "id" dentro de vencimientos y comisiones
        Integer idAux = null;
        //Variable para obtener la lista de las lineas de credito por contrato
        List<LineaCredito> listaLineaCredito = new ArrayList<LineaCredito>();
        //Se declara variable para obtener la fecha vigencia
        XMLGregorianCalendar fechaVigencia = null;
        //Se agrega for para recorrer la lista de contratos de acuerdo a el cambio solicitado por QA
        logger.warning("Tamaño de contratos :" + contratos.size());
        for(Contrato contrato : contratos){
            listaLineaCredito = contrato.getLineaCredito();
            fechaVigencia = contrato.getFechaVigencia();
        /*****Linea de credito*****/
        //Validar que la listaLineaCredito no venga "null"
        logger.warning("Tamaño de la lista es :" + listaLineaCredito.size());
        if(listaLineaCredito!=null){
            //Iterar los objetos de lineaCredito que se encuentran en listaLineaCredito
            for(LineaCredito lineaCredito : listaLineaCredito ) {
                logger.warning("Entra a Iterar los objetos de lineaCredito que se encuentran en listaLineaCredito");
                //Validar que idLineaCredito sea el mismo al del objecto lineaCredito
                //para recuperar sus valores.
                if(lineaCredito.getIdLineaCredito().equals(idLineaCredito)){
                    logger.warning("Entra a Validar que idLineaCredito sea el mismo al del objecto lineaCredito");
                    //Crear el Row de registrarDatosLineaCreditoVO
                    getRegistrarDatosLineaCreditoVO().crearRowRegistrarDatosLineaCredito();
                    registrarDatosLineaCreditoVO = (RegistrarDatosLineaCreditoVORowImpl)
                                           getRegistrarDatosLineaCreditoVO().getCurrentRow();
                    //CatFondoContableVO
                    CatFondoContableVOImpl catFondoContableVOImpl = getCatFondoContableVO();
                    //CatProductoFLexcubeLOV
                    CatProductoFlexcubeLOVImpl catProductoFlexcubeLOVImpl = getCatProductoFlexcubeLOV();
                    /*****IdLineaCredito*****/
                    //Recupera el id de la linea de credito
                    if(lineaCredito.getIdLineaCredito() != null){
                        registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.ID,
                                                    lineaCredito.getIdLineaCredito());
                    }else{
                        logger.warning("No se recupero el id de la linea de credito.");
                    }
                    /*****Información general*****/
                    //Numero de linea
                    if(lineaCredito.getNumeroLineaCredito() != null)
                    {
                        registrarDatosLineaCreditoVO.setAttribute(
                                                    RegistrarDatosLineaCreditoVORowImpl.NUMEROLINEACREDITO,
                                                    lineaCredito.getNumeroLineaCredito());
                    }else{
                        logger.warning("No se recupero el numero de la linea de credito.");
                    }
                    //Fondo contable
                    if(lineaCredito.getFondo() != null)
                    {
                        //Recuperar el Row apartir de "CodeDesc" == lineaCredito.getFondo()
                        Row[] rowCatFondoContable = getCatFondoContableVO().
                            getFilteredRows("CodeDesc",lineaCredito.getFondo());
                        //Iterar el Row, Siempre va ser 1 Row
                        for (Row row : rowCatFondoContable)
                        {   
                            //Hacer el row filtrado el CurrentRow en vista
                            getCatFondoContableVO().setCurrentRow(row);
                        }
                        
                    }else{
                        logger.warning("No se recupero el fondo.");
                    }
                    //Es revolvente 
                    if(lineaCredito.isEsRevolvente() != null)
                    {
                        logger.log(ADFLogger.WARNING, "valor isRevolvente:"+lineaCredito.isEsRevolvente());
                        logger.log(ADFLogger.WARNING, "valor idProducto:"+idProducto);
                        //Ejecutar query de CatProductoFlexcubeLOV
                        //Se asigna el valor del idProducto(Parametro de entrada del taskFlow)
                        
                        catProductoFlexcubeLOVImpl.setidProducto(idProducto);
                        catProductoFlexcubeLOVImpl.setidProductoFlexcube(null);
                        
                        /* FNXII-6640.
                        if(!lineaCredito.getFlexcube().getIdProductoFlexcube().isEmpty()){
                            logger.log(ADFLogger.WARNING, "valor IdProductoFlexcube:"+
                                                          lineaCredito.getFlexcube().getIdProductoFlexcube());
                            catProductoFlexcubeLOVImpl.setidProductoFlexcube(
                                                                lineaCredito.getFlexcube().getIdProductoFlexcube());
                            catProductoFlexcubeLOVImpl.setidProducto(null);
                        }else{
                            catProductoFlexcubeLOVImpl.setidProducto(idProducto);
                            catProductoFlexcubeLOVImpl.setidProductoFlexcube(null);
                        }*/
                        
                        //Revolvente púede ser 'Y' o 'N'
                        //Si isEsRevolvente == true --> 'Y'
                        if(lineaCredito.isEsRevolvente())
                        { 
                            catProductoFlexcubeLOVImpl.setisRevolvente(FenixModelConstants.ES_REVOLVENTE_TRUE);
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.ESREVOLVENTE,
                                                        FenixModelConstants.ES_REVOLVENTE_TRUE_INT);
                        }else{
                            //Si isEsRevolvente ==false --> 'N'
                            catProductoFlexcubeLOVImpl.setisRevolvente(FenixModelConstants.ES_REVOLVENTE_FALSE);
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.ESREVOLVENTE,
                                                        FenixModelConstants.ES_REVOLVENTE_FALSE_INT);
                        }
                        //Ejecuta el query de productoFlexcubeLOV
                        if(idTarea.equals(FenixModelConstants.ID_TAREA_VALIDAR_LINEA_CREDITO)
                           && lineaCredito.getFlexcube().getIdProductoFlexcube().isEmpty()){
                               //Si la tarea es validar linea de credito y idProductoFlexcube es null
                                //No mostra valores 
                            logger.log(ADFLogger.WARNING, "No ejecuta query catProductoFlexcubeLOV");
                            catProductoFlexcubeLOVImpl.setidProducto(null);
                            catProductoFlexcubeLOVImpl.setidProductoFlexcube(null);
                            catProductoFlexcubeLOVImpl.setisRevolvente(null);
                            catProductoFlexcubeLOVImpl.executeQuery();
                        }else{
                            logger.log(ADFLogger.WARNING, "Ejecuta query catProductoFlexcubeLOV");
                            catProductoFlexcubeLOVImpl.executeQuery();
                        }
                    }else{
                        logger.warning("No se recupero valor de isResolvente.");
                    }
                    
                    // Mover entre meses 
                    if(lineaCredito.getMoverEntreMeses() != null) {
                                                 
                        // Si moverEntreMeses == 1
                        if(lineaCredito.getMoverEntreMeses().intValue() > 0) { 
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.MOVERENTREMESES,
                                                        FenixModelConstants.ES_REVOLVENTE_TRUE_INT);
                        } else {
                            //Si moverEntreMeses == 0
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.MOVERENTREMESES,
                                                    FenixModelConstants.ES_REVOLVENTE_FALSE_INT);
                        }
                    } else {
                        logger.log(ADFLogger.WARNING, "Mover entre meses es null.");
                        
                        //Si moverEntreMeses == 0
                        registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.MOVERENTREMESES,
                                                FenixModelConstants.ES_REVOLVENTE_FALSE_INT);
                    }

                    //Recursos ordinarios (recursosExternos)
                    if(lineaCredito.isRecursosExternos())
                    {   
                        logger.log(ADFLogger.WARNING, "valor isRecursosExternos:"+lineaCredito.isRecursosExternos());
                        //recurso externo es true,recursos ordinario es false
                        registrarDatosLineaCreditoVO.setAttribute(
                                RegistrarDatosLineaCreditoVORowImpl.ESRECURSOSEXTERNOS,
                                                            FenixModelConstants.ES_RECURSOS_ORDINARIOS_TRUE);
                    }else{
                        //recurso externo es false,recurso ordinario es true
                        registrarDatosLineaCreditoVO.setAttribute(
                                RegistrarDatosLineaCreditoVORowImpl.ESRECURSOSEXTERNOS,
                                                            FenixModelConstants.ES_RECURSOS_ORDINARIOS_FALSE);
                    }
                    //Producto
                    if(lineaCredito.getFlexcube().getIdProductoFlexcube() != null)
                    {
                        Row[] rowCatProductoFlexcube = getCatProductoFlexcubeLOV()
                            .getFilteredRows("Id",lineaCredito.getFlexcube().getIdProductoFlexcube());
                        for (Row row : rowCatProductoFlexcube) {
                            getCatProductoFlexcubeLOV().setCurrentRow(row);
                            }
                    }else{
                        logger.warning("No se recupero el id producto flexcube.");
                    }
                    /*****Condiciones financieras*****/
                    //FNXII-2570
                    //Pruebas-PC05-Formalización-RegistrarDatosLineaCred-Fecha de apertura
                    //NI06    
                    //El valor de este campo aparecerá de conforme a lo guardado en el campo “Fecha de vigencia”
                    //de la tarea “Registrar Fecha Vigencia”.
                    //Fecha de apertura (fechaVigencia)
                    if (null != lineaCredito.getFechaValor()) {
                        GregorianCalendar fechaValorCalendar = lineaCredito.getFechaValor().toGregorianCalendar();
                        registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.FECHAVALOR,
                                                                  new java.sql.Timestamp(fechaValorCalendar.getTimeInMillis()));
                        fechaVigencia = lineaCredito.getFechaValor();
                    } else {
                        //Valida que la fechaVigencia no sea null
                        if (null != fechaVigencia) {
                            GregorianCalendar fechaVigenciaCalendar = fechaVigencia.toGregorianCalendar();
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.FECHAVALOR,
                                                                      new java.sql.Timestamp(fechaVigenciaCalendar.getTimeInMillis()));
                        } else {
                            logger.warning("No se recupero la fecha de vigencia.");
                        }
                    }
                    //Plazo de financiamiento
                    if(lineaCredito.getPlazoFinanciamiento() >=0)
                    {
                        registrarDatosLineaCreditoVO.setAttribute(
                                    RegistrarDatosLineaCreditoVORowImpl.PLAZOFINANCIAMIENTO,
                                    new Integer(lineaCredito.getPlazoFinanciamiento()));
                    }else{
                        logger.warning("No se recupero el plazo de financiamiento.");
                    }
                    //Se recupera el id de CatPlazoFinanciamientoLOV, viene en 
                    //lineaCredito.getFrecuenciaFinanciamiento()
                    if(lineaCredito.getFrecuenciaFinanciamiento() >= 0)
                    {
                        Row[] rowCatPlazoFinanciamiento = getCatPlazoFinanciamientoLOV()
                            .getFilteredRows("Id",new Integer(lineaCredito.getFrecuenciaFinanciamiento()));
                        for (Row row : rowCatPlazoFinanciamiento) {
                            getCatPlazoFinanciamientoLOV().setCurrentRow(row);
                            }
                    }
                    //Periodo de gracia
                    if(lineaCredito.getPlazoGracia() >= 0)
                    {
                        registrarDatosLineaCreditoVO.setAttribute(
                            RegistrarDatosLineaCreditoVORowImpl.PLAZOGRACIA,
                            new Integer(lineaCredito.getPlazoGracia()));
                    }
                    if(lineaCredito.getFrecuenciaGracia() >= 0)
                    {
                        Row[] rowCatPeriodoGracia = getCatPeriodoGraciaLOV()
                            .getFilteredRows("Id",new Integer(lineaCredito.getFrecuenciaGracia()));
                        for (Row row : rowCatPeriodoGracia) {
                            getCatPeriodoGraciaLOV().setCurrentRow(row);
                            }
                    }
                    //fecha vencimiento (fecha calculada apartir de fechaApertura(fechaVigencia),plazoFinanciamiento y plazoGracia)
                    //Validar que fecha de apertura no este null
                    if(fechaVigencia != null)
                    {   
                        //Invocar metodo que calcular la fecha de vencimiento
                        java.sql.Timestamp fechaVencimiento = calcularFechaDeVencimiento(fechaVigencia,
                                                    lineaCredito.getPlazoFinanciamiento(),
                                                    lineaCredito.getFrecuenciaFinanciamiento(),
                                                    lineaCredito.getPlazoGracia(),
                                                    lineaCredito.getFrecuenciaGracia());
                        registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.FECHAVENCIMIENTO,
                                                                        fechaVencimiento);
                    }
                    if(lineaCredito.getTratamientoDiasFeriados() != null)
                    {
                        Row[] rowCatDiasFeriados = getCatDiasFeriadosVO()
                            .getFilteredRows("CodigoExterno",lineaCredito.getTratamientoDiasFeriados());
                        for (Row row : rowCatDiasFeriados) {
                            getCatDiasFeriadosVO().setCurrentRow(row);
                            }
                        
                    }
                    //Monto de la linea
                    if(lineaCredito.getMontoLinea() != null)
                    {
                        registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.MONTOLINEA,
                                                    lineaCredito.getMontoLinea());
                    }
                    
                    /*****Restricciones*****/
                    //Tasa minima
                    if(lineaCredito.getTasaMinima() != null)
                    {
                        registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.TASAMINIMA,
                                                     new Double (lineaCredito.getTasaMinima().getValue()));
                    }
                    //Tasa maxima
                    if(lineaCredito.getTasaMaxima() != null)
                    {
                        registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.TASAMAXIMA,
                                                     new Double (lineaCredito.getTasaMaxima().getValue()));
                    }
                    //Monto minimo
                    if(lineaCredito.getMontoMinimo() != null)
                    {
                        registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.MONTOMINIMO,
                                                     new Double (lineaCredito.getMontoMinimo().getValue()));
                    }
                    //Monto maximo
                    if(lineaCredito.getMontoMaximo() != null)
                    {
                        registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.MONTOMAXIMO,
                                                     new Double (lineaCredito.getMontoMaximo().getValue()));
                    }
                    /*****Condiciones especiales*****/
                    //Condicion financiera especial
                    if(lineaCredito.isCondicionEspecial() != null)
                    {
                        Boolean condicionEspecial = lineaCredito.isCondicionEspecial();
                        registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.CONDICIONESPECIAL,
                                                                  lineaCredito.isCondicionEspecial());
                    }
                    //Descripcion de la condision financiera
                    if(lineaCredito.getDescCondEspecial() != null)
                    {
                        registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.DESCRIPCIONCONDESPECIAL,
                                                                  lineaCredito.getDescCondEspecial());
                    }
                    /*****Mapping vencimiento de plazos*****/
                    //Limpia el VO de VencimientoPlazoVO
                    getVencimientoPlazoVO().executeQuery();
                    //getVencimientoPlazoVO().clearCache();
                    if(lineaCredito.getTermino() != null){
                        List<Termino> listaVencimiento = lineaCredito.getTermino();
                        idAux = new Integer(0);
                        logger.log(ADFLogger.WARNING, "Cantidad de vencimientos : " + listaVencimiento.size());
                        for(Termino vencimiento : listaVencimiento ) {
                            vencimientoPlazoVO = null;
                            idAux++;
                            
                            vencimientoPlazoVO = (VencimientoPlazoVORowImpl)
                                                        getVencimientoPlazoVO().createRow();
                            
                            // Id de VO
                            vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.ID, idAux);
                            
                            //id de evaluacion
                            if(vencimiento.getIdTermino() >= 0){
                                vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.IDVENCIMIENTO,
                                                                vencimiento.getIdTermino());
                            }
                            //Tipo de vencimiento
                            if(vencimiento.getTipoTermino().getDescripcion() !=null){
                                vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.TIPODEVENCIMIENTO,
                                                                vencimiento.getTipoTermino().getDescripcion());
                            }
                            //IdTipoDeVencimiento
                            if(vencimiento.getTipoTermino().getIdCatTermino() != null){
                                vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.IDTIPODEVENCIMIENTO,
                                                                vencimiento.getTipoTermino().getIdCatTermino());
                            }
                            //Tipo de fecha
                            if(vencimiento.getTipoFechaInicio().getDescripcion() != null){
                                vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.TIPODEFECHA,
                                                                vencimiento.getTipoFechaInicio().getDescripcion());
                            }
                            //IdTipoDeFecha
                            if(vencimiento.getTipoFechaInicio().getId() != null){
                                vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.IDTIPODEFECHA,
                                                                vencimiento.getTipoFechaInicio().getId());
                            }
                            //Fecha Inicio
                            if(vencimiento.getFechaInicio() != null){
                                GregorianCalendar fechaInicio = vencimiento.getFechaInicio().toGregorianCalendar();
                                vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.FECHAINICIO,
                                                                new java.sql.Timestamp(fechaInicio.getTimeInMillis()));
                            }
                            //Plazo 
                            if(vencimiento.getPlazo()>=0){
                                vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.PLAZO,vencimiento.getPlazo());
                            }
                            //idPlazo
                            if(vencimiento.getFrecuenciaPlazo().getId() != null){
                                vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.IDPLAZO,
                                                                vencimiento.getFrecuenciaPlazo().getId());
                            }
                            //tipoPlazo(dias,meses,años)
                            if(vencimiento.getFrecuenciaPlazo().getDescripcion() != null){
                                vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.TIPOPLAZO,
                                                                vencimiento.getFrecuenciaPlazo().getDescripcion());
                            }
                            //Fecha vencimiento
                            if(vencimiento.getFechaVencimiento() != null){
                                GregorianCalendar fechaVencimiento = vencimiento.getFechaVencimiento().toGregorianCalendar();
                                vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.FECHAVENCIMIENTO,
                                                                new java.sql.Timestamp(fechaVencimiento.getTimeInMillis()));
                            }
                            //Estado
                            //Por default poner TRUE en el estado de los vencimientos, ya que el servicio no regresa el
                            //estado y el servicio tiene que regresar solo los vencimientos activos
                            vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.ESTADO,Boolean.TRUE);
                            //agregar row de VencimientoPlazoVO
                            getVencimientoPlazoVO().insertRow(vencimientoPlazoVO);
                            
                        } //termina for(Termino vencimiento : listaVencimiento )
                    }//termina if(lineaCredito.getTermino() != null)
                    
                    /*****Mapping comisiones*****/
                    //Limpia el VO de ComisionVO
                    getComisionVO().executeQuery();
                    //getComisionVO().clearCache();
                    if(lineaCredito.getComision() != null){
                        List<org.bcie.comisionbo.Comision> listaComisiones = lineaCredito.getComision();
                        idAux = new Integer(0);
                        logger.log(ADFLogger.WARNING, "Cantidad de comisiones : " + listaComisiones.size());
                        for(org.bcie.comisionbo.Comision comision : listaComisiones ) {
                            comisionVO = null;
                            idAux++;
                            
                            comisionVO = (ComisionVORowImpl) getComisionVO().createRow();
                            
                            // Id de VO
                            comisionVO.setAttribute("Id", idAux);
                            
                            //IdComision
                            if(comision.getIdComision() >= 0){
                                comisionVO.setAttribute(ComisionVORowImpl.IDCOMISION,
                                                        comision.getIdComision());
                            }
                            //IdTipoComision
                            if(comision.getTipoComision().getIdTipoComision().getId() != null){
                                comisionVO.setAttribute(ComisionVORowImpl.IDTIPOCOMISION,comision.getTipoComision().
                                                        getIdTipoComision().getId());
                            }
                            //TipoComision
                            if(comision.getTipoComision().getDescripcion() != null){
                                comisionVO.setAttribute(ComisionVORowImpl.TIPOCOMISION,
                                                        comision.getTipoComision().getDescripcion());
                            }
                            //Porcentaje
                            if(comision.getMontoBase().getPorcentajeMontoBase() != null){ 
                                comisionVO.setAttribute(ComisionVORowImpl.PORCENTAJE,
                                                        (comision.getMontoBase().getPorcentajeMontoBase() + "%"));
                            }
                            //IdFrecuencia
                            if(comision.getTipoFrecuencia().getId() != null){
                                comisionVO.setAttribute(ComisionVORowImpl.IDFRECUENCIA,
                                                        comision.getTipoFrecuencia().getId());
                            }
                            //Frecuencia
                            if(comision.getFrecuenciaPago() != null){
                                comisionVO.setAttribute(ComisionVORowImpl.FRECUENCIA,comision.getFrecuenciaPago());
                            }
                            //TipoFrecuencia
                            if(comision.getTipoFrecuencia().getDescripcion() != null){
                                comisionVO.setAttribute(ComisionVORowImpl.TIPOFRECUENCIA,
                                                        comision.getTipoFrecuencia().getDescripcion());
                            }
                            //IdBaseCalculo
                            if(comision.getBaseCalculo().getId() != null) {
                                comisionVO.setAttribute(ComisionVORowImpl.IDBASECALCULO,
                                                        comision.getBaseCalculo().getId());
                            }
                            //BaseCalculo
                            if(comision.getBaseCalculo().getDescripcion() != null){
                                comisionVO.setAttribute(ComisionVORowImpl.BASECALCULO,
                                                        comision.getBaseCalculo().getDescripcion());
                            }
                            //Estado
                            //Agrega por default true,servicio solo tiene que traer las comisiones activas
                            comisionVO.setAttribute(ComisionVORowImpl.ESTADO,Boolean.TRUE);
                            //Agrega el row a ComisionVO()
                            getComisionVO().insertRow(comisionVO);

                        }
                        
                    }
                        
                    //agregar row de RegistrarDatosLineaCreditoVO
                    logger.log(ADFLogger.WARNING, "Inserta el row a RegistrarDatosLineaCreditoVO");
                    getRegistrarDatosLineaCreditoVO().insertRow(registrarDatosLineaCreditoVO);
                    
                    
                }else{// cierra if(lineaCredito.getIdLineaCredito() == idLineaCredito)
                    logger.warning("Los ID's de Linea de credito no son iguales");
                }
            }// cierra  for(LineaCredito lineaCredito : listaLineaCredito )  
        }else {//cierra if(listaLineaCredito!=null)
            logger.warning("No se encontro lineas de credito, La lista es nula.");
        }
        }
        /*****Linea de credito asociada*****/
        cargarLineaCreditoAsociada(listaLineaCreditoAsociada, idLineaCredito, idProducto, fechaVigencia);
        
    }//cierra mappingConsultarLineaCredito
    
    private void cargarLineaCreditoAsociada(List<LineaCredito> listaLineaCreditoAsociada, 
                                            Long idLineaCredito, Integer idProducto, 
                                            XMLGregorianCalendar fechaVigencia) {
        logger.warning("Entrando en cargarLineaCreditoAsociada.");
        
        RegistrarDatosLineaCreditoVORowImpl registrarDatosLineaCreditoVO = null;
        VencimientoPlazoVORowImpl vencimientoPlazoVO = null;
        ComisionVORowImpl comisionVO = null;
        Integer idAux = null;
        logger.warning("Linea de credito : " + idLineaCredito );
        logger.warning("Producto : " + idProducto );
        logger.warning("Fecha vigencia : " + fechaVigencia );
        
        logger.warning("Numero de lineas asociadas :" + listaLineaCreditoAsociada.size());
        if(listaLineaCreditoAsociada!=null){
            //Iterar los objetos de lineaCredito que se encuentran en listaLineaCredito
            for(LineaCredito lineaCredito : listaLineaCreditoAsociada ) {
                logger.warning("Entra a Iterar los objetos de lineaCredito que se encuentran en listaLineaCredito");
                //Validar que idLineaCredito sea el mismo al del objecto lineaCredito
                //para recuperar sus valores.
                logger.warning("Linea credito asociada :" + lineaCredito.getIdLineaCredito());
                if(null != lineaCredito.getIdLineaCredito() && null != idLineaCredito){
                    if(lineaCredito.getIdLineaCredito().equals(idLineaCredito)){
                        logger.warning("Entra a Validar que idLineaCredito sea el mismo al del objecto lineaCredito");
                        //Crear el Row de registrarDatosLineaCreditoVO
                        getRegistrarDatosLineaCreditoVO().crearRowRegistrarDatosLineaCredito();
                        registrarDatosLineaCreditoVO = (RegistrarDatosLineaCreditoVORowImpl)
                                               getRegistrarDatosLineaCreditoVO().getCurrentRow();
                        //CatFondoContableVO
                        CatFondoContableVOImpl catFondoContableVOImpl = getCatFondoContableVO();
                        //CatProductoFLexcubeLOV
                        CatProductoFlexcubeLOVImpl catProductoFlexcubeLOVImpl = getCatProductoFlexcubeLOV();
                        /*****IdLineaCredito*****/
                        //Recupera el id de la linea de credito
                        if(lineaCredito.getIdLineaCredito() != null){
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.ID,
                                                        lineaCredito.getIdLineaCredito());
                        }else{
                            logger.warning("No se recupero el id de la linea de credito.");
                        }
                        /*****Información general*****/
                        //Numero de linea
                        if(lineaCredito.getNumeroLineaCredito() != null){
                            registrarDatosLineaCreditoVO.setAttribute(
                                                        RegistrarDatosLineaCreditoVORowImpl.NUMEROLINEACREDITO,
                                                        lineaCredito.getNumeroLineaCredito());
                        }else{
                            logger.warning("No se recupero el numero de la linea de credito.");
                        }
                        //Fondo contable
                        if(lineaCredito.getFondo() != null){
                            //Recuperar el Row apartir de "CodeDesc" == lineaCredito.getFondo()
                            Row[] rowCatFondoContable = getCatFondoContableVO().
                                getFilteredRows("CodeDesc",lineaCredito.getFondo());
                            //Iterar el Row, Siempre va ser 1 Row
                            for (Row row : rowCatFondoContable){   
                                //Hacer el row filtrado el CurrentRow en vista
                                getCatFondoContableVO().setCurrentRow(row);
                            }
                            
                        }else{
                            logger.warning("No se recupero el fondo.");
                        }
                        //Es revolvente 
                        if(lineaCredito.isEsRevolvente() != null){
                            logger.log(ADFLogger.WARNING, "valor isRevolvente:"+lineaCredito.isEsRevolvente());
                            logger.log(ADFLogger.WARNING, "valor idProducto:"+idProducto);
                            //Ejecutar query de CatProductoFlexcubeLOV
                            //Se asigna el valor del idProducto(Parametro de entrada del taskFlow)
                            
                            catProductoFlexcubeLOVImpl.setidProducto(idProducto);
                            catProductoFlexcubeLOVImpl.setidProductoFlexcube(null);
                            
                            /* FNXII-6640.
                            if(!lineaCredito.getFlexcube().getIdProductoFlexcube().isEmpty()){
                                logger.log(ADFLogger.WARNING, "valor IdProductoFlexcube:"+
                                                              lineaCredito.getFlexcube().getIdProductoFlexcube());
                                catProductoFlexcubeLOVImpl.setidProductoFlexcube(
                                                                    lineaCredito.getFlexcube().getIdProductoFlexcube());
                                catProductoFlexcubeLOVImpl.setidProducto(null);
                            }else{
                                catProductoFlexcubeLOVImpl.setidProducto(idProducto);
                                catProductoFlexcubeLOVImpl.setidProductoFlexcube(null);
                            }*/
                            
                            //Revolvente púede ser 'Y' o 'N'
                            //Si isEsRevolvente == true --> 'Y'
                            if(lineaCredito.isEsRevolvente()){ 
                                catProductoFlexcubeLOVImpl.setisRevolvente(FenixModelConstants.ES_REVOLVENTE_TRUE);
                                registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.ESREVOLVENTE,
                                                            FenixModelConstants.ES_REVOLVENTE_TRUE_INT);
                            }else{
                                //Si isEsRevolvente ==false --> 'N'
                                catProductoFlexcubeLOVImpl.setisRevolvente(FenixModelConstants.ES_REVOLVENTE_FALSE);
                                registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.ESREVOLVENTE,
                                                            FenixModelConstants.ES_REVOLVENTE_FALSE_INT);
                            }
                            //Ejecuta el query de productoFlexcubeLOV
                            
                            // Se exluyo fragmento de codigo por pertenecer a la tarea 
                            // VALIDAR_LINEA_CREDITO del proceso de formalizacion
                        }else{
                            logger.warning("No se recupero valor de isResolvente.");
                        }
                        
                        // Mover entre meses 
                        if(lineaCredito.getMoverEntreMeses() != null) {
                                                     
                            // Si moverEntreMeses == 1
                            if(lineaCredito.getMoverEntreMeses().intValue() > 0) { 
                                registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.MOVERENTREMESES,
                                                            FenixModelConstants.ES_REVOLVENTE_TRUE_INT);
                            } else {
                                //Si moverEntreMeses == 0
                                registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.MOVERENTREMESES,
                                                        FenixModelConstants.ES_REVOLVENTE_FALSE_INT);
                            }
                        } else {
                            logger.log(ADFLogger.WARNING, "Mover entre meses es null.");
                            
                            //Si moverEntreMeses == 0
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.MOVERENTREMESES,
                                                    FenixModelConstants.ES_REVOLVENTE_FALSE_INT);
                        }
    
                        //Recursos ordinarios (recursosExternos)
                        if(lineaCredito.isRecursosExternos()){   
                            logger.log(ADFLogger.WARNING, "valor isRecursosExternos:"+lineaCredito.isRecursosExternos());
                            //recurso externo es true,recursos ordinario es false
                            registrarDatosLineaCreditoVO.setAttribute(
                                    RegistrarDatosLineaCreditoVORowImpl.ESRECURSOSEXTERNOS,
                                                                FenixModelConstants.ES_RECURSOS_ORDINARIOS_TRUE);
                        }else{
                            //recurso externo es false,recurso ordinario es true
                            registrarDatosLineaCreditoVO.setAttribute(
                                    RegistrarDatosLineaCreditoVORowImpl.ESRECURSOSEXTERNOS,
                                                                FenixModelConstants.ES_RECURSOS_ORDINARIOS_FALSE);
                        }
                        //Producto
                        if(lineaCredito.getFlexcube().getIdProductoFlexcube() != null){
                            Row[] rowCatProductoFlexcube = getCatProductoFlexcubeLOV()
                                .getFilteredRows("Id",lineaCredito.getFlexcube().getIdProductoFlexcube());
                            for (Row row : rowCatProductoFlexcube) {
                                getCatProductoFlexcubeLOV().setCurrentRow(row);
                            }
                        }else{
                            logger.warning("No se recupero el id producto flexcube.");
                        }
                        /*****Condiciones financieras*****/
                        //FNXII-2570
                        //Pruebas-PC05-Formalización-RegistrarDatosLineaCred-Fecha de apertura
                        //NI06    
                        //El valor de este campo aparecerá de conforme a lo guardado en el campo “Fecha de vigencia”
                        //de la tarea “Registrar Fecha Vigencia”.
                        //Fecha de apertura (fechaVigencia)
                        if (null != lineaCredito.getFechaValor()) {
                            GregorianCalendar fechaValorCalendar = lineaCredito.getFechaValor().toGregorianCalendar();
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.FECHAVALOR,
                                                                      new java.sql.Timestamp(fechaValorCalendar.getTimeInMillis()));
                            fechaVigencia = lineaCredito.getFechaValor();
                        } else {
                            //Valida que la fechaVigencia no sea null
                            if (null != fechaVigencia) {
                                GregorianCalendar fechaVigenciaCalendar = fechaVigencia.toGregorianCalendar();
                                registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.FECHAVALOR,
                                                                          new java.sql.Timestamp(fechaVigenciaCalendar.getTimeInMillis()));
                            } else {
                                logger.warning("No se recupero la fecha de vigencia.");
                            }
                        }
                        //Plazo de financiamiento
                        if(lineaCredito.getPlazoFinanciamiento() >=0){
                            registrarDatosLineaCreditoVO.setAttribute(
                                        RegistrarDatosLineaCreditoVORowImpl.PLAZOFINANCIAMIENTO,
                                        new Integer(lineaCredito.getPlazoFinanciamiento()));
                        }else{
                            logger.warning("No se recupero el plazo de financiamiento.");
                        }
                        //Se recupera el id de CatPlazoFinanciamientoLOV, viene en 
                        //lineaCredito.getFrecuenciaFinanciamiento()
                        if(lineaCredito.getFrecuenciaFinanciamiento() >= 0){
                            Row[] rowCatPlazoFinanciamiento = getCatPlazoFinanciamientoLOV()
                                .getFilteredRows("Id",new Integer(lineaCredito.getFrecuenciaFinanciamiento()));
                            for (Row row : rowCatPlazoFinanciamiento) {
                                getCatPlazoFinanciamientoLOV().setCurrentRow(row);
                                }
                        }
                        //Periodo de gracia
                        if(lineaCredito.getPlazoGracia() >= 0){
                            registrarDatosLineaCreditoVO.setAttribute(
                                RegistrarDatosLineaCreditoVORowImpl.PLAZOGRACIA,
                                new Integer(lineaCredito.getPlazoGracia()));
                        }
                        if(lineaCredito.getFrecuenciaGracia() >= 0){
                            Row[] rowCatPeriodoGracia = getCatPeriodoGraciaLOV()
                                .getFilteredRows("Id",new Integer(lineaCredito.getFrecuenciaGracia()));
                            for (Row row : rowCatPeriodoGracia) {
                                getCatPeriodoGraciaLOV().setCurrentRow(row);
                                }
                        }
                        //fecha vencimiento (fecha calculada apartir de fechaApertura(fechaVigencia),plazoFinanciamiento y plazoGracia)
                        //Validar que fecha de apertura no este null
                        if(fechaVigencia != null){   
                            //Invocar metodo que calcular la fecha de vencimiento
                            java.sql.Timestamp fechaVencimiento = calcularFechaDeVencimiento(fechaVigencia,
                                                        lineaCredito.getPlazoFinanciamiento(),
                                                        lineaCredito.getFrecuenciaFinanciamiento(),
                                                        lineaCredito.getPlazoGracia(),
                                                        lineaCredito.getFrecuenciaGracia());
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.FECHAVENCIMIENTO,
                                                                            fechaVencimiento);
                        }
                        if(lineaCredito.getTratamientoDiasFeriados() != null){
                            Row[] rowCatDiasFeriados = getCatDiasFeriadosVO()
                                .getFilteredRows("CodigoExterno",lineaCredito.getTratamientoDiasFeriados());
                            for (Row row : rowCatDiasFeriados) {
                                getCatDiasFeriadosVO().setCurrentRow(row);
                            }
                        }
                        //Monto de la linea
                        if(lineaCredito.getMontoLinea() != null){
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.MONTOLINEA,
                                                        lineaCredito.getMontoLinea());
                        }
                        
                        /*****Restricciones*****/
                        //Tasa minima
                        if(lineaCredito.getTasaMinima() != null){
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.TASAMINIMA,
                                                         new Double (lineaCredito.getTasaMinima().getValue()));
                        }
                        //Tasa maxima
                        if(lineaCredito.getTasaMaxima() != null){
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.TASAMAXIMA,
                                                         new Double (lineaCredito.getTasaMaxima().getValue()));
                        }
                        //Monto minimo
                        if(lineaCredito.getMontoMinimo() != null){
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.MONTOMINIMO,
                                                         new Double (lineaCredito.getMontoMinimo().getValue()));
                        }
                        //Monto maximo
                        if(lineaCredito.getMontoMaximo() != null){
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.MONTOMAXIMO,
                                                         new Double (lineaCredito.getMontoMaximo().getValue()));
                        }
                        /*****Condiciones especiales*****/
                        //Condicion financiera especial
                        if(lineaCredito.isCondicionEspecial() != null){
                            Boolean condicionEspecial = lineaCredito.isCondicionEspecial();
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.CONDICIONESPECIAL,
                                                                      lineaCredito.isCondicionEspecial());
                        }
                        //Descripcion de la condision financiera
                        if(lineaCredito.getDescCondEspecial() != null){
                            registrarDatosLineaCreditoVO.setAttribute(RegistrarDatosLineaCreditoVORowImpl.DESCRIPCIONCONDESPECIAL,
                                                                      lineaCredito.getDescCondEspecial());
                        }
                        /*****Mapping vencimiento de plazos*****/
                        //Limpia el VO de VencimientoPlazoVO
                        getVencimientoPlazoVO().executeQuery();
                        //getVencimientoPlazoVO().clearCache();
                        if(lineaCredito.getTermino() != null){
                            List<Termino> listaVencimiento = lineaCredito.getTermino();
                            idAux = new Integer(0);
                            logger.log(ADFLogger.WARNING, "Cantidad de vencimientos : " + listaVencimiento.size());
                            for(Termino vencimiento : listaVencimiento ) {
                                vencimientoPlazoVO = null;
                                idAux++;
                                
                                vencimientoPlazoVO = (VencimientoPlazoVORowImpl)
                                                            getVencimientoPlazoVO().createRow();
                                
                                // Id de VO
                                vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.ID, idAux);
                                
                                //id de evaluacion
                                if(vencimiento.getIdTermino() >= 0){
                                    vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.IDVENCIMIENTO,
                                                                    vencimiento.getIdTermino());
                                }
                                //Tipo de vencimiento
                                if(vencimiento.getTipoTermino().getDescripcion() !=null){
                                    vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.TIPODEVENCIMIENTO,
                                                                    vencimiento.getTipoTermino().getDescripcion());
                                }
                                //IdTipoDeVencimiento
                                if(vencimiento.getTipoTermino().getIdCatTermino() != null){
                                    vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.IDTIPODEVENCIMIENTO,
                                                                    vencimiento.getTipoTermino().getIdCatTermino());
                                }
                                //Tipo de fecha
                                if(vencimiento.getTipoFechaInicio().getDescripcion() != null){
                                    vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.TIPODEFECHA,
                                                                    vencimiento.getTipoFechaInicio().getDescripcion());
                                }
                                //IdTipoDeFecha
                                if(vencimiento.getTipoFechaInicio().getId() != null){
                                    vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.IDTIPODEFECHA,
                                                                    vencimiento.getTipoFechaInicio().getId());
                                }
                                //Fecha Inicio
                                if(vencimiento.getFechaInicio() != null){
                                    GregorianCalendar fechaInicio = vencimiento.getFechaInicio().toGregorianCalendar();
                                    vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.FECHAINICIO,
                                                                    new java.sql.Timestamp(fechaInicio.getTimeInMillis()));
                                }
                                //Plazo 
                                if(vencimiento.getPlazo()>=0){
                                    vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.PLAZO,vencimiento.getPlazo());
                                }
                                //idPlazo
                                if(vencimiento.getFrecuenciaPlazo().getId() != null){
                                    vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.IDPLAZO,
                                                                    vencimiento.getFrecuenciaPlazo().getId());
                                }
                                //tipoPlazo(dias,meses,años)
                                if(vencimiento.getFrecuenciaPlazo().getDescripcion() != null){
                                    vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.TIPOPLAZO,
                                                                    vencimiento.getFrecuenciaPlazo().getDescripcion());
                                }
                                //Fecha vencimiento
                                if(vencimiento.getFechaVencimiento() != null){
                                    GregorianCalendar fechaVencimiento = vencimiento.getFechaVencimiento().toGregorianCalendar();
                                    vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.FECHAVENCIMIENTO,
                                                                    new java.sql.Timestamp(fechaVencimiento.getTimeInMillis()));
                                }
                                //Estado
                                //Por default poner TRUE en el estado de los vencimientos, ya que el servicio no regresa el
                                //estado y el servicio tiene que regresar solo los vencimientos activos
                                vencimientoPlazoVO.setAttribute(VencimientoPlazoVORowImpl.ESTADO,Boolean.TRUE);
                                //agregar row de VencimientoPlazoVO
                                getVencimientoPlazoVO().insertRow(vencimientoPlazoVO);
                                
                            } //termina for(Termino vencimiento : listaVencimiento )
                        }//termina if(lineaCredito.getTermino() != null)
                        
                        /*****Mapping comisiones*****/
                        //Limpia el VO de ComisionVO
                        getComisionVO().executeQuery();
                        //getComisionVO().clearCache();
                        if(lineaCredito.getComision() != null){
                            List<org.bcie.comisionbo.Comision> listaComisiones = lineaCredito.getComision();
                            idAux = new Integer(0);
                            logger.log(ADFLogger.WARNING, "Cantidad de comisiones : " + listaComisiones.size());
                            for(org.bcie.comisionbo.Comision comision : listaComisiones ) {
                                comisionVO = null;
                                idAux++;
                                
                                comisionVO = (ComisionVORowImpl) getComisionVO().createRow();
                                
                                // Id de VO
                                comisionVO.setAttribute("Id", idAux);
                                
                                //IdComision
                                if(comision.getIdComision() >= 0){
                                    comisionVO.setAttribute(ComisionVORowImpl.IDCOMISION,
                                                            comision.getIdComision());
                                }
                                //IdTipoComision
                                if(comision.getTipoComision().getIdTipoComision().getId() != null){
                                    comisionVO.setAttribute(ComisionVORowImpl.IDTIPOCOMISION,comision.getTipoComision().
                                                            getIdTipoComision().getId());
                                }
                                //TipoComision
                                if(comision.getTipoComision().getDescripcion() != null){
                                    comisionVO.setAttribute(ComisionVORowImpl.TIPOCOMISION,
                                                            comision.getTipoComision().getDescripcion());
                                }
                                //Porcentaje
                                if(comision.getMontoBase().getPorcentajeMontoBase() != null){ 
                                    comisionVO.setAttribute(ComisionVORowImpl.PORCENTAJE,
                                                            (comision.getMontoBase().getPorcentajeMontoBase() + "%"));
                                }
                                //IdFrecuencia
                                if(comision.getTipoFrecuencia().getId() != null){
                                    comisionVO.setAttribute(ComisionVORowImpl.IDFRECUENCIA,
                                                            comision.getTipoFrecuencia().getId());
                                }
                                //Frecuencia
                                if(comision.getFrecuenciaPago() != null){
                                    comisionVO.setAttribute(ComisionVORowImpl.FRECUENCIA,comision.getFrecuenciaPago());
                                }
                                //TipoFrecuencia
                                if(comision.getTipoFrecuencia().getDescripcion() != null){
                                    comisionVO.setAttribute(ComisionVORowImpl.TIPOFRECUENCIA,
                                                            comision.getTipoFrecuencia().getDescripcion());
                                }
                                //IdBaseCalculo
                                if(comision.getBaseCalculo().getId() != null) {
                                    comisionVO.setAttribute(ComisionVORowImpl.IDBASECALCULO,
                                                            comision.getBaseCalculo().getId());
                                }
                                //BaseCalculo
                                if(comision.getBaseCalculo().getDescripcion() != null){
                                    comisionVO.setAttribute(ComisionVORowImpl.BASECALCULO,
                                                            comision.getBaseCalculo().getDescripcion());
                                }
                                //Estado
                                //Agrega por default true,servicio solo tiene que traer las comisiones activas
                                comisionVO.setAttribute(ComisionVORowImpl.ESTADO,Boolean.TRUE);
                                //Agrega el row a ComisionVO()
                                getComisionVO().insertRow(comisionVO);
                            }
                        }
                        
                        //agregar row de RegistrarDatosLineaCreditoVO
                        logger.log(ADFLogger.WARNING, "Inserta el row a RegistrarDatosLineaCreditoVO");
                        getRegistrarDatosLineaCreditoVO().insertRow(registrarDatosLineaCreditoVO);    
                    }else{// cierra if(lineaCredito.getIdLineaCredito() == idLineaCredito)
                        logger.warning("Los ID's de Linea de credito asociadas no son iguales");
                    }
                }else{
                    logger.warning("Los ID's de Linea de credito asociadas son nulas.");
                }
                
            }// cierra  for(LineaCredito lineaCredito : listaLineaCredito )  
        }else {//cierra if(listaLineaCredito!=null)
            logger.warning("No se encontro lineas de credito asociadas, La lista es nula.");
        }
    }
    
    public void inicializarRegistrarDatosLineaCredito(){
        RegistrarDatosLineaCreditoVOImpl registrarDatosLineaCreditoVOImpl = 
                                                getRegistrarDatosLineaCreditoVO();
        registrarDatosLineaCreditoVOImpl.crearRowRegistrarDatosLineaCredito();
    }
    
    public void filtrarProducto(Integer idProducto,String isRevolvente){
        logger.log(ADFLogger.TRACE, "Into method filtrarProducto");
        logger.log(ADFLogger.TRACE, "value idProducto:"+idProducto);
        logger.log(ADFLogger.TRACE, "value isRevolvente:"+isRevolvente);
        //CatProductoFLexcubeLOV
        CatProductoFlexcubeLOVImpl catProductoFlexcubeLOVImpl = getCatProductoFlexcubeLOV();
        catProductoFlexcubeLOVImpl.setidProducto(idProducto);
        catProductoFlexcubeLOVImpl.setisRevolvente(isRevolvente);
        catProductoFlexcubeLOVImpl.setidProductoFlexcube(null);
        catProductoFlexcubeLOVImpl.executeQuery();
        catProductoFlexcubeLOVImpl.getAllRowsInRange();
        logger.log(ADFLogger.TRACE, "La cantidad de row obtenido es :" + catProductoFlexcubeLOVImpl.getRowCount());
    }
    
    
    public void actualizarLineaCredito(String idOperacion){
        logger.warning("Entrando en actualizarLineaCredito de pct");
        try {
            //Se valida si es una linea de credito asociada
            RegistrarDatosLineaCreditoVORowImpl lineaCredito =
                (RegistrarDatosLineaCreditoVORowImpl) getRegistrarDatosLineaCreditoVO().getCurrentRow();
            
            String wsdl = getWsdl(IWsdlLocation.LINEA_CREDITO);
            
            com.bcie.xmlns.lineacreditoservice.LineaCredito lineaCredito12BndQSService = 
                IWsdlLocation.Service.getInstance(com.bcie.xmlns.lineacreditoservice.LineaCredito.class, wsdl);
            LineaCreditoPT lineaCredito12Port = lineaCredito12BndQSService.getLineaCredito12Bnd();
            logger.log(ADFLogger.WARNING, ">HNWS" + lineaCredito12Port.toString());
            ActualizarContratoLineaCreditoRequestType request = new ActualizarContratoLineaCreditoRequestType();
            ActualizarContratoLineaCreditoResponseType response = new ActualizarContratoLineaCreditoResponseType();
            Contrato contrato = new Contrato();
            contrato = mappingActualizarLineaCredito((EncabezadoRegistrarDatosLineaCreditoVORowImpl)
                        this.getEncabezadoRegistrarDatosLineaCreditoVO().getRowAtRangeIndex(0),
                        (RegistrarDatosLineaCreditoVORowImpl)this.getRegistrarDatosLineaCreditoVO().getCurrentRow(),
                        idOperacion);
            
            request.setContrato(contrato);

            Date horaInicio = ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_ACTUALIZAR_LINEA_CREDITO);
            response = lineaCredito12Port.actualizarContratoLineaCredito(request);
            ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_ACTUALIZAR_LINEA_CREDITO, horaInicio);
            logger.log(ADFLogger.TRACE, "Resultado: " +response.getResultado().getResult());
            logger.log(ADFLogger.TRACE, "Mensaje: " +response.getResultado().getMessage());
            if (null != response.getResultado() &&
                response.getResultado().getResult().toString().equalsIgnoreCase("OK")) {
                //Actualizo correctamente

            } else {
                String msg = "ERROR";

                if (null != response.getResultado())
                    msg = response.getResultado().getMessage();

                throw new Exception("WS Aprobacion Error: RESULT " + msg);
            }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al acutalizar la linea de credito en el proceso de implementacionpct", e);
            JboException ex = new JboException("Error en la actualizacion de Lineas de credito,favor de intentar más tarde.");
            throw ex;
            
        }         
    }
    
    public  Contrato mappingActualizarLineaCredito(EncabezadoRegistrarDatosLineaCreditoVORowImpl 
                                                    encabezadoResgitrarDatosLineaCreditoVORow,
                                                   RegistrarDatosLineaCreditoVORowImpl registrarDatosLineaCreditoVORow,
                                                   String idOperacion){
        
        Contrato contrato = new Contrato();
        CatCuentaClienteLOVImpl catCuentaClienteLOV = getCatCuentaClienteLOV();
        CatFondoContableVOImpl catFondoContableLOV = getCatFondoContableVO();
        CatProductoFlexcubeLOVImpl catProductoLOV = getCatProductoFlexcubeLOV();
        CatPlazoFinanciamientoLOVImpl catPlazoFinanciamientoLOV = getCatPlazoFinanciamientoLOV();
        CatPeriodoGraciaLOVImpl catPeriodoGraciaLOV = getCatPeriodoGraciaLOV();
        CatDiasFeriadosVOImpl catDiasFeriadosLOV = getCatDiasFeriadosVO();
        //id contrato necesario para el servicio
        //22/12/2015 modificar URGENTE
        if(encabezadoResgitrarDatosLineaCreditoVORow.getIdContrato() != null)
        {
            contrato.setIdContrato(encabezadoResgitrarDatosLineaCreditoVORow.getIdContrato().intValue());
        }
        //id operacion necesaria para el servicio 
        if(idOperacion != null){
            long idOperacionLong = Long.parseLong(idOperacion );
            logger.log(ADFLogger.WARNING, "Valor idOperacion para Actualizar" +idOperacionLong);
            contrato.setIdOperacion(idOperacionLong);
        }
        //Datos del cliente
        if(catCuentaClienteLOV.getCurrentRow() != null)
        {
            Cuentas cuentas = new Cuentas();
            String cuentaCliente=(String)catCuentaClienteLOV.getCurrentRow().getAttribute("CuentaCliente");
            cuentas.getCuentaCliente().add(cuentaCliente);
            contrato.setCuentaCliente(cuentas);
        }
        //Datos de escrituración
        //Son solo de lectura el usuario no puede modificarlo
        
        
        LineaCredito lineaCredito = new LineaCredito();
        //Recuperar el id de la linea de credito, es requerido para actualizar la linea de credito seleccionada
        if(registrarDatosLineaCreditoVORow.getId() != null){
            logger.log(ADFLogger.WARNING, "Valor IdLineaCredito para Actualizar" +
                                          registrarDatosLineaCreditoVORow.getId());
            //valor requerido para  request del servicio ActualizarContratoLineaCreditoRequest
            lineaCredito.setIdLineaCredito(registrarDatosLineaCreditoVORow.getId());
        }
        //Información general
        //Número de línea
        if(registrarDatosLineaCreditoVORow.getNumeroLineaCredito() != null)
        {   
            String numeroLineaCredito = (String)registrarDatosLineaCreditoVORow.getNumeroLineaCredito();
            lineaCredito.setNumeroLineaCredito(numeroLineaCredito);
        }
        //Fondo contable
        if(catFondoContableLOV.getCurrentRow() != null){
            String fondoContable=(String)catFondoContableLOV.getCurrentRow().getAttribute("MisCode");
            lineaCredito.setFondo(fondoContable);
        }
        //¿Es revolvente?
        if(registrarDatosLineaCreditoVORow.getEsRevolvente() != null){
            if(registrarDatosLineaCreditoVORow.getEsRevolvente().intValue()== ES_REVOLVENTE_TRUE_INT){
                lineaCredito.setEsRevolvente(Boolean.TRUE);
            }else{
                lineaCredito.setEsRevolvente(Boolean.FALSE);
            }
        }
        
        logger.warning("moverEntreMeses : " + registrarDatosLineaCreditoVORow.getMoverEntreMeses());
        
        if(registrarDatosLineaCreditoVORow.getMoverEntreMeses() != null && registrarDatosLineaCreditoVORow.getMoverEntreMeses().toString() != ""){
            lineaCredito.setMoverEntreMeses(new BigDecimal (registrarDatosLineaCreditoVORow.getMoverEntreMeses()));
        }
        
        //¿Aplican recursos ordinarios?
        if(registrarDatosLineaCreditoVORow.getEsRecursosExternos() != null){
            Boolean esRecursosExternos;
            if(registrarDatosLineaCreditoVORow.getEsRecursosExternos().intValue() == ES_RECURSOS_ORDINARIOS_FALSE){
                esRecursosExternos = false;
                logger.log(ADFLogger.WARNING, "setRecursosExternos: " + esRecursosExternos);
                lineaCredito.setRecursosExternos(esRecursosExternos);
            }else{
                esRecursosExternos = true; // TODO NO está actualizando en servicio este valor (By FCP 23/dic/2015)
                logger.log(ADFLogger.WARNING, "setRecursosExternos: " + esRecursosExternos);
                lineaCredito.setRecursosExternos(esRecursosExternos);
            }
        }
        //Producto
        if(catProductoLOV.getCurrentRow() != null){
            Flexcube flexcube = new Flexcube();
            String idProductoFlexcube = (String)catProductoLOV.getCurrentRow().getAttribute("Id");
            flexcube.setIdProductoFlexcube(idProductoFlexcube);
            lineaCredito.setFlexcube(flexcube);
        }
        //Condiciones financieras
        //Fecha de apertura
        if(registrarDatosLineaCreditoVORow.getFechaValor() != null){
            GregorianCalendar cFechaValor = new GregorianCalendar();
            cFechaValor.setTimeInMillis(registrarDatosLineaCreditoVORow.getFechaValor().getTime());
            try{
                //Actualiza en fechaValor lineaCredito
                lineaCredito.setFechaValor(DatatypeFactory.newInstance().newXMLGregorianCalendar(cFechaValor));
                //Actualiza el valor de la fecha de vigencia 
                contrato.setFechaVigencia(DatatypeFactory.newInstance().newXMLGregorianCalendar(cFechaValor));
            }catch(Exception ex)
            {
                //remove, only test
                logger.log(ADFLogger.WARNING,"Exception in if(registrarDatosLineaCreditoVORow.getFechaValor() != null){: "
                                              +ex.getMessage());
                logger.log(ADFLogger.ERROR,ex.getClass() + ":" + ex.getMessage());
            }    
        }
        //Plazo de financiamiento
        if(registrarDatosLineaCreditoVORow.getPlazoFinanciamiento() != null){
            lineaCredito.setPlazoFinanciamiento(registrarDatosLineaCreditoVORow.getPlazoFinanciamiento().intValue());
        }
        //Tomar Id de CatPlazoFinanciamientoLOV del row seleccionado en la vista
        if(catPlazoFinanciamientoLOV.getCurrentRow() != null){
            Integer idPlazoFinanciamiento=(Integer)catPlazoFinanciamientoLOV.getCurrentRow().getAttribute("Id");
            lineaCredito.setFrecuenciaFinanciamiento(idPlazoFinanciamiento.intValue());
        }
        //cierra Plazo de financiamiento
        //Periodo de gracia
        if(registrarDatosLineaCreditoVORow.getPlazoGracia() != null){
            lineaCredito.setPlazoGracia(registrarDatosLineaCreditoVORow.getPlazoGracia().intValue());
        }
        //Tomar Id de catPeriodoGraciaLOV del row seleccionado en la vista
        if(catPeriodoGraciaLOV.getCurrentRow() != null){
            Integer idPeriodoGracia=(Integer)catPeriodoGraciaLOV.getCurrentRow().getAttribute("Id");
            lineaCredito.setFrecuenciaGracia(idPeriodoGracia.intValue());
        }
        //cierra Periodo de gracia
        //Fecha de vencimiento
        //Calculo de fecha de vencimiento
        //Validar que los valores de entrada del metodo no sean null
        if(registrarDatosLineaCreditoVORow.getFechaValor() != null && 
                registrarDatosLineaCreditoVORow.getPlazoFinanciamiento() != null &&
                    catPlazoFinanciamientoLOV.getCurrentRow() != null &&
                            registrarDatosLineaCreditoVORow.getPlazoGracia() != null &&
                                    catPeriodoGraciaLOV.getCurrentRow() != null){
            try{
                //Recupera el id de catPlazoFinanciamientoLOV
                Integer idPlazoFinanciamiento=(Integer)catPlazoFinanciamientoLOV.getCurrentRow().getAttribute("Id");
                //Recupera el id de catPeriodoGraciaLOV
                Integer idPeriodoGracia=(Integer)catPeriodoGraciaLOV.getCurrentRow().getAttribute("Id");
                //Timestamp to XMLGregorianCalendar
                GregorianCalendar cFechaValor = new GregorianCalendar();
                cFechaValor.setTimeInMillis(registrarDatosLineaCreditoVORow.getFechaValor().getTime());
                java.sql.Timestamp fechaVencimiento=calcularFechaDeVencimiento((DatatypeFactory.newInstance().
                                    newXMLGregorianCalendar(cFechaValor)),
                                    registrarDatosLineaCreditoVORow.getPlazoFinanciamiento().intValue(),
                                    idPlazoFinanciamiento.intValue(),
                                    registrarDatosLineaCreditoVORow.getPlazoGracia().intValue(),
                                    idPeriodoGracia.intValue());
                //Timestamp to XMLGregorianCalendar
                GregorianCalendar cFechaVencimiento = new GregorianCalendar();
                cFechaVencimiento.setTimeInMillis(fechaVencimiento.getTime());
                lineaCredito.setFechaVencimiento((DatatypeFactory.newInstance().
                                                 newXMLGregorianCalendar(cFechaVencimiento)));
            }catch(Exception ex){
                logger.log(ADFLogger.WARNING,"Exception in Calculo de fecha de vencimiento: " + ex.getMessage());
            }
        }
    //        if(registrarDatosLineaCreditoVORow.getFechaVencimiento() != null){
    //            GregorianCalendar cFechaVencimiento = new GregorianCalendar();
    //            cFechaVencimiento.setTimeInMillis(registrarDatosLineaCreditoVORow.getFechaVencimiento().getTime());
    //            try{
    //            lineaCredito.setFechaVencimiento(DatatypeFactory.newInstance().newXMLGregorianCalendar(cFechaVencimiento));
    //            }catch(Exception ex){
    //                //remove, only test
    //                logger.log(ADFLogger.WARNING,"Exception in if(registrarDatosLineaCreditoVORow.getFechaVencimiento() != null){: "
    //                                                              +ex.getMessage());
    //                logger.log(ADFLogger.ERROR,ex.getClass() + ":" + ex.getMessage());
    //            }
    //        }
        //Tratamiento días feriados
        if(catDiasFeriadosLOV.getCurrentRow() != null){
            String codigoExterno = (String)catDiasFeriadosLOV.getCurrentRow().getAttribute("CodigoExterno");
            lineaCredito.setTratamientoDiasFeriados(codigoExterno);
        }
        //Monto de la línea
        if(registrarDatosLineaCreditoVORow.getMontoLinea() != null){
            lineaCredito.setMontoLinea(registrarDatosLineaCreditoVORow.getMontoLinea());
        }
        //Restricciones
        //Tasa mínima
        ObjectFactory fact = new ObjectFactory();   
        JAXBElement<Double> dbValue = null;
        JAXBElement<Double> val = null;
        
        if(registrarDatosLineaCreditoVORow.getTasaMinima() != null && registrarDatosLineaCreditoVORow.getTasaMinima() > 0){
            dbValue = 
                fact.createLineaCreditoBasicTypeTasaMinima(new Double(registrarDatosLineaCreditoVORow.getTasaMinima().doubleValue()));
            lineaCredito.setTasaMinima(dbValue);
        }else{
            logger.log(ADFLogger.WARNING,"Tasa minima is null");
            //val = fact.createLineaCreditoBasicTypeTasaMinima(Double.NaN);
            //lineaCredito.setTasaMinima(val);
        }
        //Tasa máxima
        if(registrarDatosLineaCreditoVORow.getTasaMaxima() != null && registrarDatosLineaCreditoVORow.getTasaMaxima() > 0){
            dbValue = 
                fact.createLineaCreditoBasicTypeTasaMaxima(new Double(registrarDatosLineaCreditoVORow.getTasaMaxima().doubleValue()));
            lineaCredito.setTasaMaxima(dbValue);
        }else{
            logger.log(ADFLogger.WARNING,"Tasa maxima is null");
            //val = fact.createLineaCreditoBasicTypeTasaMaxima(Double.NaN);
            //lineaCredito.setTasaMaxima(val);
        }
        //Monto mínimo a desembolsar por desembolso
        if(registrarDatosLineaCreditoVORow.getMontoMinimo() != null && registrarDatosLineaCreditoVORow.getMontoMinimo() > 0){
            dbValue = 
                fact.createLineaCreditoBasicTypeMontoMinimo(new Double(registrarDatosLineaCreditoVORow.getMontoMinimo().doubleValue()));
            lineaCredito.setMontoMinimo(dbValue);
        }else{
            logger.log(ADFLogger.WARNING,"Monto minimo is null");
            //val = fact.createLineaCreditoBasicTypeMontoMinimo(Double.NaN);
            //lineaCredito.setMontoMinimo(val);
        }
        //Monto máximo a desembolsar por desembolso
        if(registrarDatosLineaCreditoVORow.getMontoMaximo() != null && registrarDatosLineaCreditoVORow.getMontoMaximo() > 0){
            dbValue = 
                fact.createLineaCreditoBasicTypeMontoMaximo(new Double(registrarDatosLineaCreditoVORow.getMontoMaximo().doubleValue()));
            lineaCredito.setMontoMaximo(dbValue);
        }else{
            //logger.log(ADFLogger.WARNING,"Monto maximo is null");
            //val = fact.createLineaCreditoBasicTypeMontoMaximo(Double.NaN);
            lineaCredito.setMontoMaximo(val);
        }
        //Condiciones especiales
        //¿Condición financiera especial?
        if(registrarDatosLineaCreditoVORow.getCondicionEspecial() != null){
            Boolean condicionEspecial;
            if(registrarDatosLineaCreditoVORow.getCondicionEspecial() == ES_FINANCIERA_ESPECIAL_TRUE){
                //getCondicionEspecial() == 1 --> true
                condicionEspecial = true;
            }else{
                //getCondicionEspecial() == 0 --> false
                condicionEspecial = false;
            }
            //Asignamos el valor del boolean a setCondicionEspecial
            lineaCredito.setCondicionEspecial(condicionEspecial);
        }
        //Descripción de la condición financiera
        if(registrarDatosLineaCreditoVORow.getDescripcionCondEspecial() != null){
            lineaCredito.setDescCondEspecial(registrarDatosLineaCreditoVORow.getDescripcionCondEspecial());
        }else{
            logger.warning("La descripción de la condicion financiera en nula, se setea cadena vacia");
            lineaCredito.setDescCondEspecial("");
        }
        
        //Vencimiento de plazos
        getVencimientoPlazoVO().setRangeSize(-1);
        Row[] vencimientoPlazoRows = getVencimientoPlazoVO().getAllRowsInRange();
        for (Row row : vencimientoPlazoRows) {
            org.bcie.terminobo.Termino termino = new org.bcie.terminobo.Termino();
            //IdVencimiento
            if(row.getAttribute("IdVencimiento") != null){
                Long idLong=(Long)row.getAttribute("IdVencimiento");
                termino.setIdTermino(idLong.longValue());
            }
            //IdTipoDeVencimiento
            if(row.getAttribute("IdTipoDeVencimiento") != null){
                org.bcie.terminobo.CatalogoTermino catalogoTermino = new org.bcie.terminobo.CatalogoTermino();
                Long idTipoVencimiento = (Long)row.getAttribute("IdTipoDeVencimiento");
                catalogoTermino.setIdCatTermino(idTipoVencimiento);
                termino.setTipoTermino(catalogoTermino);
            }
            //IdTipoDeFecha
            if(row.getAttribute("IdTipoDeFecha") != null){
                org.bcie.catalogobo.Catalogo catalogo= new org.bcie.catalogobo.Catalogo();
                Long idTipoDeFecha =(Long)row.getAttribute("IdTipoDeFecha");
                catalogo.setId(idTipoDeFecha);
                termino.setTipoFechaInicio(catalogo);
            }
            //FechaInicio
            if(row.getAttribute("FechaInicio") != null){
                try{
                    GregorianCalendar fechaInicioCalendar = new GregorianCalendar();
                    java.sql.Timestamp fechaInicioTimeStamp = (java.sql.Timestamp) row.getAttribute("FechaInicio");
                    fechaInicioCalendar.setTimeInMillis(fechaInicioTimeStamp.getTime());
                    termino.setFechaInicio(DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaInicioCalendar));
                }catch(Exception e){ 
                    logger.log(ADFLogger.WARNING,"Exception in mappingActualizarLineaCredito " +
                        "in row.getAttribute(\"FechaInicio\"): " +e.getMessage());}
            }
            //Plazo
            if(row.getAttribute("Plazo") != null){
                String plazoString = (String) row.getAttribute("Plazo");
                termino.setPlazo(Integer.valueOf(plazoString).intValue());
            }
            //IdPlazo
            if(row.getAttribute("IdPlazo") != null){
                org.bcie.catalogobo.Catalogo catalogo = new org.bcie.catalogobo.Catalogo();
                Long idPlazo = (Long) row.getAttribute("IdPlazo");
                catalogo.setId(idPlazo);
                termino.setFrecuenciaPlazo(catalogo);
            }
            //FechaVencimiento
            if(row.getAttribute("FechaVencimiento") != null){
                try{
                    GregorianCalendar fechaVencimientoCalendar = new GregorianCalendar();
                    java.sql.Timestamp fechaVencimientoTimeStamp = (java.sql.Timestamp) row.getAttribute("FechaVencimiento");
                    fechaVencimientoCalendar.setTimeInMillis(fechaVencimientoTimeStamp.getTime());
                    termino.setFechaVencimiento(DatatypeFactory.newInstance().newXMLGregorianCalendar(fechaVencimientoCalendar));
                }catch(Exception e){ 
                        logger.log(ADFLogger.WARNING,"Exception in mappingActualizarLineaCredito " +
                            "in row.getAttribute(\"FechaVencimiento\"): " +e.getMessage());}
            }
            //Estado
            if(row.getAttribute("Estado") != null){
                Boolean estado=(Boolean)row.getAttribute("Estado");
                termino.setEstado(estado);
            }
            //Agrega Termino
            lineaCredito.getTermino().add(termino);
        }
        
        //Comisiones
        getComisionVO().setRangeSize(-1);
        Row[] comisionRows = getComisionVO().getAllRowsInRange();
        for (Row row : comisionRows) {
            org.bcie.comisionbo.Comision comision = new org.bcie.comisionbo.Comision();
            //idComision si existe se actualiza, si no existe se crea
            if(row.getAttribute("IdComision") != null){
                Long idLong = (Long)row.getAttribute("IdComision");
                comision.setIdComision(idLong.longValue());
            }
            //idTipoComision
            if(row.getAttribute("IdTipoComision") != null){
                org.bcie.comisionbo.CatalogoComision catalogo = new org.bcie.comisionbo.CatalogoComision();
                
                logger.log(ADFLogger.WARNING, "REVM idTipoComision: "+row.getAttribute("IdTipoComision"));
                Long idTipoComision = (Long)row.getAttribute("IdTipoComision");
                catalogo.setIdCatComision(idTipoComision);
                comision.setTipoComision(catalogo);
            }
            //idFrecuencia
            if(row.getAttribute("IdFrecuencia") != null){
                org.bcie.catalogobo.Catalogo catalogo = new org.bcie.catalogobo.Catalogo();
                Long idTipoFrecuencia = (Long) row.getAttribute("IdFrecuencia");
                catalogo.setId(idTipoFrecuencia);
                comision.setTipoFrecuencia(catalogo);
            }
            //frecuencia
            if(row.getAttribute("Frecuencia") != null){
                String frecuenciaString = (String)  row.getAttribute("Frecuencia");
                comision.setFrecuenciaPago(Integer.valueOf(frecuenciaString));
            }
            //IdBaseCalculo
            if(row.getAttribute("IdBaseCalculo") != null){
                org.bcie.catalogobo.Catalogo catalogo = new org.bcie.catalogobo.Catalogo();
                Long idBaseCalculo = (Long) row.getAttribute("IdBaseCalculo");
                catalogo.setId(idBaseCalculo);
                comision.setBaseCalculo(catalogo);
            }
            //Porcentaje
            if(row.getAttribute("Porcentaje") != null){
                MontoBase montoBase = new MontoBase();
                String porcentajeString = (String)row.getAttribute("Porcentaje"); 
                java.math.BigDecimal porcentaje= new java.math.BigDecimal(porcentajeString.replaceAll("%", ""));
                montoBase.setPorcentajeMontoBase(porcentaje);
                comision.setMontoBase(montoBase);
            }
            //Estado
            if(row.getAttribute("Estado") != null){
                Boolean estado = (Boolean)row.getAttribute("Estado");
                comision.setEstado(estado);
            }
            
            //Mapear Nombre
            if(row.getAttribute("TipoComision") != null){
                comision.setDescripcion((String)row.getAttribute("TipoComision"));
            }
            //Mapear Codigo
            if(row.getAttribute("CodigoComision") != null){
              comision.setNombre((String)row.getAttribute("CodigoComision"));
            }
          
            //Agrega comision
            lineaCredito.getComision().add(comision);
            }
        
        // Se agrega bandera MontoEstado por FNXII-6667
        // QA solicito no se modificara la bandera para PCT
        /*if (esMontoLineaCreditoModificado(registrarDatosLineaCreditoVORow)) {
            lineaCredito.setEstadoMonto(Boolean.TRUE);
        }*/
        
       //Agregar el objeto de lineaCredito al objeto de contrato que se retorna del metodo y se envia al request
        contrato.getLineaCredito().add(lineaCredito);
        
        
        return contrato;
    }
    
    public java.sql.Timestamp calcularFechaDeVencimiento(XMLGregorianCalendar fechaApertura,int plazoFinanciamiento,
                                           int frecuenciaFinanciamiento,int plazoGracia,int frecuenciaGracia){
        GregorianCalendar fechaVencimientoCalendar = fechaApertura.toGregorianCalendar();
       
        //Sumar el valor de plazoFinanciamiento, depende de  frecuenciaFinanciamiento
        //frecuenciaFinanciamiento == 1 --> suman dias
        //frecuenciaFinanciamiento == 2 --> suman meses
        //frecuenciaFinanciamiento == 3 --> suman años
        //Validar que contenga un valor y un id mayor a 0
        if(plazoFinanciamiento > 0 && frecuenciaFinanciamiento >0){
            //frecuenciaFinanciamiento == 1 --> suman dias
            if(frecuenciaFinanciamiento == TIPO_PLAZO_DIA){
                fechaVencimientoCalendar.add(GregorianCalendar.DAY_OF_YEAR,plazoFinanciamiento);
            }
            //frecuenciaFinanciamiento == 2 --> suman meses
            if(frecuenciaFinanciamiento == TIPO_PLAZO_MES){
                fechaVencimientoCalendar.add(GregorianCalendar.MONTH,plazoFinanciamiento);
            }
            //frecuenciaFinanciamiento == 3 --> suman años
            if(frecuenciaFinanciamiento == TIPO_PLAZO_ANIO){
                fechaVencimientoCalendar.add(GregorianCalendar.YEAR,plazoFinanciamiento);
            }
        }

        // Se comenta la suma del periodo de gracia por la solicitud de cambio relacionada al Kambanize 2764
        //Validar que contenga un valor y un id mayor a 0
        /*if(plazoGracia >0 && frecuenciaGracia >0){
            //frecuenciaFinanciamiento == 1 --> suman dias
            if(frecuenciaGracia == TIPO_PLAZO_DIA){
                fechaVencimientoCalendar.add(GregorianCalendar.DAY_OF_YEAR,plazoGracia);
            }
            //frecuenciaFinanciamiento == 2 --> suman meses
            if(frecuenciaGracia == TIPO_PLAZO_MES){
                fechaVencimientoCalendar.add(GregorianCalendar.MONTH,plazoGracia);
            }
            //frecuenciaFinanciamiento == 3 --> suman años
            if(frecuenciaGracia == TIPO_PLAZO_ANIO){
                fechaVencimientoCalendar.add(GregorianCalendar.YEAR,plazoGracia);
            }
            
        }*/
        
        //Asignar el valor de fechaVencimiento calculado apoartir de fechaVencimientoCalendar
        java.sql.Timestamp fechaVencimiento = new java.sql.Timestamp(fechaVencimientoCalendar.getTimeInMillis());
        
        return fechaVencimiento;
    }

    public Integer obtenerContratoPorLote(Long idOperacion, String instanciaProceso){
        logger.log(ADFLogger.WARNING, "Entra en obtenerContratoPorLote.");
        Integer contratoLote = null;
        ContratoVORowImpl contratoRow = null;
        try {
            contratoRow =
                this.getContratoImplementacionVO().getContratoByOperacionInstanciaProceso(idOperacion,
                                                                                          instanciaProceso);
            if (null != contratoRow) {
                contratoLote = Integer.valueOf(contratoRow.getId().intValue());
                logger.log(ADFLogger.WARNING, "Valor del contrato casteado a entero." + contratoLote);
            } else {
                //Se crea nuevo contrato por lote en caso de que no exista.
                contratoLote = this.getContratoImplementacionVO().crearContratoPorLote(idOperacion, instanciaProceso);
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al ejecutar getContratoByOperacionInstanciaProceso" + e);
        }
        logger.log(ADFLogger.WARNING, "Valor de retorno del contrato :" + contratoLote);
        return contratoLote;
    }

    /**
     * Termina - IMPLEMENTACION_PCT PROCESO CORE - Registrar datos de linea de credito
     **/   
    public Map AplicarEnvioCobroService(Long idOperacion, Long idSolicitud){
        logger.warning("*Inf, inicia el metodo AplicarEnvioCobroService");
        logger.warning("*Inf, idOperacion: " + idOperacion);    
        logger.warning("*Inf, idSolicitud: " + idSolicitud);
        
        Boolean respuesta = Boolean.FALSE;
        Map mapaDatos = new HashMap();
        mapaDatos.put("MSG_ERROR", null); 

        if(idOperacion == null){
            logger.warning("***Error, parametro requerido idOperacion");                    
            mapaDatos.put("respuesta", respuesta);
            mapaDatos.put("MSG_ERROR", "Error,  idOperacion GetPayload null"); 
            return mapaDatos;
        }
        
        if( idSolicitud == null){
            logger.warning("***Error, parametro requerido idSolicitud");                               
            mapaDatos.put("respuesta", respuesta);
            mapaDatos.put("MSG_ERROR", "Error,  idSolicitud GetPayload null"); 
            return mapaDatos;
        }
        
        String wsdl = getWsdl(IWsdlLocation.OPERACION);

        Operacion12BndQSService operacion12BndQSService = IWsdlLocation.Service.getInstance(Operacion12BndQSService.class, wsdl);
        Operacion12Port operacion12Port = operacion12BndQSService.getOperacion12BndQSPort();
        AplicarEnvioCobroRequestType request = new AplicarEnvioCobroRequestType();
        AplicarEnvioCobroResponseType response = null;
        request.setIdOperacion(idOperacion);
        request.setIdSolicitudDesembolso(idSolicitud);

        try {
            java.util.Date horaInicio =
                ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_APLICAR_ENVIO_COBRO);
            response = operacion12Port.aplicarEnvioCobro(request);
            ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_APLICAR_ENVIO_COBRO, horaInicio);
        } catch (Exception e) {
            logger.warning("***ERROR al ejecutar servicio para aplicar el envio al cobro -> ", e);
            JboException ex = new JboException(e);
            ex.addToExceptions(new Exception("Error al consumir el servicio: " + IWsdlLocation.OPERACION));
            throw ex;
        }

        if (response != null) {
            if (null != response.getResultado()) {
                if (response.getResultado().getResult() != null) {
                    if (response.getResultado().getResult().value() == "OK") {
                        respuesta = Boolean.TRUE;
                        logger.warning("*Inf, responsegetResultado().getResult().value() == OK");
                    } else {
                        logger.warning("*Inf,Important! el servicio devuelve : " +
                                       response.getResultado().getResult().value());
                        if (response.getResultado().getMessage() != null){
                            logger.warning("El servicio regres el mensaje de error : " + response.getResultado().getMessage());
                            mapaDatos.put("MSG_ERROR", response.getResultado().getMessage());
                        }else{
                            mapaDatos.put("MSG_ERROR", "El servicio regresa ERROR, devuelve mensaje nulo");
                            logger.warning("response.getResultado().getMessage() no contine un mensaje de error para mostrar en pantalla");
                        }
                    }
                } else {
                    logger.warning("***Error, response.getResultado().getResult() es resuelto a null");
                    mapaDatos.put("MSG_ERROR", "No hubo respuesta del servicion, el objeto getResult() es nulo");
                }
            } else {
                logger.warning("El servicio no esta regresando respuesta.");
                mapaDatos.put("MSG_ERROR", "El response del servicio aplicar envio al cobro es resuelto a null");
            }
        } else {
            logger.warning("***Error, El response del servicio aplicar envio al cobro es resuelto a null");
            mapaDatos.put("MSG_ERROR", "No se obtuvo respuesta del servicio");
        }

        mapaDatos.put("respuesta", respuesta);
        logger.warning("*Inf, termina el metodo AplicarEnvioCobroService");
        return mapaDatos;
    }
       
       
    public Map ObtenerFactibilidadService(Long idOperacion){
     logger.warning("*Inf, inicia el metodo ObtenerFactibilidadService");
     Boolean respuesta = Boolean.FALSE;
        Map mapaDatos = new HashMap();
        mapaDatos.put("MSG_ERROR", null);
        mapaDatos.put("MSG_ERROR_SIN_CONCATENAR", null);
       
     if(idOperacion == null){
          logger.warning("***Error, parametro requerido idOperacion");
          logger.warning("*Inf, idOperacion: "+idOperacion);   
            mapaDatos.put("respuesta", respuesta);
            mapaDatos.put("MSG_ERROR", "Error,  idOperacion GetPayload null");          
         return mapaDatos;
        }
     
     
        String wsdl = getWsdl(IWsdlLocation.OPERACION);

        Operacion12BndQSService operacion12BndQSService = IWsdlLocation.Service.getInstance(Operacion12BndQSService.class, wsdl);
        Operacion12Port operacion12Port = operacion12BndQSService.getOperacion12BndQSPort();
        ObtenerFactibilidadRequestType request = new ObtenerFactibilidadRequestType();
        ObtenerFactibilidadResponseType response = null;
        request.setIdOperacion(idOperacion);
       
        
        try {
            java.util.Date horaInicio =
                ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_OBTENER_FACTIBILIDAD);
            response = operacion12Port.obtenerFactibilidad(request);
            ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_OBTENER_FACTIBILIDAD, horaInicio);
        } catch (Exception e) {
            logger.warning("***ERROR al ejecutar servicio para Obtener Factibilidad -> ", e);
            JboException ex = new JboException(e);
            ex.addToExceptions(new Exception("Error al ejecutar servicio para Obtener Factibilidad :" + e.getClass() + "." + e.getMessage()));
            throw ex;
        }
        
        try {
            if (response != null) {
                if (null != response.getResultado()) {
                    if (response.getResultado().getResult() != null) {
                        if (response.getResultado().getResult().value() == "OK") {
                            respuesta = Boolean.TRUE;
                            logger.warning("*Inf, responsegetResultado().getResult().value() == OK");
                            logger.warning("Respuesta del servicio :" +
                                           response.getResultado().getMessage().toString());
                        } else {
                            if (response.getResultado().getError().getErrorCode() != null){
                                logger.warning("Valor obtenido de errorCode del servicio: " + response.getResultado().getError().getErrorCode());
                                String errorCode = response.getResultado().getError().getErrorCode().toString();
                                if(errorCode.length() > 0 && errorCode.toUpperCase().compareToIgnoreCase("OK") == 0){
                                    logger.warning("errorCode devuelve valor OK");
                                    if(null != response.getResultado().getMessage()){
                                        logger.warning("Respuesta del servicio :" +
                                                       response.getResultado().getMessage().toString());
                                        mapaDatos.put("MSG_ERROR_SIN_CONCATENAR", response.getResultado().getMessage().toString());
                                    }else{
                                        logger.warning("El resultado del response regresa ERROR, no regresa el mensaje de errorCode.");
                                        mapaDatos.put("MSG_ERROR", "El resultado del response regresa ERROR. No regresa el mensaje de error.");
                                    }
                                }
                                else{
                                    logger.warning("No se cumple la validacion de errorCode==OK, se muestra en pantalla el valor del mensaje e error obtenido en el response");
                                    logger.warning("*Inf,Important! el servicio devuelve : " +
                                                   response.getResultado().getResult().value());
                                    if(null != response.getResultado().getMessage()){
                                        logger.warning("Respuesta del servicio :" +
                                                       response.getResultado().getMessage().toString());
                                        mapaDatos.put("MSG_ERROR", "Servicio ObtenerFactibilidad devuelve error: " + response.getResultado().getMessage().toString());
                                    }else{
                                        logger.warning("El resultado del response regresa ERROR, no regresa el mensaje de error.");
                                        mapaDatos.put("MSG_ERROR", "El resultado del response regresa ERROR. No regresa el mensaje de error.");
                                    }
                                }
                            }
                            else{
                                logger.warning("Valor obtenido de errorCode es null");
                                logger.warning("*Inf,Important! el servicio devuelve : " +
                                               response.getResultado().getResult().value());
                                if(null != response.getResultado().getMessage()){
                                    logger.warning("Respuesta del servicio :" +
                                                   response.getResultado().getMessage().toString());
                                    mapaDatos.put("MSG_ERROR", "Servicio ObtenerFactibilidad devuelve error: " + response.getResultado().getMessage().toString());
                                }else{
                                    logger.warning("El resultado del response regresa ERROR, no regresa el mensaje de error.");
                                    mapaDatos.put("MSG_ERROR", "El resultado del response regresa ERROR. No regresa el mensaje de error.");
                                }
                            }
                            
                        }

                    } else {
                        String msg = "Error, response.getResultado().getResult() es resuelto a null.";
                        logger.warning("***Error, response.getResultado().getResult() es resuelto a null");
                        mapaDatos.put("MSG_ERROR", msg);
                    }
                } else {
                    String msg = "Error, No hay respuesta del servicio.";
                    logger.warning("No hay respuesta del servicio.");
                    mapaDatos.put("MSG_ERROR", msg);
                }
            } else {
                String msg = "Error, El response del servicio Obtener Factibilidad es resuelto a null.";
                logger.warning("***Error, El response del servicio Obtener Factibilidad es resuelto a null");
                mapaDatos.put("MSG_ERROR", msg);
            }

            mapaDatos.put("respuesta", respuesta);
        } catch (Exception e) {

        }
     logger.warning("*Inf, termina el metodo ObtenerFactibilidadService" + respuesta);   
     return mapaDatos;
    }
    
       public String getWsdl(String key) {
           ConfiguracionVOImpl vo = this.getConfiguracionVO();
           Row row = vo.getValor(key);
           String wsdl = row == null ? null : (String) row.getAttribute("Valor");
           return wsdl;
       }
    
       public Map buscarCondicionFinancieraPorIdContrato(Long idOperacion){
           logger.warning("Entra en validarCondicionesFinancieras.");
           Map resultado = new HashMap<String, Object>();
           List<Row> contratos = null;
           Long idContrato = null;
           Boolean esValidado = Boolean.TRUE;
           Row rowCondicionFinanciera = null;
           ContratosDesembolsoConInteresVOImpl contratosDesembolsoConInteresVOImpl = this.getContratosDesembolsoConInteresVO();
           CondicionesFinancierasVOImpl condicionesFinancierasVOImpl = this.getCondicionesFinancierasImplementacionVO();
           try{
               contratos = contratosDesembolsoConInteresVOImpl.obtnerContratoCreadoPorImplementacion(idOperacion); 
               if(null != contratos){
                   for(Row rowContrato : contratos){
                       logger.warning("Numero de contratos encontrados :" + contratos.size());
                       resultado = new HashMap<String, Object>();
                       logger.warning("Tamaño del mapa :" + resultado.size());
                       idContrato = (Long)rowContrato.getAttribute("Id");
                       logger.warning("Buscando condicion financiera para contrato :" +idContrato) ;
                       rowCondicionFinanciera = condicionesFinancierasVOImpl.buscarCondicionFinancieraPorIdContrato(idContrato);
                       resultado = validarCamposCondicionFinanciera(rowCondicionFinanciera, idContrato);
                       esValidado = (Boolean)resultado.get("esValidado");
                       logger.warning("Tamaño del mapa :" + resultado.size() + "Es validado :" + esValidado);
                       if(resultado.size() > 0 && !esValidado){
                           logger.warning("Salir del for con el contrato." + idContrato);
                           break;
                       }
                   }
               }else{
                   logger.warning("No se encontro algun contrato creado por implementacion.");
               }
           }catch(Exception e){
               logger.warning("Error al obtener los contratos creados por implementacion.", e);
           }
           logger.warning("Termina metodo Validar campos de condiciones financieras." + (Boolean)resultado.get("esValidado"));
           return resultado;
       }
       
       private Map validarCamposCondicionFinanciera(Row rowCondicionFinanciera, Long idContrato){
           logger.warning("Entra en validarCamposCondicionFinanciera para el contrato :"+idContrato);
           Map resultado = new HashMap<String, Object>();
           String mensaje = null;
           String mje = null;
           String replace = null;
           Boolean esValidado = Boolean.TRUE;
           Integer idTcaTipoCalendario = null;
           Integer idEspecificacionFecha = null;
           
           try{
               ResourceBundle rb = BundleFactory.getBundle("org.bcie.fenix.common.model.FenixModelBundle");
               mje = rb.getString("MSG_VALORES_NULOS");;
               mensaje = mje.replace("{0}", idContrato.toString());
               if(null != rowCondicionFinanciera){
                   if(null != rowCondicionFinanciera.getAttribute("IdTcaTipoCalendario")){
                       idTcaTipoCalendario = (Integer)rowCondicionFinanciera.getAttribute("IdTcaTipoCalendario");
                       if (idTcaTipoCalendario.compareTo(1) == 0) {
                            logger.warning("Valida campos de tipo de calendario complejo.");
                            resultado = validaDatosGeneralesCondicionFinanciera(rowCondicionFinanciera, mensaje);
                            esValidado = (Boolean) resultado.get("esValidado");
                       } else {
                            logger.warning("Valida campos de tipo de calendario simple.");
                            resultado = validaDatosGeneralesCondicionFinanciera(rowCondicionFinanciera, mensaje);
                            esValidado = (Boolean) resultado.get("esValidado");
                            if (null == rowCondicionFinanciera.getAttribute("IdTcaFrecuenciaPagoCapital")) {
                                logger.warning(mensaje + " Frecuencia de capital.");
                                esValidado = Boolean.FALSE;
                                resultado.put("mjeFrecuenciaPagoCapital", mensaje + " Frecuencia de capital.");
                            }
                            if(null != rowCondicionFinanciera.getAttribute("IdTcaEspecificacionFecha")){
                                idEspecificacionFecha = (Integer)rowCondicionFinanciera.getAttribute("IdTcaEspecificacionFecha");
                                logger.warning("Valor de IdTcaEspecificacionFecha :" + idEspecificacionFecha);
                                if(idEspecificacionFecha.compareTo(1) == 0){
                                        if (null == rowCondicionFinanciera.getAttribute("FechaPrimerPagoCapital")) {
                                            logger.warning(mensaje + " Fecha de primer pago de capital.");
                                            esValidado = Boolean.FALSE;
                                            resultado.put("mjeFechaPagoCapital", mensaje + " Fecha de primer pago de capital.");
                                        }
                                        if (null == rowCondicionFinanciera.getAttribute("FechaVencimiento")) {
                                            logger.warning(mensaje + " Fecha de vencimiento.");
                                            esValidado = Boolean.FALSE;
                                            resultado.put("mjeFechaVencimiento", mensaje + " Fecha de vencimiento.");
                                        }
                                        if (null == rowCondicionFinanciera.getAttribute("FechaProximoPagoInteres")) {
                                            logger.warning(mensaje + " Fecha de proximo pago de interes.");
                                            esValidado = Boolean.FALSE;
                                            resultado.put("mjeFechaPagoInteres", mensaje + " Fecha de proximo pago de interes.");
                                        }
                                }else{
                                    logger.warning("La especificacion no es por fecha, no se validan los campos.");
                                }
                            }
                            if (null == rowCondicionFinanciera.getAttribute("FrecuenciaPagoInteres")) {
                                logger.warning(mensaje + " Frecuencia de pago de interes.");
                                esValidado = Boolean.FALSE;
                                resultado.put("mjeFrecuenciaPagoInteres", mensaje + " Frecuencia de pago de interes.");
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
       
       private Map validaDatosGeneralesCondicionFinanciera(Row rowCondicionFinanciera, String mensaje){
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
                       
                        if(null == rowCondicionFinanciera.getAttribute("ValorTasa")){
                            logger.warning(mensaje + " Valor Tasa.");
                            esValidado = Boolean.FALSE;
                            resultado.put("mjeSpreadTasa", mensaje + " Valor Tasa.");
                        }else{
                            logger.warning("ok campo ValorTasa validados ");
                        }
                        
                    break;
                    case 3:
                        logger.warning("Evaluando los campos para un tipo de tasa variable");
                      
                            if(null == rowCondicionFinanciera.getAttribute("SpreadTasa")){
                                logger.warning(mensaje + " Spread.");
                                esValidado = Boolean.FALSE;
                                resultado.put("mjeSpreadTasa", mensaje + " Spread.");
                            }else{
                                logger.warning("ok campo SpreadTasa validado");
                            }
                        
                            if (null == rowCondicionFinanciera.getAttribute("FrecuenciaRevisionTasa") ||
                             null == rowCondicionFinanciera.getAttribute("IdTcaFrecuenciaRevTasa")) {
                                 logger.warning(mensaje + " Frecuencia de revision de tasa.");
                                 esValidado = Boolean.FALSE;
                                 resultado.put("mjeFrecuenciaRevTasa", mensaje + " Frecuencia de revision de tasa.");
                            }else{
                                logger.warning("ok campo FrecuenciaRevisionTasa validado");
                            }
                        
                            if(null == rowCondicionFinanciera.getAttribute("CodigoTasaReferencia")){
                                logger.warning(mensaje + " Codigo de tasa de referncia.");
                                esValidado = Boolean.FALSE;
                                resultado.put("mjeDescripcionTasa2", mensaje + " Codigo de tasa de referencia.");
                            }else{
                                logger.warning("ok campo CodigoTasaReferencia validado");
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
                   if(idEspecificacionFecha.compareTo(1) == 0){
                       if(null == rowCondicionFinanciera.getAttribute("FechaProximaRevisionTasa")){
                           logger.warning(mensaje + " Fecha de revision de tasa.");
                           esValidado = Boolean.FALSE;
                           resultado.put("mjeFechaRevTasa", mensaje + " Fecha de revision de tasa.");
                       }
                   }else{
                       logger.warning("La especificacion no es por fecha, no se valida el campo.");
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
               
               if (null == rowCondicionFinanciera.getAttribute("SpreadMora")) {
                    logger.warning(mensaje + " Valor del Spread.");
                    esValidado = Boolean.FALSE;
                    resultado.put("mjeSpreadMora", mensaje + " Valor del Spread.");
                }
               validaProducto = validaspreadVariable(idProducto);
               if(validaProducto){
                   if (null == rowCondicionFinanciera.getAttribute("FechaProximaRevisionSpread")) {
                        logger.warning(mensaje + " Fecha proxima de revision spread.");
                        esValidado = Boolean.FALSE;
                        resultado.put("mjeFechaProximaRevisionSpread", mensaje + " Fecha proxima de revision spread.");
                    }
                   if (null == rowCondicionFinanciera.getAttribute("FrecuenciaRevisionSpread")) {
                        logger.warning(mensaje + " Valor del Spread.");
                        esValidado = Boolean.FALSE;
                        resultado.put("mjeFrecuenciaRevisionSpread", mensaje + " Frecuencia de revision spread.");
                    }
               }else{
                   logger.warning("El valor es false, no se realiza validacion de campos : FechaProximaRevisionSpread, FrecuenciaRevisionSpread.");
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
                getVtaProdFlexComponenteDesemImplementacionVO().consultarSpreadVariableById(idProductolex);
           }catch(Exception e){
               logger.warning("Error en validaspreadVariable", e);
           }
           logger.warning("Valor de retorno :" + isValido);
           return isValido;
       }
       
    private boolean esLineaCreditoAsociada(RegistrarDatosLineaCreditoVORowImpl lineaCreditoRow) {
        boolean esAsociada = Boolean.FALSE;
        RowSetIterator rowSetIterator = getDatosLineaCreditoVO().createRowSetIterator(null);
        rowSetIterator.reset();

        while (rowSetIterator.hasNext()) {
            DatosLineaCreditoVORowImpl datosLineaCreditoRow = (DatosLineaCreditoVORowImpl) rowSetIterator.next();

            if (null != datosLineaCreditoRow && null != lineaCreditoRow &&
                datosLineaCreditoRow.getId().compareTo(lineaCreditoRow.getId()) == 0 && datosLineaCreditoRow.getLineaGlobalIfi()) {
                esAsociada = Boolean.TRUE;
                break;
            }
        }

        rowSetIterator.closeRowSetIterator();

        return esAsociada;
    }
    
    private Long obtenerIdOperacionLineaCreditoAsociada(RegistrarDatosLineaCreditoVORowImpl lineaCreditoRow) {
        Long idOperacion = null;
        RowSetIterator rowSetIterator = getDatosLineaCreditoVO().createRowSetIterator(null);
        rowSetIterator.reset();

        while (rowSetIterator.hasNext()) {
            DatosLineaCreditoVORowImpl datosLineaCreditoRow = (DatosLineaCreditoVORowImpl) rowSetIterator.next();

            if (null != datosLineaCreditoRow && null != lineaCreditoRow &&
                datosLineaCreditoRow.getId().compareTo(lineaCreditoRow.getId()) == 0) {
                idOperacion = datosLineaCreditoRow.getIdOperacion();
                break;
            }
        }

        rowSetIterator.closeRowSetIterator();

        return idOperacion;
    }
    
    private Integer obtenerIdContratoLineaCreditoAsociada(RegistrarDatosLineaCreditoVORowImpl lineaCreditoRow) {
        Integer idContrato = null;
        RowSetIterator rowSetIterator = getDatosLineaCreditoVO().createRowSetIterator(null);
        rowSetIterator.reset();

        while (rowSetIterator.hasNext()) {
            DatosLineaCreditoVORowImpl datosLineaCreditoRow = (DatosLineaCreditoVORowImpl) rowSetIterator.next();

            if (null != datosLineaCreditoRow && null != lineaCreditoRow &&
                datosLineaCreditoRow.getId().compareTo(lineaCreditoRow.getId()) == 0) {
                idContrato = datosLineaCreditoRow.getIdContrato();
                break;
            }
        }

        rowSetIterator.closeRowSetIterator();

        return idContrato;
    }
    
    private boolean esMontoLineaCreditoModificado(RegistrarDatosLineaCreditoVORowImpl lineaCreditoRow) {
        boolean esAsociada = Boolean.FALSE;
        RowSetIterator rowSetIterator = getDatosLineaCreditoVO().createRowSetIterator(null);
        rowSetIterator.reset();

        while (rowSetIterator.hasNext()) {
            DatosLineaCreditoVORowImpl datosLineaCreditoRow = (DatosLineaCreditoVORowImpl) rowSetIterator.next();

            if (null != datosLineaCreditoRow && null != lineaCreditoRow &&
                datosLineaCreditoRow.getId().compareTo(lineaCreditoRow.getId()) == 0 &&
                datosLineaCreditoRow.getMontoLinea().compareTo(lineaCreditoRow.getMontoLinea()) != 0
                /* Se solicito aplique para ambas lineas */
                /*&& datosLineaCreditoRow.getLineaGlobalIfi()*/) {
                esAsociada = Boolean.TRUE;
                break;
            }
        }

        rowSetIterator.closeRowSetIterator();

        return esAsociada;
    }
    
    public boolean propagarLineaCredito(String loginUsuario) {
        logger.warning("Inicia propagarLineaCredito");
        logger.warning("Login Usuario: " + loginUsuario);
        
        boolean resultado = Boolean.FALSE;
        ViewObjectImpl datosLineaCreditoVO = getDatosLineaCreditoVO();
        Integer errores = 0;
        Row[] listaLineasCredito = datosLineaCreditoVO.getAllRowsInRange();
        Long idLineaCredito = 0L;

        if (listaLineasCredito != null && listaLineasCredito.length > 0) {
            logger.warning("Se encontraron " + listaLineasCredito.length + " lineas para propagar");
        } else {
            logger.warning("Error no se obtuvieron lineas para propagar");
            throw new JboException("Error. No se obtuvieron l\u00EDneas de cr\u00E9dito que propagar");
        }

        String idFlexcube;
        try {
            for (Row lineaCredito : listaLineasCredito) {
                idFlexcube = "";
                idLineaCredito = (Long) lineaCredito.getAttribute("Id");

                logger.warning("Se lee la linea: " + idLineaCredito + " para validar si se debe propagar");

                if (null != lineaCredito.getAttribute("IdFlexcube")) {
                    logger.warning("Se lee IdFlexcube para la linea: " + idLineaCredito);
                    idFlexcube = (String) lineaCredito.getAttribute("IdFlexcube");
                }

                if (errores == 0 && idFlexcube.length() <= 0) {
                    logger.warning("Se invoca metodo para consumir servicio de propagar linea");
                    boolean propagacionLineaExitosa = consumirPropagarLineaCredito(idLineaCredito);

                    if (propagacionLineaExitosa) {
                        logger.warning("Se propaga la Linea de credito: " + idLineaCredito);
                    } else {
                        errores = 1;
                        logger.warning("Error al propagara Linea: " + idLineaCredito);
                    }
                }
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al propagar la linea de credito ", e);
            throw new JboException("Error: " + e.getMessage());
        }

        if (errores == 0) {
            resultado = Boolean.TRUE;
        }

        logger.warning("Termina propagarLineaCredito. Return: " + resultado);
        return resultado;
    }
    
    public boolean consumirPropagarLineaCredito(Long idLinea) throws Exception {
        logger.warning("Entrando en consumirPropagarLineaCredito: ");
        
        boolean propagacionExitosa = Boolean.FALSE;
        try {
            String wsdl = getWsdl(IWsdlLocation.LINEA_CREDITO);

            com.bcie.xmlns.lineacreditoservice.LineaCredito lineaCredito =
                IWsdlLocation.Service.getInstance(com.bcie.xmlns.lineacreditoservice.LineaCredito.class, wsdl);
            LineaCreditoPT lineaCreditoPT = lineaCredito.getLineaCredito12Bnd();
            PropagarLineaCreditoRequestType request = new PropagarLineaCreditoRequestType();
            request.setIdLineaCredito(idLinea);

            Date horaInicio = ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_VALIDA_LINEA_CREDITO);
            PropagarLineaCreditoResponsetType propagarLineaCreditoResponseType =
                lineaCreditoPT.propagarLineaCredito(request);
            ModelUtils.logEndWS(logger, propagarLineaCreditoResponseType, FenixModelConstants.WSC_VALIDA_LINEA_CREDITO,
                                horaInicio);

            if (true == propagarLineaCreditoResponseType.getResultado().getResult().value().equals("ERROR")) {
                
                // Se construye mensaje de error
                StringBuilder sb = new StringBuilder();
                sb.append("La linea de credito: ").append(idLinea);
                
                if (null != propagarLineaCreditoResponseType.getResultado().getMessage() &&
                    propagarLineaCreditoResponseType.getResultado().getMessage().trim().length() > 0) {
                    sb.append(", Mensaje: ").append(propagarLineaCreditoResponseType.getResultado().getMessage());
                }
                
                if (null != propagarLineaCreditoResponseType.getResultado().getError().getErrorDescription() &&
                    propagarLineaCreditoResponseType.getResultado().getError().getErrorDescription().trim().length() > 0) {
                    sb.append(", Detalle: ").append(propagarLineaCreditoResponseType.getResultado().getError().getErrorDescription());
                }
                
                logger.warning(sb.toString());
                throw new Exception(sb.toString());
            } else {
                logger.warning("Propagacion exitosa");
                propagacionExitosa = Boolean.TRUE;
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al propagar la linea de credito ", e);
            throw new Exception(e.getMessage());
        }
        
        return propagacionExitosa;
    }
    
    /**
     * Container's getter for ImplementacionVO1.
     * @return ImplementacionVO1
     */
    public ImplementacionVOImpl getImplementacionVO() {
        return (ImplementacionVOImpl) findViewObject("ImplementacionVO");
    }

    /**
     * Container's getter for FormularioImplementacionPctVO1.
     * @return FormularioImplementacionPctVO1
     */
    public FormularioImplementacionPctVOImpl getFormularioImplementacionPctVO() {
        return (FormularioImplementacionPctVOImpl) findViewObject("FormularioImplementacionPctVO");
    }

    /**
     * Container's getter for ImplementacionParticipanteVO1.
     * @return ImplementacionParticipanteVO1
     */
    public ImplementacionParticipanteVOImpl getImplementacionParticipanteVO() {
        return (ImplementacionParticipanteVOImpl) findViewObject("ImplementacionParticipanteVO");
    }

    /**
     * Container's getter for ConcursanteImplementacionVO1.
     * @return ConcursanteImplementacionVO1
     */
    public ConcursanteImplementacionVOImpl getConcursanteImplementacionVO() {
        return (ConcursanteImplementacionVOImpl) findViewObject("ConcursanteImplementacionVO");
    }

    /**
     * Container's getter for ImplementacionAdjudicatarioVO1.
     * @return ImplementacionAdjudicatarioVO1
     */
    public ImplementacionAdjudicatarioVOImpl getImplementacionAdjudicatarioVO() {
        return (ImplementacionAdjudicatarioVOImpl) findViewObject("ImplementacionAdjudicatarioVO");
    }

    /**
     * Container's getter for LoteImplementacionVO1.
     * @return LoteImplementacionVO1
     */
    public LoteImplementacionVOImpl getLoteImplementacionVO() {
        return (LoteImplementacionVOImpl) findViewObject("LoteImplementacionVO");
    }

    /**
     * Container's getter for FormularioLoteImplementacionVO1.
     * @return FormularioLoteImplementacionVO1
     */
    public FormularioLoteImplementacionVOImpl getFormularioLoteImplementacionVO() {
        return (FormularioLoteImplementacionVOImpl) findViewObject("FormularioLoteImplementacionVO");
    }

    /**
     * Container's getter for CalcularInteresContratoByIdPrepagoSpVO1.
     * @return CalcularInteresContratoByIdPrepagoSpVO1
     */
    public CalcularInteresContratoByIdPrepagoSpVOImpl getCalcularInteresContratoByIdPrepagoSpVO() {
        return (CalcularInteresContratoByIdPrepagoSpVOImpl) findViewObject("CalcularInteresContratoByIdPrepagoSpVO");
    }


    /**
     * Container's getter for ObtenerDatosPrepagoByContratoFlexcube1.
     * @return ObtenerDatosPrepagoByContratoFlexcube1
     */
    public ObtenerDatosPrepagoByContratoFlexcubeImpl getObtenerDatosPrepagoByContratoFlexcube() {
        return (ObtenerDatosPrepagoByContratoFlexcubeImpl) findViewObject("ObtenerDatosPrepagoByContratoFlexcube");
    }


    /**
     * Container's getter for TcaTipoMonedaVO1.
     * @return TcaTipoMonedaVO1
     */
    public TcaTipoMonedaVOImpl getTcaTipoMonedaVO() {
        return (TcaTipoMonedaVOImpl) findViewObject("TcaTipoMonedaVO");
    }

    /**
     * Container's getter for FinalizacionEstudiosVO1.
     * @return FinalizacionEstudiosVO1
     */
    public FinalizacionEstudiosVOImpl getFinalizacionEstudiosVO() {
        return (FinalizacionEstudiosVOImpl) findViewObject("FinalizacionEstudiosVO");
    }


    /**
     * Container's getter for ContratosDesembolsoConInteresVO1.
     * @return ContratosDesembolsoConInteresVO1
     */
    public ContratosDesembolsoConInteresVOImpl getContratosDesembolsoConInteresVO() {
        return (ContratosDesembolsoConInteresVOImpl) findViewObject("ContratosDesembolsoConInteresVO");
    }


    /**
     * Container's getter for ConsultarLineasPorOperacionVO1.
     * @return ConsultarLineasPorOperacionVO1
     */
    public ConsultarLineasPorOperacionVOImpl getConsultarLineasPorOperacionVO() {
        return (ConsultarLineasPorOperacionVOImpl) findViewObject("ConsultarLineasPorOperacionVO");
    }
    
    /**
     * Container's getter for VcaTasaDesembolsoFlexcubeVO1.
     * @return VcaTasaDesembolsoFlexcubeVO1
     */
    public VcaTasaDesembolsoFlexcubeVOImpl getVcaTasaDesembolsoFlexcubeVO() {
        return (VcaTasaDesembolsoFlexcubeVOImpl) findViewObject("VcaTasaDesembolsoFlexcubeVO");
    }

    /**
     * Container's getter for ActualizarOperacionVO1.
     * @return ActualizarOperacionVO1
     */
    public ActualizarOperacionVOImpl getActualizarOperacionVO() {
        return (ActualizarOperacionVOImpl) findViewObject("ActualizarOperacionVO");
    }

    /**
     * Container's getter for SolicitudDesembolsoVO1.
     * @return SolicitudDesembolsoVO1
     */
    public SolicitudDesembolsoVOImpl getSolicitudDesembolsoVO() {
        return (SolicitudDesembolsoVOImpl) findViewObject("SolicitudDesembolsoVO");
    }

    /**
     * Container's getter for ContratoDesembolsoVO1.
     * @return ContratoDesembolsoVO1
     */
    public ContratoDesembolsoVOImpl getContratoDesembolsoVO() {
        return (ContratoDesembolsoVOImpl) findViewObject("ContratoDesembolsoVO");
    }
    
     /**
     * Container's getter for ContratosPorLiquidarByOperacion1.
     * @return ContratosPorLiquidarByOperacion1
     */
    public ContratosPorLiquidarByOperacionImpl getContratosPorLiquidarByOperacion() {
        return (ContratosPorLiquidarByOperacionImpl) findViewObject("ContratosPorLiquidarByOperacion");
    }

    /**
     * Container's getter for EncabezadoRegistrarDatosLineaCreditoVO1.
     * @return EncabezadoRegistrarDatosLineaCreditoVO1
     */
    public EncabezadoRegistrarDatosLineaCreditoVOImpl getEncabezadoRegistrarDatosLineaCreditoVO() {
        return (EncabezadoRegistrarDatosLineaCreditoVOImpl) findViewObject("EncabezadoRegistrarDatosLineaCreditoVO");
    }

    /**
     * Container's getter for DatosLineaCreditoVO1.
     * @return DatosLineaCreditoVO1
     */
    public DatosLineaCreditoVOImpl getDatosLineaCreditoVO() {
        return (DatosLineaCreditoVOImpl) findViewObject("DatosLineaCreditoVO");
    }

    /**
     * Container's getter for RegistrarDatosLineaCreditoVO1.
     * @return RegistrarDatosLineaCreditoVO1
     */
    public RegistrarDatosLineaCreditoVOImpl getRegistrarDatosLineaCreditoVO() {
        return (RegistrarDatosLineaCreditoVOImpl) findViewObject("RegistrarDatosLineaCreditoVO");
    }

    /**
     * Container's getter for CatFondoContableVO1.
     * @return CatFondoContableVO1
     */
    public CatFondoContableVOImpl getCatFondoContableVO() {
        return (CatFondoContableVOImpl) findViewObject("CatFondoContableVO");
    }

    /**
     * Container's getter for CatProductoFlexcubeLOV1.
     * @return CatProductoFlexcubeLOV1
     */
    public CatProductoFlexcubeLOVImpl getCatProductoFlexcubeLOV() {
        return (CatProductoFlexcubeLOVImpl) findViewObject("CatProductoFlexcubeLOV");
    }

    /**
     * Container's getter for CatPlazoFinanciamientoLOV1.
     * @return CatPlazoFinanciamientoLOV1
     */
    public CatPlazoFinanciamientoLOVImpl getCatPlazoFinanciamientoLOV() {
        return (CatPlazoFinanciamientoLOVImpl) findViewObject("CatPlazoFinanciamientoLOV");
    }

    /**
     * Container's getter for CatPeriodoGraciaLOV1.
     * @return CatPeriodoGraciaLOV1
     */
    public CatPeriodoGraciaLOVImpl getCatPeriodoGraciaLOV() {
        return (CatPeriodoGraciaLOVImpl) findViewObject("CatPeriodoGraciaLOV");
    }

    /**
     * Container's getter for CatDiasFeriadosVO1.
     * @return CatDiasFeriadosVO1
     */
    public CatDiasFeriadosVOImpl getCatDiasFeriadosVO() {
        return (CatDiasFeriadosVOImpl) findViewObject("CatDiasFeriadosVO");
    }

    /**
     * Container's getter for CatCuentaClienteLOV1.
     * @return CatCuentaClienteLOV1
     */
    public CatCuentaClienteLOVImpl getCatCuentaClienteLOV() {
        return (CatCuentaClienteLOVImpl) findViewObject("CatCuentaClienteLOV");
    }

    /**
     * Container's getter for VencimientoPlazoVO1.
     * @return VencimientoPlazoVO1
     */
    public VencimientoPlazoVOImpl getVencimientoPlazoVO() {
        return (VencimientoPlazoVOImpl) findViewObject("VencimientoPlazoVO");
    }

    /**
     * Container's getter for ComisionVO1.
     * @return ComisionVO1
     */
    public ComisionVOImpl getComisionVO() {
        return (ComisionVOImpl) findViewObject("ComisionVO");
    }

    /**
     * Container's getter for AgregarVencimientoPlazoVO1.
     * @return AgregarVencimientoPlazoVO1
     */
    public AgregarVencimientoPlazoVOImpl getAgregarVencimientoPlazoVO() {
        return (AgregarVencimientoPlazoVOImpl) findViewObject("AgregarVencimientoPlazoVO");
    }

    /**
     * Container's getter for AgregarComisionVO1.
     * @return AgregarComisionVO1
     */
    public AgregarComisionVOImpl getAgregarComisionVO() {
        return (AgregarComisionVOImpl) findViewObject("AgregarComisionVO");
    }

    /**
     * Container's getter for TiposComisionLOV1.
     * @return TiposComisionLOV1
     */
    public TiposComisionLOVImpl getTiposComisionLOV() {
        return (TiposComisionLOVImpl) findViewObject("TiposComisionLOV");
    }

    /**
     * Container's getter for AgregarLineaCreditoVO1.
     * @return AgregarLineaCreditoVO1
     */
    public AgregarLineaCreditoVOImpl getAgregarLineaCreditoVO() {
        return (AgregarLineaCreditoVOImpl) findViewObject("AgregarLineaCreditoVO");
    }

    /**
     * Container's getter for LineaCreditoVO1.
     * @return LineaCreditoVO1
     */
    public LineaCreditoVOImpl getLineaCreditoVO() {
        return (LineaCreditoVOImpl) findViewObject("LineaCreditoVO");
    }

    /**
     * Container's getter for ConfiguracionVO1.
     * @return ConfiguracionVO1
     */
    public ConfiguracionVOImpl getConfiguracionVO() {
        return (ConfiguracionVOImpl) findViewObject("ConfiguracionVO");
    }

    /**
     * Container's getter for MontoOperacionVO1.
     * @return MontoOperacionVO1
     */
    public MontoOperacionVOImpl getMontoOperacionImplementacionPCTVO() {
        return (MontoOperacionVOImpl) findViewObject("MontoOperacionImplementacionPCTVO");
    }

    /**
     * Container's getter for ContratoVO1.
     * @return ContratoVO1
     */
    public ContratoVOImpl getContratoImplementacionVO() {
        return (ContratoVOImpl) findViewObject("ContratoImplementacionVO");
    }

    /**
     * Container's getter for ConsultarTerminoContratacionClienteVO1.
     * @return ConsultarTerminoContratacionClienteVO1
     */
    public ConsultarTerminoContratacionClienteVOImpl getConsultarTerminoContratacionClienteVO() {
        return (ConsultarTerminoContratacionClienteVOImpl) findViewObject("ConsultarTerminoContratacionClienteVO");
    }

    /**
     * Container's getter for MontoTotalEnvioGastoPorContratosDesembolsoVO1.
     * @return MontoTotalEnvioGastoPorContratosDesembolsoVO1
     */
    public MontoTotalEnvioGastoPorContratosDesembolsoVOImpl getMontoTotalEnvioGastoPorContratosDesembolsoVO() {
        return (MontoTotalEnvioGastoPorContratosDesembolsoVOImpl) findViewObject("MontoTotalEnvioGastoPorContratosDesembolsoVO");
    }

    /**
     * Container's getter for ContratoDesembolsoVO1.
     * @return ContratoDesembolsoVO1
     */
    public ContratoDesembolsoVOImpl getContratoDesembolsoVOFT05() {
        return (ContratoDesembolsoVOImpl) findViewObject("ContratoDesembolsoVOFT05");
    }

    /**
     * Container's getter for CondicionesFinancierasVO1.
     * @return CondicionesFinancierasVO1
     */
    public CondicionesFinancierasVOImpl getCondicionesFinancierasImplementacionVO() {
        return (CondicionesFinancierasVOImpl) findViewObject("CondicionesFinancierasImplementacionVO");
    }

    /**
     * Container's getter for VtaProdFlexComponenteDesemVO1.
     * @return VtaProdFlexComponenteDesemVO1
     */
    public VtaProdFlexComponenteDesemVOImpl getVtaProdFlexComponenteDesemImplementacionVO() {
        return (VtaProdFlexComponenteDesemVOImpl) findViewObject("VtaProdFlexComponenteDesemImplementacionVO");
    }

    /**
     * Container's getter for TcaPaisesVO1.
     * @return TcaPaisesVO1
     */
    public TcaPaisesVOImpl getTcaPaisesVO() {
        return (TcaPaisesVOImpl) findViewObject("TcaPaisesVO");
    }

    /**
     * Container's getter for LineasContratosCreadosImplementacionVO1.
     * @return LineasContratosCreadosImplementacionVO1
     */
    public LineasContratosCreadosImplementacionVOImpl getLineasContratosCreadosImplementacionVO() {
        return (LineasContratosCreadosImplementacionVOImpl) findViewObject("LineasContratosCreadosImplementacionVO");
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
     * Container's getter for TasaReferenciaVO1.
     * @return TasaReferenciaVO1
     */
    public TasaReferenciaVOImpl getTasaDescripcionVO() {
        return (TasaReferenciaVOImpl) findViewObject("TasaDescripcionVO");
    }

    /**
     * Container's getter for MontoAmpliacionLineaCredtoVO1.
     * @return MontoAmpliacionLineaCredtoVO1
     */
    public MontoAmpliacionLineaCredtoVOImpl getMontoAmpliacionLineaCredtoVO() {
        return (MontoAmpliacionLineaCredtoVOImpl) findViewObject("MontoAmpliacionLineaCredtoVO");
    }

    /**
     * Container's getter for CondicionesFinancierasVO1.
     * @return CondicionesFinancierasVO1
     */
    public CondicionesFinancierasVOImpl getCondicionesFinancierasInstanciaNuevaVO() {
        return (CondicionesFinancierasVOImpl) findViewObject("CondicionesFinancierasInstanciaNuevaVO");
    }

    /**
     * Container's getter for MontoAmpliacionVO1.
     * @return MontoAmpliacionVO1
     */
    public MontoAmpliacionVOImpl getMontoAmpliacionVO() {
        return (MontoAmpliacionVOImpl) findViewObject("MontoAmpliacionVO");
    }
}

