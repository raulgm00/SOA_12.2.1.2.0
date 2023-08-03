xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarContratoByIdDesembolso";
(:: import schema at "../XSD/ConsultarContratoByIdDesembolso.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarContratoByIdDesembolsoOutputCollection as element() (:: schema-element(ns1:ConsultarContratoByIdDesembolsoOutputCollection) ::) external;

declare function local:func($ConsultarContratoByIdDesembolsoOutputCollection as element() (:: schema-element(ns1:ConsultarContratoByIdDesembolsoOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarContratoByIdDesembolsoResponse) ::) {
    <ns2:ConsultarContratoByIdDesembolsoResponse>
        <ns2:idDesembolso>{fn:data($ConsultarContratoByIdDesembolsoOutputCollection/ns1:ConsultarContratoByIdDesembolsoOutput/ns1:ID)}</ns2:idDesembolso>
        <ns2:idTcaDesembolso>{fn:data($ConsultarContratoByIdDesembolsoOutputCollection/ns1:ConsultarContratoByIdDesembolsoOutput/ns1:ID_TCA_ESTADO)}</ns2:idTcaDesembolso>
        <ns2:Resultado>
           <res:result>OK</res:result>
            <res:message>La consulta se ha realizado exitosamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarContratoByIdDesembolsoResponse>
};

local:func($ConsultarContratoByIdDesembolsoOutputCollection)
