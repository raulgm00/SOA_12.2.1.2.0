xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns3="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)

declare namespace ns1="http://www.w3.org/2005/xquery-local-functions";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $MessageDescription as xs:string external;
declare variable $responseMapeoErrorMessage as element() (:: schema-element(ns2:responseMapeoErrorMessage) ::) external;

declare function ns1:func($MessageDescription as xs:string, 
                          $responseMapeoErrorMessage as element() (:: schema-element(ns2:responseMapeoErrorMessage) ::)) 
                          as element() (:: schema-element(ns3:CalcularPlazoCondicionesResponse) ::) {
    <ns3:CalcularPlazoCondicionesResponse>
        <ns3:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($MessageDescription)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($responseMapeoErrorMessage/ns2:ErrorOutput/err:errorCode)}</err:errorCode>
                <err:errorDescription>{fn:data($responseMapeoErrorMessage/ns2:ErrorOutput/err:errorDescription)}</err:errorDescription>
                <err:errorType>{fn:data($responseMapeoErrorMessage/ns2:ErrorOutput/err:errorType)}</err:errorType>
            </res:error>
        </ns3:Resultado>
    </ns3:CalcularPlazoCondicionesResponse>
};

ns1:func($MessageDescription, $responseMapeoErrorMessage)
