xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAdquisicion";
(:: import schema at "../XSD/ConsultarAdquisicion.xsd" ::)

declare variable $ConsultarAdquisicionRequest as element() (:: schema-element(ns1:ConsultarAdquisicionRequest) ::) external;

declare function local:func($ConsultarAdquisicionRequest as element() (:: schema-element(ns1:ConsultarAdquisicionRequest) ::)) as element() (:: schema-element(ns2:ConsultarAdquisicionInput) ::) {
    <ns2:ConsultarAdquisicionInput>
        <ns2:idAdquisicion>{fn:data($ConsultarAdquisicionRequest/ns1:idAdquisicion)}</ns2:idAdquisicion>
        <ns2:idOperacion>{fn:data($ConsultarAdquisicionRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarAdquisicionInput>
};

local:func($ConsultarAdquisicionRequest)