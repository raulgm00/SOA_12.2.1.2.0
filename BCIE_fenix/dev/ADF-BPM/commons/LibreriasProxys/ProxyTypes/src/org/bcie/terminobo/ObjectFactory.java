
package org.bcie.terminobo;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.terminobo package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.terminobo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Termino }
     * 
     */
    public Termino createTermino() {
        return new Termino();
    }

    /**
     * Create an instance of {@link CatalogoTermino }
     * 
     */
    public CatalogoTermino createCatalogoTermino() {
        return new CatalogoTermino();
    }

    /**
     * Create an instance of {@link ListaTerminos }
     * 
     */
    public ListaTerminos createListaTerminos() {
        return new ListaTerminos();
    }

}
