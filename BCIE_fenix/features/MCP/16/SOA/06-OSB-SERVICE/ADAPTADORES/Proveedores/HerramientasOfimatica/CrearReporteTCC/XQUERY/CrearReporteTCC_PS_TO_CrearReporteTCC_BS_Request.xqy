xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://service.org.bcie.www/";
(:: import schema at "../XSD/CrearReporteTCC.xsd" ::)
declare namespace ns1="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)

declare variable $idOperacion as element() (:: schema-element(ns1:CrearReporteTCCRequest) ::) external;

declare function local:func($idOperacion as element() (:: schema-element(ns1:CrearReporteTCCRequest) ::)) as element() (:: schema-element(ns2:crearReporteTCC) ::) {
    <ns2:crearReporteTCC>
        <idOperacion>{fn:data($idOperacion/ns1:idOperacion)}</idOperacion>
    </ns2:crearReporteTCC>
};

local:func($idOperacion)
