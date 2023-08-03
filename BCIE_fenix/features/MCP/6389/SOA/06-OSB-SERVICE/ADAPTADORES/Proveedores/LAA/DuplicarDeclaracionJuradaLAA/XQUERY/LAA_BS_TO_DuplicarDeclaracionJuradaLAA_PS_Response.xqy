xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://org/bcie/ws/middle/LAACTIVOS.wsdl";
(:: import schema at "../../XSD/LAACTIVOS_Schema.xsd" ::)
declare namespace ns1="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $DuplicarDeclaracionResponse as element() (:: schema-element(ns2:DuplicarDeclaracionResponse) ::) external;

declare function local:func($DuplicarDeclaracionResponse as element() (:: schema-element(ns2:DuplicarDeclaracionResponse) ::)) as element() (:: schema-element(ns1:DuplicarDeclaracionJuradaResponse) ::) {
    <ns1:DuplicarDeclaracionJuradaResponse>
        <ns1:codigoExternoDeclaracion>{fn:data($DuplicarDeclaracionResponse/ns2:codigoDeclaracion)}</ns1:codigoExternoDeclaracion>
        <ns1:Resultado>
            {
                if (fn:data($DuplicarDeclaracionResponse/ns2:codigoDeclaracion)!=0) then
                    <res:result>OK</res:result>
                else
                    <res:result>ERROR</res:result>
            }
            <res:message>{fn:concat(fn:data($DuplicarDeclaracionResponse/ns2:codigoResultado),':' ,$DuplicarDeclaracionResponse/ns2:tipoResultado,' -  ' ,$DuplicarDeclaracionResponse/ns2:mensaje)}</res:message>
                   </ns1:Resultado>
    </ns1:DuplicarDeclaracionJuradaResponse>
};

local:func($DuplicarDeclaracionResponse)
