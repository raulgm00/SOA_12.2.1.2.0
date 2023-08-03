xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $responseMapeoErrorMessage as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::) external;
declare variable $message as xs:string external;
declare function local:func($responseMapeoErrorMessage as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::), $message as xs:string) as element() (:: schema-element(ns2:GenerarAvisoCobroResponse) ::) {
    <ns2:GenerarAvisoCobroResponse>
        <ns2:Resultado>

            <res:result>{
              if ($responseMapeoErrorMessage/ns1:Result/res:result)
                then 
            fn:data($responseMapeoErrorMessage/ns1:Result/res:result)
            else
            'ERROR'
            }</res:result>
            <res:message>{
            if ($message)
                then 
            fn:data($message)
             else
            'Se ha producido un error en el flujo de OSB en la generacion de avisos de cobro'
            }</res:message>

            {
                if ($responseMapeoErrorMessage/ns1:Result/res:error)
                then 
                    <res:error>
                        <err:errorCode>{fn:data($responseMapeoErrorMessage/ns1:Result/res:error/err:errorCode)}</err:errorCode>
                        <err:errorDescription>{fn:data($responseMapeoErrorMessage/ns1:Result/res:error/err:errorDescription)}</err:errorDescription>
                        <err:errorType>{fn:data($responseMapeoErrorMessage/ns1:Result/res:error/err:errorType)}</err:errorType>
                    </res:error>
                else ()
            }
        </ns2:Resultado>
    </ns2:GenerarAvisoCobroResponse>
};

local:func($responseMapeoErrorMessage,$message )
