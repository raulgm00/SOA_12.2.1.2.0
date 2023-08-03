xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://service.org.bcie.www/";
(:: import schema at "../XSD/CrearAvisoCobroComision.xsd" ::)
declare namespace ns2="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)

declare variable $CrearAvisoResponse as element() (:: schema-element(ns1:CrearAvisoCobroResponse) ::) external;

declare function local:func($CrearAvisoResponse as element() (:: schema-element(ns1:CrearAvisoCobroResponse) ::)) as element() (:: schema-element(ns2:CrearAvisoCobroComisionResponse) ::) {
    <ns2:CrearAvisoCobroComisionResponse>
       <ns2:documentoCobroComision>{fn:data($CrearAvisoResponse/return)}</ns2:documentoCobroComision>
    </ns2:CrearAvisoCobroComisionResponse>
};

local:func($CrearAvisoResponse)
