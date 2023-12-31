package org.bcie.fenix.common.model.am;

import com.bcie.xmlns.solicitudaprobacionservice.SolicitudAprobacion12BndQSService;
import com.bcie.xmlns.solicitudaprobacionservice.SolicitudAprobacionPT;

import java.sql.SQLException;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.domain.Number;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.common.FenixAprobacionAM;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.utils.ModelUtils;
import org.bcie.fenix.common.model.vo.ConfiguracionVOImpl;
import org.bcie.fenix.common.model.vo.GrupoRolAprobacionVORowImpl;
import org.bcie.fenix.common.model.vo.MiembrosAprobacionVOImpl;
import org.bcie.fenix.common.model.vo.MiembrosAprobacionVORowImpl;
import org.bcie.fenix.common.model.vo.SolicitudAprobacionVOImpl;
import org.bcie.fenix.common.model.vo.aprobacion.ConsultaUsuarioReunionAprobacionVOImpl;
import org.bcie.fenix.common.model.vo.aprobacion.GestionComiteCreditoVOImpl;
import org.bcie.fenix.common.model.vo.aprobacion.GestionConNotificacionVOImpl;
import org.bcie.fenix.common.model.vo.aprobacion.MiembroComiteCreditoVOImpl;
import org.bcie.fenix.common.model.vo.aprobacion.UsuarioAprobacionVOImpl;
import org.bcie.solicitudaprobacionmo.ConfigurarSolicitudAprobacionRequestType;
import org.bcie.solicitudaprobacionmo.ConfigurarSolicitudAprobacionResponseType;
import org.bcie.solicitudaprobacionbo.SolicitudAprobacion;
import org.bcie.catalogobo.Catalogo;
import static org.bcie.fenix.common.model.FenixModelConstants.TIPO_MIEMBRO_COMITE_CREDITO;
import static org.bcie.fenix.common.model.FenixModelConstants.TIPO_MIEMBRO_EQUIPO_TRABAJO;
import static org.bcie.fenix.common.model.FenixModelConstants.TIPO_MIEMBRO_NOTIFICACION;
import org.bcie.fenix.common.model.vo.UsuarioReunionAprobacionVORowImpl;
import org.bcie.fenix.common.model.vo.aprobacion.MiembroComiteCreditoVORowImpl;
import org.bcie.fenix.common.model.vo.aprobacion.UsuarioAprobacionVORowImpl;
import org.bcie.solicitudaprobacionbo.ListadoUsuariosReunion;
import org.bcie.solicitudaprobacionbo.MiembroReunion;
import org.bcie.solicitudaprobacionbo.UsuarioReunion;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Aug 04 17:33:50 CDT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FenixAprobacionAMImpl extends ApplicationModuleImpl implements FenixAprobacionAM {
    
    private static ADFLogger logger = ADFLogger.createADFLogger(FenixAprobacionAMImpl.class);
    
    /**
     * This is the default constructor (do not remove).
     */
    public FenixAprobacionAMImpl() {
    }
    
    /**
     * Se obtienen los miembros del comite desde el servicio de ConfigurarSolicitudAprobacion.
     * @param idOperacion contiene id de Operacion
     * @param idNivelAprobacion define el nivel de aprobacion
     */
    public void configurarMiembrosPorOperacion(Long idOperacion) {
        logger.warning("Entrando en asignaMiembrosAprobacionPorOperacion");
        logger.warning("idOperacion: " + idOperacion);
        
        if (null != idOperacion) {
            //Instancia del Cliente WS
            ConfigurarSolicitudAprobacionResponseType response = null;
            response = getConfiguracionSolAprobWS(idOperacion, FenixModelConstants.NIVEL_APROBACION_COMITE_CREDITO_INT);
            
            //Valida objetos para obtener informacion de la Solicitud de Aprobacion
            if(response != null){
                cofigurarMiembrosCCPorOperacion(response);
                
                configurarUsuariosPorRoles(response);
            }
        }  
    }
    
    private void configurarUsuariosPorRoles(ConfigurarSolicitudAprobacionResponseType response) {
        HashMap<String, String> listaRoles = new HashMap<String, String>();
        List<MiembroReunion> listaComiteCredito = null;
        List<MiembroReunion> listaConNotificacion = null;
        List<MiembroReunion> listaEquipoTrabajo = null;

        // Obtencion de roles de comite de credito
        if (null != response && null != response.getListadoEmitirVoto() && response.getListadoEmitirVoto().size() > 0) {
            listaComiteCredito = response.getListadoEmitirVoto().get(0).getMiembroReunion();
            
            for (MiembroReunion miembroReunion : listaComiteCredito) {
                String rolBpm;
                try {
                    rolBpm = miembroReunion.getRol().getDescripcionCorta();
                    listaRoles.put(rolBpm, rolBpm);
                } catch (Exception e) {
                    logger.severe("Error al obtener los datos de Rol", e);
                }
            }
        }
        
        // Obtencion de roles de miembros con notificacion
        if (null != response && null != response.getListadoConNotificacion() && response.getListadoConNotificacion().size() > 0) {
            listaConNotificacion = response.getListadoConNotificacion().get(0).getMiembroReunion();
            
            for (MiembroReunion miembroReunion : listaConNotificacion) {
                String rolBpm;
                try {
                    rolBpm = miembroReunion.getRol().getDescripcionCorta();
                    listaRoles.put(rolBpm, rolBpm);
                } catch (Exception e) {
                    logger.severe("Error al obtener los datos de Rol", e);
                }
            }
        }
        
        // Obtencion de roles del equipo de trabajo
        if (null != response && null != response.getListadoEquipoTrabajo() && response.getListadoEquipoTrabajo().size() > 0) {
            listaEquipoTrabajo = response.getListadoEquipoTrabajo().get(0).getMiembroReunion();
            
            for (MiembroReunion miembroReunion : listaEquipoTrabajo) {
                String rolBpm;
                try {
                    rolBpm = miembroReunion.getRol().getDescripcionCorta();
                    listaRoles.put(rolBpm, rolBpm);
                } catch (Exception e) {
                    logger.severe("Error al obtener los datos de Rol", e);
                }
            }
        }
        
        if (listaRoles.size() > 0) {
            getUsuarioAprobacionVO().configurarUsuariosPorRoles(listaRoles);
        }
    }
    
    private void cofigurarMiembrosCCPorOperacion(ConfigurarSolicitudAprobacionResponseType response) {
        List<MiembroReunion> miembroReunionList = null;
        HashMap<String, String> listaRoles = new HashMap<String, String>();
        
        if (null != response && null != response.getListadoEmitirVoto() && response.getListadoEmitirVoto().size() > 0) {
            miembroReunionList = response.getListadoEmitirVoto().get(0).getMiembroReunion();
            
            List<SolicitudAprobacion> listaSolicitudAprob = response.getSolicitudAprobacion();
            SolicitudAprobacion solicitudAprobacion = null;

            if (null != listaSolicitudAprob && listaSolicitudAprob.size() > 0) {
                solicitudAprobacion = listaSolicitudAprob.get(0);
            }

            if (null != solicitudAprobacion) {
                for (MiembroReunion miembroReunion : miembroReunionList) {
                    Integer idTcaRolBpm = null;
                    String nombreRolBpm = null;
                    String rolBpm;
                    if (miembroReunion.getRol() != null) {
                        try {
                            idTcaRolBpm = miembroReunion.getRol().getId().intValue();
                            nombreRolBpm = miembroReunion.getRol().getDescripcion();
                            rolBpm = miembroReunion.getRol().getDescripcionCorta();
                            
                            listaRoles.put(rolBpm, rolBpm);
                        } catch (Exception e) {
                            logger.severe("Error al obtener los datos de Rol", e);
                        }
                    }

                    ListadoUsuariosReunion usuariosList = miembroReunion.getListadoUsuarios();
                    if (usuariosList != null) {
                        List<UsuarioReunion> usuarios = usuariosList.getUsuario();
                        if (usuarios != null && usuarios.size() > 0) {

                            for (UsuarioReunion usuario : usuarios) {

                                String nombreUsuario = usuario.getNombreUsuario();
                                String loginUsuario = usuario.getUsuario();

                                if (idTcaRolBpm != null && nombreRolBpm != null && nombreUsuario != null && loginUsuario != null) {
                                    
                                    UsuarioAprobacionVORowImpl usuarioAprobacion = (UsuarioAprobacionVORowImpl) getUsuarioAprobacionVO().createRow();
                                    usuarioAprobacion.setIdTcaRolBpm(idTcaRolBpm);
                                    usuarioAprobacion.setNombreUsuario(loginUsuario);
                                    usuarioAprobacion.setNombreCompleto(nombreUsuario);
                                    getUsuarioAprobacionVO().insertRow(usuarioAprobacion);

                                    MiembroComiteCreditoVORowImpl miembroComiteCredito =
                                        (MiembroComiteCreditoVORowImpl) getMiembroComiteCreditoVO().createRow();

                                    miembroComiteCredito.setBanEstatus(FenixModelConstants.BANESTATUS_TRUE);
                                    miembroComiteCredito.setConNotificacion(miembroReunion.isNotificar() ? 1 : 0);
                                    miembroComiteCredito.setEmiteVoto(miembroReunion.isEmiteVoto() ? 1 : 0);
                                    miembroComiteCredito.setFechaRegistro(new Timestamp(new Date().getTime()));
                                    miembroComiteCredito.setId(getMiembroComiteCreditoVO().getNextSequenceId());
                                    miembroComiteCredito.setIdSolicitudAprobacion(solicitudAprobacion.getIdSolicitudAprobacion());
                                    miembroComiteCredito.setIdTcaRolBpm(idTcaRolBpm);
                                    miembroComiteCredito.setDescripcionRol(nombreRolBpm);
                                    miembroComiteCredito.setIdTcaTipoUsuario(null);
                                    miembroComiteCredito.setLoginUsuario(loginUsuario);
                                    miembroComiteCredito.setMarcadoComoLeido(FenixModelConstants.BANESTATUS_FALSE);
                                    miembroComiteCredito.setNombreUsuario(nombreUsuario);

                                    getMiembroComiteCreditoVO().insertRow(miembroComiteCredito);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Container's getter for ConsultaUsuarioReunionAprobacionVO.
     * @return ConsultaUsuarioReunionAprobacionVO
     */
    public ConsultaUsuarioReunionAprobacionVOImpl getConsultaUsuarioReunionAprobacionVO() {
        return (ConsultaUsuarioReunionAprobacionVOImpl) findViewObject("ConsultaUsuarioReunionAprobacionVO");
    }

    /**
     * Container's getter for GestionComiteCreditoVO.
     * @return GestionComiteCreditoVO
     */
    public GestionComiteCreditoVOImpl getGestionComiteCreditoVO() {
        return (GestionComiteCreditoVOImpl) findViewObject("GestionComiteCreditoVO");
    }

    /**
     * Container's getter for GestionConNotificacionVO.
     * @return GestionConNotificacionVO
     */
    public GestionConNotificacionVOImpl getGestionConNotificacionVO() {
        return (GestionConNotificacionVOImpl) findViewObject("GestionConNotificacionVO");
    }

    /**
     * Container's getter for ConfiguracionVO.
     * @return ConfiguracionVO
     */
    public ConfiguracionVOImpl getConfiguracionVO() {
        return (ConfiguracionVOImpl) findViewObject("ConfiguracionVO");
    }

    /**
     * Container's getter for MiembrosAprobacionComiteCreditoVO.
     * @return MiembrosAprobacionComiteCreditoVO
     */
    public MiembrosAprobacionVOImpl getMiembrosAprobacionComiteCreditoVO() {
        return (MiembrosAprobacionVOImpl) findViewObject("MiembrosAprobacionComiteCreditoVO");
    }

    /**
     * Container's getter for SolicitudAprobacionVO.
     * @return SolicitudAprobacionVO
     */
    public SolicitudAprobacionVOImpl getSolicitudAprobacionVO() {
        return (SolicitudAprobacionVOImpl) findViewObject("SolicitudAprobacionVO");
    }

    /**
     * Container's getter for MiembroComiteCreditoVO1.
     * @return MiembroComiteCreditoVO1
     */
    public MiembroComiteCreditoVOImpl getMiembroComiteCreditoVO() {
        return (MiembroComiteCreditoVOImpl) findViewObject("MiembroComiteCreditoVO");
    }
    
    /**
     * Instancia WS y consume metodo de Configuracion de Solicitud de Aprobacion de Comite de Credito
     * @param idOperacion contiene id de operacion
     * @param nivelAprob contiene id del nivel de aprobacion
     * @return devuelve response del WS
     */
    private ConfigurarSolicitudAprobacionResponseType getConfiguracionSolAprobWS(Long idOperacion, Integer nivelAprob) {
        logger.entering(FenixAMImpl.class.getName(), "getConfiguracionSolAprobWS", new Object[] {
                        idOperacion, nivelAprob });

        ConfigurarSolicitudAprobacionRequestType confRequest = null;
        ConfigurarSolicitudAprobacionResponseType response = null;

        SolicitudAprobacionPT solicitudAprobacionPT = null;


        try {
            solicitudAprobacionPT = getInstanciaSolicitudAprobacionWS();

            confRequest = new ConfigurarSolicitudAprobacionRequestType();

            logger.warning("Ejecutando Operacion de WS: configurarSolicitudAprobacion");
            logger.warning("Id de Operacion: " + idOperacion);

            confRequest.setIdOperacion(idOperacion);
            confRequest.setNivelAprobacion(nivelAprob);

            Date horaInicio =
                ModelUtils.logStartWS(logger, confRequest, FenixModelConstants.WSC_CONFIGURAR_SOLICITUD_APROBACION);

            response = solicitudAprobacionPT.configurarSolicitudAprobacion(confRequest);

            ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_CONFIGURAR_SOLICITUD_APROBACION, horaInicio);
        } catch (Exception e) {
            logger.severe("Error al instanciar el servicio", e);
            throw new JboException(new Exception("Error al consumir el servicio. " + e.getMessage()));
        }

        logger.exiting(FenixAMImpl.class.getName(), "getConfiguracionSolAprobWS", new Object[] { response });
        return response;
    }
    
    /**
     * Crea instancia del WS de Solicitud de Aprobacion
     * @return devuelve objeto Client Port
     */
    private SolicitudAprobacionPT getInstanciaSolicitudAprobacionWS() {

        logger.entering(FenixAMImpl.class.getName(), "getInstanciaSolicitudAprobacionWS");

        SolicitudAprobacion12BndQSService solicitudAprobacion12BndQSService = null;
        SolicitudAprobacionPT solicitudAprobacionPT = null;

        try {
            String wsdl = getWsdl(IWsdlLocation.SOLICITUD_APROBACION);
            
            logger.warning("wsdl: " + wsdl);

            solicitudAprobacion12BndQSService =
                IWsdlLocation.Service.getInstance(SolicitudAprobacion12BndQSService.class, wsdl);
            solicitudAprobacionPT = solicitudAprobacion12BndQSService.getSolicitudAprobacion12BndQSPort();
            logger.warning("Instancia WS: " + solicitudAprobacionPT);
        } catch (Exception e) {
            logger.severe("Error al instanciar el servicio", e);
        }

        logger.exiting(FenixAMImpl.class.getName(), "getInstanciaSolicitudAprobacionWS", new Object[] {
                       solicitudAprobacionPT });
        return solicitudAprobacionPT;
    }
    
    public String getWsdl(String key) {
        ConfiguracionVOImpl vo = this.getConfiguracionVO();
        Row row = vo.getValor(key);
        String wsdl = row == null ? null : (String) row.getAttribute("Valor");
        return wsdl;
    }

    /**
     * Container's getter for UsuarioAprobacionVO1.
     * @return UsuarioAprobacionVO1
     */
    public UsuarioAprobacionVOImpl getUsuarioAprobacionVO() {
        return (UsuarioAprobacionVOImpl) findViewObject("UsuarioAprobacionVO");
    }
}

