
package org.bcie.documentobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Tipo de documento, calificativo para la entidad documento
 * 
 * <p>Clase Java para TipoDocumento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TipoDocumento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTipoDocumento" type="{http://www.bcie.org/DocumentoBO}idTipoDocumento"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idGrupoExterno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idTipoExterno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoExterno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumento", propOrder = {
    "content"
})
public class TipoDocumento {

    @XmlElementRefs({
        @XmlElementRef(name = "idTipoExterno", namespace = "http://www.bcie.org/DocumentoBO", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "estado", namespace = "http://www.bcie.org/DocumentoBO", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "codigoExterno", namespace = "http://www.bcie.org/DocumentoBO", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "idGrupoExterno", namespace = "http://www.bcie.org/DocumentoBO", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "fechaRegistro", namespace = "http://www.bcie.org/DocumentoBO", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "descripcion", namespace = "http://www.bcie.org/DocumentoBO", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "idTipoDocumento", namespace = "http://www.bcie.org/DocumentoBO", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;

    /**
     * Obtiene el resto del modelo de contenido. 
     * 
     * <p>
     * Ha obtenido esta propiedad que permite capturar todo por el siguiente motivo: 
     * El nombre de campo "Descripcion" se está utilizando en dos partes diferentes de un esquema. Consulte: 
     * línea 128 de http://osblnx1-vip.sndevmw.bcievcn01.oraclevcn.com:7021/Entidad/OperacionService/V1.0?SCHEMA%2FMDS%2FResources%2FComponentesComunes%2FDominioDocumento%2FDocumento%2FV1%2FSchema%2FDocumentoBO
     * línea 123 de http://osblnx1-vip.sndevmw.bcievcn01.oraclevcn.com:7021/Entidad/OperacionService/V1.0?SCHEMA%2FMDS%2FResources%2FComponentesComunes%2FDominioDocumento%2FDocumento%2FV1%2FSchema%2FDocumentoBO
     * <p>
     * Para deshacerse de esta propiedad, aplique una personalización de propiedad a una
     * de las dos declaraciones siguientes para cambiarles de nombre: 
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Long }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getContent() {
        if (content == null) {
            content = new ArrayList<JAXBElement<?>>();
        }
        return this.content;
    }

}
