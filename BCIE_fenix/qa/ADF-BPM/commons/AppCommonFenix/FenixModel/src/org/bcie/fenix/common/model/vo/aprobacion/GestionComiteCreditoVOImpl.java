package org.bcie.fenix.common.model.vo.aprobacion;

import oracle.adf.share.logging.ADFLogger;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.server.ViewObjectImpl;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.FenixAprobacionAMImpl;
import org.bcie.fenix.common.model.vo.aprobacion.common.GestionComiteCreditoVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Aug 04 11:56:09 CDT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class GestionComiteCreditoVOImpl extends ViewObjectImpl implements GestionComiteCreditoVO {

    private ADFLogger logger = ADFLogger.createADFLogger(this.getClass());

    /**
     * This is the default constructor (do not remove).
     */
    public GestionComiteCreditoVOImpl() {
    }

    public void cargarMiembrosComiteCredito(Long idSolicitudAprobacion) {
        logger.warning("Entrando en cargarMiembrosComiteCredito.");
        logger.warning("idSolicitudAprobacion: " + idSolicitudAprobacion);

        ConsultaUsuarioReunionAprobacionVOImpl consultaUsuarioReunionVO =
            obtenerUsuarioReunionAprobacion(idSolicitudAprobacion);

        RowSetIterator rowSetIterator = consultaUsuarioReunionVO.createRowSetIterator(null);
        rowSetIterator.reset();

        while (rowSetIterator.hasNext()) {
            Row rowUsuarioReunion = rowSetIterator.next();

            Row row = createRow();
            row.setAttribute(GestionComiteCreditoVORowImpl.IDMIEMBRO,
                             rowUsuarioReunion.getAttribute(ConsultaUsuarioReunionAprobacionVORowImpl.IDMIEMBRO));
            row.setAttribute(GestionComiteCreditoVORowImpl.NOMBRE,
                             rowUsuarioReunion.getAttribute(ConsultaUsuarioReunionAprobacionVORowImpl.NOMBREUSUARIO));
            row.setAttribute(GestionComiteCreditoVORowImpl.DESCRIPCION,
                             rowUsuarioReunion.getAttribute(ConsultaUsuarioReunionAprobacionVORowImpl.DESCRIPCIONROL));
            row.setAttribute(GestionComiteCreditoVORowImpl.AGREGAR, Boolean.FALSE);
            row.setAttribute(GestionComiteCreditoVORowImpl.MODIFICAR, Boolean.FALSE);
            row.setAttribute(GestionComiteCreditoVORowImpl.ELIMINAR, Boolean.FALSE);
            
            insertRow(row);
        }

        rowSetIterator.closeRowSetIterator();
    }

    public ConsultaUsuarioReunionAprobacionVOImpl obtenerUsuarioReunionAprobacion(Long idSolicitudAprobacion) {
        FenixAprobacionAMImpl fenixAprobacionAM = (FenixAprobacionAMImpl) this.getRootApplicationModule();

        ConsultaUsuarioReunionAprobacionVOImpl consultaUsuarioReunionVO =
            fenixAprobacionAM.getConsultaUsuarioReunionAprobacionVO();

        consultaUsuarioReunionVO.setpIdSolicitudAprobacion(idSolicitudAprobacion);
        consultaUsuarioReunionVO.setpIdTcaTipoUsuario(FenixModelConstants.TIPO_MIEMBRO_COMITE_CREDITO);

        consultaUsuarioReunionVO.executeQuery();

        return consultaUsuarioReunionVO;
    }
}

