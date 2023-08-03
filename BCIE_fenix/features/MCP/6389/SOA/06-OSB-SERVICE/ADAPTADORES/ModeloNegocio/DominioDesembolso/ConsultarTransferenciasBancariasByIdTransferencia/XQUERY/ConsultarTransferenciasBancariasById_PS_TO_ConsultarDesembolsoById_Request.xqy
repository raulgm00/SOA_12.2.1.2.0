xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarTransferenciaBancariaDB";
(:: import schema at "../XSD/ConsultarTransferenciaBancariaDB_sp.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare variable $ConsultarTransferenciasBancariasByIdRequest as element() (:: schema-element(ns2:OutputParameters) ::) external;

declare function local:func($ConsultarTransferenciasBancariasByIdRequest as element() (:: schema-element(ns2:OutputParameters) ::)) as element() (:: schema-element(ns1:ConsultarDesembolsosByIdRequest) ::) {
    <ns1:ConsultarDesembolsosByIdRequest>
        <ns1:idContratoDesembolso>{fn:data($ConsultarTransferenciasBancariasByIdRequest/ns2:RS_TRANSFERENCIAS/ns2:Row[1]/ns2:Column[@name='ID_CONTRATO_DESEMBOLSO'])}</ns1:idContratoDesembolso>
    </ns1:ConsultarDesembolsosByIdRequest>
};

local:func($ConsultarTransferenciasBancariasByIdRequest)
