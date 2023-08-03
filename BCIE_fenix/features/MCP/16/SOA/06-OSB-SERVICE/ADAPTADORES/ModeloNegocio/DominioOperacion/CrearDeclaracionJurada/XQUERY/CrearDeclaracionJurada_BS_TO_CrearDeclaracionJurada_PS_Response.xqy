xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearDeclaracionJurada";
(:: import schema at "../XSD/CrearDeclaracionJurada_table.xsd" ::)

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $DeclaracionCollection as element() (:: schema-element(ns1:DeclaracionCollection) ::) external;

declare function local:func($DeclaracionCollection as element() (:: schema-element(ns1:DeclaracionCollection) ::)) as element() (:: schema-element(ns2:CrearDeclaracionJuradaResponse) ::) {
    <ns2:CrearDeclaracionJuradaResponse>
        <ns2:DeclaracionJurada>
            <dec:idDeclaracion>{fn:data($DeclaracionCollection/ns1:Declaracion/ns1:id)}</dec:idDeclaracion>
            
        </ns2:DeclaracionJurada>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Declaración Jurada creada exitosamente.</res:message>
            
        </ns2:Resultado>
    </ns2:CrearDeclaracionJuradaResponse>
};

local:func($DeclaracionCollection)
