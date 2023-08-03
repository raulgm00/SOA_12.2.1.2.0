package pc04aprobaciongenerichumantask.bean;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import org.bcie.fenix.common.utils.JSFUtils;

public class GestionMiembrosAprobacionBean implements Serializable {
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = -1L;
    
    public static final ADFLogger logger = ADFLogger.createADFLogger(GestionMiembrosAprobacionBean.class);
    
    private Integer tareaBpm;
    private Long idSolicitud;
    
    public GestionMiembrosAprobacionBean() {
        super();
    }
    
    public void precarga() {
        logger.warning("Entrando en precargaMiembros.");
        
        cargarAtributos();
    }

    public void cargarAtributos() {
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pTareaBPM}") != null) {
                setTareaBpm(Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pTareaBPM}").toString()));
                logger.warning("Tarea: " + getTareaBpm());
            } else {
                logger.warning( "El valor de la Tarea es nulo.");
            }
            
            if (JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitud}") != null) {
                setIdSolicitud(Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitud}").toString()));
                
                logger.warning("Tarea: " + getIdSolicitud());
            } else {
                logger.warning( "El valor del IdSolicitud es nulo.");
            }
        } catch (Exception e) {
            logger.warning("Tarea no obtenida.", e);
        }
    }
    
    public Integer getTareaBpm() {
        return tareaBpm;
    }

    public void setTareaBpm(Integer tareaBpm) {
        this.tareaBpm = tareaBpm;
    }

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
}
