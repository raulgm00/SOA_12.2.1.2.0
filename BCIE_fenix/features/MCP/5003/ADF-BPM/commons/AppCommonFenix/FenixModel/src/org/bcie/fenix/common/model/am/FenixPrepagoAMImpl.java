package org.bcie.fenix.common.model.am;

import com.bcie.xmlns.operacionservice.Operacion12BndQSService;
import com.bcie.xmlns.operacionservice.Operacion12Port;

import com.bcie.xmlns.prepagoservice.PrepagoPT;

import com.bcie.xmlns.prepagoservice.PrepagoPT12BndQSService;

import java.io.FileOutputStream;
import java.io.OutputStream;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.sql.Timestamp;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSet;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ApplicationModuleImpl;


import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;

import oracle.security.crypto.core.math.Prime;

import org.bcie.atributobo.NivelType;
import org.bcie.clientebo.Mora;
import org.bcie.documentobo.Documento;
import org.bcie.documentobo.ListaDocumentos;
import org.bcie.fenix.common.model.FenixModelConstants;
import static org.bcie.fenix.common.model.FenixModelConstants.BAN_RETORNO;
import static org.bcie.fenix.common.model.FenixModelConstants.COL_01;
import static org.bcie.fenix.common.model.FenixModelConstants.COL_02;
import static org.bcie.fenix.common.model.FenixModelConstants.COL_03;
import static org.bcie.fenix.common.model.FenixModelConstants.COL_04;
import static org.bcie.fenix.common.model.FenixModelConstants.COL_05;
import static org.bcie.fenix.common.model.FenixModelConstants.COL_06;
import static org.bcie.fenix.common.model.FenixModelConstants.COL_07;
import static org.bcie.fenix.common.model.FenixModelConstants.COL_08;
import static org.bcie.fenix.common.model.FenixModelConstants.COL_09;
import org.bcie.fenix.common.model.am.common.FenixPrepagoAM;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.utils.ModelUtils;
import org.bcie.fenix.common.model.vo.CalculoDetallePenalidadVOImpl;
import org.bcie.fenix.common.model.vo.CalculoInteresesVOImpl;
import org.bcie.fenix.common.model.vo.CalculoCargoPenalidadPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.CalculoDetallePenalidadVORowImpl;
import org.bcie.fenix.common.model.vo.CalculoInteresesVOImpl;
import org.bcie.fenix.common.model.vo.CargoPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.CatProductoVOImpl;
import org.bcie.fenix.common.model.vo.CoberturasPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.ConsolidadoPagoPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarCalculoInteresesVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarComisionPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarDetallePenalidadVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarObservacionCargoPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.ConsultarObservacionPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.ContratoDesembolsoExternoPorOperacionVOImpl;
import org.bcie.fenix.common.model.vo.ContratoDesembolsoExternoVOImpl;
import org.bcie.fenix.common.model.vo.ContratoDesembolsoPorRangoFechasPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.ContratoOperacionSaldoNoVencidoVOImpl;
import org.bcie.fenix.common.model.vo.DetalleCargoPenalidadPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.DetallePenalidadVOImpl;
import org.bcie.fenix.common.model.vo.DiasHabilesVOImpl;
import org.bcie.fenix.common.model.vo.DiasInhabilesVOImpl;
import org.bcie.fenix.common.model.vo.FormularioCalculoInteresesVOImpl;
import org.bcie.fenix.common.model.vo.FormularioDetallePenalidadVOImpl;
import org.bcie.fenix.common.model.vo.FormularioGestionarCoberturaVOImpl;
import org.bcie.fenix.common.model.vo.FormularioObservacionCargoPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.FormularioObservacionPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.FormularioPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.FuentesExternasPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.GestionarCoberturaVOImpl;
import org.bcie.fenix.common.model.vo.ObservacionPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.ObtenerCalculoInteresesVOImpl;
import org.bcie.fenix.common.model.vo.ObtenerDetallePenalidadVOImpl;
import org.bcie.fenix.common.model.vo.ObtenerObservacionCargoPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.ObtenerTrePrepagoContratoVOImpl;
import org.bcie.fenix.common.model.vo.PrepagoVOImpl;
import org.bcie.fenix.common.model.vo.CondicionesEspecialesPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.ConsolidadoPagoPrepagoVORowImpl;
import org.bcie.fenix.common.model.vo.ConsultarObservacionPrepagoVORowImpl;
import org.bcie.fenix.common.model.vo.ContratoDesembolsoPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.ContratoDesembolsoPrepagoVORowImpl;
import org.bcie.fenix.common.model.vo.DetalleOperacionVORowImpl;
import org.bcie.fenix.common.model.vo.FormularioCalculoInteresesVORowImpl;
import org.bcie.fenix.common.model.vo.FormularioDetallePenalidadVORowImpl;
import org.bcie.fenix.common.model.vo.FormularioGestionarCoberturaVORowImpl;
import org.bcie.fenix.common.model.vo.FormularioObservacionCargoPrepagoVORowImpl;
import org.bcie.fenix.common.model.vo.FormularioObservacionPrepagoVORowImpl;
import org.bcie.fenix.common.model.vo.FormularioPrepagoVORowImpl;
import org.bcie.fenix.common.model.vo.GestionarCoberturaVORowImpl;
import org.bcie.fenix.common.model.vo.ObtenerObservacionCargoPrepagoVORowImpl;
import org.bcie.fenix.common.model.vo.PrepagoVORowImpl;
import org.bcie.fenix.common.model.vo.ResponsableVOImpl;
import org.bcie.fenix.common.model.vo.SectorGarantiaVOImpl;
import org.bcie.fenix.common.model.vo.TasasFlexcubeVOImpl;
import org.bcie.fenix.common.model.vo.TcaTipoMonedaVOImpl;
import org.bcie.fenix.common.model.vo.TipoMonedaCargoPrepagoLOVImpl;
import org.bcie.fenix.common.model.vo.TrePrepagoContratoVOImpl;
import org.bcie.fenix.common.model.vo.TrePrepagoContratoVORowImpl;
import org.bcie.fenix.common.model.vo.VentaCarteraPrepagoVOImpl;
import org.bcie.fenix.common.model.vo.common.ContratoDesembolsoPrepagoVO;
import org.bcie.fenix.common.model.vo.common.ObtenerDetallePenalidadVO;
import org.bcie.fenix.common.model.vo.common.TrePrepagoContratoVO;
import org.bcie.operacionbo.Operacion;
import org.bcie.operacionmo.ConsultarOperacionByIdOperacionRequestType;
import org.bcie.operacionmo.ConsultarOperacionResponseType;
import org.bcie.prepagobo.PrepagoType;
import org.bcie.prepagomo.ExtraerReportePrepagoRequestType;
import org.bcie.prepagomo.ExtraerReportePrepagoResponseType;
import org.bcie.prepagomo.InformePrepagoRequestType;
import org.bcie.prepagomo.InformePrepagoResponseType;
import org.bcie.prepagomo.ReportePrepagoRequestType;
import org.bcie.prepagomo.ReportePrepagoResponseType;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Aug 29 11:58:46 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FenixPrepagoAMImpl extends ApplicationModuleImpl implements FenixPrepagoAM {
    
    private static ADFLogger logger = null;
    
    /**
     * This is the default constructor (do not remove).
     */
    public FenixPrepagoAMImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void insertarContratoDesembolso(Long idPrepago){
        ContratoDesembolsoPrepagoVOImpl contratoDesembolsoPrepagoVOImpl = this.getContratoDesembolsoPrepagoVO();
        TrePrepagoContratoVOImpl trePrepagoContratoVOImpl = this.getTrePrepagoContratoVO();
        //variable del 'id' de la tabla 'TRE_PREPAGO_CONTRATO'
        Long idTrePrepagoContrato = null;
        //variables temporables 'Contratos de desembolsos'
        BigDecimal capitalNoVencido = null;
        Integer esPagoTotal = null;
        Timestamp fechaProximoPago = null;
        Long idContratoDesembolso = null;
        Integer idTcaTipoMonedaContratoDesembolso = null;
        BigDecimal montoPrepagoContratoDesembolso = null;
        String contratoFlexcube = null;
        //recuperar la lista de registros de la tabla 'Contratos de desembolsos', todos los que tengan 'check pagoTotal' o
        //se alla echo una modificacion en el campo 'montoPrepago' a nivel de la vista.
        contratoDesembolsoPrepagoVOImpl.setRangeSize(-1);
        logger.log(ADFLogger.WARNING,"row count in  contratoDesembolsoPrepagoVO is : " + 
                                                            contratoDesembolsoPrepagoVOImpl.getRowCount());
        Row[] rowContratoDesembolsoPrepago = contratoDesembolsoPrepagoVOImpl.getAllRowsInRange();
        //recorrer todos los 'row´s'
        for (Row row : rowContratoDesembolsoPrepago) {
            if(new Integer("0").equals(row.getAttribute("IdHijo"))){
                BigDecimal comparateBigDecimal = new BigDecimal("0");
                //Si el row tiene un 'montoPrepago' diferente a 0, es decir que sufrio una modificacion y es necesario
                //insertarlo en la base de datos.
                if(!comparateBigDecimal.equals(row.getAttribute("MontoPrepago"))&&
                        null != row.getAttribute("MontoPrepago")){
                    //recuperar los valores del Row
                    if(null != row.getAttribute("CapitalNoVencido"))
                        capitalNoVencido = (BigDecimal) row.getAttribute("CapitalNoVencido");
                    if(null != row.getAttribute("EsPagoTotal"))
                        esPagoTotal =  ((Boolean)row.getAttribute("EsPagoTotal")) ? new Integer(1):new Integer(0);
                    if(null != row.getAttribute("FechaProximoPago"))
                        fechaProximoPago = (Timestamp) row.getAttribute("FechaProximoPago");
                    if(null != row.getAttribute("ContratoDesembolsoFlexcube"))
                        contratoFlexcube = (String) row.getAttribute("ContratoDesembolsoFlexcube");
                    if(null != row.getAttribute("IdTcaTipoMoneda"))
                        idTcaTipoMonedaContratoDesembolso = (Integer) row.getAttribute("IdTcaTipoMoneda");
                    if(null != row.getAttribute("MontoPrepago"))
                        montoPrepagoContratoDesembolso = (BigDecimal) row.getAttribute("MontoPrepago");
                    if(null != row.getAttribute("IdContratoDesembolsoFenix"))
                        idContratoDesembolso = (Long) row.getAttribute("IdContratoDesembolsoFenix");
                    //realizar los 'n' insert a la tabla 'TRE_PREPAGO_CONTRATO', se insertaran tantos registros se allan 
                    //modificado en la vista.
                    idTrePrepagoContrato = trePrepagoContratoVOImpl.crearTrePrepagoContrato(capitalNoVencido, esPagoTotal,
                                                                    fechaProximoPago,idContratoDesembolso, idPrepago, 
                                                                            idTcaTipoMonedaContratoDesembolso, 
                                                                                montoPrepagoContratoDesembolso,
                                                                                    contratoFlexcube);
                    
                    logger.log(ADFLogger.WARNING,"ID value of the table 'TRE_PREPAGO_CONTRATO' is : "+idTrePrepagoContrato);
                    
                }
            }
        }
        
    }
    
    public Boolean eliminarTrePrepagoContratoByIdPrepago(Long idPrepago){
        Boolean resultado = Boolean.FALSE;
        try{
            logger.log(ADFLogger.WARNING, "Dentro de eliminarTrePrepagoContratoByIdPrepago : "+idPrepago);
            List<Long> listaIdTrePrepagoContrato = new ArrayList<Long>();
            ObtenerTrePrepagoContratoVOImpl obtenerTrePrepagoContratoVOImpl = this.getObtenerTrePrepagoContratoVO();
            TrePrepagoContratoVOImpl trePrepagoContratoVOImpl = this.getTrePrepagoContratoVO();
            listaIdTrePrepagoContrato = obtenerTrePrepagoContratoVOImpl.obtenerListaTrePrepagoContratoPorIdPrepago(idPrepago);
            for(Long idTrePrepagoContrato : listaIdTrePrepagoContrato){
                trePrepagoContratoVOImpl.eliminarRowTrePrepagoContrato(idTrePrepagoContrato);
            }
            resultado  = Boolean.TRUE;
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en eliminarTrePrepagoContratoByIdPrepago.", e);
            resultado = Boolean.FALSE;
            JboException ex = new JboException(e);
            ex.addToExceptions(new Exception("Error en eliminarTrePrepagoContratoByIdPrepago " + e.getClass() + ":" + e.getMessage()));
            throw ex;
        }
        return resultado;
    }
    
    public Boolean eliminarDetallePenalidadByIdPrepago(Long idPrepago){
        Boolean resultado = Boolean.FALSE;
        try{
            logger.log(ADFLogger.WARNING, "Dentro de eliminarDetallePenalidadByIdPrepago : "+idPrepago);
            List<Long> listaIdDetallePenalidad = new ArrayList<Long>();
            ObtenerDetallePenalidadVOImpl obtenerDetallePenalidadVOImpl = this.getObtenerDetallePenalidadVO();
            DetallePenalidadVOImpl detallePenalidadVOImpl = this.getDetallePenalidadVO();
            listaIdDetallePenalidad = obtenerDetallePenalidadVOImpl.obtenerListaDetallePenalidadPorIdPrepago(idPrepago);
            for(Long idDetallePenalidad : listaIdDetallePenalidad){
                detallePenalidadVOImpl.eliminarRowDetallePenalidad(idDetallePenalidad);
            }
            resultado  = Boolean.TRUE;
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en eliminarDetallePenalidadByIdPrepago.", e);
            resultado = Boolean.FALSE;
            JboException ex = new JboException(e);
            ex.addToExceptions(new Exception("Error en eliminarDetallePenalidadByIdPrepago " + e.getClass() + ":" + e.getMessage()));
            throw ex;
        }
        return resultado;
    }
    
    public Boolean eliminarCalculoInteresesByIdPrepago(Long idPrepago){
        Boolean resultado = Boolean.FALSE;
        try{
            logger.log(ADFLogger.WARNING, "Dentro de eliminarCalculoInteresesByIdPrepago : "+idPrepago);
            List<Long> listaIdCalculoIntereses = new ArrayList<Long>();
            ObtenerCalculoInteresesVOImpl obtenerCalculoInteresesVOImpl = this.getObtenerCalculoInteresesVO();
            CalculoInteresesVOImpl calculoInteresesVOImpl =this.getCalculoInteresesVO();
            listaIdCalculoIntereses = obtenerCalculoInteresesVOImpl.obtenerListaCalculoInteresesPorIdPrepago(idPrepago);
            for(Long idCalculoIntereses : listaIdCalculoIntereses){
                calculoInteresesVOImpl.eliminarRowCalculoIntereses(idCalculoIntereses);
            }
            resultado  = Boolean.TRUE;
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en eliminarDetallePenalidadByIdPrepago.", e);
            resultado = Boolean.FALSE;
            JboException ex = new JboException(e);
            ex.addToExceptions(new Exception("Error en eliminarDetallePenalidadByIdPrepago " + e.getClass() + ":" + e.getMessage()));
            throw ex;
        }
        return resultado;
    }
    
    public void llenarDetallePenalidad(Long idPrepago,Integer idProducto,Integer idOperacion, Timestamp fechaCalculoDefinitivo){
        logger.log(ADFLogger.WARNING, "aghl Dentro de llenarDetallePenalidad con el parametro idPrepago : "+idPrepago);
        logger.log(ADFLogger.WARNING, "Dentro de llenarDetallePenalidad con el parametro idProducto : "+idProducto);
        logger.log(ADFLogger.WARNING, "Dentro de llenarDetallePenalidad con el parametro idOperacion : "+idOperacion);
        logger.log(ADFLogger.WARNING, "Dentro de llenarDetallePenalidad con el parametro fechaCalculoDefinitivo : "+fechaCalculoDefinitivo);
        //variables temporales 
        Timestamp fechaInicio_original = null;
        Timestamp fechaInicio = null;
        Timestamp fechaFin = null;
        BigDecimal plazo_original = null;
        BigDecimal plazo = null;
        BigDecimal spread = null;
        String fraccionLibor = null;
        BigDecimal tasaPrepago = null;
        BigDecimal montoPenalidad = null;
        BigDecimal montoPenalidadAux = null;
        BigDecimal intereses = null;
        Long idTrePrepagoContrato = null;
        String contratoDesembolso = null;
        String moneda = null;
        String resolucion = null;
        BigDecimal montoPrepago = null;
        Timestamp proximoPago = null;
        
        //variables aux para calculos y validaciones
        Integer idTcaTipoResolucion = null;
        BigDecimal tasaPrime = null;
        BigDecimal tasaLibor = null;
        Double fraccionLiborDouble = null;
        Integer esPagoTotal = null;
        Timestamp fechaPrepago = null;
        Double tasaPrepagoDouble = null;
        Integer idTcaTipoMoneda = null;
        Boolean esValidoCalcularPenalidad = Boolean.TRUE;
        Boolean esValidoCargoPorTramite = Boolean.TRUE;
        
        //recuperar tasasde flexcube por codigo y codigoMoneda
        TasasFlexcubeVOImpl tasasFlexcubeVOImpl = this.getTasasFlexcubeVO();
        //obtener tasaPrime
        tasaPrime = tasasFlexcubeVOImpl.obtenerTasa("1002", "USD", fechaCalculoDefinitivo);
        //obtener tasaLibor
        tasaLibor = tasasFlexcubeVOImpl.obtenerTasa("1104", "USD", fechaCalculoDefinitivo);
        
        FormularioCalculoInteresesVOImpl formularioCalculoInteresesVOImpl = this.getFormularioCalculoInteresesVO();
        
        ContratoDesembolsoExternoPorOperacionVOImpl contratoDesembolsoExternoPorOperacionVOImpl = this.getContratoDesembolsoExternoPorOperacionVO();
        ContratoDesembolsoExternoVOImpl contratoDesembolsoExternoVOImpl = this.getContratoDesembolsoExternoVO();
            
        FormularioDetallePenalidadVOImpl formularioDetallePenalidadVOImpl = this.getFormularioDetallePenalidadVO();
        formularioDetallePenalidadVOImpl.limpiarFormularioDetallePenalidadVO();
        
        CalculoDetallePenalidadVOImpl calculoDetallePenalidadVOImpl = this.getCalculoDetallePenalidadVO();
        calculoDetallePenalidadVOImpl.setIdPrepago(idPrepago);
        calculoDetallePenalidadVOImpl.executeQuery();
        calculoDetallePenalidadVOImpl.setRangeSize(-1);
        
        for(Row row : calculoDetallePenalidadVOImpl.getAllRowsInRange()){
            montoPenalidad = BigDecimal.ZERO;
            
            idTrePrepagoContrato = (null != row.getAttribute(CalculoDetallePenalidadVORowImpl.IDTREPREPAGOCONTRATO)) 
                                    ? (Long)row.getAttribute(CalculoDetallePenalidadVORowImpl.IDTREPREPAGOCONTRATO) : null;
            
            contratoDesembolso = (null != row.getAttribute(CalculoDetallePenalidadVORowImpl.CONTRATOFLEXCUBE)) 
                                    ? (String)row.getAttribute(CalculoDetallePenalidadVORowImpl.CONTRATOFLEXCUBE) : null;
            
            moneda = (null != row.getAttribute(CalculoDetallePenalidadVORowImpl.MONEDA)) 
                                    ? (String)row.getAttribute(CalculoDetallePenalidadVORowImpl.MONEDA) : null;
            
            resolucion = (null != row.getAttribute(CalculoDetallePenalidadVORowImpl.RESOLUCION)) 
                                    ? (String)row.getAttribute(CalculoDetallePenalidadVORowImpl.RESOLUCION) : null;
            
            idTcaTipoResolucion = (null != row.getAttribute(CalculoDetallePenalidadVORowImpl.IDTCATIPORESOLUCION)) 
                                    ? (Integer)row.getAttribute(CalculoDetallePenalidadVORowImpl.IDTCATIPORESOLUCION) : null;
            
            //VA_05   Estos valores se obtienen de flexcube.
            proximoPago = (null != row.getAttribute(CalculoDetallePenalidadVORowImpl.FECHAPROXIMOPAGO)) 
                                    ? (Timestamp)row.getAttribute(CalculoDetallePenalidadVORowImpl.FECHAPROXIMOPAGO) : null;
            
            fechaPrepago = (null != row.getAttribute(CalculoDetallePenalidadVORowImpl.FECHAPREPAGO)) 
                                    ? (Timestamp)row.getAttribute(CalculoDetallePenalidadVORowImpl.FECHAPREPAGO) : null;
            
            switch (idTcaTipoResolucion) {
            //RN_05 La fecha inicio dependerá del tipo de resolución de acuerdo a los siguientes criterios:
            //•       Para la Resolución PRE-10/2008 debe mostrar la fecha efectiva de prepago capturado por el Responsable de Prepago.  
            //        fecha efectiva = fecha prepago
            case 1:
                fechaInicio = (null != row.getAttribute(CalculoDetallePenalidadVORowImpl.FECHAPREPAGO)) 
                                    ? (Timestamp)row.getAttribute(CalculoDetallePenalidadVORowImpl.FECHAPREPAGO) : null;
                logger.log(ADFLogger.WARNING, "1 Valor Fecha Inicio res pre-10/2008 :" + fechaInicio);
                
                fechaInicio_original = (contratoDesembolso != null) ? 
                    contratoDesembolsoExternoVOImpl.obtenerFechaEfectiva(contratoDesembolso) : null;
                logger.log(ADFLogger.WARNING, "2 Valor Fecha Inicio Original res pre-10/2008 :" + fechaInicio);
                break;
            //•        Para la Resolución PRE-28/2003 debe mostrar la fecha efectiva del primer desembolso en caso de proyectos,
            //         y en caso de LGCs, la fecha efectiva del contrato de desembolso.
            //         fecha efectiva = VTA_CONTRATO_DESEMBOLSO.FECHA_APERTURA
            case 2:
                if(obtenerLineaGlobalCredito(idProducto)){
                    //caso de LGCs, la fecha efectiva del contrato de desembolso.
                    fechaInicio = (contratoDesembolso != null) ? 
                        contratoDesembolsoExternoVOImpl.obtenerFechaEfectiva(contratoDesembolso) : null;
                    logger.log(ADFLogger.WARNING, "1 Valor Fecha Inicio res pre-28/2003 :" + fechaInicio);
                }else{
                    //debe mostrar la fecha efectiva del primer desembolso en caso de proyectos
                    fechaInicio = (idOperacion != null) ? 
                        contratoDesembolsoExternoPorOperacionVOImpl.obtenerFechaEfectivaPrimerContratoDesembolso(idOperacion) : null;
                    logger.log(ADFLogger.WARNING, "1 Valor Fecha Inicio res pre-28/2003 :" + fechaInicio);
                }
                break;
            case 3:
                    fechaInicio = (null != row.getAttribute(CalculoDetallePenalidadVORowImpl.FECHAPREPAGO)) 
                                        ? (Timestamp)row.getAttribute(CalculoDetallePenalidadVORowImpl.FECHAPREPAGO) : null;
                    logger.log(ADFLogger.WARNING, "1 Valor Fecha Inicio res otras condiciones :" + fechaInicio);
                    
                    fechaInicio_original = (contratoDesembolso != null) ? 
                        contratoDesembolsoExternoVOImpl.obtenerFechaEfectiva(contratoDesembolso) : null;
                    logger.log(ADFLogger.WARNING, "2 Valor Fecha Inicio Original res otras condiciones :" + fechaInicio);
                    break;
            }
            
            switch (idTcaTipoResolucion) {
            //RN_06   La fecha fin dependerá del tipo de resolución de acuerdo a los siguientes criterios:
            //•       Para la Resolución PRE-10/2008 debe mostrar la fecha de vencimiento de contrato de desembolso.
            //•       Para la Resolución PRE-28/2003 debe mostrar la fecha de prepago capturado por el Responsable de Prepago.
            case 1:
                //mostrar la fecha de vencimiento de contrato de desembolso.
                fechaFin = (null != contratoDesembolso) ? 
                    contratoDesembolsoExternoVOImpl.obtenerFechaVencimiento(contratoDesembolso) : null;
                logger.log(ADFLogger.WARNING, "Valor Fecha Fin :" + fechaFin);
                break;
            case 2:
                //mostrar la fecha de prepago capturado por el Responsable de Prepago.
                fechaFin = (null != row.getAttribute(CalculoDetallePenalidadVORowImpl.FECHAPREPAGO)) 
                                    ? (Timestamp)row.getAttribute(CalculoDetallePenalidadVORowImpl.FECHAPREPAGO) : null;
                logger.log(ADFLogger.WARNING, "Valor Fecha Fin :" + fechaFin);
                break;
            case 3:
                //mostrar la fecha de prepago capturado por el Responsable de Prepago.
                fechaFin = (null != contratoDesembolso) ? 
                    contratoDesembolsoExternoVOImpl.obtenerFechaVencimiento(contratoDesembolso) : null;
                logger.log(ADFLogger.WARNING, "Valor Fecha Fin :" + fechaFin);
                break;
            }
            
            logger.log(ADFLogger.WARNING,"fechaOriginal: "+fechaInicio_original);            
            logger.log(ADFLogger.WARNING,"fechaInicio: "+fechaInicio);
            logger.log(ADFLogger.WARNING,"fechaFin: " +fechaFin);
            //RN_07	El plazo trascurrido es igual a la fecha fin menos fecha inicio entre 365.
            Double plazoLong = null;
            Double plazoOriginalLong = null;
            BigDecimal plazoAux = null;
            BigDecimal plazoAuxOriginal = null;
            
            
            if(null != fechaInicio && null != fechaFin){
                plazoAux = new BigDecimal(fechaFin.getTime()-fechaInicio.getTime());
                plazoAux = plazoAux.divide(new BigDecimal(24 * 60 * 60 * 1000), 2, RoundingMode.CEILING); 
                plazoAux = plazoAux.divide(new BigDecimal(365), 2, RoundingMode.CEILING);
                    
                    //(((fechaFin.getTime()-fechaInicio.getTime()) / (24 * 60 * 60 * 1000))/365);
                plazoLong = plazoAux.doubleValue();
                plazo = plazoAux;
                logger.log(ADFLogger.WARNING, "1 Valor de calculo plazo. String:" + plazo.toString()); 
                logger.log(ADFLogger.WARNING, "1 Valor de calculo plazo.:" + plazoLong + ".:" + plazo); 
                }
            
            
            if(null != fechaInicio_original && null != fechaFin){
                plazoAuxOriginal = new BigDecimal(fechaFin.getTime()-fechaInicio_original.getTime());
                plazoAuxOriginal = plazoAuxOriginal.divide(new BigDecimal(24 * 60 * 60 * 1000), 2, RoundingMode.CEILING); 
                plazoAuxOriginal = plazoAuxOriginal.divide(new BigDecimal(365), 2, RoundingMode.CEILING);
                    
                    //(((fechaFin.getTime()-fechaInicio.getTime()) / (24 * 60 * 60 * 1000))/365); 
                plazoOriginalLong = plazoAuxOriginal.doubleValue();
                plazo_original = plazoAuxOriginal;
                logger.log(ADFLogger.WARNING, "1 Valor de calculo plazo_original. String:" + plazo_original.toString()); 
                logger.log(ADFLogger.WARNING, "1 Valor de calculo plazo_original.:" + plazoOriginalLong + ". plazo_original:" + plazo_original);
              }
            //RN_03   El porcentaje Spread es un margen adicional que se asigna según el plazo remanente del prepago, de acuerdo a la siguiente tabla:
            //Plazo Remanente         Spread
            //Hasta 18 meses            1.00 %
            //De 18 meses a 5 años      2.00 %
            //Mayor a 5 años            3.00 %
            logger.log(ADFLogger.WARNING, "Valor Plazo Long :" + plazoLong);
            if(null != plazoLong && plazoLong<1.5)
                spread = new BigDecimal(1.00);
            if(null != plazoLong && plazoLong>=1.5 && plazoLong<=5)
                spread = new BigDecimal(2.00);
            if(null != plazoLong && plazoLong>5)
                spread = new BigDecimal(3.00);
            //RN_08   La fracción Libor es la compensación por pérdidas de oportunidad;
            //la fracción a tomar deberá mostrarse a 5 decimales y
            //dependerá del plazo transcurrido y del porcentaje de Libor a 6 meses como se muestra en la siguiente tabla:  
            //Libor 6 meses     Penalidad primeros 3 años       Penalidad después de 3 años
            //0 - 3%                  2/3 de Libor                    4/5 de Libor
            //3.01% - 5.0%            ½ de Libor                      3/5 de Libor
            //5.01% en adelante       1/3 de Libor                    2/5 de Libor
            //VA_01   Se mostrará cuando la resolución a prepagar sea PRE-28/2003 en formato de solo lectura.
            //VA_14   No se debe mostrar cuando la solicitud de prepago corresponda a la Resolución "Otras condiciones"
            switch (idTcaTipoResolucion) {
            case 2:
                logger.log(ADFLogger.WARNING,"tasaLibor: " +tasaLibor);
                
                //FNXII-6221 Error por no validar objeto NULL en el registro de LOG.
                if(tasaLibor != null){
                    logger.log(ADFLogger.WARNING,"tasaLibor.doubleValue(): " +tasaLibor.doubleValue());    
                }
                
               //0 - 3% libor ----> 2/3
                if(null != tasaLibor  && tasaLibor.doubleValue() >0 && tasaLibor.doubleValue() <= 3){
                    if(null != plazoLong && plazoLong <= 3 ){
                        fraccionLiborDouble = new Double("2") / new Double("3") ;
                        fraccionLibor = "2/3("+tasaLibor.doubleValue()+")";
                    }else{
                        fraccionLiborDouble = new Double("4") / new Double("5") ;
                        fraccionLibor = "4/5("+tasaLibor.doubleValue()+")";
                    }
                }
                //3.01% - 5.1%  ---> 1/2
                if(null != tasaLibor && tasaLibor.doubleValue() > 3 && tasaLibor.doubleValue() <= 5){
                    if(null != plazoLong && plazoLong <= 3 ){
                        fraccionLiborDouble = new Double("1") / new Double("2") ;
                        fraccionLibor = "1/2("+tasaLibor.doubleValue()+")";
                    }else{
                        fraccionLiborDouble = new Double("3") / new Double("5") ;
                        fraccionLibor = "3/5("+tasaLibor.doubleValue()+")";
                    }
                }
                //5.01% en adelante ---> 1/3
                if(null != tasaLibor && tasaLibor.doubleValue() >5){
                    if(null != plazoLong && plazoLong <= 3 ){
                        fraccionLiborDouble = new Double("1") / new Double("3") ;
                        fraccionLibor = "1/3("+tasaLibor.doubleValue()+")";
                    }else{
                        fraccionLiborDouble = new Double("2") / new Double("5") ;
                        fraccionLibor = "2/5("+tasaLibor.doubleValue()+")";
                    }
                }
                break;
            }
            //RN_09   La tasa de prepago se debe calcular  de acuerdo a la resolución a prepagar:
            //•       Para la Resolución PRE-10/2008 es igual a la diferencia de la tasa Prime y la Libor más el porcentaje Spread.
            //•       Para la Resolución PRE-28/2003 es igual al porcentaje de la fracción Libor de acuerdo al rango en que se encuentre el valor de la Libor (primeros 3 años ó después de 3 años) multiplicado por la tasa Libor a 6 meses.
            switch (idTcaTipoResolucion) {
            //Para la Resolución PRE-10/2008 es igual a la diferencia de la tasa Prime y la Libor más el porcentaje Spread.
            case 1:
                logger.log(ADFLogger.WARNING,"El valor Spread :" + spread);
                if(null != tasaLibor && null != tasaPrime && null != spread){
                    //DecimalFormat df = new DecimalFormat("0.00000"); 
                    logger.log(ADFLogger.WARNING,"(tasaPrime.doubleValue()-tasaLibor.doubleValue()): " +
                                                 (tasaPrime.doubleValue()-tasaLibor.doubleValue()));
                    logger.log(ADFLogger.WARNING,"(tasaPrime.doubleValue()-tasaLibor.doubleValue())+spread.doubleValue(): " + 
                                                 ((tasaPrime.doubleValue()-tasaLibor.doubleValue())+spread.doubleValue()));
                    tasaPrepagoDouble = (tasaPrime.doubleValue()-tasaLibor.doubleValue())+spread.doubleValue();
                    logger.log(ADFLogger.WARNING,"tasaPrepagoDouble:" + tasaPrepagoDouble);
                    BigDecimal tasaPrepagoDoubleBD = tasaPrime.subtract(tasaLibor).add(spread); 
                    logger.log(ADFLogger.WARNING,"tasaPrepagoDoubleBD:" + tasaPrepagoDoubleBD);
                    tasaPrepagoDouble = new Double(tasaPrepagoDoubleBD.toString());
                    logger.log(ADFLogger.WARNING,"tasaPrepagoDouble nuevo:" + tasaPrepagoDouble);
                    
                    logger.log(ADFLogger.WARNING,"tasaPrepagoDouble: " +tasaPrepagoDouble);
                    //logger.log(ADFLogger.WARNING,"tasaPrepagoDouble con formato: " +df.format(tasaPrepagoDouble));
                    tasaPrepago = BigDecimal.valueOf(tasaPrepagoDouble);
                    logger.log(ADFLogger.WARNING,"tasaPrepago sin scale: " +tasaPrepago);
                    tasaPrepago = tasaPrepago.setScale(5,RoundingMode.FLOOR);
                    logger.log(ADFLogger.WARNING,"tasaPrepago con scale: " +tasaPrepago);
                } else {
                    logger.log("valores nulos: tasaLibor="+tasaLibor+", tasaPrime=" + tasaPrime + ", spread="+spread);
                }
                break;
            //Para la Resolución PRE-28/2003 es igual al porcentaje de la fracción Libor de acuerdo al rango en que se
            //encuentre el valor de la Libor (primeros 3 años ó después de 3 años) multiplicado por la tasa Libor a 6 meses.
            case 2:
                if(null != tasaLibor && null != fraccionLiborDouble){
                    //DecimalFormat df = new DecimalFormat("0.00000"); 
                    tasaPrepagoDouble = (tasaLibor.doubleValue() * fraccionLiborDouble);
                    logger.log(ADFLogger.WARNING,"tasaLibor * fraccionLibor : " +tasaLibor.doubleValue() * fraccionLiborDouble);
                    logger.log(ADFLogger.WARNING,"tasaPrepagoDouble: " +tasaPrepagoDouble);
                    //logger.log(ADFLogger.WARNING,"tasaPrepagoDouble con formato: " +df.format(tasaPrepagoDouble));
                    tasaPrepago = BigDecimal.valueOf(tasaPrepagoDouble);
                    logger.log(ADFLogger.WARNING,"tasaPrepago sin scale: " +tasaPrepago);
                    tasaPrepago = tasaPrepago.setScale(5,RoundingMode.FLOOR);
                    logger.log(ADFLogger.WARNING,"tasaPrepago con scale: " +tasaPrepago);
                }
                break;
            }
            
            //VA_08	Mostrará la información capturada por el Responsable de Prepago en formato de solo lectura.
            montoPrepago = (null != row.getAttribute(CalculoDetallePenalidadVORowImpl.MONTOPREPAGO)) 
                                    ? (BigDecimal)row.getAttribute(CalculoDetallePenalidadVORowImpl.MONTOPREPAGO) : null;
            
            //VA_08     Mostrará la información capturada por el Responsable de Prepago en formato de solo lectura.
            idTcaTipoMoneda = (null != row.getAttribute(CalculoDetallePenalidadVORowImpl.IDTCATIPOMONEDA)) 
                                    ? (Integer)row.getAttribute(CalculoDetallePenalidadVORowImpl.IDTCATIPOMONEDA) : null;
            //RN_12	Para la PRE-28/2003, cuando la sumatoria de los montos a prepagar, es menor o igual a USD100 mil,
            //          FENIX no debe calcular penalidad únicamente cargo por trámite.
            if(null != idTcaTipoResolucion && idTcaTipoResolucion.equals(FenixModelConstants.PRE28_2003)
                && null != montoPrepago && null != idTcaTipoMoneda){
                FenixAMImpl fenixAMImpl = null;
                BigDecimal montoPrepagoDolares = null;
                Double montoDolaresComparar = Double.valueOf("100000");
                fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
                montoPrepagoDolares = fenixAMImpl.convertirMonedasPrepago(idTcaTipoMoneda,FenixModelConstants.CODIGO_MONEDA_USD,montoPrepago);
                logger.log(ADFLogger.WARNING,"montoPrepago : " +montoPrepago);
                logger.log(ADFLogger.WARNING,"montoPrepago en dolares : " +montoPrepagoDolares);
                if(montoPrepagoDolares.doubleValue() <= montoDolaresComparar){
                    logger.log(ADFLogger.WARNING,"No se calcula penalidad por que montoPrepago en dolares es menor a 100,000 mil USD");
                    esValidoCalcularPenalidad = Boolean.FALSE;
                }else{
                    logger.log(ADFLogger.WARNING,"Si se calcula penalidad por que montoPrepago en dolares es menor a 100,000 mil USD");
                    esValidoCalcularPenalidad = Boolean.TRUE;
                }
            }
            
            //RN_13 En el caso de la PRE-10/2008, cuando el plazo original de la operación es de hasta 12 meses,
            //      FENIX no debe calcular penalidad ni cargo por trámite.
            
            //Modificacion realizada para indicar si aplica a penalidad o no. 20200225
            //if(null != idTcaTipoResolucion && idTcaTipoResolucion.equals(FenixModelConstants.PRE10_2008) && null != plazoLong){
            //    if(plazoLong<1){
            if(null != idTcaTipoResolucion && idTcaTipoResolucion.equals(FenixModelConstants.PRE10_2008) && null != plazoOriginalLong){
                logger.log(ADFLogger.WARNING,"Se validara si aplica prepago para res: PRE10_2008");
                if(plazoOriginalLong.compareTo(1.00) <= 0){
                    logger.log(ADFLogger.WARNING,"Se validara si aplica prepago para res: PRE10_2008 "+plazoOriginalLong+" menor a <=1");
                    esValidoCalcularPenalidad = Boolean.FALSE;
                    esValidoCargoPorTramite = Boolean.FALSE;
                }else{
                    logger.log(ADFLogger.WARNING,"Se validara si aplica prepago para res: PRE10_2008 "+plazoOriginalLong+" mayor a >1");
                    esValidoCalcularPenalidad = Boolean.TRUE;
                    esValidoCargoPorTramite = Boolean.TRUE;
                }
            }
                
            //RN_10   La penalidad de prepago debe ser igual al monto a prepagar multiplicado por la tasa de prepago de cada contrato de desembolso. 
            logger.log(ADFLogger.WARNING, "Valor de tasa prepago :" + tasaPrepago);
            if(null != montoPrepago && null != tasaPrepago && esValidoCalcularPenalidad){
                BigDecimal tasaPrepagoPorcentaje = tasaPrepago.divide(new BigDecimal(100));
                logger.log(ADFLogger.WARNING,"tasaPrepago / 100: " +tasaPrepagoPorcentaje);
                logger.log(ADFLogger.WARNING,"(tasaPrepago / 100) * montoPrepago: " + 
                                             montoPrepago.multiply(tasaPrepagoPorcentaje));
                montoPenalidadAux = montoPrepago.multiply(tasaPrepagoPorcentaje);
                montoPenalidad = montoPenalidadAux.setScale(2,RoundingMode.FLOOR);
                logger.log(ADFLogger.WARNING,"montoPenalidad: " + montoPenalidad);
                
            }
            //RN_11	Se mostrará el cálculo de interés en caso de prepagos totales de la PRE-10/2008  
            //y otras condiciones cuando el prepago se efectuará en una fecha diferente a una amortización.
            //fechaPrepago es diferente a fechaProximoPago

            intereses = BigDecimal.ZERO;
            
            if(fechaPrepago.compareTo(proximoPago) != 0){
                switch (idTcaTipoResolucion) {
                case 1:
                    esPagoTotal = (null != row.getAttribute(CalculoDetallePenalidadVORowImpl.ESPAGOTOTAL)) 
                                        ? (Integer)row.getAttribute(CalculoDetallePenalidadVORowImpl.ESPAGOTOTAL) : null;
                    if(null != esPagoTotal && esPagoTotal == 1){
                        intereses = formularioCalculoInteresesVOImpl.obtenerInteresesNew(idPrepago, contratoDesembolso, idTcaTipoResolucion, fechaPrepago);
                    }
                    break;
                    
                case 3:
                    intereses = formularioCalculoInteresesVOImpl.obtenerInteresesNew(idPrepago, contratoDesembolso, idTcaTipoResolucion, fechaPrepago);
                    break;
                }
            }
            
            formularioDetallePenalidadVOImpl.crearRowFormularioDetallePenalidad(fechaInicio,fechaFin,plazo,spread,
                                                                                    fraccionLibor,tasaPrepago,   
                                                                                        montoPenalidad,intereses,
                                                                                            idTrePrepagoContrato,
                                                                                                contratoDesembolso,
                                                                                                    moneda,resolucion,
                                                                                                        montoPrepago,
                                                                                                            proximoPago);
            
        }
        
    }
    
    public void llenarCalculoIntereses(Long idPrepago,Integer idTcaTipoResolucion,Timestamp fechaPrepago) {
        logger.warning("Dentro de llenarCalculoIntereses");
        logger.warning("idPrepago: " + idPrepago);
        logger.warning("idTcaTipoResolucion: "+idTcaTipoResolucion);
        logger.warning("fechaPrepago: "+fechaPrepago);
        
        try {
            getFormularioCalculoInteresesVO().llenarCalculoIntereses(idPrepago, idTcaTipoResolucion, fechaPrepago);
        } catch (Exception e) {
            logger.severe("Error en llenarCalculoIntereses.", e);
        }
        
        logger.warning("Fuera de llenarCalculoIntereses");
    }
    
    public Boolean obtenerLineaGlobalCredito(Integer idProducto) {
        Integer retorno = null;
        ViewObjectImpl catProductoVO = getCatProductoVO();
        catProductoVO.setRangeSize(-1);
        if (idProducto != null) {
            Row row = catProductoVO.getRow(new Key(new Object[] { idProducto }));
            
                retorno = (Integer) row.getAttribute("EsIfi");
        }
        
        if (retorno != null && retorno.compareTo(BAN_RETORNO) == 0) {
            return true;
        } else {
            if (retorno == null) {
                logger.log(ADFLogger.WARNING,"No existe clave de producto");

            }
            return false;
        }
    }
    
    public Map obtenerAtributosPrepago(Long idPrepago){
        logger.log(ADFLogger.WARNING, "Dentro de obtenerAtributosPrepago con el parametro idPrepago : "+idPrepago);
        //variable de prepagoVO 
        PrepagoVOImpl prepagoVOImpl = this.getPrepagoVO();
        //variable Map que se va retornar
        Map<String,Object> atributosPrepagoMap = new HashMap<String,Object>();
        //variables temporales que se recuperan del criterio de busqueda
        Long id = null;
        Timestamp fechaSolicitud = null;
        Timestamp fechaPrepago = null;
        Timestamp fechaRenovacion = null;
        BigDecimal montoPrepago = null;
        Integer idTcaTipoMoneda = null;
        Integer idTcaTipoResolucion = null;
        Integer idTcaTipoPrepago = null;
        Integer idTcaTipoCaptura = null;
        Long idObservacion = null;
        
        try {
            ViewCriteria criteria =prepagoVOImpl.getViewCriteriaManager().getViewCriteria("PrepagoVOCriteriaByIdPrepago");
            criteria.ensureVariableManager().setVariableValue("idPrepago", idPrepago);
            prepagoVOImpl.applyViewCriteria(criteria);
            prepagoVOImpl.executeQuery();
            if(null != prepagoVOImpl.getRowAtRangeIndex(0)){
                Row rowPrepagoVO = prepagoVOImpl.getRowAtRangeIndex(0);
                id = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.ID)) 
                        ? (Long)rowPrepagoVO.getAttribute(PrepagoVORowImpl.ID) : null;
                fechaSolicitud = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.FECHASOLICITUD)) 
                        ? (Timestamp)rowPrepagoVO.getAttribute(PrepagoVORowImpl.FECHASOLICITUD) : null;
                fechaPrepago = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.FECHAPREPAGO)) 
                        ? (Timestamp)rowPrepagoVO.getAttribute(PrepagoVORowImpl.FECHAPREPAGO) : null;
                fechaRenovacion = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.FECHARENOVACION)) 
                        ? (Timestamp)rowPrepagoVO.getAttribute(PrepagoVORowImpl.FECHARENOVACION) : null;
                montoPrepago = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.MONTOPREPAGO)) 
                        ? (BigDecimal)rowPrepagoVO.getAttribute(PrepagoVORowImpl.MONTOPREPAGO) : null;
                idTcaTipoMoneda = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPOMONEDA)) 
                        ? (Integer)rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPOMONEDA) : null;
                idTcaTipoResolucion = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPORESOLUCION)) 
                        ? (Integer)rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPORESOLUCION) : null;
                idTcaTipoPrepago = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPOPREPAGO)) 
                        ? (Integer)rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPOPREPAGO) : null;
                idTcaTipoCaptura = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPOCAPTURA)) 
                        ? (Integer)rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPOCAPTURA) : null;
                idObservacion = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDOBSERVACION)) 
                        ? (Long)rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDOBSERVACION) : null;
                
                atributosPrepagoMap.put("id", id);
                atributosPrepagoMap.put("fechaSolicitud", fechaSolicitud);
                atributosPrepagoMap.put("fechaPrepago", fechaPrepago);
                atributosPrepagoMap.put("fechaRenovacion", fechaRenovacion);
                atributosPrepagoMap.put("montoPrepago", montoPrepago);
                atributosPrepagoMap.put("idTcaTipoMoneda", idTcaTipoMoneda);
                atributosPrepagoMap.put("idTcaTipoResolucion", idTcaTipoResolucion);
                atributosPrepagoMap.put("idTcaTipoPrepago", idTcaTipoPrepago);
                atributosPrepagoMap.put("idTcaTipoCaptura", idTcaTipoCaptura);
                atributosPrepagoMap.put("idObservacion", idObservacion);
                
            }else{
                
                logger.log(ADFLogger.WARNING, "Criterio de busqueda vacio, con el idPrepago : "+idPrepago);
            }
            
        } catch (Exception ex) {
            logger.log(ADFLogger.ERROR, "Error en consultarPrepagoById.", ex);
        } finally {
            //This takes care of removing the applied VC.
            prepagoVOImpl.getViewCriteriaManager().removeApplyViewCriteriaName("PrepagoVOCriteriaByIdPrepago");
        }
        
        return atributosPrepagoMap;
    }
    
    public Boolean validarCambiosContratoDesembolsoPrepago(Long idPrepago){
        logger.log(ADFLogger.WARNING, "Dentro de validarCambiosContratoDesembolsoPrepago con el parametro idPrepago : "+idPrepago);
        TrePrepagoContratoVO trePrepagoContratoImpl = this.getTrePrepagoContratoVO();
        ContratoDesembolsoPrepagoVO contratoDesembolsoPrepagoVOImpl = this.getContratoDesembolsoPrepagoVO();
        try {
            ViewCriteria criteria =trePrepagoContratoImpl.getViewCriteriaManager().getViewCriteria("TrePrepagoContratoVOCriteria");
            criteria.ensureVariableManager().setVariableValue("VarIdPrepago", idPrepago);
            trePrepagoContratoImpl.applyViewCriteria(criteria);
            trePrepagoContratoImpl.executeQuery();
            trePrepagoContratoImpl.setRangeSize(-1);
            for(Row row : trePrepagoContratoImpl.getAllRowsInRange()){
                String contratoFlexcube = null;
                BigDecimal montoPrepago = null;
                if(null != row.getAttribute(TrePrepagoContratoVORowImpl.CONTRATOFLEXCUBE)){
                    contratoFlexcube = (String)row.getAttribute(TrePrepagoContratoVORowImpl.CONTRATOFLEXCUBE);
                }
                
                if(null != row.getAttribute(TrePrepagoContratoVORowImpl.MONTOPREPAGO)){
                    montoPrepago = (BigDecimal)row.getAttribute(TrePrepagoContratoVORowImpl.MONTOPREPAGO);
                }
                contratoDesembolsoPrepagoVOImpl.setRangeSize(-1);
                for(Row rowContratoDesembolso : contratoDesembolsoPrepagoVOImpl.getAllRowsInRange()){
                    if(new Integer("0").equals(rowContratoDesembolso.getAttribute("IdHijo"))){
                        String contratoFlexcubeComparar = null;
                        BigDecimal montoPrepagoComparar = null;
                        if(null != rowContratoDesembolso.getAttribute(ContratoDesembolsoPrepagoVORowImpl.CONTRATODESEMBOLSOFLEXCUBE)){
                            contratoFlexcubeComparar = (String) rowContratoDesembolso.getAttribute(ContratoDesembolsoPrepagoVORowImpl.CONTRATODESEMBOLSOFLEXCUBE);
                        }
                        if(null != rowContratoDesembolso.getAttribute(ContratoDesembolsoPrepagoVORowImpl.MONTOPREPAGO)){
                            montoPrepagoComparar = (BigDecimal) rowContratoDesembolso.getAttribute(ContratoDesembolsoPrepagoVORowImpl.MONTOPREPAGO);
                        }
                        
                        if(contratoFlexcube.equals(contratoFlexcubeComparar) && !montoPrepago.equals(montoPrepagoComparar)){
                            return Boolean.TRUE;
                        }
                    }
                }
            }
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Exception en validarCambiosContratoDesembolsoPrepago.", e);
        } finally {
            //This takes care of removing the applied VC.
            trePrepagoContratoImpl.getViewCriteriaManager().removeApplyViewCriteriaName("TrePrepagoContratoVOCriteria");
        }
        
        return Boolean.FALSE;
    }
    
    public Boolean validarCambiosPrepago(Long idPrepago){
        logger.log(ADFLogger.WARNING, "Dentro de validarCambiosPrepago con el parametro idPrepago : " +idPrepago);
        Boolean ocurrioCambio = false;
        Boolean fechaRenovacion = Boolean.FALSE;
        Boolean montoPrepago = Boolean.FALSE;
        Boolean tipoCaptura = Boolean.FALSE;
        //se agregan nuevas banderas para atender la incidencia "FNXII-4848"
        Boolean fechaSolicitud = Boolean.FALSE;
        Boolean tipoResolucion = Boolean.FALSE;
        Boolean fechaPrepago = Boolean.FALSE;
        Boolean tipoPrepago = Boolean.FALSE;
        Boolean tipoMoneda = Boolean.FALSE;
        
        Map<String,Object> atributosPrepagoMap = new HashMap<String,Object>();
        Row formularioPrepagoVORowImpl = this.getFormularioPrepagoVO().getCurrentRow();
        atributosPrepagoMap = (Map<String,Object>)obtenerAtributosPrepago(idPrepago);    
        if(null != formularioPrepagoVORowImpl){
            
            logger.severe("fechaRenovacion Anterior : "+atributosPrepagoMap.get("fechaRenovacion"));
            logger.severe("fechaRenovacion Actual : " + formularioPrepagoVORowImpl.getAttribute("FechaRenovacion"));
            if(null == atributosPrepagoMap.get("fechaRenovacion") &&
                null != formularioPrepagoVORowImpl.getAttribute("FechaRenovacion")){
                    fechaRenovacion = Boolean.TRUE;
               }
            if(null != atributosPrepagoMap.get("fechaRenovacion") && 
                null == formularioPrepagoVORowImpl.getAttribute("FechaRenovacion")){
                fechaRenovacion = Boolean.TRUE;
            }
            
            if(null != atributosPrepagoMap.get("fechaRenovacion") &&
                null != formularioPrepagoVORowImpl.getAttribute("FechaRenovacion")){
                fechaRenovacion = atributosPrepagoMap.get("fechaRenovacion").equals(formularioPrepagoVORowImpl.getAttribute("FechaRenovacion"))
                    ? Boolean.FALSE : Boolean.TRUE;
            }
            logger.log(ADFLogger.WARNING, "fechaRenovacion  cambio :" +fechaRenovacion);
            
            
            logger.severe("montoPrepago Anterior : "+atributosPrepagoMap.get("montoPrepago"));
            logger.severe("montoPrepago Actual : " + formularioPrepagoVORowImpl.getAttribute("MontoPrepago"));
            montoPrepago =(null != atributosPrepagoMap.get("montoPrepago") &&
                                atributosPrepagoMap.get("montoPrepago").equals(formularioPrepagoVORowImpl.getAttribute("MontoPrepago")))
                                    ? Boolean.FALSE : Boolean.TRUE;
            logger.log(ADFLogger.WARNING, "montoPrepago  cambio :" +montoPrepago);
            
            logger.severe("idTcaTipoCaptura Anterior : "+atributosPrepagoMap.get("idTcaTipoCaptura"));
            logger.severe("idTcaTipoCaptura Actual : " + formularioPrepagoVORowImpl.getAttribute("IdTcaTipoCaptura"));
            tipoCaptura = (null != atributosPrepagoMap.get("idTcaTipoCaptura") &&
                                atributosPrepagoMap.get("idTcaTipoCaptura").equals(formularioPrepagoVORowImpl.getAttribute("IdTcaTipoCaptura")))
                                    ? Boolean.FALSE : Boolean.TRUE;
            logger.log(ADFLogger.WARNING, "tipoCaptura  cambio :" +tipoCaptura);
            
            logger.severe("fechaSolicitud Anterior : "+atributosPrepagoMap.get("fechaSolicitud"));
            logger.severe("fechaSolicitud Actual : " + formularioPrepagoVORowImpl.getAttribute("FechaSolicitud"));
            fechaSolicitud = (null != atributosPrepagoMap.get("fechaSolicitud") &&
                                atributosPrepagoMap.get("fechaSolicitud").equals(formularioPrepagoVORowImpl.getAttribute("FechaSolicitud")))
                                    ? Boolean.FALSE : Boolean.TRUE;
            logger.log(ADFLogger.WARNING, "fechaSolicitud  cambio :" +fechaSolicitud);
            
            logger.severe("idTcaTipoResolucion Anterior : "+ atributosPrepagoMap.get("idTcaTipoResolucion"));
            logger.severe("idTcaTipoResolucion Actual : " + formularioPrepagoVORowImpl.getAttribute("IdTcaTipoResolucion"));
            tipoResolucion = (null != atributosPrepagoMap.get("idTcaTipoResolucion") && 
                                atributosPrepagoMap.get("idTcaTipoResolucion").equals(formularioPrepagoVORowImpl.getAttribute("IdTcaTipoResolucion")))
                                    ? Boolean.FALSE : Boolean.TRUE;
            logger.log(ADFLogger.WARNING, "tipo resolucion cambio :" +tipoResolucion);
            
            logger.severe("fechaPrepago Anterior : "+atributosPrepagoMap.get("fechaPrepago"));
            logger.severe("fechaPrepago Actual : "+formularioPrepagoVORowImpl.getAttribute("FechaPrepago"));
            fechaPrepago = (null != atributosPrepagoMap.get("fechaPrepago") && 
                                atributosPrepagoMap.get("fechaPrepago").equals(formularioPrepagoVORowImpl.getAttribute("FechaPrepago")))
                                    ? Boolean.FALSE : Boolean.TRUE;
            logger.log(ADFLogger.WARNING, "fecha prepago cambio :" +fechaPrepago);
            
            logger.severe("idTcaTipoPrepago Anterior : "+atributosPrepagoMap.get("idTcaTipoPrepago"));
            logger.severe("idTcaTipoPrepago Actual: "+formularioPrepagoVORowImpl.getAttribute("IdTcaTipoPrepago"));
            tipoPrepago = (null != atributosPrepagoMap.get("idTcaTipoPrepago") && 
                                atributosPrepagoMap.get("idTcaTipoPrepago").equals(formularioPrepagoVORowImpl.getAttribute("IdTcaTipoPrepago")))
                                    ? Boolean.FALSE : Boolean.TRUE;
            logger.log(ADFLogger.WARNING, "tipo prepago cambio :" +tipoPrepago);
            
            logger.severe("idTcaTipoMoneda Anterior : "+atributosPrepagoMap.get("idTcaTipoMoneda"));
            logger.severe("idTcaTipoMoneda Actual: "+formularioPrepagoVORowImpl.getAttribute("IdTcaTipoMoneda"));
            tipoMoneda = (null != atributosPrepagoMap.get("idTcaTipoMoneda") && 
                                atributosPrepagoMap.get("idTcaTipoMoneda") .equals(formularioPrepagoVORowImpl.getAttribute("IdTcaTipoMoneda")))
                                    ? Boolean.FALSE : Boolean.TRUE;
            logger.log(ADFLogger.WARNING, "tipo moneda cambio :" +tipoMoneda);
            
            //Si una bandera es "TRUE" es que existe un cambio en la solicitud de prepago
            ocurrioCambio = (fechaRenovacion == Boolean.FALSE && montoPrepago  == Boolean.FALSE 
                                && tipoCaptura  == Boolean.FALSE && fechaSolicitud == Boolean.FALSE
                                    && tipoResolucion == Boolean.FALSE  && fechaPrepago == Boolean.FALSE
                                        && tipoPrepago == Boolean.FALSE && tipoMoneda == Boolean.FALSE) 
                ? Boolean.FALSE : Boolean.TRUE;
        }
        
        logger.fine("validarCambiosPrepago return : "+ocurrioCambio);
        return ocurrioCambio;
    }
    
    public void consultarObservacionByIdPrepago(Long idPrepago){
        logger.log(ADFLogger.WARNING, "Dentro de consultarObservacionByIdPrepago con el parametro idPrepago : "+idPrepago);
        //variable consultarObservacionPrepagoVO
        ConsultarObservacionPrepagoVOImpl consultarObservacionPrepagoVOImpl = this.getConsultarObservacionPrepagoVO();
        //variable formularioObservacionPrepagoVO
        FormularioObservacionPrepagoVOImpl formularioObservacionPrepagoVOImpl = this.getFormularioObservacionPrepagoVO();
        //variables temporales que se recuperan de  consultarObservacionPrepagoVO
        Integer idTcaTareaBpm = null;
        String loginUsuario = null;
        String nombreUsuario = null;
        String observacion = null;
        Long idObservacion = null;
        try{
            consultarObservacionPrepagoVOImpl.setpIdPrepago(idPrepago);
            consultarObservacionPrepagoVOImpl.executeQuery();
            if(null != consultarObservacionPrepagoVOImpl.getRowAtRangeIndex(0)){
                Row rowConsultarObservacionPrepagoVO = consultarObservacionPrepagoVOImpl.getRowAtRangeIndex(0);
                idTcaTareaBpm = (null != rowConsultarObservacionPrepagoVO.getAttribute(ConsultarObservacionPrepagoVORowImpl.IDTCATAREABPM)) 
                        ? (Integer)rowConsultarObservacionPrepagoVO.getAttribute(ConsultarObservacionPrepagoVORowImpl.IDTCATAREABPM) : null;
                loginUsuario = (null != rowConsultarObservacionPrepagoVO.getAttribute(ConsultarObservacionPrepagoVORowImpl.LOGINUSUARIO)) 
                        ? (String)rowConsultarObservacionPrepagoVO.getAttribute(ConsultarObservacionPrepagoVORowImpl.LOGINUSUARIO) : null;
                nombreUsuario = (null != rowConsultarObservacionPrepagoVO.getAttribute(ConsultarObservacionPrepagoVORowImpl.NOMBREUSUARIO)) 
                        ? (String)rowConsultarObservacionPrepagoVO.getAttribute(ConsultarObservacionPrepagoVORowImpl.NOMBREUSUARIO) : null;
                observacion = (null != rowConsultarObservacionPrepagoVO.getAttribute(ConsultarObservacionPrepagoVORowImpl.OBSERVACION)) 
                        ? (String)rowConsultarObservacionPrepagoVO.getAttribute(ConsultarObservacionPrepagoVORowImpl.OBSERVACION) : null;
                idObservacion = (null != rowConsultarObservacionPrepagoVO.getAttribute(ConsultarObservacionPrepagoVORowImpl.ID)) 
                        ? (Long)rowConsultarObservacionPrepagoVO.getAttribute(ConsultarObservacionPrepagoVORowImpl.ID) : null;
                //insertamos los valores recuperados en FormularioObservacionPrepagoVO
                formularioObservacionPrepagoVOImpl.crearRowFormularioObservacionPrepagoConValores(idTcaTareaBpm,loginUsuario,nombreUsuario, 
                                                                                                    observacion,idObservacion);
            }else{
                logger.log(ADFLogger.WARNING, "Criterio de busqueda vacio, con el idPrepago : "+idPrepago);
            }
        }catch(Exception ex){
            logger.log(ADFLogger.ERROR, "Error en consultarObservacionByIdPrepago.", ex);
        }
    }
    
    public void consultarPrepagoById(Long idPrepago){
        logger.log(ADFLogger.WARNING, "Dentro de consultarPrepagoById con el parametro idPrepago : "+idPrepago);
        //variable de prepagoVO 
        PrepagoVOImpl prepagoVOImpl = this.getPrepagoVO();
        //variable de formularioPrepagoVO
        FormularioPrepagoVOImpl formularioPrepagoVOImpl = this.getFormularioPrepagoVO();
        //variables temporales que se recuperan del criterio de busqueda
        Long id = null;
        Timestamp fechaSolicitud = null;
        Timestamp fechaPrepago = null;
        Timestamp fechaRenovacion = null;
        BigDecimal montoPrepago = null;
        Integer idTcaTipoMoneda = null;
        Integer idTcaTipoResolucion = null;
        Integer idTcaTipoPrepago = null;
        Integer idTcaTipoCaptura = null;
        
        try {
            ViewCriteria criteria =prepagoVOImpl.getViewCriteriaManager().getViewCriteria("PrepagoVOCriteriaByIdPrepago");
            criteria.ensureVariableManager().setVariableValue("idPrepago", idPrepago);
            prepagoVOImpl.applyViewCriteria(criteria);
            prepagoVOImpl.executeQuery();
            if(null != prepagoVOImpl.getRowAtRangeIndex(0)){
                Row rowPrepagoVO = prepagoVOImpl.getRowAtRangeIndex(0);
                id = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.ID)) 
                        ? (Long)rowPrepagoVO.getAttribute(PrepagoVORowImpl.ID) : null;
                fechaSolicitud = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.FECHASOLICITUD)) 
                        ? (Timestamp)rowPrepagoVO.getAttribute(PrepagoVORowImpl.FECHASOLICITUD) : null;
                fechaPrepago = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.FECHAPREPAGO)) 
                        ? (Timestamp)rowPrepagoVO.getAttribute(PrepagoVORowImpl.FECHAPREPAGO) : null;
                fechaRenovacion = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.FECHARENOVACION)) 
                        ? (Timestamp)rowPrepagoVO.getAttribute(PrepagoVORowImpl.FECHARENOVACION) : null;
                montoPrepago = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.MONTOPREPAGO)) 
                        ? (BigDecimal)rowPrepagoVO.getAttribute(PrepagoVORowImpl.MONTOPREPAGO) : null;
                idTcaTipoMoneda = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPOMONEDA)) 
                        ? (Integer)rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPOMONEDA) : null;
                idTcaTipoResolucion = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPORESOLUCION)) 
                        ? (Integer)rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPORESOLUCION) : null;
                idTcaTipoPrepago = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPOPREPAGO)) 
                        ? (Integer)rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPOPREPAGO) : null;
                idTcaTipoCaptura = (null != rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPOCAPTURA)) 
                        ? (Integer)rowPrepagoVO.getAttribute(PrepagoVORowImpl.IDTCATIPOCAPTURA) : null;
                
                formularioPrepagoVOImpl.crearRowFormularioPrepagoConValores(fechaSolicitud,fechaPrepago,fechaRenovacion, 
                                                                                montoPrepago,idTcaTipoMoneda, 
                                                                                    idTcaTipoResolucion,idTcaTipoPrepago,
                                                                                        idTcaTipoCaptura,id);
            }else{
                
                logger.log(ADFLogger.WARNING, "Criterio de busqueda vacio, con el idPrepago : "+idPrepago);
            }
            
        } catch (Exception ex) {
            logger.log(ADFLogger.ERROR, "Error en consultarPrepagoById.", ex);
        } finally {
            //This takes care of removing the applied VC.
            prepagoVOImpl.getViewCriteriaManager().removeApplyViewCriteriaName("PrepagoVOCriteriaByIdPrepago");
        }
    }
    
    public void insertarFormulariosDetallePenalidad() {
        logger.log(ADFLogger.WARNING, "Into insertarFormulariosDetallePenalidad");
        //variable de la tabla formularioDetallePenalidadVO
        FormularioDetallePenalidadVOImpl formularioDetallePenalidadVOImpl = this.getFormularioDetallePenalidadVO();
        //variable para realizar insert en el modelo
        DetallePenalidadVOImpl detallePenalidadVOImpl = this.getDetallePenalidadVO();
        
        formularioDetallePenalidadVOImpl.setRangeSize(-1);
        Row[] rowDetallePenalidad = formularioDetallePenalidadVOImpl.getAllRowsInRange();
        //recorrer todos los 'row´s'
        try {
            logger.log(ADFLogger.WARNING, "Comienza validaciones de rows.");
            if (null != rowDetallePenalidad) {
                for (Row row : rowDetallePenalidad) {
                    //varaibles temporales 'detalle_penalidad'
                    Long idDetallePenalidad = null;
                    Long idTrePrepagoContrato = null;
                    Timestamp fechaInicio = null;
                    Timestamp fechaFin = null;
                    String fraccionLibor = null;
                    BigDecimal intereses = null;
                    BigDecimal montoPenalidad = null;
                    BigDecimal plazo = null;
                    BigDecimal spread = null;
                    BigDecimal tasaPrepago = null;
                    
                    logger.log(ADFLogger.WARNING, "Entra a recorrer row a row el Detalle de la penalidad.");
                    if (null != row.getAttribute(FormularioDetallePenalidadVORowImpl.IDTREPREPAGOCONTRATO))
                        idTrePrepagoContrato =
                            (Long) row.getAttribute(FormularioDetallePenalidadVORowImpl.IDTREPREPAGOCONTRATO);

                    if (null != row.getAttribute(FormularioDetallePenalidadVORowImpl.FECHAINICIO))
                        fechaInicio = (Timestamp) row.getAttribute(FormularioDetallePenalidadVORowImpl.FECHAINICIO);

                    if (null != row.getAttribute(FormularioDetallePenalidadVORowImpl.FECHAFIN))
                        fechaFin = (Timestamp) row.getAttribute(FormularioDetallePenalidadVORowImpl.FECHAFIN);

                    if (null != row.getAttribute(FormularioDetallePenalidadVORowImpl.FRACCIONLIBOR))
                        fraccionLibor = (String) row.getAttribute(FormularioDetallePenalidadVORowImpl.FRACCIONLIBOR);

                    if (null != row.getAttribute(FormularioDetallePenalidadVORowImpl.INTERESES))
                        intereses = (BigDecimal) row.getAttribute(FormularioDetallePenalidadVORowImpl.INTERESES);

                    if (null != row.getAttribute(FormularioDetallePenalidadVORowImpl.MONTOPENALIDAD))
                        montoPenalidad =
                            (BigDecimal) row.getAttribute(FormularioDetallePenalidadVORowImpl.MONTOPENALIDAD);

                    if (null != row.getAttribute(FormularioDetallePenalidadVORowImpl.PLAZO))
                        plazo = (BigDecimal) row.getAttribute(FormularioDetallePenalidadVORowImpl.PLAZO);

                    if (null != row.getAttribute(FormularioDetallePenalidadVORowImpl.SPREAD))
                        spread = (BigDecimal) row.getAttribute(FormularioDetallePenalidadVORowImpl.SPREAD);

                    if (null != row.getAttribute(FormularioDetallePenalidadVORowImpl.TASAPREPAGO))
                        tasaPrepago = (BigDecimal) row.getAttribute(FormularioDetallePenalidadVORowImpl.TASAPREPAGO);

                    idDetallePenalidad =
                        detallePenalidadVOImpl.insertarRowDetallePenalidad(idTrePrepagoContrato, fechaInicio, fechaFin,
                                                                           fraccionLibor, intereses, montoPenalidad,
                                                                           plazo, spread, tasaPrepago);

                    logger.log(ADFLogger.WARNING,
                               "ID value of the table 'DETALLE_PENALIDAD' is : " + idDetallePenalidad);
                }
            } else {
                logger.log(ADFLogger.WARNING, "El arreglo de rows es nulo.");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al insertar el Detalle de la penalidad", e);
        }
        logger.log(ADFLogger.WARNING, "Termina metodo correctamente. para insertar Rows");
    }
    
    public void insertarFormulariosCalculoInteres(){
        logger.log(ADFLogger.WARNING,"Into insertarFormularioCalculoInteres");
        //variable de la tabla formularioCalculoInteresesVO
        FormularioCalculoInteresesVOImpl formularioCalculoInteresesVOImpl = this.getFormularioCalculoInteresesVO();
        //variable para realizar insert en el modelo 
        CalculoInteresesVOImpl calculoInteresesVOImpl = this.getCalculoInteresesVO();
        //variables temporables 'CalculoIntereses'
        Long idCalculoIntereses = null;
        Long idTreRprepagoContrato = null;
        String base = null;
        BigDecimal tasa = null;
        Timestamp desde = null;
        Timestamp hasta = null;
        Integer dias = null;
        BigDecimal intereses = null;
        
        formularioCalculoInteresesVOImpl.setRangeSize(-1);
        Row[] rowCalculoInteres = formularioCalculoInteresesVOImpl.getAllRowsInRange();
        //recorrer todos los 'row´s'
        try {
            if (null != rowCalculoInteres) {
                for (Row row : rowCalculoInteres) {

                    if (null != row.getAttribute(FormularioCalculoInteresesVORowImpl.IDTREPREPAGOCONTRATO))
                        idTreRprepagoContrato =
                            (Long) row.getAttribute(FormularioCalculoInteresesVORowImpl.IDTREPREPAGOCONTRATO);

                    if (null != row.getAttribute(FormularioCalculoInteresesVORowImpl.BASE))
                        base = (String) row.getAttribute(FormularioCalculoInteresesVORowImpl.BASE);

                    if (null != row.getAttribute(FormularioCalculoInteresesVORowImpl.TASA))
                        tasa = (BigDecimal) row.getAttribute(FormularioCalculoInteresesVORowImpl.TASA);

                    if (null != row.getAttribute(FormularioCalculoInteresesVORowImpl.DESDE))
                        desde = (Timestamp) row.getAttribute(FormularioCalculoInteresesVORowImpl.DESDE);

                    if (null != row.getAttribute(FormularioCalculoInteresesVORowImpl.HASTA))
                        hasta = (Timestamp) row.getAttribute(FormularioCalculoInteresesVORowImpl.HASTA);

                    if (null != row.getAttribute(FormularioCalculoInteresesVORowImpl.DIAS))
                        dias = (Integer) row.getAttribute(FormularioCalculoInteresesVORowImpl.DIAS);

                    if (null != row.getAttribute(FormularioCalculoInteresesVORowImpl.INTERESES))
                        intereses = (BigDecimal) row.getAttribute(FormularioCalculoInteresesVORowImpl.INTERESES);

                    //realizar los 'n' insert a la tabla 'CALCULO_INTERESES'
                    idCalculoIntereses =
                        calculoInteresesVOImpl.insertarRowCalculoIntereses(idTreRprepagoContrato, base, tasa, desde,
                                                                           hasta, dias, intereses);

                    logger.log(ADFLogger.WARNING,
                               "ID value of the table 'CALCULO_INTERESES' is : " + idCalculoIntereses);

                }
            } else {
                logger.log(ADFLogger.WARNING, "El arreglo de Rows es nulo.");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al insertar los calculos de intereses", e);
        }
    }
    
    public Long insertarFormulariosPrepago(){
        logger.log(ADFLogger.WARNING,"Into insertarFormulariosPrepago");
        //variable de los formularios que se muestran en la vista
        FormularioPrepagoVORowImpl formularioPrepagoVORowImpl = (FormularioPrepagoVORowImpl) this.getFormularioPrepagoVO().getCurrentRow();
        FormularioObservacionPrepagoVORowImpl formularioObservacionPrepagoVORowImpl = (FormularioObservacionPrepagoVORowImpl)this.getFormularioObservacionPrepagoVO().getCurrentRow();
        ContratoDesembolsoPrepagoVOImpl contratoDesembolsoPrepagoVOImpl = this.getContratoDesembolsoPrepagoVO();
        //variable para realisar insert en el modelo, estas VO´s estan basadas en una EO
        PrepagoVOImpl prepagoVOImpl=this.getPrepagoVO();
        ObservacionPrepagoVOImpl observacionPrepagoVOImpl = this.getObservacionPrepagoVO();
        TrePrepagoContratoVOImpl trePrepagoContratoVOImpl = this.getTrePrepagoContratoVO();



        //variable del 'id' de la tabla 'OBSERVACION_PREPAGO'
        Long idObservacionPrepago = null;
        //variable del 'id' de la tabla 'PREPAGO'
        Long idPrepago = null;
        //variable del 'id' de la tabla 'TRE_PREPAGO_CONTRATO'
        Long idTrePrepagoContrato = null;
        //variables temporales 'formularioObservacionPrepago'
        Integer idTcaTareaBpm = null;
        String observacion = null;
        String loginUsuario = null;
        String nombreUsuario = null;
        //variables temporales 'formularioPrepago'
        Timestamp fechaSolicitud = null;
        Timestamp fechaPrepago = null;
        Timestamp fechaRenovacion = null;
        BigDecimal montoPrepago = null;
        Integer idTcaTipoMoneda = null;
        Integer idTcaTipoResolucion = null;
        Integer idTcaTipoPrepago = null;
        Integer idTcaTipoCaptura = null;
        //variables temporables 'Contratos de desembolsos'
        BigDecimal capitalNoVencido = null;
        Integer esPagoTotal = null;
        Timestamp fechaProximoPago = null;
        Long idContratoDesembolso = null;
        Integer idTcaTipoMonedaContratoDesembolso = null;
        BigDecimal montoPrepagoContratoDesembolso = null;
        String contratoFlexcube = null;
        
        //validar que los formularios de la vista no sean null
        if(null != formularioPrepagoVORowImpl && null != formularioObservacionPrepagoVORowImpl){ 
            //recuperar los valorés de 'rowFormularioObservacionPrepago'
            if(null != formularioObservacionPrepagoVORowImpl.getIdTcaTareaBpm()){
                idTcaTareaBpm = formularioObservacionPrepagoVORowImpl.getIdTcaTareaBpm();
                                                    }
            if(null != formularioObservacionPrepagoVORowImpl.getObservacion()){
                observacion = formularioObservacionPrepagoVORowImpl.getObservacion();
                                                    }
            if(null != formularioObservacionPrepagoVORowImpl.getLoginUsuario()){
                loginUsuario = formularioObservacionPrepagoVORowImpl.getLoginUsuario();
                                                    }
            if(null != formularioObservacionPrepagoVORowImpl.getNombreUsuario()){
                nombreUsuario = formularioObservacionPrepagoVORowImpl.getNombreUsuario();
                                                    }
            //Crear el registro en 'OBSERVACION_PREPAGO' y obtener el 'id' que se creo
            idObservacionPrepago = observacionPrepagoVOImpl.crearObservacionPrepago(idTcaTareaBpm,observacion,
                                                                                        loginUsuario,nombreUsuario);
            
            logger.log(ADFLogger.WARNING,"ID value of the table 'OBSERVACION_PREPAGO' is : " + idObservacionPrepago);
            
            //recuperar los valores de 'rowFormularioPrepago'
            if(null != formularioPrepagoVORowImpl.getFechaSolicitud()){
                fechaSolicitud = formularioPrepagoVORowImpl.getFechaSolicitud();
            }
            
            if(null != formularioPrepagoVORowImpl.getFechaPrepago()){
                fechaPrepago = formularioPrepagoVORowImpl.getFechaPrepago();
            }
            
            if(null != formularioPrepagoVORowImpl.getMontoPrepago()){
                montoPrepago = formularioPrepagoVORowImpl.getMontoPrepago();
            }
            
            if(null != formularioPrepagoVORowImpl.getFechaRenovacion()){
                fechaRenovacion = formularioPrepagoVORowImpl.getFechaRenovacion();
            }
            
            if(null != formularioPrepagoVORowImpl.getIdTcaTipoResolucion()){
                idTcaTipoResolucion = formularioPrepagoVORowImpl.getIdTcaTipoResolucion();
            }
            
            if(null != formularioPrepagoVORowImpl.getIdTcaTipoPrepago()){
                idTcaTipoPrepago = formularioPrepagoVORowImpl.getIdTcaTipoPrepago();
            }
            
            if(null != formularioPrepagoVORowImpl.getIdTcaTipoCaptura()){
                idTcaTipoCaptura = formularioPrepagoVORowImpl.getIdTcaTipoCaptura();
            }
            
            if(null != formularioPrepagoVORowImpl.getIdTcaTipoMoneda()){
                idTcaTipoMoneda = formularioPrepagoVORowImpl.getIdTcaTipoMoneda();
            }
            
            //Crear el registro en 'PREPAGO'
            idPrepago = prepagoVOImpl.crearPrepago(fechaSolicitud,fechaPrepago,montoPrepago,fechaRenovacion,
                                                        idTcaTipoResolucion,idTcaTipoPrepago,idTcaTipoCaptura,
                                                            idTcaTipoMoneda,idObservacionPrepago);
            
            logger.log(ADFLogger.WARNING,"ID value of the table  'PREPAGO' is : "+idPrepago);
            
            //recuperar la lista de registros de la tabla 'Contratos de desembolsos', todos los que tengan 'check pagoTotal' o
            //se alla echo una modificacion en el campo 'montoPrepago' a nivel de la vista.
            contratoDesembolsoPrepagoVOImpl.setRangeSize(-1);
            logger.log(ADFLogger.WARNING,"row count in  contratoDesembolsoPrepagoVO is : " + 
                                                                contratoDesembolsoPrepagoVOImpl.getRowCount());
            Row[] rowContratoDesembolsoPrepago = contratoDesembolsoPrepagoVOImpl.getAllRowsInRange();
            //recorrer todos los 'row´s'
            for (Row row : rowContratoDesembolsoPrepago) {
                BigDecimal comparateBigDecimal = new BigDecimal("0");
                //Si el row tiene un 'montoPrepago' diferente a 0, es decir que sufrio una modificacion y es necesario
                //insertarlo en la base de datos.
                if(!comparateBigDecimal.equals(row.getAttribute("MontoPrepago"))&&
                        null != row.getAttribute("MontoPrepago")){
                    //recuperar los valores del Row
                    if(null != row.getAttribute("CapitalNoVencido"))
                        capitalNoVencido = (BigDecimal) row.getAttribute("CapitalNoVencido");
                    if(null != row.getAttribute("EsPagoTotal"))
                        esPagoTotal =  ((Boolean)row.getAttribute("EsPagoTotal")) ? new Integer(1):new Integer(0);
                    if(null != row.getAttribute("FechaProximoPago"))
                        fechaProximoPago = (Timestamp) row.getAttribute("FechaProximoPago");
                    if(null != row.getAttribute("ContratoDesembolsoFlexcube"))
                        contratoFlexcube = (String) row.getAttribute("ContratoDesembolsoFlexcube");
                    if(null != row.getAttribute("IdTcaTipoMoneda"))
                        idTcaTipoMonedaContratoDesembolso = (Integer) row.getAttribute("IdTcaTipoMoneda");
                    if(null != row.getAttribute("MontoPrepago"))
                        montoPrepagoContratoDesembolso = (BigDecimal) row.getAttribute("MontoPrepago");
                    if(null != row.getAttribute("IdContratoDesembolsoFenix"))
                        idContratoDesembolso = (Long) row.getAttribute("IdContratoDesembolsoFenix");
                    //realizar los 'n' insert a la tabla 'TRE_PREPAGO_CONTRATO', se insertaran tantos registros se allan 
                    //modificado en la vista.
                    idTrePrepagoContrato = trePrepagoContratoVOImpl.crearTrePrepagoContrato(capitalNoVencido, esPagoTotal,
                                                                    fechaProximoPago,idContratoDesembolso, idPrepago, 
                                                                            idTcaTipoMonedaContratoDesembolso, 
                                                                                montoPrepagoContratoDesembolso,
                                                                                    contratoFlexcube);
                    
                    logger.log(ADFLogger.WARNING,"ID value of the table 'TRE_PREPAGO_CONTRATO' is : "+idTrePrepagoContrato);
                    
                }
            }
                                       
           
        }else{
            if(null == formularioPrepagoVORowImpl){
                logger.log(ADFLogger.ERROR,"formularioPrepagoVORowImpl is null.");
                }
            if(null == formularioObservacionPrepagoVORowImpl){
                logger.log(ADFLogger.ERROR,"formularioObservacionPrepagoVORowImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"Row in prepagoVo no insert.");
            logger.log(ADFLogger.ERROR,"Row in ObservacionPrepagoVo no insert.");
        }
        
        logger.log(ADFLogger.WARNING,"successful method insertarFormulariosPrepago, value idPrepago is : "+idPrepago);
        return idPrepago;
    }

    public boolean insertarFormularioCargoPrepago(){
        boolean exito = Boolean.FALSE;
        logger.log(ADFLogger.WARNING,"Into insertarFormularioCargoPrepago");
        
        FormularioObservacionCargoPrepagoVOImpl formularioObservacionCargoPrepagoVOImpl = this.getFormularioObservacionCargoPrepagoVO();
        CargoPrepagoVOImpl cargoPrepagoVOImpl=this.getCargoPrepagoVO();
        ObservacionPrepagoVOImpl observacionPrepagoVOImpl = this.getObservacionPrepagoVO();
        
        Row rowFormularioObservacionCargoPrepago = formularioObservacionCargoPrepagoVOImpl.getCurrentRow();

        
        Integer idTcaTareaBpm = null;
        String observacion = null;
        String loginUsuario = null;
        String nombreUsuario = null;
        
        Long idCargoPrepago = null;
        
        Long idPrepago = null; 
        Integer idTcaRolBpm = null;
        Integer aplicaCargoAdicional = null; 
        Timestamp fechaReferencia = null; 
        Integer idTcaTipoMonedaTramite = null;
        Timestamp fechaVigentePenalizacion = null;
        BigDecimal montoCargoTramite = null;
        BigDecimal prime = null; 
        BigDecimal tasaLibor = null;
        BigDecimal montoCargo = null; 
        Integer idTcaTipoMoneda = null; 
        Long idObservacionPrepago = null;
        
        if(rowFormularioObservacionCargoPrepago != null){
            
            //FORMULARIO OBSERVACION CARGO PREPAGO
            
            if(rowFormularioObservacionCargoPrepago.getAttribute(
                                                    FormularioObservacionCargoPrepagoVORowImpl.APLICACARGOADICIONAL) != null){
                aplicaCargoAdicional = (Integer) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.APLICACARGOADICIONAL);
                                                    }
            if(rowFormularioObservacionCargoPrepago.getAttribute(
                                                    FormularioObservacionCargoPrepagoVORowImpl.DETERMINARMONTO) != null){
                montoCargo = (BigDecimal) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.DETERMINARMONTO);
                                                    }
            if(rowFormularioObservacionCargoPrepago.getAttribute(
                                                    FormularioObservacionCargoPrepagoVORowImpl.TIPOMONEDA) != null){
                idTcaTipoMoneda = (Integer) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.TIPOMONEDA);
                                                    }
            if(rowFormularioObservacionCargoPrepago.getAttribute(
                                                    FormularioObservacionCargoPrepagoVORowImpl.OBSERVACION) != null){
                observacion = (String) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.OBSERVACION);
                                                    }
            if(rowFormularioObservacionCargoPrepago.getAttribute(
                                                    FormularioObservacionCargoPrepagoVORowImpl.IDTCATAREABPM) != null){
                idTcaTareaBpm = (Integer) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.IDTCATAREABPM);
                                                    }
            if(rowFormularioObservacionCargoPrepago.getAttribute(
                                                    FormularioObservacionCargoPrepagoVORowImpl.LOGINUSUARIO) != null){
                loginUsuario = (String) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.LOGINUSUARIO);
                                                    }
            if(rowFormularioObservacionCargoPrepago.getAttribute(
                                                    FormularioObservacionCargoPrepagoVORowImpl.NOMBREUSUARIO) != null){
                nombreUsuario = (String) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.NOMBREUSUARIO);
                                                    }
            if(rowFormularioObservacionCargoPrepago.getAttribute(
                                                    FormularioObservacionCargoPrepagoVORowImpl.IDTCAROLBPM) != null){
                idTcaRolBpm = (Integer) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.IDTCAROLBPM);
                                                    }
            if(rowFormularioObservacionCargoPrepago.getAttribute(
                                                    FormularioObservacionCargoPrepagoVORowImpl.IDPREPAGO) != null){
                idPrepago = (Long) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.IDPREPAGO);
                                                    }
            
            // Se agregan al calcular el detalle de penalidad
            if(rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.MONTOCARGOTRAMITE) != null){
                montoCargoTramite = (BigDecimal) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.MONTOCARGOTRAMITE);
            }
            
            if(rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.IDTCATIPOMONEDATRAMITE) != null){
                idTcaTipoMonedaTramite = (Integer) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.IDTCATIPOMONEDATRAMITE);
            }
            
            if(rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.FECHAVIGENTEPENALIZACION) != null){
                fechaVigentePenalizacion = (Timestamp) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.FECHAVIGENTEPENALIZACION);
            }
            
            if(rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.FECHAREFERENCIA) != null){
                fechaReferencia = (Timestamp) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.FECHAREFERENCIA);
            }
            
            if(rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.TASALIBOR) != null){
                tasaLibor = (BigDecimal) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.TASALIBOR);
            }
            
            if(rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.PRIME) != null){
                prime = (BigDecimal) rowFormularioObservacionCargoPrepago.getAttribute(FormularioObservacionCargoPrepagoVORowImpl.PRIME);
            }
            
            idObservacionPrepago = observacionPrepagoVOImpl.crearObservacionPrepago(idTcaTareaBpm, observacion, loginUsuario, nombreUsuario);                   
            logger.log(ADFLogger.WARNING,"El valor del ID Observacion_ Prepago es : " + idObservacionPrepago);
            
            //Invocamos el ultimo insert que realiza el commit general de la tarea
            idCargoPrepago = cargoPrepagoVOImpl.insertarRowCargoPrepago(idPrepago, idTcaRolBpm, aplicaCargoAdicional, 
                                        fechaReferencia, idTcaTipoMonedaTramite, fechaVigentePenalizacion,
                                        montoCargoTramite, prime, tasaLibor,
                                        montoCargo, idTcaTipoMoneda, idObservacionPrepago);
            
            //Validamos que el commit general de la tarea se haya realizado con exito
            if (idCargoPrepago != null) {
                exito = Boolean.TRUE;
            }
        }else{
            if(null == rowFormularioObservacionCargoPrepago){
                logger.log(ADFLogger.ERROR,"formularioObservacionCargoPrepagoVOImpl.getCurrentRow() is null.");
                }
            logger.log(ADFLogger.ERROR,"No se inserto el row en formularioObservacionCargoPrepagoVOImpl.");
        }
        logger.log(ADFLogger.WARNING, "El metodo para insertar Cargo Prepago finalizo correctamente.");
        
        return exito;
    }
    
    public void actualizarFormularioCargoPrepago(){
        logger.log(ADFLogger.WARNING,"INTO actualizarFormularioCargoPrepago");
        
        FormularioObservacionCargoPrepagoVORowImpl formularioObservacionCargoPrepagoRowVOImpl = 
            (FormularioObservacionCargoPrepagoVORowImpl)this.getFormularioObservacionCargoPrepagoVO().getCurrentRow();
        
        ObservacionPrepagoVOImpl observacionPrepagoVOImpl = this.getObservacionPrepagoVO();
        
        
        CargoPrepagoVOImpl cargoPrepagoVOImpl=this.getCargoPrepagoVO();
        
        Integer idTcaTareaBpm = null;
        String observacion = null;
        String loginUsuario = null;
        String nombreUsuario = null;
        
        Long idCargoPrepago = null;
        
        Long idPrepago = null; 
        Integer idTcaRolBpm = null;
        Integer aplicaCargoAdicional = null; 
        BigDecimal montoCargo = null; 
        Integer idTcaTipoMoneda = null; 
        Long idObservacionPrepago = null;
        
        Timestamp fechaVigentePenalizacion = null;
        BigDecimal montoCargoTramite = null;
        Integer idTcaTipoMonedaTramite = null;
        Timestamp fechaReferencia = null;
        BigDecimal prime = null; 
        BigDecimal tasaLibor = null;
        
        if(formularioObservacionCargoPrepagoRowVOImpl != null){
            
            //FORMULARIO OBSERVACION CARGO PREPAGO
            
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getIdCargoPrepago()){
                idCargoPrepago = formularioObservacionCargoPrepagoRowVOImpl.getIdCargoPrepago(); 
            }
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getAplicaCargoAdicional()){
                aplicaCargoAdicional = formularioObservacionCargoPrepagoRowVOImpl.getAplicaCargoAdicional();
                                                    }
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getDeterminarMonto()){
                montoCargo = formularioObservacionCargoPrepagoRowVOImpl.getDeterminarMonto();
                                                   }
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getTipoMoneda()){
                idTcaTipoMoneda = formularioObservacionCargoPrepagoRowVOImpl.getTipoMoneda();
                                                    }
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getObservacion()){
                observacion = formularioObservacionCargoPrepagoRowVOImpl.getObservacion();
                                                    }
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getIdTcaTareaBPM()){
                idTcaTareaBpm = formularioObservacionCargoPrepagoRowVOImpl.getIdTcaTareaBPM();
                                                    }
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getLoginUsuario()){
                loginUsuario = formularioObservacionCargoPrepagoRowVOImpl.getLoginUsuario();
                                                    }
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getNombreUsuario()){
                  nombreUsuario = formularioObservacionCargoPrepagoRowVOImpl.getNombreUsuario();
                                                    }
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getIdTcaRolBPM()){
                idTcaRolBpm = formularioObservacionCargoPrepagoRowVOImpl.getIdTcaRolBPM();
                                                    }
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getIdPrepago()){
                idPrepago = formularioObservacionCargoPrepagoRowVOImpl.getIdPrepago();
                                                    }
            
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getIdObservacion()){
                idObservacionPrepago = formularioObservacionCargoPrepagoRowVOImpl.getIdObservacion();
            }
            
            // Se agregan al calcular el detalle de penalidad
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getMontoCargoTramite()){
                montoCargoTramite = formularioObservacionCargoPrepagoRowVOImpl.getMontoCargoTramite();
            }
            
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getIdTcaTipoMonedaTramite()){
                idTcaTipoMonedaTramite = formularioObservacionCargoPrepagoRowVOImpl.getIdTcaTipoMonedaTramite();
            }

            if(null != formularioObservacionCargoPrepagoRowVOImpl.getFechaVigentePenalizacion()){
                fechaVigentePenalizacion = formularioObservacionCargoPrepagoRowVOImpl.getFechaVigentePenalizacion();
            }
            
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getFechaReferencia()){
                fechaReferencia = formularioObservacionCargoPrepagoRowVOImpl.getFechaReferencia();
            }
            
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getTasaLibor()){
                tasaLibor =  formularioObservacionCargoPrepagoRowVOImpl.getTasaLibor();
            }
            
            if(null != formularioObservacionCargoPrepagoRowVOImpl.getPrime()){
                prime = formularioObservacionCargoPrepagoRowVOImpl.getPrime();
            }
            
            observacionPrepagoVOImpl.actualizarObservacionPrepago(idObservacionPrepago, observacion);                   
            logger.log(ADFLogger.WARNING,"El valor del ID Observacion_ Prepago es : " + idObservacionPrepago);
            
            cargoPrepagoVOImpl.actualizarCargoPrepago(idCargoPrepago, aplicaCargoAdicional,
                                                        montoCargo, idTcaTipoMoneda, montoCargoTramite,
                                                            idTcaTipoMonedaTramite, fechaVigentePenalizacion,
                                                                fechaReferencia, tasaLibor, prime);
            
        }else{
            if(null == formularioObservacionCargoPrepagoRowVOImpl){
                logger.log(ADFLogger.ERROR,"obtenerObservacionCargoPrepagoVORowImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"obtenerObservacionCargoPrepagoVORowImpl es nulo.");
        }
        logger.log(ADFLogger.WARNING, "El metodo para actualizar Cargo Prepago finalizo correctamente.");
    }
    
    public Integer llenarFormularioCargoPrepago(){
        logger.log(ADFLogger.WARNING,"INTO actualizarFormularioCargoPrepago");
        
        FormularioObservacionCargoPrepagoVOImpl formularioObservacionCargoPrepagoVOImpl = this.getFormularioObservacionCargoPrepagoVO();
        
        ObtenerObservacionCargoPrepagoVORowImpl obtenerObservacionCargoPrepagoVORowImpl = (ObtenerObservacionCargoPrepagoVORowImpl)
            this.getObtenerObservacionCargoPrepagoVO().getRowAtRangeIndex(0);
        
        
        Integer idTcaTareaBpm = null;
        String observacion = null;
        String loginUsuario = null;
        String nombreUsuario = null;
        
        Long idCargoPrepago = null;
        
        Long idPrepago = null; 
        Integer idTcaRolBpm = null;
        Integer aplicaCargoAdicional = null; 
        BigDecimal montoCargo = null; 
        Integer idTcaTipoMoneda = null; 
        Long idObservacionPrepago = null;
        
        if(obtenerObservacionCargoPrepagoVORowImpl != null){
            
            //FORMULARIO OBSERVACION CARGO PREPAGO
            
            if(null != obtenerObservacionCargoPrepagoVORowImpl.getIdCargoPrepago()){
                idCargoPrepago = obtenerObservacionCargoPrepagoVORowImpl.getIdCargoPrepago(); 
            }
            if(null != obtenerObservacionCargoPrepagoVORowImpl.getAplicaCargoAdicional()){
                aplicaCargoAdicional = obtenerObservacionCargoPrepagoVORowImpl.getAplicaCargoAdicional();
                                                    }
            if(null != obtenerObservacionCargoPrepagoVORowImpl.getMontoCargo()){
                montoCargo = obtenerObservacionCargoPrepagoVORowImpl.getMontoCargo();
                                                   }
            if(null != obtenerObservacionCargoPrepagoVORowImpl.getIdTcaTipoMoneda()){
                idTcaTipoMoneda = obtenerObservacionCargoPrepagoVORowImpl.getIdTcaTipoMoneda();
                                                    }
            if(null != obtenerObservacionCargoPrepagoVORowImpl.getObservacion()){
                observacion = obtenerObservacionCargoPrepagoVORowImpl.getObservacion();
                                                    }
            if(null != obtenerObservacionCargoPrepagoVORowImpl.getIdTcaTareaBpm()){
                idTcaTareaBpm = obtenerObservacionCargoPrepagoVORowImpl.getIdTcaTareaBpm();
                                                    }
            if(null != obtenerObservacionCargoPrepagoVORowImpl.getLoginUsuario()){
                loginUsuario = obtenerObservacionCargoPrepagoVORowImpl.getLoginUsuario();
                                                    }
            if(null != obtenerObservacionCargoPrepagoVORowImpl.getNombreUsuario()){
                  nombreUsuario = obtenerObservacionCargoPrepagoVORowImpl.getNombreUsuario();
                                                    }
            if(null != obtenerObservacionCargoPrepagoVORowImpl.getIdTcaRolBpm()){
                idTcaRolBpm = obtenerObservacionCargoPrepagoVORowImpl.getIdTcaRolBpm();
                                                    }
            if(null != obtenerObservacionCargoPrepagoVORowImpl.getIdPrepago()){
                idPrepago = obtenerObservacionCargoPrepagoVORowImpl.getIdPrepago();
                                                    }
            
            if(null != obtenerObservacionCargoPrepagoVORowImpl.getIdObs()){
                idObservacionPrepago = obtenerObservacionCargoPrepagoVORowImpl.getIdObs();
            }
            
            formularioObservacionCargoPrepagoVOImpl.crearRowFormularioObservacionCargoPrepagoDatosCompletos(idCargoPrepago,idObservacionPrepago, aplicaCargoAdicional, montoCargo, idTcaTipoMoneda, 
                                                                       observacion, idPrepago, idTcaRolBpm, idTcaTareaBpm, loginUsuario, nombreUsuario);
        }else{
            if(null == obtenerObservacionCargoPrepagoVORowImpl){
                logger.log(ADFLogger.ERROR,"obtenerObservacionCargoPrepagoVORowImpl is null.");
                }
            logger.log(ADFLogger.ERROR,"obtenerObservacionCargoPrepagoVORowImpl es nulo.");
        }
        logger.log(ADFLogger.WARNING, "El metodo para llenar el Formulario de Cargo Prepago finalizo correctamente.");
        return aplicaCargoAdicional;
    }
    
    public Map cargarDatosConsolidadoPagoPrepago(Long idPrepago){
        logger.log(ADFLogger.WARNING, "INTO cargarDatosConsolidadoPagoPrepago.");
        
        ConsolidadoPagoPrepagoVOImpl consolidadoPagoPrepagoVOImpl = this.getConsolidadoPagoPrepagoVO();
        CalculoCargoPenalidadPrepagoVOImpl calculoCargoPenalidadPrepagoVOImpl = this.getCalculoCargoPenalidadPrepagoVO();
        CargoPrepagoVOImpl cargoPrepagoVOImpl = this.getCargoPrepagoVO();
        TipoMonedaCargoPrepagoLOVImpl tipoMonedaCargoPrepagoLOVImpl = this.getTipoMonedaCargoPrepagoLOV();
        
        Map<String,Object> atributosPenalidadPrepagoMap = new HashMap<String,Object>();
        Map<String,Object> atributosPrepagoMap = new HashMap<String,Object>();
        Map<String,Object> montoCargoPrepagoMap = new HashMap<String,Object>();
        Map<String,Object> descripcionMonedaPrepagoMap = new HashMap<String,Object>();
        Map<String,Object> valoresPrepago = new HashMap<String,Object>();
        //Se Declaran variables para el Monto del Prepago con el tipo de moneda
        BigDecimal montoPrepago = null;
        Integer idTcaTipoMonedaMontoPrepago = null;
        Integer idTcaTipoResolucion = null;
        //Se declaran variables para la penalidad y los intereses con el tipo de moneda
        BigDecimal interes = null;
        BigDecimal penalidad = null;
        Integer tipoMonedaPenalidad = null;
        //Se declaran variables para el Cargo del Prepago con el tipo de moneda
        BigDecimal totalCargoPrepagoDolares = null;
        BigDecimal totalCargoPrepagoLocal = null;
        Integer tipoMonedaCargoDolares = null;
        Integer tipoMonedaCargoLocal = null;
        Timestamp fechaVigentePenalizacion = null;
        
        String nombreMonedaLocal = null;
        String nombreMonedaDolar = null;
        
        try{
        //Se llama al metodo obtenerAtributosPrepago para obtener el mapa atributosPrepagoMap con el Monto del Prepago
         
                atributosPrepagoMap = obtenerAtributosPrepago(idPrepago);
                montoPrepago = (BigDecimal)atributosPrepagoMap.get("montoPrepago");
                idTcaTipoMonedaMontoPrepago = (Integer)atributosPrepagoMap.get("idTcaTipoMoneda");
                idTcaTipoResolucion = (Integer)atributosPrepagoMap.get("idTcaTipoResolucion");
            //Obtener la lista de monedas utilizadas en las tablas relacionadas al prepago
            
            descripcionMonedaPrepagoMap = tipoMonedaCargoPrepagoLOVImpl.obtenerDescripcionMonedaByIdPrepago(idPrepago);
            nombreMonedaLocal = (String)descripcionMonedaPrepagoMap.get("nombreMonedaLocal");
            nombreMonedaDolar = (String)descripcionMonedaPrepagoMap.get("nombreMonedaDolar");
        /*Se llama al metodo calculoCargoPenalidadPrepagoVOImpl.obtenerPenalidadPrepago para obtener el mapa atributosPenalidadPrepagoMap
         * con el monto total de la penalidad y el interes.
          */
                atributosPenalidadPrepagoMap = calculoCargoPenalidadPrepagoVOImpl.obtenerPenalidadPrepago(idPrepago);
                interes = (BigDecimal)atributosPenalidadPrepagoMap.get("totalMontoInteres");
                penalidad = (BigDecimal)atributosPenalidadPrepagoMap.get("totalMontoPenalidad");
                tipoMonedaPenalidad = (Integer)atributosPenalidadPrepagoMap.get("idTcaTipoMOneda");
        
        //Se llama al metodo cargoPrepagoVOImpl.obtenerCargoPrepagoByIdPrepago para obtener el mapa montoCargoPrepagoMap 
        // con el total de los montos de cada area.
        montoCargoPrepagoMap = cargoPrepagoVOImpl.obtenerCargoPrepagoByIdPrepago(idPrepago);
        totalCargoPrepagoDolares = (BigDecimal)montoCargoPrepagoMap.get("totalMontoCargoPrepagoDolares");
        tipoMonedaCargoDolares = (Integer)montoCargoPrepagoMap.get("idTipoMonedaDolares");
        totalCargoPrepagoLocal = (BigDecimal)montoCargoPrepagoMap.get("totalMontoCargoPrepagoLocal");
        tipoMonedaCargoLocal = (Integer)montoCargoPrepagoMap.get("idTipoMonedaLocal");
        fechaVigentePenalizacion = (Timestamp)montoCargoPrepagoMap.get("fechaVigentePenalizacion");
        
        //Se crea el Row con los monto Totales de cada Concepto.
//        consolidadoPagoPrepagoVOImpl.crearRowConsolidadoPago(montoPrepago, idTcaTipoMonedaMontoPrepago,interes,penalidad,
//                                                             tipoMonedaPenalidad,totalCargoPrepagoDolares,tipoMonedaCargoDolares,
//                                                             totalCargoPrepagoLocal,tipoMonedaCargoLocal,idTcaTipoResolucion);
            //nombreMonedaLocal,nombreMonedaDolar
            valoresPrepago.put("nombreMonedaLocal", nombreMonedaLocal);
            valoresPrepago.put("idTipoMonedaMontoPrepago", idTcaTipoMonedaMontoPrepago);
            valoresPrepago.put("fechaVigentePenalizacion", fechaVigentePenalizacion);
            valoresPrepago.put("idTcaTipoResolucion", idTcaTipoResolucion);
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en cargarDatosConsolidadoPagoPrepago.", e);
        }
        logger.log(ADFLogger.WARNING, "Termina Metodo cargarDatosConsolidadoPagoPrepago correctamente.");
        return valoresPrepago;
    }
    
    public Map cargarDatosConsolidadoPenalidad(Long idPrepago, Integer idTcaTipoResolucion){
        logger.warning("Entra en cargarDatosConsolidadoPenalidad.");
        Map<String,Object> map = new HashMap<String, Object>();
        Timestamp fechaVigentePenalizacion = null;
        ConsolidadoPagoPrepagoVOImpl consolidadoPagoPrepagoVOImpl = this.getConsolidadoPagoPrepagoVO();
        BigDecimal montoDolarConFuentes = new BigDecimal(0);
        String monedaConFuentes = null;
        try{
        map = consolidadoPagoPrepagoVOImpl.consultarConsolidadoPagoByIdPrepago(idPrepago, idTcaTipoResolucion);
        montoDolarConFuentes = (BigDecimal)map.get("montoDolarConFuentes");
        monedaConFuentes = (String)map.get("monedaConFuentes");
        
        fechaVigentePenalizacion = cargarDatosDetalleCargoPenalidadPrepago(idPrepago, montoDolarConFuentes, monedaConFuentes); 
            map.put("fechaVigentePenalizacion", fechaVigentePenalizacion);
        }catch(Exception e){
            logger.warning("Error en cargarDatosConsolidadoPenalidad.", e);
        }
        return map;
    }
    public Timestamp cargarDatosDetalleCargoPenalidadPrepago(Long idPrepago, BigDecimal montoDolarConFuentes, String monedaFuentes) {
        logger.log(ADFLogger.WARNING, "INTO cargarDatosDetalleCargoPenalidadPrepago.");
        FenixAMImpl fenixAMImpl = null;
        fenixAMImpl = (FenixAMImpl)this.getRootApplicationModule();
        
        DetalleCargoPenalidadPrepagoVOImpl detalleCargoPenalidadPrepagoVoImpl =
            this.getDetalleCargoPenalidadPrepagoVO();
        CalculoCargoPenalidadPrepagoVOImpl calculoCargoPenalidadPrepagoVOImpl =
            this.getCalculoCargoPenalidadPrepagoVO();
        CargoPrepagoVOImpl cargoPrepagoVOImpl = this.getCargoPrepagoVO();
        TipoMonedaCargoPrepagoLOVImpl tipoMonedaCargoPrepagoLOVImpl = this.getTipoMonedaCargoPrepagoLOV();

        Map<String, Object> atributosPenalidadPrepagoMap = new HashMap<String, Object>();
        Map<String, Object> descripcionMonedaPrepagoMap = new HashMap<String, Object>();
        //Map<String,Object> atributosPrepagoMap = new HashMap<String,Object>();
        Map<String, Object> montoCargoPrepagoMap = new HashMap<String, Object>();
        //Se declaran variables para la penalidad
        BigDecimal penalidad = null;
        String nombreMoneda = null;
        //Se declaran variables para el Cargo del Prepago con el tipo de moneda
        BigDecimal montoCargoCopres = new BigDecimal(0);
        Integer idTipoMonedaCopres = null;
        BigDecimal montoCargoDaeci = new BigDecimal(0);
        Integer idTipoMonedaDaeci = null;
        BigDecimal montoCargoMdc = new BigDecimal(0);
        Integer idTipoMonedaMdc = null;
        BigDecimal montoCargoSepri = new BigDecimal(0);
        Integer idTipoMonedaSepri = null;
        BigDecimal montoCargoCofi = new BigDecimal(0);
        Integer idTipoMonedaCofi = null;
        BigDecimal montoCargoTramiteCofi = new BigDecimal(0);
        Integer idTipoMonedaTramiteCofi = null;
        Timestamp fechaVigentePenalizacion = null;
        
        String nombreMonedaLocal = null;
        String nombreMonedaDolar = null;

        try {
            /*Se llama al metodo calculoCargoPenalidadPrepagoVOImpl.obtenerPenalidadPrepago para obtener el mapa atributosPenalidadPrepagoMap
             * con el monto total de la penalidad y el interes.
              */
            atributosPenalidadPrepagoMap = calculoCargoPenalidadPrepagoVOImpl.obtenerPenalidadPrepago(idPrepago);
            if (null != atributosPenalidadPrepagoMap.get("totalMontoPenalidad")) {
                penalidad = (BigDecimal) atributosPenalidadPrepagoMap.get("totalMontoPenalidad");
            } else {
                logger.log(ADFLogger.WARNING, "El valor de la penalidad es nulo.");
            }
            if (null != atributosPenalidadPrepagoMap.get("nombreMoneda")) {
                nombreMoneda = (String) atributosPenalidadPrepagoMap.get("nombreMoneda");
            } else {
                logger.log(ADFLogger.WARNING, "El nombre de la moneda es nulo.");
            }

            //Metodo para obtener la lista de la descripcion de la moneda de acuerdo al id del Prepago
            //tipoMonedaCargoPrepagoLOVImpl.setpIdPrepago(idPrepago);
            descripcionMonedaPrepagoMap = tipoMonedaCargoPrepagoLOVImpl.obtenerDescripcionMonedaByIdPrepago(idPrepago);
            if (null != descripcionMonedaPrepagoMap.get("nombreMonedaLocal")) {
                nombreMonedaLocal = (String) descripcionMonedaPrepagoMap.get("nombreMonedaLocal");
            } else {
                logger.log(ADFLogger.WARNING, "El nombre de la moneda local es nulo.");
            }
            if (null != descripcionMonedaPrepagoMap.get("nombreMonedaDolar")) {
                nombreMonedaDolar = (String) descripcionMonedaPrepagoMap.get("nombreMonedaDolar");
            } else {
                logger.log(ADFLogger.WARNING, "El nombre de la moneda dolar es nulo.");
            }
            //Se llama al metodo cargoPrepagoVOImpl.obtenerCargoPrepagoByIdPrepago para obtener el mapa montoCargoPrepagoMap
            // con los cargos por cada area.
            montoCargoPrepagoMap = cargoPrepagoVOImpl.obtenerCargoPrepagoByIdPrepago(idPrepago);
            if (null != montoCargoPrepagoMap) {
                if (null != montoCargoPrepagoMap.get("montoCargoCopres")) {
                    montoCargoCopres = (BigDecimal) montoCargoPrepagoMap.get("montoCargoCopres");
                } else {
                    logger.log(ADFLogger.WARNING, "El valor de montoCargoCopres es nulo.");
                }
                if (null != montoCargoPrepagoMap.get("idTipoMonedaCopres")) {
                    idTipoMonedaCopres = (Integer) montoCargoPrepagoMap.get("idTipoMonedaCopres");
                } else {
                    logger.log(ADFLogger.WARNING, "El valor de idTipoMonedaCopres es nulo.");
                }
                if (null != montoCargoPrepagoMap.get("montoCargoDaeci")) {
                    montoCargoDaeci = (BigDecimal) montoCargoPrepagoMap.get("montoCargoDaeci");
                } else {
                    logger.log(ADFLogger.WARNING, "El valor de montoCargoDaeci es nulo.");
                }
                if (null != montoCargoPrepagoMap.get("idTipoMonedaDaeci")) {
                    idTipoMonedaDaeci = (Integer) montoCargoPrepagoMap.get("idTipoMonedaDaeci");
                } else {
                    logger.log(ADFLogger.WARNING, "El valor de idTipoMonedaDaeci es nulo.");
                }
                if (null != montoCargoPrepagoMap.get("montoCargoMdc")) {
                    montoCargoMdc = (BigDecimal) montoCargoPrepagoMap.get("montoCargoMdc");
                } else {
                    logger.log(ADFLogger.WARNING, "El valor de montoCargoMdc es nulo.");
                }
                if (null != montoCargoPrepagoMap.get("idTipoMonedaMdc")) {
                    idTipoMonedaMdc = (Integer) montoCargoPrepagoMap.get("idTipoMonedaMdc");
                } else {
                    logger.log(ADFLogger.WARNING, "El valor de idTipoMonedaMdc es nulo.");
                }
                if (null != montoCargoPrepagoMap.get("montoCargoSepri")) {
                    montoCargoSepri = (BigDecimal) montoCargoPrepagoMap.get("montoCargoSepri");
                } else {
                    logger.log(ADFLogger.WARNING, "El valor de montoCargoSepri es nulo.");
                }
                if (null != montoCargoPrepagoMap.get("idTipoMonedaSepri")) {
                    idTipoMonedaSepri = (Integer) montoCargoPrepagoMap.get("idTipoMonedaSepri");
                } else {
                    logger.log(ADFLogger.WARNING, "El valor de idTipoMonedaSepri es nulo.");
                }
                if (null != montoCargoPrepagoMap.get("montoCargoCofi")) {
                    montoCargoCofi = (BigDecimal) montoCargoPrepagoMap.get("montoCargoCofi");
                } else {
                    logger.log(ADFLogger.WARNING, "El valor de montoCargoCofi es nulo.");
                }
                if (null != montoCargoPrepagoMap.get("idTipoMonedaCofi")) {
                    idTipoMonedaCofi = (Integer) montoCargoPrepagoMap.get("idTipoMonedaCofi");
                } else {
                    logger.log(ADFLogger.WARNING, "El valor de idTipoMonedaCofi es nulo.");
                }
                if (null != montoCargoPrepagoMap.get("montoCargoTramiteCofi")) {
                    montoCargoTramiteCofi = (BigDecimal) montoCargoPrepagoMap.get("montoCargoTramiteCofi");
                } else {
                    logger.log(ADFLogger.WARNING, "El valor de montoCargoTramiteCofi es nulo.");
                }
                if (null != montoCargoPrepagoMap.get("idTipoMonedaTramiteCofi")) {
                    idTipoMonedaTramiteCofi = (Integer) montoCargoPrepagoMap.get("idTipoMonedaTramiteCofi");
                } else {
                    logger.log(ADFLogger.WARNING, "El valor de idTipoMonedaTramiteCofi es nulo.");
                }
                if (null != montoCargoPrepagoMap.get("fechaVigentePenalizacion")) {
                    fechaVigentePenalizacion = (Timestamp) montoCargoPrepagoMap.get("fechaVigentePenalizacion");
                } else {
                    logger.log(ADFLogger.WARNING, "El valor de fechaVigentePenalizacion es nulo.");
                }

            } else {
                logger.log(ADFLogger.WARNING, "El mapa montoCargoPrepagoMap es nulo.");
            }
            // Se valida la regla RN_02
            // Se realiza la comparacion de montos considerando que sean tipos de moneda diferente
            // Se agrega validacion si la variable 'idTipoMonedaDaeci' es null, no valide cuando
            // daeci no agrego un monto en cargoPrepago
            if(null != monedaFuentes && null != montoDolarConFuentes && null != idTipoMonedaDaeci ){
                
                String descripcionMonedaDaeci = getTcaTipoMonedaVO().obtenerDescripcionMoneda(idTipoMonedaDaeci);
                BigDecimal montoFuenteExterna = fenixAMImpl.convertirMonedasPrepago(FenixModelConstants.TIPO_MONEDA_USD,monedaFuentes,montoDolarConFuentes);
                BigDecimal montoDaeci = fenixAMImpl.convertirMonedasPrepago(FenixModelConstants.TIPO_MONEDA_USD,descripcionMonedaDaeci,montoCargoDaeci);
                if(montoFuenteExterna.compareTo(montoDaeci) == 1){
                    logger.info("Monto de DAECI se muestra en 0");
                    montoCargoDaeci = new BigDecimal(0);
                }else{
                    logger.warning("Se muestra el monto ingresdo por DAECI.");
                }
            
            }else{
                logger.severe("No es posible comparar el monto de fuente externa con el monto de DAECI");
            }
            //Se crea el Row con los monto Totales de cada Concepto.
            detalleCargoPenalidadPrepagoVoImpl.crearRowDetalleCargoPenalidadPrepago(penalidad, nombreMoneda,
                                                                                    montoCargoCopres,
                                                                                    idTipoMonedaCopres, montoCargoDaeci,
                                                                                    idTipoMonedaDaeci, montoCargoMdc,
                                                                                    idTipoMonedaMdc, montoCargoSepri,
                                                                                    idTipoMonedaSepri, montoCargoCofi,
                                                                                    idTipoMonedaCofi,
                                                                                    montoCargoTramiteCofi,
                                                                                    idTipoMonedaTramiteCofi,
                                                                                    nombreMonedaLocal,
                                                                                    nombreMonedaDolar);
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                       "Error en cargarDatosDetalleCargoPenalidadPrepago.", e);
        }
        logger.log(ADFLogger.WARNING, "Termina Metodo cargarDatosDetalleCargoPenalidadPrepago correctamente.");
        return fechaVigentePenalizacion;
    }
    
    public void actualizarFechaFormularioPrepago(){
        logger.log(ADFLogger.WARNING,"INTO actualizarFechaFormularioPrepago");
        
        FormularioPrepagoVORowImpl formularioPrepagoVORowImpl = 
                                   (FormularioPrepagoVORowImpl) this.getFormularioPrepagoVO().getCurrentRow();
        PrepagoVOImpl prepagoVOImpl = this.getPrepagoVO();
        
        Long idPrepago = null; 
        Timestamp fechaPrepago = null;
        
        if(null != formularioPrepagoVORowImpl) { 
            
            if(null != formularioPrepagoVORowImpl.getFechaPrepago()){
                fechaPrepago = formularioPrepagoVORowImpl.getFechaPrepago();
            }
            
            if(null != formularioPrepagoVORowImpl.getIdPrepago()){
                idPrepago = formularioPrepagoVORowImpl.getIdPrepago();
            }
            
            //Actualiza Fecha de prepago en 'PREPAGO'
            prepagoVOImpl.actualizarFechaPrepago(idPrepago, fechaPrepago);
           
        } else{
            if(null == formularioPrepagoVORowImpl){
                logger.warning("formularioPrepagoVORowImpl is null.");
            }
            logger.warning("Row in prepagoVo no insert.");
        }
        
        logger.warning("successful method insertarFormulariosPrepago, idPrepago: " + idPrepago);
    }
    
    public Long insertarFormularioGestionarCobertura() {
        logger.warning("Into insertarFormularioGestionarCobertura.");
        
        FormularioGestionarCoberturaVORowImpl formularioGestionarCoberturaVORowImpl = (FormularioGestionarCoberturaVORowImpl) this.getFormularioGestionarCoberturaVO().getCurrentRow();
        
        GestionarCoberturaVOImpl gestionarCoberturaVOImpl = this.getGestionarCoberturaVO();
        
        Long idGestionarCobertura = null;
        Long idPrepago = null;
        Integer existeDifCobertura = null;
        BigDecimal montoPagado = null;
        Integer idTcaTipoMonedaPagado = null;
        BigDecimal montoReal = null;
        Integer idTcaTipoMonedaReal = null;
        Timestamp fechaRegistro = null;
        
        if(null != gestionarCoberturaVOImpl){ 
            
            if(null != formularioGestionarCoberturaVORowImpl.getIdPrepago()) {
                idPrepago = formularioGestionarCoberturaVORowImpl.getIdPrepago();
            }
            if(null != formularioGestionarCoberturaVORowImpl.getExisteDifCobertura()) {
                existeDifCobertura = formularioGestionarCoberturaVORowImpl.getExisteDifCobertura();
            }
            if(null != formularioGestionarCoberturaVORowImpl.getMontoPagado()) {
                montoPagado = formularioGestionarCoberturaVORowImpl.getMontoPagado();
            }
            if(null != formularioGestionarCoberturaVORowImpl.getIdTcaTipoMonedaPagado()) {
                idTcaTipoMonedaPagado = formularioGestionarCoberturaVORowImpl.getIdTcaTipoMonedaPagado();
            }
            if(null != formularioGestionarCoberturaVORowImpl.getMontoReal()) {
                montoReal = formularioGestionarCoberturaVORowImpl.getMontoReal();
            }
            if(null != formularioGestionarCoberturaVORowImpl.getIdTcaTipoMonedaReal()) {
                idTcaTipoMonedaReal = formularioGestionarCoberturaVORowImpl.getIdTcaTipoMonedaReal();
            }
            if(null != formularioGestionarCoberturaVORowImpl.getFechaRegistro()) {
                fechaRegistro = formularioGestionarCoberturaVORowImpl.getFechaRegistro();
            }

            idGestionarCobertura = gestionarCoberturaVOImpl.crearGestionarCobertura(idPrepago, existeDifCobertura, montoPagado, idTcaTipoMonedaPagado,
                                                        montoReal,idTcaTipoMonedaReal,fechaRegistro);
            
            logger.warning("idGestionarCobertura: " + idGestionarCobertura);

        } else {
            if(null == formularioGestionarCoberturaVORowImpl){
                logger.log(ADFLogger.ERROR,"formularioPrepagoVORowImpl is null.");
                }
            logger.warning("Row in gestionarCoberturaVO no insert.");
        }
        
        logger.warning("successful method insertarFormularioGestionarCobertura, value idGestionarCobertura is: " + idGestionarCobertura);
        
        return idGestionarCobertura;
    }
    
    public Boolean consultarGestionarCoberturaById(Long idPrepago) {
        logger.warning("Into consultarGestionarCoberturaById.");
        
        GestionarCoberturaVOImpl gestionarCoberturaVOImpl = this.getGestionarCoberturaVO();
        FormularioGestionarCoberturaVOImpl formularioGestionarCoberturaVOImpl = (FormularioGestionarCoberturaVOImpl) this.getFormularioGestionarCoberturaVO();
        
        Boolean result = Boolean.FALSE;
        
        Long idGestionarCobertura = null;
        Integer existeDifCobertura = null;
        BigDecimal montoPagado = null;
        Integer idTcaTipoMonedaPagado = null;
        BigDecimal montoReal = null;
        Integer idTcaTipoMonedaReal = null;
        Timestamp fechaRegistro = null;
        
        try {
            
            ViewCriteria criteria = gestionarCoberturaVOImpl.getViewCriteriaManager().getViewCriteria("GestionarCoberturaVOCriteriaByIdPrepago");
            criteria.ensureVariableManager().setVariableValue("varIdPrepago", idPrepago);
            gestionarCoberturaVOImpl.applyViewCriteria(criteria);
            gestionarCoberturaVOImpl.executeQuery();
            
            if (null != gestionarCoberturaVOImpl.getRowAtRangeIndex(0)) {
                Row rowGestionarCoberturaVO = gestionarCoberturaVOImpl.getRowAtRangeIndex(0);
                
                idGestionarCobertura = (null != rowGestionarCoberturaVO.getAttribute(GestionarCoberturaVORowImpl.ID)) 
                        ? (Long)rowGestionarCoberturaVO.getAttribute(GestionarCoberturaVORowImpl.ID) : null;
                existeDifCobertura = (null != rowGestionarCoberturaVO.getAttribute(GestionarCoberturaVORowImpl.EXISTEDIFCOBERTURA)) 
                        ? (Integer)rowGestionarCoberturaVO.getAttribute(GestionarCoberturaVORowImpl.EXISTEDIFCOBERTURA) : null;
                montoPagado = (null != rowGestionarCoberturaVO.getAttribute(GestionarCoberturaVORowImpl.MONTOPAGADO)) 
                        ? (BigDecimal)rowGestionarCoberturaVO.getAttribute(GestionarCoberturaVORowImpl.MONTOPAGADO) : null;
                idTcaTipoMonedaPagado = (null != rowGestionarCoberturaVO.getAttribute(GestionarCoberturaVORowImpl.IDTCATIPOMONEDAPAGADO)) 
                        ? (Integer)rowGestionarCoberturaVO.getAttribute(GestionarCoberturaVORowImpl.IDTCATIPOMONEDAPAGADO) : null;
                montoReal = (null != rowGestionarCoberturaVO.getAttribute(GestionarCoberturaVORowImpl.MONTOREAL)) 
                        ? (BigDecimal)rowGestionarCoberturaVO.getAttribute(GestionarCoberturaVORowImpl.MONTOREAL) : null;
                idTcaTipoMonedaReal = (null != rowGestionarCoberturaVO.getAttribute(GestionarCoberturaVORowImpl.IDTCATIPOMONEDAREAL)) 
                        ? (Integer)rowGestionarCoberturaVO.getAttribute(GestionarCoberturaVORowImpl.IDTCATIPOMONEDAREAL) : null;
                fechaRegistro = (null != rowGestionarCoberturaVO.getAttribute(GestionarCoberturaVORowImpl.FECHAREGISTRO)) 
                        ? (Timestamp)rowGestionarCoberturaVO.getAttribute(GestionarCoberturaVORowImpl.FECHAREGISTRO) : null;
                
                formularioGestionarCoberturaVOImpl.crearRowFormularioGestionarCoberturaConValores(idGestionarCobertura, idPrepago, existeDifCobertura, montoPagado,
                                                                                                  idTcaTipoMonedaPagado, montoReal, idTcaTipoMonedaReal, fechaRegistro);
                result = Boolean.TRUE;
                logger.warning("Se encontro registro, con el idPrepago : " + idPrepago);
            } else {
                logger.warning("Criterio de busqueda vacio, con el idPrepago : " + idPrepago);
                result = Boolean.FALSE;
            }
            
        } catch (Exception ex) {
            logger.warning("Error en consultarPrepagoById.", ex);
        } finally {
            //This takes care of removing the applied VC.
            gestionarCoberturaVOImpl.getViewCriteriaManager().removeApplyViewCriteriaName("GestionarCoberturaVOCriteriaByIdPrepago");
        }
        return (result);
    }
    
    public void crearInicializarGestionarCobertura(Long idPrepago) {
        logger.warning("Into crearRowInicialGestionarCobertura.");
        
        FormularioGestionarCoberturaVOImpl formularioGestionarCoberturaVOImpl = (FormularioGestionarCoberturaVOImpl) this.getFormularioGestionarCoberturaVO();
        
        Map<String,Object> montosCargoPrepago = new HashMap<String,Object>();
        
        BigDecimal montoPagado = null;
        Integer idTcaTipoMonedaPagado = null;
        
        //Se ejecuta metodo de CargoPrepagoVO para obtener montos y tipos de moneda de "Coberturas (MDC)".
        CargoPrepagoVOImpl cargoPrepagoVOImpl = this.getCargoPrepagoVO();
        montosCargoPrepago = (Map) cargoPrepagoVOImpl.obtenerCargoPrepagoByIdPrepago(idPrepago);
        
        montoPagado = (BigDecimal) montosCargoPrepago.get("montoCargoMdc");
        idTcaTipoMonedaPagado = (Integer) montosCargoPrepago.get("idTipoMonedaMdc");
        
        logger.warning("idPrepago: " + idPrepago);
        logger.warning("montoPagado: " + montoPagado);
        logger.warning("idTipoMonedaMdc: " + idTcaTipoMonedaPagado);
        
        formularioGestionarCoberturaVOImpl.crearRowFormularioGestionarCobertura(idPrepago, montoPagado, idTcaTipoMonedaPagado);
    }
              
    /**
    ???? * Se crea metodo para obtener un Map con las listas de 
    ???? * Bytes para generar el aviso
    ???? * @param map
    ???? * @since 22/10/2016
    ???? * @by José Hernández Cruz
    ???? */
    public Map<String,Object> consultarGenerarAviso(Long idPrepago){
        logger.log(ADFLogger.WARNING,"Dentro consultarGenerarAviso");
        logger.log(ADFLogger.WARNING,"idPrepago : "+idPrepago);
        PrepagoPT12BndQSService prepagoPT12BndQSService = null;
        String wsdl = null;
        Map<String,Object> reporteAviso = new HashMap<String,Object>();
        
        try{
            FenixAMImpl fenixAM = null;
            fenixAM = (FenixAMImpl)this.getRootApplicationModule();
            
            wsdl = fenixAM.getWsdl(IWsdlLocation.REPORTE_PREPAGO);
            prepagoPT12BndQSService = IWsdlLocation.Service.getInstance(PrepagoPT12BndQSService.class, wsdl);
            
            PrepagoPT prepagoPT = prepagoPT12BndQSService.getPrepagoPT12BndQSPort();
            ReportePrepagoRequestType request = new ReportePrepagoRequestType();
            request.setIdPrepago(idPrepago);
            
            Date horaInicio = ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_CONSULTAR_REPORTE_PREPAGO);
            ReportePrepagoResponseType response = prepagoPT.reportePrepago(request);
            ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_CONSULTAR_REPORTE_PREPAGO, horaInicio);
            logger.log(ADFLogger.WARNING,"El servicion finalizo correctamente.");
            if(null != response.getDocumentos()){
                
            reporteAviso = generarAviso(response.getDocumentos().getDocumento());
            
            }else{
                logger.log(ADFLogger.WARNING, "El reporte es nulo");
            }
            
        } catch(Exception e){
            logger.log(ADFLogger.WARNING,"ConsultarOperacionResponseType Operacion is  null, no mapping information.");
            logger.log(ADFLogger.ERROR, "Error en consultarGenerarAviso.", e);
        JboException ex = new JboException(e);
        throw ex;
        }
        logger.log(ADFLogger.WARNING, "Numero de registros en el Map :" + reporteAviso.size());
        return reporteAviso;
    }
    
    public Map<String,Object> generarAviso(List<Documento> documentos){ 
       boolean archivoCorrecto = Boolean.FALSE;
        Map<String,Object> reporteAviso = new HashMap<String,Object>();
        byte[] aviso = null;
       byte[] reporte = null;
       String nombreArchivoUno = null;
        String nombreArchivoDos = null;
       try { 
        
           int cantidad_documentos = documentos.size();
              if ( cantidad_documentos == 2) {
                      logger.log(ADFLogger.WARNING, "Contiene exactamente "+cantidad_documentos+" valores.");
                  aviso = documentos.get(0).getContent();
                  nombreArchivoUno = documentos.get(0).getFilename();
                  reporte = documentos.get(1).getContent();
                  nombreArchivoDos = documentos.get(1).getFilename();
                  } 
              else if ( cantidad_documentos != 2  && cantidad_documentos > 1){
                      aviso = documentos.get(0).getContent();
                      nombreArchivoUno = documentos.get(0).getFilename();
                      reporte = documentos.get(1).getContent();
                      nombreArchivoDos = documentos.get(1).getFilename();
                      
                      logger.log(ADFLogger.WARNING, "Contiene mas "+cantidad_documentos+" valores.");
                  }           
              else {
                  logger.log(ADFLogger.WARNING, "Contiene "+cantidad_documentos+" valores.");
              }
           logger.log(ADFLogger.WARNING, "Valor aviso." + aviso);
           logger.log(ADFLogger.WARNING, "Valor reporte." + reporte);
           
           reporteAviso.put("aviso", aviso);
           reporteAviso.put("reporte", reporte);
           reporteAviso.put("nombreArchivoUno", nombreArchivoUno);
           reporteAviso.put("nombreArchivoDos", nombreArchivoDos);
           
       } catch (Exception e) { 
           logger.warning("Error en generarAviso.", e);
       }         
         return reporteAviso;
    }
    
    private String getWsdl(String iWsdlLocation) {
        FenixAMImpl fenixAM = null;
        fenixAM = (FenixAMImpl)this.getRootApplicationModule();
        String wsdl = fenixAM.getWsdl(iWsdlLocation);
        return wsdl;
    }
    
    public Map<String,Object> obtenerMoraCliente(Long idOperacion) {
        logger.warning("inside obtenerDetalleOperacion");
        logger.warning("idOperacion: " + idOperacion);
        boolean enMora = Boolean.FALSE;
        int diasMora = 0;
        BigDecimal montoMora = BigDecimal.ZERO;
        Map<String,Object> reporteAviso = new HashMap<String,Object>();

        long startTime = System.currentTimeMillis();
        startTime = System.currentTimeMillis();
        logger.warning("Tiempo de inicio respuesta del: " + startTime);
        try {
            if (idOperacion != null && idOperacion > 0) {
                String wsdl = getWsdl(IWsdlLocation.OPERACION);

                Operacion12BndQSService operacion12BndQSService =
                    IWsdlLocation.Service.getInstance(Operacion12BndQSService.class, wsdl);
                Operacion12Port operacion12Port = operacion12BndQSService.getOperacion12BndQSPort();

                ConsultarOperacionByIdOperacionRequestType request = new ConsultarOperacionByIdOperacionRequestType();
                request.setIdOperacion(idOperacion);
                request.setNivelDetalle(NivelType.OPERACION);
                request.setInfoDetalleCliente(Boolean.TRUE);

                Date horaInicio =
                    ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_OBTENER_DETALLE_OPERACION);
                ConsultarOperacionResponseType response = operacion12Port.consultarOperacionByIdOperacion(request);
                ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_OBTENER_DETALLE_OPERACION, horaInicio);

                // Evaluamos el response
                if (null != response) {
                    try {
                        if (null != response.getResultado().getResult()) {

                            switch (response.getResultado().getResult()) {
                            case OK:
                                List<Operacion> listaOperqacionesObtenidas = response.getOperacion();
                                if (!listaOperqacionesObtenidas.isEmpty()) {
                                    Operacion operacionUnica = listaOperqacionesObtenidas.get(0);
                                    if (null != operacionUnica.getCliente().isEnMora() && operacionUnica.getCliente().isEnMora()) {
                                        enMora = Boolean.TRUE;
                                        Mora mora = operacionUnica.getCliente().getMora();
                                        if (null != mora) {
                                            diasMora = mora.getDias();
                                            montoMora = mora.getMonto();
                                        }
                                    } else {
                                        logger.warning("El cliente no se encuentra en mora.");
                                    }
                                } else {
                                    logger.warning("La respuesta del servicio no incluye una operacion.");
                                }
                                break;
                            case ERROR:
                                // Validacion de errores no controlados
                                String mensajeError = response.getResultado().getMessage();
                                logger.warning("Error: " + mensajeError);
                                break;
                            default:
                                logger.warning("El objeto Result no tiene un tipo valido.");
                                break;
                            }

                        } else {
                            logger.warning("El objeto Result del response es nulo.");

                            // Validacion de errores controlados
                            if (response.getResultado().getError().getErrorCode().equalsIgnoreCase("ERROR")) {
                                String mensajeError = response.getResultado().getError().getErrorDescription();
                                logger.warning("Error: " + mensajeError);
                            }
                        }
                    } catch (Exception e) {
                        logger.warning("Ocurrio un error al evaluar el response.", e);
                    }
                } else {
                    logger.warning("El response es nulo.");
                }
            }
            long endTime = System.currentTimeMillis();
            logger.warning("Tiempo de fin respuesta: " + endTime);
            logger.warning("Tiempo de respuesta del metodo Inico metodo de adquisiciones " +
                           (endTime - startTime) / 1000 + " segundos");
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error al carga detalle de la operacion", e);
        }
        
        reporteAviso.put("enMora", enMora);
        reporteAviso.put("diasMora", diasMora);
        reporteAviso.put("montoMora", montoMora);
        
        logger.warning("enMora: " + reporteAviso.get("enMora"));
        logger.warning("diasMora: " + reporteAviso.get("diasMora"));
        logger.warning("montoMora: " + reporteAviso.get("montoMora"));
        
        return reporteAviso;
    }
    
    /**
     * Container's getter for PrepagoVO1.
     * @return PrepagoVO1
     */
    public PrepagoVOImpl getPrepagoVO() {
        return (PrepagoVOImpl) findViewObject("PrepagoVO");
    }
     /**
     * Container's getter for CondicionesEspecialesPrepagoVO1.
     * @return CondicionesEspecialesPrepagoVO1
     */
    public CondicionesEspecialesPrepagoVOImpl getCondicionesEspecialesPrepagoVO() {
        return (CondicionesEspecialesPrepagoVOImpl) findViewObject("CondicionesEspecialesPrepagoVO");
    }

    /**
     * Container's getter for ObservacionPrepagoVO1.
     * @return ObservacionPrepagoVO1
     */
    public ObservacionPrepagoVOImpl getObservacionPrepagoVO() {
        return (ObservacionPrepagoVOImpl) findViewObject("ObservacionPrepagoVO");
    }

    /**
     * Container's getter for VentaCarteraPrepagoVO1.
     * @return VentaCarteraPrepagoVO1
     */
    public VentaCarteraPrepagoVOImpl getVentaCarteraPrepagoVO() {
        return (VentaCarteraPrepagoVOImpl) findViewObject("VentaCarteraPrepagoVO");
    }

    /**
     * Container's getter for FormularioPrepagoVO1.
     * @return FormularioPrepagoVO1
     */
    public FormularioPrepagoVOImpl getFormularioPrepagoVO() {
        return (FormularioPrepagoVOImpl) findViewObject("FormularioPrepagoVO");
    }

    /**
     * Container's getter for FormularioObservacionPrepagoVO1.
     * @return FormularioObservacionPrepagoVO1
     */
    public FormularioObservacionPrepagoVOImpl getFormularioObservacionPrepagoVO() {
        return (FormularioObservacionPrepagoVOImpl) findViewObject("FormularioObservacionPrepagoVO");
    }

    /**
     * Container's getter for TrePrepagoContratoVO1.
     * @return TrePrepagoContratoVO1
     */
    public TrePrepagoContratoVOImpl getTrePrepagoContratoVO() {
        return (TrePrepagoContratoVOImpl) findViewObject("TrePrepagoContratoVO");
    }

    /**
     * Container's getter for ContratoDesembolsoPrepagoVO1.
     * @return ContratoDesembolsoPrepagoVO1
     */
    public ContratoDesembolsoPrepagoVOImpl getContratoDesembolsoPrepagoVO() {
        return (ContratoDesembolsoPrepagoVOImpl) findViewObject("ContratoDesembolsoPrepagoVO");
    }

    /**
     * Container's getter for CoberturasPrepagoVO1.
     * @return CoberturasPrepagoVO1
     */
    public CoberturasPrepagoVOImpl getCoberturasPrepagoVO() {
        return (CoberturasPrepagoVOImpl) findViewObject("CoberturasPrepagoVO");
    }

    /**
     * Container's getter for FuentesExternasPrepagoVO1.
     * @return FuentesExternasPrepagoVO1
     */
    public FuentesExternasPrepagoVOImpl getFuentesExternasPrepagoVO() {
        return (FuentesExternasPrepagoVOImpl) findViewObject("FuentesExternasPrepagoVO");
    }

    /**
     * Container's getter for CargoPrepagoVO1.
     * @return CargoPrepagoVO1
     */
    public CargoPrepagoVOImpl getCargoPrepagoVO() {
        return (CargoPrepagoVOImpl) findViewObject("CargoPrepagoVO");
    }

    /**
     * Container's getter for FormularioObservacionCargoPrepagoVO1.
     * @return FormularioObservacionCargoPrepagoVO1
     */
    public FormularioObservacionCargoPrepagoVOImpl getFormularioObservacionCargoPrepagoVO() {
        return (FormularioObservacionCargoPrepagoVOImpl) findViewObject("FormularioObservacionCargoPrepagoVO");
    }

    /**
     * Container's getter for ConsultarObservacionCargoPrepagoVO1.
     * @return ConsultarObservacionCargoPrepagoVO1
     */
    public ConsultarObservacionCargoPrepagoVOImpl getConsultarObservacionCargoPrepagoVO() {
        return (ConsultarObservacionCargoPrepagoVOImpl) findViewObject("ConsultarObservacionCargoPrepagoVO");
    }

    /**
     * Container's getter for ConsultarObservacionPrepagoVO1.
     * @return ConsultarObservacionPrepagoVO1
     */
    public ConsultarObservacionPrepagoVOImpl getConsultarObservacionPrepagoVO() {
        return (ConsultarObservacionPrepagoVOImpl) findViewObject("ConsultarObservacionPrepagoVO");
    }

    /**
     * Container's getter for TipoMonedaCargoPrepagoLOV1.
     * @return TipoMonedaCargoPrepagoLOV1
     */
    public TipoMonedaCargoPrepagoLOVImpl getTipoMonedaCargoPrepagoLOV() {
        return (TipoMonedaCargoPrepagoLOVImpl) findViewObject("TipoMonedaCargoPrepagoLOV");
    }

    /**
     * Container's getter for SectorGarantiaVO1.
     * @return SectorGarantiaVO1
     */
    public SectorGarantiaVOImpl getSectorGarantiaVO() {
        return (SectorGarantiaVOImpl) findViewObject("SectorGarantiaVO");
    }

    /**
     * Container's getter for ObtenerObservacionCargoPrepagoVO1.
     * @return ObtenerObservacionCargoPrepagoVO1
     */
    public ObtenerObservacionCargoPrepagoVOImpl getObtenerObservacionCargoPrepagoVO() {
        return (ObtenerObservacionCargoPrepagoVOImpl) findViewObject("ObtenerObservacionCargoPrepagoVO");
    }

    /**
     * Container's getter for ConsolidadoPagoPrepagoVO1.
     * @return ConsolidadoPagoPrepagoVO1
     */
    public ConsolidadoPagoPrepagoVOImpl getConsolidadoPagoPrepagoVO() {
        return (ConsolidadoPagoPrepagoVOImpl) findViewObject("ConsolidadoPagoPrepagoVO");
    }


    /**
     * Container's getter for CalculoInteresesVO1.
     * @return CalculoInteresesVO1
     */
    public CalculoInteresesVOImpl getCalculoInteresesVO() {
        return (CalculoInteresesVOImpl) findViewObject("CalculoInteresesVO");
    }
    
    /**
     * Container's getter for DetallePenalidadVO1.
     * @return DetallePenalidadVO1
     */
    public DetallePenalidadVOImpl getDetallePenalidadVO() {
        return (DetallePenalidadVOImpl) findViewObject("DetallePenalidadVO");
    }


    /**
     * Container's getter for ConsultarDetallePenalidadVO1.
     * @return ConsultarDetallePenalidadVO1
     */
    public ConsultarDetallePenalidadVOImpl getConsultarDetallePenalidadVO() {
        return (ConsultarDetallePenalidadVOImpl) findViewObject("ConsultarDetallePenalidadVO");
    }

    /**
     * Container's getter for ConsultarCalculoInteresesVO1.
     * @return ConsultarCalculoInteresesVO1
     */
    public ConsultarCalculoInteresesVOImpl getConsultarCalculoInteresesVO() {
        return (ConsultarCalculoInteresesVOImpl) findViewObject("ConsultarCalculoInteresesVO");
    }

    /**
     * Container's getter for FormularioCalculoInteresesVO1.
     * @return FormularioCalculoInteresesVO1
     */
    public FormularioCalculoInteresesVOImpl getFormularioCalculoInteresesVO() {
        return (FormularioCalculoInteresesVOImpl) findViewObject("FormularioCalculoInteresesVO");
    }

    /**
     * Container's getter for FormularioDetallePenalidadVO1.
     * @return FormularioDetallePenalidadVO1
     */
    public FormularioDetallePenalidadVOImpl getFormularioDetallePenalidadVO() {
        return (FormularioDetallePenalidadVOImpl) findViewObject("FormularioDetallePenalidadVO");
    }


    /**
     * Container's getter for CalculoCargoPenalidadPrepagoVO1.
     * @return CalculoCargoPenalidadPrepagoVO1
     */
    public CalculoCargoPenalidadPrepagoVOImpl getCalculoCargoPenalidadPrepagoVO() {
        return (CalculoCargoPenalidadPrepagoVOImpl) findViewObject("CalculoCargoPenalidadPrepagoVO");
    }

    /**
     * Container's getter for DetalleCargoPenalidadPrepagoVO1.
     * @return DetalleCargoPenalidadPrepagoVO1
     */
    public DetalleCargoPenalidadPrepagoVOImpl getDetalleCargoPenalidadPrepagoVO() {
        return (DetalleCargoPenalidadPrepagoVOImpl) findViewObject("DetalleCargoPenalidadPrepagoVO");
    }

    /**
     * Container's getter for CalculoDetallePenalidadVO1.
     * @return CalculoDetallePenalidadVO1
     */
    public CalculoDetallePenalidadVOImpl getCalculoDetallePenalidadVO() {
        return (CalculoDetallePenalidadVOImpl) findViewObject("CalculoDetallePenalidadVO");
    }
    
    /**
     * Container's getter for DiasHabilesVO1.
     * @return DiasHabilesVO1
     */
    public DiasHabilesVOImpl getDiasHabilesVO() {
        return (DiasHabilesVOImpl) findViewObject("DiasHabilesVO");
    }

    /**
     * Container's getter for GestionarCoberturaVO1.
     * @return GestionarCoberturaVO1
     */
    public GestionarCoberturaVOImpl getGestionarCoberturaVO() {
        return (GestionarCoberturaVOImpl) findViewObject("GestionarCoberturaVO");
    }

    /**
     * Container's getter for FormularioGestionarCoberturaVO1.
     * @return FormularioGestionarCoberturaVO1
     */
    public FormularioGestionarCoberturaVOImpl getFormularioGestionarCoberturaVO() {
        return (FormularioGestionarCoberturaVOImpl) findViewObject("FormularioGestionarCoberturaVO");
    }
    
    /**
     * Container's getter for ContratoDesembolsoExternoVO1.
     * @return ContratoDesembolsoExternoVO1
     */
    public ContratoDesembolsoExternoVOImpl getContratoDesembolsoExternoVO() {
        return (ContratoDesembolsoExternoVOImpl) findViewObject("ContratoDesembolsoExternoVO");
    }

    /**
     * Container's getter for CatProductoVO1.
     * @return CatProductoVO1
     */
    public CatProductoVOImpl getCatProductoVO() {
        return (CatProductoVOImpl) findViewObject("CatProductoVO");
    }

    /**
     * Container's getter for ContratoDesembolsoExternoPorOperacionVO1.
     * @return ContratoDesembolsoExternoPorOperacionVO1
     */
    public ContratoDesembolsoExternoPorOperacionVOImpl getContratoDesembolsoExternoPorOperacionVO() {
        return (ContratoDesembolsoExternoPorOperacionVOImpl) findViewObject("ContratoDesembolsoExternoPorOperacionVO");
    }



    /**
     * Container's getter for TasasFlexcubeVO1.
     * @return TasasFlexcubeVO1
     */
    public TasasFlexcubeVOImpl getTasasFlexcubeVO() {
        return (TasasFlexcubeVOImpl) findViewObject("TasasFlexcubeVO");
    }

    /**
     * Container's getter for TcaTipoMonedaVO1.
     * @return TcaTipoMonedaVO1
     */
    public TcaTipoMonedaVOImpl getTcaTipoMonedaVO() {
        return (TcaTipoMonedaVOImpl) findViewObject("TcaTipoMonedaVO");
    }

    /**
     * Container's getter for ConsultarComisionPrepagoVO1.
     * @return ConsultarComisionPrepagoVO1
     */
    public ConsultarComisionPrepagoVOImpl getConsultarComisionPrepagoVO() {
        return (ConsultarComisionPrepagoVOImpl) findViewObject("ConsultarComisionPrepagoVO");
    }

    /**
     * Container's getter for ObtenerDetallePenalidadVO1.
     * @return ObtenerDetallePenalidadVO1
     */
    public ObtenerDetallePenalidadVOImpl getObtenerDetallePenalidadVO() {
        return (ObtenerDetallePenalidadVOImpl) findViewObject("ObtenerDetallePenalidadVO");
    }

    /**
     * Container's getter for ObtenerCalculoInteresesVO1.
     * @return ObtenerCalculoInteresesVO1
     */
    public ObtenerCalculoInteresesVOImpl getObtenerCalculoInteresesVO() {
        return (ObtenerCalculoInteresesVOImpl) findViewObject("ObtenerCalculoInteresesVO");
    }

    /**
     * Container's getter for ObtenerTrePrepagoContratoVO1.
     * @return ObtenerTrePrepagoContratoVO1
     */
    public ObtenerTrePrepagoContratoVOImpl getObtenerTrePrepagoContratoVO() {
        return (ObtenerTrePrepagoContratoVOImpl) findViewObject("ObtenerTrePrepagoContratoVO");
    }

    /**
     * Container's getter for ContratoOperacionSaldoNoVencidoVO1.
     * @return ContratoOperacionSaldoNoVencidoVO1
     */
    public ContratoOperacionSaldoNoVencidoVOImpl getContratoOperacionSaldoNoVencidoVO() {
        return (ContratoOperacionSaldoNoVencidoVOImpl) findViewObject("ContratoOperacionSaldoNoVencidoVO");
    }

    /**
     * Container's getter for ResponsableVO1.
     * @return ResponsableVO1
     */
    public ResponsableVOImpl getResponsableVO() {
        return (ResponsableVOImpl) findViewObject("ResponsableVO");
    }

    /**
     * Container's getter for DiasInhabilesVO1.
     * @return DiasInhabilesVO1
     */
    public DiasInhabilesVOImpl getDiasInhabilesVO() {
        return (DiasInhabilesVOImpl) findViewObject("DiasInhabilesVO");
    }

    /**
     * Container's getter for ContratoDesembolsoPorRangoFechasPrepagoVO1.
     * @return ContratoDesembolsoPorRangoFechasPrepagoVO1
     */
    public ContratoDesembolsoPorRangoFechasPrepagoVOImpl getContratoDesembolsoPorRangoFechasPrepagoVO() {
        return (ContratoDesembolsoPorRangoFechasPrepagoVOImpl) findViewObject("ContratoDesembolsoPorRangoFechasPrepagoVO");
    }

    /**
     * Container's getter for ContratoDesembolsoPrepagoVO1.
     * @return ContratoDesembolsoPrepagoVO1
     */
    public ContratoDesembolsoPrepagoVOImpl getTreContratoDesembolsoPrepagoVO() {
        return (ContratoDesembolsoPrepagoVOImpl) findViewObject("TreContratoDesembolsoPrepagoVO");
    }

    /**
     * Container's getter for ContratoDesembolsoPorRangoFechasPrepagoVO1.
     * @return ContratoDesembolsoPorRangoFechasPrepagoVO1
     */
    public ContratoDesembolsoPorRangoFechasPrepagoVOImpl getContratoDesembolsoPorRangoFechasPrepagoVO1() {
        return (ContratoDesembolsoPorRangoFechasPrepagoVOImpl) findViewObject("ContratoDesembolsoPorRangoFechasPrepagoVO1");
    }

    /**
     * Container's getter for TreContratoDesembolsoPrepagoVL1.
     * @return TreContratoDesembolsoPrepagoVL1
     */
    public ViewLinkImpl getTreContratoDesembolsoPrepagoVL1() {
        return (ViewLinkImpl) findViewLink("TreContratoDesembolsoPrepagoVL1");
    }
}

