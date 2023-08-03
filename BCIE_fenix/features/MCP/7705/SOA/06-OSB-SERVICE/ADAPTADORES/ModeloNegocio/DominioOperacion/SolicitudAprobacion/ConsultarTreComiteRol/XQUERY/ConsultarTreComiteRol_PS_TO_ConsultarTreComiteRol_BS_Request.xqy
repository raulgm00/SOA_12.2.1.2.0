xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTreComiteRol";
(:: import schema at "../XSD/ConsultarTreComiteRol.xsd" ::)

declare variable $ConsultarTreComiteRolRequest as element() (:: schema-element(ns1:ConsultarTreComiteRolRequest) ::) external;

declare function local:func($ConsultarTreComiteRolRequest as element() (:: schema-element(ns1:ConsultarTreComiteRolRequest) ::)) as element() (:: schema-element(ns2:ConsultarTreComiteRolInput) ::) {
    <ns2:ConsultarTreComiteRolInput>
        <ns2:idNivelAprobacion>{fn:data($ConsultarTreComiteRolRequest/ns1:nivelAprobacion)}</ns2:idNivelAprobacion>
    </ns2:ConsultarTreComiteRolInput>
};

local:func($ConsultarTreComiteRolRequest)
