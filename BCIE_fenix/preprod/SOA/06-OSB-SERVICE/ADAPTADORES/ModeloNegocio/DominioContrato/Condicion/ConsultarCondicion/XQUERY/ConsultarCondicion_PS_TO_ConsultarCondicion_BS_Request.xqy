xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarCondicionDB";
(:: import schema at "../XSD/ConsultarCondicionDB_table.xsd" ::)

declare variable $CondicionRequest as element() (:: schema-element(ns1:ConsultarCondicionRequest) ::) external;

declare function local:func($CondicionRequest as element() (:: schema-element(ns1:ConsultarCondicionRequest) ::)) as element() (:: schema-element(ns2:ConsultarCondicionDBSelect_idCondicionInputParameters) ::) {
    <ns2:ConsultarCondicionDBSelect_idCondicionInputParameters>
        <ns2:idCondicion>{fn:data($CondicionRequest/ns1:idCondicion)}</ns2:idCondicion>
    </ns2:ConsultarCondicionDBSelect_idCondicionInputParameters>
};

local:func($CondicionRequest)
