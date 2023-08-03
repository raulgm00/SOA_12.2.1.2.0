xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)
declare namespace ns3="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace ns1="http://www.w3.org/2005/xquery-local-functions";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $responseMapeoErrorMessage as element() (:: schema-element(ns2:responseMapeoErrorMessage) ::) external;
declare variable $Mensaje as xs:string external;

declare function ns1:func($responseMapeoErrorMessage as element() (:: schema-element(ns2:responseMapeoErrorMessage) ::), 
                          $Mensaje as xs:string) 
                          as element() (:: schema-element(ns3:ConsultarAccionesResponse) ::) {
    <ns3:ConsultarAccionesResponse>
        <ns3:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($Mensaje)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($responseMapeoErrorMessage/ns2:ErrorOutput/err:errorCode)}</err:errorCode>
                <err:errorDescription>{fn:data($responseMapeoErrorMessage/ns2:ErrorOutput/err:errorDescription)}</err:errorDescription>
                <err:errorType>{fn:data($responseMapeoErrorMessage/ns2:ErrorOutput/err:errorType)}</err:errorType>
            </res:error>
        </ns3:Resultado>
    </ns3:ConsultarAccionesResponse>
};

ns1:func($responseMapeoErrorMessage, $Mensaje)
