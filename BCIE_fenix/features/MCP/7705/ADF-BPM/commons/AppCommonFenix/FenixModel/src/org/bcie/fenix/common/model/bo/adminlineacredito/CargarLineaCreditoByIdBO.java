package org.bcie.fenix.common.model.bo.adminlineacredito;

import java.math.BigDecimal;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewObject;

import org.bcie.commonbo.MontoType;
import org.bcie.contratobo.Contrato;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.bo.BaseBO;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.utils.ModelUtils;
import org.bcie.fenix.common.model.vo.CatProductoFlexcubeLOVImpl;
import org.bcie.fenix.common.model.vo.ComisionVOImpl;
import org.bcie.fenix.common.model.vo.ComisionesLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.ComisionesLineaCreditoVORowImpl;
import org.bcie.fenix.common.model.vo.ContratoVORowImpl;
import org.bcie.fenix.common.model.vo.LineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.LineaCreditoVORowImpl;
import org.bcie.fenix.common.model.vo.RegistrarDatosLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.RegistrarDatosLineaCreditoVORowImpl;
import org.bcie.fenix.common.model.vo.TerminosLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.TerminosLineaCreditoVORowImpl;
import org.bcie.fenix.common.model.vo.VencimientoPlazoVOImpl;
import org.bcie.fenix.common.model.vo.VencimientosLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.VencimientosLineaCreditoVORowImpl;
import org.bcie.lineacreditobo.LineaCredito;
import org.bcie.lineacreditomo.ConsultarLineaCreditoBPELRequestType;
import org.bcie.lineacreditomo.ConsultarLineaCreditoBPELResponseType;

/**
 * [KB:14513]
 * Permite la carga de líneas de crédito por su ID.
 * Se encarga de configurar las demas VO que se requieran para su visualización en la vista de admin líneas de crédito.
 * ATENCION: Una vez ejecutada (método execute es invocado), no podrá volverse a ejecutar y una nueva instancia será requerida.
 */
public class CargarLineaCreditoByIdBO extends BaseBO {

    private static ADFLogger logger = ADFLogger.createADFLogger(CargarLineaCreditoByIdBO.class);
    
    /** Descripción corta del monto tipo DISPONIBLE.*/
    private static final String MONTOTYPE_DISPONIBLE = "DISPONIBLE";

    /** Identificador de la línea de crédito a consultar.*/
    private Long idLineaCredito;

    /** Producto actual.*/
    private Integer idProducto;

    public CargarLineaCreditoByIdBO(FenixAMImpl fenixAM, Long idLineaCredito, Integer idProducto) {
        super(fenixAM);

        Objects.requireNonNull(idLineaCredito, "idLineaCredito es requerido.");
        Objects.requireNonNull(idProducto, "idProducto es requerido.");

        this.idLineaCredito = idLineaCredito;
        this.idProducto = idProducto;
    }

    @Override
    public FenixAMImpl getAM() {
        return (FenixAMImpl) super.getAM();
    }

