package pc04aprobaciongenerichumantask.dto;

public class MiembroNotificacionDTO {
    private String usuario;
    private Long idOperacion;
    private Integer idPlantilla;
    private Long idSolicitudAprobacion;
    private Long idClienteFenix;
    private Integer idNivelAprobacion;
    private String idUsuarioReunion;
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Integer getIdPlantilla() {
        return idPlantilla;
    }

    public void setIdPlantilla(Integer idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public Long getIdSolicitudAprobacion() {
        return idSolicitudAprobacion;
    }

    public void setIdSolicitudAprobacion(Long idSolicitudAprobacion) {
        this.idSolicitudAprobacion = idSolicitudAprobacion;
    }

    public Long getIdClienteFenix() {
        return idClienteFenix;
    }

    public void setIdClienteFenix(Long idClienteFenix) {
        this.idClienteFenix = idClienteFenix;
    }

    public Integer getIdNivelAprobacion() {
        return idNivelAprobacion;
    }

    public void setIdNivelAprobacion(Integer idNivelAprobacion) {
        this.idNivelAprobacion = idNivelAprobacion;
    }

    public String getIdUsuarioReunion() {
        return idUsuarioReunion;
    }

    public void setIdUsuarioReunion(String idUsuarioReunion) {
        this.idUsuarioReunion = idUsuarioReunion;
    }
}
