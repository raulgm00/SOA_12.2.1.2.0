xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTcaTermino";
(:: import schema at "../XSD/ConsultarTcaTermino.xsd" ::)

declare variable $ConsultarTerminobyIdLineaCreditoRequest as element() (:: schema-element(ns1:ConsultarTerminobyIdLineaCreditoRequest) ::) external;

declare function local:func($ConsultarTerminobyIdLineaCreditoRequest as element() (:: schema-element(ns1:ConsultarTerminobyIdLineaCreditoRequest) ::)) as element() (:: schema-element(ns2:ConsultarTcaTerminoInput) ::) {
    <ns2:ConsultarTcaTerminoInput>
        <ns2:P_ID_LINEA_CREDITO>{fn:data($ConsultarTerminobyIdLineaCreditoRequest/ns1:idLineaCredito)}</ns2:P_ID_LINEA_CREDITO>
    </ns2:ConsultarTcaTerminoInput>
};

local:func($ConsultarTerminobyIdLineaCreditoRequest)
