xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearOperacionAsociadaBD";
(:: import schema at "../XSD/CrearOperacionAsociadaBD_table.xsd" ::)

declare variable $CrearOperacionAsociadaRequest as element() (:: schema-element(ns1:CrearOperacionAsociadaRequest) ::) external;

declare function local:func($CrearOperacionAsociadaRequest as element() (:: schema-element(ns1:CrearOperacionAsociadaRequest) ::)) as element() (:: schema-element(ns2:AsociadasCollection) ::) {
    <ns2:AsociadasCollection>
        <ns2:Asociadas>
            <ns2:idAsociadas></ns2:idAsociadas>
            <ns2:idOperacion>{fn:data($CrearOperacionAsociadaRequest/ns1:idOperacionPadre)}</ns2:idOperacion>
            <ns2:idOpAsoc>{fn:data($CrearOperacionAsociadaRequest/ns1:idOperacionAsociada)}</ns2:idOpAsoc>
            <ns2:fechaRegistro>{fn:current-date()}</ns2:fechaRegistro>
        </ns2:Asociadas>
    </ns2:AsociadasCollection>
};

local:func($CrearOperacionAsociadaRequest)
