
package org.bcie.clientebo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.clientebo package. 
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

    private final static QName _TipoPersonaIdTipoDocumento_QNAME = new QName("http://www.bcie.org/ClienteBO", "idTipoDocumento");
    private final static QName _TipoPersonaEstado_QNAME = new QName("http://www.bcie.org/ClienteBO", "estado");
    private final static QName _TipoPersonaCodigoExterno_QNAME = new QName("http://www.bcie.org/ClienteBO", "codigoExterno");
    private final static QName _TipoPersonaFechaRegistro_QNAME = new QName("http://www.bcie.org/ClienteBO", "fechaRegistro");
    private final static QName _TipoPersonaDescripcionCorta_QNAME = new QName("http://www.bcie.org/ClienteBO", "descripcionCorta");
    private final static QName _TipoPersonaDescripcion_QNAME = new QName("http://www.bcie.org/ClienteBO", "descripcion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.clientebo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Contacto }
     * 
     */
    public Contacto createContacto() {
        return new Contacto();
    }

    /**
     * Create an instance of {@link Cliente }
     * 
     */
    public Cliente createCliente() {
        return new Cliente();
    }

    /**
     * Create an instance of {@link TipoPersona }
     * 
     */
    public TipoPersona createTipoPersona() {
        return new TipoPersona();
    }

    /**
     * Create an instance of {@link SCRType }
     * 
     */
    public SCRType createSCRType() {
        return new SCRType();
    }

    /**
     * Create an instance of {@link ReservaType }
     * 
     */
    public ReservaType createReservaType() {
        return new ReservaType();
    }

    /**
     * Create an instance of {@link Contactos }
     * 
     */
    public Contactos createContactos() {
        return new Contactos();
    }

    /**
     * Create an instance of {@link ClienteBasicType }
     * 
     */
    public ClienteBasicType createClienteBasicType() {
        return new ClienteBasicType();
    }

    /**
     * Create an instance of {@link DetalleSCRType }
     * 
     */
    public DetalleSCRType createDetalleSCRType() {
        return new DetalleSCRType();
    }

    /**
     * Create an instance of {@link SeguimientoCrediticioType }
     * 
     */
    public SeguimientoCrediticioType createSeguimientoCrediticioType() {
        return new SeguimientoCrediticioType();
    }

    /**
     * Create an instance of {@link Mora }
     * 
     */
    public Mora createMora() {
        return new Mora();
    }

    /**
     * Create an instance of {@link EntidadMinimaOperacion }
     * 
     */
    public EntidadMinimaOperacion createEntidadMinimaOperacion() {
        return new EntidadMinimaOperacion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/ClienteBO", name = "idTipoDocumento", scope = TipoPersona.class)
    public JAXBElement<Long> createTipoPersonaIdTipoDocumento(Long value) {
        return new JAXBElement<Long>(_TipoPersonaIdTipoDocumento_QNAME, Long.class, TipoPersona.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/ClienteBO", name = "estado", scope = TipoPersona.class)
    public JAXBElement<String> createTipoPersonaEstado(String value) {
        return new JAXBElement<String>(_TipoPersonaEstado_QNAME, String.class, TipoPersona.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/ClienteBO", name = "codigoExterno", scope = TipoPersona.class)
    public JAXBElement<String> createTipoPersonaCodigoExterno(String value) {
        return new JAXBElement<String>(_TipoPersonaCodigoExterno_QNAME, String.class, TipoPersona.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/ClienteBO", name = "fechaRegistro", scope = TipoPersona.class)
    public JAXBElement<XMLGregorianCalendar> createTipoPersonaFechaRegistro(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TipoPersonaFechaRegistro_QNAME, XMLGregorianCalendar.class, TipoPersona.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/ClienteBO", name = "descripcionCorta", scope = TipoPersona.class)
    public JAXBElement<Integer> createTipoPersonaDescripcionCorta(Integer value) {
        return new JAXBElement<Integer>(_TipoPersonaDescripcionCorta_QNAME, Integer.class, TipoPersona.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/ClienteBO", name = "descripcion", scope = TipoPersona.class)
    public JAXBElement<String> createTipoPersonaDescripcion(String value) {
        return new JAXBElement<String>(_TipoPersonaDescripcion_QNAME, String.class, TipoPersona.class, value);
    }

}
