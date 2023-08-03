
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConsultarReactivarOperacionResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarReactivarOperacionResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ListaProcesosBitacora" type="{http://www.bcie.org/OperacionMO}ProcesosOperacionBitacora"/>
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
@XmlType(name = "ConsultarReactivarOperacionResponseType", propOrder = {
    "listaProcesosBitacora",
    "resultado"
})
public class ConsultarReactivarOperacionResponseType {

    @XmlElement(name = "ListaProcesosBitacora", required = true)
    protected ProcesosOperacionBitacora listaProcesosBitacora;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad listaProcesosBitacora.
     * 
     * @return
     *     possible object is
     *     {@link ProcesosOperacionBitacora }
     *     
     */
    public ProcesosOperacionBitacora getListaProcesosBitacora() {
        return listaProcesosBitacora;
    }

    /**
     * Define el valor de la propiedad listaProcesosBitacora.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcesosOperacionBitacora }
     *     
     */
    public void setListaProcesosBitacora(ProcesosOperacionBitacora value) {
        this.listaProcesosBitacora = value;
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
