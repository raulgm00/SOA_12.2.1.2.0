xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ExtraerReportePrepago";
(:: import schema at "../XSD/ExtraerReportePrepago_sp.xsd" ::)

declare variable $ExtraerReportePrepagoRequest as element() (:: schema-element(ns1:ExtraerReportePrepagoRequest) ::) external;

declare function local:func($ExtraerReportePrepagoRequest as element() (:: schema-element(ns1:ExtraerReportePrepagoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_PREPAGO>{fn:data($ExtraerReportePrepagoRequest/ns1:idPrepago)}</ns2:P_ID_PREPAGO>
    </ns2:InputParameters>
};

local:func($ExtraerReportePrepagoRequest)
