package org.bcie.fenix.common.model.vo.comentarios;

import java.sql.Timestamp;

public class ComentarioOperacionVOBean {
    
    public ComentarioOperacionVOBean() {
        super();
    }

    public ComentarioOperacionVOBean(Long Id, String Comentario, Timestamp FechaRegistro, String IdInstanciaTarea,
                                     Long IdOperacion, Integer IdTcaTarea, String LoginUsuario, String NombreUsuario,
                                     Integer BanEstatus, String Nombretarea, String Nombreproceso,
                                     Integer IdTcaTipoComentario) {
        super();
        this.Id = Id;
        this.Comentario = Comentario;
        this.FechaRegistro = FechaRegistro;
        this.IdInstanciaTarea = IdInstanciaTarea;
        this.IdOperacion = IdOperacion;
        this.IdTcaTarea = IdTcaTarea;
        this.LoginUsuario = LoginUsuario;
        this.NombreUsuario = NombreUsuario;
        this.BanEstatus = BanEstatus;
        this.Nombretarea = Nombretarea;
        this.Nombreproceso = Nombreproceso;
        this.IdTcaTipoComentario = IdTcaTipoComentario;
    }
    
    
    
    private Long Id;
    private String Comentario;
    private Timestamp FechaRegistro;
    private String IdInstanciaTarea;
    private Long IdOperacion;
    private Integer IdTcaTarea;
    private String LoginUsuario;
    private String NombreUsuario;
    private Integer BanEstatus;
    private String Nombretarea;
    private String Nombreproceso;
    private Integer IdTcaTipoComentario;

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("ID = ").append(this.Id).append("\n");
        str.append("COMENTARIO = ").append(this.Comentario).append("\n");
        str.append("FECHA_REGISTRO = ").append(this.FechaRegistro).append("\n");
        str.append("ID_INSTANCIA_TAREA = ").append(this.IdInstanciaTarea).append("\n");
        str.append("ID_OPERACION = ").append(this.IdOperacion).append("\n");
        str.append("ID_TCA_TAREA = ").append(this.IdTcaTarea).append("\n");
        str.append("LOGIN_USUARIO = ").append(this.LoginUsuario).append("\n");
        str.append("NOMBRE_USUARIO = ").append(this.NombreUsuario).append("\n");
        str.append("BAN_ESTATUS = ").append(this.BanEstatus).append("\n");
        str.append("NOMBRETAREA = ").append(this.Nombretarea).append("\n");
        str.append("NOMBREPROCESO = ").append(this.Nombreproceso).append("\n");
        str.append("ID_TCA_TIPO_COMENTARIO = ").append(this.IdTcaTipoComentario).append("\n");
        return  str.toString();
    }
    
    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getId() {
        return Id;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setFechaRegistro(Timestamp FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }

    public Timestamp getFechaRegistro() {
        return FechaRegistro;
    }

    public void setIdInstanciaTarea(String IdInstanciaTarea) {
        this.IdInstanciaTarea = IdInstanciaTarea;
    }

    public String getIdInstanciaTarea() {
        return IdInstanciaTarea;
    }

    public void setIdOperacion(Long IdOperacion) {
        this.IdOperacion = IdOperacion;
    }

    public Long getIdOperacion() {
        return IdOperacion;
    }

    public void setIdTcaTarea(Integer IdTcaTarea) {
        this.IdTcaTarea = IdTcaTarea;
    }

    public Integer getIdTcaTarea() {
        return IdTcaTarea;
    }

    public void setLoginUsuario(String LoginUsuario) {
        this.LoginUsuario = LoginUsuario;
    }

    public String getLoginUsuario() {
        return LoginUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setBanEstatus(Integer BanEstatus) {
        this.BanEstatus = BanEstatus;
    }

    public Integer getBanEstatus() {
        return BanEstatus;
    }

    public void setNombretarea(String Nombretarea) {
        this.Nombretarea = Nombretarea;
    }

    public String getNombretarea() {
        return Nombretarea;
    }

    public void setNombreproceso(String Nombreproceso) {
        this.Nombreproceso = Nombreproceso;
    }

    public String getNombreproceso() {
        return Nombreproceso;
    }

    public void setIdTcaTipoComentario(Integer IdTcaTipoComentario) {
        this.IdTcaTipoComentario = IdTcaTipoComentario;
    }

    public Integer getIdTcaTipoComentario() {
        return IdTcaTipoComentario;
    }
}
