xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarTreLineaCredito";
(:: import schema at "../XSD/EliminarTreLineaCreditoAprobacionByIdLinea_table.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $EliminarTreLineaCreditoAprobacion as element() (:: schema-element(ns1:EliminarTreLineaCreditoAprobacionByIdLineaRequest) ::) external;

declare function local:func($EliminarTreLineaCreditoAprobacion as element() (:: schema-element(ns1:EliminarTreLineaCreditoAprobacionByIdLineaRequest) ::)) as element() (:: schema-element(ns2:EliminarTreLineaCreditoInput) ::) {
    <ns2:EliminarTreLineaCreditoInput>
        <ns2:idLineaCredito>{fn:data($EliminarTreLineaCreditoAprobacion/ns1:TreLineaCredito/lin:idLineaCredito)}</ns2:idLineaCredito>
        <ns2:idAprobacion>{fn:data($EliminarTreLineaCreditoAprobacion/ns1:TreLineaCredito/lin:idAprobacion)}</ns2:idAprobacion>
    </ns2:EliminarTreLineaCreditoInput>
};

local:func($EliminarTreLineaCreditoAprobacion)