    /**
     * Ejecuta esta operación de negocio.
     * Carga la infor de la línea de crédito a objetos de memoria y configura LOVs para la vista admin líneas de crédito.
     */
    @Override
    public void execute() {
        //si el recurso ya fue liberado, entonces no se puede continuar y se debe crear otra instancia de esta clase
        if (this.getAM() == null) {
            logger.warning("Instancia ya liberada, no se continuará.");
            throw new IllegalStateException("El AM de esta instancia ya fue liberado, por lo tanto no puede volver a usarse. Debe crear una nueva instancia de esta clase.");
        }

        logger.info("Iniciando carga de datos de línea de crédito a memoria.");

        try {
            TerminosLineaCreditoVORowImpl termPlazoFinanciamiento =
                this.getTermino(FenixModelConstants.TERMINO_PLAZO_FINANCIAMIENTO);
            TerminosLineaCreditoVORowImpl termPlazoGracia =
                this.getTermino(FenixModelConstants.TERMINO_PLAZO_PERIODO_GRACIA);
            TerminosLineaCreditoVORowImpl termEnDesembolso = this.getTermino(FenixModelConstants.TERMINO_EN_DESEMBOLSO);
            TerminosLineaCreditoVORowImpl termRecursoExterno =
                this.getTermino(FenixModelConstants.TERMINO_RECURSO_EXTERNO);

            RegistrarDatosLineaCreditoVORowImpl regLCRow =
                this.cargarLineaCreditoEnMemoria(termPlazoFinanciamiento, termPlazoGracia, termEnDesembolso,
                                                 termRecursoExterno);
            if (regLCRow != null) {
                this.cargarVencimientosEnMemoria();
                this.cargarComisionesEnMemoria();
                this.configurarLOVs(regLCRow, termPlazoFinanciamiento, termPlazoGracia);                
            }
        } finally {
            //Se asegura de liberar recursos por si de casualidad el cliente de esta clase no lo hace.
            try {
                this.close();
            } catch (Exception ex) {
                logger.severe(ex);
            }
        }

        logger.info("Finaliza carga de datos de línea de crédito a memoria.");
    }

    /**
     * Carga datos de la linea de credito desde base de datos a memoria (RegistrarDatosLineaCreditoVO) para que sean
     * usadas por la vista en admin lineas de credito.
     * @return Línea de crédito en memoria.
     */
    private RegistrarDatosLineaCreditoVORowImpl cargarLineaCreditoEnMemoria(final TerminosLineaCreditoVORowImpl termPlazoFinanciamiento,
                                                                            final TerminosLineaCreditoVORowImpl termPlazoGracia,
                                                                            final TerminosLineaCreditoVORowImpl termEnDesembolso,
                                                                            final TerminosLineaCreditoVORowImpl termRecursoExterno) {

        FenixAMImpl fenixAM = this.getAM();

        //limpiamos objeto en memoria que usa la vista admin lineas credito para los datos de la LC
        RegistrarDatosLineaCreditoVOImpl regLCVO = fenixAM.getRegistrarDatosLineaCreditoVO();
        regLCVO.executeQuery();

        //Obtenemos la línea de crédito desde base de datos
        LineaCreditoVOImpl lineaCreditoVO = fenixAM.getLineaCreditoVO();
        ViewCriteria byIdVC = lineaCreditoVO.getViewCriteriaManager().getViewCriteria("LineaCreditoVOCriteria");
        if (byIdVC == null) {
            logger.warning("No se encuentra ViewCriteria: LineaCreditoVOCriteria. No se puede continuar.");
            return null;
        }

        RegistrarDatosLineaCreditoVORowImpl regLCRow = null;
        try {
            //se limpia el id del contrato ya que queremos consultar solo por id de linea de credito
            lineaCreditoVO.setcontratoId(null);
            byIdVC.ensureVariableManager().setVariableValue("varIdLineaCredito", this.idLineaCredito);
            lineaCreditoVO.applyViewCriteria(byIdVC);
            lineaCreditoVO.executeQuery();

            if (lineaCreditoVO.getEstimatedRowCount() > 0) {
                RowSetIterator rsi = lineaCreditoVO.createRowSetIterator(null);
                try {
                    if (rsi.hasNext()) {
                        BigDecimal disponible = this.obtenerMontoDisponible();

                        LineaCreditoVORowImpl lcDbRow = (LineaCreditoVORowImpl) rsi.next();
                        regLCRow =
                            (RegistrarDatosLineaCreditoVORowImpl) regLCVO.insertCopyOf(lcDbRow, termPlazoFinanciamiento,
                                                                                       termPlazoGracia,
                                                                                       termEnDesembolso,
                                                                                       termRecursoExterno, disponible);
                        regLCVO.setCurrentRow(regLCRow);
                    }
                } finally {
                    rsi.closeRowSetIterator();
                }
            } else {
                logger.warning("No se encuentra Línea de Crédito ID: {0}", this.idLineaCredito);
                return null;
            }
        } finally {
            lineaCreditoVO.getViewCriteriaManager().removeApplyViewCriteriaName("LineaCreditoVOCriteria");
        }

        return regLCRow;
    }

