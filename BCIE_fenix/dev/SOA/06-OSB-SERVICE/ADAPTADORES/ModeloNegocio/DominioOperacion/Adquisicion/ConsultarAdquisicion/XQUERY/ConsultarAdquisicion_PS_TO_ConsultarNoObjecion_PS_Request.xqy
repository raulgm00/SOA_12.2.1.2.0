xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)

declare variable $ConsultarAdquisicionRequest as element() (:: schema-element(ns1:ConsultarAdquisicionRequest) ::) external;

declare function local:func($ConsultarAdquisicionRequest as element() (:: schema-element(ns1:ConsultarAdquisicionRequest) ::)) as element() (:: schema-element(ns1:ConsultarNoObjecionRequest) ::) {
    <ns1:ConsultarNoObjecionRequest>
        <ns1:idNoObjecion>{fn:data($ConsultarAdquisicionRequest/ns1:idNoObjecion)}</ns1:idNoObjecion>
    </ns1:ConsultarNoObjecionRequest>
};

local:func($ConsultarAdquisicionRequest)