
package com.oracle.xmlns.bpmn.bpmnprocess.aprobacionprocess;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.oracle.xmlns.bpmn.bpmnprocess.aprobacionprocess package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.oracle.xmlns.bpmn.bpmnprocess.aprobacionprocess
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InicioAprobacionCliente }
     * 
     */
    public InicioAprobacionCliente createInicioAprobacionCliente() {
        return new InicioAprobacionCliente();
    }

    /**
     * Create an instance of {@link FinAprobacionCliente }
     * 
     */
    public FinAprobacionCliente createFinAprobacionCliente() {
        return new FinAprobacionCliente();
    }

    /**
     * Create an instance of {@link FinAprobacion }
     * 
     */
    public FinAprobacion createFinAprobacion() {
        return new FinAprobacion();
    }

    /**
     * Create an instance of {@link InicioAprobacion }
     * 
     */
    public InicioAprobacion createInicioAprobacion() {
        return new InicioAprobacion();
    }

}
