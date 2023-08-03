package org.bcie.fenix.view.gestoroperaciones;

import java.io.Serializable;

public class ComponenteBean implements Serializable{
    @SuppressWarnings("compatibility:48379689483456439")
    private static final long serialVersionUID = 1L;
    String nombre;
    String descripcion;
    String porcentaje;
    String actividadEconomica;
    String iniciativaEstrategica;
    String cantidadPaises;
    String areaFocalizacion;
    String ejeEstrategico;
    String actividadEconomicaPrimaria;
    String codigoExterno;
    String codigoExterno1;
    String sectorIbcie;
    String subSectorIbcie;

    public void setCodigoExterno1(String codigoExterno1) {
        this.codigoExterno1 = codigoExterno1;
    }

    public String getCodigoExterno1() {
        return codigoExterno1;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setActividadEconomica(String actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }

    public String getActividadEconomica() {
        return actividadEconomica;
    }

    public void setIniciativaEstrategica(String iniciativaEstrategica) {
        this.iniciativaEstrategica = iniciativaEstrategica;
    }

    public String getIniciativaEstrategica() {
        return iniciativaEstrategica;
    }

    public void setCantidadPaises(String cantidadPaises) {
        this.cantidadPaises = cantidadPaises;
    }

    public String getCantidadPaises() {
        return cantidadPaises;
    }

    public void setAreaFocalizacion(String areaFocalizacion) {
        this.areaFocalizacion = areaFocalizacion;
    }

    public String getAreaFocalizacion() {
        return areaFocalizacion;
    }

    public void setEjeEstrategico(String ejeEstrategico) {
        this.ejeEstrategico = ejeEstrategico;
    }

    public String getEjeEstrategico() {
        return ejeEstrategico;
    }

    public void setActividadEconomicaPrimaria(String actividadEconomicaPrimaria) {
        this.actividadEconomicaPrimaria = actividadEconomicaPrimaria;
    }

    public String getActividadEconomicaPrimaria() {
        return actividadEconomicaPrimaria;
    }

    public void setCodigoExterno(String codigoExterno) {
        this.codigoExterno = codigoExterno;
    }

    public String getCodigoExterno() {
        return codigoExterno;
    }
    
    public void setSectorIbcie(String sectorIbcie) {
        this.sectorIbcie = sectorIbcie;
    }

    public String getSectorIbcie() {
        return sectorIbcie;
    }

    public void setSubSectorIbcie(String subSectorIbcie) {
        this.subSectorIbcie = subSectorIbcie;
    }

    public String getSubSectorIbcie() {
        return subSectorIbcie;
    }
}
