xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/EliminarMontoOperacion";
(:: import schema at "../XSD/EliminarMontoOperacion_table.xsd" ::)

declare variable $EliminarMontoOperacionRequest as element() (:: schema-element(ns1:EliminarMontoOperacionRequest) ::) external;

declare function local:func($EliminarMontoOperacionRequest as element() (:: schema-element(ns1:EliminarMontoOperacionRequest) ::)) as element() (:: schema-element(ns2:MontoOperacionCollection) ::) {
    <ns2:MontoOperacionCollection>
        <ns2:MontoOperacion>
            <ns2:id>{fn:data($EliminarMontoOperacionRequest/ns1:idMontoOperacion)}</ns2:id>
            <ns2:idOperacion></ns2:idOperacion>
            <ns2:idTcaTipoMonto></ns2:idTcaTipoMonto>
            <ns2:monto></ns2:monto>
        </ns2:MontoOperacion>
    </ns2:MontoOperacionCollection>
};

local:func($EliminarMontoOperacionRequest)
