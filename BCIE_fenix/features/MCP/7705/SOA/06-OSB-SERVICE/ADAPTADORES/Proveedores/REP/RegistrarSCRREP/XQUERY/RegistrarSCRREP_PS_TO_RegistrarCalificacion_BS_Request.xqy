xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/registrarCalificacion";
(:: import schema at "../XSD/registrarCalificacion_sp.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $RegistrarSCRREPRequest as element() (:: schema-element(ns1:RegistrarSCRREPRequest) ::) external;

declare function local:func($RegistrarSCRREPRequest as element() (:: schema-element(ns1:RegistrarSCRREPRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
            <ns2:BHQ>{fn:data($RegistrarSCRREPRequest/cli:idFacturador)}</ns2:BHQ>
            <ns2:SCR>{fn:data($RegistrarSCRREPRequest/cli:scr)}</ns2:SCR>
            <ns2:ESTADOSCR>{fn:data($RegistrarSCRREPRequest/cli:estadoScr)}</ns2:ESTADOSCR>
            <ns2:OBSERVACION>{fn:data($RegistrarSCRREPRequest/cli:observacion)}</ns2:OBSERVACION>
            <ns2:FECHA>{fn:current-date()}</ns2:FECHA>
            <ns2:USUARIOMODIFICO>{fn:data($RegistrarSCRREPRequest/cli:usuarioModifico)}</ns2:USUARIOMODIFICO>
            <ns2:USUARIOVALIDO>{fn:data($RegistrarSCRREPRequest/cli:usuarioValido)}</ns2:USUARIOVALIDO>
    </ns2:InputParameters>
};

local:func($RegistrarSCRREPRequest)