
package org.bcie.herramientacebo;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.herramientacebo package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.herramientacebo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HerramientaCEType }
     * 
     */
    public HerramientaCEType createHerramientaCEType() {
        return new HerramientaCEType();
    }

    /**
     * Create an instance of {@link ProgramaType }
     * 
     */
    public ProgramaType createProgramaType() {
        return new ProgramaType();
    }

    /**
     * Create an instance of {@link ClasificacionGeneralType }
     * 
     */
    public ClasificacionGeneralType createClasificacionGeneralType() {
        return new ClasificacionGeneralType();
    }

}
