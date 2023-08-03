xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLineaCreditoByIdLineaCredito";
(:: import schema at "../XSD/ConsultarLineaCreditoByIdLineaCredito.xsd" ::)

declare variable $ConsultarLineaCreditoByIdLineaCreditoOutputCollection as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoOutputCollection) ::) external;

declare function local:func($ConsultarLineaCreditoByIdLineaCreditoOutputCollection as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarTerminobyIdLineaCreditoRequest) ::) {
    <ns2:ConsultarTerminobyIdLineaCreditoRequest>
        <ns2:idLineaCredito>{fn:data($ConsultarLineaCreditoByIdLineaCreditoOutputCollection/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput[1]/ns1:ID_LINEA_CREDITO)}</ns2:idLineaCredito>
    </ns2:ConsultarTerminobyIdLineaCreditoRequest>
};

local:func($ConsultarLineaCreditoByIdLineaCreditoOutputCollection)
