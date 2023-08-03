xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarMiembroReunion_DB";
(:: import schema at "../XSD/EliminarMiembroReunion_DB.xsd" ::)

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $EliminarMiembroReunionRequest as element() (:: schema-element(ns1:EliminarMiembroReunionRequest) ::) external;

declare function local:func($EliminarMiembroReunionRequest as element() (:: schema-element(ns1:EliminarMiembroReunionRequest) ::)) as element() (:: schema-element(ns2:EliminarMiembroReunion_DBInput) ::) {
    <ns2:EliminarMiembroReunion_DBInput>
        <ns2:ID_TRE_COMITE_ROL>{fn:data($EliminarMiembroReunionRequest/ns1:listadoEmitirVoto/sol:MiembroReunion/sol:rol/cat:Id)}</ns2:ID_TRE_COMITE_ROL>
    </ns2:EliminarMiembroReunion_DBInput>
};

local:func($EliminarMiembroReunionRequest)