    /**
     * Configura los diferentes LOVs que se usan en la vista de admin íneas crédito.
     * @param regLCRow Registro de línea de crédito de donde se toman valores para configurar los LOVs.
     */
    private void configurarLOVs(final RegistrarDatosLineaCreditoVORowImpl regLCRow,
                                final TerminosLineaCreditoVORowImpl termPlazoFinanciamiento,
                                final TerminosLineaCreditoVORowImpl termPlazoGracia) {
        logger.info("Inicia configurarLOVs.");

        Objects.requireNonNull(regLCRow);

        FenixAMImpl fenixAM = this.getAM();

        //CatProductoFLexcubeLOV
        CatProductoFlexcubeLOVImpl catProductoFlexcubeLOVImpl = fenixAM.getCatProductoFlexcubeLOV();
        if (regLCRow.getEsRevolvente() != null) {
            logger.info("Revolvente, configurando catProductoFlexcubeLOV.");

            if (regLCRow.getIdProductoFlexcube() != null && !regLCRow.getIdProductoFlexcube().isEmpty()) {
                logger.info("Revolvente, asignando IdProductoFlexcube: {0} a catProductoFlexcubeLOV.",
                            regLCRow.getIdProductoFlexcube());
                catProductoFlexcubeLOVImpl.setidProducto(null);
                catProductoFlexcubeLOVImpl.setidProductoFlexcube(regLCRow.getIdProductoFlexcube());
            } else {
                logger.info("Revolvente, asignando IdProducto: {0} a catProductoFlexcubeLOV.", idProducto);
                catProductoFlexcubeLOVImpl.setidProducto(idProducto);
                catProductoFlexcubeLOVImpl.setidProductoFlexcube(null);
            }

            if (FenixModelConstants.ES_REVOLVENTE_TRUE_INT.equals(regLCRow.getEsRevolvente())) {
                logger.info("Revolvente, asignando isRevolvente: {0} a catProductoFlexcubeLOV.",
                            FenixModelConstants.ES_REVOLVENTE_TRUE);
                catProductoFlexcubeLOVImpl.setisRevolvente(FenixModelConstants.ES_REVOLVENTE_TRUE);
            } else {
                logger.info("Revolvente, asignando isRevolvente: {0} a catProductoFlexcubeLOV.",
                            FenixModelConstants.ES_REVOLVENTE_FALSE);
                catProductoFlexcubeLOVImpl.setisRevolvente(FenixModelConstants.ES_REVOLVENTE_FALSE);
            }

            catProductoFlexcubeLOVImpl.executeQuery();
        }

        //Producto
        this.filtrarVOByKey(catProductoFlexcubeLOVImpl, regLCRow.getIdProductoFlexcube());

        //Selecciona el valor del fondo en CatFondoContableVO para que la vista se actualice acorde.
        this.filtrarVOByKey(fenixAM.getCatFondoContableVO(), regLCRow.getFondo());

        //PlazoFinanciamiento o FrecuenciaFinanciamiento
        if (termPlazoFinanciamiento != null) {
            this.filtrarVOByAttribute(fenixAM.getCatPlazoFinanciamientoLOV(), "Id",
                                      termPlazoFinanciamiento.getIdTipoPlazo());
        }

        //PlazoGracias o FrecuenciaGracia
        if (termPlazoGracia != null) {
            this.filtrarVOByAttribute(fenixAM.getCatPeriodoGraciaLOV(), "Id", termPlazoGracia.getIdTipoPlazo());
        }

        //Dias Feriados
        this.filtrarVOByKey(fenixAM.getCatDiasFeriadosVO(), regLCRow.getTratamientoDiasFeriados());

        //Tipo Redondeo
        this.filtrarVOByKey(fenixAM.getTcaTipoRedondeoVO1(), regLCRow.getIdTcaTipoRedondeo());

        //Tipo Mora
        this.filtrarVOByAttribute(fenixAM.getTcaTipoMoraVO1(), "Id", regLCRow.getIdTcaTipoMora());

        //Tipo Frecuencia
        this.filtrarVOByAttribute(fenixAM.getTcaTipoFrecuenciaVO1(), "Id", regLCRow.getIdTcaTipoFrecuencia());

        //Dias Spot Tasa
        if (regLCRow.getDiasSpotTasa() != null) {
            this.filtrarVOByKey(fenixAM.getDiasSpotTasaVO1(), String.valueOf(regLCRow.getDiasSpotTasa()));
        }

        logger.info("Finaliza configurarLOVs.");
    }

