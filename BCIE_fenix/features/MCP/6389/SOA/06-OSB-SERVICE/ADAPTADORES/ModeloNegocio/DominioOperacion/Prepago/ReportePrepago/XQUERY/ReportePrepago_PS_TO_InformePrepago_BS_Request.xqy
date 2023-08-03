xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)

declare variable $ReportePrepagoRequest as element() (:: schema-element(ns1:ReportePrepagoRequest) ::) external;

declare function local:func($ReportePrepagoRequest as element() (:: schema-element(ns1:ReportePrepagoRequest) ::)) as element() (:: schema-element(ns1:InformePrepagoRequest) ::) {
    <ns1:InformePrepagoRequest>
        <ns1:idPrepago>{fn:data($ReportePrepagoRequest/ns1:idPrepago)}</ns1:idPrepago>
    </ns1:InformePrepagoRequest>
};

local:func($ReportePrepagoRequest)
