xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://service.org.bcie.www/";
(:: import schema at "../XSD/CrearAvisoCobroComision.xsd" ::)
declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)

declare variable $CrearAvisoRequest as element() (:: schema-element(ns1:CrearAvisoCobroComisionRequest) ::) external;

declare function local:func($CrearAvisoRequest as element() (:: schema-element(ns1:CrearAvisoCobroComisionRequest) ::)) as element() (:: schema-element(ns2:CrearAvisoCobro) ::) {
    <ns2:CrearAvisoCobro>
        <idOperacion>{fn:data($CrearAvisoRequest/ns1:idOperacion)}</idOperacion>
        <idComision>{fn:data($CrearAvisoRequest/ns1:idComision)}</idComision>
    </ns2:CrearAvisoCobro>
};

local:func($CrearAvisoRequest)
