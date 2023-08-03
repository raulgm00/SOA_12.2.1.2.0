xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ValidarEstadoFlexcubeMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarEstadoFlexcube/V1/Schema/ValidarEstadoFlexcubeMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $result as xs:string external;
declare variable $message as xs:string external;

declare function local:func($result as xs:string, 
                            $message as xs:string) 
                            as element() (:: schema-element(ns1:ValidarEstadoFlexcubeResponse) ::) {
    <ns1:ValidarEstadoFlexcubeResponse>
        <ns1:Resultado>
            <res:result>{fn:data($result)}</res:result>
            <res:message>{fn:data($message)}</res:message>
        </ns1:Resultado>
    </ns1:ValidarEstadoFlexcubeResponse>
};

local:func($result, $message)
