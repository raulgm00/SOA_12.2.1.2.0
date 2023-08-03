xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LeccionAprendidaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLeccionesAprendidas/LeccionAprendida/V1/Schema/LeccionAprendidaMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarRolesCategoriaLeccion";
(:: import schema at "../XSD/ConsultarRolesCategoriaLeccion.xsd" ::)

declare namespace lec = "http://www.bcie.org/LeccionAprendidaBO";

declare variable $ConsultarRolesCategoriaLeccionAprendidaRequest as element() (:: schema-element(ns1:ConsultarRolesCategoriaLeccionAprendidaRequest) ::) external;

declare function local:func($ConsultarRolesCategoriaLeccionAprendidaRequest as element() (:: schema-element(ns1:ConsultarRolesCategoriaLeccionAprendidaRequest) ::)) as element() (:: schema-element(ns2:ConsultarRolesCategoriaLeccionInput) ::) {
    <ns2:ConsultarRolesCategoriaLeccionInput>
        <ns2:idLeccion>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns1:LeccionAprendida/lec:idLeccionAprendida)}</ns2:idLeccion>
    </ns2:ConsultarRolesCategoriaLeccionInput>
};

local:func($ConsultarRolesCategoriaLeccionAprendidaRequest)
