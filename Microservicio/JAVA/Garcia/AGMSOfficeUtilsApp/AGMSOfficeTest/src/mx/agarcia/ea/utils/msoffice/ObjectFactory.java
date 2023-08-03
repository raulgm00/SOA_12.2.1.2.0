
package mx.agarcia.ea.utils.msoffice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the mx.agarcia.ea.utils.msoffice package.
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

    private final static QName _Generate_QNAME = new QName("http://msoffice.utils.ea.agarcia.mx/", "generate");
    private final static QName _GenerateResponse_QNAME =
        new QName("http://msoffice.utils.ea.agarcia.mx/", "generateResponse");
    private final static QName _MSOfficeUtilException_QNAME =
        new QName("http://msoffice.utils.ea.agarcia.mx/", "MSOfficeUtilException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.agarcia.ea.utils.msoffice
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Generate }
     *
     */
    public Generate createGenerate() {
        return new Generate();
    }

    /**
     * Create an instance of {@link GenerateResponse }
     *
     */
    public GenerateResponse createGenerateResponse() {
        return new GenerateResponse();
    }

    /**
     * Create an instance of {@link MSOfficeUtilException }
     *
     */
    public MSOfficeUtilException createMSOfficeUtilException() {
        return new MSOfficeUtilException();
    }

    /**
     * Create an instance of {@link ExcelData }
     *
     */
    public ExcelData createExcelData() {
        return new ExcelData();
    }

    /**
     * Create an instance of {@link ExcelRow }
     *
     */
    public ExcelRow createExcelRow() {
        return new ExcelRow();
    }

    /**
     * Create an instance of {@link ExcelCell }
     *
     */
    public ExcelCell createExcelCell() {
        return new ExcelCell();
    }

    /**
     * Create an instance of {@link ExcelFile }
     *
     */
    public ExcelFile createExcelFile() {
        return new ExcelFile();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Generate }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Generate }{@code >}
     */
    @XmlElementDecl(namespace = "http://msoffice.utils.ea.agarcia.mx/", name = "generate")
    public JAXBElement<Generate> createGenerate(Generate value) {
        return new JAXBElement<Generate>(_Generate_QNAME, Generate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateResponse }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GenerateResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://msoffice.utils.ea.agarcia.mx/", name = "generateResponse")
    public JAXBElement<GenerateResponse> createGenerateResponse(GenerateResponse value) {
        return new JAXBElement<GenerateResponse>(_GenerateResponse_QNAME, GenerateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MSOfficeUtilException }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MSOfficeUtilException }{@code >}
     */
    @XmlElementDecl(namespace = "http://msoffice.utils.ea.agarcia.mx/", name = "MSOfficeUtilException")
    public JAXBElement<MSOfficeUtilException> createMSOfficeUtilException(MSOfficeUtilException value) {
        return new JAXBElement<MSOfficeUtilException>(_MSOfficeUtilException_QNAME, MSOfficeUtilException.class, null,
                                                      value);
    }

}
