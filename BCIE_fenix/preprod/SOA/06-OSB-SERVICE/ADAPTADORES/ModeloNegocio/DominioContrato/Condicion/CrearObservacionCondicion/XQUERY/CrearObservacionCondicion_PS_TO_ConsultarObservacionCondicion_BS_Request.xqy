xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarObservacionCondicion";
(:: import schema at "../XSD/ConsultarObservacionCondicion_table.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare variable $CrearObservacionCondicionRequest as element() (:: schema-element(ns1:CrearObservacionCondicionRequest) ::) external;

declare function local:func($CrearObservacionCondicionRequest as element() (:: schema-element(ns1:CrearObservacionCondicionRequest) ::)) as element() (:: schema-element(ns2:ConsultarObservacionCondicionSelect_idObservacionInputParameters) ::) {
    <ns2:ConsultarObservacionCondicionSelect_idObservacionInputParameters>
        <ns2:idObservacion>{fn:data($CrearObservacionCondicionRequest/ns1:ObservacionCondicion/con:id)}</ns2:idObservacion>
    </ns2:ConsultarObservacionCondicionSelect_idObservacionInputParameters>
};

local:func($CrearObservacionCondicionRequest)
