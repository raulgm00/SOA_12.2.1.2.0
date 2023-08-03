
package org.bcie.reglatareabo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;
import org.bcie.reglabo.ReglaBasicType;


/**
 * <p>Clase Java para ReglaEvaluacionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ReglaEvaluacionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/ReglaBO}ReglaBasicType">
 *       &lt;sequence>
 *         &lt;element name="EsAdvertencia" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="EsError" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Mensaje" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReglaEvaluacionType", propOrder = {
    "esAdvertencia",
    "esError",
    "mensaje"
})
public class ReglaEvaluacionType
    extends ReglaBasicType
{

    @XmlElement(name = "EsAdvertencia")
    protected boolean esAdvertencia;
    @XmlElement(name = "EsError")
    protected boolean esError;
    @XmlElement(name = "Mensaje", required = true)
    protected Catalogo mensaje;

    /**
     * Obtiene el valor de la propiedad esAdvertencia.
     * 
     */
    public boolean isEsAdvertencia() {
        return esAdvertencia;
    }

    /**
     * Define el valor de la propiedad esAdvertencia.
     * 
     */
    public void setEsAdvertencia(boolean value) {
        this.esAdvertencia = value;
    }

    /**
     * Obtiene el valor de la propiedad esError.
     * 
     */
    public boolean isEsError() {
        return esError;
    }

    /**
     * Define el valor de la propiedad esError.
     * 
     */
    public void setEsError(boolean value) {
        this.esError = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setMensaje(Catalogo value) {
        this.mensaje = value;
    }

}
