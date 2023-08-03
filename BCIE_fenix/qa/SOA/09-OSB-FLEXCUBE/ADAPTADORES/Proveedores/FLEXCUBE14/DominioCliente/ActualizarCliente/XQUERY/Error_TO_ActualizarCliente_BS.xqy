xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/FLEXCUBE14/ClienteMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Flexcube14/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace res = "http://www.bcie.org/FLEXCUBE14/ResultBO";

declare namespace err = "http://www.bcie.org/FLEXCUBE14/ErrorBO";

declare variable $Codigo_Error as xs:string external;
declare variable $Mensaje_Error as xs:string external;

declare function local:func($Codigo_Error as xs:string, 
                          $Mensaje_Error as xs:string) 
                          as element() (:: schema-element(ns1:CreaClienteResponse) ::) {
    <ns1:CreaClienteResponse>
        <ns1:Response>
            <ns1:Resultado>
                <res:result>ERROR</res:result>
                <res:message>{fn:data($Mensaje_Error)}</res:message>
                <res:error>
                    <err:errorCode>{fn:data($Codigo_Error)}</err:errorCode>
                    <err:errorDescription>{fn:data($Mensaje_Error)}</err:errorDescription>
                    <err:errorType></err:errorType>
                </res:error>
            </ns1:Resultado>
        </ns1:Response>
    </ns1:CreaClienteResponse>
};

local:func($Codigo_Error, $Mensaje_Error)