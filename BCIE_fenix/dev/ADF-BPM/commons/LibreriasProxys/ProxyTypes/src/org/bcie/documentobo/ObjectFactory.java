
package org.bcie.documentobo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.documentobo package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TipoDocumentoFechaRegistro_QNAME = new QName("http://www.bcie.org/DocumentoBO", "fechaRegistro");
    private final static QName _TipoDocumentoIdGrupoExterno_QNAME = new QName("http://www.bcie.org/DocumentoBO", "idGrupoExterno");
    private final static QName _TipoDocumentoDescripcion_QNAME = new QName("http://www.bcie.org/DocumentoBO", "descripcion");
    private final static QName _TipoDocumentoCodigoExterno_QNAME = new QName("http://www.bcie.org/DocumentoBO", "codigoExterno");
    private final static QName _TipoDocumentoIdTipoExterno_QNAME = new QName("http://www.bcie.org/DocumentoBO", "idTipoExterno");
    private final static QName _TipoDocumentoEstado_QNAME = new QName("http://www.bcie.org/DocumentoBO", "estado");
    private final static QName _TipoDocumentoIdTipoDocumento_QNAME = new QName("http://www.bcie.org/DocumentoBO", "idTipoDocumento");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.documentobo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Documentos }
     * 
     */
    public Documentos createDocumentos() {
        return new Documentos();
    }

    /**
     * Create an instance of {@link TipoDocumento }
     * 
     */
    public TipoDocumento createTipoDocumento() {
        return new TipoDocumento();
    }

    /**
     * Create an instance of {@link ListaDocumentos }
     * 
     */
    public ListaDocumentos createListaDocumentos() {
        return new ListaDocumentos();
    }

    /**
     * Create an instance of {@link Documento }
     * 
     */
    public Documento createDocumento() {
        return new Documento();
    }

    /**
     * Create an instance of {@link Etapa }
     * 
     */
    public Etapa createEtapa() {
        return new Etapa();
    }

    /**
     * Create an instance of {@link TipoDcoumento }
     * 
     */
    public TipoDcoumento createTipoDcoumento() {
        return new TipoDcoumento();
    }

    /**
     * Create an instance of {@link ListaIdDocumento }
     * 
     */
    public ListaIdDocumento createListaIdDocumento() {
        return new ListaIdDocumento();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/DocumentoBO", name = "fechaRegistro", scope = TipoDocumento.class)
    public JAXBElement<XMLGregorianCalendar> createTipoDocumentoFechaRegistro(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TipoDocumentoFechaRegistro_QNAME, XMLGregorianCalendar.class, TipoDocumento.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/DocumentoBO", name = "idGrupoExterno", scope = TipoDocumento.class)
    public JAXBElement<Integer> createTipoDocumentoIdGrupoExterno(Integer value) {
        return new JAXBElement<Integer>(_TipoDocumentoIdGrupoExterno_QNAME, Integer.class, TipoDocumento.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/DocumentoBO", name = "descripcion", scope = TipoDocumento.class)
    public JAXBElement<String> createTipoDocumentoDescripcion(String value) {
        return new JAXBElement<String>(_TipoDocumentoDescripcion_QNAME, String.class, TipoDocumento.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/DocumentoBO", name = "codigoExterno", scope = TipoDocumento.class)
    public JAXBElement<String> createTipoDocumentoCodigoExterno(String value) {
        return new JAXBElement<String>(_TipoDocumentoCodigoExterno_QNAME, String.class, TipoDocumento.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/DocumentoBO", name = "idTipoExterno", scope = TipoDocumento.class)
    public JAXBElement<Integer> createTipoDocumentoIdTipoExterno(Integer value) {
        return new JAXBElement<Integer>(_TipoDocumentoIdTipoExterno_QNAME, Integer.class, TipoDocumento.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/DocumentoBO", name = "estado", scope = TipoDocumento.class)
    public JAXBElement<String> createTipoDocumentoEstado(String value) {
        return new JAXBElement<String>(_TipoDocumentoEstado_QNAME, String.class, TipoDocumento.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/DocumentoBO", name = "idTipoDocumento", scope = TipoDocumento.class)
    public JAXBElement<Long> createTipoDocumentoIdTipoDocumento(Long value) {
        return new JAXBElement<Long>(_TipoDocumentoIdTipoDocumento_QNAME, Long.class, TipoDocumento.class, value);
    }

}
