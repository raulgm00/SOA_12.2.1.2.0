xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraReactivarOperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraReactivarOperacion/V1/Schema/CrearBitacoraReactivarOperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarProcesosBPMByIdOperacion_DB";
(:: import schema at "../XSD/ConsultarProcesosBPMByIdOperacion_DB.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraReactivarOperacionBO";

declare variable $requestCrearBitacoraReactivarOperacionMessage as element() (:: schema-element(ns1:requestCrearBitacoraReactivarOperacionMessage) ::) external;

declare function local:func($requestCrearBitacoraReactivarOperacionMessage as element() (:: schema-element(ns1:requestCrearBitacoraReactivarOperacionMessage) ::)) as element() (:: schema-element(ns2:ConsultarProcesosBPMByIdOperacion_DBInput) ::) {
    <ns2:ConsultarProcesosBPMByIdOperacion_DBInput>
        <ns2:idOperacion>{fn:data($requestCrearBitacoraReactivarOperacionMessage/ns1:Bitacora/cre:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarProcesosBPMByIdOperacion_DBInput>
};

local:func($requestCrearBitacoraReactivarOperacionMessage)
