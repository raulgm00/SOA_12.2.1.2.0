xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.bcie.com/CuestionarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Cuestionario/V1/Schema/CuestionarioMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ObtenerCuestionaroElegibilidadV2db";
(:: import schema at "../XSD/ObtenerCuestionaroElegibilidadV2db_sp.xsd" ::)

declare variable $reporteElegibilidad as element() (:: schema-element(ns1:CrearReporteElegibilidadV2Request) ::) external;

declare function local:func($reporteElegibilidad as element() (:: schema-element(ns1:CrearReporteElegibilidadV2Request) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_CODIGO_OPERACION>{fn:data($reporteElegibilidad/ns1:idOperacion)}</ns2:P_CODIGO_OPERACION>
    </ns2:InputParameters>
};

local:func($reporteElegibilidad)
