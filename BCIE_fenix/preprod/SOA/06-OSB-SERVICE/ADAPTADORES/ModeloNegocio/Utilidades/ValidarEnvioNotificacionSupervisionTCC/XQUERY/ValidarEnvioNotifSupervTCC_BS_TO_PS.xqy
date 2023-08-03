xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarEnvioNotifSupervTCC";
(:: import schema at "../XSD/ValidarEnvioNotifSupervTCC.xsd" ::)

declare variable $ValidarEnvioNotifSupervTCCResponse as element() (:: schema-element(ns1:ValidarEnvioNotifSupervTCCResponse) ::) external;

declare function local:func($ValidarEnvioNotifSupervTCCResponse as element() (:: schema-element(ns1:ValidarEnvioNotifSupervTCCResponse) ::)) as element() (:: schema-element(ns2:ValidarEnvioNotifSupervTCCResponse) ::) {
    <ns2:ValidarEnvioNotifSupervTCCResponse>
        <ns2:ESTADO>{fn:data($ValidarEnvioNotifSupervTCCResponse/ns1:P_ESTADO)}</ns2:ESTADO>
        <ns2:CODIGO>{fn:data($ValidarEnvioNotifSupervTCCResponse/ns1:P_CODIGO)}</ns2:CODIGO>
        <ns2:MENSAJE>{fn:data($ValidarEnvioNotifSupervTCCResponse/ns1:P_MENSAJE)}</ns2:MENSAJE>
    </ns2:ValidarEnvioNotifSupervTCCResponse>
};

local:func($ValidarEnvioNotifSupervTCCResponse)
