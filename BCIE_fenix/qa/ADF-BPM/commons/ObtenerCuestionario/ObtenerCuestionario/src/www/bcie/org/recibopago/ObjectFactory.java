
package www.bcie.org.recibopago;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.bcie.herramientaofismatica.recibopago package.
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

    private final static QName _ReciboPago_QNAME = new QName("http://www.bcie.org/herramientaOfismatica/ReciboPago", "ReciboPago");
    
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.herramientaofismatica.recibopago
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Detalles }
     *
     */
    public Detalles createDetalles() {
        return new Detalles();
    }

    /**
     * Create an instance of {@link Recibo }
     *
     */
    public Recibo createRecibo() {
        return new Recibo();
    }

    /**
     * Create an instance of {@link ReciboPago }
     *
     */
    public ReciboPago createReciboPago() {
        return new ReciboPago();
    }

    /**
     * Create an instance of {@link TablaDetalle }
     *
     */
    public TablaDetalle createTablaDetalle() {
        return new TablaDetalle();
    }
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReciboPago }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/herramientaOfismatica/ReciboPago", name = "ReciboPago")
    public JAXBElement<ReciboPago> createReciboPago(ReciboPago value) {
        return new JAXBElement<ReciboPago>(_ReciboPago_QNAME, ReciboPago.class, null, value);
    }
}
