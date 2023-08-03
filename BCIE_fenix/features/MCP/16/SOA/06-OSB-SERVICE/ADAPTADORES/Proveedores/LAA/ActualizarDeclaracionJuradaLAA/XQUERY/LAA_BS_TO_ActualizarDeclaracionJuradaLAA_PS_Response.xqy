xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://org/bcie/ws/middle/LAACTIVOS.wsdl";
(:: import schema at "../../XSD/LAACTIVOS_Schema.xsd" ::)
declare namespace ns2="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ActualizarDeclaracionResponse as element() (:: schema-element(ns1:ActualizarDeclaracionResponse) ::) external;

declare function local:func($ActualizarDeclaracionResponse as element() (:: schema-element(ns1:ActualizarDeclaracionResponse) ::)) as element() (:: schema-element(ns2:ActualizarDeclaracionJuradaResponse) ::) {
    <ns2:ActualizarDeclaracionJuradaResponse>
        <ns2:Operacion>
            <ope:idOperacion>{fn:data($ActualizarDeclaracionResponse/ns1:codigoIntervencion)}</ope:idOperacion>
            
        </ns2:Operacion>
        <ns2:DeclaracionJurada>
            <dec:codigoExternoDeclaracion>{fn:data($ActualizarDeclaracionResponse/ns1:codigoDeclaracion)}</dec:codigoExternoDeclaracion>
         
        </ns2:DeclaracionJurada>
        <ns2:Resultado>
             {
                if (fn:data($ActualizarDeclaracionResponse/ns1:codigoResultado)!=1) then
                    <res:result>OK</res:result>
                else
                    <res:result>ERROR</res:result>
            }
            <res:message>{fn:concat($ActualizarDeclaracionResponse/ns1:codigoResultado,' - ',$ActualizarDeclaracionResponse/ns1:tipoResultado,' - ', fn:data($ActualizarDeclaracionResponse/ns1:mensaje))}</res:message>
           
        </ns2:Resultado>
    </ns2:ActualizarDeclaracionJuradaResponse>
};

local:func($ActualizarDeclaracionResponse)