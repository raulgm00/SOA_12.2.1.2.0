xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $codigoContrato as xs:string external;
declare variable $plantillaLimite as xs:string external;
declare variable $serialLimite as xs:string external;
declare variable $codigoResultado as xs:string external;
declare variable $tipoResultado as xs:string external;
declare variable $mensaje as xs:string external;

declare function local:func($codigoContrato as xs:string, 
                            $plantillaLimite as xs:string, 
                            $serialLimite as xs:string, 
                            $codigoResultado as xs:string, 
                            $tipoResultado as xs:string, 
                            $mensaje as xs:string) 
                            as element() (:: schema-element(ns1:CrearCommitmentFLEXCUBEResponse) ::) {
    <ns1:CrearCommitmentFLEXCUBEResponse>
        <ns1:codigoContrato>{fn:data($codigoContrato)}</ns1:codigoContrato>
        <ns1:plantillaLimite>{fn:data($plantillaLimite)}</ns1:plantillaLimite>
        <ns1:serialLimite>{fn:data($serialLimite)}</ns1:serialLimite>
        <ns1:Resultado>
         {
                if (fn:data($codigoResultado)= '0') then
            <res:result>OK</res:result>
             else  <res:result>ERROR</res:result>
             }
            <res:message>{fn:data($mensaje)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($codigoResultado)}</err:errorCode>
                <err:errorDescription>{fn:data($tipoResultado)}</err:errorDescription>
            </res:error>
        </ns1:Resultado>
    </ns1:CrearCommitmentFLEXCUBEResponse>
};

local:func($codigoContrato, $plantillaLimite, $serialLimite, $codigoResultado, $tipoResultado, $mensaje)
