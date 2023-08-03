xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAgrupadorByCondicion";
(:: import schema at "../XSD/ConsultarAgrupadorByCondicion.xsd" ::)

declare variable $ConsultarAgrupadorByCondicionRequest as element() (:: schema-element(ns1:ConsultarAgrupadorByCondicionRequest) ::) external;

declare function local:func($ConsultarAgrupadorByCondicionRequest as element() (:: schema-element(ns1:ConsultarAgrupadorByCondicionRequest) ::)) as element() (:: schema-element(ns2:ConsultarAgrupadorByCondicionInput) ::) {
    <ns2:ConsultarAgrupadorByCondicionInput>
        <ns2:P_ID_CONTRATO>{fn:data($ConsultarAgrupadorByCondicionRequest/ns1:idContrato)}</ns2:P_ID_CONTRATO>
    </ns2:ConsultarAgrupadorByCondicionInput>
};

local:func($ConsultarAgrupadorByCondicionRequest)
