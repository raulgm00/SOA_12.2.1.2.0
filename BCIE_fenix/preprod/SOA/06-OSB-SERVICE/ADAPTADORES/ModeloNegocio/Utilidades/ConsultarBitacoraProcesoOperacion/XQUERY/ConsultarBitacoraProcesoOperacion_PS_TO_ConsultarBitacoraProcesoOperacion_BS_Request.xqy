xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarBitacoraProcesoOperacion";
(:: import schema at "../XSD/ConsultarBitacoraProcesoOperacion.xsd" ::)

declare variable $ConsultarBitacoraProcesoOperacionRequest as element() (:: schema-element(ns1:ConsultarBitacoraProcesoOperacionRequest) ::) external;

declare function local:func($ConsultarBitacoraProcesoOperacionRequest as element() (:: schema-element(ns1:ConsultarBitacoraProcesoOperacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarBitacoraProcesoOperacionInput) ::) {
    <ns2:ConsultarBitacoraProcesoOperacionInput>
        <ns2:idOperacion>{fn:data($ConsultarBitacoraProcesoOperacionRequest/ns1:idOperacion)}</ns2:idOperacion>
        <ns2:idProceso>{fn:data($ConsultarBitacoraProcesoOperacionRequest/ns1:idProceso)}</ns2:idProceso>
    </ns2:ConsultarBitacoraProcesoOperacionInput>
};

local:func($ConsultarBitacoraProcesoOperacionRequest)
