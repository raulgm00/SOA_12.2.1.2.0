xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarComision_DB";
(:: import schema at "../XSD/EliminarComision_DB.xsd" ::)

declare variable $idComision as element() (:: schema-element(ns1:EliminarComisionRequest) ::) external;

declare function local:func($idComision as element() (:: schema-element(ns1:EliminarComisionRequest) ::)) as element() (:: schema-element(ns2:EliminarComision_DBInput) ::) {
    <ns2:EliminarComision_DBInput>
        <ns2:ID>{fn:data($idComision/ns1:Comision)}</ns2:ID>
    </ns2:EliminarComision_DBInput>
};

local:func($idComision)
