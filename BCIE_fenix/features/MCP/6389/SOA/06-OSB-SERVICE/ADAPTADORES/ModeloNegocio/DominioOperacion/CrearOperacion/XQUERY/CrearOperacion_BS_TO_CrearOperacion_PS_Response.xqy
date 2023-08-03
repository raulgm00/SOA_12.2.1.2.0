xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearOperacionBD";
(:: import schema at "../XSD/CrearOperacionBD_table.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $CrearOperacionResponse as element() (:: schema-element(ns1:OperacionCollection) ::) external;

declare function local:func($CrearOperacionResponse as element() (:: schema-element(ns1:OperacionCollection) ::)) as element() (:: schema-element(ns2:CrearOperacionResponse) ::) {
    <ns2:CrearOperacionResponse>
        <ns2:Operacion>
            <ope:idOperacion>{fn:data($CrearOperacionResponse/ns1:Operacion/ns1:idOperacion)}</ope:idOperacion></ns2:Operacion>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Creación realizada correctamente</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:CrearOperacionResponse>
};

local:func($CrearOperacionResponse)