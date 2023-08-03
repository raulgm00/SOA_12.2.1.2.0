//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.01.04 a las 08:46:22 AM CST 
//


package www.bcie.org.cumplimientocondiciones;


import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.herramientaofismatica.cumplimientocondiciones package. 
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

    private final static QName _Cumplimiento_QNAME = new QName("http://www.bcie.org/herramientaOfismatica/cumplimientocondiciones", "cumplimiento");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.herramientaofismatica.cumplimientocondiciones
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Cumplimiento }
     * 
     */
    public Cumplimiento createCumplimiento() {
        return new Cumplimiento();
    }

    /**
     * Create an instance of {@link LstCondicion }
     * 
     */
    public LstCondicion createLstCondicion() {
        return new LstCondicion();
    }

    /**
     * Create an instance of {@link Condicion }
     * 
     */
    public Condicion createCondicion() {
        return new Condicion();
    }

    /**
     * Create an instance of {@link CategoriaValidacion }
     * 
     */
    public CategoriaValidacion createCategoriaValidacion() {
        return new CategoriaValidacion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cumplimiento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/herramientaOfismatica/cumplimientocondiciones", name = "cumplimiento")
    public JAXBElement<Cumplimiento> createCumplimiento(Cumplimiento value) {
        return new JAXBElement<Cumplimiento>(_Cumplimiento_QNAME, Cumplimiento.class, null, value);
    }

}
