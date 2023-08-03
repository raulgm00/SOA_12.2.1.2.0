xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/ErrorBO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Common/V1/Schema/ErrorBO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare variable $Mensaje as xs:string external;
declare variable $Error as element() (:: element(*, ns1:Error) ::) external;

declare function local:func($Mensaje as xs:string, 
                            $Error as element() (:: element(*, ns1:Error) ::)) 
                            as element() (:: schema-element(ns2:ValidarEnvioNotifSupervTCCResponse) ::) {
    <ns2:ValidarEnvioNotifSupervTCCResponse>
        <ns2:Resultado>
            <res:result></res:result>
            <res:message>{fn:data($Mensaje)}</res:message>
            <res:error>
                <ns1:errorCode>{fn:data($Error/ns1:errorCode)}</ns1:errorCode>
                <ns1:errorDescription>{fn:data($Error/ns1:errorDescription)}</ns1:errorDescription>
                <ns1:errorType>{fn:data($Error/ns1:errorType)}</ns1:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ValidarEnvioNotifSupervTCCResponse>
};

local:func($Mensaje, $Error)
