xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearOperacionProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearOperacionProceso/V1/Schema/CrearOperacionProcesoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $idOperacionProceso as xs:long external;
declare variable $tipoAccion as xs:string external;

declare function local:func($idOperacionProceso as xs:long, 
                            $tipoAccion as xs:string) 
                            as element() (:: schema-element(ns1:responseCrearOperacionProcesoMessage) ::) {
    <ns1:responseCrearOperacionProcesoMessage>
        <ns1:idOperacionProceso>{fn:data($idOperacionProceso)}</ns1:idOperacionProceso>
        <ns1:Result>
            <res:result>OK</res:result>
            <res:message>{
            if (fn:data($tipoAccion) = 'INSERT')
            then (fn:concat('La inserción se ha realizado correctamente, operacion proceso ID:', xs:string($idOperacionProceso)))
            else(fn:concat('La actualización se ha realizado correctamente, operacion proceso ID:', xs:string($idOperacionProceso)))}</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Result>
    </ns1:responseCrearOperacionProcesoMessage>
};

local:func($idOperacionProceso, $tipoAccion)
