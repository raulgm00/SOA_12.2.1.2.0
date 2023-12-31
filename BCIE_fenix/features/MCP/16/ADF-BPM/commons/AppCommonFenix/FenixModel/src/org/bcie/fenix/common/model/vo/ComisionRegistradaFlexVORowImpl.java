package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jul 18 17:18:01 CDT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ComisionRegistradaFlexVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        DescripcionTipo,
        Descripcion,
        ConcatTcasComision,
        DescripcionCorta,
        IdTcaTipoComision,
        CodExterno,
        IdComision,
        IdTcaEstadoTcc,
        IdTcaSubEstadoTcc,
        DescripcionEstado,
        DescripcionSubEstado,
        Nombre,
        Cantidad;
        private static AttributesEnum[] vals = null;
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
    public static final int DESCRIPCIONTIPO = AttributesEnum.DescripcionTipo.index();
    public static final int DESCRIPCION = AttributesEnum.Descripcion.index();
    public static final int CONCATTCASCOMISION = AttributesEnum.ConcatTcasComision.index();
    public static final int DESCRIPCIONCORTA = AttributesEnum.DescripcionCorta.index();
    public static final int IDTCATIPOCOMISION = AttributesEnum.IdTcaTipoComision.index();
    public static final int CODEXTERNO = AttributesEnum.CodExterno.index();
    public static final int IDCOMISION = AttributesEnum.IdComision.index();
    public static final int IDTCAESTADOTCC = AttributesEnum.IdTcaEstadoTcc.index();
    public static final int IDTCASUBESTADOTCC = AttributesEnum.IdTcaSubEstadoTcc.index();
    public static final int DESCRIPCIONESTADO = AttributesEnum.DescripcionEstado.index();
    public static final int DESCRIPCIONSUBESTADO = AttributesEnum.DescripcionSubEstado.index();
    public static final int NOMBRE = AttributesEnum.Nombre.index();
    public static final int CANTIDAD = AttributesEnum.Cantidad.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ComisionRegistradaFlexVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute Id.
     * @return the Id
     */
    public Integer getId() {
        return (Integer) getAttributeInternal(ID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Id.
     * @param value value to set the  Id
     */
    public void setId(Integer value) {
        setAttributeInternal(ID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DescripcionTipo.
     * @return the DescripcionTipo
     */
    public String getDescripcionTipo() {
        return (String) getAttributeInternal(DESCRIPCIONTIPO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DescripcionTipo.
     * @param value value to set the  DescripcionTipo
     */
    public void setDescripcionTipo(String value) {
        setAttributeInternal(DESCRIPCIONTIPO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Descripcion.
     * @return the Descripcion
     */
    public String getDescripcion() {
        return (String) getAttributeInternal(DESCRIPCION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Descripcion.
     * @param value value to set the  Descripcion
     */
    public void setDescripcion(String value) {
        setAttributeInternal(DESCRIPCION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ConcatTcasComision.
     * @return the ConcatTcasComision
     */
    public String getConcatTcasComision() {
        return (String) getAttributeInternal(CONCATTCASCOMISION);
    }

    /**
     * Gets the attribute value for the calculated attribute DescripcionCorta.
     * @return the DescripcionCorta
     */
    public String getDescripcionCorta() {
        return (String) getAttributeInternal(DESCRIPCIONCORTA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DescripcionCorta.
     * @param value value to set the  DescripcionCorta
     */
    public void setDescripcionCorta(String value) {
        setAttributeInternal(DESCRIPCIONCORTA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdTcaTipoComision.
     * @return the IdTcaTipoComision
     */
    public Integer getIdTcaTipoComision() {
        return (Integer) getAttributeInternal(IDTCATIPOCOMISION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdTcaTipoComision.
     * @param value value to set the  IdTcaTipoComision
     */
    public void setIdTcaTipoComision(Integer value) {
        setAttributeInternal(IDTCATIPOCOMISION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CodExterno.
     * @return the CodExterno
     */
    public String getCodExterno() {
        return (String) getAttributeInternal(CODEXTERNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CodExterno.
     * @param value value to set the  CodExterno
     */
    public void setCodExterno(String value) {
        setAttributeInternal(CODEXTERNO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdComision.
     * @return the IdComision
     */
    public Long getIdComision() {
        return (Long) getAttributeInternal(IDCOMISION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdComision.
     * @param value value to set the  IdComision
     */
    public void setIdComision(Long value) {
        setAttributeInternal(IDCOMISION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdTcaEstadoTcc.
     * @return the IdTcaEstadoTcc
     */
    public Integer getIdTcaEstadoTcc() {
        return (Integer) getAttributeInternal(IDTCAESTADOTCC);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdTcaEstadoTcc.
     * @param value value to set the  IdTcaEstadoTcc
     */
    public void setIdTcaEstadoTcc(Integer value) {
        setAttributeInternal(IDTCAESTADOTCC, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdTcaSubEstadoTcc.
     * @return the IdTcaSubEstadoTcc
     */
    public Integer getIdTcaSubEstadoTcc() {
        return (Integer) getAttributeInternal(IDTCASUBESTADOTCC);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdTcaSubEstadoTcc.
     * @param value value to set the  IdTcaSubEstadoTcc
     */
    public void setIdTcaSubEstadoTcc(Integer value) {
        setAttributeInternal(IDTCASUBESTADOTCC, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DescripcionEstado.
     * @return the DescripcionEstado
     */
    public String getDescripcionEstado() {
        return (String) getAttributeInternal(DESCRIPCIONESTADO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DescripcionEstado.
     * @param value value to set the  DescripcionEstado
     */
    public void setDescripcionEstado(String value) {
        setAttributeInternal(DESCRIPCIONESTADO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DescripcionSubEstado.
     * @return the DescripcionSubEstado
     */
    public String getDescripcionSubEstado() {
        return (String) getAttributeInternal(DESCRIPCIONSUBESTADO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DescripcionSubEstado.
     * @param value value to set the  DescripcionSubEstado
     */
    public void setDescripcionSubEstado(String value) {
        setAttributeInternal(DESCRIPCIONSUBESTADO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Nombre.
     * @return the Nombre
     */
    public String getNombre() {
        return (String) getAttributeInternal(NOMBRE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Nombre.
     * @param value value to set the  Nombre
     */
    public void setNombre(String value) {
        setAttributeInternal(NOMBRE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Cantidad.
     * @return the Cantidad
     */
    public BigDecimal getCantidad() {
        return (BigDecimal) getAttributeInternal(CANTIDAD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Cantidad.
     * @param value value to set the  Cantidad
     */
    public void setCantidad(BigDecimal value) {
        setAttributeInternal(CANTIDAD, value);
    }
}

