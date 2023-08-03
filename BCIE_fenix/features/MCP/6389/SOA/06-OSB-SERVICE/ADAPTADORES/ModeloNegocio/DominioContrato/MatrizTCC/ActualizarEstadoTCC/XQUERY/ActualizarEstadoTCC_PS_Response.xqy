xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $message as xs:string external;

declare function local:func($message as xs:string) as element() (:: schema-element(ns2:ActualizarEstadoTCCResponse) ::) {
     <ns2:ActualizarEstadoTCCResponse>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Operación Exitosa</res:message>
      
        </ns2:Resultado>
    </ns2:ActualizarEstadoTCCResponse>
};

local:func($message)
