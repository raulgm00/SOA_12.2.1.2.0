xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://org/bcie/ws/middle/LAACTIVOS.wsdl";
(:: import schema at "../../XSD/LAACTIVOS_Schema.xsd" ::)
declare namespace ns2="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $NuevaDeclaracionResponse as element() (:: schema-element(ns1:NuevaDeclaracionResponse) ::) external;

declare function local:func($NuevaDeclaracionResponse as element() (:: schema-element(ns1:NuevaDeclaracionResponse) ::)) as element() (:: schema-element(ns2:CrearDeclaracionJuradaResponse) ::) {
    <ns2:CrearDeclaracionJuradaResponse>
        <ns2:DeclaracionJurada>
            <dec:codigoExternoDeclaracion>{fn:data($NuevaDeclaracionResponse/ns1:codigoDeclaracion)}</dec:codigoExternoDeclaracion>
            <dec:idOperacion>{fn:data($NuevaDeclaracionResponse/ns1:codigoIntervencion)}</dec:idOperacion>
            
        </ns2:DeclaracionJurada>
        <ns2:Resultado>
            {
                if (fn:data($NuevaDeclaracionResponse/ns1:codigoDeclaracion)!=0) then
                    <res:result>OK</res:result>
                else
                    <res:result>ERROR</res:result>
            }
            <res:message>{fn:concat(fn:data($NuevaDeclaracionResponse/ns1:codigoResultado),':' ,$NuevaDeclaracionResponse/ns1:tipoResultado,' -  ' ,$NuevaDeclaracionResponse/ns1:mensaje)}</res:message>
                   </ns2:Resultado>
    </ns2:CrearDeclaracionJuradaResponse>
};

local:func($NuevaDeclaracionResponse)
