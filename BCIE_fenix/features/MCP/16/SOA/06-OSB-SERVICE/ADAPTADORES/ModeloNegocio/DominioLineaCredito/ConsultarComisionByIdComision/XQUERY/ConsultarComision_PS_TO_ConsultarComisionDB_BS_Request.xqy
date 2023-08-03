xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarComisionDB";
(:: import schema at "../XSD/ConsultarComisionDB.xsd" ::)

declare variable $ConsultarComisionRequest as element() (:: schema-element(ns1:ConsultarComisionRequest) ::) external;

declare function local:func($ConsultarComisionRequest as element() (:: schema-element(ns1:ConsultarComisionRequest) ::)) as element() (:: schema-element(ns2:ConsultarComisionDBInput) ::) {
    <ns2:ConsultarCargoTramiteDBInput>
        <ns2:idCargo>{fn:data($ConsultarComisionRequest/ns1:idComision)}</ns2:idCargo>
        <ns2:idOperacion>{fn:data($ConsultarComisionRequest/ns1:idOperacion)}</ns2:idOperacion>
        <ns2:idMomentoCobro>{fn:data($ConsultarComisionRequest/ns1:idMomentoCobro)}</ns2:idMomentoCobro>
    </ns2:ConsultarCargoTramiteDBInput>
};

local:func($ConsultarComisionRequest)