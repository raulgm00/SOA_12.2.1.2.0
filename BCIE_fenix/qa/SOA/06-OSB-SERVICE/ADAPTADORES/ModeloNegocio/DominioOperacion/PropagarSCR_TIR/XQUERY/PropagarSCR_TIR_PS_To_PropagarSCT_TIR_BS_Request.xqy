xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/PropagarSCR_TIR";
(:: import schema at "../XSD/PropagarSCR_TIR.xsd" ::)

declare variable $propagarRequest as element() (:: schema-element(ns1:PropagarSCRyTIRRequest) ::) external;

declare function local:func($propagarRequest as element() (:: schema-element(ns1:PropagarSCRyTIRRequest) ::)) as element() (:: schema-element(ns2:PropagarSCR_TIRInput) ::) {
    <ns2:PropagarSCR_TIRInput>
        <ns2:idOperacion>{fn:data($propagarRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:PropagarSCR_TIRInput>
};

local:func($propagarRequest)
