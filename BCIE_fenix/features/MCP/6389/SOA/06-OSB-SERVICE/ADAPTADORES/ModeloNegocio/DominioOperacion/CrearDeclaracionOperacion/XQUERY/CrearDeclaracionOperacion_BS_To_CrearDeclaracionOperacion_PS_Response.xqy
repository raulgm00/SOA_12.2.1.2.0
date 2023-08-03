xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearDeclaracionOperacion";
(:: import schema at "../XSD/CrearDeclaracionOperacion_table.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $TreCrearDeclaracionOperacionCollection as element() (:: schema-element(ns2:TreDeclaracionOperacionCollection) ::) external;

declare function local:func($TreCrearDeclaracionOperacionCollection as element() (:: schema-element(ns2:TreDeclaracionOperacionCollection) ::)) as element() (:: schema-element(ns1:CrearDeclaracionOperacionResponse) ::) {
    <ns1:CrearDeclaracionOperacionResponse>
        <ns1:id>{fn:data($TreCrearDeclaracionOperacionCollection/ns2:TreDeclaracionOperacion/ns2:id)}</ns1:id>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Creación exitosa</res:message>
          
        </ns1:Resultado>
    </ns1:CrearDeclaracionOperacionResponse>
};

local:func($TreCrearDeclaracionOperacionCollection)
