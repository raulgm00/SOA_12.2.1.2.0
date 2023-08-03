xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare variable $ConsultarVotacionUsuarioResponse as element() (:: schema-element(ns1:ConsultarVotacionUsuarioResponse) ::) external;

declare function local:func($ConsultarVotacionUsuarioResponse as element() (:: schema-element(ns1:ConsultarVotacionUsuarioResponse) ::)) as element() (:: schema-element(ns2:ConsultarOperacionByIdOperacionRequest) ::) {
    <ns2:ConsultarOperacionByIdOperacionRequest>
        <ns2:idOperacion>{fn:data($ConsultarVotacionUsuarioResponse/ns1:ListaVotacionUsuario/sol:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarOperacionByIdOperacionRequest>
};

local:func($ConsultarVotacionUsuarioResponse)
