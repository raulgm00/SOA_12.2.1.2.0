xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarGestionCobro";
(:: import schema at "../XSD/ConsultarGestionCobro.xsd" ::)

declare variable $ConsultarGestionCobroRequest as element() (:: schema-element(ns1:ConsultarGestionCobroRequest) ::) external;

declare function local:func($ConsultarGestionCobroRequest as element() (:: schema-element(ns1:ConsultarGestionCobroRequest) ::)) as element() (:: schema-element(ns2:ConsultarGestionCobroInput) ::) {
    <ns2:ConsultarGestionCobroInput>
        <ns2:idEjecucion>{fn:data($ConsultarGestionCobroRequest/ns1:idEjecucion)}</ns2:idEjecucion>
        <ns2:estado>{fn:data($ConsultarGestionCobroRequest/ns1:Estado)}</ns2:estado>
    </ns2:ConsultarGestionCobroInput>
};

local:func($ConsultarGestionCobroRequest)
