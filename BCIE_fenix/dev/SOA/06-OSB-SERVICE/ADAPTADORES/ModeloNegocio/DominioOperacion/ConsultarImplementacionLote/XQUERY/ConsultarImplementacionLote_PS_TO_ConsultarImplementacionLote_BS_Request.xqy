xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarImplementacionLoteSAD";
(:: import schema at "../XSD/ConsultarImplementacionLoteSAD.xsd" ::)

declare variable $requestConsultaImplementacion as element() (:: schema-element(ns2:ConsultarImplementacionLoteRequest) ::) external;

declare function local:func($requestConsultaImplementacion as element() (:: schema-element(ns2:ConsultarImplementacionLoteRequest) ::)) as element() (:: schema-element(ns1:ConsultarImplementacionLoteSADInput) ::) {
    <ns1:ConsultarImplementacionLoteSADInput>
        <ns1:ID_IMP>{fn:data($requestConsultaImplementacion/ns2:idImplementacion)}</ns1:ID_IMP>
        <ns1:ID_OPER>{fn:data($requestConsultaImplementacion/ns2:idOperacion)}</ns1:ID_OPER>
        <ns1:ID_LOTE>{fn:data($requestConsultaImplementacion/ns2:idLote)}</ns1:ID_LOTE>
    </ns1:ConsultarImplementacionLoteSADInput>
};

local:func($requestConsultaImplementacion)