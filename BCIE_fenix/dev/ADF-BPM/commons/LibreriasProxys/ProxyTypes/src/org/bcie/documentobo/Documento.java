
package org.bcie.documentobo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Entidad Documento - (Modelo canónico)
 *           Se usa para el control, administración y comunicación
 *           con gestores documentales
 * 
 * <p>Clase Java para Documento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Documento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDocumento" type="{http://www.bcie.org/DocumentoBO}idDocumento" minOccurs="0"/>
 *         &lt;element name="idTipoDocumento" type="{http://www.bcie.org/DocumentoBO}idTipoDocumento" minOccurs="0"/>
 *         &lt;element name="idTipoDocumentoExterno" type="{http://www.bcie.org/DocumentoBO}idTipoDocumentoExterno" minOccurs="0"/>
 *         &lt;element name="nombreTipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="etapa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idExterno" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idDocAreaTipo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion" minOccurs="0"/>
 *         &lt;element name="idCliente" type="{http://www.bcie.org/ClienteBO}idCliente" minOccurs="0"/>
 *         &lt;element name="idProducto" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="idPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mime_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tamanio" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idAdjunto" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="esJustificacion" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="comentario" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="250"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="fechaDocumento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="status" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="estatusTipoDoc" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="codExtTipoDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tarea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idtarea" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="PuedeModificar" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="PuedeBorrar" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="usuarioAgrega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuarioUltimaActualizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accionARealizar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idFlujoNegocio" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Documento", propOrder = {
    "idDocumento",
    "idTipoDocumento",
    "idTipoDocumentoExterno",
    "nombreTipoDocumento",
    "etapa",
    "codigoDocumento",
    "idExterno",
    "idDocAreaTipo",
    "idOperacion",
    "idCliente",
    "idProducto",
    "idPais",
    "nombre",
    "filename",
    "mimeType",
    "tamanio",
    "idAdjunto",
    "esJustificacion",
    "comentario",
    "fechaDocumento",
    "fechaRegistro",
    "status",
    "estatusTipoDoc",
    "codExtTipoDoc",
    "tarea",
    "idtarea",
    "content",
    "puedeModificar",
    "puedeBorrar",
    "usuarioAgrega",
    "usuarioUltimaActualizacion",
    "accionARealizar",
    "idFlujoNegocio",
    "instanciaProceso"
})
public class Documento {

    protected Long idDocumento;
    protected Long idTipoDocumento;
    protected Long idTipoDocumentoExterno;
    protected String nombreTipoDocumento;
    protected String etapa;
    protected String codigoDocumento;
    protected Long idExterno;
    protected Long idDocAreaTipo;
    protected Long idOperacion;
    protected Long idCliente;
    protected Object idProducto;
    protected String idPais;
    protected String nombre;
    protected String filename;
    @XmlElement(name = "mime_type")
    protected String mimeType;
    protected Long tamanio;
    protected Long idAdjunto;
    protected Boolean esJustificacion;
    protected String comentario;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaDocumento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRegistro;
    protected String status;
    protected BigDecimal estatusTipoDoc;
    protected String codExtTipoDoc;
    protected String tarea;
    protected Long idtarea;
    protected byte[] content;
    @XmlElement(name = "PuedeModificar")
    protected Boolean puedeModificar;
    @XmlElement(name = "PuedeBorrar")
    protected Boolean puedeBorrar;
    protected String usuarioAgrega;
    protected String usuarioUltimaActualizacion;
    protected String accionARealizar;
    protected Long idFlujoNegocio;
    @XmlElement(required = true)
    protected String instanciaProceso;

