xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $message as xs:string external;

declare function local:func($message as xs:string) as element() (:: schema-element(ns1:ActualizarComisionResponse) ::) {
    <ns1:ActualizarComisionResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Actualización Exitosa</res:message>
           
        </ns1:Resultado>
    </ns1:ActualizarComisionResponse>
};

local:func($message)