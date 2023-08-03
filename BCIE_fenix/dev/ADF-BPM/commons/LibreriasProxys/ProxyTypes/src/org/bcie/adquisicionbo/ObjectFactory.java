
package org.bcie.adquisicionbo;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.adquisicionbo package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.adquisicionbo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConcursanteType }
     * 
     */
    public ConcursanteType createConcursanteType() {
        return new ConcursanteType();
    }

    /**
     * Create an instance of {@link NoObjecionType }
     * 
     */
    public NoObjecionType createNoObjecionType() {
        return new NoObjecionType();
    }

    /**
     * Create an instance of {@link AdquisicionType }
     * 
     */
    public AdquisicionType createAdquisicionType() {
        return new AdquisicionType();
    }

    /**
     * Create an instance of {@link AdjudicadoType }
     * 
     */
    public AdjudicadoType createAdjudicadoType() {
        return new AdjudicadoType();
    }

    /**
     * Create an instance of {@link TipoNoObjecionType }
     * 
     */
    public TipoNoObjecionType createTipoNoObjecionType() {
        return new TipoNoObjecionType();
    }

    /**
     * Create an instance of {@link HeaderPublicacionType }
     * 
     */
    public HeaderPublicacionType createHeaderPublicacionType() {
        return new HeaderPublicacionType();
    }

    /**
     * Create an instance of {@link ListaAdquisicion }
     * 
     */
    public ListaAdquisicion createListaAdquisicion() {
        return new ListaAdquisicion();
    }

}
