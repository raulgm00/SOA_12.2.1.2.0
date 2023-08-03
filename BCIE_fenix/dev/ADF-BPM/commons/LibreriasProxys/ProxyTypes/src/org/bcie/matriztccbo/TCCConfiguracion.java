
package org.bcie.matriztccbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TCCConfiguracion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TCCConfiguracion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTCCConfig" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tipo" type="{http://www.bcie.org/MatrizTCCBO}tipo"/>
 *         &lt;element name="idPrecarga" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idTcaTipo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idTca" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="esMandatorio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCCConfiguracion", propOrder = {
    "idTCCConfig",
    "tipo",
    "idPrecarga",
    "nombre",
    "idTcaTipo",
    "idTca",
    "esMandatorio"
})
public class TCCConfiguracion {

    protected long idTCCConfig;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Tipo tipo;
    protected long idPrecarga;
    @XmlElement(required = true)
    protected String nombre;
    protected long idTcaTipo;
    protected long idTca;
    protected int esMandatorio;

    /**
     * Obtiene el valor de la propiedad idTCCConfig.
     * 
     */
    public long getIdTCCConfig() {
        return idTCCConfig;
    }

    /**
     * Define el valor de la propiedad idTCCConfig.
     * 
     */
    public void setIdTCCConfig(long value) {
        this.idTCCConfig = value;
    }

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link Tipo }
     *     
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link Tipo }
     *     
     */
    public void setTipo(Tipo value) {
        this.tipo = value;
    }

    /**
     * Obtiene el valor de la propiedad idPrecarga.
     * 
     */
    public long getIdPrecarga() {
        return idPrecarga;
    }

    /**
     * Define el valor de la propiedad idPrecarga.
     * 
     */
    public void setIdPrecarga(long value) {
        this.idPrecarga = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad idTcaTipo.
     * 
     */
    public long getIdTcaTipo() {
        return idTcaTipo;
    }

    /**
     * Define el valor de la propiedad idTcaTipo.
     * 
     */
    public void setIdTcaTipo(long value) {
        this.idTcaTipo = value;
    }

    /**
     * Obtiene el valor de la propiedad idTca.
     * 
     */
    public long getIdTca() {
        return idTca;
    }

    /**
     * Define el valor de la propiedad idTca.
     * 
     */
    public void setIdTca(long value) {
        this.idTca = value;
    }

    /**
     * Obtiene el valor de la propiedad esMandatorio.
     * 
     */
    public int getEsMandatorio() {
        return esMandatorio;
    }

    /**
     * Define el valor de la propiedad esMandatorio.
     * 
     */
    public void setEsMandatorio(int value) {
        this.esMandatorio = value;
    }

}
