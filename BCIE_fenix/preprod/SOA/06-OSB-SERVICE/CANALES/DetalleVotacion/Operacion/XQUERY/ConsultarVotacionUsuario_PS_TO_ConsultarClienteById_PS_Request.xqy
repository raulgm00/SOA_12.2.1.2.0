xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare variable $ConsultarVotacionUsuarioResponse as element() (:: schema-element(ns1:ConsultarVotacionUsuarioResponse) ::) external;

declare function local:func($ConsultarVotacionUsuarioResponse as element() (:: schema-element(ns1:ConsultarVotacionUsuarioResponse) ::)) as element() (:: schema-element(ns2:ConsultarClienteByIdClienteRequest) ::) {
    <ns2:ConsultarClienteByIdClienteRequest>
        <ns2:idCliente>{fn:data($ConsultarVotacionUsuarioResponse/ns1:ListaVotacionUsuario/sol:idCliente)}</ns2:idCliente>
    </ns2:ConsultarClienteByIdClienteRequest>
};

local:func($ConsultarVotacionUsuarioResponse)
