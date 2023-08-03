xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDetalleAvisoCobro";
(:: import schema at "../XSD/ConsultarDetalleAvisoCobro.xsd" ::)

declare variable $ConsultarDetalleAvisoCobroRequest as element() (:: schema-element(ns1:ConsultarDetalleAvisoCobroRequest) ::) external;

declare function local:func($ConsultarDetalleAvisoCobroRequest as element() (:: schema-element(ns1:ConsultarDetalleAvisoCobroRequest) ::)) as element() (:: schema-element(ns2:ConsultarDetalleAvisoCobroInput) ::) {
    <ns2:ConsultarDetalleAvisoCobroInput>
        <ns2:idEjecucion>{fn:data($ConsultarDetalleAvisoCobroRequest/ns1:idEjecucion)}</ns2:idEjecucion>
        <ns2:responsable>{fn:data($ConsultarDetalleAvisoCobroRequest/ns1:Responsable)}</ns2:responsable>
    </ns2:ConsultarDetalleAvisoCobroInput>
};

local:func($ConsultarDetalleAvisoCobroRequest)
