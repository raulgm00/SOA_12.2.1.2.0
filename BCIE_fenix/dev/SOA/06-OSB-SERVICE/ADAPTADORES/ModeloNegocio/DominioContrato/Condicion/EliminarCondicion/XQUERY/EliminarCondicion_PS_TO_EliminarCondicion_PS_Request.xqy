xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarCondicion";
(:: import schema at "../XSD/EliminarCondicion.xsd" ::)

declare variable $idCondicion as element() (:: schema-element(ns1:EliminarCondicionRequest) ::) external;

declare function local:func($idCondicion as element() (:: schema-element(ns1:EliminarCondicionRequest) ::)) as element() (:: schema-element(ns2:EliminarCondicionInput) ::) {
    <ns2:EliminarCondicionInput>
        <ns2:ID>{fn:data($idCondicion/ns1:idCondicion)}</ns2:ID>
    </ns2:EliminarCondicionInput>
};

local:func($idCondicion)
