xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearMontoOperacion";
(:: import schema at "../XSD/CrearMontoOperacion_table.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $responseMontoOperacion as element() (:: schema-element(ns1:MontoOperacionCollection) ::) external;

declare function local:func($responseMontoOperacion as element() (:: schema-element(ns1:MontoOperacionCollection) ::)) as element() (:: schema-element(ns2:CrearMontoOperacionResponse) ::) {
    <ns2:CrearMontoOperacionResponse>
        <ns2:MontoOperacion>
            <ope:id>{fn:data($responseMontoOperacion/ns1:MontoOperacion/ns1:id)}</ope:id>
            {
                if ($responseMontoOperacion/ns1:MontoOperacion/ns1:idOperacion)
                then <ope:idOperacion>{fn:data($responseMontoOperacion/ns1:MontoOperacion/ns1:idOperacion)}</ope:idOperacion>
                else ()
            }
            {
                if ($responseMontoOperacion/ns1:MontoOperacion/ns1:idTcaTipoMonto)
                then <ope:IdTcaTipoMonto>{fn:data($responseMontoOperacion/ns1:MontoOperacion/ns1:idTcaTipoMonto)}</ope:IdTcaTipoMonto>
                else ()
            }
            {
                if ($responseMontoOperacion/ns1:MontoOperacion/ns1:monto)
                then <ope:monto>{fn:data($responseMontoOperacion/ns1:MontoOperacion/ns1:monto)}</ope:monto>
                else ()
            }</ns2:MontoOperacion>
        <ns2:Resultado>
            <res:result>Ok</res:result>
            <res:message>Creación realizada correctamente</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:CrearMontoOperacionResponse>
};

local:func($responseMontoOperacion)
