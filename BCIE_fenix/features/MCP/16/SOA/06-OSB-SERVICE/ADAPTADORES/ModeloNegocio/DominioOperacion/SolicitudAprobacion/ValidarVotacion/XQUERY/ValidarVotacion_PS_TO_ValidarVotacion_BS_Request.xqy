xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarVotacion";
(:: import schema at "../XSD/ValidarVotacion_sp.xsd" ::)

declare variable $ValidarVotacionRequest as element() (:: schema-element(ns1:ValidarVotacionRequest) ::) external;

declare function local:func($ValidarVotacionRequest as element() (:: schema-element(ns1:ValidarVotacionRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_USUARIO_REUNION>{fn:data($ValidarVotacionRequest/ns1:idUsuarioReunion)}</ns2:P_USUARIO_REUNION>
        <ns2:P_USUARIO>{fn:data($ValidarVotacionRequest/ns1:loginUsuario)}</ns2:P_USUARIO>
    </ns2:InputParameters>
};

local:func($ValidarVotacionRequest)
