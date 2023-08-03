xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/EliminarAprobacion";
(:: import schema at "../XSD/EliminarAprobacion_table.xsd" ::)

declare variable $EliminarAprobacionRequest as element() (:: schema-element(ns1:EliminarAprobacionRequest) ::) external;

declare function local:func($EliminarAprobacionRequest as element() (:: schema-element(ns1:EliminarAprobacionRequest) ::)) as element() (:: schema-element(ns2:AprobacionCollection) ::) {
    <ns2:AprobacionCollection>
        <ns2:Aprobacion>
            <ns2:id>{fn:data($EliminarAprobacionRequest/ns1:idAprobacion)}</ns2:id>
        </ns2:Aprobacion>
    </ns2:AprobacionCollection>
};

local:func($EliminarAprobacionRequest)
