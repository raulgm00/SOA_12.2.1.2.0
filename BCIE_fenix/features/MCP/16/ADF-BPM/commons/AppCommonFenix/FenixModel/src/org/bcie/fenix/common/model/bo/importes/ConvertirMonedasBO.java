package org.bcie.fenix.common.model.bo.importes;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.catalogobo.Catalogo;
import org.bcie.conversormonedas.ConversorMonedasPT;
import org.bcie.conversormonedas.ConversorMonedasPT12BndQSService;
import org.bcie.conversormonedasbo.ConvierteMonedas;
import org.bcie.conversormonedasbo.ConvierteMonedasImporte;
import org.bcie.conversormonedasmo.ConvertirMonedasImportesRequestType;
import org.bcie.conversormonedasmo.ConvertirMonedasImportesResponseType;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.bo.BaseBO;
import org.bcie.fenix.common.model.bo.adminlineacredito.CargarContratoOperacionProcesoBO;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.utils.ModelUtils;
import org.bcie.fenix.common.model.vo.EncabezadoRegistrarDatosLineaCreditoVORowImpl;
import org.bcie.fenix.common.model.vo.TerminosLineaCreditoVOImpl;

/**
 * [KB:13306]
 * Permite la conversion de mondotos entre monedas.
 * ATENCION: Una vez ejecutada (método execute es invocado), no podrá volverse a ejecutar y una nueva instancia será requerida.
 */
public class ConvertirMonedasBO extends BaseBO {

    private static ADFLogger logger = ADFLogger.createADFLogger(ConvertirMonedasBO.class);

    /** Identificador del tipo de moneda desde el cual se desea hacer la conversión.*/
    private Integer idTcaTipoMonedaDe;

    /** Código Externo del tipo de moneda desde el cual se desea hacer la conversión.*/
    private String codigoTipoMonedaDe;

    /** Monto que se desea convertir.*/
    private BigDecimal montoAConvertir;

    /** Identificador del tipo de moneda hacia la cual se desea hacer la conversión.*/
    private Integer idTcaTipoMonedaA;

    /** Código Externo del tipo de moneda hacia la cual se desea hacer la conversión.*/
    private String codigoTipoMonedaA;

    /** Resultado de la conversión.*/
    private BigDecimal resultado;

    public ConvertirMonedasBO(FenixAMImpl fenixAM, Integer idTcaTipoMonedaDe, BigDecimal montoAConvertir,
                              Integer idTcaTipoMonedaA) {
        super(fenixAM);

        Objects.requireNonNull(idTcaTipoMonedaDe, "idTcaTipoMonedaDe es requerido.");
        Objects.requireNonNull(montoAConvertir, "montoAConvertir es requerido.");
        Objects.requireNonNull(idTcaTipoMonedaA, "idTcaTipoMonedaA es requerido.");

        this.idTcaTipoMonedaDe = idTcaTipoMonedaDe;
        this.montoAConvertir = montoAConvertir;
        this.idTcaTipoMonedaA = idTcaTipoMonedaA;
    }

    @Override
    public FenixAMImpl getAM() {
        return (FenixAMImpl) super.getAM();
    }

    /**
     * Ejecuta esta operación de negocio.
     * Carga la info del contrato a objeto de memoria usado en la vista admin líneas de crédito.
     */
    @Override
    public void execute() {
        //si el recurso ya fue liberado, entonces no se puede continuar y se debe crear otra instancia de esta clase
        if (this.getAM() == null) {
            logger.warning("Instancia ya liberada, no se continuará.");
            throw new IllegalStateException("El AM de esta instancia ya fue liberado, por lo tanto no puede volver a usarse. Debe crear una nueva instancia de esta clase.");
        }

        logger.entering(ConvertirMonedasBO.class.getName(), "ConvertirMonedasBO::execute", new Object[] {
                        this.idTcaTipoMonedaDe, this.montoAConvertir, this.idTcaTipoMonedaA
        });

        try {
            this.codigoTipoMonedaDe = this.getCodigoMoneda(this.idTcaTipoMonedaDe);
            this.codigoTipoMonedaA = this.getCodigoMoneda(this.idTcaTipoMonedaA);
            //solo enviar a convertir si tenemos los códigos de las monedas
            if(this.codigoTipoMonedaDe != null && this.codigoTipoMonedaA != null){
                //capturar la conversión para que el cliente de esta operación la pueda leer.
                this.resultado = this.convertir();
            }
        } finally {
            //Se asegura de liberar recursos por si de casualidad el cliente de esta clase no lo hace.
            try {
                this.close();
            } catch (Exception ex) {
                logger.severe(ex);
            }
        }

        logger.exiting(ConvertirMonedasBO.class.getName(), "ConvertirMonedasBO::execute");
    }

    /**
     * Obtiene el código externo de la moneda pasada por param.
     * @param idTcaTipoMoneda Identificador de la moneda de la cual se desea conocer el código externo.
     * @return Código externo de la moneda o nulo si no se encuentra una moneda con ese ID.
     */
    private String getCodigoMoneda(final Integer idTcaTipoMoneda) {
        String codigoMoneda = null;
        ViewObjectImpl tcaCatMonedaVO = this.getAM().getTcaTipoMonedaVO();
        Row[] filteredArray = tcaCatMonedaVO.findByKey(new Key(new Object[] { idTcaTipoMoneda }), 1);
        if (filteredArray != null && filteredArray.length > 0) {
            Row rowFound = filteredArray[0];
            codigoMoneda = (String)rowFound.getAttribute("CodExterno");
            logger.info("Código moneda encontrado: {0}", codigoMoneda);
        } else {
            logger.warning("No se encontró tipoMoneda para el id: {0}", idTcaTipoMoneda);
        }

        return codigoMoneda;
    }

    /**
     * Realiza la conversión.
     * Actualmente invoca método en el AM que hace la invocación al servicio de conversión.
     * @return Resultado de la conversión o nulo si hubo algún error.
     */
    private BigDecimal convertir() {
        return this.getAM().convertirMonedasNew(this.codigoTipoMonedaDe, this.codigoTipoMonedaA, this.montoAConvertir);
    }

    /**
     * Permite que los clientes de esta operación puedan acceder a su resultado.
     * @return Resultado de la conversión o nulo si hubo algún error.
     */
    public BigDecimal getResultado() {
        return this.resultado;
    }
}
