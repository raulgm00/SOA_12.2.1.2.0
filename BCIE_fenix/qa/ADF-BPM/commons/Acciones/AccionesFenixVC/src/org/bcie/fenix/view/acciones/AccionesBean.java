package org.bcie.fenix.view.acciones;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import oracle.adf.model.BindingContext;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.JSFUtils;

public class AccionesBean implements Serializable{
    @SuppressWarnings("compatibility:-7345260795172461439")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;

    private List<String> listaPermisos;
    private String nombreCompleto;
    private Boolean rolIngresador;
    private Boolean rolValdiador;
    private Boolean rolOperador;
    private Boolean rolAsignadoAplica;
    private Boolean rolProcessControl;
    private Long idAccion;
    private String tituloNuevoEditarAccion;
    private Integer nuevaAccion;

    private Long idObservacion;
    private String observacion;
    private String rolActivo;

    private Integer estadoAnterior;
    private Integer categoria;
    private Integer categoriaInicio;
    private Integer estadoAccion;
    private Integer proceso;
    private Boolean botonCategoria;
    private Boolean requireValidacion;
    private String aviso;
    private Integer rolBPM;
    private String botonAccion;
    private Boolean catalogo1;
    private Boolean catalogo2;
    private Boolean catalogo3;
    private Boolean catalogo4;
    private Boolean catalogo5;
    private Boolean catalogo6;
    
    
    private Long idAccionSelected;
    
    private Boolean esEstadoCompletado;

