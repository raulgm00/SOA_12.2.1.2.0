xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $codigoResultado as xs:string external;
declare variable $tipoResultado as xs:string external;
declare variable $mensaje as xs:string external;

declare function local:func($codigoResultado as xs:string, 
                            $tipoResultado as xs:string, 
                            $mensaje as xs:string) 
                            as element() (:: schema-element(ns1:PropagarSCRyTIRResponse) ::) {
    <ns1:PropagarSCRyTIRResponse>
    {if($codigoResultado='0')
    then
        <ns1:Resultado>
           <res:result>OK</res:result>
            <res:message>Actualizacion realizada Exitosamente</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
        else 
         <ns1:Resultado>
            <res:result>ERROR</res:result>
        
            <res:message>{fn:concat($tipoResultado,' - ' , $mensaje)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($codigoResultado)}</err:errorCode>
                <err:errorDescription>{fn:data($mensaje)}</err:errorDescription>
                <err:errorType>{fn:data($tipoResultado)}</err:errorType>
            </res:error>
        </ns1:Resultado>
        }
    </ns1:PropagarSCRyTIRResponse>
};

local:func($codigoResultado, $tipoResultado, $mensaje)
