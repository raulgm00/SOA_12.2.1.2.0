
package org.bcie.xmlns.objetoproceso.comun.cliente.v1;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.xmlns.objetoproceso.comun.cliente.v1 package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.xmlns.objetoproceso.comun.cliente.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ContactoType }
     * 
     */
    public ContactoType createContactoType() {
        return new ContactoType();
    }

    /**
     * Create an instance of {@link ComentarioType }
     * 
     */
    public ComentarioType createComentarioType() {
        return new ComentarioType();
    }

    /**
     * Create an instance of {@link Cliente }
     * 
     */
    public Cliente createCliente() {
        return new Cliente();
    }

    /**
     * Create an instance of {@link ClienteType }
     * 
     */
    public ClienteType createClienteType() {
        return new ClienteType();
    }

}
