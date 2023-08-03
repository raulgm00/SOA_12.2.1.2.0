package org.bcie.fenix.common.model.vo.common;

import org.eclipse.persistence.sdo.SDODataObject;

@SuppressWarnings("serial")
public class LineaCreditoActivaVOSDOImpl extends SDODataObject implements LineaCreditoActivaVOSDO {

   public static final int START_PROPERTY_INDEX = 0;

   public static final int END_PROPERTY_INDEX = START_PROPERTY_INDEX + 31;

   public LineaCreditoActivaVOSDOImpl() {}

   public java.lang.Long getId() {
      return getLong(START_PROPERTY_INDEX + 0);
   }

   public void setId(java.lang.Long value) {
      set(START_PROPERTY_INDEX + 0 , value);
   }

   public java.lang.Long getIdContrato() {
      return getLong(START_PROPERTY_INDEX + 1);
   }

   public void setIdContrato(java.lang.Long value) {
      set(START_PROPERTY_INDEX + 1 , value);
   }

   public java.lang.String getNumeroLineaCredito() {
      return getString(START_PROPERTY_INDEX + 2);
   }

   public void setNumeroLineaCredito(java.lang.String value) {
      set(START_PROPERTY_INDEX + 2 , value);
   }

   public java.lang.String getDescripcionLinea() {
      return getString(START_PROPERTY_INDEX + 3);
   }

   public void setDescripcionLinea(java.lang.String value) {
      set(START_PROPERTY_INDEX + 3 , value);
   }

   public java.lang.String getIdFlexcube() {
      return getString(START_PROPERTY_INDEX + 4);
   }

   public void setIdFlexcube(java.lang.String value) {
      set(START_PROPERTY_INDEX + 4 , value);
   }

   public java.lang.String getFondo() {
      return getString(START_PROPERTY_INDEX + 5);
   }

   public void setFondo(java.lang.String value) {
      set(START_PROPERTY_INDEX + 5 , value);
   }

   public java.math.BigDecimal getMontoLinea() {
      return getBigDecimal(START_PROPERTY_INDEX + 6);
   }

   public void setMontoLinea(java.math.BigDecimal value) {
      set(START_PROPERTY_INDEX + 6 , value);
   }

   public java.lang.Integer getEsRevolvente() {
      return getInt(START_PROPERTY_INDEX + 7);
   }

   public void setEsRevolvente(java.lang.Integer value) {
      set(START_PROPERTY_INDEX + 7 , value);
   }

   public java.lang.String getTratamientoDiasFeriados() {
      return getString(START_PROPERTY_INDEX + 8);
   }

   public void setTratamientoDiasFeriados(java.lang.String value) {
      set(START_PROPERTY_INDEX + 8 , value);
   }

   public java.lang.String getIdProductoFlexcube() {
      return getString(START_PROPERTY_INDEX + 9);
   }

   public void setIdProductoFlexcube(java.lang.String value) {
      set(START_PROPERTY_INDEX + 9 , value);
   }

   public java.sql.Timestamp getFechaValor() {
      return (java.sql.Timestamp)get(START_PROPERTY_INDEX + 10);
   }

   public void setFechaValor(java.sql.Timestamp value) {
      set(START_PROPERTY_INDEX + 10 , value);
   }

   public java.sql.Timestamp getFechaVencimiento() {
      return (java.sql.Timestamp)get(START_PROPERTY_INDEX + 11);
   }

   public void setFechaVencimiento(java.sql.Timestamp value) {
      set(START_PROPERTY_INDEX + 11 , value);
   }

   public java.lang.String getCodigoPlantillaLimite() {
      return getString(START_PROPERTY_INDEX + 12);
   }

   public void setCodigoPlantillaLimite(java.lang.String value) {
      set(START_PROPERTY_INDEX + 12 , value);
   }

   public java.lang.Integer getCodigoSerialLimite() {
      return getInt(START_PROPERTY_INDEX + 13);
   }

   public void setCodigoSerialLimite(java.lang.Integer value) {
      set(START_PROPERTY_INDEX + 13 , value);
   }

   public java.lang.Long getCodigoAsignacion() {
      return getLong(START_PROPERTY_INDEX + 14);
   }

   public void setCodigoAsignacion(java.lang.Long value) {
      set(START_PROPERTY_INDEX + 14 , value);
   }

   public java.lang.String getDescripcionAsignacion() {
      return getString(START_PROPERTY_INDEX + 15);
   }

