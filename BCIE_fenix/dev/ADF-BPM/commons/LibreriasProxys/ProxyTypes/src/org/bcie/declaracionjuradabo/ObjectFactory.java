
package org.bcie.declaracionjuradabo;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.declaracionjuradabo package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.declaracionjuradabo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EstadoDeclaracion }
     * 
     */
    public EstadoDeclaracion createEstadoDeclaracion() {
        return new EstadoDeclaracion();
    }

    /**
     * Create an instance of {@link Justificacion }
     * 
     */
    public Justificacion createJustificacion() {
        return new Justificacion();
    }

    /**
     * Create an instance of {@link DeclaracionJurada }
     * 
     */
    public DeclaracionJurada createDeclaracionJurada() {
        return new DeclaracionJurada();
    }

    /**
     * Create an instance of {@link CalificacionDeRiesgo }
     * 
     */
    public CalificacionDeRiesgo createCalificacionDeRiesgo() {
        return new CalificacionDeRiesgo();
    }

    /**
     * Create an instance of {@link EstadoBusqueda }
     * 
     */
    public EstadoBusqueda createEstadoBusqueda() {
        return new EstadoBusqueda();
    }

}