    /**
     * Obtiene el valor de la propiedad idDocumento.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdDocumento() {
        return idDocumento;
    }

    /**
     * Define el valor de la propiedad idDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdDocumento(Long value) {
        this.idDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad idTipoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdTipoDocumento() {
        return idTipoDocumento;
    }

    /**
     * Define el valor de la propiedad idTipoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdTipoDocumento(Long value) {
        this.idTipoDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad idTipoDocumentoExterno.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdTipoDocumentoExterno() {
        return idTipoDocumentoExterno;
    }

    /**
     * Define el valor de la propiedad idTipoDocumentoExterno.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdTipoDocumentoExterno(Long value) {
        this.idTipoDocumentoExterno = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreTipoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTipoDocumento() {
        return nombreTipoDocumento;
    }

    /**
     * Define el valor de la propiedad nombreTipoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTipoDocumento(String value) {
        this.nombreTipoDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad etapa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEtapa() {
        return etapa;
    }

    /**
     * Define el valor de la propiedad etapa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEtapa(String value) {
        this.etapa = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoDocumento() {
        return codigoDocumento;
    }

    /**
     * Define el valor de la propiedad codigoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoDocumento(String value) {
        this.codigoDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad idExterno.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdExterno() {
        return idExterno;
    }

    /**
     * Define el valor de la propiedad idExterno.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdExterno(Long value) {
        this.idExterno = value;
    }

    /**
     * Obtiene el valor de la propiedad idDocAreaTipo.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdDocAreaTipo() {
        return idDocAreaTipo;
    }

    /**
     * Define el valor de la propiedad idDocAreaTipo.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdDocAreaTipo(Long value) {
        this.idDocAreaTipo = value;
    }

    /**
     * Obtiene el valor de la propiedad idOperacion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdOperacion() {
        return idOperacion;
    }

    /**
     * Define el valor de la propiedad idOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdOperacion(Long value) {
        this.idOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idCliente.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * Define el valor de la propiedad idCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdCliente(Long value) {
        this.idCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad idProducto.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getIdProducto() {
        return idProducto;
    }

    /**
     * Define el valor de la propiedad idProducto.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setIdProducto(Object value) {
        this.idProducto = value;
    }

    /**
     * Obtiene el valor de la propiedad idPais.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPais() {
        return idPais;
    }

    /**
     * Define el valor de la propiedad idPais.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPais(String value) {
        this.idPais = value;
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
     * Obtiene el valor de la propiedad filename.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Define el valor de la propiedad filename.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilename(String value) {
        this.filename = value;
    }

    /**
     * Obtiene el valor de la propiedad mimeType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Define el valor de la propiedad mimeType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    /**
     * Obtiene el valor de la propiedad tamanio.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTamanio() {
        return tamanio;
    }

    /**
     * Define el valor de la propiedad tamanio.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTamanio(Long value) {
        this.tamanio = value;
    }

    /**
     * Obtiene el valor de la propiedad idAdjunto.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAdjunto() {
        return idAdjunto;
    }

    /**
     * Define el valor de la propiedad idAdjunto.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAdjunto(Long value) {
        this.idAdjunto = value;
    }

    /**
     * Obtiene el valor de la propiedad esJustificacion.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsJustificacion() {
        return esJustificacion;
    }

    /**
     * Define el valor de la propiedad esJustificacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsJustificacion(Boolean value) {
        this.esJustificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad comentario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Define el valor de la propiedad comentario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentario(String value) {
        this.comentario = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaDocumento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaDocumento() {
        return fechaDocumento;
    }

    /**
     * Define el valor de la propiedad fechaDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaDocumento(XMLGregorianCalendar value) {
        this.fechaDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaRegistro.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Define el valor de la propiedad fechaRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRegistro(XMLGregorianCalendar value) {
        this.fechaRegistro = value;
    }

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Obtiene el valor de la propiedad estatusTipoDoc.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEstatusTipoDoc() {
        return estatusTipoDoc;
    }

    /**
     * Define el valor de la propiedad estatusTipoDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEstatusTipoDoc(BigDecimal value) {
        this.estatusTipoDoc = value;
    }

    /**
     * Obtiene el valor de la propiedad codExtTipoDoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodExtTipoDoc() {
        return codExtTipoDoc;
    }

    /**
     * Define el valor de la propiedad codExtTipoDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodExtTipoDoc(String value) {
        this.codExtTipoDoc = value;
    }

    /**
     * Obtiene el valor de la propiedad tarea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarea() {
        return tarea;
    }

    /**
     * Define el valor de la propiedad tarea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarea(String value) {
        this.tarea = value;
    }

    /**
     * Obtiene el valor de la propiedad idtarea.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdtarea() {
        return idtarea;
    }

    /**
     * Define el valor de la propiedad idtarea.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdtarea(Long value) {
        this.idtarea = value;
    }

    /**
     * Obtiene el valor de la propiedad content.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * Define el valor de la propiedad content.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setContent(byte[] value) {
        this.content = value;
    }

    /**
     * Obtiene el valor de la propiedad puedeModificar.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPuedeModificar() {
        return puedeModificar;
    }

    /**
     * Define el valor de la propiedad puedeModificar.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPuedeModificar(Boolean value) {
        this.puedeModificar = value;
    }

    /**
     * Obtiene el valor de la propiedad puedeBorrar.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPuedeBorrar() {
        return puedeBorrar;
    }

    /**
     * Define el valor de la propiedad puedeBorrar.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPuedeBorrar(Boolean value) {
        this.puedeBorrar = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioAgrega.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioAgrega() {
        return usuarioAgrega;
    }

    /**
     * Define el valor de la propiedad usuarioAgrega.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioAgrega(String value) {
        this.usuarioAgrega = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioUltimaActualizacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioUltimaActualizacion() {
        return usuarioUltimaActualizacion;
    }

    /**
     * Define el valor de la propiedad usuarioUltimaActualizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioUltimaActualizacion(String value) {
        this.usuarioUltimaActualizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad accionARealizar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccionARealizar() {
        return accionARealizar;
    }

    /**
     * Define el valor de la propiedad accionARealizar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccionARealizar(String value) {
        this.accionARealizar = value;
    }

    /**
     * Obtiene el valor de la propiedad idFlujoNegocio.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdFlujoNegocio() {
        return idFlujoNegocio;
    }

    /**
     * Define el valor de la propiedad idFlujoNegocio.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdFlujoNegocio(Long value) {
        this.idFlujoNegocio = value;
    }

    /**
     * Obtiene el valor de la propiedad instanciaProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    /**
     * Define el valor de la propiedad instanciaProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanciaProceso(String value) {
        this.instanciaProceso = value;
    }

}
