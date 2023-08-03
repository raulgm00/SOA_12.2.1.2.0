xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ValidarEnProcesoExcepcionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarEnProcesoExcepcion/V1/Schema/ValidarEnProcesoExcepcionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ValidarEnProcesoExcepcion_DB";
(:: import schema at "../XSD/ValidarEnProcesoExcepcion_DB.xsd" ::)

declare namespace val = "http://www.bcie.org/ValidarEnProcesoExcepcionBO";

declare variable $ValidarEnProcesoExcepcionRequest as element() (:: schema-element(ns1:ValidarEnProcesoExcepcionRequest) ::) external;

declare function local:func($ValidarEnProcesoExcepcionRequest as element() (:: schema-element(ns1:ValidarEnProcesoExcepcionRequest) ::)) as element() (:: schema-element(ns2:ValidarEnProcesoExcepcion_DBInput) ::) {
    <ns2:ValidarEnProcesoExcepcion_DBInput>
        <ns2:idSolicitud>{fn:data($ValidarEnProcesoExcepcionRequest/ns1:ProcesoExcepcion/val:idSolicitud)}</ns2:idSolicitud>
    </ns2:ValidarEnProcesoExcepcion_DBInput>
};

local:func($ValidarEnProcesoExcepcionRequest)
