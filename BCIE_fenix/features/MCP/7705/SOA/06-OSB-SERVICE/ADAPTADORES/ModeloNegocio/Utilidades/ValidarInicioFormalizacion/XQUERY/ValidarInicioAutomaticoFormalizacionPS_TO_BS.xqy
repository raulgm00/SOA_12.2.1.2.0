xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ValidarInicioAutomaticoFormalizacion";
(:: import schema at "../XSD/ValidarInicioAutomaticoFormalizacion.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $ValidarInicioAutomaticoFormalizacionRequest as element() (:: schema-element(ns1:ValidarInicioFormalizacionRequest) ::) external;

declare function local:func($ValidarInicioAutomaticoFormalizacionRequest as element() (:: schema-element(ns1:ValidarInicioFormalizacionRequest) ::)) as element() (:: schema-element(ns2:ValidarInicioAutomaticoFormalizacionInput) ::) {
    <ns2:ValidarInicioAutomaticoFormalizacionInput>
        <ns2:idOperacion>{fn:data($ValidarInicioAutomaticoFormalizacionRequest/ns1:Operacion/ope:idOperacion)}</ns2:idOperacion>
    </ns2:ValidarInicioAutomaticoFormalizacionInput>
};

local:func($ValidarInicioAutomaticoFormalizacionRequest)