   public void setDescripcionAsignacion(java.lang.String value) {
      set(START_PROPERTY_INDEX + 15 , value);
   }

   public java.lang.String getIdFlexcubePasivo() {
      return getString(START_PROPERTY_INDEX + 16);
   }

   public void setIdFlexcubePasivo(java.lang.String value) {
      set(START_PROPERTY_INDEX + 16 , value);
   }

   public java.lang.Integer getCondicionEspecial() {
      return getInt(START_PROPERTY_INDEX + 17);
   }

   public void setCondicionEspecial(java.lang.Integer value) {
      set(START_PROPERTY_INDEX + 17 , value);
   }

   public java.lang.String getDescripcionCondEspecial() {
      return getString(START_PROPERTY_INDEX + 18);
   }

   public void setDescripcionCondEspecial(java.lang.String value) {
      set(START_PROPERTY_INDEX + 18 , value);
   }

   public java.sql.Timestamp getFechaRegistro() {
      return (java.sql.Timestamp)get(START_PROPERTY_INDEX + 19);
   }

   public void setFechaRegistro(java.sql.Timestamp value) {
      set(START_PROPERTY_INDEX + 19 , value);
   }

   public java.lang.Integer getBanEstatus() {
      return getInt(START_PROPERTY_INDEX + 20);
   }

   public void setBanEstatus(java.lang.Integer value) {
      set(START_PROPERTY_INDEX + 20 , value);
   }

   public java.math.BigDecimal getMontoAmpliacionLinea() {
      return getBigDecimal(START_PROPERTY_INDEX + 21);
   }

   public void setMontoAmpliacionLinea(java.math.BigDecimal value) {
      set(START_PROPERTY_INDEX + 21 , value);
   }

   public java.lang.Integer getDiasSpotTasa() {
      return getInt(START_PROPERTY_INDEX + 22);
   }

   public void setDiasSpotTasa(java.lang.Integer value) {
      set(START_PROPERTY_INDEX + 22 , value);
   }

   public java.lang.Long getIdTcaTipoRedondeo() {
      return getLong(START_PROPERTY_INDEX + 23);
   }

   public void setIdTcaTipoRedondeo(java.lang.Long value) {
      set(START_PROPERTY_INDEX + 23 , value);
   }

   public java.lang.Integer getCantidadDecimales() {
      return getInt(START_PROPERTY_INDEX + 24);
   }

   public void setCantidadDecimales(java.lang.Integer value) {
      set(START_PROPERTY_INDEX + 24 , value);
   }

   public java.lang.Long getIdTcaTipoMora() {
      return getLong(START_PROPERTY_INDEX + 25);
   }

   public void setIdTcaTipoMora(java.lang.Long value) {
      set(START_PROPERTY_INDEX + 25 , value);
   }

   public java.lang.String getCodigoTasaReferencia() {
      return getString(START_PROPERTY_INDEX + 26);
   }

   public void setCodigoTasaReferencia(java.lang.String value) {
      set(START_PROPERTY_INDEX + 26 , value);
   }

   public java.math.BigDecimal getValorTasa() {
      return getBigDecimal(START_PROPERTY_INDEX + 27);
   }

   public void setValorTasa(java.math.BigDecimal value) {
      set(START_PROPERTY_INDEX + 27 , value);
   }

   public java.math.BigDecimal getSpreadTasa() {
      return getBigDecimal(START_PROPERTY_INDEX + 28);
   }

   public void setSpreadTasa(java.math.BigDecimal value) {
      set(START_PROPERTY_INDEX + 28 , value);
   }

   public java.lang.Integer getFrecuencia() {
      return getInt(START_PROPERTY_INDEX + 29);
   }

   public void setFrecuencia(java.lang.Integer value) {
      set(START_PROPERTY_INDEX + 29 , value);
   }

   public java.lang.Integer getIdTcaTipoFrecuencia() {
      return getInt(START_PROPERTY_INDEX + 30);
   }

   public void setIdTcaTipoFrecuencia(java.lang.Integer value) {
      set(START_PROPERTY_INDEX + 30 , value);
   }

   public java.lang.Integer getSePuedeMoverEntreMeses() {
      return getInt(START_PROPERTY_INDEX + 31);
   }

   public void setSePuedeMoverEntreMeses(java.lang.Integer value) {
      set(START_PROPERTY_INDEX + 31 , value);
   }


}

