xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ObtenerURLMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Utilidades/ObtenerURL/V1/Schema/ObtenerURLMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ObtenerURLResponse as element() (:: schema-element(ns1:ObtenerURLResponse) ::) external;

declare function local:func($ObtenerURLResponse as element() (:: schema-element(ns1:ObtenerURLResponse) ::)) as element() (:: schema-element(ns2:PlanArmotizacionResponse) ::) {
    <ns2:PlanArmotizacionResponse>
        <ns2:URL>{fn:data($ObtenerURLResponse/ns1:URL)}</ns2:URL>
        <ns2:Resultado>
            <res:result>{fn:data($ObtenerURLResponse/ns1:Resultado/res:result)}</res:result>
            <res:message>{fn:data($ObtenerURLResponse/ns1:Resultado/res:message)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($ObtenerURLResponse/ns1:Resultado/res:error/err:errorCode)}</err:errorCode>
                <err:errorDescription>{fn:data($ObtenerURLResponse/ns1:Resultado/res:error/err:errorDescription)}</err:errorDescription>
                <err:errorType>{fn:data($ObtenerURLResponse/ns1:Resultado/res:error/err:errorType)}</err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:PlanArmotizacionResponse>
};

local:func($ObtenerURLResponse)
