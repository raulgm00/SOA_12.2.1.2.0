xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacora/V1/Schema/CrearBitacoraMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $mensaje as xs:string external;
declare variable $result as xs:string external;
declare function local:func($mensaje as xs:string ,$result as xs:string ) as element() (:: schema-element(ns1:CrearBitacoraResponse) ::) {
    <ns1:CrearBitacoraResponse>
        <ns1:Resultado>
            <res:result>{$result}</res:result>
            <res:message>{$mensaje}</res:message>
        </ns1:Resultado>
    </ns1:CrearBitacoraResponse>
};

local:func($mensaje,$result)
