
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

    private final static QName _MSOfficeUtilException_QNAME =
        new QName("http://msoffice.utils.ea.agarcia.mx/", "MSOfficeUtilException");
    private final static QName _Parse_QNAME = new QName("http://msoffice.utils.ea.agarcia.mx/", "parse");
    private final static QName _ParseResponse_QNAME =
        new QName("http://msoffice.utils.ea.agarcia.mx/", "parseResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.agarcia.ea.utils.msoffice
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Header }
     *
     */
    public Header createHeader() {
        return new Header();
    }

    /**
     * Create an instance of {@link Header.Parameters }
     *
     */
    public Header.Parameters createHeaderParameters() {
        return new Header.Parameters();
    }

    /**
     * Create an instance of {@link MSOfficeUtilException }
     *
     */
    public MSOfficeUtilException createMSOfficeUtilException() {
        return new MSOfficeUtilException();
    }

    /**
     * Create an instance of {@link Parse }
     *
     */
    public Parse createParse() {
        return new Parse();
    }

    /**
     * Create an instance of {@link ParseResponse }
     *
     */
    public ParseResponse createParseResponse() {
        return new ParseResponse();
    }

    /**
     * Create an instance of {@link Request }
     *
     */
    public Request createRequest() {
        return new Request();
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
     * Create an instance of {@link Header.Parameters.Entry }
     *
     */
    public Header.Parameters.Entry createHeaderParametersEntry() {
        return new Header.Parameters.Entry();
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

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Parse }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Parse }{@code >}
     */
    @XmlElementDecl(namespace = "http://msoffice.utils.ea.agarcia.mx/", name = "parse")
    public JAXBElement<Parse> createParse(Parse value) {
        return new JAXBElement<Parse>(_Parse_QNAME, Parse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseResponse }{@code >}
     *
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ParseResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://msoffice.utils.ea.agarcia.mx/", name = "parseResponse")
    public JAXBElement<ParseResponse> createParseResponse(ParseResponse value) {
        return new JAXBElement<ParseResponse>(_ParseResponse_QNAME, ParseResponse.class, null, value);
    }

}
