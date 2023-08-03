
package org.bcie.leccionaprendidabo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.leccionaprendidabo package. 
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

    private final static QName _CategoriaPayload_QNAME = new QName("http://www.bcie.org/LeccionAprendidaBO", "CategoriaPayload");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.leccionaprendidabo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CategoriaType }
     * 
     */
    public CategoriaType createCategoriaType() {
        return new CategoriaType();
    }

    /**
     * Create an instance of {@link LeccionAprendidaType }
     * 
     */
    public LeccionAprendidaType createLeccionAprendidaType() {
        return new LeccionAprendidaType();
    }

    /**
     * Create an instance of {@link CategoriasType }
     * 
     */
    public CategoriasType createCategoriasType() {
        return new CategoriasType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoriaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bcie.org/LeccionAprendidaBO", name = "CategoriaPayload")
    public JAXBElement<CategoriaType> createCategoriaPayload(CategoriaType value) {
        return new JAXBElement<CategoriaType>(_CategoriaPayload_QNAME, CategoriaType.class, null, value);
    }

}
