
package org.bcie.implementacionpctbo;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.implementacionpctbo package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.implementacionpctbo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListaImplementacionPCT }
     * 
     */
    public ListaImplementacionPCT createListaImplementacionPCT() {
        return new ListaImplementacionPCT();
    }

    /**
     * Create an instance of {@link LoteType }
     * 
     */
    public LoteType createLoteType() {
        return new LoteType();
    }

    /**
     * Create an instance of {@link ResultadoLoteType }
     * 
     */
    public ResultadoLoteType createResultadoLoteType() {
        return new ResultadoLoteType();
    }

    /**
     * Create an instance of {@link ImplementacionType }
     * 
     */
    public ImplementacionType createImplementacionType() {
        return new ImplementacionType();
    }

}
