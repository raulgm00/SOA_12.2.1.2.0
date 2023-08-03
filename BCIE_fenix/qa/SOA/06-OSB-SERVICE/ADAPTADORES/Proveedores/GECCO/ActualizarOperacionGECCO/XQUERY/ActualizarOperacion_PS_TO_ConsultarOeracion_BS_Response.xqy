xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://org/bcie/ws/middle/GECCO.wsdl";
(:: import schema at "../../XSD/GECCO_CUSTOM.xsd" ::)
declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ActualizaOperacion as element() (:: schema-element(ns1:ActualizarOperacion) ::) external;

declare function local:func($ActualizaOperacion as element() (:: schema-element(ns1:ActualizarOperacion) ::)) as element() (:: schema-element(ns2:ActualizarOperacionResponse) ::) {
    <ns2:ActualizarOperacionResponse>
        <ns2:Operacion>
            <ope:idOperacion> </ope:idOperacion></ns2:Operacion>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Realizada Correctamente</res:message>
        </ns2:Resultado></ns2:ActualizarOperacionResponse>
};

local:func($ActualizaOperacion)
