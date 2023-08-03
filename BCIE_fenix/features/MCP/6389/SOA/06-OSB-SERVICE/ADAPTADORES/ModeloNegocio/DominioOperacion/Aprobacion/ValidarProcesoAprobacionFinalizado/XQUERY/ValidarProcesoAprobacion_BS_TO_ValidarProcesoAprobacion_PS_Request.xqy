xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ValidarProcesoAprobacionFinalizado";
(:: import schema at "../XSD/ValidarProcesoAprobacionFinalizado.xsd" ::)

declare variable $ValidarProcesoAprobacionFinalizadoRequest as element() (:: schema-element(ns1:ValidarProcesoAprobacionFinalizadoRequest) ::) external;

declare function local:func($ValidarProcesoAprobacionFinalizadoRequest as element() (:: schema-element(ns1:ValidarProcesoAprobacionFinalizadoRequest) ::)) as element() (:: schema-element(ns2:ValidarProcesoAprobacionFinalizadoInput) ::) {
    <ns2:ValidarProcesoAprobacionFinalizadoInput>
        <ns2:P_OPERACION>{fn:data($ValidarProcesoAprobacionFinalizadoRequest/ns1:idOperacion)}</ns2:P_OPERACION>
    </ns2:ValidarProcesoAprobacionFinalizadoInput>
};

local:func($ValidarProcesoAprobacionFinalizadoRequest)
