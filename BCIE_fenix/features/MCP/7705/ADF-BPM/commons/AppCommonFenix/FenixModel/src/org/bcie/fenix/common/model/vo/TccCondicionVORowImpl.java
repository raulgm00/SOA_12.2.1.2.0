package org.bcie.fenix.common.model.vo;

import java.sql.Timestamp;

import oracle.jbo.RowIterator;
import oracle.jbo.RowSet;
import oracle.jbo.domain.Array;
import oracle.jbo.domain.Number;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Feb 10 16:18:26 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TccCondicionVORowImpl extends ViewRowImpl {


    public static final int ENTITY_CONDICIONEO = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        BanEstatus,
        Descripcion,
        FechaEnmienda,
        FechaFinal,
        FechaInicio,
        FechaRegistro,
        IdCondicionEnmendada,
        IdOperacion,
        IdTcaCondicion,
        IdTcaControlCondicion,
        IdTcaEstadoTcc,
        IdTcaFrecuenciaPlazo,
        IdTcaSubEstadoTcc,
        IdTcaTipoFechaInicio,
        Nombre,
        Plazo,
        IdCategoriaCondList,
        IdEventoCondList,
        DescCategoriaCondList,
        DescEventosCondList,
        DescObservacionPrincipal,
        EditableFormalizacion,
        DispensaEnmienda,
        IdTcaTipoDesembolso,
        IdEvento,
        TreTcaEventoCondicionVO,
        TreCategoriaCondicionVO,
        TccObservacionCondicionVO,
        CatTipoFrecuenciaVO1,
        TcaEstadoTccVO1,
        TcaSubEstadoTccVO1,
        TcaCondicionLOV1,
        TccTcaControlCondicionLOV1,
        TccTipoFechaInicioLOV1,
        TcaTipoDesembolsoVO;
        static AttributesEnum[] vals = null;
        ;
        private static final int firstIndex = 0;

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int ID = AttributesEnum.Id.index();
    public static final int BANESTATUS = AttributesEnum.BanEstatus.index();
    public static final int DESCRIPCION = AttributesEnum.Descripcion.index();
    public static final int FECHAENMIENDA = AttributesEnum.FechaEnmienda.index();
    public static final int FECHAFINAL = AttributesEnum.FechaFinal.index();
    public static final int FECHAINICIO = AttributesEnum.FechaInicio.index();
    public static final int FECHAREGISTRO = AttributesEnum.FechaRegistro.index();
    public static final int IDCONDICIONENMENDADA = AttributesEnum.IdCondicionEnmendada.index();
    public static final int IDOPERACION = AttributesEnum.IdOperacion.index();
    public static final int IDTCACONDICION = AttributesEnum.IdTcaCondicion.index();
    public static final int IDTCACONTROLCONDICION = AttributesEnum.IdTcaControlCondicion.index();
    public static final int IDTCAESTADOTCC = AttributesEnum.IdTcaEstadoTcc.index();
    public static final int IDTCAFRECUENCIAPLAZO = AttributesEnum.IdTcaFrecuenciaPlazo.index();
    public static final int IDTCASUBESTADOTCC = AttributesEnum.IdTcaSubEstadoTcc.index();
    public static final int IDTCATIPOFECHAINICIO = AttributesEnum.IdTcaTipoFechaInicio.index();
    public static final int NOMBRE = AttributesEnum.Nombre.index();
    public static final int PLAZO = AttributesEnum.Plazo.index();
    public static final int IDCATEGORIACONDLIST = AttributesEnum.IdCategoriaCondList.index();
    public static final int IDEVENTOCONDLIST = AttributesEnum.IdEventoCondList.index();
    public static final int DESCCATEGORIACONDLIST = AttributesEnum.DescCategoriaCondList.index();
    public static final int DESCEVENTOSCONDLIST = AttributesEnum.DescEventosCondList.index();
    public static final int DESCOBSERVACIONPRINCIPAL = AttributesEnum.DescObservacionPrincipal.index();
    public static final int EDITABLEFORMALIZACION = AttributesEnum.EditableFormalizacion.index();
    public static final int DISPENSAENMIENDA = AttributesEnum.DispensaEnmienda.index();
    public static final int IDTCATIPODESEMBOLSO = AttributesEnum.IdTcaTipoDesembolso.index();
    public static final int IDEVENTO = AttributesEnum.IdEvento.index();
    public static final int TRETCAEVENTOCONDICIONVO = AttributesEnum.TreTcaEventoCondicionVO.index();
    public static final int TRECATEGORIACONDICIONVO = AttributesEnum.TreCategoriaCondicionVO.index();
    public static final int TCCOBSERVACIONCONDICIONVO = AttributesEnum.TccObservacionCondicionVO.index();
    public static final int CATTIPOFRECUENCIAVO1 = AttributesEnum.CatTipoFrecuenciaVO1.index();
    public static final int TCAESTADOTCCVO1 = AttributesEnum.TcaEstadoTccVO1.index();
    public static final int TCASUBESTADOTCCVO1 = AttributesEnum.TcaSubEstadoTccVO1.index();
    public static final int TCACONDICIONLOV1 = AttributesEnum.TcaCondicionLOV1.index();
    public static final int TCCTCACONTROLCONDICIONLOV1 = AttributesEnum.TccTcaControlCondicionLOV1.index();
    public static final int TCCTIPOFECHAINICIOLOV1 = AttributesEnum.TccTipoFechaInicioLOV1.index();
    public static final int TCATIPODESEMBOLSOVO = AttributesEnum.TcaTipoDesembolsoVO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public TccCondicionVORowImpl() {
    }

    /**
     * Gets CondicionEO entity object.
     * @return the CondicionEO
     */
    public EntityImpl getCondicionEO() {
        return (EntityImpl) getEntity(ENTITY_CONDICIONEO);
    }

    /**
     * Gets the attribute value for ID using the alias name Id.
     * @return the ID
     */
    public Long getId() {
        return (Long) getAttributeInternal(ID);
    }

    /**
     * Sets <code>value</code> as attribute value for ID using the alias name Id.
     * @param value value to set the ID
     */
    public void setId(Long value) {
        setAttributeInternal(ID, value);
    }

    /**
     * Gets the attribute value for BAN_ESTATUS using the alias name BanEstatus.
     * @return the BAN_ESTATUS
     */
    public Integer getBanEstatus() {
        return (Integer) getAttributeInternal(BANESTATUS);
    }

    /**
     * Sets <code>value</code> as attribute value for BAN_ESTATUS using the alias name BanEstatus.
     * @param value value to set the BAN_ESTATUS
     */
    public void setBanEstatus(Integer value) {
        setAttributeInternal(BANESTATUS, value);
    }

    /**
     * Gets the attribute value for DESCRIPCION using the alias name Descripcion.
     * @return the DESCRIPCION
     */
    public String getDescripcion() {
        return (String) getAttributeInternal(DESCRIPCION);
    }

    /**
     * Sets <code>value</code> as attribute value for DESCRIPCION using the alias name Descripcion.
     * @param value value to set the DESCRIPCION
     */
    public void setDescripcion(String value) {
        setAttributeInternal(DESCRIPCION, value);
    }

    /**
     * Gets the attribute value for FECHA_ENMIENDA using the alias name FechaEnmienda.
     * @return the FECHA_ENMIENDA
     */
    public Timestamp getFechaEnmienda() {
        return (Timestamp) getAttributeInternal(FECHAENMIENDA);
    }

    /**
     * Sets <code>value</code> as attribute value for FECHA_ENMIENDA using the alias name FechaEnmienda.
     * @param value value to set the FECHA_ENMIENDA
     */
    public void setFechaEnmienda(Timestamp value) {
        setAttributeInternal(FECHAENMIENDA, value);
    }

    /**
     * Gets the attribute value for FECHA_FINAL using the alias name FechaFinal.
     * @return the FECHA_FINAL
     */
    public Timestamp getFechaFinal() {
        return (Timestamp) getAttributeInternal(FECHAFINAL);
    }

    /**
     * Sets <code>value</code> as attribute value for FECHA_FINAL using the alias name FechaFinal.
     * @param value value to set the FECHA_FINAL
     */
    public void setFechaFinal(Timestamp value) {
        setAttributeInternal(FECHAFINAL, value);
    }

    /**
     * Gets the attribute value for FECHA_INICIO using the alias name FechaInicio.
     * @return the FECHA_INICIO
     */
    public Timestamp getFechaInicio() {
        return (Timestamp) getAttributeInternal(FECHAINICIO);
    }

    /**
     * Sets <code>value</code> as attribute value for FECHA_INICIO using the alias name FechaInicio.
     * @param value value to set the FECHA_INICIO
     */
    public void setFechaInicio(Timestamp value) {
        setAttributeInternal(FECHAINICIO, value);
    }

    /**
     * Gets the attribute value for FECHA_REGISTRO using the alias name FechaRegistro.
     * @return the FECHA_REGISTRO
     */
    public Timestamp getFechaRegistro() {
        return (Timestamp) getAttributeInternal(FECHAREGISTRO);
    }

    /**
     * Sets <code>value</code> as attribute value for FECHA_REGISTRO using the alias name FechaRegistro.
     * @param value value to set the FECHA_REGISTRO
     */
    public void setFechaRegistro(Timestamp value) {
        setAttributeInternal(FECHAREGISTRO, value);
    }

    /**
     * Gets the attribute value for ID_CONDICION_ENMENDADA using the alias name IdCondicionEnmendada.
     * @return the ID_CONDICION_ENMENDADA
     */
    public Long getIdCondicionEnmendada() {
        return (Long) getAttributeInternal(IDCONDICIONENMENDADA);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_CONDICION_ENMENDADA using the alias name IdCondicionEnmendada.
     * @param value value to set the ID_CONDICION_ENMENDADA
     */
    public void setIdCondicionEnmendada(Long value) {
        setAttributeInternal(IDCONDICIONENMENDADA, value);
    }

    /**
     * Gets the attribute value for ID_OPERACION using the alias name IdOperacion.
     * @return the ID_OPERACION
     */
    public Long getIdOperacion() {
        return (Long) getAttributeInternal(IDOPERACION);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_OPERACION using the alias name IdOperacion.
     * @param value value to set the ID_OPERACION
     */
    public void setIdOperacion(Long value) {
        setAttributeInternal(IDOPERACION, value);
    }

    /**
     * Gets the attribute value for ID_TCA_CONDICION using the alias name IdTcaCondicion.
     * @return the ID_TCA_CONDICION
     */
    public Integer getIdTcaCondicion() {
        return (Integer) getAttributeInternal(IDTCACONDICION);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_TCA_CONDICION using the alias name IdTcaCondicion.
     * @param value value to set the ID_TCA_CONDICION
     */
    public void setIdTcaCondicion(Integer value) {
        setAttributeInternal(IDTCACONDICION, value);
    }

    /**
     * Gets the attribute value for ID_TCA_CONTROL_CONDICION using the alias name IdTcaControlCondicion.
     * @return the ID_TCA_CONTROL_CONDICION
     */
    public Integer getIdTcaControlCondicion() {
        return (Integer) getAttributeInternal(IDTCACONTROLCONDICION);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_TCA_CONTROL_CONDICION using the alias name IdTcaControlCondicion.
     * @param value value to set the ID_TCA_CONTROL_CONDICION
     */
    public void setIdTcaControlCondicion(Integer value) {
        setAttributeInternal(IDTCACONTROLCONDICION, value);
    }

    /**
     * Gets the attribute value for ID_TCA_ESTADO_TCC using the alias name IdTcaEstadoTcc.
     * @return the ID_TCA_ESTADO_TCC
     */
    public Integer getIdTcaEstadoTcc() {
        return (Integer) getAttributeInternal(IDTCAESTADOTCC);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_TCA_ESTADO_TCC using the alias name IdTcaEstadoTcc.
     * @param value value to set the ID_TCA_ESTADO_TCC
     */
    public void setIdTcaEstadoTcc(Integer value) {
        setAttributeInternal(IDTCAESTADOTCC, value);
    }

    /**
     * Gets the attribute value for ID_TCA_FRECUENCIA_PLAZO using the alias name IdTcaFrecuenciaPlazo.
     * @return the ID_TCA_FRECUENCIA_PLAZO
     */
    public Integer getIdTcaFrecuenciaPlazo() {
        return (Integer) getAttributeInternal(IDTCAFRECUENCIAPLAZO);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_TCA_FRECUENCIA_PLAZO using the alias name IdTcaFrecuenciaPlazo.
     * @param value value to set the ID_TCA_FRECUENCIA_PLAZO
     */
    public void setIdTcaFrecuenciaPlazo(Integer value) {
        setAttributeInternal(IDTCAFRECUENCIAPLAZO, value);
    }

    /**
     * Gets the attribute value for ID_TCA_SUB_ESTADO_TCC using the alias name IdTcaSubEstadoTcc.
     * @return the ID_TCA_SUB_ESTADO_TCC
     */
    public Integer getIdTcaSubEstadoTcc() {
        return (Integer) getAttributeInternal(IDTCASUBESTADOTCC);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_TCA_SUB_ESTADO_TCC using the alias name IdTcaSubEstadoTcc.
     * @param value value to set the ID_TCA_SUB_ESTADO_TCC
     */
    public void setIdTcaSubEstadoTcc(Integer value) {
        setAttributeInternal(IDTCASUBESTADOTCC, value);
    }

    /**
     * Gets the attribute value for ID_TCA_TIPO_FECHA_INICIO using the alias name IdTcaTipoFechaInicio.
     * @return the ID_TCA_TIPO_FECHA_INICIO
     */
    public Integer getIdTcaTipoFechaInicio() {
        return (Integer) getAttributeInternal(IDTCATIPOFECHAINICIO);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_TCA_TIPO_FECHA_INICIO using the alias name IdTcaTipoFechaInicio.
     * @param value value to set the ID_TCA_TIPO_FECHA_INICIO
     */
    public void setIdTcaTipoFechaInicio(Integer value) {
        setAttributeInternal(IDTCATIPOFECHAINICIO, value);
    }

    /**
     * Gets the attribute value for NOMBRE using the alias name Nombre.
     * @return the NOMBRE
     */
    public String getNombre() {
        return (String) getAttributeInternal(NOMBRE);
    }

    /**
     * Sets <code>value</code> as attribute value for NOMBRE using the alias name Nombre.
     * @param value value to set the NOMBRE
     */
    public void setNombre(String value) {
        setAttributeInternal(NOMBRE, value);
    }

    /**
     * Gets the attribute value for PLAZO using the alias name Plazo.
     * @return the PLAZO
     */
    public Integer getPlazo() {
        return (Integer) getAttributeInternal(PLAZO);
    }

    /**
     * Sets <code>value</code> as attribute value for PLAZO using the alias name Plazo.
     * @param value value to set the PLAZO
     */
    public void setPlazo(Integer value) {
        setAttributeInternal(PLAZO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdCategoriaCondList.
     * @return the IdCategoriaCondList
     */
    public Array getIdCategoriaCondList() {
        return (Array) getAttributeInternal(IDCATEGORIACONDLIST);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdCategoriaCondList.
     * @param value value to set the  IdCategoriaCondList
     */
    public void setIdCategoriaCondList(Array value) {
        setAttributeInternal(IDCATEGORIACONDLIST, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdEventoCondList.
     * @return the IdEventoCondList
     */
    public Array getIdEventoCondList() {
        return (Array) getAttributeInternal(IDEVENTOCONDLIST);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdEventoCondList.
     * @param value value to set the  IdEventoCondList
     */
    public void setIdEventoCondList(Array value) {
        setAttributeInternal(IDEVENTOCONDLIST, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DescCategoriaList.
     * @return the DescCategoriaList
     */
    public String getDescCategoriaCondList() {
        return (String) getAttributeInternal(DESCCATEGORIACONDLIST);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DescCategoriaCondList.
     * @param value value to set the  DescCategoriaCondList
     */
    public void setDescCategoriaCondList(String value) {
        setAttributeInternal(DESCCATEGORIACONDLIST, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DescEventosCondList.
     * @return the DescEventosCondList
     */
    public String getDescEventosCondList() {
        return (String) getAttributeInternal(DESCEVENTOSCONDLIST);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DescEventosCondList.
     * @param value value to set the  DescEventosCondList
     */
    public void setDescEventosCondList(String value) {
        setAttributeInternal(DESCEVENTOSCONDLIST, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DescObservacionPrincipal.
     * @return the DescObservacionPrincipal
     */
    public String getDescObservacionPrincipal() {
        return (String) getAttributeInternal(DESCOBSERVACIONPRINCIPAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DescObservacionPrincipal.
     * @param value value to set the  DescObservacionPrincipal
     */
    public void setDescObservacionPrincipal(String value) {
        setAttributeInternal(DESCOBSERVACIONPRINCIPAL, value);
    }

    /**
     * Gets the attribute value for the calculated attribute EditableFormalizacion.
     * @return the EditableFormalizacion
     */
    public Boolean getEditableFormalizacion() {
        return (Boolean) getAttributeInternal(EDITABLEFORMALIZACION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute EditableFormalizacion.
     * @param value value to set the  EditableFormalizacion
     */
    public void setEditableFormalizacion(Boolean value) {
        setAttributeInternal(EDITABLEFORMALIZACION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DispensaEnmienda.
     * @return the DispensaEnmienda
     */
    public Boolean getDispensaEnmienda() {
        return (Boolean) getAttributeInternal(DISPENSAENMIENDA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DispensaEnmienda.
     * @param value value to set the  DispensaEnmienda
     */
    public void setDispensaEnmienda(Boolean value) {
        setAttributeInternal(DISPENSAENMIENDA, value);
    }

    /**
     * Gets the attribute value for ID_TCA_TIPO_DESEMBOLSO using the alias name IdTcaTipoDesembolso.
     * @return the ID_TCA_TIPO_DESEMBOLSO
     */
    public Integer getIdTcaTipoDesembolso() {
        return (Integer) getAttributeInternal(IDTCATIPODESEMBOLSO);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_TCA_TIPO_DESEMBOLSO using the alias name IdTcaTipoDesembolso.
     * @param value value to set the ID_TCA_TIPO_DESEMBOLSO
     */
    public void setIdTcaTipoDesembolso(Integer value) {
        setAttributeInternal(IDTCATIPODESEMBOLSO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdEvento.
     * @return the IdEvento
     */
    public Number getIdEvento() {
        return (Number) getAttributeInternal(IDEVENTO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdEvento.
     * @param value value to set the  IdEvento
     */
    public void setIdEvento(Number value) {
        setAttributeInternal(IDEVENTO, value);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link TreTcaEventoCondicionVO.
     */
    public RowIterator getTreTcaEventoCondicionVO() {
        return (RowIterator) getAttributeInternal(TRETCAEVENTOCONDICIONVO);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link TreCategoriaCondicionVO.
     */
    public RowIterator getTreCategoriaCondicionVO() {
        return (RowIterator) getAttributeInternal(TRECATEGORIACONDICIONVO);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link TccObservacionCondicionVO.
     */
    public RowIterator getTccObservacionCondicionVO() {
        return (RowIterator) getAttributeInternal(TCCOBSERVACIONCONDICIONVO);
    }

    /**
     * Gets the view accessor <code>RowSet</code> CatTipoFrecuenciaVO1.
     */
    public RowSet getCatTipoFrecuenciaVO1() {
        return (RowSet) getAttributeInternal(CATTIPOFRECUENCIAVO1);
    }


    /**
     * Gets the view accessor <code>RowSet</code> TcaEstadoTccVO1.
     */
    public RowSet getTcaEstadoTccVO1() {
        return (RowSet) getAttributeInternal(TCAESTADOTCCVO1);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TcaSubEstadoTccVO1.
     */
    public RowSet getTcaSubEstadoTccVO1() {
        return (RowSet) getAttributeInternal(TCASUBESTADOTCCVO1);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TcaCondicionLOV1.
     */
    public RowSet getTcaCondicionLOV1() {
        return (RowSet) getAttributeInternal(TCACONDICIONLOV1);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TccTcaControlCondicionLOV1.
     */
    public RowSet getTccTcaControlCondicionLOV1() {
        return (RowSet) getAttributeInternal(TCCTCACONTROLCONDICIONLOV1);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TccTipoFechaInicioLOV1.
     */
    public RowSet getTccTipoFechaInicioLOV1() {
        return (RowSet) getAttributeInternal(TCCTIPOFECHAINICIOLOV1);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TcaTipoDesembolsoVO.
     */
    public RowSet getTcaTipoDesembolsoVO() {
        return (RowSet) getAttributeInternal(TCATIPODESEMBOLSOVO);
    }
}

