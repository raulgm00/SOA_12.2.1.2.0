package org.bcie.fenix.common.model.vo.detalleop;

import com.bcie.xmlns.operacionservice.Operacion12BndQSService;
import com.bcie.xmlns.operacionservice.Operacion12Port;

import java.util.Date;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.utils.ModelUtils;
import org.bcie.fenix.common.model.vo.DatosSupervisionVOImpl;
import org.bcie.fenix.common.model.vo.detalleop.common.CatalogoSectorialIBCIEVO;
import org.bcie.operacionbo.Operacion;
import org.bcie.operacionmo.ConsultarOperacionByIdOperacionRequestType;
import org.bcie.operacionmo.ConsultarOperacionResponseType;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Sep 20 10:21:03 CDT 2021
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CatalogoSectorialIBCIEVOImpl extends ViewObjectImpl implements CatalogoSectorialIBCIEVO {

    private static ADFLogger logger = null;

    /**
     * This is the default constructor (do not remove).
     */
    public CatalogoSectorialIBCIEVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public boolean obtenerDetalleOperacionById(Integer idOperacion) {

        logger.warning("inside Fenix-AM - obtenerDetalleOperacionById");
        Boolean existeError = Boolean.FALSE;
        String msgError = "";
        long startTime = System.currentTimeMillis();
        startTime = System.currentTimeMillis();
        logger.warning("Tiempo de inicio respuesta del: " + startTime);
        try {
            boolean banderaObtenerDetalleOperacion = false;
            String componenteConcecionalidad = null;
            String programadoPoa = null;
            DatosSupervisionVOImpl datosSupervisionVO = null;

            //Generacion de imstancia de FenixAM, para mandar a llamar el metodo getWsdl
            FenixAMImpl fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
            System.out.println("IWsdlLocation.WSDL_OPERACION = " + IWsdlLocation.OPERACION);
            String wsdl = fenixAMImpl.getWsdl(IWsdlLocation.OPERACION);
            System.out.println("wsdl = " + wsdl);


            if (idOperacion != null && idOperacion > 0) {
                //Creacion de una fila en memoria oara asignacion de data [roveniente del swrvicio
                //GetDetalleOperacionVOImpl row = (GetDetalleOperacionVOImpl) this.createRow();
                Row row = super.createRow();
                
                //Invocacion del webservices
                Operacion12BndQSService operacion12BndQSService =  IWsdlLocation.Service.getInstance(Operacion12BndQSService.class, wsdl);
                Operacion12Port operacion12Port = operacion12BndQSService.getOperacion12BndQSPort();
                try {
                    logger.log(ADFLogger.WARNING, ">HNWS" + operacion12Port.toString());
                } catch (Exception ex) {
                }
                //Request
                ConsultarOperacionByIdOperacionRequestType request = new ConsultarOperacionByIdOperacionRequestType();
                request.setIdOperacion( idOperacion );
                Date horaInicio = ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_OBTENER_DETALLE_OPERACION);

                //Response
                ConsultarOperacionResponseType obtenerOperacion = operacion12Port.consultarOperacionByIdOperacion(request);
                ModelUtils.logEndWS(logger, obtenerOperacion, FenixModelConstants.WSC_OBTENER_DETALLE_OPERACION, horaInicio);

                try {
                    if (obtenerOperacion.getResultado() != null && obtenerOperacion.getResultado().getResult().value() == "ERROR") {
                        existeError = Boolean.TRUE;
                    }

                    if (null != obtenerOperacion.getResultado().getMessage() && obtenerOperacion.getResultado().getMessage().trim().length() > 0) {
                        msgError = obtenerOperacion.getResultado().getMessage();
                    }
                } catch (Exception e) {
                    logger.warning("Error al verificar si el servicio consultarOperacionByIdOperacion devuelve un error para mostrarlo en pantalla");
                }

                if (!existeError) {
                    logger.warning("El servicio retorna OK... se comienzan las validaciones para cargar los campos en el Obj GetDetalleOperacionVO");
                    List<Operacion> listaOperqacionesObtenidas = obtenerOperacion.getOperacion();

                    if (!listaOperqacionesObtenidas.isEmpty()) {
                        banderaObtenerDetalleOperacion = false;
                        Operacion operacionUnica = listaOperqacionesObtenidas.get(0);
                        
                        row.setAttribute(CatalogoSectorialIBCIEVORowImpl.IDOPERACION, operacionUnica.getIdOperacion());
                        System.out.println("ID Operacion = " + operacionUnica.getIdOperacion());
                        System.out.println("�Es multisectorial? = " + operacionUnica.isEsMultisectorial().toString());
                        if ( operacionUnica.isEsMultisectorial()) {
                            row.setAttribute(CatalogoSectorialIBCIEVORowImpl.ESMULTISECTORIAL, 1);
                            //System.out.println("�Es ultisectorial?" + Boolean.TRUE);
                            banderaObtenerDetalleOperacion = false;

                        } else {
                            row.setAttribute(CatalogoSectorialIBCIEVORowImpl.ESMULTISECTORIAL, 0);
                            //System.out.println("�Es ultisectorial? = " + Boolean.FALSE);
                            //No es multisecyorial es candidata a evaluacion
                            banderaObtenerDetalleOperacion = true;

                        }
                        //Seteo de SectorIbcie
                        row.setAttribute(CatalogoSectorialIBCIEVORowImpl.IDSECTORIBCIE,operacionUnica.getClasificacionEstrategicaMultisectorial().getComponenteClasificacionEstrategica().get(0).getSectorIbcie().getId());
                        //System.out.println("ID Sector IBCIE = " + operacionUnica.getClasificacionEstrategicaMultisectorial().getComponenteClasificacionEstrategica().get(0).getSectorIbcie().getId());
                        row.setAttribute(CatalogoSectorialIBCIEVORowImpl.SECTORIBCIE,operacionUnica.getClasificacionEstrategicaMultisectorial().getComponenteClasificacionEstrategica().get(0).getSectorIbcie().getDescripcion());
                        //System.out.println("Descripcion Sector IBCIE = " + operacionUnica.getClasificacionEstrategicaMultisectorial().getComponenteClasificacionEstrategica().get(0).getSectorIbcie().getDescripcion());
                        //Seteo de Sub-SectorIbcie
                        row.setAttribute(CatalogoSectorialIBCIEVORowImpl.IDSUBSECTORIBCIE,operacionUnica.getClasificacionEstrategicaMultisectorial().getComponenteClasificacionEstrategica().get(0).getSubSectorIbcie().getId());
                        //System.out.println("ID Sub-Sector IBCIE = " + operacionUnica.getClasificacionEstrategicaMultisectorial().getComponenteClasificacionEstrategica().get(0).getSubSectorIbcie().getId());
                        row.setAttribute(CatalogoSectorialIBCIEVORowImpl.SUBSECTORIBCIE,operacionUnica.getClasificacionEstrategicaMultisectorial().getComponenteClasificacionEstrategica().get(0).getSubSectorIbcie().getDescripcion());
                        //System.out.println("Descripcion Sub-Sector IBCIE = " + operacionUnica.getClasificacionEstrategicaMultisectorial().getComponenteClasificacionEstrategica().get(0).getSubSectorIbcie().getDescripcion());
                        
                        //Generamos el objeto en Memoria.
                        this.insertRow(row);
                        this.setCurrentRow(row);


                    }else {
                        banderaObtenerDetalleOperacion = false;
                    }
                } else {
                    logger.warning("El servicio consultarOperacionByIdOperacion devuelve ERROR... no se cargan los valores para DetalleOperacionVO");
                }
            }
            long endTime = System.currentTimeMillis();
            logger.warning("Tiempo de fin respuesta: " + endTime);
            logger.warning("Tiempo de respuesta del metodo Inico metodo de adquisiciones " + (endTime - startTime) / 1000 + " segundos");

            return banderaObtenerDetalleOperacion;

        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error al carga detalle de la operacion", e);
        } finally {
            if (existeError) {
                logger.warning("Servicio consultarOperacionByIdOperacion devuelve ERROR >> " + msgError);
                JboException exception = new JboException("Servicio consultarOperacionByIdOperacion devuelve ERROR >> " + msgError);
                throw exception;
            }
        }

        return false;
    }


}


