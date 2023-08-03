xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarMomentoCobro_DB";
(:: import schema at "../XSD/ConsultarMomentoCobro_DB.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare variable $ConsultarMomentoCobroRequest as element() (:: schema-element(ns1:ConsultarMomentoCobroRequest) ::) external;

declare function local:func($ConsultarMomentoCobroRequest as element() (:: schema-element(ns1:ConsultarMomentoCobroRequest) ::)) as element() (:: schema-element(ns2:ConsultarMomentoCobro_DBInput) ::) {
    <ns2:ConsultarMomentoCobro_DBInput>
    'MOMENTO COBRO INPUT'
    </ns2:ConsultarMomentoCobro_DBInput>
};

local:func($ConsultarMomentoCobroRequest)