
package org.bcie.operacionbo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para ComponenteClasificacionEstrategicaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ComponenteClasificacionEstrategicaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombreComponente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="porcentaje" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="actividadEconomica" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="actividadEconomicaAsociada" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="areaFocalizacion" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="ejeEstrategico" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="iniciativaEstrategica" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="cantidadPaises" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="sectorIbcie" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="subSectorIbcie" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComponenteClasificacionEstrategicaType", propOrder = {
    "nombreComponente",
    "descripcion",
    "porcentaje",
    "actividadEconomica",
    "actividadEconomicaAsociada",
    "areaFocalizacion",
    "ejeEstrategico",
    "iniciativaEstrategica",
    "cantidadPaises",
    "sectorIbcie",
    "subSectorIbcie"
})
public class ComponenteClasificacionEstrategicaType {

    protected String nombreComponente;
    protected String descripcion;
    protected BigDecimal porcentaje;
    protected Catalogo actividadEconomica;
    protected Catalogo actividadEconomicaAsociada;
    protected Catalogo areaFocalizacion;
    protected Catalogo ejeEstrategico;
    protected Catalogo iniciativaEstrategica;
    protected Catalogo cantidadPaises;
    protected Catalogo sectorIbcie;
    protected Catalogo subSectorIbcie;

    /**
     * Obtiene el valor de la propiedad nombreComponente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreComponente() {
        return nombreComponente;
    }

    /**
     * Define el valor de la propiedad nombreComponente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreComponente(String value) {
        this.nombreComponente = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad porcentaje.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    /**
     * Define el valor de la propiedad porcentaje.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPorcentaje(BigDecimal value) {
        this.porcentaje = value;
    }

    /**
     * Obtiene el valor de la propiedad actividadEconomica.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getActividadEconomica() {
        return actividadEconomica;
    }

    /**
     * Define el valor de la propiedad actividadEconomica.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setActividadEconomica(Catalogo value) {
        this.actividadEconomica = value;
    }

    /**
     * Obtiene el valor de la propiedad actividadEconomicaAsociada.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getActividadEconomicaAsociada() {
        return actividadEconomicaAsociada;
    }

    /**
     * Define el valor de la propiedad actividadEconomicaAsociada.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setActividadEconomicaAsociada(Catalogo value) {
        this.actividadEconomicaAsociada = value;
    }

    /**
     * Obtiene el valor de la propiedad areaFocalizacion.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getAreaFocalizacion() {
        return areaFocalizacion;
    }

    /**
     * Define el valor de la propiedad areaFocalizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setAreaFocalizacion(Catalogo value) {
        this.areaFocalizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad ejeEstrategico.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getEjeEstrategico() {
        return ejeEstrategico;
    }

    /**
     * Define el valor de la propiedad ejeEstrategico.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setEjeEstrategico(Catalogo value) {
        this.ejeEstrategico = value;
    }

    /**
     * Obtiene el valor de la propiedad iniciativaEstrategica.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getIniciativaEstrategica() {
        return iniciativaEstrategica;
    }

    /**
     * Define el valor de la propiedad iniciativaEstrategica.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setIniciativaEstrategica(Catalogo value) {
        this.iniciativaEstrategica = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadPaises.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getCantidadPaises() {
        return cantidadPaises;
    }

    /**
     * Define el valor de la propiedad cantidadPaises.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setCantidadPaises(Catalogo value) {
        this.cantidadPaises = value;
    }

    /**
     * Obtiene el valor de la propiedad sectorIbcie.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getSectorIbcie() {
        return sectorIbcie;
    }

    /**
     * Define el valor de la propiedad sectorIbcie.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setSectorIbcie(Catalogo value) {
        this.sectorIbcie = value;
    }

    /**
     * Obtiene el valor de la propiedad subSectorIbcie.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getSubSectorIbcie() {
        return subSectorIbcie;
    }

    /**
     * Define el valor de la propiedad subSectorIbcie.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setSubSectorIbcie(Catalogo value) {
        this.subSectorIbcie = value;
    }

}
