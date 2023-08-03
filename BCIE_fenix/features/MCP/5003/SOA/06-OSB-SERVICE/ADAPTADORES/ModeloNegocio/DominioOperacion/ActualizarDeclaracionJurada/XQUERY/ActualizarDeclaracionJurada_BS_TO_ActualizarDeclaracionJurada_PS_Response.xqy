xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarDeclaracionJurada";
(:: import schema at "../XSD/ActualizarDeclaracionJurada_table.xsd" ::)

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $DeclaracionCollection as element() (:: schema-element(ns2:DeclaracionCollection) ::) external;

declare function local:func($DeclaracionCollection as element() (:: schema-element(ns2:DeclaracionCollection) ::)) as element() (:: schema-element(ns1:ActualizarDeclaracionJuradaResponse) ::) {
    <ns1:ActualizarDeclaracionJuradaResponse>
        <ns1:DeclaracionJurada>
            <dec:idDeclaracion>{fn:data($DeclaracionCollection/ns2:Declaracion/ns2:id)}</dec:idDeclaracion>
        </ns1:DeclaracionJurada>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Actualización Exitosa</res:message>
        </ns1:Resultado>
    </ns1:ActualizarDeclaracionJuradaResponse>
};

local:func($DeclaracionCollection)