    /**
     * Carga los vencimientos de la linea de credito desde base de datos a memoria (VencimientoPlazoVO) para que sean
     * usados por la vista en admin lineas de credito.
     */
    private void cargarVencimientosEnMemoria() {
        FenixAMImpl fenixAM = this.getAM();

        //Limpiamos objeto en memoria que usa la vista admin lineas credito para los vencimientos
        VencimientoPlazoVOImpl plazoVO = fenixAM.getVencimientoPlazoVO();
        plazoVO.executeQuery();
        //Obtenemmos los Vencimientos activos desde base de datos
        VencimientosLineaCreditoVOImpl vencimientosVO = fenixAM.getVencimientosLineaCreditoVO();
        vencimientosVO.setpIdLineaCredito(this.idLineaCredito);
        vencimientosVO.executeQuery();
        RowSetIterator rsi = vencimientosVO.createRowSetIterator(null);
        try {
            //insertamos una copia de los vencimientos encontrados en base de datos al objeto en memoria
            while (rsi.hasNext()) {
                VencimientosLineaCreditoVORowImpl rowVencimiento =
                    (VencimientosLineaCreditoVORowImpl) rsi.next();
                plazoVO.insertCopyOf(rowVencimiento);
            }
        } finally {
            rsi.closeRowSetIterator();
        }
    }

    /**
     * Carga las comisiones de la linea de credito desde base de datos a memoria (ComisionVO) para que sean usadas por
     * la vista en admin lineas de credito.
     */
    private void cargarComisionesEnMemoria() {
        FenixAMImpl fenixAM = this.getAM();

        //Limpiamos objeto en memoria que usa la vista admin lineas credito para las comisiones
        ComisionVOImpl comisionVO = fenixAM.getComisionVO();
        comisionVO.executeQuery();
        //Obtenemmos las comisiones activas desde base de datos
        ComisionesLineaCreditoVOImpl comisionesVO = fenixAM.getComisionesLineaCreditoVO();
        comisionesVO.setpIdLineaCredito(this.idLineaCredito);
        comisionesVO.executeQuery();
        RowSetIterator rsi = comisionesVO.createRowSetIterator(null);
        try {
            //insertamos una copia de las comisiones encontradas en base de datos al objeto en memoria
            while (rsi.hasNext()) {
                ComisionesLineaCreditoVORowImpl rowComision = (ComisionesLineaCreditoVORowImpl) rsi.next();
                comisionVO.insertCopyOf(rowComision);
            }
        } finally {
            rsi.closeRowSetIterator();
        }
    }

    /**
     * Usado para filtrar VOs por su llave y asignar el currentRow al primer registro encontrado.
     * @param vo ViewObject a filtrar.
     * @param keyValues Valores a usar en la llave.
     * @return Fila encontrada.
     */
    private Row filtrarVOByKey(final ViewObject vo, final Object... keyValues) {
        Row rowFound = null;
        if (keyValues != null && keyValues.length > 0) {
            Row[] filteredArray = vo.findByKey(new Key(keyValues), 1);
            if (filteredArray != null && filteredArray.length > 0) {
                rowFound = filteredArray[0];
                vo.setCurrentRow(rowFound);
            } else {
                logger.info("No se encontró registro con llave: {0}", Arrays.toString(keyValues));
            }
        } else {
            logger.info("No se puede filtrar. Valores llave son obligatorios.");
        }
        return rowFound;
    }

