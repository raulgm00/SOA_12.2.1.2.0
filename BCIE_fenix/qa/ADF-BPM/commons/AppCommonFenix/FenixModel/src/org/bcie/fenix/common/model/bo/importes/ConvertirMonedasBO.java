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
 * ATENCION: Una vez ejecutada (m�todo execute es invocado), no podr� volverse a ejecutar y una nueva instancia ser� requerida.
 */
public class ConvertirMonedasBO extends BaseBO {

    private static ADFLogger logger = ADFLogger.createADFLogger(ConvertirMonedasBO.class);

    /** Identificador del tipo de moneda desde el cual se desea hacer la conversi�n.*/
    private Integer idTcaTipoMonedaDe;

    /** C�digo Externo del tipo de moneda desde el cual se desea hacer la conversi�n.*/
    private String codigoTipoMonedaDe;

    /** Monto que se desea convertir.*/
    private BigDecimal montoAConvertir;

    /** Identificador del tipo de moneda hacia la cual se desea hacer la conversi�n.*/
    private Integer idTcaTipoMonedaA;

    /** C�digo Externo del tipo de moneda hacia la cual se desea hacer la conversi�n.*/
    private String codigoTipoMonedaA;

    /** Resultado de la conversi�n.*/
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
     * Ejecuta esta operaci�n de negocio.
     * Carga la info del contrato a objeto de memoria usado en la vista admin l�neas de cr�dito.
     */
    @Override
    public void execute() {
        //si el recurso ya fue liberado, entonces no se puede continuar y se debe crear otra instancia de esta clase
        if (this.getAM() == null) {
            logger.warning("Instancia ya liberada, no se continuar�.");
            throw new IllegalStateException("El AM de esta instancia ya fue liberado, por lo tanto no puede volver a usarse. Debe crear una nueva instancia de esta clase.");
        }

        logger.entering(ConvertirMonedasBO.class.getName(), "ConvertirMonedasBO::execute", new Object[] {
                        this.idTcaTipoMonedaDe, this.montoAConvertir, this.idTcaTipoMonedaA
        });

        try {
            this.codigoTipoMonedaDe = this.getCodigoMoneda(this.idTcaTipoMonedaDe);
            this.codigoTipoMonedaA = this.getCodigoMoneda(this.idTcaTipoMonedaA);
            //solo enviar a convertir si tenemos los c�digos de las monedas
            if(this.codigoTipoMonedaDe != null && this.codigoTipoMonedaA != null){
                //capturar la conversi�n para que el cliente de esta operaci�n la pueda leer.
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
     * Obtiene el c�digo externo de la moneda pasada por param.
     * @param idTcaTipoMoneda Identificador de la moneda de la cual se desea conocer el c�digo externo.
     * @return C�digo externo de la moneda o nulo si no se encuentra una moneda con ese ID.
     */
    private String getCodigoMoneda(final Integer idTcaTipoMoneda) {
        String codigoMoneda = null;
        ViewObjectImpl tcaCatMonedaVO = this.getAM().getTcaTipoMonedaVO();
        Row[] filteredArray = tcaCatMonedaVO.findByKey(new Key(new Object[] { idTcaTipoMoneda }), 1);
        if (filteredArray != null && filteredArray.length > 0) {
            Row rowFound = filteredArray[0];
            codigoMoneda = (String)rowFound.getAttribute("CodExterno");
            logger.info("C�digo moneda encontrado: {0}", codigoMoneda);
        } else {
            logger.warning("No se encontr� tipoMoneda para el id: {0}", idTcaTipoMoneda);
        }

        return codigoMoneda;
    }

    /**
     * Realiza la conversi�n.
     * Actualmente invoca m�todo en el AM que hace la invocaci�n al servicio de conversi�n.
     * @return Resultado de la conversi�n o nulo si hubo alg�n error.
     */
    private BigDecimal convertir() {
        return this.getAM().convertirMonedasNew(this.codigoTipoMonedaDe, this.codigoTipoMonedaA, this.montoAConvertir);
    }

    /**
     * Permite que los clientes de esta operaci�n puedan acceder a su resultado.
     * @return Resultado de la conversi�n o nulo si hubo alg�n error.
     */
    public BigDecimal getResultado() {
        return this.resultado;
    }
}
