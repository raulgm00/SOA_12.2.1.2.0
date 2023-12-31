package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.vo.common.DetalleCargoPenalidadPrepagoVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Oct 03 18:09:25 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DetalleCargoPenalidadPrepagoVOImpl extends ViewObjectImpl implements DetalleCargoPenalidadPrepagoVO {
    /**
     * This is the default constructor (do not remove).
     */
    
    private static ADFLogger logger = null;
    
    public DetalleCargoPenalidadPrepagoVOImpl() {
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    /**
    ???? * Se crea metodo para crear rows de Consolidado de Pago
    ???? * @param
    ???? * @since 07/10/2016
    ???? */
    public void crearRowDetalleCargoPenalidadPrepago(BigDecimal penalidad, String nombreMoneda,
                                                     BigDecimal montoCargoCopres, Integer idTipoMonedaCopres,
                                                     BigDecimal montoCargoDaeci, Integer idTipoMonedaDaeci,
                                                     BigDecimal montoCargoMdc, Integer idTipoMonedaMdc,
                                                     BigDecimal montoCargoSepri, Integer idTipoMonedaSepri,
                                                     BigDecimal montoCargoCofi, Integer idTipoMonedaCofi,
                                                     BigDecimal montoCargoTramiteCofi, Integer idTipoMonedaTramiteCofi,
                                                     String nombreMonedaLocal, String nombreMonedaDolar) {

        logger.log(ADFLogger.WARNING, "INTO crearRowDetalleCargoPenalidadPrepago.");
        //Variables para insertar los montos de los distintos CONCEPTOS
        String areaTecnica = null;
        BigDecimal penalidadCofi = null;
        String nombreMonedaPenalidadCofi = null;
        BigDecimal CargoTramiteCofi = null;
        String nombreMonedaTramiteCofi = null;

        String nombreMonedaRol = null;
        BigDecimal montoCargoRol = null;

        Row rowDetalleCargoPenalidadPrepago = null;

        try {
            for (int i = 1; i < 6; i++) {
                logger.log(ADFLogger.WARNING, "Entra For." + i);
                rowDetalleCargoPenalidadPrepago = this.createRow();

                switch (i) {
                case 1:
                    areaTecnica = FenixModelConstants.PENALIDAD_CARGO_TRAMITE;
                    if (null != penalidad) {
                        penalidadCofi = penalidad;
                    } else {
                        logger.log(ADFLogger.WARNING, "La penalidad es nula.");
                    }
                    if (null != nombreMoneda) {
                        nombreMonedaPenalidadCofi = nombreMoneda;
                    } else {
                        logger.log(ADFLogger.WARNING, "El nombre de la moneda es nulo.");
                    }
                    if (null != montoCargoTramiteCofi) {
                        CargoTramiteCofi = montoCargoTramiteCofi;
                    } else {
                        logger.log(ADFLogger.WARNING, "El montoCargoTramiteCofi s nulo.");
                    }
                    if (null != idTipoMonedaTramiteCofi) {
                        if (idTipoMonedaTramiteCofi.compareTo(FenixModelConstants.MONEDA_USD) == 0) {
                            if (null != nombreMonedaDolar) {
                                nombreMonedaTramiteCofi = nombreMonedaDolar;
                            } else {
                                logger.log(ADFLogger.WARNING, "El valor de nombreMonedaDolar para COFI es nulo");
                            }
                        } else {
                            if (null != nombreMonedaLocal) {
                                nombreMonedaTramiteCofi = nombreMonedaLocal;
                            } else {
                                logger.log(ADFLogger.WARNING, "El valor de nombreMonedaLocal para COFI es nulo.");
                            }
                        }
                    } else {
                        logger.log(ADFLogger.WARNING, "El valor de idTipoMonedaTramiteCofi es nulo.");
                    }
                    if (null != montoCargoCofi) {
                        montoCargoRol = montoCargoCofi;
                    } else {
                        logger.log(ADFLogger.WARNING, "El valor de montoCargoCofi es nulo.");
                    }
                    logger.log(ADFLogger.WARNING, "Valor tipo moneda COFI :" + idTipoMonedaCofi);
                    logger.log(ADFLogger.WARNING, "Descripcion Dolar :" + nombreMonedaDolar);
                    logger.log(ADFLogger.WARNING, "Descripcion Moneda Local :" + nombreMonedaLocal);
                    if (null != idTipoMonedaCofi) {
                        if (idTipoMonedaCofi.compareTo(FenixModelConstants.MONEDA_USD) == 0) {
                            if (null != nombreMonedaDolar) {
                                nombreMonedaRol = nombreMonedaDolar;
                            } else {
                                logger.log(ADFLogger.WARNING, "El valor nombreMonedaDolar para COFI es nulo.");
                            }
                        } else {
                            if (null != nombreMonedaLocal) {
                                nombreMonedaRol = nombreMonedaLocal;
                            } else {
                                logger.log(ADFLogger.WARNING, "EL valor nombreMonedaLocal para COFI es nulo.");
                            }
                        }
                    } else {
                        logger.log(ADFLogger.WARNING, "El idTipoMonedaCofi es nulo.");
                    }
                    logger.log(ADFLogger.WARNING, "Valor tipo moneda COFI :" + nombreMonedaRol);
                    break;
                case 2:
                    areaTecnica = FenixModelConstants.ESTRUCTURACIONES;
                    penalidadCofi = null;
                    nombreMonedaPenalidadCofi = null;
                    CargoTramiteCofi = null;
                    nombreMonedaTramiteCofi = null;
                    if (null != montoCargoSepri) {
                        montoCargoRol = montoCargoSepri;
                    } else {
                        logger.log(ADFLogger.WARNING, "El valor de montoCargoSepri es nulo");
                    }
                    if (null != idTipoMonedaSepri) {
                        if (idTipoMonedaSepri.compareTo(FenixModelConstants.MONEDA_USD) == 0) {
                            if (null != nombreMonedaDolar) {
                                nombreMonedaRol = nombreMonedaDolar;
                            } else {
                                logger.log(ADFLogger.WARNING, "El valor nombreMonedaDolar para SEPRI es nulo.");
                            }
                        } else {
                            if (null != nombreMonedaLocal) {
                                nombreMonedaRol = nombreMonedaLocal;
                            } else {
                                logger.log(ADFLogger.WARNING, "EL valor nombreMonedaLocal para SEPRI es nulo.");
                            }
                        }
                    } else {
                        logger.log(ADFLogger.WARNING, "El valor de idTipoMonedaSepri es nulo.");
                    }
                    logger.log(ADFLogger.WARNING, "Valor tipo moneda SEPRI :" + nombreMonedaRol);
                    break;
                case 3:
                    areaTecnica = FenixModelConstants.COBERTURAS;
                    penalidadCofi = null;
                    nombreMonedaPenalidadCofi = null;
                    CargoTramiteCofi = null;
                    nombreMonedaTramiteCofi = null;
                    if (null != montoCargoMdc) {
                        montoCargoRol = montoCargoMdc;
                    } else {
                        logger.log(ADFLogger.WARNING, "El valor de montoCargoMdc es nulo.");
                    }
                    if (null != idTipoMonedaMdc) {
                        if (idTipoMonedaMdc.compareTo(FenixModelConstants.MONEDA_USD) == 0) {
                            if (null != nombreMonedaDolar) {
                                nombreMonedaRol = nombreMonedaDolar;
                            } else {
                                logger.log(ADFLogger.WARNING, "El valor nombreMonedaDolar para MDC es nulo.");
                            }
                        } else {
                            if (null != nombreMonedaLocal) {
                                nombreMonedaRol = nombreMonedaLocal;
                            } else {
                                logger.log(ADFLogger.WARNING, "EL valor nombreMonedaLocal para MDC es nulo.");
                            }
                        }
                    } else {
                        logger.log(ADFLogger.WARNING, "EL valor de idTipoMonedaMdc es nulo.");
                    }
                    logger.log(ADFLogger.WARNING, "Valor tipo moneda MDC :" + nombreMonedaRol);
                    break;
                case 4:
                    areaTecnica = FenixModelConstants.FUENTE_EXTERNA;
                    penalidadCofi = null;
                    nombreMonedaPenalidadCofi = null;
                    CargoTramiteCofi = null;
                    nombreMonedaTramiteCofi = null;
                    if (null != montoCargoDaeci) {
                        montoCargoRol = montoCargoDaeci;
                    } else {
                        logger.log(ADFLogger.WARNING, "El valor de montoCargoDaeci es nulo.");
                    }
                    if (null != idTipoMonedaDaeci) {
                        if (idTipoMonedaDaeci.compareTo(FenixModelConstants.MONEDA_USD) == 0) {
                            if (null != nombreMonedaDolar) {
                                nombreMonedaRol = nombreMonedaDolar;
                            } else {
                                logger.log(ADFLogger.WARNING, "El valor nombreMonedaDolar para DAECI es nulo.");
                            }
                        } else {
                            if (null != nombreMonedaLocal) {
                                nombreMonedaRol = nombreMonedaLocal;
                            } else {
                                logger.log(ADFLogger.WARNING, "EL valor nombreMonedaLocal para DAECI es nulo.");
                            }
                        }
                    } else {
                        logger.log(ADFLogger.WARNING, "El valor de idTipoMonedaDaeci es nulo.");
                    }
                    logger.log(ADFLogger.WARNING, "Valor tipo moneda DAECI :" + nombreMonedaRol);
                    break;
                case 5:
                    areaTecnica = FenixModelConstants.OTRO_CARGO_COPRES;
                    penalidadCofi = null;
                    nombreMonedaPenalidadCofi = null;
                    CargoTramiteCofi = null;
                    nombreMonedaTramiteCofi = null;
                    if (null != montoCargoCopres) {
                        montoCargoRol = montoCargoCopres;
                    } else {
                        logger.log(ADFLogger.WARNING, "El valor montoCargoCopres es nulo.");
                    }
                    if (null != idTipoMonedaCopres) {
                        if (idTipoMonedaCopres.compareTo(FenixModelConstants.MONEDA_USD) == 0) {
                            if (null != nombreMonedaDolar) {
                                nombreMonedaRol = nombreMonedaDolar;
                            } else {
                                logger.log(ADFLogger.WARNING, "El valor nombreMonedaDolar para COPRES es nulo.");
                            }
                        } else {
                            if (null != nombreMonedaLocal) {
                                nombreMonedaRol = nombreMonedaLocal;
                            } else {
                                logger.log(ADFLogger.WARNING, "EL valor nombreMonedaLocal para COPRES es nulo.");
                            }
                        }
                    } else {
                        logger.log(ADFLogger.WARNING, "El valor de idTipoMonedaCopres es nulo.");
                    }
                    logger.log(ADFLogger.WARNING, "Valor tipo moneda COPRES :" + nombreMonedaRol);
                    break;
                default:
                    break;
                }

                rowDetalleCargoPenalidadPrepago.setAttribute(DetalleCargoPenalidadPrepagoVORowImpl.AREATECNICA,
                                                             areaTecnica);
                rowDetalleCargoPenalidadPrepago.setAttribute(DetalleCargoPenalidadPrepagoVORowImpl.PENALIDAD,
                                                             penalidadCofi);
                rowDetalleCargoPenalidadPrepago.setAttribute(DetalleCargoPenalidadPrepagoVORowImpl.MONEDAPENALIDAD,
                                                             nombreMonedaPenalidadCofi);
                rowDetalleCargoPenalidadPrepago.setAttribute(DetalleCargoPenalidadPrepagoVORowImpl.CARGOTRAMITE,
                                                             CargoTramiteCofi);
                rowDetalleCargoPenalidadPrepago.setAttribute(DetalleCargoPenalidadPrepagoVORowImpl.MONEDACARGOTRAMITE,
                                                             nombreMonedaTramiteCofi);
                rowDetalleCargoPenalidadPrepago.setAttribute(DetalleCargoPenalidadPrepagoVORowImpl.OTROCARGO,
                                                             montoCargoRol);
                rowDetalleCargoPenalidadPrepago.setAttribute(DetalleCargoPenalidadPrepagoVORowImpl.MONEDAOTROCARGO,
                                                             nombreMonedaRol);

                this.insertRow(rowDetalleCargoPenalidadPrepago);
                this.setCurrentRow(rowDetalleCargoPenalidadPrepago);
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en crearRowConsolidadoPago." + e.getClass() + "." + e.getMessage());
        }
    }
}

