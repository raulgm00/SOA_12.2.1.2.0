xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/MergeAgrupador";
(:: import schema at "../XSD/MergeAgrupador.xsd" ::)

declare variable $mergeAgrupadorRequest as element() (:: schema-element(ns1:MergeAgrupadorRequest) ::) external;

declare function local:func($mergeAgrupadorRequest as element() (:: schema-element(ns1:MergeAgrupadorRequest) ::)) as element() (:: schema-element(ns2:MergeAgrupadorInput) ::) {
    <ns2:MergeAgrupadorInput>
        <ns2:agrupador>{fn:data($mergeAgrupadorRequest/ns1:Agrupador)}</ns2:agrupador>
        <ns2:instancia>{fn:data($mergeAgrupadorRequest/ns1:instanciaProceso)}</ns2:instancia> 
    </ns2:MergeAgrupadorInput>
};

local:func($mergeAgrupadorRequest)