xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarParticipantesByIdNoObjecion";
(:: import schema at "../XSD/ConsultarParticipantesByIdNoObjecion.xsd" ::)

declare variable $ConsultarParticipantesByIdNoObjecionRequest as element() (:: schema-element(ns1:ConsultarParticipantesByIdNoObjecionRequest) ::) external;

declare function local:func($ConsultarParticipantesByIdNoObjecionRequest as element() (:: schema-element(ns1:ConsultarParticipantesByIdNoObjecionRequest) ::)) as element() (:: schema-element(ns2:ConsultarParticipantesByIdNoObjecionInput) ::) {
    <ns2:ConsultarParticipantesByIdNoObjecionInput>
        <ns2:idNoObjecion>{fn:data($ConsultarParticipantesByIdNoObjecionRequest/ns1:idNoObjecion)}</ns2:idNoObjecion>
    </ns2:ConsultarParticipantesByIdNoObjecionInput>
};

local:func($ConsultarParticipantesByIdNoObjecionRequest)
