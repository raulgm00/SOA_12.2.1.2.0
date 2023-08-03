xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/CondicionesPeriodicasPorCumplir";
(:: import schema at "../XSD/CondicionesPeriodicasPorCumplir.xsd" ::)

declare variable $CondicionesPeriodicasPorCumplirRequest as element() (:: schema-element(ns1:CondicionesPeriodicasPorCumplirRequest) ::) external;

declare function local:func($CondicionesPeriodicasPorCumplirRequest as element() (:: schema-element(ns1:CondicionesPeriodicasPorCumplirRequest) ::)) as element() (:: schema-element(ns2:CondicionesPeriodicasPorCumplirInput) ::) {
    <ns2:CondicionesPeriodicasPorCumplirInput>
        <ns2:idOperacion>{fn:data($CondicionesPeriodicasPorCumplirRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:CondicionesPeriodicasPorCumplirInput>
};

local:func($CondicionesPeriodicasPorCumplirRequest)