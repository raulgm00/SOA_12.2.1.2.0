
package mx.agarcia.ea.utils.msoffice;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0-b170214.1743
 * Generated source version: 2.2
 *
 */
@WebServiceClient(name = "ExcelGeneratorUtilService", targetNamespace = "http://msoffice.utils.ea.agarcia.mx/",
                  wsdlLocation =
                  "http://localhost:7001/AGMSOfficeUtils/ExcelGeneratorUtilService?WSDL#%7Bhttp%3A%2F%2Fmsoffice.utils.ea.agarcia.mx%2F%7DExcelGeneratorUtilService")
public class ExcelGeneratorUtilService extends Service {

    private final static URL EXCELGENERATORUTILSERVICE_WSDL_LOCATION;
    private final static WebServiceException EXCELGENERATORUTILSERVICE_EXCEPTION;
    private final static QName EXCELGENERATORUTILSERVICE_QNAME =
        new QName("http://msoffice.utils.ea.agarcia.mx/", "ExcelGeneratorUtilService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url =
                new URL("http://localhost:7001/AGMSOfficeUtils/ExcelGeneratorUtilService?WSDL#%7Bhttp%3A%2F%2Fmsoffice.utils.ea.agarcia.mx%2F%7DExcelGeneratorUtilService");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        EXCELGENERATORUTILSERVICE_WSDL_LOCATION = url;
        EXCELGENERATORUTILSERVICE_EXCEPTION = e;
    }

    public ExcelGeneratorUtilService() {
        super(__getWsdlLocation(), EXCELGENERATORUTILSERVICE_QNAME);
    }

    public ExcelGeneratorUtilService(WebServiceFeature... features) {
        super(__getWsdlLocation(), EXCELGENERATORUTILSERVICE_QNAME, features);
    }

    public ExcelGeneratorUtilService(URL wsdlLocation) {
        super(wsdlLocation, EXCELGENERATORUTILSERVICE_QNAME);
    }

    public ExcelGeneratorUtilService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, EXCELGENERATORUTILSERVICE_QNAME, features);
    }

    public ExcelGeneratorUtilService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ExcelGeneratorUtilService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns ExcelGeneratorUtil
     */
    @WebEndpoint(name = "ExcelGeneratorUtilPort")
    public ExcelGeneratorUtil getExcelGeneratorUtilPort() {
        return super.getPort(new QName("http://msoffice.utils.ea.agarcia.mx/", "ExcelGeneratorUtilPort"),
                             ExcelGeneratorUtil.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ExcelGeneratorUtil
     */
    @WebEndpoint(name = "ExcelGeneratorUtilPort")
    public ExcelGeneratorUtil getExcelGeneratorUtilPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://msoffice.utils.ea.agarcia.mx/", "ExcelGeneratorUtilPort"),
                             ExcelGeneratorUtil.class, features);
    }

    private static URL __getWsdlLocation() {
        if (EXCELGENERATORUTILSERVICE_EXCEPTION != null) {
            throw EXCELGENERATORUTILSERVICE_EXCEPTION;
        }
        return EXCELGENERATORUTILSERVICE_WSDL_LOCATION;
    }

}
