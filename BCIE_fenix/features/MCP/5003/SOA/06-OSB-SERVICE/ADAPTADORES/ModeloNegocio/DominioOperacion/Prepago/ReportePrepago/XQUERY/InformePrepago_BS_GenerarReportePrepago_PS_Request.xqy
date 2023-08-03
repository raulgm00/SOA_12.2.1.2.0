xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace pre = "http://www.bcie.org/PrepagoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace pre1 = "http://www.bcie.org/PrepagoBO";

declare variable $InformePrepagoResponse as element() (:: schema-element(ns1:InformePrepagoResponse) ::) external;

declare function local:func($InformePrepagoResponse as element() (:: schema-element(ns1:InformePrepagoResponse) ::)) as element() (:: schema-element(ns1:GenerarReportePrepagoRequest) ::) {
    <ns1:GenerarReportePrepagoRequest>
        <ns1:Prepago>{$InformePrepagoResponse/ns1:Prepago/*}</ns1:Prepago>
        <ns1:Reporte>{$InformePrepagoResponse/ns1:Reporte/*}</ns1:Reporte>
    </ns1:GenerarReportePrepagoRequest>
};

local:func($InformePrepagoResponse)
