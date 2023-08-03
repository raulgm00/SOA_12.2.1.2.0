xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarEnvioNotifSupervTCC";
(:: import schema at "../XSD/ValidarEnvioNotifSupervTCC.xsd" ::)

declare variable $ValidarEnvioNotifSupervTCCRequest as element() (:: element(*, ns1:ValidarEnvioNotifSupervTCCRequestType) ::) external;

declare function local:func($ValidarEnvioNotifSupervTCCRequest as element() (:: element(*, ns1:ValidarEnvioNotifSupervTCCRequestType) ::)) as element() (:: schema-element(ns2:ValidarEnvioNotifSupervTCCRequest) ::) {
    <ns2:ValidarEnvioNotifSupervTCCRequest>
        <ns2:P_ID_SUPERVISION>{fn:data($ValidarEnvioNotifSupervTCCRequest/ns1:ID_SUPERVISION)}</ns2:P_ID_SUPERVISION>
        <ns2:P_DESC_PLANTILLA>{fn:data($ValidarEnvioNotifSupervTCCRequest/ns1:DESC_PLANTILLA)}</ns2:P_DESC_PLANTILLA>
        <ns2:P_ID_OPERACION>{fn:data($ValidarEnvioNotifSupervTCCRequest/ns1:ID_OPERACION)}</ns2:P_ID_OPERACION>
    </ns2:ValidarEnvioNotifSupervTCCRequest>
};

local:func($ValidarEnvioNotifSupervTCCRequest)
