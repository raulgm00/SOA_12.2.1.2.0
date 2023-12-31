package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Oct 29 16:49:30 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConfigVisibilidadVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum
    {
        Id,
        IdProducto,
        DatosGenerales,
        MontoTotal,
        ActividadEconomica,
        IniciativaEstrategica,
        ObjetivoEstrategico,
        AreaFocalizacion,
        EjeEstrategico,
        TipoGarantia,
        ComponenteConcesionalidad,
        Estructurador,
        Beneficiario,
        Programa,
        OperacionesAsociadas,
        RespondeCuestionario,
        AnalisisLaft,
        SectorMercado;
        static AttributesEnum[] vals = null;
        ;
        private static final int firstIndex = 0;

        public int index()
        {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex()
        {
            return firstIndex;
        }

        public static int count()
        {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues()
        {
            if (vals == null)
            {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int ID = AttributesEnum.Id.index();
    public static final int IDPRODUCTO = AttributesEnum.IdProducto.index();
    public static final int DATOSGENERALES = AttributesEnum.DatosGenerales.index();
    public static final int MONTOTOTAL = AttributesEnum.MontoTotal.index();
    public static final int ACTIVIDADECONOMICA = AttributesEnum.ActividadEconomica.index();
    public static final int INICIATIVAESTRATEGICA = AttributesEnum.IniciativaEstrategica.index();
    public static final int OBJETIVOESTRATEGICO = AttributesEnum.ObjetivoEstrategico.index();
    public static final int AREAFOCALIZACION = AttributesEnum.AreaFocalizacion.index();
    public static final int EJEESTRATEGICO = AttributesEnum.EjeEstrategico.index();
    public static final int TIPOGARANTIA = AttributesEnum.TipoGarantia.index();
    public static final int COMPONENTECONCESIONALIDAD = AttributesEnum.ComponenteConcesionalidad.index();
    public static final int ESTRUCTURADOR = AttributesEnum.Estructurador.index();
    public static final int BENEFICIARIO = AttributesEnum.Beneficiario.index();
    public static final int PROGRAMA = AttributesEnum.Programa.index();
    public static final int OPERACIONESASOCIADAS = AttributesEnum.OperacionesAsociadas.index();
    public static final int RESPONDECUESTIONARIO = AttributesEnum.RespondeCuestionario.index();
    public static final int ANALISISLAFT = AttributesEnum.AnalisisLaft.index();
    public static final int SECTORMERCADO = AttributesEnum.SectorMercado.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ConfigVisibilidadVORowImpl()
    {
    }


    /**
     * Gets the attribute value for the calculated attribute Id.
     * @return the Id
     */
    public Integer getId()
    {
        return (Integer) getAttributeInternal(ID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Id.
     * @param value value to set the  Id
     */
    public void setId(Integer value)
    {
        setAttributeInternal(ID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdProducto.
     * @return the IdProducto
     */
    public Integer getIdProducto()
    {
        return (Integer) getAttributeInternal(IDPRODUCTO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdProducto.
     * @param value value to set the  IdProducto
     */
    public void setIdProducto(Integer value)
    {
        setAttributeInternal(IDPRODUCTO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DatosGenerales.
     * @return the DatosGenerales
     */
    public String getDatosGenerales()
    {
        return (String) getAttributeInternal(DATOSGENERALES);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DatosGenerales.
     * @param value value to set the  DatosGenerales
     */
    public void setDatosGenerales(String value)
    {
        setAttributeInternal(DATOSGENERALES, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MontoTotal.
     * @return the MontoTotal
     */
    public String getMontoTotal()
    {
        return (String) getAttributeInternal(MONTOTOTAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MontoTotal.
     * @param value value to set the  MontoTotal
     */
    public void setMontoTotal(String value)
    {
        setAttributeInternal(MONTOTOTAL, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ActividadEconomica.
     * @return the ActividadEconomica
     */
    public String getActividadEconomica()
    {
        return (String) getAttributeInternal(ACTIVIDADECONOMICA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ActividadEconomica.
     * @param value value to set the  ActividadEconomica
     */
    public void setActividadEconomica(String value)
    {
        setAttributeInternal(ACTIVIDADECONOMICA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IniciativaEstrategica.
     * @return the IniciativaEstrategica
     */
    public String getIniciativaEstrategica()
    {
        return (String) getAttributeInternal(INICIATIVAESTRATEGICA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IniciativaEstrategica.
     * @param value value to set the  IniciativaEstrategica
     */
    public void setIniciativaEstrategica(String value)
    {
        setAttributeInternal(INICIATIVAESTRATEGICA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ObjetivoEstrategico.
     * @return the ObjetivoEstrategico
     */
    public String getObjetivoEstrategico()
    {
        return (String) getAttributeInternal(OBJETIVOESTRATEGICO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ObjetivoEstrategico.
     * @param value value to set the  ObjetivoEstrategico
     */
    public void setObjetivoEstrategico(String value)
    {
        setAttributeInternal(OBJETIVOESTRATEGICO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute AreaFocalizacion.
     * @return the AreaFocalizacion
     */
    public String getAreaFocalizacion()
    {
        return (String) getAttributeInternal(AREAFOCALIZACION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute AreaFocalizacion.
     * @param value value to set the  AreaFocalizacion
     */
    public void setAreaFocalizacion(String value)
    {
        setAttributeInternal(AREAFOCALIZACION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute EjeEstrategico.
     * @return the EjeEstrategico
     */
    public String getEjeEstrategico()
    {
        return (String) getAttributeInternal(EJEESTRATEGICO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute EjeEstrategico.
     * @param value value to set the  EjeEstrategico
     */
    public void setEjeEstrategico(String value)
    {
        setAttributeInternal(EJEESTRATEGICO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TipoGarantia.
     * @return the TipoGarantia
     */
    public String getTipoGarantia()
    {
        return (String) getAttributeInternal(TIPOGARANTIA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TipoGarantia.
     * @param value value to set the  TipoGarantia
     */
    public void setTipoGarantia(String value)
    {
        setAttributeInternal(TIPOGARANTIA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ComponenteConcesionalidad.
     * @return the ComponenteConcesionalidad
     */
    public String getComponenteConcesionalidad()
    {
        return (String) getAttributeInternal(COMPONENTECONCESIONALIDAD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ComponenteConcesionalidad.
     * @param value value to set the  ComponenteConcesionalidad
     */
    public void setComponenteConcesionalidad(String value)
    {
        setAttributeInternal(COMPONENTECONCESIONALIDAD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Estructurador.
     * @return the Estructurador
     */
    public String getEstructurador()
    {
        return (String) getAttributeInternal(ESTRUCTURADOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Estructurador.
     * @param value value to set the  Estructurador
     */
    public void setEstructurador(String value)
    {
        setAttributeInternal(ESTRUCTURADOR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Beneficiario.
     * @return the Beneficiario
     */
    public String getBeneficiario()
    {
        return (String) getAttributeInternal(BENEFICIARIO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Beneficiario.
     * @param value value to set the  Beneficiario
     */
    public void setBeneficiario(String value)
    {
        setAttributeInternal(BENEFICIARIO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Programa.
     * @return the Programa
     */
    public String getPrograma()
    {
        return (String) getAttributeInternal(PROGRAMA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Programa.
     * @param value value to set the  Programa
     */
    public void setPrograma(String value)
    {
        setAttributeInternal(PROGRAMA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute OperacionesAsociadas.
     * @return the OperacionesAsociadas
     */
    public String getOperacionesAsociadas()
    {
        return (String) getAttributeInternal(OPERACIONESASOCIADAS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute OperacionesAsociadas.
     * @param value value to set the  OperacionesAsociadas
     */
    public void setOperacionesAsociadas(String value)
    {
        setAttributeInternal(OPERACIONESASOCIADAS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute RespondeCuestionario.
     * @return the RespondeCuestionario
     */
    public String getRespondeCuestionario()
    {
        return (String) getAttributeInternal(RESPONDECUESTIONARIO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute RespondeCuestionario.
     * @param value value to set the  RespondeCuestionario
     */
    public void setRespondeCuestionario(String value)
    {
        setAttributeInternal(RESPONDECUESTIONARIO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute AnalisisLaft.
     * @return the AnalisisLaft
     */
    public String getAnalisisLaft()
    {
        return (String) getAttributeInternal(ANALISISLAFT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute AnalisisLaft.
     * @param value value to set the  AnalisisLaft
     */
    public void setAnalisisLaft(String value)
    {
        setAttributeInternal(ANALISISLAFT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SectorMercado.
     * @return the SectorMercado
     */
    public String getSectorMercado()
    {
        return (String) getAttributeInternal(SECTORMERCADO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SectorMercado.
     * @param value value to set the  SectorMercado
     */
    public void setSectorMercado(String value)
    {
        setAttributeInternal(SECTORMERCADO, value);
    }
}

