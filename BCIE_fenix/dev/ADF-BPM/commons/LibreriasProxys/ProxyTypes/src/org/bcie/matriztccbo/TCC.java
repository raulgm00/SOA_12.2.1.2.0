
package org.bcie.matriztccbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TCC complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TCC">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tipo" type="{http://www.bcie.org/MatrizTCCBO}tipo"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subEstado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="banEstatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCC", propOrder = {
    "id",
    "tipo",
    "estado",
    "subEstado",
    "banEstatus"
})
public class TCC {

    protected long id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Tipo tipo;
    protected int estado;
    protected int subEstado;
    protected int banEstatus;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     */
    public void setId(long value) {
        this.id = value;
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
     * Obtiene el valor de la propiedad estado.
     * 
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     */
    public void setEstado(int value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad subEstado.
     * 
     */
    public int getSubEstado() {
        return subEstado;
    }

    /**
     * Define el valor de la propiedad subEstado.
     * 
     */
    public void setSubEstado(int value) {
        this.subEstado = value;
    }

    /**
     * Obtiene el valor de la propiedad banEstatus.
     * 
     */
    public int getBanEstatus() {
        return banEstatus;
    }

    /**
     * Define el valor de la propiedad banEstatus.
     * 
     */
    public void setBanEstatus(int value) {
        this.banEstatus = value;
    }

}
