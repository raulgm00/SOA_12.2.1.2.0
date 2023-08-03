xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $PropagarSCRyTIRRequest as element() (:: schema-element(ns1:PropagarSCRyTIRRequest) ::) external;

declare function local:func($PropagarSCRyTIRRequest as element() (:: schema-element(ns1:PropagarSCRyTIRRequest) ::)) as element() (:: schema-element(ns1:PropagarSCRyTIRResponse) ::) {
    <ns1:PropagarSCRyTIRResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Soy un dummy y soy feliz como una lombriz</res:message>
        </ns1:Resultado>
    </ns1:PropagarSCRyTIRResponse>
};

local:func($PropagarSCRyTIRRequest)
