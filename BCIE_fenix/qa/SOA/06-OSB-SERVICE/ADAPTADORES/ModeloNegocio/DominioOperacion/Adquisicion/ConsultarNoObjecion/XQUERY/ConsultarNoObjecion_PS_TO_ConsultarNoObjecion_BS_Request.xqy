xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarNoObjecion_DB";
(:: import schema at "../../ConsultarNoObjecion/XSD/ConsultarNoObjecion_DB.xsd" ::)

declare variable $ConsultarNoObjecionRequest as element() (:: schema-element(ns1:ConsultarNoObjecionRequest) ::) external;

declare function local:func($ConsultarNoObjecionRequest as element() (:: schema-element(ns1:ConsultarNoObjecionRequest) ::)) as element() (:: schema-element(ns2:ConsultarNoObjecion_DBInput) ::) {
    <ns2:ConsultarNoObjecionInput>
        <ns2:idNoObjecion>{fn:data($ConsultarNoObjecionRequest/ns1:idNoObjecion)}</ns2:idNoObjecion>
        <ns2:idAdquisicion>{fn:data($ConsultarNoObjecionRequest/ns1:idAquisicion)}</ns2:idAdquisicion>
    </ns2:ConsultarNoObjecionInput>
};

local:func($ConsultarNoObjecionRequest)