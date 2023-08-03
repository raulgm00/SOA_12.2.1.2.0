xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $idObservacionCondicion as xs:int external;
declare variable $tipoAccion as xs:string external;

declare function local:func($idObservacionCondicion as xs:int,$tipoAccion as xs:string)as element() (:: schema-element(ns1:CrearObservacionCondicionResponse) ::) {
    <ns1:CrearObservacionCondicionResponse>
        <ns1:idObservacion>{fn:data($idObservacionCondicion)}</ns1:idObservacion>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{
            if (fn:data($tipoAccion) = 'INSERT') 
              then fn:concat('La inserción se ha realizado correctamente, observacionCondicion ID:', xs:string($idObservacionCondicion))
              else (fn:concat('La actualización se ha realizado correctamente, observacionCondicion ID:', xs:string($idObservacionCondicion)))}</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:CrearObservacionCondicionResponse>
};

local:func($idObservacionCondicion,$tipoAccion)
