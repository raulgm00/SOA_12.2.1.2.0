xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ReglaTareaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ReglaTarea/V1/Schema/ReglaTareaMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarReglasTarea_DB";
(:: import schema at "../XSD/ConsultarReglasTarea_DB.xsd" ::)

declare variable $ConsultarReglasTareaRequest as element() (:: schema-element(ns1:ConsultarReglasTareaRequest) ::) external;

declare function local:func($ConsultarReglasTareaRequest as element() (:: schema-element(ns1:ConsultarReglasTareaRequest) ::)) as element() (:: schema-element(ns2:ConsultarReglasTarea_DBInput) ::) {
    <ns2:ConsultarReglasTarea_DBInput>
        <ns2:idTarea>{fn:data($ConsultarReglasTareaRequest/ns1:idTarea)}</ns2:idTarea>
    </ns2:ConsultarReglasTarea_DBInput>
};

local:func($ConsultarReglasTareaRequest)