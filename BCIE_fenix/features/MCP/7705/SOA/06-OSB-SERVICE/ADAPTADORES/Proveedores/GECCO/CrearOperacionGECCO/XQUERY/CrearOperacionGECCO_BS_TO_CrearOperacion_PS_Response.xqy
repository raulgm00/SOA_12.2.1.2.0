xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $idOperacion as xs:long external;
declare variable $mensaje as xs:string external;

declare function local:func($idOperacion as xs:long, 
                            $mensaje as xs:string) 
                            as element() (:: schema-element(ns1:CrearOperacionResponse) ::) {
    <ns1:CrearOperacionResponse>
        <ns1:Operacion>
            <ope:idOperacion>{fn:data($idOperacion)}</ope:idOperacion>
        </ns1:Operacion>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:data($mensaje)}</res:message>
        </ns1:Resultado>
    </ns1:CrearOperacionResponse>
};

local:func($idOperacion, $mensaje)