    public AccionesBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }


    public List<String> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List<String> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Boolean getRolIngresador() {
        return rolIngresador;
    }

    public void setRolIngresador(Boolean rolIngresador) {
        this.rolIngresador = rolIngresador;
    }

    public Boolean getRolValdiador() {
        return rolValdiador;
    }

    public void setRolValdiador(Boolean rolValdiador) {
        this.rolValdiador = rolValdiador;
    }

    public Boolean getRolOperador() {
        return rolOperador;
    }

    public void setRolOperador(Boolean rolOperador) {
        this.rolOperador = rolOperador;
    }

    public Long getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(Long idAccion) {
        this.idAccion = idAccion;
    }

    public String getTituloNuevoEditarAccion() {
        return tituloNuevoEditarAccion;
    }

    public void setTituloNuevoEditarAccion(String tituloNuevoEditarAccion) {
        this.tituloNuevoEditarAccion = tituloNuevoEditarAccion;
    }

    public Long getIdObservacion() {
        return idObservacion;
    }

    public void setIdObservacion(Long idObservacion) {
        this.idObservacion = idObservacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(Integer estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getCategoriaInicio() {
        return categoriaInicio;
    }

    public void setCategoriaInicio(Integer categoriaInicio) {
        this.categoriaInicio = categoriaInicio;
    }

    public Integer getEstadoAccion() {
        return estadoAccion;
    }

    public void setEstadoAccion(Integer estadoAccion) {
        this.estadoAccion = estadoAccion;
    }

    public Integer getProceso() {
        return proceso;
    }

    public void setProceso(Integer proceso) {
        this.proceso = proceso;
    }


    public Boolean getRolAsignadoAplica() {
        return rolAsignadoAplica;
    }

    public void setRolAsignadoAplica(Boolean rolAsignadoAplica) {
        this.rolAsignadoAplica = rolAsignadoAplica;
    }

    public Boolean getRequireValidacion() {
        return requireValidacion;
    }

    public void setRequireValidacion(Boolean requireValidacion) {
        this.requireValidacion = requireValidacion;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public Integer getNuevaAccion() {
        return nuevaAccion;
    }

    public void setNuevaAccion(Integer nuevaAccion) {
        this.nuevaAccion = nuevaAccion;
    }

    public void setRolProcessControl(Boolean rolProcessControl) {
        this.rolProcessControl = rolProcessControl;
    }

    public Boolean getRolProcessControl() {
        return rolProcessControl;
    }

    public void setIdAccionSelected(Long idAccionSelected) {
        this.idAccionSelected = idAccionSelected;
    }

    public Long getIdAccionSelected() {
        return idAccionSelected;
    }

    public Map validaRoles() {

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("consultarAtributosUsuario");
        HashMap<String, Object> result = (HashMap<String, Object>) operationBinding.execute();

        logger.warning("resultado= " + result);
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return null;
        }
        return result;
    }

    public void cargarJRamirez() {
        //rolActivo=FenixConstants.ROL_INGRESADOR_VALIDADOR;
        rolActivo=FenixConstants.ROL_PROCCESS_CONTROL;
        listaPermisos = new ArrayList<String>();
        nombreCompleto = "J DAvid Ramirez";
        listaPermisos.add("FENIX_REPRESENTANTES");
        listaPermisos.add("FENIX_EJECUTIVO_SEPRI");
        listaPermisos.add("FENIX_ANALISTA_AECRED");
        listaPermisos.add("FENIX_ESPECIALISTA_SECTORIAL");
        listaPermisos.add("FENIX_GERENTE_PAIS");
        listaPermisos.add("FENIX_COORDINADOR_USC");
        listaPermisos.add("FENIX_ANALISTA_USC");
        listaPermisos.add("FENIX_ASJUR");
        listaPermisos.add("FENIX_JEFE_AREA_TECNICA");
        listaPermisos.add("FENIX_OFIC");
        listaPermisos.add("FENIX_ANALISTA_AED");
        listaPermisos.add("FENIX_RIESGOS");
        listaPermisos.add("FENIX_EJECUTIVO_DAECI");
        listaPermisos.add("FENIX_GERENTE_GERSYP");
        listaPermisos.add("FENIX_ANALISTA_SUPERVISION_CREDITO");
        listaPermisos.add("FENIX_ESPECIALISTA_AMBIENTAL");
        rolIngresador = Boolean.TRUE;
        rolValdiador = Boolean.TRUE;
        rolAsignadoAplica = Boolean.TRUE;
        rolProcessControl = Boolean.TRUE;
        logger.warning("ingresador= " + rolIngresador);
        logger.warning("validador= " + rolValdiador);
        logger.warning("rolAsignadoAplica= " + rolAsignadoAplica);
    }

    public void cargaRolesNombre() {
        String rolProces = "FENIX_PROCESS_CONTROL";
        String usuario=null;
        try{
        rolBPM=Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pRolUsuraio}").toString());
        }catch(Exception ex){
            logger.warning("Rol no obtenido");
            }
        try{
        requireValidacion =Boolean.parseBoolean(JSFUtils.resolveExpression("#{pageFlowScope.pRequiereValidacion}").toString());
           // (Boolean) JSFUtils.resolveExpression("#{pageFlowScope.pRequiereValidacion}");
        }catch(Exception ex){
            logger.warning("valdiacion no obtenido");
            }
            try{
        usuario = (String) JSFUtils.resolveExpression("#{pageFlowScope.pLoginUsuario}");
        }catch(Exception ex){
            logger.warning("login no obtenido");
            }
        Map<String, Object> obtenerNombreRoles = new HashMap<String, Object>();
        try{
        proceso =Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdProceso}").toString());
        }catch(Exception ex){
            logger.warning("proceso no obtenido");
            }
        String rolAValidar = null;
        String rolAValidar2 = null;
        rolAsignadoAplica = Boolean.FALSE;
        botonCategoria=Boolean.FALSE;
        rolIngresador = Boolean.FALSE;
        rolValdiador = Boolean.FALSE;
        rolActivo=FenixConstants.ROL_OTROS;
        Integer idRol=null;
        rolProcessControl = Boolean.FALSE;
        logger.warning("rolBPM= " + rolBPM);
        logger.warning("proceso= " + proceso);

        if (null != proceso) {
            switch (proceso) {
            case FenixConstants.PROCESO_UCE:
                catalogo1=Boolean.TRUE;
                catalogo2=Boolean.FALSE;
                categoria=FenixConstants.CATEGORIA_UCE;
                categoriaInicio=3;
                botonCategoria=Boolean.TRUE;
                rolAValidar = FenixConstants.ROL_ACCIONES_UCE;
                rolAValidar2 = "FENIX_APROBADOR_ACCIONES_UCE";
                idRol=FenixConstants.ID_ROL_INGRESADOR_VALDIADOR_UCE;
                break;
            case FenixConstants.PROCESO_SEGUIMIENTO:
                catalogo1=Boolean.TRUE;
                catalogo2=Boolean.FALSE;
                categoria=FenixConstants.CATEGORIA_SEGUIMIENTO;
                categoriaInicio=3;
                botonCategoria=Boolean.TRUE;
                rolAValidar = FenixConstants.ROL_ACCIONES_SEGUIMIENTO;
                idRol=FenixConstants.ID_ROL_INGRESADOR_VALDIADOR_SEGUIMIENTO;
                break;
            case FenixConstants.PROCESO_SUPERVISION:
                catalogo1=Boolean.TRUE;
                catalogo2=Boolean.FALSE;
                categoria=FenixConstants.CATEGORIA_SUPERVISION;
                categoriaInicio=3;
                botonCategoria=Boolean.TRUE;
                rolAValidar = FenixConstants.ROL_ACCIONES_SUPERVISION;
                idRol=FenixConstants.ID_ROL_INGRESADOR_VALDIADOR_SUPERVISION;
                break;
            case FenixConstants.PROCESO_SUPERVISION_AMBIENTAL_SOCIAL:
                    catalogo1=Boolean.TRUE;
                    catalogo2=Boolean.FALSE;
                    categoria=FenixConstants.CATEGORIA_SUPERVISION;
                    categoriaInicio=3;
                    botonCategoria=Boolean.TRUE;
                    rolAValidar = FenixConstants.ROL_ACCIONES_SUPERVISION_AS;
                    idRol=FenixConstants.ID_ROL_INGRESADOR_VALDIADOR_SUPERVISION_AS;
                    break;
            case FenixConstants.PROCESO_SIEMAS:
                catalogo1=Boolean.TRUE;
                catalogo2=Boolean.FALSE;
                categoria=FenixConstants.CATEGORIA_EVALUACION_SIEMAS;
                categoriaInicio=3;
                botonCategoria=Boolean.TRUE;
                rolAValidar = FenixConstants.ROL_ACCIONES_SIEMAS;
                idRol=FenixConstants.ID_ROL_INGRESADOR_VALDIADOR_SIEMAS;
                break;
            case FenixConstants.PROCESO_IBCIE:
                catalogo1=Boolean.TRUE;
                catalogo2=Boolean.FALSE;
                categoria=FenixConstants.CATEGORIA_EVALUACION_IBCIE;
                categoriaInicio=3;
                botonCategoria=Boolean.TRUE;
                rolAValidar = FenixConstants.ROL_ACCIONES_IBCIE;
                idRol=FenixConstants.ID_ROL_INGRESADOR_VALDIADOR_IBCIE;
                break;
            case FenixConstants.PROCESO_GESTOR_CLIENTES:
                catalogo1=Boolean.FALSE;
                catalogo2=Boolean.TRUE;
                categoriaInicio=1;
                rolAValidar = FenixConstants.ROL_ACCIONES_CLIENTE;
                idRol=FenixConstants.ID_ROL_INGRESADOR_VALDIADOR_GESTORCLIENTES;
                break;
            case FenixConstants.PROCESO_GESTOR_OPERACIONES:
                catalogo1=Boolean.FALSE;
                catalogo2=Boolean.TRUE;
                categoriaInicio=2;
                rolAValidar = FenixConstants.ROL_ACCIONES_OPERACION;
                idRol=FenixConstants.ID_ROL_INGRESADOR_VALDIADOR_GESTOROPERACION;
                break;
            default:
                catalogo1=Boolean.FALSE;
                catalogo2=Boolean.TRUE;
                categoriaInicio=2;
                rolAValidar = FenixConstants.ROL_ACCIONES_OPERACION;
                idRol=FenixConstants.ID_ROL_INGRESADOR_VALDIADOR_GESTOROPERACION;
                break;
            }
        }

      //  if (null != usuario && !usuario.equalsIgnoreCase("jramirez")) {
            if (null != usuario ) {
            if (null != rolAValidar) {

                obtenerNombreRoles = validaRoles();
                if (null != obtenerNombreRoles) {
                    listaPermisos = (List<String>) obtenerNombreRoles.get("listaGrupos");
                    if (null != listaPermisos) {
                        for (String rol : listaPermisos) {
                            logger.warning("Rol: " + rol);
                            if (rol.equalsIgnoreCase(rolAValidar)) {
                                rolActivo=FenixConstants.ROL_INGRESADOR_VALIDADOR;
                                rolIngresador = Boolean.TRUE;
                                rolValdiador = Boolean.TRUE;
                                logger.warning("Tiene rol ingresador o validador");
                                if(null==rolBPM && null!=idRol){ rolBPM= idRol;  }
                            }
                            if (null != rolAValidar2 && rol.equalsIgnoreCase(rolAValidar2)) {
                                rolOperador = Boolean.TRUE;
                            }
                            if (rol.equalsIgnoreCase(rolProces)) {
                                logger.warning("Tiene rol proccess control");
                                rolActivo=FenixConstants.ROL_PROCCESS_CONTROL;
                                rolIngresador = Boolean.TRUE;
                                rolValdiador = Boolean.TRUE;
                                rolProcessControl = Boolean.TRUE;
                                idRol=FenixConstants.ID_ROL_PROCESS_CONTROL;
                                if(null==rolBPM && null!=idRol){ rolBPM= idRol;  }
                                }
                        }
                    }
                    nombreCompleto = (String) obtenerNombreRoles.get("nombreUsuario");
                    logger.warning("Nombre: " + nombreCompleto);
                    logger.warning("Rol definitivo: " +rolBPM);
                }
            }
        } else {
            logger.warning("Error al obtener los roles");
           // cargarJRamirez();
        }

    }
    
    public void evaluarParametroPStateBpm(){
        logger.warning("Dentro de evaluarParametroPStateBpm");
        String state = null;
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pStateBpm}") != null) {
                state = (String) JSFUtils.resolveExpression("#{pageFlowScope.pStateBpm}");
                logger.warning("state :" + state);
                setEsEstadoCompletado(state.equals("COMPLETED") ? Boolean.TRUE : Boolean.FALSE);
            }else{
                logger.warning("pState es nulo");
                setEsEstadoCompletado(Boolean.FALSE);
            }
        } catch (Exception ex) {
            logger.severe("Error al recuperar #{pageFlowScope.pStateBpm} :",ex);
            logger.warning("pState no obtenido");
        }

        logger.warning("esEstadoCompletado : " + getEsEstadoCompletado());
        logger.warning("Fuera de evaluarParametroPStateBpm");
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }


    public String getRolActivo() {
        return rolActivo;
    }

    public void setRolActivo(String rolActivo) {
        this.rolActivo = rolActivo;
    }

    public Boolean getBotonCategoria() {
        return botonCategoria;
    }

    public void setBotonCategoria(Boolean botonCategoria) {
        this.botonCategoria = botonCategoria;
    }

    public Integer getRolBPM() {
        return rolBPM;
    }

    public void setRolBPM(Integer rolBPM) {
        this.rolBPM = rolBPM;
    }

    public String getBotonAccion() {
        return botonAccion;
    }

    public void setBotonAccion(String botonAccion) {
        this.botonAccion = botonAccion;
    }

    public Boolean getCatalogo1() {
        return catalogo1;
    }

    public void setCatalogo1(Boolean catalogo1) {
        this.catalogo1 = catalogo1;
    }

    public Boolean getCatalogo2() {
        return catalogo2;
    }

    public void setCatalogo2(Boolean catalogo2) {
        this.catalogo2 = catalogo2;
    }

    public Boolean getCatalogo3() {
        return catalogo3;
    }

    public void setCatalogo3(Boolean catalogo3) {
        this.catalogo3 = catalogo3;
    }

    public Boolean getCatalogo4() {
        return catalogo4;
    }

    public void setCatalogo4(Boolean catalogo4) {
        this.catalogo4 = catalogo4;
    }

    public Boolean getCatalogo5() {
        return catalogo5;
    }

    public void setCatalogo5(Boolean catalogo5) {
        this.catalogo5 = catalogo5;
    }

    public Boolean getCatalogo6() {
        return catalogo6;
    }

    public void setCatalogo6(Boolean catalogo6) {
        this.catalogo6 = catalogo6;
    }

    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }
}
