
package org.bcie.adquisicionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para InformeNoObjecionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="InformeNoObjecionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requierePublicacion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idAdquisicion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idNoObjecion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InformeNoObjecionRequestType", propOrder = {
    "requierePublicacion",
    "idAdquisicion",
    "idNoObjecion"
})
public class InformeNoObjecionRequestType {

    protected int requierePublicacion;
    protected long idAdquisicion;
    protected long idNoObjecion;

    /**
     * Obtiene el valor de la propiedad requierePublicacion.
     * 
     */
    public int getRequierePublicacion() {
        return requierePublicacion;
    }

    /**
     * Define el valor de la propiedad requierePublicacion.
     * 
     */
    public void setRequierePublicacion(int value) {
        this.requierePublicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idAdquisicion.
     * 
     */
    public long getIdAdquisicion() {
        return idAdquisicion;
    }

    /**
     * Define el valor de la propiedad idAdquisicion.
     * 
     */
    public void setIdAdquisicion(long value) {
        this.idAdquisicion = value;
    }

    /**
     * Obtiene el valor de la propiedad idNoObjecion.
     * 
     */
    public long getIdNoObjecion() {
        return idNoObjecion;
    }

    /**
     * Define el valor de la propiedad idNoObjecion.
     * 
     */
    public void setIdNoObjecion(long value) {
        this.idNoObjecion = value;
    }

}
