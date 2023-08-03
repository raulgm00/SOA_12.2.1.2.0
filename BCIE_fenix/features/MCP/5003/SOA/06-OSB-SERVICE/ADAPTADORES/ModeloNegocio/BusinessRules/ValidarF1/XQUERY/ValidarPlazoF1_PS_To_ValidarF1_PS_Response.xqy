xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ValidarPlazoF1Response as element() (:: schema-element(ns1:ValidarPlazoF1Response) ::) external;

declare function local:func($ValidarPlazoF1Response as element() (:: schema-element(ns1:ValidarPlazoF1Response) ::)) as element() (:: schema-element(ns1:ValidarF1Response) ::) {
    <ns1:ValidarF1Response>
        <ns1:Regla>
          {
            $ValidarPlazoF1Response/ns1:Regla/*
          }
        </ns1:Regla>
        <ns1:Resultado>
            <res:result>{fn:data($ValidarPlazoF1Response/ns1:Resultado/res:result)}</res:result>
            <res:message>{fn:data($ValidarPlazoF1Response/ns1:Resultado/res:message)}</res:message>
            {
                if ($ValidarPlazoF1Response/ns1:Resultado/res:error)
                then 
                    <res:error>
                        <err:errorCode>{fn:data($ValidarPlazoF1Response/ns1:Resultado/res:error/err:errorCode)}</err:errorCode>
                        <err:errorDescription>{fn:data($ValidarPlazoF1Response/ns1:Resultado/res:error/err:errorDescription)}</err:errorDescription>
                        <err:errorType>{fn:data($ValidarPlazoF1Response/ns1:Resultado/res:error/err:errorType)}</err:errorType>
                    </res:error>
                else ()
            }
        </ns1:Resultado>
    </ns1:ValidarF1Response>
};

local:func($ValidarPlazoF1Response)
