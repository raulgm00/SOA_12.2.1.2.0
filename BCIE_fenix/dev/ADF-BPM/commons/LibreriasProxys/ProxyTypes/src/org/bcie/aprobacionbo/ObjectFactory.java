
package org.bcie.aprobacionbo;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.aprobacionbo package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.aprobacionbo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UsuarioReunion }
     * 
     */
    public UsuarioReunion createUsuarioReunion() {
        return new UsuarioReunion();
    }

    /**
     * Create an instance of {@link Aprobacion }
     * 
     */
    public Aprobacion createAprobacion() {
        return new Aprobacion();
    }

    /**
     * Create an instance of {@link MontoBase }
     * 
     */
    public MontoBase createMontoBase() {
        return new MontoBase();
    }

}
