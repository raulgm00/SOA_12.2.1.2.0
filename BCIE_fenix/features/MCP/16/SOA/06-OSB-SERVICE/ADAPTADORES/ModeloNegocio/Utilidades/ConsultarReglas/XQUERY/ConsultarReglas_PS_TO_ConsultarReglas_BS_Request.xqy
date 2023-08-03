xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ReglaTareaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ReglaTarea/V1/Schema/ReglaTareaMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarReglas_DB";
(:: import schema at "../XSD/ConsultarReglas_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare variable $ConsultarReglasRequest as element() (:: schema-element(ns1:ConsultarReglasRequest) ::) external;

declare function local:func($ConsultarReglasRequest as element() (:: schema-element(ns1:ConsultarReglasRequest) ::)) as element() (:: schema-element(ns2:ConsultarReglas_DBInput) ::) {
    <ns2:ConsultarReglas_DBInput>
        <ns2:tipo>{fn:data($ConsultarReglasRequest/ns1:Regla/reg:Tipo/cat:DescripcionCorta)}</ns2:tipo>
        <ns2:id>{fn:data($ConsultarReglasRequest/ns1:Regla/reg:Id)}</ns2:id>
    </ns2:ConsultarReglas_DBInput>
};

local:func($ConsultarReglasRequest)