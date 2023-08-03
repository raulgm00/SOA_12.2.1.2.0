
package org.bcie.condicionbo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.condicionbo package. 
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

    private final static QName _CondicionBanEstatus_QNAME = new QName("http://www.bcie.org/CondicionBO", "banEstatus");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.condicionbo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TransaccionCondicion }
     * 
     */
    public TransaccionCondicion createTransaccionCondicion() {
        return new TransaccionCondicion();
    }

    /**
     * Create an instance of {@link Condicion }
     * 
     */
    public Condicion createCondicion() {
        return new Condicion();
    }

    /**
     * Create an instance of {@link ListaTransaccionCondicion }
     * 
     */
    public ListaTransaccionCondicion createListaTransaccionCondicion() {
        return new ListaTransaccionCondicion();
    }

    /**
     * Create an instance of {@link ListaCondiciones }
     * 
     */
    public ListaCondiciones createListaCondiciones() {
        return new ListaCondiciones();
    }

    /**
     * Create an instance of {@link Cumplimiento }
     * 
     */
    public Cumplimiento createCumplimiento() {
        return new Cumplimiento();
    }

    /**
     * Create an instance of {@link CatalogoCondicion }
     * 
     */
    public CatalogoCondicion createCatalogoCondicion() {
        return new CatalogoCondicion();
    }

    /**
     * Create an instance of {@link ObservacionCondicion }
     * 
     */
    public ObservacionCondicion createObservacionCondicion() {
        return new ObservacionCondicion();
    }

    /**
     * Create an instance of {@link ValidacionCondicion }
     * 
     */
    public ValidacionCondicion createValidacionCondicion() {
        return new ValidacionCondicion();
    }

    /**
     * Create an instance of {@link CategoriaCondicion }
     * 
     */
    public CategoriaCondicion createCategoriaCondicion() {
        return new CategoriaCondicion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/CondicionBO", name = "banEstatus", scope = Condicion.class)
    public JAXBElement<Integer> createCondicionBanEstatus(Integer value) {
        return new JAXBElement<Integer>(_CondicionBanEstatus_QNAME, Integer.class, Condicion.class, value);
    }

}
