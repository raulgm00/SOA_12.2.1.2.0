//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.25 at 11:19:22 AM CDT 
//


package www.bcie.org.prepago;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.herramientaofismatica.prepago package. 
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

    private final static QName _ReportePrePago_QNAME = new QName("http://www.bcie.org/herramientaOfismatica/prepago", "ReportePrePago");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.herramientaofismatica.prepago
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PrePago }
     * 
     */
    public PrePago createPrePago() {
        return new PrePago();
    }

    /**
     * Create an instance of {@link DetalleCargosPenalidades }
     * 
     */
    public DetalleCargosPenalidades createDetalleCargosPenalidades() {
        return new DetalleCargosPenalidades();
    }

    /**
     * Create an instance of {@link DetallePenalidad }
     * 
     */
    public DetallePenalidad createDetallePenalidad() {
        return new DetallePenalidad();
    }

    /**
     * Create an instance of {@link Observaciones }
     * 
     */
    public Observaciones createObservaciones() {
        return new Observaciones();
    }

    /**
     * Create an instance of {@link VentaCartera }
     * 
     */
    public VentaCartera createVentaCartera() {
        return new VentaCartera();
    }

    /**
     * Create an instance of {@link Comentario2 }
     * 
     */
    public Comentario2 createComentario2() {
        return new Comentario2();
    }

    /**
     * Create an instance of {@link Comentario1 }
     * 
     */
    public Comentario1 createComentario1() {
        return new Comentario1();
    }

    /**
     * Create an instance of {@link FuentesExternas }
     * 
     */
    public FuentesExternas createFuentesExternas() {
        return new FuentesExternas();
    }

    /**
     * Create an instance of {@link DatosBanco }
     * 
     */
    public DatosBanco createDatosBanco() {
        return new DatosBanco();
    }

    /**
     * Create an instance of {@link ConsolidadoPago }
     * 
     */
    public ConsolidadoPago createConsolidadoPago() {
        return new ConsolidadoPago();
    }

    /**
     * Create an instance of {@link DetallesConsolidadoPago }
     * 
     */
    public DetallesConsolidadoPago createDetallesConsolidadoPago() {
        return new DetallesConsolidadoPago();
    }

    /**
     * Create an instance of {@link CondicionesEspeciales }
     * 
     */
    public CondicionesEspeciales createCondicionesEspeciales() {
        return new CondicionesEspeciales();
    }

    /**
     * Create an instance of {@link Coberturas }
     * 
     */
    public Coberturas createCoberturas() {
        return new Coberturas();
    }

    /**
     * Create an instance of {@link DetalleCalculos }
     * 
     */
    public DetalleCalculos createDetalleCalculos() {
        return new DetalleCalculos();
    }

    /**
     * Create an instance of {@link DatosPrePago }
     * 
     */
    public DatosPrePago createDatosPrePago() {
        return new DatosPrePago();
    }

    /**
     * Create an instance of {@link PrePagoReporte }
     * 
     */
    public PrePagoReporte createPrePagoReporte() {
        return new PrePagoReporte();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrePago }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/herramientaOfismatica/prepago", name = "ReportePrePago")
    public JAXBElement<PrePago> createReportePrePago(PrePago value) {
        return new JAXBElement<PrePago>(_ReportePrePago_QNAME, PrePago.class, null, value);
    }

}
