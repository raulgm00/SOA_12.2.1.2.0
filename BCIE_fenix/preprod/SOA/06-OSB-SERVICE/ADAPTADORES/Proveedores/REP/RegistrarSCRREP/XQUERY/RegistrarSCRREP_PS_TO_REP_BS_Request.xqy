xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace rep="http://org/bcie/ws/middle/REP.wsdl";

declare namespace ns2="http://org/bcie/ws/middle/REP.wsdl";
(:: import schema at "../../XSD/REP_CUSTOM.xsd" ::)
declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $RegistrarSCRREPRequest as element() (:: schema-element(ns1:RegistrarSCRREPRequest) ::) external;

declare function local:func($RegistrarSCRREPRequest as element() (:: schema-element(ns1:RegistrarSCRREPRequest) ::)) as element() (:: element(rep:registrarCalificacion) ::) {
    <rep:registrarCalificacion>
        <bhq>{fn:data($RegistrarSCRREPRequest/cli:idFacturador)}</bhq>
        <scr>{fn:data($RegistrarSCRREPRequest/cli:scr)}</scr>
        <estadoscr>{fn:data($RegistrarSCRREPRequest/cli:estadoScr)}</estadoscr>
        <observacion>{fn:data($RegistrarSCRREPRequest/cli:observacion)}</observacion>
        <fecha>{fn:current-date()}</fecha>
        <usuariomodifico>{fn:data($RegistrarSCRREPRequest/cli:usuarioModifico)}</usuariomodifico>
        <usuariovalido>{fn:data($RegistrarSCRREPRequest/cli:usuarioValido)}</usuariovalido>
    </rep:registrarCalificacion>
};

local:func($RegistrarSCRREPRequest)