    /**
     * Usado para filtrar VOs por un atributoy asignar el currentRow al primer registro encontrado.
     * @param vo ViewObject a filtrar.
     * @parma attributeName Nombre del atributo por donde se filtrará.
     * @param attributeValue Valor del atributo.
     * @return Fila encontrada.
     */
    private Row filtrarVOByAttribute(final ViewObject vo, final String attributeName, final Object attributeValue) {
        Row rowFound = null;
        if (attributeName != null && attributeValue != null) {
            Row[] filteredArray = vo.getFilteredRows(attributeName, attributeValue);
            if (filteredArray != null && filteredArray.length > 0) {
                rowFound = filteredArray[0];
                vo.setCurrentRow(rowFound);
            } else {
                logger.info("No se encontró registro con atributo: {0}={1}", new Object[] {
                            attributeName, attributeValue });
            }
        } else {
            logger.info("No se puede filtrar. Nombre del atributo y su valor son obligatorios.");
        }
        return rowFound;
    }

    private TerminosLineaCreditoVORowImpl getTermino(final Integer idTipoTermino) {
        TerminosLineaCreditoVOImpl terminosLCVO = this.getAM().getTerminosLineaCreditoVO();
        terminosLCVO.setpIdLineaCredito(this.idLineaCredito);
        terminosLCVO.setpIdTipoTermino(idTipoTermino);
        terminosLCVO.executeQuery();

        return (TerminosLineaCreditoVORowImpl) terminosLCVO.first();
    }
    
    /**
     * Obtiene el monto disponible de la línea desde Flexcube.
     * @return Monto disponible o BigDecimal.ZERO si no se encuentra monto disponible.
     */
    private BigDecimal obtenerMontoDisponible() {
        logger.info("Buscando monto disponible de la línea.");
        
        FenixAMImpl fenixAM = this.getAM();
        
        BigDecimal result = BigDecimal.ZERO;

        String wsdl = fenixAM.getWsdl(IWsdlLocation.LINEA_CREDITO);

        com.bcie.xmlns.lineacreditoservice.LineaCredito lineaCredito12BndQSService =
            IWsdlLocation.Service.getInstance(com.bcie.xmlns.lineacreditoservice.LineaCredito.class, wsdl);
        com.bcie.xmlns.lineacreditoservice.LineaCreditoPT lineaCreditoPT =
            lineaCredito12BndQSService.getLineaCredito12Bnd();

        ConsultarLineaCreditoBPELRequestType request = new ConsultarLineaCreditoBPELRequestType();
        request.setIdLineaCredito(this.idLineaCredito);

        Date horaInicio =
            ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_CONSULTAR_LINEA_CREDITO_BY_ID_LINEA_CREDITO);

        ConsultarLineaCreditoBPELResponseType response = lineaCreditoPT.consultarLineaCreditoById(request);

        ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_CONSULTAR_LINEA_CREDITO_BY_ID_LINEA_CREDITO,
                            horaInicio);

        if (response.getResultado() != null) {
            logger.warning("Respuesta consultarLineaCreditoById: {0}", response.getResultado().getResult().value());

            if ("OK".equals(response.getResultado().getResult().value())) {
                LineaCredito linea = response.getLineaCredito();

                if (linea != null) {
                    List<MontoType> montos = linea.getMonto();
                    for (MontoType monto : montos) {
                        if (MONTOTYPE_DISPONIBLE.equals(monto.getTipo().getDescripcionCorta())) {
                            logger.info("Monto disponible encontrado!");
                            
                            result = monto.getImporte();
                            break;
                        }
                    }
                }
            }
        }
        
        logger.info("Finaliza obtenerMontoDisponible con resultado: {0}", result);

        return result;
    }
}
