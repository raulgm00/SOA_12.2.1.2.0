
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.operacionbo.ListaAccionesType;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConsultarAccionesResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarAccionesResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ListaAcciones" type="{http://www.bcie.org/OperacionBO}ListaAccionesType" minOccurs="0"/>
 *         &lt;element name="Resultado" type="{http://www.bcie.org/ResultBO}Resultado"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarAccionesResponseType", propOrder = {
    "listaAcciones",
    "resultado"
})
public class ConsultarAccionesResponseType {

    @XmlElement(name = "ListaAcciones")
    protected ListaAccionesType listaAcciones;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad listaAcciones.
     * 
     * @return
     *     possible object is
     *     {@link ListaAccionesType }
     *     
     */
    public ListaAccionesType getListaAcciones() {
        return listaAcciones;
    }

    /**
     * Define el valor de la propiedad listaAcciones.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaAccionesType }
     *     
     */
    public void setListaAcciones(ListaAccionesType value) {
        this.listaAcciones = value;
    }

    /**
     * Obtiene el valor de la propiedad resultado.
     * 
     * @return
     *     possible object is
     *     {@link Resultado }
     *     
     */
    public Resultado getResultado() {
        return resultado;
    }

    /**
     * Define el valor de la propiedad resultado.
     * 
     * @param value
     *     allowed object is
     *     {@link Resultado }
     *     
     */
    public void setResultado(Resultado value) {
        this.resultado = value;
    }

}
