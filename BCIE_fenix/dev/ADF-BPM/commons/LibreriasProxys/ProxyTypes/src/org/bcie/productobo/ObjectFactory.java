
package org.bcie.productobo;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bcie.productobo package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bcie.productobo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InstrumentoFinanciero }
     * 
     */
    public InstrumentoFinanciero createInstrumentoFinanciero() {
        return new InstrumentoFinanciero();
    }

    /**
     * Create an instance of {@link Tareas }
     * 
     */
    public Tareas createTareas() {
        return new Tareas();
    }

    /**
     * Create an instance of {@link ConfiguracionProductoTareas }
     * 
     */
    public ConfiguracionProductoTareas createConfiguracionProductoTareas() {
        return new ConfiguracionProductoTareas();
    }

    /**
     * Create an instance of {@link Responsable }
     * 
     */
    public Responsable createResponsable() {
        return new Responsable();
    }

    /**
     * Create an instance of {@link Producto }
     * 
     */
    public Producto createProducto() {
        return new Producto();
    }

    /**
     * Create an instance of {@link ProductoBasicType }
     * 
     */
    public ProductoBasicType createProductoBasicType() {
        return new ProductoBasicType();
    }

    /**
     * Create an instance of {@link TareasPorProducto }
     * 
     */
    public TareasPorProducto createTareasPorProducto() {
        return new TareasPorProducto();
    }

    /**
     * Create an instance of {@link RequiereCierre }
     * 
     */
    public RequiereCierre createRequiereCierre() {
        return new RequiereCierre();
    }

    /**
     * Create an instance of {@link ReglasOperacion }
     * 
     */
    public ReglasOperacion createReglasOperacion() {
        return new ReglasOperacion();
    }

}
