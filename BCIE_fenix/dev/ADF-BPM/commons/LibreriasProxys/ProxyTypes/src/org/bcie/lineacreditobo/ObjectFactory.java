
package org.bcie.lineacreditobo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.lineacreditobo package. 
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

    private final static QName _LineaCreditoBasicTypeMontoMaximo_QNAME = new QName("http://www.bcie.org/LineaCreditoBO", "montoMaximo");
    private final static QName _LineaCreditoBasicTypeTasaMinima_QNAME = new QName("http://www.bcie.org/LineaCreditoBO", "tasaMinima");
    private final static QName _LineaCreditoBasicTypeMontoMinimo_QNAME = new QName("http://www.bcie.org/LineaCreditoBO", "montoMinimo");
    private final static QName _LineaCreditoBasicTypeTasaMaxima_QNAME = new QName("http://www.bcie.org/LineaCreditoBO", "tasaMaxima");
    private final static QName _TBILineaCreditoTypeNumeroLineaCredito_QNAME = new QName("http://www.bcie.org/LineaCreditoBO", "numeroLineaCredito");
    private final static QName _TBILineaCreditoTypeInstanciaProceso_QNAME = new QName("http://www.bcie.org/LineaCreditoBO", "instanciaProceso");
    private final static QName _TBILineaCreditoTypeEstadoLineaCredito_QNAME = new QName("http://www.bcie.org/LineaCreditoBO", "estadoLineaCredito");
    private final static QName _TBILineaCreditoTypeUsuario_QNAME = new QName("http://www.bcie.org/LineaCreditoBO", "usuario");
    private final static QName _TBILineaCreditoTypeIdContrato_QNAME = new QName("http://www.bcie.org/LineaCreditoBO", "idContrato");
    private final static QName _TBILineaCreditoTypeIdTareaBpm_QNAME = new QName("http://www.bcie.org/LineaCreditoBO", "idTareaBpm");
    private final static QName _TBILineaCreditoTypeIdLineaCredito_QNAME = new QName("http://www.bcie.org/LineaCreditoBO", "idLineaCredito");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.lineacreditobo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoContrato }
     * 
     */
    public TipoContrato createTipoContrato() {
        return new TipoContrato();
    }

    /**
     * Create an instance of {@link TreLineaCreditoAprobacion }
     * 
     */
    public TreLineaCreditoAprobacion createTreLineaCreditoAprobacion() {
        return new TreLineaCreditoAprobacion();
    }

    /**
     * Create an instance of {@link Fuente }
     * 
     */
    public Fuente createFuente() {
        return new Fuente();
    }

    /**
     * Create an instance of {@link TBILineaCreditoType }
     * 
     */
    public TBILineaCreditoType createTBILineaCreditoType() {
        return new TBILineaCreditoType();
    }

    /**
     * Create an instance of {@link Garantias }
     * 
     */
    public Garantias createGarantias() {
        return new Garantias();
    }

    /**
     * Create an instance of {@link InformacionAdicional }
     * 
     */
    public InformacionAdicional createInformacionAdicional() {
        return new InformacionAdicional();
    }

    /**
     * Create an instance of {@link LineaCreditoAprobacion }
     * 
     */
    public LineaCreditoAprobacion createLineaCreditoAprobacion() {
        return new LineaCreditoAprobacion();
    }

    /**
     * Create an instance of {@link TBILineaCreditoCollection }
     * 
     */
    public TBILineaCreditoCollection createTBILineaCreditoCollection() {
        return new TBILineaCreditoCollection();
    }

    /**
     * Create an instance of {@link Flexcube }
     * 
     */
    public Flexcube createFlexcube() {
        return new Flexcube();
    }

    /**
     * Create an instance of {@link TeenLineaCreditoType }
     * 
     */
    public TeenLineaCreditoType createTeenLineaCreditoType() {
        return new TeenLineaCreditoType();
    }

    /**
     * Create an instance of {@link Prestamo }
     * 
     */
    public Prestamo createPrestamo() {
        return new Prestamo();
    }

    /**
     * Create an instance of {@link LineaCredito }
     * 
     */
    public LineaCredito createLineaCredito() {
        return new LineaCredito();
    }

    /**
     * Create an instance of {@link LineaCreditoBasicType }
     * 
     */
    public LineaCreditoBasicType createLineaCreditoBasicType() {
        return new LineaCreditoBasicType();
    }

    /**
     * Create an instance of {@link LineaCreditoFlexcubeType }
     * 
     */
    public LineaCreditoFlexcubeType createLineaCreditoFlexcubeType() {
        return new LineaCreditoFlexcubeType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/LineaCreditoBO", name = "montoMaximo", scope = LineaCreditoBasicType.class)
    public JAXBElement<Double> createLineaCreditoBasicTypeMontoMaximo(Double value) {
        return new JAXBElement<Double>(_LineaCreditoBasicTypeMontoMaximo_QNAME, Double.class, LineaCreditoBasicType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/LineaCreditoBO", name = "tasaMinima", scope = LineaCreditoBasicType.class)
    public JAXBElement<Double> createLineaCreditoBasicTypeTasaMinima(Double value) {
        return new JAXBElement<Double>(_LineaCreditoBasicTypeTasaMinima_QNAME, Double.class, LineaCreditoBasicType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/LineaCreditoBO", name = "montoMinimo", scope = LineaCreditoBasicType.class)
    public JAXBElement<Double> createLineaCreditoBasicTypeMontoMinimo(Double value) {
        return new JAXBElement<Double>(_LineaCreditoBasicTypeMontoMinimo_QNAME, Double.class, LineaCreditoBasicType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/LineaCreditoBO", name = "tasaMaxima", scope = LineaCreditoBasicType.class)
    public JAXBElement<Double> createLineaCreditoBasicTypeTasaMaxima(Double value) {
        return new JAXBElement<Double>(_LineaCreditoBasicTypeTasaMaxima_QNAME, Double.class, LineaCreditoBasicType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/LineaCreditoBO", name = "numeroLineaCredito", scope = TBILineaCreditoType.class)
    public JAXBElement<String> createTBILineaCreditoTypeNumeroLineaCredito(String value) {
        return new JAXBElement<String>(_TBILineaCreditoTypeNumeroLineaCredito_QNAME, String.class, TBILineaCreditoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/LineaCreditoBO", name = "instanciaProceso", scope = TBILineaCreditoType.class)
    public JAXBElement<String> createTBILineaCreditoTypeInstanciaProceso(String value) {
        return new JAXBElement<String>(_TBILineaCreditoTypeInstanciaProceso_QNAME, String.class, TBILineaCreditoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/LineaCreditoBO", name = "estadoLineaCredito", scope = TBILineaCreditoType.class)
    public JAXBElement<String> createTBILineaCreditoTypeEstadoLineaCredito(String value) {
        return new JAXBElement<String>(_TBILineaCreditoTypeEstadoLineaCredito_QNAME, String.class, TBILineaCreditoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/LineaCreditoBO", name = "usuario", scope = TBILineaCreditoType.class)
    public JAXBElement<String> createTBILineaCreditoTypeUsuario(String value) {
        return new JAXBElement<String>(_TBILineaCreditoTypeUsuario_QNAME, String.class, TBILineaCreditoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/LineaCreditoBO", name = "idContrato", scope = TBILineaCreditoType.class)
    public JAXBElement<Long> createTBILineaCreditoTypeIdContrato(Long value) {
        return new JAXBElement<Long>(_TBILineaCreditoTypeIdContrato_QNAME, Long.class, TBILineaCreditoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/LineaCreditoBO", name = "idTareaBpm", scope = TBILineaCreditoType.class)
    public JAXBElement<Long> createTBILineaCreditoTypeIdTareaBpm(Long value) {
        return new JAXBElement<Long>(_TBILineaCreditoTypeIdTareaBpm_QNAME, Long.class, TBILineaCreditoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/LineaCreditoBO", name = "idLineaCredito", scope = TBILineaCreditoType.class)
    public JAXBElement<Long> createTBILineaCreditoTypeIdLineaCredito(Long value) {
        return new JAXBElement<Long>(_TBILineaCreditoTypeIdLineaCredito_QNAME, Long.class, TBILineaCreditoType.class, value);
    }

}
